//$Id: ExampleClient.java,v 1.2 2007/02/22 15:02:55 srajeswari Exp $
package com.adventnet.nms.example.befe;

import com.adventnet.nms.startnms.*;
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.util.*;
import java.io.*;
import java.awt.Cursor;
import java.util.*;
import javax.swing.*;
/* This class uses the common socket for communicating with the ExampleFESession
or ExampleBESession.The "BEFE_ID" is the ID used by this Client to send the mesages//No Internationalisation
to Server.
*/
public class ExampleClient implements SocketConnection
{
	static final int CLOSE=4;
	static final int READY=5;							
	// Passed by the server to the client,if there is an Alert Updation or Addition
	// or Deletion.
	static final int ALERT_UPDATE=6;
	// Passed by the server to the client,if there is an Event Updation or Addition
	// or Deletion.
	static final int EVENT_UPDATE=7;
	// Passed by the client to the server,to get the total number of alerts and events
	// from the server.
	static final int REFRESH=8;
	static final int REMOVE=9;
	// An Unique ID to identify the ExampleFESession
	static final String ID="BEFE_ID";//No Internationalisation
	public static Hashtable frameInstances = new Hashtable(10);
	public static ExampleClient exampleClientInstance;	
	
	public ExampleClient()
	{
		// Registering with the MainSocketClient
		
		PureClientUtils.commonSocket.registerForResponses(this,"BEFE_ID");//No Internationalisation
		
		//sendReadyToFE();
	}
	
	public static ExampleClient getInstance()
	{
		if ( exampleClientInstance == null)
			exampleClientInstance = new ExampleClient();
		
		return exampleClientInstance;
	}

	public synchronized void registerForGettingUpdates(ExampleFrame frame,String name)
	{
		frameInstances.put(frame,name);
		// Passing READY ID
		sendNameToServer(frame);
		// Passing REFRESH ID
		getEventsAndAlerts(frame);
	}
	
	public synchronized void receive(byte[] data)
	{
		
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ObjectInputStream ois=null;
		try
		{
			 ois = new ObjectInputStream(bais);
			int code=ois.readInt();
			if ( code == REFRESH )
			{
				Hashtable eventsHash = (Hashtable)ois.readObject();
				Hashtable alertsHash = (Hashtable)ois.readObject();
				String name =ois.readUTF();
				displayEventsAndAlerts(eventsHash, alertsHash, name);
			}
			else if ( code == EVENT_UPDATE)
			{
				int eventsCount=ois.readInt();// 1 or -1
				String eseverity=ois.readUTF();
				String name =ois.readUTF(); 
				displayEvents(eseverity,eventsCount,name);
			}
			else if ( code == ALERT_UPDATE)
			{
				
				int alertsCount=ois.readInt();// 1 or -1
				String aseverity=ois.readUTF(); // severity
				String name =ois.readUTF(); // name
				displayAlerts(aseverity,alertsCount,name);
			}
		}
		catch(Exception e)
		{
			System.err.println(NmsClientUtil.GetString("Exception in receiving data from FE"));
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if ( bais != null)
					bais.close();
				if ( ois != null)
					ois.close();
			}
			catch(IOException ioe)
			{				
			}
		}
		
	}

	public void close()
	{
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos=null;
		try
		{
			dos = new DataOutputStream(baos);
			dos.writeInt(CLOSE);
			dos.flush();
			PureClientUtils.commonSocket.send(ID,baos.toByteArray() );
		}
		catch ( Exception e)
		{
			System.err.println(NmsClientUtil.GetString("Exception in sending CLOSE  to FE SERVER"));
			e.printStackTrace();
		}
		finally
		{
			try
			{
				baos.close();
				if ( dos != null )
				dos.close();
			}
			catch(Exception ioe)
			{
			}
		}
		exampleClientInstance=null;
	}
	
	public void sendNameToServer(ExampleFrame frame)
	{
		String name=(String)frameInstances.get(frame);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos=null;
		try
		{
			oos = new ObjectOutputStream(baos);
			oos.writeInt(READY);
			oos.writeUTF(name);
			oos.flush();
			PureClientUtils.commonSocket.send(ID,baos.toByteArray() );
		}
		catch ( Exception e)
		{
			System.err.println(NmsClientUtil.GetString("Exception in sending READY  to FE SERVER"));
			e.printStackTrace();
		}
		finally
		{
			try
			{
				baos.close();
				if ( oos != null)
					oos.close();
			}
			catch(Exception e)
			{
			}
		}
		
	}
	// For removing the names from the server
	public void removeNameFromServer(ExampleFrame frame)
	{
		String name=(String)frameInstances.get(frame);
		int numberOfNames=0;
		if ( name != null)
		{
			Enumeration enumerate = frameInstances.elements();
			while(enumerate.hasMoreElements() )
			{
				String mObj=(String)enumerate.nextElement();
				if ( name.equals(mObj))
				{
					numberOfNames++;
				}
			}
		}
		
		frameInstances.remove(frame);
		if ( ! (numberOfNames > 1) )
		{
		//String name=(String)frameInstances.get(frame);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos=null;
			try
			{
				oos = new ObjectOutputStream(baos);
				oos.writeInt(REMOVE);
				oos.writeUTF(name);
				oos.flush();
				PureClientUtils.commonSocket.send(ID,baos.toByteArray() );
			}
			catch ( Exception e)
			{
				System.err.println(NmsClientUtil.GetString("Exception in sending READY  to FE SERVER"));
				e.printStackTrace();
			}
			finally
			{
				try
				{
					baos.close();
					if ( oos != null )
					oos.close();
				}
				catch(IOException ioe)
				{
				}
			}
		}
	}
	// REFRESH ID is passed to get events and alerts from the database
	public void getEventsAndAlerts(ExampleFrame frame)
	{
		String name=(String)frameInstances.get(frame);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos=null;
		try
		{
			oos = new ObjectOutputStream(baos);
			oos.writeInt(REFRESH);
			oos.writeUTF(name);
			oos.flush();
			PureClientUtils.commonSocket.send(ID,baos.toByteArray() );
            frame.frame.setCursor(new Cursor(Cursor.WAIT_CURSOR)); // Normal cursor will be resumed once, response is received.
		}
		catch ( Exception e)
		{
			System.err.println(NmsClientUtil.GetString("Exception in sending name  to FE SERVER"));
			e.printStackTrace();
		}
		finally
		{
			
			try
			{	
				baos.close();
				if ( oos != null )
				oos.close();
			}
			catch(IOException ioe)
			{
			}
		}
		
	}
	// For displaying events and alerts in the corresponding frame
	private void displayEventsAndAlerts(Hashtable eventsHash, Hashtable alertsHash, String name)
	{
			
		Enumeration keys = frameInstances.keys(); 
		while( keys.hasMoreElements() )
		{
			ExampleFrame frame=(ExampleFrame)keys.nextElement();
			if (((String) frameInstances.get(frame)).equals(name) )
			{
				frame.showEventsAndAlerts(eventsHash, alertsHash);
                frame.frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // During request the cursor has been changed to busy cursor & so default cursor is resumed here.
			}

		}
	}
	// For displaying events in the corresponding frame
	private void displayEvents(String severity,int eventsCount,String name)
	{
		//ExampleFrame.frameInstance.showEvents(eventsCount);	
		Enumeration keys = frameInstances.keys(); 
		
		while( keys.hasMoreElements() )
		{
			ExampleFrame frame=(ExampleFrame)keys.nextElement();
			if ( ((String)frameInstances.get(frame)).equals(name))
			{
				frame.showEvents(severity,eventsCount);
			}

		}
	}
	// For displaying alerts in the correspondinf frame.
	private void displayAlerts(String severity,int alertsCount,String name)
	{
		//ExampleFrame.frameInstance.showAlerts(alertsCount);
		Enumeration keys = frameInstances.keys(); 
		
		while( keys.hasMoreElements() )
		{
			ExampleFrame frame=(ExampleFrame)keys.nextElement();
			if ( ((String)frameInstances.get(frame)).equals(name))
			{
				frame.showAlerts(severity,alertsCount);
			}

		}
	}
	
}
							  

