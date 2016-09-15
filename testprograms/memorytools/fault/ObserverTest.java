import java.rmi.Naming;
import java.util.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.trap.*;
import com.adventnet.nms.poll.*;

public class ObserverTest extends Thread
{
    CompleteObserver[] array = null;
    String hostName = null;
    AlertAPI alertAPI = null;
    EventAPI eventAPI = null;
    TrapAPI trapAPI = null;
    PollAPI pollAPI = null;
   
    public ObserverTest(String a[])
    {
        
        int observerCount = Integer.parseInt(a[0]);
        array = new CompleteObserver[observerCount];
        
        this.hostName=a[1];
        if(a[2].equalsIgnoreCase("true"))
        {
            try
            {
                Runtime.getRuntime().addShutdownHook(this);
				System.out.println(" Successfully Added Shut down hook ==");
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        
        getAPI();
        for(int i=0;i<observerCount;i++)
        {
            try
            {
                CompleteObserver observer = new CompleteObserver();
                array[i] = observer;
                trapAPI.registerForTrap(6000,(SocketListener)observer);
                eventAPI.register((TrapObserver)observer);
                eventAPI.registerForEvents((EventObserver)observer);
                alertAPI.registerForAlerts((AlertObserver)observer);
                alertAPI.addAlertListener((AlertListener)observer);
                //pollAPI.registerForData("saravanakumar",(PollObserver)observer);
                pollAPI.registerForAllData((PollObserver)observer);
                //pollAPI.registerForDataFromAgent("saravanakumar",(PollObserver)observer);
                //pollAPI.register((PollUnitObserver)observer);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
    }
    
    private void getAPI()
    {
        try
        {
            trapAPI = (TrapAPI)Naming.lookup("//"+hostName+"/TrapAPI");
            eventAPI = (EventAPI)Naming.lookup("//"+hostName+"/EventAPI");
            alertAPI = (AlertAPI)Naming.lookup("//"+hostName+"/AlertAPI");
            pollAPI = (PollAPI)Naming.lookup("//"+hostName+"/PollAPI");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    public void run()
    {
        try
        {
            for(int i=0;i<array.length;i++)
            {
                CompleteObserver observer = array[i];
                trapAPI.deRegisterForTrap(6000,(SocketListener)observer);
                eventAPI.deregister((TrapObserver)observer);
                eventAPI.deregisterForEvents((EventObserver)observer);
                alertAPI.deregisterForAlerts((AlertObserver)observer);
                alertAPI.removeAlertListener((AlertListener)observer);
                pollAPI.deregisterForData("saravanakumar",(PollObserver)observer);
                pollAPI.deregisterForAllData((PollObserver)observer);
                pollAPI.deregisterForDataFromAgent("saravanakumar",(PollObserver)observer);
                pollAPI.deRegister((PollUnitObserver)observer);
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }
    
    public static void main(String a[])
    {
        if(a.length !=3)
        {
            System.out.println(" HELP java ObserverTest Number hostName ShutDown ");
            return;
        }
        new ObserverTest(a);
    }
}
