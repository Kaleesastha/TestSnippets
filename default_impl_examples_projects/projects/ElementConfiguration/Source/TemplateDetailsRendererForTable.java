
 package com.adventnet.nms.config ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class TemplateDetailsRendererForTable extends javax.swing.JLabel  implements javax.swing.table.TableCellRenderer
 {
	Color red = new Color(153,255,153);
	Color green = new Color(204,255,255);
	Color white = new Color(255,255,204);
	 public TemplateDetailsRendererForTable()
	 {
		super();
		setOpaque(true);
		setForeground(Color.black);
	 }
	//--Interface Implementation--//

	public java.awt.Component getTableCellRendererComponent( javax.swing.JTable table, java.lang.Object value, boolean isSelected, 
							boolean hasFocus, int row, int column)
	{
		if(column == 0)
		{
			if(value.toString().startsWith("N"))
			{
				setBackground(red);
			}
			
			else if(value.toString().startsWith("I"))
			{
				setBackground(green);
			}
			else
			{
				setBackground(white);
			}
		}
		
		if(column == 1)
		{
			String str = table.getValueAt(row,0).toString();
			if(str.toString().startsWith("N"))
			{
				setBackground(red);
			}
			
			else if(str.toString().startsWith("I"))
			{
				setBackground(green);
			}
			else
			{
				setBackground(white);
			}
		}
		
		setText(value.toString());
		return this;
	}
 }

