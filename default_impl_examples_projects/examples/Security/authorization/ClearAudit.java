/*$Id: ClearAudit.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)ClearAudit.java
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

package com.adventnet.security.example.audit;

//Security Imports.
import com.adventnet.security.audit.AuditAPI;

//Java Imports.
import java.util.Vector;
import java.util.Properties;
import java.rmi.*;

/**
 * This class gives an example of how to use the API
 * "clearAudit(String userName, Properties auditProp)".
 */
public class ClearAudit 
{
	public static void main(String[] args) 
	{
		if( args.length < 1 ) 
		{
			System.out.println("Usage: java com.adventnet.security.example.audit.ClearAudit <userName> ");
			System.exit(1);
		}

		String name = "//localhost/NmsAuditAPI";
		String userName = args[0];

		try 
		{
			/** 
			 * Get the Remote Object, AuditAPI reference 
			 * through rmi by doing a lookup. 
			 */

			AuditAPI authAudit = (AuditAPI) Naming.lookup(name);

			if(authAudit == null) 
			{
				System.out.println("Could not get a Reference to remote object, AuditAPI. Lookup failed");
				System.exit(1);
			}
			else   
			{
				System.out.println("Got a reference to remote object, AuditAPI. Invoking the API clearAudit(UserName, auditProp)....");
			}
			/**
			 * Invoke the API on the remote Object.
			 * The following API "clearAudit(......)",
			 * will clear the previous Audit Trails with respect to the
			 * given parameters.
			 */	
			authAudit.clearAudit(userName, null); // API Call.
		}
		catch (Exception e) 
		{
			System.out.println("Exception caught "+e.getMessage());
			System.exit(1);
		}
		System.exit(0);
	}
}
