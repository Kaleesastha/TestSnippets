/*
$Id: AddAction.java,v 1.2 2007/02/22 15:02:55 srajeswari Exp $
 * AddAction.java
 *
 * Created on September 14, 2004, 8:28 PM
 */
package com.adventnet.nms.eventui;

import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.File;
import java.text.*;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.eventui.*;
import com.adventnet.nms.util.*;

/**
 *
 * @author  sivaprakash
 */
public class AddAction extends javax.swing.JDialog {
    
    Vector list = null;
    Hashtable actions = null;
    EventBrowser evtBrowser = null;
    DefaultNmsFilterFrame frm = null;
    String selectedAction = null;
    DefaultMutableTreeNode selectedNode = null;
    Hashtable notifiers = new Hashtable();
    boolean addNew = false;
    
    //action names
    public static String actionroot       = NmsClientUtil.GetString("Notifications");//No i18n
    public static String suppressAction   = NmsClientUtil.GetString("Suppress Action");//No i18n
    public static String runCommandAction = NmsClientUtil.GetString("Run Command Action");//No i18n
    public static String sendTrapAction   = NmsClientUtil.GetString("Send Trap Action");//No i18n
    public static String sendEmailAction  = NmsClientUtil.GetString("Send Email Action");//No i18n
    public static String customFilter     = NmsClientUtil.GetString("Custom Filter");//No i18n
    
    /** Creates new form AddAction */
    public AddAction(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //frm = (DefaultNmsFilterFrame)parent;
        initComponents();
        tree.setCellRenderer(new MyRenderer(this));
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        setIcons();
        
        /*frm = (DefaultNmsFilterFrame)parent;
        populateTree();*/
    }
    
    public AddAction(java.awt.Frame parent, boolean modal, EventBrowser evt) {
        this(parent, false);
        //notifiers = hst;
        evtBrowser = evt;
        frm = (DefaultNmsFilterFrame)parent;
        populateTree();
       
       /*tree = new javax.swing.JTree() {
            public String getToolTipText(MouseEvent evt) {
                  if (getRowForLocation(evt.getX(), evt.getY()) == -1) return null;
                  TreePath curPath = getPathForLocation(evt.getX(), evt.getY());
                  System.out.println(curPath.getLastPathComponent().toString());
                  return curPath.getLastPathComponent().toString();
            }
         };
         tree.setToolTipText("");*/
    }
    
    
    /*public AddAction(java.awt.Frame parent, boolean model){
        this(parent, model);
        frm = (DefaultNmsFilterFrame)parent;
        populateTree();
      }*/
    
    DefaultMutableTreeNode root    = new DefaultMutableTreeNode(actionroot);
    DefaultMutableTreeNode spaNode = new DefaultMutableTreeNode(suppressAction);
    DefaultMutableTreeNode rcaNode = new DefaultMutableTreeNode(runCommandAction);
    DefaultMutableTreeNode staNode = new DefaultMutableTreeNode(sendTrapAction);
    DefaultMutableTreeNode seaNode = new DefaultMutableTreeNode(sendEmailAction);
    DefaultMutableTreeNode cvaNode = new DefaultMutableTreeNode(customFilter);
    public void populateTree()
    {   
        root.add(spaNode);
        root.add(rcaNode);
        root.add(staNode);
        root.add(seaNode);
        root.add(cvaNode);
        Hashtable ht = NotifierList.notifiers;
        
        for(Enumeration enumerate  = ht.keys(); enumerate.hasMoreElements();)
        {
            String key = (String)enumerate.nextElement();
            
            com.adventnet.nms.eventdb.FilterAction action = (com.adventnet.nms.eventdb.FilterAction)ht.get(key);
            DefaultMutableTreeNode node = null;
            String type = getActionType(action);
            if(type.equals(suppressAction))
            {
                node = spaNode;
            }
            else if(type.equals(runCommandAction))
            {
                node = rcaNode;
            }
            else if(type.equals(sendTrapAction))
            {
                node = staNode;
            }
            else if(type.equals(sendEmailAction))
            {
                node = seaNode;
            }
            else if(type.equals(customFilter))
            {
                node = cvaNode;
            }
            else
            {
                continue;
            }
            
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(key);
            node.add(child);
        }
        
        DefaultTreeModel model = new DefaultTreeModel(root);
        tree.setModel(model);
        
        expandTree();
        tree.setSelectionRow(1);
        pack();
    }
    
    public void expandTree()
    {
        int size = tree.getRowCount();
        for(int i = size; i >= 0; i--)
        { 
            tree.expandRow(i);
        }
    }
    
    public void setIcons()
    {
        jLabel1.setIcon(getIcon("images/add_action_top.jpg"));//No i18n
    }
   
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        tree = new javax.swing.JTree();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        newButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        helpButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(NmsClientUtil.GetString("Add Action"));//No i18n
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(320, 400));
        //jPanel1.setPreferredSize(new java.awt.Dimension(320, 400));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setIcon(jLabel1.getIcon());
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel14.setLayout(new java.awt.BorderLayout());

        tree.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        tree.setForeground(new java.awt.Color(51, 0, 204));
        tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treeValueChanged(evt);
            }
        });

        //jPanel14.add(tree, java.awt.BorderLayout.CENTER);
	jPanel4.setPreferredSize(new java.awt.Dimension(150, 250));
        //jScrollPane1.setViewportView(jPanel14);
        jScrollPane1.setViewportView(tree);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        newButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        newButton.setText(NmsClientUtil.GetString("javaui.filter.New"));
        newButton.setToolTipText(NmsClientUtil.GetString("Add New Notifier"));//No i18n
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 10, 10);
        jPanel5.add(newButton, gridBagConstraints);

        editButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        editButton.setText(NmsClientUtil.GetString("javaui.filter.Edit"));
        editButton.setToolTipText(NmsClientUtil.GetString("Edit the selected Notifier"));//No i18n
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(editButton, gridBagConstraints);

        deleteButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        deleteButton.setText(NmsClientUtil.GetString("javaui.filter.Delete"));
        deleteButton.setToolTipText("Delete the select the Notifier");//No i18n
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(deleteButton, gridBagConstraints);

        jPanel7.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel7, java.awt.BorderLayout.EAST);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jPanel9.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        helpButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        helpButton.setText(NmsClientUtil.GetString("javaui.filter.Help"));
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        jPanel9.add(helpButton);

        jPanel6.add(jPanel9, java.awt.BorderLayout.WEST);

        jPanel10.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        jPanel6.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel11.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jPanel11.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 10)));
        okButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        okButton.setText(NmsClientUtil.GetString("javaui.filter.Add"));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        jPanel11.add(okButton);

        cancelButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        cancelButton.setText(NmsClientUtil.GetString("javaui.filter.Cancel"));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jPanel11.add(cancelButton);

        jPanel6.add(jPanel11, java.awt.BorderLayout.EAST);

        jPanel12.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel13.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        jPanel13.add(jSeparator1, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel13, java.awt.BorderLayout.NORTH);

        jPanel3.add(jPanel12, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        addWindowListener(); 

        pack();
    }//GEN-END:initComponents
     // Method to add Winow Listeners for the Filter Actions
 public void addWindowListener()
    {
rcdialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {   
             addNew=false;
            }
        });  
        sadialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {   
            addNew=false;
            }
        });  
         stdialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {   
            addNew=false;
            }
        });  
         sedialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) 
            {   
            addNew=false;
            }
        });  
         cfdialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) 
            {   
            addNew=false;
            }
        });    

    }


    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if(selectedAction != null && !selectedAction.trim().equals(""))
        {
            DefaultTableModel model = (DefaultTableModel)frm.getTableModel();
            int size  = model.getRowCount();
        
            for(int i = 0; i < size; i ++)
            {
                String key = (String)model.getValueAt(i, 0);
                if(selectedAction.equals(key))
                {
                    JLabel label = new JLabel(NmsClientUtil.GetString("javaui.filter.alreadexist"));//No i18n
                    JOptionPane.showConfirmDialog(this  ,label, NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.DEFAULT_OPTION);//No i18n
                    return;
                }
            }
        
            Vector vec = new Vector();
            vec.addElement(selectedAction);
            model.addRow(vec);
            //frm.addTable(selectedAction);
	    if(!NmsFilterFrame.standAlone)
		{
		    EventBrowser eb = (EventBrowser)NmsUiAPI.getNmsPanel("Events");//No i18n
		    eb.client.putNotifiers(new NotifierList());
		}
	    else
		{
		    saveNotifiers();
		}
        }
        cancelButtonActionPerformed(evt);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeValueChanged
        TreePath newNode = evt.getNewLeadSelectionPath();
        if(newNode == null)
        {
            return;
        }
        selectedNode = (DefaultMutableTreeNode)newNode.getLastPathComponent();
	selectedAction = selectedNode.toString();	        
        String name = selectedAction;
        
        TreeNode pnode = selectedNode.getParent();
	String parentName = null;
	if(pnode != null)
        {
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)selectedNode.getParent();
            parentName = parentNode.toString();
        }
        else
        {
            parentName = actionroot;
        }
        if(name.equals(actionroot))
	    {
		newButton.setEnabled(false);
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
		okButton.setEnabled(false);
	    }
        else if(parentName.equals(actionroot))
	    {
		newButton.setEnabled(true);
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
		okButton.setEnabled(false);
	    }
        else
	    {
		newButton.setEnabled(true);
		editButton.setEnabled(true);
		deleteButton.setEnabled(true);  
		okButton.setEnabled(true);
	    }  
	
    }//GEN-LAST:event_treeValueChanged

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
       JPopupMenu jp = new JPopupMenu();
       
       JMenuItem mi = new JMenuItem("Help");//No i18n
       JPanel panel = new JPanel();

       String msgTxt = "<html>This tool is provided for adding and configuring various Action.<br>By using this tool, we can configure various actions like <br> 1. suppress Action <br> 2. RunCommand Action <br> 3. Send Trap Action <br> 4. Send Email Action <br> 5. Custom Filter</html>";//No i18n
       
       JLabel ep = new JLabel();
       ep.setSize(300,200);
       ep.setText(msgTxt);
       panel.add(ep);

       mi.setLayout(new BorderLayout());
       mi.setPreferredSize(panel.getPreferredSize());
       mi.setFocusPainted(false);
       mi.add(panel, BorderLayout.CENTER);
       jp.setLayout(new BorderLayout());
       jp.add(mi, BorderLayout.CENTER);
       jp.show(helpButton,-60,-60);
       
       
    }//GEN-LAST:event_helpButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int rows[] = tree.getSelectionRows();
        int row = rows[0];
        NotifierList.notifiers.remove(selectedAction);
        
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        
        model = new DefaultTreeModel(root);
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)selectedNode.getParent();
        
        parentNode.remove(selectedNode);
        selectedNode.removeFromParent();
        tree.setModel(model);
        tree.setSelectionRow (row - 1);
        expandTree ();
	if(!NmsFilterFrame.standAlone)
	    {
		EventBrowser eb = (EventBrowser)NmsUiAPI.getNmsPanel("Events");//No i18n
		eb.client.putNotifiers(new NotifierList());
	    }
	else
	    {
		saveNotifiers();
	    }
    }//GEN-LAST:event_deleteButtonActionPerformed

    SuppressActionDialog sadialog = new SuppressActionDialog((Frame)this.getParent(), true , this);
    RunCommandActionDialog rcdialog = new RunCommandActionDialog((Frame)this.getParent(), true , this);
    SendTrapActionDialog stdialog = new SendTrapActionDialog((Frame)this.getParent(), true , this);
    SendEmailActionDialog sedialog = new SendEmailActionDialog((Frame)this.getParent(), true, this);
    CustomFilterActionDialog cfdialog = new CustomFilterActionDialog((Frame)this.getParent(), true, this);

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
       
        String actionType = selectedNode.getParent().toString();
        if(actionType.equals(suppressAction))
        {
            //sadialog = new SuppressActionDialog((Frame)this.getParent(), true , this);
            sadialog.setProperties(selectedAction);
            NmsClientUtil.centerWindow(sadialog);
            sadialog.show();
        }  
        else if(actionType.equals(runCommandAction))
        {
            //rcdialog = new RunCommandActionDialog((Frame)this.getParent(), true , this);
            rcdialog.setProperties(selectedAction);
            NmsClientUtil.centerWindow(rcdialog);
            rcdialog.show();
        }
        else if(actionType.equals(sendTrapAction))
        {
            //stdialog = new SendTrapActionDialog((Frame)this.getParent(), true , this);
            stdialog.setProperties(selectedAction);
            NmsClientUtil.centerWindow(stdialog);
            stdialog.show();
        }
        else if(actionType.equals(sendEmailAction))
        {
            //sedialog = new SendEmailActionDialog((Frame)this.getParent(), true, this);
            sedialog.setProperties(selectedAction);
            NmsClientUtil.centerWindow(sedialog);
            sedialog.show();
        }
        else if(actionType.equals(customFilter))
        {
            //cfdialog = new CustomFilterActionDialog((Frame)this.getParent(), true, this);
            cfdialog.setProperties(selectedAction);
            NmsClientUtil.centerWindow(cfdialog);
            cfdialog.show();
        }
        expandTree();
    }

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String parentNode = selectedNode.getParent().toString();
        addNew = true;
        if(selectedAction.equals(suppressAction) || parentNode.equals(suppressAction))
        {
            //sadialog = new SuppressActionDialog((Frame)this.getParent(), true , this);
            sadialog.setProperties(null);
            NmsClientUtil.centerWindow(sadialog);
            sadialog.show();
        }  
        else if(selectedAction.equals(runCommandAction) || parentNode.equals(runCommandAction))
        {
            //rcdialog = new RunCommandActionDialog((Frame)this.getParent(), true , this);
            rcdialog.setProperties(null);
            NmsClientUtil.centerWindow(rcdialog);
            rcdialog.show();
        }
        else if(selectedAction.equals(sendTrapAction) || parentNode.equals(sendTrapAction))
        {
            //stdialog = new SendTrapActionDialog((Frame)this.getParent(), true , this);
            stdialog.setProperties(null);
            NmsClientUtil.centerWindow(stdialog);
	    stdialog.show();
        }
        else if(selectedAction.equals(sendEmailAction) || parentNode.equals(sendEmailAction))
        {
            //sedialog = new SendEmailActionDialog((Frame)this.getParent(), true, this);
            sedialog.setProperties(null);
            NmsClientUtil.centerWindow(sedialog);
            sedialog.show();
        }
        else if(selectedAction.equals(customFilter) || parentNode.equals(customFilter))
        {
            //cfdialog = new CustomFilterActionDialog((Frame)this.getParent(), true, this);
            cfdialog.setProperties(null);
            NmsClientUtil.centerWindow(cfdialog);
            cfdialog.show();
        }
        expandTree();
    }
    
    public static void main(String args[]) {
        new AddAction(new javax.swing.JFrame(), true).show();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton newButton;
    private javax.swing.JButton okButton;
    private javax.swing.JTree tree;
    // End of variables declaration//GEN-END:variables
    
    public boolean addNewAction(Properties prop, String type)
    {
        if(prop == null)
        {
            return false;
        }
        String actionName = prop.getProperty("name");
        FilterAction action = null;
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(actionName);
        DefaultMutableTreeNode node = null;
        if(type.equals(suppressAction))
        {
            node = spaNode;
            action = new FilterAction();  
        }
        else if(type.equals(runCommandAction))
        {
            
            node = rcaNode;
            action = new FilterCommand();
        }
        else if(type.equals(sendTrapAction))
        {
            node = staNode;
            action = new SendTrap();
        }
        else if(type.equals(sendEmailAction))
        {
            node = seaNode;
            action = new SendEmail();
        }
        else if(type.equals(customFilter))
        {
            node = cvaNode;
            action = new UserFilter();
        }
        else
        {
            return false;
        }
        
        action.setProperties(prop);
        
        
        NotifierList.notifiers.put(actionName, action);
        
        if(addNew)
        {
            node.add(newNode);   
        
            DefaultTreeModel model = new DefaultTreeModel(root);
            tree.setModel(model);  
            addNew = false;
        }

        if(!NmsFilterFrame.standAlone)
	    {
		EventBrowser eb = (EventBrowser)NmsUiAPI.getNmsPanel("Events");//No i18n
		eb.client.putNotifiers(new NotifierList());
	    }
	else
	    {
		saveNotifiers();
	    }
        return true;
    }

    public void saveNotifiers()
    {
	String fileName = "conf/notifications.conf";//No i18n
	XMLNode rootNode = ((new NotifierList()).saveNotifiers(fileName));
	
	if(rootNode != null)
	    {
		File f = new File(fileName);
		if (f.exists() && !f.canWrite())
		    {
			NmsClientUtil.err(NmsClientUtil.GetString("Cannot open file for writing Alert Filters: ")+fileName);//No i18n
		    }	
		else
		    {
			XMLDataWriter  xmlWriter = new XMLDataWriter(fileName,rootNode);
		    }
	    } 
    }
    
    public TreeNode getSelectedNode()
    {
        return selectedNode;
    }
    
    public String getSelectedAction()
    {
        return selectedAction;
    }
      
    private String getActionType(com.adventnet.nms.eventdb.FilterAction act)
    {
        if(act == null)
        {
            return null;
        }
        Properties p           = act.getProperties();
        String actionClassName = act.getClass().getName();
        if ( actionClassName.equals("com.adventnet.nms.eventdb.FilterAction"))
            {
                return suppressAction;
            }
	else if (actionClassName.equals("com.adventnet.nms.eventdb.FilterCommand"))
	    {
                return runCommandAction;
	    }
	else if (actionClassName.equals("com.adventnet.nms.eventdb.SendTrap"))
	    {
		return sendTrapAction;
	    }
	else if (actionClassName.equals("com.adventnet.nms.eventdb.SendEmail"))
	    {
		return sendEmailAction;
	    }
	else if (actionClassName.equals("com.adventnet.nms.eventdb.UserFilter"))
	    {
		return customFilter;
            }
	return "";
    }
    
    public static Icon getIcon(String image){
	
        String imagePath=null;

        if(NmsClientUtil.applet!=null)
	    {
		imagePath = NmsClientUtil.applet.getDocumentBase() + "../" + image;//No i18n
	    }
        else
	    {
		imagePath = image;
	    }
        if(imagePath==null)
	    {
		return null;
	    }
        try
	    {
		if(!NmsFilterFrame.standAlone)
		    {
			return NmsClientUtil.getImageIcon(new URL(imagePath));
		    }
		else
		    {
			ImageIcon icon = new ImageIcon(imagePath);
			return icon;
		    }
	    }
        catch(Exception e)
	    {
		System.err.println(e.getMessage());
		return null;
	    }   
    }
    
    public static synchronized boolean checkNull(JTextField tf, String s)
    { 
   	if((tf.getText().trim()).equals(""))
	    {
		JOptionPane.showMessageDialog(new JFrame(), MessageFormat.format("Value of {0} should not be empty", new String[]{s}),					      NmsClientUtil.GetString("javaui.filter.errormessage"),JOptionPane.ERROR_MESSAGE);//No i18n
		tf.requestFocus();
		tf.selectAll();
		return true;
	    }
	else
        {
	    return false;
        }
    }
    
    public static synchronized boolean checkNumber(JTextField tf, String str)
    {
        try
        {
            String text = tf.getText().trim();
            if(!checkIfSymbolic(text)){
		   int port = Integer.parseInt(text);
                   if(port < 0)
                   {
                       JOptionPane.showMessageDialog(new JFrame(), MessageFormat.format(NmsClientUtil.GetString("Value of {0} should be a positive number"), new String[]{str}), NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.ERROR_MESSAGE);//No i18n
                       tf.requestFocus();
                       tf.selectAll();				
                       return true;
                   }
            }
        }
        catch(NumberFormatException ne)
        {
           JOptionPane.showMessageDialog(new JFrame(),
					 MessageFormat.format(NmsClientUtil.GetString("Value of {0} should be a numeric value."), new String[]{str}), NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.ERROR_MESSAGE);//No i18n
           tf.requestFocus();
           tf.selectAll();				
           return true;
        }		
        return false;
    }
    
    public static boolean checkIfSymbolic(String string)
    {
        boolean retVal = false;
        
        if(string != null && !string.equals(""))
	    {
		if(string.charAt(0) == '$' || string.charAt(0) == '%'  || string.charAt(0) == '@')
		    {
			retVal = true;
		    }
	    }
        return retVal; 
    }

    class MyRenderer extends DefaultTreeCellRenderer 
    {
	AddAction axn = null;
	
        public MyRenderer(AddAction aa) 
	{
            axn = aa;
        }
	
        public Component getTreeCellRendererComponent(JTree tree,
						      Object value,
						      boolean sel,
						      boolean expanded,
						      boolean leaf,
						      int row,
						      boolean hasFocus) 
        {
	    
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            
            Object obj = NotifierList.notifiers.get(value.toString());
            String val = value.toString();
            String actionType = "";
            String image = "";
            if(val.equals(suppressAction) || val.equals(runCommandAction) || val.equals(sendTrapAction) || val.equals(sendEmailAction) || val.equals(customFilter))
		{
		    image = "images/parentAction.png";//No i18n
		}
            else if(val.equals("Notifications"))
		{
		    image = "empty.png";//No i18n
		}
            else
		{
		    image = "images/childAction.png";//No i18n
		}
            
            /*if(obj != null && image.equals(""))
	      {
	      try
	      {
	      actionType = axn.getActionType((FilterAction)obj);
	      }
	      catch(Exception e)
	      {
	      System.out.println("error in tree renderer");//No i18n
	      }
	      actionType = val;
	      }
	      
	      if(actionType.equals(suppressAction))
	      {
	      image = "images/saIcon.png";//No i18n
	      }
	      else if (actionType.equals(runCommandAction))
	      {
	      image = "images/rcIcon.png";//No i18n
	      }
	      else if (actionType.equals(sendTrapAction))
	      {
	      image = "images/stIcon.png";//No i18n
	      }
	      else if (actionType.equals(sendEmailAction))
	      {
	      image = "images/seIcon.png";//No i18n
	      }
	      else if (actionType.equals(customFilter))
	      {
	      image = "images/cfIcon.png";//No i18n
	      }*/
            
            Icon icon = axn.getIcon(image);
            setIcon(icon);
            setToolTipText(value.toString());
	    
            return this;
        }
    }
}
