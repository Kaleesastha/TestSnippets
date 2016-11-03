//$Id: InitScreen.java,v 1.2.6.1 2012/09/10 05:54:12 wesley Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$2
//<End_Version>
package com.adventnet.nms.tools.confchanges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.tools.utils.ImgConv;
import java.net.URL;

public class InitScreen extends JDialog implements ActionListener,Runnable,ConfExceptionHandler
{
    //<Begin_Variable_Declarations>
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    private String localePropertiesFileName = "ConfFileUpdationResources";
//    static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
// BuilderResourceBundle class is avaliable in the package com.adventnet.nms.tools.confchanges;    
    static BuilderResourceBundle resourceBundle = null;
    private boolean running=false;
    javax.swing.JPanel Top = null;
    javax.swing.JLabel JLabel1 = null;
    javax.swing.JPanel JPanel2 = null;
    javax.swing.JButton previous = null;
    javax.swing.JButton next = null;
    javax.swing.JButton close = null;
    javax.swing.JButton help = null;
    javax.swing.JPanel cardPanel = null;
    //<End_Variable_Declarations>
    
    CardLayout card = null;
    LogScreen log = null;
    QueryDetails query = null;
    RevertConfiguration revert = null;
    UpdateConfiguration update = null;
    SelectionScreen select = null;
    ConfUpgradeManager confMgr = null;
    URL url1=null;
    String nmsHome="";
    String version="";
    String installedVersions=null;
    String action="";
    Thread thread=null;
    private boolean isChild=false;
    private boolean operationPerformed = false;
    private UpdateConfChanges changes = null;



    public int handleException(Exception ex, ConfNode conf_node)
    {
        
        int user_preferrence  = conf_node.getPriority();

        if(user_preferrence == ConfExceptionHandler.ABORT_AND_ROLLBACK)
        {
            JOptionPane.showMessageDialog(this," Error occurred. All the Configuration Changes will be reverted to the previous version.","Message",JOptionPane.INFORMATION_MESSAGE);
        }
        
        return user_preferrence;
    }

    public void setOperationPerformed(boolean value)
    {
        operationPerformed = value;
    }
    
    public boolean getOperationPerformed()
    {
        return operationPerformed;
    }

    public InitScreen()
    {
				//<Begin_InitScreen>
        pack();
        
        //<End_InitScreen>
    }

    public InitScreen(java.applet.Applet applet)
    {
				//<Begin_InitScreen_java.applet.Applet>
        this.applet = applet;
        pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
        //<End_InitScreen_java.applet.Applet>
    }

    public InitScreen(String home)
    {
        setModal(true);
        nmsHome=home;
        pack();
    }
    public InitScreen(String home,JDialog parent)
    {
        super(parent,true);
        isChild = true;
        nmsHome=home;
        pack();
    }      

    // Issue :: Multiple instance of UpdateConfChanges created . Fixed 

    
    public InitScreen(String home,UpdateConfChanges changes)
    {
        setModal(true);
        this.changes = changes;
        nmsHome=home;
        pack();
    }
    public InitScreen(String home,JDialog parent,UpdateConfChanges updateChange)
    {
        super(parent,true);
        this.changes = updateChange;
        isChild = true;
        nmsHome=home;
        pack();
    }      

    
    public void init()
    {
				//<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
            localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+693,getPreferredSize().height+490); 
        setTitle(resourceBundle.getString("InitScreen"));
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

        setTitle(resourceBundle.getString("Configuration Update Tool"));
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
            value = (String)Utility.getParameter(input);
        }    
        if(value == null)
        {
            if (input.equals("RESOURCE_PROPERTIES")) value = "ConfFileUpdationResources"; 
        }
        return value;

  
        //<End_getParameter_String>
    } 
    public void setUpProperties()
    {
				//<Begin_setUpProperties> 

        try
        {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
        }
        
        //<UserCode_Begin_Bean_JLabel1>


			
        //<UserCode_End_Bean_JLabel1>

        try
        {
            JPanel2.setBorder(new javax.swing.border.LineBorder(new Color(-16764109),1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
        }

        //<UserCode_Begin_Bean_JPanel2>


			
        //<UserCode_End_Bean_JPanel2>

        try
        {
            previous.setFont(new Font("SansSerif",0,12));
            previous.setHorizontalTextPosition(4);
            previous.setText(resourceBundle.getString("Previous"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+previous,ex); 
        }

        //<UserCode_Begin_Bean_previous>


			
        //<UserCode_End_Bean_previous>

        try
        {
            next.setFont(new Font("SansSerif",0,12));
            next.setHorizontalTextPosition(4);
            next.setText(resourceBundle.getString("   Next   "));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+next,ex); 
        }

        //<UserCode_Begin_Bean_next>


			
        //<UserCode_End_Bean_next>

        try
        {
            close.setFont(new Font("SansSerif",0,12));
            close.setHorizontalTextPosition(4);
            close.setText(resourceBundle.getString("Close"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+close,ex); 
        }

        //<UserCode_Begin_Bean_close>


			
        //<UserCode_End_Bean_close>

        try
        {
            help.setFont(new Font("SansSerif",0,12));
            help.setHorizontalTextPosition(4);
            help.setText(resourceBundle.getString("Help"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+help,ex); 
        }

        //<UserCode_Begin_Bean_help>


			
        //<UserCode_End_Bean_help>
        JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+181,JLabel1.getPreferredSize().height+390));

  
        //<End_setUpProperties>
        url1=getClass().getResource("images"+File.separator+"confupdate.jpg");
        if(url1!=null)
        {
            JLabel1.setIcon(new ImageIcon(ImgConv.getImage(url1)));
        }
        previous.setEnabled(false);
        card = new CardLayout();
        cardPanel.setLayout(card);
        cardPanel.add("select",select);
        cardPanel.add("Update",update);
        cardPanel.add("Revert",revert);
        cardPanel.add("Query",query);
        cardPanel.add("Log",log);
        next.addActionListener(this);
        next.setActionCommand("Next");
        next.setMnemonic('n');
        previous.addActionListener(this);
        previous.setActionCommand("Previous");
        previous.setMnemonic('p');
        close.addActionListener(this);
        close.setActionCommand("close");
        close.setMnemonic('c');
        help.addActionListener(this);
        help.setActionCommand("help");
        help.setMnemonic('h');
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
        JLabel1= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        previous= new javax.swing.JButton();
        next= new javax.swing.JButton();
        close= new javax.swing.JButton();
        help= new javax.swing.JButton();
        cardPanel= new javax.swing.JPanel();

  
        //<End_initVariables>
        if(changes == null)
        {
            changes = UpdateConfChanges.getInstance();
        }

        changes.checkForVersion();
        version=changes.getCurrentVersion();
        installedVersions=changes.getInstalledVersions();
        log = new LogScreen(nmsHome);
        query = new QueryDetails(nmsHome,changes);
        update = new UpdateConfiguration(nmsHome,changes);
        revert = new RevertConfiguration(nmsHome);
        select = new SelectionScreen();
        revert.setCurrentVersion(version,installedVersions);
        select.setCurrentVersion(version);

    } 
    public void setUpGUI(Container container)
    {
				//<Begin_setUpGUI_Container> 
        container.add(Top,BorderLayout.CENTER);
        Top.setLayout(new BorderLayout(5,5));
        Top.add(JLabel1,BorderLayout.WEST);
        Top.add(JPanel2,BorderLayout.SOUTH);
        JPanel2.setLayout(new FlowLayout(1,5,5));
        JPanel2.add(previous);
        JPanel2.add(next);
        JPanel2.add(close);
        JPanel2.add(help);
        Top.add(cardPanel,BorderLayout.CENTER);
        cardPanel.setLayout(new BorderLayout(5,5));

  
        //<End_setUpGUI_Container>
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension dim=kit.getScreenSize();
        this.setLocation((dim.width-this.getWidth())/2,(dim.height-this.getHeight())/2);
    } 
    public void setUpConnections()
    {
				//<Begin_setUpConnections> 

  
        //<End_setUpConnections>
        this.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    if(!getOperationPerformed())
                    {
                        if((JOptionPane.showConfirmDialog(null,"Do you really want to close","Caution",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION))
                        {
                            return;
                        }
                    }
                    else
                    {
                        String message[] = {"      Please close the Web NMS Launcher, if it is running.",
                                            " The Configuration Update Tool has updated its configuration ",
                                            " Please ignore this message if the Web NMS Launcher is not running"};	
                        JOptionPane.showMessageDialog(null,message,"warning",JOptionPane.WARNING_MESSAGE);																									
                    }
                    if(isChild)
                    {
			String jvmname = System.getProperty("java.vm.name").toLowerCase();
                        if(jvmname.contains("openjdk"))
                        {
                                dispose();
                        }
                        else
                        {
                                setVisible(false);
                                dispose();
                        }
                    }
                    else
                    {
                        System.exit(1);
                    }
                }
            });		
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

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().trim().equals("Previous"))
        {
            card.show(cardPanel,"select");
            previous.setEnabled(false);
            next.setEnabled(true);
            next.setText("Next");
            next.setActionCommand("Next");
        }
        else if(ae.getActionCommand().trim().equals("Next"))
        {
            String javaHome = System.getProperty("JAVAHOME");
            String selected=select.getSelection();
            if(selected.equals("Revert"))
            {
                if(isChild)
                {
                    revert.setShowTextField(true);
                    revert.showTextField();
                }
                else
                {
                    revert.setShowTextField(false);
                    revert.showTextField();
                }
            }
            else if(selected.equals("Query"))
            {
                next.setEnabled(false);
            }
            else if(selected.equals("Update"))
            {
                if(javaHome != null && !javaHome.trim().equals(""))
                {
                    if(System.getProperty("os.name").trim().toLowerCase().startsWith("win"))
                    {
                        File javac = new File(javaHome + File.separator + "bin" + File.separator + "javac.exe");
                        if(!javac.exists())
                        {
                            JOptionPane.showMessageDialog(this,"Enter a Valid Java Home Eg : c:\\jdk1.5 ","Error",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
			else if(javaHome.indexOf(" ")!=-1)
			{//file present... then check for space in the directory

                            JOptionPane.showMessageDialog(this,"Java Home contains space,Edit the home to remove space like: c:\\Progra~1\\jdk1.5","Error",JOptionPane.ERROR_MESSAGE);
			    return;
			}
                    }
                    else
                    {
                        File javac = new File(javaHome + File.separator + "bin" + File.separator + "javac");
                        if(!javac.exists())
                        {
                            JOptionPane.showMessageDialog(this,"Enter a Valid Java Home Eg : /usr/java/jdk1.5 ","Error",JOptionPane.ERROR_MESSAGE);
                            
                            return;
                        }
                    }
                }
                else
                {
                    String message[] = {"JDK Home is not set, JSP file modified for this Service Pack",
                                        "                   will not be compiled.",
                                        "         Use compileJSP.bat/sh file to compile them         "}; 
                    JOptionPane.showMessageDialog(this,message,"warning",JOptionPane.WARNING_MESSAGE);
                }
                //select.setLanguageToSystem();
            }
            card.show(cardPanel,selected);
            
            previous.setEnabled(true);
            next.setText(selected);
            next.setActionCommand(selected);
        }
        else if(ae.getActionCommand().trim().equals("Update"))
        {
            if(update.checkForFileSelection())
            {
                log.setLogInfo();
                card.show(cardPanel,"Log");
                next.setText("Next");
                next.setEnabled(false);
                action="update";
                thread = new Thread(this);
                setOperationPerformed(true);
                thread.start();					
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Please select files for UPDATE","Message",JOptionPane.INFORMATION_MESSAGE);
            }	

        }
        else if(ae.getActionCommand().trim().equals("Revert"))
        {
            version=revert.getSelectedVersion();
            
            if(version!=null&&!version.trim().equals(""))
            {
                revert.setLogInfo();
                action="revert";
                thread=new Thread(this);
                setOperationPerformed(true);
                thread.start();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"No Previous version available for REVERT","Message",JOptionPane.INFORMATION_MESSAGE);
            }
            next.setText("Next");
            next.setEnabled(false);

        }
        else if(ae.getActionCommand().trim().equals("close"))
        {
            if(!getOperationPerformed())
            {
                if((JOptionPane.showConfirmDialog(this,"Do you really want to close","Caution",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION))
                {
                    return;
                }
            }
            else
            {
                String message[] = {"      Please close the Web NMS Launcher, if it is running.",
                                    " The Configuration Update Tool has updated its configuration ",
                                    " Please ignore this message if the Web NMS Launcher is not running"};	
                JOptionPane.showMessageDialog(this,message,"warning",JOptionPane.WARNING_MESSAGE);																									
            }
            if(isChild)
            {
		    String jvmname = System.getProperty("java.vm.name").toLowerCase();
		    if(jvmname.contains("openjdk"))
		    {
			    dispose();
		    }
		    else
		    {
			    setVisible(false);
			    dispose();
		    }
            }
            else
            {
                System.exit(1);
            }
        }

        else if(ae.getActionCommand().trim().equals("help"))
        {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try{
		 String url=nmsHome+File.separator+"help"+File.separator+"installation_guide"+File.separator+"installation_and_setup"+File.separator+"installing_service_pack.html";
                File helpFile = new File(url);
		if(!helpFile.exists())
		{
			//If help File doesn't exist show the index.html
	                url = nmsHome + File.separator + "help" + File.separator + "installation_guide" + File.separator + "index.html";	
		}
		BrowserControl.displayURL(url);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }



    public void run()
    {
        enableButtons(false);
        
        if(action.equals("update"))
        {
            try
            {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                
                boolean result = changes.updateChanges(update.getFilesAsXMLNode());
                
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                
                version=changes.getCurrentVersion();
                installedVersions = changes.getInstalledVersions();
                revert.setCurrentVersion(version,installedVersions);
                select.setCurrentVersion(version);

                if(result)
                {
                    JOptionPane.showMessageDialog(this,"Update Process Complete","Message",JOptionPane.INFORMATION_MESSAGE);
                }

            }
            catch(Exception e)
            {			
                JOptionPane.showMessageDialog(this,"Update Process Failed","Message",JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace();
            }
        }
        else if(action.equals("revert"))
        {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            changes.revertChanges(version);
            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            
            installedVersions=changes.getInstalledVersions();
            revert.setCurrentVersion(version,installedVersions);
            select.setCurrentVersion(version);
            
            JOptionPane.showMessageDialog(this,"Reversion Process Completed","Message",JOptionPane.INFORMATION_MESSAGE);
            
        }
        
        enableButtons(true);
    }

    private void enableButtons(boolean flag)
    {
        previous.setEnabled(flag);
        close.setEnabled(flag);
        help.setEnabled(flag);
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

    public void setProperties(java.util.Properties props)
    {
				//<Begin_setProperties_java.util.Properties> 

  
        //<End_setProperties_java.util.Properties>
    }
}






