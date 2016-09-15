
import com.adventnet.nms.alertui.ClientAlertAPI;
import com.adventnet.nms.alertui.ClientAlertAPIImpl;
import com.adventnet.nms.client.*;
import com.adventnet.nms.pollui.*;
import com.adventnet.nms.topoui.*;
import com.adventnet.nms.eventui.*;

public class FrameworkOperations
{

    String hostName = null;

    public FrameworkOperations(String hostName)
    {
        this.hostName = hostName;
        new Thread(new TestingRunnable()).start();
    }

    class TestingRunnable implements Runnable
    {
        public void run()
        {
            startTesting();
        }
    }

    int count=1;
    private void startTesting()
    {
        System.out.println(" startTesting =="+(count++));
        //for Alert
        doAlertTesting();
        doEventTesting();
        doTopoTesting();
        doPollTesting();
    }

    private void doAlertTesting()
    {
        try
        {
            ClientAlertAPI api = new ClientAlertAPIImpl(hostName,"root");
            NmsClientTableModel model = api.createCustomView("king");
            model.getTop();
            model.setIncrement(100);
            model.getNext();
            model.getPrevious();
            model.getBottom();
            model.setViewRange(1,10);
            api.removeCustomView("king");
            System.out.println(" ALERTAPI REMOVED SUCCESSFULLY ==============");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void doEventTesting()
    {
        try
        {
            ClientEventAPI api = new ClientEventAPIImpl(hostName,"root");
            NmsClientTableModel model = api.createCustomView("name");
            model.getTop();
            model.setIncrement(100);
            model.getNext();
            model.getPrevious();
            model.getBottom();
            model.setViewRange(1,10);
            api.removeCustomView("name");
            System.out.println(" EVENTAPI REMOVED SUCCESSFULLY ==============");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void doTopoTesting()
    {
        try
        {
            ClientTopoAPI api = new ClientTopoAPIImpl(hostName,"root");
            NmsClientTableModel model = api.createCustomView("name");
            model.getTop();
            model.setIncrement(100);
            model.getNext();
            model.getPrevious();
            model.getBottom();
            model.setViewRange(1,10);
            api.removeCustomView("name");
            System.out.println(" TOPOAPI REMOVED SUCCESSFULLY ==============");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void doPollTesting()
    {
        try
        {
            ClientPollAPI api = new ClientPollAPIImpl(hostName,"root");
            NmsClientTableModel model = api.createCustomView("name");
            model.getTop();
            model.setIncrement(100);
            model.getNext();
            model.getPrevious();
            model.getBottom();
            model.setViewRange(1,10);
            api.removeCustomView("name");
            System.out.println(" POLLAPI REMOVED SUCCESSFULLY ==============");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    public static void main(String a[]) throws Exception
    {
        if(a.length !=1)
        {
            System.out.println(" java FrameworkOperations hostName");
            return;
        }
        for(int i=0;i<20;i++)
        {
            new FrameworkOperations(a[0]);
            Thread.sleep(30000);
        }
    }
}
