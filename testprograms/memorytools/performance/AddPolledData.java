import com.adventnet.nms.poll.*;
import java.rmi.Naming;

public class AddPolledData
{
    PollAPI api = null;
    
    public AddPolledData()
    {
        try
        {
            api = (PollAPI)Naming.lookup("//saravanakumar/PollAPI");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    public void startTesting()
    {
        try
        {
            for(int i=0;i<5000;i++)
            {
                PolledData data = new PolledData();
                data.setAgent("umashankar");
                data.setName("uma"+i);
                data.setOid(".1.3.6.1.2.1.1.5.0");
                data.setPeriod(60);
                api.addPoll(data);
                
                System.out.println(" added =="+i);
            }
            
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }
    
    public static void main(String a[])
    {
        new AddPolledData().startTesting();
    }
}


