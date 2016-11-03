
//$Id: TreeConfig.java,v 1.4 2008/09/16 07:21:50 jeyaprakash Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$10
//<End_Version>
package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.beans.utilbeans.*;
import com.adventnet.nms.util.*;
import javax.swing.tree.*;
import java.util.*;
import com.adventnet.nms.util.CommonBuilderUIInterface;


public class TreeConfig extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTree JTree1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JButton JLabel3 = null;
	javax.swing.JButton JLabel2 = null;
	//<End_Variable_Declarations>

           public boolean MODIFY = false;
	 HelpWindow help = null;
	private String name = "";
	
	private Hashtable modifyNot = null;
	private Hashtable storedHash = null;
	private Hashtable fixRender = null;
	private boolean flag = false;  // Flag used in PrepareForRendering Method to get the currently selected node

  public TreeConfig()
  {
    //<Begin_TreeConfig>
    pack();
  
    //<End_TreeConfig>
  }

  public TreeConfig(java.applet.Applet applet)
  {
    //<Begin_TreeConfig_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_TreeConfig_java.applet.Applet>
  }
	
  public TreeConfig( Frame owner, java.applet.Applet applet)
  {
    super(owner);	 
    this.applet = applet;
    pack();
    this.setTitle("TreeConfig");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }	
  
  
  public TreeConfig( Dialog owner, java.applet.Applet applet)
  {
       super(owner);
       this.applet = applet;
       pack();
       this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
        this.setSize(getPreferredSize().width+478,getPreferredSize().height+564); 
          setTitle(resourceBundle.getString("TreeConfig"));
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
	setTitle(resourceBundle.getString("Assign Permissions"));
	if(!AuthMain.standalone)
	{
		JTree1.setRootVisible(false);
	}
	else
	{
		JTree1.setRootVisible(true);
	}	
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double wid = screenSize.getWidth();
 	double hgt = screenSize.getHeight();
	setLocation((int)wid/2,( (int)hgt/2 - (int)hgt/4));	
	customizeTree();	
	//JLabel3.setIcon(AuthMain.getBuilderUiIfInstance().getImage("expandit_small.png"));
	JLabel3.setIcon(AuthMain.getBuilderUiIfInstance().getImage("collapseit_small.png"));
	//JLabel2.setIcon(AuthMain.getBuilderUiIfInstance().getImage("handup.png"));	
	//setIconImage(AuthMain.getBuilderUiIfInstance().getFrameIcon());	
		
  } 
//public AbstractSecurityModel model = null;

public void setOperationTree()
	{
	 JTree1.setModel(AuthMain.model.getTreeModel());
	JTree1.setCellRenderer(new CustomTreeRenderer());	
             for(int i=1;i<JTree1.getRowCount();i++)
	  	 JTree1.expandRow(i);
             ToolTipManager.sharedInstance().registerComponent(JTree1); 
           
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
            JScrollPane1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Allow / Disallow")));
            JScrollPane1.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane1>

//<UserCode_End_Bean_JScrollPane1>

          try
          {
            JTree1.setDoubleBuffered(true);
            JTree1.setLargeModel(true);
            JTree1.setRootVisible(false);
            JTree1.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTree1,ex); 
          }

//<UserCode_Begin_Bean_JTree1>

//<UserCode_End_Bean_JTree1>

          try
          {
            JButton2.setText(resourceBundle.getString("Reset"));
            JButton2.setToolTipText(resourceBundle.getString("Reset"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>
JButton2.setMnemonic('R');
//<UserCode_End_Bean_JButton2>

          try
          {
            JButton1.setText(resourceBundle.getString("Done"));
            JButton1.setEnabled(false);
            JButton1.setToolTipText(resourceBundle.getString("Done"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JButton3.setText(resourceBundle.getString("Cancel"));
            JButton3.setToolTipText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>

//<UserCode_End_Bean_JButton3>

          try
          {
            JLabel1.setHorizontalAlignment(0);
            JLabel1.setFont(new Font("Dialog",1,14));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setBackground(new Color(-1));
            JLabel1.setText(resourceBundle.getString("Permissions tree hierarchy        "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel3.setToolTipText(resourceBundle.getString("Collapse"));
            JLabel3.setPreferredSize(new Dimension(15,24));
            JLabel3.setFocusPainted(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel2.setFont(new Font("Dialog",1,14));
            JLabel2.setForeground(new Color(-16777216));
            JLabel2.setHorizontalAlignment(0);
            JLabel2.setPreferredSize(new Dimension(15,24));
            JLabel2.setFocusPainted(false);
            JLabel2.setToolTipText(resourceBundle.getString("Help"));
            JLabel2.setText(resourceBundle.getString("?"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

          JLabel2.setText("");//NO I18N
          JLabel2.setIcon(AuthMain.getBuilderUiIfInstance().getImage("help_contextual.png"));//NO I18N

//<UserCode_End_Bean_JLabel2>
		JLabel2.setPreferredSize(new Dimension(JLabel2.getPreferredSize().width+12,JLabel2.getPreferredSize().height+0));
		JLabel3.setPreferredSize(new Dimension(JLabel3.getPreferredSize().width+12,JLabel3.getPreferredSize().height+0));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+70,JLabel1.getPreferredSize().height+1));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+0,JPanel2.getPreferredSize().height+7));
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+30,JButton3.getPreferredSize().height+1));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+40,JButton1.getPreferredSize().height+1));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+36,JButton2.getPreferredSize().height+1));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+209,JPanel1.getPreferredSize().height+0));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+297,JScrollPane1.getPreferredSize().height+13));

  
          //<End_setUpProperties>
		javax.swing.border.TitledBorder titledBorder = (javax.swing.border.TitledBorder)(JScrollPane1.getBorder());
		if(titledBorder != null)
		{
			titledBorder.setTitle(resourceBundle.getString("Allow / Disallow"));
		}
		
		KeyStroke f1Stroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1,0);
		KeyStroke escStroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE,0);  
		KeyStroke enterStroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER,0); 
   		((JComponent) getRootPane()).registerKeyboardAction(new EscListener(), "HELP", f1Stroke, 2 );
   		((JComponent) getRootPane()).registerKeyboardAction(new EscListener(), "ESCAPE", escStroke, 2 );
   		((JComponent) getRootPane()).registerKeyboardAction(new EscListener(), "ENTER", enterStroke, 2 );
  }
  
class EscListener implements java.awt.event.ActionListener, java.io.Serializable {
	public void actionPerformed( java.awt.event.ActionEvent arg0) {
	     	String actionCommand = arg0.getActionCommand().toString(); 
		if(actionCommand.equals("HELP"))  {
			showHelp();
		} else if (actionCommand.equals("ESCAPE")) {
		     	if (help == null || !help.isVisible()) {
				close(); 
		     	} else if (help!= null ) help.dispose(); 
		} else if (actionCommand.equals("ENTER")) {
		     	if (JButton1.isEnabled()) 	done();   
		}
	}
}
  
  public void showHelp() {
	if(help != null)  help.dispose();
 
 	if (modify)  help = new HelpWindow(true);
	else help = new HelpWindow();

	help.setSize(350, 280);
  
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double wid = screenSize.getWidth();
	double hgt = screenSize.getHeight();
	help.setLocation((int)wid/2+ 100,( (int)hgt/2 - (int)hgt/4 + 100)); 
  
	String str = "";
	if(name1.equals("Users ")) {
		str = "<html><body><p>"+resourceBundle.getString("security.ui.help.user.text")  +"</p></body></html>"; 
		help.display(str, resourceBundle.getString("Assigning permissions for a user")); 
	} else {
		str = "<html><body><p>"+resourceBundle.getString("security.ui.help.group.text")  +"</p></body></html>"; 
		help.display(str, resourceBundle.getString("Assigning permissions for a group")); 
	}
	help.setVisible(true); 
 }
  
  public void done() {
       
	if(help != null) help.dispose();   
  	if( (modifiedcount != 0) ) {
		if( JOptionPane.showConfirmDialog(null,resourceBundle.getString("The changes made here will affect the permissions of the other\n users who are associated to the selected groups\nDo you want to continue ?"),
                                         resourceBundle.getString("Warning!"),
                                         JOptionPane.YES_NO_OPTION,
                                         JOptionPane.WARNING_MESSAGE,
                                          null) == JOptionPane.NO_OPTION)  {
				return;   
		} 
	 }  

	if(name1.equals("Groups ")) { 
		AuthMain.main.disableButtons();  
		AuthMain.model.addGroupOperData(name,null,operhash);  
	} else if(name1.equals("Users ") && !name.equals("")) {  
		Vector vec = AuthMain.model.getGroupsForUser(name);
		AuthMain.main.disableButtons();  
		AuthMain.model.modifyOper_new(name,operhash);
	}
 
	setVisible(false);
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
	if(false)		
		{
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTree1= new javax.swing.JTree();
        JPanel1= new javax.swing.JPanel();
        JButton2= new javax.swing.JButton();
        JButton1= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        JPanel2= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel3= new javax.swing.JButton();
        JLabel2= new javax.swing.JButton();

  
        //<End_initVariables>
		}

      Top= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JPanel1= new javax.swing.JPanel();
        JButton2= new javax.swing.JButton();
        JButton1= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        JPanel2= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel3= new javax.swing.JButton();
        JLabel2= new javax.swing.JButton();
		
		
       Object previousExpanded = UIManager.put("Tree.expandedIcon", AuthMain.getBuilderUiIfInstance().getImage("expand3.png"));
       Object previousCollapsed = UIManager.put("Tree.collapsedIcon", AuthMain.getBuilderUiIfInstance().getImage("collapse3.png"));
       JTree1= new javax.swing.JTree();
       UIManager.put("Tree.expandedIcon", previousExpanded);
       UIManager.put("Tree.collapsedIcon", previousCollapsed);
       JTree1.putClientProperty("JTree.lineStyle", "Angled");		
       //ToolTipManager.sharedInstance().registerComponent(JTree1); 
       
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTree1);
Top.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new FlowLayout(2,5,5));
JPanel1.add(JButton2);
JPanel1.add(JButton1);
JPanel1.add(JButton3);
Top.add(JPanel2,BorderLayout.NORTH);
JPanel2.setLayout(new FlowLayout(2,20,3));
JPanel2.add(JLabel1);
JPanel2.add(JLabel3);
JPanel2.add(JLabel2);

  
//<End_setUpGUI_Container>


  } 

public void recursivelySelectChildren(DefaultMutableTreeNode node, int select)
 {
		
	  for(int i=0;i<node.getChildCount();i++) {
	 	DefaultMutableTreeNode child =(DefaultMutableTreeNode)( (( DefaultTreeModel) JTree1.getModel()).getChild(node,i)); 
	 	TreeObject tob = (TreeObject)child.getUserObject(); 
	 	if (modifyAllowed(tob)){
		if(select == 0) {
		      tob.setInt(0); 
		      //operhash.remove(tob.getString());
		      operhash.remove(tob.getString());
		} else if(select ==1) 
{
		      tob.setInt(1); 
		      operhash.remove(tob.getString());
				
		} else if(select ==2)
 {
			tob.setInt(2);     		
			operhash.remove(tob.getString());
 
		}
		
		if(!child.isLeaf())
 {
			recursivelySelectChildren(child,select);
		}
		}
	   }
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

public void customizeTree()
	{
		

   		DefaultMutableTreeNode   Event, addE, delE, Alert, addA, delA, View, addV, delV ,
            	Network, Node, addN, delN, addn, deln, Printer, addP, delP;
		DefaultMutableTreeNode root  = new DefaultMutableTreeNode("Operation Tree");

    	           DefaultTreeModel model = new DefaultTreeModel(root);
		JTree1.setModel(model);
				
	}
 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      JLabel3_JLabel3_conn1 JLabel3_JLabel3_conn11 =  new JLabel3_JLabel3_conn1();
      JLabel3.addMouseListener(JLabel3_JLabel3_conn11);
      JLabel2_JLabel2_conn JLabel2_JLabel2_conn1 =  new JLabel2_JLabel2_conn();
      JLabel2.addKeyListener(JLabel2_JLabel2_conn1);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JPanel1_JPanel1_conn1 JPanel1_JPanel1_conn11 =  new JPanel1_JPanel1_conn1();
      JPanel1.addMouseListener(JPanel1_JPanel1_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      JButton3.addActionListener(JButton3_JButton3_conn11);
      JTree1_JTree1_conn2 JTree1_JTree1_conn21 =  new JTree1_JTree1_conn2();
      JTree1.addMouseMotionListener(JTree1_JTree1_conn21);
      JPanel2_JPanel2_conn1 JPanel2_JPanel2_conn11 =  new JPanel2_JPanel2_conn1();
      JPanel2.addMouseListener(JPanel2_JPanel2_conn11);
      JTree1_JTree1_conn1 JTree1_JTree1_conn11 =  new JTree1_JTree1_conn1();
      JTree1.addMouseListener(JTree1_JTree1_conn11);
      JLabel2_JLabel2_conn1 JLabel2_JLabel2_conn11 =  new JLabel2_JLabel2_conn1();
      JLabel2.addMouseListener(JLabel2_JLabel2_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
  }
 
java.util.Hashtable  useroperations;
//java.util.Hashtable operhash;	
java.util.Hashtable newhash;
java.util.Vector storeviews = null;	
DefaultTreeModel tempModel = null;
private boolean modify = false;	
private Hashtable myHash;	
private Hashtable temphash4;	
public void prepareForRendering(Vector viewsforuser)
	{
	   modifyNot = new Hashtable();
	   fixRender = new Hashtable();
	    storeviews = viewsforuser;
	    newhash = new java.util.Hashtable();
	    tempModel =  AuthMain.model.getTreeModel();
	    JTree1.setModel(tempModel);
 
  	    Hashtable temphash1 = new Hashtable();
	    Hashtable temphash2 = new Hashtable();
	    Hashtable temphash3 = new Hashtable();
	    //Hashtable to store the operations for only Group views to fix issue for Ericsson
	    //to disable (make it non-editable) the operations with conflicting permissions
	    //with that of any Group associated with user. Idelly the fix should be on the
	    //server side to avoid conflicting permissions. This is a temporary fix and can be
	    //removed when the actual root cause is addressed. Sorry about one more Hashtable!
	    //Hashtable temphash4 = new Hashtable();
	    temphash4 = new Hashtable();
	    Hashtable fixHash = new Hashtable();
	    Hashtable viewOper = new Hashtable();
	    for(int i=0;i< viewsforuser.size(); i++)
	   	{

		    String viewname = (String)viewsforuser.elementAt(i);
		    if(name1.equals("Users ") && viewname.equals("default "+name+" View"))
			{
				modify = true;	
			}
	   	    temphash2 = AuthMain.model.getViewOperations(viewname);

	   	    for(Enumeration e = temphash2.keys(); e.hasMoreElements();)
	    	   	{
				String key =e.nextElement().toString();
				String value = temphash2.get(key).toString();
				temphash3.put( key, value);
				//As per the flow modifying the code ...
				if(name1.equals("Users ")&& !viewname.equals("default "+name+" View"))
				{
				  temphash4.put( key, value);
				}
			    //This means it is in the different view ,so do a union of
			    //operations ...
			    if(fixHash.containsKey(key))
			    {
				    if(value.equals("1"))
				    {
				    	temphash1.put(key,value);
				    }
					    
				    fixRender = (Hashtable)temphash2.clone();
				    constructUserOperationHashFix((DefaultMutableTreeNode)((DefaultTreeModel)JTree1.getModel()).getRoot(),fixHash,key,viewOper,viewname);
				    renderOpTree((DefaultMutableTreeNode)tempModel.getRoot());
			     }
			     //This means in the same view so do a intersection of 
			     //operations ...
			     else
			     {
				     temphash1.put(key,value);
				     useroperations = temphash1;
				     constructUserOperationHash((DefaultMutableTreeNode)((DefaultTreeModel)JTree1.getModel()).getRoot());
				     renderOpTree((DefaultMutableTreeNode)tempModel.getRoot());
				     fixHash.put(key,value);
 
				     viewOper.put(key,viewname);
			     }
 			}
		    useroperations = temphash1;
  
		    constructUserOperationHash((DefaultMutableTreeNode)((DefaultTreeModel)JTree1.getModel()).getRoot());
		    renderOpTree((DefaultMutableTreeNode)tempModel.getRoot());
		    

		}

	if(modify ){
		for(Enumeration en =temphash1.keys();en.hasMoreElements();){
			String inc = en.nextElement().toString();
			if(temphash2.containsKey(inc) && temphash2.get(inc).equals("0") && temphash1.get(inc).equals("1"))
					temphash2.put(inc,"1");
		}
		for(Enumeration en =temphash2.keys();en.hasMoreElements();)
		{
			String inc = en.nextElement().toString();

			if(temphash4.containsKey(inc))
			{
				temphash2.remove(inc);
			}
			else
			{
				flag = false;
				DefaultMutableTreeNode treeNode = null;
				DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)tempModel.getRoot();
				for(Enumeration en1 = rootNode.children(); en1.hasMoreElements();)
				{
					treeNode = (DefaultMutableTreeNode)en1.nextElement();
					TreeNode[] treeHierarchy = getNodeHierarchy(treeNode,inc);
					if(treeHierarchy != null)
					{
						for(int i=treeHierarchy.length-1;i>=0;i--)
						{
							String parentNode = treeHierarchy[i].toString();
							if(parentNode.equals("Operation Tree Root"))
							{
								break;
							}
							else if(temphash4.containsKey(parentNode))
							{
								temphash2.remove(inc);
							}
						}
					}
				}
			}
		}
		modifyNot = (Hashtable)temphash2.clone();
		
		myHash = temphash2;	
	}
		useroperations = temphash1;
	  	//operhash = temphash2;
		operhash = temphash3;
		newhash = temphash3;
		if(name1.equals("Users "))
 filter();
		if(viewsforuser.size() != 1)

 {
			constructUserOperationHash1((DefaultMutableTreeNode)((DefaultTreeModel)JTree1.getModel()).getRoot());
			constructUserOperationHash((DefaultMutableTreeNode)((DefaultTreeModel)JTree1.getModel()).getRoot());
		}
		renderOpTree1((DefaultMutableTreeNode)tempModel.getRoot());
		renderOpTree((DefaultMutableTreeNode)tempModel.getRoot()); 	}
	
	//Mymethod.
	private void filter() {
		operhash = myHash; 	}
private TreeNode[] getNodeHierarchy(DefaultMutableTreeNode node, String nodeName)
{
	TreeNode[] treeNodeInstance = null;

	DefaultMutableTreeNode treeNode = node;
	for(Enumeration en1 = treeNode.children(); en1.hasMoreElements();)
	{
		if(flag)
		{
			break;
		}
		treeNode = (DefaultMutableTreeNode)en1.nextElement();
		String currNodeName = ((TreeObject)treeNode.getUserObject()).getString();
		if(nodeName.equals(currNodeName))
		{
			flag = true;                
			return treeNode.getPath();
		}
		if(!treeNode.isLeaf())
		{
			treeNodeInstance = getNodeHierarchy(treeNode,nodeName);
		}            
	}
	return treeNodeInstance;
}
	
public void constructUserOperationHash(DefaultMutableTreeNode parentnode)
	{
		
 	  DefaultMutableTreeNode parent = parentnode ;
	  DefaultMutableTreeNode child = null;	
       	  for(int i=0;i<parent.getChildCount();i++)
	  	{
	 	      child = (DefaultMutableTreeNode)tempModel.getChild(parent,i);
                              //String userob = ((TreeObject)child.getUserObject()).toString();
		String userob = ((TreeObject)child.getUserObject()).getString();
		         if(useroperations.containsKey(userob))
			 {
                   		   if(useroperations.get(userob).equals("1"))
			      	{
				         if(!child.isLeaf())
					{
				  	 for(int j=0;j<child.getChildCount();j++)
				    		{
						  DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
		 				  //String userob1 = ((TreeObject)childofchild.getUserObject()).toString();
						String userob1 = ((TreeObject)childofchild.getUserObject()).getString();
				   		  if(useroperations.containsKey(userob1) && useroperations.get(userob1).equals("0"))
					 	   {
		           			         		useroperations.put(userob1, "0");
					 	   }
						else
					 	   {
					                        useroperations.put(userob1, "1");
						   }	
				    		}
			         		}
			 	}
         		 				
		 	 }
		      constructUserOperationHash(child);
	 	  }	
	
	}

private boolean modifyAllowed(TreeObject tob) {
     
     if (modify) 
     if (operhash != null && !operhash.contains(tob.getString()))
     if ( (useroperations.containsKey(tob.getString()) && !modifyNot.containsKey(tob.getString())) || 
     	(modifyNot.containsKey(tob.getString()) && !useroperations.containsKey(tob.getString()))) return false;
     return true;
}


private void setInt(TreeObject tob, int type) {

     String name = tob.getString();

     if (modify)  {
               if (modifyAllowed(tob)) {
                             	if (type == 0) tob.setInt(0);
                             	else if (type == 2) tob.setInt(2);
               	else if (type == 1) tob.setInt(1);

          	} else {
		if (type == 0 ) tob.setInt(0);
		else if (type == 2) tob.setInt(4);
		else if (type == 1) tob.setInt(3);
	}

     } else tob.setInt(type);

     

}

public void renderOpTree(DefaultMutableTreeNode parentnode)
 {


	for(int i=0;i<JTree1.getRowCount();i++)
 	JTree1.expandRow(i); 
 	DefaultMutableTreeNode parent = parentnode ;
	DefaultMutableTreeNode child = null;	
	for(int i=0;i<parent.getChildCount();i++)
 {
 		child = (DefaultMutableTreeNode)tempModel.getChild(parent,i);
   		String userob = ((TreeObject)child.getUserObject()).getString();
	  	if(useroperations.containsKey(userob))
 {
	  	     	if(useroperations.get(userob).equals("1"))
 {
	  	     	     	setInt((TreeObject)child.getUserObject(),1);
	  	     	     	
				if(!child.isLeaf()) {
				  	for(int j=0;j<child.getChildCount();j++)
 {
			 			DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
						String userob1 = ((TreeObject)childofchild.getUserObject()).getString();
						setInt((TreeObject)childofchild.getUserObject(),1);
						if(useroperations.containsKey(userob1))
 {
					        		if(useroperations.get(userob).equals("0"))
 
					        			setInt((TreeObject)childofchild.getUserObject(),2);
					   	} else useroperations.put(userob1,"1");
 				     	} 
			    	}
			   } else if(useroperations.get(userob).equals("0"))
 {
			        	setInt((TreeObject)child.getUserObject(),2);
				if(!child.isLeaf()) {
				     for(int j=0;j<child.getChildCount();j++)
 {
			 		 DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
			 		 String userob1 = ((TreeObject)childofchild.getUserObject()).getString();
			 		 setInt((TreeObject)childofchild.getUserObject(),2);
					 if(useroperations.containsKey(userob1) && useroperations.get(userob1).equals("1"))
					 {
						 //useroperations.put(userob1,"1");
						 if(temphash4.containsKey(userob1))
						 {
						 	modifyNot.remove(userob1);
						 }
					 }
					 else
					 {
						 useroperations.put(userob1,"0");
					 }
	 			       }
				}
			   }
		} else
 {
			setInt((TreeObject)child.getUserObject(),0);
		}
 		renderOpTree(child);
	}
}


	//My Methods.
	
	public void constructUserOperationHash1(DefaultMutableTreeNode parentnode)
	{
		
 	  DefaultMutableTreeNode parent = parentnode ;
	  DefaultMutableTreeNode child = null;	
       	  for(int i=0;i<parent.getChildCount();i++)
	  	{
	 	      child = (DefaultMutableTreeNode)tempModel.getChild(parent,i);
                              //String userob = ((TreeObject)child.getUserObject()).toString();
   		String userob = ((TreeObject)child.getUserObject()).getString();
		         if(modifyNot.containsKey(userob))
			 {
                   		   if(modifyNot.get(userob).equals("1"))
			      	{
				         if(!child.isLeaf())
					{
				  	 for(int j=0;j<child.getChildCount();j++)
				    		{
						  DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
		 				  //String userob1 = ((TreeObject)childofchild.getUserObject()).toString();
						String userob1 = ((TreeObject)childofchild.getUserObject()).getString();
				   		  if(modifyNot.containsKey(userob1) && modifyNot.get(userob1).equals("0"))
					 	   {
		           			         		modifyNot.put(userob1, "0");
					 	   } else
 {
					                        modifyNot.put(userob1, "1");
						   }
				    		}
			         		}
			 	}
		 	 }
		      constructUserOperationHash1(child);
	 	  }
	}


public void renderOpTree1(DefaultMutableTreeNode parentnode)
 {

	for(int i=0;i<JTree1.getRowCount();i++)
 JTree1.expandRow(i);
 
	 
 	DefaultMutableTreeNode parent = parentnode ;
	DefaultMutableTreeNode child = null;	
             	for(int i=0;i<parent.getChildCount();i++)
 {
		child = (DefaultMutableTreeNode)tempModel.getChild(parent,i);
		String userob = ((TreeObject)child.getUserObject()).getString();
	  	if(modifyNot.containsKey(userob))
 {
			if(modifyNot.get(userob).equals("1"))
 {
 				setInt((TreeObject)child.getUserObject(),1);
				if(!child.isLeaf()) {
					for(int j=0;j<child.getChildCount();j++)
 {
						DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
						setInt((TreeObject)childofchild.getUserObject(),0);
						String userob1 = ((TreeObject)childofchild.getUserObject()).getString();
						setInt((TreeObject)childofchild.getUserObject(),1);
						if(modifyNot.containsKey(userob1))
 {
							if(modifyNot.get(userob).equals("0"))
 {
							     	modifyNot.put(userob1,"0");
								setInt((TreeObject)childofchild.getUserObject(),2);
							}
					   	}
 else modifyNot.put(userob1,"1");
 				     	}
			    	}
		  	} else if(modifyNot.get(userob).equals("0")) {	
				setInt((TreeObject)child.getUserObject(),2);
				if(!child.isLeaf()) {
					for(int j=0;j<child.getChildCount();j++)
 { 						DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
						setInt((TreeObject)childofchild.getUserObject(),2);
						String userob1 = ((TreeObject)childofchild.getUserObject()).getString();		
						modifyNot.put(userob1,"0");
	 			            	}
				}
			}
		}
 else
 {
			setInt((TreeObject)child.getUserObject(),0);
		}
		renderOpTree1(child);	
	
	}
	
}

public void constructUserOperationHashFix(DefaultMutableTreeNode parentnode,Hashtable deleteHash,String operName,Hashtable viewOperHash,String viewName)
	{
		
 	  DefaultMutableTreeNode parent = parentnode ;
	  DefaultMutableTreeNode child = null;	
       	  for(int i=0;i<parent.getChildCount();i++)
	  	{
	 	      child = (DefaultMutableTreeNode)tempModel.getChild(parent,i);
                              //String userob = ((TreeObject)child.getUserObject()).toString();
		String userob = ((TreeObject)child.getUserObject()).getString();
		         if(useroperations.containsKey(userob))
			 {
                   		   if(useroperations.get(userob).equals("1"))
			      	{
				         if(!child.isLeaf())
					{
				  	 for(int j=0;j<child.getChildCount();j++)
				    		{
						  DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
		 				  //String userob1 = ((TreeObject)childofchild.getUserObject()).toString();
						String userob1 = ((TreeObject)childofchild.getUserObject()).getString();
				   		  if(useroperations.containsKey(userob1) && useroperations.get(userob1).equals("0"))
					 	   {
							   if(deleteHash.containsKey(userob1))
							   {
									  if(deleteHash.get(userob1).equals("0") && useroperations.get(userob1).equals("0") && userob1.equals(operName))
									  {
										  useroperations.put(userob1,"0");
									  }
									  else
									  {	
										if(!fixRender.containsKey(userob1) || operName.equals(userob1))
										{
									  			useroperations.put(userob1,"1");
										}

										else if(viewOperHash.containsKey(userob1) && viewOperHash.get(userob1).equals(viewName) && viewOperHash.containsKey(userob) && !viewOperHash.get(userob).equals(viewName))
										{
											useroperations.put(userob1,"1");
										}
									  }
							   }else if (fixRender.containsKey(userob) && fixRender.get(userob).equals("1"))//No I18N
							   {
								    // the parent is enabled but children are disabled
								    if (useroperations.get(userob1).equals("0"))
								    {
									    useroperations.put(userob1,"1");
									    fixRender.put(userob1,"1");
								    }
							   }
					 	   }
						else
					 	   {
					                        useroperations.put(userob1, "1");
						   }	
				    		}
			         		}
			 	}
		 	 }
		      constructUserOperationHashFix(child,deleteHash,operName,viewOperHash,viewName);
	 	  }
	}
		
	//New method to maitain the cache while adding the operations to use directly.
	public void renderByHash(Hashtable storedHash)
	{
	
		if(storedHash == null)
		{
			storedHash = new Hashtable();
		}	
		this.storedHash = storedHash;
		if(storedHash.size() != 0)
		{
			operhash = (Hashtable)storedHash.clone();
			renderOpHash((DefaultMutableTreeNode)tempModel.getRoot());
		}	
		
	}
	
	
public void renderOpHash(DefaultMutableTreeNode parentnode)
 {

	for(int i=0;i<JTree1.getRowCount();i++)
 JTree1.expandRow(i);
 
	
 	DefaultMutableTreeNode parent = parentnode ;
	DefaultMutableTreeNode child = null;	
	for(int i=0;i<parent.getChildCount();i++)
 {
		child = (DefaultMutableTreeNode)tempModel.getChild(parent,i);
		String userob = ((TreeObject)child.getUserObject()).getString();
		if(storedHash.containsKey(userob)) {
			setInt((TreeObject)child.getUserObject(),0);
			if(storedHash.get(userob).equals("1"))
 {
				setInt((TreeObject)child.getUserObject(),1);
				if(temphash4.containsKey(child.getUserObject().toString()))
				{
					if(Integer.parseInt(temphash4.get(child.getUserObject().toString()).toString()) == 1)
					{
						setInt((TreeObject)child.getUserObject(),Integer.parseInt(temphash4.get(child.getUserObject().toString()).toString()));
					}
					else
					{
						setInt((TreeObject)child.getUserObject(),2);
					}
				}
				if(!child.isLeaf())
 {
					for(int j=0;j<child.getChildCount();j++)
 {
						DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
						setInt((TreeObject)childofchild.getUserObject(),0);
						String userob1 = ((TreeObject)childofchild.getUserObject()).getString();
						setInt((TreeObject)childofchild.getUserObject(),1);
						if(storedHash.containsKey(userob1))
 {
							if(storedHash.get(userob).equals("0"))
								setInt((TreeObject)childofchild.getUserObject(),2);
						   }
 else storedHash.put(userob1,"1");	
 				     	}
 
			    	}
		  	} else if(storedHash.get(userob).equals("0"))
 {
				setInt((TreeObject)child.getUserObject(),2);
				if(temphash4.containsKey(child.getUserObject().toString()))
				{
					setInt((TreeObject)child.getUserObject(),Integer.parseInt(temphash4.get(child.getUserObject().toString()).toString()));
					useroperations.remove(child.getUserObject().toString());
				}
				if(!child.isLeaf())
 {
					for(int j=0;j<child.getChildCount();j++)
 {
			 			 DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
						 setInt((TreeObject)childofchild.getUserObject(),2);
 						String userob1 = ((TreeObject)childofchild.getUserObject()).getString();		
						storedHash.put(userob1,"0");
					}
				}
			}
		} else if(!useroperations.containsKey(userob))
 {
			setInt((TreeObject)child.getUserObject(),0);
		}
		renderOpHash(child);
	 }
}
	//End for My Methods.
	
	public void resetChanges()
	{
		prepareForRendering(storeviews);		
		JTree1.repaint();	
	}	
	
  public void setVisible(boolean bl)
  {
        if(false)
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

  super.setVisible(bl);		
  }

public Hashtable getOperHash()
	{
		//System.out.println("OPER HASTABLE "+operhash);
		if(operhash!=null)
			return operhash;
		else 
			return new Hashtable();
	}
		
 int modifiedcount = 0;
public Hashtable getAddOperations(Hashtable oper )
	{
		if( (newhash == null) ||  (newhash.size() == 0))
		{
			oper = null;
		}
		else
		{
			oper = newhash;
		}
 		return oper;
	}
	
public Hashtable getModOperations(Hashtable oper )
	{
	 if(operhash == null)
		{
			oper = null;
		}	
	 if(modifiedcount == 0)
		{
			oper = null;
		}
	 else
		{
			oper = operhash;				
		}
	  	return oper;
	}
	
 public void close()
	{
	     if(help != null)
 help.dispose();	
 	
     	      newhash = null;  
	      operhash = null;
	      dispose();
}	
	
String username = "";
	
public void setUserName(String username)
	{
	     this.username = username;
	}
	
class ConfigTreeRenderer extends JLabel implements TreeCellRenderer
{

    ImageIcon task1 = null;
    ImageIcon unch = null;
    ImageIcon ch = null;
    public ConfigTreeRenderer()
    {
        task1 = AuthMain.getBuilderUiIfInstance().getImage("circle.png");
        unch = AuthMain.getBuilderUiIfInstance().getImage("wrong01.png");
 //unch = AuthMain.getBuilderUiIfInstance().getImage("task1.png");
        ch = AuthMain.getBuilderUiIfInstance().getImage("tick01.png");		
        setOpaque(false);
    }

public Component getTreeCellRendererComponent(JTree tree,Object value,boolean selected,boolean expanded,boolean leaf,int row,boolean hasFocus)
    {
		
	setText(value.toString());
	TreeObject tob = (TreeObject)(((DefaultMutableTreeNode)value).getUserObject());
	if(tob.toString().charAt(tob.toString().length() - 1) == ' ')
			{
			  setForeground(Color.pink);
			}
	if(tob.getString().equals("Operation Tree Root"))
		{
	 setIcon(task1);					
	 return this;
		}
		
	return this;	
		
    }		

 }	
	
//<Begin__class_JTree1_JTree1_conn1>
 class JTree1_JTree1_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {
     //<TOP_PART>
//<UserCode_Begin_Connection_JTree1_JTree1_conn1>


     //Listener Interface Methods Are Added Below 
     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  if(help != null) 
  {
    help.dispose();
   } 
 if(!JButton1.isEnabled())
 {
  JButton1.setEnabled(true);
 } 
  
  int row = JTree1.getRowForLocation(arg0.getX(), arg0.getY()); 
 if(row == -1)
 return;
 TreePath  path =  JTree1.getPathForRow(row); 
 DefaultMutableTreeNode node =  (DefaultMutableTreeNode )path.getLastPathComponent();
 TreeObject tob = (TreeObject)node.getUserObject() ;
 if(tob.getString().equals("Operation Tree Root"))
   {
    return;
   }
       myHashTable(node);  
       JTree1.revalidate(); 
       JTree1.repaint(); 
    }
 

     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
       
  setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

     } 
      //System.out.println("NEW HASH"+newhash);
 //System.out.println("OPER HASH"+operhash);
   
//<UserCode_End_Connection_JTree1_JTree1_conn1>
 }//<End__class_JTree1_JTree1_conn1>


//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
	done();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>
//private Hashtable care;
	java.util.Hashtable operhash = null;
	  void myHashTable(DefaultMutableTreeNode node)
 {
	  
		TreeObject tob = (TreeObject)node.getUserObject() ;
		if(tob.getString().equals("Operation Tree Root"))
 {
   			return;
  		} 
		int whole = useroperations.size(); 		int some = modifyNot.size();	
		if(whole >0 && name1.equals("Users ")){

			if(whole>some || whole ==some){			
				if(useroperations.containsKey(tob.getString()) && !modifyNot.containsKey(tob.getString()))
 {
					return;	
				}
			} else if(some>whole){			
				if(modifyNot.containsKey(tob.getString()) && !useroperations.containsKey(tob.getString()))
 {	
					return; 
				}
		        	} 
		    }
		
 		if(tob.getInt() == 0)  
{
			tob.setInt(1);
			operhash.put(tob.getString(),"1");
			if(!node.isLeaf() &&!operhash.contains(((TreeObject)node.getUserObject()).getString()))
 {   				recursivelySelectChildren(node, 1); 
 			}
 		}
 else if(tob.getInt() ==1)
 {
			tob.setInt(2);
			operhash.put(tob.getString(),"0");
			if(!node.isLeaf() && !operhash.contains(((TreeObject)node.getUserObject()).getString()))
 {
	  			recursivelySelectChildren(node, 2); 
 			}
 		}
 else if(tob.getInt() == 2 )  
{
				if(operhash.containsKey(tob.getString())) {
					tob.setInt(0);
	    				if(!node.isLeaf())
 {
 						recursivelySelectChildren(node,0); 
 					}
 					operhash.remove(((TreeObject)node.getUserObject()).getString());
				}
 		}
 	}

	private String name1="";
	public void setName(String namew,String dpname){
		name = namew;
		name1 = dpname;	
		//System.out.println("MYVIEW  "+name+"               "+name1);
		}	
	
 




 





//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 close();  
  
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>


//<Begin__class_JLabel2_JLabel2_conn1>

 class JLabel2_JLabel2_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JLabel2_JLabel2_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
 if(help != null) 
 {
  help.dispose();
 }
 
 if (modify)  
help = new HelpWindow(true);
else help = new HelpWindow();

	
 help.setSize(350, 280);
  
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
 double wid = screenSize.getWidth();
  double hgt = screenSize.getHeight();
 help.setLocation((int)wid/2+ 100,( (int)hgt/2 - (int)hgt/4 + 100)); 
  
 String str = "";
if(name1.equals("Users "))
{
// str = "<html><body><p>"+resourceBundle.getString(" Click on an operation to allow / disallow it . Checked operations are included for the user  and unchecked operations are excluded. Here, you can allow or disallow existing operations to the user") +" <br>"+resourceBundle.getString("This dialog helps you assign permissions for a user , if you modify the operations which are already associated to a group , the permissions of the other users who belong to the particular group will change.") +"</p> <p>" + resourceBundle.getString("If you want to add  or remove operations select  Operations Tree from the Advanced tab")+"</p></body></html>"; 

 str = "<html><body><p>"+resourceBundle.getString("security.ui.help.user.text")  +"</p></body></html>"; 

 help.display(str, resourceBundle.getString("Assigning permissions for a user")); 
 

 
}
else
{
 // str = "<html><body><p>"+resourceBundle.getString(" Click on an operation to allow / disallow it . Checked operations are included for the user  and unchecked operations are excluded. Here, you can allow or disallow existing operations to this group") +" <br>"+resourceBundle.getString("This dialog helps you to assign permissions for a group. If you want to add  or remove operations select  AddOperation from the main screen")+"</p></body></html>"; 
 str = "<html><body><p>"+resourceBundle.getString("security.ui.help.group.text")  +"</p></body></html>"; 
 help.display(str, resourceBundle.getString("Assigning permissions for a group")); 
}
 help.setVisible(true); 
     }
//<UserCode_End_Connection_JLabel2_JLabel2_conn1>
 }//<End__class_JLabel2_JLabel2_conn1>


//<Begin__class_JLabel3_JLabel3_conn1>

 class JLabel3_JLabel3_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JLabel3_JLabel3_conn1>



     //Listener Interface Methods Are Added Below 
   boolean flip = true;
 public void flipflop()
 {
  flip = ! flip;
 } 

     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
if(help != null) 
 {
  help.dispose(); 
 }  

   if(!flip)
  {
   for(int i=0; i<JTree1.getRowCount();i++)
   JTree1.expandRow(i);
   //JLabel3.setIcon(AuthMain.getBuilderUiIfInstance().getImage("expandit_small.png")); 
   //JLabel3.setToolTipText(resourceBundle.getString("Expand"));  
   JLabel3.setIcon(AuthMain.getBuilderUiIfInstance().getImage("collapseit_small.png"));
   JLabel3.setToolTipText(resourceBundle.getString("Collapse"));
   flipflop();
    }
  else
  {
   for(int i=1; i<JTree1.getRowCount();i++)
   JTree1.collapseRow(i);
   //JLabel3.setIcon(AuthMain.getBuilderUiIfInstance().getImage("collapseit_small.png"));  
   //JLabel3.setToolTipText(resourceBundle.getString("Collapse"));  
   JLabel3.setIcon(AuthMain.getBuilderUiIfInstance().getImage("expandit_small.png"));
   JLabel3.setToolTipText(resourceBundle.getString("Expand"));
flipflop();
   
  } 
 
     }
//<UserCode_End_Connection_JLabel3_JLabel3_conn1>
 }//<End__class_JLabel3_JLabel3_conn1>


//<Begin__class_JTree1_JTree1_conn2>

 class JTree1_JTree1_conn2 extends java.awt.event.MouseMotionAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTree1_JTree1_conn2>



     //Listener Interface Methods Are Added Below 


     public void mouseMoved( java.awt.event.MouseEvent arg0)
     {
 int x = arg0.getX();
 int y=arg0.getY();
  
 if(JTree1.getRowForLocation(x,y) != -1 ) 
  {
   setCursor(new Cursor(Cursor.HAND_CURSOR));
  } 
 else
  {
   setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
  }  
  
     }
//<UserCode_End_Connection_JTree1_JTree1_conn2>
 }//<End__class_JTree1_JTree1_conn2>


//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 resetChanges();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>


//<Begin__class_JPanel1_JPanel1_conn1>

 class JPanel1_JPanel1_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JPanel1_JPanel1_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  
   if(help != null) 
  {
   help.dispose();
  } 
 
     }
//<UserCode_End_Connection_JPanel1_JPanel1_conn1>
 }//<End__class_JPanel1_JPanel1_conn1>


//<Begin__class_JPanel2_JPanel2_conn1>

 class JPanel2_JPanel2_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JPanel2_JPanel2_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
 
 
  if(help != null) 
  {
   help.dispose();
  }
 
     }
//<UserCode_End_Connection_JPanel2_JPanel2_conn1>
 }//<End__class_JPanel2_JPanel2_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }


//<Begin__class_JLabel2_JLabel2_conn>

 class JLabel2_JLabel2_conn extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JLabel2_JLabel2_conn>

     //Listener Interface Methods Are Added Below 


     public void keyPressed( java.awt.event.KeyEvent arg0)
     {
          System.out.println("Help Label KeyPressed : " + arg0.getKeyCode());
          if (arg0.getKeyCode() == KeyEvent.VK_F1) System.out.println("F1 Pressed");
     }
//<UserCode_End_Connection_JLabel2_JLabel2_conn>
 }//<End__class_JLabel2_JLabel2_conn>
}












