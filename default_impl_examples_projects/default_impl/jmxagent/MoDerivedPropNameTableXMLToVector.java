 //$Id: MoDerivedPropNameTableXMLToVector.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
import com.adventnet.utilities.xml.dom.*;
import com.adventnet.utilities.xml.sax.*;
import com.adventnet.utilities.xml.indenter.*;
import com.adventnet.snmp.snmp2.agent.*;



/**
 * This class is used for persisting the table values into a xml file
 */
public class MoDerivedPropNameTableXMLToVector implements UpdateListener
{

	final static int INDEXNUM = 1;
	final static int OBJCLASSNAME = 2;
	final static int DERIVEDPROPNAMES = 3;
	final static int TABLEOID = 4;
	final static int TABLENAME = 5;
	final static String [] columnNames = {"indexNum" , "objClassName" , "derivedPropNames" , "tableOid" , "tableName" };//No I18N

	private String dirStr = null;
	private String fileName = null;
	private String name = null;

	private Vector tableVector = null;
	private MoDerivedPropNameTable handler= null;


	/**
	 * Returns the column Id corresponding to the column name
	 * @param name The column name for which the column Id is required
	 */
	public static int getColumnId(String name)
	{

		for(int i=0;i<columnNames.length;i++){
			if(columnNames[i].equals(name))
				return (i+1);
		}
		return -1;
	}

	/**
	 * Returns the column value
	 * @param entry The MoDerivedPropNameEntry corresponding to the column value requested
	 * @param columnName The column name for which the value is requested
	 */
	public String getColumnValue(MoDerivedPropNameEntry entry, String columnName)
	{
		String toRet = null;
		int columnId = getColumnId(columnName);

		try{
			switch(columnId){
			case INDEXNUM:
				toRet = ""+entry.getIndexNum();//No I18N
				break;
			case OBJCLASSNAME:
				toRet = ""+entry.getObjClassName();//No I18N
				break;
			case DERIVEDPROPNAMES:
				toRet = ""+entry.getDerivedPropNames();//No I18N
				break;
			case TABLEOID:
				toRet = ""+entry.getTableOid();//No I18N
				break;
			case TABLENAME:
				toRet = ""+entry.getTableName();//No I18N
				break;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception"+e.getMessage());  //No I18N
		}
		return toRet;
	}


	/**
	 * Constructor which takes the directory and the file name as parameter
	 * @param dirStr The directory location of the xml file
	 * @param filename The name of the xml file
	 */
	public MoDerivedPropNameTableXMLToVector(String dirStr, String fileName)
	{
		if(dirStr == null)
			this.dirStr = "xmlToVector";//No I18N
		else
			this.dirStr = dirStr;
		File f = new File(dirStr);
		if(!f.exists()) // If the Directory does not exist, create it.
			f.mkdirs();
		 if (fileName == null)
			 this.fileName = "MoDerivedPropNameTable.xml";//No I18N
		else
			this.fileName = fileName;
		this.name = dirStr + File.separator + fileName;
		// User code starts here
	this.name = dirStr + File.separator + fileName;
	// User code ends here
		tableVector = new Vector();
	}



	/**
	 * Reads the value from the xml file and updates the table vector
	 */
	public void readFromFile() throws Exception
	{
			MoDerivedPropNameEntry  entry = null;

			new RandomAccessFile(name, "r");//No I18N

			XMLDataReader xmlReader = new XMLDataReader(name.trim(), false);
			Vector children = xmlReader.getRootChildNodes();

			for(Enumeration en = children.elements(); en.hasMoreElements();){

				XMLNode rowNode = (XMLNode)en.nextElement();

				if(rowNode.getNodeType() == XMLNode.ELEMENT){
					if(rowNode.getNodeName().equalsIgnoreCase("row")) //No I18N
					{
						entry = new MoDerivedPropNameEntry();
						for(Enumeration e = rowNode.children();e.hasMoreElements();){

							XMLNode columnNode = (XMLNode)e.nextElement();
							Hashtable indhash = columnNode.getAttributeList();

							String columnName = (String)indhash.get("name");//No I18N
							String columnValue = (String)indhash.get("value");//No I18N

					int columnId = getColumnId(columnName);
					switch(columnId){
						case INDEXNUM:
							entry.indexNum = new Integer(columnValue);
							break;
						case OBJCLASSNAME:
							entry.objClassName = new String(columnValue);
							break;
						case DERIVEDPROPNAMES:
							entry.derivedPropNames = new String(columnValue);
							break;
						case TABLEOID:
							entry.tableOid = new String(columnValue);
							break;
						case TABLENAME:
							entry.tableName = new String(columnValue);
							break;
							} //end of switch
					} // End While (strtok.hasMoreElements())

						tableVector.addElement(entry);
				}
			} // End of if loop
		}
	}



	/**
	 * Writes the table values read from the table vector into the xml file
	 */
	public void writeIntoFile()
	{

		MoDerivedPropNameEntry ent = null;

		byte[] date = null;

		if(handler != null){
			tableVector = handler.getTableVector();
		}
		XMLNode rootNode = new XMLNode();//No I18N
		rootNode.setNodeName("Table");//No I18N
		rootNode.setNodeType(XMLNode.ELEMENT);

		for(int i=0;i<tableVector.size();i++){
			XMLNode rowNode = new XMLNode();
			rowNode.setNodeName("row");//No I18N
			rowNode.setNodeType(XMLNode.ELEMENT);
			rootNode.addChildNode(rowNode);

			ent = (MoDerivedPropNameEntry)tableVector.elementAt(i);
			for(int j=0;j<columnNames.length;j++){
				XMLNode columnNode = new XMLNode();

				columnNode.setNodeName("column");//No I18N
				columnNode.setNodeType(XMLNode.ELEMENT);
				rowNode.addChildNode(columnNode);

				columnNode.setAttribute("name", columnNames[j]);//No I18N
				columnNode.setAttribute("value", getColumnValue(ent, columnNames[j]));//No I18N
			}
		}
		XMLDataWriter writer = new XMLDataWriter(name, rootNode);
	}



	/**
	 * Sets the table handler
	 */
	public void setTable(MoDerivedPropNameTable handler)
	{
		this.handler = handler;
		// Sort the entries read from the file
		this.handler.setTableVector(tableVector);
	}



}


