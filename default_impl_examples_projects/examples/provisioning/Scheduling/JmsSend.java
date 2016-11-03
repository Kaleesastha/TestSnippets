package test.provisioning;

import java.util.Hashtable;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Example program for sending Jms Messages to the Jms Provider.
 * Based on these the Jms Trigger will be invoked.
 * The filter Criteria for this Message is "MESSAGETYPE=PROVMESSAGE"
 * This program assumes that the J2ee Server is the Jms Provider
 * and the ConnectionFactory Name is "QueueConnectionFactory"
 * and the Queue Name is "requestQueue"
 * 
 */

public class JmsSend
{
	public static void main(String args[])
	{
		QueueConnection conn = null;
		try
		{

            //Need to set the System Properties if the J2ee Server is running 
			//in different machine and different port. If the Jms Provider is
			//other than J2ee Server use PROVIDER_URL property.
			System.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
			System.setProperty("org.omg.CORBA.ORBInitialPort", "1050");

			Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");  
			Context cont = new InitialContext(env);
			QueueConnectionFactory q = (QueueConnectionFactory) cont.lookup("QueueConnectionFactory");
			conn = q.createQueueConnection();
			Queue requestQueue  = (Queue) cont.lookup("requestQueue");
			QueueSession senderSession = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = senderSession.createSender(requestQueue);
			TextMessage message = senderSession.createTextMessage();
			message.setStringProperty("MESSAGETYPE", "PROVMESSAGE");
			sender.send(message,DeliveryMode.PERSISTENT, 4, 0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null)
			{
				try
				{
					conn.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
