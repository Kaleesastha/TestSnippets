
//$Id: ViewName.java,v 1.1 2006/08/29 13:57:02 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.CommonBuilderUIInterface;


public class ViewName extends JPanel 
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
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField viewText = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel7 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


  
  
  














   


  public ViewName()
  {
    //<Begin_ViewName>
    this.init();
  
    //<End_ViewName>
  }

  public ViewName(java.applet.Applet applet)
  {
    //<Begin_ViewName_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ViewName_java.applet.Applet>
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
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setIconTextGap(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel2.setText(resourceBundle.getString("Give a view name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

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
            JLabel3.setForeground(new Color(-16764109));
            JLabel3.setFont(new Font("Dialog",0,13));
            JLabel3.setText(resourceBundle.getString("Enter View Name  *"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel6.setFont(new Font("Dialog",0,12));
            JLabel6.setForeground(new Color(-16764109));
            JLabel6.setText(resourceBundle.getString("    This wizard helps you to add a View , add"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

          try
          {
            JLabel4.setFont(new Font("Dialog",0,12));
            JLabel4.setForeground(new Color(-16764109));
            JLabel4.setText(resourceBundle.getString("    Properties for it and associate operations"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JLabel7.setFont(new Font("Dialog",0,12));
            JLabel7.setForeground(new Color(-16764109));
            JLabel7.setText(resourceBundle.getString("    Click on next to continue ."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel7,ex); 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+11,JPanel3.getPreferredSize().height+10));
		JLabel2.setPreferredSize(new Dimension(JLabel2.getPreferredSize().width+77,JLabel2.getPreferredSize().height+2));

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+507,getPreferredSize().height+360)); 
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
		
		JLabel1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("addview.png"));
		viewText.requestFocus();
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
        JLabel1= new javax.swing.JLabel();
        JPanel1= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        viewText= new javax.swing.JTextField();
        JPanel4= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JLabel6= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        JLabel7= new javax.swing.JLabel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.2,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JLabel1,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.8,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JLabel2,BorderLayout.NORTH);
JPanel1.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,10,0,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JLabel3,cons);
inset = new Insets(0,5,0,10);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(viewText,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(1,5,5));
inset = new Insets(0,0,0,0);
setConstraints(1,1,1,1,0.1,0.1,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(JPanel5,cons);
JPanel5.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JPanel3,BorderLayout.SOUTH);
JPanel3.setLayout(new GridLayout(4,1,5,5));
JPanel3.add(JLabel6);
JPanel3.add(JLabel4);
JPanel3.add(JLabel7);

  
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


 public void setViewName(String vname)
	{
		viewText.setText(vname);
		viewText.setEditable(false);
	}

public String getViewName()
	{
		return viewText.getText();
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}


