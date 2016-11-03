//$Id: AlertFilterExample1.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/**
 *   AlertFilterExample1.java
 */

package com.adventnet.nms.example.alertfilter;


import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.topodb.*;
import java.util.*;

/**
 *  This filter action class explains the basic idea of 
 *  writing a filter action class. This class when added as event or alert
 *  filter action class, prints some messages about which method
 *  has been called.
 *
 * This Alert filter action class can be used by renaming the file
 *                  <Web NMS>/conf/examples/alertfilters/AlertFilterExample.filters
 * into             <Web NMS>/conf/alert.filters
 * before starting the server or by adding this filter action class dynamically.
 */
public class AlertFilterExample1 implements FilterClient {

	/**
	 * Default constructor
	**/
	public AlertFilterExample1() {
	}

	/** 
	 *	This method is called when the user filter action class is 
	 *	first instantiated. This method is defined in 
	 *  com.adventnet.nms.eventdb.FilterClient interface.
	**/
	public void init() {
		System.err.println("Inside AlertFilterExample1 init");
	}

    /**
 	* This is the filter method which should be implemented if this
    * class is registered as Event filter. This method will be invoked  
    * whenever an event  matches the Event Filter criteria.
    * The matching event object will passed as the parameter.  
    * We call this example only as alert filter by including the entry 
    * in alert filters alone. Hence this method will not be called.
    * If user includes this example as event filter this will be called.
    * This is the filter method called to filter each NetAlerts event. 
    * This method is defined in 
    <b>com.adventnet.nms.eventdb.FilterClient</b> interface.
    **/
	public com.adventnet.nms.eventdb.Event filter(com.adventnet.nms.eventdb.Event e) {
		System.err.println("Inside AlertFilterExample1 Event");
		return e;
  	}


    /**
 	* This is the filter method which should be implemented if this
    * class is registered as Alert filter. This method will be invoked  
    * whenever an alert matches the Alert Filter criteria.
    * The matching Alert object will passed as the parameter.  
    * We call this example only as alert filter by including the entry 
    * in alert filters alone. Hence this method will be called.
    * This method is defined in 
    <b>com.adventnet.nms.eventdb.FilterClient</b> interface.

    **/
	public com.adventnet.nms.alertdb.Alert filter(com.adventnet.nms.alertdb.Alert a) {
		System.err.println("Inside AlertFilterExample1 Alert");
		return a;
  	}

}
