//$Id: TabbedPane1.java,v 1.5 2008/11/15 07:04:08 venkatramanan Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$9
//<End_Version>
package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.util.Hashtable;



public class TabbedPane1 extends JPanel implements com.adventnet.security.ui.SecurityCommonInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JTabbedPane JTabbedPane1 = null;
	javax.swing.JPanel JPanel311 = null;
	javax.swing.JScrollPane JScrollPane21 = null;
	com.adventnet.beans.table.SortTable SortTable1 = null;
	javax.swing.JButton JButton4 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel JLabel3 = null;
	com.adventnet.beans.text.NumericTextField NumericTextField1 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel4 = null;
	com.adventnet.beans.text.NumericTextField NumericTextField2 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel8 = null;
	javax.swing.JTextField JTextField1 = null;
	javax.swing.JPanel JPanel41 = null;
	javax.swing.JLabel JLabel31 = null;
	javax.swing.JTextField FullNameTextField1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel111 = null;
	javax.swing.JButton JButton31 = null;
	javax.swing.JScrollPane JScrollPane2111 = null;
	com.adventnet.beans.table.SortTable SortTable111 = null;
	javax.swing.DefaultListModel GroupModel = null;
	javax.swing.DefaultListModel AllGroups = null;
	com.adventnet.beans.table.SortTableModel SortTableModel1 = null;
	com.adventnet.beans.table.SortTableModel SortTableModel2 = null;
	com.adventnet.beans.table.SortTableModel SortTableModel3 = null;
	javax.swing.JPanel JPanel11 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JScrollPane JScrollPane211 = null;
	com.adventnet.beans.table.SortTable SortTable11 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private String group = "";
	//String[] columnNames = {resourceBundle.getString("Group Name"),resourceBundle.getString("Description")};
  	
	com.adventnet.security.ui.AbstractSecurityModel model = null;
	
	//Screens from this pane.
	protected com.adventnet.security.ui.TreeConfig treec = null;
	protected com.adventnet.security.ui.AssignProp prop = null;  
	protected com.adventnet.security.ui.AgePanel panel = null;
	//End for screens.
	
	String status = null;
  


 

  

    public void init()
  {
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+676,getPreferredSize().height+443)); 
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
	String[] columnNames = {resourceBundle.getString("Group Name"),resourceBundle.getString("Description")};
	//	columnNames = {resourceBundle.getString("Group Name"),resourceBundle.getString("Description")};
	
	
	SortTableModel1.setDataVector(new Object[0][0],columnNames);
	
	String[] columnNames1 = {resourceBundle.getString("Operation Name"),resourceBundle.getString("Type"),resourceBundle.getString("Description")};
	SortTableModel2.setDataVector(new Object[0][0],columnNames1);
	//AuthMain.model.registerWithModel(this);
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
            JTabbedPane1.setTabPlacement(1);
            JTabbedPane1.setSelectedIndex(0);
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
            //JButton4.setText(resourceBundle.getString("Setting Groups"));
	    JButton4.setText(resourceBundle.getString("javaui.securityadmin.button.settinggroups"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton4,ex); 
          }

//<UserCode_Begin_Bean_JButton4>
JButton4.setMnemonic('S');
//<UserCode_End_Bean_JButton4>

          try
          {
            JPanel4.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Account expiry for :")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JLabel3.setHorizontalAlignment(2);
            JLabel3.setFont(new Font("SansSerif",0,12));
            JLabel3.setHorizontalTextPosition(4);
            JLabel3.setText(resourceBundle.getString("This user account expires in"));
            JLabel3.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
              showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex);//No I18N
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            NumericTextField1.setEnabled(false);
            NumericTextField1.setEditable(false);
            NumericTextField1.setMinValue(-92233720);
          }
          catch(Exception ex)
          {
              showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NumericTextField1,ex);//NO I18N
          }

//<UserCode_Begin_Bean_NumericTextField1>

//<UserCode_End_Bean_NumericTextField1>

          try
          {
            JLabel5.setHorizontalAlignment(2);
            JLabel5.setFont(new Font("SansSerif",0,12));
            JLabel5.setHorizontalTextPosition(4);
            JLabel5.setText(resourceBundle.getString("Day(s)."));
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
            JPanel5.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Password expiry for :")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            JLabel4.setHorizontalAlignment(2);
            JLabel4.setFont(new Font("SansSerif",0,12));
            JLabel4.setHorizontalTextPosition(4);
            JLabel4.setText(resourceBundle.getString("The password expires in        "));
            JLabel4.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            NumericTextField2.setEnabled(false);
            NumericTextField2.setEditable(false);
            NumericTextField2.setMinValue(-92233720);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NumericTextField2,ex); 
          }

//<UserCode_Begin_Bean_NumericTextField2>

//<UserCode_End_Bean_NumericTextField2>

          try
          {
            JLabel6.setHorizontalAlignment(2);
            JLabel6.setFont(new Font("SansSerif",0,12));
            JLabel6.setHorizontalTextPosition(4);
            JLabel6.setText(resourceBundle.getString("Days(s)."));
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
            JPanel6.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Status for the User :")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            JLabel1.setFont(new Font("Dialog",0,12));
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setText(resourceBundle.getString("Current status of the user     "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel8.setText(resourceBundle.getString("             "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel8,ex); 
          }

//<UserCode_Begin_Bean_JLabel8>

//<UserCode_End_Bean_JLabel8>

          try
          {
            JTextField1.setEditable(false);
            JTextField1.setEnabled(false);
            JTextField1.setPreferredSize(new Dimension(40,21));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField1,ex); 
          }

//<UserCode_Begin_Bean_JTextField1>

//<UserCode_End_Bean_JTextField1>

          try
          {
              JPanel41.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Full Name of the User :")));//NO I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel41,ex);//NO I18N
          }

//<UserCode_Begin_Bean_JPanel41>

//<UserCode_End_Bean_JPanel41>

          try
          {
            JLabel31.setHorizontalAlignment(2);
            JLabel31.setFont(new Font("SansSerif",0,12));//NO I18N
            JLabel31.setHorizontalTextPosition(4);
            JLabel31.setForeground(new Color(-16764109));
            JLabel31.setText(resourceBundle.getString("Full name of the user"));//NO I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel31,ex);//NO I18N
          }

//<UserCode_Begin_Bean_JLabel31>

//<UserCode_End_Bean_JLabel31>

          try
          {
            FullNameTextField1.setEnabled(false);
            FullNameTextField1.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+FullNameTextField1,ex);//NO I18N
          }

//<UserCode_Begin_Bean_FullNameTextField1>

//<UserCode_End_Bean_FullNameTextField1>

          try
          {
            JLabel2.setHorizontalAlignment(2);
            JLabel2.setFont(new Font("SansSerif",0,12));
            JLabel2.setHorizontalTextPosition(4);
            JLabel2.setText(resourceBundle.getString("<html><body><font face=\"sansserif,SansSerif\"><font size=3>   Please enter the number of days in which  <br><font face=\"sansserif,SansSerif\">    the user and/or the password expires...</font><br><br><font face=\"san sserif,SansSerif\">   A value of zero indicates no expiry. </font></body></html>"));
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
            JButton1.setFont(new Font("Dialog",0,12));
            JButton1.setText(resourceBundle.getString("Setting Profile"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>
JButton1.setMnemonic('S');
//<UserCode_End_Bean_JButton1>

          try
          {
            JPanel111.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel111,ex); 
          }

//<UserCode_Begin_Bean_JPanel111>

//<UserCode_End_Bean_JPanel111>

          try
          {
            JButton31.setFont(new Font("SansSerif",0,12));
            JButton31.setHorizontalTextPosition(4);
            JButton31.setText(resourceBundle.getString("Set Permissions"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton31,ex); 
          }

//<UserCode_Begin_Bean_JButton31>
JButton31.setMnemonic('S');
//<UserCode_End_Bean_JButton31>

          try
          {
            SortTable111.setModel(SortTableModel2);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+SortTable111,ex); 
          }

//<UserCode_Begin_Bean_SortTable111>

//<UserCode_End_Bean_SortTable111>

          try
          {
            JPanel11.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("")));
            JPanel11.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel11,ex); 
          }

//<UserCode_Begin_Bean_JPanel11>

//<UserCode_End_Bean_JPanel11>

          try
          {
            JButton3.setFont(new Font("SansSerif",0,12));
            JButton3.setHorizontalTextPosition(4);
            JButton3.setText(resourceBundle.getString("Clear Trails"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>
JButton3.setMnemonic('C');
//<UserCode_End_Bean_JButton3>

          try
          {
            SortTable11.setModel(SortTableModel3);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+SortTable11,ex); 
          }

//<UserCode_Begin_Bean_SortTable11>

//<UserCode_End_Bean_SortTable11>
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+629,JPanel3.getPreferredSize().height+0));
		JTabbedPane1.setPreferredSize(new Dimension(JTabbedPane1.getPreferredSize().width+0,JTabbedPane1.getPreferredSize().height+1));

  
          //<End_setUpProperties>
	//JPanel6.setBorder(new javax.swing.border.TitledBorder("Description of Operations"));
	 // Fix to make table cell uneditable on tabbed pane present on security administration -> Users tree.
	 SortTable1.setDefaultEditor(Object.class,null);
	 SortTable111.setDefaultEditor(Object.class,null);
	 SortTable11.setDefaultEditor(Object.class,null);
	 // End of Fix    
            
	JScrollPane21.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Groups for : ")+group));
	JScrollPane2111.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Permissions For User :")+group));
	JTabbedPane1.setTitleAt(1,resourceBundle.getString("User Profile"));
	

	JTabbedPane1.setTitleAt(0,resourceBundle.getString("Member Of"));
	JTabbedPane1.setTitleAt(2,resourceBundle.getString("Permitted Operations for User"));			
	//JTabbedPane1.add(com.adventnet.security.ui.AgeingPanel);
	//JTabbedPane1.setTitleAt(3,resourceBundle.getString("Audit Trails for User"));
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
        JPanel311= new javax.swing.JPanel();
        JScrollPane21= new javax.swing.JScrollPane();
        SortTable1= new com.adventnet.beans.table.SortTable();
        JButton4= new javax.swing.JButton();
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        NumericTextField1= new com.adventnet.beans.text.NumericTextField();
        JLabel5= new javax.swing.JLabel();
        JPanel5= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        NumericTextField2= new com.adventnet.beans.text.NumericTextField();
        JLabel6= new javax.swing.JLabel();
        JPanel6= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel8= new javax.swing.JLabel();
        JTextField1= new javax.swing.JTextField();
        JPanel41= new javax.swing.JPanel();
        JLabel31= new javax.swing.JLabel();
        FullNameTextField1= new javax.swing.JTextField();
        JPanel3= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JButton1= new javax.swing.JButton();
        JPanel111= new javax.swing.JPanel();
        JButton31= new javax.swing.JButton();
        JScrollPane2111= new javax.swing.JScrollPane();
        SortTable111= new com.adventnet.beans.table.SortTable();
        GroupModel= new javax.swing.DefaultListModel();
        AllGroups= new javax.swing.DefaultListModel();
        SortTableModel1= new com.adventnet.beans.table.SortTableModel();
        SortTableModel2= new com.adventnet.beans.table.SortTableModel();
        SortTableModel3= new com.adventnet.beans.table.SortTableModel();
        JPanel11= new javax.swing.JPanel();
        JButton3= new javax.swing.JButton();
        JScrollPane211= new javax.swing.JScrollPane();
        SortTable11= new com.adventnet.beans.table.SortTable();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JTabbedPane1,BorderLayout.CENTER);
JTabbedPane1.addTab(resourceBundle.getString("JPanel31"),null,JPanel311,null);
JPanel311.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,2,0.05,0.1,cons.NORTH,cons.BOTH,inset,0,0);
JPanel311.add(JScrollPane21,cons);
JScrollPane21.getViewport().add(SortTable1);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel311.add(JButton4,cons);
JTabbedPane1.addTab(resourceBundle.getString("JPanel1"),null,JPanel1,null);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,5,5,5);
setConstraints(0,2,1,1,0.01,0.01,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(NumericTextField1,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JLabel5,cons);
inset = new Insets(0,5,5,5);
setConstraints(0,3,1,1,0.01,0.01,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(NumericTextField2,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,0,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JLabel6,cons);
inset = new Insets(0,5,5,5);
setConstraints(0,1,1,1,0.01,0.01,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(0,5,5,5);
setConstraints(0,0,1,1,0.01,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JLabel1,cons);
inset = new Insets(0,5,5,5);
setConstraints(2,0,1,1,0.01,0.01,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(JLabel8,cons);
inset = new Insets(0,5,5,5);
setConstraints(1,0,1,1,0.0080,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JTextField1,cons);
inset = new Insets(0,5,5,5);
setConstraints(0,0,1,1,0.01,0.01,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel41,cons);
JPanel41.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel41.add(JLabel31,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,2,1,0.249,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel41.add(FullNameTextField1,cons);
JPanel1.add(JPanel3,BorderLayout.SOUTH);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.01,0.01,cons.NORTH,cons.BOTH,inset,0,0);
JPanel3.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.01,0.0,cons.SOUTHEAST,cons.NONE,inset,0,0);
JPanel3.add(JButton1,cons);
JTabbedPane1.addTab(resourceBundle.getString("JPanel111"),null,JPanel111,null);
JPanel111.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel111.add(JButton31,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,2,0.05,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel111.add(JScrollPane2111,cons);
JScrollPane2111.getViewport().add(SortTable111);
JPanel11.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel11.add(JButton3,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,2,0.05,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel11.add(JScrollPane211,cons);
JScrollPane211.getViewport().add(SortTable11);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      JButton31_JButton31_conn JButton31_JButton31_conn1 =  new JButton31_JButton31_conn();
      JButton31.addActionListener(JButton31_JButton31_conn1);
      JButton4_JButton4_conn1 JButton4_JButton4_conn11 =  new JButton4_JButton4_conn1();
      JButton4.addActionListener(JButton4_JButton4_conn11);
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




  

  

	java.util.Vector usergroup = null;
	Vector allgroups = null;
	Vector oldgroups = null;
	//Utility Methods
	/*
	public void setModel(com.adventnet.security.ui.AbstractSecurityModel model){

	this.model = model;
	}*/
 	public void setGroupData(java.util.Vector groups,java.util.Vector allGroups,String name){
		allgroups = new Vector();
		oldgroups = new Vector();
		allgroups = allGroups;
		oldgroups = groups;
		group = name;
		JScrollPane21.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Groups for :")+group));
		//JScrollPane211.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Operations For User :")+group));
		usergroup = new Vector();
		usergroup = groups;
		GroupModel.removeAllElements();
		int size = groups.size();
		Object[][] obj = new Object[size][2];
		
		for(int i=0;i<size;i++){
			
			obj[i][0]=groups.elementAt(i).toString();
			obj[i][1]="";
		}
		String[] columnNames = {resourceBundle.getString("Group Name"),resourceBundle.getString("Description")};
		SortTableModel1.setDataVector(obj,columnNames);
		/*
			AllGroups.removeAllElements();
			int siz = allGroups.size();
			for(int j=0;j<siz;j++){
				AllGroups.addElement(allGroups.elementAt(j));
			}
		*/

	}
		
		
	public void setOperations(java.util.Hashtable oper){
		JScrollPane2111.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Permissions For User :")+group));
		int size = oper.size();
		int count = 0;
		Object[][] obj1 = new Object[size][3];
		for(java.util.Enumeration e = oper.keys();e.hasMoreElements();){
			String op = e.nextElement().toString();
			//----------- Added By Shajahan -----------
			String I18str = resourceBundle.getString("security.operation.tree." + op);
                     	//obj1[count][0] = resourceBundle.getString("security.operation.tree." + op);
			if(I18str.equals("security.operation.tree." + op))
				obj1[count][0] = op;
			else
				obj1[count][0] = I18str;
			// ---------- End Of Shajahan ------------- 	                          	                          	                                  	                                    
			if(oper.get(op).toString().equals("0"))
				obj1[count][1] = "excluded";
			else
				obj1[count][1] ="included" ;
			obj1[count][2] = "";
			count++;
		}
	String[] columnNames1 = {resourceBundle.getString("Operation Name"),resourceBundle.getString("Type"),resourceBundle.getString("Description")};
          SortTableModel2.setDataVector(obj1,columnNames1);
	
	 
	}



 	//This is for the audit details.
	
	public void setAudit(java.util.Vector vec){
	
		JScrollPane211.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("AuditTrails for :")+group));
		int size = vec.size();
		int count = 0;
		Object[][] obj1 = new Object[size][3];
		for(int i =0;i<vec.size();i++){
			java.util.Properties prop = new java.util.Properties();
			prop = (java.util.Properties)vec.elementAt(i);
			//for(java.util.Enumeration e = prop.keys();e.hasMoreElements();){
				obj1[count][0] = resourceBundle.getString((String)prop.get("operation"));
				obj1[count][1] = prop.get("audittime");
				obj1[count][2] = resourceBundle.getString((String)prop.get("status"));
			//}
			count++;
		}
	String[] columnNames1 = {resourceBundle.getString("Operation Name"),resourceBundle.getString("Time"),resourceBundle.getString("Status")};
          SortTableModel3.setDataVector(obj1,columnNames1);
		
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
AssignProp.what = "Groups";
 prop.setVisible(true);
prop.setData(allgroups,oldgroups,group);
prop.setButtonStatus(com.adventnet.nms.util.NmsClientUtil.getUserName());
     }
//<UserCode_End_Connection_JButton4_JButton4_conn1>
 }//<End__class_JButton4_JButton4_conn1>




 




 








  public TabbedPane1()
  {
    //<Begin_TabbedPane1>
    this.init();
  
    //<End_TabbedPane1>
  }

  public TabbedPane1(java.applet.Applet applet)
  {
    //<Begin_TabbedPane1_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_TabbedPane1_java.applet.Applet>
  }

	
 public void setUserAttributes(int userage, int pwdage,String userStatus,String descriptiveName)
	{
		
		JPanel4.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Account expiry for :")+group));
		JPanel5.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Password expiry for :")+group));
		JPanel6.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Status for the User :")+group));
		JPanel41.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Full Name of the User :")+group));//NO I18N
 		{
			NumericTextField1.setText(userage+"");
 		}
 		
 		{
			NumericTextField2.setText(pwdage+"");
 		}

		{
			JTextField1.setText(resourceBundle.getString(userStatus)); 
			JTextField1.setEditable(false);
			status= userStatus;
		}
		FullNameTextField1.setText(descriptiveName);
	}


  public Hashtable getUserAttributes()
	{
		Hashtable hash = new Hashtable();

		if(NumericTextField1.getText().equals("0"))
		{
			//hash.put("uage","null");
		}
		else
		{
			hash.put("uage",NumericTextField1.getText());
		}
		if(NumericTextField2.getText().equals("0"))
		{
		//hash.put("pwage","null");
		}
		else
		{
			hash.put("pwage",NumericTextField2.getText());
		}
		
		return hash;
	}

 
 
	public void setSecurityModel(com.adventnet.security.ui.AbstractSecurityModel model){
		this.model = model;
	 }
	public void setBuilderUiImpl(com.adventnet.nms.util.CommonBuilderUIImpl uiImpl){
	}
	public void fireDataChanged(){
		//System.out.println("fire dajrpwjr");
		setGroupData(model.getAllGroupsForUser(group),allgroups,group);			
		  Hashtable useratb = model.getUserAttributes(group);
		  int userage =0;
		  int pwdage =0; 
		  String km = ""; 
		  if(useratb.get("userexpirytime") != null)
			    userage = new Integer(useratb.get("userexpirytime").toString()).intValue();
		  if(useratb.get("passwdexpirytime") != null)
			   pwdage =  new Integer(useratb.get("passwdexpirytime").toString()).intValue();
			km=useratb.get("status").toString();
		String descName = (String)useratb.get("descriptivename");//NO I18N
		setUserAttributes(userage,pwdage,km,descName);
	}
	public void showError(String err){
		Utilities.errorMessage(resourceBundle.getString(err));
	}
	public void close()
	{
		//dispose();
	}	



 










 


//<Begin__class_JButton3_SortTableModel3_conn>

 class JButton3_SortTableModel3_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_SortTableModel3_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 AuthMain.main.disableButtons(); 
   AuthMain.model.clearAudit(group);
  
     }
//<UserCode_End_Connection_JButton3_SortTableModel3_conn>
 }//<End__class_JButton3_SortTableModel3_conn>
//<Begin__class_JButton31_JButton31_conn>

 class JButton31_JButton31_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton31_JButton31_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
    treec = new TreeConfig(AuthMain.main, applet); 
   treec.init();  
  // treec.model = AuthMain.model;  
  
   Vector temp = new Vector(); 
   Vector groupview = AuthMain.model.getAllGroupsForUser(group);
   int size = groupview.size();
   for(int i=0;i<size;i++){  
 if(!temp.contains("default "+groupview.elementAt(i).toString()+" View")) 
     temp.add("default "+groupview.elementAt(i).toString()+" View" );       
 Vector mtVec = AuthMain.model.getViewsForGroup(groupview.elementAt(i).toString());  
 for(int j=0;j<mtVec.size();j++){  
  if(!temp.contains(mtVec.elementAt(j))){
    temp.addElement(mtVec.elementAt(j));
    temp.add("default "+mtVec.elementAt(j).toString()+" View" );    
  }   
 }   
   }  
temp.add("default "+group+" View" );   
  treec.setOperationTree();     
  treec.setName(group,"Users ");  
  treec.prepareForRendering(temp);  

  treec.setVisible(true);   
     
  //TreeConfig tc = new TreeConfig();
  //tc.setVisible(true);
     }
//<UserCode_End_Connection_JButton31_JButton31_conn>
 }//<End__class_JButton31_JButton31_conn>


//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  panel = new AgePanel(AuthMain.main,applet);
   panel.setVisible(true);     
   panel.setUserProfile(NumericTextField1.getText(),NumericTextField2.getText(),status,group,FullNameTextField1.getText());
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}



