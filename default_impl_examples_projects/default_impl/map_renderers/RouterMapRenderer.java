// $Id: RouterMapRenderer.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.mapui;

import java.awt.*;
import java.awt.image.*;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.util.NmsClientUtil;
import java.util.Vector;
import java.util.Properties;
import java.util.StringTokenizer;


public class RouterMapRenderer  extends  MapSymbolRendererImpl_3
{
	public RouterMapRenderer(){
        }
	public void paintShapeAndSeverity(Graphics g,MapSymbolComponent mapSymbol,Point p,Dimension d, Color bg,int type)
	{
		if ( mapSymbol.getParent() != null ) //Interfaces
		{
			if ( bg != null )
			{
				g.setColor(bg);
				String label=mapSymbol.getLabel().trim();
				if (label.startsWith("Serial") || label.startsWith("serial"))
				{
					g.fillRect(p.x,p.y-5,65,30);
				}
				else	
				{
					g.fillRect(p.x,p.y-5,25,30); //Width & Height of Ethernet image
				}	 
			}	  
		} 	
		// super.paintShapeAndSeverity(g,mapSymbol,p,d,bg,type);
	}

	public void paintIcon(Graphics g,MapSymbolComponent mapSymbol,Point p, Dimension d, ImageObserver io,Image img,boolean selected)
	{

		if (mapSymbol.getParent() != null) //Interfaces
		{
			if (img != null)
				g.drawImage(img,p.x,p.y-5,img.getWidth(io),img.getHeight(io),io); //For adjustment

			//Paint name at the bottom of the interfaces
			Font font = NmsClientUtil.getFont();//new Font("Verdana",Font.PLAIN,10);
			g.setFont(font);
			g.setColor(Color.black);
			String label=mapSymbol.getLabel();

			if ( mapSymbol.getLabel().startsWith("Ethernet") || mapSymbol.getLabel().startsWith("ethernet") )
			{
				label = "Eth"+(mapSymbol.getLabel()).charAt(mapSymbol.getLabel().length()-1);//No Internationalization		
				g.drawString(label,p.x+5,p.y+37);
			} 	
			else if ( mapSymbol.getLabel().startsWith("Serial") || mapSymbol.getLabel().startsWith("serial"))
			{
				g.drawString(mapSymbol.getLabel(),p.x+20,p.y+37);	
			}
			else //Label other than Serial & Ethernet
			{
				if ( (mapSymbol.getLabel()).length() > 4 )
					g.drawString((mapSymbol.getLabel()).substring(0,4)+"...",p.x+5,p.y+37);//No Internationalization	
			}		

			if ( selected)
			{
				int markHt = d.height/5;
				if (markHt < 5) markHt = 5;
				g.fillRect(p.x-markHt, p.y-markHt, markHt,markHt);
				g.fillRect(p.x+d.width, p.y-markHt, markHt,markHt);
				g.fillRect(p.x-markHt, p.y+d.height, markHt,markHt);
				g.fillRect(p.x+d.width, p.y+d.height, markHt,markHt);			

			}	

		}
		else  //Router
		{
			super.paintIcon(g,mapSymbol,p,d,io,img,false);
		        Font f= NmsClientUtil.getFont();//new Font ("Dialog",Font.BOLD,d.width/65);    
                        g.setFont(f);
		        int x = p.x+5;
                        int y = p.y + d.height-2;
                        g.setColor(Color.white);

                        //Displaying the name of the switch
//                        g.drawString(mapSymbol.getName(),x+12,y-15);

			//g.setColor(Color.black);
			//g.setFont(new Font("Verdana",Font.PLAIN,11));
			g.drawString(mapSymbol.getName(),p.x+(d.width*7/8)-50,p.y+(d.height*6/8));

			if (selected)
			{
				Color tempColor = g.getColor();

				int markHt = d.height/10;
				if (markHt < 3) markHt = 3 ;
				g.setColor(new Color(0,0,128));
				//15 is the small line length        +7 and -9 is for adjustment ie to bring the arcs closer to the object
				g.drawLine(p.x-markHt+7,p.y-markHt,p.x-markHt+15+7,p.y-markHt);
				g.drawLine(p.x-markHt+7,p.y-markHt,p.x-markHt+7,p.y-markHt+15);

				g.drawLine(p.x-markHt+7,p.y+d.height+markHt-10,p.x-markHt+7,p.y+d.height+markHt-15-10);
				g.drawLine(p.x-markHt+7,p.y+d.height+markHt-10,p.x-markHt+15+7,p.y+d.height+markHt-10);

				g.drawLine(p.x+d.width+markHt-9,p.y-markHt,p.x+d.width+markHt-15-9,p.y-markHt);
				g.drawLine(p.x+d.width+markHt-9,p.y-markHt,p.x+d.width+markHt-9,p.y-markHt+15);

				g.drawLine(p.x+d.width+markHt-9,p.y+d.height-10+markHt,p.x+d.width+markHt-15-9,p.y+d.height+markHt-10);
				g.drawLine(p.x+d.width+markHt-9,p.y+d.height-10+markHt,p.x+d.width+markHt-9,p.y+d.height-10+markHt-15);
				g.setColor(tempColor);
			}
		}
	}

	public void paintLabelString(Graphics g,MapSymbolComponent mapSymbol, String s0, Point p,Font font )
	{

	}

}

