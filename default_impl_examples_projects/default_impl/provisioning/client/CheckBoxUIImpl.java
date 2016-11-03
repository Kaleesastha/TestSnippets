//$Id: CheckBoxUIImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.provisioning.ui.uielements;

import javax.swing.*;



public class CheckBoxUIImpl extends AbstractXmlUIElement
{

    JCheckBox cbox=new JCheckBox();

    public CheckBoxUIImpl()
    {
	cbox.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public void setEditable(boolean b)
    {
	cbox.setEnabled(b);
    }

    public int getNumberOfColumns()
    {	
	return 1;
    }

    public String getValue()
    {
       return String.valueOf(cbox.isSelected());
    }


    public void setValue(String value)
    {
       cbox.setSelected(value.equals("true"));       
    }

    public JComponent getComponent()
    {
        return cbox;
    }
}

