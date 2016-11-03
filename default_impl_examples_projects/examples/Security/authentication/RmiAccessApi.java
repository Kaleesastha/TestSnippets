/*$Id: RmiAccessApi.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)RmiAccessApi.java
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

import com.adventnet.security.authentication.*;
import com.adventnet.security.authorization.*;
import com.adventnet.security.AuthUtil;
import java.rmi.*;


/**
 * This class gives an example of how to use the RMIAccessAPI's
 * "getAPI" method.
 */
public class RmiAccessApi{

	public static void main(String[] args)
	{
		if ( args.length < 2 ) 
		{
			System.out.println("Usage : java com.adventnet.nms.example.security.authentication.RmiAccessApi <userName> <password> ");
			System.exit(1);
		}

		String userName = args[0];
		String password = args[1];

		/** 
		 * Get the reference to remote Object, RMIAccessAPI by
		 * doing a lookup. 
		 */

		String name = "//localhost/RMIAccessAPI";
		RMIAccessAPI rmiAccessAPI=null; 
		try
		{
			rmiAccessAPI = (RMIAccessAPI)Naming.lookup(name);
			if(rmiAccessAPI == null)
			{
				System.out.println("Could not get a Reference to remote object");
				System.exit(1);
			}
			else
			{
				System.out.println("Got a reference to remote object ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception while Naming.lookup :"+e);
			System.exit(1);
		}

		/**
		 * The following will get the specified API from the security module. 
		 */ 

		try
		{
			/*
			   String nms="NmsAuthEngineAPI";
			   AuthorizationEngine authEngine=(AuthorizationEngine)rmiAccessAPI.getAPI("root","public",nms);
			   System.err.println("EngineAPI isAuthrzd:"+authEngine.isAuthorized("root","GET_ALERTHISTORY"));	
			   */	
			String API="NmsAuthAdminAPI";
			String challenge = rmiAccessAPI.getChallenge( userName);
			String key = AuthUtil.getChallengeKey( userName, password, challenge);
			AuthenticationTicket ticket = new AuthTicket();
			ticket.setKey(key);
			AuthorizationAdmin authAdmin = ( AuthorizationAdmin ) rmiAccessAPI.getAPI( userName, ticket, API);
			System.out.println("Got the API " + API);
			System.out.println("Accessing the API getAllGroups() from the handle --NmsAuthAdmin :"+authAdmin.getAllGroups());
		}
		catch(Exception e)
		{
			System.out.println("Exception caught "  + e.getMessage());
			System.exit(1);
		}
		System.exit(0);
	}
}
