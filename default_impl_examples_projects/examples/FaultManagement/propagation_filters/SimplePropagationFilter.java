/*$Id: SimplePropagationFilter.java,v 1.2 2008/07/30 11:26:57 barathv Exp $*/
/**
 *  SimplePropagationFilter.java
**/ 

package com.adventnet.nms.example.propagationfilter;

import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.severity.SeverityInfo;



/**
 * This is an example propagation filter which explains
 * how propagation filters can be used to get the informations about alert that has been 
 * added/updated/deleted. This propagation filter will be notified 
 * by MiniAlert Alert. 
 * 
 * This propagation filter, identifies the operation performed on the Alert object
 * and the severity by which the ManagedObject is updated.
 *
 * This Alert filter action class can be used by renaming the file
 *                  <WebNMS>/conf/examples/propagationfilters/SimplePropagationFilter.filters
 * into             <WebNMS>/conf/propagation.filters
 * before starting the server.
 *
 * @since WebNMS 2.2
**/     
public class SimplePropagationFilter implements PropagationFilter
{
	
	/**
	 * Default Constructor
	 */
	public SimplePropagationFilter()
	{
	}

    /**
     * This method will be called by Web NMS only
     * after an managed object is updated according to the status
     * of the alert which has been added/updated/deleted.
     * MiniAlert has some of the properties of Alert by which ManagedObject is updated.
     */
   	public void applyPropagation(MiniAlert minialert)
	{
		TopoAPI api = com.adventnet.nms.topodb.DBServer.topodb; 
		ManagedObject mo = null;
		try
		{
			//to fetch the ManagedObject from database
			mo = api.checkOut(minialert.getSource(),0,false,true);
		}
		catch(Exception e)
		{
			System.err.println("SimplePropagationFilter:" + e);
		}
		
		// if source is not yet discovered
		if(mo == null)
		{
			System.err.println("An alert from an undiscovered object " + minialert.getSource() + " is updated");
			return;
		}
		
		// Status of the managed object
		int status = mo.getStatus();

		SeverityInfo severityInfo = SeverityInfo.getInstance();

		// if the minialert's severity is special purpose severity i.e. alert is deleted
		if(minialert.getSeverity() == severityInfo.getSpecialPurposeSeverity())
		{
			// if another alert for the entity is available
			if(minialert.getMaxOrLatestSeverityAgainstSource() !=  severityInfo.getSpecialPurposeSeverity())
			{
				System.err.println(minialert.getSource() + " is updated with the next appropriate alert");
			}
			// if no alert for the same entity is pending
			else
			{
				System.err.println(minialert.getSource() + " is cleared, because the alert is deleted and there is no alert pending");
			}	
		}
		// if the managed object is updated with this alert
 		else if(status == minialert.getSeverity())
		{
			System.err.println(minialert.getSource() + " updated");
		}
		// if the managed object is not updated with this alert, which will never happen
		else
		{
			System.err.println(minialert.getSource() + " not updated");
		}	
	}
}
