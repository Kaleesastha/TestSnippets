import java.util.*;
import java.io.*;
import java.net.*;
import java.text.MessageFormat;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.SnmpPing;
import com.adventnet.nms.util.Ping;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.IPV6Util;
import com.adventnet.nms.util.WatchUtil;
import com.adventnet.management.log.Log;


public class IPV6Ping
{
    
    public static void main(String[] s)
    {
	    try
	    {
		    int ping_retries = 10;
		    String ipStack = "V4";  
		    boolean a1 = Ping.ping("127.0.0.1", ping_retries);  //No internationalization
		    boolean b1 = Ping.ping("0:0:0:0:0:0:0:1", ping_retries);  //No internationalization
		    if(a1 && b1)
			    ipStack = "DUAL_STACK"; //No internationalization
		    else if(a1 && !b1)
			    ipStack = "V4";  //No internationalization
		    else if(!a1 && b1)
			    ipStack = "V6";  //No internationalization


		    System.err.println("The machine address is:"+java.net.InetAddress.getLocalHost().getHostAddress());
		    System.err.println("ipStack is::"+ipStack+"a1 & b1 are::"+a1+"and"+b1);
		    System.err.println(""+WatchUtil.getMACAddrFromIPV6Addr("fe80::214:22ff:fef7:d6c"));
	    }
	    catch(Exception e)
	    {
		    e.printStackTrace();
	    }
    }
}
