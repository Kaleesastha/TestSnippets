//$Id: RendererForTemplateDetailsTree.java,v 1.1 2006/08/29 13:56:51 build Exp $
package com.adventnet.nms.config ; 

import java.net.URL;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RendererForTemplateDetailsTree extends javax.swing.JLabel  implements javax.swing.tree.TreeCellRenderer
{

	private ConfigPanel configPanel = null;

	private ImageIcon rootIcon = null;
	private ImageIcon detailsIcon = null;
	private ImageIcon neIcon = null;
	private ImageIcon ivIcon = null;
	private ImageIcon uiIcon = null;	
	private ImageIcon holderIcon = null;	

	public RendererForTemplateDetailsTree()
	{
	}

	public RendererForTemplateDetailsTree(ConfigPanel panel)
	{
		configPanel = panel;

		setOpaque(false);
		setForeground(Color.black);

		initializeIcons();
	}


	private void initializeIcons()
	{
		rootIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/addlink.png");
		detailsIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/templatedetails_small.png");
		neIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/ntwkinput_small.png");
		ivIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/inventoryinput_small.png");
		uiIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/userinput_small.png");		
		holderIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/placeholder.png");
	}

	//--Interface Implementation--//

	public java.awt.Component getTreeCellRendererComponent( javax.swing.JTree tree, java.lang.Object value, boolean isSelected,
			boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		if(leaf)
		{
			if(row==0)
			{
				setIcon(detailsIcon);
				setText(node.toString());
			}

			else
			{
				setIcon(holderIcon);
				setText(node.toString());
			}
		}


		else
		{
			if(row==0)
			{
				setIcon(detailsIcon);
				setText(node.toString());
			}	
			else
			{
				if(node.toString().equals("Network Input"))
				{
					setIcon(neIcon);
				}
				else if(node.toString().equals("Inventory Input"))
				{
					setIcon(ivIcon);
				}
				else 
				{
					setIcon(uiIcon);
				}
				setText(node.toString());
			}	
		}

		return this;
	}
}


