import java.rmi.Naming;
import java.util.*;

import com.adventnet.nms.eventdb.*;

public class TestTrapAPI
{
    String hostName = null;
    TrapAPI tapi = null;

    public static void main(String a[])
    {
        if(a.length !=1)
        {
            System.out.println("Usage: java TestTrapAPI hostName");
            return;
        }
        TestTrapAPI prg = new TestTrapAPI(a[0]);
        //prg.setEnableReceiveTraps();
        //prg.getTrapAllParsers();
        prg.setTrapParser();
    }

    public TestTrapAPI(String hostName)
    {
        this.hostName=hostName;
        getTrapAPI();
    }

    private void getTrapAPI()
    {
        try
        {
            tapi = (TrapAPI)Naming.lookup("//"+hostName+":2099/TrapAPI");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void setEnableReceiveTraps()
    {
        try
        {
            boolean flag = true;
            tapi.setEnableReceiveTraps(flag);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void getTrapAllParsers()
    {
        try
        {
            boolean flag = true;
            TrapParser arr[] = tapi.getAllTrapParsers();
            if(arr == null)
            {
				System.out.println("null");
			}
			else
			{
				System.out.println(arr.length);
			}

			String names[] = {"test006"};
			tapi.deleteTrapParsers(names);

			arr = tapi.getAllTrapParsers();
			if(arr == null)
			{
				System.out.println("null");
			}
			else
			{
				System.out.println(arr.length);
			}

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

	private void setTrapParser()
	{
		try
        {
			TrapParser tp = new TrapParser();
			Properties p = new Properties();
			p.put("name","TP1" );
			p.put("type","0");
			p.put("enterprise", ".1.3.6.1.2.1.11");
			p.put("GT","6");
			p.put("ST", "6");
			p.put("severity","3");
			tp.setProperties(p);
			tapi.setTrapParser(tp);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
