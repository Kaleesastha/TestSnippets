//$Id: EventLoggingPolicy.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $

package com.adventnet.nms.policies;


import java.io.*;
import java.util.*;

import java.text.SimpleDateFormat;
import com.adventnet.management.policydb.PolicyEvent;
import com.adventnet.management.policydb.NmsPolicyAPI;
import com.adventnet.management.policydb.PeriodicPolicyObject;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil ;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.eventdb.EventAPI;
import com.adventnet.nms.eventdb.InputEvent;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.eventdb.Event;

import com.adventnet.nms.fault.FaultException;



import java.rmi.Naming;

/**
	 This policy creates a new file under the <WebNMSHome> directory which contains the Events for that specified period
	 */
public class EventLoggingPolicy extends PeriodicPolicyObject 
{

	//The time in which the last file is created
	private long lastTime;
   	
   	//The id of the last Event in the previous file
    private int lastId;


    /** Set the default period to one day in the constructor. **/
    public EventLoggingPolicy()
    {
		super();
		policyObjectCustomizer = "com.adventnet.nms.policies.EventPolicyCustomizer"; 
        period = 24*3600;
        setUserProperty("Events_Directory","event");
    }

    /**
     * Sets the properties for this policy object
     * @param p the property to be set.
     */
    public void setProperties(Properties p)
    {
		String temp = (String)p.remove("Events_Directory");
		//instance variables are saved in DB as user properties
		if( temp != null )
		{
           setUserProperty("Events_Directory",temp.trim());
		}
        temp = (String)p.remove("lastId");
        if( temp != null )
        {
            setUserProperty("lastId",temp.trim());
        }
        temp =(String)p.remove("lastTime");
        if ( temp != null )
        {
            setUserProperty("lastTime",temp.trim());
        }
		super.setBaseProperties(p);
    }

    /**
     * Returns the properties of this policy object
     * @return "EventLoggingPolicy properties"
     */
    public Properties getProperties()
    {
        
        Properties p = super.getBaseProperties();
        return p;
    }
    
    /**
     * Returns the help url of this policy object. As, the default customizer associated
     * with this policy uses the help url directly from the help.conf, this method
     * returns null.
     */
    public String getHelpURL()
    {
        return null;
    }

    //this method adds the missing Events if any to the vector. 

    private Vector checkForId(Vector eventVector) throws java.rmi.RemoteException, FaultException



    {
		EventAPI eventAPI=null;
		eventAPI= (EventAPI)NmsUtil.getAPI("EventAPI");
		if(eventAPI == null)
        {
            System.err.println(" EventAPI is null in EventPolicy");
            return null;
        }
    	int firstId=((Event)eventVector.firstElement()).getId();
    	int position=0;
    	if((lastId+1)!=firstId)
    	{
    		for(int i=lastId+1;i<firstId;i++)
    		{
				Event ev = null;

				try
				{			
    				ev = eventAPI.getEventByID(i);
				}
				catch(FaultException fe)
				{	
					System.err.println("Exception while getting the event. ");
					throw fe;
				}	



					if(ev != null)
					{
						eventVector.add(position, ev);
						position++;
					}
    		}
    	}
    	return eventVector;	
    }

    //This method gets the Events from the Database through RMI
    private Vector getEvents(Date now) throws java.rmi.RemoteException
    {
		EventAPI eventAPI=null;
	    Vector eventVector=null;
    	eventAPI= (EventAPI)NmsUtil.getAPI("EventAPI");
    	if(eventAPI == null)
        {
            System.err.println(" EventAPI is null in EventPolicy");
            return null;
        }
        InputEvent event1 = new InputEvent();
        InputEvent event2 = new InputEvent();
        event1.setTime(lastTime);
        event2.setTime(now.getTime());

        try
            {
                eventVector = eventAPI.getEvents(event1,event2);
            }
        catch(FaultException fe)
            {
                fe.printStackTrace();
            }



        lastTime=now.getTime();
        return eventVector;
    }

    /**
     * Returns the customizer of this Policy Object
     * @return "Policy customizer name"
     */
    public String getPolicyObjectCustomizer()
    {
		//EventLoggingPolicy has EventPolicyCustomizer
		return policyObjectCustomizer;
    }

    /**
     * executes action of this policy object.
     * @param policyEvt PolicyEvent
     */

	public void executeAction(PolicyEvent policyEvt)
	{
        try
        {
        
			//default directory is event
			String directory = "event";
            //Properties got from API and from that properties instance variables are assigned
			NmsPolicyAPI api = (NmsPolicyAPI)NmsUtil.getAPI("NmsPolicyAPI");
            Properties tempProp = api.getPolicy(getName()).getProperties(); 
            String temp =(String)tempProp.get("Events_Directory");
            if(temp !=null)
            {
                directory = temp;
            }
            temp = (String)tempProp.get("lastId");
            if(temp != null)
            {
                lastId = Integer.parseInt(temp);
            }
            temp = (String)tempProp.get("lastTime");
            if(temp != null)
            {
                lastTime = Long.parseLong(temp);
            }
            temp = (String)tempProp.get("period");
            if(temp != null)
            {
                period = Integer.parseInt(temp);
            }
        
			Date now = new Date();
        	SimpleDateFormat formatter = new SimpleDateFormat ("MM-dd-yy_H-mm-ss");
        	String timestamp = formatter.format(now);	
            File dir = new File(PureUtils.rootDir+directory);
        	Vector eventVector=getEvents(now);
            if(eventVector != null && eventVector.size()!=0)	
            {	

				try
				{            	
					eventVector=checkForId(eventVector);
				}
				catch(FaultException fe)
				{
					fe.printStackTrace();	
				}	



				if (!dir.exists()) dir.mkdirs();
           		FileOutputStream fileout =new FileOutputStream(directory+"/"+"Events_"+timestamp);
            	PrintWriter out = new PrintWriter(fileout);
               	out.println("*****************This file contains Events with ID,"+((Event)eventVector.firstElement()).getId()+" To "+ ((Event)eventVector.lastElement()).getId()+"*******************\n");
            	for (Enumeration en=eventVector.elements();en.hasMoreElements();)
				{
               		Event e=(Event)en.nextElement();
               		out.print("ID="+e.getId()+";\t"+"Source="+e.getSource()+";\t"+"entity="+e.getEntity()+";\t"+"Severity="+SeverityInfo.getInstance().getName(e.getSeverity())+";\t"+"Time="+new Date(e.getTime())+";\t"+"Message="+e.getText()+";\t"+"Category="+e.getCategory()+";\t"+"Node="+e.getNode()+"\n");
               	}
				lastId=((Event)eventVector.lastElement()).getId();
				out.flush();
        		fileout.close();
                
            }
            //setting the modified instance variables as userProperties of policy and updating the policy
			setUserProperty("Events_Directory",directory);
            setUserProperty("lastId",String.valueOf(lastId));
            setUserProperty("lastTime",String.valueOf(lastTime));
            api.updatePolicy(name,getProperties());
		}
        catch(Exception e)	
        {
        	NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Error in execution of EventLoggingPolicy")+e,e);
        }
	
    }

}




