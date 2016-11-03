
//$Id: TrapStateRenderer.java,v 1.1 2006/08/29 13:57:02 build Exp $

package com.adventnet.nms.runtimeconfig;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class TrapStateRenderer extends DefaultTableCellRenderer
 {
    private Color unselectedForeground; 
    private Color unselectedBackground; 
	
	
	public Component getTableCellRendererComponent(JTable table,
                                               Object value,
                                               boolean isSelected,
                                               boolean hasFocus,
                                               int row,
                                               int column)	
	{
		
	if (isSelected) {
	   super.setForeground(table.getSelectionForeground());
	   super.setBackground(table.getSelectionBackground());
	}
	else {
	    super.setForeground((unselectedForeground != null) ? unselectedForeground 
	                                                       : table.getForeground());
	    super.setBackground((unselectedBackground != null) ? unselectedBackground 
	                                                       : table.getBackground());
	}
	
	setFont(table.getFont());

	if (hasFocus) {

	    setBorder(noFocusBorder);			
	    if (table.isCellEditable(row, column)) {

	    }
	} else {
	    setBorder(noFocusBorder);
	}		
		
		if(value == null)
		{
			setValue("");
			return this;
		}
		
		String temp = (String)value;
		if(temp.indexOf('*') == -1)
		{
			setForeground(new  Color(0,100,0));
		}
		else
		{
			setForeground(Color.red);
		}

		if(temp.indexOf("*") != -1)
			setValue(temp.substring(0,temp.indexOf("*")));
		else 
			setValue(value);
		
		return this;
	}		
 }


