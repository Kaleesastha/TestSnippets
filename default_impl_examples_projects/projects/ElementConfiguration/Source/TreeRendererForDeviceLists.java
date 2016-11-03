
 package com.adventnet.nms.config ; 

 import javax.swing.tree.*;
 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;
 public class TreeRendererForDeviceLists extends JLabel  implements javax.swing.tree.TreeCellRenderer
 {
	ConfigPanel configPanel = null;
	javax.swing.ImageIcon rootIcon = null;
       	javax.swing.ImageIcon deviceIcon = null;
       	javax.swing.ImageIcon parentIcon = null;
	//Color color = new Color(0,153,102);
	Color color = Color.black;
	
	 public TreeRendererForDeviceLists()
	 {
		super();
		setOpaque(true);
	 }
	
	public TreeRendererForDeviceLists(ConfigPanel panel)
	{
		super();
		setOpaque(true);
		configPanel = panel;
		java.net.URL url = configPanel.applet.getDocumentBase();
		String root = String.valueOf(url) + "../images/devicelist.png";
		String	devices = String.valueOf(url) + "../images/devices.png";
		String	parent = String.valueOf(url) + "../images/devicelist.png";	
	       	rootIcon = configPanel.configClientUtils.getImage(root);
	       	deviceIcon = configPanel.configClientUtils.getImage(devices);
	       	parentIcon = configPanel.configClientUtils.getImage(parent);		
	}
	
	//--Interface Implementation--//

	public java.awt.Component getTreeCellRendererComponent( javax.swing.JTree tree, java.lang.Object value, boolean isSelected,
						boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		if(leaf)
		{
			if(row == 0)
			{
				setIcon(rootIcon);
    	  			//setBackground(new Color(51,0,153));
				//setForeground(Color.white);
				setBackground(tree.getBackground());	
				setForeground(Color.red);
			}	
			else
			{
				setIcon(deviceIcon);	
				setBackground(tree.getBackground());	
				setForeground(color);	
			}	
		}
		
		else
		{
			if(row == 0)
			{
				setIcon(rootIcon);
				//setBackground(new Color(51,0,153));
				//setForeground(Color.white);
				setBackground(tree.getBackground());	
				setForeground(Color.red);						
			}	
			else
			{
				setIcon(parentIcon);
				//setBackground(new Color(255,255,204));					
				//setForeground(Color.red);
				setBackground(tree.getBackground());	
				setForeground(tree.getForeground());						
			}	
		}
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		String str = (String)node.getUserObject();
//		setForeground(Color.blue);
		setFont(tree.getFont());
		setText(str+"    ");
		
		return this;
	}
 }







