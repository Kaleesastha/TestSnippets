

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;



//$Id: ViewPropertiesDialog.java,v 1.2.6.1 2012/01/25 05:12:46 karen.r Exp $



import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class ViewPropertiesDialog extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton closeButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


  
  
  











   


  public ViewPropertiesDialog()
  {
    //<Begin_ViewPropertiesDialog>
    pack();
  
    //<End_ViewPropertiesDialog>
  }

  public ViewPropertiesDialog(java.applet.Applet applet)
  {
    //<Begin_ViewPropertiesDialog_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ViewPropertiesDialog_java.applet.Applet>
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
            JPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("User Properties"),0,2,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            table.setModel(tableModel);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

          try
          {
            JPanel2.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JTextArea1.setOpaque(false);
            JTextArea1.setEditable(false);
            JTextArea1.setLineWrap(true);
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setText(resourceBundle.getString(""));
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
            closeButton.setText(resourceBundle.getString("Close"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>

//<UserCode_End_Bean_closeButton>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"User Property ","Value"});
//<UserCode_End_Bean_tableModel>
		closeButton.setPreferredSize(new Dimension(closeButton.getPreferredSize().width+0,closeButton.getPreferredSize().height+4));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+2,JScrollPane1.getPreferredSize().height+2));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+13));

  
          //<End_setUpProperties>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("User Property"),resourceBundle.getString("Value")});	
 
 TitledBorder tl=(TitledBorder)JPanel1.getBorder();
 tl.setTitle(resourceBundle.getString("User Properties"));
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
        this.setSize(getPreferredSize().width+362,getPreferredSize().height+309); 
          setTitle(resourceBundle.getString("ViewPropertiesDialog"));
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
        setTitle(resourceBundle.getString("User Properties"));
	table.getTableHeader().setReorderingAllowed(false);
	RuntimeConfigFrame.getCommonBuilderUIImpl().centerWindow(this);
	table.setDefaultRenderer(Object.class,new CustomRenderer());
	table.setDefaultEditor(Object.class,null);
	JLabel1.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));
	JTextArea1.setText(resourceBundle.getString("This table shows the User Properties configured for this type"));
	JTextArea1.setEditable(false);
	closeButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	setModal(true);
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      closeButton_closeButton_conn1 closeButton_closeButton_conn11 =  new closeButton_closeButton_conn1();
      closeButton.addActionListener(closeButton_closeButton_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel2= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JTextArea1= new javax.swing.JTextArea();
        JPanel3= new javax.swing.JPanel();
        closeButton= new javax.swing.JButton();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(table);
Top.add(JPanel2,BorderLayout.NORTH);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JTextArea1,cons);
Top.add(JPanel3,BorderLayout.SOUTH);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(closeButton);

  
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

	public void populateUI(Hashtable mainHash)
	{
		Enumeration enumerate = mainHash.keys();	
		while(enumerate.hasMoreElements())
		{
			String key = enumerate.nextElement().toString();
			if(key.equals("TYPE") || key.equals("menuName") || key.equals("iconName") || key.equals("MAP_FILTER"))
			{
				continue;
			}
		
			Vector v = new Vector();
			v.add(key);
			v.add(mainHash.get(key));
			tableModel.addRow(v);
		}
	}


 
//<Begin__class_closeButton_closeButton_conn1>

 class closeButton_closeButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_closeButton_closeButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  ViewPropertiesDialog.this.setVisible(false);
  ViewPropertiesDialog.this.dispose();
     }
//<UserCode_End_Connection_closeButton_closeButton_conn1>
 }//<End__class_closeButton_closeButton_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}










