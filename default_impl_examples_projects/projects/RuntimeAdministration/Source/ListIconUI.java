

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;




//$Id: ListIconUI.java,v 1.2 2007/02/22 15:03:07 srajeswari Exp $



import com.adventnet.nms.util.CommonBuilderUIInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ListIconUI extends JPanel implements ApplyToServerInterface
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
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton nextButton = null;
	javax.swing.JButton previousButton = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField searchText = null;
	javax.swing.JButton searchButton = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JComboBox combo = null;
	javax.swing.JPanel JPanel9 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton modifyButton = null;
	javax.swing.JButton deleteButton = null;
	javax.swing.JButton detailsButton = null;
	javax.swing.JPanel JPanel10 = null;
	javax.swing.JButton applyButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	ListIconModel listIconModel = null;
  	int currentBeginIndex = 0;
	int currentEndIndex = 10;
	Vector mainVector = null;  
	int comboSelection =10;  
	int previousIndex = 0;
	Hashtable cloneHash = null;
	int selectedRow = 0;
	ListIconAddModify addModify = null;
	Vector severityVector = null;
	CommonBuilderUIInterface uiUtils = null;
	JProgressBar bar = null;
	Vector vectorForModify = null;
	Vector vectorForAdd = null;
	Vector vectorForDelete = null;
	static boolean isModified = false;
	boolean refetchFlag = false;

//added  for spp  RTA fix
combo_combo_conn1 combo_combo_conn11 =  null;
previousButton_previousButton_conn1 previousButton_previousButton_conn11 =  null;
table_table_conn1 table_table_conn11 =  null;
addButton_addButton_conn1 addButton_addButton_conn11 =  null;
searchButton_searchButton_conn1 searchButton_searchButton_conn11 =  null;
detailsButton_detailsButton_conn1 detailsButton_detailsButton_conn11 = null;
modifyButton_modifyButton_conn1 modifyButton_modifyButton_conn11 =  null;
nextButton_nextButton_conn1 nextButton_nextButton_conn11 =  null;
searchText_searchText_conn1 searchText_searchText_conn11 = null;
deleteButton_deleteButton_conn1 deleteButton_deleteButton_conn11 = null;
JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  null;
//end


  public ListIconUI()
  {
	uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
    //<Begin_ListIconUI>
    this.init();
  
    //<End_ListIconUI>

	
  	initializeVariables();
  }

  private void initializeVariables()
  {
	listIconModel = new ListIconModel(this);
	RuntimeConfigFrame.setStatusText(resourceBundle.getString("Loading Managed Object UI settings data....."));
	listIconModel.getListIconNodes();
	RuntimeConfigFrame.setBusyCursor(this);
  }

  public ListIconUI(java.applet.Applet applet)
  {
	uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
    //<Begin_ListIconUI_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ListIconUI_java.applet.Applet>
	
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
            JLabel1.setText(resourceBundle.getString("Managed Object UI Settings"));
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
            JLabel5.setText(resourceBundle.getString("( listicon.data )"));
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
            table.setToolTipText(resourceBundle.getString("Please double-click to view details"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

          try
          {
            JPanel7.setBorder(new javax.swing.border.LineBorder(new Color(-1),1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel7,ex); 
          }

//<UserCode_Begin_Bean_JPanel7>

//<UserCode_End_Bean_JPanel7>

          try
          {
            nextButton.setText(resourceBundle.getString(""));
            nextButton.setPreferredSize(new Dimension(30,25));
            nextButton.setMinimumSize(new Dimension(30,25));
            nextButton.setBorderPainted(false);
            nextButton.setFocusPainted(false);
            nextButton.setToolTipText(resourceBundle.getString("Next"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nextButton,ex); 
          }

//<UserCode_Begin_Bean_nextButton>

//<UserCode_End_Bean_nextButton>

          try
          {
            previousButton.setText(resourceBundle.getString(""));
            previousButton.setMinimumSize(new Dimension(30,25));
            previousButton.setPreferredSize(new Dimension(30,25));
            previousButton.setBorderPainted(false);
            previousButton.setFocusPainted(false);
            previousButton.setToolTipText(resourceBundle.getString("Previous"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+previousButton,ex); 
          }

//<UserCode_Begin_Bean_previousButton>

//<UserCode_End_Bean_previousButton>

          try
          {
            JLabel3.setText(resourceBundle.getString("Search for Type"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            searchButton.setText(resourceBundle.getString("Search"));
            searchButton.setBorderPainted(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+searchButton,ex); 
          }

//<UserCode_Begin_Bean_searchButton>

//<UserCode_End_Bean_searchButton>

          try
          {
            JPanel6.setMaximumSize(new Dimension(150,50));
            JPanel6.setMinimumSize(new Dimension(150,44));
            JPanel6.setPreferredSize(new Dimension(150,44));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            JLabel2.setText(resourceBundle.getString("Page Length"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            combo.setMaximumSize(new Dimension(50,24));
            combo.setMinimumSize(new Dimension(50,24));
            combo.setPreferredSize(new Dimension(50,24));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+combo,ex); 
          }

//<UserCode_Begin_Bean_combo>

//<UserCode_End_Bean_combo>

          try
          {
            JPanel9.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel9,ex); 
          }

//<UserCode_Begin_Bean_JPanel9>

//<UserCode_End_Bean_JPanel9>

          try
          {
            JPanel4.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

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
            JPanel10.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel10,ex); 
          }

//<UserCode_Begin_Bean_JPanel10>

//<UserCode_End_Bean_JPanel10>

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
            new Object[]{"Type ","Menu Name","Transparent Image"});
//<UserCode_End_Bean_tableModel>
		applyButton.setPreferredSize(new Dimension(applyButton.getPreferredSize().width+0,applyButton.getPreferredSize().height+4));
		detailsButton.setPreferredSize(new Dimension(detailsButton.getPreferredSize().width+0,detailsButton.getPreferredSize().height+4));
		deleteButton.setPreferredSize(new Dimension(deleteButton.getPreferredSize().width+0,deleteButton.getPreferredSize().height+4));
		modifyButton.setPreferredSize(new Dimension(modifyButton.getPreferredSize().width+0,modifyButton.getPreferredSize().height+4));
		addButton.setPreferredSize(new Dimension(addButton.getPreferredSize().width+10,addButton.getPreferredSize().height+4));
		combo.setPreferredSize(new Dimension(combo.getPreferredSize().width+0,combo.getPreferredSize().height+2));
		JLabel2.setPreferredSize(new Dimension(JLabel2.getPreferredSize().width+0,JLabel2.getPreferredSize().height+4));
		searchButton.setPreferredSize(new Dimension(searchButton.getPreferredSize().width+0,searchButton.getPreferredSize().height+4));
		searchText.setPreferredSize(new Dimension(searchText.getPreferredSize().width+0,searchText.getPreferredSize().height+4));
		JLabel3.setPreferredSize(new Dimension(JLabel3.getPreferredSize().width+0,JLabel3.getPreferredSize().height+4));
		JLabel5.setPreferredSize(new Dimension(JLabel5.getPreferredSize().width+0,JLabel5.getPreferredSize().height+4));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+4));

  
          //<End_setUpProperties>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("Type"),resourceBundle.getString("Menu Name"),resourceBundle.getString("Transparent Image")});
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
        setPreferredSize(new Dimension(getPreferredSize().width+685,getPreferredSize().height+476)); 
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
	nextButton.setIcon(uiUtils.getImage("next_mo.png"));
	previousButton.setIcon(uiUtils.getImage("back_mo.png"));
	JLabel4.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("listsettings.png","images/runtimeadmin"));
	combo.addItem("10");
	combo.addItem("20");
	combo.addItem("30");
	table.getTableHeader().setReorderingAllowed(false);
	addButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	modifyButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	deleteButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	detailsButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	searchButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JLabel5.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	combo.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	applyButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	applyButton.setEnabled(false);
	modifyButton.setEnabled(false);
	deleteButton.setEnabled(false);
	detailsButton.setEnabled(false);
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

      combo_combo_conn1 combo_combo_conn11 =  new combo_combo_conn1();
      combo.addActionListener(combo_combo_conn11);
      previousButton_previousButton_conn1 previousButton_previousButton_conn11 =  new previousButton_previousButton_conn1();
      previousButton.addActionListener(previousButton_previousButton_conn11);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      addButton_addButton_conn1 addButton_addButton_conn11 =  new addButton_addButton_conn1();
      addButton.addActionListener(addButton_addButton_conn11);
      searchButton_searchButton_conn1 searchButton_searchButton_conn11 =  new searchButton_searchButton_conn1();
      searchButton.addActionListener(searchButton_searchButton_conn11);
      detailsButton_detailsButton_conn1 detailsButton_detailsButton_conn11 =  new detailsButton_detailsButton_conn1();
      detailsButton.addActionListener(detailsButton_detailsButton_conn11);
      modifyButton_modifyButton_conn1 modifyButton_modifyButton_conn11 =  new modifyButton_modifyButton_conn1();
      modifyButton.addActionListener(modifyButton_modifyButton_conn11);
      nextButton_nextButton_conn1 nextButton_nextButton_conn11 =  new nextButton_nextButton_conn1();
      nextButton.addActionListener(nextButton_nextButton_conn11);
      searchText_searchText_conn1 searchText_searchText_conn11 =  new searchText_searchText_conn1();
      searchText.addKeyListener(searchText_searchText_conn11);
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
        JPanel8= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JLabel5= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel7= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        nextButton= new javax.swing.JButton();
        previousButton= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        searchText= new javax.swing.JTextField();
        searchButton= new javax.swing.JButton();
        JPanel6= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        combo= new javax.swing.JComboBox();
        JPanel9= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        addButton= new javax.swing.JButton();
        modifyButton= new javax.swing.JButton();
        deleteButton= new javax.swing.JButton();
        detailsButton= new javax.swing.JButton();
        JPanel10= new javax.swing.JPanel();
        applyButton= new javax.swing.JButton();
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
JPanel8.add(JLabel4);
JPanel8.add(JLabel1);
JPanel8.add(JLabel5);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel7,cons);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(5,10,5,10);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel7.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel3.add(nextButton,cons);
inset = new Insets(0,0,0,10);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel3.add(previousButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel7.add(JPanel5,cons);
JPanel5.setLayout(new BorderLayout(5,5));
JPanel5.add(JLabel3,BorderLayout.WEST);
JPanel5.add(searchText,BorderLayout.CENTER);
JPanel5.add(searchButton,BorderLayout.EAST);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel7.add(JPanel6,cons);
JPanel6.setLayout(new FlowLayout(1,5,8));
JPanel6.add(JLabel2);
JPanel6.add(combo);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel9,cons);
JPanel9.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel9.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(1,5,5));
JPanel4.add(addButton);
JPanel4.add(modifyButton);
JPanel4.add(deleteButton);
JPanel4.add(detailsButton);
inset = new Insets(0,10,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel9.add(JPanel10,cons);
JPanel10.setLayout(new FlowLayout(1,5,5));
JPanel10.add(applyButton);

  
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

	public void updateTheUI(Hashtable mainHash)
	{
		if(! refetchFlag)
		{
			RuntimeConfigFrame.setStatusText(resourceBundle.getString("Done"));
			RuntimeConfigFrame.setDefaultCursor(this);
		}
		cloneHash = mainHash;
		mainVector = convertToVector(mainHash);
		severityVector = com.adventnet.nms.severity.SeverityInfo.getInstance().getNames();
		int vectorSize = mainVector.size();
		fillTheTableWith(mainVector,currentBeginIndex,currentEndIndex);
		
	}
	
	public Vector convertToVector(Hashtable hash)
	{
		Vector v = new Vector();
		Enumeration enumerate = hash.keys();
		while(enumerate.hasMoreElements())
		{
			v.add(hash.get(enumerate.nextElement()));
		}
		return v;
	}

	public void fillTheTableWith(Vector vect,int begin,int end)
	{
		currentBeginIndex = begin;
		if(begin < 0)
		{
			begin =0;
			end = comboSelection;
		}
		if(begin > vect.size())
		{
			return;
		}
		if(end > vect.size())
		{
			end = vect.size();
		}
		currentEndIndex = end;
		for(int i=begin;i<end;i++)
		{
			Hashtable childHash = (Hashtable)vect.elementAt(i);
			Vector v = new Vector();
			v.add(childHash.get("TYPE"));
			v.add(childHash.get("MENU"));
			String s = "";
			if(childHash.get("DEFAULT_TRANSPARENT_IMG") == null)
				s = "";
			else
				s = childHash.get("DEFAULT_TRANSPARENT_IMG").toString();
			v.add(s);
			tableModel.addRow(v);
		}
	}


 
//<Begin__class_nextButton_nextButton_conn1>

 class nextButton_nextButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_nextButton_nextButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  nextButtonActionPerformed();
     }
//<UserCode_End_Connection_nextButton_nextButton_conn1>
 }//<End__class_nextButton_nextButton_conn1>
	
	public void nextButtonActionPerformed()
	{
		if(currentBeginIndex > mainVector.size())
		{
			return;
		}
		if(currentEndIndex >= mainVector.size())
		{
			currentEndIndex = mainVector.size();
			return;
		}
		removeTableRows();
		fillTheTableWith(mainVector,currentEndIndex,currentEndIndex+comboSelection);
	}

 
//<Begin__class_previousButton_previousButton_conn1>

 class previousButton_previousButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_previousButton_previousButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  previousButtonActionPerformed();
     }
//<UserCode_End_Connection_previousButton_previousButton_conn1>
 }//<End__class_previousButton_previousButton_conn1>
	
	public void previousButtonActionPerformed()
	{
		if(currentBeginIndex <= 0)
		{
			currentBeginIndex = 0;
			removeTableRows();
			fillTheTableWith(mainVector,currentBeginIndex,currentBeginIndex+comboSelection);
			return;
		}
		removeTableRows();
		fillTheTableWith(mainVector,currentBeginIndex-comboSelection,currentBeginIndex);
	}

 
//<Begin__class_combo_combo_conn1>

 class combo_combo_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_combo_combo_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  comboActionPerformed();
     }
//<UserCode_End_Connection_combo_combo_conn1>
 }//<End__class_combo_combo_conn1>
	
	public void comboActionPerformed()
	{
		int index = combo.getSelectedIndex();
		if(index == 0)
		{
			comboSelection = 10;
		}	
		else if(index == 1)
		{
			comboSelection = 20;
		}
		else if(index == 2)
		{
			comboSelection = 30;
		}
		if(previousIndex > index)
		{
			removeTableRows();
			fillTheTableWith(mainVector,currentBeginIndex,currentBeginIndex+comboSelection);	
		}
		else if(previousIndex < index)
		{
			removeTableRows();
			fillTheTableWith(mainVector,currentBeginIndex,currentBeginIndex+comboSelection);
		}
		previousIndex = index;
	}

	public void removeTableRows()
	{
		for(int j=table.getRowCount()-1;j >=0;j--)
		{
			tableModel.removeRow(j);
		} 		
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
	
	public void detailsButtonActionPerformed()
	{
		Hashtable childHash = getTheChildHash();
		if(childHash != null)
		{
			ListIconDetailsDialog details = new ListIconDetailsDialog(applet);
			details.init();
			details.populateDetails(childHash);
			details.setVisible(true);
		}
	}

	public Hashtable getTheChildHash()
	{
		int row = table.getSelectedRow();
		Hashtable childHash = null;
		if(row != -1)
		{
			String type = table.getValueAt(row,0).toString();
			Enumeration enumerate = cloneHash.keys();
			while(enumerate.hasMoreElements())
			{
				String temp = enumerate.nextElement().toString();
				if(temp.equals(type))
				{
					childHash = (Hashtable)cloneHash.get(temp);
					break;
				}
			}
		}
		return childHash;
	}
	public void updateEntries(String type, String menu,Hashtable hash,boolean isModify)
	{
		isModified = true;
		type = type.trim();
		boolean flag = true;
		if(isModify)
		{
			mainVector.removeElementAt(selectedRow);
			mainVector.insertElementAt(hash,selectedRow);
			table.setValueAt(menu,selectedRow,1);
			if(hash.get("DEFAULT_TRANSPARENT_IMG") != null)
				table.setValueAt(hash.get("DEFAULT_TRANSPARENT_IMG"),selectedRow,2);
			else
				table.setValueAt("",selectedRow,2);
			cloneHash.put(type,hash);
			if(vectorForModify == null)
			{
				vectorForModify = new Vector();
			}
			vectorForModify.add(hash);
			applyButton.setEnabled(true);
			table.getSelectionModel().addSelectionInterval(selectedRow,selectedRow);
		}
		else
		{
			if(flag)
			{
					mainVector.insertElementAt(hash,currentBeginIndex);	
					removeTableRows();
					fillTheTableWith(mainVector,currentBeginIndex,currentBeginIndex+comboSelection);
			}
			table.getSelectionModel().addSelectionInterval(0,0);
			cloneHash.put(type,hash);
			if(vectorForAdd == null)
			{
				vectorForAdd = new Vector();
			}
			vectorForAdd.add(hash);
			applyButton.setEnabled(true);
		}
		tableMousePressedEvent();
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
		addModify = new ListIconAddModify(severityVector,this,applet);
		addModify.init();
		addModify.addRequest(severityVector,cloneHash);
		addModify.setVisible(true);
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
		selectedRow = table.getSelectedRow();
		Hashtable childHash = getTheChildHash();
		if(childHash != null)
		{
			addModify = new ListIconAddModify(severityVector,this,applet);
			addModify.init();
			addModify.populateUI(childHash);
			addModify.setVisible(true);
		}
	}


//<Begin__class_searchButton_searchButton_conn1>

 class searchButton_searchButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_searchButton_searchButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  searchButtonActionPerformed();
     }
//<UserCode_End_Connection_searchButton_searchButton_conn1>
 }//<End__class_searchButton_searchButton_conn1>
	
	public void searchButtonActionPerformed()
	{
		String type = searchText.getText().trim();
		boolean find = false;
		int searchRow = currentBeginIndex;
		if(type.equalsIgnoreCase(""))
			return;
		for(int i=0;i<mainVector.size();i++)
		{
			Hashtable temphash = (Hashtable)mainVector.elementAt(i);
			String temp = temphash.get("TYPE").toString();
			if(temp.equalsIgnoreCase(type))
			{
				searchRow = i;
				find = true;
				break;
			}
		}
		if(find)
		{
			currentBeginIndex = searchRow;
			removeTableRows();
			fillTheTableWith(mainVector,currentBeginIndex,currentBeginIndex+comboSelection);
			table.getSelectionModel().addSelectionInterval(0,0);
		}
		else
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Type ")+type+resourceBundle.getString(" not found"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
			return;
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
		int option =  jp.showConfirmDialog(null, resourceBundle.getString("Do you really want to delete the selected item(s)"),resourceBundle.getString("Confirmation"),jp.YES_NO_OPTION);
		if (option == jp.YES_OPTION)
		{ 
			isModified = true;
			for(int j=length-1;j >=0;j--)
			{
				mainVector.removeElementAt(currentBeginIndex+selectedRows[j]);
				if(vectorForDelete == null)
				{
					vectorForDelete = new Vector();
				}
				vectorForDelete.add(cloneHash.get(table.getValueAt(selectedRows[j],0)));
				cloneHash.remove(table.getValueAt(selectedRows[j],0));
				tableModel.removeRow(selectedRows[j]);
			} 
  	   	}
		removeTableRows();
		applyButton.setEnabled(true);
		fillTheTableWith(mainVector,currentBeginIndex,currentBeginIndex+comboSelection);
		tableMousePressedEvent();
	}


//<Begin__class_searchText_searchText_conn1>

 class searchText_searchText_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_searchText_searchText_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
  if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
  {
   searchButtonActionPerformed();
  }
     }
//<UserCode_End_Connection_searchText_searchText_conn1>
 }//<End__class_searchText_searchText_conn1>
	
	public boolean  applyToServer()
	{
      try
      {
		if(isModified())
		{
			RuntimeConfigFrame.setBusyCursor(this);
			RuntimeConfigFrame.applyStatus();
			if(vectorForAdd != null)
			{
				for(int i=0;i<vectorForAdd.size();i++)
				{
					listIconModel.addListIconNode((Hashtable)vectorForAdd.elementAt(i));
				}
				vectorForAdd.removeAllElements();
			}
			if(vectorForModify != null)
			{
				for(int i=0;i<vectorForModify.size();i++)
				{
					listIconModel.modifyListIconNode((Hashtable)vectorForModify.elementAt(i));
				}
				vectorForModify.removeAllElements();	
			}
			if(vectorForDelete != null)
			{
				for(int i=0;i<vectorForDelete.size();i++)
				{
					listIconModel.deleteListIconNode((Hashtable)vectorForDelete.elementAt(i));
				}
				vectorForDelete.removeAllElements();	
			}
			isModified = false;
			applyButton.setEnabled(false);
		}

      }
        catch(Exception exec)
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
	
	public void updateTheUI()
	{
		RuntimeConfigFrame.setDefaultCursor(this);
		RuntimeConfigFrame.applied();
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
		applyButton.setEnabled(false);
		modifyButton.setEnabled(false);
		deleteButton.setEnabled(false);
		detailsButton.setEnabled(false);
		listIconModel.getListIconNodes();
	}


 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

 //added for RTA spp fix
 
  public void dispose(){
      
     
    listIconModel.listConfiguartionSession.dispose();
    combo.removeActionListener(combo_combo_conn11);
    previousButton.removeActionListener(previousButton_previousButton_conn11);
    table.removeMouseListener(table_table_conn11);
    addButton.removeActionListener(addButton_addButton_conn11);
    searchButton.removeActionListener(searchButton_searchButton_conn11);
    detailsButton.removeActionListener(detailsButton_detailsButton_conn11);
    modifyButton.removeActionListener(modifyButton_modifyButton_conn11);
    nextButton.removeActionListener(nextButton_nextButton_conn11);
    searchText.removeKeyListener(searchText_searchText_conn11);
    deleteButton.removeActionListener(deleteButton_deleteButton_conn11);
    applyButton.removeActionListener(JButton1_JButton1_conn11);
    JButton1_JButton1_conn11=null;
    deleteButton_deleteButton_conn11=null;
    searchText_searchText_conn11=null;
    nextButton_nextButton_conn11=null;
    modifyButton_modifyButton_conn11=null;
    detailsButton_detailsButton_conn11=null;
    searchButton_searchButton_conn11=null;
    addButton_addButton_conn11=null;
    table_table_conn11=null;
    previousButton_previousButton_conn11=null;
    combo_combo_conn11=null;
    combo=null;
    previousButton=null;
    addButton=null;
    searchButton=null;
    detailsButton=null;
    modifyButton=null;
    nextButton=null;
    searchText=null;
    applyButton =null;
    deleteButton=null;
    listIconModel=null;
    addModify=null;
    mainVector = null;
    cloneHash = null;
    severityVector = null;
    uiUtils = null;
    vectorForModify = null;
    vectorForAdd = null;
    Vector vectorForDelete = null;
    
    Top=null;      
    table=null;
    
  }
 public void modSetUpConnections()
 {

      combo_combo_conn11 =  new combo_combo_conn1();
      combo.addActionListener(combo_combo_conn11);
      previousButton_previousButton_conn11 =  new previousButton_previousButton_conn1();
      previousButton.addActionListener(previousButton_previousButton_conn11);
      table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      addButton_addButton_conn11 =  new addButton_addButton_conn1();
      addButton.addActionListener(addButton_addButton_conn11);
      searchButton_searchButton_conn11 =  new searchButton_searchButton_conn1();
      searchButton.addActionListener(searchButton_searchButton_conn11);
      detailsButton_detailsButton_conn11 =  new detailsButton_detailsButton_conn1();
      detailsButton.addActionListener(detailsButton_detailsButton_conn11);
      modifyButton_modifyButton_conn11 =  new modifyButton_modifyButton_conn1();
      modifyButton.addActionListener(modifyButton_modifyButton_conn11);
      nextButton_nextButton_conn11 =  new nextButton_nextButton_conn1();
      nextButton.addActionListener(nextButton_nextButton_conn11);
      searchText_searchText_conn11 =  new searchText_searchText_conn1();
      searchText.addKeyListener(searchText_searchText_conn11);
      deleteButton_deleteButton_conn11 =  new deleteButton_deleteButton_conn1();
      deleteButton.addActionListener(deleteButton_deleteButton_conn11);
      JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      applyButton.addActionListener(JButton1_JButton1_conn11);
 }     
 //end of addition
}













