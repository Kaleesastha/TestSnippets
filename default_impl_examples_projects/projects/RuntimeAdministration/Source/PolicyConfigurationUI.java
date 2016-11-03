
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;




//$Id: PolicyConfigurationUI.java,v 1.2 2007/02/22 15:03:07 srajeswari Exp $



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

public class PolicyConfigurationUI extends JPanel implements ActionListener,ApplyToServerInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel9 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton modifyButton = null;
	javax.swing.JButton deleteButton = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JButton applyButton = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JButton policyColorButton = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JTextArea JTextArea2 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	PolicyConfigurationModel policyModel = null;
  	PolicyColorChooser policyColorChooser = null;
  	CustomRenderer customRenderer = null;
	int startRow = 0;  
	boolean shiftFlag = false;
	boolean controlFlag = false;
	boolean isModified = false;
	boolean refetchFlag = false;

















	public void actionPerformed(ActionEvent ae)
	{}




   


  public PolicyConfigurationUI()
  {
    //<Begin_PolicyConfigurationUI>
    this.init();
  
    //<End_PolicyConfigurationUI>
  	initializeVariables();
  }

  private void initializeVariables()
  {

	policyModel = new PolicyConfigurationModel(this);
	RuntimeConfigFrame.setStatusText("Loading Policy configuration data.....");
	policyModel.getFilterNodes();
	RuntimeConfigFrame.setBusyCursor(this);	
  }

  public PolicyConfigurationUI(java.applet.Applet applet)
  {
    //<Begin_PolicyConfigurationUI_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_PolicyConfigurationUI_java.applet.Applet>

  	initializeVariables();

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
            JPanel9.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel9,ex); 
          }

//<UserCode_Begin_Bean_JPanel9>

//<UserCode_End_Bean_JPanel9>

          try
          {
            JLabel1.setText(resourceBundle.getString("Policy Configuration"));
            JLabel1.setHorizontalAlignment(0);
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
            JLabel6.setText(resourceBundle.getString("( policy.conf )"));
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
            JPanel6.setBorder(new javax.swing.border.BevelBorder(1));
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

          try
          {
            JPanel8.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel8,ex); 
          }

//<UserCode_Begin_Bean_JPanel8>

//<UserCode_End_Bean_JPanel8>

          try
          {
            applyButton.setText(resourceBundle.getString("Apply"));
            applyButton.setPreferredSize(new Dimension(63,27));
            applyButton.setMinimumSize(new Dimension(63,27));
            applyButton.setMaximumSize(new Dimension(63,27));
            applyButton.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+applyButton,ex); 
          }

//<UserCode_Begin_Bean_applyButton>

//<UserCode_End_Bean_applyButton>

          try
          {
            JPanel7.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel7,ex); 
          }

//<UserCode_Begin_Bean_JPanel7>

//<UserCode_End_Bean_JPanel7>

          try
          {
            policyColorButton.setText(resourceBundle.getString("Policy Color"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+policyColorButton,ex); 
          }

//<UserCode_Begin_Bean_policyColorButton>

//<UserCode_End_Bean_policyColorButton>

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
            JTextArea1.setText(resourceBundle.getString(""));
            JTextArea1.setBackground(new Color(-1));
            JTextArea1.setOpaque(false);
            JTextArea1.setLineWrap(true);
            JTextArea1.setWrapStyleWord(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
          }

//<UserCode_Begin_Bean_JTextArea1>

//<UserCode_End_Bean_JTextArea1>

          try
          {
            JTextArea2.setBackground(new Color(-6684673));
            JTextArea2.setText(resourceBundle.getString(""));
            JTextArea2.setOpaque(false);
            JTextArea2.setLineWrap(true);
            JTextArea2.setWrapStyleWord(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea2,ex); 
          }

//<UserCode_Begin_Bean_JTextArea2>

//<UserCode_End_Bean_JTextArea2>

          try
          {
            JLabel2.setBackground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel3.setBackground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

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
            table.setModel(tableModel);
            table.setRowHeight(22);
            table.setToolTipText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Class Name","Display Name"});
//<UserCode_End_Bean_tableModel>
		JLabel4.setPreferredSize(new Dimension(JLabel4.getPreferredSize().width+159,JLabel4.getPreferredSize().height+227));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+2,JPanel2.getPreferredSize().height+2));
		policyColorButton.setPreferredSize(new Dimension(policyColorButton.getPreferredSize().width+0,policyColorButton.getPreferredSize().height+4));
		JPanel7.setPreferredSize(new Dimension(JPanel7.getPreferredSize().width+32,JPanel7.getPreferredSize().height+0));
		applyButton.setPreferredSize(new Dimension(applyButton.getPreferredSize().width+10,applyButton.getPreferredSize().height+0));
		JPanel8.setPreferredSize(new Dimension(JPanel8.getPreferredSize().width+1,JPanel8.getPreferredSize().height+0));
		deleteButton.setPreferredSize(new Dimension(deleteButton.getPreferredSize().width+0,deleteButton.getPreferredSize().height+4));
		modifyButton.setPreferredSize(new Dimension(modifyButton.getPreferredSize().width+0,modifyButton.getPreferredSize().height+4));
		addButton.setPreferredSize(new Dimension(addButton.getPreferredSize().width+10,addButton.getPreferredSize().height+4));
		JPanel6.setPreferredSize(new Dimension(JPanel6.getPreferredSize().width+95,JPanel6.getPreferredSize().height+0));
		JLabel6.setPreferredSize(new Dimension(JLabel6.getPreferredSize().width+0,JLabel6.getPreferredSize().height+4));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+4));

  
          //<End_setUpProperties>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("Class Name"),resourceBundle.getString("Display Name")});
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
        setPreferredSize(new Dimension(getPreferredSize().width+728,getPreferredSize().height+517)); 
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
	customRenderer = new CustomRenderer();
	table.setDefaultRenderer(Object.class,customRenderer);
	table.setDefaultEditor(Object.class,null);
	JLabel4.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("filters.png","images/runtimeadmin"));
	JLabel2.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));
	JLabel3.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));
	JTextArea1.setText(resourceBundle.getString("The different types of Policies that are to be executed by the WebNMS can be configured below."));
	JTextArea2.setText(resourceBundle.getString("Deleting a Policy will delete all the Policy instances created for this type."));
	JTextArea1.setEditable(false);
	JTextArea2.setEditable(false);
	table.getTableHeader().setReorderingAllowed(false);
	addButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JLabel6.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	modifyButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	deleteButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	policyColorButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	applyButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	applyButton.setEnabled(false);
			modifyButton.setEnabled(false);
			deleteButton.setEnabled(false);
JLabel5.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("policyfilters.png", "images/runtimeadmin"));
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      table_table_conn3 table_table_conn31 =  new table_table_conn3();
      table.addKeyListener(table_table_conn31);
      table_table_conn2 table_table_conn21 =  new table_table_conn2();
      table.addMouseMotionListener(table_table_conn21);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      addButton_addButton_conn1 addButton_addButton_conn11 =  new addButton_addButton_conn1();
      addButton.addActionListener(addButton_addButton_conn11);
      classPathButton_classPathButton_conn1 classPathButton_classPathButton_conn11 =  new classPathButton_classPathButton_conn1();
      applyButton.addActionListener(classPathButton_classPathButton_conn11);
      modifyButton_modifyButton_conn1 modifyButton_modifyButton_conn11 =  new modifyButton_modifyButton_conn1();
      modifyButton.addActionListener(modifyButton_modifyButton_conn11);
      policyColorButton_JPanel8_conn1 policyColorButton_JPanel8_conn11 =  new policyColorButton_JPanel8_conn1();
      policyColorButton.addActionListener(policyColorButton_JPanel8_conn11);
      deleteButton_deleteButton_conn1 deleteButton_deleteButton_conn11 =  new deleteButton_deleteButton_conn1();
      deleteButton.addActionListener(deleteButton_deleteButton_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel9= new javax.swing.JPanel();
        JLabel5= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JLabel6= new javax.swing.JLabel();
        JPanel4= new javax.swing.JPanel();
        JPanel6= new javax.swing.JPanel();
        addButton= new javax.swing.JButton();
        modifyButton= new javax.swing.JButton();
        deleteButton= new javax.swing.JButton();
        JPanel8= new javax.swing.JPanel();
        applyButton= new javax.swing.JButton();
        JPanel7= new javax.swing.JPanel();
        policyColorButton= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JTextArea1= new javax.swing.JTextArea();
        JTextArea2= new javax.swing.JTextArea();
        JLabel2= new javax.swing.JLabel();
        JLabel3= new javax.swing.JLabel();
        JPanel5= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JLabel4= new javax.swing.JLabel();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JPanel9,BorderLayout.CENTER);
JPanel9.setLayout(new FlowLayout(1,10,5));
JPanel9.add(JLabel5);
JPanel9.add(JLabel1);
JPanel9.add(JLabel6);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(JPanel6,BorderLayout.WEST);
JPanel6.setLayout(new FlowLayout(1,5,5));
JPanel6.add(addButton);
JPanel6.add(modifyButton);
JPanel6.add(deleteButton);
JPanel4.add(JPanel8,BorderLayout.EAST);
JPanel8.setLayout(new FlowLayout(2,5,5));
JPanel8.add(applyButton);
JPanel4.add(JPanel7,BorderLayout.CENTER);
JPanel7.setLayout(new FlowLayout(1,5,5));
JPanel7.add(policyColorButton);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JTextArea1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JTextArea2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel5,cons);
JPanel5.setLayout(new BorderLayout(5,5));
JPanel5.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.NORTHWEST,cons.BOTH,inset,0,0);
JPanel2.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
JPanel5.add(JLabel4,BorderLayout.WEST);

  
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

	Hashtable colorHash = new Hashtable();
	Hashtable policyHash = new Hashtable();

	public void updateThePolicyUI(Hashtable mainHash)
	{
		if(! refetchFlag)
		{
			RuntimeConfigFrame.setStatusText("Done");
			RuntimeConfigFrame.setDefaultCursor(this);
		}
		colorHash =(Hashtable) mainHash.remove("POLICY_COLOR");
		policyHash = mainHash;
		Enumeration enumerate = mainHash.keys();
		while(enumerate.hasMoreElements())
		{
			String cName = (String) enumerate.nextElement();
			Hashtable temp =(Hashtable) mainHash.get(cName);
			String dName = (String)temp.get("display_name");
			setAsLastRow(cName,dName);
		}
	}

	public void setAsLastRow(String cname,String dname)
	{
		tableModel.addRow(new Object[]  { "" ,""}  );
		table.setValueAt(cname,(table.getRowCount()-1),0);
		table.setValueAt(dname,(table.getRowCount()-1),1);
	}

	public void updateTheUI()
	{
		RuntimeConfigFrame.setDefaultCursor(this);
		RuntimeConfigFrame.applied();
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
		AddModifyClassName addClassName= new AddModifyClassName(this,applet);
		addClassName.init();
		addClassName.populateUI("","",false);
		addClassName.setVisible(true);
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
			String className = table.getValueAt(row,0).toString();
			String displayName = table.getValueAt(row,1).toString();
			AddModifyClassName modifyClassName= new AddModifyClassName(this,applet);
			modifyClassName.init();
			modifyClassName.populateUI(className,displayName,true);
			modifyClassName.setVisible(true);
		}
	}


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
			isModified = true;
			for(int j=length-1;j >=0;j--)
			{
				tableModel.removeRow(selectedRows[j]);
			} 
			applyButton.setEnabled(true);
  	   	}
	}

 

	
	public void moveUpButtonActionPerformed()
	{
		int index = table.getSelectedRow();
  		if (index !=-1)
		{ 
			if (index > 0)
			{ 
			    ListSelectionModel listModel = table.getSelectionModel();
			    int i = listModel.getMaxSelectionIndex();
			    listModel.clearSelection();
			    table.setSelectionModel(listModel); 
			    Vector temp = new Vector();
    			temp.add(table.getValueAt(index,0));
			    temp.add(table.getValueAt(index,1)); 
			    tableModel.insertRow(index-1,temp);
			    tableModel.removeRow(index+1);
			    listModel.addSelectionInterval(index-1,index-1);   
		   	}
			else {} 
		}   
	}

 

	
	public void moveDownButtonActionPerformed()
	{
		int index = table.getSelectedRow(); 
  		if (index != -1)
		{ 
			if(index < table.getRowCount()-1)
			{ 
				ListSelectionModel listModel = table.getSelectionModel();
			    int i = listModel.getMaxSelectionIndex();
		        listModel.clearSelection();
			    table.setSelectionModel(listModel); 
			    Vector temp = new Vector();
			    temp.add(table.getValueAt(index,0));
			    temp.add(table.getValueAt(index,1)); 
	    	    tableModel.insertRow(index+2,temp);
			    tableModel.removeRow(index);
			    listModel.addSelectionInterval(index+1,index+1);   
			}
			else
			{ 
			}
		}   
	}



	
	

//<Begin__class_policyColorButton_JPanel8_conn1>

 class policyColorButton_JPanel8_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_policyColorButton_JPanel8_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  policyColorButtonActionPerformed();
     }
//<UserCode_End_Connection_policyColorButton_JPanel8_conn1>
 }//<End__class_policyColorButton_JPanel8_conn1>
	
	public void policyColorButtonActionPerformed()
	{
		policyColorChooser = new PolicyColorChooser(PolicyConfigurationUI.this , applet);
		policyColorChooser.init();
		policyColorChooser.setButtonBackground(colorHash);  
		policyColorChooser.setVisible(true);  
	}

	public void newPolicyColor(Hashtable ht) 
	{
		for (int i=0;i<ht.size();i++)
		{
			colorHash.put("DISABLED",ht.get("DISABLED").toString());
			colorHash.put("STOPPED",ht.get("STOPPED").toString());
			colorHash.put("EXECUTED",ht.get("EXECUTED").toString());
			colorHash.put("SCHEDULED",ht.get("SCHEDULED").toString());
		}
		isModified = true;
		applyButton.setEnabled(true);
	}

	public void updateEntries(String cname,String dname,boolean modifyFlag)
	{
		isModified = true;
		int row = table.getSelectedRow();
		boolean classNameValidity = true;
		boolean displayNameValidity = true;
		for(int i=0;i<table.getRowCount();i++)
		{
			String temp = table.getValueAt(i,0).toString();
			if(cname.equals(temp))
			{
				classNameValidity = false;
			}
			String temp1 = table.getValueAt(i,1).toString();
			if(dname.equals(temp1))
			{
				displayNameValidity = false;
			}
		}
		if(modifyFlag)
		{
			if(classNameValidity || displayNameValidity)
			{
				Vector v =new Vector();
				v.add(cname);
				v.add(dname);
				tableModel.insertRow(row,v);
				tableModel.removeRow(row+1);
				table.getSelectionModel().clearSelection();
				applyButton.setEnabled(true);
				//table.getSelectionModel().addSelectionInterval(row,row);
			}
		}
		if(!modifyFlag && classNameValidity)
		{
			Vector v =new Vector();
			v.add(cname);
			v.add(dname);
			tableModel.addRow(v);
			table.getSelectionModel().clearSelection();
			applyButton.setEnabled(true);
			//table.getSelectionModel().addSelectionInterval(table.getRowCount()-1,table.getRowCount()-1);
		}
	}

	public void classpathButtonActionPerformed()
	{
		ClassPathDialog classPathDialog = new ClassPathDialog(applet);
		classPathDialog.init();
classPathDialog.populateUI(RuntimeConfigFrame.getClassPath(),RuntimeConfigFrame.getClassPathDelimiter());
		classPathDialog.setVisible(true);
	}


//<Begin__class_classPathButton_classPathButton_conn1>

 class classPathButton_classPathButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_classPathButton_classPathButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  applyToServer();
     }
//<UserCode_End_Connection_classPathButton_classPathButton_conn1>
 }//<End__class_classPathButton_classPathButton_conn1>

 
//<Begin__class_table_table_conn2>

 class table_table_conn2 extends java.awt.event.MouseMotionAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_table_conn2>



     //Listener Interface Methods Are Added Below 


     public void mouseDragged( java.awt.event.MouseEvent arg0)
     {
  tableMouseDraggedEvent(arg0);
     }
//<UserCode_End_Connection_table_table_conn2>
 }//<End__class_table_table_conn2>
//<Begin__class_table_table_conn1>

 class table_table_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_table_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  tableMousePressedEvent(arg0);
     }



     public void mouseReleased( java.awt.event.MouseEvent arg0)
     {
  tableMouseReleasedEvent(arg0);
     }
 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
  tableMouseClickedEvent();
     }
  
//<UserCode_End_Connection_table_table_conn1>
 }//<End__class_table_table_conn1>
	
	
	
 
//<Begin__class_table_table_conn3>

 class table_table_conn3 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_table_conn3>



     //Listener Interface Methods Are Added Below 


     public void keyPressed( java.awt.event.KeyEvent arg0)
     {
  tableKeyPressedEvent(arg0);
     }



     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
  tableKeyReleasedEvent(arg0);
     }
 
//<UserCode_End_Connection_table_table_conn3>
 }//<End__class_table_table_conn3>
	
	public void tableMouseReleasedEvent(MouseEvent arg0)
	{
		if(shiftFlag || controlFlag)
		{}
		else  if (shiftFlag && controlFlag)
		{}
		else
		{
			RuntimeConfigFrame.gip.mouseReleased(SwingUtilities.convertMouseEvent(arg0.getComponent(),arg0,RuntimeConfigFrame.gip)); 
		}
		int index = ((JTable)arg0.getSource()).rowAtPoint(arg0.getPoint());
		if(index == -1)
		{
			modifyButton.setEnabled(false);
			deleteButton.setEnabled(false);
		}
		else
		{
				modifyButton.setEnabled(true);
				deleteButton.setEnabled(true);
		}
		table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		if(index != -1 && startRow != -1)
		{
			if(startRow != index)
			{
				isModified = true;
				applyButton.setEnabled(true);
				//reOrder = true;
			}
			if(startRow >= index)
			{
				Vector temp = new Vector();
				temp.add(table.getValueAt(startRow,0));  
				temp.add(table.getValueAt(startRow,1));
				tableModel.insertRow(index,temp);
				tableModel.removeRow(startRow+1);
			}
			else
			{
				Vector temp = new Vector();
				temp.add(table.getValueAt(startRow,0));  
				temp.add(table.getValueAt(startRow,1));
			  	tableModel.insertRow(index+1,temp);
				tableModel.removeRow(startRow);
			}
			int j = index;
			if(!shiftFlag && !controlFlag)
			table.getSelectionModel().addSelectionInterval(j,index);
		}
	}

	public void tableMouseDraggedEvent(MouseEvent arg0)
	{
		if(shiftFlag || controlFlag)
		{}
		else
		{	
			RuntimeConfigFrame.gip.mouseDragged(SwingUtilities.convertMouseEvent(arg0.getComponent(),arg0,RuntimeConfigFrame.gip)); 
		}		
		table.getSelectionModel().clearSelection(); 

	}

	public void tableMousePressedEvent(MouseEvent arg0)
	{
		if(table.getSelectedRow() == -1)
		{
			modifyButton.setEnabled(false);
			deleteButton.setEnabled(false);
		}
		else
		{
				modifyButton.setEnabled(true);
				deleteButton.setEnabled(true);
		}
		startRow = ((JTable)arg0.getSource()).rowAtPoint(arg0.getPoint());
		if(startRow == -1)
			return;
		String str1 = table.getValueAt(startRow,0).toString();
		String str2 = table.getValueAt(startRow,1).toString();
		String str = str1+"   |   "+str2;
		if(shiftFlag || controlFlag)
		{
		}
		else if (shiftFlag && controlFlag)
		{}
		else 
		{
			RuntimeConfigFrame.gip.showGlassPanel(this,(JFrame)SwingUtilities.windowForComponent(this),1,null,str,shiftFlag,controlFlag);
		}

	}

	public void tableKeyPressedEvent(KeyEvent arg0)
	{
		 if(arg0.getKeyCode() == KeyEvent.VK_SHIFT)
 		 {
		 	shiftFlag = true; 
		 } 
 		 if(arg0.getKeyCode() == KeyEvent.VK_CONTROL)
		 {
		 	controlFlag = true; 
		 }
	}

	public void tableKeyReleasedEvent(KeyEvent arg0)
	{
		if(arg0.getKeyCode() == KeyEvent.VK_SHIFT)
 		{
		 	shiftFlag = false; 
		} 
 		if(arg0.getKeyCode() == KeyEvent.VK_CONTROL)
		{
		 	controlFlag = false; 
		}
	}

      //Reason for Changes
    //Modified by Balan on 21/06/03 for SPP's Requirements ie when exception arises with applyToserver method
    //it should not allow the user to select some other node as well as not to allow RTA to be closed. 

    // Commented by Balan on 21/06/03 for SPP
    //public void applyToServer()
    // {
    // Comment Ends

    //Added by Balan on 21/06/03 for SPP
    public boolean applyToServer()
    {
        try
        {
            //Add Ends
            
            //Commented by Balan on 21/06/03 for SPP
            //public void applyToServer()
            //{
            //Comment Ends
	if(isModified())
		{
			RuntimeConfigFrame.setBusyCursor(this);
			RuntimeConfigFrame.applyStatus();
			//isModified = false;
			int index = table.getRowCount(); 
 			Hashtable [] setArray = new Hashtable[index];
			for(int i=0;i<index;i++)
			{
				  setArray[i] = new Hashtable(); 
				  String tempclass = (String)table.getValueAt(i,0); 
				  setArray[i].put("classname",tempclass); 
				  String tempdisp = (String)table.getValueAt(i,1); 
				  setArray[i].put("display_name",tempdisp);   
			}
   			Hashtable color = new Hashtable();
  	 		color.put("POLICY_COLOR",colorHash); 
			policyModel.setFilterNodes(setArray,color);
                        isModified = false;
			applyButton.setEnabled(false);
		}
        //Added by Balan on 21/06/03
        }
        catch(Exception exce)
        {
            RuntimeConfigFrame.setDefaultCursor(this);
            return false;
        }
        return true;
        //Add Ends
	}
	public boolean isModified()
	{
		return isModified;
	}

	public void tableMouseClickedEvent()
	{
		if(table.getSelectedRow() == -1)
		{
			modifyButton.setEnabled(false);
			deleteButton.setEnabled(false);
		}
		else
		{
				modifyButton.setEnabled(true);
				deleteButton.setEnabled(true);
		}
	}

	public void refetchData()
	{
		int i=tableModel.getRowCount();
		if(i != 0)
		{
			for(int j=i-1;j >=0;j--)
			{
				tableModel.removeRow(j);
			}
		}
		refetchFlag = true;
		isModified = false;
		policyModel.getFilterNodes();
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

    //Added by Balan on 08/07/03 for SPP for Memory Leak issue
    public void dispose()
    {
        policyModel.policyConfiguartionSession.dispose(); 
        policyModel = null;
  	policyColorChooser = null;
  	customRenderer = null;
           
    }
    //Add Ends
}









