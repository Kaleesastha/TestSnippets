//$Id: StandAloneResponseHandler.java,v 1.1 2006/08/29 13:56:51 build Exp $

package com.adventnet.nms.config;

import java.awt.*;

import java.util.*;

import java.io.*;

import com.adventnet.nms.util.PureClientUtils;

import com.adventnet.nms.failover.*;

import com.adventnet.management.config.*;


public class StandAloneResponseHandler implements ResponseHandler
{
	private ConfigPanel configPanel = null;
	
	private StandAloneInterfaceImpl standAloneInterface = null;

	public void init(ConfigPanel configPanel)
	{
		this.configPanel = configPanel;

		standAloneInterface = new StandAloneInterfaceImpl(configPanel.applet);		
	}

	public String sendRequest(int id, Object[] parameters, ConfigResponseListener configResponseListener)
	{
		StandAloneThread sat = new StandAloneThread(id, parameters, configResponseListener);
		sat.start();
		return null;
	}

	private byte[] getByteArray(Object obj)
	{
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try
		{
			baos=new ByteArrayOutputStream();
			oos=new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.flush();
			return baos.toByteArray();
		}
		catch(Exception ex)
		{
		}
		finally
		{
			try
			{
				baos.close();
				oos.close();
			}
			catch(Exception ex)
			{
			}
		}
		return null;
	}


	public byte[] getByteArrayWithName(String taskName, String task)
	{
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try
		{
			baos=new ByteArrayOutputStream();
			oos=new ObjectOutputStream(baos);
			oos.writeUTF(taskName);
			oos.writeInt(task.length());
			oos.write(task.getBytes());
			oos.flush();
			return baos.toByteArray();
		}
		catch(Exception ex)
		{
		}
		finally
		{
			try
			{
				baos.close();
				oos.close();
			}
			catch(Exception ex)
			{
			}
		}
		return null;
	}


	class StandAloneThread extends Thread
	{
		int id;
		Object[] parameters;
		ConfigResponseListener configResponseListener;
		public StandAloneThread(int id, Object[] parameters, ConfigResponseListener configResponseListener)
		{
			this.id = id;
			this.parameters = parameters;
			this.configResponseListener = configResponseListener;
		}

		public void run()
		{
			try
			{
				if(id == NmsConfigConstants.SAVE_TASK)
				{
					standAloneInterface.saveTask((String)parameters[0]);
					ConfigResultEvent cre = new ConfigResultEvent(id, null, null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.GET_PROTOCOL_SPECIFIC_TASKS)
				{
				}
				else if(id == NmsConfigConstants.GET_TASK)
				{
					String taskName = (String)parameters[0];
					String taskXml = standAloneInterface.getTask(taskName);
					ConfigResultEvent cre = new ConfigResultEvent(id, getByteArrayWithName(taskName, taskXml), null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.GET_TASKS)
				{
					try
					{
						Vector tasks = standAloneInterface.getTasks();
						ConfigResultEvent cre = new ConfigResultEvent(id, getByteArray(tasks), null);
						configResponseListener.response(cre);
					}
					catch(Exception ex)
					{
					}
				}
				else if(id == NmsConfigConstants.DELETE_TASK)
				{
					standAloneInterface.deleteTask((String)parameters[0]);
				}
				else if(id == NmsConfigConstants.GET_AUDIT_FOR_TASK)
				{
				}
				else if(id == NmsConfigConstants.GET_MO_WITH_CRITERIA)
				{
				}
				else if(id == NmsConfigConstants.GET_MAX_COUNT_OF_MO_SUPPORTED)
				{
				}
				else if(id == NmsConfigConstants.GET_DEVICE_PROPERTIES_FETCH_LIMIT)
				{
				}
				else if(id == NmsConfigConstants.SERVER_DEBUG)
				{
				}
				else if(id == NmsConfigConstants.GET_COMBINED_TASKS)
				{
				}
				else if(id == NmsConfigConstants.SAVE_DEVICELIST)
				{
					standAloneInterface.saveDeviceList((String) parameters[0]);
					ConfigResultEvent cre = new ConfigResultEvent(id, null, null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.GET_DEVICELIST)
				{
					String deviceList = standAloneInterface.getDeviceList((String) parameters[0]);
					ConfigResultEvent cre = new ConfigResultEvent(id, getByteArrayWithName( (String) parameters[0], deviceList), null);
					configResponseListener.response(cre);

				}
				else if(id == NmsConfigConstants.GET_DEVICELIST_NAMES)
				{
				}
				else if(id == NmsConfigConstants.GET_PROTOCOL_SPECIFIC_DEVICELIST_NAMES)
				{
					Vector toReturn = standAloneInterface.getProtocolSpecificDeviceLists((String) parameters[0]);
					ConfigResultEvent cre = new ConfigResultEvent(id, getByteArray(toReturn), null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.GET_ALL_NETWORKS)
				{
				}
				else if(id == NmsConfigConstants.DELETE_DEVICELIST)
				{
					standAloneInterface.deleteDeviceList((String) parameters[0]);
					ConfigResultEvent cre = new ConfigResultEvent(id, null, null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.DELETE_DEVICELISTMAP)
				{
					standAloneInterface.deleteDeviceListMap((String) parameters[0]);
					ConfigResultEvent cre = new ConfigResultEvent(id, null, null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.GET_DEVICELIST_FOR_TASK)
				{
					Vector toReturn = standAloneInterface.getTaskSpecificDeviceLists((String) parameters[0]);
					ConfigResultEvent cre = new ConfigResultEvent(id, getByteArray(toReturn), null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.SAVE_DEVICELISTMAP)
				{
					String taskName = (String)parameters[0];
					String[] deviceListNames = (String[]) parameters[1];
					Vector deviceLists = new Vector(deviceListNames.length);
					for(int i = 0; i < deviceListNames.length; i++)
						deviceLists.add(deviceListNames[i]);	
					standAloneInterface.saveDeviceListMap(taskName,deviceLists);
					ConfigResultEvent cre = new ConfigResultEvent(id, null, null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.EXECUTE)
				{
				}
				else if(id == NmsConfigConstants.UPLOAD_CONFIGURATION)
				{
				}
				else if(id == NmsConfigConstants.GET_LASTEXECUTED_TIME)
				{
					ConfigResultEvent cre = new ConfigResultEvent(id, null, null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.GET_TASK_AUDIT)
				{
				}
				else if(id == NmsConfigConstants.GET_DEVICE_AUDIT_FOR_DEVICELIST)
				{
				}
				else if(id == NmsConfigConstants.GET_SPECIFIC_ATTRIBUTE_AUDIT)
				{
				}
				else if(id == NmsConfigConstants.GET_ATTRIBUTE_AUDIT_FOR_ID)
				{
				}
				else if(id == NmsConfigConstants.SAVE_DATASOURCE)
				{
					standAloneInterface.saveDataSource((String) parameters[0]);
					ConfigResultEvent cre = new ConfigResultEvent(id, null, null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.DELETE_DATASOURCE)
				{
					standAloneInterface.deleteDataSource((String) parameters[0]);
					ConfigResultEvent cre = new ConfigResultEvent(id, null, null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.GET_DATASOURCE)
				{
					String dataSource = standAloneInterface.getDataSource((String) parameters[0]);
					ConfigResultEvent cre = new ConfigResultEvent(id, getByteArrayWithName( (String) parameters[0], dataSource) , null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.GET_ASSOCIATED_DATASOURCES)
				{
					String[] dataSources = standAloneInterface.getAssociatedDataSource((String) parameters[0]);
					
					Vector dataSourceNames = new Vector(dataSources.length);
					for(int i = 0; i < dataSources.length; i++)
						dataSourceNames.add(dataSources[i]);	

					ConfigResultEvent cre = new ConfigResultEvent(id, getByteArray(dataSourceNames) , null);
					configResponseListener.response(cre);
				}
				else if(id == NmsConfigConstants.EXECUTE_TASK_WITH_DATASOURCE)
				{
				}
			}
			catch(Exception e)
			{
			}
		}
	}
}
