/* $Id: TypePropertiesPanel.java,v 1.1 2006/08/29 13:57:02 build Exp $ */

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//package com.adventnet.nms.studio.typecfgtr.tl1;

//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.runtimeconfig;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;



public class TypePropertiesPanel extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField JTextField1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField JTextField2 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField JTextField3 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JTextField JTextField4 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JTextField JTextField5 = null;
	javax.swing.JTextField JTextField6 = null;
	javax.swing.JLabel JLabel8 = null;
	javax.swing.JTextField JTextField7 = null;
	javax.swing.JTextField JTextField8 = null;
	javax.swing.JLabel JLabel9 = null;
	javax.swing.JTextField JTextField9 = null;
	javax.swing.JTextField JTextField10 = null;
	javax.swing.JLabel JLabel10 = null;
	javax.swing.JTextField JTextField11 = null;
	javax.swing.JTextField JTextField12 = null;
	javax.swing.JTextField JTextField13 = null;
	javax.swing.JTextField JTextField14 = null;
	javax.swing.JLabel JLabel11 = null;
	javax.swing.JLabel JLabel12 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
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
        setPreferredSize(new Dimension(getPreferredSize().width+505,getPreferredSize().height+442)); 
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
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
            if (input.equals("PORT")) value = "161"; 
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
            JLabel1.setText(resourceBundle.getString("Type"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JTextField1.setHorizontalAlignment(2);
            JTextField1.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField1,ex); 
          }

//<UserCode_Begin_Bean_JTextField1>
          
//<UserCode_End_Bean_JTextField1>

          try
          {
            JLabel2.setHorizontalAlignment(2);
            JLabel2.setFont(new Font("SansSerif",0,12));
            JLabel2.setForeground(new Color(-16777216));
            JLabel2.setHorizontalTextPosition(4);
            JLabel2.setText(resourceBundle.getString("Port"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JTextField2.setHorizontalAlignment(2);
            JTextField2.setFont(new Font("SansSerif",0,12));
            JTextField2.setMaximumSize(new Dimension(2147483647,2147483647));
            JTextField2.setMinimumSize(new Dimension(4,21));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField2,ex); 
          }

//<UserCode_Begin_Bean_JTextField2>

//<UserCode_End_Bean_JTextField2>

          try
          {
            JLabel3.setHorizontalAlignment(2);
            JLabel3.setFont(new Font("SansSerif",0,12));
            JLabel3.setForeground(new Color(-16777216));
            JLabel3.setHorizontalTextPosition(4);
            JLabel3.setText(resourceBundle.getString("Status Poller"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JTextField3.setHorizontalAlignment(2);
            JTextField3.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField3,ex); 
          }

//<UserCode_Begin_Bean_JTextField3>

//<UserCode_End_Bean_JTextField3>

          try
          {
            JLabel4.setHorizontalAlignment(2);
            JLabel4.setFont(new Font("SansSerif",0,12));
            JLabel4.setForeground(new Color(-16777216));
            JLabel4.setHorizontalTextPosition(4);
            JLabel4.setText(resourceBundle.getString("Connection Handler"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JTextField4.setHorizontalAlignment(2);
            JTextField4.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField4,ex); 
          }

//<UserCode_Begin_Bean_JTextField4>

//<UserCode_End_Bean_JTextField4>

          try
          {
            JLabel5.setHorizontalAlignment(2);
            JLabel5.setFont(new Font("SansSerif",0,12));
            JLabel5.setForeground(new Color(-16777216));
            JLabel5.setHorizontalTextPosition(4);
            JLabel5.setText(resourceBundle.getString("Command"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JLabel6.setHorizontalAlignment(2);
            JLabel6.setFont(new Font("SansSerif",0,12));
            JLabel6.setForeground(new Color(-16777216));
            JLabel6.setHorizontalTextPosition(4);
            JLabel6.setText(resourceBundle.getString("Response"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

          try
          {
            JLabel7.setHorizontalAlignment(2);
            JLabel7.setFont(new Font("SansSerif",0,12));
            JLabel7.setForeground(new Color(-16777216));
            JLabel7.setHorizontalTextPosition(4);
            JLabel7.setText(resourceBundle.getString("Login"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel7,ex); 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>

          try
          {
            JTextField5.setHorizontalAlignment(2);
            JTextField5.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField5,ex); 
          }

//<UserCode_Begin_Bean_JTextField5>

//<UserCode_End_Bean_JTextField5>

          try
          {
            JTextField6.setHorizontalAlignment(2);
            JTextField6.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField6,ex); 
          }

//<UserCode_Begin_Bean_JTextField6>

//<UserCode_End_Bean_JTextField6>

          try
          {
            JLabel8.setHorizontalAlignment(2);
            JLabel8.setFont(new Font("SansSerif",0,12));
            JLabel8.setForeground(new Color(-16777216));
            JLabel8.setHorizontalTextPosition(4);
            JLabel8.setText(resourceBundle.getString("Init"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel8,ex); 
          }

//<UserCode_Begin_Bean_JLabel8>

//<UserCode_End_Bean_JLabel8>

          try
          {
            JTextField7.setHorizontalAlignment(2);
            JTextField7.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField7,ex); 
          }

//<UserCode_Begin_Bean_JTextField7>

//<UserCode_End_Bean_JTextField7>

          try
          {
            JTextField8.setHorizontalAlignment(2);
            JTextField8.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField8,ex); 
          }

//<UserCode_Begin_Bean_JTextField8>

//<UserCode_End_Bean_JTextField8>

          try
          {
            JLabel9.setHorizontalAlignment(2);
            JLabel9.setFont(new Font("SansSerif",0,12));
            JLabel9.setForeground(new Color(-16777216));
            JLabel9.setHorizontalTextPosition(4);
            JLabel9.setText(resourceBundle.getString("Info"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel9,ex); 
          }

//<UserCode_Begin_Bean_JLabel9>

//<UserCode_End_Bean_JLabel9>

          try
          {
            JTextField9.setHorizontalAlignment(2);
            JTextField9.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField9,ex); 
          }

//<UserCode_Begin_Bean_JTextField9>

//<UserCode_End_Bean_JTextField9>

          try
          {
            JTextField10.setHorizontalAlignment(2);
            JTextField10.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField10,ex); 
          }

//<UserCode_Begin_Bean_JTextField10>

//<UserCode_End_Bean_JTextField10>

          try
          {
            JLabel10.setHorizontalAlignment(2);
            JLabel10.setFont(new Font("SansSerif",0,12));
            JLabel10.setForeground(new Color(-16777216));
            JLabel10.setHorizontalTextPosition(4);
            JLabel10.setText(resourceBundle.getString("Status"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel10,ex); 
          }

//<UserCode_Begin_Bean_JLabel10>

//<UserCode_End_Bean_JLabel10>

          try
          {
            JTextField11.setHorizontalAlignment(2);
            JTextField11.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField11,ex); 
          }

//<UserCode_Begin_Bean_JTextField11>

//<UserCode_End_Bean_JTextField11>

          try
          {
            JTextField12.setHorizontalAlignment(2);
            JTextField12.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField12,ex); 
          }

//<UserCode_Begin_Bean_JTextField12>

//<UserCode_End_Bean_JTextField12>

          try
          {
            JTextField13.setHorizontalAlignment(2);
            JTextField13.setFont(new Font("SansSerif",0,12));
            JTextField13.setMinimumSize(new Dimension(250,21));
            JTextField13.setPreferredSize(new Dimension(200,21));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField13,ex); 
          }

//<UserCode_Begin_Bean_JTextField13>

//<UserCode_End_Bean_JTextField13>

          try
          {
            JTextField14.setHorizontalAlignment(2);
            JTextField14.setFont(new Font("SansSerif",0,12));
            JTextField14.setPreferredSize(new Dimension(200,21));
            JTextField14.setMinimumSize(new Dimension(200,21));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField14,ex); 
          }

//<UserCode_Begin_Bean_JTextField14>

//<UserCode_End_Bean_JTextField14>

          try
          {
            JLabel11.setHorizontalAlignment(2);
            JLabel11.setFont(new Font("SansSerif",0,12));
            JLabel11.setForeground(new Color(-16777216));
            JLabel11.setHorizontalTextPosition(4);
            JLabel11.setText(resourceBundle.getString("AppendFront"));
            JLabel11.setPreferredSize(new Dimension(70,17));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel11,ex); 
          }

//<UserCode_Begin_Bean_JLabel11>

//<UserCode_End_Bean_JLabel11>

          try
          {
            JLabel12.setHorizontalAlignment(2);
            JLabel12.setFont(new Font("SansSerif",0,12));
            JLabel12.setForeground(new Color(-16777216));
            JLabel12.setHorizontalTextPosition(4);
            JLabel12.setText(resourceBundle.getString("AppendEnd"));
            JLabel12.setPreferredSize(new Dimension(70,17));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel12,ex); 
          }

//<UserCode_Begin_Bean_JLabel12>

//<UserCode_End_Bean_JLabel12>

  
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
        JLabel1= new javax.swing.JLabel();
        JTextField1= new javax.swing.JTextField();
        JLabel2= new javax.swing.JLabel();
        JTextField2= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        JTextField3= new javax.swing.JTextField();
        JLabel4= new javax.swing.JLabel();
        JTextField4= new javax.swing.JTextField();
        JLabel5= new javax.swing.JLabel();
        JLabel6= new javax.swing.JLabel();
        JLabel7= new javax.swing.JLabel();
        JTextField5= new javax.swing.JTextField();
        JTextField6= new javax.swing.JTextField();
        JLabel8= new javax.swing.JLabel();
        JTextField7= new javax.swing.JTextField();
        JTextField8= new javax.swing.JTextField();
        JLabel9= new javax.swing.JLabel();
        JTextField9= new javax.swing.JTextField();
        JTextField10= new javax.swing.JTextField();
        JLabel10= new javax.swing.JLabel();
        JTextField11= new javax.swing.JTextField();
        JTextField12= new javax.swing.JTextField();
        JTextField13= new javax.swing.JTextField();
        JTextField14= new javax.swing.JTextField();
        JLabel11= new javax.swing.JLabel();
        JLabel12= new javax.swing.JLabel();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,5,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JLabel1,cons);
inset = new Insets(0,5,5,0);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField1,cons);
inset = new Insets(0,5,0,0);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JLabel2,cons);
inset = new Insets(0,5,5,0);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField2,cons);
inset = new Insets(0,5,0,0);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JLabel3,cons);
inset = new Insets(0,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField3,cons);
inset = new Insets(0,5,0,0);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JLabel4,cons);
inset = new Insets(0,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField4,cons);
inset = new Insets(5,0,5,0);
setConstraints(1,6,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
Top.add(JLabel5,cons);
inset = new Insets(5,0,0,0);
setConstraints(2,6,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
Top.add(JLabel6,cons);
inset = new Insets(0,5,0,0);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JLabel7,cons);
inset = new Insets(0,5,5,0);
setConstraints(1,7,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField5,cons);
inset = new Insets(0,5,5,5);
setConstraints(2,7,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField6,cons);
inset = new Insets(0,5,0,0);
setConstraints(0,8,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JLabel8,cons);
inset = new Insets(0,5,5,0);
setConstraints(1,8,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField7,cons);
inset = new Insets(0,5,5,5);
setConstraints(2,8,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField8,cons);
inset = new Insets(0,5,0,0);
setConstraints(0,9,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JLabel9,cons);
inset = new Insets(0,5,5,0);
setConstraints(1,9,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField9,cons);
inset = new Insets(0,5,5,5);
setConstraints(2,9,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField10,cons);
inset = new Insets(0,5,0,0);
setConstraints(0,10,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JLabel10,cons);
inset = new Insets(0,5,0,0);
setConstraints(1,10,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField11,cons);
inset = new Insets(0,5,5,5);
setConstraints(2,10,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField12,cons);
inset = new Insets(0,5,5,5);
setConstraints(1,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JTextField13,cons);
inset = new Insets(0,5,5,5);
setConstraints(1,5,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JTextField14,cons);
inset = new Insets(0,5,0,0);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JLabel11,cons);
inset = new Insets(0,5,0,0);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JLabel12,cons);

  
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






  public TypePropertiesPanel()
  {
    //<Begin_TypePropertiesPanel>
    this.init();
  
    //<End_TypePropertiesPanel>
  }

  public TypePropertiesPanel(java.applet.Applet applet)
  {
    //<Begin_TypePropertiesPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_TypePropertiesPanel_java.applet.Applet>
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
	
    public void showTypeProperties(Hashtable props)
    {
        if(props.get("type") != null)
            JTextField1.setText(props.get("type").toString());
        if(props.get("tl1port")!= null)
            JTextField2.setText(props.get("tl1port").toString());
        if(props.get("uClass")!= null)
            JTextField3.setText(props.get("uClass").toString());
        if(props.get("connectionHandler")!= null)
            JTextField4.setText(props.get("connectionHandler").toString());
        String tempStr = (String)props.get("APPEND_FRONT");
        if (tempStr != null)
        {
            JTextField13.setText(tempStr);
        }
        tempStr = (String)props.get("APPEND_END");
        if (tempStr != null)
        {
            JTextField14.setText(tempStr);
        }
	if(props.get("loginCommand")!= null)
	{
            Hashtable lc = (Hashtable)props.get("loginCommand");
            if(lc.get("command") != null)
            {
                JTextField5.setText(lc.get("command").toString());
            }
            if(lc.get("response")!= null)
            {
                JTextField6.setText(lc.get("response").toString());
            }
	}
	if(props.get("initCommand")!= null)
	{
            Hashtable ic = (Hashtable)(((Vector)props.get("initCommand")).elementAt(0));
            if(ic.get("command") != null)
            {
                JTextField7.setText(ic.get("command").toString());
            }
            if(ic.get("response")!= null)
            {
                JTextField8.setText(ic.get("response").toString());
            }
	}
	if(props.get("infoCommand")!= null)
	{
            Hashtable ic = (Hashtable)(((Vector)props.get("infoCommand")).elementAt(0));
            if(ic.get("command") != null)
            {
                JTextField9.setText(ic.get("command").toString());
            }
            if(ic.get("response")!= null)
            {
                JTextField10.setText(ic.get("response").toString());
            }
	}
	if(props.get("statpollCommand")!= null)
	{
        Hashtable sc = (Hashtable)(((Vector)props.get("statpollCommand")).elementAt(0));
        if(sc.get("command") != null)
        {
            JTextField11.setText(sc.get("command").toString());
        }
        if(sc.get("response")!= null)
        {
            JTextField12.setText(sc.get("response").toString());
        }
	}
    }
    
    public void updateProps(Hashtable props)
    {
        if(!JTextField1.getText().equals(""))
        {
            props.put("type",JTextField1.getText());
        }
        props.put("tl1port",JTextField2.getText());
        if(!JTextField3.getText().equals(""))
        {
            props.put("uClass",JTextField3.getText());
        }
        if(!JTextField4.getText().equals(""))
        {
            props.put("connectionHandler",JTextField4.getText());
        }
        props.put("APPEND_FRONT", JTextField13.getText());
        props.put("APPEND_END", JTextField14.getText());
        if(props.get("loginCommand") == null)
        {
            props.put("loginCommand",new Hashtable());
        }
        Hashtable lc = (Hashtable)props.get("loginCommand");
        lc.put("command",JTextField5.getText());
        lc.put("response",JTextField6.getText());
        if(props.get("initCommand") == null)
        {
            Vector v = new Vector();
        v.addElement(new Hashtable());
        props.put("initCommand",v);
	}
    Hashtable ic = (Hashtable)((Vector)props.get("initCommand")).elementAt(0);
    ic.put("command",JTextField7.getText());
    ic.put("response",JTextField8.getText());
	if(props.get("infoCommand") == null)
	{
            Vector v = new Vector();
            v.addElement(new Hashtable());
            props.put("infoCommand",v);
	}
    Hashtable inc = (Hashtable)((Vector)props.get("infoCommand")).elementAt(0);
    inc.put("command",JTextField9.getText());
    inc.put("response",JTextField10.getText());
	if(props.get("statpollCommand") == null)
	{
        Vector v = new Vector();
        v.addElement(new Hashtable());
        props.put("statpollCommand",v);
	}
    Hashtable sc = (Hashtable)((Vector)props.get("statpollCommand")).elementAt(0);
    sc.put("command",JTextField11.getText());
    sc.put("response",JTextField12.getText());
    
    }
    
  void clear()
  {
	JTextField1.setText("");
	JTextField2.setText("");
	JTextField3.setText("");
	JTextField4.setText("");
	JTextField5.setText("");
	JTextField6.setText("");
	JTextField7.setText("");
	JTextField8.setText("");
	JTextField9.setText("");
	JTextField10.setText("");
	JTextField11.setText("");
	JTextField12.setText("");
  }

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}





