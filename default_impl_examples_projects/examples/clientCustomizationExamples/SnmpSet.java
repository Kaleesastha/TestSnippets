
/**
 * $Id: SnmpSet.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 */
package com.adventnet.nms.example;

//swing imports
import javax.swing.*;

//awt imports
import java.awt.*;
import java.awt.event.*;

//util imports
import java.util.Enumeration;
import java.util.Properties;

//AdventNet SNMP imports
import com.adventnet.snmp.beans.*;
import com.adventnet.snmp.snmp2.*;

//WebNMS imports
import com.adventnet.nms.util.*;

/**
 * This class is an example of a custom written class that could be 
 * had as a menu action in any of the menus defined in the AdventNet
 * WebNMS.This class implements the com.adventnet.nms.util.CustomClassInterface.<p>
 * This does an SnmpSet on the agents that are selected in the current
 * NMSPanel. This class searches for a property by name "source" in the
 * properties object of the selected items, passed to it through the 
 * <i>setProperties</i> method. The value of this property is taken
 * to be the agentName on which the SnmpSet is to be performed. The other
 * details of the OID to be set, the type of the OID and the value to
 * be set are taken from the user via a proper GUI. <p>
 * 
 * After getting all the relevant information (when the Set button is
 * pressed), this class goes about doing an snmp set operation on all
 * the selected agents.
 * 
 */

public class SnmpSet  implements com.adventnet.nms.util.CustomClassInterface,
	   ActionListener
{
	/**
	 * SnmpTarger object is used to perform snmp operations on
	 * the agent.
	 */ 
	private SnmpTarget st = new SnmpTarget(NmsUiAPI.getMainApplet());
	/**
	 * Dialog to get user inputs.
	 */
	private JDialog setDialog = new JDialog();
	/**
	 * Textfield to get the OID information
	 */
	private JTextField  oidField  = new JTextField(10);
	/**
	 * TextField to get the value to be set
	 */
    private JTextField valueField = new JTextField(10);
	/**
	 * Possible Snmp Types to be displayed in the typesCombo.
	 */
	private String[]   snmpTypes  = {"Counter","Counter64",
									  "Gauge","Integer","IPAddress",
									 "NetworkAddress","ObjectID","Opaque",
									 "NullObj","String","Timeticks",
									 "UInteger32"};
	/**
	 * Contains the list of supported Snmp Types.
	 */
	private JComboBox  typesCombo = new JComboBox(snmpTypes);
	/**
	 * panel that holds the UI
	 */
	private JPanel     setPanel = new JPanel();
	/**
	 * Button to invoke the set operation on agents
	 */
	private JButton    setButton = new JButton("Set");
	/**
	 * Button to dispose the SnmpSet Dialog
	 */
    private JButton    closeButton = new JButton("Close");
	/**
	 * Properties object to store the properties of the current
	 * NMS Panel.
	 */
    private Properties[] props  =  null;
	
	/**
	 * Empty Constructor.
	 */
	public SnmpSet()
	{
		createSamplePanel();
		JLabel title = new JLabel("Custom Written class to Perform Snmp Set",SwingUtilities.CENTER);
		setDialog.getContentPane().add(title,BorderLayout.NORTH);
		setDialog.getContentPane().add(setPanel);
		setDialog.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt)
			{
				setDialog.dispose();
				setDialog.setVisible(false);
			}
		});	
		setDialog.setSize(500,250);
		NmsClientUtil.centerWindow(setDialog);
	}
	
	/**
	 * This the implementation of the com.adventnet.nms.uti.CustomClassInterface.
	 * The Properties object array <tt>p</tt> contains  properties of all
	 * the selected objects in the current NMSPanel. 
	 * 
	 * @param p contains the array of properties representing each
	 *          of the selected objects in the current NMSPanel.
	 * 
	 */
	public void setProperties(Properties p[])
	{
		  props = p;
		  if (p != null)
		  {
		    setDialog.setVisible(true);
		  }	
	}
	
	/**
	 * This method creates an UI for getting the following inputs
	 *    <p>1. The ObjectID to be set
	 *    <p>2. The type of the ObjectID
	 *    <p>3. The value to be set for the ObjectID.
	 */
	private void createSamplePanel()
	{
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		setPanel.setLayout(gb);
		gc.gridx = 0;
		gc.gridy = 0;
		JLabel oidLabel = new JLabel("OID");
		gb.setConstraints(oidLabel,gc);
		setPanel.add(oidLabel);
		gc.gridx = 1;
		gc.gridy = 0;
		gb.setConstraints(oidField,gc);
		setPanel.add(oidField);
		gc.gridx = 2;
		gc.gridy = 0;
		JLabel type = new JLabel("Snmp Type");
		gb.setConstraints(type,gc);
		setPanel.add(type);
		gc.gridx = 3;
		gc.gridy = 0;
		gb.setConstraints(typesCombo,gc);
		setPanel.add(typesCombo);
		gc.gridx = 0;
		gc.gridy = 1;
		JLabel value = new JLabel("Value");
		gb.setConstraints(value,gc);
		setPanel.add(value);
		gc.gridx = 1;
		gc.gridy = 1;
		gb.setConstraints(valueField,gc);
		setPanel.add(valueField);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(setButton);
		buttonPanel.add(closeButton);
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 4;
		gb.setConstraints(buttonPanel,gc);
		setPanel.add(buttonPanel);
		setButton.addActionListener(this);
		closeButton.addActionListener(this);
		
}

 
 /**
  * This method is the implementation of the actionListener
  * interface. This method listens for the actions of the
  * "Set" button and the "Close" button that appears in the UI.
  * This method performs an SnmpSet on the selected agents, if
  * the Set button is pressed. 
  */
 public void actionPerformed(ActionEvent evt)
 {
	  Object obj = evt.getSource();
	  if (obj.equals(setButton))
	  {
		 if ((props != null) && (props.length > 0))
		 {
			for(int count =0;count < props.length;count ++)
			{
				  Properties p = props[count];
				  String host = (String)p.get("source");
				  if (host != null)
				  {
					  st.setTargetHost(host);
					  System.out.println("The host name is :"+host);
					  if (!oidField.getText().trim().equals(""))
					  {
						st.setSnmpOID(new SnmpOID(oidField.getText().trim()));
						String type = (String)typesCombo.getSelectedItem();
						byte snmpType = getType(type);
						try
						{
						  st.snmpSetVariable(SnmpVar.createVariable(valueField.getText(),snmpType));
						}
						catch(Exception ex)
						{
							  ex.printStackTrace();
						}
					  }
								 
				  }
			}
		 }  
          oidField.setText("");
		  valueField.setText("");
	  }
	  else
	  {
		  oidField.setText("");
		  valueField.setText("");
		  setDialog.dispose();
		  setDialog.setVisible(false);
	  } 
	  
 }
 /**
  * This is a convinience method that converts the user input
  * regarding the SnmpType to a byte representation understood
  * by the AdventNetSNMP API.
  */
 private byte getType(String type)
 {
      byte retVal = 0;															   
      /*if (type.trim().equals("BitString")) //removed
	 {
		 retVal = SnmpAPI.BITSTRING;
         }*/
	 if (type.trim().equals("Counter"))
	 {
		 retVal = SnmpAPI.COUNTER;
	 }
	 else if (type.trim().equals("Counter64"))
	 {
		 retVal = SnmpAPI.COUNTER64;
	 }
	 else if (type.trim().equals("Gauge"))
	 {
		 retVal = SnmpAPI.GAUGE;
	 }
	 else if (type.trim().equals("Integer"))
	 {
		 retVal = SnmpAPI.INTEGER;
	 }
	 else if (type.trim().equals("IPAddress"))
	 {
		 retVal = SnmpAPI.IPADDRESS;
	 }
	 else if (type.trim().equals("NetworkAddress"))
	 {
		 retVal = SnmpAPI.NETWORKADDRESS;
	 }
	 else if (type.trim().equals("ObjectID"))
	 {
		 retVal = SnmpAPI.OBJID;
	 }
	 else if (type.trim().equals("Opaque"))
	 {
		 retVal = SnmpAPI.OPAQUE;
	 }
	 else if (type.trim().equals("NullObj"))
	 {
		 retVal = SnmpAPI.NULLOBJ;
	 }
      /* else if (type.trim().equals("Nsap"))
	 {
		 retVal = SnmpAPI.NSAP;
         }*/
	 else if (type.trim().equals("String"))
	 {
		 retVal = SnmpAPI.STRING;
	 }
	 else if (type.trim().equals("Timeticks"))
	 {
		 retVal = SnmpAPI.TIMETICKS;
	 }
      /*else if (type.trim().equals("UInteger32"))
	 {
		 retVal = SnmpAPI.UINTEGER32;
         }*/
      else if (type.trim().equals("Unsigned32"))
      {
          retVal = SnmpAPI.UNSIGNED32;
      }
	 return retVal;
 }
	
}

 
