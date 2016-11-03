//$Id: Status.java,v 1.3 2007/07/30 14:16:19 sureshbabu Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.client.status;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.*;
import com.adventnet.snmp.mibs.*;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.BrowserControl;
import com.adventnet.nms.tools.status.StatusViewer;

import com.adventnet.nms.tools.utils.HelpConfReader;
import com.adventnet.nms.tools.utils.ToolsUtil;
public class Status extends JPanel 
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "StatusViewer";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JTextField JTextField2 = null;
	javax.swing.JTextField JTextField1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTabbedPane JTabbedPane1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JLabel host = null;
	javax.swing.JLabel Version = null;
	javax.swing.JLabel IpAddress = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel JLabel18 = null;
	javax.swing.JLabel JLabel17 = null;
	javax.swing.JLabel JLabel16 = null;
	javax.swing.JLabel JLabel15 = null;
	javax.swing.JLabel JLabel14 = null;
	javax.swing.JLabel NoofManagedObjects = null;
	javax.swing.JLabel NoofNwManaged = null;
	javax.swing.JLabel NoofNodesManaged = null;
	javax.swing.JLabel NoofInterfacesManaged = null;
	javax.swing.JLabel NoofPollObjects = null;
	javax.swing.JLabel JLabel8 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel39 = null;
	javax.swing.JLabel JLabel38 = null;
	javax.swing.JLabel JLabel37 = null;
	javax.swing.JLabel JLabel36 = null;
	javax.swing.JLabel JLabel35 = null;
	javax.swing.JLabel JLabel34 = null;
	javax.swing.JLabel NoofEvents = null;
	javax.swing.JLabel EventQSize = null;
	javax.swing.JLabel AlertQSize = null;
	javax.swing.JLabel ActivePollers = null;
	javax.swing.JLabel JLabel24 = null;
	javax.swing.JLabel NoofThresholdObjects = null;
	javax.swing.JLabel NoofAlerts = null;
	javax.swing.JLabel NoofNotification = null;
	javax.swing.JLabel JLabel10 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel42 = null;
	javax.swing.JLabel JLabel41 = null;
	javax.swing.JLabel MemoryUsed = null;
	javax.swing.JLabel FreeMemory = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JLabel JLabel54 = null;
	javax.swing.JLabel JLabel53 = null;
	javax.swing.JLabel JLabel51 = null;
	javax.swing.JLabel Httpport = null;
	javax.swing.JLabel Socketport = null;
	javax.swing.JLabel Rmiregistryport = null;
	javax.swing.JLabel JLabel9 = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton help = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
    Properties props;
    
    private SnmpTarget target;
    private MibOperations mibOps;	
    MyThread mythread;
    String values[];
    boolean refreshing=false;
    //ArrayList arr = new ArrayList();
    //String[] strArr = null;
		private HelpConfReader helpReader = null;
    
    
    public Status()
	  {
        //<Begin_Status>
		this.init();
		this.start();
	  
                 //<End_Status>
    }
    public Status(String args[])
    {
        
        values=args;
        this.init();
        this.start();
    }
    
    public Status(java.applet.Applet applet)
  {
        //<Begin_Status_java.applet.Applet>
	this.applet = applet;
	this.init();
	this.start(); 
  
         //<End_Status_java.applet.Applet>
    }
    
    
    public void start()
  { 
        
        //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
        
    } 
    public void initVariables()
  { 
        
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JTextField2= new javax.swing.JTextField();
        JTextField1= new javax.swing.JTextField();
        JLabel2= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JTabbedPane1= new javax.swing.JTabbedPane();
        JPanel3= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JLabel5= new javax.swing.JLabel();
        host= new javax.swing.JLabel();
        Version= new javax.swing.JLabel();
        IpAddress= new javax.swing.JLabel();
        JLabel7= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        JPanel4= new javax.swing.JPanel();
        JLabel18= new javax.swing.JLabel();
        JLabel17= new javax.swing.JLabel();
        JLabel16= new javax.swing.JLabel();
        JLabel15= new javax.swing.JLabel();
        JLabel14= new javax.swing.JLabel();
        NoofManagedObjects= new javax.swing.JLabel();
        NoofNwManaged= new javax.swing.JLabel();
        NoofNodesManaged= new javax.swing.JLabel();
        NoofInterfacesManaged= new javax.swing.JLabel();
        NoofPollObjects= new javax.swing.JLabel();
        JLabel8= new javax.swing.JLabel();
        JPanel5= new javax.swing.JPanel();
        JLabel39= new javax.swing.JLabel();
        JLabel38= new javax.swing.JLabel();
        JLabel37= new javax.swing.JLabel();
        JLabel36= new javax.swing.JLabel();
        JLabel35= new javax.swing.JLabel();
        JLabel34= new javax.swing.JLabel();
        NoofEvents= new javax.swing.JLabel();
        EventQSize= new javax.swing.JLabel();
        AlertQSize= new javax.swing.JLabel();
        ActivePollers= new javax.swing.JLabel();
        JLabel24= new javax.swing.JLabel();
        NoofThresholdObjects= new javax.swing.JLabel();
        NoofAlerts= new javax.swing.JLabel();
        NoofNotification= new javax.swing.JLabel();
        JLabel10= new javax.swing.JLabel();
        JPanel6= new javax.swing.JPanel();
        JLabel42= new javax.swing.JLabel();
        JLabel41= new javax.swing.JLabel();
        MemoryUsed= new javax.swing.JLabel();
        FreeMemory= new javax.swing.JLabel();
        JLabel6= new javax.swing.JLabel();
        JPanel7= new javax.swing.JPanel();
        JLabel54= new javax.swing.JLabel();
        JLabel53= new javax.swing.JLabel();
        JLabel51= new javax.swing.JLabel();
        Httpport= new javax.swing.JLabel();
        Socketport= new javax.swing.JLabel();
        Rmiregistryport= new javax.swing.JLabel();
        JLabel9= new javax.swing.JLabel();
        JPanel8= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        help= new javax.swing.JButton();

  
        //<End_initVariables>
        
        
    } 
    
    public void setUpGUI(Container container)
  { 
        
        //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,10));
Top.add(JPanel1,BorderLayout.NORTH);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,10);
setConstraints(1,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JTextField2,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,1,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JTextField1,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel2,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);
Top.add(JTabbedPane1,BorderLayout.CENTER);
JTabbedPane1.addTab(resourceBundle.getString("General"),null,JPanel3,"General Information");
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(15,10,10,10);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel3.add(JLabel3,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel3.add(JLabel5,cons);
inset = new Insets(15,10,10,10);
setConstraints(1,0,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel3.add(host,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,1,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel3.add(Version,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,2,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel3.add(IpAddress,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel3.add(JLabel7,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,3,0,0,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
JPanel3.add(JLabel4,cons);
JTabbedPane1.addTab(resourceBundle.getString("Topology"),null,JPanel4,"Topology Related Information");
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(15,10,10,10);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel4.add(JLabel18,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel4.add(JLabel17,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel4.add(JLabel16,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel4.add(JLabel15,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel4.add(JLabel14,cons);
inset = new Insets(15,10,10,10);
setConstraints(1,0,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel4.add(NoofManagedObjects,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,1,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel4.add(NoofNwManaged,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,2,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel4.add(NoofNodesManaged,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,3,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel4.add(NoofInterfacesManaged,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,4,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel4.add(NoofPollObjects,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,5,0,0,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
JPanel4.add(JLabel8,cons);
JTabbedPane1.addTab(resourceBundle.getString("Fault"),null,JPanel5,"Fault Related Information");
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(15,10,10,10);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(JLabel39,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(JLabel38,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(JLabel37,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(JLabel36,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(JLabel35,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(JLabel34,cons);
inset = new Insets(15,10,10,10);
setConstraints(1,0,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(NoofEvents,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,2,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(EventQSize,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,3,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(AlertQSize,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,4,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(ActivePollers,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(JLabel24,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,5,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(NoofThresholdObjects,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,1,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(NoofAlerts,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,6,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(NoofNotification,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,7,0,0,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
JPanel5.add(JLabel10,cons);
JTabbedPane1.addTab(resourceBundle.getString("Memory"),null,JPanel6,"Memory Related Information");
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,10);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel6.add(JLabel42,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel6.add(JLabel41,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,0,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel6.add(MemoryUsed,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,1,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel6.add(FreeMemory,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,2,0,0,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
JPanel6.add(JLabel6,cons);
JTabbedPane1.addTab(resourceBundle.getString("Port"),null,JPanel7,"Port Related Information");
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(15,10,10,10);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel7.add(JLabel54,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel7.add(JLabel53,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel7.add(JLabel51,cons);
inset = new Insets(15,10,10,10);
setConstraints(1,0,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel7.add(Httpport,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,1,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel7.add(Socketport,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,2,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel7.add(Rmiregistryport,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,3,0,0,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
JPanel7.add(JLabel9,cons);
Top.add(JPanel8,BorderLayout.SOUTH);
JPanel8.setLayout(new FlowLayout(1,20,8));
JPanel8.add(JButton1);
JPanel8.add(JButton2);
JPanel8.add(help);

  
//<End_setUpGUI_Container>
    } 
    public void setUpProperties()
  { 
        
        //<Begin_setUpProperties> 

          try
          {
            Top.setPreferredSize(new Dimension(300,500));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_components>

//<UserCode_End_Bean_components>

          try
          {
            JTextField2.setText(resourceBundle.getString(""));
            JTextField2.setBorder(new javax.swing.border.BevelBorder(1));
            JTextField2.setToolTipText(resourceBundle.getString("Specify the Host Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField2,ex); 
          }

//<UserCode_Begin_Bean_JTextField2>

//<UserCode_End_Bean_JTextField2>

          try
          {
            JTextField1.setText(resourceBundle.getString(""));
            JTextField1.setBorder(new javax.swing.border.BevelBorder(1));
            JTextField1.setToolTipText(resourceBundle.getString("Specify the Port where Agent is running"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField1,ex); 
          }

//<UserCode_Begin_Bean_JTextField1>

//<UserCode_End_Bean_JTextField1>

          try
          {
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setText(resourceBundle.getString("Agent Port"));
            JLabel2.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel1.setFont(new Font("Dialog",0,12));
            JLabel1.setText(resourceBundle.getString("Host Name"));
            JLabel1.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JTabbedPane1.setFont(new Font("Dialog",1,12));
            JTabbedPane1.setSelectedIndex(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTabbedPane1,ex); 
          }

//<UserCode_Begin_Bean_JTabbedPane1>

//<UserCode_End_Bean_JTabbedPane1>

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
            JLabel3.setText(resourceBundle.getString("Host"));
            JLabel3.setForeground(new Color(-16777216));
            JLabel3.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel5.setText(resourceBundle.getString("IP Address"));
            JLabel5.setFont(new Font("Dialog",0,12));
            JLabel5.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            host.setFont(new Font("Dialog",0,12));
            host.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+host,ex); 
          }

//<UserCode_Begin_Bean_host>

//<UserCode_End_Bean_host>

          try
          {
            Version.setFont(new Font("Dialog",0,12));
            Version.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Version,ex); 
          }

//<UserCode_Begin_Bean_Version>

//<UserCode_End_Bean_Version>

          try
          {
            IpAddress.setFont(new Font("Dialog",0,12));
            IpAddress.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+IpAddress,ex); 
          }

//<UserCode_Begin_Bean_IpAddress>

//<UserCode_End_Bean_IpAddress>

          try
          {
            JLabel7.setText(resourceBundle.getString("Version"));
            JLabel7.setFont(new Font("Dialog",0,12));
            JLabel7.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel7,ex); 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>

          try
          {
            JPanel4.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JLabel18.setFont(new Font("Dialog",0,12));
            JLabel18.setText(resourceBundle.getString("Number of Managed Objects"));
            JLabel18.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel18,ex); 
          }

//<UserCode_Begin_Bean_JLabel18>

//<UserCode_End_Bean_JLabel18>

          try
          {
            JLabel17.setFont(new Font("Dialog",0,12));
            JLabel17.setText(resourceBundle.getString("Number of Networks Managed"));
            JLabel17.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel17,ex); 
          }

//<UserCode_Begin_Bean_JLabel17>

//<UserCode_End_Bean_JLabel17>

          try
          {
            JLabel16.setFont(new Font("Dialog",0,12));
            JLabel16.setText(resourceBundle.getString("Number of Nodes Managed"));
            JLabel16.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel16,ex); 
          }

//<UserCode_Begin_Bean_JLabel16>

//<UserCode_End_Bean_JLabel16>

          try
          {
            JLabel15.setFont(new Font("Dialog",0,12));
            JLabel15.setText(resourceBundle.getString("Number of Interfaces Managed"));
            JLabel15.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel15,ex); 
          }

//<UserCode_Begin_Bean_JLabel15>

//<UserCode_End_Bean_JLabel15>

          try
          {
            JLabel14.setFont(new Font("Dialog",0,12));
            JLabel14.setText(resourceBundle.getString("Number of Polled Objects"));
            JLabel14.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel14,ex); 
          }

//<UserCode_Begin_Bean_JLabel14>

//<UserCode_End_Bean_JLabel14>

          try
          {
            NoofManagedObjects.setFont(new Font("Dialog",0,12));
            NoofManagedObjects.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NoofManagedObjects,ex); 
          }

//<UserCode_Begin_Bean_NoofManagedObjects>

//<UserCode_End_Bean_NoofManagedObjects>

          try
          {
            NoofNwManaged.setFont(new Font("Dialog",0,12));
            NoofNwManaged.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NoofNwManaged,ex); 
          }

//<UserCode_Begin_Bean_NoofNwManaged>

//<UserCode_End_Bean_NoofNwManaged>

          try
          {
            NoofNodesManaged.setFont(new Font("Dialog",0,12));
            NoofNodesManaged.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NoofNodesManaged,ex); 
          }

//<UserCode_Begin_Bean_NoofNodesManaged>

//<UserCode_End_Bean_NoofNodesManaged>

          try
          {
            NoofInterfacesManaged.setFont(new Font("Dialog",0,12));
            NoofInterfacesManaged.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NoofInterfacesManaged,ex); 
          }

//<UserCode_Begin_Bean_NoofInterfacesManaged>

//<UserCode_End_Bean_NoofInterfacesManaged>

          try
          {
            NoofPollObjects.setFont(new Font("Dialog",0,12));
            NoofPollObjects.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NoofPollObjects,ex); 
          }

//<UserCode_Begin_Bean_NoofPollObjects>

//<UserCode_End_Bean_NoofPollObjects>

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
            JLabel39.setFont(new Font("Dialog",0,12));
            JLabel39.setText(resourceBundle.getString("Number of Events"));
            JLabel39.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel39,ex); 
          }

//<UserCode_Begin_Bean_JLabel39>

//<UserCode_End_Bean_JLabel39>

          try
          {
            JLabel38.setFont(new Font("Dialog",0,12));
            JLabel38.setText(resourceBundle.getString("Number of Alerts"));
            JLabel38.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel38,ex); 
          }

//<UserCode_Begin_Bean_JLabel38>

//<UserCode_End_Bean_JLabel38>

          try
          {
            JLabel37.setText(resourceBundle.getString("Event Queue Size"));
            JLabel37.setFont(new Font("Dialog",0,12));
            JLabel37.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel37,ex); 
          }

//<UserCode_Begin_Bean_JLabel37>

//<UserCode_End_Bean_JLabel37>

          try
          {
            JLabel36.setFont(new Font("Dialog",0,12));
            JLabel36.setText(resourceBundle.getString("Alert Queue Size"));
            JLabel36.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel36,ex); 
          }

//<UserCode_Begin_Bean_JLabel36>

//<UserCode_End_Bean_JLabel36>

          try
          {
            JLabel35.setFont(new Font("Dialog",0,12));
            JLabel35.setText(resourceBundle.getString("Number of Active Pollers"));
            JLabel35.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel35,ex); 
          }

//<UserCode_Begin_Bean_JLabel35>

//<UserCode_End_Bean_JLabel35>

          try
          {
            JLabel34.setFont(new Font("Dialog",0,12));
            JLabel34.setText(resourceBundle.getString("Number of Threshold Objects"));
            JLabel34.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel34,ex); 
          }

//<UserCode_Begin_Bean_JLabel34>

//<UserCode_End_Bean_JLabel34>

          try
          {
            NoofEvents.setFont(new Font("Dialog",0,12));
            NoofEvents.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NoofEvents,ex); 
          }

//<UserCode_Begin_Bean_NoofEvents>

//<UserCode_End_Bean_NoofEvents>

          try
          {
            EventQSize.setFont(new Font("Dialog",0,12));
            EventQSize.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+EventQSize,ex); 
          }

//<UserCode_Begin_Bean_EventQSize>

//<UserCode_End_Bean_EventQSize>

          try
          {
            AlertQSize.setFont(new Font("Dialog",0,12));
            AlertQSize.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+AlertQSize,ex); 
          }

//<UserCode_Begin_Bean_AlertQSize>

//<UserCode_End_Bean_AlertQSize>

          try
          {
            ActivePollers.setFont(new Font("Dialog",0,12));
            ActivePollers.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ActivePollers,ex); 
          }

//<UserCode_Begin_Bean_ActivePollers>

//<UserCode_End_Bean_ActivePollers>

          try
          {
            JLabel24.setFont(new Font("Dialog",0,12));
            JLabel24.setText(resourceBundle.getString("Sequence Number of Notification"));
            JLabel24.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel24,ex); 
          }

//<UserCode_Begin_Bean_JLabel24>

//<UserCode_End_Bean_JLabel24>

          try
          {
            NoofThresholdObjects.setFont(new Font("Dialog",0,12));
            NoofThresholdObjects.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NoofThresholdObjects,ex); 
          }

//<UserCode_Begin_Bean_NoofThresholdObjects>

//<UserCode_End_Bean_NoofThresholdObjects>

          try
          {
            NoofAlerts.setFont(new Font("Dialog",0,12));
            NoofAlerts.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NoofAlerts,ex); 
          }

//<UserCode_Begin_Bean_NoofAlerts>

//<UserCode_End_Bean_NoofAlerts>

          try
          {
            NoofNotification.setFont(new Font("Dialog",0,12));
            NoofNotification.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NoofNotification,ex); 
          }

//<UserCode_Begin_Bean_NoofNotification>

//<UserCode_End_Bean_NoofNotification>

          try
          {
            JLabel10.setFont(new Font("Dialog",0,12));
            JLabel10.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel10,ex); 
          }

//<UserCode_Begin_Bean_JLabel10>

//<UserCode_End_Bean_JLabel10>

          try
          {
            JPanel6.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            JLabel42.setText(resourceBundle.getString("Memory Used (Bytes)"));
            JLabel42.setFont(new Font("Dialog",0,12));
            JLabel42.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel42,ex); 
          }

//<UserCode_Begin_Bean_JLabel42>

//<UserCode_End_Bean_JLabel42>

          try
          {
            JLabel41.setText(resourceBundle.getString("Free Memory (Bytes)"));
            JLabel41.setFont(new Font("Dialog",0,12));
            JLabel41.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel41,ex); 
          }

//<UserCode_Begin_Bean_JLabel41>

//<UserCode_End_Bean_JLabel41>

          try
          {
            MemoryUsed.setFont(new Font("Dialog",0,12));
            MemoryUsed.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+MemoryUsed,ex); 
          }

//<UserCode_Begin_Bean_MemoryUsed>

//<UserCode_End_Bean_MemoryUsed>

          try
          {
            FreeMemory.setFont(new Font("Dialog",0,12));
            FreeMemory.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+FreeMemory,ex); 
          }

//<UserCode_Begin_Bean_FreeMemory>

//<UserCode_End_Bean_FreeMemory>

          try
          {
            JPanel7.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel7,ex); 
          }

//<UserCode_Begin_Bean_JPanel7>

//<UserCode_End_Bean_JPanel7>

          try
          {
            JLabel54.setFont(new Font("Dialog",0,12));
            JLabel54.setText(resourceBundle.getString("HTTP Port"));
            JLabel54.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel54,ex); 
          }

//<UserCode_Begin_Bean_JLabel54>

//<UserCode_End_Bean_JLabel54>

          try
          {
            JLabel53.setText(resourceBundle.getString("Socket Port"));
            JLabel53.setFont(new Font("Dialog",0,12));
            JLabel53.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel53,ex); 
          }

//<UserCode_Begin_Bean_JLabel53>

//<UserCode_End_Bean_JLabel53>

          try
          {
            JLabel51.setFont(new Font("Dialog",0,12));
            JLabel51.setText(resourceBundle.getString("RMI Registry Port"));
            JLabel51.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel51,ex); 
          }

//<UserCode_Begin_Bean_JLabel51>

//<UserCode_End_Bean_JLabel51>

          try
          {
            Httpport.setFont(new Font("Dialog",0,12));
            Httpport.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Httpport,ex); 
          }

//<UserCode_Begin_Bean_Httpport>

//<UserCode_End_Bean_Httpport>

          try
          {
            Socketport.setFont(new Font("Dialog",0,12));
            Socketport.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Socketport,ex); 
          }

//<UserCode_Begin_Bean_Socketport>

//<UserCode_End_Bean_Socketport>

          try
          {
            Rmiregistryport.setFont(new Font("Dialog",0,12));
            Rmiregistryport.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Rmiregistryport,ex); 
          }

//<UserCode_Begin_Bean_Rmiregistryport>

//<UserCode_End_Bean_Rmiregistryport>

          try
          {
            JPanel8.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel8,ex); 
          }

//<UserCode_Begin_Bean_JPanel8>

//<UserCode_End_Bean_JPanel8>

          try
          {
            JButton1.setLabel(resourceBundle.getString("Refresh"));
            JButton1.setBorder(new javax.swing.border.BevelBorder(0));
            JButton1.setMinimumSize(new Dimension(50,25));
            JButton1.setHorizontalTextPosition(0);
            JButton1.setPreferredSize(new Dimension(50,25));
            JButton1.setMaximumSize(new Dimension(50,25));
            JButton1.setFont(new Font("Dialog",1,12));
            JButton1.setToolTipText(resourceBundle.getString("Click here to Refresh."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>
	JButton1.setLabel(ToolsUtil.getMenuName(resourceBundle.getString("Refresh"),'R'));
//<UserCode_End_Bean_JButton1>

          try
          {
            JButton2.setLabel(resourceBundle.getString("Exit"));
            JButton2.setBorder(new javax.swing.border.BevelBorder(0));
            JButton2.setHorizontalTextPosition(0);
            JButton2.setMaximumSize(new Dimension(50,25));
            JButton2.setMinimumSize(new Dimension(50,25));
            JButton2.setPreferredSize(new Dimension(50,25));
            JButton2.setFont(new Font("Dialog",1,12));
            JButton2.setToolTipText(resourceBundle.getString("Click here to Exit the tool."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>
	JButton2.setLabel(ToolsUtil.getMenuName(resourceBundle.getString("Exit"),'X'));

//<UserCode_End_Bean_JButton2>

          try
          {
            help.setText(resourceBundle.getString("Help"));
            help.setBorder(new javax.swing.border.BevelBorder(0));
            help.setPreferredSize(new Dimension(50,25));
            help.setMaximumSize(new Dimension(50,25));
            help.setMinimumSize(new Dimension(50,25));
            help.setHorizontalTextPosition(0);
            help.setFont(new Font("Dialog",1,12));
            help.setToolTipText(resourceBundle.getString("Click here for Help."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+help,ex); 
          }

//<UserCode_Begin_Bean_help>
	help.setText(ToolsUtil.getMenuName(resourceBundle.getString("Help"),'H'));
//<UserCode_End_Bean_help>
		help.setPreferredSize(new Dimension(help.getPreferredSize().width+7,help.getPreferredSize().height+2));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+14,JButton2.getPreferredSize().height+2));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+29,JButton1.getPreferredSize().height+2));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+6,JPanel1.getPreferredSize().height+0));

  
          //<End_setUpProperties>
	JButton2.setPreferredSize(JButton1.getPreferredSize());
	help.setPreferredSize(JButton1.getPreferredSize());
        JButton1.setMnemonic('r');
        JButton2.setMnemonic('x');
        help.setMnemonic('h');
        if(values.length!=0 )
        {
            Vector v=new Vector();
            //strArr= new String[values.length];
            for(int i=0;i<values.length;i++)
            {
                v.add(values[i]);
                
            }
            for(int j=0;j<v.size();j++)
            {
                if(v.elementAt(j).equals("-host"))
                {
                    if((j+1)<v.size())
                    {
                        JTextField2.setText(v.elementAt(j+1).toString());
                    }
                    else
                    {
                        JTextField2.setText("");
                    }
                }
                if(v.elementAt(j).equals("-port"))
                {
                    if((j+1)<v.size())
                    {
                        JTextField1.setText(v.elementAt(j+1).toString());
                    }
                    else
                    {
                        JTextField1.setText("");
                    }
                }
            }
        }
        else
        {
            JTextField1.setText("");
            JTextField2.setText("");
        }
    }
    
    public void setUpConnections()
  { 
        
        //<Begin_setUpConnections> 

      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      help_help_conn1 help_help_conn11 =  new help_help_conn1();
      help.addActionListener(help_help_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
        JTextField2.addKeyListener( new KeyAdapter() {
                public void keyPressed ( KeyEvent ke)
                {
                    if ( ke.getKeyChar()==KeyEvent.VK_ENTER )
                    {
                        mythread=new MyThread(); 
                        if(!refreshing)
                        {
                            mythread.start();
                        }
                    }
                }
            });
        JTextField1.addKeyListener( new KeyAdapter() {
                public void keyPressed ( KeyEvent ke)
                {
                    if ( ke.getKeyChar()==KeyEvent.VK_ENTER )
                    {
                        mythread=new MyThread(); 
                        if(!refreshing)
                        {
                            mythread.start();
                        }
                    }
                    
                    
                }
            });
        
        
        
        
    } 
    public void stop()
  { 
        
        //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
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
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
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
        setPreferredSize(new Dimension(getPreferredSize().width+520,getPreferredSize().height+494)); 
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
        
        mythread=new MyThread();
        mythread.start();
        
        
        
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
    private Properties getPropsFromMib()
    {
        String s1="";
        if(!(JTextField2.getText().trim()).equals(""))
        {
            if(!(s1=JTextField1.getText().trim()).equals(""))
            {
                try
                {
                    Integer.parseInt(s1);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(StatusViewer.view,resourceBundle.getString("Please enter a valid integer for Port."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                    return null;
                }
                try
                {
                    target = new SnmpTarget();
                    target.setTargetHost(JTextField2.getText());
                    
                    if(!(s1 = JTextField1.getText().trim()).equals(""))
                    {
                        target.setTargetPort(Integer.parseInt(s1));
                    }
                    target.setCommunity("public");
                    String[] objIDList = getObjectIDs();
                    target.setObjectIDList(objIDList);
                    props = new Properties();
                    String res[] = target.snmpGetList();
                    
                    if(res==null)
                    {
                        return props;
                    }
                   
                    String nodeLable="";
                    MibNode node;        
                    target.loadMibs("\""+ System.getProperty("user.dir") + File.separator + "mibs" + File.separator +"AdventNet-WebNMS-MIB" + "\"");   //No Internationalization  
                    SnmpOID oids [] = target.getSnmpOIDList();
                    
                    for(int i=0;i<res.length;i++)
                    {
                        mibOps = target.getMibOperations();
                        node= mibOps.getMibNode(oids[i]);
                        nodeLable=node.getLabel();
                        if((res[i]!=null))
                        {
                            props.put(nodeLable,res[i]);
                        }
                    }
                }
                catch(Exception e)
                {
                    System.out.println(resourceBundle.getString("Exception in getting Data from MIB " )+ e.getMessage());
                    e.printStackTrace();
                    return null;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(StatusViewer.view,resourceBundle.getString("Please specify the Agent Port."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(StatusViewer.view,resourceBundle.getString("Please specify the Host Name."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        
        return props;
    }
    

    private String[] getObjectIDs()
    {
        return new String[]{".1.3.6.1.4.1.2162.4.1.1.0",   //  Version                   //No Internationalization    
                            ".1.3.6.1.4.1.2162.4.1.2.0",   //  host                      //No Internationalization  
                            ".1.3.6.1.4.1.2162.4.1.3.0",   //  ipaddress                 //No Internationalization 
                            ".1.3.6.1.4.1.2162.4.1.4.0",   //  total memory              //No Internationalization  
                            ".1.3.6.1.4.1.2162.4.1.5.0",   //  free memory               //No Internationalization  
                            ".1.3.6.1.4.1.2162.4.1.9.1.0", //  http port                 //No Internationalization  
                            ".1.3.6.1.4.1.2162.4.1.9.2.0", //  socket port               //No Internationalization 
                            ".1.3.6.1.4.1.2162.4.1.9.3.0", //  registry port             //No Internationalization 
                            ".1.3.6.1.4.1.2162.4.2.1.0",   //  no of objects             //No Internationalization 
                            ".1.3.6.1.4.1.2162.4.2.2.0",   //  no of networks            //No Internationalization  
                            ".1.3.6.1.4.1.2162.4.2.3.0",   //  no of nodes               //No Internationalization 
                            ".1.3.6.1.4.1.2162.4.2.4.0",   //  no of interfaces          //No Internationalization  
                            ".1.3.6.1.4.1.2162.4.4.1.0",   //  no of polled objects      //No Internationalization 
                            ".1.3.6.1.4.1.2162.4.3.1.0",   //  no of events              //No Internationalization 
                            ".1.3.6.1.4.1.2162.4.3.2.0",   //  no of alerts              //No Internationalization 
                            ".1.3.6.1.4.1.2162.4.3.3.0",   //  Events in buffer          //No Internationalization  
                            ".1.3.6.1.4.1.2162.4.3.4.0",   //  alerts in buffer          //No Internationalization  
                            ".1.3.6.1.4.1.2162.4.4.2.0",   //  no of active pollers      //No Internationalization  
                            ".1.3.6.1.4.1.2162.4.4.3.0",   //  no of threshold objects   //No Internationalization  
                            ".1.3.6.1.4.1.2162.4.5.2.0"    //  Sequence number           //No Internationalization  
        };
    }

    public void setProperty(Properties prop)
    {
        if(prop != null)
        {
            
            
            host.setText(prop.getProperty("webNMSHost"));
            Version.setText(prop.getProperty("webNMSVersion"));
            
            IpAddress.setText(prop.getProperty("webNMSIpAddress"));
            
            NoofManagedObjects.setText(prop.getProperty("webNMSNumObjects"));
            
            NoofNwManaged.setText(prop.getProperty("webNMSNumNetworks"));
            
            NoofNodesManaged.setText(prop.getProperty("webNMSNumNodes"));
            
            NoofInterfacesManaged.setText(prop.getProperty("webNMSNumInterfaces"));
            
            NoofPollObjects.setText(prop.getProperty("numPollObjects"));
            
            NoofEvents.setText(prop.getProperty("webNMSNumEvents"));
            
            NoofAlerts.setText(prop.getProperty("webNMSNumAlerts"));
            
            EventQSize.setText(prop.getProperty("webNMSEventsInBuffer"));
            
            AlertQSize.setText(prop.getProperty("webNMSAlertsInBuffer"));
            
            
            
            
            ActivePollers.setText(prop.getProperty("numActivePollers"));
            
            NoofThresholdObjects.setText(prop.getProperty("numThresholdObjects"));
            
            
            NoofNotification.setText(prop.getProperty("sequenceNum"));
            
            
            MemoryUsed.setText(prop.getProperty("webNMSTotalMemory"));
            
            FreeMemory.setText(prop.getProperty("webNMSFreeMemory"));
            
            
            
            Httpport.setText(prop.getProperty("httpPort"));
            
            Socketport.setText(prop.getProperty("nmsSocketPort"));
            
            
            
            Rmiregistryport.setText(prop.getProperty("rmiRegistryPort"));
            
            
        }
        
        
    }
    
    
    
    
    
    
    
    
    //<Begin_NmsPanelScreenWrapper>
	  /** The Management Application built as a JPanel should implement the
	   *  interface com.adventnet.nms.startclient.NmsPanel if it is required
	   *  to be invoked as a result of a Menu Action from the Client either
	   *  in the MapView or the ListView.
	   */
	  public static class NmsPanelScreenWrapper extends com.adventnet.nms.startclient.AbstractNmsPanel
	  {
		java.util.Properties panelProps = null;

		/** Returns a name for the Panel */
		public String key()
		{
		  return "Status";
		}

		/** Returns the Name of the screen */
		public String getPanelClassName()
		{
		  return "com.adventnet.nms.client.status.Status";
		}

		/** setProperties method Implementation **/
		public void setProperties(java.util.Properties props)
		{
		  Object panelInstance = super.getPanelInstance();
		  if(panelInstance == null)
		  {
			panelProps = props;
			super.setProperties(props);
			panelInstance = super.getPanelInstance();
			if(panelInstance instanceof Status)
			{
			  ((Status)panelInstance).start();
			}
		  }
		  else
		  {
			if(panelInstance instanceof Status)
			{
			  int eventID = com.adventnet.nms.startclient.NmsPanel.SET_APPLET_PROPERTY;
			  java.awt.Event event = new java.awt.Event(this,eventID,props);
			  com.adventnet.nms.util.NmsPanelEvent nmsEvent = new com.adventnet.nms.util.NmsPanelEvent(event);
			  fireNmsPanelEvent(nmsEvent);
			  if(checkIfDifferent(props))
			  {
				((Status)panelInstance).stop();
				panelProps = props;
				((Status)panelInstance).setProperties(props);
				((Status)panelInstance).start();
			  }	
			}
		  }
		}

		/** stop method implemetation **/
		public void stop()
		{
		  Object panelInstance = super.getPanelInstance();
		  if(panelInstance != null)
		  {
			((Status)panelInstance).stop();
		  }
		}

		private boolean checkIfDifferent(java.util.Properties props)
		{
		  boolean modified = false;
		  for(java.util.Enumeration enumerate = props.keys();enumerate.hasMoreElements();)
		  {
			Object key = enumerate.nextElement();
			Object value = props.get((String)key);
			if(!((String)value).equals((String)panelProps.get(key)))
			{
			  modified = true;
			  break;
			}
		  }
		  return modified;
		}
	  }
  //<End_NmsPanelScreenWrapper>
    
    
    //<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     { 
 mythread=new MyThread(); 
mythread.setPriority(Thread.MAX_PRIORITY);   
  if(!refreshing)
   mythread.start();
   
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
  if(JOptionPane.showConfirmDialog(null,resourceBundle.getString("Are you sure that you want to exit?"),resourceBundle.getString("Confirmation"),JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
  {
   System.exit(0);
  }
  
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>
    public class MyThread extends Thread
    {
        public void run()
    	{
            refreshing=true;
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            JButton1.setEnabled(false);
            props=getPropsFromMib();
            if(props!=null)
            {
                if(props.isEmpty())
                {
                    JOptionPane.showMessageDialog(StatusViewer.view,resourceBundle.getString("Please check whether the Agent is running")+"\n"+resourceBundle.getString("in the specified  Host and Port"),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    setProperty(props);
                }
                
            }
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            JButton1.setEnabled(true);
            refreshing=false;
    	}
        
    }
    
    
    
    //<Begin__class_help_help_conn1>

 class help_help_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_help_help_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
						if(helpReader == null)
						{
							helpReader = new HelpConfReader();
						}
						try{
						    String url = helpReader.getHelpUrl("STATUS_VIEWER",System.getProperty("user.dir"));
						    BrowserControl.displayURL(url);
						}
						catch(Exception e){
						    System.out.println(resourceBundle.getString("Error while getting help URL"));
						    e.printStackTrace();
						}

     }
//<UserCode_End_Connection_help_help_conn1>
 }//<End__class_help_help_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}







































