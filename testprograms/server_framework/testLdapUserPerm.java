/* $Id: testLdapUserPerm.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */

import java.util.*;

import javax.naming.*;
import javax.naming.directory.*;

public class testLdapUserPerm
{
	private DirContext ctx;
	private Hashtable env;


	public boolean initClient(String args[])
	{

		String hostname = "localhost";

		for(int i = 0; i < args.length; i++)
		{
			if(args[i].equals("-h"))
			{
				hostname=args[i+1];
				i++;
			}
		}
		String provider_url = "ldap://" + hostname +":389";
		String security_principal = "cn=root,o=myorganization,c=mycountry";
		String security_credentials= "secret";
		String initctx= "com.sun.jndi.ldap.LdapCtxFactory";


		env = new Hashtable(5, 0.75f);
		env.put(Context.INITIAL_CONTEXT_FACTORY,initctx);
		env.put(Context.PROVIDER_URL, provider_url);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, security_principal);
		env.put(Context.SECURITY_CREDENTIALS, security_credentials);



		try
		{
			ctx = new InitialDirContext(env);
		}
		catch(Exception e)
		{
			System.err.println("Init failed, LDAP server may be down\n");
			System.err.println(e.getMessage());
			return false;
		}

		return true;
	}

	public void printAllUsers(String objcla)
	{

		String mysearchbase="nmsusertree=usersstartpoint";
		String myfilter="objectclass=" + objcla;
		String[] attrIDs = {"cn"};
		SearchControls constraints = new SearchControls();
		constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
		constraints.setReturningAttributes(attrIDs);
		String userlist= "";


		try
		{
			NamingEnumeration results
				= ctx.search(mysearchbase,myfilter,constraints);

			while (results != null && results.hasMore()) 
			{
				SearchResult si = (SearchResult)results.next();

				Attributes attrs = si.getAttributes();

				if (attrs == null) 
				{
					//System.out.println("No attributes");
				}
				else 
				{
					Attribute attr = (Attribute)attrs.get("cn");

					Enumeration vals = attr.getAll();

					for (;vals.hasMoreElements();)
					{
						String str =  (String)vals.nextElement();
						//if(objcla.equals("nmsloggers"))
						//{
						//	EditorPanel.userVector.addElement(str);
						//}
						//else if(objcla.equals("nmsgroups"))
						//{
						//	EditorPanel.groupVector.addElement(str);
						//}
						userlist += str;
						userlist +=",";
					}


				}

			}

		}
		catch(NamingException ee)
		{
			//System.out.println("Search failed" + ee.getMessage());
		}

		System.out.println("List of users: " + userlist);

	}


	public void printUserPermission(String username)
	{

		String mysearchbase="nmsusertree=usersstartpoint";

		Attributes matchAttrs = new BasicAttributes(true);
		matchAttrs.put(new BasicAttribute("cn", username));
		String[] attrIDs = {"users_permission"};

		Enumeration vals = null;

		try
		{
			NamingEnumeration results = ctx.search(mysearchbase, matchAttrs, attrIDs);

			while (results != null && results.hasMore()) 
			{
				SearchResult si = (SearchResult)results.next();

				Attributes attrs = si.getAttributes();
				if (attrs == null) 
				{
					//System.out.println("No attributes");
				}
				else 
				{
					Attribute attr = (Attribute)attrs.get("users_permission");

					vals = attr.getAll();
				}
			}
		}
		catch(NamingException ee)
		{
			System.out.println("Search failed" + ee.getMessage());
			return;
		}

		if(vals == null)
		{
			System.out.println("Error: " + username + " user not found");
			return;
		}

		int count = 0;
		System.out.println("Permissions for " + username);
		for(; vals.hasMoreElements(); )
		{
			String str = (String) vals.nextElement();
			System.out.println(" " + count + " "  + str);
			count ++;
		}

	}

	public static void main(String args[])
	{
		String permissionsearchbase="nmspermissiontree=permissionsstartpoint";

		testLdapUserPerm t = new testLdapUserPerm();

		boolean status = t.initClient(args);
		if(status == true)
		{
			System.out.println("LDAP server found.");
		}
		else
		{
			System.exit(1);
		}

		t.printAllUsers("nmsloggers");

		t.printUserPermission("group1user");
		t.printUserPermission("unknownuser");

		System.exit(0);

	}
}
