

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;




//$Id: AddModifyMainPanel.java,v 1.1 2006/08/29 13:57:02 build Exp $


import java.util.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddModifyMainPanel extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField typeText = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField menuText = null;
	javax.swing.JTextField iconText = null;
	javax.swing.JTextField filterText = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JTextArea JTextArea2 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JLabel JLabel5 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
       
  
  
  





   


  public AddModifyMainPanel()
  {
    //<Begin_AddModifyMainPanel>
    this.init();
  
    //<End_AddModifyMainPanel>
  }

  public AddModifyMainPanel(java.applet.Applet applet)
  {
    //<Begin_AddModifyMainPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_AddModifyMainPanel_java.applet.Applet>
       
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
            JPanel5.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Data Type Details"),0,2,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            JLabel1.setText(resourceBundle.getString("Type (*)"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel3.setText(resourceBundle.getString("Icon Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel4.setText(resourceBundle.getString("Map Filter"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JLabel2.setText(resourceBundle.getString("Menu File Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JButton1.setMaximumSize(new Dimension(25,20));
            JButton1.setMinimumSize(new Dimension(25,20));
            JButton1.setPreferredSize(new Dimension(25,20));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

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
            JTextArea2.setOpaque(false);
            JTextArea2.setLineWrap(true);
            JTextArea2.setWrapStyleWord(true);
            JTextArea2.setEditable(false);
            JTextArea2.setText(resourceBundle.getString("Press \"Next\" button to configure user properties"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea2,ex); 
          }

//<UserCode_Begin_Bean_JTextArea2>

//<UserCode_End_Bean_JTextArea2>

          try
          {
            JTextArea1.setOpaque(false);
            JTextArea1.setText(resourceBundle.getString("Fields with (*) are mandatory"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
          }

//<UserCode_Begin_Bean_JTextArea1>

//<UserCode_End_Bean_JTextArea1>

          try
          {
            JLabel5.setHorizontalTextPosition(2);
            JLabel5.setHorizontalAlignment(2);
            JLabel5.setFont(new Font("SansSerif",0,12));
            JLabel5.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+0,JPanel5.getPreferredSize().height+7));

  
          //<End_setUpProperties>
 TitledBorder tl=(TitledBorder)JPanel5.getBorder();
 tl.setTitle(resourceBundle.getString("Data Type Details"));

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
        setPreferredSize(new Dimension(getPreferredSize().width+404,getPreferredSize().height+365)); 
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
	JTextArea1.setEditable(false);
	JTextArea2.setEditable(false);
	JLabel5.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));
	JLabel6.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        typeText= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
        menuText= new javax.swing.JTextField();
        iconText= new javax.swing.JTextField();
        filterText= new javax.swing.JTextField();
        JButton1= new javax.swing.JButton();
        JPanel2= new javax.swing.JPanel();
        JLabel6= new javax.swing.JLabel();
        JTextArea2= new javax.swing.JTextArea();
        JTextArea1= new javax.swing.JTextArea();
        JLabel5= new javax.swing.JLabel();

  
        //<End_initVariables>
     JButton1.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("browse.png","images/"));
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel5,BorderLayout.CENTER);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(10,5,10,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel1,cons);
inset = new Insets(10,5,10,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(typeText,cons);
inset = new Insets(5,5,10,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel3,cons);
inset = new Insets(5,5,10,5);
setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel4,cons);
inset = new Insets(5,5,10,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel2,cons);
inset = new Insets(5,5,10,5);
setConstraints(1,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(menuText,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(iconText,cons);
inset = new Insets(5,5,10,5);
setConstraints(1,3,0,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(filterText,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel5.add(JButton1,cons);
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel6,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JTextArea2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JTextArea1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel5,cons);

  
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



 

	
	public void setValues(Hashtable mainHash,boolean addFlag)
	{
		typeText.setText(mainHash.get("TYPE").toString());
		if(!addFlag)
		{
			typeText.setEditable(false);
		}
		if(mainHash.get("menuName") == null)
			menuText.setText("");
		else
			menuText.setText(mainHash.get("menuName").toString());
		if(mainHash.get("iconName") == null)
			iconText.setText("");
		else
			iconText.setText(mainHash.get("iconName").toString());
		if(mainHash.get("MAP_FILTER") == null)
			filterText.setText("");
		else
			filterText.setText(mainHash.get("MAP_FILTER").toString());
	}

	public Hashtable getAllValues()
	{
		Hashtable temp = new Hashtable();
		temp.put("TYPE",typeText.getText());
		temp.put("menuName",menuText.getText());
		temp.put("iconName",iconText.getText());
		temp.put("MAP_FILTER",filterText.getText());
		return temp;
	}

 



	public void ButtonActionPerformed()
	{
		RuntimeConfigFrame.getCommonBuilderUIImpl().showImageBrowser(resourceBundle.getString("Remote File Chooser"), iconText);
	}

	

 


//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  ButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}















