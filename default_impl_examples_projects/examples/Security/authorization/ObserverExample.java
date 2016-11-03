/*$Id: ObserverExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)ObserverExample.java
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

package com.adventnet.nms.example.security.authorization;

import com.adventnet.security.authorization.*;
import java.rmi.*;
import java.util.*;

public class ObserverExample 
{

	static void Usage() 
	{
		System.out.println(" java com.adventnet.nms.example.security.authorization.ObserverExample <ALL | USER | OPERATION>\n(register for all or user or operation)");
		System.exit(1);
	}

	public static void main(String[] args) 
	{

		if( args.length < 1 ) 
		{
			Usage();
		}

		String name = "//localhost/NmsAuthAdminAPI";
		String reg  = args[0];	

		try 
		{
			AuthorizationAdmin authAdmin = (AuthorizationAdmin) Naming.lookup(name);
			if(authAdmin == null) 
			{
				System.out.println("Could not get a Reference to remote object, AuthorizationAdmin");
				System.exit(1);
			}
			else 
			{
				System.out.println("Got a reference to remote object, AuthorizationAdmin");
			}

			AuthObserverImpl obs = new AuthObserverImpl();

			if(reg.equalsIgnoreCase("ALL"))
			{
				System.out.println("register for all updates :"+authAdmin.registerForAllUpdates(obs));
			}
			else if(reg.equalsIgnoreCase("USER")) 
			{
				System.out.println("register for user updates only :"+authAdmin.registerForUserUpdates(obs));
			}
			else if(reg.equalsIgnoreCase("OPERATION"))
			{
				System.out.println("register for operation updates only :"+authAdmin.registerForOperationUpdates(obs));
			}
			else 
			{
				System.out.println("No match found. Exiting...");
				System.exit(0);
			}
		}
		catch(Exception e) 
		{
			System.out.println("Exception caught " +  e.getMessage());
			System.exit(1);
		}
	}
}
