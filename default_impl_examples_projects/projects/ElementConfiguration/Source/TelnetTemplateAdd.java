
//$Id: TelnetTemplateAdd.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;


import java.net.URL;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class TelnetTemplateAdd extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1111 = null;
	javax.swing.JLabel JLabel1111 = null;
	javax.swing.JPanel JPanel3111 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel5111 = null;
	javax.swing.JScrollPane JScrollPane1111 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel6111 = null;
	javax.swing.JLabel JLabel4111 = null;
	javax.swing.JLabel valueLabel = null;
	javax.swing.JTextField nameText = null;
	javax.swing.JTextField valueText = null;
	javax.swing.JPanel JPanel7111 = null;
	javax.swing.JButton updateButton = null;
	javax.swing.JLabel templateLabel111 = null;
	javax.swing.JComboBox templateCombo = null;
	javax.swing.JPanel JPanel4111 = null;
	javax.swing.JButton okButton111 = null;
	javax.swing.JButton cancelButton111 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;
	private TelnetPanel telnetPanel = null;

	private boolean isModify = false;

	public TelnetTemplateAdd(ConfigPanel configPanel, TelnetPanel telnetPanel)
	{
		super(configPanel.configClientUtils.getParentDialog(telnetPanel));

		this.configPanel = configPanel;
		this.telnetPanel = telnetPanel;
		
		applet = configPanel.applet;

		init();
		configInit();

		populateData(null);
	}

	public TelnetTemplateAdd(ConfigPanel configPanel, TelnetPanel telnetPanel, Vector rowData)
	{
		super(configPanel.configClientUtils.getParentDialog(telnetPanel));

		this.configPanel = configPanel;
		this.telnetPanel = telnetPanel;
		
		applet = configPanel.applet;

		isModify = true;

		init();
		configInit();

		populateData(rowData);
	}

	private void configInit()
	{
		URL docBase = configPanel.applet.getDocumentBase();

		String test = docBase+"../images/taskconfig.png" ;
		ImageIcon image = configPanel.configClientUtils.getImage(test);
		JLabel2.setIcon(image);

		templateCombo.addItem("InventoryInput");
		templateCombo.addItem("UserInput");
		templateCombo.addItem("DataSourceParam");
		templateCombo.addItem("None");

		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);
		configPanel.configClientUtils.centerWindow(this);
	}

	private void populateData(Vector rowData)
	{
		for(int i=0; i<3; i++)
		{
			Vector v = new Vector();

			if(i == 0)
				v.add("Command");
			else if(i == 1)
				v.add("Command Arguments");
			else 	
				v.add("Prompt");

			if(isModify)
			{
				v.add(rowData.elementAt(i));
			}
			else
			{
				v.add("");
			}

			tableModel.addRow(v);
		}

		table.getSelectionModel().addSelectionInterval(0,0);
		tableMousePressedEvent();
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
        this.setSize(getPreferredSize().width+491,getPreferredSize().height+422); 
          setTitle(resourceBundle.getString("TelnetTemplateAdd"));
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

          setTitle(resourceBundle.getString("Telnet Task Configuration"));
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
            JPanel1111.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1111,ex); 
          }

//<UserCode_Begin_Bean_JPanel1111>

//<UserCode_End_Bean_JPanel1111>

          try
          {
            JLabel1111.setForeground(new Color(-16764109));
            JLabel1111.setText(resourceBundle.getString("Telnet Task Configuration"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1111,ex); 
          }

//<UserCode_Begin_Bean_JLabel1111>

//<UserCode_End_Bean_JLabel1111>

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
            JPanel5111.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5111,ex); 
          }

//<UserCode_Begin_Bean_JPanel5111>

//<UserCode_End_Bean_JPanel5111>

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
            JLabel4111.setText(resourceBundle.getString("Attribute Name"));
            JLabel4111.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4111,ex); 
          }

//<UserCode_Begin_Bean_JLabel4111>

//<UserCode_End_Bean_JLabel4111>

          try
          {
            valueLabel.setForeground(new Color(-16777216));
            valueLabel.setText(resourceBundle.getString("Place Holder"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+valueLabel,ex); 
          }

//<UserCode_Begin_Bean_valueLabel>

//<UserCode_End_Bean_valueLabel>

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
            templateLabel111.setText(resourceBundle.getString("Input Type"));
            templateLabel111.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+templateLabel111,ex); 
          }

//<UserCode_Begin_Bean_templateLabel111>

//<UserCode_End_Bean_templateLabel111>

          try
          {
            JPanel4111.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4111,ex); 
          }

//<UserCode_Begin_Bean_JPanel4111>

//<UserCode_End_Bean_JPanel4111>

          try
          {
            okButton111.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton111,ex); 
          }

//<UserCode_Begin_Bean_okButton111>

//<UserCode_End_Bean_okButton111>

          try
          {
            cancelButton111.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton111,ex); 
          }

//<UserCode_Begin_Bean_cancelButton111>

//<UserCode_End_Bean_cancelButton111>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Attribute Name","Value"});
//<UserCode_End_Bean_tableModel>
		okButton111.setPreferredSize(new Dimension(okButton111.getPreferredSize().width+22,okButton111.getPreferredSize().height+0));

  
          //<End_setUpProperties>
		  tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("Attribute Name"),resourceBundle.getString("Value")});
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
        JPanel1111= new javax.swing.JPanel();
        JLabel1111= new javax.swing.JLabel();
        JPanel3111= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JPanel5111= new javax.swing.JPanel();
        JScrollPane1111= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel6111= new javax.swing.JPanel();
        JLabel4111= new javax.swing.JLabel();
        valueLabel= new javax.swing.JLabel();
        nameText= new javax.swing.JTextField();
        valueText= new javax.swing.JTextField();
        JPanel7111= new javax.swing.JPanel();
        updateButton= new javax.swing.JButton();
        templateLabel111= new javax.swing.JLabel();
        templateCombo= new javax.swing.JComboBox();
        JPanel4111= new javax.swing.JPanel();
        okButton111= new javax.swing.JButton();
        cancelButton111= new javax.swing.JButton();
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
Top.add(JPanel1111,cons);
JPanel1111.setLayout(new FlowLayout(1,5,5));
JPanel1111.add(JLabel1111);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel3111,cons);
JPanel3111.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel3111.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3111.add(JPanel5111,cons);
JPanel5111.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel5111.add(JScrollPane1111,cons);
JScrollPane1111.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5111.add(JPanel6111,cons);
JPanel6111.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel6111.add(JLabel4111,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel6111.add(valueLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6111.add(nameText,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6111.add(valueText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,2,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6111.add(JPanel7111,cons);
JPanel7111.setLayout(new FlowLayout(2,5,5));
JPanel7111.add(updateButton);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel6111.add(templateLabel111,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6111.add(templateCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4111,cons);
JPanel4111.setLayout(new FlowLayout(1,5,5));
JPanel4111.add(okButton111);
JPanel4111.add(cancelButton111);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      cancelButton111_cancelButton111_conn1 cancelButton111_cancelButton111_conn11 =  new cancelButton111_cancelButton111_conn1();
      cancelButton111.addActionListener(cancelButton111_cancelButton111_conn11);
      updateButton_updateButton_conn1 updateButton_updateButton_conn11 =  new updateButton_updateButton_conn1();
      updateButton.addActionListener(updateButton_updateButton_conn11);
      okButton111_okButton111_conn1 okButton111_okButton111_conn11 =  new okButton111_okButton111_conn1();
      okButton111.addActionListener(okButton111_okButton111_conn11);
      templateCombo1_templateCombo1_conn1 templateCombo1_templateCombo1_conn11 =  new templateCombo1_templateCombo1_conn1();
      templateCombo.addActionListener(templateCombo1_templateCombo1_conn11);
      table_JScrollPane1111_conn1 table_JScrollPane1111_conn11 =  new table_JScrollPane1111_conn1();
      table.addMouseListener(table_JScrollPane1111_conn11);
  
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





	public TelnetTemplateAdd()
  {
		//<Begin_TelnetTemplateAdd>
    pack();
  
    //<End_TelnetTemplateAdd>
	}

	public TelnetTemplateAdd(java.applet.Applet applet)
  {
		//<Begin_TelnetTemplateAdd_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_TelnetTemplateAdd_java.applet.Applet>
	}




	//<Begin__class_cancelButton111_cancelButton111_conn1>

 class cancelButton111_cancelButton111_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_cancelButton111_cancelButton111_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  cancelButtonActionPerformed();
     }
//<UserCode_End_Connection_cancelButton111_cancelButton111_conn1>
 }//<End__class_cancelButton111_cancelButton111_conn1>
	//<Begin__class_okButton111_okButton111_conn1>

 class okButton111_okButton111_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_okButton111_okButton111_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  okButtonActionPerformed();
     }
//<UserCode_End_Connection_okButton111_okButton111_conn1>
 }//<End__class_okButton111_okButton111_conn1>
	//<Begin__class_templateCombo1_templateCombo1_conn1>

 class templateCombo1_templateCombo1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_templateCombo1_templateCombo1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  comboActionPerformed();
     }
//<UserCode_End_Connection_templateCombo1_templateCombo1_conn1>
 }//<End__class_templateCombo1_templateCombo1_conn1>

	public void comboActionPerformed()
	{
		if(templateCombo.getSelectedItem().toString().equals("None"))
		{
			valueLabel.setText(resourceBundle.getString("Value"));
		}
		else
		{
			valueLabel.setText(resourceBundle.getString("Place Holder"));
		}		
	}

	public void okButtonActionPerformed()
	{
		String command = (String)tableModel.getValueAt(0, 1);
		String commandArguments = (String)tableModel.getValueAt(1, 1);
		String prompt = (String)tableModel.getValueAt(2, 1);

		if(command.equals(""))
		{
			configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Some of the fields are left empty. Please fill them"), resourceBundle.getString("Info"), "Info");

			return;
		}

		if(isModify)
		{
			telnetPanel.updateRow(command, commandArguments, prompt);
		}
		else
		{
			telnetPanel.addRow(command, commandArguments, prompt);
		}

		this.setVisible(false);
		this.dispose();
	}

	public void cancelButtonActionPerformed()
	{
		this.setVisible(false);
		this.dispose();
	}


	//<Begin__class_table_JScrollPane1111_conn1>

 class table_JScrollPane1111_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_JScrollPane1111_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  tableMousePressedEvent();
     }
//<UserCode_End_Connection_table_JScrollPane1111_conn1>
 }//<End__class_table_JScrollPane1111_conn1>

	public void tableMousePressedEvent()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			nameText.setText((String)table.getValueAt(row,0));

			String value = (String)table.getValueAt(row,1);	

			if(value != null && !(value.trim()).equals(""))
			{
				if(value.indexOf("InventoryInput") != -1)
					templateCombo.setSelectedItem("InventoryInput");
				else if(value.indexOf("UserInput") != -1)
					templateCombo.setSelectedItem("UserInput");
				else if(value.indexOf("DataSourceParam") != -1)
					templateCombo.setSelectedItem("DataSourceParam");
				else
					templateCombo.setSelectedItem("None");

				if(value.lastIndexOf('$') != -1)	
				{
					value = value.substring(value.lastIndexOf('$')+1);
				}
			}

			valueText.setText(value);
		}
	}






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

	public void updateButtonActionPerformed()
	{
		int row = table.getSelectedRow();
		if(row != -1)
		{
			String value = valueText.getText().trim();

			if(!value.equals(""))
			{
				String inputType = (String)templateCombo.getSelectedItem();

				if(!inputType.equals("None"))
				{
					value = "$"+inputType+"$"+value;
				}

				table.setValueAt(value, row, 1);

				if(row != table.getRowCount()-1)
				{
					giveNewSelection(row+1);
				}
				else
				{
					giveNewSelection(0);
				}
			}
		}
	}

	public void giveNewSelection(int row)
	{
		table.getSelectionModel().clearSelection();
		table.getSelectionModel().addSelectionInterval(row,row);

		tableMousePressedEvent();				
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}














