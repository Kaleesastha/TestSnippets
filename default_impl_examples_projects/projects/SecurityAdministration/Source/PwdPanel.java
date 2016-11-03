
//$Id: PwdPanel.java,v 1.1 2006/08/29 13:57:02 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class PwdPanel extends JPanel 
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
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPasswordField enterPassText = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JPasswordField confirmPassText = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JLabel JLabel9 = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JLabel JLabel8 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


  
  
  
















   


  public PwdPanel()
  {
    //<Begin_PwdPanel>
    this.init();
  
    //<End_PwdPanel>
  }

  public PwdPanel(java.applet.Applet applet)
  {
    //<Begin_PwdPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_PwdPanel_java.applet.Applet>
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
            JLabel1.setFont(new Font("Dialog",0,12));
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setText(resourceBundle.getString("Password and user expiration"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

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
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setHorizontalAlignment(11);
            JLabel2.setText(resourceBundle.getString("Enter Password"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel3.setFont(new Font("Dialog",0,12));
            JLabel3.setForeground(new Color(-16764109));
            JLabel3.setHorizontalAlignment(11);
            JLabel3.setText(resourceBundle.getString("Confirm Password"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel5.setAlignmentX((float)0.5);
            JLabel5.setHorizontalAlignment(0);
            JLabel5.setFont(new Font("Dialog",0,11));
            JLabel5.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JLabel6.setFont(new Font("Dialog",0,12));
            JLabel6.setForeground(new Color(-16764109));
            JLabel6.setText(resourceBundle.getString("If you don't enter password the username"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

          try
          {
            JLabel9.setFont(new Font("Dialog",0,13));
            JLabel9.setForeground(new Color(-16764109));
            JLabel9.setText(resourceBundle.getString("is assumed as the default password ."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel9,ex); 
          }

//<UserCode_Begin_Bean_JLabel9>

//<UserCode_End_Bean_JLabel9>

          try
          {
            JLabel7.setFont(new Font("Dialog",0,12));
            JLabel7.setForeground(new Color(-16764109));
            JLabel7.setText(resourceBundle.getString("Click Next to Continue... "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel7,ex); 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>

          try
          {
            JLabel8.setIconTextGap(0);
            JLabel8.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel8,ex); 
          }

//<UserCode_Begin_Bean_JLabel8>

//<UserCode_End_Bean_JLabel8>
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+31,JPanel4.getPreferredSize().height+10));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+1,JPanel1.getPreferredSize().height+0));

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+630,getPreferredSize().height+400)); 
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
	
		enterPassText.requestFocus();
		
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        enterPassText= new javax.swing.JPasswordField();
        JLabel3= new javax.swing.JLabel();
        confirmPassText= new javax.swing.JPasswordField();
        JLabel4= new javax.swing.JLabel();
        JLabel5= new javax.swing.JLabel();
        JPanel4= new javax.swing.JPanel();
        JLabel6= new javax.swing.JLabel();
        JLabel9= new javax.swing.JLabel();
        JLabel7= new javax.swing.JLabel();
        JLabel8= new javax.swing.JLabel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.8,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JPanel2,cons);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JLabel1,BorderLayout.NORTH);
JPanel2.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(15,10,20,0);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JLabel2,cons);
inset = new Insets(15,10,20,20);
setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel3.add(enterPassText,cons);
inset = new Insets(10,10,0,0);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JLabel3,cons);
inset = new Insets(10,10,0,20);
setConstraints(1,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel3.add(confirmPassText,cons);
inset = new Insets(50,10,0,0);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JLabel4,cons);
inset = new Insets(50,0,0,0);
setConstraints(1,2,1,1,0.1,0.1,cons.NORTHWEST,cons.BOTH,inset,0,0);
JPanel3.add(JLabel5,cons);
JPanel2.add(JPanel4,BorderLayout.SOUTH);
JPanel4.setLayout(new GridLayout(3,1,5,5));
JPanel4.add(JLabel6);
JPanel4.add(JLabel9);
JPanel4.add(JLabel7);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.2,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JLabel8,cons);

  
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
            if (input.equals("MS_MODE")) value = "3"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "SecurityAdministrationResources"; 
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


  public String getPassword()
	{
		String pwd = "error";

		if(enterPassText.getText().equals("") && confirmPassText.getText().equals(""))	
		  {
			return "";
		  } 	
		
				
		if(enterPassText.getText().equals(confirmPassText.getText()))
			{
				return confirmPassText.getText();
			}
		return pwd;
		
	}
	
	public void enableFields()
		{
		  enterPassText.setEditable(true);
		  confirmPassText.setEditable(true);
		}
  public void setPassword()
	{
		//enterPassText.setEditable(true);
		//confirmPassText.setEditable(false);
		JLabel6.setText(resourceBundle.getString("If you don't enter password, the old password "));
 	}	

	public void setBuilderUiIf(com.adventnet.nms.util.CommonBuilderUIInterface  uiImpl)
	{
		
	 	JLabel8.setIcon(uiImpl.getImage("addpassword.png"));
		
	}


 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}





