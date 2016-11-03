/* $Id: SendSmsPanel.java,v 1.1.4.2 2013/07/02 06:16:02 wesley Exp $ */

package com.webnms.nms.eventui;


import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.adventnet.nms.alertui.AlertApplet;
import com.adventnet.nms.eventui.EventBrowser;
import com.adventnet.nms.eventui.NmsFilterFrame;
import com.adventnet.nms.policyui.NmsPolicyPanel;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.NmsUiAPI;
import com.adventnet.nms.util.SMSProfileParser;
import com.webnms.nms.adminui.SMSConfigurator;


public class SendSmsPanel extends AbstractFilterActionPanel implements PropertyChangeListener,ActionListener
{

	private DefaultComboBoxModel model;
	private Hashtable<String,Properties> smsprofileList;

	public SendSmsPanel(AddFilterAction parent)
	{

		super(parent);
		smsprofileList=parent.getsmsprofileList();
		Set<String> keys = smsprofileList.keySet();
		for(String key : keys)
		{
			model.addElement(key);
		}
		if(SMSListBox.getItemCount() > 0)
		{
			SMSListBox.setSelectedIndex(0);
		}
	}

	private String[] keys = {"name","smsprofilename","message"}; //No I18N

	protected Properties getActionProps()
	{
		Properties props = new Properties();
		int size = keys.length;
		String value = null;
		int counter = 0;

		for(int i = 0 ;i < size ;i++)
		{
			if(i == 1)
			{
				value = parent.checkString(SMSListBox);
			}
			else if(i < 2)
			{
				value = parent.checkString(textFields[counter]);
				counter++;
			}
			else
			{
				value = textFields[counter].getText();
				counter++;
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
		return "javaui.filteractionui.smsHelpText"; //No I18N
	}




	@Override
	protected void initContentPane()
	{
		contentPane.setFont(NmsClientUtil.getFont("DIALOG")); //No I18N
		contentPane.addTab(NmsClientUtil.GetString("javaui.filteractionui.generalTab"),detailsPanel);
	}




	@Override
	protected void initDetailsPanel()
	{
		detailsPanel.setLayout(new GridBagLayout());
		String[] labels = {"javaui.filter.NotificationName","javaui.filteractionui.SMSProfile","javaui.filteractionui.smsConfigButtonTip","javaui.filter.Message"}; //No I18N

		textFields = new JTextComponent[2];
		int counter = 0;

		GridBagConstraints constraints = new GridBagConstraints();

		for(int i = 0;i < 4;i++)
		{
			JLabel labelComp = new JLabel(NmsClientUtil.GetString(labels[i]));
			labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N

			constraints.gridx = 0;
			constraints.gridy = i;
			constraints.weightx = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.insets = new java.awt.Insets(5, 10, 5, 20);
			if(i == 2)
			{
				constraints.gridwidth = 2;
				constraints.anchor = GridBagConstraints.WEST;
				constraints.insets = new java.awt.Insets(5, 10, 5, 0);
			}
			constraints.fill = GridBagConstraints.NONE;
			detailsPanel.add(labelComp,constraints);

			JComponent comp = null;
			constraints.weightx = 1;
			constraints.gridx = 1;
			constraints.gridwidth = 2;
			constraints.insets = new java.awt.Insets(5, 5, 5, 10);

			if(i == 1)
			{
				SMSListBox = new JComboBox();
				SMSListBox.setEditable(false);
				model = new DefaultComboBoxModel();
				SMSListBox.setModel(model);
				comp = SMSListBox;
				constraints.weightx = 0;
				constraints.fill = GridBagConstraints.HORIZONTAL;
			}
			else if(i == 2)
			{
				JPanel confPanel = new JPanel();
				confPanel.add(labelComp);

				JButton configSMSButton = new JButton();
				configSMSButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
				configSMSButton.setText(NmsClientUtil.GetString("javaui.filteractionui.configureSMSButton"));
				configSMSButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) 
					{
						configSMSButtonActionPerformed(evt);
					}
				});

				confPanel.add(configSMSButton);
				comp = confPanel;
				constraints.gridx = 0;
				constraints.gridwidth = 3;
				constraints.anchor = GridBagConstraints.EAST;
				constraints.fill = GridBagConstraints.NONE;
				constraints.insets = new java.awt.Insets(5, 0, 5, 5);
			}
			else if(i == 3)
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				JTextArea msgField = new JTextArea();
				msgField.setLineWrap(true);
				msgField.setRows(4);
				msgField.setWrapStyleWord(true);
				textFields[counter] = msgField;
				counter++;
				scrollPane.setViewportView(msgField);
				comp = scrollPane;
				constraints.fill = GridBagConstraints.BOTH;
				constraints.gridheight = 2;
				constraints.weighty = 1;
			}
			else
			{
				textFields[counter] = new JTextField(8);					
				comp = textFields[counter];
				counter++;
				constraints.fill = GridBagConstraints.HORIZONTAL;
			}
			comp.setName(NmsClientUtil.GetString(labels[i]));
			comp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N


			if(i == 3)
			{
				JButton propButton = new JButton();
				propButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
				{
					propButton.setIcon(AddFilterAction.getIcon("images/click.png"));//No i18n
					propButton.setToolTipText(NmsClientUtil.GetString("javaui.filteractionui.appendPropertyTip"));//No i18n
				}
				propButton.setActionCommand("propButton"+(i-2)); //No I18N
				propButton.setBorderPainted(false);
				propButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
				propButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) 
					{
						propButtonActionPerformed(evt);
					}
				});

				constraints.gridx = 2;
				constraints.gridwidth = 1;
				constraints.weightx = 0;
				constraints.weighty = 0;
				constraints.gridheight = 1;
				constraints.insets = new java.awt.Insets(5, 0, 5, 10);
				constraints.anchor = GridBagConstraints.NORTHWEST;
				constraints.fill = GridBagConstraints.NONE;
				detailsPanel.add(propButton,constraints);
				constraints.anchor = GridBagConstraints.WEST;
				constraints.weightx = 1;
				constraints.gridx = 1;
				constraints.fill = GridBagConstraints.HORIZONTAL;
				constraints.insets = new java.awt.Insets(5, 5, 5, 5);
			}

			detailsPanel.add(comp, constraints);
		}

	}

	private void propButtonActionPerformed(java.awt.event.ActionEvent evt)
	{


		JPopupMenu popMenu = new JPopupMenu();
		String[] keys = {"category","entity","message","text","severity","source","groupName"}; //No I18N


		ActionListener popMenuListener = new ActionListener(){
			public void actionPerformed(ActionEvent e)  
			{					
				String a = e.getActionCommand();
				a = textFields[1].getText() + " $"  + a;//No i18n
				textFields[1].setText(a);
			}
		};
		for(String str : keys)
		{
			JMenuItem menuItem = new JMenuItem(str);
			menuItem.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
			{
				menuItem.addActionListener(popMenuListener);
			}
			popMenu.add(menuItem);
		}

		popMenu.show((Component)evt.getSource(), 0,5 );

	}

	private void configSMSButtonActionPerformed(java.awt.event.ActionEvent evt)
	{
		if(NmsFilterFrame.standAlone)
		{
			smsprofileList=SMSProfileParser.getInstance().getAllsmsprofileDetails();
			SMSConfigurator.standAlone=true;
			SMSConfigurator SMSConfig = new SMSConfigurator((Frame)parent.getParent(),this,smsprofileList);
			NmsClientUtil.centerWindow(SMSConfig);
			SMSConfig.setVisible(true);
		}
		else
		{ 
			if(NmsFilterFrame.type.equals("AlertFilter"))
			{
				Runnable runnable=new Runnable() {
					public void run() 
					{
						AlertApplet eb = (AlertApplet)NmsUiAPI.getNmsPanel("Alerts");//No i18n
						SendSmsPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						eb.alsclient.getSmsDetails();
						SendSmsPanel.this.setCursor(Cursor.getDefaultCursor());
						smsprofileList=AlertApplet.sAlertFilterConfig.smsprofiles;
						SMSConfigurator SMSConfig = new SMSConfigurator((Frame)parent.getParent(),SendSmsPanel.this,smsprofileList);
						NmsClientUtil.centerWindow(SMSConfig);
						SMSConfig.setVisible(true);

					}
				};
				Thread t=new Thread(runnable);
				t.start();
			}
			else if(NmsFilterFrame.type.equals("EventFilter")){

				Runnable runnable=new Runnable() {
					public void run() 
					{	
						EventBrowser eb = (EventBrowser)NmsUiAPI.getNmsPanel("Events");//No i18n
						SendSmsPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						eb.getEASClient().getSmsDetails();
						SendSmsPanel.this.setCursor(Cursor.getDefaultCursor());
						smsprofileList=EventBrowser.sevtFilterConfig.smsprofiles;
						SMSConfigurator SMSConfig = new SMSConfigurator((Frame)parent.getParent(),SendSmsPanel.this,smsprofileList);
						NmsClientUtil.centerWindow(SMSConfig);
						SMSConfig.setVisible(true);

					}
				};
				Thread t1=new Thread(runnable);
				t1.start();

			}
			else{

				Runnable runnable=new Runnable() {
					public void run() 
					{	
						NmsPolicyPanel policyPanel = (NmsPolicyPanel)NmsUiAPI.getNmsPanel("Policy");//No i18n
						SendSmsPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						policyPanel.getPolicyClient().sendReqToGetNotifiers();
						SendSmsPanel.this.setCursor(Cursor.getDefaultCursor());
						smsprofileList=policyPanel.smsList;
						SMSConfigurator SMSConfig = new SMSConfigurator((Frame)parent.getParent(),SendSmsPanel.this,smsprofileList);
						NmsClientUtil.centerWindow(SMSConfig);
						SMSConfig.setVisible(true);

					}
				};
				Thread t1=new Thread(runnable);
				t1.start();

			}
		}
	}

	public void propertyChange(PropertyChangeEvent evt)
	{
		if(NmsFilterFrame.standAlone)
		{
			smsprofileList.clear();
			smsprofileList.putAll((Hashtable<String,Properties>) evt.getNewValue());
			model.removeAllElements();
			Set<String> keys = smsprofileList.keySet();
			for(String key : keys)
			{
				model.addElement(key);
			}
		}
		else
		{
			Properties newlist=(Properties) evt.getNewValue();
			Set<String> keys = newlist.stringPropertyNames();
			for(String key : keys)
			{
				if(key.equals("action"))
				{
					continue;
				}
				else
				{
					if(newlist.getProperty("action").equals("Update"))
					{
						continue;
					}
					else if(newlist.getProperty("action").equals("Insert"))
					{
						model.addElement(key);
					}
					else
					{
						model.removeElement(key);
					}
				}
			}
			parent.savesmsprofileList(newlist);
		}
	}



	protected void resetButtonActionPerformed(ActionEvent evt)
	{
		super.resetButtonActionPerformed(evt);
		if(SMSListBox.getItemCount() > 0)
		{
			SMSListBox.setSelectedIndex(0);
		}
	}

	@Override
	protected void setActionProps(Properties props)
	{

		String value = null;
		int size = keys.length;
		int counter = 0;
		for(int i=0;i<size;i++)
		{
			value = props.getProperty(keys[i]);
			if(i == 1)
			{
				SMSListBox.setSelectedItem(value);
			}
			else
			{
				textFields[counter].setText(value);
				counter++;
			}

		}

		super.setActionProps(props);
	}

	private JComboBox SMSListBox;


	@Override
	public void actionPerformed(ActionEvent e) 
	{

	}

}
