/* $Id: ejbwalk.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/*
 * @(#)ejbwalk.java
 * Copyright (c) 1996-2001 AdventNet, Inc. All Rights Reserved.
 * Please read the COPYRIGHTS file for more details.
 */

/** 
 *  Use ejb to do an SNMP walk based on command line arguments.  
 *  The ejb server is specified with the -SnmpEJBServer option
 *  and defaults to localhost if not specified.
 *
 *  Loads MIBs as specified, and converts to/from names for loaded MIB data.
 *  Multiple OIDs are ok, but checking for when to stop
 *  is based on first OID. 
 *
 *  Once a MIB is loaded on the server, susequent uses of
 *  this program will have the MIB available for SNMP operations.
 *    
 *
 * java ejbwalk [options] host oid ...
 *
 *<img SRC="images/v3only.jpg" ALT="v3 only"> v3 request:
 * java ejbwalk [-SnmpEJBServer hostName] [-v version(v1,v2,v3)] [-m MIB_files] [-c community] [-p port] [-t timeout] [-r retries] [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password] [-i contextID] [-n contextName] host OID [OID] ...
 * e.g. 
 * java ejbwalk -v v3 -m ..\..\mibs\rfc1213-MIB -u user -a MD5 -w passwd localhost 1.2
 *
 *<img SRC="images/v2candv3only.jpg" ALT="v2c and v3 only"> v2c request:
 * java ejbwalk [-SnmpEJBServer hostName] [-v version(v1,v2)] [-m MIB_files] [-c community] [-p port] [-t timeout] [-r retries] host OID [OID] ...
 * e.g. 
 * java ejbwalk -v v2 -m ..\..\mibs\rfc1213-MIB localhost 1.2
 *
 * v1 request:
 * java ejbwalk [-SnmpEJBServer hostName] [-m MIB_files] [-c community] [-p port] [-t timeout] [-r retries] host OID [OID] ...
 * e.g. 
 * java ejbwalk -m ..\..\mibs\rfc1213-MIB localhost 1.2
 *
 * Options:
 * [-SnmpEJBServer ] <hostName>      - SnmpEJBServer SERVER option to specify the ejb server host.
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
 * [-i] <contextID>    - The contextID to be used for the v3 pdu.
 * [-n] <contextName>  - The context to be used for the v3 pdu.
 * host Mandatory      - The RemoteHost (agent).Format (string without double qoutes/IpAddress).
 * OID  Mandatory      - Give multiple no. of Object Identifiers.
 **/


import com.adventnet.snmp.ejb.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;
import javax.naming.*;
import weblogic.jndi.*;

public class ejbwalk {

	
	static final int USM_SECURITY_MODEL = 3;

    public static void main(String args[]) {

    // Take care of getting options
        String usage = "ejbwalk [-SnmpEJBServer hostName] [-v version(v1,v2,v3)] [-m MIB_files] [-c community] [-p port] [-t timeout] [-r retries] [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password] [-i contextID] [-n contextName]  host OID [OID] ...";
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
    if (opt.remArgs.length<2) opt.usage_error();

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
        } catch (Exception ex) {
        System.err.println("Error loading MIBs: "+ex);
        }

        
        // create OID array from command line arguments
        String oids[] = new String[opt.remArgs.length-1];
        for (int i=1;i<opt.remArgs.length;i++) oids[i-1] = opt.remArgs[i];
    
        // Set the OID List on the SnmpTarget instance
        target.setObjectIDList(oids);

        int maxtry = 0;
        SnmpOID rootoid = target.getSnmpOID();
		System.out.println(rootoid.toString());
		
		
        while ( maxtry++ < 1000) {  // limit the max getnexts to 1000

        String result[] = target.snmpGetNextList();
        if (result == null) break;

        if ( !com.adventnet.snmp.beans.SnmpTarget.isInSubTree(rootoid,target.getSnmpOID() ) )
            break;  // check first column

        if (maxtry == 1) System.out.println("Response received: ");

        for (int i=0;i<result.length;i++) {  // print the values
            System.out.println(target.getObjectID(i) + ": " + result[i]);
        }

        }

        if (maxtry == 1) {  // we did not get a valid row
        System.err.println("Request failed, timed out or no available data. \n"+
                   target.getErrorString());
        } 
		

    }  catch (Exception e) {
        System.err.println("Error : "+e.getMessage());
    }

    System.exit(0);

    }

}
