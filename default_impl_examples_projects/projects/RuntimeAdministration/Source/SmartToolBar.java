
//$Id: SmartToolBar.java,v 1.1 2006/08/29 13:57:02 build Exp $


 package com.adventnet.nms.runtimeconfig ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;
 import javax.swing.tree.*;
 import java.util.*;
 public class SmartToolBar extends JToolBar
 {
	RuntimeConfigFrame frame = null;
	TreeCombo treeCombo = null;
	 public SmartToolBar(RuntimeConfigFrame fr)
	 {
		frame = fr;
		setFloatable(true);
		treeCombo = new TreeCombo(frame.model);
		treeCombo.addActionListener(new ComboListener());
		treeCombo.setPreferredSize(new Dimension(210,25));
    	treeCombo.setMinimumSize(new Dimension(210,25));
    	treeCombo.setMaximumSize(new Dimension(210,25));
    	add(treeCombo);
		addSeparator();
		addSeparator();
		addSeparator();
		
		ToolBarAction toolBarAction = new ToolBarAction();
		JButton saveAsButton =  add(toolBarAction);
    	ImageIcon icon = frame.getCommonBuilderUIImpl().getScaledImage("file.png", "images/runtimeadmin" , 28 , 28 ,Image.SCALE_DEFAULT );
	    saveAsButton.setPreferredSize(new Dimension(28,28));
	    saveAsButton.setMinimumSize(new Dimension(28,28));
	    saveAsButton.setMaximumSize(new Dimension(28,28));
	    saveAsButton.setBorderPainted(false);
    	saveAsButton.setIcon(icon);
    	icon = frame.getCommonBuilderUIImpl().getScaledImage("file_mo.png", "images/runtimeadmin" , 28 , 28 ,Image.SCALE_DEFAULT );
	saveAsButton.setRolloverIcon(icon);
	    saveAsButton.setActionCommand("Apply To Server");
    	saveAsButton.setToolTipText(RuntimeConfigFrame.getString("Apply To Server"));
		addSeparator();
	
		JButton close = add( toolBarAction );
    	icon = frame.getCommonBuilderUIImpl().getScaledImage("exit.png","images/" , 28 , 28 ,Image.SCALE_DEFAULT );
	    close.setPreferredSize(new Dimension(28,28));
	    close.setMinimumSize(new Dimension(28,28));
	    close.setMaximumSize(new Dimension(28,28));
	    close.setIcon(icon);
	    close.setBorderPainted(false);
    	icon = frame.getCommonBuilderUIImpl().getScaledImage("exit_mo.png","images/" , 28 , 28 ,Image.SCALE_DEFAULT );
	close.setRolloverIcon(icon);
	    close.setActionCommand("Close");
    	close.setToolTipText(RuntimeConfigFrame.getString("Close"));
		addSeparator();
		
		JButton help = add(toolBarAction);
	    icon = frame.getCommonBuilderUIImpl().getScaledImage("toolhelp.png","images/", 28 , 28 ,Image.SCALE_DEFAULT ); 
	    help.setPreferredSize(new Dimension(28,28));
	    help.setMinimumSize(new Dimension(28,28));
	    help.setMaximumSize(new Dimension(28,28));
	    help.setBorderPainted(false);
    	help.setIcon(icon);

	    icon = frame.getCommonBuilderUIImpl().getScaledImage("toolhelp_mo.png","images/", 28 , 28 ,Image.SCALE_DEFAULT ); 
	help.setRolloverIcon(icon);    
	help.setActionCommand("Help Contents");
	    help.setToolTipText(RuntimeConfigFrame.getString("Help"));
		//addSeparator();
		
	 }
	
		public DefaultMutableTreeNode findNode(DefaultMutableTreeNode parent , String match)
		{
			if (parent == null)
				return null;
			// Commented by Balan on 15/03/03
			//String treename =  (String)parent.getUserObject ();
                        //Comment Ends
                        
                        // Added by Balan  on 15/03/03
                        String treename =""+((Hashtable)parent.getUserObject ()).get("TREE-NAME");
                        // Add Ends
                        
			if (treename != null)
			{
			if (treename.equals (match))
				return parent;
			}

			if (frame.model.isLeaf(parent))
				return null;

			Enumeration en = parent.children();
			if ((en == null) || (!en.hasMoreElements()))
				return null;
			for (; en.hasMoreElements();)
			{

				DefaultMutableTreeNode child = (DefaultMutableTreeNode) en.nextElement();
				DefaultMutableTreeNode returnNode = findNode (child, match);
				if (returnNode != null)
					return returnNode;
			}
			return null;
		}

		public boolean fromCombo = false;
	
		public void update (String selectedNode)
		{
			treeCombo.setSelectedNode( selectedNode);
		}
	
		public class ComboListener implements ActionListener
		{ 
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource() != null)
				{

                                 // Commented by Balan on 15/03/03
                                 //TreeNode selectedTreeNode = findNode((DefaultMutableTreeNode)frame.model.getRoot() , (( (TreeCombo)evt.getSource() ).getSelectedNode()).toString() );
                                 // Comment Ends

                                 // Added by Balan on 15/03/03

                   Object tempObject   = ((DefaultMutableTreeNode)((TreeCombo)evt.getSource()).getSelectedNode()).getUserObject();          
                                          String strMatchNode =""+((Hashtable)tempObject).get("TREE-NAME"); 
                                          TreeNode selectedTreeNode = findNode((DefaultMutableTreeNode)frame.model.getRoot(),strMatchNode);
                                       
                                 // Add Ends

                                     if(selectedTreeNode != null)
					{
						fromCombo = true;
						TreeNode tempNode[] = frame.model.getPathToRoot(selectedTreeNode);
						frame.JTree1.setSelectionPath(new TreePath ( tempNode ));
					}else
					{
						//This case should n't occur
					}
				}
			}
		}
	
		public class ToolBarAction extends AbstractAction 
		{
     		public void actionPerformed(ActionEvent evt)
     		{

				frame.handleToolBarAction ( evt.getActionCommand() );

     		}
		}

     //Added by Balan on 4/7/03 for the memory leak issue reported by SSP Team
     public void dispose()
     {
         treeCombo = null;
         frame     = null;
     }
     //Add Ends
 }









