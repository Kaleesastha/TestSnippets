/*$Id: SecurityExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)SecurityExample.java
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

/**
 * This example illustrates how to get the handle for the 
 * AuthorizationAdmin API and perform the following Admin
 * operations on the authorization data store.
 */

package com.adventnet.nms.example.security.authorization;

import com.adventnet.security.authorization.*;
import java.rmi.*;
import java.util.*;

public class SecurityExample 
{

	public static void main(String args[]) 
	{
		if ( args.length < 1 ) 
		{
			System.out.println("Usage: java com.adventnet.nms.example.security.authorization.SecurityExample <process>");
			System.exit(0);
		}  

		String name          = "//localhost/NmsAuthAdminAPI";
		String process       = args[0];

		try 
		{
			/**
			 * Get the reference to remote Object, AuthorizationAdmin by
			 * doing a RMI lookup.
			 */
			AuthorizationAdmin authAdmin = (AuthorizationAdmin) Naming.lookup(name);
			if(authAdmin == null) 
			{
				System.out.println("Could not get a reference to remote object, AuthorizationAdmin");
				System.exit(1);
			}
			else 
			{
				System.out.println("Got a reference to remote object, AuthorizationAdmin");
			}
			if(process.equalsIgnoreCase("create user")) 
			{
				if ( args.length < 5 ) 
				{
					System.out.println("Usage: java com.adventnet.nms.example.security.authorization.SecurityExample <'create user'> <user> <group> <view> <operation> ");
					System.exit(1);
				}
				String userName      = args[1];
				String groupName     = args[2];
				String viewName      = args[3];
				String operationName = args[4];

				/*
				 * Creating the user and assigning it to the specified
				 * group.
				 */

				authAdmin.createUser(userName,groupName);
				System.out.println("user "+ userName +" created.");

				/*
				 * Assigning the authorized view to the group.
				 */
				 
				authAdmin.assignViewToGroup(groupName, viewName);
				System.out.println(viewName + " is assigned to group " + groupName);

				/*
				 * Assigning an operation to the authorized view.
				 */

				authAdmin.assignOperationToView(operationName, viewName);
				System.out.println("operation "+ operationName + " is assigned to "+ viewName );

				/*
				 * Get the Hashtable of operations for that authorized view.
				 */

				Hashtable hashOpens=authAdmin.getOperationsForViewInHash(viewName);
				System.out.println("The operations for the "+viewName+" are "+hashOpens);
			}

			else if(process.equalsIgnoreCase("change password"))
			{
				if ( args.length < 3) 
				{
					System.out.println("Usage: java com.adventnet.nms.example.security.authorization.SecurityExample <'change password'> <user> <new_password> ");
					System.exit(1);
				}
				String userName      = args[1];
				String newPassword   = args[2];

				/*
				 * Change the password for the existing user.
				 */

				if(authAdmin.changePassword(userName,newPassword))
				{
					System.out.println("Password changed for the user "+userName);
				}
				else
				{
					System.out.println("Unable to change the Password for the user "+userName);
				}
			}

			else if(process.equalsIgnoreCase("password age"))
			{
				if ( args.length < 3) 
				{
					System.out.println("Usage: java com.adventnet.nms.example.security.authorization.SecurityExample <'password age'> <user> <password_age_in_days> ");
					System.exit(1);
				}
				String userName      = args[1];
				Integer i= new Integer(args[2]);
				int passwdAgeInDays  =i.intValue();

				/*
				 * Set the Password Age for the user. Password Age in number of days.
				 */

				authAdmin.setPasswordAge(userName,passwdAgeInDays); // API Call.
				System.out.println("The password age for " + userName + " is set to " + passwdAgeInDays +" days.");

				/*
				 * Get the password age for the User in number of days.
				 */
				
				int passwdAge=authAdmin.getPasswordAge(userName); // API Call.
				System.out.println("The password age for " + userName + "is " + passwdAge +" days.");
			}

			else if(process.equalsIgnoreCase("user expiration"))
			{
				if ( args.length < 3) 
				{
					System.out.println("Usage: java com.adventnet.nms.example.security.authorization.SecurityExample <'user expiration'> <user> <user_age_in_days> ");
					System.exit(1);
				}
				String userName      = args[1];     
				Integer j= new Integer(args[2]);
				int noOfDays         =j.intValue();

				/*
				 * Set the expiration time for the User in number of days.
				 */
				
				authAdmin.setUserExpirationTime(userName,noOfDays);
				System.out.println("The expiration time for " + userName + " is set to " + noOfDays +" days.");

				/*
				 * Get the expiry time for the User.
				 */
				
				int expiryTime=authAdmin.getUserExpirationTime(userName);
				System.out.println("User expiration time is "+expiryTime+" days.");
			}

			else if(process.equalsIgnoreCase("user status"))
			{
				if ( args.length < 3) 
				{
					System.out.println("Usage: java com.adventnet.nms.example.security.authorization.SecurityExample <'user status'> <user> <user_status> ");
					System.exit(1);
				}
				String userName      = args[1];
				String status        = args[2];

				/*
				 * Set the status for that User.
				 */

				authAdmin.setUserStatus(userName,status); // API Call.
				System.out.println("The user status is set to " + status);
			}

			else if(process.equalsIgnoreCase("get Attributes"))
			{
				if ( args.length < 2) 
				{
					System.out.println("Usage: java com.adventnet.nms.example.security.authorization.SecurityExample <'get attributes'> <user> ");
					System.exit(1);
				}
				String userName      = args[1];

				/*
				 * Get all the attributes for the User.
				 */
				
				Hashtable userAttributes = authAdmin.getAllAttributes(userName); // API Call.
				System.out.println("The attributes for the user "+userName+" are "+userAttributes);
			}

			else if(process.equalsIgnoreCase("remove user"))
			{
				if ( args.length < 3) 
				{
					System.out.println("Usage: java com.adventnet.nms.example.security.authorization.SecurityExample <'remove user'> <user> <remove_traces(boolean)> ");
					System.exit(1);
				}
				String userName      = args[1];
				String removeTrace   = args[2];
				
				boolean b=(removeTrace.equals("true"))?true:false;

				/*
				 * Remove the user traces by the flag removeTrace.
				 */

				if(authAdmin.removeUser(userName,b))
				{
					if(removeTrace.equals("true"))
					{
						System.out.println("Removed the user : " + userName + " and its traces");
					}
					else
					{
						System.out.println("Removed the user : " + userName);
					}
				}
				else
				{
					System.out.println("Unable to remove user : "+userName);
				}
			}

			else if(process.equalsIgnoreCase("operations tree"))
			{

				/*
				 * Get the entire Operations in the Operations Tree.
				 */

				Vector operations = authAdmin.getAllOperations(); // API Call.
				for ( int k=0; k<operations.size(); k++) 
				{
				
					OperationObject oo = (OperationObject) operations.elementAt(k);
					System.out.println("Parent : "+oo.getParentName()+"  OperationName : "+oo.getOperationName()+"  isLeaf : "+oo.isIsLeaf());
				}
			}

			else 
			{
				System.out.println("\n\n First argument will be \n <  create user \n || change password \n || password age\n || user expiration\n || remove user\n || get attributes \n || operations tree \n || user status  > ");
			}

		}
		catch (Exception e) 
		{
			System.out.println("Exception caught " +  e.getMessage());
			System.exit(1);
		}
		System.exit(0);
	}
}
