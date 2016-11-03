
//$Id: InventoryInputPanel.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;

public class InventoryInputPanel extends JPanel implements ListSelectionListener 
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
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField placeHolderText = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JTextField moNameText = null;
	javax.swing.JButton browseButton = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField defaultValueText = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JTextField JTextField3 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton updateButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>



	public InventoryInputPanel()
  {
		//<Begin_InventoryInputPanel>
    this.init();
  
    //<End_InventoryInputPanel>
	}

	public InventoryInputPanel(java.applet.Applet applet)
  {
		//<Begin_InventoryInputPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_InventoryInputPanel_java.applet.Applet>
	}


	public void start()
  { 

		//<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
	} 
	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel2= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        placeHolderText= new javax.swing.JTextField();
        JLabel2= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        moNameText= new javax.swing.JTextField();
        browseButton= new javax.swing.JButton();
        JLabel3= new javax.swing.JLabel();
        defaultValueText= new javax.swing.JTextField();
        JLabel4= new javax.swing.JLabel();
        JTextField3= new javax.swing.JTextField();
        JPanel4= new javax.swing.JPanel();
        updateButton= new javax.swing.JButton();
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
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,0,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,0,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel1,cons);
inset = new Insets(5,5,0,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(placeHolderText,cons);
inset = new Insets(5,5,0,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel2,cons);
inset = new Insets(5,5,0,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,20);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(moNameText,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel3.add(browseButton,cons);
inset = new Insets(5,5,0,5);
setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel3,cons);
inset = new Insets(5,5,0,5);
setConstraints(1,3,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(defaultValueText,cons);
inset = new Insets(5,5,0,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel4,cons);
inset = new Insets(5,5,0,5);
setConstraints(1,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JTextField3,cons);
inset = new Insets(5,5,0,5);
setConstraints(0,4,2,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(2,5,2));
JPanel4.add(updateButton);

  
//<End_setUpGUI_Container>
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
            JLabel1.setText(resourceBundle.getString("Place Holder"));
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            placeHolderText.setEditable(false);
            placeHolderText.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+placeHolderText,ex); 
          }

//<UserCode_Begin_Bean_placeHolderText>

//<UserCode_End_Bean_placeHolderText>

          try
          {
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setText(resourceBundle.getString("MO Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            browseButton.setText(resourceBundle.getString("Browse"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+browseButton,ex); 
          }

//<UserCode_Begin_Bean_browseButton>

//<UserCode_End_Bean_browseButton>

          try
          {
            JLabel3.setText(resourceBundle.getString("Default Value"));
            JLabel3.setForeground(new Color(-16764109));
            JLabel3.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel4.setText(resourceBundle.getString("MO Property"));
            JLabel4.setForeground(new Color(-16764109));
            JLabel4.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

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

  
          //<End_setUpProperties>
	} 
	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      updateButton_updateButton_conn1 updateButton_updateButton_conn11 =  new updateButton_updateButton_conn1();
      updateButton.addActionListener(updateButton_updateButton_conn11);
      browseButton_browseButton_conn1 browseButton_browseButton_conn11 =  new browseButton_browseButton_conn1();
      browseButton.addActionListener(browseButton_browseButton_conn11);
  
      //<End_setUpConnections>
	} 
	public void stop()
  { 

		//<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
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
	public void init()
  { 

		//<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+601,getPreferredSize().height+440)); 
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

		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);
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
	//<Begin__class_browseButton_browseButton_conn1>

 class browseButton_browseButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_browseButton_browseButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  browseButtonActionPerformed();
     }
//<UserCode_End_Connection_browseButton_browseButton_conn1>
 }//<End__class_browseButton_browseButton_conn1>

	public void browseButtonActionPerformed()
	{
		SelectDeviceForNEInput selectDialog = new SelectDeviceForNEInput(configPanel, this, protocol);
		selectDialog.setVisible(true);
	}

	public void updateButtonActionPerformed()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{	
			tableModel.setValueAt(moNameText.getText(), row, 1);
			tableModel.setValueAt(JTextField3.getText(), row, 2);
			tableModel.setValueAt(defaultValueText.getText(), row, 3);
		}
	}

	private ConfigPanel configPanel = null;

	private String protocol = null;

	private Vector columns = new Vector();

	public InventoryInputPanel(ConfigPanel configPanel, Vector templateDetails)
	{
		this.configPanel = configPanel;

		applet = configPanel.applet;

		protocol = (String)templateDetails.elementAt(2);

		init();
		configInit((Vector)templateDetails.elementAt(3));
	}

	private void configInit(Vector placeHolders)
	{
		moNameText.setEditable(false);
		moNameText.setDisabledTextColor(Color.black);

		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);

		if(placeHolders != null)
		{
			Vector tableData = new Vector();

			for(int i=0; i<placeHolders.size(); i++)
			{
				String placeHolder = (String)placeHolders.elementAt(i); 

				if(placeHolder.indexOf("InventoryInput") != -1)
				{
					Vector rowData = new Vector();

					rowData.addElement(placeHolder.substring(placeHolder.lastIndexOf('$')+1));

					rowData.addElement("");					
					rowData.addElement("");
					rowData.addElement("");

					tableData.addElement(rowData);
				}
			}

			columns.addElement(resourceBundle.getString("Place Holder"));
			columns.addElement(resourceBundle.getString("MO Name"));
			columns.addElement(resourceBundle.getString("MO Property"));
			columns.addElement(resourceBundle.getString("Default Value"));

			tableModel.setDataVector(tableData, columns);
			table.updateUI();
		}
	}

	public void valueChanged(ListSelectionEvent lse)
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			placeHolderText.setText((String)tableModel.getValueAt(row, 0));	
			moNameText.setText((String)tableModel.getValueAt(row, 1));
			JTextField3.setText((String)tableModel.getValueAt(row, 2));
			defaultValueText.setText((String)tableModel.getValueAt(row, 3));
		}
	}

	public void setDevice(String deviceName)	
	{
		moNameText.setText(deviceName);
	}

	public Vector getAllValues()
	{
		return tableModel.getDataVector();
	}

	public void setValues(Vector values)
	{
		if(values != null)
		{
			tableModel.setDataVector(values, columns);
		}
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}



