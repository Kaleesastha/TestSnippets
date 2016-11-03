

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;



//$Id: AddModifyClassName.java,v 1.1.6.1 2012/01/25 05:12:46 karen.r Exp $


import com.adventnet.nms.util.CommonBuilderUIInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class AddModifyClassName extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JTextField classNameText = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField displayNameText = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JTextArea JLabel4 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JLabel JLabel6 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	boolean modifyFlag = false;
	DiscoveryFilterUI discoveryUI = null;
	MapFilterUI mapUI = null;
	PollFilterUI pollUI = null;
  	PolicyConfigurationUI policyUI = null;
  	CommonBuilderUIInterface uiUtils = null;
  








	public AddModifyClassName(DiscoveryFilterUI ui,java.applet.Applet applet)
	{	
		this.applet = applet;
		discoveryUI = ui;	
	}

	public AddModifyClassName(MapFilterUI ui,java.applet.Applet applet)
	{	
		this.applet = applet;
uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
		mapUI = ui;	
	}

	public AddModifyClassName(PollFilterUI ui,java.applet.Applet applet)
	{	
		this.applet = applet;
uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
		pollUI = ui;	
	}

	public AddModifyClassName(PolicyConfigurationUI ui,java.applet.Applet applet)
	{	
		this.applet = applet;
	uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
		policyUI = ui;	
	}
   


  public AddModifyClassName()
  {
	
    //<Begin_AddModifyClassName>
    pack();
  
    //<End_AddModifyClassName>
	
  }

  public AddModifyClassName(java.applet.Applet applet)
  {
    //<Begin_AddModifyClassName_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_AddModifyClassName_java.applet.Applet>
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
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JLabel1.setText(resourceBundle.getString("Filter Configuration"));
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

          try
          {
            JPanel2.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JPanel7.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel7,ex); 
          }

//<UserCode_Begin_Bean_JPanel7>

//<UserCode_End_Bean_JPanel7>

          try
          {
            JLabel2.setText(resourceBundle.getString("Class Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel3.setText(resourceBundle.getString("Display Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JPanel6.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            JLabel4.setOpaque(false);
            JLabel4.setLineWrap(true);
            JLabel4.setWrapStyleWord(true);
            JLabel4.setText(resourceBundle.getString(""));
            JLabel4.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JLabel6.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>
		JLabel6.setPreferredSize(new Dimension(JLabel6.getPreferredSize().width+82,JLabel6.getPreferredSize().height+193));
		JPanel6.setPreferredSize(new Dimension(JPanel6.getPreferredSize().width+0,JPanel6.getPreferredSize().height+44));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+271,JPanel4.getPreferredSize().height+72));
		JPanel7.setPreferredSize(new Dimension(JPanel7.getPreferredSize().width+117,JPanel7.getPreferredSize().height+10));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+0,JButton2.getPreferredSize().height+4));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+18,JButton1.getPreferredSize().height+4));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+4));

  
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
        this.setSize(getPreferredSize().width+467,getPreferredSize().height+377); 
          setTitle(resourceBundle.getString("AddModifyClassName"));
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
          setTitle(resourceBundle.getString("Filter Configuration"));
	RuntimeConfigFrame.getCommonBuilderUIImpl().centerWindow(this);
	JLabel6.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("classconfig.png","images/runtimeadmin"));
	JLabel4.setEditable(false);	
	JLabel4.setText(resourceBundle.getString("Please specify the class name with complete Package structure, if any. The specified class name should be present in the Server classpath to take effect immediately."));
	JLabel5.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));	
JButton1.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JButton2.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	classNameText.requestFocus();
	setModal(true);
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
        JPanel3= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JPanel2= new javax.swing.JPanel();
        JPanel7= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        classNameText= new javax.swing.JTextField();
        JLabel2= new javax.swing.JLabel();
        JLabel3= new javax.swing.JLabel();
        displayNameText= new javax.swing.JTextField();
        JPanel6= new javax.swing.JPanel();
        JLabel4= new javax.swing.JTextArea();
        JLabel5= new javax.swing.JLabel();
        JLabel6= new javax.swing.JLabel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JLabel1);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(JButton1);
JPanel3.add(JButton2);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JPanel7,BorderLayout.CENTER);
JPanel7.setLayout(new BorderLayout(5,5));
JPanel7.add(JPanel4,BorderLayout.CENTER);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel4.add(classNameText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel4.add(displayNameText,cons);
JPanel7.add(JPanel6,BorderLayout.SOUTH);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(5,5,10,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JLabel4,cons);
inset = new Insets(5,5,10,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel6.add(JLabel5,cons);
JPanel2.add(JLabel6,BorderLayout.WEST);

  
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
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

	public void populateUI(String s,boolean isModify)
	{
		modifyFlag = isModify;
		displayNameText.setVisible(false);
		JLabel3.setVisible(false);
		classNameText.setText(s);
	}

	public void populateUI(String s1,String s2,boolean isModify)
	{
		modifyFlag = isModify;
		displayNameText.setText(s2);
		classNameText.setText(s1);
	}

 
//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  AddModifyClassName.this.dispose();
  AddModifyClassName.this.setVisible(false);
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>


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
	
	public void okButtonActionPerformed()
	{
		String className = classNameText.getText().trim();
		if(className.equals(""))
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please specify the class name"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(pollUI != null)
		{
			pollUI.updateEntries(className,modifyFlag);
		}else if(mapUI != null)
		{
			mapUI.updateEntries(className,modifyFlag);
		}else if(discoveryUI != null)
		{
			discoveryUI.updateEntries(className,modifyFlag);
		}
		else if(policyUI != null)
		{
			String displayName = displayNameText.getText();
			if(displayName.equals(""))
			{
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please specify the display name"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			policyUI.updateEntries(className,displayName,modifyFlag);
		}
		this.setVisible(false);
		this.dispose();
		
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}







