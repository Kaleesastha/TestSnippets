import java.net.*;

public class inetAddressTest
{

	public static void main(String args[])
	{
		String hostname = null;
		try
		{
			hostname = InetAddress.getLocalHost().getHostName();
		}
		catch(Exception ex)
		{
		}

		System.out.println("hostname = " + hostname);
	}
}
