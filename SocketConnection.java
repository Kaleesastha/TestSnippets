package com.adventnet.util;
import java.net.Socket;
import java.io.IOException;
public class SocketConnection
{
    public SocketConnection () {}
    public static void main(String args[])
    {
        Socket socket = null;
	if(args.length !=2)
	{
		System.out.println("Usage: BE_HOST , Port (Port-1099 / NMS_FE_SECONDARY_PORT / PORT_TO_LISTEN)");
		System.exit(0);
	}
	String host = args[0];
	int port = Integer.parseInt(args[1]);
		try
		{
			socket = test.TimedSocket.getSocket(host,port,5000);
			System.out.println("Socket is::"+socket+","+System.currentTimeMillis());
		}
		catch(Exception es)
		{
			System.out.println("Exception at : "+System.currentTimeMillis());
			es.printStackTrace();
		}
		finally
		{
			if(socket != null)
			{
				try{socket.close();}catch(IOException ioe){ioe.printStackTrace();}
			}
		}
    }
}
