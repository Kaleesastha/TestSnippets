package test;

import com.adventnet.nms.util.*;
public class UsersAndMOCount implements RunProcessInterface
{
	public void callMain(String args[])
	{
		try{
			Runnable task=new TestRun();
			System.out.println("This part does the work");
			NmsUtil.scheduler.scheduleTask(task,System.currentTimeMillis()+(1000*60));//the exact date of execution can also be given here instead of this long
		}
		catch(Exception e)
		{
			System.out.println("this test is Fail");
			e.printStackTrace();
		}
	}
	public boolean isInitialized()
	{
		return true;
	}
	public void shutDown()
	{

	}

}
