/* $Id: GroupPropertiesDialog.java,v 1.1 2006/08/29 13:57:02 build Exp $ */

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

/*$Id: GroupPropertiesDialog.java,v 1.1 2006/08/29 13:57:02 build Exp $*/


//package com.adventnet.nms.studio.typecfgtr.tl1;
//<Begin_Version>
//version$2
//<End_Version>
package com.adventnet.nms.runtimeconfig;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;



public class GroupPropertiesDialog extends JDialog 
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	com.adventnet.nms.runtimeconfig.TypePropertiesPanel TypePropertiesPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JLabel GradientLabel1 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField JTextField1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

    boolean okClicked = false;
    boolean modify = false;

    public void init(java.applet.Applet app)
        {
                this.applet = app;

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
        this.setSize(getPreferredSize().width+449,getPreferredSize().height+509); 
          setTitle(resourceBundle.getString("GroupPropertiesDialog"));
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
            JPanel2.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JButton1.setFont(new Font("SansSerif",0,12));
            JButton1.setHorizontalTextPosition(4);
            JButton1.setText(resourceBundle.getString("Ok"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JButton2.setFont(new Font("SansSerif",0,12));
            JButton2.setHorizontalTextPosition(4);
            JButton2.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            JLabel3.setHorizontalAlignment(2);
            JLabel3.setFont(new Font("SansSerif",0,12));
            JLabel3.setHorizontalTextPosition(4);
            JLabel3.setText(resourceBundle.getString("Device Group Properties"));
            JLabel3.setForeground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
            JLabel1.setText(resourceBundle.getString("Device Group"));
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
		JLabel3.setPreferredSize(new Dimension(JLabel3.getPreferredSize().width+0,JLabel3.getPreferredSize().height+1));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+0,JButton2.getPreferredSize().height+1));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+0,JButton1.getPreferredSize().height+1));

  
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
        TypePropertiesPanel1= new com.adventnet.nms.runtimeconfig.TypePropertiesPanel(applet);
        JPanel2= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        GradientLabel1= new javax.swing.JLabel();
        JLabel3= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JTextField1= new javax.swing.JTextField();

  
        //<End_initVariables>
    } 
    public void setUpGUI(Container container)
  {
        //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,2,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(TypePropertiesPanel1,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,3,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(JButton1);
JPanel2.add(JButton2);
inset = new Insets(0,0,0,0);
setConstraints(0,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(GradientLabel1,cons);
GradientLabel1.setLayout(new FlowLayout(1,5,10));
GradientLabel1.add(JLabel3);
inset = new Insets(20,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(JLabel1,cons);
inset = new Insets(20,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField1,cons);

  
//<End_setUpGUI_Container>
    } 
    public void setUpConnections()
  {
        //<Begin_setUpConnections> 

      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
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




  

    private Hashtable currProps ;

    void clear()
    {
	JTextField1.setText("");
	TypePropertiesPanel1.clear();
    }

    public void showGroupDlg(Hashtable props)
    {
	clear();
	currProps = props;
	if(props.get("deviceGroupName") != null)
	{
            JTextField1.setText(props.get("deviceGroupName").toString());
	}
	TypePropertiesPanel1.showTypeProperties(props);
    }
 
    public void updateProps()
    {
        currProps.put("deviceGroupName",JTextField1.getText());
        TypePropertiesPanel1.updateProps(currProps);
    }



    public GroupPropertiesDialog()
  {
        //<Begin_GroupPropertiesDialog>
    pack();
  
    //<End_GroupPropertiesDialog>
	setModal(true);
    }

    public GroupPropertiesDialog(java.applet.Applet applet)
  {
        //<Begin_GroupPropertiesDialog_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_GroupPropertiesDialog_java.applet.Applet>
    }

    public boolean isOkClicked()
    {
	return okClicked;
    }

 


    //<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 okClicked  = false;
 setVisible(false);
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>
    //<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>
      //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         updateProps();
         okClicked = true;
         setVisible(false);
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>
	
    public void setModify(boolean mod)
    {
        modify = mod;
    }
    
    public boolean isModify()
    {
        return modify;
    }

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}





