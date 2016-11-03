//$Id: PropertyTableCellRenderer.java,v 1.1 2006/08/29 13:57:02 build Exp $

package com.adventnet.security.ui ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;
 import com.adventnet.nms.util.CommonBuilderUIImpl;

 public class PropertyTableCellRenderer extends javax.swing.table.DefaultTableCellRenderer
 {
	private Color unselectedForeground; 
     private Color unselectedBackground; 
	 public PropertyTableCellRenderer()
	 {
		setOpaque(true);
	 }
 


	
            
public Component getTableCellRendererComponent(JTable table, Object value,
                          boolean isSelected, boolean hasFocus, int row, int column) 
	{
		
	
	if (isSelected) 
	{
	     super.setForeground(table.getSelectionForeground());
	     super.setBackground(table.getSelectionBackground());
	}
		
	else
	{
	    super.setForeground((unselectedForeground != null) ? unselectedForeground : table.getForeground());
	    super.setBackground((unselectedBackground != null) ? unselectedBackground : table.getBackground());
	}
		
	 setFont(table.getFont());
            setValue(value); 
	 setText(value.toString());
        
	return this;
    }
	
}	









