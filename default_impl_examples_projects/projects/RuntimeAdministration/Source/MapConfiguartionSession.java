//$Id: MapConfiguartionSession.java,v 1.1 2006/08/29 13:57:02 build Exp $

package com.adventnet.nms.runtimeconfig;

//import com.adventnet.nms.util.ResultEventListener;
//import com.adventnet.nms.util.ResultEventAdapter;
//import com.adventnet.nms.util.ResultEventObject;
//import com.adventnet.nms.util.NmsClientUtil;
//import com.adventnet.nms.util.XMLNode;
//import com.adventnet.nms.util.GenericConstants;
import com.adventnet.nms.startnms.GenericConstants;

import com.adventnet.nms.util.*;
import java.io.*;

public class MapConfiguartionSession  extends ResultEventAdapter implements ResultEventListener
{

	XMLNode rootLogNode = null;
	String moduleId = "MAP_CLIENT";
	//private static MapConfiguartionSession mapConfiguartionSession = null;
	static MapConfiguartionSession mapConfiguartionSession = null;

	public MapConfiguartionSession()
	{
		RunTimeConfigSession.getInstance().register(this);
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
			System.err.println(NmsClientUtil.GetString("MapConfiguartionSession : Exception in sendRequest") + "  " + e);
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
			System.err.println(NmsClientUtil.GetString("MapConfiguartionSession : Exception in syncSendRequest") + "  " + e);
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
			if(type == RunTimeConfigSession.getModuleIndex("MAP_CLIENT"))
			{
				int confFileIndex = inp.readInt();
                ResultEventObject resultEventObject = new ResultEventObject(this,bais,confFileIndex );
                fireResultEvent(resultEventObject);
				
			}
		}
		catch(Exception e)
		{
			System.err.println(NmsClientUtil.GetString("MapConfiguartionSession : Exception in callBack ") + "  " + e);
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

	public String toString()
	{
		return "RunTimeConfigSession : "+getModuleId();
	}


	public static MapConfiguartionSession getInstance()
	{
		if( mapConfiguartionSession == null)
			mapConfiguartionSession = new MapConfiguartionSession();
		return mapConfiguartionSession;
	}

}
