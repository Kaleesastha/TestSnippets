//$Id: CustomViewTypes.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory







package com.adventnet.nms.tools.CustomView;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.tree.*;
import java.util.*;
import java.rmi.RemoteException;
import java.rmi.*;
import com.adventnet.nms.tools.utils.ImgConv;
import java.net.URL; 

import com.adventnet.nms.fe.common.NmsTreeAPI;
import com.adventnet.nms.fe.common.PanelTreeNode;
 import com.adventnet.nms.fe.common.ViewData;
import com.adventnet.nms.fe.common.TableColumn;


public class CustomViewTypes extends JFrame implements ActionListener,MouseListener,TreeSelectionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private static final String param[] = {};
	javax.swing.JPanel Top = null;
	javax.swing.JSplitPane JSplitPane1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton create = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTree customTypes = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton expand = null;
	javax.swing.JButton collapse = null;
	private JMenuBar menuBar=null;
	JMenu FileMenu= null;
	JMenuItem   CreateMenuItem = null;
	JMenuItem   ModifyMenuItem = null;
	JMenuItem   DeleteMenuItem = null;
	JMenuItem   ExitMenuItem = null;
	JMenu HelpMenu= null;
	JMenuItem   Help_ContentsMenuItem = null;
	private JToolBar toolBar = null;
	private ImageIcon icon = null;
	JButton   CreateButton = null;
	JButton   ModifyButton = null;
	JButton   DeleteButton = null;
	JButton   ExitButton = null;
	JButton   HelpButton = null;
	MenuToolBarAction menuToolBarAction = new MenuToolBarAction();
	//<End_Variable_Declarations>

	//private final String[] typesArr = CustomParameters.moduleNames;
	public static JFrame topFrame = null;

	private final String[] options = new String[]{"Modify","Delete"};
	private final String[] typesArr = new String[]{"TOPO CUSTOMVIEW","ALERT CUSTOMVIEW","EVENT CUSTOMVIEW","PERFORMANCE CUSTOMVIEW"};
	private DefaultMutableTreeNode root = null;
	private DefaultMutableTreeNode node = null;
	private DefaultMutableTreeNode custViewNode=null;
	private DefaultTreeModel model = null;
	private JPopupMenu popup = null;
	private JMenuItem item = null;
	private NodeRenderer renderer = null;
	private URL url = null;
	CustomizerDialog customizer=null;
	 NmsTreeAPI treeAPI=null;
	PanelTreeNode panelNode=null;
	UserGroupInformation userGroupInfo=null;
	Vector users=new Vector(0);
	CustomViewObject custViewObject=null;	
	ShowCustomView showCustView=null;
	Properties customViewProperties=null;
	int count;
	Vector customViews=null;
	boolean deleteAllChilds=false;
  public CustomViewTypes()
  {
    //<Begin_CustomViewTypes>
    pack();
    this.setTitle("CustomViewTypes");
  
    //<End_CustomViewTypes>
	
		//URL url=getClass().getResource("images"+File.separator+"adventneticon.jpg");
	//setIconImage(new ImgConv().getImage(url.toString()));
  }

  public CustomViewTypes(java.applet.Applet applet)
  {
    //<Begin_CustomViewTypes_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setTitle("CustomViewTypes");
  
    //<End_CustomViewTypes_java.applet.Applet>
  }


  public static void main(String [] args)
  {
	
      
	//<Begin_main_String[]>
      com.adventnet.apiutils.Utility.parseAndSetParameters(param,args);  
     CustomViewTypes frame = new CustomViewTypes();
     frame.setVisible(true);
     frame.addWindowListener(new WindowAdapter()
     {
       public void windowClosing(WindowEvent evt)
       {
         System.exit(0);
       }
     });
  
      //<End_main_String[]>
		Toolkit kit=Toolkit.getDefaultToolkit();
     Image img=kit.getImage(System.getProperty("user.dir")+File.separator+"icons"+File.separator+"ic1.jpg");		
     frame.setIconImage(img); 	  

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

  public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+743,getPreferredSize().height+479); 
          setTitle("CustomView Administrator");
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

setUpMenus();
setUpToolBar();

  
        //<End_init>
	
   	
	String name="//localhost/TreeAPI";
          try
	{
	   treeAPI = (NmsTreeAPI) Naming.lookup(name);
	}
	catch(Exception e)
	{
		System.out.println("Exception while initialising treeAPI"+e.getMessage());
		JOptionPane.showMessageDialog(null,"Please start the server before invoking this tool","Message",JOptionPane.INFORMATION_MESSAGE);
	
	}			
	
	
			
      userGroupInfo=new UserGroupInformation();	
      Vector getusers=userGroupInfo.getAllUsers();

       Vector grpUsers=new Vector(0);

	

      for(int i=0;i<getusers.size();i++)
       {
            grpUsers = (Vector)getusers.elementAt(i);
             for(int j=0;j<grpUsers.size();j++)
                {
                    if(!users.contains(grpUsers.elementAt(j).toString()))
                        users.add(grpUsers.elementAt(j));
                    else
                        continue;
                }	
      }			

	
	      		
      root = new DefaultMutableTreeNode("Custom View Types");
      custViewNode=new DefaultMutableTreeNode("TOPO CUSTOMVIEW");
      root.add(custViewNode);
      setCustomViews("Network Database","TOPO CUSTOMVIEW");
      custViewNode=new DefaultMutableTreeNode("ALERT CUSTOMVIEW");
      root.add(custViewNode);
      setCustomViews("Alerts","ALERT CUSTOMVIEW");
      custViewNode=new DefaultMutableTreeNode("EVENT CUSTOMVIEW");
      root.add(custViewNode);
      setCustomViews("Events","EVENT CUSTOMVIEW");

      custViewNode=new DefaultMutableTreeNode("PERFORMANCE CUSTOMVIEW");
      root.add(custViewNode);
      setCustomViews("Stats Admin","PERFORMANCE CUSTOMVIEW");

	


     	model = new DefaultTreeModel(root);
     	customTypes.setModel(model);
	 customTypes.addMouseListener(this);
	customTypes.addTreeSelectionListener(this);
	renderer = new NodeRenderer(); 
	customTypes.setCellRenderer(renderer);
	customTypes.setSelectionRow(0);
	
	
  }
public void setCustomViews(String module,String type)
{
		for(int j=0;j<users.size();j++)
		{
		     	String user=users.elementAt(j).toString();
			node=new DefaultMutableTreeNode(user);
		     	custViewNode.add(node);		
			try
			{
				treeAPI.updateUserDetail(user);
				 customViews=treeAPI.getChildList(user,module);
			}
			catch(Exception e)
			{
				System.out.println("Exception");
				e.printStackTrace();	
			}
			if(customViews!=null)
			{
				for(int i=0;i<customViews.size();i++)
				{
					String custViewId=customViews.elementAt(i).toString();
					String custViewName="";
					String treeIcon="";
					Properties panelProps=null;
					try
					{
						 panelNode=treeAPI.getPanelTreeNode(user,custViewId);
							
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					if(panelNode!=null)
					{
						 panelProps=panelNode.getPanelProperties();	
					}
					if(panelProps!=null)
					{
						if(panelProps.containsKey("TREE-NAME"))
						{
							custViewName=(String)panelProps.getProperty("TREE-NAME");
						}
						if(panelProps.containsKey("ICON-FILE"))
						{
							
							treeIcon=(String)panelProps.getProperty("ICON-FILE");

						}
					}
					String parentId=panelNode.getParent();
					custViewObject=new CustomViewObject(custViewId,type,custViewName,user,treeIcon,parentId);
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(custViewId);
					child.setUserObject(custViewObject);
  			                      node.add(child);
				}
			}
		}
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
            }
        return value;

  
           //<End_getParameter_String>
  }
		
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+Top,ex); 
          }

          try
          {
            create.setFont(new Font("SansSerif",0,12));
            create.setHorizontalTextPosition(4);
            create.setText("Create");
            create.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+create,ex); 
          }

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
            JLabel1.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

          try
          {
            expand.setFont(new Font("SansSerif",0,12));
            expand.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+expand,ex); 
          }

          try
          {
            collapse.setFont(new Font("SansSerif",0,12));
            collapse.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+collapse,ex); 
          }
		create.setPreferredSize(new Dimension(create.getPreferredSize().width+34,create.getPreferredSize().height+6));
		JSplitPane1.setPreferredSize(new Dimension(JSplitPane1.getPreferredSize().width+189,JSplitPane1.getPreferredSize().height+0));

  
          //<End_setUpProperties>
									
									(customTypes.getSelectionModel()).setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
  } 
  public void start()
  {
  //<Begin_start> 

  
  //<End_start>
  } 
  public void stop()
  {
  //<Begin_stop> 

  
  //<End_stop>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JSplitPane1= new javax.swing.JSplitPane();
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        create= new javax.swing.JButton();
        JLabel1= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        customTypes= new javax.swing.JTree();
        JPanel4= new javax.swing.JPanel();
        expand= new javax.swing.JButton();
        collapse= new javax.swing.JButton();

  
        //<End_initVariables>
topFrame = this;

	String image="file:///"+System.getProperty("user.dir")+File.separator+"images"+File.separator+"expandit_small.png";
	expand.setIcon(new ImageIcon(ImgConv.getImage(image)));
	image="file:///"+System.getProperty("user.dir")+File.separator+"images"+File.separator+"collapseit_small.png";
	collapse.setIcon(new ImageIcon(ImgConv.getImage(image)));
	expand.addActionListener(this);
	collapse.addActionListener(this);
create.setEnabled(false);			
node = new DefaultMutableTreeNode();
		create.addActionListener(menuToolBarAction);			
		create.setActionCommand("Create");
//image="file:///"+System.getProperty("user.dir")+File.separator+"images"+File.separator+"bcv1.jpg";
// JLabel1.setIcon(new ImageIcon(ImgConv.getImage(image)));
JLabel1.setText("<html><font face=serif color=#000000 size=3><ul><B>Custom View</B> filters out the required output by sorting through the large amount of data of Web NMS.It is basically a set of data which are subset of a complete set of data ,satisfying  the given criteria.<BR> <li><B>Create</B> Custom view adds a new customview with given criteria.Create button present below or toolbar button can be used for this.After submitting the neccessary criteria a new Custom view will be created for all specified  users.<BR><li><B>Modify</B> Custom view can be used for modifying an already created Custom view.The modify option available in the popup menu or toolbar button can be used for this.The modification can be done in user level as well as in group level.<BR><li><B>Delete</B> Custom view deletes the already created Customview.The deletion can be done in user level as well as in group level..If the selected customview has one or more child views then option is provided for deleting or not deleting them.</ul></html>");	




  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JSplitPane1,BorderLayout.CENTER);
JSplitPane1.setRightComponent(JPanel1);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(2,10,10));
JPanel2.add(create);
JPanel1.add(JLabel1,BorderLayout.CENTER);
JSplitPane1.setLeftComponent(JPanel3);
JSplitPane1.setDividerLocation(121);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(customTypes);
JPanel3.add(JPanel4,BorderLayout.SOUTH);
JPanel4.setLayout(new FlowLayout(1,5,5));
JPanel4.add(expand);
JPanel4.add(collapse);

  
//<End_setUpGUI_Container>
Toolkit kit=Toolkit.getDefaultToolkit();
Dimension dim=kit.getScreenSize();
this.setSize(dim.width,dim.height);
JSplitPane1.setDividerLocation(250);
popup= new JPopupMenu();
for(int i=0;i<options.length;i++)
{
			item = new JMenuItem(options[i]);
			item.addActionListener(menuToolBarAction);
			item.setActionCommand(options[i]);
			popup.add(item);
}

		
//MenuBar.setBorder(new javax.swing.border.EtchedBorder(0));
//toolBar.setBorder(new javax.swing.border.EtchedBorder(0));
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
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


 
  public void setUpMenus()
  {
   //<Begin_setUpMenus> 
   menuBar = new JMenuBar();
FileMenu= new JMenu("File");
   FileMenu.setMnemonic('F');
menuBar.add(FileMenu);
   CreateMenuItem = new JMenuItem("Create");
   CreateMenuItem.setMnemonic('C');
   CreateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,+ActionEvent.ALT_MASK));
   CreateMenuItem.setActionCommand("Create");
   CreateMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(CreateMenuItem);
   ModifyMenuItem = new JMenuItem("Modify");
   ModifyMenuItem.setMnemonic('M');
   ModifyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,+ActionEvent.ALT_MASK));
   ModifyMenuItem.setActionCommand("Modify");
   ModifyMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(ModifyMenuItem);
   DeleteMenuItem = new JMenuItem("Delete");
   DeleteMenuItem.setMnemonic('D');
   DeleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,+ActionEvent.ALT_MASK));
   DeleteMenuItem.setActionCommand("Delete");
   DeleteMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(DeleteMenuItem);
   ExitMenuItem = new JMenuItem("Exit");
   ExitMenuItem.setMnemonic('X');
   ExitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,+ActionEvent.ALT_MASK));
   ExitMenuItem.setActionCommand("Exit");
   ExitMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(ExitMenuItem);
HelpMenu= new JMenu("Help");
   HelpMenu.setMnemonic('H');
menuBar.add(HelpMenu);
   Help_ContentsMenuItem = new JMenuItem("Help Contents");
   Help_ContentsMenuItem.setMnemonic('C');
   Help_ContentsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,+ActionEvent.ALT_MASK));
   Help_ContentsMenuItem.setActionCommand("Help Contents");
   Help_ContentsMenuItem.addActionListener(menuToolBarAction);
   HelpMenu.add(Help_ContentsMenuItem);
this.setJMenuBar(menuBar);

  
   //<End_setUpMenus>
	CreateMenuItem.setEnabled(false);
	DeleteMenuItem.setEnabled(false);
	ModifyMenuItem.setEnabled(false);
} 
  public void setUpToolBar()
  {
//<Begin_setUpToolBar> 
toolBar = new JToolBar();
toolBar.setFloatable(false);
CreateButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("./images/addcustom.png",applet); 
   CreateButton.setPreferredSize(new Dimension(35,35));
   CreateButton.setMinimumSize(new Dimension(35,35));
   CreateButton.setMaximumSize(new Dimension(35,35));
   CreateButton.setIcon(icon);
   CreateButton.setActionCommand("Create");
   CreateButton.setToolTipText("Create New Custom View");
ModifyButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("./images/modifycustom.png",applet); 
   ModifyButton.setPreferredSize(new Dimension(35,35));
   ModifyButton.setMinimumSize(new Dimension(35,35));
   ModifyButton.setMaximumSize(new Dimension(35,35));
   ModifyButton.setIcon(icon);
   ModifyButton.setActionCommand("Modify");
   ModifyButton.setToolTipText("Modify Custom View");
DeleteButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("./images/removecustom.png",applet); 
   DeleteButton.setPreferredSize(new Dimension(35,35));
   DeleteButton.setMinimumSize(new Dimension(35,35));
   DeleteButton.setMaximumSize(new Dimension(35,35));
   DeleteButton.setIcon(icon);
   DeleteButton.setActionCommand("Delete");
   DeleteButton.setToolTipText("Delete Custom View");
ExitButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("./images/exit.jpg",applet); 
   ExitButton.setPreferredSize(new Dimension(35,35));
   ExitButton.setMinimumSize(new Dimension(35,35));
   ExitButton.setMaximumSize(new Dimension(35,35));
   ExitButton.setIcon(icon);
   ExitButton.setActionCommand("Exit");
   ExitButton.setToolTipText("Exit");
HelpButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("./images/help_mo.png",applet); 
   HelpButton.setPreferredSize(new Dimension(35,35));
   HelpButton.setMinimumSize(new Dimension(35,35));
   HelpButton.setMaximumSize(new Dimension(35,35));
   HelpButton.setIcon(icon);
   HelpButton.setActionCommand("Help Contents");
   HelpButton.setToolTipText("Help");
   JPanel toolBarPanel = new JPanel(new BorderLayout());
   toolBarPanel.add(toolBar);
   getContentPane().add(toolBarPanel,BorderLayout.NORTH);

  
//<End_setUpToolBar>
CreateButton.setEnabled(false);
DeleteButton.setEnabled(false);
ModifyButton.setEnabled(false);
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
      if(evt.getActionCommand().equals("Create"))
      {
         TreePath path=customTypes.getSelectionPath();
  if(path==null)
  {
    return;
  }
  setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//the third parameter is used only during modification for creation it is not needed
customizer=new CustomizerDialog(path.getLastPathComponent().toString(),"create",null);
customizer.showDialog(); 
 addToTree(customizer.getCreatedCustomViewInfo(),path.getLastPathComponent().toString());
customTypes.setSelectionPath(path);
setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      }
      if(evt.getActionCommand().equals("Modify"))
      {
         TreePath path=customTypes.getSelectionPath();
  if(path==null)
  {
    return;
  }
setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
DefaultMutableTreeNode node=(DefaultMutableTreeNode)path.getLastPathComponent();   
CustomViewObject object =(CustomViewObject)node.getUserObject();
String type=object.getCustViewType();
String viewId=object.getCustViewId();
customizer=new CustomizerDialog(type,"modify",customViewProperties);
customizer.showDialog();  
setModifiedInformation(customizer.getCreatedCustomViewInfo(),type);
customTypes.setSelectionPath(path);

setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      }
      if(evt.getActionCommand().equals("Delete"))
      {
         TreePath path=customTypes.getSelectionPath();
  if(path==null)
  {
    return;
  }
  setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
DefaultMutableTreeNode node=(DefaultMutableTreeNode)path.getLastPathComponent();   
CustomViewObject object =(CustomViewObject)node.getUserObject();
String user=object.getUserName();
String type=object.getCustViewType();
String viewName=object.getCustViewName();
DeleteDialog dialog=new DeleteDialog(user);
dialog.setVisible(true);
Vector v=dialog.getUsers();
 deleteAllChilds=dialog.getDeleteChilds();
if(v!=null)
{
callForDelete(viewName,type,v);
}
setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      }
      if(evt.getActionCommand().equals("Exit"))
      {
         System.exit(0);
      }
      if(evt.getActionCommand().equals("Help Contents"))
      {
         //Please add the action code for menu Here Help Contents
      }
      {
         
      }
      {
         
      }
      {
         
      }
      {
         
      }
      {
         
      }

  } 
}
//<End_class_MenuToolBarAction>

public void actionPerformed(ActionEvent ae)
{
	if(ae.getSource().equals(expand))
	{
		expandTree();
	}
	if(ae.getSource().equals(collapse))
	{
			
		collapseTree();	
	}
}
public void expandTree()
{
	
	
	count=0;
	for(int i=0;i<root.getChildCount();i++)
	{
			count++;
			countTreeNodes(root.getChildAt(i));
	}
	for(int j=0;j<count;j++)
	{
		if(!customTypes.isExpanded(j))
		{
			customTypes.expandRow(j);	
		}
	}
}
public void collapseTree()
{
	//only root and all it's childs will be visible
	for(int i=1;i<=root.getChildCount();i++)
	{
			if(!customTypes.isCollapsed(i))
			{
				customTypes.collapseRow(i);
			}
	}
}
	
public void countTreeNodes(TreeNode node)
{
		for(int j=0;j<node.getChildCount();j++)
		{
			count++;
			countTreeNodes(node.getChildAt(j));
		}
}
public void callForDelete(String viewName,String type,Vector v)
{
	String module="";
	boolean result=true;
	
	if(type.equals("TOPO CUSTOMVIEW"))
	{
		module="Network Database";
	}
	else if(type.equals("ALERT CUSTOMVIEW"))
	{
		module="Alerts";
	}
	else if(type.equals("EVENT CUSTOMVIEW"))
	{
		module="Events";
	}
	else if(type.equals("PERFORMANCE CUSTOMVIEW"))
	{
		module="Stats Admin";
	}
	for(int j=0;j<model.getChildCount(root);j++)
	{
		DefaultMutableTreeNode parent=(DefaultMutableTreeNode)model.getChild(root,j);
		if(parent.toString().equals(type))
		{
			int count=model.getChildCount(parent);
			for(int k=0;k<count;k++)
			{
				DefaultMutableTreeNode child =(DefaultMutableTreeNode)model.getChild(parent,k);
				String user=child.toString();
				if(v.contains(user))
				{
					int noOfChilds=model.getChildCount(child);
					for(int a=0;a<noOfChilds;a++)
					{
						DefaultMutableTreeNode subChild =(DefaultMutableTreeNode)model.getChild(child,a);
						CustomViewObject object=(CustomViewObject)subChild.getUserObject();
						if(object.getCustViewName().equals(viewName))
						{

							String viewId=object.getCustViewId();
							result=delete(viewId,user,module);				
							System.out.println("Customview :"+ viewName+" is deleted for"+user+"Result :"+result);
							if(result)
							{
								 child.remove(subChild);
								//when we delete a node the number of childs should also be reduced	
								noOfChilds--;
								a--;
								if(deleteAllChilds)
								{
									//when delete all childs option is true we have to search for the childs of this customview and we have to delete them also from tree	
									for(int i=0;i<noOfChilds;i++)
									{
										DefaultMutableTreeNode node=(DefaultMutableTreeNode)model.getChild(child,i);
										CustomViewObject cvo=(CustomViewObject)node.getUserObject();
										if(cvo.getParentId().equals(viewId))
										{
												child.remove(node);
												noOfChilds--;
												i--;
										}
								}	}		
			 				}
						}	
												
					}
				}
			}
		}
	}
	customTypes.updateUI();
	customTypes.setSelectionRow(0);
}
public void valueChanged(TreeSelectionEvent evt)
{
		TreePath tp=evt.getPath();
	        DefaultMutableTreeNode selNode=(DefaultMutableTreeNode)tp.getLastPathComponent();				
			ModifyButton.setEnabled(false);
			ModifyMenuItem.setEnabled(false);
			DeleteButton.setEnabled(false);
			DeleteMenuItem.setEnabled(false);	
			CreateMenuItem.setEnabled(false);
			CreateButton.setEnabled(false);
	       	 if(selNode.isRoot())
		{
			JSplitPane1.setRightComponent(JPanel1);			
			JSplitPane1.setDividerLocation(250);
			create.setEnabled(false);
				
			
			return;
		}
		
		if(root.isNodeChild(selNode))
		{
			JSplitPane1.setRightComponent(JPanel1);			
			JSplitPane1.setDividerLocation(250);
			create.setEnabled(true);
			CreateMenuItem.setEnabled(true);
			CreateButton.setEnabled(true);
			
			return;
		}
		create.setEnabled(false);

		JSplitPane1.setRightComponent(JPanel1);	
		JSplitPane1.setDividerLocation(250);
 		 				
		if(selNode.isLeaf())
		{
			Object obj=selNode.getUserObject();
			if(obj instanceof CustomViewObject)
			{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//the customviewproperties is collected for the selected node and it will be for show customview and modify customview
			customViewProperties=getCustomViewProperties(selNode);
			if(customViewProperties!=null)
			{
			showProperties(selNode,customViewProperties);
			
			
			ModifyButton.setEnabled(true);
			ModifyMenuItem.setEnabled(true);
			DeleteButton.setEnabled(true);
			DeleteMenuItem.setEnabled(true);
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			return;	
					
			}
			else
			{
				ModifyButton.setEnabled(false);
				ModifyMenuItem.setEnabled(false);
				DeleteButton.setEnabled(false);
				DeleteMenuItem.setEnabled(false);				
				create.setEnabled(false);
				CreateButton.setEnabled(false);
				CreateMenuItem.setEnabled(false);
			}
		}
			
}
 
public void mousePressed(MouseEvent me)
{
}

public void mouseClicked(MouseEvent me)
{
    	if(me.getSource()==customTypes ) 
     	{
        		
		if(customTypes.getPathForLocation(me.getX(),me.getY())==null){
             	return;		 
        		}
		 TreePath tp=customTypes.getPathForLocation(me.getX(),me.getY());				
	        DefaultMutableTreeNode selNode=(DefaultMutableTreeNode)tp.getLastPathComponent();	
		if(selNode.isLeaf())
		{
			if(SwingUtilities.isRightMouseButton(me))
			{
                    			customTypes.setSelectionPath(tp);
				Object obj=selNode.getUserObject();
				if(obj instanceof CustomViewObject)
				{
					popup.show(me.getComponent(),me.getX(),me.getY());				
				}
			}
		}
		
			

		
             }
}
public void mouseReleased(MouseEvent me){}
public void mouseEntered(MouseEvent me){}
public void mouseExited(MouseEvent me){}
public void showProperties(DefaultMutableTreeNode node,Properties p)
{
		showCustView=new ShowCustomView(node,p);
		JSplitPane1.setRightComponent(showCustView.showPanel());	
}
public void addToTree(Vector v,String type)
{

	if(v==null)
	{
		return;
	}

	for(int i=0;i<v.size();i++)
	{
		
		DefaultMutableTreeNode parent=null;
		String[] s=(String[])v.elementAt(i);
		DefaultMutableTreeNode node=new DefaultMutableTreeNode(s[2]);
		custViewObject=new CustomViewObject(s[2],type,s[1],s[0],s[3],s[4]);
		node.setUserObject(custViewObject);
		for(int j=0;j<model.getChildCount(root);j++)
		{
			 parent=(DefaultMutableTreeNode)model.getChild(root,j);
			if(parent.toString().equals(type))
			{
				int count=model.getChildCount(parent);
				for(int k=0;k<count;k++)
				{
					DefaultMutableTreeNode child =(DefaultMutableTreeNode)model.getChild(parent,k);
					if(child.toString().equals(s[0]))
					{
					
						int noOfChilds=model.getChildCount(child);
						model.insertNodeInto(node,child,noOfChilds);
					}
				}
			}
		}
	}
	customTypes.updateUI();
	customTypes.setSelectionRow(0);
}
public void setModifiedInformation(Vector v,String type)
{
	if(v==null)
	{
		return;
	}
	for(int i=0;i<v.size();i++)
	{
		DefaultMutableTreeNode parent=null;
		String[] s=(String[])v.elementAt(i);
		for(int j=0;j<model.getChildCount(root);j++)
		{
			 parent=(DefaultMutableTreeNode)model.getChild(root,j);
			if(parent.toString().equals(type))
			{
				int count=model.getChildCount(parent);
				for(int k=0;k<count;k++)
				{
					DefaultMutableTreeNode child =(DefaultMutableTreeNode)model.getChild(parent,k);
					if(child.toString().equals(s[0]))
					{
					
						int noOfChilds=model.getChildCount(child);
						for(int a=0;a<noOfChilds;a++)
						{
							DefaultMutableTreeNode subChild =(DefaultMutableTreeNode)model.getChild(child,a);
							CustomViewObject object=(CustomViewObject)subChild.getUserObject();
							if(object.getCustViewId().equals(s[3]))
							{
								object.setTreeIcon(s[2]);
								object.setCustViewName(s[1]);
								object.setParentId(s[4]);
							}
							
						}
					}
				}
			}
		}
	}
	customTypes.updateUI();
	customTypes.setSelectionRow(0);
}
public Properties getCustomViewProperties(DefaultMutableTreeNode node)
{
	Object object=node.getUserObject();
	if(!(object instanceof CustomViewObject))
	{
		return null;
	}
	custViewObject =(CustomViewObject)object;
	String user=custViewObject.getUserName();
	String id=custViewObject.getCustViewId();
	try
	{
		treeAPI=(NmsTreeAPI)Naming.lookup("//localhost/TreeAPI");
		if(treeAPI!=null)
		{
			panelNode=treeAPI.getPanelTreeNode(user,id);
		}
	}
	catch(Exception e)
	{
		System.out.println("Exception while instantiating TreeAPI for getting properties of customview");
		e.printStackTrace();
		return null;
	}
	if(panelNode==null)
	{
		return null;
	}
	Properties p= panelNode.getPanelProperties();
	if(p==null||p.size()==0)
	{
		return null;
	}
	//the parent name will not come in this property to is obtained by using the getParent method and then it is put in the property object
	String parentId=panelNode.getParent();
	//the parent here we get is only id in order to get the tree name we need the following
	String parentName="";
	String module=panelNode.getModuleName();
	try
	{
		panelNode=treeAPI.getPanelTreeNode(user,parentId);
		if(panelNode!=null)
		{
			Properties prop=panelNode.getPanelProperties();
			parentName=prop.getProperty("TREE-NAME");
		}
	}
	catch(Exception e)
	{
		System.out.println("Exception while getting the parentName");
		e.printStackTrace();
	}
	p.put("parentname",parentName);
	p.put("parentid",parentId);
	p.put("viewid",id);	
	p.put("user",user);
	p.put("MODULE",module);
	//this property will contain all the view properties us one single value for the key VIEW-CRITERIA here it is splited
	if(p.containsKey("VIEW-CRITERIA"))
	{
		Properties viewProps=new Properties();
		String viewCriteria=p.getProperty("VIEW-CRITERIA");
		StringTokenizer tok=new StringTokenizer(viewCriteria,";");
		while(tok.hasMoreTokens())
		{
			String s=(String)tok.nextToken();

			int index=s.indexOf("=");
			if(index != -1)
			{
				viewProps.put(s.substring(0,index),s.substring(index+1));
			}
		}
		p.put("viewcriteria",viewProps);

	}
	if(p.containsKey("TABLE-COLUMNS"))
	{
		Properties tableProps=new Properties();
		String tableColumn=p.getProperty("TABLE-COLUMNS");
		StringTokenizer tok=new StringTokenizer(tableColumn,";");
		while(tok.hasMoreTokens())
		{
			String s=(String)tok.nextToken();
			int firstIndex=s.indexOf("=");
			int  lastIndex=s.lastIndexOf("=");
			if(firstIndex != -1&& lastIndex!=-1)
			{
				tableProps.put(s.substring(firstIndex+1,lastIndex),s.substring(0,firstIndex));
			}
			
		}
		p.put("tablecolumns",tableProps);
	}
	return p;
}
public boolean delete(String viewId,String user,String module)
{
		boolean result=true;
		try
		{
			result= treeAPI.removeNode(viewId,user,module,deleteAllChilds);
		}
		catch(Exception e)
		{
			System.out.println("Exception while useing deleteNode in treeAPI"+e.getMessage());
		
			return false;
		}
		return result;
}
public class NodeRenderer extends DefaultTreeCellRenderer
{
	public Component getTreeCellRendererComponent(JTree tree,Object value,boolean selected,boolean expanded,boolean leaf,int row,	boolean hasFocus)
	{
	   	super.getTreeCellRendererComponent(tree,value,selected,expanded,leaf,row,hasFocus);
		if(value != null)
		{				
								
			String name = value.toString();
			String iconName="";
			url=null;
			if(name.equals("Custom View Types"))
			{
			 	iconName="file:///"+System.getProperty("user.dir")+File.separator+"images"+File.separator+"panel.png";	
			}
			else if(name.equals("ALERT CUSTOMVIEW"))
			{
				iconName="file:///"+System.getProperty("user.dir")+File.separator+"images"+File.separator+"alarm.png";	
			}
			else if(name.equals("EVENT CUSTOMVIEW"))
			{
				iconName="file:///"+System.getProperty("user.dir")+File.separator+"images"+File.separator+"event.png";	
			}
			else if(name.equals("PERFORMANCE CUSTOMVIEW"))
			{
				iconName="file:///"+System.getProperty("user.dir")+File.separator+"images"+File.separator+"perfmgmttreeicon.png";				
			}
			else if(name.equals("TOPO CUSTOMVIEW"))
			{
				iconName="file:///"+System.getProperty("user.dir")+File.separator+"images"+File.separator+"ntwkdbtreeicon.png";				
			}
			else 
			{

				DefaultMutableTreeNode node=(DefaultMutableTreeNode)value;
				//In leaves only we are having customviews
				if(node.isLeaf())
				{	
						Object object=node.getUserObject();
						if(object instanceof CustomViewObject)
						{
							custViewObject=(CustomViewObject)object;
							String icon=custViewObject.getTreeIcon();
							if(!icon.equals(""))
							{
								iconName="file:///"+System.getProperty("user.dir")+File.separator+icon;
							}
						}	
						
				}
			}
			if(!iconName.equals(""))
			{
				if(ImgConv.getImage(iconName)!=null)
				{
					setIcon(new ImageIcon(ImgConv.getImage(iconName)));
				}
			}
			else
			{
				 setIcon(null);								
			}
			
		}
		return this;
	}
	
}

}










































































