/*
$Id: MapLinkRendererImpl_2.java,v 1.1.1.1.4.1 2013/07/24 09:53:32 vijayalakshmiv Exp $
*/

/* MapLinkRendererImpl_2.java */

package com.adventnet.nms.mapui;

import java.awt.*;
import java.awt.geom.*;
import com.adventnet.nms.severity.SeverityInfo;
/**
 * MapLinkRendererImpl_2 defines how a MapLinkComponent is to be  represented in the Map . 
 * This <code> MapLinkRendererImpl_2 </code> is an implementation provided by WebNMS
 * as an example . The style , shape and color of an Map Link  are specified 
 * by this implementation .It also specifies the mouse down response for an Map Link
 * and its tool tip .
 * @see com.adventnet.nms.mapui.MapLinkComponent  
 * @see com.adventnet.nms.mapui.MapLinkRendererInterface
 * @see com.adventnet.nms.mapui.MapLinkRendererImpl
 * @see com.adventnet.nms.mapui.MapSymbolComponent
 */

public class MapLinkRendererImpl_2 implements MapLinkRendererInterface 
{



	/**
	 * To paint Map Link between source object and destination object 
	 * @param      g 	    - Graphics of Map Link object
	 * @param      mapLink     - Reference of MapLinkComponent object
	 * @param      sourcePoint - Source point (start point) of Map Link
	 * @param      destPoint   - Destination point (end point) of Map Link
	 * @param      status      - Color of Map Link with respect to the status
	 * @param      selected    - Whether the link is selected or not
	 */

	public void paintMapLink(Graphics g,MapLinkComponent mapLink,Point sourcePoint,Point destPoint, Color bg,boolean selected)
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
		Stroke oldStroke = ((Graphics2D)g).getStroke();

		BasicStroke strokeType=null;

		float dash1[] = {10.0f};



		strokeType = new BasicStroke((float)nthick,BasicStroke.CAP_BUTT,

				BasicStroke.JOIN_MITER,10.0f,dash1,0.0f);



		((Graphics2D)g).setStroke(strokeType);

		((Graphics2D)g).draw(new Line2D.Double(nx,ny,nx+nlt,ny+nht));



		//To reset the stroke

		((Graphics2D)g).setStroke(oldStroke);

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

			}  
			else 
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
			s = label.substring(0,MapConstants.MAX_LINK_LABEL_LENGTH) + "...";

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
	public String getToolTipText(MapLinkComponent link , int x, int y)
	{
		String toolTip = BasicMap.getToolTipProperty().trim();
		if(toolTip.equals("status"))//No Internationalisation
			return SeverityInfo.getInstance().getName(Integer.parseInt(link.getProperty(toolTip)));
		return link.getProperty(toolTip);
	}




	/**  To determine if a mouse down is inside the Map Link 
	 * @param		x		- x  coordinate of mouse event relative to the map
	 * @param		y		- y  coordinate of mouse event relative to the map
	 * @param		link		-Reference of Map Link Object
	 * @return	a <code> boolean  </code> true , if the mouse is down on the MapLinkComponent
	 */
	public boolean downInObject(int x, int y,MapLinkComponent link)

	{

		if ( (link.getP0() == null) || (link.getNp0() == null) ) return false;

		int xpts[] = 
		{
			link.getP0().x-3 + (link.getNp0().x-link.getP0().x)/5,link.getP0().x+3 + (link.getNp0().x-link.getP0().x)/5,
			link.getNp0().x+3 - (link.getNp0().x-link.getP0().x)/5, link.getNp0().x-3 - (link.getNp0().x-link.getP0().x)/5 }; 
			int ypts[] = 
			{
				link.getP0().y-3 + (link.getNp0().y-link.getP0().y)/5,link.getP0().y+3 + (link.getNp0().y-link.getP0().y)/5, 
				link.getNp0().y+3 - (link.getNp0().y-link.getP0().y)/5, link.getNp0().y-3 - (link.getNp0().y-link.getP0().y)/5 };

				Polygon poly = new Polygon(xpts,ypts,4);     

				if (poly.contains(x,y)) 
				{
					//deltax=x-link.p0.x;deltay=y-link.p0.y;
					return true;
				} 
				return false;

	}


}
