//$Id: PolicyConfigurationSession.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.nms.runtimeconfig;


import com.adventnet.nms.util.ResultEventListener;
//import com.adventnet.nms.util.ResultEventAdapter;
import com.adventnet.nms.util.ResultEventObject;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.startnms.GenericConstants;

import java.io.*;

public class PolicyConfigurationSession implements ResultEventListener
{

	PolicyConfigurationModel policyConfigurationModel = null;
	XMLNode rootLogNode = null;

	public PolicyConfigurationSession(PolicyConfigurationModel policyConfModel)
	{
		policyConfigurationModel = policyConfModel;
		RunTimeConfigSession.getInstance().register(this);
	}

	public void send( int operation )
	{
		send( operation , null);
	}

	public void send( int operation , XMLNode modifyNode )
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream(byteStream);
			outp.writeUTF(policyConfigurationModel.getModuleId());
			outp.writeInt(GenericConstants.POLICY_CONF);
			outp.writeInt(operation);
			if(modifyNode != null)
			{
				byte[] serverData = NmsClientUtil.serializeObject(modifyNode );
				int len = serverData.length;
				outp.writeInt(len);
				outp.write(serverData,0,len);  
			}
			outp.flush();
			byte[] bytes = byteStream.toByteArray();
			outp.close();
			byteStream.reset();
			byteStream.close(); 
			RunTimeConfigSession.getInstance().send(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("PolicyConfigurationSession : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
	}

	//public XMLNode syncSend( int operation , XMLNode modifyNode )
	public byte[] syncSend( int operation , XMLNode modifyNode )
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream(byteStream);
			outp.writeUTF(policyConfigurationModel.getModuleId());
			outp.writeInt(GenericConstants.LOGGING_PARAMS);
			outp.writeInt(operation);
			if(modifyNode != null)
			{
				byte[] serverData = NmsClientUtil.serializeObject(modifyNode);
				int len = serverData.length;
				outp.writeInt(len);
				outp.write(serverData,0,len);  
			}
			outp.flush();
			byte[] bytes = byteStream.toByteArray();
			outp.close();
			byteStream.reset();
			byteStream.close(); 
			return RunTimeConfigSession.getInstance().syncSend(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("PolicyConfigurationSession : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
		return null;
	}

	//Response From Server Comes Here
	public void callBack(ResultEventObject evt)
	{
		DataInputStream inp = null;
		ByteArrayInputStream bais = evt.getResultEventData();
		try
		{
			inp = new DataInputStream(bais);
			int type = evt.getEventType();
			if(type == RunTimeConfigSession.getModuleIndex("POLICY_CLIENT"))
			{
				int confFileIndex = inp.readInt();
				if(confFileIndex == GenericConstants.POLICY_CONF)
				{
					int operationIndex = inp.readInt();
					//Return the value to the UI
					switch(operationIndex )
					{
						case GenericConstants.SET:
							{
								policyConfigurationModel.updateTheUI(); 
								break;    
							}
						case GenericConstants.ADD:
							{
								policyConfigurationModel.updateTheUI(); 
								break;    
							}
						case GenericConstants.DELETE:
							{
								policyConfigurationModel.updateTheUI(); 
								break;    
							}
						case GenericConstants.MODIFY:
							{
								policyConfigurationModel.updateTheUI(); 
								break;    
							}
						case GenericConstants.RELOAD:
							{
								policyConfigurationModel.updateTheUI(); 
								break;    
							}
						case GenericConstants.GET:
							{
								int datalength = inp.readInt();
								byte xmlarr[] = new byte[datalength];
								inp.readFully(xmlarr);
								rootLogNode = (XMLNode)NmsClientUtil.deSerializeObject(xmlarr);
								policyConfigurationModel.updateTheUI(rootLogNode); 
								break;    
							}

						default:
							{
	//							System.err.println(" Unknown command received in RunTimeConfigSession : PolicyConfigurationSession ");
								break;
							}

					}
					//policyConfigurationModel.updateTheUI(); 
				}
			}
		}
		catch(Exception e)
		{
			System.err.println(NmsClientUtil.GetString("PolicyConfigurationSession : Exception in callBack ") + "  " + e);
			e.printStackTrace();
		}
	}

	public void dispose()
	{
		RunTimeConfigSession.getInstance().deRegister(this);
	}
}
