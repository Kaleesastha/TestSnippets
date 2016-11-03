

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;




//$Id: DiscoveryFilterUI.java,v 1.1 2006/08/29 13:57:02 build Exp $



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

public class DiscoveryFilterUI extends JPanel implements ActionListener,ApplyToServerInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JButton applyButton = null;
	javax.swing.JButton reloadButton = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton modifyButton = null;
	javax.swing.JButton deleteButton = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JTextArea JTextArea2 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	String defaultText = null;
	DiscoveryFilterModel discoveryFilterModel = null;
	Vector classNameVector = null;
  	CustomRenderer customRenderer = null;	
	String selectedString = null;  
	int selectedRow = 0;  
	int startRow = 0;
	boolean shiftFlag = false;
	int forShift = 0;
	boolean controlFlag = false;
	boolean isModified = false;
	boolean reOrder = false;
	boolean refetchFlag = false;
	Vector reloadVector = new Vector();

    //added for RTA spp fix

    table_table_conn3 table_table_conn31 =  null;
    table_table_conn2 table_table_conn21 =  null;
    table_table_conn1 table_table_conn11 =  null;
    addButton_addButton_conn1 addButton_addButton_conn11 = null;
    classPathButton_classPathButton_conn1 classPathButton_classPathButton_conn11 = null;
    modifyButton_modifyButton_conn1 modifyButton_modifyButton_conn11 =  null;
    reloadButton_reloadButton_conn1 reloadButton_reloadButton_conn11 =  null;
    deleteButton_deleteButton_conn1 deleteButton_deleteButton_conn11 =  null;



	public void actionPerformed(ActionEvent ae)
	{}

   


  public DiscoveryFilterUI()
  {
    //<Begin_DiscoveryFilterUI>
    this.init();
  
    //<End_DiscoveryFilterUI>

  initializeVariables();
  }

  private void initializeVariables()
  {
	discoveryFilterModel = new DiscoveryFilterModel(this);
	discoveryFilterModel.getDiscoveryFilterNodes();
	JLabel2.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));
	JTextArea1.setText(resourceBundle.getString("The filters will be invoked in the same order as listed in the table. Drag and Drop to change the order of the filters."));
	JTextArea1.setEditable(false);
	JLabel3.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));
	JTextArea2.setText(resourceBundle.getString("Use reload option to load DiscoveryFilter classes again in the WebNMS server at runtime."));
	JTextArea2.setEditable(false);	
	RuntimeConfigFrame.setBusyCursor(this);
	RuntimeConfigFrame.setStatusText(resourceBundle.getString("Loading Discovery Filter data ..........."));
	RuntimeConfigFrame.setStatusColor(Color.red);
  }

  public DiscoveryFilterUI(java.applet.Applet applet)
  {
    //<Begin_DiscoveryFilterUI_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_DiscoveryFilterUI_java.applet.Applet>

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
            JLabel1.setText(resourceBundle.getString("Discovery Filter Configuration"));
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
            JLabel6.setForeground(new Color(-16764109));
            JLabel6.setText(resourceBundle.getString("( discovery.filters )"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

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
            table.setRowHeight(22);
            table.setToolTipText(resourceBundle.getString("Drag and Drop to change the order"));
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
            applyButton.setName(resourceBundle.getString("Move the selected Filter down by one row.This means that blablabla"));
            applyButton.setText(resourceBundle.getString("Apply"));
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
            reloadButton.setText(resourceBundle.getString("Reload"));
            reloadButton.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+reloadButton,ex); 
          }

//<UserCode_Begin_Bean_reloadButton>

//<UserCode_End_Bean_reloadButton>

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
            addButton.setName(resourceBundle.getString("Add"));
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
            modifyButton.setName(resourceBundle.getString("Modify"));
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
            deleteButton.setName(resourceBundle.getString("Delete"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+deleteButton,ex); 
          }

//<UserCode_Begin_Bean_deleteButton>

//<UserCode_End_Bean_deleteButton>

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
            JLabel2.setBackground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Class Name"});
//<UserCode_End_Bean_tableModel>
		deleteButton.setPreferredSize(new Dimension(deleteButton.getPreferredSize().width+0,deleteButton.getPreferredSize().height+4));
		modifyButton.setPreferredSize(new Dimension(modifyButton.getPreferredSize().width+0,modifyButton.getPreferredSize().height+4));
		addButton.setPreferredSize(new Dimension(addButton.getPreferredSize().width+10,addButton.getPreferredSize().height+4));
		reloadButton.setPreferredSize(new Dimension(reloadButton.getPreferredSize().width+0,reloadButton.getPreferredSize().height+4));
		applyButton.setPreferredSize(new Dimension(applyButton.getPreferredSize().width+0,applyButton.getPreferredSize().height+4));
		JLabel4.setPreferredSize(new Dimension(JLabel4.getPreferredSize().width+148,JLabel4.getPreferredSize().height+248));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+2,JPanel4.getPreferredSize().height+2));
		JLabel6.setPreferredSize(new Dimension(JLabel6.getPreferredSize().width+0,JLabel6.getPreferredSize().height+4));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+4));

  
          //<End_setUpProperties>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("Class Name")});
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
        setPreferredSize(new Dimension(getPreferredSize().width+725,getPreferredSize().height+528)); 
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
	table.setDefaultEditor(Object.class,null);
	customRenderer = new CustomRenderer();
	table.setDefaultRenderer(Object.class,customRenderer);
	JTextArea1.setEditable(false);
	JTextArea2.setEditable(false);
	JLabel4.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("filters.png","images/runtimeadmin"));
	JLabel5.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("discoveryfilters.png", "images/runtimeadmin"));
	table.getTableHeader().setReorderingAllowed(false);
	reloadButton.setEnabled(false);
	addButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	modifyButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	deleteButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	reloadButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	applyButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JLabel6.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	applyButton.setEnabled(false);
	modifyButton.setEnabled(false);
	deleteButton.setEnabled(false);
  } 
  public void setUpConnections()
  { 

   //added to define the components globally for removing the component's Listeners when dispose() called -  rme integration
      modSetUpConnections(); 
      if(true)
      {
       return;   
      }    
     //end 
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
      reloadButton_reloadButton_conn1 reloadButton_reloadButton_conn11 =  new reloadButton_reloadButton_conn1();
      reloadButton.addActionListener(reloadButton_reloadButton_conn11);
      deleteButton_deleteButton_conn1 deleteButton_deleteButton_conn11 =  new deleteButton_deleteButton_conn1();
      deleteButton.addActionListener(deleteButton_deleteButton_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel8= new javax.swing.JPanel();
        JLabel5= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JLabel6= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JLabel4= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        JPanel7= new javax.swing.JPanel();
        applyButton= new javax.swing.JButton();
        reloadButton= new javax.swing.JButton();
        JPanel6= new javax.swing.JPanel();
        addButton= new javax.swing.JButton();
        modifyButton= new javax.swing.JButton();
        deleteButton= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        JTextArea1= new javax.swing.JTextArea();
        JTextArea2= new javax.swing.JTextArea();
        JLabel3= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JPanel8,BorderLayout.CENTER);
JPanel8.setLayout(new FlowLayout(1,10,5));
JPanel8.add(JLabel5);
JPanel8.add(JLabel1);
JPanel8.add(JLabel6);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JPanel4,BorderLayout.CENTER);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel4.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
JPanel2.add(JLabel4,BorderLayout.WEST);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,10,0,0);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JPanel7,cons);
JPanel7.setLayout(new FlowLayout(1,5,5));
JPanel7.add(applyButton);
JPanel7.add(reloadButton);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JPanel6,cons);
JPanel6.setLayout(new FlowLayout(1,5,5));
JPanel6.add(addButton);
JPanel6.add(modifyButton);
JPanel6.add(deleteButton);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JTextArea1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JTextArea2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel2,cons);

  
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


 	public void updateTheUI() 
	{
		RuntimeConfigFrame.setDefaultCursor(this);
		RuntimeConfigFrame.applied();
	}

	public void updateTheUI(Vector classVector)
	{
		if(! refetchFlag)
		{
			RuntimeConfigFrame.setDefaultCursor(this);
			RuntimeConfigFrame.setStatusText(resourceBundle.getString("Done"));
		}
		classNameVector = classVector;
		for(int i=0;i < classNameVector.size();i++)
		{
			setAsLastRow(classVector.elementAt(i).toString());
		}
		//RuntimeConfigFrame.setStatusColor(new Color(6,91,35));
	}

	public void setAsLastRow(String type)
	{
		tableModel.addRow(new Object[]  {""}  );
		table.setValueAt(type,(table.getRowCount()-1),0);
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
		addClassName.populateUI("",false);
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
			AddModifyClassName modifyClassName= new AddModifyClassName(this,applet);
			modifyClassName.init();
			modifyClassName.populateUI(className,true);
			modifyClassName.setVisible(true);
		}
	}

	public void updateEntries(String name,boolean modifyFlag)
	{
		isModified = true;
		int row = table.getSelectedRow();
		boolean validity = true;
		for(int i=0;i<classNameVector.size();i++)
		{
			String temp = classNameVector.elementAt(i).toString().trim();
			if(name.equals(temp))
			{
				validity = false;
			}
		}
		if(validity)
		{
			reloadVector.add(name);
		}
		if(modifyFlag)
		{
			Vector v =new Vector();
			v.add(name);
			reloadButton.setEnabled(false);
			tableModel.insertRow(row,v);
			tableModel.removeRow(row+1);
			table.getSelectionModel().clearSelection();	
			//reloadVector.add(name);
			//table.getSelectionModel().addSelectionInterval(row,row);
			applyButton.setEnabled(true);
			modifyButton.setEnabled(false);
			deleteButton.setEnabled(false);
			reloadButton.setEnabled(false);
		}
		if(!modifyFlag)
		{ 
			Vector v =new Vector();
			v.add(name);
			tableModel.addRow(v);
			reloadButton.setEnabled(false);
			table.getSelectionModel().clearSelection();
			//reloadVector.add(name);
			//table.getSelectionModel().addSelectionInterval(table.getRowCount()-1,table.getRowCount()-1);
			applyButton.setEnabled(true);
			reloadButton.setEnabled(false);
			modifyButton.setEnabled(false);
			deleteButton.setEnabled(false);
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
		int option =  jp.showConfirmDialog(null, resourceBundle.getString("Do you really want to delete the selected item(s)"),resourceBundle.getString("Confirmation"),jp.YES_NO_OPTION);
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
	
	public void classpathButtonActionPerformed()
	{
		ClassPathDialog classPathDialog = new ClassPathDialog(applet);
		classPathDialog.init();
		classPathDialog.populateUI(RuntimeConfigFrame.getClassPath(),RuntimeConfigFrame.getClassPathDelimiter());
		classPathDialog.setVisible(true);
	}

 
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
		if(index == -1 && index != startRow)
		{
			modifyButton.setEnabled(false);
			deleteButton.setEnabled(false);
			reloadButton.setEnabled(false);
		}
		else
		{
			modifyButton.setEnabled(true);
			deleteButton.setEnabled(true);
		}
		table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		if(index != -1 && startRow != -1)
		{
			if(index != startRow)
			{
				isModified = true;
				applyButton.setEnabled(true);
				reOrder = true;
			}
			if(startRow >= index)
			{
				Vector temp = new Vector();
				temp.add(table.getValueAt(startRow,0));  
			  	tableModel.insertRow(index,temp);
				tableModel.removeRow(startRow+1);
			}
			else
			{
				Vector temp = new Vector();
				temp.add(table.getValueAt(startRow,0));  
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
		startRow = ((JTable)arg0.getSource()).rowAtPoint(arg0.getPoint());
		if(table.getSelectedRow() == -1)
		{
			modifyButton.setEnabled(false);
			deleteButton.setEnabled(false);
			reloadButton.setEnabled(false);
		}
		else
		{
				modifyButton.setEnabled(true);
				deleteButton.setEnabled(true);
		}
		setReloadState(startRow);
		if(startRow == -1)
			return;
		String str = table.getValueAt(startRow,0).toString();
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

	public boolean  applyToServer()
	{
       try
       {
		if(isModified())
		{
			reloadButton.setEnabled(true);
	//		isModified = false;
			int index = table.getRowCount(); 
			Vector setVector = new Vector();
			for(int i=0;i<index;i++)
			{
				setVector.add(table.getValueAt(i,0).toString());
	   		}
			RuntimeConfigFrame.setBusyCursor(this);			
			RuntimeConfigFrame.applyStatus();
			discoveryFilterModel.setDiscoveryFilterNodes(setVector,reOrder); 
			reOrder = false;
			isModified = false;
			applyButton.setEnabled(false);
			reloadVector.removeAllElements();
            
		}

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


//<Begin__class_reloadButton_reloadButton_conn1>

 class reloadButton_reloadButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_reloadButton_reloadButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  reloadButtonActionPerformed();
     }
//<UserCode_End_Connection_reloadButton_reloadButton_conn1>
 }//<End__class_reloadButton_reloadButton_conn1>
	
	public void reloadButtonActionPerformed()
	{
		int [] selectedRows = table.getSelectedRows();
		int count = selectedRows.length;
		if(count == 0)
		{
			return;
		}
		String [] reloadArray = new String[count];
		for(int i=0;i<count;i++)
		{ 
				reloadArray[i] = table.getValueAt(selectedRows[i],0).toString();
		} 
		discoveryFilterModel.reloadDiscoveryFilterNode(reloadArray); 
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
		applyButton.setEnabled(false);
		modifyButton.setEnabled(false);
		deleteButton.setEnabled(false);
		reloadButton.setEnabled(false);
		discoveryFilterModel.getDiscoveryFilterNodes();		
	}

	public void setReloadState(int row)
	{
		if(row == -1)
		{
			reloadButton.setEnabled(false);
		}
		else
		{
			String temp = table.getValueAt(row,0).toString();
			boolean enable = true;
			for(int j=0;j<reloadVector.size();j++)
			{
				String str = reloadVector.elementAt(j).toString();
				if(str.equals(temp))
				{
					reloadButton.setEnabled(false);
					enable = false;
				}
			}
			if(enable)
			{
				reloadButton.setEnabled(true);
			}
		}
		if(table.getSelectedRow() == -1)
		{
			reloadButton.setEnabled(false);
		}
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

  //added for RTA spp Fix
  public void dispose()
  {
      
      discoveryFilterModel.listConfiguartionSession.dispose();
      table.removeKeyListener(table_table_conn31);    
      table.removeMouseMotionListener(table_table_conn21);
      table.removeMouseListener(table_table_conn11);
      addButton.removeActionListener(addButton_addButton_conn11);
      applyButton.removeActionListener(classPathButton_classPathButton_conn11);
      modifyButton.removeActionListener(modifyButton_modifyButton_conn11);
      reloadButton.removeActionListener(reloadButton_reloadButton_conn11);
      deleteButton.removeActionListener(deleteButton_deleteButton_conn11);
      table_table_conn31=null;
      table_table_conn21=null;
      table_table_conn11=null;
      addButton_addButton_conn11=null;
      classPathButton_classPathButton_conn11=null;
      modifyButton_modifyButton_conn11=null;
      reloadButton_reloadButton_conn11=null;
      deleteButton_deleteButton_conn11=null;
      
      discoveryFilterModel=null;
      classNameVector = null;
      customRenderer = null;
      reloadVector=null;
      tableModel=null;
      Top=null;
      table=null;
                     
  }
 public void modSetUpConnections()
 {
      table_table_conn31 =  new table_table_conn3();
      table.addKeyListener(table_table_conn31);
      table_table_conn21 =  new table_table_conn2();
      table.addMouseMotionListener(table_table_conn21);
      table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      addButton_addButton_conn11 =  new addButton_addButton_conn1();
      addButton.addActionListener(addButton_addButton_conn11);
      classPathButton_classPathButton_conn11 =  new classPathButton_classPathButton_conn1();
      applyButton.addActionListener(classPathButton_classPathButton_conn11);
      modifyButton_modifyButton_conn11 =  new modifyButton_modifyButton_conn1();
      modifyButton.addActionListener(modifyButton_modifyButton_conn11);
      reloadButton_reloadButton_conn11 =  new reloadButton_reloadButton_conn1();
      reloadButton.addActionListener(reloadButton_reloadButton_conn11);
      deleteButton_deleteButton_conn11 =  new deleteButton_deleteButton_conn1();
      deleteButton.addActionListener(deleteButton_deleteButton_conn11);
 }
 //end rta add
}
















