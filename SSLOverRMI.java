import java.text.*;
import java.util.*;
import java.rmi.Naming;
import java.io.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.topodb.ManagedObject;
import java.util.Vector;
import java.rmi.server.RMISocketFactory;
import test.RMISSLServerSocketFactory;
import com.adventnet.security.authentication.RMIAccessAPI;
public class SSLOverRMI
{
	public static void main(String[] args) throws Exception
	{
		TopoAPI topocs = null;
		System.out.println("main: started");
		String hostName = "localost";
		if(args[0] != null && !args[0].equals(""))
		{hostName = args[0];}
		String port = "1099";
		if(args[1] != null && !args[1].equals(""))
		{port = args[1];}
		String urlString = "//"+hostName+":"+port;

		String trustStore = System.getProperty("javax.net.ssl.trustStore");
		if(trustStore != null && !trustStore.equals(""))
		{
			try
			{
				RMISocketFactory sf = new RMISSLServerSocketFactory();
				RMISocketFactory.setSocketFactory(sf);
			}
			catch (Exception re)
			{
				System.out.println ( "Error in setting SocketFactory");
				re.printStackTrace();
			}
		}
		try
		{
			String[] n = Naming.list(urlString);
			for (int i=0; i<n.length; i++) System.out.println(n[i]);
		}
		catch (Exception re)
		{
			System.out.println ( "Error in getting the handle: " + re);
			re.printStackTrace();
		}
		try{
			String rmiSecure = System.getProperty("rmi.secure");
			if(rmiSecure != null && rmiSecure.equalsIgnoreCase("true"))
			{
				RMIAccessAPI rmiaccessapi = (RMIAccessAPI)Naming.lookup(urlString+"/RMIAccessAPI");
				topocs = (TopoAPI)rmiaccessapi.getAPI("root", "public", "TopoAPI");
			}
			else{
				topocs = (TopoAPI)Naming.lookup((urlString+"/TopoAPI"));}
				System.out.println("GOT HANDLE");
			ManagedObject mo_Node=new ManagedObject();
			String NodeName = args[2];
			mo_Node.setName(NodeName);
			Properties prop = new Properties();
			prop.put("groupIDs","xxxx");
			mo_Node.setProperties(prop);
			System.err.println("Add Object result is::"+topocs.addObject(mo_Node));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
