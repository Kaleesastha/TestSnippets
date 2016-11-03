/* $Id: TL1MainScreen.java,v 1.1 2006/08/29 13:57:02 build Exp $ */
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//package com.adventnet.nms.studio.typecfgtr.tl1;
//<Begin_Version>
//version$2
//<End_Version>
package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TL1MainScreen extends JDialog 
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JButton JButton1 = null;
	com.adventnet.nms.runtimeconfig.TL1TypeCfgtrScreen TL1TypeCfgtrScreen1 = null;
	//<End_Variable_Declarations>

    public static boolean isTL1 = true;
    
    public TL1MainScreen()
  {
        //<Begin_TL1MainScreen>
    pack();
  
    //<End_TL1MainScreen>
    }
    public static boolean isClient = false;
    
    public TL1MainScreen(java.applet.Applet applet)
    {
        //<Begin_TL1MainScreen_java.applet.Applet>
        this.applet = applet;
        pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        //<End_TL1MainScreen_java.applet.Applet>
    }

    
    public TL1MainScreen(JFrame frame)
    {
        super(frame,true);
        pack();
    }

 
    public void init(java.applet.Applet app)
    {
        this.applet = app;
        
    }


    public void setVisible(boolean bl)
    {
      
      if(bl && TL1MainScreen.isTL1)
      {
          init();
          start();
      }
      else  
      {
          stop();
      }
      if(TL1MainScreen.isTL1)
      {
          super.setVisible(bl);
      }
      else
      {
          if(resourceBundle == null)
          {
              resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
          }
          String htmlformat = resourceBundle.getString("TL1 Component has been removed");
          JOptionPane.showMessageDialog(this,htmlformat,resourceBundle.getString("Warning:"),JOptionPane.WARNING_MESSAGE);
          super.setVisible(false);
      }
      if( true ) return; 
      // this is done decause we donot want the automatic coded provided 
      // below to be executed. we cannot also remove the following one since
      // it is a automatic generated one.
      
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


    public void init()
  {
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}


	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+721,getPreferredSize().height+462); 
          setTitle(resourceBundle.getString("TL1MainScreen"));
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
        setTitle(resourceBundle.getString("TL1 Properties"));
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
    public void setUpProperties()
  {
        //<Begin_setUpProperties> 

          try
          {
            JButton1.setFont(new Font("SansSerif",0,12));
            JButton1.setHorizontalTextPosition(4);
            JButton1.setText(resourceBundle.getString("Save"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+0,JButton1.getPreferredSize().height+1));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+616,JPanel1.getPreferredSize().height+0));

  
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
        JButton1= new javax.swing.JButton();
        TL1TypeCfgtrScreen1= new com.adventnet.nms.runtimeconfig.TL1TypeCfgtrScreen(applet);

  
        //<End_initVariables>
    } 
    public void setUpGUI(Container container)
  {
        //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JButton1);
Top.add(TL1TypeCfgtrScreen1,BorderLayout.CENTER);

  
//<End_setUpGUI_Container>
    } 
    public void setUpConnections()
  {
        //<Begin_setUpConnections> 

      JButton1_JButton1_conn JButton1_JButton1_conn1 =  new JButton1_JButton1_conn();
      JButton1.addActionListener(JButton1_JButton1_conn1);
  
      //<End_setUpConnections>
	this.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent we)
                {
                    JOptionPane jp = new JOptionPane();
                    int option = jp.showConfirmDialog(null, resourceBundle.getString("Do you want apply changes"),resourceBundle.getString("Confirmation"),jp.YES_NO_OPTION);
                    if (option == jp.YES_OPTION)
                    {
                        TL1TypeCfgtrScreen1.saveChanges();
                    }
                    //System.exit(0);
                }
            });
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

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

 


//<Begin__class_JButton1_JButton1_conn>

 class JButton1_JButton1_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn>

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
                                  TL1TypeCfgtrScreen1.saveChanges();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn>
 }//<End__class_JButton1_JButton1_conn>
}










