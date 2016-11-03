/* $Id: CustomFilterActionPanel.java,v 1.2 2010/10/29 13:45:50 swaminathap Exp $
 * 
 * CustomFilterActionPanel.java
 *
 */

package com.webnms.nms.eventui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.adventnet.nms.util.NmsClientUtil;


public class CustomFilterActionPanel extends AbstractFilterActionPanel
{
	public CustomFilterActionPanel(AddFilterAction parent)
	{
		super(parent);
	}

	@Override
	protected Properties getActionProps()
	{
		Properties props = new Properties();
		String value = parent.checkString(textFields[0]);
		if(value == null)
		{
			return null;
		}
		props.setProperty("name",value);
        
		value = parent.checkString(textFields[1]);
		if(value == null)
		{
			return null;
		}		
		props.setProperty("userclass",value);
		props.putAll(super.getActionProps());
		return props;
	}

	@Override
	public String getHelpText()
	{
		return "javaui.filteractionui.customHelpText"; //No I18N
	}

	@Override
	protected void initDetailsPanel()
	{
		detailsPanel.setLayout(new GridBagLayout());

		String[] labels = new String[]{"javaui.filter.NotificationName","javaui.filter.ProgramName"}; //No I18N

		textFields = new JTextField[2];

		GridBagConstraints constraints = new GridBagConstraints();

		for(int i = 0;i < 2;i++)
		{
			JLabel labelComp = new JLabel(NmsClientUtil.GetString(labels[i]));
			labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
			
			constraints.gridx = 0;
			constraints.gridy = i;
			constraints.weightx = 0;
			constraints.fill = GridBagConstraints.NONE;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.insets = new java.awt.Insets(10, 10, 5, 20);
			detailsPanel.add(labelComp,constraints);

			textFields[i] = new JTextField(8);
			textFields[i].setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
			textFields[i].setName(NmsClientUtil.GetString(labels[i]));
			constraints.insets = new java.awt.Insets(5, 5, 5, 10);
			constraints.gridx = 1;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			detailsPanel.add(textFields[i], constraints);
		}


	}

	@Override
	protected void setActionProps(Properties props)
	{
		String value = props.getProperty("name");
        textFields[0].setText(value);
		value = props.getProperty("userclass");
		textFields[1].setText(value);
		super.setActionProps(props);

	}

}
