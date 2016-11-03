
//$Id: ViewProperty.java,v 1.3.4.1 2012/01/25 05:12:46 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
	// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$2
//<End_Version>
package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.CommonBuilderUIInterface;
import java.util.*;
import com.adventnet.nms.util.*;
import java.text.*;

public class ViewProperty extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel5 = null;
	Component dateComp = null;
	javax.swing.JComboBox nameText = null;
	javax.swing.JTextField valueText = null;
	javax.swing.JComboBox searchBox = null;	
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton JButton5 = null;
	javax.swing.JButton JButton4 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable PropTable = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField JTextField1 = null;
	javax.swing.table.DefaultTableModel PropTableModel = null;
	javax.swing.DefaultListModel Scope = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private String myVec = "";
  	protected static boolean state = false;
	private String custView = "";
	private Vector authVec = new Vector();
  	private String goname = "";
  
  
   


  public ViewProperty()
  {
    //<Begin_ViewProperty>
    pack();
  
    //<End_ViewProperty>
  }

  public ViewProperty(java.applet.Applet applet)
  {
    //<Begin_ViewProperty_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ViewProperty_java.applet.Applet>
  }

  //This is for the change as the JDialog.
   public ViewProperty(Frame owner, java.applet.Applet applet)
  {
    super(owner);
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
   //System.out.println("IMAGE "+owner.getClass().getName());  
  }
  
 
    public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

//<UserCode_Begin_Bean_components>
this.setResizable(false);
//<UserCode_End_Bean_components>

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
            JLabel4.setText(resourceBundle.getString("Name"));
            JLabel4.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JLabel5.setText(resourceBundle.getString("Value"));
            JLabel5.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

	      try
	      {
		      searchBox.addItem(NmsClientUtil.GetString("javaui.cvscombo.isbefore"));
		      searchBox.addItem(NmsClientUtil.GetString("javaui.cvscombo.isafter"));
		      searchBox.addItem(NmsClientUtil.GetString("javaui.cvscombo.equals"));
		      searchBox.addItem(NmsClientUtil.GetString("javaui.cvscombo.notequals"));
	      }
	      catch(Exception ex)
	      {
		  
	      }
//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JPanel3.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JButton1.setText(resourceBundle.getString("Add"));
            JButton1.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>
JButton1.setMnemonic('A');
//<UserCode_End_Bean_JButton1>

          try
          {
            JButton2.setText(resourceBundle.getString("Edit"));
            JButton2.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>
JButton2.setMnemonic('E');
JButton2.setEnabled(false);
//<UserCode_End_Bean_JButton2>

          try
          {
            JButton3.setText(resourceBundle.getString("Delete"));
            JButton3.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>
JButton3.setMnemonic('D');
JButton3.setEnabled(false);
//<UserCode_End_Bean_JButton3>

          try
          {
            JButton5.setHorizontalTextPosition(4);
            JButton5.setText(resourceBundle.getString("  Ok  "));
            JButton5.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton5,ex); 
          }

//<UserCode_Begin_Bean_JButton5>
JButton5.setMnemonic('O');
//<UserCode_End_Bean_JButton5>

          try
          {
            JButton4.setHorizontalTextPosition(4);
            JButton4.setText(resourceBundle.getString("Cancel"));
            JButton4.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton4,ex); 
          }

//<UserCode_Begin_Bean_JButton4>
JButton4.setMnemonic('C');
//<UserCode_End_Bean_JButton4>

          try
          {
            JLabel2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Scope for the group")));
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            PropTable.setModel(PropTableModel);
            PropTable.setRowHeight(20);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+PropTable,ex); 
          }

//<UserCode_Begin_Bean_PropTable>

//<UserCode_End_Bean_PropTable>

          try
          {
            JLabel3.setText(resourceBundle.getString("<html><body><font face =\"sansSerif\"><font size =2> Name"));
            JLabel3.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JTextField1.setEditable(false);
            JTextField1.setToolTipText(resourceBundle.getString("null"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField1,ex); 
          }

//<UserCode_Begin_Bean_JTextField1>

//<UserCode_End_Bean_JTextField1>

//<UserCode_Begin_Bean_PropTableModel>
 
     String[] columnNames = {resourceBundle.getString("Property Name"),resourceBundle.getString("Property Value")}; 
PropTableModel.setDataVector(new Object[0][0],columnNames); 

//<UserCode_End_Bean_PropTableModel>
		JButton4.setPreferredSize(new Dimension(JButton4.getPreferredSize().width+3,JButton4.getPreferredSize().height+2));
		JButton5.setPreferredSize(new Dimension(JButton5.getPreferredSize().width+5,JButton5.getPreferredSize().height+2));
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+2,JButton3.getPreferredSize().height+1));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+15,JButton2.getPreferredSize().height+1));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+13,JButton1.getPreferredSize().height+1));

  
//<End_setUpProperties>
	javax.swing.border.TitledBorder titledBorder = (javax.swing.border.TitledBorder)(JLabel2.getBorder());
	if(titledBorder != null)
	{
		titledBorder.setTitle(resourceBundle.getString("Scope for the group"));
	}
	JLabel2.setText(resourceBundle.getString("<html><body><font face = \"sansSerif\"><font size = 3>&nbsp;&nbsp;*&nbsp; Match criteria to limit the data on<br>&nbsp;&nbsp;&nbsp;&nbsp; which this operation can be performed<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;by this group is specified here.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;For ex. to perform this operation only on <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;snmpNodes the Property is \"isSNMP\" and<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the value is \"true\".<br>&nbsp;&nbsp;*&nbsp; When no criteria is specified then the <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;group has unrestricted access to perform this  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;operation on any object."));
		//JScrollPane2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("All Scopes")));  
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
        this.setSize(getPreferredSize().width+595,getPreferredSize().height+490); 
          setTitle(resourceBundle.getString("ViewProperty"));
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
	JLabel1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("addview.png"));	
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double wid = screenSize.getWidth();
 	double hgt = screenSize.getHeight();
	setLocation((int)wid/3,( (int)hgt/2 - (int)hgt/4));	
	//setIconImage(AuthMain.getBuilderUiIfInstance().getFrameIcon());	
	setTitle(resourceBundle.getString("Scope Settings"));	
	PropTable.getCellEditor(0,0).getTableCellEditorComponent(PropTable,null,true, 0,0).setEnabled(false);
     DefaultCellEditor te = (DefaultCellEditor)PropTable.getCellEditor(0,0);
	te.setClickCountToStart(10);
     PropTable.setCellEditor(te);
	
	PropTable.getTableHeader().setReorderingAllowed(false);	
   	PropTable.setDefaultRenderer(PropTable.getColumnClass(0),new PropertyTableCellRenderer());
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JButton1_PropTable_conn JButton1_PropTable_conn1 =  new JButton1_PropTable_conn();
      JButton1.addActionListener(JButton1_PropTable_conn1);
      JButton5_JButton5_conn JButton5_JButton5_conn1 =  new JButton5_JButton5_conn();
      JButton5.addActionListener(JButton5_JButton5_conn1);
      PropTable_JButton2_conn PropTable_JButton2_conn1 =  new PropTable_JButton2_conn();
      PropTable.addMouseListener(PropTable_JButton2_conn1);
      JButton2_PropTable_conn JButton2_PropTable_conn1 =  new JButton2_PropTable_conn();
      JButton2.addActionListener(JButton2_PropTable_conn1);
      PropTable_JButton3_conn PropTable_JButton3_conn1 =  new PropTable_JButton3_conn();
      PropTable.addMouseListener(PropTable_JButton3_conn1);
      JButton4_JButton4_conn JButton4_JButton4_conn1 =  new JButton4_JButton4_conn();
      JButton4.addActionListener(JButton4_JButton4_conn1);
      JButton3_PropTable_conn JButton3_PropTable_conn1 =  new JButton3_PropTable_conn();
      JButton3.addActionListener(JButton3_PropTable_conn1);
      PropTable_PropTable_conn PropTable_PropTable_conn1 =  new PropTable_PropTable_conn();
      PropTable.addMouseListener(PropTable_PropTable_conn1);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
	    JPanel6= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        JLabel5= new javax.swing.JLabel();
        nameText= new javax.swing.JComboBox();
        valueText= new javax.swing.JTextField();
	    searchBox = new javax.swing.JComboBox();
	    dateComp = new DateTimeComponent();
        JPanel3= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        JPanel4= new javax.swing.JPanel();
        JButton5= new javax.swing.JButton();
        JButton4= new javax.swing.JButton();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
        JScrollPane1= new javax.swing.JScrollPane();
        PropTable= new javax.swing.JTable();
        JLabel3= new javax.swing.JLabel();
        JTextField1= new javax.swing.JTextField();
        PropTableModel= new javax.swing.table.DefaultTableModel();
        Scope= new javax.swing.DefaultListModel();

		nameText.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String propName = ((String)nameText.getSelectedItem()).trim();
				if(propName.equals("time") || propName.equals("statusChangeTime") || propName.equals("statusUpdateTime") || propName.equals("createTime") || propName.equals("modTime") || propName.equals("lastTimeValue") || propName.equals("timeVal"))
				{
					valueText.setVisible(false);
					dateComp.setVisible(true);
					searchBox.setVisible(true);
				}
				else
				{
					valueText.setVisible(true);
					dateComp.setVisible(false);
					searchBox.setVisible(false);
				}
			}
		});
  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(1,3,2,1,0.0,1.0E-4,cons.SOUTHEAST,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new BorderLayout());
//JPanel2.setLayout(new GridLayout(3,1,5,5));
JPanel2.add(JPanel5,BorderLayout.NORTH);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(0,0,10,0);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JLabel4.setMinimumSize(new Dimension(20,30));
JPanel5.add(JLabel4,cons);
inset = new Insets(0,10,0,0);
setConstraints(2,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
nameText.setMinimumSize(new Dimension(200,30));
JPanel5.add(nameText,cons);
inset = new Insets(5,0,15,0);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JLabel5.setMinimumSize(new Dimension(20,30));
JPanel5.add(JLabel5,cons);
inset = new Insets(5,10,0,0);
setConstraints(2,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JPanel6,cons);
GridBagLayout gb = new GridBagLayout();
JPanel6.setLayout(gb);
inset = new Insets(0,0,0,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
searchBox.setMinimumSize(new Dimension(50,30));
JPanel6.add(searchBox,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.2,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
((DateTimeComponent)dateComp).setMinimumSize(new Dimension(170,30));
JPanel6.add((DateTimeComponent)dateComp,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.2,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
valueText.setMinimumSize(new Dimension(200,30));
JPanel6.add(valueText,cons);

JPanel2.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,210,0,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JButton1,cons);
inset = new Insets(0,0,0,5);
setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JButton2,cons);
inset = new Insets(0,0,0,0);
setConstraints(2,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JButton3,cons);

JPanel2.add(JPanel4,BorderLayout.SOUTH);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(0,250,0,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(JButton5,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(JButton4,cons);

inset = new Insets(0,0,0,0);
setConstraints(0,0,1,4,0.0,0.01,cons.WEST,cons.BOTH,inset,0,0);
Top.add(JLabel1,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,2,1,0.0010,0.0,cons.NORTHEAST,cons.BOTH,inset,0,0);
Top.add(JLabel2,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,2,2,1,0.0,0.0050,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(PropTable);
inset = new Insets(0,5,0,5);
setConstraints(1,1,1,1,3.0E-5,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(JLabel3,cons);
inset = new Insets(0,0,0,0);
setConstraints(2,1,1,1,0.0010,0.0,cons.NORTHEAST,cons.HORIZONTAL,inset,0,0);
Top.add(JTextField1,cons);

  
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "SecurityAdministrationResources"; 
            if (input.equals("MS_MODE")) value = "3"; 
            }
        return value;

  
           //<End_getParameter_String>
  } 

  public JComboBox getNameComboBox()
  {
       return nameText;
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

public void setUnsetIcon(boolean set)
	{
	  if(set)
		{
			JLabel1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("addview.png"));		
		}	
	  else
		{
			JLabel1.setIcon(null);
		}	
	}
	
public void setProperties(String forTheView, java.util.Properties prop)
	{
  
	String vname = forTheView;	
	
//JLabel2.setText("Add Properties for view : "+vname);
     clear();
	if(prop == null)	
		{
			return;
		}	
    java.util.Properties viewProp = prop;
    java.util.Enumeration viewPropNames = viewProp.propertyNames();
  
    int row = 0;  
    while(viewPropNames.hasMoreElements())
     {
    	 	String str = viewPropNames.nextElement().toString();
	 	PropTableModel.addRow(new Object[] {"",""} );
      	PropTable.setValueAt(str,row,0);   
        if(str.equals("time") || str.equals("statusChangeTime") || str.equals("statusUpdateTime") || str.equals("createTime") || str.equals("modTime") || str.equals("lastTimeValue") || str.equals("timeVal"))
		{
			String timeValue = addTimeValue(viewProp.getProperty(str));
			PropTable.setValueAt(timeValue,row,1);
		}
		else
		{	
      		PropTable.setValueAt(viewProp.getProperty(str),row,1);        
		}
	 	row++;    
    }  	
	 
	
	}

public void clear()
	{
		if(PropTable.getRowCount()>0)	
			for(int i=PropTable.getRowCount()-1;i>=0;i--)
				{
					PropTableModel.removeRow(i);
				}
	}

public void setViewName(String vname)
	{
//		name = vname;	  
//JLabel2.setText(resourceBundle.getString("Add Properties for view : ") + vname);
	}
	public java.util.Properties getProperties()
	{
		java.util.Properties prop = new java.util.Properties();
		for(int i=0;i<PropTable.getRowCount();i++)
		{
			String nameText = PropTable.getValueAt(i,0).toString();
			if(PropTable.getValueAt(i,0).toString().equals("severity"))
			{
				try
				{
					int k = Integer.parseInt(PropTable.getValueAt(i,1).toString());
					prop.setProperty(PropTable.getValueAt(i,0).toString(),PropTable.getValueAt(i,1).toString());
				}
				catch(NumberFormatException nfe)
				{
					prop.setProperty("stringseverity",PropTable.getValueAt(i,1).toString());
					//prop.setProperty(PropTable.getValueAt(i,0).toString(),PropTable.getValueAt(i,1).toString());
				}
			}
			else if(PropTable.getValueAt(i,0).toString().equals("status"))
			{
				try
				{
						int k = Integer.parseInt(PropTable.getValueAt(i,1).toString());
						prop.setProperty(PropTable.getValueAt(i,0).toString(),PropTable.getValueAt(i,1).toString());
				}
				catch(NumberFormatException nfe)
				{
						prop.setProperty("stringstatus",PropTable.getValueAt(i,1).toString());
				}
			}
			else if(nameText.equals("time") || nameText.equals("statusChangeTime") || nameText.equals("statusUpdateTime") || nameText.equals("createTime") || nameText.equals("modTime") || nameText.equals("lastTimeValue") || nameText.equals("timeVal"))
			{
				String value = PropTable.getValueAt(i,1).toString();
				String formDateString = formTheDate(value);
				prop.setProperty(PropTable.getValueAt(i,0).toString(),formDateString);
			}
			else 
			{
				prop.setProperty(PropTable.getValueAt(i,0).toString(),PropTable.getValueAt(i,1).toString());
			}
		}	
		return prop;
		
	}			


 


//This is for the AddAuthorizedScope from the CustomView.
	public void enableText()
	{
		JTextField1.setEditable(true);
	}
 
	//This is to get the custView and authScope
	public void setAuthViews(String custScope,Vector views)
	{
		custView = custScope;
		authVec = views;
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



//private String goname = "";
//public void setViews(String views,String gpname){
	//System.out.println("DWARAA "+views+"              "+gpname);	
//		myVec = views;
//		goname=gpname;
 //JTextField1.setText(gpname+" "+views+" Scope");
 //JTextField1.setToolTipText(gpname+" "+views+" Scope");		
//}


	private String groupName ="";

	public void setViews(String views,String gpname,String groupname)
	{
	
		if(groupname.equals(""))
		{
		myVec = views;
		goname=gpname;
		}
		groupName=groupname;
		if(!state)
  		{		
 			JTextField1.setText(gpname+" "+views+" Scope");
 			JTextField1.setToolTipText(gpname+" "+views+" Scope");		
  		}
 		else if(state)
		{
			JTextField1.setText(gpname);
 			JTextField1.setToolTipText(gpname);		
		}			
	}







 


//<Begin__class_JButton1_PropTable_conn>

 class JButton1_PropTable_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_PropTable_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         String propName = nameText.getSelectedItem().toString();
	 	String dateText = ((DateTimeComponent)dateComp).getDateTime();
	 	String searchValue = searchBox.getSelectedItem().toString();
         if ((propName.equals("")  || valueText.getText().trim().equals("")) && (propName.equals("")  || dateText.trim().equals("")))
         {
             return;
         } 

         for(int i =0 ;i<PropTable.getRowCount();i++)
         {
             if (propName.equals(PropTable.getValueAt(i,0).toString()))
             {
                 JOptionPane.showOptionDialog (null, 
                         resourceBundle.getString("Property name already exists"), 
                         resourceBundle.getString("Error Message"), 
                         JOptionPane.DEFAULT_OPTION, 
                         JOptionPane.WARNING_MESSAGE, 
                         null, new Object[]{resourceBundle.getString("OK")},resourceBundle.getString("OK")); 
                 return;
             } 
         }    

         PropTableModel.addRow(new Object[] {"",""} );
         int row = PropTable.getRowCount() -1;  
         PropTable.setValueAt(propName,row,0); 
	 	 if(propName.equals("time") || propName.equals("statusChangeTime") || propName.equals("statusUpdateTime") || propName.equals("createTime") || propName.equals("modTime") || propName.equals("lastTimeValue") || propName.equals("timeVal"))
	 	 { 
	 		if(dateText.length() > 0)
	 		{
		 		try
				{
		 			String timeString = formDateString(searchValue,dateText);
		 			PropTable.setValueAt(timeString,row,1);
		 		}
		 		catch(Exception e)
		 		{
			 		e.printStackTrace();
		 		}
	 		}
		}	 
	 	else
	 	{
         	PropTable.setValueAt(valueText.getText().trim(),row,1); 
	 	}

         valueText.setText("");
	 	((DateTimeComponent)dateComp).clearAll();
     }
     
//<UserCode_End_Connection_JButton1_PropTable_conn>
 }//<End__class_JButton1_PropTable_conn>


//<Begin__class_JButton2_PropTable_conn>

 class JButton2_PropTable_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_PropTable_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         if(PropTable.getSelectedRowCount() !=1)
         {
             return;
         } 
         String propName = nameText.getSelectedItem().toString();
		String dateText = ((DateTimeComponent)dateComp).getDateTime();
		String searchValue = searchBox.getSelectedItem().toString();
        if((propName.equals("") || valueText.getText().equals("") ) && (propName.equals("")  || dateText.trim().equals("")))
         {
             return;
         }

         for(int i =0 ;i<PropTable.getRowCount();i++)
         {
             if(propName.equals(PropTable.getValueAt(i,0).toString())  && (!PropTable.isRowSelected(i)))
             {
                 JOptionPane.showOptionDialog (null, 
                         resourceBundle.getString("Property name already exists"), 
                         resourceBundle.getString("Error Message"), 
                         JOptionPane.DEFAULT_OPTION, 
                         JOptionPane.WARNING_MESSAGE, 
                         null, new Object[]{resourceBundle.getString("OK")},resourceBundle.getString("OK")); 
                 return;
             } 
         }    

         PropTable.setValueAt(propName, PropTable.getSelectedRow(), 0 );
	 	 if(propName.equals("time") || propName.equals("statusChangeTime") || propName.equals("statusUpdateTime") || propName.equals("createTime") || propName.equals("modTime") || propName.equals("lastTimeValue") || propName.equals("timeVal"))
	 	 {
		 	if(dateText.length() > 0)
		 	{
				try
				{
					 String timeString = formDateString(searchValue,dateText);
					 PropTable.setValueAt(timeString,PropTable.getSelectedRow(),1);
			 	}
			 	catch(Exception e)
			 	{
					 e.printStackTrace();
			 	}
		 	}
	 	}
	 	else
	 	{
         	PropTable.setValueAt(valueText.getText(), PropTable.getSelectedRow(), 1 );  
	 	}
         
		valueText.setText("");     
	 	((DateTimeComponent)dateComp).clearAll();
     }
//<UserCode_End_Connection_JButton2_PropTable_conn>
 }//<End__class_JButton2_PropTable_conn>


//<Begin__class_JButton3_PropTable_conn>

 class JButton3_PropTable_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_PropTable_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         if(PropTable.getSelectedRowCount() ==0)
         {
             return;
         }

         PropTableModel.removeRow(PropTable.getSelectedRow()); 

         valueText.setText("");  
	 	((DateTimeComponent)dateComp).clearAll(); 

         if (PropTable.getSelectedRowCount() == 0)
         {
             JButton2.setEnabled(false);
             JButton3.setEnabled(false);
         }

     }
//<UserCode_End_Connection_JButton3_PropTable_conn>
 }//<End__class_JButton3_PropTable_conn>


//<Begin__class_PropTable_PropTable_conn>

 class PropTable_PropTable_conn extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_PropTable_PropTable_conn>



     //Listener Interface Methods Are Added Below 
     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
         if(PropTable.getSelectedRowCount()  != 1)
         {
             return;
         }
		try{ 
	     nameText.setSelectedItem(PropTable.getValueAt(PropTable.getSelectedRow(),0).toString()); 
	     String namecomboVal = (String)nameText.getSelectedItem();
	     if(namecomboVal.equals("time") || namecomboVal.equals("statusChangeTime") || namecomboVal.equals("statusUpdateTime") || namecomboVal.equals("createTime") || namecomboVal.equals("modTime") || namecomboVal.equals("lastTimeValue") || namecomboVal.equals("timeVal")) 
	     {
		        dateComp.setVisible(true);
			searchBox.setVisible(true);
			valueText.setVisible(false);
		        String dateVal = PropTable.getValueAt(PropTable.getSelectedRow(),1).toString();
			if(dateVal.startsWith("!"))
			{
				searchBox.setSelectedItem(NmsClientUtil.GetString("javaui.cvscombo.notequals"));
				dateVal = dateVal.substring(2).trim();
			}
			else if(dateVal.startsWith(">"))
			{
				searchBox.setSelectedItem(NmsClientUtil.GetString("javaui.cvscombo.isafter"));
				dateVal = dateVal.substring(2).trim();
			}
			else if(dateVal.startsWith("<"))
			{
				searchBox.setSelectedItem(NmsClientUtil.GetString("javaui.cvscombo.isbefore"));
				dateVal = dateVal.substring(2).trim();
			}
			else
			{
				searchBox.setSelectedItem(NmsClientUtil.GetString("javaui.cvscombo.equals"));
				dateVal = dateVal.trim();
			}
			DateFormat nmsFormat = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");//No I18N
			Date dat = nmsFormat.parse(dateVal);
			String dateStr = String.valueOf(dat.getTime());
			
			Long lo = Long.valueOf(dateStr);
			Date dt = new Date(lo.longValue());
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) +1;
			int date = cal.get(Calendar.DATE);
			int hour = cal.get(Calendar.HOUR);
			int minute = cal.get(Calendar.MINUTE);
			int sec = cal.get(Calendar.SECOND);
			int am_pm = cal.get(Calendar.AM_PM);
			((DateTimeComponent)dateComp).setYear(year);
			((DateTimeComponent)dateComp).setMonth(month);
			((DateTimeComponent)dateComp).setDay(date);
			((DateTimeComponent)dateComp).setHour(hour);
			((DateTimeComponent)dateComp).setMinute(minute);
			((DateTimeComponent)dateComp).setSecond(sec);
			((DateTimeComponent)dateComp).setM(am_pm);

	     }
	     else
	     {
		     dateComp.setVisible(false);
		     searchBox.setVisible(false);
		     valueText.setVisible(true);
         	     valueText.setText(PropTable.getValueAt(PropTable.getSelectedRow(),1).toString());   
	     }
	}catch(Exception e)
	{
	}
     }
//<UserCode_End_Connection_PropTable_PropTable_conn>
 }//<End__class_PropTable_PropTable_conn>




 





//<Begin__class_JButton5_JButton5_conn>

 class JButton5_JButton5_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton5_JButton5_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {

  //int size = viewVec.size();
  //for(int i=0;i<size;i++)
  //AuthMain.model.addViewProp(goname,myVec,getProperties());
  //dispose();
  
if(!state)
   {  
  if(JTextField1.getText().trim().equals(""))
  {
           Utilities.errorMessage(resourceBundle.getString("Please enter the name of the scope"));        
           return;                
           }
  //else if( PropTable.getRowCount() ==0)
  //{
  //    Utilities.errorMessage(resourceBundle.getString("Please enter the properties for the scope"))        ;
  //    return;                
  //}       
          AuthMain.main.disableButtons();   
      AuthMain.model.addViewProp(goname,myVec,getProperties());
   }
   else if(state)
   {
    
 Vector allViewsVec = AuthMain.model.getAllAuthScopes();  
      String tempViewName ="";
if(goname.equals(""))
    {
       goname = JTextField1.getText();
       tempViewName=goname;
    }
  if(JTextField1.getText().trim().equals(""))
  {
           Utilities.errorMessage(resourceBundle.getString("Please enter the name of the scope"));        
           return;                
           }
  //else if( PropTable.getRowCount() ==0)
  //{
  //    Utilities.errorMessage(resourceBundle.getString("Please enter the properties for the scope"))        ;
  //    return;                
  //} 
   
 if(allViewsVec.contains(tempViewName))
 {
 goname="";
  Utilities.errorMessage(resourceBundle.getString("Scope name already present"));
 JTextField1.requestFocus();   
 JTextField1.selectAll();
  return;                
   } 
    AuthMain.main.disableButtons();  
   if(getProperties().size() == 0)
   {
          Utilities.errorMessage(resourceBundle.getString("Error while adding custom view."));
          System.out.println("Exception while creating Custom Views without criteria ");
          return;
    }	       
    AuthMain.model.addAuthViewProp(goname,getProperties(),groupName); 
   AuthMain.main.disableButtons();
   authVec.addElement(goname);  
    AuthMain.model.assignAuthScope(custView,authVec,groupName); 
 }
   dispose();
  
     }
//<UserCode_End_Connection_JButton5_JButton5_conn>
 }//<End__class_JButton5_JButton5_conn>
//<Begin__class_JButton4_JButton4_conn>

 class JButton4_JButton4_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton4_JButton4_conn>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  dispose();
     }
//<UserCode_End_Connection_JButton4_JButton4_conn>
 }//<End__class_JButton4_JButton4_conn>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }


//<Begin__class_PropTable_JButton2_conn>

 class PropTable_JButton2_conn extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_PropTable_JButton2_conn>

     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
          if (PropTable.getSelectedRowCount() != 0)
          {
               JButton2.setEnabled(true);
          }
     }
//<UserCode_End_Connection_PropTable_JButton2_conn>
 }//<End__class_PropTable_JButton2_conn>
//<Begin__class_PropTable_JButton3_conn>

 class PropTable_JButton3_conn extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_PropTable_JButton3_conn>

     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
          if (PropTable.getSelectedRowCount() != 0)
          {
               JButton3.setEnabled(true);
          }
     }
//<UserCode_End_Connection_PropTable_JButton3_conn>
 }//<End__class_PropTable_JButton3_conn>

 private String formTheDate(String value)
 {
	 String time1 = "";
	 String time2 = "";
	 if(value.startsWith("!") || value.startsWith("<") || value.startsWith(">"))
	 {
	 	time2 = value.substring(2).trim();
	 }
	 else
	 {
		time2 = value.trim();
	 }
	 String timeString = "";//No I18N
	 try{
	 if(time2.length() >0)
	 {
		 DateFormat nmsFormat = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");//No I18N
		 Date dt = nmsFormat.parse(time2);
		 time2 = String.valueOf(dt.getTime());
	 }
	 }catch(Exception e)
	 {
	 }
	 if(value.startsWith("!"))//No I18N
	 {
		 timeString = "!"+time2;//No Internationalisation
	 }
	 else if(value.startsWith("<"))
	 {
		 timeString ="<between> "+ time1 + " and "+time2;//No Internationalisation
	 }
	 else if(value.startsWith(">"))
	 {
		 timeString ="<between> "+ time2 + " and "+time1;//No Internationalisation
	 }
	 else
	 {
		 long startTime=Long.parseLong(time2);
		 timeString ="<between> "+startTime+" and "+(startTime+999);//To search for events between the current time and the time + 1 second but excluding the time+1th second.ie., 10:10:10:000 to 10:10:10:999 //No I18N
	 }
	 return timeString;
 }

 private String formDateString(String searchStr,String date)
 {
	 String dateString = "";//No I18N
	 if(searchStr.equals(NmsClientUtil.GetString("javaui.cvscombo.equals")))
	 {
		dateString = date;
	 }
	 else if(searchStr.equals(NmsClientUtil.GetString("javaui.cvscombo.notequals")))
	 {
		dateString = "!=   "+date;//No I18N
	 }
	 else if(searchStr.equals(NmsClientUtil.GetString("javaui.cvscombo.isbefore")))
	 {
		 dateString = "<   "+date;//No I18N
	 }
	 else if(searchStr.equals(NmsClientUtil.GetString("javaui.cvscombo.isafter")))
	 {
		 dateString = ">   "+date;//No I18N
	 }
	 return dateString;
 }

 private String addTimeValue(String val)
 {
	 String timeString = "";//No I18N
	 Vector vec = new Vector();
	 DateFormat nmsFormat = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");//No I18N
	 if(val.startsWith("!"))//No I18N
	 {
		 val = val.substring(2).trim();
		 Long l = Long.valueOf(val);
		 Date dt = new Date(l);
		 timeString = "!=   "+nmsFormat.format(dt);//No I18N
	 }
	 else
	 {
		 StringTokenizer strToken = new StringTokenizer(val," ");//No I18N
		 while(strToken.hasMoreElements())
		 {
			 String st = strToken.nextToken();
			 vec.addElement(st);
		 }
		 String firstElement = (String)vec.get(0);
		 String secondElement = (String)vec.get(1);
		 if(secondElement.equals("and")) //No I18N
		 {
			 String time = (String)vec.get(2);
			 Long l = Long.valueOf(time);
			 Date dt = new Date(l);
			 timeString = "<   "+nmsFormat.format(dt);//No I18N
		 }
		 else if(vec.size() ==4)
		 {
			 Long l = Long.valueOf(secondElement);
			 Date dt = new Date(l);
			 timeString = nmsFormat.format(dt);
		 }
		 else
		 {
			 Long l = Long.valueOf(secondElement);
			 Date dt = new Date(l);
			 timeString = ">   "+nmsFormat.format(dt);//No I18N
		 }
	 }
	 return timeString;
 }
}































