package com.adventnet.nms.example;

// Java Imports
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.event.*;
import java.util.Vector;
import java.util.*;
import java.util.Properties;
import javax.swing.border.*;
import java.awt.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.File;
import java.rmi.*;
import java.io.*;
import java.net.*;

//Server imports
import com.adventnet.nms.eventdb.EventAPI;
import com.adventnet.nms.eventdb.EventObserver;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.security.authentication.RMIAccessAPI;
import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.alertdb.AlertListener;
import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.alertdb.AlertActionInformer;
import com.adventnet.nms.util.XMLNode;
 

//Client Imports
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.util.NmsPanelEventListener;
import com.adventnet.nms.util.NmsPanelEvent;
import com.adventnet.nms.util.NmsClientUtil;

public class ExampleNMSPanel extends AbstractBaseNmsPanel
{
    JMenuBar menuBar;

    private EventAPI eapi=null;
    private AlertAPI aapi=null;
    
    AudioNotifier notifier = null;
    private String audioFile=null;

    TestEventObserver eventObserver = null;
    TestAlertListener alertLis = null;

    public ExampleNMSPanel()
    {
        
    }

    /** init method with Applet parameter */
    public void init(JApplet app)
    {

    }
	public void init(Properties prop)
	{
        parseData(prop);
		super.init(prop);
	}

    public String key()
    { 
        return "EXAMPLE_ID"; 
	}

    public void setProperties(Properties prop)
    {
            
       
    }

    /**
     * HOW TO GET CONFIGURED VALUES FROM THE CONF FILE :- 
     *
     * You can obtain the property values for the following property keys , which is configured in NmsPanels.conf
     *  1. EVENT_OBSERVER   - To decide whether to register as EvenObserver.
     *  2. ALERT_LISTENER   - To decide whether to register as AlertListener.
     *  3. EVENT_PROPERTIES - Determines the matching criteria for Alerts.
     *  4. ALERT_PROPERTIES - Determines the matching criteria for Events.
     *  5. AUDIO_FILE       - Name of the audio file to be played.( note : The audio file shouls be in .wav format.)   
     *
     *  For details regarding the configuration in NmsPanels.conf, please refer to the README.html provided with
     *  this example
     */

    private void parseData(Properties prop)
    {
        
        String eveObserver = prop.getProperty("EVENT_OBSERVER");
		String alertListener = prop.getProperty("ALERT_LISTENER");
        String eventProp = prop.getProperty("EVENT_PROPERTIES");
        String alertProp = prop.getProperty("ALERT_PROPERTIES");
		audioFile = prop.getProperty("AUDIO_FILE");
     
        //if eveObserver=true then, EventObserver will be register with the server for updates.
		if(eveObserver != null && eveObserver.equals("true"))
		{
            try
            {
                eventObserver = new TestEventObserver(NmsClientUtil.applet.getCodeBase().getHost() , formCriteria(eventProp));
            }
            catch(Exception e)
            {
                System.err.println("Exception in initializing TestEventObserver()" + e);
                e.printStackTrace();
            }
		}
        //if alertListener=true then, this example will be registered as an AlertListener with the server to get updates.
		if(alertListener != null && alertListener.equals("true"))
		{
			try
            {
                alertLis = new TestAlertListener(NmsClientUtil.applet.getCodeBase().getHost() , formCriteria(alertProp));
            }
            catch(Exception e)
            {
                System.err.println("Exception in initialising TestAlertObserver()" +e);
                e.printStackTrace();
            }
		}

    }

    //This method tokenize the ";" separated criteria string, and returns Properties objects with the property keys & values.
    private Properties formCriteria(String criteria)
    {
		Properties criteriaProp = new Properties();
        if(criteria == null)
        {
            return criteriaProp;
        }
        criteria = criteria.trim();
        StringTokenizer tokenizer = new StringTokenizer(criteria, ";");
        while(tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken().trim();
            int index = token.indexOf('=');
            if(index != -1)
            {
                String key = token.substring(0, index).trim();
                String value =token.substring(index+1).trim();
                
				if(key != null && value != null)
				{
					key = key.trim();
					value = value.trim();
					criteriaProp.put(key,value);
				}
            }
        }
        return criteriaProp;
	}

    // This method is to check whether the criteria properties and the properties obtained from the  updates are equal.
	boolean isPropertyMatch(Properties eveProp, Properties prop)
	{
		for(Enumeration en = prop.keys(); en.hasMoreElements();)
		{
			String key = (String)en.nextElement();
			Vector valueVec = formVector(prop.getProperty(key));
			String valueFromEvent = eveProp.getProperty(key);
			if(valueFromEvent == null || !valueVec.contains(valueFromEvent))
			{
				return false;
			}
		}
		
		return true;
	}
	
	private Vector formVector(String value)
	{
		Vector toReturn = new Vector();
		StringTokenizer tokenizer = new StringTokenizer(value, ",");
		while(tokenizer.hasMoreTokens())
		{
			toReturn.addElement(tokenizer.nextToken().trim());
		}
        return toReturn;
	}
    
    // EventObserver....
	class TestEventObserver extends UnicastRemoteObject implements EventObserver 
	{
        Properties prop = new Properties();
        public TestEventObserver() throws java.rmi.RemoteException
        {
        }

		public TestEventObserver(String hostName, Properties prop) throws Exception
		{
            getEventAPI(hostName);
            register();
			this.prop = prop;
        }
	
        // To obtain the EventAPI handle..
        public void getEventAPI(String hostName) throws java.rmi.RemoteException
        {
            try
            {
                String apiString = "//"+hostName+"/EventAPI";
                eapi =(EventAPI)Naming.lookup(apiString);
                System.out.println("EventAPI handle is successfully got. ");
            }
            catch(Exception e)
            {
                System.out.println("Exception occured while getting EventAPI handle" +e); 
            }
        }
        //After getting the EventAPI handle register with the server for updates.
        public void register() throws Exception
        {
            try
            {
                if(eapi.registerForEvents(this))
                {
                    
                    System.out.println("Successfully registered as EventObserver");
                }
                else
                {
                    System.out.println("Failed in registering as EventObserver");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        //Whenever an update is notified to observer and the given match property is equal then it will play a WAV file.
		public void update(Event eve)
		{
			if(isPropertyMatch(eve.getProperties(), prop))
			{
                playAudioFile();
			}
            System.out.println("Notification received for Event ");
		}
	}
    
    
	 
    //  AlertListener
    class TestAlertListener extends UnicastRemoteObject implements AlertListener
    {
        Properties prop = new Properties();
        public TestAlertListener() throws java.rmi.RemoteException
        {
        }
    
        public TestAlertListener(String hostName , Properties prop) throws Exception
		{
            getAlertAPI(hostName);
            register();
			this.prop = prop;
		}
        //To obtain the AlertAPI handle.
        public void getAlertAPI(String hostName) throws java.rmi.RemoteException
        {
            try
            {
                String apiString = "//"+hostName+"/AlertAPI";
                aapi =(AlertAPI)Naming.lookup(apiString);
                System.out.println("AlertAPI handle is successfully got. ");
            }
            catch(Exception e)
            {
                System.out.println("Exception occured while getting EventAPI handle" +e); 
            }
        }
        //Afetr getting the AlertAPI handle register with the server for updates.
        public void register() throws Exception
        {
            try
            {
                if(aapi.addAlertListener(this))
                {
                    System.out.println("Successfully registered as AlertListener");
                }
                else
                {
                    System.out.println("Failed in registering as AlertListener");
                }
            }
            catch(Exception e)
            {
                System.out.println("Error in registering as AlertListener ");
                e.printStackTrace();
            }    
     
        }

        public void update(XMLNode node)
        {
            System.out.println("Notification Received for Bulk Delete");
        }
        
        //Whenever an update is notified to observer and the given match property is equal then it will play a WAV file.
        public void listenAlert(AlertActionInformer actionInformer)
        {
            if(actionInformer.isBatchUpdate())
            {
                Vector vec=actionInformer.getAlertList();
                int size = vec.size();
                for(int i=0; i<size;i++)
                {
                    if(isPropertyMatch(((Alert)vec.elementAt(i)).getProperties(),prop))
				   	{
						playAudioFile();
					}
                }
            }
            else
            {
                if(isPropertyMatch(actionInformer.getAlert().getProperties(),prop))
                {
					playAudioFile();
				}
            }
            System.out.println("Notification Received for Alert operation");
        }
        
    }

    public void playAudioFile()
    {
        try
        {
            //Play...
            String param =NmsClientUtil.getClientType();
            String urlString=NmsClientUtil.applet.getDocumentBase().toString();
            URL audioURL = new URL(urlString+"../"+audioFile);
            notifier = new AudioNotifier(audioURL);
            notifier.play();
        }
        catch(java.security.AccessControlException ace)
        {
            ace.printStackTrace();
        }
        catch(java.net.MalformedURLException mue)
        {
            mue.printStackTrace();
        }
    }

    public void stop()
    {
        try
        {
            eapi.deregisterForEvents(eventObserver);
            aapi.removeAlertListener(alertLis);
        }
        catch(RemoteException ree)
        {
            System.out.println("Exception occured while deregistering. "+ree);
            ree.printStackTrace();
        }
    }
    
}//End of class ExampleNMSPanel









