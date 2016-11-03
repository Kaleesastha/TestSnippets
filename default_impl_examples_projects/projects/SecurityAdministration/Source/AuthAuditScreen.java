//$Id:
package com.adventnet.security.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


import com.adventnet.nms.authaudit.AuthAuditBrowser;

public class AuthAuditScreen extends JFrame implements ActionListener
{
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
    public AuthAuditScreen()
    {
        setTitle(AuthMain.resourceBundle.getString("Auth Audit Screen"));
        Image img;
        if((img = com.adventnet.nms.util.NmsClientUtil.getFrameIcon())!= null)
            {
                this.setIconImage(img);
            }
        //setSize(getPreferredSize().width+500, getPreferredSize().height+400);
        setSize(750, 500);
        //setResizable(false);
    }
    private AuthAuditBrowser auditBrowser = null;
    public static void main(String arg[])
    {
        /*
        JFrame frame = new JFrame();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Edit");
        menu.add("Modify View");
        menu.add("Audit Details");
        menuBar.add(menu);
        frame.getContentPane().add(menuBar, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Test "));
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().setSize(400,400);
        frame.setVisible(true);               
        */
        AuthMain.resourceBundle = com.adventnet.apiutils.Utility.getBundle("","en",null);
        AuthAuditScreen ins = new AuthAuditScreen();
        ins.setUpGUI(new AuthAuditBrowser());
        ins.setVisible(true);
    }
    
    public void setUpGUI(AuthAuditBrowser auditBrowser)
    {
        this.auditBrowser = auditBrowser;
        JMenuBar menuBar = new JMenuBar();
        JMenu viewMenu = new JMenu(AuthMain.resourceBundle.getString("View"));
        JMenu editMenu = new JMenu(AuthMain.resourceBundle.getString("Edit"));
        JMenuItem audDetails = new JMenuItem(AuthMain.resourceBundle.getString("Audit Details"));
        audDetails.addActionListener(this);
        audDetails.setActionCommand("Audit Details");
        JMenuItem search = new JMenuItem(AuthMain.resourceBundle.getString("Search"));
        search.addActionListener(this);
        search.setActionCommand("Search");
	search.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,KeyEvent.CTRL_MASK));
        //menu.add(modView);
        viewMenu.add(audDetails);
        editMenu.add(search);
        menuBar.add(viewMenu);
        menuBar.add(editMenu);
        
	
	JPanel buttonPanel=new JPanel();
	buttonPanel.setLayout(new GridBagLayout());
	
	JButton exportAudit=new JButton(AuthMain.resourceBundle.getString("Export"));//No Internationalization
	exportAudit.addActionListener(this);
    exportAudit.setActionCommand("Export");//No Internationalization 
	inset = new Insets(5,5,5,5);
	setConstraints(0,1,1,1,0.05,0.0,cons.EAST,cons.NONE,inset,0,0);
	buttonPanel.add(exportAudit,cons);

	JButton refreshAudit=new JButton(AuthMain.resourceBundle.getString("Refresh"));//No Internationalization
	refreshAudit.addActionListener(this);
	refreshAudit.setActionCommand("Refresh");//No Internationalization
	inset = new Insets(5,5,5,5);
	setConstraints(1,1,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
	buttonPanel.add(refreshAudit,cons);
						
	
	JButton clearAudit=new JButton(AuthMain.resourceBundle.getString("Clear Audit"));//No Internationalization
	clearAudit.addActionListener(this);
	clearAudit.setActionCommand("Clear Audit");
	inset = new Insets(5,5,5,5);
	setConstraints(2,1,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
	buttonPanel.add(clearAudit,cons);
        buttonPanel.setBorder(new TitledBorder(""));
		

        auditBrowser.setBorder(new TitledBorder(""));
        getContentPane().add(menuBar, BorderLayout.NORTH);
        getContentPane().add(auditBrowser, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().setSize(400,400);
    }
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        if(command.equals("Search"))
        {
            auditBrowser.actionPerformed(ae);
            auditBrowser.addShowAllButton();
        }
        else if(command.equals("Audit Details"))
            {
                ActionEvent ae1 = new ActionEvent(ae.getSource(), ae.getID(), "Attribute Audit");
                auditBrowser.actionPerformed(ae1);
            }
        else if(command.equals("Clear Audit"))
            {
                ActionEvent ae1 = new ActionEvent(ae.getSource(), ae.getID(), "Clear Audit");
                auditBrowser.actionPerformed(ae1);
            }
	else if(command.equals("Export"))
	{
		auditBrowser.actionPerformed(ae);
	}
	else if(command.equals("Refresh"))
	{
		ActionEvent ae1 = new ActionEvent(ae.getSource(), ae.getID(),"Refresh");//No Internationalization
		auditBrowser.actionPerformed(ae1);
	}
        
    }

    public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
    {
	//<Begin_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>
	    cons.gridx = x;
	    cons.gridy = y;
	    cons.gridwidth = width;
	    cons.gridheight = height;
	    cons.weightx = wtX;
	    cons.weighty = wtY;
	    cons.anchor = anchor;
	    cons.fill = fill;
	    cons.insets = inset;
	    cons.ipadx = padX;
	    cons.ipady = padY;
	    //<End_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>
    }                                                                                                   
}
