import com.adventnet.nms.eventdb.*;
import java.rmi.*;
public class MonitorEventSize
{
    public static void main(String args[])
    {
        try
        {
            EventAPI eventAPI = (EventAPI)Naming.lookup("//localhost:2099/EventAPI");
            while(true)
            {
                System.out.println("size --> "+eventAPI.getEventQueueSize());
                Thread.sleep(3000);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
