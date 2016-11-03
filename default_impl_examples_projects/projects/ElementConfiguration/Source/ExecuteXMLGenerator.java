//$Id: ExecuteXMLGenerator.java,v 1.1 2006/08/29 13:56:51 build Exp $
package com.adventnet.nms.config;

import java.util.*;

import com.adventnet.management.config.*;
import com.adventnet.management.config.xml.*;

public class ExecuteXMLGenerator
{
	private String taskName = null;

	private String protocol = null;

	private Vector deviceListNames = null; 

	private Vector devices = null;

	private boolean isTemplate = false;

	private String dataSourceName = null;

	private boolean isCombined = false;

	private Vector subTaskNames = null;

	//private Vector combinedDevices = null;

	private Vector combinedDeviceListNames = null;

	public ExecuteXMLGenerator()
	{
	}

	public ExecuteXMLGenerator(String taskName)
	{
		this.taskName = taskName;
	}

	public void setTaskName(String taskName)	
	{
		this.taskName = taskName;
	}

	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	public void setDeviceListNames(Vector deviceListNames)
	{
		this.deviceListNames = deviceListNames;
	}

	public void setDevices(Vector devices) 
	{
		this.devices = devices;
	}

	public void setDataSourceName(String dataSourceName)
	{
		isTemplate = true;

		this.dataSourceName = dataSourceName;
	}

	/*public void setDevices(Vector subTaskNames, Vector combinedDevices)
	  {
	  isCombined = true;

	  this.subTaskNames = subTaskNames;
	  this.combinedDevices = combinedDevices;
	  }*/

	public void setDeviceListNames(Vector subTaskNames, Vector combinedDeviceListNames)
	{
		isCombined = true;

		this.subTaskNames = subTaskNames;
		this.combinedDeviceListNames = combinedDeviceListNames;
	}

	public String getExecuteXML()
	{
		TaskGenerator xmlGen = new TaskGenerator();

		xmlGen.setNewTask(false);
		xmlGen.setTaskName(taskName);

		if(isCombined)
		{
			xmlGen.setDeviceList(subTaskNames, combinedDeviceListNames);
		}
		else
		{
			xmlGen.setProtocol(protocol);

			if(isTemplate)
			{
				xmlGen.setDataSourceName(dataSourceName);
			}

			if(deviceListNames != null)
			{
				String[] strArr = new String[deviceListNames.size()];

				for(int i=0; i<deviceListNames.size(); i++)
				{
					strArr[i] = (String)deviceListNames.elementAt(i);
				}

				xmlGen.setDeviceList(strArr);
			}

			if(devices != null)
			{
				DeviceListGenerator deviceListGenerator = new DeviceListGenerator();

				deviceListGenerator.setProtocol(protocol);
				deviceListGenerator.setDevices(devices);

				xmlGen.setDevices(deviceListGenerator.getDevices());
			}
		}

		return xmlGen.getTask();
	}
}
