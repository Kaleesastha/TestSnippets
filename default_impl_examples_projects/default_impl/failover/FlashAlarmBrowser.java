//$Id: FlashAlarmBrowser.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.alertui;

//java imports
import java.util.Vector;
import java.util.Properties;
import javax.swing.JApplet;

//Nms imports
import com.adventnet.nms.broadcast.BroadcastListener;
import com.adventnet.nms.broadcast.BroadcastClient;
import com.adventnet.nms.failover.FailOverListener;
import com.adventnet.nms.failover.FailOverEvent;
import com.adventnet.nms.util.PureClientUtils;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.startclient.AbstractBaseNmsPanel;

/**
 * This AbstractBaseNmsPanel will display the Alerts that are received during the 
 * FailOver period.Whenever NmsClient is started, this NmsPanel will 
 * register itself as a FailOverListener. So it will be notified whenever client failover 
 * is happen. In the preFailOverNotification method it will construct the FlashAlarmPanel 
 * which will take care of update the view.
 * 
 * This NmsPanel will act as a BroadCastListener. This is because assume if the client is 
 * connected to the standalone FE Server (Client -> FE ->  BE).In this combination if the 
 * primary server is down, then FE will attempt for failover. But the client does not know 
 * about BE failover. In that case the alarms collected during the failover period will not be
 * shown in UI. In WebNMS we have a default example process called ExampleFE. The functionality
 * of this ExampleFE is broadcast BE failover information to all the clients connected to 
 * that FE during failover.So whenever Broadcast message is received the FlashAlarmBrowser 
 * will be notified. This will interrupt the message and can able to find the BEFailover.  
 * 
 */

public class FlashAlarmBrowser extends AbstractBaseNmsPanel implements BroadcastListener,FailOverListener
{
	private FlashAlarmPanel panel = null; //Panel instance
	private boolean mainSocketFailure = false;
	private boolean genericSocketFailure = false;

	//Register as FailOverListener and BroadcastListener
	public void init(JApplet applet)
	{
		PureClientUtils.addFailOverListener(this, PureClientUtils.GENERAL);
		BroadcastClient.getInstance().registerForResponse(this);
	}

	public String key()
	{
		return "FailOverAlarms";
	}

	public void setProperties(Properties prop) {}

	//Implementation for FailOverListener
	public void preFailOverNotification(FailOverEvent event)
	{
		if(event.getSocketType() == PureClientUtils.MAIN_SOCKET)
		{
			mainSocketFailure = true;
		}
		else if(event.getSocketType() == PureClientUtils.GENERIC_SOCKET)
		{
			genericSocketFailure = true;
		}
		if(mainSocketFailure && genericSocketFailure)
		{
			doFailOverTasks();
		}
	}

	public void postFailOverNotification(FailOverEvent event)
	{
		if(event.getResult())
		{
			releaseResources();
		}
	}

	//Implementation for BroadcastListener
	public void receiveAndShow(String data)
	{
		//Assume FE is try to connect standby Server
		if(data.endsWith("Please wait till the standby server is completely initialized."))
		{
			doFailOverTasks();
		}
		//Assume FE is successfully connected to Standby Server
		else if(data.startsWith("FE successfully connected to the new WebNMS"))
		{
			releaseResources();
		}
	}

	private void doFailOverTasks()
	{
		//Get the standby server information.
		Vector feList = PureClientUtils.feList;
		Properties prop = null;
		for(int i=0; i<feList.size(); i++)
		{
			prop = (Properties)feList.elementAt(i);
			if("STANDBY".equalsIgnoreCase(prop.getProperty("FE_SERVER_TYPE")))
			{
				break;
			}
		}
		panel = new FlashAlarmPanel();
		//Add the panel into view
		NmsClientUtil.getMainPanel().addPanelToList(panel, "FailOverAlarmsView");
		panel.initialize(prop);
	}

	//Remove the resources used for the alarms view.
	private void releaseResources()
	{
		mainSocketFailure = false;
		genericSocketFailure = false;
		if(panel != null)
			panel.releaseResources();
		panel = null;
	}

}


