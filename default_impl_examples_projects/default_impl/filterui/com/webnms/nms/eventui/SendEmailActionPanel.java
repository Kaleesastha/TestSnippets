/* $Id: SendEmailActionPanel.java,v 1.3.4.2.2.1 2014/02/26 12:01:12 venkatramanan Exp $
 * 
 * SendEmailActionPanel.java
 *
 */

package com.webnms.nms.eventui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
import com.adventnet.nms.util.MailConfigParser;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.NmsUiAPI;
import com.adventnet.snmp.ui.SasFileDialog;
import com.webnms.nms.adminui.SMTPConfigurator;


public class SendEmailActionPanel extends AbstractFilterActionPanel implements PropertyChangeListener,ActionListener
{

	private DefaultComboBoxModel model;
	private Hashtable<String,Properties> smtpAccountList;
	private JFileChooser chooser;
	private SasFileDialog attachDialog;
	private String fileUrl;

	public SendEmailActionPanel(AddFilterAction parent)
	{
		super(parent);
		try
		{
			if(!NmsFilterFrame.standAlone)
			{
				attachDialog = new SasFileDialog(NmsClientUtil.applet);
				attachDialog.addActionListener(this);
			}
			else
			{
				chooser =  new JFileChooser(com.adventnet.nms.util.PureUtils.rootDir);
				chooser.addActionListener(this);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		smtpAccountList = parent.getSmtpAccountList();
				
		Set<String> keys = smtpAccountList.keySet();
		for(String key : keys)
		{
			model.addElement(key);
		}
		if(smtpListBox.getItemCount() > 0)
		{
			smtpListBox.setSelectedIndex(0);
		}
	}

	private String[] keys = {"name","smtpAccountName","subject","message","attachedfileName"}; //No I18N

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
				value = parent.checkString(smtpListBox);
			}
			else if(i < 3)
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
		return "javaui.filteractionui.emailHelpText"; //No I18N
	}

	@Override
	protected void initDetailsPanel()
	{
		detailsPanel.setLayout(new GridBagLayout());
		String[] labels = {"javaui.filter.NotificationName","javaui.filteractionui.SMTPAccount","javaui.filteractionui.smtpConfigButtonTip","javaui.filter.Subject","javaui.filter.Message","javaui.filter.FileAttachment"}; //No I18N

		textFields = new JTextComponent[4];
		int counter = 0;

		GridBagConstraints constraints = new GridBagConstraints();

		for(int i = 0;i < 6;i++)
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
				smtpListBox = new JComboBox();
				smtpListBox.setEditable(false);
				model = new DefaultComboBoxModel();
				smtpListBox.setModel(model);
				comp = smtpListBox;
				constraints.weightx = 0;
				constraints.fill = GridBagConstraints.HORIZONTAL;
			}
			else if(i == 2)
			{
				JPanel confPanel = new JPanel();
				confPanel.add(labelComp);
			
				JButton configSMTPButton = new JButton();
				configSMTPButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
				configSMTPButton.setText(NmsClientUtil.GetString("javaui.filteractionui.configureSMTPButton"));
				configSMTPButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) 
					{
						configSMTPButtonActionPerformed(evt);
					}
				});
				
				confPanel.add(configSMTPButton);
				comp = confPanel;
				constraints.gridx = 0;
				constraints.gridwidth = 3;
				constraints.anchor = GridBagConstraints.EAST;
				constraints.fill = GridBagConstraints.NONE;
				constraints.insets = new java.awt.Insets(5, 0, 5, 5);
			}
			else if(i == 4)
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setMinimumSize(new Dimension(50,70));
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
			
			
			if(i >= 3)
			{
				JButton propButton = new JButton();
				propButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
				if(i == 5)
				{
					propButton.setText(NmsClientUtil.GetString("javaui.filteractionui.browseButton"));//No i18n 
				}
				else
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
		String command = evt.getActionCommand();
		if(command.equals("propButton3"))
		{
			if(NmsFilterFrame.standAlone)
			{
				chooser.setApproveButtonText(NmsClientUtil.GetString("javaui.filteractionui.fileChooserApproveButton"));//No i18n
				chooser.showOpenDialog(this); 
			}
			else
			{
				attachDialog.init();
				attachDialog.setDirectory(""); //No I18N
				attachDialog.setVisible(true);
				fileChooser = new JDialog(this.parent,true);
				fileChooser.getContentPane().setLayout(new BorderLayout());
				fileChooser.getContentPane().add(attachDialog);
				fileChooser.setPreferredSize(new Dimension(450,350));
				fileChooser.setTitle(NmsClientUtil.GetString(" File Attachment "));//No i18n
				fileChooser.pack();
				NmsClientUtil.centerWindow(fileChooser);
				fileChooser.setVisible(true);
			}
		}
		else
		{
			JPopupMenu popMenu = new JPopupMenu();
			String[] keys = {"category","entity","text","severity","source","groupName"}; //No I18N

			ActionListener popMenuListner1 = new ActionListener(){
				public void actionPerformed(ActionEvent e)  
				{					
					String a = e.getActionCommand();
					a = textFields[1].getText() + " $"  + a;//No i18n
					textFields[1].setText(a);
				}
			}; 

			ActionListener popMenuListner2 = new ActionListener(){
				public void actionPerformed(ActionEvent e)  
				{					
					String a = e.getActionCommand();
					a = textFields[2].getText() + " $"  + a;//No i18n
					textFields[2].setText(a);
				}
			};
			for(String str : keys)
			{
				JMenuItem menuItem = new JMenuItem(str);
				menuItem.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
				if(command.equals("propButton1"))
				{
					menuItem.addActionListener(popMenuListner1);
				}
				else
				{
					menuItem.addActionListener(popMenuListner2);
				}
				popMenu.add(menuItem);
			}

			popMenu.show((Component)evt.getSource(), 0,5 );
			
		}
	}


	private void configSMTPButtonActionPerformed(java.awt.event.ActionEvent evt)
	{
		SMTPConfigurator smtpConfig = new SMTPConfigurator((Frame)parent.getParent(),this,smtpAccountList);
		NmsClientUtil.centerWindow(smtpConfig);
		smtpConfig.setVisible(true);
	}

	public void propertyChange(PropertyChangeEvent evt)
	{
		smtpAccountList.clear();
		smtpAccountList.putAll((Hashtable<String,Properties>) evt.getNewValue());
		model.removeAllElements();
		Set<String> keys = smtpAccountList.keySet();
		for(String key : keys)
		{
			model.addElement(key);
		}
		
		parent.saveSmtpAccountList(smtpAccountList);
		
	}

	public void actionPerformed(java.awt.event.ActionEvent e) 
	{
		if((textFields[3].getText()).equals(""))
		{
			fileUrl = null;
		}
		else
		{
			fileUrl = textFields[3].getText();
		}

		if((e.getActionCommand()).equalsIgnoreCase("Open"))
		{
			if(!NmsFilterFrame.standAlone)
			{
				if(fileUrl == null || fileUrl.equalsIgnoreCase(""))
				{
					String tempfileUrl = attachDialog.getSelectedFileUrl();
					fileUrl = tempfileUrl.substring(1);
				}
				else
				{
					String tempfileUrl = attachDialog.getSelectedFileUrl();
					String subfileUrl =  tempfileUrl.substring(1);
					fileUrl = fileUrl+','+subfileUrl; 
				}
				fileChooser.setVisible(false);
				fileChooser.dispose();
				textFields[3].setText(fileUrl);
			}
		}
		if((e.getActionCommand()).equalsIgnoreCase("Close"))
		{
			fileChooser.setVisible(false);
			fileChooser.dispose();
		}
		if((e.getActionCommand()).equalsIgnoreCase("ApproveSelection"))
		{
			String rtDir = com.adventnet.nms.util.PureUtils.rootDir;
			int len = rtDir.length();
			try {
				File selectedFile = chooser.getSelectedFile();
				if(fileUrl == null || fileUrl.equalsIgnoreCase(""))
				{
					String tmpfileUrl = selectedFile.getCanonicalPath();
					fileUrl = tmpfileUrl.substring(len);
				}
				else
				{

					String tempfileUrl = selectedFile.getCanonicalPath();
					tempfileUrl = tempfileUrl.substring(len);
					fileUrl = fileUrl+','+tempfileUrl;
				}
			}
			catch(Exception ae) {
				ae.printStackTrace();
			}

			textFields[3].setText(fileUrl);
		}
	}   

	protected void resetButtonActionPerformed(ActionEvent evt)
	{
		super.resetButtonActionPerformed(evt);
		if(smtpListBox.getItemCount() > 0)
		{
			smtpListBox.setSelectedIndex(0);
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
				smtpListBox.setSelectedItem(value);
			}
			else
			{
				textFields[counter].setText(value);
				counter++;
			}

		}
		
		super.setActionProps(props);
	}

	private JComboBox smtpListBox;
	private JDialog fileChooser;
	
}
