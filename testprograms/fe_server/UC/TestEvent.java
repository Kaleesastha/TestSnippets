import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import java.util.*;
import java.rmi.Naming;
public class TestEvent
{
	public static void main(String[] a) throws Exception
	{
		if(a.length !=3)
		{
			System.out.println("Usage : java TestEvent hostName number severity");
			return;
		}	
		EventAPI api = (EventAPI)Naming.lookup("//"+a[0]+"/EventAPI");
		int count = Integer.parseInt(a[1]);
		int severityCount = Integer.parseInt(a[2]);
        Random rand = new Random();
        while(true)
        {
            for(int i=0;i<count;i++)
            {	
                Event evt = new Event();
                String source = "jsr"+i;
                evt.setSource(source);
                evt.setEntity(source);
                evt.setText("This is Event "+i);
                evt.setCategory("Test");
                evt.setUserProperty("Test", "testEvent");
                evt.setTime(System.currentTimeMillis());
                evt.setSeverity(severityCount);
                evt.setUserProperty("testColumn", rand.nextInt(i + 1) + "." + rand.nextInt(10000));
                api.addEvent((Event)evt);
                try
                {
                    Thread.sleep(1000);
                }catch(Exception e){};
            }	
            severityCount++;
            severityCount = (severityCount % 5) + 1;
            //break;
        }
	}
}	
		
