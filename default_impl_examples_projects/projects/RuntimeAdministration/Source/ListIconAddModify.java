

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;


//$Id: ListIconAddModify.java,v 1.2.6.1 2012/01/25 05:12:46 karen.r Exp $



import com.adventnet.nms.util.CommonBuilderUIInterface;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;

public class ListIconAddModify extends JDialog 
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
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField typeText = null;
	javax.swing.JTextField menuFileText = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JPanel JPanel10 = null;
	javax.swing.JPanel JPanel11 = null;
	javax.swing.JPanel JPanel13 = null;
	javax.swing.JButton enterButton = null;
	javax.swing.JButton deleteButton = null;
	javax.swing.JPanel JPanel12 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JTextField severityText = null;
	javax.swing.JTextField imageNameText = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel14 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JCheckBox transparentCheck = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	ListIconUI mainUI = null;
	Vector severityVector = null;
	boolean modifyFlag = false;  
	CommonBuilderUIInterface uiUtils = null;  
	boolean transparentFlag = false;  
	String transparentValue = "";
	Hashtable cloneHash = null;
	Hashtable rootHash = null;
	





	public ListIconAddModify(Vector severity,ListIconUI ui,java.applet.Applet applet)
	{
		this.applet=applet;
		uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
		mainUI = ui;
		severityVector = severity;
	}



   


  public ListIconAddModify()
  {
	
    //<Begin_ListIconAddModify>
    pack();
  
    //<End_ListIconAddModify>
	
  }

  public ListIconAddModify(java.applet.Applet applet)
  {
    //<Begin_ListIconAddModify_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ListIconAddModify_java.applet.Applet>
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
            JLabel1.setText(resourceBundle.getString("Managed Object UI Settings"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

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
            JLabel2.setText(resourceBundle.getString("Type"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel3.setText(resourceBundle.getString("Menu File Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

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
            JLabel4.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JPanel6.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Image Details"),0,2,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            JPanel10.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel10,ex); 
          }

//<UserCode_Begin_Bean_JPanel10>

//<UserCode_End_Bean_JPanel10>

          try
          {
            enterButton.setText(resourceBundle.getString("Update"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+enterButton,ex); 
          }

//<UserCode_Begin_Bean_enterButton>

//<UserCode_End_Bean_enterButton>

          try
          {
            deleteButton.setText(resourceBundle.getString("Delete"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+deleteButton,ex); 
          }

//<UserCode_Begin_Bean_deleteButton>

//<UserCode_End_Bean_deleteButton>

          try
          {
            JLabel6.setText(resourceBundle.getString("Severity"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

          try
          {
            JLabel7.setText(resourceBundle.getString("Image Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel7,ex); 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>

          try
          {
            JButton1.setMaximumSize(new Dimension(25,20));
            JButton1.setMinimumSize(new Dimension(25,20));
            JButton1.setPreferredSize(new Dimension(25,20));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

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
            transparentCheck.setText(resourceBundle.getString("Use Transparent Image"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+transparentCheck,ex); 
          }

//<UserCode_Begin_Bean_transparentCheck>

//<UserCode_End_Bean_transparentCheck>

          try
          {
            JPanel4.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            okButton.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); 
          }

//<UserCode_Begin_Bean_okButton>

//<UserCode_End_Bean_okButton>

          try
          {
            cancelButton.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Image Category","Image Name"});
//<UserCode_End_Bean_tableModel>
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+0,cancelButton.getPreferredSize().height+4));
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+18,okButton.getPreferredSize().height+4));
		deleteButton.setPreferredSize(new Dimension(deleteButton.getPreferredSize().width+0,deleteButton.getPreferredSize().height+4));
		enterButton.setPreferredSize(new Dimension(enterButton.getPreferredSize().width+0,enterButton.getPreferredSize().height+4));
		JPanel11.setPreferredSize(new Dimension(JPanel11.getPreferredSize().width+71,JPanel11.getPreferredSize().height+2));
		JLabel4.setPreferredSize(new Dimension(JLabel4.getPreferredSize().width+168,JLabel4.getPreferredSize().height+388));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+4));

  
          //<End_setUpProperties>
 TitledBorder tl=(TitledBorder)JPanel6.getBorder();
 tl.setTitle(resourceBundle.getString("Image Details"));
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("Image Category"),resourceBundle.getString("Image Name")});	
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
        this.setSize(getPreferredSize().width+557,getPreferredSize().height+572); 
          setTitle(resourceBundle.getString("ListIconAddModify"));
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
	table.setDefaultEditor(Object.class,null);
	table.setDefaultRenderer(Object.class,new CustomRenderer());
	JLabel4.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("listicon.png","images/runtimeadmin"));	
	okButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	cancelButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	enterButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	deleteButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	deleteButton.setEnabled(false);
	transparentCheck.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
        setTitle(resourceBundle.getString("Managed Object User Interface Settings"));
	setModal(true);
	((java.awt.Frame)getOwner()).setIconImage(uiUtils.getFrameIcon());
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      imageNameText_imageNameText_conn2 imageNameText_imageNameText_conn21 =  new imageNameText_imageNameText_conn2();
      imageNameText.addKeyListener(imageNameText_imageNameText_conn21);
      enterButton_enterButton_conn1 enterButton_enterButton_conn11 =  new enterButton_enterButton_conn1();
      enterButton.addActionListener(enterButton_enterButton_conn11);
      table_table_conn1 table_table_conn11 =  new table_table_conn1();
      table.addMouseListener(table_table_conn11);
      okButton_okButton_conn1 okButton_okButton_conn11 =  new okButton_okButton_conn1();
      okButton.addActionListener(okButton_okButton_conn11);
      transparentCheck_transparentCheck_conn1 transparentCheck_transparentCheck_conn11 =  new transparentCheck_transparentCheck_conn1();
      transparentCheck.addActionListener(transparentCheck_transparentCheck_conn11);
      JButton4_JButton4_conn1 JButton4_JButton4_conn11 =  new JButton4_JButton4_conn1();
      cancelButton.addActionListener(JButton4_JButton4_conn11);
      deleteButton_deleteButton_conn1 deleteButton_deleteButton_conn11 =  new deleteButton_deleteButton_conn1();
      deleteButton.addActionListener(deleteButton_deleteButton_conn11);
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
        JLabel2= new javax.swing.JLabel();
        JLabel3= new javax.swing.JLabel();
        typeText= new javax.swing.JTextField();
        menuFileText= new javax.swing.JTextField();
        JPanel3= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        JPanel6= new javax.swing.JPanel();
        JPanel10= new javax.swing.JPanel();
        JPanel11= new javax.swing.JPanel();
        JPanel13= new javax.swing.JPanel();
        enterButton= new javax.swing.JButton();
        deleteButton= new javax.swing.JButton();
        JPanel12= new javax.swing.JPanel();
        JLabel6= new javax.swing.JLabel();
        JLabel7= new javax.swing.JLabel();
        severityText= new javax.swing.JTextField();
        imageNameText= new javax.swing.JTextField();
        JButton1= new javax.swing.JButton();
        JPanel14= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        transparentCheck= new javax.swing.JCheckBox();
        JPanel4= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
	JButton1.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("browse.png","images/"));	
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
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(typeText,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(menuFileText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel3.add(JPanel5,cons);
JPanel5.setLayout(new BorderLayout(5,5));
JPanel5.add(JLabel4,BorderLayout.CENTER);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(15,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.NORTHWEST,cons.BOTH,inset,0,0);
JPanel6.add(JPanel10,cons);
JPanel10.setLayout(new BorderLayout(5,5));
JPanel10.add(JPanel11,BorderLayout.CENTER);
JPanel11.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel11.add(JPanel13,cons);
JPanel13.setLayout(new FlowLayout(2,5,5));
JPanel13.add(enterButton);
JPanel13.add(deleteButton);
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel11.add(JPanel12,cons);
JPanel12.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel12.add(JLabel6,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHEAST,cons.HORIZONTAL,inset,0,0);
JPanel12.add(JLabel7,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,0,1,0.1,0.1,cons.NORTHWEST,cons.BOTH,inset,0,0);
JPanel12.add(severityText,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.1,cons.NORTHEAST,cons.BOTH,inset,0,0);
JPanel12.add(imageNameText,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.0,0.1,cons.CENTER,cons.NONE,inset,0,0);
JPanel12.add(JButton1,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel11.add(JPanel14,cons);
JPanel14.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel14.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,5,0);
setConstraints(0,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6.add(transparentCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(1,5,5));
JPanel4.add(okButton);
JPanel4.add(cancelButton);

  
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

	public void populateUI(Hashtable hash)
	{
		modifyFlag = true;
		cloneHash = hash;
		typeText.setText(hash.get("TYPE").toString());
		typeText.setEditable(false);
		menuFileText.setText(hash.get("MENU").toString());
		Enumeration enumerate = hash.keys();
		while(enumerate.hasMoreElements())
		{
			String temp = enumerate.nextElement().toString();
			if(temp.equals("DEFAULT_TRANSPARENT_IMG"))
			{
				transparentFlag = true;
				transparentValue = hash.get(temp).toString();
				break;
			}
		}
		if(!transparentFlag)
		{
			Enumeration e = cloneHash.keys();
			while(e.hasMoreElements())
			{
				String temp = e.nextElement().toString();
				if(temp.equals("TYPE") || temp.equals("MENU"))
				{
					continue;
				}
				Vector v = new Vector();
				v.add(temp.substring(0,temp.indexOf("_")));
				v.add(cloneHash.get(temp));
				tableModel.addRow(v);
			}
			giveNewSelection(0);
			severityText.setEditable(false);
			
		}
		else
		{
			transparentCheck.setSelected(true);
			changeToTransparent();
			giveNewSelection(0);
			severityText.setEditable(false);
		}

	}
	
	public void fillTheTableWithSeverity()
	{
			for(int i=0;i < severityVector.size(); i++)
			{
				Vector v = new Vector();
				String temp = severityVector.elementAt(i).toString().toUpperCase();
				v.add(temp);
				v.add("");
				tableModel.addRow(v);
			}
	}


 
//<Begin__class_JButton4_JButton4_conn1>

 class JButton4_JButton4_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton4_JButton4_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  ListIconAddModify.this.setVisible(false);
  ListIconAddModify.this.dispose();
     }
//<UserCode_End_Connection_JButton4_JButton4_conn1>
 }//<End__class_JButton4_JButton4_conn1>

 

	
	public void tableMousePressedEvent()
	{
		int row = table.getSelectedRow();
		boolean b = checkIfDeleteNeeded();
		if(!b)
		{
			deleteButton.setEnabled(true);
		}
		else
		{
			deleteButton.setEnabled(false);
		}
		if(row != -1)
		{
			severityText.setText(table.getValueAt(row,0).toString());
			severityText.setEditable(false);
			imageNameText.setText(table.getValueAt(row,1).toString());
		}
		else
		{
			severityText.setText("");
			imageNameText.setText("");
		}
	}

 
//<Begin__class_table_table_conn1>

 class table_table_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_table_table_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  tableMousePressedEvent();
     }
//<UserCode_End_Connection_table_table_conn1>
 }//<End__class_table_table_conn1>


//<Begin__class_transparentCheck_transparentCheck_conn1>

 class transparentCheck_transparentCheck_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_transparentCheck_transparentCheck_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  checkBoxActionPerformed();
     }
//<UserCode_End_Connection_transparentCheck_transparentCheck_conn1>
 }//<End__class_transparentCheck_transparentCheck_conn1>
	
	public void checkBoxActionPerformed()
	{
		if(transparentCheck.isSelected())
		{
			clearTable();
			changeToTransparent();
			severityText.setEditable(false);
		}
		else
		{
			transparentValue = table.getValueAt(0,1).toString();
			clearTable();
			if(cloneHash == null || transparentFlag)
			{
				fillTheTableWithSeverity();
			}
			if(!transparentFlag && cloneHash != null)
			{
				Enumeration e = cloneHash.keys();
				while(e.hasMoreElements())
				{
					String temp = e.nextElement().toString();
					if(temp.equals("TYPE") || temp.equals("MENU"))
					{
						continue;
					}
					Vector v = new Vector();
					v.add(temp.substring(0,temp.indexOf("_")));
					v.add(cloneHash.get(temp));
					tableModel.addRow(v);
				}
			}
			giveNewSelection(0);
			severityText.setEditable(false);
			giveNewSelection(0);
		}
	}

	public void changeToTransparent()
	{
		clearTable();
		Vector v = new Vector();
		v.add("Transparent Image");
		v.add(transparentValue);
		tableModel.addRow(v);
		table.getSelectionModel().addSelectionInterval(0,0);
		severityText.setText(table.getValueAt(0,0).toString());
		severityText.setEditable(false);
		imageNameText.setText("");
	}
		
	public void addRequest(Vector severityVector,Hashtable mainHash)
	{
		rootHash = mainHash;
	/*	for(int i=0;i < severityVector.size();i++)
		{
			String temp = severityVector.elementAt(i).toString().toUpperCase();
			transparentText.setEditable(false);
			Vector v = new Vector();
			v.add(temp);
			v.add("");
			tableModel.addRow(v);
		} */
		fillTheTableWithSeverity();
		severityText.setEditable(false);
		giveNewSelection(0);
	}

 
//<Begin__class_imageNameText_imageNameText_conn2>

 class imageNameText_imageNameText_conn2 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_imageNameText_imageNameText_conn2>



     //Listener Interface Methods Are Added Below 


     public void keyPressed( java.awt.event.KeyEvent arg0)
     {
  // tablekeyPressedEvent(arg0);
     }



     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
  //tableKeyReleasedEvent(arg0);
     }
 
//<UserCode_End_Connection_imageNameText_imageNameText_conn2>
 }//<End__class_imageNameText_imageNameText_conn2>
	
/*	public void tablekeyPressedEvent(KeyEvent ev)
	{
		char c =ev.getKeyChar();
		int keyCode = ev.getKeyCode();
		System.out.println(keyCode);
		if(keyCode == KeyEvent.VK_ENTER)
		{
			enterButtonActionPerformed();
		}
	//}
		if(keyCode != -1)
		{
			int i = table.getSelectedRow();
			if(keyCode == 127)
			{ }
			
			else if(keyCode == 8)
			{ }
			else
			{
				if(keyCode != 10)
				{
				String val = table.getValueAt(i,1).toString();
				String newval = val+c;
				Vector v =new Vector();
				v.add(table.getValueAt(i,0));
				v.add(newval);
				tableModel.insertRow(i,v);
				tableModel.removeRow(i+1);
				}
			}
		}
	} */  


//<Begin__class_enterButton_enterButton_conn1>

 class enterButton_enterButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_enterButton_enterButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  enterButtonActionPerformed();
     }
//<UserCode_End_Connection_enterButton_enterButton_conn1>
 }//<End__class_enterButton_enterButton_conn1>
	
	public void enterButtonActionPerformed()
	{
		int i=table.getSelectedRow();
		if(i != -1)
		{
			Vector temp = new Vector();
			String icon = imageNameText.getText();
			tableModel.setValueAt(icon,i,1);
			imageNameText.setText("");
			if(i != table.getRowCount()-1)
			{
				giveNewSelection(i+1);
			}
			else
			{
				giveNewSelection(0);
			}
		}
		boolean b = checkIfDeleteNeeded();
		if(!b)
		{
			deleteButton.setEnabled(true);
		}
		else
		{
			deleteButton.setEnabled(false);
		}
		imageNameText.requestFocus();
	}

	public void giveNewSelection(int row)
	{
		ListSelectionModel listModel = table.getSelectionModel();
		listModel.clearSelection();
		listModel.addSelectionInterval(row,row);
		severityText.setText(table.getValueAt(row,0).toString());
		imageNameText.setText(table.getValueAt(row,1).toString());
	
	}


//<Begin__class_okButton_okButton_conn1>

 class okButton_okButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_okButton_okButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  okButtonActionPerformed();
     }
//<UserCode_End_Connection_okButton_okButton_conn1>
 }//<End__class_okButton_okButton_conn1>
	
	public void okButtonActionPerformed()
	{
		String type = typeText.getText();
		if(type.equals(""))
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please specify a type"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(rootHash != null)
		{
			Enumeration enumerate = rootHash.keys();
			while(enumerate.hasMoreElements())
			{
				String temp = enumerate.nextElement().toString();
				if(temp.equalsIgnoreCase(type))
				{
					JOptionPane.showMessageDialog(null,resourceBundle.getString("Type specified already exist."),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		}
		String menu = menuFileText.getText();
		Hashtable hash = null;
		if(transparentCheck.isSelected())
		{
			hash = new Hashtable();
			hash.put("TYPE",type);
			hash.put("MENU",menu);
			hash.put("DEFAULT_TRANSPARENT_IMG",table.getValueAt(0,1).toString());
		}
		else
		{
			hash = new Hashtable();
			hash.put("TYPE",type);
			hash.put("MENU",menu);
			for(int i=0;i < table.getRowCount();i++)
			{
				String temp = table.getValueAt(i,0).toString().toUpperCase()+"_IMG";
				hash.put(temp,table.getValueAt(i,1).toString());
			}
		}
		mainUI.updateEntries(type,menu,hash,modifyFlag);
		this.setVisible(false);
		this.dispose();
	}


	public void tableKeyReleasedEvent(KeyEvent ev)
	{
		int i = table.getSelectedRow();
		if(i != -1)
		{
			table.setValueAt(imageNameText.getText(),i,1);
		}
	/*	if(ev.getKeyCode() == KeyEvent.VK_DELETE || ev.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
				System.out.println("Delet Pressed");
				String val = imageNameText.getText();
				Vector v =new Vector();
				v.add(table.getValueAt(i,0).toString());
				v.add(val);
				System.out.println(val);
				tableModel.insertRow(i,v);
				tableModel.removeRow(i+1);
		} */
	}
	
	public void clearTable()
	{
		int count = tableModel.getRowCount();
			if(count !=0)
			{
				for(int j=count-1;j >=0;j--)
				{
					tableModel.removeRow(j);
				}
			}
	}
	
	public boolean checkIfDeleteNeeded()
	{
		int i=table.getSelectedRow();
		boolean b = false;
		if(i != -1)
		{
			String str = table.getValueAt(i,0).toString();
			for(int j=0;j<severityVector.size();j++)
			{
				if(str.equalsIgnoreCase(severityVector.elementAt(j).toString()))
				{
					b=true;
				}
			}
		}
		return b;
	}


//<Begin__class_deleteButton_deleteButton_conn1>

 class deleteButton_deleteButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_deleteButton_deleteButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  deleteButtonActionPerformed();
     }
//<UserCode_End_Connection_deleteButton_deleteButton_conn1>
 }//<End__class_deleteButton_deleteButton_conn1>
	
	public void deleteButtonActionPerformed()
	{
		int i = table.getSelectedRow();
		if(i != -1)
		{
			cloneHash.remove(table.getValueAt(i,0));
			tableModel.removeRow(i);
		}
	}



	
		public void browseButtonActionPerformed()
		{
			RuntimeConfigFrame.getCommonBuilderUIImpl().showImageBrowser(resourceBundle.getString("Remote File Chooser") , imageNameText);
		}

 
   
  


//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  browseButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}






















