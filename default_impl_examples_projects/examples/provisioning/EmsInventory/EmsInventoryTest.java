/*$Id: EmsInventoryTest.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/

package com.adventnet.nms.provisioning.inventorydb.example;

//Java imports
import java.rmi.Naming;
import java.util.Properties;

//Nms imports
import com.adventnet.nms.provisioning.inventorydb.InventoryAPI;
import com.adventnet.nms.provisioning.inventorydb.NetworkInventory;

public class EmsInventoryTest
{
	public static void main(String args[])
	{
		if(args.length == 0
		   || args.length < 6)
		{
			usage();
			return;
		}
		
		try
		{
			InventoryAPI inventoryAPI = (InventoryAPI)Naming.lookup("rmi://"+args[1]+":"+args[3]+"/InventoryAPI");
			System.out.println(" InventoryAPI handle is successfully got.");

			String emsName = "ExampleEms";
			String objectKey = args[5];
			if(args.length == 8)
			{
				emsName = args[7];
			}

			NetworkInventory networkInventory = inventoryAPI.getInventory(emsName, objectKey);
			Properties inventoryProp = networkInventory.getProperties();
			System.out.println("NetworkInventory with name "+objectKey+" is successfully got.");
			System.out.println("Properties of the NetworkInventory are: "+inventoryProp); 
		}
		catch(Exception exc )
		{
			exc.printStackTrace();
		}

	}

	private static void usage()
	{
		System.out.println("Usage: java com.adventnet.nms.provisioning.inventorydb.example.EmsInventoryTest"
						   +" -HOST <Host name where Server is running> -PORT <RMIRegistry port>"
						   +" -OBJECTKEY <key of the NetworkInventory object> "
						   +"[-EMSNAME <Name of the EMS.>]");
	}
}
