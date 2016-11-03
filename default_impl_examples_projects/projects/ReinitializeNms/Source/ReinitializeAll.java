// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.launcher.nms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintStream;
import jdbc.DropSchema;
import java.io.*;

public class ReinitializeAll extends JFrame implements ActionListener
{
    //<Begin_Variable_Declarations>
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    private String localePropertiesFileName = "ReinitializeNmsResources";
    static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
    private boolean running=false;
    javax.swing.JPanel Top = null;
    javax.swing.JPanel labelPanel = null;
    javax.swing.JLabel lfReinit = null;
    javax.swing.JPanel butPanel = null;
    javax.swing.JButton ok = null;
    javax.swing.JButton cancel = null;
    GridBagConstraints cons = new GridBagConstraints();
    Insets inset;
    //<End_Variable_Declarations>
    String rootDir="";
    String stateDir="";
    public boolean windowListener_enabled  = true;
    private boolean reinit_status = false;
    String text1 = "";
    
    public ReinitializeAll(String args[])
    {
        
        for(int i = 0; i < args.length; i++)
        {
            if (args[i].equals("-ROOT_DIR"))
            {
                rootDir=args[i+1];
                System.setProperty("webnms.rootdir",rootDir);
                i++;
            }
            else if (args[i].equals("-STATE_DIR"))
            {
                stateDir=args[i+1];
                i++;
            }
        }
        
    }
    
    public ReinitializeAll()
    {
				//<Begin_ReinitializeAll>
        pack();
  
        //<End_ReinitializeAll>
    }
    
    public ReinitializeAll(java.applet.Applet applet)
    {
				//<Begin_ReinitializeAll_java.applet.Applet>
        this.applet = applet;
        pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
        //<End_ReinitializeAll_java.applet.Applet>
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
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
            localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+379,getPreferredSize().height+221); 
        setTitle("Reinitialize Web NMS Database");
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


  
        //<End_init>
        setTitle(resourceBundle.getString("Reinitialize Web NMS Database"));	
        this.setSize(300,250);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        this.setLocation((d.width-300)/2,(d.height-250)/2);
	Image image = kit.getImage(System.getProperty("user.dir")+File.separator+"images"+File.separator+"adventneticon.jpg");
        this.setIconImage(image);
        this.addWindowListener( new WindowAdapter()
            {
                public void windowClosing( WindowEvent we)
                {
                    System.exit(0);
                }
            });
	
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
            if (input.equals("MS_MODE")) value = "3"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "ReinitializeNmsResources"; 
        }
        return value;

  
        //<End_getParameter_String>
    } 
    public void setUpProperties()
    {
				//<Begin_setUpProperties> 

        try
        {
            labelPanel.setBorder(new javax.swing.border.EtchedBorder(1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelPanel,ex); 
        }

        try
        {
            lfReinit.setFont(new Font("SansSerif",0,12));
            lfReinit.setForeground(new Color(-16777216));
            lfReinit.setText(resourceBundle.getString("<html><h3>Reinitializing AdventNet Web NMS drops all the data and configuration information stored in the database. <br><br> Do you want to reinitialize Web NMS ?</h3></html>"));
            lfReinit.setHorizontalTextPosition(0);
            lfReinit.setHorizontalAlignment(0);
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfReinit,ex); 
        }

        try
        {
            butPanel.setBorder(new javax.swing.border.EtchedBorder(1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+butPanel,ex); 
        }

        try
        {
            ok.setFont(new Font("SansSerif",0,12));
            ok.setHorizontalTextPosition(0);
            ok.setBorder(new javax.swing.border.BevelBorder(0));
            ok.setActionCommand(resourceBundle.getString("Yes"));
            ok.setText(resourceBundle.getString("Yes"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ok,ex); 
        }

        try
        {
            cancel.setFont(new Font("SansSerif",0,12));
            cancel.setHorizontalTextPosition(0);
            cancel.setBorder(new javax.swing.border.BevelBorder(0));
            cancel.setActionCommand(resourceBundle.getString("No"));
            cancel.setText(resourceBundle.getString("No"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancel,ex); 
        }
        cancel.setPreferredSize(new Dimension(cancel.getPreferredSize().width+64,cancel.getPreferredSize().height+6));
        ok.setPreferredSize(new Dimension(ok.getPreferredSize().width+60,ok.getPreferredSize().height+6));

  
        //<End_setUpProperties>
        cancel.addActionListener(this);
        ok.addActionListener(this);
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
        labelPanel= new javax.swing.JPanel();
        lfReinit= new javax.swing.JLabel();
        butPanel= new javax.swing.JPanel();
        ok= new javax.swing.JButton();
        cancel= new javax.swing.JButton();

  
        //<End_initVariables>
    } 
    public void setUpGUI(Container container)
    {
				//<Begin_setUpGUI_Container> 
        container.add(Top,BorderLayout.CENTER);
        Top.setLayout(new BorderLayout(5,5));
        Top.add(labelPanel,BorderLayout.CENTER);
        labelPanel.setLayout(new GridBagLayout());
        inset = new Insets(5,5,5,5);
        setConstraints(0,0,0,0,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
        labelPanel.add(lfReinit,cons);
        Top.add(butPanel,BorderLayout.SOUTH);
        butPanel.setLayout(new FlowLayout(1,5,5));
        butPanel.add(ok);
        butPanel.add(cancel);

  
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
    
    
    
    
    
    private void deleteFile(File file, String s)
    {
        if(file.isFile())
        {
            if(!file.getName().endsWith(".bat") && !file.getName().endsWith(".sh"))
            {
                System.out.println("Deleting the file " + file.getName());
                file.delete();
            }
        }
        else
        {
            String as[] = file.list();
            Object obj = null;
            if(as != null)
            {
                for(int i = 0; i < as.length; i++)
                {
                    String s1 = s + File.separator + file.getName();
                    File file1 = new File(s1, as[i]);
                    deleteFile(file1, s1);
                }
                
            }
        }
    }
    
    
    private void DropDBTable(String s)
    {
        try
        {
            DropSchema dropschema = new DropSchema();
            String argsForDropSchema[]=new String[]{"MODE",s};
            dropschema.doreinitialize(argsForDropSchema);
            JOptionPane.showMessageDialog(this,resourceBundle.getString("Successfully reinitialized the Database"),resourceBundle.getString("Reinitialize Web NMS Database"),JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception exception)
        {
            System.out.println("Exception" + exception);
            exception.printStackTrace();
            String exstr = exception.toString();
            int mlen = 40;
            int exstr_l = (exstr.length()/mlen) + 1;
            String[] messg = new String[2+ exstr_l];
            
            messg[0] =resourceBundle.getString("Problem in reinitializing  database");
            messg[1] =resourceBundle.getString("Please refer logs directory for more details");
            for(int i = 0; i < exstr_l; i++)
            {
                int endl = (i+1)*mlen;
                if(endl > exstr.length())
                {	
                    endl = exstr.length();
                }
                messg[i+2] = exstr.substring(i*mlen,endl);
            }
            JOptionPane.showMessageDialog(this,messg,resourceBundle.getString("Reinitialize Web NMS Database"),JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(ok))
        {		
            ReinitializeAllThread rt = new ReinitializeAllThread(this);
            rt.start();
        }
        else if(e.getSource().equals(cancel) )
        {
            this.dispose();
            System.exit(0);
        }
    }
    // HardCoded some directories.
    private void deleteDatabaseStateFiles(String stateDir)
    {
        File stateFile=new File(stateDir);
        String[] files=null;
        if ( stateFile.isDirectory())
        {
            files=stateFile.list();
        }
        
        if ( files != null)
        {
            for ( int i=0;i<files.length;++i)
            {
                File fileToDelete=new File(stateFile,files[i]);
                if (  fileToDelete.isDirectory())
                {
                    continue;
                }
                
                if ( files[i].endsWith(".sh") || files[i].endsWith(".bat") )
                {
                    continue;
                }
                
                if ( fileToDelete.delete())
                {
                    System.out.println("Deleting the file " + fileToDelete.getName());
                }
                
            }
            
            
        }
    }
    private void deleteCommonStateFiles()
    {
				// TODO: Need to use the same method for deletion
				// Hardcoded
				// Deleting the policy in state directory
				//deleteStateFiles(new File(stateDir,"policy"));
        
				// Deleting the reports/daily directory
        deleteStateFiles(new File(rootDir+File.separator+"reports","daily"));
				// Deleting the reports/weekly directory
        deleteStateFiles(new File(rootDir+File.separator+"reports","weekly"));
        
				// Deleting the polldata directory
				// Removed these entries since serialize mode dependency is removed.
				//deleteStateFiles(new File(rootDir,"polldata"));
				//Deleting the mapHierarchy.conf
				//File mapFile= new File(rootDir+File.separator+"conf","mapHierarchy.conf");
				//if ( mapFile.delete() )
				//{
				//	System.out.println("Deleting the file " + mapFile.getName());
				//}

		//SPP_CHANGES_BEGIN
        deleteStateFiles(new File(rootDir+File.separator+"state" + File.separator,"communication"));
		copyFile(new File(rootDir + File.separator + "conf","SPP_DashboardScreens.xml.backup"), new File(rootDir+File.separator + "conf" + File.separator + "SPP_DashboardScreens.xml"));
		copyFile(new File(rootDir + File.separator + "conf","ResourceTypeVsApplnDataClass.xml.backup"), new File(rootDir+File.separator + "conf" + File.separator + "ResourceTypeVsApplnDataClass.xml"));
		copyFile(new File(rootDir + File.separator + "conf","NOCServerDetails.xml.backup"), new File(rootDir+File.separator + "conf" + File.separator + "NOCServerDetails.xml"));
		//SPP_CHANGES_END
    }

	public static void copyFile(File source, File dest)
	{
		if (source==null||dest==null)
		{
			return;
		}

		if(!source.exists())
		{
			return;
		}

		try
		{
			BufferedReader bufferedreader = new BufferedReader(new FileReader(source));
			PrintWriter printwriter = new PrintWriter(new BufferedWriter(new FileWriter(dest)));

			String s2;
			while((s2 = bufferedreader.readLine()) != null)
			{
				printwriter.println(s2);
			}
			bufferedreader.close();
			printwriter.flush();
			printwriter.close();
		}
		catch(Exception ex)
		{
			System.err.println("Exception while coping file "+source.getAbsolutePath()+" as "+dest.getAbsolutePath());
			ex.printStackTrace();
		}
	}
	
    private void deleteStateFiles(File f)
    {
        String[] files=null;
        
        if (! f.exists())
        {
            //File or directory not exists....
            return;
        }
        
        if ( f.isDirectory());
        {
            files=f.list();
        }
        for( int i=0;i<files.length;++i)
        {
            File file=new File(f,files[i]);
            if ( file.isDirectory() )
            {
                deleteStateFiles(file);
                continue;
            }
            if (  file.delete() )
            {
                System.out.println("Deleting the file " + file.getName());
            }
        }
        
    }
    private void deleteConfigFiles(String dir)
    {
        File configDir;
        if ( rootDir.length() != 0)
            configDir=new File(rootDir+File.separator+dir);
        else
            configDir=new File("."+File.separator+dir);
        String[] configFiles=null;
        if ( configDir.isDirectory() )
            configFiles=configDir.list();
        if ( configFiles == null)
            return;
        
        for ( int i=0;i<configFiles.length;++i)
        {
            if ( configFiles[i].endsWith(".tab"))
            {
                File file =new File(configDir,configFiles[i]);
                if ( file.delete())
                    System.out.println("Deleting the file " + file.getName());
                
            }
            
        }
        
    }
    
    public void enablebuttons()
    {
        ok.setEnabled(true);
        cancel.setEnabled(true);
    }
    
    public void disablebuttons()
    {
        ok.setEnabled(false);
        cancel.setEnabled(false);
        
    }
    
    class ReinitializeAllThread extends Thread
    {
        ReinitializeAll frame = null;
        ReinitializeAllThread(ReinitializeAll frame){
            this.frame=frame;
        }
        
        public void run(){
            windowListener_enabled=false;
            frame.disablebuttons();
            try{
                //if (e.getSource().equals(ok))
                //{
                DropDBTable("ALL");
                deleteDatabaseStateFiles(stateDir);
                deleteCommonStateFiles();
                frame.setVisible(false);
                reinit_status = true;
                
                //this.dispose();
                System.exit(0);
                //}
            }
            catch(Exception exception)
            {
                System.out.println("Exception" + exception);
                exception.printStackTrace();
                String exstr = exception.toString();
                int mlen = 40;
                int exstr_l = (exstr.length()/mlen) + 1;
                String[] messg = new String[2+ exstr_l];
                
                messg[0] =resourceBundle.getString("Problem in reinitializing  database");
                messg[1] =resourceBundle.getString("Please refer logs directory for more details.");
                for(int i = 0; i < exstr_l; i++)
                {
                    int endl = (i+1)*mlen;
                    if(endl > exstr.length())
                    {	
                        endl = exstr.length();
                    }
                    messg[i+2] = exstr.substring(i*mlen,endl);
                }
                JOptionPane.showMessageDialog(frame,messg,resourceBundle.getString("Reinitialize Web NMS Database"),JOptionPane.ERROR_MESSAGE);
            }
            windowListener_enabled=true;
            frame.enablebuttons();
        }
    }
    		
    public static void main(String [] args)
    {
        String[] argName = new String[3];
        String[] argVal = new String[3];
        
        for(int i=0,j=0;i<args.length;i++)
        {
            if(args[i].trim().equals("-NMS_RESOURCE_DIRECTORY")|| args[i].trim().equals("-RESOURCE_PROPERTIES") || args[i].trim().equals("-RESOURCE_LOCALE"))
            {
                argName[j] = args[i].substring(1);
                i++;
                j++;
            }
        }
        com.adventnet.apiutils.Utility.parseAndSetParameters(argName,args);	
        String modeValue = null;
        for(int i = 0; i < args.length; i++)
        {
            if (args[i].equals("-ROOT_DIR"))
            {
                String rootValue=args[i+1];
                System.setProperty("webnms.rootdir",rootValue);
                i++;
            }
            else if (args[i].equals("MODE"))
            {
                modeValue=args[i+1];
                i++;
            }
        }
        if(modeValue == null)
        {
            try
            {
                JFrame f = new JFrame();
            }
            catch(Throwable ex)
            {
                System.out.println("Error: Could not instantiate X - Windows");
                System.out.println("To avoid this use command line arguments to run");
                System.out.println("Command line arguments: MODE All");
                System.exit(1);
            }
            
            ReinitializeAll reinitializeall = new ReinitializeAll(args);
            reinitializeall.setVisible(true);
        }
        else
        {
            try
            {
                DropSchema dropschema = new DropSchema();
                String argsForDropSchema[]=new String[]{"MODE",modeValue};
                dropschema.doreinitialize(argsForDropSchema);
                System.exit(0);
            }
            catch(Exception ex)
            {
                System.out.println(ex);
                System.exit(1);
            }
        }
        
        
        //<Begin_main_String[]>
        //<End_main_String[]>
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
}


































