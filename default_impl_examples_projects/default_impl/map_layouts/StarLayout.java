/*
$Id: StarLayout.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/
/**
 *  StarLayout.java
 **/
package com.adventnet.nms.mapui;

import java.awt.*;
import java.util.*;

/** The interface implemented by Map Layout classes, e.g. StarLayout.
  These methods are provided by the Layout implementations
  to the MapModel, to enable layout flexibility.  These
  methods are called when the model changes to update the layouts.  **/
public class StarLayout implements AutoLayout 
{
    /**  Layout all the symbols and links in a map specified by MapModel 
     *   Using the model reference you could retrieve the symbols, links and
     *   their counts. Then calculate the size and positions for each symbol, 
     *   assign them to p0 and d0 fields of MapSymbolComponent.For clarifications refer
	 *   examples in WebNMS_HOME/examples/map_layouts directory.
     *   Star Layout checks for the special property called "root".If root is
	 *   set for an symbol then that corresponding symbol is palced at the center.
	 * @param 	symb	MapSymbolComponent that is get added to the MapContainerComponent Object parent
	 * @param 	parent  	MapContainerComponent Object whose child to be laid out. 
	 * @param 	depth		The Layer in which the MapContainerComponent present in the Map.
	 * It Starts with <b>"0"</b> for the first level i.e for the Map itself and 
	 * incremented by one for subsequent levels .
	 **/

	public void layoutMap(MapContainerComponent mc , int index)	
	{
		Vector symbols = mc.getSymbols();
		int numSymbols = symbols.size();
		int virtualWidth = mc.getWidth();
		int virtualHeight = mc.getHeight();
		boolean symbolAtCenterFound = false;
		String rootSymbol = null;
		if ((numSymbols <= 0)||(virtualWidth <= 0)||(virtualHeight <= 0)) 
			return;
		for (int i=0;i<numSymbols;i++)
		{
			MapSymbolComponent symb = (MapSymbolComponent) symbols.elementAt(i);
			if( !symb.getAnchored() )
			{
				if (index == 2) 
				{
					symb.dim.width =(virtualWidth)*300/1000;
					symb.dim.height =(virtualHeight)*300/1000;
					if(symb.dim.width < symb.dim.height)
						symb.dim.height = symb.dim.width;
					else
						symb.dim.width = symb.dim.height;

				}else
				{
					symb.dim.width =(virtualWidth)*75/1000;
					symb.dim.height =(virtualHeight)*75/1000;
					if(symb.dim.width < symb.dim.height)
						symb.dim.height = symb.dim.width;
					else
						symb.dim.width = symb.dim.height;

				}
			}
			if(!symbolAtCenterFound)
			{
				rootSymbol = symb.getProperty("root");
				if( (rootSymbol != null) && (rootSymbol.equalsIgnoreCase("true")) )
				{
					// just swaping the elements
					//symbols.set( i , symbols.set(0,symb));
					symbols.setElementAt( symbols.elementAt(0), i);
					symbols.setElementAt(symb , 0);
					symbolAtCenterFound = true;
				}
			}	
		}
		if (numSymbols == 1) 
		{
			MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(0);
			if ( ! obj.getAnchored())
				obj.p = new Point((virtualWidth/2)-(obj.dim.width/2),((virtualHeight/2)-(obj.dim.height/2)));

		}
		else if (numSymbols == 2) 
		{

			MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(0);
			if ( ! obj.getAnchored())
				obj.p = new Point((virtualWidth/2)-(obj.dim.width/2),(virtualHeight/2)-(obj.dim.height/2));
			obj = (MapSymbolComponent) symbols.elementAt(1);
			if ( ! obj.getAnchored())
				obj.p = new Point((virtualWidth*3/4)-(obj.dim.width/2),(virtualHeight/2)-(obj.dim.height/2));

		}
		else if (numSymbols > 2) 
		{
			// place in a circle

			int cols = (int) Math.ceil(Math.sqrt(numSymbols));
			int maxDim = virtualWidth/(6*cols);
			//int maxDim = virtualWidth/10;

			MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(0);


			if ( !obj.getAnchored() )
			{
				if (obj.dim.width > maxDim) obj.dim.width = maxDim;
		    	if (obj.dim.height > maxDim) obj.dim.height = maxDim;
				obj.p = new Point((virtualWidth/2)-(obj.dim.width/2),(virtualHeight/2)-(obj.dim.height/2));
			}
			int y0 = virtualHeight*150/1000;
			if (obj.dim.height <virtualHeight/10) 
				y0 = (3*obj.dim.height)/2;
			if (y0 < virtualHeight/100) 
				y0 = virtualHeight*10/1000;
			int r = 0;	
			if(virtualHeight > virtualWidth )
			{
			 r = (virtualWidth - 2*y0)/2;
			 y0 = virtualHeight/2 - r;
			}
			else
			 r = (virtualHeight - 2*y0)/2;

			double delta = 2*Math.PI/(numSymbols-1);

			for (int i=0;i<numSymbols-1;i++)
			{
				obj = (MapSymbolComponent) symbols.elementAt(i+1);
			//	obj.dim.width = maxDim;
			//	obj.dim.height = maxDim;
				if (obj.getObjType() == 0) continue;
				if( !obj.getAnchored()) 
				{
					int x1 =((virtualWidth*(500/1000)) - (obj.dim.width/2) + (int) ( r * Math.sin(i*delta)) );
					int y1 = y0- (obj.dim.height/2) + r - (int) (r * Math.cos(i*delta));
					obj.p = new Point(x1+virtualWidth/2,y1);
				}
			}

		} 

	}
	public void addLayoutObject(MapSymbolComponent symb ,MapContainerComponent parent ,int index)	
	{
	}

	public void removeLayoutObject(MapSymbolComponent symb ,MapContainerComponent parent , int index)	
	{
	}

	public void updateLayoutObject(MapSymbolComponent symb ,MapContainerComponent parent ,int index)	
	{
	}



	public String getName()
	{
		return "star";
	}

}

