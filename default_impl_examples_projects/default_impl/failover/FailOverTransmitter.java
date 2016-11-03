//$Id: FailOverTransmitter.java,v 1.2.4.1 2013/05/13 13:04:02 venkatramanan Exp $

package com.adventnet.nms.example;

//Java imports
import java.util.Properties;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import test.TimedSocket;

//Nms imports
import com.adventnet.nms.util.ShutDownObserver;
import com.adventnet.management.log.Log;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.util.AbstractFailOverTransmitter;

/**
 * This is an default implementation for AbstractFailOverTransmitter.
 * The primary server failure identification should be based on the user environment. 
 * Hence users can plug in their own failure identification mechanism. 
 * This is because sooner you identify the primary failure,faster the failover period. 
 * This default implementation based on the TCP communication failure mechanism to 
 * identify the primary server failure.In Primary Server a ServerSocket will be opened. 
 * Whenever standby server is started it will connect to the ServerSocket.Based on the socket 
 * communication failure standby server will identify the primary failure.
 * This default implementation will implements ShutDownObserver.This is because whenever 
 * Primary Server is properly shutdown, it will notify the standby Server about Primary shutdown.
 */


public class FailOverTransmitter extends AbstractFailOverTransmitter implements ShutDownObserver
{
	private Socket s1 = null;
	private ServerSocket ss = null;
	private InputStream input = null;
	private OutputStream output = null;
	private int PRIMARY_FAILURE = 1;
        private boolean PRIMARY_SHUTDOWN=false;
	int monitor_port = 2014;
	String monitorhost = "localhost"; //No I18N

	/**
	 * This method should be called if the Server type is Primary.
	 * Here users can write their own communication mechanism.
	 * @param Properties contains the common parameters specified in the FailOver.xml file
	 * */

	public void preparePrimary(Properties prop)
	{
		//Register as ShutDownObserver
		PureServerUtils.registerForShutDown(this);
		try
		{
			//Open a ServerSocket
			int port = Integer.parseInt(prop.getProperty("MONITOR_PORT"));
			ss = new ServerSocket(port);
		}
		catch(Exception e)
		{
			System.err.println("Can not able to prepare Primary for HotStandby");
			e.printStackTrace();
			return;
		}
		while(!PRIMARY_SHUTDOWN)
		{
			try
			{
				//Accept the connection from standby server
				s1 = ss.accept();
				input = s1.getInputStream();
				output = s1.getOutputStream();
				int a = input.read();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				close();
			}
		}
	}

	/**
	 * This method should be called if the Server type is Standby.
	 * Whenever standby server is started as hot standby the user can register 
	 * with the Primary Server.
	 * After identify the Primary Server failure the user should return true.In that case
	 * the hotstandby modules will get Pre notification immediately after the Primary Server
	 * failure.
	 * If the standby server is not able to communicate(register) with the Primary Server, 
	 * then the user should return false. In that case the hotstandby modules will get 
	 * Pre notification only after identify the Primary Server failure via the 
	 * BEFailOver mechanism.
	 * @param Properties contains the common parameters specified in the FailOver.xml file
	 * and the Primary Server parameters including HOSTNAME, NMSBEPORT etc.
	 * @return boolean variable. 
	 */
/**/
	public boolean monitorPrimary(Properties prop)
	{
		try
		{
			createConnectionWithPrimary(prop);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Can not able to register with Primary Server for HotStandby notification");
			return false;
		}
		try
		{
			/*Implementation to monitor PRIMARY server changed for TTC_MARCONI*/
			Socket monitor_socket;
			while(true)
			{
				/*TimedSocket utility is (http://bit.ly/TimedSocket) specifically designed for handling IOExceptions */
				monitor_socket = TimedSocket.getSocket(monitorhost, monitor_port, 30000); //Setting timeout as 30 sec. for the socket on Primary's 2014 port
				if (monitor_socket == null) //If no TimedSocket can be opened for 2014, decide for PRIMARY's failure. 
				{
					NmsLogMgr.HAERR.log("Primary Socket is null. returning true ",Log.SUMMARY);
					return true; //This will return true to call preFailOverListener.
				}
				else //Otherwise - PRIMARY is alive - wait for 30 sec. This time can be reduced if we want the time-to-open-trapport should be small!
				{
					try{monitor_socket.close();} catch(Exception exp){}
					Thread.sleep(30000);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close();
		}
		return true;
	}
/**/

	//Try again with the primary and ensure the primary failure
	private void createConnectionWithPrimary(Properties prop) throws IOException
	{
		int port = Integer.parseInt(prop.getProperty("MONITOR_PORT"));
		String host = prop.getProperty("HOSTNAME");
		monitorhost = host;
		monitor_port = port;
		s1 = new Socket(host, port);
		input = s1.getInputStream();
		output = s1.getOutputStream();
	}

	//Impl for ShutDownObserver
	public void shutDown()
	{
		try
		{
			if(output != null)
			{
				output.write(PRIMARY_FAILURE);
				ss.close();
				PRIMARY_SHUTDOWN=true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close();
		}
	}

	//close the streams
	private void close()
	{
		try
		{
			if(input != null)
				input.close();
			if(output != null)
				output.close();
			if(s1 != null)
				s1.close();
		}
		catch(Exception e) {}
	}

}


