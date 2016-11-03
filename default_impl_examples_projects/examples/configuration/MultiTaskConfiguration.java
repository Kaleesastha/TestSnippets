//$Id: MultiTaskConfiguration.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.example.config;

// NMS imports
import com.adventnet.management.config.ConfigClient;
import com.adventnet.management.config.ConfigResultListener;
import com.adventnet.management.config.ConfigResultEvent;
import com.adventnet.management.config.xml.DeviceResult;
import com.adventnet.management.config.AttributeConstants;
import com.adventnet.management.config.xml.AttributeResult;
import com.adventnet.management.config.ConfigConstants;
import com.adventnet.management.config.ConfigClientAPI;
import com.adventnet.management.config.TaskGenerator;

// java imports
import java.io.*;
import java.util.*;
import java.rmi.*;

/** This example is used to execute the combinedTask using ConfigServer.
 *  The combinedTask contains set of subtasks. 
 *  The subtasks are should be already existing.
 */
public class MultiTaskConfiguration implements ConfigResultListener
{
	ConfigClient configClient;
	ConfigClientAPI configClientAPI;
	static MultiTaskConfiguration multiTaskConfiguration;
	static MultiTaskParseOptions multiTaskParseOptions;
	static String userName;
	String password;
	String hostName;
	String port;
	Vector taskList;
	Vector deviceList;
	String combinedTask;
	boolean overwrite;
	boolean sequential;
	boolean isRollBackNeeded;

	// Does the Configuration with TCP
	private void configure()
	{   
		//userName of the client  
		userName=multiTaskParseOptions.getUserName();

		//passWord of the client
		password=multiTaskParseOptions.getPassword();

		//port where the ConfigServer is listening
		port=multiTaskParseOptions.getPort();

		//Host where the ConfigServer is running		
		hostName=multiTaskParseOptions.getHostName();

		//TaskName of the CombinedConfigTask
		combinedTask=multiTaskParseOptions.getCombinedTaskName();

		//Whether the task should be overwritten
		overwrite=multiTaskParseOptions.getOverWriteValue();

		//Whether the task should be executed sequentially
		sequential=multiTaskParseOptions.getSequentialValue();

		//Whether the CombinedTask should go for rollback incase 
		//DeviceConfigurtion Fails
		String isRollBackReq=multiTaskParseOptions.isRollBackReq();

		if (isRollBackReq.equalsIgnoreCase("true"))
		{
			isRollBackNeeded=true;
		}       	
		else
		{
			isRollBackNeeded=false;
		}	

		//subTaskNames for the CombinedConfigTask
		taskList=multiTaskParseOptions.getSubTaskNames();

		//subDevices for the CombinedConfigTask  
		deviceList=multiTaskParseOptions.getSubDevices();

		// This class create a task.    
		TaskGenerator taskGenerator = new TaskGenerator ();

		//sets the taskName to the combinedTaskName
		taskGenerator.setTaskName (combinedTask);

		//sets the overWrite value of the combinedTask
		taskGenerator.setOverwrite (overwrite);

		//sets the sequential value 
		taskGenerator.setSequential (sequential);

		//sets the rollback value for CombinedConfigTask   
		taskGenerator.setRollback(isRollBackNeeded);

		//sets the device for the specific tasks
		taskGenerator.setDevices (taskList, deviceList);

		if (multiTaskParseOptions.DEBUG)
		{
			System.out.println (taskGenerator.getTask ());
		}

		try
		{
			// Getting the ConfigClient Instance,which communicates with the ConfigServer.
			configClient =	ConfigClient.getInstance("com.adventnet.management.config.DefaultConfigClientImpl", hostName, port, userName, password);

			// Registering the listener which in turn returns an UniqueID, which should
			// be used for sending the data.This is the uniqueID which is used by the
			// client and ConfigServer
			String uniqueID = configClient.registerResultListener (multiTaskConfiguration);

			configClientAPI = new ConfigClientAPI (configClient, uniqueID);

			// Executing the task via the executeTask API available in ConfigClientAPI
			configClientAPI.executeTask (taskGenerator.getTask ());

		}

		catch (Exception ex)
		{
			System.out.println("Exception is"+ex);
			ex.printStackTrace ();
		}
	}

	public static void main (String args[])
	{
		multiTaskParseOptions=new MultiTaskParseOptions(args);
		multiTaskConfiguration=new MultiTaskConfiguration();
		multiTaskConfiguration.configure();
	}


	//This method will be called to display the result of the Configuration of Devices only in case of TCP
	public void setResult (ConfigResultEvent cre)
	{
		DeviceResult[] result = cre.getConfigResult ();
		if (result != null)
		{
			if (multiTaskParseOptions.DEBUG)
			{
				byte[]data = cre.getResponseData ();
				ByteArrayInputStream byteArr = null;
				ObjectInputStream ois = null;
				try
				{
					byteArr = new ByteArrayInputStream (data);
					ois = new ObjectInputStream (byteArr);
					int length = ois.readInt ();
					byte[] dataBytes = new byte[length];
					ois.readFully (dataBytes, 0, length);
					String xmlFile = new String (dataBytes);
					System.out.println ("Result xml file is \n" + xmlFile);
				}
				catch (IOException ioe)
				{
					System.err.println ("Exception in reading the result file");
					ioe.printStackTrace ();
				}
				finally
				{
					try
					{
						byteArr.close ();
						ois.close ();
					}
					catch (Exception ex)
					{

					}
				}
			}
			for (int i = 0; i < result.length; i++)
			{
				String deviceName = result[i].getDeviceName ();
				String taskName = result[i].getTaskName ();
				AttributeResult[]attrResult = result[i].getAttributeResults ();
				if(attrResult==null)
				{
					if(!result[i].isConfigurationAttempted())
						System.out.println("Device : "+deviceName+ " Configuration : Ignored");
				}
				else
				{
					for (int j = 0; j < attrResult.length; j++)
					{
						if (attrResult[j].getAttributeType () !=AttributeConstants.GROUP_ATTRIBUTE)
						{
							System.out.println ("Device:  " + deviceName +"  Attribute: " +attrResult[j].getIdentifier () +"  Status:  " +attrResult[j].getMessage ()+"\n");
						}
					}
				}
			}
		}


		if (cre.getErrorString () != null)
		{
			System.out.println (cre.getErrorString ());
		}

		//Deregisters the ResultListener
		configClient.deregisterResultListener (multiTaskConfiguration);

		configClient.close ();
		//exitting after everything is over.
		System.exit(0);
	}


}

