package com.adventnet.nms.examples.routermap;


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
import java.applet.*;
 import javax.swing.event.*;
 import com.adventnet.nms.util.NmsClientUtil;

 public class ImagePanel  extends JPanel
 {
	Image img;
        Applet applet = null;
	public ImagePanel(Applet applet)
	{
            this.applet = applet;               
		setDoubleBuffered(true);
	}	
	public void setBackgroungImage(Image img)
	{
		this.img = img;
	}	
	public void paintComponent(Graphics g)
		{
	Image img = NmsClientUtil.getImage(applet.getDocumentBase() +"../images/world3.png"); //No Internationalisation
			
			g.drawImage(img,0,0,this);
		}	

 }



