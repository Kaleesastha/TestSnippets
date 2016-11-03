/* $Id: RunCommandActionPanel.java,v 1.2 2010/10/29 13:45:50 swaminathap Exp $
 * 
 * RunCommandActionPanel.java
 *
 */

package com.webnms.nms.eventui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.Properties;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.adventnet.nms.util.NmsClientUtil;


public class RunCommandActionPanel extends AbstractFilterActionPanel
{
	public RunCommandActionPanel(AddFilterAction parent)
	{
		super(parent);
	}

	private String[] keys = {"name","command","timeout"}; //No I18N
	
	@Override
	protected Properties getActionProps()
	{
		Properties props = new Properties();
		
		boolean val = checkBoxes[0].isSelected();
		props.setProperty("append",String.valueOf(val));

		val = checkBoxes[1].isSelected();
		props.setProperty("errappend",String.valueOf(val));

		String value = null;
		
		int size = keys.length;
		for(int i = 0; i< size; i++)
		{
			if(i == 3)
			{
				value = textFields[i].getText();
			}
			else
			{
				value = parent.checkString(textFields[i]);
			}
			if(value == null)
			{
				return null;
			}
			props.setProperty(keys[i],value);
		}
		
		props.putAll(super.getActionProps());
		return props;
        
	}

	@Override
	public String getHelpText()
	{
		return "javaui.filteractionui.runCommandHelpText";   //No I18N
	}

	@Override
	protected void initDetailsPanel()
	{
		detailsPanel.setLayout(new GridBagLayout());

		String[] labels = {"javaui.filter.NotificationName","javaui.filter.SystemCommand","javaui.filter.appendOutput","javaui.filter.appendError","javaui.filter.AbortAfter","javaui.filter.Seconds"}; //No I18N

		textFields = new JTextField[3];
		checkBoxes = new JCheckBox[2];

		int textFieldCounter = 0;
		int checkBoxCounter = 0;

		GridBagConstraints constraints = new GridBagConstraints();

		for(int i = 0;i < 5;i++)
		{
			JLabel labelComp = new JLabel(NmsClientUtil.GetString(labels[i]));
			labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
			
			constraints.gridx = 0;
			constraints.gridy = i;
			constraints.weightx = 0;
			constraints.gridwidth = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.fill = GridBagConstraints.NONE;
			constraints.insets = new java.awt.Insets(5, 10, 5, 20);
			

			JComponent comp = null;
			
			if(i == 2 || i == 3)
			{
				constraints.gridwidth = 2;
				detailsPanel.add(labelComp,constraints);
				
				checkBoxes[checkBoxCounter] = new JCheckBox();
				comp = checkBoxes[checkBoxCounter];
				checkBoxCounter++;
				constraints.insets = new java.awt.Insets(5, 0, 5, 5);
				constraints.weightx = 0;
				constraints.anchor = GridBagConstraints.CENTER;
				constraints.fill = GridBagConstraints.NONE;				
			}
			else
			{
				detailsPanel.add(labelComp,constraints);
				
				textFields[textFieldCounter] = new JTextField(8);					
				textFields[textFieldCounter].setName(NmsClientUtil.GetString(labels[i]));
				comp = textFields[textFieldCounter];
				textFieldCounter ++;
				constraints.insets = new java.awt.Insets(5, 5, 5, 10);
				constraints.weightx = 1;
				constraints.fill = GridBagConstraints.HORIZONTAL;
			}
			
			comp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
			constraints.gridx = 1;
			constraints.gridwidth = 2;
						
			if(i == 4)
			{
				constraints.gridwidth = 1;
				detailsPanel.add(comp, constraints);
				
				labelComp = new JLabel(NmsClientUtil.GetString(labels[5]));
				labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N

				constraints.gridx = 2;
				constraints.gridy = i;
				constraints.weightx = 0;
				constraints.anchor = GridBagConstraints.WEST;
				constraints.fill = GridBagConstraints.NONE;
				constraints.insets = new java.awt.Insets(5, 0, 5, 10);
				detailsPanel.add(labelComp,constraints);

			}
			else
			{
				detailsPanel.add(comp, constraints);
			}
		}

	}
	
	protected void resetButtonActionPerformed(ActionEvent evt)
	{
		super.resetButtonActionPerformed(evt);
		checkBoxes[0].setSelected(false);
		checkBoxes[1].setSelected(false);
		textFields[2].setText("60"); //No I18N
	}

	@Override
	protected void setActionProps(Properties props)
	{
		String value = null;
        int size = keys.length;
        for(int i=0;i<size;i++)
        {
        	value = props.getProperty(keys[i]);
        	if(i == 2 && (value == null ||value.trim().equals("")))
        	{
        		value = "60"; //No I18N
        	}        	
        	textFields[i].setText(value);        	
        }
        
        value = props.getProperty("append");
        checkBoxes[0].setSelected(Boolean.parseBoolean(value));
        value = props.getProperty("errappend");
        checkBoxes[1].setSelected(Boolean.parseBoolean(value));
        super.setActionProps(props);
	}
	
	private JCheckBox[] checkBoxes;

}
