/* $Id: ServerTestToolAPI.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */

package com.adventnet.nms.server.testtool;

import java.util.Vector;
import java.util.Properties;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServerTestToolAPI  extends Remote 
{
	public Object  executeMethod(String keyword) throws RemoteException ;
	public Object  executeMethod(String keyword , int firstarg) throws RemoteException ;
	public Object  executeMethod(String keyword , String firstarg , String secondarg) throws RemoteException ;

}


