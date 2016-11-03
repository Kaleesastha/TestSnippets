/*$Id: RemoveOperationFromView.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)RemoveOperationFromView.java
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

/**
 * This class gives an example of how to use the API
 * "removeOperationFromView".
 */
public class RemoveOperationFromView 
{

	public static void main(String args[]) 
	{
		if( args.length < 2) 
		{
			System.out.println("Usage: java com.adventnet.nms.example.security.authorization.RemoveOperationFromView <operationName> <viewName> ");
			System.exit(1);
		}

		String name          = "//localhost/NmsAuthAdminAPI";
		String operationName = args[0];
		String viewName      = args[1];

		try 
		{
			/** 
			 * Get the reference to remote Object, AuthorizationAdmin by
			 * doing a lookup. 
			 */
			AuthorizationAdmin authAdmin = (AuthorizationAdmin) Naming.lookup(name);

			if(authAdmin == null) 
			{
				System.out.println("Could not get a Reference to remote object");
				System.exit(1);
			}
			else
			{
				System.out.println("Got a reference to remote object");
			}

			// The following API removeOperationFromView,
			// will remove the given operation from the 
			// specified view
			authAdmin.removeOperationFromView( operationName, viewName);// API Call.
			System.out.println("Removed " + operationName + " from " + viewName);

		}
		catch(Exception e) 
		{
			System.out.println("Exception caught "  + e.getMessage());
			System.exit(1);
		}
		System.exit(0);
	}
}
