
//$Id: ViewTaskHistory.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import com.adventnet.management.config.*;
import java.util.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class ViewTaskHistory extends JDialog implements ConfigResponseListener,HelpInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JButton helpButton = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JSplitPane JSplitPane1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTree tree = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JPanel JPanel21 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField nameText = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JTextField lastExecText = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


	private ConfigPanel configPanel = null;

	private DefaultTreeModel treeModel = null;

	private Hashtable deviceListVsDeviceAudits = new Hashtable();

	private Hashtable uniqueIDVsDL = new Hashtable();

	private String taskName = null;

	private String executionID = null;

	private HelpDialog helpDialog = null;


	public ViewTaskHistory(ConfigPanel configPanel, String taskName)
	{
		super(configPanel.configClientUtils.getParentFrame(configPanel));
		
		this.configPanel = configPanel;
		this.taskName = taskName;

		applet = configPanel.applet;
		
		init();
		configInit();
	}

	public ViewTaskHistory()
  {
		//<Begin_ViewTaskHistory>
    pack();
  
    //<End_ViewTaskHistory>
	}

	public ViewTaskHistory(java.applet.Applet applet)
  {
		//<Begin_ViewTaskHistory_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ViewTaskHistory_java.applet.Applet>
	}







	public void setVisible(boolean bl)
  {
		//<Begin_setVisible_boolean>
       	if(bl)
       	{
       	  init();
          start();
        }
        else
        {
          stop();
        }
        super.setVisible(bl);
  
                 //<End_setVisible_boolean>
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
        this.setSize(getPreferredSize().width+664,getPreferredSize().height+467); 
          setTitle(resourceBundle.getString("ViewTaskHistory"));
        Container container = getContentPane();
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


          setTitle(resourceBundle.getString("Task History"));
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
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JLabel1.setText(resourceBundle.getString("Task History"));
            JLabel1.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            helpButton.setBackground(new Color(-1));
            helpButton.setFocusPainted(false);
            helpButton.setPreferredSize(new Dimension(35,23));
            helpButton.setMinimumSize(new Dimension(35,23));
            helpButton.setMaximumSize(new Dimension(35,23));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+helpButton,ex); 
          }

//<UserCode_Begin_Bean_helpButton>

//<UserCode_End_Bean_helpButton>

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
            JPanel4.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            closeButton.setBackground(new Color(-16764109));
            closeButton.setFocusPainted(false);
            closeButton.setText(resourceBundle.getString("Close"));
            closeButton.setOpaque(false);
            closeButton.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>

//<UserCode_End_Bean_closeButton>

          try
          {
            JPanel21.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel21,ex); 
          }

//<UserCode_Begin_Bean_JPanel21>

//<UserCode_End_Bean_JPanel21>

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
            JLabel3.setText(resourceBundle.getString("Task Name"));
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
            nameText.setEditable(false);
            nameText.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nameText,ex); 
          }

//<UserCode_Begin_Bean_nameText>

//<UserCode_End_Bean_nameText>

          try
          {
            JLabel4.setText(resourceBundle.getString("Last Executed Time"));
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
            lastExecText.setOpaque(false);
            lastExecText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lastExecText,ex); 
          }

//<UserCode_Begin_Bean_lastExecText>

//<UserCode_End_Bean_lastExecText>

  
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
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel6= new javax.swing.JPanel();
        helpButton= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JSplitPane1= new javax.swing.JSplitPane();
        JScrollPane1= new javax.swing.JScrollPane();
        tree= new javax.swing.JTree();
        JScrollPane2= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel4= new javax.swing.JPanel();
        closeButton= new javax.swing.JButton();
        JPanel21= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JPanel5= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        nameText= new javax.swing.JTextField();
        JLabel4= new javax.swing.JLabel();
        lastExecText= new javax.swing.JTextField();
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
inset = new Insets(5,10,5,20);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(JLabel1,cons);
inset = new Insets(5,5,5,20);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel6.add(helpButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JSplitPane1,cons);
JSplitPane1.setLeftComponent(JScrollPane1);
JSplitPane1.setDividerLocation(194);
JScrollPane1.getViewport().add(tree);
JSplitPane1.setRightComponent(JScrollPane2);
JScrollPane2.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(2,5,5));
JPanel4.add(closeButton);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel21,cons);
JPanel21.setLayout(new GridBagLayout());
inset = new Insets(5,20,5,15);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.NONE,inset,0,0);
JPanel21.add(JLabel2,cons);
inset = new Insets(5,15,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel21.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(nameText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(lastExecText,cons);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      helpButton_helpButton_conn1 helpButton_helpButton_conn11 =  new helpButton_helpButton_conn1();
      helpButton.addActionListener(helpButton_helpButton_conn11);
      tree_tree_conn1 tree_tree_conn11 =  new tree_tree_conn1();
      tree.addTreeSelectionListener(tree_tree_conn11);
      closeButton_closeButton_conn1 closeButton_closeButton_conn11 =  new closeButton_closeButton_conn1();
      closeButton.addActionListener(closeButton_closeButton_conn11);
  
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


	public void response(ConfigResultEvent cre)
	{
		int workId = cre.getWorkID();
		int errorCode = cre.getErrorCode();

		String uniqueId = cre.getRequestID();

		if(workId == NmsConfigConstants.GET_TASK_AUDIT)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				constructTree(cre.getTaskAudit());
			}
			else
			{
				System.out.println(cre.getErrorString());
			}
		}	
		else if(workId == NmsConfigConstants.GET_DEVICE_AUDIT_FOR_DEVICELIST)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				String deviceList = (String)uniqueIDVsDL.remove(uniqueId);

				if(deviceList != null)
				{
					Vector deviceAudits = cre.getDeviceAudit();

					if(deviceAudits != null)
					{
						deviceListVsDeviceAudits.put(deviceList, deviceAudits);	
					}

					if(deviceList.equalsIgnoreCase("Associated Devices"))
					{
						addAssociatedDevicesNode();
					}
					else
					{
						refreshTable();
					}
				}
			}
			else 
			{
				System.out.println(cre.getErrorString());
			}
		}
	}


	//<Begin__class_tree_tree_conn1>

 class tree_tree_conn1 implements javax.swing.event.TreeSelectionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_tree_tree_conn1>



     //Listener Interface Methods Are Added Below 


     public void valueChanged( javax.swing.event.TreeSelectionEvent arg0)
     {
  treeSelectionListener(arg0);
     }
//<UserCode_End_Connection_tree_tree_conn1>
 }//<End__class_tree_tree_conn1>


	public void treeSelectionListener(TreeSelectionEvent tse)
	{
		TreePath path = tse.getPath();

		if(path != null)
		{
			String selection = path.getLastPathComponent().toString();

			if(deviceListVsDeviceAudits.get(selection) == null && path.getPathCount() == 2)
			{
				Object [] params = {executionID, selection};
				String uniqueID = configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DEVICE_AUDIT_FOR_DEVICELIST, params, this);
				uniqueIDVsDL.put(uniqueID, selection);
			}
			else
			{
				refreshTable();
			}
		}

	}

	private void configInit()
	{
		configPanel.configClientUtils.centerWindow(this);
		nameText.setText(taskName);

		lastExecText.setText(configPanel.configMainUI.getLastExecutedTime(taskName));

		table.addMouseListener(new MyMouseListener());
		table.setDefaultEditor(Object.class, null);
		//table.setDefaultRenderer(Object.class, new TableRendererForTaskHistory(configPanel));

		tree.setModel(treeModel);
		tree.putClientProperty("JTree.lineStyle", "Angled");
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		Vector columns = new Vector();

		if(configPanel.configMainUI.isCombined(taskName))
		{
			columns.addElement(resourceBundle.getString("SubTask Name"));
		}


		columns.addElement(resourceBundle.getString("Device Name"));
		columns.addElement(resourceBundle.getString("Start Time"));
		columns.addElement(resourceBundle.getString("Finish Time"));

		String rollbackDocument = configPanel.configMainUI.getRollbackDocument(taskName);

		//if(rollbackDocument != null && !rollbackDocument.equals(""))
		//{
		columns.addElement(resourceBundle.getString("Rollback Status"));
		//}

		columns.addElement(resourceBundle.getString("Task Status"));

		tableModel.setDataVector(new Vector(), columns);

		//statusColumn.setCellRenderer(new ColumnRendererForHistoryStatus(configPanel));

		Object[] params = {taskName};

		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_TASK_AUDIT, params, this);

		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		if( !configPanel.configMainUI.isCombined(taskName))
		{
			TableColumn statusColumn = table.getColumnModel().getColumn(4);
			statusColumn.setCellRenderer(new ColumnRendererForHistoryStatus(configPanel));
		}

		else
		{
			TableColumn statusColumn = table.getColumnModel().getColumn(5);
			statusColumn.setCellRenderer(new ColumnRendererForHistoryStatus(configPanel));
		}

		table.setDefaultEditor(Object.class,null);
		JLabel2.setIcon(configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/taskhistory.png"));		
		tree.setCellRenderer(new TreeRendererForTaskHistory(configPanel, configPanel.configMainUI.getProtocol(taskName)));
		tree.setRowHeight(22);
		helpButton.setIcon(configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+ 
					"../images/contextualhelp.png"));		
	}

	private void constructTree(TaskAudit taskAudit)
	{
		if(taskAudit != null)
		{
			String[] deviceLists = taskAudit.getDeviceList();

			DefaultMutableTreeNode root = new DefaultMutableTreeNode(taskName);

			treeModel = new DefaultTreeModel(root);

			tree.setModel(treeModel);

			if(deviceLists != null)
			{
				for(int i=0; i<deviceLists.length; i++)
				{
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(deviceLists[i]);

					root.add(child);
				}

				treeModel.reload();
			}

			tree.expandPath(new TreePath(root.getPath()));

			executionID = String.valueOf(taskAudit.getExecutionID());

			Object [] params = {executionID, NmsConfigConstants.ASSOCIATED_DEVICES};
			String uniqueID = configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DEVICE_AUDIT_FOR_DEVICELIST, params, this);

			uniqueIDVsDL.put(uniqueID, "Associated Devices");
		}
	}

	private void addAssociatedDevicesNode()
	{
		if(deviceListVsDeviceAudits.get("Associated Devices") != null)		
		{
			DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();

			DefaultMutableTreeNode associatedDevicesNode = new DefaultMutableTreeNode("Associated Devices");

			root.add(associatedDevicesNode);

			treeModel.reload();

			tree.expandPath(new TreePath(root.getPath()));
		}
	}

	private void refreshTable()
	{
		TreePath path = tree.getSelectionPath();

		if(path != null)
		{
			String selection = path.getLastPathComponent().toString();

			int rowCount = tableModel.getRowCount();

			if(rowCount > 0)
			{
				for(int i=0; i<rowCount; i++)
				{
					tableModel.removeRow(0);
				}
			}

			Vector deviceAudits = (Vector)deviceListVsDeviceAudits.get(selection);

			if(deviceAudits != null)
			{
				boolean isCombined = false;
				boolean isRollbackNeeded = false;


				if(configPanel.configMainUI.isCombined(taskName))
				{
					isCombined = true;
				}

				/*String rollbackDocument = configPanel.configMainUI.getRollbackDocument(taskName);
				//System.out.println("Rollback Document................"+rollbackDocument);

				if(rollbackDocument != null && !rollbackDocument.equals(""))
				{
				isRollbackNeeded = true;
				}*/

				for(int i=0; i<deviceAudits.size(); i++)
				{
					DeviceAudit deviceAudit = (DeviceAudit)deviceAudits.elementAt(i);	

					Vector rowData = new Vector();

					if(isCombined)
					{
						rowData.addElement(deviceAudit.getSubTaskName());
					}

					rowData.addElement(deviceAudit.getDeviceName());
					rowData.addElement(configPanel.configClientUtils.formatDate(deviceAudit.getTimeOfStart()));
					rowData.addElement(configPanel.configClientUtils.formatDate(deviceAudit.getTimeOfFinish()));

					if(deviceAudit.getRollbackStatus() != null)
						rowData.addElement(deviceAudit.getRollbackStatus());
					else
						rowData.addElement("No Rollback");


					rowData.addElement(deviceAudit.getStatus());
					tableModel.addRow(rowData);
				}
			}
		}
	}

	class MyMouseListener extends java.awt.event.MouseAdapter implements java.io.Serializable 
	{
		public void mousePressed( java.awt.event.MouseEvent me)
		{
			if(me.getClickCount() == 2)
			{
				int row = table.rowAtPoint(me.getPoint());

				if(row != -1)
				{
					TreePath path = tree.getSelectionPath();

					if(path != null)
					{
						String deviceList = path.getLastPathComponent().toString();

						Vector deviceAudits = (Vector)deviceListVsDeviceAudits.get(deviceList);

						if(deviceAudits != null)
						{
							DeviceAudit deviceAudit = (DeviceAudit)deviceAudits.elementAt(row);

							AdvancedTaskHistory his = new AdvancedTaskHistory(configPanel, ViewTaskHistory.this, deviceAudit);
							his.setVisible(true);

						}
					}
				}
			}
		}
	}



	//<Begin__class_closeButton_closeButton_conn1>

 class closeButton_closeButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_closeButton_closeButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  doneButtonActionPerformed();
     }
//<UserCode_End_Connection_closeButton_closeButton_conn1>
 }//<End__class_closeButton_closeButton_conn1>

	public void doneButtonActionPerformed()
	{
		this.setVisible(false);
		this.dispose();
	}




	//<Begin__class_helpButton_helpButton_conn1>

 class helpButton_helpButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_helpButton_helpButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
    contextSensitiveHelpActionPerformed();
     }
//<UserCode_End_Connection_helpButton_helpButton_conn1>
 }//<End__class_helpButton_helpButton_conn1>

	public void contextSensitiveHelpActionPerformed()
	{
		if(helpDialog == null)
		{
			helpDialog = new HelpDialog(configPanel, this);
		}
		else
		{
			if(!helpDialog.isVisible())
			{
				configPanel.configClientUtils.centerWindow(helpDialog);
			}
		}

		helpDialog.setVisible(true);
	}

	public String  getHelpFor(String key)
	{
		String helpContent = "";

		if(key.equals("Task Audit Details"))
		{
			helpContent = "\t" + resourceBundle.getString("Task Audit Details (History) gives you information regarding the status of the task when it was last executed. The time of execution of the task is shown along with the task name and the task description. The Device Lists on which this task was executed is shown in the tree (Left Hand Side). If some additional devices were associated other than Device Lists during Exection, it is denoted with the Tree node named 'Associated Devices'. Selecting any of the Tree Nodes (DeviceLists) fills up the table on the right hand side with the Device level audit. Attribute level audit for any of the devices can be got by double clicking on them."); 
		}

		return helpContent;
	}

	public String getTitle()
	{
		return resourceBundle.getString("Task Audit Details");
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}



















