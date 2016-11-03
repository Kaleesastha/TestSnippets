/*$Id: UserConfigAPIExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)UserConfigAPIExample.java
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

package com.adventnet.nms.example.authentication;

import java.net.*;
import java.io.*;
import java.util.*;
import java.rmi.*;

import com.adventnet.nms.authentication.*;

/**
 * An example class to illustrate the usage of all the methods
 * available in the UserConfig API.
 */

public class UserConfigAPIExample 
{

	public static void main(String argn[])
	{
		boolean result;
		Vector usersName = null;
		UserConfigAPI auth = null;

		try
		{
			String myHost = java.net.InetAddress.getLocalHost().getHostName();
			auth = (UserConfigAPI)Naming.lookup("//"+myHost+"/UserConfigAPI");
			if(auth == null)
			{
				System.out.println("Could not get a reference to remote object");
				System.exit(1);
			}
			else
			{
				System.out.println("Got a reference to remote object ");
			}

			/**
			 * Checking whether the user "root" is present and is
			 * assigned to the group "Admin".
			 */

			result = auth.isUserNamePresent("Admin","root");

			if(result)
			{
				System.out.println("The user root is present and assigned to the group Admin.");
			}
			else
			{
				System.out.println("The user root is not present.");
			}

			/**
			 * Creating the new account with the user name as "newuser1" and
			 * assigning it to the group "Users".
			 */
			
			result = auth.createNewAccount("newuser1","newuser","Users");
			if(result)
			{
				System.out.println("The account is created with the user name as newuser1 and assigned to the group Users.");
			}


			/**
			 * Changing the password for the user added as newuser1.
			 */
			
			result = auth.changePassword("newuser1","newuser","newuser5");
			if(result)
			{
				System.out.println("The password for the user newuser1 is changed to newuser5.");
			}


			if(auth.isUserNamePresent("Users","newuser1"))
			{
				result = auth.removeAccount("newuser1","Users");
				if(result)
				{
					System.out.println("The user account newuser1 has been deleted.");
				}
			}
			//get the list of users configured under the
			//realm users.
			usersName = auth.getUsers("Users");

			//just print the list here.May be this can
			//be used for some other purpose also.
			System.out.println("The users present in the Users group are "+usersName);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}//End of main()

}//End of class UserConfigAPIExample
