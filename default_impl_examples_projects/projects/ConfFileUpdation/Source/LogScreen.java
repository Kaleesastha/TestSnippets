
//$Id: LogScreen.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.tools.confchanges;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.tools.utils.PrintToTextArea;



public class LogScreen extends JPanel implements ActionListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ConfFileUpdationResources";
	static BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JButton clear = null;
	//<End_Variable_Declarations>
	String nmsHome="";
	PrintToTextArea cprint=null;
	PrintToTextArea cprint1=null;
	

  public LogScreen()
  {
    //<Begin_LogScreen>
    this.init();
  
    //<End_LogScreen>
  }
  public LogScreen(String home)
  {
      	nmsHome=home;
	this.init();
   
  }
  public LogScreen(java.applet.Applet applet)
  {
    //<Begin_LogScreen_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_LogScreen_java.applet.Applet>
  }

    public void setLogInfo()
    {
		
        try
        {
            File outLog = new File( UpdateConfChanges.getStdOutLogFilePath() );
            File errLog = new File( UpdateConfChanges.getStdErrLogFilePath() );
            cprint = new PrintToTextArea(JTextArea1,outLog);
            cprint1 = new PrintToTextArea(JTextArea1,errLog);
        }
        catch(Exception e)
        {
            System.err.println(	e.getMessage());
        }

        cprint.showTimeStamp(true);
        cprint1.showTimeStamp(true);
        
        UpdateConfChanges.setLogInfo(cprint,cprint1);
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
        setPreferredSize(new Dimension(getPreferredSize().width+458,getPreferredSize().height+363)); 
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
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Log Message"),0,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_components>

//<UserCode_End_Bean_components>

          try
          {
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setLineWrap(true);
            JTextArea1.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
          }

//<UserCode_Begin_Bean_JTextArea1>

//<UserCode_End_Bean_JTextArea1>

          try
          {
            clear.setFont(new Font("SansSerif",0,12));
            clear.setHorizontalTextPosition(4);
            clear.setText(resourceBundle.getString("Clear"));
            clear.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+clear,ex); 
          }

//<UserCode_Begin_Bean_clear>

//<UserCode_End_Bean_clear>
		clear.setPreferredSize(new Dimension(clear.getPreferredSize().width+30,clear.getPreferredSize().height+6));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+6,JScrollPane1.getPreferredSize().height+25));

  
          //<End_setUpProperties>
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
        JScrollPane1= new javax.swing.JScrollPane();
        JTextArea1= new javax.swing.JTextArea();
        JPanel1= new javax.swing.JPanel();
        clear= new javax.swing.JButton();

  
        //<End_initVariables>
		clear.addActionListener(this);
  } 
  public void actionPerformed(ActionEvent e)
  {
  	if(e.getSource().equals(clear))
	{
		JTextArea1.setText("");
	}
  }
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTextArea1);
Top.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new FlowLayout(2,10,10));
JPanel1.add(clear);

  
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


 
  

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}





