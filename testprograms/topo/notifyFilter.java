import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.util.NmsUtil.*;
import com.adventnet.nms.topodb.TopoNotificationRegistry;
import com.adventnet.nms.topodb.TopoNotificationFilter;
import com.adventnet.nms.topodb.TopoNotificationRegistryIfc;
import com.adventnet.nms.util.PureServerUtils;

import java.rmi.*;
import java.util.*;
import java.io.Serializable;
public class notifyFilter implements RunProcessInterface,Serializable
{
	public static TopoNotificationRegistryIfc toporeg=null;

	public static TopoAPI topo=null;
	public static boolean initialised=false;
	public static String arg1;

	public notifyFilter()
	{
		try
		{
			if (!PureServerUtils.smallNet)//Different JVM
			{
				try
				{
					toporeg=(TopoNotificationRegistryIfc)NmsUtil.lookupObjectInRegistry(arg1,"TopoNotificationRegistry");
				}
				catch(Exception e)
				{
				}

				registerNotnFilter();

			}

			else//Same JVM
			{
				try
				{
					while(true)
					{
						toporeg=(TopoNotificationRegistryIfc)NmsUtil.getAPI("TopoNotificationRegistry");
						if(toporeg==null){
							Thread.sleep(1000);
						}
						else break;
					}
					registerNotnFilter();
				}

				catch(Exception e){
				}
			}


		}
		catch(Exception e){}

	}	


	public static void main(String arg[])
	{
		arg1=arg[0];
		notifyFilter statushan=new notifyFilter();
	}
	public void callMain(String a[])
	{
		initialised=true;
	}
	public void registerNotnFilter() throws java.rmi.RemoteException
	{
		try
		{
			String file = PureUtils.rootDir +"conf/filterXml.conf";
			XMLDataReader xmlReader=new XMLDataReader(file,false);
			XMLNode rootnode=xmlReader.getRootNode();

			TopoNotificationHandler apifc=(TopoNotificationHandler)Class.forName("test.userTest").newInstance();

			TopoNotificationFilter notfilter=new TopoNotificationFilter(rootnode,apifc);
			toporeg.registerNotificationFilter(notfilter);

		}
		catch(Exception e)
		{
			System.out.println("The Exception caught is"+e);
		}
	}

	public boolean isInitialized()
	{
		return initialised;
	}
	public void shutDown()
	{
	}
}// 




