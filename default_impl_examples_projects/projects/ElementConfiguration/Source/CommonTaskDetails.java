
//$Id: CommonTaskDetails.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import  java.util.*;

public class CommonTaskDetails extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable JTable1 = null;
	javax.swing.table.DefaultTableModel DefaultTableModel1 = null;
	//<End_Variable_Declarations>


  	ConfigPanel configPanel = null;
	
	public CommonTaskDetails(ConfigPanel configPanel, Vector attributeDetails, Vector columnNames )
  	{
		this.configPanel = configPanel;
		
		applet = configPanel.applet;
		
		init();
		
		setUpValues(attributeDetails, columnNames);
	}



   



 
    public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            JTable1.setModel(DefaultTableModel1);
            JTable1.setRowHeight(21);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTable1,ex); 
          }

//<UserCode_Begin_Bean_JTable1>

//<UserCode_End_Bean_JTable1>

//<UserCode_Begin_Bean_DefaultTableModel1>
DefaultTableModel1.setDataVector(
             new Object[0][0],
            new Object[]{"OID","Label","Type","Value"});
//<UserCode_End_Bean_DefaultTableModel1>
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+10));

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+530,getPreferredSize().height+378)); 
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

	JTable1.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
	JTable1.setDefaultEditor(Object.class,null);
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
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new javax.swing.JTable();
        DefaultTableModel1= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTable1);

  
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; 
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



 
   
  


   


  public CommonTaskDetails()
  {
    //<Begin_CommonTaskDetails>
    this.init();
  
    //<End_CommonTaskDetails>
  }

  public CommonTaskDetails(java.applet.Applet applet)
  {
    //<Begin_CommonTaskDetails_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_CommonTaskDetails_java.applet.Applet>
  }
	
	private void setUpValues(Vector attributeDetails, Vector columnNames)
	{
		DefaultTableModel1.setDataVector(attributeDetails, columnNames);
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}


