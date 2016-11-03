/*
  $Id: URLDiscovery.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/

/**
 * URLDiscovery.java
 *
 */

package test;

import java.util.*;
import java.net.*;
import java.io.*;

import com.adventnet.nms.netwatch.UserDiscoveryInterface;
import com.adventnet.nms.netwatch.DiscoveryAPI;
import com.adventnet.nms.util.WatchUtil;

public class URLDiscovery implements UserDiscoveryInterface
{

    private URL url = null;
    Properties prop = null;
    String urlString = null;
    int port ;

    public Vector discover(String ipAddress, Vector vectOfProps)
    {
	Properties tempProps = null;

	if(ipAddress == null )
	{
	    return null;
	}
    if(vectOfProps == null || vectOfProps.isEmpty()) return null;
	
	Vector returnVector = new Vector();

	for(Enumeration en1 = vectOfProps.elements();en1.hasMoreElements();)
	{
	    tempProps = (Properties)en1.nextElement();
	    urlString = tempProps.getProperty("url");
	    port      = Integer.parseInt(tempProps.getProperty("port"));
	    try
	    {
		url = new URL("http://"+ipAddress+":"+port+"/"+urlString);
		DiscoveryAPI.println("Trying to connect to "+url);
	    }
	    catch (MalformedURLException mue)
	    {
		System.out.println ("Malformed URL " + url+mue);
		continue;
	    }
	    
	    try
	    {
		HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
		urlCon.connect();
		urlCon.disconnect();
	    }
	    catch (IOException ioe)
	    {
			continue;
	    }
	    
	    prop = new Properties();
	    prop.setProperty("uClass","test.URLPing");
	    prop.setProperty("tester","usertest");
	    prop.setProperty("pollInterval","60");
	    prop.setProperty("type","URL");
		prop.setProperty("ipAddress",ipAddress);
	    prop.setProperty("port",String.valueOf(port));
	    prop.setProperty("url",urlString);
		prop.setProperty("name",WatchUtil.getDNSName(ipAddress)+"_URL");
		returnVector.addElement(prop);
	    return returnVector;
	}
	return null;
    }
    
}
    



