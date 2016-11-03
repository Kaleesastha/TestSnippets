
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$64
//<End_Version>
package com.adventnet.nms.runtimeconfig;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.XMLNode;
import java.util.*;

public class SNMPpropConfigScreen extends JDialog implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel buttonPanel = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JTabbedPane v3TabPane = null;
	javax.swing.JPanel snmpGeneralPanel = null;
	javax.swing.JLabel lCommunity = null;
	javax.swing.JLabel lAgentPort = null;
	javax.swing.JTextField tfCommunity = null;
	javax.swing.JTextField tfAgentPort = null;
	javax.swing.JPanel snmpV3Panel = null;
	javax.swing.JPanel descPanel = null;
	javax.swing.JTextArea taDesc = null;
	javax.swing.JPanel v3ButtonsPanel = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton modButton = null;
	javax.swing.JButton delButton = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable snmpV3Table = null;
	javax.swing.JPanel titlePanel = null;
	javax.swing.JLabel titleLbl = null;
	javax.swing.JPanel chPanel = null;
	javax.swing.JRadioButton chV1V2 = null;
	javax.swing.JRadioButton chV3 = null;
	javax.swing.JRadioButton chV2 = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	//vkarthik
	String sUserName = null;
	String sContextName = null;
	String sCommunity = null;
	String sAgentPort = null;
	String sSecurityLevel = null;
	String sAuthProtocol = null;
	String sAuthPwd = null;
	String sPrivProtocol = null;
	String sPrivPwd = null;
	boolean isV1V2Selected = false;
	boolean isV3Selected = false;
	XMLNode tempXmlNode = null;
	boolean snmpFlg = false;
 	public Vector addData = null;
	public Vector rowDataVector = null;
	public Vector tableRowDataVector = new Vector();
	String sSnmpVersion = null;
	Network network = null;
	String SNMPVer = null;
	SnmpV3PropTableModel snmpV3PropTableModel = null;
	


 public void setVisible(boolean bl)
  {
      
      if(true)
      {
      //Thread.dumpStack();
      	super.setVisible(bl);
      	return; 
      }
      
       
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
if(MainScreen.isClient == false)
{
	taDesc.setLineWrap(true);
	taDesc.setWrapStyleWord(true);
	taDesc.setText(resourceBundle.getString("WARNING :   The entries given in this tab will be stored in the database, so database must be running."));
}

this.setModal(true);
//chV1V2.doClick();
v3TabPane.setEnabledAt(0,false);
v3TabPane.setEnabledAt(1,false); 
//comboSnmpVer.setSelectedItem();
lCommunity.setEnabled(false);
tfCommunity.setEnabled(false);
lAgentPort.setEnabled(false);
tfAgentPort.setEnabled(false);
  
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
        this.setSize(getPreferredSize().width+569,getPreferredSize().height+491); 
          setTitle(resourceBundle.getString("Snmp Properties"));
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

 rowDataVector = new Vector();
 Toolkit tool = Toolkit.getDefaultToolkit();
 Dimension d = tool.getScreenSize();        
 Dimension d1 = getSize();        
 this.setLocation(new Point((int)((d.getWidth()/2)-(d1.getWidth()/2)),(int)((d.getHeight()/2)-(d1.getHeight()/2))));

 // THEME-II Start
   this.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
		    dispose();
                }
            });
   // THEME-II End   

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
       
  //<Begin_setUpProperties> 

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

          try
          {
            v3TabPane.setEnabled(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+v3TabPane,ex); 
          }

//<UserCode_Begin_Bean_v3TabPane>
 
//<UserCode_End_Bean_v3TabPane>

          try
          {
            snmpGeneralPanel.setName(resourceBundle.getString("General"));
            snmpGeneralPanel.setToolTipText(resourceBundle.getString("General Parameters"));
            snmpGeneralPanel.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+snmpGeneralPanel,ex); 
          }

//<UserCode_Begin_Bean_snmpGeneralPanel>

//<UserCode_End_Bean_snmpGeneralPanel>

          try
          {
            lCommunity.setForeground(new Color(-16777216));
            lCommunity.setText(resourceBundle.getString("Community"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lCommunity,ex); 
          }

//<UserCode_Begin_Bean_lCommunity>

//<UserCode_End_Bean_lCommunity>

          try
          {
            lAgentPort.setText(resourceBundle.getString("SNMP Agent Port"));
            lAgentPort.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lAgentPort,ex); 
          }

//<UserCode_Begin_Bean_lAgentPort>

//<UserCode_End_Bean_lAgentPort>

          try
          {
            tfCommunity.setEditable(true);
            tfCommunity.setEnabled(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfCommunity,ex); 
          }

//<UserCode_Begin_Bean_tfCommunity>

//<UserCode_End_Bean_tfCommunity>

          try
          {
            snmpV3Panel.setBorder(new javax.swing.border.LineBorder(new Color(-16777216),1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+snmpV3Panel,ex); 
          }

//<UserCode_Begin_Bean_snmpV3Panel>

//<UserCode_End_Bean_snmpV3Panel>

          try
          {
            taDesc.setEditable(false);
            taDesc.setEnabled(true);
            taDesc.setDisabledTextColor(new Color(-16777216));
            taDesc.setBackground(new Color(-1));
            taDesc.setOpaque(false);
            taDesc.setRequestFocusEnabled(false);
            taDesc.setText(resourceBundle.getString("This tab is for configuring SNMP V3 Security Parameters"));
            taDesc.setLineWrap(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taDesc,ex); 
          }

//<UserCode_Begin_Bean_taDesc>

//<UserCode_End_Bean_taDesc>

          try
          {
            addButton.setText(resourceBundle.getString("Add"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addButton,ex); 
          }

//<UserCode_Begin_Bean_addButton>

//<UserCode_End_Bean_addButton>

          try
          {
            modButton.setText(resourceBundle.getString("Modify"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+modButton,ex); 
          }

//<UserCode_Begin_Bean_modButton>

//<UserCode_End_Bean_modButton>

          try
          {
            delButton.setText(resourceBundle.getString("Delete"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+delButton,ex); 
          }

//<UserCode_Begin_Bean_delButton>

//<UserCode_End_Bean_delButton>

          try
          {
            titleLbl.setBackground(new Color(-3355444));
            titleLbl.setFont(new Font("dialog",1,13));
            titleLbl.setText(resourceBundle.getString("SNMP Properties "));
            titleLbl.setHorizontalAlignment(10);
            titleLbl.setBorder(new javax.swing.border.EtchedBorder(0));
            titleLbl.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+titleLbl,ex); 
          }

//<UserCode_Begin_Bean_titleLbl>
titleLbl.setHorizontalTextPosition(SwingConstants.CENTER);
titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
//<UserCode_End_Bean_titleLbl>

          try
          {
            chV1V2.setText(resourceBundle.getString("SNMP"));
            chV1V2.setLabel(resourceBundle.getString("V1"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chV1V2,ex); 
          }

//<UserCode_Begin_Bean_chV1V2>

//<UserCode_End_Bean_chV1V2>

          try
          {
            chV3.setText(resourceBundle.getString("SNMP V3"));
            chV3.setLabel(resourceBundle.getString("V3"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chV3,ex); 
          }

//<UserCode_Begin_Bean_chV3>

//<UserCode_End_Bean_chV3>

          try
          {
            chV2.setLabel(resourceBundle.getString("V2"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chV2,ex); 
          }

//<UserCode_Begin_Bean_chV2>

//<UserCode_End_Bean_chV2>

          try
          {
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setText(resourceBundle.getString("SNMP Version : "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>
		titlePanel.setPreferredSize(new Dimension(titlePanel.getPreferredSize().width+178,titlePanel.getPreferredSize().height+10));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+0,JScrollPane1.getPreferredSize().height+69));
		v3ButtonsPanel.setPreferredSize(new Dimension(v3ButtonsPanel.getPreferredSize().width+92,v3ButtonsPanel.getPreferredSize().height+0));
		taDesc.setPreferredSize(new Dimension(taDesc.getPreferredSize().width+141,taDesc.getPreferredSize().height+18));
		descPanel.setPreferredSize(new Dimension(descPanel.getPreferredSize().width+10,descPanel.getPreferredSize().height+0));
		buttonPanel.setPreferredSize(new Dimension(buttonPanel.getPreferredSize().width+371,buttonPanel.getPreferredSize().height+0));

  
          //<End_setUpProperties>

  
  } 
  public void initVariables()
  {
       
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        buttonPanel= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        v3TabPane= new javax.swing.JTabbedPane();
        snmpGeneralPanel= new javax.swing.JPanel();
        lCommunity= new javax.swing.JLabel();
        lAgentPort= new javax.swing.JLabel();
        tfCommunity= new javax.swing.JTextField();
        tfAgentPort= new javax.swing.JTextField();
        snmpV3Panel= new javax.swing.JPanel();
        descPanel= new javax.swing.JPanel();
        taDesc= new javax.swing.JTextArea();
        v3ButtonsPanel= new javax.swing.JPanel();
        addButton= new javax.swing.JButton();
        modButton= new javax.swing.JButton();
        delButton= new javax.swing.JButton();
        JScrollPane1= new javax.swing.JScrollPane();
        snmpV3Table= new javax.swing.JTable();
        titlePanel= new javax.swing.JPanel();
        titleLbl= new javax.swing.JLabel();
        chPanel= new javax.swing.JPanel();
        chV1V2= new javax.swing.JRadioButton();
        chV3= new javax.swing.JRadioButton();
        chV2= new javax.swing.JRadioButton();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
       snmpV3PropTableModel = new SnmpV3PropTableModel();
       String[] header = {"UserName","ContextName","PortNumber","SecurityLevel","AuthProtocol","AuthPassword","PrivProtocol","PrivPassword"};
       snmpV3PropTableModel.setColumnIdentifiers(header);
       snmpV3Table.setModel(snmpV3PropTableModel);
       //MyTableCellRenderer myTableCellRenderer = new MyTableCellRenderer();
       snmpV3Table.setDefaultRenderer(Object.class,new MyTableCellRenderer());
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(buttonPanel,BorderLayout.SOUTH);
buttonPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
buttonPanel.add(okButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
buttonPanel.add(cancelButton,cons);
Top.add(v3TabPane,BorderLayout.CENTER);
v3TabPane.addTab(resourceBundle.getString("SNMP Properties"),null,snmpGeneralPanel,"SNMP Properties");
snmpGeneralPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
snmpGeneralPanel.add(lCommunity,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
snmpGeneralPanel.add(lAgentPort,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
snmpGeneralPanel.add(tfCommunity,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
snmpGeneralPanel.add(tfAgentPort,cons);
v3TabPane.addTab(resourceBundle.getString("SNMP V3 Properties"),null,snmpV3Panel,"SNMP V3 Properties");
snmpV3Panel.setLayout(new BorderLayout(5,5));
snmpV3Panel.add(descPanel,BorderLayout.NORTH);
descPanel.setLayout(new FlowLayout(0,5,5));
descPanel.add(taDesc);
snmpV3Panel.add(v3ButtonsPanel,BorderLayout.SOUTH);
v3ButtonsPanel.setLayout(new FlowLayout(1,5,5));
v3ButtonsPanel.add(addButton);
v3ButtonsPanel.add(modButton);
v3ButtonsPanel.add(delButton);
snmpV3Panel.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(snmpV3Table);
Top.add(titlePanel,BorderLayout.NORTH);
titlePanel.setLayout(new GridLayout(2,0,5,5));
titlePanel.add(titleLbl);
titlePanel.add(chPanel);
chPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
chPanel.add(chV1V2,cons);
inset = new Insets(5,5,5,5);
setConstraints(4,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
chPanel.add(chV3,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
chPanel.add(chV2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
chPanel.add(JLabel1,cons);

  
//<End_setUpGUI_Container>
addButton.addActionListener(this);
modButton.addActionListener(this);
delButton.addActionListener(this);
chV1V2.addActionListener(this);
chV2.addActionListener(this);
chV3.addActionListener(this);
okButton.addActionListener(this);
cancelButton.addActionListener(this);
tfAgentPort.addKeyListener(new KeyAdapter()
            {
				public void keyTyped(KeyEvent ke) 
				{ 
                	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar() == '.') || (ke.getKeyChar()== ke.VK_BACK_SPACE))
					{ 
                		//DO Nothing 
                	} 
                	else
					{ 
                    	ke.consume(); 
                    	Toolkit.getDefaultToolkit().beep(); 
                	} 
				}
                public void keyPressed(KeyEvent ke)
                {
                    
                } 
            } );
 /*okButton.addActionListener(this);
 cancelButton.addActionListener(this);
 chV1V2.addActionListener(this);
 chV3.addActionListener(this);*/
 //this.setModal(true);
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





  

   
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
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
  public void actionPerformed(ActionEvent ae)
  {
       
    
     
      // String verSelected = ((String)comboSnmpVer.getSelectedItem());
      // if(ae.getActionCommand().equals("comboSnmpVer") && (verSelected.equals("V1") || verSelected.equals("V2")))
       if(ae.getSource() == chV1V2 && chV1V2.isSelected() == true)
       {
            chV2.setSelected(false);
            chV3.setSelected(false);
            //snmpGeneralPanel.grabFocus();
            v3TabPane.setEnabledAt(0,true);
            v3TabPane.setEnabledAt(1,false); 
            snmpGeneralPanel.setEnabled(true);
            //snmpGeneralPanel.requestFocus();
            lCommunity.setEnabled(true);
             lAgentPort.setEnabled(true);
             tfCommunity.setEditable(true);
             tfCommunity.setEnabled(true);
             tfAgentPort.setEnabled(true);
             tfAgentPort.setEditable(true); 
             snmpV3Table.setEnabled(false);
             addButton.setEnabled(false);
             modButton.setEnabled(false);
             delButton.setEnabled(false);
             v3TabPane.setSelectedIndex(0);
       }
       if(ae.getSource() == chV2 && chV2.isSelected() == true)
       {
            chV3.setSelected(false);
            chV1V2.setSelected(false);
            //snmpGeneralPanel.grabFocus();
            v3TabPane.setEnabledAt(0,true);
            v3TabPane.setEnabledAt(1,false); 
            snmpGeneralPanel.setEnabled(true);
            //snmpGeneralPanel.requestFocus();
            lCommunity.setEnabled(true);
             lAgentPort.setEnabled(true);
             tfCommunity.setEditable(true);
             tfCommunity.setEnabled(true);
             tfAgentPort.setEnabled(true);
             tfAgentPort.setEditable(true); 
             snmpV3Table.setEnabled(false);
             addButton.setEnabled(false);
             modButton.setEnabled(false);
             delButton.setEnabled(false);
             v3TabPane.setSelectedIndex(0);
       }
       
      // if(ae.getActionCommand().equals("comboSnmpVer") && verSelected.equals("V3"))
      if(ae.getSource() == chV3 && chV3.isSelected() == true)
        {
             chV1V2.setSelected(false);
             chV2.setSelected(false);
            // JOptionPane.showMessageDialog(this,resourceBundle.getString("To configure SNMP V3 properties, database should be running."),resourceBundle.getString("Warning"),JOptionPane.INFORMATION_MESSAGE);
             snmpV3Panel.grabFocus();
             v3TabPane.setEnabledAt(1,true);
             v3TabPane.setEnabledAt(0,false);  
             snmpV3Panel.setEnabled(true);
             //snmpV3Panel.requestFocus();
             lCommunity.setEnabled(false);
             lAgentPort.setEnabled(false);
             tfCommunity.setEditable(false);
             tfCommunity.setEnabled(false);
             tfAgentPort.setEnabled(false);
             tfAgentPort.setEditable(false);
             snmpV3Table.setEnabled(true);
             addButton.setEnabled(true);
             modButton.setEnabled(true);
             delButton.setEnabled(true);
             v3TabPane.setSelectedIndex(1);
        }
        if(ae.getSource() == addButton)
         {
	 	try
	 	{	 	     
	 	SNMPV3propScreen snmpPropScreen = new SNMPV3propScreen();
	 	snmpPropScreen.setVisible(true);
	 	Vector v = snmpPropScreen.getRowData();
	 	String valueUN = null;
	 	String valueAP = null;
	 	if(v != null)
	 	{
	 		for(int i=0;i<snmpV3PropTableModel.getRowCount();i++)
	 		{
	 			valueUN = (String)snmpV3PropTableModel.getValueAt(i,0);
	 			valueAP = (String)snmpV3PropTableModel.getValueAt(i,2);
	 				 			
	 		}
	 		if( (v.elementAt(0).equals(valueUN)) && (v.elementAt(2).equals(valueAP) ) )    
	 			{
	 				JOptionPane.showMessageDialog(this,resourceBundle.getString("The User Name with the specified Port is already added in the table"),resourceBundle.getString("Warning"),JOptionPane.INFORMATION_MESSAGE);     
	 			}
	 		else
	 		{	
	 		snmpV3PropTableModel.addRow(v);
	 		}
	 	}
	 	snmpPropScreen = null;
	 	}
	 	catch(Exception e)
	 	{
	 		e.printStackTrace();
	 	}
	 	
	 	
         }	
         if(ae.getActionCommand().equals("Modify"))
         {
	     // THEME-II Start
	            SNMPV3propScreen snmpPropScreenMod = new SNMPV3propScreen();
		    // THEME-II End
         		snmpPropScreenMod.init();
         		snmpPropScreenMod .start();
         		String s = "";
         		boolean b = snmpV3Table.getRowSelectionAllowed();
         		
         		if(b == true)
         		{
         			int rowIndex = snmpV3Table.getSelectedRow();
         			if(rowIndex ==-1)
         			{
         			     return;
         			}
         			
         			for(int i=0; i<snmpV3Table.getColumnCount();i++)
         			{
         				s = (String)snmpV3PropTableModel.getValueAt(rowIndex,i);
         				if(i==0)
         				{
         				       snmpPropScreenMod.setUserName(s);
         				}
         				else if(i==1)
         				{
         				     snmpPropScreenMod.setContextName(s);
         				}
         				else if(i==2)
         				{
         				     snmpPropScreenMod.setAgentPort(s);
         				}
         				else if(i==3)
         				{
         				     snmpPropScreenMod.setSecurityLevel(s);
         				}
         				else if(i==4)
         				{
         				     snmpPropScreenMod.setAuthProtocol(s);
         				}
         				else if(i==5)
         				{
         				     snmpPropScreenMod.setAuthPwd(s);
         				}
         				else if(i==6)
         				{
         				     snmpPropScreenMod.setPrivProtocol(s);
         				}
         				else if(i==7)
         				{
         				     snmpPropScreenMod.setPrivPwd(s);
         				}
         			 }
         			snmpPropScreenMod.setVisible(true) ;
         			try
         			{
         				Vector v = snmpPropScreenMod.getRowData();
         				if(v != null)
         				{
         					snmpV3PropTableModel.removeRow(rowIndex);
         					snmpV3PropTableModel.insertRow(rowIndex,v);
         				}
         			}
         			catch(Exception e)
         			{
         				e.printStackTrace();     
         			}
         		}
        		
         }
	 if(ae.getSource() == okButton)
	 {
	  okButtonActionPerformed();     
	 }
	 if(ae.getSource() == cancelButton)
	 {
	     this.setVisible(false);   
	     // THEME-II Start
	     dispose();
	     // THEME-II End.
	 }
	
	if(ae.getActionCommand().equals("Delete"))
	{
	      int rowIndex = snmpV3Table.getSelectedRow();
	      if(rowIndex == -1)
	      {
	      	return;
	      }
	      snmpV3PropTableModel.removeRow(rowIndex);
	}
       //snmpV3PropTableModel.addRow(snmpPropScreen.getRowData());
  }

	public void okButtonActionPerformed()
	{
	    if(chV1V2.isSelected())
	     {
	       	
	     	 if( ( sCommunity = tfCommunity.getText().trim()) != null)
	      	{
	       		//setCommunity(sCommunity);
	       		network.setCommunities(sCommunity);
	      	}
		 if( ( sAgentPort = tfAgentPort.getText().trim()) != null)
		 {
	      		network.setSnmpPorts(sAgentPort) ;
	      	 }
		
		 network.setVersion("V1");
	     }
	     
	     if(chV2.isSelected())
	     {
	       	
	     	 if( ( sCommunity = tfCommunity.getText().trim()) != null)
	      	{
	       		//setCommunity(sCommunity);
	       		network.setCommunities(sCommunity);
	      	}
		 if( ( sAgentPort = tfAgentPort.getText().trim()) != null)
		 {
	      		network.setSnmpPorts(sAgentPort) ;
	      	 }
		
		 network.setVersion("V2");
	     }		
	     if(chV3.isSelected()) 	 			     
	     {
	          network.setVersion("V3");
	         
	          setTableRowData(snmpV3PropTableModel.getDataVector());
	          
	     }
	     //network.setSNMPProperties(snmpV3PropTableModel.getDataVector(),getSnmpVersion(),getCommunity(),getAgentPort());
	     this.setVisible(false);   
	     // THEME-II Start
	     dispose();
	     // THEME-II End
	}


  public SNMPpropConfigScreen(Network net)
  {
    
    this.network = net;
    pack();
    init();
    start();
    
       
    //<Begin_SNMPpropConfigScreen>
    //<End_SNMPpropConfigScreen>
  }
  
  public void populateScreen()
  {
    SNMPVer = network.getVersion();
       
    if(SNMPVer != null)
    {
    	if(SNMPVer.equalsIgnoreCase("V1") || SNMPVer.equalsIgnoreCase("V2"))
   	{
    		populateV1data(SNMPVer);     
    	}
   	 if(SNMPVer.equalsIgnoreCase("V3"))
    	{
    		populateV3data();     
    	}
    }
  }
  
  private void populateV1data(String snmpver)
  {
       //Thread.dumpStack();
       String spVal = network.getSnmpPorts();
       String comVal = network.getCommunities();
       chV1V2.setEnabled(true);
       //chV1V2.setSelected(true);
       if(snmpver.equalsIgnoreCase("V1"))
       chV1V2.doClick();
       else
       chV2.doClick();
       tfAgentPort.setText(spVal);
       tfCommunity.setText(comVal);
             
  }
  private void populateV3data()
  {
  	chV3.setEnabled(true);     
  	//chV3.setSelected(true);
  	chV3.doClick();
  	Vector v3dataVec = network.getV3Data();
  	
  	for(int i=0;i<v3dataVec.size();i++)
  	{
   	     Vector rowVal = new Vector();
   	     Properties row = (Properties)v3dataVec.elementAt(i);
  	     rowVal.addElement(row.getProperty("USERNAME"));
  	     
  	     rowVal.addElement(network.getContextName());
  	     rowVal.addElement(row.getProperty("SNMPAGENTPORT"));
  	     rowVal.addElement(row.getProperty("SECURITYLEVEL"));
  	     rowVal.addElement(row.getProperty("AUTHPROTOCOL"));
  	     rowVal.addElement(row.getProperty("AUTHPASSWORD"));
  	     rowVal.addElement(row.getProperty("PRIVPROTOCOL"));
  	     rowVal.addElement(row.getProperty("PRIVPASSWORD"));
  	     snmpV3PropTableModel.addRow( rowVal);
  	     
  	}
       
  }

  public SNMPpropConfigScreen()
  {
    //<Begin_SNMPpropConfigScreen>
    pack();
  
    //<End_SNMPpropConfigScreen>
  }

  public SNMPpropConfigScreen(java.applet.Applet applet)
  {
    //<Begin_SNMPpropConfigScreen_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_SNMPpropConfigScreen_java.applet.Applet>

  }
  public Network getNetwork()
  {
  	return this.network;
  }
 
  public void setCommunity(String community)
  {
   	 this.sCommunity = sCommunity;
   	 
  }
  public String getCommunity()
  {
  	return this.sCommunity;     
  } 
  public void setAgentPort(String port)
  {
  	this.sAgentPort = port;
  	
  }
  public String getAgentPort()
  {
  	return this.sAgentPort;     
  }
  public void setSnmpVersion(String ver)
  {
  	this.sSnmpVersion = ver;
  	
   }
  public String getSnmpVersion()
  {
  	return this.sSnmpVersion;     
  }
  public void setTableRowData(Vector tableRowVector)
  {
  	Vector tableVec = new Vector();
  	for(int i=0; i<tableRowVector.size();i++)
    	{
    		Properties v3PropObj = new Properties();
    		Vector tempVec = (Vector)tableRowVector.elementAt(i);
    		v3PropObj.setProperty("USERNAME",(String)tempVec.elementAt(0));
    		v3PropObj.setProperty("SNMPV3_CONTEXTNAME",(String)tempVec.elementAt(1));
    		//network.setContextName((String)tempVec.elementAt(1));
    		v3PropObj.setProperty("SNMPAGENTPORT",(String)tempVec.elementAt(2));
    		v3PropObj.setProperty("SECURITYLEVEL",(String)tempVec.elementAt(3));
    		v3PropObj.setProperty("AUTHPROTOCOL",(String)tempVec.elementAt(4));
    		v3PropObj.setProperty("AUTHPASSWORD",(String)tempVec.elementAt(5));
    		v3PropObj.setProperty("PRIVPROTOCOL",(String)tempVec.elementAt(6));
    		v3PropObj.setProperty("PRIVPASSWORD",(String)tempVec.elementAt(7));
    		tableVec.addElement(v3PropObj);
        	}
  	network.setClientV3Data(tableVec); 
  	
  }
}
class MyTableCellRenderer extends  javax.swing.table.DefaultTableCellRenderer
{
	public MyTableCellRenderer()
	{
	}
	public Component getTableCellRendererComponent(JTable myTable,Object obj,boolean isSelected,boolean hasFocus,int row,int col)
	{
	     	JLabel label=(JLabel)super.getTableCellRendererComponent(myTable,obj,isSelected,hasFocus,row,col);
		JPasswordField passwordField = new JPasswordField();
		if(col == 5 || col ==7)
		{
			passwordField.setText(obj.toString());
			return passwordField;     	
		}
		//label.setText(obj.toString());
		return label;
	}      
}
class SnmpV3PropTableModel extends javax.swing.table.DefaultTableModel
{
	
	public SnmpV3PropTableModel()
	{    
	}
	public boolean isCellEditable(int row,int col)
	{
		return false;     
	}
}
































































































