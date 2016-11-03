//$Id: ExampleFEServer.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.example.befe;
// NMS Imports
import com.adventnet.nms.startnms.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.fe.alert.AlertFE;
import com.adventnet.nms.fe.event.EventFE;

//Java Imports
import java.util.*;

/* This class gets notified through its  init(MainSocketSessionFE mss),
   whenever a client connects to the FE, if it is registered with the MainSocketServerFE.
   This class spawns a new Session for handling the requests from that client.*/

public class ExampleFEServer implements SocketServerConnectionFE
{
	// Holds the EventAPI instance.
	static  EventAPI eventAPI;
	// Holds the AlertAPI instance
	static AlertAPI alertAPI;
	// Holds the Severity information
	static Hashtable Severitytable=new Hashtable(10);
	
	public ExampleFEServer()
	{
		PureServerUtilsFE.serverSocketFE.registerForResponses(this);
	}
	
	public void init(MainSocketSessionFE mss)
	{
		eventAPI=getEventAPI();
		alertAPI=getAlertAPI();
		getSeverityInfo();
		
		ExampleFESession session = new ExampleFESession(mss);
	}
	private EventAPI getEventAPI()
	{
            return (EventAPI)NmsUtil.getAPI("EventAPI");//No Internationalisation
	}
	
	
	private AlertAPI getAlertAPI()
	{
            return (AlertAPI)NmsUtil.getAPI("AlertAPI");//No Internationalisation
	}
	private void getSeverityInfo()
	{
		SeverityInfo severityInfo=SeverityInfo.getInstance(); 
		while ( severityInfo == null)
		{
			try
			{
				severityInfo=SeverityInfo.getInstance(); 
				Thread.sleep(3000);
				System.err.println(NmsUtil.GetString("Getting the Severity Instance"));
			}
			catch ( Exception e)
			{
				System.err.println(NmsUtil.GetString("Exception in getting the Severity API"));
				e.printStackTrace();
			}
		}
		Vector names=severityInfo.getNames();
		if ( names != null)
		{
			for ( int i=0;i< names.size();++i)
			{
				if ( ((String)names.elementAt(i)).equalsIgnoreCase("unknown") )//No Internationalisation
					continue;
				int val=severityInfo.getValue((String)names.elementAt(i));
				Severitytable.put(new Integer(val),names.elementAt(i));
			}
			
		}
		else
		{
			System.err.println(NmsUtil.GetString("Names returned from getNames() method is null"));
			// Proceeding  with the Default values
			Severitytable.put(new Integer(1),"Critical");//No Internationalisation
			Severitytable.put(new Integer(2),"Major");//No Internationalisation
			Severitytable.put(new Integer(3),"Minor");//No Internationalisation
			Severitytable.put(new Integer(4),"Warning");//No Internationalisation
			Severitytable.put(new Integer(5),"Clear");//No Internationalisation
			Severitytable.put(new Integer(6),"Info");//No Internationalisation
			
		}
	}
}

