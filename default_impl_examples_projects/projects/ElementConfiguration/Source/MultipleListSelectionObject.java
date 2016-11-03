//$Id: MultipleListSelectionObject.java,v 1.1 2006/08/29 13:56:51 build Exp $
package com.adventnet.nms.config ; 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MultipleListSelectionObject 
{
	boolean state = false;
	Object object = null;
	String str = "";

	public MultipleListSelectionObject(Object obj)
	{
		this.object = obj;
		state = false;
	}

	public MultipleListSelectionObject(String str)
	{
		this.str = str;
		state = false;
	}

	public MultipleListSelectionObject(String str,boolean state)
	{
		this.str = str;
		this.state = state;
	}

	public void setTrueState()
	{
		state = true;
	}

	public void setFalseState()
	{
		state = false;
	}

	public boolean getState()
	{
		return state;
	}

	public String getString()
	{
		return str;
	}

	public String toString()
	{
		if(object != null)
			return object.toString();
		else return str;
	}

}






