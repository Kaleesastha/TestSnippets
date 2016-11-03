/*$Id: ThreeDimensionalLayout.java,v 1.1.4.5 2013/07/18 06:06:29 vijayalakshmiv Exp $*/

package com.adventnet.nms.mapui;


import java.awt.Point;
import java.awt.Toolkit;
import java.util.Properties;
import java.util.Vector;

import com.adventnet.nms.mapui.AutoLayout;
import com.adventnet.nms.mapui.DefaultMapModel;
import com.adventnet.nms.mapui.MapClientInitializer;
import com.adventnet.nms.mapui.MapContainerComponent;
import com.adventnet.nms.mapui.MapSymbolComponent;

/*
 * This layout is used to impart a 3D look-and-feel to a map.It is a simple modification of the conventional grid layout. Here the symbols are arranged as rows and columns. But the columns are placed at varying angles and the size of rows and size of symbols in each row are progressively reduced. Hence, a 3D feel is achieved.
 * In this layout, GridLayout parameters like gapX,gapY,isActualIconSize,isPercentage etc are not considered.
 */
public class ThreeDimensionalLayout implements AutoLayout {

	static double screenHeight = 1.0D;
    static double screenWidth  = 1.0D;
    int rows =0;
	int columns =0;
	Point lastleft=null;
	Point lastright=null;
	double k=450; //the distance above the map view height.This will be used for computing the angle that the outermost column makes with the horizontal axis 
	double theta=0.0;//this will be computed based on the above k factor
	double diff =0;
	private Point[][] inter = null;
    static
    {
	screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	screenWidth  = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }
    
    /** Layout all the symbols and links in a map specified by MapModel **/

	
	public void addLayoutObject(MapSymbolComponent symb,
			MapContainerComponent parent, int depth) {
		
	}

	public String getName() {
		return "3Dimension";
	}

	public void layoutMap(MapContainerComponent mc, int index) {
		// TODO Auto-generated method stub
		
		Vector symbols = mc.getSymbols();
		symbols = processSymbolsForanchored(symbols);
		
		//DefaultMapModel model=MapClientAPI.getInstance().getMapModel(mc.getMapName());
		DefaultMapModel model=(DefaultMapModel)MapClientInitializer.cacheMapTable.get(mc.getMapName());
		if(model==null)return;

		Properties p = mc.getCurrentTopologyProperties();
		int numSymbols = symbols.size();
		int vw = 0;
		int vh = 0;
	        
		if( index == 0)
		{
	            vh = (int)(model.getViewHeight() * model.zoomRatio);
	            vw = (int)(model.getViewWidth() * model.zoomRatio);
		}
		else
		{
	            vw = mc.getWidth();
	            vh = mc.getHeight();
		}


		boolean verticalScroll   = false;
		boolean horizScroll      = false;
		if ((numSymbols <= 0)||(vw <= 0)||(vh <= 0)) 
	            return;
	      	     
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
	            verticalScroll   = false;
	            horizScroll      = false;
	        }

	       //Step 1: set the lower most point  
	    setLowerMostPoint(vw,vh);
	       
		{
	            
	            columns = (int) Math.ceil(Math.sqrt(numSymbols));
	            rows = columns-1;  
	            if ( (columns*rows) < numSymbols )  rows = columns; 
	            if ( (columns*rows) < numSymbols )  columns++; 
	           
	            int width = 0;
	            int height = 0;

	           
	            
	            if(index==0)
	            {
	              
	                if( verticalScroll && !horizScroll ) 
	                {
	                    while(width > (int)(model.getViewWidth()*model.zoomRatio))
	                        { 
	                                                
	                            columns--;
	                            rows++;
	                                   
	                        }	
	                                    
	                    // "cols" should not be increased and "cols" * "rows" should
	                    // be equal to number of symbols.
	                    if ( (columns*rows) < numSymbols )   
	                    {
	                        while(columns*rows < numSymbols)
	                        {
	                            rows++;
	                        }
	                    }
	                    
	                }
	                /*
	                 * If the horizontal scroll s selected, then the cols & rows 
	                 * are to be modified as follows.
	                 */
	                else if(horizScroll  && !verticalScroll) 
	                {
	                    while(height > (int)(model.getViewHeight()*model.zoomRatio)){
	                            
	                                                     
	                            columns++;
	                            rows--;
	                           // height = rows * dy+(dy/2-maxDimH/2);
	                        } 
	                    
	                    // "rows" should not be increased and "cols" * "rows" should
	                    // be equal or greater than number of symbols.
	                    
	                    if ( (columns*rows) < numSymbols )   
	                    {
	                        while(columns*rows < numSymbols)
	                        {
	                            columns++;
	                        }
	                    }
	            
	                    
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
	                    model.setWidth((int)(model.getViewWidth() * model.zoomRatio));
	                }
	                
	         
	                model.setHeight((int)(model.getViewHeight() * model.zoomRatio));
	                model.setWidth((int)(model.getViewWidth() * model.zoomRatio));
	            }
	       
	            inter = new Point[rows][columns];
	            drawLines(model);
	            int j=0;
	            int i=0;
	            boolean done = false;
	           
                   	while(j<=rows-1)
                	{
                   	 MapSymbolComponent obj1 =null;
                   		int k = 0;
                		while(k<=columns-1)
                		{	
                			if(i>symbols.size()-1)
                			{
                			done=true;
                			break;
                			}	
                			MapSymbolComponent obj = (MapSymbolComponent) symbols.elementAt(i);
                		
                			if (obj.getObjType() == 0) 
                				i++;
                					if(i>0 && obj1 == null){
                						obj1 = (MapSymbolComponent)symbols.elementAt(i-1);
                						
                					}	
                				
                            			
                				obj.p=inter[j][k];
                				int symwidth=0;
                				int symheight=0;
                				double factor=0.8;
                				if(i==0)
                				{
                					int wfactor=model.getWidth()/columns;
                					if(rows<5)
                					{
                						factor=0.15*rows;
                					}
                					symwidth=convert(factor*wfactor);
                					int hfactor=model.getHeight()/rows;
                					symheight=convert(factor*hfactor);
                					
                				}
                				else{
                				symwidth = getReducedHeightorWidth(j,obj.getWidth(),obj1,"width");
                			 
                			 
                				symheight=getReducedHeightorWidth(j,obj.getHeight(),obj1,"height");
                				}
 	                     		
                				if(symwidth < symheight)
	                            symheight = symwidth;
                				else
	                            symwidth = symheight;
                				obj.setHeight(symheight);
 	                        	obj.setWidth(symheight);
                			 
 	                        	i++;
 	                        	k++;
            			
                		}  
                	 if(done)
                	  break;
                	    j++;	
                	}
                			
                			
                       
	                	
	         }
	     }
    

	private Vector processSymbolsForanchored(Vector<MapSymbolComponent> symbols) {
		// TODO Auto-generated method stub
		Vector<MapSymbolComponent> newsymbs = new Vector<MapSymbolComponent>();
		for(MapSymbolComponent symbol:symbols)
		{
			if(!symbol.anchored)
				newsymbs.add(symbol);
		}
		
		return newsymbs;
	}

	/*
	 * In this layout, we reduce the symbol size for each row and hence, based on row index, the reduction factor will be calculated in this method
	 */
	private int getReducedHeightorWidth(int row,int dim,MapSymbolComponent prev,String toget)
	{
		int dim1=dim;
		if(prev != null)
		{	
			if(toget.equals("width"))	
			dim1=prev.getWidth();
			else
				dim1=prev.getHeight();
		dim =convert(0.95*dim1);
		}
		return dim;
	}
	
	
    private void setLowerMostPoint(int vw,int vh)
    {
    	int y = 72*vh/100;
    	lastleft=new Point(convert(vw/15),y);
    	lastright=new Point(convert(85*vw/100),y);
    	
    }
	public void removeLayoutObject(MapSymbolComponent symb,
			MapContainerComponent parent, int depth) {
		// TODO Auto-generated method stub
		
	}

	public void updateLayoutObject(MapSymbolComponent symb,
			MapContainerComponent parent, int depth) {
		// TODO Auto-generated method stub
		
	}
	
	private int convert(double x)
	{
		Double db = new Double(x);
		return db.intValue();
		
	}
	
	private void drawLines(DefaultMapModel model)
	{
		
		int x1 = lastleft.x;
		int y1 = lastleft.y;
		int x2 = lastright.x;
		int y2 = lastright.y;
		
		double d = getDistance(x1,y1,x2,y2);
		double ref = model.getViewHeight();
		k=450;	
		//Length of altitude
		
		double alt = ref+k; 
		
		int xmid = (x2+x1)/2;
		int ymid = (y2+y1)/2;
		
		//Length of sides of triangle (Assume the triangle is isoceles)
		theta = Math.toDegrees(Math.atan(alt/(d/2)));
		double l = alt/(Math.sin(Math.toRadians(theta)));
		
		//Step 2: Draw horizontal lines
		drawHorizontalLines(l,model);
		
		//Step 3: Draw vertical lines
		drawVerticalLines(l,d,alt);
		
	}
	private void drawHorizontalLines(double l,DefaultMapModel model)
	{

		double k1 = (k)/(Math.sin(Math.toRadians(theta)));
		double l1 = l-k1;
		
		//We subtract 20 px so that the top most symbols have some gap above them to accomodate links 
		double D = (l1/(rows))-20;
		diff = D*(Math.sin(Math.toRadians(theta)));
		if(rows ==1)
		{
			l1=l1/2;
			D=l1;
			diff=(4*screenHeight/10);
		}
			
	}
		private void drawVerticalLines(double l,double d,double alt) 
		{
			
			double angle1 = 90.0;
		
			double baseo = d/2;
			int k1 = (columns-1)/2;
			double D =0.0;
			int x1 = lastleft.x;
			int y1 = lastleft.y;
			int x2= lastright.x;
			int y2=lastright.y;
			
			if(columns != 1)
			{	
			D = (angle1 -theta)/(columns-(k1+1));
			if(columns%2==0){
				k1=(columns)/2;
				D=(angle1 -theta)/(columns-(k1));
			}	
			boolean first=true;
			int sos=10;
			for(int i=0;i<=k1;i++)
			{
				
				double angle2 = theta +(i*D);
				
				if(columns%2==0)
				{	
					if(angle2==90.0)
					continue;
					//if(i!=0)
					angle2=angle2+(sos*D/100);
					sos=sos+7;
					
						
				}	
				double base = alt/(Math.tan(Math.toRadians(angle2)));
				
				int x01=convert(x1+(baseo-base));
				int y01=y1;
			
				int x02=convert(x01+(base*2));
				
				{	
				
			    findPoints(x01,y01,angle2,i);
			    int k2 = columns-(i+1);
			    findPoints(x02,y01,180-angle2,k2);
				}
				
			}
			}
			else{
				int xmid = (x2+x1)/2;
				int ymid = (y2+y1)/2;
				
				findPoints(xmid,ymid,90.0,0);
			}
		}
		
		private void findPoints(int x1,int y1,double theta,int i)
		{
			for(int j=0;j<=rows-1;j++)
			{
				double dist = (j)*diff;
				
				if(rows==1)
					dist=diff;
				double r = dist/(Math.sin(Math.toRadians(theta)));
				double x = x1+(r*Math.cos(Math.toRadians(theta)));
				double y = y1-(r*Math.sin(Math.toRadians(theta)));
				
				if(columns==1)
				{
					x=x1;
					y=y1-(j*dist);
				}	
				if(rows ==1)
				{
				
						y=(3*screenHeight/10);
				}	
				
				inter[j][i]=new Point(convert(x),convert(y));
				
			}
		}		
	
	
	private double getDistance(int x1,int y1,int x2,int y2)
	{
		return Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
		
	}

}
