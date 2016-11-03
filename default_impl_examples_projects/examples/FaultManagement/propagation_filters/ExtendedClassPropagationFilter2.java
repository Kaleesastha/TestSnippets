/**
 * ExtendedClassPropagationFilter2.java
**/

package com.adventnet.nms.example.propagationfilter;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.severity.*;

import com.adventnet.nms.fault.FaultException;


/**
 * This is an example propagation filter which explains
 * how we can use the EXTENDED-CLASS tag in conf/propagation.filter
 * to know whether the alert is added, updated or deleted.
 * This example file identifies whether the corresponding
 * Alert operation is an Alert insert or Alert update by checking out the Event from
 * database. Whenever Alert is updated through Events, corresponding Event's
 * 'id' will be assigned to the 'id' field of Alert. Using this 'id' field
 * Event is queried from database and by checking the 'createTime' of MiniAlert with
 * 'time' field of Alert, the Alert's operation is decided.
 * If both these time values are equal then that will be an new Alert addition.
 * If they are different then that operation will be an Alert Update.
 * To use this class file the EXTENDED-CLASS tag in conf/propagation.filters should be defined as
 *       <EXTENDED-CLASS className="test.ExtendedClass">
 *
 * This Alert filter action class can be used by renaming the file
 *                  <WebNMS>/conf/examples/propagationfilters/ExtendedClassPropagationFilter2.filters
 * into             <WebNMS>/conf/propagation.filters
 * before starting the server.
 *
 * @since WebNMS 2.2
**/
public class ExtendedClassPropagationFilter2 implements PropagationFilter
{
	/**
	 * Default constructor
	**/
	public ExtendedClassPropagationFilter2()
	{
	}

	/**
	 * This method will be called by Web NMS only
	 * after an managed object is updated according to the status
	 * of the alert which has been added/updated/deleted.
	 * @param minialert object by which Alert's operation can be identified.
	 */
	public void applyPropagation(MiniAlert minialert)
	{
		SeverityInfo severityInfo = SeverityInfo.getInstance();
		// If the severity of the minialert is special purpose severity
		// then the alert under consideration is deleted.
		if(minialert.getSeverity() == severityInfo.getSpecialPurposeSeverity())
		{
			System.err.println("Alert for the source " +
				minialert.getSource() + " is deleted");
			return ;
		}
		// Typecast the minialert to ExtendedClass which is
		// defined as the EXTENDED-CLASS in propagation.filter.
		ExtendedClass extendedClass = (ExtendedClass)minialert;
        EventAPI api = com.adventnet.nms.eventdb.EventMgr.eventapi;
		long eventTime = 0;
		try
		{
			// get the time at which the event, which is responsible for the
			// addition or updation of the alert under consideration,
			// is generated.
			eventTime = (api.getEventByID(extendedClass.getId())).getTime();
		}
		catch(Exception e)
		{

			if(e instanceof FaultException)
				System.err.println("FaultException occurred. " + e);

			System.err.println("ExtendedClassPropagationFilter2:" + e);
		}

		// If the event corresponding to the alert's generated time and
		// ExtendedClass's created time (Which is the created time of
		// corresponding alert) are equal then the alert under consideration
		// is added. Otherwise it is updated.
		if(extendedClass.getCreateTime() == eventTime)
		{
			System.err.println("Alert for the source " + 
				minialert.getSource() + " is added");
		}
		else
		{
			System.err.println("Alert for the source " + 
				minialert.getSource() + " is updated");
		}
	}
}
