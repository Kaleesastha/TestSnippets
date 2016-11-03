//$Id: EthernetMapSymbolRenderer.java,v 1.1.4.3 2013/08/19 09:36:37 vijayalakshmiv Exp $
package com.adventnet.nms.mapui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.json.JSONException;
import org.json.JSONObject;

import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.mapui.MapConstants;
import com.adventnet.nms.mapui.MapSymbolComponent;
import com.adventnet.nms.mapui.MapSymbolRendererImpl_4;
import com.adventnet.nms.severity.*;

public class EthernetMapSymbolRenderer extends MapSymbolRendererImpl_4{

	
	public EthernetMapSymbolRenderer()
	{
		
	}
	
	public void paintIcon(Graphics g,
			MapSymbolComponent mapSymbol,
			Point p,
			Dimension d,
			ImageObserver io,
			Image img,
			boolean selected)
	{
		Graphics2D g2=(Graphics2D) g;
		if ( img != null )
		{
			Composite def = g2.getComposite();
			
			drawOuterBox(g2,mapSymbol.dim.width,p.x,p.y,mapSymbol.dim.height);
			g.drawImage(img,p.x,p.y+2,d.width,d.height,io);
			SeverityInfo si = SeverityInfo.getInstance();
			String status = si.getName(mapSymbol.getStatus());
			int sX = p.x+(mapSymbol.dim.width);
			int sY = p.y-(mapSymbol.dim.height/7);
			int statusWidth=mapSymbol.getWidth()/6;
			int statusHeight=mapSymbol.getWidth()/6;
			
			if(mapSymbol.flash == 0)
			{
				int l = mapSymbol.getHeight()/5;
				//int w=mapSymbol.getWidth()/6;
				Color color = SeverityInfo.getInstance().getColor(mapSymbol.getStatus());
				
				GradientPaint gp = new GradientPaint(sX+l,sY+l,Color.white,sX,sY+l,color);
				g2.setPaint(gp);
				g.fillOval(sX,sY,statusWidth,statusWidth);
			
			}
			else if(mapSymbol.flash == 1)
			{
				Color tmpcolor = g.getColor();
				Color color = (Color)flashColorList.get(status);
				if(color == null)
				{
					color = si.getFlashColor(status);
					flashColorList.put(status, color);
				}
				g.setColor(color);
				g.fillOval(sX, sY, statusWidth, statusWidth);
				g.setColor(Color.white);
				g.drawArc(sX+3, sY, statusWidth, statusHeight, 150, 75);
				g.drawArc(sX+4, sY, statusWidth, statusHeight, 150, 75);
				g.setColor(tmpcolor);
			}
			else if(mapSymbol.flash == -1)
			{
				Color tmpcolor = g.getColor();
				Color color = (Color)normalColorList.get(status);
				if(color == null)
				{
					color = si.getColor(status);
					normalColorList.put(status, color);
				}
				g.setColor(color);
				g.fillOval(sX, sY, statusWidth, statusWidth);
				g.setColor(Color.white);
				g.drawArc(sX+3, sY, statusWidth, statusWidth, 150,75 );
				g.drawArc(sX+4, sY+(mapSymbol.getHeight()+5), statusWidth, statusWidth, 150,75 );
				g.setColor(tmpcolor);
			}
			
			if(selected)
			{
				g2.setComposite(makeComposite(0.3f));
				g2.setColor(new Color(0,93,255));
				int symbWidth=mapSymbol.dim.width;
				int symheight=mapSymbol.dim.height;
				int width=18*mapSymbol.dim.width/10;
				int height=16*mapSymbol.dim.height/10;
				int x=p.x-(symbWidth/3);
				int y =p.y-((symheight/2));
				g2.fillRoundRect(x, y, width,height,10,10);
			
			}
			g2.setComposite(def);
		}

		
		
	} /* end paintIcon() */
private String handleMoProps(String propertyName,String value) {
		
		if(propertyName.equals("status"))
		{
			try
			{
			SeverityInfo info=SeverityInfo.getInstance();
			if(info != null){
			value=info.getName(Integer.parseInt(value));
			}
			}
			catch(NumberFormatException nfe)
			{
				//Status is already a string.Hence, we return the value as it is
				return value;
			}
		}
		else if(propertyName.equals("discoveryStatus"))
		{
			try
			{
			int val = Integer.parseInt(value);
			switch(val)
			{
			case(1):
			{
			value = "Yet to begin";
			break;
			}
			case(2):
			{	
			value="In progress";
			break;
			}
			case(3):
			{	
			value="Finished";
			break;
			}
			case(4):
			{	
			value="Disabled";
			break;
			}
			}
			}
			catch(NumberFormatException nfe)
			{
				//Discovery status is already a string.Hence, we return the value as it is
				return value;
			}
			
		}
		return value;
}
	private AlphaComposite makeComposite(float alpha) {
	    int type = AlphaComposite.SRC_OVER;
	    return(AlphaComposite.getInstance(type, alpha));
	  }
	private void drawOuterBox(Graphics2D g2, int width, int x, int y, int height) {
		
		int x1 = x-(6*width/10);
		int y1 = y-(12*height/10);
		int y2 = y+(12*height/10);
		
		GradientPaint gp = new GradientPaint(x1,y1,new Color(236,236,236),x1,y2,Color.white);
		g2.setPaint(gp);
		g2.fillRoundRect(x-(width/4),y-(24*height/100),(160*width/100),(12*height/10),10,10);
	}

	public void paintLabelString(Graphics g,MapSymbolComponent mapSymbol, String s0, Point p,Font font )
	{
		/* This draws a centered label below specified point */

		if ( (s0 == null) || (font == null) ) return;
		String s=getStringforLabel(s0);
				drawString(s,p.x,p.y,g,mapSymbol);
				}
	// end of paintLabelString()
	
	
	private void drawString(String s,int x0,int y0,Graphics g,MapSymbolComponent mapSymbol)
	{
		g.setFont(NmsClientUtil.getFont());
		g.setColor(Color.black);
		FontMetrics fm = g.getFontMetrics();
		int fwidth = fm.stringWidth(s);
		int fheight = fm.getHeight();
		int k = mapSymbol.dim.width;
		int k0=mapSymbol.dim.height;
		int x=x0-(8*k/10);
		int y=y0-(17*k0/10);
		g.drawString(s,x+2,y+fheight);
			
	}
	
	private String getStringforLabel(String s0)
	{
		String temp="";//No internationalization
		for(int i=0;i<s0.length();i++)
		{
			if(s0.charAt(i)=='\n' || s0.charAt(i)=='\t')
			{
				continue;
			}
			temp=temp+s0.charAt(i);
		}

		s0=temp;
		String s=s0;
	
	
		if (s.length() > MapConstants.MAX_LABEL_LENGTH)
		{	
			s = s0.substring(0,MapConstants.MAX_LABEL_LENGTH) + "...";//No Internationalisation
			Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");//No internationalization  
		//check if string is not an IPAddress
		if(!ipPattern.matcher(s).matches())
			s = s0.substring(0,MapConstants.MAX_LABEL_LENGTH-3) + "...";//No Internationalisation
		}
		
		return s;
	}
	
	
	
	

	private int convert(double x)
	{
		Double db = new Double(x);
		return db.intValue();
		
	}
	public boolean downInObject(int x, int y,MapSymbolComponent symb)
	{
		if ( (symb.getP0() == null) || (symb.getD0() == null) ) return false;
		Rectangle rect = null;
		Rectangle tri=null;
		if (symb.getObjType()==0)
			rect= new Rectangle(symb.getP0().x + (symb.getD0().width*3)/8,
					symb.getP0().y + (symb.getD0().height*3)/8,
					symb.getD0().width/4, symb.getD0().height/4);
		else
		{
			SeverityInfo si = SeverityInfo.getInstance();
			String status = si.getName(symb.getStatus());
			
			int symbWidth=symb.getWidth();
			int symheight=symb.getHeight();
			int x0=symb.getP0().x;
			int y0=symb.getP0().y;
			int sX = x0-((5*symbWidth)/10);
			
			int sY=y0-((6*symheight)/10);
			int sW=(2*symbWidth);
			int sH=(15*symheight)/10;
			
			rect = new Rectangle(sX,sY,sW,sH);
		}

		if (rect.contains(x,y)) 
			return true;
		else if(symb.getLabelSize().contains(x,y))
			return true;
		return false;
	}
	
	public String getToolTipText(MapSymbolComponent symb,int x,int y)
	{
		
		
		Border border = BorderFactory.createEmptyBorder(0, 0, 0, 0);
		UIManager.put("ToolTip.border",border);//No internationalization
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html>");//No internationalization
		sb.append("<head>");
		sb.append("<style>");
		sb.append(".rowOdd{height:22px; font-family:arial,verdana;color:#000; font-size:8px;color:#000;background-color:#f3f8fd;padding-left:5px; text-align:left;border-top:1px solid #fff;}");
		sb.append(".rowHdr{height:22px; font-family:arial,verdana;color:#000; font-size:9px;color:#FFFFFF;font-weight:bold;background-color:#2c69b1;text-align:center;}");
		sb.append(".rowEven{height:22px; font-family:arial,verdana;color:#000; font-size:8px;color:#000;background-color:#e8f3fd;padding-left:5px; text-align:left;border-top:1px solid #fff;}");
		sb.append(".brdWhite{border:1px solid #FFFFFF;}");
		sb.append(".container{width:180px; padding:5px 3px 5px 3px;background-color:#b8cfe5;}");
		sb.append("</style>");
		sb.append("</head>");
		sb.append("<body>");
		Hashtable tooltipprops = NmsClientUtil.mapSymbolTooltipReader.getTooltipPropsTable();
		
		String type = symb.getType();
		
		if(type != null)
		{	
		Vector v = (Vector) tooltipprops.get(type);
		
		int dy=20;

		sb.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("<div class=\"container\">");
		sb.append("<table class=\"brdWhite\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">");
		
		sb.append("<tr><td class=\"rowHdr\" colspan=3>"+symb.getLabel()+"</td></tr>");//No internationalization
			
		String font = NmsClientUtil.getFont().getName();
			try{
				
		if(v==null)
			
		{
			sb.append("<tr><td class=\"rowEven\" width=1></td><td class=\"rowEven\" width=80>"+"Name"+"</td>");//No internationalization
			sb.append("<td class=\"rowEven\">"+symb.getName()+"</td></tr>");//No internationalization
			sb.append("</table></div></td></tr></table></body></html>");//No internationalization
			return sb.toString();
		}
		for(int i=0;i<=v.size()-1;i++)
		{
			Properties props = (Properties) v.elementAt(i);
			String prop = (String) props.get("name");//No internationalization
			String val = symb.getProperty(prop);
			if(val == null)
			{
				val=getValfromJSON(prop, symb);
			}	
			if(val == null)
			{	
				continue;
			}
			val=handleMoProps(prop,val);
				
			if(val.length()>20)
			{
				val=val.substring(0,16);
				val=val+"...";//No internationalization
			}
			
			if(i%2==0)
			{
				sb.append("<tr><td class=\"rowEven\" width=1></td><td class=\"rowEven\" width=80 style=\"padding-left=5px\" >"+props.get("label").toString()+"</td>");//No internationalization
				sb.append("<td class=\"rowEven\">"+val+"</td></tr>");//No internationalization
			}
			else
			{
				sb.append("<tr><td class=\"rowOdd\" width=1></td><td class=\"rowOdd\" width=80 style=\"padding-left=5px\">"+props.get("label").toString()+"</td>");//No internationalization
				sb.append("<td class=\"rowOdd\" >"+val+"</td></tr>");//No internationalization
			}
		}
		
		sb.append("</table></div></td></tr></table></body></html>");//No internationalization
					
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		String s = sb.toString();
		return s;
	}
		return "";
	}		
	
	private String getLongestString(Enumeration en)
	{
		int max=0;
		String longest="";//No internationalization
		while(en.hasMoreElements())
		{
		String s = (String) en.nextElement();
		int width = s.length();
		if(width>max)
		{	
			max=width;
			longest=s;
		}	
		}
		
		return longest;
	}
	
	private int getLongestValues(Vector v,String toget,MapSymbolComponent symb)
	{
		Enumeration en = v.elements();
		Vector vec = new Vector();
		while(en.hasMoreElements())
		{
			Properties props = (Properties) en.nextElement();
			if(toget.equals("label"))//No internationalization
			{
				String p = (String)props.get("label");//No internationalization
				if(p.length()>20)
					return 20;
				vec.add(p);
			}
			else{
				String name = (String)props.get("name");//No internationalization
				name=symb.getProperty(name);
				if(name == null)
				{
					name=symb.getUserProperty(name);
				}
				vec.add(name);
			}	
		}
		return getLongestString(vec.elements()).length();
		
	}
	
	private String getTruncatedString(String val)
	{
		if(val.length()>20)
		{
			val=val.substring(0,16);
			val=val+"...";//No internationalization
		}
		
		return val;
	}
	
	private String getValfromJSON(String key,MapSymbolComponent symb)
	{
		String obj = symb.getUserProperty("tooltip");//No internationalization
		 
		if(obj != null)
		{
			JSONObject json;
			try {
				json = new JSONObject(obj);
				if(json.has(key))
				return json.getString(key);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		return null;
	}
		
}
