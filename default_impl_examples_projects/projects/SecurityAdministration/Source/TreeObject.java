//$Id: TreeObject.java,v 1.1 2006/08/29 13:57:02 build Exp $
 package com.adventnet.security.ui ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class TreeObject 
 {
	private boolean included = false;
	private boolean selected = false;
	public int select = 0;
	private Object userOb = null;
	private boolean excluded = false;
	private String originalStr = null;
	
	public TreeObject(Object userObject)
	 {
 		userOb = userObject;		
	 }
	
	public TreeObject(Object userObject, String originalStr)
	{
		userOb = userObject;
		this.originalStr = originalStr;
	}
	
	public String getString()
	{
		if(originalStr != null)
		{
			return originalStr;	
		}
		return userOb.toString();
	}
	
	public void setInt(int i)
	{
		select = i;		
	}	
	public int getInt()
	{
		return select;	
	}	
	
	public boolean isIncluded()
	{
 		return included;	
 	}	

	public boolean isExcluded()
	{
		return excluded ;
	}	
	public boolean isSelected()
	 {
		return selected;
 	 }	
	
	public void setIncluded(boolean included)
	 {
		this.included = included;
 	 }	
	
	public void setSelected(boolean selected)
	 {
		this.selected = selected;
 	 }	
	
	public void setExcluded(boolean excluded)
	{
		this.excluded = excluded;
	}	
  	public String toString()
	 {
		return userOb.toString();
	 }	
	
 }







