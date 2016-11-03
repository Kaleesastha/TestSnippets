//$Id: ConfigResponseHandler.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $

package com.adventnet.nms.config;

import java.awt.*;

import java.util.*;

import com.adventnet.nms.util.PureClientUtils;

import com.adventnet.nms.failover.*;

import com.adventnet.management.config.*;


public class ConfigResponseHandler implements ConfigResultListener, FailOverListener, ResponseHandler
{
	private ConfigPanel configPanel = null;

	private ConfigClientAPI configClientAPI = null;

	private Hashtable uniqueIDVsListener = new Hashtable();

	private Hashtable componentVsRequests = new Hashtable();

	private Vector requestsExceptExeReq = new Vector();

	public void init(ConfigPanel configPanel) throws Exception
	{
		this.configPanel = configPanel;

		initializeConfigClientSession();
	}

	private void initializeConfigClientSession() throws Exception
	{
		ConfigClient configClient = configPanel.configClientUtils.getConfigClientInstance();

		PureClientUtils.addFailOverListener(this, PureClientUtils.MAIN_SOCKET);

		String uniqueID = configClient.registerResultListener(this);

		configClientAPI = new ConfigClientAPI(configClient, uniqueID);

		configPanel.userName = configClient.getUserName();
	}

	public String sendRequest(int id, Object[] parameters, ConfigResponseListener configResponseListener)
	{
		try{
			String uniqueID = null;

			if(id == NmsConfigConstants.SAVE_TASK)
			{
				uniqueID = configClientAPI.saveTask((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_PROTOCOL_SPECIFIC_TASKS)
			{
				uniqueID = configClientAPI.requestTasks((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_TASK)
			{
				uniqueID = configClientAPI.requestTask((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_TASKS)
			{
				uniqueID = configClientAPI.requestTasks();
			}
			else if(id == NmsConfigConstants.DELETE_TASK)
			{
				uniqueID = configClientAPI.deleteTask((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_AUDIT_FOR_TASK)
			{
				uniqueID = configClientAPI.requestAuditForTask((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_MO_WITH_CRITERIA)
			{
				uniqueID = configClientAPI.requestManagedObjects((Properties)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_MAX_COUNT_OF_MO_SUPPORTED)
			{
				uniqueID = configClientAPI.requestMaxCountOfMOSupported();
			}
			else if(id == NmsConfigConstants.GET_DEVICE_PROPERTIES_FETCH_LIMIT)
			{
				uniqueID = configClientAPI.requestDevicePropertiesFetchLimit();
			}
			else if(id == NmsConfigConstants.SERVER_DEBUG)
			{
				//uniqueID = configClientAPI.setDebug();
			}
			else if(id == NmsConfigConstants.GET_COMBINED_TASKS)
			{
				uniqueID = configClientAPI.requestCombinedTasks();
			}
			else if(id == NmsConfigConstants.SAVE_DEVICELIST)
			{
				uniqueID = configClientAPI.saveDeviceList((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_DEVICELIST)
			{
				uniqueID = configClientAPI.requestDeviceList((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_DEVICELIST_NAMES)
			{
				uniqueID = configClientAPI.requestDeviceListNames();
			}
			else if(id == NmsConfigConstants.GET_PROTOCOL_SPECIFIC_DEVICELIST_NAMES)
			{
				uniqueID = configClientAPI.requestDeviceListNames((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_ALL_NETWORKS)
			{
				uniqueID = configClientAPI.requestAllNetworks();
			}
			else if(id == NmsConfigConstants.DELETE_DEVICELIST)
			{
				uniqueID = configClientAPI.deleteDeviceList((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.DELETE_DEVICELISTMAP)
			{
				uniqueID = configClientAPI.deleteDeviceListMap((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_DEVICELIST_FOR_TASK)
			{
				uniqueID = configClientAPI.requestDeviceListForTask((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.SAVE_DEVICELISTMAP)
			{
				uniqueID = configClientAPI.saveDeviceListMap((String)parameters[0], (String[])parameters[1]);
			}
			else if(id == NmsConfigConstants.EXECUTE)
			{
				uniqueID = configClientAPI.executeTask((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.UPLOAD_CONFIGURATION)
			{
				uniqueID = configClientAPI.requestDeviceConfiguration((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_LASTEXECUTED_TIME)
			{
				uniqueID = configClientAPI.requestLastExecutedTime((String[])parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_TASK_AUDIT)
			{
				uniqueID = configClientAPI.requestTaskAudit((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_DEVICE_AUDIT_FOR_DEVICELIST)
			{
				uniqueID = configClientAPI.requestDeviceAudit(Integer.parseInt((String)parameters[0]),(String)parameters[1]);
			}
			else if(id == NmsConfigConstants.GET_SPECIFIC_ATTRIBUTE_AUDIT)
			{
				uniqueID = configClientAPI.requestSpecificAttributeAudit((String)parameters[0],(String)parameters[1]);
			}
			else if(id == NmsConfigConstants.GET_ATTRIBUTE_AUDIT_FOR_ID)
			{
				uniqueID = configClientAPI.requestAttributeAuditForID(Integer.parseInt((String)parameters[0]));                         }
			else if(id == NmsConfigConstants.SAVE_DATASOURCE)
			{
				uniqueID = configClientAPI.saveDataSource((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.DELETE_DATASOURCE)
			{
				uniqueID = configClientAPI.deleteDataSource((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_DATASOURCE)
			{
				uniqueID = configClientAPI.requestDataSource((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.GET_ASSOCIATED_DATASOURCES)
			{
				uniqueID = configClientAPI.requestAssociatedDataSources((String)parameters[0]);
			}
			else if(id == NmsConfigConstants.EXECUTE_TASK_WITH_DATASOURCE)
			{
				uniqueID = configClientAPI.executeTask((String)parameters[0], (Properties)parameters[1]);
			}
			else if(id == NmsConfigConstants.SAVE)
			{
				uniqueID = configClientAPI.saveConfigurationProfile((String)parameters[0], (String)parameters[1]);
			}

			uniqueIDVsListener.put(uniqueID, configResponseListener);

			if(id != NmsConfigConstants.EXECUTE && id != NmsConfigConstants.EXECUTE_TASK_WITH_DATASOURCE)
			{
				if(!(configResponseListener instanceof ConfigMainUI) && (configResponseListener instanceof Component))
				{
					if(componentVsRequests.containsKey(configResponseListener))
					{
						Vector value = (Vector)componentVsRequests.get(configResponseListener);

						value.addElement(uniqueID);
					}
					else
					{
						Vector value = new Vector();

						value.addElement(uniqueID);

						componentVsRequests.put(configResponseListener, value);					

						configPanel.configClientUtils.setBusyCursor((Component)configResponseListener);
					}
				}

				if(requestsExceptExeReq.size() == 0)
				{
					configPanel.configClientUtils.setBusyCursor(configPanel);
				}

				requestsExceptExeReq.addElement(uniqueID);
			}

			return uniqueID;
		}catch(Exception e){
			System.out.println("exception occured during request being send"+e); 

			return null;
		}
	}

	public void setResult(ConfigResultEvent configResultEvent)
	{
		int workID = configResultEvent.getWorkID();

		String uniqueID = configResultEvent.getRequestID();

		ConfigResponseListener configResponseListener = (ConfigResponseListener)uniqueIDVsListener.remove(uniqueID);

		Vector value = (Vector)componentVsRequests.get(configResponseListener); 

		if(value != null)
		{
			value.removeElement(uniqueID);

			if(value.size() == 0)
			{
				componentVsRequests.remove(configResponseListener);

				configPanel.configClientUtils.setNormalCursor((Component)configResponseListener);
			}
		}

		if(workID != NmsConfigConstants.EXECUTE && workID != NmsConfigConstants.EXECUTE_TASK_WITH_DATASOURCE)
		{
			requestsExceptExeReq.removeElement(uniqueID);	

			if(requestsExceptExeReq.size() == 0)
			{
				configPanel.configClientUtils.setNormalCursor(configPanel);
			}
		}

		configResponseListener.response(configResultEvent);
	}

	public void resetRequestDataModel()
	{
		for(Enumeration enumerate = componentVsRequests.keys(); enumerate.hasMoreElements();)
		{
			configPanel.configClientUtils.setNormalCursor((Component)enumerate.nextElement());
		}

		configPanel.configClientUtils.setNormalCursor(configPanel);

		uniqueIDVsListener.clear();

		componentVsRequests.clear();

		requestsExceptExeReq.removeAllElements();
	}

	//methods for FailOverListener

	public void preFailOverNotification(FailOverEvent failOverEvent)
	{
		resetRequestDataModel();	
	}

	public void postFailOverNotification(FailOverEvent failOverEvent)
	{

	}
}







