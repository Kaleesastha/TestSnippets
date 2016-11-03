//$Id: ExampleBE.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.example.befe;


import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.startnms.*;
import java.util.*;
public class ExampleBE implements RunProcessInterface
{
	
	public void shutDown()
	{
		System.out.println(NmsUtil.GetString("ExampleBE shutdown called"));
	}
	public boolean isInitialized()
	{
		return true;
	}
	public void callMain(String args[])
	{
		try
		{
			ExampleBEServer obj = new ExampleBEServer();
			NmsLogMgr.MISCUSER.log(NmsUtil.GetString("ExampleBE module Initialized"),Log.SUMMARY);   
			
		}
		catch ( Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
}

