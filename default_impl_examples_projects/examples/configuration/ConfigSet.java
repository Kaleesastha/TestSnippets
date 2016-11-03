//$Id: ConfigSet.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/*
 * @(#)ConfigSet.java	
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
import com.adventnet.management.config.snmp.SnmpTaskGenerator;
import com.adventnet.management.config.snmp.SnmpDevice;
import com.adventnet.management.config.ConfigClient;
import com.adventnet.management.config.ConfigResultListener;
import com.adventnet.management.config.ConfigResultEvent;
import com.adventnet.management.config.xml.DeviceResult;
import com.adventnet.management.config.AttributeConstants;
import com.adventnet.management.config.xml.AttributeResult;
import com.adventnet.management.config.ConfigConstants;
import com.adventnet.management.config.ConfigClientAPI;

// java imports
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Hashtable;

// Configuration through ConfigServer
/**
 * This example uses com.adventnet.management.config.ConfigClient for 
 * sending the Configuration data to the ConfigServer.
 * To do the Configuration the user has to run the ConfigServer 
 * or NMS Server ( Which internally runs the ConfigServer) .
 * ( Execute java com.adventnet.management.config.ConfigServer <Port> 
 *  to start the ConfigServer)
 * This class implement com.adventnet.management.config.ConfigResultListener 
 * for getting responses from ConfigServer.
 */

public class ConfigSet implements ConfigResultListener
{
    ConfigClient configClient;
    ConfigClientAPI configClientAPI;
    Hashtable pt;
    static ConfigSet configSet;

    private void configureDev (String[]args)
    {
        ConfigureParams cpa = new ConfigureParams ();
        String diff = "ConfigSet";
        pt = cpa.getParams (diff, args);
        configure (pt);
    }

    // Does the Configuration
    private void configure (Hashtable ptab)
    {
        // This class create a task.
        SnmpTaskGenerator taskGenerator = new SnmpTaskGenerator ();

        //Name of the task
        String taskName = (String) ptab.get ("taskName");
        taskGenerator.setTaskName (taskName);

        //whether the task should be overwritten or not
        boolean overwrite;
        String overw = (String) ptab.get ("overwrite");
        if (overw.equals ("true"))
        {
            overwrite = true;
        }
        else
        {
            overwrite = false;
        }
        taskGenerator.setOverwrite (overwrite);

        //whether the task should be executed sequentially
        boolean sequential;
        String seq = (String) ptab.get ("sequential");
        if (seq.equals ("true"))
        {
            sequential = true;
        }
        else
        {
            sequential = false;
        }
        taskGenerator.setSequential (sequential);

        //whether the rollback is needed in case the task fails to execute
        String isRollBackNeeded=(String)ptab.get("isRollBackNeeded");
        boolean val;
        if(isRollBackNeeded.equals("true"))
        {
            val=true;
        }
        else
        {
            val=false;
        }
        taskGenerator.setRollback(val);
        
        String rtype=(String)ptab.get("rollBackType");
        
        //rollBackType can be either 1 or 2 ie
        //1 is for Current Configuration and 2 is for
        //Rollback Document
        int rollBacktype=Integer.parseInt(rtype);
        if(rollBacktype !=1 && rollBacktype !=2)
        {
            System.out.println("Invalid Rollback type : Taking 1");
            rollBacktype = 1;
        }
        //setting the rollback type
        taskGenerator.setRollbackType(rollBacktype);

        //rollBackdocument should be an already existing task
        String rollBackDocument=(String)ptab.get("rollBackDocument");
        taskGenerator.setRollbackDocument(rollBackDocument);

        //whether the task should be stored or not.
        boolean persistence;
        String per = (String) ptab.get ("persistence");
        if (per.equals ("true"))
        {
            persistence = true;
        }
        else
        {
            persistence = false;
        }
        taskGenerator.setPersistence( persistence );
      
        String oid[] = (String[])ptab.get ("oid");
        if (oid == null)
        {
            taskGenerator.setNewTask (false);
        }
        else
        {
            taskGenerator.setNewTask (true);

            //getting the SnmpAttributes
            String[]label = (String[])ptab.get ("label");
            String[] type = (String[])ptab.get ("type");
            String value[] = (String[])ptab.get ("value");

            //Adds all the Snmp Attributes
            taskGenerator.addAttributes (oid, label, type, value);
            // For adding a row ,Use addRowAttribute(String tableOID, String[] columnOidList, byte[] columnTypeList, String[] columnValueList, String index)
            // method in  SnmpTaskGenerator class.
        }
        SnmpDevice[]device = (SnmpDevice[])ptab.get ("device");

        //setting the devices for which the task is to be executed
        taskGenerator.setDevices (device);

        //port where the ConfigServer is listening.
        String port = (String) ptab.get ("port");
        
        // Name of the configure user.
        String userName = (String) ptab.get ("UserName");
        
        // password for the configure user.
        String password = (String) ptab.get ("PassWord");

        //HostName where the ConfigServer is running
        String hostName = (String) ptab.get ("hostName");

        if (ConfigParserOptions.DEBUG)
        {
            System.out.println (taskGenerator.getTask ()+"\n");
        }
        try
        {
            // Getting the ConfigClient Instance,which communicates with the ConfigServer.
            configClient = ConfigClient.getInstance("com.adventnet.management.config.DefaultConfigClientImpl", hostName,port, userName, password);

            // Registering the listener which in turn returns an UniqueID, which should
            // be used for sending the data.This is the uniqueID which is used by the
            // client and ConfigServer
            String uniqueID = configClient.registerResultListener (configSet);

            configClientAPI = new ConfigClientAPI (configClient, uniqueID);

            //For executing a task which in turn calls the ConfigServer's executeTask method
            configClientAPI.executeTask (taskGenerator.getTask ());
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }

    public static void main (String args[])
    {
        configSet = new ConfigSet ();
        configSet.configureDev (args);
    }

    // For receiving the events from the ConfigServer.
    public void setResult (ConfigResultEvent cre)
    {
        DeviceResult[] result = cre.getConfigResult ();
        if (result != null)
        {
            if (ConfigParserOptions.DEBUG)
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
        configClient.deregisterResultListener (configSet);

        configClient.close ();

        //exitting after everything is over.
        System.exit(0);
    }

}
