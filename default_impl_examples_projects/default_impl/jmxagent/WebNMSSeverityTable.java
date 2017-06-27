//$Id: WebNMSSeverityTable.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Thu Feb 22 12:16:11 IST 2007
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
import com.adventnet.nms.severity.*;
	// User code ends here


/**
 * Handles all requests under
 * webNMSSeverityTable group
 */


public class WebNMSSeverityTable{

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

	private WebNMSSeverityEntry entry = null;
	private String instrClassName = "com.adventnet.nms.jmxagent.WebNMSSeverityEntry"; //No I18N
	private String[] indexNames = new String[]{"SeverityName"};//No I18N

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
	 * Initialises the WebNMSSeverityTable
	 */
	public WebNMSSeverityTable()
	{
		table = new Hashtable();
	}
	public WebNMSSeverityTable(AdventNet_WebNMS_MIB_JMX agentRef)
	{
		agentName = agentRef;
		table = new Hashtable();
	}



	/**
	 * Returns the TabularData
	 * @return TabularData containing the rows corresponding the WebNMSSeverityTable
	 */
	public TabularData getWebNMSSeverityTable()
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
	public void setWebNMSSeverityTable(TabularData data) throws Exception
	{
		AgentException ae = null;

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();
			CompositeData comp = data.getRow(index);

			if(table != null)
				entry = (WebNMSSeverityEntry)Utilities.getEntryFromCompositeData(table, comp, indexNames, instrClassName);
			else if(vec != null)
				entry = (WebNMSSeverityEntry)Utilities.getEntryFromCompositeData(vec, comp, indexNames, instrClassName);

			if(comp.getOperationType().equals(CompositeData.CREATED))
			{//create new entry

			if(entry != null)
				throw new AgentException("Row already exist",CommonUtils.ROWCREATIONFAILED);//No I18N
				entry = new WebNMSSeverityEntry();

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
     * getting all the severity objects.Using them,forming an array of CompositeDatas.
     * Using the array of CompositeDatas,forming a TabularData.Returning the TabularData
     */
    TabularData getTable()
    {
        if(!agentName.initAlert())
	    return null;


	String[] names = {"SeverityName","NumberOfAlarms"};//No I18N
	Object[] values = new Object[names.length];

        TabularData td = null;
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
		parameterInfo[i] = new OpenMBeanParameterInfo(names[i],returnType,null,null,null);
	    }
	}
        catch(Exception e)
	{
	    agentName.agentErr.fail("exception..",e);//No I18N
	    return null;
	}

        SeverityInfo sevInfo = SeverityInfo.getInstance();
	Vector sevector = sevInfo.getNames();
        int size = sevector.size();
        CompositeData[] comps = new CompositeData[size];

        for(int i=0;i<size;i++)
	{
            try
                {
                    String severity = (String)sevector.elementAt(i);

                    values = new Object[names.length];
                    values[0] = severity;
                    int sevalue = sevInfo.getValue(severity);
                    values[1] = new Integer(agentName.alertAPI.getAlertsCount(sevalue));
                    comps[i] = new CompositeData(null,names,values);
                }
	    catch(Exception e)
                {
                    agentName.agentErr.fail(" exception  ", e);//No I18N
                }
        }
        TabularParameterInfo tinfo = new TabularParameterInfo(null,null,null,null,null,
                                                              parameterInfo,
                                                              indexNames);
        try
	{
	    td =  new TabularData(tinfo, comps);
	}
        catch(Exception  e)
	{
	 agentName.agentErr.fail("exception...",e);//No I18N
	}
        return td;
    }
    // User code ends here


}





