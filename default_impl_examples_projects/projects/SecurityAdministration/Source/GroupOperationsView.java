//$Id: GroupOperationsView.java,v 1.1 2006/08/29 13:57:02 build Exp $
 package com.adventnet.security.ui ; 

 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.tree.*;
 import javax.swing.event.*;
 import java.util.*;
 import com.adventnet.nms.util.CommonBuilderUIInterface;

 public class GroupOperationsView extends JWindow
 {
      JPanel base = null;	
      JPanel north = null;	
      JScrollPane scrollpane = null;
      JTree tree = null;
      JLabel close = null;	
     public GroupOperationsView(DefaultTreeModel model, Hashtable opers, String gname, Window owner)
	 {
         super(owner);

		/*
		* customizing tree root handles.
 		*/
		
           Object previousExpanded = UIManager.put("Tree.expandedIcon",AuthMain.getBuilderUiIfInstance().getImage("expand3.png"));
           Object previousCollapsed = UIManager.put("Tree.collapsedIcon", AuthMain.getBuilderUiIfInstance().getImage("collapse3.png"));
           tree = new JTree();
           UIManager.put("Tree.expandedIcon", previousExpanded);
           UIManager.put("Tree.collapsedIcon", previousCollapsed);
           tree.putClientProperty("JTree.lineStyle", "Angled");			
	tree.setRootVisible(false);
	tree.setModel(model);
	tree.setCellRenderer(new CustomTreeRenderer());			
	scrollpane = new JScrollPane(tree);
	scrollpane.setBackground(Color.white);
	//scrollpane.setBorder(new javax.swing.border.TitledBorder("Permissions for  group : "+ gname));	
	base = new JPanel();
	base.setBackground(Color.white);
	base.setLayout(new BorderLayout());
	north = new JPanel();
	north.setBackground(new Color(27,117,147));	
	close = new JLabel();
	close.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));	
	close.setFont(new Font("Dialog",Font.BOLD, 9));
	close.setIcon(AuthMain.getBuilderUiIfInstance().getImage("white_cross.png"));	
	north.setLayout(new FlowLayout(FlowLayout.RIGHT));
	north.add(close);
	base.add(north, BorderLayout.NORTH);	
	base.add(scrollpane, BorderLayout.CENTER);	
	base.setBorder(new javax.swing.border.LineBorder(Color.black,3));	
	setContentPane(base);
	
	for(int i=0;i<tree.getRowCount();i++)	
		{
	tree.expandRow(i);
		}	
	
		
	close.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent me)
				{
					dispose();
				}
			});


	 }

public void setBorderTitle(String title)
{
	scrollpane.setBorder(new javax.swing.border.TitledBorder(title));
}	
	
Hashtable viewoperations = null;
DefaultTreeModel tempModel = null;	

public void renderTree(Hashtable opers)
	{
		viewoperations = opers;
		tempModel = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getModel().getRoot();
  		constructViewOperationHash( node );	
		renderOpTree( node);
		tree.repaint();
	}
	
	
public void constructViewOperationHash(DefaultMutableTreeNode parentnode)
    {
        DefaultMutableTreeNode parent = parentnode;
        DefaultMutableTreeNode child = null;
        for(int i = 0; i < parent.getChildCount(); i++)
          {
            child = (DefaultMutableTreeNode)tempModel.getChild(parent, i);
            //String userob = ((TreeObject)child.getUserObject()).toString();
 	String userob = ((TreeObject)child.getUserObject()).getString();
            if(viewoperations.containsKey(userob) && viewoperations.get(userob).equals("1") && !child.isLeaf())
             {
                for(int j = 0; j < child.getChildCount(); j++)
                {
                    DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child, j);
                    //String userob1 = ((TreeObject)childofchild.getUserObject()).toString();
		String userob1 = ((TreeObject)childofchild.getUserObject()).getString();
		if(viewoperations.containsKey(userob1) && viewoperations.get(userob1).equals("0"))
			{
		                    viewoperations.put(userob1, "0");
			}
		else
			{
		                    viewoperations.put(userob1, "1");
			}
                }
            }
            constructViewOperationHash(child);
          }
    }

/*	
 public void renderOpTree(DefaultMutableTreeNode parentnode)
    {
        DefaultMutableTreeNode parent = parentnode;
        DefaultMutableTreeNode child = null;
        for(int i = 0; i < parent.getChildCount(); i++)
        {
            child = (DefaultMutableTreeNode)tempModel.getChild(parent, i);
            String userob = ((TreeObject)child.getUserObject()).toString();
            if(viewoperations.containsKey(userob))
            {
                if(viewoperations.get(userob).equals("1"))
                {
                    ((TreeObject)child.getUserObject()).setIncluded(true);
                    if(!child.isLeaf())
                    {
                        for(int j = 0; j < child.getChildCount(); j++)
                        {
                            DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child, j);
                            ((TreeObject)childofchild.getUserObject()).setSelected(true);
                            String userob1 = ((TreeObject)childofchild.getUserObject()).toString();
                            ((TreeObject)childofchild.getUserObject()).setIncluded(true);
                            if(viewoperations.containsKey(userob1))
                            {
                                if(viewoperations.get(userob).equals("0"))
                                    ((TreeObject)childofchild.getUserObject()).setIncluded(false);
                            }
                            else
                            {
                                viewoperations.put(userob1, "1");
                            }
                        }

                    }
                }
                else
                if(viewoperations.get(userob).equals("0"))
                    ((TreeObject)child.getUserObject()).setIncluded(false);
            }
            else
            {
                ((TreeObject)child.getUserObject()).setSelected(false);
            }
            renderOpTree(child);
         }

    }
	*/
	
public void renderOpTree(DefaultMutableTreeNode parentnode)
	{
		
 	  DefaultMutableTreeNode parent = parentnode ;
	  DefaultMutableTreeNode child = null;	
             for(int i=0;i<parent.getChildCount();i++)
	  	{
  	 	   child = (DefaultMutableTreeNode)tempModel.getChild(parent,i);
                          //String userob = ((TreeObject)child.getUserObject()).toString();
		String userob = ((TreeObject)child.getUserObject()).getString();
	  	   if(viewoperations.containsKey(userob))
	   	     {
		           ((TreeObject)child.getUserObject()).setInt(0);	
			if(viewoperations.get(userob).equals("1"))
			    {
 				((TreeObject)child.getUserObject()).setInt(1);
				if(!child.isLeaf())
				{
				  for(int j=0;j<child.getChildCount();j++)
					{
			 		 DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
					 ((TreeObject)childofchild.getUserObject()).setInt(0);												
	 			            //String userob1 = ((TreeObject)childofchild.getUserObject()).toString();
					String userob1 = ((TreeObject)childofchild.getUserObject()).getString();
				            ((TreeObject)childofchild.getUserObject()).setInt(1);	
				            if(viewoperations.containsKey(userob1))
					   {
					        if(viewoperations.get(userob).equals("0"))
					         ((TreeObject)childofchild.getUserObject()).setInt(2);
					   }
					else viewoperations.put(userob1,"1");	
 				     }
			    }
		  }
		 	
		 else if(viewoperations.get(userob).equals("0"))
 		   	{	
				((TreeObject)child.getUserObject()).setInt(2);
				if(!child.isLeaf())
				{
				  for(int j=0;j<child.getChildCount();j++)
					{

			 		 DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
					 ((TreeObject)childofchild.getUserObject()).setInt(2);												
					//String userob1 = ((TreeObject)childofchild.getUserObject()).toString();		
 					String userob1 = ((TreeObject)childofchild.getUserObject()).getString();		
						viewoperations.put(userob1,"0");
						//System.out.println("FOR EXPLICITLY RXCLUDED" +(TreeObject)childofchild.getUserObject());				
	 			            }
				}		
			}
         	 				
		  }
      	else
 			{
				((TreeObject)child.getUserObject()).setInt(0);	
			}	
			
 			renderOpTree(child);	
			
	 	}	
	
	}	


 }













