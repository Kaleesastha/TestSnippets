// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.launcher.nms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ShutdownSettings extends JDialog implements ActionListener,ItemListener
{
    //<Begin_Variable_Declarations>
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    private String localePropertiesFileName = "ShutDownNmsServerResources";
    static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
    private boolean running=false;
    javax.swing.JPanel Top = null;
    javax.swing.JPanel butPanel = null;
    javax.swing.JButton okButton = null;
    javax.swing.JButton cancelButton = null;
    javax.swing.JPanel mainPanel = null;
    javax.swing.JPanel optionPanel = null;
    javax.swing.JRadioButton rbWeb = null;
    javax.swing.JRadioButton rbRMI = null;
    javax.swing.JRadioButton rbTCP = null;
    javax.swing.JLabel lfPort = null;
    private javax.swing.JTextField tfPort = null;
    javax.swing.JLabel JLabel1 = null;
    GridBagConstraints cons = new GridBagConstraints();
    Insets inset;
    //<End_Variable_Declarations>
    ButtonGroup butGroup = null;
    private String vals1="";
    private String vals2="";
    private String vals3="";
    private ShutdownServer shDownSer = null;
    private String tcpPort = "";
    private String rmiPort = "";
    private String httpPort = "";

    public ShutdownSettings(ShutdownServer s)
    {
	super(s);
	this.shDownSer = s;
	
        //<Begin_ShutdownSettings>
        //<End_ShutdownSettings>
	this.init();
	this.setModal(true);
    }

    public ShutdownSettings()
    {
        //<Begin_ShutdownSettings>
        pack();
  
        //<End_ShutdownSettings>
    }

    public ShutdownSettings(java.applet.Applet applet)
    {
        //<Begin_ShutdownSettings_java.applet.Applet>
        this.applet = applet;
        pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
        //<End_ShutdownSettings_java.applet.Applet>
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

    public void init()
    {
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
            localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+434,getPreferredSize().height+202); 
        setTitle("Mode of Shutdown");
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
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 


  
        //<End_init>
        setTitle(resourceBundle.getString("Mode of Shutdown"));  
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
            if (input.equals("MS_MODE")) value = "3"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "ShutDownNmsServerResources"; 
        }
        return value;

  
        //<End_getParameter_String>
    } 
    public void setUpProperties()
    {
        //<Begin_setUpProperties> 

        try
        {
            butPanel.setBorder(new javax.swing.border.EtchedBorder(1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+butPanel,ex); 
        }

        try
        {
            okButton.setFont(new Font("Dialog",1,12));
            okButton.setHorizontalTextPosition(0);
            okButton.setText(resourceBundle.getString("OK"));
            okButton.setBorder(new javax.swing.border.BevelBorder(0));
            okButton.setMaximumSize(new Dimension(73,27));
            okButton.setMinimumSize(new Dimension(73,27));
            okButton.setPreferredSize(new Dimension(73,27));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); 
        }

        try
        {
            cancelButton.setHorizontalTextPosition(4);
            cancelButton.setFont(new Font("Dialog",1,12));
            cancelButton.setBorder(new javax.swing.border.BevelBorder(0));
            cancelButton.setLabel(resourceBundle.getString("Cancel"));
            cancelButton.setPreferredSize(new Dimension(73,27));
            cancelButton.setMinimumSize(new Dimension(73,27));
            cancelButton.setMaximumSize(new Dimension(73,27));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
        }

        try
        {
            mainPanel.setBorder(new javax.swing.border.EtchedBorder(1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+mainPanel,ex); 
        }

        try
        {
            optionPanel.setFont(new Font("Dialog",0,12));
            optionPanel.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),"Select Mode of Shutdown",2,0,new Font("Dialog",1,12),new Color(-16777216)));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+optionPanel,ex); 
        }

        try
        {
            rbWeb.setHorizontalTextPosition(4);
            rbWeb.setHorizontalAlignment(2);
            rbWeb.setVerticalAlignment(0);
            rbWeb.setVerticalTextPosition(0);
            rbWeb.setFont(new Font("Dialog",1,12));
            rbWeb.setText(resourceBundle.getString("We"));
            rbWeb.setLabel(resourceBundle.getString("Web Server Port"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbWeb,ex); 
        }

        try
        {
            rbRMI.setHorizontalTextPosition(4);
            rbRMI.setHorizontalAlignment(2);
            rbRMI.setVerticalAlignment(0);
            rbRMI.setVerticalTextPosition(0);
            rbRMI.setFont(new Font("Dialog",1,12));
            rbRMI.setLabel(resourceBundle.getString("RMI Port"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbRMI,ex); 
        }

        try
        {
            rbTCP.setHorizontalTextPosition(4);
            rbTCP.setHorizontalAlignment(2);
            rbTCP.setVerticalAlignment(0);
            rbTCP.setVerticalTextPosition(0);
            rbTCP.setText(resourceBundle.getString("TCP Port"));
            rbTCP.setFont(new Font("Dialog",1,12));
            rbTCP.setSelected(true);
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbTCP,ex); 
        }

        try
        {
            lfPort.setHorizontalAlignment(2);
            lfPort.setForeground(new Color(-16777216));
            lfPort.setHorizontalTextPosition(4);
            lfPort.setFont(new Font("Dialog",1,12));
            lfPort.setText(resourceBundle.getString("Port Number"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfPort,ex); 
        }

        try
        {
            tfPort.setHorizontalAlignment(2);
            tfPort.setFont(new Font("SansSerif",0,12));
            tfPort.setBorder(new javax.swing.border.BevelBorder(1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfPort,ex); 
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
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
        }

  
        //<End_setUpProperties>
        optionPanel.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Select Mode of Shutdown"),2,0,new Font("Dialog",1,12),new Color(-16777216)));
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
        butPanel= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        mainPanel= new javax.swing.JPanel();
        optionPanel= new javax.swing.JPanel();
        rbWeb= new javax.swing.JRadioButton();
        rbRMI= new javax.swing.JRadioButton();
        rbTCP= new javax.swing.JRadioButton();
        lfPort= new javax.swing.JLabel();
        tfPort= new javax.swing.JTextField();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
	butGroup = new javax.swing.ButtonGroup();
    } 
    public void setUpGUI(Container container)
    {
        //<Begin_setUpGUI_Container> 
        container.add(Top,BorderLayout.CENTER);
        Top.setLayout(new BorderLayout(5,5));
        Top.add(butPanel,BorderLayout.SOUTH);
        butPanel.setLayout(new FlowLayout(1,15,10));
        butPanel.add(okButton);
        butPanel.add(cancelButton);
        Top.add(mainPanel,BorderLayout.CENTER);
        mainPanel.setLayout(new GridBagLayout());
        inset = new Insets(5,5,5,5);
        setConstraints(0,0,0,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
        mainPanel.add(optionPanel,cons);
        optionPanel.setLayout(new FlowLayout(1,15,5));
        optionPanel.add(rbWeb);
        optionPanel.add(rbRMI);
        optionPanel.add(rbTCP);
        inset = new Insets(5,5,5,5);
        setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
        mainPanel.add(lfPort,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(1,1,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
        mainPanel.add(tfPort,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(0,2,0,0,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
        mainPanel.add(JLabel1,cons);

  
        //<End_setUpGUI_Container>
        butGroup.add(rbWeb);
	butGroup.add(rbRMI);
	butGroup.add(rbTCP);	
	rbWeb.addActionListener(this);
        rbWeb.addItemListener(this);  
        rbRMI.addActionListener(this);
        rbRMI.addItemListener(this);
        rbTCP.addActionListener(this);
        rbTCP.addItemListener(this);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        tfPort.addKeyListener(new KeyAdapter()
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
        this.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        checkValues();
                    }
                    else if(ke.getKeyCode()== ke.VK_ESCAPE)
                    {
                        setVisible(false);
                    }
                } 
            });
        
        this.setSize(415,200);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        this.setLocation((d.width-415)/2,(d.height-200)/2);
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

    public void setValues(String wValue,String rValue,String tValue)
    {
        if(!wValue.trim().equals(""))
        {
            rbWeb.setSelected(true);
            tfPort.setText(wValue);
            vals1 = wValue;
        }

        if(!rValue.trim().equals(""))
        {
            rbRMI.setSelected(true);
            tfPort.setText(rValue);
            vals2 = rValue;
        }

        if(!tValue.trim().equals(""))
        {
            rbTCP.setSelected(true);
            tfPort.setText(tValue);
            vals3 = tValue;
        }
    }

    public void checkValues()
    {
        String ptValue = tfPort.getText();
        if(ptValue.trim().equals(""))
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("Port Number cannot be null"),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String tcpValue=tcpPort;
        String rmiValue=rmiPort;
        String webValue =httpPort;
	    
        if(rbWeb.isSelected())
        {
            webValue = ptValue;

        }
        else if (rbRMI.isSelected())
        {
            rmiValue = ptValue;

        }
        else if (rbTCP.isSelected())
        {
            tcpValue = ptValue;
        }
	    
        //Set the values to the parent dialog
        shDownSer.setPortValues(webValue,rmiValue,tcpValue);
        setVisible(false);
    }

    public void  actionPerformed(ActionEvent ev)
    {

        Object src = ev.getSource();

        if (src == cancelButton)
        {
            setVisible(false);
        }	    
		    
        else if(src == okButton)
        {
            checkValues();			    
        }
        else if (src == rbWeb)
        {
            String webPortValue = vals1;
            tfPort.setText(webPortValue);
        }
        else if (src == rbRMI)
        {
            String rmiPortValue=vals2;
            tfPort.setText(rmiPortValue);
        }
        else if (src == rbTCP)
        {			    
            String tcpPortValue=vals3;
            tfPort.setText(tcpPortValue);
        }
    }

    public void itemStateChanged(ItemEvent e)
    {
        if(e.getSource() == rbTCP)
        {
            tcpPort = tfPort.getText();
        }
        else if(e.getSource() == rbRMI)
        {
            rmiPort = tfPort.getText();
        }
        else if(e.getSource() == rbWeb)
        {
            httpPort = tfPort.getText();
        }
    }
}
























