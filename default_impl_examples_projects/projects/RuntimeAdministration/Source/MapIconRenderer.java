
//$Id: MapIconRenderer.java,v 1.1 2006/08/29 13:57:02 build Exp $

package com.adventnet.nms.runtimeconfig;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.Vector;

public class MapIconRenderer extends DefaultTableCellRenderer
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
		Vector v = (Vector)value;
		
		if(v.elementAt(0) != null)
		{	
        	setIcon((ImageIcon)v.elementAt(0));
		}	
		else
		{
			setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getScaledImage("broken.png","images/runtimeadmin",16,16,Image.SCALE_DEFAULT));
		}
			setText((String)v.elementAt(1));
		return this;		
	}
 }







