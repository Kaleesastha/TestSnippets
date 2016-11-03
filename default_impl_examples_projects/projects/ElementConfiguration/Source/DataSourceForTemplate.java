
//$Id: DataSourceForTemplate.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;


import javax.swing.table.*;
import java.util.*;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.tree.*;

import com.adventnet.management.config.*;

public class DataSourceForTemplate extends JDialog implements ConfigResponseListener, ActionListener, HelpInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTree tree = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton modifyButton = null;
	javax.swing.JButton deleteButton = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField JTextField1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel9 = null;
	javax.swing.JPanel JPanel10 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JPanel JPanel11 = null;
	javax.swing.JButton helpButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>












	public DataSourceForTemplate()
  {
		//<Begin_DataSourceForTemplate>
    pack();
  
    //<End_DataSourceForTemplate>
	}

	public DataSourceForTemplate(java.applet.Applet applet)
  {
		//<Begin_DataSourceForTemplate_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_DataSourceForTemplate_java.applet.Applet>
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
        JLabel2= new javax.swing.JLabel();
        JPanel4= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JPanel7= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JButton2= new javax.swing.JButton();
        JPanel8= new javax.swing.JPanel();
        JScrollPane2= new javax.swing.JScrollPane();
        tree= new javax.swing.JTree();
        JPanel6= new javax.swing.JPanel();
        addButton= new javax.swing.JButton();
        modifyButton= new javax.swing.JButton();
        deleteButton= new javax.swing.JButton();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JTextField1= new javax.swing.JTextField();
        JPanel3= new javax.swing.JPanel();
        cancelButton= new javax.swing.JButton();
        JButton1= new javax.swing.JButton();
        JPanel9= new javax.swing.JPanel();
        JPanel10= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JPanel11= new javax.swing.JPanel();
        helpButton= new javax.swing.JButton();
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
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel2.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel4.add(JPanel5,cons);
JPanel5.setLayout(new GridLayout(1,0,10,10));
JPanel5.add(JPanel7);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel7.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel7.add(JButton2,cons);
JPanel5.add(JPanel8);
JPanel8.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel8.add(JScrollPane2,cons);
JScrollPane2.getViewport().add(tree);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JPanel6,cons);
JPanel6.setLayout(new FlowLayout(0,8,5));
JPanel6.add(addButton);
JPanel6.add(modifyButton);
JPanel6.add(deleteButton);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(10,5,10,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(10,5,10,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JTextField1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(2,5,5));
JPanel3.add(cancelButton);
JPanel3.add(JButton1);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel9,cons);
JPanel9.setLayout(new GridBagLayout());
inset = new Insets(5,10,5,20);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel9.add(JPanel10,cons);
JPanel10.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel10.add(JLabel3,cons);
inset = new Insets(5,5,5,20);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel9.add(JPanel11,cons);
JPanel11.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel11.add(helpButton,cons);

  
//<End_setUpGUI_Container>
	} 
	public void setUpProperties()
  { 

		//<Begin_setUpProperties> 

          try
          {
            JLabel2.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JPanel4.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JScrollPane1.setBorder(new javax.swing.border.BevelBorder(1));
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
            table.setGridColor(new Color(-16764109));
            table.setRowHeight(22);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

          try
          {
            JButton2.setBackground(new Color(-16751002));
            JButton2.setText(resourceBundle.getString("Existing Data Sources"));
            JButton2.setFocusPainted(false);
            JButton2.setFont(new Font("Dialog",1,13));
            JButton2.setForeground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            JScrollPane2.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane2,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane2>

//<UserCode_End_Bean_JScrollPane2>

          try
          {
            tree.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tree,ex); 
          }

//<UserCode_Begin_Bean_tree>

//<UserCode_End_Bean_tree>

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
            JPanel1.setBorder(new javax.swing.border.LineBorder(new Color(-1),1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setText(resourceBundle.getString("Data Source configuration for"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JTextField1.setEditable(false);
            JTextField1.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField1,ex); 
          }

//<UserCode_Begin_Bean_JTextField1>

//<UserCode_End_Bean_JTextField1>

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
            cancelButton.setText(resourceBundle.getString("Close"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>

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
            JPanel9.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel9,ex); 
          }

//<UserCode_Begin_Bean_JPanel9>

//<UserCode_End_Bean_JPanel9>

          try
          {
            JLabel3.setText(resourceBundle.getString("Data Source Configuration"));
            JLabel3.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            helpButton.setPreferredSize(new Dimension(35,23));
            helpButton.setMinimumSize(new Dimension(35,23));
            helpButton.setMaximumSize(new Dimension(35,23));
            helpButton.setBackground(new Color(-1));
            helpButton.setFocusPainted(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+helpButton,ex); 
          }

//<UserCode_Begin_Bean_helpButton>

//<UserCode_End_Bean_helpButton>
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+13,JButton1.getPreferredSize().height+0));
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+5,cancelButton.getPreferredSize().height+0));
		deleteButton.setPreferredSize(new Dimension(deleteButton.getPreferredSize().width+1,deleteButton.getPreferredSize().height+0));
		modifyButton.setPreferredSize(new Dimension(modifyButton.getPreferredSize().width+1,modifyButton.getPreferredSize().height+0));
		addButton.setPreferredSize(new Dimension(addButton.getPreferredSize().width+15,addButton.getPreferredSize().height+0));

  
          //<End_setUpProperties>
	}


	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      helpButton_helpButton_conn1 helpButton_helpButton_conn11 =  new helpButton_helpButton_conn1();
      helpButton.addActionListener(helpButton_helpButton_conn11);
      JButton5_JButton5_conn1 JButton5_JButton5_conn11 =  new JButton5_JButton5_conn1();
      deleteButton.addActionListener(JButton5_JButton5_conn11);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      addButton.addActionListener(JButton3_JButton3_conn11);
      JButton4_JButton4_conn2 JButton4_JButton4_conn21 =  new JButton4_JButton4_conn2();
      modifyButton.addActionListener(JButton4_JButton4_conn21);
      cancelButton_cancelButton_conn1 cancelButton_cancelButton_conn11 =  new cancelButton_cancelButton_conn1();
      cancelButton.addActionListener(cancelButton_cancelButton_conn11);
  
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
        this.setSize(getPreferredSize().width+728,getPreferredSize().height+508); 
          setTitle(resourceBundle.getString("DataSourceForTemplate"));
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
		setTitle(resourceBundle.getString("Data Source Configuration"));
		URL docBase = configPanel.applet.getDocumentBase();
		String test = docBase+"../images/datasource_wiz.png" ;
		ImageIcon satishBug = configPanel.configClientUtils.getImage(test);
		JLabel2.setIcon(satishBug);

		String help = docBase+"../images/linux.png";
		ImageIcon icon = configPanel.configClientUtils.getImage(help);
		//JLabel3.setIcon(icon);
		ImageIcon icon1 = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/contextualhelp.png");
		helpButton.setIcon(icon1);		
		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);
		tree.setCellRenderer(new RendererForTemplateDetailsTree(configPanel));		
		JTableHeader header = table.getTableHeader();
		header.setPreferredSize(new Dimension((int)(table.getPreferredSize()).getWidth(),20));
		header.setMinimumSize(new Dimension((int)(table.getMinimumSize()).getWidth(),20));		


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
  deleteButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton5_JButton5_conn1>
 }//<End__class_JButton5_JButton5_conn1>
	//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  addButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>
	//<Begin__class_JButton4_JButton4_conn2>

 class JButton4_JButton4_conn2 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton4_JButton4_conn2>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  modifyButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton4_JButton4_conn2>
 }//<End__class_JButton4_JButton4_conn2>

	public void addButtonActionPerformed()
	{
		AddModifyDataSource data = new AddModifyDataSource(configPanel, this, templateDetails);
		data.setVisible(true);	
	}

	public void modifyButtonActionPerformed()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			Object[] params = {tableModel.getValueAt(row, 0)};
			configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DATASOURCE, params, this);			
		}
	}

	public void deleteButtonActionPerformed()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			if(configPanel.configClientUtils.getConfirmation(this))
			{
				Object[] params = {tableModel.getValueAt(row, 0)};
				configPanel.configResponseHandler.sendRequest(NmsConfigConstants.DELETE_DATASOURCE, params, this);			
				tableModel.removeRow(row);
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
	{}


	//<============================================================================================================>

	private	ConfigPanel configPanel = null;

	private HelpDialog helpDialog = null;

	DefaultTreeModel treeModel = null;

	private String templateName = null;
	private String templateDescription = null;
	private String protocol = null;

	private Vector templateDetails = new Vector();

	private Vector placeHolders = null;

	private String helpKey = "Batch_Conf_Data_Sources";

	public DataSourceForTemplate(ConfigPanel configPanel, String templateName)
	{
		super(configPanel.configClientUtils.getParentFrame(configPanel));

		this.configPanel = configPanel;
		this.templateName = templateName;
		
		applet = configPanel.applet;

		init();
		configInit();
	}

	private void configInit()
	{
		Object[] params = {templateName};	

		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_ASSOCIATED_DATASOURCES, params, this);
		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_TASK, params, this);

		Vector columns = new Vector();
		columns.addElement(resourceBundle.getString("Existing Data Sources "));

		tableModel.setDataVector(new Vector(), columns);

		JTextField1.setText(templateName);

		tree.setModel(treeModel);

		tree.putClientProperty("JTree.lineStyle", "Angled");
		tree.setShowsRootHandles(true);
		tree.setRowHeight(22);
		configPanel.configClientUtils.centerWindow(this);
		JButton button = new JButton(resourceBundle.getString("Template Details"));
		button.setFocusPainted(false);
		button.setBackground(new Color(0,102,102));
		button.setForeground(Color.white);
		button.setFont(new Font("dialog",Font.BOLD,13));
		JViewport viewPort = new JViewport();
		viewPort.setView(button);
		JScrollPane2.setColumnHeader(viewPort);
		tree.setShowsRootHandles(true);
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

		TableColumn lastColumn = table.getColumnModel().getColumn(0);
		ImageIcon listIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/datasource.png");
		lastColumn.setCellRenderer(new CommonIconRenderer(listIcon));

		JButton1.addActionListener(this);
	}

	private void extractTemplatePlaceHolders(String template)
	{
		ConfigTaskReader reader = new ConfigTaskReader(configPanel, template);

		templateDescription = reader.getTaskDesc();
		protocol = reader.getProtocol();

		placeHolders = reader.getTemplatePlaceHolders();	

		templateDetails.addElement(templateName);
		templateDetails.addElement(templateDescription); 
		templateDetails.addElement(protocol);
		templateDetails.addElement(placeHolders);
		templateDetails.addElement(reader.getNEInputAttributes());

		showTemplatePlaceHoldersTree();
	}

	public void response(ConfigResultEvent configResultEvent)
	{
		int workId = configResultEvent.getWorkID();
		int errorCode = configResultEvent.getErrorCode();
		String uniqueId = configResultEvent.getRequestID();

		if(workId == NmsConfigConstants.GET_TASK)
		{
			extractTemplatePlaceHolders(configResultEvent.getTask());		
		}
		else if(workId == NmsConfigConstants.GET_DATASOURCE)
		{
			extractDataSource(configResultEvent.getDataSource());
		}
		else if(workId == NmsConfigConstants.GET_ASSOCIATED_DATASOURCES)
		{
			extractAssociatedDataSources(configResultEvent.getAssociatedDataSources());
		}
	}

	public void addDataSourceToView(String dataSourceName)
	{
		Vector rowData = new Vector();

		rowData.addElement(dataSourceName);

		tableModel.addRow(rowData);
	}

	private void extractDataSource(String dataSource)
	{
		DataSourceReader reader = new DataSourceReader(dataSource);

		AddModifyDataSource data = new AddModifyDataSource(configPanel, this, reader,templateDetails);
		data.setVisible(true);
	}

	private void extractAssociatedDataSources(String[] dataSources)
	{
		if(dataSources != null)
		{
			for(int i=0; i<dataSources.length; i++)	
			{
				addDataSourceToView(dataSources[i]);
			}
		}
	}

	private void showTemplatePlaceHoldersTree()
	{
		if(placeHolders != null)
		{
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(templateName);
			treeModel = new DefaultTreeModel(root);
			tree.setModel(treeModel);

			Vector neInputVec = null;
			Vector inventoryInputVec = null;
			Vector userInputVec = null;

			for(int i=0; i<placeHolders.size(); i++)
			{
				String placeHolder = (String)placeHolders.elementAt(i);

				if(placeHolder.startsWith("$NEInput$"))
				{
					if(neInputVec == null)
					{
						neInputVec = new Vector();
					}

					neInputVec.addElement(placeHolder.substring(9));
				}
				else if(placeHolder.startsWith("$InventoryInput$"))
				{
					if(inventoryInputVec == null)
					{
						inventoryInputVec = new Vector();
					}

					inventoryInputVec.addElement(placeHolder.substring(16));
				}
				else if(placeHolder.startsWith("$UserInput$"))
				{
					if(userInputVec == null)
					{
						userInputVec = new Vector();
					}

					userInputVec.addElement(placeHolder.substring(11));
				}
			}

			if(neInputVec != null)	
			{
				DefaultMutableTreeNode neInputNode = new DefaultMutableTreeNode("Network Input");

				for(int i=0; i<neInputVec.size(); i++)
				{
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(neInputVec.elementAt(i));

					neInputNode.add(child);
				}

				root.add(neInputNode);
			}

			if(inventoryInputVec != null)	
			{
				DefaultMutableTreeNode inventoryInputNode = new DefaultMutableTreeNode("Inventory Input");

				for(int i=0; i<inventoryInputVec.size(); i++)
				{
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(inventoryInputVec.elementAt(i));

					inventoryInputNode.add(child);
				}

				root.add(inventoryInputNode);
			}

			if(userInputVec != null)	
			{
				DefaultMutableTreeNode userInputNode = new DefaultMutableTreeNode("User Input");

				for(int i=0; i<userInputVec.size(); i++)
				{
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(userInputVec.elementAt(i));

					userInputNode.add(child);
				}

				root.add(userInputNode);
			}

			treeModel.reload();

			for(int i=0; i<root.getChildCount(); i++)
			{
				tree.expandPath(new TreePath(((DefaultMutableTreeNode)root.getChildAt(i)).getPath()));
			}
		}
	}


	//<Begin__class_cancelButton_cancelButton_conn1>

 class cancelButton_cancelButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_cancelButton_cancelButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  closeButtonActionPerformed();
     }
//<UserCode_End_Connection_cancelButton_cancelButton_conn1>
 }//<End__class_cancelButton_cancelButton_conn1>

	public void closeButtonActionPerformed()
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

		if(key.equals(resourceBundle.getString("Data Source Configuration")))
		{
			helpContent = "\t" + resourceBundle.getString("When a task is defined as a template, then data is required to be specified in either of the three input types namely Network Element Input, Inventory Input or User Input. For every input type a Place Holder or identifier is required to be specified during template task configuration. For every Place Holder a value can be specified that either corresponds to a specific data or is the specified data itself. User can create new data source or modify or delete existing data sources for the selected template. The existing data sources for the template selected will be listed and also the details of the template will be listed."); 
		}

		return helpContent;
	}

	public String getTitle()
	{
		return resourceBundle.getString("Data Source Configuration");
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

