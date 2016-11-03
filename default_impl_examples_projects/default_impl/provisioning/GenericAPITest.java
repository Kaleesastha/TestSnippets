//$Id: GenericAPITest.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package test.provisioning;

import java.io.*;
import java.util.*;

import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.nms.provisioning.ext.*;
import com.adventnet.nms.provisioning.*;

/**
 *  This command line program uses the GenericAPI provisioning extension 
 *  which allows remote access to managed objects and invoking of
 *  MO methods so they execute on the server.
 **/
public class GenericAPITest 
{
	static String serverName = null;
	static String moName = null;
	ProvisioningAPI api = null;

	public GenericAPITest() 
	{

		try 
		{
			api = (ProvisioningAPI) java.rmi.Naming.lookup
				("//"+serverName+"/ProvisioningAPI");

			ExtensionAPI exapi = (ExtensionAPI) api.getExtensionAPI("Generic");
			RemoteMO mo = exapi.getRemoteMO(moName);
			System.out.println("MO Properties:\n"+mo.getProperties());

			Object arg[] = { "Major" };
			System.out.println("MO Invoking getStatus number for Major: "
					+mo.invokeMethod("getStatus", arg));

		}
		catch (Exception ex)
		{
			System.err.println("Error initializing: " + ex);
			ex.printStackTrace();
		}

	}

	public static void main (String[] args) throws Exception
	{
		if (args.length != 2) 
		{
			System.out.println(" Usage : java " + 
					GenericAPITest.class.getName() 
					+ " ServerName MOName");
			System.exit(-1);
		}
		serverName = args[0];
		moName = args[1];
		GenericAPITest t = new GenericAPITest();
	} 
}
