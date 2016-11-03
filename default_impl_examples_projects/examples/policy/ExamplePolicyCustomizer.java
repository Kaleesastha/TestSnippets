/*
   $Id: ExamplePolicyCustomizer.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 */


// ExamplePolicyCustomizer.java
package test.policy.policyui;
						  
import  com.adventnet.nms.policyui.PolicyObjectCustomizer;
import com.adventnet.management.policydb.NmsPolicyAPI;

import com.adventnet.nms.util.NmsClientUtil;


import java.util.*;
import java.awt.Window;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;


public class ExamplePolicyCustomizer extends PolicyObjectCustomizer implements ActionListener,ItemListener
{
    private Properties prop = null;

    //  to get Properties
    public Properties getProperties()
    {
        return this.prop;
    }

    //  this sets the property
    public void setProperties(Properties p)
    {
        this.prop = p;
    }
	
    public void init(Properties p)
    {
        this.prop = p;

    }

    /**
     *  This method returns the window . User can construct his own GUI and
     * return their GUI as a window 
     */
    public  Window getCustomizer()
    {
        return getGUI();
		
    }

    JTextArea message = null;
    JTextField period,groupname = null;
    JDialog dialog = null;
    JButton okbutton, cancelbutton,helpbutton = null;
    JComboBox status = null;	
    //   constructs a window
    private Window getGUI()
    {
        Properties p = getProperties();
        dialog = new JDialog(new JFrame(),"ExamplePolicyCustomizer",true);
        JPanel buttPanel = new JPanel();
        
        okbutton = new JButton(NmsClientUtil.GetString("Submit",'S'));
        okbutton.setActionCommand("Submit");
        okbutton.setMnemonic('S');
        cancelbutton = new JButton(NmsClientUtil.GetString("Cancel",'C'));
        cancelbutton.setActionCommand("Cancel");
        cancelbutton.setMnemonic('C');
        helpbutton = new JButton(NmsClientUtil.GetString("Help",'H'));
        helpbutton.setActionCommand("Help");
        helpbutton.setMnemonic('H');
        okbutton.addActionListener(this);
        cancelbutton.addActionListener(this);
        helpbutton.addActionListener(this);
		
        JLabel policyNameLabel = new JLabel(NmsClientUtil.GetString("Policy Name "));
        JLabel policyNameValue = new JLabel((String)p.get("name"));
        JLabel messageLabel = new JLabel(NmsClientUtil.GetString("Message "));
        JLabel groupNameLabel = new JLabel(NmsClientUtil.GetString("Group Name "));
        JLabel policyPeriodLabel = new JLabel(NmsClientUtil.GetString("Period "));
        JLabel statusLabel = new JLabel(NmsClientUtil.GetString("Status "));

        String[] data = {"Enabled", "Disabled"}; 
        status  = new JComboBox(data);
        status.addItemListener(this);
        status.setEditable(false);
        message = new JTextArea(2,20);
        period = new JTextField(20);
        groupname = new JTextField(20);

        message.setText((String)p.get("message"));
        groupname.setText((String)p.get("groupName"));
        period.setText((String)p.get("period in seconds"));
        int st = Integer.parseInt((String)p.get("status"));
        if(st == NmsPolicyAPI.POLICY_ENABLED)
        {
            status.setSelectedItem("Enabled");
        }
        else
        {
            status.setSelectedItem("Disabled");
        }

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setBorder(new EtchedBorder());

        gbc.gridx=0; gbc.gridy=0;
        gbc.gridwidth=1; gbc.gridheight=1;
        gbc.fill=GridBagConstraints.HORIZONTAL ;
        gbc.insets =new Insets(0,5,10,20);
        centerPanel.add(policyNameLabel,gbc);

        gbc.gridx=1; gbc.gridy=0;
        gbc.gridwidth=1; gbc.gridheight=1;
        gbc.fill=GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(0,0,10,0);
        centerPanel.add(policyNameValue,gbc);

        gbc.gridx=0; gbc.gridy=1;
        gbc.gridwidth=1; gbc.gridheight=1;
        gbc.fill=GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(0,5,10,20);
        centerPanel.add(groupNameLabel,gbc);

        gbc.gridx=1; gbc.gridy=1;
        gbc.gridwidth=1; gbc.gridheight=1;
        gbc.fill=GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(0,0,10,0);
        centerPanel.add(groupname,gbc);

        gbc.gridx=0; gbc.gridy=2;
        gbc.gridwidth=1; gbc.gridheight=1;
        gbc.fill=GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(0,5,10,20);
        centerPanel.add(statusLabel,gbc);

        gbc.gridx=1; gbc.gridy=2;
        gbc.gridwidth=1; gbc.gridheight=1;
        gbc.fill=GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(0,0,10,0);
        centerPanel.add(status,gbc);

        gbc.gridx=0; gbc.gridy=3;
        gbc.gridwidth=1; gbc.gridheight=1;
        gbc.fill=GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(0,5,10,20);
        centerPanel.add(policyPeriodLabel,gbc);

        gbc.gridx=1; gbc.gridy=3;
        gbc.gridwidth=1; gbc.gridheight=1;
        gbc.fill=GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(0,0,10,0);
        centerPanel.add(new JScrollPane(period),gbc);

        gbc.gridx=0; gbc.gridy=4;
        gbc.gridwidth=1; gbc.gridheight=1;
        gbc.fill=GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(0,5,10,20);
        centerPanel.add(messageLabel,gbc);

        gbc.gridx=1; gbc.gridy=4;
        gbc.gridwidth=1; gbc.gridheight=1;
        gbc.fill=GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(0,0,10,0);
        centerPanel.add(new JScrollPane(message),gbc);

        buttPanel.add(okbutton);
        buttPanel.add(helpbutton);
        buttPanel.add(cancelbutton);
        		
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(BorderLayout.CENTER,centerPanel);
        dialog.getContentPane().add(BorderLayout.SOUTH,buttPanel);
        dialog.setSize(400,300);
        return dialog;
    }
	
    //  listen to actions
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("Submit"))
        {
            //  once the user finishes all his modifications  dispose the window 
            //	and fire property changed  event.
            //  this is  a indication for WebNMS to proceed further
			
            prop.put("message",message.getText());
            prop.put("groupName",groupname.getText());
            prop.put("period in seconds",period.getText());
            dialog.dispose();  // dispose window
            this.firePropertyChange(); // fire the event
        }
        else if (ae.getActionCommand().equals("Cancel"))
        {
            dialog.dispose();  // do nothing
        }
        else if (ae.getActionCommand().equals("Help"))
        {
            System.out.println(prop);
            System.out.println(" URL : " + prop.get("helpURL"));
            NmsClientUtil.showHelp("help/" + (String)prop.get("helpURL"));
        }

    }

    public void itemStateChanged(ItemEvent e) 
    {
        String stat = (String) status.getSelectedItem();
        if(stat.equals("Enabled"))
        {
            prop.put("status",String.valueOf(NmsPolicyAPI.POLICY_ENABLED));
        }
        else
        {
        prop.put("status",String.valueOf(NmsPolicyAPI.POLICY_DISABLED));
        }       
    }
}
