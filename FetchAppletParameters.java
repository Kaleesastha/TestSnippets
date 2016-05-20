/* $Id: FetchAppletParameters.java,v 1.2.10.1 2014/10/20 05:56:24 venkatramanan Exp $ */

package com.adventnet.nms.servlets;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;
//import	org.w3c.dom.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.startnms.NmsMainFE;
//import org.apache.crimson.tree.*;
//import com.adventnet.nms.fe.utils.*;

public class FetchAppletParameters extends HttpServlet
{

    /**
     * Handle POST the same as GET.
     * This method is simply a call to doGet().
     *
     * @param req encapsulates the request to the servlet
     * @param resp encapsulates the response from the servlet
     * @see javax.servlet.http.HttpServletRequest#getPathTranslated
     * @exception ServletException will be passed on from included servlets
     * @see #doGet
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
        doGet(req, res);
    }

    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
	    PrintWriter p = res.getWriter();
	    String userName = req.getParameter("userName");
	    if( userName == null){
		    userName = req.getHeader("userName");
	    }

	    if(!NmsUtil.webNMSModulesStarted || !NmsMainFE.isStarted)
	    {
		    p.println("Sorry");
		    p.flush();
		    p.close();
		    return;
	    }else
	    {
		    p.println("OK");
	    }

	    // this parameter is added to bypass the authentication 
	    // when moving from Application Client to HTML Client
	    String jsessionid = req.getSession().getId();
	    p.println("<PARAM name=jsessionid value="+jsessionid+">");
	    //p.println("<PARAM name=SHOW_BUTTONS value=false>");
	    //p.println("<PARAM name=PASSWORD_KEY value=" + userName + ">");
	    //p.println("<PARAM name=HANDLE value=" + userName +">");
	    //p.println("<PARAM name=USE_MAS value=true>");
	    //p.println("<PARAM name=USER_NAME value=" + userName + ">");
	    String rmi = NmsUtil.getParameter("SECURE_RMI");//NO I18N
	    if(rmi != null && !rmi.equals(""))//NO I18N
	    {
		    p.println("<PARAM name=SECURE_RMI value=\""+rmi.toUpperCase()+"\">");//NO I18N
	    }
	    p.println("<PARAM name=NMS_FE_SECONDARY_PORT value=\""+PureServerUtilsFE.nms_fe_secondary_port+"\">");
	    p.println("<PARAM name=NMS_FE_SECONDARY_PORT_DIR value=\""+PureServerUtilsFE.nms_fe_secondary_port_dir+"\">");

	    if(!com.adventnet.nms.fe.sas.NmsSAServerFE.isSAServerRunning())
	    {
		    p.println("<PARAM name=TRANSPORT_PROVIDER value=\"com.adventnet.nms.client.sas.SASClientTransporter\">");
	    }
            /*
              else
              {
              p.println("<PARAM name=TRANSPORT_PROVIDER value= >" );
              }
            */
	    //------------------- Fix to update BE Host Address During Failover -----------------------

                String be_hostName = PureServerUtilsFE.be_host;
                String be_address = PureServerUtilsFE.be_host;
                if(be_address.equals("localhost"))
                {
                        try
                        {
                                be_hostName = java.net.InetAddress.getLocalHost().getHostName();
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
                p.println("<PARAM name=BE_HOST_NAME value=\"" + be_hostName + "\">"); //No Internationalization
                p.println("<PARAM name=BE_HOST_ADDRESS value=\"" + be_address + "\">"); //No Internationalization

        //----------------------------------  End of Fix  ----------------------------------

	    int regPort = NmsUtil.getRegistryPort();
	    //Need to do this b'cos the NmsUtil.getRegistryPort() returns -1 incase of RMI_REGISTRY_PORT not specified in the ServerParameters.conf !!
	    if(regPort == -1)
	    {
		    regPort = 1099;
	    }

	    p.println("<PARAM name=RMI_REG_PORT value=\"" +regPort + "\">"); 

	    // p.println("<PARAM name=CLIENT_SERVER value=" + NmsMainFE.proto + ">");

	    Calendar cal = Calendar.getInstance();
	    cal.set(1998,0,1,0,0,0);
	    Date d = cal.getTime();
	    p.println("<PARAM name=jan1_98 value="+d.getTime()+">");
	    String s=String.valueOf(TimeZone.getDefault().getRawOffset());
	    p.println("<PARAM name=TIME_ZONE value="+s+">");


	    String clientClassName = PureServerUtilsFE.getClientTransportClassName();
	    p.println("<PARAM name=CLIENT_CLASS_NAME value="+clientClassName+">");

	    p.println("<PARAM name=KEEPALIVE_WINDOW_SIZE value="+ String.valueOf(NmsUtil.keepalive_window_size) +">");

	    p.flush();

	    p.close();
    }
}
