//$Id: SelectPropsToView.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory




package com.adventnet.nms.tools.CustomView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;



public class SelectPropsToView extends JDialog implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton ok = null;
	javax.swing.JButton cancel = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel31 = null;
	javax.swing.JTextField nameField = null;
	javax.swing.JTextField statusField = null;
	javax.swing.JTextField isSnmpField = null;
	javax.swing.JTextField classNameField = null;
	javax.swing.JTextField pollIntervalField = null;
	javax.swing.JTextField statusChangeTimeField = null;
	javax.swing.JTextField uClassField = null;
	javax.swing.JTextField isRouterField = null;
	javax.swing.JTextField sysNameField = null;
	javax.swing.JTextField communityField = null;
	javax.swing.JTextField isNodeField = null;
	javax.swing.JCheckBox isNodeCheck = null;
	javax.swing.JCheckBox communityCheck = null;
	javax.swing.JCheckBox sysNameCheck = null;
	javax.swing.JCheckBox isRouterCheck = null;
	javax.swing.JCheckBox uClassCheck = null;
	javax.swing.JCheckBox statusChangeTimeCheck = null;
	javax.swing.JCheckBox pollIntervalCheck = null;
	javax.swing.JCheckBox classNameCheck = null;
	javax.swing.JCheckBox isSnmpCheck = null;
	javax.swing.JCheckBox statusCheck = null;
	javax.swing.JCheckBox nameCheck = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JTextField ipAddressField = null;
	javax.swing.JTextField typeField = null;
	javax.swing.JTextField netMaskField = null;
	javax.swing.JTextField managedField = null;
	javax.swing.JTextField statusUpdateTimeField = null;
	javax.swing.JTextField testerField = null;
	javax.swing.JTextField inUseField = null;
	javax.swing.JTextField sysOIDField = null;
	javax.swing.JTextField sysDescrField = null;
	javax.swing.JTextField isInterfaceField = null;
	javax.swing.JTextField isNetworkField = null;
	javax.swing.JCheckBox isNetworkCheck = null;
	javax.swing.JCheckBox isInterfaceCheck = null;
	javax.swing.JCheckBox sysDescrCheck = null;
	javax.swing.JCheckBox sysOIDCheck = null;
	javax.swing.JCheckBox inUseCheck = null;
	javax.swing.JCheckBox testerCheck = null;
	javax.swing.JCheckBox statusUpdateTimeCheck = null;
	javax.swing.JCheckBox managedCheck = null;
	javax.swing.JCheckBox netMaskCheck = null;
	javax.swing.JCheckBox typeCheck = null;
	javax.swing.JCheckBox ipAddressCheck = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JButton additionaltablecolumns = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	AdditionalCriteria criteria=null;
	Properties selectedColumns=null;
	Properties additionalColumns=null;
	Vector selectedProps=null;
	
  public SelectPropsToView()
  {

    //<Begin_SelectPropsToView>
    pack();
    this.setTitle("SelectPropsToView");
  
    //<End_SelectPropsToView>
	this.init();	
		
  }

  public SelectPropsToView(java.applet.Applet applet)
  {
    //<Begin_SelectPropsToView_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setTitle("SelectPropsToView");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_SelectPropsToView_java.applet.Applet>
  }


  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+552,getPreferredSize().height+539); 
          setTitle("Select Table Columns");
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 


  
        //<End_init>	
	this.setModal(true);
	Toolkit kit=Toolkit.getDefaultToolkit();
	Dimension dim=kit.getScreenSize();
	
	this.setLocation((dim.width-(int)this.getSize().getWidth())/2,(dim.height-(int)this.getSize().getHeight())/2);
	
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
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            ok.setFont(new Font("SansSerif",0,12));
            ok.setHorizontalTextPosition(4);
            ok.setText("OK");
            ok.setBorder(new javax.swing.border.BevelBorder(0));
            ok.setMaximumSize(new Dimension(43,27));
            ok.setMinimumSize(new Dimension(43,27));
            ok.setPreferredSize(new Dimension(43,27));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ok,ex); 
          }

          try
          {
            cancel.setFont(new Font("SansSerif",0,12));
            cancel.setHorizontalTextPosition(4);
            cancel.setText("Cancel");
            cancel.setBorder(new javax.swing.border.BevelBorder(0));
            cancel.setMaximumSize(new Dimension(43,27));
            cancel.setMinimumSize(new Dimension(43,27));
            cancel.setPreferredSize(new Dimension(43,27));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cancel,ex); 
          }

          try
          {
            JPanel4.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JPanel4,ex); 
          }

          try
          {
            nameField.setHorizontalAlignment(2);
            nameField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+nameField,ex); 
          }

          try
          {
            statusField.setHorizontalAlignment(2);
            statusField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statusField,ex); 
          }

          try
          {
            isSnmpField.setHorizontalAlignment(2);
            isSnmpField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isSnmpField,ex); 
          }

          try
          {
            classNameField.setHorizontalAlignment(2);
            classNameField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+classNameField,ex); 
          }

          try
          {
            pollIntervalField.setHorizontalAlignment(2);
            pollIntervalField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+pollIntervalField,ex); 
          }

          try
          {
            statusChangeTimeField.setHorizontalAlignment(2);
            statusChangeTimeField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statusChangeTimeField,ex); 
          }

          try
          {
            uClassField.setHorizontalAlignment(2);
            uClassField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+uClassField,ex); 
          }

          try
          {
            isRouterField.setHorizontalAlignment(2);
            isRouterField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isRouterField,ex); 
          }

          try
          {
            sysNameField.setHorizontalAlignment(2);
            sysNameField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysNameField,ex); 
          }

          try
          {
            communityField.setHorizontalAlignment(2);
            communityField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+communityField,ex); 
          }

          try
          {
            isNodeField.setHorizontalAlignment(2);
            isNodeField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isNodeField,ex); 
          }

          try
          {
            isNodeCheck.setFont(new Font("SansSerif",0,12));
            isNodeCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isNodeCheck,ex); 
          }

          try
          {
            communityCheck.setFont(new Font("SansSerif",0,12));
            communityCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+communityCheck,ex); 
          }

          try
          {
            sysNameCheck.setFont(new Font("SansSerif",0,12));
            sysNameCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysNameCheck,ex); 
          }

          try
          {
            isRouterCheck.setFont(new Font("SansSerif",0,12));
            isRouterCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isRouterCheck,ex); 
          }

          try
          {
            uClassCheck.setFont(new Font("SansSerif",0,12));
            uClassCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+uClassCheck,ex); 
          }

          try
          {
            statusChangeTimeCheck.setFont(new Font("SansSerif",0,12));
            statusChangeTimeCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statusChangeTimeCheck,ex); 
          }

          try
          {
            pollIntervalCheck.setFont(new Font("SansSerif",0,12));
            pollIntervalCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+pollIntervalCheck,ex); 
          }

          try
          {
            classNameCheck.setFont(new Font("SansSerif",0,12));
            classNameCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+classNameCheck,ex); 
          }

          try
          {
            isSnmpCheck.setFont(new Font("SansSerif",0,12));
            isSnmpCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isSnmpCheck,ex); 
          }

          try
          {
            statusCheck.setFont(new Font("SansSerif",0,12));
            statusCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statusCheck,ex); 
          }

          try
          {
            nameCheck.setFont(new Font("SansSerif",0,12));
            nameCheck.setHorizontalTextPosition(4);
            nameCheck.setEnabled(false);
            nameCheck.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+nameCheck,ex); 
          }

          try
          {
            ipAddressField.setHorizontalAlignment(2);
            ipAddressField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ipAddressField,ex); 
          }

          try
          {
            typeField.setHorizontalAlignment(2);
            typeField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+typeField,ex); 
          }

          try
          {
            netMaskField.setHorizontalAlignment(2);
            netMaskField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+netMaskField,ex); 
          }

          try
          {
            managedField.setHorizontalAlignment(2);
            managedField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+managedField,ex); 
          }

          try
          {
            statusUpdateTimeField.setHorizontalAlignment(2);
            statusUpdateTimeField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statusUpdateTimeField,ex); 
          }

          try
          {
            testerField.setHorizontalAlignment(2);
            testerField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+testerField,ex); 
          }

          try
          {
            inUseField.setHorizontalAlignment(2);
            inUseField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+inUseField,ex); 
          }

          try
          {
            sysOIDField.setHorizontalAlignment(2);
            sysOIDField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysOIDField,ex); 
          }

          try
          {
            sysDescrField.setHorizontalAlignment(2);
            sysDescrField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysDescrField,ex); 
          }

          try
          {
            isInterfaceField.setHorizontalAlignment(2);
            isInterfaceField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isInterfaceField,ex); 
          }

          try
          {
            isNetworkField.setHorizontalAlignment(2);
            isNetworkField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isNetworkField,ex); 
          }

          try
          {
            isNetworkCheck.setFont(new Font("SansSerif",0,12));
            isNetworkCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isNetworkCheck,ex); 
          }

          try
          {
            isInterfaceCheck.setFont(new Font("SansSerif",0,12));
            isInterfaceCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isInterfaceCheck,ex); 
          }

          try
          {
            sysDescrCheck.setFont(new Font("SansSerif",0,12));
            sysDescrCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysDescrCheck,ex); 
          }

          try
          {
            sysOIDCheck.setFont(new Font("SansSerif",0,12));
            sysOIDCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysOIDCheck,ex); 
          }

          try
          {
            inUseCheck.setFont(new Font("SansSerif",0,12));
            inUseCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+inUseCheck,ex); 
          }

          try
          {
            testerCheck.setFont(new Font("SansSerif",0,12));
            testerCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+testerCheck,ex); 
          }

          try
          {
            statusUpdateTimeCheck.setFont(new Font("SansSerif",0,12));
            statusUpdateTimeCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statusUpdateTimeCheck,ex); 
          }

          try
          {
            managedCheck.setFont(new Font("SansSerif",0,12));
            managedCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+managedCheck,ex); 
          }

          try
          {
            netMaskCheck.setFont(new Font("SansSerif",0,12));
            netMaskCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+netMaskCheck,ex); 
          }

          try
          {
            typeCheck.setFont(new Font("SansSerif",0,12));
            typeCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+typeCheck,ex); 
          }

          try
          {
            ipAddressCheck.setFont(new Font("SansSerif",0,12));
            ipAddressCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ipAddressCheck,ex); 
          }

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

          try
          {
            additionaltablecolumns.setFont(new Font("SansSerif",0,12));
            additionaltablecolumns.setHorizontalTextPosition(4);
            additionaltablecolumns.setText("Additional table Columns");
            additionaltablecolumns.setBorder(new javax.swing.border.BevelBorder(0));
            additionaltablecolumns.setMaximumSize(new Dimension(150,27));
            additionaltablecolumns.setMinimumSize(new Dimension(150,27));
            additionaltablecolumns.setPreferredSize(new Dimension(150,27));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+additionaltablecolumns,ex); 
          }
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+438,JPanel1.getPreferredSize().height+59));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+10,JPanel4.getPreferredSize().height+10));
		cancel.setPreferredSize(new Dimension(cancel.getPreferredSize().width+30,cancel.getPreferredSize().height+0));
		ok.setPreferredSize(new Dimension(ok.getPreferredSize().width+30,ok.getPreferredSize().height+0));

  
          //<End_setUpProperties>
  } 
  public void start()
  {
  //<Begin_start> 

  
  //<End_start>
  } 
  public void stop()
  {
  //<Begin_stop> 

  
  //<End_stop>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        ok= new javax.swing.JButton();
        cancel= new javax.swing.JButton();
        JPanel4= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel31= new javax.swing.JPanel();
        nameField= new javax.swing.JTextField();
        statusField= new javax.swing.JTextField();
        isSnmpField= new javax.swing.JTextField();
        classNameField= new javax.swing.JTextField();
        pollIntervalField= new javax.swing.JTextField();
        statusChangeTimeField= new javax.swing.JTextField();
        uClassField= new javax.swing.JTextField();
        isRouterField= new javax.swing.JTextField();
        sysNameField= new javax.swing.JTextField();
        communityField= new javax.swing.JTextField();
        isNodeField= new javax.swing.JTextField();
        isNodeCheck= new javax.swing.JCheckBox();
        communityCheck= new javax.swing.JCheckBox();
        sysNameCheck= new javax.swing.JCheckBox();
        isRouterCheck= new javax.swing.JCheckBox();
        uClassCheck= new javax.swing.JCheckBox();
        statusChangeTimeCheck= new javax.swing.JCheckBox();
        pollIntervalCheck= new javax.swing.JCheckBox();
        classNameCheck= new javax.swing.JCheckBox();
        isSnmpCheck= new javax.swing.JCheckBox();
        statusCheck= new javax.swing.JCheckBox();
        nameCheck= new javax.swing.JCheckBox();
        JPanel3= new javax.swing.JPanel();
        ipAddressField= new javax.swing.JTextField();
        typeField= new javax.swing.JTextField();
        netMaskField= new javax.swing.JTextField();
        managedField= new javax.swing.JTextField();
        statusUpdateTimeField= new javax.swing.JTextField();
        testerField= new javax.swing.JTextField();
        inUseField= new javax.swing.JTextField();
        sysOIDField= new javax.swing.JTextField();
        sysDescrField= new javax.swing.JTextField();
        isInterfaceField= new javax.swing.JTextField();
        isNetworkField= new javax.swing.JTextField();
        isNetworkCheck= new javax.swing.JCheckBox();
        isInterfaceCheck= new javax.swing.JCheckBox();
        sysDescrCheck= new javax.swing.JCheckBox();
        sysOIDCheck= new javax.swing.JCheckBox();
        inUseCheck= new javax.swing.JCheckBox();
        testerCheck= new javax.swing.JCheckBox();
        statusUpdateTimeCheck= new javax.swing.JCheckBox();
        managedCheck= new javax.swing.JCheckBox();
        netMaskCheck= new javax.swing.JCheckBox();
        typeCheck= new javax.swing.JCheckBox();
        ipAddressCheck= new javax.swing.JCheckBox();
        JPanel5= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        additionaltablecolumns= new javax.swing.JButton();

  
        //<End_initVariables>
	additionaltablecolumns.addActionListener(this);
	ok.addActionListener(this);
	cancel.addActionListener(this);
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(1,5,5));
JPanel2.add(ok);
JPanel2.add(cancel);
Top.add(JPanel4,BorderLayout.CENTER);
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridLayout(1,1,5,5));
JPanel1.add(JPanel31);
JPanel31.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel31.add(nameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel31.add(statusField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel31.add(isSnmpField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel31.add(classNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel31.add(pollIntervalField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel31.add(statusChangeTimeField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel31.add(uClassField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,7,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel31.add(isRouterField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,8,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel31.add(sysNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,9,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel31.add(communityField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,10,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel31.add(isNodeField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,10,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel31.add(isNodeCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,9,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel31.add(communityCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,8,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel31.add(sysNameCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel31.add(isRouterCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel31.add(uClassCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel31.add(statusChangeTimeCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel31.add(pollIntervalCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel31.add(classNameCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel31.add(isSnmpCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel31.add(statusCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel31.add(nameCheck,cons);
JPanel1.add(JPanel3);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(ipAddressField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(typeField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(netMaskField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(managedField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(statusUpdateTimeField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(testerField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(inUseField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,7,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(sysOIDField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,8,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(sysDescrField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,9,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(isInterfaceField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,10,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(isNetworkField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,10,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(isNetworkCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,9,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(isInterfaceCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,8,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(sysDescrCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(sysOIDCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(inUseCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(testerCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(statusUpdateTimeCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(managedCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(netMaskCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(typeCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(ipAddressCheck,cons);
JPanel4.add(JPanel5,BorderLayout.SOUTH);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JLabel1,cons);
inset = new Insets(10,0,10,10);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel5.add(additionaltablecolumns,cons);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
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
public void actionPerformed(ActionEvent e)
{
	if(e.getSource().equals(additionaltablecolumns))
	{
		if(criteria==null)
		{
			criteria=new AdditionalCriteria("Additional Table Columns",additionalColumns,selectedProps);
		}
		else
		{
			Properties prop=criteria.getProps();
			criteria=new AdditionalCriteria("Additional Table Columns",prop,null);	
		}	
		criteria.setVisible(true);
	}
		
	if(e.getSource().equals(ok))
	{
			selectedColumns=getSelectedColumns();	
			this.setVisible(false);
	}
	if(e.getSource().equals(cancel))
	{
			this.setVisible(false);
	}	
			
}
	
public Properties getColumns()
{
	return selectedColumns;		
}
	public void setProperties(Properties displayName,Properties checkedColumns)
	{
		setDisplayNames(displayName);
		setSelection(checkedColumns);
	
	}
	public void setDisplayNames(Properties displayName)
	{
		Vector vec=new Vector();
		if(displayName.get("name")!=null)
		{
			nameField.setText((String)displayName.get("name"));
			vec.add("name");
		}
		if(displayName.get("status")!=null)
		{
			statusField.setText((String)displayName.get("status"));
			vec.add("status");
		}
		if(displayName.get("isSNMP")!=null)
		{
			isSnmpField.setText((String)displayName.get("isSNMP"));
			vec.add("isSNMP");
		}
		if(displayName.get("classname")!=null)
		{
			classNameField.setText((String)displayName.get("classname"));
			vec.add("classname");
		}
		if(displayName.get("pollInterval")!=null)
		{
			pollIntervalField.setText((String)displayName.get("pollInterval"));
			vec.add("pollInterval");
		}
		if(displayName.get("statusChangeTime")!=null)
		{
			statusChangeTimeField.setText((String)displayName.get("statusChangeTime"));
			vec.add("statusChangeTime");
		}
		if(displayName.get("uClass")!=null)
		{
			uClassField.setText((String)displayName.get("uClass"));
			vec.add("uClass");
		}
		if(displayName.get("isRouter")!=null)
		{
			isRouterField.setText((String)displayName.get("isRouter"));
			vec.add("isRouter");
		}
		if(displayName.get("sysName")!=null)
		{
			sysNameField.setText((String)displayName.get("sysName"));
			vec.add("sysName");
		}
		if(displayName.get("community")!=null)
		{							
			communityField.setText((String)displayName.get("community"));
			vec.add("community");
		}
		if(displayName.get("isNode")!=null)
		{
			isNodeField.setText((String)displayName.get("isNode"));
			vec.add("isNode");
		}
		if(displayName.get("ipAddress")!=null)
		{
			ipAddressField.setText((String)displayName.get("ipAddress"));	
			displayName.remove("ipAddress");
		}
		if(displayName.get("type")!=null)
		{
			typeField.setText((String)displayName.get("type"));
			vec.add("type");
		}
		if(displayName.get("netmask")!=null)
		{
			netMaskField.setText((String)displayName.get("netmask"));
			vec.add("netmask");
		}
		if(displayName.get("managed")!=null)
		{
			managedField.setText((String)displayName.get("managed"));
			vec.add("managed");
		}
		if(displayName.get("statusUpdateTime")!=null)
		{
			statusUpdateTimeField.setText((String)displayName.get("statusUpdateTime"));
			vec.add("statusUpdateTime");
		}
		if(displayName.get("tester")!=null)
		{
			testerField.setText((String)displayName.get("tester"));
			vec.add("tester");
		}
		if(displayName.get("inUse")!=null)
		{
			inUseField.setText((String)displayName.get("inUse"));
			vec.add("inUse");
		}																
		if(displayName.get("sysOID")!=null)
		{
			sysOIDField.setText((String)displayName.get("sysOID"));
			vec.add("sysOID");
		}
		if(displayName.get("sysDescr")!=null)
		{
			sysDescrField.setText((String)displayName.get("sysDescr"));
			vec.add("sysDescr");
		}
		if(displayName.get("isInterface")!=null)
		{
			isInterfaceField.setText((String)displayName.get("isInterface"));
			vec.add("isInterface");
		}
		if(displayName.get("isNetwork")!=null)
		{
			isNetworkField.setText((String)displayName.get("isNetwork"));
			vec.add("isNetwork");
		}
		additionalColumns=displayName;	
		selectedProps=vec;
	}
	public void setSelection(Properties checkedColumns)
	{
		selectedColumns=checkedColumns;
		if(checkedColumns.get("status")!=null)
		{
			statusCheck.setSelected(true);
		}
		else
		{
			statusCheck.setSelected(false);
		}
		if(checkedColumns.get("isSNMP")!=null)
		{
			isSnmpCheck.setSelected(true);
		}
		else
		{
			isSnmpCheck.setSelected(false);
		}
		if(checkedColumns.get("classname")!=null)
		{
			classNameCheck.setSelected(true);
		}
		else
		{
			classNameCheck.setSelected(false);
		}
		if(checkedColumns.get("pollInterval")!=null )
		{
			pollIntervalCheck.setSelected(true);
		}
		else
		{
			pollIntervalCheck.setSelected(false);
		}
		if(checkedColumns.get("statusChangeTime")!=null)
		{
			statusChangeTimeCheck.setSelected(true);
		}
		else
		{
			statusChangeTimeCheck.setSelected(false);
		}
		if(checkedColumns.get("uClass")!=null)
		{
			uClassCheck.setSelected(true);
		}
		else
		{
			uClassCheck.setSelected(false);
		}
		if(checkedColumns.get("isRouter")!=null)
		{
			isRouterCheck.setSelected(true);
		}
		else
		{
			isRouterCheck.setSelected(false);
		}
		if(checkedColumns.get("sysName")!=null)
		{
			sysNameCheck.setSelected(true);
		}
		else
		{
			sysNameCheck.setSelected(false);
		}
		if(checkedColumns.get("community")!=null)
		{
			communityCheck.setSelected(true);
		}
		else
		{
			communityCheck.setSelected(false);
		}
		if(checkedColumns.get("isNode")!=null)
		{
			isNodeCheck.setSelected(true);
		}
		else
		{
			isNodeCheck.setSelected(false);
		}
		if(checkedColumns.get("isNetwork")!=null)
		{
			isNetworkCheck.setSelected(true);
		}
		else
		{
			isNetworkCheck.setSelected(false);
		}
		if(checkedColumns.get("isInterface")!=null)
		{
			isInterfaceCheck.setSelected(true);
		}
		else
		{
			isInterfaceCheck.setSelected(false);
		}
		if(checkedColumns.get("sysDescr")!=null)
		{
			sysDescrCheck.setSelected(true);
		}
		else
		{
			sysDescrCheck.setSelected(false);
		}
		if(checkedColumns.get("sysOID")!=null)
		{
			sysOIDCheck.setSelected(true);
		}
		else
		{
			sysOIDCheck.setSelected(false);
		}
		if(checkedColumns.get("inUse")!=null)
		{
			inUseCheck.setSelected(true);
		}
		else
		{
			inUseCheck.setSelected(false);
		}
		if(checkedColumns.get("tester")!=null)
		{
			testerCheck.setSelected(true);
		}
		else
		{
			testerCheck.setSelected(false);
		}
		if(checkedColumns.get("statusUpdateTime")!=null)
		{
			statusUpdateTimeCheck.setSelected(true);
		}
		else
		{
			statusUpdateTimeCheck.setSelected(false);
		}
		if(checkedColumns.get("managed")!=null)
		{
			managedCheck.setSelected(true);
		}
		else
		{
			managedCheck.setSelected(false);
		}
		if(checkedColumns.get("netmask")!=null)
		{
			netMaskCheck.setSelected(true);
		}
		else
		{
			netMaskCheck.setSelected(false);
		}
		if(checkedColumns.get("type")!=null)
		{
			typeCheck.setSelected(true);
		}
		else
		{
			typeCheck.setSelected(false);
		}
		if(checkedColumns.get("ipAddress")!=null)
		{
			ipAddressCheck.setSelected(true);
		}
		else
		{
			ipAddressCheck.setSelected(false);
		}
	}
	public Properties getSelectedColumns()
	{
		selectedColumns=new Properties();
		if(isNodeCheck.isSelected())
		{
			selectedColumns.put("isNode",isNodeField.getText());
		}
		if(communityCheck.isSelected())
		{
			selectedColumns.put("community",communityField.getText());
		}
		if(sysNameCheck.isSelected())
		{
			selectedColumns.put("sysName",sysNameField.getText());
		}

		if(isRouterCheck.isSelected())
		{
			selectedColumns.put("isRouter",isRouterField.getText());
		}
		if(uClassCheck.isSelected())
		{
			selectedColumns.put("uClass",uClassField.getText());
		}
		if(statusChangeTimeCheck.isSelected())
		{
			selectedColumns.put("statusChangeTime",statusChangeTimeField.getText());
		}
		if(pollIntervalCheck.isSelected())
		{
			selectedColumns.put("pollInterval",pollIntervalField.getText());
		}
		if(classNameCheck.isSelected())
		{
			selectedColumns.put("classname",classNameField.getText());
		}
		if(isSnmpCheck.isSelected())
		{
			selectedColumns.put("isSNMP",isSnmpField.getText());
		}
		if(statusCheck.isSelected())
		{
			selectedColumns.put("status",statusField.getText());
		}
		if(nameCheck.isSelected())
		{
			selectedColumns.put("name",nameField.getText());
		}
		if(isNetworkCheck.isSelected())
		{
			selectedColumns.put("isNetwork",isNetworkField.getText());
		}
		if(isInterfaceCheck.isSelected())
		{
			selectedColumns.put("isInterface",isInterfaceField.getText());
		}
		if(sysDescrCheck.isSelected())
		{
			selectedColumns.put("sysDescr",sysDescrField.getText());
		}
		if(sysOIDCheck.isSelected())
		{
			selectedColumns.put("sysOID",sysOIDField.getText());
		}
		if(inUseCheck.isSelected())
		{
			selectedColumns.put("inUse",inUseField.getText());
		}
		if(testerCheck.isSelected())
		{
			selectedColumns.put("tester",testerField.getText());
		}
		if(statusUpdateTimeCheck.isSelected())
		{
			selectedColumns.put("statusUpdateTime",statusUpdateTimeField.getText());
		}
		if(managedCheck.isSelected())
		{
			selectedColumns.put("managed",managedField.getText());
		}
		if(netMaskCheck.isSelected())
		{
			selectedColumns.put("netmask",netMaskField.getText());
		}
		if(typeCheck.isSelected())
		{
			selectedColumns.put("type",typeField.getText());
		}
		if(ipAddressCheck.isSelected())
		{
			selectedColumns.put("ipAddress",ipAddressField.getText());
		}
		if(criteria!=null  && criteria.getProps().size()>0)
		{
	       		 Properties properties=criteria.getProps();
		          for(Enumeration e=properties.propertyNames();e.hasMoreElements();)
	        		{
		  		 String str=(String)e.nextElement();
				 selectedColumns.put(str,properties.get(str));
	        		}
		}
		return selectedColumns;
	}
}












