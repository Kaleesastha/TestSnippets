
//$Id: SeverityTableRenderer.java,v 1.1 2006/08/29 13:57:02 build Exp $

package com.adventnet.nms.runtimeconfig;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class SeverityTableRenderer extends DefaultTableCellRenderer
 {
	
	public SeverityTableRenderer()
	{
		setOpaque(false);
	}
	public Component getTableCellRendererComponent(JTable table,
                                               Object value,
                                               boolean isSelected,
                                               boolean hasFocus,
                                               int row,
                                               int column)	
	{
		if(value == null)
		{
			setValue("");
			return this;
		}
		String temp = (String)value;
		if(temp.indexOf('*') == -1)
		{
			//System.out.println("Valid");
			//setBackground(Color.red);
			setForeground(Color.black);
			setBackground(Color.white);
		}
		else
		{
			setOpaque(true);			
			setBackground(Color.red);				
			setForeground(Color.white);
			//System.out.println("Invalid");				
		}
		if(temp.indexOf("*") != -1)
		setValue(temp.substring(0,temp.indexOf("*")));
		else 
			setValue(value);
		return this;		
	}
 }



