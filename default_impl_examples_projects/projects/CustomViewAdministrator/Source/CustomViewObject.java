//$Id: CustomViewObject.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $

 package com.adventnet.nms.tools.CustomView ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class CustomViewObject  
 {
	public String custViewId=null;
	public String custViewType=null;
	public String custViewName=null;
	public String userName=null;
	public String treeIcon=null;
	public String parentId=null;
	 public CustomViewObject(String viewid,String type,String viewName,String user,String icon,String id)
	 {
	      custViewId=viewid;
	      custViewType=type;	
	custViewName=viewName;	
	userName=user;
	treeIcon=icon;
	parentId=id;
		
	 }
           
	public String getCustViewId()
	{
	      return custViewId;	
	}
	public String getCustViewType()
	{
	      return custViewType;	
	}

	public String getCustViewName()
	{
		return custViewName;
	}
	
	public String getUserName()
	{
		return userName;
	}
	public String getTreeIcon()
	{
		return treeIcon;
	}
	public String getParentId()
	{
		return parentId;
	}
	public String toString()
	{
		return custViewName;
	}
	public void setTreeIcon(String s)
	{
		treeIcon=s;
	}
	public void setCustViewName(String s)
	{
		custViewName=s;
	}
	public void setParentId(String s)
	{
		parentId=s;
	}
}








