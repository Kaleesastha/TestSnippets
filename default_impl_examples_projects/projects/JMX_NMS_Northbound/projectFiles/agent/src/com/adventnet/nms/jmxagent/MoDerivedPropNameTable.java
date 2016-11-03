 //$Id: MoDerivedPropNameTable.java,v 1.3 2007/08/09 20:48:24 tinku Exp $
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
 * @Version :  6.0.0 Wed Feb 14 00:34:08 IST 2007
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
import com.adventnet.nms.util.*;
	// User code ends here



/**
 * Handles all requests under
 * moDerivedPropNameTable group
 */


public class MoDerivedPropNameTable{

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

	private MoDerivedPropNameEntry entry = null;
	private String instrClassName = "com.adventnet.nms.jmxagent.MoDerivedPropNameEntry";//No I18N
	private String[] indexNames = new String[]{"IndexNum"};//No I18N

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
	 * Initialises the MoDerivedPropNameTable
	 */
	public MoDerivedPropNameTable()
	{
		table = new Hashtable();
		// User code starts here
                MoDerivedPropNameTableXMLToVector xmlToVector = new MoDerivedPropNameTableXMLToVector(PureUtils.rootDir + "conf"+File.separator+"jmx_agent"+File.separator+"conf","MoDerivedPropNameTable.txt");//No I18N
		xmlToVector.setTable(this);
		try{
			xmlToVector.readFromFile();
		}catch(Exception e){
			
		}
	// User code ends here
		this.addUpdateListener(xmlToVector);
	}
	public MoDerivedPropNameTable(AdventNet_WebNMS_MIB_JMX agentRef)
	{
		agentName = agentRef;
		table = new Hashtable();
		// User code starts here
                MoDerivedPropNameTableXMLToVector xmlToVector = new MoDerivedPropNameTableXMLToVector(PureUtils.rootDir + "conf"+File.separator+"jmx_agent"+File.separator+"conf","MoDerivedPropNameTable.txt");//No I18N
		// User code ends here
		xmlToVector.setTable(this);
		try{
			xmlToVector.readFromFile();
		}catch(Exception e){
			//e.printStackTrace();
		}

		this.addUpdateListener(xmlToVector);
	}



	/**
	 * Returns the TabularData
	 * @return TabularData containing the rows corresponding the MoDerivedPropNameTable
	 */
	public TabularData getMoDerivedPropNameTable()
	{
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
	public void setMoDerivedPropNameTable(TabularData data) throws Exception
	{
		AgentException ae = null;

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();
			CompositeData comp = data.getRow(index);

			if(table != null)
				entry = (MoDerivedPropNameEntry)Utilities.getEntryFromCompositeData(table, comp, indexNames, instrClassName);
			else if(vec != null)
				entry = (MoDerivedPropNameEntry)Utilities.getEntryFromCompositeData(vec, comp, indexNames, instrClassName);

			if(comp.getOperationType().equals(CompositeData.CREATED))
			{//create new entry

			if(entry != null)
				throw new AgentException("Row already exist",CommonUtils.ROWCREATIONFAILED);//No I18N
				entry = new MoDerivedPropNameEntry();

				if(table != null)
					table.put(index, entry);
				else if(vec != null)
				vec.addElement(entry);
				for(Enumeration ce = comp.enumerate();ce.hasMoreElements();)
				{
					String key = (String)ce.nextElement();
						 // User code starts here
		    if((key.equals("DerivedPropNames")) && (!agentName.syntaxOK((String)comp.getDataItem(key))))//No I18N
		    {
			vec.remove(entry);
			throw new AgentException("",CommonUtils.BADVALUE);
		    }
		   	 // User code ends here
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
					     // User code starts here
			if((key.equals("DerivedPropNames")) && (!agentName.syntaxOK((String)comp.getDataItem(key))))//No I18N
			    throw new AgentException("", CommonUtils.BADVALUE);
			// User code ends here

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

		writeIntoFile();
	}
	protected UpdateListener listener = null;

	/**
	 * Adds the UpdateListener to the table.This will update the Table when the values are changed
	 */
	public void addUpdateListener(UpdateListener l){
		listener = l;
	}

	/**
	 * Removes the Update Listener
	 */	public void removeUpdateListener(){
		listener = null;
	}

	/**
	 * Writes the table values held in memory to a text file or xml file
	 */
	public void writeIntoFile() {
		if(listener != null){
			listener.writeIntoFile();
		}
	}



}





