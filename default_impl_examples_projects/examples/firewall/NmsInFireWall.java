//$Id: NmsInFireWall.java,v 1.2 2008/07/24 14:41:01 tinku Exp $
/**
 * NmsInFireWall.java
 *
 *
 * Created: Fri Oct 19 21:36:44 2001
 *
 * @author <a href="mailto: "</a>
 * @version
 */

package test;
import com.adventnet.nms.startnms.NmsMainBE;
import java.rmi.server.RMISocketFactory;
import test.RMISocketFactoryImpl;
import java.lang.management.*;
import java.rmi.registry.*;
import java.util.*;
import javax.management.*;
import javax.management.remote.*;
import javax.management.remote.rmi.*;
/** 
 * This Example is used to configure the NMS with firewall setup.  
 */

public class NmsInFireWall 
{
    private static final int JMX_RMI_SERVER_PORT=3000;
    private static final int JMX_RMI_CONNECTOR_PORT=6000;
    public static void main(String arg[])
    {
        
        try
        {
            //Setting the port for Remote Managemention through JMX 
            setRemoteManagementForFirewall();
        /*  The purpose of setSocketFactory is if the remote object is not associated with a
          specific client and/or server socket factory.*/
                //Warning:Set the RMISocketFactory after calling the Above Method
         
           RMISocketFactory.setSocketFactory(new RMISocketFactoryImpl());
        }catch(Exception e)
        {
            e.printStackTrace();
        }
	// Now call the NMS BE
        NmsMainBE.main(arg);
    }
    private static void setRemoteManagementForFirewall() throws Exception
    {
        //start a separate RMIRegistry for Remote Management
        LocateRegistry.createRegistry(JMX_RMI_SERVER_PORT);
        //Get the MBean Server which is responsible for Monitoring
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        //create an RMI Connector server
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi://localhost:"+JMX_RMI_CONNECTOR_PORT+"/jndi/rmi://localhost:"+JMX_RMI_SERVER_PORT+"/jmxrmi");
        JMXConnectorServer connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
        //Start the RMI connector server
        connectorServer.start();
 

    }
}
