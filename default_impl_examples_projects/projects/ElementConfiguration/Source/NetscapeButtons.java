//$Id: NetscapeButtons.java,v 1.1 2006/08/29 13:56:51 build Exp $

package com.adventnet.nms.config;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class NetscapeButtons extends JComponent  //implements MouseListener
{
	public JTable table = null;
	public DefaultTableModel tableModel = null;
	int columnShown = 3;
	Vector columnVector = null;
	JButton b1 = null;
	JButton b2 = null;	

	public NetscapeButtons()
	{
		this.setLayout(new GridLayout(1,2));
		this.setSize(25,20);
		b1 = new JButton("");
		b2 = new JButton("");
		b1.setMaximumSize(new Dimension(20,20));
		b1.setMinimumSize(new Dimension(20,20));
		b1.setPreferredSize(new Dimension(20,20));
		b2.setMaximumSize(new Dimension(20,20));
		b2.setMinimumSize(new Dimension(20,20));
		b2.setPreferredSize(new Dimension(20,20));
		b1.setBorder(new BevelBorder(BevelBorder.RAISED));
		b2.setBorder(new BevelBorder(BevelBorder.RAISED));
		this.add(b1);
		this.add(b2);
	}

	public JButton getNextButton()
	{
		return b2;
	}

	public JButton getBackButton()
	{
		return b1;
	}

	public void setIconForNextButton()
	{
	}

	public void setIconForBackButton()
	{
	}
}

