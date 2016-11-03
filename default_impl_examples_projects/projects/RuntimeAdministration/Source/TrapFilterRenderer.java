
//$Id: TrapFilterRenderer.java,v 1.1 2006/08/29 13:57:02 build Exp $

package com.adventnet.nms.runtimeconfig;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class TrapFilterRenderer extends DefaultTableCellRenderer
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

		setBorder(noFocusBorder);
        	setIcon((ImageIcon)value); 
		return this;		
	}
 }


