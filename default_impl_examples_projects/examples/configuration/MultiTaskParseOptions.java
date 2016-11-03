//$Id: MultiTaskParseOptions.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.example.config;

import com.adventnet.management.config.xml.Device;
import java.util.*;
public class MultiTaskParseOptions
{
	String[] mOptions = null;
	String[] options = null;
	String[] values = null;
	String[] arguments = null;
	String[] remArguments = null;
	public String usage = null;
	int deviceIndex = -1;
	Device [] devArr=null;
	public static boolean DEBUG=false;

	public MultiTaskParseOptions(String args[])
	{
		String usage = "Usage: java com.adventnet.nms.example.config.MultiTaskConfiguration "+
			" [-d] -u userName -P password [-h localhost] -p port -t taskName [-o true/false]"+
			" [-s true/false] [-r true/false] -st taskName device [-S port ] .... [ -st taskName device [-S port ] ....]"+
			" Options: \n"+
			"    -d\t\t\t\tGenerates debugging information\n" +
			"    -u <userName>\t\tSpecify the user\n"+
			"    -P <password>\t\tPassword\n"+
			"    -h <hostName>\t\tSpecify the host where ConfigServer is running\n"+
			"    -p <portNumber>\t\tSpecify the port where ConfigServer is running\n"+
			"    -t <taskName>\t\tSpecify taskName of the CombinedConfigTask\n"+
			"    -o <true/false>\t\tSpecify whether to override the existing configuration or not\n"+
			"    -s <true/false>\t\tSpecify whether to configure the devices in the same order or not \n"+
			"    -r <true/false>\t\tSpecify whether the rollback is required or not\n"+
			"    -st <subTaskName>\t\tSpecify the subTaskName\n"+
			"    -S <SnmpPort>\t\tSpecify the port where snmp agent is running\n";
		String [] mOptions = {"-u","-P","-p","-t","-st"};
		String []options = {"-d","-h" ,"-o","-s","-r"};
		String []values = {"false","localhost","false","false","false"};
		parseOptions(mOptions,options,values,args,usage);

	}	

	void parseOptions(String[] mOptions,String[] options,String[] values,String[] arguments,String usage)
	{
		this.mOptions = mOptions;
		this.options = options;
		this.values = values;
		this.usage = usage;
		this.arguments = arguments;
		remArguments = arguments;
		parseMandatoryArguments();
		parseOptionalArguments();
		String dval=values[0];
		if (dval.equalsIgnoreCase("true"))
			DEBUG=true;
		else
			DEBUG=false;
		assignRemArguments();
		checkForDevices();
	}

	private void parseMandatoryArguments()
	{
		if ( mOptions == null )
			return;
		for( int i = 0; i < mOptions.length; ++i)
		{
			boolean present = false;
			for( int j = 0; j < arguments.length ;++j)
			{
				if ( mOptions[i].equals(arguments[j]) )
				{
					if ( j+1 < arguments.length )
						present = true;
					else
					{
						printMessage("Value not present for this option "+ mOptions[i]+"\n");
						printUsage();
						System.exit(0);
					}
				}
			}
			if (! present )
			{
				printMessage("\""+mOptions[i]+"\" not present in the Arguments \n");
				printUsage();
				System.exit(0);
			}
		}
	}

	private void parseOptionalArguments()
	{
		if ( options == null )
			return;
		for( int i = 0 ; i < options.length; ++i)
		{
			for( int j = 0; j <  arguments.length;++j)
			{
				if ( options[i].equals(arguments[j]))
				{
				        if ( options[i].equals("-d") )
					{
						values[i] = "true";
						remArguments[j] = null;
						continue;
					}
					if(j+1 < arguments.length)
					{
					        values[i] = arguments[j+1];
						remArguments[j] = null;
						remArguments[j+1] = null;
						
					}
					
					
					else
					{
					         printMessage("Value not present for this option "+ options[i]+"\n");
						printUsage();
						System.exit(0);	
					}
				}
			}
		}
	}
	private void assignRemArguments()
	{
		Vector vec=new Vector(1);
		for ( int i=0;i<remArguments.length;++i)
			if ( remArguments[i] != null )
				vec.addElement(remArguments[i]);		
		remArguments=new String[vec.size()];
		vec.copyInto(remArguments);

	}
	private void checkForDevices()
	{
		for( int i = 0; i< remArguments.length ;++i)
		{
			if ( remArguments[i].equals("-st" ) )
			{
				if(remArguments[i+1].equals("-st"))
				{
					printMessage(" Task Name not mentioned  \n");
					printUsage();
					System.exit(0);
				}
				if ( deviceIndex == -1 )
					deviceIndex = i+2;
				if ( i+2 >= remArguments.length || remArguments[i+2].equals("-st"))
				{
					printMessage("TaskName: "+ remArguments[i+1]+" No devices specified for configuration \n");
					printUsage();
					System.exit(0);
				}
			}
		}
	}
	
	Vector getSubTaskNames()
	{
		Vector subTaskNames = new Vector(1);
		for( int i = 0 ; i < remArguments.length;++i)
		{
			if ( remArguments[i].equals("-st") )
			{
				if ( i+1 >= remArguments.length )
				{
					printMessage("SubTaskName not present for this option -st \n");
					printUsage();
					System.exit(0);
				}
				subTaskNames.addElement(remArguments[i+1]);
			}
		}
		if( subTaskNames.size() == 0 )
			subTaskNames = null;

		if ( subTaskNames == null )
		{
			printMessage("No SubTaskNames defined \n");
			printUsage();
			System.exit(0);
		}
		return subTaskNames;
	}
	
	Vector getSubDevices()
	{
		Vector deviceVec = new Vector(2);
		Vector subDeviceVec = new Vector(2);
		for( int i = deviceIndex; i< remArguments.length;++i)
		{
			if ( remArguments[i].equals("-st") )
			{
				deviceVec.addElement(subDeviceVec);
				subDeviceVec = new Vector(1);
				i++;
				continue;
			}
			else
			{
				String deviceName = remArguments[i];
				String port = "161";
				i++;
				//Indicates end of parsing.
				if ( i >= remArguments.length )
				{
					Device dev = new Device(deviceName,port);
					subDeviceVec.addElement(dev);
					deviceVec.addElement(subDeviceVec);

				}
				else if (  remArguments[i].equals("-st") )
				{
					Device dev = new Device(deviceName,port);
					subDeviceVec.addElement(dev);
					i--;
				}
				else if ( remArguments[i].equals("-S" ) )
				{
					i++;
					if ( i >= remArguments.length || remArguments[i].equals("-st"))
					{
						printMessage("Value not present for this option -S \n");
						printUsage();
						System.exit(0);
					}
					port = remArguments[i];
					Device dev = new Device(deviceName,port);
					subDeviceVec.addElement(dev);
					if ( i+1 >= remArguments.length )
					{
						deviceVec.addElement(subDeviceVec);

					}
				}
				else
				{
					Device dev = new Device(deviceName,port);
					subDeviceVec.addElement(dev);
					i--;
				}
			}
		}

		for(int i=0;i<deviceVec.size();i++)
		{
			Vector vec=(Vector)deviceVec.elementAt(i);
			devArr=new Device[vec.size()];
			vec.copyInto(devArr);
			deviceVec.setElementAt(devArr,i);
		}
		return deviceVec;
	}

        String getPort()
	{
		return getValue("-p");
	}

	String getCombinedTaskName()
	{
		return getValue("-t");
	}

	String getUserName()
	{
		return getValue("-u");
	}

	String getPassword()
	{
		return getValue("-P");
	}

	String getHostName()
	{
		return  values[1];
	}

	boolean getOverWriteValue()
	{
		String owVal= values[2];
		if (owVal.equalsIgnoreCase("true"))
		{
			return true;
		}
		else
			return false;
	}

	boolean getSequentialValue()
	{
		String seqVal=values[3];
		if(seqVal.equalsIgnoreCase("true"))
		{
			return true;
		}
		else
			return false;
	}

	String isRollBackReq()
	{
		return values[4];
	}

        private String getValue(String option)
	{
		for( int i = 0;i<remArguments.length ;++i)
		{
			if ( remArguments[i].equals(option) )
			{
				return remArguments[i+1];
			}
		}
		return null;
	}

        void printMessage(String  message)
	{
		System.out.println("\n"+message);
	}

	void printUsage()
	{
		System.out.println(usage);
	}



}

