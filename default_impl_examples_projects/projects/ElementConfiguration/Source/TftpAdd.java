
//$Id: TftpAdd.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class TftpAdd extends JDialog 
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
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JTextField sourceText = null;
	javax.swing.JTextField destinationText = null;
	javax.swing.JComboBox modeCombo = null;
	javax.swing.JComboBox commandCombo = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.DefaultListModel DefaultListModel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;
	private TftpPanel mainPanel = null;


	public TftpAdd(ConfigPanel configPanel, TftpPanel mainPanel)
	{
		super(configPanel.configClientUtils.getParentDialog(mainPanel));

		this.configPanel = configPanel;
		this.mainPanel = mainPanel;

		applet = configPanel.applet;

		init();
		configInit();
	}

	private void configInit()
	{
		String title = "";

		if(mainPanel.isScreenForFTP())
		{
			title = "FTP Task Configuration";
		}
		else
		{
			title = "TFTP Task Configuration";
		}

		title = resourceBundle.getString(title);

		JLabel1.setText(title);
		setTitle(title);

		ImageIcon image = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase() + "../images/taskconfig.png");
		JLabel2.setIcon(image);

		modeCombo.addItem("ASCII");
		modeCombo.addItem("Binary");

		commandCombo.addItem("Get");
		commandCombo.addItem("Put");

		configPanel.configClientUtils.centerWindow(this);
	}

	public TftpAdd()
	{
		//<Begin_TftpAdd>
		pack();

		//<End_TftpAdd>
	}

	public TftpAdd(java.applet.Applet applet)
	{
		//<Begin_TftpAdd_java.applet.Applet>
		this.applet = applet;
		pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		//<End_TftpAdd_java.applet.Applet>
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
			JLabel1.setText(resourceBundle.getString("TFTP Task Configuration"));
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
			JLabel2.setBorder(new javax.swing.border.SoftBevelBorder(1));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
		}

		//<UserCode_Begin_Bean_JLabel2>

		//<UserCode_End_Bean_JLabel2>

		try
		{
			JPanel4.setBorder(new javax.swing.border.SoftBevelBorder(0));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
		}

		//<UserCode_Begin_Bean_JPanel4>

		//<UserCode_End_Bean_JPanel4>

		try
		{
			JLabel3.setText(resourceBundle.getString("Source"));
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
			JLabel4.setText(resourceBundle.getString("Destination"));
			JLabel4.setForeground(new Color(-16764109));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
		}

		//<UserCode_Begin_Bean_JLabel4>

		//<UserCode_End_Bean_JLabel4>

		try
		{
			JLabel5.setForeground(new Color(-16764109));
			JLabel5.setText(resourceBundle.getString("Mode"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
		}

		//<UserCode_Begin_Bean_JLabel5>

		//<UserCode_End_Bean_JLabel5>

		try
		{
			JLabel6.setText(resourceBundle.getString("Command "));
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
			modeCombo.setPreferredSize(new Dimension(130,26));
			modeCombo.setMinimumSize(new Dimension(130,26));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+modeCombo,ex); 
		}

		//<UserCode_Begin_Bean_modeCombo>

		//<UserCode_End_Bean_modeCombo>

		try
		{
			commandCombo.setPreferredSize(new Dimension(130,26));
			commandCombo.setMinimumSize(new Dimension(130,26));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+commandCombo,ex); 
		}

		//<UserCode_Begin_Bean_commandCombo>

		//<UserCode_End_Bean_commandCombo>

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
			JButton1.setText(resourceBundle.getString("OK"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
		}

		//<UserCode_Begin_Bean_JButton1>

		//<UserCode_End_Bean_JButton1>

		try
		{
			JButton2.setText(resourceBundle.getString("Cancel"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
		}

		//<UserCode_Begin_Bean_JButton2>

		//<UserCode_End_Bean_JButton2>
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+22,JButton1.getPreferredSize().height+0));


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
		this.setSize(getPreferredSize().width+474,getPreferredSize().height+378); 
		setTitle(resourceBundle.getString("TftpAdd"));
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

	} 
	public void setUpConnections()
	{ 

		//<Begin_setUpConnections> 

		JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
		JButton2.addActionListener(JButton2_JButton2_conn11);
		JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
		JButton1.addActionListener(JButton1_JButton1_conn11);

		//<End_setUpConnections>
	} 
	public void initVariables()
	{ 

		//<Begin_initVariables> 
		Top= new javax.swing.JPanel();
		JPanel1= new javax.swing.JPanel();
		JLabel1= new javax.swing.JLabel();
		JPanel2= new javax.swing.JPanel();
		JLabel2= new javax.swing.JLabel();
		JPanel4= new javax.swing.JPanel();
		JLabel3= new javax.swing.JLabel();
		JLabel4= new javax.swing.JLabel();
		JLabel5= new javax.swing.JLabel();
		JLabel6= new javax.swing.JLabel();
		sourceText= new javax.swing.JTextField();
		destinationText= new javax.swing.JTextField();
		modeCombo= new javax.swing.JComboBox();
		commandCombo= new javax.swing.JComboBox();
		JPanel3= new javax.swing.JPanel();
		JButton1= new javax.swing.JButton();
		JButton2= new javax.swing.JButton();
		DefaultListModel1= new javax.swing.DefaultListModel();


		//<End_initVariables>
	} 

	public void setUpGUI(Container container)
	{ 

		//<Begin_setUpGUI_Container> 
		container.add(Top,BorderLayout.CENTER);
		Top.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		Top.add(JPanel1,cons);
		JPanel1.setLayout(new FlowLayout(1,5,5));
		JPanel1.add(JLabel1);
		inset = new Insets(5,5,5,5);
		setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		Top.add(JPanel2,cons);
		JPanel2.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
		JPanel2.add(JLabel2,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		JPanel2.add(JPanel4,cons);
		JPanel4.setLayout(new GridBagLayout());
		inset = new Insets(0,5,15,0);
		setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
		JPanel4.add(JLabel3,cons);
		inset = new Insets(0,5,15,0);
		setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
		JPanel4.add(JLabel4,cons);
		inset = new Insets(0,5,15,0);
		setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
		JPanel4.add(JLabel5,cons);
		inset = new Insets(0,5,15,0);
		setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
		JPanel4.add(JLabel6,cons);
		inset = new Insets(0,10,15,5);
		setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
		JPanel4.add(sourceText,cons);
		inset = new Insets(0,10,15,5);
		setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
		JPanel4.add(destinationText,cons);
		inset = new Insets(0,10,15,5);
		setConstraints(1,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
		JPanel4.add(modeCombo,cons);
		inset = new Insets(0,10,15,0);
		setConstraints(1,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
		JPanel4.add(commandCombo,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		Top.add(JPanel3,cons);
		JPanel3.setLayout(new FlowLayout(1,5,5));
		JPanel3.add(JButton1);
		JPanel3.add(JButton2);


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
	//<Begin__class_JButton2_JButton2_conn1>

	class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			TftpAdd.this.setVisible(false);
			TftpAdd.this.dispose();
		}
		//<UserCode_End_Connection_JButton2_JButton2_conn1>
	}//<End__class_JButton2_JButton2_conn1>




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

	public void okButtonActionPerformed()
	{
		String source = sourceText.getText().trim();
		String destination = destinationText.getText().trim();
		if(source.equals(""))
		{
			configPanel.configClientUtils.showErrorDialog(null,resourceBundle.getString("Please enter the Source name"),resourceBundle.getString("Info"),"Info");
			return;
		}
		String mode = modeCombo.getSelectedItem().toString();
		String command = commandCombo.getSelectedItem().toString();
		mainPanel.updateEntries(command,source,destination,mode);
		this.setVisible(false);
		this.dispose();
	}



	//<Begin__class_JButton1_JButton1_conn1>

	class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			okButtonActionPerformed();
		}
		//<UserCode_End_Connection_JButton1_JButton1_conn1>
	}//<End__class_JButton1_JButton1_conn1>

	public void populateData(String command, String source, String destination, String mode)
	{
		if(command.equalsIgnoreCase("Get"))
		{
			commandCombo.setSelectedIndex(0);
		}
		else	
		{
			commandCombo.setSelectedIndex(1);
		}
		if(mode.equalsIgnoreCase("ASCII"))
		{
			modeCombo.setSelectedIndex(0);
		}
		else	
		{
			modeCombo.setSelectedIndex(1);
		}
		sourceText.setText(source);
		destinationText.setText(destination);
	}






	public void setProperties(java.util.Properties props)
	{
		//<Begin_setProperties_java.util.Properties> 


		//<End_setProperties_java.util.Properties>
	}
}
