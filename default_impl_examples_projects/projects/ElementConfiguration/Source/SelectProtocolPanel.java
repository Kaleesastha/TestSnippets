
//$Id: SelectProtocolPanel.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.config;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class SelectProtocolPanel extends JPanel implements ActionListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JRadioButton snmpButton = null;
	javax.swing.JRadioButton telnetButton = null;
	javax.swing.JRadioButton tftpButton = null;
	javax.swing.JRadioButton ftpButton = null;
	javax.swing.JRadioButton tl1Button = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JTextArea JTextArea2 = null;
	javax.swing.ButtonGroup ButtonGroup1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;


	public SelectProtocolPanel(ConfigPanel configPanel)
	{
		this.configPanel = configPanel;

		applet = configPanel.applet;

		init();
		configInit();
	}




	public SelectProtocolPanel(java.applet.Applet applet)
	{
		//<Begin_SelectProtocolPanel_java.applet.Applet>
		this.applet = applet;
		this.init();

		//<End_SelectProtocolPanel_java.applet.Applet>
	}


	public void init()
	{
		//<Begin_init> 
		if(getParameter("RESOURCE_PROPERTIES" ) != null)
		{
			localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
		}
		resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
		if (initialized) return; 
		setPreferredSize(new Dimension(getPreferredSize().width+500,getPreferredSize().height+360)); 
		setSize(getPreferredSize()); 
		Container container = this;
		container.setLayout(new BorderLayout()); 
		try 
		{ 
			initVariables(); 
			setUpGUI(container); 
			setUpProperties(); 
			setUpConnections(); 
		} 
		catch(Exception ex) 
		{ 
			showStatus(resourceBundle.getString("Error in init method"),ex); 
		} 
		// let us set the initialized variable to true so  
		// we dont initialize again even if init is called 
		initialized = true; 


		//<End_init>

	} 
	public String getParameter(String input)
	{
		//<Begin_getParameter_String> 
		String value = null;
		if ( applet != null)
		{    
			value = applet.getParameter(input);
		}    
		else
		{    
			value = (String)com.adventnet.apiutils.Utility.getParameter(input);
		}    
		if(value == null)
		{
			if (input.equals("HOST")) value = "localhost"; 
			if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; 
			if (input.equals("PORT")) value = "161"; 
		}
		return value;


		//<End_getParameter_String>
	} 
	public void setUpProperties()
	{
		//<Begin_setUpProperties> 

		try
		{
			JPanel4.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Protocol Information"),0,2,new Font("Dialog",0,12),new Color(-16777012)));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
		}

		//<UserCode_Begin_Bean_JPanel4>

		//<UserCode_End_Bean_JPanel4>

		try
		{
			JPanel2.setBorder(new javax.swing.border.EtchedBorder(1));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
		}

		//<UserCode_Begin_Bean_JPanel2>

		//<UserCode_End_Bean_JPanel2>

		try
		{
			snmpButton.setText(resourceBundle.getString("SNMP"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+snmpButton,ex); 
		}

		//<UserCode_Begin_Bean_snmpButton>

		//<UserCode_End_Bean_snmpButton>

		try
		{
			telnetButton.setText(resourceBundle.getString("Telnet"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+telnetButton,ex); 
		}

		//<UserCode_Begin_Bean_telnetButton>

		//<UserCode_End_Bean_telnetButton>

		try
		{
			tftpButton.setText(resourceBundle.getString("TFTP"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tftpButton,ex); 
		}

		//<UserCode_Begin_Bean_tftpButton>

		//<UserCode_End_Bean_tftpButton>

		try
		{
			ftpButton.setText(resourceBundle.getString("FTP"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ftpButton,ex); 
		}

		//<UserCode_Begin_Bean_ftpButton>

		//<UserCode_End_Bean_ftpButton>

		try
		{
			tl1Button.setText(resourceBundle.getString("TL1"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tl1Button,ex); 
		}

		//<UserCode_Begin_Bean_tl1Button>

		//<UserCode_End_Bean_tl1Button>

		try
		{
			JPanel5.setBorder(new javax.swing.border.BevelBorder(1));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
		}

		//<UserCode_Begin_Bean_JPanel5>

		//<UserCode_End_Bean_JPanel5>

		try
		{
			JTextArea2.setOpaque(false);
			JTextArea2.setEditable(false);
			JTextArea2.setLineWrap(true);
			JTextArea2.setWrapStyleWord(true);
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea2,ex); 
		}

		//<UserCode_Begin_Bean_JTextArea2>

		//<UserCode_End_Bean_JTextArea2>


		//<End_setUpProperties>

		JPanel4.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Protocol Information"),0,2,new Font("Dialog",0,12),new Color(-16777012)));		
	} 
	public void start()
	{
		//<Begin_start> 
		if(running)
			return;
		running=true;


		//<End_start>
	} 
	public void stop()
	{
		//<Begin_stop> 
		if(!running)
			return;
		running=false;


		//<End_stop>
	} 
	public void initVariables()
	{
		//<Begin_initVariables> 
		Top= new javax.swing.JPanel();
		JPanel4= new javax.swing.JPanel();
		JPanel2= new javax.swing.JPanel();
		snmpButton= new javax.swing.JRadioButton();
		telnetButton= new javax.swing.JRadioButton();
		tftpButton= new javax.swing.JRadioButton();
		ftpButton= new javax.swing.JRadioButton();
		tl1Button= new javax.swing.JRadioButton();
		JPanel5= new javax.swing.JPanel();
		JTextArea2= new javax.swing.JTextArea();
		ButtonGroup1= new javax.swing.ButtonGroup();


		//<End_initVariables>
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
	public void setUpGUI(Container container)
	{
		//<Begin_setUpGUI_Container> 
		container.add(Top,BorderLayout.CENTER);
		Top.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		Top.add(JPanel4,cons);
		JPanel4.setLayout(new GridLayout(1,0,5,10));
		JPanel4.add(JPanel2);
		JPanel2.setLayout(new GridBagLayout());
		inset = new Insets(20,50,10,50);
		setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		JPanel2.add(snmpButton,cons);
		inset = new Insets(0,50,10,50);
		setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		JPanel2.add(telnetButton,cons);
		inset = new Insets(0,50,10,50);
		setConstraints(0,4,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		JPanel2.add(tftpButton,cons);
		inset = new Insets(0,50,10,50);
		setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		JPanel2.add(ftpButton,cons);
		inset = new Insets(0,50,10,50);
		setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		JPanel2.add(tl1Button,cons);
		JPanel4.add(JPanel5);
		JPanel5.setLayout(new BorderLayout(5,5));
		JPanel5.add(JTextArea2,BorderLayout.CENTER);


		//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
	{
		//<Begin_setUpConnections> 


		//<End_setUpConnections>
	} 




	public void showStatus(String message)
	{
		//<Begin_showStatus_String>
		System.out.println("Internal Error :"+ message);
		//<End_showStatus_String>
	}
	public void showStatus(String message,Exception ex)
	{
		//<Begin_showStatus_String_Exception>
		System.out.println("Internal Error :"+ message);
		ex.printStackTrace();
		//<End_showStatus_String_Exception>
	}


	private void configInit()
	{
		snmpButton.addActionListener(this);
		telnetButton.addActionListener(this);
		ftpButton.addActionListener(this);
		tftpButton.addActionListener(this);
		tl1Button.addActionListener(this);

		ButtonGroup1.add(snmpButton);
		ButtonGroup1.add(telnetButton);
		ButtonGroup1.add(ftpButton);
		ButtonGroup1.add(tftpButton);
		ButtonGroup1.add(tl1Button);

		snmpButton.setSelected(true);

		radioButtonActionPerformed();
	}

	public String getSelectedProtocol()
	{
		String protocol = "";

		if(snmpButton.isSelected())
		{
			protocol = "snmp";
		}
		else if(telnetButton.isSelected())
		{
			protocol = "telnet";
		}
		else if(ftpButton.isSelected())
		{
			protocol = "ftp";
		}
		else if(tftpButton.isSelected())
		{
			protocol = "tftp";
		}
		else if(tl1Button.isSelected())
		{
			protocol = "tl1";
		}

		return protocol;	
	}

	public void actionPerformed(ActionEvent ae)
	{
		radioButtonActionPerformed();
	}

	private void radioButtonActionPerformed()
	{
		if(snmpButton.isSelected())
		{
			JTextArea2.setText(resourceBundle.getString("SNMP - The Simple Network Management Protocol is by far the most dominant protocol in network management. SNMP facilitates communication between a managed device (a device with an SNMP agent, e.g. a router), and an SNMP manager or management application (represents a user of network management). The SNMP agent on the managed device serves to provide access to data (managed objects) stored on the managed device.")); 
		}
		else if(telnetButton.isSelected())
		{
			JTextArea2.setText(resourceBundle.getString("Telnet - The Telnet protocol is seen as an easy, quick and low-overhead way of managing devices. Most of the CLI commands were in the form of an ASCII based man-machine language (MML), thus making them easier to remember and understand. While this offered easy usage of the command, there was no standardized way in which these commands could manage devices. It is by far the most prevalent management connectivity that devices offer in the world."));
		}
		else if(tftpButton.isSelected())
		{
			JTextArea2.setText(resourceBundle.getString("TFTP - The Trivial File Transfer Protocol is a utility for transferring files and is very simple to use. TFTP uses the User Datagram Protocol (UDP) rather than Transmission Control Protocol (TCP). It is used where user authentication and directory visibility are not required, for example, by servers to boot diskless workstations, X-terminals, and routers."));
		}
		else if(ftpButton.isSelected())
		{
			JTextArea2.setText(resourceBundle.getString("FTP - The File Transfer Protocol, is the simplest way to exchange files between computers. It is one of the oldest and most popular methods of transferring files over the internet. FTP is a two-way system, ie., its used both to download files from a server to a client and to upload files from a client to a server. It differs from TFTP in that, FTP actually 'logs on' to the remote machine, is a standard TCP/IP service and is more secure."));
		}
		else if(tl1Button.isSelected())
		{
			JTextArea2.setText(resourceBundle.getString("TL1 - Transaction Language 1 (TL1) is a network management protocol used for managing Telecommunications networks. TL1 facilitates communication between a managed device (a device with TL1 agent), and a TL1 manager. The TL1 agent on the managed device serves to provide access to data stored on the managed device. The TL1 manager uses this access to monitor and control the managed device. TL1 protocol communicates via TCP."));
		}
	}




	public SelectProtocolPanel()
	{
		//<Begin_SelectProtocolPanel>
		this.init();

		//<End_SelectProtocolPanel>
	}


	public void setProperties(java.util.Properties props)
	{
		//<Begin_setProperties_java.util.Properties> 


		//<End_setProperties_java.util.Properties>
	}
}
