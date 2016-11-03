//$Id: NodeFilter.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/**
NodeFilter.java
*/
package test;

import java.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;


/** This program demonstrates how you can write a discovery filter of your own. Your filter
* should implement com.adventnet.nms.topodb.FoundFilter interface. This interface has a method 
* called filterObject(ManagedObject obj, TopoAPI api) which is called before the managed 
* object is added to Database.So you can add/define your own properties to the managed object
* and also filter out unwanted Managed objects.
* In the program the following is done
*    a) If the discovered object is a Network return  that object
*    b) If the discovered object does not support SNMP filter it out of the
*       WebNMS system by returning NULL. 
*       ( so that is it not added to the database )
*    c) if the discovered object is of a type other than node return that object*    d) if it is a node add a new user property called pingResult and return
*       that object
*/

public class NodeFilter implements FoundFilter
{
    /**
     * This method filters out all non-snmp objects discovered and also adds
     * custom properties for the snmp objects discovered.
    **/

    public ManagedObject filterObject(ManagedObject obj, TopoAPI api)
    {
		// If the object is null, return null
        if (obj == null) return null;                  
        if (obj instanceof Network) return obj;

		//Filter out non snmp objects
		if(!((TopoObject)obj).getIsSNMP())return null; 

		// we dont want objects other than node.
        if(!((TopoObject)obj).getIsNode()) return obj; 

		// get the properties of object.
        Properties p = obj.getProperties();           

	    //initially the property is set to true
        p.put("pingResult","true");                    

		//set the properties of object.         
        obj.setProperties(p);                         


		// return the node with new properties. 
        return obj;                                    
    }
}

