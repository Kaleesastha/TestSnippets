
//$Id: DeviceListOperation.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;


import javax.swing.table.*;
import javax.swing.tree.*;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;

import com.adventnet.management.config.*;


public class DeviceListOperation extends JDialog implements ConfigResponseListener, ActionListener, HelpInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel12 = null;
	javax.swing.JCheckBox allCheck = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTree tree = null;
	javax.swing.JPanel JPanel9 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JTextField taskNameField = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton modifyButton = null;
	javax.swing.JButton deleteButton = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel10 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel11 = null;
	javax.swing.JButton helpButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;
	private String deviceListName = "";
	private String taskName = "";
	private String protocol = ""; 
	private Vector deviceListVector = new Vector();
	DefaultTreeModel treeModel = null;
	DefaultMutableTreeNode rootNode = null;

	Vector protocolSpecificDeviceLists = null;

	private String helpKey = "Batch_Conf_Device_List";

	private HelpDialog helpDialog = null;

	public DeviceListOperation(ConfigPanel panel, String taskName)
	{
		super(panel.configClientUtils.getParentFrame(panel));

		configPanel = panel;
		this.taskName = taskName;

		applet = configPanel.applet;

		this.init();
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
            JLabel4.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JPanel2.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

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
            allCheck.setFocusPainted(false);
            allCheck.setText(resourceBundle.getString(" Select All"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+allCheck,ex); 
          }

//<UserCode_Begin_Bean_allCheck>

//<UserCode_End_Bean_allCheck>

          try
          {
            JButton2.setText(resourceBundle.getString("Existing Device Lists"));
            JButton2.setFocusPainted(false);
            JButton2.setFont(new Font("Dialog",1,13));
            JButton2.setForeground(new Color(-1));
            JButton2.setBackground(new Color(-16751002));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

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

          try
          {
            JPanel9.setBorder(new javax.swing.border.LineBorder(new Color(-1),1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel9,ex); 
          }

//<UserCode_Begin_Bean_JPanel9>

//<UserCode_End_Bean_JPanel9>

          try
          {
            JLabel5.setText(resourceBundle.getString("Task Name"));
            JLabel5.setForeground(new Color(-16764109));
            JLabel5.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            taskNameField.setEditable(false);
            taskNameField.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taskNameField,ex); 
          }

//<UserCode_Begin_Bean_taskNameField>

//<UserCode_End_Bean_taskNameField>

          try
          {
            JPanel7.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel7,ex); 
          }

//<UserCode_Begin_Bean_JPanel7>

//<UserCode_End_Bean_JPanel7>

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
            modifyButton.setText(resourceBundle.getString("Modify"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+modifyButton,ex); 
          }

//<UserCode_Begin_Bean_modifyButton>

//<UserCode_End_Bean_modifyButton>

          try
          {
            deleteButton.setText(resourceBundle.getString("Delete"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+deleteButton,ex); 
          }

//<UserCode_Begin_Bean_deleteButton>

//<UserCode_End_Bean_deleteButton>

          try
          {
            JPanel6.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            okButton.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); 
          }

//<UserCode_Begin_Bean_okButton>

//<UserCode_End_Bean_okButton>

          try
          {
            closeButton.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>

//<UserCode_End_Bean_closeButton>

          try
          {
            JButton1.setText(resourceBundle.getString("Help"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

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
            JLabel1.setText(resourceBundle.getString("Device List Configuration"));
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
            helpButton.setMaximumSize(new Dimension(35,23));
            helpButton.setMinimumSize(new Dimension(35,23));
            helpButton.setPreferredSize(new Dimension(35,23));
            helpButton.setBackground(new Color(-1));
            helpButton.setFocusPainted(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+helpButton,ex); 
          }

//<UserCode_Begin_Bean_helpButton>

//<UserCode_End_Bean_helpButton>

//<UserCode_Begin_Bean_tableModel>
Vector v = new Vector();
v.addElement(" ");
v.addElement("Device Lists");
Vector v2 = new Vector();
tableModel.setDataVector(v2,v);
//<UserCode_End_Bean_tableModel>
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+13,JButton1.getPreferredSize().height+0));
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+21,okButton.getPreferredSize().height+0));
		addButton.setPreferredSize(new Dimension(addButton.getPreferredSize().width+10,addButton.getPreferredSize().height+0));

  
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
        this.setSize(getPreferredSize().width+710,getPreferredSize().height+514); 
          setTitle(resourceBundle.getString("DeviceListOperation"));
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
		URL docBase = configPanel.applet.getDocumentBase();
		//JLabel3.setIcon(configPanel.configClientUtils.getImage(docBase + "../images/alarm.png"));		
		protocol = configPanel.configMainUI.getProtocol(taskName);
		setTaskNameAndRequestDeviceList();
		configPanel.configClientUtils.centerWindow(this);
		docBase = configPanel.applet.getDocumentBase();
		String test = docBase+"../images/devicelist_wiz.png" ;
		ImageIcon satishBug = configPanel.configClientUtils.getImage(test);
		JLabel4.setIcon(satishBug);
		ImageIcon icon1 = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/contextualhelp.png");
		helpButton.setIcon(icon1);
		tree.setCellRenderer(new TreeRendererForDeviceLists(configPanel));
		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		rootNode = new DefaultMutableTreeNode("Details Of Selected Device List");
		if(treeModel == null)	
		{
			treeModel = new DefaultTreeModel(rootNode);
			tree.setModel(treeModel);
		}
		tree.putClientProperty("JTree.lineStyle", "Angled");
		tree.setRowHeight(22);
		TableColumn firstColumn = table.getColumnModel().getColumn(0);
		firstColumn.setMaxWidth(25);
		firstColumn.setCellRenderer(new SelectionFlag(configPanel));

		TableColumn lastColumn = table.getColumnModel().getColumn(1);
		ImageIcon listIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/devicelist.png");
		lastColumn.setCellRenderer(new CommonIconRenderer(listIcon));


		JButton button = new JButton(resourceBundle.getString("Device List Details"));
		button.setFocusPainted(false);
		button.setBackground(new Color(0,102,102));
		button.setForeground(Color.white);
		button.setFont(new Font("dialog",Font.BOLD,13));		
		//button.setBorder(new javax.swing.border.LineBorder(Color.blue,1));
		JViewport viewPort = new JViewport();
		viewPort.setView(button);
		JTableHeader header = table.getTableHeader();
		header.setPreferredSize(new Dimension((int)(table.getPreferredSize()).width,22));
		header.setMinimumSize(new Dimension((int)(table.getMinimumSize()).width,22));
		JScrollPane2.setColumnHeader(viewPort);
		tree.setShowsRootHandles(true);
		tree.setRootVisible(false);
		table.setDefaultEditor(Object.class,null);
		header.setPreferredSize(new Dimension(0,0));
		header.setMinimumSize(new Dimension(0,0));				
		header.setMaximumSize(new Dimension(0,0));					
		header.setVisible(false);
		ImageIcon icon12= configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/contextualhelp.png");
		helpButton.setIcon(icon12);

		JButton1.addActionListener(this);
	} 
	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      helpButton_helpButton_conn1 helpButton_helpButton_conn11 =  new helpButton_helpButton_conn1();
      helpButton.addActionListener(helpButton_helpButton_conn11);
      allCheck_allCheck_conn1 allCheck_allCheck_conn11 =  new allCheck_allCheck_conn1();
      allCheck.addActionListener(allCheck_allCheck_conn11);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      okButton_okButton_conn1 okButton_okButton_conn11 =  new okButton_okButton_conn1();
      okButton.addActionListener(okButton_okButton_conn11);
      modifyButton_modifyButton_conn1 modifyButton_modifyButton_conn11 =  new modifyButton_modifyButton_conn1();
      modifyButton.addActionListener(modifyButton_modifyButton_conn11);
      deleteButton_JPanel7_conn1 deleteButton_JPanel7_conn11 =  new deleteButton_JPanel7_conn1();
      deleteButton.addActionListener(deleteButton_JPanel7_conn11);
      closeButton_closeButton_conn1 closeButton_closeButton_conn11 =  new closeButton_closeButton_conn1();
      closeButton.addActionListener(closeButton_closeButton_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      addButton.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
	} 
	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel8= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel12= new javax.swing.JPanel();
        allCheck= new javax.swing.JCheckBox();
        JButton2= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        JScrollPane2= new javax.swing.JScrollPane();
        tree= new javax.swing.JTree();
        JPanel9= new javax.swing.JPanel();
        JLabel5= new javax.swing.JLabel();
        taskNameField= new javax.swing.JTextField();
        JPanel7= new javax.swing.JPanel();
        addButton= new javax.swing.JButton();
        modifyButton= new javax.swing.JButton();
        deleteButton= new javax.swing.JButton();
        JPanel6= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        closeButton= new javax.swing.JButton();
        JButton1= new javax.swing.JButton();
        JPanel1= new javax.swing.JPanel();
        JPanel10= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel11= new javax.swing.JPanel();
        helpButton= new javax.swing.JButton();
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
Top.add(JPanel8,cons);
JPanel8.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel8.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel8.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel3,cons);
JPanel3.setLayout(new GridLayout(1,0,5,5));
JPanel3.add(JPanel4);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel4.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(0,0,0,0);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JPanel12,cons);
JPanel12.setLayout(new GridBagLayout());
inset = new Insets(0,5,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel12.add(allCheck,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JButton2,cons);
JPanel3.add(JPanel5);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel5.add(JScrollPane2,cons);
JScrollPane2.getViewport().add(tree);
inset = new Insets(5,5,15,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel9,cons);
JPanel9.setLayout(new GridBagLayout());
inset = new Insets(10,5,10,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel9.add(JLabel5,cons);
inset = new Insets(10,5,10,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel9.add(taskNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel7,cons);
JPanel7.setLayout(new FlowLayout(0,10,5));
JPanel7.add(addButton);
JPanel7.add(modifyButton);
JPanel7.add(deleteButton);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel6,cons);
JPanel6.setLayout(new FlowLayout(2,5,5));
JPanel6.add(okButton);
JPanel6.add(closeButton);
JPanel6.add(JButton1);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,10,5,20);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JPanel10,cons);
JPanel10.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel10.add(JLabel1,cons);
inset = new Insets(5,5,5,20);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JPanel11,cons);
JPanel11.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel11.add(helpButton,cons);

  
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




	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 newDeviceListButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

	public void newDeviceListButtonActionPerformed()
	{
		AddDeviceList add = new AddDeviceList(configPanel,this,protocol);
		add.setVisible(true);
	}









	//<Begin__class_modifyButton_modifyButton_conn1>

 class modifyButton_modifyButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_modifyButton_modifyButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  modifyButtonActionPerformed();
     }
//<UserCode_End_Connection_modifyButton_modifyButton_conn1>
 }//<End__class_modifyButton_modifyButton_conn1>
	//<Begin__class_deleteButton_JPanel7_conn1>

 class deleteButton_JPanel7_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_deleteButton_JPanel7_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  deleteButtonActionPerformed();
     }
//<UserCode_End_Connection_deleteButton_JPanel7_conn1>
 }//<End__class_deleteButton_JPanel7_conn1>
	//<Begin__class_closeButton_closeButton_conn1>

 class closeButton_closeButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_closeButton_closeButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  closeButtonActionPerformed();
     }
//<UserCode_End_Connection_closeButton_closeButton_conn1>
 }//<End__class_closeButton_closeButton_conn1>

	public void modifyButtonActionPerformed()
	{
		int row = table.getSelectedRow();
		if(row != -1)
		{
			deviceListName = (String)table.getValueAt(row,1);

			AddDeviceList add = new AddDeviceList(configPanel,this,protocol,deviceListName);
			add.setVisible(true);
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("Select a devicelist to modify"), resourceBundle.getString("Info"), "info");
			return;
		}

	}

	public void deleteButtonActionPerformed()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			if (configPanel.configClientUtils.getConfirmation(this))
			{ 
				String name = (String)table.getValueAt(row,1);

				Object[] params = {name};
				configPanel.configResponseHandler.sendRequest(NmsConfigConstants.DELETE_DEVICELIST,params, this);

				deviceListVector.removeElement(name);
				tableModel.removeRow(row);
				rootNode.removeAllChildren();
				treeModel.reload();

				protocolSpecificDeviceLists.remove(name);
			}
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("Select a devicelist to delete"), resourceBundle.getString("Info"), "info");
			return;
		}

	}

	public void closeButtonActionPerformed()
	{
		setVisible(false);
		dispose();
	}





	public DeviceListOperation()
  {
		//<Begin_DeviceListOperation>
    pack();
  
    //<End_DeviceListOperation>
	}

	public DeviceListOperation(java.applet.Applet applet)
  {
		//<Begin_DeviceListOperation_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_DeviceListOperation_java.applet.Applet>
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
	private void setTaskNameAndRequestDeviceList()
	{
		taskNameField.setText(taskName);

		Object[] params = {taskName};
		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DEVICELIST_FOR_TASK, params, this);

		Object[] param = {protocol.toLowerCase()};
		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_PROTOCOL_SPECIFIC_DEVICELIST_NAMES, param, this);
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
				deviceListVector = cre.getTaskSpecificDeviceListNames();

				for(int i=0;i<deviceListVector.size();i++)
				{
					Vector v = new Vector();
					MultipleListSelectionObject object = new MultipleListSelectionObject("");
					object.setTrueState();
					v.addElement(object);
					v.addElement(deviceListVector.elementAt(i));
					tableModel.addRow(v);
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
		else if(workId == NmsConfigConstants.DELETE_DEVICELIST)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				if(deviceListVector.contains(deviceListName))
				{
					deviceListVector.removeElement(deviceListName);
				}
			}
		}
		else if(workId == NmsConfigConstants.GET_PROTOCOL_SPECIFIC_DEVICELIST_NAMES)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				Vector deviceLists = cre.getProtocolSpecificDeviceListNames();

				if(deviceLists != null)
				{
					protocolSpecificDeviceLists = deviceLists;
					for(int i=0;i<deviceLists.size();i++)
					{
						if(! deviceListVector.contains(deviceLists.elementAt(i)))
						{
							Vector v = new Vector();
							v.addElement(new MultipleListSelectionObject(""));
							v.addElement(deviceLists.elementAt(i));
							tableModel.addRow(v);
						}
					}
				}
			}

			else
			{
				System.out.println(cre.getErrorString());
			}
		}
		else if(workId == NmsConfigConstants.SAVE_DEVICELISTMAP)
		{
			if(errorCode != NmsConfigConstants.NO_ERROR)
			{
				configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString(cre.getErrorString()), resourceBundle.getString("Error"), "error");
			}

			closeButtonActionPerformed();
		}
	}

	public void addDeviceNameToList(String deviceListName)
	{
		Vector v = new Vector();
		MultipleListSelectionObject object = new MultipleListSelectionObject("");
		object.setTrueState();
		v.addElement(object);			
		v.addElement(deviceListName);
		if(protocolSpecificDeviceLists == null)
			protocolSpecificDeviceLists = new Vector();
		if(! protocolSpecificDeviceLists.contains(deviceListName))
		{
			deviceListVector.addElement(deviceListName);	
			protocolSpecificDeviceLists.addElement(deviceListName);
			tableModel.addRow(v);
			table.getSelectionModel().clearSelection();
			table.getSelectionModel().addSelectionInterval(table.getRowCount()-1,table.getRowCount()-1);
		}
		Object[] params = {deviceListName};
		this.deviceListName = deviceListName;
		Object[] obj = {taskName};

		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DEVICELIST, params, this);
	}

	boolean firstTime = true;

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
		Point p = me.getPoint();
		int row = table.rowAtPoint(p);
		if(row != -1)
		{
			int column = table.columnAtPoint(p);
			if(column == 0)
			{
				MultipleListSelectionObject object = (MultipleListSelectionObject)table.getValueAt(row,0);
				if(object.getState())
				{
					object.setFalseState();
				}
				else
				{
					object.setTrueState();
				}
				table.repaint();
			}
			deviceListName = (String)table.getValueAt(row,1);
			Object[] params = {deviceListName};
			configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DEVICELIST, params, this);
		}		
	}




	//<Begin__class_okButton_okButton_conn1>

 class okButton_okButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_okButton_okButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  okButtonActionPerformed();
     }
//<UserCode_End_Connection_okButton_okButton_conn1>
 }//<End__class_okButton_okButton_conn1>

	public void okButtonActionPerformed()
	{
		Vector v = new Vector();
		for(int i=0;i<table.getRowCount();i++)
		{
			MultipleListSelectionObject object = (MultipleListSelectionObject)table.getValueAt(i,0);
			if(object.getState())
			{
				v.addElement(table.getValueAt(i,1));
			}
		}

		String [] param = new String[v.size()];
		for(int i =0;i<v.size();i++)
		{
			param[i]=v.elementAt(i).toString();
		}

		if(v.size() ==  0)
		{
			Object [] objectArr = {taskName};
			configPanel.configResponseHandler.sendRequest(NmsConfigConstants.DELETE_DEVICELISTMAP,objectArr,this);
		}
		else
		{
			Object [] objectArr = {taskName,param};
			configPanel.configResponseHandler.sendRequest(NmsConfigConstants.SAVE_DEVICELISTMAP,objectArr,this);
		}

		setVisible(false);
	}




	//<Begin__class_allCheck_allCheck_conn1>

 class allCheck_allCheck_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_allCheck_allCheck_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  checkAllActionPerformed();
     }
//<UserCode_End_Connection_allCheck_allCheck_conn1>
 }//<End__class_allCheck_allCheck_conn1>

	public void checkAllActionPerformed()
	{
		if(allCheck.isSelected())
		{
			for(int i=0;i<table.getRowCount();i++)
			{
				((MultipleListSelectionObject)table.getValueAt(i,0)).setTrueState();
			}
		}

		else
		{
			for(int i=0;i<table.getRowCount();i++)
			{
				((MultipleListSelectionObject)table.getValueAt(i,0)).setFalseState() ;
			}
		}

		table.repaint();

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

		if(key.equals(resourceBundle.getString("Device List Operations")))
		{
			helpContent = resourceBundle.getString("A Device List is nothing but a collection of devices and is configured based on a definite protocol. Device Lists are added at the protocol level and can be associated with the various tasks defined for that protocol. When this User Interface is invoked for a selected task, the device lists that are already associated with that task are identified by the 'Check' mark present against their names. Those which remain 'Unchecked' are defined for that protocol (to which this task belongs) and can be associated with that task by using the Check boxes.");
		}

		return helpContent;
	}

	public String getTitle()
	{
		return resourceBundle.getString("Device List Operations");
	}

	public void actionPerformed(ActionEvent ae)
	{
		configPanel.configClientUtils.showHelp(helpKey);
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
