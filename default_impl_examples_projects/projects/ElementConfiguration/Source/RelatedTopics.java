
//$Id: RelatedTopics.java,v 1.1 2006/08/29 13:56:51 build Exp $
package com.adventnet.nms.config ; 

import javax.swing.border.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RelatedTopics extends javax.swing.JWindow
{
	private JPanel panel = null;

	private JLabel [] array = null;

	private GridLayout layout = null;

	private HelpDialog parent = null;

	private Vector vector = null;

	int maxWidth = 0;

	public RelatedTopics(HelpDialog parent,Point point, int height,int width,Vector vector)
	{
		super(parent);
		this.parent = parent;
		layout = new GridLayout(vector.size(),1);
		getContentPane().setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(layout);
		array = new JLabel[vector.size()];
		this.vector = vector;
		for(int i=0;i<vector.size();i++)
		{
			array[i] = new JLabel();
			array[i].setOpaque(true);
			array[i].setForeground(Color.green);
			array[i].setBackground(Color.black);
			array[i].setFont(new Font("dialog",Font.PLAIN,12));
			int length = (vector.elementAt(i).toString()).length();
			if(length > maxWidth)
				maxWidth = length;
			array[i].setText(vector.elementAt(i).toString());
			array[i].addMouseListener(new Listener());
			panel.add(array[i]);
		}

		getContentPane().add(panel,BorderLayout.CENTER);
		panel.setBorder(new LineBorder(Color.yellow,2));
		setForeground(Color.green);
		setBackground(Color.black);
		setLocation((int)point.getX(),(int)point.getY()+28);
		height = vector.size()*20;
		setSize(maxWidth*13,height);
		setVisible(true);
	}

	class Listener extends MouseAdapter
	{
		public void mouseEntered(MouseEvent me)
		{
			for(int i=0;i<vector.size();i++)
			{
				if(me.getComponent().equals(array[i]))
				{
					array[i].setForeground(Color.red);
					array[i].setBackground(Color.lightGray);
					array[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

			}
		}

		public void mouseExited(MouseEvent me)
		{
			for(int i=0;i<vector.size();i++)
			{
				if(me.getComponent().equals(array[i]))
				{
					array[i].setForeground(Color.green);
					array[i].setBackground(Color.black);
					array[i].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));					
				}

				else
				{
					array[i].setForeground(Color.green);
					array[i].setBackground(Color.black);
				}
			}
		}

		String selected = "";

		public void mouseClicked(MouseEvent me)
		{
			for(int i=0;i<vector.size();i++)
			{
				if(me.getComponent().equals(array[i]))
				{
					selected = array[i].getText();
					break;
				}

			}

			parent.showHelpFor(selected);
			RelatedTopics.this.setVisible(false);
		}
	}

}




