package topo.test.general;
import com.adventnet.nms.topodb.*;


import java.rmi.*;
import java.util.*;
import test.*;
import com.adventnet.nms.severity.*;
import javax.transaction.RollbackException;

import com.adventnet.management.transaction.*;
import com.adventnet.nms.util.*;

/*

This program can be used for adding a parentobject with multiple level of children objects. Adding a prent object with multiple level of children objects will be required for testing cascade delete functionality:

The usage of this program is 

USAGE: java topo.test.general.CascadeDeleteTest hostName parentName childrenName level noOfChildrenPerLevel

hostName            : The name of host where the server is running
parentName          : The name of parent object to be added
childrenName        : The name of children object to be added.
level               : A integer value greater than 1 specifying the level or \n                      depth of containment
noOfChildrenPerLevel: A integer value specigying number of children to be added at each level. 

*/

public class CascadeDeleteTest 
{

    static String[] arg;
    public void getHandle()
    {
        TopoAPI topo = null;
        ManagedObject child=null;
        Vector v = new Vector();
        String hostName=arg[0];
        try
        {
            topo=(TopoAPI)Naming.lookup("//"+hostName+"/TopoAPI");
            System.out.println("sucessfully got the handle of TopoAPI");
        }
        catch(Exception e)
        {
            System.out.println("Error in getting handle"+e);
        }
        boolean firstTime= true;
        String currentParent = null;
        int levelcount=1;
        String levelName="atlevel";
        String parentName=arg[1];
        String childName=arg[2];
        int numOfChildren=Integer.parseInt(arg[4]);
        int level =Integer.parseInt(arg[3]);
        
        ManagedObject childmo = null;
        ManagedObject parentmo=null;
        parentmo = new ManagedObject();
        parentmo.setName(parentName);
        parentmo.setIsContainer(true);
        try
        {
            topo.addObject(parentmo);
            level=level-1;
        }
        catch(Exception ex)
        {
            System.out.println("Exception while adding parentmo "+ex);
        }
            while(level>0)
            {
                //System.out.println("levelllll"+level);
                level=level-1;
                if(firstTime)
                {
                    currentParent=parentName;
                    firstTime=false;
                }
                for(int i=0;i<numOfChildren;i++)
                {
                    childmo = new ManagedObject();
                    childmo.setName(childName+i+levelName+levelcount);
                    childmo.setParentKey(currentParent);
                    childmo.setIsContainer(true);
                    //childmo.setProperties(p);
                    try
                    {
                        topo.addObject(childmo);
                    }
                    catch (Exception ee)
                    {
                        System.out.println("Exception while adding childmo "+childmo.getName()+" "+ee);
                    }
                }
                currentParent=childmo.getName();    
                levelcount=levelcount+1;
            }
            System.out.println("Sucessfullay added parent object--> "+parentName+" with --> " +arg[3]+ " level of children, each level containing --> "+numOfChildren+ " children objects");
            try
            {
                Vector objectlist=topo.getCompleteList();
            }
            catch(Exception remoteException)
            {
                System.out.println(remoteException);
            }
    }
    
    
    
    public static void main(String args[])
    {
        arg=args;
        if(arg.length<4)
        {
            System.out.println("Usage: java topo.test.general.CascadeDeleteTest hostName parentName childrenName level noOfChildrenPerLevel");
            System.exit(1);

        }
        int testlevel=Integer.parseInt(arg[3]);
        if(testlevel<1)
        {
            System.out.println("The level cannot not be less than 1 ");
            System.exit(1);
        }
        //if(Integer.parseInt(arg[3])=0)
        //
        CascadeDeleteTest casd = new CascadeDeleteTest();
        
        casd.getHandle();

       
    }
}


