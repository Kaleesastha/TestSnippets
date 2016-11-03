/* $Id: TL1StatusPoller.java,v 1.3 2008/07/31 08:49:45 barathv Exp $ */
/**
* TL1StatusPoller.java
* Copyright 2001. AdventNet, Inc. All Rights Reserved.
* Author Rajesh Purushothaman
**/

package com.adventnet.nms.topodb.tl1;

import com.adventnet.management.ManagementServer;
import com.adventnet.management.ManagementServerResultEvent;
import com.adventnet.management.Property;
import com.adventnet.management.tl1.TL1Property;
import com.adventnet.nms.netwatch.UserTester;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.severity.SeverityIterator;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.TopoObject;
import com.adventnet.nms.util.Ping;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.management.log.Log;
import com.adventnet.tl1.message.TL1InputMessage;
import com.adventnet.tl1.message.TL1ResponseMessage;
import com.adventnet.tl1.message.TL1AutonomousMessage;
import com.adventnet.tl1.message.TL1Line;
import com.adventnet.tl1.message.TL1Param;
//import com.adventnet.tl1.message.TL1ParamValue;
import com.adventnet.tl1.parser.TL1InputMessageParser;
import com.adventnet.tl1.parser.TL1ResponseMessageParser;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Hashtable;


public class TL1StatusPoller implements UserTester
{
    private TL1DiscProcess tl1discPr = null;
    private ManagementServer mSvr = null;
    private SeverityIterator s_iter = null;

//----------------------------------------------------------------------------
   
    public TL1StatusPoller()
    {
        if (mSvr == null) mSvr = ManagementServer.getInstance();
        if (tl1discPr == null) tl1discPr = TL1DiscProcess.getHandle();
        if (s_iter == null) s_iter = SeverityInfo.getInstance().getIterator();
    }

//----------------------------------------------------------------------------

    public int test(String moname,Properties prop , TopoAPI tapi)
    {
        ManagedObject obj = null;
        try
         {
             obj = tapi.checkOut(moname,0,false,true);
             
         }
         catch( Exception ex) {
            NmsLogMgr.TOPOERR.fail("Exception in getting the printer object  " + obj.getName(),ex); // no i18n
        }
        if (obj == null) 
            return SeverityInfo.getInstance().getSpecialPurposeSeverity();
        if ( ! (obj instanceof TL1Interface) ) 
         // return obj.getStatus();
            return SeverityInfo.getInstance().getSpecialPurposeSeverity();

        String ipaddress = ((TopoObject)obj).getIpAddress();
        if ( (ipaddress == null) || ( ! Ping.ping(ipaddress)) ) {
            SeverityIterator s_iter =
                              SeverityInfo.getInstance().getIterator();
            s_iter.moveToHighest(SeverityInfo.LEFT); // Default. Optional.
            return s_iter.getCurrent();              // CRITICAL=1
        }

        String sessionId = ((TL1Interface)obj).getSessionId();
        if ((sessionId == null) || sessionId.equals("unknown")) {
            s_iter.moveToHighest(SeverityInfo.LEFT); // Default. Optional.
            return s_iter.getPreviousCriticality(1); // MAJOR=2 
        }

        Vector statpollV = ((TL1Interface)obj).getStatpollCommandV();
        if ((statpollV == null) || (statpollV.size() == 0))
            return SeverityInfo.getInstance().getClear();    // CLEAR=5
       
        if (mSvr == null) { 
            s_iter.moveToHighest(SeverityInfo.LEFT); // Default. Optional.
            return s_iter.getPreviousCriticality(1); // MAJOR=2 
        }
            
        TL1Property tl1P = new TL1Property();
        tl1P.setTargetHost(ipaddress);
        tl1P.setTargetPort(String.valueOf( ((TL1Interface)obj).getTl1port() ));
        tl1P.setConnectionHandler( ((TL1Interface)obj).getConnectionHandler() );
        tl1P.setSessionId( ((TL1Interface)obj).getSessionId() );
        tl1P.setResultType(Property.RESULT_STRING);

        for (int i=0; i<statpollV.size(); i++) {
            tl1P.setCommandCode("");
            tl1P.setTargetId("");
            tl1P.setAccessId("");
            tl1P.setGeneralBlock("");
            tl1P.setMessagePayloadBlock("");

            int severity = syncSendCommand(tl1P, 
                                        (Hashtable) statpollV.elementAt(i));
            if (severity != SeverityInfo.getInstance().getClear())
                return severity;
        }

        return SeverityInfo.getInstance().getClear();    // CLEAR=5
    }


//----------------------------------------------------------------------------

    private int syncSendCommand(TL1Property tl1P, Hashtable commandP)
    { 
        String command = commandP.get("command").toString();
        if (command == null) {
            s_iter.moveToHighest(SeverityInfo.LEFT); // Default. Optional.
            return s_iter.getPreviousCriticality(1); // MAJOR=2 
        }
        
        try {

            TL1InputMessageParser inParser = new TL1InputMessageParser();
            TL1InputMessage inMess = (TL1InputMessage)inParser.createTL1Message(command);

            if (inMess.getCommandCode() != null)
                tl1P.setCommandCode(inMess.getCommandCode().toString());
            if (inMess.getTargetId() != null)
                tl1P.setTargetId(inMess.getTargetId());
            if (inMess.getAccessId() != null) 
                tl1P.setAccessId(inMess.getAccessId().toString());
            if (inMess.getGeneralBlock() != null)
                tl1P.setGeneralBlock(inMess.getGeneralBlock().toString());
            if (inMess.getMessagePayloadBlock() != null)
                tl1P.setMessagePayloadBlock(inMess.getMessagePayloadBlock().toString());
        } catch (Exception ex) {
            NmsLogMgr.TOPOERR.fail (NmsUtil.GetString("TL1: Error parsing Status Poll Command : ")+command, ex);
            s_iter.moveToHighest(SeverityInfo.LEFT); // Default. Optional.
            return s_iter.getPreviousCriticality(1); // MAJOR=2 
        }
        
        ManagementServerResultEvent resultEv = mSvr.syncSend(tl1P);
        if (resultEv == null) {
            s_iter.moveToHighest(SeverityInfo.LEFT); // Default. Optional.
            return s_iter.getPreviousCriticality(1); // MAJOR=2 
        }
        
        TL1ResponseMessage resMess = convertResponse(resultEv);
        if (resMess == null) {
            s_iter.moveToHighest(SeverityInfo.LEFT); // Default. Optional.
            return s_iter.getPreviousCriticality(1); // MAJOR=2 
        }

        String code = resMess.getResponseId().getCompletionCode();
        if ((code == null) || ( ! code.trim().equalsIgnoreCase("compld") )) {
            s_iter.moveToHighest(SeverityInfo.LEFT); // Default. Optional.
            return s_iter.getPreviousCriticality(1); // MAJOR=2 
        }
         
        String response = (String) commandP.get("response");
        if (response == null)
            return SeverityInfo.getInstance().getClear();    // CLEAR=5

        if (checkValues(resMess, response)) {
            return SeverityInfo.getInstance().getClear();    // CLEAR=5
        } else { 
            s_iter.moveToHighest(SeverityInfo.LEFT); // Default. Optional.
            return s_iter.getPreviousCriticality(1); // MAJOR=2
        } 
    }
        
//----------------------------------------------------------------------------
    
    public boolean checkValues(TL1ResponseMessage resMess, String response)
    {
     // Vector tl1LineV = resMess.getTextBlock().getOutputText();
        Vector tl1LineV = resMess.getTextBlock().getTL1LineList();
        if ((tl1LineV == null) || (tl1LineV.size() == 0)) return false;

        StringTokenizer strTok = new StringTokenizer(response,",");
        for (;strTok.hasMoreTokens();) {
            String param = strTok.nextToken();
            int sep = param.indexOf("=");

            // Only name-value pairs will be taken
            if (sep == -1) continue;

            String str1 = param.substring(0,sep);
            String str2 = param.substring(sep+1, param.length());

            boolean isParamPresent = false;
            for (int i=0; i<tl1LineV.size(); i++) {
                Hashtable ht = null;
                if (tl1LineV.elementAt(i) instanceof TL1Line) {
                    TL1Line tl1Line = (TL1Line) tl1LineV.elementAt(i);
                    ht = convertTL1Line(tl1Line, false);
                } else {
                    NmsLogMgr.TOPOERR.log(NmsUtil.GetString("TL1: Response Message block is not proper"), Log.INTERMEDIATE_DETAIL);
                    continue;
                }

                if ((ht == null) || (ht.size() == 0)) continue;
                if (ht.get(str1) != null) {
                    isParamPresent = true;
                    if (ht.get(str1).toString().equals(str2))
                        break;
                    else 
                        return false;
                }
            }
            if ( ! isParamPresent ) return false;
        }

        return true;
    }

//----------------------------------------------------------------------------

    private TL1ResponseMessage convertResponse(ManagementServerResultEvent rev)
    {
        if (rev == null) return null;

        String resMessStr = null;

        Object resMessObj = rev.getResult();
        if (resMessObj == null) { 
            return null;
        } else if (resMessObj instanceof Integer) {
            return null;
        } else if (resMessObj instanceof Long) {
            return null;        
        } else if (resMessObj instanceof TL1ResponseMessage) {
            return (TL1ResponseMessage) resMessObj;
        } else if (resMessObj instanceof String) { 
            resMessStr = resMessObj.toString();
        }
               
        if (resMessStr == null) return null;
        resMessStr = findAndReplace(resMessStr,"<CR>\n","\r");
        resMessStr = findAndReplace(resMessStr,"<LF>","\n");
        try {
            TL1ResponseMessageParser resParser = new TL1ResponseMessageParser();
            TL1ResponseMessage resMess = (TL1ResponseMessage)resParser.createTL1Message(resMessStr);
            
            return resMess;
        } catch (Exception ex) {
            NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("TL1: Error parsing Response Message : ")+resMessStr, ex);
            return null;
        }
    }

//----------------------------------------------------------------------------

    private Hashtable convertTL1Line(TL1Line tl1Line, boolean pdef)
    {
        Hashtable p = new Hashtable();

        if (tl1Line == null) return p;
        Vector blockV = tl1Line.getBlock();
        if ((blockV == null) || (blockV.size() == 0)) return p;

        int position = 0;

        for (int i=0; i<blockV.size(); i++) {
            Vector paramV = (Vector) blockV.elementAt(i);
            if ((paramV == null) || (paramV.size() == 0)) continue;

            for (int j = 0; j < paramV.size(); j++) {
                TL1Param param = (TL1Param) paramV.elementAt(j);
                if (param == null) continue;

                String paramName = param.getParamName();
             /* TL1ParamValue paramValue = param.getParamValue();
                if ( (paramValue == null) ||
                     (paramValue.getValue().trim().equalsIgnoreCase("null")) )
                    continue; */
                String paramValue = param.getParamValue();
                if ( (paramValue == null) ||
                     (paramValue.trim().equalsIgnoreCase("null")) )
                    continue;

                if ( (paramName == null) ||
                     (paramName.trim().equalsIgnoreCase("null")) ) {
                    if (pdef) {
                     // p.put(String.valueOf(position), paramValue.getValue());
                        p.put(String.valueOf(position), paramValue);
                    } else {
                        if (p.get("position_defined") == null)
                            p.put("position_defined", new String());
                        String position_def =
                                 (String) p.get("position_defined");
                     // p.put("position_defined", position_def+"#"+
                     //                               paramValue.getValue());
                        p.put("position_defined", position_def+"#"+paramValue);
                    }
                } else {
                 // p.put(paramName, paramValue.getValue());
                    p.put(paramName, paramValue);
                }

                position++;
            }
        }

        return p;
    }

//----------------------------------------------------------------------------

    private String findAndReplace(String str, String find, String replace)
    {
        String des = new String ();
        while (str.indexOf (find) != -1) {
            des += str.substring (0, str.indexOf (find));
            des = des + replace;
            str = str.substring (str.indexOf (find) + find.length ());
        }
        des += str;
        return des;
    }
    
//----------------------------------------------------------------------------

}
