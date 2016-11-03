//$Id: ListAll.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory






package com.adventnet.nms.tools.CustomView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;




public class ListAll extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JTextArea listText = null;
	javax.swing.JTextArea descrText = null;
	//<End_Variable_Declarations>



  public ListAll()
  {
    //<Begin_ListAll>
    this.init();
  
    //<End_ListAll>
  }

  public ListAll(java.applet.Applet applet)
  {
    //<Begin_ListAll_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ListAll_java.applet.Applet>
  }


  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+823,getPreferredSize().height+502)); 
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
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),"CustomView Details",0,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+Top,ex); 
          }

          try
          {
            listText.setWrapStyleWord(true);
            listText.setBackground(new Color(-3355444));
            listText.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+listText,ex); 
          }

          try
          {
            descrText.setWrapStyleWord(true);
            descrText.setBackground(new Color(-3355444));
            descrText.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+descrText,ex); 
          }

  
          //<End_setUpProperties>

	descrText.setWrapStyleWord(true);
	descrText.setLineWrap(true);
	descrText.setFont(new Font("Dialog",1,15));
	descrText.setText("\nList of all the properties of the CustomView\n");
	descrText.setEditable(false);
	listText.setWrapStyleWord(true);
	listText.setLineWrap(true);
	listText.setFont(new Font("Dialog",0,15));
	listText.setText(" Module Name\n\n CustomView Name\n\n Properties\n\n Parent Node");
	listText.setEditable(false);
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
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        listText= new javax.swing.JTextArea();
        descrText= new javax.swing.JTextArea();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(listText,BorderLayout.CENTER);
Top.add(descrText,BorderLayout.NORTH);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
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

}









