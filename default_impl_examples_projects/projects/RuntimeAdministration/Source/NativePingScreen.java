
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

public class NativePingScreen extends JDialog implements ActionListener
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel butPanel = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JPanel panelICMP = null;
	javax.swing.JCheckBox chbNatPing = null;
	javax.swing.JCheckBox chbPingSw = null;
	javax.swing.JLabel lfTimeout = null;
	javax.swing.JLabel lfRetries = null;
	javax.swing.JLabel lfSwPack = null;
	javax.swing.JLabel lfSwSlInt = null;
	javax.swing.JLabel lfDebug = null;
	javax.swing.JTextField tfTimeout = null;
	javax.swing.JTextField tfRetries = null;
	javax.swing.JTextField tfSwPack = null;
	javax.swing.JTextField tfSwSlInt = null;
	javax.swing.JTextField tfDebug = null;
	javax.swing.JPanel imagePanel = null;
	javax.swing.JLabel lfImage = null;
	javax.swing.JTextArea taICMP = null;
	javax.swing.JLabel lfEmpty = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
     String Retries;
    String Timeout;
    String DebugLevel;
    String SweepPackets;
    String SweepSleepInterval;
    String PingSweep;
    boolean isOKclicked = false;
    boolean isModified = false;
    SeedParser sParser = null;
    XMLNode tempNode = null;

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
        butPanel= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        panelICMP= new javax.swing.JPanel();
        chbNatPing= new javax.swing.JCheckBox();
        chbPingSw= new javax.swing.JCheckBox();
        lfTimeout= new javax.swing.JLabel();
        lfRetries= new javax.swing.JLabel();
        lfSwPack= new javax.swing.JLabel();
        lfSwSlInt= new javax.swing.JLabel();
        lfDebug= new javax.swing.JLabel();
        tfTimeout= new javax.swing.JTextField();
        tfRetries= new javax.swing.JTextField();
        tfSwPack= new javax.swing.JTextField();
        tfSwSlInt= new javax.swing.JTextField();
        tfDebug= new javax.swing.JTextField();
        imagePanel= new javax.swing.JPanel();
        lfImage= new javax.swing.JLabel();
        taICMP= new javax.swing.JTextArea();
        lfEmpty= new javax.swing.JLabel();

  
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
    
    public void close()
    {
        this.dispose();
        
    }
    
    public void setUpGUI(Container container)
  { 

        //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
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
setConstraints(0,1,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(panelICMP,cons);
panelICMP.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
panelICMP.add(chbNatPing,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
panelICMP.add(chbPingSw,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,-1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
panelICMP.add(lfTimeout,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,-1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
panelICMP.add(lfRetries,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,-1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
panelICMP.add(lfSwPack,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,-1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
panelICMP.add(lfSwSlInt,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,-1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
panelICMP.add(lfDebug,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
panelICMP.add(tfTimeout,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
panelICMP.add(tfRetries,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
panelICMP.add(tfSwPack,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
panelICMP.add(tfSwSlInt,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
panelICMP.add(tfDebug,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(imagePanel,cons);
imagePanel.setLayout(new BorderLayout(5,5));
imagePanel.add(lfImage,BorderLayout.WEST);
imagePanel.add(taICMP,BorderLayout.CENTER);
inset = new Insets(5,5,5,5);
setConstraints(0,2,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lfEmpty,cons);

  
//<End_setUpGUI_Container>
	chbNatPing.addActionListener(this);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
		okButton.setMnemonic(KeyEvent.VK_O); 
		cancelButton.setMnemonic(KeyEvent.VK_C); 

        chbNatPing.addKeyListener(new KeyAdapter()
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

        tfSwPack.addKeyListener(new KeyAdapter()
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

        tfSwSlInt.addKeyListener(new KeyAdapter()
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

        tfDebug.addKeyListener(new KeyAdapter()
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

        chbPingSw.addKeyListener(new KeyAdapter()
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
        
	this.setSize(350,350);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        this.setLocation((dim.width-350)/2,(dim.height-350)/2);
    } 

    public void setUpProperties()
  { 

        //<Begin_setUpProperties> 

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
            chbNatPing.setText(resourceBundle.getString("Native Ping"));
            chbNatPing.setToolTipText(resourceBundle.getString("Native Ping"));
            chbNatPing.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbNatPing,ex); 
          }

//<UserCode_Begin_Bean_chbNatPing>

//<UserCode_End_Bean_chbNatPing>

          try
          {
            chbPingSw.setText(resourceBundle.getString("ICMP Ping Sweep"));
            chbPingSw.setToolTipText(resourceBundle.getString("NativePing Sweep"));
            chbPingSw.setLabel(resourceBundle.getString("Native Ping Sweep"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbPingSw,ex); 
          }

//<UserCode_Begin_Bean_chbPingSw>

//<UserCode_End_Bean_chbPingSw>

          try
          {
            lfTimeout.setForeground(new Color(-16777211));
            lfTimeout.setText(resourceBundle.getString("Native Ping Timeout"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfTimeout,ex); 
          }

//<UserCode_Begin_Bean_lfTimeout>

//<UserCode_End_Bean_lfTimeout>

          try
          {
            lfRetries.setForeground(new Color(-16777210));
            lfRetries.setText(resourceBundle.getString("Native Ping Retries"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfRetries,ex); 
          }

//<UserCode_Begin_Bean_lfRetries>

//<UserCode_End_Bean_lfRetries>

          try
          {
            lfSwPack.setForeground(new Color(-16777213));
            lfSwPack.setText(resourceBundle.getString("Native Ping Sweep Packets"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfSwPack,ex); 
          }

//<UserCode_Begin_Bean_lfSwPack>

//<UserCode_End_Bean_lfSwPack>

          try
          {
            lfSwSlInt.setForeground(new Color(-16777216));
            lfSwSlInt.setText(resourceBundle.getString("Native Ping Sweep Sleep Interval"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfSwSlInt,ex); 
          }

//<UserCode_Begin_Bean_lfSwSlInt>

//<UserCode_End_Bean_lfSwSlInt>

          try
          {
            lfDebug.setForeground(new Color(-16777213));
            lfDebug.setText(resourceBundle.getString("Native Ping Debug  Level"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfDebug,ex); 
          }

//<UserCode_Begin_Bean_lfDebug>

//<UserCode_End_Bean_lfDebug>

          try
          {
            tfTimeout.setToolTipText(resourceBundle.getString("NativePing Timeout in seconds"));
            tfTimeout.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfTimeout,ex); 
          }

//<UserCode_Begin_Bean_tfTimeout>

//<UserCode_End_Bean_tfTimeout>

          try
          {
            tfRetries.setToolTipText(resourceBundle.getString("NativePing Retries"));
            tfRetries.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfRetries,ex); 
          }

//<UserCode_Begin_Bean_tfRetries>

//<UserCode_End_Bean_tfRetries>

          try
          {
            tfSwPack.setToolTipText(resourceBundle.getString("NativePing Sweep Packets"));
            tfSwPack.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfSwPack,ex); 
          }

//<UserCode_Begin_Bean_tfSwPack>

//<UserCode_End_Bean_tfSwPack>

          try
          {
            tfSwSlInt.setToolTipText(resourceBundle.getString("NativePing_Sweep_Sleep Interval in seconds"));
            tfSwSlInt.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfSwSlInt,ex); 
          }

//<UserCode_Begin_Bean_tfSwSlInt>

//<UserCode_End_Bean_tfSwSlInt>

          try
          {
            tfDebug.setToolTipText(resourceBundle.getString("NativePing Debug Level"));
            tfDebug.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfDebug,ex); 
          }

//<UserCode_Begin_Bean_tfDebug>

//<UserCode_End_Bean_tfDebug>

          try
          {
            lfImage.setHorizontalTextPosition(0);
            lfImage.setHorizontalAlignment(0);
            lfImage.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfImage,ex); 
          }

//<UserCode_Begin_Bean_lfImage>

//<UserCode_End_Bean_lfImage>

          try
          {
            taICMP.setText(resourceBundle.getString("Configure Native Ping parameters for discovery."));
            taICMP.setEditable(false);
            taICMP.setWrapStyleWord(true);
            taICMP.setLineWrap(true);
            taICMP.setFont(new Font("Dialog",0,10));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taICMP,ex); 
          }

//<UserCode_Begin_Bean_taICMP>

//<UserCode_End_Bean_taICMP>
		taICMP.setPreferredSize(new Dimension(taICMP.getPreferredSize().width+9,taICMP.getPreferredSize().height+5));

  
          //<End_setUpProperties>
		cancelButton.setNextFocusableComponent(chbNatPing);
        lfImage.setIcon(MainScreen.getCommonInterface().getImage("tips.png","images/runtimeadmin"));
        taICMP.setBackground(imagePanel.getBackground());
        taICMP.setForeground(Color.black);
        taICMP.setFont(new Font("Dialog",0,11));
	chbNatPing.setVisible(false);
        //okButton.setPreferredSize(cancelButton.getPreferredSize());
		} 

/**
     * Get the value of NativePing_Retries.
     * @return value of NativePing_Retries.
     */
    public String getRetries() 
    {
        return Retries;
    }
    
    /**
     * Set the value of NativePing_Retries.
     * @param v  Value to assign to NativePing_Retries.
     */
    public void setRetries(String ret) 
    {
        this.Retries = ret;
    }
    
    /**
     * Get the value of Timeout.
     * @return value of Timeout.
     */
    public String getTimeout() 
    {
        return Timeout;
    }
    
    /**
     * Set the value of Timeout.
     * @param v  Value to assign to Timeout.
     */
    public void setTimeout(String time) 
    {
        this.Timeout = time;
    }
    
    /**
     * Get the value of DebugLevel.
     * @return value of DebugLevel.
     */
    public String getDebugLevel() 
    {
        return DebugLevel;
    }
    
    /**
     * Set the value of DebugLevel.
     * @param v  Value to assign to DebugLevel.
     */
    public void setDebugLevel(String dLevel) 
    {
        this.DebugLevel = dLevel;
    }
    
    /**
     * Get the value of SweepPackets.
     * @return value of SweepPackets.
     */
    public String getSweepPackets() 
    {
        return SweepPackets;
    }
    
    /**
     * Set the value of SweepPackets.
     * @param v  Value to assign to SweepPackets.
     */
    public void setSweepPackets(String swPackets) 
    {
        this.SweepPackets = swPackets;
    }
        
    /**
     * Get the value of SweepSleepInterval.
     * @return value of SweepSleepInterval.
     */
    public String getSweepSleepInterval() 
    {
        return SweepSleepInterval;
    }
    
    /**
     * Set the value of SweepSleepInterval.
     * @param v  Value to assign to SweepSleepInterval.
     */
    public void setSweepSleepInterval(String swSleepInt) 
    {
        this.SweepSleepInterval = swSleepInt;
    }
        
    /**
     * Get the value of PingSweep.
     * @return value of PingSweep.
     */
    public String getPingSweep() 
    {
        return PingSweep;
    }
    
    /**
     * Set the value of PingSweep.
     * @param v  Value to assign to PingSweep.
     */
    public void setPingSweep(String pingSw) 
    {
        this.PingSweep = pingSw;
    }
   
    public XMLNode giveXMLNode()
    {
        Vector temp = sParser.getXMLNode("NATIVE_PING");
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
        if(tempNode!= null)
        {
            String time = (String)tempNode.getAttribute("ICMP_TIMEOUT");
            if(time != null)
            {
                tfTimeout.setText(time.trim());
            }
            String retries = (String)tempNode.getAttribute("ICMP_RETRIES");
            if(retries != null)
            {
                tfRetries.setText(retries.trim());
            }
            String swPack = (String)tempNode.getAttribute("ICMP_SWEEP_PACKETS");
            if(swPack != null)
            {
                tfSwPack.setText(swPack.trim());
            }
            String swSlInt = (String)tempNode.getAttribute("ICMP_SWEEP_SLEEP_INTERVAL");
            if(swSlInt != null)
            {
                tfSwSlInt.setText(swSlInt.trim());
            }
            String debug = (String)tempNode.getAttribute("ICMP_DEBUG_LEVEL");
            if(debug != null)
            {
                tfDebug.setText(debug.trim());
            }
            String pingSw = (String)tempNode.getAttribute("PING_SWEEP");
            if(pingSw!=null && pingSw.equals("true"))
            {
                chbPingSw.setSelected(true);
            }
        }
    }

    public XMLNode oldXMLNode()
    {
        populateObject();
        XMLNode node = new XMLNode();
        node.setNodeType(XMLNode.ELEMENT);
        node.setNodeName("NATIVE_PING");
        Hashtable hash = new Hashtable();
        if(tempNode != null)
        {
            if((String)tempNode.getAttribute("ICMP_TIMEOUT") != null)
            {
                hash.put("ICMP_TIMEOUT",(String)tempNode.getAttribute("ICMP_TIMEOUT"));
            }
            if((String)tempNode.getAttribute("ICMP_RETRIES") != null)
            {
                hash.put("ICMP_RETRIES",(String)tempNode.getAttribute("ICMP_RETRIES"));
            }
            if((String)tempNode.getAttribute("ICMP_SWEEP_PACKETS") != null)
            {
                hash.put("ICMP_SWEEP_PACKETS",(String)tempNode.getAttribute("ICMP_SWEEP_PACKETS"));
            }
            if((String)tempNode.getAttribute("ICMP_SWEEP_SLEEP_INTERVAL") != null)
            {
                hash.put("ICMP_SWEEP_SLEEP_INTERVAL",(String)tempNode.getAttribute("ICMP_SWEEP_SLEEP_INTERVAL"));
            }
            if((String)tempNode.getAttribute("ICMP_DEBUG_LEVEL") != null)
            {
                hash.put("ICMP_DEBUG_LEVEL",(String)tempNode.getAttribute("ICMP_DEBUG_LEVEL"));
            }
            if((String)tempNode.getAttribute("PING_SWEEP") != null)
            {
                hash.put("PING_SWEEP",(String)tempNode.getAttribute("PING_SWEEP"));
            }
        }
        node.setAttributeList(hash);
        return node;
    }
 
    public XMLNode addToXMLNode() 
    {
        XMLNode node = new XMLNode();
        node.setNodeType(XMLNode.ELEMENT);
        node.setNodeName("NATIVE_PING");
        
        if(tfTimeout.getText()!= null)
        {
            setTimeout(tfTimeout.getText());
        }
        else
        {
            setTimeout("");
        }
        if(getTimeout()== null)
        {
            node.setAttribute("ICMP_TIMEOUT","");
        }
        else
        {
            node.setAttribute("ICMP_TIMEOUT",getTimeout());
        }

        if(tfRetries.getText()!=null)
        {
            setRetries(tfRetries.getText());
        }
        else
        {
            setRetries("");
        }
        if(getRetries()==null)
        {
            node.setAttribute("ICMP_RETRIES","");
        }
        else
        {
            node.setAttribute("ICMP_RETRIES",getRetries());
        }

        if(tfSwPack.getText()!=null)
        {
            setSweepPackets(tfSwPack.getText());
        }
        else
        {
            setSweepPackets("");
        }
        if(getSweepPackets()==null)
        {
            node.setAttribute("ICMP_SWEEP_PACKETS","");
        }
        else
        {
            node.setAttribute("ICMP_SWEEP_PACKETS",getSweepPackets());
        }

        if(tfSwSlInt.getText()!=null)
        {
            setSweepSleepInterval(tfSwSlInt.getText());
        }
        else
        {
            setSweepSleepInterval("");
        }
        if(getSweepSleepInterval()==null)
        {
            node.setAttribute("ICMP_SWEEP_SLEEP_INTERVAL","");
        }
        else
        {
            node.setAttribute("ICMP_SWEEP_SLEEP_INTERVAL",getSweepSleepInterval());
        }

        if(tfDebug.getText()!=null)
        {
            setDebugLevel(tfDebug.getText());
        }
        else
        {
            setDebugLevel("");
        }
        if(getDebugLevel()==null)
        {
            node.setAttribute("ICMP_DEBUG_LEVEL","");
        }
        else
        {
            node.setAttribute("ICMP_DEBUG_LEVEL",getDebugLevel());
        }

        if(chbPingSw.isSelected())
        {
            setPingSweep("true");
        }
        else
        {
            setPingSweep("false");
        }
        if(getPingSweep()==null)
        {
            node.setAttribute("PING_SWEEP","");
        }
        else
        {
            node.setAttribute("PING_SWEEP",getPingSweep());
        }
        
        return node;
    }
 
    
    public void actionPerformed(ActionEvent ae)
    {
    if(chbNatPing.isSelected())
        {   
            lfTimeout.setEnabled(true); 
            lfRetries.setEnabled(true); 
            lfSwPack.setEnabled(true); 
            lfSwSlInt.setEnabled(true);
            lfDebug.setEnabled(true);
            tfTimeout.setEditable(true);
            tfRetries.setEditable(true);
            tfSwPack.setEditable(true);
            tfSwSlInt.setEditable(true);
            tfDebug.setEditable(true);
            chbPingSw.setEnabled(true);
        }
        else
        {
            lfTimeout.setEnabled(false);
            lfRetries.setEnabled(false);
            lfSwPack.setEnabled(false);
            lfSwSlInt.setEnabled(false);
            lfDebug.setEnabled(false);
            tfTimeout.setEditable(false);
            tfRetries.setEditable(false);
            tfSwPack.setEditable(false);
            tfSwSlInt.setEditable(false);
            tfDebug.setEditable(false);
            chbPingSw.setEnabled(false);
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
            tempNode.setAttribute("ICMP_TIMEOUT",tfTimeout.getText().trim());
            tempNode.setAttribute("ICMP_RETRIES",tfRetries.getText().trim());
            tempNode.setAttribute("ICMP_SWEEP_PACKETS",tfSwPack.getText().trim());
            tempNode.setAttribute("ICMP_SWEEP_SLEEP_INTERVAL",tfSwSlInt.getText().trim());
            tempNode.setAttribute("ICMP_DEBUG_LEVEL",tfDebug.getText().trim());
            tempNode.setAttribute("PING_SWEEP",new Boolean(chbPingSw.isSelected()).toString());
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
        this.setSize(getPreferredSize().width+628,getPreferredSize().height+430); 
          setTitle(resourceBundle.getString("NativePingScreen"));
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
        setTitle(resourceBundle.getString("Native Ping Properties"));
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

    public NativePingScreen(SeedParser parser,JFrame frame)
    {
//mtest	resourceBundle = com.adventnet.apiutils.Utility.getBundle(getParameter("RESOURCE_PROPERTIES"),applet);
        super(frame,true);
        sParser = parser;
        pack();
    }

    public NativePingScreen()
  {
        //<Begin_NativePingScreen>
    pack();
  
    //<End_NativePingScreen>
    }

    public NativePingScreen(java.applet.Applet applet)
  {
        //<Begin_NativePingScreen_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_NativePingScreen_java.applet.Applet>
    }

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}

























