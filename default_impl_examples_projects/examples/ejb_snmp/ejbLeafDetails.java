/* $Id: ejbLeafDetails.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/*
 * @(#)ejbLeafDetails.java
 * Copyright (c) 1996-2001 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 */

/**
 *  Use ejb to do a get leafnode details based on the mibs loaded.
 *  The ejb server is specified with the -EJBServer option
 *  and defaults to localhost if not specified.
 *  This allows loading MIBs as specified, and converts to/from
 *  names for loaded MIB data.
 *  Once a MIB is loaded on the server, its is available for subsequent
 *  operations.  
 *
 * java ejbLeafDetails [options]  -m MIB_FILES  Numeric-Leaf-OID
 *
 */
import com.adventnet.snmp.ejb.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;
import javax.naming.*;
import weblogic.jndi.*;
import java.util.Hashtable;
import java.util.Vector;

public class ejbLeafDetails  {
	
	static final int USM_SECURITY_MODEL = 3;
	
	public static void main(String args[])	{
		// Take care of getting options
        String usage = "ejbLeafDetails [-SnmpEJBServer hostName] [-m MIB_files]  Numeric-LEAF-OID";
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
		} catch (Exception e)  {
            System.out.println("Exception : "+e);
            System.exit(1);
        }
         
		 SnmpOID oid = new SnmpOID(opt.remArgs[0]);
		 MibNode nodetemp= mibops.getMibNode(oid);
		 LeafSyntax leafnode = nodetemp.getSyntax();
		 System.out.println("Value : "+leafnode.getType());
		 System.out.println("Data-type : "+leafnode.getEquivname());
		 
		
	   
	   } catch( Exception e ){
        System.err.println( "Exception:" + e );
        e.printStackTrace();
		}
	}		
}