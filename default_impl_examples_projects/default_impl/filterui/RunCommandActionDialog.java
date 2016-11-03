/*
$Id: RunCommandActionDialog.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/
package com.adventnet.nms.eventui;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import com.adventnet.nms.eventdb.FilterAction;
import com.adventnet.nms.eventdb.NotifierList;
import com.adventnet.nms.eventdb.FilterCommand;
import com.adventnet.nms.util.NmsClientUtil;

public class RunCommandActionDialog extends javax.swing.JDialog {
    AddAction addAction = null;
    
    public RunCommandActionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public RunCommandActionDialog(java.awt.Frame parent, boolean modal, AddAction act) {
        this(parent, modal);
        addAction = act;
    }

    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        command = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        appendOutput = new javax.swing.JCheckBox();
        appendError = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        abort = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        helpButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(AddAction.runCommandAction);//No i18n
        setResizable(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(450, 250));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(376, 300));
        jPanel3.setPreferredSize(new java.awt.Dimension(376, 500));
        jLabel1.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel1.setText(NmsClientUtil.GetString("javaui.filter.NotificationName"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(13, 15, 7, 5);
        jPanel3.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel2.setText(NmsClientUtil.GetString("javaui.filter.SystemCommand"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 7, 5);
        jPanel3.add(jLabel2, gridBagConstraints);

        name.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(13, 6, 10, 15);
        jPanel3.add(name, gridBagConstraints);

        command.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 6, 7, 15);
        jPanel3.add(command, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel4.setText(NmsClientUtil.GetString("javaui.filter.RunCommandAction"));
        jPanel4.add(jLabel4, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jSeparator2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 5, 5);
        jPanel3.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(jSeparator1, gridBagConstraints);

        appendOutput.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        appendOutput.setText(NmsClientUtil.GetString("javaui.filter.appendOutput"));
        appendOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appendOutputActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 6, 5, 15);
        jPanel3.add(appendOutput, gridBagConstraints);

        appendError.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        appendError.setText(NmsClientUtil.GetString("javaui.filter.appendError"));
        appendError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appendErrorActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 5, 15);
        jPanel3.add(appendError, gridBagConstraints);

        jLabel3.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel3.setText(NmsClientUtil.GetString("javaui.filter.AbortAfter"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 13, 5);
        jPanel3.add(jLabel3, gridBagConstraints);

        jPanel1.setLayout(new java.awt.BorderLayout());

        abort.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        abort.setText("60");//No i18n
        abort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abortActionPerformed(evt);
            }
        });

        jPanel1.add(abort, java.awt.BorderLayout.CENTER);

        jLabel5.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel5.setText(NmsClientUtil.GetString("javaui.filter.Seconds"));
        jPanel1.add(jLabel5, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 6, 7, 15);
        jPanel3.add(jPanel1, gridBagConstraints);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

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

        jPanel2.add(jPanel7, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void appendErrorActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appendErrorActionPerformed
        
    }//GEN-LAST:event_appendErrorActionPerformed

    private void appendOutputActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appendOutputActionPerformed
        
    }//GEN-LAST:event_appendOutputActionPerformed

    private void abortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abortActionPerformed
        
    }//GEN-LAST:event_abortActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        String na = name.getText();
        if(AddAction.checkNull(name, "Notification Name"))
        {
            return;
        }
        Properties prop = getProperties();
        boolean flag = addAction.addNewAction(prop, AddAction.runCommandAction);//No i18n
        if(flag)
        {
            this.setVisible(false);
            this.dispose();
        }
        else
        {
            return;
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
        this.dispose();
         addAction.addNew=false;
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
       JPopupMenu jp = new JPopupMenu();
       
       JMenuItem mi = new JMenuItem("Help");//No i18n
       JPanel panel = new JPanel();

       String msgTxt = "<html>Executes a command/batch/script file when <br>matching Events/Alerts are received. <br>Option to append the comand <br>result to the event/alert <br>message is available. <br>The timeout value of command execution <br>is also configurable. <br>Event /alert properties can be <br>specified as command arguments. <br>Refer Admin Guide for details.</html>";//No i18n
       
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

    }//GEN-LAST:

    public static void main(String args[]) {
        new RunCommandActionDialog(new javax.swing.JFrame(), true).show();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField abort;
    private javax.swing.JCheckBox appendError;
    private javax.swing.JCheckBox appendOutput;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField command;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JTextField name;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
    public void setProperties(String actionType){
 name.setEnabled(true); 
        if(actionType == null)
        {
           name.setText(""); 
           command.setText("");
           abort.setText("60");//No i18n
           appendError.setSelected(false);
           appendOutput.setSelected(false);
        }
        else if(NotifierList.notifiers.get(actionType) == null)
        {
           name.setText(""); 
           command.setText("");
           abort.setText("60");//No i18n
           appendError.setSelected(false);
           appendOutput.setSelected(false);
        }
        else
        {
            FilterCommand action = (FilterCommand)NotifierList.notifiers.get(actionType);
            Properties prop = action.getProperties();
            String na = prop.getProperty("name");
            if(na == null) na = "";
            name.setText(na);
	    name.setEnabled(false);
            String cmd  = prop.getProperty("command");
            if(cmd == null) cmd = "";
            command.setText(cmd);
            String atime = prop.getProperty("timeout");
            if(atime == null) atime = "60";//No i18n
            
            abort.setText(atime);
            String err   = prop.getProperty("errappend");
            if(err == null) err = "false";//No i18n
            if(err.equals("true")) 
                appendError.setSelected(true);
            else
                appendError.setSelected(false);
            String app   = prop.getProperty("append");
            if(app == null) app = "false";
            if(app.equals("true"))
                appendOutput.setSelected(true);
            else
                appendOutput.setSelected(false);
        }
    
    }
    
    public java.util.Properties getProperties()
    {
        java.util.Properties prop = new java.util.Properties();
        String na = name.getText();
        prop.setProperty("name", na);
        String cmd = command.getText();
        prop.setProperty("command", cmd);
        String atime = abort.getText();
        prop.setProperty("timeout", atime);
        String aOut  = appendOutput.isSelected() + "";
        prop.setProperty("append", aOut);
        String aErr  = appendError.isSelected() + "";
        prop.setProperty("errappend", aErr);
        return prop;
    }
}
