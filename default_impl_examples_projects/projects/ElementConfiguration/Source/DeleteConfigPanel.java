//$Id: DeleteConfigPanel.java,v 1.1.4.1 2012/05/29 06:26:56 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$13
//<End_Version>
package com.adventnet.nms.config;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;




public class DeleteConfigPanel extends JPanel
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";//No I18N
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel11 = null;
	javax.swing.JLabel JLabel11 = null;
	javax.swing.JComboBox JComboBox11 = null;
	javax.swing.JLabel JLabel21 = null;
	javax.swing.JTextField JTextField11 = null;
	javax.swing.JLabel JLabel31 = null;
	javax.swing.JRadioButton JRadioButton21 = null;
	javax.swing.JRadioButton JRadioButton31 = null;
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
        setPreferredSize(new Dimension(getPreferredSize().width+448,getPreferredSize().height+340));
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
          showStatus(resourceBundle.getString("Error in init method"),ex); //No I18N
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
            if (input.equals("HOST")) value = "localhost"; //No I18N
            if (input.equals("PORT")) value = "161"; //No I18N
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; //No I18N
            }
        return value;


           //<End_getParameter_String>
  }
  public void setUpProperties()
  {
  //<Begin_setUpProperties>

          try
          {
            JPanel11.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Target Configuration"),0,0,new Font("Dialog",0,12),new Color(-13421773)));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel11,ex); //No I18N
          }

//<UserCode_Begin_Bean_JPanel11>

//<UserCode_End_Bean_JPanel11>

          try
          {
            JLabel11.setText(resourceBundle.getString("Target Datasource"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel11,ex); //No I18N
          }

//<UserCode_Begin_Bean_JLabel11>

//<UserCode_End_Bean_JLabel11>

//<UserCode_Begin_Bean_JComboBox11>
JComboBox11.addItem("Startup");//No I18N
JComboBox11.addItem("Candidate");//No I18N
JComboBox11.addItem("Running");//No I18N
//<UserCode_End_Bean_JComboBox11>

          try
          {
            JLabel21.setText(resourceBundle.getString("Target URL"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel21,ex); //No I18N
          }

//<UserCode_Begin_Bean_JLabel21>
JLabel21.setEnabled(false);
JTextField11.setEnabled(false);
//<UserCode_End_Bean_JLabel21>

          try
          {
            JLabel31.setText(resourceBundle.getString("Target Type"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel31,ex); //No I18N
          }

//<UserCode_Begin_Bean_JLabel31>

//<UserCode_End_Bean_JLabel31>

          try
          {
            JRadioButton21.setText(resourceBundle.getString("Datasource"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JRadioButton21,ex); //No I18N
          }

//<UserCode_Begin_Bean_JRadioButton21>
JRadioButton21.setSelected(true);
//<UserCode_End_Bean_JRadioButton21>

          try
          {
            JRadioButton31.setText(resourceBundle.getString("URL"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JRadioButton31,ex); //No I18N
          }

//<UserCode_Begin_Bean_JRadioButton31>

//<UserCode_End_Bean_JRadioButton31>

//<UserCode_Begin_Bean_ButtonGroup1>
ButtonGroup1.add(JRadioButton21);
ButtonGroup1.add(JRadioButton31);
//<UserCode_End_Bean_ButtonGroup1>


          //<End_setUpProperties>
  }
  public void initVariables()
  {
        //<Begin_initVariables>
        Top= new javax.swing.JPanel();
        JPanel11= new javax.swing.JPanel();
        JLabel11= new javax.swing.JLabel();
        JComboBox11= new javax.swing.JComboBox();
        JLabel21= new javax.swing.JLabel();
        JTextField11= new javax.swing.JTextField();
        JLabel31= new javax.swing.JLabel();
        JRadioButton21= new javax.swing.JRadioButton();
        JRadioButton31= new javax.swing.JRadioButton();
        ButtonGroup1= new javax.swing.ButtonGroup();


        //<End_initVariables>
  }
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
Top.setLayout(null);
JPanel11.setBounds(5,5,430,150);
Top.add(JPanel11);
JPanel11.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel11.add(JLabel11,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,0,1,0.0,0.0,cons.NORTH,cons.HORIZONTAL,inset,0,0);
JPanel11.add(JComboBox11,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel11.add(JLabel21,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel11.add(JTextField11,cons);
inset = new Insets(0,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel11.add(JLabel31,cons);
inset = new Insets(0,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.NORTH,cons.HORIZONTAL,inset,0,0);
JPanel11.add(JRadioButton21,cons);
inset = new Insets(0,5,5,5);
setConstraints(2,0,1,1,0.0,0.0,cons.NORTH,cons.HORIZONTAL,inset,0,0);
JPanel11.add(JRadioButton31,cons);


//<End_setUpGUI_Container>
  }
  public void setUpConnections()
  {
  //<Begin_setUpConnections>

      JRadioButton21_JPanel11_conn JRadioButton21_JPanel11_conn1 =  new JRadioButton21_JPanel11_conn();
      JRadioButton21.addActionListener(JRadioButton21_JPanel11_conn1);
      JRadioButton31.addActionListener(JRadioButton21_JPanel11_conn1);

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
	  String configOperation = "delete-config";//No I18N
	  String configAction = "";//No I18N
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
	  
	  if(JRadioButton21.isSelected())
	  {
		  target = JComboBox11.getSelectedItem().toString().toLowerCase();
	  }
	  else if (JRadioButton31.isSelected()) {
		  targetUrl = JTextField11.getText();
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



  public void populateData(Vector rowData) {

	  String target =(String)rowData.get(7);
	  String targetUrl=(String)rowData.get(9);
	  String testOption=(String)rowData.get(11);
	  String errorOption=(String)rowData.get(12);
	  if (!target.equals(""))//No I18N
	  {
		  JRadioButton21.setSelected(true);
		  if (target.equals("startup"))//No I18N
		  {
			  JComboBox11.setSelectedIndex(0);
		  }
		  else if (target.equals("candidate"))//No I18N
		  {
			  JComboBox11.setSelectedIndex(1);
		  }
		  else if (target.equals("running"))//No I18N
		  {
			  JComboBox11.setSelectedIndex(2);
		  }
	  }
	  else if (!targetUrl.equals(""))//No I18N
	  {
		  JRadioButton31.setSelected(true);
		  JTextField11.setText(targetUrl);
	  }

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


   public void targetSelectAction()
  {
       if(JRadioButton21.isSelected())
       {
            JLabel11.setEnabled(true);
            JComboBox11.setEnabled(true);
            JLabel21.setEnabled(false);
            JTextField11.setEnabled(false);
       }
       else if (JRadioButton31.isSelected())
       {
            JLabel11.setEnabled(false);
            JComboBox11.setEnabled(false);
            JLabel21.setEnabled(true);
            JTextField11.setEnabled(true);
       }
  }

  public DeleteConfigPanel()
  {
    //<Begin_DeleteConfigPanel>
    this.init();

    //<End_DeleteConfigPanel>
  }

  public DeleteConfigPanel(java.applet.Applet applet)
  {
    //<Begin_DeleteConfigPanel_java.applet.Applet>
    this.applet = applet;
    this.init();

    //<End_DeleteConfigPanel_java.applet.Applet>
  }




//<Begin__class_JRadioButton21_JPanel11_conn>

 class JRadioButton21_JPanel11_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JRadioButton21_JPanel11_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          targetSelectAction();
     }
//<UserCode_End_Connection_JRadioButton21_JPanel11_conn>
 }//<End__class_JRadioButton21_JPanel11_conn>
}






