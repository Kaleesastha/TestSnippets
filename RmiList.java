package test;

import java.util.*;
import java.io.*;
import java.rmi.*;
import java.util.*;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.mapdb.MapAPI;
import java.rmi.server.RemoteServer;

public class RmiList {

    public static void main(String[] argv) {
        
        System.out.println("main: started");

	try
	{
		TopoAPI topo = null;
		if(argv.length !=0)
		{
			if (argv.length==1)
			{
				System.err.println("Usage: test.RmiList <hostName> <RMI_PORT>");
				System.exit(0);
			}
			else
				topo = (TopoAPI) Naming.lookup("//"+argv[0]+":"+argv[1]+"/TopoAPI");
		}
		if(topo == null)
			topo = (TopoAPI) Naming.lookup("//localhost/TopoAPI");
		Properties prop = new Properties();
		prop.setProperty("type","node");
		System.out.println("============nodes============");
		Vector vect = topo.getObjectNamesWithProps(prop);
		Enumeration enumm = vect.elements();
		while(enumm.hasMoreElements()) {
			System.out.println("==>"+enumm.nextElement());
		}
		System.out.println("============snmp-node============");
		prop.setProperty("type","snmp-node");
		vect = topo.getObjectNamesWithProps(prop);
		enumm = vect.elements();
		while(enumm.hasMoreElements()) {
			System.out.println("==>"+enumm.nextElement());
		}
		System.out.println("============Nodes in 108.0 network============");
		prop.setProperty("type","node");
		prop.setProperty("name","*.108.*");
		vect = topo.getObjectNamesWithProps(prop);
		enumm = vect.elements();
		while(enumm.hasMoreElements()) {
			System.out.println("==>"+enumm.nextElement());
		}
		System.out.println("============getClientHost is ============"+RemoteServer.getClientHost());
	}
	catch (Exception re)
	{
		System.out.println ( "Error in getting the handle: " + re);
		re.printStackTrace();
	}
	System.err.println("*************After lookup:"+System.currentTimeMillis()+"***********************************************************");

    }
}
