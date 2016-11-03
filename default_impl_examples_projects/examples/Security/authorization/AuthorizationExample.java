/*$Id: AuthorizationExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)AuthorizationExample.java
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

/*
 * This Example is going to check for a particular User is Authorized to perform a certain Operation
 * with a specified Properties.
 */

package com.adventnet.nms.example.security.authorization;

import com.adventnet.security.authorization.*;

import java.util.Vector;
import java.util.Properties;
import java.rmi.*;

/**
 * This class gives an example of how to use the API
 * "isAuthorized(String userName,String operationName,
 * Properties viewProperties)".
 */

public class AuthorizationExample 
{

	public static void main(String[] args) 
	{

		if( args.length < 2 ) 
		{
			System.out.println("Usage: java com.adventnet.nms.example.security.authorization.AuthorizationExample <userName> <operationName> ");
			System.exit(1);
		}

		String name         = "//localhost/NmsAuthEngineAPI";
		String userName     = args[0];
		String operation    = args[1];
		Properties viewProp = new Properties();
		viewProp.setProperty("network","192.168.1.0,192.168.2.0");
		viewProp.setProperty("netmask","255.255.255.0");
		viewProp.setProperty("type","snmpNode");

		try 
		{
			/** 
			 * Get the Remote Object, AuthorizationEngine reference
			 * through rmi by doing a lookup.
			 */
			AuthorizationEngine authEngine = (AuthorizationEngine) Naming.lookup(name);
			if(authEngine == null) 
			{
				System.out.println("Could not get a Reference to remote object,Authorization.LookUp failed");
				System.exit(1);
			}
			else 
			{
				System.out.println("Got a reference to remote object, AuthorizationEngine. Invoking the API isAuthorized(UserName,OperationName,ViewProperies)....");
			}

			/**
			 * Invoke the API on the remote Object.
			 * The following API "isAuthorized(......)",
			 * will give the result whether the User is Authorized to do the operation(oper) or not.
			 */

			if(authEngine.isAuthorized(userName, operation)) // API Call.
			{
				System.out.println("User " + userName+" is Authorized to do the "+operation+" operation");
			}
			else
			{
				System.out.println("User " + userName+" can't do the "+operation+" operation");
			}

			/**
			 * Invoke the API on the remote Object.
			 * The following API "isAuthorized(......)",
			 * will give the result whether the User with
			 * the Properties(p) is Authorized to do the operation(oper) or not.
			 *
			 *
			 * The viewProp should contain all the propertyNames and any one of the propertyValue for that
			 * propertyName,which is in the database for a viewName then it returns true otherwise false.
			 * If the viewProp is null then it will returns isAuthorized(userName, operationName);
			 *
			 *
			 * if(authEngine.isAuthorized(userName, operation, viewProp)) // API Call.
			*   System.out.println("User " + userName+" is Authorized to do the "+operation+" operation");
			 * else
			*   System.out.println("User " + userName+" can't do the "+operation+" operation");
			*/

			/**
			 * Now You can call the API isAuthorized(userName,operationName,viewProperties,namedViewname);
			 * this method will check authorization as done above.If the user is authorized then 
			 * It will create NamedViewName for all the AuthorizedViewNames common for userName and 
			 * operationName.
			 *
			 * if(isAuthorized(userName,operation,viewProp,"Printers") {
			*     System.out.println("User " + userName+" is Authorized to do the "+operation+" operation");
			*     System.out.println("NamedViews are created.");
			 * }
			 * else 
			*     System.out.println("User " + userName+" can't do the "+operation+" operation");
			*/

		}
		catch (Exception e) 
		{
			System.out.println("Exception caught "+e.getMessage());
		}
		System.exit(0);
	}
}
