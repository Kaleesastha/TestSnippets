package test;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.TopoNotificationHandler;
import java.util.*;
import java.rmi.*;
import java.net.*;
import java.io.Serializable;

public class NetDiscoveryStatusListener implements TopoNotificationHandler,Serializable
{
	public static TopoAPI topo=null;
	public static Hashtable networkStatus=new Hashtable();
	String prevStatus=null;
	String name=null;
	String currentStatus=null;

	public NetDiscoveryStatusListener()
	{
	}
	public static void main(String a[])
	{
	}
	public boolean handleNotification(Properties[] p) throws RemoteException
	{
		for(Enumeration en=p[0].propertyNames();en.hasMoreElements();)
		{
			String key=(String)en.nextElement();
			if(key.equals("name"))
			{
				name=(String)p[0].get(key);
			}
			else if(key.equals("discoveryStatus"))
			{
				currentStatus=(String)p[0].get(key);	
			}
		}


		if(!(networkStatus.containsKey(name)))
		{
			networkStatus.put(name,currentStatus);
		}
		if((!((String)networkStatus.get(name)).equals(currentStatus))&&(currentStatus.equals("2"))) 

		{
			System.out.println("Discovery for the Network  "+name+"is started");
			networkStatus.put(name,"2");
		}
		else if((((String)networkStatus.get(name)).equals("2"))&&(currentStatus.equals("3")))

		{
			System.out.println("Discovery for the Network  "+name+"is							completed");
			networkStatus.put(name,"3");
		}

		return true;

	}

}








