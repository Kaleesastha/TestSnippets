import java.rmi.Naming;
import java.util.*;

import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.server.FilterObject;

public class TestAlertFilterAPI
{
    String hostName = "localhost";
    String port = "1099";
    AlertFilterAPI api = null;

    public static void main(String a[])
    {
        if(a.length !=2)
        {
            System.out.println("Usage: java TestAlertFilterAPI hostName port");
            return;
        }
        TestAlertFilterAPI tapi = new TestAlertFilterAPI(a[0]);
        //tapi.setv1Filters();
        //tapi.setv2Filters();
        //tapi.setv2_2Filters();
        tapi.setFilterSuppress();
    }

    public TestAlertFilterAPI(String hostName)
    {
        this.hostName=hostName;
        getAlertFilterAPI();
    }

    private void getAlertFilterAPI()
    {
        try
        {
            api = (AlertFilterAPI)Naming.lookup("//"+hostName+":"+port+"/AlertFilterAPI");
            System.out.println("Successfully got the handle");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void setv1Filters()
    {
        System.out.println("setv1Filters");
        try
        {
            FilterObject object = new FilterObject();
            object.filtername = "winner";
            Properties prop = new Properties();
            prop.setProperty("entity","rajalakshmytr");
            object.criteria = prop;

            SendTrap faction= new SendTrap();

            Properties p2 = new Properties();
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
            object.actions=faction_arr; 

            FilterObject[] fo_arr=new FilterObject[1]; 
            fo_arr[0]=object;

            api.setFilters(fo_arr); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setv2Filters()
    {
        System.out.println("setv2Filters");
        try
        {
            FilterObject object = new FilterObject();
            object.filtername = "winner2";
            Properties prop = new Properties();
            prop.setProperty("entity","rajalakshmytr");
            object.criteria = prop;

            SendTrap faction= new SendTrap();

            Properties p2 = new Properties();
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
            object.actions=faction_arr; 

            FilterObject[] fo_arr=new FilterObject[1]; 
            fo_arr[0]=object;

            api.setFilters(fo_arr); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setv2_2Filters()
    {
        System.out.println("setv2_2Filters");
        try
        {
            FilterObject object = new FilterObject();
            object.filtername = "winner2_2";
            Properties prop = new Properties();
            prop.setProperty("entity","rajalakshmytr");
            object.criteria = prop;

            SendTrap faction= new SendTrap();

            Properties p2 = new Properties();
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
            object.actions=faction_arr; 

            FilterObject[] fo_arr=new FilterObject[1]; 
            fo_arr[0]=object;

            api.setFilters(fo_arr); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setFilterSuppress()
    {
        System.out.println("setFilterSUpress");
        try
        {
            FilterObject object = new FilterObject();
            object.filtername = "winner";

            Properties p = new Properties();
            p.put("entity", "makesh" );  
            p.put("category","Topology");  
            object.criteria=p;

            FilterAction faction=new FilterAction();

            Properties p2 = new Properties();
            //p2.put("name","action_name"); 
            //p2.put("suppressAll","yes"); 
            //p2.put("suppressInt","32"); 

            p2.put("name", "action_name");  
            p2.put("suppressAll","false" );  
            p2.put("suppressInt", "32");  

            faction.setProperties(p2); 

            FilterAction[] faction_arr=new FilterAction[1]; 
            faction_arr[0]=faction;
            object.actions=faction_arr; 

            FilterObject[] fo_arr=new FilterObject[1]; 
            fo_arr[0]=object;

            api.setFilters(fo_arr); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
