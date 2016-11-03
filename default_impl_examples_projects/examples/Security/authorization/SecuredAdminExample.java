/*$Id: SecuredAdminExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)SecuredAdminExample.java
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
 * This Example will show you a complete work of Creating a user
 * and assign the user to group.
 * assigning View For that group 
 * assigning an operastion for that group.
 */

package com.adventnet.nms.example.security.authorization;

import java.rmi.*;
import com.adventnet.security.authentication.*;
import com.adventnet.security.authorization.*;
import com.adventnet.security.AuthUtil;
import com.adventnet.security.crypto.CryptoGraphAPI;
import com.adventnet.security.authorization.SecuredAdminAPI;

public class  SecuredAdminExample 
{
	private String superUser = "root";
	private String superUserPassword = "public";
	String name = "//localhost/RMIAccessAPI";
	String API = "SecuredAdminAPI";
	SecuredAdminAPI securedAdmin = null;

	private void showHelp() 
	{
		System.out.println("Usage : java com.adventnet.nms.example.security.authorization.SecuredAdminExample -cu <userName> <groupname> <password> \n(for creating user)\n");	
		System.out.println("      : java com.adventnet.nms.example.security.authorization.SecuredAdminExample -cp <userName> <old-password> <new-passowrd> \n(for changing password)\n");	
		System.out.println("      : java com.adventnet.nms.example.security.authorization.SecuredAdminExample -co <userName> <groupname> <password> <ownerName> \n(for creating user with Owner)\n");	
		System.out.println("      : java com.adventnet.nms.example.security.authorization.SecuredAdminExample -csu <userName> <password> \n(for changing password directly without taking oldPassword)\n");	
		System.exit(1);
	}

	private void createUser(String args[]) 
	{
		if ( args.length < 4 ) 
		{
			showHelp();
		}

		String userName = args[1];
		String groupName = args[2];
		String password = args[3];
		securedAdmin = getSecuredAdminAPI(getRmiAccessApi()); 

		try 
		{
			CryptoGraphAPI cryptoObject = (CryptoGraphAPI) securedAdmin.getCryptoAPI ( userName );

			String en_Password = cryptoObject.enCrypt(password);

			if ( securedAdmin.createUser (userName, groupName, en_Password) ) 
			{
				System.out.println("CREATED USER SUCCESSFULLY");	
			}
			else
			{
				System.out.println("ERROR WHILE CREATING USER.");	
			}

		}
		catch ( Exception e ) 
		{
			System.out.println("EXCEPTION : " + e.getMessage() );	
			//e.printStackTrace();
		}
	}

	private void creatUserForOwner(String args[]) 
	{
		if ( args.length < 5 ) 
		{
			showHelp();
		}

		String userName   = args[1];
		String groupName  = args[2];
		String password   = args[3];
		String ownerName  = args[4];
		securedAdmin = getSecuredAdminAPI(getRmiAccessApi()); 

		try 
		{
			CryptoGraphAPI cryptoObject = (CryptoGraphAPI) securedAdmin.getCryptoAPI ( userName );

			String en_Password = cryptoObject.enCrypt(password);

			if ( securedAdmin.createUserForOwner (userName, groupName, en_Password, ownerName) ) 
			{
				System.out.println("CREATED USER SUCCESSFULLY.");	
			}
			else
			{
				System.out.println("ERROR WHILE CREATING USER.");	
			}

		} 
		catch ( Exception e ) 
		{
			System.out.println("EXCEPTION : " + e.getMessage() );	
		}
	}

	private void changePasswordForSuper(String args[]) 
	{
		if ( args.length < 3 ) 
		{
			showHelp();
		}

		String userName = args[1];
		String password = args[2];
		securedAdmin = getSecuredAdminAPI(getRmiAccessApi()); 

		try 
		{
			CryptoGraphAPI cryptoObject = (CryptoGraphAPI) securedAdmin.getCryptoAPI ( userName );

			String en_Password = cryptoObject.enCrypt(password);

			if ( securedAdmin.changePassword (userName, en_Password) ) 
			{
				System.out.println("PASSWORD CHANGED SUCCESSFULLY.");	
			}
			else
			{
				System.out.println("ERROR WHILE CHANGING PASSWORD.");	
			}

		} 
		catch ( Exception e ) 
		{
			System.out.println("EXCEPTION : " + e.getMessage() );	
		}
	}

	private void changePassword(String args[]) 
	{
		if ( args.length < 4 ) 
		{
			showHelp();
		}

		String userName = args[1];
		String oldpass = args[2];
		String newpass = args[3];
		securedAdmin = getSecuredAdminAPI(getRmiAccessApi()); 

		try
		{
			CryptoGraphAPI cryptoObject = (CryptoGraphAPI) securedAdmin.getCryptoAPI ( userName );

			String en_old_Password = cryptoObject.enCrypt(oldpass);
			String en_new_Password = cryptoObject.enCrypt(newpass);

			if ( securedAdmin.changePassword (userName, en_old_Password, en_new_Password) ) 
			{
				System.out.println("PASSWORD CHANGED SUCCESSFULLY.");	
			}
			else
			{
				System.out.println("ERROR WHILE CHANGING PASSWORD.");	
			}

		} 
		catch ( Exception e ) 
		{
			System.out.println("EXCEPTION : " + e.getMessage() );	
		}

	}

	private RMIAccessAPI getRmiAccessApi() 
	{
		RMIAccessAPI rmiAccessAPI=null; 

		try
		{
			rmiAccessAPI = (RMIAccessAPI)Naming.lookup(name);
			if(rmiAccessAPI == null)
			{
				System.out.println("Could not get Reference to remote object");
				System.exit(1);
			}
			else
			{
				System.out.println("Got a reference to remote object \'RMIAccessAPI\'");
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			System.out.println("Exception while Naming.lookup :"+e);
			System.exit(1);
		}
		return rmiAccessAPI;
	}	

	private SecuredAdminAPI getSecuredAdminAPI(RMIAccessAPI rmiAccessAPI) 
	{
		try 
		{
			String challenge = rmiAccessAPI.getChallenge( superUser );
			String key = AuthUtil.getChallengeKey( superUser, superUserPassword, challenge);
			AuthenticationTicket ticket = new AuthTicket();
			ticket.setKey(key);
			SecuredAdminAPI securedAdmin = ( SecuredAdminAPI ) rmiAccessAPI.getAPI( superUser, ticket, API);
			System.out.println("Got the API \'" + API+"\'");
			return securedAdmin;
		}
		catch ( Exception e ) 
		{
			System.out.println("Error while getting SecuredAdminAPI");
			System.exit(1);
		}
		return null;
	}		

	private void call( String args[] ) 
	{
		if ( args.length < 1 ) 
		{
			showHelp();
		}

		String type = args[0];

		if ( type.equals("-cu") )
		{
			createUser(args);
		}
		else if ( type.equals("-cp") )
		{
			changePassword(args);
		}
		else if ( type.equals("-co") )
		{
			creatUserForOwner(args);
		}
		else if ( type.equals("-csu") )
		{
			changePasswordForSuper(args);
		}
		else 
		{
			showHelp();
		}

		System.exit(0);
	}

	public static void main ( String args[] ) 
	{
		new SecuredAdminExample().call(args);
	}
}
