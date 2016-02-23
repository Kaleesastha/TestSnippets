package test;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.fe.utils.NmsFEUtil;
import com.adventnet.nms.startnms.UpdateMaintainer;
import java.util.Vector;
import java.util.Iterator;
import java.util.Properties;

public class LogClientDetails implements RunProcessInterface
{
	public void callMain(String args[])
	{
		try{
			Thread.sleep(20000);
			while(true)
			{
				System.err.println("***********************************");
				System.err.println("Client Details:::"+NmsFEUtil.getClientJVMDetails());
				System.err.println("***********************************");
				System.err.println("getSessionDetails\n"+UpdateMaintainer.getSessionDetails());
				System.err.println("***********************************");

				System.err.println("getAllClientList-->"); 
				Vector vec = UpdateMaintainer.getAllClientList(); 
				for(Iterator iter = vec.iterator(); iter.hasNext();)
				{
					Properties prop = (Properties)iter.next();
					System.err.println("props are:"+prop);
					String ID = prop.getProperty("ID");
					String IPAddress = prop.getProperty("CLIENT_IPADDRESS");
					System.err.println("ID & CLIENT_IPADDRESS are:"+ID+", "+IPAddress);//Will log IPAddress & ID for the clients
				}
				System.err.println("***********************************");
				Thread.sleep(20000);
			}
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
