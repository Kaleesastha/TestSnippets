/* $Id: ConfigurableMapSymbolRenderer.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package com.adventnet.nms.mapui;

import java.awt.*;
import java.awt.image.*;
import java.util.Properties;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.util.NmsClientUtil;

/**
 * ConfigurableMapSymbolRenderer  defines how a MapSymbolComponent is to be  represented in the Map . 
 * This <code> ConfigurableMapSymbolRenderer  </code> is an implementation 
 * provided by WebNMS as an example . The style , shape and color of an 
 * Mapsymbol are specified  by this implementation .It also specifies the   
 * ouse down response for an MapSymbolComponent and its tool tip .
 * @see com.adventnet.nms.mapui.MapSymbolComponent
 * @see com.adventnet.nms.mapui.MapSymbolRendererInterface
 * @see com.adventnet.nms.mapui.MapSymbolRendererImpl_2
 */

public class ConfigurableMapSymbolRenderer implements MapSymbolRendererInterface 
{
  /**
   * To paint Map Symbol Shape and Severity .Depending upon the objType 
   * MapSymbolComponent's shape is drawn . For Example ,acoording to WebNMS if the objType 
   * is 2 it is an network.A circle is drawn for the Network. 
   * 
   * @param     g 	  - Graphics of MapSymbolComponent
   * @param     mapSymbol - Reference of MapSymbolComponent object
   * @param     location  - Top Left Corner point of MapSymbolComponent
   * @param     size      - Size of the map symbol
   * @param     status    - color of map symbol with respect to the status
   * @param     objType   - Type of map symbol object
   * @see com.adventnet.nms.mapui.MapSymbolComponent
   */

  public void paintShapeAndSeverity(Graphics g,MapSymbolComponent mapSymbol,Point p,Dimension d, Color bg,int type)

  {
	if ( (p==null) || (d==null) || (g==null) ) 
	{
	  System.err.println((NmsClientUtil.GetString("Invalid values to paint icon.")));
	  return;
	}
	DefaultMapModel model = MapClientAPI.getInstance().getMapModel(mapSymbol.getMapName());
	Properties prop = (Properties)model.getSymbolRendererProps();	
	String statusPosition    = (String) prop.get("statusPosition");

	/* 
	 * The "statusPosition"  property contains the position of the status 
	 * around the symbol. The valid positions are top right, top left, 
	 * bottom right, bottom left and back ground of the symbol.
	 */

	if(statusPosition == null)
	{
	  statusPosition = "background";
	}

	if(statusPosition.trim().equals("background"))
	{
	  if (bg != null)  
	  {
		// we have an object symbol

		if (type == 2) 
		{ 
		  // network is a circle
		  g.setColor(Color.darkGray);
		  g.drawOval(p.x+1,p.y,d.width,d.height);
		  g.drawOval(p.x+2,p.y,d.width,d.height);
		  g.setColor(bg);
		  g.fillOval(p.x,p.y,d.width,d.height);
		  g.setColor(Color.black);
		  g.drawOval(p.x,p.y,d.width,d.height);

		} else if (type == 3) 
		{ 
		  // gateway
		  int xp[] = {p.x+d.width/2, p.x+d.width, p.x+d.width/2, p.x};
		  int yp[] = {p.y, p.y+d.height/2, p.y+d.height, p.y+d.height/2};
		  g.setColor(Color.darkGray);
		  int xp2[] = { xp[0]+1, xp[1]+1, xp[2]+1, xp[3]+1 };
		  g.drawPolygon(xp2,yp,4);
		  int xp3[] = { xp[0]+2, xp[1]+2, xp[2]+2, xp[3]+2 };
		  g.drawPolygon(xp3,yp,4);
		  g.setColor(bg);
		  g.fillPolygon(xp,yp,4);
		  g.setColor(Color.black);
		  g.drawPolygon(xp,yp,4);

		}  else if (type == 4) 
		{ 
		  // sub-system
		  int xp[] = { p.x ,  p.x + d.width,  p.x + d.width/2 };
		  int yp[] = { p.y, p.y , p.y+d.height };
		  g.setColor(Color.darkGray);
		  g.drawLine(p.x+d.width+1,p.y, p.x+(d.width/2)+1, p.y+d.height);
		  g.drawLine(p.x+d.width+2,p.y, p.x+(d.width/2)+2, p.y+d.height);
		  g.setColor(bg);
		  g.fillPolygon(xp,yp,3);
		  g.setColor(Color.black);
		  g.drawPolygon(xp,yp,3);

		}  else if (type == 5) 
		{ 
		  // site
		  int xp[] = { p.x+d.width/2, p.x+d.width, p.x+d.width, p.x,p.x};
		  int yp[] = { p.y, p.y+d.height/2, p.y+d.height, 
			p.y+d.height, p.y+d.height/2 };
			g.setColor(Color.darkGray);
			int xp2[] = { xp[0]+1, xp[1]+1, xp[2]+1, xp[3]+1, xp[4]+1 };
			g.drawPolygon(xp2,yp,5);
			int xp3[] = { xp[0]+2, xp[1]+2, xp[2]+2, xp[3]+2, xp[4]+2 };
			g.drawPolygon(xp3,yp,5);
			g.setColor(bg);
			g.fillPolygon(xp,yp,5);
			g.setColor(Color.black);
			g.drawPolygon(xp,yp,5);

		}  else if (type == 1) 
		{ 
		  // square by default
		  g.setColor(Color.darkGray);
		  g.drawRect(p.x+1,p.y+1,d.width,d.height);
		  g.drawRect(p.x+2,p.y+2,d.width,d.height);
		  g.setColor(bg);
		  g.fillRect(p.x,p.y,d.width,d.height);
		  g.setColor(Color.black);
		  g.drawRect(p.x,p.y,d.width,d.height);
		}
	  } 
	}
	else
	{
	  int x = p.x - 3 ;  // Initialize as top left. '2' is the offset
	  int y = p.y - 3 ;

	  if(statusPosition.trim().equals("topleft"))
	  {
		x = p.x - 3 ;  // "p" denotes top left postion of the sybol.
		y = p.y - 3 ;
	  }

	  else if(statusPosition.trim().equals("topright"))
	  {
		x = p.x + d.width + 3 ;
		y = p.y - 3 ;

	  }
	  else if(statusPosition.trim().equals("botleft"))
	  {
		x = p.x - 3 ;
		y = p.y + d.height + 3 ;
	  }
	  else if(statusPosition.trim().equals("botright"))
	  {
		x = p.x + d.width + 3 ;
		y = p.y + d.height + 3 ;
	  }

	  g.setColor(new Color(128,0,128));
	  g.drawRect(x-1,y-1,d.width/4+2,d.height/8+2);
	  g.setColor(bg);
	  g.fillRect(x,y,d.width/4+1,d.height/8+1);
	}

  } /* end paint() */

  /**
   * To paint Icon on the Map Symbol
   * @param     g 	  - Graphics of MapSymbolComponent
   * @param     mapSymbol - Reference of MapSymbolComponent object
   * @param     location  - Top Left Corner point of MapIcon
   * @param     size      - Size of the Map Icon 
   * @param     io        - ImageObserver  
   * @param     imageIcon - Image to be displayed on the Map Symbol
   * @param     selected  - Whether the symbol is selected or not
   */

  /** This paints the icon symbol at specified point, and dimension.*/
  public void paintIcon(Graphics g,MapSymbolComponent mapSymbol,Point p, Dimension d, ImageObserver io,Image img,boolean selected) 
  {
	DefaultMapModel model = MapClientAPI.getInstance().getMapModel(mapSymbol.getMapName());
	Properties prop = (Properties) model.getSymbolRendererProps();

	/*
	 * The "paintIcon" property tells  whether the icon is to be painted or not
	 */

	String paintIcon        = (String) prop.get("paintIcon");

	if((paintIcon == null) || (paintIcon.trim().equals("true")))
	{
	  if (img != null)
		g.drawImage(img,p.x,p.y,d.width,d.height,io);
	}
	//This paints four dark squares on the corners to indicate the object is selected
	if (selected) 
	{  
	  g.setColor(Color.black);
	  int markHt = d.height/5;
	  if (markHt < 5) markHt = 5;
	  g.fillRect(p.x-markHt, p.y-markHt, markHt,markHt);
	  g.fillRect(p.x+d.width, p.y-markHt, markHt,markHt);
	  g.fillRect(p.x-markHt, p.y+d.height, markHt,markHt);
	  g.fillRect(p.x+d.width, p.y+d.height, markHt,markHt);
	}	

  } 
  /* end paintIcon() */

  /**
   * To draw a label string at the bottom of Map Symbol.
   * @param     g 	  - Graphics of MapSymbolComponent
   * @param     mapSymbol - Reference of MapSymbolComponent object
   * @param     label     - String to be displayed
   * @param     location  - Starting location of string
   * @param     font      - Font type which is used to draw the label
   */

  public void paintLabelString(Graphics g,MapSymbolComponent mapSymbol, String s0, Point p,Font font ) 
  {
	/* This draws a centered label below specified point */
	Font f = font;
	String s = s0;
	if ( (s == null) || (f == null) ) return;



	DefaultMapModel model = MapClientAPI.getInstance().getMapModel(mapSymbol.getMapName());
	Properties prop = (Properties) model.getSymbolRendererProps();

	/* 
	 * The "paintLabel" property tells whether the label has to be painted or 
	 * not.
	 */

	String paintLabel        = (String) prop.get("paintLabel");

	if( paintLabel == null)
	{ 
	  paintLabel = "true";
	}

	if(paintLabel.trim().equals("true"))
	{
	  /* 
	   * The "labelPosition" property tells the position of the label whether
	   * top or bottom.
	   */

	  String labelPosition     = (String) prop.get("labPosition");
	  if (( labelPosition != null) && labelPosition.trim().equals("top"))
	  {
		labelPosition = "top";
	  }
	  else
	  {
		labelPosition = "bottom";
	  }

	  if (s.length() > MapConstants.MAX_LABEL_LENGTH) 
		s = s0.substring(0,MapConstants.MAX_LABEL_LENGTH) + "...";//No Internationalisation

	  //g.setFont(f);
	  FontMetrics fm = g.getFontMetrics();
	  int width = fm.stringWidth(s);
	  int height = fm.getHeight();
	  int x, y;

	  if(labelPosition.equals("top"))
	  {
		p.y = p.y - mapSymbol.getHeight() - height;
		y   = p.y + (3 * height / 4)  ;
	  }
	  else
	  {
		y = p.y + height - (height/4);
	  }
	  x = p.x - (width/2) - 1;


	  /*
	   * To show the status on the label background.
	   */

	  String  statusOnLabelBG = (String) prop.get("statusOnLabelBG");
	  if((statusOnLabelBG != null) && (statusOnLabelBG.trim().equals("true")))
	  {
		Color bg = SeverityInfo.getInstance().getColor(Integer.parseInt(mapSymbol.getProperty("status")));	
		g.setColor(bg);
	  }
	  else
	  {
		g.setColor(Color.white);
	  }


	  g.fillRect(x,p.y,width+4, height-1);
	  g.setColor(Color.black);
	  g.drawRect(x,p.y,width+4, height-1);
	  mapSymbol.setLabelSize(new Rectangle(x,p.y,width+4, height-1));

	  /*
	   * To show the status on the foreground of the label.
	   */

	  String  statusOnLabelFG = (String) prop.get("statusOnLabelFG");
	  if((statusOnLabelFG != null) && (statusOnLabelFG.trim().equals("true")))
	  {
		Color bg = SeverityInfo.getInstance().getColor(Integer.parseInt(mapSymbol.getProperty("status")));	
		g.setColor(bg);
	  }
	  else
	  {
		g.setColor(Color.black);
	  }
	  g.drawString(s,x+3,y);
	}
  } 
  // end of paintLabelString()
  /**
   * This method is used to change the tool tip text of an Map Symbol
   * @param		symb		-Reference of Map Symbol Object
   * @return	a <code>String </code> that is to be the tool tip for this MapSymbolComponent.
   */

  public String getToolTipText(MapSymbolComponent symb,int x, int y)
  {
	//Get the status from the status value .
        String toolTip = BasicMap.getToolTipProperty().trim();
		if(toolTip.equals("status"))//No Internationalisation
			return SeverityInfo.getInstance().getName(Integer.parseInt(symb.getProperty(toolTip)));
		return symb.getProperty(toolTip);
  }


  /**  To determine if a mouse down is inside the Map Symbol 
   * @param		x		- x  coordinate of mouse event relative to the map
   * @param		y		- y  coordinate of mouse event relative to the map
   * @param		symb		-Reference of Map Symbol Object
   * @return	a <code> boolean  </code> true , if the mouse is down on the MapLinkComponent
   */


  public boolean downInObject(int x, int y,MapSymbolComponent symb)
  {
	if ( (symb.getP0() == null) || (symb.getD0() == null) ) return false;
	Rectangle rect = null;
	if (symb.getObjType()==0)
	  rect= new Rectangle(symb.getP0().x + (symb.getD0().width*3)/8,
			  symb.getP0().y + (symb.getD0().height*3)/8,
			  symb.getD0().width/4, symb.getD0().height/4);
	else 
	  rect = new Rectangle(symb.getP0().x, symb.getP0().y, symb.getD0().width, symb.getD0().height);


	if (rect.contains(x,y)) 
	  return true;
	else if(symb.getLabelSize().contains(x,y))
	  return true;
	return false;
  }

}

