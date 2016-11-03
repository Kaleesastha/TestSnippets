//$Id: EthernetMapLinkRenderer.java,v 1.1.4.3 2012/03/29 07:27:12 vijayalakshmiv Exp $
package com.adventnet.nms.mapui;


import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;

import com.adventnet.nms.mapui.MapLinkComponent;
import com.adventnet.nms.mapui.MapLinkRendererInterface;
import com.adventnet.nms.util.NmsClientUtil;

public class EthernetMapLinkRenderer implements MapLinkRendererInterface {

	
	
	public boolean downInObject(int x, int y, MapLinkComponent link) {
		// TODO Auto-generated methodstub
		
		
		if(link !=null)
		{	
		Point destPoint=link.getNp0();
		if(destPoint !=null)
		{	
		int x0=destPoint.x;
		int y0 = destPoint.y;
		//Note: width and height of interface image
		int w = 15;
		int h = 14;
		int x1 = x0-w;
		int y1 = y0-h;
		if(x<=(x0+(w/2)))
			x1=x0-(w/2);
		if(y>=(y0-(h/2)))
			y1=y0-(h/2);
			
		Rectangle rect = new Rectangle(x1,y1,w+3,h+3);
		if(rect.contains(x,y))
		{
		
		return true;
		}
		}
		}
		return false;
	}


	public String getToolTipText(MapLinkComponent link, int x, int y) {
		
		return link.getName();
		
	}

	
	public void paintLabelString(Graphics g, MapLinkComponent mapLink,
			Point sourcePoint, Point destPoint, String label, Font font) {
		// TODO Auto-generated method stub
		

	}
	
	
	public void paintMapLink(Graphics g, MapLinkComponent mapLink,
			Point sourcePoint, Point destPoint, Color status, boolean selected) {
		// TODO Auto-generated method stub
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(status);
		try {
			ImageIcon icon =NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/interface.png"));//No Internationalization
		
		 
		int y0=sourcePoint.y-25;
		//g2.fillRect(sourcePoint.x,sourcePoint.y,r,height);
		g2.drawLine(sourcePoint.x,y0,destPoint.x,destPoint.y);
		
		int x=destPoint.x-(icon.getIconWidth()/2);
		int y = destPoint.y-(6*icon.getIconHeight()/10);
		//int y1=destPoint.y;
		//int x2=destPoint.x+w;
		g2.drawImage(icon.getImage(),x,y,icon.getImageObserver());
		//render link like an line with the interface at the end
		mapLink.setLinkType(1);
		if(selected)
		{
			Composite def = g2.getComposite();
			g2.setComposite(makeComposite(0.2f));
			g2.setColor(new Color(0,93,255));
			int width=icon.getIconWidth()+10;
			int height = icon.getIconHeight()+10;
			
			int x1=destPoint.x-(width/2);
			int y1 =destPoint.y-(6*height/10);
			g2.fillRoundRect(x1, y1,width,height,10,10);
			
			g2.setComposite(def);
		}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BasicStroke normal = new BasicStroke(mapLink.getThickness());
		g2.setStroke(normal);
		
	}
	private AlphaComposite makeComposite(float alpha) {
	    int type = AlphaComposite.SRC_OVER;
	    return(AlphaComposite.getInstance(type, alpha));
	  }

	
}
