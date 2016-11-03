
// $Id: Group.java,v 1.2.6.1 2012/01/25 05:12:46 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$3
//<End_Version>
package com.adventnet.security.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;
import java.util.*;


public class Group extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable JTable1 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JTextField grpText = null;
	javax.swing.JButton addGrpBtn = null;
	javax.swing.JPanel JPanel41 = null;
	javax.swing.JButton addGrpBtn1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JCheckBox JCheckBox1 = null;
	javax.swing.JCheckBox JCheckBox11 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.table.DefaultTableModel DefaultTableModel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

           com.adventnet.security.ui.TreeConfig treec = null;
           private Hashtable storeOperations = null;	  
           
           Dialog parent = null;

  
 	
   


 
 
    public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JPanel2.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>
            JPanel2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Assign groups for the user")));
//<UserCode_End_Bean_JPanel2>

          try
          {
            JScrollPane1.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane1>

//<UserCode_End_Bean_JScrollPane1>

          try
          {
            JTable1.setRowMargin(0);
            JTable1.setRowHeight(22);
            JTable1.setAutoResizeMode(4);
            JTable1.setIntercellSpacing(new Dimension(1,0));
            JTable1.setDoubleBuffered(true);
            JTable1.setSelectionForeground(new Color(-16777216));
            JTable1.setSelectionBackground(new Color(-1));
            JTable1.setGridColor(new Color(-16764109));
            JTable1.setShowVerticalLines(false);
            JTable1.setModel(DefaultTableModel1);
            JTable1.setShowHorizontalLines(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTable1,ex); 
          }

//<UserCode_Begin_Bean_JTable1>

//<UserCode_End_Bean_JTable1>

          try
          {
            JPanel5.setBorder(new javax.swing.border.EmptyBorder(5,5,5,5));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            JPanel4.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Enter the new group name")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            addGrpBtn.setText(resourceBundle.getString("Add Group"));
            addGrpBtn.setEnabled(true);
            addGrpBtn.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addGrpBtn,ex); 
          }

//<UserCode_Begin_Bean_addGrpBtn>
	  addGrpBtn.setEnabled(AuthMain.getPermission("Add Group"));//No Internationalisation
          grpText.setEnabled(AuthMain.getPermission("Add Group"));//No Internationalisation
//<UserCode_End_Bean_addGrpBtn>

          try
          {
            JPanel41.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Direct assignment of Permissions")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel41,ex); 
          }

//<UserCode_Begin_Bean_JPanel41>

//<UserCode_End_Bean_JPanel41>

          try
          {
            addGrpBtn1.setText(resourceBundle.getString("Permissions"));
            addGrpBtn1.setEnabled(true);
            addGrpBtn1.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addGrpBtn1,ex); 
          }

//<UserCode_Begin_Bean_addGrpBtn1>
	 addGrpBtn1.setEnabled(AuthMain.getPermission("Set User Permission"));//No Internationalisation
//<UserCode_End_Bean_addGrpBtn1>

          try
          {
            JLabel2.setHorizontalAlignment(2);
            JLabel2.setHorizontalTextPosition(4);
            JLabel2.setText(resourceBundle.getString("Click here to assign permissions directly."));
            JLabel2.setFont(new Font("Dialog",0,13));
            JLabel2.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JCheckBox1.setFont(new Font("SansSerif",0,12));
            JCheckBox1.setHorizontalTextPosition(4);
            JCheckBox1.setText(resourceBundle.getString("            Direct assignment."));
            JCheckBox1.setForeground(new Color(-16764109));
            JCheckBox1.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JCheckBox1,ex); 
          }

//<UserCode_Begin_Bean_JCheckBox1>
	  JCheckBox1.setEnabled(AuthMain.getPermission("Set User Permission"));//No Internationalisation
//<UserCode_End_Bean_JCheckBox1>

          try
          {
            JCheckBox11.setFont(new Font("SansSerif",0,12));
            JCheckBox11.setHorizontalTextPosition(4);
            JCheckBox11.setText(resourceBundle.getString("            Assign groups to the user."));
            JCheckBox11.setLabel(resourceBundle.getString("            Group based permissions."));
            JCheckBox11.setForeground(new Color(-16764109));
            JCheckBox11.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JCheckBox11,ex); 
          }

//<UserCode_Begin_Bean_JCheckBox11>
	 JCheckBox11.setEnabled(AuthMain.getPermission("Add Group"));//No Internationalisation
//<UserCode_End_Bean_JCheckBox11>

//<UserCode_Begin_Bean_DefaultTableModel1>



//<UserCode_End_Bean_DefaultTableModel1>
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+489,JScrollPane1.getPreferredSize().height+74));

  
          //<End_setUpProperties>
	
	JPanel41.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Direct assignment of Permissions")));
	JPanel4.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Enter the new group name")));
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
        setPreferredSize(new Dimension(getPreferredSize().width+630,getPreferredSize().height+400)); 
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
          showStatus(resourceBundle.getString("Error in init method"),ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>

 	DefaultTableModel1.setDataVector(  new Object[0][0] ,new Object[]{"",resourceBundle.getString("Group name(s)"),""} );	
		
	JViewport jv = new JViewport();	
	JLabel vBtn = new JLabel(resourceBundle.getString("Group(s) List"));
	vBtn.setBorder(new javax.swing.border.SoftBevelBorder( javax.swing.border.BevelBorder.RAISED));	
	JTable1.setRowMargin(0);	
	jv.setView(vBtn);//new JLabel("Group(s) List"));
	//JScrollPane1.setColumnHeader(jv);

	DefaultCellEditor te = (DefaultCellEditor)JTable1.getCellEditor(0,0);
	te.setClickCountToStart(50);
           JTable1.setCellEditor(te);
	JTable1.getTableHeader().setReorderingAllowed(false);		
		
		
  } 
	
String username = "";	
 public void setUserName(String username)
	{
		this.username = username;
	}	
   public void renderTable()
	{
		
	TableColumn col1 = JTable1.getColumnModel().getColumn(0);
	col1.setCellRenderer(new CheckBoxCellRenderer());
   	col1.setMaxWidth(40);	
	//col1.setHeaderRenderer(new ColumnCheckRenderer());
	//col1.setHeaderValue(new ListObject(""));	
	TableColumn col3 = JTable1.getColumnModel().getColumn(2);
	DefaultTableCellRenderer renderer3 = new DefaultTableCellRenderer();
	renderer3.setHorizontalAlignment(SwingConstants.CENTER);	
	renderer3.setIcon(AuthMain.getBuilderUiIfInstance().getImage("more.png"));
	renderer3.setFont(new Font("Dialog", Font.ITALIC , 9));	
	renderer3.setText(resourceBundle.getString("Operations"));	
	col3.setCellRenderer(renderer3);
  	col3.setMaxWidth(40);	
		
	}	
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JCheckBox11_addGrpBtn_conn1 JCheckBox11_addGrpBtn_conn11 =  new JCheckBox11_addGrpBtn_conn1();
      JCheckBox11.addActionListener(JCheckBox11_addGrpBtn_conn11);
      JPanel4_grpText_conn1 JPanel4_grpText_conn11 =  new JPanel4_grpText_conn1();
      JPanel4.addMouseListener(JPanel4_grpText_conn11);
      grpText_addGrpBtn_conn1 grpText_addGrpBtn_conn11 =  new grpText_addGrpBtn_conn1();
      grpText.addKeyListener(grpText_addGrpBtn_conn11);
      JTable1_JTable1_conn3 JTable1_JTable1_conn31 =  new JTable1_JTable1_conn3();
      JTable1.addKeyListener(JTable1_JTable1_conn31);
      JTable1_JTable1_conn2 JTable1_JTable1_conn21 =  new JTable1_JTable1_conn2();
      JTable1.addMouseMotionListener(JTable1_JTable1_conn21);
      addGrpBtn1_addGrpBtn1_conn1 addGrpBtn1_addGrpBtn1_conn11 =  new addGrpBtn1_addGrpBtn1_conn1();
      addGrpBtn1.addActionListener(addGrpBtn1_addGrpBtn1_conn11);
      JTable1_JTable1_conn1 JTable1_JTable1_conn11 =  new JTable1_JTable1_conn1();
      JTable1.addMouseListener(JTable1_JTable1_conn11);
      addGrpBtn_grpText_conn1 addGrpBtn_grpText_conn11 =  new addGrpBtn_grpText_conn1();
      addGrpBtn.addActionListener(addGrpBtn_grpText_conn11);
      JCheckBox1_addGrpBtn1_conn1 JCheckBox1_addGrpBtn1_conn11 =  new JCheckBox1_addGrpBtn1_conn1();
      JCheckBox1.addActionListener(JCheckBox1_addGrpBtn1_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new javax.swing.JTable();
        JPanel5= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        grpText= new javax.swing.JTextField();
        addGrpBtn= new javax.swing.JButton();
        JPanel41= new javax.swing.JPanel();
        addGrpBtn1= new javax.swing.JButton();
        JLabel2= new javax.swing.JLabel();
        JCheckBox1= new javax.swing.JCheckBox();
        JCheckBox11= new javax.swing.JCheckBox();
        JLabel1= new javax.swing.JLabel();
        DefaultTableModel1= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
		JLabel1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("addgroup.png"));
	
	
	
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.8,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,2,1,2,0.05,0.1,cons.NORTH,cons.BOTH,inset,0,0);
JPanel1.add(JPanel2,cons);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTable1);
inset = new Insets(0,0,0,0);
setConstraints(0,4,1,1,0.0,0.0,cons.SOUTH,cons.BOTH,inset,0,0);
JPanel1.add(JPanel5,cons);
JPanel5.setLayout(new GridLayout(2,2,5,5));
JPanel5.add(JPanel4);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.8,0.2,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(grpText,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.2,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(addGrpBtn,cons);
JPanel5.add(JPanel41);
JPanel41.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.2,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel41.add(addGrpBtn1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.8,0.2,cons.WEST,cons.HORIZONTAL,inset,0,0);
JPanel41.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTH,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JCheckBox1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTH,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JCheckBox11,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.2,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JLabel1,cons);

  
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
	Vector groupvec = null;
	public Vector getGroups()
	{
		groupvec= new Vector();
		for(int i=0;i < JTable1.getRowCount(); i++)
		    {
			 ListObject obj = (ListObject)JTable1.getValueAt(i,0);
	 		 if(obj.getState())
				{
				  groupvec.add(JTable1.getValueAt(i,1).toString());
				}
		    }
		if(groupvec.size()  == 0)
			{
				//groupvec.add("default "+username+" Group");
			}
		return groupvec;
	}
	
public void close()
	{
		if(treec != null)
		{
		  treec.dispose();
		}
	}	
	
Hashtable oper = new Hashtable();	
public Hashtable getAddOperations()
	{
		
		if(treec == null)
		{
			return null;
		}
		
 		return treec.getOperHash();
 	}		
	
public Hashtable getModOperations()
	{
		
		if(treec == null)
		{
			return null;
		}
 		return treec.getModOperations(oper);
	}			
	
public void setGroups(Vector allGroups,Vector userGroups)
	{
		
	Vector temp = new Vector();
	for(int j=0;j< allGroups.size();j++)	
		{
			ListObject lob = new ListObject(allGroups.elementAt(j));
			lob.setState(false);
			temp.add(lob);
		}

	Object[][]  values = new Object[allGroups.size()][3];
	for(int i=0;i< allGroups.size() ;i++)
		{
	   	        values[i] = new Object[]{new ListObject(""),temp.elementAt(i),null};
		}
 	DefaultTableModel1.setDataVector(values, new Object[]{" ",resourceBundle.getString("Group name(s)")," "});
	renderTable();	
	
	}
	
public ListObject getListObject(String str)
	{
		ListObject lob = new ListObject(str);
		lob.setState(true);
		return lob;
	}	
	
public void setBuilderUiIf(com.adventnet.nms.util.CommonBuilderUIInterface  uiImpl)
	{
		JLabel1.setIcon(uiImpl.getImage("addgroup.png"));
	}	

	
public void setUserGroups(Vector groups,Vector allGroups)
	{
		
	Vector temp = new Vector();

	groups.remove(new String(""));
		
	for(int i=0;i<groups.size();i++)
	         {
 	   	         allGroups.remove(groups.elementAt(i));
	         }
	
	for(int i=0;i<groups.size();i++)
		{
		          ListObject lob = new ListObject(groups.elementAt(i));
		          lob.setState(true);
		          temp.add(lob);
		}
	for(int j=0;j<allGroups.size();j++)
		{
		          ListObject lob = new ListObject(allGroups.elementAt(j));
		          lob.setState(false);
		          temp.add(lob);
		}

	Object[][] values = new Object[temp.size()][3];
	for(int k = 0;k<temp.size();k++)
		{
			ListObject lob = (ListObject)temp.elementAt(k);
				if(!lob.getState())
					{	
			values[k] = new Object[]{new ListObject("") ,temp.elementAt(k),null};
					}
				else
					{
			ListObject listo = new ListObject("");
			listo.setState(true);
			values[k] = new Object[]{listo,temp.elementAt(k),null};
					}
	
		}

 	DefaultTableModel1.setDataVector(values, new Object[]{" ",resourceBundle.getString("Group name(s)")," "});
	 renderTable();	
	}	
	
 com.adventnet.security.ui.AbstractSecurityModel model = null;	
 public void setSecurityModel(AbstractSecurityModel model)
	{
 this.model = AuthMain.model;		
	}
	
 public void disableAddPermissions(boolean bool)		
	{
		//JPanel6.setVisible(bool);
	}	


   


  public Group()
  {
    //<Begin_Group>
    this.init();
  
    //<End_Group>
  }

  public Group(java.applet.Applet applet)
  {
    //<Begin_Group_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_Group_java.applet.Applet>
  } 
	
class ColumnCheckRenderer extends JCheckBox implements TableCellRenderer
	{
    public ColumnCheckRenderer()
			{
				setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
				setHorizontalAlignment(SwingConstants.CENTER);
			}
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
			{
				ListObject val = (ListObject)value;
				if(val.getState())
				{
					setSelected(true);
				}
				else
				{
					setSelected(false);
				}
				return this;
			}
		
	}	

 
//<Begin__class_addGrpBtn_grpText_conn1>

 class addGrpBtn_grpText_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_addGrpBtn_grpText_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
   if(grpText.getText().trim().equals("")) 
   {
   grpText.setText("");     
   return;
   } 
   
   
   int count = JTable1.getRowCount();
   for(int i=0; i<count; i++)
   {
        //System.out.println(" New Group   "+grpText.getText().trim());
        //System.out.println(" group Name " + JTable1.getValueAt(i, 1));
        if(grpText.getText().trim().equals(JTable1.getValueAt(i, 1).toString()))
        {
             grpText.setText("");
             return;
        }
   }

 

  DefaultTableModel1.addRow(new Object[]{new ListObject(""),new ListObject(grpText.getText()),null}); 
   grpText.setText("");  
    }
//<UserCode_End_Connection_addGrpBtn_grpText_conn1>
 }//<End__class_addGrpBtn_grpText_conn1>



	

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

 GroupOperationsView gov = null;
  //<Begin__class_JTable1_JTable1_conn1>

 class JTable1_JTable1_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTable1_JTable1_conn1>



     //Listener Interface Methods Are Added Below 

int count = 2;
     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
   if(!JTable1.isEnabled()){

 return;
   }  
 
 if(gov != null) 
  {
   gov.dispose();
  } 
  
   if(JTable1.columnAtPoint(arg0.getPoint()) == 2)
     {
      gov = new GroupOperationsView(model.getTreeModel(), null,JTable1.getValueAt(JTable1.getSelectedRow(),1).toString(),(Window)JTable1.getTopLevelAncestor());
      gov.setBorderTitle(resourceBundle.getString("Permissions for  group :")+JTable1.getValueAt(JTable1.getSelectedRow(),1).toString()); 
      gov.setSize(300,350);
   
  Point p = JTable1.getLocationOnScreen();
  Dimension size = JTable1.getSize();
  
  gov.setLocation(  (int)(p.getX() + size.getWidth() ) - 300 , (int )p.getY() +(JTable1.getSelectedRow() * JTable1.getRowHeight()) );   
   
  Vector viewvec = AuthMain.model.getViewsForGroup(JTable1.getValueAt(JTable1.getSelectedRow(),1).toString()); 
  viewvec.addElement("default "+JTable1.getValueAt(JTable1.getSelectedRow(),1).toString()+" View");   
  Hashtable operhash = new Hashtable();
  Hashtable temphash = null;

  for(int i=0; i < viewvec.size(); i++)
  {
   temphash = AuthMain.model.getViewOperations( (String)viewvec.elementAt(i));
   for(Enumeration e = temphash.keys(); e.hasMoreElements();)
             {
     Object key =e.nextElement();
    Object value = temphash.get(key);
    if(operhash.containsKey(key) && (operhash.get(key).toString().equals("1")))
   {
         continue;
   }
    operhash.put( key, value);
  }  
   }

   gov.renderTree(operhash);   
   gov.setVisible(true);   
  } 
  
  
if(JTable1.columnAtPoint(arg0.getPoint()) == 0)  
  {
 ListObject lob = (ListObject)JTable1.getValueAt(JTable1.getSelectedRow(),0);
 if(lob.getState())  
         { 
           lob.setState(false);
         }  
 else
       {
         lob.setState(true);     
       }    
  
  } 
   JTable1.repaint(); 
      }



     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
       JTableHeader header = JTable1.getTableHeader(); 
      Rectangle rect = header.getHeaderRect(0); 
      if(rect.contains(arg0.getPoint()))
     {
   /*
    TableColumn col1 = JTable1.getColumnModel().getColumn(0);
    ListObject listob =(ListObject) col1.getHeaderValue();
      if(listob.getState())
    {
     listob.setState(false);
      for(int i=0;i<JTable1.getRowCount();i++)
      {
         ListObject lob1 = (ListObject)JTable1.getValueAt(i,0);
         lob1.setState(false);
      } 

    } 
   else
   {
    listob.setState(true);
    for(int i=0;i<JTable1.getRowCount();i++)
    {
      ListObject lob1 = (ListObject)JTable1.getValueAt(i,0);
      lob1.setState(true);
    } 
   } 
   */
  } 
  JTable1.repaint();
     }
 
     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
       
 setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

     }  
 
//<UserCode_End_Connection_JTable1_JTable1_conn1>
 }//<End__class_JTable1_JTable1_conn1>
	
class CheckBoxCellRenderer extends JCheckBox implements TableCellRenderer
	{
	     public CheckBoxCellRenderer()
		{
			setBackground(Color.white);
			setHorizontalAlignment(SwingConstants.CENTER);
		}
              
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
	{
		if(value instanceof ListObject)
			{
		ListObject lob = (ListObject)value;
		if(lob.getState())
		{
			setSelected(true);
		}	
		else
		{
			setSelected(false);			
		}	
			}

		return this;
	}	
 }
	


 


//<Begin__class_JPanel4_grpText_conn1>

 class JPanel4_grpText_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JPanel4_grpText_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
 if(gov != null) 
  {
   gov.dispose();
  } 
     }
//<UserCode_End_Connection_JPanel4_grpText_conn1>
 }//<End__class_JPanel4_grpText_conn1>


//<Begin__class_JTable1_JTable1_conn2>

 class JTable1_JTable1_conn2 extends java.awt.event.MouseMotionAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTable1_JTable1_conn2>



     //Listener Interface Methods Are Added Below 


     public void mouseMoved( java.awt.event.MouseEvent arg0)
     {

  
 if(JTable1.columnAtPoint(arg0.getPoint()) == 2 ) 
  {
   setCursor(new Cursor(Cursor.HAND_CURSOR));
  } 
 else
  {
   setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
  }  
   
     }
//<UserCode_End_Connection_JTable1_JTable1_conn2>
 }//<End__class_JTable1_JTable1_conn2>


//<Begin__class_grpText_addGrpBtn_conn1>

 class grpText_addGrpBtn_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_grpText_addGrpBtn_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyTyped( java.awt.event.KeyEvent arg0)
     {
      /*
  addGrpBtn.setEnabled( true);
if(grpText.getText().length() == 0)
  addGrpBtn.setEnabled( false);
  */
     }
//<UserCode_End_Connection_grpText_addGrpBtn_conn1>
 }//<End__class_grpText_addGrpBtn_conn1>


//<Begin__class_addGrpBtn1_addGrpBtn1_conn1>

 class addGrpBtn1_addGrpBtn1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_addGrpBtn1_addGrpBtn1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          invokeTreeForDirectAssignment();
     }
//<UserCode_End_Connection_addGrpBtn1_addGrpBtn1_conn1>
 }//<End__class_addGrpBtn1_addGrpBtn1_conn1>



private void invokeTreeForDirectAssignment()
{
     storeOperations = getAddOperations();
     //System.out.println("THE TREE CONFIG "+getAddOperations());    
     treec = new TreeConfig(parent, applet); 
     treec.init(); 
     
     Vector temp = getGroups();
     int size = temp.size();
     Vector groupview = new Vector();  
     for(int i =0;i<size;i++)
     {
          Vector myvec = AuthMain.model.getViewsForGroup(temp.elementAt(i).toString());  
          int siz = myvec.size();
          for(int j=0;j<siz;j++)
          {
               if(!groupview.contains(myvec.elementAt(j)))
                    groupview.addElement(myvec.elementAt(j));
          }
     }
     for(int j = 0;j<temp.size();j++)
     {
          groupview.add("default "+temp.elementAt(j)+" View");
     }
     groupview.add("default "+username+" View" );  
     treec.setOperationTree();
     //System.out.println(groupview);  
     treec.setName("","Users ");  
     treec.prepareForRendering(groupview);
     if(storeOperations != null)
     {
          treec.renderByHash(storeOperations);
     }
     treec.setVisible(true);   
}






 


//<Begin__class_JCheckBox11_addGrpBtn_conn1>

 class JCheckBox11_addGrpBtn_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox11_addGrpBtn_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         
         
  Object obj = arg0.getSource();
  if(obj == JCheckBox11)
  {
  
   boolean state = JCheckBox11.isSelected();
   // System.out.println("TRUE"+state);
           grpText.setEnabled( state);
           addGrpBtn.setEnabled( state);
 for(int i=0;i < JTable1.getRowCount(); i++)
      {
    ListObject obj1 = (ListObject)JTable1.getValueAt(i,0);
     obj1.setState(false);
  } 
    
   JTable1.setEnabled( state); 
  }  
     }
//<UserCode_End_Connection_JCheckBox11_addGrpBtn_conn1>
 }//<End__class_JCheckBox11_addGrpBtn_conn1>


//<Begin__class_JCheckBox1_addGrpBtn1_conn1>

 class JCheckBox1_addGrpBtn1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox1_addGrpBtn1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  Object obj = arg0.getSource();
  if(obj == JCheckBox1)
  {
  
   boolean state = JCheckBox1.isSelected();
    //System.out.println("TRUE"+state);
     //      grpText.setEnabled( state);
           addGrpBtn1.setEnabled( state);
	   if(state ==  false)
	   {
		   if(treec != null)
		   {
			   treec.resetChanges();
		   }
	   }
  }  
     }
//<UserCode_End_Connection_JCheckBox1_addGrpBtn1_conn1>
 }//<End__class_JCheckBox1_addGrpBtn1_conn1>


//<Begin__class_JTable1_JTable1_conn3>

 class JTable1_JTable1_conn3 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTable1_JTable1_conn3>



     //Listener Interface Methods Are Added Below 


     public void keyTyped( java.awt.event.KeyEvent arg0)
     {
 if(arg0.getKeyChar() != KeyEvent.VK_TAB)
 {
  arg0.consume();
 }
     }

     public void keyPressed( java.awt.event.KeyEvent arg0)
     {
 if(arg0.getKeyChar() != KeyEvent.VK_TAB)
 {
  arg0.consume();
 }
     }
 
     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
 if(arg0.getKeyChar() != KeyEvent.VK_TAB)
 {
  arg0.consume();
 }
     }
  
//<UserCode_End_Connection_JTable1_JTable1_conn3>
 }//<End__class_JTable1_JTable1_conn3>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
  
  public void setParent(Dialog parent)
  {
       this.parent = parent;
  }
}













































