// $Id: Editor.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.management.i18n.tools;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Editor extends JDialog 
{

    java.util.ResourceBundle resourceBundle = null;

	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "I18NToolResources";
    //static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTextArea KeyArea = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTextArea ValueArea = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JCheckBox JCheckBox1 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton3 = null;
	//<End_Variable_Declarations>



  public Editor(java.util.ResourceBundle bundle)
  {
      resourceBundle = bundle;
    //<Begin_Editor>
    pack();
  
    //<End_Editor>
  }

  public Editor(java.applet.Applet applet, java.util.ResourceBundle bundle)
  {
      resourceBundle = bundle;
    //<Begin_Editor_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
    //<End_Editor_java.applet.Applet>
  }

  public Editor(java.applet.Applet applet)
  {
      this(applet, null);
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

    public void init()
  {
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	//resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+508,getPreferredSize().height+231); 
          setTitle(resourceBundle.getString("Row Editor"));
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
          I18NUtils.WindowPosition(this);
	ValueArea.requestFocus();

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
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 
setResizable(false);
          try
          {
            JScrollPane1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Key")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

          try
          {
            KeyArea.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+KeyArea,ex); 
          }

          try
          {
            JScrollPane2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Edit Value")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane2,ex); 
          }

          try
          {
            ValueArea.setEditable(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ValueArea,ex); 
          }

          try
          {
            JCheckBox1.setText(resourceBundle.getString("Edit key"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JCheckBox1,ex); 
          }

          try
          {
            JButton1.setText(resourceBundle.getString("OK"));
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
            JButton3.setText(resourceBundle.getString("Apply"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+8,JButton3.getPreferredSize().height+0));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+22,JButton1.getPreferredSize().height+0));
		JCheckBox1.setPreferredSize(new Dimension(JCheckBox1.getPreferredSize().width+138,JCheckBox1.getPreferredSize().height+0));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+338,JPanel1.getPreferredSize().height+10));

  
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
        JPanel1= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        KeyArea= new javax.swing.JTextArea();
        JScrollPane2= new javax.swing.JScrollPane();
        ValueArea= new javax.swing.JTextArea();
        JPanel2= new javax.swing.JPanel();
        JCheckBox1= new javax.swing.JCheckBox();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridLayout(2,1,5,5));
JPanel1.add(JScrollPane1);
JScrollPane1.getViewport().add(KeyArea);
JPanel1.add(JScrollPane2);
JScrollPane2.getViewport().add(ValueArea);
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(JCheckBox1);
JPanel2.add(JButton1);
JPanel2.add(JButton2);
JPanel2.add(JButton3);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      JCheckBox1_JCheckBox1_conn1 JCheckBox1_JCheckBox1_conn11 =  new JCheckBox1_JCheckBox1_conn1();
      JCheckBox1.addItemListener(JCheckBox1_JCheckBox1_conn11);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      JButton3.addActionListener(JButton3_JButton3_conn11);
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


 
  

JTable table = null;
int tablerow = 0;	
	
public void setText(String key, String value, JTable table, int row)
	{
		this.table = table;
		tablerow = row;
		KeyArea.setText(key);
                ValueArea.setText(value);
	}

public void setValueToTable()
	{
	 if(table.isEditing())
	 {
	 ((DefaultCellEditor)table.getCellEditor()).stopCellEditing();
	 }	
	    table.setValueAt(KeyArea.getText(), tablerow, 0);	
	    table.setValueAt(ValueArea.getText(), tablerow, 1);
	}	

//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 dispose(); 
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
 setValueToTable(); 
 dispose(); 
     }
}
//<End__class_JButton1_JButton1_conn1>


//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 setValueToTable();  
     }
}
//<End__class_JButton3_JButton3_conn1>

 


//<Begin__class_JCheckBox1_JCheckBox1_conn1>

 class JCheckBox1_JCheckBox1_conn1 implements java.awt.event.ItemListener, java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
 if(JCheckBox1.isSelected()) 
  {
   KeyArea.setEditable(true);
  } 
 else
  {
   KeyArea.setEditable(false);   
  } 
     }
}
//<End__class_JCheckBox1_JCheckBox1_conn1>
}






