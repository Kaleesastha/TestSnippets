//$Id: SelectionFlag.java,v 1.1 2006/08/29 13:56:51 build Exp $ 

package com.adventnet.nms.config ; 

import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SelectionFlag extends javax.swing.JCheckBox  implements javax.swing.table.TableCellRenderer
{
	private ConfigPanel configPanel = null;

	public SelectionFlag(ConfigPanel configPanel)
	{
		this.configPanel = configPanel;

		setOpaque(true);
		setHorizontalAlignment(SwingConstants.CENTER);		
		setBorder(new BevelBorder(BevelBorder.LOWERED));

		//setBackground(Color.white);
		//setBorderPaintedFlat(false);
	}

	//--Interface Implementation--//

	public java.awt.Component getTableCellRendererComponent( javax.swing.JTable table, 
			java.lang.Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		if(((MultipleListSelectionObject)value).getState())
		{			
			setSelected(true);
		}
		else
		{
			setSelected(false);
		}

		if(isSelected)
		{
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		}
		else 
		{
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}

		return this;
	}
}






