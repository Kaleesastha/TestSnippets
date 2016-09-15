package com.adventnet.nms.poll;

/* $Id: ObserverTest1.java,v 1.1 2001/09/11 06:53:19 sudhal Exp $ */

/**
 * PollingObject.java
 */
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.poll.PollObserver;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.poll.CollectedInfo;
import java.util.Properties;
import java.util.Vector;
//import java.rmi.Naming;
//import java.rmi.*;

public class ObserverTest1 implements  PollObserver{

    PollAPI api = null;
static int i=0;
    private void init()
    {
        try{
        api = (PollAPI)NmsUtil.getPollAPI();
        }
        catch(Exception e)
        {
            System.out.println("Exception :"+e);
            System.exit(1);
        }
        System.out.println("handle obtained");
    }

    private void registerObserver()
    {
        try{

            java.rmi.server.UnicastRemoteObject.exportObject(this);
            //api.registerForData("INTERFACE_out_octets"+"\t"+"huprema.india.adventnet.com"+"\t"+"2.2.1.16.2", this);
            //api.registerForData("INTERFACE_out_octets"+"\t"+"nmagesh"+"\t"+"2.2.1.16.2", this);
        //api.registerForData("INTERFACE_out_octets"+"\t"+"huprema.india.adventnet.com"+"\t"+"2.2.1.16.2", this);        
        api.registerForDataFromAgent("romi",this);        

		System.out.println(" Observer registered");        
        }
        catch(Exception e)
        {
            System.out.println("Error in registering:"+e);
        }

    }
int count=0;
    public void dataUpdate(CollectedInfo colinfo) 
	{
		 count=i++;
		 System.out.println("The count is "+count);
        System.out.println(" dataUpdate of CollectedInfo called");
		if(count > 5)
		{
			try
			{
			api.deregisterForDataFromAgent("romi",this);
			System.out.println("deregistering");
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}
    }

    public void dataUpdate(String pollKey , long time , long value)
    {
        System.out.println("\n dataUpdate for long called");
        System.out.println("Key:"+pollKey);
        System.out.println("time:"+time);
        System.out.println("value:"+value);
    }

    public void dataUpdate(String pollKey, long time , String value)
    {
        System.out.println("\n dataUpdate for string  called");
        System.out.println("Key:"+pollKey);
        System.out.println("time:"+time);
        System.out.println("value:"+value);        
    }

    public static void main(String [] args)
    {
        ObserverTest1 t=new ObserverTest1();
        t.init();
        t.registerObserver();
        PollMgr mgr=Collector.pollmgr;
        if(mgr!=null)
        System.out.println(" PollMgr instance obtained:"+mgr);
        else
            System.out.println(" PollMgr instance is null");
    }

    
    
}












