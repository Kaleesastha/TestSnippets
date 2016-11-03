// $Id: ExampleBESession.java,v 1.4 2010/10/29 13:45:39 swaminathap Exp $
package com.adventnet.nms.example.befe;

//NMS Imports
import com.adventnet.nms.startnms.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.util.*;

//Java Imports
import java.io.*;
import java.util.*;	

/* A separate Session is created for each FE, connecting to the Server BE.
If the receive() method in the Corresponding FE Session returns false,then
the request is forwarded to this Session.*/

public class ExampleBESession implements SocketSessionConnectionBE, EventObserver,AlertListener,Serializable
{
	static final int CLOSE=4;
	static final int READY=5;							
	static final int ALERT_UPDATE=6;
	static final int EVENT_UPDATE=7;
	static final int REFRESH=8;
	static final int REMOVE=9;
	// A unique ID to identify the client(ExampleClient.java)
	static final String ID="BEFE_ID";//No Internationalisation
	/* Holds the ManagedObject Names, for which updates 
	will bw sent */
	Vector mNames = new Vector(20);
	transient MainSocketSessionBE mss;
	//Request ID from the server.Use this sending the response
	String uniqueID;
	
	public ExampleBESession(MainSocketSessionBE mss)
	{
		this.mss=mss;
		mss.registerForResponses(this,ID);
		registerForEventUpdates();
		registerForAlertUpdates();
		
		
	}
	/* This method will be called for any request from the client, if not
	handled by the SessionFE*/
	public synchronized void  receive(String uid,byte[] data)
	{
		
		uniqueID=uid;
		ObjectInputStream ois=null;
		try
		{
			
			ois = new ObjectInputStream(new ByteArrayInputStream(data) );
			int read = ois.readInt();
			if ( read == READY )
			{
				String name= ois.readUTF();
				if(! mNames.contains(name) )
					mNames.addElement(name);
				
			}
			else if ( read == CLOSE )
			{
				// Session is closed.
			}
			else if ( read == REMOVE)
			{
				String name= ois.readUTF();
				mNames.remove(name);
				
			}
			else if ( read == REFRESH )
			{
				
				String name=ois.readUTF();
				Vector events =getEventsFromServer(name);
				int totalEvents=0;
				if ( events != null)
					totalEvents=events.size();
				Hashtable Etable = new Hashtable(10);
				Enumeration enumerate  = ExampleBEServer.Severitytable.keys();
				while ( enumerate.hasMoreElements() )
				{
					int val=((Integer)enumerate.nextElement()).intValue();
					Etable.put(ExampleBEServer.Severitytable.get(new Integer(val)),new Integer(0));
					int count=0;
					for ( int i=0;i<totalEvents;++i)
					{	
						Event e = ExampleBEServer.eventAPI.getEventByID(Integer.parseInt( (String)events.elementAt(i)));
						if ( e != null)
						{
							if (e.getSeverity() == val)
								count++;
						}
					}
					Etable.put( ExampleBEServer.Severitytable.get(new Integer(val) ),new Integer(count));
				}
				

                Vector alerts =getAlertsFromServer(name);
                int totalAlerts=0;
                if ( alerts != null)
                    totalAlerts=alerts.size();
                Hashtable Atable = new Hashtable(10);
                Enumeration en= ExampleBEServer.Severitytable.keys();
                while ( en.hasMoreElements() )
                {
                    int val=((Integer)en.nextElement()).intValue();
                    Atable.put(ExampleBEServer.Severitytable.get(new Integer(val)),new Integer(0));
                    int count=0;
                    for ( int i=0;i<totalAlerts;++i)
                    {	
                        Alert a = ExampleBEServer.alertAPI.getAlert((String)alerts.elementAt(i));
                        if ( a != null)
                        {
                            if (a.getSeverity() == val)
                                count++;
                        }
                    }
                    Atable.put( ExampleBEServer.Severitytable.get(new Integer(val) ),new Integer(count));
                }
					
                sendToClient(Etable, Atable, name);
				
			}
			
		}
		catch ( Exception e)
		{
			System.err.println(NmsUtil.GetString("Error in getting the received data"));
			e.printStackTrace();
		}
		finally 
		{
			if ( ois != null )
			{
				try
				{
					ois.close();
				}
				catch(IOException ioe)
				{
				}
			}
		}
	}
	public void close()
	{
		
            // added to deregister the ExampleBESession.
            try
            {
                getEventAPI().deregisterForEvents(this);
                getAlertAPI().removeAlertListener(this);
            }
            catch (java.rmi.RemoteException rex)
            {
                System.out.println("Exception while deRegistering for Alerts and Events in ExampleBEServer : " + rex);//no i18n
            }
            mNames=null;		
            uniqueID=null;
            mss=null;
            
            System.out.println(NmsUtil.GetString("ExampleBESession closed"));
	}
	//To get the instance of EventAPI
	private EventAPI getEventAPI()
	{
		try
		{
			EventAPI eventapi= ExampleBEServer.eventAPI;
			while ( eventapi == null)
			{
				eventapi= (EventAPI)NmsUtil.getAPI("EventAPI");//No I18N
				if ( eventapi == null)
				{
					System.err.println(NmsUtil.GetString("Eventapi is null, still trying "));
					try
					{
						Thread.sleep(200);
					}
					catch(Exception e)
					{}
				}
			}
			return eventapi;
		}
		catch ( Exception e)
		{
			System.err.println(NmsUtil.GetString("Error in getting the Eventapi"));
			e.printStackTrace();
		}
		return null;
	}
	// To get all Events from the Server for the corresponding ManagedObject
	private Vector getEventsFromServer(String name)
	{
		
		try
		{
			
			EventAPI eventapi= getEventAPI();
			
			Properties propToSend = new Properties();
			propToSend.put("source", "<" + name + ">");//No Internationalisation
			Vector events = eventapi.getObjectNamesWithProps(propToSend);
			return events;
		}
		catch ( Exception e)
		{
			System.err.println(NmsUtil.GetString("Exception in getting the EventAPI instance"));
			e.printStackTrace();
		}
		return null;
	}
	// To get the AlertAPI instance
	private AlertAPI getAlertAPI()
	{
		try
		{
			AlertAPI alertapi = ExampleBEServer.alertAPI;
			while ( alertapi == null)
			{
				alertapi= (AlertAPI)NmsUtil.getAPI("AlertAPI");//No I18N
				if ( alertapi == null)
				{
					System.err.println(NmsUtil.GetString("Alertapi is null, still trying "));//No I18N
					try
					{
						Thread.sleep(200);
					}
					catch(Exception e)
					{}
				}
			}
			return alertapi;
		}
		catch (Exception e)
		{
			System.err.println(NmsUtil.GetString("Exception in getting the AlertAPI"));
			e.printStackTrace();
		}
		return null;
	}
	// To get the Alert for the Selected ManagedObject
	private Vector getAlertsFromServer(String name)
	{
		
		try
		{
			AlertAPI alertapi=getAlertAPI();
			Properties propToSend = new Properties();
			propToSend.put("source", "<" + name + ">");//No Internationalisation
			Vector alerts=alertapi.getObjectNamesWithProps(propToSend);
			return alerts;
		}
		catch ( Exception e)
		{
			System.err.println(NmsUtil.GetString("Exception in getting the AlertAPI instance"));
			e.printStackTrace();
		}
		return null;
	}

	//To send the number of alerts and events for the object.
	private void sendToClient(Hashtable Etable, Hashtable Atable, String name)
	{
		try
		{	
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeInt(REFRESH);
			oos.writeObject(Etable); // Events table
			oos.writeObject(Atable); // Alerts table
			oos.writeUTF(name);
			oos.flush();
			mss.send(ID,uniqueID,baos.toByteArray());
			baos.close();
			oos.close();
		}
		catch ( Exception e)
		{
			System.err.println(NmsUtil.GetString("Exception in sending the data from BE to client"));
			e.printStackTrace();
		}
	}
	// To receive the Event Updates
	private void registerForEventUpdates()
	{
		try
		{
			EventAPI eventapi = getEventAPI();
			eventapi.registerForEvents(this);
		}
		catch ( Exception e)
		{
			System.err.println(NmsUtil.GetString(" Exception in getting the EventAPI instance"));
			e.printStackTrace();
		}
		
	}
	// To receive the Alert Updates
	private void registerForAlertUpdates()
	{
		try
		{
			AlertAPI alertapi = getAlertAPI();
			alertapi.addAlertListener(this);
		}
		catch ( Exception e)
		{
			System.err.println(NmsUtil.GetString(" Exception in getting the AlertAPI instance"));
			e.printStackTrace();
		}
	}
	// Called by the EventObserver, to notify the Events Updates in the server.
	public synchronized void update(Event ev)
	{
		
		try
		{
			for ( int i=0;i< mNames.size();++i)
			{
				String name=(String)mNames.elementAt(i);
				if ( ev.getSource().equals(name))
				{
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream  oos  = new ObjectOutputStream(baos);
					int severity=ev.getSeverity();
					String sname= (String)ExampleBEServer.Severitytable.get(new Integer(severity));
					int eventsCount=1;
					oos.writeInt(EVENT_UPDATE);
					oos.writeInt(eventsCount);
					oos.writeUTF(sname);
					oos.writeUTF(name);
					oos.flush();
					mss.send(ID,uniqueID,baos.toByteArray());
					baos.close();
					oos.close();
					
				}
			}
		}
		catch ( Exception e)
		{
			System.err.println(NmsUtil.GetString("Exception in sending the updates for events"));
			e.printStackTrace();
		}
		
	}
	// Called by the AlertObserver for AlertUpdates
	private void update(Alert al)
	{
		
		try
		{
			
			if (mNames!=null)
			{
			for ( int i=0;i< mNames.size();++i)
			{
				String name=(String)mNames.elementAt(i);
				if ( al.getSource().equals(name))
				{
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream  oos  = new ObjectOutputStream(baos);
					int severity=al.getSeverity();
					int alertsCount=1;
					
					String sname="";//No Internationalisation
					if ( severity != -1) // Not alert delete
					{
                        if(al.getPreviousSeverity() != -1) // For Updating an existing Alert,
                                // decrement the alert count for the previous severity &
                                // then add for new severity.
                        {
                            sname=(String)ExampleBEServer.Severitytable.get(new Integer(al.getPreviousSeverity() ));	
                            alertsCount=-1;
                            ByteArrayOutputStream baost = new ByteArrayOutputStream();
                            ObjectOutputStream  oost  = new ObjectOutputStream(baost);
                            oost.writeInt(ALERT_UPDATE);
                            oost.writeInt(alertsCount);
                            oost.writeUTF(sname);
                            oost.writeUTF(name);
                            oost.flush();
                            mss.send(ID,uniqueID,baost.toByteArray());
                            baost.close();
                            oost.close();
                        }
                        alertsCount = 1; // increment one both for new alert and updating
                                // an existing alert.
						sname= (String)ExampleBEServer.Severitytable.get(new Integer(severity));
					}
					else        // Alert delete
					{
						sname=(String)ExampleBEServer.Severitytable.get(new Integer(al.getPreviousSeverity() ));	
						alertsCount=-1;
					}
					oos.writeInt(ALERT_UPDATE);
					oos.writeInt(alertsCount);
					oos.writeUTF(sname);
					oos.writeUTF(name);
					oos.flush();
					mss.send(ID,uniqueID,baos.toByteArray());
					baos.close();
					oos.close();
				}
			}
			}
		}
		catch ( Exception e)
		{
			System.err.println(NmsUtil.GetString("Exception in sending the updates for alerts"));
			e.printStackTrace();
		}
	}

    //A Vector of Alerts is passed as the Parameter*/
	private void update(Vector v)
	{
		try
		{
			for ( int i=0;i<v.size();++i)
			{
				if ( v.elementAt(i) instanceof Alert )
				{
					update((Alert)v.elementAt(i));
				}
			}
		}
		catch ( Exception e)
		{
			System.err.println(NmsUtil.GetString("Exception in sending the updates for alerts"));
			e.printStackTrace();
		}
	}


    //implementation for AlertListener
    public void listenAlert(AlertActionInformer action)
    {

        if(action.isBatchUpdate())
        {

           update(action.getAlertList());
        }
        else
        {
            update(action.getAlert());
        }

    }

    //implementation for AlertListener
    public void update(XMLNode xmlNode)
    {
        //TODO: implementaion for bulk delete need to be provided
    }
	
}

