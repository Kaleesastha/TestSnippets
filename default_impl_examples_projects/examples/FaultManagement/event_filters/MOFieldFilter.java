//$Id: MOFieldFilter.java,v 1.2 2008/07/30 11:03:31 barathv Exp $
package com.adventnet.nms.example.fault.filter;

import java.util.Properties;
import java.rmi.*;

import com.adventnet.nms.eventdb.Event; 
import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.TopoObject;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.eventdb.*;

/* This example basically aids in creating a custom view scope based on
 * the IP Addresses of the network. Since IP Address will not be present
 * in the incoming Event/Alert by default, the 'source' of the Event/Alert
 * is first got, with the help of which the corresponding Managed Object
 * is got. From the Managed Object, the corresponding IP Address is 
 * obtained. The IP Address thus obtained, is set as value to one of the
 * unused fields of Event/Alert. In Event, it is set to network field and
 * in Alert, it is set to mapName field. 
 */
public class MOFieldFilter implements FilterClient
{ 
    /**
     * Properties to have the mapping 
     * between the source and IpAddress of the event
     */
    private Properties prop;
    /**
      TopoAPI instance 
    */
    private TopoAPI topoAPI;
    //default Constructor.
    public MOFieldFilter()
    {
    
    }

    /* Init method */
    public void init()
    {
        prop = new Properties();
        topoAPI = getTopoAPI();
    }

    /**
 	* This is the filter method which should be implemented if this
    * class is registered as Event filter. This method will be invoked  
    * whenever an event  matches the Event Filter criteria.
    * The matching event object will passed as the parameter.  
    * <u>This method gets ipAddress of the source for which  Event is</u>
    * <u>generated and sets that ipAddress in network field of event.</u>
    * This method is defined in 
    <b>com.adventnet.nms.eventdb.FilterClient</b> interface.
    **/
    public Event filter(Event e)
	{
        if(topoAPI == null)
            topoAPI = getTopoAPI();
        if(topoAPI == null)
            return e;
        try
        {
            String source = e.getSource();
            if(source == null)
            {
                return e;
            }
            String ipAddress = null;
            /** Gets ManagedObject correponding to the Event's Source
             *  to resolve IP of Event's source.
             */
            if(prop.get(source) == null)
            {
                ManagedObject mo = topoAPI.checkOut(source,0,false,true);
                if(mo == null)
                {
                    return e;
                }
                if(mo instanceof TopoObject)
                {
                    ipAddress = ((TopoObject)mo).getIpAddress();
                    prop.put(source,ipAddress);
                }
            }
            else
            {
                ipAddress = (String) prop.get(source);
            }
            if(ipAddress != null)
                e.setNetwork(ipAddress);
            return e;
        }
        catch (Exception ee)
        {
            ee.printStackTrace();
        }
        return e;
    }

    /**
 	* This is the filter method which should be implemented if this
    * class is registered as Alert filter. This method will be invoked  
    * whenever an alert matches the Alert Filter criteria.
    * The matching Alert object will passed as the parameter.  
    * <u>This method gets network field of Event which has ipAddress of</u>
    * <u>Event's source. And set that ipAddress as mapName in alert.</u>
    * This method is defined in 
    <b>com.adventnet.nms.eventdb.FilterClient</b> interface.
    **/
    public Alert filter(Alert a)
    {
        try
        {
            Event e = a.getEvent();
            if(e == null)
                return a;
            String strNetwork = e.getNetwork();
            if(strNetwork != null)
                a.setMapname(e.getNetwork());
            return a;
        }
        catch (Exception aa)
        {
            aa.printStackTrace();
        }
        return a;
    }

    // To get the TopoAPI handle
    private TopoAPI getTopoAPI()
    {
        return (TopoAPI) NmsUtil.getAPI("TopoAPI");
    }

} 
