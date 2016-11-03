
//$Id: DeviceListsForExecution.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import javax.swing.tree.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.table.*;

import com.adventnet.management.config.*;

public class DeviceListsForExecution extends JPanel implements ConfigResponseListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JTextArea2 = null;
	javax.swing.JCheckBox JCheckBox1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table1 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTree tree = null;
	javax.swing.table.DefaultTableModel tableModel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;
	private String taskName = null;
	DefaultTreeModel treeModel = null;
	DefaultMutableTreeNode rootNode = null;	
	String deviceListName = "";	


	public DeviceListsForExecution(ConfigPanel configPanel, String taskName)
	{	
		this.configPanel = configPanel;
		this.taskName = taskName;

		applet = configPanel.applet;

		init();
	}

	public DeviceListsForExecution(ConfigPanel configPanel)
	{
		this.configPanel = configPanel;

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
            JPanel3.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JTextArea2.setOpaque(false);
            JTextArea2.setText(resourceBundle.getString("Would you like to associate more devices for this task ?"));
            JTextArea2.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea2,ex); 
          }

//<UserCode_Begin_Bean_JTextArea2>

//<UserCode_End_Bean_JTextArea2>

          try
          {
            JPanel4.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            table1.setModel(tableModel1);
            table1.setRowHeight(20);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table1,ex); 
          }

//<UserCode_Begin_Bean_table1>

//<UserCode_End_Bean_table1>

          try
          {
            JPanel5.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+615,getPreferredSize().height+390)); 
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
		Vector row = new Vector();
		Vector column = new Vector();
		column.addElement(" ");
		column.addElement(resourceBundle.getString("Device Lists"));
		tableModel1.setDataVector(row,column);
		tree.setCellRenderer(new TreeRendererForDeviceLists(configPanel));
		rootNode = new DefaultMutableTreeNode(resourceBundle.getString("Details Of Selected Device List"));
		if(treeModel == null)	
		{
			treeModel = new DefaultTreeModel(rootNode);
			tree.setModel(treeModel);
		}		
		sendRequestDeviceList();
		table1.setShowHorizontalLines(true);
		TableColumn firstColumn = table1.getColumnModel().getColumn(0);
		firstColumn.setCellRenderer(new SelectionFlag(configPanel));
		firstColumn.setMaxWidth(25);
		TableColumn secondColumn = table1.getColumnModel().getColumn(1);
		secondColumn.setCellRenderer(new SimpleTableSelectionRenderer());
		table1.setDefaultEditor(Object.class,null);


	} 
	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      JTable1_JTable1_conn1 JTable1_JTable1_conn11 =  new JTable1_JTable1_conn1();
      table1.addMouseListener(JTable1_JTable1_conn11);
  
      //<End_setUpConnections>
	} 
	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JTextArea2= new javax.swing.JLabel();
        JCheckBox1= new javax.swing.JCheckBox();
        JPanel2= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table1= new javax.swing.JTable();
        JPanel5= new javax.swing.JPanel();
        JScrollPane2= new javax.swing.JScrollPane();
        tree= new javax.swing.JTree();
        tableModel1= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
	} 

	public void setUpGUI(Container container)
  { 

		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(0,20,5));
JPanel3.add(JTextArea2);
JPanel3.add(JCheckBox1);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridLayout(1,0,5,5));
JPanel2.add(JPanel4);
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(table1);
JPanel2.add(JPanel5);
JPanel5.setLayout(new BorderLayout(5,5));
JPanel5.add(JScrollPane2,BorderLayout.CENTER);
JScrollPane2.getViewport().add(tree);

  
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











	public DeviceListsForExecution()
  {
		//<Begin_DeviceListsForExecution>
    this.init();
  
    //<End_DeviceListsForExecution>
	}

	public DeviceListsForExecution(java.applet.Applet applet)
  {
		//<Begin_DeviceListsForExecution_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_DeviceListsForExecution_java.applet.Applet>
	}

	public boolean isExtraDevicesNeededForExecute()
	{
		return JCheckBox1.isSelected();
	}


	//<Begin__class_JTable1_JTable1_conn1>

 class JTable1_JTable1_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTable1_JTable1_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  table1MousePressedEvent(arg0);
     }
//<UserCode_End_Connection_JTable1_JTable1_conn1>
 }//<End__class_JTable1_JTable1_conn1>


	public void table1MousePressedEvent(MouseEvent me)
	{
		Point point = me.getPoint();
		int row1 = table1.rowAtPoint(point);
		deviceListName = table1.getValueAt(row1,1).toString();
		Object[] params = {table1.getValueAt(row1,1)};
		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DEVICELIST, params, this);

		if(table1.columnAtPoint(point) == 0)
		{
			int row = table1.rowAtPoint(point);

			MultipleListSelectionObject object = (MultipleListSelectionObject)table1.getValueAt(row,0);

			if(((MultipleListSelectionObject)object).getState())
			{
				((MultipleListSelectionObject)object).setFalseState();
			}
			else
			{
				((MultipleListSelectionObject)object).setTrueState();
			}		

			table1.repaint();
		}
	}

	private void sendRequestDeviceList()
	{
		Object[] params = {taskName};
		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DEVICELIST_FOR_TASK, params, this);
	}

	public Vector getSelectedDeviceList()
	{
		Vector v = new Vector();

		for(int i = 0; i < table1.getRowCount(); i++)
		{
			MultipleListSelectionObject object = (MultipleListSelectionObject)table1.getValueAt(i,0);
			if(object.getState())
			{
				v.addElement(table1.getValueAt(i, 1));
			}
		}
		return v;
	}

	public void response(ConfigResultEvent cre)
	{
		int workId = cre.getWorkID();
		int errorCode = cre.getErrorCode();
		String uniqueId = cre.getRequestID();

		if(workId == NmsConfigConstants.GET_DEVICELIST_FOR_TASK)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{

				Vector devicesVector = cre.getTaskSpecificDeviceListNames();
				for(int i = 0; i < devicesVector.size(); i++)
				{
					String dev = (String)devicesVector.elementAt(i);
					Vector v = new Vector();
					v.addElement(new MultipleListSelectionObject(new Object()));
					v.addElement(dev);

					tableModel1.addRow(v);
				}
			}
		}
		else if(workId == NmsConfigConstants.GET_DEVICELIST)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				Vector device = new Vector();
				String deviceList = cre.getDeviceList();
				DeviceListReader dlr = new DeviceListReader(deviceList);
				Vector devicesVector = dlr.getDevices();
				for(int i = 0; i < devicesVector.size(); i++)
				{
					device.add(((Vector)devicesVector.elementAt(i)).elementAt(0));
				} 

				constructTree(device);
			}
		}
	}	

	public void constructTree(Vector devicesVector)
	{
		if(!rootNode.isLeaf())
		{
			rootNode.removeAllChildren();
		}

		DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(deviceListName);
		rootNode.add(parentNode);
		TreePath path = null;
		for(int j = 0;j<devicesVector.size();j++)
		{
			DefaultMutableTreeNode device = new DefaultMutableTreeNode(devicesVector.elementAt(j));
			parentNode.add(device);
			if(path == null) path = new TreePath(treeModel.getPathToRoot(device));
		}

		treeModel.reload();
		tree.scrollPathToVisible(path);

	}


 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}




