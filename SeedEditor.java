package com.adventnet.nms.topodb;

import java.rmi.Naming;
import java.util.Vector;
import com.adventnet.nms.netwatch.DiscoveryAPI;
import com.adventnet.nms.netwatch.SeedReader;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.topodb.DBServer;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.util.XMLDataReader;
import com.adventnet.nms.util.XMLNode;

public class SeedEditor implements RunProcessInterface
{
	
	//our proprietary class ... not exposed to any customers. Please do not user this for any other purpose.
	DiscoveryAPI disc = null;
	
	public void callMain(String[] args) 
	{
		//The seed.file present in <NMS_HOME>/conf/ directory.
		String fileString = PureUtils.rootDir + "conf/seed.file";
		
		//TopoAPI
		TopoAPI tA = null;

		try
		{
			//Wait till all the modules are initialized.
			while(!NmsUtil.webNMSModulesInitialized)
			{
				try
				{
					Thread.sleep(1000);
				}
				catch(Exception se)
				{
					System.out.println("Interrupted.");
				}
			}
			//Since this is a process and we are modifying the seed.file, there are chances that the NMS server 
			//can also modify the seed file. To avoid this we are introducing a delay.
			try
			{
				Thread.sleep(20000);
			}
			catch(Exception ie)
			{
				System.out.println("Interrupted...");
			}

			//TopoAPI handle.
			tA = (TopoAPI) NmsUtil.getAPI("TopoAPI");
		
			//discovery api handle.	
			disc=DBServer.api;
			
			//Root Node for the existing seed file is obtained by the following method.
			XMLNode rootNode=disc.getSeedFile();	
			
			//The following method deletes the TO_DISCOVER nodes in the rootNode.
			XMLNode modifiedNode = deleteNode(rootNode,"TO_DISCOVER");

			//Apply the changes made. This updates the seed file and also clears updates the memory.
			disc.handleSeedFile(modifiedNode,"set");

			//This adds the new set of nodes.
			tA.setAddressRangeToDiscover("192.168.108.31", "192.168.108.41","192.168.108.0", "255.255.255.0");
			System.out.println("Added the new TO_DISCOVER entry!!!!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//run process implementation. For testing purpose we have implmeneted this intreface
	public boolean isInitialized() 
	{
		return true;
	}
	
	//run process implementation. For testing purpose we have implmeneted this intreface
	public void shutDown() 
	{
 		System.out.println("Shutdown called .... ");
	}

	public static XMLNode deleteNode(XMLNode xNode, String deleteNodeName)
	{
		//From the rootNode we are getting the child nodes.
		Vector v = xNode.getChildNodes();

		if ((v == null) || (v.size() == 0)) 
		{
			return xNode;
		}

		//From the child nodes we are checking whether the node to be deleted is present (in this case TO_DISCOVER).
		//If the node to be deleted exists, then we delete it.
		for (int i=0; i<v.size(); i++) 
		{
			XMLNode node = (XMLNode)v.elementAt(i);
			if (node.getNodeName() == null) continue;
			//Here we deleting the node TO_DISCOVER. Here you can have your own implementation.
			if (node.getNodeName().equals(deleteNodeName)) 	
			{

				xNode.deleteChildNode(node);
			}
		}
		return xNode;
	}
}
