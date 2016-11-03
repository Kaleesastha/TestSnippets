/* $Id: DeviceConfigDemo.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/*
 * @(#)DeviceConfigDemo.java	
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

//Java Imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.util.Vector;
import java.util.Properties;

//NMS Imports
import com.adventnet.management.config.*;
import com.adventnet.management.config.snmp.SnmpTaskGenerator;
import com.adventnet.management.config.snmp.SnmpDevice;
import com.adventnet.nms.config.*;
import com.adventnet.management.config.xml.DeviceResult;
import com.adventnet.management.config.xml.AttributeResult;


import com.adventnet.nms.util.CustomClassInterface;
import com.adventnet.nms.util.NmsClientUtil;

/**
 * An example class which guides the user to use the Nms Client Configuration APIs to
 * build superior Configuration Management Screens.
 */
public class DeviceConfigDemo extends JFrame implements ActionListener, ConfigResultListener, CustomClassInterface
{
	Container container = null;
	ConfigurePanel conPanel;
	ResultPanel result;

	/**
	 * The Nms ConfigClient using the common socket connection.
	 */
	ConfigClient nmsClient = null;

	/**
	 * An unique ID that is used by client and the Configuration Server
	 * for communication.
	 */
	String uniqueID = null;

	public DeviceConfigDemo()
	{
		super(NmsClientUtil.GetString("Example Device Configuration Screen"));
		container = getContentPane();

		/**
		 * Setup the Configuration screens.
		 */
		conPanel = new ConfigurePanel(this);
		result = new ResultPanel(this);
		container.add(conPanel);

		setSize(800, 800);
		setResizable(false);
		pack();

		addWindowListener(new WindowAdapter ()
		{
			public void windowClosing (WindowEvent we)
			{
				setVisible (false);
				if(nmsClient != null)
				{
					/**
					 * Deregister the result listener.
					 */
					nmsClient.deregisterResultListener(uniqueID);

					/**
					 * Close the underlying resources.
					 */
					nmsClient.close();
				}
				dispose ();
			}
		});      

		try
		{
			/**
			 * Get the instance of the NmsConfigClient which internally uses the
			 * the common socket connection. Here the NmsConfigClient reuses the
			 * common socket connection and hence the serverName and port are passed
			 * as null
			 */
			nmsClient = ConfigClient.getInstance("com.adventnet.nms.config.NmsConfigClient", null, null,NmsClientUtil.getUserName(),"password");

			/**
			 * Register with the ConfigClient to get the responses from the Server.
			 * This uniqueID should be used for sending any data from the client to
			 * the Configuration server.
			 */
			uniqueID = nmsClient.registerResultListener(this);
		}
		catch(ConfigException ce)
		{
			JOptionPane.showMessageDialog(this, "Error in getting the ConfigClient ", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("Configure"))
		{
			if(conPanel.validateAttributeEntries())
			{
				/**
				 * This class is used for generating the Configuration information in
				 * xml format.
				 */
				SnmpTaskGenerator xmlGen = new SnmpTaskGenerator();
				
				/**
				 * Overrides the existing task if it is already present in the database.
				 */
				xmlGen.setOverwrite(true);

				/**
				 * Set the name of the task. By default, it takes "Task1" as the 
				 * task name.
				 */
				xmlGen.setTaskName(conPanel.getTaskName());

				/**
				 * Construct a device which needs to be configured with protocol
				 * parameters like port, community etc.
				 */
				SnmpDevice device =  new SnmpDevice(conPanel.getDeviceName(), "161", "public");

				/**
				 * Set the devices for Configuration.
				 */
				xmlGen.setDevices( new SnmpDevice[] { device } );

				/**
				 * Add all the snmp attributes entered in the text fields for configuration.
				 * For table configuration, the addRowAttribute method of SnmpTaskGenerator
				 * can be used.
				 */
				xmlGen.addAttributes(conPanel.getIdentifiers(), conPanel.getLabels(), conPanel.getTypes(), conPanel.getValues());

				/**
				 * Get the taskXML  of the generated Configuration Profiles.
				 */
				
				String taskXML = xmlGen.getTask();
				ConfigClientAPI clientAPI = new ConfigClientAPI(nmsClient,uniqueID);
				try
				{
					//Executes the task.
					clientAPI.executeTask(taskXML);
				}
				catch(Exception ce)
				{
					JOptionPane.showMessageDialog(this, "Error in sending data to the Config Server ", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				//Some UI manipulation stuff.
				container.remove(conPanel);
				container.add(result);
				result.updateUI();
			}
		}
	}

	/**
	 * This is the interface method of the ConfigResultListener class. This method will
	 * be called whenever there is a response for the request made by this client.
	 */
	public void setResult(ConfigResultEvent resultEvent)
	{
		DeviceResult[] deviceResults = resultEvent.getConfigResult();
		if(deviceResults != null)
		{
			for(int i = 0; i < deviceResults.length; i++)
			{
				String deviceName = deviceResults[i].getDeviceName();
				String taskName = deviceResults[i].getTaskName();
				AttributeResult[] resultAttributes = deviceResults[i].getAttributeResults();
				for(int j = 0; j < resultAttributes.length; j++)
				{
					if(resultAttributes[j].getAttributeType() != AttributeConstants.GROUP_ATTRIBUTE)
					{
						result.append("\nTaskName : " + taskName);
						result.append("\nDeviceName : " + deviceName);
						result.append("\nIdentifier : " + resultAttributes[j].getIdentifier());
						result.append("\nStatus : " + resultAttributes[j].getMessage() + "\n");
					}
				}
			}
		}
	}

	/**
	 * This is the interface method of CustomClassInterface which will be called by
	 * invokeClass method of NmsClient.
	 */
	public void setProperties(Properties[] props)
	{
		if(props != null)
		{
			String deviceName = props[0].getProperty("HOST");
			if(deviceName != null)
			{
				conPanel.setDevice(deviceName);
			}
		}
		setVisible(true);
	}
}
