
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

/*$Id: UninstallPathPanel.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $*/

 
//<Begin_Version>
//version$9
//<End_Version>

package com.adventnet.studio.framework.installer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.adventnet.builder.utils.guilogic.Browse;



public class UninstallPathPanel extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel appLabel = null;
	javax.swing.JTextField appField = null;
	javax.swing.JLabel uninstLocLabel = null;
	javax.swing.JTextField uninstLocField = null;
	javax.swing.JButton uninstLocButton = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	Browse uninstPathBrowse;

    String getApplicationNameVersion()
    {
	return appField.getText();
    }

    String getUninstallLocation()
    {
	return uninstLocField.getText();
    }

  public UninstallPathPanel()
  {
    //<Begin_UninstallPathPanel>
    this.init();
  
    //<End_UninstallPathPanel>
  }

  public UninstallPathPanel(java.applet.Applet applet)
  {
    //<Begin_UninstallPathPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_UninstallPathPanel_java.applet.Applet>
  }

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+647,getPreferredSize().height+456)); 
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
        // let us set the initialized variable to true so  
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
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  } 
   
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            appLabel.setHorizontalAlignment(2);
            appLabel.setFont(new Font("SansSerif",0,12));
            appLabel.setForeground(new Color(-16777216));
            appLabel.setHorizontalTextPosition(4);
            appLabel.setText("Enter Application Name");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+appLabel,ex); 
          }

//<UserCode_Begin_Bean_appLabel>

//<UserCode_End_Bean_appLabel>

          try
          {
            appField.setHorizontalAlignment(2);
            appField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+appField,ex); 
          }

//<UserCode_Begin_Bean_appField>

//<UserCode_End_Bean_appField>

          try
          {
            uninstLocLabel.setHorizontalAlignment(2);
            uninstLocLabel.setFont(new Font("SansSerif",0,12));
            uninstLocLabel.setForeground(new Color(-16777216));
            uninstLocLabel.setHorizontalTextPosition(4);
            uninstLocLabel.setText("Select Uninstall Location");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+uninstLocLabel,ex); 
          }

//<UserCode_Begin_Bean_uninstLocLabel>

//<UserCode_End_Bean_uninstLocLabel>

          try
          {
            uninstLocField.setHorizontalAlignment(2);
            uninstLocField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+uninstLocField,ex); 
          }

//<UserCode_Begin_Bean_uninstLocField>

//<UserCode_End_Bean_uninstLocField>

          try
          {
            uninstLocButton.setFont(new Font("SansSerif",0,12));
            uninstLocButton.setHorizontalTextPosition(4);
            uninstLocButton.setText("Browse");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+uninstLocButton,ex); 
          }

//<UserCode_Begin_Bean_uninstLocButton>

//<UserCode_End_Bean_uninstLocButton>

  
          //<End_setUpProperties>

	uninstPathBrowse.setBrowseBttn(uninstLocButton);
	uninstPathBrowse.setFileTxtFld(uninstLocField);
	uninstPathBrowse.setTitle("Select Uninstall Location");
	uninstPathBrowse.setSelectionMode(JFileChooser.DIRECTORIES_ONLY);
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        appLabel= new javax.swing.JLabel();
        appField= new javax.swing.JTextField();
        uninstLocLabel= new javax.swing.JLabel();
        uninstLocField= new javax.swing.JTextField();
        uninstLocButton= new javax.swing.JButton();

  
        //<End_initVariables>
	uninstPathBrowse = new Browse("Uninstall Location");
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,0,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(appLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(appField,cons);
inset = new Insets(5,5,0,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(uninstLocLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(uninstLocField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(uninstLocButton,cons);

  
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
}








