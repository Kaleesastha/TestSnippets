/*
   $Id: MainPanel.java,v 1.1 2002/12/19 05:04:41 muthuganeshj Exp $
 */
/**
 *  MainPanel.java
 */
package com.adventnet.nms.startclient;

import javax.swing. *;
import javax.swing.event. *;
import javax.swing.tree. *;

import java.awt. *;
import java.util. *;
import java.net. *;
import java.io. *;
import java.applet.Applet;
import java.awt.event. *;
import java.lang.reflect.Constructor;

import com.adventnet.nms.util. *;
import com.adventnet.nms.alertui. *;
import com.adventnet.nms.eventui. *;
import com.adventnet.nms.mapui. *;
import com.adventnet.nms.poll. *;
import com.adventnet.nms.tree. *;
import com.adventnet.nms.broadcast.BroadcastClient;
import com.adventnet.alf.themes.AdventNetThemeAPI;
import com.adventnet.alf.themes.AdventNetThemeFileHandler;

/**
   This is the main class which is handling all the UI related stuff. This class is the listener for all the 
   NmsPanelEvents. Only the following method can be used by the user . Rest of the methods are 
   only for internal purpose.
   <br>
   <br>handleNmsPanelEvent

   <p> The public methods which are present here, recommended not use the methods directly. The equivalent 
   methods are present in NmsUiAPi class.
   
 */

public class MainPanel extends JPanel implements Runnable, TreeSelectionListener,NmsPanelEventListener, ActionListener, ItemListener
{
    JFrame frame;
    public JApplet applet;
    JTree tree;
    JMenuBar menuBar = new JMenuBar ();
    JSplitPane splitPane1;
    JSplitPane splitPane2;
    ToolBar tbar;
    DeviceDB deviceDB;
    
    XMLTreeModel treeModel;
    TreeRenderer treeCellRenderer;
    CustomTreeCellEditor treeCellEditor;
    
    Object[] currentComponents;
    JPanel cardPanel = new JPanel ();
    JPanel toolbarPanel = new JPanel();
    CardLayout card = new CardLayout ();
    Vector nmsPanelNames = new Vector ();
    //popup menu.
    CustomPopupMenu popup;
    
    NmsPanel currentPanel = null;
    XMLNode currentNode = null;
    String logoURL = null;    
    
    int currentPanelIndex = -1;
    AlertCounts alCounts;
    
    public Hashtable panelList = new Hashtable (5);
    public Hashtable keyTreeList = new Hashtable (5);
    private Hashtable initializedList = new Hashtable (5);
    //This Hashtable stores the (moduleids,key of the panel) pairs of the modules 
    //as in DeviceData.xml - clarence
    public Hashtable moduleIds = new Hashtable (5);
    
    //This Hashtable stores the (moduleids,XmlNode) pairs of which can be
    //used to access the attributes of the XmlNode given a moduleId. - clarence
    public Hashtable xmlNodes = new Hashtable (5);
    
    int cardx, cardy;
    int alerty;
    int wid, ht;
    
    //label for printing the error messages.
    JLabel statusLabel;
    Image infoImage = null;
    ImageIcon imageName = null;
    ImageIcon imageIcon = null;
    JLabel logoLabel = new JLabel();
    String firstNmsPanelToBeShown = "ipnet.netmap";//No Internationalisation
    String defaultLookAndFeel = null;
    public Vector menusFromBegining;
    public Vector menusFromEnd;
    JDesktopPane desktopPane = new JDesktopPane ();
    
    public JComponent testPanel = desktopPane;
    
    boolean useInternalFrame = true;
    public boolean openMultipleMaps = true;
    private Hashtable panelkeyVsToolbars = null;
    private Properties internalFramesProp = new Properties ();
    String treeViewWidth = null;
    String treeViewHeight = null;
    public boolean isInitialized = false;
    public LedPanel ledpanel = new LedPanel(49,17);
    private boolean Detach = false;
    private Vector createdPanels = new Vector();
    
    NmsProgressBar progressbar;
    private int count;

    JCheckBox removetbar;
    ImageIcon toremove;
    ImageIcon toremovemo;
    ImageIcon afterremoved;
    ImageIcon afterremovedmo;
    JCheckBoxMenuItem showToolbar;
    JScrollPane treeView = null;
    private boolean ignore = true;

    public Vector eventDispatchQueue = new Vector();
    public boolean isTreeConstructed = false;
    
    //Sathish changes for StatusBar customization starts here. (from SP6)
    //********************************************************
    
    private JPanel statusBarPanel = null;
    private JPanel statusPanel = null;
	private int statusLabelWidth = 100;
	private int widthOfComponentToLeft = 0;
	private int widthOfComponentToRight = 0;
	private int totalStatusBarWidth = 100;
	private BorderLayout borderLayout = new BorderLayout();	
	private JComponent componentToLeftOfLabel = null;
	private JComponent componentToRightOfLabel = null;
	
	public final int LEFT_OF_LABEL = 0;
	public final int RIGHT_OF_LABEL = 1;
	
	//********************************************************
	//Sathish changes for StatusBar customization ends here. (from SP6)


    public MainPanel (JFrame frame)
    {
        this.frame = frame;
		frame.setName("NmsMainFrame");//No Internationalisation
		new Thread(new Runnable() {             
			public void run()             
			{                 
				try
				{
					Thread.sleep(15*60*1000);
					//Thread.sleep(30000);
					shutDown(null);
				}
				catch(Exception excp)
				{
					excp.printStackTrace();
					}
				}
			}).start(); 
    }

    /** The applets init method initializes the GUI */
    public void init (JApplet applet, DeviceDB devDB)
    {
        DispatcherThread dispatcher = new DispatcherThread(this);
        dispatcher.start();

        defaultLookAndFeel = applet.getParameter ("DEFAULT_LOOKANDFEEL");//No Internationalisation//No Internationalisation
        logoURL = applet.getParameter ("LOGO_URL");//No Internationalisation
        this.applet = applet;
        deviceDB = devDB;
        setLayout (new BorderLayout ());
        
        //adding the statusLabel
        
        URL docBase = applet.getDocumentBase ();




        String userDir = docBase + "../users/" + NmsClientUtil.getUserName() + "/";//No Internationalisation


        infoImage = NmsClientUtil.getImage (docBase +
                                            "../images/alarm.png");//No Internationalisation//No Internationalisation
        
        String logoName =applet.getParameter("LOGO_ICON");//No Internationalisation
        imageName = NmsClientUtil.getImageIcon(applet.getDocumentBase() + logoName);
        String action = NmsClientUtil.applet.getParameter("ACTION-ON-NO-PRIVILEGE");		   //No Internationalisation
        if (action == null)
            action = "HIDE";//No Internationalisation
        if(action.equalsIgnoreCase("DISABLE") || (action.equalsIgnoreCase("HIDE")))//No Internationalisation
            {
                action = action.toUpperCase();
            }
        else
            {
                action = "HIDE";//No Internationalisation
            }
        



        String str = NmsClientUtil.applet.getDocumentBase ()+ "../unauthenticatedservlets/com.adventnet.nms.servlets.MenuFileParser?userName=" + NmsClientUtil.getUserName()+"&ACTION-ON-NO-PRIVILEGE="+action+ "&menuFilename=toolbar"  ;//No Internationalisation

        panelkeyVsToolbars = XMLToolBar.createToolBar (str);//No Internationalisation  
        if(panelkeyVsToolbars != null)
            {
                for (Enumeration en = panelkeyVsToolbars.keys (); en.hasMoreElements ();)
                    {
                        String keys = en.nextElement ().toString();
                        ((ToolBar) panelkeyVsToolbars.get (keys)).addActionListener (this);
                    } 
                if(panelkeyVsToolbars.containsKey("Default"))//No Internationalisation
                    tbar = (ToolBar) panelkeyVsToolbars.get ("Default");//No Internationalisation//No Internationalisation
                else
                    tbar = null;
            }
        else
            {
                tbar = null;
            }
        // tbar.setPreferredSize (new Dimension (786, 44));
        // tbar.setMinimumSize (new Dimension (786, 44));
        
        // Following lines are added to display LOGO in 
        // main panel while initializing
        
        logoLabel.setOpaque (true);
        logoLabel.setForeground (Color.white);
        if (imageName != null)
            {
                int height = imageName.getIconHeight();
                int width  = imageName.getIconWidth();
            
                imageIcon = imageName;
            
                if(width > 121 || height > 35)
                    {
                        imageIcon.setImage((imageName).getImage().getScaledInstance(121,35,Image.SCALE_DEFAULT));
				//setIcon(icon);
                        height = 35;
                        width  = 121;
                    }
                logoLabel.setIcon (imageIcon);
            }
        logoLabel.setPreferredSize(new Dimension (125,35));
        logoLabel.setMinimumSize(new Dimension (125,35));
        toremove=NmsClientUtil.getImageIcon(applet.getDocumentBase()+"../images/normal.png");//No Internationalisation
        toremovemo=NmsClientUtil.getImageIcon(applet.getDocumentBase()+"../images/normalmover.png");//No Internationalisation
        afterremoved=NmsClientUtil.getImageIcon(applet.getDocumentBase()+"../images/hide.png");//No Internationalisation
        afterremovedmo=NmsClientUtil.getImageIcon(applet.getDocumentBase()+"../images/hidemover.png");//No Internationalisation
        if(panelkeyVsToolbars != null)
            {
                toolbarPanel.setLayout(new BorderLayout());
                if(tbar != null)
                    {
                        removetbar=new JCheckBox(toremove);
                        removetbar.setActionCommand("HideToolbar");//No Internationalisation
                        removetbar.setPreferredSize(new Dimension(9,40));
                        removetbar.setSelected(true);
                        removetbar.setToolTipText(NmsClientUtil.GetString("Hide Toolbar"));
                        toolbarPanel.add(removetbar,BorderLayout.WEST);
                        toolbarPanel.add(tbar, BorderLayout.CENTER);
                        toolbarPanel.add(logoLabel, BorderLayout.EAST);	
                        logoLabel.addMouseListener(mouseListener);
                        removetbar.addMouseListener(mouseListener);
                        add (toolbarPanel, BorderLayout.NORTH);
                        toolbarPanel.revalidate(); 
                        toolbarPanel.invalidate();    		
                    }
            }
        cardPanel.setLayout (card);
        cardPanel.setPreferredSize (new Dimension (590, 460));
        cardPanel.setMinimumSize (new Dimension (250, 200));
        cardx = 204;
        cardy = 60;
        
        String param = applet.getParameter ("USE_INTERNALFRAME");//No Internationalisation//No Internationalisation
        if  ( (param  != null) && (!param.trim().equals("")))
            {
                if (param.equalsIgnoreCase ("true"))//No Internationalisation//No Internationalisation
                    useInternalFrame = true;
                else
                    useInternalFrame = false;
            }
        
        openedExternalFrames = new Vector (5);
        if (useInternalFrame)
            {
                openedInternalFrames = new Vector (5);
            }
        else
            {
                openedCardPanelNames = new Vector (5);
            }
        param = applet.getParameter ("OPEN_MULTIPLE_MAPS");//No Internationalisation//No Internationalisation
        if ((param != null) && (!param.trim().equals("")))
            {
                if (param.equalsIgnoreCase ("true"))//No Internationalisation//No Internationalisation
                    openMultipleMaps = true;
                else
                    openMultipleMaps = false;
            }
        treeViewWidth  = applet.getParameter ("TreeViewWidth");//No Internationalisation//No Internationalisation
        treeViewHeight = applet.getParameter ("TreeViewHeight");//No Internationalisation//No Internationalisation
        Vector temp = NmsClientUtil.getMenuVector ("framemenu.xml",this);//No Internationalisation//No Internationalisation
        
        if ((temp != null) && (temp.size() == 2))
            {
                menusFromBegining = (Vector) temp.elementAt (0);
                menusFromEnd      = (Vector) temp.elementAt (1);
                //removed the adding of the menus here..
            }
        int size = menusFromBegining.size ();
        for (int i = 0; i < size; i++)
            {
                menuBar.add ((JMenu) menusFromBegining.elementAt (i));
            }
        size = menusFromEnd.size ();
        for (int i = 0; i < size; i++)
            {
                menuBar.add ((JMenu) menusFromEnd.elementAt (i));
            }
        
        createLookAndFeelMenu ();
        createWindowMenu ();
        createCheckBoxMenuItem();

        setJMenuBar (frame,menuBar);
        
        desktopPane.setPreferredSize (cardPanel.getPreferredSize ());
        
        desktopPane.setMinimumSize (new Dimension (250, 200));
        //Make dragging faster:
        desktopPane.putClientProperty ("JDesktopPane.dragMode", "outline");//No Internationalisation//No Internationalisation
        //This is to read internal frame size and location from a file
        try
            {
                URL url = new URL (userDir + "FramesInfo.conf");//No Internationalisation//No Internationalisation
                InputStream istream = url.openStream ();
                if (istream != null)
                    {
                        internalFramesProp.load (istream); 
                    }
                int xy[] = getInternalFrameSizeAndLoc("NmsMainFrame");//No Internationalisation//No Internationalisation
                if (xy != null && xy.length >= 4)
                    {
                        frame.setBounds(xy[0],xy[1],xy[2],xy[3]);
                    }
                else
                    {
                        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize ();
                        frame.setBounds(0,0,screenSize.width,screenSize.height);
                    }
            
            }
        catch (Exception exc)
            {
                Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize ();
                frame.setBounds(0,0,screenSize.width,screenSize.height);
                //System.err.println("Exception raised when reading internal frame size and locations\n " + exc);//No Internationalisation//No Internationalisation
            }
        treeViewWidth = (String)internalFramesProp.get("TreeViewWidth");//No Internationalisation
        treeViewHeight = (String)internalFramesProp.get("TreeViewHeight");//No Internationalisation
        if ((treeViewWidth == null) || (treeViewWidth.equals("")))//No Internationalisation
            treeViewWidth = applet.getParameter("TreeViewWidth");//No Internationalisation
        if ((treeViewHeight == null) || (treeViewHeight.equals("")))//No Internationalisation
            treeViewHeight = applet.getParameter("TreeViewHeight");//No Internationalisation
        boolean b = true;
        String toolbar = (String)internalFramesProp.get("ToolBar");//No Internationalisation
        if ((toolbar != null) && (toolbar.equals("false")))//No Internationalisation
            b = false;
		
        if(tbar != null)
            {
                if (!b)
                    {
                        removetbar.setSelected(false);
                        removetbar.setActionCommand("ShowToolbar");//No Internationalisation
                        removetbar.setPreferredSize(new Dimension(60,8));
                        removetbar.setIcon(afterremoved);
                        removetbar.setToolTipText(NmsClientUtil.GetString("Show Toolbar"));
                        toolbarPanel.remove(tbar);
                        toolbarPanel.remove(logoLabel);
                        toolbarPanel.repaint();
                    }

            }
        progressbar = ((NmsMainApplet)applet).progressbar;
        run();
    }				//end of init
    
    private void registerBroadcastClient()
    {
        BroadcastClient bclient = BroadcastClient.getInstance();	
    }
    
    boolean runDone = false;
    
    public void run ()
    {
        //JFrame f;
        frame.setCursor (NmsClientUtil.wait_cursor);
        //NmsClientUtil.showWaitDialog1 (frame, "Please wait while the Client Queries the Server.", false);//No Internationalisation

        currentComponents    = new Object[1];
        currentComponents[0] = deviceDB.getRootNode ();
        treeCellRenderer     = new TreeRenderer (applet);
        treeModel            = new XMLTreeModel(deviceDB.getRootNode());


        createNmsPanel(null);

        initializeTreeParms (deviceDB);

        //added by shanks to parse from clientparams.conf
        String param = applet.getParameter("INIT_PANEL");//No Internationalisation//No Internationalisation
        if (param != null && ! param.trim().equals(""))//No Internationalisation//No Internationalisation
            {
                String clientPanel = param.trim();
                StringTokenizer panelTokens=new StringTokenizer(clientPanel,",");//No Internationalisation//No Internationalisation
                int i=panelTokens.countTokens();
                String temp;
                for(int j=1;j<=i-1;j++)
                    {
				temp = (String)panelTokens.nextElement();
			}
			firstNmsPanelToBeShown =(String)panelTokens.nextElement ();
		}//done by shanks

		tree = new NmsTree();
		tree.putClientProperty("JTree.lineStyle", "Angled");//No Internationalisation
		tree.setModel (treeModel);
		tree.setCellRenderer (treeCellRenderer);	// included By GBReddy
		tree.setEditable(true);

		//cell Editor.. Ask Vidhya why do we need a TreeCellEditor at all ??
		treeCellEditor = new CustomTreeCellEditor(this);
		tree.setCellEditor(treeCellEditor);
		tree.setRowHeight (20);	// included By GBReddy
		tree.getSelectionModel ().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener (this);
		tree.addMouseListener (mouseListener);
		//otherwise in Solaris L&F the tree looks like a patch...
		//tree.setBackground(Color.white);
		tree.setBackground(NmsClientUtil.getTreeBackgroundColor());

		treeView = new JScrollPane (tree);
		treeView.setPreferredSize (new Dimension (200, 360));
		treeView.setMinimumSize (new Dimension (50, 100));

		JComponent lhsPanel = treeView;

		//Whether to show or not the AlertCountsPanel.This might be
		//changed once we find a way in the server side to avoid 
		//sending the updates of AlertCounts to the client..!!
		param = applet.getParameter("SHOW_ALERT_COUNTS_PANEL");//No Internationalisation//No Internationalisation

		//ALC initializations..
		if(param != null && param.trim().equalsIgnoreCase("true"))//No Internationalisation//No Internationalisation
		{
			alCounts = new AlertCounts ((AlertApplet) panelList.get ("AlertApplet"));//No Internationalisation//No Internationalisation
			alCounts.setPreferredSize (new Dimension (200, 100));
			alCounts.setMinimumSize (new Dimension (50, 40));
			alerty = 360;
			alCounts.updateCounts ();

			splitPane1 = new JSplitPane (JSplitPane.VERTICAL_SPLIT, 
					treeView, alCounts);
			splitPane1.setContinuousLayout (true);
			splitPane1.setPreferredSize (new Dimension (200, 460));
			splitPane1.setMinimumSize (new Dimension (50, 200));
			lhsPanel = splitPane1;
		}
		if (useInternalFrame)
		{
			splitPane2 = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, lhsPanel, desktopPane);
			testPanel = desktopPane;
		}
		else
		{
			splitPane2 = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, lhsPanel, cardPanel);
			testPanel = cardPanel;
		}

		splitPane2.setContinuousLayout (true);
		splitPane2.setPreferredSize (new Dimension (800, 600));
		splitPane2.setMinimumSize (new Dimension (400, 300));

		//To set panel size from the conf file
		try
		{
			int tw = Integer.parseInt (treeViewWidth);
			int th = Integer.parseInt (treeViewHeight);
			treeView.setPreferredSize (new Dimension (tw, th));
			splitPane1.setPreferredSize (new Dimension (tw, 460));
		}
		catch (Exception ex)
		{
			treeView.setPreferredSize (new Dimension (200, 300));
		}

		//invalidate ();
		add (splitPane2, BorderLayout.CENTER);
		//validate ();

		NmsClientUtil.hideWaitDialog (frame);
		runDone = true;
		//doLayout ();
		isInitialized = true;
				
		//Sathish changes starts here for StatusBar customization
		/********************************************************
		
		/*statusLabel = new JLabel ();
		statusLabel.setOpaque (true);
		statusLabel.setForeground (Color.white);
		if (infoImage != null)
		{
			ImageIcon icon = new ImageIcon (infoImage);
			statusLabel.setIcon (icon);
		}

		JPanel led = new JPanel ();
		led.setLayout(new BorderLayout());
		led.add(ledpanel,BorderLayout.WEST);
		led.add(statusLabel);
		add (led, BorderLayout.SOUTH);*/
		constructStatusBar();
		addComponentListener(new ComponentResizeListener());
		
		//*********************************************************
		//Sathish changes ends here.

		//added by shanks
		Vector abstNmsPanelVector=new Vector(5);
		for(Enumeration en=panelList.elements();en.hasMoreElements();)
		{
			Object panelObject=en.nextElement();
			if(panelObject instanceof AbstractBaseNmsPanel)
			{
				abstNmsPanelVector.addElement (panelObject);
			}
		}
		while(abstNmsPanelVector.size() > 0)
		{
			for(int i=0;i<abstNmsPanelVector.size();i++)
			{
				if(((AbstractBaseNmsPanel)(abstNmsPanelVector.elementAt (i))).isInitialized())
				{
					abstNmsPanelVector.removeElementAt (i);
				}
			}
			try
			{
				Thread.sleep(200);
			}
			catch(Exception e)
			{
				// do nothing...
			}
		}
		//registering Broadcast client. 
		registerBroadcastClient();

                if(progressbar != null)
                {
		     int maxval = progressbar.getMaximum();
		     progressbar.setValue(maxval);
		     progressbar.window.setVisible(false);
		     progressbar.window.dispose();
		     progressbar = null;
                }
        if(defaultLookAndFeel.equalsIgnoreCase("javax.swing.plaf.metal.MetalLookAndFeel"))
        {
	    try
      	    {       
                URL url = new URL(NmsClientUtil.applet.getDocumentBase()+"../conf/Themes.conf");
                AdventNetThemeAPI.getInstance().setThemeConfigFile(url);
                AdventNetThemeAPI.getInstance().setStartUpTheme();
       	    }       
	    catch(Exception e)
	    {
                System.out.println(NmsClientUtil.GetString("Specified theme cannot be applied since the corresponding theme file is not present"));
	    }
        }
		
		Runnable run = new Runnable()
		{
			public void run()
			{
			    frame.setVisible(true);
				try
				{
					showFirstNmsPanel();//done by shanks
				}
				catch(Exception ee)
				{
				}
			}
		}; 
		SwingUtilities.invokeLater(run);
		//GenericSession.getInstance();
                LockOutController.getInstance().start();
                new LockOutFrame();
		/*Runtime.getRuntime().addShutdownHook
				(new Thread ("ShutdownHookForClient")//No Internationalisation
							{
								public void run()
								{
								NmsClientUtil.printOnConsole("Shutting down client ... ");//No Internationalisation
								//((NmsMainApplet)applet).stop();
								saveSizeAndLocationOfInternalFrames();
								NmsClientUtil.cleanUpForAppletReload ();
								MainPanel.this.stop();
								MainPanel.this.destroy();
								NmsClientUtil.printOnConsole("Done.");//No Internationalisation
								}
							}
							);*/

	}
	
	//Sathish changes start here for StatusBar customization
	//**********************************************************************

	private void constructStatusBar()
	{
		statusBarPanel = new JPanel();
		statusBarPanel.setLayout(borderLayout);
		borderLayout.setHgap(3);
		statusBarPanel.setPreferredSize(new Dimension(50,20));
		statusPanel = new JPanel();
		statusPanel.setLayout(new BorderLayout());
		ledpanel = new LedPanel(49,17);	
                String parameter = applet.getParameter("SHOW_LEDPANEL");
		if(parameter == null || parameter.equalsIgnoreCase("true"))
		{
			statusPanel.add(ledpanel,BorderLayout.WEST);
		}
		statusLabel = new JLabel();
		statusLabel.setBorder(new javax.swing.border.EtchedBorder(1));
		statusLabel.setOpaque(true);
		statusLabel.setForeground(Color.white);
		statusPanel.add(statusLabel,BorderLayout.CENTER);
		layoutComponentsInStatusBar();
		add(statusBarPanel,BorderLayout.SOUTH);		
	}
	
	private void layoutComponentsInStatusBar()
	{
		if(statusBarPanel != null)
		{
			borderLayout.setHgap(5);
			if(componentToLeftOfLabel != null)
			{
				int width = frame.getSize().width*widthOfComponentToLeft/100 ;
				int height = frame.getSize().height ;
				componentToLeftOfLabel.setPreferredSize(new Dimension(width,height));
				statusBarPanel.add(componentToLeftOfLabel,BorderLayout.WEST);
			}
			if (statusLabelWidth > 0)
			{
				int width = frame.getSize().width*statusLabelWidth/100 ;
				int height = frame.getSize().height ;
				statusPanel.setPreferredSize(new Dimension(width,height));
				statusBarPanel.add(statusPanel,BorderLayout.CENTER);
			}
			if(componentToRightOfLabel != null)
			{
				int width = frame.getSize().width*widthOfComponentToRight/100 ;
				int height = frame.getSize().height ;
				componentToRightOfLabel.setPreferredSize(new Dimension(width,height));
				statusBarPanel.add(componentToRightOfLabel,BorderLayout.EAST);
			}
		}
	}
	
	private void validateComponentWidths()
	{
		int tempWidth = 100 - widthOfComponentToLeft-widthOfComponentToRight;
		if(tempWidth < 0)
		{
			assumeDefaultWidths();
		}
		statusLabelWidth = 100 - widthOfComponentToLeft - widthOfComponentToRight;
	}
	
	private void assumeDefaultWidths()
	{
		System.err.println(NmsClientUtil.GetString("Specified component width in statusbar exceeds limits. Assuming default values"));
		widthOfComponentToLeft = 20;
		widthOfComponentToRight = 20;
	}
	
	//API to be called from NmsClientUtil for adding user components to StatusBar.
	
	public void addComponentToStatusBar(JComponent component, int position, int percentageWidth)
	{
		if(component != null)
		{
			if(position == LEFT_OF_LABEL)
			{
				if(componentToLeftOfLabel == null)
				{
					if(percentageWidth > 0 && percentageWidth <= 100)
					{
						widthOfComponentToLeft = percentageWidth;
					}
					else 
					{
						assumeDefaultWidths();
					}
					validateComponentWidths();
					componentToLeftOfLabel = component;
				}
				else
				{
					System.err.println(NmsClientUtil.GetString("Component already present to the left of the Status Label." +
					 "Please remove the same before adding a new component"));
					return;
				}
			}
			else if(position == RIGHT_OF_LABEL)
			{
				if(componentToRightOfLabel == null)
				{
					if(percentageWidth > 0 && percentageWidth <= 100)
					{
						widthOfComponentToRight = percentageWidth;
					}
					else 
					{
						assumeDefaultWidths();
					}
					validateComponentWidths();
					componentToRightOfLabel = component;
				}
				else
				{
					System.err.println(NmsClientUtil.GetString("Component already present to the right of the Status Label." +
					 "Please remove the same before adding a new component"));
					return;
				}
			}
                        else
                        {
                               System.err.println(NmsClientUtil.GetString("Invalid position value specified for component addition"));
                                return;
                        }
		}
		layoutComponentsInStatusBar();
	}
	
	//API to be called from NmsClientUtil for removing user components from StatusBar.
	
	public boolean removeComponentFromStatusBar(int position)
	{
		if(position == LEFT_OF_LABEL)
		{
			if(componentToLeftOfLabel != null)
			{
				statusBarPanel.remove(componentToLeftOfLabel);
				componentToLeftOfLabel = null;
				widthOfComponentToLeft = 0;
				validateComponentWidths();
				layoutComponentsInStatusBar();
				SwingUtilities.updateComponentTreeUI(statusBarPanel);				
				return true;
			}
			else
			{
				System.err.println(NmsClientUtil.GetString("Component to the Left of the StatusBar does not exist"));
				return false;
			}
		}
		else if(position == RIGHT_OF_LABEL)
		{
			if(componentToRightOfLabel != null)
			{
				statusBarPanel.remove(componentToRightOfLabel);
				componentToRightOfLabel = null;
				widthOfComponentToRight = 0;
				validateComponentWidths();
				layoutComponentsInStatusBar();
				SwingUtilities.updateComponentTreeUI(statusBarPanel);
				return true;
			}
			else
			{
				System.err.println(NmsClientUtil.GetString("Component to the Right of the StatusBar does not exist"));
				return false;
			}
		}
		else
		{
			System.out.println(NmsClientUtil.GetString("Invalid position specified for removal."));
			return false;
		}
	}
	
	//Inner class to handle the resizing actions of the NmsMainframe. This has to be done
	//to maintain the percentage widths of the components in the StatusBar.
	
	class ComponentResizeListener extends ComponentAdapter
	{
		public void componentResized(ComponentEvent ce)
		{
			if(statusBarPanel != null)
			{			
				validateComponentWidths();
				layoutComponentsInStatusBar();
				SwingUtilities.updateComponentTreeUI(statusBarPanel);
			}
		}
	}
	
	//********************************************************************************
	//Sathish change for StatusBar customization ends here.

	/** Notifies all the panels by calling the stop method */
	public void stop()
	{
		for(Enumeration en=panelList.elements();en.hasMoreElements();)
		{
			NmsPanel panelObject = (NmsPanel) en.nextElement();
			panelObject.stop();
			//NmsClientUtil.printOnConsole("Stopped "+panelObject.key());//No Internationalisation
		}
	}

	/* Notifies all the panels by invoking the destroy method by passing the SHUT-DOWN as the argument */
	public void destroy()
	{
		for(Enumeration en=panelList.elements();en.hasMoreElements();)
		{
			NmsPanel panelObject = (NmsPanel) en.nextElement();
			panelObject.destroy("SHUT-DOWN");//No Internationalisation
			//NmsClientUtil.printOnConsole("Destroy "+panelObject.key());//No Internationalisation
		}
		
 		Class acClass = JDesktopPane.class;
	 	try
 		{
        	java.lang.reflect.Method  m = acClass.getMethod("setSelectedFrame",null);
			if (m != null)
         	{
            	m.invoke(null,null);
         	}
 		}
		catch(Exception e)
 		{
 		}
	}           

	/** 
		The reference to the splitpane is returned.
		@return the reference of the splitpane
	 */
    public JSplitPane getSplitPane()
    {
        return splitPane2;
    }

    /** this method provides the children custom views to be deleted.
     *@param disp the filter to be deleted
     *@return Vector the child filters to be deleted.
     *@author vidhya
     **/
    public Vector getFiltersToBeRemoved(String disp)
    {
        Vector filterVec = new Vector();
        XMLNode node = (XMLNode)xmlNodes.get(disp);
		if (node == null)
			return new Vector();
        filterVec.addElement((String)node.getAttribute("ID"));//No Internationalisation//No Internationalisation
        getChildren ( node,filterVec);
        return filterVec;
    }
    
	
	/** private recursive  method to fetch the children*/
    private void  getChildren(XMLNode root,Vector filterVec)
    {
        Vector child = root.getChildNodes();
        int index = child.size();
        if(index == 0 )
            return;
        for(int i = 0 ; i< index ; i++)
        {
            XMLNode childNode =  (XMLNode)(child.elementAt(i));
            filterVec.addElement((String)childNode.getAttribute("ID"));//No Internationalisation
            getChildren(childNode,filterVec);
        }
        
    }
    /** This method returns the popup menu associated with the particular panel.
     * This is obtained from Tree.xml, where the attribute is TABLE-POPUP-MENU.
	   But it is not recommended to use this way as there is an equivalent method is present 
	   in AbstractBaseNmsPanel. The method is <i>getCurrentNodeProperties()</i>
	   
     *@param nmspanel the nmspanel which the table popup is to be found.
     *@return the string name of the popup menu.
     *@author N.Vidhya
     **/
    public String getTablePopupMenu(String nmsPanel)
    {
        XMLNode xmlnode = (XMLNode)xmlNodes.get(nmsPanel);
        return (String) xmlnode.getAttribute("TABLE-POPUP-MENU");//No Internationalisation//No Internationalisation
    }
    
    

    //This instance is returned for removing the selection
	AlertCounts  getAlertCountsInstance()
    {
        return(alCounts);
    }
    
    public void showFirstNmsPanel ()
    {
        if (firstNmsPanelToBeShown != null)
        {
            //Need to think something here... May be we shall do with
            //asking the user to enter the XMLNode-ID instead of the
            //TREE-NAME to avoid this!!
            //String firstNode = getID(firstNmsPanelToBeShown);
            String firstNode = firstNmsPanelToBeShown;
            if (setSelectedNode (firstNode))
            {
                changePanel (firstNode);
                selectedTreeNode = firstNmsPanelToBeShown;
            }
            else
            {
                String s = selectFirstLeafInTree ();
                selectedTreeNode = s;
                setSelectedNode (s);
                changePanel (s);
            }
        }
        String startupTheme =AdventNetThemeFileHandler.getInstance().getStartUpThemeDisplayName();
        if(startupTheme != null && ! startupTheme.equalsIgnoreCase("None"))
            updateAllComponentsUI();
        frame.setCursor (NmsClientUtil.normal_cursor);
    }

   	//New method is used for set the status text on the status bar //added by pitchaimani 25/07/2001	

	//Sathish changes for status bar foreground customization starts here
	//****************************************************************************
	
	public void  setTheStatusText(final String s, final Color color)
	{
		setStatusMessage(s,color,Color.white);
	}

	public void setTheStatusText(final String str,final Color background, final Color foreground)
	{
		setStatusMessage(str,background,foreground);
	}

	private void setStatusMessage(final String str, final Color background, final Color foreground)
	{
		Runnable run = new Runnable()
		{
			public void run()
			{
				if(statusLabel == null)
				{
					System.err.println(str);
					return;
				}
				statusLabel.setText(str);
				statusLabel.setBackground(background);
				statusLabel.setForeground(foreground);
			}
		};
		SwingUtilities.invokeLater(run);
	}

	/*public void  setTheStatusText(final String s, final Color color)
	{
		Runnable run = new Runnable()
		{
			public void run()
			{

				if( statusLabel == null )
				{
					System.err.println(s);
					return;
				}
				statusLabel.setText (s);
				statusLabel.setBackground (color);
			}
		};
		SwingUtilities.invokeLater(run);
	}*/

	//****************************************************************************
	//Sathish changes for Status Bar foreground customization ends here


    public synchronized	void setTheText (String s, Applet applet, String color)
    {
        statusLabel.setText (s);
        //moved the setOpaque() and setForeground() to the init method..
		try
		{
			int colorValue[] = getTheColor (color);
        //create the backColor using the RGB values..
			Color backColor = new Color (colorValue[0], 
                                     colorValue[1], 
                                     colorValue[2]);
        //for GC
			colorValue = null;
			statusLabel.setBackground (backColor);
		}
		catch(Exception e)
		{
			statusLabel.setBackground (new Color(0,128,0));
		}
    }
    
    private int[] getTheColor (String colorString)
    {
        StringTokenizer colors = new StringTokenizer(colorString,"-");//No Internationalisation//No Internationalisation
        int length = colors.countTokens();
        int colorValues[] = new int[length];
        for(int i = 0 ; i < length ; i++)
        {
            colorValues[i] = Integer.parseInt (colors.nextToken());
        }
        return colorValues;
    }
    
    /* This will remove old value for treename in keyTreeList and add new new entry.
       This is because to create multiple maps. While creating treelist, for all the map entries 
       it will take "MapApplet" as the key.We need to change the key as corresponding mapnames.//No Internationalisation//No Internationalisation
       So that while calling changePanel() for the first time, it will remove the old entry, and
       add the new entry with treename and corresponding mapname.
       This function can be from MapApplet for maps.
    */
    public void updateKeyTreeList (String treename, String mapname)
	{
            if (treename == null)
                return;
            keyTreeList.put (treename, mapname);
            //removed the unnecessary for() loop here.
	}
    
    /**Adds the NmsPanel instance to the panelList Hashtable*/
    
    // id is the field which is defined in DeviceData.xml.
    // If id and Tree-Name are different then this id plays a role. 
    public boolean addPanelToList (NmsPanel panel,String id)
    {
        return addPanelToList (panel, true,id,true,false);
    }
    public boolean addPanelToList(NmsPanel panel, boolean callInit,String id)
    {
        return addPanelToList (panel, callInit,id,false,true);
    }
    InternalFrameAction internalFrameAction = null;
    public boolean addPanelToList (NmsPanel panel, boolean callInit,String id,boolean callStart,boolean callRest)
    {
		boolean skipmenus = false;
		String panelKey = null;
        if (callInit)
        {
            panel.init (applet);
			if (panel instanceof AbstractBaseNmsPanel)
			{
				if (id != null)
				{	
					XMLNode temp = (XMLNode)xmlNodes.get(id);
					if (temp != null)
					{	
						panelKey = (String) temp.getAttribute("PANEL-KEY");//No Internationalisation
					}
					else
					{
						panelKey = id;
					}
				}
				String comparevalue = null;
				if (panelKey != null)
					comparevalue = "PANEL-KEY";//No Internationalisation
				String propStr = null;
				if (panelKey != null)
					propStr = getValue(panelKey,"all",comparevalue);//No Internationalisation
				else
					propStr = getValue(panel.getClass().getName(),"all",null);//No Internationalisation
				if (propStr == null) 
				{
					if (id != null)
					{
						XMLNode temp = (XMLNode)xmlNodes.get(id);
						Hashtable ht = new Hashtable();
						if (temp != null)
							ht = temp.getAttributeList();
						propStr = "";
						for (Enumeration e = ht.keys(); e.hasMoreElements();)
						{
							String key = (String)e.nextElement();
							String value1 = (String)ht.get(key);
							propStr =propStr + key + "=" + value1 + ":";//No Internationalisation
						}
					}
				}
				Properties prop = parseAndGive(propStr);
				((AbstractBaseNmsPanel)panel).init(prop);
			}
        }
        if (callStart)
        {
            //panel.start ();
            panelKey = panel.key ();
            if(panelKey == null)
            {
                System.err.println(NmsClientUtil.GetString("The method key() returns null for the panel with id ")+id);
                return false;
            }
            panelList.put (panelKey, panel);
            
        }
		if (id != null)
		{	
			XMLNode temp = (XMLNode)xmlNodes.get(id);
			if (temp != null)
			{	
			}
			else
			{
				skipmenus = true;
			}
		}
        if (callRest)
        {
			if (panel == null)
			{
				System.out.println(NmsClientUtil.GetString("No Panel registered for node ") + id);
				return false;
			}
            panelKey = panel.key ();
            if (!(panel instanceof MapApplet))
            {
                if ((id != null) && (!skipmenus))
                {	
                    createPanelMenuBar(panel,id);
                }
            }
            else
            {
                if (openMultipleMaps == false)
                {
                    createPanelMenuBar(panel,"Maps");//No Internationalisation
                }	
                
                panelList.put (panelKey, panel);
            }
            if (useInternalFrame)
			{
				if(internalFrameAction == null)
				{
					internalFrameAction = new InternalFrameAction ();
				}
				NmsInternalFrame currentInternalFrame = new NmsInternalFrame ("", true, true, true, true);//No Internationalisation//No Internationalisation
				//currentInternalFrame.setVisible (false);
				currentInternalFrame.setName (panel.key ());
				currentInternalFrame.getContentPane ().add ((JPanel) panel);




				currentInternalFrame.addInternalFrameListener (internalFrameAction);
				String action = getTheValue(id,"DEFAULT-CLOSE-OPERATION",null);//No Internationalisation
				if ( ("Maps".equals(id)) || (action == null) || (panel instanceof NmsCustomPanel))
					action = "HIDE_ON_CLOSE";//No Internationalisation
				String setAction = "HIDE_ON_CLOSE";//No Internationalisation
				int setActionValue = JInternalFrame.DO_NOTHING_ON_CLOSE;
				Vector validate = new Vector();
				validate.addElement("DO_NOTHING_ON_CLOSE");
				validate.addElement("HIDE_ON_CLOSE");
				validate.addElement("DISPOSE_ON_CLOSE");
				validate.addElement("DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE");
				validate.addElement("DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE");
				if (!validate.contains(action))
				{
					action = "HIDE_ON_CLOSE";
				}
			    if(action.equals("HIDE_ON_CLOSE"))//No Internationalisation
				{
					setAction = "HIDE_ON_CLOSE";//No Internationalisation
					setActionValue = JInternalFrame.HIDE_ON_CLOSE;
				}
				else if(action.equals("DISPOSE_ON_CLOSE")) //No Internationalisation
				{
					setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
					setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
				}
				else if (action.equals("DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE"))//No Internationalisation
				{
					setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
					setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
				}
				else if (action.equals("DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE"))//No Internationalisation
				{
					setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
					setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
				}
				currentInternalFrame.setDefaultCloseOperation (setActionValue);
				currentInternalFrame.setID(id);

				desktopPane.add (currentInternalFrame);
				frame.validate();

				int xy[] = getInternalFrameSizeAndLoc (panel.key ());
				if (xy != null && xy.length >= 4)
				{
					currentInternalFrame.setBounds (xy[0], xy[1], xy[2], xy[3]);
				}
				else
				{



                                    
                                    Rectangle p = currentInternalFrame.getParent().getBounds();
                                    boolean success = setNmsInternalFrameSize(p.width,p.height,currentInternalFrame);
                                    
                                    if(!success)
                                    {
                                        Dimension splitPane2Dimension = splitPane2.getPreferredSize();
                                        success = setNmsInternalFrameSize((int)splitPane2Dimension.getWidth(),(int)splitPane2Dimension.getHeight(),currentInternalFrame);
                                    }
                                    
                                    

                                    if(!success)
                                    {
                                        Dimension desktopPaneDimension = desktopPane.getPreferredSize();
                                        success = setNmsInternalFrameSize((int)desktopPaneDimension.getWidth(),(int)desktopPaneDimension.getHeight(),currentInternalFrame);
                                    }
                                    

                                    if(!success)
                                    {
                                        
                                        setNmsInternalFrameSize(800,600,currentInternalFrame);
                                    }
  


				}
				
				//to register nmsPanel itself as a listener to the internal frame
				if (panel instanceof javax.swing.event.InternalFrameListener)
				{
					currentInternalFrame.addInternalFrameListener((javax.swing.event.InternalFrameListener)panel);
				}
			}
            else
            {
                cardPanel.add (panelKey, (Component) panel);
            }
			if (!(createdPanels.contains(panelKey)))
            createdPanels.addElement(panelKey);
        }
        return true;
    }

  private boolean setNmsInternalFrameSize(int width , int height , NmsInternalFrame internalFrame)
    {
        if((width>0)&&(height>0))
        {
            internalFrame.setSize(new Dimension(width,height));
            return true;
        }
        return false;
        
    }
  
    
    private void removePanelFromList(String panelKey)
    {
		NmsPanel nmspanel = (NmsPanel)panelList.remove(panelKey);
		if (nmspanel != null)
		{
			if (((JPanel)nmspanel).getParent() != null)
			{
				((JPanel)nmspanel).getParent().remove((JPanel)nmspanel);
			}
			initializedList.remove(nmspanel.getClass().getName());
			createdPanels.remove(nmspanel.key());
		}
    }
    
    /*
     * Returns the NmsPanel corresponding to a specific XMLNode ID.
     */
    private NmsPanel getThePanelForNodeID(String nodeID)
    {
        if (nodeID == null)
            return null;
        String keyOfPanel = (String) keyTreeList.get (nodeID);
        if (keyOfPanel == null)
        {
            keyOfPanel = nodeID;
        }
        NmsPanel panel = (NmsPanel) panelList.get (keyOfPanel);
		
        return panel;
    }
    
    public void changePanel (String nodeID)
    {
        changePanel (nodeID, false);
    }
    
    public void changePanel (String nodeID, boolean doNotStoreState)
    {
        changePanel (nodeID, doNotStoreState, true);
    }
    
    //PENDING:1 
    
    /*
     * What do we do here ??
     * First find the corresponding NmsPanel for the nodeID, then based upon the
     * other args we set the panel properties and call the changePanel(nmsPanel,id)
     * method which takes care of any UI updation..
     */
    public void changePanel (String nodeID, boolean doNotStoreState, boolean callSetProperties)
    {
        InitializePanel(nodeID);
        NmsPanel panel = getThePanelForNodeID(nodeID);
        if (panel == null)
        {
            System.out.println (NmsClientUtil.GetString ("No Panel registered for node :") + nodeID);
            return;
        }
        NmsClientUtil.busyCursor(this);
        boolean alreadyOpened = isOpenedInternalFramesContains (nodeID);
        /*The condition is added for seProperties(), to be called for the first time only
          when using multiple maps with internal frames. */
        //if (!(useInternalFrame && openMultipleMaps && alreadyOpened == true))
        //{

		XMLNode tempXml = (XMLNode)xmlNodes.get(nodeID);
		if (tempXml != null)
		{
			if(! getAuthentication(tempXml))
			{
				showPrevInternalFrame(nodeID);
				return;
			}
		}
        if (callSetProperties)
		{
			Properties prop = new Properties ();
			String argument = nodeID;
			String param = null;

			//if the argument is Events or Alerts we shall first
			//get the refresh_criteria from the applet so that
			//corresponding actions can be performed in the setProperties
			//of the NmsCustomPanel.

			if (argument.equals("Events")) //No Internationalisation
			{
				//store the param as the value of the REFRESH_EVENTS_EACH_TIME
				param = applet.getParameter("REFRESH_EVENTS_EACH_TIME");//No Internationalisation//No Internationalisation
				argument = "All";//No Internationalisation//No Internationalisation
			}
			else if (argument.equals("Stats Admin")) //No Internationalisation
			{
                            //added for setting refresh parameter for Stats Admin Panel
                            param = applet.getParameter("REFRESH_STATS_EACH_TIME");//No Internationalisation//No Internationalisation
				argument = "All";//No Internationalisation//No Internationalisation
			}
			else if (argument.equals("Alerts")) //No Internationalisation
			{
				//store the param as the value of the REFRESH_ALERTS_EACH_TIME
				param = applet.getParameter("REFRESH_ALERTS_EACH_TIME");//No Internationalisation//No Internationalisation
				argument = "All";//No Internationalisation//No Internationalisation
			}
			else if (argument.equals("Network Database")) //No Internationalisation
			{
                                //added for setting refresh parameter for Network Dtatbase
                                param = applet.getParameter("REFRESH_NETWORK_DATABASE_EACH_TIME");//No Internationalisation//No Internationalisation
				argument = "All";//No Internationalisation//No Internationalisation
			}
			else if (argument.equals("Mib Manager")) //No Internationalisation
			{
				argument = "All";//No Internationalisation//No Internationalisation
			}
			else if (argument.equals("Maps")) //No Internationalisation
			{
				argument = "root";//No Internationalisation//No Internationalisation
			}
			if(argument.startsWith("Events"))//No Internationalisation
			{
				param = applet.getParameter("REFRESH_EVENTS_EACH_TIME");//No Internationalisation//No Internationalisation
			}
			else if(argument.startsWith("Alerts"))//No Internationalisation
			{
				param = applet.getParameter("REFRESH_ALERTS_EACH_TIME");//No Internationalisation//No Internationalisation
			}
			else if(argument.startsWith("Network"))//No Internationalisation
			{
				param = applet.getParameter("REFRESH_NETWORK_DATABASE_EACH_TIME");//No Internationalisation//No Internationalisation
			}
			else if(argument.startsWith("Stats"))//No Internationalisation
			{
				param = applet.getParameter("REFRESH_STATS_EACH_TIME");//No Internationalisation//No Internationalisation
			}


			//as we can't put a null value into a hashtable we check
			//here whether param is null before storing the value in
			//the hashtable.
			if(param != null)
			{
				prop.put("REFRESH_EACH_TIME",param);//No Internationalisation//No Internationalisation
			}
			prop.put ("Type", argument);//No Internationalisation//No Internationalisation
			//NmsClientUtil.panelBeingViewed = panel.key ();
			Properties p = getProps(nodeID);
			if (p != null)
			{
				for (Enumeration ee= p.keys();ee.hasMoreElements();)
				{
					String key = (String)ee.nextElement();
					String value = (String)p.get(key);
					if (prop.get(key) == null)
						prop.put(key,value);
				}
			}
			isExternalFrameShown = isExternalFramePresentForPanel(panel,nodeID);
			if(!isExternalFrameShown)
			{
				NmsInternalFrame nmsframe = getInternalFrameFromDesktopPane (panel.key ());
				if (nmsframe != null)
				{
					if (!nmsframe.isVisible())
					{
						panel.start();
					}
				}
			}

			if ( (panel instanceof AbstractBaseNmsPanel) && (tempXml != null))
			{			
				//Hashtable ht = giveCommonProp(panel,nodeID);
				Hashtable ht = (Hashtable)tempXml.getAttributeList().clone();
				((AbstractBaseNmsPanel)panel).setCurrentNodeProperties(ht);
			}	
			panel.setProperties (prop);
		}
		else
		{
			if ( (panel instanceof AbstractBaseNmsPanel) && (tempXml != null))
			{			
				//Hashtable ht = giveCommonProp(panel,nodeID);
				Hashtable ht = (Hashtable)tempXml.getAttributeList().clone();
				((AbstractBaseNmsPanel)panel).setCurrentNodeProperties(ht);
			}
		}
	
        //}
        if (!doNotStoreState)
        {
            storeState (nodeID);
        }
        /*
          For Multiple maps, panel.setProperties() will call setPanelProperties() of MapApplet, in which
		it creates new MapApplet for new mapfile and it will update the hashtables keyTreeList
		and panelList by calling functions of MainPanel from MapApplet itselt.
		Now, the next three statements will get the exact panel for the treename from the updated
		keyTreeList, panelList and that will be shown in InternalFrame.
		*/
		if (openMultipleMaps       && 
			alreadyOpened == false)
		{
			panel = getThePanelForNodeID (nodeID);
			if (panel == null)
			{
				System.out.println (NmsClientUtil.GetString ("No Panel registered for node :") + nodeID);
				NmsClientUtil.normalCursor(this);
				return;
			}
			NmsClientUtil.panelBeingViewed = panel.key ();
		}
		//removed the repeated code here!!
		//currentPanel = null;
		changePanel(panel,nodeID);
	}

	/**should be called whenever switching between the NmsPanels */
	//PENDING:5
	private String currentNodeId = null;
	public void changePanel (NmsPanel panel, String nodeID)
	{
		if (panel == null)
		{
			System.err.println (NmsClientUtil.GetString("No panel registered to handle this request"));
			return;
		}
		
		
		NmsClientUtil.panelBeingViewed = panel.key ();

		if ((currentPanel != null) && (nodeID != null))
		{
			if (nodeID.equals(currentNodeId))//if (currentPanel.equals(panel))
			{
                            if (!((panel instanceof NmsCustomPanel) || (panel instanceof MapApplet)))
                                NmsClientUtil.normalCursor(this);
                             return;
			}
		}
		if (currentPanel != null)
		{
			if(tbar != null)
				tbar.removeActionListener (currentPanel);
		}
		currentPanel = panel;
		currentNodeId = nodeID;
		updatePanelMenuBar (panel, nodeID);
		boolean change = true;
		if (useInternalFrame)
		{
			change = showInternalFrame (panel, nodeID);
		}
		else
		{
			showCardPanel (panel, nodeID);
		}
		//Hard coding things here. This is the way we 
		//handle it for Making this MapApplet stuffs
		//to get working. Need to strive hard to find
		//a way to remove this **special** handling of
		//MapApplet here.
		if (!change)
			setToolBar(panel);
		//NmsClientUtil.normalCursor(this);
                if (!((panel instanceof NmsCustomPanel) || (panel instanceof MapApplet)))
                    NmsClientUtil.normalCursor(this);
		updateWindowMenu();

	}

	/** Handle toolbar's "goback" and "goforward" functions*///No Internationalisation//No Internationalisation
	public void actionPerformed (ActionEvent e)
	{
		String command = e.getActionCommand().trim();
		if (command.equals("Back"))//No Internationalisation
		{
			
			int size = nmsPanelNames.size ();
			
			if (size  == 0)
			{
				return;
			}
			if (currentPanelIndex > (size-1))
			{
				currentPanelIndex = (size-1);
				return;
			}
			
                        //Fix - If back menu item is clicked when there is no panel to be shown then currentPanelIndex was assigned to -1 so by which "Forward" tool bar button did't work
                        if(currentPanelIndex <= 0)
                        {
                            System.out.println(NmsClientUtil.GetString("No panel is present to show "));
                            return;
                        }

			String prevpanel = null;
			while (true)
			{
				currentPanelIndex  = currentPanelIndex - 1;
				if (currentPanelIndex < 0)
				{
					break;
				}
				prevpanel = (String) nmsPanelNames.elementAt (currentPanelIndex);
				
				if (useInternalFrame)
				{	
					if (isInternalFrameOpenedForTreeNode(prevpanel))
						break;
				}
				else
				{
					if (openedCardPanelNames.contains(prevpanel))
					{
						break;
					}
				}
			}
			if (prevpanel == null)
			{
				System.out.println(NmsClientUtil.GetString("No panel is present to show "));
				return;
			}
			changePanel (prevpanel, true);
			selectedTreeNode = prevpanel;
			setSelectedNode (prevpanel, true);
			return;
		}
		else if(command.equals("htmlui"))//No Internationalisation//No Internationalisation
		{
			
			try
			{





			   String action_value ="/jsp/index.jsp;jsessionid="+NmsClientUtil.applet.getParameter("jsessionid")+"?htmlui=htmlui&Language="+NmsClientUtil.language+"&Country="+NmsClientUtil.country; //No Internationalisation//No Internationalisation

				NmsClientUtil.showURLInNW((Component)e.getSource(),action_value);
			}
			catch (Exception ex)
			{
				System.err.println (NmsClientUtil.GetString("Error while opening document."));
				ex.printStackTrace ();
			}
		}
		else if (command.equals("Forward"))//No Internationalisation//No Internationalisation
		{
			
			int size = nmsPanelNames.size ();
			if (size == 0)
			{
				return;
			}
			if (currentPanelIndex < 0)
			{
				currentPanelIndex = -1;
				return;
			}

                        //Fix - If forward menu item is clicked when there is no panel to be shown then currentPanelIndex was assigned to be > (size-1)  so by which "BackWard" tool bar button did't work
                        if(currentPanelIndex >= (size-1))
                        {
                            System.out.println(NmsClientUtil.GetString("No panel is present to show "));
                            return;
                        }

			String prevpanel = null;
			while (true)
			{
				currentPanelIndex = currentPanelIndex + 1;
				if (currentPanelIndex > (size-1))
				{
					break;
				}
				prevpanel = (String) nmsPanelNames.elementAt (currentPanelIndex);
				if (useInternalFrame)
				{
					if (isInternalFrameOpenedForTreeNode(prevpanel)) 
						break;
				}
				else
				{
					if (openedCardPanelNames.contains(prevpanel))
						break;
				}
			}
			if (prevpanel == null)
			{
				System.out.println(NmsClientUtil.GetString("No panel is present to show "));
				return;
			}
			changePanel (prevpanel, true);
			selectedTreeNode = prevpanel;
			setSelectedNode (prevpanel, true);
			return;
		}
		else if (command.equals("Close"))//No Internationalisation//No Internationalisation
		{
			int option =JOptionPane.showConfirmDialog(this,NmsClientUtil.GetString("Do you really want to exit the client?"),NmsClientUtil.GetString("Confirmation Message"),JOptionPane.YES_NO_OPTION);
			if(option != JOptionPane.YES_OPTION) 
				return;
                        shutDown(null);
		}
		else if (command.equals("SystemAdmin"))//No Internationalisation//No Internationalisation
		{
			String action_value ="../jsp/index.jsp;jsessionid="+NmsClientUtil.applet.getParameter("jsessionid")+"?fromAppClient=../jsp/SysAdminFrontPage.jsp&Language="+NmsClientUtil.language+"&Country="+NmsClientUtil.country; //No Internationalisation	//No Internationalisation
		// String action_value ="../jsp/index.jsp?fromAppClient=../jsp/SysAdminFrontPage.jsp&Language="+NmsClientUtil.language+"&Country="+NmsClientUtil.country; //No Internationalisation//No Internationalisation
			NmsClientUtil.showURLInNW((Component)e.getSource(),action_value);

		}






















		//added for Window Menu 
		else if (command.equals("SaveLS"))//No Internationalisation//No Internationalisation
		{
			if (useInternalFrame)
			{
				saveSizeAndLocationOfInternalFrames ();
			}
		}
		else if (command.equals("Close All"))//No Internationalisation//No Internationalisation
		{
			if (useInternalFrame)
			{
				removeAllInternalFrames ();
				setDefaultTreeNodeAndMenuBar ();
			}
		}
		else if (command.equals("CloseExternalFrame"))//No Internationalisation//No Internationalisation
		{
			JMenuItem menuitem = (JMenuItem) e.getSource ();
			String exFrameName = menuitem.getName ();
			JFrame exFrame = getExternalFrameForName (exFrameName);
			removeExternalFrame (exFrame,false,true);
		}
		else if (command.equals("Detach"))//No Internationalisation//No Internationalisation

		{
			if (useInternalFrame)
			{
				NmsInternalFrame iframe = getCurrentInternalFrame ();

				if(iframe == null)
				{
					//this should be removed after disabling the detach button when there is no internal frame
					NmsClientUtil.showError(NmsClientUtil.GetString("There is no internal frame present that can be detached ."));
					return;
				}	
				String key = iframe.getName();
				detachInternalFrame (key);
			}
			else
			{
				detachCurrentCardPanel ();
			}
		}
		else if (command.equals("Cascade"))//No Internationalisation//No Internationalisation
		{
			
			arrangeInternalFrames ("Cascade");//No Internationalisation//No Internationalisation
		}
		else if (command.equals("TileH"))//No Internationalisation//No Internationalisation
		{
			arrangeInternalFrames ("TileH");//No Internationalisation//No Internationalisation
		}
		else if (command.equals("TileV"))//No Internationalisation//No Internationalisation
		{
			arrangeInternalFrames ("TileV");//No Internationalisation//No Internationalisation
		}
		else if (command.equalsIgnoreCase("stop"))//No Internationalisation//No Internationalisation
		{
			
                    NmsClientUtil.normalCursor (this);
		}
		else if (command.equalsIgnoreCase("Mainhelp"))//No Internationalisation//No Internationalisation
		{
			//NmsClientUtil.showHelp("../help/index.html");//No Internationalisation
			NmsClientUtil.showHelpBasedOnKey("Main_Help");//No Internationalisation

		}
		else if(command.equalsIgnoreCase("Cell Editing"))//No Internationalisation
		{
			
			//This action takes care of renaming the tree nodes on double click.
			//author N.Vidhya.
			
			XMLNode xmlnode = treeCellEditor.getNode();
			String nodeID   = (String) xmlnode.getAttribute ("ID");//No Internationalisation
		//	String oldDisplayName = (String)NmsCustomPanel.typeVsName.get(nodeID);

			//validation for presence of dot in the name. We cant allow it presently.
			String treeCellEditorValue =  ((String)treeCellEditor.getCellEditorValue()).trim();
			//if nothing has been typed we return. ie the old name exists.
			
			if((treeCellEditorValue == null) ||
			   (treeCellEditorValue.length()== 0))//No Internationalisation
			{
				tree.stopEditing();
				return;
			}
			//if the one edited and the one selected are diff then dont rename!
			//XMLNode selectedNode = (XMLNode)tree.getLastSelectedPathComponent ();
			//NmsPanel panel = getCurrentNmsPanel();
			renameTheNode(nodeID,treeCellEditorValue);//xmlnode,selectedNode,panel);
		}     
		if(command.equalsIgnoreCase("Show Toolbar"))//No Internationalisation
		{
			if(((JCheckBoxMenuItem)e.getSource()).isSelected())
			{
				removetbar.setSelected(true);
				removetbar.setActionCommand("HideToolbar");//No Internationalisation
				removetbar.setPreferredSize(new Dimension(9,40));
				removetbar.setIcon(toremove);
				removetbar.setToolTipText(NmsClientUtil.GetString("Hide Toolbar"));
				toolbarPanel.add(tbar,BorderLayout.CENTER);
				toolbarPanel.add(logoLabel,BorderLayout.EAST);
				toolbarPanel.repaint();
			}
			else
			{
				removetbar.setSelected(false);
				removetbar.setActionCommand("ShowToolbar");//No Internationalisation
				removetbar.setPreferredSize(new Dimension(60,8));
				removetbar.setIcon(afterremoved);
				removetbar.setToolTipText(NmsClientUtil.GetString("Show Toolbar"));
				toolbarPanel.remove(tbar);
				toolbarPanel.remove(logoLabel);
				toolbarPanel.repaint();
			}
		}
		else if (command.equalsIgnoreCase("CloseFrame"))//No Internationalisation
		{
			Properties addhash = new Properties();
			addhash.put("PANEL_NAME",currentNodeId);//No Internationalisation
			Event event = new Event (this, NmsPanel.CLOSE_PANEL_EVENT, addhash);
			NmsPanelEvent nmsevt = new NmsPanelEvent (event);
			handleNmsPanelEvent(nmsevt);
		}












		
	}
	
	void updateAllChildren(XMLNode root)
	{
		Vector child  = root.getChildNodes();
		int childSize = child.size();

		if (childSize == 0) 
		{
			//if we have no children here we need not proceed further..
			return;
		}

		String rootId = (String) root.getAttribute("ID");//No Internationalisation
		String 	rootvalue = (String)root.getAttribute("TREE-NAME");//No Internationalisation
		if( NmsCustomPanel.typeVsName.contains(rootId))
		{
			NmsCustomPanel.typeVsName.put(rootId,rootvalue);
		}

		for (int i = 0;i < childSize;i++)
		{	
			XMLNode node  = (XMLNode)child.elementAt(i);
			String key    = (String)node.getAttribute("ID");//No Internationalisation
			String value  = (String)node.getAttribute("TREE-NAME");//No Internationalisation
			String oldValue = (String)NmsCustomPanel.typeVsName.get(key);

			if( oldValue == null)
				continue;

			String newvalue = rootvalue + "." + value;//No Internationalisation
			NmsCustomPanel.typeVsName.put(key,newvalue);

			if(useInternalFrame)
			{
				//change in NmsInternalframe

				//adding to the window menu item (ie ) change in openedInternalFrames
				for (Enumeration framesInfo = openedInternalFrames.elements (); framesInfo.hasMoreElements ();)
				{
					Object info[] = (Object[])framesInfo.nextElement ();
					String wname = (String) info[0];
					if (wname != null && wname.equals (oldValue))
					{
						info[0]= newvalue;
						//						info[3] = newkey;
						//break;
					}
				}
			}	
			updateAllChildren(node);
		}
		updateWindowMenu();
	}

	private void changeTreeNodeProperties(Hashtable hasht, Object source)
	{
		try
		{
			String tobeselected = (String) hasht.remove ("tobeselected"); //No Internationalisation//No Internationalisation
			String id = (String) hasht.remove("ID");//No Internationalisation
			XMLNode topmost = (XMLNode) currentComponents[0];
			XMLNode childNode = checkIfBelongs (topmost, id);
			boolean update = false;
			if (id == null)
			{
				System.out.println(NmsClientUtil.GetString(" The ID field is null"));
				return;
			}
			if (childNode == null)
			{
				System.out.println(NmsClientUtil.GetString(" The specified ID is not present"));
				return;
			}

			//Changes for Tree Node disabling
			if ( ! getAuthentication( childNode))
				return;
			
			Hashtable oldProps = (Hashtable)childNode.getAttributeList();
			String overWrite = (String)hasht.remove("<UPDATE_ONLY>");//No Internationalisation
			if (overWrite == null)
				overWrite = "true";//No Internationalisation
			if (overWrite.equals("false"))//No Internationalisation
			{
				for (Enumeration e = oldProps.keys(); e.hasMoreElements();)
				{
                                    String key = (String)e.nextElement();
                                    if(key.equals("ID") || key.equals("PANEL-NAME") || key.equals("PANEL-KEY")
                                       || key.equals("TREE-NAME"))
                                    {
                                        continue;
                                    }
                                    oldProps.remove(key);
				}
			}
			String panelkey = (String)keyTreeList.get(id);
			if (panelkey == null)
				return;
			NmsPanel panel = (NmsPanel) panelList.get (panelkey);
			
			if (childNode != null)
			{
				int index = -1;	
				String treeName =(String)hasht.remove("TREE-NAME");//No Internationalisation
				for(Enumeration keys = hasht.keys(); keys.hasMoreElements();)
				{
					String key =(String)keys.nextElement();
					String val = (String) hasht.get(key);
					if( key != null && val != null)
					    {
						if(key.trim().equals("NODE-INDEX") )//No Internationalisation
						    {
							try
							    {
								index = Integer.parseInt(val);
							    }
							catch(Exception er){}
						    }
						else
						    {
							childNode.setAttribute (key,val); 
							if(key.trim().equals("ICON-FILE"))//No Internationalisation
							    {
								treeCellRenderer.addTreeIcon (val);
							    }
						    }
					    }
				}
				if (panel == null)
					return;
				xmlNodes.put(id,childNode);
				if (panel != null && panel instanceof AbstractBaseNmsPanel)
				{
					//String currentID = (String)((AbstractBaseNmsPanel)panel).getCurrentNodeProperties().get("ID");//No Internationalisation
					//if (currentID == null)   
						//return;     // Here it is not handled properly. Need to handle it properly.
					if (currentNodeId != null && currentNodeId.equals(id))
						update = true;
				}
				if( treeName != null)	
				{
					String prevTreename = (String)childNode.getAttribute ("TREE-NAME");//No Internationalisation
					childNode.setAttribute ("TREE-NAME", treeName);//No Internationalisation
					if (!prevTreename.equals(treeName))
					updateAllChildren(childNode);
					String panelKey =(String)keyTreeList.get(id);
					if(panelKey != null)
					{
						NmsPanel xpanel= (NmsPanel) panelList.get (panelKey);
						if(xpanel != null && xpanel instanceof AbstractBaseNmsPanel )
						{
							XMLNode tempXml = (XMLNode)xmlNodes.get(id);
							if ((tempXml != null) && update)
							{
								Hashtable ht = (Hashtable)tempXml.getAttributeList().clone();
								((AbstractBaseNmsPanel)xpanel).setCurrentNodeProperties(ht);
							}
							if (update)
								updatePanelMenuBar(xpanel,id);
						}
					}				
				}	
				String panelKey = (String)keyTreeList.get(id);
				if (panelKey == null)
				{
					return;
				}
				if (update)
				{
				if(useInternalFrame)
				{
					JFrame exFrame = getExternalFrameForName (panelKey);
					if (exFrame != null)
					{
						String title =(String)childNode.getAttribute("FRAME-TITLE");//No Internationalisation
						if(title == null || title.trim().equals("") )//No Internationalisation
							title = (String)childNode.getAttribute("TREE-NAME");//No Internationalisation;
						
						if( ! (panel instanceof WindowListener) )
						{
							exFrame.setTitle(NmsClientUtil.GetString(title));
						}
						String imageName = applet.getDocumentBase()+ "../images/redDot.png";//No Internationalisation
						Image image = NmsClientUtil.getImage(imageName);
						if (xmlNodes.containsKey(id))
						{
							imageName = (String)((XMLNode)xmlNodes.get(id)).getAttribute("ICON-FILE");//No Internationalisation
							imageName = applet.getDocumentBase() + "../" + imageName;//No Internationalisation
						}
						if (NmsClientUtil.getImage(imageName) != null)
							image = NmsClientUtil.getImage(imageName);
						if  ( (image != null) && (keyTreeList.containsKey(id)) && update)
						{
							exFrame.setIconImage(image);
						}
						exFrame.show ();
					}
					else
					{
						NmsInternalFrame iframe = getInternalFrameFromDesktopPane (panelKey);
						if (iframe != null)
						{
							String title =(String)childNode.getAttribute("FRAME-TITLE");//No Internationalisation
							if(title == null || title.trim().equals("") )//No Internationalisation
							{
								title = (String)childNode.getAttribute("TREE-NAME");
							}
							if( !(panel instanceof InternalFrameListener) )	
							{
								iframe.setTitle(title);
							}
							String action= (String)childNode.getAttribute("DEFAULT-CLOSE-OPERATION");
							if ( ("Maps".equals(id)) || action == null || panel instanceof NmsCustomPanel)
								action = "HIDE_ON_CLOSE";//No Internationalisation
							Vector validate = new Vector();
							validate.addElement("DO_NOTHING_ON_CLOSE");
							validate.addElement("HIDE_ON_CLOSE");
							validate.addElement("DISPOSE_ON_CLOSE");
							validate.addElement("DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE");
							validate.addElement("DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE");
							if (!validate.contains(action))
							{
								action = "HIDE_ON_CLOSE";
							}
							String setAction = "DO_NOTHING_ON_CLOSE";//No Internationalisation
							int setActionValue = JInternalFrame.DO_NOTHING_ON_CLOSE;
							if(action.equals("HIDE_ON_CLOSE"))//No Internationalisation
							{
								setAction = "HIDE_ON_CLOSE";//No Internationalisation
								setActionValue = JInternalFrame.HIDE_ON_CLOSE;
							}
							else if(action.equals("DISPOSE_ON_CLOSE")) //No Internationalisation
							{
								setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
								setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
							}
							else if (action.equals("DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE"))//No Internationalisation
							{
								setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
								setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
							}
							else if (action.equals("DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE"))//No Internationalisation
							{
								setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
								setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
							}
							iframe.setDefaultCloseOperation (setActionValue);
						
							updateOpenedInternalFrameWithDisplayName(getSelectedPath(),id);
							updateWindowMenu();
							String imageName = null;
							if (xmlNodes.containsKey(id))
							{
								imageName = (String)((XMLNode)xmlNodes.get(id)).getAttribute("ICON-FILE");//No Internationalisation
								imageName = applet.getDocumentBase() + "../" + imageName;//No Internationalisation
							}
							ImageIcon imgLeaf =null;
							if( imageName != null && !(imageName.trim().equals("")) )
							{
								imgLeaf = getScalledImage(NmsClientUtil.getImageIcon(imageName));
							}
							if( imgLeaf == null )
							{
								imgLeaf = new ImageIcon(NmsClientUtil.getImage(applet.getDocumentBase()+
											"../images/redDot"+NmsClientUtil.getImageType()));//No Internationalisation
							}

							if (keyTreeList.containsKey(id))
								iframe.setFrameIcon(imgLeaf);
						}
					}
				}
				}	
				if (treeModel != null)
				{
					
					XMLNode parentNode = childNode.getParentNode();

                XMLNode[] tempComponents = treeModel.getPathToRoot(parentNode);
                TreePath currentTreePath = new TreePath (tempComponents);
                Enumeration expPth = tree.getExpandedDescendants(currentTreePath);

					if( index > -1 && index < parentNode.getChildCount())
					{
						Vector children = parentNode.getChildNodes();
						if( children.indexOf(childNode) != index )
						{
							children.remove(childNode);
							children.insertElementAt(childNode,index);
							parentNode.setChildNodes(children);
							ignoreValueChanged = true;
							treeModel.reload (parentNode);
							ignoreValueChanged = false;
						}
						else
						{
							ignoreValueChanged = true;
							treeModel.reload (childNode);
							ignoreValueChanged = false;
						}
					}
					else
					{
						ignoreValueChanged = true;
						treeModel.reload (childNode);
						ignoreValueChanged = false;
					}	
                                        if (expPth != null)
                                        {       
                                          while (expPth.hasMoreElements()) 
                                          {     
                                              tree.expandPath((TreePath)expPth.nextElement());
                                          }
                                        }
				}
				
				if (tobeselected != null)
				{
					selectedTreeNode = tobeselected;
					setSelectedNode (tobeselected, false);
				}
				else
				{
					setSelectedNode (selectedTreeNode, true);
				}
			}
			else
			{
				System.err.println (NmsClientUtil.GetString("No Tree Node - ") + id + NmsClientUtil.GetString(" found.Cannot modify- tree node properties"));
				return;
			}
		}
		catch (Exception ee)
		{
			System.err.println (NmsClientUtil.GetString("Exception occured while  modifying Tree node properties : ") + ee);
			ee.printStackTrace ();
		}
		return;
	}


	//PENDING:6
	/** 
		This method handles all the NmsPanelEvents. users can invoke this method for firing events.
		But this way of invoking is not recommended as fireNmsPanelEvent is present in NmsPanel itself 
	*/

    public synchronized void handleNmsPanelEvent (NmsPanelEvent e)
    {
        if(isTreeConstructed)
            {
                tempHandleNmsPanelEvent(e);
            }
        else
            {
                eventDispatchQueue.addElement(e);
            }
    }

    

    void tempHandleNmsPanelEvent(NmsPanelEvent e)
    { 
		if (e.getID() == NmsPanel.EXECUTE_ACTION)
		{
			Hashtable ht = (Hashtable)e.getArgument();
			//String panelkey = (String) ht.get("panelkey");//No Internationalisation
			String action  = (String) ht.get("action");//No Internationalisation
			ActionEvent ea = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,action);
			actionPerformed (ea);
		}
		if (e.getID() ==200)
		{
			Hashtable ht = (Hashtable)e.getArgument();
			Object panel = ht.get("nmspanel");
			if (panel instanceof NmsPanel)
			{
				NmsPanel nmspanel = (NmsPanel)panel;
				addPanelToList (nmspanel,true,nmspanel.key (),true,true);
			}
			
		}
		if (e.getID() == 100)
		{
			final NmsPanelEvent tempEvent = e;
			Runnable run = new Runnable()
			{
			public void run()
			{
				Hashtable ht = (Hashtable)tempEvent.getArgument();
				Vector v = (Vector)ht.get("menus");//No Internationalisation
				String panelKey = (String)ht.get("panelkey");//No Internationalisation
				JFrame exFrame = getExternalFrameForName(panelKey);
				JFrame parentFrame = null;
				if (exFrame != null)
				{
					parentFrame = exFrame;
				}
				else
				{	
					if ((currentPanel != null) && !(currentPanel.key().equals(panelKey)) || (currentPanel == null))
						return;
					parentFrame = NmsClientUtil.getFrame(MainPanel.this);
				}
				if (parentFrame != null)
				{
					Vector menus = returnmenusFromBegining();
					for (int i = menus.size() - 1; i >= 0 ; i--)
					{
						JMenu menu = (JMenu)menus.elementAt(i);
						if (exFrame != null)
							v.remove(menu);
						else
							v.insertElementAt(menu,0);	
					}
					menus = returnmenusFromEnd();
					for (int i = 0; i < menus.size(); i++)
					{
						JMenu menu = (JMenu)menus.elementAt(i);
						if (exFrame != null)
							v.remove(menu);
						else
							v.addElement(menu);	
					}
					if(v.size() > 0)
					{
						JMenuBar menubar = parentFrame.getJMenuBar();
						if(menubar == null)
						{
							menubar = new JMenuBar();
						}
						else
						{
							menubar.removeAll();
						}
						for (int i = 0; i < v.size(); i++)
						{
							menubar.add((JMenu)v.elementAt(i));
						}
						setJMenuBar (parentFrame,menubar);
						parentFrame.validate ();
					}
				}
				//SwingUtilities.updateComponentTreeUI (menubar);
				//return;
			}
			};
                        SwingUtilities.invokeLater(run);
                        return;
		}
		
		XMLNode treeNode = currentNode;
		if (e.getID () == NmsPanel.MODIFY_TREE_LABEL)
		{
			try
			{
				Hashtable hasht = (Hashtable) e.getArgument ();
				if (hasht == null)
				{
					System.err.println (NmsClientUtil.GetString("Argument for modify tree label is null"));
					return;
				}
				String id = (String) hasht.remove ("id");//No Internationalisation
				String label = (String) hasht.remove ("label");//No Internationalisation

				//panel key ane parent  properties are meaningless for this event 
				hasht.remove("panelkey");//No Internationalisation
				hasht.remove("parent");//No Internationalisation
				
				if(id != null)
				{
					hasht.put("ID",id);//No Internationalisation
				}
				else
				{
					System.err.println (NmsClientUtil.GetString("No panel - ") + id + NmsClientUtil.GetString(" found.Cannot  modify tree label "));//No Internationalisation
					return;

				}
				if(label != null )
				{
					hasht.put("TREE-NAME",label);//No Internationalisation
				}
				changeTreeNodeProperties(hasht,null);
			}
			catch (Exception ee)
			{
				System.err.println (NmsClientUtil.GetString("Exception modifying Tree label : ") + ee);
				ee.printStackTrace ();
			}
			return;
		}
		//mohan
		if (e.getID () == NmsPanel.MODIFY_TREE_ICON)
		{
			try
			{
				Hashtable hasht = (Hashtable) e.getArgument ();
				if (hasht == null)
				{
					return;
				}
				String id = (String)hasht.remove ("id");//No Internationalisation//No Internationalisation
				String newIcon = (String)hasht.remove ("newLeafIconFile");//No Internationalisation//No Internationalisation
				//parent property is not needed for this event
				hasht.remove("parent");//No Internationalisation

				if(id != null)
				{
					hasht.put("ID",id);//No Internationalisation
				}
				else
				{
					System.err.println (NmsClientUtil.GetString("No panel - ") + id + NmsClientUtil.GetString(" found.Cannot  modify tree icon "));//No Internationalisation
					return;
				}
				if(newIcon != null )
				{
					hasht.put("ICON-FILE",newIcon);//No Internationalisation
				}
				changeTreeNodeProperties(hasht,null);

			}
			catch (Exception excep)
			{
				System.err.println (NmsClientUtil.GetString("Exception modifying Tree icon File Name : ") + excep);
				excep.printStackTrace ();
			}
			return;
		}
		if (e.getID () == NmsPanel.ADD_LEAF_TO_MENU)
        {
			try
          {
				Hashtable hasht = (Hashtable) e.getArgument ();
				if (hasht == null)
                                    {
					System.err.println (NmsClientUtil.GetString("No new Element specified"));
					return;
                                    }
				Vector newleafs = (Vector) hasht.remove ("newleafs");//No Internationalisation//No Internationalisation
				Vector iconFiles = (Vector) hasht.remove ("newleafsIconFiles");//No Internationalisation//No Internationalisation
				Vector treeNames = (Vector) hasht.remove ("newleafsName");//No Internationalisation//No Internationalisation
				String tobeselected = (String) hasht.remove ("tobeselected");//No Internationalisation//No Internationalisation
				String panelkey = (String) hasht.get ("panelkey");//No Internationalisation//No Internationalisation
				String parent = (String) hasht.get ("parent");//No Internationalisation//No Internationalisation
				Integer indexVal = (Integer) hasht.remove ("index"); //No Internationalisation//No Internationalisation











                                
                                String addNode = (String) hasht.get("addNode");
                                

				String panelName = (String)hasht.get("PANEL-NAME");//No Internationalisation

				XMLNode trueXMLNode = (XMLNode)hasht.get("source");//No Internationalisation
				String ignoreValue = (String)hasht.get("ignoreValueChanged");//No Internationalisation
				// Properties of xml node
				Properties attributeList =(Properties)hasht.get("attributeList");//No Internationalisation
				if (attributeList == null)
                                    attributeList = new Properties();
				boolean passval = (ignoreValue == "true");//No Internationalisation
				int index = -1;
				if (indexVal != null)
                                    {
					index = indexVal.intValue();
                                    }
				else if( attributeList != null)
                                    {
					String nodeIndex =(String)attributeList.get("NODE-INDEX");//No Internationalisation
					if(nodeIndex != null)
                                            {
						try
                                                    {
							index = Integer.parseInt(nodeIndex);
                                                    }
						catch(Exception er){}
                                            }

                                    }

				String id = null;
                                boolean nodeTobeAdded = true;
                                if ((addNode!= null) && (addNode.equals("false")))

                                    nodeTobeAdded = false;                      
                                    
                                    if (!nodeTobeAdded)
                                    return;
                                    

				if (parent == null)
                                    {
					System.err.println (NmsClientUtil.GetString("Cannot add ")+(String) newleafs.elementAt (0)+NmsClientUtil.GetString(" to tree. No parent name specified " ) );
					return;
                                    }
				XMLNode topmost = (XMLNode) currentComponents[0];
				XMLNode parentNode = checkIfBelongs (topmost, parent);
				if (parentNode == null)
                                    {
					System.err.println (NmsClientUtil.GetString("Cannot add ")+(String) newleafs.elementAt (0)+ NmsClientUtil.GetString(" to tree. No parent matching with "+  parent + NmsClientUtil.GetString("in add Event") ));
					return;
                                    }
				//Changes for Tree Node disabling
				if(! getAuthentication(parentNode))
                                    return;
			
				XMLNode childNode = null;
				for (int i = 0; i < newleafs.size (); i++)
                                    {	
					String temp = (String) newleafs.elementAt (i);
					childNode = new XMLNode ();
					childNode.setNodeName(temp);

					String treename = null;
					Object source = e.getSource();

					if( treeNames != null && treeNames.size()> i)
                                            {
						treename =(String)treeNames.elementAt(i) ;
                                            }
                                        
                                        Vector childs = parentNode.getChildNodes();
                                        for (int ii = 0 ; ii < childs.size(); ii ++)
                                            {
                                                XMLNode child = (XMLNode)childs.elementAt(ii);
                                                if (treename.equals(child.getAttribute("TREE-NAME")))//No Internationalisation
                                                    {
                                                        //                                                        System.out.println(NmsClientUtil.GetString(" In the same level , the node can not be added") + parentNode.getAttribute("ID") + "***************" +  parentNode.getAttribute("TREE-NAME") + " treeName = " + treename);
													String childNodeId = (String)child.getAttribute("ID");	
														
												    if (!temp.equals(childNodeId))
													{
                                                        System.out.println(NmsClientUtil.GetString(" In the same level , the node can not be added"));
													}
                                                        return;
                                                    }
                                            }
                                        /*

							if(source instanceof NmsCustomPanel) //No Internationalisation
							{
							String abcd = (String)((NmsCustomPanel)e.getSource()).typeVsName.get(temp);
							if(abcd!=null) 
							{
							abcd = abcd.substring(abcd.lastIndexOf(".") + 1);//No Internationalisation//No Internationalisation
							treename = abcd;
							}	
							}
							else if (source instanceof MapApplet)
							{
							treename = ((MapApplet)source).getLabelForMap(temp);
							}
							else if(NmsCustomPanel.typeVsName.get(temp) != null)
							treename = NmsCustomPanel.typeVsName.get(temp).toString();*/

					Hashtable attlist = new Hashtable();
					id = temp;
					attlist.put("ID",temp);//No Internationalisation//No Internationalisation

					if(panelName == null)
                                            {	
						if( attributeList != null)
                                                    panelName =(String) attributeList.get("PANEL-NAME");//No Internationalisation

						/*if(panelName == null )
						  panelName =	(String) (parentNode.getAttribute ("PANEL-NAME"));*///No Internationalisation
                                            }

					if (panelName != null)
                                            attlist.put("PANEL-NAME",panelName);//No Internationalisation

					//xmlNodes.put(temp,childNode);

					if(attributeList != null && attributeList.size() > 0)
                                            {
						//Id should be removed from this attribute list
						attributeList.remove("ID");//No Internationalisation

						for (Enumeration keys = attributeList.keys();keys.hasMoreElements();)
                                                    {
							String key =(String) keys.nextElement();

							String val =(String)attributeList.get(key);
							if( ! val.trim().equals(""))//No Internationalisation
                                                            {
								if(key.equals("ICON-FILE"))//No Internationalisation
                                                                    {
									treeCellRenderer.addTreeIcon (val);
                                                                    }
								attlist.put(key,val);
                                                            }
                                                    }	
                                            }
					else
                                            {
						for (Enumeration keys = hasht.keys();keys.hasMoreElements();)
                                                    {
							String key =(String) keys.nextElement();

							String val =(String)hasht.get(key);
							if(key.equals("ICON-FILE"))//No Internationalisation
                                                            {
								treeCellRenderer.addTreeIcon (val);
                                                            }
							attlist.put(key,val);
						}
					}

					if(treename != null)
					{
						attlist.put ("TREE-NAME",treename);//No Internationalisation
					}

					String nodeName =(String)attlist.get ("TREE-NAME");//No Internationalisation

					if(nodeName ==null ||(nodeName.trim().equals("")))  //No Internationalisation
					{
						//This should be removed.
						attlist.put ("TREE-NAME", temp);//No Internationalisation//No Internationalisation
						nodeName = temp;
					}

					NmsCustomPanel.typeVsName.put(temp,nodeName);
					if ((attlist.get ("ICON-FILE") == null)&& iconFiles != null)//No Internationalisation
					{
						String iconFileName = (String) iconFiles.elementAt (i);
						if (iconFileName != null)//No Internationalisation
						{
							attlist.put ("ICON-FILE", iconFileName);//No Internationalisation//No Internationalisation
							treeCellRenderer.addTreeIcon (iconFileName);
						}
					}
					childNode.setAttributeList (attlist);
					Object deviceRef = (Object) parentNode.getAttribute ("DEVICE-REF");//No Internationalisation
					if(deviceRef != null)
					{
						childNode.setAttribute ("DEVICE-REF", deviceRef);//No Internationalisation//No Internationalisation
					}
					xmlNodes.put(temp,childNode);
                                        //if (ignore)
					//{	
						//MapProblemDueToChangesOnServer
						if( index < 0 || index >= parentNode.getChildCount())
						{
							parentNode.addChildNode(childNode);
						}
						else
						{
							parentNode.insertChildNode(childNode,index);
						}
						// If the instance is not created , create it
						if (panelkey == null || panelList.get(panelkey) == null)
						{
							initXMLNodeParms(childNode);
							panelkey = (String)keyTreeList.get(temp);
						}
						//ignore = true;
                                                //}
                                                //else
                                                //{
						//if (trueXMLNode != null)
						//	trueXMLNode.setAttributeList (attlist);
						//ignore = true;
                                                //}
					//parentNode.addChildNode (childNode);
					if( panelkey != null)
					{
						keyTreeList.put (temp, panelkey);
					}
				}

				createAndAddXMLNodes(parentNode,childNode, panelkey, tobeselected, id,passval);
				ignoreValueChanged = false;
				if(( panelkey != null) && (tobeselected != null))
				{
					NmsPanel xpanel= (NmsPanel) panelList.get (panelkey);
					if(xpanel != null && xpanel instanceof AbstractBaseNmsPanel && (!(xpanel instanceof MapApplet)))
					{
						//Hashtable ht = giveCommonProp(xpanel,id);
						XMLNode tempXml = (XMLNode)xmlNodes.get(id);
						if (tempXml != null)
						{
							Hashtable htprop = (Hashtable)tempXml.getAttributeList().clone();
							((AbstractBaseNmsPanel)xpanel).setCurrentNodeProperties(htprop);
							String updateMenuBar =(String) hasht.get("updateMenuBar");
							if(updateMenuBar != null && updateMenuBar.equals("true"))
								updatePanelMenuBar(xpanel,id);

							if ((useInternalFrame) && (tobeselected != null))
							{
								NmsInternalFrame nmsframe=getInternalFrameFromDesktopPane(panelkey);
								if (nmsframe != null)
								{
									String imageName = (String) htprop.get ("ICON-FILE");
									ImageIcon imgLeaf =null;
									if( imageName != null && !(imageName.trim().equals("")) )
									{
										imageName = applet.getDocumentBase() + "../" + imageName;//No Internationalisation
										imgLeaf = getScalledImage(NmsClientUtil.getImageIcon(imageName));
									}
									if( imgLeaf == null )
									{
										imgLeaf = new ImageIcon(NmsClientUtil.getImage(applet.getDocumentBase()+
													"../images/redDot"+NmsClientUtil.getImageType()));//No Internationalisation
									}

									//Altamar changes -- fix for empty internal frame
									JFrame exFrame = getExternalFrameForName (panelkey);
									if (exFrame != null)
									{
										exFrame.setIconImage(imgLeaf.getImage());
										return;
									}
									if(imgLeaf != null )
									{
										nmsframe.setFrameIcon(imgLeaf);
									}
									
									if (!nmsframe.isVisible())
										nmsframe.show();
								}
							}	
						}
						
					}
				}
				else
				{
					//If we are here then it means PROBLEMS!!!.
					if (selectedTreeNode != null)
					{
						//NEED to think here also !!!
						setSelectedNode(selectedTreeNode,true);  // added by rajasekar

						// Changes done for Netrake on 12th
						changePanel(selectedTreeNode);
					}
				}
			}
			catch (Exception ee)
			{
				System.err.println (NmsClientUtil.GetString("exception adding to tree View ") + ee);
				ee.printStackTrace ();
				ignoreValueChanged = false;
			}
			return;
		}

		if (e.getID () == NmsPanel.REMOVE_LEAF_FROM_MENU)
                    {
			try
			{
				Hashtable hasht = (Hashtable) e.getArgument ();
				if (hasht == null)
				{
					return;
				}
				Vector remleafs = (Vector) hasht.get ("removeleafs");//No Internationalisation//No Internationalisation
				String tobeselected = (String) hasht.get ("tobeselected");//No Internationalisation//No Internationalisation
				String panelkey = (String) hasht.get ("panelkey");//No Internationalisation//No Internationalisation
				
				XMLNode topmost = (XMLNode) currentComponents[0];
				
				XMLNode childNode = null;
				XMLNode parentNode = null;
				boolean isCurrentPanelGettingDeleted = false;
				//for (Enumeration en = remleafs.elements (); en.hasMoreElements ();)
                                int remLeafSize = remleafs.size();
                                for(int i=remLeafSize-1;i>=0;i--)
				{
                                    //String temp = (String) en.nextElement ();
                                    String temp = (String) remleafs.elementAt(i);
					xmlNodes.remove(temp);
					//keyTreeList.remove (temp);
					childNode = checkIfBelongs ( topmost, temp);

					if (childNode != null)
					{
                                                panelkey = (String)keyTreeList.get(temp);
						parentNode = childNode.getParentNode();
						//Changes for Tree Node disabling
						if( ! getAuthentication(childNode)) 
							return ;

						//if(panelkey.equals(NmsClientUtil.panelBeingViewed))
						if (temp.equals(currentNodeId))
							isCurrentPanelGettingDeleted = true;

						
							
						childNode.removeFromParent ();
						for(Enumeration enum = nmsPanelNames.elements(); enum.hasMoreElements();)
						{
							String nmspanelname = (String)enum.nextElement();
							if((nmspanelname != null) && (nmspanelname.startsWith(temp)))
							{
								currentPanelIndex--;
								nmsPanelNames.removeElement (nmspanelname);
							}
						}
					}
                                        else
                                        {
                                            //return;
                                            //System.err.println (NmsClientUtil.GetString("Cannot remove from ")+ temp + NmsClientUtil.GetString(" tree. No node matching with "+  temp + NmsClientUtil.GetString("in remove event" )));
					return;
                                        }
					boolean cleanupOver = false;
					if (useInternalFrame && openMultipleMaps)
					{
						cleanupOver = doCleanUpOnDelete(e.getSource(),temp,panelkey);
					}
					if (!cleanupOver && useInternalFrame)
					{
						/*This deletes the entry and "window"  menu item will be updated*///No Internationalisation//No Internationalisation
						addToOpenedInternalFrames(getSelectedPath(),null,false,temp); // rajasekar
					}
					keyTreeList.remove (temp);
					NmsCustomPanel.typeVsName.remove(temp);
                    removeFromOpenedInternalFrames(temp);
				}

				//added by rajasekar_27_05
				//if (useInternalFrame)
				//	updateWindowMenu();                             // rajasekar

				if (treeModel != null)
				{
                                XMLNode[] tempComponents = treeModel.getPathToRoot(parentNode);
                                TreePath currentTreePath = new TreePath (tempComponents);
                                Enumeration expPth = tree.getExpandedDescendants(currentTreePath);
					ignoreValueChanged = true;
					treeModel.reload (parentNode);
					ignoreValueChanged = false;
                                        if (expPth != null)
                                        {       
                                          while (expPth.hasMoreElements()) 
                                          {     
                                              tree.expandPath((TreePath)expPth.nextElement());
                                          }
                                        }
				}

				//Added for Altamar requirements
				NmsInternalFrame nmsframe = getInternalFrameFromDesktopPane(panelkey);
				if (nmsframe != null)
				{
					//nmsframe.setVisible(false);
				  	nmsframe.setSelected(false);
				  	removeInternalFrame(nmsframe);
				}
				if (tobeselected != null)
				{
					setSelectedNode (tobeselected,false);
				}
				else if ( selectedTreeNode != null )
				{   
 					if(isCurrentPanelGettingDeleted)
					{
						setSelectedNode(parentNode,false);
					}
					else
					{
						//Altamar changes -- changed to false.
						setSelectedNode(selectedTreeNode,false);
						if ((nmsframe != null) && (!nmsframe.isVisible()))
						{
						  currentNodeId = null;
						}
						changePanel(selectedTreeNode,false,false);
					}
				}
			}
			catch (Exception ee)
			{
				System.err.println (NmsClientUtil.GetString("exception removing from tree : ") + ee);
				ee.printStackTrace ();
			}
			NmsClientUtil.normalCursor(this);
			return;
		}
		if (e.getID () == NmsPanel.CHANGE_PANEL_EVENT)
		{
			Detach = true;
			Properties prop = (Properties) e.getArgument ();
			processChangePanelEvent (prop);
		}
		if (e.getID () == NmsPanel.CHANGE_TREE_SELECTION)
		{
			//if (openedExternalFrames.size () == 0)
			// This event selectes the node and updates the window and title is also set
			// for the corresponding internalFrame or externalFrame
			Hashtable hasht = (Hashtable) e.getArgument ();
			String tobeselected = (String)hasht.get("tobeselected");//No Internationalisation//No Internationalisation
			String panelkey = (String)hasht.get("panelkey");//No Internationalisation//No Internationalisation


			XMLNode nodeToBeSelected = (XMLNode)xmlNodes.get(tobeselected);

			if(nodeToBeSelected == null)
			{
				return;
			}
			
			XMLNode rootNode = deviceDB.getRootNode();
			nodeToBeSelected = checkIfBelongs(rootNode,tobeselected);
			if (nodeToBeSelected == null)
			{
				System.out.println(" The specified " + tobeselected + "is not present");
				return;
			}

			//Changes for Tree Node disabling
			if(!getAuthentication(nodeToBeSelected))
				return ;

			setSelectedNode (tobeselected, true);
			return;
		}

		if (e.getID () == NmsPanel.SET_APPLET_PROPERTY)
		{
			((NmsMainApplet) applet).setParameters ((Properties) e.getArgument ());
			return;
		}
		if (e.getID () == NmsPanel.MOVE_TREE_NODE)
		{
			//XMLNode selectedNode = (XMLNode)tree.getLastSelectedPathComponent ();
			//String  currNodeName = (String)selectedNode.getAttribute("ID");//No Internationalisation

			Hashtable hasht = (Hashtable) e.getArgument ();
			String nodename = (String) hasht.get("nodetobemoved");//No Internationalisation
			String parentnodename = (String) hasht.get("newparentnode");//No Internationalisation
			String tobeselected = (String) hasht.get("tobeselected");//No Internationalisation












			int indexVal = -1;
			if( hasht.get ("index") != null)//No Internationalisation
			{
				indexVal = ((Integer) hasht.get ("index")).intValue(); //No Internationalisation//No Internationalisation
			}









			
		//	moveTreeNode(nodename,parentnodename);
			moveTreeNode(nodename,parentnodename,indexVal,tobeselected);
			//setSelectedNode(currNodeName,true);
		}

		if (e.getID () == NmsPanel.MOVE_NODE_TO_NEWINDEX)
		{	
			Hashtable hasht = (Hashtable) e.getArgument ();

			String id = (String)hasht.get ("id");//No Internationalisation//No Internationalisation
			String tobeselected = (String) hasht.get ("tobeselected"); //No Internationalisation//No Internationalisation
			int newIndexVal = ((Integer) hasht.get ("newIndex")).intValue(); //No Internationalisation//No Internationalisation

			XMLNode rootNode = deviceDB.getRootNode();
			XMLNode node = checkIfBelongs(rootNode,id);
			if(node !=null)
			{
				XMLNode parentNode = node.getParentNode();
				if (treeModel == null) return ;
				
				//Changes for Tree Node disabling
				if(! getAuthentication( node))
					return ;

	

				
				int oldIndexVal = treeModel.getIndexOfChild(parentNode, node);
				Vector children = parentNode.getChildNodes ();
				int size = children.size();
				if( (oldIndexVal == -1) || (newIndexVal> size))
				{
					System.out.println(id +NmsClientUtil.GetString(" can not be moved to new index ")+newIndexVal);	
					return;
				}

				if( newIndexVal == oldIndexVal) return;
				
				children.remove(oldIndexVal);
				children.insertElementAt(node,newIndexVal);
				parentNode.setChildNodes(children);

				XMLNode currNode  = (XMLNode)tree.getLastSelectedPathComponent();
				if (treeModel != null)
				{
                                XMLNode[] tempComponents = treeModel.getPathToRoot(parentNode);
                                TreePath currentTreePath = new TreePath (tempComponents);
                                Enumeration expPth = tree.getExpandedDescendants(currentTreePath);
					ignoreValueChanged = true;
					treeModel.reload (parentNode);
					ignoreValueChanged = false;
                                        if (expPth != null)
                                        {       
                                          while (expPth.hasMoreElements()) 
                                          {     
                                              tree.expandPath((TreePath)expPth.nextElement());
                                          }
                                        }
					if (tobeselected != null)
					{
						selectedTreeNode = tobeselected;
						setSelectedNode (tobeselected, false);
					}
					else
					{
						setSelectedNode ( (String) currNode.getAttribute("ID") );//No Internationalisation
					}
				}
			}
		}
		if (e.getID () == NmsPanel.CLOSE_PANEL_EVENT)
		{
			Properties prop = (Properties) e.getArgument ();
			String panelName = prop.getProperty ("PANEL_NAME");//No Internationalisation
			String whattodo = prop.getProperty("DEFAULT-CLOSE-OPERATION");//No Internationalisation
			if( panelName == null) 
			{
				System.out.println(NmsClientUtil.GetString("No Panel registered for this panel")+panelName);	
				return;
			}
			if (whattodo == null)
				whattodo = getTheValue(panelName,"DEFAULT-CLOSE-OPERATION",null);//No Internationalisation
			if (whattodo == null)
				whattodo = "HIDE_ON_CLOSE";//No Internationalisation
			try
			{
				doCleanUp(panelName,whattodo,prop);
			}
			catch(Exception ee)
			{
				ee.printStackTrace();
			}
		}
		if (e.getID () == NmsPanel.MODIFY_TREE_NODE_PROPERTIES)
		{
			Hashtable hasht = (Hashtable) e.getArgument ();
			if (hasht == null)
			{
				return;
			}
			Object source = e.getSource();
			changeTreeNodeProperties(hasht,source);
		}
	}
	public void processChangePanelEvent (Properties prop)
	{
		boolean returnVal = false;
		String panelName = prop.getProperty ("PANEL_NAME");//No Internationalisation//No Internationalisation
		
		String tobeselected = prop.getProperty ("tobeselected");//No Internationalisation//No Internationalisation
		prop.remove("tobeselected");//No Internationalisation
		
		if (panelName == null)
		{
			return;
		}
		if (!(panelName).equals ("MapApplet"))//No Internationalisation//No Internationalisation
		{
			InitializePanel(panelName);
			NmsPanel panel = (NmsPanel) panelList.get (panelName);
			prop.remove ("PANEL_NAME");//No Internationalisation//No Internationalisation
			prop.put ("Arg", "temporary");//No Internationalisation//No Internationalisation
			storeState(tobeselected);
			//String key = prop.getProperty("OPEN_MAP");//No Internationalisation//No Internationalisation
			//if(key == null)
			if (tobeselected == null)
			{
				for (Enumeration en = keyTreeList.keys (); en.hasMoreElements ();)
				{
					String treenam = (String) en.nextElement ();
					String correspondingPanel = (String) keyTreeList.get (treenam);
					if (correspondingPanel.equals (panelName))
					{
						tobeselected = treenam;
						break;
					}
				}
			}
			// For opanframe and openpanel actions key would be null if type is not specified.
			String dis = null;
			if (tobeselected == null)
			{
				//setDefaultTreeNodeAndMenuBar();
				dis = panelName;
				tobeselected = selectedTreeNode;
			}
			else
			{
				dis = tobeselected;
			}
			NmsInternalFrame nmsframe=getInternalFrameFromDesktopPane(panelName);	
			if (panel == null)
			{
				System.err.println (NmsClientUtil.GetString("No panel registered to handle this request"));
				return;
			}
			
			if ((panel != null) && (panel instanceof AbstractBaseNmsPanel))
			{
				XMLNode tempXml = (XMLNode)xmlNodes.get(dis);
				if (tempXml != null)
				{
					Hashtable htprop = (Hashtable)tempXml.getAttributeList().clone();
					//changes for TreeNode disabling 
					String isEnabled = (String) htprop.get("isEnabled");
					if("false".equalsIgnoreCase(isEnabled))
						return;

					((AbstractBaseNmsPanel)panel).setCurrentNodeProperties(htprop);
				}
				panel.setProperties (prop);
			}
			else
			{
				panel.setProperties (prop);
			}
			if (!isExternalFramePresentForPanel(panel,dis))
			{	
				if (getDisplayName(dis) == null)
				{	
					addToOpenedInternalFrames (dis, nmsframe, true,dis,prop);
				}	
				else
				{	
					addToOpenedInternalFrames (getSelectedPath(), nmsframe, true,dis,null);
				}
				changePanel (panel, dis);
			}
			else
			{
				panel.setProperties (prop);
			}
			if (panel == null)
			{
				return;
			}
			if (getExternalFrameForName (panel.key ()) == null)
				setSelectedNode (tobeselected, true);
			return;
		}
		else
		{
			prop.remove ("PANEL_NAME");//No Internationalisation//No Internationalisation
			String treeString = (String) prop.get ("OPEN_MAP");//No Internationalisation//No Internationalisation
			if (treeString != null)
			{
				int index = treeString.indexOf (".netmap");//No Internationalisation//No Internationalisation
				if (index != -1)
					treeString = treeString.substring (0, index);
				changePanel (treeString);
			}
			setSelectedNode (treeString, true);
		}
	}

	private boolean valuedChanged = false;
	private boolean ignoreValueChanged = false;
	
	/** Called whenever there is change in TreeSelection*/
	public void valueChanged (TreeSelectionEvent e)
	{
		if (!e.isAddedPath ())
		{
			return;
		}
		if (ignoreValueChanged)
		{
			return;
		}
		
		currentComponents = e.getPath ().getPath ();
		XMLNode treeNode = (XMLNode) (e.getPath ().getLastPathComponent ());
		currentNode = treeNode;
		
		String nodeID    = (String) treeNode.getAttribute ("ID");//No Internationalisation//No Internationalisation
		String panelname = (String) treeNode.getAttribute ("PANEL-NAME");//No Internationalisation//No Internationalisation
		//changes for TreeNode disabling
		if( ! getAuthentication(treeNode))
		{
			showPrevInternalFrame(nodeID);
			return;
		}
		if (panelname != null)
		{
			if (initializedList.containsKey (panelname))
			{
				//store the value for reset the selected node after setting L and F.
				selectedTreeNode = nodeID;
			}
			changePanel (nodeID);
			valuedChanged = true;
		}
		else
		{
			String panelkey = (String) treeNode.getAttribute("PANEL-KEY");//No Internationalisation
			if (panelkey!= null)
			{
				changePanel (nodeID);
				valuedChanged = true;
			}
			else
			{
				String nodeName = currentNode.getNodeName();
				if(nodeName != null && (nodeName.equals("DEVICE-DATA") || nodeName.equals("DEVICE-GROUP")) )//No Internationalisation
				{
				return;
				}
			}
		}
	}

	MouseListener mouseListener = new MouseAdapter (){
		
		public void mouseClicked (MouseEvent e)
		{
		Object obj = e.getSource();
		if ((obj instanceof JLabel)&& (imageIcon != null))
		{	
		setCursor(new Cursor (Cursor.WAIT_CURSOR));	
		
		try
		{
		if (applet == null)
		{
		NmsClientUtil.err (null,
		NmsClientUtil.GetString
		(NmsClientUtil.GetString("Show Document Error. Cannot get Applet.Doc:")) +
		logoURL);
		return;
		}
		if(logoURL != null)
		{
			URL url = new URL ("http://"+logoURL);//No Internationalisation
			applet.getAppletContext ().showDocument (url, "_blank");//No Internationalisation
		}
		
		}
		catch (MalformedURLException me)
		{
		System.err.println (NmsClientUtil.GetString ("Cannot open new document:") + me);
		}
		setCursor(NmsClientUtil.normal_cursor);
		}
                if(obj instanceof JCheckBox)
                {
                    if(removetbar.isSelected())
                    {
                        showToolbar.setSelected(true);
                        removetbar.setPreferredSize(new Dimension(9,40));
                        removetbar.setIcon(toremove);
                        removetbar.setActionCommand("HideToolbar");//No Internationalisation
                        removetbar.setToolTipText(NmsClientUtil.GetString("Hide Toolbar"));
                        toolbarPanel.add(tbar,BorderLayout.CENTER);
                        toolbarPanel.add(logoLabel,BorderLayout.EAST);
                        toolbarPanel.repaint();
                    }
                    else
                    {
                        showToolbar.setSelected(false);
                        removetbar.setPreferredSize(new Dimension(60,8));
                        removetbar.setIcon(afterremoved);
                        removetbar.setActionCommand("ShowToolbar");//No Internationalisation
                        removetbar.setToolTipText(NmsClientUtil.GetString("Show Toolbar"));
                        toolbarPanel.remove(tbar);
                        toolbarPanel.remove(logoLabel);
                        toolbarPanel.repaint();
                    }
                }
                }
		public void mouseEntered(MouseEvent me)
                    {
                        Object obj = me.getSource();
                        if ((obj instanceof JLabel)&& (imageIcon != null)&&(logoURL != null))
                        {
                            // hand cursor is set for label alone because of busy cursor issue
                            ((JLabel)obj).setCursor(NmsClientUtil.hand_cursor);
                            //   setCursor(NmsClientUtil.hand_cursor);
                        }
                        if(obj instanceof JCheckBox && removetbar.getActionCommand()=="HideToolbar")//No Internationalisation
                            removetbar.setIcon(toremovemo);
                        if(obj instanceof JCheckBox && removetbar.getActionCommand()=="ShowToolbar")//No Internationalisation
                            removetbar.setIcon(afterremovedmo);
                }
		public void mouseExited(MouseEvent me)
                    {
                        Object obj = me.getSource();
                        if (obj instanceof JLabel)	
                        {
                            //   setCursor(NmsClientUtil.normal_cursor);
                        }
                        if(obj instanceof JCheckBox && removetbar.getActionCommand()=="HideToolbar")//No Internationalisation
                            removetbar.setIcon(toremove);
                        if(obj instanceof JCheckBox && removetbar.getActionCommand()=="ShowToolbar")//No Internationalisation
                            removetbar.setIcon(afterremoved);
                    }

                        // called whenever there is a mouse click on tree node
                        //this checks if the right click was made.
                        // if so then  it brings a popup menu
                        //N.Vidhya
                        
                public  void mouseReleased(MouseEvent ee)
                    {
		Object obj = ee.getSource();
		if (obj instanceof JTree)
		{	
		if(ee.isMetaDown())
		{
		TreePath selPath = tree.getPathForLocation (ee.getX (), ee.getY ());
		//if clicked around the tree selPath will be null.
		//hence we should return.
		if(selPath == null) 
		return;
		
		XMLNode treeNode = (XMLNode) (selPath.getLastPathComponent ());
		if(treeNode == null)
		return;
		
		String panelString = (String)treeNode.getAttribute("ID");//No Internationalisation//No Internationalisation
		String panelKey    = (String)keyTreeList.get(panelString);

		//incases where panel is not present. Say 'WebNMS-Panels'
		if(panelKey == null)
		return;
		
		/************ changes for show the tree popup menu **********/
		boolean callTreePopupMenu = true;
		// for Maps alone we need to check here 
		if( panelString.indexOf(".netmap") != -1 )
		{
			callTreePopupMenu = false;
			if( nmsPanelNames.contains(panelString))
			{
				callTreePopupMenu = true;
			}

		}
		/************ changes for show the tree popup menu **********/
		
		tree.setSelectionPath(selPath);
		InitializePanel(panelString);
		NmsPanel panel = (NmsPanel) panelList.get (panelKey);
		
		JMenu popupmenus = null;
		if (getExternalFrameForName (panel.key ()) != null)
			return;
		if(panel instanceof AbstractBaseNmsPanel)
		{
			if(	callTreePopupMenu )
				popupmenus = ((AbstractBaseNmsPanel)panel).getTreePopupMenu();
		}
		else
		{
			return;
		}
		
		//if the panel does not want to handle right click it will return null . Hence we just return.
		if(popupmenus == null)
		{
			return;
		}
		popup = new CustomPopupMenu();
		int menuCount = popupmenus.getMenuComponentCount();	
		
		for(int j=0;j<menuCount;j++)
		{
		popup.add(popupmenus.getMenuComponent(0));
		}
		popup.show(ee.getComponent(),ee.getX(),ee.getY());
		}
		}
		}	
		
		};

	void storeState (String nmsPanelName)
	{
		if (nmsPanelName == null)
		{
			return;
		}
		int size = nmsPanelNames.size ();
		String lastName = null;
		
		if(size > 0)
		{
			lastName = (String)(nmsPanelNames.lastElement());
		}
		if(lastName != null)
		{
			if(lastName.equalsIgnoreCase(nmsPanelName))
			{
				return;
			}
		}
		
		if((currentPanelIndex+1) < size)
		{
			int remove = size-(currentPanelIndex+1);
			
			for(int counter = 0; counter < remove; counter++ )
			{
				if(((nmsPanelNames.size()) >= (currentPanelIndex+2))&&((currentPanelIndex+1) >= 0))
				{
					nmsPanelNames.removeElementAt(currentPanelIndex+1);//eswaran
				}
			}
			
			nmsPanelNames.addElement (nmsPanelName);
			currentPanelIndex = (nmsPanelNames.size() - 1);
		}
		else
		{
			nmsPanelNames.addElement (nmsPanelName);
			currentPanelIndex++;
		}
	}
	
	public void showPanel (String panelName)
	{
		if (panelName.equals ("Events"))//No Internationalisation
		{
			panelName = "Events";//No Internationalisation
		}
		else if (panelName.equals ("Network Database"))//No Internationalisation
		{
			panelName = "Network Database";//No Internationalisation
		}
		else if (panelName.equals ("Alerts"))//No Internationalisation
		{
			panelName = "Alerts";//No Internationalisation
		}
		else if (panelName.equals ("Stats Admin"))//No Internationalisation
		{
			panelName = "Stats Admin";//No Internationalisation
		}
		else if (panelName.equals ("Mib Manager"))//No Internationalisation
		{
			panelName = "Mib Manager";//No Internationalisation
		}
		else if (panelName.equals ("Maps"))//No Internationalisation
		{
			panelName = "Maps";//No Internationalisation
		}
		setSelectedNode(panelName,false);
	}

	/** Selects a particular node in the tree*/


	boolean setSelectedNode (String nodeID)
	{
		if (nodeID == null) 
		{
			return false;
		}
		return setSelectedNode (nodeID, true);
	}
	
	public boolean setSelectedNode (String nodeID, boolean disableEvents)
	{
		try
		{
			if (nodeID == null || nodeID.equals(""))//No Internationalisation//No Internationalisation
				return false;
			XMLNode topmost = (XMLNode) currentComponents[0];
			//Vector tempcompvect = new Vector ();
			XMLNode noder = checkIfBelongs (topmost, nodeID);
			
			if (noder == null)
			{
				setSelectedNode ((String) topmost.getAttribute ("ID"));//No Internationalisation//No Internationalisation
				if (keyTreeList.containsKey(nodeID))
				System.err.println (NmsClientUtil.GetString(" there is no node matching ") + nodeID);
				return false;
			}

			setSelectedNode(noder,disableEvents);
		}
		catch (Exception ee)
		{
			System.err.println (NmsClientUtil.GetString("Exception changing tree view ") + ee);
			ee.printStackTrace ();
		}
		return true;
	}

	/*
	* Selects the selectNode from the tree.We just get the TreePath from 
	* the getPathToRoot() method in the XMLTreeModel Object and select 
	* that path. (cool stuff yeah !!)
	*/
	private boolean setSelectedNode(XMLNode selectNode,boolean disableEvents)
	{
		XMLNode[] tempComponents = treeModel.getPathToRoot(selectNode);
		TreePath currentTreePath = new TreePath (currentComponents);
		TreePath tempTreePath    = new TreePath (tempComponents);
		
		if (disableEvents)
		{
			ignoreValueChanged = true;
		}
		// try catch added to fix problem in MSP JavaUI
		try
		{
			tree.setSelectionPath (tempTreePath);
		}
		catch(Exception ex){}
		// try catch added to fix problem in MSP JavaUI
		
		if (disableEvents)
		{
			ignoreValueChanged = false;
		}
		
		return true;
	}
	
	public XMLNode checkIfBelongs (XMLNode parent, String match)
	{

		if (parent == null)
			return null;
		String treename = (String) parent.getAttribute ("ID");//No Internationalisation//No Internationalisation
		if (treename != null)
		{
			if (match == null)
                        {
                            return null;
                        }
			match = match.trim();
			if (treename.equals (match))
                        {
                            return parent;
                        }
		}
                

		if (treeModel.isLeaf(parent))
                {
                    return null;
                }

		Vector children = parent.getChildNodes ();

		if ((children == null) || (children.size () == 0))
                {
			return null;
                }

		for (int i = 0; i < children.size (); i++)
		{
                    XMLNode child = (XMLNode) children.elementAt (i);
                    XMLNode thisIsIt = checkIfBelongs (child, match);
                    if (thisIsIt != null)
                    {
                        return thisIsIt;
                    }
		}
		return null;
	}
	
	//PENDING:3
	private void  initializeTreeParms (DeviceDB deviceDB)
	{
		Vector v = (Vector)deviceDB.getRootNodes();










		for (int i = 0; i < v.size() ; i++)
		{
			XMLNode node = (XMLNode)v.elementAt(i);
			String javaui = (String)node.getAttribute("Client");//No Internationalisation
			if (javaui == null)
				javaui = "Java";//No Internationalisation
			if (javaui.equals("HTML"))//No Internationalisation
			return;
			initXMLNodeParms(node);
			String id = (String)node.getAttribute("ID");
			if (id.indexOf(".netmap") != -1)
			{
				String str = (String)node.getAttribute("TREE-NAME");
				if (str == null)
				{
					str = id;
				}
				MapApplet.updateMapNameVsLabel(id,str);
			}
			setupChildren(node);
		}

	}				// end initializeTreeParms()          
	

    private  void incrementProgressBarValue()
    {
        progressbar.setValue(count);
        //progressbar.window.setVisible(true);
        count++;
    }
    //PENDING:2
    private boolean initXMLNodeParms (XMLNode xmlNode)
    {
        if(progressbar!= null)
            incrementProgressBarValue();

        // Initialize an instance of the class identified by PANEL attribute
		String panelkey = (String)xmlNode.getAttribute("PANEL-KEY");//No Internationalisation
        
        String panelClassName = (String)xmlNode.getAttribute ("PANEL-NAME");//No Internationalisation//No Internationalisation
		
		String id = (String)xmlNode.getAttribute ("ID");//No Internationalisation
		if (id == null)
		{
			System.out.println(NmsClientUtil.GetString(" ID is null for the ") + xmlNode.getAttribute("TREE-NAME"));//No Internationalisation
			return false;
		}
		String javaui = getTheValue(id,"Client",xmlNode);//No Internationalisation
		if (javaui == null)
			javaui = "Java";//No Internationalisation
		if (javaui.equals("HTML"))//No Internationalisation
		{
			return true;
		}
        String imgFile = getTheValue(id,"ICON-FILE",xmlNode);//No Internationalisation
		if (imgFile != null)
            treeCellRenderer.addTreeIcon (imgFile);
        String init_on_startup = "true";//No Internationalisation
        init_on_startup = getTheValue(id,"INIT-ON-STARTUP",xmlNode);//No Internationalisation
        String fullName = "";//No Internationalisation//No Internationalisation













        String treename = getTheValue(id,"TREE-NAME",xmlNode);//No Internationalisation//No Internationalisation
        XMLNode parentNode = xmlNode.getParentNode();
		if (parentNode != null)
		{
			if (!parentNode.getNodeName().equals("DEVICE-GROUP"))//No Internationalisation//No Internationalisation
			{
				String parentId    = (String)parentNode.getAttribute("ID");//No Internationalisation//No Internationalisation
                if (parentId != null)
				{	
					String dispName    = (String)NmsCustomPanel.typeVsName.get(parentId);
					fullName = (dispName == null)?"":dispName + "." ;//No Internationalisation
				}
			}	
		
		String tempNodeId =(String)parentNode.getAttribute("ID");//No Internationalisation

		if (! tempNodeId.equals("Maps") && tempNodeId.indexOf(".netmap") == -1)//No Internationalisation
		{
		   NmsCustomPanel.typeVsName.put(id,fullName + treename);
		} 
		}// MSP changed the brackets common for all

		boolean validPanelKey = false;

		// Here the PANEL-KEY will be considered first. If INIT-ON-STARTUP is true
		// then the panel would be instantiated else not. If given panelkey is not present NmsPanels.conf and PANEL-NAME value is also not present then throw an error message.
		// 
		if (panelkey != null)
		{
			if ((panelList.get(panelkey.trim()) == null))
			{
				if ((init_on_startup == null) || (init_on_startup.equalsIgnoreCase("TRUE")))//No Internationalisation
				{
					validPanelKey = createNmsPanel(panelkey);
					if ( (!validPanelKey) && (panelClassName == null) )
					{
						System.out.println(NmsClientUtil.GetString("The specified panelkey is not known  ") + panelkey + " for the node " + id);
						return false;
					}
					else
					{
						keyTreeList.put(id,panelkey); 
						xmlNodes.put(id,xmlNode);
						moduleIds.put (id,panelkey);
					}
				}
			}
			else
			{
				keyTreeList.put(id,panelkey); 
				xmlNodes.put(id,xmlNode);
				moduleIds.put (id,panelkey);
				return true;
			}
		}
		if (init_on_startup == null)
		{
			init_on_startup = "true";//No Internationalisation
		}
		if (init_on_startup.equalsIgnoreCase("false"))//No Internationalisation
        {
            return true;
        }
		//if (panelkey == null)
		//{	
			if (panelClassName != null)
			{
				Object panelInstance = null;
				try
				{
					String keyOfPanel = "";//No Internationalisation//No Internationalisation
					if (initializedList.containsKey (panelClassName))
					{
						if (panelkey == null)
						{
							keyOfPanel = (String) initializedList.get (panelClassName);
						}
						else
						{
							if (panelList.get(panelkey.trim()) != null)
							{
								panelkey = null;
								keyOfPanel = (String) initializedList.get (panelClassName);
							}
						}
					}
					else
					{
						panelkey = "dummy";//No Internationalisation
					}

					if (panelkey != null)
					{
						Class panelClass = Class.forName (panelClassName);
						panelInstance = panelClass.newInstance ();
						NmsPanel nmsPanel = (NmsPanel) panelInstance;
						String menuFileName = getTheValue(id,"MENU-FILE-NAME",xmlNode);//No Internationalisation//No Internationalisation
						String nodeId = (String)xmlNode.getAttribute("ID");//No Internationalisation//No Internationalisation
						xmlNodes.put(nodeId,xmlNode);
                    //createPanelMenuBar (menuFileName, nmsPanel);
						nmsPanel.addNmsPanelEventListener (this);
						if(!addPanelToList (nmsPanel,nodeId))
							return false;
                    // This xmlNodes hashtable will have (id,xmlnodes)
                    

						keyOfPanel = nmsPanel.key ();

						if(keyOfPanel == null)
						{
							System.err.println(NmsClientUtil.GetString("The method key() returns null for the class : panelClassName."));
							System.err.println(NmsClientUtil.GetString("ClassName : ")+panelClassName);
							return false;
						}
						initializedList.put (panelClassName, keyOfPanel);
						
						//for the purpose of tree popup, we set this variable in the corresponding panel
						if(nmsPanel instanceof AbstractBaseNmsPanel)
							((AbstractBaseNmsPanel)nmsPanel).treePopupMenuName = (String)xmlNode.getAttribute("TREE-POPUP-MENU");//No Internationalisation//No Internationalisation
						
					}
					
					String xmlNodeID = (String) xmlNode.getAttribute ("ID");//No Internationalisation//No Internationalisation
					keyTreeList.put (xmlNodeID, keyOfPanel);
					if(keyTreeList.get (xmlNodeID) != null)
					{
						moduleIds.put (xmlNodeID, keyTreeList.get (xmlNodeID));
					}
					xmlNodes.put(xmlNodeID,xmlNode);
					//initializedList.put (keyOfPanel, "true");//No Internationalisation//No Internationalisation

					return true;
				}
				catch(ClassNotFoundException e)
				{
					System.err.println(NmsClientUtil.GetString("ClassNotFoundException for the node ") + xmlNode.getAttribute("ID"));//No Internationalisation
					return false;
				}
				catch (Exception ce)
				{
					ce.printStackTrace ();
					return false;
				}
			}
			else
			{
				String nodeId = (String)xmlNode.getAttribute("ID");//No Internationalisation//No Internationalisation
				xmlNodes.put(nodeId,xmlNode);
			}
			//}
			//else
			//{
			//keyTreeList.put(id,panelkey); 
			//xmlNodes.put(id,xmlNode);
			//}
		return true;
    }
    
    
    private void setupChildren (XMLNode parentNode)
    {
		String javaui = (String)parentNode.getAttribute("Client");//No Internationalisation
		if (javaui == null)
			javaui = "Java";//No Internationalisation
		if (javaui.equals("HTML"))//No Internationalisation
			return;
        for (Enumeration c = parentNode.getChildNodes ().elements (); c.hasMoreElements ();)
        {
            XMLNode childNode = (XMLNode) c.nextElement ();
			Hashtable ht = childNode.getAttributeList();
			String id = (String)childNode.getAttribute("ID");//No Internationalisation












			if (id.indexOf(".netmap") != -1)//No Internationalisation
			{
				Hashtable addhash = new Hashtable ();
				Vector ipvect = new Vector ();
				if (id == null)
				{
					System.out.println(NmsClientUtil.GetString("ID is null for the map") + childNode.getAttribute("TREE-NAME"));//No Internationalisation
				}

				ipvect.addElement (id);
				addhash.put ("newleafs", ipvect);//No Internationalisation//No Internationalisation
				addhash.put ("panelkey", "MapApplet");//No Internationalisation//No Internationalisation
 				addhash.put ("parent", (String)childNode.getParentNode().getAttribute("ID"));//No Internationalisation//No Internationalisation
				Hashtable tobeset = (Hashtable)childNode.getAttributeList();
				for (Enumeration e = tobeset.keys(); e.hasMoreElements(); )
				{
					String key = (String)e.nextElement();
					String value = (String)tobeset.get(key);
					addhash.put(key,value);
				}
				String indexVal =(String)ht.get("NODE-INDEX");//No Internationalisation
				if(indexVal != null )
				{
					try
					{
						int index = Integer.parseInt(indexVal);
						addhash.put ("index",new Integer(index));//No Internationalisation
					}
					catch(Exception ex)
					{}
				}
				
				addhash.put("source",childNode);//No Internationalisation
				Properties p = new Properties();
				String str = (String)childNode.getAttribute("ICON-FILE");//No Internationalisation
				if (str == null)
					str = "";//No Internationalisation
				p.put("ICON-FILE",str);//No Internationalisation
				str = (String)childNode.getAttribute("TREE-NAME");//No Internationalisation
				if (str == null)
					str = id;
				p.put("TREE-NAME",str);//No Internationalisation
                                Vector treeNames = new Vector();
                                treeNames.addElement(str);
				p.put("FRAME-TITLE",str);//No Internationalisation
                                addhash.put("newleafsName",treeNames);
				
				//Fix for getting the panel name 
				String  panelName = getPanelClassName((String)ht.get("PANEL-KEY"));//No Internationalisation
				if( panelName == null )
				{
					panelName =(String) ht.get("PANEL-NAME");//No Internationalisation
				}
				if(panelName == null)
				{
					System.err.println(NmsClientUtil.GetString("No panel name specified for ")+id);	
					continue;
				}
				p.put ("PANEL-NAME",panelName );//No Internationalisation
				
				//mohan MapProblemDueToChangesOnServer
				MapApplet.updateMapNameVsLabel(id,str);
				addhash.put("attributeList",p);//No Internationalisation
				//ignore = false;
                                addhash.put("addNode","false");
				Event event = new Event (this, NmsPanel.ADD_LEAF_TO_MENU, addhash);
				NmsPanelEvent nmsevt = new NmsPanelEvent (event);
				handleNmsPanelEvent(nmsevt);
				
			}
            if(! initXMLNodeParms (childNode))
                continue;
            Object deviceRef = (Object) parentNode.getAttribute ("DEVICE-REF");//No Internationalisation
            if(deviceRef != null)
            {
                childNode.setAttribute ("DEVICE-REF", deviceRef);//No Internationalisation//No Internationalisation
            }
            if (!(treeModel.isLeaf(childNode)))
                setupChildren (childNode);
        }
    }
    
    public String getTableColumns (String nmsPanel)
    {
        XMLNode xmlnode = (XMLNode)xmlNodes.get(nmsPanel);
        if(xmlnode != null)
        {
            String nodeAttrib = (String) xmlnode.getAttribute ("TABLE-COLUMNS");//No Internationalisation
            if(nodeAttrib != null)
            {
                return nodeAttrib;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    private String selectFirstLeafInTree ()
    {
        XMLNode topmost = (XMLNode) currentComponents[0];
        //String treename = (String)topmost.getAttribute("TREE-NAME");//No Internationalisation//No Internationalisation
        String treename = (String) topmost.getAttribute ("ID");//No Internationalisation//No Internationalisation
        if (treeModel.isLeaf(topmost))
            return treename;
        while (true)
        {
            Vector children = topmost.getChildNodes ();
            if ((children == null) || (children.size () == 0))
                return (String) topmost.getAttribute ("ID");//No Internationalisation//No Internationalisation
            //return (String)topmost.getAttribute("TREE-NAME");//No Internationalisation//No Internationalisation
            topmost = (XMLNode) children.elementAt (0);
        }
    }
    
    String selectedTreeNode = null;
    JRadioButtonMenuItem look_item[];
    String look_itemClassName[];
    ButtonGroup lookgroup = new ButtonGroup ();
    
    public void createLookAndFeelMenu ()
    {
		setALFDefaults();
        JMenu lookAndFeelMenu = null;
        int menuCount         = menuBar.getMenuCount ();
        for (int i = 0; i < menuCount; i++)
        {
            JMenu thisMenu = menuBar.getMenu (i);
            if(NmsClientUtil.validateMenuText(thisMenu.getText(), NmsClientUtil.GetString("Look And Feel")))
            {
                lookAndFeelMenu = thisMenu;
            }
        }
        if (lookAndFeelMenu == null)
        {
            lookAndFeelMenu = new JMenu();
            //return;
        }
        String defaultLook = UIManager.getLookAndFeel ().getName ();
        UIManager.LookAndFeelInfo[] lookAndFeelInformation = 
            UIManager.getInstalledLookAndFeels ();
        
        look_item = new JRadioButtonMenuItem[lookAndFeelInformation.length];
        look_itemClassName = new String[lookAndFeelInformation.length];
        
        for (int i = 0; i < lookAndFeelInformation.length; i++)
        {
            String lookName = lookAndFeelInformation[i].getName ();
            look_itemClassName[i] = lookAndFeelInformation[i].getClassName ();
            look_item[i] = new JRadioButtonMenuItem (NmsClientUtil.GetString(lookName));
            look_item[i].setMnemonic (NmsClientUtil.GetString(lookName).charAt (0));
            look_item[i].setFont (NmsClientUtil.getFont ("MENU"));//No Internationalisation//No Internationalisation
            lookAndFeelMenu.add (look_item[i]);
            lookgroup.add (look_item[i]);
            
            if (lookName.equals (defaultLook))
            {
                look_item[i].setSelected (true);
            }
            
            try
            {
                LookAndFeel lnfClass = (LookAndFeel) Class.forName (look_itemClassName[i]).newInstance ();
                if (lnfClass.isSupportedLookAndFeel () == false)
                {
                    look_item[i].setEnabled (false);
                }
            }
            catch (Exception lookexcep)
            {
                look_item[i].setEnabled (false);
            }
            
            look_item[i].addItemListener (this);
        }
        //menuBar.add (lookAndFeelMenu);
    }
    public void itemStateChanged (ItemEvent ie)
    {
        Object obj = ie.getSource ();
        if (ie.getStateChange() == ie.SELECTED)
        {
            if (obj instanceof JRadioButtonMenuItem)
            {
                String itemname = ((JRadioButtonMenuItem) obj).getActionCommand ();
                if (itemname != null && itemname.startsWith ("WINDOW"))//No Internationalisation//No Internationalisation
                {
                    JRadioButtonMenuItem selectedItem = (JRadioButtonMenuItem) obj;
                    changeInternalFrame (selectedItem.getName ());
                }
                else
                {
                    for (int i = 0; i < look_item.length; i++)
                    {
                        if (obj == look_item[i])
                        {
                            NmsClientUtil.busyCursor(this);
                            try
                            {
                                UIManager.setLookAndFeel (look_itemClassName[i]);
								setALFDefaults();
                            }
                            catch (Exception exc)
                            {
                                String feelClass = UIManager.getCrossPlatformLookAndFeelClassName ();
                                try
                                {
                                    UIManager.setLookAndFeel (feelClass);
									setALFDefaults();
                                }
                                catch (Exception exc2)
                                {
                                }
                                // Error - unsupported Look & Feel
                                
                                JOptionPane.showMessageDialog (NmsClientUtil.getFrame (this), exc.getMessage (),
                                                               NmsClientUtil.GetString("Invalid L & F"), JOptionPane.ERROR_MESSAGE);
                            }
                            
                            //XMLNode currNode  = (XMLNode)tree.getLastSelectedPathComponent();
							updateAllComponentsUI ();
							//setJMenuBar (frame,menuBar);
                            //setSelectedNode (getID(selectedTreeNode));
                            //setSelectedNode(currNode,true);
                            //NmsClientUtil.normalCursor(this);
                        }
                    }
                }
            }
        }
    }
    
    
    Vector openedExternalFrames = null;
    Vector openedInternalFrames = null;
    Vector openedCardPanelNames = null;
    boolean isExternalFrameShown = false;
    private Dimension defaultSize = new Dimension (800, 600);
    JMenu windowMenu = null;
    ButtonGroup windowMenuButtonGroup = new ButtonGroup ();
    
    private boolean showTBar = false;
    private Hashtable windowMenuItems = new Hashtable();
    private String actionValue;
    private int position, permpos = -1;
    private WindowMenuCustomizer menuCustomizer = null;

    private void createCheckBoxMenuItem()
    {
        int totalMenus = menuBar.getMenuCount();
        for (int i = 0; i < totalMenus; i++)
        {
            JMenu menu = menuBar.getMenu(i);
            int items = menu.getItemCount();
            for(int j = 0; j < items; j++)
            {
                JMenuItem item = menu.getItem(j);
                if(item != null)
                {
                    String cmd = item.getActionCommand();
                    if(cmd.equals("Show Toolbar"))
                    {
                        showTBar = true;
                        String text = NmsClientUtil.GetString(((JMenuItem)item).getText());
                        menu.remove((JMenuItem)item);
                        item = new JCheckBoxMenuItem(text, true);
                        ((JCheckBoxMenuItem)item).setActionCommand(cmd);
                        ((JCheckBoxMenuItem)item).addActionListener(this);
                        ((JCheckBoxMenuItem)item).setFont(NmsClientUtil.getFont("MENU"));
                        showToolbar = (JCheckBoxMenuItem)item;
                        menu.insert(showToolbar, j);
                    }
                }
            }
        }
    }

    public void createWindowMenu ()
    {
        int totalMenus = menuBar.getMenuCount();
        for (int i = 0; i < totalMenus ; i++)
        {
            String str = menuBar.getMenu (i).getText ();
            if(str != null && NmsClientUtil.validateMenuText(str, NmsClientUtil.GetString("Window")))
            {
	        windowMenu = menuBar.getMenu (i);
                int menuItemCount = windowMenu.getItemCount();
                for(int j = 0; j < menuItemCount; j++)
                {
                    Object item = windowMenu.getItem(j);
                    if(item != null)
                    {
                        String cmd = ((JMenuItem)item).getActionCommand();
                        if(cmd.equals("Cascade"))
                            windowMenuItems.put(cmd, item);

                        else if(cmd.equals("TileH"))
                            windowMenuItems.put(cmd, item);
       
                        else if(cmd.equals("TileV"))
                            windowMenuItems.put(cmd, item);

                        else if(cmd.equals("SaveLS"))
                            windowMenuItems.put(cmd, item);

                        if(( ((JMenuItem)item).getText() ).equals("WindowMenuCustomizer"))
                        {
                            actionValue = cmd;
                            windowMenu.remove((JMenuItem)item);
                            position = j;
                            permpos = j;
                            menuItemCount--;
                            j--;
                        }
                    }
               }
               break;
            }
        }
        updateWindowMenu ();
    }
    
    public void updateWindowMenu ()
    {
        if (windowMenu != null)
        {
            position = permpos;
            if(windowMenuButtonGroup != null)
            {
                for(Enumeration en = windowMenuButtonGroup.getElements(); en.hasMoreElements();)
                    windowMenu.remove((JMenuItem)en.nextElement());
            }
            windowMenuButtonGroup = new ButtonGroup ();
        }
        else
            return;
        boolean opened = false;

        String param = applet.getParameter("DISABLE_WINDOW_MENUITEM");
        boolean disable = false;
        if(param != null && param.equalsIgnoreCase("true"))
            disable = true;

        if (useInternalFrame)
        {
            if(position >= 0)
            {
                for (Enumeration framesInfo = openedInternalFrames.elements (); framesInfo.hasMoreElements ();)
                {
                  Object info[] = (Object[])framesInfo.nextElement ();
                  if (info.length == 5 && ((Boolean) info[2]).booleanValue () == true)
                  {
                    String wname = (String) info[0];
                    wname = NmsClientUtil.GetString(wname);
                    NmsInternalFrame iframe = (NmsInternalFrame) info[1];
                    if (wname == null || iframe == null)
                        continue;
                    JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem (wname);
                    String nodeID = (String)info[3];
                    menuItem.setName (nodeID);
                    menuItem.setActionCommand ("WINDOW" + wname);//No Internationalisation//No Internationalisation
					
                   // menuItem.setText(wname);
                    XMLNode selectedNode = (XMLNode)tree.getLastSelectedPathComponent ();
                    String currNodeName = null;
                    //added because if the top node is not associated with a panel then it would't get selected even if all the frames are minimized
                    if(selectedNode != null)
                        currNodeName = (String)selectedNode.getAttribute("ID");//No Internationalisation
                    //Set selected the item which has the corresponding tree-node
                    //selected.
                    XMLNode rootNode = deviceDB.getRootNode();
                    XMLNode tempXML = checkIfBelongs(rootNode,nodeID);
                    if (tempXML == null)
                    {
                        if (iframe.isSelected () )
			{
                            menuItem.setText(nodeID);
                            menuItem.setSelected (true);
			}	
                    }
                    else
                    {
                        String menuString = getPathString(tempXML);
                        String parsedString = getParsedString(menuString);
                        menuItem.setText(parsedString);
                        //added because if the top node is not associated with a panel then it would't get selected even if all the frames are minimized

                        if(currNodeName != null)
                        {                        
                            if (iframe.isSelected () && currNodeName.equals (nodeID))
                            {
                                 menuItem.setSelected (true);
                            }
                        }
                    }	
                    menuItem.setFont (NmsClientUtil.getFont ("MENU"));//No Internationalisation
                    menuItem.addItemListener (this);
                    windowMenuButtonGroup.add (menuItem);
                    windowMenu.insert(menuItem, position);
                    position ++;
                    opened = true;
                }
              }
                position = position - 1; 
            }
            if(opened)
            {
                for(Enumeration en = windowMenuItems.elements(); en.hasMoreElements();)
                {
                    if(disable)
                        ((JMenuItem)en.nextElement()).setEnabled(true);
                    else
                        ((JMenuItem)en.nextElement()).setVisible(true);
                }
                if(! disable)
                {
                    int totalItems = windowMenu.getItemCount();
                    for(int i = 0; i < totalItems ; i++)
                    {
                        Component comp = windowMenu.getMenuComponent(i);
                        if(comp instanceof JSeparator)
                            comp.setVisible(true);
                    }
                }
                if(tbar != null && showTBar)
                {                             
                    if(removetbar.getActionCommand()=="HideToolbar")//No Internationalisation
                    {
                        showToolbar.setSelected(true);
                    }
                    else
                    {
                        showToolbar.setSelected(false);
                    }
                    showToolbar.setEnabled(true);
                }
                else
                {
                    showToolbar.setSelected(false);
                    showToolbar.setEnabled(false);
                }
            }			
            else
            {
                for(Enumeration en = windowMenuItems.elements(); en.hasMoreElements();) 
                {
                    if(disable)
                        ((JMenuItem)en.nextElement()).setEnabled(false);
                    else
                        ((JMenuItem)en.nextElement()).setVisible(false);
                }
                if(! disable)
                {
                    int totalItems = windowMenu.getItemCount();
                    for(int i = 0; i < totalItems ; i++)
                    {
                        Component comp = windowMenu.getMenuComponent(i);
                        if(comp instanceof JSeparator)
                            comp.setVisible(false);
                    }
                }
            }
        }
        else if (!useInternalFrame)
        {
            JMenuItem detachMenuItem = new JMenuItem (NmsClientUtil.GetString("Detach"));
            detachMenuItem.setFont (NmsClientUtil.getFont ("MENU"));//No Internationalisation//No Internationalisation
            detachMenuItem.setActionCommand ("Detach");//No Internationalisation//No Internationalisation
            detachMenuItem.setName ("Detach");//No Internationalisation//No Internationalisation
            detachMenuItem.setMnemonic ((int) 't');
            detachMenuItem.setAccelerator (KeyStroke.getKeyStroke ((int) 'T', Event.CTRL_MASK));
            detachMenuItem.addActionListener (this);
            windowMenu.add (detachMenuItem);
            if(tbar != null && showTBar)
            {
                if(removetbar.getActionCommand()=="HideToolbar")//No Internationalisation
                {
                    showToolbar.setSelected(true);
                }
                else
                {
                    showToolbar.setSelected(false);
                }
                showToolbar.setEnabled(true);
            }
            else if(showTBar)
            {
                showToolbar.setSelected(false);
                showToolbar.setEnabled(false);
            }
        }
    }
	public void createPanelMenuBar (String menuFileName, NmsPanel panel)
    {
        Vector menusVector = NmsClientUtil.getMenuVector (menuFileName, panel);
        JMenuBar mb = new JMenuBar ();
		if (menusVector != null && menusVector.size() > 0)
		{
			addMenusToMenuBar(mb,menusVector);
		}
		mb.setName(menuFileName);
		panel.setPanelMenuBar (mb);
    }
	//If the NmsPanel has the frame menu as a panel specific menu then the following code is needed for handle the menu bar creations 
	private JMenuBar addMenusToMenuBar(JMenuBar mb, Vector menusVector)
	{
		if( menusVector != null )
		{
			for (int i = 0; i < menusVector.size (); i++)
			{
				if( menusVector.elementAt(i) instanceof Vector )
				{
				 addMenusToMenuBar(mb,(Vector) menusVector.elementAt(i));
				}
				else
					mb.add ((JMenu) (menusVector.elementAt (i)));
			}
		}
		return mb;
	}
    
    /*      This function can be called from other files also ie, MapApplet or etc, to provide
            multiple windows for maps or custom views with seperate mnubars.
            Here key is nothing but panel.getClass().getName().
    */
    public void createPanelMenuBar (NmsPanel panel, String key)
	{
		XMLNode xmlnode = (XMLNode)xmlNodes.get(key);
		if (xmlnode != null)
		{
			String menuFileName = getTheValue(key,"MENU-FILE-NAME",xmlnode);
			createPanelMenuBar (menuFileName, panel);
		}
	}

	public void updatePanelMenuBar (NmsPanel panel, String treename)
	{
	    
		//JMenuBar mbar = panel.getPanelMenuBar();
		//whether menubar already attached with external frame
		JMenuBar mbar = panel.getPanelMenuBar ();
		JFrame externalFrame = getExternalFrameForName (panel.key ());
		if (externalFrame != null)
		{
			if (mbar != null)
			{
				externalFrame.setJMenuBar (mbar);
				//SwingUtilities.updateComponentTreeUI (mbar);
			}
			return;
		}
		//This code is used to check whether to update the menubar for currently modified node or add node
		if( panel instanceof AbstractBaseNmsPanel )
		    {
			Hashtable attributeList = (Hashtable)((AbstractBaseNmsPanel)panel).getCurrentNodeProperties();
			String nodeID = (String)attributeList.get("ID");
			if( nodeID != null )
			    {
				if( !(nodeID.equals(currentNodeId)))
				    return;
			    }
		    }

		if (menuBar != null)
		{
			if (menuBar.getMenuCount () != 0)
				menuBar.removeAll ();
			int count = 0;

			if (mbar != null)
				count = mbar.getMenuCount ();
			for (int i = 0; i < menusFromBegining.size (); i++)
				menuBar.add ((JMenu) menusFromBegining.elementAt (i));
			if (mbar != null)
				for (int i = 0; i < count; i++)
				{
					menuBar.add (mbar.getMenu (0));
				}
			for (int i = 0; i < menusFromEnd.size (); i++)
				menuBar.add ((JMenu) menusFromEnd.elementAt (i));
			//panel.setPanelMenuBar(menuBar);
			if (useInternalFrame)
                            updateWindowMenu();
				//createWindowMenu ();
			/*Runnable run = new Runnable()
				{
					public void run()
					{
						SwingUtilities.updateComponentTreeUI (menuBar);
					}

					};
					SwingUtilities.invokeLater(run);*/
			setJMenuBar (frame,menuBar);
			frame.validate ();
		}
		else
		{
			setDefaultMenuBar ();
		}
	}

	private void setDefaultMenuBar ()
	{

		if (menuBar.getMenuCount () != 0)
			menuBar.removeAll ();
		for (int i = 0; i < menusFromBegining.size (); i++)
			menuBar.add ((JMenu) menusFromBegining.elementAt (i));
		for (int i = 0; i < menusFromEnd.size (); i++)
			menuBar.add ((JMenu) menusFromEnd.elementAt (i));
		if (useInternalFrame)
                     updateWindowMenu();
			//createWindowMenu ();
		//SwingUtilities.updateComponentTreeUI (menuBar);
		setJMenuBar (frame,menuBar);
		frame.invalidate ();
		frame.validate ();
		frame.repaint ();
	}

	public JFrame getExternalFrameForName (String framename)
	{
		for (Enumeration eframesInfo = openedExternalFrames.elements (); eframesInfo.hasMoreElements ();)
		{
			Object info[] = (Object[])eframesInfo.nextElement ();
			String fname = (String) info[0];
                       
			JFrame exframe = (JFrame) info[1];
                       
			if (fname.equals (framename))
				return exframe;
		}
		return null;
	}

	public void showExternalFrame (NmsPanel xpanel, String title,String nodeID)
	{
		showExternalFrame (xpanel, title, nodeID,defaultSize);
	}

	ExternalWindowAction extWindowAction = null;
	
	public void showExternalFrame (NmsPanel xpanel, String title, String nodeID,Dimension size)
	{
		//This is going to be the External Window's Window Listener
		if(extWindowAction == null)
		{
			extWindowAction = new ExternalWindowAction ();
		}
		
		JFrame externalFrame = new NmsExternalFrame (title,infoImage,NmsClientUtil.INFORMATION_COLOR );
		//SwingUtilities.updateComponentTreeUI(externalFrame);
		externalFrame.setName (xpanel.key ());
		externalFrame.setSize (size.width, size.height);
		if (xpanel instanceof WindowListener)
		{
			externalFrame.addWindowListener((java.awt.event.WindowListener)xpanel); 
		}
		else
		{
			externalFrame.addWindowListener (extWindowAction);
		}
		externalFrame.getContentPane ().add ("Center",(JPanel) xpanel);//No Internationalisation//No Internationalisation
		//This is a work around made so as to make the opensubmapinNW working
		//I don't know how this xpanel(MapApplet) is being made invisible.But
		//this code makes it work !!. Find another solution and avoid this piece
		//of code as this sounds ridiculous ???
		((JPanel) xpanel).setVisible(true);
		Object newInfo[] = {xpanel.key (), externalFrame,nodeID};
		openedExternalFrames.addElement (newInfo);
		JMenuBar exMenuBar = xpanel.getPanelMenuBar ();
		if (exMenuBar != null)
		{
			setJMenuBar(externalFrame,exMenuBar);
			//SwingUtilities.updateComponentTreeUI (exMenuBar);
		}
		NmsClientUtil.centerWindow (externalFrame);
		externalFrame.setVisible (true);
		try
		{
			externalFrame.setState(Frame.NORMAL);
		}
		catch(Exception et)
		{
		}
		externalFrame.show();
		String imageName = applet.getDocumentBase()+ "../images/redDot"+NmsClientUtil.getImageType();//No Internationalisation
		Image image = NmsClientUtil.getImage(imageName);
		if (xmlNodes.containsKey(nodeID))
		{
			imageName = (String)((XMLNode)xmlNodes.get(nodeID)).getAttribute("ICON-FILE");//No Internationalisation
			imageName = applet.getDocumentBase() + "../" + imageName;//No Internationalisation
		}
		if (NmsClientUtil.getImage(imageName) != null)
			image = NmsClientUtil.getImage(imageName);
		if ((image != null) && (keyTreeList.containsKey(nodeID)))
		{
			externalFrame.setIconImage(image);
		}
		externalFrame.toFront();       // rajasekar 
	}
	private void removeExternalFrame (JFrame eframe)
	{
		removeExternalFrame(eframe,false,true);
	}
	private void removeExternalFrame (JFrame eframe,boolean reallyRemove)
	{
		removeExternalFrame(eframe,reallyRemove,true);
	}
	
	private void removeExternalFrame (JFrame eframe,boolean reallyRemove,boolean attachBack)
	{
		String nodeID = null;
		// added now
		for (Enumeration eframesInfo = openedExternalFrames.elements (); eframesInfo.hasMoreElements ();)
		{
			Object info[] = (Object[])eframesInfo.nextElement ();
			JFrame exframe = (JFrame) info[1];
			if (exframe.equals (eframe))
			{
				openedExternalFrames.removeElement (info);
				nodeID = (String)info[2];
				break;
			}
		}
		eframe.setVisible (false);
		String action = eframe.getName();
		//eframe.dispose ();

		if(nodeID == null)
		{
			//this will NEVER happen, but still for safety
			return;
		}
		NmsPanel xpanel = (NmsPanel) panelList.get (eframe.getName ());
		if (xpanel == null)
			return;
		if (xpanel instanceof java.awt.event.WindowListener)
			eframe.removeWindowListener((java.awt.event.WindowListener)xpanel);
		if (useInternalFrame)
		{
			NmsInternalFrame iframe = getInternalFrameFromDesktopPane (eframe.getName ());	// rajasekar
			if (iframe != null)
			{
				//Why are we doing this here.
				if (reallyRemove)
				{
					((NmsExternalFrame)eframe).close("dispose");
					removeInternalFrame(iframe,true);
					return;
				}
				else
				{
					((NmsExternalFrame)eframe).close("donotdispose");
				}
				//reset the contentpane to internal frame
				//iframe.setContentPane(eframe.getContentPane());
				iframe.getContentPane ().add ((JPanel) xpanel);
				((JPanel) xpanel).validate ();
				iframe.validate ();
			}
		}
		else
		{
			if (reallyRemove)
			{
				removeCardPanel(xpanel);
				return;
			}
			cardPanel.add (xpanel.key (), (Component) xpanel);
		}
		if (!attachBack)
		{
			return;
		}
		// First consider the global value
		String attach_on_close = applet.getParameter("ATTACH-ON-CLOSE");
		if (xpanel instanceof AbstractBaseNmsPanel)
		{
			// Respective panels can override the value that is specified globally.
			String localValue = (String)(((AbstractBaseNmsPanel)xpanel).getCurrentNodeProperties()).get("ATTACH-ON-CLOSE");//No Internationalisation
			if (localValue != null)
				attach_on_close = localValue;
		}
		
		if (attach_on_close == null || attach_on_close.equalsIgnoreCase("true"))//No Internationalisation
		{
			selectedTreeNode = nodeID;  
			if (keyTreeList.containsKey(nodeID))
				setSelectedNode(nodeID); 
			//((NmsExternalFrame)eframe).close("donotdispose");
			changePanel(nodeID,true,false);
		}
		NmsClientUtil.normalCursor(this);// Sometimes the cursor remains in busy state. MSP
	}

	private void removeAllExternalFrames ()
	{
		for (Enumeration eframesInfo = openedExternalFrames.elements (); eframesInfo.hasMoreElements ();)
		{
			Object info[] = (Object[])eframesInfo.nextElement ();
			JFrame exframe = (JFrame) info[1];
			NmsPanel xpanel = (NmsPanel) panelList.get (exframe.getName ());
			if (useInternalFrame)
			{
				NmsInternalFrame iframe = getInternalFrameFromDesktopPane (exframe.getName ());
				iframe.getContentPane ().add ((JPanel) xpanel);
			}
			else
			{
				cardPanel.add (xpanel.key (), (Component) xpanel);
			}
			exframe.setVisible (false);
			exframe.dispose ();
		}
		openedExternalFrames.clear ();
		updateWindowMenu ();
	}

	private boolean removeInternalFrame (NmsInternalFrame nmsframe)
	{
		return removeInternalFrame (nmsframe, false);
	}
    
	private boolean removeInternalFrame (NmsInternalFrame nmsframe, boolean reallyRemove)
	{
		boolean removed = false;
		if ((openedInternalFrames == null) || (nmsframe == null))
		{
			return removed;
		}
		for (Enumeration framesInfo = openedInternalFrames.elements (); framesInfo.hasMoreElements ();)
		{
			Object info[] = (Object[])framesInfo.nextElement ();
			String wname = (String) info[3];
			NmsInternalFrame iframe = (NmsInternalFrame) info[1];
			if (iframe != null && iframe.equals(nmsframe))
			{
				if (reallyRemove)
				{
					nmsframe.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
					openedInternalFrames.removeElement(info);
					frame.invalidate();
					frame.validate();
					frame.repaint();
					removePanelFromList(iframe.getName());
					iframe.setVisible(false);
					removed = true;
					iframe.dispose();
					iframe.setClosed(true);
				}
				else
				{
						if (wname.equals(iframe.getID()))
						info[2] = new Boolean (false);
				}
			}
		}
		
		// This deletes the entries from nmsPanelNames on closing....
		if(nmsPanelNames.contains(nmsframe.getID()))
		{
			currentPanelIndex--;
			
			int namessize = nmsPanelNames.size();
			int remove = namessize-(currentPanelIndex+1);
			
			for(int counter = 0; counter < remove; counter++ )
			{
				if(((nmsPanelNames.size()) >= (currentPanelIndex+2))&&((currentPanelIndex+1) >= 0))
				{
					nmsPanelNames.removeElementAt(currentPanelIndex+1);//eswaran
				}
			}
			
			currentPanelIndex = (nmsPanelNames.size() - 1);
			
			while(nmsPanelNames.contains(nmsframe.getID()))
			{
				currentPanelIndex--;
				nmsPanelNames.removeElement(nmsframe.getID());
			}
		}
		
		if (!reallyRemove)
		{
			nmsframe.setVisible (false);
			return true;
		}
		else
			return removed;
	}

	private void removeAllInternalFrames ()
	{
		if (openedInternalFrames == null)
			return;
		for (Enumeration framesInfo = openedInternalFrames.elements (); framesInfo.hasMoreElements ();)
		{
			Object info[] = (Object[])framesInfo.nextElement ();
			String wname = (String) info[0];
			NmsInternalFrame nmsframe = (NmsInternalFrame) info[1];
			if (nmsframe == null)
			{
				continue;
			}
			if( nmsframe != null )
			{
				try
				{
					if (nmsframe.isIcon ())
						nmsframe.setIcon (false);
				}
				catch (java.beans.PropertyVetoException ex)
				{
				}
				info[2] = new Boolean (false);
			}
		}
		currentPanelIndex = -1;
		nmsPanelNames.removeAllElements();	
		JInternalFrame frames[] = NmsUiAPI.getDesktopPane().getAllFrames ();
		NmsInternalFrame nmsframe = null;
		for (int i = 0; i < frames.length; i++)
		{
			nmsframe = (NmsInternalFrame)frames[i];
			try
			{
				if (nmsframe.isIcon ())
					nmsframe.setIcon (false);
			}
			catch (java.beans.PropertyVetoException ex)
			{
			}
			nmsframe.setVisible (false);
		}
	}

	public NmsInternalFrame getCurrentInternalFrame ()
	{
		String iframeName = null;
		for (Enumeration menuitems = windowMenuButtonGroup.getElements (); menuitems.hasMoreElements ();)
		{
			JRadioButtonMenuItem item = (JRadioButtonMenuItem) menuitems.nextElement ();
			if (item.isSelected ())
			{
				iframeName = item.getName ();
				break;
			}
		}

		if (iframeName != null)
		{
			String panelKey = (String)keyTreeList.get(iframeName);
			if (panelKey == null)
			{
				panelKey = iframeName;
			}
			NmsInternalFrame iframe = getInternalFrameFromDesktopPane (panelKey);
			return iframe;
		}
		return null;
	}

	public void detachInternalFrame (String panelkey)
	{
		// added by Rajasekar
		boolean showExternalWindow = false;
		
		if (openedInternalFrames == null ||
			openedInternalFrames.size() == 0)
		{
			setDefaultTreeNodeAndMenuBar();       //added by rajasekar_22_05_2000
			return;
		}
		// end of add rajasekar

		//Changed the getting the treeName to getting the 
		//XMLNode-ID, to avoid the getID() call..
		NmsInternalFrame iframe = getInternalFrameFromDesktopPane (panelkey);
		
		if(iframe == null)
		{
			return;
		}

		String currNodeID          = iframe.getID();
		String prevNodeID          = null;
		NmsInternalFrame  nmsframe = null;
		int size                   = openedInternalFrames.size()-1;
		
		for (int i = size;i >= 0;i--)
		{
			Object info[] =(Object[]) openedInternalFrames.elementAt(i);
			nmsframe  = (NmsInternalFrame) info[1];
			boolean isOpen = ((Boolean) info[2]).booleanValue();
			if (isOpen)
			{
				showExternalWindow = true;
				/* Here previous node tobe selected is found */
				if((nmsframe != iframe) && (!nmsframe.isIcon ()))   // rajasekar 
				{							
					//treeName = (String) info[3];
                                    JFrame exFrame = getExternalFrameForName (nmsframe.getName());
                                    if( exFrame == null )
                                    {
					prevNodeID = nmsframe.getID();
					break;
                                    }
				}
			}
		}	
		
		if(!showExternalWindow)   // rajasekar
		{
			setDefaultTreeNodeAndMenuBar();       // added by rajasekar_22_05_2000
			return;
		}

		NmsPanel xpanel = (NmsPanel) panelList.get (iframe.getName ());
		if (xpanel == null)
		{
			return;
		}
		
		//removeInternalFrame(iframe,false);
		if(nmsPanelNames.contains(currNodeID))
		{
			currentPanelIndex--;
			
			int namessize = nmsPanelNames.size();
			int remove = namessize-(currentPanelIndex+1);
			
			for(int counter = 0; counter < remove; counter++ )
			{
				if(((nmsPanelNames.size()) >= (currentPanelIndex+2))&&((currentPanelIndex+1) >= 0))
				{
					nmsPanelNames.removeElementAt(currentPanelIndex+1);//eswaran
				}
			}
			
			currentPanelIndex = (nmsPanelNames.size() - 1);
			
			while(nmsPanelNames.contains(currNodeID))
			{
				currentPanelIndex--;
				nmsPanelNames.removeElement(currNodeID);
			}
		}
		
		removeInternalFrame (iframe);
		updateWindowMenu();	                  // rajasekar
		//showExternalFrame (xpanel, iframe.getTitle (), currNodeID,iframe.getSize ());
                String tempvar = currNodeID;
		// added by rajasekar
		/* Previous panel is shown here */
		if (prevNodeID != null)
		{
			try
			{
				ignoreThisEvent = true;
				nmsframe.setSelected(true);
				ignoreThisEvent = false;
			}
			catch (Exception exception)
			{
				System.out.println(NmsClientUtil.GetString("Exception in PropertyVetoException"));
				exception.printStackTrace();
			}
			setSelectedNode(prevNodeID);
			//While detaching the internal frame we have to change the currentNodeID to the currently selected treeNode. added by Pitchaimani
			currentNodeId = prevNodeID;
		}
		else
		{
			if (panelkeyVsToolbars != null)
			{
				tbar = (ToolBar) panelkeyVsToolbars.get ("Default");//No Internationalisation
				if (tbar != null)
				{
					tbar.disableIcon(NmsClientUtil.GetString("Detach"));
				}
			}

			setDefaultTreeNodeAndMenuBar();         // added by rajasekar
			setSelectedNode((String)deviceDB.getRootNode().getAttribute("ID"));//No Internationalisation
			//return;
		}
		// end of add rajasekar
                showExternalFrame(xpanel, iframe.getTitle (), tempvar,iframe.getSize ());
	}

	//This might be called from MapApplet
	//added by rajasekar_27_05
	public void detachPanel (String panelName, String title)
	{
		JFrame exFrame = getExternalFrameForName (panelName);
		//added by rajasekar_27_05
		if (exFrame != null)
		{
			try
			{
				// Set the state to normal always.
				exFrame.setState (Frame.NORMAL);
			}
			catch (NoSuchMethodError err)
			{
				//to prevent NoSuchMethodError in linux
			}
			exFrame.show ();
			return;
		}
		
		NmsInternalFrame iframe = getInternalFrameFromDesktopPane (panelName);
		NmsPanel xpanel         = (NmsPanel) panelList.get (panelName);
		if (xpanel == null)
		{
			return;
		}
		
		//added by rajasekar_27_05
		if(useInternalFrame)
		{
			removeInternalFrame (iframe);
		}
		else
		{
			removeCardPanel(xpanel);
		}
		
		if (iframe != null)
		{
			//need to analyze here .. A very BAD assumption is being made here
			//ie., we have only the MapApplet calling this method where the title
			//and the id are the same.. Need to change this. !!!
			addToOpenedInternalFrames(title,iframe,false,panelName,null);
			showExternalFrame (xpanel, title, panelName,iframe.getSize ());
		}
		else if (desktopPane != null)
		{
			showExternalFrame (xpanel, title, panelName,desktopPane.getPreferredSize ());
		}
		else
		{
			showExternalFrame (xpanel, title,panelName);
		}
		//isExternalFramePresentForPanel(xpanel,panelName);
	}
	
	private NmsInternalFrame getInternalFrameFromDesktopPane (String frameName)
	{
		if (desktopPane == null)
			return null;
			
		JInternalFrame frames[] = desktopPane.getAllFrames ();
		
		if( frames == null )
			return null;
			
		for (int i = 0; i < frames.length; i++)
		{
			if(frames[i] instanceof NmsInternalFrame)
			{
				NmsInternalFrame nmsInternalFrame = (NmsInternalFrame)frames[i];
				if (frameName.equals (nmsInternalFrame.getName ()))
					return nmsInternalFrame;
			}
			
		}
		return null;
	}

	//updating the ID for the openedExternalFrames vector. This will be
	//necessary incase where we , after detaching the panel, select some
	//child nodes ala CustomViews etc., The openedExternalFrameVector should
	//contain the latest id info..
	private void updateOpenedExternalFrames(JFrame eframe,String id)
	{
		for (Enumeration eframesInfo = openedExternalFrames.elements (); eframesInfo.hasMoreElements ();)
		{
			Object info[] = (Object[])eframesInfo.nextElement ();
			JFrame exframe = (JFrame) info[1];
			if(exframe.equals(eframe))
			{
				info[2] = id;
			}
		}
		return;
	}
	
	private boolean showInternalFrame (final NmsPanel panel, String nodeID)
	{
		//if there is any external frame for the panel show it.
		String id = nodeID;
		isExternalFrameShown = isExternalFramePresentForPanel(panel,nodeID);
		if(!isExternalFrameShown)
		{
			NmsInternalFrame nmsframe = getInternalFrameFromDesktopPane (panel.key ());
			if (nmsframe == null)
			{
				return true;
			}
			
			String action= getTheValue(nodeID,"DEFAULT-CLOSE-OPERATION",(XMLNode)xmlNodes.get(nodeID));//No Internationalisation
			if ( ("Maps".equals(nodeID)) || action == null || panel instanceof NmsCustomPanel)
				action = "HIDE_ON_CLOSE";//No Internationalisation
			Vector validate = new Vector();
			validate.addElement("DO_NOTHING_ON_CLOSE");
			validate.addElement("HIDE_ON_CLOSE");
			validate.addElement("DISPOSE_ON_CLOSE");
			validate.addElement("DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE");
			validate.addElement("DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE");
			if (!validate.contains(action))
			{
				action = "HIDE_ON_CLOSE";
			}
			String setAction = "DO_NOTHING_ON_CLOSE";//No Internationalisation
			int setActionValue = JInternalFrame.DO_NOTHING_ON_CLOSE;
			if(action.equals("HIDE_ON_CLOSE"))//No Internationalisation
			{
				setAction = "HIDE_ON_CLOSE";//No Internationalisation
				setActionValue = JInternalFrame.HIDE_ON_CLOSE;
			}
			else if(action.equals("DISPOSE_ON_CLOSE")) //No Internationalisation
			{
				setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
				setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
			}
			else if (action.equals("DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE"))//No Internationalisation
			{
				setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
				setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
			}
			else if (action.equals("DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE"))//No Internationalisation
			{
				setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
				setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
			}
			nmsframe.setDefaultCloseOperation (setActionValue);
			
			XMLNode node  =	(XMLNode)xmlNodes.get(nodeID);
			
			if (node != null)
			{
				String title =(String)node.getAttribute("FRAME-TITLE");//No Internationalisation
				if(title == null || title.trim().equals("") )//No Internationalisation
				{
					title = (String)node.getAttribute("TREE-NAME");
				}
			
				if ( (!(panel instanceof InternalFrameListener)) && (keyTreeList.containsKey(nodeID)) )
					nmsframe.setTitle(NmsClientUtil.GetString(title));
			}
			//resetting the ID of the InternalFrame...
			nmsframe.setID(nodeID);
			try
			{
				String imageName = null;
				if (xmlNodes.containsKey(nodeID))
				{
					imageName = (String)((XMLNode)xmlNodes.get(nodeID)).getAttribute("ICON-FILE");//No Internationalisation
					imageName = applet.getDocumentBase() + "../" + imageName;//No Internationalisation
				}
				ImageIcon imgLeaf =null;
				if( imageName != null && !(imageName.trim().equals("")) )
				{
					imgLeaf = getScalledImage(NmsClientUtil.getImageIcon(imageName));
				}
				if( imgLeaf == null )
				{
					imgLeaf = new ImageIcon(NmsClientUtil.getImage(applet.getDocumentBase()+
								"../images/redDot"+NmsClientUtil.getImageType()));//No Internationalisation
				}
				if (keyTreeList.containsKey(nodeID))
				nmsframe.setFrameIcon(imgLeaf);
				nmsframe.setSelected (true);
				if (nmsframe.isIcon ())
					nmsframe.setIcon (false);
			}
			catch (java.beans.PropertyVetoException ex)
			{
				System.err.println (ex);
			}
			if (keyTreeList.get(nodeID) == null)
			{
				Properties p = getProps(nodeID);
				addToOpenedInternalFrames (nodeID, nmsframe, true,nodeID,p);
			}
			else
			{
				addToOpenedInternalFrames (getSelectedPath(), nmsframe, true,id);
			}
			selectedTreeNode = nodeID;    
			updateWindowMenu ();
			if (!nmsframe.isVisible())
			{
				//panel.start();
				nmsframe.show ();
			}
			nmsframe.validate ();
			if (Detach)
			{	
				frame.toFront();
				Detach = false;
			}	
		}
		return isExternalFrameShown;
	}
	
	private void updateOpenedInternalFrameWithDisplayName(String dispname,String id)
	{
		if (!useInternalFrame)
			return;
		for (Enumeration framesInfo = openedInternalFrames.elements (); framesInfo.hasMoreElements ();)
		{
			Object info[] = (Object[])framesInfo.nextElement();
			String wname = (String) info[3];
			if (wname != null && wname.equals (id))
			{
				info[0] = dispname;
				return;
			}
		}
	}
	private void addToOpenedInternalFrames (String treename, NmsInternalFrame nmsframe, boolean isFrameOpened,String id)
	{
		addToOpenedInternalFrames (treename,nmsframe,isFrameOpened,id,null);
	}

	private void addToOpenedInternalFrames (String treename, NmsInternalFrame nmsframe, boolean isFrameOpened,String id,Properties p)
	{
		if (!useInternalFrame)
			return;

		Boolean isOpen = new Boolean (isFrameOpened);
		for (Enumeration framesInfo = openedInternalFrames.elements (); framesInfo.hasMoreElements ();)
		{
			Object info[] = (Object[])framesInfo.nextElement ();
			String wname = (String) info[3];
		
			if (wname != null && wname.equals (id))
			{
				//This property is taken from the previous info bject set it ot the new info object
				if(p == null )
				{
					p =(Properties) info[4];
				}
				openedInternalFrames.removeElement (info);
				Object newInfo[] = {treename, nmsframe, isOpen,id,p};
				if (nmsframe != null)             // added by rajasekar			
				{
					openedInternalFrames.addElement (newInfo);
				}
				return;
			}
			
		}
		Object newInfo[] = {treename, nmsframe, isOpen,id,p};
		openedInternalFrames.addElement (newInfo);

	}
    
    //This method is used to remove the Opened InternalFrame from the list
     private void removeFromOpenedInternalFrames(String nodeID )
     {
	 if (!useInternalFrame  || nodeID == null || nodeID.trim().equals(""))
	     return;
	 
	 for (Enumeration framesInfo = openedInternalFrames.elements (); framesInfo.hasMoreElements ();)
		 {
		     Object info[] = (Object[])framesInfo.nextElement ();
		     String wname = (String) info[3];
		     
		     if ( wname.equals (nodeID))
			 {
			     openedInternalFrames.removeElement (info);
			     break;
			 } 
		     
		 }
    } 
    
	/*
	* This method will be invoked from the Window Menu Item.
	*/
	private void changeInternalFrame (String treeName)
	{
		selectedTreeNode = treeName;
		final String id = treeName;
		if (keyTreeList.containsKey(id))
		setSelectedNode (id);
		String keyOfPanel = (String) keyTreeList.get (id);
		if (keyOfPanel == null)
		{	
			keyOfPanel = treeName;
		}	
		NmsPanel panel = (NmsPanel) panelList.get (keyOfPanel);
		if (panel == null)
			return;
		//      showInternalFrame(panel,treeName);
		Runnable run = new Runnable()
		{
			public void run()
			{
				try
				{
					changePanel (id);
				}
				catch(Exception ee)
				{
				}
			}
		};
		SwingUtilities.invokeLater(run);
	}
	// Added by Ananda Raman to 
	// minimise all frames if Adventnet or WebNMS Panel is selected in tree.

	private void minimiseAllInternalFrame ()
	{
		NmsInternalFrame nmsframe = null;
		for (int i = openedInternalFrames.size () - 1; i >= 0; i--)
		{
			Object info[] = (Object[])openedInternalFrames.elementAt (i);
			nmsframe = (NmsInternalFrame) info[1];
			boolean isOpen = ((Boolean) info[2]).booleanValue ();
			if (isOpen)
			{
				try
				{
					if (!nmsframe.isIcon ())	    
						nmsframe.setIcon(true);
				}
				catch (java.beans.PropertyVetoException ex)
				{
					System.err.println (ex);
				}	   
			}
			
		}
		setDefaultMenuBar ();
	}

	boolean ignoreThisEvent = false;
	/*
	* We show the previous InternalFrame here. This method will be invoked
	* in the following cases :
	* 1. InternalFrame is DeActivated or Closed
	* 2. InternalFrame is minimized.
	*/
	private void showPrevInternalFrame (String currentNodeID )
	{
		String treeName = null;
		String id = null;
		NmsInternalFrame nmsframe = null;
		for (int i = openedInternalFrames.size () - 1; i >= 0; i--)
		{
			Object info[] = (Object[])openedInternalFrames.elementAt (i);
			String wname = (String) info[0];
			nmsframe = (NmsInternalFrame) info[1];
			boolean isOpen = ((Boolean) info[2]).booleanValue ();
                        JFrame extfrm = getExternalFrameForName(nmsframe.getName());
			if (nmsframe != null && !nmsframe.isIcon () && isOpen && extfrm == null)
			{
				treeName = wname;
				id = (String)info[3];
				break;
			}
		}
		// Current node id check is added for avoiding the unnecessary looping.......... 
		if (id != null && !(id.equals(currentNodeID))) 
		{
			selectedTreeNode = id;
			setSelectedNode (id);
			String keyOfPanel = (String) keyTreeList.get (id);
			if (keyOfPanel == null)
			{	
				keyOfPanel = id;
			}	
			NmsPanel panel = (NmsPanel) panelList.get (keyOfPanel);
			if (panel == null)
			{
				return;
			}	
			//Altamar changes -- third argument changed to false
			changePanel (id, false, true);
		}
		else
		{
			setDefaultTreeNodeAndMenuBar ();
		}
		NmsClientUtil.normalCursor(this); // MSP incase cursor remains busy.
	}

	/**
	 * This is the inner class which acts as a Window Listener for
	 * all those detached NmsPanels.
	 */
	class ExternalWindowAction extends WindowAdapter
	{
		public void windowActivated (WindowEvent evt)
		{
			JFrame eframe = (JFrame) evt.getSource ();
			NmsPanel panel = (NmsPanel) panelList.get (eframe.getName ());
			if (panel != null)
				NmsClientUtil.panelBeingViewed = panel.key ();
		}
		
		public void windowClosing (WindowEvent evt)
		{
			JFrame eframe = (JFrame) evt.getSource ();
			String panelKey = null;
			String id = null;
			for (Enumeration eframesInfo = openedExternalFrames.elements (); eframesInfo.hasMoreElements ();)
			{
				Object info[] = (Object[])eframesInfo.nextElement ();
				JFrame exframe = (JFrame) info[1];
				if (exframe.equals (eframe)) 
				{
					panelKey= (String)info[0];
					id = (String)info[2];	
					break;
				}
			}

			eframe.setName(panelKey);
			if( id != null )
			{
				doCleanUp(id,null,null);
			}
			removeExternalFrame (eframe);
			NmsClientUtil.normalCursor(MainPanel.this);

		}
		public void windowDeiconified (WindowEvent e)
		{
			JFrame f = (JFrame) e.getSource ();
			Dimension dim = f.getSize();
			int width     = dim.width;
			int height    = dim.height;
			f.setSize (width + 1, height);
			f.setSize (width - 1, height);
		}
		
	}//End of ExternalWindowAction

	/**
	 * This is the inner class which acts as a InternalFrame Listener for
	 * all the InternalFrames created in the WebNMS Client.
	 */
	class InternalFrameAction extends InternalFrameAdapter
	{
		public void internalFrameClosing (InternalFrameEvent ife)
		{
			NmsInternalFrame nmsframe = (NmsInternalFrame) ife.getSource();
			try
			{
				if (nmsframe.isIcon ())
					nmsframe.setIcon (false);
			}
			catch (java.beans.PropertyVetoException ex)
			{
			}
			String id = nmsframe.getID();
			try
			{
				doCleanUp(id,null,null);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			if (!nmsframe.isSelected())
			{
				nmsframe.setVisible(false);
				internalFrameDeactivated(ife);
			}
		}
		
		public void internalFrameActivated (InternalFrameEvent ife)
		{
			NmsInternalFrame iframe = (NmsInternalFrame) ife.getSource ();
			selectedTreeNode = iframe.getID();
			//need to change here!!. Ideally this must be like
			//the commented part of the code.But somehow we are not
			//selecting the correct Node in a sense.So , reverting back!!(:-()
			//String id = getID(selectedTreeNode);
 			String id = iframe.getID();
	 		if (keyTreeList.containsKey(id))
		 	setSelectedNode (id);
			NmsPanel panel = (NmsPanel) panelList.get (iframe.getName ());
			NmsClientUtil.panelBeingViewed = panel.key ();
			changePanel(panel,id);
		}
		
		public void internalFrameDeactivated (InternalFrameEvent ife)
		{
			NmsInternalFrame iframe = (NmsInternalFrame) ife.getSource ();
			if (openedInternalFrames != null && iframe.isVisible () == false)
			{
				removeInternalFrame (iframe);
				if (!ignoreThisEvent)
				showPrevInternalFrame (iframe.getID());
                                //fix to update the windows menu while closing the internal frame which is not in focus
                                updateWindowMenu();
			}
		}
		
		public void internalFrameIconified (InternalFrameEvent ife)
		{
			NmsInternalFrame iframe = (NmsInternalFrame) ife.getSource ();
			if (!ignoreThisEvent)
			showPrevInternalFrame (iframe.getID());
		}
	}//End of InternalFrameAction
	
	public void updateAllComponentsUI ()
	{
            XMLNode currNode  = (XMLNode)tree.getLastSelectedPathComponent();
		SwingUtilities.updateComponentTreeUI (this);
		JMenuBar mb = frame.getJMenuBar ();
		if (mb != null)
		{
			SwingUtilities.updateComponentTreeUI (mb);
		}
		//setJMenuBar(frame,mb);
		Frame[] array = Frame.getFrames();
		int sizeOfFrames = array.length;
		for (int i = 0; i < sizeOfFrames ; i ++)
		{
			Frame frame = array[i];
			SwingUtilities.updateComponentTreeUI (frame);
			Window [] windows = frame.getOwnedWindows();
			int sizeOfWindows = windows.length;
			for (int j = 0; j < sizeOfWindows; j++)
			{
				Window window = windows[j];
				SwingUtilities.updateComponentTreeUI(window);
			}
		}
		SwingUtilities.updateComponentTreeUI(applet);
		if (panelkeyVsToolbars != null)
		{
			for (Enumeration en = panelkeyVsToolbars.keys (); en.hasMoreElements ();)
			{
				String keys = en.nextElement ().toString();
				SwingUtilities.updateComponentTreeUI(((ToolBar) panelkeyVsToolbars.get (keys)));
			}
			SwingUtilities.updateComponentTreeUI(((ToolBar) panelkeyVsToolbars.get ("Default")));//No Internationalisation
		}
		MapApplet.updateAllMapAppletObjectsUI ();
		for (Enumeration e = panelList.keys(); e.hasMoreElements(); )
		{
			String key = (String)e.nextElement();
			NmsPanel nmspanel = (NmsPanel)panelList.get(key);  
			SwingUtilities.updateComponentTreeUI((JComponent)nmspanel);
		}
                setSelectedNode(currNode,true);
                NmsClientUtil.normalCursor(this);
	}
	
	private void setDefaultTreeNodeAndMenuBar ()
	{
		setDefaultTreeNodeAndMenuBar (true);
	}
	
	//Selecting the rootNode here !!
	private void setDefaultTreeNodeAndMenuBar (boolean updateMenubar)
	{
		selectedTreeNode = (String)deviceDB.getRootNode().getAttribute("ID");//No Internationalisation
                
                if(deviceDB.getRootNode().getAttribute("PANEL-NAME") != null || deviceDB.getRootNode().getAttribute("PANEL-KEY") != null) 
                    tree.setSelectionRow(-1);
                else
                    setSelectedNode(selectedTreeNode);
                
		//XMLNode treeNode = (XMLNode) (tree.getPathForRow (0).getLastPathComponent ());
		XMLNode treeNode = deviceDB.getRootNode();
		NmsClientUtil.panelBeingViewed = "";//No Internationalisation
		currentNode = treeNode;
		//String treename = (String)treeNode.getAttribute("TREE-NAME");//No Internationalisation//No Internationalisation
		String treename = (String) treeNode.getAttribute ("ID");//No Internationalisation//No Internationalisation
		selectedTreeNode = treename;
		if (updateMenubar)
		{	
			setDefaultMenuBar ();
			if (currentPanel != null &&	tbar != null)
			{
				tbar.removeActionListener (currentPanel);
				//currentPanel.stop ();
				currentPanel = null;
			}
			else
			{
				currentPanel = null;
			}
			setToolBar(null);
		}	
	}
	
	private void showCardPanel (NmsPanel panel, String nodeID)
	{
		isExternalFrameShown = isExternalFramePresentForPanel(panel,nodeID);
		if(!isExternalFrameShown)
		{
			
			card.show ((Container) cardPanel, panel.key ());
			if (nodeID != null)
			{
				if (openedCardPanelNames.contains (nodeID))
				{
					openedCardPanelNames.removeElement (nodeID);
				}
				openedCardPanelNames.addElement (nodeID);
			}
			else
			{
				setDefaultTreeNodeAndMenuBar (false);
			}
			if (Detach)
			{	
				frame.toFront();
				Detach = false;
			}		
		}
	}
	
	/*
	* This method actually checks whether there is any external frame present
	* for the current NmsPanel.If present it will invoke the externalFrame
	* and would select the previously selected node in the tree.
	*/
	private boolean isExternalFramePresentForPanel(NmsPanel panel,String nodeID)
	{
		//if there is any external frame for the panel show it.
		JFrame eframe = getExternalFrameForName (panel.key ());
		if (eframe != null)
		{
			String lastName = null;
			int nmsPanelNamesSize = nmsPanelNames.size();
			
			if(nmsPanelNamesSize > 0)
			{
				lastName = (String)(nmsPanelNames.lastElement());
			}
			if(lastName != null)
			{
				if(lastName.equalsIgnoreCase(nodeID))
				{
					currentPanelIndex--;
					nmsPanelNames.removeElementAt(nmsPanelNamesSize-1);
				}
			}

			//removeExternalFrame(eframe);
			try
			{
				// Set the state to normal always.
				eframe.setState (Frame.NORMAL);
			}
			catch (NoSuchMethodError err)
			{
				//to prevent NoSuchMethodError in linux
			}
			//Temply !! to make things work smoothly...
			eframe.requestFocus();
			updateOpenedExternalFrames(eframe,nodeID);
			
			
			
			String imageName = null;
			if (xmlNodes.containsKey(nodeID))
			{
				XMLNode node  =	(XMLNode)xmlNodes.get(nodeID);
				String title =(String)node.getAttribute("FRAME-TITLE");//No Internationalisation
				if(title == null || title.trim().equals("") )//No Internationalisation
				{
					title = (String)node.getAttribute("TREE-NAME");
				}
				if ( (!(panel instanceof WindowListener)) && (keyTreeList.containsKey(nodeID)) )
					eframe.setTitle(NmsClientUtil.GetString(title));

				imageName = (String)((XMLNode)xmlNodes.get(nodeID)).getAttribute("ICON-FILE");//No Internationalisation
				imageName = applet.getDocumentBase() + "../" + imageName;//No Internationalisation
			}
			Image image = NmsClientUtil.getImage(imageName);
			if  ( (image != null) && (keyTreeList.containsKey(nodeID)) )
			{
				eframe.setIconImage(image);
			}
			isExternalFrameShown = true;
			//Just select the previously selected node incase there is
			//an existing ExternalFrame associated with this panel.
			if(useInternalFrame)
			{
				showPrevInternalFrame(nodeID);
			}
			else
			{
				showPrevCardPanel ();
			}
			//To make the external frame to come upfront!!
			try
			{
				eframe.setState(Frame.NORMAL);
			}
			catch(Exception et)
			{
			}
			eframe.show ();
			eframe.toFront();
			return true;
		}
		return false;
	}

	private void detachCurrentCardPanel ()
	{
		if (currentPanel == null)
			return;

		XMLNode currNode  = (XMLNode)tree.getLastSelectedPathComponent();
		String keyOfPanel = (String) keyTreeList.get ((String)currNode.getAttribute("ID"));//No Internationalisation
		if (keyOfPanel == null)
			return;
		NmsPanel panel = (NmsPanel) panelList.get (keyOfPanel);
		if (panel == null)
			return;
		JFrame eframe = getExternalFrameForName (panel.key ());
		//added by rajasekar_27_05
		if (eframe != null)
		{
			try
			{
				eframe.setState(JFrame.NORMAL);
			}
			catch(NoSuchMethodError noerr)
			{
				//to avoid the NoSuchMethodError seen in Linux	
			}
			
			eframe.show ();
			return;
		}
		removeCardPanel (panel);
		String nodeID = (String)currNode.getAttribute("ID");//No Internationalisation//No Internationalisation
		if(nmsPanelNames.contains(nodeID))
		{
			currentPanelIndex--;
			int size = nmsPanelNames.size();
			int remove = size-(currentPanelIndex+1);
			for(int counter = 0; counter < remove; counter++ )
			{
				nmsPanelNames.removeElementAt(currentPanelIndex+1);//eswaran
			}
			
			currentPanelIndex = (nmsPanelNames.size() - 1);
		}
		selectedTreeNode = getDisplayName(selectedTreeNode);
		showExternalFrame (panel, selectedTreeNode, nodeID,cardPanel.getSize ());
		showPrevCardPanel ();
	}

	private boolean removeCardPanel (NmsPanel panel)
	{
		boolean removed = false;
		Component[] panels = cardPanel.getComponents ();
		for (int i = 0; i < panels.length; i++)
		{
			NmsPanel nmspanel = (NmsPanel) panels[i];
			if (panel.equals (nmspanel))
			{
				cardPanel.remove ((Component) nmspanel);
				//added by rajasekar_27_05
				card.invalidateLayout(cardPanel);
				removed = true;
				break;
			}
		}
		for (int i = openedCardPanelNames.size () - 1; i >= 0; i--)
		{
			String name = (String) openedCardPanelNames.elementAt (i);
			String keyOfPanel = (String) keyTreeList.get (name);
			if (keyOfPanel == null)
				continue;
			NmsPanel nmspanel = (NmsPanel) panelList.get (keyOfPanel);
			if (nmspanel == null)
				continue;
			if (panel.equals (nmspanel))
			{
				openedCardPanelNames.removeElement (name);
			}
		}
		return removed;
	}

	private void showPrevCardPanel ()
	{
		String nodeID = null;
		if (openedCardPanelNames.size () == 0)
		{
			//added by rajasekar_27_05
			cardPanel.repaint();
			setDefaultTreeNodeAndMenuBar ();
			return;
		}
		nodeID = (String) openedCardPanelNames.lastElement ();
		//NEED to analze here !!
		if (nodeID != null)
		{
			selectedTreeNode = nodeID;
			//setSelectedNode (getID(treeName));
			//changePanel (getID(treeName));
			setSelectedNode (nodeID);
			changePanel (nodeID,true);
		}
		else
		{
			//added by rajasekar_27_05
			cardPanel.repaint();
			setDefaultTreeNodeAndMenuBar ();
		}
	}

	private void arrangeInternalFrames (String style)
	{
		// Here the cursor is made busy 
            NmsClientUtil.busyCursor(this);
		Vector uniqueInternalFrames = new Vector ();
		for (Enumeration framesInfo = openedInternalFrames.elements (); framesInfo.hasMoreElements ();)
		{
			Object info[] = (Object[])framesInfo.nextElement ();
			String wname = (String) info[0];
			NmsInternalFrame nmsframe = (NmsInternalFrame) info[1];
			
			//added by rajasekar_27_05
			boolean isOpen = ((Boolean)info[2]).booleanValue();
			if (isOpen && !nmsframe.isIcon())
			{
				if (uniqueInternalFrames.contains (nmsframe))
				{
					uniqueInternalFrames.removeElement (nmsframe);
					uniqueInternalFrames.addElement (nmsframe);
				}
				else
				{
					uniqueInternalFrames.addElement (nmsframe);
				}
			}
		}

		NmsInternalFrame iframe = getCurrentInternalFrame ();
		if (iframe != null && uniqueInternalFrames.contains (iframe))
		{
			uniqueInternalFrames.removeElement (iframe);
			uniqueInternalFrames.addElement (iframe);
		}
		int size = uniqueInternalFrames.size ();
		Dimension dim = desktopPane.getSize ();
		for (int i = 0; i < size ; i++)
		{
			NmsInternalFrame nmsframe = (NmsInternalFrame) uniqueInternalFrames.elementAt (i);
			try
			{
					nmsframe.setMaximum(false);
			}
			catch(Exception e)
			{
			}
		}
			
		if (size > 0)
		{
			if (style.equalsIgnoreCase ("Cascade"))//No Internationalisation//No Internationalisation
			{
				int x = 0, y = 0;
				for (int i = 0; i < size; i++)
				{
					NmsInternalFrame nmsframe = (NmsInternalFrame) uniqueInternalFrames.elementAt (i);
					nmsframe.setSize (dim);
					nmsframe.setLocation (x, y);
					nmsframe.show ();
					x += 25;
					y += 25;
				}
			}
			else
			{
				int x = 0, y = 0, wid = 0, ht = 0;
				
				if (style.equalsIgnoreCase ("TileH"))//No Internationalisation//No Internationalisation
				{
					ht = dim.height / size;
					wid = dim.width;
				}
				else if (style.equalsIgnoreCase ("TileV"))//No Internationalisation//No Internationalisation
				{
					ht = dim.height;
					wid = dim.width / size;
				}
				for (int i = 0; i < size; i++)
				{
					NmsInternalFrame nmsframe = (NmsInternalFrame) uniqueInternalFrames.elementAt (i);
					nmsframe.setBounds (x, y, wid, ht);
					nmsframe.show ();
					
					if (style.equalsIgnoreCase ("TileH"))//No Internationalisation//No Internationalisation
					{
						y += ht;
					}
					else if (style.equalsIgnoreCase ("TileV"))//No Internationalisation//No Internationalisation
					{
						x += wid;
					}
				}
			}
		}
		NmsClientUtil.normalCursor(this);

	}

	public void saveSizeAndLocationOfInternalFrames ()
	{
		String name = "UserName";//No Internationalisation//No Internationalisation
		String value = NmsClientUtil.getUserName ();
		String postdata = URLEncoder.encode (name) + "=" + URLEncoder.encode (value);//No Internationalisation//No Internationalisation
		if (useInternalFrame)
		{	
			JInternalFrame frames[] = desktopPane.getAllFrames ();
			int frameLength = frames.length;
			for (int i = 0; i < frameLength; i++)
			{
				if (!frames[i].isVisible ())
				{
					continue;
				}
				Rectangle d = frames[i].getBounds ();
				name = frames[i].getName ();
				value = d.x + "-" + d.y + "-" + d.width + "-" + d.height;//No Internationalisation//No Internationalisation
				postdata += "&" + URLEncoder.encode (name) + "=" + URLEncoder.encode (value);//No Internationalisation//No Internationalisation
			}
		}
		Rectangle d = frame.getBounds();
		name = frame.getName();
		value = d.x + "-" + d.y + "-" + d.width + "-" + d.height;//No Internationalisation//No Internationalisation
		postdata += "&" + URLEncoder.encode(name) + "=" + URLEncoder.encode (value);//No Internationalisation//No Internationalisation
		name = "TreeViewWidth";//No Internationalisation
		value = (int)treeView.getSize().getWidth()+"";//No Internationalisation
		postdata += "&" + URLEncoder.encode(name) + "=" + URLEncoder.encode (value);//No Internationalisation//No Internationalisation
		name = "TreeViewHeight";//No Internationalisation
		value = (int)treeView.getSize().getHeight()+"";//No Internationalisation
		postdata += "&" + URLEncoder.encode(name) + "=" + URLEncoder.encode (value);//No Internationalisation//No Internationalisation
		name="ToolBar";//No Internationalisation
		if (removetbar == null)
			value="false";//No Internationalisation
		else
		value=removetbar.isSelected()+"";//No Internationalisation
		postdata += "&" + URLEncoder.encode(name) + "=" + URLEncoder.encode (value);//No Internationalisation//No Internationalisation
		//Here we write the size and location using servlets
		try
		{



			URL url = new URL (NmsClientUtil.applet.getDocumentBase (), "../unauthenticatedservlets/com.adventnet.nms.servlets.SaveFrameSizeServlet?" + postdata);//No Internationalisation//No Internationalisation

			InputStream is = url.openStream ();
			// urlc.setDoInput(true);  urlc.setDoOutput(true); 
			// urlc.setRequestProperty("Content-type","application/x-www-form-urlencoded");//No Internationalisation//No Internationalisation
		}
		catch (Exception ex)
		{
			ex.printStackTrace ();
		}
	}

	private int[] getInternalFrameSizeAndLoc (String name)
	{
		int size[] = null;
		String str = (String) internalFramesProp.get (name);
		try
		{
			if (str != null)
			{
				StringTokenizer st = new StringTokenizer (str, "-");//No Internationalisation//No Internationalisation
				size = new int[st.countTokens ()];
				for (int i = 0; st.hasMoreElements (); i++)
				{
					String s = st.nextToken ();
					size[i] = Integer.parseInt (s);
				}
			}
		}
		catch(java.lang.NumberFormatException e)
		{
			size = null;
		}
		return size;
	}

	private boolean isInternalFrameOpenedForTreeNode (String id_of_treenode)
	{
		if (openedInternalFrames == null)
			return false;

		boolean isOpened = false;
		for (Enumeration framesInfo = openedInternalFrames.elements (); framesInfo.hasMoreElements ();)
		{
			Object info[] = (Object[])framesInfo.nextElement ();
			String id = (String) info[3];
			if (id == null)
				continue;
			if (id.equals (id_of_treenode))
			{
				isOpened = ((Boolean) info[2]).booleanValue ();
				break;
			}
		}
		return isOpened;
	}

	private boolean isOpenedInternalFramesContains (String treename)
	{
		if (openedInternalFrames == null)
			return false;

		boolean isContain = false;
		for (Enumeration framesInfo = openedInternalFrames.elements (); framesInfo.hasMoreElements ();)
		{
			Object info[] = (Object[])framesInfo.nextElement ();
			String tname = (String) info[0];
			if (tname == null)
				continue;
			if (tname.equals (treename))
			{
				isContain = true;
				break;
			}
		}
		return isContain;
	}

	public NmsPanel getCurrentNmsPanel ()
	{
		return currentPanel;
	}

	public JFrame getMainParentFrame()
	{
		return frame;
	}
	
	/* Here we are returning the uniqueID to tree Node. It is of the form parent.parent.child. - vidhya.*/ 
	String getUniqueID(String nameoffilter,String parent)
	{
		String nodeID = nameoffilter;
		nodeID = parent+"."+nameoffilter;//No Internationalisation//No Internationalisation
		return nodeID;					
	}    
	// added by rajasekar  
	/* This method will be invoked when the refresh is done to
	clean up the external Frames */
	public void cleanUpAllExternalFrames()
	{
		if (openedExternalFrames == null)
		{
			return;
		}	
		for(Enumeration eframesInfo=openedExternalFrames.elements();eframesInfo.hasMoreElements();)
		{
			Object info[] = (Object[]) eframesInfo.nextElement();
			String fname = (String) info[0];
			((JFrame) info[1]).dispose();
		}
		
	}

	//get the tree shown in the Client
	public JTree getNmsTree ()
	{
		return tree;
	}

	//get the reference of the MenuBar
	public JMenuBar getJMenuBar ()
	{
		return menuBar;
	}

	/**
	 * This method will written the name of the Class that will be
	 * used to display the DataForm whose name should be given as a
	 * parameter <tt>dataFormName</tt>. The <tt>moduleId</tt> of the
	 * module under which the Data Form falls should also be given 
	 * as a parameter. A <i>null</i> will be returned if the method
	 * fails to find a class name for the data form.
	 * 
	 *@param    moduleId The module Id in which the Data Form is present.
	 * 
	 *@param    dataFormName The name of the Data Form.
	 * 
	 *@return   The Class name as which will be used to display the 
	 *          Data Form. The value <b>"default"</b> will be written//No Internationalisation//No Internationalisation
	 *          if there is no user written cusotomizer class name 
	 *          available for the Data Form.
	 *  
	 *@author   Clarence  
	 */
	public String getDataFormClassName (String moduleId, String dataFormName)
	{
		String retVal = null;
		XMLNode xmlnode = (XMLNode) xmlNodes.get (moduleId);
		if (xmlnode != null)
		{
			retVal = (String) xmlnode.getAttribute (dataFormName);
		}
		if (retVal == null)
		{
        	try	
			{
			String id = (String)keyTreeList.get(moduleId);
			NmsPanel nms =(NmsPanel) panelList.get(id);
			if (nms == null)
				return null;
			retVal = getValue(nms.getClass().getName(),dataFormName);
			}
			catch(Exception ex)
			{
				retVal = null;
			}// try catch added for MSP
		}
		return retVal;
	}

	/**
	 * This method returns the main applet reference.
	 */
	public Applet getMainApplet ()
	{
		return applet;
	}
	
	public boolean renameMenuClicked = false;
	public boolean returnRenameMenuClicked()
	{
		return renameMenuClicked;
	}
	
	public void setRenameMenuClicked(boolean flag)
	{
		renameMenuClicked = flag;
	}
	public void setTreeEditing()
	{
		renameMenuClicked = true;
		tree.startEditingAtPath(tree.getSelectionPath());

	}
	private String getDisplayName(String str)
	{	
		// changes to support openframe and openpanel
		if (str == null)
		{
			return "";//No Internationalisation//No Internationalisation
		}
		return (String) NmsCustomPanel.typeVsName.get(str);
	}
	
	//The following code is used to update the window menu with the selected tree path	
	private String getSelectedPath()
	{
		TreePath[] treePaths = tree.getSelectionPaths();
		if(treePaths != null )
		{
			for(int i =0; i< treePaths.length; i++ )
			{
				if( tree.isPathSelected(treePaths[i]))
				{
					TreePath path = treePaths[i];
					if( path != null)
					{
                                             String menuString = getPathString((XMLNode)(path.getPathComponent(path.getPathCount()-1)));
                                             return getParsedString(menuString);
				             //return getPathString((XMLNode)(path.getPathComponent(path.getPathCount()-1)));		
					}
					else 
						return null;
				}
			}
		}
		return null;
	}
	
	private String getPathString(XMLNode node)
	{
		if( node == null ) return null;
		
		XMLNode parent = node.getParentNode();
		String treeName =(String) node.getAttribute("TREE-NAME");
		if( treeName == null || treeName.equals("") )
		{
			treeName =(String) node.getAttribute("ID");
		}
		
		if(parent == null ) return NmsClientUtil.GetString(treeName);		
		return getPathString(parent)+ ","+ NmsClientUtil.GetString(treeName);
	}
	
    private String getParsedString(String menuString)
    {
        String pathString = "";
        Vector path = new Vector();
        StringTokenizer tokenizer = new StringTokenizer(menuString, ",");
        int size = tokenizer.countTokens();
        for(int i = 0; i < size; i++)
        {
            path.add(tokenizer.nextToken());
        }        
        if(menuCustomizer == null)
        {
            menuCustomizer = getMenuCustomizerInstance();
            if(menuCustomizer == null)
                pathString = menuString;
        }
        else
            pathString = menuCustomizer.getPathString(path);

        return pathString;        
    }        

    private WindowMenuCustomizer getMenuCustomizerInstance()
    {

        if(actionValue == null)
            return null;

        int index = actionValue.indexOf(":");
        String className;
        if(index != -1)
            className = actionValue.substring(index+1, actionValue.indexOf("?"));
        else
            className = actionValue;

        index = actionValue.indexOf("?");
        Properties pp = new Properties();
        if(index != -1)
        {
            String params = actionValue.substring(index+1);
            StringTokenizer stkn = new StringTokenizer(params, ",");
            int tokenSize = stkn.countTokens();
            for(int i=0; i < tokenSize; i++)
            {
                String keyValue = stkn.nextToken();
                String key = keyValue.substring(0, keyValue.indexOf("="));
                String value = keyValue.substring(keyValue.indexOf("=")+1);
                pp.put(key , value);
            }
        }

        Object obj = null;
        try
        {
            Class classToInvoke = Class.forName(className);
            Class  clsArr[] = new Class[] { Properties.class };
            Object objArr[] = new Object[] {pp};
            Constructor cons = null;
            try
            {
                cons = classToInvoke.getConstructor(clsArr);
            }
            catch(NoSuchMethodException ee){}

            if(cons != null)
                obj = cons.newInstance(objArr);
            else
                obj = classToInvoke.newInstance(); 
        }
        catch(Exception ex)
        {
            System.out.println(NmsClientUtil.GetString("Exception while getting menu string")+" "+ex);
        }
        return (WindowMenuCustomizer)obj;
    }

	public String getID(String str)
	{
		// changes to support openframe and openpanel
		if (str == null || str.equals(""))//No Internationalisation//No Internationalisation
		{
			return "";//No Internationalisation//No Internationalisation
		}
		if (str.equals(getDisplayName(str))) 
		{
			return str;
		}	
		
		for(Enumeration eframesInfo=openedInternalFrames.elements();eframesInfo.hasMoreElements();)
		{
			Object info[] = (Object[]) eframesInfo.nextElement();
			String fname = (String) info[0];
			if (str.equals(fname))
			{
				return (String)info[3];
			}
		}
		return null;
	}
	
	
	public Vector returnmenusFromBegining()
	{
		return menusFromBegining;
	}
	public Vector returnmenusFromEnd()
	{
		return menusFromEnd;
	}
	
	public String getImageIcon(String id)
	{
		XMLNode node = (XMLNode)xmlNodes.get(id);
		return (String)node.getAttribute ("ICON-FILE");//No Internationalisation//No Internationalisation
	}

	private boolean doCleanUpOnDelete(Object source, String treeLabel,String panelKey)
	{
		String keyofpanel = (String) keyTreeList.get(treeLabel);  
		if (keyofpanel == null)
			return false;

		NmsPanel panel = (NmsPanel)panelList.get(keyofpanel);
		if (panel instanceof MapApplet)
		{	
			doCleanUp(keyofpanel,"DISPOSE_ON_CLOSE",null);
		}
		return true;
	}

	public String getTreeName(String id)
	{
		XMLNode node = (XMLNode)xmlNodes.get(id);
		String treeName =  (String)node.getAttribute ("TREE-NAME");//No Internationalisation//No Internationalisation
		if(treeName == null)
		{
			treeName = id;
		}
		return treeName;
	}

	private void moveTreeNode(String nodeNameToBeMoved, String newParentNodeName,int index,String tobeselected)
	{
		XMLNode rootNode = deviceDB.getRootNode();
		XMLNode nodeToBeMoved = checkIfBelongs(rootNode,nodeNameToBeMoved);
		XMLNode nodeToBeMovedTo = checkIfBelongs(rootNode,newParentNodeName);

		if (nodeToBeMoved == null || nodeToBeMovedTo == null)
		{
			//System.err.println(NmsClientUtil.GetString("Cannot move node "));
			//System.err.println(NmsClientUtil.GetString("NodeToBeMoved = ") + nodeToBeMoved + "\t NodeToBeMovedTo = " + nodeToBeMovedTo);//No Internationalisation
			return;
		}

		XMLNode parentNode = nodeToBeMoved.getParentNode();
		if (parentNode != null)
		{
			String oldParentNodeName = (String) parentNode.getAttribute("ID");//No Internationalisation
			if (newParentNodeName.equals(oldParentNodeName))
			{
				//No change in parent map
				return;
			}
			
			//Changes for Tree Node disabling
			if(! getAuthentication(nodeToBeMoved))
			{
				return ;
			}	
			
			//Changes for Tree Node disabling
			if(! getAuthentication( nodeToBeMovedTo))
			{
				return ;
			}	
			
			parentNode.deleteChildNode(nodeToBeMoved);
			if (treeModel != null)
			{
                                XMLNode[] tempComponents = treeModel.getPathToRoot(parentNode);
                                TreePath currentTreePath = new TreePath (tempComponents);
                                Enumeration expPth = tree.getExpandedDescendants(currentTreePath);
				ignoreValueChanged = true;
				treeModel.reload (parentNode);
				ignoreValueChanged = false;
                                if (expPth != null)
                                {       
                                    while (expPth.hasMoreElements()) 
                                    {     
                                         tree.expandPath((TreePath)expPth.nextElement());
                                     }
                                }
			}
		}

		if( (index >= 0)  && (index < nodeToBeMovedTo.getChildCount()))
		{
			nodeToBeMovedTo.insertChildNode(nodeToBeMoved,index);
		}
		else
			nodeToBeMovedTo.addChildNode(nodeToBeMoved);
			
		if (treeModel != null)
		{
                        XMLNode[] tempComponents = treeModel.getPathToRoot(parentNode);
                        TreePath currentTreePath = new TreePath (tempComponents);
                        Enumeration expPth = tree.getExpandedDescendants(currentTreePath);
			ignoreValueChanged = true;
			treeModel.reload (nodeToBeMovedTo);
			ignoreValueChanged = false;
                        if (expPth != null)
                        {       
                            while (expPth.hasMoreElements()) 
                            {     
                                 tree.expandPath((TreePath)expPth.nextElement());
                            }
                        }
		}
		if (tobeselected != null)
		{
			setSelectedNode(tobeselected,false);
		}
		else
		{
			setSelectedNode(selectedTreeNode,true);
		}
	}

	private void setToolBar(NmsPanel nmspanel)
	{
		if (panelkeyVsToolbars == null)
		{
			// window menu is updated in case when toolbar is removed
			updateWindowMenu();
			return;
		}	
		String toolbar_key =  null;
		if (!(nmspanel instanceof MapApplet))
		{
			if (nmspanel != null)
				toolbar_key = nmspanel.key();
			else
				if(panelkeyVsToolbars.containsKey("Default"))//No Internationalisation
				toolbar_key= "Default";//No Internationalisation
		}
		else  
		{
			toolbar_key = "MapApplet";	//No Internationalisation
		}
		toolbarPanel.removeAll();
		if(panelkeyVsToolbars != null)
		{
                    if(toolbar_key != null)
                    {
			if(panelkeyVsToolbars.containsKey(toolbar_key))
				tbar = (ToolBar) panelkeyVsToolbars.get (toolbar_key);
			else
				tbar = null;
                    }
		}
		else  tbar = null;
		if (tbar == null)
			if(panelkeyVsToolbars.containsKey("Default"))//No Internationalisation
				tbar = (ToolBar) panelkeyVsToolbars.get ("Default");//No Internationalisation//No Internationalisation
		if(tbar != null)
		{
			if (nmspanel != null)
				tbar.enableIcon(NmsClientUtil.GetString("Detach"));
			tbar.addActionListener (nmspanel);		
			int size = nmsPanelNames.size ();
			if (currentPanelIndex <= 0)
			{
				tbar.disableIcon(NmsClientUtil.GetString("Back"));
				tbar.enableIcon(NmsClientUtil.GetString("Forward"));
			if (size <= 1)
				tbar.disableIcon(NmsClientUtil.GetString("Forward"));
			}
			else if ((currentPanelIndex >= (size-1))|| (currentPanelIndex >(size-1)))
			{
				tbar.disableIcon(NmsClientUtil.GetString("Forward"));
				tbar.enableIcon(NmsClientUtil.GetString("Back"));	
			}
			else
			{
				tbar.enableIcon(NmsClientUtil.GetString("Back"));
				tbar.enableIcon(NmsClientUtil.GetString("Forward"));	
			}
                    if(removetbar == null)
                    {
                        removetbar = new JCheckBox(toremove);
                        removetbar.setActionCommand("HideToolbar");//No Internationalisation
                        removetbar.setPreferredSize(new Dimension(9,40));
                        removetbar.setSelected(true);
                        removetbar.setToolTipText(NmsClientUtil.GetString("Hide Toolbar"));
                        toolbarPanel.add(removetbar,BorderLayout.WEST);
                        toolbarPanel.add(tbar, BorderLayout.CENTER);
                        toolbarPanel.add(logoLabel, BorderLayout.EAST);		
                        logoLabel.addMouseListener(mouseListener);
                        removetbar.addMouseListener(mouseListener);
                        add (toolbarPanel, BorderLayout.NORTH);
                        toolbarPanel.revalidate();     
                        toolbarPanel.invalidate();
                    }
                    if(removetbar.getActionCommand()=="HideToolbar")//No Internationalisation
                    {
                        toolbarPanel.add(tbar, BorderLayout.CENTER);
                        toolbarPanel.add(logoLabel, BorderLayout.EAST);		
                        removetbar.setToolTipText(NmsClientUtil.GetString("Hide Toolbar"));
                    }
                    else
                    {
                        removetbar.setPreferredSize(new Dimension(60,8));
                    }
                    toolbarPanel.add(removetbar,BorderLayout.WEST);
                    add (toolbarPanel, BorderLayout.NORTH);
                    //toolbarPanel.revalidate();     
                    //toolbarPanel.invalidate();
                     toolbarPanel.repaint();   
                }
                updateWindowMenu();
        }

	private boolean renameTheNode(String nodeID1, String newname)//, XMLNode renameNode, XMLNode selectedNode1, NmsPanel panel1)
	{
		if( nodeID1 == null)
		{
			return false;
		}
		String treeCellEditorValue = newname;
		//XMLNode selectedNode = selectedNode1;
		//XMLNode xmlnode = renameNode;
		//String  currNodeName = (String)selectedNode.getAttribute("ID");//No Internationalisation
		String nodeID  = nodeID1;
		String oldDisplayName = (String)NmsCustomPanel.typeVsName.get(nodeID);		
		//new Display name 

        XMLNode selectedNode = (XMLNode) xmlNodes.get(nodeID);
		
		if( selectedNode == null )
			return false;
			
		XMLNode parent = selectedNode.getParentNode();

		if( checkTreeName(parent,nodeID,treeCellEditorValue))
		{
			tree.stopEditing();		
			NmsClientUtil.showError(NmsClientUtil.GetString("Tree node with name ")+treeCellEditorValue+" "+NmsClientUtil.GetString("already exists at the same level in the tree .\n"+NmsClientUtil.GetString("Tree nodes with the same name can exist only at different levels.")));
			return false;
		}
		String panelkey = (String)keyTreeList.get(nodeID);
		if (panelkey == null)
			return false;

		//if alright then go ahead and change the name 
		NmsPanel panel = (NmsPanel)panelList.get(panelkey);
		if(panel instanceof AbstractBaseNmsPanel)
		{
			if( ((AbstractBaseNmsPanel)panel).renameTreeNode(nodeID,treeCellEditorValue))
			{
				Hashtable newHash = new Hashtable();
				newHash.put("ID",nodeID);//No Internationalisation
				newHash.put("TREE-NAME",treeCellEditorValue);//No Internationalisation
				changeTreeNodeProperties(newHash,panel);
			}
			tree.stopEditing();
		}
		return true;
	}
	
	public boolean renameTreeFromAPI(String cvID, String newTreeName)
	{
				
		XMLNode rootNode = deviceDB.getRootNode();
		XMLNode node = checkIfBelongs(rootNode,cvID);
		cvID = cvID.trim();
		//get the NmsPanel and send....
		//InitializePanel(cvID);
		String panelKey    = (String)keyTreeList.get(cvID);
		//incases where panel is not present. Say 'WebNMS-Panels'
		if(panelKey == null )
		{
			System.out.println(NmsClientUtil.GetString("No Panel registered to handle this request ") + cvID);
			return false;
		}
		NmsPanel panel = (NmsPanel) panelList.get (panelKey);
		if(panel == null)
		{
			System.out.println(NmsClientUtil.GetString("No Panel registered to handle this request ") + panelKey);
			return false;
		}

		return renameTheNode(cvID,newTreeName);
	}

	
	public boolean createAndAddXMLNodes(XMLNode parentNode, XMLNode childNode , String panelkey, String tobeselected, String id)
	{
		return createAndAddXMLNodes(parentNode,childNode,panelkey,tobeselected,id,false);
	}
	
    public boolean createAndAddXMLNodes(XMLNode parentNode, XMLNode childNode , String panelkey, String tobeselected, String id,boolean ignoreValue)
    {
        if (panelkey == null)
            return false;
        try
            {
                if (parentNode != null)
                    {
                        XMLNode[] tempComponents = treeModel.getPathToRoot(parentNode);
                        TreePath currentTreePath = new TreePath (tempComponents);
                        Enumeration expPth = tree.getExpandedDescendants(currentTreePath);
                        ignoreValueChanged = true;
                        if (treeModel != null)
                            treeModel.reload (parentNode);
                        ignoreValueChanged = false;
                        if (expPth != null)
                        {       
                            while (expPth.hasMoreElements()) 
                            {     
                                 tree.expandPath((TreePath)expPth.nextElement());
                            }
                        }
                    }
                ignoreValueChanged = ignoreValue;
                if(childNode == null)
                    return false;
                // this for getting the display name from the xml node attributes 	
                String displayName =(String)childNode.getAttribute("FRAME-TITLE");//No Internationalisation
                if(displayName == null || displayName.trim().equals(""))//No Internationalisation
                    {
                        displayName = (String)childNode.getAttribute("TREE-NAME");//No Internationalisation
                    }
				
                if (tobeselected != null)
                    {
                        // added by rajasekar 10_05_2000
                        JFrame exFrame = getExternalFrameForName (panelkey);

					
                        if (exFrame != null)
                            {
                                exFrame.setTitle(NmsClientUtil.GetString(displayName));
                                NmsInternalFrame nmsframe=getInternalFrameFromDesktopPane(panelkey);
                                if (!isInternalFrameOpenedForTreeNode(id))  
                                    {
                                        addToOpenedInternalFrames(getSelectedPath()+"."+childNode.getAttribute("TREE-NAME"),nmsframe,false,id);//No Internationalisation
                                    }
                                updateOpenedExternalFrames(exFrame,id);
                                try
                                    {
                                        exFrame.setState(Frame.NORMAL);
                                    }
                                catch(Exception et)
                                    {
                                    }
                                exFrame.toFront();
                                exFrame.requestFocus();
						
                                return true ;
                            }
					
                        if (!panelkey.equals("MapApplet"))//No Internationalisation
                            {
                                if (useInternalFrame)
                                    {
                                        NmsInternalFrame nmsframe = getInternalFrameFromDesktopPane(panelkey);//getCurrentInternalFrame();  
                                        if (nmsframe == null)
                                            {
                                                InitializePanel(id);
                                                nmsframe=getInternalFrameFromDesktopPane(panelkey);
                                            }
                                        if (nmsframe != null)
                                            if (!isInternalFrameOpenedForTreeNode(id)) 
                                                {
                                                    addToOpenedInternalFrames(getSelectedPath()+"."+childNode.getAttribute("TREE-NAME"),nmsframe,true,id);//No Internationalisation
                                                }
                                        //added by rajasekar_27_05
                                        if (nmsframe != null)
                                            {
                                                nmsframe.setID(id);
                                                nmsframe.setTitle(NmsClientUtil.GetString(displayName));                      
                                                nmsframe.setSelected(true);
                                                storeState (tobeselected);
                                                //Altamar changes -- Added the following line. for the isSelected method to work fine.
                                                nmsframe.show();
                                            }
                                    }
						
                            }

                        if (ignoreValueChanged)
                            currentNodeId = id;

                        setSelectedNode (tobeselected,ignoreValueChanged );
                        ignoreValueChanged = false;
                        selectedTreeNode = tobeselected;         
                        updateWindowMenu();	
                    }
            }
        catch (Exception ee)
            {
                System.err.println (NmsClientUtil.GetString("exception adding to tree View ") + ee);
                ee.printStackTrace ();
                ignoreValueChanged = false;
                return false;
            }
        return true;

    }
    public TreeRenderer getTreeCellRenderer()
	{
		return treeCellRenderer;
	}
	private boolean createNmsPanel(String createPanelWithThisKey)
	{
		Vector nmsPanelList = NmsClientUtil.nmsPanelList;
		int panelListSize   = nmsPanelList.size ();
		Vector knownPanelNames = new Vector();
		Vector knowPanelIds = new Vector();
		knownPanelNames.addElement("EventBrowser");
		knownPanelNames.addElement("AlertApplet");
		knownPanelNames.addElement("NmsListView");
		knownPanelNames.addElement("StatsAdminPanel");
		knowPanelIds.addElement("Events");
		knowPanelIds.addElement("Alerts");
		knowPanelIds.addElement("Network Database");
                knowPanelIds.addElement("Performance");


		for (int i = 0; i < panelListSize; i++)
		{
			NmsPanel nmsPanel;
			String keyOfPanel = null;
			String init_on_startup = null;
			String panelkey = null;
			String action = null;
			String panelClassName = null;
			XMLNode nmsxmlNode = null;
			nmsxmlNode  = (XMLNode)nmsPanelList.elementAt (i);
			panelClassName = (String)nmsxmlNode.getAttribute("className");//No Internationalisation
			
			if( panelClassName != null)
				nmsxmlNode.setAttribute("PANEL-NAME",panelClassName);//No Internationalisation

			init_on_startup = (String)nmsxmlNode.getAttribute("INIT-ON-STARTUP");//No Internationalisation
			panelkey = (String)nmsxmlNode.getAttribute("PANEL-KEY");//No Internationalisation
			action = (String)nmsxmlNode.getAttribute("DEFAULT-CLOSE-OPERATION");//No Internationalisation

			if ((panelkey == null) && (init_on_startup != null))
			{
				if (createPanelWithThisKey == null)
				{
					
					System.err.println(NmsClientUtil.GetString("panelkey is not specified for the panel ") + panelClassName);
					System.out.println(NmsClientUtil.GetString(" The attribute INIT-ON-STARTUP is taken as true"));
					init_on_startup = null;
				}
				else
					return false;
			}
			if ((panelkey == null) && (action != null))
			{
				if (createPanelWithThisKey == null)
				{
					System.err.println(NmsClientUtil.GetString("panelkey is not specified for the panel ") + panelClassName);
					System.out.println(NmsClientUtil.GetString(" The attribute DEFAULT-CLOSE-OPERATION is taken as HIDE_ONLY"));
				}
				else
					return false;
			}
			if (action == null)
				action = "HIDE_ONLY";//No Internationalisation
			if (init_on_startup == null)
			{
				init_on_startup = "true";//No Internationalisation
			}
			if (panelkey != null && panelkey.equalsIgnoreCase(createPanelWithThisKey))
			{
				init_on_startup = "TRUE";//No Internationalisation
				nmsxmlNode.setAttribute("INIT-ON-STARTUP",init_on_startup);//No Internationalisation
				
			}
			if (init_on_startup.equalsIgnoreCase("TRUE"))// && (!(initializedList.containsKey(panelClassName))) )//No Internationalisation
			{
				if ((createPanelWithThisKey == null) || (panelkey != null && panelkey.equals(createPanelWithThisKey)))
				{	
					if(progressbar!= null)
						incrementProgressBarValue();
					try
					{
						Class panelClass = Class.forName (panelClassName);
						Object panelInstance = panelClass.newInstance ();
						nmsPanel = (NmsPanel) panelInstance;
						nmsPanel.addNmsPanelEventListener (this);
						addPanelToList (nmsPanel,true,panelkey,true,false);

						keyOfPanel = nmsPanel.key ();
						if (keyOfPanel == null)
						{
							if (createPanelWithThisKey == null)
								continue;
							else
								return false;

						}
						String panelName = panelClass.toString();
						int knownPanelsSize = knownPanelNames.size();
						for (int index = 0; index < knownPanelsSize; index++)
						{
							String check = (String) knownPanelNames.elementAt(index);
							String id    = (String) knowPanelIds.elementAt(index);
							if (panelName.indexOf(check) != -1)
							{
								moduleIds.put(id,keyOfPanel);
							}
						}
						initializedList.put (panelClassName, keyOfPanel);
						if (createPanelWithThisKey != null)
							return true;
					}
					catch (Exception e)
					{
						System.err.println (NmsClientUtil.GetString("Error occured while creating instance for NmsPanel : ") + panelClassName);
						e.printStackTrace ();
						if (createPanelWithThisKey == null)
						{
							continue;
						}
						else
							return false;
					}
				}
			}
		}
		return false;
	}

	private void doCleanUp(String id,String actionVal,Properties prop)
	{
		boolean proceed = false;
		String action = null;
		if (actionVal != null)
		{
			action = actionVal;
			proceed = true;
		}
		else
			action = getTheValue(id,"DEFAULT-CLOSE-OPERATION",null);//No Internationalisation
		if (action == null)
			action = "HIDE_ON_CLOSE";//No Internationalisation
		String panelKey = (String)keyTreeList.get(id);
		if (panelKey == null)
		{
			panelKey = id;
			if (panelKey.indexOf(".netmap") == -1)
			{
			String panelname = getValue(id,"className","PANEL-KEY");
                	if(panelname == null)
                    	action = "HIDE_ON_CLOSE";
			}

		}
		NmsInternalFrame frame = getInternalFrameFromDesktopPane(panelKey);
		if (frame == null)
		{
			return;
		}
                if (!panelKey.equals(id))
                {
		        if (!frame.getID().equals(id))
		        {
			        return;
		        }
                }
		NmsPanel xpanel = (NmsPanel)panelList.get(panelKey);
		if (xpanel == null)
		{
			System.out.println(NmsClientUtil.GetString("No Panel registered to handle this request ") + id);
			return;
		}
		String panelName = panelKey;
		if ( ("Maps".equals(id)) || (action == null) || (xpanel instanceof NmsCustomPanel))
			action = "HIDE_ON_CLOSE";//No Internationalisation
		Vector validate = new Vector();
		validate.addElement("DO_NOTHING_ON_CLOSE");
		validate.addElement("HIDE_ON_CLOSE");
		validate.addElement("DISPOSE_ON_CLOSE");
		validate.addElement("DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE");
		validate.addElement("DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE");
		if (!validate.contains(action))
			action = "HIDE_ON_CLOSE";
		String setAction = "DO_NOTHING_ON_CLOSE";//No Internationalisation
		int setActionValue = JInternalFrame.DO_NOTHING_ON_CLOSE;
		if(action.equals("HIDE_ON_CLOSE"))//No Internationalisation
		{
			setAction = "HIDE_ON_CLOSE";//No Internationalisation
			setActionValue = JInternalFrame.HIDE_ON_CLOSE;
		}
		else if(action.equals("DISPOSE_ON_CLOSE")) //No Internationalisation
		{
			setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
			setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
		}
		else if (action.equals("DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE"))//No Internationalisation
		{
			setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
			setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
		}
		else if (action.equals("DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE"))//No Internationalisation
		{
			setAction = "DISPOSE_ON_CLOSE";//No Internationalisation
			setActionValue = JInternalFrame.DISPOSE_ON_CLOSE;
		}
		/*int oldValue = fra me.getDefaultCloseOperation();
		if ((oldValue != setActionValue)&& (prop == null))
		{ 
			return; 
			}*/
		frame.setDefaultCloseOperation (setActionValue);
		if(action.equals("DO_NOTHING_ON_CLOSE"))//No Internationalisation
		{
			return;
		}
		else if(action.equals("HIDE_ON_CLOSE"))//No Internationalisation
		{
			boolean attach_back = true;
			if (prop != null)
			{
				String attach_on_close = prop.getProperty("ATTACH-ON-CLOSE");
				if (attach_on_close != null)
					attach_back = (attach_on_close.equals("true"));
			}
			xpanel.stop();
			if (useInternalFrame)
			{
				if (proceed)
				{
					JFrame exFrame = getExternalFrameForName (panelName); 
					if (exFrame != null)
					{
						removeExternalFrame(exFrame,false,attach_back);
					}
					else
					{
						NmsInternalFrame nmsframe = getInternalFrameFromDesktopPane(panelName);
						removeInternalFrame (nmsframe, false);
						showPrevInternalFrame(id);
					}
				}
				if (useInternalFrame)
					updateWindowMenu();
				return;
			}
			if( xpanel != null)
			{
				boolean selectPreviousPanel = false;
				if(xpanel == getCurrentNmsPanel() )
					selectPreviousPanel=true;

				removeCardPanel(xpanel);
				if(selectPreviousPanel)
					showPrevCardPanel ();
			}
		}
		else if(action.equals("DISPOSE_ON_CLOSE")) //No Internationalisation
		{
			xpanel.stop();
			xpanel.destroy(action);
		}
		else if (action.equals("DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE"))//No Internationalisation
		{
			xpanel.stop();
			xpanel.destroy(action);
		}
		else if (action.equals("DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE"))
		{
			xpanel.stop();
			xpanel.destroy(action);
		}
		JFrame exFrame = getExternalFrameForName (panelName); 
		if (exFrame != null)
		{
			removeExternalFrame(exFrame,true,false);
		}
		else
		{
			if (useInternalFrame)
			{
				NmsInternalFrame nmsframe = getInternalFrameFromDesktopPane(panelName);
				if (xpanel instanceof javax.swing.event.InternalFrameListener)
					nmsframe.removeInternalFrameListener((javax.swing.event.InternalFrameListener)xpanel);


						// storing the state 
				if (action.equals("DISPOSE_ON_CLOSE"))
				{
					Rectangle d = nmsframe.getBounds ();
					String name = nmsframe.getName ();
					String value = d.x + "-" + d.y + "-" + d.width + "-" + d.height;//No Internationalisation//No Internationalisation     
					internalFramesProp.setProperty(name,value);
				}

				removeInternalFrame (nmsframe, true);
				if (proceed)
				{
					showPrevInternalFrame(id);
				}
			}
			else
			{
				boolean selectPreviousPanel = false;
				if (xpanel == getCurrentNmsPanel() )
					selectPreviousPanel=true;
				if( xpanel != null)
				{
					removeCardPanel(xpanel);
					if(selectPreviousPanel && proceed)
						showPrevCardPanel ();
				}
			}
		}
		//panelList.remove(panelName);
		removePanelFromList(panelName);
		if (tbar != null)
		tbar.removeActionListener (xpanel);
                
		
		xpanel = null;
		if(action.equals("DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE"))//No Internationalisation
		{
			Vector v= getFiltersToBeRemoved(id);
			if (v.size() == 0)
			{
				return;
			}
			Hashtable addhash = new Hashtable();
			addhash.put("removeleafs",v);//No Internationalisation//No Internationalisation
			addhash.put("panelkey",panelName);//No Internationalisation//No Internationalisation
			XMLNode temp = (XMLNode)currentComponents[0];
			addhash.put("parent",(String)temp.getAttribute("ID"));//No Internationalisation//No Internationalisation
			Event event = new Event(this,NmsPanel.REMOVE_LEAF_FROM_MENU,addhash);
			NmsPanelEvent nmsevt = new NmsPanelEvent(event);
			handleNmsPanelEvent(nmsevt);

		}
		if (action.equals("DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE"))//No Internationalisation
		{
			NmsUiAPI.removeTreeNode(id,null,false);
		}
		if (id.indexOf(".netmap") == -1)
		{
			xmlNodes.remove(id);
			for (Enumeration en = keyTreeList.keys (); en.hasMoreElements ();)
			{
				String treenam = (String) en.nextElement ();
				String correspondingPanel = (String) keyTreeList.get (treenam);
				if (correspondingPanel.equals (panelName))
				{
					xmlNodes.remove(treenam);
				}
			}
		}
                if (panelKey.equals(id))
                {
                    for (Enumeration en = keyTreeList.keys (); en.hasMoreElements ();)
			{
				String treenam = (String) en.nextElement ();
				String correspondingPanel = (String) keyTreeList.get (treenam);
				if (correspondingPanel.equals (panelName))
				{
                                    keyTreeList.remove(treenam);
                                }
                        }
                }
		if (useInternalFrame)
		updateWindowMenu();	
	}
	private void InitializePanel(String nodeID)
	{
		XMLNode topmost = (XMLNode) currentComponents[0];
		XMLNode noder = checkIfBelongs (topmost, nodeID);
		boolean checkSecConditon = true;
		if (noder == null)
		{
			if (!keyTreeList.containsValue(nodeID))
			{
				String panelName = getValue(nodeID,"className","PANEL-KEY");//No Internationalisation
				if ((panelName == null) && (panelList.get(nodeID) == null))
				{
					System.out.println(NmsClientUtil.GetString("The specified panekey is not correct ") + nodeID);
					return;
				}
				createNmsPanel(nodeID);
			}
		}
		else
		{	
			if (xmlNodes.get(nodeID) == null)
			{
				noder.setAttribute("INIT-ON-STARTUP","true");//No Internationalisation
				initXMLNodeParms(noder);
			}
			String panelClassName = (String)noder.getAttribute ("PANEL-NAME");//No Internationalisation//No Internationalisation
			
			if (panelClassName != null)
			{
				//if (!initializedList.containsKey (panelClassName))
				//{
					if (panelClassName.indexOf("MapApplet") != -1)//No Internationalisation
					{
						//initializedList.put(panelClassName,"");//No Internationalisation
						//return;
					}
			}
		}
		String panelKey = null;
		if (noder != null)
		{
			panelKey = (String) keyTreeList.get(nodeID);
		}
		else
		{
			panelKey = nodeID;
			for (Enumeration en = keyTreeList.keys (); en.hasMoreElements ();)
			{
				String treenam = (String) en.nextElement ();
				String correspondingPanel = (String) keyTreeList.get (treenam);
				if (correspondingPanel.equals (nodeID))
				{
					nodeID = treenam;
					break;
				}
			}
		}
		if (panelKey == null)
		{
			return;
		}
		if (!createdPanels.contains(panelKey))
		{
			NmsPanel panel = (NmsPanel) panelList.get(panelKey);
			if (!(panel instanceof MapApplet))
			addPanelToList (panel, false,nodeID,false,true);
			if (nodeID.equals("Maps") || ( (!openMultipleMaps) && (panel instanceof MapApplet)) )//No Internationalisation
			{
				addPanelToList (panel, false,nodeID,false,true);
			}
		}
	}

	private String getValue(String compare_value,String index)
	{
		return getValue(compare_value,index,null);
	}
	private String getValue(String compare_value,String index,String compareWithThiskey)
	{
		Vector nmsPanelList = NmsClientUtil.nmsPanelList;
		int panelListSize   = nmsPanelList.size ();
		String value =null; 
		String panelClassName = null;
		for (int i = 0; i < panelListSize; i++)
		{
			XMLNode nmspanelNode = (XMLNode)nmsPanelList.elementAt(i);
			if (compareWithThiskey == null)
				panelClassName =(String)nmspanelNode.getAttribute("className");//No Internationalisation
			else 
				panelClassName =(String)nmspanelNode.getAttribute(compareWithThiskey);
			value = (String)nmspanelNode.getAttribute(index);
			if (compare_value.equals(panelClassName))
			{
				if (index.equals("all") && value == null)//No Internationalisation
				{
					String returnStr = "";//No Internationalisation
					Hashtable ht = nmspanelNode.getAttributeList();
					for (Enumeration e = ht.keys(); e.hasMoreElements();)
					{
						String key = (String)e.nextElement();
						String value1 = (String)ht.get(key);
						returnStr = returnStr + key + "=" + value1 + ":";//No Internationalisation
					}
					return returnStr;
				}
				return value;
			}
		}
		return null;
	}
	
	private String  getPanelClassName( String panelKey)
	{
		if(panelKey == null ) return null;
		for( Enumeration e = initializedList.keys(); e.hasMoreElements();)
		{
			String tempKey =(String)e.nextElement();
			if( panelKey .equals((String)initializedList.get(tempKey)))
				return tempKey;
		}
		return null;
	}
	
	private Properties getProps(String str)
	{
		// changes to support openframe and openpanel
		if (!useInternalFrame)
			return null;
		if (str == null || str.equals(""))//No Internationalisation//No Internationalisation
		{
			return null;//No Internationalisation
		}
		for(Enumeration eframesInfo=openedInternalFrames.elements();eframesInfo.hasMoreElements();)
		{
			Object info[] = (Object[]) eframesInfo.nextElement();
			String fname = (String) info[3];
			Properties p = (Properties) info[4];
			if (str.equals(fname))
			{
				return p;
			}
		}
		return null;
	}

	private  Properties parseAndGive(String str)
	{ 
		Properties prop = new Properties();
		
		if(str != null)
		{
			StringTokenizer string = new StringTokenizer(str,":");//No Internationalisation

			while(string.hasMoreTokens())
			{
				String token = string.nextToken();
					
				int seperatorIndex = token.indexOf('=');

				if(seperatorIndex != -1)
				{
					String key = token.substring(0, seperatorIndex);
					String value = token.substring(seperatorIndex + 1);
					prop.put(key.trim(),value.trim());
				}
			}
		}
		return prop;
	}

	private void setJMenuBar(JFrame frame , JMenuBar menuBar)
	{
           try
           {
		SwingUtilities.updateComponentTreeUI (menuBar);
		frame.setJMenuBar(menuBar);
           }
           catch(Exception exp){}
	}

	private String getTheValue(String id,String attribname,XMLNode xmlNode)
	{
		attribname = attribname.trim();
		XMLNode tempxml = null;
		String value = null;
		String panelkey = null;
		String panelName = null;
		if (id != null)
		{
			if (xmlNode != null)
				tempxml = xmlNode;
			else
				tempxml = (XMLNode)xmlNodes.get(id);
			// First searching in the respectinve xmlnode
			if (tempxml != null)
			{
				value = (String)tempxml.getAttribute(attribname);
				panelkey = (String) tempxml.getAttribute("PANEL-KEY");//No Internationalisation
				panelName = (String) tempxml.getAttribute("PANEL-NAME");//No Internationalisation
			}
			else
			{
				// This is for NmsPanel.conf entries which do not have corresponding node in th tree
				panelkey = id;
			}
		}
		// If not present then take it from common attribute.
		if (value == null) 
		{
			String commVal = null;
			if (panelkey == null)
			{
				if (panelName == null)
					return null;
				commVal  = getValue(panelName,attribname);
			}
			else
			{
				commVal = getValue(panelkey,attribname,"PANEL-KEY");//No Internationalisation
			}
					
			if (commVal != null)
				value = commVal;
		}
		return value;
	}
	private	boolean checkTreeName(XMLNode parent , String filterName, String treeName)
	{
		if (parent == null || treeName == null)
			return false;
		
		Vector children = parent.getChildNodes ();
		if ((children == null) || (children.size () == 0))
			return false; // tree name should be allowed to add the node
		for (int i = 0; i < children.size (); i++)
		{
			XMLNode child = (XMLNode) children.elementAt (i);

			String temp = (String) child.getAttribute("TREE-NAME");//No Internationalisation
                        //fix for rename customview which is made to be case sensitive
			if( filterName != null )
			{
				if( filterName.equals( (String )child.getAttribute("ID"))&& treeName.equalsIgnoreCase(temp) )//No Internationalisation
				  return false;
			}
			if( treeName.equalsIgnoreCase(temp))
				return true;
		}
		return false;
	}
	public JDesktopPane getDesktopPane()
	{
		if (useInternalFrame)
			return desktopPane;
		else
			return null;
	}


	//This method is called whenever we change the L&F.
	//This is presently used to i18'ize the JOptionPane .
	public void setALFDefaults()
	{
		/*
		   Thread.dumpStack();
		   System.err.println("mohantest :yesButtonText == "+UIManager.get("OptionPane.yesButtonText" ));
		   System.err.println("mohantest :noButtonText == "+UIManager.get("OptionPane.noButtonText"));
		   System.err.println("mohantest :cancelButtonText == "+UIManager.get("OptionPane.cancelButtonText"));
		   System.err.println("mohantest :okButtonText == "+UIManager.get("OptionPane.okButtonText"));
		 */
		UIManager.put("OptionPane.yesButtonText",NmsClientUtil.GetString("Yes"));
		UIManager.put("OptionPane.noButtonText",NmsClientUtil.GetString("No"));
		UIManager.put("OptionPane.cancelButtonText",NmsClientUtil.GetString("Cancel"));
		UIManager.put("OptionPane.okButtonText",NmsClientUtil.GetString("OK"));
	}
	//This method is used to reduce the size of the image for the frame icon
	private ImageIcon getScalledImage(ImageIcon imgLeaf)
	{
		if( imgLeaf == null )
			return null;
			
		int height =imgLeaf.getIconHeight();
		int width =	imgLeaf.getIconWidth();
		if(height > 20 || width >20)
		{
			height = (height > 20)? 20 : height;
			width = (width > 20) ? 20 :width;
			Image image = imgLeaf.getImage();
			imgLeaf.setImage(image.getScaledInstance(width, height,Image.SCALE_SMOOTH));
		}
		return imgLeaf;
	}
	//This method is used for getting the authentication for doing some operation on the given tree node
	public boolean getAuthentication(XMLNode node)
	{
		if(node != null )
		{
			String isEnabled = (String) node.getAttribute("isEnabled");
			if("false".equalsIgnoreCase(isEnabled)) 
				return false;
			else
				return true;	
		}
		else
			return false;
	}

    public void shutDown(String s)
    {
        frame.setVisible (false);
        if (applet != null)
        {
			if( s == null )
			{
				NmsClientUtil.printOnConsole(NmsClientUtil.GetString(" Shutting down the Client .. Please wait....."));
			}

            String param = applet.getParameter ("WebNMSClient");//No Internationalisation
            try
            {
                if (param != null && param.equals("ApplicationClient"))//No Internationalisation
                {
                    if (((NmsMainApplet)applet).logStream != null)
                    {
                        JFrame frame =  ((NmsMainApplet)applet).logStream.frame;
                        if (frame != null)
                        {
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    }
                }
                Frame[] array = Frame.getFrames();
                for (int i = 0; i < array.length ; i ++)
                {
                    Frame frame = array[i];
                    frame.setVisible(false);
                    //frame.dispose();
                }
				//The following statement should be excuted only when the socket is in activat state	
				if( PureClientUtils.commonSocket.isConnected())
				{
					saveSizeAndLocationOfInternalFrames();
				}
                stop();
                destroy();  
                frame.dispose () ;
                if (param != null && param.equals("ApplicationClient"))//No Internationalisation
                {
                    if (PureClientUtils.commonSocket != null)
                        //to invoke the close methods..
                        NmsClientUtil.cleanUpForAppletReload ();
                    if(s == null)
                       System.exit (0);
                }
            }
            catch(Exception excep)
            {
                if (param != null && param.equals("ApplicationClient"))//No Internationalisation
                {
                    if (PureClientUtils.commonSocket != null)
                        //to invoke the close methods..
                        NmsClientUtil.cleanUpForAppletReload ();
                    if(s == null)
                        System.exit (0);
                }
            }
			if( s != null )
			{
        	  JOptionPane.showMessageDialog(frame,s,"Termination message",JOptionPane.ERROR_MESSAGE);
			  //System. exit should be excuted for Application client only not for browser client
			  if ("ApplicationClient".equals(param))
			  {
				  System.exit(0);
			  }
			}
        }
    }
}

    class DispatcherThread extends Thread 
    {
        MainPanel mainPanel = null;

        public DispatcherThread(MainPanel mainPanel)
        {
            this.mainPanel = mainPanel;
        }
        
        public void run()
        {
            while(!mainPanel.isInitialized)
            {
                try {Thread.sleep(50);} catch(Exception e){}
            }
            
            if(mainPanel.eventDispatchQueue != null)
            {
                while(mainPanel.eventDispatchQueue.size()!=0)
                {
                    NmsPanelEvent nmsevt=(NmsPanelEvent)mainPanel.eventDispatchQueue.firstElement();
                    mainPanel.tempHandleNmsPanelEvent(nmsevt);
                    mainPanel.eventDispatchQueue.removeElement(nmsevt);
                }
                mainPanel.isTreeConstructed = true;
            }
        }
    }

