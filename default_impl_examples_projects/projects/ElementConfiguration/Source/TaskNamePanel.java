
//$Id: TaskNamePanel.java,v 1.1.6.2 2012/05/30 09:14:42 karen.r Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class TaskNamePanel extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField nameText = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField descriptionText = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JCheckBox templateCheck = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JRadioButton snmpButton = null;
	javax.swing.JRadioButton telnetButton = null;
	javax.swing.JRadioButton ftpButton = null;
	javax.swing.JRadioButton tftpButton = null;
	javax.swing.JRadioButton tl1Button = null;
	javax.swing.JRadioButton combinedButton = null;
	javax.swing.JRadioButton netconfButton = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JTextArea JTextArea2 = null;
	javax.swing.ButtonGroup ButtonGroup1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>




	public void setUpProperties()
  { 

		//<Begin_setUpProperties> 

          try
          {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setText(resourceBundle.getString("Name of the Task  **"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            nameText.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nameText,ex); 
          }

//<UserCode_Begin_Bean_nameText>

//<UserCode_End_Bean_nameText>

          try
          {
            JLabel3.setText(resourceBundle.getString("Task Description"));
            JLabel3.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            descriptionText.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+descriptionText,ex); 
          }

//<UserCode_Begin_Bean_descriptionText>

//<UserCode_End_Bean_descriptionText>

          try
          {
            JPanel3.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setText(resourceBundle.getString("Define as Template ?"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

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
            combinedButton.setText(resourceBundle.getString("Combined Task"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+combinedButton,ex); 
          }

//<UserCode_Begin_Bean_combinedButton>

//<UserCode_End_Bean_combinedButton>

          try
          {
            netconfButton.setLabel(resourceBundle.getString("Netconf"));//No I18N
            netconfButton.setText(resourceBundle.getString("Netconf"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+netconfButton,ex);//No I18N
          }

//<UserCode_Begin_Bean_netconfButton>

//<UserCode_End_Bean_netconfButton>

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

//<UserCode_Begin_Bean_ButtonGroup1>



//<UserCode_End_Bean_ButtonGroup1>
		JTextArea2.setPreferredSize(new Dimension(JTextArea2.getPreferredSize().width+0,JTextArea2.getPreferredSize().height+1));


  
          //<End_setUpProperties>
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
        setPreferredSize(new Dimension(getPreferredSize().width+697,getPreferredSize().height+578));
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
		JPanel4.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Protocol Information"),0,2,new Font("Dialog",0,12),new Color(-16777012)));
	}



	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        nameText= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        descriptionText= new javax.swing.JTextField();
        JPanel3= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        templateCheck= new javax.swing.JCheckBox();
        JPanel4= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        snmpButton= new javax.swing.JRadioButton();
        telnetButton= new javax.swing.JRadioButton();
        ftpButton= new javax.swing.JRadioButton();
        tftpButton= new javax.swing.JRadioButton();
        tl1Button= new javax.swing.JRadioButton();
        combinedButton= new javax.swing.JRadioButton();
        netconfButton= new javax.swing.JRadioButton();
        JPanel5= new javax.swing.JPanel();
        JTextArea2= new javax.swing.JTextArea();
        ButtonGroup1= new javax.swing.ButtonGroup();

  
        //<End_initVariables>
	} 



	public void stop()
  { 

		//<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
	} 
	public void start()
  { 

		//<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
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











	public TaskNamePanel()
  {
		//<Begin_TaskNamePanel>
    this.init();
  
    //<End_TaskNamePanel>
	}

	public TaskNamePanel(java.applet.Applet applet)
  {
		//<Begin_TaskNamePanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_TaskNamePanel_java.applet.Applet>
	}












	//<---------------------------------------------------------------------------------------------------------------------->

	private ConfigPanel configPanel = null;

	public TaskNamePanel(ConfigPanel panel)
	{
		configPanel = panel;

		applet = configPanel.applet;

		init();
		configInit(null);
	}

	public TaskNamePanel(ConfigPanel panel, Hashtable taskDetails)
	{
		configPanel = panel;

		applet = configPanel.applet;

		init();
		configInit(taskDetails);
	}

	public void setTaskName(String taskName)
	{
		nameText.setText(taskName);
		nameText.setEnabled(false);
		nameText.setDisabledTextColor(Color.black);
	}

	public String getTaskName()
	{
		return nameText.getText().trim();
	}

	public void setTaskDescription(String taskDesc)
	{
		descriptionText.setText(taskDesc);
	}

	public String getTaskDescription()
	{
		return descriptionText.getText().trim();
	}

	private void setSelectedProtocol(String protocol)
	{
		if(protocol.equalsIgnoreCase("snmp"))
		{
			snmpButton.setSelected(true);
		}
		else if(protocol.equalsIgnoreCase("telnet"))
		{
			telnetButton.setSelected(true);
		}
		else if(protocol.equalsIgnoreCase("tftp"))
		{
			tftpButton.setSelected(true);
		}
		else if(protocol.equalsIgnoreCase("ftp"))
		{
			ftpButton.setSelected(true);
		}
		else if(protocol.equalsIgnoreCase("tl1"))
		{
			tl1Button.setSelected(true);
		}
		else if(protocol.equalsIgnoreCase("netconf"))//No I18N
		{
			netconfButton.setSelected(true);
		}

		else
		{
			combinedButton.setSelected(true);
		}

		snmpButton.setEnabled(false);
		telnetButton.setEnabled(false);
		tftpButton.setEnabled(false);
		ftpButton.setEnabled(false);
		tl1Button.setEnabled(false);
		netconfButton.setEnabled(false);
		combinedButton.setEnabled(false);

		comboAction();
	}

	public String getSelectedProtocol()
	{
		if(snmpButton.isSelected())
		{
			return "snmp";
		}
		else if(telnetButton.isSelected())
		{
			return "telnet";
		}
		else if(tftpButton.isSelected())
		{
			return "tftp";
		}
		else if(ftpButton.isSelected())
		{
			return "ftp";
		}
		else if(tl1Button.isSelected())
		{
			return "tl1";
		}
		else if(netconfButton.isSelected())
		{
			return "netconf";//No I18N
		}
		else 
		{
			return "combined";
		}
	}

	public void setTemplate(boolean isTemplate)
	{
		templateCheck.setSelected(isTemplate);
		templateCheck.setEnabled(false);
	}	

	public boolean isTemplate()
	{
		if(templateCheck.isSelected())
			return true;
		else
			return false;
	}

	private void configInit(Hashtable taskDetails)
	{
		ButtonGroup1.add(snmpButton);
		ButtonGroup1.add(telnetButton);
		ButtonGroup1.add(tftpButton);
		ButtonGroup1.add(ftpButton);
		ButtonGroup1.add(tl1Button);
		ButtonGroup1.add(netconfButton);
		ButtonGroup1.add(combinedButton);

		if(taskDetails != null)
		{
			setTaskName((String)taskDetails.get("taskname"));

			if(taskDetails.get("description") != null)
			{
				setTaskDescription((String)taskDetails.get("description"));
			}

			setSelectedProtocol((String)taskDetails.get("protocol"));

			if(((String)taskDetails.get("istemplate")).equals("true"))
			{
				setTemplate(true);
			}
			else
			{
				setTemplate(false);
			}
		}
		else
		{
			if(configPanel.isStandAlone)
			{
				String protocol = (configPanel.applet).getParameter("CONFIG_PROTOCOL");	

				if(protocol == null)
				{
					protocol = "snmp";
				}

				setSelectedProtocol(protocol);
			}
			else
			{
				snmpButton.setSelected(true);
				comboAction();
			}
		}
	}

	private void comboAction()
	{
		if(combinedButton.isSelected())
		{
			if(templateCheck.isSelected())
			{
				templateCheck.setSelected(false);
			}

			templateCheck.setEnabled(false);

			JTextArea2.setText(resourceBundle.getString("Combined Task - The user can create a new task by combining some of the existing tasks from the same protocol or by combining task belonging to different protocols."));

			return;
		}
		else
		{
			if(!templateCheck.isEnabled())
			{
				templateCheck.setEnabled(true);
			}
		}

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
		else if(netconfButton.isSelected())
		{
			JTextArea2.setText(resourceBundle.getString("Netconf - The Network Configuration Protocol provides  mechanisms to install, manipulate, and delete the configuration of network devices. Its operations are realized on top of a simple Remote Procedure Call (RPC) layer. The NETCONF protocol uses XML based data encoding for the configuration data as well as the protocol messages. This in turn is realized on top of the TCP layer."));//No I18N
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
	public void setUpGUI(Container container)
  {
		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(15,10,20,10);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(nameText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(descriptionText,cons);
inset = new Insets(0,10,10,10);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.6,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(templateCheck,cons);
inset = new Insets(0,10,20,10);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new GridLayout(1,0,5,10));
JPanel4.add(JPanel2);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(20,50,10,50);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(snmpButton,cons);
inset = new Insets(0,50,10,50);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(telnetButton,cons);
inset = new Insets(0,50,10,50);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(ftpButton,cons);
inset = new Insets(0,50,10,50);
setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(tftpButton,cons);
inset = new Insets(0,50,10,50);
setConstraints(0,4,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(tl1Button,cons);
inset = new Insets(0,50,20,50);
setConstraints(0,6,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(combinedButton,cons);
inset = new Insets(0,50,10,50);
setConstraints(0,5,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(netconfButton,cons);
JPanel4.add(JPanel5);
JPanel5.setLayout(new BorderLayout(5,5));
JPanel5.add(JTextArea2,BorderLayout.CENTER);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      tl1Button_tl1Button_conn1 tl1Button_tl1Button_conn11 =  new tl1Button_tl1Button_conn1();
      tl1Button.addActionListener(tl1Button_tl1Button_conn11);
      combinedButton_combinedButton_conn1 combinedButton_combinedButton_conn11 =  new combinedButton_combinedButton_conn1();
      combinedButton.addActionListener(combinedButton_combinedButton_conn11);
      tftpButton_tftpButton_conn1 tftpButton_tftpButton_conn11 =  new tftpButton_tftpButton_conn1();
      tftpButton.addActionListener(tftpButton_tftpButton_conn11);
      JRadioButton1_JRadioButton1_conn1 JRadioButton1_JRadioButton1_conn11 =  new JRadioButton1_JRadioButton1_conn1();
      snmpButton.addActionListener(JRadioButton1_JRadioButton1_conn11);
      JRadioButton2_JRadioButton2_conn1 JRadioButton2_JRadioButton2_conn11 =  new JRadioButton2_JRadioButton2_conn1();
      telnetButton.addActionListener(JRadioButton2_JRadioButton2_conn11);
      ftpButton_ftpButton_conn1 ftpButton_ftpButton_conn11 =  new ftpButton_ftpButton_conn1();
      ftpButton.addActionListener(ftpButton_ftpButton_conn11);
      netconfButton_netconfButton_conn netconfButton_netconfButton_conn1 =  new netconfButton_netconfButton_conn();
      netconfButton.addActionListener(netconfButton_netconfButton_conn1);
  
      //<End_setUpConnections>
	} 


	//<Begin__class_tl1Button_tl1Button_conn1>

 class tl1Button_tl1Button_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_tl1Button_tl1Button_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  comboAction();
     }
//<UserCode_End_Connection_tl1Button_tl1Button_conn1>
 }//<End__class_tl1Button_tl1Button_conn1>
	//<Begin__class_combinedButton_combinedButton_conn1>

 class combinedButton_combinedButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_combinedButton_combinedButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  comboAction();
     }
//<UserCode_End_Connection_combinedButton_combinedButton_conn1>
 }//<End__class_combinedButton_combinedButton_conn1>
	//<Begin__class_tftpButton_tftpButton_conn1>

 class tftpButton_tftpButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_tftpButton_tftpButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  comboAction();
     }
//<UserCode_End_Connection_tftpButton_tftpButton_conn1>
 }//<End__class_tftpButton_tftpButton_conn1>
	//<Begin__class_JRadioButton1_JRadioButton1_conn1>

 class JRadioButton1_JRadioButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JRadioButton1_JRadioButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  comboAction();
     }
//<UserCode_End_Connection_JRadioButton1_JRadioButton1_conn1>
 }//<End__class_JRadioButton1_JRadioButton1_conn1>
	//<Begin__class_JRadioButton2_JRadioButton2_conn1>

 class JRadioButton2_JRadioButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JRadioButton2_JRadioButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  comboAction();
     }
//<UserCode_End_Connection_JRadioButton2_JRadioButton2_conn1>
 }//<End__class_JRadioButton2_JRadioButton2_conn1>


	//<Begin__class_ftpButton_ftpButton_conn1>

 class ftpButton_ftpButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_ftpButton_ftpButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  comboAction();
     }
//<UserCode_End_Connection_ftpButton_ftpButton_conn1>
 }//<End__class_ftpButton_ftpButton_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }


//<Begin__class_netconfButton_netconfButton_conn>

 class netconfButton_netconfButton_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_netconfButton_netconfButton_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          comboAction();
     }
//<UserCode_End_Connection_netconfButton_netconfButton_conn>
 }//<End__class_netconfButton_netconfButton_conn>
}
