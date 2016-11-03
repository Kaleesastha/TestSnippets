/*$Id: GetAuditTrials.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)GetAuditTrials.java
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
 * "getAuditTrails(String userName, Properties auditProp)".
 */
public class GetAuditTrials 
{
	public static void main(String[] args) 
	{
		if( args.length < 1 ) 
		{
			System.out.println("Usage: java com.adventnet.security.example.audit.GetAuditTrials <userName> ");
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
			else {
				System.out.println("Got a reference to remote object, AuditAPI. Invoking the API getAuditTrails(UserName, auditProp)....");
			}
			/**
			 * Invoke the API on the remote Object.
			 * The following API "getAuditTrails(......)",
			 * will give the result as a vector of Audit Trails that
			 * are made previously.
			 */	
			Vector trials = authAudit.getAuditTrails(userName, null); // API Call.
			if(trials.size() == 0) 
			{
				System.out.println("No Audit Trails are made." );
			}
			else 
			{
				System.out.println("The Audit Trails are as follows ");
				for(int i=0;i<trials.size();i++) 
				{
					System.out.println(trials.elementAt(i));
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
