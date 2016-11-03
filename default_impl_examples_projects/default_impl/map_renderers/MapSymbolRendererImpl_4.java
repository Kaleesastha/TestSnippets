//$Id: MapSymbolRendererImpl_4.java,v 1.1 2007/07/17 12:57:55 karanmercy Exp $
package com.adventnet.nms.mapui;

import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import java.util.*;
import java.net.*;

import javax.swing.ImageIcon;

import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.severity.*;

public class MapSymbolRendererImpl_4 extends MapSymbolRendererImpl_3 {

	Hashtable normalColorList = new Hashtable();
	Hashtable flashColorList = new Hashtable();

	public void paintIcon(Graphics g,
			MapSymbolComponent mapSymbol,
			Point p,
			Dimension d,
			ImageObserver io,
			Image img,
			boolean selected)
	{
		if ( img != null )
		{
			g.drawImage(img,p.x,p.y,d.width,d.height,io);

			SeverityInfo si = SeverityInfo.getInstance();
			String status = si.getName(mapSymbol.getStatus());
			Image statusImage =(Image)statusImages.get(status);
			int innerRatio = 2;
			int statusWidth = statusImage.getWidth(io);
			int statusHeight = statusImage.getHeight(io);
			int sX = p.x  -  (statusWidth/innerRatio);
			int sY = p.y  -  (statusHeight/innerRatio);

			if(mapSymbol.flash == 0)
			{
				g.drawImage( statusImage, sX, sY, statusWidth, statusHeight, io );
			}
			else if(mapSymbol.flash == 1)
			{
				Color tmpcolor = g.getColor();
				Color color = (Color)flashColorList.get(status);
				if(color == null)
				{
					color = si.getFlashColor(status);
					flashColorList.put(status, color);
				}
				g.setColor(color);
				//g.drawOval(sX, sY, statusWidth, statusHeight);
				g.fillOval(sX, sY, statusWidth, statusHeight);
				g.setColor(Color.white);
				g.drawArc(sX+3, sY, statusWidth, statusHeight, 150, 75);
				g.drawArc(sX+4, sY, statusWidth, statusHeight, 150, 75);
				g.setColor(tmpcolor);
			}
			else if(mapSymbol.flash == -1)
			{
				Color tmpcolor = g.getColor();
				Color color = (Color)normalColorList.get(status);
				if(color == null)
				{
					color = si.getColor(status);
					normalColorList.put(status, color);
				}
				g.setColor(color);
				//g.drawOval(sX, sY, statusWidth, statusHeight);
				g.fillOval(sX, sY, statusWidth, statusHeight);
				g.setColor(Color.white);
				g.drawArc(sX+3, sY, statusWidth, statusHeight, 150,75 );
				g.drawArc(sX+4, sY, statusWidth, statusHeight, 150,75 );
				g.setColor(tmpcolor);
			}
		}

		if (selected)
		{
			g.setColor(Color.blue);
			Stroke oldStroke = ((Graphics2D)g).getStroke();
			BasicStroke strokeType = new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
			((Graphics2D)g).setStroke(strokeType);
			g.drawRect(p.x-6,p.y-6,d.width+12,d.height+12);
			((Graphics2D)g).setStroke(oldStroke);
		}

	} /* end paintIcon() */

	public void paintLabelString(Graphics g,MapSymbolComponent mapSymbol, String s0, Point p,Font font )
	{
		/* This draws a centered label below specified point */

		String temp="";
		for(int i=0;i<s0.length();i++)
		{
			if(s0.charAt(i)=='\n' || s0.charAt(i)=='\t')
			{
				continue;
			}
			temp=temp+s0.charAt(i);
		}

		s0=temp;
		String s=s0;
		if ( (s == null) || (font == null) ) return;
		if (s.length() > MapConstants.MAX_LABEL_LENGTH)
			s = s0.substring(0,MapConstants.MAX_LABEL_LENGTH) + "...";//No Internationalisation


		FontMetrics fm = g.getFontMetrics();
		int width = fm.stringWidth(s);
		int height = fm.getHeight();
		int x = p.x - (width/2);
		int y = p.y + height - (height/4);
		int symbWidth = mapSymbol.getWidth();

		g.setColor(Color.blue);

		mapSymbol.setLabelSize(new Rectangle(x,p.y,width+4, height-1));
		g.drawString(s,x+3,y);
	}
	// end of paintLabelString()

	//Reads the severity images from SeverityInfo.conf file and store it in a hashtable.
	private void storeImages()
	{
		try
		{
			SeverityInfo sevInfo = NmsClientUtil.severityInfo;
			Vector sevNames = sevInfo.getNames();
			int totSevs = sevNames.size();
			statusImages = new Hashtable();
			for(int i=0;i<totSevs;i++)
			{
				String sevName = sevNames.elementAt(i).toString();
				SeverityNode sevNode = sevInfo.getSeverityNode(sevName);
				Object obj = sevNode.getAttribute("IMAGE");
				String sevImageName = "";
				if(obj != null)
				{
					sevImageName = obj.toString();
				}
				Image sevImage = getImage(sevImageName);
				if(sevImage == null)
				{
					sevImage = getImage("./images/defaultstatus.png");//No Internationalization
				}
				statusImages.put(sevName,sevImage);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	private Image getImage(String imageName) throws Exception {
		if(NmsClientUtil.applet != null)
		{
			//ImageIcon ic = com.adventnet.apiutils.Utility.findImage(imageName, NmsClientUtil.applet, true);

			// Utility from ApiUtils.jar is not used as the protocol name
			// "http" is hardcoded there and won't work for "https" protocol.
			URL url
			= new URL(NmsClientUtil.applet.getDocumentBase()
					+ "../" + imageName); // No I18N
			ImageIcon ic = NmsClientUtil.getImageIcon(url);

			if(ic == null)
			{
				return null;
			}
			else
			{
				return ic.getImage();
			}
		}
		else
		{
			System.err.println("NMS Applet not yet initialized"); //No Internationalization
			return null;
		}

	}
	
}
