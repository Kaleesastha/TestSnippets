
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.examples.routermap;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import com.adventnet.nms.util.*;

public class RouterDetails extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  JPanel1 = null;
	javax.swing.JLabel  SnmpVerLbl = null;
	javax.swing.JLabel  SysContLbl = null;
	javax.swing.JLabel  SysOidLbl = null;
	javax.swing.JLabel  SysDescrLbl = null;
	javax.swing.JLabel  DnsNameLbl = null;
	javax.swing.JTextField  DNSText = null;
	javax.swing.JTextField  SysDText = null;
	javax.swing.JTextField  SysOidText = null;
	javax.swing.JComboBox  SysCText = null;
	javax.swing.JTextField  SnmpVText = null;
	javax.swing.JLabel  snmpPortlbl = null;
	javax.swing.JTextField  snmpportText = null;
	javax.swing.JPanel  JPanel2 = null;
	javax.swing.JPanel  v1v2panel = null;
	javax.swing.JLabel  JLabel1 = null;
	javax.swing.JLabel  JLabel2 = null;
	javax.swing.JTextField  CommText = null;
	javax.swing.JTextField  WCommText = null;
	javax.swing.JPanel  JPanel3 = null;
	javax.swing.JLabel  JLabel3 = null;
	javax.swing.JLabel  JLabel4 = null;
	javax.swing.JTextField  CNameText = null;
	javax.swing.JTextField  UNameText = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


  
  
  
   


  public RouterDetails()
  {
    //<Begin_RouterDetails>
    this.init();
  
    //<End_RouterDetails>
  }

  public RouterDetails(java.applet.Applet applet)
  {
    //<Begin_RouterDetails_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_RouterDetails_java.applet.Applet>
  }

 
    public void start()
  { 

  //<Begin_start>

  //<End_start>
  } 
  public void initVariables()throws Exception
  { 

  //<Begin_initVariables>
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        SnmpVerLbl= new javax.swing.JLabel();
        SysContLbl= new javax.swing.JLabel();
        SysOidLbl= new javax.swing.JLabel();
        SysDescrLbl= new javax.swing.JLabel();
        DnsNameLbl= new javax.swing.JLabel();
        DNSText= new javax.swing.JTextField();
        SysDText= new javax.swing.JTextField();
        SysOidText= new javax.swing.JTextField();
        SysCText= new javax.swing.JComboBox();
        SnmpVText= new javax.swing.JTextField();
        snmpPortlbl= new javax.swing.JLabel();
        snmpportText= new javax.swing.JTextField();
        JPanel2= new javax.swing.JPanel();
        v1v2panel= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
        CommText= new javax.swing.JTextField();
        WCommText= new javax.swing.JTextField();
        JPanel3= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        CNameText= new javax.swing.JTextField();
        UNameText= new javax.swing.JTextField();

  //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)throws Exception
  { 

  //<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new BorderLayout(5,5));
	Top.add(JPanel1,BorderLayout.CENTER);
	JPanel1.setLayout(new GridBagLayout());
	inset = new Insets(5,5,10,10);
	setConstraints(0,5,1,1,0.0,0.1,cons.NORTHWEST,cons.NONE,inset,0,0);
	JPanel1.add(SnmpVerLbl,cons);
	inset = new Insets(5,5,10,10);
	setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	JPanel1.add(SysContLbl,cons);
	inset = new Insets(5,5,10,10);
	setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	JPanel1.add(SysOidLbl,cons);
	inset = new Insets(5,5,10,10);
	setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	JPanel1.add(SysDescrLbl,cons);
	inset = new Insets(20,5,10,10);
	setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	JPanel1.add(DnsNameLbl,cons);
	inset = new Insets(20,5,10,20);
	setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	JPanel1.add(DNSText,cons);
	inset = new Insets(5,5,10,20);
	setConstraints(1,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	JPanel1.add(SysDText,cons);
	inset = new Insets(5,5,10,20);
	setConstraints(1,2,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	JPanel1.add(SysOidText,cons);
	inset = new Insets(5,5,10,20);
	setConstraints(1,3,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	JPanel1.add(SysCText,cons);
	inset = new Insets(5,5,0,20);
	setConstraints(1,5,1,1,0.1,0.1,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	JPanel1.add(SnmpVText,cons);
	inset = new Insets(5,5,10,10);
	setConstraints(0,4,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	JPanel1.add(snmpPortlbl,cons);
	inset = new Insets(5,5,10,20);
	setConstraints(1,4,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	JPanel1.add(snmpportText,cons);
	Top.add(JPanel2,BorderLayout.SOUTH);
	JPanel2.setLayout(new CardLayout(5,5));
	JPanel2.add(v1v2panel,"v1v2panel");//No Internationalisation
	v1v2panel.setLayout(new GridBagLayout());
	inset = new Insets(5,5,5,5);
	setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	v1v2panel.add(JLabel1,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	v1v2panel.add(JLabel2,cons);
	inset = new Insets(5,15,5,15);
	setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	v1v2panel.add(CommText,cons);
	inset = new Insets(5,15,5,15);
	setConstraints(1,1,1,1,0.1,0.1,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	v1v2panel.add(WCommText,cons);
	JPanel2.add(JPanel3,"JPanel3");//No Internationalisation
	JPanel3.setLayout(new GridBagLayout());
	inset = new Insets(5,0,5,5);
	setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	JPanel3.add(JLabel3,cons);
	inset = new Insets(5,0,5,5);
	setConstraints(0,1,1,1,0.0,0.1,cons.NORTHWEST,cons.NONE,inset,0,0);
	JPanel3.add(JLabel4,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	JPanel3.add(CNameText,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(1,1,1,1,0.1,0.1,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	JPanel3.add(UNameText,cons);
	
  //<End_setUpGUI_Container>
  } 
  public void setUpProperties()throws Exception
  { 

  //<Begin_setUpProperties>

          try
          {
            Top.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+Top,ex); 
          }

          try
          {
            JPanel1.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

          try
          {
            SnmpVerLbl.setForeground(new Color(-16764109));
            SnmpVerLbl.setText(NmsClientUtil.GetString("  SNMP Version"));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+SnmpVerLbl,ex); 
          }

          try
          {
            SysContLbl.setForeground(new Color(-16764109));
            SysContLbl.setText(NmsClientUtil.GetString("  Interface List"));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+SysContLbl,ex); 
          }

          try
          {
            SysOidLbl.setForeground(new Color(-16764109));
            SysOidLbl.setText(NmsClientUtil.GetString("  System Object ID"));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+SysOidLbl,ex); 
          }

          try
          {
            SysDescrLbl.setHorizontalAlignment(10);
            SysDescrLbl.setForeground(new Color(-16764109));
            SysDescrLbl.setText(NmsClientUtil.GetString("  System Description"));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+SysDescrLbl,ex); 
          }

          try
          {
            DnsNameLbl.setHorizontalAlignment(10);
            DnsNameLbl.setForeground(new Color(-16764109));
            DnsNameLbl.setText(NmsClientUtil.GetString("  DNS Name"));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+DnsNameLbl,ex); 
          }

          try
          {
            DNSText.setBackground(new Color(-13108));
            DNSText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+DNSText,ex); 
          }

          try
          {
            SysDText.setBackground(new Color(-13108));
            SysDText.setEditable(false);
            SysDText.setColumns(100);
            SysDText.setAutoscrolls(true);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+SysDText,ex); 
          }

          try
          {
            SysOidText.setBackground(new Color(-13108));
            SysOidText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+SysOidText,ex); 
          }

          try
          {
            SysCText.setBackground(new Color(-13108));
            SysCText.setFont(new Font("Dialog",0,12));//No Internationalisation
            SysCText.setDoubleBuffered(true);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+SysCText,ex); 
          }

          try
          {
            SnmpVText.setBackground(new Color(-13108));
            SnmpVText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+SnmpVText,ex); 
          }

          try
          {
            snmpPortlbl.setForeground(new Color(-16250872));
            snmpPortlbl.setText(NmsClientUtil.GetString("  Snmp Port"));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+snmpPortlbl,ex); 
          }

          try
          {
            snmpportText.setBackground(new Color(-13108));
            snmpportText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+snmpportText,ex); 
          }

          try
          {
            JPanel2.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

          try
          {
            v1v2panel.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+v1v2panel,ex); 
          }

          try
          {
            JLabel1.setForeground(new Color(-16119543));
            JLabel1.setText(NmsClientUtil.GetString("Community  "));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

          try
          {
            JLabel2.setForeground(new Color(-16053750));
            JLabel2.setText(NmsClientUtil.GetString("Write Community   "));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

          try
          {
            CommText.setBackground(new Color(-13108));
            CommText.setFont(new Font("Dialog",0,12));//No Internationalisation
            CommText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+CommText,ex); 
          }

          try
          {
            WCommText.setBackground(new Color(-13108));
            WCommText.setEnabled(true);
            WCommText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+WCommText,ex); 
          }

          try
          {
            JPanel3.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

          try
          {
            JLabel3.setText(NmsClientUtil.GetString("Context Name       "));
            JLabel3.setForeground(new Color(-15790578));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

          try
          {
            JLabel4.setText(NmsClientUtil.GetString("User Name"));
            JLabel4.setForeground(new Color(-16053750));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

          try
          {
            CNameText.setBackground(new Color(-13108));
            CNameText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+CNameText,ex); 
          }

          try
          {
            UNameText.setBackground(new Color(-13108));
            UNameText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+UNameText,ex); 
          }
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+276,JPanel2.getPreferredSize().height+0));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+0,JPanel1.getPreferredSize().height+1));

  //<End_setUpProperties>
  } 
  public void setUpConnections()throws Exception
  { 

  //<Begin_setUpConnections>

      SysCText_JPanel2_conn1 SysCText_JPanel2_conn11 =  new SysCText_JPanel2_conn1();
      SysCText.addMouseListener(SysCText_JPanel2_conn11);
  //<End_setUpConnections>
  } 
  public void stop()
  { 

  //<Begin_stop>

  //<End_stop>
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
            if (input.equals("HOST")) value = "localhost"; //No Internationalisation
            if (input.equals("PORT")) value = "161"; //No Internationalisation
            }
        return value;

  //<End_getParameter_String>
  } 
  public void init()
  { 

  //<Begin_init>
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+491,getPreferredSize().height+298)); 
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
          showStatus(NmsClientUtil.GetString("Error in init method"),ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
      setUpMenus();
      setUpToolBar();
        

  //<End_init>

	SysCText.setLightWeightPopupEnabled(true);
  } 

     

  
  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println(NmsClientUtil.GetString("Internal Error :")+ message);
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println(NmsClientUtil.GetString("Internal Error :")+ message);
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

public void setValues( Properties prop )
	{
		SysCText.removeAllItems();
           	DNSText.setText(prop.getProperty("name"));//No Internationalisation
		String str = prop.getProperty("sysDescr");//No Internationalisation
		String temp="";//No Internationalisation

		for(int i=0;i < str.length();i++)	
			{
				if(str.charAt(i) == '\n')
					{
					  continue;
					}
				temp +=str.charAt(i) +"";//No Internationalisation
			}

	 	   SysDText.setText(temp);
		   SysOidText.setText(prop.getProperty("sysOID"));//No Internationalisation
            	   String ifList = prop.getProperty("InterfaceList");//No Internationalisation
		   StringTokenizer stk = new StringTokenizer(ifList," ");//No Internationalisation
		   	
		   while(stk.hasMoreElements())
			{
		   SysCText.addItem(stk.nextElement()); 	
			}
                         snmpportText.setText(prop.getProperty("snmpport"));//No Internationalisation

		   SnmpVText.setText(prop.getProperty("version"));//No Internationalisation
		 if(prop.getProperty("version").startsWith("v3"))//No Internationalisation
			{
			  ((CardLayout)JPanel2.getLayout()).last(JPanel2);
			 UNameText.setText(prop.getProperty("userName"));	//No Internationalisation
			 CNameText.setText(prop.getProperty("contextName"));//No Internationalisation
			}
		else
			{
				 ((CardLayout)JPanel2.getLayout()).first(JPanel2);
        		   		CommText.setText(prop.getProperty("community"));	//No Internationalisation
		   		WCommText.setText(prop.getProperty("writeCommunity"));	//No Internationalisation
			}
  		
	}	




 


//<Begin__class_SysCText_JPanel2_conn1>

 class SysCText_JPanel2_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
 SysCText.showPopup(); 
     }
}
//<End__class_SysCText_JPanel2_conn1>

 
  public void setUpToolBar()
  { 

  //<Begin_setUpToolBar>

  //<End_setUpToolBar>
  } 
  public void setUpMenus()
  { 

  //<Begin_setUpMenus>

  //<End_setUpMenus>
  }
}














