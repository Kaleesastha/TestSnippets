import java.text.*;
import java.util.*;
import java.rmi.Naming;
import java.io.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.topodb.ManagedObject;
import java.util.Vector;
import java.rmi.server.RMISocketFactory;
//import test.RMISSLServerSocketFactory;

import com.adventnet.nms.poll.*;
import com.adventnet.nms.poll.PolledData;

public class Hello
{
	/*public static void main(String[] args) throws Exception
	{
		Properties prop = new Properties();
		prop.setProperty("abc","NULL");
		String propValue = prop.getProperty("abc");
		if(propValue != null)
		{
			System.out.println("Null Check passes!"+prop.getProperty("abc"));
		}
		if(propValue == "NULL")
		{
			System.out.println("NULL Check passes!"+prop.getProperty("abc"));
		}
	}*/
	public static void main(String[] args) throws Exception
	{
		try{
			ManagedObject mo_Node=new ManagedObject();
			//RMISocketFactory sf = new RMISSLServerSocketFactory();
			//RMISocketFactory.setSocketFactory(sf);
			TopoAPI topocs = (TopoAPI)Naming.lookup("//"+args[0]+":"+args[1]+"/TopoAPI");

			String NodeName = "Node_2"+args[0];
			mo_Node.setName(NodeName);
		Properties prop = new Properties();
		//prop.setProperty("GROUPIDS","NULL");
		prop.put("groupIDs","xxxx");
		mo_Node.setProperties(prop);
		/*String	hostName = args[0];
		String	portNumber = args[1];
			PollAPI pollapi = (PollAPI)Naming.lookup("rmi://" + hostName + ":"+ portNumber +"/PollAPI");
			PolledData pd = new PolledData();
			String oid = ".1.3.6.1.4.1.42.2.145.3";
			String Name = "ClientJVM";
			String agent = args[2];
			pd.setName(Name);
			pd.setAgent(agent);
			pd.setOid(oid);
			if(pollapi.getPolledData("ClientJVM\t"+args[2].toUpperCase()+"\t.1.3.6.1.4.1.42.2.145.3") == null)
			{
				System.err.println(args[2]+"is null!");
				pollapi.addPoll(pd);
			}
				//pollapi.addPoll(pd);*/
		System.err.println("Add Object result is::"+topocs.addObject(mo_Node));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
