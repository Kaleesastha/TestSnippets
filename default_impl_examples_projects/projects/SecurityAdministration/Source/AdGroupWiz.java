/* $Id: AdGroupWiz.java,v 1.2 2010/10/29 13:46:41 swaminathap Exp $ */


// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;




public class AdGroupWiz extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	com.adventnet.beans.panels.CardPanel CardPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	//<End_Variable_Declarations>

	com.adventnet.security.ui.AbstractSecurityModel model =null;
	private String name = "";

  

  


  


 

  

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
  public void init()
  {
         //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+576,getPreferredSize().height+427); 
          setTitle(resourceBundle.getString("AdGroupWiz"));
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
  	setTitle(resourceBundle.getString("Groups Wizard"));
	CardPanel1.showCard("groups");
	JButton3.setEnabled(false);
	((com.adventnet.security.ui.ViewOper)CardPanel1.getCard("oper")).setTreeModel(AuthMain.model.getTreeModel());
	AuthMain.getBuilderUiIfInstance().centerWindow(this);
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "SecurityAdministrationResources"; 
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

//<UserCode_Begin_Bean_components>
//this.setResizable(false);
//<UserCode_End_Bean_components>

          try
          {
            java.lang.String[]  CardPanel1cardAndClassNames_array = new java.lang.String[ 2 ]; 
            CardPanel1cardAndClassNames_array[ 0 ] = resourceBundle.getString("groups=com.adventnet.security.ui.AddGroup");
            CardPanel1cardAndClassNames_array[ 1 ] = resourceBundle.getString("oper=com.adventnet.security.ui.ViewOper");
            CardPanel1.setCardAndClassNames(CardPanel1cardAndClassNames_array);
            CardPanel1.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+CardPanel1,ex); 
          }

//<UserCode_Begin_Bean_CardPanel1>

//<UserCode_End_Bean_CardPanel1>

          try
          {
            JButton3.setText(resourceBundle.getString("Back"));
            JButton3.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>
JButton3.setMnemonic('B');
//<UserCode_End_Bean_JButton3>

          try
          {
            JButton1.setText(resourceBundle.getString("Next"));
            JButton1.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>
JButton1.setMnemonic('N');
//<UserCode_End_Bean_JButton1>

          try
          {
            JButton2.setText(resourceBundle.getString("Cancel"));
            JButton2.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>
JButton2.setMnemonic('C');
//<UserCode_End_Bean_JButton2>
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+4,JButton2.getPreferredSize().height+1));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+16,JButton1.getPreferredSize().height+1));
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+12,JButton3.getPreferredSize().height+1));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+224,JPanel2.getPreferredSize().height+1));

  
//<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        CardPanel1= new com.adventnet.beans.panels.CardPanel(applet);
        JPanel2= new javax.swing.JPanel();
        JButton3= new javax.swing.JButton();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(CardPanel1,BorderLayout.CENTER);
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(JButton3);
JPanel2.add(JButton1);
JPanel2.add(JButton2);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      JButton2_JButton2_conn JButton2_JButton2_conn1 =  new JButton2_JButton2_conn();
      JButton2.addActionListener(JButton2_JButton2_conn1);
      JButton1_CardPanel1_conn JButton1_CardPanel1_conn1 =  new JButton1_CardPanel1_conn();
      JButton1.addActionListener(JButton1_CardPanel1_conn1);
      JButton3_CardPanel1_conn JButton3_CardPanel1_conn1 =  new JButton3_CardPanel1_conn();
      JButton3.addActionListener(JButton3_CardPanel1_conn1);
  
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



  public AdGroupWiz(  Frame owner, java.applet.Applet applet)
  {

    super(owner);		
    this.applet = applet;
    pack();
    //this.setTitle(resourceBundle.getString("AdGroupWiz"));
   this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

  }	


  public AdGroupWiz()
  {
    //<Begin_AdGroupWiz>
    pack();
  
    //<End_AdGroupWiz>
  }

  public AdGroupWiz(java.applet.Applet applet)
  {
	
    //<Begin_AdGroupWiz_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_AdGroupWiz_java.applet.Applet>
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
  
  	((com.adventnet.security.ui.AddGroup)CardPanel1.getCard("groups")).setFocus();
  }

 
  

 


//<Begin__class_JButton1_CardPanel1_conn>

 class JButton1_CardPanel1_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_CardPanel1_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  if(arg0.getActionCommand().equals(resourceBundle.getString("Next"))){
  if(((com.adventnet.security.ui.AddGroup)CardPanel1.getCard("groups")).getName().trim().equals(""))
     {
      Utilities.errorMessage(resourceBundle.getString("You must type a group name"));    
      ((com.adventnet.security.ui.AddGroup)CardPanel1.getCard("groups")).setFocus(); 
      return;
    }  
   //((com.adventnet.security.ui.ViewOper)CardPanel1.getCard("oper")).setTreeModel(AuthMain.model.getTreeModel());
   CardPanel1.showCard("oper"); 
   JButton1.setText(resourceBundle.getString("Finish"));   
   JButton3.setEnabled(true);   
  } 
   if(arg0.getActionCommand().equals(resourceBundle.getString("Finish"))){
 java.util.Hashtable hash = ((com.adventnet.security.ui.ViewOper)CardPanel1.getCard("oper")).getOperations();
 name = ((com.adventnet.security.ui.AddGroup)CardPanel1.getCard("groups")).getName();  
if(hash == null)
{
 hash = new java.util.Hashtable();    
}
 AuthMain.main.disableButtons();  
 AuthMain.model.addGroupOperData(name,hash,null); 
 //AuthMain.main.disableButtons();   
 dispose();  
   
   
   }  
     }
//<UserCode_End_Connection_JButton1_CardPanel1_conn>
 }//<End__class_JButton1_CardPanel1_conn>

 


//<Begin__class_JButton3_CardPanel1_conn>

 class JButton3_CardPanel1_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_CardPanel1_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
   
   CardPanel1.showCard( CardPanel1.getPreviousCardName());   
  if(CardPanel1.getSelectedCardName().equals("groups")) 
   {
    JButton3.setEnabled(false);
    JButton1.setText(resourceBundle.getString("Next"));   
   } 
  else
   {
    JButton3.setEnabled(true);
   }  
     }
//<UserCode_End_Connection_JButton3_CardPanel1_conn>
 }//<End__class_JButton3_CardPanel1_conn>

 


//<Begin__class_JButton2_JButton2_conn>

 class JButton2_JButton2_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  dispose();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn>
 }//<End__class_JButton2_JButton2_conn>
	//Utility Methods.
	
	
 public void setSecurityModel(AbstractSecurityModel model){
		this.model = model;
 }

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}












