 import com.adventnet.nms.alertdb.*;
 import com.adventnet.nms.alertdb.AlertListener;
 import com.adventnet.nms.alertdb.AlertAPI;
 import com.adventnet.nms.alertdb.AlertActionInformer;
 import com.adventnet.nms.util.XMLNode;
 import com.adventnet.security.authentication.RMIAccessAPI;
 import java.rmi.Naming; 
 import java.rmi.server.UnicastRemoteObject;
 public class TestAlertListener extends UnicastRemoteObject implements AlertListener
 {
     public TestAlertListener() throws java.rmi.RemoteException
     {
     }
 
     public void update(XMLNode node)
     {
        System.out.println("Notification Received for Bulk Delete..."+node);        
     }
     
     public void listenAlert(AlertActionInformer actionInformer)
     {
		System.out.println("Notification Received for Alert operation ");
		int op = actionInformer.getOperation();
		String opString = null;
		if (op == AlertConstants.ALS_UPDATE_ALERT)
		{
			System.out.println("Action : Update ");
			System.out.println("User : "+actionInformer.getUserName());
		}

     }

     void start()
     {
        try
        {
            RMIAccessAPI rmiapi = (RMIAccessAPI)Naming.lookup("//localhost/RMIAccessAPI");
            AlertAPI api = (AlertAPI)rmiapi.getAPI("root","public","AlertAPI");
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
     {
        try
         {
            TestAlertListener listener = new TestAlertListener();
            listener.start();
         }
         catch(Exception e)
         {
            System.out.println("Error in initailizing TestAlertListener");
            e.printStackTrace();
         }
     }
 }