

/**
 * $Id:
 */

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory


//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.tools.nar;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import com.adventnet.nms.tools.utils.ImgConv;



public class InstalledList extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "DeploymentWizard";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JTextArea JTextArea2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTextArea listArea = null;
	javax.swing.JLabel JLabel1 = null;
	//<End_Variable_Declarations>


  
  
  


   


  

  

 
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
        JPanel1= new javax.swing.JPanel();
        JTextArea2= new javax.swing.JTextArea();
        JScrollPane1= new javax.swing.JScrollPane();
        listArea= new javax.swing.JTextArea();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JTextArea2,BorderLayout.NORTH);
JPanel1.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(listArea);
Top.add(JLabel1,BorderLayout.WEST);

  
//<End_setUpGUI_Container>
  } 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Install Information"),2,0,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_components>

//<UserCode_End_Bean_components>

          try
          {
            JPanel1.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JTextArea2.setBackground(new Color(-3355444));
            JTextArea2.setText(resourceBundle.getString("The list shows all the selected options.Please click previous button to modify or finish to install."));
            JTextArea2.setFont(new Font("Dialog",1,13));
            JTextArea2.setWrapStyleWord(true);
            JTextArea2.setLineWrap(true);
            JTextArea2.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea2,ex); 
          }

//<UserCode_Begin_Bean_JTextArea2>

//<UserCode_End_Bean_JTextArea2>

          try
          {
            JScrollPane1.setBackground(new Color(-3355444));
            JScrollPane1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("List"),0,0,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane1>

//<UserCode_End_Bean_JScrollPane1>

          try
          {
            listArea.setBackground(new Color(-3355444));
            listArea.setWrapStyleWord(true);
            listArea.setLineWrap(true);
            listArea.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+listArea,ex); 
          }

//<UserCode_Begin_Bean_listArea>

//<UserCode_End_Bean_listArea>
//		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+185,JLabel1.getPreferredSize().height+415));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+15,JScrollPane1.getPreferredSize().height+0));
		JTextArea2.setPreferredSize(new Dimension(JTextArea2.getPreferredSize().width+479,JTextArea2.getPreferredSize().height+64));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+10));

  
          //<End_setUpProperties>
	
	
JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+168,JLabel1.getPreferredSize().height+388));
		
	
         URL url=getClass().getResource("images"+File.separator+"userlistedprops.jpg");
         JLabel1.setIcon(new ImageIcon(ImgConv.getImage(url.toString())));
		JTextArea2.setText("\n"+resourceBundle.getString("The list shows all the selected options.Please click previous button to modify or finish to install."));
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
  public void init()
  { 

  //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+682,getPreferredSize().height+453)); 
        setSize(getPreferredSize()); 
        Container container = this;
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




   


  public InstalledList()
  {
    //<Begin_InstalledList>
    this.init();
  
    //<End_InstalledList>
  }

  public InstalledList(java.applet.Applet applet)
  {
    //<Begin_InstalledList_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_InstalledList_java.applet.Applet>
  }

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}







