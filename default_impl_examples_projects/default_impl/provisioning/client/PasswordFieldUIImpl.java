//$Id: PasswordFieldUIImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.provisioning.ui.uielements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PasswordFieldUIImpl extends AbstractXmlUIElement 
{

    JPasswordField pf=new JPasswordField();

    public PasswordFieldUIImpl()
    {
    }

    public void setEditable(boolean b)
    {
        pf.setEditable(b);
    }

    public int addComponents(JComponent parentComp,GridBagConstraints gc,int row,int col,int maxCols)
    {
        if (maxCols <= 2) 
        {
            pf.setColumns(22);
            pf.setPreferredSize(new Dimension(120,30));
            pf.setMinimumSize(new Dimension(120,30));
        }
        else 
        {
            pf.setColumns(14);
            pf.setPreferredSize(new Dimension(75,30));
            pf.setMinimumSize(new Dimension(75,30));
        }
        return super.addComponents( parentComp, gc, row, col, maxCols);
    }

    public String getValue()
    {
        return new String(pf.getPassword());
    }

    public void setValue(String value)
    {
        pf.setText(value);
    }

    public JComponent getComponent()
    {
        return pf;
    }
}
