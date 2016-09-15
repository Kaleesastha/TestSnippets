/* $Id: testLdapServer.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */

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
public class testLdapServer
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
		String security_principal = "cn=root";
		//String security_principal = "cn=root,o=myorganization,c=mycountry";
		//String security_principal = "o=myorganization,c=mycountry";
		//String security_credentials= "secret";
		String security_credentials= "secret123";
		String initctx= "com.sun.jndi.ldap.LdapCtxFactory";
		String security_authentication="simple";


			env = new Hashtable(5, 0.75f);
			env.put(Context.INITIAL_CONTEXT_FACTORY,initctx);
			env.put(Context.PROVIDER_URL, provider_url);
			env.put(Context.SECURITY_AUTHENTICATION,security_authentication); 
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

	public static void main(String args[])
	{
		String permissionsearchbase="nmspermissiontree=permissionsstartpoint";

		testLdapServer t = new testLdapServer();

		t.parseArgs(args);
		boolean status = t.initClient();
		if(status == true)
		{
			System.out.println("LDAP server found.");
		}
		else
		{
			System.exit(1);
		}

		System.exit(0);

	}
}
