package test;

import com.adventnet.nms.util.*;
public class TestProcess2 implements RunProcessInterface
{
	public void callMain(String args[])
	{
		try{
			Runnable task=new TestRun();
			System.out.println("REMUS: this part does the work");
			NmsUtil.scheduler.scheduleTask(task,System.currentTimeMillis()+(1000*60*2));//the exact date of execution can also be given here instead of this long
		}
		catch(Exception e)
		{
			System.out.println("REMUS: this test is Fail");
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
