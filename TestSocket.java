package com.adventnet.util;
import java.net.Socket;
import java.io.IOException;
public class TestSocket
{
    public TestSocket () {}
    public static void main(String args[])
    {
        Socket socket = null;
	if(args.length !=2)
	{
		System.out.println("Usage: HOST , SSH_Port");
		System.exit(0);
	}
	String host = args[0];
	int port = Integer.parseInt(args[1]);
	try
	{
		socket = TimedSocket.getSocket(host,port);
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
