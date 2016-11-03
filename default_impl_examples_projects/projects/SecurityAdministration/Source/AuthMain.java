
//$Id: AuthMain.java,v 1.9.8.1 2013/05/14 08:22:06 wesley Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$3
//<End_Version>
package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.tree.*;
import java.util.*;
import com.adventnet.nms.util.*;

public class AuthMain extends JFrame  implements com.adventnet.security.ui.SecurityCommonInterface,FocusListener,com.adventnet.nms.startclient.NmsFrame 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JSplitPane JSplitPane1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTree JTree1 = null;
	com.adventnet.beans.panels.CardPanel CardPanel1 = null;
	private JMenuBar menuBar=null;
	JMenu FileMenu= null;
	JMenu   NewMenu= null;
	JMenuItem   AddUserMenuItem = null;
	JMenuItem   AddGroupMenuItem = null;
	JMenuItem   AddOperationsMenuItem = null;
	JMenuItem   ExitMenuItem = null;
	JMenu ViewMenu= null;
	JMenuItem   AuditTrailsMenuItem = null;
	JMenu EditMenu= null;
	JMenuItem   RefreshMenuItem = null;
	JMenuItem   Change_PasswordMenuItem = null;
	JMenuItem   DeleteMenuItem = null;
	JMenu HelpMenu= null;
	JMenuItem   Help_ContentsMenuItem = null;
	MenuToolBarAction menuToolBarAction = new MenuToolBarAction();
	//<End_Variable_Declarations>

	protected static com.adventnet.security.ui.AbstractSecurityModel model = null;
	protected static com.adventnet.nms.util.CommonBuilderUIInterface uiImpl = null;
	protected static boolean standalone = false;	
    
    public static com.adventnet.nms.authaudit.AuthAuditBrowser auditBrowser = null;  
    private static AuthAuditScreen frame = null;
    protected boolean canAddUser=false;
    private static java.util.Properties permissionProps = new java.util.Properties();
	//This variable is used to differentiate the standalone RMI mode
	//and RMI application mode.
	protected static boolean rmistandalone = false;
  	private JToolBar toolBar = null;
	private ImageIcon icon = null;
	JButton   AddUserButton = null;
	JButton   AddGroupButton = null;
	JButton   AddOperationButton = null;
	JButton   FindButton = null;
	JButton   HelpButton = null;
	JButton   ExitButton = null;
	JButton   FetchAuditTrails = null;	
	JButton RefreshButton = null;
  	DefaultTreeModel treeModel = null;

	//com.adventnet.security.ui.TreeCombo combo = null;

	JPopupMenu   adduser= null;
	JMenuItem      Configure_PermissionsMenuItem = null;
	JPopupMenu addgroup = null;
	JPopupMenu delegate = null;
	JMenuItem      Reset_PasswordMenuItem1 = null;
	JMenuItem      userAuditTrailsMenuItem = null;
	JMenuItem      Assign_GroupsMenuItem = null;
	JMenuItem      DeleteMenuItem1 = null;
	JMenuItem      DeleteMenuItem2 = null;	
	JMenuItem      Add_GroupMenuItem = null;
	JMenuItem      Assign_UsersMenuItem = null;
	JMenuItem      SetUserStatus = null;
	protected Vector xUsers = null;
	 //To support for the display of the different status for the user.
	protected Vector userExpired = null;
	protected Vector pwdExpired = null;
	protected Vector userForcedOut = null;
	protected Vector loginFail = null;
	private Vector audData = null;
	private String user = "Security ";
	private String groups = "";
	private boolean isUser = false;  
	
	//Wizards from this screen.
	protected com.adventnet.security.ui.AdUserWiz ad = null;
	protected com.adventnet.security.ui.AdGroupWiz newgroup = null;
	protected com.adventnet.security.ui.Operations op = null;
	protected com.adventnet.security.ui.ChangePwd pwd = null;
    //protected com.adventnet.security.ui.AuditScreen audit = null;
	//End for Wizards.

	com.adventnet.security.ui.TabbedPane2 pane2 = null; 
	TreePath path1= null;
	DefaultMutableTreeNode root1 = null;
	DefaultTreeModel treeModel1 = null;
  	com.adventnet.security.ui.TabbedPane1 pane1 =null;
	//protected static boolean runonce = false;
	protected static String CURRENTUSER = "";
	protected static com.adventnet.security.ui.AuthMain main = null;
	private boolean isDelete = false;


   public void init()
  {
	if (initialized == true) return;
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+839,getPreferredSize().height+491); 
          setTitle(resourceBundle.getString("AuthMain"));
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
setUpMenus();

  
         //<End_init>

	setTitle(resourceBundle.getString("Security Administration"));
	model.registerWithModel(this);
	DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
	renderer.setLeafIcon(getBuilderUiIfInstance().getImage("unchecked.png"));
	renderer.setOpenIcon(getBuilderUiIfInstance().getImage("unchecked.png"));
	renderer.setClosedIcon(getBuilderUiIfInstance().getImage("unchecked.png"));
	setIconImage(uiImpl.getFrameIcon());	
	getBuilderUiIfInstance().centerWindow(this);
// 	JTree2.setCellRenderer(renderer);
//	JViewport jv = new JViewport();
//	JButton jb = new JButton(resourceBundle.getString("Operations Tree"));
//	jb.setFocusPainted(false);
//	jb.setToolTipText(resourceBundle.getString("Expand / Collapse Tree"));
/*	for(int i=0;i<JTree2.getRowCount();i++)
		JTree2.expandRow(i);
		jb.addActionListener(new ActionListener()  
	{ 
		int count = 2;
		public void actionPerformed(java.awt.event.ActionEvent ev)					
		{														
			if((count % 2)==0)	
			{
				for(int i=1;i<JTree2.getRowCount();i++)
	  	 			JTree2.expandRow(i);
					count+=1;
			}	
			else
			{
				for(int i=1;i<JTree2.getRowCount()-1;i++)
	  				JTree2.collapseRow(i);
					count+=1;
			}
		}			
	});
  	jv.setView(jb);
 	JScrollPane3.setColumnHeader(jv);
			

	addWindowListener(new WindowAdapter()
	{
		public void windowClosing(WindowEvent we)
		{
			close();
		}
	}
);*/
	if(!standalone){
		String str = com.adventnet.nms.util.NmsClientUtil.getUserName();	
		CURRENTUSER = str;
	}
	this.addWindowListener(new WindowAdapter()	
	{
	public void windowClosing(WindowEvent we)
		{
			//System.out.println("CALLES CLSOE ");
			close();
		}
	});
	root1  = new DefaultMutableTreeNode(resourceBundle.getString("Security "));
	root1.add(new DefaultMutableTreeNode(resourceBundle.getString("Groups ")));
            root1.add(new DefaultMutableTreeNode(resourceBundle.getString("Users ")));
		
		
		treeModel1 = new DefaultTreeModel(root1);
		
	
        	           JTree1.setModel(treeModel1);
		 (JTree1.getSelectionModel()).setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		JTree1.setCellRenderer(new ConfigTreeRenderer());
				//JTree1.setExpandsSelectedPaths(true);
				//Fix for jdk1.2 compatibility.
		try

		{
			Class treeClass = JTree1.getClass();
			java.lang.reflect.Method  m = treeClass.getMethod("setExpandsSelectedPaths", new
					Class[] { boolean.class});

			if (m != null)

			{

				m.invoke(JTree1, new Object[] { new Boolean(true)});

			}

		}

		catch ( Exception e)

		{



		}



				
	/*
	DefaultMutableTreeNode root2  = new DefaultMutableTreeNode();
		
		DefaultTreeModel treeModel2 = null;
		treeModel2 = new DefaultTreeModel(root2);
	*/	

        	       //    JTree2.setModel(treeModel2);
		setUpToolBar();
	//combo = new com.adventnet.security.ui.TreeCombo(treeModel1);
	//CardPanel1.showCard("label");
	
	setSecurityModel(model);
	
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
            java.lang.String[]  CardPanel1cardAndClassNames_array = new java.lang.String[ 3 ]; 
            CardPanel1cardAndClassNames_array[ 0 ] = resourceBundle.getString("label=com.adventnet.security.ui.Lable");
            CardPanel1cardAndClassNames_array[ 1 ] = resourceBundle.getString("tab1=com.adventnet.security.ui.TabbedPane1");
            CardPanel1cardAndClassNames_array[ 2 ] = resourceBundle.getString("tab2=com.adventnet.security.ui.TabbedPane2");
            CardPanel1.setCardAndClassNames(CardPanel1cardAndClassNames_array);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+CardPanel1,ex); 
          }

//<UserCode_Begin_Bean_CardPanel1>

//<UserCode_End_Bean_CardPanel1>
		JSplitPane1.setPreferredSize(new Dimension(JSplitPane1.getPreferredSize().width+540,JSplitPane1.getPreferredSize().height+28));

  
          //<End_setUpProperties>
	//JLabel1.setIcon(uiImpl.getImage("adduser.png"));
	//JPanel2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("User Properties")));
	//JTabbedPane1.setTitleAt(0,resourceBundle.getString("User Properties"));
	//JTabbedPane1.setTitleAt(1,resourceBundle.getString("Users for Group"));
	//JTabbedPane1.setTitleAt(2,resourceBundle.getString("Operations"));			
	//JCheckBox1.setEnabled(false);
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
        JSplitPane1= new javax.swing.JSplitPane();
        JScrollPane1= new javax.swing.JScrollPane();
        JTree1= new javax.swing.JTree();
        CardPanel1= new com.adventnet.beans.panels.CardPanel(applet);

  
        //<End_initVariables>
	
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JSplitPane1,BorderLayout.CENTER);
JSplitPane1.setLeftComponent(JScrollPane1);
JSplitPane1.setDividerLocation(164);
JScrollPane1.getViewport().add(JTree1);
JSplitPane1.setRightComponent(CardPanel1);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      JTree1_CardPanel1_conn JTree1_CardPanel1_conn1 =  new JTree1_CardPanel1_conn();
      JTree1.addTreeSelectionListener(JTree1_CardPanel1_conn1);
      JTree1_JSplitPane1_conn1 JTree1_JSplitPane1_conn11 =  new JTree1_JSplitPane1_conn1();
      JTree1.addMouseListener(JTree1_JSplitPane1_conn11);
  
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





  

  

 
  

 
   

public JPopupMenu getdelegate()

  {		
	if(delegate== null)
   {
      delegate = new JPopupMenu();
      Configure_PermissionsMenuItem = new JMenuItem(resourceBundle.getString("Configure Permissions"));
      Configure_PermissionsMenuItem.setMnemonic('P');
      Configure_PermissionsMenuItem.setActionCommand(resourceBundle.getString("Configure Permissions"));
      Configure_PermissionsMenuItem.addActionListener(menuToolBarAction);
      delegate.add(Configure_PermissionsMenuItem);
      delegate.addSeparator();
	/*
      Reset_PasswordMenuItem = new JMenuItem(resourceBundle.getString("Change Password"));
      Reset_PasswordMenuItem.setMnemonic('P');
      Reset_PasswordMenuItem.setActionCommand(resourceBundle.getString("Reset Password"));
      Reset_PasswordMenuItem.addActionListener(menuToolBarAction);
      delegate.add(Reset_PasswordMenuItem);
	*/
      delegate.addSeparator();
      DeleteMenuItem = new JMenuItem(resourceBundle.getString("Delete"));
      DeleteMenuItem.setMnemonic('D');
      DeleteMenuItem.setActionCommand(resourceBundle.getString("Delete"));
      DeleteMenuItem.addActionListener(menuToolBarAction);
      delegate.add(DeleteMenuItem);
   }
   return delegate;

  
  
   //<Begin_getdelegate>
   //<End_getdelegate>
  } 

//<Begin_class_PopupMenuMouseListener>
public class PopupMenuMouseListener extends MouseAdapter{ 
  
public void mouseClicked(MouseEvent evt)

  { 
      if(evt.isMetaDown())
      {
      }

  } 
}
//<End_class_PopupMenuMouseListener> 
//<Begin_class_MenuToolBarAction>
public class MenuToolBarAction extends AbstractAction{ 
  
public void actionPerformed(ActionEvent evt)

  { 
      if(evt.getActionCommand().equals(resourceBundle.getString("AddUser")))
      {
         
//<UserCode_Begin_menuItem_AddUserMenuItem>
//Please add the action code for menu Here AddUser
/*
 ad = new AdUserWiz();
ad.setSecurityModel(model);
ad.setGroups(setGroups());
ad.setVisible(true);
*/

ad = new  AdUserWiz(AuthMain.this , applet);
  Point p = JTree1.getLocationOnScreen();  
  ad.setLocation(p);      
 ad.init();
 ad.setState(true);   
  ad.setSecurityModel( model );
 ad.setBuilderUiIf(uiImpl);  
 ad.setVisible(true);
setDelete(false,user);
//<UserCode_End_menuItem_AddUserMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("AddGroup")))
      {
         
//<UserCode_Begin_menuItem_AddGroupMenuItem>
//Please add the action code for " AddGroup "
newgroup = new AdGroupWiz(AuthMain.this,applet);
//newgroup.setSecurityModel(model);
//if(model == null)
//System.out.println("dwaraka model"+model);
newgroup.setVisible(true);
setDelete(false,user);
//<UserCode_End_menuItem_AddGroupMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("AddOperations")))
      {
         
//<UserCode_Begin_menuItem_AddOperationsMenuItem>
//Please add the action code for menu Here AddOperations
op = new Operations(AuthMain.this,applet);
op.setSecurityModel(model);
op.setVisible(true);
//<UserCode_End_menuItem_AddOperationsMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("Exit")))
      {
         
//<UserCode_Begin_menuItem_ExitMenuItem>
//Please add the action code for menu Here E
close();
//<UserCode_End_menuItem_ExitMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("AuditTrails")))
      {
         
//<UserCode_Begin_menuItem_AuditTrailsMenuItem>
if(com.adventnet.nms.authaudit.AuthAuditBrowser.auditBrowserInstance == null)
              {
		  if(NmsClientUtil.getMainPanel() == null)
		  {
		         Utilities.errorMessage(resourceBundle.getString("Error while viewing Audit Trails"));
		         System.out.println("Exception while viewing audit trails.The Main Panel instance is null");
		         return;
		  }						
		  AuthMain.auditBrowser = new com.adventnet.nms.authaudit.AuthAuditBrowser();		  
                  AuthMain.auditBrowser.init(com.adventnet.nms.util.NmsClientUtil.getMainPanel().applet);
              }  
          AuthMain.auditBrowser = com.adventnet.nms.authaudit.AuthAuditBrowser.auditBrowserInstance;
          if(AuthMain.frame == null)
              {
                  AuthMain.frame = new AuthAuditScreen();
                  frame.setUpGUI(auditBrowser);
                  /*
                  com.adventnet.nms.util.NmsClientUtil.getMainPanel().moduleIds.put("AuthAudit","AuthAuditBrowser");
                  com.adventnet.nms.util.NmsClientUtil.getMainPanel().panelList.put("AuthAuditBrowser",auditBrowser);
                  String nodeId = null;
                  String moduleId = "AuthAudit";
                  java.util.Properties criteria = new java.util.Properties();
                  java.util.Properties treeNodeProps = new java.util.Properties();
                  String fieldsWanted = "User Name=userName=150;Operation Name=operation=75;Audit Time=auditTime=75;Status=status=75;Category=category=75;Audited Object=auditedObj=75";
                  criteria.setProperty("FieldsWanted", fieldsWanted);
                  treeNodeProps.setProperty("MENU-FILE-NAM", "authauditmenu.xml");
                  treeNodeProps.setProperty("TABLE-POPUP-MENU","View");
                  treeNodeProps.setProperty("TABLE-COLUMNS",fieldsWanted);
                  boolean addToTree = false;
                  String parentNodeId = "null";
                  tempCVId = com.adventnet.nms.util.NmsUiAPI.addTemporaryCustomView(nodeId, moduleId, criteria, treeNodeProps, addToTree, parentNodeId);
                  System.out.println("Temp Custom View Id >> "+tempCVId);
                  */
                  
              }
          java.util.Properties p = new java.util.Properties();
          //p.setProperty("<isTempCV>", "true");
          auditBrowser.setProperties(p);
          frame.setVisible(true);


          //Please add the action code for " AuditTrails "
          //audit = new AuditScreen(AuthMain.this,applet);
          //audData = model.getAudit(null);
          //audit.setVisible(true);
          //System.out.println("THE TRAILS "+audData);
          //audit.setAuditData(audData);
//<UserCode_End_menuItem_AuditTrailsMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("Refresh")))
      {
         
//<UserCode_Begin_menuItem_RefreshMenuItem>
//Please add the action code for menu Here Refresh
/*
RefreshButton_Datachange data = new RefreshButton_Datachange();
RefreshMenuItem.addActionListener(data);
*/
refresh();
//<UserCode_End_menuItem_RefreshMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("Reset Password")))
      {
         
//<UserCode_Begin_menuItem_Change_PasswordMenuItem>
//Please add the action code for menu Here Reset Password

pwd = new ChangePwd(AuthMain.this,applet);
Point p = JSplitPane1.getLocationOnScreen();  
pwd.setLocation(p);       
pwd.init();
if(isUser)
{
pwd.setSecurityModel(model);  

pwd.setUserData(user,new Vector());  
pwd.setVisible(true);
}
else{
Utilities.errorMessage(resourceBundle.getString("Please select a user to change the password "));
return; 
}

setDelete(false,user);
//System.out.println("DWARAKA ");
//<UserCode_End_menuItem_Change_PasswordMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("Delete")))
      {
         
//<UserCode_Begin_menuItem_DeleteMenuItem>
//Please add the action code for menu Here Delete
if(!(user.equals(resourceBundle.getString("Security "))) && !(user.equals(resourceBundle.getString("Groups "))) && !(user.equals(resourceBundle.getString("Users ")))){
 
if(groups.equals(resourceBundle.getString("Groups "))){
   Vector groupsVec = new Vector();
   groupsVec = model.getGroupsForUser(CURRENTUSER);
   if(groupsVec.contains(user))
   {
	JOptionPane.showMessageDialog(null,"It is not allowed to delete this group as the currently logged-in user associated to it.", resourceBundle.getString("Warning!"), JOptionPane.WARNING_MESSAGE, null);//No I18N
	return;
   }
 if( JOptionPane.showConfirmDialog(null,
                                          resourceBundle.getString( resourceBundle.getString("Are you sure you want to do delete this group ? ")),
                                           resourceBundle.getString("Warning!"),
                                           JOptionPane.YES_NO_OPTION,
                                          JOptionPane.WARNING_MESSAGE,
                                           null) == JOptionPane.YES_OPTION)
    {
         disableButtons();
         setDelete(true, "");
         model.deleteGroup(user);
    }
 }  
else if(groups.equals(resourceBundle.getString("Users "))){
if(user.equals(CURRENTUSER))
 {
	 //String str = resourceBundle.getString("You have logged in as   ")+CURRENTUSER+" ."+"\n"+resourceBundle.getString("Cannot delete the currently logged in user");
	 String str = java.text.MessageFormat.format(resourceBundle.getString("You have logged in as {0} ."), new String[]{CURRENTUSER}) + "\n"+resourceBundle.getString("Cannot delete the currently logged in user");
   JOptionPane.showMessageDialog(null,str, resourceBundle.getString("Warning!"), JOptionPane.WARNING_MESSAGE, null);
  return;  
 }  
  if( JOptionPane.showConfirmDialog(null,
                                          resourceBundle.getString( "On deleting this  user you would no longer be able to log on with \nthis user name, are you sure you want to do this ? "),
                                           resourceBundle.getString("Warning!"),
                                           JOptionPane.YES_NO_OPTION,
                                          JOptionPane.WARNING_MESSAGE,
                                           null) == JOptionPane.YES_OPTION)
   {
        disableButtons(); 
        isDelete = true;
        model.deleteUser(user);            
   }
   
// isDelete = true; 
 
  }  
} 
else{
 Utilities.errorMessage(resourceBundle.getString("Please select a group/user to be deleted."));
  return; 
}
 setDelete(true,"");
path1=null;
//<UserCode_End_menuItem_DeleteMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("Help Contents")))
      {
         
//<UserCode_Begin_menuItem_Help_ContentsMenuItem>
//Please add the action code for menu Here Help Contents
//com.adventnet.nms.util.NmsClientUtil.showHelpBasedOnKey("Security_Administration");
 if(standalone ) 
 { 
 String urltext = getHelpURL();  
 com.adventnet.nms.util.BrowserControl.displayURL(urltext,"sameWindow"); 
 } 
 else 
 { 
 com.adventnet.nms.util.NmsClientUtil.showHelpBasedOnKey("Security_Administration"); 
 }
//<UserCode_End_menuItem_Help_ContentsMenuItem>

      
}

  } 
}
//<End_class_MenuToolBarAction> 
	
	
public class UserAuditTrailAction extends AbstractAction{ 
  
public void actionPerformed(ActionEvent evt)

  { 
      if(evt.getActionCommand().equals(resourceBundle.getString("UserAuditTrails")))
      {
            if(AuthMain.auditBrowser == null)
    {
        ActionEvent ae = new ActionEvent(evt.getSource(), evt.getID(), "AuditTrails");
        AuthMain.this.menuToolBarAction.actionPerformed(ae);
	return;
    }
         java.util.Properties p = new java.util.Properties();
         p.setProperty("userName", user);
         p.setProperty("AnyOrAll","MatchAll");
         p.setProperty("Arg","temporary");
         AuthMain.auditBrowser.setProperties(p);
         frame.setVisible(true);
         auditBrowser.addShowAllButton();
     }
  }
	}
	/*
public class MenuToolBarAction1 extends MenuToolBarAction{ 
  
public void actionPerformed(ActionEvent evt)

  { 
	      if(evt.getActionCommand().equals(resourceBundle.getString("Assign Users"))){
			
	       	
  }
}

public class MenuToolBarAction extends AbstractAction{ 
  
public void actionPerformed(ActionEvent evt)

  { 
      if(evt.getActionCommand().equals(resourceBundle.getString("Configure Permissions")))
      {
         //Please add the action code for menu Here Configure Permissions
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Reset Password")))
      {
         //Please add the action code for menu Here Reset Password
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Delete")))
      {
         //Please add the action code for menu Here Delete
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("AddUser")))
      {
         //Please add the action code for menu Here AddUser
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Reset Password")))
      {
         //Please add the action code for menu Here Reset Password
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Assign Groups")))
      {
         //Please add the action code for menu Here Assign Group
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Delete")))
      {
         //Please add the action code for menu Here Delete
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Add Group")))
      {
         //Please add the action code for menu Here Add Group
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Assign Users")))
      {
         //Please add the action code for menu Here Assign Users
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("AddUser")))
      {
         //Please add the action code for menu Here AddUser
AddUser1 ad = new AddUser1();
ad.setVisible(true);
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("AddGroup")))
      {
         //Please add the action code for menu Here AddGroup
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("AddOperations")))
      {
         //Please add the action code for menu Here AddOperations
Operations op = new Operations();
op.setSecurityModel(model);
op.setVisible(true);
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Exit")))
      {
         //Please add the action code for menu Here Exit
dispose();
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Find")))
      {
         //Please add the action code for menu Here Find
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Refresh")))
      {
         //Please add the action code for menu Here Refresh
RefreshButton_Datachange data = new RefreshButton_Datachange();
RefreshMenuItem.addActionListener(data);
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Reset Password")))
      {
         //Please add the action code for menu Here Reset Password

pwd = new ChangePwd(applet);
  Point p = JLabel1.getLocationOnScreen();  
  pwd.setLocation(p);       
 pwd.init();

pwd.setSecurityModel(model);  
pwd.setUserData(uname.toString(),getGroups());  
 pwd.setVisible(true);  

      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Delete")))
      {
         //Please add the action code for menu Here Delete
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("About")))
      {
         //Please add the action code for menu Here About
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Help Contents")))
      {
         //Please add the action code for menu Here Help Contents
      }

  } 
}
*/
	
	//For render

class ConfigTreeRenderer extends DefaultTreeCellRenderer
{

    ImageIcon task1 = null;
    ImageIcon unch = null;
    ImageIcon ch = null;
    ImageIcon ch1 = null;
    ImageIcon chout = null;
    ImageIcon pwdexp = null;
    ImageIcon chexp = null;
    ImageIcon logfail = null;	
    public ConfigTreeRenderer()
    {
        task1 = uiImpl.getImage("task1.png");
        unch = uiImpl.getImage("user1.png");
        ch = uiImpl.getImage("group1.png");		
        //To support for the display of the different status for the user.
        ch1 = uiImpl.getImage("user_disabled.png"); 
        chout = uiImpl.getImage("user_out_of_server.png");
        pwdexp = uiImpl.getImage("pwd_expiry.png");
        chexp = uiImpl.getImage("user_expired.png");
        logfail = uiImpl.getImage("user_login_failed.png");		
        //setOpaque(false);
    }

    public Component getTreeCellRendererComponent(JTree tree,Object value,boolean selected,boolean expanded,boolean leaf,int row,boolean hasFocus)
    {
		
	super.getTreeCellRendererComponent(tree,value,selected,expanded,leaf,row,hasFocus);
	//setText(value.toString());
	
	String tob = (String)(((DefaultMutableTreeNode)value).getUserObject());
	
	if((tob.toString()).equals(resourceBundle.getString("Security ")))
		setIcon(task1);					
	if(((DefaultMutableTreeNode)value).getParent() !=null){
		
		DefaultMutableTreeNode val = (DefaultMutableTreeNode)((DefaultMutableTreeNode)value).getParent();
 		String toc = (String)(((DefaultMutableTreeNode)val).getUserObject());
	
	if(toc.toString().equals(resourceBundle.getString("Groups "))){
		setIcon(ch);
	}
	
	else if((toc.toString()).equals(resourceBundle.getString("Users ")) ){
 		setIcon(unch);
		int cou = val.getChildCount();
	//DefaultMutableTreeNode disab = (DefaultMutableTreeNode)value
	 	//System.out.println("COUNT "+cou);
		
		if(xUsers!=null){
			int size =xUsers.size();
			for(int i = 0;i<size;i++){
				String child1 = (String)(((DefaultMutableTreeNode)value).getUserObject());
				if(child1.toString().equals(xUsers.elementAt(i).toString())){
					setIcon(ch1);
				}
			}
		}
		 //To support for the display of the different status for the user.
		if(userExpired != null)
		{
			int size1 = userExpired.size();
			for(int j=0;j<size1;j++)
			{
				String child2 = (String)(((DefaultMutableTreeNode)value).getUserObject());
				if(child2.toString().equals(userExpired.elementAt(j).toString()))
				{
					setIcon(chexp);
				}
			}
		}
		
		if(pwdExpired != null)
		{
			int size2 = pwdExpired.size();
			for(int k=0;k<size2;k++)
			{
				String child3 = (String)(((DefaultMutableTreeNode)value).getUserObject());
				if(child3.toString().equals(pwdExpired.elementAt(k).toString()))
				{
					setIcon(pwdexp);
				}
			}
		}
		
		if(userForcedOut != null)
		{
			int size3 = userForcedOut.size();
			for(int m=0;m<size3;m++)
			{
				String child4 = (String)(((DefaultMutableTreeNode)value).getUserObject());
				if(child4.toString().equals(userForcedOut.elementAt(m).toString()))
				{
					setIcon(chout);
				}
			}
		}
		
		if(loginFail != null)
		{
			int size4 = loginFail.size();
			for(int s=0;s<size4;s++)
			{
				String child5 = (String)(((DefaultMutableTreeNode)value).getUserObject());
				if(child5.toString().equals(loginFail.elementAt(s).toString()))
				{
					setIcon(logfail);
				}
			}
		}

	}
	

	}
	else ;
	return this;	
		
    }		

}
	
	
	//Utility methods.
    boolean firsttime = true;	
	public void setRoot()
	{
            /*if(!standalone){		
              if(firsttime)
              {
              // This is to fix the Tree node settings  problem. This is temporoy, a proper fix to be provided 
              firsttime = false;
              return;
              }
              }*/
            //System.out.println("MY NODE "+treeModel1.getRoot()+"         "+treeModel1.getChildCount(treeModel1.getRoot()));
            DefaultMutableTreeNode group  = (DefaultMutableTreeNode)(treeModel1.getChild(root1.getRoot(),0));

            DefaultMutableTreeNode users = (DefaultMutableTreeNode)(treeModel1.getChild(root1.getRoot(),1));
            //System.out.println("GROUP "+group+"USERS "+user);

            java.util.Vector vec = setGroups();
            //System.out.println(vec);
            group.removeAllChildren();
            users.removeAllChildren();

            int size = vec.size();

            for(int i=0;i<size;i=i+1){
                group.add(new DefaultMutableTreeNode(vec.elementAt(i)));

            }	

            /*	
              root1.add(group);

              DefaultMutableTreeNode user = new DefaultMutableTreeNode("Users ");
            */
            java.util.Vector usr = setUsers();
            int siz = usr.size();
            //System.out.println(usr);
            for(int j=0;j<siz;j=j+1){
                users.add(new DefaultMutableTreeNode(usr.elementAt(j)));
                /*
                  if(user.equals(usr.elementAt(j))){
                  //System.out.println("for path");
                  path1 = new TreePath(users.getPath());
                  }
                */
            }	

            //root.add(user);

            //treeModel1 = new DefaultTreeModel(root);
            treeModel1.reload();
            /*
              DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
              render.setIcon(uiImpl.getImage("user1.png"));
            */
            //combo.setModel(treeModel);
            //combo = new com.adventnet.security.ui.TreeCombo(treeModel);
            //JTree1.updateUI();
            //JTree1.setModel(treeModel1);
            JTree1.setSelectionRow(0);
            Hashtable userAttributes = model.getAllUserAttributes();
            setDisabledUsers(userAttributes);
            //JTree1.setCellRenderer(new ConfigTreeRenderer());
            //System.out.println("THE TREE PATHl  "+path1);
        }
	
	public void setTreeRoot(){
	
			


	}
	//Added for Interface.
	public void close()
	{
/*
		AdvPropScreen1.close();
		if(prop != null)
			prop.dispose();
		if(pwd != null)
			pwd.dispose();
		if(userWiz != null)
			userWiz.dispose();
		*/		
		//runonce = false;
		//System.out.println("FOR CLOSE Main"+runonce);
		model.cleanUp();
		model = null;
		main=null;
	
		//Added for child UI disposing

		if(ad != null)
			ad.dispose();
		if(op != null)
			op.dispose();
		if(newgroup != null)
			newgroup.dispose();
		if(pwd != null)
			pwd.dispose();	
	

		dispose();
		if(rmistandalone)
		 	System.exit(0);

	}
    
	public void focusGained(java.awt.event.FocusEvent focus)
	{
		model.registerWithModel(this);
	}
    
	public void fireDataChanged()
	{
        JTree1.expandRow(2);
        JTree1.expandRow(1);
        int c[]=JTree1.getSelectionRows();
        
            setRoot();
            JTree1.expandRow(2);
            JTree1.expandRow(1);
            //setDisabledUsers();
            if(path1 !=null && !isDelete){
                setDelete(false,user);
                if(c!=null)
                {
                    JTree1.setSelectionRow(c[0]);
                }
                else
                {
                    JTree1.setSelectionRow(0);
                }
            }
            /*
            if(audit!=null)
                {
                    audit.valueChanged();
                }
            */ 
            if(isDelete)
                {
                    //System.out.println("FOR DWARAKA DELETE ");
                    JTree1.expandRow(2);
                    JTree1.expandRow(1);
                    //isDelete = false;
                    setDelete(false,"");
                    //path1.getParent
                }
            else{
		JTree1.expandRow(2);
                JTree1.expandRow(1);
            }
            model.checkAuthorizationForButtons();
            
		/*
                  JTree1.setSelectionRow(0);
		
                  if(pane2!=null){
                  //pane2=(com.adventnet.security.ui.TabbedPane2)CardPanel1.getCard("tab2");   
                  pane2.setUserData(model.getUsersForGroup(user),setUsers(),user);    
                  java.util.Vector vec = new java.util.Vector();  
                  vec.add(user); 
                  pane2.setOperations(model.getOperationsForGroup(vec));   								
                  }
                  if(pane1 !=null){
                  pane1.setGroupData(model.getAllGroupsForUser(user),setGroups(),user);
                  pane1.setOperations(model.getOperations(user));      
                  Hashtable useratb = model.getUserAttributes(user);
                  int userage =0;
                  int pwdage =0; 
                  if(useratb.get("userexpirytime") != null)
                  userage = new Integer(useratb.get("userexpirytime").toString()).intValue();
                  if(useratb.get("passwdexpirytime") != null)
                  pwdage =  new Integer(useratb.get("passwdexpirytime").toString()).intValue();
                  if(xUsers.contains(user))    
                  pane1.setUserAttributes(userage,pwdage,"disabled"); 
                  else
                  pane1.setUserAttributes(userage,pwdage,"enabled"); 
                  }
	
                  int rowcount = JTree1.getRowCount();
                  for(int i=0;i<rowcount;i++){
                  JTree1.expandRow(i);
                  }
		
                  JTree1.setSelectionPath(path1);
                  JTree1.updateUI();
		*/
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	public void focusLost(java.awt.event.FocusEvent lost)
	{
	}
	public void showError(java.lang.String error)
	{
		if(error == null)
		{
			error = "Exception caught null";	
		}
		Utilities.errorMessage(resourceBundle.getString(error));
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	public void setBuilderUiImpl(com.adventnet.nms.util.CommonBuilderUIImpl builderImpl)
	{
	}
	public void setSecurityModel(com.adventnet.security.ui.AbstractSecurityModel absrModel)
	{
	}

	//For the connection
	public void setSessionAndBuilderImplemantation(String bui, String seImpl )
	{
	   model = new com.adventnet.security.ui.SecurityModel(seImpl);
	   model.registerWithModel(this);			
	    try  
                	{
             	      Class cl = Class.forName(bui);                         
		      uiImpl = (com.adventnet.nms.util.CommonBuilderUIInterface)cl.newInstance();
		}
	  catch(Exception ce)	
	           {
		      System.err.println(ce);
	           }
	}

	public static com.adventnet.nms.util.CommonBuilderUIInterface getBuilderUiIfInstance()
	{
 		return uiImpl;
	}

	public void setDisabledUsers(Hashtable userAttributes)
 	{
        xUsers = new Vector();
        userExpired = new Vector();
        loginFail = new Vector();
        userForcedOut = new Vector();
        pwdExpired = new Vector();
        
        //UserTableCellRenderer1.disabledvalues.clear();
        
        /*Vector users = model.getAllUserNames();
        if( users  == null)
        {
            return;
        }
        String userStatus = "";
        Hashtable userAttr = null;
        for(int i=0;i<users.size();i++ )
        {
            userAttr = model.getUserAttributes(users.elementAt(i).toString());
            userStatus =  (String) userAttr.get("status");

            if(userStatus.equals("disabled") )
            {
                xUsers.add(users.elementAt(i));
            }
            //To support for the display of the different status for the user.
            else if( userStatus.equals("loginFailed") )
            {
                loginFail.add(users.elementAt(i));
            }
            else if( userStatus.equals("userExpired") )
            {
                userExpired.add(users.elementAt(i));
            }
            else if( userStatus.equals("passwordExpired") )
            {
                pwdExpired.add(users.elementAt(i));
            }
            else if( userStatus.equals("forcedOut") )
            {
                userForcedOut.add(users.elementAt(i));
            }
        }*/
        String userStatus = "";
        Hashtable userAttr = null;
        
        for (Enumeration en = userAttributes.keys(); en.hasMoreElements();)
        {
            String userName = (String) en.nextElement();
            userAttr = (Hashtable) userAttributes.get(userName);
            userStatus = (String) userAttr.get("status");
            
            if(userStatus.equals("disabled") )
            {
                xUsers.add(userName);
            }
            //To support for the display of the different status for the user.
            else if( userStatus.equals("loginFailed") )
            {
                loginFail.add(userName);
            }
            else if( userStatus.equals("userExpired") )
            {
                userExpired.add(userName);
            }
            else if( userStatus.equals("passwordExpired") )
            {
                pwdExpired.add(userName);
            }
            else if( userStatus.equals("forcedOut") )
            {
                userForcedOut.add(userName);
            }
        }
	}		

 
	public void disableButtons(){
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
	}

	 public void enableButtons() 
    	{
      		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}


	public void setDelete(boolean state,String user){
	
		isDelete = state;
			JTree1.expandRow(2);
			JTree1.expandRow(1);
		if(!user.equals(""))
			JTree1.setSelectionPath(path1);
			//JTree1.setSelectionRow(JTree1.getRowForPath(path1));
			
		
	}


	public void enableMenuOptions(boolean value)
	{
	    NewMenu.setEnabled(value);
	    AddUserButton.setEnabled(value);
	    AddGroupButton.setEnabled(value);
	    AddOperationButton.setEnabled(value);
	    //FindButton.setEnabled(value);
	    Change_PasswordMenuItem.setEnabled(value);
	    DeleteMenuItem.setEnabled(value);		
	}

  




  


 

  





  




  


 

  





  




  


 

    public void setVisible(boolean bl)
  {
        //AuthMain is not null meaning already AuthMain is loaded
        if (main != null)
        {
            //Already one AuthMain is loaded, so dispose the the current one and
            //show the already loaded AuthMain
            if ((disposeMe) && (bl))
            {
                super.setVisible(false);
                dispose();
                System.gc();

                if (main.getState() == Frame.ICONIFIED)
                {
                    main.setState(Frame.NORMAL);
                }
		main.model.checkAuthorizationForButtons();
                main.toFront();
                main.setVisible(true);
                return;
            }
        }

        /*if (runonce && bl)
        {
            //System.out.println("RUNONCE "+runonce);
            super.setVisible(false);
            dispose();
            if(main.getState() == Frame.ICONIFIED)
                main.setState(Frame.NORMAL);
            main.toFront();
            return;
        }        
        */
	
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
	    if(bl){
            //runonce = true;
            main = this;
            int count = 0;
            while (!model.isInitialized())
            {
                try
                {
                    Thread.sleep(50);
                    count++;

                    if (count > 100)
                    {
                        break;
                    }
                }
                catch (Exception e)
                {
                }
            }
            setRoot();
	    JTree1.setCellRenderer(new ConfigTreeRenderer());
	    if(!model.isInitialized())
	    {
		enableMenuOptions(false);
	    	showError("com.adventnet.security.ui.dataFetchError"); //No I18N
	    }
        }
    }	


//This constructor to differentiate the invocation between 
 //the socket client and the WebNms client
 public AuthMain(javax.swing.JApplet applet){
	 standalone = true;
	 init(applet);
 }

  public void init(javax.swing.JApplet applet)
  {
	  /*if(runonce)
	  {
		  //System.out.println(" WITHIN INIT ");
		  return; 
	  }*/
      if (main != null)
      {
          return;
      }
	  this.applet = applet;
	  String st = "";
	  String str = "";
		
	  if(getParameter("RESOURCE_PROPERTIES" ) != null)
	      {
		  localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	      }
	  resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
	  if(applet.getParameter("COMMUNICATION_IMPL") == null)	
		{
                             st = "com.adventnet.security.ui.SecuritySession";
		}
             else
		{
		       st = applet.getParameter("COMMUNICATION_IMPL");
		}

			 if(!standalone){
				 if(applet.getParameter("BUILDER_UI_IMPL") == null)
				 {
					 str = "com.adventnet.nms.util.CommonBuilderUIImpl";
				 }
				 else
				 {
					 str = applet.getParameter("BUILDER_UI_IMPL");
				 }
			 }
  
  			else{
				str = "com.adventnet.nms.runtimeconfig.CommonBuilderUIStandAloneImpl";
			}
	
		
           setSessionAndBuilderImplemantation(str, st);
		   if(standalone){
			   java.util.Properties prop = new java.util.Properties();
			   prop.put("HOST",applet.getParameter("HOSTNAME"));
			   prop.put("HTTP_PORT",applet.getParameter("WEB-SERVER-PORT"));	
			   uiImpl.initialize(prop);
		   }
		   else{		
	           uiImpl.init(applet);
		   }
		   model.checkAuthorizationForButtons();
	if(false)
		{
  
   //<Begin_init_javax.swing.JApplet>
   this.applet = applet;
   init();
  
   //<End_init_javax.swing.JApplet>
  }
}
 
	public void setUsers(String userName)
	{
		//java.ustil.Vector users = model.getUsersForGroup(userName);
		//JList1.setListData(users);
	}
	
	public void setOperations(String userName)
	{
		//System.out.println("OPE"+model.getOperations(userName));
		/*
		java.util.Vector groups = model.getGroupsForUser(userName);
		int siz = groups.size();
		java.util.Vector views = new java.util.Vector();
		for(int i=0;i<siz;i++){
		java.util.Vector temp = new java.util.Vector();	
			temp = model.getViewsForGroup(groups.elementAt(i).toString());
			int len = temp.size();
			for(int j=0;j<len;j++){
			String view = (String)temp.elementAt(j);
			if(!views.contains(view))
				views.add(view);
			}
		}
		int l = views.size();
		java.util.Hashtable oper = new java.util.Hashtable();
		for(int k=0;k<l;k++){
			oper = model.getViewOperations(views.elementAt(k).toString());
		}*/
	}
    
	public Vector setGroups()
	{
		java.util.Vector group = new java.util.Vector();
		java.util.Vector vec = model.getGroups();	
		int size = vec.size();
		for(int i=0;i<size-1;i=i+2){
			group.addElement(vec.elementAt(i));
		}
		//return group;
		return sortElements(group, java.text.Collator.getInstance());
	}
    
	public Vector setUsers()
	{
		java.util.Vector user = new java.util.Vector();
		java.util.Vector usr = model.getAllUsers();
		int siz = usr.size();
		//System.out.println(usr);
		for(int j=0;j<siz-1;j=j+2){
			user.addElement(usr.elementAt(j));
		}
		//return user;
		return sortElements(user, java.text.Collator.getInstance());
	}

	public Vector sortElements(Vector toBeSort, java.text.Collator collator)
	{
	     String[] toSortArray = new String[toBeSort.size()];
	     for(int i=0; i <toBeSort.size();i++)
	     {
	          toSortArray[i]=(String)toBeSort.elementAt(i);
	     }
	     String temp;
	     for (int i = 0; i < toSortArray.length; i++)
	     {
	          for (int j = i + 1; j < toSortArray.length; j++)
	          {
	               // Compare elements of the array two at a time.
	               if (collator.compare(toSortArray[i],toSortArray[j] ) > 0 )
	               {
	                    // Swap words[i] and words[j]
	                    temp = toSortArray[i];
	                    toSortArray[i] = toSortArray[j];
	                    toSortArray[j] = temp;
	                }
	           }
	      }
	      return new Vector((Collection)Arrays.asList(toSortArray));
	}

	public void setGroups(String userName){
	//	JList1.setListData(model.getAllGroupsForUser(userName));
	}

	
	public Vector setCVS(){
		Vector cvsVec = new Vector();
		Hashtable cvsHash = model.getAuthScope(user);

		for(Enumeration en = cvsHash.keys();en.hasMoreElements();){
			cvsVec.addElement(en.nextElement());
		}
		return cvsVec;	
	}





 






//JMenuItem   SetUserStatus = null;

    boolean adduserAuthorization = true;
  public JPopupMenu getadduser(String usern)

  {
   //<Begin_getadduser>
   //<End_getadduser>
	/*
	if(adduser!=null){
		if(xUsers.contains(usern)){	
		      	SetUserStatus = new JMenuItem(resourceBundle.getString("Enable"));
			SetUserStatus.setActionCommand(resourceBundle.getString("Enable"));
		      }
		      else{
			SetUserStatus = new JMenuItem(resourceBundle.getString("Disable"));
			SetUserStatus.setActionCommand(resourceBundle.getString("Disable"));
		      }
		}
		*/	

	
   {
	adduser = new JPopupMenu();
	
	if(user.equals(resourceBundle.getString("Users "))){
	
      AddUserMenuItem = new JMenuItem(resourceBundle.getString("AddUser"));
      AddUserMenuItem.setActionCommand(resourceBundle.getString("AddUser"));
      AddUserMenuItem.setMnemonic('U');	
      AddUserMenuItem.addActionListener(menuToolBarAction);
AddUserMenuItem.setFont(new Font("SansSerif",0,12)); 	
AddUserMenuItem.setEnabled(adduserAuthorization);
      adduser.add(AddUserMenuItem);
	}
	else{
	/*
      adduser = new JPopupMenu();
      AddUserMenuItem = new JMenuItem(resourceBundle.getString("AddUser"));
      AddUserMenuItem.setActionCommand(resourceBundle.getString("AddUser"));
      AddUserMenuItem.setMnemonic('U');	
      AddUserMenuItem.addActionListener(menuToolBarAction);
AddUserMenuItem.setFont(new Font("SansSerif",0,12)); 	
      adduser.add(AddUserMenuItem);
      adduser.addSeparator();
	*/
      Reset_PasswordMenuItem1 = new JMenuItem(resourceBundle.getString("Change Password"));
      Reset_PasswordMenuItem1.setMnemonic('C');
      Reset_PasswordMenuItem1.setActionCommand(resourceBundle.getString("Reset Password"));
      Reset_PasswordMenuItem1.addActionListener(menuToolBarAction);
      Reset_PasswordMenuItem1.setFont(new Font("SansSerif",0,12)); 		
      boolean status = getPermission("Change Password");
	  if(status)
	  {
		  adduser.add(Reset_PasswordMenuItem1);
	  }
      adduser.addSeparator();
	/*
	System.out.println("USERS "+xUsers+"       "+usern);
     if(xUsers.contains(usern)){	
      	SetUserStatus = new JMenuItem(resourceBundle.getString("Enable"));
	SetUserStatus.setActionCommand(resourceBundle.getString("Enable"));
      }
      else{
	SetUserStatus = new JMenuItem(resourceBundle.getString("Disable"));
	SetUserStatus.setActionCommand(resourceBundle.getString("Disable"));
      }
      SetUserStatus.addActionListener(menuToolBarAction);
      SetUserStatus.setFont(new Font("SansSerif",0,12)); 	
      adduser.add(SetUserStatus);
      adduser.addSeparator();
      Assign_GroupsMenuItem = new JMenuItem(resourceBundle.getString("Assign Groups"));
      Assign_GroupsMenuItem.setMnemonic('R');
      Assign_GroupsMenuItem.setActionCommand(resourceBundle.getString("Assign Groups"));
      Assign_GroupsMenuItem.addActionListener(menuToolBarAction);
      adduser.add(Assign_GroupsMenuItem);
Assign_GroupsMenuItem.setFont(new Font("SansSerif",0,12)); 			
      adduser.addSeparator();
	*/

      DeleteMenuItem1 = new JMenuItem(resourceBundle.getString("Delete"));
      DeleteMenuItem1.setActionCommand(resourceBundle.getString("Delete"));
      DeleteMenuItem1.addActionListener(menuToolBarAction);
	DeleteMenuItem1.setFont(new Font("SansSerif",0,12)); 		
	boolean remuser = getPermission("Remove Users");
	  if(remuser)
	  {
		  adduser.add(DeleteMenuItem1);
	  }
      
      
      userAuditTrailsMenuItem = new JMenuItem(resourceBundle.getString("Audit Trails"));
      userAuditTrailsMenuItem.setActionCommand(resourceBundle.getString("UserAuditTrails"));
      UserAuditTrailAction auditTrailAction = new UserAuditTrailAction();
      //userAuditTrailsMenuItem.addActionListener(menuToolBarAction);
      userAuditTrailsMenuItem.addActionListener(auditTrailAction);
      userAuditTrailsMenuItem.setFont(new Font("SansSerif",0,12)); 		
      adduser.add(userAuditTrailsMenuItem);
	}
   }
 //System.out.println("PRP UP MENU "+adduser);
   return adduser;

  } 
  




		



 
  public void setUpMenus()
  {
   //<Begin_setUpMenus> 
   menuBar = new JMenuBar();
   FileMenu= new JMenu(resourceBundle.getString("File"));
   FileMenu.setMnemonic('F');
   menuBar.add(FileMenu);
   NewMenu = new JMenu(resourceBundle.getString("New"));
   FileMenu.add(NewMenu);
   AddUserMenuItem = new JMenuItem(resourceBundle.getString("AddUser"));
   AddUserMenuItem.setMnemonic('U');
   AddUserMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
   AddUserMenuItem.setActionCommand(resourceBundle.getString("AddUser"));
   AddUserMenuItem.addActionListener(menuToolBarAction);
   NewMenu.add(AddUserMenuItem);
   NewMenu.addSeparator();
   AddGroupMenuItem = new JMenuItem(resourceBundle.getString("AddGroup"));
   AddGroupMenuItem.setMnemonic('G');
   AddGroupMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
   AddGroupMenuItem.setActionCommand(resourceBundle.getString("AddGroup"));
   AddGroupMenuItem.addActionListener(menuToolBarAction);
   NewMenu.add(AddGroupMenuItem);
   NewMenu.addSeparator();
   AddOperationsMenuItem = new JMenuItem(resourceBundle.getString("AddOperations"));
   AddOperationsMenuItem.setMnemonic('O');
   AddOperationsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
   AddOperationsMenuItem.setActionCommand(resourceBundle.getString("AddOperations"));
   AddOperationsMenuItem.addActionListener(menuToolBarAction);
   NewMenu.add(AddOperationsMenuItem);
   FileMenu.addSeparator();
   ExitMenuItem = new JMenuItem(resourceBundle.getString("Exit"));
   ExitMenuItem.setMnemonic('X');
   ExitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,+ActionEvent.ALT_MASK));
   ExitMenuItem.setActionCommand(resourceBundle.getString("Exit"));
   ExitMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(ExitMenuItem);
   ViewMenu= new JMenu(resourceBundle.getString("View"));
   ViewMenu.setMnemonic('V');
   menuBar.add(ViewMenu);
   AuditTrailsMenuItem = new JMenuItem(resourceBundle.getString("AuditTrails"));
   AuditTrailsMenuItem.setMnemonic('A');
   AuditTrailsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
   AuditTrailsMenuItem.setActionCommand(resourceBundle.getString("AuditTrails"));
   AuditTrailsMenuItem.addActionListener(menuToolBarAction);
   ViewMenu.add(AuditTrailsMenuItem);
   EditMenu= new JMenu(resourceBundle.getString("Edit"));
   EditMenu.setMnemonic('E');
   menuBar.add(EditMenu);
   RefreshMenuItem = new JMenuItem(resourceBundle.getString("Refresh"));
   RefreshMenuItem.setMnemonic('E');
   RefreshMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,+ActionEvent.ALT_MASK));
   RefreshMenuItem.setActionCommand(resourceBundle.getString("Refresh"));
   RefreshMenuItem.addActionListener(menuToolBarAction);
   EditMenu.add(RefreshMenuItem);
   Change_PasswordMenuItem = new JMenuItem(resourceBundle.getString("Change Password"));
   Change_PasswordMenuItem.setMnemonic('C');
   Change_PasswordMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
   Change_PasswordMenuItem.setActionCommand(resourceBundle.getString("Reset Password"));
   Change_PasswordMenuItem.addActionListener(menuToolBarAction);
   EditMenu.add(Change_PasswordMenuItem);
   EditMenu.addSeparator();
   DeleteMenuItem = new JMenuItem(resourceBundle.getString("Delete"));
   DeleteMenuItem.setMnemonic('E');
   DeleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,+ActionEvent.ALT_MASK));
   DeleteMenuItem.setActionCommand(resourceBundle.getString("Delete"));
   DeleteMenuItem.addActionListener(menuToolBarAction);
   EditMenu.add(DeleteMenuItem);
   HelpMenu= new JMenu(resourceBundle.getString("Help"));
   HelpMenu.setMnemonic('H');
   menuBar.add(HelpMenu);
   Help_ContentsMenuItem = new JMenuItem(resourceBundle.getString("Help Contents"));
   Help_ContentsMenuItem.setMnemonic('C');
   Help_ContentsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
   Help_ContentsMenuItem.setActionCommand(resourceBundle.getString("Help Contents"));
   Help_ContentsMenuItem.addActionListener(menuToolBarAction);
   HelpMenu.add(Help_ContentsMenuItem);
   this.setJMenuBar(menuBar);

  
   //<End_setUpMenus>
	AddGroupMenuItem.setFont(new Font("SansSerif",0,12));
	AddOperationsMenuItem.setFont(new Font("SansSerif",0,12));
	NewMenu.setFont(new Font("SansSerif",0,12));
	DeleteMenuItem.setFont(new Font("SansSerif",0,12));
	//FindMenuItem.setFont(new Font("SansSerif",0,12));
	FileMenu.setFont(new Font("SansSerif",0,12));
	ExitMenuItem.setFont(new Font("SansSerif",0,12));
	EditMenu.setFont(new Font("SansSerif",0,12));
	AddUserMenuItem.setFont(new Font("SansSerif",0,12));
	//AboutMenuItem.setFont(new Font("SansSerif",0,12));
	HelpMenu.setFont(new Font("SansSerif",0,12));
	Help_ContentsMenuItem.setFont(new Font("SansSerif",0,12));
	Change_PasswordMenuItem.setFont(new Font("SansSerif",0,12));
	RefreshMenuItem.setFont(new Font("SansSerif",0,12));
	//AboutMenuItem.setEnabled(false);
	ViewMenu.setFont(new Font("SansSerif",0,12));
	AuditTrailsMenuItem.setFont(new Font("SansSerif",0,12));
	}
	private void setPermission(String permission, String value)
	{	 
		permissionProps.setProperty(permission, value);
	}
	public static boolean getPermission(String operation)
	{
		String flag = permissionProps.getProperty(operation);
		if(flag != null && flag.equals("false"))//No I18N
		{
			return false;
		}
		else
		{
			return true;
		}
	}
    public void enableButtonsAndMenus(java.util.Properties prop)
    {
	String flag = prop.getProperty("Add Users");//No I18N

	if(flag != null && flag.equals("false"))//No I18N
	    {
		adduserAuthorization = false;
	    }
	else
	    {
		adduserAuthorization = true;
	    }
	AddUserButton.setEnabled(adduserAuthorization);
	AddUserMenuItem.setEnabled(adduserAuthorization);
	
	flag = prop.getProperty("Add Group");
	if(flag != null && flag.equals("false"))
	    {
		addgroupAuthorization = false;
		setPermission("Add Group", "false");//No I18N
	    }
	else
	    {
		addgroupAuthorization = true;
		setPermission("Add Group", "true");//No I18N
	    }
	AddGroupButton.setEnabled(addgroupAuthorization);
	AddGroupMenuItem.setEnabled(addgroupAuthorization);


	flag = prop.getProperty("Add Operation");
	if(flag != null && flag.equals("false"))
	    {
		AddOperationButton.setEnabled(false);
		AddOperationsMenuItem.setEnabled(false);
	    }
	else
	    {
		AddOperationButton.setEnabled(true);
		AddOperationsMenuItem.setEnabled(true);
	    }
	flag = prop.getProperty("Remove Users");
	if(flag != null && flag.equals("false"))
	{
		DeleteMenuItem.setEnabled(false);
		setPermission("Remove Users", "false");//No I18N
	}
	else
	{
		DeleteMenuItem.setEnabled(true);
		setPermission("Remove Users", "true");//No I18N
	}
	flag = prop.getProperty("Change Password");
	if(flag != null && flag.equals("false"))
	{
		Change_PasswordMenuItem.setEnabled(false);
		setPermission("Change Password", "false");//No I18N
	}
	else
	{
		Change_PasswordMenuItem.setEnabled(true);
		setPermission("Change Password", "true");//No I18N
	}
	flag = prop.getProperty("Set User Permission");//No I18N
	if(flag !=null && flag.equals("false"))//No I18N
	{
		setPermission("Set User Permission", "false");//No I18N
	}
	else
	{
		setPermission("Set User Permission", "true");//No I18N
	}
}
    

	public void setUpToolBar()
  {
	
	toolBar = new JToolBar();
	toolBar.setFloatable(false);
	/*
	DefaultMutableTreeNode root1  = new DefaultMutableTreeNode("Security ");
	
		DefaultTreeModel treeModel1 = null;
		
		treeModel1 = new DefaultTreeModel(root1);

	combo = new com.adventnet.security.ui.TreeCombo(treeModel1);
	
	int size = JSplitPane1.getDividerLocation();	
	 combo.setPreferredSize(new Dimension(size,35));
	 combo.setMaximumSize(new Dimension(size,35));
	 combo.setMinimumSize(new Dimension(size,35));
	
	toolBar.add(combo);
	*/

RefreshButton = toolBar.add(menuToolBarAction);
//icon = com.adventnet.apiutils.Utility.findImage("./images/refresh.png",applet); 
   RefreshButton.setPreferredSize(new Dimension(35,35));
   RefreshButton.setMinimumSize(new Dimension(35,35));
   RefreshButton.setMaximumSize(new Dimension(35,35));
   RefreshButton.setIcon(uiImpl.getImage("refresh.png"));
   RefreshButton.setRolloverIcon(uiImpl.getImage("refresh_mo.png"));
   RefreshButton.setActionCommand(resourceBundle.getString("Refresh"));
   RefreshButton.setToolTipText(resourceBundle.getString("Refresh"));
   RefreshButton.setBorderPainted(false);

	
        
//<Begin_setUpToolBar>
//<End_setUpToolBar>
		
		
	AddUserButton = toolBar.add(menuToolBarAction);

	   AddUserButton.setPreferredSize(new Dimension(35,35));
	   AddUserButton.setMinimumSize(new Dimension(35,35));
	   AddUserButton.setMaximumSize(new Dimension(35,35));
	   AddUserButton.setIcon(uiImpl.getImage("add_user.png"));
	   AddUserButton.setRolloverIcon(uiImpl.getImage("add_user_mo.png"));
	   AddUserButton.setActionCommand(resourceBundle.getString("AddUser"));
	   AddUserButton.setToolTipText(resourceBundle.getString("AddUser"));
	AddUserButton.setBorderPainted(false);	
	AddGroupButton = toolBar.add(menuToolBarAction);

	
	AddGroupButton.setPreferredSize(new Dimension(35,35));
	   AddGroupButton.setMinimumSize(new Dimension(35,35));
	   AddGroupButton.setMaximumSize(new Dimension(35,35));
	   AddGroupButton.setIcon(uiImpl.getImage("add_group.png"));
	   AddGroupButton.setRolloverIcon(uiImpl.getImage("add_group_mo.png"));
	   AddGroupButton.setActionCommand(resourceBundle.getString("AddGroup"));
	   AddGroupButton.setToolTipText(resourceBundle.getString("AddGroup"));
	AddGroupButton.setBorderPainted(false);	
	AddOperationButton = toolBar.add(menuToolBarAction);

	   AddOperationButton.setPreferredSize(new Dimension(35,35));
	   AddOperationButton.setMinimumSize(new Dimension(35,35));
	   AddOperationButton.setMaximumSize(new Dimension(35,35));
	   AddOperationButton.setIcon(uiImpl.getImage("add_operation.png"));
	   AddOperationButton.setRolloverIcon(uiImpl.getImage("add_operation_mo.png"));
	   AddOperationButton.setActionCommand(resourceBundle.getString("AddOperations"));
	   AddOperationButton.setToolTipText(resourceBundle.getString("AddOperation"));
	   AddOperationButton.setBorderPainted(false);
	FetchAuditTrails = toolBar.add(menuToolBarAction);
	FetchAuditTrails.setPreferredSize(new Dimension(35,35));
   	FetchAuditTrails.setMinimumSize(new Dimension(35,35));
   	FetchAuditTrails.setMaximumSize(new Dimension(35,35));
   	FetchAuditTrails.setIcon(uiImpl.getImage("audit_trails.png"));
   	FetchAuditTrails.setRolloverIcon(uiImpl.getImage("audit_trails_mo.png"));
	FetchAuditTrails.setActionCommand(resourceBundle.getString("AuditTrails"));
   	FetchAuditTrails.setToolTipText(resourceBundle.getString("AuditTrails"));
	FetchAuditTrails.setBorderPainted(false);
	/*
	FindButton = toolBar.add(menuToolBarAction);

	   FindButton.setPreferredSize(new Dimension(35,35));
	   FindButton.setMinimumSize(new Dimension(35,35));
	   FindButton.setMaximumSize(new Dimension(35,35));
	   FindButton.setIcon(uiImpl.getImage("find.png"));
	   FindButton.setActionCommand(resourceBundle.getString("Find"));
	   FindButton.setToolTipText(resourceBundle.getString("Find"));
	*/
	HelpButton = toolBar.add(menuToolBarAction);
	
	   HelpButton.setPreferredSize(new Dimension(35,35));
	   HelpButton.setMinimumSize(new Dimension(35,35));
	   HelpButton.setMaximumSize(new Dimension(35,35));
	   HelpButton.setIcon(uiImpl.getImage("toolhelp.png"));
	   HelpButton.setRolloverIcon(uiImpl.getImage("toolhelp_mo.png"));
	   HelpButton.setActionCommand(resourceBundle.getString("Help Contents"));
	   HelpButton.setToolTipText(resourceBundle.getString("Help"));
	   HelpButton.setBorderPainted(false);
	ExitButton = toolBar.add(menuToolBarAction);

	   ExitButton.setPreferredSize(new Dimension(35,35));
	   ExitButton.setMinimumSize(new Dimension(35,35));
	   ExitButton.setMaximumSize(new Dimension(35,35));
	   ExitButton.setIcon(uiImpl.getImage("exit.png"));
	   ExitButton.setRolloverIcon(uiImpl.getImage("exit_mo.png"));
	   ExitButton.setActionCommand(resourceBundle.getString("Exit"));
	   ExitButton.setToolTipText(resourceBundle.getString("Exit"));
	   ExitButton.setBorderPainted(false);
	   JPanel toolBarPanel = new JPanel(new BorderLayout());
	   toolBarPanel.add(toolBar);
	   getContentPane().add(toolBarPanel,BorderLayout.NORTH);
	 
	
        }



public String getHelpURL()
{
	String helpdoc =null;
	String path = hostName+":"+port;
	path = path.replace('\\','/');
  	path = path+"/";   //No Internationalisation
  	String doc = "help/administrator_guide/security_management/security_administration.html";
  	doc = doc.trim();
  	if(doc.startsWith("../"))  //No Internationalisation
  	{
		doc = doc.substring(3);
  	}
  	if(!doc.endsWith(".html"))  //No Internationalisation
  	{
		int ptr = doc.indexOf(".html"); //No Internationalisation
		helpdoc = doc.substring(0,ptr+5);
  	}
  	else
  	{
		helpdoc = doc;
  	}
  	//File helpFile = new File(path+helpdoc);
	
        //Added by Balan for SSL Operation
        String strProtocolName = com.adventnet.nms.util.NmsClientUtil.getProtocolName(); 
        //return "http://"+path+doc; //No Internationalisation
        return strProtocolName+"://"+path+doc; //No Internationalisation
        //Add Ends
}



 	//Connection classes


  class RefreshButton_Datachange implements java.awt.event.ActionListener,java.io.Serializable
  {
	public void actionPerformed(java.awt.event.ActionEvent arg0)
	{	
		try{
			fireDataChanged();
			//System.out.println("fire data");
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
  }  













 
  

 



 
   



 

//<Begin__class_JTree1_JSplitPane1_conn1>

 class JTree1_JSplitPane1_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTree1_JSplitPane1_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
  
 groups="";
      Object obj = arg0.getSource();
       if((JTree1.getLastSelectedPathComponent() != null) && (obj instanceof JTree )){  
     TreePath tm =(TreePath) JTree1.getSelectionPath();
     int count = tm.getPathCount(); 
 
     if(count>2){ 
  groups = tm.getPathComponent(tm.getPathCount()-2).toString();
      } 
   
     user = JTree1.getLastSelectedPathComponent().toString();
// System.out.println("THE USER "+user+"THE GROUP "+groups);
    if((user.equals(resourceBundle.getString("Groups ")) || user.equals(resourceBundle.getString("Users ")) || user.equals(resourceBundle.getString("Security ")))){
  isUser = false;  
    }  
    if(user.equals(resourceBundle.getString("Users ")) || groups.equals(resourceBundle.getString("Users "))){
   if(SwingUtilities.isRightMouseButton(arg0))
      getadduser(user).show(JTree1,arg0.getX()+10,arg0.getY()+10); 
 if(groups.equals(resourceBundle.getString("Users ")))   
 isUser=true;  
     }
    if(user.equals(resourceBundle.getString("Groups ")) || groups.equals(resourceBundle.getString("Groups "))){
    if(SwingUtilities.isRightMouseButton(arg0))
     getaddgroup().show(JTree1,arg0.getX()+10,arg0.getY()+10);  
 if(groups.equals(resourceBundle.getString("Groups ")))   
 isUser=false;  
 
     }
      }  
       }
//<UserCode_End_Connection_JTree1_JSplitPane1_conn1>
 }//<End__class_JTree1_JSplitPane1_conn1>

 

boolean addgroupAuthorization = true;
  public JPopupMenu getaddgroup()

  {
   //<Begin_getaddgroup>
   //<End_getaddgroup>


   {
	addgroup = new JPopupMenu();
	if(user.equals(resourceBundle.getString("Groups "))){
      
      Add_GroupMenuItem = new JMenuItem(resourceBundle.getString("AddGroup"));
      Add_GroupMenuItem.setMnemonic('G');
      Add_GroupMenuItem.setActionCommand(resourceBundle.getString("AddGroup"));
      Add_GroupMenuItem.addActionListener(menuToolBarAction);
	Add_GroupMenuItem.setFont(new Font("SansSerif",0,12)); 	
	Add_GroupMenuItem.setEnabled(addgroupAuthorization);
      addgroup.add(Add_GroupMenuItem);
	}
	else{
	/*
      addgroup.addSeparator();
      Assign_UsersMenuItem = new JMenuItem(resourceBundle.getString("Assign Users"));
      Assign_UsersMenuItem.setMnemonic('U');
      Assign_UsersMenuItem.setActionCommand(resourceBundle.getString("Assign Users"));
      Assign_UsersMenuItem.addActionListener(menuToolBarAction);
      addgroup.add(Assign_UsersMenuItem);
	Assign_UsersMenuItem.setFont(new Font("SansSerif",0,12)); 	
	*/
 //addgroup.addSeparator();
DeleteMenuItem2 = new JMenuItem(resourceBundle.getString("Delete"));
      DeleteMenuItem2.setActionCommand(resourceBundle.getString("Delete"));
      DeleteMenuItem2.addActionListener(menuToolBarAction);
	DeleteMenuItem2.setFont(new Font("SansSerif",0,12)); 		
      addgroup.add(DeleteMenuItem2);
	}
	
   }
   return addgroup;

  }




  

  




  

  


    //boolean to dispose AuthMain, if already one is available
    private boolean disposeMe = false;

  public AuthMain()
  {
      //already AuthMain is loaded, so set disposeMe to true, so that this
      //instace can be disposed in setVisible method  
      if (main != null)
      {
          disposeMe = true;
          return;
      }
    //<Begin_AuthMain>
    pack();
  
    //<End_AuthMain>
  }

  public AuthMain(java.applet.Applet applet)
  {
    //<Begin_AuthMain_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_AuthMain_java.applet.Applet>
  }

String hostName="";
String port ="";
//Constructor for invoking the SecurityUI as standalone.
	
	public AuthMain(String se_model, String builder_impl, java.util.Properties prop)
    {
	if(prop.get("standalone") != null)
	{
		rmistandalone = ((String)prop.get("standalone")).equals("true")?true:false;			
	}

	if(se_model != null)	
 	    {
 		try 
		{
		    Class cl = Class.forName(se_model); 
		    model = (com.adventnet.security.ui.AbstractSecurityModel)cl.newInstance();
	 	    model.init(prop);	
		    hostName = (String)prop.get("HOST");
		    port = (String)prop.get("HTTP_PORT");
		}
		catch(Exception e)
		    {
		    }	
            }
	else
	    {
		model = new com.adventnet.nms.security.ui.NmsSecurityModelRMIImpl();
	    }
        if(builder_impl != null)
	    {
		try 
		{
		    Class cl = Class.forName(builder_impl); 
		    uiImpl = (com.adventnet.nms.util.CommonBuilderUIInterface)cl.newInstance();
		    uiImpl.initialize(prop);	 
		}
		catch(Exception e)
		{
			
		}
	    }	
	else
	     {
		//uiImpl = new com.adventnet.nms.runtimeconfig.CommonBuilderUIStandAloneImpl();
	     }

       //model.registerWithModel(this);
	//groupflag = true;	
	standalone = true;	
	
    }
 
	

//<Begin__class_JTree1_CardPanel1_conn>

 class JTree1_CardPanel1_conn implements javax.swing.event.TreeSelectionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTree1_CardPanel1_conn>



     //Listener Interface Methods Are Added Below 


     public synchronized void valueChanged( javax.swing.event.TreeSelectionEvent arg0)
     {
  
 Object obj = arg0.getSource();
       if((JTree1.getLastSelectedPathComponent() != null) && (obj instanceof JTree )){  
     TreePath tm =(TreePath) JTree1.getSelectionPath();
     int count = tm.getPathCount(); 
  
     if(count>2){ 
  groups = tm.getPathComponent(tm.getPathCount()-2).toString();
      } 
   
     user = JTree1.getLastSelectedPathComponent().toString();

    if((user.equals(resourceBundle.getString("Groups ")) || user.equals(resourceBundle.getString("Users ")) || user.equals(resourceBundle.getString("Security ")))){
                       CardPanel1.showCard("label"); 

    }
    else if(groups.equals(resourceBundle.getString("Users "))){

      CardPanel1.showCard("tab1");
     pane1 = (com.adventnet.security.ui.TabbedPane1)CardPanel1.getCard("tab1");   
     pane1.setGroupData(model.getAllGroupsForUser(user),setGroups(),user);

     pane1.setOperations(model.getOperations(user));      
     pane1.setAudit(model.getAudit(user));                            
      path1 = JTree1.getSelectionPath();    
     Hashtable useratb = model.getUserAttributes(user);
//System.out.println(" USER ATB : "+useratb);
  int userage =0;
  int pwdage =0; 
  String userStatus = (String) useratb.get("status");
    
  if(useratb.get("userexpirytime") != null)
   userage = new Integer(useratb.get("userexpirytime").toString()).intValue();
  if(useratb.get("passwdexpirytime") != null)
   pwdage =  new Integer(useratb.get("passwdexpirytime").toString()).intValue();
  String descriptiveName = (String)useratb.get("descriptivename");
  pane1.setUserAttributes(userage,pwdage, userStatus,descriptiveName);    

     }
    else if(groups.equals(resourceBundle.getString("Groups "))){
    
     CardPanel1.showCard("tab2");
     pane2=(com.adventnet.security.ui.TabbedPane2)CardPanel1.getCard("tab2");   
     pane2.setUserData(model.getUsersForGroup(user),setUsers(),user);    
     java.util.Vector vec = new java.util.Vector();  

    pane2.setCustomView( setCVS(), vec);
    pane2.setCustomData( vec, model.getAllAuthScopes(), user);
    
     vec.add(user); 

     path1 = JTree1.getSelectionPath();    

      pane2.setOperations(model.getOperationsForGroup(vec));
      

     }
       }    
     }
//<UserCode_End_Connection_JTree1_CardPanel1_conn>
 }//<End__class_JTree1_CardPanel1_conn>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

	private void refresh()
	{
	    Thread refreshThread = new Thread(new Runnable()
				   {

				       	public void run()
				       	{
					    disableButtons();
					    boolean prevStat = model.isInitialized();
					    model.refetchData();
		                            int count = 0;
		                            while (!model.isInitialized())
					    {
						try
						{
						    Thread.sleep(50);
						    count++;

						    if (count > 100)
						    {
							break;
						    }
						}
						catch (Exception e)
						{
						}
					    }

					    if(!model.isInitialized())
					    {
						enableButtons();
						showError("com.adventnet.security.ui.dataFetchError"); //No I18N
					    }
					    else if(!prevStat)
					    {
						enableMenuOptions(true);
						fireDataChanged();
					    }
					    enableButtons();
					}
				    });
	    refreshThread.start();
    	}
}























