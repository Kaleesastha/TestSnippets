import java.io.*
import java.util.*;

public class StartStopNMS
{
	public static void main(String args[])
	{
		for(int i=1;i<=1;i++)
		{
			try
			{
				String startCommand = "C:"+File.separator+"WebNMS"+File.separator+"Occam_SP3"+File.separator+"bin"+"startnms.bat";
				Sting stopCommand = "C:"+File.separator+"WebNMS"+File.separator+"Occam_SP3"+File.separator+"bin"+"ShutDown.bat root public";
				Runtime.getRuntime().exec(startCommand);
				Thread.sleep(75000);
				Runtime.getRuntime().exec(stopCommand);
				Thread.sleep(40000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
