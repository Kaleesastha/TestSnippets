/* $Id: testLdap.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */

import java.util.*;

import javax.naming.*;
import javax.naming.directory.*;

/*
 * To start openldap server run /usr/local/libexec/slapd
 *
 * If you want to start the client independently,
 *    Root: 
 *        nmspermissiontree=permissionsstartpoint
 *    Properties:
 *        java.naming.provider.url="ldap://ldapserver:389"
 *        java.naming.factory.initial="com.sun.jndi.ldap.LdapCtxFactory"
 *        java.naming.security.authentication="simple"
 *        java.naming.security.principal="cn=root,o=myorganization,c=mycountry"
 *        java.naming.security.credentials="secret"
 */
public class testLdap
{
	private DirContext ctx;
	private Hashtable env;

	private boolean need_hasMore = false;

	public void parseArgs(String args[])
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
		//String security_principal = "cn=root";
		String security_principal = "cn=root,o=myorganization,c=mycountry";
		//String security_credentials= "secret123";
		String security_credentials= "secret12";
		//String security_credentials= "secret";
		String initctx= "com.sun.jndi.ldap.LdapCtxFactory";


			env = new Hashtable(5, 0.75f);
			env.put(Context.INITIAL_CONTEXT_FACTORY,initctx);
			env.put(Context.PROVIDER_URL, provider_url);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, security_principal);
			env.put(Context.SECURITY_CREDENTIALS, security_credentials);

			System.out.println("env = " + env);
	}

	public boolean initClient()
	{

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

	public void getAllPerm(StringBuffer tree_buff,String mysearchbase,int clevel)
	{

		SearchControls constraints = new SearchControls();
		constraints.setSearchScope(SearchControls.ONELEVEL_SCOPE);
		//String myfilter="objectclass=nmsperms";
		String myfilter="objectclass=*";
		String[] attrIDs = {"dn_name","short_name"};
		constraints.setReturningAttributes(attrIDs);
		boolean firstTime = true;
		String str="";

		try
		{
			NamingEnumeration results = ctx.search(mysearchbase,myfilter,constraints);

			while (results != null && results.hasMore()) 
			{
				SearchResult si = (SearchResult)results.next();

				Attributes attrs = si.getAttributes();
				Attribute attr = (Attribute)attrs.get("dn_name");
				Attribute n_attr = (Attribute)attrs.get("short_name");
				str = (String)attr.get();
				String str1 = (String)n_attr.get();
				tree_buff.append("\n");
				if(clevel > 0)
				{
					for(int i = 1; i < clevel;i++)
					{
						tree_buff.append("|");
						tree_buff.append("  ");
					}
					boolean hasm = false;
					try
					{
						if(need_hasMore)
						{
							hasm =results.hasMoreElements();
						}
					}
					catch(Exception ex)
					{
					}
					if(hasm)
					{
						tree_buff.append("|");
					}
					else
					{
						tree_buff.append("`");
					}
					tree_buff.append("--");

				}
				tree_buff.append(str1);
				getAllPerm(tree_buff,str,clevel+1);
			}
			clevel --;
			//tree_buff.append("<");
		}
		catch(NamingException ee)
		{
			System.out.println("Search failed" + ee.getMessage());
		}

		return;
		//return tree_buff.toString();

	}

	public String getAllUsers(String objcla)
	{

		String mysearchbase="nmsusertree=usersstartpoint";
		String myfilter="objectclass=" + objcla;
		String[] attrIDs = {"cn"};
		SearchControls constraints = new SearchControls();
		constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
		//constraints.setReturningAttributes(attrIDs);
		String treeString = "";


		try
		{
			NamingEnumeration results = ctx.search(mysearchbase,myfilter,constraints);

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
						treeString += str;
						treeString +="<>";
					}


				}

			}
			if(treeString.endsWith("<>"))
			{
				treeString = treeString.substring(0,(treeString.length() - 2));
			}

		}
		catch(NamingException ee)
		{
			//System.out.println("Search failed" + ee.getMessage());
		}
		return treeString;

	}

   public boolean addEntryToDir(String objcla,String usr_id,String surname,String[] permissions_arr,Vector listData)
   {
	   Attribute objClasses = new BasicAttribute("objectclass",objcla);
	   Attribute cn = new BasicAttribute("cn", usr_id);
	   Attributes orig = new BasicAttributes();
	   orig.put(objClasses);
	   orig.put(cn);

	   //if(surname.length() > 0)
	   //{ 
	   Attribute sn = new BasicAttribute("sn", surname);
	   orig.put(sn);
	   //}
	   if(listData.size() > 0)
	   {
		   Attribute groupname = new BasicAttribute("groupname",(String)listData.elementAt(0));
		   for(int i=1;i < listData.size();i++)
		   {
			   groupname.add((String)listData.elementAt(i));
		   }
		   orig.put(groupname);
	   }   
	   else
	   {
		   Attribute groupname = new BasicAttribute("groupname","");
		   orig.put(groupname);
	   }

	   if(permissions_arr.length > 0)
	   {   

		   Attribute users_permission = new BasicAttribute("users_permission",permissions_arr[0]);
		   for(int j=1;j < permissions_arr.length;j++)
		   {   
			   users_permission.add(permissions_arr[j]);
		   }       
		   orig.put(users_permission);
	   }           

	   String dn = "cn="+ usr_id +",nmsusertree=UsersStartPoint";

	   try 
	   {
		   ctx.createSubcontext(dn, orig);
	   }
	   catch (NameAlreadyBoundException e) 
	   {
		   System.out.println("Error: " + e);

			return false;
	   }
	   catch(NamingException ex)
	   {
		   System.out.println("Error: " + ex);
	   }

	  return true;
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

		testLdap t = new testLdap();

		t.parseArgs(args);
		boolean status = t.initClient();
		if(status == true)
		{
			System.out.println("LDAP server found.");
			StringBuffer sb = new StringBuffer();
			t.getAllPerm(sb,permissionsearchbase,0);
			System.out.println("Perm = " + sb.toString());
		}
		else
		{
			System.exit(1);
		}

		//String grpstr = t.getAllUsers("nmsgroups");
		String grpstr = t.getAllUsers("*");
		System.out.println("grpstr = " + grpstr);
		//String usrstr = t.getAllUsers("nmsloggers");
		//System.out.println("usrstr = " + usrstr);


		//String usr_id = "myuser2";
		//String surname = "mysurname2";
		//Vector listData  = new Vector();
		//listData.addElement("grp1");
		//String[] permissions_arr = {"myperm1"};
   		//t.addEntryToDir("nmsloggers",usr_id,surname,permissions_arr,listData);


		//dn = "nms_perm_level=modify_topology,nms_perm_level=topology,nms_perm_level=databaseoperations,nmspermissiontree=PermissionsStartPoint";


		//t.printUserPermission("root");
		//t.printUserPermission("unknownuser");

		System.exit(0);

	}
}
