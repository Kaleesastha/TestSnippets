import com.adventnet.nms.topodb.*;

import java.rmi.*;
import java.util.*;
import test.*;
import com.adventnet.nms.severity.*;
import javax.transaction.RollbackException;

import com.adventnet.management.transaction.*;
import com.adventnet.nms.util.*;



public class RecursiveDelete 
{
    
    
    public void getHandle()
    {
        TopoAPI topo = null;
        ManagedObject child=null;
        TopoObject to = null;
        
        try
        {
            topo=(TopoAPI)Naming.lookup("//rprabhu/TopoAPI");
            System.out.println("sucessfully got  the handle");
            
            ManagedObject mo=topo.getByName("parent");

            recursiveDelete(topo,mo);

        }
        catch(Exception e)
        {
            System.out.println("Error in getting handle"+e);
        }
        try
        {
            Vector objectlist=topo.getCompleteList();
        }
        catch(Exception remoteException)
        {
            System.out.println(remoteException);
        }
    }
    static public void recursiveDelete(TopoAPI api, ManagedObject mo) throws RemoteException
    {
        // get immediate children for this object
        Properties props = new Properties();
        props.put("parentKey", mo.getName());
        Vector objects = api.getObjectNamesWithProps(props);
        int size = objects.size();
        if(size > 0)
        {
            for(int i=0; i < size; i++){
                ManagedObject childMo = api.getByName((String)objects.get(i));
                if(childMo != null)
                    recursiveDelete(api, childMo);
            }
        }
        
        // by this time all siblings of mo must have been deleted
        System.out.println("Deleting the " + mo.getName() + " object from database.");
        api.deleteObject(mo);
    }



  
        
    
    public static void main(String args[])
    {
        RecursiveDelete rd = new RecursiveDelete();
        rd.getHandle();

       
    }
}





