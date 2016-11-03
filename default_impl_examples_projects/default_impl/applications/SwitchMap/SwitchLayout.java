//$Id: SwitchLayout.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.mapui;

import java.awt.*;
import java.util.*;



public class SwitchLayout implements  AutoLayout 
{

  public void layoutMap(MapContainerComponent mc , int index)	
  {
	  if(index==0){
		  Vector symbols = mc.getSymbols();
		  //DefaultMapModel model=MapClientAPI.getInstance().getMapModel(mc.getMapName());
		  DefaultMapModel model=(DefaultMapModel)MapClientInitializer.cacheMapTable.get(mc.getMapName());

		  int numSymbols = symbols.size();
		  int  vw = (int)(model.getViewWidth() * model.zoomRatio);
		  int  vh = (int)(model.getViewHeight() * model.zoomRatio);

		  if ((numSymbols <= 0)||(vw <= 0)||(vh <= 0)) 
			  return;


		  int maxDimH = vh/8;
		  int maxDimW = (int)(vw/1.2);

		  int x = vw/15;
		  int y = vh/14;

		  int  width = (int)(model.getViewWidth() * model.zoomRatio);
		  int  height =(int)(model.getViewHeight() * model.zoomRatio);

		  width=width-width/45*2;	

		  if(numSymbols>4)
		  {
			  int windowHeight = (numSymbols*y) + (numSymbols * maxDimH);
			  if( windowHeight >= (int)(model.getViewHeight() * model.zoomRatio))
			  {
				  model.setHeight(windowHeight);
			  }
			  else
			  {
				  model.setHeight((int)(model.getViewHeight() * model.zoomRatio));
			  }
		  }

		  int dx = (width-numSymbols*(width/45))/(numSymbols+1);

		  for (int i=0;i<numSymbols;i++) 
		  {
			  MapSymbolComponent obj1 = mc.getSymbol(i);
			  if(! obj1.getAnchored())
			  {   
				  if( obj1 instanceof MapContainerComponent )    
				  {
					  setSizeLocation(obj1,maxDimW+66,maxDimH+20,x-20,y,i);
				  }
			  }
		  }
	  }
	  else
	  {
		  int imageWidth = 16; //Next & previous buttons
		  int imageHeight = 25;

		  //Width & height of the component ie switch image
		  int vw = mc.getWidth();
		  int vh = mc.getHeight();

		  //Spacing on the left & rigth side is wgap whereas spacing at the top is hgap
		  int wgap = vw/20;
		  int hgap = vh/3;

		  int x = wgap;
		  int y = vh/3;
		  //Returns the components on the switch (ie ports and the two button images
		  Vector childSymbs = mc.getSymbols();
		  int count = 0;

		  //Spacing between the ports
		  int dx = ((vw - 2 * wgap) - (24 * 21))/(25); //Fixed spacing
		  int rem = ((vw - 2 * wgap) - (24 * 21))%(25);


		  x = x + dx + 7 + rem/2 ; //plus 7 for adjustment

		  //Total number of ports
		  int portCount = 0;


		  for ( int no = 0; no < childSymbs.size() ; no ++)
		  {
			  String symName = ((MapSymbolComponent)mc.getSymbol(no)).getName();
			  if ( symName.equalsIgnoreCase("next")) //left button
			  {
				  count++;
				  MapSymbolComponent rightImg = mc.getSymbol(no);
				  rightImg.setWidth(imageWidth);
				  rightImg.setHeight(imageHeight);
				  rightImg.setX(vw - 30);
				  rightImg.setY((vh/2)-7);
			  }
			  else if ( symName.equalsIgnoreCase("previous")) //right button
			  {
				  count++;
				  MapSymbolComponent leftImg = mc.getSymbol(no);
				  leftImg.setWidth(imageWidth);
				  leftImg.setHeight(imageHeight);
				  leftImg.setX(14);
				  leftImg.setY((vh/2)-7);
			  }
			  else  //Port
			  {
				  if (portCount % 24 == 0)
					  x = wgap + dx + rem/2;
				  portCount++;
				  MapSymbolComponent symb = mc.getSymbol(no);
				  symb.setWidth(21);
				  symb.setHeight(33);
				  symb.setX(x);
				  symb.setY(y);
				  //For Tool Tip
				  symb.setUserProperty("Position",Integer.toString(portCount)); //No Internationalization
				  x = x + 21 + dx;
			  }

		  }

		  int numPorts = childSymbs.size()-count;

		  //Setting the maxLevel user property for every switch
		  int maxLevel = 1;
		  if ( numPorts % 24 == 0 ) //if exact multiple of 24 like 24, 48, 72 ...
			  maxLevel = numPorts/24;
		  else
			  maxLevel = (numPorts/24)+1;


		  if ( numPorts > 0)
		  {
			  MapContainerComponent containerSymbol = (mc.getSymbol(0)).getParent();
			  containerSymbol.setUserProperty("maxLevel",Integer.toString(maxLevel));//No Internationalization

		  }



		  if ( numPorts > 24 && count == 0)
		  {
			  MapSymbolComponent portSym = mc.getSymbol(0);
			  MapContainerComponent switchSym = portSym.getParent();
			  MapSymbolComponent symb =  new MapSymbolComponent();
			  symb.setIconName("switch_next.png");//No Internationalization
			  symb.setLabel("next");//No Internationalization
			  symb.setName("next");//No Internationalization
			  symb.setMapName(mc.getMapName());
			  symb.setWidth(imageWidth);
			  symb.setHeight(imageHeight);
			  symb.setX(vw - 35);
			  symb.setY((vh/2)-8);
			  symb.setParent(switchSym);
			  symb.setParentName(switchSym.getName());

			  boolean  result = switchSym.addSymbol(symb);


			  symb =  new MapSymbolComponent();
			  symb.setIconName("switch_prev.png");//No Internationalization
			  symb.setLabel("previous");//No Internationalization
			  symb.setName("previous");//No Internationalization
			  symb.setMapName(mc.getMapName());
			  symb.setWidth(imageWidth);
			  symb.setHeight(imageHeight);
			  symb.setX(19); //Relative to the switch image
			  symb.setY((vh/2)-8);
			  symb.setParent(switchSym);
			  symb.setParentName(switchSym.getName());
			  result = switchSym.addSymbol(symb);

		  }


	  }//End of else

  }

  private void setSizeLocation(MapSymbolComponent obj,int wid,int hei ,int x, int y, int i) 
  {
	((MapContainerComponent)(obj)).dim.width =wid;
	((MapContainerComponent)(obj)).dim.height = hei;    
	((MapContainerComponent)(obj)).p = new Point(x,y+hei*i+hei/2*i);
  }


  private void setSizeLocationForSymbol(MapSymbolComponent obj,int wid,int hei ,int x, int y, int i) 
  {
	((obj)).dim.width =wid;
	((obj)).dim.height = hei;    
	((obj)).p = new Point(x,y+hei*i+hei/2*i);
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
	return "switch";
  }
}
