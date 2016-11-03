//$Id: ConfigTaskReader.java,v 1.1.6.1 2012/05/29 06:26:56 karen.r Exp $

package com.adventnet.nms.config;

import java.io.*;
import java.util.*;

import com.adventnet.management.config.*;
import com.adventnet.management.config.xml.*;
import com.adventnet.management.config.snmp.*;
import com.adventnet.management.config.telnet.*;
import com.adventnet.management.config.tftp.*;
import com.adventnet.management.config.ftp.*;
import com.adventnet.management.config.tl1.*;
import com.adventnet.management.config.netconf.NetconfAttribute;
import com.adventnet.management.config.netconf.NetconfAttributeConstants;


public class ConfigTaskReader
{
	private ConfigPanel configPanel = null;

	private ConfigTask xmlReader = null;

	private String taskName = null;

	private boolean isTemplate = false;

	private String taskDesc = null;

	private String protocol = null;

	private String rollbackDocument = null;

	private boolean isCombined = false;

	private Vector taskAttributes = null;

	private Vector placeHolders = null;

	private Vector dataSourceParams = null;

	private Hashtable NEInputAttributes = null;

	public ConfigTaskReader(ConfigPanel configPanel, String task)
	{
		this.configPanel = configPanel;

		try
		{
			xmlReader = new ConfigTask(task);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		parse();
	}

	public String getTaskName()
	{
		return taskName;
	}

	public boolean isTemplate()
	{
		return isTemplate;
	}

	public Vector getTemplatePlaceHolders()
	{
		return placeHolders;
	}

	public Vector getDataSourceParams()
	{
		return dataSourceParams;
	}

	public Hashtable getNEInputAttributes()
	{
		return NEInputAttributes;
	}

	public String getTaskDesc()
	{
		return taskDesc;
	}

	public String getProtocol()
	{
		return protocol;
	}

	public String getRollbackDocument()
	{
		return rollbackDocument;
	}

	public boolean isCombinedTask()
	{
		return isCombined;
	}

	public Vector getTaskAttributes()
	{
		return taskAttributes;
	}

	private void parse()
	{
		taskName = xmlReader.getTaskName();

		isTemplate = xmlReader.isTemplate();

		taskDesc = xmlReader.getDescription();

		protocol = xmlReader.getProtocol();

		if(isTemplate)
		{
			placeHolders = new Vector();
			dataSourceParams = new Vector();
			
			NEInputAttributes = new Hashtable();
		}

		int rollbackType = xmlReader.getRollbackType();

		if(rollbackType == ConfigConstants.CURRENT_CONFIG)
		{
			rollbackDocument = "Current Configuration";	
		}
		else if(rollbackType == ConfigConstants.DOCUMENT)
		{
			rollbackDocument = xmlReader.getRollbackDocument();
		}

		isCombined = xmlReader.isCombinedTask();

		if(isCombined)
		{
			taskAttributes = convertStrArrToVec(xmlReader.getSubTaskNames());
		}
		else
		{
			Attribute[] attributes = xmlReader.getConfigAttributes();

			if(protocol != null)
			{
				if(protocol.equalsIgnoreCase("snmp"))
				{
					taskAttributes = getSNMPTaskAttributes(attributes);
				}
				else if(protocol.equalsIgnoreCase("telnet"))
				{
					taskAttributes = getTelnetTaskAttributes(attributes);
				}
				else if(protocol.equalsIgnoreCase("tftp"))
				{
					taskAttributes = getTFTPTaskAttributes(attributes);
				}
				else if(protocol.equalsIgnoreCase("ftp"))
				{
					taskAttributes = getFTPTaskAttributes(attributes);
				}
				else if(protocol.equalsIgnoreCase("tl1"))
				{
					taskAttributes = getTL1TaskAttributes(attributes);
				}
				else if (protocol.equalsIgnoreCase("netconf"))//No I18N
				{
					taskAttributes = getNetconfTaskAttributes(attributes);
				}
			}
		}
	}

	private Vector convertStrArrToVec(String[] subTaskNames)
	{
		Vector vec = null;

		if(subTaskNames != null && subTaskNames.length > 0)
		{
			vec = new Vector();

			for(int i=0; i < subTaskNames.length; i++)
			{
				vec.addElement(subTaskNames[i]);
			}
		}

		return vec;
	}

	private Vector getSNMPTaskAttributes(Attribute[] attributes)
	{
		Vector tableData = null;

		if(attributes != null && attributes.length > 0)
		{
			tableData = new Vector();
			Vector scalarAttributes = new Vector();
			Hashtable tableAttributes = new Hashtable();

			for(int i=0; i < attributes.length; i++)
			{

				SnmpAttribute snmpAttrib = new SnmpAttribute(attributes[i]);

				if(snmpAttrib.getAttributeType() == AttributeConstants.SCALAR_ATTRIBUTE)
				{
					Properties prop = new Properties();

					prop.put("identifier",snmpAttrib.getIdentifier());

					if(snmpAttrib.getLabel() != null)
						prop.put("label",snmpAttrib.getLabel());

					prop.put("type",String.valueOf(snmpAttrib.getType()));

					String value = snmpAttrib.getValue();

					prop.put("value", value);

					if(isTemplate)
					{
						addValuePlaceHolder(attributes[i], value);
					}

					scalarAttributes.addElement(prop);
				}
				else if(snmpAttrib.getAttributeType() == AttributeConstants.TABLE_ATTRIBUTE)
				{
					Attribute[] childList = snmpAttrib.getAttributeList();

					if(childList != null && childList.length > 0)
					{
						String tableOID = snmpAttrib.getTableOID();

						String index = snmpAttrib.getIndex();

						Vector columns = null;

						Vector rowData = new Vector();

						if(!tableAttributes.containsKey(tableOID))
						{
							columns = new Vector();
						}	
						//Added by vkarthik as a L3 fix for Message Id : 1502421
						addValuePlaceHolder(snmpAttrib,index);

						for(int j=0; j < childList.length; j++)
						{
							SnmpAttribute child = new SnmpAttribute(childList[j]);

							if(columns != null)
							{
								columns.addElement(child.getIdentifier());
							}

							String value = child.getValue();

							if(isTemplate)
							{
								addValuePlaceHolder(childList[j], value);	
							}

							rowData.addElement(value);
						}

						Vector indexValues = configPanel.mibHandler.getIndexValues(tableOID, index);	

						if(indexValues != null)
						{
							for(int j=0; j<indexValues.size(); j++)
							{
								rowData.addElement(indexValues.elementAt(j));	
							}
						}

						if(columns != null)
						{
							Vector value = new Vector();

							Vector tableData1 = new Vector();

							if(indexValues != null)
							{
							Vector indexColumns = configPanel.mibHandler.getIndexes(tableOID);

							for(int j=0; j<indexColumns.size(); j++)
							{
								columns.addElement(indexColumns.elementAt(j));
							}
							}

							value.addElement(columns);

							tableData1.addElement(rowData);

							value.addElement(tableData1);

							tableAttributes.put(tableOID, value);
						}
						else
						{
							Vector value = (Vector)tableAttributes.get(tableOID);

							Vector tableData2 = (Vector)value.elementAt(1);

							tableData2.addElement(rowData);
						}
					}
				}
			}

			tableData.addElement(scalarAttributes);
			tableData.addElement(tableAttributes);
		}

		return tableData;
	}

	private Vector getTelnetTaskAttributes(Attribute[] attributes)
	{
		Vector tableData = null;

		if(attributes != null && attributes.length > 0)
		{
			tableData = new Vector();

			for(int i=0; i < attributes.length; i++)
			{
				Vector rowData = new Vector();

				TelnetAttribute TelnetAttrib = new TelnetAttribute(attributes[i]);

				String command = TelnetAttrib.getCommand();
				String commandArguments = TelnetAttrib.getCommandArgument();
				String prompt = TelnetAttrib.getPrompt();

				rowData.addElement(command);
				rowData.addElement(commandArguments);
				rowData.addElement(prompt);

				if(isTemplate)
				{
					addValuePlaceHolder(attributes[i], command);
					addValuePlaceHolder(attributes[i], commandArguments);
					addValuePlaceHolder(attributes[i], prompt);
				}

				tableData.addElement(rowData);
			}
		}

		return tableData;
	}

	private Vector getTFTPTaskAttributes(Attribute[] attributes)
	{
		Vector tableData = null;

		if(attributes != null && attributes.length > 0)
		{
			tableData = new Vector();

			for(int i=0; i < attributes.length; i++)
			{
				Vector rowData = new Vector();

				TftpAttribute TftpAttrib = new TftpAttribute(attributes[i]);

				rowData.addElement(TftpAttrib.getCommand());

				String sourceFileName = TftpAttrib.getSourceFileName();
				String destinationFileName = TftpAttrib.getDestinationFileName();

				rowData.addElement(sourceFileName);			
				rowData.addElement(destinationFileName);

				if(isTemplate)
				{
					addValuePlaceHolder(attributes[i], sourceFileName);
					addValuePlaceHolder(attributes[i], destinationFileName);
				}

				if(TftpAttrib.isBinaryMode())
					rowData.addElement("binary");
				else
					rowData.addElement("ascii");

				tableData.addElement(rowData);
			}
		}

		return tableData;
	}

	private Vector getFTPTaskAttributes(Attribute[] attributes)
	{
		Vector tableData = null;

		if(attributes != null && attributes.length > 0)
		{
			tableData = new Vector();

			for(int i=0; i < attributes.length; i++)
			{
				Vector rowData = new Vector();

				FTPAttribute attrib = new FTPAttribute(attributes[i]);

				rowData.addElement(attrib.getCommand());

				String sourceFileName = attrib.getSourceFileName();
				String destinationFileName = attrib.getDestinationFileName();

				rowData.addElement(sourceFileName);			
				rowData.addElement(destinationFileName);

				if(isTemplate)
				{
					addValuePlaceHolder(attributes[i], sourceFileName);
					addValuePlaceHolder(attributes[i], destinationFileName);
				}

				rowData.addElement(attrib.getMode());

				tableData.addElement(rowData);
			}
		}

		return tableData;
	}

	private Vector getTL1TaskAttributes(Attribute[] attributes)
	{
		Vector tableData = null;

		if(attributes != null && attributes.length > 0)
		{
			tableData = new Vector();

			for(int i=0; i < attributes.length; i++)
			{
				Vector rowData = new Vector();

				TL1Attribute TL1Attrib = new TL1Attribute(attributes[i]);

				String commandCode = TL1Attrib.getCommandCode();
				String targetID = TL1Attrib.getTargetID();
				String accessID = TL1Attrib.getAccessID();
				String generalBlock = TL1Attrib.getGeneralBlock();
				String messagePayLoadBlock = TL1Attrib.getMessagePayLoadBlock();

				rowData.addElement(commandCode);
				rowData.addElement(targetID);
				rowData.addElement(accessID);
				rowData.addElement(generalBlock);
				rowData.addElement(messagePayLoadBlock);

				if(isTemplate)
				{
					addValuePlaceHolder(attributes[i], commandCode);
					addValuePlaceHolder(attributes[i], targetID);
					addValuePlaceHolder(attributes[i], accessID);
					addValuePlaceHolder(attributes[i], generalBlock);
					addValuePlaceHolder(attributes[i], messagePayLoadBlock);
				}

				tableData.addElement(rowData);
			}
		}

		return tableData;
	}

	private Vector getNetconfTaskAttributes(Attribute[] attributes)
	{
		
		
		Vector tableData = null;

		if(attributes != null && attributes.length > 0)
		{
			tableData = new Vector();
			for(int i=0; i < attributes.length; i++)
			{
				Vector rowData = new Vector();
				NetconfAttribute attrib = new NetconfAttribute(attributes[i]);
				for(int k=0;k<NetconfAttributeConstants.ATTRIBUTE_KEYS.length;k++)
				{
					if(attrib.hasAttribute(NetconfAttributeConstants.ATTRIBUTE_KEYS[k]))
					{
						rowData.addElement(attrib.getAttribute(NetconfAttributeConstants.ATTRIBUTE_KEYS[k]));
						if(isTemplate)
						{
							addValuePlaceHolder(attributes[i], attrib.getAttribute(NetconfAttributeConstants.ATTRIBUTE_KEYS[k]));
						}
					}
					else
					{
						//adding empty string for properties that are not available for this configattribute.
						rowData.addElement("");  //No I18N
					}
				}
				tableData.addElement(rowData);
			}
			
		}
		return tableData;
	}
	
	private void addValuePlaceHolder(Attribute attribute, String value)
	{
		if(value != null)
		{
			if(value.startsWith("$NEInput$") || value.startsWith("$InventoryInput$") || value.startsWith("$UserInput$"))
			{
				if(!placeHolders.contains(value))
				{
					if(value.startsWith("$NEInput$"))
					{
						NEInputAttributes.put(value.substring(value.lastIndexOf("$")+1), attribute);
					}

					placeHolders.addElement(value);
				}
			}
			else if(value.startsWith("$DataSourceParam$"))
			{
				if(!dataSourceParams.contains(value))
				{
					dataSourceParams.addElement(value.substring(value.lastIndexOf('$')+1));
				}
			}
		}
	}
}





