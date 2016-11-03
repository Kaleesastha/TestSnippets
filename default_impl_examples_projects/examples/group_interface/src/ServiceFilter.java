/* $Id: ServiceFilter.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $   */
package test;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.*;
import com.adventnet.snmp.mibs.*;

import java.net.*;
import java.util.*;


import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 


/**
 * This is a discovery filter for the demonstration of establishment 
 * of Group relationships between ManagedObjects.
 * When an Object is discovered, the Object is checked for the availability
 * of three services-namely, FtpService, TelnetService and SnmpService.
 * Based on this availabilty, the members are added to the respective groups.
 * This example shows the utilization of the GroupInterface to bring about 
 * many-to-many relationships.
 *
 **/

public class ServiceFilter implements FoundFilter 
{ 
    private SnmpTarget target = null;
    

    /**
     * Method which performs the filytering action.
     * @param obj ManagedObject Instance.
     * @param api Instance of TopoAPI.
     * @return ManagedObject instance.
     **/

    public ManagedObject filterObject(ManagedObject obj, TopoAPI api) throws NmsStorageException, UserTransactionException



    { 
	String[] strArr = new String[3];

        createInitialServiceMaps(api);
	

	if (obj == null) 
        {
            return null; 
        }
	
	if(!((TopoObject)obj).getIsNode())  //return if not a node
        {
            return obj;
        }
	
	int i = 0;
	if(doSnmpGet(obj.getName()))  //check for snmp service
	{ 
	    strArr[i] = "SnmpService";
	    i++;
	    obj.setUserProperty("SnmpService", "yes");
	}
	else 
	{
	    obj.setUserProperty("SnmpService", "no");
	}
	
	
	if(checkService(obj.getName(),21)) // check for ftp service
	{
	    strArr[i] = "FtpService";
	    i++;
	    obj.setUserProperty("FtpService", "yes");
	}
	else 
	{
	    obj.setUserProperty("FtpService", "no");
	}
		
	
	if(checkService(obj.getName(),23))   //Check for Telnet Service
	{
	    strArr[i] = "TelnetService";
	    i++;
	    obj.setUserProperty("TelnetService", "yes");
	}
	else 
	{
	    obj.setUserProperty("TelnetService", "no");
	}
	obj.setGroupNames(strArr);
	
	try 
	{
	    api.addObject(obj);
	    api.setGroupsForMO(obj.getName(), strArr, false);

	}
	catch(NmsStorageException nse)
	{
		throw nse;
     	}
	catch(UserTransactionException ute)
	{
		throw ute;

	}catch(Exception e) 
	{
            System.out.println("Exception while addObject/setGroupsForMO. : " +e);
	    e.printStackTrace();
	}
    cleanUp();
	return null;
    }

    /**
     * Performs an SnmpGet to the specified port using the SnmpTarget class.
     * @param hostName The name that returned by the getName() method in 
     *                 ManagedObject.
     * @return Boolean value representing the success/failure of the SnmpGet
     Operation.
    **/

    public boolean doSnmpGet(String hostName) 
    { 
	target.setTargetHost(hostName);
	target.setCommunity("public");
	target.setSnmpOID(new SnmpOID("1.1.0"));
	target.setTargetPort(161);
	String sysDescr = target.snmpGet();
	if(sysDescr != null) 
	{ 
	    return true;
	}
	else 
	{ 
	    return false;
	}
    }
    /** 
     * Checks for the availability of Services like Ftp and Telnet on the 
     * host name specified.
     * @param host Host name returned by the getName() method of the MO.
     * @param port The port to be tested for the service availability.
     * @return Boolean value representing the presence/absence of the service.
     **/
    public boolean checkService(String host, int port) 
    { 
        boolean success = false;
        Socket sock = null;
        
	try 
	{ 
	    sock = new Socket(host,port);
	    success = true;
	}
	catch(Exception e) 
	{ 
            System.out.println(" Exception while creating the socket.. " + e);
	}

        if( !success )
	{ 
	    return false;
	}
        
	if (success) 
	{ 
	    try 
	    { 
		sock.close();
	    }
	    catch(Exception exc) 
	    { 
                System.out.println("Exception while closing the Socket.. " + exc);  
	    }
	}
        return true;
    }

    private static boolean firstTime = true ;

    /**
     * This method adds the initial service maps to the database.
     *
     * @param api An instance of the TopoAPI to add the objects database.
     */

    private void createInitialServiceMaps(TopoAPI api) throws NmsStorageException, UserTransactionException



    {
        IPService snmpSer = null;
        IPService telnetSer = null;
        IPService ftpSer = null;
        
	if(firstTime) 
	{ 
	    target = new SnmpTarget();
	    try 
	    {
		ftpSer = new IPService("FtpService");
		api.addObject(ftpSer);
		telnetSer = new IPService("TelnetService");
		api.addObject(telnetSer);
		snmpSer = new IPService("SnmpService");
		api.addObject(snmpSer);
	    }

	    catch(NmsStorageException nse)
	    {
		throw nse;
     	    }
	    catch(UserTransactionException ute)
	    {
		throw ute;
	    }

	    catch(Exception e1) 
	    {
		System.err.println("Exception while adding IPService objects :"+e1);
	    }
	    firstTime = false;
	}
    }

    public void cleanUp()
    {
        target.releaseResources();        
    }
}


