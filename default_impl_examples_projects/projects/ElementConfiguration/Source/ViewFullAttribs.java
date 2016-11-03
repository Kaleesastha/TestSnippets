//$Id: ViewFullAttribs.java,v 1.1 2006/08/29 13:56:51 build Exp $
package com.adventnet.nms.config ; 


import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ViewFullAttribs extends JPanel 
{

	private JTable table = new JTable();
	private ConfigPanel configPanel = null;

	private SetPropertiesForDevices parent = null;

	public ViewFullAttribs(ConfigPanel configPanel, SetPropertiesForDevices parent, Point point, Vector valueVector, Vector nameVector)
	{
		this.configPanel = configPanel;
		this.parent = parent;

		setLayout(new BorderLayout());
		DefaultTableModel tableModel = new DefaultTableModel();
		table.setModel(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane,BorderLayout.CENTER);
		table.setRowHeight(21);
		table.setBackground(Color.darkGray);
		table.setForeground(Color.white);
		table.setFont(new Font("dialog",Font.BOLD,13));
		scrollPane.getViewport().setBackground(Color.darkGray);
		((JTableHeader)table.getTableHeader()).setBackground(Color.white);
		Vector v = new Vector();
		v.addElement(configPanel.configClientUtils.getString("Attribute Name"));
		v.addElement(configPanel.configClientUtils.getString("Attribute Value"));
		tableModel.setDataVector(new Vector(),v);

		for(int i=0;i<nameVector.size();i++)
		{
			Vector v1 = new Vector();
			v1.addElement(nameVector.elementAt(i));
			v1.addElement(valueVector.elementAt(i));
			tableModel.addRow(v1);
		}

		if(nameVector.size() > 10)
			setPreferredSize(new Dimension(200,nameVector.size()*20+38));
		else if(nameVector.size() <5)
			setPreferredSize(new Dimension(200,nameVector.size()*20+28));
		else
			setPreferredSize(new Dimension(200,nameVector.size()*20+33));
		setLocation((int)point.getX(),(int)point.getY());
	}
}
