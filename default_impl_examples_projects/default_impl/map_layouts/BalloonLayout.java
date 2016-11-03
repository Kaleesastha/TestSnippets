//$Id: BalloonLayout.java,v 1.1.2.4 2013/09/06 14:56:47 nishanthini.k Exp $

package com.adventnet.nms.mapui;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.adventnet.nms.util.NmsClientUtil;

/*
 * The balloon layout can be ideally used to depict topologies where a tree relationship is present. i.e., a set of symbols are connected to a central
 * symbol and the second level symbols inturn have other symbols connected to them and so on. The concept here is to place symbols in the same level in the 
 * circumference of a circle. Radii of each of these circles will be optimized to minimize edge(link) crossings as much as possible.
 * 
 */


public class BalloonLayout implements AutoLayout {


	
	
	// Root node is the centre-most node from where all the other nodes will be laid out.A root node can defined as part of maps.conf/map filter or it will determined by the layout
	MapSymbolComponent rootnode=null;
	
	//Edge length will be the space allocated for a particular symbol.The symbol's height and width will be set to this value.In case, additional space is desired for a symbol,this value has to be optimized.
	int edgelength=40;
	
	//Gap value will be additional space between two symbols.
	int gap=20;
	
	//This is the maximum angle upto which the branches of a parent node can span.
	double range = 270;
	
	/*
	 * This factor has been incorporated to minimize overlapping of nodes. This factor is finally incorporated in the calculation of radius of
	 * a branch node. Overlapping can be minimized by tweaking this variable. 
	 */
	
	double radiusfactor=2.0;
	
	//There may be cases where there are more than one rootnode and in such cases this vector used for holding the determined root nodes.
	Vector rootnodes = new Vector();

	
	//This is the factor by which radius of a heavier node differs from a lighter node.This can be tweaked to avoid overlapping between nodes
	
	//double radiusfactor = 0;
		
	/*
	 * Note:The following parameters are meant for internal use by the layout. Users are advised against manipulating/modifying the same
	 */
	
	//This hashtable maintains the mapsymbolName vs their respective properties specific to this layout
	Hashtable nodes = new Hashtable();
	
	//Vector holding the symbols which do not have links
	Vector<String> nolinks = new Vector<String>();
	
	//Boolean to indicate that a rootnode has been defined.
	boolean rootdefined = false;
	
	//offsetx and offsety are the values by which the rootnode will be shifted to avoid overlapping of symbols into negative quadrants.
	public static int offsetx=0;
	public static int offsety=0;
	
	boolean firstiteration=true;
	
	//Indicates the radius of the rootnode
	double rootnoderadius =0;
	
	//Indicates the radius of leaves( nodes which do not branch further).
		double leafradius = 70;
		
		int maxlevel=0;
	
	public void layoutMap(MapContainerComponent mc, int depth) {
	
		Vector symbols = mc.getSymbols();
		nolinks.clear();
		rootnodes.clear();
		rootnode=null;
		offsetx=0;
		offsety=0;
		nodes.clear();
		boolean isActualIconSize = false;
		DefaultMapModel model=(DefaultMapModel)MapClientInitializer.cacheMapTable.get(mc.getMapName());
		
		if(model==null)return;
		//model.setHeight(1200);
		Properties p = mc.getCurrentTopologyProperties();
		setMapTopologyProps(p);
		String rootname = "";
		if(p.get("rootnode")!= null)
		{
		 rootdefined = true;
		 rootname=p.get("rootnode").toString();
		 
		 }
		String iconSize  = (String)p.get("isActualIconSize");
        if( (iconSize != null) && (iconSize.trim().equals("true")))
        {	  	
            isActualIconSize = true;
        }
		int numSymbols = symbols.size();
		
	
		
		for (int i=0;i<=numSymbols-1;i++)
		{
			 MapSymbolComponent symb = (MapSymbolComponent) symbols.elementAt(i);

			if(rootdefined)
			{
				setDefinedRoot(symb.getName(),rootname,symb);
			}
			if (isActualIconSize)
		        {
		            try
		            {
		             
		                String myIconName=symb.getIconName();
		                ImageIcon myIcon=(NmsClientUtil.getImageIcon(NmsClientUtil.applet.getDocumentBase()+"../images/" + myIconName));
		                
		                symb.dim.width =(int)(myIcon.getIconWidth()*model.zoomRatio);
		                symb.dim.height =(int)(myIcon.getIconHeight()*model.zoomRatio); 
		                edgelength = symb.dim.width;
		                if(symb.dim.height<edgelength)
		                	edgelength = symb.dim.height;
		                	
		            }
		            catch(Exception e)
		            {
		                symb.dim.width =edgelength; // Assigning default width and height
		                symb.dim.height =edgelength;
		               
		            }
		        }
			 else
			 {
				 symb.dim.width=convert(edgelength*model.zoomRatio);
				 symb.dim.height=(convert(edgelength*model.zoomRatio));
				 
			 }
			 setNodeProps(symb);		
		}
		if(numSymbols>0)
		{	
		if(!rootdefined)	
		setRootNode();
		if(rootnode==null)
			rootnode = (MapSymbolComponent) symbols.elementAt(0);
		setRootNodeRadius(model);
		setLeafRadius();
		setNodeLevels(rootnode,new Vector());
		if(!rootnode.getAnchored())
		rootnode.setP(new Point(convert(2*model.getWindowWidth()/10),convert(2*model.getWindowHeight()/10)));
		setNodesLocation(rootnode,0,new Vector(),model,isActualIconSize,true);
		}
	}

	private void setDefinedRoot(String symbname,String rootname, MapSymbolComponent symbol) {
		
		if(rootname.contains(","))
		{	
			StringTokenizer st = new StringTokenizer(rootname,",");
			while(st.hasMoreTokens())
			{
			String name = st.nextToken();
			int index = symbname.indexOf("\t");
			symbname = symbname.substring(0,index);
			if(symbname.equals(name))
			{
				rootnodes.add(symbol);
				break;
			}
			}
		}
		
		else{
			int index = symbname.indexOf("\t");
			if(index!=-1)
			{	
			symbname = symbname.substring(0,index);
			}
			if(symbname.equals(rootname))
			rootnode = symbol;	
		}
		
	}
	
	

	private void setLeafRadius() {
		
		leafradius = (1.2)*(edgelength+gap);
		
	}

	private void setNodeProps(MapSymbolComponent symb)
	{
		int links = symb.getConnectedLinks().size();
		 Properties props = new Properties();
		 props.put("level",0);
		 props.put("MapSymbolComponent",symb);
		 props.put("isLeaf",false);
		 props.put("weight",links-1);
		 if(links==1)
			 props.put("isLeaf",true);
		 else
			 props.put("isLeaf",false);
		 props.put("depth",getNodeDepth(symb,new Vector()));
		 
		 //System.out.println("depth is        "+symb.getName()+"::::"+props.get("depth"));
		 nodes.put(symb.getName(),props);
		 if(links==0 && !nolinks.contains(symb.getName())&&!symb.getAnchored() )
			 nolinks.add(symb.getName());
		 if(symb.getAnchored())
			 symb.setP(symb.getP());
	}
	private void setNodesLocation(MapSymbolComponent centre,double initial,Vector checkedge,DefaultMapModel model,boolean isActualIconSize,boolean adjust)
	{
			
		Vector<MapLinkComponent> edges = sortNodesAroundCentre(centre);
		
		double limit = edges.size();
		double diff =0;
		
		 if(centre==rootnode){
			 if(limit==0)
				 limit--;
			 limit = limit+(nolinks.size());
			 diff=360/limit;
			 
			 }
		
		 else{
			 double ang = ((limit-1)*360*(edgelength+gap))/(3.14*2*leafradius);
			 initial=ang;
			 diff=(ang/(limit-1));
		 }
		
		 int i=0;
		 for(i=0;i<=edges.size()-1;i++)
		 {
			 MapLinkComponent edge = edges.elementAt(i);
			 if(checkedge.contains(edge))
				 continue;
			 checkedge.add(edge);
			
			double theta = initial-(i*diff);
		   	
			 if(centre==rootnode)
				 theta = (i*diff);
			
			 MapSymbolComponent node = verifyNode(edge,centre,false);
			 if(node==null)
				 continue;
			 boolean isLeaf= Boolean.parseBoolean((getNodeProperty("isLeaf",node)).toString());
			 double radius = 0;
			
			if(isLeaf){
				radius = leafradius*model.zoomRatio;
				if(centre==rootnode)
					radius = rootnoderadius*model.zoomRatio;
			}
			else
				 radius=getNodeRadius(node,diff,model,isActualIconSize);
		
			setNodeProperty("radius",radius,node);
			 
			if(node.getAnchored())
				node.setP(new Point(node.p.x,node.p.y));
			else
			node.setP(computeNodeLocation(radius,theta,centre,model));
			
			if(!isLeaf)
			setNodesLocation(node,theta,checkedge,model,isActualIconSize,adjust);
			
		
		 }
		 if((i>edges.size()-1 ||edges.size()==0) && centre==rootnode)
		 {
			
			 arrangenoLinkNodes(diff,model);
			 if(adjust)
			 {	 
		 	 adjust=false;
				 adjustUsingOffset(model,isActualIconSize);
			 }
			 
		 }	 
	 
	}
private Vector sortNodesAroundCentre(MapSymbolComponent centre)
{
	Vector<MapLinkComponent> edges = centre.getConnectedLinks();
	Hashtable nodeVsWt = new Hashtable();
	ArrayList arr = new ArrayList(edges.size());
	for(int i=0;i<=edges.size()-1;i++)
	{	
	MapSymbolComponent node = verifyNode(edges.elementAt(i),centre,false);
	if(node==null)
		continue;
	double wt = Double.parseDouble(getNodeProperty("weight",node).toString());
	arr.add(wt);
	nodeVsWt.put(edges.elementAt(i),wt);
	}
	Collections.sort(arr);
	edges.clear();
	for(int i=0;i<=arr.size()-1;i++)
	{
		double obj = Double.parseDouble(arr.get(i).toString());
		for(Enumeration en = nodeVsWt.keys();en.hasMoreElements();)
		{
			MapLinkComponent edge = (MapLinkComponent) en.nextElement();
			double wt1 =Double.parseDouble(nodeVsWt.get(edge).toString());
			if(obj==wt1 && !edges.contains(edge))
			{	
				edges.add(edge);
				break;
			}	
		}
	}
	return arrangeEdges(edges);
	
}
private Vector arrangeEdges(Vector<MapLinkComponent> edges)
{
	Vector<MapLinkComponent> edges1 = (Vector<MapLinkComponent>) edges.clone();
	int size = edges1.size();
	int stop = size/2;
	//edges.clear();
	if(size%2!=0)
		stop=(size+1)/2;
	int k=0;
	for(int i=0;i<=stop-1;i++)
	{
		int start = i;
		int end = size-(i+1);
		
		edges.setElementAt(edges1.elementAt(end-k),start);
		if((size%2!=0 && i==stop)||(start==end))
			continue;
		edges.setElementAt(edges1.elementAt(end-(k+1)),end);
		k++;
	}
	return edges;
}
private void adjustUsingOffset(DefaultMapModel model, boolean isActualIconSize) {
		
	Point p = rootnode.p;
	
		if(offsetx>0||offsety>0)
		{
			if(!rootnode.getAnchored())
			rootnode.setP(new Point(p.x+offsetx+edgelength+gap,p.y+offsety+edgelength+gap));
			setNodesLocation(rootnode,0,new Vector(),model,isActualIconSize,false);
		}
	
			
	}

private Point computeNodeLocation(double radius,double theta,MapSymbolComponent centre,DefaultMapModel model)
{
	int x = convert((centre.p.x)+(radius*Math.cos(Math.toRadians(theta))));
 	
	 int y=  convert((centre.p.y)-(radius*Math.sin(Math.toRadians(theta))));
	 
		if(x<0&&Math.abs(x)>offsetx)
		offsetx = Math.abs(x);
		
		if(y<0&&Math.abs(y)>offsety)
			offsety=Math.abs(y);
	return new Point(convert(x),convert(y));
}

private void arrangenoLinkNodes(double diff,DefaultMapModel model)
{
	int k = rootnode.getConnectedLinks().size();
	double theta=0;
	int limit =nolinks.size();
	double radius= rootnoderadius;
	setNodeProperty("radius",radius,rootnode);
	boolean hasroot=false;
	for(Enumeration en = nolinks.elements();en.hasMoreElements();)
	{	
		String name = (String) en.nextElement();
		if(rootnode.getName().contains(name))
		{
		hasroot=true;
		break;
		}
	}
	for(int i=0;i<=limit-1;i++)
	{
		String name = nolinks.elementAt(i);
		Properties props = (Properties) nodes.get(name);
		MapSymbolComponent symb = (MapSymbolComponent) props.get("MapSymbolComponent");
		if(symb==rootnode){
		
			continue;
		}
		theta = (k+i)*diff;
		if(symb.getAnchored()){
			symb.setP(new Point(symb.p.x,symb.p.y));
		continue;
		}
		 symb.setP(computeNodeLocation(radius, theta,rootnode, model));
	
		 }
	
	
}
private void setRootNodeRadius(DefaultMapModel model)
{
	double wt =rootnode.getConnectedLinks().size()+nolinks.size();
	
	double radius= ((edgelength+gap)*model.zoomRatio*(wt))/(3.14*2);
	rootnoderadius = radius;
	 setNodeProperty("radius",rootnoderadius,rootnode);
	 
}
private double getNodeRadius(MapSymbolComponent node,double theta,DefaultMapModel model, boolean isActualIconSize) {
		
	    
	
	    double radius=0;
	    double wt = 0;
	 
	    if(node==rootnode){return rootnoderadius*model.zoomRatio;
	    }
	    wt = computeTotalWeight(node,0,node,new Vector());
	    
	    if(theta==0)
	    	theta=range;
	   
	    radius =((edgelength+gap)*model.zoomRatio*(wt)*360)/(3.14*2*range);
	     
	    	if(maxlevel<=1){
	    		radiusfactor=3.0;
	    		
	    	}
			  
	    	radius = (radius*radiusfactor)+((rootnoderadius)*model.zoomRatio);
		    	
	    if((getNodeProperty("level",node)!=null && Integer.parseInt(getNodeProperty("level",node).toString())==1) && radius<rootnoderadius*model.zoomRatio)
	    {
	    	double diff = rootnoderadius*model.zoomRatio-radius;
	    	if(diff<=edgelength+gap)
	    	radius =1.1*(radius+(rootnoderadius*model.zoomRatio));
	    }	
	    setNodeProperty("radius",radius,node);
	    return radius;
	    
	    
	}

	private int convert(double x)
	{
	Double db = new Double(x);
	return db.intValue();
	
	}

	private void setNodeLevels(MapSymbolComponent parent,Vector checkedge)
	{
		Vector<MapLinkComponent> edges = parent.getConnectedLinks();
		
		for(int i=0;i<=edges.size()-1;i++)
		{
			MapLinkComponent ed = edges.elementAt(i);
			if(checkedge.contains(ed))
				continue;
			checkedge.add(ed);
			
			MapSymbolComponent node = verifyNode(ed,parent,false);
			
			if(node==null)
				continue;
			int level=0;
			if(getNodeProperty("level",parent)!=null)
				level = Integer.parseInt((getNodeProperty("level",parent)).toString());
			setNodeProperty("level",level+1,node);
			if(level>maxlevel)
				maxlevel=level;
	
			setNodeLevels(node,checkedge);
		}
		
		
	}
	
	private Object getNodeProperty(String key, MapSymbolComponent node)
	{
		Properties props = (Properties) nodes.get(node.getName());
		if(props!=null && props.containsKey(key))
			return props.get(key);
		return null;
	}
	
	private void setNodeProperty(String key,Object val, MapSymbolComponent node)
	{
		Properties props = (Properties) nodes.get(node.getName());
		props.put(key, val);
	}
	private void setRootNode()
	{
		int leastdepth=1000;
		
		for(Enumeration en = nodes.keys();en.hasMoreElements();)
		{
			String symbname =(String) en.nextElement();
			if(symbname.equals("L2_MapSymbol 1")||symbname.equals("L2_MapSymbol 12"))
			{
				//System.out.println();
			}
			Properties props = (Properties) nodes.get(symbname);
			int depth = Integer.parseInt(props.get("depth").toString());
			MapSymbolComponent node = (MapSymbolComponent) props.get("MapSymbolComponent");	
			boolean isLeaf = Boolean.parseBoolean(props.get("isLeaf").toString());
			if(depth<=leastdepth&&!isLeaf&&depth!=-1)
			{	
				if(depth<leastdepth)
				{
					rootnode=node;
					rootnodes.clear();
					rootnodes.add(node);
				}
				
				if(depth==leastdepth)
				{
					Vector<MapLinkComponent> links = node.getConnectedLinks();
					//if(links.size()>rootnode.getConnectedLinks().size())
						
					boolean multipleroot = true;
					for(int i=0;i<=links.size()-1;i++)
					{
						MapLinkComponent link = links.elementAt(i);
						//If node in question is already connected to a rootnode then it cannot be considered a root node
						if(rootnodes.contains(link.getSourceObj())||rootnodes.contains(link.getDestObj()))
						{
							if(links.size()>rootnode.linksConnected.size())
								rootnode=node;
							multipleroot=false;
							break;
						}	
					}
					if(multipleroot)
					{	
					rootnode=null;
					rootnodes.add(node);
					}
				}
				leastdepth=depth;
				
			}	
		}
		if(rootnode==null)
		
		setDummyRoot();
		
		if(rootnode!=null){
		setNodeProperty("radius",rootnoderadius,rootnode);
		setNodeProperty("MapSymbolComponent",rootnode,rootnode);
		}
		
	}
	
	private void setDummyRoot()
	{
		MapSymbolComponent dummy = new MapSymbolComponent();
		dummy.setName("Imaginaryroot");
		for(int i=0;i<=rootnodes.size()-1;i++)
		{
			MapSymbolComponent symbol = (MapSymbolComponent) rootnodes.elementAt(i);
			MapLinkComponent link = new MapLinkComponent();
			link.setSourceObj(dummy);
			link.setDestObj(symbol);
			link.setName("Imaginaryroot"+"& "+i);
		}
		Properties props = new Properties();
		props.put("weight",rootnodes.size());
		props.put("links",dummy.linksConnected);
		props.put("isLeaf", false);
		props.put("level",0);
		props.put("depth",getNodeDepth(dummy,new Vector()));
		nodes.put(dummy.getName(),props);
		rootnode = dummy;
	}
	
	
	private int computeDepth(MapSymbolComponent subnode, MapSymbolComponent node,Vector checkedge, int depth)
	{
		
		if(node.getConnectedLinks().size()==0)
		{
			return -1;
		}
		// We need not compute the depth for leaf nodes
		if(node.getConnectedLinks().size()==1||subnode.getConnectedLinks().size()==0)
		{
	
			return depth;
		}
				
		Vector<MapLinkComponent> edges = subnode.getConnectedLinks();
		
		if(edges.size()>0)
		{
		boolean incremented=false;
		for(int j=0;j<=edges.size()-1;j++)
		{
			MapLinkComponent edge1 = edges.elementAt(j);
			if(checkedge.contains(edge1))
				continue;
				
			checkedge.add(edge1);
			MapSymbolComponent nod = verifyNode(edge1,subnode,false);
			if(!incremented)
			{	
				depth++;
				incremented=true;
			}
			if(nod==null||nod.getConnectedLinks().size()==1)
				continue;
			
			if(node==subnode)
			{
				int depth1 = computeDepth(nod,node,checkedge,1);
				if(depth1>depth)
					depth=depth1;
			}
			else
			depth= computeDepth(nod,node,checkedge,depth);
			
			
			
				 
		}
		
		}
		return depth;
		
	}
	
	private int computeTotalWeight(MapSymbolComponent node,int weight,MapSymbolComponent subnode,Vector tocheck)
	{
		boolean isLeaf1= Boolean.parseBoolean((getNodeProperty("isLeaf",node)).toString());
		boolean isLeaf2= Boolean.parseBoolean((getNodeProperty("isLeaf",subnode)).toString());
		if(isLeaf1||isLeaf2)
			return weight;
			
		
		Vector<MapLinkComponent> edges = subnode.getConnectedLinks();
		for(int i=0;i<=edges.size()-1;i++)
		{
			MapLinkComponent edge = edges.elementAt(i);
			if(tocheck.contains(edge))
				continue;
			tocheck.add(edge);
			
			MapSymbolComponent nod = verifyNode(edge, subnode,true);
			if(nod == null){
				continue;
			}	
			
			weight++;
			
							
		weight=computeTotalWeight(node,weight,nod,tocheck);
				
		}
		
		return weight;
	}
	private int getNodeDepth(MapSymbolComponent node,Vector checkedge)
	{
		int depth=0;
		boolean incremented=false;
		Vector<MapLinkComponent> edges=node.getConnectedLinks();
		if(edges.size()==0)
			return -1;
		Vector branches = new Vector();
		for(int i=0;i<=edges.size()-1;i++)
		{	
			MapLinkComponent link = edges.elementAt(i);
			if(checkedge.contains(link))
				continue;
			checkedge.add(link);
		if(!incremented)
		{
			depth++;
			incremented=true;
		}
	
		MapSymbolComponent nod = verifyNode(link,node,false);
		 if(nod!=null)
		 {
			 if(nod.getConnectedLinks().size()==1)
			 {
				 continue;
				
				 // int subnodedepth=getNodeDepth(node,checkedge);
				 //depths.add(subnodedepth);
			 }
			 branches.add(nod);
			 
		 }
		}
		if(branches.size()>0)
		depth=depth+getForBranch(branches,checkedge);
		return depth;
	}
	private int getForBranch(Vector<MapSymbolComponent> branches,Vector checkedge)
	{
		int depth=0;
		if(branches.size()==1)
			return getNodeDepth(branches.elementAt(0),checkedge);
		else{
			
			for(int i=0;i<=branches.size()-1;i++)
			{
				MapSymbolComponent branch = branches.elementAt(i);
				int branchdepth= getNodeDepth(branch,checkedge);
				if(branchdepth>depth)
					depth=branchdepth;
			}
		
		}
		return depth;
	}
	private MapSymbolComponent verifyNode(MapLinkComponent edge, MapSymbolComponent subnode,boolean flag)
	{
		MapSymbolComponent nod = edge.getSourceObj();
		if(nod == subnode)
		{
			nod = edge.getDestObj();
		}
		if(nod == null)
			return null;
		Properties nodeprops = (Properties) nodes.get(nod.getName());
		Properties subnodeprops = (Properties) nodes.get(subnode.getName());
		if(flag)
		{	
			if(nodeprops.containsKey("level") && subnodeprops.containsKey("level"))
			{	
				int nodelevel = Integer.parseInt(nodeprops.get("level").toString());
				int subnodelevel = Integer.parseInt(subnodeprops.get("level").toString());
				if((subnode!=rootnode && nodelevel<subnodelevel))
			return null;
			}
		}	
		return nod;
	}
	private void setMapTopologyProps(Properties p)
	{
		Object gp = p.get("gap");
		if(gp!=null)
		gap=Integer.parseInt(gp.toString());
		Object edglength = p.get("edgelength");
		if(edglength!=null)
			edgelength = Integer.parseInt(edglength.toString());
		Object rge = p.get("range");
		if(rge!=null)
			range = Double.parseDouble(rge.toString());
		Object rootnde = p.get("rootnode");
		
			
	}
	
	public void addLayoutObject(MapSymbolComponent symb,
			MapContainerComponent parent, int depth) {
	
	}

	
	public void removeLayoutObject(MapSymbolComponent symb,
			MapContainerComponent parent, int depth) {
	
	}

	
	public void updateLayoutObject(MapSymbolComponent symb,
			MapContainerComponent parent, int depth) {
	
	}

	
	public String getName() {
		
		return "balloon";
	}

	}
