//$Id: MainPerfPanel.java,v 1.1.4.6 2013/08/08 10:17:57 karen.r Exp $
 

package com.adventnet.nms.perfui;



import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSeparator;

import java.net.MalformedURLException;
import java.net.URL;

import java.awt.Color;
import java.awt.Dimension;

import com.adventnet.nms.util.NmsClientUtil;
/**
 *
 * @author vijayalakshmiv
 */
public class MainPerfPanel extends javax.swing.JDialog {

    /** Creates the main perf gui frame**/
	
	private JButton help = new JButton();
    public MainPerfPanel() {
    	super(NmsClientUtil.getParentFrame());
        initComponents();
     
 }

    private void initComponents() {

        JPanel jPanel6 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JSeparator jSeparator3 = new javax.swing.JSeparator();
        JPanel jPanel5 = new JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JSeparator jSeparator1 = new javax.swing.JSeparator();
        JPanel jPanel1 = new JPanel();
        JSeparator jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(211, 222, 240));
        jPanel4.setPreferredSize(new java.awt.Dimension(20, 10));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setPreferredSize(new java.awt.Dimension(1, 40));
        jPanel4.add(jSeparator3, java.awt.BorderLayout.LINE_START);

        jPanel3.add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel5.setBackground(new java.awt.Color(211, 222, 240));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font(NmsClientUtil.getFont().getName(), 1, 14)); // NO I18N //fix for garbled font
        jLabel1.setText(NmsClientUtil.GetString("javaui.perfgui.header.title"));
        jPanel5.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jLabel2.setFont(NmsClientUtil.getFont()); // NO I18N
        jLabel2.setText(NmsClientUtil.GetString("javaui.perfgui.label.description"));
        jPanel5.add(jLabel2, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setPreferredSize(new java.awt.Dimension(0, 1));
        jPanel3.add(jSeparator1, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel6.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(167, 50));
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.setBackground(new Color(59, 120, 225));//90,135,226));

        
        String helpImage="../images/help.png";// NO I18N
        try{
            help.setIcon(NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/help.png")));// NO I18N
        }
        catch(MalformedURLException e)
        {
        }
        help.setActionCommand("help");//NO I18N
        help.setBorder(null);
        help.setBorderPainted(false);
        help.setIconTextGap(0);
        jPanel1.add(help, java.awt.BorderLayout.EAST);

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setPreferredSize(new java.awt.Dimension(0, 1));
        jPanel1.add(jSeparator2, java.awt.BorderLayout.PAGE_END);

        jPanel6.add(jPanel1, java.awt.BorderLayout.LINE_START);
        getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_START);
        
        Dimension dim=NmsClientUtil.getScreenSize();
        setLocation(dim.width/4,dim.height/10);
        //Note: commented because this method is available in jDialog only from jdk1.6 onwards
//        try {
//			setIconImage(NmsClientUtil.getImage(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/adventneticon.jpg")));// NO I18N
//			
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		setTitle(NmsClientUtil.GetString("javaui.perfgui.mainframe.title"));
		setFont(NmsClientUtil.getFont());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }
    public JButton getHelpButton()
    {
    	return help;
    }

}
