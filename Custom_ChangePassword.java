import com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.*;
import javax.swing.*;
import java.util.*;
import java.net.*;
/**
 *
 * @author  sivaprakash
 */
public class ChangePassword extends javax.swing.JFrame implements NmsFrame{
    
    public ChangePassword () {
        initComponents ();
    }
    
    public void init(JApplet app)
    {
        show();
    }
    
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        oldPasswd = new javax.swing.JPasswordField();
        newPasswd = new javax.swing.JPasswordField();
        conPasswd = new javax.swing.JPasswordField();

        setTitle("Password Configurator");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.GridLayout());

        jPanel9.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        jPanel6.add(jPanel9, java.awt.BorderLayout.WEST);

        jPanel10.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        jPanel6.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel11.setLayout(new java.awt.GridLayout());

        jPanel11.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 10)));
        okButton.setFont(new java.awt.Font("Dialog", 0, 10));
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        jPanel11.add(okButton);

        cancelButton.setFont(new java.awt.Font("Dialog", 0, 10));
        cancelButton.setText("Cancel");
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(jPanel12, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel1.setText("Password Configurator");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel2.setText("Old Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel3.setText("New Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel4.setText("Confirm Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel1.add(jLabel4, gridBagConstraints);

        jSeparator2.setPreferredSize(new java.awt.Dimension(200, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel1.add(jSeparator2, gridBagConstraints);

        oldPasswd.setFont(new java.awt.Font("Dialog", 0, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(oldPasswd, gridBagConstraints);

        newPasswd.setFont(new java.awt.Font("Dialog", 0, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(newPasswd, gridBagConstraints);

        conPasswd.setFont(new java.awt.Font("Dialog", 0, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(conPasswd, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void cancelButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        String op = oldPasswd.getText();
        String np = newPasswd.getText();
        String cp = conPasswd.getText();
        if(!cp.equals(np))
        {
            JOptionPane.showMessageDialog(this, "New Password and Confirm password should be same");
            newPasswd.setText("");
            conPasswd.setText("");
            newPasswd.requestFocus ();
            return;
        }
        Properties prop = new Properties();
        prop.setProperty("USER_NAME",NmsClientUtil.getUserName());
        
        String host = NmsClientUtil.applet.getDocumentBase().getHost();
        String port = NmsClientUtil.applet.getDocumentBase().getPort() + "";
        
        prop.setProperty("PORT",port);
        prop.setProperty("HOST",host);
        prop.setProperty("PASSWORD",op);
        String hostAddress = "";
        try
        {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        }
        catch(Exception e)
        {
            
        }
        prop.setProperty("HOSTADDRESS",hostAddress);
        try
        {
            NmsClientUtil.authenticateUser(prop);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "The old password is not correct");
            newPasswd.setText("");
            conPasswd.setText("");
            oldPasswd.setText("");
            oldPasswd.requestFocus();
            return;
        }
        
        Properties cprop = new Properties();
        cprop.setProperty("userName",NmsClientUtil.getUserName());
        cprop.setProperty("password",np);
        cprop.setProperty("age", "10");
        NmsClientUtil.changePassword (cprop);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed
    
    private void exitForm (java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit (0);
    }//GEN-LAST:event_exitForm
    
    private javax.swing.JButton cancelButton;
    private javax.swing.JPasswordField conPasswd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField newPasswd;
    private javax.swing.JButton okButton;
    private javax.swing.JPasswordField oldPasswd;
}
