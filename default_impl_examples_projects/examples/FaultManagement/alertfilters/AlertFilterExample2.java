//$Id: AlertFilterExample2.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/**
 *   AlertFilterExample2.java
 */

package com.adventnet.nms.example.alertfilter;


import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.topodb.*;
import java.util.*;

/**
 *  This filter action class explains how user can get some information 
 *  and also explains how they can change 
 *  some properties of events and alerts that are received by using
 *  event and alert filter action classes.
 *
 * This Alert filter action class can be used by renaming the file
 *                  <Web NMS>/conf/examples/alertfilters/AlertFilterExample2.filters
 * into             <Web NMS>/conf/alert.filters
 * before starting the server or by adding this filter action class dynamically.
 */
public class AlertFilterExample2 implements FilterClient {

	public AlertFilterExample2() {
	}

	/** 
	 * This is called when the user filter action class is first instantiated.
	 * This method is defined in <b>com.adventnet.nms.eventdb.FilterClient</b> interface.
	**/
	public void init() {
  	}

	    /**
 	* This is the filter method which should be implemented if this
    * class is registered as Event filter. This method will be invoked  
    * whenever an event  matches the Event Filter criteria.
    * The matching event object will passed as the parameter.  
    * We call this example only as alert filter by including the entry 
    * in alert filters alone. Hence this method will not be called.
    * If user includes this example as event filter this will be called.
    * This method prints some of the properties of events.
    * This also changes the descriptive text of the event.
    * This method is defined in 
    <b>com.adventnet.nms.eventdb.FilterClient</b> interface.
    **/
	public com.adventnet.nms.eventdb.Event filter(com.adventnet.nms.eventdb.Event e) {
    	System.err.println("Filtered Event: "+
						e.getEntity() + " " + e.getTime() + " " + e);
						
		e.setText("This message has been changed in the " +
						"AlertFilterExample2 filter action class." + e.getText());
						
		return e;
	}  // End of filter(Event) method

    /**
 	* This is the filter method which should be implemented if this
    * class is registered as Alert filter. This method will be invoked  
    * whenever an alert matches the Alert Filter criteria.
    * The matching Alert object will passed as the parameter.  
    * We call this example only as alert filter by including the entry 
    * in alert filters. Hence this method will be called.
    * This method prints some of the properties of events.
    * This also changes the group name of the alerts to "MyGroup".
    * This method is defined in 
    <b>com.adventnet.nms.eventdb.FilterClient</b> interface.
    **/
	public com.adventnet.nms.alertdb.Alert filter(com.adventnet.nms.alertdb.Alert a) 
    {
		System.err.println("Filtered Alert: "+ a.getSeverity() + " " +
						a.getEntity() + " " + a.getModTime() + " " + a);
        a.setGroupName("MyGroup");
		return a;
	}  // End of filter(Alert) method

} // End of class AlertFilterExample2



