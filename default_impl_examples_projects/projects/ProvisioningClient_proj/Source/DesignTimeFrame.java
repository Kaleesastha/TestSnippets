//$Id: DesignTimeFrame.java,v 1.1 2006/08/29 13:57:01 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.provisioning.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.border.*;
import java.net.URL;

import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.management.config.xml.*;


import com.adventnet.nms.util.NmsUtil;
import com.adventnet.management.i18n.AdventNetResourceBundle;

import com.adventnet.builder.persistentgui.*;
import com.adventnet.builder.runtimegui.*;
import com.adventnet.builder.gui.GuiStatusAndErrorListener;
import com.adventnet.builder.xmlparser.ParseHolder;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class DesignTimeFrame extends JFrame  implements GuiStatusAndErrorListener
{
    //<Begin_Variable_Declarations>

	public static AdventNetResourceBundle resourceBundle=null;
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ProvisioningClientResources";
	private boolean running=false;
	javax.swing.JPanel Top = null;
	private JMenuBar menuBar=null;
        private Font locf =null;
	JMenu FileMenu= null;
	JMenuItem   Load_TemplateMenuItem = null;
	JMenuItem   SaveMenuItem = null;
	JMenuItem   Save_AsMenuItem = null;
	JMenuItem   Default_LayoutMenuItem = null;
	JMenuItem   CloseMenuItem = null;
	JMenuItem   ExitMenuItem = null;
	JMenu PreviewMenu= null;
	JMenuItem   Full_PreviewMenuItem = null;
	JMenuItem   Partial_PreviewMenuItem = null;
	JMenu HelpMenu= null;
	JMenuItem   HelpMenuItem = null;
	MenuToolBarAction menuToolBarAction = new MenuToolBarAction();
	//<End_Variable_Declarations>

    private StyleSheetManager manager = new StyleSheetManager();
    private PersistentGUI gui;
    private boolean saved = false;
    private String savefilename = "";
    JTextField statusfield = new JTextField();
    Template template = null;
    File templateFile = null;
    String imageIcon;
    JPanel toolbarPanel;
    JButton open;
    JButton save;
    JButton preview;
    JButton fullPreview;
    int x;
    int y;
    int width;


    String[] toolBarClasses = {"javax.swing.JPanel","javax.swing.JScrollPane","javax.swing.JTabbedPane","javax.swing.JSplitPane"};
    JButton jb1 = new JButton(new ImageIcon("./images/JPanel.jpg"));
    JButton jb2 = new JButton(new ImageIcon("./images/JScrollPane.jpg"));
    JButton jb3 = new JButton(new ImageIcon("./images/JTabbedPane.jpg"));
    JButton jb4 = new JButton(new ImageIcon("./images/JSplitPane.jpg"));
    JButton toolbarButtons[] = {jb1,jb2,jb3,jb4};

	   File helpFile = new File("help/developer_guide/provisioning_framework/setting_templatelayouts/prov_layoutintro.html");
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
    public void init()
  {
        //<Begin_init> 
	initializeResourceBundle();
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+631,getPreferredSize().height+515); 
          setTitle(resourceBundle.getString("DesignTimeFrame"));
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

  
         //<End_init>
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "ProvisioningClientResources"; 
            }
        return value;

  
           //<End_getParameter_String>
    } 
    public void setUpProperties()
  {
        //<Begin_setUpProperties> 

  
  //<End_setUpProperties>
    } 
    public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();

  
        //<End_initVariables>
    } 
    public void setUpGUI(Container container)
  {
        //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));

  
//<End_setUpGUI_Container>
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






    public DesignTimeFrame()
  {
        //<Begin_DesignTimeFrame>
    pack();
  
    //<End_DesignTimeFrame>
    }

    public DesignTimeFrame(java.applet.Applet applet)
  {
        //<Begin_DesignTimeFrame_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_DesignTimeFrame_java.applet.Applet>
    }


    public void setUpMenus()
  {
        //<Begin_setUpMenus> 
   menuBar = new JMenuBar();
   FileMenu= getStandardsCompliantMenu(resourceBundle.getString("File"));
   FileMenu.setMnemonic('F');
   menuBar.add(FileMenu);
   Load_TemplateMenuItem= getStandardsCompliantMenuItem(resourceBundle.getString("Load Template"));
   Load_TemplateMenuItem.setMnemonic('O');
   Load_TemplateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
   Load_TemplateMenuItem.setActionCommand("Open Template");
   Load_TemplateMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(Load_TemplateMenuItem);
   SaveMenuItem= getStandardsCompliantMenuItem(resourceBundle.getString("Save"));
   SaveMenuItem.setMnemonic('S');
   SaveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
   SaveMenuItem.setActionCommand("Save");
   SaveMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(SaveMenuItem);
   Save_AsMenuItem= getStandardsCompliantMenuItem(resourceBundle.getString("Save As"));
   Save_AsMenuItem.setMnemonic('A');
   Save_AsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
   Save_AsMenuItem.setActionCommand("Save As");
   Save_AsMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(Save_AsMenuItem);
   Default_LayoutMenuItem= getStandardsCompliantMenuItem(resourceBundle.getString("Default Layout"));
   Default_LayoutMenuItem.setMnemonic('D');
   Default_LayoutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
   Default_LayoutMenuItem.setActionCommand("Default Layout");
   Default_LayoutMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(Default_LayoutMenuItem);
   CloseMenuItem= getStandardsCompliantMenuItem(resourceBundle.getString("Close"));
   CloseMenuItem.setMnemonic('C');
   CloseMenuItem.setActionCommand("Close");
   CloseMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(CloseMenuItem);
   ExitMenuItem= getStandardsCompliantMenuItem(resourceBundle.getString("Exit"));
   ExitMenuItem.setMnemonic('x');
   ExitMenuItem.setActionCommand("Exit");
   ExitMenuItem.addActionListener(menuToolBarAction);
   FileMenu.add(ExitMenuItem);
   PreviewMenu= getStandardsCompliantMenu(resourceBundle.getString("Preview"));
   PreviewMenu.setMnemonic('P');
   menuBar.add(PreviewMenu);
   Full_PreviewMenuItem= getStandardsCompliantMenuItem(resourceBundle.getString("Full Preview"));
   Full_PreviewMenuItem.setMnemonic('F');
   Full_PreviewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
   Full_PreviewMenuItem.setActionCommand("Full Preview");
   Full_PreviewMenuItem.addActionListener(menuToolBarAction);
   PreviewMenu.add(Full_PreviewMenuItem);
   Partial_PreviewMenuItem= getStandardsCompliantMenuItem(resourceBundle.getString("Partial Preview"));
   Partial_PreviewMenuItem.setMnemonic('P');
   Partial_PreviewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
   Partial_PreviewMenuItem.setActionCommand("Partial Preview");
   Partial_PreviewMenuItem.addActionListener(menuToolBarAction);
   PreviewMenu.add(Partial_PreviewMenuItem);
   HelpMenu= getStandardsCompliantMenu(resourceBundle.getString("Help"));
   HelpMenu.setMnemonic('H');
   menuBar.add(HelpMenu);
   HelpMenuItem= getStandardsCompliantMenuItem(resourceBundle.getString("Help"));
   HelpMenuItem.setMnemonic('H');
   HelpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
   HelpMenuItem.setActionCommand("Help");
   HelpMenuItem.addActionListener(menuToolBarAction);
   HelpMenu.add(HelpMenuItem);
   this.setJMenuBar(menuBar);

  
   //<End_setUpMenus>
    } 
    public JMenu getStandardsCompliantMenu(String menuName)
  {
        //<Begin_getStandardsCompliantMenu_String> 

   javax.swing.JMenu newInstance = new javax.swing.JMenu(menuName);
          try
          {
            newInstance.setFont(locf);
            newInstance.setForeground(new Color(-16777216));
            newInstance.setVerticalAlignment(0);
            newInstance.setVerticalTextPosition(0);
            newInstance.setHorizontalAlignment(2);
            newInstance.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
	   	showStatus(java.text.MessageFormat.format(resourceBundle.getString("Exception while setting properties for bean {0}"),new Object[] {newInstance}),ex); 
          }
   return newInstance;
  
   //<End_getStandardsCompliantMenu_String>
    } 
    public JMenuItem getStandardsCompliantMenuItem(String menuItemName)
  {
        //<Begin_getStandardsCompliantMenuItem_String> 

   javax.swing.JMenuItem newInstance = new javax.swing.JMenuItem(menuItemName);
          try
          {
            newInstance.setFont(locf);
            newInstance.setForeground(new Color(-16777216));
            newInstance.setVerticalAlignment(0);
            newInstance.setVerticalTextPosition(0);
            newInstance.setHorizontalAlignment(2);
            newInstance.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
		  showStatus(java.text.MessageFormat.format(resourceBundle.getString("Exception while setting properties for bean {0}"),new Object[]{newInstance}),ex); 
          }
   return newInstance;
  
   //<End_getStandardsCompliantMenuItem_String>
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
      if(evt.getActionCommand().equals("Open Template"))
      {
         //Please add the action code for " Open Template "
openTemplate();
      }
      if(evt.getActionCommand().equals("Save"))
      {
         //Please add the action code for " Save "
saveStyleSheet();
      }
      if(evt.getActionCommand().equals("Save As"))
      {
         //Please add the action code for " Save As "
saveStyleSheetAs();
      }
      if(evt.getActionCommand().equals("Default Layout"))
      {
         //Please add the action code for " Start From Scratch "
startFromScratch();
      }
      if(evt.getActionCommand().equals("Close"))
      {
         //Please add the action code for " Close "
closeAction();
      }
      if(evt.getActionCommand().equals("Exit"))
      {
         //Please add the action code for " Exit "
System.exit(0);
      }
      if(evt.getActionCommand().equals("Full Preview"))
      {
         //Please add the action code for " Full Preview "
fullPreview();
      }
      if(evt.getActionCommand().equals("Partial Preview"))
      {
         //Please add the action code for " Show Preview "
showPreview();
      }
      if(evt.getActionCommand().equals("Help"))
      {
         //Please add the action code for " Help "
helpAction();
      }

  } 
}
//<End_class_MenuToolBarAction>

    private void openTemplate()
    {
        StyleSheetFileFilter filter = new StyleSheetFileFilter("OPEN");
        JFileChooser  fileSelector = new JFileChooser("./provisioningtemplates");
        fileSelector.setDialogTitle(resourceBundle.getString("Load Template"));
        fileSelector.setFileFilter(filter);
        int returnValue = fileSelector.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            setBusyCursor();
            try
            {
                File f = fileSelector.getSelectedFile();
                if(f.exists())
                {
                    saved = false;
                    templateFile = f;
                    String templateName = templateFile.getName();
		    setTitle(java.text.MessageFormat.format(resourceBundle.getString("Layout Manager {0}"),new String[] {templateName}));
                    String filename = templateFile.getAbsolutePath();
                    BufferedReader br = new BufferedReader(new FileReader(templateFile));
                    StringBuffer buffer = new StringBuffer("");
                    String s = br.readLine();
                    while(s != null)
                    {
                        buffer.append(s);
                        buffer.append("\n");
                        s = br.readLine();
                    }
                    template = new Template(new String(buffer));
                    String stylesheet = template.getAttribute("stylesheet");
                    File stylesheetFile = new File(stylesheet);
                    Hashtable ht = null;
                    if(stylesheet == null || stylesheet.trim().equals("") || (!stylesheetFile.exists()))
                    {
                        ht = manager.getMainHashtable(template);
                    }
                    else
                    {
                        ht = manager.getHashtableForStyleSheet(template,stylesheet);
                        savefilename = stylesheet;
                        saved = true;
                    }
                    getContentPane().removeAll();
                    if(gui == null)
                    {
                        gui = new PersistentGUI(this);
                        gui.setToolBarItems(toolBarClasses,toolbarButtons);
                        gui.setGuiStateErrorListener(this);
                    }
                    gui.openFile(ht);
                    SaveMenuItem.setEnabled(true);
                    Save_AsMenuItem.setEnabled(true);
                    Default_LayoutMenuItem.setEnabled(true);
                    Full_PreviewMenuItem.setEnabled(true);
                    Partial_PreviewMenuItem.setEnabled(true);	
                    CloseMenuItem.setEnabled(true);
                    save.setEnabled(true);
                    addToolbarAndStatus();
		    statusfield.setText(java.text.MessageFormat.format(resourceBundle.getString("Loaded Template {0}"),new String[] {templateName}));
                    if(!(stylesheet.trim().equals("")) && !(stylesheetFile.exists()))
                    {
			statusfield.setText(java.text.MessageFormat.format(resourceBundle.getString("Style sheet file  {0} does not exists. Using default layout"),new Object[]{stylesheetFile}));
                    }

                    if(!template.isTreeNeeded())
                    {
                        JTabbedPane designTimePane = manager.getDesigntimePane();
                        if(designTimePane != null)
                        {
                            int count = designTimePane.getTabCount();
                            if(count > 0)
                            {
                                designTimePane.setSelectedIndex(0);
                                designTimePane.repaint();
                            }
                        }
                    }
                }
                validate();
            }
            catch(Exception e)
            {
                statusfield.setText(resourceBundle.getString("Error Loading Template"));
                e.printStackTrace();
            }
            setNormalCursor();
        }
    } 

    private void startFromScratch()
    {
        if(template != null)
        {
            setBusyCursor();
            Hashtable    ht = manager.getMainHashtable(template);
            getContentPane().removeAll();
            //gui = new PersistentGUI(this);
            //gui.setToolBarItems(toolBarClasses,toolbarButtons);
            //gui.setGuiStateErrorListener(this);
            gui.openFile(ht);
            SaveMenuItem.setEnabled(true);
            addToolbarAndStatus();
	    statusfield.setText(java.text.MessageFormat.format(resourceBundle.getString("Loaded Template {0}"),new String[]{templateFile.getName()}));

            setNormalCursor();
            JTabbedPane designTimePane = manager.getDesigntimePane();
            if(designTimePane != null)
            {
                int count = designTimePane.getTabCount();
                if(count > 0)
                {
                    designTimePane.setSelectedIndex(0);
                    designTimePane.repaint();
                }
                for(int i = 1 ; i < count ; i++)
                {
                    designTimePane.getComponent(i).setVisible(false);
                }
            }
            validate();
        }
    }

    private void saveStyleSheetAs()
    {
        saved = false;
        saveStyleSheet();
    }

    private void setBusyCursor()
    {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    private void setNormalCursor()
    {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private void showErrorDialog(String message)
    {
        JOptionPane.showMessageDialog(this,message,resourceBundle.getString("ERROR") ,JOptionPane.ERROR_MESSAGE);
    }

    private void showWarningDialog(String message)
    {
        JOptionPane.showMessageDialog(this,message,resourceBundle.getString("WARNING"),JOptionPane.WARNING_MESSAGE);
    }

    private void saveStyleSheet()
    {
        if(saved)
        {
            manager.saveFile(savefilename,gui.getScreenHash());		
            setTextAndClear(savefilename);
            return;
        }
        JFileChooser chooser = new JFileChooser("./stylesheets");
        chooser.setFileFilter(new StyleSheetFileFilter("SAVE"));
        int returnValue = chooser.showSaveDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();
            String filename = file.getAbsolutePath();
            String name = file.getName();
            if(!filename.endsWith(".tss"))
            {
                filename = filename + ".tss";
            }
            file = new File(filename);
            manager.saveFile(filename,gui.getScreenHash());		
            savefilename = filename;
            String currentdir = System.getProperty("user.dir");
            int index = filename.indexOf(currentdir);
            if( index != -1)
            {
                String fname = filename.substring(index + currentdir.length());
                fname = fname.replace('\\','/');
                template.setAttribute("stylesheet","." + fname);
            }
            else
            {
                template.setAttribute("stylesheet",file.getName());
            }
            if(template != null)
            {
                try 
                {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(templateFile));
                    Element element = template.getElement();

                    String encoding = "ISO-8859-1";
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    DOMSource source = new DOMSource( element );
                    StreamResult result = new StreamResult( writer );
                    Properties prop = new Properties();
                    prop.put( OutputKeys.INDENT, "yes" );
                    prop.put( OutputKeys.ENCODING, encoding );
                    prop.put( OutputKeys.METHOD, "xml" );
                    prop.put( OutputKeys.DOCTYPE_SYSTEM,"Template.dtd");
                    transformer.setOutputProperties( prop );
                    transformer.transform( source, result );

                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            setTextAndClear(savefilename);
            saved = true;
        }
    }

    private void setTextAndClear(String name)
    {
        setBusyCursor();
	 statusfield.setText(java.text.MessageFormat.format(resourceBundle.getString("File {0} saved successfully"),new String[]{name}));
        try
        {
            Thread.sleep(325);
        }catch(InterruptedException ex)
        {

        }
        setNormalCursor();
    }

    class StyleSheetFileFilter extends javax.swing.filechooser.FileFilter
    {
        String extension="";

        StyleSheetFileFilter(String s)
        {
            if(s != null)
            {
                if(s.equals("OPEN")) extension = ".xml";
                else if(s.equals("SAVE"))  extension = ".tss";
            }
        }
        public boolean accept(File f)
        {
            if(f.isDirectory())  return true;
            String filename = f.getAbsolutePath();
            if(filename.endsWith(extension)) return true;
            return false;
        }

        public String getDescription()
        {
            if(extension.equals(".xml")) return resourceBundle.getString("Provisioning Templates");
            else return resourceBundle.getString("Files of type *.tss");
        }
    }

    public void initialize()
    {
        createToolbar();
        setTitle(resourceBundle.getString("Layout Manager"));
        SaveMenuItem.setEnabled(false);
        Save_AsMenuItem.setEnabled(false);
        Default_LayoutMenuItem.setEnabled(false);
        Full_PreviewMenuItem.setEnabled(false);	
        Partial_PreviewMenuItem.setEnabled(false);
        CloseMenuItem.setEnabled(false); 
        save.setEnabled(false);
        setIconImage(getImage(imageIcon));
       


    }

    public Image getImage(String image)
    {
        if(image == null || image.trim().equals("")) return null;
        ImageIcon icon =  new ImageIcon(imageIcon);
        return icon.getImage();
    }

    private void addToolbarAndStatus()
    {
        getContentPane().add(statusfield,"South");
        for(int i = 0 ; i < toolbarButtons.length ; i++)
        {
            JButton jb = toolbarButtons[i];
            jb.setToolTipText(toolBarClasses[i]);
            jb.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            //jb.addMouseListener(new ToolbarButtonListener());
        }
        JToolBar tb = gui.getToolBar();
        tb.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("Containers")));
        JPanel maintoolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        getContentPane().remove(tb);
        maintoolPanel.add(tb);
        JPanel jp = getToolBar();
        maintoolPanel.add(jp);
        getContentPane().add(maintoolPanel,"North");
    }

    class ToolbarButtonListener extends MouseAdapter
    {
        public void mouseEntered(MouseEvent me)
        {
            JButton jb = (JButton)me.getSource();
            jb.setBorder(BorderFactory.createRaisedBevelBorder());
        }
        public void mouseExited(MouseEvent me)
        { 
            JButton jb = (JButton)me.getSource();
            jb.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        }
    }

    private JPanel getToolBar()
    {
        return toolbarPanel;
    }
    
    private void createToolbar()
    {
        toolbarPanel = new JPanel(new BorderLayout());
        JToolBar tb1 = new JToolBar();
        tb1.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("Operations")));
        open = tb1.add(new TBAction("images/loadtemplate.png",resourceBundle.getString("Load Template"),"Load"));
        save = tb1.add(new TBAction("images/save.png",resourceBundle.getString("Save"),"Save"));
        preview = tb1.add(new TBAction("images/preview.png",resourceBundle.getString("Partial Preview"),"Preview"));
        fullPreview = tb1.add(new TBAction("images/fullpreview.png",resourceBundle.getString("Full Preview"),"FullPreview"));
        open.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        save.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        preview.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        fullPreview.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        toolbarPanel.add(tb1,"East");
    } 



    class TBAction extends AbstractAction
    {
        String imageFileName = "";
        String tooltip = "";
        String actionCommand = "";
        TBAction(String key,String tooltip,String ac)
        {
            imageFileName = key;
            this.tooltip = tooltip;
            actionCommand = ac;
        }
        public Object getValue(String key)
        {
            if(key.equals(SMALL_ICON))
            {
                return new ImageIcon(imageFileName);
            }
            else if(key.equals(SHORT_DESCRIPTION))
            {
                return tooltip;
            }
            else
            {
                return null;
            }
        }
        public void actionPerformed(ActionEvent ae)
        {
            if(actionCommand.equals("Load"))
            {
                openTemplate();
            }
            else if(actionCommand.equals("Save"))
            {
                saveStyleSheet();
            }
            else if(actionCommand.equals("Preview"))
            {
                showPreview();
            }
            else if(actionCommand.equals("FullPreview"))
            {
                fullPreview();
            }
        }
    }

    private void createStatusField()
    {
        statusfield.setEnabled(false);
        statusfield.setEditable(false);
        statusfield.setFont(locf);
        statusfield.setForeground(Color.blue);
    }
	
   private void fullPreview()
   {
        PreviewPanel previewPanel = new PreviewPanel();
	ProvClientUtils.englishToNative=resourceBundle;
        previewPanel.locf=locf;
        previewPanel.init();
        Hashtable ht = gui.getScreenHash();
         JPanel jp = manager.getFullPreviewPanel(template,ht);
         if(jp == null) return;
 	jp.setBorder(new TitledBorder(new LineBorder(Color.gray,1),java.text.MessageFormat.format(resourceBundle.getString("Template : {0}"),new String[]{template.getName()}),0,2));
	String title = java.text.MessageFormat.format(resourceBundle.getString("Preview for {0}"),new String[]{template.getName()});       
        previewPanel.setTitle(title);
        int width = ParseHolder.getInt("Screen Width",ht,600);
        int height = ParseHolder.getInt("Screen Height",ht,550);
        jp.setPreferredSize(new Dimension(width,height));
       previewPanel.setLocation(x,y);
       previewPanel.showTemplate(jp);
   }

    private void showPreview()
    {
        PreviewPanel previewPanel = new PreviewPanel();
       ProvClientUtils.englishToNative=resourceBundle;
	previewPanel.locf=locf;
        previewPanel.init();
        Hashtable ht = gui.getScreenHash();
        JPanel jp = manager.getPreviewPanel(template,ht);
        if(jp == null) return;
	jp.setBorder(new TitledBorder(new LineBorder(Color.gray,1),java.text.MessageFormat.format(resourceBundle.getString("Template : {0}"),new String[]{template.getName()}),0,2));
	String title = java.text.MessageFormat.format(resourceBundle.getString("Preview for {0}"),new String[]{template.getName()});
        previewPanel.setTitle(title);
        int width = ParseHolder.getInt("Screen Width",ht,600);
        int height = ParseHolder.getInt("Screen Height",ht,550);
        jp.setPreferredSize(new Dimension(width,height));
        previewPanel.setLocation(x,y);
        previewPanel.showTemplate(jp);
    }

    public static void main(String[] ar)
    {
        DesignTimeFrame frame = new DesignTimeFrame();
        int length = ar.length;
        for(int i = 0 ; i < length ; i++)
        {
            String arg = ar[i];
            int index = arg.indexOf('=');
            if(index != -1)
            {
                String key = arg.substring(0,index);
                String value = arg.substring(index+1);
                if(key.equals("IMAGE_ICON")) frame.imageIcon = value;
                if(key.equals("HELP_FILE"))
                {
                    File f = new File(value);
                    if(f.exists()) frame.helpFile = f;
                }
            }
        }
        frame.init();
        frame.initialize();
        frame.createStatusField();
        //JPanel toolbarPanel = frame.getToolBar(); 
        //frame.getContentPane().add(toolbarPanel,"North");
        InitialPanel panel = frame.new InitialPanel();
        frame.getContentPane().add(panel);
        frame.setSize(frame.width+10,600);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        frame.x = (width-700)/2;
        frame.y = (height-600)/2;
        if(frame.x < 0 || frame.y < 0)
        {
            frame.x = 0;
            frame.y = 0;
        }
        frame.setLocation(frame.x,frame.y);
        frame.show();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void helpAction()
    {
        try
        {
            setBusyCursor();
            com.adventnet.nms.util.BrowserControl.displayURL(helpFile.toURL().toString());
            setNormalCursor();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	
	private void closeAction()
	{
		SaveMenuItem.setEnabled(false);
		Save_AsMenuItem.setEnabled(false);
                             Default_LayoutMenuItem.setEnabled(false);
	               Full_PreviewMenuItem.setEnabled(false);	
		Partial_PreviewMenuItem.setEnabled(false);
		CloseMenuItem.setEnabled(false);
		getContentPane().removeAll();
		getContentPane().add(new InitialPanel());
		validate();
		repaint();
	}

    class InitialPanel extends JPanel
    {
	
	InitialPanel()
	{
		setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/LayoutManager.png");
        width = icon.getIconWidth();
		JLabel label = new JLabel(icon);
		add(label,"Center");
	}
/*        
	public void paintComponent(Graphics g)
             {
            super.paintComponent(g);
            g.setFont(new Font("SansSerif",Font.BOLD,14));
            g.drawString(resourceBundle.getString("Steps for using Layout Manager") , 25,75);
            g.drawString("1."+resourceBundle.getString("Load Template"),50,100);
            g.drawString("2. "+resourceBundle.getString("Change the Layouts of Forms") , 50 , 125);
            g.drawString("3. "+resourceBundle.getString("Save the Style Sheet"), 50,150);
            g.drawString("4. "+resourceBundle.getString("Invoke the Template using Provisioning Client or OpenTemplate.bat/sh"),50,175);
            g.drawString("5. "+resourceBundle.getString("The Saved layout will be reflected in the UI during runtime"),50,200);
        }
  */  
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

    //GuiStatusAndErrorListener implementations
    public void showStatusMessage(String msgArg)
    {
    }
    public void showStatusError(String errMsgArg)
    {
        System.err.println(errMsgArg);
    }
    
    public void showMessage(String msgArg)
    {
    }
    public void showError(String errMsgArg)
    {
        System.err.println(errMsgArg);
    }
    public void showError(String errMsgArg,Throwable thArg) 
    {
        System.err.println( errMsgArg);
        if(!(thArg instanceof NullPointerException))
        {
            showErrorDialog(thArg.getMessage());
        }
    }
    public void showError(String errMsgArg,Throwable thArg,String showTillArg)
    {
        System.err.println( errMsgArg);
    }

private void initializeResourceBundle()
	{
	String coun ="";
	String lan="";
        String fname ="";

        
	 try{

	       	NmsUtil.readServerParams();
		 coun=NmsUtil.getParameter("COUNTRY");
		 lan =NmsUtil.getParameter("LANGUAGE");
		fname=NmsUtil.getParameter("FONT");
		locf= parseAndGiveTheFont (fname);
                
	}catch(Exception e)	
	{
			String errorMessage = e.getMessage() != null ? e.getMessage() : e.toString();
			System.err.println("Cannot instantiate resource bundle : "+errorMessage);
	}
		
		if  (coun== null && lan == null){
		
		Locale loc = Locale.getDefault();
		coun = loc.getCountry();
		lan = loc.getLanguage();
		
		}
		initializeEnglishToNative(lan, coun);
	}
	
    private void initializeEnglishToNative(String lang,String country)
	{
		FileInputStream fis =null;
		try
		{
		StringBuffer fileBuf=new StringBuffer("html/EnglishToNative");
            if((lang!=null)&&(country!=null))
			{
				fileBuf.append("_");
				fileBuf.append(lang);
				fileBuf.append("_");
				fileBuf.append(country);
			}
			fileBuf.append(".properties");
			if(!(new File(fileBuf.toString()).exists()))
			{
				fileBuf = new StringBuffer("html/EnglishToNative.properties");
			}
			fis = new FileInputStream(fileBuf.toString());
		        resourceBundle = new AdventNetResourceBundle(fis);
		}
		catch(Exception e)
		{
			String errorMessage = e.getMessage() != null ? e.getMessage() : e.toString();
			System.err.println("Cannot instantiate resource bundle : "+errorMessage);
		}
		finally
		{
			if(fis !=null)
			{
				try
				{
					fis.close();
				}
				catch(Exception e)
				{
					System.err.println("Cannot close internationalization property file");
				}
			}
		}

	}
        private static Font parseAndGiveTheFont (String something)
        {
          Font f =null;
                StringTokenizer str = new StringTokenizer (something, ",");//No Internationalisation
                if (str.countTokens () != 3)
                {
                        System.err.println (resourceBundle.getString("Font not specified properly: ") + something);
                        f = new Font ("Dialog", Font.PLAIN, 12);//No Internationalisation
                }
                try
                {
                        String name = str.nextToken ();
                        int style = Integer.parseInt (str.nextToken ());
                        int size = Integer.parseInt (str.nextToken ());
                        f = new Font (name, style, size);
                }
                catch (Exception e)
                {
                //        return parseAndGiveTheFont ("something");//No Internationalisation
                        String errorMessage = e.getMessage() != null ? e.getMessage() : e.toString();
                        System.err.println("Cannot instantiate Font : "+errorMessage);

                }
	return f;

        }


}











