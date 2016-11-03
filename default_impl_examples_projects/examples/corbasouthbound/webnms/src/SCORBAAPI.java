/* $Id: SCORBAAPI.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
package com.adventnet.nms.example.southboundcorba;
import com.adventnet.nms.example.southboundcorba.device.*;
import java.lang.Thread;
import com.adventnet.nms.util.NmsLogMgr;

// CORBA imports
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

//import customer IDL classes
//import CorbaNode.*;

//import AdventNet ManagedObjects and custom device ManagedObjects
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.topodb.corba.*;

/**
 * This is the API interface for accessing, updating the South bound CORBA Objects in the 
 * device
*/
public class SCORBAAPI extends java.lang.Thread 
{
    private NamingContext ncRef;
    public static SCORBAAPI api = null;
    String nameServiceHostName;
    boolean firstTime = true;
    long waitingTime;
    
    /** Constructor **/
    SCORBAAPI(String argv[])
    {
        super();
        try
        {         
            String args[] = {argv[1], argv[2], argv[3], argv[4]};
            System.out.println("****SCORBAAPI*** "+argv[0]+ argv[1]+ argv[2]+ argv[3]+ argv[4]);
            nameServiceHostName = argv[2];
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);
            
            // get the root naming context -- Modify the code to get this from the Corba_parameters.conf
            org.omg.CORBA.Object objRef = orb.resolve_initial_references(argv[0]); //arg[0] -- NameService
            System.out.println("*******SCORBAAPI********NAMING SERVICE "+ argv[0]);
            ncRef = NamingContextHelper.narrow(objRef);
            
            api = this;
            
        } 
        catch (Exception e) 
        {
            System.err.println("From SouthBoundCorbaServer:Unable to resolve name context to the CORBA nameservice. Exception: " + e.toString());
            e.printStackTrace(System.out);
        }
    }
    public NamingContext getNamingContext() 
    {
	return ncRef;
    }
    
    /** To get the AdventNet ManagedObjects from the device.*/
    public String getInfo(String hostName,String CORBA_OID)
    {
        if(firstTime)
        {
            waitingTime = 60000;
            firstTime = false;
            //System.out.println("****wait time - 60000");
        }
        else
        {
            //firstTime=false;
            waitingTime=0;
            //System.out.println("****wait time - 0");
        }
	try{
            
            // resolve the Object Reference in Naming
            //NameComponent nc1 = new NameComponent("DeviceList", "");
            System.out.println("****SCORBAAPI*** Contacting Name Service on host - "+ nameServiceHostName + "for CORBA agent on - " + hostName);
            NameComponent nc = new NameComponent(hostName,"");
            NameComponent[] path = {nc};
            //sleep(60000);
            sleep(waitingTime);
            CORBAAgent CORBAAgentRef = CORBAAgentHelper.narrow(ncRef.resolve(path));

            return CORBAAgentRef.CORBAget(CORBA_OID);
            
        } catch (Exception e) {
            System.out.println("****CorbaServer:Unable to getName Exception: " + e.toString());
            e.printStackTrace(System.out);
        }
        return null;
    }
    
}







