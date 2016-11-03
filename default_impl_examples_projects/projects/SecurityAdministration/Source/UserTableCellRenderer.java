//$Id: UserTableCellRenderer.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.security.ui ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;
 import java.util.*;
 import com.adventnet.nms.util.CommonBuilderUIInterface;

 public class UserTableCellRenderer extends javax.swing.table.DefaultTableCellRenderer
 {
     private Color unselectedForeground; 
     private Color unselectedBackground; 
     ImageIcon group = null;
     ImageIcon user = null;
     ImageIcon disabled = null;	
     Vector disabledvalues;	
	
	 public UserTableCellRenderer()
 	 {
		group = AuthMain.getBuilderUiIfInstance().getImage("group1.png");
		user =AuthMain.getBuilderUiIfInstance().getImage("user1.png");
		disabled = AuthMain.getBuilderUiIfInstance().getImage("user_disabled.png");
		setOpaque(true);
		disabledvalues = new Vector();
 	 }
             
     public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) 
	{
	 	 if(column == 0)
		{
 		      setIcon(user);
		}
     	     else if(column == 1)
		{
		      setIcon(group);		
		}			
		
	if(disabledvalues.contains( value ))	
	 	{
		     if(column == 0)
		     setIcon(disabled);
	 	}
	else
		{
		     if(column == 0)
		     setIcon(user);
		}	

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















