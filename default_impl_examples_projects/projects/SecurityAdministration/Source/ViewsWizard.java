
//$Id: ViewsWizard.java,v 1.1 2006/08/29 13:57:02 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import com.adventnet.security.authorization.AuthViewWithOperations;
public class ViewsWizard extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton backButton = null;
	javax.swing.JButton nextButton = null;
	javax.swing.JButton cancelButton = null;
	com.adventnet.beans.panels.CardPanel CardPanel1 = null;
	//<End_Variable_Declarations>

    AbstractSecurityModel model = null;	
    java.util.Vector ViewPropOper = null;
    private boolean STATE = true;

 public ViewsWizard()
  {
    //<Begin_ViewsWizard>
    pack();
  
    //<End_ViewsWizard>
		
  }

  public ViewsWizard(java.applet.Applet applet)
  {
    //<Begin_ViewsWizard_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ViewsWizard_java.applet.Applet>

  }
  public ViewsWizard(Frame owner,java.applet.Applet applet)
  {
   super(owner);
    this.applet = applet;
    pack();
    this.setTitle("Views Wizard");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  

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

  
    public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            Top.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_Top>
setResizable(false);

//setModal(true);
//<UserCode_End_Bean_Top>

          try
          {
            backButton.setText(resourceBundle.getString("Back"));
            backButton.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+backButton,ex); 
          }

//<UserCode_Begin_Bean_backButton>
backButton.setMnemonic('B');
//<UserCode_End_Bean_backButton>

          try
          {
            nextButton.setText(resourceBundle.getString("Next"));
            nextButton.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nextButton,ex); 
          }

//<UserCode_Begin_Bean_nextButton>
nextButton.setMnemonic('N');

//<UserCode_End_Bean_nextButton>

          try
          {
            cancelButton.setText(resourceBundle.getString("Cancel"));
            cancelButton.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>

          try
          {
            CardPanel1.setBorder(new javax.swing.border.SoftBevelBorder(0));
            java.lang.String[]  CardPanel1cardAndClassNames_array = new java.lang.String[ 3 ]; 
            CardPanel1cardAndClassNames_array[ 0 ] = resourceBundle.getString("view=com.adventnet.security.ui.ViewName");
            CardPanel1cardAndClassNames_array[ 1 ] = resourceBundle.getString("prop=com.adventnet.security.ui.ViewProperty");
            CardPanel1cardAndClassNames_array[ 2 ] = resourceBundle.getString("oper=com.adventnet.security.ui.ViewOper");
            CardPanel1.setCardAndClassNames(CardPanel1cardAndClassNames_array);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+CardPanel1,ex); 
          }

//<UserCode_Begin_Bean_CardPanel1>

//<UserCode_End_Bean_CardPanel1>
		CardPanel1.setPreferredSize(new Dimension(CardPanel1.getPreferredSize().width+471,CardPanel1.getPreferredSize().height+180));
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+2,cancelButton.getPreferredSize().height+0));
		nextButton.setPreferredSize(new Dimension(nextButton.getPreferredSize().width+16,nextButton.getPreferredSize().height+0));
		backButton.setPreferredSize(new Dimension(backButton.getPreferredSize().width+12,backButton.getPreferredSize().height+0));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+208,JPanel2.getPreferredSize().height+6));

  
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
        this.setSize(getPreferredSize().width+569,getPreferredSize().height+404); 
          setTitle(resourceBundle.getString("ViewsWizard"));
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
	       setTitle(resourceBundle.getString("Views Wizard"));
		CardPanel1.showCard("view");
		backButton.setEnabled(false);
		//JLabel1.setBackground(Color.white);
		//AuthMain.getBuilderUiIfInstance().centerWindow(this);
		((com.adventnet.security.ui.ViewOper)CardPanel1.getCard("oper")).setTreeModel(model.getTreeModel());
		addWindowListener(new WindowAdapter()
						{
							public void windowClosing(WindowEvent we)
							 	{
								 ViewConfig.getInstance().enableButtons();
								 dispose();  
							 	}
						}
					   );
	
	 } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      backButton_backButton_conn1 backButton_backButton_conn11 =  new backButton_backButton_conn1();
      backButton.addActionListener(backButton_backButton_conn11);
      nextButton_nextButton_conn1 nextButton_nextButton_conn11 =  new nextButton_nextButton_conn1();
      nextButton.addActionListener(nextButton_nextButton_conn11);
      cancelButton_cancelButton_conn1 cancelButton_cancelButton_conn11 =  new cancelButton_cancelButton_conn1();
      cancelButton.addActionListener(cancelButton_cancelButton_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        backButton= new javax.swing.JButton();
        nextButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        CardPanel1= new com.adventnet.beans.panels.CardPanel(applet);

  
        //<End_initVariables>
		
		ViewPropOper = new java.util.Vector(); 
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(backButton);
JPanel2.add(nextButton);
JPanel2.add(cancelButton);
Top.add(CardPanel1,BorderLayout.CENTER);

  
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
            if (input.equals("MS_MODE")) value = "3"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "SecurityAdministrationResources"; 
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


 public void setSecurityModel(AbstractSecurityModel model)
	{
		this.model = model;
	}	
	
 public void setValues(com.adventnet.security.authorization.AuthViewWithOperations viewOp)
	{
		
		if(viewOp == null)
		{
			return;
		}	
		
		if(viewOp.getAuthorizedViewName() == null)
		{
			return;
		}	
		
((com.adventnet.security.ui.ViewName)CardPanel1.getCard("view")).setViewName(viewOp.getAuthorizedViewName());
((com.adventnet.security.ui.ViewProperty)CardPanel1.getCard("prop")).setProperties(viewOp.getAuthorizedViewName(),viewOp.getViewProperties());
((com.adventnet.security.ui.ViewOper)CardPanel1.getCard("oper")).setOperations(viewOp.getAuthorizedViewName(),viewOp.getOperations());
		
		
	}	
	
public void setState(boolean state)
	{
		STATE = state;
	}
	
public void addViews()
	{
		
model.addViewOp(ViewPropOper.elementAt(0).toString(),(java.util.Properties)ViewPropOper.elementAt(1),
				(java.util.Hashtable)ViewPropOper.elementAt(2));
   	}	

public void editViews()
	{
	 model.modViewOp(ViewPropOper.elementAt(0).toString(),(java.util.Properties)ViewPropOper.elementAt(1),
	 (java.util.Hashtable)ViewPropOper.elementAt(2));
	}		
	
	
	
	//<Begin__class_backButton_backButton_conn1>

 class backButton_backButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_backButton_backButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  if(nextButton.getActionCommand().equals("Finish"))
   {
    nextButton.setText(resourceBundle.getString("Next"));
    nextButton.setActionCommand("Next"); 
   }  
   CardPanel1.showCard( CardPanel1.getPreviousCardName());   
  if(CardPanel1.getSelectedCardName().equals("view")) 
   {
    backButton.setEnabled(false);
   } 
  else
   {
    backButton.setEnabled(true);
   }  
   
     }
//<UserCode_End_Connection_backButton_backButton_conn1>
 }//<End__class_backButton_backButton_conn1>
//<Begin__class_nextButton_nextButton_conn1>

 class nextButton_nextButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_nextButton_nextButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  if(arg0.getActionCommand().equals("Finish"))
   {

     ViewPropOper.add(2,  ((com.adventnet.security.ui.ViewOper)CardPanel1.getCard("oper")).getOperations()  );
    
   java.util.Properties tempProp = (java.util.Properties)ViewPropOper.elementAt(1);
    Hashtable tempHash = (Hashtable)ViewPropOper.elementAt(2);    

  if( (tempProp.size() == 0) && (tempHash.size() == 0))
   {
    Utilities.errorMessage(resourceBundle.getString("You have to select either properties or \noperations or both")); 
    return; 
   }  
    
 if(STATE)
  {
    addViews();
  }
 else
  {
    editViews();
  }    
  setVisible(false); 
  return; 
   }  
   
   nextButton.setMnemonic('N');    
   if(CardPanel1.getSelectedCardName().equals("view"))
      {
String vname = ((com.adventnet.security.ui.ViewName)CardPanel1.getCard("view")).getViewName( );
 if(vname.equals(""))
  {
   Utilities.errorMessage(resourceBundle.getString("You have to give a view name"));
   return; 
  }   
   ((com.adventnet.security.ui.ViewProperty)CardPanel1.getCard("prop")).setViewName(vname);
  ((com.adventnet.security.ui.ViewOper)CardPanel1.getCard("oper")).setViewName(vname);    
 ViewPropOper.add(0, vname );    
      } 
  backButton.setEnabled(true);   
   if(CardPanel1.getSelectedCardName().equals("prop"))
      {
 ViewPropOper.add(1,  ((com.adventnet.security.ui.ViewProperty)CardPanel1.getCard("prop")).getProperties( )  );
//((com.adventnet.security.ui.ViewOper)CardPanel1.getCard("oper")).setTreeModel(model.getTreeModel());   
       }  
     CardPanel1.showCard( CardPanel1.getNextCardName()); 
     if(CardPanel1.getSelectedCardName().equals("oper"))
       {
      nextButton.setActionCommand("Finish");  
      nextButton.setText(resourceBundle.getString("Finish"));   
      nextButton.setMnemonic('F');      
       }
   }
//<UserCode_End_Connection_nextButton_nextButton_conn1>
 }//<End__class_nextButton_nextButton_conn1>
//<Begin__class_cancelButton_cancelButton_conn1>

 class cancelButton_cancelButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_cancelButton_cancelButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
ViewConfig.getInstance().enableButtons();
 dispose();  
     }
//<UserCode_End_Connection_cancelButton_cancelButton_conn1>
 }//<End__class_cancelButton_cancelButton_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}





















