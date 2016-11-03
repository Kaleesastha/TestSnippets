//$Id: CustomizerTreeModel.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.CustomView;
import com.adventnet.nms.util.XMLDataReader;
import com.adventnet.nms.util.XMLNode;
import javax.swing.tree.*;
import java.io.File;
import java.util.*;
import java.rmi.*;
import com.adventnet.nms.fe.common.NmsTreeAPI;

public class CustomizerTreeModel
{
	
	File f=null;
	DefaultTreeModel dtm=null;
	Hashtable treeVsImage=null;
	Hashtable treeVsId=null;
	NmsTreeAPI api=null;
	public DefaultTreeModel getModel(String user)
	{
		XMLNode root=null;
		try
		{
			api=(NmsTreeAPI)Naming.lookup("//localhost/TreeAPI");
			root=api.getTree(user,null);
		}
		catch(Exception e)
		{
			System.out.println("Exception while getting current tree using treeAPI");
			e.printStackTrace();
		}
		
		if(root!=null)
		{
		//A hashtable with tree name and its corresponding icon is created and it is sent to TreeCombo
		treeVsImage=new Hashtable();
		treeVsId=new Hashtable();
		String node=(String)root.getAttribute("TREE-NAME");
		String image=(String)root.getAttribute("ICON-FILE");
		String id=(String)root.getAttribute("ID");
		treeVsImage.put(node,image);
		treeVsId.put(node,id);
		
		Vector childs=root.getChildNodes();
		DefaultMutableTreeNode top=new DefaultMutableTreeNode(node);
		for(int i=0;i<childs.size();i++)
		{
			XMLNode child=(XMLNode) childs.elementAt(i);
			if(child!=null&&child.getNodeType()==XMLNode.ELEMENT)
			{
				treeVsImage.put(child.getAttribute("TREE-NAME").toString(),child.getAttribute("ICON-FILE").toString());
				treeVsId.put(child.getAttribute("TREE-NAME").toString(),child.getAttribute("ID").toString());
				addnode(top,child,child.getAttribute("TREE-NAME").toString());
			}
		}
		dtm=new DefaultTreeModel(top);
		}	
		return dtm;
	}	
	public Hashtable getTreeVsImage()
	{
		return treeVsImage;
	}
	public Hashtable getTreeVsId()
	{
		return treeVsId;
	}
	public String getParentId(String parentName)
	{
		if(treeVsId.containsKey(parentName))
		{
			return treeVsId.get(parentName).toString();
		}
		else
		{
			return null;
		}
	}

	public void addnode(DefaultMutableTreeNode top,XMLNode xmlnode,String chi)
	{

		DefaultMutableTreeNode dmtn=new DefaultMutableTreeNode(chi);
		top.add(dmtn);
		Vector subchilds=xmlnode.getChildNodes();
		if(subchilds.size()>0)
		{
			for(int i=0;i<subchilds.size();i++)
			{
				XMLNode subchild=(XMLNode)subchilds.elementAt(i);
				if(subchild!=null&&subchild.getNodeType()==XMLNode.ELEMENT)
				{
					treeVsImage.put(subchild.getAttribute("TREE-NAME").toString(),subchild.getAttribute("ICON-FILE").toString());
					treeVsId.put(subchild.getAttribute("TREE-NAME").toString(),subchild.getAttribute("ID").toString());
					addnode(dmtn,subchild,subchild.getAttribute("TREE-NAME").toString());
				}
			}
		}
	}
}



