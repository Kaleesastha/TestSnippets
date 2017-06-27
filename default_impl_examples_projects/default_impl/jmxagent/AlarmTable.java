//$Id: AlarmTable.java,v 1.3 2007/07/03 07:00:48 barathv Exp $
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
 * @Version :  6.0.0 Wed Jun 13 11:34:57 IST 2007
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
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.topodb.*;

import com.adventnet.nms.store.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.management.transaction.PreparedStatementWrapper;

import java.sql.*;
	// User code ends here





/** 
 * Handles all requests under 
 * alarmTable group
 */ 


public class AlarmTable implements JmxTableModelListener{


    // User code starts here

    String[] names = {"AlarmSource","AlarmEntity","AlarmSeverity","AlarmPreviousSeverity", "AlarmCreateTime", "AlarmModTime", "AlarmCategory", "AlarmAssignedTo","AlarmUserProperties"};//No I18N

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

	private AlarmEntry entry = null;
	private String instrClassName = "com.adventnet.nms.jmxagent.AlarmEntry"; // no i18n
	private String[] indexNames = new String[]{"AlarmSource","AlarmEntity"}; // no i18n

	/**
	 * To get the index column names of the table
	 * @retrun An array of String containing the Index names
	 */
	public String[] getIndexNames()
	{
		return indexNames;
	}
	MBeanServer server = null;
	private boolean[] externalIndexFlags = new boolean[]{false, false};

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
	 * Initialises the AlarmTable 
	 */
	public AlarmTable()
	{
		table = new Hashtable();
	}
	public AlarmTable(AdventNet_WebNMS_MIB_JMX agentRef)
	{
		agentName = agentRef; 
		table = new Hashtable();

		// User code starts here
		initPSID();
		// User code ends here

	}





	public CompositeData getFirstEntry()
	{


		// User code starts here
		if(!agentName.initAlert())
	    return null;

	String keys[] = getFirstAlert();

	if(keys == null)
	    return null;

	String source = keys[0];

	//String ownerName = keys[1];

	String entity = keys[1];
        Alert alert1 = new Alert();

	alert1.setSource(source);

	//alert1.setOwnerName(ownerName);

	alert1.setEntity(entity);

	Alert alert2 = new Alert();

	alert2.setModTime(System.currentTimeMillis());

	Vector alerts = null;

        try
	{
	    alerts = agentName.alertAPI.getAlerts(alert1, alert2);

	    if(alerts != null)
		return makeComData((Alert)alerts.elementAt(0));
	}
	catch(Exception e)
	{
	    return null;
	}
		// User code ends here
		return null;
	}
	public CompositeData getEntry(Object[] indexObjects)
	{

		// User code starts here
		  if(!agentName.initAlert())
	    return null;

	String source =  indexObjects[0].toString();

        //String ownerName =  indexObjects[1].toString();

        String entity =  indexObjects[1].toString();

        Alert alert1 = new Alert();

	alert1.setSource(source);

	//alert1.setOwnerName(ownerName);

	alert1.setEntity(entity);

	Alert alert2 = new Alert();

	alert2.setModTime(System.currentTimeMillis());

	Vector alerts = null;

        try
	{
	    alerts = agentName.alertAPI.getAlerts(alert1, alert2);

	    if(alerts != null)
		return makeComData((Alert)alerts.elementAt(0));
   	}
	catch(Exception e)
	{
	    return null;
	}


		// User code ends here
		return null;
	}
	public CompositeData getNextEntry(Object[] indexObjects)
	{

		// User code starts here
		 if(!agentName.initAlert())
	    return null;

	String previousKeys[] = {  indexObjects[0].toString(),  indexObjects[1].toString()};
	String keys[] = getNextAlert(previousKeys);

	if(keys == null)
	    return null;

	String source = keys[0];

	//String ownerName = keys[1];

	String entity = keys[1];

        Alert alert1 = new Alert();

	alert1.setSource(source);

//	alert1.setOwnerName(ownerName);

	alert1.setEntity(entity);

	Alert alert2 = new Alert();

	alert2.setModTime(System.currentTimeMillis());

	Vector alerts = null;

        try
	{
	    alerts = agentName.alertAPI.getAlerts(alert1, alert2);

	    if(alerts != null)
		return makeComData((Alert)alerts.elementAt(0));
	}
	catch(Exception e)
	{
	    return null;
	}


		// User code ends here
		return null;
	}
	public int totalRows()
	{

		// User code starts here
		/*
		return -1;
		*/
		if(agentName.initAlert())
	{
	    try
	    {
		return agentName.alertAPI.getTotalAlertCount();
	    }
	    catch(Exception e)
	    {
		return 0;
	    }
	}

	return 0;
		// User code ends here
		//return -1;
	}
	public List getEntries(int startIndex, int endIndex)
	{

		// User code starts here
		/*
		return null;
		*/
		 if(!agentName.initAlert())
	    return null;

        ArrayList arrayList = new ArrayList();

        boolean firstAlert = true;

	String[] keys = null;

	int totalAlerts = totalRows();

        for(int i = 0; i < totalAlerts; i++)
	{
	    if(firstAlert)
	    {
		firstAlert = false;

		keys = getFirstAlert();
	    }
	    else
		keys = getNextAlert(keys);

	    //Checking the range
	    if((i+1 >= startIndex) && (i+1 <= endIndex))
	    {
		Object[] index = new Object[]{keys[0], keys[1]};

		arrayList.add(index);
	    }

	    // checking if the upper limit is exceeded, if so break
	    if(i+1 == endIndex)
		break;

	}

        return arrayList;
		// User code ends here
	//	return null;
	}
	public void addRow(Object[] indexObjects, CompositeData entry) throws Exception
	{	}
	public void modifyRow(Object[] indexObjects, CompositeData entry) throws Exception
	{	}
	public void deleteRow(Object[] indexObjects) throws Exception
	{	}

	/**
	 * Returns the TabularData
	 * @return TabularData containing the rows corresponding the AlarmTable
	 */
	public TabularData getAlarmTable()
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
	public void setAlarmTable(TabularData data) throws Exception
	{
		AgentException ae = null;

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();
			CompositeData comp = data.getRow(index);

			if(table != null)
				entry = (AlarmEntry)Utilities.getEntryFromCompositeData(table, comp, indexNames, instrClassName);
			else if(vec != null)
				entry = (AlarmEntry)Utilities.getEntryFromCompositeData(vec, comp, indexNames, instrClassName);

			if(comp.getOperationType().equals(CompositeData.CREATED))
			{//create new entry

			if(entry != null)
				throw new AgentException("Row already exist",CommonUtils.ROWCREATIONFAILED); // no i18n
				entry = new AlarmEntry();

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
						throw new AgentException("Invalid Index", CommonUtils.INVALIDINDEX); // no i18n
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
     * Getting all the alerts in the NMS Alert table and using the alerts,
     * forming the array of CompositeData.Using the CompositeData forming
     * the TabularData and returning the TabularData
     */
    TabularData getTable()
    {

        if(!agentName.initAlert())
	    return null;

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
	    agentName.agentErr.fail("Exception in getTable(): ", e);//No I18N
	    return null;
	}

        try
	{

	    int numalerts = agentName.alertAPI.getTotalAlertCount();

	    CompositeData[] comps = new CompositeData[numalerts];

	    int count = 0;

	    Alert alert = null;

	    String entity = "";//No I18N

	    String ownerName ="";//No I18N

	    while(true)
	    {

		if(alert == null)
		{
		    try
		    {
			/**
			 * getting the oldest/first alert in the database which has the least recent mod time
			 */
			alert = agentName.alertAPI.getOldestModifiedAlert();
		    }
		    catch(Exception e)
		    {
			agentName.agentErr.fail("exception ",e);//No I18N
		    }
		}
		else
		{
		    try
		    {
			entity =  alert.getEntity();

			ownerName = alert.getOwnerName();

			/**
			 * getting the next alert from the database using the modified time as criteria
			 */
			if(ownerName != null)
			    alert = agentName.alertAPI.getNextAlertBasedOnModtime(entity+"\t"+ownerName);//No I18N
			else
			    alert = agentName.alertAPI.getNextAlertBasedOnModtime(entity);
		    }
		    catch(Exception e)
		    {
			agentName.agentErr.fail("exception",e);//No I18N
		    }
		}

		if (alert == null)
		    break;

		comps[count++] =  makeComData(alert);

		if(count == numalerts)
		    break;

	    }//end of while

	    TabularParameterInfo tinfo = new TabularParameterInfo(null,null,null,null,null,
								  parameterInfo,
								  indexNames);

	    td =  new TabularData(tinfo, comps);
	}
        catch(Exception e)
	{
	    agentName.agentErr.fail(" Exception in getTable(): ",e);//No I18N
	}

	return td;

    }//end of getTable
    //PreparedStatementIDs

    //int psForSelectMINOwnerName;

    int psForSelectMINSource;

    int psForSelectMINEntity;

    int psForSelectNEXTOwnerName;

    int psForSelectNEXTSource;

    int psForSelectNEXTEntity;
     //Initialize PreparedStatementIDs

    private void initPSID()
    {
	//psForSelectMINOwnerName = agentName.rlAPI.getPreparedStatementID("SELECT MIN(" + RelationalUtil.getAlias("ownerName") + ") FROM Alert");//No I18N

	psForSelectMINSource = agentName.rlAPI.getPreparedStatementID("SELECT MIN(" + RelationalUtil.getAlias("source") + ") FROM Alert ");//No I18N

	psForSelectMINEntity = agentName.rlAPI.getPreparedStatementID("SELECT MIN(" + RelationalUtil.getAlias("entity") + ") FROM Alert where " + RelationalUtil.getAlias("source") + "=?");//No I18N

	psForSelectNEXTOwnerName = agentName.rlAPI.getPreparedStatementID("SELECT MIN(" + RelationalUtil.getAlias("ownerName") + ") FROM Alert where " + RelationalUtil.getAlias("ownerName") + ">?");//No I18N

	psForSelectNEXTSource = agentName.rlAPI.getPreparedStatementID("SELECT MIN(" + RelationalUtil.getAlias("source") + ") FROM Alert where " + RelationalUtil.getAlias("source") + ">?");//No I18N

	psForSelectNEXTEntity = agentName.rlAPI.getPreparedStatementID("SELECT MIN(" + RelationalUtil.getAlias("entity") + ") FROM Alert where " + RelationalUtil.getAlias("source") + "=? AND " + RelationalUtil.getAlias("entity") + ">?");//No I18N
    }
    private String[] getFirstAlert()
    {
	String[] keysForFirstAlert = new String[2];

	ResultSet rs = null;

	PreparedStatementWrapper pstat = null;

	PreparedStatement pstatement = null;

	try
	{
	    try
	    {
		/*try
		{
		    //SELECT MIN(ONWERNAME)

		    pstat = agentName.rlAPI.fetchPreparedStatement(psForSelectMINOwnerName);

		    pstatement = pstat.getPreparedStatement();

		    rs = agentName.rlAPI.executeQuery(pstatement);

		    rs.next();

		    keysForFirstAlert[1] = (String)rs.getObject(1);
		}
		finally
		{
		    try{
			rs.close();
		    }catch(Exception e){}
http://ismp-build/php/download/1466f836f17657/AlarmTable.java
		    agentName.rlAPI.returnPreparedStatement(pstat);
		}*/

		//SELECT MIN(SOURCE) WHERE OWNERNAME=""//No I18N

		pstat = agentName.rlAPI.fetchPreparedStatement(psForSelectMINSource);

		pstatement = pstat.getPreparedStatement();

		//pstatement.setString(1, keysForFirstAlert[1]);

		rs = agentName.rlAPI.executeQuery(pstatement);

		rs.next();

		keysForFirstAlert[0] = (String)rs.getObject(1);
	    }
	    finally
	    {
		try{
		    rs.close();
		}catch(Exception e){}

		agentName.rlAPI.returnPreparedStatement(pstat);
	    }

	    //SELECT MIN(ENTITY) WHERE OWNERNAME="" AND SOURCE=""//No I18N

	    pstat = agentName.rlAPI.fetchPreparedStatement(psForSelectMINEntity);

	    pstatement = pstat.getPreparedStatement();

	    pstatement.setString(1, keysForFirstAlert[0]);

	   // pstatement.setString(2, keysForFirstAlert[0]);

	    rs = agentName.rlAPI.executeQuery(pstatement);

	    rs.next();

	    keysForFirstAlert[1] = (String)rs.getObject(1);
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

	return keysForFirstAlert;
    }


    private String[] getNextAlert(String[] previousKeys)
    {
	String[] keysForNextAlert = new String[2];

	keysForNextAlert[0] = previousKeys[0];

	keysForNextAlert[1] = previousKeys[1];

	ResultSet rs = null;

	PreparedStatementWrapper pstat = null;

	PreparedStatement pstatement = null;

	try
	{
	    //Next ENTITY
	    try
	    {
		//SELECT MIN(ENTITY) WHERE OWNERNAME="" AND SOURCE="" AND ENTITY>""//No I18N

		pstat = agentName.rlAPI.fetchPreparedStatement(psForSelectNEXTEntity);

		pstatement = pstat.getPreparedStatement();

		pstatement.setString(1, previousKeys[0]);

		pstatement.setString(2, previousKeys[1]);

	//	pstatement.setString(3, previousKeys[2]);

		rs = agentName.rlAPI.executeQuery(pstatement);

		rs.next();

		keysForNextAlert[1] = (String)rs.getObject(1);
	    }
	    finally
	    {
		try{
		    rs.close();
		}catch(Exception e){}

		agentName.rlAPI.returnPreparedStatement(pstat);
	    }

	    if(keysForNextAlert[1] == null)
	    {
		//Next SOURCE
		try
		{
		    //SELECT MIN(SOURCE) WHERE OWNERNAME="" AND SOURCE>"";//No I18N

		    pstat = agentName.rlAPI.fetchPreparedStatement(psForSelectNEXTSource);

		    pstatement = pstat.getPreparedStatement();

		    pstatement.setString(1, previousKeys[0]);

		    //pstatement.setString(2, previousKeys[0]);

		    rs = agentName.rlAPI.executeQuery(pstatement);

		    rs.next();

		    keysForNextAlert[0] = (String)rs.getObject(1);

		}
		finally
		{
		    try{
			rs.close();
		    }catch(Exception e){}

		    agentName.rlAPI.returnPreparedStatement(pstat);
		}

		if(keysForNextAlert[0] == null)
		{
		    //Next OWNERNAME
		   /* try
		    {

			//SELECT MIN(OWNERNAME) WHERE OWNERNAME>"";//No I18N

			pstat = agentName.rlAPI.fetchPreparedStatement(psForSelectNEXTOwnerName);

			pstatement = pstat.getPreparedStatement();

			pstatement.setString(1, previousKeys[1]);

			rs = agentName.rlAPI.executeQuery(pstatement);

			rs.next();

			keysForNextAlert[1] = (String)rs.getObject(1);

			if(keysForNextAlert[1] == null)
			    return null;
		    }
		    finally
		    {
			try{
			    rs.close();
			}catch(Exception e){}

			agentName.rlAPI.returnPreparedStatement(pstat);
		    }*/

		    try
		    {
			//SELECT MIN(SOURCE) WHERE OWNERNAME="";//No I18N

			pstat = agentName.rlAPI.fetchPreparedStatement(psForSelectMINSource);

			pstatement = pstat.getPreparedStatement();

			pstatement.setString(1, keysForNextAlert[1]);

			rs = agentName.rlAPI.executeQuery(pstatement);

			rs.next();

			keysForNextAlert[0] = (String)rs.getObject(1);

		    }
		    finally
		    {
			try{
			    rs.close();
			}catch(Exception e){}

			agentName.rlAPI.returnPreparedStatement(pstat);
		    }
		}

		try
		{
		    //SELECT MIN(ENETITY) WHERE OWNERNAME="" AND SOURCE="";//No I18N

		    pstat = agentName.rlAPI.fetchPreparedStatement(psForSelectMINEntity);

		    pstatement = pstat.getPreparedStatement();

		    pstatement.setString(1, keysForNextAlert[0]);


		    rs = agentName.rlAPI.executeQuery(pstatement);

		    rs.next();

		    keysForNextAlert[1] = (String)rs.getObject(1);
		}
		finally
		{
		    try{
			rs.close();
		    }catch(Exception e){}

		    agentName.rlAPI.returnPreparedStatement(pstat);
		}
	    }
	}
	catch(Exception e)
	{
	    return null;
	}

	return keysForNextAlert;
    }
    /**
     * We fill in the values for the alert in the composite data.
     * Forming the composite data for each alert and returning it
     */
    private CompositeData makeComData(Alert alert)
    {

        values = new Object[names.length];

	values[0] = alert.getSource();

	/*values[1] = alert.getOwnerName();
	if(values[1] == null)
	{
	    values[1] = "NULL";//No I18N
	}*/

	values[1] = alert.getEntity();

	values[2] = new Integer(alert.getSeverity());

	values[3] = new Integer(alert.getPreviousSeverity());

	values[4] = new Long(alert.getCreateTime());

	values[5] = new Long(alert.getModTime());

	values[6] = alert.getCategory();

	values[7] = alert.getWho();
	if(values[7] == null)
	{
	    values[7] = " ";//No I18N
	}

	values[8] = agentName.getPropValues(alert.getUserProperties(),agentName.alertUserPropNames);

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


