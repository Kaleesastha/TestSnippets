package com.adventnet.clientTest;


import com.adventnet.nms.startclient.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

/** This class can be used for testing Panel integration and TreePopupMenu.*/
public class PanelTest extends AbstractBaseNmsPanel
{
		//treePopupMenuName = "Panel_Test_Menu"
		public void setProperties(Properties prop)
		{
			System.out.println("the prop erties set are "+prop);
		}
	
		public String key()
		{
			return new String("Panel Test");
//			return null;
		}
	
		public void init(JApplet app)
		{
			JLabel lab = new JLabel("my Test Program");
			add(lab,"Center");
		 
		}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("action is "+e.getActionCommand());
	}
	
	public JMenu getTreePopupMenu()
	{	
		JMenu menu = new JMenu("Panel_Test_Menu");
		JMenuItem menuItem1 = new JMenuItem("first Menu");	
		menu.add(menuItem1);
		JMenuItem menuItem2 = new JMenuItem("Second Menu");	
		menu.add(menuItem2);
		menu.addActionListener(this);
		return menu;
	}
	


}

