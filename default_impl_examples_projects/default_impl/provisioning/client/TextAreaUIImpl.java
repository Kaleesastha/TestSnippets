//$Id: TextAreaUIImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.provisioning.ui.uielements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextAreaUIImpl extends AbstractXmlUIElement
{
    JTextArea textarea=new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textarea);

    public TextAreaUIImpl()
    {
    }
    public void setEditable(boolean b)
    {
     	textarea.setEditable(b);
    }

    public int getNumberOfColumns()
    {
	return 2;
    }

    public String getValue()
    {
   	 return textarea.getText();
    }
    
    public void setValue(String value)
    {
        textarea.setText(value);
    }
 
   public JComponent getComponent()
    {
		textarea.setLineWrap(true);
		textarea.setRows(2);
        return scrollPane;
    }
  
}
