/* $Id: testAddUser.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */

import java.rmi.*;

import com.adventnet.nms.authentication.AuthenticationAPI;

public class testAddUser
{
	public static void main(String args[])
	{
		String host ="10.232.114.5";
		String port = "1099";
		String username = null;
		String passwd = null;

		for(int i = 0; i < args.length; i++)
		{
			if(args[i].equals("-u"))
			{
				username = args[i+1];
				i++;
			}
			else if(args[i].equals("-p"))
			{
				passwd = args[i+1];
				i++;
			}
		}

		if(username == null || passwd == null)
		{
			System.out.println("Usage: testAddUser -u username -p passwd");
			System.exit(1);
		}

		try
		{
			String lookupstr = "rmi://" + host +":"+ port+"/"+ "AuthAPI";
			System.out.println("lookupstr =" + lookupstr);
			AuthenticationAPI api = (AuthenticationAPI)Naming.lookup(lookupstr);
			System.out.println(api);
			System.out.println("trying to add user");
			api.createNewAccount(username,passwd,"Admin");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		System.out.println("....");
	}
}
