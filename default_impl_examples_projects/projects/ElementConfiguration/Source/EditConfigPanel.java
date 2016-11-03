//$Id: EditConfigPanel.java,v 1.1.4.1 2012/05/29 06:26:56 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$19
//<End_Version>
package com.adventnet.nms.config;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;




public class EditConfigPanel extends JPanel
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";//No I18N
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JComboBox JComboBox1 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JComboBox JComboBox2 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JComboBox JComboBox3 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JRadioButton JRadioButton4 = null;
	javax.swing.JRadioButton JRadioButton5 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JTextField JTextField1 = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JTextField JTextField2 = null;
	javax.swing.JLabel JLabel8 = null;
	javax.swing.JTextField JTextField3 = null;
	javax.swing.JLabel JLabel51 = null;
	javax.swing.JComboBox JComboBox31 = null;
	javax.swing.JLabel JLabel52 = null;
	javax.swing.JComboBox JComboBox32 = null;
	javax.swing.ButtonGroup ButtonGroup1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>















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
	if(getParameter("RESOURCE_PROPERTIES" ) != null)//No I18N
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");//No I18N
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);//No I18N
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+474,getPreferredSize().height+473)); 
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
          showStatus(resourceBundle.getString("Error in init method"),ex);//No I18N 
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
            if (input.equals("HOST")) value = "localhost";//No I18N
            if (input.equals("PORT")) value = "161";//No I18N
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources";//No I18N
            }
        return value;

  
           //<End_getParameter_String>
  }
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            JLabel2.setText(resourceBundle.getString("Config Action"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JComboBox1.setToolTipText(resourceBundle.getString("Select the config action to be performed."));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JComboBox1,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JComboBox1>
JComboBox1.addItem("Create");//No I18N
JComboBox1.addItem("Merge");//No I18N
JComboBox1.addItem("Replace");//No I18N
JComboBox1.addItem("Delete");//No I18N

//<UserCode_End_Bean_JComboBox1>

          try
          {
            JLabel4.setText(resourceBundle.getString("Default Action"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

//<UserCode_Begin_Bean_JComboBox2>
JComboBox2.addItem("None");//No I18N          
JComboBox2.addItem("Merge");//No I18N
JComboBox2.addItem("Replace");//No I18N
//<UserCode_End_Bean_JComboBox2>

          try
          {
            JLabel5.setText(resourceBundle.getString("Target Datasource"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

//<UserCode_Begin_Bean_JComboBox3>
JComboBox3.addItem("Startup");//No I18N
JComboBox3.addItem("Running");//No I18N
JComboBox3.addItem("Candidate");//No I18N
//<UserCode_End_Bean_JComboBox3>

          try
          {
            JPanel6.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Config Element Details"),0,0,new Font("Dialog",0,12),new Color(-13421773)));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            JRadioButton4.setText(resourceBundle.getString("Path Expression"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JRadioButton4,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JRadioButton4>
JRadioButton4.setSelected(true);
//<UserCode_End_Bean_JRadioButton4>

          try
          {
            JRadioButton5.setText(resourceBundle.getString("URI"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JRadioButton5,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JRadioButton5>

//<UserCode_End_Bean_JRadioButton5>

          try
          {
            JLabel6.setText(resourceBundle.getString("Namespace"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

          try
          {
            JLabel7.setText(resourceBundle.getString("Config Path"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel7,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>

          try
          {
            JLabel8.setText(resourceBundle.getString("Sub Path"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel8,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JLabel8>

//<UserCode_End_Bean_JLabel8>

          try
          {
            JLabel51.setText(resourceBundle.getString("Action On Error"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel51,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JLabel51>

//<UserCode_End_Bean_JLabel51>

//<UserCode_Begin_Bean_JComboBox31>
JComboBox31.addItem("stop-on-error");//No I18N
JComboBox31.addItem("continue-on-error");//No I18N
JComboBox31.addItem("rollback-on-error");//No I18N
//<UserCode_End_Bean_JComboBox31>

          try
          {
            JLabel52.setText(resourceBundle.getString("Validate Option"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel52,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JLabel52>

//<UserCode_End_Bean_JLabel52>

//<UserCode_Begin_Bean_JComboBox32>
JComboBox32.addItem("Set without Validation");//No I18N
JComboBox32.addItem("Validate then Set");//No I18N
//<UserCode_End_Bean_JComboBox32>

//<UserCode_Begin_Bean_ButtonGroup1>
ButtonGroup1.add(JRadioButton4);
ButtonGroup1.add(JRadioButton5);
//<UserCode_End_Bean_ButtonGroup1>

  
          //<End_setUpProperties>
  }
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JComboBox1= new javax.swing.JComboBox();
        JLabel4= new javax.swing.JLabel();
        JComboBox2= new javax.swing.JComboBox();
        JLabel5= new javax.swing.JLabel();
        JComboBox3= new javax.swing.JComboBox();
        JPanel6= new javax.swing.JPanel();
        JRadioButton4= new javax.swing.JRadioButton();
        JRadioButton5= new javax.swing.JRadioButton();
        JPanel7= new javax.swing.JPanel();
        JLabel6= new javax.swing.JLabel();
        JTextField1= new javax.swing.JTextField();
        JLabel7= new javax.swing.JLabel();
        JTextField2= new javax.swing.JTextField();
        JLabel8= new javax.swing.JLabel();
        JTextField3= new javax.swing.JTextField();
        JLabel51= new javax.swing.JLabel();
        JComboBox31= new javax.swing.JComboBox();
        JLabel52= new javax.swing.JLabel();
        JComboBox32= new javax.swing.JComboBox();
        ButtonGroup1= new javax.swing.ButtonGroup();

  
        //<End_initVariables>
  }
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,-1,1,0.1,0.0,cons.NORTH,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JComboBox1,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,-1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JComboBox2,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel5,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,-1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JComboBox3,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,0,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(5,10,10,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel6.add(JRadioButton4,cons);
inset = new Insets(5,10,10,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel6.add(JRadioButton5,cons);
inset = new Insets(5,5,10,5);
setConstraints(0,1,0,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JPanel7,cons);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,0);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel7.add(JLabel6,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,-1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel7.add(JTextField1,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel7.add(JLabel7,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,-1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel7.add(JTextField2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel7.add(JLabel8,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,-1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel7.add(JTextField3,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel51,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JComboBox31,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel52,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JComboBox32,cons);

  
//<End_setUpGUI_Container>
  }
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      JRadioButton5_JPanel7_conn JRadioButton5_JPanel7_conn1 =  new JRadioButton5_JPanel7_conn();
      JRadioButton5.addActionListener(JRadioButton5_JPanel7_conn1);
      JRadioButton4_JPanel7_conn JRadioButton4_JPanel7_conn1 =  new JRadioButton4_JPanel7_conn();
      JRadioButton4.addActionListener(JRadioButton4_JPanel7_conn1);
      JRadioButton5.addActionListener(JRadioButton4_JPanel7_conn1);
  
      //<End_setUpConnections>
  }




  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);//No I18N
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);//No I18N
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }

  public Vector getRowData()
  {
	  String configOperation = "edit-config";//No I18N
	  String configAction = "create";//No I18N
	  String namespace = "";//No I18N
	  String configPath = "";//No I18N
	  String subPath = "";//No I18N
	  String configUrl = "";//No I18N
	  String source ="";//No I18N
	  String target ="";//No I18N
	  String sourceUrl="";//No I18N
	  String targetUrl="";//No I18N
	  String defaultAction="none";//No I18N
	  String testOption="";//No I18N
	  String errorOption="";//No I18N
	  
	  Vector rowData = new Vector();
	  configAction = JComboBox1.getSelectedItem().toString().toLowerCase();
	  defaultAction = JComboBox2.getSelectedItem().toString().toLowerCase();
	  target = JComboBox3.getSelectedItem().toString().toLowerCase();
	  errorOption = JComboBox31.getSelectedItem().toString();
	  if(JComboBox32.getSelectedItem().equals("Set without Validation"))//No I18N
	  {
	       testOption="set";//No I18N
	  }
	  else if(JComboBox32.getSelectedItem().equals("Validate then Set"))//No I18N
	  {
	       testOption="test-then-set";//No I18N
	  }
	  if (JRadioButton4.isSelected())
	  {
		  namespace = JTextField1.getText();
		  configPath = JTextField2.getText();
		  subPath=JTextField3.getText();
	  }
	  else if (JRadioButton5.isSelected())
	  {
		  configUrl = JTextField1.getText();
	  }
	  rowData.add(configOperation);
	  rowData.add(configAction);
	  rowData.add(namespace);
	  rowData.add(configPath);
	  rowData.add(subPath);
	  rowData.add(configUrl);
	  rowData.add(source);
	  rowData.add(target);
	  rowData.add(sourceUrl);
	  rowData.add(targetUrl);
	  rowData.add(defaultAction);
	  rowData.add(testOption);
	  rowData.add(errorOption);
	  return rowData;
  }



  public EditConfigPanel()
  {
    //<Begin_EditConfigPanel>
    this.init();
  
    //<End_EditConfigPanel>
  }

  public EditConfigPanel(java.applet.Applet applet)
  {
    //<Begin_EditConfigPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_EditConfigPanel_java.applet.Applet>
  }
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
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


public void pathSelectionAction()
{
     if (JRadioButton4.isSelected())
     {
          JLabel6.setText("Namespace");//No I18N
          JLabel6.setEnabled(true);
          JLabel7.setEnabled(true);
          JLabel8.setEnabled(true);
          JTextField1.setEnabled(true);
          JTextField2.setEnabled(true);
          JTextField3.setEnabled(true);
     }
    if (JRadioButton5.isSelected())
     {
          JLabel6.setText("Config URL");//No I18N
          JLabel6.setEnabled(true);
          JLabel7.setEnabled(false);
          JLabel8.setEnabled(false);
          JTextField1.setEnabled(true);
          JTextField1.setText("");
          JTextField2.setEnabled(false);
          JTextField3.setEnabled(false);
     }
}


//<Begin__class_JRadioButton4_JPanel7_conn>

 class JRadioButton4_JPanel7_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JRadioButton4_JPanel7_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent evt)
     {
          pathSelectionAction();
     }
//<UserCode_End_Connection_JRadioButton4_JPanel7_conn>
 }//<End__class_JRadioButton4_JPanel7_conn>




//<Begin__class_JRadioButton5_JPanel7_conn>

 class JRadioButton5_JPanel7_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JRadioButton5_JPanel7_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          pathSelectionAction();
     }
//<UserCode_End_Connection_JRadioButton5_JPanel7_conn>
 }//<End__class_JRadioButton5_JPanel7_conn>




 	public void populateData(Vector rowData) {

 		String configAction = (String)rowData.get(1);
 		String namespace = (String)rowData.get(2);
 		String configPath = (String)rowData.get(3);
 		String subPath = (String)rowData.get(4);
 		String configUrl = (String)rowData.get(5);
 		String target =(String)rowData.get(7);
 		String targetUrl=(String)rowData.get(9);
 		String defaultAction=(String)rowData.get(10);
 		String testOption=(String)rowData.get(11);
 		String errorOption=(String)rowData.get(12);
 		
 		if(configAction.equals("create"))//No I18N
 		{
 			JComboBox1.setSelectedIndex(0);
 		}
 		else if(configAction.equals("merge"))//No I18N
 		{
 			JComboBox1.setSelectedIndex(1);
 		}
 		else if(configAction.equals("replace"))//No I18N
 		{
 			JComboBox1.setSelectedIndex(2);
 		}
 		else if(configAction.equals("delete"))//No I18N
 		{
 			JComboBox1.setSelectedIndex(3);
 		}
 		
 		if (defaultAction.equals("none"))//No I18N
 		{
 			JComboBox2.setSelectedIndex(0);
 		}
 		else if (defaultAction.equals("merge"))//No I18N
 		{
 			JComboBox2.setSelectedIndex(1);
 		}
 		else if (defaultAction.equals("replace"))//No I18N
 		{
 			JComboBox2.setSelectedIndex(2);
 		}
 		
 		if (target.equals("startup"))//No I18N
 		{
 			JComboBox3.setSelectedIndex(0);
 		}
 		else  if (target.equals("running"))//No I18N
 		{
 			JComboBox3.setSelectedIndex(1);
 		}
 		else  if (target.equals("candidate"))//No I18N
 		{
 			JComboBox3.setSelectedIndex(2);
 		}
 		
 		JComboBox31.setSelectedItem(errorOption);
 		
 		if (testOption.equals("set"))//No I18N
 		{
 		     JComboBox32.setSelectedIndex(0);
 		}
 		else if (testOption.equals("test-then-set"))//No I18N
 		{
 		     JComboBox32.setSelectedIndex(1);
 		}
 		
 		if(configUrl.equals(""))//No I18N
 		{
 			JRadioButton4.setSelected(true);
 			JRadioButton5.setSelected(false);
 			JTextField1.setText(namespace);
 			JTextField2.setText(configPath);
 			JTextField3.setText(subPath);
 		}
 		else
 		{
 			JRadioButton4.setSelected(false);
 			JRadioButton5.setSelected(true);
 			JLabel6.setText("Config URL");//No I18N
 	        JLabel6.setEnabled(true);
 	        JLabel7.setEnabled(false);
 	        JLabel8.setEnabled(false);
 	        JTextField1.setEnabled(true);
 	        JTextField2.setEnabled(false);
 	        JTextField3.setEnabled(false);
 	        JTextField1.setText(configUrl);
 		}

 	}
}









