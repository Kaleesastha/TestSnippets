//$Id: ConfigTaskGenerator.java,v 1.2.6.4 2013/01/25 06:47:57 karen.r Exp $

package com.adventnet.nms.config;

import java.util.*;

import com.adventnet.management.config.*;
import com.adventnet.management.config.netconf.NetconfAttribute;
import com.adventnet.management.config.netconf.NetconfAttributeConstants;
import com.adventnet.management.config.snmp.*;
import com.adventnet.management.config.telnet.*;
import com.adventnet.management.config.tftp.*;
import com.adventnet.management.config.ftp.*;
import com.adventnet.management.config.tl1.*;
import com.adventnet.management.config.xml.InvalidTemplateException;

public class ConfigTaskGenerator
{

	private ConfigPanel configPanel = null;

	private String taskName = null;

	private boolean isTemplate = false;

	private String taskDesc = null;

	private String protocol = null;

	private String rollbackDocument = null;

	private boolean isCombinedTask = false;

	private Vector taskAttributes = null;

	private boolean isOverwrite = false;

	public ConfigTaskGenerator(ConfigPanel configPanel)
	{
		this.configPanel = configPanel;
	}

	public ConfigTaskGenerator(ConfigPanel configPanel, String taskName)
	{
		this.configPanel = configPanel;
		this.taskName = taskName;
	}

	public ConfigTaskGenerator(ConfigPanel configPanel, String taskName, String protocol)
	{
		this.configPanel = configPanel;
		this.taskName = taskName;
		this.protocol = protocol;
	}

	public ConfigTaskGenerator(ConfigPanel configPanel, String taskName, String protocol, String taskDesc)
	{
		this.configPanel = configPanel;
		this.taskName = taskName;
		this.protocol = protocol;
		this.taskDesc = taskDesc;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	public void setTemplate(boolean isTemplate)	
	{
		this.isTemplate = isTemplate;
	}

	public void setDescription(String taskDesc)
	{
		this.taskDesc = taskDesc;
	}

	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	public void setRollbackDocument(String rollbackDocument)
	{
		this.rollbackDocument = rollbackDocument;
	}

	public void setCombinedTask(boolean isCombinedTask)
	{
		this.isCombinedTask = isCombinedTask;
	}

	public void setTaskAttributes(Vector taskAttributes)
	{
		this.taskAttributes = taskAttributes;
	}

	public void setOverwrite(boolean isOverwrite)
	{
		this.isOverwrite = isOverwrite;
	}

	public String generateTask()
	{
		String xmlfile = null;

		if(taskAttributes != null)
		{
			if(isCombinedTask)
			{
				xmlfile = generateCombinedTask();
			}
			else
			{
				if(protocol.equalsIgnoreCase("snmp"))
				{
					xmlfile = generateSNMPTask();
				}
				else if(protocol.equalsIgnoreCase("telnet"))
				{
					xmlfile = generateTelnetTask();
				}
				else if(protocol.equalsIgnoreCase("tftp"))
				{
					xmlfile = generateTFTPTask();
				}
				else if(protocol.equalsIgnoreCase("ftp"))
				{
					xmlfile = generateFTPTask();
				}
				else if(protocol.equalsIgnoreCase("tl1"))
				{
					xmlfile = generateTL1Task();
				}
				else if(protocol.equalsIgnoreCase("netconf"))//No I18N
				{
					try {
						xmlfile = generateNetconfTask();
					} catch (ConfigException e) {
						
						e.printStackTrace();
					}
				}
			}
		}

		return xmlfile;
	}

	private String generateCombinedTask()
	{
		TaskGenerator xmlGen = new TaskGenerator();

		setBasicTaskParams(xmlGen);

		xmlGen.setSubTaskNames(taskAttributes);

		return xmlGen.getTask();	
	}

	private String generateSNMPTask()
	{
		SnmpTaskGenerator snmpXMLGenerator = new SnmpTaskGenerator(configPanel.mibHandler.getMibOperations());
		boolean flg = false;//Added by vkarthik as a L3 fix for issue id 1502421

		setBasicTaskParams(snmpXMLGenerator);

		if(taskAttributes != null)
		{
			if(taskAttributes.size() > 0)
			{
				Vector scalarAttributes = (Vector)taskAttributes.elementAt(0);

				if(scalarAttributes != null) 
				{
					int size = scalarAttributes.size();

					String oidarr[]   = new String[size];
					String labelarr[] = new String[size];
					String typearr[]  = new String[size];
					String valuearr[] = new String[size];

					for(int i=0; i<size; i++)
					{
						Properties prop = (Properties)scalarAttributes.elementAt(i);

						oidarr[i]   = (String)prop.get("identifier");
						labelarr[i] = (String)prop.get("label");
						typearr[i]  = (String)prop.get("type");
						valuearr[i] = (String)prop.get("value");
					}

					snmpXMLGenerator.addAttributes(oidarr, labelarr, typearr, valuearr);
				}

				if(taskAttributes.size() == 2)
				{
					Hashtable tableAttributes = (Hashtable)taskAttributes.elementAt(1);

					if(tableAttributes != null)
					{
						for(Enumeration enumerate = tableAttributes.keys(); enumerate.hasMoreElements();)
						{
							String tableOID = (String)enumerate.nextElement();

							Vector tableData = (Vector)tableAttributes.get(tableOID);

							if(tableData != null)
							{
								Vector columns = (Vector)tableData.elementAt(0);

								Vector rows = (Vector)tableData.elementAt(1);

								Vector indexOIDs = configPanel.mibHandler.getIndexes(tableOID);

								int totalColumns = columns.size() - indexOIDs.size();

								for(int i=0; i<rows.size(); i++)	
								{
									Vector rowData = (Vector)rows.elementAt(i);

									Vector indexValues = new Vector();

									String[] columnOIDs = new String[totalColumns];

									String[] columnValues = new String[totalColumns];

									int columnCount = 0;
									
									String index = "";//Added by vkarthik as a L3 fix for issue id 1502421

									for(int j=0; j<rowData.size(); j++)
									{
										String columnValue = (String)rowData.elementAt(j);

										String columnOID = (String)columns.elementAt(j);

										if(!indexOIDs.contains(columnOID))
										{
											columnOIDs[columnCount] = columnOID;

											columnValues[columnCount] = columnValue;

											columnCount++;
										}
									}

									for(int k=0; k<indexOIDs.size(); k++)
									{
										String indexOID = (String)indexOIDs.elementAt(k);	

										int	index1 = columns.indexOf(indexOID); 

										if(index1 != -1)
										{
										    String temp =(String)rowData.elementAt(index1);//Added by vkarthik as a L3 fix for issue id 1502421
										    indexValues.addElement(rowData.elementAt(index1));
										    //Added by vkarthik as a L3 fix for issue id 1502421
										    if((temp.startsWith("$NEInput$") || temp.startsWith("$InventoryInput$") || temp.startsWith("$UserInput$") || temp.startsWith("$DataSourceParam$")) &&  isTemplate == true)
											{
											    flg = true; 
											}
										}
									}
									//Added by vkarthik as a L3 fix for issue id 1502421
									if(flg)						
									{
									    	StringBuffer buffer = new StringBuffer();
										for(int l=0;l<indexValues.size();l++)
										    {
											String str =(String)indexValues.elementAt(l);
											if(l == (indexValues.size()-1))
											    {
												buffer.append(str);
											    }
											else
											    {
												buffer.append(str+".");
											    }

										    }
										index = buffer.toString();
									}    
									if(!flg)
									{
									    index = configPanel.mibHandler.getEncodedValue(indexOIDs, indexValues);
									}
									//End - vkarthik
									
									snmpXMLGenerator.addRowAttribute(tableOID, columnOIDs, columnValues, index);
								}
							}


							/*
							   if(tableData != null)
							   {
							   Vector columns = (Vector)tableData.elementAt(0);
							   Vector rowDatas = (Vector)tableData.elementAt(1);

							   int columnSize = columns.size();

							   for(Enumeration enum1 = rowDatas.elements(); enum1.hasMoreElements();)
							   {
							   Vector vec = (Vector)enum1.nextElement();

							   String columnOids[] = new String[columnSize];
							   String columnDatas[] = new String[columnSize];

							   Vector snmpVarVec = new Vector();
							   Vector mibNodeVec = new Vector();

							   LeafSyntax lf = null;

							   for(int i=0; i < columnSize; i++)
							   {
							   String column = (String)columns.elementAt(i);
							   columnDatas[i] = (String)vec.elementAt(i);
							   if(column.startsWith("index"))
							   {
							   columnOids[i] = column.substring(5);

							   MibNode mibNode = configPanel.mibHandler.getMibNode(columnOids[i]);											
							   SnmpVar snmpVar = null;

							   if(mibNode != null)
							lf = mibNode.getSyntax();

							try{
								snmpVar = lf.createVariable(columnDatas[i]);		
							}catch(Exception e){}

							snmpVarVec.addElement(snmpVar);
							mibNodeVec.addElement(mibNode);	

						}
							   else
								   columnOids[i] = column;
						}


						String s="";

						if(lf != null)
						{
							int[] intIndex=lf.encodeInstanceString(snmpVarVec,mibNodeVec);
							for ( int k=0;k<intIndex.length;++k)
							{
								s +=intIndex[k];
								if ( k != intIndex.length -1)
									s +=".";
							}
						}

						snmpXMLGenerator.addRowAttribute(tableOID, columnOids, columnDatas, s);
						}
						}
						*/

						}
					}
				}
			}
		}

		return snmpXMLGenerator.getTask();
	}

	private String generateNetconfTask() throws ConfigException {
		TaskGenerator xmlGen = new TaskGenerator();

		setBasicTaskParams(xmlGen);

		for(int i = 0; i < taskAttributes.size(); i++)
		{
			Vector rowData = (Vector)taskAttributes.elementAt(i);
			String configOperation = (String)rowData.elementAt(0);
			NetconfAttribute attrib = null;
			try{
				attrib = new NetconfAttribute(configOperation);
			}
			catch (InvalidTemplateException e) {
				throw new ConfigException();
			}
	 		String configAction = (String)rowData.get(1);
	 		String namespace = (String)rowData.get(2);
	 		String configPath = (String)rowData.get(3);
	 		String subPath = (String)rowData.get(4);
	 		String configUrl = (String)rowData.get(5);
	 		String source =(String)rowData.get(6);
	 		String target =(String)rowData.get(7);
	 		String sourceUrl=(String)rowData.get(8);
	 		String targetUrl=(String)rowData.get(9);
	 		String defaultAction=(String)rowData.get(10);
	 		String testOption=(String)rowData.get(11);
	 		String errorOption=(String)rowData.get(12);
	 		String xPathFilter = (String)rowData.get(13);


	 		if (configOperation.equals("get-config"))//No I18N
	 		{
	 			attrib.setAttribute(NetconfAttributeConstants.NAMESPACE, namespace);
	 			attrib.setAttribute(NetconfAttributeConstants.SOURCE_DS, source);
	 			if (!xPathFilter.equals(""))//No I18N
	 			{
	 				 attrib.setAttribute(NetconfAttributeConstants.XPATH_FILTER, xPathFilter);
	 				 attrib.setAttribute("identifier","get-config >> "+xPathFilter);
	 			}
	 			else
	 			{
	 				attrib.setAttribute(NetconfAttributeConstants.CONFIG_PATH, configPath);
	 				attrib.setAttribute("identifier","get-config >> "+configPath);
	 			}
	 		}
	 		else if (configOperation.equals("edit-config"))//No I18N
	 		{
	 			attrib.setAttribute(NetconfAttributeConstants.CONFIG_ACTION, configAction);
	 			attrib.setAttribute(NetconfAttributeConstants.DEFAULT_ACTION, defaultAction);
	 			attrib.setAttribute(NetconfAttributeConstants.TARGET_DS, target);
	 			if (!configUrl.equals(""))//No I18N
	 			{
	 				attrib.setAttribute(NetconfAttributeConstants.CONFIG_URL, configUrl);
	 				attrib.setAttribute("identifier","edit-config >> "+configUrl);
	 			}
	 			else
	 			{
	 				attrib.setAttribute(NetconfAttributeConstants.CONFIG_PATH, configPath);
	 				attrib.setAttribute("identifier","edit-config >> "+configPath);
	 				if (!subPath.equals(""))//No I18N
	 				{
	 					attrib.setAttribute(NetconfAttributeConstants.SUB_PATH, subPath);
	 					attrib.setAttribute("identifier","edit-config >> "+configPath+":"+subPath);
	 				}
	 				if(!namespace.equals(""))//No I18N
	 				{
	 					attrib.setAttribute(NetconfAttributeConstants.NAMESPACE, namespace);
	 				}
	 			}
	 			attrib.setAttribute(NetconfAttributeConstants.TEST_OPTION, testOption);
	 			attrib.setAttribute(NetconfAttributeConstants.ERROR_OPTION, errorOption);

	 		}
	 		else if (configOperation.equals("copy-config"))//No I18N
	 		{
	 			if (!sourceUrl.equals(""))//No I18N
	 			{
	 				attrib.setAttribute(NetconfAttributeConstants.SOURCE_URL, sourceUrl);
	 			}
	 			else if (!source.equals(""))//No I18N
	 			{
	 				attrib.setAttribute(NetconfAttributeConstants.SOURCE_DS, source);
	 			}
	 			if (!targetUrl.equals(""))//No I18N
	 			{
	 				attrib.setAttribute(NetconfAttributeConstants.TARGET_URL, targetUrl);
	 				if(!sourceUrl.equals(""))
	 				{
	 					attrib.setAttribute("identifier","copy-config >> "+sourceUrl+":"+targetUrl);
	 				}
	 				else
	 				{
	 					attrib.setAttribute("identifier","copy-config >> "+source+":"+targetUrl);
	 				}
	 				
	 			}
	 			else if (!target.equals(""))//No I18N
	 			{
	 				attrib.setAttribute(NetconfAttributeConstants.TARGET_DS, target);
	 				if(!sourceUrl.equals(""))
	 				{
	 					attrib.setAttribute("identifier","copy-config >> "+sourceUrl+":"+target);
	 				}
	 				else
	 				{
	 					attrib.setAttribute("identifier","copy-config >> "+source+":"+target);
	 				}
	 			}

	 		}
	 		else if (configOperation.equals("delete-config"))//No I18N
	 		{
	 			if (!targetUrl.equals(""))//No I18N
	 			{
	 				attrib.setAttribute(NetconfAttributeConstants.TARGET_URL, targetUrl);
	 				attrib.setAttribute("identifier","delete-config >> "+targetUrl);
	 			}
	 			else if (!target.equals(""))//No I18N
	 			{
	 				attrib.setAttribute(NetconfAttributeConstants.TARGET_DS, target);
	 				attrib.setAttribute("identifier","delete-config >> "+target);
	 			}
	 		}
	 		xmlGen.addAttribute(attrib);

		}
		return xmlGen.getTask();
	}

	private String generateTelnetTask()
	{
		TaskGenerator xmlGen = new TaskGenerator();

		setBasicTaskParams(xmlGen);

		for(int i = 0; i < taskAttributes.size(); i++)
		{
			Vector vec = (Vector)taskAttributes.elementAt(i);

			TelnetAttribute attrib = new TelnetAttribute((String)vec.elementAt(0));

			attrib.setCommandArgument((String)vec.elementAt(1));

			String prompt = (String)vec.elementAt(2);

			if(prompt != null && !prompt.equals(""))
				attrib.setPrompt(prompt);

			xmlGen.addAttribute(attrib);
		}

		return xmlGen.getTask();
	}

	private String generateTFTPTask()
	{
		TaskGenerator xmlGen = new TaskGenerator();

		setBasicTaskParams(xmlGen);

		for(int i = 0; i < taskAttributes.size(); i++)
		{
			Vector vec = (Vector)taskAttributes.elementAt(i);

			String mode = (String)vec.elementAt(3);

			boolean binaryMode = true;

			if(!mode.equalsIgnoreCase("binary"))
			{
				binaryMode = false;
			}

			TftpAttribute attrib = new TftpAttribute((String)vec.elementAt(0), (String)vec.elementAt(1), (String)vec.elementAt(2), binaryMode);

			xmlGen.addAttribute(attrib);
		}

		return xmlGen.getTask();	
	}

	private String generateFTPTask()
	{
		TaskGenerator xmlGen = new TaskGenerator();

		setBasicTaskParams(xmlGen);

		for(int i = 0; i < taskAttributes.size(); i++)
		{
			Vector vec = (Vector)taskAttributes.elementAt(i);

			String dest = (String)vec.elementAt(2);

			FTPAttribute attrib;

			if(dest != null && !dest.equals(""))
			{
				attrib = new FTPAttribute((String)vec.elementAt(0), (String)vec.elementAt(1), dest, (String)vec.elementAt(3));
			}
			else
			{
				attrib = new FTPAttribute((String)vec.elementAt(0), (String)vec.elementAt(1), (String)vec.elementAt(3));
			}

			xmlGen.addAttribute(attrib);
		}

		return xmlGen.getTask();	
	}

	private String generateTL1Task()
	{
		TaskGenerator xmlGen = new TaskGenerator();

		setBasicTaskParams(xmlGen);

		for(int i = 0; i < taskAttributes.size(); i++)
		{
			Vector vec = (Vector)taskAttributes.elementAt(i);

			TL1Attribute attrib = new TL1Attribute((String)vec.elementAt(0), (String)vec.elementAt(1), (String)vec.elementAt(2), (String)vec.elementAt(3),(String)vec.elementAt(4));

			xmlGen.addAttribute(attrib);
		}

		return xmlGen.getTask();	
	}

	public String generateTask(Hashtable taskDetails)
	{
		String task = null;

		if(taskDetails != null)
		{
			setTaskName((String)taskDetails.get("taskname"));

			String protocol = (String)taskDetails.get("protocol");

			if(protocol.equalsIgnoreCase("combined"))
			{
				setCombinedTask(true);	
			}
			else
			{
				setProtocol(protocol.toLowerCase());
			}

			String taskDesc = (String)taskDetails.get("description");

			if(taskDesc != null)
			{
				setDescription(taskDesc);
			}

			if(((String)taskDetails.get("istemplate")).equals("true"))
			{
				setTemplate(true);
			}

			setTaskAttributes((Vector)taskDetails.get("attributes"));

			setRollbackDocument((String)taskDetails.get("rollback"));	

			task = generateTask();
		}

		return task;
	}

	private void setBasicTaskParams(TaskGenerator taskGenerator) 
	{
		taskGenerator.setTaskName(taskName);
		taskGenerator.setDescription(taskDesc);
		taskGenerator.setOverwrite(isOverwrite);

		if(!isCombinedTask)
		{
			taskGenerator.setProtocol(protocol);
			taskGenerator.setTemplate(isTemplate);

			if((rollbackDocument != null)&&(!rollbackDocument.equals("")))
			{
				taskGenerator.setRollback(true);

				if(rollbackDocument.equals("Current Configuration"))
				{
					taskGenerator.setRollbackType(ConfigConstants.CURRENT_CONFIG);
				}
				else
				{
					taskGenerator.setRollbackType(ConfigConstants.DOCUMENT);
					taskGenerator.setRollbackDocument(rollbackDocument);
				}
			}
		}		
	}
}
