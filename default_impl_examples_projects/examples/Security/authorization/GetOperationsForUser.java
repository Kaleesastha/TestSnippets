/*$Id: GetOperationsForUser.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)GetOperationsForUser.java
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

import java.util.Vector;
import java.util.Properties;
import java.rmi.*;

/**
 * This class gives an example of how to use the API
 * "getOperationsForUser(String userName)".
 */
public class GetOperationsForUser 
{

	public static void main(String[] args) 
	{

		if( args.length < 1 ) 
		{
			System.out.println("Usage: java com.adventnet.nms.example.security.authorization.GetOperationsForUser <userName> ");
			System.exit(1);
		}

		String name = "//localhost/NmsAuthEngineAPI";
		String userName = args[0];

		try 
		{
			/** 
			 * Get the Remote Object, AuthorizationEngine reference 
			 * through rmi by doing a lookup. 
			 */

			AuthorizationEngine authEngine = (AuthorizationEngine) Naming.lookup(name);

			if(authEngine == null) 
			{
				System.out.println("Could not get a Reference to remote object, AuthorizationEngine. Lookup failed");
				System.exit(1);
			}
			else 
			{
				System.out.println("Got a reference to remote object, AuthorizationEngine. Invoking the API getOperationsForUser(UserName)....");
			}
			/**
			 * Invoke the API on the remote Object.
			 * The following API "getOperationsForUser(......)",
			 * will give the result as a vector of operations the user
			 * is authorized to perform.
			 */	
			Vector operations = authEngine.getOperationsForUser(userName); // API Call.
			if(operations.size() == 0) 
			{
				System.out.println("No operations for user " + userName);
			}
			else 
			{
				System.out.println("The operations for the user \"" + userName + "\" are :\n");
				for(int i=0;i<operations.size();i++) 
				{
					System.out.println(operations.elementAt(i));
				}
			}

		}
		catch (Exception e) 
		{
			System.out.println("Exception caught "+e.getMessage());
			System.exit(1);
		}
		System.exit(0);
	}
}
