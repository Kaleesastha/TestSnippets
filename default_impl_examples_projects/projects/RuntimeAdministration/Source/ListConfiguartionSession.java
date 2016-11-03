//$Id: ListConfiguartionSession.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.nms.runtimeconfig;


//import com.adventnet.nms.util.ResultEventListener;
//import com.adventnet.nms.util.ResultEventAdapter;
//import com.adventnet.nms.util.ResultEventObject;
//import com.adventnet.nms.util.NmsClientUtil;
//import com.adventnet.nms.util.XMLNode;

import com.adventnet.nms.util.*;
import java.io.*;

public class ListConfiguartionSession  extends ResultEventAdapter implements ResultEventListener
{

	XMLNode rootLogNode = null;
	String moduleId = "LIST_CLIENT";
	static ListConfiguartionSession listConfiguartionSession = null;
	//private static ListConfiguartionSession listConfiguartionSession = null;

	public ListConfiguartionSession()
	{
		RunTimeConfigSession.getInstance().register(this);
		listConfiguartionSession = this;
	}

	//Just ForWard to RunTimeConfigSession
    public void send(byte[] sendData)
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream(byteStream);
			outp.writeUTF(getModuleId());
			outp.write(sendData);
			//outp.write(sendData , 0 , sendData.length);
			outp.flush();
			byte[] bytes = byteStream.toByteArray();
			outp.close();
			byteStream.reset();
			byteStream.close();
			RunTimeConfigSession.getInstance().send(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("ListConfiguartionSession : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
	}

    public byte[] syncSend(byte[] sendData)
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream(byteStream);
			outp.writeUTF(getModuleId());
			outp.write(sendData);
			//outp.write(sendData , 0 , sendData.length);
			outp.flush();
			byte[] bytes = byteStream.toByteArray();
			outp.close();
			byteStream.reset();
			byteStream.close();
			return RunTimeConfigSession.getInstance().syncSend(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("ListConfiguartionSession : Exception in syncSendRequest") + "  " + e);
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
			if(type == RunTimeConfigSession.getModuleIndex("LIST_CLIENT"))
			{
				int confFileIndex = inp.readInt();
                ResultEventObject resultEventObject = new ResultEventObject(this,bais,confFileIndex );
                fireResultEvent(resultEventObject);
				
			}
		}
		catch(Exception e)
		{
			System.err.println(NmsClientUtil.GetString("ListConfiguartionSession : Exception in callBack ") + "  " + e);
			e.printStackTrace();
		}
	}

	public void dispose()
	{
		RunTimeConfigSession.getInstance().deRegister(this);
	}

	public String getModuleId()
	{
		return moduleId;
	}

	public static ListConfiguartionSession getInstance()
	{
		if( listConfiguartionSession == null)
			new ListConfiguartionSession();
		return listConfiguartionSession;
	}

	public String toString()
	{
		return "RunTimeConfigSession : "+getModuleId();
	}


}
