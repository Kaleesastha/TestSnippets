/* $Id: SMSConfigurator.java,v 1.1.4.3 2013/08/08 09:28:01 krishnakumar.e Exp $ */

package com.webnms.nms.adminui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.adventnet.nms.alertui.AlertApplet;
import com.adventnet.nms.eventui.EventBrowser;
import com.adventnet.nms.eventui.NmsFilterFrame;
import com.adventnet.nms.policyui.NmsPolicyPanel;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.NmsUiAPI;
import com.adventnet.nms.util.SMSProfileParser;


/**
 * This Dialog is used to configure SMS profiles.We can add,modify and delete SMS Profiles. 
 *
 */
public class SMSConfigurator extends JDialog
{

	public static boolean standAlone = false;
	private static Hashtable<String,Properties> smsprofileDetails = new Hashtable<String,Properties>();
	private PropertyChangeListener listener;
	private boolean propsChanged = false;
	private DefaultListModel model;
	String accName=null;
	String recepient=null;

	/** Creates new form AddAction */
	public SMSConfigurator(Frame parent, boolean modal) 
	{
		super(parent, modal);
		initComponents();
	}

	/** Creates new form SmsConfigurator */
	public SMSConfigurator(Frame parent,Hashtable<String,Properties> accDetails) 
	{
		this(parent,true);	
		if(accDetails != null)
		{
			if(standAlone)
			{
				smsprofileDetails.putAll(accDetails);
			}
			else
			{
			smsprofileDetails.clear();
			smsprofileDetails.putAll(accDetails);
			}
		}
		Set<String> listData = smsprofileDetails.keySet();
		for(String name : listData)
		{
			model.addElement(name);
		}


	}

	public SMSConfigurator(Frame parent,PropertyChangeListener listener,Hashtable<String,Properties> accDetails)
	{

		this(parent,accDetails);
		this.listener = listener;

	}


	private void initComponents() 
	{
		mainPanel = new javax.swing.JPanel();
		separator = new javax.swing.JSeparator();
		bottomPanel = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		helpButton = new javax.swing.JButton();
		detailsPanel = new javax.swing.JPanel();
		buttonPanel = new javax.swing.JPanel();
		addButton = new javax.swing.JButton();
		editButton = new javax.swing.JButton();
		deleteButton = new javax.swing.JButton();
		resetButton = new javax.swing.JButton();
		listPanel = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		accountList = new javax.swing.JList();
		propsPanel = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				formWindowClosing(evt);
			}
		});

		setTitle(NmsClientUtil.GetString("javaui.smsconfigui.title"));

		okButton.setText(NmsClientUtil.GetString("javaui.smsconfigui.okButton"));
		okButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		okButton.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				okButtonActionPerformed(evt);
			}
		});

		cancelButton.setText(NmsClientUtil.GetString("javaui.smsconfigui.cancelButton"));
		cancelButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		cancelButton.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				cancelButtonActionPerformed(evt);
			}
		});

		helpButton.setText(NmsClientUtil.GetString("javaui.smsconfigui.helpButton"));
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
		bottomPanel.add(separator,constraints);

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
		bottomPanel.add(okButton,constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.insets = new java.awt.Insets(5, 5, 5, 20);
		bottomPanel.add(cancelButton,constraints);

		propsPanel.setBorder(BorderFactory.createTitledBorder (null, NmsClientUtil.GetString("javaui.smsconfigui.detailsTitle"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, NmsClientUtil.getFont("DIALOG")));
		propsPanel.setLayout(new GridBagLayout());

		String[] labels = new String[]{"javaui.smsconfigui.profileName","javaui.smsconfigui.recipients"};
		textFields = new JTextField[2];

		int textFieldCounter = 0;


		for(int i = 0;i < 2;i++)
		{
			JLabel labelComp = new JLabel(NmsClientUtil.GetString(labels[i]));
			labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
			constraints.gridx = 0;
			constraints.gridy = i;
			constraints.weightx = 0;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.fill = GridBagConstraints.NONE;
			constraints.insets = new java.awt.Insets(5, 10, 5, 15);
			propsPanel.add(labelComp,constraints);

			JComponent comp = null;
			if(i<2)
			{
				textFields[textFieldCounter] = new JTextField(i);
			}
			textFields[textFieldCounter].setName(NmsClientUtil.GetString(labels[i]));
			comp = textFields[textFieldCounter];
			textFieldCounter ++;
			constraints.insets = new java.awt.Insets(5, 5, 5, 10);
			constraints.gridx = 1;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.anchor = GridBagConstraints.WEST;
			propsPanel.add(comp, constraints);

		}


		addButton.setText(NmsClientUtil.GetString("javaui.smsconfigui.addButton"));
		addButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		addButton.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				addButtonActionPerformed(evt);

			}
		});

		editButton.setText(NmsClientUtil.GetString("javaui.smsconfigui.editButton"));
		editButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		editButton.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				editButtonActionPerformed(evt);
			}
		});

		deleteButton.setText(NmsClientUtil.GetString("javaui.smsconfigui.deleteButton"));
		deleteButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		deleteButton.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				deleteButtonActionPerformed(evt);
			}
		});

		resetButton.setText(NmsClientUtil.GetString("javaui.smsconfigui.resetButton"));
		resetButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		resetButton.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				resetButtonActionPerformed(evt);
			}
		});

		buttonPanel.setLayout(new GridLayout(1,4,10,5));
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(resetButton);

		model = new DefaultListModel();
		accountList.setModel(model);
		accountList.setFont(NmsClientUtil.getFont("DIALOG"));
		accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		accountList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e)
			{
				handleListSelection(e);				
			}
		});

		jScrollPane1.setViewportView(accountList);
		jScrollPane1.setBorder(BorderFactory.createEmptyBorder(8,5,5,5));

		listPanel.setLayout(new GridLayout(1,1));
		jScrollPane1.setMinimumSize(new Dimension(190,200));
		jScrollPane1.setPreferredSize(new Dimension(190,200));
		listPanel.add(jScrollPane1);
		listPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, NmsClientUtil.GetString("javaui.smsconfigui.listTitle"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, NmsClientUtil.getFont("DIALOG")));

		detailsPanel.setLayout(new GridBagLayout());

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 2;
		constraints.weighty = 1;
		constraints.weightx = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.insets = new java.awt.Insets(5, 7, 2, 7);
		detailsPanel.add(listPanel,constraints);

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridheight = 1;
		constraints.fill = java.awt.GridBagConstraints.BOTH;
		constraints.insets = new java.awt.Insets(5, 5, 5, 5);
		detailsPanel.add(propsPanel,constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weighty = 0;
		constraints.weightx = 0;
		constraints.fill = java.awt.GridBagConstraints.NONE;
		constraints.insets = new java.awt.Insets(15, 7, 2, 10);
		detailsPanel.add(buttonPanel,constraints);

		mainPanel.setLayout(new GridBagLayout());

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.fill = java.awt.GridBagConstraints.BOTH;
		constraints.insets = new java.awt.Insets(5, 5, 5, 5);
		mainPanel.add(detailsPanel,constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.insets = new java.awt.Insets(5, 0, 5, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(bottomPanel,constraints);

		getContentPane().add(mainPanel);
		shortCutKeyInit();

		resetButtonActionPerformed(null);
		setPreferredSize(new Dimension(650,440));
		pack();
	}

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{
		handleFrameClosing("enter");
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{
		handleFrameClosing("escape");
	}

	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{
		Properties props = new Properties();
		String name = textFields[0].getText();
		String value = textFields[1].getText();
		StringTokenizer toToken = new StringTokenizer(value, ",");
		while(toToken.hasMoreTokens())
		{
			String toNumb = toToken.nextToken();
			if((!toNumb.matches("\\d+")) || toNumb.length()>20)
			{
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.smsconfigui.errorinrecipient"),NmsClientUtil.GetString ("javaui.smsconfigui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE);
				textFields[1].requestFocus();
				textFields[1].selectAll();	
				return;
			}
		}

		if(name.equals("")|| value.equals(""))
		{
			JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.smsconfigui.emptyfields"),NmsClientUtil.GetString ("javaui.smsconfigui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(smsprofileDetails.containsKey(name))
		{
			JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.smsconfigui.profileAlreadyExists"),NmsClientUtil.GetString ("javaui.smsconfigui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE);
			textFields[0].requestFocus();
			textFields[0].selectAll();				
			return;
		}
		else{
			props.put(name, value);
			if(standAlone)
			{
				smsprofileDetails.put(name,props);//for standalone
			}
			props.put("action","Insert");
			model.addElement(name);
			propsChanged = true;
			persistSMSProfile(props);
			resetButtonActionPerformed(null);//to clear the fields after adding
		}

	}

	private void resetButtonActionPerformed(ActionEvent evt)
	{
		for(int i = 0;i<2;i++)
		{
			textFields[i].setText("");
		}
		textFields[0].setEditable(true);
		accountList.clearSelection();
		addButton.setEnabled(true);
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
	}

	private void editButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{
		Properties editprops=new Properties();
		String name = textFields[0].getText();
		String value = textFields[1].getText();
		Properties props = smsprofileDetails.get(name);
		StringTokenizer toToken = new StringTokenizer(value, ",");
		while(toToken.hasMoreTokens())
		{
			String toNumb = toToken.nextToken();
			if((!toNumb.matches("\\d+")) || toNumb.length()>20)
			{
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.smsconfigui.errorinrecipient"),NmsClientUtil.GetString ("javaui.smsconfigui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE);
				textFields[1].requestFocus();
				textFields[1].selectAll();	
				return;
			}
		}

		if(props.get(name).equals(value))
		{
			JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.smsconfigui.noChangeInDetails"),NmsClientUtil.GetString ("javaui.smsconfigui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		editprops.put(name, value);
		if(standAlone)
		{
		smsprofileDetails.put(name,editprops);//for standalone
		}
		editprops.put("action","Update");
		resetButtonActionPerformed(null);//to clear the fields after editing
		propsChanged = true;
		persistSMSProfile(editprops);

	}



	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{
		int del = JOptionPane.showConfirmDialog(this,NmsClientUtil.GetString("javaui.smsconfigui.deleteAccount"), NmsClientUtil.GetString("javaui.smsconfigui.confirmDialog"), JOptionPane.YES_NO_OPTION);	
		if(del == JOptionPane.NO_OPTION)
		{
			JOptionPane.showMessageDialog(this, NmsClientUtil.GetString("javaui.smsconfigui.canceldelete"),NmsClientUtil.GetString("javaui.smsconfigui.confirmDialog"), JOptionPane.INFORMATION_MESSAGE);
			return;
		} 


		String name = (String)accountList.getSelectedValue();
		model.removeElement(name);
		Properties newProps=new Properties();
		newProps.put(name, textFields[1].getText());
		newProps.put("action","Delete");
		if(standAlone)
		{
			smsprofileDetails.remove(name);//for standalone
		}
		propsChanged = true;
		resetButtonActionPerformed(null);
		persistSMSProfile(newProps);
	}

	private void handleListSelection(ListSelectionEvent evt)
	{
		String name = (String) accountList.getSelectedValue();
		if(name == null)
		{
			return;
		}
		if(standAlone)
		{
			Properties props = smsprofileDetails.get(name);
			textFields[0].setText(name);
			textFields[0].setEditable(false);
			String value=props.getProperty(name);
			textFields[1].setText(value);
			textFields[1].setEditable(true);
			addButton.setEnabled(false);
			editButton.setEnabled(true);
			deleteButton.setEnabled(true);
		}
		else
		{
			if(NmsFilterFrame.type.equals("AlertFilter"))
			{
				Runnable runnable=new Runnable() {
					public void run() 
					{
						AlertApplet eb = (AlertApplet)NmsUiAPI.getNmsPanel("Alerts");//No i18n
						SMSConfigurator.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						eb.alsclient.getSmsDetails();
						SMSConfigurator.this.setCursor(Cursor.getDefaultCursor());
						smsprofileDetails=AlertApplet.sAlertFilterConfig.smsprofiles;
						String name = (String) accountList.getSelectedValue();
						Properties props = smsprofileDetails.get(name);
						textFields[0].setText(name);
						textFields[0].setEditable(false);
						String value=props.getProperty(name);
						textFields[1].setText(value);
						textFields[1].setEditable(true);
						addButton.setEnabled(false);
						editButton.setEnabled(true);
						deleteButton.setEnabled(true);
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
						SMSConfigurator.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						eb.getEASClient().getSmsDetails();
						SMSConfigurator.this.setCursor(Cursor.getDefaultCursor());
						smsprofileDetails=EventBrowser.sevtFilterConfig.smsprofiles;
						String name = (String) accountList.getSelectedValue();
						Properties props = smsprofileDetails.get(name);
						textFields[0].setText(name);
						textFields[0].setEditable(false);
						String value=props.getProperty(name);
						textFields[1].setText(value);
						textFields[1].setEditable(true);
						addButton.setEnabled(false);
						editButton.setEnabled(true);
						deleteButton.setEnabled(true);
					}
				};
				Thread t1=new Thread(runnable);
				t1.start();
			}
			else{
				Runnable runnable=new Runnable() {
					public void run() 
					{	
						NmsPolicyPanel npp = (NmsPolicyPanel)NmsUiAPI.getNmsPanel("Policy");//No i18n
						SMSConfigurator.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						npp.getPolicyClient().sendReqToGetNotifiers();
						SMSConfigurator.this.setCursor(Cursor.getDefaultCursor());
						smsprofileDetails=npp.smsList;
						String name = (String) accountList.getSelectedValue();
						Properties props = smsprofileDetails.get(name);
						textFields[0].setText(name);
						textFields[0].setEditable(false);
						String value=props.getProperty(name);
						textFields[1].setText(value);
						textFields[1].setEditable(true);
						addButton.setEnabled(false);
						editButton.setEnabled(true);
						deleteButton.setEnabled(true);
					}
				};
				Thread t1=new Thread(runnable);
				t1.start();
			}
		}
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) 
	{
		handleFrameClosing("escape");
	}

	private void shortCutKeyInit()
	{
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"escape");
		getRootPane().getActionMap().put("escape", new AbstractAction()
		{
			public void actionPerformed(ActionEvent ae)
			{
				handleFrameClosing("escape");                
			}
		});


	}

	private void persistSMSProfile(Properties profileProps)
	{
		if(standAlone)
		{
			PropertyChangeEvent evt = new PropertyChangeEvent(this,"smsprofiles",null,smsprofileDetails);
			listener.propertyChange(evt);
			SMSProfileParser.getInstance().setsmsprofileDetails(smsprofileDetails);
		}
		else
		{
			notifyListener(profileProps);
		}
	}

	private void handleFrameClosing(String key)
	{
		exitFrame();
	}

	//method to closeFrame
	public void exitFrame()
	{
		setVisible(false);
		dispose();
	}


	private void notifyListener(Properties profileProps)
	{
		PropertyChangeEvent evt = new PropertyChangeEvent(this,"smsprofiles",null,profileProps);
		listener.propertyChange(evt);
	}


	private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{                                           
		JPopupMenu jpopMenu = new JPopupMenu();
		JLabel helpLabel = new JLabel();
		helpLabel.setSize(300,200);
		helpLabel.setText(NmsClientUtil.GetString("javaui.smsconfigui.helpText"));
		jpopMenu.setLayout(new BorderLayout());
		jpopMenu.add(helpLabel, BorderLayout.CENTER);
		jpopMenu.show(helpButton,-60,-30);

	}                                          

	// Variables declaration - do not modify
	private javax.swing.JButton addButton;
	private javax.swing.JButton cancelButton;
	private javax.swing.JButton deleteButton;
	private javax.swing.JButton resetButton;
	private javax.swing.JButton editButton;
	private javax.swing.JButton helpButton;
	private javax.swing.JList accountList;
	private javax.swing.JPanel mainPanel;
	private javax.swing.JPanel bottomPanel;
	private javax.swing.JPanel detailsPanel;
	private javax.swing.JPanel propsPanel;
	private javax.swing.JPanel buttonPanel;
	private javax.swing.JPanel listPanel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSeparator separator;
	private javax.swing.JButton okButton;
	private javax.swing.JTextField[] textFields;
	// End of variables declaration


}
