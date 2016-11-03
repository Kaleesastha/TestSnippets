
//$Id: RollbackPanel.java,v 1.1.4.1 2013/07/11 12:29:30 wesley Exp $
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

public class RollbackPanel extends JPanel 
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
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JCheckBox configCheck = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTable table2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextArea JTextArea2 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	javax.swing.table.DefaultTableModel tableModel2 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;

	private String protocol = null;
	private String taskName = null;


	public RollbackPanel(ConfigPanel configPanel, String protocol)
	{
		this(configPanel, protocol, null);
	}

	public RollbackPanel(ConfigPanel configPanel, String protocol, String taskName)
	{
		this.configPanel = configPanel;

		this.protocol = protocol;
		this.taskName = taskName;

		applet = configPanel.applet;

		init();
		configInit();
	}

	private void configInit()
	{
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//JList1.setCellRenderer(new SingleSelectionListRenderer(configPanel));
		Vector v = new Vector();
		v.addElement(" ");
		v.addElement(resourceBundle.getString("Available Tasks For Rollback"));
		tableModel.setDataVector(new Vector(),v);
		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);
		TableColumn firstColumn = table.getColumnModel().getColumn(0);
		firstColumn.setCellRenderer(new SelectionFlag(configPanel));
		firstColumn.setMaxWidth(25);
		refreshList();

		Vector v1 = new Vector();
		v1.addElement(resourceBundle.getString("Name"));
		v1.addElement(resourceBundle.getString("Value"));
		tableModel2.setDataVector(new Vector(),v1);

		for(int i=0;i<6;i++)
		{
			Vector temp = new Vector();
			if(i==0)
				temp.addElement(resourceBundle.getString("Task Name"));
			else if(i ==1)
				temp.addElement(resourceBundle.getString("Task Description"));
			else if(i==2)
				temp.addElement(resourceBundle.getString("Protocol"));
			else if(i==3)
				temp.addElement(resourceBundle.getString("Template"));
			else if(i==4)
				temp.addElement(resourceBundle.getString("Rollback Configuration"));
			else
				temp.addElement(resourceBundle.getString("Last Executed Time"));
			temp.addElement("");
			tableModel2.addRow(temp);
		}

		table2.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table2.setDefaultEditor(Object.class,null);
		
		if(protocol.equals("netconf"))
		{
			JLabel3.setEnabled(false);
			configCheck.setVisible(false);
		}
		else
		{
			JLabel3.setEnabled(true);
			configCheck.setVisible(true);
		}
	}



	public RollbackPanel()
  {
		//<Begin_RollbackPanel>
    this.init();
  
    //<End_RollbackPanel>
	}

	public RollbackPanel(java.applet.Applet applet)
  {
		//<Begin_RollbackPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_RollbackPanel_java.applet.Applet>
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
            JLabel1.setText(resourceBundle.getString("Rollback Configuration"));
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
            JPanel2.setBorder(new javax.swing.border.EtchedBorder(0));
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
        	
            JLabel3.setText(resourceBundle.getString("Use Current configuration for Rollback  "));
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
            table2.setModel(tableModel2);
            table2.setRowHeight(21);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table2,ex); 
          }

//<UserCode_Begin_Bean_table2>

//<UserCode_End_Bean_table2>

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
            JTextArea2.setEditable(false);
            JTextArea2.setLineWrap(true);
            JTextArea2.setWrapStyleWord(true);
            JTextArea2.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea2,ex); 
          }

//<UserCode_Begin_Bean_JTextArea2>

//<UserCode_End_Bean_JTextArea2>
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+2,JScrollPane1.getPreferredSize().height+2));

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+550,getPreferredSize().height+422)); 
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
	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      JCheckBox1_JCheckBox1_conn1 JCheckBox1_JCheckBox1_conn11 =  new JCheckBox1_JCheckBox1_conn1();
      configCheck.addActionListener(JCheckBox1_JCheckBox1_conn11);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
  
      //<End_setUpConnections>
	} 
	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel6= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        configCheck= new javax.swing.JCheckBox();
        JPanel5= new javax.swing.JPanel();
        JScrollPane2= new javax.swing.JScrollPane();
        table2= new javax.swing.JTable();
        JPanel3= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JTextArea2= new javax.swing.JTextArea();
        tableModel= new javax.swing.table.DefaultTableModel();
        tableModel2= new javax.swing.table.DefaultTableModel();

  
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
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JLabel1);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridLayout(1,0,5,5));
JPanel2.add(JPanel4);
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(table);
JPanel4.add(JPanel6,BorderLayout.NORTH);
JPanel6.setLayout(new FlowLayout(0,5,5));
JPanel6.add(JLabel3);
JPanel6.add(configCheck);
JPanel2.add(JPanel5);
JPanel5.setLayout(new BorderLayout(5,5));
JPanel5.add(JScrollPane2,BorderLayout.CENTER);
JScrollPane2.getViewport().add(table2);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JTextArea2,cons);

  
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
	//<Begin__class_JCheckBox1_JCheckBox1_conn1>

 class JCheckBox1_JCheckBox1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox1_JCheckBox1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  checkBoxActionPerformed();
     }
//<UserCode_End_Connection_JCheckBox1_JCheckBox1_conn1>
 }//<End__class_JCheckBox1_JCheckBox1_conn1>




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

	public void checkBoxActionPerformed()
	{
		if(configCheck.isSelected())
		{
			JPanel2.setEnabled(false);
			table.getSelectionModel().clearSelection();
			table.setEnabled(false);
			Vector v = new Vector();
			for(int i=0;i<6;i++)
				v.addElement("");
			fillTheTable(v);

			for(int i=0; i<table.getRowCount(); i++)
			{
				((MultipleListSelectionObject)table.getValueAt(i, 0)).setFalseState();
			}
		}
		else
		{
			JPanel2.setEnabled(true);
			table.setEnabled(true);
		}
	}

	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	public void refreshList()
	{
		Vector tasks = configPanel.configMainUI.getProtocolSpecificTasks(protocol);
		
		if(protocol.equals("netconf"))
		{
			JLabel3.setEnabled(false);
			configCheck.setVisible(false);
		}
		else
		{
			JLabel3.setEnabled(true);
			configCheck.setVisible(true);
		}

		int count = tableModel.getRowCount();			
		if(count !=0)
		{
			for(int j=count-1;j >=0;j--)
			{
				tableModel.removeRow(j);
			}
		}	

		int count2 = tableModel2.getRowCount();
		if(count2 !=0)
		{
			for(int j=count2-1;j >=0;j--)
			{
				tableModel2.removeRow(j);
			}
		}
		for(int i=0;i<6;i++)
		{
			Vector temp = new Vector();
			if(i==0)
				temp.addElement(resourceBundle.getString("Task Name"));
			else if(i ==1)
				temp.addElement(resourceBundle.getString("Task Description"));
			else if(i==2)
				temp.addElement(resourceBundle.getString("Protocol"));
			else if(i==3)
				temp.addElement(resourceBundle.getString("Template"));
			else if(i==4)
				temp.addElement(resourceBundle.getString("Rollback Configuration"));
			else
				temp.addElement(resourceBundle.getString("Last Executed Time"));
			temp.addElement("");
			tableModel2.addRow(temp);
		}	

		if(tasks != null && tasks.size()>0)
		{
			if(taskName != null)
			{
				tasks.removeElement(taskName);
			}


			for(int i=0;i<tasks.size();i++)
			{
				Vector v = new Vector();
				v.addElement(new MultipleListSelectionObject(new Object()));
				v.addElement(tasks.elementAt(i));
				tableModel.addRow(v);
			}
			//JList1.setListData(tasks);	
		}
		else 
		{
			//JList1.setListData(new Vector());
		}

		//JList1.updateUI();
	}





	public String getRollbackDocument()
	{
		String rollbackDocument = "";

		if(configCheck.isSelected())
		{
			rollbackDocument = "Current Configuration";
		}
		else
		{
			for(int i=0;i<table.getRowCount();i++)
			{
				MultipleListSelectionObject object = (MultipleListSelectionObject)table.getValueAt(i,0);

				if(object.getState())
				{
					rollbackDocument = table.getValueAt(i,1).toString();
					break;
				}
			}
			//rollbackDocument = (String)JList1.getSelectedValue();
		}

		return rollbackDocument;
	}	

	public void setRollbackDocument(String rollbackDocument)
	{
		if(rollbackDocument != null)
		{
			if(rollbackDocument.equalsIgnoreCase("Current Configuration"))
			{
				configCheck.setSelected(true);
				table.setEnabled(false);
			}
			else
			{
				//JList1.setSelectedValue(rollbackDocument, false);
				for(int i=0;i<table.getRowCount();i++)
				{
					String str = table.getValueAt(i,1).toString();
					if(str.equals(rollbackDocument))
					{
						((MultipleListSelectionObject)table.getValueAt(i,0)).setTrueState();
						break;
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
		Point point = me.getPoint();		
		if(table.isEnabled())
		{
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
			}	
			table.repaint();
		}
		if(table.getSelectedRow() != -1)
		{
			String str = table.getValueAt(table.getSelectedRow(),1).toString();
			Vector v = configPanel.configMainUI.getTaskDetails(str);
			fillTheTable(v);
		}
	}	

	public void fillTheTable(Vector data)
	{
		for(int i=0;i<6;i++)
		{
			tableModel2.setValueAt(data.elementAt(i),i,1);			
		}
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}















