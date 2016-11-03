
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$1
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

public class InitialParametersScreen extends JDialog implements ActionListener
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
	javax.swing.JLabel lfNatRetries = null;
	javax.swing.JLabel lfSNMPTime = null;
	javax.swing.JLabel lfICMPRet = null;
	javax.swing.JLabel lfDiscInt = null;
	javax.swing.JLabel lfRediscInt = null;
	javax.swing.JLabel lfSNMPRet = null;
	javax.swing.JLabel lfNatTime = null;
	javax.swing.JTextField tfDiscInt = null;
	javax.swing.JTextField tfRediscInt = null;
	javax.swing.JTextField tfICMPRet = null;
	javax.swing.JTextField tfSNMPTime = null;
	javax.swing.JTextField tfSNMPRet = null;
	javax.swing.JTextField tfNatTime = null;
	javax.swing.JTextField tfNatRet = null;
	javax.swing.JLabel emptyLabel2 = null;
	javax.swing.JLabel emptyLabel1 = null;
	javax.swing.JPanel imagePanel = null;
	javax.swing.JLabel lfImage = null;
	javax.swing.JTextArea taInit = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
    String DiscoveryInterval;
    String RediscoveryInterval;
    String ICMP_PingRetries;
    String SNMP_Timeout;
    String SNMP_Retries;
    String NativePing_Timeout;
    String NativePing_Retries;
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
    public void initVariables()
  { 

        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        butPanel= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        lfNatRetries= new javax.swing.JLabel();
        lfSNMPTime= new javax.swing.JLabel();
        lfICMPRet= new javax.swing.JLabel();
        lfDiscInt= new javax.swing.JLabel();
        lfRediscInt= new javax.swing.JLabel();
        lfSNMPRet= new javax.swing.JLabel();
        lfNatTime= new javax.swing.JLabel();
        tfDiscInt= new javax.swing.JTextField();
        tfRediscInt= new javax.swing.JTextField();
        tfICMPRet= new javax.swing.JTextField();
        tfSNMPTime= new javax.swing.JTextField();
        tfSNMPRet= new javax.swing.JTextField();
        tfNatTime= new javax.swing.JTextField();
        tfNatRet= new javax.swing.JTextField();
        emptyLabel2= new javax.swing.JLabel();
        emptyLabel1= new javax.swing.JLabel();
        imagePanel= new javax.swing.JPanel();
        lfImage= new javax.swing.JLabel();
        taInit= new javax.swing.JTextArea();

  
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
setConstraints(0,10,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(butPanel,cons);
butPanel.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,20);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
butPanel.add(okButton,cons);
inset = new Insets(10,20,10,10);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
butPanel.add(cancelButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,8,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(lfNatRetries,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(lfSNMPTime,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(lfICMPRet,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(lfDiscInt,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(lfRediscInt,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(lfSNMPRet,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(lfNatTime,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(tfDiscInt,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(tfRediscInt,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(tfICMPRet,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(tfSNMPTime,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(tfSNMPRet,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,7,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(tfNatTime,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,8,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(tfNatRet,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,9,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
Top.add(emptyLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(emptyLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(imagePanel,cons);
imagePanel.setLayout(new BorderLayout(5,5));
imagePanel.add(lfImage,BorderLayout.WEST);
imagePanel.add(taInit,BorderLayout.CENTER);

  
//<End_setUpGUI_Container>
okButton.addActionListener(this);
        cancelButton.addActionListener(this);
		okButton.setMnemonic(KeyEvent.VK_O); 
		cancelButton.setMnemonic(KeyEvent.VK_C); 
	  
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
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        OK_Clicked();
                    }
                } 
            });

        tfRediscInt.addKeyListener(new KeyAdapter()
            {
				public void keyTyped(KeyEvent ke) 
				{ 
                	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9' ) || (ke.getKeyChar()== ke.VK_BACK_SPACE) ||(ke.getKeyChar()=='-'))
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




	tfRediscInt.addFocusListener(new FocusAdapter()
		{
			public void focusLost(FocusEvent fe)
			{
				String text=tfRediscInt.getText().trim();
				int len =text.length();

				if(len==2)
				{
					 char firstIndex=text.charAt(0);
					 char secondIndex = text.charAt(1);
					if(firstIndex=='-' && secondIndex=='1')
					{
						//Nil

					}	

					else if (firstIndex!='-')
					{
						//Nil
					}
					
					else 
					{
                    				Toolkit.getDefaultToolkit().beep(); 
						tfRediscInt.setText("");
						tfRediscInt.requestFocus();
					}	
				}		




				else if(len>2)
				{
					 char first=text.charAt(0);
					if(first=='-')
					{
                    				Toolkit.getDefaultToolkit().beep(); 
						tfRediscInt.setText("");
						tfRediscInt.requestFocus();
				
					}



				}
					






			}
		});

        tfICMPRet.addKeyListener(new KeyAdapter()
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

        tfSNMPRet.addKeyListener(new KeyAdapter()
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

        tfSNMPTime.addKeyListener(new KeyAdapter()
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

        tfNatRet.addKeyListener(new KeyAdapter()
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

        tfNatTime.addKeyListener(new KeyAdapter()
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

        this.setSize(420,400);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        this.setLocation((dim.width-420)/2,(dim.height-400)/2);        

    }
    public void close()
    {
        this.dispose();
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

          okButton.setPreferredSize(new Dimension(83,27));
          
//<UserCode_End_Bean_okButton>

          try
          {
            cancelButton.setToolTipText(resourceBundle.getString("Cancel"));
            cancelButton.setBorder(new javax.swing.border.BevelBorder(0));
            cancelButton.setHorizontalTextPosition(0);
            cancelButton.setPreferredSize(new Dimension(73,27));
            cancelButton.setMinimumSize(new Dimension(73,27));
            cancelButton.setMaximumSize(new Dimension(73,27));
            cancelButton.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>

          cancelButton.setPreferredSize(new Dimension(83,27));
          
//<UserCode_End_Bean_cancelButton>

          try
          {
            lfNatRetries.setForeground(new Color(-16777211));
            lfNatRetries.setText(resourceBundle.getString("NativePing Retries"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfNatRetries,ex); 
          }

//<UserCode_Begin_Bean_lfNatRetries>

//<UserCode_End_Bean_lfNatRetries>

          try
          {
            lfSNMPTime.setForeground(new Color(-16777215));
            lfSNMPTime.setText(resourceBundle.getString("SNMP Timeout"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfSNMPTime,ex); 
          }

//<UserCode_Begin_Bean_lfSNMPTime>

//<UserCode_End_Bean_lfSNMPTime>

          try
          {
            lfICMPRet.setText(resourceBundle.getString("ICMP Ping Retries"));
            lfICMPRet.setForeground(new Color(-16777213));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfICMPRet,ex); 
          }

//<UserCode_Begin_Bean_lfICMPRet>

//<UserCode_End_Bean_lfICMPRet>

          try
          {
            lfDiscInt.setText(resourceBundle.getString("Discovery Interval"));
            lfDiscInt.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfDiscInt,ex); 
          }

//<UserCode_Begin_Bean_lfDiscInt>

//<UserCode_End_Bean_lfDiscInt>

          try
          {
            lfRediscInt.setText(resourceBundle.getString("Rediscovery Interval"));
            lfRediscInt.setForeground(new Color(-16777213));
            lfRediscInt.setToolTipText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfRediscInt,ex); 
          }

//<UserCode_Begin_Bean_lfRediscInt>

//<UserCode_End_Bean_lfRediscInt>

          try
          {
            lfSNMPRet.setForeground(new Color(-16777214));
            lfSNMPRet.setText(resourceBundle.getString("SNMP Retries"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfSNMPRet,ex); 
          }

//<UserCode_Begin_Bean_lfSNMPRet>

//<UserCode_End_Bean_lfSNMPRet>

          try
          {
            lfNatTime.setForeground(new Color(-16777214));
            lfNatTime.setText(resourceBundle.getString("NativePing Timeout"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfNatTime,ex); 
          }

//<UserCode_Begin_Bean_lfNatTime>

//<UserCode_End_Bean_lfNatTime>

          try
          {
            tfDiscInt.setText(resourceBundle.getString("0"));
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
            tfRediscInt.setText(resourceBundle.getString("1"));
            tfRediscInt.setToolTipText(resourceBundle.getString("Rediscovery Interval in hours"));
            tfRediscInt.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfRediscInt,ex); 
          }

//<UserCode_Begin_Bean_tfRediscInt>

//<UserCode_End_Bean_tfRediscInt>

          try
          {
            tfICMPRet.setText(resourceBundle.getString("0"));
            tfICMPRet.setToolTipText(resourceBundle.getString("ICMP Ping Retries"));
            tfICMPRet.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfICMPRet,ex); 
          }

//<UserCode_Begin_Bean_tfICMPRet>

//<UserCode_End_Bean_tfICMPRet>

          try
          {
            tfSNMPTime.setText(resourceBundle.getString("1"));
            tfSNMPTime.setToolTipText(resourceBundle.getString("SNMP Timeout"));
            tfSNMPTime.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfSNMPTime,ex); 
          }

//<UserCode_Begin_Bean_tfSNMPTime>

//<UserCode_End_Bean_tfSNMPTime>

          try
          {
            tfSNMPRet.setText(resourceBundle.getString("0"));
            tfSNMPRet.setToolTipText(resourceBundle.getString("SNMP Retries"));
            tfSNMPRet.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfSNMPRet,ex); 
          }

//<UserCode_Begin_Bean_tfSNMPRet>

//<UserCode_End_Bean_tfSNMPRet>

          try
          {
            tfNatTime.setText(resourceBundle.getString("1"));
            tfNatTime.setToolTipText(resourceBundle.getString("NativePing Timeout"));
            tfNatTime.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfNatTime,ex); 
          }

//<UserCode_Begin_Bean_tfNatTime>

//<UserCode_End_Bean_tfNatTime>

          try
          {
            tfNatRet.setText(resourceBundle.getString("1"));
            tfNatRet.setToolTipText(resourceBundle.getString("NativePing Retries"));
            tfNatRet.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfNatRet,ex); 
          }

//<UserCode_Begin_Bean_tfNatRet>

//<UserCode_End_Bean_tfNatRet>

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
            taInit.setWrapStyleWord(true);
            taInit.setLineWrap(true);
            taInit.setEditable(false);
            taInit.setText(resourceBundle.getString(" The discovery engine considers the parameters specified here during the first start of  the WebNMS server(either a cold or a warm start)."));
            taInit.setForeground(new Color(-16777216));
            taInit.setFont(new Font("Dialog",0,10));
            taInit.setRows(2);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taInit,ex); 
          }

//<UserCode_Begin_Bean_taInit>

//<UserCode_End_Bean_taInit>
		taInit.setPreferredSize(new Dimension(taInit.getPreferredSize().width+0,taInit.getPreferredSize().height+6));

  
          //<End_setUpProperties>
   	    cancelButton.setNextFocusableComponent(tfDiscInt);
        lfImage.setIcon(MainScreen.getCommonInterface().getImage("tips.png","images/runtimeadmin"));
        taInit.setBackground(imagePanel.getBackground());
        taInit.setForeground(Color.black);
        taInit.setFont(new Font("Dialog",0,11));
        //okButton.setPreferredSize(cancelButton.getPreferredSize());   
    } 
     
   public boolean isOK_Clicked()
    {
        return isOKclicked;
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
    public void setDiscoveryInterval(String  discInt) 
    {
        this.DiscoveryInterval = discInt;
    }
       
    /**
     * Get the value of RediscoveryInterval.
     * @return value of RediscoveryInterval.
     */
    public String getRediscoveryInterval() 
    {
        return RediscoveryInterval;
    }
    
    /**
     * Set the value of RediscoveryInterval.
     * @param v  Value to assign to RediscoveryInterval.
     */
    public void setRediscoveryInterval(String  rediscInt) 
    {
        this.RediscoveryInterval = rediscInt;
    }
     
    /**
     * Get the value of ICMP_PingRetries.
     * @return value of ICMP_PingRetries.
     */
    public String getICMP_PingRetries() 
    {
        return ICMP_PingRetries;
    }
    
    /**
     * Set the value of ICMP_PingRetries.
     * @param v  Value to assign to ICMP_PingRetries.
     */
    public void setICMP_PingRetries(String  icmpRet) 
    {
        this.ICMP_PingRetries = icmpRet;
    }
       
    /**
     * Get the value of SNMP_Timeout.
     * @return value of SNMP_Timeout.
     */
    public String getSNMP_Timeout() 
    {
        return SNMP_Timeout;
    }
    
    /**
     * Set the value of SNMP_Timeout.
     * @param v  Value to assign to SNMP_Timeout.
     */
    public void setSNMP_Timeout(String  snmpTime) 
    {
        this.SNMP_Timeout = snmpTime;
    }
       
    /**
     * Get the value of SNMP_Retries.
     * @return value of SNMP_Retries.
     */
    public String getSNMP_Retries() 
    {
        return SNMP_Retries;
    }
    
    /**
     * Set the value of SNMP_Retries.
     * @param v  Value to assign to SNMP_Retries.
     */
    public void setSNMP_Retries(String  snmpRet) 
    {
        this.SNMP_Retries = snmpRet;
    }
       
    /**
     * Get the value of NativePing_Timeout.
     * @return value of NativePing_Timeout.
     */
    public String getNativePing_Timeout() 
    {
        return NativePing_Timeout;
    }
    
    /**
     * Set the value of NativePing_Timeout.
     * @param v  Value to assign to NativePing_Timeout.
     */
    public void setNativePing_Timeout(String  nativeTime) 
    {
        this.NativePing_Timeout = nativeTime;
    }
       
    /**
     * Get the value of NativePing_Retries.
     * @return value of NativePing_Retries.
     */
    public String getNativePing_Retries() 
    {
        return NativePing_Retries;
    }
    
    /**
     * Set the value of NativePing_Retries.
     * @param v  Value to assign to NativePing_Retries.
     */
    public void setNativePing_Retries(String  nativeRet) 
    {
        this.NativePing_Retries = nativeRet;
    }
        
    public XMLNode addToXMLNode()
    {
        XMLNode node = new XMLNode();
        node.setNodeType(XMLNode.ELEMENT);
        node.setNodeName("INITIAL_PARAMETERS");
        
        if(tfDiscInt.getText()!= null)
        {
            setDiscoveryInterval(tfDiscInt.getText().trim());
        }
        else
        {
            setDiscoveryInterval("");
        }
        if(getDiscoveryInterval()== null)
        {
            node.setAttribute("DISCOVERY_INTERVAL","");
        }
        else
        {
            node.setAttribute("DISCOVERY_INTERVAL",getDiscoveryInterval());
        }
        
        if(tfRediscInt.getText()!= null)
        {
            setRediscoveryInterval(tfRediscInt.getText().trim());
        }
        else
        {
            setRediscoveryInterval("");
        }
        if(getRediscoveryInterval()== null)
        {
            node.setAttribute("REDISCOVER_INTERVAL","");
        }
        else
        {
            node.setAttribute("REDISCOVER_INTERVAL",getRediscoveryInterval());
        }

        if(tfICMPRet.getText()!= null)
        {
            setICMP_PingRetries(tfICMPRet.getText().trim());
        }
        else
        {
            setICMP_PingRetries("");
        }
        if(getICMP_PingRetries()== null)
        {
            node.setAttribute("PING_RETRIES","");
        }
        else
        {
            node.setAttribute("PING_RETRIES",getICMP_PingRetries());
        }

        if(tfSNMPTime.getText()!= null)
        {
            setSNMP_Timeout(tfSNMPTime.getText().trim());
        }
        else
        {
            setSNMP_Timeout("");
        }
        if(getSNMP_Timeout()== null)
        {
            node.setAttribute("SNMP_TIMEOUT","");
        }
        else
        {
            node.setAttribute("SNMP_TIMEOUT",getSNMP_Timeout());
        }

        if(tfSNMPRet.getText()!= null)
        {
            setSNMP_Retries(tfSNMPRet.getText().trim());
        }
        else
        {
            setSNMP_Retries("");
        }
        if(getSNMP_Retries()== null)
        {
            node.setAttribute("SNMP_RETRIES","");
        }
        else
        {
            node.setAttribute("SNMP_RETRIES",getSNMP_Retries());
        }

        if(tfNatTime.getText()!= null)
        {
            setNativePing_Timeout(tfNatTime.getText().trim());
        }
        else
        {
            setNativePing_Timeout("");
        }
        if(getNativePing_Timeout()== null)
        {
            node.setAttribute("ICMP_TIMEOUT","");
        }
        else
        {
            node.setAttribute("ICMP_TIMEOUT",getNativePing_Timeout());
        }
         
        if(tfNatRet.getText()!= null)
        {
            setNativePing_Retries(tfNatRet.getText().trim());
        }
        else
        {
            setNativePing_Retries("");
        }
        if(getNativePing_Retries()== null)
        {
            node.setAttribute("ICMP_RETRIES","");
        }
        else
        {
            node.setAttribute("ICMP_RETRIES",getNativePing_Retries());
        }

        return node;
    }

    public XMLNode giveXMLNode()
    {
        Vector temp = sParser.getXMLNode("INITIAL_PARAMETERS");
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
    public XMLNode oldXMLNode()
    {
        populateObject();
        Hashtable hash = new Hashtable();
        if(tempNode != null)
        {
            if((String)tempNode.getAttribute("REDISCOVER_INTERVAL") != null)
            {
                hash.put("REDISCOVER_INTERVAL",(String)tempNode.getAttribute("REDISCOVER_INTERVAL"));
            }
            if((String)tempNode.getAttribute("DISCOVERY_INTERVAL") != null)
            {
                hash.put("DISCOVERY_INTERVAL",(String)tempNode.getAttribute("DISCOVERY_INTERVAL"));
            }
            if((String)tempNode.getAttribute("PING_RETRIES") != null)
            {
                hash.put("PING_RETRIES",(String)tempNode.getAttribute("PING_RETRIES"));
            }
            if((String)tempNode.getAttribute("SNMP_TIMEOUT") != null)
            {
                hash.put("SNMP_TIMEOUT",(String)tempNode.getAttribute("SNMP_TIMEOUT"));
            }
            if((String)tempNode.getAttribute("SNMP_RETRIES") != null)
            {
                hash.put("SNMP_RETRIES",(String)tempNode.getAttribute("SNMP_RETRIES"));
            }
            if((String)tempNode.getAttribute("ICMP_TIMEOUT") != null)
            {
                hash.put("ICMP_TIMEOUT",(String)tempNode.getAttribute("ICMP_TIMEOUT"));
            }
            if((String)tempNode.getAttribute("ICMP_RETRIES") != null)
            {
                hash.put("ICMP_RETRIES",(String)tempNode.getAttribute("ICMP_RETRIES"));
            }
            tempNode.setAttributeList(hash);
        }
        return tempNode;
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
            String discInt = (String)tempNode.getAttribute("DISCOVERY_INTERVAL");
            if(discInt != null)
            {
                tfDiscInt.setText(discInt.trim());
            }
            String rediscInt = (String)tempNode.getAttribute("REDISCOVER_INTERVAL"); 
            if(rediscInt != null)
            {
                tfRediscInt.setText(rediscInt.trim());
            }
            String pingRet = (String)tempNode.getAttribute("PING_RETRIES");
            if(pingRet != null)
            {
                tfICMPRet.setText(pingRet.trim());
            }
            String snmpTime = (String)tempNode.getAttribute("SNMP_TIMEOUT");
            if(snmpTime != null)
            {
                tfSNMPTime.setText(snmpTime.trim());
            }
            String snmpRet = (String)tempNode.getAttribute("SNMP_RETRIES");
            if(snmpRet != null)
            {
                tfSNMPRet.setText(snmpRet.trim());
            }
            String natTime = (String)tempNode.getAttribute("ICMP_TIMEOUT");
            if(natTime != null)
            {
                tfNatTime.setText(natTime.trim());
            }
            String natRet = (String)tempNode.getAttribute("ICMP_RETRIES");
            if(natRet != null)
            {
                tfNatRet.setText(natRet.trim());
            }
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
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
            tempNode.setAttribute("DISCOVERY_INTERVAL",tfDiscInt.getText().trim());
            tempNode.setAttribute("REDISCOVER_INTERVAL",tfRediscInt.getText().trim());
            tempNode.setAttribute("PING_RETRIES",tfICMPRet.getText().trim());
            tempNode.setAttribute("SNMP_TIMEOUT",tfSNMPTime.getText().trim());
            tempNode.setAttribute("SNMP_RETRIES",tfSNMPRet.getText().trim());
            tempNode.setAttribute("ICMP_TIMEOUT",tfNatTime.getText().trim());
            tempNode.setAttribute("ICMP_RETRIES",tfNatRet.getText().trim());
        }
		MainScreen.setApplyButton(true);
        this.setVisible(false);
    }

    public void Cancel_Clicked()
    {
        isOKclicked = false;
        isModified = false;
        this.setVisible(false);
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
            if (input.equals("HOST")) value = "localhost"; 
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
        this.setSize(getPreferredSize().width+640,getPreferredSize().height+433); 
          setTitle(resourceBundle.getString("InitialParametersScreen"));
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
        setTitle(resourceBundle.getString("Initial Parameters"));
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

    public InitialParametersScreen(SeedParser parser,JFrame frame)
    {
	//mtest	resourceBundle = com.adventnet.apiutils.Utility.getBundle(getParameter("RESOURCE_PROPERTIES"),applet);
        super(frame,true);
        sParser = parser;
        pack();
    }

    public InitialParametersScreen()
  {
        //<Begin_InitialParametersScreen>
    pack();
  
    //<End_InitialParametersScreen>
    }

    public InitialParametersScreen(java.applet.Applet applet)
  {
        //<Begin_InitialParametersScreen_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_InitialParametersScreen_java.applet.Applet>
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

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}














