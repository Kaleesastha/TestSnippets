 /* $Id: TL1TableCellRenderer.java,v 1.1 2006/08/29 13:57:02 build Exp $ */

package com.adventnet.nms.runtimeconfig;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class TL1TableCellRenderer extends javax.swing.table.DefaultTableCellRenderer
{
    Border cellBorder;
	
    public TL1TableCellRenderer()
    {
        super();
        cellBorder = BorderFactory.createLineBorder(Color.black);
    }
	
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) 
    {
        super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        if(hasFocus)
        {
            setBorder(cellBorder);
        }
        return this;
    }
}

