/*$Id: AuthenticationExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)AuthenticationExample.java
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

package com.adventnet.nms.example.security.authentication;

import com.adventnet.security.AuthUtil;
import com.adventnet.security.authentication.*;

import java.rmi.*;

/**
 * This example illustrates how the authentication is done in Web NMS.
 * 
 * Usage: java com.adventnet.nms.example.security.authentication.AuthenticationExample <userName> <passWord>
 * Arguments  :
 * <userName> : The name of the User who is going to check for his credentials.
 * <passWord> : Password for this user.
 * 
 */

public class AuthenticationExample 
{
	public static void main(String[] args) 
	{
		/**
		 * Get the Remote Object, AuthenticationAPI reference 
		 * through rmi by doing a lookup. 
		 */

		if(args.length < 2)
		{
			Usage();
		}

		String userName = args[0]; // Username.
		String passWord = args[1]; // Password.

		try 
		{
			String name = "//localhost/NmsAuthenticationAPI"; // Handle Name
			AuthenticationAPI authenapi = (AuthenticationAPI) Naming.lookup(name);
			if(authenapi == null) 
			{
				System.out.println("Could not get a Reference to remote "+
						"object, AuthenticationAPI. LookUp failed");
				System.exit(1);
			}
			else
			{
				System.out.println("Got a reference to remote object, "+
						"AuthenticationAPI.");
			}

			String challenge = authenapi.getChallenge(userName); // gets Challenge for this user.
			String key = AuthUtil.getChallengeKey(userName, passWord, challenge); // gets Key for this user.


			//Usage of isPasswordAged()
			if(authenapi.isPasswordAged(userName))
			{
				System.out.println("User"+userName+"'s password is aged");
			}
			else
			{
				System.out.println("User "+userName+"'s password is not aged");
			}

			//Usage of isUserEnabled()
			if(authenapi.isUserEnabled(userName))
			{
				System.out.println("User "+userName+" is enabled");
			}
			else
			{
				System.out.println("User "+userName+" is not enabled");
			}

			if(authenapi.verifyCredentials(userName,key)) // Authentication checked here.
			{
				System.out.println("Authenticated.");
			}
			else
			{
				System.out.println("Authentication Failed.");
			}

		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION : "+e);
		}
		System.exit(0);
	}

	static void Usage() 
	{
		System.out.println("Usage: java com.adventnet.nms.example.security.authentication.AuthenticationExample "+
				"<userName> <passWord> ");
		System.exit(1);
	}
}
