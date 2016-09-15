package test;

import java.util.*;
import java.net.*;
import java.io.*;

import com.adventnet.nms.netwatch.UserDiscoveryInterface;
import com.adventnet.nms.netwatch.DiscoveryAPI;
import com.adventnet.nms.util.WatchUtil;

/* Values to be specified in userdiscovery.conf file to include this discovery process 
 * are as follows.

   <USER_DISCOVERY_INFO>
    <CLASS_INFO
	  DISCOVERY_CLASSNAME="test.ServiceDiscovery"
	  OBJECT_TYPE = "com.adventnet.nms.topodb.ServiceObject"/>
    <ARGS
      service="FTP"
      port="21" />
    <ARGS
      service="TELNET"
      port="23" />
  </USER_DISCOVERY_INFO>

  */
  
public class ServiceDiscovery implements UserDiscoveryInterface
{
    
    private Socket sock = null;
    Properties prop = null;
    String serviceName = null;
    int port ;
    
    public boolean checkService(String ipAddress, int portNo)
    {
	boolean success = false;
	try 
	{ 
	    sock = new Socket(ipAddress,portNo);
	    success = true;
	    DiscoveryAPI.println("Successful in connecting to port"+portNo+" of "+ipAddress);
	}
	catch(Exception e) {}
	
	if (success) 
	{ 
	    try 
	    { 
		sock.close();
		return true;
	    }
	    catch(Exception exc) 
	    { 
		return true;
	    }
	}
	else 
	{ 
	    return false;
	}
    }
    
    public Vector  discover(String ipAddress, Vector vectOfProps)
    {
	Properties tempProps = null;
	Vector returnVector = new Vector();
	boolean discovered = false;

	if(ipAddress == null )
	{
	    return null;
	}
	
	for(Enumeration en1 = vectOfProps.elements();en1.hasMoreElements();)
	{
	    tempProps   = (Properties)en1.nextElement();
	    serviceName = (String)tempProps.getProperty("service");
	    port        = Integer.parseInt(tempProps.getProperty("port"));
	    
		//String forByteArray = "testvalue";
	    if(checkService(ipAddress, port))
	    {
		discovered = true;
		prop = new Properties();
		prop.setProperty("pollInterval","60");
		prop.setProperty("type",serviceName);
		prop.setProperty("port",String.valueOf(port));
		prop.setProperty("serviceType",serviceName);
		prop.setProperty("ipAddress",ipAddress);
		prop.setProperty("name",WatchUtil.getDNSName(ipAddress)+"_"+serviceName);
		//Byte[] b = forByteArray.getBytes();
		//prop.setProperty("byteArrayTest",forByteArray);
		returnVector.addElement(prop);
	    }
	}
	if(discovered)
	{
	    return returnVector;
	}
	else
	{
	    return null;
	}
    }
}
    
    


