/**
AgentFilter.java
*/
package test;

import java.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.*;
import com.adventnet.snmp.mibs.*;


import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 


/** This class demonstrates how one can handle a situation where a node is having more
* than one agent listening at different ports. Here we discover a node, do a snmpping at a
* port other than 161, to find out if the node responds. If it responds we create a 
* new node object and add it to database. Users can write UserTesters for these 
* created objects
*/

public class AgentFilter implements FoundFilter
{
    static boolean firstTime = true;
    SnmpTarget target;
    MibOperations mibOps;


    public ManagedObject filterObject(ManagedObject obj, TopoAPI api) throws NmsStorageException, UserTransactionException



    {
        //for once we will create an SnmpTarget.
        if(firstTime)
        {
            target = new SnmpTarget();
            target.setAttemptPartial(true);
            mibOps = target.getMibOperations();
            firstTime = false;
        }

        //if the object is null, return null
        if (obj == null) return null;

        //if the object is other than node, return it as it is.
        if(!((TopoObject)obj).getIsNode()) return obj;
        
        // Otherwise do an snmpping.Here the object name and community are same as of node.
        // The numeric OID and port are assumed.
        target.setTargetHost(((TopoObject)obj).getName());
        target.setCommunity(((TopoObject)obj).getCommunity());
        target.setSnmpOID(new SnmpOID(".1.3.6.1.4.1.2162.3.1.1.0"));
        target.setTargetPort(8001);
        String sysDescr = target.snmpGet();

        cleanUp();
        // if the snmp request fails, return
        if (sysDescr == null)
            return obj;
        
        SnmpNode node;
        
        // Create a new SnmpNode if it is SnmpNode already. If it is just a node then
        // Make the node an SnmpNode.
        if(((TopoObject)obj).getIsSNMP())
        {
            // create a new node object.
            node = new SnmpNode();
        
            // This is a crucial step. It should be done before doing setProperties        
            node.setName(obj.getName() + String.valueOf(8001));

            // get properties of the node.
            Properties prop = ((Node)obj).getProperties();

            //remove property "name", as it is already set. Anyway this is optional.
            prop.remove("name");
        
            //now set the properties of new node object.
            node.setProperties(prop);                
        
            // set the port to be one assumed.
            node.setSnmpport(8001);
 
            try
            {   
                // add the new node object to database.
                api.addObject(node);
            }

	    catch(NmsStorageException nse)
	    {
		throw nse;
     	    }
	    catch(UserTransactionException ute)
	    {
		throw ute;
	    }

            catch(Exception e)
            {
                System.err.println("Exception adding Object" + node.getName());
            }
            return obj;
        }
        else 
        {
            node = new SnmpNode();
            node.setName(obj.getName());
            //Set the obj properties to the new snmpnode
            node.setProperties(obj.getProperties());
            // set the port to be one assumed.
            node.setSnmpport(8001);
            return node; //Return the new snmpnode to be added into database.
        }
    }
 
    public void cleanUp()
    {
        target.releaseResources();        
    }
}
