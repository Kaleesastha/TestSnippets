
//$Id: TelnetPanel.java,v 1.2 2007/07/31 10:33:46 karanmercy Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class TelnetPanel extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;

	private boolean isTemplate = false;


	public TelnetPanel(ConfigPanel panel, boolean template)
	{
		isTemplate = template;
		configPanel = panel;
		
		applet = configPanel.applet;
		
		init();
		configInit();
	}

	private void configInit()
	{
		table.setDefaultRenderer(Object.class, new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);	

		Vector columns = new Vector();

		columns.addElement(resourceBundle.getString("Command"));
		columns.addElement(resourceBundle.getString("Command Arguments"));
		columns.addElement(resourceBundle.getString("Prompt"));

		tableModel.setDataVector(new Vector(), columns);
	}

	public TelnetPanel()
  {
		//<Begin_TelnetPanel>
    this.init();
  
    //<End_TelnetPanel>
	}

	public TelnetPanel(java.applet.Applet applet)
  {
		//<Begin_TelnetPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_TelnetPanel_java.applet.Applet>
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
            JPanel2.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JButton1.setText(resourceBundle.getString("Add"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JButton2.setText(resourceBundle.getString("Modify"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            JButton3.setText(resourceBundle.getString("Delete"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>

//<UserCode_End_Bean_JButton3>

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
            JTextArea1.setOpaque(false);
            JTextArea1.setEditable(false);
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setLineWrap(true);
            JTextArea1.setText(resourceBundle.getString("Use the buttons given below the table to configure the Attributes for this task."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
          }

//<UserCode_Begin_Bean_JTextArea1>

//<UserCode_End_Bean_JTextArea1>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Command","Prompt"});
//<UserCode_End_Bean_tableModel>
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+14,JButton1.getPreferredSize().height+0));

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+549,getPreferredSize().height+416)); 
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

      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      JButton3.addActionListener(JButton3_JButton3_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
	} 
	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel2= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JTextArea1= new javax.swing.JTextArea();
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
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(JButton1);
JPanel2.add(JButton2);
JPanel2.add(JButton3);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JTextArea1,cons);

  
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
	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  addbuttonaction();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>




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

	public void addbuttonaction()
	{
		if(isTemplate)
		{
			TelnetTemplateAdd ta = new TelnetTemplateAdd(configPanel,this);
			ta.setVisible(true);
		}
		else
		{
			TelnetAdd ta = new TelnetAdd(configPanel,this);
			ta.setVisible(true);
		}
	}

	public void addRow(String command, String commandArguments, String prompt)
	{
		Vector rowData = new Vector();

		rowData.addElement(command);
		rowData.addElement(commandArguments);
		rowData.addElement(prompt);

		tableModel.addRow(rowData);
	}

	public void updateRow(String command, String commandArguments, String prompt)
	{
		int row = table.getSelectedRow();	

		if(row != -1)
		{
			tableModel.setValueAt(command, row, 0);
			tableModel.setValueAt(commandArguments, row, 1);
			tableModel.setValueAt(prompt, row, 2);
		}
	}


	//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  modifyButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>

	public void modifyButtonActionPerformed()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			Vector rowData = new Vector();

			for(int i=0; i<tableModel.getColumnCount(); i++)
			{
				rowData.addElement(tableModel.getValueAt(row, i));
			}

			if(isTemplate)
			{
				TelnetTemplateAdd templateAdd = new TelnetTemplateAdd(configPanel, this, rowData);
				templateAdd.setVisible(true);
			}
			else
			{
				TelnetAdd add = new TelnetAdd(configPanel, this, rowData);
				add.setVisible(true);
			}
		}
	}


	//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  deleteButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>

	public void deleteButtonActionPerformed()
	{
		int [] selectedRows = table.getSelectedRows();
		int length = selectedRows.length;
		if(length == 0)
		{
			return;
		}
		//JOptionPane jp = new JOptionPane(); 
		//int option =  jp.showConfirmDialog(null, resourceBundle.getString("Are you sure about deleting the selected item(s)"),null,jp.YES_NO_OPTION);
		if (configPanel.configClientUtils.getConfirmation(this))
		{ 
			for(int j=length-1;j >=0;j--)
			{
				tableModel.removeRow(selectedRows[j]);
			} 
		}
	}

	public Vector getAllValues()
	{
		return tableModel.getDataVector();
	}

	public void setValues(Vector data)
	{
		Vector columns = new Vector();

		columns.addElement(resourceBundle.getString("Command"));
		columns.addElement(resourceBundle.getString("Command Arguments"));
		columns.addElement(resourceBundle.getString("Prompt"));

		tableModel.setDataVector(data, columns);
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
















