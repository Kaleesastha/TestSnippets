//$Id: TestProcess.java,v 1.2 2002/02/15 13:17:26 karthicks Exp $
 package com.adventnet.nms.simulation ; 


import java.util.*;
import java.rmi.RemoteException;

import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;
import com.adventnet.nms.util.XMLNode;

import com.adventnet.nms.eventdb.EventAPI;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.TrapObserver;
import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.nms.eventdb.EventObserver;

import com.adventnet.nms.alertdb.AlertListener;
import com.adventnet.nms.alertdb.AlertActionInformer;
import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.alertdb.Alert;



public class TestProcess implements RunProcessInterface, EventObserver, AlertListener,TrapObserver
{
    private boolean initialized = false;
    
    // API's to register and used for notification.
    private EventAPI eAPI = null;
    private AlertAPI aAPI = null;
    
    // to keep track of Event counts
    private long eventCount=1;
    private long trapCount=1;
    private double tempInt1=0;
    private double tempInt2=0;
    private double tempInt3=0;

    // to keep tract of Alert counts
    private long alertCount=1,alertCount1=1,oldAlertCount=1,oldtrapCount=1,oldAlertCount1=1,oldeventCount=1;
    
    // initialization parameter to determine for how many count the perf needs to be logged.
    private long eventDiffCount=500,alertDiffCount=500,trapDiffCount=500;


    // to keep track when event / Alert are last logged.
    private long tempEventTime,tempAlertTime,tempTrapTime,tempTrapTime1,tempDifftime,tempAlertTime1,tempEventTime1;

   

    private void init (String[] args)
	{
   		// do process specific initialization
		for(int i=0;i<args.length;i++)
		{
			try
			{
				if(args[i].equalsIgnoreCase("EVENT_COUNT"))
				{
					eventDiffCount = Long.parseLong(args[++i]);
					continue;
				}
				if(args[i].equalsIgnoreCase("ALERT_COUNT"))
				{
					alertDiffCount = Long.parseLong(args[++i]);
					continue;
				}
                if(args[i].equalsIgnoreCase("TRAP_COUNT"))
				{
					trapDiffCount = Long.parseLong(args[++i]);
					continue;
				}
			}
			catch(NumberFormatException nume)
			{
				NmsLogMgr.EVENTERR.fail("----------->Invalid entry for EVENT_COUNT or ALERT_COUNT or TRAP_COUNT .  Please recheck.  Till then perf will be logged for every 500 counts",null);
			}
		}

	}

	private void proc()
	{
		// will try to get the EventAPI handle & AlertAPI handle and wait if EventAPI not binded.  This could be changed
		// not to wait infinite time.
		while(eAPI == null || aAPI == null)
		{
			getAPIHandle();
			try
			{
				Thread.sleep(2000);
			}
			catch(InterruptedException inte)
			{
			}
		}
		NmsLogMgr.EVENTUSER.log("--------->EventAPI and AlertAPI handles obtained . ",Log.SUMMARY);
		initialized = true;
		registerForNotifications();
	}


	/*  Helper method to register as TrapObserver.
	 */
	private void registerForNotifications()
	{
		try
		{
			eAPI.registerForEvents(this);
			aAPI.addAlertListener(this);
            eAPI.register(this);
			NmsLogMgr.EVENTUSER.log("------------->Registered as Event,Trap and Alert Observer",Log.SUMMARY);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}


	/*  Helper method to obtain EventAPI handle.
	 */
	private void getAPIHandle()
	{
		if(eAPI == null)
		{
			eAPI = (EventAPI) NmsUtil.getAPI("EventAPI");
		}
		if(aAPI == null)
		{
			aAPI = (AlertAPI) NmsUtil.getAPI("AlertAPI");
		}
	}

	/*  This method should return 'true' after initialization of the process.
	 */
	public boolean isInitialized ()
	{
		return initialized;
	}


	/*  To handle shut Down.
	 */
	public void shutDown ()
	{
		// handle shutdown for the process

	}

    public void update(SnmpPDU pdu )
	{
		if(trapCount == 1)
		{
			tempTrapTime = System.currentTimeMillis();
			tempTrapTime1 = System.currentTimeMillis();
		}
		if((trapCount % trapDiffCount) == 0)
		{
			printTrapRate();
		}
		tempDifftime = System.currentTimeMillis();
		if((tempDifftime - tempTrapTime1) >=30000)
		{
			printTrapRate1();
		}
		trapCount++;
	}
    
	public void update(Event event)
	{
		if(eventCount == 1)
		{
			tempEventTime = System.currentTimeMillis();
			tempEventTime1 = System.currentTimeMillis();
		}
		if((eventCount % eventDiffCount) == 0)
		{
			printEventRate();
		}
		tempDifftime = System.currentTimeMillis();
		if((tempDifftime - tempEventTime1) >=30000)
		{
			printEventRate1();
		}
		eventCount++;
	}

	public void update(XMLNode xmlnode)
	{
		try
		{
			NmsLogMgr.EVENTUSER.log("------------>Alert update notified with XML Node.",Log.SUMMARY);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void listenAlert(AlertActionInformer action) 
	{
		if(alertCount == 1)
		{
			tempAlertTime = System.currentTimeMillis();
			tempAlertTime1 = System.currentTimeMillis();
		}
		if(action.isBatchUpdate())
		{
			Vector v = action.getAlertList();
			if(v.size() > 0)
			{
				alertCount += v.size();
				//alertCount1 += v.size();
			}	
		}
		else
		{
			Alert a = action.getAlert();
			alertCount += 1;
			//alertCount1 += 1;
		}
		if((alertCount - oldAlertCount) >= alertDiffCount)
		{
			printAlertRate();
		}
		tempDifftime = System.currentTimeMillis();
		if((tempDifftime - tempAlertTime1) >=30000)
		{
			printAlertRate1();
		}
	}


    private void printTrapRate()
	{
		double tempInt = ((System.currentTimeMillis() - tempTrapTime) / 1000d);
        
		NmsLogMgr.EVENTUSER.log("--------->Timetakentoprocesslast"+ trapDiffCount +"Traps " + tempInt + " secs.  Processing rate : " + (trapDiffCount / tempInt)+ " per sec",Log.SUMMARY);
		tempTrapTime = System.currentTimeMillis();
	}
	private void printTrapRate1()
	{
		double tempInt = ((System.currentTimeMillis() - tempTrapTime1) / 1000d);
        
		NmsLogMgr.EVENTUSER.log("--------->Trapsprocessedin30secs " + (trapCount - oldtrapCount) + " Traps " + tempInt + " secs.  Processing rate : " + ((trapCount - oldtrapCount) / tempInt)+ " per sec",Log.SUMMARY);
		tempInt1 = tempInt1 + tempInt;
		NmsLogMgr.EVENTUSER.log("--------->Trapsprocessedinnext30secs " + trapCount + " Traps " + tempInt1 + " secs.  Processing rate : " + (trapCount  / tempInt1)+ " per sec",Log.SUMMARY);
		oldtrapCount = trapCount;
		tempTrapTime1 = System.currentTimeMillis();
	}

	private void printEventRate()
	{
		double tempInt = ((System.currentTimeMillis() - tempEventTime) / 1000d);
        
		NmsLogMgr.EVENTUSER.log("--------->Timetakentoprocesslast"+ eventDiffCount +"events " + 
                                tempInt + " secs.  Processing rate : " + (eventDiffCount / tempInt)+ " per sec",Log.SUMMARY);
		tempEventTime = System.currentTimeMillis();
	}

	private void printEventRate1()
	{
		double tempInt = ((System.currentTimeMillis() - tempEventTime1) / 1000d);
        
		NmsLogMgr.EVENTUSER.log("--------->Eventsprocessedin30secs " + (eventCount - oldeventCount) + " events " +  
                                tempInt + " secs.  Processing rate : " + ((eventCount - oldeventCount) / tempInt)+ " per sec",Log.SUMMARY);
		tempInt2 = tempInt2 + tempInt;
		NmsLogMgr.EVENTUSER.log("--------->Eventsprocessedinnext30secs " + eventCount + " events " + tempInt2 + " secs.  Processing rate : " + (eventCount / tempInt2)+ " per sec",Log.SUMMARY);

		oldeventCount = eventCount;
		tempEventTime1 = System.currentTimeMillis();
	}

	private void printAlertRate()
	{
	    double tempInt =  ((System.currentTimeMillis() - tempAlertTime) / 1000d);
	    NmsLogMgr.ALERTERR.fail("----------->Timetakentoprocesslast"+ (alertCount - oldAlertCount) +"Alerts " + tempInt + " secs.  Processing rate : " + ((alertCount - oldAlertCount) / tempInt) + " per sec ",null);
	    oldAlertCount=alertCount;
	    tempAlertTime = System.currentTimeMillis();
	}
	private void printAlertRate1()
	{
	    double tempInt =  ((System.currentTimeMillis() - tempAlertTime1) / 1000d);
	    NmsLogMgr.ALERTERR.fail("----------->Alertsprocessedin30secs " + (alertCount - oldAlertCount1) + " Alerts " + tempInt + " secs.  Processing rate : " + ((alertCount - oldAlertCount1) / tempInt) + " per sec ",null);
		tempInt3 = tempInt3 + tempInt;
	    NmsLogMgr.ALERTERR.fail("----------->Alertsprocessedinnext30secs " + alertCount + " Alerts " + tempInt3 + " secs.  Processing rate : " + (alertCount / tempInt3) + " per sec ",null);
	    oldAlertCount1=alertCount;
	    tempAlertTime1 = System.currentTimeMillis();
	}


	/*  This method will be called during start up of NMS server.
	 */
	public void callMain (String[] args)
	{
		// This method is called by the NMS server during startup.
		init(args);
		proc(); 
	}
} // End of class TestProcess
