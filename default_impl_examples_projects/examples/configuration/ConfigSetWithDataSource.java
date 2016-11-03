/* $Id: ConfigSetWithDataSource.java,v 1.2 2007/02/22 15:02:57 srajeswari Exp $ */
package com.adventnet.nms.example.config;

//NMS imports
import com.adventnet.management.config.TaskGenerator;
import com.adventnet.management.config.ConfigClient;
import com.adventnet.management.config.ConfigClientAPI;
import com.adventnet.management.config.ConfigResultListener;
import com.adventnet.management.config.ConfigResultEvent;
import com.adventnet.management.config.AttributeConstants;
import com.adventnet.management.config.xml.DeviceResult;
import com.adventnet.management.config.xml.AttributeResult;
import com.adventnet.management.config.xml.Device;

// Java imports
import java.util.Vector;
import java.util.Enumeration;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

/** This example is used to execute the template task with dataSource.
 *	The template task and the dataSource are should be already existing.
 *  The data source forms the repository which forms the input to the template. 
 *  When the configuration template is executed, the values to be filled in the template is taken from the data
 *  source. For changing the input values for the template, the data source has to be changed accordingly.
 */

public class ConfigSetWithDataSource implements ConfigResultListener
{
	ConfigClient configClient;
	ConfigClientAPI configClientAPI;
	static ConfigSetWithDataSource configSet;
	ConfigParserOptions parserOptions;
	String usage = "java com.adventnet.nms.example.config.ConfigSetWithDataSource [-d] -u userName -P password [-h ConfigServerHost] -p port deviceName [-S Deviceport] [device [-S DevicePort] ... ]  -t taskName -D dataSourceName\n"+
		" Options: \n"+
		"    -d\t\t\t\tGenerates debugging information\n" +
		"    -u <userName>\t\tSpecify the name of the configure user\n"+
		"    -P <password>\t\tPassword\n"+
		"    -h <hostName>\t\tSpecify the host where ConfigServer is running\n"+
		"    -p <portNumber>\t\tSpecify the port where ConfigServer is running\n"+
		"     deviceName\t\t\tSpecify the name of the device\n"+
		"    -S <devicePort>\t\tSpecify the devicePort where particular protocol is running\n"+            
		"    -t <taskName>\t\tName of the already existing task\n"+
		"    -D <dataSourceName>\t\tName of the already existing dataSource\n";

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
	public ConfigSetWithDataSource()
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

		// Name of the already existing template task
		taskName = parserOptions.getTaskName();

		// port where ConfigServer is listening
		port = String.valueOf ( parserOptions.getPort() );

		// HostName where the ConfigServer is running
		hostName = values[1];
		String[] remArguments = parserOptions.getRemArguments();

		// Name of the already existing dataSource
		dataSourceName = remArguments[remArguments.length-1];
	}

	// Does the configuration for various devices
	private void configure()
	{
		// This class create a task
		TaskGenerator taskGenerator = new TaskGenerator();

		// set the already existing taskName
		taskGenerator.setTaskName( taskName );

		// set the already existing dataSourceName
		taskGenerator.setDataSourceName( dataSourceName );

		// Associate devices for the task
		taskGenerator.setDevices(  devices );

		taskGenerator.setNewTask( false );
		if (ConfigParserOptions.DEBUG)
		{
			System.out.println ("\n" +taskGenerator.getTask()+ "\n");
		}
		try
		{
			// Getting the ConfigClient Instance,which communicates with the ConfigServer.
			configClient = ConfigClient.getInstance("com.adventnet.management.config.DefaultConfigClientImpl", hostName, port, userName, password);

			// Registering the listener which in turn returns an UniqueID, which should
			// be used for sending the data.This is the uniqueID which is used by the
			// client and ConfigServer
			String uniqueID = configClient.registerResultListener( configSet );
			configClientAPI = new ConfigClientAPI( configClient, uniqueID );
			//For executing a task which in turn calls the ConfigServer's executeTask method
			configClientAPI.executeTask ( taskGenerator.getTask(), null );
		}
		catch (Exception ex)
		{
			ex.printStackTrace ();
		}
	}

	public static void main(String args[])
	{
		configSet = new ConfigSetWithDataSource();
		configSet.callConfigureParseOptions( args );
		configSet.assignValues();
		configSet.configure();
	}
	// For receiving the events from the ConfigServer.
	public void setResult (ConfigResultEvent cre)
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
		if (cre.getErrorString() != null )
		{
			System.out.println( cre.getErrorString() );
		}
		//Deregisters the ResultListener
		configClient.deregisterResultListener( configSet );
		configClient.close();
		System.exit(0);
	}
}




