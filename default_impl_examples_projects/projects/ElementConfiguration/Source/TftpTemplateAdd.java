
//$Id: TftpTemplateAdd.java,v 1.1 2006/08/29 13:56:51 build Exp $
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


public class TftpTemplateAdd extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel21 = null;
	javax.swing.JLabel JLabel21 = null;
	javax.swing.JPanel JPanel31 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel51 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel61 = null;
	javax.swing.JLabel JLabel41 = null;
	javax.swing.JLabel valueLabel = null;
	javax.swing.JTextField nameText = null;
	javax.swing.JTextField valueText = null;
	javax.swing.JPanel JPanel71 = null;
	javax.swing.JButton updateButton = null;
	javax.swing.JLabel templateLabel = null;
	javax.swing.JComboBox templateCombo = null;
	javax.swing.JLabel commandLabel = null;
	javax.swing.JComboBox commandCombo = null;
	javax.swing.JLabel modeLabel = null;
	javax.swing.JComboBox modeCombo = null;
	javax.swing.JScrollPane JScrollPane11 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel41 = null;
	javax.swing.JButton okButton1 = null;
	javax.swing.JButton cancelButton1 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	ConfigPanel configPanel = null;
	TftpPanel tftpPanel = null;

	public TftpTemplateAdd(ConfigPanel configPanel, TftpPanel tftpPanel)
	{
		super(configPanel.configClientUtils.getParentDialog(tftpPanel));

		this.configPanel = configPanel;
		this.tftpPanel = tftpPanel;

		applet = configPanel.applet;

		init();
		configInit();
	}

	private void configInit()
	{
		String title = "";

		if(tftpPanel.isScreenForFTP())
		{
			title = "FTP Task Configuration";
		}
		else
		{
			title = "TFTP Task Configuration";
		}

		title = resourceBundle.getString(title);

		JLabel21.setText(title);
		setTitle(title);

		ImageIcon image = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase() + "../images/taskconfig.png");

		JLabel2.setIcon(image);

		for(int i=0;i<4;i++)
		{
			Vector v = new Vector();
			if(i==0)
				v.add("Command");
			else if(i ==1)
				v.add("Source");
			else if(i ==2)
				v.add("Destination");
			else
				v.add("Mode");
			v.add("");

			tableModel.addRow(v);
		}	

		templateCombo.addItem("InventoryInput");
		templateCombo.addItem("UserInput");
		templateCombo.addItem("DataSourceParam");
		templateCombo.addItem("None");

		table.getSelectionModel().addSelectionInterval(0,0);

		commandCombo.addItem("Get");
		commandCombo.addItem("Put");

		modeCombo.addItem("ASCII");
		modeCombo.addItem("Binary");		

		tableMousePressedEvent();

		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);

		configPanel.configClientUtils.centerWindow(this);
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
		this.setSize(getPreferredSize().width+513,getPreferredSize().height+437); 
		setTitle(resourceBundle.getString("TftpTemplateAdd"));
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
			JPanel21.setBorder(new javax.swing.border.EtchedBorder(1));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel21,ex); 
		}

		//<UserCode_Begin_Bean_JPanel21>

		//<UserCode_End_Bean_JPanel21>

		try
		{
			JLabel21.setText(resourceBundle.getString("Tftp Task Configuration"));
			JLabel21.setForeground(new Color(-16764109));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel21,ex); 
		}

		//<UserCode_Begin_Bean_JLabel21>

		//<UserCode_End_Bean_JLabel21>

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
			JPanel51.setBorder(new javax.swing.border.SoftBevelBorder(0));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel51,ex); 
		}

		//<UserCode_Begin_Bean_JPanel51>

		//<UserCode_End_Bean_JPanel51>

		try
		{
			JLabel41.setText(resourceBundle.getString("Attribute Name"));
			JLabel41.setForeground(new Color(-16777216));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel41,ex); 
		}

		//<UserCode_Begin_Bean_JLabel41>

		//<UserCode_End_Bean_JLabel41>

		try
		{
			valueLabel.setText(resourceBundle.getString("Place Holder"));
			valueLabel.setForeground(new Color(-16777216));
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
			templateLabel.setText(resourceBundle.getString("Input Type"));
			templateLabel.setForeground(new Color(-16777216));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+templateLabel,ex); 
		}

		//<UserCode_Begin_Bean_templateLabel>

		//<UserCode_End_Bean_templateLabel>

		try
		{
			commandLabel.setText(resourceBundle.getString("Command"));
			commandLabel.setForeground(new Color(-16764109));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+commandLabel,ex); 
		}

		//<UserCode_Begin_Bean_commandLabel>

		//<UserCode_End_Bean_commandLabel>

		try
		{
			modeLabel.setText(resourceBundle.getString("Mode"));
			modeLabel.setForeground(new Color(-16764109));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+modeLabel,ex); 
		}

		//<UserCode_Begin_Bean_modeLabel>

		//<UserCode_End_Bean_modeLabel>

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
			JPanel41.setBorder(new javax.swing.border.EtchedBorder(1));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel41,ex); 
		}

		//<UserCode_Begin_Bean_JPanel41>

		//<UserCode_End_Bean_JPanel41>

		try
		{
			okButton1.setText(resourceBundle.getString("OK"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton1,ex); 
		}

		//<UserCode_Begin_Bean_okButton1>

		//<UserCode_End_Bean_okButton1>

		try
		{
			cancelButton1.setText(resourceBundle.getString("Cancel"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton1,ex); 
		}

		//<UserCode_Begin_Bean_cancelButton1>

		//<UserCode_End_Bean_cancelButton1>

		//<UserCode_Begin_Bean_tableModel>
		tableModel.setDataVector(
				new Object[0][0],
				new Object[]{"Attribute Name","Value"});
		//<UserCode_End_Bean_tableModel>
		okButton1.setPreferredSize(new Dimension(okButton1.getPreferredSize().width+22,okButton1.getPreferredSize().height+0));


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
		JPanel21= new javax.swing.JPanel();
		JLabel21= new javax.swing.JLabel();
		JPanel31= new javax.swing.JPanel();
		JLabel2= new javax.swing.JLabel();
		JPanel51= new javax.swing.JPanel();
		JPanel3= new javax.swing.JPanel();
		JPanel61= new javax.swing.JPanel();
		JLabel41= new javax.swing.JLabel();
		valueLabel= new javax.swing.JLabel();
		nameText= new javax.swing.JTextField();
		valueText= new javax.swing.JTextField();
		JPanel71= new javax.swing.JPanel();
		updateButton= new javax.swing.JButton();
		templateLabel= new javax.swing.JLabel();
		templateCombo= new javax.swing.JComboBox();
		commandLabel= new javax.swing.JLabel();
		commandCombo= new javax.swing.JComboBox();
		modeLabel= new javax.swing.JLabel();
		modeCombo= new javax.swing.JComboBox();
		JScrollPane11= new javax.swing.JScrollPane();
		table= new javax.swing.JTable();
		JPanel41= new javax.swing.JPanel();
		okButton1= new javax.swing.JButton();
		cancelButton1= new javax.swing.JButton();
		tableModel= new javax.swing.table.DefaultTableModel();


		//<End_initVariables>
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





	public TftpTemplateAdd()
	{
		//<Begin_TftpTemplateAdd>
		pack();

		//<End_TftpTemplateAdd>
	}

	public TftpTemplateAdd(java.applet.Applet applet)
	{
		//<Begin_TftpTemplateAdd_java.applet.Applet>
		this.applet = applet;
		pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		//<End_TftpTemplateAdd_java.applet.Applet>
	} 








	public void okButtonActionPerformed()
	{
		Vector v = new Vector();
		for(int i=0;i<table.getRowCount();i++)
		{
			if((table.getValueAt(i,1) == null) || (((String)table.getValueAt(i,1)).trim().equals("")))
			{
				if(i == 1)
				{
					configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("Please enter the value for source"), resourceBundle.getString("Info"), "info");
					return;
				}
				else if(i == 3)
				{
					configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("Please enter the value for mode"), resourceBundle.getString("Info"), "info");
					return;
				}
				else if(i == 0)
				{
					configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("Please enter the value for command"), resourceBundle.getString("Info"), "info");
					return;
				}
			}
			v.addElement(table.getValueAt(i,1));
		}
		tftpPanel.updateEntries(v);	
		this.setVisible(false);
		this.dispose();
	}

	public void cancelButtonActionPerformed()
	{
		this.setVisible(false);
		this.dispose();
	}






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




	public void tableMousePressedEvent()
	{
		int row = table.getSelectedRow();
		if(row != -1)
		{
			String str = table.getValueAt(row,0).toString();
			nameText.setText(table.getValueAt(row,0).toString());
			if(str.startsWith("Command"))
			{
				templateLabel.setVisible(false);
				templateCombo.setVisible(false);
				commandLabel.setVisible(true);
				commandCombo.setVisible(true);
				modeLabel.setVisible(false);
				modeCombo.setVisible(false);	
				valueText.setText("");
				valueText.setEditable(false);
				if(table.getValueAt(row,1).toString().equalsIgnoreCase("Get"))
				{
					commandCombo.setSelectedIndex(0);
				}
				else
				{
					commandCombo.setSelectedIndex(1);
				}
			}

			else if(str.startsWith("Mode"))
			{
				templateLabel.setVisible(false);
				templateCombo.setVisible(false);
				commandLabel.setVisible(false);
				commandCombo.setVisible(false);
				modeLabel.setVisible(true);
				modeCombo.setVisible(true);	
				valueText.setText("");				
				valueText.setEditable(false);				
				if(table.getValueAt(row,1).toString().equalsIgnoreCase("ASCII"))
				{
					modeCombo.setSelectedIndex(0);
				}
				else
				{
					modeCombo.setSelectedIndex(1);
				}				
			}

			else
			{
				templateCombo.setVisible(true);
				templateLabel.setVisible(true);
				commandLabel.setVisible(false);
				commandCombo.setVisible(false);
				modeLabel.setVisible(false);
				modeCombo.setVisible(false);				
				valueText.setEditable(true);

				String value = table.getValueAt(row,1).toString();	

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
						value = value.substring(value.lastIndexOf('$') + 1);
					}
				}

				valueText.setText(value);
			}
		}
	}








	public void updateButtonActionPerformed()
	{
		int row = table.getSelectedRow();
		if(row != -1)
		{
			if(nameText.getText().equals("Command"))
			{
				String val = commandCombo.getSelectedItem().toString();
				table.setValueAt(val,row,1);
			}

			else if(nameText.getText().equals("Mode"))
			{
				String val = modeCombo.getSelectedItem().toString();
				table.setValueAt(val,row,1);
			}

			else
			{
				String value = valueText.getText().trim();

				if(!value.equals(""))
				{
					String inputType = (String)templateCombo.getSelectedItem();

					if(!inputType.equals("None"))
					{
						value = "$"+inputType+"$"+value;
					}
				}

				table.setValueAt(value,row,1);
			}	
			if(row != table.getRowCount()-1)
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
		/*String value = (table.getValueAt(row,1).toString());
		  if(value.equals(""))
		  {}
		  else
		  {
		  value = table.getValueAt(row,1).toString();
		  value = value.substring(1);
		  String temp = value.substring(0,value.indexOf("$"));
		  if(temp.indexOf("InventoryInput") != -1)
		  templateCombo.setSelectedIndex(0);
		  else 
		  templateCombo.setSelectedIndex(1);
		  value = value.substring(value.lastIndexOf("$")+1);
		  }
		  valueText.setText(value); */

	}




	public void populateUI(Vector v)
	{
		for(int i=0;i<table.getRowCount();i++)
		{
			table.setValueAt(v.elementAt(i),i,1);
		}

		giveNewSelection(0);
		tableMousePressedEvent();
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
		Top.add(JPanel21,cons);
		JPanel21.setLayout(new FlowLayout(1,5,5));
		JPanel21.add(JLabel21);
		inset = new Insets(5,5,5,5);
		setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		Top.add(JPanel31,cons);
		JPanel31.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
		JPanel31.add(JLabel2,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		JPanel31.add(JPanel51,cons);
		JPanel51.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		JPanel51.add(JPanel3,cons);
		JPanel3.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		JPanel3.add(JPanel61,cons);
		JPanel61.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
		JPanel61.add(JLabel41,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(0,4,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
		JPanel61.add(valueLabel,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
		JPanel61.add(nameText,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(1,4,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
		JPanel61.add(valueText,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(0,5,2,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		JPanel61.add(JPanel71,cons);
		JPanel71.setLayout(new FlowLayout(2,5,5));
		JPanel71.add(updateButton);
		inset = new Insets(5,5,5,5);
		setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
		JPanel61.add(templateLabel,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
		JPanel61.add(templateCombo,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
		JPanel61.add(commandLabel,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(1,2,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
		JPanel61.add(commandCombo,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
		JPanel61.add(modeLabel,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(1,3,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
		JPanel61.add(modeCombo,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		JPanel3.add(JScrollPane11,cons);
		JScrollPane11.getViewport().add(table);
		inset = new Insets(5,5,5,5);
		setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		Top.add(JPanel41,cons);
		JPanel41.setLayout(new FlowLayout(1,5,5));
		JPanel41.add(okButton1);
		JPanel41.add(cancelButton1);


		//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
	{
		//<Begin_setUpConnections> 

		templateCombo_templateCombo_conn3 templateCombo_templateCombo_conn31 =  new templateCombo_templateCombo_conn3();
		templateCombo.addActionListener(templateCombo_templateCombo_conn31);
		okButton1_okButton1_conn1 okButton1_okButton1_conn11 =  new okButton1_okButton1_conn1();
		okButton1.addActionListener(okButton1_okButton1_conn11);
		cancelButton1_cancelButton1_conn1 cancelButton1_cancelButton1_conn11 =  new cancelButton1_cancelButton1_conn1();
		cancelButton1.addActionListener(cancelButton1_cancelButton1_conn11);
		updateButton_updateButton_conn1 updateButton_updateButton_conn11 =  new updateButton_updateButton_conn1();
		updateButton.addActionListener(updateButton_updateButton_conn11);
		table_JPanel61_conn1 table_JPanel61_conn11 =  new table_JPanel61_conn1();
		table.addMouseListener(table_JPanel61_conn11);

		//<End_setUpConnections>
	} 


	//<Begin__class_templateCombo_templateCombo_conn3>

	class templateCombo_templateCombo_conn3 implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_templateCombo_templateCombo_conn3>



		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			comboActionPerformed();
		}
		//<UserCode_End_Connection_templateCombo_templateCombo_conn3>
	}//<End__class_templateCombo_templateCombo_conn3>
	//<Begin__class_okButton1_okButton1_conn1>

	class okButton1_okButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_okButton1_okButton1_conn1>



		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			okButtonActionPerformed();
		}
		//<UserCode_End_Connection_okButton1_okButton1_conn1>
	}//<End__class_okButton1_okButton1_conn1>
	//<Begin__class_cancelButton1_cancelButton1_conn1>

	class cancelButton1_cancelButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_cancelButton1_cancelButton1_conn1>



		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			cancelButtonActionPerformed();
		}
		//<UserCode_End_Connection_cancelButton1_cancelButton1_conn1>
	}//<End__class_cancelButton1_cancelButton1_conn1>
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
	//<Begin__class_table_JPanel61_conn1>

	class table_JPanel61_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_table_JPanel61_conn1>



		//Listener Interface Methods Are Added Below 


		public void mousePressed( java.awt.event.MouseEvent arg0)
		{
			tableMousePressedEvent();
		}
		//<UserCode_End_Connection_table_JPanel61_conn1>
	}//<End__class_table_JPanel61_conn1>


	public void setProperties(java.util.Properties props)
	{
		//<Begin_setProperties_java.util.Properties> 


		//<End_setProperties_java.util.Properties>
	}
}
