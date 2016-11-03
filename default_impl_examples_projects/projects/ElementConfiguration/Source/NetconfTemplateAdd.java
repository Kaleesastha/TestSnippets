
//$Id: NetconfTemplateAdd.java,v 1.1.4.2 2013/07/11 12:29:30 wesley Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$19
//<End_Version>
package com.adventnet.nms.config;


import java.net.URL;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.management.config.netconf.NetconfAttributeConstants;

public class NetconfTemplateAdd extends JDialog
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";//No I18N
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
	javax.swing.JComboBox operationCombo = null;
	javax.swing.JPanel JPanel4111 = null;
	javax.swing.JButton okButton111 = null;
	javax.swing.JButton cancelButton111 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;
	private NetconfPanel netconfPanel = null;

	private boolean isModify = false;

	public NetconfTemplateAdd(ConfigPanel configPanel, NetconfPanel netconfPanel)
	{
		super(configPanel.configClientUtils.getParentDialog(netconfPanel));

		this.configPanel = configPanel;
		this.netconfPanel = netconfPanel;

		applet = configPanel.applet;

		init();
		configInit();

		populateData(null);
	}

	public NetconfTemplateAdd(ConfigPanel configPanel, NetconfPanel netconfPanel, Vector rowData)
	{
		super(configPanel.configClientUtils.getParentDialog(netconfPanel));

		this.configPanel = configPanel;
		this.netconfPanel = netconfPanel;

		applet = configPanel.applet;

		isModify = true;

		init();
		configInit();

		populateData(rowData);
	}

	private void configInit()
	{
		URL docBase = configPanel.applet.getDocumentBase();

		String test = docBase+"../images/taskconfig.png" ;//No I18N
		ImageIcon image = configPanel.configClientUtils.getImage(test);
		JLabel2.setIcon(image);

		templateCombo.addItem("InventoryInput");//No I18N
		templateCombo.addItem("UserInput");//No I18N
		templateCombo.addItem("DataSourceParam");//No I18N
		templateCombo.addItem("None");//No I18N
		templateCombo.setVisible(false);
		
		operationCombo.addItem("get-config");//No I18N
		operationCombo.addItem("edit-config");//No I18N
		operationCombo.addItem("copy-config");//No I18N
		operationCombo.addItem("delete-config");//No I18N

		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);
		configPanel.configClientUtils.centerWindow(this);
	}

	private void populateData(Vector rowData)
	{
		for(int i=0; i<NetconfAttributeConstants.ATTRIBUTE_KEYS.length; i++)
		{
			Vector v = new Vector();

			v.add(resourceBundle.getString(NetconfAttributeConstants.ATTRIBUTE_KEYS[i]));
			if(isModify)
			{
				v.add(rowData.elementAt(i));
			}
			else
			{
				v.add("");//No I18N
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
	if(getParameter("RESOURCE_PROPERTIES" ) != null)//No I18N
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");//No I18N
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);//No I18N
        if (initialized) return;
        this.setSize(getPreferredSize().width+589,getPreferredSize().height+461);
          setTitle(resourceBundle.getString("NetconfTemplateAdd"));//No I18N
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
          showStatus(resourceBundle.getString("Error in init method"),ex); //No I18N
        }
        // let us set the initialized variable to true so
        // we dont initialize again even if init is called
        initialized = true;


         //<End_init>

          setTitle(resourceBundle.getString("Netconf Task Configuration"));//No I18N
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
            if (input.equals("HOST")) value = "localhost"; //No I18N
            if (input.equals("PORT")) value = "161"; //No I18N
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; //No I18N
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
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1111,ex); //No I18N
          }

//<UserCode_Begin_Bean_JPanel1111>

//<UserCode_End_Bean_JPanel1111>

          try
          {
            JLabel1111.setForeground(new Color(-16764109));
            JLabel1111.setText(resourceBundle.getString("Netconf Task Configuration"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1111,ex); //No I18N
          }

//<UserCode_Begin_Bean_JLabel1111>

//<UserCode_End_Bean_JLabel1111>

          try
          {
            JLabel2.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); //No I18N
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JPanel5111.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5111,ex); //No I18N
          }

//<UserCode_Begin_Bean_JPanel5111>

//<UserCode_End_Bean_JPanel5111>

          try
          {
            table.setModel(tableModel);
            table.setPreferredScrollableViewportSize(new Dimension(200,100));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); //No I18N
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

          try
          {
            updateButton.setText(resourceBundle.getString("Update"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+updateButton,ex); //No I18N
          }

//<UserCode_Begin_Bean_updateButton>

//<UserCode_End_Bean_updateButton>

          try
          {
            okButton111.setText(resourceBundle.getString("OK"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton111,ex);//No I18N
          }

//<UserCode_Begin_Bean_okButton111>

//<UserCode_End_Bean_okButton111>

          try
          {
            cancelButton111.setText(resourceBundle.getString("Cancel"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton111,ex);//No I18N
          }

//<UserCode_Begin_Bean_cancelButton111>

//<UserCode_End_Bean_cancelButton111>
		cancelButton111.setPreferredSize(new Dimension(cancelButton111.getPreferredSize().width+0,cancelButton111.getPreferredSize().height+1));
		okButton111.setPreferredSize(new Dimension(okButton111.getPreferredSize().width+22,okButton111.getPreferredSize().height+1));
		updateButton.setPreferredSize(new Dimension(updateButton.getPreferredSize().width+1,updateButton.getPreferredSize().height+1));
		JLabel1111.setPreferredSize(new Dimension(JLabel1111.getPreferredSize().width+0,JLabel1111.getPreferredSize().height+1));


          //<End_setUpProperties>
		  tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("Attribute Name"),resourceBundle.getString("Value")});//No I18N
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
        operationCombo= new javax.swing.JComboBox();
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
setConstraints(0,2,1,1,0.1,0.075,cons.NORTH,cons.BOTH,inset,0,0);
Top.add(JPanel3111,cons);
JPanel3111.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel3111.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3111.add(JPanel5111,cons);
JPanel5111.setLayout(new GridBagLayout());
inset = new Insets(0,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.NORTH,cons.BOTH,inset,0,0);
JPanel5111.add(JScrollPane1111,cons);
JScrollPane1111.getViewport().add(table);
inset = new Insets(5,5,0,5);
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
setConstraints(0,3,2,1,0.0,0.02,cons.CENTER,cons.HORIZONTAL,inset,0,0);
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
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6111.add(operationCombo,cons);
inset = new Insets(0,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.NORTH,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4111,cons);
JPanel4111.setLayout(new FlowLayout(1,5,5));
JPanel4111.add(okButton111);
JPanel4111.add(cancelButton111);


//<End_setUpGUI_Container>
	}
	public void setUpConnections()
  {
		//<Begin_setUpConnections>

      cancelButton111_cancelButton111_conn cancelButton111_cancelButton111_conn1 =  new cancelButton111_cancelButton111_conn();
      cancelButton111.addActionListener(cancelButton111_cancelButton111_conn1);
      updateButton_updateButton_conn updateButton_updateButton_conn1 =  new updateButton_updateButton_conn();
      updateButton.addActionListener(updateButton_updateButton_conn1);
      okButton111_okButton111_conn okButton111_okButton111_conn1 =  new okButton111_okButton111_conn();
      okButton111.addActionListener(okButton111_okButton111_conn1);
      table_table_conn table_table_conn1 =  new table_table_conn();
      table.addMouseListener(table_table_conn1);

      //<End_setUpConnections>
	}




	public void showStatus(String message)
  {
		//<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);//No I18N
     //<End_showStatus_String>
	}
	public void showStatus(String message,Exception ex)
  {
		//<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);//No I18N
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
	}





	public NetconfTemplateAdd()
  {
		//<Begin_NetconfTemplateAdd>
    pack();

    //<End_NetconfTemplateAdd>
	}

	public NetconfTemplateAdd(java.applet.Applet applet)
  {
		//<Begin_NetconfTemplateAdd_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    //<End_NetconfTemplateAdd_java.applet.Applet>
	}








	public void comboActionPerformed()
	{
		if(templateCombo.getSelectedItem().toString().equals("None"))//No I18N
		{
			valueLabel.setText(resourceBundle.getString("Value"));//No I18N
		}
		else
		{
			valueLabel.setText(resourceBundle.getString("Place Holder"));//No I18N
		}
	}

	public void okButtonActionPerformed()
	{
		Vector rowData = new Vector();

		for(int i=0;i<tableModel.getRowCount();i++)
		{
			String data = (String)tableModel.getValueAt(i, 1);
			if (data!=null)
			{
				rowData.add(data);
			}
			else
			{
				rowData.add("");//No I18N
			}
		}

		if(isModify)
		{
			netconfPanel.updateRow(rowData);
		}
		else
		{
			netconfPanel.addRow(rowData);
		}

		this.setVisible(false);
		this.dispose();
	}


	public void cancelButtonActionPerformed()
	{
		this.setVisible(false);
		this.dispose();
	}




	public void tableMousePressedEvent()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			String attName = (String)table.getValueAt(row,0);
			nameText.setText(attName);
			
			String value = (String)table.getValueAt(row,1);
			
			if(attName.trim().equals("configOperation"))
			{
				operationCombo.setVisible(true);
				templateCombo.setVisible(false);
				
				if(value.indexOf("get-config") != -1)//No I18N
				{
					operationCombo.setSelectedItem("get-config");//No I18N
				}
				else if(value.indexOf("edit-config") != -1)//No I18N
				{
					operationCombo.setSelectedItem("edit-config");//No I18N
				}
				else if(value.indexOf("copy-config") != -1)//No I18N
				{
					operationCombo.setSelectedItem("copy-config");//No I18N
				}
				else
				{
					operationCombo.setSelectedItem("delete-config");//No I18N
				}
				
				valueText.setEditable(false);
				
			}
			else
			{
				operationCombo.setVisible(false);
				templateCombo.setVisible(true);
			if(value != null && !(value.trim()).equals(""))//No I18N
			{
				if(value.indexOf("InventoryInput") != -1)//No I18N
					templateCombo.setSelectedItem("InventoryInput");//No I18N
				else if(value.indexOf("UserInput") != -1)//No I18N
					templateCombo.setSelectedItem("UserInput");//No I18N
				else if(value.indexOf("DataSourceParam") != -1)//No I18N
					templateCombo.setSelectedItem("DataSourceParam");//No I18N
				else
					templateCombo.setSelectedItem("None");//No I18N

				if(value.lastIndexOf('$') != -1)//No I18N
				{
					value = value.substring(value.lastIndexOf('$')+1);//No I18N
				}
			}
				valueText.setEditable(true);
				valueText.setText(value);
			}

			
		}
	}








	public void updateButtonActionPerformed()
	{
		int row = table.getSelectedRow();
		if(row != -1)
		{
			String attName = (String)table.getValueAt(row,0);
			
			String value = valueText.getText().trim();
			if(attName.equals("configOperation"))
			{
				value = (String)operationCombo.getSelectedItem();
				
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
			else
			{
				if(!value.equals(""))//No I18N
				{
				String inputType = (String)templateCombo.getSelectedItem();

				if(!inputType.equals("None"))//No I18N
				{
					value = "$"+inputType+"$"+value;//No I18N
				}
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




//<Begin__class_updateButton_updateButton_conn>

 class updateButton_updateButton_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_updateButton_updateButton_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          updateButtonActionPerformed();
     }
//<UserCode_End_Connection_updateButton_updateButton_conn>
 }//<End__class_updateButton_updateButton_conn>
//<Begin__class_okButton111_okButton111_conn>

 class okButton111_okButton111_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_okButton111_okButton111_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          okButtonActionPerformed();
     }
//<UserCode_End_Connection_okButton111_okButton111_conn>
 }//<End__class_okButton111_okButton111_conn>




//<Begin__class_cancelButton111_cancelButton111_conn>

 class cancelButton111_cancelButton111_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_cancelButton111_cancelButton111_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          cancelButtonActionPerformed();
     }
//<UserCode_End_Connection_cancelButton111_cancelButton111_conn>
 }//<End__class_cancelButton111_cancelButton111_conn>


//<Begin__class_table_table_conn>

 class table_table_conn extends java.awt.event.MouseAdapter implements java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_table_conn>

     //Listener Interface Methods Are Added Below


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
          tableMousePressedEvent();
     }
//<UserCode_End_Connection_table_table_conn>
 }//<End__class_table_table_conn>
}
















