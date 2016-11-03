//$Id: LoginUI.java,v 1.2.2.1 2014/05/27 06:09:12 venkatramanan Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$56
//<End_Version>
package com.adventnet.nms.startclient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.*;

import java.util.*;


public class LoginUI extends JFrame  implements ActionListener,com.adventnet.apiutils.ParameterChangeListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	com.adventnet.nms.util.CustomImagePanel ImageLabel1 = null;
	javax.swing.JPanel dataPanel = null;
	javax.swing.JPanel advPanel = null;
	javax.swing.JLabel server = null;
	javax.swing.JLabel lang = null;
	javax.swing.JTextField tfHostName = null;
	com.adventnet.beans.probeans.ProIntegerTextField tfPortNum = null;
	javax.swing.JComboBox jlanguagecombo = null;
	javax.swing.JLabel port = null;
	javax.swing.JLabel country = null;
	javax.swing.JComboBox jcountrycombo = null;
	javax.swing.JLabel console = null;
	javax.swing.JCheckBox showConsole = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JButton advButton = null;
	javax.swing.JPanel cardPanel = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel uid = null;
	javax.swing.JTextField tfUserName = null;
	javax.swing.JLabel pwd = null;
	javax.swing.JPasswordField tfPassword = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel statusLabel = null;
	com.adventnet.nms.util.CustomImagePanel CustomImagePanel1 = null;
	javax.swing.JTextArea loginText = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	//private ResourceBundle resourceBundle=null; Commenting on Aug 22nd fix Id:3033956. Pre-login I18N moved to NmsClientUtil.GetString
	private Hashtable uiDetails=null;
	private ActionListener actionListener=null;
	

  


 

  

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
  } 
  public void init()
  {
        //<Begin_init> 
        if (initialized) return; 
        this.setSize(getPreferredSize().width+483,getPreferredSize().height+376); 
          setTitle("ScreenTitle");
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
        //<End_init>
String title=(String)uiDetails.get("title");
	if(title!=null){
		setTitle(getString(title));
	}
addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e)
                {
                    System.exit(0);
                }
                public void windowOpened(WindowEvent e)
                {
                    tfUserName.requestFocus();
                }
            });
    Image frameImage = (Image)uiDetails.get("frameImage");
        if(frameImage != null){
            setIconImage(frameImage);
        }

setSize(400,250);
setResizable(false);
advPanel.setVisible(false);
validate();
 KeyStroke escStroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE,0);
 ((JComponent)getRootPane()).registerKeyboardAction(actionListener,"Cancel", escStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

NmsClientUtil.setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
NmsClientUtil.centerWindow(this);

  } 
  public String getParameter(String input)
  {
           //<Begin_getParameter_String> 
	if (po != null) 
	 { 
	   if(po.getParameter(input) != null) 
	     {
	       return (String)po.getParameter(input); 
	     }
	 }
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
            }
        return value;

  
         //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            Top.setBackground(new Color(-2105377));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+Top,ex); 
          }

//<UserCode_Begin_Bean_components>

//<UserCode_End_Bean_components>

          try
          {
            ImageLabel1.setBackgroundImageOption("Scaled");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ImageLabel1,ex); 
          }

//<UserCode_Begin_Bean_ImageLabel1>
Image fullBgImage=(Image)uiDetails.get("backgroundImage");
 if(fullBgImage!=null){
 ImageLabel1.setBackgroundImage(new ImageIcon(fullBgImage));
 }
//<UserCode_End_Bean_ImageLabel1>

          try
          {
            dataPanel.setBackground(new Color(-2105377));
            dataPanel.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+dataPanel,ex); 
          }

//<UserCode_Begin_Bean_dataPanel>

	
//<UserCode_End_Bean_dataPanel>

          try
          {
            advPanel.setBackground(new Color(-2105377));
            advPanel.setBorder(new javax.swing.border.BevelBorder(1));
            advPanel.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+advPanel,ex); 
          }

//<UserCode_Begin_Bean_advPanel>

	
//<UserCode_End_Bean_advPanel>

          try
          {
            server.setHorizontalAlignment(2);
            server.setForeground(new Color(-16777216));
            server.setHorizontalTextPosition(4);
            server.setBackground(new Color(-2105377));
            server.setFont(new Font("dialog",0,11));
            server.setText(getString("Host",'s'));
            server.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+server,ex); 
          }

//<UserCode_Begin_Bean_server>
		server.setDisplayedMnemonic('S');
		server.setLabelFor(tfHostName);
//<UserCode_End_Bean_server>

          try
          {
            lang.setHorizontalAlignment(2);
            lang.setForeground(new Color(-16777216));
            lang.setHorizontalTextPosition(4);
            lang.setBackground(new Color(-2105377));
            lang.setFont(new Font("dialog",0,11));
            lang.setText(getString("Language",'L'));
            lang.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lang,ex); 
          }

//<UserCode_Begin_Bean_lang>
			lang.setDisplayedMnemonic('L');
		lang.setLabelFor(jlanguagecombo);
//<UserCode_End_Bean_lang>

          try
          {
            tfHostName.setHorizontalAlignment(2);
            tfHostName.setFont(new Font("dialog",0,11));
            tfHostName.setText("");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tfHostName,ex); 
          }

//<UserCode_Begin_Bean_tfHostName>
String host=(String)uiDetails.get("host");
if(host!=null){
     tfHostName.setText(host);
     }
String  webStart=(String)uiDetails.get("isWebStart");

if(webStart!=null && webStart.equals("true")){
     tfHostName.setEnabled(false);
     tfPortNum.setEnabled(false);
     }

//<UserCode_End_Bean_tfHostName>

          try
          {
            tfPortNum.setHorizontalAlignment(2);
            tfPortNum.setPreferredSize(new Dimension(54,19));
            tfPortNum.setMinimumSize(new Dimension(4,19));
            tfPortNum.setFont(new Font("dialog",0,11));
            tfPortNum.setText("");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tfPortNum,ex); 
          }

//<UserCode_Begin_Bean_tfPortNum>
String portNumber=(String)uiDetails.get("port");
if(portNumber!=null){
     tfPortNum.setText(portNumber);
 }
//<UserCode_End_Bean_tfPortNum>

          try
          {
            jlanguagecombo.setFont(new Font("dialog",0,11));
            jlanguagecombo.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+jlanguagecombo,ex); 
          }

//<UserCode_Begin_Bean_jlanguagecombo>
    Vector languageList=(Vector)uiDetails.get("languageList");
    if(languageList!=null){
         int count=languageList.size();
         for(int i=0;i<count;i++){
              jlanguagecombo.addItem(languageList.elementAt(i));
         }
     }
    
    String languageGiven=(String)uiDetails.get("language");
        
    if(languageGiven !=null){
    int count = jlanguagecombo.getItemCount();
        for(int i = 0;i<count;i++)
        {
            String lang = (String)jlanguagecombo.getItemAt(i);
            String lan = lang.substring(0,2);
            if(languageGiven.compareTo(lan) == 0)
            {
                jlanguagecombo.setSelectedItem(lang);
            }
        }
}
//<UserCode_End_Bean_jlanguagecombo>

          try
          {
            port.setHorizontalAlignment(2);
            port.setForeground(new Color(-16777216));
            port.setHorizontalTextPosition(4);
            port.setLabelFor(tfPortNum);
            port.setBackground(new Color(-2105377));
            port.setFont(new Font("dialog",0,11));
            port.setText(getString("Port",'o'));
            port.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+port,ex); 
          }

//<UserCode_Begin_Bean_port>
		port.setDisplayedMnemonic('o');
		port.setLabelFor(tfPortNum);
//<UserCode_End_Bean_port>

          try
          {
            country.setHorizontalAlignment(2);
            country.setForeground(new Color(-16777216));
            country.setHorizontalTextPosition(4);
            country.setBackground(new Color(-2105377));
            country.setFont(new Font("dialog",0,11));
            country.setText(getString("Country",'C'));
            country.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+country,ex); 
          }

//<UserCode_Begin_Bean_country>
	
		country.setDisplayedMnemonic('C');
		country.setLabelFor(jcountrycombo);
	
//<UserCode_End_Bean_country>

          try
          {
            jcountrycombo.setFont(new Font("dialog",0,11));
            jcountrycombo.setOpaque(false);
            jcountrycombo.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+jcountrycombo,ex); 
          }

//<UserCode_Begin_Bean_jcountrycombo>

Vector countryList=(Vector)uiDetails.get("countryList");
    if(countryList!=null){
         int count=countryList.size();
         for(int i=0;i<count;i++){
              jcountrycombo.addItem(countryList.elementAt(i));
         }
     }
    
    String countryGiven=(String)uiDetails.get("country");
        
    if(countryGiven !=null){
    int count = jcountrycombo.getItemCount();
        for(int i = 0;i<count;i++)
        {
            String country = (String)jcountrycombo.getItemAt(i);
            String cunt = country.substring(0,2);
            if(countryGiven.compareTo(cunt) == 0)
            {
                jcountrycombo.setSelectedItem(country);
            }
        }
}


//<UserCode_End_Bean_jcountrycombo>

          try
          {
            console.setHorizontalAlignment(2);
            console.setForeground(new Color(-16777216));
            console.setHorizontalTextPosition(4);
            console.setBackground(new Color(-2105377));
            console.setFont(new Font("dialog",0,11));
            console.setText(getString("Show Console",'w'));
            console.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+console,ex); 
          }

//<UserCode_Begin_Bean_console>
		console.setDisplayedMnemonic('w');
		console.setLabelFor(showConsole);
	
//<UserCode_End_Bean_console>

          try
          {
            showConsole.setHorizontalTextPosition(4);
            showConsole.setFont(new Font("sansserif",0,11));
            showConsole.setOpaque(false);
            showConsole.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+showConsole,ex); 
          }

//<UserCode_Begin_Bean_showConsole>
String log=(String)uiDetails.get("logs");
if(log!=null){
	showConsole.setSelected(log.equalsIgnoreCase("yes"));
}

webStart=(String)uiDetails.get("isWebStart");
if(webStart !=null && webStart.equals("true")){
     showConsole.setEnabled(false);
     }

//<UserCode_End_Bean_showConsole>

          try
          {
            JPanel3.setBackground(new Color(-2105377));
            JPanel3.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

	
//<UserCode_End_Bean_JPanel3>

          try
          {
            okButton.setHorizontalTextPosition(4);
            okButton.setBackground(new Color(-2105377));
            okButton.setText("Connect");
            okButton.setFont(new Font("dialog",0,11));
            okButton.setOpaque(true);
            okButton.setMaximumSize(new Dimension(100,30));
            okButton.setMinimumSize(new Dimension(100,30));
            okButton.setPreferredSize(new Dimension(100,30));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+okButton,ex); 
          }

//<UserCode_Begin_Bean_okButton>
okButton.setActionCommand("Connect");
okButton.addActionListener(actionListener);
okButton.setText(getString("Connect"));
okButton.getRootPane().setDefaultButton(okButton);
 Image okImage = (Image)uiDetails.get("connectImage");
        if(okImage != null){
            okButton.setIcon(new ImageIcon(okImage));
        }
//<UserCode_End_Bean_okButton>

          try
          {
            cancelButton.setHorizontalTextPosition(4);
            cancelButton.setBackground(new Color(-2105377));
            cancelButton.setText("Cancel");
            cancelButton.setFont(new Font("dialog",0,11));
            cancelButton.setOpaque(true);
            cancelButton.setPreferredSize(new Dimension(90,30));
            cancelButton.setMinimumSize(new Dimension(90,30));
            cancelButton.setMaximumSize(new Dimension(90,30));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>
cancelButton.addActionListener(actionListener);
cancelButton.setActionCommand("Cancel");
cancelButton.setText(getString("Cancel"));
 Image cancelImage = (Image)uiDetails.get("cancelImage");
        if(cancelImage != null){
            cancelButton.setIcon(new ImageIcon(cancelImage));
        }
//<UserCode_End_Bean_cancelButton>

          try
          {
            advButton.setHorizontalTextPosition(4);
            advButton.setMnemonic(65);
            advButton.setBackground(new Color(-2105377));
            advButton.setNextFocusableComponent(tfUserName);
            advButton.setFont(new Font("dialog",0,11));
            advButton.setText("Advanced>>");
            advButton.setOpaque(true);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+advButton,ex); 
          }

//<UserCode_Begin_Bean_advButton>
advButton.setText(getString("Advanced")+ ">>");
advButton.addActionListener(this);
 Image advImage = (Image)uiDetails.get("advancedImage");
        if(advImage != null){
            advButton.setIcon(new ImageIcon(advImage));
        }
//<UserCode_End_Bean_advButton>

          try
          {
            cardPanel.setBackground(new Color(-2105377));
            cardPanel.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cardPanel,ex); 
          }

//<UserCode_Begin_Bean_cardPanel>

	
//<UserCode_End_Bean_cardPanel>

          try
          {
            JPanel4.setBackground(new Color(-2105377));
            JPanel4.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

	
//<UserCode_End_Bean_JPanel4>

          try
          {
            uid.setHorizontalAlignment(2);
            uid.setForeground(new Color(-16777216));
            uid.setHorizontalTextPosition(4);
            uid.setBackground(new Color(-2105377));
            uid.setFont(new Font("dialog",0,11));
            uid.setText(getString("User ID",'U'));
            uid.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+uid,ex); 
          }

//<UserCode_Begin_Bean_uid>
		uid.setDisplayedMnemonic('U');
		uid.setLabelFor(tfUserName);
	
//<UserCode_End_Bean_uid>

          try
          {
            tfUserName.setHorizontalAlignment(2);
            tfUserName.setFont(new Font("dialog",0,11));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tfUserName,ex); 
          }

//<UserCode_Begin_Bean_tfUserName>
 
//<UserCode_End_Bean_tfUserName>

          try
          {
            pwd.setHorizontalAlignment(2);
            pwd.setForeground(new Color(-16777216));
            pwd.setHorizontalTextPosition(4);
            pwd.setBackground(new Color(-2105377));
            pwd.setFont(new Font("dialog",0,11));
            pwd.setText(getString("Password",'P'));
            pwd.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+pwd,ex); 
          }

//<UserCode_Begin_Bean_pwd>
		pwd.setDisplayedMnemonic('P');
		pwd.setLabelFor(tfPassword);
//<UserCode_End_Bean_pwd>

          try
          {
            tfPassword.setHorizontalAlignment(2);
            tfPassword.setFont(new Font("dialog",0,11));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tfPassword,ex); 
          }

//<UserCode_Begin_Bean_tfPassword>
 
//<UserCode_End_Bean_tfPassword>

          try
          {
            JPanel5.setBackground(new Color(-2105377));
            JPanel5.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

	
//<UserCode_End_Bean_JPanel5>

          try
          {
            statusLabel.setHorizontalAlignment(2);
            statusLabel.setForeground(new Color(-16777216));
            statusLabel.setHorizontalTextPosition(4);
            statusLabel.setBackground(new Color(-2105377));
            statusLabel.setFont(new Font("dialog",0,11));
            statusLabel.setText(getString("Ready"));
            statusLabel.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statusLabel,ex); 
          }

//<UserCode_Begin_Bean_statusLabel>

	
//<UserCode_End_Bean_statusLabel>

          try
          {
            CustomImagePanel1.setBackgroundImageOption("Scaled");
            CustomImagePanel1.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+CustomImagePanel1,ex); 
          }

//<UserCode_Begin_Bean_CustomImagePanel1>
Image bgImage=(Image)uiDetails.get("textBackgroundImage");
 if(bgImage!=null){
  CustomImagePanel1.setBackgroundImage(new ImageIcon(bgImage));
 }

//<UserCode_End_Bean_CustomImagePanel1>

          try
          {
            loginText.setEditable(false);
            loginText.setWrapStyleWord(true);
            loginText.setLineWrap(true);
            loginText.setBackground(new Color(-2105377));
            loginText.setOpaque(false);
            loginText.setText("");
            loginText.setFont(new Font("dialog",0,12));
            loginText.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+loginText,ex); 
          }

//<UserCode_Begin_Bean_loginText>
String login=(String)uiDetails.get("loginText");
if(login!=null){
     loginText.setText(login);
     }
//<UserCode_End_Bean_loginText>
		advButton.setPreferredSize(new Dimension(advButton.getPreferredSize().width+2,advButton.getPreferredSize().height+0));
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+17,cancelButton.getPreferredSize().height+0));
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+7,okButton.getPreferredSize().height+0));
		ImageLabel1.setPreferredSize(new Dimension(ImageLabel1.getPreferredSize().width+10,ImageLabel1.getPreferredSize().height+10));

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
	 if(po == null)
	{
	po = new com.adventnet.apiutils.ParameterObject();
	}
        Top= new javax.swing.JPanel();
        ImageLabel1= new com.adventnet.nms.util.CustomImagePanel();
        dataPanel= new javax.swing.JPanel();
        advPanel= new javax.swing.JPanel();
        server= new javax.swing.JLabel();
        lang= new javax.swing.JLabel();
        tfHostName= new javax.swing.JTextField();
        tfPortNum= new com.adventnet.beans.probeans.ProIntegerTextField();
        jlanguagecombo= new javax.swing.JComboBox();
        port= new javax.swing.JLabel();
        country= new javax.swing.JLabel();
        jcountrycombo= new javax.swing.JComboBox();
        console= new javax.swing.JLabel();
        showConsole= new javax.swing.JCheckBox();
        JPanel3= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        advButton= new javax.swing.JButton();
        cardPanel= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        uid= new javax.swing.JLabel();
        tfUserName= new javax.swing.JTextField();
        pwd= new javax.swing.JLabel();
        tfPassword= new javax.swing.JPasswordField();
        JPanel5= new javax.swing.JPanel();
        statusLabel= new javax.swing.JLabel();
        CustomImagePanel1= new com.adventnet.nms.util.CustomImagePanel();
        loginText= new javax.swing.JTextArea();
        initializeParameters(); 

  
          //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(10,5));
Top.add(ImageLabel1,BorderLayout.CENTER);
ImageLabel1.setLayout(new BorderLayout(5,5));
ImageLabel1.add(dataPanel,BorderLayout.CENTER);
dataPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
dataPanel.add(advPanel,cons);
advPanel.setLayout(new GridBagLayout());
inset = new Insets(5,15,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
advPanel.add(server,cons);
inset = new Insets(5,15,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
advPanel.add(lang,cons);
inset = new Insets(5,5,5,30);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
advPanel.add(tfHostName,cons);
inset = new Insets(5,5,5,30);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
advPanel.add(tfPortNum,cons);
inset = new Insets(5,5,5,30);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
advPanel.add(jlanguagecombo,cons);
inset = new Insets(5,15,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
advPanel.add(port,cons);
inset = new Insets(5,15,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
advPanel.add(country,cons);
inset = new Insets(5,5,5,30);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
advPanel.add(jcountrycombo,cons);
inset = new Insets(5,15,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
advPanel.add(console,cons);
inset = new Insets(5,5,5,30);
setConstraints(1,4,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
advPanel.add(showConsole,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
dataPanel.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(1,6,6));
JPanel3.add(okButton);
JPanel3.add(cancelButton);
JPanel3.add(advButton);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
dataPanel.add(cardPanel,cons);
cardPanel.setLayout(new CardLayout(0,5));
cardPanel.add(JPanel4,"Login");
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,15,5,28);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel4.add(uid,cons);
inset = new Insets(5,5,5,30);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(tfUserName,cons);
inset = new Insets(5,15,5,28);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel4.add(pwd,cons);
inset = new Insets(5,5,5,30);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(tfPassword,cons);
cardPanel.add(JPanel5,"Status");
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(statusLabel,cons);
ImageLabel1.add(CustomImagePanel1,BorderLayout.NORTH);
CustomImagePanel1.setLayout(new GridBagLayout());
inset = new Insets(10,10,5,10);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
CustomImagePanel1.add(loginText,cons);

  
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





  

   
  public void setProperties(java.util.Properties props)
  {
         //<Begin_setProperties_java.util.Properties> 
	if(po != null)
	{
	po.setParameters(props);
	}
  
         //<End_setProperties_java.util.Properties>
  } 
  private void initializeParameters()
  {
          //<Begin_initializeParameters> 
	 if(po != null) 
	   {
	    po.addParameterChangeListener(this);
	   }

  
          //<End_initializeParameters>
  } 
  public void destroy()
  {
         //<Begin_destroy> 
	if(po != null)
	{
	 po.removeParameterChangeListener(this);
	}
  
         //<End_destroy>
  } 
  public void setParameterObject(com.adventnet.apiutils.ParameterObject paramObj)
  {
         //<Begin_setParameterObject_com.adventnet.apiutils.ParameterObject> 
	this.po=paramObj;

  
         //<End_setParameterObject_com.adventnet.apiutils.ParameterObject>
  } 
  public void parameterChanged(com.adventnet.apiutils.ParameterObject paramObj)
  {
  //<Begin_parameterChanged_com.adventnet.apiutils.ParameterObject> 

  
  //<End_parameterChanged_com.adventnet.apiutils.ParameterObject>
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

public void actionPerformed(ActionEvent ae){
     if(ae.getSource()==advButton){
          	if(!advPanel.isVisible()){

     	     setSize(400,450);
     	     advPanel.setVisible(true);
     	    advButton.setText("<<"+getString("Advanced"));
     	  
     	}
     	else{

     	     setSize(400,250);
  	     advPanel.setVisible(false);
  	     advButton.setText(getString("Advanced")+">>");
     	}
     	validate();
          }
}







  

  

public LoginUI(ResourceBundle bundle,Hashtable uiDetails,ActionListener actionListener){
     //this.resourceBundle=bundle; Commenting on Aug 22nd fix Id:3033956. Pre-login I18N moved to NmsClientUtil.GetString
     this.uiDetails=uiDetails;
     this.actionListener=actionListener;
}


  public LoginUI()
  {
    //<Begin_LoginUI>
    pack();
  
    //<End_LoginUI>
  }

  public LoginUI(java.applet.Applet applet)
  {
    //<Begin_LoginUI_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_LoginUI_java.applet.Applet>
  }
  
    

   void setEnabledFields(boolean flag)
    {
         String  webStart=(String)uiDetails.get("isWebStart");

     if(webStart!=null && webStart.equals("true")){
          showConsole.setEnabled(false);
            tfHostName.setEnabled(false);
        }
        else
        {
            tfHostName.setEnabled(flag);
            tfPortNum.setEnabled(flag);
        }
        okButton.setEnabled(flag);
        jcountrycombo.setEnabled(flag);
        jlanguagecombo.setEnabled(flag);
        tfUserName.setEnabled(flag);
        tfPassword.setEnabled(flag);
        cancelButton.setEnabled(flag);
        advButton.setEnabled(flag);
    }
    void showStatusPanel()
    {
        Runnable status = new Runnable() {

            public void run()
            {
                okButton.getRootPane().setDefaultButton(null);
                okButton.setVisible(false);
                cancelButton.setVisible(false);
                advButton.setVisible(false);
                advPanel.setVisible(false);
                CardLayout cl = (CardLayout)cardPanel.getLayout();
                cl.show(cardPanel, "Status");
                Dimension dd = new Dimension(400,200);
                setSize(dd);
                doLayout();
            }

        };
        SwingUtilities.invokeLater(status);
    }

    
   void showAdvancedPanel()
    {
        Runnable showAdvanced = new Runnable() {

            public void run()
            {
                setSize(400, 450);
                advPanel.setVisible(true);
                okButton.getRootPane().setDefaultButton(okButton);
                CardLayout cl = (CardLayout)cardPanel.getLayout();
                cl.show(cardPanel, "Login");
                okButton.setVisible(true);
                cancelButton.setVisible(true);
                advButton.setVisible(true);
                advButton.setText("<<"+getString("Advanced"));
                doLayout();
                Top.updateUI();
            }

        };
        SwingUtilities.invokeLater(showAdvanced);
    }

      

     void hideAdvancedPanel()
    {
        Runnable hideAdvanced = new Runnable() {

            public void run()
            {
                setSize(400, 250);
                advPanel.setVisible(false);
                okButton.getRootPane().setDefaultButton(okButton);
                CardLayout cl = (CardLayout)cardPanel.getLayout();
                cl.show(cardPanel, "Login");
                okButton.setVisible(true);
                cancelButton.setVisible(true);
                advButton.setVisible(true);
                advButton.setText(getString("Advanced")+">>");
                doLayout();
                Top.updateUI();
            }

        };
        SwingUtilities.invokeLater(hideAdvanced);
    }

    private String getString(String key,char c){
		String s=getString(key);
		boolean isPresent=false;
		int length=s.length();
		for(int i=0;i<length;i++){
			if(s.charAt(i)==c){
				isPresent=true;
				break;
			}
		}
		if(!isPresent){
			s=s+"("+String.valueOf(c)+")";
		}
		return s;
	}

    private String getString(String key)
    {
        try
        {/*
            if(resourceBundle != null)
            {
                String value = resourceBundle.getString(key);
                
                if(value.equals(""))
                {
                    return key;
                }
                
                return value;
            }
            else
            {
                return key;
            }*/ //Commenting on Aug 22nd fix Id:3033956. Pre-login I18N moved to NmsClientUtil.GetString
		String value = NmsClientUtil.GetString(key);
		return value;  

        }
        catch(MissingResourceException e)
        {
            return key;
        }
        catch(NullPointerException ne)
        {
            return key;
        }
	catch(Exception e)
	{
		return key;
	}       
	
    }
}













































