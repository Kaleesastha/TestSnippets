/* $Id: SMTPConfigurator.java,v 1.2 2010/10/29 13:45:58 swaminathap Exp $
 * SmtpConfigurator.java
 *
 */

package com.webnms.nms.adminui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.adventnet.nms.util.MailConfigParser;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.security.authorization.Coding;

/**
 *	This dialog is used to configure SMTP Accounts. Users would be able to add,
 *	modify and delete SMTP Accounts.
 *
 * @author  aravinds
 */
public class SMTPConfigurator extends JDialog
{

	private static boolean standAlone = false;
	private Hashtable<String,Properties> mailServerDetails = new Hashtable<String,Properties>();
	private PropertyChangeListener listener;
	private boolean propsChanged = false;
	private DefaultListModel model;
	
	
	/** Creates new form AddAction */
	public SMTPConfigurator(Frame parent, boolean modal) 
	{
		super(parent, modal);
		initComponents();
	}

	/** Creates new form SmtpConfigurator */
	public SMTPConfigurator(Frame parent,Hashtable<String,Properties> accDetails) 
	{
		this(parent,true);
		if(accDetails != null)
		{
			mailServerDetails.putAll(accDetails);
		}
		Set<String> listData = mailServerDetails.keySet();
		for(String name : listData)
		{
			model.addElement(name);
		}

	}

	public SMTPConfigurator(Frame parent,PropertyChangeListener listener,Hashtable<String,Properties> accDetails)
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

		setTitle(NmsClientUtil.GetString("javaui.smtpconfigui.title"));

		okButton.setText(NmsClientUtil.GetString("javaui.smtpconfigui.okButton"));
		okButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		okButton.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				okButtonActionPerformed(evt);
			}
		});

		cancelButton.setText(NmsClientUtil.GetString("javaui.smtpconfigui.cancelButton"));
		cancelButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		cancelButton.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				cancelButtonActionPerformed(evt);
			}
		});

		helpButton.setText(NmsClientUtil.GetString("javaui.smtpconfigui.helpButton"));
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

		propsPanel.setBorder(BorderFactory.createTitledBorder (null, NmsClientUtil.GetString("javaui.smtpconfigui.detailsTitle"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, NmsClientUtil.getFont("DIALOG")));
		propsPanel.setLayout(new GridBagLayout());

		String[] labels = new String[]{"javaui.smtpconfigui.accountName","javaui.smtpconfigui.smtpServer","javaui.smtpconfigui.fromAddress","javaui.smtpconfigui.toAddress","javaui.smtpconfigui.sslMode","javaui.smtpconfigui.port","javaui.smtpconfigui.authRequired","javaui.smtpconfigui.userName","javaui.smtpconfigui.password"};
		textFields = new JTextField[7];
		checkBoxes = new JCheckBox[2];

		int textFieldCounter = 0;
		int checkBoxCounter = 0;

		for(int i = 0;i < 9;i++)
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
			if(i == 4 || i == 6)
			{
				checkBoxes[checkBoxCounter] = new JCheckBox();
				comp = checkBoxes[checkBoxCounter];
				if(i == 6)
				{
					((JCheckBox)comp).addItemListener(new ItemListener() {

						public void itemStateChanged(ItemEvent e)
						{
							changeAuthState(e);							
						}
					});
				}

				constraints.insets = new java.awt.Insets(5, 2, 5, 5);
				checkBoxCounter++;
			}
			else
			{
				if(i == 8)
				{
					textFields[textFieldCounter] = new JPasswordField(8);
				}
				else
				{
					textFields[textFieldCounter] = new JTextField(8);					
				}
				textFields[textFieldCounter].setName(NmsClientUtil.GetString(labels[i]));
				comp = textFields[textFieldCounter];
				textFieldCounter ++;
				constraints.insets = new java.awt.Insets(5, 5, 5, 10);
			}
			
			comp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
			constraints.gridx = 1;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.anchor = GridBagConstraints.WEST;
			propsPanel.add(comp, constraints);
		}

		textFields[5].setEditable(false);
		textFields[6].setEditable(false);

		addButton.setText(NmsClientUtil.GetString("javaui.smtpconfigui.addButton"));
		addButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		addButton.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				addButtonActionPerformed(evt);
			}
		});

		editButton.setText(NmsClientUtil.GetString("javaui.smtpconfigui.editButton"));
		editButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		editButton.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				editButtonActionPerformed(evt);
			}
		});

		deleteButton.setText(NmsClientUtil.GetString("javaui.smtpconfigui.deleteButton"));
		deleteButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		deleteButton.addActionListener(new java.awt.event.ActionListener() 
		{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				deleteButtonActionPerformed(evt);
			}
		});

		resetButton.setText(NmsClientUtil.GetString("javaui.smtpconfigui.resetButton"));
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
		listPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, NmsClientUtil.GetString("javaui.smtpconfigui.listTitle"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, NmsClientUtil.getFont("DIALOG")));

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

		Properties props = populateProps();
		if(props == null)
		{
			return;
		}
		String name = textFields[0].getText();
		if(mailServerDetails.containsKey(name))
		{
			JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.smtpconfigui.accountAlreadyExists"),NmsClientUtil.GetString ("javaui.smtpconfigui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE);
			textFields[0].requestFocus();
			textFields[0].selectAll();				
			return;
		}
		mailServerDetails.put(name,props);
		model.addElement(name);
		propsChanged = true;
		resetButtonActionPerformed(null);		
	}

	private void resetButtonActionPerformed(ActionEvent evt)
	{
		for(int i = 0;i < 7;i++)
		{
			textFields[i].setText("");
		}
		for(int i = 0;i < 2;i++)
		{
			checkBoxes[i].setSelected(false);
		}
		textFields[0].setEditable(true);
		accountList.clearSelection();
		addButton.setEnabled(true);
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
	}

	private void editButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{

		Properties props = populateProps();
		if(props == null)
		{
			return;
		}

		String name = textFields[0].getText();
		if(props.equals(mailServerDetails.get(name)))
		{
			JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.smtpconfigui.noChangeInDetails"),NmsClientUtil.GetString ("javaui.smtpconfigui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		mailServerDetails.put(name,props);
		resetButtonActionPerformed(null);
		propsChanged = true;			

	}

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{
		int n = JOptionPane.showConfirmDialog(this,NmsClientUtil.GetString("javaui.smtpconfigui.deleteAccount"),NmsClientUtil.GetString ("javaui.smtpconfigui.confirmDialog"),JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.NO_OPTION)
		{
			return;
		}

		String name = (String)accountList.getSelectedValue();
		model.removeElement(name);
		mailServerDetails.remove(name);
		propsChanged = true;
		resetButtonActionPerformed(null);
	}

	private void handleListSelection(ListSelectionEvent evt)
	{
		String name = (String) accountList.getSelectedValue();
		if(name == null)
		{
			return;
		}
		Properties props = mailServerDetails.get(name);
		textFields[0].setText(name);
		textFields[0].setEditable(false);
		setProperties(props);
		addButton.setEnabled(false);
		editButton.setEnabled(true);
		deleteButton.setEnabled(true);
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

		/*
		 * removed enter key handling
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"enter");
		getRootPane().getActionMap().put("enter", new AbstractAction()
		{
			public void actionPerformed(ActionEvent ae)
			{
				handleFrameClosing("enter");                
			}
		});
		*/
	}

	private void handleFrameClosing(String key)
	{
		if(propsChanged)
		{
			if(key.equalsIgnoreCase("escape"))
			{
				int n = JOptionPane.showConfirmDialog(this,NmsClientUtil.GetString("javaui.smtpconfigui.saveModifiedProps"),NmsClientUtil.GetString ("javaui.smtpconfigui.confirmDialog"),JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.NO_OPTION)
				{
					exitFrame();
					return;
				}
				else if(n == JOptionPane.CLOSED_OPTION)
				{
					return;
				}
			}
			if(standAlone)
			{
				MailConfigParser.getInstance().setMailServerDetails(mailServerDetails);
			}
			else
			{
				notifyListener();
			}
		}
		exitFrame();
	}

	//method to closeFrame
	public void exitFrame()
	{
		setVisible(false);
		dispose();
		if(standAlone)
		{
			System.exit(0);
		}
	}


	private void notifyListener()
	{
		PropertyChangeEvent evt = new PropertyChangeEvent(this,"smtpAccounts",null,mailServerDetails);
		listener.propertyChange(evt);
	}

	private void changeAuthState(ItemEvent evt)
	{
		if(evt.getStateChange() == ItemEvent.SELECTED)
		{
			textFields[5].setEditable(true);
			textFields[6].setEditable(true);
		}
		else
		{
			textFields[5].setEditable(false);
			textFields[6].setEditable(false);
		}

	}

	private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{                                           
		JPopupMenu jpopMenu = new JPopupMenu();
		JLabel helpLabel = new JLabel();
		helpLabel.setSize(300,200);
		helpLabel.setText(NmsClientUtil.GetString("javaui.smtpconfigui.helpText"));
		jpopMenu.setLayout(new BorderLayout());
		jpopMenu.add(helpLabel, BorderLayout.CENTER);
		jpopMenu.show(helpButton,-60,-30);

	}                                          

	public void setProperties(Properties props)
	{
		if(props == null)
		{
			return;
		}

		String val = props.getProperty("SMTP_SERVER");
		textFields[1].setText(val);
		val = props.getProperty("FROM_ADDRESS");
		textFields[2].setText(val);
		val = props.getProperty("TO_ADDRESS");
		textFields[3].setText(val);
		val = props.getProperty("PORT");
		textFields[4].setText(val);
		val = props.getProperty("USER_NAME");
		textFields[5].setText(val);
		val = props.getProperty("PASSWORD");
		try
		{
			val = Coding.convertFromBase(val);
			textFields[6].setText(val);
		}
		catch(Exception exp){}
		val = props.getProperty("SMTP_SSL");
		checkBoxes[0].setSelected(Boolean.parseBoolean(val));
		val = props.getProperty("AUTH");
		checkBoxes[1].setSelected(Boolean.parseBoolean(val));
	}

	private Properties populateProps()
	{
		Properties props = new Properties();
		String value = null;
		String[] keys = {"SMTP_SERVER","FROM_ADDRESS","TO_ADDRESS","PORT","USER_NAME","PASSWORD"};

		boolean val = checkBoxes[0].isSelected();
		props.setProperty("SMTP_SSL",String.valueOf(val));

		val = checkBoxes[1].isSelected();
		props.setProperty("AUTH",String.valueOf(val));

		//here all the 7 textfields are validated, the last 2 are validated only if auth is true
		for(int i = 0; i< 7; i++)
		{
			if(i == 4)
			{
				value = checkNumber(textFields[i]);
			}
			else if(i < 5 || val)
			{
				value = checkString(textFields[i]);
			}
			else
			{
				break;
			}

			if(value == null)
			{
				return null;
			}
			else if(i == 6)//encrypt password
			{
				value = Coding.convertToNewBase(value);
			}
			
			if(i != 0)
			{
				props.put(keys[i-1], value);
			}
		}

		if(!val)
		{
			props.setProperty("USER_NAME","");
			props.setProperty("PASSWORD","");
		}
		return props;
	}

	private String checkString(JTextField textField)
	{ 
		String val = textField.getText().trim(); 
		if(val.trim().equals(""))
		{
			JOptionPane.showMessageDialog(this,MessageFormat.format(NmsClientUtil.GetString("javaui.smtpconfigui.emptyField"), new String[]{textField.getName()}),NmsClientUtil.GetString ("javaui.smtpconfigui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
			textField.selectAll();
			return null;
		}
		else
		{
			return val;
		}
	}

	private String checkNumber(JTextField textField)
	{
		String text = null;
		try
		{
			text = textField.getText().trim();
			int port = Integer.parseInt(text);
			if(port < 0)
			{
				JOptionPane.showMessageDialog(this,MessageFormat.format(NmsClientUtil.GetString("javaui.smtpconfigui.negativeValueFound"), new String[]{textField.getName()}),NmsClientUtil.GetString ("javaui.smtpconfigui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE);
				textField.requestFocus();
				textField.selectAll();				
				return null;
			}
		}
		catch(NumberFormatException ne)
		{
			JOptionPane.showMessageDialog(this,MessageFormat.format(NmsClientUtil.GetString("javaui.smtpconfigui.numericValueExpected"), new String[]{textField.getName()}),NmsClientUtil.GetString ("javaui.smtpconfigui.errorDialogTitle"),JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
			textField.selectAll();				
			return null;
		}		
		return text;
	}


	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				Hashtable<String,Properties> mailServerDetails = MailConfigParser.getInstance().getAllMailServerDetails();
				SMTPConfigurator.standAlone = true;
				SMTPConfigurator config = new SMTPConfigurator(new Frame(),mailServerDetails);
				config.setVisible(true);
			}
		});
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
	private javax.swing.JCheckBox[] checkBoxes;
	// End of variables declaration

}
