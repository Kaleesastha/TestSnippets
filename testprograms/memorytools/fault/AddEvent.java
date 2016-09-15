import com.adventnet.nms.eventdb.*;
import java.rmi.Naming;
import test.*;
public class AddEvent
{
    int totalCount=1;
    String hostName = null;
    EventAPI eapi = null;

    public AddEvent(String stringCount,String hostName)
    {
        this.totalCount=Integer.parseInt(stringCount);
        this.hostName=hostName;
        getEventAPI();
    }
    
    private void getEventAPI()
    {
        try
        {
            eapi = (EventAPI)Naming.lookup("//"+hostName+"/EventAPI");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void addEvent()
    {
        try
        {
            for(int i=0;i<totalCount;i++)
            {
				Event evt = new Event();
                evt.setEntity("Test"+i);
                evt.setSource("Test");
                evt.setSeverity((i%4)+1);
                evt.setText("This is text "+i);
                evt.setCategory("public");
                evt.setTime(System.currentTimeMillis());

                for(int j=0;j<5;j++)
                {
                    evt.setUserProperty("winner",String.valueOf(j));
                }

                /*while(eapi.is_inQ_SizeBeyondLimit())
                {
                    Thread.sleep(100);
                }*/
            
                eapi.addEvent(evt);
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    public static void main(String a[])
    {
        if(a.length !=2)
        {
            System.out.println(" java AddEvent No_of_Events hostName");
            return;
        }
        new AddEvent(a[0],a[1]).addEvent();
    }
}
