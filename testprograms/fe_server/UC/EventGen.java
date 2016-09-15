import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import java.util.*;
import java.rmi.Naming;

public class EventGen
{
	public static void main(String[] a) throws Exception
	{
		if(a.length !=4)
		{
			System.out.println("Usage : java EventGen BEHost severity No ObjectName");
			return;
		}
		EventAPI api = (EventAPI)Naming.lookup("//"+a[0]+"/EventAPI");
		int count = Integer.parseInt(a[2]);
		int severityCount = Integer.parseInt(a[1]);
        Random rand = new Random();
        String text = "XXX This is example tezt";
        //for (int xx= 0; xx < 2; xx++)
        Event evt = new Event();
        evt.setText(text);
        evt.setSeverity(severityCount);
        System.out.println("Started sending events");
        long startTime = System.currentTimeMillis();
        String name  = a[3];
        for(int i=1;i<=count;i++)
        {
            evt.setSource(name + i);
            evt.setEntity(name + i);
            evt.setTime(System.currentTimeMillis());
            //evt.setUserProperty("alertDoubleColumn", "" + System.currentTimeMillis());

            //uncomment the following line for testing double datatype in SQL Query generator
            //evt.setUserProperty("testColumn", "" + "10.52");
            while (true)
            {
                if (api.is_inQ_SizeBeyondLimit())
                {
                    Thread.sleep(10);
                }
                else
                {
                    api.addEvent(evt);
                    break;
                }
            }
            if (i % 1000 == 0)
            {
                long endTime = (System.currentTimeMillis() - startTime) / 1000L;
                if (endTime > 0)
                {
                    System.out.println("Added : " + i + " events in " + (endTime) + " Sec. at the rate of " + (i / endTime));
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Finished sending the events");
	}
}

