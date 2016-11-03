// $Id: ExampleBEServer.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.example.befe;
// NMS imports
import com.adventnet.nms.startnms.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.severity.*;
//Java imports
import java.util.*;


/*  A seperate SessionBE is created for each FE ,connecting to the ServerBE.
    This class is  get notified( if this class is registered with the MainSocketServerBE,
     by calling registerForResponses() ) through its init(MainSocketSessionBE mss),
	whenever a FE connects to the BE,if it is registered 
	with the MainSocketServerBE. 
*/

public class ExampleBEServer implements SocketServerConnectionBE
{
	// Holds the EventAPI instance
	static EventAPI eventAPI;
	// Holds the AlertAPI instance
	static AlertAPI alertAPI;
	// Holds the Severity Information
	static Hashtable Severitytable= new Hashtable(10);
	
	public ExampleBEServer()
	{
		PureServerUtilsBE.serverSocketBE.registerForResponses(this);
	}
	//Called by the MainSocketServerBE,for each BE Session created
	public void init(MainSocketSessionBE mss)
	{
		
		eventAPI=getEventAPI();
		alertAPI=getAlertAPI();
		getSeverityInfo();
		ExampleBESession ebs = new ExampleBESession(mss);		
	}
	// To get the EventAPI instance
	public  EventAPI getEventAPI()
	{
		
		return (EventAPI)NmsUtil.getEventAPI();
	}
	
	// To get the AlertAPI instance
	public  AlertAPI getAlertAPI()
	{
		return (AlertAPI)NmsUtil.getAlertAPI();
		
	}
	// To get the Severity Information. The Info is stored in the Hashtable.
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
			//Proceeding with the Default values
			Severitytable.put(new Integer(1),"Critical");//No Internationalisation
			Severitytable.put(new Integer(2),"Major");//No Internationalisation
			Severitytable.put(new Integer(3),"Minor");//No Internationalisation
			Severitytable.put(new Integer(4),"Warning");//No Internationalisation
			Severitytable.put(new Integer(5),"Clear");//No Internationalisation
			Severitytable.put(new Integer(6),"Info");//No Internationalisation
		}
	}

}

