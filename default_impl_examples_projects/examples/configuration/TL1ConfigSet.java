//$Id: TL1ConfigSet.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/*
 * @(#)TL1ConfigSet.java	
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
import com.adventnet.management.config.ConfigClientAPI;
import com.adventnet.management.config.AttributeConstants;
import com.adventnet.management.config.ConfigConstants;
import com.adventnet.management.config.ConfigResultEvent;
import com.adventnet.management.config.ConfigResultListener;
import com.adventnet.management.config.xml.Device;
import com.adventnet.management.config.xml.DeviceResult;
import com.adventnet.management.config.xml.AttributeResult;
import com.adventnet.management.config.tl1.TL1Attribute;

// java imports
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * This example illustrates the execution of a TL1 Config Task 
 * It uses <code>ConfigClient</code> for 
 * sending the Configuration data to the <code>ConfigServer</code>.
 * To do the Configuration, the user has to run the NMS Server
 * hich starts the <code>ConfigServer</code> in turn internally.
 * This class implements <code>ConfigResultListener</code> 
 * for getting responses from ConfigServer.
 * Note that, this basic example is built such that, it can execute only
 * TL1 tasks containing a single attribute over a single device.  
 */
public class TL1ConfigSet implements ConfigResultListener
{
    ConfigClient configClient;
    ConfigClientAPI configClientAPI;
	static TL1ConfigSet tl1ConfigSet;

	private static String Usage = 
		" Usage:" + 
		
		"\n\n java com.adventnet.nms.example.config.TL1ConfigSet " + 
		"[-d] -u userName -P password [-h hostName] -p port " + 
		"-D deviceName [-T TL1Port] -t taskName [-o true/false] " + 
		"[-r true/false] [ -rbtype 1/2] [-rbdoc rollbackTaskName] [-per true/false] " + 
		"[-cmd commandCode] [-tid targetID] [-aid accessID] [-gblock generalBlock] " + 
		"[-mblock messagePayLoadBlock] \n\n" +

		" Options: \n\n" +

		"\t-d\t\tGenerates debugging information\n" +
		"\t-u\t\tSpecify the user's name\n" +
		"\t-P\t\tSpecify the password\n" +
		"\t-h\t\tSpecify the host where ConfigServer is running\n" +
		"\t-p\t\tSpecify the port where ConfigServer is running\n" +
		"\t-D\t\tSpecify the name of the TL1 device\n" +
		"\t-T\t\tSpecify the port where TL1 agent is running\n" +
		"\t-t\t\tSpecify the task's name\n" +
		"\t-o\t\tSpecify whether the existing task is to be overwritten\n" +
		"\t-r\t\tSpecify whether the rollback is required or not\n" +
		"\t-rbtype\t\tSpecify the rollback type, 1 for current configuration, 2 for rollback document\n" +
		"\t-rbdoc\t\tSpecify an already existing task's name, if -rbtype is specified as 2\n" +
		"\t-per\t\tSpecify whether the task is to be stored\n" + 
		"\t-cmd\t\tSpecify the TL1 command to be executed\n" + 
		"\t-tid \t\tSpecify the Target ID\n" +
		"\t-aid\t\tSpecify the Access ID\n" + 
		"\t-gblock\t\tSpecify the General Block values\n" + 
		"\t-mblock\t\tSpecify the Message Payload Block values\n" ; 

	private static String 
		debug = "false", 		
		hostName = "localhost", 
		userName, 
		password, 
		port, 
		deviceName, 
		tl1Port = "9099", 
		taskName, 
		isOverwrite = "false", 
		isRollbackNeeded = "false", 
		rollbackType = null, 
		rollbackDocument = null, 
		persistence = "true", 
		cmdCode, 
		accessID, 
		targetID, 
		generalBlock, 
		messagePayLoadBlock;
	
	public static void main (String args[])
    {
        tl1ConfigSet = new TL1ConfigSet ();
		tl1ConfigSet.configureDevices(args);
    }

	private void configureDevices(String[] args)
    {
		Hashtable pt = getParams(args);
		configure(pt);
    }

	private Hashtable getParams(String args[])
    {		
		//Assigning values to a Hashtable,
        Hashtable ht = new Hashtable();		
		if(args == null || args.length == 0)
		{
			printUsage();
		}
		else
		{
			for( int i = 0 ; i < args.length ; ++i)
			{
				if(args[i].equalsIgnoreCase("-help") || args[i].equalsIgnoreCase("--help"))
				{
					printUsage();
				}
				else if ( args[i].equals("-d") )
				{
					try
					{
						debug = "true";
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -d (debug) argument " + ae1.toString());
					}					
				}				
				else if ( args[i].equalsIgnoreCase("-u") )
				{					
					try
					{
						userName = args[++i];
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the '-u' (user) argument " + ae1.toString());
					}
				}
				else if (args[i].equals("-P"))
				{
					try
					{
						password = args[++i];
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the '-P' (password) argument " + ae1.toString());
					}
				}
				else if (args[i].equalsIgnoreCase("-h"))
				{
					try
					{
						hostName = args[++i];
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the '-h' (password) argument " + ae1.toString());
					}
				}				
				else if ( args[i].equals("-p"))
				{
					try
					{
						port = args[++i];
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the '-p' (ConfigServer port) argument " + ae1.toString());
					}
				}
				else if(args[i].equals("-D"))
				{
					try
					{
						deviceName = args[++i];
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -D (device) argument " + ae1.toString());
					}					
				}
				else if(args[i].equals("-T"))
				{
					try
					{
						tl1Port = args[++i];
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -T (TL1 Port) argument " + ae1.toString());
					}
				}
				else if(args[i].equals("-t"))
				{
					try
					{
						taskName = args[++i];
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -t (taskName) argument " + ae1.toString());
					}
				}
				else if(args[i].equals("-o"))
				{
					try
					{
						isOverwrite = (args[++i].equalsIgnoreCase("true") ? "true" : "false");
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -o (overwrite) argument " + ae1.toString());
					}
				}
				else if(args[i].equalsIgnoreCase("-r"))
				{
					try
					{
						isRollbackNeeded = args[++i];
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -r (rollback) argument " + ae1.toString());
					}
				}				
				else if(args[i].equalsIgnoreCase("-rbtype"))
				{
					try
					{
						rollbackType = args[++i];
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -rbtype (rollbackType) argument " + ae1.toString());
					}
					ht.put("RollbackType", rollbackType);
				}
				else if(args[i].equalsIgnoreCase("-rbdoc"))
				{
					try
					{
						rollbackDocument = args[++i];
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -rbdoc (rollbackDocument) argument " + ae1.toString());
					}
					ht.put("RollbackDocument", rollbackDocument);
				}
				else if(args[i].equalsIgnoreCase("-per"))
				{
					try
					{
						persistence = (args[++i].equalsIgnoreCase("false") ? "false" : "true");
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -T (TL1 port) argument " + ae1.toString());
					}
					ht.put("Persistence", persistence);
				}
				else if ( args[i].equalsIgnoreCase("-cmd"))
				{
					try
					{
						cmdCode = args[++i];						
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -cmd (commandCode) argument " + ae1.toString());
					}
					ht.put("CommandCode", cmdCode);
				}
				else if ( args[i].equalsIgnoreCase("-tid"))
				{
					try
					{
						targetID = args[++i];						
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -tid (targetID) argument " + ae1.toString());
					}
					ht.put("TargetID", targetID);
				}
				else if ( args[i].equalsIgnoreCase("-aid"))
				{
					try
					{
						accessID = args[++i];						
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -aid (accessID) argument " + ae1.toString());
					}
					ht.put("AccessID", accessID);
				}
				else if ( args[i].equalsIgnoreCase("-gblock"))
				{
					try
					{
						generalBlock = args[++i];						
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -gblck (generalBlock) argument " + ae1.toString());
					}
					ht.put("GeneralBlock", generalBlock);
				}
				else if ( args[i].equalsIgnoreCase("-mblock"))
				{
					try
					{
						messagePayLoadBlock = args[++i];						
					}
					catch(ArrayIndexOutOfBoundsException ae1)
					{
						System.err.println("Exception in parsing the -mblck (messagePayLoadBlock) argument " + ae1.toString());
					}
					ht.put("MessagePayLoadBlock", messagePayLoadBlock);
				}
			}
		}
		
		if(userName == null) 
		{
			System.out.println("\n User name not given, use the -u option.");
			printUsage();
		}
		   
		if(password == null)
		{
			System.out.println("\n Password not given, use the -P option.");
			printUsage();
		}
		if(port == null)
		{
			System.out.println("\n ConfigServer port not given, use the -p option.");
			printUsage();
		}
		if(deviceName == null)
		{
			System.out.println("\n Device name not given, use the -D option.");
			printUsage();
		}
		if(taskName == null)
		{
			System.out.println("\n Task name not given, use the -t option.");
			printUsage();
		}
		
		ht.put("Debug", debug);
		ht.put("UserName", userName);
		ht.put("Password", password);
		ht.put("HostName", hostName);
		ht.put("Port", port);
		ht.put("DeviceName", deviceName);		
		ht.put("TL1Port", tl1Port);
		ht.put("TaskName", taskName);
		ht.put("Overwrite", isOverwrite);
		ht.put("Rollback", isRollbackNeeded);
		
		return ht;
	}

	// Does the Configuration
    private void configure (Hashtable ptab)
    {
        // This class creates a task.
        TaskGenerator taskGenerator = new TaskGenerator();
		
        //Name of the task
        String taskName = (String) ptab.get ("TaskName");
		
        taskGenerator.setTaskName (taskName);
		taskGenerator.setProtocol("tl1");

        //whether the task should be overwritten or not
        boolean overwrite;
        String overw = (String) ptab.get ("Overwrite");
        if (overw.equals ("true"))
		{
			overwrite = true;
		}
        else
        {
            overwrite = false;
        }
        taskGenerator.setOverwrite (overwrite);
		
		//Whether the task should be executed sequentially		
		//Further implementation, when multiple devices are to be assigned
		
		//whether the task should be stored or not.      
		persistence = (String) ptab.get ("Persistence");		
		boolean per = true;
		if(persistence != null)
		{
			if (persistence.equals ("true"))
			{
				per = true;
			}
			else
			{
				per = false;
			}
		}
		taskGenerator.setPersistence(per);

        //whether the rollback is needed in case the task fails to execute
        String isRollbackNeeded = (String)ptab.get("Rollback");
		if(isRollbackNeeded != null)
		{
			boolean val;
			if(isRollbackNeeded.equals("true"))
			{
				val = true;
			}
			else
			{
				val = false;
			}
			taskGenerator.setRollback(val);
						
			String rtype = (String)ptab.get("RollbackType");
			if(rollbackType != null)
			{
				//rollBackType can be either 1 or 2 ie.,
				//1 is for Current Configuration,
				//2 is for Rollback Document
				int rollbackType = Integer.parseInt(rtype);
				if(rollbackType !=1 && rollbackType !=2)
				{
					System.out.println("Invalid Rollback type : Taking 1");
					rollbackType = 1;
				}
				//setting the rollback type
				taskGenerator.setRollbackType(rollbackType);
			}	
			
			//rollBackDocument should be an already existing task
			String rollBackDocument=(String)ptab.get("RollbackDocument");
			taskGenerator.setRollbackDocument(rollBackDocument);
		}
		
        String cmd = (String)ptab.get("CommandCode");
        if (cmd == null)
        {
            taskGenerator.setNewTask (false);
        }
        else
        {
            taskGenerator.setNewTask (true);

            //Getting the TL1Attribute
			String tgtID = (String)ptab.get ("TargetID");
            String accID = (String)ptab.get ("AccessID");
			String genBlock = (String)ptab.get ("GeneralBlock");
            String msgPayLdBlock = (String)ptab.get ("MessagePayLoadBlock");

            //Adds the TL1Attribute
            TL1Attribute tl1Attr = new TL1Attribute(cmd, tgtID, accID, genBlock, msgPayLdBlock);
			taskGenerator.addAttribute(tl1Attr);

        }

        deviceName = (String) ptab.get ("DeviceName");
		tl1Port = (String) ptab.get ("TL1Port");
        //setting the devices for which the task is to be executed
        Device[] device = new Device[1];
		device[0] = new Device(deviceName, tl1Port);
		taskGenerator.setDevices(device);
			
        //port where the ConfigServer is listening.
        port = (String) ptab.get ("Port");
        
        // Name of the configure user.
        userName = (String) ptab.get ("UserName");
		
		// password for the configure user.
        password = (String) ptab.get ("Password");
		
        //HostName where the ConfigServer is running
        hostName = (String) ptab.get ("HostName");
		debug = (String) ptab.get("Debug");		
		if(debug.equalsIgnoreCase("true"))
        {
            System.out.println (taskGenerator.getTask() + "\n");
        }

        try
        {
            // Getting the ConfigClient instance, which communicates with the ConfigServer.
            configClient = ConfigClient.getInstance("com.adventnet.management.config.DefaultConfigClientImpl", hostName, port, userName, password);

            // Registering the listener which in turn returns a UniqueID, which should
            // be used for sending the data. This uniqueID is used by the
            // client and ConfigServer
            String uniqueID = configClient.registerResultListener (tl1ConfigSet);

            configClientAPI = new ConfigClientAPI (configClient, uniqueID);

            //For executing the task, which in turn calls the ConfigServer's executeTask method
            configClientAPI.executeTask (taskGenerator.getTask ());
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
	}
		
	// For receiving the events from the ConfigServer.
    public void setResult (ConfigResultEvent cre)
    {
        DeviceResult[] result = cre.getConfigResult();
        if (result != null)
        {
            if (debug.equalsIgnoreCase("true"))
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
				String deviceName = result[i].getDeviceName();
                String taskName = result[i].getTaskName();
                AttributeResult[] attrResult = result[i].getAttributeResults();
                if(attrResult == null)
                {
                    if(!result[i].isConfigurationAttempted())
                        System.out.println("Device : " + deviceName + " - Configuration Ignored");
                }
                else
                {
                    for (int j = 0; j < attrResult.length; j++)
                    {
                        if (attrResult[j].getAttributeType () != AttributeConstants.GROUP_ATTRIBUTE)
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
        configClient.deregisterResultListener (tl1ConfigSet);

        configClient.close ();

        //Exiting after everything is over.
        System.exit(0);
    }
	
	private void printUsage()
	{
		System.out.println("\n" + Usage);
		System.exit(0);
	}	
}
