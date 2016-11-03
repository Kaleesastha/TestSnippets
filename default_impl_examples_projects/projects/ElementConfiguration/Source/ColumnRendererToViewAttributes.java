
 package com.adventnet.nms.config ; 

 import javax.swing.border.*;
 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class ColumnRendererToViewAttributes extends javax.swing.JLabel  implements javax.swing.table.TableCellRenderer
 {
	 public ColumnRendererToViewAttributes()
	 {
		super();
	 }
	
	public ColumnRendererToViewAttributes(ConfigPanel configPanel)
	{
		super();
		setOpaque(true);
		setBackground(Color.blue);
		setForeground(Color.white);
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setText(configPanel.configClientUtils.getString(">>"));
	}
	//--Interface Implementation--//

	public java.awt.Component getTableCellRendererComponent( javax.swing.JTable arg0, java.lang.Object arg1, boolean arg2, boolean arg3, int arg4, int arg5)
	{
		return this;
	}
 }

