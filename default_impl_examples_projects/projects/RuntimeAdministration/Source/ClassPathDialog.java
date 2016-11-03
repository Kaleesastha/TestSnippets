

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;




//$Id: ClassPathDialog.java,v 1.1 2006/08/29 13:57:02 build Exp $


import com.adventnet.nms.util.CommonBuilderUIInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class ClassPathDialog extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JList list = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.DefaultListModel listModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	CommonBuilderUIInterface uiUtils = null;
  
  
  
















   


  public ClassPathDialog()
  {
	uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
    //<Begin_ClassPathDialog>
    pack();
  
    //<End_ClassPathDialog>
	
  }

  public ClassPathDialog(java.applet.Applet applet)
  {
      uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
    //<Begin_ClassPathDialog_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ClassPathDialog_java.applet.Applet>

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
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JLabel1.setText(resourceBundle.getString("ClassPath Settings"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            list.setModel(listModel);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+list,ex); 
          }

//<UserCode_Begin_Bean_list>

//<UserCode_End_Bean_list>

          try
          {
            JPanel5.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>
//JPanel5.setVisible(false);
//<UserCode_End_Bean_JPanel5>

          try
          {
            JButton3.setText(resourceBundle.getString("Remove"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>

//<UserCode_End_Bean_JButton3>

          try
          {
            JButton2.setText(resourceBundle.getString("Browse"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            JPanel6.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            JTextArea1.setOpaque(false);
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setLineWrap(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
          }

//<UserCode_Begin_Bean_JTextArea1>

//<UserCode_End_Bean_JTextArea1>

          try
          {
            JPanel3.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JButton1.setText(resourceBundle.getString("Close"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+0,JButton1.getPreferredSize().height+4));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+0,JButton2.getPreferredSize().height+4));
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+0,JButton3.getPreferredSize().height+4));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+4));

  
          //<End_setUpProperties>
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
        this.setSize(getPreferredSize().width+397,getPreferredSize().height+349); 
          setTitle(resourceBundle.getString("ClassPathDialog"));
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
	uiUtils.centerWindow(this);
	JPanel5.setVisible(false);
	JLabel2.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));
	JTextArea1.setText(resourceBundle.getString("The classpath settings are not configurable as of now.")+
					resourceBundle.getString("The present classpath settings are listed below.")+ resourceBundle.getString("Please make sure,you place your class file in any of the available classpaths."));
	JTextArea1.setEditable(false);
JButton1.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
        setTitle(resourceBundle.getString("Classpath Settings")); 

  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        list= new javax.swing.JList();
        JPanel5= new javax.swing.JPanel();
        JButton3= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JPanel6= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JTextArea1= new javax.swing.JTextArea();
        JPanel3= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        listModel= new javax.swing.DefaultListModel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JLabel1);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel4.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(list);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel5,cons);
JPanel5.setLayout(new FlowLayout(2,5,5));
JPanel5.add(JButton3);
JPanel5.add(JButton2);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel6.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JTextArea1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(JButton1);

  
//<End_setUpGUI_Container>
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
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
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

	


 
//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  ClassPathDialog.this.setVisible(false);
  ClassPathDialog.this.dispose();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>
	
	public void populateUI(String classpath,String separator)
	{
		StringTokenizer st = new StringTokenizer(classpath,separator);
		int count = st.countTokens();
		while(st.hasMoreElements())
		{
			String temp = st.nextElement().toString();
			listModel.addElement(temp);
		}
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}









