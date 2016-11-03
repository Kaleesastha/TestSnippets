//$Id: CurrentStatistics.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
//sample file to be invoked as custom action

package com.adventnet.nms.pollui;

//util imports
import java.util.Enumeration;
import java.util.Properties;
 import com.adventnet.snmp.beans.*;
 import com.adventnet.snmp.snmp2.*;
import java.util.*;
//WebNMS imports.
import com.adventnet.nms.util.*;

/**
 * This class just displays the properties given to it 
 * by the WebNMS.
 */
public class CurrentStatistics implements com.adventnet.nms.util.CustomClassInterface
{
 private SnmpPoller poller = null;
 private SnmpQuery sq =null;
 
 
 public void setProperties(Properties p[])
 {
        Properties selectedRow =p[0]; 
        String community = (String) selectedRow.getProperty ("community");
        String statsname = (String) selectedRow.getProperty("name");
        String dnsName = (String) selectedRow.getProperty ("dnsName");
        String oid_str = (String) selectedRow.getProperty ("oid");
        String multiple = (String) selectedRow.getProperty("isMultiplePolledData");
        String port=(String)selectedRow.getProperty("port");
        String active=(String)selectedRow.getProperty("active");
        String key=statsname+"\t"+(String)selectedRow.getProperty("agent")+"\t"+oid_str;
        String ownerName=(String)selectedRow.getProperty("ownerName");
        String abSol = ((String)selectedRow.getProperty("saveAbsolutes"));
        if(ownerName!=null)
        {
            if(!ownerName.equals("") && (!ownerName.equalsIgnoreCase("NULL")))
            {
                key=key+"\t"+ownerName;
            }
        }
        
	if(active.equals("false") && (NmsUiAPI.getTreeNodeProperties("Stats Admin").get("CURR-STAT")==null || !NmsUiAPI.getTreeNodeProperties("Stats Admin").get("CURR-STAT").equals("true")))
        {
            NmsClientUtil.showError(NmsClientUtil.GetString("Cannot plot current statistics,as statistics is inactive"));
            //NmsClientUtil.normalCursor(this);
            return;
        }

        
        String title = statsname +" "+dnsName+" " +oid_str;
        SnmpPoller poller = new SnmpPoller();
        poller.setCommunity(community);
        poller.setTargetHost(dnsName);
        poller.setObjectID(oid_str);
        poller.setPollInterval(15);
        poller.setTargetPort(Integer.parseInt(port));
        if(abSol!=null && abSol.equals("true"))
        {
              poller.setAbsoluteCounters(true);  
        }    

        /** Here we read time_out value set for the parameter snmp_timeout 
            under WebNMSHome/conf/clientParameters.conf and set it as time 
            out value for poller object.
        */
        String timeOut = NmsClientUtil.applet.getParameter ("snmp_timeout");
        if(timeOut != null)
            {
                int to =(Integer.valueOf(timeOut.trim())).intValue();
                try
                    {
                        poller.setTimeout (to);
                    }
                catch (NumberFormatException ne)
                    {
                        System.err.println (NmsClientUtil.GetString ("Invalid Snmp Timeout Parameter:") + timeOut);
                    }
            }
         
        Vector mulOid = null;  // MultipleOIDs, Vector to hold OIDs in case of multiple polling object.
        boolean dType = false; // polled data Type(String or Long)
        if(multiple.equals("true"))
            { 
                boolean test=true;
                SnmpVarBind varbind = poller.snmpGetNextVariableBinding();
                if(varbind == null)
                    {
                        //NmsClientUtil.normalCursor(this);
                        NmsClientUtil.err (NmsClientUtil.GetString ("Cannot get the value for Statistics as request timed out to")+" "+ oid_str);
                        return;
                    }
                dType = checkForPolledDataType(varbind);
                if(!dType)
                    return;
                else
                    {  
                        mulOid = new Vector();
                        mulOid.addElement(oid_str); 
                        boolean oidTest = true;
                        String vartest = null;
                        int length = oid_str.length();
                        while (true)  
                            {
                                vartest = varbind.getObjectID().toString();
                                int index = vartest.indexOf(oid_str);
                                 if(index  == -1)
                                    break;
                                poller.setObjectID(vartest);
                                mulOid.addElement(vartest.substring(index+length)); 
                                varbind = poller.snmpGetNextVariableBinding();
                            }
                        oid_str = oid_str+(String)mulOid.elementAt(1); 
                        poller.setObjectID(oid_str);
                    }
            }
        else
            {
                SnmpVarBind varbind = poller.snmpGetVariableBinding();
                if(varbind == null)
                    {
                        NmsClientUtil.err (NmsClientUtil.GetString ("Cannot get the value for Statistics as request timed out to")+" "+ oid_str);
                       // NmsClientUtil.normalCursor(this);
                        return;
                    }       
                dType = checkForPolledDataType(varbind);
                 if(!dType)
                     return;
            }
        
        /** As SnmpGraph and it related files are under 
            WebNMSHome/default_impl/clientcustomization it will enable 
            user to customize the graphs further.
        */
        sq = new SnmpQuery(title,poller,mulOid);
        //graph.setKey(key);
        sq.start ();
        //NmsClientUtil.normalCursor(this);
        return;
    }	
	
 

   private boolean checkForPolledDataType(SnmpVarBind varbind)
    {
        SnmpVar var = varbind.getVariable();

        try 
        {
       		String val = var.toString();
            long value = Long.parseLong(val);
        }
        catch (NumberFormatException exc) 
        {
			try
			{
		        	if(var.getType() == SnmpAPI.COUNTER64 || var.getType() == SnmpAPI.TIMETICKS )
      	 		  	{
 					return true;
				}
				else
				{
					NmsClientUtil.err (NmsClientUtil.GetString ("Cannot Plot"
					+" Graph for String Values "));
					//NmsClientUtil.normalCursor(this);  
					return false;
				}	
			}
			catch(NumberFormatException nfe)
			{
	            			NmsClientUtil.err (NmsClientUtil.GetString ("Cannot Plot Graph for String Values "));
                			//NmsClientUtil.normalCursor(this);
			               return false;
            			}
 	}
	return true;
    }
	
}

 



