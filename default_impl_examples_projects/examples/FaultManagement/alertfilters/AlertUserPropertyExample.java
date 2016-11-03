//$Id: AlertUserPropertyExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $

/**
 *   AlertUserPropertyExample.java
 */

package com.adventnet.nms.example.alertfilter;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.util.*;
import java.util.*;
import java.io.*;
  
/** 
 * In this example we will demonstrate how to set the  userProperties to the
 * Alert and the userProperties of  the alert  changes according to the
 * value of the ALERTS_USER_PROPERTY [none/latest/append] set in the
 * <Web NMS>/conf/NmsProcessesBE.conf.
 *
 * This Alert filter action class can be used by renaming the file
 * <Web NMS>/conf/examples/alertfilters/AlertUserProperyExample.filters
 * into <Web NMS>/conf/alert.filters before starting the server or by adding this filter action class dynamically.
 */
public class AlertUserPropertyExample implements FilterClient 
{
 
	public AlertUserPropertyExample()
	{
    
	}

	/**
	 *  This is called when the user filter action class is first instantiated **/
	public void init() {

	}

    /**
 	* This is the filter method which should be implemented if this
    * class is registered as Event filter. This method will be invoked  
    * whenever an event  matches the Event Filter criteria.
    * The matching event object will passed as the parameter.  
    * We do nothing in this method as we intend to use this class only as a Alert Filter.  
    **/
	public com.adventnet.nms.eventdb.Event filter(com.adventnet.nms.eventdb.Event e) {
		    
		return e;
    
	}


    /**
     * This is the method that will be invoked whenever an Alert matching
     * the Alert Filter criteria comes into NMS.In our example
     * it will be invoked whenever an alert gets added(or Updated)into NMS
     * The first Event ID of the alert is set as  UserProperty for
	 *  the Alert i.e. with the value of the id of the event which created
     *  this alert. 
     *  If the first Event which created the alert is not in the database
     *  user property, EventID will be set to "" for the Alert.
	 */
	public com.adventnet.nms.alertdb.Alert filter(com.adventnet.nms.alertdb.Alert a) 
	{
		if(a.getUserProperty("FirstEventId")==null)
			{
				Vector v=null;
				InputEvent e= new InputEvent();
				e.setEntity(a.getEntity());
				e.setTime(a.getCreateTime()-1);
				a.setUserProperty("FirstEventId", "");
				try
					{
						v =  EventMgr.eventapi.getEvents(e); 
					}
				catch(java.rmi.RemoteException ex)
					{			  
						System.err.println("Remote Exception in retrieving the events ");			  
					}								

				catch(com.adventnet.nms.fault.FaultException fe)
					{
						System.err.println("FaultException occurred. " + fe.toString());
					}

					if(v==null || v.size() == 0)
					{					
						return a;
					}
		  
				int id = -1;
				for(int i=0;i<v.size();i++)
					{
						Event temp = (Event)v.elementAt(i);
                        /** Alert's CreateTime will be set once when
                         * New alert is created due to event and it 
                         * will be of Event's Time which created the Alert.
                         * Even during Alert updation it will
                         * remain constant and alerts modTime will be set
                         * from Event's Time. Hence createTime of alert 
                         * is checked with Event's time to find Event 
                         * which created the alert.
                         */
						if(temp.getTime() == a.getCreateTime())
							{
								id = temp.getId();
								break;
							}
					}
				if(id != -1)
					{
						a.setUserProperty("FirstEventId",String.valueOf(id));				
					}		  
			}
		return a;
	} 

}


