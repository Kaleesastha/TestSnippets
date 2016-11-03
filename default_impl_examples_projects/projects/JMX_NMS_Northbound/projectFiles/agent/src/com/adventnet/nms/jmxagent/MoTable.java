 //$Id: MoTable.java,v 1.7 2007/08/09 20:43:47 tinku Exp $
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
 * @Version :  6.0.0 Fri Mar 09 14:26:06 IST 2007
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
//added by senthil
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.*;
import com.adventnet.nms.store.relational.*;
import java.sql.*;
import com.adventnet.management.transaction.*;
	// User code ends here




/**
 * Handles all requests under
 * moTable group
 */


public class MoTable implements JmxTableModelListener{

	private AdventNet_WebNMS_MIB_JMX agentName = null;
	// User code starts here
	String[] names = {"MoNameIndex","MoOwnerName","MoType","MoFailureCount","MoFailureThreshold","MoManaged","MoStatus","MoStatusChangeTime","MoStatusUpdateTime","MoPollInterval","MoUserProperties","MoDerivedProperties","InheritingOid1","InheritingTableName1"};//No I18N
    Object[] values = null;
     String usrObjTypes[] = null;
    String dpNames[] = null;
    Hashtable hash = new Hashtable();	

    /*
     * Initializing the user object properties
     */
    private void initUsrObjTypesAndProps()
    {
	Vector usrObjTab = agentName.getMoDerivedPropNameTable().getTableVector();
	int usrObjTabSize = 0;
	if(usrObjTab != null)
	{
	    usrObjTabSize = usrObjTab.size();
	    usrObjTypes = new String[usrObjTabSize];
	    dpNames = new String[usrObjTabSize];

	    for(int i = 0;i < usrObjTabSize;i++)
	    {
		MoDerivedPropNameEntry usrObjEntry = (MoDerivedPropNameEntry)usrObjTab.elementAt(i);
		try
		{
		    usrObjTypes[i] = usrObjEntry.getObjClassName();
		    dpNames[i] = usrObjEntry.getDerivedPropNames();
		}
		catch(Exception e) {}
	    }
	}
    }
     /*
     * Getting the values of all derived properties
     */
    private String getDrPropValues(int i,Properties properties)
    {
	String prValues = "";
	StringTokenizer st1 = new StringTokenizer(dpNames[i],",");//No I18N
	int noOfTokens = st1.countTokens();
	String propNameArr[] = new String[noOfTokens];
	int j = 0;

	while(st1.hasMoreTokens())
	    propNameArr[j++] = st1.nextToken();

	for(int k = 0;k < noOfTokens;k++)
	{
	    if(!prValues.trim().equals("")) prValues += ",";
	    prValues += propNameArr[k] + "=" + properties.getProperty(propNameArr[k]);//No I18N
	}

	int index = getIndexOfSuperType(usrObjTypes[i]);
	if(index == -1)
	    return prValues;
	else
	    return prValues + "," + getDrPropValues(index,properties);//No I18N
    }

    /*
     * Getting the index of given object's superclass
     */
    private int getIndexOfSuperType(String objType)
    {
	String superClassName = null;
	while(true)
	{

	    try
	    {
		superClassName = Class.forName(objType).getSuperclass().getName();
	    }
	    catch(Exception e)
	    {
		return -1;
	    }

	    if(superClassName.equals("com.adventnet.nms.topodb.ManagedObject")) //No I18N
		return -1;

	    for(int i = 0; i < usrObjTypes.length;i++)
		if(usrObjTypes[i].equals(superClassName))
		    return i;
	    objType = superClassName;
	}
    }
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

	private MoEntry entry = null;
	private String instrClassName = "com.adventnet.nms.jmxagent.MoEntry"; //No I18N
	private String[] indexNames = new String[]{"MoNameIndex"}; //No I18N

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
	 * Initialises the MoTable
	 */
	public MoTable()
	{
		table = new Hashtable();
	}
	public MoTable(AdventNet_WebNMS_MIB_JMX agentRef)
	{
		agentName = agentRef;
		table = new Hashtable();
                //User code starts here
                initPSID();
                //User code ends here
	}



// User code starts here
     
private CompositeData getCompositeData(ManagedObject mo,String moName) throws Exception
    {
        
        try{

        values = new Object[names.length];
	values[0] = moName;
	values[1] = mo.getOwnerName();
        if(values[1]==null)
            {
                values[1]="NULL"; //No I18N
            }
	values[2] = mo.getType();
	values[3] = new Integer(mo.getFailureCount());
	values[4] = new Integer(mo.getFailureThreshold());
	if(mo.getManaged())
	    values[5] = new Integer(1);
	else
	    values[5] = new Integer(2);
	values[6] = new Integer(mo.getStatus());
	values[7] = new Long(mo.getStatusChangeTime());
	values[8] = new Long(mo.getStatusUpdateTime());
	values[9] = new Long(mo.getPollInterval());
	values[10] = agentName.getPropValues(mo.getUserProperties(),agentName.moUserPropNames);

	//fill up the dr porps
	String propValues = "";

	for(int i = 0;i < usrObjTypes.length; i++)
	{
	    String uType = usrObjTypes[i];
	    uType = uType.substring(uType.lastIndexOf('.')+1,uType.length());
	    if(uType.equals(mo.getClassname()))
	    {
		propValues = getDrPropValues(i,mo.getProperties());
		break;
	    }
	}
	values[11] = propValues;

	String childOID = agentName.getChildTableOID(mo.getClassname(),"com.adventnet.nms.topodb.ManagedObject"); //No I18N
	values[12] = childOID;
	values[13] = agentName.getChildTableName(childOID);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

	try
	{
           
	    return new CompositeData(null, names, values);
	}
	catch(Exception e)
	{
            
            e.printStackTrace();
           
	    return null;
	}
    }
    // User code ends here

	public CompositeData getFirstEntry()
	{
            // User code starts here
            if(!agentName.initTopo())
            {
                return null;
            }
            
            String[] name = {""};//No I18N  
            initUsrObjTypesAndProps();          
            return getFirstMo(name);

            //User code starts here
	     
	}
	
	public CompositeData getEntry(Object[] indexObjects)
	{
            //User code starts here
            if(!agentName.initTopo())
            {
                return null;
            }           
            String[] name = {(String)indexObjects[0]};  
            initUsrObjTypesAndProps();          
            return getManagedObject(name);
	
            //User code ends here
	}
	public CompositeData getNextEntry(Object[] indexObjects)
	{
            //User code starts here
            if(!agentName.initTopo())
            {
                return null;
            }            
            String[] name = {(String)indexObjects[0]};  
            initUsrObjTypesAndProps();      
            return getNextMo(name);
            //User code ends here
        }


	public int totalRows()
	{
		//User code starts here
		if(agentName.initTopo())
		{
			try
			{
				int count= agentName.topoAPI.getNumObjects();
                                
                                return count;
			}
			catch(Exception e)
			{
				return 0;
			}
		}
		//User code ends here

		return 0;
	}
	public List getEntries(int startIndex, int endIndex)
	{
            //User code starts here
            if(agentName.initTopo())
            {
                initUsrObjTypesAndProps();
                int noOfObj=0;
                ArrayList arrayList = new ArrayList();
                try
                {
                     noOfObj = agentName.topoAPI.getNumObjects();
                }
                catch(Exception e)
                {
                    return null;
                }
                String[] name = {""};//No I18N
                
                for(int i = 0; i < noOfObj; i++)
                {
                    if(name[0].trim().equals(""))//No I18N
                    {
                        getFirstMo(name);
                    }
                    else
                    {
                        getNextMo(name);
                    }                    
                    if((i+1 >= startIndex) && (i+1 <= endIndex))
                    {
                        Object[] indx = new Object[]{name[0]};
		    arrayList.add(indx);
		    if(i+1 == endIndex) break;
                    }
                }
                return arrayList;
            }	
		
            //User code ends here
		return null;			
	}
	public void addRow(Object[] indexObjects, CompositeData entry) throws Exception
	{	}
	public void modifyRow(Object[] indexObjects, CompositeData entry) throws Exception
	{	}
	public void deleteRow(Object[] indexObjects) throws Exception
	{	}

	/**
	 * Returns the TabularData
	 * @return TabularData containing the rows corresponding the MoTable
	 */
	public TabularData getMoTable()
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
	public void setMoTable(TabularData data) throws Exception
	{
		AgentException ae = null;

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();
			CompositeData comp = data.getRow(index);

			if(table != null)
				entry = (MoEntry)Utilities.getEntryFromCompositeData(table, comp, indexNames, instrClassName);
			else if(vec != null)
				entry = (MoEntry)Utilities.getEntryFromCompositeData(vec, comp, indexNames, instrClassName);

			if(comp.getOperationType().equals(CompositeData.CREATED))
			{//create new entry

			if(entry != null)
				throw new AgentException("Row already exist",CommonUtils.ROWCREATIONFAILED); //No I18N
				entry = new MoEntry();

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
						throw new AgentException("Invalid Index", CommonUtils.INVALIDINDEX); //No I18N
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
    int psSelectMoID1;
    int psSelectMoID2;
   
    
    void initPSID()
    {
        psSelectMoID1 = agentName.rlAPI.getPreparedStatementID("SELECT MIN("+RelationalUtil.getAlias("name") +") FROM ManagedObject" );//No I18N

        psSelectMoID2=agentName.rlAPI.getPreparedStatementID("SELECT MIN("+RelationalUtil.getAlias("name") +") FROM ManagedObject WHERE "+RelationalUtil.getAlias("name")+ " >?"); //No I18N

        
    }
    
    public CompositeData getFirstMo(String[] name)
    {
        ResultSet rs=null;
        PreparedStatementWrapper pstatForMo= null;
        PreparedStatement pstatementForMo = null;
        try
        {
            pstatForMo = agentName.rlAPI.fetchPreparedStatement(psSelectMoID1);
            pstatementForMo = pstatForMo.getPreparedStatement();
            rs = agentName.rlAPI.executeQuery(pstatementForMo);
            rs.next();
            name[0] = rs.getString(1);
            
            return getManagedObject(name);
            
            
        }
        catch(Exception e)
        {
            return null;
        }
        finally
        {
            try
            {
                if(rs!=null)
                {
                    rs.close();
                }
            }
            catch(Exception e)
            {
                
            }
            agentName.rlAPI.returnPreparedStatement(pstatForMo); 
        }
        
    }
    public CompositeData getManagedObject(String[] name)
    {
        ManagedObject node=null;
        try
        {
            node=(ManagedObject)agentName.topoAPI.getByName(name[0]);
            
            if(node!=null)
            {
                
                return getCompositeData(node,name[0]);
            }
            return null;             
        }
        catch(Exception e)
        {
            return null;
            
        }

    }
    
    public CompositeData getNextMo(String[] name)
    {

        ResultSet rs=null;
        PreparedStatementWrapper pstatForMo= null;
        PreparedStatement pstatementForMo = null;
        try
        {
            pstatForMo = agentName.rlAPI.fetchPreparedStatement(psSelectMoID2);
            pstatementForMo = pstatForMo.getPreparedStatement();
            pstatementForMo.setString(1,name[0]);
            rs = agentName.rlAPI.executeQuery(pstatementForMo);
            rs.next();
            name[0] = rs.getString(1);
            

            return getManagedObject(name);
            
            
        }
        catch(Exception e)
        {
            return null;
        }
        finally
        {
            try
            {
                if(rs!=null)
                {
                    rs.close();
                }
            }
            catch(Exception e)
            {
                
            }
            agentName.rlAPI.returnPreparedStatement(pstatForMo); 
        }
    }
    
   
    // User code ends here


}




