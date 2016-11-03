//$Id: RMISocketFactoryImpl.java,v 1.2 2008/07/24 14:41:01 tinku Exp $
package test;
import java.rmi.server.*;
import java.net.*;
import java.io.*;
import com.adventnet.nms.util.NmsUtil;
 /**
  *  An RMISocketFactoryImpl instance is used by the RMI runtime in order to
  *  obtain client and server sockets for RMI calls. An application may use the
  *  setSocketFactory method to request that the RMI runtime use its
  *  socket factory instance instead of the default implementation.
  *
  *  The default socket factory implementation used goes through a
  *  three-tiered approach to creating client sockets. First, a direct socket
  *  connection to the remote VM is attempted. If that fails (due to a firewall),
  *  the runtime uses HTTP with the explicit port number of the server. If the
  *  firewall does not allow this type of communication, then HTTP to a cgi-bin
  *  script on the server is used to POST the RMI call.
  */



public class RMISocketFactoryImpl extends RMISocketFactory
{
    private static final int RMI_CONNECTOR_PORT=6010;
    /**
     *Create two server sockets, one to run RmiRegistry(Only in the case of runn
     *ing Web NMS as service) and another to export all remote objects.
     */
    public ServerSocket createServerSocket(int port) throws IOException
    {
        if(port == NmsUtil.getRegistryPort())
        {
            return new ServerSocket(port,0,InetAddress.getLocalHost());
        }
        else
        {
            return new ServerSocket(RMI_CONNECTOR_PORT,0,InetAddress.getLocalHost());
        }
        
    }

   /**
    *   Creates a client socket connected to the specified host and port.
    */

    public Socket createSocket(String host , int port) throws IOException,UnknownHostException
    {
        
        return new Socket(host,port);
    
    }
			
}

