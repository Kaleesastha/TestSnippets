/*
	$Id: TesterPolicyUI.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 */

//TesterPolicyUI.java
package com.adventnet.nms.policyui;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.*;

import com.adventnet.nms.util.NmsClientUtil;


public class TesterPolicyUI extends JDialog implements ActionListener,WindowListener,ItemListener
{

	private TesterPolicyCustomizer tpc = null;
	private Properties props = null;
	
	private JButton applyButt = new JButton(NmsClientUtil.GetString("Apply"));
	private JButton scheduleButt = new JButton(NmsClientUtil.GetString("Schedule"));
	private JButton cancelButt = new JButton(NmsClientUtil.GetString("Cancel"));
	
	private JTextField typeTxt = new JTextField(20);
	private JTextField communityTxt = new JTextField(20);
	private JComboBox testerCombo = new JComboBox();
	private JTextField policyNameTxt = new JTextField(20);
	private JTextField groupNameTxt = new JTextField(20);
	private JComboBox statusCombo = new JComboBox();
	private JTextField testerClass = new JTextField(20);
 
	private JTextField parentNet = new JTextField(20);
	
    private PolicyCustom policyCustom = null;
	private Vector timeVector = null;

    
	private Font font;

	public TesterPolicyUI(TesterPolicyCustomizer tpc,Properties props) 
	{
		super(NmsClientUtil.getParentFrame(),NmsClientUtil.GetString("Tester Policy"));
		this.props = props;
		this.tpc = tpc;
		font = NmsClientUtil.getFont("DIALOG");//No Internationalisation
		initialize();
		setFontForComponents();
		this.pack();
      	this.setSize(new Dimension(getSize().width + 20,getSize().height + 60));
	}

	private void initialize()
	{
		JPanel criteriaPanel = new JPanel();
		JPanel headerPanel = new JPanel();
		JPanel userPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		this.getContentPane().setLayout(new BorderLayout());
		criteriaPanel.setLayout(gridbag);
		userPanel.setLayout(gridbag);
		headerPanel.setLayout(new BorderLayout());
		buttonPanel.setLayout(gridbag);

		headerPanel.setBorder( new CompoundBorder( new EtchedBorder( EtchedBorder.RAISED ),new EmptyBorder( 5, 5, 5, 5 ) ) );
		buttonPanel.setBorder( new CompoundBorder( new EtchedBorder( EtchedBorder.RAISED ),new EmptyBorder( 5, 5, 5, 5 ) ) );
//	criteriaPanel.setBorder(new TitledBorder(NmsClientUtil.GetString("Match Criteria")));
		
		String headerTxt= NmsClientUtil.GetString("This  policy primarily walks through all the nodes in the database.  Picks up the nodes which matches the criteria given by the user and changes their tester to the tester specified by the user.");

		JLabel lbl = new JLabel(NmsClientUtil.GetString("default"));
		
		JTextArea headerText = new JTextArea();
		headerText.setLineWrap(true);
		headerText.setText(NmsClientUtil.GetString(headerTxt));
		headerText.setFont(font);
		headerText.setEditable(false);
		headerText.setBackground(lbl.getBackground());
		headerText.setForeground(lbl.getForeground());
		headerPanel.add(headerText,BorderLayout.CENTER);

		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets (5,5,5,5);
		
		JLabel policyDetails = new JLabel(NmsClientUtil.GetString("Policy Details"));
		policyDetails.setForeground(Color.black);
		buildConstraints(c,0,0,2,1,0,0);
		criteriaPanel.add(policyDetails,c);
		
		JLabel nameLbl = new JLabel(NmsClientUtil.GetString("Policy Name"));
		nameLbl.setFont(font);
		buildConstraints(c,0,1,1,1,0,0);
		criteriaPanel.add(nameLbl,c);
		
		JLabel groupLbl = new JLabel(NmsClientUtil.GetString("Group Name"));
		groupLbl.setFont(font);
		buildConstraints(c,0,2,1,1,0,0);
		criteriaPanel.add(groupLbl,c);
		
                String val = NmsClientUtil.GetString("Policy Status");
                String label = "Status";
                if( ! val.equals("Policy Status"))
                {
                    label = "Policy Status";
                }
                JLabel statusLbl = new JLabel(NmsClientUtil.GetString(label));
		buildConstraints(c,2,1,1,2,0,0);
		statusLbl.setFont(font);
		criteriaPanel.add(statusLbl,c);
		
		JLabel nodeDetails = new JLabel(NmsClientUtil.GetString("Node Details"));
		nodeDetails.setForeground(Color.black);
		buildConstraints(c,0,3,2,1,0,0);
		criteriaPanel.add(nodeDetails,c);
		
		JLabel typeLbl = new JLabel(NmsClientUtil.GetString("Type"));
		buildConstraints(c,0,4,1,1,0,0);
		typeLbl.setFont(font);
		criteriaPanel.add(typeLbl,c);
		
		JLabel communityLbl = new JLabel(NmsClientUtil.GetString("Community"));
		buildConstraints(c,2,4,1,1,0,0);
		communityLbl.setFont(font);
		criteriaPanel.add(communityLbl,c);
		
		buildConstraints(c,0,5,1,1,0,0);
		JLabel changeTesterLbl = new JLabel(NmsClientUtil.GetString("Change Tester to"));
		changeTesterLbl.setFont(font);
		criteriaPanel.add(changeTesterLbl,c);

		JLabel testerClassLbl = new JLabel(NmsClientUtil.GetString("Tester Class"));
		testerClassLbl.setFont(font);
		buildConstraints(c,0,6,1,1,0,0);
		criteriaPanel.add(testerClassLbl,c);

		JLabel parentNetlbl = new JLabel(NmsClientUtil.GetString("Parent Network"));
		parentNetlbl.setFont(font);
		buildConstraints(c,2,5,1,1,0,0);
		criteriaPanel.add(parentNetlbl,c);

		
	    policyNameTxt.setText(props.getProperty("name"));//No Internationalisation
		policyNameTxt.setEnabled(false);
	
		String str = props.getProperty("groupName");//No Internationalisation
		if((str == null) || str.length() <= 0)
		{
      		groupNameTxt.setText(NmsClientUtil.GetString("StatusTesterPolicy"));
		}
		else
		{
			groupNameTxt.setText(str);
		}

		statusCombo.addItem("Enabled");//No Internationalisation
		statusCombo.addItem("Disabled");//No Internationalisation

		str = props.getProperty("status");//No Internationalisation
		if((str == null) || (str.length() <= 0))
		{
      		statusCombo.setSelectedIndex(0);
		}
		else
		{
			try
			{
				int n = Integer.parseInt(str);
				if(n == PolicyConstants.POLICY_ENABLED)
				{
					statusCombo.setSelectedItem("Enabled");
				}
				else if ( n == PolicyConstants.POLICY_DISABLED)
				{
					statusCombo.setSelectedItem("Disabled");
				}
			}
			catch(Exception ee)
			{
				statusCombo.setSelectedIndex(0);
			}
		}
	
		str = props.getProperty("type");//No Internationalisation
		if((str == null) || str.length() <= 0)
		{
			typeTxt.setText("");//No Internationalisation
		}
		else
		{
			typeTxt.setText(str);
		}
	
		str = props.getProperty("community");//No Internationalisation
		if((str == null) || str.length() <= 0)
		{
			communityTxt.setText("");//No Internationalisation
		}
		else
		{
			communityTxt.setText(str);
		}
		
		testerCombo.addItem("max");//No Internationalisation
		testerCombo.addItem("ping");//No Internationalisation
		testerCombo.addItem("snmpping");//No Internationalisation
		testerCombo.addItem("usertester");//No Internationalisation
		testerCombo.addItemListener(this);

		str = props.getProperty("tester");//No Internationalisation
		if((str == null) || str.length() <= 0)
		{
      		testerCombo.setSelectedItem("max");//No Internationalisation
		}
		else if (str.equals("max") ||str.equals("ping") ||str.equals("snmpping"))//No Internationalisation
		{
      		testerCombo.setSelectedItem(str);
		}
		else
		{
			testerCombo.setSelectedItem("usertester");//No Internationalisation
			testerClass.setText(str);
			testerClass.setEnabled(true);
		}
		
		str = props.getProperty("parentNet");//No Internationalisation
		if((str == null) || str.length() <= 0)
		{
			parentNet.setText("");//No Internationalisation
		}
		else
		{
			parentNet.setText(str);
		}
	
		
		c.fill = GridBagConstraints.HORIZONTAL;
		buildConstraints(c,1,1,1,1,0,0);
		criteriaPanel.add(policyNameTxt,c);
		buildConstraints(c,1,2,1,1,0,0);
		criteriaPanel.add(groupNameTxt,c);
		buildConstraints(c,3,1,1,2,0,0);
		criteriaPanel.add(statusCombo,c);
		buildConstraints(c,1,4,1,1,100,0);
		criteriaPanel.add(typeTxt,c);
		buildConstraints(c,3,4,1,1,100,0);
		criteriaPanel.add(communityTxt,c);
	
//		c.fill = GridBagConstraints.NONE;
		c.fill = GridBagConstraints.HORIZONTAL;
		buildConstraints(c,1,5,1,1,0,0);
		criteriaPanel.add(testerCombo,c);
		
		buildConstraints(c,3,5,1,1,0,0);
		criteriaPanel.add(parentNet,c);
		
		
		buildConstraints(c,1,6,1,1,0,0);
		testerClass.setEnabled((testerCombo.getSelectedItem() == "usertester"));//No Internationalisation
		criteriaPanel.add(testerClass,c);

		
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		
		buildConstraints(c,0,1,1,1,0,0);
		buttonPanel.add(applyButt,c);
		
		buildConstraints(c,1,1,1,1,0,0);
		buttonPanel.add(scheduleButt,c);
		timeVector = (Vector)props.get("timeVector");

		buildConstraints(c,2,1,1,1,0,0);
		buttonPanel.add(cancelButt,c);
		
		this.getContentPane().add(headerPanel,BorderLayout.NORTH);
		this.getContentPane().add(criteriaPanel,BorderLayout.WEST);
		this.getContentPane().add(userPanel,BorderLayout.CENTER);
		this.getContentPane().add(buttonPanel,BorderLayout.SOUTH);

		applyButt.addActionListener(this);
		cancelButt.addActionListener(this);
		scheduleButt.addActionListener(this);
    
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
	
	}

	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
		String actionCommand = e.getActionCommand();

		if(obj == applyButt)
		{
			applyAction();
		}
		else if(obj == cancelButt)
		{
			cancelAction();
		}
		else if(obj == scheduleButt)
		{
			showSchedulerScreen();			
		}
                else if (actionCommand.equals("OK"))
		{
                    
                    if(policyCustom.validateTime())
                    {
                        policyCustom.setVisible(false);
                        timeVector = policyCustom.getModifiedTimeVector();
                        policyCustom.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(policyCustom, NmsClientUtil.GetString("Time is not specifed . Specify a time for scheduling"), NmsClientUtil.GetString("AdventNet Message"), JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
                else if(actionCommand.equals("Cancel"))
                {
                    policyCustom.setVisible(false);
                    timeVector = policyCustom.backUpVector;
                    policyCustom.dispose();
                }
                

                
			
	}

	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getItem() == "usertester")//No Internationalisation
		{
			testerClass.setEnabled(true);
		}
		else
		{
			testerClass.setEnabled(false);
			testerClass.setText("");//No Internationalisation
		}
	}
	
	private void showSchedulerScreen()
	{
		policyCustom = new PolicyCustom((Vector)timeVector.clone(),this,this);
		policyCustom.show();
		
	}
	private void applyAction()
	{	
		if(getAndSetAllValuesInCust())
		{
			tpc.firePropertyChange();
           	this.setVisible(false);
           	this.dispose();
			return;
		}	

	}

	private void cancelAction()
	{
		this.setVisible(false);
        this.dispose();
		return;
	}

	private void buildConstraints(GridBagConstraints gbc,int gx,int gy,
			int gw,int gh,int wx,int wy)
	{
		gbc.gridx=gx;
		gbc.gridy=gy;
		gbc.gridwidth=gw;
		gbc.gridheight=gh;
		gbc.weightx=wx;
		gbc.weighty=wy;
	}
	
	 private boolean getAndSetAllValuesInCust()
	 {
	 	Properties p = new Properties();
		String str="";//No Internationalisation
		int n;
		
		str= policyNameTxt.getText().trim();
		if((str == null) || str.length() <= 0)
		{
			showErrorMessage(NmsClientUtil.GetString("Policy Name cannot be null"));
			return false;
		}
		p.put("name",str);//No Internationalisation

		str= groupNameTxt.getText().trim();
		if((str == null) || str.length() <= 0)
		{
			showErrorMessage(NmsClientUtil.GetString("Group Name cannot be null"));
			return false;
		}
		p.put("groupName",str);//No Internationalisation
		
		if(((String)statusCombo.getSelectedItem()).equals("Enabled"))
		{
			p.put("status",""+PolicyConstants.POLICY_ENABLED);//No Internationalisation
		}
		else
		{
			p.put("status",""+PolicyConstants.POLICY_DISABLED);//No Internationalisation
		}
		
		str= typeTxt.getText().trim();
		
		if((str == null) || (str.length() <= 0))
		{
			p.put("type","");//No Internationalisation
		}
		else
		{
			p.put("type",str);//No Internationalisation
		}	
	
		String str1 = communityTxt.getText().trim();
		if((str1 == null) || (str1.length() <= 0))
		{
			p.put("community","");//No Internationalisation
		}
		else
		{
			p.put("community",str1);//No Internationalisation
		}	

		String str2 = parentNet.getText().trim();
		if((str1 == null) || (str1.length() <= 0))
		{
			p.put("parentNet","");//No Internationalisation
		}
		else
		{
			p.put("parentNet",str1);//No Internationalisation
		}	

		
		if((str == null && str1 == null && str2 == null) || (str1.length() <=0 && str.length() <= 0 && str2.length() <=0))
		{
			showErrorMessage(NmsClientUtil.GetString("Any one of Type , Community or ParentNet must have a value"));
			return false;
		}

		str = testerClass.getText();
		if (testerCombo.getSelectedItem() == "usertester")//No Internationalisation
		{
			if ((str == null || str.length() <= 0))
			{
			showErrorMessage(NmsClientUtil.GetString("Tester Class Name must be present"));
			return false;
			}
			else
			{
				p.put("tester",str);//No Internationalisation

			}
		}
		else
		{
			p.put("tester",(String)(testerCombo.getSelectedItem()));//No Internationalisation
		}
		
		String str3 = parentNet.getText().trim();
		if(str3 == null || str3.length() <=0)
		{
			p.put("parentNet","");//No Internationalisation
		}
		else
		{
			p.put("parentNet",str3);//No Internationalisation
		}
		
		// setting time vector
		p.put("timeVector" , timeVector);
		
		tpc.setProperties(p);
		return true;

	}

	private void showErrorMessage(String errStr)
	{
        JOptionPane.showMessageDialog(this,NmsClientUtil.GetString(errStr),NmsClientUtil.GetString("AdventNet") + " -- " + NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);//No Internationalisation
	}


	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e)
	{
		this.setVisible(false);
       	this.dispose();
		return;
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	private void setFontForComponents()
	{
		applyButt.setFont(font);
		scheduleButt.setFont(font);
		cancelButt.setFont(font); 
		typeTxt.setFont(font); 
		communityTxt.setFont(font);
		testerCombo.setFont(font);
		policyNameTxt.setFont(font);
		groupNameTxt.setFont(font);
		statusCombo.setFont(font);
	}

}

