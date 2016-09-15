/* $Id: ServerTestToolAPIImpl.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */

package com.adventnet.nms.server.testtool;

import java.util.Vector;
import java.util.Properties;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

import com.adventnet.nms.util.NmsUtil;


public class ServerTestToolAPIImpl  extends UnicastRemoteObject implements ServerTestToolAPI 
{
	public ServerTestToolAPIImpl() throws RemoteException
	{
		super();
	}

	public Object  executeMethod(String keyword) throws RemoteException 
	{
		if(keyword.equals("loadInterface"))
		{
			return  NmsUtil.loadInterfacesList();
			
		}

		return "Not yet implemented_1";

	}
	public Object executeMethod(String keyword , int firstarg) throws RemoteException
	{
		return "Not yet implemented_2";
	}
	public Object  executeMethod(String keyword , String firstarg , String secondarg) throws RemoteException
	{
		return "Not yet implemented_3";
	}

}


