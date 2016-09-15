package com.adventnet.nms.util;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.rmi.RMIUtil;
import com.adventnet.nms.eventdb.*;
import com.adventnet.management.log.Log;
import java.util.*;


public class SystemInfoWithDelete implements RunProcessInterface 
{
	public SystemInfoWithDelete()
	{            
	}

	static boolean initialized = false; 
	private boolean isShutDown = false;
	private static Integer integer = new Integer(0);
    private long lastPurgeTime = System.currentTimeMillis();

	public void startLogging()
	{	
		Runtime runtime = Runtime.getRuntime();
		long totalMemory = 0;
		long freeMemory = 0;
		String fileName = PureUtils.rootDir+ "/MemoryUsage.txt";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		while(!isShutDown)
		{
			totalMemory = runtime.totalMemory();
			freeMemory = runtime.freeMemory();
			try
			{
				fos = new FileOutputStream(fileName,true);
				fos.write("At : ".getBytes());
				fos.write(String.valueOf(new Date(System.currentTimeMillis())).getBytes());
				fos.write(", FreeMemory:".getBytes());
				fos.write(String.valueOf(freeMemory).getBytes());
				fos.write(", TotalMemory:".getBytes());
				fos.write(String.valueOf(totalMemory).getBytes());
				fos.write("\n".getBytes());
				fos.flush();
				fos.close();
			}
			catch(FileNotFoundException fne)
			{
				fne.printStackTrace();
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
			try
			{
				synchronized(integer)
				{
					integer.wait(300000);
					//integer.wait(2000);
				}
			}
			catch(Exception ie)
			{
				ie.printStackTrace();
			}

            purgeEvent();
		}
	}

    private void purgeEvent()
    {
        try
        {
            long diff = System.currentTimeMillis()-lastPurgeTime;
            if(diff > 2*7200000)
            {
                EventAPI api = (EventAPI)NmsUtil.getAPI("EventAPI");
                api.purgeEventDB();
                lastPurgeTime=System.currentTimeMillis();
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }
    
	public boolean isInitialized()
	{
		return initialized;
	}

	public void callMain(String argv[]) 
	{
		NmsLogMgr.MISCUSER.log("Main called : SystemInfo",Log.SUMMARY);
		initialized = true;
		NmsLogMgr.MISCUSER.log("Module Initialized : SystemInfo",Log.SUMMARY);
		startLogging();
	}

	public static void main(String s[])
	{
		new SystemInfoWithDelete().startLogging();
	}

	public void shutDown()
	{
		NmsLogMgr.MISCUSER.log(" SystemInfo - Shut Down invoked. ",Log.SUMMARY);
		isShutDown = true;
		synchronized(integer)
		{
			try
			{
				integer.notifyAll();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}
	}
}
