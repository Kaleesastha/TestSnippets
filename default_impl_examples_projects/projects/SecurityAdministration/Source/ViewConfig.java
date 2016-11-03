
//$Id: ViewConfig.java,v 1.1 2006/08/29 13:57:02 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.applet.*;
import java.util.*;
import javax.swing.table.*;

import com.adventnet.security.authorization.AuthViewWithOperations;



public class ViewConfig extends JFrame implements SecurityCommonInterface
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
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel1 = null;
	com.adventnet.beans.table.SortTableModel ViewTableModel = null;
	//<End_Variable_Declarations>

     
     public static boolean runonce = false;
  
     ViewsWizard views = null;
  
   
  private static ViewConfig viewc = null; 
	
  public static ViewConfig getInstance()
	{
		return viewc;
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
            JTable1.setGridColor(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTable1,ex); 
          }

//<UserCode_Begin_Bean_JTable1>

//<UserCode_End_Bean_JTable1>

          try
          {
            JPanel3.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JButton1.setFont(new Font("Dialog",0,12));
            JButton1.setText(resourceBundle.getString("Add"));
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
            JButton2.setFont(new Font("Dialog",0,13));
            JButton2.setText(resourceBundle.getString("Edit"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>
JButton2.setMnemonic('E');
//<UserCode_End_Bean_JButton2>

          try
          {
            JButton3.setFont(new Font("Dialog",0,13));
            JButton3.setText(resourceBundle.getString("Delete"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>
JButton3.setMnemonic('D');
//<UserCode_End_Bean_JButton3>

          try
          {
            closeButton.setFont(new Font("Dialog",0,13));
            closeButton.setText(resourceBundle.getString("Close"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>

//<UserCode_End_Bean_closeButton>

//<UserCode_Begin_Bean_JPanel5>
//JPanel5.setVisible(false);
//<UserCode_End_Bean_JPanel5>

          try
          {
            JLabel1.setFont(new Font("Dialog",0,12));
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setText(resourceBundle.getString("Use the following list to add , edit a View , its Properties and authorized Operations"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

//<UserCode_Begin_Bean_ViewTableModel>
ViewTableModel.setDataVector(
  new Object[0][0], 
new String[]{ resourceBundle.getString("Views List")} );
 
//<UserCode_End_Bean_ViewTableModel>
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+22,JPanel5.getPreferredSize().height+38));
		closeButton.setPreferredSize(new Dimension(closeButton.getPreferredSize().width+8,closeButton.getPreferredSize().height+0));
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+32,JButton3.getPreferredSize().height+0));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+45,JButton2.getPreferredSize().height+0));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+47,JButton1.getPreferredSize().height+2));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+10,JPanel2.getPreferredSize().height+26));
		JLabel2.setPreferredSize(new Dimension(JLabel2.getPreferredSize().width+124,JLabel2.getPreferredSize().height+72));
		JPanel6.setPreferredSize(new Dimension(JPanel6.getPreferredSize().width+10,JPanel6.getPreferredSize().height+114));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+39,JPanel1.getPreferredSize().height+38));

  
//<End_setUpProperties>
		
    JPanel1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("View Settings")));	
    JPanel5.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Views Properties and Operations")));		
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
        this.setSize(getPreferredSize().width+495,getPreferredSize().height+480); 
          setTitle(resourceBundle.getString("ViewConfig"));
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
	setTitle(resourceBundle.getString("View Configuration"));	
	setIconImage(AuthMain.getBuilderUiIfInstance().getFrameIcon());	
	JLabel1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("viewconfig.png"));	
	com.adventnet.security.ui.ViewListCellRenderer ViewListCellRenderer1 = new com.adventnet.security.ui.ViewListCellRenderer();
	JTable1.setDefaultRenderer(JTable1.getColumnClass(0),ViewListCellRenderer1);	
	
	JLabel2.setIcon(AuthMain.getBuilderUiIfInstance().getImage("addview1.png"));		
 	JTable1.getCellEditor(0,0).getTableCellEditorComponent(JTable1,null,true, 0,0).setEnabled(false);
	DefaultCellEditor te = (DefaultCellEditor)JTable1.getCellEditor(0,0);
	te.setClickCountToStart(10);
	JTable1.setCellEditor(te);
		
	
	JViewport vp = new JViewport();
	JLabel lab = new JLabel(resourceBundle.getString("List of available views"));
	lab.setHorizontalAlignment((int)JLabel.CENTER_ALIGNMENT);
	lab.setForeground(Color.black);
	vp.setView(lab);	
	
	AuthMain.getBuilderUiIfInstance().centerWindow(this);	
	setData();	
	
    	viewc = this;	
		
   			addWindowListener(new WindowAdapter()
					{
						public void windowClosing(WindowEvent we)
						      {
							close();
						      }
					}
	 				   );

	/*
	TableColumn col2  = JTable1.getColumnModel().getColumn(1);
	DefaultTableCellRenderer ren = new DefaultTableCellRenderer();
	ren.setIcon(AuthMain.getBuilderUiIfInstance().getImage("task1.png"));	
 	col2.setCellRenderer(ren);	
  	col2.setMaxWidth(30);	
	*/
		
	DefaultListSelectionModel  selModel = new DefaultListSelectionModel();
	selModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JTable1.setSelectionModel(selModel);
		
  } 
   
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new com.adventnet.beans.table.SortTable();
        JPanel6= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        closeButton= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        ViewTableModel= new com.adventnet.beans.table.SortTableModel();

  
        //<End_initVariables>
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




   


  public ViewConfig()
  {
    //<Begin_ViewConfig>
    pack();
  
    //<End_ViewConfig>
		
  }

  public ViewConfig(java.applet.Applet applet)
  {
    //<Begin_ViewConfig_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ViewConfig_java.applet.Applet>
  }
	
  public ViewConfig(Frame owner,java.applet.Applet applet)
  {
    //super(owner);
    this.applet = applet;
    pack();
    this.setTitle("ViewConfig");
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

com.adventnet.security.ui.AbstractSecurityModel model = null;
	
public void setSecurityModel(com.adventnet.security.ui.AbstractSecurityModel model)
	{
		this.model = model;
	}
public void setBuilderUiImpl(com.adventnet.nms.util.CommonBuilderUIImpl uiImpl)
	{
		
	}	
public void fireDataChanged()
	{
		enableButtons();
		setData();
	}	
public void close()
	{
		if(views != null)
		{
			views.dispose();
		}
		runonce = false;	
     	           AdvPropScreen.getInstance().enableButtons();  
		model = null;
		removeAll();
		dispose();
	}	
public void registerWithModel()
	{
		model.registerWithModel(this);
	}
public void setData()
	{
		/*
	    if(model.getAllViews() == null)	
 		{
	         	return;
		}	
		*/
	for(int i=JTable1.getRowCount()-1;i>=0;i--)
 		{
			ViewTableModel.removeRow(i);			
		}	
 
	Vector views = model.getAllViews();
	for(int j=0;j<views.size();j++)
	{
		AuthViewWithOperations view = (AuthViewWithOperations)views.elementAt(j);
		String viewNa =view.getAuthorizedViewName();
		ViewTableModel.addRow(new Object[]{viewNa});
	}

 }	



 public void showError(String err)
	{
  		 enableButtons(); 	   
		Utilities.errorMessage(resourceBundle.getString(err));
	}	


public void enableButtons()
	{
		JButton1.setEnabled(true);
		JButton3.setEnabled(true);
		JButton2.setEnabled(true);		
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
		
	}	
public void disableButtons()
	{
		JButton1.setEnabled(false);
		JButton3.setEnabled(false);
		JButton2.setEnabled(false);		
		setCursor(new Cursor(Cursor.WAIT_CURSOR));	
	}	

 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTable1);
JPanel1.add(JPanel6,BorderLayout.WEST);
JPanel6.setLayout(new BorderLayout(5,5));
JPanel6.add(JLabel2,BorderLayout.CENTER);
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new GridLayout(1,1,5,5));
JPanel2.add(JPanel3);
JPanel3.setLayout(new FlowLayout(2,5,5));
JPanel3.add(JButton1);
JPanel3.add(JButton2);
JPanel3.add(JButton3);
JPanel3.add(closeButton);
Top.add(JPanel5,BorderLayout.NORTH);
JPanel5.setLayout(new GridLayout(2,1,5,5));
JPanel5.add(JLabel1);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
      //<Begin_setUpConnections> 

      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      JButton3.addActionListener(JButton3_JButton3_conn11);
      closeButton_closeButton_conn1 closeButton_closeButton_conn11 =  new closeButton_closeButton_conn1();
      closeButton.addActionListener(closeButton_closeButton_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
  } 


//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
   
 if(JTable1.getSelectedRowCount() !=1 ) 
    {
   
 Utilities.errorMessage(resourceBundle.getString("Please select a view to edit"));    
 return;   
    }    
   


    views = new ViewsWizard(ViewConfig.this,applet);
   views.setSecurityModel(model);   
  Point p = JLabel1.getLocationOnScreen();  
  views.setLocation(p);    
    views.init();    



    views.setState(false); 
  Vector viewvec = model.getAllViews();
  for(int i=0;i<viewvec.size();i++)
  { 
String viewname = ((AuthViewWithOperations)viewvec.elementAt(i)) .getAuthorizedViewName(); 
  if(JTable1.getValueAt(JTable1.getSelectedRow(),0).toString().equals( viewname))
    {
      views.setValues((AuthViewWithOperations)viewvec.elementAt(i));    
    }  
  }   

    disableButtons();   
     views.setVisible(true);    
   
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
if(JTable1.getSelectedRowCount() != 1)
  {
 Utilities.errorMessage(resourceBundle.getString("Please select a view to delete"));    
    return ; 
  } 
 
 if( JOptionPane.showConfirmDialog(null,
                                           resourceBundle.getString("Are you sure you want to delete the selected view "),
                                           resourceBundle.getString("Warning!"),
                                           JOptionPane.YES_NO_OPTION,
                                          JOptionPane.WARNING_MESSAGE,
                                           null) == JOptionPane.NO_OPTION)
       
              return;    
 
  Vector viewvec = model.getAllViews();
      for(int i=0;i<viewvec.size();i++)
   { 
 String viewname = ((AuthViewWithOperations)viewvec.elementAt(i)) .getAuthorizedViewName(); 
   if( JTable1.getValueAt(JTable1.getSelectedRow(),0).toString().equals( viewname))
     {
     AuthViewWithOperations avop = (AuthViewWithOperations)viewvec.elementAt(i); 
  
     model.delViewOp(avop.getAuthorizedViewName(),avop.getViewProperties(),avop.getOperations());
     }   
 
 }  
 
disableButtons();
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>
//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  
   views = new ViewsWizard(ViewConfig.this ,applet);
   views.setSecurityModel(model); 
 Point p = JLabel1.getLocationOnScreen();  
  views.setLocation(p);  
   views.init(); 
   views.setState(true);
    disableButtons();   
   views.setVisible(true);    
  
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>
//<Begin__class_closeButton_closeButton_conn1>

 class closeButton_closeButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_closeButton_closeButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
   AdvPropScreen.getInstance().enableButtons();  
   close();
     }
//<UserCode_End_Connection_closeButton_closeButton_conn1>
 }//<End__class_closeButton_closeButton_conn1>




 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}


































