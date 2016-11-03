//$Id: LinkFilter.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/**
LinkFilter.java
*/
package test;

import java.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;


import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 


/** The LinkFilter is DiscoveryFilter.This class demonstrates how a user can
    create his/her own  CustomObjects. 
    As a test case we will create a LinkObject (based on the interfaces of 
    Router when a router is discovered) 
    This example makes use of the  LinkObject found in examples/new_object_type
   
	On the discovery of a router the following is done
        a) For each of its interfaces a new object of type LinkObject 
           is created. The LinkObject has 4 main properties
           1) source : -set to the dns name of the router
           2) destination :- set to the ipaddress of that interface
           3) ipADDR :- set to the ipaddress of that interface
           4) name :- set to  source_destination
       b) The new object is added to the database using the TopoAPI
       c) The original router object is returned so that it gets added to
          the database
           
    */
public class LinkFilter implements FoundFilter
{


    public ManagedObject filterObject(ManagedObject obj, TopoAPI api) throws NmsStorageException, UserTransactionException



    {
        //if the object is null, return null
        if (obj == null) return null;

		if( !(obj instanceof TopoObject) ) return obj;
        //if the object is other than router, return it as it is.
        if(!((TopoObject)obj).getIsRouter()) return obj;

	//TypeCast Object to node
        Node node = (Node)obj;

	//Get the list of interfaces to the the router.
        Vector interfaces = node.getIpaddrs(); 

        // declare a LinkObject variable.
        LinkObject link = null; 
        for(int i = 0;i< interfaces.size();i++)
        {
            link = new LinkObject(); // create LinkObject

	    // set the source of LinkObject as the router
            link.setSource(node.getName()); 

	    // set the destination of LinkObject  to the interface
            String dest = (String)interfaces.elementAt(i); 
            link.setDestination(dest);
            String ipadd = "0.0.0.0"; 

	    //get the IpAddress for the interface
            ipadd =  (String)interfaces.elementAt(i);

            // set the ipAddress for LinkObject            
            link.setIpADDR(ipadd); 

	    // set the name for LinkObject
            link.setName(node.getName() + "_" + dest); 

            try
            {
                api.addObject(link); // Add the LinkObject to database.
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
                System.err.println("Unable to add LinkObject" + e);
            }
        }
        return obj; //return the router object.
    }
}
