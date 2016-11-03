import java.io.*;
import java.net.*;
public class SocketTriggerGenerator
{
	/**
		This send a trigger to initiate provisioning operation. 
		First it asks for confirmation. On accepting it, a new socket
		is created to initiate trigger.And then the socket is closed.
	**/
	public static void main(String ar[])
	{
		try
		{
			System.out.print("Do you want to initiate Provisioning of using_Trigger_SnmpNodeTemplate?(y/n)");
			String ok=(new DataInputStream(System.in)).readLine();
			if (ok.equalsIgnoreCase("y"))
			{
				System.out.print("Enter the host name of the Provisioning Server : ");
				String host=(new DataInputStream(System.in)).readLine();
				(new Socket(host,8539)).close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
