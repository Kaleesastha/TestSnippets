/*$Id: PreviewButton.java,v 1.1 2006/08/29 13:57:01 build Exp $*/

 package com.adventnet.nms.mapui; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.border.*;
 import javax.swing.event.*;

 public class PreviewButton extends javax.swing.JToggleButton  implements java.awt.event.MouseListener
 {
	Border origBorder;
	Border bevelBorder;
	Border bevelLowered;
	
	 public PreviewButton()
	 {
		addMouseListener(this);
		origBorder = BorderFactory.createEmptyBorder();
		setBorder(origBorder);
		bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		bevelLowered = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		setPreferredSize(new Dimension(80,40));
		setMinimumSize(new Dimension(80,40));
		setModel(new PreviewButtonModel());
		setBackground(new Color(128,128,128));
		
	 }
	//--Interface Implementation--//

	public void mousePressed( java.awt.event.MouseEvent arg0)
	{
	}
	public void mouseReleased( java.awt.event.MouseEvent arg0)
	{
	}
	public void mouseClicked( java.awt.event.MouseEvent arg0)
	{
	}
	public void mouseExited( java.awt.event.MouseEvent arg0)
	{
		if(!isSelected())
		{
			setBorder(origBorder);
		}
	}
	public void mouseEntered( java.awt.event.MouseEvent arg0)
	{
		if(!isSelected())
		{
			setBorder(bevelBorder);
		}
	}
	
	class PreviewButtonModel extends ToggleButtonModel
	{
		public void setSelected(boolean b)
		{
			super.setSelected(b);
			if(isSelected())
			{
				setBorder(bevelLowered);
			}
			else
			{
				setBorder(origBorder);
			}
		}
	}
 }



