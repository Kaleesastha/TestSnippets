
//$Id: DataSourceForExecution.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import javax.swing.table.*;
import javax.swing.tree.*;
import java.util.*;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.adventnet.management.config.*;

public class DataSourceForExecution extends JPanel implements ConfigResponseListener 
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
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTree tree = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton paramsButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>



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
        JTextArea1= new javax.swing.JTextArea();
        JPanel3= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel5= new javax.swing.JPanel();
        JScrollPane2= new javax.swing.JScrollPane();
        tree= new javax.swing.JTree();
        JPanel2= new javax.swing.JPanel();
        paramsButton= new javax.swing.JButton();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
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
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JTextArea1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridLayout(1,0,5,5));
JPanel3.add(JPanel4);
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(table);
JPanel3.add(JPanel5);
JPanel5.setLayout(new BorderLayout(5,5));
JPanel5.add(JScrollPane2,BorderLayout.CENTER);
JScrollPane2.getViewport().add(tree);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(paramsButton);

  
//<End_setUpGUI_Container>
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
            JTextArea1.setOpaque(false);
            JTextArea1.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
          }

//<UserCode_Begin_Bean_JTextArea1>

//<UserCode_End_Bean_JTextArea1>

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
            paramsButton.setText(resourceBundle.getString("Configure Data Source Parameters"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+paramsButton,ex); 
          }

//<UserCode_Begin_Bean_paramsButton>

//<UserCode_End_Bean_paramsButton>
		JScrollPane2.setPreferredSize(new Dimension(JScrollPane2.getPreferredSize().width+140,JScrollPane2.getPreferredSize().height+0));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+2,JScrollPane1.getPreferredSize().height+2));

  
          //<End_setUpProperties>
	} 
	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      paramsButton.addActionListener(JButton1_JButton1_conn11);
  
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
        setPreferredSize(new Dimension(getPreferredSize().width+566,getPreferredSize().height+470)); 
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

		Vector v = new Vector();
		v.addElement(" ");
		v.addElement(resourceBundle.getString("Available Data Sources"));
		tableModel.setDataVector(new Vector(),v);
		TableColumn firstColumn = table.getColumnModel().getColumn(0);
		firstColumn.setMaxWidth(25);
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







	public DataSourceForExecution()
  {
		//<Begin_DataSourceForExecution>
    this.init();
  
    //<End_DataSourceForExecution>
	}

	public DataSourceForExecution(java.applet.Applet applet)
  {
		//<Begin_DataSourceForExecution_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_DataSourceForExecution_java.applet.Applet>
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


	private ConfigPanel configPanel = null;

	private Properties dataSourceParams = null;

	private Vector templateDataSourceParams = null;

	private DefaultTreeModel treeModel = null;

	private String templateName = null;


	public DataSourceForExecution(ConfigPanel configPanel, String templateName)
	{
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

		tree.setModel(treeModel);

		tree.setShowsRootHandles(true);
		tree.putClientProperty("JTree.lineStyle", "Angled");
		tree.setCellRenderer(new RendererForTemplateDetailsTree(configPanel));
		TableColumn firstColumn = table.getColumnModel().getColumn(0);
		firstColumn.setCellRenderer(new SelectionFlag(configPanel));
		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);

		paramsButton.setEnabled(false);
	}

	public void response(ConfigResultEvent configResultEvent)
	{
		int workId = configResultEvent.getWorkID();
		int errorCode = configResultEvent.getErrorCode();
		String uniqueId = configResultEvent.getRequestID();

		if(workId == NmsConfigConstants.GET_TASK)
		{
			showTemplateTree(configResultEvent.getTask());		
		}
		else if(workId == NmsConfigConstants.GET_ASSOCIATED_DATASOURCES)
		{
			extractAssociatedDataSources(configResultEvent.getAssociatedDataSources());
		}
		else if(workId == NmsConfigConstants.GET_DATASOURCE)
		{
			extractDataSourceParams(configResultEvent.getDataSource());
		}
	}

	private void extractDataSourceParams(String dataSource)
	{
		DataSourceReader reader = new DataSourceReader(dataSource);

		Vector params = reader.getDataSourceParams();

		dataSourceParams = new Properties();

		if(params != null)
		{
			for(int i=0; i<params.size(); i++)
			{
				dataSourceParams.put(params.elementAt(i), "");
			}
		}

		if(templateDataSourceParams != null)
		{
			for(int i=0; i<templateDataSourceParams.size(); i++)
			{
				dataSourceParams.put(templateDataSourceParams.elementAt(i), "");
			}
		}

		if(dataSourceParams.size() > 0)
		{
			paramsButton.setEnabled(true);
		}
		else
		{
			paramsButton.setEnabled(false);
		}
	}

	private void extractAssociatedDataSources(String[] dataSources)
	{
		if(dataSources != null)
		{
			for(int i=0; i<dataSources.length; i++)	
			{
				Vector rowData = new Vector();

				rowData.addElement(new MultipleListSelectionObject(new Object()));
				rowData.addElement(dataSources[i]);
				
				tableModel.addRow(rowData);
			}
		}
	}

	private void showTemplateTree(String template)
	{
		ConfigTaskReader reader = new ConfigTaskReader(configPanel, template);

		templateDataSourceParams = reader.getDataSourceParams();
		
		Vector placeHolders = reader.getTemplatePlaceHolders();

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
				DefaultMutableTreeNode neInputNode = new DefaultMutableTreeNode("NEInput");

				for(int i=0; i<neInputVec.size(); i++)
				{
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(neInputVec.elementAt(i));

					neInputNode.add(child);
				}

				root.add(neInputNode);
			}

			if(inventoryInputVec != null)	
			{
				DefaultMutableTreeNode inventoryInputNode = new DefaultMutableTreeNode("InventoryInput");

				for(int i=0; i<inventoryInputVec.size(); i++)
				{
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(inventoryInputVec.elementAt(i));

					inventoryInputNode.add(child);
				}

				root.add(inventoryInputNode);
			}

			if(userInputVec != null)	
			{
				DefaultMutableTreeNode userInputNode = new DefaultMutableTreeNode("UserInput");

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

	public String getSelectedDataSource()
	{
		String dataSource = null;

		for(int i=0;i<table.getRowCount();i++)
		{
			MultipleListSelectionObject object = (MultipleListSelectionObject)table.getValueAt(i,0);
			if(object.getState())
			{
				dataSource = (String)tableModel.getValueAt(i, 1);
				break;
			}	
		}	

		return dataSource;
	}

	public void setDataSourceParams(Properties dataSourceParams)
	{
		this.dataSourceParams = dataSourceParams;
	}

	public Properties getDataSourceParams()
	{
		Properties params = new Properties();

		if(params != null)
		{
			for(Enumeration enumerate = dataSourceParams.keys(); enumerate.hasMoreElements();)
			{
				String key = (String)enumerate.nextElement();
				String value = (String)dataSourceParams.get(key);
			
				params.put("$DataSourceParam$"+key, value);	
			}
		}

		return params;
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
		Point point = me.getPoint();		
		if(table.columnAtPoint(point) == 0)
		{
			int row = table.rowAtPoint(point);
			MultipleListSelectionObject object = (MultipleListSelectionObject)table.getValueAt(row,0);

			if(((MultipleListSelectionObject)object).getState())
			{
				((MultipleListSelectionObject)object).setFalseState();
				for(int i=0;i<table.getRowCount();i++)
				{
					MultipleListSelectionObject temp = (MultipleListSelectionObject)table.getValueAt(i,0);
					if( i != row)
						temp.setFalseState();
				}
			}
			else
			{
				((MultipleListSelectionObject)object).setTrueState();
				for(int i=0;i<table.getRowCount();i++)
				{
					MultipleListSelectionObject temp = (MultipleListSelectionObject)table.getValueAt(i,0);
					if( i != row)
						temp.setFalseState();
				}
			}		

			table.repaint();
		}

		int row = table.getSelectedRow();

		if(row != -1)
		{
			Object[] params = {table.getValueAt(row, 1)};
			
			configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DATASOURCE, params, this);
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
  dataSourceParamsButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

	public void dataSourceParamsButtonActionPerformed()
	{
		DataSourceParamsConfig dataSourceParamsConfig = new DataSourceParamsConfig(configPanel, this, dataSourceParams);
		dataSourceParamsConfig.setVisible(true);
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
