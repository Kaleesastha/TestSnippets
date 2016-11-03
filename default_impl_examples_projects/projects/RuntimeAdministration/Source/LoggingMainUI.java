

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;




//$Id: LoggingMainUI.java,v 1.4 2007/08/02 06:25:09 aravinds Exp $




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LoggingMainUI extends JPanel implements ApplyToServerInterface
{
	//<Begin_Variable_Declarations>
    public static boolean isNMSLogging = true;
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JTextArea JTextArea2 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton modifyButton = null;
	javax.swing.JButton deleteButton = null;
	javax.swing.JButton detailsButton = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JButton applyButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	LoggingConfiguartionModel loggingModel = null;
	Hashtable localMainHash = null;  
	int selectedRowForModify = 0;
	boolean modifyFlag = false;
	Vector vectorForAdd = null;
	Vector vectorForModify = null;
	Vector vectorForDelete = null;
	boolean isModified = false;
	boolean refetchFlag = false;
    JPanel dummyPanel = null;
    JLabel dummyLabel = null;












    public void showDummyPanel()
    {
        if(!isNMSLogging)
        {            
	    RuntimeConfigFrame.getInstance().hstPanelNames.put("Log Settings",dummyPanel);
	    dummyPanel.setLayout(new BorderLayout(5,5));
	    dummyPanel.add(dummyLabel,BorderLayout.CENTER);
	    RuntimeConfigFrame.getInstance().basePanel.removeAll();
	    RuntimeConfigFrame.getInstance().basePanel.add(dummyPanel,BorderLayout.CENTER);
	    RuntimeConfigFrame.getInstance().basePanel.updateUI();
	    RuntimeConfigFrame.setStatusText("Done"); // No I18N
	    RuntimeConfigFrame.setDefaultCursor(RuntimeConfigFrame.getInstance());            
	    isNMSLogging=false; 
 	}
    }
   


  public LoggingMainUI()
  {
      
    //<Begin_LoggingMainUI>
    this.init();
  
    //<End_LoggingMainUI>
  	initializeVariables();
  }

  private void initializeVariables()
  {
          loggingModel = new LoggingConfiguartionModel(this);
          RuntimeConfigFrame.setStatusText("Loading Logging data.....");
          loggingModel.getLogNodes();
          RuntimeConfigFrame.setBusyCursor(this);
  }

  public LoggingMainUI(java.applet.Applet applet)
  {            
    //<Begin_LoggingMainUI_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_LoggingMainUI_java.applet.Applet>
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
       try
       {
           dummyLabel.setHorizontalAlignment(0);
           dummyLabel.setFont(new Font("SansSerif",0,14));
           dummyLabel.setForeground(new Color(-16777216));
           dummyLabel.setHorizontalTextPosition(4);
           dummyLabel.setText(resourceBundle.getString("javaui.logging.notavailable"));
       }
       catch(Exception ex)
       {
           showStatus("Exception while setting properties for bean "+JLabel1,ex); 
       }
  //<Begin_setUpProperties> 

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
            JLabel1.setText(resourceBundle.getString("Logging Configuration"));
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
            JLabel5.setText(resourceBundle.getString("( logging_parameters.conf )"));
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
            table.setModel(tableModel);
            table.setToolTipText(resourceBundle.getString("Please double-click to view details"));
            table.setRowHeight(22);
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
            JPanel7.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel7,ex); 
          }

//<UserCode_Begin_Bean_JPanel7>

//<UserCode_End_Bean_JPanel7>

          try
          {
            JPanel3.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

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
            detailsButton.setText(resourceBundle.getString("View Details"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+detailsButton,ex); 
          }

//<UserCode_Begin_Bean_detailsButton>

//<UserCode_End_Bean_detailsButton>

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
            applyButton.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+applyButton,ex); 
          }

//<UserCode_Begin_Bean_applyButton>

//<UserCode_End_Bean_applyButton>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"File Name ","Directory","Maximum Lines","File Count"});
//<UserCode_End_Bean_tableModel>
		applyButton.setPreferredSize(new Dimension(applyButton.getPreferredSize().width+0,applyButton.getPreferredSize().height+4));
		detailsButton.setPreferredSize(new Dimension(detailsButton.getPreferredSize().width+0,detailsButton.getPreferredSize().height+4));
		deleteButton.setPreferredSize(new Dimension(deleteButton.getPreferredSize().width+0,deleteButton.getPreferredSize().height+4));
		modifyButton.setPreferredSize(new Dimension(modifyButton.getPreferredSize().width+0,modifyButton.getPreferredSize().height+4));
		addButton.setPreferredSize(new Dimension(addButton.getPreferredSize().width+10,addButton.getPreferredSize().height+4));
		JLabel5.setPreferredSize(new Dimension(JLabel5.getPreferredSize().width+0,JLabel5.getPreferredSize().height+4));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+4));

  
          //<End_setUpProperties>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("File Name"),resourceBundle.getString("Directory"),resourceBundle.getString("Maximum Lines"),resourceBundle.getString("File Count")});	
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
        setPreferredSize(new Dimension(getPreferredSize().width+667,getPreferredSize().height+506)); 
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
	table.setDefaultRenderer(Object.class,new CustomRenderer());
	deleteButton.setVisible(false);	
	JLabel2.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));
	JLabel3.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));
	JTextArea1.setText(resourceBundle.getString("Users can configure their logging mechanism using this configurator"));
	JTextArea2.setText(resourceBundle.getString("Log settings already configured cannot be deleted. They can only be enabled or disabled"));
	JTextArea1.setEditable(false);
	JTextArea2.setEditable(false);	
	table.getTableHeader().setReorderingAllowed(false);
	addButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	modifyButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	detailsButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	applyButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JLabel5.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	applyButton.setEnabled(false);
	modifyButton.setEnabled(false);
	deleteButton.setEnabled(false);
	detailsButton.setEnabled(false);
	JLabel4.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("logsettings.png", "images/runtimeadmin"));
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      addButton_addButton_conn1 addButton_addButton_conn11 =  new addButton_addButton_conn1();
      addButton.addActionListener(addButton_addButton_conn11);
      modifyButton_modifyButton_conn1 modifyButton_modifyButton_conn11 =  new modifyButton_modifyButton_conn1();
      modifyButton.addActionListener(modifyButton_modifyButton_conn11);
      detailsButton_detailsButton_conn1 detailsButton_detailsButton_conn11 =  new detailsButton_detailsButton_conn1();
      detailsButton.addActionListener(detailsButton_detailsButton_conn11);
      deleteButton_deleteButton_conn1 deleteButton_deleteButton_conn11 =  new deleteButton_deleteButton_conn1();
      deleteButton.addActionListener(deleteButton_deleteButton_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      applyButton.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JLabel5= new javax.swing.JLabel();
        JPanel6= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel4= new javax.swing.JPanel();
        JTextArea1= new javax.swing.JTextArea();
        JTextArea2= new javax.swing.JTextArea();
        JLabel2= new javax.swing.JLabel();
        JLabel3= new javax.swing.JLabel();
        JPanel7= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        addButton= new javax.swing.JButton();
        modifyButton= new javax.swing.JButton();
        deleteButton= new javax.swing.JButton();
        detailsButton= new javax.swing.JButton();
        JPanel8= new javax.swing.JPanel();
        applyButton= new javax.swing.JButton();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
        dummyLabel = new javax.swing.JLabel();
        dummyPanel = new javax.swing.JPanel();
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
JPanel1.add(JPanel5,BorderLayout.CENTER);
JPanel5.setLayout(new FlowLayout(1,10,5));
JPanel5.add(JLabel4);
JPanel5.add(JLabel1);
JPanel5.add(JLabel5);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JTextArea1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JTextArea2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel7,cons);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,10);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel7.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(addButton);
JPanel3.add(modifyButton);
JPanel3.add(deleteButton);
JPanel3.add(detailsButton);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel7.add(JPanel8,cons);
JPanel8.setLayout(new FlowLayout(1,5,5));
JPanel8.add(applyButton);

  
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



 
//<Begin__class_detailsButton_detailsButton_conn1>

 class detailsButton_detailsButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_detailsButton_detailsButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  detailsButtonActionPerformed();
     }
//<UserCode_End_Connection_detailsButton_detailsButton_conn1>
 }//<End__class_detailsButton_detailsButton_conn1>
	
	public void updateTheUI(Hashtable mainHash)
	{
		if(!refetchFlag)
		{
			RuntimeConfigFrame.setStatusText("Done");
			RuntimeConfigFrame.setDefaultCursor(this);
		}
		localMainHash = mainHash;
		Enumeration enumerate = localMainHash.keys();
		while(enumerate.hasMoreElements())
		{
			Hashtable tempHash = (Hashtable)localMainHash.get(enumerate.nextElement());
			Vector vect = new Vector();
			vect.add(tempHash.get("FileName"));
			vect.add(tempHash.get("LogsDirectory"));
			vect.add(tempHash.get("MaxLines"));
			vect.add(tempHash.get("FileCount"));
			tableModel.addRow(vect);
		}
	}

	public void updateTheUI()
	{
		RuntimeConfigFrame.setDefaultCursor(this);
		RuntimeConfigFrame.applied();
		//System.out.println("Update the UI called");
	}

	public void detailsButtonActionPerformed()
	{
		int row = table.getSelectedRow();
		if(row != -1)
		{
			String name = table.getValueAt(row,0).toString();
			Enumeration enumerate = localMainHash.keys();
			while(enumerate.hasMoreElements())
			{
				boolean specialFlag = false;
				String temp = enumerate.nextElement().toString();
				if(temp.equals("stdout.txt") || temp.equals("stderr.txt"))
					specialFlag = true;
				if(name.equals(temp))
				{
					Hashtable hash = (Hashtable)localMainHash.get(temp);
					if(temp.equals("stdout.txt") || temp.equals("stderr.txt"))
					{
						LogsAddModifyDialog special = new LogsAddModifyDialog(applet);
						special.init();
						special.specialEntry(hash);
						special.setVisible(true);
					}
					else
					{
						LogsDetails details = new LogsDetails(applet);
						details.init();
						details.populateTheUI(hash,specialFlag);
						details.setVisible(true);
					}
				}
			}
		}
	}
	
	public void fillTheDetailsDialog(boolean isDetails)
	{
		boolean flag = false;
		int selectedRow = table.getSelectedRow();
		boolean specialFlag = false;
		modifyFlag = true;
		selectedRowForModify = selectedRow;
		Hashtable temp = null;
		Hashtable keyNodeHash = null;
		if(selectedRow != -1)
		{
			String value = table.getValueAt(selectedRow,0).toString();
			if(value.equals("stdout.txt") || value.equals("stderr.txt"))
			{
				specialFlag = true;
			}
			else
			{
				specialFlag = false;
			}
			Enumeration enumerate = localMainHash.keys();
			while(enumerate.hasMoreElements())
			{
				if(enumerate.nextElement().equals(value))
				{
					temp = (Hashtable)localMainHash.get(value);
					keyNodeHash = (Hashtable)temp.get("keyNodeVect");
					if(keyNodeHash == null)
					{
						keyNodeHash = new Hashtable();
					}
					if (keyNodeHash.equals(new Hashtable()))
					{
						flag = true;
					}
					break;
				}	
			}
			LogsAddModifyWizard detailsDialog = new LogsAddModifyWizard(this,specialFlag,applet);
			detailsDialog.init();
			detailsDialog.populateTheUI(temp,specialFlag);
			detailsDialog.setVisible(true);
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
		boolean isDetails = false;
		modifyFlag = true;
		fillTheDetailsDialog(isDetails);
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
			LogsAddModifyWizard detailsDialog = new LogsAddModifyWizard(this,false,applet);
			detailsDialog.init();
			modifyFlag = false;
			detailsDialog.addRequest(localMainHash);
			detailsDialog.setVisible(true);	
	}

	public void updateEntries(Hashtable hash)
	{
		isModified = true;
		if(modifyFlag)
		{
			table.setValueAt(hash.get("MaxLines"),selectedRowForModify,2);
			table.setValueAt(hash.get("FileCount"),selectedRowForModify,3);
			localMainHash.put(hash.get("FileName"),hash);
			//table.getSelectionModel().addSelectionInterval(selectedRowForModify,selectedRowForModify);
			if(vectorForModify == null)
			{
				vectorForModify = new Vector();
			}
			Hashtable temp = new Hashtable();
			temp.put(hash.get("FileName").toString().trim(),hash);
			vectorForModify.add(temp);
			applyButton.setEnabled(true);		
		}
		else
		{
			Vector v = new Vector();
			v.add(hash.get("FileName"));
			v.add(hash.get("LogsDirectory"));
			v.add(hash.get("MaxLines"));
			v.add(hash.get("FileCount"));
			tableModel.addRow(v);
			localMainHash.put(hash.get("FileName").toString().trim(),hash);
			if(vectorForAdd == null)
			{
				vectorForAdd = new Vector();
			}
			Hashtable temp = new Hashtable();
			temp.put(hash.get("FileName").toString().trim(),hash);
			vectorForAdd.add(temp);
			table.getSelectionModel().clearSelection();
			//table.getSelectionModel().addSelectionInterval(table.getRowCount()-1,table.getRowCount()-1);
			applyButton.setEnabled(true);
		}
		tableMousePressedEvent();
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
				if(vectorForDelete == null)
				{
					vectorForDelete = new Vector();
				}
				localMainHash.remove(table.getValueAt(selectedRows[j],0));
				tableModel.removeRow(selectedRows[j]);
			} 
			applyButton.setEnabled(true);
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
        {       //Add Ends
    
	 if(isModified())
		{
			RuntimeConfigFrame.setBusyCursor(this);
			RuntimeConfigFrame.applyStatus();
			//isModified = false;
			if(vectorForAdd != null)
			{
				for(int i=0;i<vectorForAdd.size();i++)
				{
					loggingModel.addLogNode((Hashtable)vectorForAdd.elementAt(i));
				}
				vectorForAdd.removeAllElements();
			}
			if(vectorForModify != null)
			{
				for(int i=0;i<vectorForModify.size();i++)
				{
					loggingModel.modifyLogNode((Hashtable)vectorForModify.elementAt(i));
				}
				vectorForModify.removeAllElements();
			}
			if(vectorForDelete != null)
			{
				/*for(int i=0;i<vectorForDelete.size();i++)
				{
					loggingModel.deleteLogNode((Hashtable)vectorForDelete.elementAt(i));
				}
				vectorForDelete.removeAllElements();	*/
			}
                        isModified = false;
			applyButton.setEnabled(false);
		}
        // Added by Balan on 21/06/03 for SPP
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

 
//<Begin__class_table_table_conn1>

 class table_table_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_table_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
  if(arg0.getClickCount() == 2)
  {
   detailsButtonActionPerformed();
  }
     }



     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  tableMousePressedEvent();
     }
 
//<UserCode_End_Connection_table_table_conn1>
 }//<End__class_table_table_conn1>


//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  applyToServer();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>
	
	public void tableMousePressedEvent()
	{
		if(table.getSelectedRow() == -1)
		{
			modifyButton.setEnabled(false);
			deleteButton.setEnabled(false);
			detailsButton.setEnabled(false);
		}
		else
		{
				modifyButton.setEnabled(true);
				deleteButton.setEnabled(true);
				detailsButton.setEnabled(true);
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

		loggingModel.getLogNodes();
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

   // Added by Balan on 08/07/03 for SPP for Memory Leak issue
    public void dispose()
    {
      loggingModel.loggingConfiguartionSession.dispose();
       
      loggingModel = null;
      if(localMainHash != null)
          localMainHash.clear();
          localMainHash = null;
      if(vectorForAdd != null)
          vectorForAdd.removeAllElements();
          vectorForAdd = null;
      if(vectorForModify != null)
          vectorForModify.removeAllElements();
          vectorForModify = null;
      if(vectorForDelete != null)
          vectorForDelete.removeAllElements();
          vectorForDelete = null; 
     }
   // Add Ends
}











