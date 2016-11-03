/*
$Id : $
*/
package com.adventnet.nms.eventui;

import com.adventnet.nms.util.*;
import com.adventnet.nms.eventdb.NotifierList;
import com.adventnet.nms.eventdb.FilterAction;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class SuppressActionDialog extends javax.swing.JDialog {
    
    AddAction addAction = null;
    
    public SuppressActionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public SuppressActionDialog(java.awt.Frame parent, boolean modal, AddAction act) {
        this(parent, modal);
        addAction = act;
    }
    

    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        saName = new javax.swing.JTextField();
        saInt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        saYes = new javax.swing.JRadioButton();
        saNo = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        helpButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(AddAction.suppressAction);
        jPanel1.setLayout(new java.awt.BorderLayout());

        //jPanel1.setPreferredSize(new java.awt.Dimension(350, 160));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel1.setText(NmsClientUtil.GetString("javaui.filter.NotificationName"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(6, 11, 6, 6);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel2.setText(NmsClientUtil.GetString("javaui.filter.SuppressAll"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 1, 6);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel3.setText(NmsClientUtil.GetString("javaui.filter.SuppressInterval"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(6, 11, 1, 6);
        jPanel2.add(jLabel3, gridBagConstraints);

        saName.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 11);
        jPanel2.add(saName, gridBagConstraints);

        saInt.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 1, 11);
        jPanel2.add(saInt, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel4.setText(NmsClientUtil.GetString("javaui.filter.SuppressAction"));
        jPanel3.add(jLabel4, new java.awt.GridBagConstraints());

	jSeparator2.setPreferredSize(new java.awt.Dimension(300, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jSeparator2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 5, 5);
        jPanel2.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 5);
        jPanel2.add(jSeparator1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        saYes.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        saYes.setSelected(true);
        saInt.setEnabled(false);
        saYes.setText(NmsClientUtil.GetString("javaui.filter.Yes"));
        saYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saYesActionPerformed(evt);
            }
        });

        jPanel4.add(saYes);

        saNo.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        saNo.setText(NmsClientUtil.GetString("javaui.filter.No"));
        saNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saNoActionPerformed(evt);
            }
        });

        jPanel4.add(saNo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 11, 1, 16);
        jPanel2.add(jPanel4, gridBagConstraints);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(10, 10, 10, 10)));
        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        helpButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        helpButton.setText(NmsClientUtil.GetString("javaui.filter.Help"));
	helpButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
		    helpButtonActionPerformed(evt);
		}
	    });
        jPanel9.add(helpButton);

        jPanel7.add(jPanel9, java.awt.BorderLayout.WEST);

        jPanel10.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        okButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        okButton.setText(NmsClientUtil.GetString("javaui.filter.OK"));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        jPanel10.add(okButton);

        cancelButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        cancelButton.setText(NmsClientUtil.GetString("javaui.filter.Cancel"));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jPanel10.add(cancelButton);

        jPanel7.add(jPanel10, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(jPanel7, gridBagConstraints);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
	       JPopupMenu jp = new JPopupMenu();
       
       JMenuItem mi = new JMenuItem("Help");//No i18n
       JPanel panel = new JPanel();

       String msgTxt = "<html>Drops redundant events/alerts. <br>The suppressed/dropped Events/alerts will not <br>be added to the database. <br>You can choose to suppress all events/alerts <br>or only those that occur after a <br>certain period of time. <br>Refer Admin Guide for details.</html>";//No i18n
       
       /*JTextArea ep = new JTextArea();
       ep.setColumns(20);
       ep.setLineWrap(true);
       ep.setRows(10);
       ep.setBackground(new java.awt.Color(200, 200, 200));
       ep.setWrapStyleWord(true);*/
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
       
    }


    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
        this.dispose();
         addAction.addNew=false;
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        String name = saName.getText();
        if(AddAction.checkNull(saName, NmsClientUtil.GetString("Notification Name")))
        {
            return;
        }
	if(AddAction.checkNumber(saInt,jLabel3.getText() ))
	    {
		return;
	    }
        addAction.addNewAction(getProperties(), AddAction.suppressAction);//No i18n
        
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void saNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saNoActionPerformed
        // TODO add your handling code here:
        saNo.setSelected(true);
        saInt.setEnabled(true);
        saYes.setSelected(false);
    }//GEN-LAST:event_saNoActionPerformed

    private void saYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saYesActionPerformed
        // TODO add your handling code here:
        saNo.setSelected(false);
        saInt.setEnabled(false);
        saYes.setSelected(true);
    }//GEN-LAST:event_saYesActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new SuppressActionDialog(new javax.swing.JFrame(), true).show();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField saInt;
    private javax.swing.JTextField saName;
    private javax.swing.JRadioButton saNo;
    private javax.swing.JRadioButton saYes;
    // End of variables declaration//GEN-END:variables
    
    public void setProperties(String actionName)
    {
         saName.setEnabled(true);
        if(actionName == null)
        {
            saInt.setText(0 + "");
            saYes.setSelected(true);
            saInt.setEnabled(false);
            saNo.setSelected(false);
            saName.setText("");
            saName.setEnabled(true);
        }
        else if(NotifierList.notifiers.get(actionName) == null)
        {
            
            saInt.setText(0 + "");
            saYes.setSelected(true);
            saInt.setEnabled(false);
            saNo.setSelected(false);
            saName.setText("");
            saName.setEnabled(true);
        }
        else
        {
            
            FilterAction action = (FilterAction)NotifierList.notifiers.get(actionName);
            Properties prop = action.getProperties();
            
            String saint = prop.getProperty("suppressInt");
            if(saint == null) saint = "0";
            saInt.setText(saint);
            
            String name = prop.getProperty("name");
            if(name == null) name = "";
            saName.setText(name);
            
            saName.setEnabled(false);
            
            String opt = prop.getProperty("suppressAll");
            if(opt != null && opt.equals("true"))
            {
                saYes.setSelected(true);
                saInt.setEnabled(false);
                saNo.setSelected(false);
            }
            else
            {
                saYes.setSelected(false);
                saInt.setEnabled(true);
                saNo.setSelected(true); 
            }
        }
    }
    
    public Properties getProperties()
    {
        Properties prop = new Properties();
        String name = saName.getText();
        String interval = saInt.getText();
        boolean opt = saYes.isSelected();
        
        prop.put("name", name);
        prop.put("suppressInt", interval);
        prop.put("suppressAll", opt + "");
        
        return prop;
    }
    
}
