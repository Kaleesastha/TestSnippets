//$Id: SNMPV3propScreen.java,v 1.2 2007/07/03 18:18:22 aravinds Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$23
//<End_Version>
package com.adventnet.nms.runtimeconfig;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.XMLNode;
import java.util.*;

public class SNMPV3propScreen extends JDialog implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JTabbedPane v3TabPane = null;
	javax.swing.JPanel general = null;
	javax.swing.JLabel lUserName = null;
	javax.swing.JLabel lContextName = null;
	javax.swing.JLabel lAgentPort = null;
	javax.swing.JTextField tfUserName = null;
	javax.swing.JTextField tfContextName = null;
	javax.swing.JTextField tfAgentPort = null;
	javax.swing.JPanel security = null;
	javax.swing.JLabel lSecLevel = null;
	javax.swing.JLabel lAuthPr = null;
	javax.swing.JLabel lAuthPass = null;
	javax.swing.JLabel lPrivPr = null;
	javax.swing.JLabel lPrivPass = null;
	javax.swing.JComboBox comboSecLevel = null;
	javax.swing.JComboBox comboAuthPr = null;
	javax.swing.JPasswordField pwdAuthPass = null;
	javax.swing.JComboBox comboPrivPr = null;
	javax.swing.JPasswordField pwdPrivPass = null;
	javax.swing.JPanel buttonPanel = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	//vkarthik
	public String sUserName = null;
	public String sHostName = null;
	public String sContextName = null;
	public String sAgentPort = null;
	public String sSecurityLevel = null;
	public String sAuthProtocol = null;
	public String sAuthPwd = null;
	public String sPrivProtocol = null;
	public String sPrivPwd = null;
	public boolean isV1V2Selected = false;
	public boolean isV3Selected = false;
	public XMLNode tempXmlNode = null;
	public String req = null;
	public Vector rowdata = null;
	Vector v = null;
 


 

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
this.setModal(true);
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
        this.setSize(getPreferredSize().width+412,getPreferredSize().height+308); 
          setTitle(resourceBundle.getString("Snmp V3 Properties"));
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
 //setTitle(resourceBundle.getString("SNMP Properties"));
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
 
 //added by vkarthik - starts
  public void setUserName(String userName)
  {
  	this.sUserName = userName;
  	this.tfUserName.setText(userName);
  }
  public String getUserName()
  {
  	return sUserName;     
  }
  public void setHostName(String hostName)
  {
  	this.sHostName = hostName;
  	
  }
  public String getHostName()
  {
  	 return sHostName;     
  }
  
  public void setAgentPort(String agentPort)
  {
  	this.sAgentPort= agentPort;
  	
  	this.tfAgentPort.setText(agentPort);
  }
  public String getAgentPort()
  {
  	return sAgentPort;     
  }
 
   public void setContextName(String contextName )
  {
  	this.sContextName= contextName;
  	tfContextName.setText(contextName);
  }
  public String getContextName ()
  {
  	return sContextName;     
  }
   public void setSecurityLevel(String secLevel)
  {
  	this.sSecurityLevel = secLevel;
  	this.comboSecLevel.setSelectedItem(secLevel);     
  }
  public String getSecurityLevel()
  {
  	return sSecurityLevel;     
  }
   public void setAuthProtocol (String authPr)
  {
  	this.sAuthProtocol = authPr; 
  	this.comboAuthPr.setSelectedItem(authPr);        
  }
  public String getAuthProtocol()
  {
  	return sAuthProtocol;     
  }
   public void setAuthPwd (String authPwd )
  {
  	this.sAuthPwd = authPwd;   
  	this.pwdAuthPass.setText(authPwd);  
  }
  public String getAuthPwd()
  {
  	return sAuthPwd;     
  }
   public void setPrivProtocol(String privPr)
  {
  	this.sPrivProtocol = privPr;
  	this.comboPrivPr.setSelectedItem(privPr);     
  }
  public String getPrivProtocol()
  {
  	return  sPrivProtocol;    
  }
   public void setPrivPwd(String privPwd)
  {
  	this.sPrivPwd = privPwd; 
  	this.pwdPrivPass.setText(privPwd);
  }
  public String getPrivPwd()
  {
  	return sPrivPwd;
  } 
  
//added by vkarthik ends  
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
  public void setUpProperties()
  {
       
  //<Begin_setUpProperties> 

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
            general.setName(resourceBundle.getString("General"));
            general.setToolTipText(resourceBundle.getString("General Parameters"));
            general.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+general,ex); 
          }

//<UserCode_Begin_Bean_general>

//<UserCode_End_Bean_general>

          try
          {
            lUserName.setForeground(new Color(-16777216));
            lUserName.setText(resourceBundle.getString("User Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lUserName,ex); 
          }

//<UserCode_Begin_Bean_lUserName>

//<UserCode_End_Bean_lUserName>

          try
          {
            lContextName.setForeground(new Color(-16777216));
            lContextName.setText(resourceBundle.getString("Context Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lContextName,ex); 
          }

//<UserCode_Begin_Bean_lContextName>

//<UserCode_End_Bean_lContextName>

          try
          {
            lAgentPort.setForeground(new Color(-16777216));
            lAgentPort.setText(resourceBundle.getString("Agent Port"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lAgentPort,ex); 
          }

//<UserCode_Begin_Bean_lAgentPort>

//<UserCode_End_Bean_lAgentPort>

          try
          {
            tfUserName.setEditable(true);
            tfUserName.setEnabled(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfUserName,ex); 
          }

//<UserCode_Begin_Bean_tfUserName>

//<UserCode_End_Bean_tfUserName>

          try
          {
            tfContextName.setEditable(true);
            tfContextName.setEnabled(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfContextName,ex); 
          }

//<UserCode_Begin_Bean_tfContextName>

//<UserCode_End_Bean_tfContextName>

          try
          {
            security.setName(resourceBundle.getString("Security"));
            security.setToolTipText(resourceBundle.getString("Security parameters"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+security,ex); 
          }

//<UserCode_Begin_Bean_security>
 
//<UserCode_End_Bean_security>

          try
          {
            lSecLevel.setText(resourceBundle.getString("Security Level"));
            lSecLevel.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lSecLevel,ex); 
          }

//<UserCode_Begin_Bean_lSecLevel>

//<UserCode_End_Bean_lSecLevel>

          try
          {
            lAuthPr.setText(resourceBundle.getString("Auth Protocol"));
            lAuthPr.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lAuthPr,ex); 
          }

//<UserCode_Begin_Bean_lAuthPr>

//<UserCode_End_Bean_lAuthPr>

          try
          {
            lAuthPass.setText(resourceBundle.getString("Auth Password"));
            lAuthPass.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lAuthPass,ex); 
          }

//<UserCode_Begin_Bean_lAuthPass>

//<UserCode_End_Bean_lAuthPass>

          try
          {
            lPrivPr.setText(resourceBundle.getString("Priv Protocol"));
            lPrivPr.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lPrivPr,ex); 
          }

//<UserCode_Begin_Bean_lPrivPr>

//<UserCode_End_Bean_lPrivPr>

          try
          {
            lPrivPass.setText(resourceBundle.getString("Priv Password"));
            lPrivPass.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lPrivPass,ex); 
          }

//<UserCode_Begin_Bean_lPrivPass>

//<UserCode_End_Bean_lPrivPass>

          try
          {
            comboSecLevel.setActionCommand(resourceBundle.getString("comboSecLevel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+comboSecLevel,ex); 
          }

//<UserCode_Begin_Bean_comboSecLevel>

comboSecLevel.addItem("NoAuthNoPriv");
comboSecLevel.addItem("AuthNoPriv");
comboSecLevel.addItem("AuthPriv");
//<UserCode_End_Bean_comboSecLevel>

//<UserCode_Begin_Bean_comboAuthPr>
comboAuthPr.addItem("MD5");
comboAuthPr.addItem("SHA");

//<UserCode_End_Bean_comboAuthPr>

//<UserCode_Begin_Bean_comboPrivPr>
comboPrivPr.addItem("CBC-DES"); //No I18N
comboPrivPr.addItem("CFB-AES-128"); //No I18N
//<UserCode_End_Bean_comboPrivPr>

          try
          {
            okButton.setText(resourceBundle.getString("OK")); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); //No I18N 
          }

//<UserCode_Begin_Bean_okButton>

//<UserCode_End_Bean_okButton>

          try
          {
            cancelButton.setText(resourceBundle.getString("Cancel")); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); //No I18N
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        v3TabPane= new javax.swing.JTabbedPane();
        general= new javax.swing.JPanel();
        lUserName= new javax.swing.JLabel();
        lContextName= new javax.swing.JLabel();
        lAgentPort= new javax.swing.JLabel();
        tfUserName= new javax.swing.JTextField();
        tfContextName= new javax.swing.JTextField();
        tfAgentPort= new javax.swing.JTextField();
        security= new javax.swing.JPanel();
        lSecLevel= new javax.swing.JLabel();
        lAuthPr= new javax.swing.JLabel();
        lAuthPass= new javax.swing.JLabel();
        lPrivPr= new javax.swing.JLabel();
        lPrivPass= new javax.swing.JLabel();
        comboSecLevel= new javax.swing.JComboBox();
        comboAuthPr= new javax.swing.JComboBox();
        pwdAuthPass= new javax.swing.JPasswordField();
        comboPrivPr= new javax.swing.JComboBox();
        pwdPrivPass= new javax.swing.JPasswordField();
        buttonPanel= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();

  
        //<End_initVariables>
//rowdata = new Vector();
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(null);
v3TabPane.setBounds(0,5,400,250);
Top.add(v3TabPane);
v3TabPane.addTab(resourceBundle.getString("General"),null,general,"General Properties");
general.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
general.add(lUserName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
general.add(lContextName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
general.add(lAgentPort,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
general.add(tfUserName,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
general.add(tfContextName,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
general.add(tfAgentPort,cons);
v3TabPane.addTab(resourceBundle.getString("Security"),null,security,"Security Properties");
security.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
security.add(lSecLevel,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
security.add(lAuthPr,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
security.add(lAuthPass,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
security.add(lPrivPr,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
security.add(lPrivPass,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
security.add(comboSecLevel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
security.add(comboAuthPr,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
security.add(pwdAuthPass,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
security.add(comboPrivPr,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
security.add(pwdPrivPass,cons);
buttonPanel.setBounds(0,255,405,45);
Top.add(buttonPanel);
buttonPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
buttonPanel.add(okButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
buttonPanel.add(cancelButton,cons);

  
//<End_setUpGUI_Container>
 okButton.addActionListener(this);
 cancelButton.addActionListener(this);
 comboSecLevel.addActionListener(this);
  //this.setModal(true);
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
  public SNMPV3propScreen()
  {
       
    //<Begin_SNMPV3propScreen>
    pack();
  
    //<End_SNMPV3propScreen>
  }

  public SNMPV3propScreen(java.applet.Applet applet)
  {
    //<Begin_SNMPV3propScreen_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_SNMPV3propScreen_java.applet.Applet>


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
         
	if(ae.getActionCommand().equals("OK"))
	{
    		rowdata = new Vector();
    		okButtonActionPerformed();     
	}
	else if(ae.getActionCommand().equals("Cancel"))
	{
		this.setVisible(false);
		// THEME-II Start
		dispose();
		// THEME-II End
	}
	  	if(ae.getActionCommand().equals("comboSecLevel"))
  	{
  		if(((String)comboSecLevel.getSelectedItem()).equals("AuthPriv"))  
  		{
  		      	lAuthPr.setEnabled(true);
  		      	lAuthPass.setEnabled(true);
  		      	comboAuthPr.setEnabled(true);
  		      	pwdAuthPass.setEnabled(true);
		      	lPrivPr.setEnabled(true);
  			lPrivPass.setEnabled(true);
  			comboPrivPr.setEnabled(true);
  			pwdPrivPass.setEnabled(true);  			     
  		}   
  		if(((String)comboSecLevel.getSelectedItem()).equals("AuthNoPriv"))  
  		{
  			lAuthPr.setEnabled(true);
  		      	lAuthPass.setEnabled(true);
  		      	comboAuthPr.setEnabled(true);
  		      	pwdAuthPass.setEnabled(true);
  			lPrivPr.setEnabled(false);
  			lPrivPass.setEnabled(false);
  			comboPrivPr.setEnabled(false);
  			pwdPrivPass.setEnabled(false);
  			pwdPrivPass.setText("");
  		}  
  		if(((String)comboSecLevel.getSelectedItem()).equals("NoAuthNoPriv"))  
  		{
  		      	lAuthPr.setEnabled(false);
  		      	lAuthPass.setEnabled(false);
  		      	comboAuthPr.setEnabled(false);
  		      	pwdAuthPass.setText("");
  		      	pwdAuthPass.setEnabled(false);
  		      	lPrivPr.setEnabled(false);
  			lPrivPass.setEnabled(false);
  			comboPrivPr.setEnabled(false);
  			pwdPrivPass.setEnabled(false); 
  			pwdPrivPass.setText("");
  		}
  		
  	}

}

	public void okButtonActionPerformed()
	{
		rowdata.removeAllElements();
		if( ( sUserName = tfUserName.getText().trim()) != null)
		{
			rowdata.addElement(sUserName);
			
		}
		if( ( sContextName = tfContextName.getText().trim()) != null)
		{
			rowdata.addElement(sContextName);
		}
	           if((sAgentPort = tfAgentPort.getText().trim())!=null)
	           {
			    rowdata.addElement(sAgentPort);
	           }
	           
	          /*	if((sSecurityLevel = (String)comboSecLevel.getSelectedItem()) !=null)
		{
			    rowdata.addElement(sSecurityLevel);
		}
		if( (sAuthProtocol =  (String)comboAuthPr.getSelectedItem()) !=null)
		{
			     rowdata.addElement(sAuthProtocol);
		}
		if( (sAuthPwd =  pwdAuthPass.getText().trim())!=null)
		{
			    rowdata.addElement(sAuthPwd);
		}
		if( (sPrivProtocol = tfPrivPr.getText().trim()) != null)
		{
			    rowdata.addElement(sPrivProtocol);
		}
		if((sPrivPwd = pwdPrivPass.getText().trim())!=null)
		{
			    rowdata.addElement(sPrivPwd);
		}*/
		
		if( (sSecurityLevel= (String)comboSecLevel.getSelectedItem()) != null)
  			rowdata.addElement(sSecurityLevel);
  		if(sSecurityLevel.equalsIgnoreCase("NoAuthNoPriv"))	
  			rowdata.addElement("");
  		else
  		{
  			if( (sAuthProtocol  = (String)comboAuthPr.getSelectedItem()) !=null)
  			{
	  			rowdata.addElement(sAuthProtocol);
  			}
  		}
  		if( (sAuthPwd = (String)pwdAuthPass.getText().trim()) != null)
  		{
  			rowdata.addElement(sAuthPwd);
  		}
  		else
  		{
  		     	rowdata.addElement("");
  		}
  		if(sSecurityLevel.equalsIgnoreCase("AuthPriv") && (sPrivProtocol = (String)comboPrivPr.getSelectedItem()) != null)
	           {
                        	rowdata.addElement(sPrivProtocol);
                	}
  		else
  		{
  			rowdata.addElement("");     
  		}
  		if( (sPrivPwd  = (String)pwdPrivPass.getText().trim()) != null)
  		{
  			rowdata.addElement(sPrivPwd);
  		}
  		else
  		{
  			rowdata.addElement("");     
  		}
  		//getRowData();
		this.setVisible(false);
		// THEME-II Start
		dispose();
		// THEME-II End
				
	}
	public Vector getRowData()
	{
	     return rowdata;
	}

}


























































