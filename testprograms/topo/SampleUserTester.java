package test;
import com.adventnet.nms.util.Ping;
import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.netwatch.UserTester;
import com.adventnet.nms.topodb.ManagedObject;
import java.io.*;
import java.util.*;
public class SampleUserTester implements UserTester
{
    public int test(ManagedObject mo, TopoAPI api)
    {
        System.out.println(" my uclass called ***********");//No Internationalisation
        try
            {
                nodeStatus(mo,api);
 
           }
        catch(Exception e)
            {
                System.out.println(" Exception **********" + e.getMessage());//No Internationalisation
            }
        return 0;
    }

 
    private int nodeStatus(ManagedObject mo,TopoAPI api)
    {
        try{
            
            System.out.println("checkStatus begin" );//No Internationalisation
            Properties prop= api.getDiscoveryParameters();//No Internationalisation
            System.out.println("checkStatus properties: "+prop );//No Internationalisation
            String pingTimeout = prop.getProperty("PING_TIMEOUT"); //No Internationalisation
            System.out.println("checkStatus pingtimeout: "+pingTimeout );//No Internationalisation
            Ping.setPingTimeout(120);//No Internationalisation
            //Uncomment the following to set the ping timeout value using Ping.ping(properties p);
            /*
            Properties pingProp = new Properties();
            pingProp.setProperty("hostName",mo.getName());
            pingProp.setProperty("pingRetries","3");
            pingProp.setProperty("pingTimeout","10");
            pingStatus = Ping.ping(pingProp);
            */

            // First step: we ping the device

            boolean pingStatus = true;
            long  startTime =  System.currentTimeMillis();//No Internationalisation
            pingStatus = Ping.ping(mo.getName());//No Internationalisation
            System.out.println("Time taken for ping  " + (startTime -System.currentTimeMillis()));//No Internationalisation

        }catch(Exception e)
            {
                System.out.println("the exception is " +e );//No Internationalisation
            }
        return 5;
    }
}
