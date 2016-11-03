/* $Id: SuppressActionPanel.java,v 1.2 2010/10/29 13:45:51 swaminathap Exp $
 * 
 * SuppressActionPanel.java
 *
 */

package com.webnms.nms.eventui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Properties;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.adventnet.nms.util.NmsClientUtil;


/**
 * @author aravinds
 *
 */
public class SuppressActionPanel extends AbstractFilterActionPanel
{
	public SuppressActionPanel(AddFilterAction parent)
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
		
		boolean val = checkBox.isSelected();
		props.setProperty("suppressAll",String.valueOf(val));

		if(!val)
		{
			value = parent.checkString(textFields[1]);
			if(value == null)
			{
				return null;
			}
			props.setProperty("suppressInt",value);
		}
		else
		{
			props.setProperty("suppressInt","0");
		}
		
		props.putAll(super.getActionProps());
		return props;

	}

	protected void initDetailsPanel()
	{
		detailsPanel.setLayout(new GridBagLayout());

		String[] labels = new String[]{"javaui.filter.NotificationName","javaui.filter.SuppressAll","javaui.filter.SuppressInterval"}; //No I18N

		textFields = new JTextField[2];
		checkBox = new JCheckBox();

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
				checkBox = new JCheckBox();
				comp = checkBox;
				checkBox.setSelected(true);
				checkBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e)
					{
						changeSuppressState(e);							
					}
				});
				constraints.insets = new java.awt.Insets(5, 1, 5, 5);
			}
			else
			{
				textFields[textFieldCounter] = new JTextField(8);
				textFields[textFieldCounter].setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
				textFields[textFieldCounter].setName(NmsClientUtil.GetString(labels[i]));
				if(i == 2)
				{
					textFields[textFieldCounter].setEditable(false);
				}
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
		val = props.getProperty("suppressAll");
		checkBox.setSelected(Boolean.parseBoolean(val));
		val = props.getProperty("suppressInt");
		if(val == null || val.trim().equals(""))
		{
			val = "0";  //No I18N
		}
		textFields[1].setText(val);
		super.setActionProps(props);
		
	}
	
	protected void resetButtonActionPerformed(ActionEvent evt)
	{
		super.resetButtonActionPerformed(evt);
		checkBox.setSelected(true);
	}
	
	
	private void changeSuppressState(ItemEvent evt)
	{
		if(evt.getStateChange() == ItemEvent.SELECTED)
		{
			textFields[1].setEditable(false);			
		}
		else
		{
			textFields[1].setEditable(true);
		}
	}
	
	public String getHelpText()
	{
		return "javaui.filteractionui.suppressHelpText"; //No I18N
	}
	
	private JCheckBox checkBox;

}
