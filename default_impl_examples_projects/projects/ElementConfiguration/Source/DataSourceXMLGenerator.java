//$Id: DataSourceXMLGenerator.java,v 1.1 2006/08/29 13:56:51 build Exp $
package com.adventnet.nms.config;

import java.util.*;

import com.adventnet.management.config.*;
import com.adventnet.management.config.xml.*;

public class DataSourceXMLGenerator
{
	private String dataSourceName = null;

	private String dataSourceDesc = null;

	private String associatedTemplate = null;

	private String templateProtocol = null;

	private Hashtable dataSourceAttributes = null;

	public DataSourceXMLGenerator()
	{
	}

	public DataSourceXMLGenerator(String dataSourceName)
	{
		this.dataSourceName = dataSourceName;
	}

	public DataSourceXMLGenerator(String dataSourceName, String dataSourceDesc)
	{
		this.dataSourceName = dataSourceName;
		this.dataSourceDesc = dataSourceDesc;
	}

	public void setDataSourceName(String dataSourceName)
	{
		this.dataSourceName = dataSourceName;
	}

	public void setDataSourceDesc(String dataSourceDesc)
	{
		this.dataSourceDesc = dataSourceDesc;
	}

	public void setAssociatedTemplate(String associatedTemplate)
	{
		this.associatedTemplate = associatedTemplate;
	}

	public void setTemplateProtocol(String templateProtocol)
	{
		this.templateProtocol = templateProtocol;
	}

	public void setDataSourceAttributes(Hashtable dataSourceAttributes)	
	{
		this.dataSourceAttributes = dataSourceAttributes;
	}

	public String getDataSource()
	{
		try
		{
			DataSourceGenerator dataSourceGenerator = new DataSourceGenerator();

			dataSourceGenerator.setDataSourceName(dataSourceName);
			dataSourceGenerator.setDescription(dataSourceDesc);

			String[] associatedTasks = {associatedTemplate};

			dataSourceGenerator.setAssociatedTasks(associatedTasks);

			if(dataSourceAttributes != null)
			{
				Vector NEInputs = (Vector)dataSourceAttributes.get("NEInput");

				if(NEInputs != null)
				{
					for(int i=0; i<NEInputs.size(); i++)
					{
						Vector NEInput = (Vector)NEInputs.elementAt(i);

						String taskXML = getTaskWithDevice((Attribute)NEInput.elementAt(1), (Vector)NEInput.elementAt(2), (String)NEInput.elementAt(3));

						dataSourceGenerator.addNEInput((String)NEInput.elementAt(0), taskXML);
					}
				}

				Vector InventoryInputs = (Vector)dataSourceAttributes.get("InventoryInput");

				if(InventoryInputs != null)
				{
					for(int i=0; i<InventoryInputs.size(); i++)
					{
						Vector InventoryInput = (Vector)InventoryInputs.elementAt(i);

						dataSourceGenerator.addInventoryInput((String)InventoryInput.elementAt(0), (String)InventoryInput.elementAt(1), (String)InventoryInput.elementAt(2), (String)InventoryInput.elementAt(3));
					}
				}

				Vector UserInputs = (Vector)dataSourceAttributes.get("UserInput");

				if(UserInputs != null)
				{
					for(int i=0; i<UserInputs.size(); i++)
					{
						Vector UserInput = (Vector)UserInputs.elementAt(i);

						dataSourceGenerator.addUserInput((String)UserInput.elementAt(0), "", (String)UserInput.elementAt(1));
					}
				}

			}

			return dataSourceGenerator.getDataSource();
		}catch(Exception ex)
		{
			return null;
		}
	}

	private String getTaskWithDevice(Attribute attribute, Vector deviceAttributes, String defaultValue)
	{
		TaskGenerator xmlGen = new TaskGenerator();

		xmlGen.setProtocol(templateProtocol);

		if(defaultValue != null)
		{
			attribute.setAttribute("default", defaultValue);
		}

		xmlGen.addAttribute(attribute);

		if(deviceAttributes != null)
		{
			Vector devices = new Vector();

			devices.addElement(deviceAttributes);

			DeviceListGenerator deviceListGenerator = new DeviceListGenerator();

			deviceListGenerator.setProtocol(templateProtocol);
			deviceListGenerator.setDevices(devices);

			xmlGen.setDevices(deviceListGenerator.getDevices());
		}

		return xmlGen.getTask();
	}

	public String getDataSource(Properties prop)
	{
		if(prop != null)
		{
			setDataSourceName((String)prop.get("name"));

			setDataSourceDesc((String)prop.get("description"));

			setAssociatedTemplate((String)prop.get("template"));

			setTemplateProtocol((String)prop.get("protocol"));

			setDataSourceAttributes((Hashtable)prop.get("attributes"));

			return getDataSource();
		}
		else
		{
			return null;
		}
	}
}
