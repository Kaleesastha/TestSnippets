
/**
 * ShutDownNMS.java
 *
 *
 * Created: Mon Apr 09 10:51:50 2001
 *
 * @author <a href="mailto: "</a>
 * @version
 */
import com.adventnet.nms.util.*;
import com.adventnet.nms.admin.*;
import com.adventnet.security.authentication.*;


public class ShutDownNMS 
{
    public ShutDownNMS ()
    {
        
    }
    public static void main(String arg[]) throws Exception
    {

        String hostName = "localhost";
        if(arg.length > 0)
        {
            hostName = arg[0];   
        }
        ShutDownAPI api1=null;
        AuthenticationAPI api2=null;
        try
        {
            api1 = (ShutDownAPI)java.rmi.Naming.lookup("//"+hostName +"/ShutDownAPI");	       
            api2=(AuthenticationAPI)java.rmi.Naming.lookup("//"+hostName +"/NmsAuthenticationAPI");
        }
        catch(Exception e1)
        {
            System.out.println("Exception While getting Handle for ShutDownAPI and AuthenticationAPI"+e1.getMessage());
            System.exit(-1);
        }
        String key1=api2.getChallenge("root");
        String key2=com.adventnet.security.AuthUtil.getChallengeKey("root","public",key1);
        System.out.println("Going to shutdown WebNMS running on machine :" + hostName);
        try
        {
            api1.shutDownServer("root" ,key2 );
        }
        catch(Exception e)
        {
            //System.out.println("Exception "+e);
        }
        System.out.println("WebNMS shut down successfully");
        System.exit(0);
        
                        
    }
}// ShutDownNMS
