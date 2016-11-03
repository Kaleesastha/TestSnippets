
//$Id: GroupViewAscn.java,v 1.1 2006/08/29 13:57:02 build Exp $
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

public class GroupViewAscn extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JList JList1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel10 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField groupText = null;
	javax.swing.JPanel JPanel9 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JComboBox groupCombo = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.DefaultListModel ViewListModel = null;
	com.adventnet.security.ui.OperationsListCellRenderer OperationsListCellRenderer1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


  
	private boolean addGroup = true;  
  










   


  public GroupViewAscn()
  {
    //<Begin_GroupViewAscn>
    pack();
  
    //<End_GroupViewAscn>
  }

  public GroupViewAscn(java.applet.Applet applet)
  {
    //<Begin_GroupViewAscn_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_GroupViewAscn_java.applet.Applet>
  }
  public GroupViewAscn(Frame owner, java.applet.Applet applet)
  {
    super(owner);
    this.applet = applet;
    pack();
    this.setTitle("GroupViewAscn");
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

//<UserCode_Begin_Bean_Top>
setResizable(false);
//<UserCode_End_Bean_Top>

          try
          {
            JPanel3.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JList1.setModel(ViewListModel);
            JList1.setCellRenderer(OperationsListCellRenderer1);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JList1,ex); 
          }

//<UserCode_Begin_Bean_JList1>

//<UserCode_End_Bean_JList1>

          try
          {
            JLabel1.setText(resourceBundle.getString("Enter group name"));
            JLabel1.setFont(new Font("Dialog",0,12));
            JLabel1.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setIconTextGap(8);
            JLabel2.setText(resourceBundle.getString("Edit a Group"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            groupCombo.setBackground(new Color(-1));
            groupCombo.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+groupCombo,ex); 
          }

//<UserCode_Begin_Bean_groupCombo>

//<UserCode_End_Bean_groupCombo>

          try
          {
            JLabel3.setFont(new Font("Dialog",0,12));
            JLabel3.setForeground(new Color(-16764109));
            JLabel3.setText(resourceBundle.getString("Use the following list to add a new group, assign views for it,"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel4.setText(resourceBundle.getString("or assign different views for an existing group"));
            JLabel4.setFont(new Font("Dialog",0,12));
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
            JButton1.setText(resourceBundle.getString("Apply"));
            JButton1.setFont(new Font("Dialog",0,13));
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
            JButton2.setText(resourceBundle.getString("Close"));
            JButton2.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            OperationsListCellRenderer1.setText(resourceBundle.getString("OperationsListCellRenderer1"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+OperationsListCellRenderer1,ex); 
          }

//<UserCode_Begin_Bean_OperationsListCellRenderer1>

//<UserCode_End_Bean_OperationsListCellRenderer1>
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+198,JPanel1.getPreferredSize().height+18));
		JPanel6.setPreferredSize(new Dimension(JPanel6.getPreferredSize().width+22,JPanel6.getPreferredSize().height+38));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+12,JPanel4.getPreferredSize().height+43));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+10,JPanel2.getPreferredSize().height+10));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+361,JPanel3.getPreferredSize().height+10));

  
//<End_setUpProperties>
		JPanel4.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Group Name")));
		JPanel6.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Group View Association")));
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
        this.setSize(getPreferredSize().width+438,getPreferredSize().height+439); 
          setTitle(resourceBundle.getString("GroupViewAscn"));
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
		
	JViewport vp = new JViewport();
	JLabel lab = new JLabel(resourceBundle.getString("Views list"));
	lab.setHorizontalAlignment((int)JLabel.CENTER_ALIGNMENT);	
	vp.setView(lab);	
	JScrollPane1.setColumnHeader(vp);	
	JLabel1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("group1.png"));	
	JLabel2.setIcon(AuthMain.getBuilderUiIfInstance().getImage("group1.png"));	
	AuthMain.getBuilderUiIfInstance().centerWindow(this);
	displayData();	
	
	//groupCombo.fireItemStateChanged((ItemEvent)new EventObject(this));	
	groupCombo.updateUI();	
		
			addWindowListener(new WindowAdapter()
						{
							public void windowClosing(WindowEvent we)
								{
									 ViewGroup.getInstance().enableButtons();
									 dispose();  
								}
						}
					   );	
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JList1_JPanel3_conn1 JList1_JPanel3_conn11 =  new JList1_JPanel3_conn1();
      JList1.addMouseListener(JList1_JPanel3_conn11);
      groupCombo_groupCombo_conn1 groupCombo_groupCombo_conn11 =  new groupCombo_groupCombo_conn1();
      groupCombo.addItemListener(groupCombo_groupCombo_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JList1= new javax.swing.JList();
        JPanel2= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JPanel10= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        groupText= new javax.swing.JTextField();
        JPanel9= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        groupCombo= new javax.swing.JComboBox();
        JPanel6= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        JPanel1= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        ViewListModel= new javax.swing.DefaultListModel();
        OperationsListCellRenderer1= new com.adventnet.security.ui.OperationsListCellRenderer();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(20,15));
Top.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JList1);
Top.add(JPanel2,BorderLayout.NORTH);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JPanel4,BorderLayout.CENTER);
JPanel4.setLayout(new CardLayout(5,5));
JPanel4.add(JPanel10,"JPanel10");
JPanel10.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel10.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.2,0.1,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel10.add(groupText,cons);
JPanel4.add(JPanel9,"JPanel9");
JPanel9.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel9.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel9.add(groupCombo,cons);
JPanel2.add(JPanel6,BorderLayout.NORTH);
JPanel6.setLayout(new GridLayout(2,1,5,5));
JPanel6.add(JLabel3);
JPanel6.add(JLabel4);
Top.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new GridLayout(1,1,5,5));
JPanel1.add(JPanel5);
JPanel5.setLayout(new FlowLayout(2,5,5));
JPanel5.add(JButton1);
JPanel5.add(JButton2);

  
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

com.adventnet.security.ui.AbstractSecurityModel model = null;
	
public void setSecurityModel(com.adventnet.security.ui.AbstractSecurityModel model)
	{
		this.model = model;
		
	}

 public void displayData()
	{
		
		Vector grpView = model.getAllGroups();

		Vector viewOp = model.getAllViews();
		Vector group = new Vector();
		Vector view = new Vector();
		
		for(int i=0;i< grpView.size();i+=2)
		{
		  group.add(grpView.elementAt(i));
 		  groupCombo.addItem(grpView.elementAt(i));
		}
		
	 	for(int j=0;j< viewOp.size();j++)
		 {
		   AuthViewWithOperations viewObject = (AuthViewWithOperations)viewOp.elementAt(j);                                        
		   view.add(viewObject.getAuthorizedViewName());
		   ListObject lob = new ListObject(viewObject.getAuthorizedViewName());
		   lob.setState(false);
		    ViewListModel.addElement(lob);
		   //((DefaultListModel)JList1.getModel()).addElement(lob);
		}
	}	
	
 public Vector getViewsForGroup(String groupName)
	{
	 	Vector groups = model.getAllGroups();
		int index = groups.indexOf(groupName);
		Vector views = (Vector)groups.elementAt(index+1);
		return views;
 	}	  


  
    

 
public void setAddOrEdit(boolean add, String group)
	{
		Vector viewOp = model.getAllViews();
		
		CardLayout layout = (CardLayout)JPanel4.getLayout();
		if(add)
		{
			addGroup = true;
			ViewListModel.clear();
			for(int j=0;j<viewOp.size();j++)
			{
  			 AuthViewWithOperations viewObject = (AuthViewWithOperations)viewOp.elementAt(j);
			 ListObject lob = new ListObject(viewObject.getAuthorizedViewName());
			 lob.setState(false);
			 ViewListModel.addElement(lob);
 			 JList1.setModel(ViewListModel);
			}	
			
			layout.first(JPanel4);
			
			
		}	
		else
		{ 

			addGroup = false;
			layout.last(JPanel4);
			Vector temp = new Vector();
			for(int i=0;i<groupCombo.getItemCount();i++)
			{
			    temp.add(groupCombo.getItemAt(i).toString()); 
			}	
			if(!temp.contains(group))
			{
			   groupCombo.addItem(group);
			}	
			groupCombo.setSelectedItem(group);
			groupCombo.updateUI();
			//remDupEntry(group);

		}
	
		
		
	}	
	

	
 //<Begin__class_groupCombo_groupCombo_conn1>

 class groupCombo_groupCombo_conn1 implements java.awt.event.ItemListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_groupCombo_groupCombo_conn1>



     //Listener Interface Methods Are Added Below 


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
   
 Vector gwv = model.getAllGroupNames();
 if(!gwv.contains(groupCombo.getSelectedItem().toString()))
 {
  JList1.setModel(ViewListModel);
  addGroup = true;
  return;  
 }
else
 {   
   addGroup = false;  
 }    
 Vector viewGp = getViewsForGroup(groupCombo.getSelectedItem().toString());
 DefaultListModel viewModel2 = new DefaultListModel();
 Vector allViewProps = model.getAllViews();
 Vector viewNames = new Vector();  
 for(int k=0;k < allViewProps.size();k++)
  {
    viewNames.add( ((AuthViewWithOperations)allViewProps.elementAt(k)).getAuthorizedViewName());
  }  

 for(int i=0 ; i< viewGp.size();i++)
  {
   if(viewNames.contains(viewGp.elementAt(i)))
    {
         viewNames.remove(viewGp.elementAt(i));
    } 
  }
  
 for(int y=0;y<viewGp.size();y++)
  { 
    ListObject lob = new ListObject(viewGp.elementAt(y));
   lob.setState(true);  
     viewModel2.addElement(lob);
  } 
  
 for(int x = 0;x< viewNames.size();x++)
 {
 ListObject lob = new ListObject(viewNames.elementAt(x));
 lob.setState(false);   
  viewModel2.addElement(lob);
 }   

 JList1.setModel(viewModel2);  
 JList1.repaint(); 
   
     }
//<UserCode_End_Connection_groupCombo_groupCombo_conn1>
 }//<End__class_groupCombo_groupCombo_conn1>

 
//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {

  ViewGroup.getInstance().enableButtons();
dispose();  
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>

//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


public void actionPerformed( java.awt.event.ActionEvent arg0)
     {

    
  Vector groupviews = new Vector();
  for(int i=0;i< JList1.getModel().getSize();i++)
  {
   ListObject lob = (ListObject)((DefaultListModel)JList1.getModel()).elementAt(i);
   if(lob.getState())
   { 
    groupviews.add(lob.toString());
   }  
  } 
   
 if(groupviews.isEmpty())  
 {
  Utilities.errorMessage(resourceBundle.getString("Select atleast one view for the group"));   
  return;    
 }   
     
  if(addGroup)
   {
    if(groupText.getText().trim().equals(""))
 {
  Utilities.errorMessage(resourceBundle.getString("Group name field cannot be empty")); 
  return;  
 }   
     model.setGroupData(groupText.getText().trim(),groupviews);
    setVisible(false);   
   dispose();   
   }
  else
   {
      model.modifyGroupData(groupCombo.getSelectedItem().toString().trim(),groupviews);  
   dispose();      
  } 
   
   JButton1.setEnabled(false);   
 
      }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>


//<Begin__class_JList1_JPanel3_conn1>

 class JList1_JPanel3_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JList1_JPanel3_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  JButton1.setEnabled(true); 
   Point p = new Point(arg0.getX(),arg0.getY());
  int index = JList1.locationToIndex(p);
 
   if(index == -1)
  {
    return;
  } 

  ListObject lob = (ListObject)((DefaultListModel)JList1.getModel()).elementAt(index); 

   if(lob.getState())
   {
     lob.setState(false);
   JList1.repaint();   
   } 
   else
   {
     lob.setState(true);
   } 
  JList1.repaint(); 
  
  
     }
//<UserCode_End_Connection_JList1_JPanel3_conn1>
 }//<End__class_JList1_JPanel3_conn1>

 
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

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}


















