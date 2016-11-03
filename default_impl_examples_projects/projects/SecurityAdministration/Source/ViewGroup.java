
//$Id: ViewGroup.java,v 1.1 2006/08/29 13:57:02 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;


public class ViewGroup extends JFrame implements SecurityCommonInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	com.adventnet.beans.table.SortTable JTable1 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JButton JButton4 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton JButton2 = null;
	com.adventnet.beans.table.SortTableModel ViewTableModel = null;
	//<End_Variable_Declarations>


    public static boolean runonce = false;
  
GroupViewAscn  gva =null;	
  private static ViewGroup viewgp = null;
	
  public static ViewGroup getInstance()
	{
  		return viewgp;
	}	
  
   


   


  public ViewGroup()
  {
    //<Begin_ViewGroup>
    pack();
  
    //<End_ViewGroup>
  }

  public ViewGroup(java.applet.Applet applet)
  {
    //<Begin_ViewGroup_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ViewGroup_java.applet.Applet>
  }
	
  public ViewGroup(Frame owner,java.applet.Applet applet)
  {
    //super(owner);		
    this.applet = applet;
    pack();
    this.setTitle("ViewGroup");
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
//this.setModal(true);
setResizable(false);
//<UserCode_End_Bean_Top>

          try
          {
            JTable1.setRowHeight(22);
            JTable1.setModel(ViewTableModel);
            JTable1.setGridColor(new Color(-8355712));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTable1,ex); 
          }

//<UserCode_Begin_Bean_JTable1>

//<UserCode_End_Bean_JTable1>

          try
          {
            JPanel5.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            JButton1.setText(resourceBundle.getString("Add"));
            JButton1.setToolTipText(resourceBundle.getString("Add a group"));
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
            JButton3.setText(resourceBundle.getString("Edit"));
            JButton3.setToolTipText(resourceBundle.getString("Select agroup to edit"));
            JButton3.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>
JButton3.setMnemonic('E');
//<UserCode_End_Bean_JButton3>

          try
          {
            JButton4.setText(resourceBundle.getString("Delete"));
            JButton4.setToolTipText(resourceBundle.getString("Selected a group to delete."));
            JButton4.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton4,ex); 
          }

//<UserCode_Begin_Bean_JButton4>
JButton4.setMnemonic('D');
//<UserCode_End_Bean_JButton4>

          try
          {
            JLabel1.setFont(new Font("Dialog",0,12));
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setText(resourceBundle.getString("Use the following  list to add or  edit a group and assign views for it."));
            JLabel1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Views and Groups")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JPanel3.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Note")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setHorizontalTextPosition(0);
            JLabel2.setText(resourceBundle.getString("   The groups for which views are not associated cannot be deleted from here. You have to "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel3.setFont(new Font("Dialog",0,12));
            JLabel3.setForeground(new Color(-16764109));
            JLabel3.setHorizontalTextPosition(0);
            JLabel3.setText(resourceBundle.getString("   edit the users for which this group is present and disassociate the group from all of them"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel4.setFont(new Font("Dialog",0,12));
            JLabel4.setForeground(new Color(-16764109));
            JLabel4.setText(resourceBundle.getString("   You can only associate views for such groups ."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

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

//<UserCode_Begin_Bean_ViewTableModel>
  String[] columnNames = {resourceBundle.getString("Group Name"),resourceBundle.getString("View Name(s)")}; 
ViewTableModel.setDataVector(new Object[0][0],columnNames); 

//<UserCode_End_Bean_ViewTableModel>
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+10));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+10,JPanel2.getPreferredSize().height+10));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+104,JLabel1.getPreferredSize().height+12));
		JButton4.setPreferredSize(new Dimension(JButton4.getPreferredSize().width+21,JButton4.getPreferredSize().height+0));
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+35,JButton3.getPreferredSize().height+0));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+35,JButton1.getPreferredSize().height+0));
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+233,JPanel5.getPreferredSize().height+0));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+413,JScrollPane1.getPreferredSize().height+47));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+80));

  
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
        this.setSize(getPreferredSize().width+507,getPreferredSize().height+482); 
          setTitle(resourceBundle.getString("ViewGroup"));
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
	          setTitle(resourceBundle.getString("Views and Groups"));	
	setIconImage(AuthMain.getBuilderUiIfInstance().getFrameIcon());	
        com.adventnet.security.ui.ViewTableCellRenderer ViewTableCellRenderer1 = new com.adventnet.security.ui.ViewTableCellRenderer();
 	JTable1.setDefaultRenderer(JTable1.getColumnClass(0),ViewTableCellRenderer1);	
	//setViews();
	AuthMain.getBuilderUiIfInstance().centerWindow(this);	
	JLabel1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("usersettings.png"));	
		
		
 	JTable1.getCellEditor(0,0).getTableCellEditorComponent(JTable1,null,true, 0,0).setEnabled(false);
     DefaultCellEditor te = (DefaultCellEditor)JTable1.getCellEditor(0,0);
	te.setClickCountToStart(10);
     JTable1.setCellEditor(te);
		
	JTable1.getTableHeader().setReorderingAllowed(false);		
		
	viewgp = this;	
		
	addWindowListener(new WindowAdapter()
						{
							public void windowClosing(WindowEvent we)
								{
									close();
								}
						}
					   );	
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      JButton3.addActionListener(JButton3_JButton3_conn11);
      JButton4_JButton4_conn1 JButton4_JButton4_conn11 =  new JButton4_JButton4_conn1();
      JButton4.addActionListener(JButton4_JButton4_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new com.adventnet.beans.table.SortTable();
        JPanel5= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        JButton4= new javax.swing.JButton();
        JLabel1= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JLabel3= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        JPanel4= new javax.swing.JPanel();
        JButton2= new javax.swing.JButton();
        ViewTableModel= new com.adventnet.beans.table.SortTableModel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(15,10));
JPanel1.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTable1);
JPanel1.add(JPanel5,BorderLayout.SOUTH);
JPanel5.setLayout(new FlowLayout(2,5,15));
JPanel5.add(JButton1);
JPanel5.add(JButton3);
JPanel5.add(JButton4);
Top.add(JLabel1,BorderLayout.NORTH);
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new GridLayout(3,1,5,5));
JPanel3.add(JLabel2);
JPanel3.add(JLabel3);
JPanel3.add(JLabel4);
JPanel2.add(JPanel4,BorderLayout.SOUTH);
JPanel4.setLayout(new FlowLayout(2,5,5));
JPanel4.add(JButton2);

  
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
		model.registerWithModel(this);
	}

public void setBuilderUiImpl(com.adventnet.nms.util.CommonBuilderUIImpl uiImpl)
	{
		
	}	
public void fireDataChanged()
	{
		enableButtons();
		setViews();
	}	
public void close()
	{

	 if(gva != null)
		{
			gva.dispose();	
		}
	  AdvPropScreen.getInstance().enableButtons();  
	  runonce = false;
	  removeAll();
	  model = null;
	  dispose();	
	}
 public void showError(String err)
	{
          enableButtons(); 	   
		Utilities.errorMessage(resourceBundle.getString(err));
	}

 public Vector getAllGroups()
	{
		Vector allGroupVec = new Vector();
		//Vector vec = model.getAllGroups();
		/*
 		Vector uservec = model.getAllUserNames();
		
		for(int i=0;i < uservec.size();i++)
			{
  				String uname = (String)uservec.elementAt(i);
		          Vector grpvec = model.getGroupsForUser(uname);
				for(int j=0;j<grpvec.size();j++)
				{
					if(!allGroupVec.contains(grpvec.elementAt(j).toString()))							
					{
					  allGroupVec.add(grpvec.elementAt(j));	
					}
				}
			}
		*/
		return allGroupVec;
  	}
	
 public void setViews()
	{
		if(JTable1.getRowCount() > 0)
			{
	  for(int t=JTable1.getRowCount()-1;t>=0;t--)
 		{
			ViewTableModel.removeRow(t);
 		}
			}
	   Vector v = model.getAllGroups();
	   Object[][] obj = new Object[v.size()/2][2];
	   int count = 0;
	   for(int i=0;i<v.size();i += 2)
		{
			    String str = "",str1 = "";
			    str = v.elementAt(i).toString();
			    Vector temp = (Vector)v.elementAt(i+1);
		         for(int j= 0;j<temp.size();j++)
				{
		  		 str1 += temp.elementAt(j).toString()+ ",";
				}
	 			obj[count][0] = str;
			if(str1 != "")
				{		
	 			obj[count][1] = str1.substring(0,str1.length()-1);	
				}
			else
				{
 				obj[count][1] = str1;
				}
	 	 	 count ++;
	
    }	
	
	String[] columnNames = {resourceBundle.getString("Group Name"),resourceBundle.getString("View Name(s)")};
 	ViewTableModel.setDataVector(obj,columnNames);
	Vector groupnames = new Vector();
	for(int r=0;r<JTable1.getRowCount();r++)
 		{
		   groupnames.add(JTable1.getValueAt(r,0).toString());
 		}
	
	Vector temp = 	getAllGroups();
	for(int k=0;k<temp.size();k++)  
 		{
		   if(!groupnames.contains(temp.elementAt(k)) )
			{
				groupnames.add(temp.elementAt(k));
			}	
		}	
           	  
	  for(int n=0;n<JTable1.getRowCount();n++)
 		{
		   groupnames.remove(JTable1.getValueAt(n,0).toString());
		}	
	
	 for(int z = 0;z<groupnames.size();z++)	
 		{
	             ViewTableModel.addRow(new String[]{groupnames.elementAt(z).toString(),""});		
 		}
	
	}

public void enableButtons()
	{
		JButton1.setEnabled(true);
		JButton3.setEnabled(true);
		JButton4.setEnabled(true);		
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
		
	}	
public void disableButtons()
	{
		JButton1.setEnabled(false);
		JButton3.setEnabled(false);
		JButton4.setEnabled(false);		
		setCursor(new Cursor(Cursor.WAIT_CURSOR));	
	}	

           
 

                        
 

                        

//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 gva = new GroupViewAscn(ViewGroup.this,applet);
   
 gva.setSecurityModel(model);   
   
  gva.init();
  gva.setAddOrEdit(true,""); 
 disableButtons();   
  gva.setVisible(true);
   

     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>
                        

//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {

  close();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>
                        

//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 if(JTable1.getSelectedRowCount() !=  1)
  {
   Utilities.errorMessage(resourceBundle.getString("Please select a group to edit"));    
   return;
  } 
  
   gva = new GroupViewAscn(ViewGroup.this, applet);
   gva.setSecurityModel(model);   
  
   gva.init();
   gva.setAddOrEdit(false,JTable1.getValueAt(JTable1.getSelectedRow(),0).toString());    
   //gva.remDupEntry(JTable1.getValueAt(JTable1.getSelectedRow(),0).toString());  
   disableButtons();
   gva.setVisible(true);  

  
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>
//<Begin__class_JButton4_JButton4_conn1>

 class JButton4_JButton4_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton4_JButton4_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 if(JTable1.getSelectedRowCount() != 1)
  {
     Utilities.errorMessage(resourceBundle.getString("Please select a group to delete"));    
   return;
  } 
 if( JOptionPane.showConfirmDialog(null,
                resourceBundle.getString("Are you sure you want to delete the selected group") ,
                                           resourceBundle.getString("Warning!"),
                                           JOptionPane.YES_NO_OPTION,
                                          JOptionPane.WARNING_MESSAGE,
                                          null) == JOptionPane.NO_OPTION)
       
              return;   
  
 model.deleteGroup(JTable1.getValueAt(JTable1.getSelectedRow(),0).toString());  
     }
//<UserCode_End_Connection_JButton4_JButton4_conn1>
 }//<End__class_JButton4_JButton4_conn1>
                        
 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
                        }







