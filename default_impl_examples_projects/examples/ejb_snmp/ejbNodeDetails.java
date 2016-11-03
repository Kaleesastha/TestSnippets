/* $Id: ejbNodeDetails.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/*
 * @(#)ejbNodeDetails.java
 * Copyright (c) 1996-2001 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 */

/**
 *  Use ejb to do a get node details based on the mibs loaded.
 *  The ejb server is specified with the -EJBServer option
 *  and defaults to localhost if not specified.
 *  Once a MIB is loaded on the server, its is available for subsequent
 *  operations.  
 *
 * java ejbNodeDetails -m MIB_FILES  MibNode
 *
 */
import com.adventnet.snmp.ejb.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;
import javax.naming.*;
import weblogic.jndi.*;
import java.util.Hashtable;
import java.util.Vector;

public class ejbNodeDetails  {
	
	
	
	public static void main(String args[])	{
		// Take care of getting options
        String usage = "ejbNodeDetails [-SnmpEJBServer hostName] [-m MIB_files] MibNode ";
        String options[] = { "-SnmpEJBServer",  "-m"};
        String values[] = { null, null};

           ParseOptions opt = new ParseOptions(args,options,values, usage);

    // check for at least hostname and one OID in remaining arguments
    if (opt.remArgs.length<1) opt.usage_error();	
	
	//This block performs a lookup on the EJBComponent.
	//The lookup is specific to the ApplicationServer used.
	 
	try {
	    Environment env = new Environment();
		if(values[0]!=null)
		  env.setProviderUrl(values[0]+"/MibOperationsEJB");
		else env.setProviderUrl("t3://localhost:7001/MibOperationsEJB");
	    Context ctx = env.getInitialContext();
		 
	    MibOperationsHome home = (MibOperationsHome) ctx.lookup("MibOperationsEJB");
		com.adventnet.snmp.ejb.MibOperations mibops = (com.adventnet.snmp.ejb.MibOperations) home.create();
		try	{	
			mibops.loadMibModules(values[1]);
			System.out.println("loaded "+ values[1]+ "mib");
		} catch (Exception e)  {
            System.out.println("Exception : "+e);
            System.exit(1);
        }
      
	  
		SnmpOID oid = mibops.getSnmpOID(opt.remArgs[0]);
	    MibNode node = mibops.getMibNode(oid);
	    System.out.println("Syntax:"+node.getSyntax()+"\n"+
                   "Access:"+node.printAccess()+"\n"+
                   "Status:"+node.getStatus()+"\n"+
                   "Reference:"+node.getReference()+"\n"+
                   "OID:"+node.getNumberedOIDString()+"\n"+
                   "Node:"+node.getOIDString()+"\n"+
                   "Description:"+node.getDescription()+"\n");
			   
	   } catch( Exception e ){
        System.err.println( "Exception:" + e );
        e.printStackTrace();
		}
	}		
}