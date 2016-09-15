package com.adventnet.nms.failover;

import javax.swing.*;
import java.util.Properties;
import java.awt.*;

import com.adventnet.nms.startclient.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.util.NmsPanelEventListener;
import com.adventnet.nms.util.NmsPanelEvent;
/**
	* This class serve as an example for developing user client for listening fail over event.
	* This class set the status field in the ManiFrame when ever fail over event occured.
	*/

public class FailOverNotificationPanel extends AbstractBaseNmsPanel implements FailOverListener 
{
    public FailOverNotificationPanel()
    {
		PureClientUtils.addFailOverListener(this,PureClientUtils.GENERAL);
    }

    private void initLayout()
    {
    }
    
    /** init method with Applet parameter */
    public void init(JApplet app)
    {
    }
	public void init(Properties p)
	{
		super.init(p);
	}

    public String key()
    { 
        return "FAILOVER_ID"; 
	}

    public void setProperties(Properties prop)
    {
    }



    //Below methods of the interface NmsPanel are not implemented here.
    //As they are of no significance to us.
    public void start()
    {

    }

    public void stop()
    {
    }

    public void destroy()
    {
    }
	
	//This method will be called for notifying this Event.
	public void preFailOverNotification(FailOverEvent failoverevent)
	{
		String host = failoverevent.getOldHost();
		int  port = failoverevent.getOldPort();
		String message = NmsClientUtil.GetString("Communicaion failure occured and client gets disconnected from ") + host + NmsClientUtil.GetString("  at : ") + port;
		NmsClientUtil.showStatusOnLabel(message);
	}


	//This method will be called for notifying this Event.
	public void postFailOverNotification(FailOverEvent failoverevent)
	{
		boolean result = failoverevent.getResult();
		String message = null;
		if(result)
		{
			String host = failoverevent.getNewHost();
			int port = failoverevent.getNewPort();
			message = NmsClientUtil.GetString("FailOver Operation successfully completed. Client gets connected to ") + host + NmsClientUtil.GetString("  at : ") +port;

                        System.out.println("=====================================================");

                        System.out.println(" Jsession id ......." +NmsClientUtil.applet.getParameter( "jsessionid")); 
                        System.out.println(" NMS_FE_SECONDARY_PORT ......." +NmsClientUtil.applet.getParameter("NMS_FE_SECONDARY_PORT")); 
                        System.out.println(" NMS_FE_SECONDARY_PORT_DIR......."+ NmsClientUtil.applet.getParameter("NMS_FE_SECONDARY_PORT_DIR")); 
                        System.out.println(" TRANSPORT_PROVIDER ......." +NmsClientUtil.applet.getParameter("TRANSPORT_PROVIDER")); 
                        System.out.println(" RMI_REG_PORT  ......." +NmsClientUtil.applet.getParameter("RMI_REG_PORT")); 
                        System.out.println(" jan1_98 ......."+NmsClientUtil.applet.getParameter( "jan1_98")); 
                        System.out.println(" TIME_ZONE ......." +NmsClientUtil.applet.getParameter( "TIME_ZONE")); 
                        System.out.println(" CLIENT_CLASS_NAME ......." +NmsClientUtil.applet.getParameter("CLIENT_CLASS_NAME")); 
                        System.out.println(" KEEPALIVE_WINDOW_SIZE ......." +NmsClientUtil.applet.getParameter("KEEPALIVE_WINDOW_SIZE")); 

                        System.out.println("=====================================================");                    

                        System.out.println("=====================================================");
                        System.out.println("Time post ....."+failoverevent.getFailOverTime()); 
                        System.out.println("getPreviousFEInfo ....." +failoverevent.getPreviousFEInfo()); 
                        System.out.println("getPresentFEInfo ....." +failoverevent.getPresentFEInfo()); 
                        System.out.println("getOldHost ....."+failoverevent.getOldHost()); 
                        System.out.println("getOldPort ....."+failoverevent.getOldPort()); 
                        System.out.println("getResult ....."+failoverevent.getResult()); 
                        System.out.println("getSocketType ....."+failoverevent.getSocketType()); 
                        System.out.println("getResult ....."+failoverevent.getResult()); 
                        System.out.println("=====================================================");
                    


		}
		else
		{
			String host = failoverevent.getOldHost();
			int  port = failoverevent.getOldPort();
			message = NmsClientUtil.GetString("FailOver Operation unsuccessfully completed. Client gets disconnected from ") + host + NmsClientUtil.GetString("  at : ") + port;
		}
		NmsClientUtil.showStatusOnLabel(message);
	}

}//End of class FailOverNotificationPanel










