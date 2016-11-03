
//$Id: SelectTasksPanel.java,v 1.1 2006/08/29 13:56:51 build Exp $
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


public class SelectTasksPanel extends JPanel implements ListSelectionListener 
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
	javax.swing.table.DefaultTableModel tableModel1 = null;
	javax.swing.table.DefaultTableModel tableModel2 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;

	private String protocol = null;

	public SelectTasksPanel(ConfigPanel configPanel)
	{
		this.configPanel = configPanel;

		applet = configPanel.applet;

		init();
		configInit();
	}

	public SelectTasksPanel()
  {
		//<Begin_SelectTasksPanel>
    this.init();
  
    //<End_SelectTasksPanel>
	}

	public SelectTasksPanel(java.applet.Applet applet)
  {
		//<Begin_SelectTasksPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_SelectTasksPanel_java.applet.Applet>
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
        setPreferredSize(new Dimension(getPreferredSize().width+546,getPreferredSize().height+351)); 
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
        tableModel1= new javax.swing.table.DefaultTableModel();
        tableModel2= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
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

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

  
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

	private void configInit()
	{
		table1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table1.getSelectionModel().addListSelectionListener(this);
		table1.addMouseListener(new MyMouseListener());

		table1.setDefaultRenderer(Object.class, new SimpleTableSelectionRenderer());
		table1.setDefaultEditor(Object.class, null);

		Vector columns1 = new Vector();

		columns1.addElement(" ");
		columns1.addElement(resourceBundle.getString("Task Name"));

		tableModel1.setDataVector(new Vector(), columns1);

		TableColumn firstColumn = table1.getColumnModel().getColumn(0);

		firstColumn.setCellRenderer(new SelectionFlag(configPanel));
		firstColumn.setMaxWidth(25);


		table2.setDefaultRenderer(Object.class, new SimpleTableSelectionRenderer());
		table2.setDefaultEditor(Object.class, null);

		Vector columns2 = new Vector();

		columns2.addElement(resourceBundle.getString("Name"));
		columns2.addElement(resourceBundle.getString("Value"));

		tableModel2.setDataVector(new Vector(), columns2);
	}

	public void setProtocol(String newProtocol)
	{
		if(protocol == null || !protocol.equals(newProtocol))
		{
			protocol = newProtocol;

			updateTaskTable();
		}
	}

	private void updateTaskTable()
	{
		int rowCount = tableModel1.getRowCount();

		if(rowCount > 0)
		{
			for(int i=0; i<rowCount; i++)
			{
				tableModel1.removeRow(0);
			}
		}

		Vector tasks = configPanel.configMainUI.getTasksForProtocol(protocol);

		if(tasks != null)
		{
			for(int i=0; i<tasks.size(); i++)
			{
				Vector rowData = new Vector();

				rowData.addElement(new MultipleListSelectionObject(new Object()));
				rowData.addElement(tasks.elementAt(i));

				tableModel1.addRow(rowData);
			}
		}
	}

	public void valueChanged(ListSelectionEvent lse)
	{
		int row = table1.getSelectedRow();

		if(row != -1)
		{
			updateTaskDetailsTable((String)tableModel1.getValueAt(row, 1));
		}
	}

	private void updateTaskDetailsTable(String taskName)
	{
		int rowCount = tableModel2.getRowCount();

		if(rowCount > 0)
		{
			for(int i=0; i<rowCount; i++)
			{
				tableModel2.removeRow(0);
			}
		}

		Vector taskDetails = configPanel.configMainUI.getTaskDetails(taskName);

		if(taskDetails != null)
		{
			Vector row1 = new Vector();

			row1.addElement("Task Name");
			row1.addElement(taskDetails.elementAt(0));

			tableModel2.addRow(row1);

			Vector row2 = new Vector();

			row2.addElement("Task Description");
			row2.addElement(taskDetails.elementAt(1));

			tableModel2.addRow(row2);

			Vector row3 = new Vector();

			row3.addElement("Protocol");
			row3.addElement(taskDetails.elementAt(2));

			tableModel2.addRow(row3);

			Vector row4 = new Vector();

			row4.addElement("Template");
			row4.addElement(taskDetails.elementAt(3));

			tableModel2.addRow(row4);

			Vector row5 = new Vector();

			row5.addElement("Rollback");
			row5.addElement(taskDetails.elementAt(4));

			tableModel2.addRow(row5);

			Vector row6 = new Vector();

			row6.addElement("Last Execution Time");
			row6.addElement(taskDetails.elementAt(5));

			tableModel2.addRow(row6);
		}
	}

	public Vector getSelectedTasks()
	{
		return getSelectedItems(false);
	}

	public Vector getSelectedTemplates()
	{
		return getSelectedItems(true);
	}

	private Vector getSelectedItems(boolean isTemplate)
	{
		Vector selectedItems = new Vector();

		int rowCount = tableModel1.getRowCount();

		if(rowCount > 0)
		{
			for(int i=0; i<rowCount; i++)
			{
				MultipleListSelectionObject selectionObject = (MultipleListSelectionObject)tableModel1.getValueAt(i, 0);

				String item = (String)tableModel1.getValueAt(i, 1);

				if(selectionObject.getState()) 
				{
					if(isTemplate)
					{
						if(configPanel.configMainUI.isTemplate(item))
						{
							selectedItems.addElement(item);
						}
					}
					else
					{
						if(!configPanel.configMainUI.isTemplate(item))
						{
							selectedItems.addElement(item);
						}
					}

				}
			}
		}

		return selectedItems;
	}

	class MyMouseListener extends MouseAdapter
	{
		public void mousePressed(MouseEvent me)
		{
			int row = table1.rowAtPoint(me.getPoint());

			if(row != -1)
			{
				if(table1.columnAtPoint(me.getPoint()) == 0)
				{
					MultipleListSelectionObject selectionObject = (MultipleListSelectionObject)tableModel1.getValueAt(row, 0);

					if(selectionObject.getState())
					{
						selectionObject.setFalseState();
					}
					else
					{
						selectionObject.setTrueState();
					}

					table1.repaint();
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








