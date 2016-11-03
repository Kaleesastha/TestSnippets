
 package com.adventnet.nms.config ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class SimpleTableSelectionRenderer extends javax.swing.table.DefaultTableCellRenderer
 {
	 public SimpleTableSelectionRenderer()
	 {

	 }
	
	public Component getTableCellRendererComponent(JTable table,
                                               Object value,
                                               boolean isSelected,
                                               boolean hasFocus,
                                               int row,
                                               int column)	
	{
		if (isSelected) 
		{
		   super.setForeground(table.getSelectionForeground());
		   super.setBackground(table.getSelectionBackground());
		}
		else 
		{
			    super.setForeground(table.getForeground());
			    super.setBackground(table.getBackground());				
		}
		setFont(table.getFont());
	    setValue(value); 
		return this;		
	}

 }

