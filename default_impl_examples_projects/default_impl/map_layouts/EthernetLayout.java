/*
$Id: EthernetLayout.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/

/**
 *  EthernetLayout.java
 **/

package com.adventnet.nms.mapui;

import com.adventnet.nms.mapui.*;
import java.awt.*;
import java.util.*;

/** The interface implemented by Map Layout classes, e.g. EthernetLayout.
  These methods are provided by the Layout implementations
  to the MapModel, to enable layout flexibility.  These
  methods are called when the model changes to update the layouts.  **/
public class EthernetLayout implements AutoLayout 
{
	
  static double screenHeight = 1.0D;
  static double screenWidth  = 1.0D;

  static
  {
	screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	screenWidth  = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  }
	/** Layout all the symbols and links in a map specified by MapModel **/
	public void layoutMap(MapContainerComponent mc , int index)	
	{
        Vector symbols = mc.getSymbols(); 
	//DefaultMapModel model=MapClientAPI.getInstance().getMapModel(mc.getMapName());
	DefaultMapModel model=(DefaultMapModel)MapClientInitializer.cacheMapTable.get(mc.getMapName());
	if(model==null)return;

	int numSymbols = symbols.size();
	int vw         = 0;
	int vh         = 0;
	Properties p   = new Properties();

	if(index == 0)
	{
	  vw = (int)(model.getViewWidth() * model.zoomRatio);
	  vh = (int)(model.getViewHeight() * model.zoomRatio);
	  p = mc.getCurrentTopologyProperties();
	}
	else
	{
	  vw = mc.getWidth();
	  vh = mc.getHeight();
	}


	boolean verticalScroll = false;
	boolean horizScroll    = false;
	boolean isPercentage   = false;
	boolean hgexcep        = false;
	boolean wdexcep		   = false;

	if ((numSymbols <= 0)||(vw <= 0)||(vh <= 0)) 
	  return;
	String percentage  = (String)p.get("isPercentage");
	if( (percentage != null) && (percentage.trim().equals("true")))
	{	  	
		isPercentage = true;
	}
	else 
	{
		//in case of nothing is specified.
		isPercentage = false;
	}
	String scroll  = (String)p.get("scroll");
	if(scroll!=null)
	{	  	
		if(scroll.trim().equals("vertical"))
		{
			verticalScroll = true;
			horizScroll    = false;
		}

		else if(scroll.trim().equals("horizontal"))
		{
			horizScroll = true;
			verticalScroll = false;
		}

		else    // "both" or invalid parameters.
		{ 
			verticalScroll   = true;
			horizScroll      = true;
		}

	}
	else 
	{
		verticalScroll   = true;
		horizScroll      = true;
	}
 
	for (int i=0;i<numSymbols;i++)
	{
	  MapSymbolComponent symb = (MapSymbolComponent) symbols.elementAt(i);
	  if(!symb.getAnchored())	
       { 
        
		try{
		  int wid  = (int)(Integer.parseInt((String)p.get("width")));
		  // If the getting value is in percentage.
		  if(isPercentage)
		  {
			wid = getActualWidthOrDXByResolution(wid);
		  }
		  symb.dim.width =(int)(wid*model.zoomRatio);
		  wdexcep = false;
		}
		catch(Exception e)
		{
		  wdexcep = true;
		  symb.dim.width =(int)(vw*75/1000);
		}
		try
		{
		  int ht = (int)(Integer.parseInt((String)p.get("height")));
		  // If the getting value is in percentage.
		  if(isPercentage)
		  {
			ht = getActualHeightOrDYByResolution(ht);
		  }
		  symb.dim.height =(int)(ht*model.zoomRatio);
		  hgexcep   = false;
		}
		catch(Exception e)
		{
		  hgexcep = true;
	          symb.dim.height =(int)(vh*75/1000);

		}
		if((hgexcep) || (wdexcep))
		{
		  if(symb.dim.width < symb.dim.height)
			symb.dim.height = symb.dim.width;
		  else
			symb.dim.width = symb.dim.height;
		}
       } 
	  }
	
	if(p.size()==0 && numSymbols<5)
	{
	  if (numSymbols == 1) 
	  {

		MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(0);
		if ( ! obj.getAnchored())
		 {
		      obj.p = new Point((2*vw/4 )-(obj.dim.width/2), (2*vh/4)-(obj.dim.height/2));
 	  	 }
	
	  }
	  else if (numSymbols == 2) 
	  {
		MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(0);
		if ( ! obj.getAnchored())
		{
		  obj.p = new Point((vw/4)-(obj.dim.width/2), (2*vh/4)-(obj.dim.height/2));
		}
		obj = (MapSymbolComponent) symbols.elementAt(1);
		if ( ! obj.getAnchored())
		  {
		     obj.p = new Point((3*vw/4)-(obj.dim.width/2), (vh*2/4)-(obj.dim.height/2));
		  }
	  }
	  else if (numSymbols == 3) 
	  {
		MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(0);
		if ( ! obj.getAnchored())
		{		
		  obj.p = new Point(vw/4-(obj.dim.width/2), 2*vh/4-(obj.dim.height/2));
		}
		obj = (MapSymbolComponent) symbols.elementAt(1);
		if ( ! obj.getAnchored())
		{	
		  obj.p = new Point(3*vw/4-(obj.dim.width/2), vh/4-(obj.dim.height/2));
		}
		obj = (MapSymbolComponent) symbols.elementAt(2);
		if ( ! obj.getAnchored())
		{
		  obj.p = new Point(3*vw/4-(obj.dim.width/2), 3*vh/4-(obj.dim.height/2));
		}
	  }
	  else if (numSymbols == 4) 
	  {
		MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(0);
		if ( ! obj.getAnchored())
		{
		  obj.p = new Point((vw*1/4)-(obj.dim.width/2), (vh*1/4)-(obj.dim.height/2));
                }
		obj = (MapSymbolComponent) symbols.elementAt(1);
		if ( ! obj.getAnchored())
		{
		  obj.p = new Point((vw*1/4)-(obj.dim.width/2), (vh*3/4)-(obj.dim.height/2));
		}
		obj = (MapSymbolComponent) symbols.elementAt(2);
		if ( ! obj.getAnchored())
		{
		  obj.p = new Point((vw*3/4)-(obj.dim.width/2), (vh*1/4)-(obj.dim.height/2));
		}
		obj = (MapSymbolComponent) symbols.elementAt(3);
		if ( ! obj.getAnchored())
		{
		  obj.p = new Point((vw*3/4)-(obj.dim.width/2), (vh*3/4)-(obj.dim.height/2));
		}
	  } 
	}
	else //if (numSymbols > 4) 
	{
	  int cols;
	  int rows;
	  cols = (int) Math.ceil(Math.sqrt(numSymbols));
	  rows = cols-1;  
	  if ( (cols*rows) < numSymbols )  rows = cols; 
	  if ( (cols*rows) < numSymbols )  cols++; 

	  int maxDimH ;
	  int maxDimW;
	  int dx=1;
	  int dy=1;
	  boolean dxexcep=false;
	  boolean dyexcep=false;
	  boolean wexcep=false;
	  boolean hexcep=false;
	  try{
		maxDimW = (int)(Integer.parseInt((String)p.get("width")));
		// If the getting value is in percentage.
		if(isPercentage)
		{
		  maxDimW = getActualWidthOrDXByResolution(maxDimW);
		}
		maxDimW = (int)(maxDimW * model.zoomRatio);
	  }
	  catch(Exception e)
	  {
		wexcep=true;
		maxDimW = (int)vh/(6*rows);
	  }
	  try{
		maxDimH = (int)(Integer.parseInt((String)p.get("height")));
		// If the getting value is in percentage.
		if(isPercentage)
		{
		  maxDimH = getActualHeightOrDYByResolution(maxDimH);
		}
		maxDimH=(int)(maxDimH * model.zoomRatio);
	  }
	  catch(Exception e)
	  {
		hexcep=true;
		maxDimH =vw/(6*cols);
	  }
	  try{
		dx = (int)(Integer.parseInt((String)p.get("gapX")));
		// If the getting value is in percentage.
		if(isPercentage)
		{
		  dx = getActualWidthOrDXByResolution(dx);
		}
		dx =(int)(dx * model.zoomRatio);
	  }
	  catch(Exception e)
	  {
		dxexcep = true;
	  }
	  try{
		dy = (int)(Integer.parseInt((String)p.get("gapY")));
		// If the getting value is in percentage.
		if(isPercentage)
		{
		  dy = getActualHeightOrDYByResolution(dy);
		}
		dy = (int)(dy * model.zoomRatio);
	  }
	  catch(Exception e)
	  {
		dyexcep = true;
	  }
	  if(hexcep && wexcep)
	  {
		if(maxDimW < maxDimH)
		{
		  maxDimH = maxDimW;
		}
		else
		{
		  maxDimW = maxDimH;
		}
	  }
	  int dx1=vw/(cols);
	  int dy1 =vh/(rows);

	  if(dxexcep)dx=(vw-dx1/2)/(cols);
	  if(dyexcep)dy=(vh-dy1/2)/(rows);

	  if(index==0)
	  {

		/* Distance between(in x axis) two symbols's centre point  should 
		 * not be lesser than the width of the symbol. */

		if( dx < maxDimW)
		{
		  dx =  maxDimW  * 3 / 2;
		}

		/* Distance between(in y axis) two symbols's centre point  should 
		 * not be lesser than the height  of the symbol.*/

		if( dy < maxDimH)
		{
		  dy = maxDimW * 3 / 2;
		}


		int width = cols * dx+(dx/2-maxDimW/2);// width = window width.
		int height = rows * dy+(dy/2-maxDimH/2);// window height.


		/*
		 * If the vertical scroll bar is selected, then the cols & rows 
		 * are to be modified as follows.
		 */


		if( verticalScroll && !horizScroll ) 
		{
		  while(width > (int)(model.getViewWidth() * model.zoomRatio))
		  {
			if( cols == 1 )
			{
			  while(width > (int)(model.getViewWidth() * model.zoomRatio))
			  {
				if(dx <= 1)
				{
					dx = maxDimW = maxDimW/2;
				}

				dx = dx / 2;
				width = cols * dx+(dx/2-maxDimW/2);
			  }
			  break;
			}
			cols--;
			rows++;

			width = cols * dx+(dx/2-maxDimW/2);
		  }
		  // "cols" should not be increased and "cols" * "rows" should
		  // be equal to number of symbols.
		  if ( (cols*rows) < numSymbols )   
		  {
			while(cols*rows < numSymbols)
			{
			  rows++;
			}
		  }
		  height = rows * dy+(dy/2-maxDimH/2);
		}
		/*
		 * If the horizontal scroll s selected, then the cols & rows 
		 * are to be modified as follows.
		 */
		else if( horizScroll  && !verticalScroll) 
		{
		  while(height > (int)(model.getViewHeight() * model.zoomRatio)){

			if( rows == 1 )
			{
			  while(height > (int)(model.getViewHeight() * model.zoomRatio))
			  {
				  if( dy <= 1)
					  dy = maxDimH = maxDimH/2;

					  dy = dy / 2;
					  
				height = rows * dy+(dy/2-maxDimH/2);
			  }
			  break;
			}

			cols++;
			rows--;
			height = rows * dy+(dy/2-maxDimH/2);
		  }
		  // "rows" should not be increased and "cols" * "rows" should
		  // be equal or greater than number of symbols.

		  if ( (cols*rows) < numSymbols )   
		  {
			while(cols*rows < numSymbols)
			{
			  cols++;
			}
		  }
		  width = cols * dx+(dx/2-maxDimW/2);
		}


		  /* 
		   *  If the "width" is greater than the calculated width, then  change the actual 
		   *  width.
		   */

		  if(width >= (int)(model.getViewWidth() * model.zoomRatio)) 
		  {
			model.setWidth(width);
		  }

		  /*
		   *  If the "width" is lesser than or equal  the calculated width; adjust gapx 
		   *  accordingly.
		   */
		  else 
		  {
			dx = (int)(model.getViewWidth() * model.zoomRatio) / cols;
			model.setWidth((int)(model.getViewWidth() * model.zoomRatio));
		  }

		  /* 
		   *  If the "height" is greater than the calculated height, the  change the actual 
		   *  height.
		   */
		  if(height >= (int)(model.getViewHeight()* model.zoomRatio))
		  {
			model.setHeight(height);
		  }
		  /*
		   *  If the "height" is lesser than or equal  the calculated height; adjust gapy 
		   *  accordingly.
		   */
		  else
		  { 
			dy = (int)(model.getViewHeight() * model.zoomRatio)/rows;
			model.setHeight((int)(model.getViewHeight() * model.zoomRatio));
		  }

		  }	
		  //Store the number of cols & rows in the model.
		  Properties tP = new Properties();

		  tP.put("rows",String.valueOf(rows));
		  tP.put("cols",String.valueOf(cols));
		  tP.put("dx",String.valueOf(dx));
		  tP.put("dy",String.valueOf(dy));

		  model.setTransientProps(tP);

		int y1 = (dy)/2, x1 = dx/2;
		int x = 1;
		for (int i=0;i<numSymbols;i++) 
		{
			MapSymbolComponent obj = (MapSymbolComponent)symbols.elementAt(i);
			if (obj.getObjType() == 0) continue;
			if(! obj.getAnchored() )	
			{
                obj.dim.width = maxDimW;
                obj.dim.height = maxDimH;
 
				obj.p = new Point(x1-obj.dim.width/2,y1-obj.dim.height/2);
			}

			x1 = x1+dx;
			if (++x > cols) { x=1; x1 = dx/2; y1 += dy; }
		}
            }
	}
	
	private int getActualWidthOrDXByResolution(int wid)
	{

	  wid = (int)((wid * screenWidth / 100));
	  return wid;

	}
	private int getActualHeightOrDYByResolution(int hid)
	{
	  hid = (int)((hid * screenHeight / 100));
	  return hid;
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
		return "ethernet";//No Internationalisation
	}

}


