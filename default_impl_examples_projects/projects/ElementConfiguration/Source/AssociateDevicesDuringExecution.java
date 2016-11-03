
//$Id: AssociateDevicesDuringExecution.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;


import java.net.URL;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import com.adventnet.management.config.*;

public class AssociateDevicesDuringExecution extends JPanel implements ConfigResponseListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable attributesTable = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel valueLabel = null;
	javax.swing.JTextField propertyText = null;
	javax.swing.JTextField valueText = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JButton updateButton = null;
	javax.swing.JLabel versionLabel = null;
	javax.swing.JComboBox versionCombo = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JPanel JPanel9 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JButton searchButton = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTable devicesTable = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	javax.swing.table.DefaultTableModel customModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;
	String taskName = "";
	String protocol = "";
	boolean isSnmp = false;
	boolean isVersion3 = false;
	Vector attributesVector = null;
	DeviceAttributesObject attributesObject = null;
	Vector devicesVector = null;
	String deviceListName = "";
	private DeviceListOperation deviceListOperation;
	private boolean modify = false;



	public AssociateDevicesDuringExecution(ConfigPanel panel, String taskName)
	{
		configPanel = panel;

		this.taskName = taskName;

		applet = configPanel.applet;

		init();
		configInit();
	}

	public AssociateDevicesDuringExecution(ConfigPanel panel)
	{
		configPanel = panel;

		applet = configPanel.applet;

		init();
		configInit();
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
            JPanel2.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JPanel4.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Device Attributes"),0,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            attributesTable.setModel(tableModel);
            attributesTable.setRowHeight(21);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+attributesTable,ex); 
          }

//<UserCode_Begin_Bean_attributesTable>

//<UserCode_End_Bean_attributesTable>

          try
          {
            JPanel5.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            JLabel2.setText(resourceBundle.getString("Property"));
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
            valueLabel.setText(resourceBundle.getString("Value"));
            valueLabel.setForeground(new Color(-16764109));
            valueLabel.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+valueLabel,ex); 
          }

//<UserCode_Begin_Bean_valueLabel>

//<UserCode_End_Bean_valueLabel>

          try
          {
            propertyText.setEditable(false);
            propertyText.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+propertyText,ex); 
          }

//<UserCode_Begin_Bean_propertyText>

//<UserCode_End_Bean_propertyText>

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

          try
          {
            versionLabel.setText(resourceBundle.getString("Value"));
            versionLabel.setForeground(new Color(-16764109));
            versionLabel.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+versionLabel,ex); 
          }

//<UserCode_Begin_Bean_versionLabel>

//<UserCode_End_Bean_versionLabel>

          try
          {
            JPanel7.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel7,ex); 
          }

//<UserCode_Begin_Bean_JPanel7>

//<UserCode_End_Bean_JPanel7>

          try
          {
            JLabel4.setText(resourceBundle.getString("Search for Devices"));
            JLabel4.setForeground(new Color(-16764109));
            JLabel4.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            searchButton.setText(resourceBundle.getString("Search"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+searchButton,ex); 
          }

//<UserCode_Begin_Bean_searchButton>

//<UserCode_End_Bean_searchButton>

          try
          {
            devicesTable.setModel(customModel);
            devicesTable.setRowHeight(21);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+devicesTable,ex); 
          }

//<UserCode_Begin_Bean_devicesTable>

//<UserCode_End_Bean_devicesTable>

          try
          {
            JLabel5.setText(resourceBundle.getString("List Of Devices"));
            JLabel5.setForeground(new Color(-16764109));
            JLabel5.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Property","Value"});


//<UserCode_End_Bean_tableModel>

  
          //<End_setUpProperties>
		Vector row = new Vector();
		Vector column = new Vector();
		column.addElement(" ");
		column.addElement(resourceBundle.getString("Devices"));
		customModel.setDataVector(row,column); 
		JPanel4.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Device Attributes"),0,0,new Font("Dialog",0,12),new Color(-16764109)));
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
        setPreferredSize(new Dimension(getPreferredSize().width+600,getPreferredSize().height+390)); 
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

	} 
	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      table_JPanel5_conn1 table_JPanel5_conn11 =  new table_JPanel5_conn1();
      attributesTable.addMouseListener(table_JPanel5_conn11);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      searchButton.addActionListener(JButton2_JButton2_conn11);
      JTable1_JTable1_conn1 JTable1_JTable1_conn11 =  new JTable1_JTable1_conn1();
      devicesTable.addMouseListener(JTable1_JTable1_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      updateButton.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
	} 
	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        attributesTable= new javax.swing.JTable();
        JPanel5= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        valueLabel= new javax.swing.JLabel();
        propertyText= new javax.swing.JTextField();
        valueText= new javax.swing.JTextField();
        JPanel6= new javax.swing.JPanel();
        updateButton= new javax.swing.JButton();
        versionLabel= new javax.swing.JLabel();
        versionCombo= new javax.swing.JComboBox();
        JPanel7= new javax.swing.JPanel();
        JPanel8= new javax.swing.JPanel();
        JPanel9= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        searchButton= new javax.swing.JButton();
        JScrollPane2= new javax.swing.JScrollPane();
        devicesTable= new javax.swing.JTable();
        JLabel5= new javax.swing.JLabel();
        tableModel= new javax.swing.table.DefaultTableModel();
        customModel= new javax.swing.table.DefaultTableModel();

  
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
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel4.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(attributesTable);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(valueLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(propertyText,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(valueText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,2,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JPanel6,cons);
JPanel6.setLayout(new FlowLayout(2,5,5));
JPanel6.add(updateButton);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(versionLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(versionCombo,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel2.add(JPanel7,cons);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(0,0,10,0);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel7.add(JPanel8,cons);
JPanel8.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel8.add(JPanel9,cons);
JPanel9.setLayout(new FlowLayout(1,5,5));
JPanel9.add(JLabel4);
JPanel9.add(searchButton);
inset = new Insets(0,0,10,0);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel8.add(JScrollPane2,cons);
JScrollPane2.getViewport().add(devicesTable);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel8.add(JLabel5,cons);

  
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
















	public AssociateDevicesDuringExecution()
  {
		//<Begin_AssociateDevicesDuringExecution>
    this.init();
  
    //<End_AssociateDevicesDuringExecution>
	}

	public AssociateDevicesDuringExecution(java.applet.Applet applet)
  {
		//<Begin_AssociateDevicesDuringExecution_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_AssociateDevicesDuringExecution_java.applet.Applet>
	}


	//<Begin__class_table_JPanel5_conn1>

 class table_JPanel5_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_JPanel5_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  attributesTableMousePressedEvent();
     }
//<UserCode_End_Connection_table_JPanel5_conn1>
 }//<End__class_table_JPanel5_conn1>
	//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  searchButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>
	//<Begin__class_JTable1_JTable1_conn1>

 class JTable1_JTable1_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTable1_JTable1_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  devicesTableMousePressedEvent(arg0);
     }
//<UserCode_End_Connection_JTable1_JTable1_conn1>
 }//<End__class_JTable1_JTable1_conn1>
	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  updateButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

	private void configInit()
	{
		protocol = configPanel.configMainUI.getProtocol(taskName);

		if(protocol.equalsIgnoreCase("snmp")) isSnmp = true;

		devicesTable.setShowHorizontalLines(true);

		attributesTable.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		attributesTable.setDefaultEditor(Object.class,null);

		if(isSnmp)
		{
			versionCombo.addItem("v1");
			versionCombo.addItem("v2c");
			versionCombo.addItem("v3");
		}

		versionCombo.setVisible(false);

		versionLabel.setVisible(false);

		TableColumn firstColumn = devicesTable.getColumnModel().getColumn(0);
		firstColumn.setCellRenderer(new SelectionFlag(configPanel));
		firstColumn.setMaxWidth(25);

		TableColumn secondColumn = devicesTable.getColumnModel().getColumn(1);
		secondColumn.setCellRenderer(new SimpleTableSelectionRenderer());		
		devicesTable.setDefaultEditor(Object.class,null);
	}


	private void searchButtonActionPerformed()
	{
		SearchForBatchConfiguration.showSearchWindow(configPanel, configPanel.applet, this);
	}

	private void updateButtonActionPerformed()
	{
		int row = attributesTable.getSelectedRow();
		if(row != -1)
		{
			if(isSnmp)
			{
				row = attributesTable.getSelectedRow();
				if(row != -1)
				{
					String value = "";
					if(row == 3)
					{
						String temp = versionCombo.getSelectedItem().toString();
						if(temp.indexOf("3") == -1)
							isVersion3 = false;
						else
							isVersion3 = true;
						value = temp;
					}
					else
					{
						value = valueText.getText().trim();
					}
					attributesTable.setValueAt(value,row,1);
					if(row != attributesTable.getRowCount()-1)
					{
						//giveNewSelection(row+1);
					}
					//else
					//giveNewSelection(0);
					if(devicesTable.getSelectedRow() != -1)
					{
						DeviceAttributesObject deviceData = (DeviceAttributesObject)devicesVector.elementAt(devicesTable.getSelectedRow());
						for(int i=0;i<attributesTable.getRowCount();i++)                               
						{
							deviceData.setElementAt(attributesTable.getValueAt(i,1),i);
						}
					}	

				}
			}

			else
			{
				row = attributesTable.getSelectedRow();
				if(row != -1)
				{
					String value = valueText.getText();
					attributesTable.setValueAt(value,row,1);
				}
				if(devicesTable.getSelectedRow() != -1)
				{
					DeviceAttributesObject deviceData = (DeviceAttributesObject)devicesVector.elementAt(devicesTable.getSelectedRow());
					for(int i=0;i<attributesTable.getRowCount();i++)
					{
						deviceData.setElementAt(attributesTable.getValueAt(i,1),i);
					}
				}		
			}
		}		
	}

	private void devicesTableMousePressedEvent(MouseEvent me)
	{
		Point point = me.getPoint();
		DeviceAttributesObject deviceData = (DeviceAttributesObject)devicesVector.elementAt(devicesTable.getSelectedRow());
		Vector dataVector = new Vector();
		for(int i=0;i<attributesVector.size();i++)
		{
			Vector v = new Vector();
			v.addElement(attributesVector.elementAt(i));
			v.addElement(deviceData.elementAt(i));
			dataVector.addElement(v);
		}
		Vector columnVector = new Vector();
		columnVector.addElement(resourceBundle.getString("Property"));
		columnVector.addElement(resourceBundle.getString("Value"));
		tableModel.setDataVector(dataVector,columnVector);

		if(devicesTable.columnAtPoint(point) == 0)
		{
			int row = devicesTable.rowAtPoint(point);
			MultipleListSelectionObject object = (MultipleListSelectionObject)devicesTable.getValueAt(row,0);

			if(((MultipleListSelectionObject)object).getState())
			{
				((MultipleListSelectionObject)object).setFalseState();
			}
			else
			{
				((MultipleListSelectionObject)object).setTrueState();
			}		

			devicesTable.repaint();
		}

	}

	private void attributesTableMousePressedEvent()
	{
		int row = attributesTable.getSelectedRow();
		if(row != -1)
			propertyText.setText(attributesTable.getValueAt(row,0).toString());
		if(isSnmp)
		{
			if(row == 0)
				valueText.setEditable(false);
			else
				valueText.setEditable(true);
			if(row == 3)
			{
				versionLabel.setVisible(true);
				versionCombo.setVisible(true);
				valueLabel.setVisible(false);
				valueText.setVisible(false);
			}
			else
			{
				versionLabel.setVisible(false);
				versionCombo.setVisible(false);
				valueLabel.setVisible(true);
				valueText.setVisible(true);
			}
			if(row == 4 || row == 5 || row == 6 || row == 7)
			{
				if(isVersion3)
				{
					valueText.setEditable(true);
					valueText.setText(attributesTable.getValueAt(row,1).toString());
				}
				else
				{
					valueText.setEditable(false);
					valueText.setText("");
				}
			}
			else
			{
				valueText.setText(attributesTable.getValueAt(row,1).toString());
			}
		}
		else
		{
			//int row = table.getSelectedRow();
			if(row == 0)
				valueText.setEditable(false);
			else
				valueText.setEditable(true);
			String value = attributesTable.getValueAt(row,1).toString();	
			valueText.setText(attributesTable.getValueAt(row,1).toString());		
		}

	}

	public void constructAttributesVector()
	{
		Object[][] deviceAttributes = configPanel.configMainUI.getDeviceAttributes(protocol);

		tableModel.setDataVector(deviceAttributes, configPanel.configMainUI.columnObject);

		attributesObject = new DeviceAttributesObject(deviceAttributes);

		attributesVector = configPanel.configMainUI.getDeviceAttributeNames(deviceAttributes);
	}

	public Vector getSelectedDevices()
	{
		Vector v = new Vector();

		for(int i = 0; i < devicesTable.getRowCount(); i++)
		{
			MultipleListSelectionObject object = (MultipleListSelectionObject)devicesTable.getValueAt(i,0);
			if(object.getState())
			{
				DeviceAttributesObject obj = (DeviceAttributesObject)devicesVector.elementAt(i);
				v.addElement(obj);

			}
		}
		return v;

	}


	public void response(ConfigResultEvent cre)
	{
		int workId = cre.getWorkID();
		int errorCode = cre.getErrorCode();
		String uniqueId = cre.getRequestID();

		if(workId == NmsConfigConstants.GET_MO_WITH_CRITERIA)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				Vector networkDetails = cre.getMatchedManagedObjects();
				devicesVector = new Vector();
				constructAttributesVector();
				//selectedDevices = networkDetails;

				int count = customModel.getRowCount();
				if(count !=0)
				{
					for(int j=count-1;j >=0;j--)
					{
						customModel.removeRow(j);
					}
				}
				for(int i=0; i < networkDetails.size(); i++)
				{
					MultipleListSelectionObject object = new MultipleListSelectionObject(networkDetails.elementAt(i));
					Vector v = new Vector();
					v.addElement(new MultipleListSelectionObject(new Object()));
					v.addElement(object);
					customModel.addRow(v);
					devicesVector.addElement(new DeviceAttributesObject(networkDetails.elementAt(i)));

				}
			}
		}
		else if(workId == NmsConfigConstants.GET_DEVICELIST)
		{
			devicesVector = new Vector();
			constructAttributesVector();
			Vector device = new Vector();
			String deviceList = cre.getDeviceList();
			DeviceListReader dlr = new DeviceListReader(deviceList);
			Vector devices = dlr.getDevices();
			for(int i = 0; i < devices.size(); i++)
			{
				Vector temp = (Vector)devices.elementAt(i);
				MultipleListSelectionObject object = new MultipleListSelectionObject(temp.elementAt(0));
				Vector v = new Vector();
				MultipleListSelectionObject mso = new MultipleListSelectionObject(new Object());
				mso.setTrueState();
				v.addElement(mso);
				v.addElement(object);
				customModel.addRow(v);
				attributesObject = new DeviceAttributesObject(temp);
				devicesVector.addElement(new DeviceAttributesObject(temp.elementAt(0)));
			}
		}



	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
