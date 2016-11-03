
//$Id: ChangePwd.java,v 1.6 2010/10/29 13:46:42 swaminathap Exp $
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
import javax.swing.event.*;
import java.text.MessageFormat;
import com.adventnet.nms.client.ValidateCredentials;
import java.awt.*;
public class ChangePwd extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPasswordField oldPassword = null;
	javax.swing.JPasswordField newPassword = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


  
  
  










   


  public ChangePwd()
  {
    //<Begin_ChangePwd>
    pack();
  
    //<End_ChangePwd>
	
  }

  public ChangePwd(java.applet.Applet applet)
  {
    //<Begin_ChangePwd_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ChangePwd_java.applet.Applet>

  }

  public ChangePwd(Frame owner, java.applet.Applet applet)
  {
    super(owner);
    this.applet = applet;
    pack();
    //this.setTitle(resourceBundle.getString("ChangePwd"));
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
     
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

//<UserCode_Begin_Bean_Top>
//this.setModal(true);
//setResizable(false);
//<UserCode_End_Bean_Top>

          try
          {
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setText(resourceBundle.getString("Confirm New Password"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setFont(new Font("Dialog",0,12));
            JLabel1.setText(resourceBundle.getString("Enter New Password"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

//<UserCode_Begin_Bean_JPanel2>
JButton2.setMnemonic('C');
//<UserCode_End_Bean_JPanel2>

          try
          {
            JButton1.setFont(new Font("Dialog",0,13));
            JButton1.setText(resourceBundle.getString("Ok"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>
   JButton1.setMnemonic('0');
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

//<UserCode_End_Bean_JButton2>
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+4,JButton2.getPreferredSize().height+0));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+21,JButton1.getPreferredSize().height+0));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+169,JPanel2.getPreferredSize().height+0));

  
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
        this.setSize(getPreferredSize().width+328,getPreferredSize().height+115); 
          setTitle(resourceBundle.getString("ChangePwd"));
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
	oldPassword.requestFocus();
        int length=NmsClientUtil.getMaxPasswordLength();
	oldPassword.setDocument(new LimitDocument(length));
	newPassword.requestFocus();
	newPassword.setDocument(new LimitDocument(length));

	AuthMain.getBuilderUiIfInstance().centerWindow(this);
	oldPassword.requestFocus();
	setTitle(resourceBundle.getString("Change Password"));	
	
	addWindowListener(new WindowAdapter(){
				
					public void windowClosing(WindowEvent we)
					{
						close();
					}
				} );	
         addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent ke)
				{
					if(ke.getKeyChar() == ke.VK_ESCAPE)
						{
							close();
						}
				}
			 } );
		JButton1.setMnemonic('O');	
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
        JLabel2= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        oldPassword= new javax.swing.JPasswordField();
        newPassword= new javax.swing.JPasswordField();
        JPanel2= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.NORTH);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,10,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JLabel2,cons);
inset = new Insets(5,10,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(5,5,5,15);
setConstraints(2,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(oldPassword,cons);
inset = new Insets(5,5,5,15);
setConstraints(2,1,1,1,0.1,0.1,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(newPassword,cons);
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(JButton1);
JPanel2.add(JButton2);

  
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
 
public java.util.Vector userVec = null;
	
public void setUserData(String uname,java.util.Vector groups)
	{
		userVec = new java.util.Vector();
		userVec.add(0,uname);
		userVec.add(1,"");
  		//userVec.add(2,groups);
	}	

public AbstractSecurityModel model = null;

public void setSecurityModel(AbstractSecurityModel model)
	{
     	this.model = model;		
	}	
	
public void setUser()
	{
		
	userVec.add(1,newPassword.getText());	
	String passw = newPassword.getText();
	  
                    for(int i=0;i<passw.length();i++)
		  {
		   if(passw.charAt(i) == ' ')
		   {
		    Utilities.errorMessage(resourceBundle.getString("Illegal character in password space is not allowed"));
			//AuthMain.main.enableButtons();
		   return ; 
		   }	
		 }

		 for (int i = 0; i < passw.length(); i++) 
		 {
			 char ch = passw.charAt(i);
			 if (ch > 256 || ch < 0) 
			 {
				 Utilities.errorMessage(resourceBundle.getString("Please use ascii character for password."));
				 return;
			 }
		 }
		
     if(userVec.contains(""))
	userVec.remove("");
		
	//java.util.Vector groups = (java.util.Vector)userVec.elementAt(2);
	//groups.remove("");
	 //System.out.println("PASS "+userVec);		
	model.changePassword(userVec.elementAt(0).toString(),userVec.elementAt(1).toString());
	}	
public void close()
	{
 	//AuthMain.main.enableButtons();
     	dispose();  	
	}	

	
//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 
 dispose();
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
    // Added by vbala to validate characters entered while changing the password
    public boolean isValid(String text)
  {
	int length = text.length();

	for(int i=0;i<length;i++)
	{
	  int asciis = text.charAt(i);
	  if(!(asciis>=33 && asciis<=126))
	  {
		return false;
	   }
	}
	return true;
  }


//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  
         //AuthMain.main.disableButtons(); 
         if(oldPassword.getText().equals("") || newPassword.getText().equals("")) 
         {
             Utilities.errorMessage(resourceBundle.getString("Fields cannot be empty"));   
             return;  
         }  
         if(oldPassword.getText().equals(newPassword.getText()))
         {
             if(NmsClientUtil.getMinPasswordLength()!=-1 && oldPassword.getText().length() < NmsClientUtil.getMinPasswordLength())
             {
                 Object[] msgargs={new Integer(NmsClientUtil.getMinPasswordLength())};
                 MessageFormat msgform=new MessageFormat(NmsClientUtil.GetString("javaui.common.changepassword.minlength.reached"));//No I18N
                 String message = msgform.format(msgargs);
                 String title = NmsClientUtil.GetString("javaui.common.changepassword.title");//No I18N
                 JOptionPane.showMessageDialog(null,message,title,JOptionPane.ERROR_MESSAGE);
                 return;
                     
             }
             //Custom Validation
             ValidateCredentials validator = NmsClientUtil.getValidator();
	     java.util.Properties serverProps = new java.util.Properties();
	     serverProps = NmsClientUtil.appendServerProperties(serverProps);
             if(validator!=null)
             {
		     boolean done = validator.validate((String)userVec.elementAt(0),null,oldPassword.getText(),serverProps);
                 if(!done)
                 {
                     return ; 
                 }
	     }
	     else
	     {
	         if(!isValid(oldPassword.getText()))
		 {
		     Utilities.errorMessage(resourceBundle.getString("Illegal character in password")); //No I18N
		     return ;
		 }
	     }

             AuthMain.main.disableButtons();   
             setVisible(false);    
             dispose();      
             setUser();
         }
         else 
         {
             Utilities.errorMessage(resourceBundle.getString("The typed passwords do not match ")+
                                    "\n"+resourceBundle.getString("please retype the passwords")); 
         }         
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
} //<End__class_JButton1_JButton1_conn1>

  
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
























