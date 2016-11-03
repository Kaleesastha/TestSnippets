/*
$Id: RingLayout.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/

/**
 *  RingLayout.java
 **/
package com.adventnet.nms.mapui;

import java.awt.*;
import java.util.*;


import javax.swing.ImageIcon;
import com.adventnet.nms.util.*;

        
/** The interface implemented by Map Layout classes, e.g. RingLayout.
  These methods are provided by the Layout implementations
  to the MapModel, to enable layout flexibility.  These
  methods are called when the model changes to update the layouts.  **/
public class RingLayout implements AutoLayout 
{
	/** Layout all the symbols and links in a map specified by MapModel **/
	public void layoutMap(MapContainerComponent mc , int index)	
	{
		Vector symbols = mc.getSymbols();

		//DefaultMapModel model=MapClientAPI.getInstance().getMapModel(mc.getMapName());
		DefaultMapModel model=(DefaultMapModel)MapClientInitializer.cacheMapTable.get(mc.getMapName());
		if(model==null)return;

		Properties p = mc.getCurrentTopologyProperties();
		
		int numSymbols = symbols.size();
		//int virtualWidth = mc.getWidth();
		int vw = 0;
		int vh = 0;
//*******************************************************
		if( index == 0)
		{
			vh = (int)(model.getViewHeight() * model.zoomRatio);
			vw = (int)(model.getViewWidth() * model.zoomRatio);
		}
		else
		{
			vh = mc.getHeight();
			vw = mc.getWidth();
		}
//*******************************************************
		int cellPad = 0;
		boolean isActualIconSize = false; // whether Symbolsize is asigned base on the icon size --by default it is false.
		
		
		//if ((numSymbols <= 0)||(virtualWidth <= 0)||(virtualHeight <= 0)) 
		
		if ((numSymbols <= 0)||(vw <= 0)||(vh <= 0)) 
			return;
		try
		{
			String iconSize  = (String)p.get("isActualIconSize");
			if( (iconSize != null) && (iconSize.trim().equals("true")))
			{	  	
				isActualIconSize = true;
			}
		}
		catch(Exception e) {}
		
		try
		{
			String pad  = (String)p.get("cellDia");
			if(pad != null)
			{	  	
				cellPad = Integer.parseInt(pad);
			}
		}
		catch(Exception e) {}


		for (int i=0;i<numSymbols;i++)
		{
			MapSymbolComponent symb = (MapSymbolComponent) symbols.elementAt(i);
			if(!symb.getAnchored())
			{
				if(isActualIconSize)
				{
					try
					{
						// Asigning the icon's width and height to the symbols
						String myIconName=symb.getIconName();
						//ImageIcon myIcon=(NmsClientUtil.getImageIcon(NmsClientUtil.applet.getDocumentBase() + myIconName));
                        ImageIcon myIcon=(NmsClientUtil.getImageIcon(NmsClientUtil.applet.getDocumentBase()+"../images/" + myIconName));
						symb.dim.width =(int)(myIcon.getIconWidth()*model.zoomRatio);
						symb.dim.height =(int)(myIcon.getIconHeight()*model.zoomRatio);
						
					}
					catch(Exception e)
					{
                        symb.dim.width =(vw)*75/1000;
						symb.dim.height =(vh)*75/1000;
						if(symb.dim.width < symb.dim.height)
						{
							symb.dim.height = symb.dim.width;
						}
						else
						{
							symb.dim.width = symb.dim.height;
						}
					}
				}
				else
				{
					if (index == 2) 
					{
						symb.dim.width =(vw)*300/1000;
						symb.dim.height =(vh)*300/1000;
						if(symb.dim.width < symb.dim.height)
							symb.dim.height = symb.dim.width;
						else
							symb.dim.width = symb.dim.height;

					}
					else
					{
						symb.dim.width =(vw)*75/1000;
						symb.dim.height =(vh)*75/1000;
						if(symb.dim.width < symb.dim.height)
							symb.dim.height = symb.dim.width;
						else
							symb.dim.width = symb.dim.height;

					}
				}
			}
		}


		if (numSymbols == 1) 
		{
			MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(0);
			if ( ! obj.getAnchored())
				obj.p = new Point((2*vw/4 )-(obj.dim.width/2), (2*vh/4)-(obj.dim.height/2));
		}
		else if (numSymbols == 2) 
		{
			MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(0);;
			if ( ! obj.getAnchored())
				obj.p = new Point((vw/4)-(obj.dim.width/2), (2*vh/4)-(obj.dim.height/2));
			obj = (MapSymbolComponent) symbols.elementAt(1);
			if ( ! obj.getAnchored())
				obj.p = new Point((3*vw/4)-(obj.dim.width/2), (vh*2/4)-(obj.dim.height/2));
		}
		else if (numSymbols > 2) 
		{
			if(isActualIconSize)
			{
				//to get radius "rad" of Ring 
				double cellDia = 1;//cell diameter
				double minCellDia = 1;
				double ringRad;
				double minRad;

				for (int i=0;i<numSymbols;i++)
				{
					MapSymbolComponent symb = (MapSymbolComponent) symbols.elementAt(i);
					
					String myIconName=symb.getIconName();
					
					//ImageIcon myIcon=(NmsClientUtil.getImageIcon(NmsClientUtil.applet.getDocumentBase() + myIconName));
                    ImageIcon myIcon=(NmsClientUtil.getImageIcon(NmsClientUtil.applet.getDocumentBase()+"../images/" + myIconName));

					
					int h=(int)(myIcon.getIconHeight()*model.zoomRatio);
					int w=(int)(myIcon.getIconWidth()*model.zoomRatio);

					int hyp =(int) Math.sqrt((h*h)+(w*w));
					//to get the largest cell diameter
					if(minCellDia < hyp)
					{
						minCellDia = hyp;
					}
				}
				if (cellPad > 0)
				{
					cellDia = cellPad;
				}
				else
				{
					cellDia = minCellDia;
				}
				double delta = 2*Math.PI/numSymbols;

				ringRad = (cellDia/2)/Math.sin(delta/2);
				
				minRad = (vh-minCellDia)/2;
				
				if (ringRad<minRad)
				{
					ringRad = minRad;
				}

				ringRad = ringRad * model.zoomRatio;

				int height =(int)(minCellDia+2*ringRad);
				int width = height;
				
				int ox;
				int oy;
				if(vh > height)
				{
					ox = (vw/2);
					oy = (vh/2);
					model.setWidth(vw);
					model.setHeight(vh); 
				}
				else
				{
					if(vw > width)
					{
						width = vw;
					}
					ox = (width)/2; //origin x
					oy = (height)/2; //origin y
					model.setWidth(width);
					model.setHeight(height); 
				}

			
				
				for (int i=0;i<numSymbols;i++)
				{
					MapSymbolComponent symb = (MapSymbolComponent) symbols.elementAt(i);
					int x;
					int y;
					if(i==0)
					{
						x =(int)(Math.ceil(ringRad * Math.cos(2*Math.PI)));
						y =(int)(Math.ceil(ringRad * Math.sin(2*Math.PI)));
					}
					else
					{
						x =(int)Math.ceil(ringRad * Math.cos(i*delta));
						y =(int)Math.ceil(ringRad * Math.sin(i*delta));
					}
					String myIconName=symb.getIconName();
					//ImageIcon myIcon=(NmsClientUtil.getImageIcon(NmsClientUtil.applet.getDocumentBase() + myIconName));
                    ImageIcon myIcon=(NmsClientUtil.getImageIcon(NmsClientUtil.applet.getDocumentBase()+"../images/" + myIconName));
					symb.dim.height=(int)(myIcon.getIconHeight()*model.zoomRatio);
					symb.dim.width=(int)(myIcon.getIconWidth()*model.zoomRatio);

					if (! symb.getAnchored())
					{
						symb.p = new Point(x+ox-symb.dim.width/2,y+oy-symb.dim.height/2);
					}

				}
				//*************************************************************
			}
			else
			{

				// place in a circle
				int cols = (int) Math.ceil(Math.sqrt(numSymbols));
				int maxDim = vw/(6*cols);

				MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(0);
				int y0 = vh*150/1000;
				if (obj.dim.height <vh/10) y0 = (3*obj.dim.height)/2;
				if (y0 < vh/100) y0 = vh*10/1000;
				int r = 0;
				if(vh > vw )
				{
				 r = (vw - 2*y0)/2;
				 y0 = vh/2 - r;
				}
				else
				 r = (vh - 2*y0)/2;
				double delta = 2*Math.PI/(numSymbols);

				for (int i=0;i<numSymbols;i++)
				{
					obj = (MapSymbolComponent) symbols.elementAt(i);
					if (obj.getObjType() == 0 ) continue;

				//	if (numSymbols > 20) 
				//	{

						if ( ! obj.getAnchored())
						{
							if (obj.dim.width > maxDim) obj.dim.width = maxDim;
							if (obj.dim.height > maxDim) obj.dim.height = maxDim;
						}
				//	}

					int x1 =((vw*(500/1000)) - (obj.dim.width/2) + (int) ( r * Math.sin(i*delta)) );
				    
					int y1 = y0- (obj.dim.height/2) + r - (int) (r * Math.cos(i*delta));
					if ( ! obj.getAnchored())
					{
						obj.p = new Point(x1+vw/2,y1);
				//		obj.dim.width = maxDim;
				//		obj.dim.height = maxDim;
					}

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
		return "ring";
	}


}

