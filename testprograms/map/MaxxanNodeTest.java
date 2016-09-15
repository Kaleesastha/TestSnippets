package com.maxxan.server.topology;

import java.lang.*;
import java.util.*;
//import junit.framework.*;
//import com.maxxan.server.topology.MaxxanNode;
import java.rmi.*;
import test.Printer;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;



public class MaxxanNodeTest //implements RunProcessInterface
{
	public MaxxanNodeTest()
	{
	}

	public static void main(String args[])
	{
		MaxxanNodeTest mnt = new MaxxanNodeTest();
		
		//		  mnt.testDelete();
		//mnt.testGetByName();
		//DBServer.comapi.comtorel.printCache();
		for (int i=0;i<1;i++)
		{
			mnt.testUpdateNode();
			mnt.testAddUpdateDeleteNode();
			}
		mnt.resetTopology();
		
	}
	
	
	
	
	public void callMain(String args[])
	{
		try{
			Thread.sleep(60000);
		}
		catch(Exception eee)
		{
		}
		for (int i=0;i<1;i++)
		{
			testUpdateNode();
			testAddUpdateDeleteNode();
		}
		
	}
	
	
	
	public TopoAPI getHandle()
	{
		TopoAPI topo = null;
		ManagedObject child=null;
		TopoObject to = null;
		Vector v = new Vector();
		try
		{
			topo=(TopoAPI)Naming.lookup("//ramanr/TopoAPI");
			System.out.println("sucessfully got	 the handle");
		}
		catch (Exception exx)
		{
			System.out.println("Exception while getting handle "+exx);
			
		}
		return topo;
	}
	
	
	
	
	
	

	
	public boolean isInitialized()
	{
		return true;
	}
	public void shutDown()
	{
		System.err.println("Stopping MaxxanNodeTest");
	}
	
	
	public void testUpdateNode()
	{
		System.err.println("testUpdateNode: BEGIN");

		boolean testSuccess = false;
		
		try
		{
			//TopoAPI topoApi = com.adventnet.nms.topodb.DBServer.topodb;
			TopoAPI topoApi = getHandle();
			
			boolean wasAdded = true;
			
			final int NUM_NODES = 100;

			for (int i = 1; (i <= NUM_NODES) && wasAdded; i++)
			{
				/*MaxxanNode newNode = new MaxxanNode();
				  
				  newNode.setIpAddress("10.100.10." + i);
				  newNode.setName("Node" + i);
				  newNode.setSysOID("10.100.10." + i);
				  newNode.setManaged(false);
				  newNode.setIsSNMP(false);*/
				Printer newNode = new Printer();
				newNode.setIpAddress("10.100.10." + i);
				newNode.setName("Node" + i);
				newNode.setSysOID("10.100.10." + i);
				newNode.setManaged(false);
				newNode.setConsoleDispBufferText("testval");
				
				
				wasAdded = wasAdded & topoApi.addObject(newNode);
				
				if (wasAdded)
				{
					System.err.println("testUpdateNode: added node " + i);
				}
				else
				{
					System.err.println("testUpdateNode: node " + i + " NOT added");
				}
			}

			boolean wasUpdated = true;
			for (int i = 1; (i <= NUM_NODES) && wasUpdated && wasAdded; i++)
			{
				ManagedObject updatedObject =
					(ManagedObject)topoApi.getByName("Node" + i);

				 wasUpdated =
					 wasUpdated & topoApi.updateObject(updatedObject, false, false);
				if (wasUpdated)
				{
					System.err.println("testUpdateNode: updated node " + i);
				}
				else
				{
					System.err.println("testUpdateNode: node " + i + " NOT updated");
				}
			}
			resetTopology();
			boolean wasDeleted = true;
            /*for (int i = 1; (i <= NUM_NODES) && wasUpdated && wasAdded; i++)
			{
				ManagedObject updatedObject =
					(ManagedObject)topoApi.checkOut("Node" + i);

				 wasDeleted =
					 wasDeleted & topoApi.deleteObject(updatedObject, true, true);
				if (wasDeleted)
				{
					System.err.println("testUpdateNode: deleted node " + i);
				}
				else
				{
					System.err.println("testUpdateNode: node " + i + " NOT deleted");
				}
				}*/

			testSuccess = wasAdded & wasUpdated & wasDeleted;
		}
		catch (Exception exception)
		{
			System.err.println("testUpdateNode: " + exception.getMessage());

			testSuccess = false;
		}

		System.err.println("testUpdateNode: END");
	}

	public void testDelete()
	{
		System.err.println("testDelete: BEGIN");

		boolean testSuccess = false;

		try
		{
			//TopoAPI topoApi = com.adventnet.nms.topodb.DBServer.topodb;
			TopoAPI topoApi = getHandle();
			
			boolean wasAdded = true;
			boolean wasDeleted = true;
			
			final int NUM_NODES = 255;
			
			for (int i = 1; (i <= NUM_NODES) && wasAdded && wasDeleted; i++)
			{
				
				/*MaxxanNode newNode = new MaxxanNode();
				  
				  newNode.setIpAddress("10.100.10." + i);
				  newNode.setName("Node" + i);
				  newNode.setSysOID("10.100.10." + i);
				  newNode.setManaged(false);
				  newNode.setIsSNMP(false);*/
				Printer newNode = new Printer();
				newNode.setIpAddress("10.100.10." + i);
				newNode.setName("Printer4nd" + i);
				newNode.setSysOID("10.100.10." + i);
				newNode.setManaged(false);
				newNode.setConsoleDispBufferText("testval");
				
				wasAdded = wasAdded && topoApi.addObject(newNode);
				
				if (wasAdded)
				{
					System.err.println("testDelete: added node " + i);
				}
				else
				{
					System.err.println("testDelete: node " + i + " NOT added");
				}
				
				ManagedObject updatedObject =
					(ManagedObject)topoApi.checkOut("Printer4nd" + i);
				
				wasDeleted =
					wasDeleted && topoApi.deleteObject(updatedObject, false, true);
				if (wasDeleted)
				{
					System.err.println("testDelete: deleted node " + i);
				}
				else
				{
					System.err.println("testDelete: node " + i + " NOT deleted");
				}
			}

			testSuccess = wasAdded && wasDeleted;
		}
		catch (Exception exception)
		{
			System.err.println("testDelete: " + exception.getMessage());
			exception.printStackTrace(); 
			
			testSuccess = false;
		}
		
		System.err.println("testDelete: END");
	}
	
	
	
	private void resetTopology()
	{
		
		
		final int NUM_NODES = 100;
		/*try
		{
			TopoAPI topoApi=getHandle(); 

//			 TopoAPI topoAPI=com.adventnet.nms.topodb.DBServer.topodb;
			
			boolean wasAdded=true;
			for (int i = 1; (i <= NUM_NODES) && wasAdded; i++)
			{
				MaxxanNode newNode = new MaxxanNode();
				
				newNode.setIpAddress("10.100.10." + i);
				newNode.setName("Node" + i);
				newNode.setSysOID("10.100.10." + i);
				newNode.setManaged(false);
				newNode.setIsSNMP(false);
				
				wasAdded = wasAdded & topoApi.addObject(newNode);
				
				if (wasAdded)
				{
					System.err.println("resetTopology: added node " + i);
				}
				else
				{
					System.err.println("resetTopology: node " + i + " NOT added");
				}
			}
		}
		catch(Exception resetEx)
		{
			System.out.println("Exception in resetTopology "+resetEx);
		}
			*/

			try
			{
				//	  TopoAPI baseTopology = com.adventnet.nms.topodb.DBServer.topodb;
				TopoAPI baseTopology=getHandle();
				
				java.util.Iterator objectNames = baseTopology.getCompleteList().iterator();
				
				while (objectNames.hasNext())
				{
					String objectName = (String)objectNames.next();
					
					ManagedObject object = baseTopology.checkOut(objectName,2);
					
					boolean wasDeleted =
						baseTopology.deleteObject(object, false, true); //2.3
					
					if (!wasDeleted)
					{
						System.err.println( "TOPOTEST: resetTopology: OBJ not deleted: " +objectName);
					}
			}
            }
		catch (Exception exception)
		{
			//Debug.print("TOPOTEST", Debug.CRITICAL_LEVEL, "resetTopology: exception: " +exception.getMessage());
			System.out.println("Exception in resetTopology-delete "+exception);
		}
	}
	




	public void testAddUpdateDeleteNode()
	{
		System.err.println("testUpdateNode: BEGIN");

		boolean testSuccess = false;

		try
		{
			//TopoAPI topoApi = com.adventnet.nms.topodb.DBServer.topodb;
			TopoAPI topoApi = getHandle();

			boolean wasAdded = true;
			boolean wasUpdated = true;
			boolean wasDeleted = true;
			final int NUM_NODES = 100;

			for (int i = 1; (i <= NUM_NODES) && wasAdded; i++)
			{
				//MaxxanNode newNode = new MaxxanNode();
				Printer newNode = new Printer();
				newNode.setIpAddress("10.100.10." + i);
				newNode.setName("Node" + i);
				newNode.setSysOID("10.100.10." + i);
				newNode.setManaged(false);
				newNode.setIsSNMP(false);
				
				wasAdded = wasAdded & topoApi.addObject(newNode);
				
				if (wasAdded)
				{
					System.err.println("testAddUpdateDeleteNode: added node " + i);
				}
				else
				{
					System.err.println("testAddUpdateDeleteNode: node " + i + " NOT added");
				}
				
				ManagedObject updatedObject = (ManagedObject)topoApi.checkOut("Node" + i,2);
				if(wasAdded)
				{
				wasUpdated = wasUpdated & topoApi.updateObject(updatedObject, false, false);
				}
				if (wasUpdated )
				{
					System.err.println("testAddUpdateDeleteNode: updated node " + i);
				}
				else
				{
					System.err.println("testAddUpdateDeleteNode: node " + i + " NOT updated");
				}
				//ManagedObject deleteObject = (ManagedObject)topoApi.checkOut("Node" + i);
				if(wasAdded & wasUpdated)
				{
					wasDeleted = wasDeleted & topoApi.deleteObject(updatedObject, true, true);
				}
				if (wasDeleted)
				{
					System.err.println("testAddUpdateDeleteNode: deleted node " + i);
				}
				else
				{
					System.err.println("testAddUpdateDeleteNode: node " + i + " NOT deleted");
				}

			}

		 

			testSuccess = wasAdded & wasUpdated & wasDeleted;
		}
		catch (Exception exception)
		{
			System.err.println("testUpdateNode: " + exception.getMessage());

			testSuccess = false;
		}

		System.err.println("testAddUpdateDeleteNode: END");
	}



	public void testGetByName()
	{
		boolean getResult=true;
		ManagedObject getMo=null;
		TopoAPI api=getHandle();
		
		try
		{
			boolean wasAdded = true;
			final int NUM_NODES = 255;
			
			for (int i = 1; (i <= NUM_NODES) && wasAdded; i++)
			{
				/*MaxxanNode newNode = new MaxxanNode();
				  
				  newNode.setIpAddress("10.100.10." + i);
				  newNode.setName("Node" + i);
				  newNode.setSysOID("10.100.10." + i);
				  newNode.setManaged(false);
				  newNode.setIsSNMP(false);*/
				Printer newNode = new Printer();
				newNode.setIpAddress("10.100.10." + i);
				newNode.setName("Printerfortestgetbyname" + i);
				newNode.setSysOID("10.100.10." + i);
				newNode.setManaged(false);
				newNode.setConsoleDispBufferText("testval");
				
				
				wasAdded = wasAdded & api.addObject(newNode);
				
				if (wasAdded)
				{
					System.err.println("testGetByName: added node " + i);
				}
				else
				{
					System.err.println("testGetByName: node " + i + " NOT added");
				}
				
			}
			java.util.Iterator objNames=api.getCompleteList().iterator();
			while(objNames.hasNext())
			{
				String moName=(String)objNames.next();
				getMo=api.checkOut(moName,2);
				//api.updateObject(getMo,false,false);
				api.unlock(getMo);
				
				if(getMo==null)
				{
					System.out.println("testGetByName  : The mo is null");
					getResult=false;
				}
				else
				{
					System.out.println("testGetByName : Sucessfully got the mo "+getMo.getName());
					getMo=null;
				}
			}
		}
		catch (Exception getEx)
		{
			System.out.println("testGetByName : Exception in testGetByName "+getEx);
		}
	}

}
