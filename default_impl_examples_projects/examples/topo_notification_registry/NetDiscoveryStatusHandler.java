//$Id: NetDiscoveryStatusHandler.java,v 1.2 2008/09/25 13:44:54 tinku Exp $
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.util.NmsUtil.*;
import com.adventnet.nms.topodb.TopoNotificationRegistry;
import com.adventnet.nms.topodb.TopoNotificationFilter;
import com.adventnet.nms.topodb.TopoNotificationRegistryIfc;
import com.adventnet.nms.util.PureServerUtils;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.util.*;
import java.io.Serializable;
public class NetDiscoveryStatusHandler  implements RunProcessInterface,Serializable
{
	public static TopoNotificationRegistryIfc toporeg=null;

	public static TopoAPI topo=null;
	public static boolean initialised=false;
	public static String arg1;

	public NetDiscoveryStatusHandler()
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
		if(arg.length < 1)
		{
			System.out.println("USAGE :java NetDiscoveryStatusHandler hostname");
			System.exit(0);
		}
		arg1=arg[0];
		NetDiscoveryStatusHandler statushan=new NetDiscoveryStatusHandler();
	}
	public void callMain(String a[])

	{
		initialised=true;
	}
	public void registerNotnFilter() throws java.rmi.RemoteException
	{
		try
		{
			String file = PureUtils.rootDir +"examples/topo_notification_registry/FilterXml.conf";
			XMLDataReader xmlReader=new XMLDataReader(file,false);
			XMLNode rootnode=xmlReader.getRootNode();
			
				
			TopoNotificationHandler apifc=(TopoNotificationHandler)Class.forName("test.NetDiscoveryStatusListener").newInstance();
			
			 UnicastRemoteObject.exportObject(apifc,0);

			TopoNotificationFilter notfilter=new TopoNotificationFilter(rootnode,apifc);
			if(toporeg==null)//Registering through TopoAPI
			{
				topo=(TopoAPI)Naming.lookup("rmi://"+arg1+"/TopoAPI");
				topo.registerNotificationFilter(notfilter);
			}
			else 

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




