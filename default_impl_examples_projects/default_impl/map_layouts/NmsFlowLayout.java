/*
$Id: NmsFlowLayout.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/

/**
 *  NmsFlowLayout.java
 **/
package com.adventnet.nms.mapui;

import java.awt.*;
import java.util.*;
import javax.swing.*;


/** The interface implemented by Map Layout classes, e.g. NmsFlowLayout.
  These methods are provided by the Layout implementations
  to the MapModel, to enable layout flexibility.  These
  methods are called when the model changes to update the layouts.  **/
public class NmsFlowLayout implements AutoLayout 
{

	/** Layout all the symbols and links in a map specified by MapModel **/
	public void layoutMap(MapContainerComponent mc , int index)	
	{
		Vector symbols = mc.getSymbols();
		int numSymbols = symbols.size();
		int vw = mc.getWidth();
		int vh = mc.getHeight();
		if ((numSymbols <= 0)||(vw <= 0)||(vh <= 0)) 
			return;


		int maxDimW=(2*numSymbols+1);
		// double maxDimH=(1000/10);


		for (int i=0;i<numSymbols;i++) 
		{
			MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(i);
			if (obj.getObjType() == 0) continue;
			//obj.dim.height = (int)maxDimH;			
			//obj.p = new Point((2*(i+1)-1)*obj.dim.width,obj.dim.height);
			//obj.dim.width = 1000/maxDimW;
			
			if( !obj.getAnchored())
			{
				obj.dim.width = vw/maxDimW;
				obj.dim.height = (int)vh/10;			
				obj.p = new Point((2*(i+1)-1)*obj.dim.width,(vh/2-obj.dim.height/2));
				if(obj.dim.width < obj.dim.height)
					obj.dim.height = obj.dim.width;
				else
					obj.dim.width = obj.dim.height;
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
		return "flow";
	}

}

