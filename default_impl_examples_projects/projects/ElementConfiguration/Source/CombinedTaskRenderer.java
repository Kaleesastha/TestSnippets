//$Id: CombinedTaskRenderer.java,v 1.1 2006/08/29 13:56:51 build Exp $
 package com.adventnet.nms.config ; 


 import javax.swing.tree.*;
 import javax.swing.border.*;
 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class CombinedTaskRenderer   implements javax.swing.tree.TreeCellRenderer
 {
	JPanel panel = null;
	JLabel label = null;
	JCheckBox box = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//Color color = new Color(204,204,255);
	Color color = Color.lightGray;
	LineBorder border = new LineBorder(new Color(153,255,153),1);
	
	ConfigPanel configPanel = null;
	String protocol = "";
	ImageIcon snmpIcon = null;
	ImageIcon telnetIcon = null;
	ImageIcon tftpIcon = null;
	ImageIcon tl1Icon = null;	
	ImageIcon rootIcon = null;
	
	
	
	 public CombinedTaskRenderer(ConfigPanel configPanel)
	 {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		box = new JCheckBox();
		label = new JLabel();
		label.setOpaque(true);
		box.setBackground(Color.white) ;
		setUpGUI();
		this.configPanel = configPanel;
		snmpIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/snmptask.png");
		telnetIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/telnettask.png");
		tftpIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/tftptask.png");
		tl1Icon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/tl1task.png");
		
		rootIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/devicelist.png");
		
	 }
	

	public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
 	{
		cons.gridx = x;
		cons.gridy = y;
		cons.gridwidth = width;
		cons.gridheight = height;
		cons.weightx = wtX;
		cons.weighty = wtY;
		cons.anchor = anchor;
		cons.fill = fill;
		cons.insets = inset;
		cons.ipadx = padX;
		cons.ipady = padY;
	} 
	
	public void setUpGUI()
	{
		inset = new Insets(1,0,0,0);
		setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
		panel.add(box,cons);
		inset = new Insets(0,0,0,0);
		setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panel.add(label,cons);
	} 

	//--Interface Implementation--//

	public java.awt.Component getTreeCellRendererComponent( javax.swing.JTree tree, java.lang.Object value, boolean isSelected, 
							boolean expanded,boolean leaf, int row, boolean hasFocus)
	{
		if((((DefaultMutableTreeNode)value).getPath()).length == 2)
		{
			if(value.toString().equalsIgnoreCase("snmp"))
			{
				label.setIcon(snmpIcon);
			}
			else if(value.toString().equalsIgnoreCase("telnet"))
			{
				label.setIcon(telnetIcon);
			}
			else if(value.toString().equalsIgnoreCase("tftp"))
			{
				label.setIcon(tftpIcon);
			}
			else if(value.toString().equalsIgnoreCase("tl1"))
			{
				label.setIcon(tl1Icon);
			}
			
			else
			{
				label.setIcon(rootIcon);
			}
			label.setIconTextGap(7);
			box.setVisible(false);
		}
		
		else
		{
			MultipleListSelectionObject obj = (MultipleListSelectionObject)((DefaultMutableTreeNode)value).getUserObject();
			if(obj.getState() && row !=0)
			{
				box.setSelected(true);
			}
			else
			{
				box.setSelected(false);
			}
			label.setIcon(null);
			label.setIconTextGap(0);
			box.setVisible(true);
		}
		
		if(isSelected)
		{
			label.setBackground(Color.blue);
			label.setForeground(Color.white);			
			//panel.setBorder(border);
		}
		else
		{
			label.setBackground(tree.getBackground());
			label.setForeground(tree.getForeground());			
			//panel.setBorder(null);
		}
		
		panel.setBackground(tree.getBackground());
		label.setFont(tree.getFont());
		box.setBackground(tree.getBackground());
		label.setText(value.toString());
		return panel;
	}
 }


