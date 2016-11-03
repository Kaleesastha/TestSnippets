//$Id: AlertAgingExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/**
 * AlertAgingExample.java
 */ 
package com.adventnet.nms.example.alertfilter;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Date;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.severity.SeverityInfo;

import com.adventnet.nms.fault.FaultException;


/**
  * This is an example filter class that implements the functionality of
  * aging over Alerts. The method "filter(Alert)" in this class will be invoked
  * for every alert that is generated within NMS. The filter  
  * class implements the aging mechansim for all the alert that   
  * are received by it. The alert aging duration is set to 120 seconds.  
  * The alerts with the same failure Object (entity) value are aged together.
  * The user can change it to the field based on which he/she requires to 
  * age them eg. the alerts from the same source etc.,   
  * 
  * This class schedules itself to be called by the NMS Main scheduler
  * every 1 second (this can be changed accordingly). The NMS Scheduler calls the run() method 
  * of this class whenever the scheduled duration expires. The NMS Main Scheduler
  * needs the class to implement the java.lang.Runnable interface for it
  * to schedule the class.
  * 
  *
  * If this class is to be used as an Alert filter, the command line
  * parameter ALERTS_USER_PROPERTY of EventMgr in 
  * NmsProcessesBE.conf file in conf directory
  * should be set as latest or append. Then only the user properties set for
  * the events will be carried to alerts. Note that the user property AgeFlag
  * is used to check whether the alert is already aged or not. So only if the
  * properties of the events can be copied to alerts, this example will work.
  * Otherwise, the alerts won't be updated forever.
  *
  * Note that this alert filter is written to handle all type of events
  * from a particular entity. So alert filter criteria for severity for this example
  * should be All.
  *
  * This Alert filter action class can be used by renaming the file
  * <Web NMS>/conf/examples/alertfilters/AlertAgingExample.filters
  * in to  <Web NMS>/conf/alert.filters
  * before starting the server or adding this filter action class dynamically.
  */
public class AlertAgingExample implements FilterClient, Runnable
{    
	/**
     * This Hashtable will hold the alerts that are aged. The key  will
     * be the entity (or failure Object) of the received alert and the value 
     * will be an instance of AgeObject, which will encapsulate the information 
     * about the alert and properties associated with aging.
     */    
	 Hashtable pendingAlerts;
    
    /**
     * This vector will hold the instance of the AgeObject in the time order (Ascending)
     * in which they are to be scheduled (or processed for aging). 
     */    
	 Vector    schedulerVector;                                               
     
    /**
     * This is an empty (default) constructor which is needed to instantiate
     * the class by Web NMS.
     */
    public AlertAgingExample()
    {
        //Intialization job
        pendingAlerts = new Hashtable(10);        
        schedulerVector = new Vector(10,10);                
        schedule();
    }
    
    /**      
	 * This is called by NMS when the user filter action class is first instantiated
	 * by Web NMS.
	 */
    public void init() 
    {
    }        
    /**
     * This method will be called by NMS main scheduler every 1 second. This method 
     * will be the one which does the aging process on the pending alerts.
     */    
    public void run()
    {
        try
        {
            if(schedulerVector.size() > 0)
            {            
                AgeObject ageObject = (AgeObject) schedulerVector.elementAt(0);
				while(ageObject.getScheduleTime() <= System.currentTimeMillis())
				{
					schedulerVector.removeElementAt(0);
					pendingAlerts.remove(ageObject.getEntity());
					generateAlert(ageObject);       
					if(schedulerVector.size() == 0)
						break;                    
					ageObject = (AgeObject) schedulerVector.elementAt(0);
				}
            }
        }
        catch(Exception ex)
        {
            System.err.println("AlertAgingExample: Exception in the run() method ");
            ex.printStackTrace();
        }
        schedule();
    }

    /**
     * This method generates an Event which is pending after its  
     * aging period has expired. The method takes the AgeObject as the 
     * parameter and generates the Event from the information in it. 
     * The generated Event will be added into NMS using the FaultMgr addEvent()
     * method. The generated Event will have 2 User property namely
     * "ChatterCount" and "RepeatCount" added to it which 
     * has the value of the chatter count and repeat count for the 
     * alerts with the same "entity"(or failure object) generated during the aging period.
     * ChatterCount means : No of Clear events with same entity.
     * RepeatCount means : No of Events with same entity.

     */

	 private void generateAlert(AgeObject ageObject)
	 {
	 	EventAPI eventAPI = com.adventnet.nms.eventdb.EventMgr.eventapi;
		FaultMgr fMgr = com.adventnet.nms.eventdb.EventMgr.getFaultMgr();
		if(eventAPI != null && fMgr != null)
		{            
			Vector eventsVector = null;
			InputEvent event1 = new InputEvent();
			event1.setEntity(ageObject.entity);
			event1.setTime(ageObject.getCreatedTime() - 1);
			try
			{
                eventsVector = eventAPI.getEvents(event1);
			}
            catch(Exception e)
            {
                System.err.println("AlertAgingExample: Exception thrown while using EventAPI:" + e.getMessage());
			}
			if(eventsVector == null)
			{
                System.err.println("From AlertAgingExample.java -> Events == NULL; This should not occur");
				return;
			}
			if(eventsVector.size() == 0)
			{
				return;
			}
			Event event = (Event)eventsVector.lastElement();
			if(event == null)
			{
                System.err.println("From AlertAgingExample.java -> Event == NULL; This should not occur");
			}
			int chatterCount = 0;
			int repeatCount = 0;
			boolean isPreviousEventClear = false;
			SeverityInfo severityInfo = SeverityInfo.getInstance();
			for(int i=0; i < eventsVector.size(); i++)
			{
				++repeatCount;
				try
				{
					if(!isPreviousEventClear && severityInfo.isClear(((Event)eventsVector.elementAt(i)).getSeverity()))
					{
						++chatterCount;
					}
				}
				catch(NullPointerException ne)
				{
					System.err.println("AlertAgingExample: Event == Null; This should not occur" + ne);
				}
			}
			event.setUserProperty("ChatterCount", String.valueOf(chatterCount));
			event.setUserProperty("RepeatCount", String.valueOf(repeatCount));
			
            /**
			 * We add this user property to say that this alert has been aged already.
			 * and hence do not age it when it comes back into this
			 * filter. The User should note that the event that is getting
			 * added through the FaulMgr addEvent() method will get converted
			 * into an Alert and again pass through the Alert Filter Level
			 * hence we add this Flag "AgeFlag" to avoid repeated aging.
			 */
			
            event.setUserProperty("AgeFlag","true");                
			
			//Adding the event back into the Fault Manager
			fMgr.addEvent(event); 

		}
	}    

    /**
     * This is the method that will be invoked whenever an Alert matching
     * the Alert Filter criteria comes into NMS. In our example
     * it will be invoked whenever an alert gets added(or Updated) into NMS 
     * The method checks whether the incoming alert has an attribute 
	 * "AgeFlag" if so it returns the alert. Its because an alert would contain  
     * the property "AgeFlag" only if it had already being aged by 
     * this filter, hence we do not age them again. 
     * 
     * For all other alerts we return "null" so that they are not 
     * added into NMS directly, so that they are aged. They will be added back by
     * this class after aging them.
     * 
     * @param  alt The incoming Alert matching the Alert Filter criteria
     * @return Returns the incoming Alert object if it contains the 
     *         user property named "AgeFlag" else returns "null".  
     */
	public com.adventnet.nms.alertdb.Alert filter(com.adventnet.nms.alertdb.Alert alt)  
	{
		//Check whether the incoming alert has been already aged if so 
		//return it, else create or update the existing AgeObject and  
		//return "null".
		if(alt.getUserProperty("AgeFlag") != null)
		{
			alt.removeUserProperty("AgeFlag");
			return alt; 
		}        

        AgeObject ageObject = (AgeObject)pendingAlerts.get(alt.getEntity());
		if(ageObject == null) 
		{ 
			//Creating a AgeObject with 120 seconds as aging time

            try
			{
				ageObject = new AgeObject(alt,120);
			}
			catch(FaultException exc)
			{
				System.err.println("Fault Exception occurred." + exc.toString());
			}



			pendingAlerts.put(alt.getEntity(),ageObject);
			schedulerVector.addElement(ageObject);
		}
		return null;
    }

    /**
 	* This is the filter method which should be implemented if this
    * class is registered as Event filter. This method will be invoked  
    * whenever an event  matches the Event Filter criteria.
    * The matching event object will passed as the parameter.  
    * We do nothing in this method as we intend to use this class only
    * as a Alert Filter.  
    **/
    public com.adventnet.nms.eventdb.Event filter(com.adventnet.nms.eventdb.Event e)
	{
		return e;
    }     
    
	/**
     * This method schedules this class run() method to be called every 1000 milliseconds
     * by the NMS Main scheduler.
     */   
    private void schedule() 
    {
        Date d = new Date();
        d = new Date(d.getTime() + 1000);
        //We schedule this instanace of the class with the time
        //at which the run() method of this instance to be called next.
        //Here we are scheduling for every 1 second which could be changed
        NmsUtil.scheduler.scheduleTask(this,d);
    }
}

/**
 * This inner class will encapsulate the aging information of alerts with the 
 * same "entity" (or failure object). The class will maintain the 
 * chatter count and repeat count and also the time after which its 
 * aging period will expire.
 */
 class AgeObject
 {
	/**
     * This variable will hold the the name of the entity.
     */
    String entity;
	/**
	 * This variable will hold the created time of the event 
	 * which added this entity into this aging process.
	 */
	 private long createdTimeInMilli;
    /**
     * This variable will hold the time at which the aging period 
     * for the alert of the same entity expires.
     */
	 private long timeForProcessingInMilli;
	/**
     * Default constructor.
     */
	 public AgeObject()   
	 {
        timeForProcessingInMilli = System.currentTimeMillis();
		entity = "";
		createdTimeInMilli = 0;
	}
    /**
     * This constructor will be used by the main Filter class above to create
     * an instance. The constructor will take the first incoming alert object
     * of an entity and the time for which the alerts of the same entity
     * should be aged (in seconds).
     */

	public AgeObject(Alert alt, long waitTimeInSeconds) throws FaultException



    {
	 	EventAPI eventAPI = com.adventnet.nms.eventdb.EventMgr.eventapi;
		if(eventAPI != null)
		{
			try
			{
				createdTimeInMilli = eventAPI.getEventByID(alt.getId()).getTime();
			}
			catch(Exception e)
			{
				System.err.println("AlertAgingExample: Exception occured" + e);
				e.printStackTrace();
			}
			//Calculate the time at which the aging period for the alerts 
			//under this group expires.  
			timeForProcessingInMilli = createdTimeInMilli + (waitTimeInSeconds * 1000);
			entity = alt.getEntity();
		}
    }    
   
    /**
     * This method returns the time at which the aging period expires
     * for this age object.
     */ 
     public long getScheduleTime()
     {
         return(timeForProcessingInMilli);
     }
    /**
	 * This method returns the created time of the Event which added this entity
	 * into this aging process.
	 */
	 public long getCreatedTime()
	 {
	 	return(createdTimeInMilli);
	 }
    /**
     * Returns the "entity"(or failure object) of the alerts represented
     * by this age object instance. It should be remembered that
     * the alerts are matched based on the same "entity" (or failure object) 
     */
    public String getEntity()
    {
        return entity;
    }
}


