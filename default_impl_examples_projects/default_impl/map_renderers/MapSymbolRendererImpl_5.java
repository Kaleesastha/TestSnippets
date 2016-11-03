//$Id: MapSymbolRendererImpl_5.java,v 1.1.4.4 2013/08/19 09:41:10 vijayalakshmiv Exp $
package com.adventnet.nms.mapui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
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
import java.awt.Rectangle;
import java.awt.RenderingHints;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

import org.json.JSONException;
import org.json.JSONObject;

import com.adventnet.nms.mapui.DefaultMapModel;
import com.adventnet.nms.mapui.MapClientInitializer;
import com.adventnet.nms.mapui.MapConstants;
import com.adventnet.nms.mapui.MapLinkComponent;
import com.adventnet.nms.mapui.MapSymbolComponent;
import com.adventnet.nms.mapui.MapSymbolRendererInterface;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.util.NmsClientUtil;

public class MapSymbolRendererImpl_5 implements MapSymbolRendererInterface {

	
	protected int labelwidth=0;
	protected int labelheight=0;
	boolean first=true;
	Composite def=null;
	public void paintShapeAndSeverity(Graphics g, MapSymbolComponent mapSymbol,
			Point location, Dimension size, Color status, int objType) {
		// TODO Auto-generated method stub
		
	}

	
	public void paintIcon(Graphics g, MapSymbolComponent mapSymbol,
			Point p, Dimension d, ImageObserver io, Image img,
			boolean selected) {
		
		Graphics2D g2=(Graphics2D) g;
		boolean shade=false;
		if(first)
		{	
		def = g2.getComposite();
		first=false;
		}
		if ( img != null )
		{
			
			
			
			
			Vector links = mapSymbol.getConnectedLinks();
			if(selected)
			{
				
				String s=getStringforLabel(mapSymbol.getLabel());
				
				FontMetrics fm = g.getFontMetrics();
				
				int height=mapSymbol.getHeight()+labelheight;
				
								
				double y1 = (p.y+3);
				
				
				g2.setStroke(new BasicStroke(1.0f));
				g.setColor(new Color(0,14,63));
				double x1= (p.x-(mapSymbol.getWidth()/3));
				g.fillRoundRect(convert(x1),convert(y1),convert(1.2*labelwidth),height,5,5);
				g.setColor(new Color(22,55,96));
				g.drawRoundRect(convert(x1-1),convert(y1-1),convert((1.2*labelwidth)+1),height,5,5);
				
				if(links != null)
				
				shade=false;	
				
			}
			
			
			
		else{
			DefaultMapModel model = MapClientInitializer.mapp.getMapModel(mapSymbol.getMapName());
			if(model.getUserProperty("highlightLinks")!=null&&model.getUserProperty("highlightLinks").equalsIgnoreCase("true"))
			{
				shade=true;
			
						
				Vector v= model.getSelectedSymbols();
				if(v!=null&& v.size()>0)
				{
				for(int j=0;j<=v.size()-1;j++)
				{
					MapSymbolComponent symbs = (MapSymbolComponent) v.elementAt(j);
					Vector lns = symbs.getConnectedLinks();
					for(int i=0;i<=lns.size()-1;i++)
					{
					MapLinkComponent link =(MapLinkComponent) lns.elementAt(i);
					if(mapSymbol==link.getSourceObj()||mapSymbol==link.getDestObj())
					{
						shade=false;
						break;
					}
					
				}
				}	
				}
				Vector slinks = model.getSelectedLinks();
				if(slinks!=null)
				{
					for(int k=0;k<=slinks.size()-1;k++)
					{
						MapLinkComponent link = (MapLinkComponent)slinks.elementAt(k);
						if(mapSymbol==link.getSourceObj()||mapSymbol==link.getDestObj())
						{
							shade=false;
							break;
						}
						
					}
				}
			}	
				
			if((model.getSelectedLinks()==null||model.getSelectedLinks().size()==0)&&(model.getSelectedSymbols()==null||model.getSelectedSymbols().size()==0))
				shade=false;
			}
				if(shade){
					g2.setComposite(makeComposite(0.3f,AlphaComposite.SRC_OVER));
				}
				else
					g2.setComposite(def);
					
			
				paintSymbols(g2,mapSymbol,p,img,d,io,selected);
			
			g2.setComposite(def);
		}
						
		}
	
	public static void convertToGrayscale(BufferedImage source) { 
	     BufferedImageOp op = new ColorConvertOp(
	       ColorSpace.getInstance(ColorSpace.CS_CIEXYZ), null); 
	     op.filter(source,source);
	}
	private boolean getShade(MapSymbolComponent mapSymbol,DefaultMapModel model)
	{
		boolean shade = false;
		Vector links = mapSymbol.getConnectedLinks();
		Vector v= model.getSelectedSymbols();
		Vector slinks = model.getSelectedLinks();
		if((v==null||v.size()==0)&&(slinks==null||slinks.size()==0))
			return false;
		if(mapSymbol.selected)
			shade=true;
		for(int j=0;j<=v.size()-1;j++)
		{
			MapSymbolComponent symbs = (MapSymbolComponent) v.elementAt(j);
			Vector lns = symbs.getConnectedLinks();
			for(int i=0;i<=lns.size()-1;i++)
			{
			MapLinkComponent link =(MapLinkComponent) lns.elementAt(i);
			if(mapSymbol==link.getSourceObj()||mapSymbol==link.getDestObj())
			{
				shade=false;
				break;
			}
			
			}
		}
		
		
		for(int k=0;k<=slinks.size()-1;k++)
			{
				MapLinkComponent link = (MapLinkComponent)slinks.elementAt(k);
				if(mapSymbol==link.getSourceObj()||mapSymbol==link.getDestObj())
				{
					shade=false;
					break;
				}
				
			}
		
		return shade;
		
	}
	
	private void paintSymbols(Graphics2D g2,MapSymbolComponent mapSymbol,Point p,Image img,Dimension d, ImageObserver io,boolean selected)
	{
		g2.setStroke(new BasicStroke(1.0f));
		BufferedImage bufferedImage=null;
	
		int wid = mapSymbol.getWidth();
		//Width of ellipse is 1.5 times width of image
			double w = (1.3)*wid;
			
		//Heigth of ellipse is 1/3 rd width of ellipse
			double h = w/3;
			
			double x= (p.x)+(wid/2)-(w/2);
			
			double y = (p.y)+((mapSymbol.getHeight())/2);
			
			
			
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
			
			GradientPaint gp = new GradientPaint(convert(x+(w/2)),convert(y+h),new Color(2,151,234),convert(x+(w/2)),convert(y),new Color(0,26,69));
			g2.setPaint(gp);
			g2.fillOval(convert(x),convert(y),convert(w),convert(h));
			g2.setColor(Color.black);
			if(bufferedImage!=null)
				g2.drawImage(bufferedImage,p.x,p.y,d.width,d.height,io);
			else
			g2.drawImage(img,p.x,p.y,d.width,d.height,io);
			double x1= (p.x-(mapSymbol.getWidth()/10));
			
			double y1 = (p.y)+((2.5*mapSymbol.getHeight()));
			drawString(mapSymbol.getLabel(),convert(x1),convert(y1),g2,mapSymbol,selected);
	}

	private AlphaComposite makeComposite(float alpha,int type) {
	 
	    return(AlphaComposite.getInstance(type, alpha));
	  }
	private int convert(double x)
	{
		Double db = new Double(x);
		return db.intValue();
		
	}
	
	public void paintLabelString(Graphics g, MapSymbolComponent mapSymbol,
			String s0, Point p, Font font) {
		
		if ( (s0 == null) || (font == null) ) return;
		String s=getStringforLabel(s0);
		int wid = mapSymbol.getWidth();
		//double w = (1.3)*wid;
		double x= (p.x-(6*wid/10));
		
		double y = (p.y)+((1.5*mapSymbol.getHeight()));
		
	}
	
	private void drawString(String s,int x0,int y0,Graphics2D g,MapSymbolComponent mapSymbol,boolean selected)
	{
		g.setFont(NmsClientUtil.getFont());
		
		FontMetrics fm = g.getFontMetrics();
		int fwidth = getLabelWidth(s,fm);
		int fheight = fm.getHeight();
		
		int k0=mapSymbol.dim.height;
		
		
		int y=y0-(16*k0/10);
		
		
		
		int rectx=x0-17	;
		int recty=y;
		int h = fheight+2;
		int rad = 9;
		labelwidth=fwidth+rad;
		labelheight=h;
	
		if(!selected)
		{
		g.setColor(new Color(22,55,96));
		g.drawRoundRect(rectx, recty+5,fwidth+rad,labelheight+1,5,5);
		g.setColor(new Color(0,26,69));
		}
		else
			g.setColor(new Color(0,14,63));
		
		g.fillRoundRect(rectx+1,recty+6,fwidth+(rad-1),labelheight,5,5);
		g.setColor(new Color(150,227,245));
		g.drawString(s,x0,y+fheight+3); 
		
		drawStatus(g,mapSymbol,x0-(convert(1.5*rad)),recty+rad,9);
			
	}
	
	private void drawStatus(Graphics g,MapSymbolComponent mapSymbol,int x,int y, int rad)
	{
		SeverityInfo si = SeverityInfo.getInstance();
		String status = si.getName(mapSymbol.getStatus());
		Color color = SeverityInfo.getInstance().getColor(mapSymbol.getStatus());
		g.setColor(color);
		g.fillOval(x,y,rad,rad);
	}
	private int getLabelWidth(String s, FontMetrics fm)
	{
		
			int diff = MapConstants.MAX_LABEL_LENGTH-s.length();
			String temp="a";
			while(temp.length()<diff)
			{
				temp=temp+"a";
			}
			s=s+temp;
			int fwidth = fm.stringWidth(s);
			return fwidth+5;
				
	}
	private String getStringforLabel(String s0)
	{
		String temp="";
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
			Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");  
		//check if string is not an IPAddress
		if(!ipPattern.matcher(s).matches())
			s = s0.substring(0,MapConstants.MAX_LABEL_LENGTH-3) + "...";//No Internationalisation
		}
		
		return s;
	}

	
	public String getToolTipText(MapSymbolComponent symb, int x, int y) {
		// TODO Auto-generated method stub
		Border border = BorderFactory.createEmptyBorder(0, 0, 0, 0);
		 UIManager.put("ToolTip.border",border);
		 StringBuilder sb = new StringBuilder();
		
		sb.append("<html>");
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
		if(tooltipprops != null && type != null)
		{	
		Vector v = (Vector) tooltipprops.get(type);
		
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
			String prop = (String) props.get("name");
			String val =symb.getProperty(prop);
			if(val==null)
			{
					val=getValfromJSON(prop, symb);
			}		
			if(val == null)
			{	
				continue;
			}
			val = handleMoProps(prop, val);	
				
			if(val.length()>20)
			{
				val=val.substring(0,16);
				val=val+"...";
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
		return null;
		
	}

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
	private String getValfromJSON(String key,MapSymbolComponent symb)
	{
		String obj = symb.getUserProperty("tooltip");
		 
		if(obj != null)
		{
			JSONObject json;
			try {
				json = new JSONObject(obj);
				return json.getString(key);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		return null;
	}
	
	public boolean downInObject(int x, int y, MapSymbolComponent symb) {
		// TODO Auto-generated method stub
		
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
		rect = new Rectangle(symb.getX(),symb.p.y-5,symb.getWidth(),symb.getHeight());
		
		}
		if (rect.contains(x,y)) 
			return true;
		else if(symb.getLabelSize().contains(x,y))
			return true;
		return false;
		
		
	}

	
}

