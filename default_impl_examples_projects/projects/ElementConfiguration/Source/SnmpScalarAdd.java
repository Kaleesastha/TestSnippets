
//$Id: SnmpScalarAdd.java,v 1.1 2006/08/29 13:56:51 build Exp $
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


public class SnmpScalarAdd extends JDialog implements MibHandlerListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField oidText = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JTextField labelText = null;
	javax.swing.JLabel commonLabel = null;
	javax.swing.JTextField valueText = null;
	javax.swing.JLabel typeLabel = null;
	javax.swing.JComboBox typeCombo = null;
	javax.swing.JLabel valueLabelForTemplate = null;
	javax.swing.JComboBox templateCombo = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton3 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;
	private SnmpScalarPanel parentPanel = null;
	private boolean isTemplate = false;

	private String columnOid = null;
	private Vector numericIndexes = null;
	private String columnLabel = null;
	private String columnType = null;

	public SnmpScalarAdd(ConfigPanel configPanel,SnmpScalarPanel panel,boolean template)
	{
		super(configPanel.configClientUtils.getParentDialog(panel));

		this.configPanel = configPanel;
		isTemplate = template;
		parentPanel = panel;
		
		applet = configPanel.applet;

		init();
		configInit();
	}


	private void configInit()
	{
		URL docBase = configPanel.applet.getDocumentBase();
		String test = docBase+"../images/taskconfig.png" ;

		ImageIcon image = configPanel.configClientUtils.getImage(test);
		JLabel1.setIcon(image);

		String [] comboArray = configPanel.mibHandler.snmpDataTypes;

		for(int i =0 ; i < comboArray.length;i++)
		{
			typeCombo.addItem(comboArray[i]);
		}
		if(isTemplate)
		{
			commonLabel.setText(resourceBundle.getString("Place Holder"));
			templateCombo.addItem("NEInput");
			templateCombo.addItem("InventoryInput");
			templateCombo.addItem("UserInput");
			templateCombo.addItem("DataSourceParam");
			templateCombo.addItem("None");
			//typeLabel.setVisible(false);
			//typeCombo.setVisible(false);
		}
		else
		{
			commonLabel.setText(resourceBundle.getString("Value"));
			valueLabelForTemplate.setVisible(false);
			templateCombo.setVisible(false);
			typeLabel.setVisible(true);
			typeCombo.setVisible(true);
		}

		configPanel.mibHandler.addMibHandlerListener(this);
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
            JLabel5.setText(resourceBundle.getString("Snmp Task Configuration"));
            JLabel5.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JLabel1.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JPanel4.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JLabel2.setText(resourceBundle.getString("Object Identifier (OID)"));
            JLabel2.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel6.setText(resourceBundle.getString("Label  "));
            JLabel6.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

          try
          {
            commonLabel.setForeground(new Color(-16764109));
            commonLabel.setText(resourceBundle.getString("Value "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+commonLabel,ex); 
          }

//<UserCode_Begin_Bean_commonLabel>

//<UserCode_End_Bean_commonLabel>

          try
          {
            typeLabel.setForeground(new Color(-16764109));
            typeLabel.setText(resourceBundle.getString("Type "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+typeLabel,ex); 
          }

//<UserCode_Begin_Bean_typeLabel>

//<UserCode_End_Bean_typeLabel>

          try
          {
            typeCombo.setPreferredSize(new Dimension(125,21));
            typeCombo.setMinimumSize(new Dimension(125,21));
            typeCombo.setMaximumSize(new Dimension(32767,21));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+typeCombo,ex); 
          }

//<UserCode_Begin_Bean_typeCombo>

//<UserCode_End_Bean_typeCombo>

          try
          {
            valueLabelForTemplate.setText(resourceBundle.getString("Value"));
            valueLabelForTemplate.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+valueLabelForTemplate,ex); 
          }

//<UserCode_Begin_Bean_valueLabelForTemplate>

//<UserCode_End_Bean_valueLabelForTemplate>

          try
          {
            templateCombo.setPreferredSize(new Dimension(125,21));
            templateCombo.setMinimumSize(new Dimension(125,21));
            templateCombo.setMaximumSize(new Dimension(32767,21));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+templateCombo,ex); 
          }

//<UserCode_Begin_Bean_templateCombo>

//<UserCode_End_Bean_templateCombo>

          try
          {
            JButton1.setText(resourceBundle.getString("Browse Mibs"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

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
            JButton2.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            JButton3.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>

//<UserCode_End_Bean_JButton3>
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+21,JButton2.getPreferredSize().height+0));

  
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
        this.setSize(getPreferredSize().width+490,getPreferredSize().height+400); 
          setTitle(resourceBundle.getString("SnmpScalarAdd"));
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
          showStatus(resourceBundle.getString("Error in init method"), ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
          setTitle(resourceBundle.getString("SNMP Task Configuration"));

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
        JLabel5= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel4= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        oidText= new javax.swing.JTextField();
        JLabel6= new javax.swing.JLabel();
        labelText= new javax.swing.JTextField();
        commonLabel= new javax.swing.JLabel();
        valueText= new javax.swing.JTextField();
        typeLabel= new javax.swing.JLabel();
        typeCombo= new javax.swing.JComboBox();
        valueLabelForTemplate= new javax.swing.JLabel();
        templateCombo= new javax.swing.JComboBox();
        JPanel6= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();

  
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
JPanel1.add(JLabel5);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel2.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel4.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(oidText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel6,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(labelText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(commonLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(valueText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(typeLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(typeCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(valueLabelForTemplate,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(templateCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JPanel6,cons);
JPanel6.setLayout(new FlowLayout(2,5,5));
JPanel6.add(JButton1);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(JButton2);
JPanel3.add(JButton3);

  
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
	//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  cancelButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>




	public void showStatus(String message)
  {
		//<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);
     //<End_showStatus_String>
	}
	public void showStatus(String message, Exception ex)
  {
		//<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :" + message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
	}





	public SnmpScalarAdd()
  {
		//<Begin_SnmpScalarAdd>
    pack();
  
    //<End_SnmpScalarAdd>
	}

	public SnmpScalarAdd(java.applet.Applet applet)
  {
		//<Begin_SnmpScalarAdd_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_SnmpScalarAdd_java.applet.Applet>
	}


	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  browseMibsButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

	public void browseMibsButtonActionPerformed()
	{
		configPanel.mibHandler.addMibHandlerListener(this);
		configPanel.mibHandler.showMib(this, "SCALAR");
	}

	public void setResult(Properties data)
	{
		String oid = data.getProperty("identifier");
		String label = data.getProperty("label");
		String type = data.getProperty("type");

		String columnar = data.getProperty("columnar");
		Vector stringIndexes = null;
		if(columnar != null)
		{
			columnOid = oid;
			columnLabel = label;
			columnType = type;
			stringIndexes = (Vector)data.get("indexes");
			numericIndexes = (Vector)data.get("numericIndexes");

			SnmpTableAdd s = new SnmpTableAdd(configPanel,this,false);
			s.setVisible(true);
			configPanel.configClientUtils.centerWindow(s);
			s.populateUI(stringIndexes);
		}
		else
		{
			oidText.setText(oid);
			labelText.setText(label);

			if(isTemplate)
			{
				valueText.setText(label);
			}

			for(int i = 0;i < typeCombo.getItemCount();i++)
			{
				String temp = typeCombo.getItemAt(i).toString();
				if(temp.equalsIgnoreCase(type))
				{
					typeCombo.setSelectedIndex(i);
					return;
				}
			}
			typeCombo.addItem(type);
			typeCombo.setSelectedItem(type);
		}
	}

	public void cancelButtonActionPerformed()
	{
		this.setVisible(false);
		this.dispose();
	}




	//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  okButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>

	public void okButtonActionPerformed()
	{	
		String oid = oidText.getText().trim();
		String label = labelText.getText().trim();
		String value = valueText.getText().trim();
		String type = typeCombo.getSelectedItem().toString();
		// The if check is added by vkarthik as a L3 fix for the message id 1469844
		if(!isTemplate)
		{
 	        if(!configPanel.mibHandler.isValueInRange(value, oid))
		{
			JOptionPane.showMessageDialog(null, resourceBundle.getString("The variable value is outside range. Kindly give proper values"), resourceBundle.getString("Message"), JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		}
		if(isTemplate)
		{
			String inputType = (String)templateCombo.getSelectedItem();
			
			if(!inputType.equals("None"))
			{
				value = "$"+inputType+"$"+value; 
			}
		}
		
		if(oid.equals("") || label.equals("") || value.equals(""))
		{
			JOptionPane.showMessageDialog(null, resourceBundle.getString("Some of the fields are left empty. Please fill them"), resourceBundle.getString("Message"), JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if((!((parentPanel.getAddedScalarValues()).contains(oid))) || parentPanel.isModify())
		{
			parentPanel.updateEntries(oid, label, type, value);
		}
		else
		{
			JOptionPane.showMessageDialog(null, resourceBundle.getString("The Object is already added"), resourceBundle.getString("Message"), JOptionPane.INFORMATION_MESSAGE);
			oidText.setText("");
			labelText.setText("");
			valueText.setText("");
			oidText.requestFocus();
			return;
		}
		parentPanel.JButton2.setEnabled(true);
		parentPanel.JButton3.setEnabled(true);
		parentPanel.table.setRowSelectionInterval(parentPanel.tableModel.getRowCount()-1, parentPanel.tableModel.getRowCount()-1);
		this.setVisible(false);
		this.dispose();
	}

	public void populateUI(String oid, String label, String type, String value)
	{
		oidText.setText(oid);
		labelText.setText(label);
		
		if(isTemplate)
		{
			if(value != null)
			{
				if(value.indexOf("NEInput") != -1)
				{
					templateCombo.setSelectedItem("NEInput");
				}
				else if(value.indexOf("InventoryInput") != -1)
				{
					templateCombo.setSelectedItem("InventoryInput");
				}
				else if(value.indexOf("UserInput") != -1)
				{
					templateCombo.setSelectedItem("UserInput");
				}
				else if(value.indexOf("DataSourceParam") != -1)
				{
					templateCombo.setSelectedItem("DataSourceParam");
				}
				else
				{
					templateCombo.setSelectedItem("None");
				}
			
				int lastIndex = value.lastIndexOf('$');
				
				if(lastIndex != -1)
				{	
					value = value.substring(lastIndex+1);
				}
			}
		}
		
		valueText.setText(value);
		
		for(int i = 0;i < typeCombo.getItemCount();i++)
		{
			String temp = typeCombo.getItemAt(i).toString();
			if(temp.equalsIgnoreCase(type))
			{
				typeCombo.setSelectedIndex(i);
				return;
			}
		}
		
		typeCombo.addItem(type);
		typeCombo.setSelectedItem(type);
	}

	public void updateEntries(Vector valueVector)
	{
		String tempString = configPanel.mibHandler.getEncodedValue(numericIndexes,valueVector);
		if(tempString.equals(""))
		{
			return;
		}
		String encodedOid = columnOid + "." + tempString;
		oidText.setText(encodedOid);
		labelText.setText(columnLabel);

		if(isTemplate)
		{
			valueText.setText(columnLabel);
		}

		for(int i = 0;i < typeCombo.getItemCount();i++)
		{
			String temp = typeCombo.getItemAt(i).toString();
			if(temp.equalsIgnoreCase(columnType))
			{
				typeCombo.setSelectedIndex(i);
				return;
			}
		}
		typeCombo.addItem(columnType);
		typeCombo.setSelectedItem(columnType);

	}

	 
	

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}




















