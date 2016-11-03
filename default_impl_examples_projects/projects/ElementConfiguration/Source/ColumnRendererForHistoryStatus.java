//$Id: ColumnRendererForHistoryStatus.java,v 1.1 2006/08/29 13:56:51 build Exp $
 package com.adventnet.nms.config ; 

 import javax.swing.table.*;
 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class ColumnRendererForHistoryStatus extends javax.swing.JLabel  implements javax.swing.table.TableCellRenderer
 {
	ConfigPanel configPanel = null;
	Color green = new Color(0,102,102);	
	Color blue = new Color(51,0,153);
	
	 public ColumnRendererForHistoryStatus()
	 {

	 }
	
	public ColumnRendererForHistoryStatus(ConfigPanel panel)
	{
		super();
		configPanel = panel;
		setOpaque(true);
		setHorizontalAlignment(SwingConstants.CENTER);
	}	
	
	//--Interface Implementation--//

	public java.awt.Component getTableCellRendererComponent( javax.swing.JTable table, java.lang.Object value, boolean isSelected,
									boolean hasFocus, int row, int column)
	{
		String str = value.toString();
		
		if(str.equalsIgnoreCase("Success"))
		{
			setBackground(green);
			setForeground(Color.white);
		}
		
		else if(str.equalsIgnoreCase("Failed"))
		{
			setBackground(Color.red);
			setForeground(Color.white);
		}
		
		else
		{
			setBackground(blue);
			setForeground(Color.white);
		}
		setText(str);		
		return this;
	}
 }


