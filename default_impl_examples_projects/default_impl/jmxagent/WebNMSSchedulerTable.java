//$Id: WebNMSSchedulerTable.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
/* Copyright (c)  1996 - 2004  Adventnet, Inc. All Rights Reserved.
 * PLEASE READ THE ASSOCIATED COPYRIGHTS FILE FOR MORE DETAILS.
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.
 * ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.
 */

/**
 * @Version :  6.0.0 Wed Feb 21 22:49:54 IST 2007
 * @Author  :  AdventNet Agent Toolkit Java Edition
 */

// Any changes made to this file will be lost, if regenerated.
// User code should be added within user tags for code merging support, if regenerated.



// Package Name (Dont Delete this comment)
package com.adventnet.nms.jmxagent ;

import javax.management.*;
import javax.management.modelmbean.*;
import javax.jmx.openmbean.*;

import com.adventnet.utils.jmx.*;
import com.adventnet.utilities.common.*;
import com.adventnet.utils.agent.utils;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;
	// User code starts here
import com.adventnet.management.scheduler.Scheduler;
	// User code ends here




/**
 * Handles all requests under
 * webNMSSchedulerTable group
 */


public class WebNMSSchedulerTable{

	private AdventNet_WebNMS_MIB_JMX agentName = null;

	/**
	 * Sets the Agent Reference
	 */
	public void setAgentReference(AdventNet_WebNMS_MIB_JMX agentRef){
		agentName = agentRef;
	}


	/**
	 * Gets the Agent Reference
	 */
	public AdventNet_WebNMS_MIB_JMX getAgentReference(){
		return agentName;
	}

	private WebNMSschedulerEntry entry = null;
	private String instrClassName = "com.adventnet.nms.jmxagent.WebNMSschedulerEntry"; //No I18N
	private String[] indexNames = new String[]{"WebNMSSchedulerIndex"};//No I18N

	/**
	 * To get the index column names of the table
	 * @retrun An array of String containing the Index names
	 */
	public String[] getIndexNames()
	{
		return indexNames;
	}
	MBeanServer server = null;
	private boolean[] externalIndexFlags = new boolean[]{false};

	private Hashtable table = null;
	private Vector vec = null;


	/**
	 * Sets the Hashtable containing the Table entries
	 * @param vec The Hashtable containing the Table entries
	 */
	public void setHashtable(Hashtable table)
	{
		this.table = table;
		vec = null;
	}

	/**
	 * Returns the Hashtable containing the Table entries
	 * @return Hashtable containing the Table entries
	 */
	public Hashtable getHashtable()
	{
		return table;
	}

	/**
	 * Sets the Vector containing the Table entries
	 * @param vec The Vector containing the Table entries
	 */
 	public void setTableVector(Vector  vec)
	{
		this.vec = vec;
		table = null;
	}

	/**
	 * Returns the Vector containing the Table entries
		* @return Vector containing the Table entries
	 */
	public Vector getTableVector()
	{
		return vec;
	}



	/**
	 * Initialises the WebNMSSchedulerTable
	 */
	public WebNMSSchedulerTable()
	{
		table = new Hashtable();
	}
	public WebNMSSchedulerTable(AdventNet_WebNMS_MIB_JMX agentRef)
	{
		agentName = agentRef;
		table = new Hashtable();
	}



	/**
	 * Returns the TabularData
	 * @return TabularData containing the rows corresponding the WebNMSSchedulerTable
	 */
	public TabularData getWebNMSSchedulerTable()
	{

	// User code starts here
	if(true)
	{
	    return getTable();
	}
	// User code ends here

		try{
			if(table != null)
				return  Utilities.getTabularData(this, indexNames, table, instrClassName, null);
			else if(vec != null)
				return  Utilities.getTabularData(this, indexNames, vec, instrClassName, null);
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return null;
	}



	/**
	 * Sets the TabularData to the AaplicationTable
	 * @param data The TabularData to be set to the AaplicationTable
	 */
	public void setWebNMSSchedulerTable(TabularData data) throws Exception
	{
		AgentException ae = null;

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();
			CompositeData comp = data.getRow(index);

			if(table != null)
				entry = (WebNMSschedulerEntry)Utilities.getEntryFromCompositeData(table, comp, indexNames, instrClassName);
			else if(vec != null)
				entry = (WebNMSschedulerEntry)Utilities.getEntryFromCompositeData(vec, comp, indexNames, instrClassName);

			if(comp.getOperationType().equals(CompositeData.CREATED))
			{//create new entry

			if(entry != null)
				throw new AgentException("Row already exist",CommonUtils.ROWCREATIONFAILED);//No I18N
				entry = new WebNMSschedulerEntry();

				if(table != null)
					table.put(index, entry);
				else if(vec != null)
				vec.addElement(entry);
				for(Enumeration ce = comp.enumerate();ce.hasMoreElements();)
				{
					String key = (String)ce.nextElement();
					try{
						Utilities.setField(entry, instrClassName, key, comp.getDataItem(key));
					}catch(AgentException aexp){
						ae = aexp;
					}
				}
			}

			else if(comp.getOperationType().equals(CompositeData.DELETED))
			{

				if(table != null)
				{
					for(Enumeration en = table.keys();en.hasMoreElements();)
					{
						Object keyObject = en.nextElement();
						if(entry.equals(table.get(keyObject)))
							table.remove(keyObject);
					}
				}
				else if(vec != null)
					if(!vec.removeElement(entry))
						throw new AgentException("Invalid Index", CommonUtils.INVALIDINDEX);//No I18N
				data.deleteRow(index);
			}

			else if(comp.getOperationType().equals(CompositeData.MODIFIED))
			{

				for(Enumeration ce = comp.enumerate();ce.hasMoreElements();)
				{
					String key = (String)ce.nextElement();
					if(!comp.isModified(key))
						continue;
					try{

						Utilities.setField(entry, instrClassName, key, comp.getDataItem(key));
					}catch(AgentException aexp){
						ae = aexp;
					}
				}
			}

			comp.setOperationType(CompositeData.NOCHANGES);
		}

		if(ae != null)
			throw ae;

	}
	 // User code starts here
    /**
     * getting all the scheduler objects.Using the scheduler objects,forming an array of CompositeData
     * Forming a TabularData using the CompositeData array.Returning the Tabulardata
     */
    TabularData getTable()
    {
	TabularData td = null;
	String[] names = {"WebNMSSchedulerIndex","WebNMSSchedulerDesc","WebNMSSchedulerNumTasks", "WebNMSSchedulerNumThreads","WebNMSSchedulerActiveThreads", "WebNMSSchedulerIdleThreads"}; //No I18N
	OpenMBeanParameterInfo[] parameterInfo = new OpenMBeanParameterInfo[names.length];
	String returnType = null;

	try
	{
	    Class entryClassName = Class.forName(instrClassName);

	    for(int i = 0; i < names.length; i++)
	    {
		String methodName = "get" + names[i];//No I18N
		Method method = entryClassName.getMethod(methodName,null);
		returnType = method.getReturnType().getName();
		parameterInfo[i] = new OpenMBeanParameterInfo(names[i],
							      returnType,
							      null,null,null);
	    }
	}
	catch(Exception e)
	{
	    agentName.agentErr.fail("Exception = ", e);//No I18N
	    return null;
	}

	try
	{
	    Vector schedulerVector = Scheduler.getSchedulerList();
	    int noOfObjs = schedulerVector.size();
	    CompositeData[] comps = new CompositeData[noOfObjs];

	    for(int con = 0 ;con < noOfObjs; con++)
		comps[con] =  makeComData((Scheduler)schedulerVector.elementAt(con),names);

	    TabularParameterInfo tinfo = new TabularParameterInfo(null,null,null,null,null,
								  parameterInfo,
								  indexNames);
	    td =  new TabularData(tinfo, comps);
	}
	catch(Exception e)
	{
	    agentName.agentErr.fail(" Exception = ", e);//No I18N
	    return null;
	}
	return td;
    }
    /**
     * forming CompositeData for each scheduler object and returning the CompositeData formed
     */
    private CompositeData makeComData(Scheduler scheduler,String[] names)
    {
	//getting the all values
	Object[] values = new Object[names.length];
	values[0] = new Integer(scheduler.getIndex());
	values[1] = scheduler.getDescriptor();
	values[2] = new Integer(scheduler.getNumTasks());
	values[3] = new Integer(scheduler.getNumThreads());
	values[4] = new Integer(scheduler.getActiveThreads());
	values[5] = new Integer(scheduler.getIdleThreads());

	try
	{
	    return new CompositeData(null, names, values);
	}
	catch(Exception e)
	{
	    return null;
	}
    }
    // User code ends here



}





