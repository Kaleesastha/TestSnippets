// $Id: I18NMain.java,v 1.2 2007/02/22 15:03:05 srajeswari Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.management.i18n.tools;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.TableModelEvent;



public class I18NMain extends JFrame implements MouseListener, I18NInterface
{

    //THIS PROJECT CANNOT BE REGENERATED.

    MyResourceBundle resourceBundle = null;

  //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "I18NToolResources";
    //static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private static final String param[] = {"RESOURCE_PROPERTIES","PORT","HOST","MS_MODE",};
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	com.adventnet.beans.table.SortTable JTable1 = null;
	javax.swing.JLabel currFileLabel = null;
	com.adventnet.beans.table.SortTableModel PropTableModel = null;
	JPopupMenu   Edit= null;
	JMenuItem      Edit_in_editorMenuItem = null;
	PopupMenuMouseListener popupMenuMouseListener = new PopupMenuMouseListener();
	private JMenuBar menuBar=null;
	JMenu FileMenu= null;
	JMenuItem   OpenMenuItem = null;
	JMenuItem   SaveMenuItem = null;
	JMenuItem   Save_as_TextMenuItem = null;
	JMenuItem   ExitMenuItem = null;
	JMenu EditMenu= null;
	JMenuItem   FindMenuItem = null;
	JMenuItem   AddMenuItem = null;
	JMenuItem   DeleteMenuItem = null;
	JMenuItem   Edit_in_EditorMenuItem = null;
	JMenu ViewMenu= null;
	JMenuItem   Prominent_EntriesMenuItem = null;
	JMenuItem   All_EntriesMenuItem = null;
	JMenu HelpMenu= null;
	JMenuItem   HelpMenuItem = null;
	private JToolBar toolBar = null;
	private ImageIcon icon = null;
	JButton   OpenButton = null;
	JButton   SaveButton = null;
	JButton   ReloadButton = null;
	JButton   ProminentButton = null;
	JButton   AddButton = null;
	JButton   DeleteButton = null;
	JButton   FindButton = null;
	MenuToolBarAction menuToolBarAction = new MenuToolBarAction();
	//<End_Variable_Declarations>


  private boolean PROMINENT = false; 
  private boolean alreadyOpen = false;
  private boolean startFlag = true;
  private JFileChooser fileOpen = null;
  private JTextField searchText = null;	  
  private String newpattern = "";
  String cmdlinedir = "";

  boolean isModified = false; 
  private boolean standalone = true;


  public I18NMain()
  {
	//<Begin_I18NMain>
    pack();
  
    //<End_I18NMain>
  }

  public I18NMain(String file)
  {
	this(file,true);
  }

  public I18NMain(String file, boolean standalone)
  {

	pack();
	this.setTitle("I18NMain");
	cmdlinedir = file;	 
	this.standalone = standalone;	
  }

  public I18NMain(java.applet.Applet applet)
  {
	//<Begin_I18NMain_java.applet.Applet>
    this.applet = applet;
    pack();
  
    //<End_I18NMain_java.applet.Applet>
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


  public void setUpProperties()
  { 
	javax.swing.table.TableColumnModel colModel = JTable1.getColumnModel();
	for(java.util.Enumeration enumerate = colModel.getColumns();enumerate.hasMoreElements();)
	{
	  ((javax.swing.table.TableColumn)enumerate.nextElement()).setHeaderRenderer(new javax.swing.table.DefaultTableCellRenderer());
	}
	//<Begin_setUpProperties> 

          try
          {
            JScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(0));
            JScrollPane1.setBackground(new Color(-3355444));
            JScrollPane1.setAlignmentX((float)5.0);
            JScrollPane1.setAlignmentY((float)1.0);
            JScrollPane1.setHorizontalScrollBarPolicy(32);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

          try
          {
            JTable1.setDoubleBuffered(true);
            JTable1.setRequestFocusEnabled(true);
            JTable1.setAutoResizeMode(2);
            JTable1.setSelectionForeground(new Color(-16764109));
            JTable1.setToolTipText(resourceBundle.getString("Doubleclick to edit"));
            JTable1.setEditingColumn(1);
            JTable1.setModel(PropTableModel);
            JTable1.setSelectionBackground(new Color(-16750849));
            JTable1.setRowHeight(28);
            JTable1.setFont(new Font("Dialog",1,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTable1,ex); 
          }

          try
          {
            currFileLabel.setForeground(new Color(-16764109));
            currFileLabel.setFont(new Font("Dialog",1,11));
            currFileLabel.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+currFileLabel,ex); 
          }
		currFileLabel.setPreferredSize(new Dimension(currFileLabel.getPreferredSize().width+681,currFileLabel.getPreferredSize().height+20));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+1,JScrollPane1.getPreferredSize().height+1));

  
          //<End_setUpProperties>
  } 
  public void init()
  { 

    initializeResourceBundle();

	//<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	//resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+718,getPreferredSize().height+572); 
          setTitle(resourceBundle.getString("I18N Resource File Editor"));
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


	setIconImage(com.adventnet.apiutils.Utility.findImage("com/adventnet/management/i18n/tools/images/i18n.jpg",applet,true).getImage());

	OpenButton.setBorderPainted(false);
	FindButton.setBorderPainted(false);
	SaveButton.setBorderPainted(false);
	ReloadButton.setBorderPainted(false);
	ProminentButton.setBorderPainted(false);
	AddButton.setBorderPainted(false);
	DeleteButton.setBorderPainted(false);

	OpenButton.setFocusPainted(false);
	FindButton.setFocusPainted(false);
	SaveButton.setFocusPainted(false);
	ReloadButton.setFocusPainted(false);
	ProminentButton.setFocusPainted(false);
	AddButton.setFocusPainted(false);
	DeleteButton.setFocusPainted(false);

	OpenButton.addMouseListener(this);
	FindButton.addMouseListener(this);
	SaveButton.addMouseListener(this);
	ReloadButton.addMouseListener(this);	
	AddButton.addMouseListener(this);
	DeleteButton.addMouseListener(this);	
	ProminentButton.addMouseListener(this);

	toolBar.add(new JLabel("  "),1);
	toolBar.add(new JLabel("  "),3);	
	toolBar.add(new JLabel("  "),5);
	toolBar.add(new JLabel("  "),7);
	toolBar.add(new JLabel("  "),9);	
	toolBar.add(new JLabel("  "),11);	
	toolBar.add(new JLabel("  "),13);	

	JTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	addWindowListener( new WindowAdapter()
			{
			public void windowClosing(java.awt.event.WindowEvent we)
			{
			close();
			}
			}
			);	


	I18NUtils.WindowPosition(this);
	fileOpen = new JFileChooser();
	fileOpen.setSize(new Dimension(500,700));


	if(cmdlinedir.equals(""))
	{
	  presentfile = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "html");
	}
	else 
	{
	  presentfile = new File(cmdlinedir);
	}

	fileOpen.setCurrentDirectory(presentfile);

	fileOpen.setFileFilter(new I18nFileFilter(resourceBundle));
        
  } 

  public void mouseEntered(java.awt.event.MouseEvent arg0)
  {
	((JButton)arg0.getSource()).setBorderPainted(true);		
  }	

  public void mouseExited(java.awt.event.MouseEvent arg0)
  {
	((JButton)arg0.getSource()).setBorderPainted(false);				
  }	


  public void mouseClicked(MouseEvent e) {}

  public void mousePressed(MouseEvent e) {}

  public void mouseReleased(MouseEvent e)  {}


  public void setUpConnections()
  { 

	//<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 


    private void initializeResourceBundle()
    {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        String language = locale.getLanguage();
        String nmsHome= System.getProperty("WEBNMSHOME");
        String resourceFileName = "I18NToolResources"+"_"+language+"_"+country+".properties";
        File resFile=null;
       
        if(nmsHome != null && ! nmsHome.equals(""))
        {
            String lang = System.getProperty("LANGUAGE",language);
            String cntry= System.getProperty("COUNTRY",country);
            if(! lang.equals("") && ! cntry.equals(""))
            {
                resourceFileName= "I18NToolResources"+"_"+lang+"_"+cntry+".properties";
            }
            resFile = new File(nmsHome+"/html/" + resourceFileName);
            resourceFileName = nmsHome+"/html/I18NToolResources.properties";
        }
        else
        {
            resFile= new File("./html/"+ resourceFileName);
            resourceFileName = "./html/I18NToolResources.properties";
        }
        URL url = null;

        try
        {
            if(resFile.exists())
            {
                url = resFile.toURL();
            }
            else
            {
                // resourceFileName = "./html/I18NToolResources.properties";
                resFile = new File(resourceFileName);
                url = resFile.toURL();
            }
            resourceBundle = new MyResourceBundle(url.openStream());
        }
        catch(Exception ee)
        {
            resourceBundle = new MyResourceBundle();
            System.out.println("Error while creating resource bundle");
        }
    }

  public void initVariables()
  { 

	//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new com.adventnet.beans.table.SortTable();
        currFileLabel= new javax.swing.JLabel();
        PropTableModel= new com.adventnet.beans.table.SortTableModel();
   currFileLabel.addMouseListener(popupMenuMouseListener);
   Top.addMouseListener(popupMenuMouseListener);
   JTable1.addMouseListener(popupMenuMouseListener);

  
        //<End_initVariables>
	searchText = new JTextField(20);

  } 
  public void setUpToolBar()
  { 

	//<Begin_setUpToolBar> 
toolBar = new JToolBar();
toolBar.setFloatable(false);
OpenButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("com/adventnet/management/i18n/tools/images/foldero.jpg",applet); 
   OpenButton.setPreferredSize(new Dimension(35,35));
   OpenButton.setMinimumSize(new Dimension(35,35));
   OpenButton.setMaximumSize(new Dimension(35,35));
   OpenButton.setIcon(icon);
   OpenButton.setActionCommand(resourceBundle.getString("Open"));
   OpenButton.setToolTipText(resourceBundle.getString("open"));
SaveButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("com/adventnet/management/i18n/tools/images/file.png",applet); 
   SaveButton.setPreferredSize(new Dimension(35,35));
   SaveButton.setMinimumSize(new Dimension(35,35));
   SaveButton.setMaximumSize(new Dimension(35,35));
   SaveButton.setIcon(icon);
   SaveButton.setActionCommand(resourceBundle.getString("Save"));
   SaveButton.setToolTipText(resourceBundle.getString("Save"));
ReloadButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("com/adventnet/management/i18n/tools/images/refresh_mo.png",applet); 
   ReloadButton.setPreferredSize(new Dimension(35,35));
   ReloadButton.setMinimumSize(new Dimension(35,35));
   ReloadButton.setMaximumSize(new Dimension(35,35));
   ReloadButton.setIcon(icon);
   ReloadButton.setActionCommand(resourceBundle.getString("Reload"));
   ReloadButton.setToolTipText(resourceBundle.getString("Reload"));
ProminentButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("com/adventnet/management/i18n/tools/images/index.jpg",applet); 
   ProminentButton.setPreferredSize(new Dimension(35,35));
   ProminentButton.setMinimumSize(new Dimension(35,35));
   ProminentButton.setMaximumSize(new Dimension(35,35));
   ProminentButton.setIcon(icon);
   ProminentButton.setActionCommand(resourceBundle.getString("Prominent"));
   ProminentButton.setToolTipText(resourceBundle.getString("Prominent"));
AddButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("com/adventnet/management/i18n/tools/images/addcontainer.png",applet); 
   AddButton.setPreferredSize(new Dimension(35,35));
   AddButton.setMinimumSize(new Dimension(35,35));
   AddButton.setMaximumSize(new Dimension(35,35));
   AddButton.setIcon(icon);
   AddButton.setActionCommand(resourceBundle.getString("Add"));
   AddButton.setToolTipText(resourceBundle.getString("Add"));
DeleteButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("com/adventnet/management/i18n/tools/images/delete.png",applet); 
   DeleteButton.setPreferredSize(new Dimension(35,35));
   DeleteButton.setMinimumSize(new Dimension(35,35));
   DeleteButton.setMaximumSize(new Dimension(35,35));
   DeleteButton.setIcon(icon);
   DeleteButton.setActionCommand(resourceBundle.getString("Delete"));
   DeleteButton.setToolTipText(resourceBundle.getString("Delete"));
FindButton = toolBar.add(menuToolBarAction);
icon = com.adventnet.apiutils.Utility.findImage("com/adventnet/management/i18n/tools/images/find.png",applet); 
   FindButton.setPreferredSize(new Dimension(35,35));
   FindButton.setMinimumSize(new Dimension(35,35));
   FindButton.setMaximumSize(new Dimension(35,35));
   FindButton.setIcon(icon);
   FindButton.setActionCommand(resourceBundle.getString("Find"));
   FindButton.setToolTipText(resourceBundle.getString("Find"));
   JPanel toolBarPanel = new JPanel(new BorderLayout());
   toolBarPanel.add(toolBar);
   getContentPane().add(toolBarPanel,BorderLayout.NORTH);

  
//<End_setUpToolBar>
	toolBar.setFloatable(true);
  } 
  public void setUpGUI(Container container)
  { 

	//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTable1);
Top.add(currFileLabel,BorderLayout.SOUTH);

  
//<End_setUpGUI_Container>
	JScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER,new JButton()	);	
  } 
  public void setUpMenus()
  { 

	//<Begin_setUpMenus> 
   menuBar = new JMenuBar();
   FileMenu= new JMenu(getString("File",'F'));
   FileMenu.setMnemonic('F');
   menuBar.add(FileMenu);
   OpenMenuItem = new JMenuItem(getString("Open",'O'));
   OpenMenuItem.setMnemonic('O');
   OpenMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
   OpenMenuItem.setActionCommand(resourceBundle.getString("Open"));
   OpenMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(OpenMenuItem);
   SaveMenuItem = new JMenuItem(getString("Save",'s'));
   SaveMenuItem.setMnemonic('S');
   SaveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
   SaveMenuItem.setActionCommand(resourceBundle.getString("Save"));
   SaveMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(SaveMenuItem);
   FileMenu.addSeparator();
   Save_as_TextMenuItem = new JMenuItem(getString("Save as Text",'T'));
   Save_as_TextMenuItem.setMnemonic('T');
   Save_as_TextMenuItem.setActionCommand(resourceBundle.getString("Save as Text"));
   Save_as_TextMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(Save_as_TextMenuItem);
   FileMenu.addSeparator();
   ExitMenuItem = new JMenuItem(getString("Exit",'X'));
   ExitMenuItem.setMnemonic('X');
   ExitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,+ActionEvent.ALT_MASK));
   ExitMenuItem.setActionCommand(resourceBundle.getString("Exit"));
   ExitMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(ExitMenuItem);
   EditMenu= new JMenu(getString("Edit",'E'));
   EditMenu.setMnemonic('E');
   menuBar.add(EditMenu);
   FindMenuItem = new JMenuItem(getString("Find",'F'));
   FindMenuItem.setMnemonic('F');
   FindMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
   FindMenuItem.setActionCommand(resourceBundle.getString("Find"));
   FindMenuItem.addActionListener(menuToolBarAction);
   EditMenu.add(FindMenuItem);
   EditMenu.addSeparator();
   AddMenuItem = new JMenuItem(getString("Add",'A'));
   AddMenuItem.setMnemonic('A');
   AddMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
   AddMenuItem.setActionCommand(resourceBundle.getString("Add"));
   AddMenuItem.addActionListener(menuToolBarAction);
   EditMenu.add(AddMenuItem);
   DeleteMenuItem = new JMenuItem(getString("Delete",'D'));
   DeleteMenuItem.setMnemonic('D');
   DeleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
   DeleteMenuItem.setActionCommand(resourceBundle.getString("Delete"));
   DeleteMenuItem.addActionListener(menuToolBarAction);
   EditMenu.add(DeleteMenuItem);
   EditMenu.addSeparator();
   Edit_in_EditorMenuItem = new JMenuItem(getString("Edit in Editor",'R'));
   Edit_in_EditorMenuItem.setMnemonic('R');
   Edit_in_EditorMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
   Edit_in_EditorMenuItem.setActionCommand(resourceBundle.getString("Edit in Editor"));
   Edit_in_EditorMenuItem.addActionListener(menuToolBarAction);
   EditMenu.add(Edit_in_EditorMenuItem);
   ViewMenu= new JMenu(getString("View",'V'));
   ViewMenu.setMnemonic('V');
   menuBar.add(ViewMenu);
   Prominent_EntriesMenuItem = new JMenuItem(getString("Prominent Entries",'P'));
   Prominent_EntriesMenuItem.setMnemonic('P');
   Prominent_EntriesMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
   Prominent_EntriesMenuItem.setActionCommand(resourceBundle.getString("Prominent"));
   Prominent_EntriesMenuItem.addActionListener(menuToolBarAction);
   ViewMenu.add(Prominent_EntriesMenuItem);
   All_EntriesMenuItem = new JMenuItem(getString("All Entries",'E'));
   All_EntriesMenuItem.setMnemonic('E');
   All_EntriesMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
   All_EntriesMenuItem.setActionCommand(resourceBundle.getString("All Entries"));
   All_EntriesMenuItem.addActionListener(menuToolBarAction);
   ViewMenu.add(All_EntriesMenuItem);
   HelpMenu= new JMenu(getString("Help",'H'));
   HelpMenu.setMnemonic('H');
   menuBar.add(HelpMenu);
   HelpMenuItem = new JMenuItem(getString("Help",'H'));
   HelpMenuItem.setMnemonic('H');
   HelpMenuItem.setActionCommand(resourceBundle.getString("Help"));
   HelpMenuItem.addActionListener(menuToolBarAction);
   HelpMenu.add(HelpMenuItem);
   this.setJMenuBar(menuBar);

  
   //<End_setUpMenus>
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "I18NToolResources"; 
            if (input.equals("PORT")) value = "9090"; 
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("MS_MODE")) value = "3"; 
            }
        return value;

  
           //<End_getParameter_String>
  }

  public void showFileOpen(String dirArg)
  {
	  // Added for Studio as they wanted to set the current dir.
	  if(dirArg != null)
	  {
		  File dir = new File(dirArg);
		  fileOpen.setCurrentDirectory(dir);
	  }
	  showFileOpen();
  }


  public void showFileOpen() 
  {

	PROMINENT = false;
	if(fileOpen.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
	{
	  return;
	}

	String str =fileOpen.getSelectedFile().toString();
	File file = new File(str);
	alreadyOpen = true;  		
	Properties prop = new Properties();	
	String ext = getExtension(file);
	if(ext != null)
	{
	  if(ext.equals("txt"))
	  {
		String key="";
		String value="";
		try 
		{
		  URL url = file.toURL();
		  InputStream stream = url.openStream();	
		  BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		  String s="";
		  StringTokenizer stk;

		  if( br != null)
		  {
                        Vector data = new Vector();
			while( (s=br.readLine())!=null )
			{   
			  if(( (s.startsWith("#")) || (s.startsWith("*"))||(s.trim().equals(""))))
				continue;
			  stk=new StringTokenizer(s,"||");
                          int noOfTokens = stk.countTokens();
			  if(noOfTokens <= 0)
			  {
				continue;
			  }
			  key=stk.nextToken();
			  if(noOfTokens == 2) 
			  {
				value=stk.nextToken();
			  }
                          Vector row = new Vector();
                          row.addElement(key);
                          row.addElement(value);
                          data.addElement(row);
			}
                        Vector header = new Vector();
                        header.addElement(resourceBundle.getString("Property Key"));
                        header.addElement(resourceBundle.getString("Property Value"));
                        ((javax.swing.table.DefaultTableModel)JTable1.getModel()).setDataVector(data, header);
		  }

		} 
		catch(Exception e)
		{
		  e.printStackTrace();
		}
	    presentfile = file;
	  }

	  else if(ext.equals("properties"))
	  {
		try
		{
		  FileInputStream fin = new FileInputStream(file);
                  Vector values = load(fin);
                  Vector header = new Vector();
                  header.addElement(resourceBundle.getString("Property Key"));
                  header.addElement(resourceBundle.getString("Property Value"));
                  ((javax.swing.table.DefaultTableModel)JTable1.getModel()).setDataVector(values, header);
		}
		catch(Exception e)
		{

		}
	   presentfile = file;

	  }
	  else
	  {
		JOptionPane.showMessageDialog(null,"The selected file cannot be opened by this tool.","Warning Message",JOptionPane.ERROR_MESSAGE);
		return;
	  }
	}
	else
	{
	  JOptionPane.showMessageDialog(null,"The selected file cannot be opened.","Warning Message",JOptionPane.ERROR_MESSAGE);
	  return;
	}

	this.setTitle("I18N Resource File Editor - "+ presentfile.getName());

	if(prominent != null)
	{
	  prominent = null;
	}	

  }	

    private static final String keyValueSeparators = "=: \t\r\n\f";
    private static final String strictKeyValueSeparators = "=:";
    private static final String specialSaveChars = "=: \t\r\n\f#!";
    private static final String whiteSpaceChars = " \t\r\n\f";

    public synchronized Vector load(InputStream inStream) throws IOException 
    {
        Vector dataVector = new Vector();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "8859_1"));
	while (true)
        {
            // Get next line
            String line = in.readLine();
            if (line == null)
            {
                return dataVector;
            }
            if (line.length() > 0)
            {
                // Continue lines that end in slashes if they are not comments
                char firstChar = line.charAt(0);
                if ((firstChar != '#') && (firstChar != '!'))
                {
                    while (continueLine(line))
                    {
                        String nextLine = in.readLine();
                        if(nextLine == null)
                            nextLine = new String("");
                        String loppedLine = line.substring(0, line.length()-1);
                        // Advance beyond whitespace on new line
                        int startIndex=0;
                        for(startIndex=0; startIndex<nextLine.length(); startIndex++)
                            if (whiteSpaceChars.indexOf(nextLine.charAt(startIndex)) == -1)
                                break;
                        nextLine = nextLine.substring(startIndex,nextLine.length());
                        line = new String(loppedLine+nextLine);
                    }

                    // Find start of key
                    int len = line.length();
                    int keyStart;
                    for(keyStart=0; keyStart<len; keyStart++)
                    {
                        if(whiteSpaceChars.indexOf(line.charAt(keyStart)) == -1)
                            break;
                    }

                    // Blank lines are ignored
                    if (keyStart == len)
                        continue;

                    // Find separation between key and value
                    int separatorIndex;
                    for(separatorIndex=keyStart; separatorIndex<len; separatorIndex++)
                    {
                        char currentChar = line.charAt(separatorIndex);
                        if (currentChar == '\\')
                            separatorIndex++;
                        else if(keyValueSeparators.indexOf(currentChar) != -1)
                            break;
                    }

                    // Skip over whitespace after key if any
                    int valueIndex;
                    for (valueIndex=separatorIndex; valueIndex<len; valueIndex++)
                        if (whiteSpaceChars.indexOf(line.charAt(valueIndex)) == -1)
                            break;

                    // Skip over one non whitespace key value separators if any
                    if (valueIndex < len)
                        if (strictKeyValueSeparators.indexOf(line.charAt(valueIndex)) != -1)
                            valueIndex++;

                    // Skip over white space after other separators if any
                    while (valueIndex < len)
                    {
                        if (whiteSpaceChars.indexOf(line.charAt(valueIndex)) == -1)
                            break;
                        valueIndex++;
                    }
                    String key = line.substring(keyStart, separatorIndex);
                    String value = (separatorIndex < len) ? line.substring(valueIndex, len) : "";

                    // Convert then store key and value
                    key = loadConvert(key);
                    value = loadConvert(value);

                    Vector row = new Vector();
                    row.addElement(code(key));
                    row.addElement(code(value));
                    dataVector.addElement(row);
                }
            }
	}
    }

    private String loadConvert (String theString)
    {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);

        for(int x=0; x<len; )
        {
            aChar = theString.charAt(x++);
            if (aChar == '\\')
            {
                aChar = theString.charAt(x++);
                if(aChar == 'u')
                {
                    // Read the xxxx
                    int value=0;
		    for (int i=0; i<4; i++)
                    {
		        aChar = theString.charAt(x++);
		        switch (aChar)
                        {
                        case '0': case '1': case '2': case '3': case '4':
                        case '5': case '6': case '7': case '8': case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a': case 'b': case 'c':
                        case 'd': case 'e': case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A': case 'B': case 'C':
                        case 'D': case 'E': case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException(
                                                               "Malformed \\uxxxx encoding.");
                        }
                    }
                    outBuffer.append((char)value);
                }
                else
                {
                    if (aChar == 't') aChar = '\t';
                    else if (aChar == 'r') aChar = '\r';
                    else if (aChar == 'n') aChar = '\n';
                    else if (aChar == 'f') aChar = '\f';
                    outBuffer.append(aChar);
                }
            }
            else
            {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    private boolean continueLine (String line)
    {
        int slashCount = 0;
        int index = line.length() - 1;
        while((index >= 0) && (line.charAt(index--) == '\\'))
            slashCount++;
        return (slashCount % 2 == 1);
    }

  public void saveProminent()
  {
  }	

  public File presentfile = null;
  public File lastfile = null;	


    public static String code(String key)
    {
        String dst = "";
        for (int i = 0; i < key.length(); ++ i)
        {
            char c = key.charAt(i);
            if (c == '\n')
                dst += "\\n";
            else if (c == '\\')
                dst += "\\\\";
            else dst += c;
        }
        return dst;
    }


    public static String decode(String key)
    {
        if (key == null)
            return key;

        String dst = "";
        for(int i = 0; i < key.length(); ++ i)
        {
            char c = key.charAt(i);
            char next = i < key.length() - 1 ? key.charAt(i + 1) : ' ';
            if(c == '\\' && next == 'n')
            {
                dst += "\n";
                i ++;
            }
            else if(c == '\\' && next == '\\')
            {
                dst += "\\";
                i ++;
            }
            else
            {
                dst += c;
            }
        }
        return dst;
    }

    private Vector dataVector = null;
    public Vector prominent = null;
    public Vector getProminent()
    {
        prominent = new Vector();
        dataVector = new Vector();
	int count = 0;
        int size = JTable1.getRowCount();        
	for(int i=0; i<size; i++) 
	{
            String str = JTable1.getValueAt(i,0).toString();
            String value = JTable1.getValueAt(i,1).toString();
            if(str.startsWith("<PROMINENT_KEY>"))
            {
                Vector row = new Vector();
                row.addElement(str.substring(15));
                row.addElement(value);
                prominent.addElement(row);
		count++;
            }
            Vector vec = new Vector();
            vec.addElement(str);
            vec.addElement(value);
            dataVector.addElement(vec);
        }
	if(count == 0)
	{
            currFileLabel.setText(resourceBundle.getString("File does not contain prominent entries"));
            return null;
	}	
        
	PROMINENT = true;
	return	prominent;	   
    }		

  public File getFile()
  {
	return presentfile;
  }	

  int begin = 0;


  public  void find(boolean matchcase)
  {

	setCursor(new Cursor(Cursor.WAIT_CURSOR));
	StringSearch stringS = new StringSearch();

	String pattern =  newpattern ;
	for(int i=begin;i<JTable1.getRowCount();i++) 
	{

	  String key = JTable1.getValueAt(i,0).toString();
	  if(stringS.search(key,pattern,matchcase))
	  {
		stringS = null;
		Rectangle rect = JTable1.getCellRect(i,0,false);
		JTable1.setRowSelectionInterval(i,i);
		JTable1.scrollRectToVisible(rect);
		currFileLabel.setText(resourceBundle.getString("Search string found at line :") + i);
		begin  = i+1;
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		return;
	  }  

	  String value = JTable1.getValueAt(i,1).toString(); 

	  if(stringS.search(value,pattern,matchcase))
	  {
		stringS = null;
		Rectangle rect = JTable1.getCellRect(i,0,false);  	
		JTable1.setRowSelectionInterval(i,i);	
		JTable1.scrollRectToVisible(rect);	
		currFileLabel.setText(resourceBundle.getString("Search string found at line :") + i);
		begin = i+1;	
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
		return;
	  }  
	}

	setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
	currFileLabel.setText(resourceBundle.getString("Finished searching the document"));
	begin=0;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	toolkit.beep();

  }

  public Properties getTableProps()
  {
	Properties prop = new Properties();
	for(int i=0;i<JTable1.getRowCount();i++)
	{
	  prop.put(JTable1.getValueAt(i,0).toString(),JTable1.getValueAt(i,1).toString());	
	}	

	return prop;

  }
  public void saveLastFile()
  {
	/*
	   lastfile = presentfile;
	   try
	   {
	   FileOutputStream fout = new FileOutputStream("I18N");
	   ObjectOutputStream oout = new ObjectOutputStream(fout);
	   oout.writeObject(lastfile);		
	   oout.flush();
	   oout.close();	
	   }
	   catch(Exception e)
	   {

	   }
	 */

  }	

  public File getlastFile()
  {

	File temp = null;
	/*
	   try
	   {
	   FileInputStream fin = new FileInputStream("I18N");
	   ObjectInputStream oin = new ObjectInputStream(fin);
	   temp = (File)oin.readObject();
	   oin.close();	
	   }
	   catch(Exception e)
	   {

	   }	
	 */
	return temp;

  }	
  public void reload()
  {
	if(!alreadyOpen)	
	{
	  JOptionPane.showMessageDialog(null,resourceBundle.getString("No file opened for reload"),resourceBundle.getString("Warning Message"),JOptionPane.ERROR_MESSAGE);
	  return;
	}		
	stopEditing();

	if(JTable1.getRowCount() == 0)
	{
	  return;
	}	
	PROMINENT = false;
	try
	{            
            String ext = getExtension(presentfile);
            if(ext != null && ext.equals("txt"))
            {
		String key="";
		String value="";
		try 
		{
                    URL url = presentfile.toURL();
                    InputStream stream = url.openStream();	
                    BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                    String s="";
                    StringTokenizer stk;

                    if( br != null)
                    {
                        Vector data = new Vector();
			while( (s=br.readLine())!=null )
			{   
                            if(( (s.startsWith("#")) || (s.startsWith("*"))||(s.trim().equals(""))))
				continue;
                            stk=new StringTokenizer(s,"||");
                            int noOfTokens = stk.countTokens();
                            if(noOfTokens <= 0)
                            {
				continue;
                            }
                            key = stk.nextToken();
                            if(noOfTokens == 2) 
                            {
				value = stk.nextToken();
                            }
                            Vector row = new Vector();
                            row.addElement(key);
                            row.addElement(value);
                            data.addElement(row);
			}
                        Vector header = new Vector();
                        header.addElement(resourceBundle.getString("Property Key"));
                        header.addElement(resourceBundle.getString("Property Value"));
                        ((javax.swing.table.DefaultTableModel)JTable1.getModel()).setDataVector(data, header);
                    }
		}
		catch(Exception e)
		{
                    e.printStackTrace();
		}
            }
            else if(ext != null && ext.equals("properties"))
            {
                FileInputStream fin = new FileInputStream(presentfile);
                Vector values = load(fin);
          
                for(int k=JTable1.getRowCount()-1;k>=0;k--)
                {
                    PropTableModel.removeRow(k);
                }

                int size = values.size();
                for(int i=0; i< size; i++)
                {
                    PropTableModel.addRow((Vector)values.elementAt(i));
                }
            }
        }
	catch(Exception e)
	{
	}
	JTable1.updateUI();
	currFileLabel.setText(resourceBundle.getString("Current File  :  ")+presentfile);
  }	

  public void close()
  {
	saveLastFile();
	if(standalone)
	{
	  /*
	  // Why is all this required in exit?
	  PropTableModel  = null;
	  JTable1 = null;
	  JScrollPane1 = null;
	  menuBar = null;
	  toolBar = null;

	  removeAll();
	  dispose();
	   */
	  System.exit(0);
	}
	else
	{
	  setVisible(false);
	}
  }		

  public void clearTable()
  {
	for(int k=JTable1.getRowCount()-1;k>=0;k--)
	{
	  PropTableModel.removeRow(k);

	}

  }	

  public void stopEditing()
  {
	if(JTable1.isEditing())
	{
	  ((DefaultCellEditor)JTable1.getCellEditor()).stopCellEditing();
	}
  }	

  public void save()
  {
	if(!alreadyOpen)	
	{
	  JOptionPane.showMessageDialog(null,resourceBundle.getString("No file opened for save"),resourceBundle.getString("Warning Message"),JOptionPane.ERROR_MESSAGE);
	  return;
	}			

	if(JTable1.getRowCount() == 0)
	{
	  return;
	}	

	stopEditing();

	try
	{
	  currFileLabel.setText(resourceBundle.getString("Please wait ... "));

	  FileOutputStream fout;
          String filename2 = "";
	  String ext = getExtension(presentfile);	
	  if(ext != null && ext.equals("txt"))
	  {
		String temp = presentfile.toString().substring(0,presentfile.toString().lastIndexOf('.'));
		temp += ".properties";

		fout  = new FileOutputStream(new File(temp));
		String filename1 = presentfile.toString().substring(presentfile.toString().lastIndexOf(System.getProperty("file.separator"))+1
				,presentfile.toString().length() );	
		filename2 = temp.substring(temp.lastIndexOf(System.getProperty("file.separator"))+1, temp.length() );					
		Toolkit toolkit  = 	Toolkit.getDefaultToolkit();			

		for(int k=0; k<3;k++)
		{ 
		  Thread.sleep(500);	
		  toolkit.beep();
		}
		toolkit.beep();
                currFileLabel.setText(java.text.MessageFormat.format(
                                                                     resourceBundle.getString("Converted the file {0} to {1}"), new String[]{filename1, filename2}));
	  }	
	  else
	  {
		fout  = new FileOutputStream(presentfile);
	  }

          String header = "edited properties";
          BufferedWriter awriter;
          awriter = new BufferedWriter(new OutputStreamWriter(fout, "8859_1"));
          if (header != null)
          {
              writeln(awriter, "#" + header);
          }
          writeln(awriter, "#" + new Date().toString());
          
          if(PROMINENT)	
          {
              if(dataVector != null)
              {
                  int size = dataVector.size();
                  for(int j = 0; j < size; j++)
                  {
                      Vector row = (Vector)dataVector.elementAt(j);
                      String key = (String)row.elementAt(0);
                      String val = (String)row.elementAt(1);
                      for(int i=0; i<JTable1.getRowCount(); i++)
                      {
                          String k = decode("<PROMINENT_KEY>"+JTable1.getValueAt(i,0).toString());
                          String v = decode(JTable1.getValueAt(i,1).toString());
                          if(key.equals(k))
                          {
                              val = v;
                              break;
                          }
                      }
                      key = saveConvert(key, true);
                      val = saveConvert(val, false);
                      writeln(awriter, key + "=" + val);
                  }
              }
              else
              {
                  for(int i=0;i<JTable1.getRowCount();i++)
                  {
                      String key = decode("<PROMINENT_KEY>"+JTable1.getValueAt(i,0).toString());
                      String val = decode(JTable1.getValueAt(i,1).toString());
                      
                      key = saveConvert(key, true);
                      val = saveConvert(val, false);
                      writeln(awriter, key + "=" + val);
                  }
              }
          }	
          else
          {
              for(int i=0;i<JTable1.getRowCount();i++)
              {
                  String key = decode(JTable1.getValueAt(i,0).toString());
                  String val = decode(JTable1.getValueAt(i,1).toString());
                  
                  key = saveConvert(key, true);
                  val = saveConvert(val, false);
                  writeln(awriter, key + "=" + val);
              }	
          }	
          awriter.flush();
	  if(ext != null && ext.equals("txt"))
          {
              currFileLabel.setText(resourceBundle.getString("Saved File :  ")+filename2);
          }
          else
              currFileLabel.setText(resourceBundle.getString("Saved File :  ")+presentfile);
	}
	catch(Exception e){}

	isModified = true;	    

  }	

    private String saveConvert(String theString, boolean escapeSpace)
    {
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len*2);

        for(int x=0; x<len; x++) {
            char aChar = theString.charAt(x);
            switch(aChar) {
            case ' ':
                if (x == 0 || escapeSpace) 
                    outBuffer.append('\\');

                outBuffer.append(' ');
                break;
            case '\\':outBuffer.append('\\'); outBuffer.append('\\');
                break;
            case '\t':outBuffer.append('\\'); outBuffer.append('t');
                break;
            case '\n':outBuffer.append('\\'); outBuffer.append('n');
                break;
            case '\r':outBuffer.append('\\'); outBuffer.append('r');
                break;
            case '\f':outBuffer.append('\\'); outBuffer.append('f');
                break;
            default:
                if ((aChar < 0x0020) || (aChar > 0x007e)) {
                    outBuffer.append('\\');
                    outBuffer.append('u');
                    outBuffer.append(toHex((aChar >> 12) & 0xF));
                    outBuffer.append(toHex((aChar >>  8) & 0xF));
                    outBuffer.append(toHex((aChar >>  4) & 0xF));
                    outBuffer.append(toHex( aChar        & 0xF));
                } else {
                    if (specialSaveChars.indexOf(aChar) != -1)
                        outBuffer.append('\\');
                    outBuffer.append(aChar);
                }
            }
        }
        return outBuffer.toString();
    }


    private static char toHex(int nibble) {
	return hexDigit[(nibble & 0xF)];
    }

    /** A table of hex digits */
    private static final char[] hexDigit = {
	'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };

    private static void writeln(BufferedWriter bw, String s) throws IOException
    {
        bw.write(s);
        bw.newLine();
    }

  private String getExtension(File file)	
  {
	String ext = file.toString();
	int pos = ext.lastIndexOf(File.separator);
	int index = ext.indexOf('.',pos);
	if(index != -1)
	{
	  ext = ext.substring(index+1);
	}
	else
	{
	  ext = null;
	}
	return ext;
  }	

  public void setSearchText(String searchtext)
  {
	newpattern = searchtext;
  }	
  public void setReplaceText(String reptext, String repwithtext)
  {

  }	
  public void setAddText(String key ,String value)
  {
	int finalrow = JTable1.getRowCount();
	PropTableModel.addRow(new Object[]{key,value});
	Rectangle rect = JTable1.getCellRect(finalrow,0,false);
	JTable1.setRowSelectionInterval(finalrow,finalrow);
	JTable1.scrollRectToVisible(rect);

        if(dataVector != null)
        {
            Vector row = new Vector();
            if(PROMINENT)
                key = "<PROMINENT_KEY>"+key;
            row.addElement(key);
            row.addElement(value);
            dataVector.addElement(row);
        }
  }	

  public void showFindDialog()	
  {
	if(!alreadyOpen)	
	{
	  JOptionPane.showMessageDialog(null,resourceBundle.getString("No file opened for search"),resourceBundle.getString("Warning Message"),JOptionPane.ERROR_MESSAGE);
	  return;
	}		
	FindDialog find = new FindDialog(resourceBundle);
	find.setI18NInterface(this);
	find.setVisible(true);
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

  public static void main(String [] args)
  {
	String[] str = {""};
	if(args.length == 0 )
	{
	  args = str;	
	}

	if(false)
	{
	  //<Begin_main_String[]>
      com.adventnet.apiutils.Utility.parseAndSetParameters(param,args);  
     I18NMain frame = new I18NMain();
     frame.setVisible(true);
     frame.addWindowListener(new WindowAdapter()
     {
       public void windowClosing(WindowEvent evt)
       {
         System.exit(0);
       }
     });
  
      //<End_main_String[]>
	}
	I18NMain frame = new I18NMain(args[0]);
	frame.setVisible(true);
	/*
	   frame.addWindowListener(new WindowAdapter()
	   {
	   public void windowClosing(WindowEvent evt)
	   {
	   if(standalone)
	   {
	   System.exit(0);
	   }
	   else
	   {
	   frame.setVisible(false);
	   }
	   }

	   });

	 */
	frame.showFileOpen();		

  }

  public void deleteEntry()
  {

	if(!alreadyOpen)	
	{
	  JOptionPane.showMessageDialog(null,resourceBundle.getString("No file opened for delete"),resourceBundle.getString("Warning Message"),JOptionPane.ERROR_MESSAGE);
	  return;
	}		


	if(JTable1.getSelectedRowCount() != 1)
	{
	  return;
	}

	JOptionPane pane = new JOptionPane();
	int i = pane.showConfirmDialog(null,resourceBundle.getString("Are you sure you want to delete this entry ?"),resourceBundle.getString("Confirm delete"), JOptionPane.YES_NO_OPTION);
	if((i == JOptionPane.CLOSED_OPTION) || (i == JOptionPane.NO_OPTION))
	{
	  return;
	}
	stopEditing();
        if(dataVector != null)
        {
		//Changed by P.Shivaprakash sep18,2003
		//dataVector.removeElementAt(JTable1.getSelectedRow() + 1);
		dataVector.removeElementAt(JTable1.getSelectedRow());
        }
	PropTableModel.removeRow(JTable1.getSelectedRow());
  }	

  public void showAddDialog()
  {
	if(!alreadyOpen)	
	{
	  JOptionPane.showMessageDialog(null,resourceBundle.getString("No file opened for add"),resourceBundle.getString("Warning Message"),JOptionPane.ERROR_MESSAGE);
	  return;
	}	

	AddEntry ae = new AddEntry(resourceBundle);
	ae.setI18NInterface(this);
	ae.setVisible(true);
  }	

  public void showEditor()
  {
	if(!alreadyOpen)	
	{
	  JOptionPane.showMessageDialog(null,resourceBundle.getString("No file opened for edit"),resourceBundle.getString("Warning Message"),JOptionPane.ERROR_MESSAGE);
	  return;
	}
	if(JTable1.getSelectedRowCount() != 1)
	{
	  currFileLabel.setText(resourceBundle.getString("Select a row to edit"));
	  return;
	} 
	stopEditing();
	Editor edit = new Editor(applet, resourceBundle);
	edit.setVisible(true); 
	edit.setText(JTable1.getValueAt(JTable1.getSelectedRow(),0).toString(), 
			JTable1.getValueAt(JTable1.getSelectedRow(),1).toString(), JTable1, JTable1.getSelectedRow());
  }	






  //<Begin_class_PopupMenuMouseListener>
public class PopupMenuMouseListener extends MouseAdapter{ 
  
public void mouseClicked(MouseEvent evt)

  { 
      if(evt.isMetaDown())
      {
         if(evt.getSource()==currFileLabel)
         {
         ((JPopupMenu)getEdit()).show(evt.getComponent(),evt.getX(),evt.getY());
         }
         if(evt.getSource()==Top)
         {
         ((JPopupMenu)getEdit()).show(evt.getComponent(),evt.getX(),evt.getY());
         }
         if(evt.getSource()==JTable1)
         {
         ((JPopupMenu)getEdit()).show(evt.getComponent(),evt.getX(),evt.getY());
         }
      }

  } 
}
//<End_class_PopupMenuMouseListener> 
  //<Begin_class_MenuToolBarAction>
public class MenuToolBarAction extends AbstractAction{ 
  
public void actionPerformed(ActionEvent evt)

  { 
      if(evt.getActionCommand().equals(resourceBundle.getString("Edit in Editor")))
      {
         //Please add the action code for menu Here Edit in editor

showEditor();
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Open")))
      {
         //Please add the action code for menu Here File

//showFileOpen();
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Save")))
      {
         //Please add the action code for menu Here Save
          save();
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Save as Text")))
      {
         //Please add the action code for menu Here Save as Text

          if(JTable1.getRowCount() == 0)
          {
              return;
          }  
          if(JTable1.isEditing())
          {
              ((DefaultCellEditor)JTable1.getCellEditor()).stopCellEditing();
          }
          try{
              String origFile = presentfile.toString();
              String ext = getExtension(presentfile);
              String targetFile = ""; 
              if(ext != null)
              {
                  targetFile = origFile.substring(0,origFile.indexOf(ext)-1);
              }
              else
              {
                  targetFile = origFile;  
              }
              targetFile = targetFile+".txt"; 
              FileOutputStream fos = new FileOutputStream(targetFile);
              PrintStream ps = new PrintStream(fos);
              
              for(int i=0;i<JTable1.getRowCount();i++)
              {
                  String str =JTable1.getValueAt(i,0).toString()+"||"+JTable1.getValueAt(i,1).toString()+"\n";  
                  ps.print(str);     
              }
              currFileLabel.setText(resourceBundle.getString("Saved File : ")+targetFile);
              
          }
          catch(Exception e) 
          {
          }
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Exit")))
      {
         //Please add the action code for menu Here Close

       close();
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Find")))
      {
         //Please add the action code for menu Here Find
          showFindDialog();
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Add")))
      {
         //Please add the action code for menu Here Add
          showAddDialog();
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Delete")))
      {
          //Please add the action code for menu Here Options
          deleteEntry();
      }
      /*if(evt.getActionCommand().equals(resourceBundle.getString("Edit in Editor")))
      {
         //Please add the action code for menu Here Edit in Editor
          showEditor();
	  }*/
      if(evt.getActionCommand().equals(resourceBundle.getString("Prominent")))
      {
         //Please add the action code for menu Here Prominent Entries
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("All Entries")))
      {
          //Please add the action code for menu Here All Entries
          reload();
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Help")))
      {
         //Please add the action code for menu Here Help
          HelpWindow help = new HelpWindow(resourceBundle);
          help.setSize(400,400);
          I18NUtils.WindowPosition(help);
          help.setVisible(true);
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Open")))
      {
         //Please add the action code for ToolItem Here open
          showFileOpen();
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Save")))
      {
         //Please add the action code for ToolItem Here Save
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Reload")))
      {
         //Please add the action code for ToolItem Here Reload
          reload();
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Prominent")))
      {
         //Please add the action code for ToolItem Here Prominent

          if(JTable1.getRowCount() == 0)
          {
              return;
          } 
          stopEditing();
          Vector prom = getProminent();
          if(prom != null)
          {
              for(int k=JTable1.getRowCount()-1;k>=0;k--)
              {
                  PropTableModel.removeRow(k);
              }
              int size = prom.size();
              for(int i=0; i<size; i++)
              {
                  PropTableModel.addRow((Vector)prom.elementAt(i));
              }
              currFileLabel.setText(resourceBundle.getString("Prominent Entries"));
          }
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Delete")))
      {
          //Please add the action code for ToolItem Here Delete
          //deleteEntry();
      }
      if(evt.getActionCommand().equals(resourceBundle.getString("Find")))
      {
         //Please add the action code for ToolItem Here Find
      }

  } 
}
//<End_class_MenuToolBarAction>





  public JPopupMenu getEdit()

  {
	//<Begin_getEdit> 
   if(Edit== null)
   {
      Edit = new JPopupMenu();
      Edit_in_editorMenuItem = new JMenuItem(resourceBundle.getString("Edit in editor"));
      Edit_in_editorMenuItem.setActionCommand(resourceBundle.getString("Edit in Editor"));
      Edit_in_editorMenuItem.addActionListener(menuToolBarAction);
      Edit.add(Edit_in_editorMenuItem);
   }
   return Edit;

  
   //<End_getEdit>
  }

public  String getString(String key, char shortcut)
{
    shortcut = Character.toUpperCase(shortcut);
     
        String ret = resourceBundle.getString(key);
        
        if (ret.indexOf(Character.toUpperCase(shortcut)) != -1)
            return ret;

        if (ret.indexOf(Character.toLowerCase(shortcut)) != -1)
            return ret;

        String blank = "";
        while (ret.endsWith(" ") || ret.endsWith("."))
        {
            if (ret.endsWith(" "))
                blank += " ";
            else if (ret.endsWith("."))
                blank += ".";
            ret = ret.substring(0, ret.length() - 1);
        }
        return ret + "(" + shortcut + ")" + blank;

}

  private void showInvalidFile()
  {

  }

    class MyResourceBundle extends java.util.ResourceBundle
    {
        PropertyResourceBundle bundle = null;

        public MyResourceBundle()
        {
        }

        public MyResourceBundle(InputStream stream) throws IOException
        {
            bundle = new PropertyResourceBundle(stream);
        }

        public Object handleGetObject(String key)
        {
            if(bundle == null)
            {
                return key;	
            }

            String value = (String)bundle.handleGetObject(key);
            if(value == null || "".equals(value) )
            {  
                return (Object)key;
            }
            else
            {
                return value;
            }
        }

        public Enumeration getKeys()
        {
            return bundle.getKeys();
        }        
    }

}
