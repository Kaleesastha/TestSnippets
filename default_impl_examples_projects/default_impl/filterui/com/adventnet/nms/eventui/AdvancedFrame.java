//$Id: AdvancedFrame.java,v 1.4 2010/11/10 10:42:42 aravinds Exp $
package com.adventnet.nms.eventui;

import java.util.Properties;
import javax.swing.*;
import com.webnms.nms.eventui.AddFilterAction;

import com.adventnet.nms.util.NmsClientUtil;
public class AdvancedFrame extends javax.swing.JDialog {
    Properties prop = new Properties();
    
    public AdvancedFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public AdvancedFrame(java.awt.Frame parent, boolean modal, Properties p) {
        this(parent, modal);
        if(!p.containsKey("text"))
	{
	prop =p;
	setProperties();
	}
	else
	{
	p.setProperty("message",p.getProperty("text"));
	p.remove("text"); //no i18n
	prop = p;
        setProperties();
	}
    }
    
    public void setProperties()
    {
        String message = prop.getProperty("message");
	 // Fix for the issue reported in the Issue Manager. Message ID 1160520
        if(message == null) message = "";
        messageTextField.setText(message);
        
        String category = prop.getProperty("category");
        if(category == null) category = "";
        categoryTextField.setText(category);
        
        String domain = prop.getProperty("domain");
        if(domain == null) domain = "";
        domainTextField.setText(domain);
        
        String network = prop.getProperty("network");
        if(network == null) network = "";
        networkTextField.setText(network);
        
        String node = prop.getProperty("node");
        if(node == null) node = "";
        nodeTextField.setText(node);
        
        String entity = prop.getProperty("entity");
        if(entity == null) entity = "";
        entityTextField.setText(entity);
        
        String save = prop.getProperty("saveoption");
        if(save == null) save = "true";
        
        boolean saveOption = true;
        if(save.equals("false")){
            saveOption = false;
        }
    }
    
    public void updateProperties()
    {
        String message = messageTextField.getText();
        if(message == null ) message = "";

	if(!message.equals(""))
	{  
        prop.setProperty("message", message);
                
    }
    else //Issue ID 11348018
    {
    	prop.remove("message"); //No I18N
    }
        
        String category = categoryTextField.getText();
        if(category == null) category = "";

	if(!category.equals(""))
	{
        prop.setProperty("category", category);
    }
    else //Issue ID 11348018
    {
    	prop.remove("category"); //No I18N
    }
        
        String domain = domainTextField.getText();
        if(domain == null) domain = "";

	if(!domain.equals(""))
	{
        prop.setProperty("domain", domain);
    }
    else //Issue ID 11348018
    {
    	prop.remove("domain"); //No I18N
    }
                
        String network = networkTextField.getText();
        if(network == null) network = "";

	if(!network.equals(""))
	{
        prop.setProperty("network", network);
    }
    else //Issue ID 11348018
    {
    	prop.remove("network"); //No I18N
    }
                
        String node = nodeTextField.getText();
        if(node == null) node = "";

	if(!node.equals(""))
	{
        prop.setProperty("node",node);
    }
    else //Issue ID 11348018
    {
    	prop.remove("node"); //No I18N
    }
                
        String entity = entityTextField.getText();
        if(entity == null) entity = "";

	if(!entity.equals(""))
	{
        prop.setProperty("entity", entity);
    }
    else //Issue ID 11348018
    {
    	prop.remove("entity"); //No I18N
    }
    }

    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        messageTextField = new javax.swing.JTextField();
        categoryTextField = new javax.swing.JTextField();
        domainTextField = new javax.swing.JTextField();
        networkTextField = new javax.swing.JTextField();
        nodeTextField = new javax.swing.JTextField();
        entityTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        moreButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Match criteria Properties");//No i18n
        setModal(true);
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel10.setIcon(AddFilterAction.getIcon("images/advancedframe_top.jpg"));//No i18n
        jPanel1.add(jLabel10, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText(NmsClientUtil.GetString("javaui.filter.Message"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel2.setText(NmsClientUtil.GetString("javaui.filter.Category"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel3.setText(NmsClientUtil.GetString("javaui.filter.Domain"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel4.setText(NmsClientUtil.GetString("javaui.filter.Network"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel2.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel5.setText(NmsClientUtil.GetString("javaui.filter.Node"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText(NmsClientUtil.GetString("javaui.filter.Entity"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel2.add(jLabel6, gridBagConstraints);

        messageTextField.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 7, 7);
        jPanel2.add(messageTextField, gridBagConstraints);

        categoryTextField.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 7, 7);
        jPanel2.add(categoryTextField, gridBagConstraints);

        domainTextField.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 7, 7);
        jPanel2.add(domainTextField, gridBagConstraints);

        networkTextField.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 7, 7);
        jPanel2.add(networkTextField, gridBagConstraints);

        nodeTextField.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 7, 7);
        jPanel2.add(nodeTextField, gridBagConstraints);

        entityTextField.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 3, 7, 7);
        jPanel2.add(entityTextField, gridBagConstraints);

        jLabel7.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel7.setText(NmsClientUtil.GetString("javaui.filter.FilterCriteria"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel9.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(12, 3, 7, 7);
        jPanel2.add(jSeparator2, gridBagConstraints);

        jPanel11.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel5.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        jPanel5.add(jSeparator1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 6, 0));

        jPanel7.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        okButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        okButton.setText(NmsClientUtil.GetString("javaui.filter.OK"));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        jPanel7.add(okButton);

        cancelButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        cancelButton.setText(NmsClientUtil.GetString("javaui.filter.Cancel"));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jPanel7.add(cancelButton);

        jPanel4.add(jPanel7, java.awt.BorderLayout.EAST);

        jPanel10.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jPanel10.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        moreButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        moreButton.setText(NmsClientUtil.GetString("javaui.filter.MoreProperties"));
        moreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreButtonActionPerformed(evt);
            }
        });

        jPanel10.add(moreButton);

        jPanel4.add(jPanel10, java.awt.BorderLayout.WEST);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel9.add(jPanel11, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel9, java.awt.BorderLayout.NORTH);

        pack();
    }//GEN-END:initComponents

    private void moreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreButtonActionPerformed
        updateProperties();
        MoreDialog dialog = new MoreDialog((javax.swing.JFrame)this.getParent(), true, prop);
        
        int x = 150, y = 320;
        if(this.getX() < 150)
        {
            x = 0;
        }
	
        dialog.setLocation(this.getX()-x, this.getY() + y);
        dialog.show();
    }//GEN-LAST:event_moreButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        updateProperties();
        setVisible(false);
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField categoryTextField;
    private javax.swing.JTextField domainTextField;
    private javax.swing.JTextField entityTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField messageTextField;
    private javax.swing.JButton moreButton;
    private javax.swing.JTextField networkTextField;
    private javax.swing.JTextField nodeTextField;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
    
}
