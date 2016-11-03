//$Id: ConfigParserOptions.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.example.config;
import java.util.Vector;
import java.util.Properties;
class ConfigParserOptions
{
	private String[] mOptions;
	// Stores the optional arguments
	private String[] options;
	// Stores the optional values
	private String[] values;
	private String[] orgArguments;
	private String[] remArguments;
	private String usage;
	public static boolean DEBUG=false;
	ConfigParserOptions(String[] mOptions,String[] options,String[] values,String[] arguments,String usage)
	{
		this.mOptions=mOptions;
		this.options=options;
		this.values=values;
		this.orgArguments=arguments;
		this.usage=usage;
		remArguments=orgArguments;
		parseMandatoryArguments();
		parseOptionalArguments();
		assignRemArguments();
	}
	private	void parseMandatoryArguments()
	{
		if ( mOptions == null)
			return;
		boolean present=false;
		String option=null;
		for ( int i=0;i<mOptions.length;++i)
		{
			option=mOptions[i];
			for ( int j=0;j<orgArguments.length;++j)
			{
				if ( mOptions[i].equals(orgArguments[j]))
				{
					if ( j+1 < orgArguments.length )
					{
						present=true;
						break;
					}
					else
					{
						printMessage("Value not present for this option "+ mOptions[i]+"\n");
						printUsage();		
						System.exit(0);
					}
				}
				
			}
			if ( !present )
			{
				printMessage("\""+option+"\" not present in the Arguments \n");
				printUsage();
				System.exit(0);
			}
			present=false;
		}
	}
	private void parseOptionalArguments()
	{
		for ( int i=0;i<orgArguments.length;++i)
		{
			for ( int j=0;j<options.length;++j)
			{
				if ( options[j].equals(orgArguments[i]) )
				{
					if ( options[j].equals("-d") )
					{
						values[j]="true";
						DEBUG=true;
						remArguments[i]=null;
						continue;
					}
					if ( i+1 < orgArguments.length)
					{
						values[j]=orgArguments[i+1];
						remArguments[i]=null;
						remArguments[i+1]=null;
						break;
					}
					else
					{
						printMessage("Value is not present for this option "+ options[j]+"\n");
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
	String[] getRemArguments()
	{
		return remArguments;
	}
	void checkForFormat()
	{
		// Checking for the presence of devices
		if (! remArguments[0].equals("-u"))
		{
			printMessage("Order of \"-u\" changed \n");
			printUsage();
			System.exit(0);
		}
		if ( ! remArguments[2].equals("-P"))
		{
			printMessage("Order of \"-P\" changed \n");
			printUsage();
			System.exit(0);
		}
		if ( remArguments[0].equals("-t") )
		{
			printMessage("Order of \"-t\" changed \n");
			printUsage();
			System.exit(0);
		}
		
	}
	void printMessage(String  message)
	{
		System.out.println("\n"+message);
	}
	void printUsage()
	{
		System.out.println("Usage: "+usage);
	}
	Vector getDevices()
	{
		Vector deviceVec = new Vector(1);
		boolean toAdd=false;
		// host or port to check.
		String hOrP="-p";
		for ( int i=0;i< options.length;++i)
			if ( options[i].equals("-p"))
				hOrP="-P";
		for( int i=0;i<remArguments.length;++i)
		{
			if ( remArguments[i].equals(hOrP))
			{
				i=i+1;
				toAdd=true;
				continue;
			}
			if (! toAdd)
				continue;
			// Here we have to get the device name and port.
			String deviceName=null;
			String devicePort="161";
			if ( !(remArguments[i].equals("-t") ))
			{
				deviceName=remArguments[i];
				if ( i+1 >= remArguments.length)
				{
					printUsage();
					System.exit(0);
				}
				if ( remArguments[i+1].equals("-S") )
				{
					if ( i+1 >= remArguments.length)
					{
						printMessage("Value not present for this option \"-S\" \n");
						printUsage();
						System.exit(0);
					}
					i = i+2;
					if ( remArguments[i].equals("-t"))
					{
						devicePort="161";
						deviceVec.addElement(deviceName);
						deviceVec.addElement(devicePort);
						break;
					}
					else
					{
						devicePort=remArguments[i];
					}
				}
				else
				{
					devicePort="161";
				}
				deviceVec.addElement(deviceName);
				deviceVec.addElement(devicePort);
				deviceName=null;
				devicePort=null;
			}
			else
				break;
		}
		if ( deviceVec.size() ==0)
		{
			printMessage("No devices selected for Configuration \n");
			deviceVec=null;
			printUsage();
			System.exit(0);
		}
		return deviceVec;
		
	}
	String getTaskName()
	{
		for( int i=0;i<remArguments.length;++i)
		{
			if ( remArguments[i].equals("-t") )
				return remArguments[i+1];	
		}
		return null;
	}
	Vector getSnmpAttributes()
	{
		Vector snmpAttributes=new Vector(1);
		for( int i=0;i<remArguments.length;++i)
		{
			if ( remArguments[i].equals("-t") )
			{
				for (int j=i+2;j<remArguments.length;++j)
				{
					String attribute="";
					if ( remArguments[j].equals("-A"))
					{
						j++;
						if ( j >= remArguments.length)
							break;
						while(! remArguments[j].equals("-A"))
						{
							attribute+=remArguments[j]+" ";
							j++;
							if ( j>=remArguments.length)
								break;
							if ( remArguments[j].equals("-A"))
							{
								j--;
								break;
							}
						}
						snmpAttributes.addElement(attribute);
					}
					// newly added
					else
					{
						printMessage("\"-A\" option should be present for adding an attribute \n");
						printUsage();
						System.exit(0);
					}
				}
				break;
			}
		}
		return snmpAttributes;	
	}
	
	// to get the port
	// Only in the case of ConfigSet only, it is called.
	int getPort()
	{
		if (! remArguments[4].equals("-p") )	
		{
			printMessage("Order of \"-p\" changed \n");
			printUsage();
			System.exit(0);
		}
		
		try
		{
			int port=Integer.parseInt(remArguments[5]);
			return port;
		}
		catch(NumberFormatException nfe)
		{
			printMessage("Invalid  ConfigServer port "+ remArguments[5]+"\n");
			printUsage();
			System.exit(0);
		}
		
		return -1;
	}
	// To get the user name
	String getUserName()
	{
		return remArguments[1];
	}
	String getPassword()
	{
		return remArguments[3];
	}
}