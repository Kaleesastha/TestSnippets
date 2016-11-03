/*
   $Id:
 */

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$15
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

public class ICMPScreen extends JDialog implements ActionListener
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel imagePanel = null;
	javax.swing.JTextArea taICMP = null;
	javax.swing.JLabel lfImage = null;
	javax.swing.JPanel icmpPanel = null;
	javax.swing.JCheckBox chbICMP = null;
	javax.swing.JLabel lfPingRet = null;
	javax.swing.JTextField tfPingRet = null;
	javax.swing.JPanel butPanel = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JLabel lfPingTimeout = null;
	javax.swing.JTextField tfPingTimeout = null;
	javax.swing.JLabel lfEmpty = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
    SeedParser sParser = null;
    String PingRetries;
    String PingTimeout;
    String ICMP_Discovery;
    boolean isOKclicked = false;
    boolean isModified = false;
    XMLNode tempNode = null; 


   
    public ICMPScreen(SeedParser parser,JFrame frame)
    {
        super(frame,true);
		sParser = parser;
		pack();
	//mtest	resourceBundle = com.adventnet.apiutils.Utility.getBundle(getParameter("RESOURCE_PROPERTIES"),applet);
    }
    public ICMPScreen()
  {
		//<Begin_ICMPScreen>
    pack();
  
    //<End_ICMPScreen>
    }

    public ICMPScreen(java.applet.Applet applet)
  {
       //<Begin_ICMPScreen_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ICMPScreen_java.applet.Applet>
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
 
    public void initVariables()
  { 

        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        imagePanel= new javax.swing.JPanel();
        taICMP= new javax.swing.JTextArea();
        lfImage= new javax.swing.JLabel();
        icmpPanel= new javax.swing.JPanel();
        chbICMP= new javax.swing.JCheckBox();
        lfPingRet= new javax.swing.JLabel();
        tfPingRet= new javax.swing.JTextField();
        butPanel= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        lfPingTimeout= new javax.swing.JLabel();
        tfPingTimeout= new javax.swing.JTextField();
        lfEmpty= new javax.swing.JLabel();

  
        //<End_initVariables>
    }

    public void close()
    {
        this.dispose();
        
    }
     
    public void setUpGUI(Container container)
  { 

        //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
Top.add(imagePanel,cons);
imagePanel.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
imagePanel.add(taICMP,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
imagePanel.add(lfImage,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,1,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
imagePanel.add(icmpPanel,cons);
icmpPanel.setLayout(new GridBagLayout());
inset = new Insets(0,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
icmpPanel.add(chbICMP,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
icmpPanel.add(lfPingRet,cons);
inset = new Insets(5,5,5,15);
setConstraints(1,1,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
icmpPanel.add(tfPingRet,cons);
inset = new Insets(5,5,0,5);
setConstraints(0,3,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
icmpPanel.add(butPanel,cons);
butPanel.setLayout(new FlowLayout(2,5,5));
butPanel.add(okButton);
butPanel.add(cancelButton);
inset = new Insets(5,5,5,0);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
icmpPanel.add(lfPingTimeout,cons);
inset = new Insets(5,5,5,15);
setConstraints(1,2,1,1,0.0,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
icmpPanel.add(tfPingTimeout,cons);

  
//<End_setUpGUI_Container>
	chbICMP.addActionListener(this);
	okButton.addActionListener(this);
	cancelButton.addActionListener(this);
	okButton.setMnemonic(KeyEvent.VK_O); 
	cancelButton.setMnemonic(KeyEvent.VK_C); 

        chbICMP.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });

        tfPingRet.addKeyListener(new KeyAdapter()
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


        tfPingTimeout.addKeyListener(new KeyAdapter()
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
        
        //this.setSize(323,215);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        this.setLocation((dim.width-400)/2,(dim.height-200)/2);
   } 

    public void setUpProperties()
  { 

        //<Begin_setUpProperties> 

          try
          {
            imagePanel.setForeground(new Color(-16750951));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+imagePanel,ex); 
          }

//<UserCode_Begin_Bean_imagePanel>

//<UserCode_End_Bean_imagePanel>

          try
          {
            taICMP.setEditable(false);
            taICMP.setFont(new Font("Dialog",0,10));
            taICMP.setLineWrap(true);
            taICMP.setWrapStyleWord(true);
            taICMP.setText(resourceBundle.getString(" Configure ICMP Properties.Ping retries is the number of attempts made to discover a network element and PingTimeout specifies the number of seconds for the ping to get the request"));
            taICMP.setTabSize(5);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taICMP,ex); 
          }

//<UserCode_Begin_Bean_taICMP>
            taICMP.setText(resourceBundle.getString("client.protocol.icmp"));
            
//<UserCode_End_Bean_taICMP>

          try
          {
            lfImage.setHorizontalTextPosition(0);
            lfImage.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfImage,ex); 
          }

//<UserCode_Begin_Bean_lfImage>

//<UserCode_End_Bean_lfImage>

          try
          {
            chbICMP.setText(resourceBundle.getString("ICMP Discovery"));
            chbICMP.setToolTipText(resourceBundle.getString("ICMP Discovery"));
            chbICMP.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbICMP,ex); 
          }

//<UserCode_Begin_Bean_chbICMP>

//<UserCode_End_Bean_chbICMP>

          try
          {
            lfPingRet.setHorizontalTextPosition(2);
            lfPingRet.setHorizontalAlignment(10);
            lfPingRet.setForeground(new Color(-16764109));
            lfPingRet.setText(resourceBundle.getString("Ping Retries"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfPingRet,ex); 
          }

//<UserCode_Begin_Bean_lfPingRet>

//<UserCode_End_Bean_lfPingRet>

          try
          {
            tfPingRet.setBorder(new javax.swing.border.BevelBorder(1));
            tfPingRet.setToolTipText(resourceBundle.getString("Specify Ping Retries in seconds."));
            tfPingRet.setVerifyInputWhenFocusTarget(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfPingRet,ex); 
          }

//<UserCode_Begin_Bean_tfPingRet>

//<UserCode_End_Bean_tfPingRet>

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
            okButton.setToolTipText(resourceBundle.getString("OK"));
            okButton.setBorder(new javax.swing.border.BevelBorder(0));
            okButton.setHorizontalTextPosition(0);
            okButton.setPreferredSize(new Dimension(73,27));
            okButton.setText(resourceBundle.getString("OK"));
            okButton.setMinimumSize(new Dimension(73,27));
            okButton.setMaximumSize(new Dimension(73,27));
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
            cancelButton.setHorizontalTextPosition(0);
            cancelButton.setText(resourceBundle.getString("Cancel"));
            cancelButton.setPreferredSize(new Dimension(73,27));
            cancelButton.setMinimumSize(new Dimension(73,27));
            cancelButton.setMaximumSize(new Dimension(73,27));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>

          try
          {
            lfPingTimeout.setText(resourceBundle.getString("Ping Timeout"));
            lfPingTimeout.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfPingTimeout,ex); 
          }

//<UserCode_Begin_Bean_lfPingTimeout>

//<UserCode_End_Bean_lfPingTimeout>

          try
          {
            tfPingTimeout.setToolTipText(resourceBundle.getString("Specify Ping Timeout in seconds"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfPingTimeout,ex); 
          }

//<UserCode_Begin_Bean_tfPingTimeout>

//<UserCode_End_Bean_tfPingTimeout>

          try
          {
            lfEmpty.setBackground(new Color(-6750055));
            lfEmpty.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfEmpty,ex); 
          }

//<UserCode_Begin_Bean_lfEmpty>

//<UserCode_End_Bean_lfEmpty>

  
          //<End_setUpProperties>
		cancelButton.setNextFocusableComponent(chbICMP);
        lfImage.setHorizontalAlignment(0);
        lfImage.setHorizontalTextPosition(0);
        lfImage.setIcon(MainScreen.getCommonInterface().getImage("tips.png","images/runtimeadmin"));
        taICMP.setForeground(Color.black);
        taICMP.setBackground(imagePanel.getBackground());
        taICMP.setFont(new Font("Dialog",0,11));
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
        this.setSize(getPreferredSize().width+323,getPreferredSize().height+214); 
          setTitle(resourceBundle.getString("ICMPScreen"));
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
        setTitle(resourceBundle.getString("ICMP Properties"));
    } 
    
/**
     * Get the value of ICMP_Discovery.
     * @return value of ICMP_Discovery.
     */
    public String getICMP_Discovery() 
    {
        return ICMP_Discovery;
    }
    
    /**
     * Set the value of ICMP_Discovery.
     * @param v  Value to assign to ICMP_Discovery.
     */
    public void setICMP_Discovery(String icmpDisc) 
    {
        this.ICMP_Discovery = icmpDisc;
    }
      
    /**
     * Get the value of PingRetries.
     * @return value of PingRetries.
     */
    public String getPingRetries() 
    {
        return PingRetries;
    }
    
    /**
     * Set the value of PingRetries.
     * @param v  Value to assign to PingRetries.
     */
    public void setPingRetries(String pingRet) 
    {
        this.PingRetries = pingRet;
    }

    /**
     * Get the value of PingTimeout.
     * @return value of PingTimeout.
     */
    public String getPingTimeout() 
    {
        return PingTimeout;
    }
    
    /**
     * Set the value of PingTimeout.
     * @param v  Value to assign to PingTimeout.
     */
    public void setPingTimeout(String pingTimeout) 
    {
        this.PingTimeout = pingTimeout;
    }



    
    public Hashtable oldAttributeValues()
    {
        populateObject();
        Hashtable hash = new Hashtable();
        if(tempNode != null)
        {
            if((String)tempNode.getAttribute("ENABLE_ICMP_DISCOVERY") != null)
            {
                hash.put("ENABLE_ICMP_DISCOVERY",(String)tempNode.getAttribute("ENABLE_ICMP_DISCOVERY"));
            }
            if((String)tempNode.getAttribute("PING_RETRIES") != null)
            {
                hash.put("PING_RETRIES",(String)tempNode.getAttribute("PING_RETRIES"));
            }
        }
        return hash;
    }

    public Hashtable addToXMLNode()
    {
        Hashtable hash = new Hashtable();

        if(chbICMP.isSelected())
        {
            setICMP_Discovery("true");
        }
        else
        {
            setICMP_Discovery("false");
        }
        if(getICMP_Discovery()== null)
        {
            hash.put("ENABLE_ICMP_DISCOVERY","");
        }
        else
        {
            hash.put("ENABLE_ICMP_DISCOVERY",getICMP_Discovery());
        }

        if(tfPingRet.getText()!= null)
        {
            setPingRetries(tfPingRet.getText());
        }
        else
        {
            setPingRetries("");
        }
        if(getPingRetries()== null)
        {
            hash.put("PING_RETRIES","");
        }
        else
        {
            hash.put("PING_RETRIES",getPingRetries());
        }
// To set Ping Timeout 
        if(tfPingTimeout.getText()!= null)
        {
            setPingTimeout(tfPingTimeout.getText());
        }
        else
        {
            setPingTimeout("");
        }
        if(getPingTimeout()== null)
        {
            hash.put("PING_TIMEOUT","");
        }
        else
        {
            hash.put("PING_TIMEOUT",getPingTimeout());
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
            String icmpDisc = (String)tempNode.getAttribute("ENABLE_ICMP_DISCOVERY");        
            if(icmpDisc!= null && (icmpDisc.trim()).equals("true"))
            {
                chbICMP.setSelected(true);
                lfPingRet.setEnabled(true);
                tfPingRet.setEditable(true);
            }
            if(icmpDisc!= null && (icmpDisc.trim()).equals("false"))
            {
                chbICMP.setSelected(false);
                lfPingRet.setEnabled(false);
                tfPingRet.setEditable(false);
            }
            String icmpRet = (String)tempNode.getAttribute("PING_RETRIES");
            if(icmpRet != null)
            {
                tfPingRet.setText(icmpRet.trim());
            }
            
            // To set Ping timeout
             String icmpTimeout = (String)tempNode.getAttribute("PING_TIMEOUT");
            if(icmpTimeout != null)
            {
                tfPingTimeout.setText(icmpTimeout.trim());
            }

        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(chbICMP.isSelected())
        {
            lfPingRet.setEnabled(true);
            tfPingRet.setEditable(true);
            // To set Ping Timeout
            lfPingTimeout.setEnabled(true);
            tfPingTimeout.setEditable(true);

        }
        else
        {
            lfPingRet.setEnabled(false);
            tfPingRet.setEditable(false);
           // To set Ping Timeout
            lfPingTimeout.setEnabled(false);
           tfPingTimeout.setEditable(false);
 
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
            tempNode.setAttribute("PING_RETRIES",tfPingRet.getText().trim());
            // To set Ping Timeout
            tempNode.setAttribute("PING_TIMEOUT",tfPingTimeout.getText().trim());
            tempNode.setAttribute("ENABLE_ICMP_DISCOVERY",new Boolean(chbICMP.isSelected()).toString()); 
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



























