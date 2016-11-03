//$Id: TabbedPane2.java,v 1.4 2010/10/29 13:46:42 swaminathap Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$2
//<End_Version>
package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.util.Properties;
import java.util.Hashtable;


public class TabbedPane2 extends JPanel implements SecurityCommonInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JTabbedPane JTabbedPane1 = null;
	javax.swing.JPanel JPanel3111 = null;
	javax.swing.JScrollPane JScrollPane211 = null;
	com.adventnet.beans.table.SortTable SortTable1 = null;
	javax.swing.JButton JButton4 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JScrollPane JScrollPane2111 = null;
	com.adventnet.beans.table.SortTable SortTable11 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JSplitPane JSplitPane1 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JComboBox JComboBox1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JButton JButton5 = null;
	javax.swing.JButton JButton6 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	com.adventnet.beans.table.SortTable SortTable3 = null;
	javax.swing.JButton JButton21 = null;
	javax.swing.DefaultListModel AllUsers = null;
	javax.swing.DefaultListModel UserModel = null;
	com.adventnet.beans.table.SortTableModel SortTableModel1 = null;
	com.adventnet.beans.table.SortTableModel SortTableModel2 = null;
	com.adventnet.beans.table.SortTableModel SortTableModel4 = null;
	javax.swing.DefaultComboBoxModel DefaultComboBoxModel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	JPopupMenu   Delete= null;
	JMenuItem      Delete_AuthorizedViewMenuItem = null;
	PopupMenuMouseListener popupMenuMouseListener = new PopupMenuMouseListener();
	MenuToolBarAction menuToolBarAction = new MenuToolBarAction();
	//<End_Variable_Declarations>

	private String user ="";
	
	static com.adventnet.security.ui.AbstractSecurityModel model = null;
	
	//Screens from this pane.
	protected com.adventnet.security.ui.TreeConfig treec = null;
	protected com.adventnet.security.ui.AssignProp prop = null;
	protected com.adventnet.security.ui.ViewProperty viewProp = null;  
	protected com.adventnet.security.ui.OperationViewProperty operViewProp = null;
	//End for the screens from this pane.

	String viewVec = null;
	private String forTheViewProp = "";
	private Vector forTheViewPropVec = new Vector();
	private String toDelete="";


	//This will maintain the mapping of resource string to original operation name. Added in SP8.
	Properties resourceToOper = new Properties();  

 
    public void init()
  {
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+683,getPreferredSize().height+422)); 
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
setUpMenus();

  
         //<End_init>
	String[] columnNames = {resourceBundle.getString("User Name")};
	SortTableModel1.setDataVector(new Object[0][0],columnNames);
	String[] columnNames1 = {resourceBundle.getString("Operation Name"),resourceBundle.getString("Type")};
          SortTableModel2.setDataVector(new Object[0][0],columnNames1);
	JButton4.setMnemonic('S');
	JButton2.setMnemonic('c');
	JButton1.setMnemonic('S');
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "SecurityAdministrationResources"; 
            if (input.equals("MS_MODE")) value = "3"; 
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            JTabbedPane1.setFont(new Font("SansSerif",0,12));
            JTabbedPane1.setSelectedIndex(1);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTabbedPane1,ex); 
          }

//<UserCode_Begin_Bean_JTabbedPane1>

//<UserCode_End_Bean_JTabbedPane1>

          try
          {
            SortTable1.setModel(SortTableModel1);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+SortTable1,ex); 
          }

//<UserCode_Begin_Bean_SortTable1>

//<UserCode_End_Bean_SortTable1>

          try
          {
            JButton4.setFont(new Font("SansSerif",0,12));
            JButton4.setHorizontalTextPosition(4);
	    JButton4.setText(resourceBundle.getString("javaui.securityadmin.button.settingusers"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton4,ex); 
          }

//<UserCode_Begin_Bean_JButton4>
JButton4.setMnemonic('E');
//<UserCode_End_Bean_JButton4>

          try
          {
            SortTable11.setModel(SortTableModel2);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+SortTable11,ex); 
          }

//<UserCode_Begin_Bean_SortTable11>

//<UserCode_End_Bean_SortTable11>

          try
          {
            JButton1.setFont(new Font("SansSerif",0,12));
            JButton1.setHorizontalTextPosition(4);
            JButton1.setText(resourceBundle.getString("Set Permissions"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>
JButton1.setMnemonic('E');
//<UserCode_End_Bean_JButton1>

          try
          {
            JButton2.setFont(new Font("SansSerif",0,12));
            JButton2.setHorizontalTextPosition(4);
            JButton2.setEnabled(false);
            JButton2.setText(resourceBundle.getString("Setting Scope"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>
JButton2.setMnemonic('S');
//<UserCode_End_Bean_JButton2>

          try
          {
            JSplitPane1.setOrientation(0);
            JSplitPane1.setDividerLocation(90);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JSplitPane1,ex); 
          }

//<UserCode_Begin_Bean_JSplitPane1>

//<UserCode_End_Bean_JSplitPane1>

          try
          {
            JComboBox1.setModel(DefaultComboBoxModel1);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JComboBox1,ex); 
          }

//<UserCode_Begin_Bean_JComboBox1>

//<UserCode_End_Bean_JComboBox1>

          try
          {
            JLabel1.setVerticalAlignment(1);
            JLabel1.setText(resourceBundle.getString("                    Custom View Scope Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JButton5.setFont(new Font("Dialog",0,12));
            JButton5.setEnabled(false);
            JButton5.setMinimumSize(new Dimension(171,27));
            JButton5.setPreferredSize(new Dimension(250,27));
            JButton5.setText(resourceBundle.getString("Set Scope Properties"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton5,ex); 
          }

//<UserCode_Begin_Bean_JButton5>
JButton5.setMnemonic('P');
//<UserCode_End_Bean_JButton5>

          try
          {
            JButton6.setFont(new Font("Dialog",0,12));
            JButton6.setEnabled(false);
            JButton6.setMaximumSize(new Dimension(55,27));
            JButton6.setText(resourceBundle.getString("Add AuthorizedScope"));
            JButton6.setMinimumSize(new Dimension(171,27));
            JButton6.setPreferredSize(new Dimension(250,27));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton6,ex); 
          }

//<UserCode_Begin_Bean_JButton6>
JButton6.setMnemonic('A');
//<UserCode_End_Bean_JButton6>

          try
          {
            SortTable3.setModel(SortTableModel4);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+SortTable3,ex); 
          }

//<UserCode_Begin_Bean_SortTable3>

//<UserCode_End_Bean_SortTable3>

          try
          {
            JButton21.setFont(new Font("Dialog",0,12));
            JButton21.setEnabled(false);
            JButton21.setText(resourceBundle.getString("Assign AuthorizedScope"));
            JButton21.setMinimumSize(new Dimension(171,27));
            JButton21.setPreferredSize(new Dimension(250,27));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton21,ex); 
          }

//<UserCode_Begin_Bean_JButton21>
JButton2.setMnemonic('S');

//<UserCode_End_Bean_JButton21>
		JSplitPane1.setPreferredSize(new Dimension(JSplitPane1.getPreferredSize().width+0,JSplitPane1.getPreferredSize().height+105));
		JTabbedPane1.setPreferredSize(new Dimension(JTabbedPane1.getPreferredSize().width+0,JTabbedPane1.getPreferredSize().height+1));

  
          //<End_setUpProperties>
 	
	JScrollPane211.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Members for :")+user));
	JScrollPane2111.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Permissions For Group :")+user)); 
	SortTable11.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SortTable11.setDefaultEditor(Object.class,null);
	// Fix to make table cell uneditable on tabbed pane present on security administration -> Group tree.
	SortTable1.setDefaultEditor(Object.class,null);
	SortTable3.setDefaultEditor(Object.class,null);
	// End of Fix table cell uneditable.
	JTabbedPane1.setTitleAt(0,resourceBundle.getString("Members"));
	JTabbedPane1.setTitleAt(1,resourceBundle.getString("Permitted Operations for Group"));	
	JTabbedPane1.setTitleAt(2,resourceBundle.getString("Custom View Scope for Group"));
	//JLabel1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("addgroup.png"));
  } 
  public void start()
  {
  //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  } 
  public void stop()
  {
  //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JTabbedPane1= new javax.swing.JTabbedPane();
        JPanel3111= new javax.swing.JPanel();
        JScrollPane211= new javax.swing.JScrollPane();
        SortTable1= new com.adventnet.beans.table.SortTable();
        JButton4= new javax.swing.JButton();
        JPanel1= new javax.swing.JPanel();
        JScrollPane2111= new javax.swing.JScrollPane();
        SortTable11= new com.adventnet.beans.table.SortTable();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JPanel2= new javax.swing.JPanel();
        JSplitPane1= new javax.swing.JSplitPane();
        JPanel7= new javax.swing.JPanel();
        JComboBox1= new javax.swing.JComboBox();
        JLabel1= new javax.swing.JLabel();
        JPanel8= new javax.swing.JPanel();
        JButton5= new javax.swing.JButton();
        JButton6= new javax.swing.JButton();
        JScrollPane2= new javax.swing.JScrollPane();
        SortTable3= new com.adventnet.beans.table.SortTable();
        JButton21= new javax.swing.JButton();
        AllUsers= new javax.swing.DefaultListModel();
        UserModel= new javax.swing.DefaultListModel();
        SortTableModel1= new com.adventnet.beans.table.SortTableModel();
        SortTableModel2= new com.adventnet.beans.table.SortTableModel();
        SortTableModel4= new com.adventnet.beans.table.SortTableModel();
        DefaultComboBoxModel1= new javax.swing.DefaultComboBoxModel();
   SortTable3.addMouseListener(popupMenuMouseListener);

  
        //<End_initVariables>
	//AuthMain.model.registerWithModel(this);  
	} 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JTabbedPane1,BorderLayout.CENTER);
JTabbedPane1.addTab(resourceBundle.getString("JPanel31"),null,JPanel3111,null);
JPanel3111.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,2,0.05,0.1,cons.NORTH,cons.BOTH,inset,0,0);
JPanel3111.add(JScrollPane211,cons);
JScrollPane211.getViewport().add(SortTable1);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel3111.add(JButton4,cons);
JTabbedPane1.addTab(resourceBundle.getString("JPanel1"),null,JPanel1,null);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,2,2,0.05,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JScrollPane2111,cons);
JScrollPane2111.getViewport().add(SortTable11);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel1.add(JButton1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.01,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel1.add(JButton2,cons);
JTabbedPane1.addTab(resourceBundle.getString("JPanel2"),null,JPanel2,null);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JSplitPane1,BorderLayout.CENTER);
JSplitPane1.setTopComponent(JPanel7);
JSplitPane1.setDividerLocation(90);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(5,25,5,5);
setConstraints(1,0,1,1,0.01,0.01,cons.NORTH,cons.HORIZONTAL,inset,0,0);
JPanel7.add(JComboBox1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.01,0.01,cons.CENTER,cons.BOTH,inset,0,0);
JPanel7.add(JLabel1,cons);
JSplitPane1.setBottomComponent(JPanel8);
JPanel8.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(3,1,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel8.add(JButton5,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.01,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel8.add(JButton6,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,4,1,0.01,0.01,cons.NORTH,cons.BOTH,inset,0,0);
JPanel8.add(JScrollPane2,cons);
JScrollPane2.getViewport().add(SortTable3);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel8.add(JButton21,cons);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      JButton5_JButton5_conn1 JButton5_JButton5_conn11 =  new JButton5_JButton5_conn1();
      JButton5.addActionListener(JButton5_JButton5_conn11);
      SortTable11_JButton2_conn SortTable11_JButton2_conn1 =  new SortTable11_JButton2_conn();
      SortTable11.addMouseListener(SortTable11_JButton2_conn1);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      SortTable3_SortTable3_conn1 SortTable3_SortTable3_conn11 =  new SortTable3_SortTable3_conn1();
      SortTable3.addMouseListener(SortTable3_SortTable3_conn11);
      JComboBox1_JButton21_conn1 JComboBox1_JButton21_conn11 =  new JComboBox1_JButton21_conn1();
      JComboBox1.addActionListener(JComboBox1_JButton21_conn11);
      JComboBox1_SortTableModel4_conn1 JComboBox1_SortTableModel4_conn11 =  new JComboBox1_SortTableModel4_conn1();
      JComboBox1.addActionListener(JComboBox1_SortTableModel4_conn11);
      JButton6_SortTableModel4_conn1 JButton6_SortTableModel4_conn11 =  new JButton6_SortTableModel4_conn1();
      JButton6.addActionListener(JButton6_SortTableModel4_conn11);
      JButton4_JButton4_conn1 JButton4_JButton4_conn11 =  new JButton4_JButton4_conn1();
      JButton4.addActionListener(JButton4_JButton4_conn11);
      JButton21_JButton21_conn1 JButton21_JButton21_conn11 =  new JButton21_JButton21_conn1();
      JButton21.addActionListener(JButton21_JButton21_conn11);
      SortTable3_JButton5_conn1 SortTable3_JButton5_conn11 =  new SortTable3_JButton5_conn1();
      SortTable3.addMouseListener(SortTable3_JButton5_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
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
	
	//Utility Methods.

	//String usergroup = null;
	Vector allgroups = null;
	Vector oldgroups = null;
	Vector allCustViews = null;
	Vector oldCustViews= null;
	/*
	public void setModel(com.adventnet.security.ui.AbstractSecurityModel model){
		this.model = model;
	}
	*/
	public void setUserData(java.util.Vector groups,java.util.Vector allGroups,String name){
		allgroups = new Vector();
		allgroups = allGroups;
		oldgroups = new Vector();
		user = name;
		oldgroups = groups;
	JScrollPane211.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Members for :")+user));	
	JScrollPane2111.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Operations For Group :")+user)); 
	
		UserModel.removeAllElements();
		int size = groups.size();
		Object[][] obj = new Object[size][2];
		
		for(int i=0;i<size;i++){
			
			obj[i][0]=groups.elementAt(i).toString();
			obj[i][1]="";
		}
		String[] columnNames = {resourceBundle.getString("User Name")};
		SortTableModel1.setDataVector(obj,columnNames);
		/*
		int size = groups.size();
		for(int i=0;i<size;i++){
			UserModel.addElement(groups.elementAt(i));
		}
		
		AllUsers.removeAllElements();
		int siz = allGroups.size();
		for(int j=0;j<siz;j++){
			AllUsers.addElement(allGroups.elementAt(j));
		}
		*/
		JButton2.setEnabled(false);
	}
		public void setOperations(java.util.Hashtable oper){

		int size = oper.size();
		int count = 0;
		Object[][] obj1 = new Object[size][3];
		for(java.util.Enumeration e = oper.keys();e.hasMoreElements();){
			String op = e.nextElement().toString();

			 //----------- Added By shajahan. -----------
			 String I18str = resourceBundle.getString("security.operation.tree." + op);
                        //resourceToOper.setProperty(resourceBundle.getString("security.operation.tree." + op ), op);
			//obj1[count][0] = resourceBundle.getString("security.operation.tree." + op);
			
			 if(I18str.equals("security.operation.tree." + op))
			 {
				 obj1[count][0] = op;
				 resourceToOper.setProperty(op,op);
			 }
			 else
			 {
				 obj1[count][0] = I18str;
				 resourceToOper.setProperty(I18str,op);
			 }
			 // ---------- End of shajahan. --------------
			
			if(oper.get(op).toString().equals("0"))
				obj1[count][1] = "excluded";
			else{
				obj1[count][1] = "included";

			}
			obj1[count][2] = "";
			count++;
		}
	String[] columnNames1 = {resourceBundle.getString("Operation Name"),resourceBundle.getString("Type")};
          SortTableModel2.setDataVector(obj1,columnNames1);
	
	 
	}


	public void setCustomView(Vector customViews,Vector views)
	{
		//JButton21.setEnabled(false);
		JButton5.setEnabled(false);
		allCustViews = views;
		oldCustViews = customViews;
	//System.out.println("Yes I am inside setCustomView ===>"+customViews+" : "+views+" for user " + user);

		JPanel7.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("CustomViewScope for  the group : ")+user));
		//SortTable2.clearSelection();
		//JComboBox1.removeAllItems();
		if(JComboBox1.getItemCount() == 0)
		{
		int size = customViews.size();
		//int count = 0;
		//Object[][] obj1 = new Object[size][2];
		//for(int i =0;i<size;i++){
		//	String custView = customViews.elementAt(i).toString();
		//	obj1[count][0] = custView;
		//	count++;
		//}
		//String[] columnNames1 = {resourceBundle.getString("Custom View Scope")};
            		//SortTableModel3.setDataVector(obj1,columnNames1);
		String custView = "";
		for(int i=0;i<size;i++)
		{
			custView = customViews.elementAt(i).toString();
			JComboBox1.addItem(custView);
		}
		}

		populateCVSTable(user);
	}

	void populateCVSTable(String groupName)
	{
		//System.out.println("I am inside populateCVSTable ===  "+ groupName );	
		JScrollPane2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("AuthorizedScopes for CV : ")+JComboBox1.getSelectedItem().toString()));
		String[] columnNames2 = {resourceBundle.getString("Authorized Scope")};
		
		Vector viewsForCVS = (Vector)AuthMain.model.getAuthScope(groupName).get(JComboBox1.getSelectedItem());
		//System.out.println("view FOR CVS ==> " + viewsForCVS);
		if(viewsForCVS == null)
		{
			viewsForCVS = new Vector();
		}
		int size = viewsForCVS.size();	
		Object  [][] cvsTable =  new Object[size][2]; // 2 represents no of columns.
		for(int i=0; i< size;i++)
		{
		 cvsTable[i][0] =viewsForCVS.elementAt(i);
		 cvsTable[i][1] = "";
		}
		SortTableModel4.setDataVector(cvsTable,columnNames2);
		JButton6.setEnabled(true);
	}



	private void setAuthData(String customView)
	{
		
		SortTable3.clearSelection();
		JButton5.setEnabled(false);
		//SortTableModel5.removeAllElements();
		//Temporarily set some data.
		Hashtable allViews = AuthMain.model.getAuthScope(user);
		//Views.addElement("authView");
		//Views.addElement("configView");
		//Here some API to get the authViews for the given custom view 
		//has to be defined.
		Vector Views = (Vector)allViews.get(customView);
		if(Views == null)
		{
			Views = new Vector();	
		}	

		int size = Views.size();

		//int count = 0;
		Object[][] obj1 = new Object[size][2];
		String view="";
		for(int i =0;i<size;i++){
			 view = (String)Views.elementAt(i);
			 if  (view==null) continue;	
			obj1[i][0] = view;
			obj1[i][1] = "";
		}
		String[] columnNames1 = {resourceBundle.getString("Authorized Scope")};
            		SortTableModel4.setDataVector(obj1,columnNames1);
		forTheViewPropVec = Views;
		//System.out.println("This is for the AuthScope");
	}
	private Vector myallgroups;
	private Vector myoldgroups;
	private String myuser;
	//Method for setting the data.
	public void setCustomData(Vector authVec,Vector allAuthView,String name)	
	{
		//myoldgroups = (Vector)AuthMain.model.getAuthScope().get(name);
		myallgroups = new Vector();
		if(allAuthView != null)
		{
			myallgroups = allAuthView;
		}
		
		myuser = name;
		//System.out.println("for the custom data"+myallgroups+"         "+myoldgroups);
	}

	public void setCustomData(Vector allAuthView)	
	{
		if(allAuthView != null)
		{
			myallgroups = allAuthView;
		}
		else
		{
		myallgroups = new Vector();		
		}
	}



	public void setSecurityModel(com.adventnet.security.ui.AbstractSecurityModel model){
		this.model = model;
	}
	public void setBuilderUiImpl(com.adventnet.nms.util.CommonBuilderUIImpl uiImpl){
	}
	public void fireDataChanged(){
		//System.out.println("fire dajrpwjr");
		setUserData(model.getUsersForGroup(user),allgroups,user);			
		//AuthMain.model = model;	
	}
	public void showError(String err){
		Utilities.errorMessage(resourceBundle.getString(err));
	}
	public void close()
	{
		//dispose();
	}	
 


//<Begin__class_JButton4_JButton4_conn1>

 class JButton4_JButton4_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton4_JButton4_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {

   prop = new AssignProp(AuthMain.main,applet);
AssignProp.what = "Users";  
  prop.setVisible(true);
  prop.setData(allgroups,oldgroups,user); 
 
  
  
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
 
  treec = new TreeConfig(AuthMain.main, applet); 
   treec.init();  
   //treec.model = AuthMain.model;  

   
 
  
   Vector groupview = AuthMain.model.getViewsForGroup(user);
   groupview.add("default "+user+" View" ); 
   //System.out.println(groupview);   
   treec.setOperationTree();     
   treec.setName(user,"Groups ");   
   treec.prepareForRendering(groupview);  
  
   treec.setVisible(true);   
     
  //TreeConfig tc = new TreeConfig();
  //tc.setVisible(true);
     }
  
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

 


//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         JButton2.setEnabled(true);
         int num =(int)SortTable11.getSelectedRow();
         //System.out.println(num); 
         if(num ==-1){
             Utilities.errorMessage(resourceBundle.getString("Please Select the Operation on which the Scope should be set"));
             return;
         }
         //viewVec = SortTable11.getValueAt(num,0).toString();
         String tempOper = SortTable11.getValueAt(num,0).toString();
         viewVec = resourceToOper.getProperty(tempOper);
         if((SortTable11.getValueAt(num,1).toString()).equals("excluded")){
             Utilities.errorMessage(resourceBundle.getString("Cannot set the Scope for the excluded operation"));
             return;
         }
         //   System.out.println(viewVec);
         operViewProp = new OperationViewProperty(AuthMain.main,applet);
         Properties prop = AuthMain.model.getViewProperties(user+" "+viewVec+" "+"Scope");  
         operViewProp.state = false;
         JButton2.setEnabled(false);
         operViewProp.setVisible(true);

         //System.out.println(" CLIEBT PROPER "+prop);  
         operViewProp.setViews(viewVec,user,"");  
         operViewProp.setProperties("",prop);
         SortTable11.clearSelection();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>




  public TabbedPane2()
  {
    //<Begin_TabbedPane2>
    this.init();
  
    //<End_TabbedPane2>
  }

  public TabbedPane2(java.applet.Applet applet)
  {
    //<Begin_TabbedPane2_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_TabbedPane2_java.applet.Applet>
  }

 


//<Begin__class_SortTable11_JButton2_conn>

 class SortTable11_JButton2_conn extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_SortTable11_JButton2_conn>



     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
 int num =(int)SortTable11.getSelectedRow();
 if(SwingUtilities.isLeftMouseButton(arg0)){
   if(num == -1)
   {
	    return;
   }	   
   if(!((SortTable11.getValueAt(num,1).toString()).equals("excluded"))){
	   JButton2.setEnabled(true);
   }
   else if(((SortTable11.getValueAt(num,1).toString()).equals("excluded")))
   {
	   JButton2.setEnabled(false);
   }
  }
     }
//<UserCode_End_Connection_SortTable11_JButton2_conn>
 }//<End__class_SortTable11_JButton2_conn>

 


//<Begin__class_JButton5_JButton5_conn1>

 class JButton5_JButton5_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton5_JButton5_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         int num =(int)SortTable3.getSelectedRow();
         if(!(num ==-1))
         {
             String custView =  (SortTable3.getValueAt(num,0)).toString();
             viewProp = new ViewProperty(AuthMain.main,applet);
             Properties prop = AuthMain.model.getViewProperties(custView);
             viewProp.state = true;   
             JButton5.setEnabled(false);
             viewProp.setVisible(true);
			 if(prop.getProperty("stringseverity")!=null)
			 {
				 String propValue = (String)prop.remove("stringseverity");//No I18n
				 prop.setProperty("severity",propValue);
			 }
			 else if(prop.getProperty("stringstatus")!=null)
			 {
				 String propValue = (String)prop.remove("stringstatus");//No I18n
				 prop.setProperty("status",propValue);
			 }
             populateComboBox(viewProp);
             viewProp.setViews(user,custView,"");  
             viewProp.setProperties("",prop);
             SortTable3.clearSelection();
         }  
     }
//<UserCode_End_Connection_JButton5_JButton5_conn1>
 }//<End__class_JButton5_JButton5_conn1>
//<Begin__class_SortTable3_SortTable3_conn1>

 class SortTable3_SortTable3_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_SortTable3_SortTable3_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
 int num =(int)SortTable3.getSelectedRow();
       if(SwingUtilities.isRightMouseButton(arg0))
      {
              if(!(num ==-1))
            {
                toDelete =  (SortTable3.getValueAt(num,0)).toString(); 
               }
           else if ( num > 0 )
          {
      JButton5.setEnabled(true);
          }    
      }  
     }
//<UserCode_End_Connection_SortTable3_SortTable3_conn1>
 }//<End__class_SortTable3_SortTable3_conn1>
//<Begin__class_JComboBox1_JButton21_conn1>

 class JComboBox1_JButton21_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JComboBox1_JButton21_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 int num =(int)JComboBox1.getSelectedIndex();
       if(!(num ==-1))
      { 
                JButton21.setEnabled(true); 
             }
     }
//<UserCode_End_Connection_JComboBox1_JButton21_conn1>
 }//<End__class_JComboBox1_JButton21_conn1>
//<Begin__class_JComboBox1_SortTableModel4_conn1>

 class JComboBox1_SortTableModel4_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JComboBox1_SortTableModel4_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 int num =(int)JComboBox1.getSelectedIndex();
  if(!(num ==-1))
   {
               String custView = (JComboBox1.getSelectedItem()).toString();
           JScrollPane2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("AuthorizedScopes for CV : "+custView)));
          JButton6.setEnabled(true); 
          forTheViewProp = custView;    
         setAuthData(custView);
              }
     }
//<UserCode_End_Connection_JComboBox1_SortTableModel4_conn1>
 }//<End__class_JComboBox1_SortTableModel4_conn1>
//<Begin__class_JButton6_SortTableModel4_conn1>

 class JButton6_SortTableModel4_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton6_SortTableModel4_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         if (user == null)
         {
             user = (String)JComboBox1.getSelectedItem();
         }
         Hashtable allViews = AuthMain.model.getAuthScope(user); 
         Vector viewsForCustom = (Vector)allViews.get(forTheViewProp);
         if(viewsForCustom == null)
         {
             viewsForCustom = new Vector();
         }
         // The following if check is added to get a only a single window on multiple clicks for Add AuthorizedScope
         if(viewProp == null || !viewProp.isVisible() )
         {           
             viewProp = new ViewProperty(AuthMain.main,applet);
             viewProp.state = true;  
             viewProp.setVisible(true);
             populateComboBox(viewProp);
             viewProp.setAuthViews(forTheViewProp,viewsForCustom);
             viewProp.setViews("","",user);
             viewProp.enableText();
           
         }
     }
//<UserCode_End_Connection_JButton6_SortTableModel4_conn1>
 }//<End__class_JButton6_SortTableModel4_conn1>
//<Begin__class_SortTable3_JButton5_conn1>

 class SortTable3_JButton5_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_SortTable3_JButton5_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
 int num =(int)SortTable3.getSelectedRow();

      if(!(num ==-1))
     {
           if(SwingUtilities.isLeftMouseButton(arg0))
          { 
             JButton5.setEnabled(true); 
          }
       }
     }
//<UserCode_End_Connection_SortTable3_JButton5_conn1>
 }//<End__class_SortTable3_JButton5_conn1>
//<Begin__class_JButton21_JButton21_conn1>

 class JButton21_JButton21_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton21_JButton21_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  Vector viewsVec = AuthMain.model.getViewsForGroup(user);
        String myView =  (JComboBox1.getSelectedItem()).toString(); 
     
          prop = new AssignProp(AuthMain.main,applet);
          AssignProp.what = resourceBundle.getString("AuthorizedScopes");
          prop.setVisible(true);   
       
           myoldgroups = (Vector)AuthMain.model.getAuthScope(user).get(myView) ; 
           if(myoldgroups == null)
           {
               myoldgroups = new Vector();
           }
          for(int i=0; i<myoldgroups.size(); i++)
          {
              String viewName = (String)myoldgroups.elementAt(i);
               if(viewsVec.contains(viewName))
               {
               boolean br = viewsVec.removeElement(viewName);   
               }
          }
  
          prop.setGroupDataForCustom(user, viewsVec);
    setCustomData(AuthMain.model.getAllAuthScopes(myView));
          prop.setData(myallgroups,myoldgroups,myView);     
     }
//<UserCode_End_Connection_JButton21_JButton21_conn1>
 }//<End__class_JButton21_JButton21_conn1>

 
  public JPopupMenu getDelete()

  {
   //<Begin_getDelete> 
   if(Delete== null)
   {
      Delete = new JPopupMenu();
      Delete_AuthorizedViewMenuItem = new JMenuItem(resourceBundle.getString("Delete AuthorizedView"));
      Delete_AuthorizedViewMenuItem.setActionCommand(resourceBundle.getString("Delete AuthorizedView"));
      Delete_AuthorizedViewMenuItem.addActionListener(menuToolBarAction);
      Delete.add(Delete_AuthorizedViewMenuItem);
   }
   return Delete;

  
   //<End_getDelete>
  } 

//<Begin_class_PopupMenuMouseListener>
public class PopupMenuMouseListener extends MouseAdapter{ 
  
public void mouseClicked(MouseEvent evt)

  { 
      if(evt.isMetaDown())
      {
         if(evt.getSource()==SortTable3)
         {
         ((JPopupMenu)getDelete()).show(evt.getComponent(),evt.getX(),evt.getY());
         }
      }

  } 
}
//<End_class_PopupMenuMouseListener> 
//<Begin_class_MenuToolBarAction>
public class MenuToolBarAction extends AbstractAction{ 
  
public void actionPerformed(ActionEvent evt)

  { 
      if(evt.getActionCommand().equals(resourceBundle.getString("Delete AuthorizedView")))
      {
         
//<UserCode_Begin_menuItem_Delete_AuthorizedViewMenuItem>
//Please add the action code for menu Here Delete AuthorizedView
int rowIndex = SortTable3.getSelectedRow();
if(rowIndex == -1)
{
        Utilities.errorMessage(resourceBundle.getString("Please select an Authorized View to be deleted."));
        return; 
}

 if( JOptionPane.showConfirmDialog(null,
                                          resourceBundle.getString("Deleting Authorized Scope will remove its association with all the groups.")+"\n"+resourceBundle.getString("For disassociating this authorized scope from a particular group")+"\n"+resourceBundle.getString("use 'Assign AuthorizedScope' option.")+"\n"+"\n"+resourceBundle.getString("Are you sure you want to do this?"),
                                           resourceBundle.getString("Warning!"),
                                           JOptionPane.YES_NO_OPTION,
                                          JOptionPane.WARNING_MESSAGE,
                                           null) == JOptionPane.YES_OPTION)
   {  
   String theAuthView = null;
  String theCustomView =  (String)JComboBox1.getSelectedItem();
  if(rowIndex != -1)
  {
   theAuthView = (String)SortTable3.getValueAt(rowIndex,0);
   //SortTableModel4.removeRow(rowIndex);
  }
  if(theAuthView != null)
  {
   Vector authViewsVec = new Vector();
   authViewsVec.addElement(theAuthView);
   AuthMain.main.disableButtons();
   AuthMain.model.modViewProp(user,true,authViewsVec,theCustomView);
  }
   }
//<UserCode_End_menuItem_Delete_AuthorizedViewMenuItem>

      
}

  } 
}
//<End_class_MenuToolBarAction>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  } 
  public void setUpMenus()
  {
  //<Begin_setUpMenus> 

  
  //<End_setUpMenus>
  }
  
    public void populateComboBox(ViewProperty viewProp)
    {
        JComboBox nameComboBox = viewProp.getNameComboBox();

        Properties propNames = AuthMain.model.getPropertyNamesForCustomViewScope();
    
        Vector names = (Vector) propNames.get(forTheViewProp);
        
        for (int i = 0; i < names.size(); i++)
        {
            nameComboBox.addItem(names.elementAt(i));
        }
    }
}
	








































