/*
  $Id: URLPing.java,v 1.6 2008/07/30 11:30:53 barathv Exp $
*/

/**
 * URLPing.java
 *
 */

package test;

import com.adventnet.nms.util.NmsLogMgr;
import java.net.*;
import java.io.*;

import com.adventnet.nms.netwatch.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.severity.*;
import java.util.Properties;

public class URLPing implements UserTester
{
    String urlString = null;
    int port ;
    URL url = null;
    String ipAddress = null;
    
    public int test(String moname, Properties prop, TopoAPI api) 
    {
	SeverityIterator iterator = SeverityInfo.getInstance().getIterator();
        ManagedObject obj = null;
        try
        {
             obj = api.checkOut(moname,0,false,true);
             
         }
         catch( Exception ex) {
            NmsLogMgr.TOPOERR.fail("Exception in getting the object  " + obj.getName(),ex); // no i18n
        }
	if (obj instanceof TopoObject) 
	{        
	    urlString = (String)obj.getUserProperty("url"); //No I18N
	    port = Integer.parseInt((String)(obj.getUserProperty("port"))); //No I18N
	    ipAddress = ((TopoObject)obj).getIpAddress();
	    try
	    {
		url = new URL("http://"+ipAddress+":"+port+"/"+urlString);
		DiscoveryAPI.println("USER Tester for "+url);
		HttpURLConnection urlCon = (HttpURLConnection)url.openConnection ();
		urlCon.connect();
		urlCon.disconnect();
		return SeverityInfo.getInstance().getClear();
	    }
	    catch(MalformedURLException e)
	    {
		iterator.moveToHighest();
                return iterator.getPreviousCriticality();
	    }
            catch(IOException ie)
	    {
		iterator.moveToHighest();
                return iterator.getPreviousCriticality();
	    }
	}
        return 5;
    }
}// URLPing





