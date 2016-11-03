//$Id: MainScreen.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.applet.Applet;
import javax.swing.tree.*;
import javax.swing.event.*;
// import package for XML handling.
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import com.adventnet.nms.util.BrowserControl;
import com.adventnet.nms.tools.utils.*;
import java.net.MalformedURLException;
import com.adventnet.nms.tools.objtorel.*;
import com.adventnet.editor.text.JavaTextPane;
import com.adventnet.editor.JMTextPane;
import com.adventnet.apiutils.Utility;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;
import java.util.Locale;

public class MainScreen extends JFrame implements FileFilter,TreeSelectionListener,ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JSplitPane horizontalSplit = null;
	javax.swing.JSplitPane verticalSplit = null;
	javax.swing.JScrollPane ProjectTreeScroller = null;
	javax.swing.JTree ProjectTree = null;
	javax.swing.JScrollPane ProjectItemTreeScroller = null;
	javax.swing.JTree ProjectItemTree = null;
	private JMenuBar menuBar=null;
	JMenu FileMenu= null;
	JMenuItem   New_ProjectMenuItem = null;
	JMenuItem   Delete_ProjectMenuItem = null;
	JMenuItem   Save_ProjectMenuItem = null;
	JMenu   NewMenu= null;
	JMenuItem   ManagedObjectMenuItem = null;
	JMenuItem   Status_PollerMenuItem = null;
	JMenuItem   Discovery_FilterMenuItem = null;
	JMenuItem   RDBMS_InterfaceMenuItem = null;
	JMenuItem   SaveMenuItem = null;
	JMenuItem   ExitMenuItem = null;
	JMenu EditMenu= null;
	JMenuItem   UndoMenuItem = null;
	JMenuItem   RedoMenuItem = null;
	JMenuItem   CutMenuItem = null;
	JMenuItem   CopyMenuItem = null;
	JMenuItem   PasteMenuItem = null;
	JMenuItem   FindMenuItem = null;
	JMenuItem   ReplaceMenuItem = null;
	JMenuItem   GoToMenuItem = null;
	JMenuItem   braceMatch=null;
	JMenuItem   indentMenuItem=null;
	JMenu BuildMenu= null;
	JMenuItem   CompileMenuItem = null;
	JMenuItem   RegenerateMenuItem = null;
	JMenuItem   OptionsMenuItem = null;
	JMenu ProjectMenu= null;
	JMenuItem   PropertiesMenuItem = null;
	JMenu HelpMenu= null;
	JMenuItem   ContentsMenuItem = null;
	JMenuItem   About_UsMenuItem = null;
	private JToolBar toolBar = null;
	private ImageIcon icon = null;
	JButton   NewButton = null;
	JButton   SaveButton = null;
	JButton   CutButton = null;
	JButton   CopyButton = null;
	JButton   PasteButton = null;
	JButton   CompileButton = null;
	JButton   HelpButton = null;
	JButton   ExitButton = null;
	MenuToolBarAction menuToolBarAction = new MenuToolBarAction();
	//<End_Variable_Declarations>
	// List of custom Declerations for MainScreen.java
	// Some of the Dialogs,Frames which are used in this tool are used here.
	//JTextArea sourceArea=null;
	MOEditor editor=null;
	JScrollPane sourceScroll=null;
	JDialog newProjectDlg;
	TransversePanel tp=null;
	MOPropChooser mopropch=null;
	AddDeviceScr     ads=null;
	AddObjProp     aos=null;
	addCustProps  acp=null;
	//SourceEditor  sEditor=null;
	wizardResult wizard=null;
	SourceScr sscr=null;
	String fileFilterExtensions[]=null;
	DefaultMutableTreeNode rootNode=null,popupMenuSelectedNode=null;
	DefaultTreeModel dtm=null;
	FocusListener treeFocusListener=null;
	JDialog newClassDialog=null;
	JPopupMenu leafNodePopup=null;
	JMenuItem  popupSave=null;
	JMenuItem  popupModify=null;
	JMenuItem  popupDelete=null;
	JMenuItem  undoMenuItem=null;
	JMenuItem redoMenuItem=null;
	JMenuItem gotoMenuItem=null;
	CompileScr compile=null;
	SourceProperty scp=null;
	TransversePanel Disctp=null;
	FileWriter DscFil_fWrite=null;
	FileWriter UserTester=null;
	DiscStatus userStat=null;
	TransversePanel Usertp=null;
	JDialog dialog=null;
	DiscStatus discStat=null;
	JLabel imageLabel=new JLabel("");
	JLabel emptySourceLabel=new JLabel();
	String sourceNotDefined=resourceBundle.getString("<HTML><P><B><FONT size=+1>Source is not Defined for ");
	String fs=File.separator;
	int    divLocSize=190;

	private BrowserControl bc=null;
	private HelpConfReader hcr=null;
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;

	public MainScreen()
	{
		//<Begin_MainScreen>
		pack();
		this.setTitle("MainScreen");

		//<End_MainScreen>
	}

	public MainScreen(java.applet.Applet applet)
	{
		//<Begin_MainScreen_java.applet.Applet>
		this.applet = applet;
		pack();
		this.setTitle("MainScreen");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		//<End_MainScreen_java.applet.Applet>
	}

	public void setVisible(boolean bl)
	{
		if(false) {
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

	public void setUpProperties()
	{
		//<Begin_setUpProperties>

		try
		{
			horizontalSplit.setDividerSize(5);
			horizontalSplit.setAutoscrolls(true);
			horizontalSplit.setForeground(new Color(-16777216));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+horizontalSplit,ex);
		}

		try
		{
			verticalSplit.setOrientation(0);
			verticalSplit.setLastDividerLocation(400);
			verticalSplit.setAutoscrolls(true);
			verticalSplit.setDividerSize(5);
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+verticalSplit,ex);
		}

		try
		{
			ProjectTree.setAutoscrolls(true);
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ProjectTree,ex);
		}
		horizontalSplit.setPreferredSize(new Dimension(horizontalSplit.getPreferredSize().width+621,horizontalSplit.getPreferredSize().height+0));


		//<End_setUpProperties>

		//horizontalSplit.setRightComponent(tp);
		ProjectTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		ProjectItemTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		ProjectTree.addTreeSelectionListener(this);
		ProjectTreeMouseListener ptml=new ProjectTreeMouseListener();
		ProjectTree.addMouseListener(ptml);
		ProjectItemTree.addTreeSelectionListener(this);
		ProjectTree.putClientProperty("JTree.lineStyle","Angled");
		imageLabel.setPreferredSize(new Dimension(200,200));

	}

	public void init()
	{
		//<Begin_init>
		if (initialized == true) return;
		this.setSize(getPreferredSize().width+823,getPreferredSize().height+589);
		setTitle("new_screen2");
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
		// let us set the initialzed variable to true so
		// we dont initialize again even if init is called
		initialized = true;

		setUpMenus();
		setUpToolBar();


		//<End_init>
	}

	public void setUpConnections()
	{

		//<Begin_setUpConnections>


		//<End_setUpConnections>
	}

	public void showProjectProperties() {
		settings_Clicked();
	}

	public void initVariables()
	{
		//<Begin_initVariables>
		Top= new javax.swing.JPanel();
		horizontalSplit= new javax.swing.JSplitPane();
		verticalSplit= new javax.swing.JSplitPane();
		ProjectTreeScroller= new javax.swing.JScrollPane();
		ProjectTree= new javax.swing.JTree();
		ProjectItemTreeScroller= new javax.swing.JScrollPane();
		ProjectItemTree= new javax.swing.JTree();


		//<End_initVariables>
		DefaultMutableTreeNode root=new DefaultMutableTreeNode();
		DefaultTreeModel tm=new DefaultTreeModel(root);
		ProjectItemTree.setModel(tm);

		//sourceArea=new JTextArea();

		editor=new MOEditor();
		//	sourceScroll=new JScrollPane(editor);

		leafNodePopup=new JPopupMenu();
		popupSave=new JMenuItem(resourceBundle.getString("Save"));
		popupSave.setActionCommand("popupSave");
		popupModify=new JMenuItem(resourceBundle.getString("Modify"));
		popupModify.setActionCommand("popupModify");
		popupDelete=new JMenuItem(resourceBundle.getString("Delete"));
		popupDelete.setActionCommand("popupDelete");
		leafNodePopup.add(popupSave);
		leafNodePopup.add(popupModify);
		leafNodePopup.add(popupDelete);
		popupSave.addActionListener(this);
		popupDelete.addActionListener(this);
		popupModify.addActionListener(this);
		editor.setParentForFind(this);
		scp=new SourceProperty(editor,this);
	}

	public void setUpToolBar()
	{
		//<Begin_setUpToolBar>
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		NewButton = toolBar.add(menuToolBarAction);
		icon = com.adventnet.apiutils.Utility.findImage("./images/newfile.png",applet);
		NewButton.setPreferredSize(new Dimension(35,35));
		NewButton.setMinimumSize(new Dimension(35,35));
		NewButton.setMaximumSize(new Dimension(35,35));
		NewButton.setIcon(icon);
		NewButton.setActionCommand("New Project");
		NewButton.setToolTipText(resourceBundle.getString("New"));
		SaveButton = toolBar.add(menuToolBarAction);
		icon = com.adventnet.apiutils.Utility.findImage("./images/file.png",applet);
		SaveButton.setPreferredSize(new Dimension(35,35));
		SaveButton.setMinimumSize(new Dimension(35,35));
		SaveButton.setMaximumSize(new Dimension(35,35));
		SaveButton.setIcon(icon);
		SaveButton.setActionCommand("Save");
		SaveButton.setToolTipText(resourceBundle.getString("Save"));
		CutButton = toolBar.add(menuToolBarAction);
		icon = com.adventnet.apiutils.Utility.findImage("./images/cut.png",applet);
		CutButton.setPreferredSize(new Dimension(35,35));
		CutButton.setMinimumSize(new Dimension(35,35));
		CutButton.setMaximumSize(new Dimension(35,35));
		CutButton.setIcon(icon);
		CutButton.setActionCommand("Cut");
		CutButton.setToolTipText(resourceBundle.getString("Cut"));
		CopyButton = toolBar.add(menuToolBarAction);
		icon = com.adventnet.apiutils.Utility.findImage("./images/copy.png",applet);
		CopyButton.setPreferredSize(new Dimension(35,35));
		CopyButton.setMinimumSize(new Dimension(35,35));
		CopyButton.setMaximumSize(new Dimension(35,35));
		CopyButton.setIcon(icon);
		CopyButton.setActionCommand("Copy");
		CopyButton.setToolTipText(resourceBundle.getString("Copy"));
		PasteButton = toolBar.add(menuToolBarAction);
		icon = com.adventnet.apiutils.Utility.findImage("./images/paste.png",applet);
		PasteButton.setPreferredSize(new Dimension(35,35));
		PasteButton.setMinimumSize(new Dimension(35,35));
		PasteButton.setMaximumSize(new Dimension(35,35));
		PasteButton.setIcon(icon);
		PasteButton.setActionCommand("Paste");
		PasteButton.setToolTipText(resourceBundle.getString("Paste"));
		
		CompileButton = toolBar.add(menuToolBarAction);
		icon = com.adventnet.apiutils.Utility.findImage("./images/panel.png",applet);
		CompileButton.setPreferredSize(new Dimension(35,35));
		CompileButton.setMinimumSize(new Dimension(35,35));
		CompileButton.setMaximumSize(new Dimension(35,35));
		CompileButton.setIcon(icon);
		CompileButton.setActionCommand("Compile");
		CompileButton.setToolTipText(resourceBundle.getString("Compile"));
		HelpButton = toolBar.add(menuToolBarAction);
		icon = com.adventnet.apiutils.Utility.findImage("./images/help.jpg",applet);
		HelpButton.setPreferredSize(new Dimension(35,35));
		HelpButton.setMinimumSize(new Dimension(35,35));
		HelpButton.setMaximumSize(new Dimension(35,35));
		HelpButton.setIcon(icon);
		HelpButton.setActionCommand("Contents");
		HelpButton.setToolTipText(resourceBundle.getString("Help"));
		ExitButton = toolBar.add(menuToolBarAction);
		icon = com.adventnet.apiutils.Utility.findImage("./images/exit.jpg",applet);
		ExitButton.setPreferredSize(new Dimension(35,35));
		ExitButton.setMinimumSize(new Dimension(35,35));
		ExitButton.setMaximumSize(new Dimension(35,35));
		ExitButton.setIcon(icon);
		ExitButton.setActionCommand("Exit");
		ExitButton.setToolTipText(resourceBundle.getString("Exit"));
		JPanel toolBarPanel = new JPanel(new BorderLayout());
		toolBarPanel.add(toolBar);
		getContentPane().add(toolBarPanel,BorderLayout.NORTH);


		//<End_setUpToolBar>
	}

	public void setUpGUI(Container container)
	{
		//<Begin_setUpGUI_Container>
		container.add(Top,BorderLayout.CENTER);
		Top.setLayout(new BorderLayout(5,5));
		Top.add(horizontalSplit,BorderLayout.CENTER);
		horizontalSplit.setLeftComponent(verticalSplit);
		horizontalSplit.setDividerLocation(158);
		verticalSplit.setTopComponent(ProjectTreeScroller);
		verticalSplit.setDividerLocation(332);
		ProjectTreeScroller.getViewport().add(ProjectTree);
		verticalSplit.setBottomComponent(ProjectItemTreeScroller);
		ProjectItemTreeScroller.getViewport().add(ProjectItemTree);


		//<End_setUpGUI_Container>
		horizontalSplit.setDividerLocation(divLocSize);
		verticalSplit.setDividerLocation(200);
		horizontalSplit.setRightComponent(scp);
		verticalSplit.setBottomComponent(null);
	}

	public void setUpMenus()
	{
		//<Begin_setUpMenus>
		menuBar = new JMenuBar();
		FileMenu= new JMenu(ToolsUtil.getMenuName(resourceBundle.getString("File"),'F'));
		FileMenu.setMnemonic('F');
		menuBar.add(FileMenu);
		New_ProjectMenuItem = new JMenuItem(resourceBundle.getString("New Project"));
		New_ProjectMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		New_ProjectMenuItem.setActionCommand("New Project");
		New_ProjectMenuItem.addActionListener(menuToolBarAction);
		FileMenu.add(New_ProjectMenuItem);
		Delete_ProjectMenuItem = new JMenuItem(resourceBundle.getString("Delete Project"));
		Delete_ProjectMenuItem.setActionCommand("Delete Project");
		Delete_ProjectMenuItem.addActionListener(menuToolBarAction);
		FileMenu.add(Delete_ProjectMenuItem);
		Save_ProjectMenuItem = new JMenuItem(resourceBundle.getString("Save Project"));
		Save_ProjectMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		Save_ProjectMenuItem.setActionCommand("Save Project");
		Save_ProjectMenuItem.addActionListener(menuToolBarAction);
		FileMenu.add(Save_ProjectMenuItem);
		FileMenu.addSeparator();
		NewMenu = new JMenu(resourceBundle.getString("New"));
		FileMenu.add(NewMenu);
		ManagedObjectMenuItem = new JMenuItem(resourceBundle.getString("ManagedObject"));
		ManagedObjectMenuItem.setActionCommand("ManagedObject");
		ManagedObjectMenuItem.addActionListener(menuToolBarAction);
		NewMenu.add(ManagedObjectMenuItem);
		Status_PollerMenuItem = new JMenuItem(resourceBundle.getString("Status Poller"));
		Status_PollerMenuItem.setActionCommand("User Tester");
		Status_PollerMenuItem.addActionListener(menuToolBarAction);
		NewMenu.add(Status_PollerMenuItem);
		Discovery_FilterMenuItem = new JMenuItem(resourceBundle.getString("Discovery Filter"));
		Discovery_FilterMenuItem.setActionCommand("Discovery Filter");
		Discovery_FilterMenuItem.addActionListener(menuToolBarAction);
		NewMenu.add(Discovery_FilterMenuItem);
		RDBMS_InterfaceMenuItem = new JMenuItem(resourceBundle.getString("RDBMS Interface"));
		RDBMS_InterfaceMenuItem.setActionCommand("RDBMS");
		RDBMS_InterfaceMenuItem.addActionListener(menuToolBarAction);
		NewMenu.add(RDBMS_InterfaceMenuItem);
		SaveMenuItem = new JMenuItem(resourceBundle.getString("Save"));
		SaveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		SaveMenuItem.setActionCommand("Save");
		SaveMenuItem.addActionListener(menuToolBarAction);
		FileMenu.add(SaveMenuItem);
		FileMenu.addSeparator();
		ExitMenuItem = new JMenuItem(ToolsUtil.getMenuName(resourceBundle.getString("Exit"),'X'));
		ExitMenuItem.setMnemonic('X');
		ExitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,+ActionEvent.ALT_MASK));
		ExitMenuItem.setActionCommand("Exit");
		ExitMenuItem.addActionListener(menuToolBarAction);
		FileMenu.add(ExitMenuItem);
		EditMenu= new JMenu(ToolsUtil.getMenuName(resourceBundle.getString("Edit"),'E'));
		EditMenu.setMnemonic('E');
		menuBar.add(EditMenu);
		UndoMenuItem = new JMenuItem(resourceBundle.getString("Undo"));
		UndoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
		UndoMenuItem.setActionCommand("Undo");
		UndoMenuItem.addActionListener(menuToolBarAction);
		EditMenu.add(UndoMenuItem);
		RedoMenuItem = new JMenuItem(resourceBundle.getString("Redo"));
		RedoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));
		RedoMenuItem.setActionCommand("Redo");
		RedoMenuItem.addActionListener(menuToolBarAction);
		EditMenu.add(RedoMenuItem);
		CutMenuItem = new JMenuItem(resourceBundle.getString("Cut"));
		CutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		CutMenuItem.setActionCommand("Cut");
		CutMenuItem.addActionListener(menuToolBarAction);
		EditMenu.add(CutMenuItem);
		CopyMenuItem = new JMenuItem(resourceBundle.getString("Copy"));
		CopyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		CopyMenuItem.setActionCommand("Copy");
		CopyMenuItem.addActionListener(menuToolBarAction);
		EditMenu.add(CopyMenuItem);
		PasteMenuItem = new JMenuItem(resourceBundle.getString("Paste"));
		PasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		PasteMenuItem.setActionCommand("Paste");
		PasteMenuItem.addActionListener(menuToolBarAction);
		EditMenu.add(PasteMenuItem);
		EditMenu.addSeparator();
		/*
		   FindMenuItem = new JMenuItem("Find");
		   FindMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		   FindMenuItem.setActionCommand("Find");
		   FindMenuItem.addActionListener(menuToolBarAction);
		   EditMenu.add(FindMenuItem); */
		ReplaceMenuItem = new JMenuItem(resourceBundle.getString("Find/Replace"));
		ReplaceMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		ReplaceMenuItem.setActionCommand("Replace");
		ReplaceMenuItem.addActionListener(menuToolBarAction);
		EditMenu.add(ReplaceMenuItem);
		GoToMenuItem = new JMenuItem(resourceBundle.getString("GoTo"));
		GoToMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
		GoToMenuItem.setActionCommand("GoTo");
		GoToMenuItem.addActionListener(menuToolBarAction);
		EditMenu.add(GoToMenuItem);
        braceMatch= new JMenuItem(resourceBundle.getString("Match Brace"));

		braceMatch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
		braceMatch.setActionCommand("Match Brace");
		braceMatch.addActionListener(menuToolBarAction);
		EditMenu.add(braceMatch);


		indentMenuItem=new JMenuItem(resourceBundle.getString("Indent"));	
		indentMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
		indentMenuItem.setActionCommand("GoTo");
		indentMenuItem.addActionListener(menuToolBarAction);
		BuildMenu= new JMenu(ToolsUtil.getMenuName(resourceBundle.getString("Build"),'B'));
		BuildMenu.setMnemonic('B');
		menuBar.add(BuildMenu);
		CompileMenuItem = new JMenuItem(resourceBundle.getString("Compile"));
		CompileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
		CompileMenuItem.setActionCommand("Compile");
		CompileMenuItem.addActionListener(menuToolBarAction);
		BuildMenu.add(CompileMenuItem);
		RegenerateMenuItem = new JMenuItem(resourceBundle.getString("Regenerate"));
		RegenerateMenuItem.setActionCommand("Regenerate");
		RegenerateMenuItem.addActionListener(menuToolBarAction);
		BuildMenu.add(RegenerateMenuItem);
		OptionsMenuItem = new JMenuItem(resourceBundle.getString("Options"));
		OptionsMenuItem.setActionCommand("Options");
		OptionsMenuItem.addActionListener(menuToolBarAction);
		BuildMenu.add(OptionsMenuItem);
		ProjectMenu= new JMenu(ToolsUtil.getMenuName(resourceBundle.getString("Project"),'P'));
		ProjectMenu.setMnemonic('P');
		menuBar.add(ProjectMenu);
		PropertiesMenuItem = new JMenuItem(resourceBundle.getString("Properties"));
		PropertiesMenuItem.setActionCommand("ProjectProperties");
		PropertiesMenuItem.addActionListener(menuToolBarAction);
		ProjectMenu.add(PropertiesMenuItem);
		HelpMenu= new JMenu(ToolsUtil.getMenuName(resourceBundle.getString("Help"),'H'));
		HelpMenu.setMnemonic('H');
		menuBar.add(HelpMenu);
		ContentsMenuItem = new JMenuItem(resourceBundle.getString("Contents"));
		ContentsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,ActionEvent.CTRL_MASK));
		ContentsMenuItem.setActionCommand("Contents");
		ContentsMenuItem.addActionListener(menuToolBarAction);
		HelpMenu.add(ContentsMenuItem);
		About_UsMenuItem = new JMenuItem(resourceBundle.getString("About Us"));
		About_UsMenuItem.setActionCommand("About Us");
		About_UsMenuItem.addActionListener(menuToolBarAction);
		HelpMenu.add(About_UsMenuItem);
		this.setJMenuBar(menuBar);


		//<End_setUpMenus>

		RegenerateMenuItem.setEnabled(false);

		JMenuItem[] menuItems={UndoMenuItem,RedoMenuItem,CopyMenuItem,CutMenuItem,PasteMenuItem,FindMenuItem,ReplaceMenuItem,GoToMenuItem,braceMatch,indentMenuItem};
		editor.setEditMenuItems(menuItems);
	}

	public void stop()
	{
		//<Begin_stop>


		//<End_stop>
	}

	public void start()
	{
		//<Begin_start>


		//<End_start>
	}

	void saveProject_Clicked() {
	}

	void close_Clicked() {
	}

	void find_Clicked() {
	}

	void replace_Clicked() {
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
			if (input.equals("PORT")) value = "161";
		}
		return value;


		//<End_getParameter_String>
	}





	class ProjectTreeMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent me) {
			if(SwingUtilities.isRightMouseButton(me)) {
				if(ProjectTree.getPathForLocation(me.getX(),me.getY())==null) {
					return;
				}
				DefaultMutableTreeNode node=(DefaultMutableTreeNode)ProjectTree.getPathForLocation(me.getX(),me.getY()).getLastPathComponent();

				if(node.getUserObject() instanceof java.lang.String) {
					return;
				}
				TreeNode selectedNodeArr[]=node.getPath();
				ProjectTree.setSelectionPath(new TreePath(selectedNodeArr));
				ManagedObjectModel mom=(ManagedObjectModel)node.getUserObject();
				switch(mom.getProjType()) {
					case -1:
						if(mom.isClassLoaded()) {
							popupSave.setEnabled(true);
						}
						else if (mom.getProjType()==-1) {
							popupSave.setEnabled(false);
						}
						popupModify.setText(resourceBundle.getString("Modify"));
						leafNodePopup.show(me.getComponent(),me.getX(),me.getY());
						popupMenuSelectedNode=node;
					break;
					case 1002:
					case 1001:
						leafNodePopup.remove(popupSave);
						leafNodePopup.remove(popupModify);
						leafNodePopup.show(me.getComponent(),me.getX(),me.getY());
					break;
				}
			}
		}
	}

	public void generateRelationalJava(DefaultMutableTreeNode relationalNode) {
		Object[] list=null;
		if(relationalNode==null) {
			if(ProjectTree.getSelectionPath()==null || ((DefaultMutableTreeNode)ProjectTree.getSelectionPath().getLastPathComponent()).getUserObject() instanceof java.lang.String){
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select the class under which you would like to add the Relational Java "),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
				return;
			}
			relationalNode=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();
		}
		if(relationalNode.getUserObject() instanceof java.lang.String) {
			return;
		}
		list=relationalNode.getPath();
		ManagedObjectModel mom=(ManagedObjectModel)relationalNode.getUserObject();
		if(mom.getProjType()!=-1) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a class and then add"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(findNodeinTree(relationalNode,"Relational Java")) {
			DefaultMutableTreeNode tempNode=getNamedChild(relationalNode,"Relational Java");
			if(tempNode!=null) {
				ManagedObjectModel relMom=(ManagedObjectModel)tempNode.getUserObject();
				if(relMom.getFileName()!=null && relMom.getFileName().toString().trim().length()!=0) {
					// TODO: Error Message
					if(JOptionPane.showConfirmDialog(null,resourceBundle.getString("Warning : Class already has Relational Java OverWrite ???"),resourceBundle.getString("Warning"),JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
						return;
					}
				}
			}
			list=tempNode.getPath();
		}
		ManagedObjectModel rootMom=(ManagedObjectModel)relationalNode.getUserObject();
		if(rootMom.getProjType()!=-1) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a class and then add"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}

		Document doc=rootMom.getDocInfo();
		Element classNode=(Element)doc.getElementsByTagName("CLASS").item(0);
		String  packageName=classNode.getAttribute("package");
		if(packageName==null) {
			packageName="";
		}
		String fullclassname=packageName+"."+relationalNode.toString();
		Initializer init=new Initializer(this,resourceBundle.getString("Relational Java - ")+relationalNode.toString());

		init.setSize(600,450);
		init.setLocation(getScrLoc(init));
		String outPathForRelJava="."+fs+"projects"+fs+((DefaultMutableTreeNode)relationalNode.getParent()).toString()+fs+relationalNode.toString();
		init.buildScreenForMO("."+fs+"projects"+fs+((DefaultMutableTreeNode)relationalNode.getParent()).toString()+fs+"classes"+fs+packageName.replace('.',File.separatorChar)+fs+relationalNode.toString()+".class",outPathForRelJava,fullclassname);
		init.setVisible(true);
		// Output Directory will be null only when user presses a cancel button.
		if(!init.getFinishStatus()) {
			return;
		}

		ManagedObjectModel newmom=new ManagedObjectModel();
		newmom.setClassNodeName(relationalNode.toString());
		newmom.setFileName(new File(outPathForRelJava+fs+"Relational"+relationalNode.toString()+".java"));
		newmom.setProjType(1003);
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			doc=db.parse(new File("."+fs+"projects"+fs+relationalNode.getParent()+fs+relationalNode.toString()+".mow"));
			((ManagedObjectModel)relationalNode.getUserObject()).setDocInfo(doc);
		}catch(Exception e) {
			e.printStackTrace();
		}

		if(findNodeinTree(relationalNode,"Relational Java")) {
			Element relJava=(Element)doc.getElementsByTagName("RELATIONAL_JAVA").item(0);
			if(relJava==null) {
				relJava=doc.createElement("RELATIONAL_JAVA");
			}
			relJava.setAttribute("className","com.adventnet.nms.store.relational.Relational"+relationalNode.toString());
			relJava.setAttribute("fileName",outPathForRelJava+fs+"Relational"+relationalNode.toString()+".java");
			DefaultMutableTreeNode childNode=getNamedChild(relationalNode,"Relational Java");
			list=childNode.getPath();
			ManagedObjectModel newRelmom=(ManagedObjectModel)childNode.getUserObject();
			newRelmom.setFileName(new File(outPathForRelJava+fs+"Relational"+relationalNode.toString()+".java"));

			try {
				if(editor.jmax.getEditor()!=null)
				{
					editor.jmax.closeFileWithOutPrompting(new File(outPathForRelJava+fs+"Relational"+relationalNode.toString()+".java"));
				}

				writeXMLFile(doc,nodeToFilePath((DefaultMutableTreeNode)relationalNode.getParent(),"")+fs+relationalNode.toString()+".mow");
				editor.OpenSourceFile(new File(outPathForRelJava+fs+"Relational"+relationalNode.toString()+".java").toString());

			}
			catch(FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
		}
		else {
			Element relElement=doc.createElement("RELATIONAL_JAVA");
			relElement.setAttribute("className","com.adventnet.nms.store.relational.Relational"+relationalNode.toString());
			relElement.setAttribute("fileName",outPathForRelJava+fs+"Relational"+relationalNode.toString()+".java");
			ManagedObjectModel relModel=new ManagedObjectModel();
			relModel.setProjType(1003);
			relModel.setClassNodeName("Relational Java");
			relModel.setFileName(new File(outPathForRelJava+fs+"Relational"+relationalNode.toString()+".java"));
			DefaultMutableTreeNode relJavaNode=new DefaultMutableTreeNode(relModel);
			list=relJavaNode.getPath();
			doc.getDocumentElement().appendChild(relElement);
			relationalNode.add(relJavaNode);
			/*try {
			  writeXMLFile(doc,nodeToFilePath((DefaultMutableTreeNode)relationalNode.getParent(),"")+fs+relationalNode.toString()+".mow");
			  }
			  catch(FileNotFoundException fnfe) {
			  fnfe.printStackTrace();
			  } */
		}
		ProjectTree.updateUI();
		restoreFocus(list);
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==popupSave) {
			popupSave_Clicked(ae);
		}
		else if(ae.getSource()==popupDelete) {
			popupDelete_Clicked(ae);
		}
		else if(ae.getSource()==popupModify) {
			popupModify_Clicked(ae);
		}
	}

	public void showStatus(String message)
	{
		//<Begin_showStatus_String>
		System.out.println(resourceBundle.getString("Internal Error :")+ message);
		//<End_showStatus_String>
	}

	public void showStatus(String message,Exception ex)
	{
		//<Begin_showStatus_String_Exception>
		System.out.println(resourceBundle.getString("Internal Error :")+ message);
		ex.printStackTrace();
		//<End_showStatus_String_Exception>
	}

	public Point getScrLoc(Component comp) {
		// This method will return the x,y that will place a container in the center
		// of the screen.
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int)(d.getWidth()-comp.getSize().getWidth())/2;
		int height=(int)(d.getHeight()-comp.getSize().getHeight())/2;
		return new Point(width,height);
	}


	public void newProject_Clicked() {
		newProjectDlg=new JDialog(this,resourceBundle.getString("New Project"),true);
		New  newProjPanel=new New();
		newProjectDlg.getContentPane().add(newProjPanel,BorderLayout.CENTER);
		newProjectDlg.pack();
		newProjectDlg.setSize(new Dimension(450,350));
		newProjectDlg.setResizable(false);
		newProjectDlg.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
				newProjectDlg.dispose();
				}
				});
		newProjectDlg.setLocation(getScrLoc(newProjectDlg));
		newProjectDlg.setVisible(true);
		if(newProjPanel.addedProjectTypeInfo()==null) {
			return;
		}
		String newProjectName=newProjPanel.addedProjectTypeInfo();
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(new File(getNmsHome()+"projects"+fs+newProjectName+".proj"));
			NodeList propNodeList=doc.getElementsByTagName("property");
			Element rootNode=null;
			for(int i=0;i<propNodeList.getLength();i++) {
				rootNode=(Element)propNodeList.item(i);
				if(rootNode.getAttribute("name").equals("projectType")) {
					ManagedObjectModel objModel=new ManagedObjectModel();
					objModel.setClassNodeName(newProjectName);
					objModel.setPackageName(newProjPanel.getPackageName());
					objModel.setLeaf(true);
					objModel.setProjType(Integer.parseInt(rootNode.getAttribute("value")));
					DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(objModel);
					((DefaultMutableTreeNode)ProjectTree.getModel().getRoot()).add(newNode);
					ProjectTree.updateUI();
					defineNewClass(newNode);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String getNmsHome() {
		return "."+File.separator;
	}

	public boolean isMOWizardProject(File projName) {
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(projName);
			NodeList propertyList=doc.getElementsByTagName("property");
			for(int i=0;i<propertyList.getLength();i++) {
				Element node=(Element)propertyList.item(i);
				if(node.getAttribute("name").equals("ToolName") && node.getAttribute("value").equals("MOWizard")) {
					return true;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void loadProjectInfo() {
		File f=new File(getNmsHome()+"projects"+File.separator);
		if(!f.exists()) {
			f.mkdir();
		}
		String fileExtensions[] =  { ".proj" };
		fileFilterExtensions=fileExtensions;
		File projFiles[]=f.listFiles(this);
		if(projFiles==null) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Running ManagedObjectWizard with no projects"),resourceBundle.getString("Info"),JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// Read all the .proj Files.
		try {
			rootNode=new DefaultMutableTreeNode("Projects");
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=null;
			for(int i=0;i<projFiles.length;i++) {
				doc=db.parse(projFiles[i]);
				if(!isMOWizardProject(projFiles[i])) {
					// Here we validate whether the project is created by MOWizard.
					continue;
				}
				DefaultMutableTreeNode newNode=documentToModel(doc);
				if(newNode==null) {
					continue;
				}
				rootNode.add(newNode);
				loadClasses(projFiles[i].getName(),newNode);
			}
			dtm=new DefaultTreeModel(rootNode);
			ProjectTree.setModel(dtm);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	DefaultMutableTreeNode documentToModel(Document doc) {
		// In this function we will read a .config file and create
		// a ManagedObject Model.  This will help us to get all
		// information from the ObjectModel than from file.
		NodeList propNodeList=doc.getElementsByTagName("property");
		ManagedObjectModel objModel=new ManagedObjectModel();
		try {
			for(int i=0;i<propNodeList.getLength();i++) {
				Element rootNode=(Element)propNodeList.item(i);
				if(rootNode.getAttribute("name").equals("projectType")) {
					objModel.setProjType(Integer.parseInt(rootNode.getAttribute("value")));

				}
				if(rootNode.getAttribute("name").equals("projectName")) {
					objModel.setClassNodeName(rootNode.getAttribute("value"));
				}
				if(rootNode.getAttribute("name").equals("package")) {
					objModel.setPackageName(rootNode.getAttribute("value"));
				}
			}
		}catch(Exception nfe) {
			nfe.printStackTrace();
			System.err.println(resourceBundle.getString("Error while converting XMLDocument to ManagedObject Model : Integer argument Expected"));
			return null;
		}
		DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(objModel);
		return newNode;
	}

	private void loadClasses(String projectName,DefaultMutableTreeNode rootNode) {
		// Take the ProjectName from this name go to that directory.
		// and load all the .config files.
		File f=new File(getNmsHome()+"projects"+fs+projectName.substring(0,projectName.indexOf("."))+File.separator);
		String fileExtensions[]= { ".mow" };
		fileFilterExtensions=fileExtensions;
		File classFiles[]=f.listFiles(this);
		for(int i=0;classFiles!=null && i<classFiles.length;i++) {
			ManagedObjectModel objModel=configFileToModel(classFiles[i],((ManagedObjectModel)rootNode.getUserObject()).getPackageName());
			DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(objModel);
			loadClassComponents(projectName,classFiles[i],newNode);
			rootNode.add(newNode);
		}
	}


	private void loadClassComponents(String projectName,File className,DefaultMutableTreeNode classNode) {
		File f=new File(getNmsHome()+"projects"+fs+projectName.substring(0,projectName.indexOf("."))+fs+className.getName()+"");
		ManagedObjectModel mom1=(ManagedObjectModel)classNode.getUserObject();
		mom1.setFileName(new File(className.getParent()+fs+className.getName().substring(0,className.getName().indexOf("."))+fs+className.getName().substring(0,className.getName().indexOf("."))+".java"));
		mom1.setClassNodeName(className.getName().substring(0,className.getName().indexOf(".")));
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(f);

			Element userTester=(Element)doc.getElementsByTagName("USER_TESTER").item(0);
			if(userTester!=null) {
				String userclassName=userTester.getAttribute("className");
				String userfileName=userTester.getAttribute("fileName");


				ManagedObjectModel ustmom=new ManagedObjectModel();
				ustmom.setProjType(1001);
				ustmom.setFileName(new File(userfileName));
				ustmom.setClassNodeName(userclassName);
				DefaultMutableTreeNode userTestNode=new DefaultMutableTreeNode(ustmom);
				classNode.add(userTestNode);
			}
			Element discFilter=(Element)doc.getElementsByTagName("DISC_FILTER").item(0);
			if(discFilter!=null) {
				String discclassName=discFilter.getAttribute("className");
				String discfileName=discFilter.getAttribute("fileName");

				ManagedObjectModel dscmom=new ManagedObjectModel();
				dscmom.setProjType(1002);
				dscmom.setFileName(new File(discfileName));
				dscmom.setClassNodeName(discclassName);
				DefaultMutableTreeNode discFilterNode=new DefaultMutableTreeNode(dscmom);
				classNode.add(discFilterNode);
			}
			Element relJava=(Element)doc.getElementsByTagName("RELATIONAL_JAVA").item(0);
			if(relJava!=null) {
				String relJavaclassName=relJava.getAttribute("className");
				String relJavafileName=relJava.getAttribute("fileName");

				ManagedObjectModel dscmom=new ManagedObjectModel();
				dscmom.setProjType(1003);
				if(relJavaclassName!=null) {
					dscmom.setFileName(new File(relJavafileName));
					dscmom.setClassNodeName(relJavaclassName);
				}
				DefaultMutableTreeNode relJavaNode=new DefaultMutableTreeNode(dscmom);
				classNode.add(relJavaNode);
			}
			Element depNode=(Element)doc.getElementsByTagName("DEPLOYMENT").item(0);
			if(depNode!=null) {
				String depClassName=relJava.getAttribute("className");
				String depFileName=relJava.getAttribute("fileName");

				ManagedObjectModel dscmom=new ManagedObjectModel();
				dscmom.setProjType(1004);
				if(depClassName!=null) {
					dscmom.setFileName(new File(depFileName));
					dscmom.setClassNodeName(depClassName);
				}
				DefaultMutableTreeNode relJavaNode=new DefaultMutableTreeNode(dscmom);
				classNode.add(relJavaNode);
			}

		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		catch(SAXException saxe) {
			saxe.printStackTrace();
		}
		catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/*public void initializeForMO() {
	  try {
	  File f=new File(getNmsHome()+"projects"+fs+"classes");
	  if(f.exists()==false) {
	  f.mkdir();
	  }
	  }
	  catch(Exception e) {
	  e.printStackTrace();
	  }
	  }*/


	public ManagedObjectModel configFileToModel(File f,String packageName) {
		ManagedObjectModel objModel=new ManagedObjectModel();
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(f);
			objModel.setLeaf(false);
			objModel.setProjType(-1);
			objModel.setClassNodeName(f.getName().substring(0,f.getName().indexOf(".")));
			objModel.setDocInfo(doc);
			objModel.setPackageName(packageName);
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		catch(SAXException saxe) {
			saxe.printStackTrace();
		}
		catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return objModel;
	}

	public void valueChanged(TreeSelectionEvent tse) {
		if(tse.getSource()==ProjectTree) {
			ProjectTree_Clicked(tse);
		}
	}

	String nodeToFilePath(DefaultMutableTreeNode node,String extn) {
		String filePath=constructPath((TreeNode)node);
		filePath=filePath.substring(0,1).toLowerCase()+filePath.substring(1,filePath.lastIndexOf(File.separator))+extn;
		filePath=getNmsHome()+filePath;
		return filePath;
	}

	String constructPath(TreeNode selectedNode) {
		if(selectedNode==null) {
			return "";
		}
		return constructPath(selectedNode.getParent())+selectedNode.toString()+File.separator;
	}

	void ProjectTree_Clicked(TreeSelectionEvent tse) {
		// Get The Node which generated the event.
		// Get the Model from that node and using that load that project
		// Set the right Component to
		handle_OldSelectedNode(tse);
		handle_NewSelectedNode(tse);
	}

	void saveClicked() {
		// get the Selected Node
		// from the node get the Project Type
		// based on the project Type switch according to the project type.
		if(ProjectTree.getLastSelectedPathComponent()==null) {
			return;
		}
		DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();
		if(selectedNode.getUserObject() instanceof java.lang.String) {
			return;
		}
		ManagedObjectModel mom=(ManagedObjectModel)selectedNode.getUserObject();
		// Code Merger should also be integrated here.
		switch(mom.getProjType()) {
			case -1:
			case 1001: // Status Poller Node.
			case 1002: // Discovery Filter.
			case 1003: // RelationalJava Source FIle.
				if(!editor.jmax.getEditor().contentModified()) {
					return;
				}
				//try {
				/*
				   FileOutputStream fout=new FileOutputStream(mom.getFileName());

				   fout.write(((JMTextPane)editor.jmax.getEditor()).getJavaTextPane().getText().getBytes());
				   fout.close(); */
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Saved file :")+mom.getFileName().getName(),resourceBundle.getString("Info"),JOptionPane.INFORMATION_MESSAGE);
				editor.saveSourceFile(mom.getFileName());
				/*}
				  catch(IOException ioe) {
				  ioe.printStackTrace();
				  }*/
				break;
		}
	}

	void handle_OldSelectedNode(TreeSelectionEvent tse) {
		// The component which loses focus is selected in this
		// if construct.
		if(tse.getOldLeadSelectionPath()==null) {
			return;
		}

		DefaultMutableTreeNode oldNode=(DefaultMutableTreeNode)tse.getOldLeadSelectionPath().getLastPathComponent();
		if(oldNode.isLeaf()) {
			String filePath=constructPath((TreeNode)tse.getOldLeadSelectionPath().getLastPathComponent());
			filePath=filePath.substring(0,1).toLowerCase()+filePath.substring(1,filePath.lastIndexOf(File.separator))+".mow";
			if(oldNode.getUserObject() instanceof java.lang.String) {
				return;
			}
		}
	}

	void writeXMLFile(Node rootNode,String pathForXMLFile) throws FileNotFoundException {
		try {
			TransformerFactory tff=TransformerFactory.newInstance();
			Transformer tf=tff.newTransformer();
			DOMSource   dSource=new DOMSource(rootNode);
			StreamResult sResult=new StreamResult(pathForXMLFile);
			tf.transform(dSource,sResult);
		}
		catch(TransformerFactoryConfigurationError tfcERR) {
			throw new FileNotFoundException(tfcERR.getMessage());
		}
		catch(TransformerConfigurationException tfcEx) {
			throw new FileNotFoundException(tfcEx.getMessage());
		}
		catch(TransformerException tfe) {
			throw new FileNotFoundException(tfe.getMessage());
		}
	}

	void handle_NewSelectedNode(TreeSelectionEvent tse) {
		// The newly selected component is selected in this construct.
		/*
		if(tse.getNewLeadSelectionPath()==null) {
			return;
		}
		*/
		if(ProjectTree.getLastSelectedPathComponent()==null) {
			return;
		}

		DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();
		if(selectedNode.getUserObject() instanceof java.lang.String) {
			//sourceArea.setText("");
			return;
		}
		ManagedObjectModel object=(ManagedObjectModel)selectedNode.getUserObject();
		scp.setServiceType(object.getProjType());

		// get file name from Model.
		// construct Path.
		// read file
		// display in screen.
		File f=object.getFileName();
		// Since deployment node need not for be checked for file name it is
		// skipped here.
		if(object.getProjType()!=1004) {
			if(f==null || f.toString().trim().length()==0) {
				divLocSize=horizontalSplit.getDividerLocation();
				scp.setActiveIndex(1);
				emptySourceLabel.setHorizontalAlignment(JLabel.CENTER);
				emptySourceLabel.setVerticalAlignment(JLabel.TOP);
				emptySourceLabel.setText(resourceBundle.getString("<HTML><P><B><FONT size=+2> Source Not Defined</FONT> </B></P></HTML>"));
				scp.setSourceTabComponent(emptySourceLabel);
				horizontalSplit.setDividerLocation(divLocSize);
			}
			else {
				scp.setActiveIndex(0);
				divLocSize=horizontalSplit.getDividerLocation();
				scp.setSourceTabComponent(editor);
				editor.OpenSourceFile(f.toString());
				horizontalSplit.setDividerLocation(divLocSize);
			}
		}
		else {
			divLocSize=horizontalSplit.getDividerLocation();
			emptySourceLabel.setHorizontalAlignment(JLabel.CENTER);
			emptySourceLabel.setVerticalAlignment(JLabel.TOP);
			emptySourceLabel.setText(resourceBundle.getString("<HTML><P><B><FONT size=+2>Please click on the Property Tab</FONT></B></P></HTML>"));
			scp.setSourceTabComponent(emptySourceLabel);
			horizontalSplit.setDividerLocation(divLocSize);
			scp.setActiveIndex(1);
		}
		switch(object.getProjType()) {
			case -1:
				divLocSize=horizontalSplit.getDividerLocation();
				horizontalSplit.setRightComponent(scp);
				horizontalSplit.setDividerLocation(divLocSize);
				scp.setServiceHelper(object.getDocInfo());
				scp.setPropTabService(-1);
				break;
			case 1001:
				divLocSize=horizontalSplit.getDividerLocation();
				horizontalSplit.setRightComponent(scp);
				horizontalSplit.setDividerLocation(divLocSize);
				object=((ManagedObjectModel)((DefaultMutableTreeNode)selectedNode.getParent()).getUserObject());
				scp.setServiceHelper(object.getDocInfo());
				scp.setPropTabService(1001);
				break;
			case 1002:
				divLocSize=horizontalSplit.getDividerLocation();
				horizontalSplit.setRightComponent(scp);
				horizontalSplit.setDividerLocation(divLocSize);
				object=((ManagedObjectModel)((DefaultMutableTreeNode)selectedNode.getParent()).getUserObject());
				scp.setServiceHelper(object.getDocInfo());
				scp.setPropTabService(1002);
				break;
			case 1003:
				divLocSize=horizontalSplit.getDividerLocation();
				horizontalSplit.setRightComponent(scp);
				horizontalSplit.setDividerLocation(divLocSize);
				object=((ManagedObjectModel)((DefaultMutableTreeNode)selectedNode.getParent()).getUserObject());
				scp.setServiceHelper(object.getDocInfo());
				scp.setPropTabService(1003);
				break;
			case 1004:
				divLocSize=horizontalSplit.getDividerLocation();
				// Since This node corresponds to Deployment change the
				// Right component to Deployment.
				horizontalSplit.setDividerLocation(divLocSize);
				DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode)selectedNode.getParent();
				ManagedObjectModel parentMom=(ManagedObjectModel)parentNode.getUserObject();
				scp.setDeployInfo(parentMom.getDocInfo(),selectedNode,parentNode.getParent().toString());
				scp.setPropTabService(1004);
				break;
			case 1:
				divLocSize=horizontalSplit.getDividerLocation();
				horizontalSplit.setRightComponent(scp);
				horizontalSplit.setDividerLocation(divLocSize);
				scp.setServiceHelper(object.getDocInfo());
				scp.setAdditionalInfo(selectedNode.toString());
				scp.setPropTabService(1);
				break;

		}
	}

	public boolean accept(File f) {
		// Since this method can be used more than once in different
		// situations, we use a global variable to set the list of
		// extensions for which this filter should return true.
		if(fileFilterExtensions==null) {
			return true;
		}
		for(int i=0;i<fileFilterExtensions.length;i++) {
			if(f.getName().toLowerCase().endsWith(fileFilterExtensions[i].trim().toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	void callDiscoveryFilter(DefaultMutableTreeNode selectedNode){
		Object[] list=null;
		if(selectedNode==null) {
			if(ProjectTree.getSelectionPath()==null || ((DefaultMutableTreeNode)ProjectTree.getSelectionPath().getLastPathComponent()).getUserObject() instanceof java.lang.String){
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select the class under which you would like to add the Discovery Filter"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
				return;
			}
			selectedNode=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();
		}
		if(selectedNode.getUserObject() instanceof java.lang.String)  {
			return;
		}
		list=selectedNode.getPath();
		ManagedObjectModel mom=(ManagedObjectModel)selectedNode.getUserObject();
		if(mom.getProjType()!=-1) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a class and then add"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		//FileWriter DiscFilter=new FileWriter(this,"Editor for DiscoveryFilter");
		TransverseContainer tCon=new TransverseContainer();
		if(mom.getProjType()!=-1) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a class and then add"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(findNodeinTree(selectedNode,"Discovery Filter")) {
			// TODO: Error Message
			DefaultMutableTreeNode tempNode=getNamedChild(selectedNode,"Discovery Filter");
			if(tempNode!=null) {
				ManagedObjectModel tempMom=(ManagedObjectModel)tempNode.getUserObject();
				if(tempMom.getFileName()!=null && tempMom.getFileName().toString().trim().length()!=0)  {
					if(JOptionPane.showConfirmDialog(null,resourceBundle.getString("Warning : Class already has Discovery Filter OverWrite ???"),resourceBundle.getString("Warning"),JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
						return;
					}
				}
			}
			list=tempNode.getPath();
		}
		tCon.addTransverseComponent("currentProjectPath",getNmsHome()+"projects"+fs+selectedNode.getParent().toString()+File.separator);
		tCon.addTransverseComponent("currentProject","."+fs+"projects"+fs+selectedNode.getParent().toString()+File.separator);
		tCon.addTransverseComponent("XMLMODEL",mom.getDocInfo());
		Disctp=new TransversePanel();
		Disctp.setImageLabel(imageLabel);
		Disctp.addTransverseContainer(tCon);
		discStat=new DiscStatus();
		discStat.setServiceType(2);
		discStat.defineDiscoveryFilter();
		DscFil_fWrite=new FileWriter();
		Disctp.addComponents("DiscFilter",discStat,"."+fs+"images"+fs+"discoveryfilter.jpg");
		Disctp.addComponents("DiscFile",DscFil_fWrite,"."+fs+"images"+fs+"discoveryfilter.jpg");
		Disctp.initialize();
		dialog=new JDialog(this,resourceBundle.getString("Editor For Discovery Filter"),true);
		dialog.getContentPane().add(Disctp,BorderLayout.CENTER);
		dialog.getContentPane().add(imageLabel,BorderLayout.WEST);
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		//dialog.setSize((int)size.getWidth()*3/4,(int)size.getHeight()*3/4-25);
		dialog.setSize(new Dimension(800,550));
		dialog.setLocation(getScrLoc(dialog));
		dialog.setTitle(resourceBundle.getString("Editor For Discovery Filter"));
		dialog.setVisible(true);

		if(tCon.getTransverseComponent("DSC_FIL_CLSNAME")==null) {
			return;
		}
		String DiscFilterClassName=(String)tCon.getTransverseComponent("DSC_FIL_CLSNAME");
		String DiscFilterFileName=(String)tCon.getTransverseComponent("DSC_FIL_FILENAME");
		Document doc=mom.getDocInfo();
		if(findNodeinTree(selectedNode,"Discovery Filter")) {
			Element discFilter=(Element)doc.getElementsByTagName("DISC_FILTER").item(0);
			discFilter.setAttribute("className",DiscFilterClassName);
			discFilter.setAttribute("fileName",DiscFilterFileName);
			//update Tree for reflecting Discovery Filter
			DefaultMutableTreeNode childNode=getNamedChild(selectedNode,"Discovery Filter");
			list=childNode.getPath();
			ManagedObjectModel childMom=(ManagedObjectModel)childNode.getUserObject();
			childMom.setFileName(new File(DiscFilterFileName));
			try {
				writeXMLFile(doc,nodeToFilePath((DefaultMutableTreeNode)selectedNode.getParent(),"")+fs+selectedNode.toString()+".mow");
			}catch(FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
		}
		else {
			Element discFilter=doc.createElement("DISC_FILTER");
			discFilter.setAttribute("className",DiscFilterClassName);
			discFilter.setAttribute("fileName",DiscFilterFileName);
			ManagedObjectModel uModel=new ManagedObjectModel();
			uModel.setProjType(1002);
			uModel.setClassNodeName("Discovery Filter");
			uModel.setFileName(new File(DiscFilterFileName));
			DefaultMutableTreeNode uNode=new DefaultMutableTreeNode(uModel);
			list=uNode.getPath();
			doc.getDocumentElement().appendChild(discFilter);
			selectedNode.add(uNode);
			try {
				writeXMLFile(doc,nodeToFilePath((DefaultMutableTreeNode)selectedNode.getParent(),"")+fs+selectedNode.toString()+".mow");
			}catch(FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
		}
		ProjectTree.updateUI();
		restoreFocus(list);
	}

	boolean findNodeinTree(DefaultMutableTreeNode node,String whatToFind) {
		DefaultMutableTreeNode child=null;
		TreeModel tm=ProjectTree.getModel();
		for(int i=0;i<node.getChildCount();i++) {
			child=(DefaultMutableTreeNode)tm.getChild(node,i);
			if(child.toString().trim().equals(whatToFind)) {
				return true;
			}
		}
		return false;
	}

	DefaultMutableTreeNode getNamedChild(DefaultMutableTreeNode parentNode,String childName) {
		DefaultMutableTreeNode child=null;
		TreeModel tm=ProjectTree.getModel();
		for(int i=0;i<parentNode.getChildCount();i++) {
			child=(DefaultMutableTreeNode)tm.getChild(parentNode,i);
			if(child.toString().trim().equals(childName)) {
				return child;
			}
		}
		return null;
	}

	void callUserTester(DefaultMutableTreeNode selectedNode){
		Object[] list=null;
		if(selectedNode==null) {
			if(ProjectTree.getSelectionPath()==null || ((DefaultMutableTreeNode)ProjectTree.getSelectionPath().getLastPathComponent()).getUserObject() instanceof java.lang.String){
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select the class under which you would like to add the Status Poller"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
				return;
			}
			selectedNode=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();
		}
		if(selectedNode.getUserObject() instanceof java.lang.String) {
			return;
		}
		list=selectedNode.getPath();
		ManagedObjectModel mom=(ManagedObjectModel)selectedNode.getUserObject();
		if(mom.getProjType()!=-1) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a class and then add"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		TransverseContainer tCon=new TransverseContainer();
		if(findNodeinTree(selectedNode,"Status Poller")) {
			// TODO: Error Message when node with User Tester Defined
			DefaultMutableTreeNode tempNode=getNamedChild(selectedNode,"Status Poller");
			if(tempNode!=null) {
				ManagedObjectModel tempMom=(ManagedObjectModel)tempNode.getUserObject();
				if(tempMom.getFileName()!=null && tempMom.getFileName().toString().trim().length()!=0)  {
					if(JOptionPane.showConfirmDialog(null,resourceBundle.getString("Warning : Class already has UserTester OverWrite ???"),resourceBundle.getString("Warning"),JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
						return;
					}
				}
			}
			list=tempNode.getPath();
		}
		// The following code should be customized for UserTester Wizard.
		tCon.addTransverseComponent("currentProjectPath",getNmsHome()+"projects"+fs+((DefaultMutableTreeNode)selectedNode.getParent()).toString()+File.separator);
		tCon.addTransverseComponent("currentProject","."+fs+"projects"+fs+((DefaultMutableTreeNode)selectedNode.getParent()).toString()+File.separator);
		tCon.addTransverseComponent("XMLMODEL",mom.getDocInfo());
		Usertp=new TransversePanel();
		Usertp.setImageLabel(imageLabel);
		Usertp.addTransverseContainer(tCon);
		userStat=new DiscStatus();
		userStat.setServiceType(1);
		userStat.defineUserTester();
		UserTester=new FileWriter();
		Usertp.addComponents("UserTester",userStat,"."+fs+"images"+fs+"statuspoller.jpg");
		Usertp.addComponents("UserFile",UserTester,"."+fs+"images"+fs+"statuspoller.jpg");
		Usertp.initialize();
		dialog=new JDialog(this,resourceBundle.getString("Editor For Status Poller"),true);
		dialog.getContentPane().add(Usertp,BorderLayout.CENTER);
		dialog.getContentPane().add(imageLabel,BorderLayout.WEST);
		dialog.setSize(new Dimension(800,550));
		dialog.setLocation(getScrLoc(dialog));
		dialog.setVisible(true);

		if(tCon.getTransverseComponent("USR_TST_CLSNAME")==null) {
			return;
		}
		String userTesterClassName=(String)tCon.getTransverseComponent("USR_TST_CLSNAME");
		String userTesterFileName=(String)tCon.getTransverseComponent("USR_TST_FILENAME");
		Document doc=mom.getDocInfo();
		if(findNodeinTree(selectedNode,"Status Poller")) {
			Element userTester=(Element)doc.getElementsByTagName("USER_TESTER").item(0);
			userTester.setAttribute("className",userTesterClassName);
			userTester.setAttribute("fileName",userTesterFileName);
			//update Tree for reflecting Status Poller
			DefaultMutableTreeNode childNode=getNamedChild(selectedNode,"Status Poller");
			list=childNode.getPath();
			ManagedObjectModel childMom=(ManagedObjectModel)childNode.getUserObject();
			childMom.setFileName(new File(userTesterFileName));
			try {
				writeXMLFile(doc,nodeToFilePath((DefaultMutableTreeNode)selectedNode.getParent(),"")+fs+selectedNode.toString()+".mow");
			}catch(FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
		}
		else {
			Element userTester=doc.createElement("USER_TESTER");
			userTester.setAttribute("className",userTesterClassName);
			userTester.setAttribute("fileName",userTesterFileName);
			ManagedObjectModel uModel=new ManagedObjectModel();
			uModel.setProjType(1001);
			uModel.setClassNodeName("Status Poller");
			uModel.setFileName(new File(userTesterFileName));
			DefaultMutableTreeNode uNode=new DefaultMutableTreeNode(uModel);
			list=uNode.getPath();
			doc.getDocumentElement().appendChild(userTester);
			selectedNode.add(uNode);
			try {
				writeXMLFile(doc,nodeToFilePath((DefaultMutableTreeNode)selectedNode.getParent(),"")+fs+selectedNode.toString()+".mow");
			}catch(FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
		}
		ProjectTree.updateUI();
		restoreFocus(list);
	}

	public void loadProject_Clicked() {

	}

	public void deleteProject_Clicked() {
		boolean delete=false;
		if(ProjectTree.getLastSelectedPathComponent()==null)
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Select a Project and then click DeleteProject"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		DefaultMutableTreeNode dmtn=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();
		if(dmtn.getUserObject() instanceof java.lang.String)
		{
			return;
		}
		if(((ManagedObjectModel)dmtn.getUserObject()).getProjType()==-1) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Select a Project and then click DeleteProject"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(((DefaultMutableTreeNode)dmtn.getParent()).isRoot())
		{
			String filepath=getNmsHome()+"projects"+fs+dmtn;
			File[] filelist=new File(getNmsHome()+"projects"+fs+dmtn).listFiles();
			if (filelist.length==0)
			{
				int val=JOptionPane.showConfirmDialog(null,resourceBundle.getString("Do you want to really delete this project"),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_OPTION);
				if(val==JOptionPane.OK_OPTION){
					((DefaultTreeModel)ProjectTree.getModel()).removeNodeFromParent(dmtn);
				}
			}
			else
			{
				DeleteFile del=new DeleteFile(this,resourceBundle.getString("Delete Project"));
				del.init();
				del.setFilePath(getNmsHome()+"projects"+fs+dmtn);
				del.populateTable(del.getFilePath());
				del.setLocation(getScrLoc(del));
				del.setVisible(true);
				delete=del.isDelete();
				if(del.isDelete()) {
					removeMOEntry(filepath);
					deleteAllFiles(filepath);
					((DefaultTreeModel)ProjectTree.getModel()).removeNodeFromParent(dmtn);
				}
			}
			if((delete) || (filelist.length==0))
			{
				File mainDir=new File(filepath);
				mainDir.delete();
				File projfile=new File(getNmsHome()+"projects"+fs+dmtn+".proj");
				projfile.delete();
			}

		}
	}
	public void removeMOEntry(String filepath)
	{
		File f=new File(filepath);
		File mofile=new File("."+fs+"projects"+fs+"MOObjects.xml");
		if(!mofile.exists()){
			return;
		}
		File[] fullpath=f.listFiles();
		for(int i=0;i<fullpath.length;i++){
			if(fullpath[i].toString().endsWith(".mow")){
				try{
					DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
					DocumentBuilder db=dbf.newDocumentBuilder();
					Document doc=db.parse(fullpath[i].toString());
					Element node=(Element)doc.getElementsByTagName("CLASS").item(0);
					String delStr=node.getAttribute("package")+"."+node.getAttribute("name");
					doc=db.parse(mofile);
					Element delnode=findWhichNode(doc,delStr);
					if(delnode==null){
						return;
					}
					doc.getDocumentElement().removeChild(delnode);
					writeXMLFile(doc,mofile.toString());
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	public Element findWhichNode(Document doc,String delStr)
	{
		NodeList tags=doc.getElementsByTagName("CLASS");
		for(int i=0;i<tags.getLength();i++){
			Element node=(Element)tags.item(i);
			if(node.getAttribute("name").equals(delStr.trim())){
				return node;
			}
		}
		return null;
	}


	void popupModify_Clicked(ActionEvent ae) {
		int returnVal=-1;
		DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();
		ManagedObjectModel tempMom=(ManagedObjectModel)selectedNode.getUserObject();
		switch(tempMom.getProjType()) {
			case 1002:
				File openFile=tempMom.getFileName();
				editor.jmax.closeFileWithOutPrompting(openFile);
				callDiscoveryFilter((DefaultMutableTreeNode)selectedNode.getParent());
				break;
			case -1:
				if(editor.jmax.getEditor().contentModified()) {
					returnVal=JOptionPane.showConfirmDialog(null,resourceBundle.getString("Source has changed. Do you want to save it ??"),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_CANCEL_OPTION);
					switch(returnVal) {
						case JOptionPane.YES_OPTION:
							// Save File
							editor.saveSourceFile(tempMom.getFileName());
							loadClassFromModel(false);
							break;
						case JOptionPane.NO_OPTION:
							// Do not save File
							loadClassFromModel(false);
							break;
					}
					return;
				}
				break;

		}
		loadClassFromModel(false);
	}

	void loadClassFromModel(boolean saveFile) {
		DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();
		Object[] list=selectedNode.getPath();
		if(selectedNode==null) {
			return;
		}
		if(selectedNode.getUserObject() instanceof java.lang.String) {
			return;
		}
		ManagedObjectModel mom=(ManagedObjectModel)selectedNode.getUserObject();
		if(mom==null) {
			return;
		}

		Document doc=(Document)mom.getDocInfo();
		if(doc==null) {
			return;
		}

		tp=new TransversePanel();
		tp.setImageLabel(imageLabel);
		mopropch=new MOPropChooser();
		ads=new AddDeviceScr();
		aos=new AddObjProp();
		acp=new addCustProps();
		wizard=new wizardResult();
		sscr=new SourceScr(false);
		sscr.setLoadStatus(true);
		//This method is added to provided loadstatus to SourceScr
		mopropch.text_PackageName.setText(((ManagedObjectModel)(((DefaultMutableTreeNode)selectedNode.getParent()).getUserObject())).getPackageName());
		mopropch.text_PackageName.setEnabled(false);
		tp.addComponents("Screen1",mopropch,"."+fs+"images"+fs+"mopropchooser.jpg");
		tp.addComponents("Screen2",ads,"."+fs+"images"+fs+"adddevice.jpg");
		tp.addComponents("Screen3",aos,"."+fs+"images"+fs+"addobject.jpg");
		tp.addComponents("Screen4",acp,"."+fs+"images"+fs+"addcustomprop.jpg");
		tp.addComponents("Screen5",wizard,"."+fs+"images"+fs+"report.jpg");
		tp.addComponents("Screen6",sscr,"."+fs+"images"+fs+"compiling.jpg");
		tp.initialize();
		TransverseContainer tempCon=mopropch.getTransverseContainer();
		tempCon.addTransverseComponent("currentProjectPath",getNmsHome()+"projects"+fs+selectedNode.getParent().toString()+File.separator);
		tempCon.addTransverseComponent("currentProject","."+fs+"projects"+fs+selectedNode.getParent().toString()+File.separator);
		mopropch.getTransverseContainer().addTransverseComponent("XMLMODEL",doc);
		mopropch.loadScreenData();
		ads.loadScreenData();
		aos.loadScreenData();
		acp.loadScreenData();
		sscr.loadScreenData();

		mopropch.disableClassNameField();

		newClassDialog=new JDialog(this,resourceBundle.getString("ManagedObject Designer Tool"),true);
		newClassDialog.getContentPane().add(tp,BorderLayout.CENTER);
		newClassDialog.getContentPane().add(imageLabel,BorderLayout.WEST);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		//newClassDialog.setSize((int)screenSize.getWidth()*3/4,(int)screenSize.getHeight()*3/4-25);
		newClassDialog.setSize(new Dimension(800,550));
		newClassDialog.setLocation(getScrLoc(newClassDialog));
		newClassDialog.setVisible(true);


		if(tp.isCancelled()) {
			return ;
		}
		if(!tp.isFinished()) {
			return;
		}
		if(!mom.isClassLoaded()) {
			mom.setClassLoaded(true);
		}
		//mom.setDocInfo((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL"));
		updateUserTester(selectedNode,((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL")));
		// find and add DiscoveryFilter.
		updateDiscoveryFilter(selectedNode,((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL")));
		ProjectTree.updateUI();
		restoreFocus(list);
		Document adoc=(Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL");
		try {
			// Find the className from MOPropChooser.
			// Create a directory under the projects/<Project Name>/<className>
			// Write a <className.conf> under the projects/<Project Name>/
			DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode)selectedNode.getParent();
			String filePath=nodeToFilePath(parentNode,"")+fs+mopropch.text_ClsName.getText().trim()+".mow";
			writeXMLFile(((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL")).getDocumentElement(),filePath);
			generateClassSource((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL"),parentNode,false);

		}catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

	void updateUserTester(DefaultMutableTreeNode selectedNode,Document doc) {
		// if doc contains userTester and Tree Contains UserTester update.
		// if doc contains userTester and Tree !contains UserTester create and add.
		// if doc !contains delete the UserTester Node,(from tree and disk)

		Element userTester=null;
		DefaultMutableTreeNode dmtn=null;
		TreeModel tm=ProjectTree.getModel();
		boolean foundInTree=false;
		Element rootNode=(Element)doc.getDocumentElement();
		if((userTester=(Element)doc.getElementsByTagName("USER_TESTER").item(0))==null) {
			int i=0;
			while(i<selectedNode.getChildCount()) {
				dmtn=(DefaultMutableTreeNode)tm.getChild(selectedNode,i);
				if(dmtn.toString().trim().equals("Status Poller")) {
					//dmtn.removeFromParent();
					ManagedObjectModel delMom=(ManagedObjectModel)dmtn.getUserObject();
					delMom.setFileName(new File("   "));
					delMom.setClassNodeName("   ");
				}
				i++;
			}
		}
		else {
			int i=0;
			ManagedObjectModel mom=new ManagedObjectModel();
			i=0;
			while(i<selectedNode.getChildCount()) {
				dmtn=(DefaultMutableTreeNode)tm.getChild(selectedNode,i);
				if(dmtn.toString().trim().equals("Status Poller")) {
					mom=(ManagedObjectModel)dmtn.getUserObject();
					foundInTree=true;
					break;
				}
				i++;
			}
			if(foundInTree) {
				mom.setFileName(new File(userTester.getAttribute("fileName")));
			}
			else {
				ManagedObjectModel ustmom=new ManagedObjectModel();
				ustmom.setProjType(1001);
				ustmom.setClassNodeName("Status Poller");
				ustmom.setFileName(new File(userTester.getAttribute("fileName")));
				DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(ustmom);
				selectedNode.add(newNode);
			}
		}
	}

	void updateDiscoveryFilter(DefaultMutableTreeNode selectedNode,Document doc) {
		//if doc contains discFilter then there may be a change so update.
		// if doc !contains delete the discFilter
		// if doc contains userTester then there may be a change so update.
		// if doc !contains delete the UserTester Node,(from tree and disk)
		Element discFilter=null;
		DefaultMutableTreeNode dmtn=null;
		boolean foundInTree=false;
		TreeModel tm=ProjectTree.getModel();
		if((discFilter=(Element)doc.getElementsByTagName("DISC_FILTER").item(0))==null) {
			int i=0;
			while(i<selectedNode.getChildCount()) {
				dmtn=(DefaultMutableTreeNode)tm.getChild(selectedNode,i);
				if(dmtn.toString().trim().equals("Discovery Filter")) {
					//dmtn.removeFromParent();
					ManagedObjectModel delMom=(ManagedObjectModel)dmtn.getUserObject();
					delMom.setFileName(new File("  "));
				}
				i++;
			}// end of while
		}
		else {
			int i=0;
			ManagedObjectModel mom=null;
			while(i<selectedNode.getChildCount()) {
				dmtn=(DefaultMutableTreeNode)tm.getChild(selectedNode,i);
				if(dmtn.toString().trim().equals("Discovery Filter")) {
					mom=(ManagedObjectModel)dmtn.getUserObject();
					foundInTree=true;
					break;
				}
				i++;
			}
			if(foundInTree) {
				if(discFilter.getAttribute("fileName")==null) {
					mom.setFileName(new File("   "));
				}
				else {
					mom.setFileName(new File(discFilter.getAttribute("fileName")));
				}
			}
			else {
				ManagedObjectModel ustmom=new ManagedObjectModel();
				ustmom.setProjType(1002);
				ustmom.setClassNodeName("Discovery Filter");
				ustmom.setFileName(new File(discFilter.getAttribute("fileName")));
				DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(ustmom);
				selectedNode.add(newNode);
			}
		}
	}

	void deleteDiscStatus(DefaultMutableTreeNode selectedNode,ManagedObjectModel selectedMom,String discStatus) {
		DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode)selectedNode.getParent();
		String configFileName=System.getProperty("user.dir")+fs+"projects"+fs+parentNode.getParent().toString()+fs+parentNode.toString()+".mow";
		ManagedObjectModel moclassModel=(ManagedObjectModel)parentNode.getUserObject();
		Document mowDoc=moclassModel.getDocInfo();
		Element discNode=(Element)mowDoc.getElementsByTagName(discStatus).item(0);
		if(discNode.hasAttribute("className")) {
			discNode.removeAttribute("className");
		}
		if(discNode.hasAttribute("fileName")) {
			File removeFile=new File(discNode.getAttribute("fileName"));
			removeFile.delete();
			discNode.removeAttribute("fileName");
		}
		moclassModel.setDocInfo(mowDoc);
		selectedMom.setFileName(null);
		selectedMom.setClassNodeName(null);
		try {
			writeXMLFile(mowDoc,configFileName);
		}
		catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		handle_NewSelectedNode(null);
	}

	void popupDelete_Clicked(ActionEvent ae) {

			DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();
			ManagedObjectModel tempMom=(ManagedObjectModel)selectedNode.getUserObject();
			switch(tempMom.getProjType()) {
				case 1002:
					deleteDiscStatus(selectedNode,tempMom,"DISC_FILTER");
					break;
				case 1001:
					deleteDiscStatus(selectedNode,tempMom,"USER_TESTER");
					break;
				case -1:
					int retvalue=JOptionPane.showConfirmDialog(this,resourceBundle.getString("Do you want to delete this class ???"),resourceBundle.getString("Message"),JOptionPane.YES_NO_OPTION);
					if(retvalue==JOptionPane.YES_OPTION)
					{
						String filename=System.getProperty("user.dir")+fs+"projects"+fs+selectedNode.getParent()+fs+selectedNode.toString();
						deleteAllFiles(filename);
						((DefaultTreeModel)ProjectTree.getModel()).removeNodeFromParent(selectedNode);
					}
					break;
			}

	}
	public void deleteAllFiles(String filename)
	{
		File f=new File(filename);
		File[] f1=f.listFiles();
		if(f1!=null){
			for(int i=0;i<f1.length;i++)
			{
				if(f1[i].isFile()){
					f1[i].delete();
				}
				else{
					File filepath=new File(f1[i].getAbsolutePath());
					if(f1[i]!=null){
						deleteAllFiles(f1[i].getAbsolutePath());
					}
					filepath.delete();
					//break;
				}
			}
		}
		if(f.exists()){
			f.delete();
		}
		if((f=new File(filename+".mow")).exists()){
			f.delete();
		}

	}
	void popupSave_Clicked(ActionEvent ae) {
	}

	public void new_Clicked() {
		// New should be enabled only when u select a Project and then Click New Menu.
		if(ProjectTree.getLastSelectedPathComponent()==null) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Select a Project and then click New"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		DefaultMutableTreeNode dmtn=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();
		if(dmtn.getUserObject() instanceof java.lang.String) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Select a Project and then click New"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(((ManagedObjectModel)dmtn.getUserObject()).getProjType()!=1) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Select a Project and then click New"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		defineNewClass(dmtn);
	}

	void defineNewClass(DefaultMutableTreeNode selectedProjectNode) {
		tp=new TransversePanel();
		tp.setImageLabel(imageLabel);
		mopropch=new MOPropChooser();
		ads=new AddDeviceScr();
		aos=new AddObjProp();
		acp=new addCustProps();
		wizard=new wizardResult();
		sscr=new SourceScr();
		ManagedObjectModel parentMom=(ManagedObjectModel)selectedProjectNode.getUserObject();
		mopropch.text_PackageName.setText(parentMom.getPackageName());
		mopropch.text_PackageName.setEnabled(false);
		mopropch.setProjectName(selectedProjectNode.toString());
		tp.addComponents("Screen1",mopropch,"."+fs+"images"+fs+"mopropchooser.jpg");
		tp.addComponents("Screen2",ads,"."+fs+"images"+fs+"adddevice.jpg");
		tp.addComponents("Screen3",aos,"."+fs+"images"+fs+"addobject.jpg");
		tp.addComponents("Screen4",acp,"."+fs+"images"+fs+"addcustomprop.jpg");
		tp.addComponents("Screen5",wizard,"."+fs+"images"+fs+"report.jpg");
		tp.addComponents("Screen6",sscr,"."+fs+"images"+fs+"compiling.jpg");
		tp.initialize();
		TransverseContainer tempCon=mopropch.getTransverseContainer();
		tempCon.addTransverseComponent("currentProjectPath",getNmsHome()+"projects"+fs+selectedProjectNode.toString()+File.separator);
		tempCon.addTransverseComponent("currentProject","."+fs+"projects"+fs+selectedProjectNode.toString()+File.separator);

		newClassDialog=new JDialog(this,resourceBundle.getString("ManagedObject Designer Tool"),true);
		newClassDialog.getContentPane().add(tp,BorderLayout.CENTER);
		newClassDialog.getContentPane().add(imageLabel,BorderLayout.WEST);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		newClassDialog.setSize(new Dimension(800,550));
		newClassDialog.setLocation(getScrLoc(newClassDialog));
		newClassDialog.setVisible(true);
		// The following if's are written to find out whether the user has finished
		// generation process or has stopped
		if(tp.isCancelled()) {
			return;
		}
		if(!tp.isFinished()) {
			return;
		}
		// Add a node under the selected Project.
		// Safety Check is done here.
		if(mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL")==null) {
			return;
		}

		//selectedProjectNode.add(new DefaultMutableTreeNode(mopropch.text_ClsName.getText()));
		ManagedObjectModel mom=new ManagedObjectModel(-1,mopropch.text_ClsName.getText());
		mom.setClassLoaded(true);
		mom.setDocInfo((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL"));
		mom.setFileName(new File(getNmsHome()+fs+"projects"+fs+selectedProjectNode+fs+mopropch.text_ClsName.getText()+fs+mopropch.text_ClsName.getText()+".java"));
		DefaultMutableTreeNode child=new DefaultMutableTreeNode(mom);
		selectedProjectNode.add(child);
		Object[] list=child.getPath();

		//child.add(new DefaultMutableTreeNode(mom1));
		// find and add UserTester
		addUserTester(child,((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL")));
		// find and add DiscoveryFilter.
		addDiscoveryFilter(child,((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL")));
		addRelationalJava(child,((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL")));
		addDeploymentNode(child,((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL")));
		//child.add(new DefaultMutableTreeNode(mom1));
		ProjectTree.updateUI();
		try {
			// Find the className from MOPropChooser.
			// Create a directory under the projects/<Project Name>/<className>
			// Write a <className.conf> under the projects/<Project Name>/
			File f=new File(nodeToFilePath(selectedProjectNode,"")+fs+mopropch.text_ClsName.getText().trim()+File.separator);
			f.mkdir();
			String filePath=nodeToFilePath(selectedProjectNode,"")+fs+mopropch.text_ClsName.getText().trim()+".mow";
			writeXMLFile(((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL")).getDocumentElement(),filePath);
			generateClassSource((Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL"),selectedProjectNode,true);
			ProjectTree.expandPath(new TreePath(list));
			restoreFocus(list);

		}catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

	void addDeploymentNode(DefaultMutableTreeNode newClassNode,Document doc) {
		if(newClassNode==null) {
			return;
		}
		Element deploy=doc.createElement("DEPLOYMENT");
		doc.getDocumentElement().appendChild(deploy);
		ManagedObjectModel mom=new ManagedObjectModel();
		mom.setProjType(1004);
		DefaultMutableTreeNode depNode=new DefaultMutableTreeNode(mom);
		newClassNode.add(depNode);
	}

	void addRelationalJava(DefaultMutableTreeNode newClassNode,Document doc) {
		if(newClassNode==null) {
			return;
		}
		Element relJava=doc.createElement("RELATIONAL_JAVA");
		doc.getDocumentElement().appendChild(relJava);
		ManagedObjectModel mom=new ManagedObjectModel();
		mom.setProjType(1003);
		DefaultMutableTreeNode relNode=new DefaultMutableTreeNode(mom);
		newClassNode.add(relNode);
	}

	void addUserTester(DefaultMutableTreeNode newClassNode, Document doc) {
		if(doc==null) {
			return;
		}
		Element userTester=(Element)doc.getElementsByTagName("USER_TESTER").item(0);
		if(userTester==null) {
			userTester=doc.createElement("USER_TESTER");
			ManagedObjectModel eMom=new ManagedObjectModel();
			eMom.setProjType(1001);
			DefaultMutableTreeNode emptyNode=new DefaultMutableTreeNode(eMom);
			newClassNode.add(emptyNode);
			doc.getDocumentElement().appendChild(userTester);
			return;
		}
		ManagedObjectModel mom=new ManagedObjectModel();
		mom.setProjType(1001);
		mom.setFileName(new File(userTester.getAttribute("fileName")));
		mom.setClassNodeName(userTester.getAttribute("className"));
		DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(mom);
		doc.getDocumentElement().appendChild(userTester);
		newClassNode.add(newNode);
	}

	void addDiscoveryFilter(DefaultMutableTreeNode newClassNode, Document doc) {
		Element discFilter=(Element)doc.getElementsByTagName("DISC_FILTER").item(0);
		if(discFilter==null) {
			discFilter=doc.createElement("DISC_FILTER");
			ManagedObjectModel eMom=new ManagedObjectModel();
			eMom.setProjType(1002);
			DefaultMutableTreeNode emptyNode=new DefaultMutableTreeNode(eMom);
			newClassNode.add(emptyNode);
			doc.getDocumentElement().appendChild(discFilter);
			return;
		}
		ManagedObjectModel mom=new ManagedObjectModel();
		mom.setProjType(1002);
		mom.setFileName(new File(discFilter.getAttribute("fileName")));
		mom.setClassNodeName(discFilter.getAttribute("className"));
		DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(mom);
		newClassNode.add(newNode);
	}


	void generateClassSource(Document doc,DefaultMutableTreeNode selectedNode,boolean firstTime) {
		if(doc==null) {
			return;
		}
		String sourcePath=nodeToFilePath(selectedNode,"")+fs+mopropch.text_ClsName.getText().trim()+fs+mopropch.text_ClsName.getText().trim();
		if(editor.jmax.getEditor()!=null)
		{
			editor.jmax.closeFileWithOutPrompting(new File(sourcePath+".java"));
		}
		editor.OpenSourceFile(sourcePath+".java");
	}

	public void open_Clicked() {
	}

	public void exit_Clicked() {
		int returnVal=JOptionPane.showConfirmDialog(null,resourceBundle.getString("Do you want to terminate ManagedObject Wizard?"),resourceBundle.getString("Warning!!"),JOptionPane.YES_NO_OPTION);
		if(JOptionPane.YES_OPTION==returnVal)
			System.exit(0);
		else return;
	}

	public void cut_Clicked() {
		((JMTextPane)editor.jmax.getEditor()).getJavaTextPane().cut();

	}

	public void copy_Clicked() {
		((JMTextPane)editor.jmax.getEditor()).getJavaTextPane().copy();

	}

	public void paste_Clicked() {
		((JMTextPane)editor.jmax.getEditor()).getJavaTextPane().paste();

	}

	public void compile_Clicked() {
		DefaultMutableTreeNode selectednode=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();
		if(selectednode==null) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Select a ManagedObject class and then click Compile"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!selectednode.isRoot()){
			ManagedObjectModel mom=(ManagedObjectModel)selectednode.getUserObject();
			if(selectednode.getUserObject() instanceof java.lang.String || (mom.getProjType() > 0 && mom.getProjType() < 1000) ){
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select the .java file which you would like to Compile"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
				return;
			}

			mom=(ManagedObjectModel)selectednode.getUserObject();
			File f=mom.getFileName();
			if(f==null||f.toString().equals("")){
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please define the class to compile it"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
				return;
			}
			String packagename=null;
			Document doc=null;
			String projpath=null;
			if(mopropch==null) {
				if(mom.getProjType()==-1) {
					doc=mom.getDocInfo();
					Element packnode=(Element)doc.getElementsByTagName("CLASS").item(0);
					packagename=packnode.getAttribute("package");
				}
				else{
					DefaultMutableTreeNode dmtn=(DefaultMutableTreeNode)selectednode.getParent();
					ManagedObjectModel mom1=(ManagedObjectModel)dmtn.getUserObject();
					packagename=mom1.getPackageName();
				}
			}
			else {
				doc=(Document)mopropch.getTransverseContainer().getTransverseComponent("XMLMODEL");
				Element packnode=(Element)doc.getElementsByTagName("CLASS").item(0);
				packagename=packnode.getAttribute("package");
			}
			File f1=new File(System.getProperty("user.dir")+fs+"projects"+fs+"coptions.xml");
			if(!f1.exists()){
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter the compile options"),resourceBundle.getString("Message"),JOptionPane.OK_OPTION);
				COptions co=new COptions(this,null);
				co.init();
				co.setLocation(getScrLoc(co));
				co.setVisible(true);
				return;
			}
			if(compile==null){
				compile=new CompileScr();
				compile.init();
			}
			try {
				DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
				DocumentBuilder db=dbf.newDocumentBuilder();
				doc=db.parse(f1);
				Element options=(Element)doc.getElementsByTagName("Options").item(0);

				compile.clearText();
				compile.setLocation(getScrLoc(compile));
				compile.setVisible(true);
				String classpath=options.getAttribute("ClassPath");
				File f2=new File("."+fs+"projects"+fs+"MOObjects.xml");
				if(f2!=null && f2.exists()){
					doc=db.parse(f2);
					NodeList tags=doc.getElementsByTagName("CLASS");
					for(int i=0;i<tags.getLength();i++){
						Element node=(Element)tags.item(i);
						if(node.getAttribute("name").equals(packagename+"."+getCurProj(mom,selectednode))){
							classpath=node.getAttribute("classpath")+classpath;
							break;
						}
					}
				}
				if(mom.getProjType()==-1){
					projpath="."+fs+"projects"+fs+((DefaultMutableTreeNode)selectednode.getParent()).toString()+fs+"classes";
				}
				else{
					projpath="."+fs+"projects"+fs+((DefaultMutableTreeNode)selectednode.getParent().getParent()).toString()+fs+"classes";
				}
				compile.compileClicked(f.toString(),options.getAttribute("JDKPath"),classpath,projpath);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getCurProj(ManagedObjectModel mom,DefaultMutableTreeNode node)
	{
		String projpath="";
		if(mom.getProjType()==-1){
			projpath=node.toString();
		}
		else{
			projpath=node.getParent().toString();
		}
		return projpath;
	}

	public void regenerate_Clicked() {

	}

	public void compile_And_Run_Clicked() {

	}

	public void run_Clicked() {

	}

	public void options_Clicked() {
		COptions fw=new COptions(this,resourceBundle.getString("Compile Options"));
		fw.init();
		if(fw.readXML()==true){
			fw.initialize();
		}
		fw.setLocation(getScrLoc(fw));
		fw.setVisible(true);
	}

	public void settings_Clicked() {
		if(ProjectTree.getLastSelectedPathComponent()==null) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a project"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		DefaultMutableTreeNode projNode=(DefaultMutableTreeNode)ProjectTree.getLastSelectedPathComponent();

		if(projNode.getUserObject() instanceof java.lang.String) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a project"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		ManagedObjectModel mom=(ManagedObjectModel)projNode.getUserObject();
		if(mom.getProjType()==-1) {
			projNode=(DefaultMutableTreeNode)projNode.getParent();
		}
		if( mom.getProjType()>1000) {
			projNode=(DefaultMutableTreeNode)projNode.getParent().getParent();
		}
		ProjProperty pp=new ProjProperty();
		final JDialog dlg=new JDialog();
		dlg.setTitle(resourceBundle.getString("Project Properties - ")+projNode);
		pp.showProjectProperty(projNode.toString());
		dlg.getContentPane().add(pp,BorderLayout.CENTER);
		JButton closeButton=new JButton(resourceBundle.getString("Close"));
		JPanel  closeButtonPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		closeButtonPanel.add(closeButton);
		dlg.getContentPane().add(closeButtonPanel,BorderLayout.SOUTH);
		closeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
				dlg.dispose();
				}
				});
		dlg.setModal(true);
		dlg.setSize(600,400);
		dlg.setLocation(getScrLoc(dlg));
		dlg.setVisible(true);
	}

	public void contents_Clicked() {
		if(hcr==null) {
			hcr=new HelpConfReader();
		}
		try {
			File f=new File(".");
			String helpURL=hcr.getHelpUrl("MO_WIZARD",f.getAbsolutePath());
			bc=new BrowserControl();
			bc.displayURL(helpURL);
		}
		catch(Exception e) {
			System.err.println(resourceBundle.getString("Error while trying to retrieve url from config file"));
			e.printStackTrace();
		}
	}


	public void about_Us_Clicked() {
		try{
			AboutFrame af=new AboutFrame();
			af.init();
			af.setVisible(true);
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public void popupModifyClicked() {
		// Show TransversePanel with Class Name in ReadOnly Mode
	}

	public void popupDeleteClicked() {
		// Delete this class and it corresponding class.config file.
	}

	public void restoreFocus(Object[] list)
	{
		ProjectTree.setSelectionPath(new TreePath(list));
	}

	public static void main(String[] args) {
	
		String locale = System.getProperty("RESOURCE_LOCALE");
		String propertiesFile=System.getProperty("RESOURCE_PROPERTIES");
		String resourceDir=System.getProperty("NMS_RESOURCE_DIRECTORY");
	
		if(locale==null||locale.trim().equals("")){
			locale=Locale.getDefault().toString();
			Utility.setParameter("RESOURCE_LOCALE",locale);
		}

		if(propertiesFile==null||propertiesFile.trim().equals("")){
			propertiesFile="MOWizard";
			Utility.setParameter("RESOURCE_PROPERTIES",propertiesFile);

		}
		if(resourceDir==null||resourceDir.trim().equals("")){
			resourceDir="./html";	
		}
		Utility.setParameter("NMS_RESOURCE_DIRECTORY",resourceDir);
		resourceBundle = Utility.getBundle(propertiesFile,locale,null);
		ToolsUtil.setBundle(resourceBundle);
	



		MainScreen msc=new MainScreen();

		String fs=File.separator;
		msc.setIconImage(new ImageIcon(System.getProperty("user.dir")+fs+"icons"+fs+"mogen.jpg").getImage());
		msc.init();
		msc.loadProjectInfo();
		//msc.initializeForMO();
		msc.ProjectTree.setCellRenderer(new ProjectTreeCellRenderer());
		msc.ProjectTree.setShowsRootHandles(true);
		msc.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-20,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-20);
		try {
			//SwingUtilities.updateComponentTreeUI(msc);
		}catch(Exception e) {
			e.printStackTrace();
		}
		msc.setTitle(resourceBundle.getString("Designer Toolkit - ManagedObject Wizard"));
		msc.setVisible(true);

		msc.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
				System.exit(0);
				}
				});
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
			if(evt.getActionCommand().equals("New Project"))
			{
				//Please add the action code for menu Here New Project
				newProject_Clicked();
			}
			if(evt.getActionCommand().equals("Delete Project"))
			{
				//Please add the action code for menu Here Delete Project
				deleteProject_Clicked();
			}
			if(evt.getActionCommand().equals("Save Project"))
			{
				//Please add the action code for menu Here Save Project
				saveProject_Clicked();
			}
			if(evt.getActionCommand().equals("ManagedObject"))
			{
				//Please add the action code for menu Here ManagedObject
				new_Clicked();
			}
			if(evt.getActionCommand().equals("User Tester"))
			{
				//Please add the action code for menu Here Status Poller
				callUserTester(null);
			}
			if(evt.getActionCommand().equals("Discovery Filter"))
			{
				//Please add the action code for menu Here Discovery Filter
				callDiscoveryFilter(null);
			}
			if(evt.getActionCommand().equals("RDBMS"))
			{
				//Please add the action code for menu Here RDBMS Interface
				generateRelationalJava(null);
			}
			if(evt.getActionCommand().equals("Save"))
			{
				//Please add the action code for menu Here Save
				saveClicked();
			}
			if(evt.getActionCommand().equals("Exit"))
			{
				//Please add the action code for menu Here Exit
				exit_Clicked();
			}
			if(evt.getActionCommand().equals("Undo"))
			{
				//Please add the action code for menu Here undo
			}
			if(evt.getActionCommand().equals("Redo"))
			{
				//Please add the action code for menu Here redo
			}
			if(evt.getActionCommand().equals("Cut"))
			{
				//Please add the action code for menu Here Cut
				cut_Clicked();
			}
			if(evt.getActionCommand().equals("Copy"))
			{
				//Please add the action code for menu Here Copy
				copy_Clicked();
			}
			if(evt.getActionCommand().equals("Paste"))
			{
				//Please add the action code for menu Here Paste
				paste_Clicked();
			}
			if(evt.getActionCommand().equals("Find"))
			{
				//Please add the action code for menu Here Find
				find_Clicked();
			}
			if(evt.getActionCommand().equals("Replace"))
			{
				//Please add the action code for menu Here Replace
				replace_Clicked();
			}
			if(evt.getActionCommand().equals("GoTo"))
			{
				//Please add the action code for menu Here goto
			}
			if(evt.getActionCommand().equals("Compile"))
			{
				//Please add the action code for menu Here Compile
				compile_Clicked();
			}
			if(evt.getActionCommand().equals("Regenerate"))
			{
				//Please add the action code for menu Here Regenerate
				regenerate_Clicked();
			}
			if(evt.getActionCommand().equals("Options"))
			{
				//Please add the action code for menu Here Options
				options_Clicked();
			}
			if(evt.getActionCommand().equals("ProjectProperties"))
			{
				//Please add the action code for menu Here Properties
				showProjectProperties();
				return;
			}
			if(evt.getActionCommand().equals("Contents"))
			{
				//Please add the action code for menu Here Contents
				contents_Clicked();
			}
			if(evt.getActionCommand().equals("About Us"))
			{
				//Please add the action code for menu Here About Us
				about_Us_Clicked();
			}
			{
				//Please add the action code for ToolItem Here New
			}
			if(evt.getActionCommand().equals("Save"))
			{
				//Please add the action code for ToolItem Here Save
			}
			if(evt.getActionCommand().equals("Cut"))
			{
				//Please add the action code for ToolItem Here Cut
			}
			if(evt.getActionCommand().equals("Copy"))
			{
				//Please add the action code for ToolItem Here Copy
			}
			if(evt.getActionCommand().equals("Paste"))
			{
				//Please add the action code for ToolItem Here Paste
			}
			if(evt.getActionCommand().equals("Compile"))
			{
				//Please add the action code for ToolItem Here Compile
			}
			if(evt.getActionCommand().equals("Contents"))
			{
				//Please add the action code for ToolItem Here Help
			}
			if(evt.getActionCommand().equals("Exit"))
			{
				//Please add the action code for ToolItem Here Exit
			}

		}
	}
	//<End_class_MenuToolBarAction>

}

class ProjectTreeCellRenderer extends DefaultTreeCellRenderer {
	String fs=File.separator;
	ProjectTreeCellRenderer() {
		super();
	}

	public Component getTreeCellRendererComponent(JTree prjTree, Object node, boolean isSelected,boolean isExpanded,boolean isLeaf,int row, boolean hasFocus) {
		JLabel label=(JLabel)super.getTreeCellRendererComponent(prjTree,node,isSelected,isExpanded,isLeaf,row,hasFocus);
		String fs=File.separator;
		try {
			DefaultMutableTreeNode currentNode=(DefaultMutableTreeNode)node;
			if(currentNode.getUserObject() instanceof java.lang.String) {
				//set Project Icon set the same Icon for both setClosedIcon & setOpenIcon
				label.setIcon(new ImageIcon(ImgConv.getImage(new File(System.getProperty("user.dir")+fs+"images"+fs+"project01.png").toURL())));
				label.setText(currentNode.toString());
				return label;
			}

			ManagedObjectModel mom=(ManagedObjectModel)currentNode.getUserObject();

			if(mom.getProjType()==-1) {
				// Set icon to represet class Icon
				label.setIcon(new ImageIcon(ImgConv.getImage(new File(System.getProperty("user.dir")+fs+"images"+fs+"class.png").toURL())));
				label.setText(mom.getClassNodeName()+" (ManagedObject)");
				label.setPreferredSize(new Dimension(label.getWidth()+200,label.getHeight()+20));

				return label;
			}
			else if(mom.getProjType()==1) {
				// Set icon to represent single MO Type Icon
				label.setIcon(new ImageIcon(ImgConv.getImage(new File(System.getProperty("user.dir")+fs+"images"+fs+"individualproject01.png").toURL())));
				label.setPreferredSize(new Dimension(label.getWidth()+200,label.getHeight()+20));

				return label;
			}
			else if(mom.getProjType()==1001) {
				// Set icon to represent Status Poller
				label.setIcon(new ImageIcon(ImgConv.getImage(new File(System.getProperty("user.dir")+fs+"images"+fs+"statuspoller.png").toURL())));
				label.setPreferredSize(new Dimension(label.getWidth()+200,label.getHeight()+20));
				label.setText("Status Poller");

				return label;
			}
			else if(mom.getProjType()==1002) {
				// Set icon to represent Discovery Filter
				label.setIcon(new ImageIcon(ImgConv.getImage(new File(System.getProperty("user.dir")+fs+"images"+fs+"discoveryfilter01.png").toURL())));
				label.setPreferredSize(new Dimension(label.getWidth()+200,label.getHeight()+20));
				label.setText("Discovery");
				return label;
			}
			else if(mom.getProjType()==1003) {
				// Set icon to represent Relational Object
				label.setText("Relational Java");

				label.setIcon(new ImageIcon(ImgConv.getImage(new File(System.getProperty("user.dir")+fs+"images"+fs+"relationaljava.png").toURL())));
				label.setPreferredSize(new Dimension(label.getWidth()+200,label.getHeight()+20));
				return label;

			}
			else if(mom.getProjType()==1004) {
				label.setText("Deployment");
				label.setIcon(new ImageIcon(ImgConv.getImage(new File(System.getProperty("user.dir")+fs+"images"+fs+"deployment.png").toURL())));
				label.setPreferredSize(new Dimension(label.getWidth()+200,label.getHeight()+20));
				return label;
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return label;
	}

}

