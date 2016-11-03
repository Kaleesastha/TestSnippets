
//$Id: NetworkInputPanel.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;

import com.adventnet.management.config.xml.*;

public class NetworkInputPanel extends JPanel implements ListSelectionListener 
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
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField placeHolderText = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JTextField valueText = null;
	javax.swing.JButton browseButton = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField defaultValueText = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton updateButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>









	public NetworkInputPanel()
  {
		//<Begin_NetworkInputPanel>
    this.init();
  
    //<End_NetworkInputPanel>
	}

	public NetworkInputPanel(java.applet.Applet applet)
  {
		//<Begin_NetworkInputPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_NetworkInputPanel_java.applet.Applet>
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
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel2= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        placeHolderText= new javax.swing.JTextField();
        JLabel2= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        valueText= new javax.swing.JTextField();
        browseButton= new javax.swing.JButton();
        JLabel3= new javax.swing.JLabel();
        defaultValueText= new javax.swing.JTextField();
        JPanel4= new javax.swing.JPanel();
        updateButton= new javax.swing.JButton();
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
inset = new Insets(5,5,0,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,0,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,0,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel1,cons);
inset = new Insets(5,5,0,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(placeHolderText,cons);
inset = new Insets(5,5,0,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel2,cons);
inset = new Insets(5,5,0,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,20);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(valueText,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel3.add(browseButton,cons);
inset = new Insets(5,5,0,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel3,cons);
inset = new Insets(5,5,0,5);
setConstraints(1,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(defaultValueText,cons);
inset = new Insets(5,5,0,5);
setConstraints(0,3,2,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(2,5,5));
JPanel4.add(updateButton);

  
//<End_setUpGUI_Container>
	} 
	public void setUpProperties()
  { 

		//<Begin_setUpProperties> 

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

          try
          {
            JPanel2.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JLabel1.setText(resourceBundle.getString("Place Holder"));
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
            placeHolderText.setEditable(false);
            placeHolderText.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+placeHolderText,ex); 
          }

//<UserCode_Begin_Bean_placeHolderText>

//<UserCode_End_Bean_placeHolderText>

          try
          {
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setText(resourceBundle.getString("Network Element"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            browseButton.setText(resourceBundle.getString("Browse"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+browseButton,ex); 
          }

//<UserCode_Begin_Bean_browseButton>

//<UserCode_End_Bean_browseButton>

          try
          {
            JLabel3.setText(resourceBundle.getString("Default Value"));
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
            updateButton.setText(resourceBundle.getString("Update"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+updateButton,ex); 
          }

//<UserCode_Begin_Bean_updateButton>

//<UserCode_End_Bean_updateButton>

  
          //<End_setUpProperties>
	} 
	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      updateButton_updateButton_conn1 updateButton_updateButton_conn11 =  new updateButton_updateButton_conn1();
      updateButton.addActionListener(updateButton_updateButton_conn11);
      JButton1_JButton1_conn2 JButton1_JButton1_conn21 =  new JButton1_JButton1_conn2();
      browseButton.addMouseListener(JButton1_JButton1_conn21);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      browseButton.addActionListener(JButton1_JButton1_conn11);
  
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
        setPreferredSize(new Dimension(getPreferredSize().width+597,getPreferredSize().height+438)); 
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

		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
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




	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  browseButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

	public void browseButtonActionPerformed()
	{
		SelectDeviceForNEInput selectDialog = new SelectDeviceForNEInput(configPanel, this, protocol);
		selectDialog.setVisible(true);
	}


	//<Begin__class_JButton1_JButton1_conn2>

 class JButton1_JButton1_conn2 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn2>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  browseButtonMousePressedEvent(arg0);
     }
//<UserCode_End_Connection_JButton1_JButton1_conn2>
 }//<End__class_JButton1_JButton1_conn2>

	public void browseButtonMousePressedEvent(MouseEvent me)
	{
	}




	//<Begin__class_updateButton_updateButton_conn1>

 class updateButton_updateButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_updateButton_updateButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  updateButtonActionPerformed();
     }
//<UserCode_End_Connection_updateButton_updateButton_conn1>
 }//<End__class_updateButton_updateButton_conn1>

	public void updateButtonActionPerformed()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			tableModel.setValueAt(valueText.getText(), row, 1);
			tableModel.setValueAt(defaultValueText.getText(), row, 2);
		}
	}

	private ConfigPanel configPanel = null;

	private String protocol = null;

	private	Vector columns = new Vector();

	private Hashtable attributesTable = null;
	private Hashtable deviceAttributesTable = new Hashtable();

	public NetworkInputPanel(ConfigPanel configPanel, Vector templateDetails)
	{
		this.configPanel = configPanel;

		applet = configPanel.applet;

		protocol = (String)templateDetails.elementAt(2);
		attributesTable = (Hashtable)templateDetails.elementAt(4);

		init();
		configInit((Vector)templateDetails.elementAt(3));
	}

	private void configInit(Vector placeHolders)
	{
		placeHolderText.setEditable(false);
		placeHolderText.setDisabledTextColor(Color.black);

		valueText.setEditable(false);
		valueText.setDisabledTextColor(Color.black);

		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);

		if(placeHolders != null)
		{
			Vector tableData = new Vector();

			for(int i=0; i<placeHolders.size(); i++)
			{
				String placeHolder = (String)placeHolders.elementAt(i); 

				if(placeHolder.indexOf("NEInput") != -1)
				{
					Vector rowData = new Vector();

					rowData.addElement(placeHolder.substring(placeHolder.lastIndexOf('$')+1));
					rowData.addElement("");					
					rowData.addElement("");

					tableData.addElement(rowData);
				}
			}


			columns.addElement(resourceBundle.getString("Place Holder"));
			columns.addElement(resourceBundle.getString("Network Element"));
			columns.addElement(resourceBundle.getString("Default Value"));

			tableModel.setDataVector(tableData, columns);
		}
	}

	public void valueChanged(ListSelectionEvent lse)
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			placeHolderText.setText((String)tableModel.getValueAt(row, 0));
			valueText.setText((String)tableModel.getValueAt(row, 1));
			defaultValueText.setText((String)tableModel.getValueAt(row, 2));
		}
	}

	public void setDevice(String deviceName, Vector deviceAttributes)
	{
		valueText.setText(deviceName);
		deviceAttributesTable.put(deviceName, deviceAttributes);
	}

	public Vector getAllValues()
	{
		Vector values = new Vector();

		int count = tableModel.getRowCount();

		for(int i=0; i<count; i++)
		{
			Vector value = new Vector();

			value.addElement(tableModel.getValueAt(i, 0));
			value.addElement(attributesTable.get(tableModel.getValueAt(i, 0)));
			value.addElement(deviceAttributesTable.get(tableModel.getValueAt(i, 1)));
			value.addElement(tableModel.getValueAt(i, 2));

			values.addElement(value);
		}

		return values;
	}

	public void setValues(Vector values)
	{
		if(values != null)
		{
			Vector tableData = new Vector();

			for(int i=0; i<values.size(); i++)
			{
				Vector value = (Vector)values.elementAt(i);

				Vector rowData = new Vector();

				String id = (String)value.elementAt(0);
				Attribute attrib = (Attribute)value.elementAt(1);

				Vector deviceAttributes = (Vector)value.elementAt(2);
				String deviceName = (String)deviceAttributes.elementAt(0);

				attributesTable.put(id, attrib);
				deviceAttributesTable.put(deviceName, deviceAttributes);

				rowData.addElement(id);
				rowData.addElement(deviceName);
				rowData.addElement(value.elementAt(3));

				tableData.addElement(rowData);
			}

			tableModel.setDataVector(tableData, columns);
		}
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}








