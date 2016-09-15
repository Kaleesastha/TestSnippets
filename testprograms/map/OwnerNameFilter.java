
/**
 * OwnerNameFilter.java
 *
 *
 * Created: Fri May 04 16:08:59 2001
 *
 * @author <a href="mailto: "</a>
 * @version
 */
package test;

import com.adventnet.nms.topodb.*;
import java.util.*;

public class OwnerNameFilter implements FoundFilter
{
    public OwnerNameFilter ()
    {
        
    }
    public ManagedObject filterObject(ManagedObject obj, TopoAPI topo)
    {
        if ((obj == null) || (obj instanceof Network))
        {
            return obj;
        }
        ManagedObject newObj, newObj1;
        String tempClassName = obj.getClass().getName();
        try 
        {
            Properties p = obj.getProperties();
            String newName = (String)p.remove("name");

            newObj = (ManagedObject)obj.getClass().newInstance();
            newObj.setName(newName);
            newObj.setProperties(p);
            newObj.setStatus(1);
            newObj.setTester("NONE");
            newObj.setOwnerName("AAA");

            newObj1 = (ManagedObject)obj.getClass().newInstance();
            newObj1.setName(newName);
            newObj1.setProperties(p);
            newObj1.setOwnerName("BBB");
            newObj1.setStatus(2);
            newObj1.setTester("NONE");

            topo.addObject(newObj);
            topo.addObject(newObj1);
        }
        catch(Exception e1)
        {
            System.err.println(" Exception while adding object " + obj);
            e1.printStackTrace();
        }
        obj.setTester("NONE");
        return obj;
    }
}// OwnerNameFilter
