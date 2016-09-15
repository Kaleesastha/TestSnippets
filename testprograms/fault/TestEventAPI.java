import java.rmi.Naming;
import java.util.*;

import com.adventnet.nms.eventdb.*;

public class TestEventAPI
{
    String hostName = null;
    EventAPI eapi = null;

    public static void main(String a[])
    {
        if(a.length !=1)
        {
            System.out.println("Usage: java TestEventAPI hostName");
            return;
        }
        TestEventAPI tapi = new TestEventAPI(a[0]);
        tapi.getEventByID();
        tapi.getObjects();
        //tapi.getTrapPort();
    }

    public TestEventAPI(String hostName)
    {
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

	private void getEventByID()
	{
		try
		{
			Event evt = eapi.getEventByID(7);
			if(evt != null)
			{
				System.out.println(evt.getProperties());
			}
			else
			{
				System.out.println("null");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	private void getObjects()
	{
		try
		{
			Properties prop = new Properties();
			prop.setProperty("id","7");
			Vector vec = eapi.getObjects("Event",prop);
			//prop.setProperty("severity","2");
			//Vector vec = eapi.getObjects("SpecialEvent",prop);
			if(vec != null)
			{
				System.out.println(vec.size());
			}
			else
			{
				System.out.println("null");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

    private void getTrapPort()
    {
        try
        {
            int[] arr = eapi.getTrapPort();
            if(arr!=null)
            {
                System.out.println(arr.length);
                for(int i=0;i<arr.length;i++)
                {
                    System.out.println(arr[i]);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
