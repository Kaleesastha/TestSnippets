
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.applet.*;

import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.util.SeedParser;

public class GeneralScreen extends JPanel implements ActionListener
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel mainPanel = null;
	javax.swing.JPanel rediscPanel = null;
	javax.swing.JButton rediscButton = null;
	javax.swing.JLabel lfRedisc = null;
	javax.swing.JPanel initPanel = null;
	javax.swing.JLabel lfInit = null;
	javax.swing.JButton initButton = null;
	javax.swing.JPanel innerPanel = null;
	javax.swing.JCheckBox chbAutoDisc = null;
	javax.swing.JCheckBox chbDiscLN = null;
	javax.swing.JCheckBox chbRediscAlrDisc = null;
	javax.swing.JCheckBox chbEnLog = null;
	javax.swing.JLabel lfDiscInt = null;
	javax.swing.JTextField tfDiscInt = null;
	javax.swing.JLabel lfEmpty = null;
	javax.swing.JLabel lfHTML = null;
	javax.swing.JLabel imageLabel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
    RediscoveryScheduler rediscSch = null;
    InitialParametersScreen initScr = null;
    SeedParser sParser = null;
    String AutoDiscovery;
    String DiscoverLocalNet;
    String RediscoverAlreadyDiscovered;
    String EnableLog;
    String DiscoveryInterval;
    boolean isAutoDiscoveryEnabled = true;
	XMLNode tempNode = null;
    boolean isModified = false;

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
        mainPanel= new javax.swing.JPanel();
        rediscPanel= new javax.swing.JPanel();
        rediscButton= new javax.swing.JButton();
        lfRedisc= new javax.swing.JLabel();
        initPanel= new javax.swing.JPanel();
        lfInit= new javax.swing.JLabel();
        initButton= new javax.swing.JButton();
        innerPanel= new javax.swing.JPanel();
        chbAutoDisc= new javax.swing.JCheckBox();
        chbDiscLN= new javax.swing.JCheckBox();
        chbRediscAlrDisc= new javax.swing.JCheckBox();
        chbEnLog= new javax.swing.JCheckBox();
        lfDiscInt= new javax.swing.JLabel();
        tfDiscInt= new javax.swing.JTextField();
        lfEmpty= new javax.swing.JLabel();
        lfHTML= new javax.swing.JLabel();
        imageLabel= new javax.swing.JLabel();

  
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
Top.setLayout(new BorderLayout(5,5));
Top.add(mainPanel,BorderLayout.CENTER);
mainPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,4,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
mainPanel.add(rediscPanel,cons);
rediscPanel.setLayout(new GridBagLayout());
inset = new Insets(10,5,10,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
rediscPanel.add(rediscButton,cons);
inset = new Insets(10,5,10,5);
setConstraints(0,0,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
rediscPanel.add(lfRedisc,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
mainPanel.add(initPanel,cons);
initPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
initPanel.add(lfInit,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
initPanel.add(initButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
mainPanel.add(innerPanel,cons);
innerPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
innerPanel.add(chbAutoDisc,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
innerPanel.add(chbDiscLN,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
innerPanel.add(chbRediscAlrDisc,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
innerPanel.add(chbEnLog,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
innerPanel.add(lfDiscInt,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
innerPanel.add(tfDiscInt,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
mainPanel.add(lfEmpty,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
mainPanel.add(lfHTML,cons);
Top.add(imageLabel,BorderLayout.WEST);

  
//<End_setUpGUI_Container>
	chbAutoDisc.addActionListener(this);
	initButton.addActionListener(this);
	rediscButton.addActionListener(this);
	initButton.setMnemonic(KeyEvent.VK_P);
	rediscButton.setMnemonic(KeyEvent.VK_R);
	tfDiscInt.addKeyListener(new KeyAdapter()
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
			});
    } 

    public boolean isAutoDiscoveryEnabled()
    {
        return isAutoDiscoveryEnabled;
    }
    
    public void setUpProperties()
  { 

        //<Begin_setUpProperties> 

          try
          {
            rediscPanel.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rediscPanel,ex); 
          }

//<UserCode_Begin_Bean_rediscPanel>

//<UserCode_End_Bean_rediscPanel>

          try
          {
            rediscButton.setActionCommand(resourceBundle.getString(""));
            rediscButton.setBorder(new javax.swing.border.BevelBorder(0));
            rediscButton.setHorizontalTextPosition(0);
            rediscButton.setText(resourceBundle.getString("Rediscovery"));
            rediscButton.setPreferredSize(new Dimension(135,27));
            rediscButton.setMinimumSize(new Dimension(135,27));
            rediscButton.setMaximumSize(new Dimension(135,27));
            rediscButton.setToolTipText(resourceBundle.getString("Click here to configure Rediscovery."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rediscButton,ex); 
          }

//<UserCode_Begin_Bean_rediscButton>

//<UserCode_End_Bean_rediscButton>

          try
          {
            lfRedisc.setForeground(new Color(-16777214));
            lfRedisc.setText(resourceBundle.getString("Click on the button to configure the Rediscovery process."));
            lfRedisc.setFont(new Font("Serif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfRedisc,ex); 
          }

//<UserCode_Begin_Bean_lfRedisc>

//<UserCode_End_Bean_lfRedisc>

          try
          {
            initPanel.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+initPanel,ex); 
          }

//<UserCode_Begin_Bean_initPanel>

//<UserCode_End_Bean_initPanel>

          try
          {
            lfInit.setForeground(new Color(-16777215));
            lfInit.setText(resourceBundle.getString("Click on the button to configure the Initial Parameters."));
            lfInit.setFont(new Font("Serif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfInit,ex); 
          }

//<UserCode_Begin_Bean_lfInit>

//<UserCode_End_Bean_lfInit>

          try
          {
            initButton.setBorder(new javax.swing.border.BevelBorder(0));
            initButton.setText(resourceBundle.getString("   Initial Parameters   "));
            initButton.setHorizontalTextPosition(0);
            initButton.setLabel(resourceBundle.getString("Initial Parameters"));
            initButton.setPreferredSize(new Dimension(135,27));
            initButton.setMinimumSize(new Dimension(135,27));
            initButton.setMaximumSize(new Dimension(135,27));
            initButton.setToolTipText(resourceBundle.getString("Click here to configure Initial Parameters."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+initButton,ex); 
          }

//<UserCode_Begin_Bean_initButton>

//<UserCode_End_Bean_initButton>

          try
          {
            chbAutoDisc.setText(resourceBundle.getString(""));
            chbAutoDisc.setHorizontalAlignment(2);
            chbAutoDisc.setLabel(resourceBundle.getString("AutoDiscovery"));
            chbAutoDisc.setToolTipText(resourceBundle.getString("AutoDiscovery  "));
            chbAutoDisc.setSelected(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbAutoDisc,ex); 
          }

//<UserCode_Begin_Bean_chbAutoDisc>

//<UserCode_End_Bean_chbAutoDisc>

          try
          {
            chbDiscLN.setLabel(resourceBundle.getString("Discover LocalNet"));
            chbDiscLN.setToolTipText(resourceBundle.getString("Discover LocalNet"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbDiscLN,ex); 
          }

//<UserCode_Begin_Bean_chbDiscLN>

//<UserCode_End_Bean_chbDiscLN>

          try
          {
            chbRediscAlrDisc.setLabel(resourceBundle.getString("Rediscover Already Discovered"));
            chbRediscAlrDisc.setToolTipText(resourceBundle.getString("Rediscover Already Discovered"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbRediscAlrDisc,ex); 
          }

//<UserCode_Begin_Bean_chbRediscAlrDisc>

//<UserCode_End_Bean_chbRediscAlrDisc>

          try
          {
            chbEnLog.setLabel(resourceBundle.getString("Enable Log"));
            chbEnLog.setToolTipText(resourceBundle.getString("Enable Log"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbEnLog,ex); 
          }

//<UserCode_Begin_Bean_chbEnLog>

//<UserCode_End_Bean_chbEnLog>

          try
          {
            lfDiscInt.setText(resourceBundle.getString("Discovery Interval"));
            lfDiscInt.setHorizontalTextPosition(2);
            lfDiscInt.setHorizontalAlignment(10);
            lfDiscInt.setForeground(new Color(-16777213));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfDiscInt,ex); 
          }

//<UserCode_Begin_Bean_lfDiscInt>

//<UserCode_End_Bean_lfDiscInt>

          try
          {
            tfDiscInt.setToolTipText(resourceBundle.getString("Discovery Interval in seconds"));
            tfDiscInt.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfDiscInt,ex); 
          }

//<UserCode_Begin_Bean_tfDiscInt>

//<UserCode_End_Bean_tfDiscInt>

          try
          {
            lfHTML.setForeground(new Color(-16777216));
            lfHTML.setFont(new Font("Dialog",1,12));
            lfHTML.setText(resourceBundle.getString("<html><body><ul><li><font color=000000 size=-1>This tool configures the parameters which control the WebNMS discovery process.<li><font color=000000 size=-1>You can configure SNMP,ICMP and NativePing based discoveries by specifying their related parameters.<li><font color=000000 size=-1>You can enable or disable the discovery of a network,a set of nodes in a network or even a single node in a network.</font></ul></body></html>"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHTML,ex); 
          }

//<UserCode_Begin_Bean_lfHTML>

//<UserCode_End_Bean_lfHTML>

          try
          {
            imageLabel.setHorizontalAlignment(0);
            imageLabel.setHorizontalTextPosition(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+imageLabel,ex); 
          }

//<UserCode_Begin_Bean_imageLabel>

//<UserCode_End_Bean_imageLabel>

  
          //<End_setUpProperties>
         innerPanel.setNextFocusableComponent(chbAutoDisc);
        imageLabel.setIcon(MainScreen.getCommonInterface().getImage("general.png","images/runtimeadmin"));
        lfHTML.setBorder(new javax.swing.border.EtchedBorder(0));
        lfHTML.setForeground(Color.black);
        //rediscButton.setPreferredSize(initButton.getPreferredSize());
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
        setPreferredSize(new Dimension(getPreferredSize().width+681,getPreferredSize().height+467)); 
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

/**
     * Get the value of AutoDiscovery.
     * @return value of AutoDiscovery.
     */
    public String getAutoDiscovery() 
    {
        return AutoDiscovery;
    }
    
    /**
     * Set the value of AutoDiscovery.
     * @param v  Value to assign to AutoDiscovery.
     */
    public void setAutoDiscovery(String autoDisc) 
    {
        this.AutoDiscovery = autoDisc;
    }
    
    /**
     * Get the value of DiscoverLocalNet.
     * @return value of DiscoverLocalNet.
     */
    public String getDiscoverLocalNet() 
    {
        return DiscoverLocalNet;
    }
    
    /**
     * Set the value of DiscoverLocalNet.
     * @param v  Value to assign to DiscoverLocalNet.
     */
    public void setDiscoverLocalNet(String discLN) 
    {
        this.DiscoverLocalNet = discLN;
    }
    
    /**
     * Get the value of RediscoverAlreadyDiscovered.
     * @return value of RediscoverAlreadyDiscovered.
     */
    public String getRediscoverAlreadyDiscovered() 
    {
        return RediscoverAlreadyDiscovered;
    }
    
    /**
     * Set the value of RediscoverAlreadyDiscovered.
     * @param v  Value to assign to RediscoverAlreadyDiscovered.
     */
    public void setRediscoverAlreadyDiscovered(String rediscAD) 
    {
        this.RediscoverAlreadyDiscovered = rediscAD;
    }
    
    
    /**
     * Get the value of EnableLog.
     * @return value of EnableLog.
     */
    public String getEnableLog() 
    {
        return EnableLog;
    }
    
    /**
     * Set the value of EnableLog.
     * @param v  Value to assign to EnableLog.
     */
    public void setEnableLog(String eLog) 
    {
        this.EnableLog = eLog;
    }
    
    /**
     * Get the value of DiscoveryInterval.
     * @return value of DiscoveryInterval.
     */
    public String getDiscoveryInterval() 
    {
        return DiscoveryInterval;
    }
    
    /**
     * Set the value of DiscoveryInterval.
     * @param v  Value to assign to DiscoveryInterval.
     */
    public void setDiscoveryInterval(String  v) 
    {
        this.DiscoveryInterval = v;
    }
    

       
    public Hashtable addToXMLNode()
    {
        Hashtable hash = new Hashtable();

        if(chbAutoDisc.isSelected())
        {
            setAutoDiscovery("true");
        }
        else
        {
            setAutoDiscovery("false");
        }
        if(getAutoDiscovery()==null) 
        {
            hash.put("DISCOVER","");
        }
        else
        {
            hash.put("DISCOVER",getAutoDiscovery());
        }

        if(chbDiscLN.isSelected())
        {
            setDiscoverLocalNet("true");
        }
        else
        {
            setDiscoverLocalNet("false");
        }
        
        if(getDiscoverLocalNet()==null)
        {
            hash.put("DISCOVER_LOCALNET","");
        }
        else
        {
            hash.put("DISCOVER_LOCALNET",getDiscoverLocalNet());
        }

        if(chbRediscAlrDisc.isSelected())
        {
            setRediscoverAlreadyDiscovered("true");
        }
        else
        {
            setRediscoverAlreadyDiscovered("false");
        }
        if(getRediscoverAlreadyDiscovered()==null)
        {
            hash.put("REDISCOVER_ALREADY_DISCOVERED","");
        }
        else
        {
            hash.put("REDISCOVER_ALREADY_DISCOVERED",getRediscoverAlreadyDiscovered());
        }

        if(chbEnLog.isSelected())
        {
            setEnableLog("true");
        }
        else
        {
            setEnableLog("false");
        }
        if(getEnableLog()== null)
        {
            hash.put("ENABLE_LOG","");
        }
        else
        {
            hash.put("ENABLE_LOG",getEnableLog());
        }

        if(tfDiscInt.getText()!=null)
        {
            setDiscoveryInterval(tfDiscInt.getText());
        }
        else{
            setDiscoveryInterval("");
        }
        if(getDiscoveryInterval()==null)
        {
            hash.put("DISCOVERY_INTERVAL","");
        }
        else
        {
            hash.put("DISCOVERY_INTERVAL",getDiscoveryInterval());
        }

        return hash;

    }
    
    public XMLNode giveXMLNode()
    {
        Vector temp = sParser.getXMLNode("DISCOVERY");
        XMLNode rootNode = null;

        if(temp!= null) 
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
        tempNode = giveXMLNode();
        if(tempNode!=null)
        {
            if(tempNode.getNodeName().trim().equals("DISCOVERY")) 
            {
                setAutoDiscovery((String)tempNode.getAttribute("DISCOVER"));
                setDiscoverLocalNet((String)tempNode.getAttribute("DISCOVER_LOCALNET"));
                setRediscoverAlreadyDiscovered((String)tempNode.getAttribute("REDISCOVER_ALREADY_DISCOVERED"));
                setEnableLog((String)tempNode.getAttribute("ENABLE_LOG"));
                setDiscoveryInterval((String)tempNode.getAttribute("DISCOVERY_INTERVAL"));
            }                                                                                          
        } 
    }
  
    public boolean autoDiscValue() 
    {
        boolean val = false;
        if(tempNode!=null)
        {
            if(tempNode.getNodeName().trim().equals("DISCOVERY")) 
            {
                String autodiscovery = (String)tempNode.getAttribute("DISCOVER");
                String changedAutoDisc = (new Boolean(chbAutoDisc.isSelected())).toString();
                if(!changedAutoDisc.trim().equals(autodiscovery.trim())) 
                {
                    val = true;
                }
            }
        }
        return val;
    }
    
    public boolean rediscAlrDiscValue() 
    {
        boolean val = false;
        if(tempNode!=null)
        {
            if(tempNode.getNodeName().trim().equals("DISCOVERY")) 
            {
               String rediscovery = (String)tempNode.getAttribute("REDISCOVER_ALREADY_DISCOVERED");
               String changedRedisc = (new Boolean(chbRediscAlrDisc.isSelected())).toString();
               if(!changedRedisc.trim().equals(rediscovery.trim())) 
               {
                   val = true;
               }
            }
        }
        return val;
    }
    
    public boolean discLNValue() 
    {
        boolean val = false;
        if(tempNode!=null)
        {
            if(tempNode.getNodeName().trim().equals("DISCOVERY")) 
            {
               String discoverLN = (String)tempNode.getAttribute("DISCOVER_LOCALNET");
               String changedDiscLN = (new Boolean(chbDiscLN.isSelected())).toString();
               if(!changedDiscLN.trim().equals(discoverLN.trim())) 
                {
                    val = true;
                }
            }
        }
        return val;
    }
    
    public boolean enLogValue() 
    {
        boolean val = false;
        if(tempNode!=null)
        {
            if(tempNode.getNodeName().trim().equals("DISCOVERY")) 
            {
               String enableLog = (String)tempNode.getAttribute("ENABLE_LOG");
               String changedEnLog = (new Boolean(chbEnLog.isSelected())).toString();
               if(!changedEnLog.trim().equals(enableLog.trim())) 
                {
                    val = true;
                }
            }
        }
        return val;
    }
    
    public boolean discIntValue() 
    {
        boolean val = false;
        if(tempNode!=null)
        {
            if(tempNode.getNodeName().trim().equals("DISCOVERY")) 
            {
                String discoveryInt = (String)tempNode.getAttribute("DISCOVERY_INTERVAL");
                String changedDiscInt = tfDiscInt.getText();
                if(!changedDiscInt.trim().equals(discoveryInt.trim())) 
                {
                    val = true;
                }
            } 
        }       
        return val;
    }

    public boolean popUpDialogValue()
    {
        boolean val = false;
        if(initScr.isModified || rediscSch.isModified)
        {
            val = true;
        }
        
        return val;
    }

    public void showingAutoDiscovery()
    {
        populateObject();
        String autodiscovery =(String)getAutoDiscovery();
        if(autodiscovery != null && autodiscovery.equals("false"))
        {
            confirmNoDiscovery();
            JOptionPane.showMessageDialog(this,resourceBundle.getString("AutoDiscovery is currently DISABLED.\nDisabling AutoDiscovery will stop the Discovery process.\nMake sure that AutoDiscovery is ENABLED for Discovery to happen."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void populatingScreen() 
    {
        populateObject();
	showingAutoDiscovery();
        if(tempNode != null)
        {
            String autodiscovery =(String)getAutoDiscovery();
            
            if(autodiscovery!= null && autodiscovery.equals("true"))
            {
                chbAutoDisc.setSelected(true);
            }
              //Added by Balan on 25/03/03 for RTA Changes Requested  SPP Team
            else
            {
               chbAutoDisc.setSelected(false);
            }
            //Add Ends
                
            
            String discoverLN =(String)getDiscoverLocalNet();
            
            if(discoverLN!= null && discoverLN.equals("true"))
            {
                chbDiscLN.setSelected(true);
            }
                  //Added by Balan on 25/03/03 for RTA Changes Requested  SPP Team
            else
            {
                  chbDiscLN.setSelected(false);
            }
            //Add Ends
            String rediscoverAD =(String)getRediscoverAlreadyDiscovered();
            
            if(rediscoverAD!= null && rediscoverAD.equals("true"))
            {
                chbRediscAlrDisc.setSelected(true);
            }
                  //Added by Balan on 25/03/03 for RTA Changes Requested  SPP Team
            else
            {
                chbRediscAlrDisc.setSelected(false);
            }
            //Add Ends
            String enableLog =(String)getEnableLog();
            
            if(enableLog!= null && enableLog.equals("true"))
            {
                chbEnLog.setSelected(true);
            }
           //Added by Balan on 25/03/03 for RTA Changes Requested  SPP Team
            else
            {
               chbEnLog.setSelected(false);
            }
            //Add Ends
            
            if(getDiscoveryInterval()!= null)
            {
                tfDiscInt.setText(getDiscoveryInterval());
            }
        }
        
    }
   
    public JTabbedPane getParentContainer()
    {
        Container cont = getParent();
        for(; !(cont instanceof JTabbedPane); cont = cont.getParent());
        return (JTabbedPane)cont;
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == chbAutoDisc && chbAutoDisc.isSelected() == false)
        {
            if(JOptionPane.showConfirmDialog(this,resourceBundle.getString("Disabling AutoDiscovery will stop the Discovery process.\nAre you sure you want to do it?"),resourceBundle.getString("Warning"),JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                confirmNoDiscovery();
            }
            else
            {
                confirmYesDiscovery();
            }
        }
        
        if(ae.getSource() == chbAutoDisc && chbAutoDisc.isSelected() == true)
        {
            isAutoDiscoveryEnabled = true;
            for(int i=1;i<getParentContainer().getTabCount();i++)
            {
                getParentContainer().setEnabledAt(i,isAutoDiscoveryEnabled());
            }
            lfRedisc.setEnabled(true);
            lfInit.setEnabled(true);
            lfDiscInt.setEnabled(true);
            tfDiscInt.setEnabled(true);
            chbDiscLN.setEnabled(true);
            chbRediscAlrDisc.setEnabled(true);
            chbEnLog.setEnabled(true);
            initButton.setEnabled(true);
            rediscButton.setEnabled(true);
            lfHTML.setEnabled(true);
        }
               
        if(ae.getSource() == initButton)
        {
            initScr.setVisible(true);
        }
        else if(ae.getSource() == rediscButton)
        {
            rediscSch.setVisible(true);
        }
    }

    public void confirmNoDiscovery() 
    {
        isAutoDiscoveryEnabled = false;
        lfRedisc.setEnabled(false);
        lfInit.setEnabled(false);
        lfDiscInt.setEnabled(false);
        tfDiscInt.setEnabled(false);
        chbDiscLN.setEnabled(false);
        chbRediscAlrDisc.setEnabled(false);
        chbEnLog.setEnabled(false);
        initButton.setEnabled(false);
        rediscButton.setEnabled(false);
        lfHTML.setEnabled(false);
        for(int i=1;i<getParentContainer().getTabCount();i++)
        {
            getParentContainer().setEnabledAt(i,isAutoDiscoveryEnabled());
        }
    }		

    public void confirmYesDiscovery() 
    {
        isAutoDiscoveryEnabled = true;
        chbAutoDisc.setSelected(true);
        for(int i=1;i<getParentContainer().getTabCount();i++)
        {
            getParentContainer().setEnabledAt(i,isAutoDiscoveryEnabled());
        }
    }		
    
    /**
     * Set the value of isModified.
     * @param a  Value to assign to isModified.
     */
    public void setIsModified(boolean a) 
    {
        isModified = a;
        rediscSch.isModified = a;
        initScr.isModified = a;
    }

    /**
     *Get the value of isModified
     */
    public boolean getIsModified()
    {
        isModified = false;
        if(autoDiscValue() || rediscAlrDiscValue() || discIntValue() || discLNValue() || enLogValue() || popUpDialogValue() )
        {
            isModified = true;
            if(tempNode != null)
            {
                tempNode.setAttribute("DISCOVER",(new Boolean(chbAutoDisc.isSelected())).toString());
                tempNode.setAttribute("REDISCOVER_ALREADY_DISCOVERED",(new Boolean(chbRediscAlrDisc.isSelected())).toString());
                tempNode.setAttribute("DISCOVER_LOCALNET",(new Boolean(chbDiscLN.isSelected())).toString());
                tempNode.setAttribute("ENABLE_LOG",(new Boolean(chbEnLog.isSelected())).toString());
                tempNode.setAttribute("DISCOVERY_INTERVAL",tfDiscInt.getText().trim());
            }
        }
        return isModified;
    }


    public GeneralScreen()
  {
        //<Begin_GeneralScreen>
    this.init();
  
    //<End_GeneralScreen>
    }
    public GeneralScreen(RediscoveryScheduler rediscScheduler,InitialParametersScreen initParamScr,SeedParser parser)
    {
	//mtest	resourceBundle = com.adventnet.apiutils.Utility.getBundle(getParameter("RESOURCE_PROPERTIES"),applet);
        rediscSch = rediscScheduler;
        initScr = initParamScr;
        sParser = parser;
    }

    public GeneralScreen(java.applet.Applet applet)
  {
        //<Begin_GeneralScreen_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_GeneralScreen_java.applet.Applet>
    }

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

  //added for RTA spp fix -integration
  
   public void dispose(){

    rediscSch = null;
    initScr = null;
    Top=null;
    
    }
   //end of rta add
}



















































