
//$Id: SmartProgressBar.java,v 1.1 2006/08/29 13:57:02 build Exp $

package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
public class SmartProgressBar extends JComponent
{
	boolean dir=true;
	int bubbleWidth=0;
	int dx = 0;
	static int offset=0;
	boolean firstTime=true;
	Color bubbleColor = null;
	Graphics g;

	public SmartProgressBar()
	{
			super();
			bubbleWidth=0;
			bubbleColor = Color.black;
			dx = 2;
			setMySize();
	}

	public void resetOffset()
	{
		bubbleWidth = 0;
	}

	public void setOffset(int value)
	{
		bubbleWidth = value;
		offset = 0;
	}
	
	public SmartProgressBar(int width , int increment , Color color )
	{
			super();
			this.bubbleWidth = width;
			this.bubbleColor = color;
			dx = increment;
	}

	public void setMySize()
	{
		setPreferredSize(new Dimension(180,14));
		setMinimumSize(new Dimension(180,14));
		setMaximumSize(new Dimension(180,14)); 
	}

	public void moveToNextPosition()
	{
			if ( dir )
			{
					offset=offset+dx;
			}
			else
			{
					offset=offset-dx;
			}
			if ( offset >= getSize().width -bubbleWidth)
					dir=false;
			if ( offset <=0)
					dir=true;
	}

	protected void paintComponent(Graphics g)
	{
			if(g!= null)
			{
					g.setColor(bubbleColor);
					g.fillOval(offset,0,bubbleWidth,getSize().height);
			}
			else
			{
				System.out.println("Graphics null");
			}
	}
	
}
