//$Id: MibHandler.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $

package com.adventnet.nms.config;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;
import com.adventnet.snmp.ui.*;
import com.adventnet.snmp.beans.SnmpTarget;


public class MibHandler implements ActionListener
{
	private String key = "";

	private ConfigPanel configPanel = null;

	public ShowMibTree mibs = null;

	private MibHandlerListener mibHandlerListener = null;

	public String[] snmpDataTypes = {"DisplayString", "INTEGER","Integer32","Unsigned32","Gauge","Gauge32","Counter","Counter32","Counter64","TimeTicks","OCTET STRING","OBJECT IDENTIFIER","IpAddress","Opaque","BITS"};

	public MibHandler(ConfigPanel configPanel)
	{
		this.configPanel = configPanel;

		mibs = new ShowMibTree(configPanel);
		mibs.addMibLoadListener(this);
	}

	public void showMib(Container parent, String key)
	{
		this.key = key;

		mibs.setKey(key);
		mibs.showMib(parent);
	}

	public void addMibHandlerListener(MibHandlerListener mibHandlerListener)
	{
		this.mibHandlerListener = mibHandlerListener;
	}

	public void actionPerformed(ActionEvent ae)
	{
		String actionCommand = ae.getActionCommand().trim();

		if(actionCommand.equalsIgnoreCase("OK"))
		{
			actionPerformed();
		}
	}

	public Vector getIndexValues(String tableOID, String index)
	{
		try
		{
		Vector indexValues = null;

		tableOID += ".1.1";

		MibNode tableNode = getMibNode(tableOID);

		if(tableNode != null)
		{
			LeafSyntax lf =	tableNode.getSyntax();

			if(lf != null)
			{
				Vector snmpVars = lf.decodeInstanceString(index, mibs.getIndexesForTable(tableNode));

				if(snmpVars != null)
				{
					indexValues = new Vector();

					for(int i=0; i<snmpVars.size(); i++)
					{
						indexValues.addElement(((SnmpVar)snmpVars.elementAt(i)).toString());
					}
				}
			}
		}

		return indexValues;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	public MibNode getMibNode(String oid)
	{
		return mibs.getMibNode(oid);
	}

	public String getLabel(String oid)
	{
		String label = null;

		MibNode mibNode = getMibNode(oid);

		if(mibNode != null)
		{
			label = mibNode.getLabel();
		}

		return label;
	}

	public String getType(String oid)
	{
		LeafSyntax lf = getLeafSyntax(oid);

		if(lf != null)
			return String.valueOf(lf.getType());
		else
			return "-1";
	}

	public String getTypeName(String oid)
	{
		LeafSyntax lf = getLeafSyntax(oid);

		if(lf != null)
			return lf.toString();
		else
			return null;
	}

	public boolean isValidOIDValue(String oid, String value)
	{
		boolean isValid = false;

		LeafSyntax lf = getLeafSyntax(oid);

		SnmpVar snmpVar = null;

		if(lf != null)
		{
			try
			{
				snmpVar = SnmpVar.createVariable(value, lf.getType());
			}
			catch(SnmpException se){}
		}

		if (snmpVar != null)
		{
			isValid = true;
		}

		return isValid;
	}


	public boolean isValueInRange(String value, String oid)
	{
		LeafSyntax lf = getLeafSyntax(oid);
		try
		{
			SnmpVar snmpVar = lf.createVariable(value);
			if(snmpVar != null)
				return true;
		}
		catch(Exception ex)
		{
		}
		return false;

/*		if(lf != null)
		{
			System.out.println("---------------->       the range is " + Arrays.asList(lf.getRangeItems()));
		}
		return false;*/
	}

	public String getTypeDescription(String oid)
	{
		LeafSyntax lf = getLeafSyntax(oid);

		if(lf != null)
			return lf.getDescription();
		else
			return null;
	}

	public LeafSyntax getLeafSyntax(String oid)
	{
		MibNode mibNode = getMibNode(oid);

		if(mibNode != null)
			return mibNode.getSyntax();
		else
			return null;
	}

	public String getNumericOID(String oid)
	{
		return mibs.getOID(oid);
	}

	public boolean checkTheValue(String oid, String value)
	{
		LeafSyntax lf = getLeafSyntax(oid);

		if(lf != null)
			return mibs.checkValueForType(value, lf.getType());
		else
			return false;
	}


	public MibOperations getMibOperations()
	{
		return mibs.getMibOperations();
	}

	public Vector getIndexes(String tableOID)
	{
		Vector indexes = new Vector();

		tableOID += ".1.1";

		MibNode tableNode = getMibNode(tableOID);

		if(tableNode != null)
		{
			Vector indexNodes = mibs.getIndexesForTable(tableNode);

			if(indexNodes != null)
			{
				for(int i=0; i<indexNodes.size(); i++)
				{
					MibNode mibNode = (MibNode)indexNodes.elementAt(i);

					indexes.addElement(mibNode.getNumberedOIDString());
				}
			}
		}
	
		return indexes;
	}


	public Vector formatSNMPTaskAttributesForSave(Vector taskAttributes)
	{
		Vector formattedTaskAttributes = null;

		if(taskAttributes != null && taskAttributes.size() > 0)
		{
			formattedTaskAttributes = new Vector();

			Vector formattedScalarAttributes = new Vector();
			Hashtable formattedTableAttributes = new Hashtable();

			Vector scalarAttributes = (Vector)taskAttributes.elementAt(0);

			if(scalarAttributes != null && scalarAttributes.size() > 0)
			{

				for(int i=0; i < scalarAttributes.size(); i++)
				{
					Vector vec = (Vector)scalarAttributes.elementAt(i); 

					Properties prop = new Properties();

					prop.put("identifier", vec.elementAt(0));
					prop.put("label", vec.elementAt(1));
					prop.put("type", getType((String)vec.elementAt(0)));
					prop.put("value", vec.elementAt(3));

					formattedScalarAttributes.addElement(prop);
				}

			}

			if(taskAttributes.size() == 2)
			{
				Hashtable tableAttributes = (Hashtable)taskAttributes.elementAt(1);

				if(tableAttributes != null && tableAttributes.size() > 0)
				{

					for(Enumeration enumerate = tableAttributes.keys(); enumerate.hasMoreElements();)
					{
						String key = (String)enumerate.nextElement();
						Vector vec = (Vector)tableAttributes.get(key);

						if(vec != null && vec.size() > 0)
						{
							if(key.indexOf('(') != -1)
							{
								key = key.substring(key.indexOf("(")+1, key.length()-1);
							}

							Vector formattedVec = new Vector();

							Vector formattedColumns = new Vector();

							Vector columns = (Vector)vec.elementAt(0);

							for(int i=0; i < columns.size(); i++)
							{
								formattedColumns.addElement(getNumericOID((String)columns.elementAt(i)));
							}

							formattedVec.addElement(formattedColumns);
							formattedVec.addElement(vec.elementAt(1));

							formattedTableAttributes.put(key, formattedVec);


							/*
							   MibNode mibNode = getMibNode(key);

							   if(mibNode != null)
							   {
							   Vector formattedVec = new Vector();
							   Vector formattedColumns = new Vector();
							   Vector columns = (Vector)vec.elementAt(0);

							   for(int i=0; i < columns.size(); i++)
							   {
							   String columnName = (String)columns.elementAt(i);
							   String formattedColumnName = getNumericOID(columnName);
							   if(isIndexColumn(mibNode, columnName))
							   {
							   formattedColumnName = "index"+formattedColumnName;
							   }

							   formattedColumns.addElement(formattedColumnName);
							   }

							   formattedVec.addElement(formattedColumns);
							   formattedVec.addElement(vec.elementAt(1));
							   formattedTableAttributes.put(key, formattedVec);
							   }
							   else 
							   {
							   formattedTableAttributes.put(key, vec);
							   }*/

						}
					}

				}

			}
			formattedTaskAttributes.addElement(formattedScalarAttributes);
			formattedTaskAttributes.addElement(formattedTableAttributes);
		}

		return formattedTaskAttributes;
	}

	private boolean isIndexColumn(MibNode mibNode, String columnName)
	{
		boolean isIndex = false;

		Vector indexColumns = mibNode.getIndexNames();

		if(indexColumns != null && indexColumns.size() > 0)
		{
			for(Enumeration enumerate = indexColumns.elements(); enumerate.hasMoreElements();)
			{
				String indexColumn = (String)enumerate.nextElement();

				if(indexColumn.equalsIgnoreCase(columnName))
				{
					isIndex = true;
					break;
				}
			}
		}

		return isIndex;
	}

	public Vector formatSNMPTaskAttributesForDisplay(Vector taskAttributes)
	{
		Vector formattedTaskAttributes = null;

		if(taskAttributes != null && taskAttributes.size() > 0)
		{
			formattedTaskAttributes = new Vector();

			Vector formattedScalarAttributes = new Vector();
			Hashtable formattedTableAttributes = new Hashtable();

			Vector scalarAttributes = (Vector)taskAttributes.elementAt(0);

			if(scalarAttributes != null && scalarAttributes.size() > 0)
			{

				for(int i=0; i < scalarAttributes.size(); i++)
				{
					Properties prop = (Properties)scalarAttributes.elementAt(i);

					if(prop != null)
					{
						Vector vec = new Vector();

						vec.addElement(prop.get("identifier"));
						vec.addElement(prop.get("label"));

						String type = getTypeName((String)prop.get("identifier"));

						if(type != null)
							vec.addElement(type);
						else
							vec.addElement(prop.get("type"));

						vec.addElement(prop.get("value"));

						formattedScalarAttributes.addElement(vec);
					}
				}
			}

			if(taskAttributes.size() == 2)
			{
				Hashtable tableAttributes = (Hashtable)taskAttributes.elementAt(1);

				if(tableAttributes != null && tableAttributes.size() > 0)
				{
					for(Enumeration enumerate = tableAttributes.keys(); enumerate.hasMoreElements();)
					{
						String key = (String) enumerate.nextElement();
						Vector value = (Vector)tableAttributes.get(key);

						String tableLabel = getLabel(key);

						if(tableLabel != null)
						{
							key = tableLabel+"("+key+")";
						}
						
						Vector columnarOIDs = (Vector)value.elementAt(0);
						
						Vector readableNames = new Vector();

						for(int i = 0; i < columnarOIDs.size(); i++)
						{
							String columnOID = (String)columnarOIDs.elementAt(i);

							String columnLabel = getLabel(columnOID);	

							if(columnLabel != null)
							{
								readableNames.addElement(columnLabel);
							}
							else
							{
								readableNames.addElement(columnOID);
							}
						}
						
						value.setElementAt(readableNames,0);

						formattedTableAttributes.put(key, value);

					}
				}

				formattedTaskAttributes.addElement(formattedScalarAttributes);
				formattedTaskAttributes.addElement(formattedTableAttributes);				}
		}

		return formattedTaskAttributes;
	}


	private void actionPerformed()
	{
		MibNode mibNode = getMibNode(mibs.getOidString());

		if(mibNode != null)
		{
			Properties data = new Properties();

			String oid = mibNode.getNumberedOIDString();

			if(key.equalsIgnoreCase("SCALAR"))
			{
				if(mibNode.isScalar())
				{
					data.put("identifier", oid + ".0");
					data.put("label", mibNode.getLabel());
					data.put("type", mibNode.getSyntax().toString());
				}
				else
				{
					MibNode tnode = mibNode.getParent().getParent();

					if(tnode.isTable())
					{
						Vector indexes = mibs.getIndexesForTable(mibNode);
						
						Vector numericIndexes = new Vector();
						Vector stringIndexes = new Vector();
						
						for(int k = 0; k < indexes.size(); k++)
						{
							stringIndexes.add(((MibNode)indexes.elementAt(k)).getLabel());
							numericIndexes.add(((MibNode)indexes.elementAt(k)).getNumberedOIDString());
						}
						
						data.put("identifier", oid);
						data.put("label", mibNode.getLabel());
						data.put("type", mibNode.getSyntax().toString());
						data.put("columnar","true");
						data.put("indexes",stringIndexes);
						data.put("numericIndexes", numericIndexes);
					}
				}
			}
			else if(key.equalsIgnoreCase("TABLE"))
			{
				if(mibNode.isTable())
				{
					data.put("identifier", mibNode.getNumberedOIDString());

					data.put("label", mibNode.getLabel());

					Vector columns = mibNode.getTableItems();

					for(int i=0; i<columns.size(); )
					{
						MibNode cnode = getMibNode((String)columns.elementAt(i));

						if(!cnode.isIndex())
						{
							int access = cnode.getAccess();

							if(access == SnmpAPI.RONLY || access == SnmpAPI.NOACCESS)
							{
								columns.removeElementAt(i);

								continue;
							}
						}

						i++;
					}

					data.put("columns", columns);
				}
			}

			if(mibHandlerListener != null)
			{
				mibHandlerListener.setResult(data);
			}
		}

	}
	
	public String getEncodedValue(Vector numericIndexes, Vector valueVector)
	{
		String s = "";
		try
		{
			Vector snmpVarVec = new Vector();
			Vector mibNodeVec = new Vector();

			LeafSyntax lf = null;
			
			for(int i =0 ;i < numericIndexes.size();i++)
			{
				MibNode indexNode = mibs.getMibNode((String)numericIndexes.elementAt(i));

				lf = indexNode.getSyntax();
				
				SnmpVar snmpVar = lf.createVariable((String)valueVector.elementAt(i));

				snmpVarVec.addElement(snmpVar);
				mibNodeVec.addElement(indexNode);
			}

			int[] intIndex = lf.encodeInstanceString(snmpVarVec,mibNodeVec);
			
			for(int i=0;i<intIndex.length;++i)
			{
				s +=intIndex[i];
				
				if ( i != intIndex.length -1)
					s +=".";
			}
		}
		catch(Exception ex)
		{
			System.out.println("Some error in the input");
		}

		return s;
	}

}






