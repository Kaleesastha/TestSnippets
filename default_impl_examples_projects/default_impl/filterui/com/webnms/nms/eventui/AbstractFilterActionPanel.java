/* $Id: AbstractFilterActionPanel.java,v 1.2.4.3 2013/06/26 05:34:54 wesley Exp $
 * 
 * SuppressActionPanel.java
 *
 */

package com.webnms.nms.eventui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.adventnet.nms.eventdb.FilterAction;
import com.adventnet.nms.util.NmsClientUtil;

/**
 *	This class is the base class for all the different types of filter action
 *	panels. It holds the common components and functions.
 *
 * @author  aravinds
 */
public abstract class AbstractFilterActionPanel extends JPanel 
{

	protected AddFilterAction parent;
	protected DefaultListModel model;
	protected Hashtable<String,FilterAction> actionList;
	protected boolean propsChanged = false;
	protected boolean enableSepThreadHandling = true;

	/** Creates new form SuppressActionPanel */
	public AbstractFilterActionPanel(AddFilterAction parent) 
	{
		this.parent = parent;
		initComponents();
	}

	private void initComponents() 
	{

		buttonPanel = new javax.swing.JPanel();
		addButton = new javax.swing.JButton();
		editButton = new javax.swing.JButton();
		delButton = new javax.swing.JButton();
		resetButton = new javax.swing.JButton();
		detailsPanel = new javax.swing.JPanel();
		contentPane = new javax.swing.JTabbedPane();
		listPanel = new javax.swing.JPanel();
		scrollPane = new javax.swing.JScrollPane();
		actionListUI = new javax.swing.JList();
		
		addButton.setText(NmsClientUtil.GetString("javaui.filteractionui.addActionButton"));
		addButton.setToolTipText(NmsClientUtil.GetString("Add New Notifier"));//No i18n
		addButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				addButtonActionPerformed(evt);
			}
		});

		editButton.setText(NmsClientUtil.GetString("javaui.filter.Edit"));
		editButton.setToolTipText(NmsClientUtil.GetString("Edit the selected Notifier"));//No i18n
		editButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		editButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				editButtonActionPerformed(evt);
			}
		});

		delButton.setText(NmsClientUtil.GetString("javaui.filter.Delete"));
		delButton.setToolTipText(NmsClientUtil.GetString("Delete the selected Notifier"));
		delButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		delButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				delButtonActionPerformed(evt);
			}
		});

		resetButton.setText(NmsClientUtil.GetString("javaui.filteractionui.resetButton"));
		resetButton.setToolTipText(NmsClientUtil.GetString("javaui.filteractionui.resetButtonTip"));
		resetButton.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		resetButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				resetButtonActionPerformed(evt);
			}
		});


		buttonPanel.setLayout(new GridLayout(1,4,5,0));
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(delButton);
		buttonPanel.add(resetButton);

		model = new DefaultListModel();
		actionListUI.setModel(model);
		actionListUI.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		actionListUI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		actionListUI.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) 
			{
				actionListValueChanged(evt);
			}
		});

		scrollPane.setViewportView(actionListUI);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(8,5,5,5));

		listPanel.setLayout(new GridLayout(1,1));
		scrollPane.setMinimumSize(new Dimension(190,200));
		scrollPane.setPreferredSize(new Dimension(190,200));		
		listPanel.add(scrollPane);
		listPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, NmsClientUtil.GetString("javaui.filteractionui.actionList"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, NmsClientUtil.getFont("DIALOG")));//No I18N
		initDetailsPanel();
		initAdvPanel();		
		initContentPane();		
		positionComponents();
		resetButtonActionPerformed(null);

	}// </editor-fold>

	protected void initContentPane()
	{
		contentPane.setFont(NmsClientUtil.getFont("DIALOG")); //No I18N
		contentPane.addTab(NmsClientUtil.GetString("javaui.filteractionui.generalTab"),detailsPanel);
		if(enableSepThreadHandling)
		{
			contentPane.addTab(NmsClientUtil.GetString("javaui.filteractionui.advancedTab"), advPanel);
		}
		contentPane.setPreferredSize(new Dimension(380,200));	
	}
	
	
	protected void initAdvPanel()
	{
		if(!enableSepThreadHandling)
		{
			return;
		}
		
		advPanel = new JPanel();
		advPanel.setLayout(new GridBagLayout());
		
		JLabel labelComp = new JLabel(NmsClientUtil.GetString("javaui.filteractionui.threadImplForEvents"));
		labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 0;
		
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new java.awt.Insets(5, 15, 5, 15);
		advPanel.add(labelComp,constraints);

		eventClass = new JTextField(10);
		eventClass.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		constraints.insets = new java.awt.Insets(5, 10, 5, 10);
		constraints.gridx = 1;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		advPanel.add(eventClass, constraints);
		
		labelComp = new JLabel(NmsClientUtil.GetString("javaui.filteractionui.threadImplForAlerts"));
		labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 0;
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new java.awt.Insets(5, 15, 5, 15);
		advPanel.add(labelComp,constraints);

		alertClass = new JTextField(10);
		alertClass.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		constraints.insets = new java.awt.Insets(5, 10, 5, 10);
		constraints.gridx = 1;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		advPanel.add(alertClass, constraints);
		
		labelComp = new JLabel(NmsClientUtil.GetString("javaui.filteractionui.separateThreadHandlingNote"));
		labelComp.setFont(NmsClientUtil.getFont("DIALOG"));//No I18N
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new java.awt.Insets(35, 15, 5, 15);
		advPanel.add(labelComp, constraints);
		
	}
	
	/*
	 * this method lays out the component in the panel
	 */
	protected void positionComponents()
	{
		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 3;
		constraints.weighty = 1;
		constraints.weightx = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.insets = new java.awt.Insets(5, 7, 2, 0);
		add(listPanel,constraints);

		JSeparator vertSep = new JSeparator();
		vertSep.setOrientation(JSeparator.VERTICAL);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 0;
		constraints.gridheight = 3;
		constraints.fill = java.awt.GridBagConstraints.VERTICAL;
		constraints.insets = new java.awt.Insets(15, 5, 5, 5);
		add(vertSep,constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridheight = 1;
		constraints.fill = java.awt.GridBagConstraints.BOTH;
		constraints.insets = new java.awt.Insets(5, 5, 5, 5);
		add(contentPane,constraints);

		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.weighty = 0;
		constraints.weightx = 0;
		constraints.fill = java.awt.GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.insets = new java.awt.Insets(15, 7, 10, 10);
		add(buttonPanel,constraints);

	}

	protected void actionListValueChanged(javax.swing.event.ListSelectionEvent evt) 
	{                                    
		String name = (String) actionListUI.getSelectedValue();
		if(name == null)
		{
			resetButtonActionPerformed(null);
			return;
		}
		FilterAction action = actionList.get(name);
		setActionProps(action.getProperties());
		editButton.setEnabled(true);
		delButton.setEnabled(true);
		addButton.setEnabled(false);
		textFields[0].setEditable(false);
		contentPane.setSelectedIndex(0);
	}

	/*
	 * This method should be implemented so as to set the details of the filter action in 
	 * respective fields
	 */
	protected void setActionProps(Properties props)
	{
		if(!enableSepThreadHandling)
		{
			return;
		}
		
		String value = props.getProperty("sepThreadImplForEvents");
		if(value != null)
		{
			eventClass.setText(value);
		}
		value = props.getProperty("sepThreadImplForAlerts");
		if(value != null)
		{
			alertClass.setText(value);
		}
	}

	/*
	 * this method must do the validation of the user input and return the completed props of the 
	 * filter action 
	 */
	protected Properties getActionProps()
	{
		Properties props = new Properties();
		if(!enableSepThreadHandling)
		{
			return props;
		}

		String value = eventClass.getText();
		
		if(!value.trim().equals(""))
		{
			props.setProperty("sepThreadImplForEvents",value);
		}
		value = alertClass.getText();
		if(!value.trim().equals(""))
		{
			props.setProperty("sepThreadImplForAlerts",value);
		}
		return props;	
	}

	protected void addButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{
		Properties props = getActionProps();
		if(props == null)
		{
			return;
		}
		String name = props.getProperty("name");
		if(parent.notifiers.containsKey(name))
		{
			JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.filter.alreadexist"),NmsClientUtil.GetString("javaui.filter.errormessage"),JOptionPane.ERROR_MESSAGE); //No I18N
			textFields[0].requestFocus();
			textFields[0].selectAll();
			return;
		}
		
		FilterAction action = parent.createAction(props);
		actionList.put(name,action);
		model.addElement(name);
		propsChanged = true;
		resetButtonActionPerformed(null);
	}

	protected void editButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{
		Properties props = getActionProps();
		if(props == null)
		{
			return;
		}

		String name = props.getProperty("name");
		Properties oldProps = actionList.get(name).getProperties();
		if(props.equals(oldProps))
		{
			JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.filteractionui.noChangeInDetails"),NmsClientUtil.GetString("javaui.filter.errormessage"),JOptionPane.ERROR_MESSAGE); //No I18N
			return;
		}
		
		FilterAction action = parent.createAction(props);
		actionList.put(name,action);
		resetButtonActionPerformed(null);
		propsChanged = true;
	}

	protected void delButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{
		int n = JOptionPane.showConfirmDialog(this,NmsClientUtil.GetString("javaui.filteractionui.deleteAccount"),NmsClientUtil.GetString ("javaui.filteractionui.confirmDialog"),JOptionPane.YES_NO_OPTION); //No I18N
		if(n == JOptionPane.NO_OPTION)
		{
			return;
		}
		String name = (String)actionListUI.getSelectedValue();
		resetButtonActionPerformed(null);
		model.removeElement(name);
		actionList.remove(name);
		propsChanged = true;
	}

	/*
	 * this method will reset all the text fields in the details panel
	 */
	protected void resetButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{
		int size = textFields.length;
		for(int i = 0;i<size;i++)
		{
			textFields[i].setText("");
		}
		actionListUI.clearSelection();
		addButton.setEnabled(true);
		editButton.setEnabled(false);
		delButton.setEnabled(false);
		textFields[0].setEditable(true);
		if(enableSepThreadHandling)
		{
			eventClass.setText("");
			alertClass.setText("");
		}
		contentPane.setSelectedIndex(0);
	}
	
	/*
	 * this method must be implemented so as to set the required fields as per the properties of the 
	 * filter action
	 */
	protected abstract void initDetailsPanel();


	public void setActionList(Hashtable<String,FilterAction> list)
	{
		if(list == null)
		{
			list = new Hashtable<String,FilterAction>();
		}
		actionList = list;
		Set<String> listData = actionList.keySet();
		for(String name : listData)
		{
			model.addElement(name);
		}
	}

	public boolean hasActionsChanged()
	{
		return propsChanged;
	}

	public Hashtable<String,FilterAction> getActionList()
	{
		return actionList;
	}
	
	public String getSelectedAction()
	{
		return (String)actionListUI.getSelectedValue();
	}

	public abstract String getHelpText();
	
	
	// Variables declaration - do not modify
	protected javax.swing.JList actionListUI;
	protected javax.swing.JButton addButton;
	protected javax.swing.JPanel buttonPanel;
	protected javax.swing.JPanel advPanel;
	protected javax.swing.JTabbedPane contentPane;
	protected javax.swing.JButton delButton;
	protected javax.swing.JPanel detailsPanel;
	protected javax.swing.JButton editButton;
	protected javax.swing.JButton resetButton;
	protected javax.swing.JPanel listPanel;
	protected javax.swing.text.JTextComponent textFields[];
	protected javax.swing.JScrollPane scrollPane;
	protected javax.swing.JTextField eventClass;
	protected javax.swing.JTextField alertClass;
	// End of variables declaration

}
