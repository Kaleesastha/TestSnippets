

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;




public class Lable extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	com.adventnet.beans.images.ImageLabel ImageLabel1 = null;
	//<End_Variable_Declarations>



  

  

 
    public void init()
  {
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+440,getPreferredSize().height+381)); 
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
                 value = (String)com.adventnet.apiutils.Utility.getParameter(input);
           }    
           if(value == null)
           {
            if (input.equals("RESOURCE_PROPERTIES")) value = "SecurityAdministrationResources"; 
            if (input.equals("MS_MODE")) value = "3"; 
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

  //<UserCode_Begin_Bean_ImageLabel1>
  ImageIcon img = AuthMain.getBuilderUiIfInstance().getImage("security_bg.png");
  ImageLabel1.setImageIcon(img);

  //<UserCode_End_Bean_ImageLabel1>


  //<UserCode_Begin_Bean_JLabel1>

  //<UserCode_End_Bean_JLabel1>

  
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
        ImageLabel1= new com.adventnet.beans.images.ImageLabel(applet);
  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(ImageLabel1,BorderLayout.CENTER);
ImageLabel1.setLayout(new FlowLayout(1,5,5));
  
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






  public Lable()
  {
    //<Begin_Lable>
    this.init();
  
    //<End_Lable>
  }

  public Lable(java.applet.Applet applet)
  {
    //<Begin_Lable_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_Lable_java.applet.Applet>
  }

 
  


	//Utility Methods.
	

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}

