package com.adventnet.util;
import java.net.Socket;
import java.io.IOException;
public class FirewallTest
{
    public FirewallTest () {}
    public static void main(String args[])
    {
        Socket socket = null;
        if(args.length < 2)
        {
                System.out.println("Usage: BE_HOST List_of_Ports");
                System.exit(0);
        }
	String ports[] = new String[args.length];
	String host = args[0];
	String errorSockets="";
	for(int k=1; k<args.length;k++)
	{
		ports[k]=args[k];
	}
	for(int k=0; k<ports.length; k++)
	{
		int port = 0;
		try{
			if(ports[k] != null)
			{
				port = Integer.parseInt(ports[k]);
			}
		}
		catch(NumberFormatException nfe)
		{
			System.err.println(ports[k]+ " is not a valid port!");
		}
		try
		{
			if (port != 0)
			{
				socket = new Socket(host,port);
				System.out.println("Socket for "+port+" is:: "+socket);
			}
		}
		catch(Exception es)
		{
			//es.printStackTrace();
			errorSockets = errorSockets+","+ports[k];
		}
		finally
		{
			if(socket != null)
			{
				try{socket.close();}catch(IOException ioe){ioe.printStackTrace();}
			}
		}
	}
	if(!errorSockets.equals(""))
	{
		System.err.println("Connection cannot be established to the following ports: "+errorSockets);
	}
    }
}
