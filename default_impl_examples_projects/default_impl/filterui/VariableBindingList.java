//$Id: VariableBindingList.java,v 1.2 2007/02/22 15:02:55 srajeswari Exp $
package com.adventnet.nms.eventui;

import java.util.*;
import javax.swing.*;

import com.adventnet.nms.util.NmsClientUtil;


public class VariableBindingList extends javax.swing.JDialog {
    
    Vector vblist = null;
    DefaultListModel listModel = new DefaultListModel();
    boolean addvb = false;
    
    public VariableBindingList(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public VariableBindingList(java.awt.Frame parent, boolean modal, Vector vbl) {
        this(parent, modal);
        vblist = vbl;
        //jList1.setModel(listModel);
        setProperties();
        jList1.setModel(listModel);
        if(listModel.size() > 0)
        {
            jList1.setSelectedIndex(0);
        }
    }
   
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        oid = new javax.swing.JTextField();
        value = new javax.swing.JTextField();
        snmptype = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
	jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Variable Binding List");//No i18n
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 1, 1, 1)));
        jList1.setVisibleRowCount(4);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });

        jScrollPane1.setViewportView(jList1);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
	gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
	gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanel4.add(jPanel6, gridBagConstraints);

        //jLabel11.setFont(new java.awt.Font("Dialog", 0, 10));//No i18n
        jLabel11.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel11.setText(NmsClientUtil.GetString("javaui.filter.OIDValue"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel4.add(jLabel11, gridBagConstraints);

        //jLabel12.setFont(new java.awt.Font("Dialog", 0, 10));//No i18n
        jLabel12.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel12.setText(NmsClientUtil.GetString("javaui.filter.SNMPType"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel4.add(jLabel12, gridBagConstraints);

        //jLabel13.setFont(new java.awt.Font("Dialog", 0, 10));//No i18n
        jLabel13.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel13.setText(NmsClientUtil.GetString("javaui.filter.SetValue"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel4.add(jLabel13, gridBagConstraints);

        //oid.setFont(new java.awt.Font("Dialog", 0, 10));//No i18n
        oid.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        oid.setText("1.1.0");//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel4.add(oid, gridBagConstraints);

        //value.setFont(new java.awt.Font("Dialog", 0, 10));//No i18n
        value.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel4.add(value, gridBagConstraints);

        //snmptype.setFont(new java.awt.Font("Dialog", 0, 10));//No i18n
        snmptype.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        snmptype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "STRING", "IPADDRESS", "OPAQUE", "OBJID", "INTEGER", "GAUGE", "COUNTER", "TIMETICKS" }));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel4.add(snmptype, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        jPanel4.add(jSeparator2, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        //jLabel1.setFont(new java.awt.Font("Dialog", 0, 10));//No i18n
        jLabel1.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel1.setText("Variable Binding List Details");//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jSeparator1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel4.add(jPanel2, gridBagConstraints);
	
        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(10, 10, 10, 10)));
        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel10.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        //jButton4.setFont(new java.awt.Font("Dialog", 0, 10));//No i18n
        jButton4.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jButton4.setText(NmsClientUtil.GetString("javaui.filter.Add"));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel10.add(jButton4);

        //jButton4.setFont(new java.awt.Font("Dialog", 0, 10));//No i18n
        jButton6.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jButton6.setText(NmsClientUtil.GetString("javaui.filter.Delete"));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel10.add(jButton6);


        //jButton5.setFont(new java.awt.Font("Dialog", 0, 10));//No i18n
        jButton5.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jButton5.setText(NmsClientUtil.GetString("javaui.filter.Update"));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel10.add(jButton5);

        //jButton1.setFont(new java.awt.Font("Dialog", 0, 10));//No i18n
        jButton1.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jButton1.setText(NmsClientUtil.GetString("javaui.filter.Close"));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel10.add(jButton1);

        jPanel7.add(jPanel10, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel7, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        
        int idx = jList1.getSelectedIndex();
        if(idx == -1)
        {
            return;
        }
        String[] str = (String[])vblist.elementAt(idx);
        String goid = str[0];
        oid.setText(goid);
        String gtype = str[1];
        snmptype.setSelectedItem(gtype);
        String gvalue = str[2];
        value.setText(gvalue);
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        addvb = true;
        oid.setText("1.1.0");//No i18n
        value.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        /*addvb = true;
        oid.setText("1.1.0");//No i18n
        value.setText("");*/

	int[] items = jList1.getSelectedIndices();
	int itemsize = items.length;
	for(int i = 0; i < itemsize; i++)
	    {
		//jList1.getModel().removeSelectionInterval(items[i], items[i]);
		((DefaultListModel)jList1.getModel()).remove(items[i]);
		vblist.remove(items[i]);
	    }
	
	if(listModel.size() > 0)
	    {
		jList1.setSelectedIndex(0);
	    }
    }


    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        String goid = oid.getText();
        if(AddAction.checkNull(oid, "OID Value"))
        {
            return;
        }
        String gsnmptype = snmptype.getSelectedItem() + "";

        String gvalue = value.getText();
        if(AddAction.checkNull(value, "Set Value"))
        {
            return;
        }
        
        if(!addvb)
        {
            int idx = jList1.getSelectedIndex();
            if(idx == -1)
            {
                return;
            }
            
            String[] str = new String[]{goid, gsnmptype, gvalue};
            vblist.remove(idx);
            vblist.add(idx, str);
            String gstr = goid + " " + gsnmptype + " " + gvalue;
            listModel.remove(idx);
            listModel.add(idx, gstr);
            jList1.setSelectedIndex(idx);
        }
        else
        {
            String[] str = new String[]{goid, gsnmptype, gvalue};
            vblist.addElement(str);
            String gstr = goid + " " + gsnmptype + " " + gvalue;
            listModel.addElement(gstr);
            int size = listModel.getSize();
            jList1.setSelectedIndex(size - 1);
            addvb = false;
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField oid;
    private javax.swing.JComboBox snmptype;
    private javax.swing.JTextField value;
    // End of variables declaration//GEN-END:variables
 
    public void setProperties()
    {
        listModel.clear();
        for(Enumeration enumerate  = vblist.elements(); enumerate.hasMoreElements();)
        {
            String[] str = (String[])enumerate.nextElement();
            String tot = str[0] + " " + str[1] + " " + str[2];
            listModel.addElement(tot);
        }
    }
    
    public Properties getProperties()
    {
        Properties prop = new Properties();
        return prop;
    }
}
