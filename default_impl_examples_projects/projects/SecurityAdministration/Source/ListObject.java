//$Id: ListObject.java,v 1.1 2006/08/29 13:57:02 build Exp $
 
 package com.adventnet.security.ui ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class ListObject 
 {
	private Object ob = null;
	private boolean state = false;
	
	public ListObject(Object userOb)
	 {
		ob = userOb;
	 }
	public boolean getState()
	{
		return state;
	}	
	public void setState(boolean state)
	{
		this.state = state;
	}
	
 	public String toString()
	 {
	 	return ob.toString();
	 } 	
 }






