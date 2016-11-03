

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory




package com.adventnet.nms.runtimeconfig;




//$Id: TrapAddModify.java,v 1.1.6.1 2012/01/25 05:12:46 karen.r Exp $




import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import com.adventnet.nms.util.CommonBuilderUIInterface;

public class TrapAddModify extends JDialog 
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
	javax.swing.JTextField JTextField1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField JTextField2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JRadioButton JRadioButton1 = null;
	javax.swing.JRadioButton JRadioButton2 = null;
	javax.swing.JPanel JPanel5 = null;
	com.adventnet.beans.panels.CardPanel CardPanel1 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JCheckBox JCheckBox1 = null;
	javax.swing.ButtonGroup ButtonGroup1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	Hashtable childHash = null;
  	Hashtable mainHash = null;
  	TrapFilterUI mainUI = null;
	CommonBuilderUIInterface uiUtils = null;	









   


  

  

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
            JLabel1.setHorizontalAlignment(11);
            JLabel1.setText(resourceBundle.getString("Class Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel2.setText(resourceBundle.getString("Trap Filter Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JPanel3.setBorder(new javax.swing.border.EtchedBorder(0));
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
            JPanel2.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Trap Filter Criteria"),1,2,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JPanel4.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Snmp version of incoming Trap"),1,2,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JRadioButton1.setText(resourceBundle.getString("Version 1"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JRadioButton1,ex); 
          }

//<UserCode_Begin_Bean_JRadioButton1>

//<UserCode_End_Bean_JRadioButton1>

          try
          {
            JRadioButton2.setText(resourceBundle.getString("Version 2 / Version 3"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JRadioButton2,ex); 
          }

//<UserCode_Begin_Bean_JRadioButton2>

//<UserCode_End_Bean_JRadioButton2>

          try
          {
            JPanel5.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Trap identifiers"),1,2,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            java.lang.String[]  CardPanel1cardAndClassNames_array = new java.lang.String[ 2 ]; 
            CardPanel1cardAndClassNames_array[ 0 ] = resourceBundle.getString("card1=com.adventnet.nms.runtimeconfig.cardPanel1");
            CardPanel1cardAndClassNames_array[ 1 ] = resourceBundle.getString("card2=com.adventnet.nms.runtimeconfig.cardPanel2");
            CardPanel1.setCardAndClassNames(CardPanel1cardAndClassNames_array);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+CardPanel1,ex); 
          }

//<UserCode_Begin_Bean_CardPanel1>

//<UserCode_End_Bean_CardPanel1>

          try
          {
            JCheckBox1.setText(resourceBundle.getString("Enable Filter"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JCheckBox1,ex); 
          }

//<UserCode_Begin_Bean_JCheckBox1>

//<UserCode_End_Bean_JCheckBox1>

//<UserCode_Begin_Bean_ButtonGroup1>
ButtonGroup1.add(JRadioButton1);
ButtonGroup1.add(JRadioButton2);

//<UserCode_End_Bean_ButtonGroup1>
		JLabel3.setPreferredSize(new Dimension(JLabel3.getPreferredSize().width+92,JLabel3.getPreferredSize().height+240));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+0,JPanel2.getPreferredSize().height+3));
		JPanel8.setPreferredSize(new Dimension(JPanel8.getPreferredSize().width+10,JPanel8.getPreferredSize().height+10));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+0,JButton2.getPreferredSize().height+4));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+18,JButton1.getPreferredSize().height+4));

  
          //<End_setUpProperties>
 TitledBorder tl=(TitledBorder)JPanel2.getBorder();
 tl.setTitle(resourceBundle.getString("Trap Filter Criteria"));
 
 tl=(TitledBorder)JPanel4.getBorder();
 tl.setTitle(resourceBundle.getString("Snmp version of incoming Trap"));

 tl=(TitledBorder)JPanel5.getBorder();
 tl.setTitle(resourceBundle.getString("Trap identifiers"));
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
        this.setSize(getPreferredSize().width+495,getPreferredSize().height+504); 
          setTitle(resourceBundle.getString("TrapAddModify"));
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
	//	JTextField2.setEditable(false);
          setTitle(resourceBundle.getString("Trap Filter Configuration"));
uiUtils.centerWindow(this);
		JRadioButton1.setSelected(true);
		CardPanel1.showCard("card1");
		JTextField2.requestFocus();
	JLabel3.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("classconfig.png","images/runtimeadmin"));
JButton1.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JButton2.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	setModal(true);
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JRadioButton1_JPanel4_conn1 JRadioButton1_JPanel4_conn11 =  new JRadioButton1_JPanel4_conn1();
      JRadioButton1.addChangeListener(JRadioButton1_JPanel4_conn11);
      JRadioButton2_JPanel4_conn1 JRadioButton2_JPanel4_conn11 =  new JRadioButton2_JPanel4_conn1();
      JRadioButton2.addChangeListener(JRadioButton2_JPanel4_conn11);
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
        JTextField1= new javax.swing.JTextField();
        JLabel2= new javax.swing.JLabel();
        JTextField2= new javax.swing.JTextField();
        JPanel3= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JPanel7= new javax.swing.JPanel();
        JPanel8= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JRadioButton1= new javax.swing.JRadioButton();
        JRadioButton2= new javax.swing.JRadioButton();
        JPanel5= new javax.swing.JPanel();
        CardPanel1= new com.adventnet.beans.panels.CardPanel(applet);
        JLabel3= new javax.swing.JLabel();
        JPanel6= new javax.swing.JPanel();
        JCheckBox1= new javax.swing.JCheckBox();
        ButtonGroup1= new javax.swing.ButtonGroup();

  
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
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(10,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(10,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JTextField1,cons);
inset = new Insets(20,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel2,cons);
inset = new Insets(20,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JTextField2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(JButton1);
JPanel3.add(JButton2);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel7,cons);
JPanel7.setLayout(new BorderLayout(5,5));
JPanel7.add(JPanel8,BorderLayout.CENTER);
JPanel8.setLayout(new BorderLayout(5,5));
JPanel8.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(10,20,5,20);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.BOTH,inset,0,0);
JPanel2.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(0,20,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JRadioButton1,cons);
inset = new Insets(0,20,0,0);
setConstraints(0,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JRadioButton2,cons);
inset = new Insets(5,20,10,20);
setConstraints(0,1,1,1,0.1,0.1,cons.NORTHWEST,cons.BOTH,inset,0,0);
JPanel2.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(0,0,10,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel5.add(CardPanel1,cons);
JPanel7.add(JLabel3,BorderLayout.WEST);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(0,5,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JCheckBox1,cons);

  
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


   public void populateUI(boolean flag,String filterName,Hashtable hash)
	{
	    mainHash = hash;
		if(flag)
		{
			JTextField2.setText(filterName);
			JTextField2.setEditable(false);
			if (hash.size() == 4 || hash.size() == 3)
			{
				JRadioButton2.setSelected(true);
				JRadioButton2.setEnabled(true);
				JRadioButton1.setEnabled(true);
				String check = (String)hash.get("enable");
				if(check.equalsIgnoreCase("true"))
					JCheckBox1.setSelected(true);
				else
					JCheckBox1.setSelected(false);
				String className =(String) hash.get("classname");
				JTextField1.setEditable(true);
				JTextField1.setText(className);
				String snmpDetails = (String)hash.get("trapoid");
				CardPanel1.showCard("card2");
				Component comp = CardPanel1.getSelectedCard();
				((cardPanel2)comp).setValues(flag,snmpDetails); 
			}
			else
			{
				JRadioButton1.setSelected(true);
				JRadioButton2.setEnabled(true);
				JRadioButton1.setEnabled(true);
				JTextField2.setEditable(false);
				String check = (String)hash.get("enable");
				if(check.equalsIgnoreCase("true"))
					JCheckBox1.setSelected(true);
				else
					JCheckBox1.setSelected(false);
				JTextField2.setText(filterName);
				String className = (String)hash.get("classname");
				JTextField1.setText(className);
				JTextField1.setEditable(true);
				String gt = (String)hash.get("GT");
				String st = (String)hash.get("ST");
				String enterprise = (String)hash.get("enterprise");
				CardPanel1.showCard("card1");
				Component comp = CardPanel1.getCard("card1");
				((cardPanel1)comp).setValues(flag,gt,st,enterprise);
			}
		}
		else
		{
			JTextField2.setText(filterName);
			JTextField2.setEditable(false);
			if (hash.size() == 4 || hash.size() == 3)
			{
				JRadioButton2.setSelected(true);
				JRadioButton2.setEnabled(false);
				JRadioButton1.setEnabled(false);
				String check = (String)hash.get("enable");
				if(check.equalsIgnoreCase("true"))
					JCheckBox1.setSelected(true);
				else
					JCheckBox1.setSelected(false);
				JCheckBox1.setEnabled(false);
				String className =(String) hash.get("classname");
				JTextField1.setText(className);
				JTextField1.setEditable(false);
				String snmpDetails = (String)hash.get("trapoid");
				CardPanel1.showCard("card2");
				Component comp = CardPanel1.getSelectedCard();
				((cardPanel2)comp).setValues(flag,snmpDetails); 
			}
			else
			{
				JRadioButton1.setSelected(true);
				JRadioButton1.setEnabled(false);
				JRadioButton2.setEnabled(false);
				JTextField2.setText(filterName);
				JTextField2.setEditable(false);
				String check = (String)hash.get("enable");
				if(check.equalsIgnoreCase("true"))
					JCheckBox1.setSelected(true);
				else
					JCheckBox1.setSelected(false);
				JCheckBox1.setEnabled(false);	
				String className = (String)hash.get("classname");
				JTextField1.setText(className);
				JTextField1.setEditable(false);
				String gt = (String)hash.get("GT");
				String st = (String)hash.get("ST");
				String enterprise = (String)hash.get("enterprise");
				CardPanel1.showCard("card1");
				Component comp = CardPanel1.getCard("card1");
				((cardPanel1)comp).setValues(flag,gt,st,enterprise);
			}
		}
	}

 
//<Begin__class_JRadioButton1_JPanel4_conn1>

 class JRadioButton1_JPanel4_conn1 implements javax.swing.event.ChangeListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JRadioButton1_JPanel4_conn1>



     //Listener Interface Methods Are Added Below 


     public void stateChanged( javax.swing.event.ChangeEvent arg0)
     {
  if (JRadioButton1.isSelected())
  CardPanel1.showCard("card1");
     }
//<UserCode_End_Connection_JRadioButton1_JPanel4_conn1>
 }//<End__class_JRadioButton1_JPanel4_conn1>

//<Begin__class_JRadioButton2_JPanel4_conn1>

 class JRadioButton2_JPanel4_conn1 implements javax.swing.event.ChangeListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JRadioButton2_JPanel4_conn1>



     //Listener Interface Methods Are Added Below 


     public void stateChanged( javax.swing.event.ChangeEvent arg0)
     {
  if (JRadioButton2.isSelected())
  CardPanel1.showCard("card2");
     }
//<UserCode_End_Connection_JRadioButton2_JPanel4_conn1>
 }//<End__class_JRadioButton2_JPanel4_conn1>


   public TrapAddModify(TrapFilterUI main,java.applet.Applet applet)
   {
   	this.applet = applet;
	uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();	
	mainUI = main;	
   }


  public TrapAddModify()
  {
		
    //<Begin_TrapAddModify>
    pack();
  
    //<End_TrapAddModify>

  }

  public TrapAddModify(java.applet.Applet applet)
  {
    //<Begin_TrapAddModify_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_TrapAddModify_java.applet.Applet>
  }

 
//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 TrapAddModify.this.dispose(); 
  setVisible(false);
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
	
	public void showCloseButton(boolean b)
	{
		if (b)
		{
			JButton1.setVisible(false);
			JButton2.setText(resourceBundle.getString("Close"));
		}
		else
		{
			JButton1.setVisible(true);
			JButton2.setText(resourceBundle.getString("Cancel"));
		}
	}

	public void okButtonActionPerformed()
	{
		mainUI.isModified = true;
		Hashtable temp = new Hashtable();
		String name = JTextField2.getText().trim();
		if (JTextField2.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please specify a filter name"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
			return;  
		}
		else
		{
			String classname =JTextField1.getText() ;
			if (JRadioButton1.isSelected())
			{
			    Component comp =CardPanel1.getSelectedCard();
			    temp = ((cardPanel1)comp).getValues();
			    if(JCheckBox1.isSelected())
				{
					temp.put("enable","true");
				}
				else
				{
					temp.put("enable","false");
				}
			    temp.put("classname",classname);
			    temp.put("name",name);
			}
			else 
			{
			    Component comp =CardPanel1.getSelectedCard();
			    temp = ((cardPanel2)comp).getValues();
				if(JCheckBox1.isSelected())
				{
					temp.put("enable","true");
				}
				else
				{
					temp.put("enable","false");
				}
			    temp.put("classname",classname);
			    temp.put("name",name);
			} 
			mainUI.addElement(temp,name);
		}
   		setVisible(false);
		this.dispose(); 
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}







