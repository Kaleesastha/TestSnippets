

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;




//$Id: AdvancedLogProperties.java,v 1.2 2007/02/22 15:03:07 srajeswari Exp $


import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class AdvancedLogProperties extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField keyText = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField displayText = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JComboBox levelText = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JCheckBox JCheckBox1 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	Hashtable cloneHash = null;
	boolean fault = false;
	boolean presumption = false;
	String backUpName = null;
	String backUpDisplay = null;
	String backUpLevel = null;
	String backUpState = null;
	String faultBackUpName = null;
	String faultBackUpDisplay = null;
	String faultBackUpLevel = null;
	String faultBackUpState = null;
	int faultRow = 0;
	Hashtable keyNodeHash = null;  
	boolean addFlag = true;  





















   


  public AdvancedLogProperties()
  {
    //<Begin_AdvancedLogProperties>
    this.init();
  
    //<End_AdvancedLogProperties>
  }

  public AdvancedLogProperties(java.applet.Applet applet)
  {
    //<Begin_AdvancedLogProperties_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_AdvancedLogProperties_java.applet.Applet>
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
            JPanel2.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
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
            JPanel4.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JLabel1.setText(resourceBundle.getString("Key Name *"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel2.setText(resourceBundle.getString("Display Name *"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel3.setText(resourceBundle.getString("Log Level"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel4.setText(resourceBundle.getString("Enable Logging ?"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

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

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Key Name","Display Name","Log Level","Enable Logging"});
//<UserCode_End_Bean_tableModel>
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+0,JButton3.getPreferredSize().height+4));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+0,JButton2.getPreferredSize().height+4));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+10,JButton1.getPreferredSize().height+4));

  
          //<End_setUpProperties>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("Key Name"),resourceBundle.getString("Display Name"),resourceBundle.getString("Log Level"),resourceBundle.getString("Enable Logging")});	
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
        setPreferredSize(new Dimension(getPreferredSize().width+450,getPreferredSize().height+420)); 
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
	table.getTableHeader().setReorderingAllowed(false);
	table.setDefaultEditor(Object.class,null);
	table.setDefaultRenderer(Object.class,new CustomRenderer());
	JButton1.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JButton2.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JButton3.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	levelText.addItem("Summary");
	levelText.addItem("Intermediate Details");
	levelText.addItem("Verbose");
        levelText.addItem("Debug");//no internationalisation
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      levelText_levelText_conn1 levelText_levelText_conn11 =  new levelText_levelText_conn1();
      levelText.addKeyListener(levelText_levelText_conn11);
      JCheckBox1_JCheckBox1_conn3 JCheckBox1_JCheckBox1_conn31 =  new JCheckBox1_JCheckBox1_conn3();
      JCheckBox1.addActionListener(JCheckBox1_JCheckBox1_conn31);
      JCheckBox1_JCheckBox1_conn2 JCheckBox1_JCheckBox1_conn21 =  new JCheckBox1_JCheckBox1_conn2();
      JCheckBox1.addKeyListener(JCheckBox1_JCheckBox1_conn21);
      JCheckBox1_JCheckBox1_conn1 JCheckBox1_JCheckBox1_conn11 =  new JCheckBox1_JCheckBox1_conn1();
      JCheckBox1.addMouseListener(JCheckBox1_JCheckBox1_conn11);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      JButton3.addActionListener(JButton3_JButton3_conn11);
      keyText_keyText_conn1 keyText_keyText_conn11 =  new keyText_keyText_conn1();
      keyText.addKeyListener(keyText_keyText_conn11);
      displayText_displayText_conn1 displayText_displayText_conn11 =  new displayText_displayText_conn1();
      displayText.addKeyListener(displayText_displayText_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel4= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        keyText= new javax.swing.JTextField();
        JLabel2= new javax.swing.JLabel();
        displayText= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        levelText= new javax.swing.JComboBox();
        JLabel4= new javax.swing.JLabel();
        JCheckBox1= new javax.swing.JCheckBox();
        JPanel5= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
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
inset = new Insets(0,5,10,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(10,5,0,5);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(keyText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(displayText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(levelText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JCheckBox1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,2,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JPanel5,cons);
JPanel5.setLayout(new FlowLayout(2,5,5));
JPanel5.add(JButton1);
JPanel5.add(JButton2);
JPanel5.add(JButton3);

  
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
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

	public void setValues(Hashtable mainHash,boolean flag)
	{
		addFlag = flag;
		keyText.setText("");
		displayText.setText("");
		levelText.setSelectedIndex(0);
		JCheckBox1.setSelected(false);
		if(addFlag)
		{
			tableMousePressedEvent();
		}
		if(mainHash != null)
		{
			int count = tableModel.getRowCount();
			if(count !=0)
			{
				for(int j=count-1;j >=0;j--)
				{
					tableModel.removeRow(j);
				}
			}
			cloneHash = mainHash;
			if(cloneHash.get("keyNodeVect") == null)
				keyNodeHash = new Hashtable();
			else
				keyNodeHash = (Hashtable)cloneHash.get("keyNodeVect");
			Enumeration enumerate = keyNodeHash.keys();
			while(enumerate.hasMoreElements())
			{
				Vector v = new Vector();
				Hashtable key = (Hashtable)keyNodeHash.get(enumerate.nextElement());
				v.add(key.get("Name"));
				v.add(key.get("DisplayName"));
				if(key.get("LogLevel").toString().equals("1"))
				{
					v.add("Summary");
				}
				else if(key.get("LogLevel").toString().equals("2"))
				{
					v.add("Intermediate Details");
				}
				else if(key.get("LogLevel").toString().equals("3"))//no internationalisation
				{
					v.add("Verbose");//no internationalisation
				}
                                else 
                                    {
                                        v.add("Debug");//no internationalisation
                                    }
				//v.add(key.get("LogLevel"));
				v.add(key.get("Logging"));
				tableModel.addRow(v);
			}
		}
		else
		{
			cloneHash = new Hashtable();
		}
		if(!addFlag)
		{
			JButton1.setEnabled(false);
			JButton3.setEnabled(false);
			keyText.setEditable(false);
			if(table.getRowCount() != 0 )
			{
				table.getSelectionModel().addSelectionInterval(0,0);
				keyText.setText(table.getValueAt(0,0).toString());
				displayText.setText(table.getValueAt(0,1).toString());
				levelText.setSelectedItem(table.getValueAt(0,2).toString());
				if(table.getValueAt(0,3).toString().equalsIgnoreCase("true"))
				{
					JCheckBox1.setSelected(true);
				}
				else
				{
					JCheckBox1.setSelected(false);
				}
			}
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
//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  addButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>
	
	public void addButtonActionPerformed()
	{
		if(keyText.getText().trim().equals("") || displayText.getText().trim().equals("")) //|| levelText.getText().trim().equals(""))
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Some of the field(s) are empty.Please fill them"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	/*	else if (keyText.getText().equalsIgnoreCase("menuName") || keyText.getText().equalsIgnoreCase("TYPE") || keyText.getText().equalsIgnoreCase("iconName") || keyText.getText().equalsIgnoreCase("MAP_FILTER"))
		{
			JOptionPane.showMessageDialog(null,"Sorry..You cannot use this key");
			return;
		} */
		boolean b = checkForDuplication();
		if(b)
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Key already exists"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
		/*	if(presumption)
			tableModel.removeRow(table.getRowCount()-1);
			table.getSelectionModel().clearSelection();
			clearAll();	*/
			return;	
		} 
		Vector v = new Vector();
		v.add(keyText.getText());
		v.add(displayText.getText());
		v.add(levelText.getSelectedItem());
		if(JCheckBox1.isSelected())
			v.add("true");
		else
			v.add("false");
		tableModel.addRow(v);
		table.getSelectionModel().clearSelection();
		Hashtable temp = new Hashtable();
		temp.put("Name",keyText.getText());
		temp.put("DisplayName",displayText.getText());
		String str = levelText.getSelectedItem().toString();
		if(str.equals("Summary"))
		{
			temp.put("LogLevel","1");
		}
		else if(str.equals("Intermediate Details"))
		{
			temp.put("LogLevel","2");
		}
		else if(str.equals("Verbose"))//no internationalisation
		{
			temp.put("LogLevel","3");//no internationalisation
		}
                else 
                    {
                        temp.put("LogLevel","4");//no internationalisation
                    }
		String state = "";
		if(JCheckBox1.isSelected())
			state = "true";
		else
			state="false";
		temp.put("Logging",state);
		keyNodeHash.put(keyText.getText(),temp);
		cloneHash.put("keyNodeVect",keyNodeHash);
		clearAll();	
		tableMousePressedEvent();
	}
	
	public void modifyButtonActionPerformed()
	{
		int row = table.getSelectedRow();
		if(row == -1)
		{
			return;
		}
		else
		{
			boolean modifyFlag = true;
			for(int i=0;i<table.getRowCount();i++)
			{
				String temp = table.getValueAt(i,0).toString();
				if(temp.equalsIgnoreCase(keyText.getText()))
				{
					if(i == row)
					{
						continue;
					}
					modifyFlag = false;
				}
			}
			boolean b = checkForDuplication();
			if(modifyFlag)
			{
					if(addFlag)
					{
						keyNodeHash.remove(backUpName);
					}
					table.setValueAt(keyText.getText(),row,0);
					table.setValueAt(displayText.getText(),row,1);
					table.setValueAt(levelText.getSelectedItem(),row,2);
					if(JCheckBox1.isSelected())
						table.setValueAt("true",row,3);
					else
						table.setValueAt("false",row,3);
					Hashtable temp = new Hashtable();
					temp.put("Name",keyText.getText());
					temp.put("DisplayName",displayText.getText());
					String str = levelText.getSelectedItem().toString();
					if(str.equals("Summary"))
					{
						temp.put("LogLevel","1");
					}
					else if(str.equals("Intermediate Details"))
					{
						temp.put("LogLevel","2");
					}
					else if(str.equals("Verbose"))
					{
						temp.put("LogLevel","3");
					}
                                        else
					{
						temp.put("LogLevel","4");
					}
					String state = "";
					if(JCheckBox1.isSelected())
						state = "true";
					else
						state="false";
					temp.put("Logging",state);
					keyNodeHash.put(keyText.getText(),temp);
					cloneHash.put("keyNodeVect",keyNodeHash);
					if(addFlag)
					{
						table.getSelectionModel().clearSelection();	
						clearAll();
						tableMousePressedEvent();
					}
			}
			else
			{
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Key already exists"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
				//escapeKeyPressed();
				return;
			}
		} 
	}
	
	public void deleteButtonActionPerformed()
	{
		int [] selectedRows = table.getSelectedRows();
		int length = selectedRows.length;
		if(length == 0)
		{
			return;
		}
		JOptionPane jp = new JOptionPane(); 
		int option =  jp.showConfirmDialog(null, resourceBundle.getString("Do you want to delete the selected item(s)"),resourceBundle.getString("Confirmation"),jp.YES_NO_OPTION);
		if (option == jp.YES_OPTION)
		{ 
			for(int j=length-1;j >=0;j--)
			{
				keyNodeHash.remove(table.getValueAt(selectedRows[j],0));
				tableModel.removeRow(selectedRows[j]);
			}	 
  	   	}
		table.getSelectionModel().clearSelection();
		clearAll();
		tableMousePressedEvent();
	}
	
	public boolean checkForDuplication()
	{
		for(int i=0;i<table.getRowCount();i++)
		{
			String temp = table.getValueAt(i,0).toString();
			if(temp.equalsIgnoreCase(keyText.getText()))
			{
				return true;
			}
		}
		return false;
		
	}


 
//<Begin__class_levelText_levelText_conn1>

 class levelText_levelText_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_levelText_levelText_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
 // keyReleasedEventForLevelText(arg0);
     }
//<UserCode_End_Connection_levelText_levelText_conn1>
 }//<End__class_levelText_levelText_conn1>
//<Begin__class_JCheckBox1_JCheckBox1_conn2>

 class JCheckBox1_JCheckBox1_conn2 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox1_JCheckBox1_conn2>



     //Listener Interface Methods Are Added Below 


     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
  //checkBoxKeyReleased();
     }



     public void keyTyped( java.awt.event.KeyEvent arg0)
     {
  //checkBoxKeyTypedEvent();
     }
 
//<UserCode_End_Connection_JCheckBox1_JCheckBox1_conn2>
 }//<End__class_JCheckBox1_JCheckBox1_conn2>
//<Begin__class_JCheckBox1_JCheckBox1_conn1>

 class JCheckBox1_JCheckBox1_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox1_JCheckBox1_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
 // checkBoxMousePressed();
     }



     public void mouseReleased( java.awt.event.MouseEvent arg0)
     {
  //checkBoxMouseReleased();
     }
 
//<UserCode_End_Connection_JCheckBox1_JCheckBox1_conn1>
 }//<End__class_JCheckBox1_JCheckBox1_conn1>
//<Begin__class_keyText_keyText_conn1>

 class keyText_keyText_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_keyText_keyText_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
  //keyReleasedEventForNameText(arg0);
     }



     public void keyPressed( java.awt.event.KeyEvent arg0)
     {
  //keyPressedEventForNameText(arg0);
     }
 
//<UserCode_End_Connection_keyText_keyText_conn1>
 }//<End__class_keyText_keyText_conn1>
//<Begin__class_displayText_displayText_conn1>

 class displayText_displayText_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_displayText_displayText_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
 // keyReleasedEventForDisplayText(arg0);
     }
//<UserCode_End_Connection_displayText_displayText_conn1>
 }//<End__class_displayText_displayText_conn1>
	
	public void keyReleasedEventForNameText(KeyEvent ev)
	{
			handleKeyReleasedEvent(ev,0);
	}

	public void keyReleasedEventForDisplayText(KeyEvent ev)
	{
			handleKeyReleasedEvent(ev,1);	
	}
	
	public void keyReleasedEventForLevelText(KeyEvent ev)
	{
			handleKeyReleasedEvent(ev,2);	
	}

	public void checkBoxKeyReleased()
	{
	/*	System.out.println("Check Bos Key Released Called");
		String str = "";
		if(JCheckBox1.isSelected())
			str = "true";
		else
			str = "false";
		table.setValueAt(str,table.getSelectedRow(),3); */
	}
	
	public void checkBoxMousePressed()
	{
	/*	System.out.println("Check Box Mouse Pressed Called");
		String str = "";
		if(JCheckBox1.isSelected())
			str = "true";
		else
			str = "false";
		table.setValueAt(str,table.getSelectedRow(),3); */
	}

	public void checkBoxMouseReleased()
	{
	/*		String str = "";
		if(JCheckBox1.isSelected())
			str = "true";
		else
			str = "false";
		table.setValueAt(str,table.getSelectedRow(),3); */
	}

	public void keyPressedEventForNameText(KeyEvent ev)
	{
		if(keyText.getText().trim().equals("") && table.getSelectedRow() == -1 && ev.getKeyCode() != KeyEvent.VK_SPACE)
		{
			Vector v = new Vector();
			v.add("");
			v.add(displayText.getText().trim());
			v.add(levelText.getSelectedItem());
			if(JCheckBox1.isSelected())
				v.add("true");
			else
				v.add("false");
			tableModel.addRow(v);
			presumption = true;
			table.getSelectionModel().addSelectionInterval(table.getRowCount()-1,table.getRowCount()-1);
		}
	/*	if(ev.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			escapeKeyPressed();
		} */
		if(ev.getKeyCode() == KeyEvent.VK_DELETE || ev.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
			if(keyText.getText().equals(""))
			{
				if(!presumption)
				{
					fault = true;
					int i = table.getSelectedRow();
					faultBackUpName = new String(backUpName);
					faultBackUpDisplay = new String(backUpDisplay);
					faultBackUpLevel = new String(backUpLevel);
					faultBackUpState = new String(backUpState);
					faultRow = i;
				}
				else
				{
					tableModel.removeRow(table.getRowCount()-1);
				}
			}
		}
	}

	public void handleKeyReleasedEvent(KeyEvent ev,int column)
	{
		if(ev.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			escapeKeyPressed();
			return;
		}
		int i = table.getSelectedRow();
		if(i != -1)
		{
			String str = "";
			if(column == 0)
				str = keyText.getText().trim();
			else if(column == 1)
			{
				if(keyText.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,resourceBundle.getString("Please specify the name before associating properties"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				str = displayText.getText().trim();
			}
			else if(column == 2)
			{
				if(keyText.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,resourceBundle.getString("Please specify the name before associating properties"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				str = levelText.getSelectedItem().toString();
			}
			table.setValueAt(str,i,column);
		}
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
		//presumption = false;
		if(addFlag)
		{
			if(row == -1)
			{
				clearAll();		
				JButton1.setEnabled(true);
				JButton2.setEnabled(false);
				JButton3.setEnabled(false);
			}
			else
			{
				backUpName = table.getValueAt(row,0).toString();
				backUpDisplay = table.getValueAt(row,1).toString();
				backUpLevel = table.getValueAt(row,2).toString();
				backUpState = table.getValueAt(row,3).toString();
				keyText.setText(table.getValueAt(row,0).toString());
				displayText.setText(table.getValueAt(row,1).toString());
				levelText.setSelectedItem(table.getValueAt(row,2).toString());
				if(table.getValueAt(row,3).toString().equals("true"))
					JCheckBox1.setSelected(true);
				else
					JCheckBox1.setSelected(false);
				JButton1.setEnabled(false);
				JButton2.setEnabled(true);
				JButton3.setEnabled(true);
			}	
		}
		else
		{
			if(row == -1)
			{
				JButton2.setEnabled(false);
				clearAll();
			}
			else
			{
				JButton2.setEnabled(true);
				keyText.setText(table.getValueAt(row,0).toString());
				displayText.setText(table.getValueAt(row,1).toString());
				levelText.setSelectedItem(table.getValueAt(row,2).toString());
				if(table.getValueAt(row,3).toString().equals("true"))
					JCheckBox1.setSelected(true);
				else
					JCheckBox1.setSelected(false);
			}
		}
	}

	public void escapeKeyPressed()
	{
			if(presumption)
			{
				tableModel.removeRow(table.getRowCount()-1);
				table.getSelectionModel().clearSelection();
				clearAll();
				presumption = false;
				return;
			}	
			else
			{
				int row = table.getSelectedRow();
				table.setValueAt(backUpName,row,0);
				table.setValueAt(backUpDisplay,row,1);
				table.setValueAt(backUpLevel,row,2);
				table.setValueAt(backUpState,row,3);
				table.getSelectionModel().clearSelection();
				clearAll();
				return;
			}
	}

	public void clearAll()
	{
		keyText.setText("");
		displayText.setText("");
		levelText.setSelectedIndex(0);
		JCheckBox1.setSelected(false);
		keyText.requestFocus();
	}


//<Begin__class_JCheckBox1_JCheckBox1_conn3>

 class JCheckBox1_JCheckBox1_conn3 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox1_JCheckBox1_conn3>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  checkBoxActionPerformed();
     }
//<UserCode_End_Connection_JCheckBox1_JCheckBox1_conn3>
 }//<End__class_JCheckBox1_JCheckBox1_conn3>
	
	public void checkBoxActionPerformed()
	{
	/*	String str = "";
		if(JCheckBox1.isSelected())
			str = "true";
		else
			str = "false";
		if(table.getSelectedRow() != -1)
			table.setValueAt(str,table.getSelectedRow(),3); */
	}

	public void checkBoxKeyTypedEvent()
	{
		String str = "";
		if(JCheckBox1.isSelected())
			str = "true";
		else
			str = "false";
		if(table.getSelectedRow() != -1)
			table.setValueAt(str,table.getSelectedRow(),3);
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}


