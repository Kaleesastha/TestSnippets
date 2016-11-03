//$Id: LoggingConfiguartionSession.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import com.adventnet.nms.util.ResultEventListener;
//import com.adventnet.nms.util.ResultEventAdapter;
import com.adventnet.nms.util.ResultEventObject;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.startnms.GenericConstants;

import java.io.*;

public class LoggingConfiguartionSession implements ResultEventListener
{

	LoggingConfiguartionModel loggingConfiguartionModel = null;
	XMLNode rootLogNode = null;

	public LoggingConfiguartionSession(LoggingConfiguartionModel loggingConfModel)
	{
		loggingConfiguartionModel = loggingConfModel;
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
			outp.writeUTF(loggingConfiguartionModel.getModuleId());
			outp.writeInt(GenericConstants.LOGGING_PARAMS);
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
			System.err.println(NmsClientUtil.GetString("LoggingConfiguartionSession : Exception in sendRequest") + "  " + e);
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
			outp.writeUTF(loggingConfiguartionModel.getModuleId());
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
			System.err.println(NmsClientUtil.GetString("LoggingConfiguartionSession : Exception in sendRequest") + "  " + e);
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
			if(type == RunTimeConfigSession.getModuleIndex("LOG_CLIENT"))
			{
				int confFileIndex = inp.readInt();
				if(confFileIndex == GenericConstants.LOGGING_PARAMS)
				{
					int operationIndex = inp.readInt();
					//Return the value to the UI
					switch(operationIndex )
					{
						case GenericConstants.SET:
							{
								loggingConfiguartionModel.updateTheUI(); 
								break;    
							}
						case GenericConstants.ADD:
							{
								loggingConfiguartionModel.updateTheUI(); 
								break;    
							}
						case GenericConstants.DELETE:
							{
								loggingConfiguartionModel.updateTheUI(); 
								break;    
							}
						case GenericConstants.MODIFY:
							{
								loggingConfiguartionModel.updateTheUI(); 
								break;    
							}
						case GenericConstants.RELOAD:
							{
								//loggingConfiguartionModel.updateTheUI(); 
								break;    
							}
						case GenericConstants.GET:
							{
                                                            try
                                                            {
								int datalength = inp.readInt();
								byte xmlarr[] = new byte[datalength];
								inp.readFully(xmlarr);
                                                                rootLogNode = (XMLNode)NmsClientUtil.deSerializeObject(xmlarr);
                                                                loggingConfiguartionModel.updateTheUI(rootLogNode);
                                                            }
                                                            catch(Exception e)
                                                            {
                                                                JOptionPane.showMessageDialog(null,LoggingMainUI.resourceBundle.getString("javaui.logging.customloggingmessage"),LoggingMainUI.resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                                                                LoggingMainUI.isNMSLogging=false;
                                                                loggingConfiguartionModel.showDummyPanel();
                                                            }
                                                            break;    
							}

						default:
							{
	//							System.err.println(" Unknown command received in RunTimeConfigSession : LoggingConfiguartionSession ");
								break;
							}

					}
					//loggingConfiguartionModel.updateTheUI(); 
				}
			}
		}
		catch(Exception e)
		{
			System.err.println(NmsClientUtil.GetString("LoggingConfiguartionSession : Exception in callBack ") + "  " + e);
			e.printStackTrace();
		}
	}

	public String toString()
	{
		return "RunTimeConfigSession : "+loggingConfiguartionModel.getModuleId();
	}


	public void dispose()
	{
		RunTimeConfigSession.getInstance().deRegister(this);
	}
}
