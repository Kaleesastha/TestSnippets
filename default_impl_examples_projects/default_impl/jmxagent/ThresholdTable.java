//$Id: ThresholdTable.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Wed Feb 14 00:34:09 IST 2007
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
import com.adventnet.nms.poll.*;
import java.sql.*;
	// User code ends here




/**
 * Handles all requests under
 * thresholdTable group
 */


public class ThresholdTable implements JmxTableModelListener{
      // User code starts here
    String[] names = {"ThresholdObjectName","ThresholdKind","ThresholdMessage", "ThresholdClearMessage","ThresholdSeverity","ThresholdCategory"};//No I18N
    Object[] values = null;
    // User code ends here

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

	private ThresholdEntry entry = null;
	private String instrClassName = "com.adventnet.nms.jmxagent.ThresholdEntry";//No I18N
	private String[] indexNames = new String[]{"ThresholdObjectName"};//No I18N

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
	 * Initialises the ThresholdTable
	 */
	public ThresholdTable()
	{
		table = new Hashtable();
	}
	public ThresholdTable(AdventNet_WebNMS_MIB_JMX agentRef)
	{
		agentName = agentRef;
		table = new Hashtable();
	}





	public CompositeData getFirstEntry()
	{
		// User code starts here
		/*
		return null;
		*/
		if(!agentName.initPoll())
	    return null;

        Vector thVector=agentName.pollMgr.getAllThresholdObjects();

	ThresholdObject th = (ThresholdObject)thVector.elementAt(0);
        return makeComData(th);
		// User code ends her
	}
	public CompositeData getEntry(Object[] indexObjects)
	{
		// User code starts here
		/*
		return null;
		*/
		 /** checking if the pollAPI handle is got or not
         */
	if(!agentName.initPoll())
	    return null;

	String thresholdName = (String)indexObjects[0];
	ThresholdObject th = null;
	int index =0;
	Vector thVector=agentName.pollMgr.getAllThresholdObjects();
        /** getting the number of threshold objects configured in the NMS at that instant
         */
	int size = thVector.size();

	for(int i=0;i<size;i++)
	{
	    if(((ThresholdObject)thVector.elementAt(i)).getName().equals(thresholdName))
	    {
		index = i;
		break;
	    }
	}

	th = (ThresholdObject)thVector.elementAt(index);

	return makeComData(th);
		// User code ends here
	}
	public CompositeData getNextEntry(Object[] indexObjects)
	{
		// User code starts here
		/*
		return null;
		*/
		if(!agentName.initPoll())
	    return null;

        String thresholdName = (String)indexObjects[0];
        ThresholdObject th = null;
        int index =0;
        Vector thVector=agentName.pollMgr.getAllThresholdObjects();
        int size = thVector.size();

        for(int i=0;i<size;i++)
            {
                /** while in the loop,keep checking if the name that has come in is the same as that had
                    before,if so break but only after taking the index number of the element that has come in
                 */
                if(((ThresholdObject)thVector.elementAt(i)).getName().equals(thresholdName))
                    {
                        index = i;
                        break;
                    }
            }

        if((index +1) == size) th = null;
        /** the index number of the number+1 gives us the next threshold object
         */
        th = (ThresholdObject)thVector.elementAt(index + 1);

        return makeComData(th);
		// User code ends here
	}
	public int totalRows()
	{
		// User code starts here
		/*
		return -1;
		*/
		if(agentName.initPoll())
	{
	    try
	    {
		Vector thVector=agentName.pollMgr.getAllThresholdObjects();
		int size=thVector.size();
		return size;
	    }
	    catch(Exception ww)
	    {
		return 0;
	    }
	}

	return 0;
		// User code ends here
	}
	public List getEntries(int startIndex, int endIndex)
	{
		// User code starts here
		/*
				return null;
				*/
				if(!agentName.initPoll())
	    return null;

	ArrayList arrayList = new ArrayList();
	String name = "";//No I18N
	Vector thVector = agentName.pollMgr.getAllThresholdObjects();
	for(int i = 0 ; i< totalRows(); i++)
	{
	    if((i+1 >= startIndex) && (i+1 <= endIndex))
	    {
		try
		{
                                //passing the index to the object array
		    name = ((ThresholdObject)thVector.elementAt(i)).getName();
		    Object[] indx = new Object[]{name};
		    arrayList.add(indx);
                    /** checking if the upper limit is exceeded,if so break
                     */
                    if(i+1 == endIndex) break;
		}
		catch(Exception exc)
		{
		    agentName.agentErr.fail("Exception in agent-threshold",exc);//No I18N
		}
	    }
	}
	return arrayList;
				// User code ends here
	}
	public void addRow(Object[] indexObjects, CompositeData entry) throws Exception
	{	}
	public void modifyRow(Object[] indexObjects, CompositeData entry) throws Exception
	{	}
	public void deleteRow(Object[] indexObjects) throws Exception
	{	}

	/**
	 * Returns the TabularData
	 * @return TabularData containing the rows corresponding the ThresholdTable
	 */
	public TabularData getThresholdTable()
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
	public void setThresholdTable(TabularData data) throws Exception
	{
		AgentException ae = null;

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();
			CompositeData comp = data.getRow(index);

			if(table != null)
				entry = (ThresholdEntry)Utilities.getEntryFromCompositeData(table, comp, indexNames, instrClassName);
			else if(vec != null)
				entry = (ThresholdEntry)Utilities.getEntryFromCompositeData(vec, comp, indexNames, instrClassName);

			if(comp.getOperationType().equals(CompositeData.CREATED))
			{//create new entry

			if(entry != null)
				throw new AgentException("Row already exist",CommonUtils.ROWCREATIONFAILED);//No I18N
				entry = new ThresholdEntry();

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
     * getting the threshold objects.Using the threshold objects,forming an array of CompositeData.
     * Using the array of CompositeData, forming a TabularData.returning the TabularData
     */
    TabularData getTable()
    {
	if(!agentName.initPoll())
	    return null;


        TabularData td = null;
        OpenMBeanParameterInfo[] parameterInfo = new OpenMBeanParameterInfo[names.length];

        String returnType = null;

        try
            {
                /** getting the class name from the entry file
                 */
                Class entryClassName = Class.forName(instrClassName);

                for(int i = 0; i < names.length; i++)
                    {
                        String methodName = "get" + names[i];//No I18N
                        /** getting the method names from the entry file
                         */
                        Method method = entryClassName.getMethod(methodName,null);
                        /** getting the return type for each method in the entry file
                         */
                        returnType = method.getReturnType().getName();
                        parameterInfo[i] = new OpenMBeanParameterInfo(names[i],returnType,null,null,null);

                    }
            }

        catch(Exception e)
            {
                agentName.agentErr.fail("exception ", e);//No I18N
                return null;
            }
        /** storing all the threshold objects in a vector
         */
        Vector thVector=agentName.pollMgr.getAllThresholdObjects();
        int size=thVector.size();
        CompositeData[] comps = new CompositeData[size];

        for(int j=0;j<size;j++)
            {

	    	ThresholdObject th = (ThresholdObject)thVector.elementAt(j);
                comps[j] = makeComData(th);
            }
        TabularParameterInfo tinfo = new TabularParameterInfo(null,null,null,null,null,
                                                              parameterInfo,indexNames);

        try{

            td =  new TabularData(tinfo, comps);

           }

        catch(Exception e )
            {
            agentName.agentErr.fail("exception .....",e);//No I18N
            }

        return td;
    }
     /**
     * forming CompositeData for each threshold object and returning the CompositeData
     */

    private CompositeData makeComData(ThresholdObject th)
    {

          values = new Object[names.length];

          values[0] = th.getName();

          values[1] =  th.getKind();

          values[2] = th.getMessage();

          values[3] = th.getClrMessage();

          values[4] = new Integer(th.getSeverity());

          values[5] = th.getCategory();


          try
              {
                  return new CompositeData(null, names, values);
              }
          catch(Exception e)
              {
                  agentName.agentErr.fail(" exception..",e);//No I18N
                  return null;
              }

    }//end of composite data

	// USer code ends here


}






