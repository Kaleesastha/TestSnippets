/* $Id: SendTrapActionPanel.java,v 1.2 2010/10/29 13:45:50 swaminathap Exp $
 * 
 * SendTrapActionPanel.java
 *
 */

package com.webnms.nms.eventui;

import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.adventnet.nms.eventui.VariableBindingList;
import com.adventnet.nms.util.NmsClientUtil;


public class SendTrapActionPanel extends AbstractFilterActionPanel
{

	private Vector<String[]> vbList;
	private String[] keys = {"name","peername","trap_port","community","timeticks","version","enterprise","generic","specific","trapOid"}; //No I18N	

	public SendTrapActionPanel(AddFilterAction parent)
	{
		super(parent);
	}

	@Override
	protected Properties getActionProps()
	{
		Properties props = new Properties();
		String value = null;
		int size = keys.length;

		boolean isV1 = true; 
		if(versionBox.getSelectedIndex() == 1)
		{
			isV1 = false;
		}

		for(int i = 0; i< size; i++)
		{
			if(i == 2 || i == 4)
			{
				value = parent.checkNumber(textFields[i]);
			}
			else if(i !=5 )
			{
				value = parent.checkString(textFields[i]);
			}
			if(value == null)
			{
				return null;
			}
			if(i == 5)
			{
				if(isV1)
				{
					for(i=6;i<9;i++)
					{
						value = parent.checkNumber(textFields[i-1]);
						if(value == null)
						{
							return null;
						}
						props.setProperty(keys[i], value);
					}
					value = "v1"; //No I18N
				}
				else
				{
					value = parent.checkString(textFields[8]);
					if(value == null)
					{
						return null;
					}
					props.setProperty(keys[9], value);
					value = "v2c"; //No I18N
				}
				props.setProperty(keys[5],value);
				break;
			}
			props.setProperty(keys[i],value);
		}

		size = vbList.size();

		for(int i = 0; i < size; i++)
		{
			String[] vb = vbList.elementAt(i);
			props.setProperty("vb[0]" + i, vb[0]);
			props.setProperty("vb[1]" + i, vb[1]);
			props.setProperty("vb[2]" + i, vb[2]);
		}
		
		props.putAll(super.getActionProps());
		return props;

	}

	@Override
	public String getHelpText()
	{
		return "javaui.filteractionui.trapHelpText"; //No I18N
	}

	@Override
	protected void initDetailsPanel()
	{
		detailsPanel.setLayout(new GridBagLayout());
		String[] labels = {"javaui.filter.NotificationName","javaui.filter.TrapDestination","javaui.filter.DestinationPort","javaui.filter.TrapCommunity","javaui.filter.SysUpTime","javaui.filter.Enterprise","javaui.filter.GenericType","javaui.filter.SpecificType","javaui.filter.TrapOID"}; //No I18N
		textFields = new JTextField[9];

		GridBagConstraints constraints = new GridBagConstraints();

		for(int i = 0;i < 5;i++)
		{
			JLabel labelComp = new JLabel(NmsClientUtil.GetString(labels[i]));
			labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N

			constraints.gridx = 0;
			constraints.gridy = i;
			constraints.weightx = 0;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.fill = GridBagConstraints.NONE;
			constraints.insets = new java.awt.Insets(5, 10, 5, 20);
			detailsPanel.add(labelComp,constraints);

			textFields[i] = new JTextField(8);					
			textFields[i].setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
			textFields[i].setName(NmsClientUtil.GetString(labels[i]));
			constraints.gridx = 1;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.insets = new java.awt.Insets(5, 5, 5, 10);
			detailsPanel.add(textFields[i], constraints);
		}
		
		//create snmp panel

		snmpPanel = new JPanel();
		snmpPanel.setLayout(new GridBagLayout());

		JLabel labelComp = new JLabel(NmsClientUtil.GetString("javaui.filteractionui.snmpVersion"));
		labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new java.awt.Insets(5, 15, 5, 20);
		snmpPanel.add(labelComp,constraints);

		
		versionBox = new JComboBox();
		versionBox.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		versionBox.addItem(NmsClientUtil.GetString("javaui.filter.V1"));
		versionBox.addItem(NmsClientUtil.GetString("javaui.filter.V2"));
		versionBox.addItemListener(new ItemListener() {			
			public void itemStateChanged(ItemEvent e)
			{
				versionChangePerformed(e);
			}
		});
		
		constraints.gridx = 1;
		constraints.weightx = 1;
		constraints.insets = new java.awt.Insets(5, 5, 5, 10);
		snmpPanel.add(versionBox, constraints);

		
		v1Panel = new JPanel();
		v1Panel.setLayout(new GridBagLayout());

		for(int i = 5 ; i < 8;i++)
		{
			labelComp = new JLabel(NmsClientUtil.GetString(labels[i]));
			labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
			
			constraints.gridx = 0;
			constraints.gridy = i-5;
			constraints.weightx = 0;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.fill = GridBagConstraints.NONE;
			constraints.insets = new java.awt.Insets(5, 0, 5, 5);
			v1Panel.add(labelComp,constraints);

			textFields[i] = new JTextField(8);
			textFields[i].setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
			textFields[i].setName(NmsClientUtil.GetString(labels[i]));
			constraints.gridx = 1;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.NONE;
			constraints.insets = new java.awt.Insets(5, 5, 5, 5);
			v1Panel.add(textFields[i], constraints);
		}


		v2Panel = new JPanel();
		v2Panel.setLayout(new GridBagLayout());

		labelComp = new JLabel(NmsClientUtil.GetString(labels[8]));
		labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 0;
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new java.awt.Insets(5, 0, 5, 5);
		v2Panel.add(labelComp,constraints);

		textFields[8] = new JTextField(3);
		textFields[8].setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		textFields[8].setName(NmsClientUtil.GetString(labels[8]));
		constraints.gridx = 1;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new java.awt.Insets(5, 5, 5, 10);
		v2Panel.add(textFields[8], constraints);

		versionPanel = new JPanel();
		versionPanel.setLayout(new CardLayout());
		versionPanel.add(v1Panel,NmsClientUtil.GetString("javaui.filter.V1"));
		versionPanel.add(v2Panel,NmsClientUtil.GetString("javaui.filter.V2"));
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new java.awt.Insets(10, 15, 5, 10);
		snmpPanel.add(versionPanel,constraints);

		JLabel listLabel = new JLabel();
		listLabel.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		listLabel.setText(NmsClientUtil.GetString("javaui.filter.variablebindinglist"));
		
		listButton = new JButton();
		listButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		listButton.setText(NmsClientUtil.GetString("javaui.filter.List"));
		listButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				listButtonActionPerformed(evt);
			}
		});
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 0;
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new java.awt.Insets(15, 15, 5, 5);
		snmpPanel.add(listLabel,constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new java.awt.Insets(5, 15, 5, 10);
		snmpPanel.add(listButton,constraints);
		
		vbList = new Vector<String[]>();
	}
	
	protected void initContentPane()
	{
		super.initContentPane();
		contentPane.insertTab(NmsClientUtil.GetString("javaui.filteractionui.snmpTab"), null,snmpPanel,"",1);
	}
	
	private void versionChangePerformed(ItemEvent evt)
	{
		String version = (String)evt.getItem();
		((CardLayout)versionPanel.getLayout()).show(versionPanel,version);

	}

	private void listButtonActionPerformed(java.awt.event.ActionEvent evt)
	{
		VariableBindingList blist = new VariableBindingList((Frame)parent.getParent(), true, vbList);
		NmsClientUtil.centerWindow(blist);
		blist.setVisible(true);
	}

	@Override
	protected void setActionProps(Properties props)
	{

		String value = null;
		int size = keys.length;
		for(int i=0;i<size;i++)
		{
			value = props.getProperty(keys[i]);
			if(value == null ||value.trim().equals(""))
			{
				switch (i)
				{
					case 0 :value = "";  //No I18N
					break;
					case 1 :value = "localhost";  //No I18N
					break;
					case 2 :value = "162";  //No I18N
					break;
					case 3 :value = "public";  //No I18N
					break;
					case 4 :value = "0";  //No I18N
					break;
				}        	
			}
			if(i == 5)
			{
				if(value.equals("v1"))
				{
					for(i=6;i<size;i++)
					{
						if(i == 9)
						{
							textFields[i-1].setText("");
							continue;
						}
						value = props.getProperty(keys[i]);
						if(value == null ||value.trim().equals(""))
						{
							switch (i)
							{
								case 6 :value = "11";  //No I18N
								break;
								case 7 :value = "6"; //No I18N
								break;
								case 8 :value = "1001"; //No I18N
								break;
							}        	
						}
						textFields[i-1].setText(value);
					}
					versionBox.setSelectedIndex(0);
				}
				else
				{
					for(i=6;i<size;i++)
					{
						if(i != 9)
						{
							textFields[i-1].setText(""); //No I18N
							continue;
						}
						value = props.getProperty(keys[i]);
						textFields[i-1].setText(value);
					}
					versionBox.setSelectedIndex(1);
				}
				break;
			}
			textFields[i].setText(value);        	
		}

		vbList.clear();
		String bindKey = "vb[0]";//No i18n
		int i = 0;

		while(true)
		{
			String key = bindKey + i;

			String str = props.getProperty(key);
			if(str != null)
			{
				String oid      = props.getProperty("vb[0]" + i);
				String snmptype = props.getProperty("vb[1]" + i);
				value    = props.getProperty("vb[2]" + i);
				String[] strm   = {oid, snmptype, value};

				vbList.add(strm);
				i++;
			}
			else
			{
				break;
			}
		}
		
		super.setActionProps(props);
	}

	protected void resetButtonActionPerformed(ActionEvent evt)
	{
		super.resetButtonActionPerformed(evt);
		textFields[1].setText("localhost"); //No I18N
		textFields[2].setText("162"); //No I18N
		textFields[3].setText("public"); //No I18N
		textFields[4].setText("0"); //No I18N
		textFields[5].setText("11"); //No I18N
		textFields[6].setText("6"); //No I18N
		textFields[7].setText("1001"); //No I18N
		versionBox.setSelectedIndex(0);
		((CardLayout)versionPanel.getLayout()).show(versionPanel,NmsClientUtil.GetString("javaui.filter.V1settings"));
		vbList.clear();
	}

	private JButton listButton;
	private JPanel v1Panel;
	private JPanel v2Panel;
	private JPanel snmpPanel;
	private JComboBox versionBox;
	private JPanel versionPanel;


}
