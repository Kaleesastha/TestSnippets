package topo.test.general;

//package test;
import java.rmi.*;
import java.rmi.server.*;
import java.lang.*;
import java.util.Properties;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.*;

/*

This testprogram is used to test the robustnness of TopoAPI.checkOut() method. This testprogram continuously checksout the object specified as the 2nd argument and updates the object. The object returned by the TopoAPI.checkOut() api is checked for proper write lock and if the object doesnt not hold a write lock or else any other exception occurs, the program quits.

Usage of the program is:
USAGE: java topo.test.general.CheckOutAPITeat hostName objectToUpdate timeoutValue propname propval

hostName       : Name of the host machine where the server runs.
objectToUpdate : Name of object to be updated.
timeoutValue   : Timeout value to be applied as the 2nd argument for \n 
                 TopoAPI.checkOut() api. 
propname       : Name of the property to be updated.
propval        : Value to be set for the above property.



 */

public class  CheckOutAPITest
{
    private static TopoAPI topoAPI = null;
    private static String[] arg=null;
    




    /**Main method*/
    public static void main(String[] args)
    {
        arg=args;
        if(arg.length<5)
        {
            System.out.println("USAGE: java topo.test.general.CheckOutAPITeat hostName objectToUpdate timeoutValue propname propval");
            System.exit(10);
        }
        String hostName=arg[0];
        String objectName=arg[1];
        int timeOut=Integer.parseInt(arg[2]);
        String propName=arg[3];
        String propVal=arg[4];
        Properties prop=new Properties();
        prop.put(propName,propVal);
        
        try
        {
            topoAPI = (TopoAPI)Naming.lookup("//"+hostName+"/TopoAPI");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        
        //MapSymbolManagedObject topoObj = null;
        ManagedObject topoObj=null;
        int i = 0;
        boolean sleepFlag = true;
        
        //int value = Integer.parseInt(args[1]);
    
        /*        if( value == 0 )
        {
            sleepFlag = false;
        }
        */
        while( 1 > 0)
        {
            i = i + 1;
            
            if( sleepFlag )
            {
                try
                {
                    Thread.sleep(100);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
            
            try
            {
                topoObj = (SnmpNode)topoAPI.checkOut(objectName,timeOut);
            }
            catch(Exception e)
            {
                e.printStackTrace();
          System.exit(1);
            }
            
            //topoObj.setPollInterval(value);
            //topoObj.setFailureThreshold(value);
            topoObj.setProperties(prop);
            System.out.println("update : "+i+" Lock ID : "+ topoObj.getLockId());
            try
            {
                System.out.println("Write permission ---->"+topoAPI.checkWritePermission(topoObj));
                boolean x = topoAPI.updateObject( topoObj, false, true);
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
