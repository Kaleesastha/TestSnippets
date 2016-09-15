/* $Id: testScheduler.java,v 1.2 2001/08/04 14:18:12 subramani Exp $ */

import com.adventnet.management.scheduler.*;

public class testScheduler implements Runnable
{

	long setTime;

	long plusTime = 500;
	String name = null;

	long graceTime = 30;

   Scheduler s = null;


	public testScheduler(Scheduler sArg,String nam)
	{
		s = sArg;
		name = nam;
	}

	public void test1()
	{
		long when = System.currentTimeMillis() + plusTime;

		setWhen(when);

		//threadChecker t = new threadChecker(s);
		//s.scheduleTask(t, System.currentTimeMillis() + 200);

	}



	public void run()
	{
		long now = System.currentTimeMillis();
		long diff = now - setTime; 

		long t = now + plusTime;	
		
		setWhen(t);

                System.out.println("Run method called");
		if(diff > graceTime)
		{
			System.out.println(name + ": " + this.getClass() + " diff = " + diff + " more than " + graceTime + " (Time = " + t + ")");
		}

	}

	private void setWhen(long when)
	{
		setTime = when;
		s.scheduleTask(this, when);
	}

	public static void main(String args[])
	{
		Scheduler sch = Scheduler.createScheduler("One",4);
		sch.start();

		for(int i = 0; i < 100; i++)
		{
			testScheduler t1 = new testScheduler(sch,"One");
			t1.test1();
		}

		while(true)
		{
			try
			{
				Thread.sleep(10000);

                                System.out.println("All schedulers will be suspended ");
                                Scheduler.suspendAll();
                                System.out.println("All schedulers suspended successfully. Should not get any more prints for next 10 seconds");
                                Thread.sleep(10000);
                                System.out.println("going to resume all schedulers ");
                                Scheduler.resumeAll();
                                
			}
			catch(Exception ex)
			{
			}
		}
	}

//	class threadChecker implements Runnable
//	{
//		Scheduler shRef = null;
//
//		public threadChecker(Scheduler sh)
//		{
//			shRef = sh;
//		}
//
//		public void run()
//		{
//			System.out.println("Num Threads = " + shRef.getNumThreads());
//			System.out.println("vec= " + shRef.getNumThreads());
//		}
//	}
}
