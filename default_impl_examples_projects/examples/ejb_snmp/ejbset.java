/* $Id: ejbset.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/*
 * @(#)ejbset.java
 * Copyright (c) 1996-2001 AdventNet, Inc. All Rights Reserved.
 * Please read the COPYRIGHTS file for more details.
 */

/** 
 *  Use ejb to do an SNMP Set based on command line arguments.  Loads MIBs 
 *  as specified, and converts to/from names for loaded MIB data.
 *  The ejb server is specified with the -SnmpEJBServer option
 *  and defaults to localhost if not specified.
 *  Since we do not ask user for variable type, in order to
 *  convert set value strings to the correct variable type, the  
 *  MIB corresponding to any object must be loaded.  
 *  Once a MIB is loaded on the server, susequent uses of
 *  This program will have the MIB available for SNMP operations.
 *
 * java ejbset [options] host oid ...
 *
 *<img SRC="images/v3only.jpg" ALT="v3 only"> v3 request:
 * java ejbset [-SnmpEJBServer hostName] [-v version(v1,v2,v3)]  [-c community] [-p port] [-t timeout] [-r retries] [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password] [-i contextID] [-n contextName] -m MIB_files host OID value ...
 * e.g. 
 * java ejbset -v v3 -m ..\..\mibs\rfc1213-MIB -u user -a MD5 -w passwd localhost 1.2.0 string
 *
 *<img SRC="images/v2candv3only.jpg" ALT="v2c and v3 only"> v2c request:
 * java ejbset [-SnmpEJBServer hostName] [-v version(v1,v2)]  [-c community] [-p port] [-t timeout] [-r retries] -m MIB_files host OID value ...
 * e.g. 
 * java ejbset -v v2 -m ..\..\mibs\rfc1213-MIB localhost 1.2.0 string
 *
 * v1 request:
 * java ejbset [-SnmpEJBServer hostName]  [-c community] [-p port] [-t timeout] [-r retries] -m MIB_files host OID value ...
 * e.g. 
 * java ejbset -m ..\..\mibs\rfc1213-MIB localhost 1.5.0 string
 *
 * Options:
  * [-SnmpEJBServer ] <hostName>      - SnmpEJBServer SERVER option to specify the ejb server host.
 * [-ORBInitialPort ] <orbPort>   - port on which transient name server is running
 * [-ORBInitialHost ] <orbHost>   - host name of transient name server
 * [-c] <community>    - community String. By default "public".
 * [-p] <port>         - remote port no. By default 161.
 * [-t] <Timeout>      - Timeout. By default 5000ms.
 * [-r] <Retries>      - Retries. By default 0. 
 * [-m] <MIBfile>      - MIB files.To load multiple mibs give within double quotes files seperated by a blank space.       
 *<img SRC="images/v3only.jpg" ALT="v3 only"> [-v] <version>      - version(v1 / v2 / v3). By default v1.
 * [-u] <username>     - The v3 principal/userName
 * [-a] <autProtocol>  - The authProtocol(MD5/SHA). Mandatory if authPassword is specified
 * [-w] <authPassword> - The authentication password.
 * [-s] <privPassword> - The privacy protocol password. Must be accompanied with auth password and authProtocol fields.
 * [-n] <contextName>  - The context to be used for the v3 pdu.
 * [-i] <contextID>    - The contextID to be used for the v3 pdu.
 * host Mandatory      - The RemoteHost (agent).Format (string without double qoutes/IpAddress).
 * OID  Mandatory      - Give multiple no. of Object Identifiers.
 * value Mandatory     - value to be set
 */

import com.adventnet.snmp.ejb.*;
import javax.naming.*;
import weblogic.jndi.*;

public class ejbset  {

	static final int USM_SECURITY_MODEL = 3;

	public static void main(String args[])	{
		// Take care of getting options
        String usage = "ejbset [-SnmpEJBServer hostName] [-v version(v1,v2,v3)]  [-c community] [-p port] [-t timeout] [-r retries] [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password] [-i contextID] [-n contextName] -m MIB_files host OID value ...";
        String options[] = { "-SnmpEJBServer", "-c", "-p", "-r", "-t", "-m", "-v", "-u", "-a", "-w", "-s" ,"-i","-n"};
        String values[] = { null, null, null, null, null, null, null, null,null,null,null,null,null};

        String userName = new String("");
        String authProtocol = new String("NO_AUTH");
        String authPassword = new String ("");
        String privPassword = new String ("");
        String contextName = new String ("");
        String contextID = new String ("");

      
    ParseOptions opt = new ParseOptions(args,options,values, usage);

    
    // check for at least hostname and one OID in remaining arguments
    if (opt.remArgs.length<3) opt.usage_error();
	
	 //This block performs a lookup on the EJBComponent.
	//The lookup is specific to the ApplicationServer used.

	try {
	    Environment env = new Environment();
		if(values[0]!=null)
		  env.setProviderUrl(values[0]+"/SnmpTargetEJB");
		else env.setProviderUrl("t3://localhost:7001/SnmpTargetEJB");
	    Context ctx = env.getInitialContext();
		
	    SnmpTargetHome home = (SnmpTargetHome) ctx.lookup("SnmpTargetEJB");
		SnmpTarget target = (SnmpTarget) home.create();
	
	    if(values[6] != null) {  // if SNMP version is specified, set it
        if(values[6].equals("v1"))
	      target.setSnmpVersion( com.adventnet.snmp.ejb.SnmpTarget.VERSION1 ) ;
        else if(values[6].equals("v2"))
            target.setSnmpVersion( com.adventnet.snmp.ejb.SnmpTarget.VERSION2C );
        else if(values[6].equals("v3"))
            target.setSnmpVersion( com.adventnet.snmp.ejb.SnmpTarget.VERSION3 );
				
        else {
            System.out.println("Invalid Version Number");
            System.exit(1);
        }
        }

        target.setTargetHost( opt.remArgs[0] );  // set the agent hostname

        if (values[1] != null) // set the community if specified
        target.setCommunity( values[1] );

        try { // set the timeout/retries/port parameters, if specified
        if (values[2] != null)
            target.setTargetPort( Integer.parseInt(values[2]) );
        if (values[3] != null)
            target.setRetries( Integer.parseInt(values[3]) );
        if (values[4] != null)
            target.setTimeout( Integer.parseInt(values[4]) );
        if (values[7] != null)
            userName = values[7];
        if (values[8] != null) 
            authProtocol = values[8];
        if (values[9] != null)
        authPassword = values[9];
        if (values[10] != null) 
        privPassword = values[10];
		if (values[11] != null)
		contextID = values[11];
		if (values[12] != null)
		contextName = values[12];
        } catch (NumberFormatException ex) {
        System.err.println("Invalid Integer Argument "+ex);
        }
        if(target.getSnmpVersion() == target.VERSION3) {

        target.setPrincipal(userName);
        target.setAuthPassword(authPassword);
        target.setPrivPassword(privPassword);
        if(authProtocol.equals("SHA"))
        target.setAuthProtocol(target.SHA_AUTH);
        else
        target.setAuthProtocol(target.MD5_AUTH);
        target.setContextID(contextID);
		target.setContextName(contextName);
        // Create the SNMPv3 tables.
		 target.create_v3_tables();
	}
		  


        if (values[5] != null) try  {     // Load the MIB files
        System.err.println("Loading MIBs: "+values[5]);
        target.loadMibs(values[5]);
		System.err.println("Loaded MIBs: "+ values[5]);
        } catch (Exception ex) {
        System.err.println("Error loading MIBs: "+ex);
        }
        else
            opt.usage_error();    


        // Put together OID and variable value lists from command line
        String oids[] = null, var_values[] = null;  // trap oids and values

        int num_varbinds = 0;
        for (int i=1;i<opt.remArgs.length;i+=2) { // add Variable Bindings
        if (opt.remArgs.length < i+2) //need "{OID type value}"
            opt.usage_error(); // unmatched arguments for name-value pairs
        num_varbinds++;
        }

        oids = new String[num_varbinds];
        var_values = new String[num_varbinds];
        for (int i=0;i<num_varbinds;i++) { // add Variable Bindings
        oids[i] = opt.remArgs[(2*i)+1];
        var_values[i] = opt.remArgs[(2*i)+2];
        }

        target.setObjectIDList(oids);  // set OID list on SnmpTarget
        String result[] = target.snmpSetList(var_values);

        if (result == null) {
            System.err.println("Request failed or timed out. \n"+
                   target.getErrorString());

        } else { // print response
            System.out.println("Set OK.  Return values:");

        for (int i=0;i<oids.length;i++) { 
            System.out.println(target.getObjectID(i) +": "+result[i]);
        }
        }

    } catch( Exception e ){
        System.err.println( "Exception:" + e );
        e.printStackTrace();
    }

    System.exit(0);
    
    }

}
