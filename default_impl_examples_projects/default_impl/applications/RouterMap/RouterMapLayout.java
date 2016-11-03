package com.adventnet.nms.mapui;

import java.awt.*;
import java.util.*;


public class RouterMapLayout extends  SwitchLayout 
{

  public void layoutMap(MapContainerComponent mc , int index)	
  {
      if(index == 0) //Router
      {
	 super.layoutMap(mc,index);

      }
      else //Interfaces
      {
	 //Width & height of the component ie router image	
	 int vw = mc.getWidth();
	 int vh = mc.getHeight();
	 
         //Spacing on the left & rigth side is wgap whereas spacing at the top is hgap	
	 int x = vw/8 ;
         int y = vh/3;
         //Returns the components on the switch (ie ports and the two button images
         Vector childSymbs = mc.getSymbols();
         int count = 0;

         //Spacing between the ports		
         int dx = 10; //Fixed spacing

         for ( int no = 0; no < childSymbs.size() ; no ++)
         {
 	        MapSymbolComponent symb = mc.getSymbol(no);
                symb.setX(x);
                symb.setY(y);
		String iconName = symb.getIconName();
		int width = 0;
		String label=symb.getLabel().trim();
		if (label.startsWith("Serial") || label.startsWith("serial"))//No Internationalization
		{
		   width = 65;
		}
		else
		{
	           width = 25;		 	
	        } 
 
 		x = x + width + dx;
		symb.setWidth(width);
		symb.setHeight(30); 
         }

	
      } ///End of else
     

  } //End fo layoutMap


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
	return "router";//No Internationalization
  }

}


