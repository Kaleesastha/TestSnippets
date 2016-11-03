/*$Id: ExtendedClassPropagationFilter.java,v 1.2 2008/07/30 11:25:33 barathv Exp $*/
/**
 * ExtendedClassPropagationFilter.java
**/

package com.adventnet.nms.example.propagationfilter;

import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.severity.SeverityInfo;

/**
 * This is an example propagation filter which explains 
 * how we can use the EXTENDED-CLASS tag in conf/propagation.filter
 * to get the properties of the alert which has been added/updated/deleted 
 * and which are not in MiniAlert. 
 * This propagation filter identifies the operation
 * performed on Alert and the severity by which ManagedObject is updated.
 *
 * This Alert filter action class can be used by renaming the file
 *                  <WebNMS>/conf/examples/propagationfilters/ExtendedClassPropagationFilter.filters
 * into             <WebNMS>/conf/propagation.filters
 * before starting the server.
 *
 * @since WebNMS 2.2
**/
public class ExtendedClassPropagationFilter implements PropagationFilter
{
	/**
	 * Default constructor
	**/
	public ExtendedClassPropagationFilter()
   	{
	}

	/**
	 * This method will be called by Web NMS only 
	 * after an managed object is updated according to the status 
	 * of the alert which has been added/updated/deleted.
	 * @param minialert Object by which Alert operation can be identified.	
	**/
	public void applyPropagation(MiniAlert minialert)
	{
		TopoAPI api = com.adventnet.nms.topodb.DBServer.topodb; 
		ManagedObject mo = null;
		// Type cast the ExtendedClass
		ExtendedClass extendedClass = (ExtendedClass)minialert;
		try
		{
			mo = api.checkOut(extendedClass.getSource(),0,false,true);
		}
		catch(Exception e)
		{
			System.err.println("ExtendedPropagationFilter:" + e);
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
		// if the managed object is not updated with this alert which will never happen
		else
		{
			System.err.println(minialert.getSource() + " not updated");
		}	
	}
}
