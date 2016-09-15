/**
 * This example is for Scheduling the report generation as per 
 * the user requirements. By using this example the user can
 * change the report generation interval as per his needs.
 * In this example report generation is configured for 20 seconds.
 */

import com.adventnet.management.scheduler.Scheduler;
import com.adventnet.nms.poll.*;
import java.rmi.*;
import java.util.*;

public class exampleScheduleReport
{
    
    long plusTime = 20000;//Time interval for report generation
    String name = null;
    Scheduler s = null;
    Reporter report=null;
    PollAPI api=null;

	// The following constructor is used to get the Scheduler
    // instance.

    public exampleScheduleReport() {}
    
    public void test1(String name)
    {
        //long when = System.currentTimeMillis() + plusTime;
        long time=System.currentTimeMillis();
        exampleRunnable q1=new exampleRunnable(1,this);
    	setWhen(q1,time+1000,1);

        try{
        Thread.sleep(10000);
        }
        catch(Exception e1)
        {}
        
        System.out.println(" about to stop scheduler ..");
        s.suspendAll();
        try{
        Thread.sleep(10000);
        }
        catch(Exception e1)
        {}
        System.out.println(" resuming scheduling ..");
        s.resumeAll();
        setWhen(q1,System.currentTimeMillis()+2000,1);
        //setWhen(new exampleRunnable(2),time+40000,2);
        //setWhen(new exampleRunnable(3), time,3);
    }
    
    
    public  void setWhen(Runnable r, long time,int count)
    {
        System.out.println(" scheduling task "+count+" at "+new Date(time));
        s.scheduleTask(r, time);
		//s.removeTask(r);
		s.cleanUp();
    }

    public void stopScheduler(Runnable r)
    {
	    System.out.println(" about to stop scheduler ..");
        s.stopAll();
     
	    //s.stopThis();
        try{
        Thread.sleep(10000);
        }
        catch(Exception e1)
        {}
        s.resumeAll();
        setWhen(r,System.currentTimeMillis(),1); 
    }
    
    public static void main(String args[])
    {
       exampleScheduleReport e=new exampleScheduleReport();
       e.schedulerStart();
       e.test1("sdd");
    }

    public void  schedulerStart()
    {
		Scheduler sch = Scheduler.createScheduler("One",1);
		//Scheduler is started.
		sch.start();
    }
}
