/*
$Id: CustomFilterActionDialog.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/
package com.adventnet.nms.eventui;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.eventdb.NotifierList;

public class CustomFilterActionDialog extends javax.swing.JDialog {
    
    AddAction addAction = null;
    
    public CustomFilterActionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public CustomFilterActionDialog(java.awt.Frame parent, boolean modal, AddAction act) { 
        this(parent, modal);
        addAction = act;
    }

    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        program = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(AddAction.customFilter);//No i18n
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel1.setText(NmsClientUtil.GetString("javaui.filter.NotificationName"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel2.setText(NmsClientUtil.GetString("javaui.filter.ProgramName"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        name.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel2.add(name, gridBagConstraints);

        program.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel2.add(program, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel4.setText(NmsClientUtil.GetString("javaui.filter.customfilter"));
        jPanel3.add(jLabel4, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jSeparator2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        jPanel2.add(jPanel3, gridBagConstraints);

        jSeparator1.setPreferredSize(new java.awt.Dimension(330, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jSeparator1, gridBagConstraints);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(10, 10, 10, 10)));
        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jButton6.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jButton6.setText(NmsClientUtil.GetString("javaui.filter.Help"));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton6);

        jPanel7.add(jPanel9, java.awt.BorderLayout.WEST);

        jPanel10.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jButton4.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jButton4.setText(NmsClientUtil.GetString("javaui.filter.OK"));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel10.add(jButton4);

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
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(jPanel7, gridBagConstraints);

        jLabel3.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel3, gridBagConstraints);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        this.dispose();
      addAction.addNew=false;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String nname = name.getText();
        
        Properties prop = getProperties();
        if (prop == null)
        {
            return;
        }

        
        boolean flag = addAction.addNewAction(prop, AddAction.customFilter);//No i18n
        if(flag)
        {
            this.setVisible(false);
            this.dispose();
        }
        else
        {
            return;
        }
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt)
    {
	       JPopupMenu jp = new JPopupMenu();
       
       JMenuItem mi = new JMenuItem("Help");//No i18n
       JPanel panel = new JPanel();

       String msgTxt = "<html>Specify your Event/Alert filter action <br>class name and the argument(s) (if any) here. <br>Refer Developer Guide for details</html>";//No i18n
       
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
       jp.show(jButton6,-60,-60);
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField name;
    private javax.swing.JTextField program;
    // End of variables declaration//GEN-END:variables
    
    
    public Properties getProperties()
    {
        Properties prop = new Properties();
        
        String nname = name.getText();
        if(AddAction.checkNull(name, "Notification Name"))// No i18n
        {
            return null;
        }
        prop.setProperty("name", nname);
        
        String nprogram = program.getText();
        if(AddAction.checkNull(program, "Program Name"))//No i18n
        {
            return null;
        }
        prop.setProperty("userclass", nprogram);
               
        return prop;
    }
    
    public void setProperties(String actionName)
    {
        name.setEnabled(true);
        Properties prop = new Properties();
        if(actionName != null && NotifierList.notifiers.get(actionName) != null)
        {
            FilterAction action = (FilterAction)NotifierList.notifiers.get(actionName);
            prop = action.getProperties();
        }
        
        String nname = prop.getProperty("name");
        if(nname == null) 
	    {
		nname = "";
	    }
	else
	    {
		name.setEnabled(false);
	    }
        name.setText(nname);
        
        String nprogram = prop.getProperty("userclass");
        if(nprogram == null) nprogram = "";
        program.setText(nprogram);
    }
}
