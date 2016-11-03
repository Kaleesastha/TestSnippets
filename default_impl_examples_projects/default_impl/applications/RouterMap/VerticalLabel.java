package com.adventnet.nms.examples.routermap;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class VerticalLabel extends JLabel
{
	private String text="";
	public VerticalLabel()
		{
			setOpaque(true);
		}
	public void setLabel(String text)
		{
		
		}	
    	public void setText(String text) {

        	String oldAccessibleName = null;
        	String oldValue = this.text;
        	this.text = text;
        	firePropertyChange("text", oldValue, text);

       	if (text == null || oldValue == null || !text.equals(oldValue)) 
       		{
		           revalidate();
            		repaint();
        		}
	    }
	
    public void setBackground(Color bg) 
	{
		Color oldBg = getBackground();
		super.setBackground(bg);
		if ((oldBg != null) ? !oldBg.equals(bg) : ((bg != null) && !bg.equals(oldBg))) {
	 		firePropertyChange("background", oldBg, bg);
	     		repaint();
		}
    	}

	public void paintComponent(Graphics g)
		{
			Graphics gCopy = g.create();
			Graphics2D graphics2d = (Graphics2D)gCopy;
			graphics2d.rotate(Math.PI/2);
			gCopy.drawString(text,0,0);
			gCopy.dispose();
		
		}	
}


