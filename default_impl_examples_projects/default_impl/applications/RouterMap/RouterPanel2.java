
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.examples.routermap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.*;


public class RouterPanel2 extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JLabel  JLabel1 = null;
	javax.swing.JLabel  JLabel2 = null;
	//<End_Variable_Declarations>



  public RouterPanel2()
  {
    //<Begin_RouterPanel2>
    this.init();
  
    //<End_RouterPanel2>
  }

  public RouterPanel2(java.applet.Applet applet)
  {
    //<Begin_RouterPanel2_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_RouterPanel2_java.applet.Applet>
  }


  


 

  

    public void init()
  {
        //<Begin_init>
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+50,getPreferredSize().height+59)); 
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
      setUpMenus();
      setUpToolBar();
        

  //<End_init>
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
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  //<End_getParameter_String>
  } 
  public void setUpProperties()throws Exception
  {
  //<Begin_setUpProperties>

          try
          {
            Top.setBorder(new javax.swing.border.BevelBorder(1));
            Top.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+Top,ex); 
          }

          try
          {
            JLabel1.setIcon(NmsClientUtil.getImageIcon(applet.getDocumentBase() +"../images/interface_toppin.png"));
            JLabel1.setOpaque(true);
            JLabel1.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

          try
          {
            JLabel2.setBackground(new Color(-1));
            JLabel2.setText("192.168.1.45");
            JLabel2.setOpaque(true);
            JLabel2.setFont(new Font("Dialog",1,8));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel2,ex); 
          }

  //<End_setUpProperties>
  } 
  public void start()
  {
  //<Begin_start>

  //<End_start>
  } 
  public void stop()
  {
  //<Begin_stop>

  //<End_stop>
  } 
  public void initVariables()throws Exception
  {
        //<Begin_initVariables>
        Top= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();

  //<End_initVariables>
  } 
  public void setUpGUI(Container container)throws Exception
  {
//<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(null);
	JLabel1.setBounds(0,20,60,35);
	Top.add(JLabel1);
	JLabel2.setBounds(0,0,60,20);
	Top.add(JLabel2);
	
  //<End_setUpGUI_Container>
  } 
  public void setUpConnections()throws Exception
  {
  //<Begin_setUpConnections>

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

  public void setValues(Color status, String ipaddr)
	{
		JLabel1.setBackground(status);
		JLabel2.setText(ipaddr);
	}


 
  public void setUpToolBar()
  { 

  //<Begin_setUpToolBar>

  //<End_setUpToolBar>
  } 
  public void setUpMenus()
  { 

  //<Begin_setUpMenus>

  //<End_setUpMenus>
  }
}






