

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;




//$Id: ObjTypeDialog.java,v 1.2.6.1 2012/01/25 05:12:46 karen.r Exp $



import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class ObjTypeDialog extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField keyText = null;
	javax.swing.JTextField valueText = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton JButton4 = null;
	javax.swing.JButton JButton5 = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	MapIconUI mapIconUI = null;
	String faultBackUpKey = "";
	String faultBackUpValue = "";
	int faultRow = 0;
	boolean fault = false;
	String backUpKey = "";
	String backUpValue = "";
	Hashtable cloneHash = null;  
	boolean presumption = false;



















	public ObjTypeDialog(MapIconUI ui,java.applet.Applet applet)
	{
		this.applet = applet;
		mapIconUI = ui;
	}



   


  public ObjTypeDialog()
  {
    //<Begin_ObjTypeDialog>
    pack();
  
    //<End_ObjTypeDialog>
  }

  public ObjTypeDialog(java.applet.Applet applet)
  {
    //<Begin_ObjTypeDialog_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ObjTypeDialog_java.applet.Applet>
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
            JPanel3.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

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
            JPanel5.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            JLabel2.setText(resourceBundle.getString("Obj Type"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel3.setText(resourceBundle.getString("Value"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

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

          try
          {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JTextArea1.setLineWrap(true);
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setOpaque(false);
            JTextArea1.setEditable(false);
            JTextArea1.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
          }

//<UserCode_Begin_Bean_JTextArea1>

//<UserCode_End_Bean_JTextArea1>

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
            JButton4.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton4,ex); 
          }

//<UserCode_Begin_Bean_JButton4>

//<UserCode_End_Bean_JButton4>

          try
          {
            JButton5.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton5,ex); 
          }

//<UserCode_Begin_Bean_JButton5>

//<UserCode_End_Bean_JButton5>

          try
          {
            JPanel8.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel8,ex); 
          }

//<UserCode_Begin_Bean_JPanel8>

//<UserCode_End_Bean_JPanel8>

          try
          {
            JLabel5.setText(resourceBundle.getString("Configure Object Types"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Obj Type ","Value"});
//<UserCode_End_Bean_tableModel>
		JLabel5.setPreferredSize(new Dimension(JLabel5.getPreferredSize().width+0,JLabel5.getPreferredSize().height+4));
		JButton5.setPreferredSize(new Dimension(JButton5.getPreferredSize().width+0,JButton5.getPreferredSize().height+4));
		JButton4.setPreferredSize(new Dimension(JButton4.getPreferredSize().width+18,JButton4.getPreferredSize().height+4));
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+0,JButton3.getPreferredSize().height+4));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+0,JButton2.getPreferredSize().height+4));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+10,JButton1.getPreferredSize().height+4));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+17,JScrollPane1.getPreferredSize().height+2));
		JPanel7.setPreferredSize(new Dimension(JPanel7.getPreferredSize().width+10,JPanel7.getPreferredSize().height+10));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+132,JPanel3.getPreferredSize().height+249));

  
          //<End_setUpProperties>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("Obj Type"),resourceBundle.getString("Value")});	
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
        this.setSize(getPreferredSize().width+512,getPreferredSize().height+424); 
          setTitle(resourceBundle.getString("ObjTypeDialog"));
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
	table.getTableHeader().setReorderingAllowed(false);
	RuntimeConfigFrame.getCommonBuilderUIImpl().centerWindow(this);
	JLabel4.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("objtype.png","images/runtimeadmin"));
	JLabel1.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));
	JTextArea1.setText(resourceBundle.getString("Object types are used while rendering Map Symbols in the client. The Object types that can be associated with the Map Symbols are to be specified here."));
	JTextArea1.setEditable(false);
	table.setDefaultRenderer(Object.class,new CustomRenderer());
	JButton1.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JButton2.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JButton3.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JButton4.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JButton5.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
        setTitle(resourceBundle.getString("Obj Type Dialog"));
	setModal(true);
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JButton5_JButton5_conn1 JButton5_JButton5_conn11 =  new JButton5_JButton5_conn1();
      JButton5.addActionListener(JButton5_JButton5_conn11);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      JButton3.addActionListener(JButton3_JButton3_conn11);
      keyText_keyText_conn1 keyText_keyText_conn11 =  new keyText_keyText_conn1();
      keyText.addKeyListener(keyText_keyText_conn11);
      JButton4_JButton4_conn1 JButton4_JButton4_conn11 =  new JButton4_JButton4_conn1();
      JButton4.addActionListener(JButton4_JButton4_conn11);
      valueText_valueText_conn1 valueText_valueText_conn11 =  new valueText_valueText_conn1();
      valueText.addKeyListener(valueText_valueText_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        JPanel7= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel5= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        keyText= new javax.swing.JTextField();
        valueText= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        JPanel6= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JTextArea1= new javax.swing.JTextArea();
        JPanel4= new javax.swing.JPanel();
        JButton4= new javax.swing.JButton();
        JButton5= new javax.swing.JButton();
        JPanel8= new javax.swing.JPanel();
        JLabel5= new javax.swing.JLabel();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JPanel3,BorderLayout.WEST);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JLabel4,BorderLayout.CENTER);
JPanel2.add(JPanel7,BorderLayout.CENTER);
JPanel7.setLayout(new BorderLayout(5,5));
JPanel7.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(table);
JPanel7.add(JPanel5,BorderLayout.SOUTH);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(keyText,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(valueText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,2,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JPanel6,cons);
JPanel6.setLayout(new FlowLayout(2,5,5));
JPanel6.add(JButton1);
JPanel6.add(JButton2);
JPanel6.add(JButton3);
JPanel2.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JTextArea1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(1,5,5));
JPanel4.add(JButton4);
JPanel4.add(JButton5);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel8,cons);
JPanel8.setLayout(new FlowLayout(1,5,5));
JPanel8.add(JLabel5);

  
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

	public void populateUI(Hashtable hash)
	{
		cloneHash = hash;
		tableMousePressedEvent();
		if(hash.equals(new Hashtable()))
		{
			
		}
		else
		{
			Enumeration enumerate = hash.keys();
			while(enumerate.hasMoreElements())
			{	
				Vector v = new Vector();
				String temp = enumerate.nextElement().toString();
				v.add(temp);
				v.add(hash.get(temp));
				tableModel.addRow(v);
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
		if(keyText.getText().trim().equals("") || valueText.getText().trim().equals(""))
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Some of the field(s) are empty.Please fill them"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		boolean b = checkForDuplication();
		if(b)
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Key already exists"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
		/*	if(presumption)
			tableModel.removeRow(table.getRowCount()-1);
			table.getSelectionModel().clearSelection();
			keyText.setText("");
			valueText.setText(""); */
			return;	
		}
		Vector v = new Vector();
		v.add(keyText.getText());
		v.add(valueText.getText());
		tableModel.addRow(v);
		table.getSelectionModel().clearSelection();
		cloneHash.put(keyText.getText(),valueText.getText());
		tableMousePressedEvent();
		keyText.setText("");
		valueText.setText("");
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
					cloneHash.remove(backUpKey);
					table.setValueAt(keyText.getText(),row,0);
					table.setValueAt(valueText.getText(),row,1);
					cloneHash.put(keyText.getText(),valueText.getText());
					table.getSelectionModel().clearSelection();
					keyText.setText("");
					valueText.setText("");
					tableMousePressedEvent();
			}
			else
			{
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Key already exists"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
				//escapeKeyPressed();
				return;
			}
		}





/*		int row = table.getSelectedRow();
		if(row == -1)
		{
			return;
		}
		else
		{
			boolean b =	checkForDuplication();
			if(!b)
			{
				//if(table.getValueAt(row,0).toString().equalsIgnoreCase(keyText.getText()))
				//{
					cloneHash.remove(backUpKey);
					table.setValueAt(keyText.getText(),row,0);
					table.setValueAt(valueText.getText(),row,1);
					cloneHash.put(keyText.getText(),valueText.getText());
					table.getSelectionModel().clearSelection();
					keyText.setText("");
					valueText.setText("");
				//}
				tableMousePressedEvent();
			}
		} */
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
		int option =  jp.showConfirmDialog(null, resourceBundle.getString("Do you really want to delete the selected item(s)."),resourceBundle.getString("Confirmation"),jp.YES_NO_OPTION);
		if (option == jp.YES_OPTION)
		{ 
			for(int j=length-1;j >=0;j--)
			{
				cloneHash.remove(table.getValueAt(selectedRows[j],0));
				tableModel.removeRow(selectedRows[j]);
			}	 
  	   	}
		table.getSelectionModel().clearSelection();
		tableMousePressedEvent();
		keyText.setText("");
		valueText.setText("");
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
 
//<Begin__class_JButton5_JButton5_conn1>

 class JButton5_JButton5_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton5_JButton5_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  cancelButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton5_JButton5_conn1>
 }//<End__class_JButton5_JButton5_conn1>
//<Begin__class_JButton4_JButton4_conn1>

 class JButton4_JButton4_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton4_JButton4_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  okButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton4_JButton4_conn1>
 }//<End__class_JButton4_JButton4_conn1>
	
	public void okButtonActionPerformed()
	{
		mapIconUI.updateObjTypes(cloneHash);
		this.setVisible(true);
		this.dispose();
	}

	public void cancelButtonActionPerformed()
	{
		this.setVisible(true);
		this.dispose();
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
//<Begin__class_keyText_keyText_conn1>

 class keyText_keyText_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_keyText_keyText_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
  //keyReleasedEventForKeyText(arg0);
     }



     public void keyPressed( java.awt.event.KeyEvent arg0)
     {
 // keyPressedEventForKeyText(arg0);
     }
 
//<UserCode_End_Connection_keyText_keyText_conn1>
 }//<End__class_keyText_keyText_conn1>
//<Begin__class_valueText_valueText_conn1>

 class valueText_valueText_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_valueText_valueText_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
 // keyReleasedEventForValueText(arg0);
     }
//<UserCode_End_Connection_valueText_valueText_conn1>
 }//<End__class_valueText_valueText_conn1>
	
	public void tableMousePressedEvent()
	{
		int row = table.getSelectedRow();
		//presumption = false;
		if(row == -1)
		{
			keyText.setText("");
			valueText.setText("");
			JButton1.setEnabled(true);
			JButton2.setEnabled(false);
			JButton3.setEnabled(false);
		}
		else
		{
			backUpKey = table.getValueAt(row,0).toString();
			backUpValue = table.getValueAt(row,1).toString();
			keyText.setText(table.getValueAt(row,0).toString());
			valueText.setText(table.getValueAt(row,1).toString());
			JButton1.setEnabled(false);
			JButton2.setEnabled(true);
			JButton3.setEnabled(true);
		}	
		/*if(fault)
		{
			fault = false;
			table.setValueAt(faultBackUpKey,faultRow,0);
			table.setValueAt(faultBackUpValue,faultRow,1);
			table.getSelectionModel().clearSelection();
			keyText.setText("");
			valueText.setText("");
		} */
	
	}
	
	public void escapeKeyPressed()
	{
			if(presumption)
			{
				tableModel.removeRow(table.getRowCount()-1);
				table.getSelectionModel().clearSelection();
				keyText.setText("");
				valueText.setText("");
				presumption = false;
				keyText.requestFocus();
				return;
			}	
			else
			{
				int row = table.getSelectedRow();
				table.setValueAt(backUpKey,row,0);
				table.setValueAt(backUpValue,row,1);
				table.getSelectionModel().clearSelection();
				keyText.setText("");
				valueText.setText("");
				keyText.requestFocus();
				return;
			}
	}

	public void keyReleasedEventForKeyText(KeyEvent ev)
	{
		int i = table.getSelectedRow();
		if(i != -1 )
		{
			String str = keyText.getText().trim();
			table.setValueAt(str,i,0);
		}

	}
	
	public void keyPressedEventForKeyText(KeyEvent ev)
	{
		if(keyText.getText().trim().equals("") && table.getSelectedRow() == -1 && ev.getKeyCode() != KeyEvent.VK_SPACE)
		{
			Vector v = new Vector();
			v.add("");
			v.add(valueText.getText().trim());
			tableModel.addRow(v);
			presumption = true;
			table.getSelectionModel().addSelectionInterval(table.getRowCount()-1,table.getRowCount()-1);
		}
		if(ev.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			escapeKeyPressed();
		}
		if(ev.getKeyCode() == KeyEvent.VK_DELETE || ev.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
			if(keyText.getText().equals(""))
			{
				if(!presumption)
				{
					fault = true;
					int i = table.getSelectedRow();
					faultBackUpKey = new String(backUpKey);
					faultBackUpValue = new String(backUpValue);
					faultRow = i;
				}
				else
				{
					tableModel.removeRow(table.getRowCount()-1);
					table.getSelectionModel().clearSelection();
				}
			}
		}
	}
	
	public void keyReleasedEventForValueText(KeyEvent ev)
	{
		if(ev.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			escapeKeyPressed();
			return;
		}
		int i = table.getSelectedRow();
		if(i != -1)
		{
			String str = valueText.getText();
			table.setValueAt(str,i,1);
		}

	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}













