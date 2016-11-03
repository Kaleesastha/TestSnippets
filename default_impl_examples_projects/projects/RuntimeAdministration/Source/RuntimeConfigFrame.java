// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;



//$Id: RuntimeConfigFrame.java,v 1.5 2010/10/29 13:46:41 swaminathap Exp $


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import com.adventnet.nms.util.CommonBuilderUIInterface;
import java.lang.reflect.*; //Added by Balan
import java.net.URL;//Added by Balan
import com.adventnet.nms.util.NmsClientUtil;//Added by Balan
import com.adventnet.nms.util.XMLNode; //Added by Balan
import com.adventnet.nms.util.XMLDataReader; //Added by Balan

public class RuntimeConfigFrame extends JFrame  implements com.adventnet.nms.startclient.NmsFrame
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JSplitPane JSplitPane1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTree JTree1 = null;
	javax.swing.JPanel basePanel = null;
	javax.swing.JLabel JButton1 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	private JMenuBar menuBar=null;
	JMenu FileMenu= null;
	JMenuItem   ApplyMenuItem = null;
	JMenuItem   Classpath_settingsMenuItem = null;
	JMenuItem   CloseMenuItem = null;
	JMenu HelpMenu= null;
	JMenuItem   Help_ContentsMenuItem = null;
	JMenuItem   AboutMenuItem = null;
	MenuToolBarAction menuToolBarAction = new MenuToolBarAction();
	//<End_Variable_Declarations>


  	
	//SeedParser parse = new SeedParser("d:\march30\adventnet\webnms\conf\seed.file");

          //Commented by Balan on 4/7/03 for the Memory Leak issue reported by SPP Team
          // private static final Hashtable helpKeys =  new Hashtable(17);
          private static Hashtable helpKeys = null;// new Hashtable(17);
          MainScreen  mScreen = null;
          Object  objaddPanelClass               = null;
          //Comment Ends
    
	private static RuntimeConfigFrame firstInstance = null;
	private boolean disposeMe = false;
	private static CommonBuilderUIInterface uiInterface = null;
	SmartToolBar toolBar = null;
	String node =null;
    
      // Commented by Balan  on 15/03/03    
     /* PolicyConfigurationUI policyconfigui = null;
	MapFilterUI mapfilterui =null;
	DiscoveryFilterUI discoveryui =null;
	PollFilterUI pollfilterui=null;
	TrapFilterUI trapfilterui=null;
	MapIconUI  mapiconui=null;
	LoggingMainUI loggingmainui =null;
	AdventnetScreen mainScreen =null;
	ListIconUI listiconui = null;
	MainScreen seedPane =null;*/
        //Comment Ends
        
    	DefaultTreeModel model =null;
    	DefaultTreeCellRenderer rend =null;
	static GlassIconPanel gip = null;
    
     // Commented by Balan  on 15/03/03    
    /*boolean policyBoolean = false; 
	boolean pollBoolean = false;
	boolean discoveryBoolean = false;
	boolean mapFilterBoolean = false;
	boolean mapIconBoolean = false;
	boolean listIconBoolean = false;
	boolean loggingBoolean = false;
	boolean trapFilterBoolean = false;
	boolean discoveryConfigBoolean = false;*/
    //Comment Ends
    
	static JLabel statusLabel = null;   
	private String currentNode = null;
	private ApplyToServerInterface applyInterface = null;
	//Thread t =null;  
	private static ProgressBarPanel progressBarPanel = null;//new ProgressBarPanel();  
  
      //Added by Balan on 15/03/03
        boolean isUIModified              = false;
        Hashtable     hstPanelNames= null;
      //Add Ends


      //Reason for Changes
      //Modified by Balan on 21/06/03 for SPP's Requirements ie when exception arises with applyToserver method
      //it should not allow the user to select some other node as well as not to allow RTA to be closed.
    
     // Added by Balan on 21/06/03 for  SPP Memory Leak Issue
        TreePath   treePathSelected      = null;
        TreePath   treePathPrevSelected  = null;
        boolean    bisApplyToserverOk    = true;
    // Add Ends

    //Added by Balan on 15/07/03  SPP Memory Leak Issue
     private boolean isLoading = false;
    //Add Ends
  
    public void start()
  { 

  //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
 } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JSplitPane1= new javax.swing.JSplitPane();
        JScrollPane1= new javax.swing.JScrollPane();
        JTree1= new javax.swing.JTree();
        basePanel= new javax.swing.JPanel();
        JButton1= new javax.swing.JLabel();
        JPanel4= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
		initializeRuntimeVariables();
	}
 
     // Added by Balan on 15/03/03
    
     class NodeSelected implements TreeSelectionListener
     {
         //Commented by Balan on 4/7/03 for the Memory leak reported by SPP Team
         //Object  objaddPanelClass               = null;
         //Comment Ends
         
         //Added by Balan for SPP on 21/06/03 for the Memory leak reported by SPP Team
           boolean bPreviouslySelectedPanelType = false;    
         //Add Ends

         //Added by Balan on 08/07/03 for the Memory leak reported by SPP Team
         boolean bCleanResourceNeeded    = false;
         String  strCleanResources       = null;
         String  strPrevSelectedNodeId   = null;
         //Add Ends
         public void valueChanged(TreeSelectionEvent tevent)
         {
             //Added by Balan for the Memory leak reported by SPP Team 
             if(firstInstance == null) return;
             //Add Ends
             
             try
             {
                 //Added by Balan for the Memory leak reported by SPP Team
                  treePathPrevSelected = treePathSelected;
                  treePathSelected     = tevent.getPath();
                 //Add Ends

                  //Added by Balan on 16/07/03 as suggested by GS for the Memory leak reported by SPP Team
                    if(!bisApplyToserverOk)
                    {
                        bisApplyToserverOk=true;
                        isLoading=false;
                        return;
                    }

                 if(applyInterface != null && !isLoading )
                 {
		   if(!bPreviouslySelectedPanelType)
                     {
                         checkIfApplyNeeded();
				 try
				 {
					 if(!bisApplyToserverOk) throw new IllegalArgumentException("Validation failed");
		                         
                                     	 if(bCleanResourceNeeded)
                                         {
                                             applyInterface.dispose();
                                             applyInterface = null;
                                             hstPanelNames.remove(strPrevSelectedNodeId);
                                         }
				 }
				 catch(RuntimeException exp)
				 {
					SwingUtilities.invokeLater(new Runnable()
                                            {
                                                public void run(){JTree1.setSelectionPath(treePathPrevSelected); }
                                            });
                                        isLoading = true;
                                        return;
				 }                                                
                     }
		 }
		 isLoading = false;
		//Add Ends

                 String                  strPanelName         = null;
                 String                  strClassName         = null;
                 String                  strNodeId            = null; 
                 DefaultMutableTreeNode  defTempSelectedNode  =((DefaultMutableTreeNode)tevent.getPath().getLastPathComponent());
                 Object                  tempObject           = defTempSelectedNode.getUserObject();
                 if(! (tempObject instanceof Hashtable))
                 {
                     return;
                 }
                 Hashtable               hstTempAttrList      = (Hashtable)tempObject;
                 strPanelName                                 = hstTempAttrList.containsKey("PANEL-NAME")?""+hstTempAttrList.get("PANEL-NAME"):null;
                 strNodeId                                    = hstTempAttrList.containsKey("NODE-ID")?""+hstTempAttrList.get("NODE-ID"):null;
                 Class                   objTempClass         = null;
                 Object objSelectedPanelUIClass               = null;
                 boolean                 bClassAlreadyLoaded  = false;
                
                 
                 if(strPanelName == null||strNodeId==null)
                 return;
                 strPanelName    = strPanelName.trim();
                 strClassName                                 = strPanelName.substring(strPanelName.lastIndexOf(".")+1);
                 basePanel.removeAll();
                 node        =""+ hstTempAttrList.get("TREE-NAME");
                 currentNode = new String(node);
              
             if(toolBar != null)
             {
                 toolBar.update(node);
                 setStatusText(node);

                 //Commented by Balan on 15/07/03 as suggested by GS  for the Memory leak reported by SPP Team
                 /*if(applyInterface != null)
                 {
                     if(!bPreviouslySelectedPanelType)
                     {
                         checkIfApplyNeeded();
                         //Added by Balan on 08/07/03
                         if(bisApplyToserverOk && bCleanResourceNeeded)
                         {
                             applyInterface.dispose();
                             applyInterface = null;
                             hstPanelNames.remove(strPrevSelectedNodeId);
                         }
                         //Add Ends
                     }
                     }*/
                 // Comment Ends

                 bClassAlreadyLoaded  =  hstPanelNames.containsKey(strNodeId);
               
                 
                 if(!bClassAlreadyLoaded)
                 {
                    objTempClass = Class.forName(strPanelName);
                    if(objTempClass == null)
                     {
                       System.out.println(" "+objTempClass+" -- " +resourceBundle.getString("the specified class is not found"));
                       return;
                     }
                 }
                                 
                 if(strClassName.equals("AdventnetScreen"))
                 {
                     if(!bClassAlreadyLoaded)
                     {
                     objSelectedPanelUIClass = objTempClass.newInstance();
                     hstPanelNames.put(strNodeId,objSelectedPanelUIClass);
                     }
                     
                 }

                 else if(strClassName.equals("MainScreen"))
                 {
                   if(!bClassAlreadyLoaded)
                    {
                        //MainScreen commented for Memory Leak
                         mScreen  =(MainScreen) (objTempClass.getConstructor(new Class[]{String.class,
                                                                                   String.class,
                                                                                   boolean.class,
                                                                                   java.applet.Applet.class})).
                                           newInstance(new Object[]{"com.adventnet.nms.runtimeconfig.ListConfiguartionSession","com.adventnet.nms.util.CommonBuilderUIImpl",new Boolean(true), applet});
                     mScreen.setVisible(true);
                      String proto = getParameter("PROTOCOL");
                     
                      // This is done as part of Trim and pack to remove the unwanted entries from the protocol Screen -- Kamalg
                      if(proto != null)
                      {
                          
                          StringTokenizer stk = new StringTokenizer(proto.trim(),",");
                          Vector vec = new Vector ( stk.countTokens());
                          while(stk.hasMoreTokens())
                          {
                              String str1 = stk.nextToken().trim();
                              vec.addElement(str1);
                          }
                          ProtocolScreen.setStoreVec(vec);
                      }
                      
                     if(tempApp != null)
                     {
                         mScreen.getCommonInterface().init(tempApp);
                     }
                     objSelectedPanelUIClass  = mScreen;
                     hstPanelNames.put(strNodeId,objSelectedPanelUIClass);
                     }
                     
                 }

                 else 
                 {
                     Constructor  tempCons[]       = null;
                     boolean       bAppletConsFound = false;
                     if(!bClassAlreadyLoaded)
                     {
                         tempCons                      = objTempClass.getConstructors();
                         Constructor   consCheck       = null;
                         Class         clsParamTypes[] = null;
                         for(int nIndex = 0 ;nIndex <tempCons.length;nIndex++)
                         {
                             consCheck       = tempCons[nIndex];
                             clsParamTypes   = consCheck.getParameterTypes();
                             if(clsParamTypes.length==1  && clsParamTypes[0].equals(java.applet.Applet.class))
                             {
                                 bAppletConsFound = true;
                                 break;
                             }

                         }
                      tempCons = null;
                      if(bAppletConsFound) //Check for Applet constructor alone
                      {
                      objSelectedPanelUIClass   = (objTempClass.getConstructor(new Class[]{java.applet.Applet.class})).
                                                  newInstance(new Object[]{applet});
                      }
                      else // Default Constructors
                      {
                          objSelectedPanelUIClass  = objTempClass.newInstance(); 
                      }
                      hstPanelNames.put(strNodeId,objSelectedPanelUIClass);
                     }
                                        
                 }
                 // This is to avoid AdventnetScreen panel which is not of type ApplyToServerInterface
                 objaddPanelClass = hstPanelNames.get(strNodeId);
                 if(objaddPanelClass instanceof ApplyToServerInterface)
                 {
                 applyInterface =((ApplyToServerInterface)objaddPanelClass) ;
                 basePanel.add((JPanel)applyInterface,BorderLayout.CENTER);
                 bPreviouslySelectedPanelType = false;
                 }
                 else
                 {
                 if(objaddPanelClass instanceof JPanel)
                     {
                        basePanel.add((JPanel)objaddPanelClass,BorderLayout.CENTER);
                        bPreviouslySelectedPanelType = true;
                                               
                     }
                 else
                 {
                     bPreviouslySelectedPanelType =  true;
                     // objaddpanelclass is not an instance of JPanel
                 }
                 }
                 //Added by Balan on 08/07/03 for Memory leak issue reported by SPP
                 strCleanResources     = ""+hstTempAttrList.get("CLEAN-RESOURCE");
                 strPrevSelectedNodeId = strNodeId;
                 if(strCleanResources == null || !strCleanResources.equalsIgnoreCase("true"))
                      bCleanResourceNeeded = false;
                  else
                      bCleanResourceNeeded = true;
                 objSelectedPanelUIClass   = null;
                 //Add Ends
                 
                 //Added by Balan on 21/06/03 for SPP

                 //Commented by Balan 16/07/03 as suggested by GS  for the Memory leak reported by SPP Team
                 /* if(!bisApplyToserverOk)
                       {
                           bisApplyToserverOk = true;
                           showErrorStatus();
                           JTree1.setSelectionPath(treePathPrevSelected);
                          
                       }
                 */
                 //Comment Ends
                 
                 //Add Ends
                 basePanel.validate();
                 basePanel.invalidate();
                 basePanel.updateUI();
                               
             }
             else
             {
                 //tool bar is null;
             }
             System.runFinalization();
         }
         catch(Exception exec)
         {
             exec.printStackTrace();
             System.runFinalization();
         }
        }
     }// Add Ends

    // Removed by Balan
    // Here the portion of hard coded NodeSelected class removed
    // Removal Ends
    
    //JPanel toolBarPanel = new JPanel(new BorderLayout());
  	//toolBarPanel.add(toolBar);
   	//toolBar = new JToolBar();
    //toolBar.setFloatable(false);
	//getContentPane().add(toolBarPanel,BorderLayout.NORTH);
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JSplitPane1,BorderLayout.CENTER);
JSplitPane1.setLeftComponent(JScrollPane1);
JSplitPane1.setDividerLocation(197);
JScrollPane1.getViewport().add(JTree1);
JSplitPane1.setRightComponent(basePanel);
basePanel.setLayout(new BorderLayout(5,5));
basePanel.add(JButton1,BorderLayout.CENTER);
Top.add(JPanel4,BorderLayout.NORTH);
JPanel4.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JPanel2,BorderLayout.WEST);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel1.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JLabel1,cons);

  
//<End_setUpGUI_Container>
	//JPanel2.add(new PaintTextBox("true"));
	// setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	//JPanel3.add(statusLabel,cons);
	JPanel2.add(progressBarPanel,BorderLayout.CENTER);
  } 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            JButton1.setBackground(new Color(-16764109));
            JButton1.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JPanel1.setMinimumSize(new Dimension(855,18));
            JPanel1.setMaximumSize(new Dimension(2147483647,18));
            JPanel1.setPreferredSize(new Dimension(855,18));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JPanel2.setBorder(new javax.swing.border.BevelBorder(1));
            JPanel2.setPreferredSize(new Dimension(190,17));
            JPanel2.setMinimumSize(new Dimension(190,17));
            JPanel2.setMaximumSize(new Dimension(190,17));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JPanel3.setBorder(new javax.swing.border.BevelBorder(1));
            JPanel3.setMinimumSize(new Dimension(24,17));
            JPanel3.setMaximumSize(new Dimension(2147483647,17));
            JPanel3.setPreferredSize(new Dimension(24,17));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+620,JPanel3.getPreferredSize().height+11));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+10,JPanel2.getPreferredSize().height+12));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+15));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+23,JButton1.getPreferredSize().height+0));
		JSplitPane1.setPreferredSize(new Dimension(JSplitPane1.getPreferredSize().width+502,JSplitPane1.getPreferredSize().height+355));

  
          //<End_setUpProperties>
	//JLabel1.setPreferredSize(new Dimension(200,30));
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 
  public void stop()
  { 

  //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
  } 
  public void setUpMenus()
  { 

  //<Begin_setUpMenus> 
   menuBar = new JMenuBar();
   FileMenu= new JMenu(resourceBundle.getString("File"));
   FileMenu.setMnemonic('F');
   menuBar.add(FileMenu);
   ApplyMenuItem = new JMenuItem(resourceBundle.getString("Apply"));
   ApplyMenuItem.setMnemonic('A');
   ApplyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
   ApplyMenuItem.setActionCommand(resourceBundle.getString("Apply To Server"));
   ApplyMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(ApplyMenuItem);
   Classpath_settingsMenuItem = new JMenuItem(resourceBundle.getString("Classpath settings"));
   Classpath_settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
   Classpath_settingsMenuItem.setActionCommand(resourceBundle.getString("Classpath settings"));
   Classpath_settingsMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(Classpath_settingsMenuItem);
   CloseMenuItem = new JMenuItem(resourceBundle.getString("Close"));
   CloseMenuItem.setMnemonic('C');
   CloseMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,+ActionEvent.ALT_MASK));
   CloseMenuItem.setActionCommand(resourceBundle.getString("Close"));
   CloseMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(CloseMenuItem);
   HelpMenu= new JMenu(resourceBundle.getString("Help"));
   HelpMenu.setMnemonic('H');
   menuBar.add(HelpMenu);
   Help_ContentsMenuItem = new JMenuItem(resourceBundle.getString("Help Contents"));
   Help_ContentsMenuItem.setMnemonic('H');
   Help_ContentsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
   Help_ContentsMenuItem.setActionCommand(resourceBundle.getString("Help Contents"));
   Help_ContentsMenuItem.addActionListener(menuToolBarAction);
   HelpMenu.add(Help_ContentsMenuItem);
   AboutMenuItem = new JMenuItem(resourceBundle.getString("About"));
   AboutMenuItem.setMnemonic('B');
   AboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
   AboutMenuItem.setActionCommand(resourceBundle.getString("About"));
   AboutMenuItem.addActionListener(menuToolBarAction);
   HelpMenu.add(AboutMenuItem);
   this.setJMenuBar(menuBar);

  
   //<End_setUpMenus>
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
  public void init()
  { 
      if(!disposeMe)
      {
  //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+873,getPreferredSize().height+620); 
          setTitle(resourceBundle.getString("RuntimeConfigFrame"));
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
            JPanel2.setPreferredSize(new Dimension(190,17));
            JPanel2.setMinimumSize(new Dimension(190,17));
            JPanel2.setMaximumSize(new Dimension(190,17));
			JPanel1.setMinimumSize(new Dimension(855,20));
            JPanel1.setMaximumSize(new Dimension(2147483647,20));
	        JPanel1.setPreferredSize(new Dimension(855,20));
			JPanel3.setMinimumSize(new Dimension(24,17));
            JPanel3.setMaximumSize(new Dimension(2147483647,17));
            JPanel3.setPreferredSize(new Dimension(24,17));
	//JSplitPane1.setDividerLocation(200);
	JTree1.setModel(model);
	//For Selection
	//TreeNode tempNode[] = model.getPathToRoot(resourceBundle.getString("Categories"));
	//JTree1.setSelectionPath(new TreePath ( tempNode ));
	JButton1.setIcon(uiInterface.getImage("background.png","images/runtimeadmin"));
	JButton1.setHorizontalAlignment(SwingConstants.CENTER);
	statusLabel.setOpaque(true);
	statusLabel.setForeground(Color.white);
	setStatusColor(new Color(9,91,35));
	uiInterface.centerWindow(this);
	JPanel toolBarPanel = new JPanel(new BorderLayout());
	toolBar = new SmartToolBar(this);
	toolBarPanel.add(toolBar);
	gip = new GlassIconPanel();
	this.setGlassPane(gip);
	getContentPane().add(toolBarPanel,BorderLayout.NORTH);
	this.setIconImage(RuntimeConfigFrame.getCommonBuilderUIImpl().getFrameIcon());
	//ok.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	//cancel.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	//help.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	FileMenu.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	HelpMenu.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	ApplyMenuItem.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	CloseMenuItem.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	Help_ContentsMenuItem.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	AboutMenuItem.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	Classpath_settingsMenuItem.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	//JLabel1.setBackground(new Color(0,152,0));
	//JLabel3.setIcon(getCommonBuilderUIImpl().getScaledImage("led_green.png", "images/runtimeadmin" , 30 , 20 ,Image.SCALE_DEFAULT ) );
	statusLabel.setIcon(getCommonBuilderUIImpl().getImage("fault.png", "images/runtimeadmin"));
	//JLabel4.setIcon(getCommonBuilderUIImpl().getImage("misc.png", "images/runtimeadmin"));
	//JButton2.setIcon(getCommonBuilderUIImpl().getImage("misc.png", "images/runtimeadmin"));
	//JButton3.setIcon(getCommonBuilderUIImpl().getImage("misc.png", "images/runtimeadmin"));
	//JButton4.setIcon(getCommonBuilderUIImpl().getImage("misc.png", "images/runtimeadmin"));
        setTitle(resourceBundle.getString("Runtime Administration"));
      }
  } 


	public void handleApplyToServerEvent()
	{
            //Added by Balan on 21/06/03 for SPP  for the Memory leak reported by SPP Team
            //okButtonActionPerformed();
            if(!okButtonActionPerformed())
            {
                showErrorStatus();
            }
             ProgressBarPanel.bCanbeRepainted  = false;
             progressBarPanel.repaint();
            //Add Ends
	}

	public void handleCloseEvent()
	{
        //Added by Balan on 15/03/03  
                if(applyInterface != null)
                  {
                     isUIModified  = applyInterface.isModified();
                  }
       //  Add Ends
		/*if(policyconfigui != null)
		{
			policyBoolean = policyconfigui.isModified(); 
		}
		if(pollfilterui != null)
		{
			pollBoolean = pollfilterui.isModified();	
		}
		if(mapfilterui != null)
		{
			mapFilterBoolean = mapfilterui.isModified();	
		}
		if(discoveryui != null)
		{
			discoveryBoolean = discoveryui.isModified();	
		}
		if(seedPane !=   null)
		{
			discoveryConfigBoolean =seedPane.isModified();
		}
		if(trapfilterui != null)
		{
			trapFilterBoolean = trapfilterui.isModified();	
		}
		if(listiconui != null)
		{
			listIconBoolean = listiconui.isModified();
		}
		if(mapiconui != null)
		{
			mapIconBoolean = mapiconui.isModified();
		}
		if(loggingmainui != null)
		{
			loggingBoolean = loggingmainui.isModified();
		}
		if(policyBoolean || pollBoolean || mapFilterBoolean || mapIconBoolean || listIconBoolean || discoveryBoolean || loggingBoolean ||trapFilterBoolean||discoveryConfigBoolean)
		{*/
                //Comment Ends
                  // Added by Balan on 15/03/03
                  if(isUIModified)
                   {
                    //Add Ends
			JOptionPane jp = new JOptionPane();
			int option = jp.showConfirmDialog(this, resourceBundle.getString("Some of the configurations remain unapplied.Do you want to apply it to the Server?"),resourceBundle.getString("Confirmation"),jp.YES_NO_CANCEL_OPTION);

			if (option == jp.YES_OPTION)
			{
				//Added by Balan on 21/06/03 for SPP  for the Memory leak reported by SPP Team
				//okButtonActionPerformed();
                                 if(!okButtonActionPerformed())
                                 {
                                     showErrorStatus();
                                     return;
                                 }
                                //Add Ends
				progressBarPanel.shouldRun = false;
				//System.out.println("Setting JTree null");
                                 //Commented by Balan on 4/7/03 for the Memory Leak issue Reported by SPP Team
                                /*toolBar= null;
				firstInstance = null;   
				RuntimeConfigFrame.this.dispose();
				RunTimeConfigSession.getInstance().dispose();
                                */
			}
			else if( option == jp.NO_OPTION)
			{
				//Thread.dumpStack();
				applyInterface.refetchData();
				progressBarPanel.shouldRun = false;
				//System.out.println("Setting JTree null");
                                 //Commented by Balan on 4/7/03 for the Memory Leak issue Reported by SPP Team
				/*toolBar= null;
				firstInstance = null;   
				RuntimeConfigFrame.this.dispose();
				RunTimeConfigSession.getInstance().dispose();
                                */
                                //Comment Ends
			}
			else if (option == jp.CANCEL_OPTION)
			{
				return;
			}
                        //Added by Balan for SPP  for the Memory leak reported by SPP Team  
			else
			{
			return;
			}
                        //Add Ends
		}
		progressBarPanel.shouldRun = false;
		//System.out.println("Setting JTree null");

                //Commented by Balan on 4/7/03 for the Memory Leak issue Reported by SPP Team
		/*toolBar= null;
		 firstInstance = null;   
		RuntimeConfigFrame.this.dispose();
		RunTimeConfigSession.getInstance().dispose();
                */
                freeMemoryonClose();//Added
                //Comment Ends
	}

    //Added by Balan on 4/7/03 for the Memory Leak issue reported by SPP Team
    
    private void  freeMemoryonClose()
    {
        Object objTempObject = null;
     
            
        objaddPanelClass  = null;
        if(progressBarPanel != null)
	{
           progressBarPanel.dispose();
           progressBarPanel  = null;
        }
        if(helpKeys != null)
        {
        	helpKeys.clear();
        }
        for(Enumeration enumerate = hstPanelNames.keys();enumerate.hasMoreElements();)
        {
            objTempObject =  hstPanelNames.get(enumerate.nextElement());
            if( objTempObject != null&& objTempObject instanceof ApplyToServerInterface)
            {
                try
                {
                    ((ApplyToServerInterface)objTempObject).dispose();
                }
                catch(Exception exce)
                {
                    
                }
            } // The below else part is included inorder to remove the AdventnetScreen instance. Added by saravanasp as part of Operation THEME.
	    else if( ( objTempObject != null) && (objTempObject instanceof JPanel) )
            {
                try
                {
                    JPanel jp = (JPanel)objTempObject;
		    jp = null;
                }
                catch(Exception exce)
                {
                    
                }
            }
           
        }
        objTempObject = null;
        hstPanelNames.clear();
        helpKeys          = null;
        hstPanelNames     = null;
        basePanel.removeAll();
        basePanel         = null;
        uiInterface       = null;
        if(toolBar != null)
	  {
          toolBar.dispose();
          }
        statusLabel       = null;
        toolBar           = null;
        model             = null;
        rend              = null;
        gip               = null;
        mScreen           = null;
        applyInterface    = null;
        JTree1            = null;
	RunTimeConfigSession.getInstance().dispose();
        dispose();
        firstInstance = null;
        System.runFinalization();
    }
    //Add Ends

	public void handleHelpEvent()
	{
		if(currentNode != null)
		{
			String helpKey = getHelpKey(currentNode);
			if(helpKey != null)
			{
				uiInterface.showHelpURL(helpKey);
			}else
			{
				System.err.println(resourceBundle.getString("Help Key not found for the Node ")+currentNode);
			}
		}else
		{
			//Intially When No Nodes Selected.
			 uiInterface.showHelpURL("Runtime_Admin_Main_Help");
		}
	}

	public void handleAboutEvent()
	{
		uiInterface.showAboutWindow();
	}

	public void handleToolBarAction(String str)
	{
		if (str == null) return;
		if(str.trim().equals("Apply To Server"))
		{
			handleApplyToServerEvent();
		}
		else if(str.trim().equals("Close"))
		{
			handleCloseEvent();
		}		
		else if(str.trim().equals("Help Contents"))		
		{
			handleHelpEvent();
		}		
	}

	public static String getString(String str)
	{
		return resourceBundle.getString(str);
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






  

 


 



   /*public void dispose()
		{
			super.dispose();
		}
	*/


  public RuntimeConfigFrame()
  {
	  if(firstInstance != null)
	  {
		  disposeMe = true;
		  return;
	  }               
	  firstInstance = this;
	  progressBarPanel = new ProgressBarPanel();  
    //<Begin_RuntimeConfigFrame>
    pack();
  
    //<End_RuntimeConfigFrame>
	try
	{
		//Pending
		Class cl = Class.forName("com.adventnet.nms.util.CommonBuilderUIImpl");
		uiInterface = (CommonBuilderUIInterface) cl.newInstance();
	}
	catch (Exception e) 
	{
		System.out.println("Class Not found");
		e.printStackTrace();
	}
	RunTimeConfigSession.getInstance().sendReqForClassPath();
    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    addWindowListener(new close());
	
  }

  public RuntimeConfigFrame(java.applet.Applet applet)
  {
    //<Begin_RuntimeConfigFrame_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_RuntimeConfigFrame_java.applet.Applet>
  }

  public void setVisible(boolean bl)
  {
	  if(firstInstance != null)
	  {
		  if(disposeMe && bl)
		  {
			  super.setVisible(false);
			  dispose();
			  System.gc();
			  if(firstInstance.getState() == Frame.ICONIFIED)
			  {
				  firstInstance.setState(Frame.NORMAL);
			  }
			  firstInstance.toFront();
			  firstInstance.setVisible(true);
			  return;
		  }
	  }
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

	JApplet tempApp = null;
  public void init(javax.swing.JApplet applet)
  {
   //<Begin_init_javax.swing.JApplet>
   this.applet = applet;
   init();
  
   //<End_init_javax.swing.JApplet>
	tempApp = applet;
   uiInterface.init(applet);
  }

	public static CommonBuilderUIInterface getCommonBuilderUIImpl()
	{
		return   uiInterface;
	}

    //Changed by Balan void to boolean
    //public void okButtonActionPerformed()
    //{
    public boolean okButtonActionPerformed()
    {
            //Added by Balan on 21/06/03 for SPP  for the Memory leak reported by SPP Team
            boolean bRetValue = true;

            if(applyInterface != null)
                  {
                      isUIModified  = applyInterface.isModified();
                  }
            
            if(isUIModified)
            {
                bRetValue =  applyInterface.applyToServer();
            }
            //Added for the Memory leak reported by SPP Team
            else
            {
                setStatusText("No changes to apply");
                
            }
            return bRetValue ;
            //Add Ends
              
    /*if (policyconfigui != null)  //Commented by Balan
		{
			if(policyconfigui.isModified())
			{
				
				 policyconfigui.applyToServer();
			}
		}
		if(pollfilterui != null)
		{
			if(pollfilterui.isModified())
			{
		
				pollfilterui.applyToServer();
			}
		}
		if(mapfilterui != null)
		{
			if(mapfilterui.isModified())
			{
					mapfilterui.applyToServer();
			}
		}
		if(discoveryui != null)
		{
			if(discoveryui.isModified())
			{
					discoveryui.applyToServer();
			}
		}
		if(seedPane != null)
		{
			if(seedPane.isModified())
			{
				seedPane.applyToServer();
			}
		}
		if(trapfilterui != null)
		{
			if(trapfilterui.isModified())
			{
				trapfilterui.applyToServer();
			}
		}

		if(listiconui != null)
		{
			if(listiconui.isModified())
			{
				listiconui.applyToServer();
			}
		}
	
		if(mapiconui != null)
		{
			if(mapiconui.isModified())
			{
				mapiconui.applyToServer();
			}
		}	

		if(loggingmainui != null)
		{
			if(loggingmainui.isModified())
			{
				loggingmainui.applyToServer();
			}
                        }*/

       // Comment Ends
    
		//System.out.println("Policy Boolean"+policyBoolean);
		//System.out.println("Poll Filter Boolean"+pollBoolean);
		//System.out.println("map filter Boolean"+mapFilterBoolean);
		//System.out.println("discovery filter"+discFilterBoolean);
		//System.out.println("trap filter boolean"+trapFilterBoolean);
		//RunTimeConfigSession.getInstance().dispose();
		//this.dispose();
	}
         // Removed by Balan
         // Here Removed Hard Codeed SetIcon class
         // Removal Ends
    // Added by Balan on 15/03/03
   class SetIcon   extends    DefaultTreeCellRenderer
   {
       //Added by Balan on 22/04/03 for black image in RTA
       Hashtable hstImageIcon = null;

       public SetIcon()
       {
           hstImageIcon = new Hashtable(20);
       }

       //Add Ends
       
        public Component getTreeCellRendererComponent(JTree tree,
                            Object value, boolean selected,
                            boolean expanded, boolean leaf,
                            int row, boolean hasFocus) 

        {
             //Added by Balan for SPP for Null Pointer Exception when look and Feel is Changed
             if(firstInstance  == null ) return this;
            //Add Ends
            
            setOpaque(true);
            
            DefaultMutableTreeNode treeNode =   (DefaultMutableTreeNode)value;
            Object obj = treeNode.getUserObject();
            Hashtable attributes = new Hashtable();

            String strImagePath       = null;
            int nLastIndexofSlah      = -1;
            String strNodeId          = null; 

            if(obj instanceof Hashtable)
            {
                attributes         = (Hashtable)obj;
                String treeName    = (String)((Hashtable)obj).get("TREE-NAME");
                setText(resourceBundle.getString(treeName));
                strImagePath       = (String)attributes.get("ICON-FILE");
                strNodeId          = (String)attributes.get("NODE-ID");
            }
                                           
            if(strImagePath  != null && (strImagePath=strImagePath.trim()).length()!=0)
            {
             nLastIndexofSlah = strImagePath.lastIndexOf("/");
            }
            
            if(nLastIndexofSlah != -1)
            {
              String     strImageName          = strImagePath.substring(nLastIndexofSlah+1).trim(); 
              String     strImageFolder        = strImagePath.substring(0,nLastIndexofSlah);

              if(!hstImageIcon.containsKey(strNodeId))
              {
              hstImageIcon.put(strNodeId,
                               getCommonBuilderUIImpl().getScaledImage(strImageName,strImageFolder , 15 , 15 ,Image.SCALE_DEFAULT ) );
              }
              setIcon((ImageIcon)hstImageIcon.get(strNodeId));
              
            }

            if (selected)
            {
                   setForeground(NmsClientUtil.getTreeBackgroundColor());
                   setBackground(NmsClientUtil.getTreeForegroundColor());
            }
            else
            {
                setBackground(NmsClientUtil.getTreeBackgroundColor());
                setForeground(NmsClientUtil.getTreeForegroundColor());
            }
               
            // setOpaque(true);
            // revalidate();
            // updateUI();
            return this;   
        }
       
     }
   // Add Ends

 class close extends WindowAdapter
		{
			public void windowClosing(WindowEvent evt)
			{
				//System.err.println("window closed ..");
				handleCloseEvent();
				// RuntimeConfigFrame.this.dispose();
 				//RunTimeConfigSession.getInstance().dispose();
		}	}

	
	public static String getClassPath()
  {
	  return RunTimeConfigSession.getClassPath();
  }

  public static String getClassPathDelimiter()
  {
	  return RunTimeConfigSession.getClassPathDelimiter();
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

	public static void setStatusText(String s)
	{
		statusLabel.setText(resourceBundle.getString(s));
	}
	
	public static void setStatusColor(Color c)
	{
		statusLabel.setBackground(Color.black);
	}

	public void checkIfApplyNeeded()
	{
		if(applyInterface.isModified())
		{
			JOptionPane jp = new JOptionPane();
			int option =  jp.showConfirmDialog(this, resourceBundle.getString("You have made some changes. Would you like to apply the changes to the Server?"),resourceBundle.getString("Confirmation"),jp.YES_NO_OPTION);
			if (option == jp.YES_OPTION)
			{
				
                            //Added by Balan on 21/06/03 for the Memory leak reported by SPP Team
			    //applyInterface.applyToServer();
                            if(!applyInterface.applyToServer())
                            {
                                showErrorStatus();
                                bisApplyToserverOk = false;
                                ProgressBarPanel.bCanbeRepainted  = false;
                                progressBarPanel.repaint();
                                return;
                            }
                            //Add Ends
			}
			else if (option == jp.NO_OPTION) 
			{
				//Thread.dumpStack();
				//applyInterface.isModified = false;
				applyInterface.refetchData();
			}
                        //Added for the Memory leak reported by SPP Team
                        else if( option == jp.CLOSED_OPTION)
			{
                             bisApplyToserverOk = false;
                             ProgressBarPanel.bCanbeRepainted  = false;
                             progressBarPanel.repaint();
                             return;
			}
                        //Add Ends
		}
                //Added by Balan on 21/06/03 for SPP
                bisApplyToserverOk = true;
                //Add Ends
	}
    
    //Added by Balan on 21/06/03 for the Memory leak reported by SPP Team
    public static void showErrorStatus()
    {
         statusLabel.setText(resourceBundle.getString("Error occured while applying changes to the Server"));
    }
    //Add Ends
	
	public void run()
	{
		System.out.println("in run method...");
	}

	public static void updateTime(String s)
	{
		statusLabel.setText(s);		
	}
	
	public static void applied()
	{
            statusLabel.setText(resourceBundle.getString("Configuration changes applied to Server successfully"));
	}
	
	public static void applyStatus()
	{
            statusLabel.setText(resourceBundle.getString("Applying new configuration to the Server"));
	}

	public void showClasspathSettings()
	{
		ClassPathDialog classPathDialog = new ClassPathDialog(applet);
		classPathDialog.init();
		classPathDialog.populateUI(getClassPath(),getClassPathDelimiter());
		classPathDialog.setVisible(true);
	}

	public String getCurrentlySelectedNode()
	{
		return currentNode;
	}

	public String getHelpKey(String s)
	{
		if(s != null && helpKeys!=null)
		{
			return (String)helpKeys.get(s);
		}
		return null;
	}

    public static void setBusyCursor(Component comp)
	{
                //Added by Balan on 4/7/03 for the memory leak issue reported by SPP Team
                ProgressBarPanel.bCanbeRepainted  = true;
                //Add Ends
		RuntimeConfigFrame.getCommonBuilderUIImpl().setBusyCursor(comp);
		ProgressBarPanel.pBar.setOffset(20);
	}

	public static void setDefaultCursor(Component comp)
	{
		RuntimeConfigFrame.getCommonBuilderUIImpl().setDefaultCursor(comp);
		ProgressBarPanel.pBar.resetOffset();
                //Added by Balan on 4/7/03 for the memory leak issue reported by SPP Team
                ProgressBarPanel.bCanbeRepainted  = false;
                progressBarPanel.repaint();
                //Add Ends
	}


	public void setBusyCursor()
	{
	}

         /*private void initializeRuntimeVariables() // Commented by Balan
	{
		statusLabel = JLabel1;
		setStatusText("Runtime Administration");
		DefaultMutableTreeNode treenode = new DefaultMutableTreeNode(resourceBundle.getString("Categories"));
		DefaultMutableTreeNode topo = new DefaultMutableTreeNode(resourceBundle.getString("Topology"));
		DefaultMutableTreeNode disc = new DefaultMutableTreeNode(resourceBundle.getString("Discovery Filters"));
		DefaultMutableTreeNode seed = new DefaultMutableTreeNode(resourceBundle.getString("Discovery Configurator"));
		DefaultMutableTreeNode list = new DefaultMutableTreeNode(resourceBundle.getString("MO UI Settings"));


		topo.add(disc);
		topo.add(seed);
		topo.add(list);

		DefaultMutableTreeNode map =  new DefaultMutableTreeNode(resourceBundle.getString("Map"));
		DefaultMutableTreeNode mapf = new DefaultMutableTreeNode(resourceBundle.getString("Map Filters"));
		DefaultMutableTreeNode mapi = new DefaultMutableTreeNode(resourceBundle.getString("Map UI Settings"));

		map.add(mapf);
		map.add(mapi);

		DefaultMutableTreeNode log = new DefaultMutableTreeNode(resourceBundle.getString("Miscellaneous"));
		DefaultMutableTreeNode logs = new DefaultMutableTreeNode(resourceBundle.getString("Log Settings"));
		log.add(logs);


		DefaultMutableTreeNode fault = new DefaultMutableTreeNode(resourceBundle.getString("Fault"));
		DefaultMutableTreeNode tfilter = new DefaultMutableTreeNode(resourceBundle.getString("Trap Filters"));
		fault.add(tfilter);


		DefaultMutableTreeNode policy= new DefaultMutableTreeNode(resourceBundle.getString("Policy"));
		DefaultMutableTreeNode pfilters = new DefaultMutableTreeNode(resourceBundle.getString("Policy Configuration"));
		policy.add(pfilters);

		DefaultMutableTreeNode performance= new DefaultMutableTreeNode(resourceBundle.getString("Performance"));
		DefaultMutableTreeNode pollfilters = new DefaultMutableTreeNode(resourceBundle.getString("Polling Filters"));
		performance.add(pollfilters);

		treenode.add(topo);
		treenode.add(map);
		treenode.add(fault);  
		treenode.add(policy);
		treenode.add(performance);
		treenode.add(log);

		model = new DefaultTreeModel(treenode);
		JTree1 = new JTree();
		JTree1.putClientProperty("JTree.lineStyle", "Angled");


		JTree1.setCellRenderer(new SetIcon());
		//JTree1.setBackground(new Color(192,192,192));
		//JTree1.setForeground(new Color(128,0,65));
		JTree1.addTreeSelectionListener(new NodeSelected());
		JScrollPane1 = new JScrollPane(JTree1);

		helpKeys.put(resourceBundle.getString("Categories"),"Runtime_Admin_Main_Help");
		helpKeys.put(resourceBundle.getString("Topology"),"Runtime_Admin_Topology_Main_Help");
		helpKeys.put(resourceBundle.getString("Discovery Filters"),"Runtime_Admin_Topology_DiscoveryFilters_Help");
		helpKeys.put(resourceBundle.getString("Discovery Configurator"),"Runtime_Admin_Topology_DiscoveryConfigurator_Help");
		helpKeys.put(resourceBundle.getString("MO UI Settings"),"Runtime_Admin_Topology_MOUISettings_Help");
		helpKeys.put(resourceBundle.getString("Map"),"Runtime_Admin_Map_Main_Help");
		helpKeys.put(resourceBundle.getString("Map Filters"),"Runtime_Admin_Map_MapFilters_Help");
		helpKeys.put(resourceBundle.getString("Map UI Settings"),"Runtime_Admin_Map_MapUISettings_Help");
		helpKeys.put(resourceBundle.getString("Fault"),"Runtime_Admin_Fault_Main_Help");
		helpKeys.put(resourceBundle.getString("Trap Filters"),"Runtime_Admin_Fault_TrapFilters_Help");
		helpKeys.put(resourceBundle.getString("Policy"),"Runtime_Admin_Policy_Main_Help");
		helpKeys.put(resourceBundle.getString("Policy Configuration"),"Runtime_Admin_Policy_PolicyConfiguration_Help");
		helpKeys.put(resourceBundle.getString("Performance"),"Runtime_Admin_Performance_Main_Help");
		helpKeys.put(resourceBundle.getString("Polling Filters"),"Runtime_Admin_Performance_PollingFilters_Help");
		helpKeys.put(resourceBundle.getString("Miscellaneous"),"Runtime_Admin_Miscellaneous_Main_Help");
		helpKeys.put(resourceBundle.getString("Log Settings"),"Runtime_Admin_Miscellaneous_LogSettings_Help");
                }*/
         //Comment Ends
												                

 

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
      if(evt.getActionCommand().equals(resourceBundle.getString("Apply To Server")))
      {
         
//<UserCode_Begin_MenuItem_ApplyMenuItem>
//Please add the action code for menu Here Apply To Server
handleApplyToServerEvent();
//<UserCode_End_MenuItem_ApplyMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("Classpath settings")))
      {
         
//<UserCode_Begin_MenuItem_Classpath_settingsMenuItem>
//Please add the action code for menu Here Classpath settings
showClasspathSettings();
//<UserCode_End_MenuItem_Classpath_settingsMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("Close")))
      {
         
//<UserCode_Begin_MenuItem_CloseMenuItem>
//Please add the action code for menu Here Close
handleCloseEvent();
//<UserCode_End_MenuItem_CloseMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("Help Contents")))
      {
         
//<UserCode_Begin_MenuItem_Help_ContentsMenuItem>
//Please add the action code for menu Here Help Contents
handleHelpEvent();
//<UserCode_End_MenuItem_Help_ContentsMenuItem>

      
}
      if(evt.getActionCommand().equals(resourceBundle.getString("About")))
      {
         
//<UserCode_Begin_MenuItem_AboutMenuItem>
//Please add the action code for menu Here About
handleAboutEvent();
//<UserCode_End_MenuItem_AboutMenuItem>

      
}

  } 
}
//<End_class_MenuToolBarAction>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

  //Added by Balan 15/03/03
    public JPanel getBasePanel()
    {
        if(basePanel != null) basePanel.removeAll();
        return  basePanel;

    }
   
    private void initializeRuntimeVariables()
    {
        hstPanelNames  = new Hashtable(16);
         //Added by Balan on 4/7/03 for the Memory leak Issue Reported by SPP Team
         helpKeys       = new Hashtable(16);
        //Add Ends
        statusLabel = JLabel1;
	setStatusText("Runtime Administration");
        DefaultMutableTreeNode  dAddNode        = null;
        String                  strTreeName     = null;
        String                  strHelpKeyValue = null;  
        URL                     urlDocBase       = null;  
        try
        {
           urlDocBase                   = new  URL( NmsClientUtil.applet.getDocumentBase()+"../conf/RunTimeConfig.xml");
           XMLDataReader xmldataReader  = new XMLDataReader(urlDocBase.openStream());
           XMLNode xmlRootNode          = (XMLNode)xmldataReader.getRootNode();
           int           nChildCount    = -1;
           if(xmlRootNode == null) return;
           strTreeName    =(String) xmlRootNode.getAttribute("TREE-NAME");
           strTreeName    = strTreeName.trim();
           strHelpKeyValue=(String) xmlRootNode.getAttribute("HELP-FILE-KEY");
           dAddNode = new DefaultMutableTreeNode(strTreeName);
           helpKeys.put(strTreeName,strHelpKeyValue);
           dAddNode.setUserObject(xmlRootNode.getAttributeList());
           nChildCount  = xmlRootNode.getChildCount();
           
           if( nChildCount > 0)
               constructChildNodes(xmlRootNode.children(),dAddNode);
          
           ///
           model = new DefaultTreeModel(dAddNode);
           JTree1 = new JTree();
           JTree1.putClientProperty("JTree.lineStyle", "Angled");
           

           JTree1.setCellRenderer(new SetIcon());
           //JTree1.setBackground(new Color(192,192,192));
           //JTree1.setForeground(new Color(128,0,65));
           JTree1.addTreeSelectionListener(new NodeSelected());
           JScrollPane1 = new JScrollPane(JTree1);
           
           ///


        }
        catch(Exception exec)
        {
            exec.printStackTrace();
        }
    }

    private void constructChildNodes (Enumeration enumerate , DefaultMutableTreeNode dNodeParent)
    {
        DefaultMutableTreeNode  dChildNode       = null;
        XMLNode                 xmlChildNode     = null;
        int                     nChildCount      = -1;
        String                  strChildTreeName = null;
        String                  strChildHelpKeyValue = null;          
        for(;enumerate.hasMoreElements();)
        {
            xmlChildNode      =(XMLNode)enumerate.nextElement();
            if(xmlChildNode.getNodeType() == XMLNode.COMMENT)
            {
                continue;
            }
            strChildTreeName  =(String) xmlChildNode.getAttribute("TREE-NAME");
            strChildTreeName   = strChildTreeName.trim();
            strChildHelpKeyValue=(String) xmlChildNode.getAttribute("HELP-FILE-KEY");
            dChildNode         = new DefaultMutableTreeNode(strChildTreeName);
            helpKeys.put(strChildTreeName,strChildHelpKeyValue);
            dChildNode.setUserObject(xmlChildNode.getAttributeList());
            dNodeParent.add(dChildNode);
            nChildCount        = xmlChildNode.getChildCount();
            if(nChildCount > 0) constructChildNodes(xmlChildNode.children(),dChildNode);
        }
        System.runFinalization();
    }

    // Add Ends

    //Added by Balan on 29/05/03 for SPPS Requirement to invoke their dialog with this instance set as Parent
    public static RuntimeConfigFrame getInstance()
    {
        return firstInstance;  
    }
    //Add Ends
         
}


