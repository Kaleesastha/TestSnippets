//$Id: V3Properties.java,v 1.2 2007/07/03 18:16:56 aravinds Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$18
//<End_Version>
package com.adventnet.nms.runtimeconfig;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;




public class V3Properties extends JDialog implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel btnPanel = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
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
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	Node node = null;
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
        this.setSize(getPreferredSize().width+363,getPreferredSize().height+327); 
          setTitle(resourceBundle.getString("SNMP V3 Properties"));
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
            okButton.setText(resourceBundle.getString("Ok"));
            okButton.setLabel(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); //No I18N 
          }

//<UserCode_Begin_Bean_okButton>

//<UserCode_End_Bean_okButton>

          try
          {
            cancelButton.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); //No I18N 
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>

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
comboSecLevel.addItem("AuthNoPriv");
comboSecLevel.addItem("NoAuthNoPriv");
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
		security.setPreferredSize(new Dimension(security.getPreferredSize().width+4,security.getPreferredSize().height+0));
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+6,cancelButton.getPreferredSize().height+4));
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+2,okButton.getPreferredSize().height+4));
		btnPanel.setPreferredSize(new Dimension(btnPanel.getPreferredSize().width+170,btnPanel.getPreferredSize().height+0));

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        btnPanel= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
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

  
        //<End_initVariables>
  okButton.addActionListener(this);
  cancelButton.addActionListener(this);
  comboSecLevel.addActionListener(this);
 // comboAuthPr.addActionListener(this);
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(btnPanel,BorderLayout.SOUTH);
btnPanel.setLayout(new FlowLayout(1,5,5));
btnPanel.add(okButton);
btnPanel.add(cancelButton);
Top.add(security,BorderLayout.CENTER);
security.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
security.add(lSecLevel,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
security.add(lAuthPr,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
security.add(lAuthPass,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
security.add(lPrivPr,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
security.add(lPrivPass,cons);
inset = new Insets(5,5,5,0);
setConstraints(1,0,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
security.add(comboSecLevel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
security.add(comboAuthPr,cons);
inset = new Insets(5,5,5,0);
setConstraints(1,2,1,1,0.0,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
security.add(pwdAuthPass,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.0,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
security.add(comboPrivPr,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.0,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
security.add(pwdPrivPass,cons);

  
//<End_setUpGUI_Container>
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

 public V3Properties(Node node)
  {
    this.node = node;
    init();
    start();
    //<Begin_V3Properties>
    //<End_V3Properties>
  }

  public void populateScreen()
  {
      
       	     String tempStr = node.getSecLevel();
       	     if(tempStr != null && tempStr.trim().equalsIgnoreCase("AuthNoPriv"))
       	     	comboSecLevel.setSelectedIndex(0);
       	      if(tempStr != null && tempStr.trim().equalsIgnoreCase("NoAuthNoPriv"))
       	     	comboSecLevel.setSelectedIndex(1);
       	      if(tempStr != null && tempStr.trim().equalsIgnoreCase("AuthPriv"))
       	     	comboSecLevel.setSelectedIndex(2);
       	     tempStr = node.getAuthPr();
       	      if(tempStr != null && tempStr.trim().equalsIgnoreCase("MD5"))
       	     	comboAuthPr.setSelectedIndex(0);
       	      if(tempStr != null && tempStr.trim().equalsIgnoreCase("SHA"))
       	     	comboAuthPr.setSelectedIndex(1);
       	     tempStr = node.getAuthPass();
       	     pwdAuthPass.setText(tempStr);
       	      tempStr = node.getPrivPr();
       	     if(tempStr != null && tempStr.trim().equalsIgnoreCase("CFB-AES-128"))
                  comboPrivPr.setSelectedIndex(1);
                else
                  comboPrivPr.setSelectedIndex(0);
       	      tempStr = node.getPrivPass();
       	     pwdPrivPass.setText(tempStr);
       }
  

  public V3Properties()
  {
    //<Begin_V3Properties>
    pack();
  
    //<End_V3Properties>
  }

  public V3Properties(java.applet.Applet applet)
  {
    //<Begin_V3Properties_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_V3Properties_java.applet.Applet>
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
  	String sSecLevel = null;
  	String sAuthPr = null;
  	String sAuthPass = null;
  	String sPrivPr = null;
  	String sPrivPass = null;
  	
  	if(ae.getActionCommand().equals("OK"))     
  	{
		 		
  		if( (sSecLevel = (String)comboSecLevel.getSelectedItem()) != null)
  			node.setSecLevel(sSecLevel);
  		if(sSecLevel.equalsIgnoreCase("AuthNoPriv") || sSecLevel.equalsIgnoreCase("AuthPriv") )
                	{
                        if( (sAuthPr = (String)comboAuthPr.getSelectedItem()) !=null)
                        {
                                node.setAuthPr(sAuthPr);
                        }
                        if(sSecLevel.equalsIgnoreCase("AuthPriv") && (sPrivPr = (String)comboPrivPr.getSelectedItem()) !=null)
                        {
                                node.setPrivPr(sPrivPr);
                        }
                        else
                        {
                             node.setPrivPr("");
                        }
                	}
                	else
                	{
                        node.setAuthPr("");
                        node.setPrivPr("");
                	}

  		if( (sAuthPass = (String)pwdAuthPass.getText().trim()) != null)
  		{
  			node.setAuthPass(sAuthPass);
  		}
  		if( (sPrivPass = (String)pwdPrivPass.getText().trim()) != null)
  		{
  			node.setPrivPass(sPrivPass);
  		}
  		this.setVisible(false);
		// THEME-II Start
		dispose();
		// THEME-II End
  	}
  	if(ae.getActionCommand().equals("Cancel"))     
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
  			pwdPrivPass.setText(""); 
  			pwdPrivPass.setEnabled(false);
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
  			pwdPrivPass.setText("");    
  			pwdPrivPass.setEnabled(false); 
  		}
  		
  	}
  	
  }

 public Node getNode()
  {
  	return this.node;
  }
}






















