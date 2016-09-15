/**
 * This example is for Scheduling the report generation as per 
 * the user requirements. By using this example the user can
 * change the report generation interval as per his needs.
 * In this example report generation is configured for 20 seconds.
 */

import com.adventnet.management.scheduler.Scheduler;
import com.adventnet.nms.poll.*;
import java.rmi.*;
import java.io.*;
import java.util.Properties;
import java.util.Vector;
import java.util.Date;
import java.util.Enumeration;
import java.lang.Object;
import java.sql.Connection;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.store.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.management.transaction.*;

public class exampleRunnable implements Runnable
{
	private int count=0;
    exampleScheduleReport ch=null;
    private int no=0;
    
    public exampleRunnable(int count,exampleScheduleReport sch)
    {
        this.count=count;
        ch=sch;
    }
   
    public void run()
    {
	    //runReport method is called here to generate  the report
        no++;
        System.out.println(" in run method "+count+ "at  "+new Date());
        long time=System.currentTimeMillis()+4000;
        ch.setWhen(this,time,count);
    }
}













