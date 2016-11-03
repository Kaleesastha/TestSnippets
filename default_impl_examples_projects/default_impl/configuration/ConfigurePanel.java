/* $Id: ConfigurePanel.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/*
 * @(#)ConfigurePanel.java	
 *
 * Copyright (c) 1996-2000 Adventnet, Inc. All Rights Reserved.
 * Please read the included COPYRIGHTS file for more details.
 * 
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES 
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET,
 * INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE 
 * OR ITS DERIVATIVES.
 */

package com.adventnet.nms.example.config;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.TitledBorder;

import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.nms.util.NmsClientUtil;


/**
 * A class for building the UI for the example Configuration Management Screen,
 */
class ConfigurePanel extends JPanel implements ActionListener
{
	private JFrame frame = null;
	private JPanel taskPanel = null;
	private JPanel varbindPanel = null;
	private JPanel commandPanel = null;

	private JLabel taskLabel = null;
	private JLabel deviceLabel = null;
	private JTextField taskText = null;
	private JTextField deviceText = null;

	final String[] datatypes = {"INTEGER", "STRING", "OID", "Gauge", "Counter", "Counter64", "TimeTicks", "IpAddress", "Opaque", "BITS"};
	final byte[] typeConstants = {2, 4, 6, 66, 65, 70, 67, 64, 68, 3};

	void setDevice(String deviceName)
	{
		deviceText.setText(deviceName);
	}

	public ConfigurePanel(JFrame frame)
	{
		this.frame = frame;
		setLayout(new BorderLayout(10, 10));

		addTaskPanel();
		add(taskPanel, "North");

		addAttributesPanel();
		add(varbindPanel, "Center");

		addCommandPanel();
		add(commandPanel, "South");
	}

	private void addTaskPanel()
	{
		taskPanel = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		taskPanel.setLayout(gbl);
		taskPanel.setBorder(new TitledBorder(NmsClientUtil.GetString("Task")));

		taskLabel = new JLabel(NmsClientUtil.GetString("TaskName"));
		gbc.insets=new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		gbl.setConstraints(taskLabel, gbc);
		taskPanel.add(taskLabel);  

		taskText = new JTextField(NmsClientUtil.GetString("Task1"));
		gbc.insets=new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbl.setConstraints(taskText, gbc);
		taskPanel.add(taskText);  

		deviceLabel = new JLabel(NmsClientUtil.GetString("DeviceName"));
		gbc.insets=new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbl.setConstraints(deviceLabel, gbc);
		taskPanel.add(deviceLabel);  

		deviceText = new JTextField("");
		gbc.insets=new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbl.setConstraints(deviceText, gbc);
		taskPanel.add(deviceText);  
	}


	private Vector oids = new Vector();
	private Vector labels = new Vector();
	private Vector types = new Vector();
	private Vector values = new Vector();

	String[] getIdentifiers()
	{
		int size = oids.size();
		String[] oidArr = new String[size];
		for(int i = 0; i < size; i++)
		{
			oidArr[i] = ((JTextField) oids.elementAt(i)).getText();
		}
		return oidArr;
	}

	String[] getLabels()
	{
		int size = labels.size();
		String[] oidArr = new String[size];
		for(int i = 0; i < size; i++)
		{
			oidArr[i] = ((JTextField) labels.elementAt(i)).getText();
		}
		return oidArr;
	}

	byte[] getTypes()
	{
		int size = types.size();
		byte[] typeArr = new byte[size];
		for(int i = 0; i < size; i++)
		{
			String typeString = (String) ((JComboBox) types.elementAt(i)).getSelectedItem();
			typeString = typeString.trim();
			int dataTypeSize = datatypes.length;
			for(int j = 0; j < dataTypeSize; j++)
			{
				if(typeString.equals(datatypes[j]))
				{
					typeArr[i] = typeConstants[j];
				}
			}
		}
		return typeArr;
	}

	String[] getValues()
	{
		int size = oids.size();
		String[] valueArr = new String[size];
		for(int i = 0; i < size; i++)
		{
			valueArr[i] = ((JTextField) values.elementAt(i)).getText();
		}
		return valueArr;
	}

	String getTaskName()
	{
		return taskText.getText();
	}

	String getDeviceName()
	{
		return deviceText.getText();
	}

	boolean validateAttributeEntries()
	{
		String taskName = taskText.getText();
		if(taskName == null || taskName.trim().equals(""))
		{
			JOptionPane.showMessageDialog(frame, NmsClientUtil.GetString("Enter task name"), NmsClientUtil.GetString("Error"), JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String deviceName = deviceText.getText();
		if(deviceName == null || deviceName.trim().equals(""))
		{
			JOptionPane.showMessageDialog(frame, NmsClientUtil.GetString("Enter device name"), NmsClientUtil.GetString("Error"), JOptionPane.ERROR_MESSAGE);
			return false;
		}

		int size = oids.size();
		for(int i = 0; i < size; i++)
		{
			String oidStr = ((JTextField) oids.elementAt(i)).getText();
			SnmpOID sOID = new SnmpOID(oidStr);
			String valueStr = ((JTextField) values.elementAt(i)).getText();
			if(	oidStr == null || oidStr.trim().equals("")  ||
				sOID.toValue() == null || valueStr == null || valueStr.trim().equals("") )
			{
				JOptionPane.showMessageDialog(frame, java.text.MessageFormat.format(NmsClientUtil.GetString("Invalid attribute entries in the row {0}"),new String[]{"" + i}), NmsClientUtil.GetString("Error"), JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		return true;
	}

	private int countOfRows = 0;

	private GridLayout layout = null;

	JPanel attributesPanel = null;
	void addAttributesPanel()
	{
		varbindPanel = new JPanel();
		layout = new GridLayout();
		attributesPanel = new JPanel(layout);
		layout.setColumns(4);
		layout.setHgap(10);
		layout.setVgap(10);

		varbindPanel.setLayout(new BorderLayout(5, 5));
		varbindPanel.setBorder(new TitledBorder(NmsClientUtil.GetString("Define Attributes")));

		JLabel oidLabel = new JLabel(NmsClientUtil.GetString("NumericOID"), JLabel.CENTER);
		JLabel strOidLabel = new JLabel(NmsClientUtil.GetString("Label"), JLabel.CENTER);
		JLabel typeLabel = new JLabel(NmsClientUtil.GetString("Type"), JLabel.CENTER);
		JLabel valueLabel = new JLabel(NmsClientUtil.GetString("Value"), JLabel.CENTER);
		layout.setRows(++countOfRows);
		attributesPanel.add(oidLabel);
		attributesPanel.add(strOidLabel);
		attributesPanel.add(typeLabel);
		attributesPanel.add(valueLabel);
		addAttributes();
		JScrollPane jpane = new JScrollPane(attributesPanel);
		varbindPanel.add(jpane, "North");

		JPanel p2 = new JPanel();
		JButton b1 = new JButton(NmsClientUtil.GetString("More", 'M'));
		b1.setMnemonic('M');
		b1.addActionListener(this);
		p2.add(b1);

		JButton b2 = new JButton(NmsClientUtil.GetString("Fewer", 'F'));
		b2.setMnemonic('F');
		b2.addActionListener(this);
		p2.add(b2);
		varbindPanel.add(p2,"South");
		b1.setActionCommand("More");
		b2.setActionCommand("Fewer");
	}

	void addCommandPanel()
	{
		commandPanel = new JPanel();
		JButton configure = new JButton(NmsClientUtil.GetString("Configure", 'C'));
		configure.setMnemonic('C');
		configure.addActionListener((ActionListener)frame);
		configure.setActionCommand("Configure");
		commandPanel.add(configure);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("More"))
		{
			addAttributes();
		}
		else if (e.getActionCommand().equals("Fewer"))
		{
			int size = oids.size() - 1;
			if (size == 0) return;
			JTextField oid = (JTextField) oids.elementAt(size);
			JTextField label = (JTextField) labels.elementAt(size);
			JComboBox typeComb = (JComboBox) types.elementAt(size);
			JTextField value = (JTextField) values.elementAt(size);
			oids.removeElementAt(size);
			labels.removeElementAt(size);
			types.removeElementAt(size);
			values.removeElementAt(size);
			layout.setRows(--countOfRows);
			attributesPanel.remove(oid);
			attributesPanel.remove(label);
			attributesPanel.remove(typeComb);
			attributesPanel.remove(value);
		}
		frame.pack();
	}

	private void addAttributes()
	{
		JTextField oid = new JTextField(8);
		JTextField label = new JTextField(8);
		JComboBox typeComb = new JComboBox(datatypes);
		JTextField value = new JTextField(8);
		oids.addElement(oid);
		labels.addElement(label);
		types.addElement(typeComb);
		values.addElement(value);
		layout.setRows(++countOfRows);
		attributesPanel.add(oid);
		attributesPanel.add(label);
		attributesPanel.add(typeComb);
		attributesPanel.add(value);
	}
}
