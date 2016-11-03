//$Id: RMIConfigSet.java,v 1.2 2008/09/25 14:07:38 tinku Exp $
/*
 * @(#)RMIConfigSet.java	
 *
 * Copyright (c) 1996-2000 Adventnet, Inc. All Rights Reserved.
 * Please read the included COPYRIGHTS file for more details.
 * 
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES 
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET,
 * INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE 
 * OR ITS DERIVATIVES.
 */

package com.adventnet.nms.example.config;

//NMS imports
import com.adventnet.management.config.AttributeEvent;
import com.adventnet.management.config.ConfigTaskEvent;
import com.adventnet.management.config.ConfigTaskOverEvent;
import com.adventnet.management.config.ConfigTaskListener;
import com.adventnet.management.config.snmp.SnmpDevice;
import com.adventnet.management.config.snmp.SnmpTaskGenerator;
import com.adventnet.management.config.snmp.SnmpAttribute;
import com.adventnet.management.config.DeviceConfigurationAPI;
import com.adventnet.management.config.ConfigAPIFactory;

// java imports
import java.rmi.Naming;
import java.util.Hashtable;
import java.io.Serializable;
import java.rmi.RemoteException;

// Configuration through RMI
/**
 * This example program demonstrates how to use DeviceConfigurationAPI for 
 * configuring a set of devices.
 * It looks for  the "ConfigurationAPI" stub object in the rmiregistry
 * and an TaskGenerator instance is created and the RMI api's createTask is called
 * which in turn checks whether the task has already been created.If the task has
 * already been created then it executes the task present otherwise a new task
 * will be created and then executed.
 * This class implements Serializable interface,as this object is passed for 
 * the registerTaskListener(String <taskName>, ConfigTaskListener <configTaskListener>) 
 * remote method.
 * Create stub and skeleton classes fot this class and also put the stub class
 * in the Web NMS Server.
 * If Snmp attributes are not passed,then it is assumed that the user is 
 * executing the already created task.
 */
class RMIConfigSet implements ConfigTaskListener, Serializable 
{
	static RMIConfigSet rmiConfigSet;
	Hashtable htab;
	String userName;
	String host;
	String port;

	// class which executes the ConfigTask
	DeviceConfigurationAPI deviceConfigurationAPI;
	public RMIConfigSet(String args[])
	{
		ConfigureParams cp=new ConfigureParams();
		String diff="RMIConfigSet";
		htab=cp.getParams(diff,args);

		// Name of the configure user
		userName=(String)htab.get("UserName");

		// HostName where the ConfigServer is ruuning
		host=(String)htab.get("hostName");

		// Port where RMIRegistry is running 	
		port=(String)htab.get("port");
	}

	// Gets the ConfigAPIFactory reference from the rmiregistry
	private ConfigAPIFactory getConfigAPIFactory() throws Exception
	{
		return (ConfigAPIFactory)Naming.lookup("rmi://"+host+":"+port+"/ConfigurationAPI");
	}

	// Does the Configuration
	private void configure(Hashtable ptab)
	{
		// Name of the task
		String taskName=(String)ptab.get("taskName");
		String seq=(String)ptab.get("sequential");
		String overw=(String)ptab.get("overwrite");

		//Snmp Attributes like oid,label,type etc
		String [] oid=(String [])ptab.get("oid");
		String [] label=(String [])ptab.get("label");
		String [] type=(String [])ptab.get("type");
		String [] value=(String [])ptab.get("value");
		SnmpDevice [] device=(SnmpDevice [])ptab.get("device");

		//whether the task should be executed sequentially
		boolean sequential;

		//whether the task should be overwritten
		boolean overwrite;
		if (seq.equals("true"))
			sequential=true;
		else
			sequential=false;

		if (overw.equals("true"))
			overwrite=true;
		else
			overwrite=false;

		//whether rollback is needed for the device to be configured
		String isRollBackNeeded=(String)ptab.get("isRollBackNeeded");
		boolean val;
		if(isRollBackNeeded.equals("true"))
			val=true;
		else
			val=false;

		//Rollback type can be either 1 or 2
		//ie 1 means getting the Current Configuration
		//and 2 means using the document for rollback
		String rtype=(String)ptab.get("rollBackType");
		int rollBacktype=Integer.parseInt(rtype);

		if(rollBacktype !=1 && rollBacktype !=2)
		{
			System.out.println("Invalid Rollback type : Taking 1");
			rollBacktype = 1;
		}

		//RollBack document is required if the rollback type is 2
		String rollBackDocument=(String)ptab.get("rollBackDocument");

		//An instance of SnmpTaskGenerator is created for using RMI API's.
		SnmpTaskGenerator taskGenerator=new SnmpTaskGenerator();

		//sets the sequential value of the task	
		taskGenerator.setSequential(sequential);

		//sets the overwrite value of the task
		taskGenerator.setOverwrite(overwrite);

		//sets the taskname 
		taskGenerator.setTaskName(taskName);

		//sets the rollbackValue of the task
		taskGenerator.setRollback(val);

		//sets the rollbacktype ie either Current Configuration
		//or rollback document
		taskGenerator.setRollbackType(rollBacktype);
		taskGenerator.setRollbackDocument(rollBackDocument);

		//whether the task should be stored or not.
		boolean persistence;
		String per = (String) ptab.get ("persistence");
		if (per.equals ("true"))
			persistence = true;
		else
			persistence = false;
		taskGenerator.setPersistence( persistence );

		if ( oid != null)
		{	
			//if the task is not already created then we have to add the attributes
			//and then create the task
			taskGenerator.addAttributes(oid,label,type,value);
			//if the task is not already created then set the task as newTask
			taskGenerator.setNewTask(true);
		}
		else
			taskGenerator.setNewTask(false);

		//setting the devices for which the task is to be executed
		taskGenerator.setDevices(device);
		if ( ConfigParserOptions.DEBUG )
			System.out.println("\n"+taskGenerator.getTask()+"\n");

		try
		{
			//Exporting the Object to receive the events from the ConfigServer.
			java.rmi.server.UnicastRemoteObject.exportObject(rmiConfigSet,0);
			// Registering a listener to the ConfigServer.
			deviceConfigurationAPI.registerTaskListener(taskName,rmiConfigSet);
			//RMI API for executing the task is executeTask(String taskName)
			deviceConfigurationAPI.executeTask(taskGenerator.getTask());
		}
		catch(Exception ex)
		{
			System.err.println(ex.toString());
			if ( ConfigParserOptions.DEBUG)
				ex.printStackTrace();
			// Deregistering the taskListener
			try
			{
				deviceConfigurationAPI.deregisterTaskListener(taskName);		
			}
			catch(RemoteException re)
			{
			}
			unexport();
		}
	}

	public static void main(String args[])
	{
		rmiConfigSet=new RMIConfigSet(args);
		ConfigAPIFactory configAPIFactory=null;

		try
		{
			configAPIFactory=rmiConfigSet.getConfigAPIFactory();
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			System.exit(0);
		}
		try
		{
			rmiConfigSet.deviceConfigurationAPI=configAPIFactory.getDeviceConfigurationAPI(rmiConfigSet.userName);
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			if ( ConfigParserOptions.DEBUG)
			{
				ex.printStackTrace();
			}
			System.exit(0);
		}
		rmiConfigSet.configureDev();
	}

	private void configureDev()
	{
		rmiConfigSet.configure(htab);
	}


	// Interface methods of ConfigTaskListener class

	/**
	 * Invoked after every attribute is configured
	 */
	public void attributeSetStatus(AttributeEvent ae)
	{
		System.out.println("  Device:  "+ae.getDeviceName()+"  Attribute:  "+ae.getAttribute()+"  Status:  "+ ae.getMessage());
	}

	/**
	 * Invoked after all the retries for each device in a specific task is over
	 */
	public void deviceTaskStatus(ConfigTaskEvent cte)
	{
		System.out.println("  Device:  "+cte.getDeviceName()+"  RetryCompleted:  "+cte.getRetryCompleted()+"  Status:  "+ cte.getStatus());
	}

	/**
	 * Invoked after all the devices in a specific task is over
	 */
	public void taskStatus(ConfigTaskOverEvent ctoe)
	{
		try
		{
			rmiConfigSet.deviceConfigurationAPI.deregisterTaskListener(ctoe.getTaskName());
			unexport();
		}
		catch(RemoteException re)
		{
			System.err.println("Exception in deregistering the object "+re.toString());
			if ( ConfigParserOptions.DEBUG)
			{
				re.printStackTrace();
			}
		}

	}

	private void unexport()
	{
		try
		{
			java.rmi.server.UnicastRemoteObject.unexportObject(rmiConfigSet,true);
		}
		catch(RemoteException re)
		{
			System.err.println("Exception in unexporting the object"+re.toString());
			if ( ConfigParserOptions.DEBUG)
			{
				re.printStackTrace();
			}
		}
	}
}



