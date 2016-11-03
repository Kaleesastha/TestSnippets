
 package com.adventnet.nms.tools.CustomView ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;
import javax.swing.table.*;
 public class GroupSelectionTableModel  extends DefaultTableModel 
 {
        final String[] columnNames = {"Group Name", "Apply"};
          
        public int getColumnCount() 
	{
            return columnNames.length;
        	}
        
        public String getColumnName(int col) 
	{
            return columnNames[col];
        	}

        public Class getColumnClass(int c)
	{
            return getValueAt(0, c).getClass();
        	}
        public boolean isCellEditable(int row, int col) 
	{
            	if (col ==0)
		{ 
	                return false;
           	 }
		else
		{
	                return true;
           	 }
        	}

        
        
	 
 }

