/*
$Id: SendTrapActionDialog.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/
package com.adventnet.nms.eventui;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import com.adventnet.nms.eventdb.FilterAction;
import com.adventnet.nms.eventdb.SendTrap;
import com.adventnet.nms.eventdb.NotifierList;
import com.adventnet.nms.util.NmsClientUtil;

public class SendTrapActionDialog extends javax.swing.JDialog {
   
    Properties cprop = null;
    AddAction addAction = null;
    Vector vblist = new Vector();
    
    public SendTrapActionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
        public SendTrapActionDialog(java.awt.Frame parent, boolean modal, AddAction act) {
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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        stname = new javax.swing.JTextField();
        distination = new javax.swing.JTextField();
        port = new javax.swing.JTextField();
        community = new javax.swing.JTextField();
        time = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lenterprise = new javax.swing.JLabel();
        lgeneric = new javax.swing.JLabel();
        lspecific = new javax.swing.JLabel();
        specificType = new javax.swing.JTextField();
        genericType = new javax.swing.JTextField();
        enterprise = new javax.swing.JTextField();
        v1 = new javax.swing.JRadioButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        ltrap = new javax.swing.JLabel();
        trapOID = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        v2 = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        helpButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(AddAction.sendTrapAction);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel1.setText(NmsClientUtil.GetString("javaui.filter.NotificationName"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel2.setText(NmsClientUtil.GetString("javaui.filter.DestinationPort"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel3.setText(NmsClientUtil.GetString("javaui.filter.TrapCommunity"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel8.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel8.setText(NmsClientUtil.GetString("javaui.filter.SysUpTime"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel9.setText(NmsClientUtil.GetString("javaui.filter.TrapDestination"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        jPanel2.add(jLabel9, gridBagConstraints);

        stname.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        stname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stnameActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 20);
        jPanel2.add(stname, gridBagConstraints);

        distination.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        distination.setText("localhost");//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 20);
        jPanel2.add(distination, gridBagConstraints);

        port.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        port.setText("162");//No i18n
        port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 20);
        jPanel2.add(port, gridBagConstraints);

        community.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        community.setText("public");//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 20);
        jPanel2.add(community, gridBagConstraints);

        time.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        time.setText("0");//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 20);
        jPanel2.add(time, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel10.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel10.setText(NmsClientUtil.GetString("javaui.filter.SendTrapAction"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel3.add(jLabel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jSeparator1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        jPanel2.add(jPanel3, gridBagConstraints);

        jSeparator2.setPreferredSize(new java.awt.Dimension(150, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel2.add(jSeparator2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel11.setText(NmsClientUtil.GetString("javaui.filter.variablebindinglist"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel4.add(jLabel11, gridBagConstraints);

        jButton1.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jButton1.setText(NmsClientUtil.GetString("javaui.filter.List"));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel4.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        jPanel2.add(jPanel4, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(new javax.swing.border.EtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        lenterprise.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        lenterprise.setText(NmsClientUtil.GetString("javaui.filter.Enterprise"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 3, 5, 1);
        jPanel11.add(lenterprise, gridBagConstraints);

        lgeneric.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        lgeneric.setText(NmsClientUtil.GetString("javaui.filter.GenericType"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 5, 1);
        jPanel11.add(lgeneric, gridBagConstraints);

        lspecific.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        lspecific.setText(NmsClientUtil.GetString("javaui.filter.SpecificType"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 10, 1);
        jPanel11.add(lspecific, gridBagConstraints);

        specificType.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        specificType.setText("1001");//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 10, 8);
        jPanel11.add(specificType, gridBagConstraints);

        genericType.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        genericType.setText("6");//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 5, 8);
        jPanel11.add(genericType, gridBagConstraints);

        enterprise.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        enterprise.setText("11");//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 3, 5, 8);
        jPanel11.add(enterprise, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel13.add(jPanel11, gridBagConstraints);

        v1.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        v1.setSelected(true);
        v1.setText(NmsClientUtil.GetString("javaui.filter.V1settings"));
        v1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                v1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        jPanel13.add(v1, gridBagConstraints);

        jPanel6.add(jPanel13);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jPanel12.setBorder(new javax.swing.border.EtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        ltrap.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        ltrap.setText(NmsClientUtil.GetString("javaui.filter.TrapOID"));
        ltrap.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 3, 5, 0);
        jPanel12.add(ltrap, gridBagConstraints);

        trapOID.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        trapOID.setText("0");//No i18n
        trapOID.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 8);
        jPanel12.add(trapOID, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel12.add(jLabel12, gridBagConstraints);

        jPanel14.add(jPanel12, java.awt.BorderLayout.CENTER);

        v2.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        v2.setText(NmsClientUtil.GetString("javaui.filter.V2Csettings"));
        v2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                v2ActionPerformed(evt);
            }
        });

        jPanel14.add(v2, java.awt.BorderLayout.NORTH);

        jPanel6.add(jPanel14);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 0, 8);
        jPanel2.add(jPanel6, gridBagConstraints);

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

        jButton5.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jButton5.setText(NmsClientUtil.GetString("javaui.filter.Cancel"));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel10.add(jButton5);

        jPanel7.add(jPanel10, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(jPanel7, gridBagConstraints);

        jLabel4.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel4, gridBagConstraints);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
       JPopupMenu jp = new JPopupMenu();
       
       JMenuItem mi = new JMenuItem("Help");//No i18n
       JPanel panel = new JPanel();

       String msgTxt = "<html>Sends V1 or V2 trap to the specified destination and port with <br>event/alert properties as varbinds. <br>Refer Admin Guide for details.</html>";//No i18n
       
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


    }//GEN-LAST:event_helpButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        String name = stname.getText();
        if(AddAction.checkNull(stname, "Notification Name"))//No i18n
        {
            return;
        }
        Properties prop = getProperties();
        
        boolean flag = addAction.addNewAction(prop, AddAction.sendTrapAction);//No i18n
        
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        this.dispose();
     addAction.addNew=false;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void v1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_v1ActionPerformed
        v1.setSelected(true);
        enterprise.setEnabled(true);
        lenterprise.setEnabled(true);
        genericType.setEnabled(true);
        lgeneric.setEnabled(true);
        specificType.setEnabled(true);
        lspecific.setEnabled(true);
        
        v2.setSelected(false);
        trapOID.setEnabled(false);
        ltrap.setEnabled(false);
    }//GEN-LAST:event_v1ActionPerformed

    private void v2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_v2ActionPerformed
        // TODO add your handling code here:
        v2.setSelected(true);
        trapOID.setEnabled(true);
        ltrap.setEnabled(true);
        v1.setSelected(false);
        enterprise.setEnabled(false);
        lenterprise.setEnabled(false);
        genericType.setEnabled(false);
        lgeneric.setEnabled(false);
        specificType.setEnabled(false);
        lspecific.setEnabled(false);
    }//GEN-LAST:event_v2ActionPerformed

    private void stnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stnameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        VariableBindingList blist = new VariableBindingList(new Frame(), true, vblist);
        blist.setLocation(jButton1.getX() + 50, jButton1.getY() + 450);
        blist.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new SendTrapActionDialog(new javax.swing.JFrame(), true).show();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField community;
    private javax.swing.JTextField distination;
    private javax.swing.JTextField enterprise;
    private javax.swing.JTextField genericType;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lenterprise;
    private javax.swing.JLabel lgeneric;
    private javax.swing.JLabel lspecific;
    private javax.swing.JLabel ltrap;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField port;
    private javax.swing.JTextField specificType;
    private javax.swing.JTextField stname;
    private javax.swing.JTextField time;
    private javax.swing.JTextField trapOID;
    private javax.swing.JRadioButton v1;
    private javax.swing.JRadioButton v2;
    // End of variables declaration//GEN-END:variables
    
    public void setProperties(String actionName)
    {
     stname.setEnabled(true);  
        if(actionName == null)
        {
            stname.setText("");
            distination.setText("localhost");//No i18n
            port.setText("162");//No i18n
            community.setText("public");//No i18n
            time.setText("0");//No i18n
            
            v1.setSelected(true);
            enterprise.setText("11");//No i18n
            enterprise.setEnabled(true);
            lenterprise.setEnabled(true);
            specificType.setText("1001");//No i18n
            specificType.setEnabled(true);
            lspecific.setEnabled(true);
            genericType.setText("6");//No i18n
            genericType.setEnabled(true);
            lgeneric.setEnabled(true);
            
            v2.setSelected(false);
            trapOID.setText("");
            trapOID.setEnabled(false);
            ltrap.setEnabled(false);
            
            vblist = new Vector();
        }
        else if(NotifierList.notifiers.get(actionName) == null)
        {
            stname.setText("");
            distination.setText("localhost");//No i18n
            port.setText("162");//No i18n
            community.setText("public");//No i18n
            time.setText("0");//No i18n
            
            v1.setSelected(true);
            enterprise.setText("11");//No i18n
            enterprise.setEnabled(true);
            lenterprise.setEnabled(true);
            specificType.setText("1001");//No i18n
            specificType.setEnabled(true);
            lspecific.setEnabled(true);
            genericType.setText("6");//No i18n
            genericType.setEnabled(true);
            lgeneric.setEnabled(true);
            
            v2.setSelected(false);
            trapOID.setText("");
            trapOID.setEnabled(false);
            ltrap.setEnabled(false);
            
            vblist = new Vector();
        }
        else
        {
            SendTrap action = (SendTrap)NotifierList.notifiers.get(actionName);
            Properties prop = action.getProperties();
            
            cprop = prop;
            
            String name = prop.getProperty("name");
            if(name == null) name = "";
            stname.setText(name);
            stname.setEnabled(false);
            String dist = prop.getProperty("peername");
            if(dist == null) dist = "localhost";
            distination.setText(dist);
            
            String gport = prop.getProperty("trap_port");
            if(gport == null) gport = "162";
            port.setText(gport);
            
            String comty = prop.getProperty("community");
            if(comty == null) comty = "public";
            community.setText(comty);
            
            String ti = prop.getProperty("timeticks");
            if(ti == null) ti = "0";
            time.setText(ti);
            
            String ver = prop.getProperty("version");
            if(ver == null) ver = "v1";
            
            if(ver.equals("v1"))
            {
                v1.setSelected(true);
                
                String enter = prop.getProperty("enterprise");
                if(enter == null) enter = "11";
                enterprise.setText(enter);
                enterprise.setEnabled(true);
                lenterprise.setEnabled(true);
                
                String st = prop.getProperty("specific");
                if(st == null) st = "1001";
                specificType.setText(st);
                specificType.setEnabled(true);
                lspecific.setEnabled(true);
                
                String gt = prop.getProperty("generic");
                if(gt == null) gt = "6";
                genericType.setText(gt);
                genericType.setEnabled(true);
                lgeneric.setEnabled(true);
            
                v2.setSelected(false);
                trapOID.setText("");
                trapOID.setEnabled(false);
                ltrap.setEnabled(false);
            }
            else
            {
                v1.setSelected(false);
                enterprise.setText("");
                enterprise.setEnabled(false);
                lenterprise.setEnabled(false);
                specificType.setText("");
                specificType.setEnabled(false);
                lspecific.setEnabled(false);
                genericType.setText("");
                genericType.setEnabled(false);
                lgeneric.setEnabled(false);
            
                v2.setSelected(true);
                
                String tr = prop.getProperty("trapOid");
                if(tr == null) tr = "";
                trapOID.setText(tr);
                trapOID.setEnabled(true);
                ltrap.setEnabled(true);
            }
            
            vblist = new Vector();
            String v = "vb[0]";//No i18n
            int i = 0;
            
            while(true)
            {
                String st = v + i;
            
                String str = prop.getProperty(st);
                if(str != null)
                {
                    String oid      = prop.getProperty("vb[0]" + i);
                    String snmptype = prop.getProperty("vb[1]" + i);
                    String value    = prop.getProperty("vb[2]" + i);
                    String[] strm   = {oid, snmptype, value};
                    
                    vblist.add(strm);
                    i++;
                }
                else
                {
                    break;
                }
            }
        }
    }
    
    public Properties getProperties()
    {
        Properties prop = new Properties();
        
        String gstname = stname.getText();
        if(AddAction.checkNull(stname, "Notification Name"))
        {
            return null;
        }
        prop.setProperty("name", gstname);
        
        String gdistination = distination.getText();
        if(AddAction.checkNull(distination, "Trap Distination"))
        {
            return null;
        }
        prop.setProperty("peername", gdistination);
        
        String gport = port.getText();
        if(AddAction.checkNumber(port, "Destination Port") || AddAction.checkNumber(port, "Destination Port"))
        {
            return null;
        }
        prop.setProperty("trap_port", gport);
        
        String gcommunity = community.getText();
        prop.setProperty("community", gcommunity);
        
        String gtime = time.getText();
        if(AddAction.checkNumber(time, "SysUp Time") || AddAction.checkNumber(time, "SysUp Time"))
        {
            return null;
        }
        prop.setProperty("timeticks", gtime);
        
        boolean isV1 = v1.isSelected();
        if(isV1)
        {
            prop.setProperty("version", "v1");

            String genterprise = enterprise.getText();
            //if(AddAction.checkNull(enterprise, "Enterprise") || AddAction.checkNumber(enterprise, "Enterprise"))
            if(AddAction.checkNull(enterprise, "Enterprise") )
            {
                return null;
            }
            prop.setProperty("enterprise", genterprise);
            
            String gspecificType = specificType.getText();
            if(AddAction.checkNull(specificType,"Specific Type") || AddAction.checkNumber(specificType, "Specific Type"))
            {
                return null;
            }
            prop.setProperty("specific", gspecificType);
            
            String ggenericType = genericType.getText();
            if(AddAction.checkNull(genericType, "Generic Type") || AddAction.checkNumber(genericType, "Generic Type"))
            {
                return null;
            }
            prop.setProperty("generic", ggenericType);
        }
        else
        {
            prop.setProperty("version", "v2c");
            String gtrapOID = trapOID.getText();
            if(AddAction.checkNull(trapOID, "Trap OID"))
            {
                return null;
            }
            prop.setProperty("trapOid", gtrapOID);
        }
        
        int size = vblist.size();
        
        for(int i = 0; i < size; i++)
        {
            String[] vb = (String[])vblist.elementAt(i);
            prop.setProperty("vb[0]" + i, vb[0]);
            prop.setProperty("vb[1]" + i, vb[1]);
            prop.setProperty("vb[2]" + i, vb[2]);
        }
        return prop;
    }
}
