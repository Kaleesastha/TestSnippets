//$Id: MoreDialog.java,v 1.2 2010/10/29 13:45:50 swaminathap Exp $
package com.adventnet.nms.eventui;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.lang.reflect.*;
import java.util.*;
import java.awt.event.*;

import com.adventnet.nms.util.NmsClientUtil;

public class MoreDialog extends javax.swing.JDialog {

    java.util.Properties totProp = new java.util.Properties();
    
   public MoreDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
   public MoreDialog(java.awt.Frame parent, boolean modal, java.util.Properties prop) {
        super(parent, modal);
        initComponents();
        totProp = prop;
    }

    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        key = new javax.swing.JTextField();
        value = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        allButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("More Properties");//No i18n
        setModal(true);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel1.setText(NmsClientUtil.GetString("javaui.filter.Propertyname"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel2.setText(NmsClientUtil.GetString("javaui.filter.Propertyvalue"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        key.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(key, gridBagConstraints);

        value.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(value, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(jSeparator2, gridBagConstraints);

        jLabel3.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel3.setText(NmsClientUtil.GetString("javaui.filter.FilterProperties"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel2.add(jPanel8, gridBagConstraints);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(10, 10, 10, 10)));
        jPanel6.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        addButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        addButton.setText(NmsClientUtil.GetString("javaui.filter.Add"));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jPanel5.add(addButton);

        cancelButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        cancelButton.setText(NmsClientUtil.GetString("javaui.filter.Cancel"));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jPanel5.add(cancelButton);

        jPanel6.add(jPanel5, java.awt.BorderLayout.EAST);

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        allButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        allButton.setText(NmsClientUtil.GetString("javaui.filter.ViewAll"));
        allButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allButtonActionPerformed(evt);
            }
        });

        jPanel9.add(allButton);

        jPanel6.add(jPanel9, java.awt.BorderLayout.WEST);

        jPanel3.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 10, 1, 10)));
        jSeparator1.setMinimumSize(new java.awt.Dimension(250, 0));
        jSeparator1.setPreferredSize(new java.awt.Dimension(350, 2));
        jPanel7.add(jSeparator1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents
    JPopupMenu jp = null;
    private void allButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allButtonActionPerformed
        jp = new JPopupMenu();

        JMenuItem it = new JMenuItem("Properties");//No i18n
        
        JTable table = new JTable();  
                     
        JPanel panel = new JPanel(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();  
        table.setModel(model);
        JScrollPane scroll = new JScrollPane(table);
        panel.add(scroll, BorderLayout.CENTER);
        
        table.setRowHeight(21);  
        table.setBackground(Color.white);  
        table.setForeground(Color.black);   
        table.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        scroll.getViewport().setBackground(Color.white);  
        
        ((JTableHeader)table.getTableHeader()).setBackground(Color.gray); 
        ((JTableHeader)table.getTableHeader()).setForeground(Color.white);
        ((JTableHeader)table.getTableHeader()).setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        Vector v = new Vector();        
        v.addElement("Property Name"); //No i18n
        v.addElement("Property Value");//No i18n
        
        model.setDataVector(new Vector(),v);
        
        
        for(Enumeration en = totProp.keys();en.hasMoreElements();)
        {  
            String key = (String)en.nextElement();
            String value = totProp.getProperty(key);
            Vector v1 = new Vector();
            v1.addElement(key);  
            v1.addElement(value);    
            model.addRow(v1);    
        }
        
        int size = totProp.size();
        if(size > 10)
            panel.setPreferredSize(new Dimension(200,size*20+38));    
        else if(totProp.size() <5) 
            panel.setPreferredSize(new Dimension(200,size*20+28)); 
        else 
            panel.setPreferredSize(new Dimension(200,size*20+33));
        
        it.setLayout(new BorderLayout());
        it.add(panel);
        it.setPreferredSize(panel.getPreferredSize());
                
        jp.add(it);
        jp.show(allButton, allButton.getX()-70, allButton.getY()+25);
    }//GEN-LAST:event_allButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String name = key.getText();
        String propValue = value.getText();
        if(name != null && propValue != null && !name.equals(""))
        {
            totProp.setProperty(name, propValue);
        }
	key.setText("");
	value.setText("");
	key.requestFocus();
    }//GEN-LAST:event_addButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton allButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField key;
    private javax.swing.JTextField value;
    // End of variables declaration//GEN-END:variables
    
}
