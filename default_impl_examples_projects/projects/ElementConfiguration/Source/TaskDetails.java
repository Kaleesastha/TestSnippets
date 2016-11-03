
//$Id: TaskDetails.java,v 1.1.6.1 2012/05/29 06:26:56 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import com.adventnet.management.config.*;
import com.adventnet.management.config.netconf.NetconfAttributeConstants;

public class TaskDetails extends JDialog implements ConfigResponseListener, ActionListener, HelpInterface, WindowListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField JTextField2 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField JTextField3 = null;
	javax.swing.JLabel commonLabel = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField JTextField1 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JTextField deviceListText = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JTextField JTextField5 = null;
	javax.swing.JLabel JLabel8 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel cardPanel = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JComboBox JComboBox1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	CardLayout cardLayout = null;
	SnmpDetails snmpDetails = null;
	CommonDetails commonDetails = null;
	ConfigPanel configPanel = null; 
	private Vector generalDetailsVector = null;    
	private boolean isCombinedTask = false;
	private boolean firsttime = true;
	private String protocolString = "";
	private String taskName = "";

	private HelpDialog helpDialog = null;

	public TaskDetails(ConfigPanel configPanel, Vector taskDetails) 
	{
		super(configPanel.configClientUtils.getParentFrame(configPanel));
		this.configPanel = configPanel;

		applet = configPanel.applet;

		init();

		generalDetailsVector = taskDetails;
		checkForCombinedTask();
		getTaskDetailsFromServer();

		// Added by saravanasp as part of Operation Theme.
		addWindowListener(this);
	}



	public void setVisible(boolean bl)
  {
		//<Begin_setVisible_boolean>
       	if(bl)
       	{
       	  init();
          start();
        }
        else
        {
          stop();
        }
        super.setVisible(bl);
  
                 //<End_setVisible_boolean>
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

	public void setUpProperties()
  { 

		//<Begin_setUpProperties> 

          try
          {
            JPanel1.setBackground(new Color(-1));
            JPanel1.setOpaque(false);
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
            JLabel6.setText(resourceBundle.getString("Task Details"));
            JLabel6.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

          try
          {
            JButton1.setPreferredSize(new Dimension(35,23));
            JButton1.setMinimumSize(new Dimension(35,23));
            JButton1.setMaximumSize(new Dimension(35,23));
            JButton1.setBackground(new Color(-1));
            JButton1.setFocusPainted(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JPanel4.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            closeButton.setText(resourceBundle.getString("Close"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>

//<UserCode_End_Bean_closeButton>

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
            JLabel2.setText(resourceBundle.getString("Task Description  "));
            JLabel2.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JTextField2.setOpaque(false);
            JTextField2.setForeground(new Color(-16764109));
            JTextField2.setEditable(false);
            JTextField2.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField2,ex); 
          }

//<UserCode_Begin_Bean_JTextField2>

//<UserCode_End_Bean_JTextField2>

          try
          {
            JLabel3.setText(resourceBundle.getString("Protocols   "));
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
            JTextField3.setOpaque(false);
            JTextField3.setForeground(new Color(-16764109));
            JTextField3.setEditable(false);
            JTextField3.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField3,ex); 
          }

//<UserCode_Begin_Bean_JTextField3>

//<UserCode_End_Bean_JTextField3>

          try
          {
            commonLabel.setText(resourceBundle.getString("Device Lists"));
            commonLabel.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+commonLabel,ex); 
          }

//<UserCode_Begin_Bean_commonLabel>

//<UserCode_End_Bean_commonLabel>

          try
          {
            JLabel1.setText(resourceBundle.getString("Task Name   "));
            JLabel1.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JTextField1.setOpaque(false);
            JTextField1.setForeground(new Color(-16764109));
            JTextField1.setEditable(false);
            JTextField1.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField1,ex); 
          }

//<UserCode_Begin_Bean_JTextField1>

//<UserCode_End_Bean_JTextField1>

          try
          {
            deviceListText.setEditable(false);
            deviceListText.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+deviceListText,ex); 
          }

//<UserCode_Begin_Bean_deviceListText>

//<UserCode_End_Bean_deviceListText>

          try
          {
            JLabel7.setText(resourceBundle.getString("Rollback Document"));
            JLabel7.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel7,ex); 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>

          try
          {
            JTextField5.setOpaque(false);
            JTextField5.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField5,ex); 
          }

//<UserCode_Begin_Bean_JTextField5>

//<UserCode_End_Bean_JTextField5>

          try
          {
            JLabel8.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel8,ex); 
          }

//<UserCode_Begin_Bean_JLabel8>

//<UserCode_End_Bean_JLabel8>

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
            JLabel4.setText(resourceBundle.getString("Subtask Names"));
            JLabel4.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>
		cardPanel.setPreferredSize(new Dimension(cardPanel.getPreferredSize().width+22,cardPanel.getPreferredSize().height+22));

  
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
        this.setSize(getPreferredSize().width+565,getPreferredSize().height+555); 
          setTitle(resourceBundle.getString("TaskDetails"));
        Container container = getContentPane();
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
		setUpListener();
		configPanel.configClientUtils.centerWindow(this);
		URL docBase = configPanel.applet.getDocumentBase();
		JLabel8.setIcon(configPanel.configClientUtils.getImage(docBase + "../images/taskdetails.png"));		
		JButton1.setIcon(configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+ 
					"../images/contextualhelp.png"));
	}

	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      JButton1_JButton1_conn2 JButton1_JButton1_conn21 =  new JButton1_JButton1_conn2();
      JButton1.addActionListener(JButton1_JButton1_conn21);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      closeButton.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
	} 
	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel8= new javax.swing.JPanel();
        JLabel6= new javax.swing.JLabel();
        JButton1= new javax.swing.JButton();
        JPanel4= new javax.swing.JPanel();
        closeButton= new javax.swing.JButton();
        JPanel6= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JTextField2= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        JTextField3= new javax.swing.JTextField();
        commonLabel= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JTextField1= new javax.swing.JTextField();
        JPanel7= new javax.swing.JPanel();
        deviceListText= new javax.swing.JTextField();
        JLabel7= new javax.swing.JLabel();
        JTextField5= new javax.swing.JTextField();
        JLabel8= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        cardPanel= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        JComboBox1= new javax.swing.JComboBox();

  
        //<End_initVariables>
	} 

	public void setUpGUI(Container container)
  { 

		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(10,5,10,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,20);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JPanel8,cons);
JPanel8.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel8.add(JLabel6,cons);
inset = new Insets(5,5,5,10);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JButton1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(2,5,5));
JPanel4.add(closeButton);
inset = new Insets(5,5,10,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,25);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JTextField2,cons);
inset = new Insets(5,5,5,25);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JTextField3,cons);
inset = new Insets(5,5,15,25);
setConstraints(0,4,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(commonLabel,cons);
inset = new Insets(15,5,5,25);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel1,cons);
inset = new Insets(15,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,3,0);
JPanel2.add(JTextField1,cons);
inset = new Insets(5,5,15,5);
setConstraints(1,4,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel7,cons);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel7.add(deviceListText,cons);
inset = new Insets(5,5,5,25);
setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel7,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JTextField5,cons);
inset = new Insets(0,0,0,10);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel6.add(JLabel8,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(cardPanel,BorderLayout.CENTER);
cardPanel.setLayout(new GridBagLayout());
JPanel3.add(JPanel5,BorderLayout.NORTH);
JPanel5.setLayout(new FlowLayout(0,22,5));
JPanel5.add(JLabel4);
JPanel5.add(JComboBox1);

  
//<End_setUpGUI_Container>
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





	public void comboAction()
	{
		/*int index = JComboBox1.getSelectedIndex();
		  if(index == 0)
		  {
		  CardPanel1.showCard("card2");
		  }
		  else
		  {
		  CardPanel1.showCard("card1");
		  }*/
	}




	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  closeButtonActionPerformed();

     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>









	public TaskDetails()
  {
		//<Begin_TaskDetails>
    pack();
  
    //<End_TaskDetails>
	}

	public TaskDetails(java.applet.Applet applet)
  {
		//<Begin_TaskDetails_java.applet.Applet>
    this.applet = applet;
    pack();
    
    // Commented to handle the Window Closing Events as part of Operation Theme
    //this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // Added by saravanasp as part of Operation Theme.
    addWindowListener(this);
  
    //<End_TaskDetails_java.applet.Applet>
	}


	public void closeButtonActionPerformed()
	{
		//cardLayout.next(cardPanel);
		//JPanel5.setVisible(false);
		TaskDetails.this.setVisible(false);
		TaskDetails.this.dispose(); 
	}
	private void setValueForDetails()
	{
		for(int i = 0; i <= generalDetailsVector.size(); i++)
		{
			JTextField1.setText((String)generalDetailsVector.elementAt(0));
			JTextField2.setText((String)generalDetailsVector.elementAt(1));
			JTextField3.setText((String)generalDetailsVector.elementAt(2));
			JTextField5.setText((String)generalDetailsVector.elementAt(4));

		}
	}

	private void setRespectiveCard()
	{
		String protocol = (String)generalDetailsVector.elementAt(2);
		if(isCombinedTask)
		{
			JPanel5.setVisible(true);
		}
		else
		{	
			JPanel5.setVisible(false);
		}
		cardLayout.show(cardPanel, taskName);
	}

	private void getTaskDetailsFromServer()
	{

		Object[] taskName = new Object[1];
		taskName[0] = generalDetailsVector.elementAt(0);
		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_TASK, taskName, this);
		if(! configPanel.configMainUI.isTemplate(taskName[0].toString()))	
		{
			commonLabel.setText(resourceBundle.getString("Device Lists"));
			configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DEVICELIST_FOR_TASK,taskName,this);
		}
		else
		{
			commonLabel.setText(resourceBundle.getString("Data Sources"));
			configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_ASSOCIATED_DATASOURCES,taskName,this);
		}	
	}

	private void getTaskInfo(String generalDetails)
	{
		if(cardLayout == null)
		{
			cardLayout = new CardLayout(5,5);
			cardPanel.setLayout(cardLayout);
		}
		ConfigTaskReader configReader = new ConfigTaskReader(configPanel, generalDetails);
		Vector columnNames = new Vector();
		boolean combined = configReader.isCombinedTask();
		Vector attributesVector = configReader.getTaskAttributes();
		if(! combined)
		{
			protocolString = configReader.getProtocol();
			taskName = configReader.getTaskName();
			if(protocolString.equalsIgnoreCase("SNMP"))
			{
				columnNames.addElement(resourceBundle.getString("OID"));
				columnNames.addElement(resourceBundle.getString("Label"));
				columnNames.addElement(resourceBundle.getString("Type"));
				columnNames.addElement(resourceBundle.getString("Value"));
				snmpDetails = new SnmpDetails(configPanel, attributesVector, columnNames);
				//cardLayout.addLayoutComponent("snmpdetails",snmpDetails);
				cardPanel.removeAll();
				cardPanel.add(taskName, snmpDetails);
			}
			else
			{
				if(protocolString.equalsIgnoreCase("Telnet"))
				{
					columnNames.addElement(resourceBundle.getString("Command"));
					columnNames.addElement(resourceBundle.getString("Command Arguments"));
					columnNames.addElement(resourceBundle.getString("Prompt"));

				}
				else if(protocolString.equalsIgnoreCase("Tftp") || protocolString.equalsIgnoreCase("ftp"))
				{

					columnNames.addElement(resourceBundle.getString("Command"));
					columnNames.addElement(resourceBundle.getString("Source"));
					columnNames.addElement(resourceBundle.getString("Destination"));
					columnNames.addElement(resourceBundle.getString("Mode"));

				}
				else if(protocolString.equalsIgnoreCase("TL1"))
				{	
					columnNames.addElement(resourceBundle.getString("Command Code"));
					columnNames.addElement(resourceBundle.getString("Target ID"));
					columnNames.addElement(resourceBundle.getString("Access ID"));
					columnNames.addElement(resourceBundle.getString("General Block"));
					columnNames.addElement(resourceBundle.getString("Message Payload Block"));

				}
				else if (protocolString.equalsIgnoreCase("Netconf"))//No I18N
				{
					for (int i=0;i<NetconfAttributeConstants.ATTRIBUTE_KEYS.length;i++)
					{
						columnNames.addElement(resourceBundle.getString(NetconfAttributeConstants.ATTRIBUTE_KEYS[i]));
					}
				}
	
				commonDetails = new CommonDetails(configPanel, attributesVector, columnNames);

				//cardLayout.addLayoutComponent("commondetails",commonDetails);
				cardPanel.removeAll();
				cardPanel.add(taskName, commonDetails);

			}
			setRespectiveCard();
		}
		else
		{
			for(int i = 0; i < attributesVector.size(); i++)
			{
				JComboBox1.addItem(attributesVector.elementAt(i));
			}

			JComboBox1.setSelectedItem(attributesVector.elementAt(0));
		}

		setValueForDetails();
	}

	private void checkForCombinedTask()
	{
		String taskName = (String)generalDetailsVector.elementAt(0);
		isCombinedTask = configPanel.configMainUI.isCombined(taskName);

	}

	private void setUpListener()
	{
		JComboBox1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae)
	{
		JComboBox combo = (JComboBox)ae.getSource();
		String source = (String)combo.getSelectedItem();
		Object[] params = {source};
		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_TASK, params, this);
	}

	public void response(ConfigResultEvent cre)
	{
		int workId = cre.getWorkID();
		int errorCode = cre.getErrorCode();
		String uniqueId = cre.getRequestID();

		if(workId == NmsConfigConstants.GET_TASK)
		{
			/*if(firsttime)
			{
				firsttime = false;
				setVisible(true);
			}*/
			
			String generalDetails = cre.getTask();
			getTaskInfo(generalDetails);		
			if(firsttime)
			{
				firsttime = false;
				setVisible(true);
			}

		}

		else if(workId ==NmsConfigConstants.GET_DEVICELIST_FOR_TASK)
		{
			Vector v = cre.getTaskSpecificDeviceListNames();
			if(v !=null)
			{
				String str = "";
				for(int i=0;i<v.size();i++)
				{
					if(i == v.size() -1)
						str = str+v.elementAt(i);
					else
						str = str+v.elementAt(i)+", ";
				}

				deviceListText.setText(str);
			}
			else
			{
				//devicesButton.setEnabled(false);				
			}
		}

		else if(workId ==NmsConfigConstants.GET_ASSOCIATED_DATASOURCES)
		{
			String [] str = cre.getAssociatedDataSources();
			if(str != null)
			{
				String string = "";
				for(int i=0;i<str.length;i++)
				{
					if(i == str.length-1)
						string = string+str[i];
					else
						string = string+str[i]+", ";
				}

				deviceListText.setText(string);
			}	
		}
	}








	public void devicesButtonActionPerformed()
	{

	}




	//<Begin__class_JButton1_JButton1_conn2>

 class JButton1_JButton1_conn2 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn2>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  contextSensitiveHelpActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn2>
 }//<End__class_JButton1_JButton1_conn2>

	public void contextSensitiveHelpActionPerformed()
	{
		if(helpDialog == null)
		{
			helpDialog = new HelpDialog(configPanel, this);
		}
		else
		{
			if(!helpDialog.isVisible())
			{
				configPanel.configClientUtils.centerWindow(helpDialog);
			}
		}

		helpDialog.setVisible(true);
	}

	public String  getHelpFor(String key)
	{
		String helpContent = "";

		if(key.equals(resourceBundle.getString("Task Details")))
		{
			helpContent = "\t" + resourceBundle.getString("This User Interface gives you details like the name of the Task, the protocol under which it is defined, the Rollback configuration for this Task, the description given for this task and the attributes defined for it. When the Task has been defined as a Template, the various Data Sources configured for it is also listed (comma separated). The attributes of the task are shown in the table. If the selected task happens to be a Combined Task, the Sub-Task details can be viewed by selecting them from the Combo Box which will be provided above the Table which shows the attributes.");
		}

		return helpContent;

	}

	public String getTitle()
	{
		return resourceBundle.getString("Task Details");
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

    // The below methods till the WindowStateChanged() method are included as part of operation Theme to cleanup the registered Listeners.
    public void windowClosed(WindowEvent we)
    {}

    public void windowActivated(WindowEvent we)
    {}

    public void windowClosing(WindowEvent we)
    {
	closeButtonActionPerformed();
	removeWindowListener(this);
    }

    public void windowDeactivated(WindowEvent we)
    {}

    public void windowDeiconified(WindowEvent we)
    {}

    public void windowGainedFocus(WindowEvent we)
    {}
    
    public void windowIconified(WindowEvent we)
    {}
    
    public void windowLostFocus(WindowEvent we)
    {}

    public void windowOpened(WindowEvent we)
    {}

    public void windowStateChanged(WindowEvent we)
    {}


}
