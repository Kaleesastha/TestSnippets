/*
$Id: MapLinkRendererImpl.java,v 1.1.1.1.4.1 2013/07/24 09:52:45 vijayalakshmiv Exp $
*/

/* MapLinkRendererImpl.java */

package com.adventnet.nms.mapui;

import java.awt.*;
import com.adventnet.nms.severity.SeverityInfo;



/**
 * MapLinkRendererImpl  defines how a MapLinkComponent is to be  represented in the Map . 
 * This <code> MapLinkRendererImpl  </code> is an implementation provided by WebNMS
 * as an example . The style , shape and color of an Map Link  are specified 
 * by this implementation .It also specifies the mouse down response for an Map Link
 * and its tool tip .
 * @see com.adventnet.nms.mapui.MapLinkComponent  
 * @see com.adventnet.nms.mapui.MapLinkRendererInterface
 * @see com.adventnet.nms.mapui.MapLinkRendererImpl_2
 * @see com.adventnet.nms.mapui.MapSymbolComponent
 */
public class MapLinkRendererImpl implements MapLinkRendererInterface {



	/**
	 * To paint Map Link between source object and destination object 
	 * @param      g 	    - Graphics of Map Link object
	 * @param      mapLink     - Reference of MapLinkComponent object
	 * @param      sourcePoint - Source point (start point) of Map Link
	 * @param      destPoint   - Destination point (end point) of Map Link
	 * @param      status      - Color of Map Link with respect to the status
	 * @param      selected    - Whether the link is selected or not
	 */
	public void paintMapLink(Graphics g,MapLinkComponent mapLink,Point sourcePoint,Point destPoint,Color bg,boolean selected)
	{
		if(mapLink.getUserProperty("linkIndex") !=null)
		{
			int index = Integer.parseInt(mapLink.getUserProperty("linkIndex"));
			int  noOfLinksWithSameSourceDest = Integer.parseInt(mapLink.getUserProperty("totalLinks"));
			setPointsbasedonIndex(mapLink, index, noOfLinksWithSameSourceDest);
			sourcePoint = mapLink.p0;
			destPoint=mapLink.np0;
		}
		int nx = sourcePoint.x;
		int ny = sourcePoint.y;
		int nlt = destPoint.x - sourcePoint.x;
		int nht = destPoint.y - sourcePoint.y;
		int nthick = mapLink.getThickness();
		if (nthick < 1) nthick = 1;

		//store current color of g in tmp
		Color tmp = g.getColor();
		g.setColor(bg);

		int t = (nthick-1)/2;
		if (Math.abs(nht) >= Math.abs(nlt)) 
			for (int j = nx - t; j< nx-t+nthick; j++ ) 
			{
				g.drawLine(j,ny,j+nlt,ny+nht);
			}
		else 
			for (int j = ny - t; j< ny-t+nthick; j++ ) 
			{
				g.drawLine(nx,j,nx+nlt,j+nht);
			}


		//set black color to draw selected lines
		g.setColor(Color.black);

		//to draw selected lines in both sides of link
		if (selected) 
		{
			if (Math.abs(nht) >= Math.abs(nlt)) 
			{
				int j = nx - 2 - t; 
				g.drawLine(j,ny,j+nlt,ny+nht);
				j = nx-t+nthick+1; 
				g.drawLine(j,ny,j+nlt,ny+nht);

			}  else 
			{
				int j = ny - t-2; 
				g.drawLine(nx,j,nx+nlt,j+nht);
				j= ny-t+nthick+1; 
				g.drawLine(nx,j,nx+nlt,j+nht);
			}
		}
		//resetting the old color to graphics
		g.setColor(tmp);


	}// end paintMapLink()


	private void setPointsbasedonIndex(MapLinkComponent comp,int index, int noOfLinksWithSameSourceDest)
	{
		Point p0=null;
		Point np0=null;
		MapSymbolComponent sourceObj=comp.getSourceObj();
		MapSymbolComponent destObj=comp.getDestObj();
		
		double dx =  (double)  (destObj.p0.x - sourceObj.p0.x);
        double dy = (double)(destObj.p0.y - sourceObj.p0.y);
        double degree  = Math.atan((double)(sourceObj.d0.width/sourceObj.d0.height)); 
        double degree1  = Math.atan((double)(destObj.d0.width/destObj.d0.height)); 
        double wid = Math.sqrt((double)(sourceObj.d0.width * sourceObj.d0.width +  sourceObj.d0.height * sourceObj.d0.height)); 
        double wid1 = Math.sqrt((double)(destObj.d0.width * destObj.d0.width +  destObj.d0.height * destObj.d0.height)); 
        
        int gap = (int)((wid) / (float)(noOfLinksWithSameSourceDest+1));     
        int gap1 = (int)((wid1) / (float)(noOfLinksWithSameSourceDest+1));  
        int xOffset  = (int)(gap*(index+1) * Math.sin(degree));  
        int yOffset  = (int)(gap*(index+1) * Math.cos(degree)); 
        int xOffset1  = (int)(gap1*(index+1) * Math.sin(degree1));  
        int yOffset1  = (int)(gap1*(index+1) * Math.cos(degree1));
        //useOffset = true;                
        if((dy<0 && dx>0) || (dx<0 && dy>0))
        { 
            p0 = new Point(sourceObj.p0.x+xOffset,sourceObj.p0.y +yOffset); 
            np0 = new Point(destObj.p0.x+xOffset1,destObj.p0.y+yOffset1);                       
        }
        else if((dx<0 && dy<0) || (dx>0 && dy>0))
        {
            p0 = new Point(sourceObj.p0.x+xOffset,sourceObj.p0.y -yOffset+ sourceObj.d0.height); 
            np0 = new Point(destObj.p0.x+xOffset1,destObj.p0.y-yOffset1 + destObj.d0.height);
        }
        
        else  if(dx==0)
        {  
            p0 = new Point(sourceObj.p0.x+xOffset,sourceObj.p0.y+(sourceObj.d0.height/2)); 
            np0 = new Point(destObj.p0.x+xOffset,destObj.p0.y+(destObj.d0.height/2));
        }
        else if (dy ==0)
        {
            p0 = new Point(sourceObj.p0.x+(sourceObj.d0.width/2),sourceObj.p0.y+ yOffset); 
            np0 = new Point(destObj.p0.x+(destObj.d0.width/2),destObj.p0.y+ yOffset);
        }
		comp.p0=p0;
		comp.np0=np0;
	}

	/**
	 * This draws a centered label below the Map Link at specified point .  
	 * @param      g 	    - Graphics of Map Link object
	 * @param      mapLink     - Reference of MapLinkComponent object
	 * @param      sourcePoint - Source point (start point) of Map Link
	 * @param      destPoint   - Destination point (end point) of Map Link
	 * @param      label       - String to be displayed
	 * @param      font        - Font to be used for draw the label string
	 */
	public void paintLabelString(Graphics g,MapLinkComponent mapLink,Point sourcePoint,Point destPoint,String label,Font font)
	{
		Font f = font;
		String s = label;
		if ( (s == null) || (f == null) ) return;
		if (s.length() > MapConstants.MAX_LINK_LABEL_LENGTH) 
			s = label.substring(0,MapConstants.MAX_LINK_LABEL_LENGTH) + "...";//No Internationalisation

		//g.setFont(f);
		FontMetrics fm = g.getFontMetrics();
		Point startPos = new Point((sourcePoint.x + destPoint.x)/2, (sourcePoint.y + destPoint.y)/2); 
		int x = startPos.x - (fm.stringWidth(s));    
		int y = startPos.y+fm.getHeight();
		g.setColor(Color.black);
		g.drawString(s,x,y);
		mapLink.setLabelSize(new Rectangle(x,y,fm.stringWidth(s),fm.getHeight()));

	} // end of paintLabelString()



	/**
	 * This method is used to change the tool tip text of an Map Link
	 * @param		link		-Reference of Map Link Object
	* @return	a <code>String </code> that is to be the tool tip for this MapLinkComponent.	 
	 */
	public String getToolTipText(MapLinkComponent link,int x, int y)
	{
		String toolTip = BasicMap.getToolTipProperty().trim();
		if(toolTip.equals("status"))//No Internationalisation
			return SeverityInfo.getInstance().getName(Integer.parseInt(link.getProperty(toolTip)));
		return link.getProperty(toolTip);

	}
	Polygon poly;



	/**  To determine if a mouse down is inside the Map Link 
	 * @param		x		- x  coordinate of mouse event relative to the map
	 * @param		y		- y  coordinate of mouse event relative to the map
	 * @param		link		-Reference of Map Link Object
	 * @return	a <code> boolean  </code> true , if the mouse is down on the MapLinkComponent
	 */
	public boolean downInObject(int x, int y , MapLinkComponent link) 
	{
		if ( (link.getP0() == null) || (link.getNp0() == null) ) return false;

		// checking if the line is inclined horizontally or vertically
		int nlt = link.getNp0().x - link.getP0().x;
		int nht = link.getNp0().y - link.getP0().y;

		//int nthick = String.valueOf(link.getProperty("thickness")) + 1;//No Internationalisation
		int nthick = Integer.parseInt(link.getProperty("thickness")) + 1;//No Internationalisation

		// to increase the line granurality when it is inclined from 35 to 55 degrees
		double angle;
		double x1,x2,y1,y2;
		x1 = link.getP0().x; y1 = link.getP0().y;
		x2 = link.getNp0().x; y2 = link.getNp0().y;
		angle = Math.atan((y2 - y1) / (x2 - x1));
		angle = angle * 180 / 3.14159;
		angle = Math.abs(angle);
		angle = angle % 90;
		if((angle > 35) && (angle <= 55))
		{
			nthick += 1;
		}

		// if the line is  inclined verticallly 		
		if(Math.abs(nht) >= Math.abs(nlt))
		{
			int xpts[] = {link.getP0().x-nthick,link.getP0().x+nthick+1,link.getNp0().x+nthick+1,link.getNp0().x-nthick};
			int ypts[] = {link.getP0().y,link.getP0().y,link.getNp0().y+1,link.getNp0().y+1};
			poly = new Polygon(xpts,ypts,4);     
			//to enable gc
			xpts = null;
			ypts = null;

		}
		// if the line is  inclined horizontally
		else
		{
			int xpts[] = {link.getP0().x,link.getNp0().x+1,link.getNp0().x+1,link.getP0().x};
			int ypts[] = {link.getP0().y-nthick,link.getNp0().y-nthick,link.getNp0().y+nthick+1,link.getP0().y+nthick+1};
			poly = new Polygon(xpts,ypts,4);     
			//to enable gc
			xpts = null;
			ypts = null;

		}	

		if (poly.contains(x,y)) 
			return true;
		return false;
	}

}

