/*
$Id: NmsGridLayout.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/

/**
 *  NmsGridLayout.java
 **/
package com.adventnet.nms.mapui;

import java.awt.*;
import java.util.*;


/** The interface implemented by Map Layout classes, e.g. NmsGridLayout.
  These methods are provided by the Layout implementations
  to the MapModel, to enable layout flexibility.  These
  methods are called when the model changes to update the layouts.  **/

public class NmsGridLayout implements  AutoLayout 
{

	public void layoutMap(MapContainerComponent mc , int index)	
	{
		

		Vector symbols = mc.getSymbols();
		int numSymbols = symbols.size();
		int vw = mc.getWidth();
		//int virtualWidth = mc.getWidth();
		int vh = mc.getHeight();
		//int virtualHeight = mc.getHeight();
		int k=0;
		//if ((numSymbols <= 0)||(virtualWidth <= 0)||(virtualHeight <= 0)) 
		if ((numSymbols <= 0)||(vw <= 0)||(vh <= 0)) 
			return;
		
		
		int maxDimH = vh/(6*noOfRows);
		int maxDimW = vw/(6*noOfCols);
		if(maxDimW < maxDimH)
			maxDimH = maxDimW;
		else
			maxDimW = maxDimH;
		int dy = vh/(noOfRows+1), dx = vw/(noOfCols);
		int y1 = dy/2, x1 = dx/2;
		//System.out.println(" value of y1 = " + y1  + " value of x1 " + x1 );
		int x = 1;
		for (int i=0;i<numSymbols/2;i++) 
		{
			MapSymbolComponent obj1 = (MapSymbolComponent) symbols.elementAt(i);
			MapSymbolComponent obj2 = (MapSymbolComponent) symbols.elementAt(i+numSymbols/2);
			
			if (obj1.getObjType() == 0 && obj2.getObjType() == 0) continue;
			
			obj2.dim.width =maxDimW*3;
			obj2.dim.height = maxDimH*3;
			obj1.dim.width =maxDimW;
			obj1.dim.height = maxDimH;
			if ( !obj1.getAnchored())
			{			
				if(i==0)
				{
					obj1.p = new Point(x1-obj1.dim.width/2,y1-obj1.dim.height/2+(dy-4*obj1.dim.height));
					obj2.p = new Point(obj1.getX()-(obj2.getWidth()-obj1.getWidth())/2,y1-obj1.dim.height/2+dy);
					
				}
				else
				{
					//int s = (Integer.parseInt((((MapSymbolComponent)(symbols.elementAt(i+numSymbols/2))).getProperty("speed")))); 
					//int s1 = (Integer.parseInt((((MapSymbolComponent)(symbols.elementAt(i+numSymbols/2-1))).getProperty("speed")))); 
					obj1.p = new Point(x1-obj1.dim.width/2,y1-obj1.dim.height/2+(dy-4*obj1.dim.height));
					obj2.p = new Point(obj1.getX()-(obj2.getWidth()-obj1.getWidth())/2,y1-obj1.dim.height/2+dy);
				
				}
				x1 = x1+dx;
				if (++x > noOfCols) { x=1; x1 = dx/2; y1 += dy; }
			}
			
		}
	}
	
	protected  int noOfRows = 1;
	protected int noOfCols = 1;
	public void setRows(int numr )
	{
		noOfRows = numr;
	}
	public void setCols(int numc )
	{
		noOfCols = numc;
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
		return "nmsgrid";
	}
}
