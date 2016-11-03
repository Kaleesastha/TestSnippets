//$Id: PollTable.java,v 1.3 2007/09/06 12:47:41 barathv Exp $
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
import com.adventnet.nms.poll.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.transaction.PreparedStatementWrapper;
import com.adventnet.nms.store.relational.*;

import com.adventnet.nms.util.*;
	// User code ends here


/**
 * Handles all requests under
 * pollTable group
 */


public class PollTable implements JmxTableModelListener{

      // User code starts here

    String[] names = {"Pollid","PolldataName","Oid","PollingInterval","FailureCount", "FailureThreshold","TimeToPoll","PolledTime", "AgentName"};//No I18N
    Object[] values = null;

    int psSelectMinOfPollID;
    int psSelectNextMinOfPollID;
    int psSelectDataOfPollID;
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

	private PollEntry entry = null;
	private String instrClassName = "com.adventnet.nms.jmxagent.PollEntry";//No I18N
	private String[] indexNames = new String[]{"Pollid"};//No I18N

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
	 * Initialises the PollTable
	 */
	public PollTable()
	{
		table = new Hashtable();
	}
	public PollTable(AdventNet_WebNMS_MIB_JMX agentRef)
	{
		agentName = agentRef;
		table = new Hashtable();
		 //User code starts here
        table = new Hashtable();

        //ResultSet rs1=  agentName.topoDB.query("select min(id) from polleddata");//No I18N
        /** selecting the polleddata with the least pollid
         */
        psSelectMinOfPollID = agentName.rlAPI.getPreparedStatementID("SELECT MIN(ID) FROM PolledData");//No I18N
        // rs= agentName.topoDB.query("select min(id),name,agent,oid from polleddata where id>" + (id.longValue()));//No I18N
        /** getting the details for the polleddata having the least pollid greater than the one passed as argument
         */
        psSelectNextMinOfPollID = agentName.rlAPI.getPreparedStatementID("SELECT MIN(ID) FROM PolledData WHERE ID>?");//No I18N
        // ResultSet rs= agentName.topoDB.query("select name,agent,oid from polleddata where id ="+id1);//No I18N
        psSelectDataOfPollID = agentName.rlAPI.getPreparedStatementID("SELECT NAME,AGENT,OID FROM PolledData WHERE ID=?");//No I18N
        //User code ends here
	}





	public CompositeData getFirstEntry()
	{
	              // User code starts here
	              /*
		return null;
		*/
		if(!agentName.initPoll())
	    return null;
	ResultSet rs = null;
	PreparedStatementWrapper pstatForPd = null;
        PreparedStatement pstatementForPd = null;
        try
            {
                long id1 = 0 ;
		pstatForPd = agentName.rlAPI.fetchPreparedStatement(psSelectMinOfPollID);
		pstatementForPd = pstatForPd.getPreparedStatement();
		rs = agentName.rlAPI.executeQuery(pstatementForPd);
		rs.next();
		if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
		    id1 = Long.parseLong(rs.getString(1));
		else
		    id1 = rs.getLong(1);

		Object[] obj = new Object[]{new Long(id1)};

		return getEntry(obj);
            }
        catch(Exception e)
            {
                return null;
            }
        finally
            {
                try
                    {
                        rs.close();
                    }
                catch(Exception a2)
                    {

                    }
                agentName.rlAPI.returnPreparedStatement(pstatForPd);
            }
		// User code ends here
	}
	public CompositeData getEntry(Object[] indexObjects)
	{
	     	// User code starts here
	     	/*
		return null;
		*/
		/** checking if the handle for the pollAPI is got or not
         */

	if(!agentName.initPoll())
	    return null;

	ResultSet rs = null;
	PreparedStatementWrapper pstatForPd = null;
    	PreparedStatement pstatementForPd = null;
        Long id = (Long)indexObjects[0];
        try
            {
                pstatForPd = agentName.rlAPI.fetchPreparedStatement(psSelectDataOfPollID);
                pstatementForPd = pstatForPd.getPreparedStatement();
		pstatementForPd.setLong(1,id.longValue());
		rs= agentName.rlAPI.executeQuery(pstatementForPd);
                rs.next();

                /** parsing the result set to get the key of the polleddata
                 */
                String key = rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3);//No I18N

                /** getting the polleddata by passing the key
                 */
                PolledData  pd = agentName.pollAPI.getPolledData(key);
                return makeComData(pd);
            }
        catch(Exception e)
            {
                return null;
            }
        finally
            {
                try
                    {
                        rs.close();
                    }
                catch(Exception a1)
                    {
                    }
                agentName.rlAPI.returnPreparedStatement(pstatForPd);
            }
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

        Long id = (Long)indexObjects[0];
        ResultSet rs = null;
	PreparedStatementWrapper pstatForPd = null;
    	PreparedStatement pstatementForPd = null;

        try
            {
		long id1 = 0;
		try
		{
		    pstatForPd = agentName.rlAPI.fetchPreparedStatement(psSelectNextMinOfPollID);
		    pstatementForPd = pstatForPd.getPreparedStatement();
		    pstatementForPd.setLong(1,id.longValue());
		    rs = agentName.rlAPI.executeQuery(pstatementForPd);
		    rs.next();
		    if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
			id1 = Long.parseLong(rs.getString(1));
		    else
			id1 = rs.getLong(1);
		}
		finally
		{
		    try
		    {
			rs.close();
		    }
		    catch(Exception ss)
		    {
		    }
		    agentName.rlAPI.returnPreparedStatement(pstatForPd);
		}

                pstatForPd = agentName.rlAPI.fetchPreparedStatement(psSelectDataOfPollID);
                pstatementForPd = pstatForPd.getPreparedStatement();
		pstatementForPd.setLong(1,id1);
		rs= agentName.rlAPI.executeQuery(pstatementForPd);
                rs.next();

                /** parsing the result set to get the key of the polleddata
                 */
                String key = rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3);//No I18N

                /** getting the polleddata by passing the key
                 */
                PolledData  pd = agentName.pollAPI.getPolledData(key);
                return makeComData(pd);
            }
        catch(Exception e)
            {
                return null;
            }
        finally
            {
                try
                    {
                        rs.close();
                    }
                catch(Exception e)
                    {
                    }
                agentName.rlAPI.returnPreparedStatement(pstatForPd);
            }
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
		Vector v = agentName.pollMgr.getCompleteList();
                /** getting the total number of polleddata in the NMS at that instant
                 */
		int size = v.size();
		return size;
	    }
	    catch(Exception xw)
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
	long id1 = 0 ;
	for(int i=0;i<totalRows();i++)
	{
	    ResultSet rs = null;
	    PreparedStatementWrapper pstatForPd = null;
	    PreparedStatement pstatementForPd = null;
	    try
	    {
		if(id1 == 0)
		{

		    pstatForPd = agentName.rlAPI.fetchPreparedStatement(psSelectMinOfPollID);
		    pstatementForPd = pstatForPd.getPreparedStatement();
		    rs = agentName.rlAPI.executeQuery(pstatementForPd);
		    rs.next();
                    if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
                        id1 = Long.parseLong(rs.getString(1));
                    else
                        id1 = rs.getLong(1);
		}
		else
		{
		    pstatForPd = agentName.rlAPI.fetchPreparedStatement(psSelectNextMinOfPollID);
		    pstatementForPd = pstatForPd.getPreparedStatement();
		    pstatementForPd.setLong(1,id1);
		    rs = agentName.rlAPI.executeQuery(pstatementForPd);
		    rs.next();
                    if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
                        id1 = Long.parseLong(rs.getString(1));
                    else
                        id1 = rs.getLong(1);
		}
	    }
	    catch(Exception w)
	    {
		agentName.agentErr.fail("Exception in agent-poll",w);   //No I18N
	    }
	    finally
	    {
                try
                    {
                        rs.close();
                    }
                catch(Exception a2)
                    {

                    }
		agentName.rlAPI.returnPreparedStatement(pstatForPd);
	    }

	    if((i+1 >= startIndex) && (i+1 <= endIndex))
	    {
		Object[] indx = new Object[]{new Long(id1)};
		arrayList.add(indx);
                /** checking if the upper limit is exceeded,if so break
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
	 * @return TabularData containing the rows corresponding the PollTable
	 */
	public TabularData getPollTable()
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
	public void setPollTable(TabularData data) throws Exception
	{
		AgentException ae = null;

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();
			CompositeData comp = data.getRow(index);

			if(table != null)
				entry = (PollEntry)Utilities.getEntryFromCompositeData(table, comp, indexNames, instrClassName);
			else if(vec != null)
				entry = (PollEntry)Utilities.getEntryFromCompositeData(vec, comp, indexNames, instrClassName);

			if(comp.getOperationType().equals(CompositeData.CREATED))
			{//create new entry

			if(entry != null)
				throw new AgentException("Row already exist",CommonUtils.ROWCREATIONFAILED);//No I18N
				entry = new PollEntry();

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
     *  getting all the polleddatas.Forming the array of CompositeDatas using the polleddatas.
     *  Forming a TabularData using the array of CompositeData and returning the TabularData
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
                /** getting the class name of the entry file
                 */
                Class entryClassName = Class.forName(instrClassName);

                for(int i = 0; i < names.length; i++)
                    {
                        String methodName = "get" + names[i];//No I18N
                        /** getting the method from the entry file
                         */
                        Method method = entryClassName.getMethod(methodName,null);
                        /** getting the return type for the methods in the entry file
                         */
                        returnType = method.getReturnType().getName();
                        parameterInfo[i] = new OpenMBeanParameterInfo(names[i],returnType,null,null,null);
                    }
            }
        catch(Exception e)
            {
               agentName.agentErr.fail(" exception ", e);//No I18N
                return null;
            }
	PolledData pd = null;
        /** getting a list of all the polleddatas' keys
         */
	Vector v = null;
	try {
	    v = agentName.pollMgr.getCompleteList();

	} catch (Exception e) {
        	    return null;
	}

        int size = v.size();
	CompositeData[]	comps = new CompositeData[size];
	for(int j=0;j<size;j++)
	    {
		try
		    {
                        pd = agentName.pollMgr.getPolledData((String)v.elementAt(j));
                        comps[j]  = makeComData(pd);
		    }
		catch(Exception e)
		    {
                        agentName.agentErr.fail("the exception in poll..",e);//No I18N
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
                        agentName.agentErr.fail("  exception is  ",e);//No I18N
                    }
                return td;
    }
     /**
     * forming CompositeData for each polleddata and returning the CompositeData
     */
    //this can have any required return type depending on our necessity
    private CompositeData makeComData(PolledData pd)
    {
        //getting the all values
        values = new Object[names.length];

        values[0] = new Long((int)pd.getId());
        values[1] = pd.getName();
        values[2] = pd.getOid();
        values[3] = new Long(pd.getPeriod());


        values[4] = new Integer(pd.getFailureCount());
        values[5] = new Integer(pd.getFailureThreshold());
        values[6] = new Long(pd.getTimeVal());
        values[7] =new Long(pd.getLastTimeValue());
	values[8] = pd.getAgent();

	try
            {
                return new CompositeData(null, names, values);
            }
	catch(Exception e)
            {
                agentName.agentErr.fail(" exception in poll...",e);//No I18N
                return null;
            }

    }
	// User code ends here

}





