import java.rmi.Naming;
import java.util.*;

import com.adventnet.nms.eventdb.*;

public class TestEventParserAPI
{
    String hostName = null;
    String port ="1099";
    EventParserAPI eapi = null;

    public static void main(String a[])
    {
        if(a.length !=2)
        {
            System.out.println("Usage: java TestEventParserAPI hostName port");
            return;
        }
        TestEventParserAPI tapi = new TestEventParserAPI(a[0],a[1]);
        //tapi.api_ep_002();
        //tapi.api_ep_020();
        //tapi.api_ep_059();
        //tapi.api_ep_060();

        //FE cases
        tapi.api_ep_023();
        tapi.api_ep_024();
    }

    public TestEventParserAPI(String hostName, String port)
    {
        this.hostName=hostName;
        this.port=port;
        getEventParserAPI();
    }

    private void getEventParserAPI()
    {
        try
        {
            eapi = (EventParserAPI)Naming.lookup("//"+hostName+":"+port+"/EventParserAPI");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void api_ep_002()
    {
        System.out.println("api_ep_002");
        try
        {
            Properties p=new Properties();  
            p.setProperty("name","api_ep_002");  
            eapi.setEventParser(p) ;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private void api_ep_020()
    {
        System.out.println("api_ep_020");
        try
        {
            Properties p1=new Properties(); 
            p1.setProperty("name","20_1");  
            p1.setProperty("testDefn","test");  
            Properties p2=new Properties();  
            p2.setProperty("name","20_2");  
            p2.setProperty("severityMatch","Critical");  
            p2.setProperty("severityDefn", "Clear"); 

            Properties parr[]={p1,p2};

            eapi.saveEventParsersToFile( parr,"conf/event.parsers" );
            parr = null;
            Properties pp[] = eapi.getEventParsersFromFile("conf/event.parsers"); 
            for(int i=0;i<pp.length;i++)
            { 
                System.out.println(pp[i]);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void api_ep_023()
    {
        System.out.println("api_ep_023");
        try
        {
            Properties p=new Properties();
            p.setProperty("name","23"); 
            p.setProperty("sourceMatch","umashankar" );
            eapi.setEventParser(p) ;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void api_ep_024()
    {
        System.out.println("api_ep_024");
        try
        {
            Properties p=new Properties(); 
            p.setProperty("name","24");
            p.setProperty("nodeMatch","exampleNode" ); 
            eapi.setEventParser(p) ;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void api_ep_059()
    {
        System.out.println("api_ep_059");
        try
        {
            Properties p = new Properties();
            p.setProperty("name","ep59_1"); 
            p.setProperty("sourceMatch","rajalakshmytr");  
            eapi.setEventParser(p); 
            Properties p1 = new Properties();
            p1.setProperty("name","ep59_2"); 
            p1.setProperty("sourceMatch","saravanakumar");  
            eapi.setEventParser(p1); 
 
            String[] arr = new String[2];  
            arr[0]="ep59_1";  
            arr[1]="ep59_2";
            eapi.enableParsers(arr,true);  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void api_ep_060()
    {
        System.out.println("api_ep_060");
        try
        {
            String[] arr = new String[0];  
            eapi.enableParsers(arr,true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
