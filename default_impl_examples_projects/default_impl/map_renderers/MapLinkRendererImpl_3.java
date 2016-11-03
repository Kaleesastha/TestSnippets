//$Id: MapLinkRendererImpl_3.java,v 1.1.4.4 2013/07/24 09:42:38 vijayalakshmiv Exp $

package com.adventnet.nms.mapui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.Vector;

import com.adventnet.nms.mapui.DefaultMapModel;
import com.adventnet.nms.mapui.MapClientInitializer;
import com.adventnet.nms.mapui.MapLinkComponent;
import com.adventnet.nms.mapui.MapLinkRendererInterface;
import com.adventnet.nms.mapui.MapSymbolComponent;

/* This renderer may be used by users to draw arc like links between Map symbols. Note that,an ordinary grid layout may not
 * complement this renderer as links between symbols belonging to the same column will appear more or less as straight lines
 * Bezier's quadratic curve equations have been used for this layout.
 */


public class MapLinkRendererImpl_3 implements MapLinkRendererInterface {

	
	private Composite def=null;
	private boolean first=true;
	
	
	public boolean downInObject(int x, int y, MapLinkComponent link) {
	
		if(link==null||link.getP0() == null)
			return false;
		
		int width =link.getSourceObj().getWidth();
		int height =link.getSourceObj().getHeight();
		int x01 = link.getP0().x;
		int y01 = link.getP0().y;
		int x02 = link.getNx();
		int y02 = link.getNy();
		int index=0;
		if(link.getUserProperty("linkIndex") !=null)
		{
			index = Integer.parseInt(link.getUserProperty("linkIndex"));
		}
		QuadCurve2D q = getCurve(x01, y01, x02, y02, width, height,index);
		
		return liesOncurve(q.getX1(),q.getX2(),q.getY1(),q.getY2(),q.getCtrlX(),q.getCtrlY(),x,y);
		
	}

	private ArrayList solveasQuad(double a, double b, double c)
	{
		double D = (b*b)-(4*a*c);
		ArrayList sol = new ArrayList();
		double sol1 = (-b+Math.sqrt(D))/(2*a);
		double sol2 = (-b-Math.sqrt(D))/(2*a);
		if(sol1>=0)
		sol.add(Round(sol1,1));
		if(sol2>0)
		sol.add(Round(sol2,1));
		
		return sol;
	}
	
	private double solveasLinear(double b,double c)
	{
		return Round(-c/b,1);
	}
	private double Round(double num,int pl)
	{
		double p = (double)Math.pow(10,pl);
		  num = num * p;
		  float tmp = Math.round(num);
		  return (double)tmp/p;
	}
	private int convert(double x)
	{
		Double db = new Double(x);
		return db.intValue();
		
	}
	
	public String getToolTipText(MapLinkComponent link, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void paintLabelString(Graphics g, MapLinkComponent mapLink,
			Point sourcePoint, Point destPoint, String label, Font font) {
		// TODO Auto-generated method stub

	}

	 public Vector<MapLinkComponent> oldLinks=new Vector<MapLinkComponent>();
	 
	
	public void paintMapLink(Graphics g, MapLinkComponent mapLink,
			Point sourcePoint, Point destPoint, Color status, boolean selected) {
		// TODO Auto-generated method stub
		DefaultMapModel model = MapClientInitializer.mapp.getMapModel(mapLink.getMapName());
		Vector v= model.getSelectedSymbols();
		for (int i=0;i<v.size();i++)
		{
			MapSymbolComponent mComponent=(MapSymbolComponent)v.get(i);
			Vector<MapLinkComponent> mLinks=mComponent.getConnectedLinks();
			for(MapLinkComponent links:mLinks)
			{
				String mkey=links.getKey();
				String currentKey=mapLink.getKey();
				if(mkey.equals(currentKey) && selected)
				{
					selected=true;
				}
				else
				{
					selected=false;
				}
			}
		}
		
		
		Graphics2D g2 = (Graphics2D) g;
		if(first)
		{
			def=g2.getComposite();
			first=false;
		}
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		BasicStroke wideStroke = new BasicStroke(1.0f);
		g2.setStroke(wideStroke);
		g2.setColor(status); 
		int x01=sourcePoint.x;
		int y01=sourcePoint.y;
		
		int x02=destPoint.x;
		int y02=destPoint.y;
		
		
		int width=mapLink.getSourceObj().dim.width;
		int height=mapLink.getSourceObj().dim.height;
		int index=0;
		if(mapLink.getUserProperty("linkIndex") !=null)
		{
			index = Integer.parseInt(mapLink.getUserProperty("linkIndex"));
		}	
		
		if(!selected)
		{
			if(mapLink.getSourceObj().selected||mapLink.getDestObj().selected)
				selected=true;
		}
	 	QuadCurve2D curve=getCurve(x01,y01,x02,y02,height,width,index);
	 	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
	 	boolean shade=false;
	 	if(selected)
	 	{
	 		shade=false;
	 		
	 		Color focusColor=g2.getColor();
	 		g2.setStroke(new BasicStroke(4));
		    g2.setColor(new Color(focusColor.getRed(), focusColor.getGreen(), focusColor.getBlue(),90));
		    g2.draw(curve);
		   
	 	}
	 	
	 else{
	 		
				shade=true;
					
	 	}
	 	Vector lns = model.getSelectedLinks();
	 	if((v==null||v.size()==0)&&(lns==null||lns.size()==0))
	 	
	 		shade=false;
	 	if(!shade)
	 	{
	 		
	 		g2.setComposite(def);
	 		
	 	}
	 	else{
	 		g2.setComposite(makeComposite(0.3f,AlphaComposite.SRC_OVER));
	 		
	 	}
	 	g2.setColor(status);
	 	BasicStroke stroke= new BasicStroke(1);
	 	if(mapLink.getLinkType()==12)
	 	{
	 		float dash1[] = {4.0f};
	 		stroke = new BasicStroke(2.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,7.0f, dash1, 0.0f);
	 		
	 	}
	 	g2.setStroke(stroke);
	 	g2.draw(curve);
	 	g2.setComposite(def);
	 	
	

	}
	
	private AlphaComposite makeComposite(float alpha,int type) {
		 
	    return(AlphaComposite.getInstance(type, alpha));
	  }
	private QuadCurve2D getCurve(int x01,int y01,int x02,int y02,int width,int height, int index)
	{
		boolean samerow=false;
		//Calculated based on Bezier;s quadratic curve
		int x1=x01-(width/7);
		
		int y1=y01-(30*height/100);
		
		int x2 =x02+(width/10);
		int y2=y02-(27*height/100);
		
		if(y01==y02)
		{
			samerow=true;
			
		}
		
		Point p3 = getThirdPoint(x1,y1,x2,y2,samerow,index);
		Point ctrl = getControlPoint(p3,x1,y1,x2,y2);
		QuadCurve2D curve=new QuadCurve2D.Double(x1,y1,ctrl.x,ctrl.y,x2,y2);
		return curve;
	 	
	 	 	
	}
	
	private Point getControlPoint(Point p3,int x1,int y1,int x2,int y2)
	{
		int x3 = p3.x;
		int y3 = p3.y;
		double t = 0.5;
		
		double k = 2*(1-t)*t;
		double c1 = (1-t)*(1-t);
		double c2 = (t*t);
		
		double Px=(1/k)*(x3-(c1*x1)-(c2*x2));
		double Py=(1/k)*(y3-(c1*y1)-(c2*y2));
		
		return new Point(new Double(Px).intValue(),new Double(Py).intValue());
	}
	
	private boolean liesOncurve(double x1,double x2,double y1,double y2,double ctrlx,double ctrly,int x,int y)
	{
				
		double ax = x2+x1-(2*ctrlx);
		double bx = (ctrlx-x1)*2;
		double cx =x1-x;
		double solx1 =0;
		boolean islinear = false;
		ArrayList solx2=null;
		if(Math.round(ax)==0)
		{
			islinear = true;
			solx1 = solveasLinear(bx, cx);
		}
		else{
			solx2 = solveasQuad(ax, bx, cx);
		}
		double soly1=0;
		ArrayList soly2=null;
		
		int ay = convert(y2+y1-(2*ctrly));
		int by = convert(ctrly-y1)*2;
		int cy = convert(y1-y);
		if(Math.round(ay)==0)
		{
			soly1=solveasLinear(by, cy);
			if(solx1==soly1||(solx2 != null && solx2.contains(soly1)))
				return true;
		}
		else{
			soly2=solveasQuad(ay, by, cy);
			if(islinear && soly2 != null && soly2.contains(solx1))
				
				return true;
				
			else if(solx2 != null && solx2.size()==2)
				{
				if(soly2.contains(solx2.get(0))||soly2.contains(solx2.get(1)))
				return true;
				}
			else if(solx2 != null && solx2.size()==1)
			{
				if(soly2.contains(solx2.get(0)))
						return true;
			}
			
		}
		
		return false;
	}
	
	
	private Point getThirdPoint(int x1,int y1,int x2,int y2,boolean samerow,int index)
	{
		//dist between x1,y1 and x2,y2
		
		boolean adjacentcolumn = false;
		
		if(Math.abs(x2-x1)<100 && Math.abs(x2-x1)>50 && !samerow)
			adjacentcolumn=true;
		
		double dist = getDistance(x1, x2, y1, y2);
		double angle=18;
		
		if(samerow)
		{
			angle=10;
			
		}
		
		int x3 = (x1+x2)/2;
		int y3=(y2+y1)/2;
		
		if(adjacentcolumn)
		{
			angle=65;
		}
		angle=angle+(2*index);
		double m = Math.tan(Math.toRadians(angle));
		// determine intercept for perpendicular line
		double m0 = (1/m);
				
		//determine the third point by solving the equations for distance formula and line equation
		
		double d = m*(dist/2);
		
		double x = x3;
		
		if(x2==x1)
			x=x1+(40*x1/100);
		double inter2 = d*(m0/(Math.sqrt((m0*m0)+1)));
		
		double y=y3-inter2;
		
		
		return new Point(new Double(x).intValue(),new Double(y).intValue());
	}

	private double getDistance(int x1,int x2,int y1,int y2)
	{
		int k1 = (x2-x1)*(x2-x1);
		int k2 = (y2-y1)*(y2-y1);
		return Math.sqrt(k1+k2);
	}
}