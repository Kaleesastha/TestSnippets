package com.adventnet.nms.test;

import com.adventnet.nms.rmi.RMIUtil;
import com.adventnet.nms.util.*;

public class RemoteTest implements RunProcessInterface
{
	public void callMain(String args[])
	{
		System.err.println("Call main of TestInvoker called  ");
		TestInvokerImpl.getInstance();
		TestInvoker test = null;
		try{Thread.sleep(5000);} catch(Exception exp){}
		while (test ==null){
			test = (TestInvoker)NmsUtil.getAPI("TestInvoker");
			System.err.println(" Instance is  :"+test);
			try{Thread.sleep(2000);} catch(Exception exp){}
		}
	}

    public boolean isInitialized()
    {
        return true;
    }

    public void shutDown()
    {
        System.out.println("Shutdown of Remote Test called ");
        try
        {
            RMIUtil.unbindObjectFromRegistry("TestInvoker");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
