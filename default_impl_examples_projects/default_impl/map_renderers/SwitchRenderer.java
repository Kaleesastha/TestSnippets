/* $Id: SwitchRenderer.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package com.adventnet.nms.mapui;

import java.awt.*;
import java.awt.image.*;

public class SwitchRenderer  extends MapSymbolRendererImpl {
	
	

	
	/** This paints the icon symbol at specified point, and dimension. */
	public void paintIcon(Graphics g,MapSymbolComponent mapSymbol,Point p, Dimension d, ImageObserver io,Image img,boolean selected)
	{
		String t= mapSymbol.getProperty("isPort");
		if (t.equals("true"))
		{
			super.paintIcon(g,mapSymbol,p, d, io, img,selected);
		}
		
	} /* end paintIcon() */


	/** This draws a centered label below specified point */
	public void paintLabelString(Graphics g,MapSymbolComponent mapSymbol, String s0, Point p,Font font ) 
	{
		String t= mapSymbol.getProperty("isPort");
		if (t.equals("true"))
		{
			super.paintLabelString( g, mapSymbol, s0,  p,font); 
			
			
		}
		
	} // end of paintLabelString()
}
