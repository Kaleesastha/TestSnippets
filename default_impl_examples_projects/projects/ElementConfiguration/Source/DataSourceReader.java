//$Id: DataSourceReader.java,v 1.1 2006/08/29 13:56:51 build Exp $
package com.adventnet.nms.config;

import java.util.*;

import com.adventnet.management.config.*;
import com.adventnet.management.config.xml.*;

public class DataSourceReader
{
	private String dataSourceName = null;

	private String dataSourceDesc = null;

	private String[] associatedTasks = null;

	private Vector dataSourceParams = null;

	private Hashtable dataSourceAttributes = null; 
	
	public DataSourceReader()
	{
	}

	public DataSourceReader(String dataSource)
	{
		parse(dataSource);	
	}

	public String getDataSourceName()
	{
		return dataSourceName;
	}

	public String getDataSourceDesc()
	{
		return dataSourceDesc;
	}

	public String[] getAssociatedTasks()
	{
		return associatedTasks;
	}

	public Hashtable getDataSourceAttributes()
	{
		return dataSourceAttributes;
	}

	public Vector getDataSourceParams()
	{
		return dataSourceParams;
	}

	private void parse(String dataSource)
	{
		try{

			DataSource reader = new DataSource(dataSource);

			dataSourceName = reader.getDataSourceName();

			dataSourceDesc = reader.getDescription();

			associatedTasks = reader.getAssociatedTasks();

			dataSourceParams = new Vector();

			dataSourceAttributes = new Hashtable();

			NEInput[] neInputs = reader.getNEInputArr();

			if(neInputs != null)
			{
				Vector neInputsVec = new Vector();

				for(int i=0; i<neInputs.length; i++)
				{
					NEInput neInput = neInputs[i];

					Vector neInputVec = new Vector();

					neInputVec.addElement(neInput.getID());

					Attribute[] attributes = neInput.getAttributes();

					if(attributes != null && attributes.length > 0)
					{
						neInputVec.addElement(attributes[0]);
						
						ConfigTask task = neInput.getTask();
						
						DeviceListReader deviceParser = new DeviceListReader(task.getProtocol(), task.getDevices());

						Vector devices = deviceParser.getDevices();
						
						if(devices != null && devices.size() > 0)
						{
							Vector deviceAttributes = (Vector)devices.elementAt(0);

							neInputVec.addElement(deviceAttributes);

							if(deviceAttributes != null)
							{
								for(int j=0; j<deviceAttributes.size(); j++)
								{
									addDataSourceParam((String)deviceAttributes.elementAt(j));
								}
							}
						}
						else
						{
							neInputVec.addElement(null);
						}

						neInputVec.addElement(attributes[0].getAttribute("default"));
						
						addDataSourceParam(attributes[0].getAttribute("default"));
					}
					else
					{
						neInputVec.addElement(null);
						neInputVec.addElement(null);
						neInputVec.addElement(null);
					}

					neInputsVec.addElement(neInputVec);
				}

				dataSourceAttributes.put("NEInput", neInputsVec);
			}

			InventoryInput[] inventoryInputs = reader.getInventoryInputArr();
			
			if(inventoryInputs != null)
			{
				Vector inventoryInputsVec = new Vector();

				for(int i=0; i<inventoryInputs.length; i++)
				{
					InventoryInput inventoryInput = inventoryInputs[i];
					
					Vector inventoryInputVec = new Vector();

					inventoryInputVec.addElement(inventoryInput.getID());
					inventoryInputVec.addElement(inventoryInput.getMOName());
					inventoryInputVec.addElement(inventoryInput.getMOField());
					inventoryInputVec.addElement(inventoryInput.getAttribute("default"));

					addDataSourceParam(inventoryInput.getMOName());
					addDataSourceParam(inventoryInput.getMOField());
					addDataSourceParam(inventoryInput.getAttribute("default"));
					
					inventoryInputsVec.addElement(inventoryInputVec);
				}

				dataSourceAttributes.put("InventoryInput", inventoryInputsVec);
			}
	
			UserInput[] userInputs = reader.getUserInputArr();
			
			if(userInputs != null)
			{
				Vector userInputsVec = new Vector();

				for(int i=0; i<userInputs.length; i++)
				{
					UserInput userInput = userInputs[i];
					
					Vector userInputVec = new Vector();

					userInputVec.addElement(userInput.getID());
					userInputVec.addElement(userInput.getValue());

					addDataSourceParam(userInput.getValue());
					
					userInputsVec.addElement(userInputVec);
				}

				dataSourceAttributes.put("UserInput", userInputsVec);
			}
		

		}catch(Exception ex)
		{
			System.out.println("Exception occured during data source parsing");
			ex.printStackTrace();
		}
	}

	private void addDataSourceParam(String dataSourceParam)
	{
		if(dataSourceParam != null && dataSourceParam.startsWith("$DataSourceParam$"))
		{
			dataSourceParams.addElement(dataSourceParam.substring(dataSourceParam.lastIndexOf('$')+1));
		}
	}
}

