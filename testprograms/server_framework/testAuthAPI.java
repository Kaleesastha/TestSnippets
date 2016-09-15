/* $Id: testAuthAPI.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */

import java.rmi.*;
import java.util.*;

import com.adventnet.nms.authentication.UserConfigAPI;

public class testAuthAPI
{
   private UserConfigAPI api = null;

	public  testAuthAPI(String args[])
	{
		String host ="localhost";
		String port = "1099";
		String usage_string="Usage: java testAuthAPI " +
		   "\n [adduser username password realm] " +
		   "\n [deluser username realm] " +
		   "\n [checkuser username realm] " +
		   "\n [changepasswd username oldpasswd newpasswd realm] " +
		   "\n [printUsersList realm] " +
		   "\n [printRealmList username]" ;

		if(args.length == 0)
		{
			System.out.println(usage_string);
			System.exit(1);
		}

		try
		{
			String lookupstr = "rmi://" + host +":"+ port+"/"+ "UserConfigAPI";
			System.out.println("lookupstr =" + lookupstr);
			api = (UserConfigAPI)Naming.lookup(lookupstr);
			System.out.println(api);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}

		if(args[0].equals("adduser"))
		{
			addUser(args[1],args[2],args[3]);
		
		}
		else if(args[0].equals("deluser"))
		{
			delUser(args[1],args[2]);
		}
		else if(args[0].equals("checkuser"))
		{
			checkUser(args[1],args[2]);
		}
		else if(args[0].equals("changepasswd"))
		{
			changePassword(args[1],args[2],args[3],args[4]);
		}
		else if(args[0].equals("printUsersList"))
		{
			printUsersList(args[1]);
		}
		else if(args[0].equals("printRealmList"))
		{
			printRealmList(args[1]);
		}
		else
		{
			System.out.println(usage_string);
			System.exit(1);
		}

		System.exit(0);
	}

	private void addUser(String username,String password,String realm)
	{
		try
		{
             		api.createNewAccount(username,password,realm);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.exit(1);
		}
	}


	private void delUser(String username,String realm)
	{
		try
		{
             		boolean b = api.removeAccount(username,realm);
			System.out.println("removeAccount status = " + b);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.exit(1);
		}
	}

	private void checkUser(String username,String realm)
	{
		try
		{
			boolean b = api.isUserNamePresent(realm,username);
			if(b)
			{
				System.out.println(username + " exists on " + realm);
			}
			else
			{
				System.out.println(username + " not found on " + realm);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.exit(1);
		}
	}
	
	private void changePassword(String username,String oldpasswd,String newpasswd,String realm)
	{
		//WARNING: changePassword() does not check oldpasswd.

		try
		{
			boolean b = api.changePassword(username,oldpasswd,newpasswd,realm);
			if(b)
			{
				System.out.println("password changed successfully");
			}
			else
			{
				System.out.println("password not changed");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.exit(1);
		}
	}

	private void printUsersList(String realm)
	{
		try
		{
			Vector v = api.getUsers(realm);
			if(v == null)
			{
				System.out.println(realm + " not found");
			}
			else
			{
				for(int i = 0; i < v.size(); i++)
				{
					System.out.println("" + (i+1) + ". " + v.elementAt(i));
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.exit(1);
		}
	}

	private void printRealmList(String user)
	{
		try
		{
			Vector v = api.getRealm(user);
			if(v == null)
			{
				System.out.println("critical error");
			}
			else
			{
				if(v.size() == 0)
				{
					System.out.println("Realm vector size is zero");
				}
				for(int i = 0; i < v.size(); i++)
				{
					System.out.println("" + (i+1) + ". " + v.elementAt(i));
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.exit(1);
		}
	}

	public static void main(String args[])
	{
		testAuthAPI tapi = new testAuthAPI(args);
	}
}
