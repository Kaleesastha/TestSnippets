//$Id: VarbindLogTable.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
/* Copyright (c)  1996 - 2004  Adventnet, Inc. All Rights Reserved.
 * PLEASE READ THE ASSOCIATED COPYRIGHTS FILE FOR MORE DETAILS.
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.
 * ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.
 */

/**
 * @Version :  6.0.0 Wed Feb 14 00:34:10 IST 2007
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
import java.sql.*;
import com.adventnet.management.transaction.PreparedStatementWrapper;
	// User code ends here

/**
 * Handles all requests under
 * varbindLogTable group
 */


public class VarbindLogTable implements JmxTableModelListener{

	private AdventNet_WebNMS_MIB_JMX agentName = null;
	// User code starts here
    String[] names = {"NotiLogIndex","VarbindIndex","VarbindType","VarbindValue"};//No I18N
    Object[] values = null;

    PreparedStatementWrapper pstat = null;
    PreparedStatement pstatement = null;
    // User code ends here

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

	private VarbindLogEntry entry = null;
	private String instrClassName = "com.adventnet.nms.jmxagent.VarbindLogEntry";//No I18N
	private String[] indexNames = new String[]{"NotiLogIndex","VarbindIndex"};//No I18N

	/**
	 * To get the index column names of the table
	 * @retrun An array of String containing the Index names
	 */
	public String[] getIndexNames()
	{
		return indexNames;
	}
	MBeanServer server = null;
	private boolean[] externalIndexFlags = new boolean[]{true, false};

	private Hashtable table = null;
	private Vector vec = null;

	private Object externalHandler = null;
	public void setExternalIndexHandler(Object externalHandler)
	{
		this.externalHandler = externalHandler;
	}


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
	 * Initialises the VarbindLogTable
	 */
	public VarbindLogTable()
	{
		table = new Hashtable();
	}
	public VarbindLogTable(AdventNet_WebNMS_MIB_JMX agentRef)
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
	     		ResultSet rs = null;
	/*
	 * Getting the very first varbind value of the first Notification
	 */
	try
	{
	    pstat = agentName.rlAPI.fetchPreparedStatement(agentName.psSelectFromVarID);
	    pstatement = pstat.getPreparedStatement();
	    pstatement.setInt(1,agentName.startIndex);
	    pstatement.setInt(2,1);
	    rs = agentName.rlAPI.executeQuery(pstatement);
	    rs.next();
	    CompositeData cd = makeComData(rs);

	    return cd;
	}
	catch(Exception e)
	{
	    return null;
	}
	finally
	{
	    try{
		rs.close();
	    }catch(Exception e){}
	    agentName.rlAPI.returnPreparedStatement(pstat);
	}
	     		// User code ends here
	}
	public CompositeData getEntry(Object[] indexObjects)
	{
		// User code starts here
		/*
		return null;
		*/
		int index1 = ((Integer)indexObjects[0]).intValue();
	int index2 = ((Integer)indexObjects[1]).intValue();

	ResultSet rs = null;
	/*
	 * Getting the perticular varbind value of a specified Notification
	 */
	try
	{
	    pstat = agentName.rlAPI.fetchPreparedStatement(agentName.psSelectFromVarID);
	    pstatement = pstat.getPreparedStatement();
	    pstatement.setInt(1,index1);
	    pstatement.setInt(2,index2);
	    rs = agentName.rlAPI.executeQuery(pstatement);
	    rs.next();
	    CompositeData cd = makeComData(rs);
	    return cd;
	}
	catch(Exception e)
	{
	    return null;
	}
	finally
	{
	    try{
		rs.close();
	    }catch(Exception e){}
	    agentName.rlAPI.returnPreparedStatement(pstat);
	}
	// User code ends here
    }




	public CompositeData getNextEntry(Object[] indexObjects)
	{
	     		// User code starts here
	     		/*
	     			return null;
	     			*/
	     			int index1 = ((Integer)indexObjects[0]).intValue();
	int index2 = ((Integer)indexObjects[1]).intValue();

	ResultSet rs = null;
	/*
	 * Getting the perticular varbind value of a specified Notification
	 */
	try
	{
	    pstat = agentName.rlAPI.fetchPreparedStatement(agentName.psSelectFromVarID);
	    pstatement = pstat.getPreparedStatement();
	    pstatement.setInt(1,index1);
	    pstatement.setInt(2,index2+1);
	    rs = agentName.rlAPI.executeQuery(pstatement);

	    if(!rs.next())
	    {
		rs.close();

		// THEME-II Start
		agentName.rlAPI.returnPreparedStatement(pstat);
		// THEME-II End

		pstat = agentName.rlAPI.fetchPreparedStatement(agentName.psSelectFromVarID);
		pstatement = pstat.getPreparedStatement();
		if(index1 == agentName.intLimit)
		    pstatement.setInt(1,1);
		else
		    pstatement.setInt(1,index1+1);
		pstatement.setInt(2,1);
		rs = agentName.rlAPI.executeQuery(pstatement);
		rs.next();
	    }
	    CompositeData cd = makeComData(rs);
	    return cd;
	}
	catch(Exception e1)
	{
	    return null;
	}
	finally
	{
	    try{
		rs.close();
	    }catch(Exception e){}
	    agentName.rlAPI.returnPreparedStatement(pstat);
	}
	     			// User code ends here
	}
	public int totalRows()
	{
	     	// User code starts here
	     	/*
	     		return -1;
	     		*/
	     		ResultSet rs = null;
	int varLogRowCount = 0;
	PreparedStatementWrapper pstat1 = null;
	try
	{
	    pstat1 = agentName.rlAPI.fetchPreparedStatement(agentName.psSelectCountOfVarLogID);
	    PreparedStatement pstatement1 = pstat1.getPreparedStatement();
	    rs = agentName.rlAPI.executeQuery(pstatement1);
	    rs.next();
	    varLogRowCount =  rs.getInt(1);
	}
	catch(Exception e){}
	finally
	{
	    try{
		rs.close();
	    }catch(Exception e){}
	    agentName.rlAPI.returnPreparedStatement(pstat1);
	}
	return varLogRowCount;
	     		// User code ends here
	}
	public List getEntries(int startIndex, int endIndex)
	{
	     		// User code starts here
	     		/*
	     		return null;
	     		*/
	     		ArrayList arrayList = new ArrayList();

	int count = agentName.startIndex;
	for(int i = 0; i < totalRows();)
	{
	    int j = 1;
	    ResultSet rs = null;

	    while(true)
	    {
		try
		{
		    pstat = agentName.rlAPI.fetchPreparedStatement(agentName.psSelectFromVarID);
		    pstatement = pstat.getPreparedStatement();
		    pstatement.setInt(1,count);
		    pstatement.setInt(2,j);
		    rs = agentName.rlAPI.executeQuery(pstatement);
		    if(!rs.next()) break;

		    if((i+1 >= startIndex) && (i+1 <= endIndex))
		    {
			Object[] indx = new Object[]{new Integer(count),new Integer(j)};
			arrayList.add(indx);
			if(i+1 == endIndex) break;
		    }
		}
		catch(Exception e)
		{
		    return null;
		}
		finally
		{
		    try{
			rs.close();
		    }catch(Exception e){}
		    agentName.rlAPI.returnPreparedStatement(pstat);
		}
		i++;j++;
	    }

	    if(i+1 == endIndex) break;

	    if(count == agentName.intLimit)
		count = 1;
	    else
		count++;
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
	 * @return TabularData containing the rows corresponding the VarbindLogTable
	 */
	public TabularData getVarbindLogTable()
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
	public void setVarbindLogTable(TabularData data) throws Exception
	{
		AgentException ae = null;

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();

			CompositeData comp = data.getRow(index);
			if(!Utilities.checkExternalIndex(externalHandler, comp, indexNames, externalIndexFlags))
				throw new AgentException("Invalid External Index", CommonUtils.INVALIDEXTERNALINDEX);//No I18N
		}

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();
			CompositeData comp = data.getRow(index);

			if(table != null)
				entry = (VarbindLogEntry)Utilities.getEntryFromCompositeData(table, comp, indexNames, instrClassName);
			else if(vec != null)
				entry = (VarbindLogEntry)Utilities.getEntryFromCompositeData(vec, comp, indexNames, instrClassName);

			if(comp.getOperationType().equals(CompositeData.CREATED))
			{//create new entry

			if(entry != null)
				throw new AgentException("Row already exist",CommonUtils.ROWCREATIONFAILED);//No I18N
				entry = new VarbindLogEntry();

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
	    /*
     * Getting the TabularData for all the Notification's Varbind values
     */
    TabularData getTable()
    {
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
		parameterInfo[i] = new OpenMBeanParameterInfo(names[i],
							      returnType,
							      null,null,null);
	    }
	}
	catch(Exception e)
	{
	    agentName.agentErr.fail("Exception  = ", e);//No I18N
	    return null;
	}

	int varLogRowCount = 0;
	ResultSet rs = null;
	PreparedStatementWrapper pstat1 = null;
	/*
	 * Getting the total number of varbinds for all Notifications
	 */
	try
	{
	    pstat1 = agentName.rlAPI.fetchPreparedStatement(agentName.psSelectCountOfVarLogID);
	    PreparedStatement pstatement1 = pstat1.getPreparedStatement();
	    rs = agentName.rlAPI.executeQuery(pstatement1);
	    rs.next();
	    varLogRowCount =  rs.getInt(1);
	}
	catch(Exception e){}
	finally
	{
	    try{
		rs.close();
	    }catch(Exception e){}
	    agentName.rlAPI.returnPreparedStatement(pstat1);
	}

	CompositeData[] comps = new CompositeData[varLogRowCount];

	/*
	 * Getting the varbind values of all the Notifications
	 */
	for(int con = 0,i = agentName.startIndex ;con < varLogRowCount;)
	{
	    int j = 1;

	    while(true)
	    {
		try
		{
		    pstat = agentName.rlAPI.fetchPreparedStatement(agentName.psSelectFromVarID);
		    pstatement = pstat.getPreparedStatement();
		    pstatement.setInt(1,i);
		    pstatement.setInt(2,j++);
		    rs = agentName.rlAPI.executeQuery(pstatement);
		    if(!rs.next()) break;
		    comps[con++] =  makeComData(rs);
		}
		catch(Exception e)
		{
		    return null;
		}
		finally
		{
		    try{
			rs.close();
		    }catch(Exception e){}
		    agentName.rlAPI.returnPreparedStatement(pstat);
		}
	    }

	    if(i == agentName.intLimit)
		i = 1;
	    else
		i++;
	}

	try
	{
	    TabularParameterInfo tinfo = new TabularParameterInfo(null,null,null,null,null,
								  parameterInfo,
								  indexNames);
	    td =  new TabularData(tinfo, comps);
	}
	catch(Exception e)
	{
	    agentName.agentErr.fail("Exception = " , e);//No I18N
	    return null;
	}
	return td;
    }
    private CompositeData makeComData(ResultSet rs)
    {
	try
	{
	    //getting the all values
	    values = new Object[4];
	    values[0] = new Integer(rs.getInt(1));
	    values[1] = new Integer(rs.getInt(2));
	    values[2] = new Integer(rs.getInt(3));
	    values[3] = rs.getString(4);

            if(values[3] == null && ((Integer)values[0]).intValue() != 0)
            values[3] = "NULL";    //No I18N


	    return new CompositeData(null, names, values);
	}
	catch(Exception e)
	{
	    return null;
	}
    }

	// User code ends here

}





