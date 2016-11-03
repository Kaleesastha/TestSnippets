
//$Id: FinalScreenForExecution.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class FinalScreenForExecution extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable JTable1 = null;
	javax.swing.table.DefaultTableModel DefaultTableModel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;







	public FinalScreenForExecution(ConfigPanel panel)
	{	
		configPanel = panel;

		applet = configPanel.applet;

		init();
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
            JTable1.setModel(DefaultTableModel1);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTable1,ex); 
          }

//<UserCode_Begin_Bean_JTable1>

//<UserCode_End_Bean_JTable1>

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+566,getPreferredSize().height+392)); 
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
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new javax.swing.JTable();
        DefaultTableModel1= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
	} 

	public void setUpGUI(Container container)
  { 

		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JScrollPane1,BorderLayout.CENTER);
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











	public FinalScreenForExecution()
  {
		//<Begin_FinalScreenForExecution>
    this.init();
  
    //<End_FinalScreenForExecution>
	}

	public FinalScreenForExecution(java.applet.Applet applet)
  {
		//<Begin_FinalScreenForExecution_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_FinalScreenForExecution_java.applet.Applet>
	}
	public void setDeviceInfo(Vector devices)
	{
		setValuesForTable(devices);
	}

	private void setValuesForTable(Vector deviceInfo)
	{
		Vector columnName = new Vector();
		
		columnName.addElement(resourceBundle.getString("Device Name")); 
		columnName.addElement(resourceBundle.getString("Port")); 
		columnName.addElement(resourceBundle.getString("Retries")); 
		columnName.addElement(resourceBundle.getString("Timeout")); 

		int noOfAttributes = ((Vector)deviceInfo.elementAt(0)).size();
		if(noOfAttributes == 4)
		{
			DefaultTableModel1.setDataVector(deviceInfo, columnName);			
		}
		else if(noOfAttributes > 4)
		{
			Vector tempDevice = new Vector();
			for(int i = 0; i < deviceInfo.size(); i ++)
			{
				Vector temp = new Vector();
				Vector attributes = (Vector)deviceInfo.elementAt(i);
				for(int j = 0; j < attributes.size(); j++)
				{
					temp.addElement(attributes.elementAt(0));
					temp.addElement(attributes.elementAt(1));
					temp.addElement(attributes.elementAt(noOfAttributes -2));
					temp.addElement(attributes.elementAt(noOfAttributes-1));
				}
				tempDevice.addElement(temp);
			}
			DefaultTableModel1.setDataVector(tempDevice, columnName);
		}
	}


 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}



