/* $Id: StartClientServlet.java,v 1.5 2008/09/05 18:34:56 govardhini Exp $ */

package com.adventnet.nms.servlets;

import java.io.*;
import java.util.*;
import java.rmi.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;

import com.adventnet.nms.netwatch.*;
import com.adventnet.nms.startnms.NmsMainFE;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureServerUtilsFE;
import com.adventnet.nms.commonbe.BEServerContext;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.util.ClientParameters;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.authentication.UserConfigAPI;
import com.adventnet.nms.servlets.AuthenticationServlet;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.jsp.JspUtility;

import java.sql.Connection; 
import java.sql.SQLException;
import com.adventnet.nms.db.util.DBXmlUpdate;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.adventnet.management.log.Log;

public class StartClientServlet extends  HttpServlet
{
    String serverNotInitialized = new String("Sorry");

    public synchronized void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        GenericUtility.KeyForCvcVSloginTime.put(req.getSession(true).getId(),new Date());
        PrintWriter p = res.getWriter();
        String userName = req.getParameter("userName");
		if( userName == null){
			userName = req.getHeader("userName");
		}
        //String userName = req.getRemoteUser();
        //String mode = "user";

        if(!NmsUtil.webNMSModulesStarted || !NmsMainFE.isStarted)
        {
            p.println(serverNotInitialized);
            p.flush();
            p.close();
            return;
        }

        for(Enumeration e = NmsUtil.runProcessModules.keys();e.hasMoreElements();)
        {
            String key = (String)e.nextElement();
            RunProcessInterface rpi = (RunProcessInterface)NmsUtil.runProcessModules.get(key);
            if(!rpi.isInitialized())
            {
                p.println(serverNotInitialized);
                p.flush();
                p.close();
                return;
            }
        }

        if (!NmsMainFE.singleJVM)
        {
            try
            {
                //this has to be the first executable after getting the userName
                NmsMainFE.downloadClientFilesFromBE(userName);
            }
            catch (Exception e)
            {
                NmsLogMgr.MISCERR.fail("Exception in downloading client related files from the BackEnd Server...", e);
                //we can also quit
            }
        }


        //To Populate Data in Database from Tree.xml for new user
        try{
            Connection con = NmsUtil.relapi.getConnection();
            DBXmlUpdate dbxmlupdate = new DBXmlUpdate(con);
            boolean ans = dbxmlupdate.updateDB(userName,"Tree.xml");
            NmsLogMgr.MISCUSER.log("Populated database for User "+userName+" "+String.valueOf(ans)+" ",Log.SUMMARY);
        }
        catch (Exception e)
        {
            NmsLogMgr.MISCERR.fail("Exception in Populating the database for the user...", e);
            
        }



        //Doing this mode identification after the process are started..!!!
        UserConfigAPI userConfigAPI = (UserConfigAPI) NmsUtil.getAPI("UserConfigAPI");
        String realms = null;
        if(userConfigAPI != null)
        {
			/*
            if(userConfigAPI.isUserNamePresent("Admin",userName))
            {
                mode = "Admin";
            }
            else
            {
                mode = "user";
            }
			*/
            Vector realm = userConfigAPI.getRealm(userName);
            if(realm != null)
            {
                realms = getRealmString(realm.toString().trim());
            }		       
        }
        else
        {
            p.println(serverNotInitialized);
            p.flush();
            p.close();
            return;
        }
        String remoteaddr = req.getRemoteAddr();
        NmsUtil.current_keys.addElement(remoteaddr);

		// if the user's password has expired, this parameter
		// will be send to the client.
/*
		if(isPasswordExpired())
		{
        	p.println("<PARAM name=PASSWORD_EXPIRED value=true>");
		}
	*/	

		// this parameter is added to bypass the authentication 
		// when moving from Application Client to HTML Client
		String jsessionid = req.getSession().getId();
        p.println("<PARAM name=jsessionid value="+jsessionid+">");

        p.println("<PARAM name=MAP_FILE value=ipnet.netmap>");
        p.println("<PARAM name=SHOW_BUTTONS value=false>");
        p.println("<PARAM name=PASSWORD_KEY value=" + userName + ">");
        p.println("<PARAM name=HANDLE value=" + userName +">");
        p.println("<PARAM name=USE_MAS value=true>");
        p.println("<PARAM name=USER_NAME value=" + userName + ">");




        p.println("<PARAM name=NMS_FE_SECONDARY_PORT value=\""+PureServerUtilsFE.nms_fe_secondary_port+"\">");
        p.println("<PARAM name=NMS_FE_SECONDARY_PORT_DIR value=\""+PureServerUtilsFE.nms_fe_secondary_port_dir+"\">");

        if(!com.adventnet.nms.fe.sas.NmsSAServerFE.isSAServerRunning())
        {
            p.println("<PARAM name=TRANSPORT_PROVIDER value=\"com.adventnet.nms.client.sas.SASClientTransporter\">");
        }

        //p.println("<PARAM name=MODE value=" + mode + ">");
        
        if(realms != null)
        {
              p.println("<PARAM name=REALMS value=" + realms + ">");   
        }

        // To pass the BE_HOST_NAME to the client!!. Actually such a thing is not required at the
        // Client side b'cos anyway the Clients' cannot contact the BE at all (Applet Security 
        // restrictions). But this is being sent JFI on the Client side.
        String be_hostName = PureServerUtilsFE.be_host;
        if(be_hostName.equals("localhost"))
        {
            try
            {
                be_hostName = java.net.InetAddress.getLocalHost().getHostName();
            }
            catch(Exception e)
            {
            }
        }
        //Added by Balan on 18/03/03 getting IPAddress of BE
        String be_address = PureServerUtilsFE.be_host;
        if(be_address.equals("localhost"))
        {
            try
            {
                be_address = java.net.InetAddress.getLocalHost().getHostAddress();
            }
            catch(Exception e)
            {
            }
        }
        else
        {
            be_address = java.net.InetAddress.getByName(be_address).getHostAddress();
        }
        //Add Ends
        p.println("<PARAM name=BE_HOST_NAME value=\"" + be_hostName + "\">");

       //Added by Balan on 18/03/03 getting  IPAddress of BE
         p.println("<PARAM name=BE_HOST_ADDRESS value=\"" + be_address + "\">");
       //Add Ends

        int regPort = NmsUtil.getRegistryPort();
        //Need to do this b'cos the NmsUtil.getRegistryPort() returns -1 incase of RMI_REGISTRY_PORT not specified in the ServerParameters.conf !!
        if(regPort == -1)
        {
            regPort = 1099;
        }

        p.println("<PARAM name=RMI_REG_PORT value=\"" +regPort + "\">"); 

        p.println("<PARAM name=CLIENT_SERVER value=" + NmsMainFE.proto + ">");

	p.println("<PARAM name=ALLOW_PASSWORD_REUSE value=\"" +NmsUtil.getParameter("ALLOW_PASSWORD_REUSE") +"\">");   //No I18n


	p.println("<PARAM name=DEVICE_VIEW value=" +NmsUtil.getParameter("DEVICE_VIEW") +">");   //No I18n
        Calendar cal = Calendar.getInstance();
        cal.set(1998,0,1,0,0,0);
        Date d = cal.getTime();
        p.println("<PARAM name=jan1_98 value="+d.getTime()+">");
        //String s=String.valueOf(TimeZone.getDefault().getRawOffset());
        //p.println("<PARAM name=TIME_ZONE value="+s+">");

        //Vel : Start
        if (!NmsUtil.createUserDir)
        {
		userName = null;
		p.println("<PARAM name=CREATE_USERDIR value=false>"); //No I18N
	}
        else
        {
                p.println("<PARAM name=CREATE_USERDIR value=true>"); //No I18N
        }
        //Vel : End
	
	readClientParameters(userName);


        String feTimeZoneID = TimeZone.getDefault().getID();
        String beTimeZoneID = getBETimeZoneID();
        
        p.println("<PARAM name=BE_TIMEZONE_ID value=" + beTimeZoneID + ">");
        p.println("<PARAM name=FE_TIMEZONE_ID value=" + feTimeZoneID + ">");
        
        String commonAction = NmsUtil.getParameter("COMMON_NOTIFICATION");//No i18n
        if(commonAction != null && commonAction.equalsIgnoreCase("false"))
        {
            commonAction = "false";//No i18n
        }
        else
        {
            commonAction = "true";//No i18n
        }
        p.println("<PARAM name=COMMON_NOTIFICATION value=" + commonAction + ">");//No i18n

        for(Enumeration e = paramList.keys();e.hasMoreElements();)
        {
            String key = (String)e.nextElement();
            p.println("<PARAM name=\"" + key + "\" value=\"" + (String)paramList.get(key)+"\">");
        }



		NmsLogMgr.MISCUSER.log("User "+userName+"  logged into the Application Client at " + JspUtility.gettime() +" from "+ remoteaddr,Log.SUMMARY);

        p.flush();
        p.close();
    }

    private String getRealmString(String realm)
    {
        realm = realm.substring(1,realm.length()-1);
        StringBuffer buff = new StringBuffer();
        StringTokenizer toks = new StringTokenizer(realm,",",true);
        while(toks.hasMoreTokens())
        {
            buff.append(toks.nextToken().trim());
        }
       return buff.toString();
    }
   
    Hashtable paramList = new Hashtable(50);
    
    /**To read client parameters
     */
    public void readClientParameters(String userName)
    {
        paramList.clear();
        String s = NmsMainFE.proto;
        ClientParameters clientParams = new ClientParameters();
        clientParams.readClientParameters(paramList);
        
        if(userName != null)
        {
            ClientParameters tempClientParams = new ClientParameters(userName);
            Hashtable tempParamList = new Hashtable(50);
            tempClientParams.readClientParameters(tempParamList);
            paramList.putAll(tempParamList);
        }
            
        String nodesToOpen=(String)paramList.get("INIT_PANEL");

        if (nodesToOpen ==null)
	     {
             /*
              * If no INIT_PANEL has been specified in the clientparameters.conf file, we are
		  * providing the localnetwork map as the default one to be selected.
             */
                 nodesToOpen = "ipnet.netmap";
		 try
		 {
                  TopoAPI topoapi = GenericUtility.getTopoAPI();
			nodesToOpen = topoapi.getLocalNetworkAddrs()+".netmap";
	       }
		catch(Exception ex)
		{
		     System.err.println("Exception while obtaining the LocalNetworkAddr from the Server.");




		     ex.printStackTrace();
             nodesToOpen = "ipnet.netmap";	

		}
             paramList.put("INIT_PANEL",nodesToOpen);
	     }

        paramList.put("CLIENT_SERVER",s);
        String clientClassName = PureServerUtilsFE.getClientTransportClassName();
        paramList.put("CLIENT_CLASS_NAME",clientClassName);
        paramList.put("KEEPALIVE_WINDOW_SIZE", new Integer(NmsUtil.keepalive_window_size).toString());
    }

    /**To print log messages.
     */
    public void log(String msg)
    {
        try
        {
            if (msg == null) super.log("No detail.");
            else super.log(msg);
        }
        catch (Exception ex)
        {
            System.err.println("Problems logging.  Ignoring exception: "+ex);
            ex.printStackTrace();
        }
    }
    
    private String getBETimeZoneID()
    {
        BEServerContext svrCntxt=PureServerUtilsFE.getBEServerContext();
        Properties prop=svrCntxt.getProperties();
        String timeZoneID=(String)prop.get("TimeZoneID");
        return timeZoneID;
    }
    


}
