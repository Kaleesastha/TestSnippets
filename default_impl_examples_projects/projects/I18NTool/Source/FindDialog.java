// $Id: FindDialog.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.management.i18n.tools;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class FindDialog extends JDialog 
{

    java.util.ResourceBundle resourceBundle = null;
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "I18NToolResources";
    //static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JCheckBox caseCheck = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField searchText = null;
	//<End_Variable_Declarations>


  public FindDialog()
  {
    //<Begin_FindDialog>
    pack();
  
    //<End_FindDialog>
  }

  public FindDialog(java.util.ResourceBundle bundle)
  {
      resourceBundle = bundle;
    //<Begin_FindDialog>
    pack();
  
    //<End_FindDialog>
  }

  public FindDialog(java.applet.Applet applet)
  {
    //<Begin_FindDialog_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_FindDialog_java.applet.Applet>
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
		searchText.requestFocus();
  }

  
     
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 
setResizable(false);
          try
          {
            caseCheck.setText(resourceBundle.getString("Match case "));
            caseCheck.setFocusPainted(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+caseCheck,ex); 
          }
caseCheck.setVisible(false);
          try
          {
            JButton1.setText(resourceBundle.getString("Find next"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

          try
          {
            JButton2.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

          try
          {
            JLabel1.setText(resourceBundle.getString("Find what"));
            JLabel1.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

  
//<End_setUpProperties>
  } 
  public void init()
  { 

  //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	//resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+360,getPreferredSize().height+114); 
          setTitle(resourceBundle.getString("Find"));
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
    
	I18NUtils.WindowPosition(this,4);	
  addWindowListener( new WindowAdapter()
					{
						public void windowClosing(java.awt.event.WindowEvent we)
							{
								close();
							}
					}
			       );	

 
   searchText.addKeyListener( new KeyAdapter(){
						public void keyTyped(java.awt.event.KeyEvent arg0)
							{
								if(arg0.getKeyChar() == (arg0.VK_ENTER))
									{
									search();	
									}
								if(arg0.getKeyChar() == (arg0.VK_ESCAPE))
									{
									close();	
									}
							}
						});		

 addKeyListener( new KeyAdapter(){
						public void keyTyped(java.awt.event.KeyEvent arg0)
							{
								if(arg0.getKeyChar() == (arg0.VK_ESCAPE))
									{
									close();	
									}
							}
						});	
	
	  searchText.grabFocus();
	
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
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        caseCheck= new javax.swing.JCheckBox();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JLabel1= new javax.swing.JLabel();
        searchText= new javax.swing.JTextField();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(null);
caseCheck.setBounds(5,65,240,30);
Top.add(caseCheck);
JButton1.setBounds(255,15,95,30);
Top.add(JButton1);
JButton2.setBounds(255,65,95,30);
Top.add(JButton2);
JLabel1.setBounds(5,15,85,30);
Top.add(JLabel1);
searchText.setBounds(95,15,155,30);
Top.add(searchText);

  
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
            if (input.equals("MS_MODE")) value = "3"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "I18NToolResources"; 
            if (input.equals("PORT")) value = "9090"; 
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

 public void close()
	{
		removeAll();
		dispose();
	}	
 

I18NInterface intInter = null;

	
 public void search()
	{
		  if(searchText.getText().equals(""))
			   {
		    return; 
			   } 
		 intInter.setSearchText(searchText.getText()); 
		 if(caseCheck.isSelected()) 
		 intInter.find(caseCheck.isSelected());
		 else
		 intInter.find(caseCheck.isSelected()); 

	}	public void setI18NInterface(I18NInterface intInter)
	{
		this.intInter = intInter;
	}	

	//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 close();
     }
}
//<End__class_JButton2_JButton2_conn1>





//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 search(); 
     }
}
//<End__class_JButton1_JButton1_conn1>



}





