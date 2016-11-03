/**
$Id: MapSymbolRendererImpl_2.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/

package com.adventnet.nms.mapui;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.util.NmsClientUtil;

/* MapSymbolRendererImpl_2 defines how a MapSymbolComponent is to be  represented in the Map . 
* This <code> MapSymbolRendererImpl_2 </code> is an implementation provided by WebNMS
* as an example . The style , shape and color of an Mapsymbol are specified 
* by this implementation .It also specifies the mouse down response for an MapSymbolComponent 
* and its tool tip .
* @see com.adventnet.nms.mapui.MapSymbolComponent
* @see com.adventnet.nms.mapui.MapSymbolRendererInterface
* @see com.adventnet.nms.mapui.MapSymbolRendererImpl_2
*/
public class MapSymbolRendererImpl_2 extends MapSymbolRendererImpl_3 {
	public void paintLabelString(Graphics g,MapSymbolComponent mapSymbol, String s0, Point p,Font font ) 
	{
	/* This draws a centered label below specified point */
		Font f = font;
		String s = s0;
		if ( (s == null) || (f == null) ) 
		{
			return;
		}
		if (s.length() > MapConstants.MAX_LABEL_LENGTH) 
		{
			s = s0.substring(0,MapConstants.MAX_LABEL_LENGTH) + "...";//No Internationalisation
		}

		//g.setFont(f);
		FontMetrics fm = g.getFontMetrics();
		int width = fm.stringWidth(s);
		int height = fm.getHeight();
		int x = p.x - (width/2) - 1;
		int y = p.y + height - (height/4);
		g.setColor(Color.white);
		g.fillRect(x,p.y,width+4, height-1);
		g.setColor(Color.black);
		g.drawRect(x,p.y,width+4, height-1);
		mapSymbol.setLabelSize(new Rectangle(x,p.y,width+4, height-1));
		g.drawString(s,x+3,y);
	} 
	// end of paintLabelString()
}

