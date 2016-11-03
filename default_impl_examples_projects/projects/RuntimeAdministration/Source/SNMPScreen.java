
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$2
//<End_Version>
package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.applet.*;

import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.util.SeedParser;

public class SNMPScreen extends JDialog implements ActionListener
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel tipsPanel = null;
	javax.swing.JLabel lfImage = null;
	javax.swing.JTextArea taSNMP = null;
	javax.swing.JPanel snmpPanel = null;
	javax.swing.JLabel lfRetries = null;
	javax.swing.JLabel lfTimeout = null;
	javax.swing.JTextField tfRetries = null;
	javax.swing.JTextField tfTimeout = null;
	javax.swing.JLabel lfPorts = null;
	javax.swing.JTextField tfPorts = null;
	javax.swing.JCheckBox chbSNMPv3 = null;
	javax.swing.JLabel lfUser = null;
	javax.swing.JTextField tfUser = null;
	javax.swing.JLabel lfContext = null;
	javax.swing.JTextField tfContext = null;
	javax.swing.JLabel lfRead = null;
	javax.swing.JLabel lfWrite = null;
	javax.swing.JCheckBox chbSNMP = null;
	javax.swing.JPasswordField tfWrite = null;
	javax.swing.JPasswordField tfRead = null;
	javax.swing.JPanel butPanel = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JLabel emptyLabel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
     String SNMPTimeout;
    String SNMPRetries;
    String SNMPReadCommunity;
    String SNMPWriteCommunity;
    String SNMPDiscovery;
    String SNMP_Ports;
    String SNMPv3Discovery;
    String SNMPv3_UserNames;
    String SNMPv3_ContextName;
    boolean isOKclicked = false;
    boolean isModified = false;
    boolean isSNMPv1v2cSelected = false;
    boolean isSNMPv3Selected = false;
    SeedParser sParser = null;
    XMLNode tempNode = null;

    public SNMPScreen(SeedParser parser,JFrame frame)
    {
        //mtest	resourceBundle = com.adventnet.apiutils.Utility.getBundle(getParameter("RESOURCE_PROPERTIES"),applet);
        super(frame,true);
        sParser = parser;
        pack();
    }

    public SNMPScreen()
  {
        //<Begin_SNMPScreen>
    pack();
  
    //<End_SNMPScreen>
    }

    public SNMPScreen(java.applet.Applet applet)
  {
        //<Begin_SNMPScreen_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_SNMPScreen_java.applet.Applet>
    }

    public void close()
    {
        this.dispose();
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

  
    public void start()
  { 

        //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
	populatingScreen();
    } 

    public boolean isOK_Clicked()
    {
        return isOKclicked;
    }

 public boolean isSNMPv1v2c_Selected()
    {
        return isSNMPv1v2cSelected;
    }

    public boolean isSNMPv3_Selected()
    {
        return isSNMPv3Selected;
    }

    public void initVariables()
  { 

        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        tipsPanel= new javax.swing.JPanel();
        lfImage= new javax.swing.JLabel();
        taSNMP= new javax.swing.JTextArea();
        snmpPanel= new javax.swing.JPanel();
        lfRetries= new javax.swing.JLabel();
        lfTimeout= new javax.swing.JLabel();
        tfRetries= new javax.swing.JTextField();
        tfTimeout= new javax.swing.JTextField();
        lfPorts= new javax.swing.JLabel();
        tfPorts= new javax.swing.JTextField();
        chbSNMPv3= new javax.swing.JCheckBox();
        lfUser= new javax.swing.JLabel();
        tfUser= new javax.swing.JTextField();
        lfContext= new javax.swing.JLabel();
        tfContext= new javax.swing.JTextField();
        lfRead= new javax.swing.JLabel();
        lfWrite= new javax.swing.JLabel();
        chbSNMP= new javax.swing.JCheckBox();
        tfWrite= new javax.swing.JPasswordField();
        tfRead= new javax.swing.JPasswordField();
        butPanel= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        emptyLabel= new javax.swing.JLabel();

  
        //<End_initVariables>
	
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
     
    public void setUpGUI(Container container)
  { 

        //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(tipsPanel,cons);
tipsPanel.setLayout(new BorderLayout(5,5));
tipsPanel.add(lfImage,BorderLayout.WEST);
tipsPanel.add(taSNMP,BorderLayout.CENTER);
inset = new Insets(5,5,5,5);
setConstraints(0,1,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(snmpPanel,cons);
snmpPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
snmpPanel.add(lfRetries,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
snmpPanel.add(lfTimeout,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
snmpPanel.add(tfRetries,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
snmpPanel.add(tfTimeout,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
snmpPanel.add(lfPorts,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
snmpPanel.add(tfPorts,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
snmpPanel.add(chbSNMPv3,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
snmpPanel.add(lfUser,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,7,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
snmpPanel.add(tfUser,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,8,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
snmpPanel.add(lfContext,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,8,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
snmpPanel.add(tfContext,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
snmpPanel.add(lfRead,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
snmpPanel.add(lfWrite,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
snmpPanel.add(chbSNMP,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
snmpPanel.add(tfWrite,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
snmpPanel.add(tfRead,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(butPanel,cons);
butPanel.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,20);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
butPanel.add(okButton,cons);
inset = new Insets(10,20,10,10);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
butPanel.add(cancelButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
Top.add(emptyLabel,cons);

  
//<End_setUpGUI_Container>
	chbSNMP.addActionListener(this);
	chbSNMPv3.addActionListener(this);
	okButton.addActionListener(this);
	cancelButton.addActionListener(this);
	okButton.setMnemonic(KeyEvent.VK_O); 
		cancelButton.setMnemonic(KeyEvent.VK_C); 
        
        chbSNMP.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });

        tfRetries.addKeyListener(new KeyAdapter()
            {
				public void keyTyped(KeyEvent ke) 
				{ 
                	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar()== ke.VK_BACK_SPACE))
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
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });

        tfTimeout.addKeyListener(new KeyAdapter()
            {
				public void keyTyped(KeyEvent ke) 
				{ 
                	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar()== ke.VK_BACK_SPACE))
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
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });

        tfRead.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });

        tfWrite.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });

        tfPorts.addKeyListener(new KeyAdapter()
            {
				public void keyTyped(KeyEvent ke) 
				{ 
                	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar()== ke.VK_BACK_SPACE)|| ke.getKeyChar() == ke.VK_SPACE || ke.getKeyChar() == 45 )
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
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });

        chbSNMPv3.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });

        tfUser.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });

        tfContext.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });

        okButton.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });
        
        cancelButton.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ESCAPE)
                    {
                        Cancel_Clicked();
                    }
                } 
            });
        
        this.setSize(350,410);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        this.setLocation((dim.width-350)/2,(dim.height-410)/2);
	}

    public void setUpProperties()
  { 

        //<Begin_setUpProperties> 

          try
          {
            taSNMP.setWrapStyleWord(true);
            taSNMP.setLineWrap(true);
            taSNMP.setEditable(false);
            taSNMP.setText(resourceBundle.getString("SNMP based discovery with support for different versions."));
            taSNMP.setFont(new Font("Dialog",0,10));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taSNMP,ex); 
          }

//<UserCode_Begin_Bean_taSNMP>

//<UserCode_End_Bean_taSNMP>

          try
          {
            lfRetries.setText(resourceBundle.getString("SNMP Retries"));
            lfRetries.setToolTipText(resourceBundle.getString(""));
            lfRetries.setForeground(new Color(-16777211));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfRetries,ex); 
          }

//<UserCode_Begin_Bean_lfRetries>

//<UserCode_End_Bean_lfRetries>

          try
          {
            lfTimeout.setText(resourceBundle.getString("SNMP Timeout"));
            lfTimeout.setToolTipText(resourceBundle.getString(""));
            lfTimeout.setForeground(new Color(-16777211));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfTimeout,ex); 
          }

//<UserCode_Begin_Bean_lfTimeout>

//<UserCode_End_Bean_lfTimeout>

          try
          {
            tfRetries.setBorder(new javax.swing.border.BevelBorder(1));
            tfRetries.setToolTipText(resourceBundle.getString("Specify SNMP Retries"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfRetries,ex); 
          }

//<UserCode_Begin_Bean_tfRetries>

//<UserCode_End_Bean_tfRetries>

          try
          {
            tfTimeout.setBorder(new javax.swing.border.BevelBorder(1));
            tfTimeout.setToolTipText(resourceBundle.getString("Specify SNMP Timeout in seconds."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfTimeout,ex); 
          }

//<UserCode_Begin_Bean_tfTimeout>

//<UserCode_End_Bean_tfTimeout>

          try
          {
            lfPorts.setForeground(new Color(-16777215));
            lfPorts.setText(resourceBundle.getString("SNMP_Ports"));
            lfPorts.setToolTipText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfPorts,ex); 
          }

//<UserCode_Begin_Bean_lfPorts>

//<UserCode_End_Bean_lfPorts>

          try
          {
            tfPorts.setText(resourceBundle.getString("161"));
            tfPorts.setBorder(new javax.swing.border.BevelBorder(1));
            tfPorts.setToolTipText(resourceBundle.getString("A numerical value for PortNumber is mandatory or 161 will be assumed."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfPorts,ex); 
          }

//<UserCode_Begin_Bean_tfPorts>
tfPorts.setText("161");
//<UserCode_End_Bean_tfPorts>

          try
          {
            chbSNMPv3.setText(resourceBundle.getString("SNMPv3 Discovery"));
            chbSNMPv3.setToolTipText(resourceBundle.getString("SNMPv3 Discovery"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbSNMPv3,ex); 
          }

//<UserCode_Begin_Bean_chbSNMPv3>

//<UserCode_End_Bean_chbSNMPv3>

          try
          {
            lfUser.setForeground(new Color(-16777215));
            lfUser.setText(resourceBundle.getString("User Names"));
            lfUser.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfUser,ex); 
          }

//<UserCode_Begin_Bean_lfUser>

//<UserCode_End_Bean_lfUser>

          try
          {
            tfUser.setToolTipText(resourceBundle.getString("A value here is mandatory for SNMPv3 based Discovery"));
            tfUser.setBorder(new javax.swing.border.BevelBorder(1));
            tfUser.setEditable(false);
            tfUser.setEnabled(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfUser,ex); 
          }

//<UserCode_Begin_Bean_tfUser>

//<UserCode_End_Bean_tfUser>

          try
          {
            lfContext.setForeground(new Color(-16777215));
            lfContext.setText(resourceBundle.getString("Context Name"));
            lfContext.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfContext,ex); 
          }

//<UserCode_Begin_Bean_lfContext>

//<UserCode_End_Bean_lfContext>

          try
          {
            tfContext.setToolTipText(resourceBundle.getString("A value here is mandatory for SNMPv3 based Discovery"));
            tfContext.setBorder(new javax.swing.border.BevelBorder(1));
            tfContext.setEditable(false);
            tfContext.setEnabled(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfContext,ex); 
          }

//<UserCode_Begin_Bean_tfContext>

//<UserCode_End_Bean_tfContext>

          try
          {
            lfRead.setForeground(new Color(-16777211));
            lfRead.setText(resourceBundle.getString("Read Community"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfRead,ex); 
          }

//<UserCode_Begin_Bean_lfRead>

//<UserCode_End_Bean_lfRead>

          try
          {
            lfWrite.setForeground(new Color(-16777215));
            lfWrite.setText(resourceBundle.getString("Write Community"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfWrite,ex); 
          }

//<UserCode_Begin_Bean_lfWrite>

//<UserCode_End_Bean_lfWrite>

          try
          {
            chbSNMP.setText(resourceBundle.getString("SNMP Discovery"));
            chbSNMP.setToolTipText(resourceBundle.getString("SNMP Discovery"));
            chbSNMP.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbSNMP,ex); 
          }

//<UserCode_Begin_Bean_chbSNMP>

//<UserCode_End_Bean_chbSNMP>

          try
          {
            tfWrite.setToolTipText(resourceBundle.getString("Specify Write Community"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfWrite,ex); 
          }

//<UserCode_Begin_Bean_tfWrite>

//<UserCode_End_Bean_tfWrite>

          try
          {
            tfRead.setToolTipText(resourceBundle.getString("Specify Read Community"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfRead,ex); 
          }

//<UserCode_Begin_Bean_tfRead>

//<UserCode_End_Bean_tfRead>

          try
          {
            butPanel.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+butPanel,ex); 
          }

//<UserCode_Begin_Bean_butPanel>

//<UserCode_End_Bean_butPanel>

          try
          {
            okButton.setLabel(resourceBundle.getString("Ok"));
            okButton.setToolTipText(resourceBundle.getString("OK"));
            okButton.setBorder(new javax.swing.border.BevelBorder(0));
            okButton.setHorizontalTextPosition(0);
            okButton.setPreferredSize(new Dimension(73,27));
            okButton.setMinimumSize(new Dimension(73,27));
            okButton.setMaximumSize(new Dimension(73,27));
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
            cancelButton.setToolTipText(resourceBundle.getString("Cancel"));
            cancelButton.setBorder(new javax.swing.border.BevelBorder(0));
            cancelButton.setText(resourceBundle.getString("   Cancel   "));
            cancelButton.setHorizontalTextPosition(0);
            cancelButton.setMaximumSize(new Dimension(73,27));
            cancelButton.setMinimumSize(new Dimension(73,27));
            cancelButton.setPreferredSize(new Dimension(73,27));
            cancelButton.setForeground(new Color(-16777216));
            cancelButton.setLabel(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>
		taSNMP.setPreferredSize(new Dimension(taSNMP.getPreferredSize().width+0,taSNMP.getPreferredSize().height+5));

  
          //<End_setUpProperties>
		cancelButton.setNextFocusableComponent(chbSNMP);
        lfImage.setHorizontalAlignment(0);
        lfImage.setHorizontalTextPosition(0);
		lfImage.setIcon(MainScreen.getCommonInterface().getImage("tips.png","images/runtimeadmin"));
        taSNMP.setFont(new Font("Dialog",0,11));
        taSNMP.setBackground(tipsPanel.getBackground());
        taSNMP.setForeground(Color.black);
        //okButton.setPreferredSize(cancelButton.getPreferredSize());
    }
	
    public void setUpConnections()
  { 

        //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
    } 
 
    public void init(java.applet.Applet app)
	{
		this.applet = app;
		init();
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
        this.setSize(getPreferredSize().width+648,getPreferredSize().height+490); 
          setTitle(resourceBundle.getString("SNMPScreen"));
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
        setTitle(resourceBundle.getString("SNMP Properties"));
  } 
    
    /**
     * Get the value of SNMPTimeout.
     * @return value of SNMPTimeout.
     */
    public String getSNMPTimeout() 
    {
        return SNMPTimeout;
    }
    
    /**
     * Set the value of SNMPTimeout.
     * @param v  Value to assign to SNMPTimeout.
     */
    public void setSNMPTimeout(String snmpTime) 
    {
        this.SNMPTimeout = snmpTime;
    }
      
    /**
     * Get the value of SNMPRetries.
     * @return value of SNMPRetries.
     */
    public String  getSNMPRetries() 
    {
        return SNMPRetries;
    }
    
    /**
     * Set the value of SNMPRetries.
     * @param v  Value to assign to SNMPRetries.
     */
    public void setSNMPRetries(String snmpRet) 
    {
        this.SNMPRetries = snmpRet;
    }
   
    /**
     * Get the value of SNMPReadCommunity.
     * @return value of SNMPReadCommunity.
     */
   
    public String getSNMPReadCommunity() 
    {
        return SNMPReadCommunity;
    }
    
    /**
     * Set the value of SNMPReadCommunity.
     * @param v  Value to assign to SNMPReadCommunity.
     */
    public void setSNMPReadCommunity(String snmpRead) 
    {
        this.SNMPReadCommunity = snmpRead;
    }
   
    /**
     * Get the value of SNMPWriteCommunity.
     * @return value of SNMPWriteCommunity.
     */
    public String getSNMPWriteCommunity() 
    {
        return SNMPWriteCommunity;
    }
    
    /**
     * Set the value of SNMPWriteCommunity.
     * @param v  Value to assign to SNMPWriteCommunity.
     */
    public void setSNMPWriteCommunity(String snmpWrite) 
    {
        this.SNMPWriteCommunity = snmpWrite;
    }
   
    
    /**
     * Get the value of SNMP_Ports.
     * @return value of SNMP_Ports.
     */
    public String getSNMP_Ports() 
    {
        return SNMP_Ports;
    }
    
    /**
     * Set the value of SNMP_Ports.
     * @param v  Value to assign to SNMP_Ports.
     */
    public void setSNMP_Ports(String snmpPorts) 
    {
        this.SNMP_Ports = snmpPorts;
    }
    
    /**
     * Get the value of SNMPDiscovery.
     * @return value of SNMPDiscovery.
     */
    public String getSNMPDiscovery() 
    {
        return SNMPDiscovery;
    }
    
    /**
     * Set the value of SNMPDiscovery.
     * @param v  Value to assign to SNMPDiscovery.
     */
    public void setSNMPDiscovery(String snmpDisc) 
    {
        this.SNMPDiscovery = snmpDisc;
    }
       
    /**
     * Get the value of SNMPv3Discovery.
     * @return value of SNMPv3Discovery.
     */
    public String getSNMPv3Discovery() 
    {
        return SNMPv3Discovery;
    }
    
    /**
     * Set the value of SNMPv3Discovery.
     * @param v  Value to assign to SNMPv3Discovery.
     */
    public void setSNMPv3Discovery(String snmpv3Disc) 
    {
        this.SNMPv3Discovery = snmpv3Disc;
    }
   
    /**
     * Get the value of SNMPv3_UserName.
     * @return value of SNMPv3_UserName.
     */
    public String getSNMPv3_UserNames() 
    {
        return SNMPv3_UserNames;
    }
    
    /**
     * Set the value of SNMPv3_UserName.
     * @param v  Value to assign to SNMPv3_UserName.
     */
    public void setSNMPv3_UserNames(String snmpv3Users) 
    {
        this.SNMPv3_UserNames = snmpv3Users;
    }
   
    /**
     * Get the value of SNMPv3_ContextName.
     * @return value of SNMPv3_ContextName.
     */
    public String getSNMPv3_ContextName() 
    {
        return SNMPv3_ContextName;
    }
    
    /**
     * Set the value of SNMPv3_ContextName.
     * @param v  Value to assign to SNMPv3_ContextName.
     */
    public void setSNMPv3_ContextName(String snmpv3Context) 
    {
        this.SNMPv3_ContextName = snmpv3Context;
    }
   
public Hashtable oldAttributeValues()
    {
        populateObject();
        Hashtable hash = new Hashtable();
        if(tempNode != null)
        {
            if((String)tempNode.getAttribute("ENABLE_SNMP_DISCOVERY") != null)
            {
                hash.put("ENABLE_SNMP_DISCOVERY",(String)tempNode.getAttribute("ENABLE_SNMP_DISCOVERY"));
            }
            if((String)tempNode.getAttribute("SNMP_RETRIES") != null)
            {
                hash.put("SNMP_RETRIES",(String)tempNode.getAttribute("SNMP_RETRIES"));
            }
            if((String)tempNode.getAttribute("SNMP_TIMEOUT") != null)
            {
                hash.put("SNMP_TIMEOUT",(String)tempNode.getAttribute("SNMP_TIMEOUT"));
            }
            if((String)tempNode.getAttribute("READ_COMMUNITY") != null)
            {
                hash.put("READ_COMMUNITY",(String)tempNode.getAttribute("READ_COMMUNITY"));
            }
            if((String)tempNode.getAttribute("WRITE_COMMUNITY") != null)
            {
                hash.put("WRITE_COMMUNITY",(String)tempNode.getAttribute("WRITE_COMMUNITY"));
            }
            if((String)tempNode.getAttribute("SNMP_PORTS") != null)
            {
                hash.put("SNMP_PORTS",(String)tempNode.getAttribute("SNMP_PORTS"));
            }
            if((String)tempNode.getAttribute("ENABLE_SNMPV3_DISCOVERY") != null)
            {
                hash.put("ENABLE_SNMPV3_DISCOVERY",(String)tempNode.getAttribute("ENABLE_SNMPV3_DISCOVERY"));
            }
            if((String)tempNode.getAttribute("SNMPV3_USERNAMES") != null)
            {
                hash.put("SNMPV3_USERNAMES",(String)tempNode.getAttribute("SNMPV3_USERNAMES"));
            }
            if((String)tempNode.getAttribute("SNMPV3_CONTEXTNAME") != null)
            {
                hash.put("SNMPV3_CONTEXTNAME",(String)tempNode.getAttribute("SNMPV3_CONTEXTNAME"));
            }
        }
        return hash;
    }

    public Hashtable addToXMLNode()
    
    {
        Hashtable hash = new Hashtable();

        if(chbSNMP.isSelected())
        {
            setSNMPDiscovery("true");
        }
        else
        {
            setSNMPDiscovery("false");
        }
        
        if(getSNMPDiscovery()== null)
        {
            hash.put("ENABLE_SNMP_DISCOVERY","");
        }
        else
        {
            hash.put("ENABLE_SNMP_DISCOVERY",getSNMPDiscovery());
        }

        if(tfRetries.getText()!= null)
        {
            setSNMPRetries(tfRetries.getText());
        }
        else
        {
            setSNMPRetries("");
        }
        if(getSNMPRetries()== null)
        {
            hash.put("SNMP_RETRIES","");
        }
        else
        {
            hash.put("SNMP_RETRIES",getSNMPRetries());
        }
        
        if(tfTimeout.getText()!= null)
        {
            setSNMPTimeout(tfTimeout.getText());
        }
        else
        {
            setSNMPTimeout("");
        }
        if(getSNMPTimeout()== null)
        {
            hash.put("SNMP_TIMEOUT","");
        }
        else
        {
            hash.put("SNMP_TIMEOUT",getSNMPTimeout());
        }
        
        if(tfRead.getText()!= null)
        {
            setSNMPReadCommunity(tfRead.getText());
        }
        else
        {
            setSNMPReadCommunity("");
        }
        if(getSNMPReadCommunity()== null)
        {
            hash.put("READ_COMMUNITY","");
        }
        else
        {
            hash.put("READ_COMMUNITY",getSNMPReadCommunity());
        }
        
        if(tfWrite.getText()!= null)
        {
            setSNMPWriteCommunity(tfWrite.getText());
        }
        else
        {
            setSNMPWriteCommunity("");
        }
        if(getSNMPWriteCommunity()== null) 
        {
            hash.put("WRITE_COMMUNITY","");
        }
        else 
        {
            hash.put("WRITE_COMMUNITY",getSNMPWriteCommunity());
        }

        if(tfPorts.getText()!= null)
        {
            setSNMP_Ports(tfPorts.getText());
        }
        else
        {
            setSNMP_Ports("");
        }
        if(getSNMP_Ports()== null) 
        {
            hash.put("SNMP_PORTS","");
        }
        else 
        {
            hash.put("SNMP_PORTS",getSNMP_Ports());
        }

        if(chbSNMPv3.isSelected())
        {
            setSNMPv3Discovery("true");
        }
        else
        {
            setSNMPv3Discovery("false");
        }
        
        if(getSNMPv3Discovery()== null)
        {
            hash.put("ENABLE_SNMPV3_DISCOVERY","");
        }
        else
        {
            hash.put("ENABLE_SNMPV3_DISCOVERY",getSNMPv3Discovery());
        }

        if(tfUser.getText()!= null)
        {
            setSNMPv3_UserNames(tfUser.getText());
        }
        else
        {
            setSNMPv3_UserNames("");
        }
        if(getSNMPv3_UserNames()== null) 
        {
            hash.put("SNMPV3_USERNAMES","");
        }
        else 
        {
            hash.put("SNMPV3_USERNAMES",getSNMPv3_UserNames());
        }

        if(tfContext.getText()!= null)
        {
            setSNMPv3_ContextName(tfContext.getText());
        }
        else
        {
            setSNMPv3_ContextName("");
        }
        if(getSNMPv3_ContextName()== null) 
        {
            hash.put("SNMPV3_CONTEXTNAME","");
        }
        else 
        {
            hash.put("SNMPV3_CONTEXTNAME",getSNMPv3_ContextName());
        }

        return hash;
                
    }

public XMLNode giveXMLNode()
    {
        Vector temp = sParser.getXMLNode("DISCOVERY");
        XMLNode rootNode = null;

        if(temp!=null) 
        {
            for (int i=0; i<temp.size(); i++)
            {
                rootNode = (XMLNode)temp.elementAt(i);
            }
        }
              
        return rootNode;
        
    }

    public void populateObject() 
    {
        if(tempNode == null)
        {    
            tempNode = giveXMLNode();
        }
    }
    
    public void populatingScreen() 
    {
        populateObject();
        if(tempNode != null)
        {
            String snmpDiscovery = (String)tempNode.getAttribute("ENABLE_SNMP_DISCOVERY");
            if(snmpDiscovery!= null && snmpDiscovery.equals("true"))
            {
                chbSNMP.setSelected(true);
                lfRetries.setEnabled(true);
                tfRetries.setEditable(true);
                lfTimeout.setEnabled(true);
                tfTimeout.setEditable(true);
                lfRead.setEnabled(true);
                tfRead.setEditable(true);
                lfWrite.setEnabled(true);
                tfWrite.setEditable(true);
                lfPorts.setEnabled(true);
                tfPorts.setEditable(true);
            }
            if(snmpDiscovery!= null && snmpDiscovery.equals("false"))
            {
                chbSNMP.setSelected(false);
                lfRetries.setEnabled(false);
                tfRetries.setEditable(false);
                lfTimeout.setEnabled(false);
                tfTimeout.setEditable(false);
                lfRead.setEnabled(false);
                tfRead.setEditable(false);
                lfWrite.setEnabled(false);
                tfWrite.setEditable(false);
                lfPorts.setEnabled(false);
                tfPorts.setEditable(false);
            }
            String retries = (String)tempNode.getAttribute("SNMP_RETRIES");
            if(retries !=null)
            {
                tfRetries.setText(retries.trim());
            }
            String timeOut = (String)tempNode.getAttribute("SNMP_TIMEOUT");
            if(timeOut!=null)
            {
                tfTimeout.setText(timeOut.trim());
            }
            String read = (String)tempNode.getAttribute("READ_COMMUNITY");
            if(read != null)
            {
                tfRead.setText(read.trim());
            }
            String write = (String)tempNode.getAttribute("WRITE_COMMUNITY");
            if(write != null)
            {
                tfWrite.setText(write.trim());
            }
            String ports = (String)tempNode.getAttribute("SNMP_PORTS");
            if(ports != null)
            {
                tfPorts.setText(ports.trim());
            }
            String snmpv3Discovery =(String)tempNode.getAttribute("ENABLE_SNMPV3_DISCOVERY");
            if(snmpv3Discovery!= null && snmpv3Discovery.equals("true"))
            {
                chbSNMPv3.setSelected(true);
                lfUser.setEnabled(true);
                tfUser.setEditable(true);
                lfContext.setEnabled(true);
                tfContext.setEditable(true);
            }
            else if(snmpv3Discovery != null && snmpv3Discovery.equals("false"))
            {
                chbSNMPv3.setSelected(false);
                lfUser.setEnabled(false);
                tfUser.setEditable(false);
                lfContext.setEnabled(false);
                tfContext.setEditable(false);
            }
            String user = (String)tempNode.getAttribute("SNMPV3_USERNAMES");
            if(user != null)
            {
                tfUser.setText(user.trim());
            }
            String context = (String)tempNode.getAttribute("SNMPV3_CONTEXTNAME");
            if(context != null)
            {
                tfContext.setText(context.trim());
            }
        }

    }

    public void actionPerformed(ActionEvent ae)
    {
        if((chbSNMP.isSelected() == false && chbSNMPv3.isSelected() == false)&& (ae.getSource() == chbSNMP || ae.getSource() == chbSNMPv3))
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("You have disabled SNMP discoveries.\nSNMP based discoveries will not happen."),resourceBundle.getString("Warning"),JOptionPane.INFORMATION_MESSAGE);
        }
	if(/*ae.getSource() == chbSNMP &&*/ chbSNMP.isSelected())
	{
            isSNMPv1v2cSelected = true;
            lfRetries.setEnabled(true);
            lfTimeout.setEnabled(true);
            lfPorts.setEnabled(true);
            lfRead.setEnabled(true);
            lfWrite.setEnabled(true);
            tfRetries.setEditable(true);
            tfTimeout.setEditable(true);
            tfPorts.setEditable(true);
            tfRead.setEditable(true);
            tfWrite.setEditable(true);
	}
	else
	{
            isSNMPv1v2cSelected = false;
            lfRetries.setEnabled(false);
            lfTimeout.setEnabled(false);
            lfPorts.setEnabled(false);
            lfRead.setEnabled(false);
            lfWrite.setEnabled(false);
            tfRetries.setEditable(false);
            tfTimeout.setEditable(false);
            tfPorts.setEditable(false);
            tfRead.setEditable(false);
            tfWrite.setEditable(false);
	}
	if(/*ae.getSource() == chbSNMPv3 &&*/ chbSNMPv3.isSelected())
        {
            isSNMPv3Selected = true;
            lfUser.setEnabled(true);
            lfContext.setEnabled(true);
            tfUser.setEditable(true);
            tfContext.setEditable(true);
        }
	else
        {
            isSNMPv3Selected = false;
            lfUser.setEnabled(false);
            lfContext.setEnabled(false);
            tfUser.setEditable(false);
            tfContext.setEditable(false);
        }
	if(ae.getSource() == okButton)
        {
            OK_Clicked();
        }
	else if(ae.getSource() == cancelButton)
        {
            Cancel_Clicked();
        }
		
    }
    
    public void OK_Clicked()
    {
        isOKclicked = true;
        isModified = true;
        if(tempNode != null)
        {
            tempNode.setAttribute("ENABLE_SNMP_DISCOVERY",new Boolean(chbSNMP.isSelected()).toString());
            tempNode.setAttribute("SNMP_TIMEOUT",tfTimeout.getText().trim());
            tempNode.setAttribute("SNMP_RETRIES",tfRetries.getText().trim());
            tempNode.setAttribute("READ_COMMUNITY",tfRead.getText().trim());
            tempNode.setAttribute("WRITE_COMMUNITY",tfWrite.getText().trim());
            tempNode.setAttribute("SNMP_PORTS",tfPorts.getText().trim());
            tempNode.setAttribute("ENABLE_SNMPV3_DISCOVERY",new Boolean(chbSNMPv3.isSelected()).toString());
            tempNode.setAttribute("SNMPV3_USERNAMES",tfUser.getText().trim());
            tempNode.setAttribute("SNMPV3_CONTEXTNAME",tfContext.getText().trim());
        }
		MainScreen.setApplyButton(true);
        this.setVisible(false);
    }
    
    public void Cancel_Clicked()
    {
        isOKclicked = false;
	this.setVisible(false);
        isModified = false;
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
}
















