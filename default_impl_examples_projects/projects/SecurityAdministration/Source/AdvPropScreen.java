
//$Id: AdvPropScreen.java,v 1.1 2006/08/29 13:57:02 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class AdvPropScreen extends JPanel
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JLabel JLabel6 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


     Operations op  = null;
     ViewGroup  gva = null;
      ViewConfig vcon = null;	
     	

   private static AdvPropScreen advprop =   null;
  
   public static AdvPropScreen getInstance()
	{
 		return advprop;
	}


   


  public AdvPropScreen()
  {
    //<Begin_AdvPropScreen>
    this.init();
  
    //<End_AdvPropScreen>
  }

  public AdvPropScreen(java.applet.Applet applet)
  {
    //<Begin_AdvPropScreen_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_AdvPropScreen_java.applet.Applet>
  }
	
Frame owner = null;	
  public AdvPropScreen(Frame owner, java.applet.Applet applet)
  {
		
     this.owner = owner;	
     this.applet = applet;
     this.init();
		
  }	

 
    public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            JLabel1.setText(resourceBundle.getString("Advanced Security Configuration"));
            JLabel1.setHorizontalAlignment(0);
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
            JLabel7.setFont(new Font("Dialog",0,12));
            JLabel7.setForeground(new Color(-16764109));
            JLabel7.setText(resourceBundle.getString("Click to associate views to a group                                "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel7,ex); 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>

          try
          {
            JButton3.setText(resourceBundle.getString("Group  To  Views    "));
            JButton3.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>
JButton3.setMnemonic('G');
//<UserCode_End_Bean_JButton3>

          try
          {
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setText(resourceBundle.getString("Click to configure  views                                                 "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JButton1.setActionCommand(resourceBundle.getString("Views and Groups"));
            JButton1.setFont(new Font("Dialog",0,12));
            JButton1.setText(resourceBundle.getString(" View Configuration   "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>
JButton1.setMnemonic('V');
//<UserCode_End_Bean_JButton1>

          try
          {
            JLabel3.setForeground(new Color(-16764109));
            JLabel3.setFont(new Font("Dialog",0,12));
            JLabel3.setText(resourceBundle.getString("Click to Configure the Operations  Tree                         "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JButton2.setFont(new Font("Dialog",0,13));
            JButton2.setText(resourceBundle.getString(" Operations Tree     "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>
JButton2.setMnemonic('O');
//<UserCode_End_Bean_JButton2>

          try
          {
            JPanel5.setFont(new Font("Dialog",1,12));
            JPanel5.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Views and Operations")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            JLabel4.setFont(new Font("Dialog",0,12));
            JLabel4.setForeground(new Color(-16764109));
            JLabel4.setText(resourceBundle.getString("The group to which a user belongs is associated with a view or a set of views, a view has a set of "));
            JLabel4.setVerticalAlignment(3);
            JLabel4.setVerticalTextPosition(3);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JLabel5.setFont(new Font("Dialog",0,12));
            JLabel5.setForeground(new Color(-16764109));
            JLabel5.setVerticalAlignment(3);
            JLabel5.setText(resourceBundle.getString("operations asssociated with it, The \"Views\" and \"Operations\" can be configured independently"));
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
            JLabel6.setVerticalAlignment(1);
            JLabel6.setVerticalTextPosition(1);
            JLabel6.setText(resourceBundle.getString("and later associated with a User-Group."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+10,JPanel5.getPreferredSize().height+10));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+49,JPanel1.getPreferredSize().height+130));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+345,JLabel1.getPreferredSize().height+21));

  
          //<End_setUpProperties>

           JPanel2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Group To View")));
	JPanel3.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("View Configuration")));
           JPanel4.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Permission Tree Settings")));	
            
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
        setPreferredSize(new Dimension(getPreferredSize().width+547,getPreferredSize().height+542)); 
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
		 JPanel5.setBorder(new javax.swing.border.TitledBorder("Views and Operations"));
		advprop = this;
		
	
  } 

	
public void disableButtons()	
	{
		JButton3.setEnabled(false);
		JButton1.setEnabled(false);		
		JButton2.setEnabled(false);		
		
	}	

public void enableButtons()	
	{
		JButton3.setEnabled(true);
		JButton1.setEnabled(true);		
		JButton2.setEnabled(true);		
		
	}		  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      JButton3.addActionListener(JButton3_JButton3_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JLabel7= new javax.swing.JLabel();
        JButton3= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JButton1= new javax.swing.JButton();
        JPanel4= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JButton2= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        JLabel5= new javax.swing.JLabel();
        JLabel6= new javax.swing.JLabel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JLabel1,BorderLayout.NORTH);
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridLayout(3,1,5,5));
JPanel1.add(JPanel2);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,10);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(JLabel7,cons);
inset = new Insets(5,10,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(JButton3,cons);
JPanel1.add(JPanel3);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,10);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel3.add(JLabel2,cons);
inset = new Insets(5,10,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel3.add(JButton1,cons);
JPanel1.add(JPanel4);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,10);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel4.add(JLabel3,cons);
inset = new Insets(5,10,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel4.add(JButton2,cons);
Top.add(JPanel5,BorderLayout.SOUTH);
JPanel5.setLayout(new GridLayout(3,1,5,5));
JPanel5.add(JLabel4);
JPanel5.add(JLabel5);
JPanel5.add(JLabel6);

  
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

AbstractSecurityModel model = null;
	
public void setSecurityModel(com.adventnet.security.ui.AbstractSecurityModel model)
	{
		this.model = model;
	}	



   public void close()
	{
		
	  if(op != null )
		{
		     op.dispose();
		}
	 if(gva != null)
		{
		     gva.dispose();
		}
	 if(vcon != null)
		{
		    vcon.dispose();
		}
	}	
 public void showError(String err)
	{
		Utilities.errorMessage(resourceBundle.getString(err));
	}		    public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
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

 
//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  

 
 op = new Operations(owner, applet);
  op.init();
 op.setSecurityModel(model);  
 op.registerWithModel();   
op.fireDataChanged();      
   
disableButtons();   
   op.setVisible(true); 
   


  
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

 
 vcon  = new ViewConfig(owner,applet);
  vcon.setSecurityModel(model);   
  vcon.init();
  vcon.registerWithModel();  
 disableButtons();   
  vcon.setVisible(true); 
  
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>


//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 gva = new  ViewGroup(owner, applet);
 gva.setSecurityModel(model);   
 gva.init();
disableButtons();   
 gva.setVisible(true); 
 gva.fireDataChanged();  
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}











