//$Id: TemplateConfigSet.java,v 1.2 2007/02/22 15:02:57 srajeswari Exp $
/*
 * @(#)TemplateConfigSet.java	
 *
 * Copyright (c) 1996-2002 Adventnet, Inc. All Rights Reserved.
 * Please read the included COPYRIGHTS file for more details.
 * 
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES 
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. ADVENTNET, INC. 
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE 
 * OR ITS DERIVATIVES.
 */

package com.adventnet.nms.example.config;

//NMS imports
import com.adventnet.management.config.TaskGenerator;
import com.adventnet.management.config.ConfigClient;
import com.adventnet.management.config.ConfigConstants;
import com.adventnet.management.config.ConfigClientAPI;
import com.adventnet.management.config.ConfigResultListener;
import com.adventnet.management.config.ConfigResultEvent;
import com.adventnet.management.config.AttributeConstants;
import com.adventnet.management.config.DataSourceGenerator;
import com.adventnet.management.config.snmp.SnmpAttribute;
import com.adventnet.management.config.xml.DeviceResult;
import com.adventnet.management.config.xml.AttributeResult;
import com.adventnet.management.config.xml.Device;

// Java imports
import java.util.Vector;
import java.util.Enumeration;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class TemplateConfigSet implements ConfigResultListener
{
	ConfigClient configClient;
	ConfigClientAPI configClientAPI;
	static TemplateConfigSet tempTask;
	ConfigParserOptions parserOptions;

	String usage = "java com.adventnet.nms.example.config.TemplateConfigSet [-d] -u userName -P password [-h ConfigServerHost] -p port deviceName [-S Deviceport] [device [-S DevicePort] ... ]  -t taskName -D dataSourceName\n"+
		" Options: \n"+
		"    -d\t\t\t\tGenerates debugging information.\n" +
		"    -u <userName>\t\tSpecify the name of the user configuring the device.\n"+
		"    -P <password>\t\tSpecify the password of the user.\n"+
		"    -h <hostName>\t\tSpecify the host where ConfigServer is running.\n"+
		"    -p <portNumber>\t\tSpecify the port where ConfigServer is running.\n"+
		"     deviceName\t\t\tSpecify the name of the device to be configured.\n"+
		"    -S <devicePort>\t\tSpecify the port in which the SNMP agent is running.\n"+            
		"    -t <taskName>\t\tSpecify the name of the Task to be created.\n"+
		"    -D <dataSourceName>\t\tSpecify the name of the DataSource to be created.\n";

	String[] options = { "-d", "-h" };
	String[] mOptions = { "-u", "-P", "-p", "-t", "-D" };
	String[] values = { "false", "localhost"  };
	String userName = null;
	String taskName = null;
	String port = null;
	String password = null;
	Device[] devices = null;
	String dataSourceName = null;
	String hostName = null;
	boolean executionFinished = false;
	boolean saveFinished = false;

	public TemplateConfigSet()
	{
	}

	private void callConfigureParseOptions( String[] args )
	{
		parserOptions = new ConfigParserOptions( mOptions, options, values, args, usage );
	}

	private void assignValues()
	{
		// Name of the configure user
		userName = parserOptions.getUserName();

		// Password for the configure user
		password = parserOptions.getPassword();

		Vector vec = parserOptions.getDevices();
		Enumeration enumerate = vec.elements();
		devices = new Device[vec.size()/2];
		int index = 0;
		if ( ConfigParserOptions.DEBUG)
		{
			System.out.println("Devices\t    SnmpPort\n");
		}
		while( enumerate.hasMoreElements() )
		{
			String deviceName = (String)enumerate.nextElement();
			String devicePort  = (String)enumerate.nextElement();
			try
			{
				Integer.parseInt( devicePort );
			}
			catch( NumberFormatException nfe )
			{
				System.out.println("Invalid port for "+ deviceName +" , Taking -1");
				devicePort = "-1";
			}
			if ( ConfigParserOptions.DEBUG)
			{
				System.out.println(deviceName+"     "+devicePort);
			}
			devices[index] = new Device(deviceName,devicePort);
			deviceName = null;
			devicePort = null;
			index++;
		}

		// Name of the template task to be created 
		taskName = parserOptions.getTaskName();

		// port where ConfigServer is listening
		port = String.valueOf ( parserOptions.getPort() );

		// HostName where the ConfigServer is running
		hostName = values[1];
		String[] remArguments = parserOptions.getRemArguments();

		// Name of the datasource to be created 
		dataSourceName = remArguments[remArguments.length-1];
	}

	// Does the configuration for various devices
	private void configure()
	{
		try
		{
			// Getting the ConfigClient Instance, to communicate with the ConfigServer.
			configClient = ConfigClient.getInstance("com.adventnet.management.config.DefaultConfigClientImpl", hostName, port, userName, password);

			// Registering the listener which in turn returns an UniqueID, that should
			// be used for sending the data.This is the uniqueID that is used by the
			// client and ConfigServer
			String uniqueID = configClient.registerResultListener( tempTask );
			configClientAPI = new ConfigClientAPI( configClient, uniqueID );

			if (ConfigParserOptions.DEBUG)
			{
				System.out.println ("Successfully Got the ConfigClient Handle \n");
			}


			// This class creates the task
			TaskGenerator taskGenerator = new TaskGenerator();

			// sets the task name 
			taskGenerator.setTaskName( taskName );
			System.out.println("Enter the Attributes to be associated with the Task:");
			while (true)
			{	
				System.out.println("Enter the OID:");	
				String oid = readLine();
				System.out.println("Enter the Label for the OID:");	
				String label = readLine();
				System.out.println("Enter the Type of the OID:");	
				String type =readLine();
				System.out.println("Enter the value for OID which should use one these three Inputs\n\t$UserInput$ui\n\t$NEInput$ni\n\t$InventoryInput$ii  :");	
				String value = readLine();
				while (true)
				{
					if ( !value.startsWith("$UserInput$") && !value.startsWith("$NEInput$") && !value.startsWith("$InventoryInput$") )
					{	 
						System.out.println("Enter the value for OID which should use one these three Inputs\n\t$UserInput$ui\n\t$NEInput$ni\n\t$InventoryInput$ii  :");	
						value = readLine();
					}
					else
						break;
				}	 
				SnmpAttribute snmpAttr = new SnmpAttribute(oid,label,type,value);
				taskGenerator.addAttribute(snmpAttr);
				System.out.println("Do you wish to continue?[y/n] ");
				if (readLine().equalsIgnoreCase("n"))
					break;
			}

			taskGenerator.setProtocol("snmp");
			taskGenerator.setOverwrite(true);
			taskGenerator.setNewTask(true);
			taskGenerator.setTemplate(true);

			if (ConfigParserOptions.DEBUG)
			{
				System.out.println (" Template XML being saved is \n" +taskGenerator.getTask()+ "\n");
			}

            //saves the template task constructed using TaskGenerator
			configClientAPI.saveTask(taskGenerator.getTask());
			while (!saveFinished)
				Thread.sleep(100);
			SnmpAttribute [] snmpAttr = (SnmpAttribute [])taskGenerator.getAttributeArray();
			String dataSourceXML = createDataSource(snmpAttr);
			  

			if (ConfigParserOptions.DEBUG)
			{
				System.out.println ("DataSource XML being saved is \n" +dataSourceXML+ "\n");
			}

            //saves the dataSource XML constructed using the DataSourceGenerator
			configClientAPI.saveDataSource(dataSourceXML);

            //executes the Template Task with the dataSource 
			configClientAPI.executeTask(taskName,dataSourceName,devices,null);  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

    //Associating the Datasources with the particular inputs  
	public void manipulateAttributes(DataSourceGenerator dsg,String oid,String value,String input) throws Exception
	{
		if (input.equals("UserInput"))
		{
			String placeHolder = value.substring(value.lastIndexOf("$")+1);
			System.out.println("Enter the User Input for the OID "+ oid + " :");
			String uservalue = readLine();
			//DataSource creation for User Input
			dsg.addUserInput(placeHolder,"sysContact",uservalue);
		}
		else if (input.equals("InventoryInput"))
		{
			String placeHolder = value.substring(value.lastIndexOf("$")+1);
			System.out.println("Enter the MOName using which the InventoryInput has to be performed:");
			String moName = readLine();

			System.out.println("Enter the MOProperty using which the InventoryInput has to be performed:");
			String moProperty = readLine();
			
			System.out.println("In case the MO is not available in DB,please specify the default value to be taken for configuring the OID  "+ oid +" :");
			String defaultVal = readLine();
			//Datasource creation for Inventory Input
			dsg.addInventoryInput(placeHolder,moName,moProperty,defaultVal);
		}
		else if(input.equals("NEInput"))
		{
			String placeHolder = value.substring(value.lastIndexOf("$")+1);
			//This TaskGenerator is for getting the oid value from a Network Element
			TaskGenerator temp = new TaskGenerator();

			temp.setProtocol("snmp");

			Device dev [] = new Device [1];
			System.out.println("Please enter the following details in order to get the value from NE:");	
			System.out.println("Enter the device for NEInput:");

			String devName = readLine();
			System.out.println("Enter the port for NEInput:");

			String devPort = readLine();
			dev[0] = new Device(devName,devPort);

			System.out.println("Enter the OID for NEInput:");
			String neoid = readLine();
			System.out.println("Enter the Type for NEInput:");
			String type = readLine();
			SnmpAttribute sa = new SnmpAttribute(neoid,placeHolder,type,"");
			temp.addAttribute(sa);
			temp.setDevices(dev);


			if (ConfigParserOptions.DEBUG)
			{
				System.out.println (" The Task XML given as input for NEInput is \n" +temp.getTask()+ "\n");
			}
            //DataSource creation for NEInput
			dsg.addNEInput(placeHolder,temp.getTask());

		}
		
	}

	public String findInput(String value)
	{
		int index = -1;
		index = value.indexOf("$UserInput$");
		if (index != -1)
		{	
			return "UserInput";
		}	
		index = value.indexOf("$InventoryInput$");	
		if (index != -1)
		{   
			return "InventoryInput";
		}	
		index = value.indexOf("$NEInput$");	
		if (index != -1)
		{
			return "NEInput";
		}	
		else
		{	
			System.out.println("None of the Inputs Found");		
			return null;
		}	
			
		
	}

    //Creating the DataSource 
    public String createDataSource(SnmpAttribute [] snmpAttr) throws Exception
	{
			DataSourceGenerator dsg = new DataSourceGenerator();
			//associating the DataSource Name
			dsg.setDataSourceName(dataSourceName);
			//associating the tasks with the Datasource
			dsg.setAssociatedTasks(new String[]{taskName});
			for (int i=0;i<snmpAttr.length;i++)
			{
				  String oid =  snmpAttr[i].getIdentifier();
                  String value = snmpAttr[i].getValue();
				  String input = findInput(value);
				  if (input != null)
				  manipulateAttributes(dsg,oid,value,input);
				  
			}
			
			String dataSourceXML = dsg.getDataSource();
			return dataSourceXML;
	}		

	public static void main(String args[])
	{
		tempTask = new TemplateConfigSet();
		tempTask.callConfigureParseOptions( args );
		tempTask.assignValues();
		tempTask.configure();
	}

	public String readLine() throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		return line;
	}
	// For receiving the events from the ConfigServer.
	public void setResult (ConfigResultEvent cre)
	{
		if (cre.getWorkID() == ConfigConstants.EXECUTE_TASK_WITH_DATASOURCE)
		{
			if (cre.getErrorString() != null )
			{
				System.out.println( "Error is : "+cre.getErrorString() );
			}
			else
			{
				DeviceResult[] result = cre.getConfigResult();
				if (result != null)
				{
					if (ConfigParserOptions.DEBUG)
					{
						byte[] data = cre.getResponseData();
						ByteArrayInputStream byteArr = null;
						ObjectInputStream ois = null;
						try
						{
							byteArr = new ByteArrayInputStream( data );
							ois = new ObjectInputStream( byteArr );
							int length = ois.readInt();
							byte[] dataBytes = new byte[ length ];
							ois.readFully ( dataBytes, 0, length );
							String xmlFile = new String( dataBytes );
							System.out.println ("\nResult xml file : \n\n" + xmlFile +"\n");
						}
						catch (IOException ioe)
						{
							System.err.println ("Exception in reading the result file");
							ioe.printStackTrace();
						}
						finally
						{
							try
							{
								byteArr.close();
								ois.close();
							}
							catch (Exception ex)
							{
							}
						}
					}
					for (int i = 0; i < result.length; i++)
					{
						String deviceName = result[i].getDeviceName();
						String taskName = result[i].getTaskName();
						AttributeResult[] attrResult = result[i].getAttributeResults();
						if(attrResult == null)
						{
							if( !result[i].isConfigurationAttempted() )
								System.out.println("Device : "+ deviceName + " Configuration : "+" Configuration Ignored");

						}
						else
						{
							for (int j = 0; j < attrResult.length; j++)
							{
								if (attrResult[j].getAttributeType() != AttributeConstants.GROUP_ATTRIBUTE)
								{
									System.out.println ("Device:  " + deviceName +"  Attribute: " +attrResult[j].getIdentifier() +"  Status:  " +attrResult[j].getMessage()+"\n");
								}
							}
						}
					}
				}
			}
			executionFinished = true;
		}
		else if (cre.getWorkID() == ConfigConstants.SAVE_TASK)
		{
			if (cre.getErrorString() != null )
			{
				System.out.println( "Error is " + cre.getErrorString() );
			}
			else
			{
				System.out.println("Task XML got saved successfully");	
			}
			saveFinished = true;
		}
		else if (cre.getWorkID() == ConfigConstants.SAVE_DATASOURCE)
		{
			if (cre.getErrorString() != null )
			{
				System.out.println( "Error is "+cre.getErrorString() );
			}
			else
			{
				System.out.println("DataSource XML got saved successfully");	
			}
		}
		if (executionFinished)
		{
			//Deregisters the ResultListener
			configClient.deregisterResultListener( tempTask );
			configClient.close();
			System.exit(0);
		}
	}
}
