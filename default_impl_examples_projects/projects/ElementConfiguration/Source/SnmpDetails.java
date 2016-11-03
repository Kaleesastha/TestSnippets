
//$Id: SnmpDetails.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SnmpDetails extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JTabbedPane JTabbedPane1 = null;
	com.adventnet.nms.config.CommonTaskDetails CommonTaskDetails1 = null;
	com.adventnet.nms.config.SnmpTableAttribDetails SnmpTableAttribDetails1 = null;
	//<End_Variable_Declarations>


  private ConfigPanel configPanel;
 private Vector attributesVector = null;
private Vector columnVector = null;
  
  



public SnmpDetails(ConfigPanel configPanel, Vector attribVector, Vector columnVector)
{
	this.configPanel = configPanel;
	this.columnVector = columnVector;
		
	attributesVector = configPanel.mibHandler.formatSNMPTaskAttributesForDisplay(attribVector);
	
	applet = configPanel.applet;
		
	init();
}   


  public SnmpDetails()
  {
    //<Begin_SnmpDetails>
    this.init();
  
    //<End_SnmpDetails>
  }

  public SnmpDetails(java.applet.Applet applet)
  {
    //<Begin_SnmpDetails_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_SnmpDetails_java.applet.Applet>
  }

 
    public void start()
  { 

  //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JTabbedPane1= new javax.swing.JTabbedPane();
        CommonTaskDetails1= new com.adventnet.nms.config.CommonTaskDetails(applet);
        SnmpTableAttribDetails1= new com.adventnet.nms.config.SnmpTableAttribDetails(applet);

  
        //<End_initVariables>

	CommonTaskDetails1 = new CommonTaskDetails(configPanel, (Vector)attributesVector.elementAt(0), columnVector);
	if(attributesVector.size() > 1)
	SnmpTableAttribDetails1 = new SnmpTableAttribDetails(configPanel, (Hashtable)attributesVector.elementAt(1));
	else
	SnmpTableAttribDetails1 = new SnmpTableAttribDetails();
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JTabbedPane1,BorderLayout.CENTER);
JTabbedPane1.addTab(resourceBundle.getString("Scalar Attributes"),null,CommonTaskDetails1,null);
JTabbedPane1.addTab(resourceBundle.getString("Table Attributes"),null,SnmpTableAttribDetails1,null);

  
//<End_setUpGUI_Container>
		
		JTabbedPane1.setTitleAt(0,resourceBundle.getString("Scalar Attributes"));
		JTabbedPane1.setTitleAt(1,resourceBundle.getString("Table Attributes"));
  } 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

  
  //<End_setUpProperties>
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 
  public void stop()
  { 

  //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
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
  public void init()
  { 

  //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+521,getPreferredSize().height+458)); 
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

	enableOrDisablePanel();
	
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

	private void enableOrDisablePanel()
	{
		Vector scalarVector = (Vector)attributesVector.elementAt(0);
		Hashtable tableHash = (Hashtable)attributesVector.elementAt(1);
		
		
		if(scalarVector == null || scalarVector.size() == 0)
		{
			JTabbedPane1.setEnabledAt(0, false);
		}
		if(tableHash == null || tableHash.size() == 0)
		{
			JTabbedPane1.setEnabledAt(1, false);
		}
		
		if(JTabbedPane1.isEnabledAt(0))
		{	
			JTabbedPane1.setSelectedIndex(0);
		}
		else
		{
			JTabbedPane1.setSelectedIndex(1);
		}	
	}


 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}


