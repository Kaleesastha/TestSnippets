
//$Id: Tl1TemplateAdd.java,v 1.1 2006/08/29 13:56:51 build Exp $
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


public class Tl1TemplateAdd extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel111 = null;
	javax.swing.JLabel JLabel111 = null;
	javax.swing.JPanel JPanel311 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JPanel JPanel511 = null;
	javax.swing.JScrollPane JScrollPane111 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel611 = null;
	javax.swing.JLabel JLabel411 = null;
	javax.swing.JLabel valueLabel = null;
	javax.swing.JTextField nameText = null;
	javax.swing.JTextField valueText = null;
	javax.swing.JPanel JPanel711 = null;
	javax.swing.JButton updateButton = null;
	javax.swing.JLabel templateLabel11 = null;
	javax.swing.JComboBox templateCombo = null;
	javax.swing.JPanel JPanel11 = null;
	javax.swing.JLabel JLabel11 = null;
	javax.swing.JTextField commandText = null;
	javax.swing.JPanel JPanel411 = null;
	javax.swing.JButton okButton11 = null;
	javax.swing.JButton cancelButton11 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;
	private	TL1Panel tl1Panel = null;



	public Tl1TemplateAdd(ConfigPanel configPanel, TL1Panel tl1Panel)
	{
		super(configPanel.configClientUtils.getParentDialog(tl1Panel));

		this.configPanel = configPanel;
		this.tl1Panel = tl1Panel;
		
		applet = configPanel.applet;

		init();
		configInit();
	}



	private void configInit()
	{
		URL docBase = configPanel.applet.getDocumentBase();
		String test = docBase+"../images/taskconfig.png" ;
		ImageIcon image = configPanel.configClientUtils.getImage(test);
		JLabel3.setIcon(image);
		for(int i=0;i<4;i++)
		{
			Vector v = new Vector();
			if(i==0)
				v.add("Target ID");
			else if(i ==1)
				v.add("Access ID");
			else if(i == 2)
				v.add("General Block");
			else
				v.add("MessagePayLoadBlock");
			v.add("");
			tableModel.addRow(v);
		}	
				
		templateCombo.addItem("InventoryInput");
		templateCombo.addItem("UserInput");
		templateCombo.addItem("DataSourceParam");
		templateCombo.addItem("None");

		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);
		table.getSelectionModel().addSelectionInterval(0,0);
		tableMousePressedEvent();

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
        this.setSize(getPreferredSize().width+474,getPreferredSize().height+467); 
          setTitle(resourceBundle.getString("Tl1TemplateAdd"));
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
          setTitle(resourceBundle.getString("TL1 Task Configuration"));
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
            JPanel111.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel111,ex); 
          }

//<UserCode_Begin_Bean_JPanel111>

//<UserCode_End_Bean_JPanel111>

          try
          {
            JLabel111.setForeground(new Color(-16764109));
            JLabel111.setText(resourceBundle.getString("TL1 Task Configuration"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel111,ex); 
          }

//<UserCode_Begin_Bean_JLabel111>

//<UserCode_End_Bean_JLabel111>

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
            JPanel511.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel511,ex); 
          }

//<UserCode_Begin_Bean_JPanel511>

//<UserCode_End_Bean_JPanel511>

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
            JLabel411.setText(resourceBundle.getString("Attribute Name"));
            JLabel411.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel411,ex); 
          }

//<UserCode_Begin_Bean_JLabel411>

//<UserCode_End_Bean_JLabel411>

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
            templateLabel11.setText(resourceBundle.getString("Input Type"));
            templateLabel11.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+templateLabel11,ex); 
          }

//<UserCode_Begin_Bean_templateLabel11>

//<UserCode_End_Bean_templateLabel11>

          try
          {
            JLabel11.setForeground(new Color(-16777216));
            JLabel11.setText(resourceBundle.getString("Command code"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel11,ex); 
          }

//<UserCode_Begin_Bean_JLabel11>

//<UserCode_End_Bean_JLabel11>

          try
          {
            JPanel411.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel411,ex); 
          }

//<UserCode_Begin_Bean_JPanel411>

//<UserCode_End_Bean_JPanel411>

          try
          {
            okButton11.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton11,ex); 
          }

//<UserCode_Begin_Bean_okButton11>

//<UserCode_End_Bean_okButton11>

          try
          {
            cancelButton11.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton11,ex); 
          }

//<UserCode_Begin_Bean_cancelButton11>

//<UserCode_End_Bean_cancelButton11>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Attribute Name","Value"});
//<UserCode_End_Bean_tableModel>
		okButton11.setPreferredSize(new Dimension(okButton11.getPreferredSize().width+22,okButton11.getPreferredSize().height+0));
		JLabel111.setPreferredSize(new Dimension(JLabel111.getPreferredSize().width+9,JLabel111.getPreferredSize().height+0));

  
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
        JPanel111= new javax.swing.JPanel();
        JLabel111= new javax.swing.JLabel();
        JPanel311= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JPanel511= new javax.swing.JPanel();
        JScrollPane111= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel611= new javax.swing.JPanel();
        JLabel411= new javax.swing.JLabel();
        valueLabel= new javax.swing.JLabel();
        nameText= new javax.swing.JTextField();
        valueText= new javax.swing.JTextField();
        JPanel711= new javax.swing.JPanel();
        updateButton= new javax.swing.JButton();
        templateLabel11= new javax.swing.JLabel();
        templateCombo= new javax.swing.JComboBox();
        JPanel11= new javax.swing.JPanel();
        JLabel11= new javax.swing.JLabel();
        commandText= new javax.swing.JTextField();
        JPanel411= new javax.swing.JPanel();
        okButton11= new javax.swing.JButton();
        cancelButton11= new javax.swing.JButton();
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





	public Tl1TemplateAdd()
  {
		//<Begin_Tl1TemplateAdd>
    pack();
  
    //<End_Tl1TemplateAdd>
	}

	public Tl1TemplateAdd(java.applet.Applet applet)
  {
		//<Begin_Tl1TemplateAdd_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_Tl1TemplateAdd_java.applet.Applet>
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


	
	

	public void cancelButtonActionPerformed()
	{
		this.setVisible(false);
		this.dispose();
	}

	public void okButtonActionPerformed()
	{
		Vector v = new Vector();
		if(commandText.getText().trim().equals(""))
		{
			//JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter a Command code"));
			configPanel.configClientUtils.showErrorDialog(null,resourceBundle.getString("Please enter the Command code"),resourceBundle.getString("Info"),"Info");
			return;
		}	
		v.addElement(commandText.getText());
		for(int i=0;i<table.getRowCount();i++)
			v.addElement(table.getValueAt(i,1));
		tl1Panel.updateEntries(v);
		this.setVisible(false);
		this.dispose();
	}


	

	public void tableMousePressedEvent()
	{
		int row = table.getSelectedRow();
		
		if(row != -1)
		{
			nameText.setText(table.getValueAt(row,0).toString());
			
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

				value = value.substring(value.lastIndexOf('$')+1);
			}
				
			valueText.setText(value);
		}
	}


	 
	



	
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
			
				table.setValueAt(value,row,1);
			
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
	
	public void populateUI(Vector v)
	{
		commandText.setText(v.elementAt(0).toString());
		for(int i=1;i<table.getRowCount()+1;i++)
		{
			table.setValueAt(v.elementAt(i),i-1,1);
		}
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
Top.add(JPanel111,cons);
JPanel111.setLayout(new FlowLayout(1,5,5));
JPanel111.add(JLabel111);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel311,cons);
JPanel311.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel311.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel311.add(JPanel511,cons);
JPanel511.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel511.add(JScrollPane111,cons);
JScrollPane111.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel511.add(JPanel611,cons);
JPanel611.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel611.add(JLabel411,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel611.add(valueLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel611.add(nameText,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel611.add(valueText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,2,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel611.add(JPanel711,cons);
JPanel711.setLayout(new FlowLayout(2,5,5));
JPanel711.add(updateButton);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel611.add(templateLabel11,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel611.add(templateCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel511.add(JPanel11,cons);
JPanel11.setLayout(new GridBagLayout());
inset = new Insets(5,2,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel11.add(JLabel11,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel11.add(commandText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel411,cons);
JPanel411.setLayout(new FlowLayout(1,5,5));
JPanel411.add(okButton11);
JPanel411.add(cancelButton11);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
      //<Begin_setUpConnections> 

      templateCombo_templateCombo_conn1 templateCombo_templateCombo_conn11 =  new templateCombo_templateCombo_conn1();
      templateCombo.addActionListener(templateCombo_templateCombo_conn11);
      table_JLabel11_conn1 table_JLabel11_conn11 =  new table_JLabel11_conn1();
      table.addMouseListener(table_JLabel11_conn11);
      updateButton_updateButton_conn1 updateButton_updateButton_conn11 =  new updateButton_updateButton_conn1();
      updateButton.addActionListener(updateButton_updateButton_conn11);
      cancelButton11_cancelButton11_conn1 cancelButton11_cancelButton11_conn11 =  new cancelButton11_cancelButton11_conn1();
      cancelButton11.addActionListener(cancelButton11_cancelButton11_conn11);
      okButton11_JPanel411_conn1 okButton11_JPanel411_conn11 =  new okButton11_JPanel411_conn1();
      okButton11.addActionListener(okButton11_JPanel411_conn11);
  
      //<End_setUpConnections>
  } 


//<Begin__class_templateCombo_templateCombo_conn1>

 class templateCombo_templateCombo_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_templateCombo_templateCombo_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  comboActionPerformed();
     }
//<UserCode_End_Connection_templateCombo_templateCombo_conn1>
 }//<End__class_templateCombo_templateCombo_conn1>
//<Begin__class_table_JLabel11_conn1>

 class table_JLabel11_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_JLabel11_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  tableMousePressedEvent();
     }
//<UserCode_End_Connection_table_JLabel11_conn1>
 }//<End__class_table_JLabel11_conn1>
//<Begin__class_cancelButton11_cancelButton11_conn1>

 class cancelButton11_cancelButton11_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_cancelButton11_cancelButton11_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  cancelButtonActionPerformed();
     }
//<UserCode_End_Connection_cancelButton11_cancelButton11_conn1>
 }//<End__class_cancelButton11_cancelButton11_conn1>
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
//<Begin__class_okButton11_JPanel411_conn1>

 class okButton11_JPanel411_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_okButton11_JPanel411_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  okButtonActionPerformed();
     }
//<UserCode_End_Connection_okButton11_JPanel411_conn1>
 }//<End__class_okButton11_JPanel411_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
















