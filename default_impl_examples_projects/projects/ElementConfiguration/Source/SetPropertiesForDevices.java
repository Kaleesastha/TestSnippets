
//$Id: SetPropertiesForDevices.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import javax.swing.table.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class SetPropertiesForDevices extends JPanel 
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
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;

	private String protocol = null;

	private Vector devices = null;

	private Vector deviceAttributeNames = null;

	private ViewFullAttribs viewAll = null;

	public SetPropertiesForDevices(ConfigPanel configPanel)
	{
		this.configPanel = configPanel;

		applet = configPanel.applet;

		init();
		configInit();
	}

	public SetPropertiesForDevices(ConfigPanel configPanel, String protocol)
	{
		this.configPanel = configPanel;

		applet = configPanel.applet;

		init();
		configInit();
		setProtocol(protocol);
	}


	public SetPropertiesForDevices()
  {
		//<Begin_SetPropertiesForDevices>
    this.init();
  
    //<End_SetPropertiesForDevices>
	}

	public SetPropertiesForDevices(java.applet.Applet applet)
  {
		//<Begin_SetPropertiesForDevices_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_SetPropertiesForDevices_java.applet.Applet>
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
        setPreferredSize(new Dimension(getPreferredSize().width+480,getPreferredSize().height+433)); 
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
	public void setUpProperties()
  {
		//<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.BevelBorder(0));
            Top.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_Top>

//<UserCode_End_Bean_Top>

          try
          {
            JPanel2.setBackground(new Color(-3355393));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JScrollPane1.setBackground(new Color(-3355393));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane1>

//<UserCode_End_Bean_JScrollPane1>

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
            JPanel3.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JButton1.setText(resourceBundle.getString("Edit Properties"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

  
          //<End_setUpProperties>
	} 
	public void start()
  {
		//<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
	} 
	public void stop()
  {
		//<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
	} 
	public void initVariables()
  {
		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel3= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
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
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(2,5,5));
JPanel3.add(JButton1);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      table_table_conn2 table_table_conn21 =  new table_table_conn2();
      table.addMouseListener(table_table_conn21);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseMotionListener(table_table_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
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

	public void updateTable()
	{
		int rowCount = tableModel.getRowCount();

		if(rowCount > 0)
		{
			for(int i=0; i<rowCount; i++)
			{
				tableModel.removeRow(0);
			}
		}

		if(devices != null)
		{
			int i = 0;
			for(i=0; i<devices.size(); i++)
			{
				Vector deviceAttributes = (Vector)devices.elementAt(i);

				Vector rowData = new Vector();

				rowData.addElement(deviceAttributes.elementAt(0));
				rowData.addElement(deviceAttributes.elementAt(1));
				rowData.addElement(deviceAttributes.elementAt(deviceAttributes.size()-2));	
				rowData.addElement(deviceAttributes.elementAt(deviceAttributes.size()-1));	

				tableModel.addRow(rowData);
			}
			table.setRowSelectionInterval(i-1, i-1);
			JButton1.setEnabled(true);
		}
	}

	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  propertiesButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

	public void propertiesButtonActionPerformed()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			Vector deviceAttributes = (Vector)devices.elementAt(row);

			UserPropertiesForDevices u = new UserPropertiesForDevices(configPanel, this, protocol);
			u.populateTable(deviceAttributes, deviceAttributeNames);
			u.setVisible(true);
		}
	}




	//<Begin__class_table_table_conn1>

 class table_table_conn1 extends java.awt.event.MouseMotionAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_table_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseMoved( java.awt.event.MouseEvent arg0)
     {
  tableMouseMoved(arg0);
     }
//<UserCode_End_Connection_table_table_conn1>
 }//<End__class_table_table_conn1>

	public void tableMouseMoved(MouseEvent me)
	{
		Point p = me.getPoint();
		int column = table.columnAtPoint(p);
		if(column == 4)
		{
			table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else
		{
			table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}




	//<Begin__class_table_table_conn2>

 class table_table_conn2 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_table_conn2>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  tableMousePressedEvent(arg0);
     }
//<UserCode_End_Connection_table_table_conn2>
 }//<End__class_table_table_conn2>


	public void tableMousePressedEvent(MouseEvent me)
	{
		Point p = me.getPoint();
		if(table.getSelectedRow() == -1)
		{
			JButton1.setEnabled(false);
		}
		else
		{
			JButton1.setEnabled(true);
		}
		int column = table.columnAtPoint(p);
		if(column == 4)
		{
			int row = table.rowAtPoint(p);
			if(row != -1)
			{
				Point p1 = table.getLocationOnScreen();
				Rectangle rec = table.getCellRect(0,0,true);
				int width = rec.width;
				int x = (int)p1.getX();
				x = x+width*4+20;
				int y = (int)p1.getY();
				y = y+row*21;
				Point point = new Point(x,y);
				/*Dimension dim = table.getSize();
				  int width = dim.height;
				  int x = (int)p1.getX();
				  x = row*20+x;
				  Point point = new Point(x,width);*/
				Vector deviceAttributes = (Vector)devices.elementAt(row);
				if(viewAll != null)
				{
					viewAll.setVisible(false);
				}
				viewAll = new ViewFullAttribs(configPanel, this, point, deviceAttributes, deviceAttributeNames);

				JPopupMenu jp = new JPopupMenu();
				JMenuItem it = new JMenuItem("Properties");
				it.setLayout(new BorderLayout());
				it.add(viewAll);
				it.setPreferredSize(viewAll.getPreferredSize());
				jp.add(it);
				jp.show(table,me.getX(),me.getY());
			}
		}
	}

	public void setProtocol(String newProtocol)
	{
		if(protocol != null)
		{
			if(!protocol.equals(newProtocol))
			{
				devices = null;
			}
		}

		protocol = newProtocol;

		deviceAttributeNames = configPanel.configMainUI.getDeviceAttributeNames(protocol);
	}

	public void setDevices(Vector newDevices)
	{
		if(devices != null && newDevices != null)
		{
			for(int i=0; i<newDevices.size(); i++)
			{
				Vector newDevice = (Vector)newDevices.elementAt(i);

				for(int j=0; j<devices.size(); j++)
				{
					Vector device = (Vector)devices.elementAt(j);	

					if(device.elementAt(0).equals(newDevice.elementAt(0)))
					{
						newDevices.setElementAt(device, i);
						break;
					}
				}
			}
		}

		devices = newDevices;

		updateTable();
	}

	public Vector getDevices()
	{
		return devices;
	}

	private void configInit()
	{
		Vector columns = new Vector();

		columns.addElement(resourceBundle.getString("Name"));
		columns.addElement(resourceBundle.getString("Port"));
		columns.addElement(resourceBundle.getString("Retries"));
		columns.addElement(resourceBundle.getString("Timeout"));
		columns.addElement(" ");

		tableModel.setDataVector(new Vector(), columns);

		TableColumn lastColumn = table.getColumnModel().getColumn(4);

		lastColumn.setMaxWidth(21);
		lastColumn.setCellRenderer(new ColumnRendererToViewAttributes(configPanel));

		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
















