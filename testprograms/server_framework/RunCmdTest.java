import com.adventnet.nms.util.*;
import com.adventnet.nms.startnms.*;

class RunCmdTest
{
	public static void main(String[] args)
	{
		String command;

		//Case 3
		//Call a sample test program having System Out and System error message in it.
		RunCmd rc = new RunCmd("java aa",true);
		rc.start();


		//Case 1

		/*if(System.getProperty("os.name").indexOf("Win") >= 0)
	   {
			command = "notepad";
		}
		else
		{
			command = "netscape";
		}
		RunCmd rc = new RunCmd(command,true);

		new StopThread(rc);
		rc.runCommand("notepad"); */

		
	}
	
	
}
/*class StopThread extends Thread
{
	RunCmd rc;
	public StopThread(RunCmd rc)
	{
		System.out.println(" Inside StopThread");
		this.rc = rc;
		start();
	}
	public void run()
	{
		try
		{
			
			Thread.sleep(5000);
			rc.stopCommand();
			System.out.println(" Thread quitting");
		}catch	(Exception e)
		{
			e.printStackTrace();
		}
				 
	}
	
}*/
