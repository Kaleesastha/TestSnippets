/**
 * usertest.java
 *
 *
 * Created: Wed Feb 28 14:16:52 2001
 *
 * @author <a href="mailto: "</a>
 * @version
 */
package test;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.TopoNotificationHandler;
import java.util.*;
import java.rmi.*;
import java.net.*;
import java.io.Serializable;

public class userTest implements TopoNotificationHandler,Serializable
{
	public userTest ()
	{
	}
	public boolean handleNotification(Properties[] p)
	{
					 System.out.println("The handleNotification method for the userTest called");
					 return true;
	}
}// usertest








