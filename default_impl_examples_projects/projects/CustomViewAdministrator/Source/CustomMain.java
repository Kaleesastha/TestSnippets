//$Id: CustomMain.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $

 package com.adventnet.nms.tools.CustomView ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;
import com.adventnet.nms.fe.common.NmsTreeAPI;
import com.adventnet.nms.fe.common.PanelTreeNode;
//import com.adventnet.nms.fe.common.ViewData;
import com.adventnet.nms.fe.common.TableColumn;
import java.util.*;
 public class CustomMain 
 {
	public NmsTreeAPI  treeAPI=null;
	PanelTreeNode treeNode=null;
	Properties panelProperties=null;
	Properties viewProperties=null;
	Random random=new Random();
	String customViewId="";
	public CustomMain()
	 {

	 }
	public boolean create(String viewName,String module,String user,String parent,Properties tableColumns,Properties treeProperties,Properties viewProperties)
	{
		boolean result=true;
		//To create a viewId random number is generated
		int val=random.nextInt();
		//To avoid -ve ids.
		if(val < 0)
		{
			val = Math.abs(val);
		}
		String  id  = String.valueOf(val);
		
		customViewId=module+"."+id;
		treeNode=new PanelTreeNode(customViewId);
		treeNode.setUserName(user);
		treeNode.setParent(parent);
		treeNode.setModuleName(module);
		treeNode.setNodeType("DEVICE");
		panelProperties=getPanelProperties(customViewId,viewName,module,treeProperties);
		viewProperties=getViewProperties(viewName,module,tableColumns,viewProperties,"create");
		if(panelProperties!=null)
		{
			treeNode.setPanelProperties(panelProperties);
		}
		if(viewProperties!=null)
		{
			treeNode.setViewProperties(viewProperties);
		}
		try
		{
			result= treeAPI.addNode(treeNode);
		}
		catch(Exception e)
		{
			System.out.println("Exception while useing addNode in treeAPI"+e.getMessage());
		
			return false;
		}
		return result;
	}
	public Properties getPanelProperties(String viewId,String viewName,String module,Properties treeProps)
	{
		
		if(treeProps==null)	
		{
			treeProps=new Properties();
		}
			treeProps.put("TREE-NAME",viewName);
			treeProps.put("ID",viewId);
			treeProps.put("MODULE-NAME",module);
			treeProps.put("TARGET","center");
			treeProps.put("Client","All");
			if(module.equals("Network Database"))
			{
				treeProps.put("PANEL-KEY","NmsListView");
				treeProps.put("URL","jsp/NetTable.jsp");
			}
			else if(module.equals("Alerts"))
			{
				treeProps.put("PANEL-KEY","AlertApplet");
				treeProps.put("URL","jsp/GetAlerts.jsp");			
			}
			else if(module.equals("Events"))
			{
				treeProps.put("PANEL-KEY","EventBrowser");
				treeProps.put("URL","jsp/GetEvents.jsp");						
			}	
			else if(module.equals("Stats Admin"))
			{
				treeProps.put("PANEL-KEY","StatsAdminPanel");
				treeProps.put("URL","jsp/Report.jsp");						
			}
			return treeProps;
	}	
	public Properties getViewProperties(String viewName,String module,Properties tableColumn,Properties viewProperties,String s)
	{
		if(viewProperties==null)
		{
			viewProperties=new Properties();
		}
		viewProperties.put("<VIEWNAME>",viewName);
		//for create only we can specify module name not for modification
		if(s.equals("create"))
		{
			viewProperties.put("<MODULE>",module);
		}
		TableColumn[] table=getTableColumn(tableColumn);
		viewProperties.put("FieldsWanted",table);
		return viewProperties;
	}
	public TableColumn[] getTableColumn(Properties column)
	{
		TableColumn[] columns=new TableColumn[column.size()];
		int i=0;
		for(Enumeration e=column.propertyNames();e.hasMoreElements();)
		{
	
			String key=e.nextElement().toString();
			String displayName=(String)column.getProperty(key);
			columns[i] =new TableColumn(key,displayName,75);
			i++;

		}
		return columns;
	}
	public boolean modify(String viewId,String viewName,String module,String user,String parent,Properties tableColumns,Properties treeProperties,Properties viewProperties)		
	{
		boolean result=true;
		treeNode=new PanelTreeNode(viewId);
		treeNode.setUserName(user);
		treeNode.setModuleName(module);
		treeNode.setNodeType("DEVICE");
		panelProperties=getPanelProperties(viewId,viewName,module,treeProperties);
		viewProperties=getViewProperties(viewName,module,tableColumns,viewProperties,"modify");
		if(panelProperties!=null)
		{
			treeNode.setPanelProperties(panelProperties);
		}
		if(viewProperties!=null)
		{
			treeNode.setViewProperties(viewProperties);
		}
		try
		{
			result= treeAPI.modifyNode(treeNode);
			if(result)
			{
				result=treeAPI.moveNode(viewId,user,parent);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception while useing modifyNode in treeAPI"+e.getMessage());
		
			return false;
		}
		return result;
	}
	
 }	











