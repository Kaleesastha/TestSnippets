
//$Id: TemplatesConfiguration.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import javax.swing.table.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.adventnet.management.config.*;

public class TemplatesConfiguration extends JPanel implements ListSelectionListener, ConfigResponseListener
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
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTable table2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.table.DefaultTableModel tableModel1 = null;
	javax.swing.table.DefaultTableModel tableModel2 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;

	private Hashtable templateVsAssocDSs = new Hashtable();
	private Hashtable templateVsSelecDS = new Hashtable();

	private Hashtable templateVsDSPs = new Hashtable();

	private Hashtable dataSourceParamsTable = new Hashtable();

	private Hashtable uniqueIDVsTemplate = new Hashtable();

	private String selectedTemplate = null;

	public TemplatesConfiguration(ConfigPanel configPanel)
	{
		this.configPanel = configPanel;
		
		applet = configPanel.applet;

		init();
		configInit();
	}

	public TemplatesConfiguration()
  {
		//<Begin_TemplatesConfiguration>
    this.init();
  
    //<End_TemplatesConfiguration>
	}

	public TemplatesConfiguration(java.applet.Applet applet)
  {
		//<Begin_TemplatesConfiguration_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_TemplatesConfiguration_java.applet.Applet>
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
        setPreferredSize(new Dimension(getPreferredSize().width+502,getPreferredSize().height+349)); 
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
            JScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane1>

//<UserCode_End_Bean_JScrollPane1>

          try
          {
            table1.setModel(tableModel1);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table1,ex); 
          }

//<UserCode_Begin_Bean_table1>

//<UserCode_End_Bean_table1>

          try
          {
            JScrollPane2.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane2,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane2>

//<UserCode_End_Bean_JScrollPane2>

          try
          {
            table2.setModel(tableModel2);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table2,ex); 
          }

//<UserCode_Begin_Bean_table2>

//<UserCode_End_Bean_table2>

          try
          {
            JButton1.setText(resourceBundle.getString("Data Source Params"));
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
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table1= new javax.swing.JTable();
        JPanel3= new javax.swing.JPanel();
        JScrollPane2= new javax.swing.JScrollPane();
        table2= new javax.swing.JTable();
        JPanel4= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        tableModel1= new javax.swing.table.DefaultTableModel();
        tableModel2= new javax.swing.table.DefaultTableModel();

  
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
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridLayout(1,0,15,5));
JPanel1.add(JPanel2);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table1);
JPanel1.add(JPanel3);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JScrollPane2,cons);
JScrollPane2.getViewport().add(table2);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(2,5,5));
JPanel4.add(JButton1);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

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





	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  paramsActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>


	public void paramsActionPerformed()
	{
		if(selectedTemplate != null)
		{
			String dataSource = (String)templateVsSelecDS.get(selectedTemplate);

			if(dataSource == null)
			{
				configPanel.configClientUtils.showErrorDialog(this, "Please select a DataSoure for the template", "Info", "info");
				return;
			}

			if(dataSourceParamsTable.get(selectedTemplate+dataSource) == null)
			{
				Object[] params = {dataSource};
				String uniqueID = configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DATASOURCE, params, this);
				uniqueIDVsTemplate.put(uniqueID, selectedTemplate);
			}
			else
			{
				invokeDataSourceParamsDialog();
			}
		}
	}

	private void configInit()
	{
		table1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table1.getSelectionModel().addListSelectionListener(this);

		table1.setDefaultRenderer(Object.class, new SimpleTableSelectionRenderer());
		table1.setDefaultEditor(Object.class, null);

		Vector columns1 = new Vector();

		columns1.addElement(resourceBundle.getString("Template Name"));

		tableModel1.setDataVector(new Vector(), columns1);


		table2.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table2.getSelectionModel().addListSelectionListener(this);

		table2.setDefaultRenderer(Object.class, new SimpleTableSelectionRenderer());
		table2.setDefaultEditor(Object.class, null);

		Vector columns2 = new Vector();

		columns2.addElement(" ");
		columns2.addElement(resourceBundle.getString("Data Source Name"));

		tableModel2.setDataVector(new Vector(), columns2);

		TableColumn firstColumn = table2.getColumnModel().getColumn(0);

		firstColumn.setCellRenderer(new SelectionFlag(configPanel));
		firstColumn.setMaxWidth(25);
	}

	public void setTemplates(Vector templates)
	{
		removeTableRows(tableModel1);	
		removeTableRows(tableModel2);

		if(templates != null)
		{
			int rowToSelect = -1;

			for(int i=0; i<templates.size(); i++)
			{
				Vector rowData = new Vector();

				String template = (String)templates.elementAt(i);

				if(selectedTemplate != null && selectedTemplate.equals(template))
				{
					rowToSelect = i;
				}

				rowData.addElement(template);

				tableModel1.addRow(rowData);
			}

			if(rowToSelect != -1)
			{
				table1.setRowSelectionInterval(rowToSelect, rowToSelect);
			}

			cleanUpTable(templateVsAssocDSs, templates, false);
			cleanUpTable(templateVsSelecDS, templates, false);
			cleanUpTable(templateVsDSPs, templates, false);

			cleanUpTable(dataSourceParamsTable, templates, true);
		}
	}

	private void cleanUpTable(Hashtable table, Vector vec, boolean startsWith)
	{
		if(table != null && vec != null)
		{
			for(Enumeration enumerate = table.keys(); enumerate.hasMoreElements();)
			{
				String key = (String)enumerate.nextElement();

				if(startsWith)
				{
					int vecSize = vec.size();

					for(int i=0; i<vecSize; i++)
					{
						String element = (String)vec.elementAt(i);

						if(key.startsWith(element))
						{
							break;
						}

						if(i == vecSize)
						{
							table.remove(key);
						}
					}
				}
				else
				{
					if(!vec.contains(key))
					{
						table.remove(key);
					}
				}
			}
		}
	}

	public Hashtable getTemplatesWithdDataSource()
	{
		return templateVsSelecDS;
	}

	public Hashtable getDataSourceParamsTable()
	{
		return dataSourceParamsTable;
	}

	public void valueChanged(ListSelectionEvent lse)
	{
		Object source = lse.getSource();

		if(source.equals(table1.getSelectionModel()))
		{
			int row = table1.getSelectedRow();

			if(row != -1)
			{
				selectedTemplate = (String)tableModel1.getValueAt(row, 0);

				if(templateVsAssocDSs.get(selectedTemplate) == null)
				{
					Object[] params = {selectedTemplate};
					String uniqueID = configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_ASSOCIATED_DATASOURCES, params, this);
					uniqueIDVsTemplate.put(uniqueID, selectedTemplate);
				}
				else
				{
					updateDataSourceTable();
				}

				if(templateVsDSPs.get(selectedTemplate) == null)
				{		
					Object[] params = {selectedTemplate};
					String uniqueID = configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_TASK, params, this);
					uniqueIDVsTemplate.put(uniqueID, selectedTemplate);
				}
			}
		}
		else if(source.equals(table2.getSelectionModel()))
		{
			int row = table2.getSelectedRow();

			if(row != -1)
			{
				String dataSource = (String)tableModel2.getValueAt(row, 1);

				int rowCount = tableModel2.getRowCount();

				for(int i=0; i<rowCount; i++)	
				{
					MultipleListSelectionObject selectionObject = (MultipleListSelectionObject)tableModel2.getValueAt(i, 0);

					String item = (String)tableModel2.getValueAt(i, 1);

					if(item.equals(dataSource))
					{
						selectionObject.setTrueState();
					}
					else
					{
						selectionObject.setFalseState();
					}
				}

				templateVsSelecDS.put(selectedTemplate, dataSource);

				table2.repaint();
			}
		}
	}

	public void response(ConfigResultEvent configResultEvent)
	{
		int workID = configResultEvent.getWorkID();
		int errorCode = configResultEvent.getErrorCode();

		String uniqueID = configResultEvent.getRequestID();

		String template = (String)uniqueIDVsTemplate.remove(uniqueID);

		if(workID == NmsConfigConstants.GET_ASSOCIATED_DATASOURCES)
		{
			if(template != null)
			{
				templateVsAssocDSs.put(template, configResultEvent.getAssociatedDataSources());
				updateDataSourceTable();
			}
		}
		else if(workID == NmsConfigConstants.GET_TASK)
		{
			if(template != null)
			{
				String task = configResultEvent.getTask();	

				ConfigTaskReader reader = new ConfigTaskReader(configPanel, task);

				Vector dataSourceParams = reader.getDataSourceParams();

				if(dataSourceParams != null)
				{
					templateVsDSPs.put(template, dataSourceParams);
				}
			}
		}
		else if(workID == NmsConfigConstants.GET_DATASOURCE)
		{
			if(template != null)
			{	
				DataSourceReader reader = new DataSourceReader(configResultEvent.getDataSource());

				Vector dataSourceParams = reader.getDataSourceParams();
				Vector templateDataSourceParams = (Vector)templateVsDSPs.get(template);

				Properties prop = new Properties();

				if(dataSourceParams != null)
				{
					for(int i=0; i<dataSourceParams.size(); i++)
					{
						prop.put(dataSourceParams.elementAt(i), "");
					}
				}

				if(templateDataSourceParams != null)
				{
					for(int i=0; i<templateDataSourceParams.size(); i++)
					{
						prop.put(templateDataSourceParams.elementAt(i), "");
					}
				}

				dataSourceParamsTable.put(template+reader.getDataSourceName(), prop);

				invokeDataSourceParamsDialog();
			}
		}
	}

	private void invokeDataSourceParamsDialog()
	{
		String templateWithDS = selectedTemplate+(String)templateVsSelecDS.get(selectedTemplate);

		Properties dataSourceParams = (Properties)dataSourceParamsTable.get(templateWithDS);

		if(dataSourceParams != null && dataSourceParams.size() > 0)
		{
			DataSourceParamsConfig dataSourceParamsDialog = new DataSourceParamsConfig(configPanel, this, dataSourceParams, templateWithDS);
			dataSourceParamsDialog.setVisible(true);
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(this, "There is no DataSourceParam associated for the selected template and datasource", "Info", "info");
		}	
	}

	public void setDataSourceParams(String templateWithDS, Properties dataSourceParams)
	{
		if(templateWithDS != null && dataSourceParams != null)	
		{
			dataSourceParamsTable.put(templateWithDS, dataSourceParams);
		}
	}

	private void updateDataSourceTable()
	{
		if(selectedTemplate != null)
		{
			removeTableRows(tableModel2);

			String[] dataSources = (String[])templateVsAssocDSs.get(selectedTemplate);
			String selectedDataSource = (String)templateVsSelecDS.get(selectedTemplate);

			if(dataSources != null)
			{
				for(int i=0; i<dataSources.length; i++)
				{
					Vector rowData = new Vector();

					MultipleListSelectionObject selectionObject = new MultipleListSelectionObject(new Object());

					if(selectedDataSource != null && selectedDataSource.equals(dataSources[i]))
					{
						selectionObject.setTrueState();
					}

					rowData.addElement(selectionObject);
					rowData.addElement(dataSources[i]);

					tableModel2.addRow(rowData);
				}
			}
		}
	}

	private void removeTableRows(DefaultTableModel tableModel)
	{
		if(tableModel != null)
		{
			int rowCount = tableModel.getRowCount();

			if(rowCount > 0)
			{
				for(int i=0; i<rowCount; i++)
				{
					tableModel.removeRow(0);
				}
			}
		}
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}








