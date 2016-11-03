package com.adventnet.nms.provisioning.examples;
import com.adventnet.nms.provisioning.server.*;
import  com.adventnet.nms.provisioning.TriggerException;
import java.io.*;
import java.net.*;
/**
	This example class accepts triggers through socket 
	to initiate provisioning operation. 
**/
public class SocketTrigger extends ProvisioningTrigger implements Runnable
{
	private ServerSocket ss;
	/**
	Creates a serversocket instance to accept client socket to connect.
	*/
	public SocketTrigger()
	{
		try
		{
			ss=new ServerSocket(8539);
		}
		catch(Exception e)
		{
			System.err.println("Cannot instantiate serversocket at port 8539 to initiate triggers");
		}
	}
	/**
		Initiates a thread. This thread accepts the client sockets to get connected.
	*/
	public void register() throws TriggerException
	{
		new Thread(this).start();
	}

	/**
		Closes the server socket.
	**/

	public void deregister() throws TriggerException
	{
		if (ss!=null)
		{
			try
			{
				ss.close();
			}
			catch(Exception e)
			{
				throw new TriggerException("Cannot close serversocket");
			}
		}
	}

	/**
		Triggers when any client is get connected. First waits for client
		to get connected. Triggers the provisioning operation. Closes the 
		client connection.
	**/
	public void run()
	{
		if (ss!=null)
		{
			try
			{
				Socket s=ss.accept();
				if (s!=null)
				{
					super.trigger(null);
					s.close();
				}
			}
			catch(Exception e)
			{
				System.err.println("Cannot handle socket requests from clients");
			}
		}
	}
}
