
//$Id: TemplateDetailsPanel.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class TemplateDetailsPanel extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField templateNameText = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField templateDescrText = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField protocolText = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>











	public TemplateDetailsPanel()
  {
		//<Begin_TemplateDetailsPanel>
    this.init();
  
    //<End_TemplateDetailsPanel>
	}

	public TemplateDetailsPanel(java.applet.Applet applet)
  {
		//<Begin_TemplateDetailsPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_TemplateDetailsPanel_java.applet.Applet>
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
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        templateNameText= new javax.swing.JTextField();
        JLabel2= new javax.swing.JLabel();
        templateDescrText= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        protocolText= new javax.swing.JTextField();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
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

	public void setUpGUI(Container container)
  { 

		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(templateNameText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(templateDescrText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(protocolText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);

  
//<End_setUpGUI_Container>
	} 
	public void setUpProperties()
  { 

		//<Begin_setUpProperties> 

          try
          {
            JLabel1.setText(resourceBundle.getString("Template Name"));
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            templateNameText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+templateNameText,ex); 
          }

//<UserCode_Begin_Bean_templateNameText>

//<UserCode_End_Bean_templateNameText>

          try
          {
            JLabel2.setText(resourceBundle.getString("Template Description"));
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            templateDescrText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+templateDescrText,ex); 
          }

//<UserCode_Begin_Bean_templateDescrText>

//<UserCode_End_Bean_templateDescrText>

          try
          {
            JLabel3.setText(resourceBundle.getString("Protocol"));
            JLabel3.setForeground(new Color(-16764109));
            JLabel3.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            protocolText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+protocolText,ex); 
          }

//<UserCode_Begin_Bean_protocolText>

//<UserCode_End_Bean_protocolText>

          try
          {
            table.setModel(tableModel);
            table.setRowHeight(21);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+651,getPreferredSize().height+466)); 
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

		Vector columnVector = new Vector();
		columnVector.add(resourceBundle.getString("Input Type"));
		columnVector.add(resourceBundle.getString("Place Holder"));
		Vector data = new Vector();
		tableModel.setDataVector(data,columnVector);
		table.setDefaultRenderer(Object.class,new TemplateDetailsRendererForTable());
		table.setDefaultEditor(Object.class,null);
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

	//<======================================================================================================================>

	private ConfigPanel configPanel = null;  

	public TemplateDetailsPanel(ConfigPanel configPanel, Vector templateDetails)
	{
		this.configPanel = configPanel;
		
		applet = configPanel.applet;

		init();
		configInit(templateDetails);
	}

	private void configInit(Vector templateDetails)
	{
		templateNameText.setText((String)templateDetails.elementAt(0));	
		templateDescrText.setText((String)templateDetails.elementAt(1));
		protocolText.setText((String)templateDetails.elementAt(2));

		templateNameText.setEnabled(false);
		templateNameText.setDisabledTextColor(Color.black);

		templateDescrText.setEnabled(false);
		templateDescrText.setDisabledTextColor(Color.black);

		protocolText.setEnabled(false);
		protocolText.setDisabledTextColor(Color.black);

		extractPlaceHolders((Vector)templateDetails.elementAt(3));
	}

	private void extractPlaceHolders(Vector placeHolders)
	{
		if(placeHolders != null)
		{
			Vector tableData = new Vector();

			for(int i=0; i<placeHolders.size(); i++)
			{
				Vector rowData = new Vector();

				StringTokenizer tokenizer = new StringTokenizer((String)placeHolders.elementAt(i), "$");

				while(tokenizer.hasMoreTokens())
				{
					rowData.addElement(tokenizer.nextToken());
				}

				tableData.addElement(rowData);
			}

			Vector columns = new Vector();

			columns.addElement(resourceBundle.getString("Input Type"));
			columns.addElement(resourceBundle.getString("Place Holder"));

			tableModel.setDataVector(tableData, columns);
		}
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}



