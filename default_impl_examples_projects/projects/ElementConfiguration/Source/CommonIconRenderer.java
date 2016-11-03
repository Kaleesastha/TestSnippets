
 package com.adventnet.nms.config ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class CommonIconRenderer extends javax.swing.table.DefaultTableCellRenderer
 {
	ImageIcon icon = null;
	
	 public CommonIconRenderer()
	 {

	 }
	
	public CommonIconRenderer(ImageIcon icon)
	{
		super();
		this.icon = icon;
		super.setOpaque(true);
		super.setIconTextGap(7);
	}
	
	public java.awt.Component getTableCellRendererComponent( javax.swing.JTable table, 
java.lang.Object value, boolean isSelected,
							boolean hasFocus, int row, int column)
	{
		if(isSelected)
		{
			super.setBackground(table.getSelectionBackground());
			super.setForeground(table.getSelectionForeground());
		}
		
		else
		{
			super.setBackground(table.getBackground());
			super.setForeground(table.getForeground());
		}
		setIcon(icon);
		setFont(table.getFont());
		setText(value.toString());
		return this;
	}
 }

