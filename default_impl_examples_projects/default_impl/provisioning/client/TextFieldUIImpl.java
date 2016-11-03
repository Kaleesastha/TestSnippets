//$Id: TextFieldUIImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.provisioning.ui.uielements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextFieldUIImpl extends AbstractXmlUIElement 
{

    JTextField tf=new JTextField();

    public TextFieldUIImpl()
    {
    }

    public void setEditable(boolean b)
    {
        tf.setEditable(b);
    }

    public int addComponents(JComponent parentComp,GridBagConstraints gc,int row,int col,int maxCols)
    {
        if (maxCols <= 2) 
        {
            tf.setColumns(22);
            tf.setPreferredSize(new Dimension(120,30));
            tf.setMinimumSize(new Dimension(120,30));
        }
        else 
        {
            tf.setColumns(14);
            tf.setPreferredSize(new Dimension(75,30));
            tf.setMinimumSize(new Dimension(75,30));
        }
        return super.addComponents( parentComp, gc, row, col, maxCols);
    }

    public String getValue()
    {
        return tf.getText();
    }

    public void setValue(String value)
    {
        tf.setText(value);
    }

    public JComponent getComponent()
    {
        return tf;
    }
}
