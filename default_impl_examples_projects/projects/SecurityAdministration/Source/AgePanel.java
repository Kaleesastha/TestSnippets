//$Id: AgePanel.java,v 1.3 2010/10/29 13:46:41 swaminathap Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$13
//<End_Version>
package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Hashtable;




public class AgePanel extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel JLabel3 = null;
	com.adventnet.beans.text.NumericTextField NumericTextField1 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JCheckBox JCheckBox1 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel4 = null;
	com.adventnet.beans.text.NumericTextField NumericTextField2 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JCheckBox JCheckBox11 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JCheckBox JCheckBox2 = null;
	javax.swing.JComboBox JComboBox1 = null;
	javax.swing.JPanel JPanel41 = null;
	javax.swing.JLabel JLabel31 = null;
	javax.swing.JTextField FullNameTextField11 = null;
	javax.swing.JCheckBox JCheckBox12 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	private	String user = "";


  public AgePanel()
  {
    //<Begin_AgePanel>
    pack();
  
    //<End_AgePanel>
  }

  public AgePanel(java.applet.Applet applet)
  {
    //<Begin_AgePanel_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_AgePanel_java.applet.Applet>
  }

//This is for the change as the JDialog.
   public AgePanel(Frame owner, java.applet.Applet applet)
  {
    super(owner);
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
   //System.out.println("IMAGE "+owner.getClass().getName());  
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
        this.setSize(getPreferredSize().width+565,getPreferredSize().height+550); 
          setTitle(resourceBundle.getString("AgePanel"));
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
 	setTitle(resourceBundle.getString("User Profile"));
	//setResizable(false);
   	 //setIconImage(AuthMain.getBuilderUiIfInstance().getFrameIcon());	
	AuthMain.getBuilderUiIfInstance().centerWindow(this);
	JComboBox1.addItem("enable");
	JComboBox1.addItem("disable");
//setFrameIcon(
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
            if (input.equals("MS_MODE")) value = "3"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "SecurityAdministrationResources"; 
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(1));
            JPanel1.setPreferredSize(new Dimension(856,962));
            JPanel1.setMinimumSize(new Dimension(856,982));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JPanel2.setBorder(new javax.swing.border.EtchedBorder(1));
            JPanel2.setPreferredSize(new Dimension(530,673));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JPanel4.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Account expiry for :")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JLabel3.setHorizontalAlignment(2);
            JLabel3.setFont(new Font("SansSerif",0,12));
            JLabel3.setHorizontalTextPosition(4);
            JLabel3.setText(resourceBundle.getString("This user account expires in"));
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
            NumericTextField1.setEditable(true);
            NumericTextField1.setEnabled(false);
            NumericTextField1.setMaxValue(999);
            NumericTextField1.setMinValue(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NumericTextField1,ex); 
          }

//<UserCode_Begin_Bean_NumericTextField1>
NumericTextField1.setMinValue(0);
//<UserCode_End_Bean_NumericTextField1>

          try
          {
            JLabel5.setHorizontalAlignment(2);
            JLabel5.setFont(new Font("SansSerif",0,12));
            JLabel5.setHorizontalTextPosition(4);
            JLabel5.setText(resourceBundle.getString("Day(s)."));
            JLabel5.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JCheckBox1.setFont(new Font("SansSerif",0,12));
            JCheckBox1.setHorizontalTextPosition(4);
            JCheckBox1.setText(resourceBundle.getString("Account never expires"));
            JCheckBox1.setSelected(true);
            JCheckBox1.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JCheckBox1,ex); 
          }

//<UserCode_Begin_Bean_JCheckBox1>

//<UserCode_End_Bean_JCheckBox1>

          try
          {
            JPanel5.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Password expiry for :")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            JLabel4.setHorizontalAlignment(2);
            JLabel4.setFont(new Font("SansSerif",0,12));
            JLabel4.setHorizontalTextPosition(4);
            JLabel4.setText(resourceBundle.getString("The password expires in        "));
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
            NumericTextField2.setEnabled(false);
            NumericTextField2.setMaxValue(999);
            NumericTextField2.setMinValue(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NumericTextField2,ex); 
          }

//<UserCode_Begin_Bean_NumericTextField2>
NumericTextField2.setMinValue(0);
//<UserCode_End_Bean_NumericTextField2>

          try
          {
            JLabel6.setHorizontalAlignment(2);
            JLabel6.setFont(new Font("SansSerif",0,12));
            JLabel6.setHorizontalTextPosition(4);
            JLabel6.setText(resourceBundle.getString("Days(s)."));
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
            JCheckBox11.setFont(new Font("SansSerif",0,12));
            JCheckBox11.setHorizontalTextPosition(4);
            JCheckBox11.setText(resourceBundle.getString("Password never expires"));
            JCheckBox11.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JCheckBox11,ex); 
          }

//<UserCode_Begin_Bean_JCheckBox11>

//<UserCode_End_Bean_JCheckBox11>

          try
          {
            JPanel6.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Status for the user :")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            JLabel1.setFont(new Font("Dialog",0,12));
            JLabel1.setForeground(new Color(-16763338));
            JLabel1.setText(resourceBundle.getString("Select the status to be set for this user"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JCheckBox2.setFont(new Font("Dialog",0,12));
            JCheckBox2.setForeground(new Color(-16764109));
            JCheckBox2.setText(resourceBundle.getString("No change in status"));
            JCheckBox2.setEnabled(true);
            JCheckBox2.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JCheckBox2,ex); 
          }

//<UserCode_Begin_Bean_JCheckBox2>

//<UserCode_End_Bean_JCheckBox2>

          try
          {
            JComboBox1.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JComboBox1,ex); 
          }

//<UserCode_Begin_Bean_JComboBox1>

//<UserCode_End_Bean_JComboBox1>

          try
          {
            JPanel41.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Full Name of the user:")));//NO I18N
          }
          catch(Exception ex)
          {
              showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel41,ex);//NO I18N
          }

//<UserCode_Begin_Bean_JPanel41>

//<UserCode_End_Bean_JPanel41>

          try
          {
            JLabel31.setHorizontalAlignment(2);
            JLabel31.setFont(new Font("SansSerif",0,12));//NO I18N
            JLabel31.setHorizontalTextPosition(4);
            JLabel31.setForeground(new Color(-16764109));
            JLabel31.setText(resourceBundle.getString("Enter the Full Name"));//NO I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel31,ex);//NO I18N
          }

//<UserCode_Begin_Bean_JLabel31>

//<UserCode_End_Bean_JLabel31>

          try
          {
            FullNameTextField11.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+FullNameTextField11,ex);//NO I18N 
          }

//<UserCode_Begin_Bean_FullNameTextField11>
NumericTextField1.setMinValue(0);
//<UserCode_End_Bean_FullNameTextField11>

          try
          {
            JCheckBox12.setFont(new Font("SansSerif",0,12));//NO I18N
            JCheckBox12.setHorizontalTextPosition(4);
            JCheckBox12.setSelected(true);
            JCheckBox12.setForeground(new Color(-16764109));
            JCheckBox12.setActionCommand(resourceBundle.getString("No change in full name"));//NO I18N
            JCheckBox12.setText(resourceBundle.getString("No change in full name"));//NO I18N
          }
          catch(Exception ex)
          {
              showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JCheckBox12,ex);//NO I18N
          }

//<UserCode_Begin_Bean_JCheckBox12>

//<UserCode_End_Bean_JCheckBox12>

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
            JLabel2.setHorizontalAlignment(2);
            JLabel2.setFont(new Font("SansSerif",0,12));
            JLabel2.setHorizontalTextPosition(4);
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setText(resourceBundle.getString("<html><body><font face=\"sansserif,SansSerif\"><font size=3>   Please enter the number of days in which  <br><font face=\"sansserif,SansSerif\">    the user and/or the password expires...</font><br><br><font face=\"san sserif,SansSerif\">   A value of zero indicates no expiry.</font></body></html>"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JButton1.setText(resourceBundle.getString("  Ok  "));
            JButton1.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>
JButton1.setMnemonic('O');
//<UserCode_End_Bean_JButton1>

          try
          {
            JButton2.setText(resourceBundle.getString("Cancel"));
            JButton2.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>
JButton2.setMnemonic('C');
//<UserCode_End_Bean_JButton2>
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+400,JPanel1.getPreferredSize().height+0));

  
          //<End_setUpProperties>
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
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        NumericTextField1= new com.adventnet.beans.text.NumericTextField();
        JLabel5= new javax.swing.JLabel();
        JCheckBox1= new javax.swing.JCheckBox();
        JPanel5= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        NumericTextField2= new com.adventnet.beans.text.NumericTextField();
        JLabel6= new javax.swing.JLabel();
        JCheckBox11= new javax.swing.JCheckBox();
        JPanel6= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JCheckBox2= new javax.swing.JCheckBox();
        JComboBox1= new javax.swing.JComboBox();
        JPanel41= new javax.swing.JPanel();
        JLabel31= new javax.swing.JLabel();
        FullNameTextField11= new javax.swing.JTextField();
        JCheckBox12= new javax.swing.JCheckBox();
        JPanel3= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.01,0.0,cons.NORTH,cons.BOTH,inset,0,0);
JPanel1.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(NumericTextField1,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JLabel5,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JCheckBox1,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(NumericTextField2,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JLabel6,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JCheckBox11,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.01,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.01,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel6.add(JCheckBox2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.EAST,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JComboBox1,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel41,cons);
JPanel41.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel41.add(JLabel31,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,2,1,0.15,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel41.add(FullNameTextField11,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel41.add(JCheckBox12,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.SOUTH,cons.BOTH,inset,0,0);
JPanel1.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,3,1,0.01,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.01,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel3.add(JButton1,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel3.add(JButton2,cons);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      JCheckBox12_FullNameTextField11_conn JCheckBox12_FullNameTextField11_conn1 =  new JCheckBox12_FullNameTextField11_conn();
      JCheckBox12.addItemListener(JCheckBox12_FullNameTextField11_conn1);
      JButton1_JButton1_conn JButton1_JButton1_conn1 =  new JButton1_JButton1_conn();
      JButton1.addActionListener(JButton1_JButton1_conn1);
      JCheckBox11_NumericTextField2_conn JCheckBox11_NumericTextField2_conn1 =  new JCheckBox11_NumericTextField2_conn();
      JCheckBox11.addItemListener(JCheckBox11_NumericTextField2_conn1);
      JCheckBox1_NumericTextField1_conn JCheckBox1_NumericTextField1_conn1 =  new JCheckBox1_NumericTextField1_conn();
      JCheckBox1.addItemListener(JCheckBox1_NumericTextField1_conn1);
      JCheckBox2_JComboBox1_conn1 JCheckBox2_JComboBox1_conn11 =  new JCheckBox2_JComboBox1_conn1();
      JCheckBox2.addItemListener(JCheckBox2_JComboBox1_conn11);
      JButton2_JButton2_conn JButton2_JButton2_conn1 =  new JButton2_JButton2_conn();
      JButton2.addActionListener(JButton2_JButton2_conn1);
  
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
	
	//Utility Methods.
	String userStatus = "new";
	public void setUserProfile(String userage,String pwdage,String status,String group,String descName)
	{
			user = group;		
			userStatus = status;
 			JPanel5.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Password expiry for :")+group));
			JPanel4.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Account expiry for :")+group));
			JPanel6.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Status for the user :")+group));
			JPanel41.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Full Name of the user:")+group));//NO I18N
			NumericTextField1.setText(userage+"");
			if(!userage.equals("0") ){
			JCheckBox1.setSelected(false);
			NumericTextField1.setEnabled(true);
			}
			else{
			JCheckBox1.setSelected(true);
			
			}
 		
			NumericTextField2.setText(pwdage+"");
			if(!pwdage.equals("0") ){
			JCheckBox11.setSelected(false);
			NumericTextField2.setEnabled(true);
			}
			else{
			JCheckBox11.setSelected(true);
		         }
		         JCheckBox12.setSelected(true);
		         FullNameTextField11.setText(descName);
 			
	}
	

  public boolean isAgeSet()
	{
		boolean ageSet = false;		
		if(!JCheckBox1.isSelected()  ||  !JCheckBox11.isSelected())
			{
				ageSet = true;
			}
 		return ageSet;	
  	}

  public Hashtable getUserAttributes()
	{
		Hashtable hash = new Hashtable();

		if(NumericTextField1.getText().equals("0"))
		{
			//hash.put("uage","null");
		}
		else
		{
			hash.put("uage",NumericTextField1.getText());
		}
		if(NumericTextField2.getText().equals("0"))
		{
		//hash.put("pwage","null");
		}
		else
		{
			hash.put("pwage",NumericTextField2.getText());
		}
		
		return hash;
	}


 


//<Begin__class_JButton1_JButton1_conn>

 class JButton1_JButton1_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
   Integer userage = new Integer(NumericTextField1.getText()); 
   Integer passage = new Integer(NumericTextField2.getText());
   String descName = FullNameTextField11.getText();
   String status = "";
  if(JCheckBox2.isSelected() == false)
  {
  status =JComboBox1.getSelectedItem().toString()+"d";
  userStatus = status; 
   }
  else
  {
  status = userStatus; 
  }
  
if((user.equals(AuthMain.CURRENTUSER))  &&  userStatus.equals("disabled"))
 {
	 //String str = resourceBundle.getString("You have logged in as   ")+AuthMain.CURRENTUSER+" ."+"\n"+resourceBundle.getString("Cannot disable the currently logged in user");
	 String str = java.text.MessageFormat.format(resourceBundle.getString("You have logged in as {0} ."), new String[]{AuthMain.CURRENTUSER}) + "\n"+resourceBundle.getString("Cannot disable the currently logged in user");
   JOptionPane.showMessageDialog(null,str, resourceBundle.getString("Warning!"), JOptionPane.WARNING_MESSAGE, null);
userStatus = "enabled";    
  return;  
 }    
  AuthMain.main.disableButtons(); 
  AuthMain.model.setUserAttributes(user,status,userage,passage,descName);
  AuthMain.model.setUserStatus(user, status);  
   //AuthMain.main.disableButtons();
   dispose();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn>
 }//<End__class_JButton1_JButton1_conn>


//<Begin__class_JButton2_JButton2_conn>

 class JButton2_JButton2_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  dispose();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn>
 }//<End__class_JButton2_JButton2_conn>

 


//<Begin__class_JCheckBox11_NumericTextField2_conn>

 class JCheckBox11_NumericTextField2_conn implements java.awt.event.ItemListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox11_NumericTextField2_conn>



     //Listener Interface Methods Are Added Below 


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
  
        if( JCheckBox11.isSelected())
        {
          NumericTextField2.setEnabled(false);    
          NumericTextField2.setText("0");      
        } 
        else
        {
          NumericTextField2.setEnabled(true);        
        } 
     }
//<UserCode_End_Connection_JCheckBox11_NumericTextField2_conn>
 }//<End__class_JCheckBox11_NumericTextField2_conn>
//<Begin__class_JCheckBox1_NumericTextField1_conn>

 class JCheckBox1_NumericTextField1_conn implements java.awt.event.ItemListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox1_NumericTextField1_conn>



     //Listener Interface Methods Are Added Below 


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
  
        if( JCheckBox1.isSelected())
        {
          NumericTextField1.setEnabled(false);    
             NumericTextField1.setText("0");      
         } 
         else
         {
          NumericTextField1.setEnabled(true);        
         } 
     }
//<UserCode_End_Connection_JCheckBox1_NumericTextField1_conn>
 }//<End__class_JCheckBox1_NumericTextField1_conn>

 








//<Begin__class_JCheckBox2_JComboBox1_conn1>

 class JCheckBox2_JComboBox1_conn1 implements java.awt.event.ItemListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox2_JComboBox1_conn1>



     //Listener Interface Methods Are Added Below 


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
     
   if( JCheckBox2.isSelected())
        {
                  JComboBox1.setEnabled(false);
        } 
        else
        {
  JComboBox1.setEnabled( true);
        } 
  
     }
//<UserCode_End_Connection_JCheckBox2_JComboBox1_conn1>
 }//<End__class_JCheckBox2_JComboBox1_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

 


//<Begin__class_JCheckBox12_FullNameTextField11_conn>

 class JCheckBox12_FullNameTextField11_conn implements java.awt.event.ItemListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox12_FullNameTextField11_conn>

     //Listener Interface Methods Are Added Below 


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
          if(JCheckBox12.isSelected())
          {
          	 FullNameTextField11.setEnabled(false);
          }
          else
          {
             FullNameTextField11.setEnabled(true);  
          }
     }
//<UserCode_End_Connection_JCheckBox12_FullNameTextField11_conn>
 }//<End__class_JCheckBox12_FullNameTextField11_conn>
}






