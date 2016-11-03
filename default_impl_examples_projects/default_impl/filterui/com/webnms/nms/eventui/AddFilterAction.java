/* $Id: AddFilterAction.java,v 1.2.4.3 2013/07/02 06:40:02 wesley Exp $
 * 
 * AddFilterAction.java
 *
 */

package com.webnms.nms.eventui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;

import com.adventnet.nms.alertui.AlertApplet;
import com.adventnet.nms.eventdb.FilterAction;
import com.adventnet.nms.eventdb.FilterCommand;
import com.adventnet.nms.eventdb.IncreaseSeverity;
import com.adventnet.nms.eventdb.NotifierList;
import com.adventnet.nms.eventdb.SendEmail;
import com.adventnet.nms.eventdb.SendTrap;
import com.adventnet.nms.eventdb.SendSms;
import com.adventnet.nms.eventdb.UserFilter;
import com.adventnet.nms.eventui.DefaultNmsFilterFrame;
import com.adventnet.nms.eventui.EventBrowser;
import com.adventnet.nms.eventui.NmsFilterFrame;
import com.adventnet.nms.policyui.NmsPolicyPanel;
import com.adventnet.nms.util.MailConfigParser;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.NmsUiAPI;
import com.adventnet.nms.util.SMSProfileParser;

/**
 *
 * @author  aravinds
 */
public class AddFilterAction extends javax.swing.JDialog 
{

	private Frame frm = null;
	DefaultMutableTreeNode selectedNode = null;
	public Hashtable<String,FilterAction> notifiers = new Hashtable<String,FilterAction>();
	public Vector<AbstractFilterActionPanel> tabPanelList = new Vector<AbstractFilterActionPanel>();
	private String policyStr = null;

	/** Creates new form AddFilterAction */
	public AddFilterAction(Frame parent, boolean modal) 
	{
		super(parent, modal);
		frm = parent;
		initComponents();
		populateDetails();
	}

	public AddFilterAction(Frame parent, boolean modal,String text) 
	{
		super(parent, modal);
		frm = parent;
		policyStr = text;
		initComponents();
		populateDetails();
	}

	private void initComponents() 
	{

		mainPanel = new javax.swing.JPanel();
		tabPanel = new javax.swing.JTabbedPane();	
		bottomPanel = new javax.swing.JPanel();
		horSep = new javax.swing.JSeparator();
		addButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		helpButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) 
			{
				formWindowClosing(evt);
			}
		});

		setTitle(NmsClientUtil.GetString("Add Action"));//No i18n
		
		tabPanel.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		AbstractFilterActionPanel filterPanel = new SendTrapActionPanel(this);
		tabPanelList.addElement(filterPanel);
		tabPanel.addTab(NmsClientUtil.GetString("javaui.filteractionui.trapTab"),filterPanel);

		filterPanel = new SendEmailActionPanel(this);
		tabPanelList.addElement(filterPanel);
		tabPanel.addTab(NmsClientUtil.GetString("javaui.filteractionui.emailTab"),filterPanel);
		
		filterPanel = new SendSmsPanel(this);
		tabPanelList.addElement(filterPanel);
		tabPanel.addTab(NmsClientUtil.GetString("javaui.filteractionui.smsTab"),filterPanel);

		filterPanel = new SuppressActionPanel(this);
		tabPanelList.addElement(filterPanel);
		tabPanel.addTab(NmsClientUtil.GetString("javaui.filteractionui.suppressTab"),filterPanel);

		filterPanel = new RunCommandActionPanel(this);
		tabPanelList.addElement(filterPanel);
		tabPanel.addTab(NmsClientUtil.GetString("javaui.filteractionui.runCommandTab"),filterPanel);

		filterPanel = new CustomFilterActionPanel(this);
		tabPanelList.addElement(filterPanel);
		tabPanel.addTab(NmsClientUtil.GetString("javaui.filteractionui.customTab"),filterPanel);

		filterPanel = new SetSeverityActionPanel(this);
		tabPanelList.addElement(filterPanel);
		if(policyStr != null && policyStr.equals("EscalationPolicy"))//No I18N
		{
			tabPanel.addTab(NmsClientUtil.GetString("javaui.filteractionui.setSeverityTab"),filterPanel);
		}

		addButton.setText(NmsClientUtil.GetString("javaui.filter.Add")); //No I18N
		addButton.setToolTipText(NmsClientUtil.GetString("javaui.filteractionui.addButtonTip")); //No I18N
		addButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				addButtonActionPerformed(evt);
			}
		});

		cancelButton.setText(NmsClientUtil.GetString("javaui.filter.Cancel")); //No I18N
		cancelButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				cancelButtonActionPerformed(evt);
			}
		});

		helpButton.setText(NmsClientUtil.GetString("javaui.filter.Help")); //No I18N
		helpButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		helpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				helpButtonActionPerformed(evt);
			}
		});

		bottomPanel.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.weightx = 1;
		constraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		constraints.insets = new java.awt.Insets(0, 5, 5, 5);
		bottomPanel.add(horSep,constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.weightx = 1;
		constraints.fill = java.awt.GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new java.awt.Insets(5, 17, 5, 17);
		bottomPanel.add(helpButton,constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new java.awt.Insets(5, 17, 5, 5);
		bottomPanel.add(addButton,constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.insets = new java.awt.Insets(5, 5, 5, 20);
		bottomPanel.add(cancelButton,constraints);

		mainPanel.setLayout(new GridBagLayout());

		JLabel topLabel = new JLabel();
		topLabel.setIcon(getIcon("images/add_action_top.jpg"));//No I18N
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weighty = 0;
		constraints.weightx = 1;
		constraints.fill = java.awt.GridBagConstraints.BOTH;
		constraints.insets = new java.awt.Insets(0, 0, 0, 0);
		mainPanel.add(topLabel,constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.fill = java.awt.GridBagConstraints.BOTH;
		constraints.insets = new java.awt.Insets(5, 5, 5, 5);
		mainPanel.add(tabPanel,constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.insets = new java.awt.Insets(5, 0, 5, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(bottomPanel,constraints);

		getContentPane().add(mainPanel);
		setPreferredSize(new Dimension(590,505));
		pack();
	}// </editor-fold>

	private void formWindowClosing(java.awt.event.WindowEvent evt) 
	{                                  
		cancelButtonActionPerformed(null);
	}                                 

	public void addButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{   
		int index = tabPanel.getSelectedIndex();
		String selectedAction = tabPanelList.get(index).getSelectedAction();
		if(selectedAction != null && !selectedAction.trim().equals(""))
		{
			DefaultTableModel model = (DefaultTableModel)((DefaultNmsFilterFrame)frm).getTableModel();
			int size  = model.getRowCount();

			for(int i = 0; i < size; i ++)
			{
				String key = (String)model.getValueAt(i, 0);
				if(selectedAction.equals(key))
				{	
					JOptionPane.showMessageDialog(this  ,NmsClientUtil.GetString("javaui.filter.alreadexist"), NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.ERROR_MESSAGE);//No i18n
					return;
				}
			}

			Vector<String> vec = new Vector<String>();
			vec.addElement(selectedAction);
			model.addRow(vec);
		}
		else
		{
			JOptionPane.showMessageDialog(this  ,NmsClientUtil.GetString("javaui.filteractionui.nofilterSelectedForAddition"), NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.ERROR_MESSAGE);//No i18n
			return;
		}
		cancelButtonActionPerformed(evt);
	}                                        

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{                                             
		checkAndSaveNotifiers();
		exitDialog();
	}

	private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{                                             
		int index = tabPanel.getSelectedIndex();
		AbstractFilterActionPanel filterPanel =  tabPanelList.get(index);
		JPopupMenu jpopMenu = new JPopupMenu();
		JLabel helpLabel = new JLabel();
		helpLabel.setSize(300,200);
		helpLabel.setText(NmsClientUtil.GetString(filterPanel.getHelpText()));
		jpopMenu.setLayout(new BorderLayout());
		jpopMenu.add(helpLabel, BorderLayout.CENTER);
		jpopMenu.show(helpButton,-60,-30);
	}

	public void populateDetails()
	{   

		if(!NmsFilterFrame.standAlone)
		{
			if(NmsFilterFrame.type.equals("AlertFilter"))
			{
				notifiers = AlertApplet.sAlertFilterConfig.notifiers;
			}
			else
			{
				notifiers = EventBrowser.sevtFilterConfig.notifiers;
			}
		}
		else
		{
			notifiers.putAll(NotifierList.getFilterActionList());
		}        

		Hashtable<Integer,Hashtable<String,FilterAction>> sepList = separateFilterActions();
		int size = tabPanelList.size();
		for(int i= 0;i<size;i++)
		{
			AbstractFilterActionPanel panel = tabPanelList.elementAt(i);
			panel.setActionList(sepList.get(i));
		}

		pack();
	}

	public Hashtable<Integer,Hashtable<String,FilterAction>> separateFilterActions()
	{
		Hashtable<Integer,Hashtable<String,FilterAction>> sepList = new Hashtable<Integer,Hashtable<String,FilterAction>>(); 
		for(Enumeration<String> enumerate  = notifiers.keys(); enumerate.hasMoreElements();)
		{
			String key = enumerate.nextElement();

			FilterAction action = notifiers.get(key);
			int type = getActionType(action);
			Hashtable<String,FilterAction> list = sepList.get(type);
			if(list == null)
			{
				list = new Hashtable<String,FilterAction>();
				sepList.put(type,list);
			}
			list.put(key, action);
		}
		return sepList;
	}

	/*
	 * returns the type of the filter action
	 */
	private int getActionType(FilterAction act)
	{
		if(act == null)
		{
			return -1;
		}

		String actionClassName = act.getClass().getName();

		if (actionClassName.equals("com.adventnet.nms.eventdb.SendTrap")) //No I18N
		{
			return 0;
		}
		else if (actionClassName.equals("com.adventnet.nms.eventdb.SendEmail")) //No I18N
		{
			return 1;
		}
		else if (actionClassName.equals("com.adventnet.nms.eventdb.SendSms")) //No I18N
		{
			return 2;
		}
		else if(actionClassName.equals("com.adventnet.nms.eventdb.FilterAction")) //No I18N
		{
			return 3;
		}
		else if (actionClassName.equals("com.adventnet.nms.eventdb.FilterCommand")) //No I18N
		{
			return 4;
		}
		else if (actionClassName.equals("com.adventnet.nms.eventdb.UserFilter")) //No I18N
		{
			return 5;
		}
		if (actionClassName.equals("com.adventnet.nms.eventdb.IncreaseSeverity"))
		{
			return 6;
		}

		return -1;
	}

	public FilterAction createAction(Properties props)
	{
		FilterAction action = null;
		int index = tabPanel.getSelectedIndex();
		switch(index)
		{
			case 0 :action = new SendTrap();
			break;
			case 1 :action = new SendEmail();
			break;
			case 2 :action = new SendSms();
            break;
			case 3 :action = new FilterAction();
			break;
			case 4 :action = new FilterCommand();
			break;
			case 5 :action = new UserFilter();
			break;
			case 6 :action = new IncreaseSeverity();
			break;
			
		}
		action.setProperties(props);
		return action;
	}

	public void checkAndSaveNotifiers()
	{

		boolean changed = false;
		notifiers.clear();		
		int size = tabPanelList.size();
		for(int i = 0 ;i<size;i++)
		{
			AbstractFilterActionPanel panel = tabPanelList.elementAt(i);
			if(panel.hasActionsChanged())
			{
				changed = true;
			}
			notifiers.putAll(panel.getActionList());			
		}

		if(changed)
		{
			if(!NmsFilterFrame.standAlone)
			{
				if(NmsFilterFrame.type.equals("EventFilter"))
				{
					EventBrowser evBrowser = (EventBrowser)NmsUiAPI.getNmsPanel("Events");//No i18n
					EventBrowser.sevtFilterConfig.notifiers = notifiers;
					evBrowser.getEASClient().saveNotifiers();
				}
				else
				{
					AlertApplet alertApp = (AlertApplet) NmsUiAPI.getNmsPanel("Alerts"); //No I18N
					AlertApplet.sAlertFilterConfig.notifiers = notifiers;
					alertApp.alsclient.saveNotifiers();
				}
			}
			else
			{
				NotifierList.setFilterActionList(notifiers);
				NotifierList.saveFilterActionsToFile();
			}
		}
		policyStr = null;
	}

	private void exitDialog()
	{
		setVisible(false);
		dispose();
	}

	public String checkString(JTextComponent textField)
	{ 
		String val = textField.getText().trim(); 
		if(val.trim().equals(""))
		{
			JOptionPane.showMessageDialog(this,MessageFormat.format(NmsClientUtil.GetString("javaui.filteractionui.emptyField"), new String[]{textField.getName()}),NmsClientUtil.GetString ("javaui.filteractionui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE); //No I18N
			textField.requestFocus();
			textField.selectAll();
			return null;
		}
		else
		{
			return val;
		}
	}

	public String checkString(JComboBox combo)
	{ 
		String val = (String)combo.getSelectedItem(); 
		if(val.trim().equals(""))
		{
			JOptionPane.showMessageDialog(this,MessageFormat.format(NmsClientUtil.GetString("javaui.filteractionui.emptyField"), new String[]{combo.getName()}),NmsClientUtil.GetString ("javaui.filteractionui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE); //No I18N
			combo.requestFocus();
			return null;
		}
		else
		{
			return val;
		}
	}

	public String checkNumber(JTextComponent textField)
	{
		String text = null;
		try
		{
			text = textField.getText().trim();
			int port = Integer.parseInt(text);
			if(port < 0)
			{
				JOptionPane.showMessageDialog(this,MessageFormat.format(NmsClientUtil.GetString("javaui.filteractionui.negativeValueFound"), new String[]{textField.getName()}),NmsClientUtil.GetString ("javaui.filteractionui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE); //No I18N
				textField.requestFocus();
				textField.selectAll();				
				return null;
			}
		}
		catch(NumberFormatException ne)
		{
			JOptionPane.showMessageDialog(this,MessageFormat.format(NmsClientUtil.GetString("javaui.filteractionui.numericValueExpected"), new String[]{textField.getName()}),NmsClientUtil.GetString ("javaui.filteractionui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE); //No I18N
			textField.requestFocus();
			textField.selectAll();				
			return null;
		}		
		return text;
	}

	public static ImageIcon getIcon(String image)
	{
		if(image == null)
		{
			return null;
		}
		
		ImageIcon imageIcon = null;
		try
		{
			if(!NmsFilterFrame.standAlone)
			{
				image = NmsClientUtil.applet.getDocumentBase() + "../" + image;//No I18N
				imageIcon = NmsClientUtil.getImageIcon(image);
			}
			else
			{
				imageIcon = new ImageIcon(image);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			return null;
		}   
		return imageIcon;
	}
	
	public Hashtable<String,Properties> getSmtpAccountList()
	{
		Hashtable<String,Properties> smtpAccountList = null;
		if(!NmsFilterFrame.standAlone)
		{
			if(NmsFilterFrame.type.equals("AlertFilter"))
			{
				smtpAccountList= AlertApplet.sAlertFilterConfig.smtpAccounts;
			}
			else if(NmsFilterFrame.type.equals("EventFilter")) 
			{
				smtpAccountList= EventBrowser.sevtFilterConfig.smtpAccounts;
			}
			else
			{
				smtpAccountList = NmsPolicyPanel.smtpList;
			}
		}
		else
		{
			smtpAccountList = MailConfigParser.getInstance().getAllMailServerDetails();
		} 
		return smtpAccountList;
	}
	
	public boolean saveSmtpAccountList(Hashtable<String,Properties> smtpAccountList)
	{
		if(NmsFilterFrame.standAlone)
		{
			return MailConfigParser.getInstance().setMailServerDetails(smtpAccountList);
		}
		else
		{
			EventBrowser eb = (EventBrowser)NmsUiAPI.getNmsPanel("Events");//No i18n
            eb.getEASClient().saveMailSettings(smtpAccountList);
            return true;
		}
	}
	
	//sms notification
	public Hashtable<String,Properties> getsmsprofileList()
	{
		Hashtable<String,Properties> smsprofileList = null;
		if(!NmsFilterFrame.standAlone)
		{
			if(NmsFilterFrame.type.equals("AlertFilter"))
			{
				smsprofileList= AlertApplet.sAlertFilterConfig.smsprofiles;
			}
			else if(NmsFilterFrame.type.equals("EventFilter"))
			{
				smsprofileList= EventBrowser.sevtFilterConfig.smsprofiles;
			}
			else
			{
				smsprofileList= NmsPolicyPanel.smsList;
			}
		}
		else
		{
			SMSProfileParser.standAlone=true;
			smsprofileList = SMSProfileParser.getInstance().getAllsmsprofileDetails();
		}
		return smsprofileList;
	}

	public boolean savesmsprofileList(Properties props)
	{
		if(NmsFilterFrame.type.equals("AlertFilter"))
		{
			AlertApplet eb = (AlertApplet)NmsUiAPI.getNmsPanel("Alerts");//No i18n
			eb.alsclient.saveSmsSettings(props);
			return true;
		}
		else
		{
			EventBrowser eb = (EventBrowser)NmsUiAPI.getNmsPanel("Events");//No i18n
			eb.getEASClient().saveSmsSettings(props);
			return true;
		}
	}


	// Variables declaration - do not modify
	private javax.swing.JPanel bottomPanel;
	private javax.swing.JButton cancelButton;
	private javax.swing.JButton helpButton;
	private javax.swing.JSeparator horSep;
	private javax.swing.JPanel mainPanel;
	private javax.swing.JButton addButton;
	public javax.swing.JTabbedPane tabPanel;
	// End of variables declaration

}
