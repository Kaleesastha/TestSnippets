import java.rmi.Naming;
import java.util.*;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.server.FilterObject;

public class TestEventFilterAPI
{
    String hostName = "localhost";
    String port = "1099";
    EventFilterAPI api = null;

    public static void main(String a[])
    {
        if(a.length !=2)
        {
            System.out.println("Usage: java TestEventFilterAPI hostName port");
            return;
        }
        TestEventFilterAPI tapi = new TestEventFilterAPI(a[0]);
        //tapi.setFilters1();
        //tapi.setFilters2();
        //tapi.setFilters3();
        tapi.setFilters4();
    }

    public TestEventFilterAPI(String hostName)
    {
        this.hostName=hostName;
        getEventFilterAPI();
    }

    private void getEventFilterAPI()
    {
        try
        {
            api = (EventFilterAPI)Naming.lookup("//"+hostName+":"+port+"/EventFilterAPI");
            System.out.println("Successfully got the handle");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void setFilters2()
    {
        System.out.println("setFilters2");
        try
        {
            FilterObject  fo=new FilterObject(); 
            fo.filtername="filtertwo"; 

            Properties p=new Properties(); 
            p.put("entity","makesh"); 
            fo.criteria=p; 

            SendTrap faction= new SendTrap();  

            Properties p2=new Properties();
            p2.put("name","trap name");  
            p2.put( "peername", "localhost"); 
            p2.put( "trap_port", "162"); 
            p2.put( "community", "public"); 
            p2.put("version","v2c"); 
            p2.put("trapOid",".1.3.6.1.6.3.1.1.5.3"); 
            p2.put( "timeticks","123");

            faction.setProperties(p2); 

            FilterAction[] faction_arr=new FilterAction[1]; 

            faction_arr[0]=faction;

            fo.actions=faction_arr; 
            FilterObject[] fo_arr=new FilterObject[1]; 
            fo_arr[0]=fo;

            api.setFilters(fo_arr); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setFilters1()
    {
        System.out.println("setFilters1");
        try
        {
            FilterObject  fo=new FilterObject(); 
            fo.filtername="filterone"; 

            Properties p=new Properties(); 
            p.put("entity","makesh"); 
            fo.criteria=p; 

            SendTrap faction= new SendTrap();  

            Properties p2=new Properties();
            p2.put("name","trap name");  
            p2.put( "peername", "localhost"); 
            p2.put( "trap_port", "162"); 
            p2.put( "community", "public"); 
            p2.put("version","v1"); 
            p2.put("generic","2" );  
            p2.put("specific" ,"3");  
            p2.put( "timeticks","123");

            faction.setProperties(p2); 

            FilterAction[] faction_arr=new FilterAction[1]; 

            faction_arr[0]=faction;

            fo.actions=faction_arr; 
            FilterObject[] fo_arr=new FilterObject[1]; 
            fo_arr[0]=fo;

            api.setFilters(fo_arr); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setFilters3()
    {
        System.out.println("setFilters3");
        try
        {
            FilterObject  fo=new FilterObject(); 
            fo.filtername="filterothree"; 

            Properties p=new Properties(); 
            p.put("entity","makesh"); 
            fo.criteria=p; 

            SendTrap faction= new SendTrap();  

            Properties p2=new Properties();
            p2.put("name","trap name");  
            p2.put( "peername", "localhost"); 
            p2.put( "trap_port", "162"); 
            p2.put( "community", "public");  
            p2.put("version","v2c" );  
            p2.put("trapOid",".1.3.6.1.6.3.1.1.5.3");  
            p2.put( "timeticks","123")  ;
            p2.put("vb[0]0" , ".1.2.3.4.5");  
            p2.put("vb[1]0","String");  
            p2.put( "vb[2]0","mak");

            faction.setProperties(p2); 

            FilterAction[] faction_arr=new FilterAction[1]; 

            faction_arr[0]=faction;

            fo.actions=faction_arr; 
            FilterObject[] fo_arr=new FilterObject[1]; 
            fo_arr[0]=fo;

            api.setFilters(fo_arr); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setFilters4()
    {
        System.out.println("setFilters4");
        try
        {
            FilterObject  fo=new FilterObject(); 
            fo.filtername="filterfoure"; 

            Properties p=new Properties(); 
            p.put("entity","makesh"); 
            fo.criteria=p; 

            SendTrap faction= new SendTrap();  

            Properties p2=new Properties();
            p2.put("name", "action_name");  
            p2.put("suppressAll","false" );  
            p2.put("suppressInt", "32");  

            faction.setProperties(p2); 

            FilterAction[] faction_arr=new FilterAction[1]; 

            faction_arr[0]=faction;

            fo.actions=faction_arr; 
            FilterObject[] fo_arr=new FilterObject[1]; 
            fo_arr[0]=fo;

            api.setFilters(fo_arr); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
