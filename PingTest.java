import com.adventnet.nms.util.Ping;
import java.net.InetAddress;
public class PingTest
{
    
    public static void main(String[] s)
    {
	    while(true)
	    {
		    try
		    {
			    long start = System.currentTimeMillis();
			    boolean result = com.adventnet.nms.util.Ping.ping(s[0]);
			    long end = System.currentTimeMillis();
			    System.out.println("Ping result:" + result + " - took " + (end - start) + "ms");
		    }
		    catch(Exception e)
		    {
			    e.printStackTrace();
		    }
	    }
    }
}
