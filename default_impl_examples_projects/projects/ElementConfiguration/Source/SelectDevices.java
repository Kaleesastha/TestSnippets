
//$Id: SelectDevices.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import java.util.*;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.adventnet.management.config.*;


public class SelectDevices extends JPanel implements ConfigResponseListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField nameText = null;
	javax.swing.JButton addButton = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JButton searchButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	ConfigPanel configPanel = null;
	AddDeviceList parent = null;
	String protocol = "";
	DeviceAttributesObject deviceAttributesObject = null;
	Vector attributesVector = null;
	Vector devicesVector = null;



	public SelectDevices()
  {
		//<Begin_SelectDevices>
    this.init();
  
    //<End_SelectDevices>
	}

	public SelectDevices(java.applet.Applet applet)
  {
		//<Begin_SelectDevices_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_SelectDevices_java.applet.Applet>
	}


	public SelectDevices(ConfigPanel configPanel,AddDeviceList parent,String protocol)
	{
		this.configPanel = configPanel;

		this.parent = parent;
		this.protocol = protocol;

		applet = configPanel.applet;

		init();
		configInit();
	}

	private void configInit()
	{
		if(configPanel.isStandAlone)
		{
			JPanel6.setVisible(false);
		}

		table.setDefaultRenderer(Object.class, new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class, null);

		Vector v = new Vector();
		
		v.addElement(" ");
		v.addElement(resourceBundle.getString("Device Names"));
		
		tableModel.setDataVector(new Vector(), v);
		
		TableColumn firstColumn = table.getColumnModel().getColumn(0);
		
		firstColumn.setMaxWidth(25);
		firstColumn.setCellRenderer(new SelectionFlag(configPanel));
		
	
		TableColumn lastColumn = table.getColumnModel().getColumn(1);
		
		ImageIcon listIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/devices.png");
		lastColumn.setCellRenderer(new CommonIconRenderer(listIcon));
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
        setPreferredSize(new Dimension(getPreferredSize().width+448,getPreferredSize().height+396)); 
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
            Top.setBackground(new Color(-3355393));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_Top>

//<UserCode_End_Bean_Top>

          try
          {
            JPanel5.setBorder(new javax.swing.border.SoftBevelBorder(0));
            JPanel5.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

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
            JPanel4.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JLabel3.setForeground(new Color(-16764109));
            JLabel3.setText(resourceBundle.getString("Enter Device Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            nameText.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nameText,ex); 
          }

//<UserCode_Begin_Bean_nameText>

//<UserCode_End_Bean_nameText>

          try
          {
            addButton.setText(resourceBundle.getString("Add"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addButton,ex); 
          }

//<UserCode_Begin_Bean_addButton>

//<UserCode_End_Bean_addButton>

          try
          {
            JPanel6.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            searchButton.setText(resourceBundle.getString("Search Device"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+searchButton,ex); 
          }

//<UserCode_Begin_Bean_searchButton>

//<UserCode_End_Bean_searchButton>

  
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
        JPanel5= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel4= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        nameText= new javax.swing.JTextField();
        addButton= new javax.swing.JButton();
        JPanel6= new javax.swing.JPanel();
        searchButton= new javax.swing.JButton();
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
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel5.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel4.add(JLabel3,cons);
inset = new Insets(0,10,0,10);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(nameText,cons);
inset = new Insets(0,0,0,0);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel4.add(addButton,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JPanel6,cons);
JPanel6.setLayout(new FlowLayout(2,5,5));
JPanel6.add(searchButton);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      JButton5_JButton5_conn1 JButton5_JButton5_conn11 =  new JButton5_JButton5_conn1();
      searchButton.addActionListener(JButton5_JButton5_conn11);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      JButton4_JButton4_conn2 JButton4_JButton4_conn21 =  new JButton4_JButton4_conn2();
      addButton.addActionListener(JButton4_JButton4_conn21);
      nameText_nameText_conn1 nameText_nameText_conn11 =  new nameText_nameText_conn1();
      nameText.addKeyListener(nameText_nameText_conn11);
  
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





	//<Begin__class_JButton5_JButton5_conn1>

 class JButton5_JButton5_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton5_JButton5_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  searchButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton5_JButton5_conn1>
 }//<End__class_JButton5_JButton5_conn1>
	//<Begin__class_JButton4_JButton4_conn2>

 class JButton4_JButton4_conn2 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton4_JButton4_conn2>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  addButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton4_JButton4_conn2>
 }//<End__class_JButton4_JButton4_conn2>


	public void addButtonActionPerformed()
	{
		String name = nameText.getText().trim();
		if(name.equals(""))
		{
			Toolkit.getDefaultToolkit().beep();
			return;
		}

		for(int i = 0;i<table.getRowCount();i++)
		{
			String str = table.getValueAt(i,1).toString();
			if (str.equals(name))
			{
				configPanel.configClientUtils.showErrorDialog(this, java.text.MessageFormat.format("Device Name {0} is already present in the list", new String[]{"" + name}), resourceBundle.getString("Info"), "info");

				nameText.selectAll();
				return;
			}
		}
		Vector v = new Vector();
		MultipleListSelectionObject object = new MultipleListSelectionObject("");
		object.setTrueState();
		v.addElement(object);
		v.addElement(name);
		tableModel.addRow(v);
		nameText.setText("");
		nameText.requestFocus();	
	}

	public void searchButtonActionPerformed()
	{
		SearchForBatchConfiguration.showSearchWindow(configPanel, configPanel.applet, this);
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
				Vector searchDevices = cre.getMatchedManagedObjects();
				if(searchDevices != null)
				{
					for(int i=0;i<searchDevices.size();i++)
					{
						boolean flag = false;
						String device = searchDevices.elementAt(i).toString();
						for(int j=0;j<table.getRowCount();j++)
						{
							if(table.getValueAt(j,1).toString().equals(device))
							{
								flag = true;
								break;	
							}
						}
						if(!flag)
						{
							Vector v = new Vector();
							MultipleListSelectionObject object = new MultipleListSelectionObject("");
							object.setTrueState();
							v.addElement(object);
							v.addElement(device);
							tableModel.addRow(v);	
						}
					}
				}
			}
		}	
	}



	//<Begin__class_table_table_conn1>

 class table_table_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_table_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  tableMousePressedEvent(arg0);
     }
//<UserCode_End_Connection_table_table_conn1>
 }//<End__class_table_table_conn1>

	public void tableMousePressedEvent(MouseEvent me)
	{
		int [] rows = table.getSelectedRows();
		for(int i=0;i<rows.length;i++)
		{
			if(rows[i] != -1)
			{
				MultipleListSelectionObject object = (MultipleListSelectionObject)table.getValueAt(rows[i],0);
				if(object.getState())
				{
					object.setFalseState();
				}
				else
				{
					object.setTrueState();
				}
			}
		}
		table.repaint();
	}

	public Vector getSelectedDevices()
	{
		if(table.getRowCount() == 0)
		{
			configPanel.configClientUtils.showErrorDialog(this,resourceBundle.getString("Please add some device(s)"),resourceBundle.getString("Info"), "info");
			return null;
		}

		boolean flag = false;
		Vector v = null;
		constructAllAttributes();
		for(int i=0;i<table.getRowCount();i++)
		{
			MultipleListSelectionObject object = (MultipleListSelectionObject)table.getValueAt(i,0);
			if(object.getState())
			{
				if(v == null)
					v = new Vector();
				flag = true;
				boolean alreadyAdded  = false;
				if(devicesVector != null)
				{
					String str = table.getValueAt(i,1).toString();
					if(devicesVector != null)
					{
						for(int j=0;j<devicesVector.size();j++)
						{
							Vector temp = (Vector)devicesVector.elementAt(j);
							String string = temp.elementAt(0).toString();
							if(string.equals(str))
							{
								v.addElement(devicesVector.elementAt(j));
								alreadyAdded = true;
							}
						}
					}
				}
				if(! alreadyAdded)
					v.addElement(new DeviceAttributesObject(table.getValueAt(i,1)));
			}
		}
		if(! flag)
		{
			configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Please select one or more devices"), resourceBundle.getString("Info"), "info");
			return null;	
		}
		else 
		{
			return v;
		}
	}

	public void constructAllAttributes()
	{
		Object[][] deviceAttributes = configPanel.configMainUI.getDeviceAttributes(protocol);

		deviceAttributesObject = new DeviceAttributesObject(deviceAttributes);

		attributesVector = configPanel.configMainUI.getDeviceAttributeNames(deviceAttributes);
	}

	public Vector getAttributeNames()
	{
		return attributesVector;
	}



	//<Begin__class_nameText_nameText_conn1>

 class nameText_nameText_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_nameText_nameText_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
  nameTextKeyReleasedEvent(arg0);
     }
//<UserCode_End_Connection_nameText_nameText_conn1>
 }//<End__class_nameText_nameText_conn1>

	public void nameTextKeyReleasedEvent(KeyEvent ke)
	{
		if(ke.getKeyCode() ==  KeyEvent.VK_ENTER)
		{
			addButtonActionPerformed();
		}
	}

	public void setExistingDevices(Vector devices)
	{
		if(devices != null)
		{
			this.devicesVector = devices;
			constructAllAttributes();
			for(int i=0;i<devices.size();i++)	
			{	
				Vector temp = (Vector)devices.elementAt(i);
				String name = temp.elementAt(0).toString();
				Vector v = new Vector();
				MultipleListSelectionObject object = new MultipleListSelectionObject(configPanel);
				object.setTrueState();	
				v.add(object);
				v.add(name);
				tableModel.addRow(v);
			}
		}
	}


	public Vector getExistingDevices()
	{
		return devicesVector;
	}


 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
