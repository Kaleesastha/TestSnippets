 import com.adventnet.nms.alertdb.AlertListener;
 import com.adventnet.nms.alertdb.*;
 import com.adventnet.nms.alertdb.AlertAPI;
 import com.adventnet.nms.alertdb.AlertActionInformer;
 import com.adventnet.nms.topodb.*;
 import com.adventnet.nms.util.XMLNode;
 import com.adventnet.security.authentication.RMIAccessAPI;
 import java.rmi.Naming; 
 import java.rmi.server.UnicastRemoteObject;
 import com.adventnet.nms.alertdb.*;
 import com.adventnet.nms.mapdb.*;
 import com.adventnet.nms.util.*;
 import com.adventnet.nms.severity.SeverityInfo;
 import com.adventnet.management.log.*;
 import java.util.*;


 public class TestAlertListener extends UnicastRemoteObject implements AlertListener
{
     MapAPI mapApi=null;
     public TestAlertListener() throws java.rmi.RemoteException
     {
         System.out.println("Test Alert Listener");
	}
 
     public void update(XMLNode node)
     {
        System.out.println("Notification Received for Bulk Delete");
     }
     
     public void listenAlert(AlertActionInformer actionInformer)
     {
                System.out.println("Notification Received for Alert operation");
                Alert A = actionInformer.getAlert();
                //ManagedObject mo = new ManagedObject();
   //           String map_name =A.getMapname();
                String mo=null;
                Properties props = new Properties();
                //int sev = A.getSeverity();
                //if(sev>0&&sev<6){
                //System.out.println("---------sev---------="+sev);
                props.put("treeIconFileName","images/ip.png"); //desired value for update
                try
                {
                      boolean result = mapApi.updateMap("ipnet.netmap", props);
                      System.out.println("---------result of update map-----------="+result);
                }
                catch(Exception e){}
               // }
               // else {
               //    System.out.println("---------severity is not matching------------");
               // }
     }

     void start()
     {
        System.out.println("START"); 
        try
        {
	    System.out.println("Looking up");
            System.out.println("looking ip");
            RMIAccessAPI rmiapi = (RMIAccessAPI)Naming.lookup("//localhost:1099/RMIAccessAPI"); 
            System.out.println("got the rmi api");
//            mapApi=(MapAPI)Naming.lookup("rmi://sankaralingam/MapAPI");
            System.out.println("got the handle of map api");
            mapApi= (MapAPI)rmiapi.getAPI("root","public","MapAPI");
	    System.out.println("Getting API");
            AlertAPI api = (AlertAPI)rmiapi.getAPI("root","public","AlertAPI");
  //           AlertAPI api = (AlertAPI)Naming.lookup("rmi://sankaralingam/AlertAPI"); 
             System.out.println("got the handle of Alert api");
	    System.out.println("Lookup successful");
            if(api.addAlertListener(this))
            {
               System.out.println("Successfully registered as AlertListener");
            }
            else
            {
               System.out.println("Failed in registering as AlertListener");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in registering ");
            e.printStackTrace();
        }    
     }
     public static void main(String args[])
     {  System.out.println("Enter main");
        try
         {
            System.out.println("Create listener");
		TestAlertListener listener = new TestAlertListener();
	      System.out.println("Starting alert listener");
            listener.start();
         }
         catch(Exception e)
         {
            System.out.println("Error in initializing TestAlertListener");
            e.printStackTrace();
         }
     }
 }
 
 
