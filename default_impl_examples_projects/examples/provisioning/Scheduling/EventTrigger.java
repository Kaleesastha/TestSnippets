//$Id: EventTrigger.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.provisioning.server;

import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.util.*;

import java.util.*;
import java.io.*;

/**
 * This class provides a default implementation for handling Adventnet WebNMS Events.<br>
 * This also implements update method of EventObserver to call the trigger 
 * method of ProvisioningTrigger(super class).This class also registers and 
 * deregisters the EventObserver. This needs criteria for filtering the incoming events.
 * This is obtained from the value of triggerCriteria attribute in the
 * Template that initiates this trigger. Its value should be a relative file 
 * from &lt;WebNMS Home&gt; directory.This file should contain an XML tag in 
 * following format.<br> 
 * &lt;?xml version="1.0" encoding="ISO-8859-1"?&gt;<br> 
 *
 * &lt;?CRITERIA  userKey1="&lt;userValue1&gt;" [userKeyN="&lt;userValueN&gt;" ...] /&gt;
 * <br>
 * @see com.adventnet.nms.eventdb.EventAPI
 * @see com.adventnet.nms.eventdb.EventObserver
 */

public class EventTrigger extends ProvisioningTrigger implements EventObserver,Serializable
{
	/** This holds an instance of EventAPI obtained with NmsUtil.getAPI(String) method.*/
	EventAPI eventAPI;
	/**
	 * Holds key and value pair of criteria for filtering.
	 * Used by the update(Event) method.
	 */
	private Hashtable criteriaHashtable;
	/**
	 *	Instantiates EventAPI variable.
	 *	EventAPI (eventAPI) instance is obtained with NmsUtil.getAPI(String) method.
	 */
	public EventTrigger() throws TriggerException
	{
		try
		{
			eventAPI = (EventAPI) NmsUtil.getAPI("EventAPI");
		}
		catch(Exception e)
		{
			String errorMessage = e.getMessage() != null ? e.getMessage() :e.toString();
			throw new TriggerException(errorMessage);
		}
	}
	/**
	 * Obtains criteria for filtering the incoming events and registers the
	 * EventObserver instance to the TopoAPI to receive triggers. 
	 * XMLDataReader instance is created to read an XML file specified with the value of 
	 * triggerCriteria attribute. This should be a relative filepath from 
	 * &lt;WebNMS Home&gt; directory. Using that instance,an XMLNode instance is created.
	 * With this XMLNode instance an instance of Hashtable to hold criteria for filtering
	 * is created.If this Hashtable is not null then EventObserver instance is registered.
	 * <br> 
	 */
	public void register() throws TriggerException
	{
		String triggerCriteriaAttribute=getTemplate().getAttribute("triggerCriteria");
		if    (triggerCriteriaAttribute !=null && triggerCriteriaAttribute.length() !=0)
		{
			try
			{
				XMLDataReader xdr= new XMLDataReader(PureUtils.rootDir+"/"+triggerCriteriaAttribute,false);
				XMLNode xml= xdr.getRootNode();
				if (!(xml.getNodeName().equals("CRITERIA")))
				{
					throw new TriggerException("Not a valid criteria tag");
				}
				criteriaHashtable=xml.getAttributeList();
			}
			catch (Exception e)
			{
				throw new TriggerException("Failed while reading the criteria file "+e.getMessage());
			}
		}
		else
		{
			throw new TriggerException("No triggerCriteria atribute specified");
		}
		try
		{
			eventAPI.registerForEvents(this);
		}
		catch(java.rmi.RemoteException e)
		{
			throw new TriggerException("Trigger cannot be registered "+e.getMessage());
		}
	}

	/**
	 * Registers the EventObserver instance to the TopoAPI to stop listening to the triggers.
	 */
	public void deregister() throws TriggerException
	{
		try
		{
			eventAPI.deregisterForEvents(this);
		}
		catch(java.rmi.RemoteException e)
		{
			throw new TriggerException("Trigger cannot be deregistered "+e.getMessage());
		}
	}

	/**
	 * Triggers the provisioning operation.
	 * This method is called from the Adventnet WebNMS Event module 
	 * when any network impulse arrives.
	 */
	public void update(Event e)
	{

		if (criteriaHashtable==null)
		{
			return;
		}
		Enumeration e1=criteriaHashtable.keys();
		Properties p=new Properties();
		Properties p1=e.getProperties();
		Properties p2=e.getUserProperties();
		if ((p1==null)&&(p2==null))
		{
			return ;
		}
		if (p1!=null)
		{
			p.putAll(p1);
		}
		if (p2!=null)
		{
			p.putAll(p2);
		}
		while(e1.hasMoreElements())
		{
			String s=e1.nextElement().toString();
			if (p.getProperty(s)==null)
			{
				return ;
			}
			else if (!(NmsUtil.checkString(p.getProperty(s).toString(),criteriaHashtable.get(s).toString())))
			{
				return ;
			}
		}
		{
			try
			{
				trigger(null);
			}
			catch(TriggerException t)
			{
				t.printStackTrace();
			}
		}
	}
}
