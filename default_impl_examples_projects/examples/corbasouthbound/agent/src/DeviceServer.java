/* $Id: DeviceServer.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
package com.adventnet.nms.example.southboundcorba;
import com.adventnet.nms.example.southboundcorba.device.*; // The package containing our stubs.
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import com.adventnet.nms.util.*;
import javax.swing.border.*;

// DeviceServer will use the naming service.
import org.omg.CosNaming.*;

// The package containing special exceptions thrown by the name service.
import org.omg.CosNaming.NamingContextPackage.*;

// All CORBA applications need these classes.
import org.omg.CORBA.*;

import java.net.InetAddress;

public class DeviceServer 
{
    public static void main(String args[])
    {
        try
        {            
            // Create and initialize the ORB
            ORB orb = ORB.init(args, null);
            
            // Create the servant and register it with the ORB
            DeviceAgent agentRef = new DeviceAgent();
            orb.connect(agentRef);
            
            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);

            InetAddress ipad = java.net.InetAddress.getLocalHost();
            String hostName = ipad.getHostName();
            System.out.println (hostName);
            
            // Bind the object reference in naming
            NameComponent nc = new NameComponent(hostName,"");
            NameComponent[] path = {nc};
            
            ncRef.rebind(path, agentRef);
            
            // Wait for invocations from clients
            java.lang.Object sync = new java.lang.Object();
            synchronized(sync)
            {
                sync.wait();
            }
            
        } 
        catch(Exception e) 
        {
            System.out.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }  
    }
}

class DeviceAgent extends _CORBAAgentImplBase 
{

    JLabel label1, label2, label3, label4,label5;
    JTextField textField1,textField2,textField3,textField5;

    public String status = "clear";
    JComboBox statusList = null;
    String values[] = {"clear","not clear"};
    JDialog jdialog;
    JButton okbutton;
    JLabel jlabel;

    DeviceAgent()
    {
        jdialog = new JDialog();
        jdialog.setTitle(System.getProperty("user.name"));
        jdialog.getContentPane().setLayout(new BorderLayout());
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraint = new GridBagConstraints();
        JPanel jpanel = new JPanel();
        jpanel.setLayout(gridbag);
        jpanel.setBorder(new EtchedBorder());
        
        JPanel statuspanel = new JPanel();
        statuspanel.setLayout(gridbag);
        statuspanel.setBorder(new EtchedBorder());
        
        JPanel labelpanel = new JPanel();
        labelpanel.setLayout(new BorderLayout());
        labelpanel.setBorder(new EtchedBorder());

        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonpanel.setBorder(new EtchedBorder());
        
        labelpanel.add(buttonpanel,BorderLayout.SOUTH);
        jdialog.getContentPane().add(jpanel,BorderLayout.NORTH);
        jdialog.getContentPane().add(statuspanel,BorderLayout.CENTER);
        jdialog.getContentPane().add(labelpanel,BorderLayout.SOUTH);
        
        statusList = new JComboBox(values);  
        statusList.setSelectedIndex(0);
       
        constraint.gridx = 0;
        constraint.gridy = 0;
        
        label1 = new JLabel("CORBA_DEVICE_UserName :");
        constraint.insets = new Insets(10,10,10,10);
        constraint.anchor = GridBagConstraints.WEST;
        jpanel.add(label1,constraint);
        textField1 = new JTextField(8);
        textField1.setEditable(false);
        textField1.setText(System.getProperty("user.name"));
        constraint.gridx = 1;
        constraint.insets = new Insets(10,10,10,10);
        jpanel.add(textField1,constraint);
        
        constraint.gridx = 0;
        constraint.gridy = 1;
        
        label2 = new JLabel("CORBA_AGENT_DeviceType :");
        constraint.insets = new Insets(10,10,10,10);
        constraint.anchor = GridBagConstraints.WEST;
        jpanel.add(label2,constraint);
        textField2 = new JTextField(8);
        textField2.setEditable(false);
        textField2.setText(System.getProperty("os.name"));
        constraint.gridx = 1;
        constraint.insets = new Insets(10,10,10,10);
        jpanel.add(textField2,constraint);
        
        constraint.gridx = 0;
        constraint.gridy = 2;
        
        label3 = new JLabel("CORBA_AGENT_OSArch :");
        constraint.insets = new Insets(10,10,10,10);
        constraint.anchor = GridBagConstraints.WEST;
        jpanel.add(label3,constraint);
        textField3 = new JTextField(8);
        textField3.setEditable(false);
        textField3.setText(System.getProperty("os.arch"));
        constraint.gridx = 1;
        constraint.insets = new Insets(10,10,10,10);
        jpanel.add(textField3,constraint);
        
        constraint.gridx = 0;
        constraint.gridy = 0;

        label5 = new JLabel("Returned Status :");
        constraint.insets = new Insets(10,0,10,10);
        constraint.anchor = GridBagConstraints.WEST;
        statuspanel.add(label5,constraint);
        textField5 = new JTextField(8);
        textField5.setEditable(false);
        constraint.gridx = 1;
        constraint.insets = new Insets(10,10,10,10);
        statuspanel.add(textField5,constraint);

        constraint.gridx = 0;
        constraint.gridy = 1;
        
        label4 = new JLabel("Set Status :");
        constraint.insets = new Insets(10,0,10,10);
        constraint.anchor = GridBagConstraints.WEST;
        statuspanel.add(label4,constraint);

        constraint.gridx = 1;
        constraint.insets = new Insets(10,10,10,10);
        statuspanel.add(statusList,constraint);
        statusList.setSelectedIndex(0);
        statusList.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    status = (String) statusList.getSelectedItem();
                    System.out.println(" Item assigned...." + status);
                }
            });
        
        jlabel = new JLabel("  Please wait for 30 Seconds to see the change  . . .");
        labelpanel.add(jlabel,BorderLayout.NORTH);
  
        okbutton  = new JButton("OK");
        buttonpanel.add(okbutton);
        okbutton.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    jdialog.dispose();
                    System.exit(0);
                }
            });
        jdialog.pack();

        Dimension dimention = Toolkit.getDefaultToolkit().getScreenSize();
        int width =(int) dimention.getWidth();
        int height =(int) dimention.getHeight();
        int w =(int)(width-jdialog.getWidth())/2;
        int h = (int)(height-jdialog.getHeight())/2;
        jdialog.setLocation(w,h);
        jdialog.show();
    }
    
    public String CORBAget (String corbaIdentifier)
    {
        if (!corbaIdentifier.equals("status"))
        {
            return System.getProperty(corbaIdentifier);
        }
        else
        {
            System.out.println(status);
            //            return "clear";
            textField5.setText(status);
            return status;
        }
    }  
}



