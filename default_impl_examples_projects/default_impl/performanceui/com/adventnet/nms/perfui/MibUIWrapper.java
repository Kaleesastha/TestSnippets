//$Id: MibUIWrapper.java,v 1.1.4.3 2013/04/02 13:59:11 thiyagu.s Exp $
package com.adventnet.nms.perfui;

import com.adventnet.nms.xmlui.NmsPropertiesPanel;
import com.adventnet.nms.util.NmsClientUtil;

import com.adventnet.snmp.ui.MibBrowser;
import com.adventnet.snmp.ui.MibTree;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author vijayalakshmiv
 */
public class MibUIWrapper extends javax.swing.JDialog {

	
    
    private javax.swing.JScrollPane jScrollPane1;
                 
    private String oid="";
    private MibTree tree;
    private NmsPropertiesPanel panel;
    
    public MibUIWrapper(NmsPropertiesPanel panel) {
        this.panel=panel;
        initComponents();
        loadMib();
        
    }

                         
    private void initComponents() {

    	JPanel jPanel1 = new JPanel();
    	setModal(true);
        jScrollPane1 = new javax.swing.JScrollPane();
        JPanel jPanel2 = new JPanel();
        JButton Add  = new JButton();
        JButton Cancel = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 500));
        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 40));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        Add.setFont(NmsClientUtil.getFont()); // NO I18N
        Add.setText("Ok");// NO I18N
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });
        jPanel2.add(Add);

        Cancel.setFont(NmsClientUtil.getFont()); 
        Cancel.setText("Cancel");//NO I18N
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });
        jPanel2.add(Cancel);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }                      

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    	try
    	{
        oid=getSelectedOID();
       if(!oid.equals(""))
        {
           
            JTextField tf= (JTextField) panel.getComponentWithProp("oid");//NO I18N
            tf.setText(oid);
            
           dispose();
        }
       else{
    	   JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.mibbrowser.selectoid.error"));
        }
    	}
    	catch(Exception e)
    	{
    		JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.mibbrowser.selectoid.error"));
    	}
      }                                   

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        dispose();
    }                                      

    public void loadMib()
    {
        MibBrowser mb = new MibBrowser();
        try {
            tree=mb.getMibTree();
            jScrollPane1.add(mb);
            jScrollPane1.setViewportView(mb);
            jScrollPane1.updateUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public String getSelectedOID()
    {
        return tree.getSelectedMibNode().getNumberedOIDString();
    }

                         
    
}

