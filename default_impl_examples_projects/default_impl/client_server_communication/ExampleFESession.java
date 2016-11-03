//$Id: ExampleFESession.java,v 1.3 2008/07/15 07:25:58 swaminathap Exp $
package com.adventnet.nms.example.befe;

//NMS imports
import com.adventnet.nms.startnms.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;

// JavaImports
import java.io.*;
import java.util.*;	
/*The ExampleClient will request a session via the common socket
  and this Session will be invoked to handle the requests for that client.
This class implements EventObserver and AlertObserver,in order to receive 
Updates.This class is just an example for showing Client-FE communication.
*/
  
public class ExampleFESession implements SocketSessionConnectionFE, EventObserver,AlertListener,Serializable
{
    static final int CLOSE=4;
    static final int READY=5;							
    static final int ALERT_UPDATE=6;
    static final int EVENT_UPDATE=7;
    static final int REFRESH=8;
    static final int REMOVE=9;
    // A unique to identify the client (ExampleClient.java)
    static final String ID="BEFE_ID";//No Internationalisation
    /* This vector holds the names of the ManagedObject, for which updation 
       has to be sent.*/
    Vector mNames = new Vector(20);
	
    transient MainSocketSessionFE mss;
    /* Each request from the server will come with an ID . It
       is urged that you respond with the same ID . */
    String uniqueID;
	
    public ExampleFESession(MainSocketSessionFE mss)
    {
        this.mss=mss;
        mss.registerForResponses(this,ID);
        registerForEventUpdates();
        registerForAlertUpdates();
		
		
    }

    /* This method will be called for any request from the client */
    public synchronized boolean receive(String uid,byte[] data)
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
                        // Client session over
                    }
                else if ( read == REMOVE)
                    {
                        String name= ois.readUTF();
                        mNames.remove(name);
					
                    }
                else if ( read == REFRESH )
                    {
             
                        String name=ois.readUTF();
                        Hashtable Etable = new Hashtable(10);
                        Enumeration enumerate = ExampleFEServer.Severitytable.keys();
                        while ( enumerate.hasMoreElements() )
                            {
                                int val=((Integer)enumerate.nextElement()).intValue();
                                Etable.put(ExampleFEServer.Severitytable.get(new Integer(val)),new Integer(0));
                                Vector events = getEventsFromServer(name,val);
                                int totalEvents = 0;
                                if(events!=null)
                                    {
                                        totalEvents = events.size();
                                    }
                                Etable.put(ExampleFEServer.Severitytable.get(new Integer(val)),new Integer(totalEvents));
                            }
                        Hashtable Atable = new Hashtable(10);
                        Enumeration en= ExampleFEServer.Severitytable.keys();
                        while ( en.hasMoreElements() )
                            {
                                int val=((Integer)en.nextElement()).intValue();
                                Atable.put(ExampleFEServer.Severitytable.get(new Integer(val)),new Integer(0));
                                int count=0;

                                Vector alerts= getAlertsFromServer(name,val);
                                if(alerts != null)
                                    {
                                        count=alerts.size();
                                    }	
                                Atable.put( ExampleFEServer.Severitytable.get(new Integer(val) ),new Integer(count));
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
                try
                    {
                        if ( ois != null)
                            ois.close();
                    }
                catch(IOException ioe)
                    {
                    }	
            }
        return true;
    }
    public void close()
    {
	
        // added to deregister the ExampleFESession.
        try
            {
                getEventAPI().deregisterForEvents(this);
                getAlertAPI().removeAlertListener(this);
            }
        catch (java.rmi.RemoteException rex)
            {
                System.out.println("Exception while deRegistering for Alerts and Events in ExampleFEServer : " + rex);
            }	
        mNames=null;
        uniqueID=null;
        mss=null;
        System.out.println(NmsUtil.GetString("ExampleFESession is closed"));
    }

    // To get the instance of EventAPI
    private EventAPI getEventAPI()
    {
        try
            {
                EventAPI eventapi= ExampleFEServer.eventAPI;
                while ( eventapi == null)
                    {
                        eventapi= ExampleFEServer.eventAPI;
                        if ( eventapi == null)
                            {
                                System.err.println(NmsUtil.GetString("Eventapi is null, still trying "));
                            }
                        Thread.sleep(2000);
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
    // To get all the events from the Server for the particular ManagedObject.
    private Vector getEventsFromServer(String name, int severity)
    {
        try
            {
                EventAPI eventapi= getEventAPI();
			
                Properties propToSend = new Properties();
                propToSend.put("source", "<" + name + ">");//No Internationalisation
                propToSend.put("severity",String.valueOf(severity));
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
                AlertAPI alertapi = ExampleFEServer.alertAPI;
                while ( alertapi == null)
                    {
                        alertapi= ExampleFEServer.alertAPI;
                        if ( alertapi == null)
                            {
                                System.err.println(NmsUtil.GetString("Eventapi is null, still trying "));
                            }
                        Thread.sleep(2000);
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
    //To get the Alert for the selected Managed Object
    private Vector getAlertsFromServer(String name, int severity)
    {
		
        try
            {
                AlertAPI alertapi=getAlertAPI();
                Properties propToSend = new Properties();
                propToSend.put("source","<" + name + ">");//No Internationalisation
                propToSend.put("severity",String.valueOf(severity));
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
                System.err.println(NmsUtil.GetString("Exception in sending the data from FE to client"));
                e.printStackTrace();
            }
    }
    // To receive the Event updates.
    private void registerForEventUpdates()
    {
        try
            {
                EventAPI eventapi = getEventAPI();
                eventapi.registerForEvents(this);
            }
        catch ( Exception e)
            {
                System.err.println(NmsUtil.GetString("Exception in getting the EventAPI instance"));
                e.printStackTrace();
            }
		
    }
    // To receive the AlertUpdates
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
                if (mNames!=null)
                    {
			for ( int i=0;i< mNames.size();++i)
                            {
				String name=(String)mNames.elementAt(i);
				if ( ev.getSource().equals(name))
                                    {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream  oos  = new ObjectOutputStream(baos);
					int severity=ev.getSeverity();
					String sname= (String)ExampleFEServer.Severitytable.get(new Integer(severity));
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
            }
        catch ( Exception e)
            {
                System.err.println(NmsUtil.GetString("Exception in sending the updates for events"));
                e.printStackTrace();
            }
		
    }
   
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
                                                        sname=(String)ExampleFEServer.Severitytable.get(new Integer(al.getPreviousSeverity() ));	
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
						sname= (String)ExampleFEServer.Severitytable.get(new Integer(severity));
                                            }
					else        // Alert delete
                                            {
						sname=(String)ExampleFEServer.Severitytable.get(new Integer(al.getPreviousSeverity() ));	
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
   
    // A Vector of Alerts is passed as the Parameter
    private void update(Vector v)
    {
        try
            {
                if (mNames!=null)
                    {
			for ( int i=0;i<v.size();++i)
                            {
				if ( v.elementAt(i) instanceof Alert )
                                    {
					update((Alert)v.elementAt(i));
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

