
//$Id: CustomRenderer.java,v 1.1 2006/08/29 13:57:02 build Exp $

package com.adventnet.nms.runtimeconfig;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class CustomRenderer extends DefaultTableCellRenderer
 {
	
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
		else 
		{
			    super.setForeground(table.getForeground());
			    super.setBackground(table.getBackground());				
		}
	
		setFont(table.getFont());

/*		if (hasFocus) 
		{
		    setBorder( UIManager.getBorder("Table.focusCellHighlightBorder") );
	    	if (table.isCellEditable(row, column)) 
			{
	        	super.setForeground( UIManager.getColor("Table.focusCellForeground") );
		        super.setBackground( UIManager.getColor("Table.focusCellBackground") );
		    }
		}
		else*/
			{
		    setBorder(noFocusBorder);
		}

        setValue(value); 
		return this;		
	}
 }

