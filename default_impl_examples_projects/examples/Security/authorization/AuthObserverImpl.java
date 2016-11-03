/*$Id: AuthObserverImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)AuthObserverImpl.java
 * Copyright (c) 1996-2002 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

package com.adventnet.security.authorization;

import java.util.Properties;
import java.rmi.*;
import java.rmi.server.*;

import com.adventnet.security.authorization.*;

/**
 * This is the implementor class of the interface AuthObserver. This class
 * extends UnicastRemoteObject which facilitates the AuthObserver to be
 * available through RMI.  
 */
public class AuthObserverImpl extends UnicastRemoteObject implements AuthObserver 
{

	public AuthObserverImpl()throws RemoteException 
	{
		super();
	}

	public void update(String type,Properties prop)throws java.rmi.RemoteException 
	{
		System.out.println("type :" + type);
		System.out.println("prop :" + prop);
	}
}
