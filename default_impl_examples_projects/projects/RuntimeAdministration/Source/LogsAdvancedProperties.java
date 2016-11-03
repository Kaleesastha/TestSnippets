

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;




//$Id: LogsAdvancedProperties.java,v 1.2 2007/02/22 15:03:07 srajeswari Exp $



import com.adventnet.nms.util.CommonBuilderUIInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LogsAdvancedProperties extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField keyName = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField displayName = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JTextField logLevel = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JCheckBox loggingCheck = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton modifyButton = null;
	javax.swing.JButton deleteButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	LogsAddModifyDialog detailsDialog = null;
	CommonBuilderUIInterface uiUtils = null;  
  
  

























   


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
            JLabel1.setText(resourceBundle.getString("Advanced Properties"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

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
            JLabel2.setText(resourceBundle.getString("Key Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel3.setText(resourceBundle.getString("Display Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel4.setText(resourceBundle.getString("Log Level"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JLabel5.setText(resourceBundle.getString("Enable Logging ?"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JPanel6.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            addButton.setText(resourceBundle.getString("Add"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addButton,ex); 
          }

//<UserCode_Begin_Bean_addButton>

//<UserCode_End_Bean_addButton>

          try
          {
            modifyButton.setText(resourceBundle.getString("Modify"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+modifyButton,ex); 
          }

//<UserCode_Begin_Bean_modifyButton>

//<UserCode_End_Bean_modifyButton>

          try
          {
            deleteButton.setText(resourceBundle.getString("Delete"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+deleteButton,ex); 
          }

//<UserCode_Begin_Bean_deleteButton>

//<UserCode_End_Bean_deleteButton>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Key Name","Display Name","Log Level","Enable Logging"});
//<UserCode_End_Bean_tableModel>
		deleteButton.setPreferredSize(new Dimension(deleteButton.getPreferredSize().width+0,deleteButton.getPreferredSize().height+4));
		modifyButton.setPreferredSize(new Dimension(modifyButton.getPreferredSize().width+0,modifyButton.getPreferredSize().height+4));
		addButton.setPreferredSize(new Dimension(addButton.getPreferredSize().width+10,addButton.getPreferredSize().height+4));
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+0,cancelButton.getPreferredSize().height+4));
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+18,okButton.getPreferredSize().height+4));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+4));

  
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
        this.setSize(getPreferredSize().width+517,getPreferredSize().height+499); 
          setTitle(resourceBundle.getString("LogsAdvancedProperties"));
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
	uiUtils.centerWindow(this);
	table.setDefaultRenderer(Object.class,new CustomRenderer());
	table.setDefaultEditor(Object.class,null);
	table.getTableHeader().setReorderingAllowed(false);
	addButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	modifyButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	deleteButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	okButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	cancelButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
        setTitle(resourceBundle.getString("Advanced Properties For Logging"));  
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      table_JPanel2_conn1 table_JPanel2_conn11 =  new table_JPanel2_conn1();
      table.addMouseListener(table_JPanel2_conn11);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      addButton_addButton_conn1 addButton_addButton_conn11 =  new addButton_addButton_conn1();
      addButton.addActionListener(addButton_addButton_conn11);
      okButton_okButton_conn1 okButton_okButton_conn11 =  new okButton_okButton_conn1();
      okButton.addActionListener(okButton_okButton_conn11);
      modifyButton_modifyButton_conn1 modifyButton_modifyButton_conn11 =  new modifyButton_modifyButton_conn1();
      modifyButton.addActionListener(modifyButton_modifyButton_conn11);
      cancelButton_cancelButton_conn1 cancelButton_cancelButton_conn11 =  new cancelButton_cancelButton_conn1();
      cancelButton.addActionListener(cancelButton_cancelButton_conn11);
      deleteButton_deleteButton_conn1 deleteButton_deleteButton_conn11 =  new deleteButton_deleteButton_conn1();
      deleteButton.addActionListener(deleteButton_deleteButton_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel4= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        JPanel8= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel3= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        keyName= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        displayName= new javax.swing.JTextField();
        JLabel4= new javax.swing.JLabel();
        logLevel= new javax.swing.JTextField();
        JLabel5= new javax.swing.JLabel();
        loggingCheck= new javax.swing.JCheckBox();
        JPanel6= new javax.swing.JPanel();
        addButton= new javax.swing.JButton();
        modifyButton= new javax.swing.JButton();
        deleteButton= new javax.swing.JButton();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JLabel1);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(1,5,5));
JPanel4.add(okButton);
JPanel4.add(cancelButton);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel8,cons);
JPanel8.setLayout(new GridBagLayout());
inset = new Insets(0,5,10,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel8.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel5.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(10,5,0,5);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel8.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(keyName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(displayName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(logLevel,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JLabel5,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(loggingCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,2,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JPanel6,cons);
JPanel6.setLayout(new FlowLayout(2,5,5));
JPanel6.add(addButton);
JPanel6.add(modifyButton);
JPanel6.add(deleteButton);

  
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



   


  
	public LogsAdvancedProperties(LogsAddModifyDialog dialog)
	{
		uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
		detailsDialog = dialog;
	}
  


   


  public LogsAdvancedProperties()
  {
	
    //<Begin_LogsAdvancedProperties>
    pack();
  
    //<End_LogsAdvancedProperties>
  }

  public LogsAdvancedProperties(java.applet.Applet applet)
  {
    //<Begin_LogsAdvancedProperties_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_LogsAdvancedProperties_java.applet.Applet>
  }

	public void populateTheUI(Hashtable mainHash,boolean modify)
	{
		if(!modify)
			JPanel3.setVisible(false);
		else
			JPanel3.setVisible(true);
		Enumeration enumerate = mainHash.keys();
		while(enumerate.hasMoreElements())
		{
			Hashtable hash = (Hashtable)mainHash.get(enumerate.nextElement());
			Vector vect = new Vector();
			vect.add(hash.get("Name"));
			vect.add(hash.get("DisplayName"));
			vect.add(hash.get("LogLevel"));
			vect.add(hash.get("Logging"));
			tableModel.addRow(vect);
		}
	}

 
//<Begin__class_table_JPanel2_conn1>

 class table_JPanel2_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_JPanel2_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
  tableMouseClicked();
     }
//<UserCode_End_Connection_table_JPanel2_conn1>
 }//<End__class_table_JPanel2_conn1>
	
	public void tableMouseClicked()
	{
		int row = table.getSelectedRow();
		if(row != -1)
		{
			keyName.setText(table.getValueAt(row,0).toString());
			displayName.setText(table.getValueAt(row,1).toString());
			logLevel.setText(table.getValueAt(row,2).toString());
			if(table.getValueAt(row,3).toString().equals("true"))	
				loggingCheck.setSelected(true);
			else
				loggingCheck.setSelected(false);
		}
	}


//<Begin__class_addButton_addButton_conn1>

 class addButton_addButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_addButton_addButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  addButtonActionPerformed();
     }
//<UserCode_End_Connection_addButton_addButton_conn1>
 }//<End__class_addButton_addButton_conn1>
	
	public void addButtonActionPerformed()
	{
		boolean shouldAddFlag = true;
		String keyNodeName = keyName.getText().trim();
		if(keyNodeName.equals(""))
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please specify a key name"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		else
		{	
			for(int i=0;i<table.getRowCount();i++)
			{
				String temp = table.getValueAt(i,0).toString();
				if(temp.equalsIgnoreCase(keyNodeName))
				{
					shouldAddFlag = false;
					break;
				}
			}
			if(shouldAddFlag)
			{
				Vector vect = new Vector();
				vect.add(keyNodeName);
				vect.add(displayName.getText());
				vect.add(logLevel.getText());
				if(loggingCheck.isSelected())
				{
					vect.add("true");
				}
				else
				{
					vect.add("false");
				}
				tableModel.addRow(vect);
			}
		}
	}


//<Begin__class_modifyButton_modifyButton_conn1>

 class modifyButton_modifyButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_modifyButton_modifyButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  modifyButtonActionPerformed();
     }
//<UserCode_End_Connection_modifyButton_modifyButton_conn1>
 }//<End__class_modifyButton_modifyButton_conn1>
	
	public void modifyButtonActionPerformed()
	{
		int row = table.getSelectedRow();
		if(row != -1)
		{
			boolean toModifyDisplay = true;
			boolean toModifyLevel = true;
			boolean toModifyState = true;
			String name = keyName.getText();
			if(name.equals(table.getValueAt(row,0).toString()))
			{
				if(displayName.getText().equals(table.getValueAt(row,1).toString()))
					toModifyDisplay = false;
				if(logLevel.getText().equals(table.getValueAt(row,2).toString()))
					toModifyLevel = false;
				String s = table.getValueAt(row,3).toString();
				String state = "";
				if(loggingCheck.isSelected())
				{
					state = "true";
				}
				else
				{
					state = "false";
				}
				if(s.equals(state))
					toModifyState = false;
				if(toModifyDisplay || toModifyState || toModifyLevel)
				{
					Vector v = new Vector();
					v.add(keyName.getText());
					v.add(displayName.getText());
					v.add(logLevel.getText());
					if(loggingCheck.isSelected())
						v.add("true");
					else
						v.add("false");
					tableModel.insertRow(row,v);
					tableModel.removeRow(row+1);
				}
			}
		}
	}	


//<Begin__class_okButton_okButton_conn1>

 class okButton_okButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_okButton_okButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  okButtonActionPerformed();
     }
//<UserCode_End_Connection_okButton_okButton_conn1>
 }//<End__class_okButton_okButton_conn1>
	
	public void okButtonActionPerformed()
	{
		Hashtable nodeHash = new Hashtable();
		if(table.getRowCount() != 0)
		{
			for(int i=0;i<table.getRowCount();i++)
			{
				String keyName = table.getValueAt(i,0).toString();
				Hashtable child = new Hashtable();
				child.put("Name",table.getValueAt(i,0).toString());
				child.put("DisplayName",table.getValueAt(i,1).toString());
				child.put("LogLevel",table.getValueAt(i,2).toString());
				child.put("Logging",table.getValueAt(i,3).toString());
				nodeHash.put(keyName,child);
			}
		}
		detailsDialog.updateKeyNodeVector(nodeHash);
		this.dispose();
		this.setVisible(false);
	}


//<Begin__class_cancelButton_cancelButton_conn1>

 class cancelButton_cancelButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_cancelButton_cancelButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  LogsAdvancedProperties.this.dispose();
  LogsAdvancedProperties.this.setVisible(false);
     }
//<UserCode_End_Connection_cancelButton_cancelButton_conn1>
 }//<End__class_cancelButton_cancelButton_conn1>
//<Begin__class_deleteButton_deleteButton_conn1>

 class deleteButton_deleteButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_deleteButton_deleteButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  deleteButtonActionPerformed();
     }
//<UserCode_End_Connection_deleteButton_deleteButton_conn1>
 }//<End__class_deleteButton_deleteButton_conn1>
	
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
				tableModel.removeRow(selectedRows[j]);
			} 
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
		if(table.getSelectedRow() == -1)
		{
			addButton.setEnabled(true);
			modifyButton.setEnabled(false);
			deleteButton.setEnabled(false);
		}
		else
		{
			addButton.setEnabled(false);
			modifyButton.setEnabled(true);
			deleteButton.setEnabled(true);
		}
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}






