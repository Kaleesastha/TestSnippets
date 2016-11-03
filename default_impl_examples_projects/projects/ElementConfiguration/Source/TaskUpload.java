
//$Id: TaskUpload.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;


import com.adventnet.management.config.*;
import java.util.*;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;

import com.adventnet.management.config.xml.*;

public class TaskUpload extends JDialog implements ConfigResponseListener, ActionListener, HelpInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel cardPanel = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTable attributesTable = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel valueLabel = null;
	javax.swing.JTextField propertyText = null;
	javax.swing.JTextField valueText = null;
	javax.swing.JPanel JPanel9 = null;
	javax.swing.JButton updateButton = null;
	javax.swing.JLabel versionLabel = null;
	javax.swing.JComboBox versionCombo = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JPanel JPanel12 = null;
	javax.swing.JButton searchButton = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel13 = null;
	javax.swing.JTextField deviceNameText = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable devicesTable = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JButton helpButton = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField taskNameText = null;
	javax.swing.JPanel JPanel10 = null;
	javax.swing.JPanel JPanel11 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	javax.swing.table.DefaultTableModel customModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;

	private String taskName = "";
	private String protocol = "";

	private boolean isSnmp = false;
	private boolean isVersion3 = false;

	private Vector devicesVector = null;
	private Vector attributesVector = null;

	private DeviceAttributesObject attributesObject = null;

	private String helpKey = "Batch_Conf_Upload_Task";	

	private HelpDialog helpDialog = null;

	public TaskUpload(ConfigPanel panel, String taskName, String protocol)
	{
		super(panel.configClientUtils.getParentFrame(panel));

		configPanel = panel;

		this.taskName = taskName;
		this.protocol = protocol;

		applet = configPanel.applet;

		init();
		configInit();
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
        cardPanel= new javax.swing.JPanel();
        JPanel7= new javax.swing.JPanel();
        JScrollPane2= new javax.swing.JScrollPane();
        attributesTable= new javax.swing.JTable();
        JPanel8= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        valueLabel= new javax.swing.JLabel();
        propertyText= new javax.swing.JTextField();
        valueText= new javax.swing.JTextField();
        JPanel9= new javax.swing.JPanel();
        updateButton= new javax.swing.JButton();
        versionLabel= new javax.swing.JLabel();
        versionCombo= new javax.swing.JComboBox();
        JPanel4= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JPanel6= new javax.swing.JPanel();
        JPanel12= new javax.swing.JPanel();
        searchButton= new javax.swing.JButton();
        JLabel1= new javax.swing.JLabel();
        JPanel13= new javax.swing.JPanel();
        deviceNameText= new javax.swing.JTextField();
        JButton2= new javax.swing.JButton();
        JScrollPane1= new javax.swing.JScrollPane();
        devicesTable= new javax.swing.JTable();
        JPanel2= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        helpButton= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        taskNameText= new javax.swing.JTextField();
        JPanel10= new javax.swing.JPanel();
        JPanel11= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JButton1= new javax.swing.JButton();
        tableModel= new javax.swing.table.DefaultTableModel();
        customModel= new javax.swing.table.DefaultTableModel();

  
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
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.7,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(cardPanel,cons);
cardPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
cardPanel.add(JPanel7,cons);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel7.add(JScrollPane2,cons);
JScrollPane2.getViewport().add(attributesTable);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel7.add(JPanel8,cons);
JPanel8.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel8.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel8.add(valueLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel8.add(propertyText,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel8.add(valueText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,2,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel8.add(JPanel9,cons);
JPanel9.setLayout(new FlowLayout(2,5,5));
JPanel9.add(updateButton);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel8.add(versionLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel8.add(versionCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel1.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel4.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(10,0,0,0);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JPanel12,cons);
JPanel12.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel12.add(searchButton,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel12.add(JLabel1,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JPanel13,cons);
JPanel13.setLayout(new GridBagLayout());
inset = new Insets(0,3,0,8);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel13.add(deviceNameText,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel13.add(JButton2,cons);
inset = new Insets(0,0,10,0);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(devicesTable);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(okButton);
JPanel2.add(cancelButton);
JPanel2.add(helpButton);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,20,5,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel5.add(JLabel2,cons);
inset = new Insets(5,20,5,30);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(taskNameText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel10,cons);
JPanel10.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,20);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel10.add(JPanel11,cons);
JPanel11.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel11.add(JLabel3,cons);
inset = new Insets(5,5,5,20);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel10.add(JButton1,cons);

  
//<End_setUpGUI_Container>
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
            cardPanel.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cardPanel,ex); 
          }

//<UserCode_Begin_Bean_cardPanel>

//<UserCode_End_Bean_cardPanel>

          try
          {
            JPanel7.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Device Attributes"),0,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel7,ex); 
          }

//<UserCode_Begin_Bean_JPanel7>

//<UserCode_End_Bean_JPanel7>

          try
          {
            attributesTable.setModel(tableModel);
            attributesTable.setRowHeight(20);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+attributesTable,ex); 
          }

//<UserCode_Begin_Bean_attributesTable>

//<UserCode_End_Bean_attributesTable>

          try
          {
            JPanel8.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel8,ex); 
          }

//<UserCode_Begin_Bean_JPanel8>

//<UserCode_End_Bean_JPanel8>

          try
          {
            JLabel4.setText(resourceBundle.getString("Property"));
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
            valueLabel.setText(resourceBundle.getString("Value"));
            valueLabel.setForeground(new Color(-16764109));
            valueLabel.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+valueLabel,ex); 
          }

//<UserCode_Begin_Bean_valueLabel>

//<UserCode_End_Bean_valueLabel>

          try
          {
            propertyText.setEditable(false);
            propertyText.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+propertyText,ex); 
          }

//<UserCode_Begin_Bean_propertyText>

//<UserCode_End_Bean_propertyText>

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
            versionLabel.setText(resourceBundle.getString("Value"));
            versionLabel.setForeground(new Color(-16764109));
            versionLabel.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+versionLabel,ex); 
          }

//<UserCode_Begin_Bean_versionLabel>

//<UserCode_End_Bean_versionLabel>

          try
          {
            versionCombo.setMinimumSize(new Dimension(126,21));
            versionCombo.setMaximumSize(new Dimension(32767,21));
            versionCombo.setPreferredSize(new Dimension(131,26));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+versionCombo,ex); 
          }

//<UserCode_Begin_Bean_versionCombo>

//<UserCode_End_Bean_versionCombo>

          try
          {
            JPanel4.setBorder(new javax.swing.border.SoftBevelBorder(1));
            JPanel4.setPreferredSize(new Dimension(231,174));
            JPanel4.setMinimumSize(new Dimension(231,175));
            JPanel4.setMaximumSize(new Dimension(2147483647,200));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            searchButton.setText(resourceBundle.getString("Search Device"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+searchButton,ex); 
          }

//<UserCode_Begin_Bean_searchButton>

//<UserCode_End_Bean_searchButton>

          try
          {
            deviceNameText.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+deviceNameText,ex); 
          }

//<UserCode_Begin_Bean_deviceNameText>

//<UserCode_End_Bean_deviceNameText>

          try
          {
            JButton2.setText(resourceBundle.getString("Add"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            devicesTable.setModel(customModel);
            devicesTable.setRowHeight(21);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+devicesTable,ex); 
          }

//<UserCode_Begin_Bean_devicesTable>

//<UserCode_End_Bean_devicesTable>

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

          try
          {
            helpButton.setText(resourceBundle.getString("Help"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+helpButton,ex); 
          }

//<UserCode_Begin_Bean_helpButton>

//<UserCode_End_Bean_helpButton>

          try
          {
            JPanel5.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setText(resourceBundle.getString("Upload for task"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            taskNameText.setEditable(false);
            taskNameText.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taskNameText,ex); 
          }

//<UserCode_Begin_Bean_taskNameText>

//<UserCode_End_Bean_taskNameText>

          try
          {
            JPanel10.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel10,ex); 
          }

//<UserCode_Begin_Bean_JPanel10>

//<UserCode_End_Bean_JPanel10>

          try
          {
            JLabel3.setText(resourceBundle.getString("Task Upload"));
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
            JButton1.setBackground(new Color(-1));
            JButton1.setPreferredSize(new Dimension(35,23));
            JButton1.setMinimumSize(new Dimension(35,23));
            JButton1.setMaximumSize(new Dimension(35,23));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Property","Value"});
//<UserCode_End_Bean_tableModel>

//<UserCode_Begin_Bean_customModel>
Vector row = new Vector();
Vector column = new Vector();
column.addElement(" ");
column.addElement("Devices");
customModel.setDataVector(row,column);
//<UserCode_End_Bean_customModel>
		helpButton.setPreferredSize(new Dimension(helpButton.getPreferredSize().width+13,helpButton.getPreferredSize().height+0));
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+21,okButton.getPreferredSize().height+0));

  
          //<End_setUpProperties>
				JPanel7.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Device Attributes"),0,0,new Font("Dialog",0,12),new Color(-16764109)));
	}

	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      devicesTable_devicesTable_conn1 devicesTable_devicesTable_conn11 =  new devicesTable_devicesTable_conn1();
      devicesTable.addMouseListener(devicesTable_devicesTable_conn11);
      JButton2_JPanel6_conn1 JButton2_JPanel6_conn11 =  new JButton2_JPanel6_conn1();
      searchButton.addActionListener(JButton2_JPanel6_conn11);
      deviceNameText_deviceNameText_conn1 deviceNameText_deviceNameText_conn11 =  new deviceNameText_deviceNameText_conn1();
      deviceNameText.addKeyListener(deviceNameText_deviceNameText_conn11);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      attributesTable.addMouseListener(table_table_conn11);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      updateButton.addActionListener(JButton3_JButton3_conn11);
      JButton4_JButton4_conn1 JButton4_JButton4_conn11 =  new JButton4_JButton4_conn1();
      cancelButton.addActionListener(JButton4_JButton4_conn11);
      JButton1_JButton1_conn2 JButton1_JButton1_conn21 =  new JButton1_JButton1_conn2();
      JButton1.addActionListener(JButton1_JButton1_conn21);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      okButton.addActionListener(JButton1_JButton1_conn11);
  
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
        this.setSize(getPreferredSize().width+634,getPreferredSize().height+525); 
          setTitle(resourceBundle.getString("TaskUpload"));
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

	private void configInit()
	{
		setTitle(resourceBundle.getString("Upload Task"));

		taskNameText.setText(taskName);

		if(protocol.equalsIgnoreCase("snmp")) isSnmp = true;

		tableModel.setDataVector(
				new Object[0][0],
				new Object[]{resourceBundle.getString("Property"),resourceBundle.getString("Value")});

		Vector row = new Vector();
		Vector column = new Vector();

		column.addElement(" ");
		column.addElement(resourceBundle.getString("Devices"));

		customModel.setDataVector(row,column);

		ImageIcon deviceIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/devices.png");


		attributesTable.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		attributesTable.setDefaultEditor(Object.class,null);

		versionCombo.addItem("v1");
		versionCombo.addItem("v2c");
		versionCombo.addItem("v3");

		versionCombo.setVisible(false);

		versionLabel.setVisible(false);

		TableColumn firstColumn = devicesTable.getColumnModel().getColumn(0);
		firstColumn.setCellRenderer(new SelectionFlag(configPanel));
		firstColumn.setMaxWidth(25);

		TableColumn secondColumn = devicesTable.getColumnModel().getColumn(1);
		secondColumn.setCellRenderer(new CommonIconRenderer(deviceIcon));		
		devicesTable.setDefaultEditor(Object.class,null);

		JButton1.setIcon(configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/contextualhelp.png"));

		helpButton.addActionListener(this);

		configPanel.configClientUtils.centerWindow(this);
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





	public void listMousePressedEvent()
	{

	}





	public TaskUpload()
  {
		//<Begin_TaskUpload>
    pack();
  
    //<End_TaskUpload>
	}

	public TaskUpload(java.applet.Applet applet)
  {
		//<Begin_TaskUpload_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_TaskUpload_java.applet.Applet>
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
		int row = attributesTable.getSelectedRow();
		if(row != -1)
			propertyText.setText(attributesTable.getValueAt(row,0).toString());
		if(isSnmp)
		{
			if(row == 0)
				valueText.setEditable(false);
			else
				valueText.setEditable(true);
			if(row == 3)
			{
				valueLabel.setVisible(false);
				valueText.setVisible(false);				
				versionLabel.setVisible(true);
				versionCombo.setVisible(true);
			}
			else
			{
				versionLabel.setVisible(false);
				versionCombo.setVisible(false);
				valueLabel.setVisible(true);
				valueText.setVisible(true);
			}
			if(row == 4 || row == 5 || row == 6 || row == 7)
			{
				if(isVersion3)
				{
					valueText.setEditable(true);
					valueText.setText(attributesTable.getValueAt(row,1).toString());
				}
				else
				{
					valueText.setEditable(false);
					valueText.setText("");
				}
			}
			else
			{
				valueText.setText(attributesTable.getValueAt(row,1).toString());
			}
		}
		else
		{
			//int row = table.getSelectedRow();
			if(row == 0)
				valueText.setEditable(false);
			else
				valueText.setEditable(true);
			String value = attributesTable.getValueAt(row,1).toString();	
			valueText.setText(attributesTable.getValueAt(row,1).toString());		
		}
	}


	//<Begin__class_JButton4_JButton4_conn1>

 class JButton4_JButton4_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton4_JButton4_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  TaskUpload.this.setVisible(false);
  TaskUpload.this.dispose(); 
     }
//<UserCode_End_Connection_JButton4_JButton4_conn1>
 }//<End__class_JButton4_JButton4_conn1>
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

	public void okButtonActionPerformed()
	{
		int count = devicesTable.getRowCount();
		MultipleListSelectionObject object = null;
		int row = 0;
		boolean check = false;
		for(int i=0;i<count;i++)
		{
			object = (MultipleListSelectionObject)devicesTable.getValueAt(i,0);
			if(object.getState())
			{
				check = true;
				row = i;
				break;
			}
		}

		if(!check)
		{
			//JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a device for Upload"));
			configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Please select a device for Upload"), resourceBundle.getString("Info"), "info");
			return;
		}

		ExecuteXMLGenerator xmlGen = new ExecuteXMLGenerator(taskName);
		xmlGen.setTaskName(taskName);
		xmlGen.setProtocol(protocol.toLowerCase());
		Vector v = new Vector();
		v.addElement(devicesVector.elementAt(row));
		xmlGen.setDevices(v);
		String upload = xmlGen.getExecuteXML();
		Object [] param = {upload};
		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.UPLOAD_CONFIGURATION,param,this);
		this.setVisible(false);
	}


	//<Begin__class_JButton2_JPanel6_conn1>

 class JButton2_JPanel6_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JPanel6_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  searchButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton2_JPanel6_conn1>
 }//<End__class_JButton2_JPanel6_conn1>

	public void searchButtonActionPerformed()
	{
		SearchForBatchConfiguration.showSearchWindow(configPanel, configPanel.applet, this);
	}

	public void response(ConfigResultEvent cre)
	{
		int workId = cre.getWorkID();
		int errorCode = cre.getErrorCode();
		String uniqueId = cre.getRequestID();


		if(workId == NmsConfigConstants.GET_MO_WITH_CRITERIA)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				Vector networkDetails = cre.getMatchedManagedObjects();
				int count = customModel.getRowCount();
				if(devicesVector == null)
					devicesVector = new Vector();
				if(attributesVector == null)
					constructAttributesVector();
				/*if(count !=0)
				  {
				  for(int j=count-1;j >=0;j--)
				  {
				  customModel.removeRow(j);
				  }
				  }*/
				for(int i=0;i<networkDetails.size();i++)
				{
					boolean flag = false;
					if(devicesVector != null)
					{
						String str = networkDetails.elementAt(i).toString();
						for(int j=0;j<devicesVector.size();j++)
						{
							Vector temp = (Vector)devicesVector.elementAt(j);
							if(str.equalsIgnoreCase(temp.elementAt(0).toString()))
							{
								flag = true;
								break;
							}
						}
					}
					if( ! flag)
					{	
						//MultipleListSelectionObject object = new MultipleListSelectionObject(networkDetails.elementAt(i));
						String object = networkDetails.elementAt(i).toString();
						Vector v = new Vector();
						v.addElement(new MultipleListSelectionObject(new Object()));
						v.addElement(object);
						customModel.addRow(v);
						devicesVector.addElement(new DeviceAttributesObject(networkDetails.elementAt(i)));
					}
				}
				this.setVisible(true);	
			}
		}

		else if(workId == NmsConfigConstants.UPLOAD_CONFIGURATION)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				saveUploadedTask(cre.getDeviceConfiguration());
			}
			else
			{
				saveUploadedTask(null);
			}
		}
	}
	
	private void saveUploadedTask(String uploadedTask)
	{
		if(uploadedTask != null)
		{
			ConfigTask ct = null;
			try
			{
				ct = new ConfigTask(uploadedTask);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			Device[] device = ct.getDevices();
			String deviceName = device[0].getDeviceName();
			String protocolName = ct.getProtocol();
			Attribute[] attrList = ct.getConfigAttributes();

			Properties prop = attrList[0].getProperties();
			Vector keyVector = new Vector();
			String[] columnHeaders = new String[prop.size()];
			int tempVar = 0;
			for(Enumeration en = prop.keys();en.hasMoreElements();)
			{
				String key = (String)en.nextElement();
				keyVector.add(key);
				columnHeaders[tempVar] = key;
				tempVar++;
			}

			String[][] values = new String[attrList.length][keyVector.size()];			
			for(int i = 0; i < device.length;i++)
			{
				for(int index = 0; index < attrList.length;index++)
				{
					Properties tempProp = attrList[index].getProperties();
					for(int j = 0; j < keyVector.size();j++) 
					{
						values[index][j] = (String)tempProp.getProperty((String)keyVector.elementAt(j));
					}
				}
			}

			UploadDetails ud = new UploadDetails(configPanel, uploadedTask, protocolName, deviceName, columnHeaders,values);
			ud.setVisible(true);

		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Upload failed"), resourceBundle.getString("Info"), "info");
		}

		dispose();
	}
	


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

	public void updateButtonActionPerformed()
	{
		int row = attributesTable.getSelectedRow();
		if(row != -1)
		{
			if(isSnmp)
			{
				row = attributesTable.getSelectedRow();
				if(row != -1)
				{
					String value = "";
					if(row == 3)
					{
						String temp = versionCombo.getSelectedItem().toString();
						if(temp.indexOf("3") == -1)
							isVersion3 = false;
						else
							isVersion3 = true;
						value = temp;
					}
					else
					{
						value = valueText.getText().trim();
					}
					attributesTable.setValueAt(value,row,1);
					if(row != attributesTable.getRowCount()-1)
					{
						//giveNewSelection(row+1);
					}
					//else
					//giveNewSelection(0);
					if(devicesTable.getSelectedRow() != -1)
					{
						DeviceAttributesObject deviceData = (DeviceAttributesObject)devicesVector.elementAt(devicesTable.getSelectedRow());
						for(int i=0;i<attributesTable.getRowCount();i++)
						{
							deviceData.setElementAt(attributesTable.getValueAt(i,1),i);
						}
					}
				}	
			}

			else
			{
				row = attributesTable.getSelectedRow();
				if(row != -1)
				{	
					String value = valueText.getText();
					attributesTable.setValueAt(value,row,1);
				}	
				if(devicesTable.getSelectedRow() != -1)
				{
					DeviceAttributesObject deviceData = (DeviceAttributesObject)devicesVector.elementAt(devicesTable.getSelectedRow());
					for(int i=0;i<attributesTable.getRowCount();i++)
					{
						deviceData.setElementAt(attributesTable.getValueAt(i,1),i);
					}
				}
			}		
		}
	}




	//<Begin__class_devicesTable_devicesTable_conn1>

 class devicesTable_devicesTable_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_devicesTable_devicesTable_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  devicesTableMousePressedEvent(arg0);
     }
//<UserCode_End_Connection_devicesTable_devicesTable_conn1>
 }//<End__class_devicesTable_devicesTable_conn1>

	public void devicesTableMousePressedEvent(MouseEvent me)
	{
		Point point = me.getPoint();
		DeviceAttributesObject deviceData = (DeviceAttributesObject)devicesVector.elementAt(devicesTable.getSelectedRow());
		Vector dataVector = new Vector();
		for(int i=0;i<attributesVector.size();i++)
		{
			Vector v = new Vector();
			v.addElement(attributesVector.elementAt(i));
			v.addElement(deviceData.elementAt(i));
			dataVector.addElement(v);
		}
		Vector columnVector = new Vector();
		columnVector.addElement(resourceBundle.getString("Property"));
		columnVector.addElement(resourceBundle.getString("Value"));
		tableModel.setDataVector(dataVector,columnVector);

		if(devicesTable.columnAtPoint(point) == 0)
		{
			int row = devicesTable.rowAtPoint(point);
			MultipleListSelectionObject object = (MultipleListSelectionObject)devicesTable.getValueAt(row,0);

			if(((MultipleListSelectionObject)object).getState())
			{
				((MultipleListSelectionObject)object).setFalseState();
				for(int i=0;i<devicesTable.getRowCount();i++)
				{
					MultipleListSelectionObject temp = (MultipleListSelectionObject)devicesTable.getValueAt(i,0);
					if( i != row)
						temp.setFalseState();
				}
			}
			else
			{
				((MultipleListSelectionObject)object).setTrueState();
				for(int i=0;i<devicesTable.getRowCount();i++)
				{
					MultipleListSelectionObject temp = (MultipleListSelectionObject)devicesTable.getValueAt(i,0);
					if( i != row)
						temp.setFalseState();
				}
			}		

			devicesTable.repaint();
		}
	}

	public void constructAttributesVector()
	{
		Object[][] deviceAttributes = configPanel.configMainUI.getDeviceAttributes(protocol);

		tableModel.setDataVector(deviceAttributes, configPanel.configMainUI.columnObject);

		attributesObject = new DeviceAttributesObject(deviceAttributes);

		attributesVector = configPanel.configMainUI.getDeviceAttributeNames(deviceAttributes);
	}


	//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  addButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>

	public void addButtonActionPerformed()
	{
		String str = deviceNameText.getText().trim();
		if(str.equals(""))
		{
			Toolkit.getDefaultToolkit().beep();
			return;
		}

		boolean flag = false;
		if(devicesVector != null)
		{
			for(int i=0;i<devicesVector.size();i++)
			{
				Vector temp = (Vector)devicesVector.elementAt(i);
				if(str.equalsIgnoreCase(temp.elementAt(0).toString()))
				{
					flag = true;
					break;
				}
			}
		}

		if( ! flag)
		{
			Vector v = new Vector();
			v.addElement(new MultipleListSelectionObject(new Object()));
			v.addElement(str);
			customModel.addRow(v);
			if(devicesVector == null)
				devicesVector = new Vector();
			if(attributesVector == null)
				constructAttributesVector();
			devicesVector.addElement(new DeviceAttributesObject(str));
			deviceNameText.requestFocus();
			deviceNameText.setText("");
		}

		else
		{
			//JOptionPane.showMessageDialog(null,resourceBundle.getString("Device name already exists in the list"));
			configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Device name already exists in the list"), resourceBundle.getString("Info"), "info");
			deviceNameText.requestFocus();
			deviceNameText.selectAll();
			return;
		}
	}




	//<Begin__class_deviceNameText_deviceNameText_conn1>

 class deviceNameText_deviceNameText_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_deviceNameText_deviceNameText_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
  addTextFieldKeyReleasedEvent(arg0);
     }
//<UserCode_End_Connection_deviceNameText_deviceNameText_conn1>
 }//<End__class_deviceNameText_deviceNameText_conn1>

	public void addTextFieldKeyReleasedEvent(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_ENTER)
		{
			addButtonActionPerformed();
		}
	}


	//<Begin__class_JButton1_JButton1_conn2>

 class JButton1_JButton1_conn2 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn2>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  contextSensitiveHelpActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn2>
 }//<End__class_JButton1_JButton1_conn2>

	public void contextSensitiveHelpActionPerformed()
	{
		if(helpDialog == null)
		{
			helpDialog = new HelpDialog(configPanel, this);
		}
		else
		{
			if(!helpDialog.isVisible())
			{
				configPanel.configClientUtils.centerWindow(helpDialog);
			}
		}

		helpDialog.setVisible(true);
	}

	public String getHelpFor(String key)
	{
		String helpContent = "";

		if(key.equals(resourceBundle.getString("Task Upload")))
		{
			helpContent = "\t" + resourceBundle.getString("Upload task functionality involves retrieving data from the device specified and substituting for the attributes of the selected task. Data are values that are set for corresponding attributes defined in the selected task. Successfully Uploaded task will overwrite the already existing values for the attributes in that Task. The uploading of data can be performed only for tasks which are not defined as Templates.");
		}

		return helpContent;
	}

	public String getTitle()
	{
		return resourceBundle.getString("Task Upload");
	}

	public void actionPerformed(ActionEvent ae)
	{
		configPanel.configClientUtils.showHelp(helpKey);
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
