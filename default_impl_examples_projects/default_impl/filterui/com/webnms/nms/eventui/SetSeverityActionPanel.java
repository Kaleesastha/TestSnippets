//$Id: SetSeverityActionPanel.java,v 1.1.6.1 2012/01/25 05:09:27 karen.r Exp $
package com.webnms.nms.eventui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.util.NmsClientUtil;


public class SetSeverityActionPanel extends AbstractFilterActionPanel
{
	
	private String initialMessage= "The status of this the Alert was changed by Escalation Policy";//No Internationalisation
	
	public SetSeverityActionPanel(AddFilterAction parent)
	{
		super(parent);
	}
	
	protected Properties getActionProps()
	{
		Properties props = new Properties();
		String value = parent.checkString(textFields[0]);
		if(value == null)
		{
			return null;
		}
		props.setProperty("name",value);
		
		String val = (String)comboBox.getSelectedItem();
		props.setProperty("stringseverity",val);
		
		String message = textArea.getText();
		props.setProperty("message", message);
		
		props.putAll(super.getActionProps());
		return props;

	}

	protected void initDetailsPanel()
	{
		detailsPanel.setLayout(new GridBagLayout());

		String[] labels = new String[]{"javaui.filter.NotificationName","javaui.filter.setSeverity","javaui.filter.Message"}; //No I18N

		textFields = new JTextField[1];

		int textFieldCounter = 0;

		GridBagConstraints constraints = new GridBagConstraints();

		for(int i = 0;i < 3;i++)
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

			JComponent comp = null;
			if(i == 1)
			{
				comboBox = new JComboBox();
				comboBox.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
				comp = comboBox;
				Vector v = null;
	            if( NmsClientUtil.severityInfo != null)
	            {
	                v = NmsClientUtil.severityInfo.getNames(SeverityInfo.EXCLUDE_NO_CRITICALITY);
	                for (int j=0;j<v.size();j++)
	                {
	                    comboBox.addItem(v.elementAt(j));
	                }
	                comboBox.setSelectedIndex(0);
	            }
				constraints.insets = new java.awt.Insets(5, 1, 5, 5);
			}
			else if(i == 2)
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				textArea = new JTextArea();
				textArea.setLineWrap(true);
				textArea.setRows(4);
				textArea.setWrapStyleWord(true);
				textArea.setText(NmsClientUtil.GetString("javaui.filteractionui.SetSeverityMessage"));
				textArea.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
				textFieldCounter++;
				scrollPane.setViewportView(textArea);
				comp = scrollPane;
				constraints.insets = new java.awt.Insets(5, 1, 5, 5);
			}
			else
			{
				textFields[textFieldCounter] = new JTextField(8);
				textFields[textFieldCounter].setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
				textFields[textFieldCounter].setName(NmsClientUtil.GetString(labels[i]));
				comp = textFields[textFieldCounter];
				textFieldCounter ++;
				constraints.insets = new java.awt.Insets(5, 5, 5, 10);				
			}

			constraints.gridx = 1;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.anchor = GridBagConstraints.WEST;
			detailsPanel.add(comp, constraints);
		}
	}
	
	protected void initAdvPanel()
	{
		enableSepThreadHandling = false;
	}

	protected void setActionProps(Properties props)
	{
		if(props == null)
		{
			return;
		}

		String val = props.getProperty("name");
		textFields[0].setText(val);
		val = props.getProperty("stringseverity");
		comboBox.setSelectedItem(val);
		
		val = props.getProperty("message");
		textArea.setText(val);
		super.setActionProps(props);
		
	}
	
	protected void resetButtonActionPerformed(ActionEvent evt)
	{
		super.resetButtonActionPerformed(evt);
		comboBox.setSelectedIndex(0);
		textArea.setText(NmsClientUtil.GetString("javaui.filteractionui.SetSeverityMessage"));
	}
	
	public String getHelpText()
	{
		return "javaui.filteractionui.suppressHelpText"; //No I18N
	}
	
	private JComboBox comboBox;
	private JTextArea textArea;

}
