//$Id: CustomListCellRenderer.java,v 1.1 2006/08/29 13:57:02 build Exp $
 package com.adventnet.security.ui ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;


public class CustomListCellRenderer extends JLabel implements ListCellRenderer 

{
		
	
     public CustomListCellRenderer()
	{
        setOpaque(true);
	}
	
     public Component getListCellRendererComponent(
		
         JList list,
         Object value,
         int index,
         boolean isSelected,
         boolean cellHasFocus)
     {
		
			
		
		setText(value.toString());
		setBackground(Color.white);

		try
			{
			
	if(((ListObject)value).getState())
		{
							
			setIcon(AuthMain.getBuilderUiIfInstance().getImage("right.png"));
			setForeground(new Color(0,150,0));
		}
	else
		{	
		
		setIcon(AuthMain.getBuilderUiIfInstance().getImage("wrong.png"));
			setForeground(Color.red);
					
		}
		
		if(isSelected)	
			{
				setBackground(new Color(170,170,255));
			}	
				
	  }
	catch(Exception e)
		{
			               
		}	
		
          return this;
     }


 }
 














