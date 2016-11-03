/* $Id: TL1TypeCfgtrScreen.java,v 1.1 2006/08/29 13:57:02 build Exp $ */

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory


//package com.adventnet.nms.studio.typecfgtr.tl1;

//<Begin_Version>
//version$2
//<End_Version>
package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;
import java.util.*;


public class TL1TypeCfgtrScreen extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JButton JButton4 = null;
	javax.swing.JButton JButton5 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel GradientLabel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable JTable1 = null;
	javax.swing.JPanel JPanel41 = null;
	javax.swing.JLabel GradientLabel11 = null;
	javax.swing.JLabel JLabel12 = null;
	javax.swing.JScrollPane JScrollPane11 = null;
	javax.swing.JTable JTable11 = null;
	javax.swing.JPanel JPanel411 = null;
	javax.swing.JLabel GradientLabel111 = null;
	javax.swing.JLabel JLabel121 = null;
	javax.swing.JScrollPane JScrollPane111 = null;
	javax.swing.JTable JTable111 = null;
	javax.swing.JPanel JPanel412 = null;
	javax.swing.JLabel GradientLabel112 = null;
	javax.swing.JLabel JLabel122 = null;
	javax.swing.JScrollPane JScrollPane112 = null;
	javax.swing.JTable JTable112 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	TL1TypeCfgtrHandler tyHlr;

	private GroupPropertiesDialog gpDlg;
	private IPPropertiesDialog ipDlg;
    private GNEPropertiesDialog gneDlg;
    private TidPropertiesDialog tidDlg;
    
	TL1TableCellRenderer cellRen;

	public TL1TypeCfgtrScreen()
  {
      //<Begin_TL1TypeCfgtrScreen>
      this.init();
  
    //<End_TL1TypeCfgtrScreen>
	}

	public TL1TypeCfgtrScreen(java.applet.Applet applet)
  {
		//<Begin_TL1TypeCfgtrScreen_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_TL1TypeCfgtrScreen_java.applet.Applet>
	}

    public void init()
  {
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+459,getPreferredSize().height+491)); 
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
		setupGroupTable();
	} 
    
	void setupGroupTable()
	{
        if (!TL1MainScreen.isTL1)
        {
            return;
        }
		JTable1.setModel(tyHlr.getDeviceGroupModel());
		int selIndex = JTable1.getRowCount() -1 ;
		if(selIndex != -1)
		{
			JTable1.setRowSelectionInterval(selIndex,selIndex); 
		}
		TableColumn col1 = JTable1.getColumn(resourceBundle.getString("Device Group"));
		col1.setPreferredWidth(200);
		TableColumn col2 = JTable1.getColumn(resourceBundle.getString("Type"));
		col2.setPreferredWidth(200);
		TableColumn col3 = JTable1.getColumn(resourceBundle.getString("Port"));
		col3.setPreferredWidth(75);
		TableColumn col4 = JTable1.getColumn(resourceBundle.getString("Status Poller"));
		col4.setPreferredWidth(300);
		TableColumn col5 = JTable1.getColumn(resourceBundle.getString("Connection Handler"));
		col5.setPreferredWidth(300);
                TableColumn col6 = JTable1.getColumn(resourceBundle.getString("APPEND_FRONT"));
		col6.setPreferredWidth(300);
                TableColumn col7 = JTable1.getColumn(resourceBundle.getString("APPEND_END"));
		col7.setPreferredWidth(300);
		TableColumn col8 = JTable1.getColumn(resourceBundle.getString("Properties"));
		col8.setMinWidth(0);
		col8.setMaxWidth(0);
		col8.setPreferredWidth(0);
		col8.setResizable(false);
		JTable1.setDefaultEditor(Object.class,null);
		JTable1.setDefaultRenderer(Object.class,cellRen);
	}
    
	void setupIPTable(String gname)
	{
		JTable11.setModel(tyHlr.getDeviceIPModel(gname));
		int selIndex = JTable11.getRowCount() -1 ;
		if(selIndex != -1)
		{
			JTable11.setRowSelectionInterval(selIndex,selIndex); 
		}
		TableColumn col1 = JTable11.getColumn(resourceBundle.getString("Device IP"));
		col1.setPreferredWidth(200);
		TableColumn col2 = JTable11.getColumn(resourceBundle.getString("Type"));
		col2.setPreferredWidth(200);
		TableColumn col3 = JTable11.getColumn(resourceBundle.getString("Port"));
		col3.setPreferredWidth(75);
		TableColumn col4 = JTable11.getColumn(resourceBundle.getString("Status Poller"));
		col4.setPreferredWidth(300);
		TableColumn col5 = JTable11.getColumn(resourceBundle.getString("Connection Handler"));
		col5.setPreferredWidth(300);
		TableColumn col6 = JTable11.getColumn(resourceBundle.getString("APPEND_FRONT"));
		col6.setPreferredWidth(300);
		TableColumn col7 = JTable11.getColumn(resourceBundle.getString("APPEND_END"));
		col7.setPreferredWidth(300);
		TableColumn col8 = JTable11.getColumn(resourceBundle.getString("Properties"));
		col8.setMinWidth(0);
		col8.setMaxWidth(0);
		col8.setPreferredWidth(0);
		col8.setResizable(false);
		JTable11.setDefaultEditor(Object.class,null);
		JTable11.setDefaultRenderer(Object.class,cellRen);
    }
    
    void setupGNETable(String gname)
	{
        JTable111.setModel(tyHlr.getGNEIPModel(gname));
		int selIndex = JTable111.getRowCount() -1 ;
		if(selIndex != -1)
		{
			JTable111.setRowSelectionInterval(selIndex,selIndex); 
		}
		TableColumn col1 = JTable111.getColumn(resourceBundle.getString("GNEIP"));
		col1.setPreferredWidth(200);
		TableColumn col2 = JTable111.getColumn(resourceBundle.getString("Type"));
		col2.setPreferredWidth(200);
		TableColumn col3 = JTable111.getColumn(resourceBundle.getString("Port"));
		col3.setPreferredWidth(75);
		TableColumn col4 = JTable111.getColumn(resourceBundle.getString("Status Poller"));
		col4.setPreferredWidth(300);
		TableColumn col5 = JTable111.getColumn(resourceBundle.getString("Connection Handler"));
		col5.setPreferredWidth(300);
		TableColumn col6 = JTable111.getColumn(resourceBundle.getString("APPEND_FRONT"));
		col6.setPreferredWidth(300);
		TableColumn col7 = JTable111.getColumn(resourceBundle.getString("APPEND_END"));
		col7.setPreferredWidth(300);
		TableColumn col8 = JTable111.getColumn(resourceBundle.getString("Properties"));
		col8.setMinWidth(0);
		col8.setMaxWidth(0);
		col8.setPreferredWidth(0);
		col8.setResizable(false);
		JTable111.setDefaultEditor(Object.class,null);
		JTable111.setDefaultRenderer(Object.class,cellRen);
    }


    void setupTIDTable(String gname,String port,DefaultTableModel dmodel)
	{
	   
        String groupName = (String)(tyHlr.gvmodel.get(dmodel));
        JTable112.setModel(tyHlr.getTIDIPModel(gname,port,groupName));
		int selIndex = JTable112.getRowCount() -1 ;
		if(selIndex != -1)
		{
			JTable112.setRowSelectionInterval(selIndex,selIndex); 
		}
		TableColumn col1 = JTable112.getColumn(resourceBundle.getString("TID"));
		col1.setPreferredWidth(200);
		TableColumn col2 = JTable112.getColumn(resourceBundle.getString("Type"));
		col2.setPreferredWidth(200);
		TableColumn col3 = JTable112.getColumn(resourceBundle.getString("Port"));
		col3.setPreferredWidth(75);
		TableColumn col4 = JTable112.getColumn(resourceBundle.getString("Status Poller"));
		col4.setPreferredWidth(300);
		TableColumn col5 = JTable112.getColumn(resourceBundle.getString("Connection Handler"));
		col5.setPreferredWidth(300);
        TableColumn col6 = JTable112.getColumn(resourceBundle.getString("APPEND_FRONT"));
		col6.setPreferredWidth(300);
        TableColumn col7 = JTable112.getColumn(resourceBundle.getString("APPEND_END"));
		col7.setPreferredWidth(300);
		TableColumn col8 = JTable112.getColumn(resourceBundle.getString("Properties"));
		col8.setMinWidth(0);
		col8.setMaxWidth(0);
		col8.setPreferredWidth(0);
		col8.setResizable(false);
		JTable112.setDefaultEditor(Object.class,null);
		JTable112.setDefaultRenderer(Object.class,cellRen);
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
    
    
	public void setUpProperties()
  {
      if (!TL1MainScreen.isTL1)
      {
          return;
      }
		//<Begin_setUpProperties> 

          try
          {
            JButton1.setFont(new Font("SansSerif",0,12));
            JButton1.setHorizontalTextPosition(4);
            JButton1.setText(resourceBundle.getString("Add"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JButton2.setFont(new Font("SansSerif",0,12));
            JButton2.setHorizontalTextPosition(4);
            JButton2.setText(resourceBundle.getString("Delete"));
            JButton2.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            JButton3.setFont(new Font("SansSerif",0,12));
            JButton3.setHorizontalTextPosition(4);
            JButton3.setEnabled(false);
            JButton3.setText(resourceBundle.getString("Modify"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>

//<UserCode_End_Bean_JButton3>

          try
          {
            JButton4.setFont(new Font("SansSerif",0,12));
            JButton4.setHorizontalTextPosition(4);
            JButton4.setText(resourceBundle.getString("IP List >>"));
            JButton4.setActionCommand("IP");
            JButton4.setPreferredSize(new Dimension(150,27));
            JButton4.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton4,ex); 
          }

//<UserCode_Begin_Bean_JButton4>

//<UserCode_End_Bean_JButton4>

          try
          {
              JButton5.setFont(new Font("SansSerif",0,12));
              JButton5.setHorizontalTextPosition(4);
              JButton5.setText(resourceBundle.getString("GNE List >>"));
              JButton5.setActionCommand("GNE");
              JButton5.setPreferredSize(new Dimension(150,27));
              JButton5.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton5,ex); 
          }

//<UserCode_Begin_Bean_JButton5>

//<UserCode_End_Bean_JButton5>

          try
          {
            GradientLabel1.setPreferredSize(new Dimension(134,50));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+GradientLabel1,ex); 
          }

//<UserCode_Begin_Bean_GradientLabel1>

//<UserCode_End_Bean_GradientLabel1>

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setHorizontalTextPosition(4);
            JLabel1.setText(resourceBundle.getString("Device Group Details"));
            JLabel1.setForeground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JTable1.setAutoResizeMode(0);
            JTable1.setRowHeight(20);
            JTable1.setSelectionBackground(new Color(-6118750));
            JTable1.setSelectionForeground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTable1,ex); 
          }

//<UserCode_Begin_Bean_JTable1>

//<UserCode_End_Bean_JTable1>

          try
          {
            GradientLabel11.setPreferredSize(new Dimension(134,50));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+GradientLabel11,ex); 
          }

//<UserCode_Begin_Bean_GradientLabel11>

//<UserCode_End_Bean_GradientLabel11>

          try
          {
            JLabel12.setHorizontalAlignment(2);
            JLabel12.setFont(new Font("SansSerif",0,12));
            JLabel12.setHorizontalTextPosition(4);
            JLabel12.setForeground(new Color(-1));
            JLabel12.setText(resourceBundle.getString("IP Details"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel12,ex); 
          }

//<UserCode_Begin_Bean_JLabel12>

//<UserCode_End_Bean_JLabel12>

          try
          {
            JTable11.setAutoResizeMode(0);
            JTable11.setRowHeight(20);
            JTable11.setSelectionBackground(new Color(-6118496));
            JTable11.setSelectionForeground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTable11,ex); 
          }

//<UserCode_Begin_Bean_JTable11>

//<UserCode_End_Bean_JTable11>

          try
          {
            GradientLabel111.setPreferredSize(new Dimension(134,50));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+GradientLabel111,ex); 
          }

//<UserCode_Begin_Bean_GradientLabel111>

//<UserCode_End_Bean_GradientLabel111>

          try
          {
            JLabel121.setHorizontalAlignment(2);
            JLabel121.setFont(new Font("SansSerif",0,12));
            JLabel121.setHorizontalTextPosition(4);
            JLabel121.setForeground(new Color(-1));
            JLabel121.setText(resourceBundle.getString("GNE Details"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel121,ex); 
          }

//<UserCode_Begin_Bean_JLabel121>

//<UserCode_End_Bean_JLabel121>

          try
          {
            JTable111.setAutoResizeMode(0);
            JTable111.setRowHeight(20);
            JTable111.setSelectionBackground(new Color(-6118496));
            JTable111.setSelectionForeground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTable111,ex); 
          }

//<UserCode_Begin_Bean_JTable111>

//<UserCode_End_Bean_JTable111>

          try
          {
            GradientLabel112.setPreferredSize(new Dimension(134,50));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+GradientLabel112,ex); 
          }

//<UserCode_Begin_Bean_GradientLabel112>

//<UserCode_End_Bean_GradientLabel112>

          try
          {
            JLabel122.setHorizontalAlignment(2);
            JLabel122.setFont(new Font("SansSerif",0,12));
            JLabel122.setHorizontalTextPosition(4);
            JLabel122.setForeground(new Color(-1));
            JLabel122.setText(resourceBundle.getString("Tid Details"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel122,ex); 
          }

//<UserCode_Begin_Bean_JLabel122>

//<UserCode_End_Bean_JLabel122>

          try
          {
            JTable112.setAutoResizeMode(0);
            JTable112.setRowHeight(20);
            JTable112.setSelectionBackground(new Color(-6118496));
            JTable112.setSelectionForeground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTable112,ex); 
          }

//<UserCode_Begin_Bean_JTable112>

//<UserCode_End_Bean_JTable112>
		JLabel122.setPreferredSize(new Dimension(JLabel122.getPreferredSize().width+48,JLabel122.getPreferredSize().height+1));
		GradientLabel112.setPreferredSize(new Dimension(GradientLabel112.getPreferredSize().width+263,GradientLabel112.getPreferredSize().height+0));
		JScrollPane111.setPreferredSize(new Dimension(JScrollPane111.getPreferredSize().width+15,JScrollPane111.getPreferredSize().height+15));
		JLabel121.setPreferredSize(new Dimension(JLabel121.getPreferredSize().width+48,JLabel121.getPreferredSize().height+1));
		GradientLabel111.setPreferredSize(new Dimension(GradientLabel111.getPreferredSize().width+263,GradientLabel111.getPreferredSize().height+0));
		JScrollPane11.setPreferredSize(new Dimension(JScrollPane11.getPreferredSize().width+15,JScrollPane11.getPreferredSize().height+15));
		JLabel12.setPreferredSize(new Dimension(JLabel12.getPreferredSize().width+48,JLabel12.getPreferredSize().height+1));
		GradientLabel11.setPreferredSize(new Dimension(GradientLabel11.getPreferredSize().width+263,GradientLabel11.getPreferredSize().height+0));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+15,JScrollPane1.getPreferredSize().height+15));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+1));
		GradientLabel1.setPreferredSize(new Dimension(GradientLabel1.getPreferredSize().width+281,GradientLabel1.getPreferredSize().height+0));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+60,JPanel3.getPreferredSize().height+174));

  
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
        JPanel2= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        JButton4= new javax.swing.JButton();
        JButton5= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        GradientLabel1= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new javax.swing.JTable();
        JPanel41= new javax.swing.JPanel();
        GradientLabel11= new javax.swing.JLabel();
        JLabel12= new javax.swing.JLabel();
        JScrollPane11= new javax.swing.JScrollPane();
        JTable11= new javax.swing.JTable();
        JPanel411= new javax.swing.JPanel();
        GradientLabel111= new javax.swing.JLabel();
        JLabel121= new javax.swing.JLabel();
        JScrollPane111= new javax.swing.JScrollPane();
        JTable111= new javax.swing.JTable();
        JPanel412= new javax.swing.JPanel();
        GradientLabel112= new javax.swing.JLabel();
        JLabel122= new javax.swing.JLabel();
        JScrollPane112= new javax.swing.JScrollPane();
        JTable112= new javax.swing.JTable();

  
        //<End_initVariables>
        if (!TL1MainScreen.isTL1)
        {
            return;
        }
        
		tyHlr = new TL1TypeCfgtrHandler();
                tyHlr.resourceBundle=resourceBundle;
		gpDlg = new GroupPropertiesDialog();
                gpDlg.init(applet);
		ipDlg = new IPPropertiesDialog();
		ipDlg.init(applet);
        	gneDlg = new GNEPropertiesDialog();
		 gneDlg.init(applet);
	        tidDlg = new TidPropertiesDialog();
		tidDlg.init(applet);
	        cellRen = new TL1TableCellRenderer();
	} 
	public void setUpGUI(Container container)
  {
      
      if (!TL1MainScreen.isTL1)
      {
          return;
      }

		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,30,0,10);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(JButton1,cons);
inset = new Insets(5,0,0,10);
setConstraints(1,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(JButton2,cons);
inset = new Insets(5,0,0,0);
setConstraints(2,0,1,1,0.1,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(JButton3,cons);
inset = new Insets(25,0,5,10);
setConstraints(2,1,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel2.add(JButton4,cons);
inset = new Insets(25,0,5,10);
setConstraints(1,1,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel2.add(JButton5,cons);
Top.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new CardLayout(5,5));
JPanel3.add(JPanel4,"Group");
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(GradientLabel1,BorderLayout.NORTH);
GradientLabel1.setLayout(new FlowLayout(1,5,5));
GradientLabel1.add(JLabel1);
JPanel4.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTable1);
JPanel3.add(JPanel41,"IP");
JPanel41.setLayout(new BorderLayout(5,5));
JPanel41.add(GradientLabel11,BorderLayout.NORTH);
GradientLabel11.setLayout(new FlowLayout(1,5,5));
GradientLabel11.add(JLabel12);
JPanel41.add(JScrollPane11,BorderLayout.CENTER);
JScrollPane11.getViewport().add(JTable11);
JPanel3.add(JPanel411,"GNE");
JPanel411.setLayout(new BorderLayout(5,5));
JPanel411.add(GradientLabel111,BorderLayout.NORTH);
GradientLabel111.setLayout(new FlowLayout(1,5,5));
GradientLabel111.add(JLabel121);
JPanel411.add(JScrollPane111,BorderLayout.CENTER);
JScrollPane111.getViewport().add(JTable111);
JPanel3.add(JPanel412,"TID");
JPanel412.setLayout(new BorderLayout(5,5));
JPanel412.add(GradientLabel112,BorderLayout.NORTH);
GradientLabel112.setLayout(new FlowLayout(1,5,5));
GradientLabel112.add(JLabel122);
JPanel412.add(JScrollPane112,BorderLayout.CENTER);
JScrollPane112.getViewport().add(JTable112);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
      if (!TL1MainScreen.isTL1)
      {
          return;
      }

		//<Begin_setUpConnections> 

      JTable111_JTable111_conn JTable111_JTable111_conn1 =  new JTable111_JTable111_conn();
      JTable111.addMouseListener(JTable111_JTable111_conn1);
      JButton1_JTable11_conn1 JButton1_JTable11_conn11 =  new JButton1_JTable11_conn1();
      JButton1.addActionListener(JButton1_JTable11_conn11);
      JButton1_JTable112_conn JButton1_JTable112_conn1 =  new JButton1_JTable112_conn();
      JButton1.addActionListener(JButton1_JTable112_conn1);
      JButton3_JTable1_conn1 JButton3_JTable1_conn11 =  new JButton3_JTable1_conn1();
      JButton3.addActionListener(JButton3_JTable1_conn11);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JButton5_JButton5_conn JButton5_JButton5_conn1 =  new JButton5_JButton5_conn();
      JButton5.addActionListener(JButton5_JButton5_conn1);
      JTable112_JTable112_conn JTable112_JTable112_conn1 =  new JTable112_JTable112_conn();
      JTable112.addMouseListener(JTable112_JTable112_conn1);
      JTable1_JTable1_conn1 JTable1_JTable1_conn11 =  new JTable1_JTable1_conn1();
      JTable1.addMouseListener(JTable1_JTable1_conn11);
      JTable11_JTable11_conn1 JTable11_JTable11_conn11 =  new JTable11_JTable11_conn1();
      JTable11.addMouseListener(JTable11_JTable11_conn11);
      JButton4_JButton4_conn1 JButton4_JButton4_conn11 =  new JButton4_JButton4_conn1();
      JButton4.addActionListener(JButton4_JButton4_conn11);
      JButton1_JTable111_conn JButton1_JTable111_conn1 =  new JButton1_JTable111_conn();
      JButton1.addActionListener(JButton1_JTable111_conn1);
  
      //<End_setUpConnections>
		TableSelectionConnection lis = new TableSelectionConnection();
		JTable1.getSelectionModel().addListSelectionListener(lis);
		JTable11.getSelectionModel().addListSelectionListener(lis);
		JTable111.getSelectionModel().addListSelectionListener(lis);
		JTable112.getSelectionModel().addListSelectionListener(lis);
	} 

    class TableSelectionConnection implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent evt)
		{
            ListSelectionModel source = (ListSelectionModel)evt.getSource();
            if(source.isSelectionEmpty())
			{
                JButton2.setEnabled(false);
				JButton3.setEnabled(false);
				if(JButton4.getActionCommand().equals("IP")&&JButton5.getActionCommand().equals("GNE")&& JTable1.getRowCount()==0)
                {
					JButton4.setEnabled(false);
					JButton5.setEnabled(false);
                }
                // else if((JButton5.getActionCommand().equalsIgnoreCase("GNE"))&& (JButton4.getActionCommand().equals("IP")))
                // {
					//JButton5.setEnabled(false);
                //}
				else 
                {
                    JButton4.setEnabled(true);
					JButton5.setEnabled(true);
                }
				
			}
			else
			{
			    JButton2.setEnabled(true);
			    JButton3.setEnabled(true);
			    JButton4.setEnabled(true);
			    JButton5.setEnabled(true);
			}
		}
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




	//<Begin__class_JButton4_JButton4_conn1>

 class JButton4_JButton4_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton4_JButton4_conn1>
     //Listener Interface Methods Are Added Below 


      public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         CardLayout cl = (CardLayout)JPanel3.getLayout();
         if(arg0.getActionCommand().equals("IP"))
         {
             int row = JTable1.getSelectedRow();
             String gname = JTable1.getValueAt(row,0).toString();
             setupIPTable(gname);   
             cl.show(JPanel3,"IP");
             JButton4.setText(resourceBundle.getString("<< Group List"));
             JButton4.setActionCommand("Group");
         } 
         else if(arg0.getActionCommand().equals("TID"))
         {
             int row = JTable111.getSelectedRow();
             if(row != -1)
             {
                 String gne = JTable111.getValueAt(row,0).toString();
                 String port = JTable111.getValueAt(row,2).toString();
                 DefaultTableModel gnemodel = (DefaultTableModel)JTable111.getModel();
                 setupTIDTable(gne,port,gnemodel);   
                 cl.show(JPanel3,"TID");
                 JButton4.setText(resourceBundle.getString("<< GNE List"));
                 JButton4.setActionCommand("GNE");
             }
             else
             {
                 //   JButton4.setEnabled(false);
             }
             
         }
         else if (arg0.getActionCommand().equals("GNE"))
         {
             cl.show(JPanel3,"GNE");   
             JButton4.setText(resourceBundle.getString("TID List >>"));
             JButton4.setActionCommand("TID");
         }
         else
         {
             cl.show(JPanel3,"Group");   
             JButton4.setText(resourceBundle.getString("IP List >>"));
             JButton4.setActionCommand("IP");
          }
     }
//<UserCode_End_Connection_JButton4_JButton4_conn1>
 }//<End__class_JButton4_JButton4_conn1>


	//<Begin__class_JButton3_JTable1_conn1>

 class JButton3_JTable1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JTable1_conn1>

      //Listener Interface Methods Are Added Below 
      public void actionPerformed( java.awt.event.ActionEvent arg0)
      {
          updateElementProperties();
      }
//<UserCode_End_Connection_JButton3_JTable1_conn1>
 }//<End__class_JButton3_JTable1_conn1>
    
	private void updateElementProperties()
	{
		if(JButton4.getActionCommand().equals("IP"))
		{
			int row = JTable1.getSelectedRow();
			if(row != -1)
            {
                Object prop = JTable1.getValueAt(row,7);
                gpDlg.setModify(true);
                showPropertiesForGroup(prop);
                if(gpDlg.isOkClicked())
                {
                    tyHlr.modifyGroup(row);
                }
            }
        }
        
        else if ( JButton4.getActionCommand().equals("TID"))
        {
            int row = JTable111.getSelectedRow();
            if(row != -1)
            {
		
                Hashtable prop = (Hashtable)JTable111.getValueAt(row,7);
                //Hashtable prop = cloneHash(p);
                //gneDlg.setModify(true);
                showPropertiesForGNE(prop);
                if(gneDlg.isOkClicked())
                {
                    tyHlr.modifyGNE((DefaultTableModel)JTable111.getModel(),row);
                }
		}
            
        }
        else if(JButton4.getActionCommand().equals("GNE"))
        {
            int row = JTable112.getSelectedRow();
	    if(row !=-1)
		{
           Hashtable p = (Hashtable)JTable112.getValueAt(row,7);
           p = cloneHash(p);
           //tidDlg.setModify(true);
           showPropertiesForTID(p);
	   if(tidDlg.isOkClicked())
	       {
		   tyHlr.modifyTID((DefaultTableModel)JTable112.getModel(),row);
	       }
		}
        }
		else
		{
			int row = JTable11.getSelectedRow();
			if(row != -1)
			    {
			Hashtable p = (Hashtable)JTable11.getValueAt(row,7);
			Hashtable prop = cloneHash(p);
			showPropertiesForIP(prop);
			if(ipDlg.isOkClicked())
			    {
				tyHlr.modifyIP((DefaultTableModel)JTable11.getModel(),row);
			    } 
			    }
        }
            
	}
	void showPropertiesForGroup(Object obj)
	{
		gpDlg.init();
        gpDlg.okClicked = false;
		gpDlg.showGroupDlg((Hashtable)obj);
		gpDlg.setLocationRelativeTo(this);
		gpDlg.setVisible(true);
	}


    void showPropertiesForGNE(Object obj)
	{
		gneDlg.init();
        gneDlg.okClicked = false;
		gneDlg.showGNEDlg((Hashtable)obj);
		gneDlg.setLocationRelativeTo(this);
		gneDlg.setVisible(true);
	}
    
    void showPropertiesForTID(Object obj)
	{
		tidDlg.init();
        tidDlg.okClicked = false;
		tidDlg.showTIDDlg((Hashtable)obj);
		tidDlg.setLocationRelativeTo(this);
		tidDlg.setVisible(true);
	}
	void showPropertiesForIP(Hashtable h)
	{
		ipDlg.init();
        ipDlg.okClicked = false;
		ipDlg.showDlg(h);
		ipDlg.setLocationRelativeTo(this);
		ipDlg.setVisible(true);
	}

	//<Begin__class_JButton1_JTable11_conn1>

    class JButton1_JTable11_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
    {
        
        //<TOP_PART>
        //<UserCode_Begin_Connection_JButton1_JTable11_conn1>
        
        //Listener Interface Methods Are Added Below 
        
        
        public void actionPerformed( java.awt.event.ActionEvent arg0)
        {
            if(JButton4.getActionCommand().equals("IP"))
            {
                gpDlg.setModify(false);
                Hashtable prop = tyHlr.getGlobalProperties();
                showPropertiesForGroup(prop);  
                if(gpDlg.isOkClicked())
                {
                    tyHlr.createNewGroup(prop);
                }
            }
            else if (JButton4.getActionCommand().equals("Group"))
            {
                String gname  = JTable1.getValueAt(JTable1.getSelectedRow(),0).toString();
                Hashtable p = (Hashtable)(tyHlr.getIPGroupProperties(gname,JTable1.getSelectedRow())).clone();
                Hashtable prop = (Hashtable)p.clone();
                showPropertiesForIP(cloneHash(prop));
                if(ipDlg.isOkClicked())
                {
                    tyHlr.createNewIP(prop,(DefaultTableModel)JTable11.getModel());
                }
            }
            
        }
        //<UserCode_End_Connection_JButton1_JTable11_conn1>
    }//<End__class_JButton1_JTable11_conn1>
    

	void removeIP()
	{
		int selIndex = JTable11.getSelectedRow();
        if( selIndex == -1 )
        {
            return;
        }
		tyHlr.removeIP((DefaultTableModel)JTable11.getModel(),selIndex);
		int newIndex = JTable11.getRowCount() - 1;        
		if(selIndex == -1)
		{
			if(newIndex != -1)
			{
				JTable11.setRowSelectionInterval(newIndex,newIndex);
			}
		}
		else
		{
			if(newIndex <= selIndex)
			{
				if(newIndex != -1)
				{
					JTable11.setRowSelectionInterval(newIndex,newIndex);
				}
				else
				{
					JButton2.setEnabled(false);
					JButton3.setEnabled(false);
					if(JButton4.getActionCommand().equals("IP"))
					{
						JButton4.setEnabled(false);
					}
				}
			}
			else
			{
				JTable11.setRowSelectionInterval(selIndex,selIndex);
			}
		}

	}

	void removeGroup()
	{
		int selIndex = JTable1.getSelectedRow();
        if( selIndex == -1 )
        {
            return;
        }
        String gname = JTable1.getValueAt(selIndex,0).toString();
        if(gname != null)
        {
            DefaultTableModel df = (DefaultTableModel)tyHlr.Gmap.remove(gname);
            // removing gne .
            if( df == null)
                return;
            for ( int h = 0; h < df.getRowCount();h++)
            {
                Hashtable top = (Hashtable)df.getValueAt(h, 7);
                String gneName = (String)top.get("ipaddress");
                String port = (String)top.get("tl1port");
                if(gneName != null  && port != null)
                {
                    tyHlr.Tmap.remove((gneName+":"+port+":"+gname));
                }
            }
            
        }
        
        tyHlr.removeGroup(selIndex);
        int newIndex = JTable1.getRowCount() - 1;        
		if(selIndex == -1)
		{
			if(newIndex != -1)
			{
				JTable1.setRowSelectionInterval(newIndex,newIndex);
			}
		}
		else
		{
			if(newIndex <= selIndex)
			{
				if(newIndex != -1)
				{
					JTable1.setRowSelectionInterval(newIndex,newIndex);
				}
				else
				{
					JButton2.setEnabled(false);
					JButton3.setEnabled(false);
					if(JButton4.getActionCommand().equals("IP"))
					{
						JButton4.setEnabled(false);
					}
				}
			}
			else
			{
				JTable1.setRowSelectionInterval(selIndex,selIndex);
			}
		}
	}

    void removeGNE()
    {
        int selIndex = JTable111.getSelectedRow();
        if(selIndex == -1 )
        {
            return;
        }
        String gne =  null;
        if( JTable111.getValueAt(selIndex,0) != null)
        {
            gne = JTable111.getValueAt(selIndex,0).toString();
        }
        String port =  null;
        if(JTable111.getValueAt(selIndex,2) != null)
        {
            port = JTable111.getValueAt(selIndex,2).toString();
        }   
        String group = (String)tyHlr.gvmodel.remove((DefaultTableModel)JTable111.getModel());
        if( gne != null && port != null && group != null)
        {
            tyHlr.Tmap.remove((gne+":"+port+":"+group));
        }
        tyHlr.removeGNE((DefaultTableModel)JTable111.getModel(),selIndex);
		int newIndex = JTable111.getRowCount() - 1;        
		if(selIndex == -1)
		{
			if(newIndex != -1)
			{
				JTable111.setRowSelectionInterval(newIndex,newIndex);
			}
		}
		else
		{
			if(newIndex <= selIndex)
			{
				if(newIndex != -1)
				{
					JTable111.setRowSelectionInterval(newIndex,newIndex);
				}
				else
				{
					JButton2.setEnabled(false);
					JButton3.setEnabled(false);
					if(JButton4.getActionCommand().equals("TID"))
					{
						JButton4.setEnabled(false);
						//have to be handled for TID and GNE.
					}
				}
			}
			else
			{
				JTable111.setRowSelectionInterval(selIndex,selIndex);
			}
		}
        
    }

    void removeTID()
    {
        int selIndex = JTable112.getSelectedRow();
        if( selIndex == -1 )
        {
            return;
        }
		tyHlr.removeTID((DefaultTableModel)JTable112.getModel(),selIndex);
		int newIndex = JTable112.getRowCount() - 1;        
		if(selIndex == -1)
		{
			if(newIndex != -1)
			{
				JTable112.setRowSelectionInterval(newIndex,newIndex);
			}
		}
		else
		{
			if(newIndex <= selIndex)
			{
				if(newIndex != -1)
				{
                    JTable112.setRowSelectionInterval(newIndex,newIndex);
				}
				else
				{
					JButton2.setEnabled(false);
					JButton3.setEnabled(false);
					if(JButton4.getActionCommand().equals("IP"))
					{
						JButton4.setEnabled(false);
                        // to be handled for GNE TID
					}
				}
			}
			else
			{
				JTable112.setRowSelectionInterval(selIndex,selIndex);
			}
		}

    }

	//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>

      //Listener Interface Methods Are Added Below 
      

     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         if(JButton4.getActionCommand().equals("IP"))
         {
             removeGroup();
         }
         else if(JButton4.getActionCommand().equals("TID"))
         {
             removeGNE();
         }
         else if(JButton4.getActionCommand().equals("GNE"))
         {
             removeTID();
         }
         else
         {
             removeIP();
         }   
         
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>	
	public static void main(String args[])
	{
		JFrame f = new JFrame();
		TL1TypeCfgtrScreen ty = new TL1TypeCfgtrScreen();
		f.setContentPane(ty);
		f.pack();
		f.setVisible(true);
	}


	public void saveChanges()
	{
        tyHlr.saveChanges();		
	}
//<Begin__class_JTable11_JTable11_conn1>

 class JTable11_JTable11_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTable11_JTable11_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
    {
        int i = arg0.getClickCount();
        if (i == 2)
        {
            updateElementProperties();
        }
    }
//<UserCode_End_Connection_JTable11_JTable11_conn1>
 }//<End__class_JTable11_JTable11_conn1>


	//<Begin__class_JTable1_JTable1_conn1>

 class JTable1_JTable1_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTable1_JTable1_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
         int i = arg0.getClickCount();
         if (i == 2)
         {
             updateElementProperties();
         }
     }
//<UserCode_End_Connection_JTable1_JTable1_conn1>
 }//<End__class_JTable1_JTable1_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }




//<Begin__class_JButton1_JTable112_conn>

 class JButton1_JTable112_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JTable112_conn>

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
      {
          if(JButton4.getActionCommand().equals("GNE"))
          {
              // tidDlg.setModify(false);
              //Hashtable prop = tyHlr.getGlobalProperties();
              Hashtable p = (Hashtable)(tyHlr.getTidGroupProperties((DefaultTableModel)JTable1.getModel(),JTable1.getSelectedRow()));
              Hashtable prop = (Hashtable)p.clone();
              showPropertiesForTID(cloneHash(prop));
              if(tidDlg.isOkClicked())
              {
                  tyHlr.createNewTID(prop,(DefaultTableModel)JTable112.getModel());
              }
              
          }

      }
//<UserCode_End_Connection_JButton1_JTable112_conn>
 }//<End__class_JButton1_JTable112_conn>

 


//<Begin__class_JTable111_JTable111_conn>

 class JTable111_JTable111_conn extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTable111_JTable111_conn>

     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
      {
          int i = arg0.getClickCount();
          if (i == 2)
          {
              updateElementProperties();
          }

     }
//<UserCode_End_Connection_JTable111_JTable111_conn>
 }//<End__class_JTable111_JTable111_conn>
//<Begin__class_JButton1_JTable111_conn>

 class JButton1_JTable111_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
      //<UserCode_Begin_Connection_JButton1_JTable111_conn>

     //Listener Interface Methods Are Added Below 


      public void actionPerformed( java.awt.event.ActionEvent arg0)
      {
          if(JButton4.getActionCommand().equals("TID"))
          {
              //gneDlg.setModify(false);
              //Hashtable prop = ((Hashtable)tyHlr.getGlobalProperties().clone());
              /*String gname  = JTable1.getValueAt(JTable1.getSelectedRow(),0).toString();
              Hashtable p = (Hashtable)(tyHlr.getGneGroupProperties((DefaultTableModel)JTable1.getModel(),JTable1.getSelectedRow()));
              Hashtable prop = (Hashtable)p.clone();
              showPropertiesForGNE(cloneHash(prop));  */
              Hashtable prop = new Hashtable();
              showPropertiesForGNE(prop);
              if(gneDlg.isOkClicked())
              {
                  tyHlr.createNewGNE(prop,(DefaultTableModel)JTable111.getModel());
              }
          }

      }
//<UserCode_End_Connection_JButton1_JTable111_conn>
 }//<End__class_JButton1_JTable111_conn>


//<Begin__class_JTable112_JTable112_conn>

 class JTable112_JTable112_conn extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTable112_JTable112_conn>

     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
      {
          int i = arg0.getClickCount();
          if (i == 2)
          {
              updateElementProperties();
          }
          
      }
//<UserCode_End_Connection_JTable112_JTable112_conn>
 }//<End__class_JTable112_JTable112_conn>


//<Begin__class_JButton5_JButton5_conn>

 class JButton5_JButton5_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton5_JButton5_conn>

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
           CardLayout cl = (CardLayout)JPanel3.getLayout();
            
            if(arg0.getActionCommand().equals("GNE"))
            {
                int row = JTable1.getSelectedRow();
                String gname = JTable1.getValueAt(row,0).toString();
                setupGNETable(gname);   
                cl.show(JPanel3,"GNE");
                JButton5.setText(resourceBundle.getString("<< Group List"));
                JButton5.setActionCommand("Group");
                JButton4.setText(resourceBundle.getString("TID List >>"));
                JButton4.setActionCommand("TID");
            } 
            else
         {
             cl.show(JPanel3,"Group");   
             JButton4.setText(resourceBundle.getString("IP List >>"));
             JButton4.setActionCommand("IP");
             JButton5.setText(resourceBundle.getString("GNE List >>"));
             JButton5.setActionCommand("GNE");
         }
         }
     
//<UserCode_End_Connection_JButton5_JButton5_conn>
 }//<End__class_JButton5_JButton5_conn>


    Hashtable cloneHash (Hashtable h)
    {
        // Hashtable h = (Hashtable)propToClone.clone();
        for (Enumeration e = h.keys(); e.hasMoreElements() ;) 
        {
            String key=(String)e.nextElement();
            if(key.equalsIgnoreCase("tidlist"))
		{
		    continue;
		}
            Object value = h.get(key);
            if(value instanceof Hashtable)
            {
                h.put(key,cloneHash((Hashtable)((Hashtable)value).clone()));
            }
            else if(value instanceof Vector)
            {
                Vector v = (Vector)value;
                Vector toStore = new Vector();
                for ( int vn = 0 ; vn < v.size();vn++)
                {
                    Object obj = v.elementAt(vn);
                    if(obj instanceof Hashtable)
                    {
                        toStore.addElement(cloneHash((Hashtable)((Hashtable)obj).clone()));
                    }
                    else
                    {
                        toStore.addElement(obj);
                    }
                } 
                
                h.put(key,toStore);
            }
          
            
        }
        return h;
    }


}







