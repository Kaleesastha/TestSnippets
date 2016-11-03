//$Id: TreeRendererForTaskHistory.java,v 1.1 2006/08/29 13:56:51 build Exp $
 package com.adventnet.nms.config ; 

 import javax.swing.border.*;
 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class TreeRendererForTaskHistory extends javax.swing.tree.DefaultTreeCellRenderer
 {
	ConfigPanel configPanel = null;
	String protocol = "";
	
	ImageIcon taskIcon = null;
	ImageIcon deviceListIcon = null;
	ImageIcon associatedIcon = null;
	Color color = new Color(204,204,255);
	LineBorder border = new LineBorder(new Color(153,255,153),1);
	
	
	 public TreeRendererForTaskHistory()
	 {

	 }
	
	public TreeRendererForTaskHistory(ConfigPanel panel, String protocol)
	{
		super();
		configPanel = panel;
		this.protocol = protocol;
		protocol = protocol.toLowerCase();
		if(protocol.equals("snmp"))
			taskIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/snmptask.png");
		else if (protocol.equals("telnet"))
			taskIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/telnettask.png");
		else if (protocol.equals("tftp"))
			taskIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/tftptask.png");
		else if (protocol.equals("tl1"))
			taskIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/tl1task.png");
		else if (protocol.equals("combined"))
			taskIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/combinedtask.png");
		
		deviceListIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/devicelist.png");
		
		associatedIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/deviceslist.png");
		setOpaque(true);
	}
	
	public java.awt.Component getTreeCellRendererComponent( javax.swing.JTree tree, java.lang.Object value, boolean isSelected,
						boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		if(row == 0)
		{
			setIcon(taskIcon);
		}
		
		else
		{
			if(leaf)
			{
				if(value.toString().equalsIgnoreCase("Associated Devices"))
				{
					setIcon(associatedIcon);
				}
				
				else
				{
					setIcon(deviceListIcon);
				}
			}
		}

		if(isSelected)
		{
			super.setBackground(color);
			super.setForeground(Color.black);
			super.setBorder(border);
		}
		
		else
		{
			super.setBackground(tree.getBackground());
			super.setForeground(tree.getForeground());
			super.setBorder(null);
		}
		
		setText(value.toString());
		return this;
	}
 }

