
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

 
//<Begin_Version>
//version$4
//<End_Version>

package com.adventnet.studio.framework.installer;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.apiutils.WindowUtil;



public class NmsPwsNarUninstaller extends JDialog implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	//<End_Variable_Declarations>
String applicationName;
String uninstallLocation;
PWSUninstallerApp uninstaller;

public NmsPwsNarUninstaller(String arg1,String arg2,JFrame parent)
{
    super(parent,true);
     applicationName = arg1;
     uninstallLocation = arg2;
     pack();
     this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);     

}

  public NmsPwsNarUninstaller()
  {
    //<Begin_NmsPwsNarUninstaller>
    pack();
  
    //<End_NmsPwsNarUninstaller>
  }

  public NmsPwsNarUninstaller(java.applet.Applet applet)
  {
    //<Begin_NmsPwsNarUninstaller_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_NmsPwsNarUninstaller_java.applet.Applet>
  }

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+729,getPreferredSize().height+534); 
          setTitle("NmsPwsNarUninstaller");
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
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
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
            }
        return value;

  
           //<End_getParameter_String>
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
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
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
	uninstaller = new PWSUninstallerApp();
	uninstaller.init();	
	uninstaller.setApplicationName(applicationName);
	uninstaller.setUninstallLocation(uninstallLocation);
	uninstaller.setStandAlone(false);
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));

  
//<End_setUpGUI_Container>

	Top.add(uninstaller.getTop(),BorderLayout.CENTER);
	WindowUtil.positionWindow(this, WindowUtil.CENTER);
  }
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
	uninstaller.addActionListener(this);
	this.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent we){
		    if(uninstaller.isProcessing()){
			JOptionPane.showMessageDialog((JDialog)we.getSource(),"Uninstallation in progress.Please wait.","Warning",JOptionPane.WARNING_MESSAGE);
			return;
		    }
		    setVisible(false);
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
  
    //Action for close button
  public void actionPerformed(ActionEvent evt)
  {
       setVisible(false);
  }
  
  public static void main(String args[])
{
     NmsPwsNarUninstaller uninstaller = new NmsPwsNarUninstaller(args[0],args[1],null);
     uninstaller.setVisible(true);
}
public boolean getResult(){
    return uninstaller.getResult();
}
public String[] getUnInstalledNarsList(){
    return uninstaller.getUnInstalledNarsList();
}

}








