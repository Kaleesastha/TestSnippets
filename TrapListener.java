 import com.adventnet.nms.trap.SocketListener;
 import com.adventnet.nms.eventdb.TrapAPI;
 import com.adventnet.snmp.sas.ProtocolDataUnit;
 import com.adventnet.security.authentication.RMIAccessAPI;
 import java.rmi.Naming; 
 import java.rmi.server.UnicastRemoteObject;
 public class TrapListener extends UnicastRemoteObject implements SocketListener
 {
     public TrapListener() throws java.rmi.RemoteException
     {
     }
 
     public void receivedData(ProtocolDataUnit ppdu)
     {
        System.out.println("Notification Received for Trap");
     }
 
     void start()
     {
         try
         {
             RMIAccessAPI rmiapi = (RMIAccessAPI)Naming.lookup("//hostName/RMIAccessAPI");
             TrapAPI api = (TrapAPI)rmiapi.getAPI("userName","password","TrapAPI");
             api.registerForTrap(4000,this);
             System.out.println("Successfully registered as Trap Listener-->");
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
             TrapListener listener = new TrapListener();
             listener.start();
          }
          catch(Exception e)
          {
             System.out.println("Error in initailizing TrapListener");
             e.printStackTrace();
          }
      }
 }


