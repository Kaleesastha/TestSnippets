
//$Id: SnmpTableAdd.java,v 1.1 2006/08/29 13:56:51 build Exp $
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


public class SnmpTableAdd extends JDialog 
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
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel3 = null;
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
	javax.swing.JLabel templateLabel = null;
	javax.swing.JComboBox templateCombo = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	private ConfigPanel configPanel = null;
	private SnmpTablePanel snmpTablePanel = null;
	private SnmpScalarAdd snmpScalarAdd = null;
	private boolean isTemplate = false;
	private boolean fromSnmpTable = true;


	public SnmpTableAdd(ConfigPanel configPanel, SnmpTablePanel mainPanel,boolean template)
	{
		super(configPanel.configClientUtils.getParentDialog(mainPanel));

		this.configPanel = configPanel;
		snmpTablePanel = mainPanel;
		fromSnmpTable = true;
		isTemplate = template;
		
		applet = configPanel.applet;

		init();
		configInit();
	}

	public SnmpTableAdd(ConfigPanel configPanel, SnmpScalarAdd mibTree,boolean template)
	{
//		super(configPanel.configClientUtils.getParentDialog(mibTree));

		this.configPanel = configPanel;
		snmpScalarAdd = mibTree;
		fromSnmpTable = false;

		applet = configPanel.applet;

		init();
		configInit();
	}

	private void configInit()
	{
		tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("OID"),resourceBundle.getString("Value")});
		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);

		URL docBase = configPanel.applet.getDocumentBase();
		String test = docBase+"../images/taskconfig.png" ;

		ImageIcon image = configPanel.configClientUtils.getImage(test);
		JLabel3.setIcon(image);

		if(isTemplate)
		{
			templateCombo.setVisible(true);
			templateLabel.setVisible(true);
			commonLabel.setText(resourceBundle.getString("Place Holder"));
			templateCombo.addItem("NEInput");
			templateCombo.addItem("InventoryInput");
			templateCombo.addItem("UserInput");
			templateCombo.addItem("DataSourceParam");
			templateCombo.addItem("None");
		}
		else
		{
			templateCombo.setVisible(false);
			templateLabel.setVisible(false);
			commonLabel.setText(resourceBundle.getString("Value"));
		}

		configPanel.configClientUtils.centerWindow(this);

		//setting mnemonics
		updateButton.setMnemonic('U');
		okButton.setMnemonic('O');
		cancelButton.setMnemonic('C');
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
        this.setSize(getPreferredSize().width+528,getPreferredSize().height+438); 
          setTitle(resourceBundle.getString("SnmpTableAdd"));
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


          setTitle(resourceBundle.getString("SNMP Task Configuration"));

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
            JLabel1.setText(resourceBundle.getString("Snmp Task Configuration"));
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
            JLabel3.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

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
            templateLabel.setText(resourceBundle.getString("Value"));
            templateLabel.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+templateLabel,ex); 
          }

//<UserCode_Begin_Bean_templateLabel>

//<UserCode_End_Bean_templateLabel>

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
            cancelButton.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"OID","Value"});
//<UserCode_End_Bean_tableModel>
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+22,okButton.getPreferredSize().height+0));

  
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
        JLabel1= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
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
        templateLabel= new javax.swing.JLabel();
        templateCombo= new javax.swing.JComboBox();
        JPanel4= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
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
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JLabel1);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel3.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JPanel5,cons);
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
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel6.add(commonLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6.add(oidText,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6.add(valueText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,2,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JPanel7,cons);
JPanel7.setLayout(new FlowLayout(2,5,5));
JPanel7.add(updateButton);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel6.add(templateLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6.add(templateCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(1,5,5));
JPanel4.add(okButton);
JPanel4.add(cancelButton);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      cancelButton.addActionListener(JButton2_JButton2_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      updateButton.addActionListener(JButton3_JButton3_conn11);
      valueText_valueText_conn1 valueText_valueText_conn11 =  new valueText_valueText_conn1();
      valueText.addKeyListener(valueText_valueText_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      okButton.addActionListener(JButton1_JButton1_conn11);
  
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





	public SnmpTableAdd()
  {
		//<Begin_SnmpTableAdd>
    pack();
  
    //<End_SnmpTableAdd>
	}

	public SnmpTableAdd(java.applet.Applet applet)
  {
		//<Begin_SnmpTableAdd_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_SnmpTableAdd_java.applet.Applet>
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
	//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  updateButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>
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

	public void cancelButtonActionPerformed()
	{
		this.setVisible(false);
		this.dispose();
	}

	public void okButtonActionPerformed()
	{
		String invalid_value_columns = "";
		
		for(int i=0; i<tableModel.getRowCount(); i++)
		{
			String column = (String)tableModel.getValueAt(i, 0);
			
			String value = tableModel.getValueAt(i,1).toString().trim();

			String numerioid = configPanel.mibHandler.getNumericOID(column);

			if(value.equals("") || !configPanel.mibHandler.isValidOIDValue(configPanel.mibHandler.getNumericOID(column), value))
			{
				invalid_value_columns += column+", ";
			}
		}
		if(!isTemplate)
		{
		    if(!invalid_value_columns.equals(""))
			{
			    invalid_value_columns = invalid_value_columns.substring(0, (invalid_value_columns.length()-2));
			    
			    JOptionPane.showMessageDialog(this,resourceBundle.getString("Please enter valid values for ")+ invalid_value_columns);
			
			    return;
			}
		}

		Vector valueVector = new Vector();
		
		for(int i=0;i<tableModel.getRowCount();i++)
		{
			valueVector.addElement(tableModel.getValueAt(i,1).toString());
		}
		
		if(fromSnmpTable)
		{
			snmpTablePanel.updateEntries(valueVector);
			snmpTablePanel.modifyRowButton.setEnabled(true);
			snmpTablePanel.deleteRowButton.setEnabled(true);
			snmpTablePanel.table.setRowSelectionInterval(snmpTablePanel.tableModel.getRowCount()-1, snmpTablePanel.tableModel.getRowCount()-1);
		}
		else
		{
			snmpScalarAdd.updateEntries(valueVector);
		}
		this.setVisible(false);
		this.dispose();
	}

	public void updateButtonActionPerformed()
	{
		int row = table.getSelectedRow();
		if(row != -1)
		{
			String value = valueText.getText().trim();
			if(value.equals(""))
			{
				return;
			}
			if(isTemplate)
			{
				String inputType = (String)templateCombo.getSelectedItem();
			
				if(!inputType.equals("None"))
				{
					value = "$"+inputType+"$"+value; 
				}
			}
			tableModel.setValueAt(value,row,1);
			if(row != tableModel.getRowCount()-1)
			{
				giveNewSelection(row+1);
			}
			else
				giveNewSelection(0);
		}
	}

	public void giveNewSelection(int row)
	{
		table.getSelectionModel().clearSelection();
		table.getSelectionModel().addSelectionInterval(row,row);

		tableMousePressedEvent();
	}

	public void populateUI(Vector columnVector)
	{
		for(int i=0;i<columnVector.size();i++)
		{
			Vector temp = new Vector();
			temp.addElement(columnVector.elementAt(i));
			temp.addElement("");
			tableModel.addRow(temp);
		}
		if(table.getRowCount() != 0)
			table.getSelectionModel().addSelectionInterval(0,0);
		tableMousePressedEvent();
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

	public void tableMousePressedEvent()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			String oid = (String)tableModel.getValueAt(row,0);
			String value = (String)tableModel.getValueAt(row,1);	

			oidText.setText(oid);

			if(isTemplate)
			{
				if(value != null && !value.trim().equals(""))
				{
					if(value.indexOf("NEInput") != -1)
						templateCombo.setSelectedItem("NEInput");
					else if(value.indexOf("InventoryInput") != -1)
						templateCombo.setSelectedItem("InventoryInput");
					else if(value.indexOf("UserInput") != -1)	
						templateCombo.setSelectedItem("UserInput");
					else if(value.indexOf("DataSourceParam") != -1)	
						templateCombo.setSelectedItem("DataSourceParam");
					else
						templateCombo.setSelectedItem("None");

					if(value.lastIndexOf('$') != -1)
					{
						value = value.substring(value.lastIndexOf("$")+1);
					}
				}
				else
				{
					value = oid;
				}
			}

			valueText.setText(value);
		}
	}


	//<Begin__class_valueText_valueText_conn1>

 class valueText_valueText_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_valueText_valueText_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyPressed( java.awt.event.KeyEvent arg0)
     {
  valueTextKeyPressedEvent(arg0);
     }
//<UserCode_End_Connection_valueText_valueText_conn1>
 }//<End__class_valueText_valueText_conn1>

	public void valueTextKeyPressedEvent(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_ENTER)
		{
			updateButtonActionPerformed();
		}
	}

	public void populateUIForModify(Vector columnVector,Vector dataVector)
	{
		for(int i=0;i<columnVector.size();i++)
		{
			Vector temp = new Vector();
			temp.addElement(columnVector.elementAt(i));
			temp.addElement(dataVector.elementAt(i));
			tableModel.addRow(temp);
		}
		if(table.getRowCount() != 0)
			table.getSelectionModel().addSelectionInterval(0,0);
		tableMousePressedEvent();
	}





 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}















