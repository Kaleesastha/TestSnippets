/* $Id: ejbtable.java,v 1.2 2008/09/25 14:03:53 tinku Exp $ */
/*
 * @(#)ejbtable.java
 * Copyright (c) 1998 AdventNet, Inc. All Rights Reserved.
 * Please read the COPYRIGHTS file for more details.
 */

/** 
 *  This is an example of using the SnmpTable class.
 *  This is a command line application that does not require JFC/swing
 *  components.  See the swingapps directory for examples that need
 *  and use swing components.
 *
 * java ejbtable [options] host tableoid ...
 *<img SRC="images/v3only.jpg" ALT="v3 only">
 * v3 request:
 * java ejbtable [-SnmpServer hostName] [-v version(v1,v2,v3)] [-m MIB_files] [-c community] [-p port] [-t timeout] [-r retries] [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password] [-i contextID] [-n contextName] host tableOID ...
 * e.g. 
 * java ejbtable -v v3 -m ..\..\mibs\rfc1213-MIB -u user -a MD5 -w passwd localhost tcpConnTable
 *
 *<img SRC="images/v2candv3only.jpg" ALT="v2c and v3 only"> v2c request:
 * java ejbtable [-SnmpServer hostName] [-v version(v1,v2)] [-m MIB_files] [-c community] [-p port] [-t timeout] [-r retries] host OID [OID] ...
 * e.g. 
 * java ejbtable -v v2 -m ..\..\mibs\rfc1213-MIB localhost tcpConnTable
 *
 * v1 request:
 * java ejbtable [-SnmpServer hostName] [-m MIB_files] [-c community] [-p port] [-t timeout] [-r retries] host OID [OID] ...
 * e.g. 
 * java ejbtable -m ..\..\mibs\rfc1213-MIB localhost tcpConnTable
 *
 * Options:
 * [-SnmpServer ] <hostName>      - SnmpServer SERVER option to specify the ejb server host.
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
 * tableOID  Mandatory      - Give multiple no. of table Object Identifiers.
 **/

import com.adventnet.snmp.ejb.*;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;
import javax.naming.*;
import weblogic.jndi.*;

public class ejbtable implements com.adventnet.snmp.rmi.SnmpTableListener {

	static final int USM_SECURITY_MODEL = 3;
	
	static SnmpTable table;

	public static void main(String args[])	{
		// Take care of getting options
        String usage = "ejbtable [-SnmpEJBServer hostName] [-v version(v1,v2,v3)] [-m MIB_files] [-c community] [-p port] [-t timeout] [-r retries] [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password] [-i contextID] [-n contextName] host OID [OID] ...";
        String options[] = { "-SnmpEJBServer", "-c", "-p", "-r", "-t", "-m", "-v", "-u", "-a", "-w", "-s" ,"-i" , "-n"};
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
		  env.setProviderUrl(values[0]+"/SnmpTableEJB");
		else env.setProviderUrl("t3://localhost:7001/SnmpTableEJB");
	    Context ctx = env.getInitialContext();
		
	    SnmpTableHome home = (SnmpTableHome) ctx.lookup("SnmpTableEJB");
		table = (SnmpTable) home.create();


	    if(values[6] != null) {  // if SNMP version is specified, set it
        if(values[6].equals("v1"))
	      table.setSnmpVersion( com.adventnet.snmp.ejb.SnmpTable.VERSION1 ) ;
        else if(values[6].equals("v2"))
            table.setSnmpVersion( com.adventnet.snmp.ejb.SnmpTable.VERSION2C );
        else if(values[6].equals("v3"))
            table.setSnmpVersion( com.adventnet.snmp.ejb.SnmpTable.VERSION3 );
				
        else {
            System.out.println("Invalid Version Number");
            System.exit(1);
        }
        }
        table.setTargetHost( opt.remArgs[0] );  // set the agent hostname

        if (values[1] != null) // set the community if specified
        table.setCommunity( values[1] );

        try { // set the timeout/retries/port parameters, if specified
        if (values[2] != null)
            table.setTargetPort( Integer.parseInt(values[2]) );
        if (values[3] != null)
            table.setRetries( Integer.parseInt(values[3]) );
        if (values[4] != null)
            table.setTimeout( Integer.parseInt(values[4]) );
        if (values[7] != null)
            userName = values[7];
        if (values[8] != null) 
            authProtocol = values[8];
        if (values[9] != null)
        authPassword = values[9];
        if (values[10] != null) 
        privPassword = values[10];
        if (values[11] != null) 
        contextID  = values[11];
        if (values[12] != null) 
        contextName  = values[12];
                } catch (NumberFormatException ex) {
        System.err.println("Invalid Integer Argument "+ex);
        }

        if(table.getSnmpVersion() == com.adventnet.snmp.ejb.SnmpTarget.VERSION3) {        
        table.setPrincipal(userName);
        table.setAuthPassword(authPassword);
        table.setPrivPassword(privPassword);
        if(authProtocol.equals("SHA"))
        table.setAuthProtocol(com.adventnet.snmp.ejb.SnmpTarget.SHA_AUTH);
        else
        table.setAuthProtocol(com.adventnet.snmp.ejb.SnmpTarget.MD5_AUTH);
	table.setContextName(contextName);
	table.setContextID(contextID);	// Create the SNMPv3 tables.
	table.create_v3_tables();
	}



        if (values[5] != null)     try { // Load the MIB files 
        System.out.println("Loading MIBs: "+values[5]);
        table.loadMibs(values[5]);
        System.out.println("Done.");
        } catch (Exception ex) {
        System.err.println("Error loading MIBs: "+ex);
        }

   
		 
        // instantiate this class to receive table events, and register it
        com.adventnet.snmp.rmi.SnmpTableListener gettable = new ejbtable();
        weblogic.rmi.server.UnicastRemoteObject.exportObject(gettable,0);
        table.addSnmpTableListener(gettable);


        // Set the table OID in SnmpTable.  Once specified,
        // SnmpTable will automatically get the data and send table events 
		while(true){
        try { 
        table.setTableOID( opt.remArgs[1] );
		table.setPollInterval(30);
        } catch (Exception ex) {
        System.err.println("Invalid table OID: "+ex);
        System.exit(1);
        }


        System.out.println("Getting table.  Table items:");
		

        try { Thread.sleep(table.getPollInterval()*1000); }  // allow some time to get all rows
        catch (InterruptedException ex) {}  
		}

    } catch (Exception ex) {
        System.err.println("Failed: "+ ex);
        ex.printStackTrace();
    }
	
    System.exit(0);

    }


    /* This is the listener method which is notified of table changes **/
    public void tableChanged(com.adventnet.snmp.beans.SnmpTableEvent e) {
	   
	      
    try {
        StringBuffer sb = new StringBuffer();
        
        if (e.getFirstRow() == 0) {  // we're being notified of first row
        for (int i=0;i<table.getColumnCount();i++)  // print column names
            sb.append(table.getColumnName(i)+" \t");
        System.out.println(sb.toString());
        }

        if (table.getRowCount() == 0) { // no rows and we're being notified
        System.err.println("Request failed or timed out. \n"+
                   table.getErrorString());

        } else { // print the rows we're getting
        sb = new StringBuffer();
        for (int j=e.getFirstRow();j<=e.getLastRow();j++) { 
            for (int i=0;i<table.getColumnCount();i++) 
            sb.append(table.getValueAt(j,i)+" \t");
        }
        System.out.println(sb.toString());
        }
    } catch (Exception ex) {
        System.err.println("Failed: "+ ex);
        ex.printStackTrace();
    }
    }
}
