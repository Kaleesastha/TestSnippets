
//$Id: CombinedTaskExecute.java,v 1.1.4.1 2012/05/29 06:26:56 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;


import javax.swing.tree.*;
import javax.swing.event.*;
import java.net.URL;
import javax.swing.table.*;
import java.util.Vector;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.applet.*;
import com.adventnet.management.config.*;

public class CombinedTaskExecute extends JPanel implements ConfigResponseListener, TreeSelectionListener
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
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTree tree = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTable table = null;
	javax.swing.JButton headerButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;
	private String taskName = null;

	private Hashtable protocolVsAssocDLs = new Hashtable();	
	private Hashtable protocolVsSelecDLs = new Hashtable();

	private Hashtable taskVsAssocDLs = new Hashtable();
	private Hashtable taskVsSelecDLS = new Hashtable();

	private Hashtable uniqueIDVsSelection = new Hashtable();

	private DefaultTreeModel treeModel = null;

	private TreePath previousPath = null;

	public CombinedTaskExecute(ConfigPanel configPanel, String taskName)
	{
		this.configPanel = configPanel;
		this.taskName = taskName;

		applet = configPanel.applet;

		init();
		configInit();
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
		JPanel2= new javax.swing.JPanel();
		JPanel3= new javax.swing.JPanel();
		JScrollPane1= new javax.swing.JScrollPane();
		tree= new javax.swing.JTree();
		JPanel1= new javax.swing.JPanel();
		JScrollPane2= new javax.swing.JScrollPane();
		table= new javax.swing.JTable();
		headerButton= new javax.swing.JButton();
		tableModel= new javax.swing.table.DefaultTableModel();


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
		JPanel2.setLayout(new GridLayout(1,0,5,5));
		JPanel2.add(JPanel3);
		JPanel3.setLayout(new GridBagLayout());
		inset = new Insets(0,0,0,0);
		setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		JPanel3.add(JScrollPane1,cons);
		JScrollPane1.getViewport().add(tree);
		JPanel2.add(JPanel1);
		JPanel1.setLayout(new GridBagLayout());
		inset = new Insets(0,0,0,0);
		setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		JPanel1.add(JScrollPane2,cons);
		JScrollPane2.getViewport().add(table);
		inset = new Insets(0,0,0,0);
		setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		JPanel1.add(headerButton,cons);


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
			headerButton.setBackground(new Color(-16751002));
			headerButton.setText(resourceBundle.getString("Device Lists"));
			headerButton.setForeground(new Color(-1));
			headerButton.setFocusPainted(false);
			headerButton.setFont(new Font("Dialog",1,13));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+headerButton,ex); 
		}

		//<UserCode_Begin_Bean_headerButton>

		//<UserCode_End_Bean_headerButton>

		//<UserCode_Begin_Bean_tableModel>
		Vector v = new Vector();
		v.addElement(" ");
		v.addElement("Device Lists");
		tableModel.setDataVector(new Vector(),v);
		TableColumn firstColumn = table.getColumnModel().getColumn(0);
		firstColumn.setMaxWidth(25);
		//<UserCode_End_Bean_tableModel>


		//<End_setUpProperties>
	}

	public void setUpConnections()
	{ 

		//<Begin_setUpConnections> 

		tree_tree_conn1 tree_tree_conn11 =  new tree_tree_conn1();
		tree.addMouseListener(tree_tree_conn11);
		table_table_conn1 table_table_conn11 =  new table_table_conn1();
		table.addMouseListener(table_table_conn11);

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
		setPreferredSize(new Dimension(getPreferredSize().width+534,getPreferredSize().height+380)); 
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





	public CombinedTaskExecute()
	{
		//<Begin_CombinedTaskExecute>
		this.init();

		//<End_CombinedTaskExecute>
	}

	public CombinedTaskExecute(java.applet.Applet applet)
	{
		//<Begin_CombinedTaskExecute_java.applet.Applet>
		this.applet = applet;
		this.init();

		//<End_CombinedTaskExecute_java.applet.Applet>
	}

	private void configInit()
	{
		URL docBase = configPanel.applet.getDocumentBase();

		Vector columns = new Vector();

		columns.addElement(" ");
		columns.addElement(resourceBundle.getString("Device Lists"));

		tableModel.setDataVector(new Vector(), columns);

		TableColumn firstColumn = table.getColumnModel().getColumn(0);

		firstColumn.setCellRenderer(new SelectionFlag(configPanel));

		firstColumn.setMaxWidth(25);
		firstColumn.setMinWidth(25);	

		TableColumn secondColumn = table.getColumnModel().getColumn(1);

		secondColumn.setCellRenderer(new SimpleTableSelectionRenderer());

		JButton button = new JButton(resourceBundle.getString("Sub-Task List"));
		button.setFocusPainted(false);
		button.setBackground(new Color(0,102,102));
		button.setForeground(Color.white);
		button.setFont(new Font("dialog",Font.BOLD,13));
		JViewport viewPort = new JViewport();
		viewPort.setView(button);
		JScrollPane1.setColumnHeader(viewPort);

		tree.setShowsRootHandles(true);
		tree.setModel(treeModel);
		tree.setCellRenderer(new CombinedTaskRenderer(configPanel));
		//tree.setRootVisible(false);

		JTableHeader header = table.getTableHeader();
		//header.setPreferredSize(new Dimension((int)(JTable1.getPreferredSize()).width,22));
		//header.setMinimumSize(new Dimension((int)(JTable1.getMinimumSize()).width,22));		
		header.setPreferredSize(new Dimension(0,0));
		header.setMinimumSize(new Dimension(0,0));				
		header.setMaximumSize(new Dimension(0,0));					
		//JButton1.setBackground(new Color(0,102,102));
		//JButton1.setFont(new Font("dialog",Font.BOLD,13));		
		header.setVisible(false);

		table.setDefaultEditor(Object.class,null);

		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		constructTree();
	}




	//<Begin__class_tree_tree_conn1>

	class tree_tree_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_tree_tree_conn1>



		//Listener Interface Methods Are Added Below 


		public void mousePressed( java.awt.event.MouseEvent arg0)
		{
			treeMousePressedEvent(arg0);
		}
		//<UserCode_End_Connection_tree_tree_conn1>
	}//<End__class_tree_tree_conn1>

	public void treeMousePressedEvent(MouseEvent me)
	{
	}

	public void constructTree()
	{
		Vector subTaskNames = configPanel.configMainUI.getSubTaskNames(taskName);

		if(subTaskNames != null)
		{
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(new MultipleListSelectionObject(taskName));
			treeModel = new DefaultTreeModel(root);

			tree.setModel(treeModel);
			tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			tree.addTreeSelectionListener(this);

			//tree.setCellRenderer(new TreeRendererForCombinedTask(configPanel));
			tree.putClientProperty("JTree.lineStyle", "Angled");

			Vector snmpTasks = null;
			Vector telnetTasks = null;
			Vector ftpTasks = null;
			Vector tftpTasks = null;
			Vector tl1Tasks = null;
			Vector netconfTasks = null;

			for(int i=0; i<subTaskNames.size(); i++)
			{
				String task = (String)subTaskNames.elementAt(i);
				String protocol = configPanel.configMainUI.getProtocol(task);

				if(protocol.equalsIgnoreCase("snmp"))
				{
					if(snmpTasks == null)
					{
						snmpTasks = new Vector();
					}

					snmpTasks.addElement(task);
				}
				else if(protocol.equalsIgnoreCase("telnet"))
				{
					if(telnetTasks == null)
					{
						telnetTasks = new Vector();
					}

					telnetTasks.addElement(task);
				}
				else if(protocol.equalsIgnoreCase("tftp"))
				{
					if(tftpTasks == null)	
					{
						tftpTasks = new Vector();
					}

					tftpTasks.addElement(task);
				}
				else if(protocol.equalsIgnoreCase("ftp"))
				{
					if(ftpTasks == null)	
					{
						ftpTasks = new Vector();
					}

					ftpTasks.addElement(task);
				}
				else if(protocol.equalsIgnoreCase("tl1"))
				{
					if(tl1Tasks == null)	
					{
						tl1Tasks = new Vector();
					}

					tl1Tasks.addElement(task);
				}
				else if(protocol.equalsIgnoreCase("netconf"))
				{
					if(netconfTasks == null)	
					{
						netconfTasks = new Vector();
					}

					netconfTasks.addElement(task);
				}
			}

			if(snmpTasks != null)
			{
				DefaultMutableTreeNode snmpNode = new DefaultMutableTreeNode(new MultipleListSelectionObject("SNMP"));

				for(int i=0; i<snmpTasks.size(); i++)	
				{
					String str = snmpTasks.elementAt(i).toString();
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(new MultipleListSelectionObject(str,true));
					snmpNode.add(child);
				}

				root.add(snmpNode);
			}

			if(telnetTasks != null)
			{
				DefaultMutableTreeNode telnetNode = new DefaultMutableTreeNode(new MultipleListSelectionObject("Telnet"));

				for(int i=0; i<telnetTasks.size(); i++)	
				{
					String str = telnetTasks.elementAt(i).toString();					
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(new MultipleListSelectionObject(str,true));
					telnetNode.add(child);
				}

				root.add(telnetNode);
			}

			if(ftpTasks != null)
			{
				DefaultMutableTreeNode ftpNode = new DefaultMutableTreeNode(new MultipleListSelectionObject("FTP"));

				for(int i=0; i<ftpTasks.size(); i++)	
				{
					String str = ftpTasks.elementAt(i).toString();					
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(new MultipleListSelectionObject(str,true));
					ftpNode.add(child);
				}

				root.add(ftpNode);
			}

			if(tftpTasks != null)
			{
				DefaultMutableTreeNode tftpNode = new DefaultMutableTreeNode(new MultipleListSelectionObject("TFTP"));

				for(int i=0; i<tftpTasks.size(); i++)	
				{
					String str = tftpTasks.elementAt(i).toString();					
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(new MultipleListSelectionObject(str,true));
					tftpNode.add(child);
				}

				root.add(tftpNode);
			}

			if(tl1Tasks != null)
			{
				DefaultMutableTreeNode tl1Node = new DefaultMutableTreeNode(new MultipleListSelectionObject("TL1"));

				for(int i=0; i<tl1Tasks.size(); i++)	
				{
					String str = tl1Tasks.elementAt(i).toString();					
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(new MultipleListSelectionObject(str,true));
					tl1Node.add(child);
				}

				root.add(tl1Node);
			}
			
			if(netconfTasks != null)
			{
				DefaultMutableTreeNode netconfNode = new DefaultMutableTreeNode(new MultipleListSelectionObject("NETCONF"));

				for(int i=0; i<netconfTasks.size(); i++)	
				{
					String str = netconfTasks.elementAt(i).toString();					
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(new MultipleListSelectionObject(str,true));
					netconfNode.add(child);
				}

				root.add(netconfNode);
			}

			treeModel.reload();

			for(int i=0; i<root.getChildCount(); i++)
			{
				tree.expandPath(new TreePath(((DefaultMutableTreeNode)root.getChildAt(i)).getPath()));
			}
		}
	}

	public Hashtable getSelectedDeviceListForSubTasks()
	{
		Vector subTaskNames = configPanel.configMainUI.getSubTaskNames(taskName);

		Hashtable taskVsDLs = new Hashtable();

		if(subTaskNames != null)
		{
			updateSelectedDLs();

			for(int i=0; i<subTaskNames.size(); i++)	
			{
				String task = (String)subTaskNames.elementAt(i);

				String protocol = configPanel.configMainUI.getProtocol(task);

				Vector deviceLists = new Vector();

				Vector taskLevelDLs = (Vector)taskVsSelecDLS.get(task);
				Vector protocolLevelDLs = (Vector)protocolVsSelecDLs.get(protocol.toLowerCase());

				if(protocolLevelDLs != null)
				{
					for(int j=0; j<protocolLevelDLs.size(); j++)
					{
						deviceLists.addElement(protocolLevelDLs.elementAt(j));
					}
				}

				if(taskLevelDLs != null)
				{
					for(int j=0; j<taskLevelDLs.size(); j++)
					{
						if(!deviceLists.contains(taskLevelDLs.elementAt(j)))
						{
							deviceLists.addElement(taskLevelDLs.elementAt(j));
						}
					}
				}

				taskVsDLs.put(task, deviceLists);
			}
		}

		return taskVsDLs;
	}

	public void response(ConfigResultEvent  cre)
	{
		int workId = cre.getWorkID();
		int errorCode = cre.getErrorCode();

		String uniqueId = cre.getRequestID();

		if(workId == NmsConfigConstants.GET_DEVICELIST_FOR_TASK)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				Vector deviceLists = cre.getTaskSpecificDeviceListNames();

				if(deviceLists != null)
				{
					taskVsAssocDLs.put(uniqueIDVsSelection.remove(uniqueId), deviceLists);
				}

			}
			else
			{
				System.out.println(cre.getErrorString());
			}

			refreshDeviceListTable();
		}
		else if(workId == NmsConfigConstants.GET_PROTOCOL_SPECIFIC_DEVICELIST_NAMES)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				Vector deviceLists = cre.getProtocolSpecificDeviceListNames();

				if(deviceLists != null)
				{
					protocolVsAssocDLs.put(uniqueIDVsSelection.remove(uniqueId), deviceLists);
				}

			}
			else
			{
				System.out.println(cre.getErrorString());
			}

			refreshDeviceListTable();
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
		int row = table.getSelectedRow();

		if(row != -1)
		{
			TreePath path = tree.getSelectionPath();

			String selectedDeviceList = (String)tableModel.getValueAt(row, 1);

			Vector protocolLevelSelectedDLs = null;

			if(path != null && path.getPathCount() == 3)
			{
				protocolLevelSelectedDLs = (Vector)protocolVsSelecDLs.get(path.getPathComponent(1).toString().toLowerCase());
			}

			MultipleListSelectionObject object = (MultipleListSelectionObject)table.getValueAt(row,0);

			if(((MultipleListSelectionObject)object).getState())
			{
				if(protocolLevelSelectedDLs == null || !protocolLevelSelectedDLs.contains(selectedDeviceList))
				{			
					((MultipleListSelectionObject)object).setFalseState();
				}
			}
			else
			{
				((MultipleListSelectionObject)object).setTrueState();
			}		

			table.repaint();
		}
	}

	private void refreshDeviceListTable()
	{
		TreePath path = tree.getSelectionPath();

		if(path != null)
		{
			int pathCount = path.getPathCount();

			String selection = path.getLastPathComponent().toString();

			Vector deviceLists = null;
			Vector selectedDeviceLists = null;

			Vector protocolLevelSelectedDLs = null;

			if(pathCount == 2)
			{
				deviceLists = (Vector)protocolVsAssocDLs.get(selection.toLowerCase());
				selectedDeviceLists = (Vector)protocolVsSelecDLs.get(selection.toLowerCase());
			}
			else if(pathCount == 3)
			{
				deviceLists = (Vector)taskVsAssocDLs.get(selection);
				selectedDeviceLists = (Vector)taskVsSelecDLS.get(selection);

				protocolLevelSelectedDLs = (Vector)protocolVsSelecDLs.get(path.getPathComponent(1).toString().toLowerCase());
			}

			int rowCount = tableModel.getRowCount();

			if(rowCount > 0)
			{
				for(int i=0; i<rowCount; i++)
				{
					tableModel.removeRow(0);
				}
			}

			if(deviceLists != null)
			{
				for(int i=0; i<deviceLists.size(); i++)
				{
					Vector rowData = new Vector();

					MultipleListSelectionObject selectionObject = new MultipleListSelectionObject(new Object());
					String deviceList = (String)deviceLists.elementAt(i);

					if((selectedDeviceLists != null && selectedDeviceLists.contains(deviceList)) || (protocolLevelSelectedDLs != null && protocolLevelSelectedDLs.contains(deviceList)))
					{
						selectionObject.setTrueState();
					}

					rowData.addElement(selectionObject);
					rowData.addElement(deviceList);

					tableModel.addRow(rowData);
				}
			}
		}
	}

	private Vector getSelectedDLs()
	{
		int rowCount = tableModel.getRowCount();

		Vector selectedDLs = null;

		if(rowCount > 0)
		{
			selectedDLs = new Vector();

			for(int i=0; i<rowCount; i++)
			{
				if(((MultipleListSelectionObject)tableModel.getValueAt(i, 0)).getState())
				{
					selectedDLs.addElement(tableModel.getValueAt(i, 1));
				}
			}
		}

		return selectedDLs;
	}

	private void updateSelectedDLs()
	{
		if(previousPath != null)
		{
			Vector selectedDLs = getSelectedDLs();

			if(selectedDLs != null)
			{
				int previousPathCount = previousPath.getPathCount();

				String previousSelection = previousPath.getLastPathComponent().toString();

				if(previousPathCount == 2)
				{
					protocolVsSelecDLs.put(previousSelection.toLowerCase(), selectedDLs);		
				}
				else if(previousPathCount == 3)
				{
					taskVsSelecDLS.put(previousSelection, selectedDLs);
				}
			}
		}
	}

	public void valueChanged(TreeSelectionEvent tse)
	{
		TreePath path = tree.getSelectionPath();

		if(path != null)
		{
			int pathCount = path.getPathCount();			

			String selection = path.getLastPathComponent().toString();

			updateSelectedDLs();

			previousPath = path;

			if(pathCount == 2 && protocolVsAssocDLs.get(selection.toLowerCase()) == null)
			{
				Object[] params = {selection.toLowerCase()};
				String uniqueID = configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_PROTOCOL_SPECIFIC_DEVICELIST_NAMES, params, this);
				uniqueIDVsSelection.put(uniqueID, selection.toLowerCase());
				
				headerButton.setText(java.text.MessageFormat.format(resourceBundle.getString("Device Lists for {0} Protocol"), new String[]{"" + selection.toUpperCase()}));
			
			}
			else if(pathCount == 3 && taskVsAssocDLs.get(selection) == null)
			{
				Object[] params = {selection};
				String uniqueID = configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DEVICELIST_FOR_TASK, params, this);
				uniqueIDVsSelection.put(uniqueID, selection);
				headerButton.setText(resourceBundle.getString("Device Lists for Task :")+selection);				
			}
			else
			{
				headerButton.setText(resourceBundle.getString("Device Lists"));
			}

			refreshDeviceListTable();

		}
	}


	public void setProperties(java.util.Properties props)
	{
		//<Begin_setProperties_java.util.Properties> 


		//<End_setProperties_java.util.Properties>
	}
}
