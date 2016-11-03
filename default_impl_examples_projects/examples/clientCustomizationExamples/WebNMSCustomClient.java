/* $Id: WebNMSCustomClient.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package com.adventnet.nms.example;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.ImageIcon;

import java.awt.event.*;

import java.awt.*;
import java.io.File;

import com.adventnet.nms.startclient.WebNMSClient;
import com.adventnet.nms.startclient.ClientConnectionException;

/**
 * This class is just an example application to show how to 
 * use the WebNMSClient class for creating custom Java Application
 * client.
 * <p>
 * An ideal implementation of the Custom client would be to get
 * the username,password,host,port etc as some UI inputs and pass
 * them as args for the WebNMSClient constructor.This would enable
 * the custom client to estbalish a connection with the WebNMS server
 * and start the WebNMS JAVA UI client.
 */

public class WebNMSCustomClient extends JDialog
{   
    ImageIcon imgIcon =null;
    Image img = null;
    JLabel imag=null;

    JTextField host,port,language,country,userid,passwd = null;
    JButton connect, cancel=null;

    WebNMSCustomClient()
    {
        getContentPane().setLayout(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String filename = "." + File.separator + "images" + File.separator + "loginimage.jpg";
        imgIcon = new ImageIcon(filename);
        img = imgIcon.getImage().getScaledInstance(200,250,Image.SCALE_FAST);
        imag = new JLabel(new ImageIcon(img)); imag.setBorder(new EtchedBorder());
        imag.setPreferredSize(new Dimension(200,getPreferredSize().width));
        
        getContentPane().add(imag,BorderLayout.WEST);
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension winSize = getPreferredSize();
        int wx = (scrSize.width - 575) / 2; int wy = (scrSize.height - 275) / 2;
        setBounds(wx,wy,575,275);

        JPanel login = new JPanel(new GridBagLayout());
        login.setBorder(new EtchedBorder());

        gbc.gridx=0; gbc.gridy=0; 
        gbc.insets = new Insets(5,3,5,5);
        gbc.anchor=GridBagConstraints.WEST;
        gbc.weightx=1;
        JLabel hostLabel = new JLabel("Host ");
        login.add(hostLabel,gbc);

        host = new JTextField("localhost",10);
        gbc.gridx=1; gbc.gridy=0; 
        gbc.insets = new Insets(5,5,5,0);
        login.add(host,gbc);

        gbc.gridx=2; gbc.gridy=0; 
        gbc.insets = new Insets(5,3,5,5);
        JLabel portLabel = new JLabel("Port ");
        login.add(portLabel,gbc);

        port = new JTextField("9090",10);
        gbc.gridx=3; gbc.gridy=0; 
        gbc.insets = new Insets(5,5,5,0);
        login.add(port,gbc);

        gbc.gridx=0; gbc.gridy=1; 
        gbc.insets = new Insets(5,3,5,5);
        JLabel langLabel = new JLabel("Language ");
        login.add(langLabel,gbc);

        language = new JTextField("English",10);
        language.setEditable(false);
        gbc.gridx=1; gbc.gridy=1; 
        gbc.insets = new Insets(5,5,5,0);
        login.add(language,gbc);

        gbc.gridx=2; gbc.gridy=1; 
        gbc.insets = new Insets(5,3,5,5);
        JLabel counLabel = new JLabel("Country ");
        login.add(counLabel,gbc);

        country = new JTextField("US",10);
        country.setEditable(false);
        gbc.gridx=3; gbc.gridy=1; 
        gbc.insets = new Insets(5,5,5,0);
        login.add(country,gbc);

        gbc.gridx=0; gbc.gridy=2; 
        gbc.insets = new Insets(5,3,5,5);
        JLabel userLabel = new JLabel("User Name ");
        login.add(userLabel,gbc);

        userid = new JTextField(15);
        gbc.gridx=1; gbc.gridy=2; gbc.gridwidth=3; gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,0);
        login.add(userid,gbc);

        gbc.gridx=0; gbc.gridy=3; 
        gbc.insets = new Insets(5,3,5,5);
        JLabel passwdLabel = new JLabel("Password ");
        login.add(passwdLabel,gbc);

        passwd = new JTextField(15);
        gbc.gridx=1; gbc.gridy=3; gbc.gridwidth=3; gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,0);
        login.add(passwd,gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        connect = new JButton("Connect to Server"); connect.setMnemonic('C');
        cancel = new JButton("Quit"); cancel.setMnemonic('Q');
        buttons.add(connect); buttons.add(cancel);

        gbc.gridx=0; gbc.gridy=4; gbc.gridwidth=4; gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(25,20,10,20); gbc.anchor=GridBagConstraints.SOUTH;
        login.add(buttons,gbc);

        getContentPane().add(login,BorderLayout.CENTER);
        setTitle("WebNMS Login");
        setVisible(true);
        setResizable(false);
        userid.requestFocus();

        cancel.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    hide(); dispose();
                    System.exit(0);
                }
            });

        connect.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    String uname = userid.getText().trim();
                    String pword = passwd.getText().trim();
                    String hst = host.getText().trim();
                    String prt = port.getText().trim();
                    String cntry = country.getText().trim();
                    String lang = language.getText().trim();
                    try
                    {
                        WebNMSClient n = new WebNMSClient(uname,pword,hst,prt,lang,cntry,true);
                        //username, password, host, port, language, country, enable Java console window
                        hide();
                        n.doconnect();
                    }
                    catch(ClientConnectionException cce)
                    {
                        JOptionPane.showMessageDialog(null,cce.getMessage());
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    } 
                }
            });
    }

    /**
     * The main  method starts just calls the WebNMSClient
     * with some default args to start the application client.
     */
    public static void main(String args[])
    {
        WebNMSCustomClient wcc = new WebNMSCustomClient();
    }
}
