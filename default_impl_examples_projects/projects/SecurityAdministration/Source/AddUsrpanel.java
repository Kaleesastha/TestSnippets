
//$Id: AddUsrpanel.java,v 1.4 2010/10/29 13:46:41 swaminathap Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.NmsClientUtil;
import java.text.MessageFormat;
import javax.swing.event.CaretEvent;


public class AddUsrpanel extends JPanel 
{
	
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel userNameLabel = null;
	javax.swing.JTextField userNameText = null;
	javax.swing.JLabel passwordLabel = null;
	javax.swing.JLabel confirmPasswordLabel = null;
	javax.swing.JPasswordField enterPassText = null;
	javax.swing.JPasswordField confirmPassText = null;
	javax.swing.JLabel descriptiveNameLabel = null;
	javax.swing.JTextField descriptiveNameText = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	
  	
  
  




   


 

 
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
            JPanel2.setFont(new Font("Dialog",0,12));
            JPanel2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("User Description")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>
//JPanel2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("User Description")));
//<UserCode_End_Bean_JPanel2>

          try
          {
            userNameLabel.setFont(new Font("Dialog",0,12));
            userNameLabel.setForeground(new Color(-16764109));
            userNameLabel.setHorizontalTextPosition(2);
            userNameLabel.setHorizontalAlignment(10);
            userNameLabel.setText(resourceBundle.getString("Enter the user name ( * )"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+userNameLabel,ex); 
          }

//<UserCode_Begin_Bean_userNameLabel>

//<UserCode_End_Bean_userNameLabel>

          try
          {
            passwordLabel.setText(resourceBundle.getString("Enter the password"));
            passwordLabel.setFont(new Font("Dialog",0,12));
            passwordLabel.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+passwordLabel,ex); 
          }

//<UserCode_Begin_Bean_passwordLabel>

//<UserCode_End_Bean_passwordLabel>

          try
          {
            confirmPasswordLabel.setText(resourceBundle.getString("Confirm password"));
            confirmPasswordLabel.setForeground(new Color(-16764109));
            confirmPasswordLabel.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+confirmPasswordLabel,ex); 
          }

//<UserCode_Begin_Bean_confirmPasswordLabel>

//<UserCode_End_Bean_confirmPasswordLabel>

          try
          {
              descriptiveNameLabel.setFont(new Font("Dialog",0,12));//NO I18N
            descriptiveNameLabel.setForeground(new Color(-16764109));
            descriptiveNameLabel.setHorizontalTextPosition(2);
            descriptiveNameLabel.setHorizontalAlignment(10);
            descriptiveNameLabel.setText(resourceBundle.getString("Enter the full name"));//NO I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+descriptiveNameLabel,ex);//NO I18N
          }

//<UserCode_Begin_Bean_descriptiveNameLabel>

//<UserCode_End_Bean_descriptiveNameLabel>

          try
          {
            JLabel3.setFont(new Font("Dialog",0,12));
            JLabel3.setForeground(new Color(-16764109));
            JLabel3.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel1.setIconTextGap(0);
            JLabel1.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>
		JLabel3.setPreferredSize(new Dimension(JLabel3.getPreferredSize().width+2402,JLabel3.getPreferredSize().height+183));
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+10,JPanel5.getPreferredSize().height+10));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+74,JPanel2.getPreferredSize().height+264));

  
          //<End_setUpProperties>
	JLabel1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("adduser.png"));	
	JLabel3.setText(resourceBundle.getString("<html><body><font face= \"sansserif,SansSerif\"><font size=2>   By default any new user added will<br>&nbsp; have only login permission.Selective <br> &nbsp; permissions can be assigned to the <br>&nbsp; user in the following two ways :<br><br>* Direct assignment of permissions.<br>* Making him a member of a group<br>&nbsp; which has preconfigured permissions.<br><br>&nbsp;If no password is entered the username<br>&nbsp; will be taken as the password.</body></html>"	));
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
        setPreferredSize(new Dimension(getPreferredSize().width+554,getPreferredSize().height+468)); 
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
	userNameText.requestFocus();	
	//removing the limit for user as it is hardcoded. Customers can modify the same if they require it
	//as it is open source
	//userNameText.setDocument(new LimitDocument(50));
        int maxLen = NmsClientUtil.getMaxPasswordLength();
	enterPassText.requestFocus();  
        enterPassText.setDocument(new LimitDocument(maxLen));
	confirmPassText.requestFocus();
	confirmPassText.setDocument(new LimitDocument(maxLen));
        
  } 
public void setFocus()
	{
		userNameText.requestFocus();
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
        JPanel3= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        userNameLabel= new javax.swing.JLabel();
        userNameText= new javax.swing.JTextField();
        passwordLabel= new javax.swing.JLabel();
        confirmPasswordLabel= new javax.swing.JLabel();
        enterPassText= new javax.swing.JPasswordField();
        confirmPassText= new javax.swing.JPasswordField();
        descriptiveNameLabel= new javax.swing.JLabel();
        descriptiveNameText= new javax.swing.JTextField();
        JPanel5= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
  } 
    
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.8,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JPanel1,cons);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(10,10,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(userNameLabel,cons);
inset = new Insets(10,20,0,20);
setConstraints(1,0,1,1,0.5,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(userNameText,cons);
inset = new Insets(10,10,0,0);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,1);
JPanel2.add(passwordLabel,cons);
inset = new Insets(10,10,40,0);
setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(confirmPasswordLabel,cons);
inset = new Insets(10,20,0,20);
setConstraints(1,2,1,1,0.5,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(enterPassText,cons);
inset = new Insets(10,20,0,20);
setConstraints(1,3,1,1,0.5,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(confirmPassText,cons);
inset = new Insets(10,10,0,0);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,1);
JPanel2.add(descriptiveNameLabel,cons);
inset = new Insets(10,20,0,20);
setConstraints(1,1,1,1,0.5,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(descriptiveNameText,cons);
JPanel1.add(JPanel5,BorderLayout.SOUTH);
JPanel5.setLayout(new BorderLayout(5,5));
JPanel5.add(JLabel3,BorderLayout.NORTH);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.2,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JLabel1,cons);

  
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




   


  public AddUsrpanel()
  {
    //<Begin_AddUsrpanel>
    this.init();
  
    //<End_AddUsrpanel>
  }

  public AddUsrpanel(java.applet.Applet applet)
  {
    //<Begin_AddUsrpanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_AddUsrpanel_java.applet.Applet>
  }

public String getUserName()
	{
		return userNameText.getText();
	}
	
public void setUserName(String uname)
	{
		userNameText.setText(uname);
		userNameText.setEditable(false);
	}
public void enableFields()
	{
		userNameText.setEditable(true);
	}


  public void setBuilderUiIf(com.adventnet.nms.util.CommonBuilderUIInterface  uiImpl)
	{
		uiImpl = AuthMain.getBuilderUiIfInstance();
		JLabel1.setIcon(uiImpl.getImage("adduser.png"));
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
	
	
  public void setPassword()
	{
		//enterPassText.setEditable(true);
		//confirmPassText.setEditable(false);
		//JLabel6.setText(resourceBundle.getString("If you don't enter password, the old password "));
 	}	

   boolean bool = false;

   public void setScreen(boolean bol)
	{
		bool = bol;
		System.out.println("set the state ae"+bol);
	}	
   public boolean getScreen()
	{
		System.out.println("GOT the state aS"+bool);
		return bool;
		
	}      
                           
    public void setDescName(String descName)
    {
        descriptiveNameText.setText(descName);
        descriptiveNameText.setEditable(false);
    }
    public String getDescName()
    {
        return descriptiveNameText.getText();
    }
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

    public class LimitDocument extends PlainDocument
  {
      private int limit;

      public LimitDocument(int limit)
      {
          super();
          setLimit(limit);
      }
      public final int getLimit()
      {
          return limit;
      }
      public void insertString(int offset, String s, AttributeSet attributeSet)
      {
	      if((s == null) || (s.length() == 0))
	      {
		      return;
	      }
              if((offset+s.length()) <= limit)
              {
                  try{
                  super.insertString(offset,s,attributeSet);
                  }
                  catch(Exception e){}  
	      }
	      else
	      {
		      JOptionPane.showOptionDialog (Top,MessageFormat.format(resourceBundle.getString("javaui.common.password.maxlength.errorMessage"),new Object[]{limit}),NmsClientUtil.GetString("javaui.common.changepassword.title"),JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,new Object[]{"OK"}, "OK");//No I18N
		      return;
	      }
          }

      public final void setLimit(int newValue)
      {
          this.limit = newValue;
      }
  }

}





















