
//$Id: SnmpTableAttribDetails.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import java.util.*;

public class SnmpTableAttribDetails extends JPanel implements ListSelectionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JSplitPane JSplitPane1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable JTable1 = null;
	javax.swing.JList JList1 = null;
	javax.swing.table.DefaultTableModel DefaultTableModel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

private ConfigPanel configPanel;
private Hashtable tempHash;

public SnmpTableAttribDetails(ConfigPanel configPanel,  Hashtable tableHash)
{
	this.configPanel = configPanel;	
		
	applet = configPanel.applet;	
		
	init();
		
	setValuesForList(tableHash);
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
            JScrollPane1.setHorizontalScrollBarPolicy(32);
            JScrollPane1.setVerticalScrollBarPolicy(22);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane1>

//<UserCode_End_Bean_JScrollPane1>

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
            new Object[]{"OID","Type","Value"});
//<UserCode_End_Bean_DefaultTableModel1>
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+266,JPanel1.getPreferredSize().height+137));

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+613,getPreferredSize().height+468)); 
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
	setUpListener();
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
        JSplitPane1= new javax.swing.JSplitPane();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new javax.swing.JTable();
        JList1= new javax.swing.JList();
        DefaultTableModel1= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JSplitPane1,cons);
JSplitPane1.setRightComponent(JScrollPane1);
JScrollPane1.getViewport().add(JTable1);
JSplitPane1.setLeftComponent(JList1);
JSplitPane1.setDividerLocation(100);

  
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



 
   
  


	private void setValuesForList(Hashtable tableHash)
	{
		Vector listElement = new Vector();
		tempHash = tableHash;
		for(Enumeration enumerate = tempHash.keys(); enumerate.hasMoreElements();)
		{
			String key = (String)enumerate.nextElement();
			listElement.addElement(key);
			//Vector value = (Vector)tempHash.get(key);
		
		}
		JList1.setListData(listElement);
		
		if(listElement == null || listElement.size() >  0)
		{
			JList1.setSelectedIndex(0);	
		}
	}

	private void setValuesForTable(String tableName)
	{
		Vector tempVector = (Vector)tempHash.get(tableName);
		DefaultTableModel1.setDataVector((Vector)tempVector.elementAt(1), (Vector)tempVector.elementAt(0));

	}
   
public void valueChanged(ListSelectionEvent le)
{
	String tableName =(String)JList1.getSelectedValue(); //le.getSource().toString();
	setValuesForTable(tableName);
}


  public SnmpTableAttribDetails()
  {
    //<Begin_SnmpTableAttribDetails>
    this.init();
  
    //<End_SnmpTableAttribDetails>
  }

  public SnmpTableAttribDetails(java.applet.Applet applet)
  {
    //<Begin_SnmpTableAttribDetails_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_SnmpTableAttribDetails_java.applet.Applet>
  }

	private void setUpListener()
	{
		JList1.addListSelectionListener(this);
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}


