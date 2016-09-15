import java.util.*;

import com.adventnet.util.*;
import com.adventnet.nms.util.*;

public class PingTest implements Runnable
{
	Thread th;
	String network;
	static NativePing nPing = null;
	Date startTime;

	public PingTest (String net)
	{
		network = net;
		startTime = new Date ();

		if (nPing == null)
		{
			nPing = new NativePing ();
		}

		//th = new Thread(this);
		//th.start();
	}

	public void run ()
	{
		String host = "";

		while (true)
		{
			//nPing.pingBroadcast (network+"0", "255.255.255.0");
			//nPing.pingNet (network+"0", "255.255.255.0");

			for (int i = 1; i < 255; i++)
			{
				host = network + i;
				Date currentTime = new Date ();
				System.out.println ("Pinging Host : " + host + " Start Time : " + startTime + ", Current Time : " + currentTime);
				if (nPing.pingHost (host, 1) == 0)
				{
					System.out.println ("Ping Successful for Host : " + host);
				}
				else
				{
					System.out.println ("Ping Failed for Host : " + host);
				}
				System.out.println();
			}
		}

	}

	void pingHost (String host)
	{
	  if (nPing.pingHost (host, 1) == 0)
	  {
		System.out.println ("Ping Successful for Host : " + host);
	  }
	  else
	  {
		System.out.println ("Ping Failed for Host : " + host);
	  }
	}

	public static void main (String s[])
	{
		PingTest test1 = new PingTest ("192.168.4.");
		PingTest test2 = new PingTest ("192.168.5.");
		PingTest test3 = new PingTest ("192.168.1.");

		NmsUtil.nativePing = true;
try
{
		Ping.ping ("rajesh");
}
catch (Exception e) 
{
	System.out.println (e);
}
		//System.exit(0);

		test1.pingHost ("adventnet.com");
		test1.pingHost ("yahoo.com");
		test1.pingHost ("hotmail.com");
		test1.pingHost ("java.sun.com");
		test1.pingHost ("google.com");
		test1.pingHost ("chennaionline.com");
		test1.pingHost ("eth.net");
		test1.pingHost ("indya.com");
		test1.pingHost ("whatis.com");
		test1.pingHost ("lantana.tenet.res.in");
		test1.pingHost ("206.103.12.151");
	}


}
