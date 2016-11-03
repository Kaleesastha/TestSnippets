  //$Id: EventTable.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.*;
import java.sql.*;
import com.adventnet.management.transaction.PreparedStatementWrapper;
import com.adventnet.nms.store.relational.*;
	// User code ends here


/**
 * Handles all requests under
 * eventTable group
 */


public class EventTable implements JmxTableModelListener{

	// User code starts here
int psSelectMaxOfEventID;
	int psSelectNextMinOfEventID;
	PreparedStatementWrapper pstatForEvt = null;
	PreparedStatement pstatementForEvt = null;

	String[] names = {"EvtID","EvtSource","EvtEntity","EvtSeverity","EvtCategory","EvtTime","EvtText","EventUserProperties"};//No I18N
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

	private EventEntry entry = null;
	private String instrClassName = "com.adventnet.nms.jmxagent.EventEntry";//No I18N
	private String[] indexNames = new String[]{"EvtID"};//No I18N

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
	 * Initialises the EventTable
	 */
	public EventTable()
	{
		table = new Hashtable();
	}
	public EventTable(AdventNet_WebNMS_MIB_JMX agentRef)
	{
		agentName = agentRef;
		table = new Hashtable();
		// User code starts here
		/** getting the event having the least id
		 */
		psSelectMaxOfEventID = agentName.rlAPI.getPreparedStatementID("SELECT MAX(ID) FROM Event");//No I18N
		//   rs = agentName.topoDB.query("select min(id) from event where id>"+id);//No I18N
		/** getting the next event with id least greater than the one passed as argument
		 */
		psSelectNextMinOfEventID =  agentName.rlAPI.getPreparedStatementID("SELECT MIN(ID) FROM Event WHERE ID>?");//No I18N
		// User code ends here
	}





	public CompositeData getFirstEntry()
	{
	     		// User code starts here
	     		/*
	     		return null;
	     		*/
	     		if(!agentName.initEvent())
			return null;

		Event event = null;
		ResultSet rs = null;
		int id1 = 0 ;
		try
		{

			pstatForEvt = agentName.rlAPI.fetchPreparedStatement(psSelectMaxOfEventID);
			pstatementForEvt = pstatForEvt.getPreparedStatement();
			rs = agentName.rlAPI.executeQuery(pstatementForEvt);
			rs.next();
			id1 = identifyStartId(rs.getInt(1));
			event = agentName.eventAPI.getEventByID(id1);
		}
		catch(Exception e)
		{
			return null;
		}
		finally
		{
			try
			{
				if(rs != null)
				{
					rs.close();
				}
			}
			catch(Exception err){}
			agentName.rlAPI.returnPreparedStatement(pstatForEvt);
		}
		return makeComData(event);
	     		// User code ends here
	}
	public CompositeData getEntry(Object[] indexObjects)
	{
		// User code starts here
		/*
		return null;
		*/
		if(!agentName.initEvent())
			return null;

		int id = ((Integer)indexObjects[0]).intValue();
		Event event = null;
		try
		{
			event =  agentName.eventAPI.getEventByID(id);
		}
		catch(Exception e)
		{
			return null;
		}
		return makeComData(event);

		// User code ends here
	}
	public CompositeData getNextEntry(Object[] indexObjects)
	{
	 		// User code starts here
	 		/*
	     		return null;
	     		*/
	     		if(!agentName.initEvent())
			return null;

		int id = ((Integer)indexObjects[0]).intValue();
		int id1 = 0 ;
		Event event = null;
		ResultSet rs = null;
		try
		{

			pstatForEvt = agentName.rlAPI.fetchPreparedStatement(psSelectNextMinOfEventID);
			pstatementForEvt = pstatForEvt.getPreparedStatement();
			pstatementForEvt.setInt(1,id);
			rs = agentName.rlAPI.executeQuery(pstatementForEvt);
			rs.next();
			id1 = rs.getInt(1);
			event =  agentName.eventAPI.getEventByID(id1);
		}
		catch(Exception e)
		{
			return null;
		}
		finally
		{
			try
			{
				if(rs != null)
				{
					rs.close();
				}
			}
			catch(Exception err){}
			agentName.rlAPI.returnPreparedStatement(pstatForEvt);

		}
		return makeComData(event);

	     		// User code ends here
	}
	public int totalRows()
	{
		// User code starts her
		/*
		return -1;
		*/
		if(agentName.initEvent())
		{
			try
			{
				/** getting the total number of events in the NMS
				 */
				return agentName.eventAPI.getTotalEventCount();
			}
			catch(Exception e)
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
		if(!agentName.initEvent())
			return null;

		ArrayList arrayList = new ArrayList();
		int id = -1;
		for(int i = 0; i < totalRows(); i++)
		{
			ResultSet rs = null;

			//getting all the ids one by one
			try
			{
				if(id == -1)
				{
					try
					{
						pstatForEvt = agentName.rlAPI.fetchPreparedStatement(psSelectMaxOfEventID);
						pstatementForEvt = pstatForEvt.getPreparedStatement();
						rs = agentName.rlAPI.executeQuery(pstatementForEvt);
						rs.next();
						id = identifyStartId(rs.getInt(1));
					}
					catch(Exception err)
					{
						agentName.agentErr.fail("exception is...",err); //No internationalization//No I18N
					}
					finally
					{
						if(rs != null)
						{
							rs.close();
						}
						agentName.rlAPI.returnPreparedStatement(pstatForEvt);
					}
				}
				else
				{
					try
					{
						pstatForEvt = agentName.rlAPI.fetchPreparedStatement(psSelectNextMinOfEventID);
						pstatementForEvt = pstatForEvt.getPreparedStatement();
						pstatementForEvt.setInt(1,id);
						rs = agentName.rlAPI.executeQuery(pstatementForEvt);
						rs.next();
						id = rs.getInt(1);
					}
					catch(Exception err)
					{
						agentName.agentErr.fail("exception is...,",err); //No internationalization
					}
					finally
					{
						if(rs != null)
						{
							rs.close();
						}
						agentName.rlAPI.returnPreparedStatement(pstatForEvt);
					}
				}
			}
			catch(Exception e)
			{
				agentName.agentErr.fail("exception is...",e);//No I18N
			}
			finally
			{
				try
				{
					if(rs != null)
					{
						rs.close();
					}
				}
				catch(Exception err){}
				agentName.rlAPI.returnPreparedStatement(pstatForEvt);
			}
			//checking if the data obtained is in the range,then put in object array
			if((i+1 >= startIndex) && (i+1 <= endIndex))
			{
				Object[] indx = new Object[]{new Integer(id)};
				arrayList.add(indx);
				/** checking if the limit is being exceeded,if so,then break
				 */
				if(i+1 == endIndex) break;
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
	 * @return TabularData containing the rows corresponding the EventTable
	 */
	public TabularData getEventTable()
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
	public void setEventTable(TabularData data) throws Exception
	{
		AgentException ae = null;

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();
			CompositeData comp = data.getRow(index);

			if(table != null)
				entry = (EventEntry)Utilities.getEntryFromCompositeData(table, comp, indexNames, instrClassName);
			else if(vec != null)
				entry = (EventEntry)Utilities.getEntryFromCompositeData(vec, comp, indexNames, instrClassName);

			if(comp.getOperationType().equals(CompositeData.CREATED))
			{//create new entry

			if(entry != null)
				throw new AgentException("Row already exist",CommonUtils.ROWCREATIONFAILED);//No I18N
				entry = new EventEntry();

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
	 * getting all the events in the NMS Events table,using those events forming an array of composite data.Using the array
	 * of composite data,forming a Tabular Data and returning the TabularData
	 */

	TabularData getTable()
	{

		if(!agentName.initEvent())
			return null;

		TabularData td = null;

		OpenMBeanParameterInfo[] parameterInfo = new OpenMBeanParameterInfo[names.length];
		//make a return type array for all methods
		String returnType = null;
		int num =0;
		try
		{
			/** getting the class name of the entry file
			 */
			Class entryClassName = Class.forName(instrClassName);
			//getting the types of all get methods
			for(int i = 0; i < names.length; i++)
			{
				String methodName = "get" + names[i];//No I18N
				/** getting the method names in the entry file
				 */
				Method method = entryClassName.getMethod(methodName,null);
				/** getting the return types of all the methods defined in the entry file
				 */
				returnType = method.getReturnType().getName();
				parameterInfo[i] = new OpenMBeanParameterInfo(names[i],returnType,null,null,null);
			}


			num = agentName.eventAPI.getTotalEventCount();
			CompositeData[] comps = new CompositeData[num];
			int id = -1;

			Event event = null;
			for(int i=0;i<num;i++)
			{
				ResultSet rs = null;
				try
				{
					if(id == -1)
					{
						try
						{
							pstatForEvt = agentName.rlAPI.fetchPreparedStatement(psSelectMaxOfEventID);
							pstatementForEvt = pstatForEvt.getPreparedStatement();
							rs = agentName.rlAPI.executeQuery(pstatementForEvt);
							rs.next();
							id = identifyStartId(rs.getInt(1));
							/** getting the  event using the id as criteria
							 */
							event = agentName.eventAPI.getEventByID(id);
						}
						catch(Exception err)
						{
							agentName.agentErr.fail("exception is...",err); //No internationalization
						}
						finally
						{
							if(rs != null)
							{
								rs.close();
							}
							agentName.rlAPI.returnPreparedStatement(pstatForEvt);
						}
					}
					else
					{
						try
						{
							/** getting the next event using the id as criteria
							 */
							pstatForEvt = agentName.rlAPI.fetchPreparedStatement(psSelectNextMinOfEventID);
							pstatementForEvt = pstatForEvt.getPreparedStatement();
							pstatementForEvt.setInt(1,id);
							rs = agentName.rlAPI.executeQuery(pstatementForEvt);
							rs.next();
							id = rs.getInt(1);
							event =  agentName.eventAPI.getEventByID(id);
						}
						catch(Exception err)
						{
							agentName.agentErr.fail("exception is...",err); //No internationalization
						}
						finally
						{
							if(rs != null)
							{
								rs.close();
							}
							agentName.rlAPI.returnPreparedStatement(pstatForEvt);
						}
					}
				}
				catch(Exception e)
				{
					agentName.agentErr.fail("exception is...",e);//No I18N
				}
				finally
				{
					if(rs != null)
					{
						rs.close();
					}
					agentName.rlAPI.returnPreparedStatement(pstatForEvt);
				}
				comps[i-1] =  makeComData(event);

			}
			TabularParameterInfo tinfo = new TabularParameterInfo(null,null,null,null,null,
					parameterInfo,indexNames);

			td =  new TabularData(tinfo, comps);
		}

		catch(Exception e)
		{
			agentName.agentErr.fail("exception ...",e);//No I18N
		}

		return td;
	}
	/**
	 * forming the CompositeData for each event and returning the CompositeData
	 */

	private CompositeData makeComData(Event event)
	{
		values = new Object[names.length];
		values[0] = new Integer(event.getId());

		values[1] = event.getSource();

		values[2] = event.getEntity();

		values[3] = new Integer(event.getSeverity());

		values[4] = event.getCategory();

		values[5] = new Long(event.getTime());

		values[6] = event.getText();

		values[7] =   agentName.getPropValues(event.getUserProperties(),agentName.eventUserPropNames);


		try
		{
			return new CompositeData(null, names, values);
		}
		catch(Exception e)
		{
			agentName.agentErr.fail("exception ",e);//No I18N
			return null;
		}
	}
	private int identifyStartId(int maxId)
	{

		try
		{
			int tempId = maxId-agentName.eventAPI.getEventWindowSize()+1;
			if(tempId < 1)
			{
				tempId=1;
			}
			return tempId;
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		return 1;
	}
	// User code ends here

}





