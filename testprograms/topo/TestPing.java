/* $Id: TestPing.java,v 1.1 2004/02/15 15:07:25 priya Exp $
 *
 * File Name      : TestPing.java
 * Description    : To Check the ICMP PING TIMEOUT functionality 
 * Other Info     :
 *
 * USAGE          : java TestPing <hostname> <timeout value>
 * Parameter Desc :
 *
 * Owner Name     : priya
 * Change History(Author Date(dd-mm-yyy) and Description of methods added/modifed/deleted):
 */

import com.adventnet.nms.util.Ping;
import com.adventnet.nms.util.*;
import java.io.*;
import java.text.*;
import java.util.*;
public class TestPing
{
    public static void main(String args[])
    {
        
        long  startTime =  System.currentTimeMillis();
        String hostname = args[0];
        int timeout  = Integer.parseInt(args[1]);
       // comment the following if not needed
        Ping.setPingTimeout(timeout);
        boolean result = Ping.ping(args[0]);
        System.out.println("Time taken by the server to get the request in milliseconds " + (startTime -System.currentTimeMillis()));//No Internationalisation
      
}
}
