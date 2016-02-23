/*
   $Id: ObjectScheduler.java,v 1.8 2001/06/26 10:48:27 srini Exp $
 */

/**
 * ObjectScheduler.java
 */

package com.adventnet.nms.poll;

import com.adventnet.management.log.*;
import java.util.Vector;

import com.adventnet.nms.util.NmsLogMgr;

import com.adventnet.nms.topodb.ManagedObject;

import com.adventnet.management.log.Log;
import com.adventnet.nms.util.NmsUtil;

class ObjectScheduler implements WebNMSRunnable 
{

	// New Variables declared
	static boolean firstTime=true;
	static int time = 0;
	static int periodiccount = 0;
	static long difference = 0;
	static long counter=0;
	public static boolean inloop=false;
	static long startTime=0;
	static long periodicstarttime=0;
	public static LogUser testuser = LogMgr.getLogUser("STAPOLL");

	private String name;

	ObjectScheduler()
	{
	}

	ObjectScheduler(String objName) 
	{
		if(scheduler == null)
			initializeScheduler();
		name = objName;
	}

	public void run() 
	{
		if(firstTime)
		{
			startTime=System.currentTimeMillis();
			periodicstarttime=startTime;
			firstTime = false;
		}

		// Counters incrementation and time calculation.
		counter++;
		periodiccount++;
		long currenttime=System.currentTimeMillis();
		difference = (currenttime-periodicstarttime);

		// Rate Calcualted for every 30 secs
		if (difference >=30000 && inloop == false) {
			inloop=true;
			time+=30;
			testuser.log("StatusPolling Rate for last 30 secs : "+ ((float)periodiccount*1000/difference),3);
			periodicstarttime = System.currentTimeMillis();
			periodiccount=0;
			inloop=false;
		}

		// Rate Calcualted for every 1000 StatusPolls
		if(counter == 1000)
		{
			long difftime=System.currentTimeMillis() - startTime;
			testuser.log("StatusPolling Rate:" + (1000000/difftime),3);
			startTime=System.currentTimeMillis();
			counter=0;
		}

		ManagedObject obj = null;
		try 
		{
			obj = PollUtil.getPollToTopoIfc().getManagedObject(name);

			if(obj == null) 
			{
				unSchedule();
				return;
			}

			if((!obj.getManaged()) || (!obj.getStatusPollEnabled()) || (obj.getPollInterval() <= 0))
			{ 
				//TODO currently when we startup we just walk thru
				// DBOBjects and then scheduler all guys  for status pollinng.
				// Even guys whou are unmanaged. This is bcos at that point we
				// have only names and no objects. So we just go and schedule them
				// So when u manage a unmanged guys it throws an exception that 
				// entry is already present. So let us remove ourselves from
				// being scheduled.  But problem is between the time nms started
				// and it gets unscheduled if an objet is manged we have an exception
				unSchedule();
				return;
			}

			obj.pollStatus();

		}
		catch(Exception e) {
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception while getting obj during status update ") + e + " " + name , e);//No Internationalisation
		}
		scheduler.updateTask(name,System.currentTimeMillis() + (obj.getPollInterval() * 1000));
	}

	// This will be in milli seconds.
	void scheduleInMillis(int interval) 
	{
		scheduler.scheduleTask(name,System.currentTimeMillis() + interval);
	}

	void unSchedule() 
	{
		scheduler.removeTask(name);
	}

	static com.adventnet.nms.poll.NameScheduler scheduler;

	static void initializeScheduler()
	{
		scheduler = com.adventnet.nms.poll.NameScheduler.createScheduler("statuspoll","com.adventnet.nms.poll.ObjectScheduler");//No Internationalisation

		// start is done separately to ensure that we start the scheduler
		// only after all guys have been scheduled during restart	
	}

	static void startScheduler()
	{
		scheduler.start();
	}

	public void initialize(String name)
	{
		this.name = name;
		return;
	}

	public void initialize(Vector name)
	{
		return;
	}
}





