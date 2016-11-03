
//$Id: DataSourceParamsConfig.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;


import java.util.*;

import javax.swing.event.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class DataSourceParamsConfig extends JDialog implements ListSelectionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JButton helpButton = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel commonLabel = null;
	javax.swing.JTextField oidText = null;
	javax.swing.JTextField valueText = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JButton updateButton = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>



	public DataSourceParamsConfig()
  {
		//<Begin_DataSourceParamsConfig>
    pack();
  
    //<End_DataSourceParamsConfig>
	}

	public DataSourceParamsConfig(java.applet.Applet applet)
  {
		//<Begin_DataSourceParamsConfig_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_DataSourceParamsConfig_java.applet.Applet>
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
        this.setSize(getPreferredSize().width+441,getPreferredSize().height+413); 
          setTitle(resourceBundle.getString("DataSourceParamsConfig"));
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
            JLabel1.setText(resourceBundle.getString("Configure Data Source Paramters "));
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

          try
          {
            JPanel5.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            table.setRowHeight(21);
            table.setModel(tableModel);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

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
            JLabel4.setText(resourceBundle.getString("OID"));
            JLabel4.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            commonLabel.setText(resourceBundle.getString("Value"));
            commonLabel.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+commonLabel,ex); 
          }

//<UserCode_Begin_Bean_commonLabel>

//<UserCode_End_Bean_commonLabel>

          try
          {
            oidText.setEditable(false);
            oidText.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+oidText,ex); 
          }

//<UserCode_Begin_Bean_oidText>

//<UserCode_End_Bean_oidText>

          try
          {
            updateButton.setText(resourceBundle.getString("Update"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+updateButton,ex); 
          }

//<UserCode_Begin_Bean_updateButton>

//<UserCode_End_Bean_updateButton>

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
            JButton1.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JButton2.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+22,JButton1.getPreferredSize().height+0));

  
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
        JPanel4= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel8= new javax.swing.JPanel();
        helpButton= new javax.swing.JButton();
        JPanel2= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel6= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        commonLabel= new javax.swing.JLabel();
        oidText= new javax.swing.JTextField();
        valueText= new javax.swing.JTextField();
        JPanel7= new javax.swing.JPanel();
        updateButton= new javax.swing.JButton();
        JLabel2= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
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
JPanel1.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel4.add(JLabel1,cons);
inset = new Insets(5,5,5,20);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JPanel8,cons);
JPanel8.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel8.add(helpButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel5.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel6.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel6.add(commonLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6.add(oidText,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6.add(valueText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,2,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JPanel7,cons);
JPanel7.setLayout(new FlowLayout(2,5,5));
JPanel7.add(updateButton);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel2.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(JButton1);
JPanel3.add(JButton2);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      updateButton_updateButton_conn1 updateButton_updateButton_conn11 =  new updateButton_updateButton_conn1();
      updateButton.addActionListener(updateButton_updateButton_conn11);
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





	//<Begin__class_table_table_conn1>

 class table_table_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_table_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  tableMousePressedEvent();
     }
//<UserCode_End_Connection_table_table_conn1>
 }//<End__class_table_table_conn1>
	//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  cancelButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>
	//<Begin__class_updateButton_updateButton_conn1>

 class updateButton_updateButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_updateButton_updateButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  updateButtonActionPerformed();
     }
//<UserCode_End_Connection_updateButton_updateButton_conn1>
 }//<End__class_updateButton_updateButton_conn1>
	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  okButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>


	private ConfigPanel configPanel = null;

	private DataSourceForExecution dataSourceForExecution = null;	

	private TemplatesConfiguration templatesConfiguration = null;

	private String templateWithDS = null;

	private Properties dataSourceParams = null;

	public DataSourceParamsConfig(ConfigPanel configPanel, DataSourceForExecution dataSourceForExecution, Properties dataSourceParams)
	{
		super(configPanel.configClientUtils.getParentDialog(dataSourceForExecution));

		this.configPanel = configPanel;
		this.dataSourceForExecution = dataSourceForExecution;
		this.dataSourceParams = dataSourceParams;
		
		applet = configPanel.applet;

		init();
		configInit();
	}

	public DataSourceParamsConfig(ConfigPanel configPanel, TemplatesConfiguration templatesConfiguration, Properties dataSourceParams, String templateWithDS)
	{
		super(configPanel.configClientUtils.getParentDialog(templatesConfiguration));

		this.configPanel = configPanel;

		this.templatesConfiguration = templatesConfiguration;
		this.templateWithDS = templateWithDS;

		this.dataSourceParams = dataSourceParams;
		applet = configPanel.applet;

		init();
		configInit();
	}

	private void configInit()
	{
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);

		table.setDefaultRenderer(Object.class, new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class, null);

		helpButton.setVisible(false);

		oidText.setEditable(false);
		oidText.setOpaque(true);
		oidText.setDisabledTextColor(Color.black);

		JLabel2.setIcon(configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/taskconfig.png"));

		JLabel4.setText(resourceBundle.getString("Key"));

		Vector columns = new Vector();

		columns.addElement(resourceBundle.getString("Key"));
		columns.addElement(resourceBundle.getString("Value"));

		Vector data = new Vector();

		if(dataSourceParams != null)
		{
			for(Enumeration enumerate = dataSourceParams.keys(); enumerate.hasMoreElements();)
			{
				Vector rowData = new Vector();

				Object key = enumerate.nextElement();
				Object value = dataSourceParams.get(key);

				rowData.addElement(key);
				rowData.addElement(value);

				data.addElement(rowData);
			}
		}

		tableModel.setDataVector(data, columns);	

		setTitle(resourceBundle.getString("Configure Data Source Parameters"));

		configPanel.configClientUtils.centerWindow(this);
	}

	public void valueChanged(ListSelectionEvent lse)
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			oidText.setText((String)table.getValueAt(row, 0));			
			valueText.setText((String)table.getValueAt(row, 1));
		}
	}

	public void okButtonActionPerformed()
	{
		Vector updatedData = tableModel.getDataVector();

		if(dataSourceParams != null && updatedData != null)
		{
			for(int i=0; i<updatedData.size(); i++)
			{
				Vector rowData = (Vector)updatedData.elementAt(i);

				dataSourceParams.put(rowData.elementAt(0), rowData.elementAt(1));
			}
		}

		if(dataSourceForExecution != null)
		{
			dataSourceForExecution.setDataSourceParams(dataSourceParams);
		}
		else if(templatesConfiguration != null)
		{
			templatesConfiguration.setDataSourceParams(templateWithDS, dataSourceParams);
		}

		setVisible(false);
		dispose();
	}

	public void cancelButtonActionPerformed()
	{
		setVisible(false);
		dispose();
	}

	public void updateButtonActionPerformed()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			table.setValueAt(valueText.getText(), row, 1);
		}
	}

	public void tableMousePressedEvent()
	{}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}






