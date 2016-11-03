
/*<img SRC="images/v2candv3only.jpg" ALT="v2c and v3 only"> $Id: ejbgetbulk.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/*<img SRC="images/v2candv3only.jpg" ALT="v2c and v3 only">
 * @(#)ejbgetbulk.java
 * Copyright (c) 1996-2001 AdventNet, Inc. All Rights Reserved.
 * Please read the COPYRIGHTS file for more details.
 */

 /** <img SRC="images/v2candv3only.jpg" ALT="v2c and v3 only">
 *  Use ejb to do an SNMP Getbulk based on command line arguments.  
 *  The ejb server is specified with the -SnmpEJBServer option
 *  and defaults to localhost if not specified.
 *
 *  Loads MIBs as specified, and converts to/from names for loaded MIB data. 
 *  Once a MIB is loaded on the server, susequent uses of
 *  this program will have the MIB available for SNMP operations.
 *
 * java ejbgetbulk [options] host oid ...
 *
 *<img SRC="images/v3only.jpg" ALT="v3 only"> v3 request:
 * java ejbgetbulk [-SnmpEJBServer hostName] [-v version(v1,v2,v3)] [-m MIB_files] [-c community] [-p port] [-t timeout] [-r retries] [-nr non-repeaters] [-mr max-repetitions]  [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password] [-i contextID] [-n contextName] host OID [OID] ...
 * e.g. 
 * java ejbgetbulk -v v3 -m ..\..\mibs\rfc1213-MIB -u user -a MD5 -w passwd localhost 1.2.0
 *
 *<img SRC="images/v2candv3only.jpg" ALT="v2c and v3 only"> v2c request:
 * java ejbgetbulk [-SnmpEJBServer hostName] [-m MIB_files] [-c community] [-p port] [-t timeout] [-r retries] [-nr non-repeaters] [-mr max-repetitions] host OID [OID] ...
 * e.g. 
 * java ejbgetbulk -v v2 -m ..\..\mibs\rfc1213-MIB localhost 1.2.0
 *
 *<img SRC="images/v2candv3only.jpg" ALT="v2c and v3 only">
 * Options:
 * [-SnmpEJBServer ] <hostName>      - SnmpEJBServer SERVER option to specify the ejb server host.
 * [-c] <community>    - community String. By default "public".
 * [-p] <port>         - remote port no. By default 161.
 * [-t] <Timeout>      - Timeout. By default 5000ms.
 * [-r] <Retries>      - Retries. By default 0. 
 * [-m] <MIBfile>      - MIB files.To load multiple mibs give within double quotes files seperated by a blank space.       
 * [-nr] <non-repeators - value of non-repeators
 * [-mr] <max-repeators - value of max-repeators
 *<img SRC="images/v3only.jpg" ALT="v3 only"> [-v] <version>      - version(v1 / v2 / v3). By default v1.
 * [-u] <username>     - The v3 principal/userName
 * [-a] <autProtocol>  - The authProtocol(MD5/SHA). Mandatory if authPassword is specified
 * [-w] <authPassword> - The authentication password.
 * [-s] <privPassword> - The privacy protocol password. Must be accompanied with auth password and authProtocol fields.
 * [-n] <contextName>  - The context to be used for the v3 pdu.
 * [-i] <contextID>    - The contextID to be used for the v3 pdu.
 *<img SRC="images/v2candv3only.jpg" ALT="v2c and v3 only"> host Mandatory      - The RemoteHost (agent).Format (string without double qoutes/IpAddress).
 * OID  Mandatory      - Give multiple no. of Object Identifiers.
 **/



import com.adventnet.snmp.ejb.*;
import com.adventnet.snmp.snmp2.*;
import javax.naming.*;
import weblogic.jndi.*;

public class ejbgetbulk {
	
	static final int USM_SECURITY_MODEL = 3;
	
	public static void main(String args[])	{
		  
	String usage = "ejbgetbulk[-SnmpEJBServer hostName] [-v version(v1,v2,v3)] [-m MIB_files] [-c community] [-p port] [-t timeout] [-r retries] [-nr non-repeaters] [-mr max-repetitions]  [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password] [-i contextID] [-n contextName] host OID [OID] ...";
    String options[] = { "-SnmpEJBServer", "-c", "-p", "-r", "-t", "-m", "-nr", "-mr" , "-u", "-a", "-w", "-s", "-v" ,"-i", "-n"};
    String values[] = { null, null, null, null, null, null, null, null,null,null,null,null,null,null,null};

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
        if(values[0] != null)
		if(values[0]!=null)
		  env.setProviderUrl(values[0]+"/SnmpTargetEJB");
		else env.setProviderUrl("t3://localhost:7001/SnmpTargetEJB");
	    Context ctx = env.getInitialContext();
		
	    SnmpTargetHome home = (SnmpTargetHome) ctx.lookup("SnmpTargetEJB");
		SnmpTarget target = (SnmpTarget) home.create();
        if(values[12] != null) {  // if SNMP version is specified, set it
        if(values[12].equals("v2"))
            target.setSnmpVersion( com.adventnet.snmp.ejb.SnmpTarget.VERSION2C ) ;
        else if(values[12].equals("v1"))
            target.setSnmpVersion( com.adventnet.snmp.ejb.SnmpTarget.VERSION1 );
        else if(values[12].equals("v3"))
            target.setSnmpVersion( com.adventnet.snmp.ejb.SnmpTarget.VERSION3 );
        else {
            System.out.println("Invalid Version Number");
            System.exit(1);
        }
        }
        int nonRepeaters = 0; // we need this for printing
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
        if (values[6] != null) 
            nonRepeaters = Integer.parseInt(values[6]);
        target.setNonRepeaters( nonRepeaters );
        if (values[7] != null) 
            target.setMaxRepetitions( Integer.parseInt(values[7]) );
            if (values[8] != null)
            userName = values[8];
        if (values[9] != null) {
        System.out.println("authProtocol = " + authProtocol);
        authProtocol = values[9];
        }
        if (values[10] != null)
        authPassword = values[10];
        if (values[11] != null) {
        privPassword = values[11];
        }
		  if (values[13] != null)
		  contextID = values[13];
		  if (values[14] != null)
		  contextName = values[14];
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
		  	target.setContextName(contextName);
		 		target.setContextID(contextID);
		  
				// Create the SNMPv3 tables.
			  target.create_v3_tables();
        }
        if (values[5] != null) try { // Load the MIB files 
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

        String result[][] = target.snmpGetBulkList(); // do a getbulk request
    
        if (result == null) {
        System.err.println("Request failed or timed out. \n"+
                   target.getErrorString());

        } else { // print the values
        System.out.println("Response received.  Values:\n");

        for (int i=0;i<nonRepeaters;i++)
            System.out.println(result[i][0]);
        
        StringBuffer sb = new StringBuffer("\nRepeaters:\n");
        
        for (int j=0;j<result[0].length;j++) {
            for (int i=nonRepeaters;i<oids.length;i++) { 
            sb.append(result[i][j]  + " \t ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
        }

    }  catch (Exception e) {
        System.err.println("Error : "+e.getMessage());
    }

    System.exit(0);

    }

}
