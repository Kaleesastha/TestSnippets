//$Id: AlertTable.java,v 1.3 2007/07/03 07:01:56 barathv Exp $
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
 * @Version :  6.0.0 Wed Jun 13 11:34:56 IST 2007
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
import java.sql.*;
	// User code ends here





/** 
 * Handles all requests under 
 * alertTable group
 */ 


public class AlertTable implements JmxTableModelListener{

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

	private AlertEntry entry = null;
	private String instrClassName = "com.adventnet.nms.jmxagent.AlertEntry"; // no i18n
	private String[] indexNames = new String[]{"AlertEntity"}; // no i18n

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
	 * Initialises the AlertTable 
	 */
	public AlertTable()
	{
		table = new Hashtable();
	}
	public AlertTable(AdventNet_WebNMS_MIB_JMX agentRef)
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
		if(!agentName.initAlert())
	    return null;

         Alert alert = null;

         //to get the first alert in the database
         try
             {
                 alert = agentName.alertAPI.getOldestModifiedAlert();
             }
         catch(Exception e)
             {
                 return null;
             }
         return makeComData(alert);
		// User code ends here
		//return null;
	}
	public CompositeData getEntry(Object[] indexObjects)
	{

		// User code starts here
		/*
		return null;
		*/
		if (!agentName.initAlert())
	    return null;

        String entity = indexObjects[0].toString();
        Alert alert = null;
        try
            {
                        alert = agentName.alertAPI.getAlert(entity);

            }
	catch(Exception e)
            {
                return null;
            }
        return makeComData(alert);

    			// User code ends here
		//return null;
	}
	public CompositeData getNextEntry(Object[] indexObjects)
	{

		// User code starts here
		/*
		return null;
		*/
		 if(!agentName.initAlert())
	    return null;

         String entity = indexObjects[0].toString();
	 //String ownerName = indexObjects[1].toString();
         Alert alert = null;

        try
              {
                        alert = agentName.alertAPI.getNextAlertBasedOnModtime(entity);

                 return makeComData(alert);
            }
	 catch(Exception e)
             {
                  return null;
             }
		// User code ends here
		//return null;
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
	    catch(Exception we)
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
        Alert alert = null;
        boolean firstAlert = true;
        String entity ="";//No I18N
        String[] names={"",""};//No I18N
        for(int i=0;i<totalRows();i++)
            {
                try
                    {
                        if(firstAlert)
                            {
                                alert = agentName.alertAPI.getOldestModifiedAlert();
                                firstAlert = false;
                            }
                        else
                            {
                                        alert = agentName.alertAPI.getNextAlertBasedOnModtime(entity);
                            }
                        entity = alert.getEntity();
                    }
                catch(Exception we)
                    {

                    }

                if((i+1 >= startIndex) && (i+1 <= endIndex))
                    {
                        Object[] indx = new Object[]{entity};
                        arrayList.add(indx);
                        /** checking if the upper limit is exceeded,if so break
                         */
                        if(i+1 == endIndex) break;
                    }

            }//for end
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
	 * @return TabularData containing the rows corresponding the AlertTable
	 */
	public TabularData getAlertTable()
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
	public void setAlertTable(TabularData data) throws Exception
	{
		AgentException ae = null;

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();
			CompositeData comp = data.getRow(index);

			if(table != null)
				entry = (AlertEntry)Utilities.getEntryFromCompositeData(table, comp, indexNames, instrClassName);
			else if(vec != null)
				entry = (AlertEntry)Utilities.getEntryFromCompositeData(vec, comp, indexNames, instrClassName);

			if(comp.getOperationType().equals(CompositeData.CREATED))
			{//create new entry

			if(entry != null)
				throw new AgentException("Row already exist",CommonUtils.ROWCREATIONFAILED); // no i18n
				entry = new AlertEntry();

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
						throw new AgentException("Invalid Index", CommonUtils.INVALIDINDEX);  // no i18n
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
    String[] names = {"AlertEntity","AlertCreateTime","AlertSource","AlertModTime", "AlertSeverity","AlertPreviousSeverity","AlertCategory","AlertUserProperties"};//No I18N

    Object[] values = null;
    /**
     *This method is invoked when any non-snmp adaptor is used.Getting all the alerts in the NMS Alert table and using
     *the alerts,forming the array of CompositeData.Using the CompositeData forming the TabularData and returning the
     *TabularData
     */
    TabularData getTable()
    {
        /** checking if the alertAPI handle is obtained or not
         */
        if(!agentName.initAlert())
	    return null;

        TabularData td = null;

        OpenMBeanParameterInfo[] parameterInfo = new OpenMBeanParameterInfo[names.length];

        String returnType = null;
        try
            {
                //to access the entry file
                /** getting the class name of the entry fle
                 */
                Class entryClassName = Class.forName(instrClassName);

                for(int i = 0; i < names.length; i++)
                    {

                        String methodName = "get" + names[i];//No I18N
                         /** getting the method names given in the entry file
                         */
                        Method method = entryClassName.getMethod(methodName,null);
                        /** getting the return type of each method in the entry file
                         */
                        returnType = method.getReturnType().getName();
                        parameterInfo[i] = new OpenMBeanParameterInfo(names[i],
                                                                      returnType,
                                                                      null,null,null);
                    }
            }

        catch(Exception e)
            {
                agentName.agentErr.fail("exception ", e);//No I18N
                return null;
            }

        try
            {
                /** getting the total number of alerts in the NMS
                 */
		int numalerts = agentName.alertAPI.getTotalAlertCount();
                CompositeData[] comps = new CompositeData[numalerts];
                int count = 0;
                Alert alert = null;
                String entity = "";//No I18N
                String ownerName ="";//No I18N
                while(true)
                    {

                        if(alert == null)
                            try
                                {
                                    /** getting the oldest/first alert in the database
                                        which has the least recent mod time
                                     */
                                    alert = agentName.alertAPI.getOldestModifiedAlert();
                                }
                            catch(Exception e)
                                {
                                    agentName.agentErr.fail("exception ",e);//No I18N
                                }
                        else
                            try
                                {
                                    /** obtaining the entity of the alert
                                     */
                                    entity =  alert.getEntity();
                                    /** obtaining the ownerName of the alert
                                     */
                                    ownerName = alert.getOwnerName();
                                    if(ownerName != null)
                                        {
                                            /** getting the next alert from the database using the modified time as criteria
                                             */
                                            alert = agentName.alertAPI.getNextAlertBasedOnModtime(entity+"\t"+ownerName);//No I18N
                                        }
                                    else
                                        alert = agentName.alertAPI.getNextAlertBasedOnModtime(entity);
                                }
                            catch(Exception e)
                                {
                                    agentName.agentErr.fail("exception",e);//No I18N
                                }
                        if (alert == null) break;


                        comps[count++] =  makeComData(alert);
                        if(count == numalerts) break;

                    }//end of while
                TabularParameterInfo tinfo = new TabularParameterInfo(null,null,null,null,null,
                                                                      parameterInfo,
                                                                      indexNames);

                td =  new TabularData(tinfo, comps);


            }
        catch(Exception e)
                {
                    agentName.agentErr.fail(" exception..",e);//No I18N
                }


            return td;

	}//end of getTable
	 /**
     * we fill in the values for the alert in the composite data.Forming the composite data for each alert and returning it
     */

     private CompositeData makeComData(Alert alert)
    {

        values = new Object[names.length];

         values[0] = alert.getEntity();

         /*values[1] = alert.getOwnerName();
         if(values[1] == null)
             {
                 values[1] = "NULL";//No I18N
             }*/

         values[1] = new Long(alert.getCreateTime());

         values[2] = alert.getSource();

         values[3] = new Long(alert.getModTime());

         values[4] = new Integer(alert.getSeverity());

         values[5] = new Integer(alert.getPreviousSeverity());

         values[6] = alert.getCategory();

         values[7] = agentName.getPropValues(alert.getUserProperties(),agentName.alertUserPropNames);

         try
             {
                 return new CompositeData(null, names, values);
             }
         catch(Exception e)
             {
                 return null;
             }
     }//end of compo data

	// User code ends here



}


