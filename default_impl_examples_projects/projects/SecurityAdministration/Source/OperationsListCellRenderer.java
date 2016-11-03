//$Id: OperationsListCellRenderer.java,v 1.1 2006/08/29 13:57:02 build Exp $
 package com.adventnet.security.ui ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;
 import com.adventnet.nms.util.CommonBuilderUIInterface;


public class OperationsListCellRenderer extends JLabel implements ListCellRenderer 	{
		
	
     public OperationsListCellRenderer()
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
		
		
		
		setText(((ListObject)value).toString());
		setBackground(Color.white);

	try
 			{
			
	if(((ListObject)value).getState())
	  	{
							
			setIcon(AuthMain.getBuilderUiIfInstance().getImage("right.png"));
			setForeground(Color.blue);
	  	}
	else
	 	{	
		
			setIcon(null);
      		 	setForeground(Color.gray);
					
		}		
				
 	  }
	catch(Exception e)
   		{
			               
  		}	
		
          return this;
     }


 }
 













