/* $Id: ElementTree.java,v 1.2 2010/10/29 13:45:51 swaminathap Exp $ */
package com.adventnet.nms.util;

import java.io.*;
import java.util.*;
import org.w3c.dom.*;
import javax.servlet.jsp.JspWriter;

/**
 * Constructs a HTML tree for a given Element.
 * The attributes to be given for each Element node are 
 * <br>ID - a unique Id.
 * <br>NAME - display name.
 * <br>URL - the href attribute for the link from displayed name.
 * <br>TREE-HANDLER-HREF - the href attribute to the link from plus or minus image
 * in the tree. For example the value may be javascript:submitViaPost(IdForThatNode)
 * <br>ICON - the src attribute for the icon in front of the display name.
 * <br>TARGET - the target attribute for the link from the displayed name
 *
 * @author R.Rajkumar
 */
public class ElementTree {
 	
    /**
     * @param style "win","metal" or "motif" are the acceptable values,if other values are given "win" style is displayed
     */
    public ElementTree(String style)
    {
            setImageNames(style);        
    }
	/**
	 * This method forms a Tree with 
	 *  <br>&nbsp;- a node in selected state ; 
	 *  <br>&nbsp;- state is not maintained - got as a parameter (treeState );
	 *  <br>&nbsp;- the default html tag and attributes for table is given.
	 * 
	 * @param x The Object based on which the tree is constructed.
	 * @param pre The string to come before the tree.(Image tags may be used to and it comes along and passed recursively.
	 * @param out The JspWriter from the JSP Page.
	 * @param nodeToOpen The node that is to be opened or closed - state is to be toggled.
	 * @param treeState The Vector which has the names of the nodes already open.
	 * @param selected The node which is to selected.
	 * 
	 */
	public void constructTree(Element element,String pre, JspWriter out, String nodeToOpen,Vector treeState,String selected,boolean showRoot)throws IOException
	{
            out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" cols=\"1\">");
            if(showRoot)
                exploreNode(element,pre,out,nodeToOpen,treeState,selected,showRoot);
            else
                exploreNode(element,pre,out,nodeToOpen,treeState,selected);
            out.println("</table>");
	}

	public void exploreNode(Element element ,String pre, JspWriter out, String nodeToOpen,Vector treeState,String selected,boolean showroot)throws IOException
    {
        String menuTreeName=element.getAttribute("TREE-NAME");
        if(menuTreeName == null)
            {
                menuTreeName = element.getAttribute("ID");
            }
        String reqString = element.getAttribute("URL");
        if (reqString.indexOf(":") ==-1)
            reqString = "../"+reqString;
        String iconSource= element.getAttribute("ICON-FILE");
		iconSource = checkIfFileExists(iconSource);
        String target= element.getAttribute("TARGET");
        String icon="<IMG WIDTH=16 HEIGHT=16 align=TEXTTOP BORDER=0 SRC=\"../"+iconSource+"\" >";
        out.println("<tr><td nowrap><a HREF=\""+reqString+"\" TARGET=\""+target+"\" >"+icon+"&nbsp;&nbsp;"+NmsUtil.GetString(menuTreeName)+"</a></td></tr>");
        exploreNode(element,pre,out,nodeToOpen,treeState,selected);
    }


	/**
	 * Holds image names strings for the required tree-style.
	 */
	private String ell;
	private String ee;
	private String hline;
	private String vline;
	private String plus;
	private String minus;
	private String space;

	/**
	 * To set the tree style Windows/Metal/Motif are the available styles.
	 * 
	 * @param treeName The tree style name : "win","metal" or "motif" are the acceptable values,if other values are given "win" style is displayed.
	 */
	private void setImageNames(String treeName)
	{
		space="<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/space.png\" >";
		if (treeName.equals("metal"))
		{
			ell=space;
			ee=space;
			hline=space;
			vline=space;
			plus = "<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/metalPlus.png\" >";
			minus = "<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/metalMinus.png\" >";
		}
		else
		{
			if (treeName.equals("motif"))
			{
				ell="<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/motifL.png\" >";
				ee="<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/motifE.png\" >";
				hline="<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/motifHline.png\" >";
				vline="<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/motifVline.png\" >";			
				plus = "<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/motifPlus.png\" >";
				minus = "<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/motifMinus.png\" >";
			}
			else
			{
				ell="<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/l.png\" >";
				ee="<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/E.png\" >";
				hline="<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/hline.png\" >";
				vline="<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/vline.png\" >";			
				plus = "<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/plus.png\" >";
				minus = "<IMG BORDER=0 WIDTH=20 HEIGHT=20 align=TEXTTOP SRC=\"../images/minus.png\" >";
			}
		}
		return;
	}



	/**
	 * 
	 * This method forms a Tree with 
	 *  <br>&nbsp;- a node in selected state ; 
	 *  <br>&nbsp;- state is not maintained - got as a parameter (treeState );
	 *  <br>&nbsp;- the default html tag and attributes for table is NOT given - Only the row tags and their contents are given.
	 * 
	 * @param x The Object based on which the tree is constructed.
	 * @param pre The string to come before the tree.(Image tags may be used to and it comes along and passed recursively.
	 * @param out The JspWriter from the JSP Page.
	 * @param nodeToOpen The node that is to be opened or closed - state is to be toggled.
	 * @param treeState The Vector which has the names of the nodes already open.
	 * @param selected The node which is to selected.
	 * 
	 */
	public void exploreNode(Element element ,String pre, JspWriter out, String nodeToOpen,Vector treeState,String selected)throws IOException
	{
		String ellOrEe =ee;
		if(element==null)
		{
			System.err.println("ElementTree : Object is null omitting the same from tree view");
			return;
		}
		NodeList nodeList =element.getChildNodes();
		if(nodeList==null)
		{
			System.err.println("ElementTree : "+element+".getChildNodes() returns null omitting the same from tree view");
			return;
		}
		int size = nodeList.getLength();
		for( int i=0;i<size;i++)
		{

                    boolean hasFollowingNodes = CheckForFollowingNodes(nodeList,i);
                    if(!hasFollowingNodes)
                        {
                            ellOrEe = ell;
                        }
                
                //            if((size-1)==i)
                //{
				//ellOrEe = ell;
                //}
			Element child = (Element)nodeList.item(i);
			if(child==null)
			{
				System.err.println("ElementTree : Element is null omitting the same from tree view");
				continue;
			}
            String clientName = child.getAttribute("Client");
            if (clientName==null || clientName.equals(" ") || clientName.equals("null")||(clientName.length() < 1))
            {
                clientName="All";
            }

            if( clientName !=null )
            {
                if((clientName.equals("Java") )||(clientName.equals("NONE") ))
                {
                    continue;
                }
            }

           	String menuid=child.getAttribute("ID");
			if(menuid!=null)
			{
				menuid=java.net.URLEncoder.encode(menuid);
				String menuTreeName=child.getAttribute("TREE-NAME");
				String target= child.getAttribute("TARGET");
				String iconSource= child.getAttribute("ICON-FILE");
				iconSource = checkIfFileExists(iconSource);
                                String menuFileName= child.getAttribute("MENU-FILE-NAME");
				String enabled = child.getAttribute("isEnabled");
				String allowchildren = child.getAttribute("allowChildren");
				
				
                                if(menuid.equals(selected))
                                {
                                    menuTreeName = "<b><font color=blue>"+menuTreeName+"</font></b>";
                                }
                                //out.println("<hr><br>child.getAttribute(\"URL\") : "+ child.getAttribute("URL"));
                                StringBuffer buffer = new StringBuffer(child.getAttribute("URL"));
                                //out.println("<br><br> buffer : "+ buffer);
                                String reqString;
				
                                if(buffer == null || buffer.length()==0)
                                {
                                    buffer = new StringBuffer();
                                    buffer.append("<IMG WIDTH=16 HEIGHT=16 align=TEXTTOP BORDER=0 SRC=\"../");
                                    buffer.append(iconSource).append("\" >&nbsp;&nbsp;").append(NmsUtil.GetString(menuTreeName));
                                    reqString = buffer.toString(); 
                                }
                                else
                                {
                                    if(buffer.toString().indexOf(":") == -1)
                                    {
                                        buffer.insert(0,"../");
                                        if(buffer.toString().indexOf("?") == -1)
                                        {
                                            buffer.append("?");
                                        }
                                        else
                                        {
                                            buffer.append("&");
                                        }
                                        buffer.append("type=").append(menuid);
                                        buffer.append("&displayName=").append(java.net.URLEncoder.encode(menuTreeName));
                                        buffer.append("&menuFileName=").append(menuFileName);
									}	

									if(( enabled != null) && (enabled.equals("false")))
									{
										if( allowchildren != null )
										{

											if(allowchildren.trim().equals("false"))
											{						    
												buffer = new StringBuffer();
												buffer.append(plus).append(" <font color=black ><i> ");
											}
											else if(allowchildren.trim().equals(""))
											{
												buffer = new StringBuffer();
												buffer.append(" <font color=black ><i> ");
											}
										}
									}
									else
									{
										buffer.insert(0,"<a HREF=\"").append("\" TARGET=\"").append(target).append("\" >");
									}

                                    buffer.append("<IMG WIDTH=16 HEIGHT=16 align=TEXTTOP BORDER=0 SRC=\"../");
                                    buffer.append(iconSource).append("\" >&nbsp;&nbsp;").append(NmsUtil.GetString(menuTreeName));
									if(enabled != null && enabled.equals("false"))
									{
										buffer.append("</i></font>");
									}
									else
									{
										buffer.append("</a>");
									}
									reqString = buffer.toString(); 
								}
                                String treeHandlerString ="javascript:submitViaPost(\'"+menuid+"\',\'"+menuTreeName+"\')\"";
								String nImg =plus;
                                String haschilds = child.getAttribute("HASCHILDS");

                                if(haschilds==null || haschilds.equals("") ) 
                                    {
                                        haschilds= String.valueOf(child.hasChildNodes());
                                    }

                                if ((clientName!=null)&&!haschilds.equals("false"))
                                {
                                
                                    if (child.hasChildNodes())
                                    {
                                        haschilds="false";
                                        NodeList nodeLst =child.getChildNodes();
                                        int childsize = nodeLst.getLength();
                                        for(int j=0;j<childsize;j++)
                                        {
                                            Element children = (Element)nodeLst.item(j);
                                            if(children==null)
                                            {
                                                continue;
                                            }
                                            String show=(String)children.getAttribute("Client");
                                            
                                            if (show == null || show.equals("All")||show.equals("HTML")|| show.equals(" ") || show.equals("null")||(show.length() < 1))
                                            {
                                                haschilds="true";
                                                break;
                                            }
                                        }
                                    }
                                    
                                }

                                if(haschilds.equals("false")) 
                                {
                                    nImg = hline;
                                    out.println("<tr><td nowrap>"+pre+ellOrEe+nImg+reqString+"</td></tr>");
                                }
                                
                                else
                                {

					if (treeState.contains(menuid))
					{
						if(menuid.equals(nodeToOpen))
						{
							nImg = plus;
						}
						else
						{
							nImg = minus;
						}
					}
					else
					{
						if(menuid.equals(nodeToOpen))
						{
							nImg = minus;
						}
						else
						{
							nImg = plus;
						}
					}
                                        out.println("<tr><td nowrap>"+pre+ellOrEe+"<a name=\""+menuTreeName+"\" HREF=\""+treeHandlerString+"\" >" +nImg+"</a>"+reqString+"</td></tr>"); 
                                }
				if(!menuid.equals(nodeToOpen))
				{
					if(treeState.contains(menuid))
					{
						if(hasFollowingNodes)
						{
							exploreNode(child,pre+vline,out,nodeToOpen,treeState,selected);
						}
						else
						{
							exploreNode(child,pre+space,out,nodeToOpen,treeState,selected);
						}
					}
				}
				else
				{
					if(!treeState.contains(menuid))
					{
						if(hasFollowingNodes)
						{
							exploreNode(child,pre+vline,out,nodeToOpen,treeState,selected);
						}
						else
						{
							exploreNode(child,pre+space,out,nodeToOpen,treeState,selected);
						}
						treeState.add(menuid);
					}
					else
					{
						treeState.remove(menuid);
					}
				}
			}
			else
			{
				System.err.println ("ElementTree : Id is null for Object - omitting the same from tree view");
			}
		}
	}
    private boolean CheckForFollowingNodes(NodeList v,int i)
    {
       
        int size = v.getLength();
        for(int j=i+1;j<size;j++)
        {
         
   			Element child = (Element)v.item(j);

            if(child==null)
            {
				continue;
			}
            String show=(String)child.getAttribute("Client");
            
            if (show == null || show.equals("All")||show.equals("HTML")|| show.equals(" ") || show.equals("null")||(show.length() < 1))
                return true;
        }
                return false;
    }

	private String checkIfFileExists(String iconSource)
	{
		if(iconSource == null || iconSource.trim().equals(""))
		{
			iconSource = "images/redDot.png";
			return iconSource;
		}
		String iconFileName = com.adventnet.nms.util.PureUtils.rootDir + iconSource;
		File iconFile = new File(iconFileName);
		if(iconFile.isFile())
		{
			boolean flag = iconFile.exists();
			if(!flag)
			{
				iconSource = "images/redDot.png";
			}
		}
		else
		{
			iconSource = "images/redDot.png";
		}
		return iconSource;
	}
}
