/*
$Id: AboutFrame.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
*/
package com.adventnet.nms.tools.utils;

import com.adventnet.nms.tools.utils.ImgConv;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;

public class AboutFrame extends JWindow
{
	public AboutFrame() throws Exception
	{
		super();
	}	
	
	public void init() 
	{ 
		try
		{
			String imagePath = System.getProperty("user.dir") +File.separator + "images" + File.separator + "about.png";
			ImageIcon image = new ImageIcon(ImgConv.getImage((new File(imagePath)).toURL()));
			JButton imagebut = new JButton();
			imagebut.setFocusPainted(false);
			imagebut.setBorderPainted(false);
			imagebut.setIcon(image);
			getContentPane().add(imagebut);
			setSize(image.getIconWidth(),image.getIconHeight());
			Toolkit tk=Toolkit.getDefaultToolkit();
			Dimension dim=tk.getScreenSize();
			Dimension butsize=getSize();
			setLocation((dim.width-butsize.width)/2,(dim.height-butsize.height)/2);
			imagebut.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent mevt)
				{
						setVisible(false);
				}
			});
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			ex.printStackTrace();
		}
	
	}

/** The show method to show the frame */
	public void setVisible(boolean flag) 
	{	
		super.setVisible(flag);
	}
}
	
