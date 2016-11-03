/*$Id: CustomViewScopeExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)CustomViewScopeExample.java
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
import java.rmi.Naming;

/**
 * This class illustrates how to use the functionality CustomViewScope.
 */

public class CustomViewScopeExample 
{
	public static void main(String[] args) 
	{
		String name = "//localhost/NmsAuthAdminAPI";

		try 
		{
			/** 
			 * Get the Remote Object, AuthorizationAdmin reference 
			 * through rmi by doing a lookup. 
			 */

			CustomViewScopeAPI cvsapi = (CustomViewScopeAPI)Naming.lookup(name);

			if ( cvsapi == null) 
			{
				System.err.println("Could not get a Reference to remote object, CustomViewScopeAPI. Lookup failed");
				System.exit(1);
			}
			else 
			{
				System.out.println("Got a reference to remote object, CustomViewScopeAPI.");
			}

			//create a CustomViewScope say "AlertCVS"
			String cvscope = "AlertCVS";
			cvsapi.createCVScope(cvscope); 
			System.out.println("The CustomViewScope is created as "+cvscope);

			//create an AuthorizedView with some criteria say {severity=Major}.
			String authorizedView = "MajorAlerts";
			Properties criteria = new Properties();
			criteria.setProperty("severity","2"); //Major has severity 2
			criteria.setProperty("entity","s*"); //entity starts with 's'

			AuthorizedViewObject avo = new AuthorizedViewObject();
			avo.setAuthorizedViewName(authorizedView);
			avo.setViewProperties(criteria);


			cvsapi.createAuthorizedView(avo); // AuthorizedView created.

			System.out.println("The Authorized Scope is created as "+authorizedView+" with criteria as "+criteria);

			// Now assign this authorizedView to both CVScope and group.
			cvsapi.assignViewToCVScope(cvscope,authorizedView);
			cvsapi.assignViewToGroup("Users",authorizedView);

			System.out.println("The Authorized Scope is assigned to group Users and assigned to CustomViewScope "+cvscope);

			String userName = "testUser";

			cvsapi.createUser(userName,"Users");
			
			System.out.println("The user is created with the name as "+userName+" and assigned to group Users.");

			/** 
			*  Now the users belonging to the "Users" Group 
			*  (i.e user "testUser") will have the 
			*  restriction to view only Major alerts in the Alerts panel.
			*  createUser("testUser","Users");
			*/

			//To view the criteria for the user "testUser"
			Properties[] p = cvsapi.getCriteria(cvscope,userName);
			System.out.println("Allowable criteria is ..");
			for ( int i=0; i<p.length; i++) 
			{
				System.out.println("p["+i+"] : "+p[i]);
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
