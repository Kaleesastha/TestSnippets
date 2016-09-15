/*
* $Id: WebNMSClient.java,v 1.1 2002/12/19 05:05:13 muthuganeshj Exp $
*/
package com.adventnet.nms.startclient;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.beans.*;

import javax.swing.*;
import javax.swing.border.*;

import com.adventnet.nms.util.*;
import com.adventnet.security.authentication.AuthenticationException;
        

/**
 * This class is used to authenticate the Username and Password provided with the
 * Web NMS Server for loading the Client Applet. It also takes in information like
 * the "Host" and "Port" at which the Web Server is running .
 * User can specify the Language that has to be used across the Client. 
 * User can also specify whether he wants to view the java Console 
 * Window or not.
 * <p>
 * When the user tries to login, the validity of the user account is
 * checked before the actual login process. The method that is used
 * for establishing connection with the server is the <code>doconnect()</code> method
 * discussed below. The <code>doconnect() </code>method invokes a servlet
 * called the <code>StartClientServlet </code>with the paramters like Username,
 * password, Hostname etc. It checks for the validity of the User account
 * and accordingly enables a connection or throws a <code>ClientConnectExcpetion</code>.
 * An user account can have one of the following states. 
 * A <code>ClientConnectionException </code>is thrown in each of the following
 * cases.
 * <p>
 * <ol>
 * <li> <b>User Disabled </b> - This corresponds to the state when the user
 * account has been disabled by the Administrator. A normal user would not be able to 
 * login using this user account until it is enabled by the administrator.</li>
 * <li> <b>User Expired </b> - This means that the particular user account
 * has expired and cannot be used further for logging purposes. </li>
 * <li> <b>User Forced Out</b> - This means that an user account has
 * been forcefully terminated/disabled by the Administrator.</li>
 * <li> <b>Password Expired</b> - It is possible to configure the expiration
 * duration for a password by the user himself. The user will be intimated
 * with a message once that duration is exceeded. Once he is intimated about the expiration
 * he can set a new password configuration or proceed with the already existing settings. 
 * The reuseSettingsAndProceed() method should be called if the user
 * wants to continue with the already existing settings. Call any of the 
 * setNewPasswordConfiguration() methods for setting the new password
 * configurations for the user. </li>
 * <li> <b> Login Failed </b> - This happens when a user makes three consecutive
 * unsuccessful attempts to login using the same user account. When this
 * happens, the corresponding user account would be disabled automatically
 * and would not be available until it is enabled by the Administrator. </li></ol>
 *
 *<p>
 * Sequence of steps that are to be followed by the user while using his own 
 * dialog for authentication purposes.
 * <ol>
 *<li> Instantiate this class.</li>
 *<li> Call the doconnect() with the necessary paramters like hostname,port
 * username, password etc.</li>
 *<li> ClientConnectionException would be thrown in any of the cases
 * discussed above. 
 *<li> If the Password expires, catch the corresponding exception and
 * use your own custom dialog to get inputs from the user regarding the
 * new password settings.</li>
 *<li> If the user wants to reuse the already existing settings, call the
 * reuseSettingsAndProceed() method. If the user configures a new password
 * call any of the setNewPasswordConfiguration() methods.</li></ol>
 *
 * <br><br><b>This is a client side class and hence can be used only in the JVM as that
 * of the client.</b>
 *
 * @see 	doconnect()
 * @see		reuseSettingsAndProceed()
 * @see		setNewPasswordConfiguration(String newPassword)
 * @see		setNewPasswordConfiguration(String newPassword,String passwdAge)
 */

public class WebNMSClient implements ActionListener,Runnable,PropertyChangeListener
{						      //Start of the class

	JFrame frame;

	JTextField 	unameField,
				hostField;
	
	com.adventnet.utils.NumericTextField portField;
	
	JComboBox jlanguagecombo,
			  jcountrycombo;
	
	JPasswordField passwdField;
	
	JDialog dialog = null;
	
	JCheckBox jcheckbox;
	
	com.adventnet.nms.startclient.MyAppletStub stub;
		
	String whost        = null,
		   UName        = null,
		   UPSWord      = null,
		   hostget      = null,
		   portget      = null,
		   country      = "US",
		   language     = "en",
		   loginTitle   = null,
                   loginTxt     = null,
                   iconfile     = "";

	String  logsDisplay = "yes",
		    paramHost   = "localhost",
		    paramPort   = "9090";

        JButton openBU = new JButton();
        JButton	exitBU;

	JLabel statusLabel = null;

	JProgressBar progressBar   = null;

	private Vector langVect    = null;
	private Vector countryVect = null;

	private String fornextfocus = null;
    int wport = 0;
    private boolean webStartMode = false;

	/**
	 * When this constructor is invoked the doconnect() method needs to be called to 
	 * actually establish the connection with the server. When this constructor is used,
	 * default values will be assigned for "Language", "Country" and "show Log" option.
	 * The default values are "English", "US" and "true" respectively.
	 *
	 * @param userName  The login Username to be used to authenticate
	 * 					with the server and establish a connection
	 * @param password  The login Password to be used to authenticate
	 * 					with the server and establish a connection
	 * @param hostName 	Name of the host where the Web Server is running
	 * @param port     	Port at which the Web Server is running 
	 */
	
	public WebNMSClient(String userName,String password,
						String hostName,String port)
	{ 
		this(userName,password,
			 hostName,port,
			 null,null,
			 true);
	}
	
	/**
	 * When this constructor is invoked the doconnect() method needs to be called to 
	 * actually establish the connection with the server.
	 *
	 * @param userName  The login Username to be used to authenticate
	 * 					with the server and establish a connection
	 * @param password  The login Password to be used to authenticate
	 * 					with the server and establish a connection
	 * @param hostName 	Name of the host where the Web Server is running
	 * @param port     	Port at which the Web Server is running 
	 * @param language  The language that has be used across the Client. 
	 * @param country   The Country corresponding to the Language that is selected.
	 * @param showLog  	A Flag for enabling the Java Console for the Application Client.
	 */
	public WebNMSClient(String userName,String password,
						String hostName,String port,
						String language,String country,
						boolean showLog)
	{ 
            webStartMode = checkWebStartClient();
            checkIfThisCan();   
	  wport = Integer.parseInt(port);
	  
	  if(language != null)
	  {
                  this.language = language;
		  paramList.put("LANGUAGE",language);			
	  }

	  if(country != null)
	  {
                  this.country = country;
		  paramList.put("COUNTRY",country);
	  }

	  UName   = userName;
	  UPSWord = password;
	  hostget = hostName;
	  whost   = hostName;
	  portget = port;	
	  logsDisplay = showLog ?"YES":"NO";

	}
	
	/**
	 * Constructs a WebNMSClient object
	 */
	
    public WebNMSClient()
    {
        webStartMode = checkWebStartClient();
        checkIfThisCan();
      	//Nothing...
    }
	
	//Need for these methods in near future will araise, probably we could
	//uncomment these set of lines then.. Any way I don't want to expose 
	//these methods as of now.. This is primarily needed when WebNMSClient
	//is used as a real API.
	/*
	 * Set the hostName where the Web NMS server is running
	 * 
	 * @param hostName the hostName where the Web NMS Server is running
	 *
	public void setHostName(String hostName)
	{
		this.hostget = hostName;
	}
	
	 *
	 * Get the hostName to which the client is connected.
	 * 
	 * @return hostget the server hostName
	 *
	public String getHostName()
	{
		return hostget;
	}
	
	 *
	 * Set the port to which the client is to be connected
	 *
	public void setPort(int port)
	{
	}
	
	public int getPort()
	{
	}
	
	public void setUserName(String userName)
	{
	}
	
	public String getUserName()
	{
	}
	
	public void setUserPassword(String password)
	{
	}
	
	public String getUserPassword()
	{
	}
	
	public void setLanguage(String language)
	{
	}
	
	public String getLanguage()
	{
	}
	
	public void setCountry(String country)
	{
	}
	
	public String getCountry()
	{
	}
	
	public void setLogDisplay(boolean showLog)
	{
	}
	
	public boolean getLogsDisplay()
	{
	}
	**/
	
	public void actionPerformed(ActionEvent evt)
	{
		String command = evt.getActionCommand();
		if(command.equals("Connect"))
		{    
			startConnection();
		}
		else if(command.equals("Cancel"))
		{
			System.exit(0);
		}
		else if(command.equals("Stop"))
		{
			stopConnection();
		}
        else if(command.equals("OK"))
		{
                    dialog.setVisible(false);
        }
        //This is the action command set to the cancel button
        //which would appear in the Password Configuration 
        //dialog.
        //else if(command.equals("PasswordConfigCancel"))
        //{
        //restoreFrame();
        //}
	}
	
    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getPropertyName().equals("configure"))
        {
            setNewPasswordConfiguration((Properties)event.getNewValue());
        }
        if(event.getPropertyName().equals("reuse"))
        {
            reuseSettingsAndProceed();
        }
        if(event.getPropertyName().equals("cancel"))
        {
            restoreFrame();
        }
    }
	boolean connecting = true;
	
	private void stopConnection()
	{
		if(connected || stopped)
		{
			return;
		}
		   
		connecting = false;
		stopped    = true;
		//re-init the applet reference, if we had
		//already created one..
		if(applet != null)
		{
			applet.stop	();
			applet.destroy();
			applet = null;
		}
		openBU.setText("Connect");
		openBU.setActionCommand("Connect");
		setEnabledFields(true);
		
		if(fornextfocus != null)
		{
			if(fornextfocus.equalsIgnoreCase("host"))
			{
				String hostText = hostField.getText();
				int hostlen = hostText.trim().length();
				if(hostlen != 0)
				{
					hostField.setSelectionStart(0);
					hostField.setSelectionEnd(hostlen);
				}
				hostField.requestFocus();
			}
			else if(fornextfocus.equalsIgnoreCase("port"))
			{
				String portText = portField.getText();
				int portlen = portText.trim().length();
				if(portlen != 0)
				{
					portField.setSelectionStart(0);
					portField.setSelectionEnd(portlen);
				}
				portField.requestFocus();
			}
		}
		connectThread.interrupt();
		connectThread = null;
		watchThread.interrupt();
		watchThread = null;

		showStatusText("Ready");
		progressBar.setValue(0);
                if(dialog != null)
                {
                    dialog.dispose();
                    dialog.setVisible(false);
                    dialog = null;
                }
	}
	
    /**
     *  Shows Alert Message.
     */
	private void showAlertMessage(String message,String title)
    {
        if(message.equals("unAuthorized"))
        {
            message = NmsClientUtil.GetString("Invalid User Name / Password");
        }
        else if(message.equals("wrongServer"))
        {
            message = NmsClientUtil.GetString("Invalid User Name / Password for this server.");
        }
        else if(message.equals("Login Failed"))
        {
            message = NmsClientUtil.GetString("Login Failed. Please contact the Administrator for further details");
        }
        else if(message.equals("User Disabled"))
        {
            message = NmsClientUtil.GetString("This User account is Disabled. Please contact the Administrator for further details");
        }
        else if(message.equals("User Expired"))
        {
            message = NmsClientUtil.GetString("This User account has Expired. Please contact the Administrator for further details");
        }
        else if(message.equals("User Forced Out"))
        {
            message = NmsClientUtil.GetString("This User account has been forced out by the Administrator. Please contact the Administrator for further details");
        }
        else if(message.equals("No Message"))
        {
            message = NmsClientUtil.GetString("No information available about this User's status");
        }

        JOptionPane.showMessageDialog(frame,message,title,JOptionPane.ERROR_MESSAGE);
    }
	
	/**
	 * Shows Message Window.
	 */
    private void showMessageWindow()
    {  
        
		if(dialog == null)
		{
        dialog       = new JDialog(frame,"Wait !");
        JLabel label = new JLabel("Server is not yet initialized");
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(this);
        label.setHorizontalAlignment(JLabel.CENTER);
        Container contentPane = dialog.getContentPane();
        contentPane.add(label, BorderLayout.CENTER);
        contentPane.add(okButton, BorderLayout.SOUTH);
        dialog.setSize(new Dimension(300, 150));
        dialog.setLocationRelativeTo(frame);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);       
                }        
    }
	
	private NmsMainApplet applet = null;
	//private URLConnection urlc   = null;
	private boolean connected = false;
	private boolean stopped   = false;

	/**
	 * This method tries to establish connection with the Web Server
	 * and later starts the Web NMS Client Applet.
	 * It throws an exception when it is not able to do so.

	 * @exception ClientConnectionException Exception is thrown due to invalid
	 * 										inputs or some other exceptions like 
	 * 										UnKnownHost, IOException, MalformedURLExecption etc 
	 *										Excpetions are also thrown in
	 *										any of the following situations :-
	 *										User Expired, User Disabled,
	 *										Password Expired, Login Failed
	 * 										and User Forced Out.
	 * <p> 
	 * <b><u>NOTE :</u></b>
	 * Following are the message strings with which the various excpetions
	 * can be identified.
	 * <p>
	 *<table BORDER COLS=2 WIDTH="100%" >
	 *<tr>
	 *<td>
	 *<center><b>Exception Message</b></center>
	 *</td>
	 *<td>
	 *<center><b>Description</b></center>
	 *</td>
	 *</tr>
	 *<tr>
	 *<td>User Expired</td>
	 *<td>This corresponds to the state when the user account has been disabled by the Administrator. A normal user would
	 *not be able to login using this user account until it is enabled by the
	 *administrator.</td>
	 *</tr>
	 *<tr>
	 *<td>User Disabled</td>
	 *<td>This means that the particular user account has expired and cannot
	 *be used further for logging purposes.</td>
	 *</tr>
	 *<tr>
	 *<td>User Foreced Out</td>
	 *<td>This means that an user account has been forcefully terminated/disabled
	 *by the Administrator.</td>
	 *</tr>
	 *<tr>
	 *<td>Password Expired</td>
	 *<td>It is possible to configure the expiration duration for a password
	 *by the user himself. The user will be intimated with a message once that
	 *duration is exceeded. Once he is intimated about the expiration he can
	 *set a new password configurationor proceed with the already existing settings.</td>
	 *</tr>
	 *<tr>
	 *<td>Login Failed</td>
	 *<td>This happens when a user makes three consecutive unsuccessful attempts to login using the same user account. When this
	 *happens, the corresponding user account would be disabled automatically
	 *and would not be available until it is enabled by the Administrator.</td>
	 *</tr>
	 *</table>
	 * 
	 * @see 	doconnect()
	 * @see		reuseSettingsAndProceed()
	 * @see		setNewPasswordConfiguration()
	 */
	public void doconnect() throws ClientConnectionException
    {                              //Start of the doconnect     
		if(stopped)
		{
			//If we are using the *stop* option..
			return;
		}
		
			resetCache();
			connecting = true;
			connected  = false;
                        if(dialog == null)
			showStatusText("Trying to contact the host " + hostget);
                        
                        Properties props= new Properties();
                        props.setProperty("USER_NAME",UName);
                        props.setProperty("PORT",portget);
                        props.setProperty("HOST",hostget);
                        props.setProperty("PASSWORD",UPSWord);
                        try
                        {

                            String hostAddress = InetAddress.getLocalHost().getHostAddress();
                            props.setProperty("HOSTADDRESS",hostAddress);

                            String connect = null;
                            try
                            {
                                NmsClientUtil.authenticateUser(props);
                            }
                            catch(ClientConnectionException ee)
                            {
                                
                                if( (ee.getMessage().equals("Password Expired")) || (ee.getMessage().equals("First Login")) )
                                {
                                    connect = ee.getMessage();
                                }
                                else
                                    throw ee;
                            }
                            
                        
                        




                        URL url = new URL("http" + "://" + hostget + ":" + portget + "/servlets/com.adventnet.nms.servlets.StartClientServlet;jsessionid="+NmsClientUtil.sessionId);
                        
                        URLConnection urlc = url.openConnection();
                        urlc.setRequestProperty("userName",UName);
                        
                        urlc.setDoInput(true);
                        urlc.setDoOutput(true);
                        urlc.setRequestProperty("Content-type","application/x-www-form-urlencoded");
                        
                        DataInputStream dis   = new DataInputStream(urlc.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(dis));
                        String s ;
                        
                        while((s = reader.readLine()) != null)
                            {
                                
                                if(s.equals("Sorry"))
                                    {
                                        showStatusText("Host Contacted .. Trying to establish connection");
                                        if(frame != null)
                                            {
                                                showMessageWindow();
                                                try
                                                    {
                                                        Thread.sleep(600);
                                                    }
                                                catch(InterruptedException ie)
                                                    {
                                                        //We are exiting as we would have possibly being interrupted 
                                                        //by the Stop Thread. Hence exiting immediately.
                                                        return;
                                                    } 
                                                catch(Exception e)
                                                    {
                                                        //nothing to be done here..
                                                    }
                                            }                    
                                        doconnect();
                                        return;
                                    }            
                                else
                                    {
                                        openBU.setEnabled(false);
                                        //This try.. catch block is to give some leeway for the Stop thread
                                        //if it had being clicked at this instance. This is a crude way
                                        //of fixing the contention between the AWT thread and this one
                                        //We have to definitely think about a alternative. 
                                        try
                                            {
                                                Thread.sleep(100);
                                            }
                                        catch(InterruptedException ie)
                                            {
                                            //We are exiting as we would have possibly being interrupted 
                                                //by the Stop Thread. Hence exiting immediately.
                                                return;
                                            }
                                        catch(Exception e)
                                            {
                                                //nothing to be done here..
                                            }
                                        connected = true;
                                        //openBU.setEnabled(false);
                                        if (dialog != null) 
                                            {
                                                dialog.dispose();
                                                dialog.setVisible(false);
                                            }
                                    setParameter(s);
                                    }
                            }
                       
                        showStatusText("Connected to Host. Client Initialization started");

                        if(connect != null)
                        {
                            throw new ClientConnectionException(connect);
                        }

                        reader.close();
                        
			userPassword = UPSWord;
			setAppletStub();
			initializeTheApplet();          
			//ie., if the user decides to show up his own frame for
			//authenticating then this frame reference will be null.
			//so discard the below line.
			
			if(frame != null && applet != null )
                            {
				frame.dispose();
				frame.setVisible(false);
                            }
		}
		
		//handling various Exceptions
        catch(MalformedURLException e)
        {
		    throw new ClientConnectionException("The URL: " + "http://" + whost + ":" + wport +  "/com.adventnet.nms.servlets.StartClientServlet is Malformed",e);
		}
        catch(UnknownHostException e)
        {
			throw new ClientConnectionException("The host: " + whost + " is unknown",e);
		}
        catch(ConnectException e)
        {
		    throw new ClientConnectionException("Connection refused. No further information",e);
		}
        catch(IOException e)
        {   
            throw new ClientConnectionException("Unable to establish connection with host\n Please try reconnecting",e);
		}
		// If the "passwordExpired" variable is set to true
		// we through a new ClientConnectionException and
		// by catching that we throw a dialog for configuration.
		catch(ClientConnectionException cce)
		{
			throw new ClientConnectionException(cce.getMessage());
		}
	}                  //End of doconnect
	
	// Method used to popup the dialog for password configuration
	// if the existing password has expired.
	private void showPasswordConfigurationDialog(String str)
	{

            if("Password Expired".equals(str))
                {
                    passwordDialog = new PasswordExpiryIndicator(this,frame);
                    passwordDialog.setDialogImage(passwdDialogIcon);        
                    passwordDialog.setDialogTitle(expiryTitle);
                    passwordDialog.setDialogMessage(expiryText);
                }
            else 
                {
                    passwordDialog = new PasswordExpiryIndicator(this,frame);
                    passwordDialog.setDialogImage(passwdDialogIcon);       
                    
                    if(firstLoginTitle == null)
                        passwordDialog.setDialogTitle("Password Confirmation");
                    else
                        passwordDialog.setDialogTitle(firstLoginTitle);
                    
                    if(firstLoginText == null)
                        passwordDialog.setDialogMessage("You are logged in for the first time,would you like to reuse the existing password or configure a new password");
                    else
                        passwordDialog.setDialogMessage(firstLoginText);
                }
            
            passwordDialog.setVisible(true);
	}
    
        PasswordExpiryIndicator passwordDialog = null;


	private String userPassword = null;
	
	private void setAppletStub()
	{
		//to make sure that we initialize every thing only
		//once...
		if(applet == null)
		{
			try
			{
				applet = new NmsMainApplet();
				applet.standAlone = true;
				stub   = new com.adventnet.nms.startclient.MyAppletStub(whost,wport);
				setStubParameters();
				applet.setStub(stub);
				//incase we are going to connect to a inconsistent-server
				//we get some NoClassDefFoundError etc..here we need to
				//be safe..
			}
			catch(Throwable e)
			{
				applet = null;
				e.printStackTrace();
			}
		}
	}
	
	// This piece of code does the applet initialization process.
	// This is called from different parts of the code depending on whether
	// new Password configuration has been effected or not.
	private void initializeTheApplet()
	{
		applet.init();
		showStatusText("Applet Initialized..");
		applet.start();
		showStatusText("Applet Started ..");
		// New variable from 2.3SP1 to cache the user password to 
		// the Lock Out mechanism.
		if(frame != null && applet != null )
		{
				frame.dispose();
				frame.setVisible(false);
		}
	}
	
	/** 
	 * This method is used to set the already existing password
	 * settings for the user and proceed with the login process. 
	 * This method invokes another servlet known as the 
	 * <code>ChangePasswordServlet to communicate this information to the
	 * server.
	 * <p>
	 * <b><u>NOTE :</u></b> This method has to be called only
	 * when the user password expires or in a situation
	 * when the user is expected to re-configure his
	 * password settings. It is not necessary that
	 * this has to be called each and every time we connect to the 
	 * server.
	 * @exception	ClientConnectionException	Exceptions such as MalformedURLExcpetion,
	 *											UnkonwnHostException, ConnectException
	 *											are thrown as ClientConnectionExceptions.
	 *											One more ClientConnectionException with
	 *											a message - "Authentication Failure" is also
	 * 											thrown.
	 *
	 *<table BORDER COLS=2 WIDTH="100%" >
	 *<tr>
	 *<td>
	 *<center><b>Exception Message</b></center>
	 *</td>
	 *<td>
	 *<center><b>Description</b></center>
	 *</td>
	 *</tr>
	 *<tr>
	 *<td>Authentication Failure</td>
	 *<td>This message appears when we try to invoke some of the applet parameters
	 *without the applet being initialized. In other terms, when we try to
	 *use the <code>reuseSettingsAndProceed()</code> method or the <code>setNewPasswordConfiguration()</code>
	 *methods for password configuration before calling the <code>doconnect()</code>
	 *method, we get this message. This is because, the applet would not
	 * have been initialized without a call to the <code>doconnect() </code>method and
	 * that, the <code>reuseSettingsAndProceed()</code> and <code>setNewPasswordConfiguration()</code>
	 * methods needs a session ID (which is got as a Applet Parameter) to
	 * call the <code>ChangePasswordServlet.</code></td>.
	 *</tr>
	 *</table>
	 * @see		doconnect()
	 */
	 
	public void reuseSettingsAndProceed() throws ClientConnectionException
	{
		if(applet == null || applet.getParameter("jsessionid") == null)
		{
			throw new ClientConnectionException("Authentication Failure");
		}
		disposeTheDialog();
		userPassword = passwdField.getText();
                Properties p = new Properties();     
                p.put("host",hostget);
                p.put("port",portget);
                p.put("userName",UName);
                p.put("jsessionid",applet.getParameter("jsessionid"));
                boolean b =  NmsClientUtil.changePassword(p);
                checkAndInitialize(b);
	}
	
	/**
	 * This method is used to set the New Password for the
	 * user when the old password expires. When this method is invoked,
	 * the default value is assumed for the Password Expiry time.
	 * The default value mentioned above would be "0" which means that
	 * there will be no expiry for this new password.
	 *
	 * @exception	ClientConnectionException
	 * @param	newPassword		The new password that is to be set.
	 * @see	setNewPasswordConfiguration(String newPassword, String newPasswdAge)
	 * @see		doconnect()
	 */
	
	public void setNewPasswordConfiguration(String newPassword) throws ClientConnectionException
	{
                Properties p = new Properties();
                p.put("password",newPassword);
                p.put("age","0");
		setNewPasswordConfiguration(p);
	}
	
	/**
	 * This method is used to set the New Password and the password expiry time
	 * for the user. This method invokes another servlet known as the 
	 * <code>ChangePasswordServlet to communicate this information to the
	 * server.
	 * @exception	ClientConnectionException Exceptions such as MalformedURLExcpetion,
	 *											UnkonwnHostException, ConnectException
	 *											are thrown as ClientConnectionExceptions.
	 *											One more ClientConnectionException with
	 *											a message - "Authentication Failure" is also
	 * 											thrown.
	 *
	 *<table BORDER COLS=2 WIDTH="100%" >
	 *<tr>
	 *<td>
	 *<center><b>Exception Message</b></center>
	 *</td>
	 *<td>
	 *<center><b>Description</b></center>
	 *</td>
	 *</tr>
	 *<tr>
	 *<td>Authentication Failure</td>
	 *<td>This message appears when we try to retrieve some of the applet parameters
	 *without the applet being initialized. In other terms, when we try to
	 *use the <code>reuseSettingsAndProceed()</code> method or the <code>setNewPasswordConfiguration()</code>
	 *methods for password configuration before calling the <code>doconnect() </code>
	 *method, we get this message. This is because, the applet would not
	 * have been initialized without a call to the <code>doconnect()</code> method and
	 * that, the <code>reuseSettingsAndProceed() and <code>setNewPasswordConfiguration()</code>
	 * methods needs a session ID (which is got as a Applet Parameter) to
	 * call the <code>ChangePasswordServlet</code>.</td>
	 *</tr>
	 *</table>
	 * @param	p The properties object.
	 * @see		setNewPasswordConfiguration(String newPassword)
	 * @see		doconnect()
	 */
	
	public void setNewPasswordConfiguration(Properties p) throws ClientConnectionException
	{
        	if(applet == null || applet.getParameter("jsessionid") == null)
	        {
			throw new ClientConnectionException("Authentication Failure");
	        }
		disposeTheDialog();
		//userPassword = newPassword;
                userPassword = p.getProperty("password");
                p.put("userName",UName);
                p.put("host",hostget);
                p.put("port",portget);
                p.put("jsessionid",applet.getParameter("jsessionid"));
                boolean b = NmsClientUtil.changePassword(p);
                checkAndInitialize(b);
	}

	// ChangePasswordServlet is the servlet that is invoked for the password configuration.
	// The response from that servlet is checked in this method to make sure that the
	// operation was a success.
	private void checkAndInitialize(boolean b)
	{
   	 	if(b)
   	 	{
      			initializeTheApplet();
     	}
       	else
       	{
       		JOptionPane.showMessageDialog(null,"Unable to set the new password configuration. Please try again or contact the Administrator for further details");
       		return;
       	}
	}	

	//Create a hastable name paramlist
	
    Hashtable paramList = new Hashtable(10);
     
    /**
     * Puts the parameter details in the paramlist.
     * @param s String which contains parameter 
     */
	private void setParameter(String s)
    {
        try
        {
            if(s.trim().equals("")) 
            {
		return;
            }
            else
            {
                String temp  = s.substring(s.indexOf("=")+1,s.indexOf("value")-1);
                String temp1 = s.substring(s.indexOf("=",s.indexOf("value"))+1,s.indexOf(">"));
                temp  = removeQuotes(temp);
                temp1 = removeQuotes(temp1);
                // The Password Expiration is handled in a different
				// manner. i.e When the Password expires, we do not get
				// this information as a part of "StartClientServlet's"
				// response. It is received as one of the Applet parameters.
				// We wait for the user to configure the new password 
				// settings before starting the client. When the 
				// Password has expired, we get an extra paramter
				// called "PASSWORD_EXPIRED". It is not sent otherwise.
				// If this paramter is present, we do not proceed
				// with the applet Initialization process. Instead,
				// we wait for the user to enter his new configuration by
				// popping up a dialog and on successful completion
				// of this process, we initialize the applet.
             /*
                if(temp.equals("PASSWORD_EXPIRED"))
                {
                	passwordExpired = true;
                	return;
                }
             */
                paramList.put(temp,temp1);
            }
       }
       catch(Exception e)
       {
	  		System.err.println("Exception reading the parameters");
       }
    }
	
	/**
	 * Removes the Quotes in the parameter.
	 * @param temp String which cotains parameter
	 */
	private String removeQuotes(String temp)
    {
        if(!temp.startsWith("\""))
            return temp;
        else
            temp = temp.substring(1,temp.length());
        if(!temp.endsWith("\""))
            return temp;
        else
            temp = temp.substring(0,temp.length()-1);
        return temp;
    }
	
    /**
     * Sets the stubParameters list.
     */
	private void setStubParameters()
    {
        for(Enumeration en = paramList.keys();en.hasMoreElements();)
        {
            String key = (String)en.nextElement();
            stub.setParameter(key,(String)paramList.get(key));
        }
        stub.setParameter("WebNMSClient","ApplicationClient");
        stub.setParameter("LogsDisplay",logsDisplay);
    }

	/**
	 * The main method
	 */
	
	public static void main(String args[])
    {
        //Start of main
		
			//WebNMSClient nms = new WebNMSClient();
			if(args.length!=4)
			{
				System.out.println("USAGE: sh startApplicationClient.sh [host] [port] [username] [password]");
				System.exit(0);
			}
			WebNMSClient nms = new WebNMSClient(args[2],args[3],args[0],args[1]);
			nms.doconnect();
			if(1==1)                 
				return;
			//The first two parameters consist of HostName and Port No.
			
			 if(args.length > 1)
			 {
				 nms.paramHost   = args[0];
				 nms.paramPort   = args[1];
				 
				 //if we have a 3rd parameter, it must be showLogin "YES/NO"
				 if (args.length > 2)
				 {  	
					 nms.logsDisplay = args[2];
				 }
				 
				 //if we have more than 3 parameters then it means we are having
				 //language and country fields also getting passed.
				 if(args.length > 4)
				 {
					 nms.language    = args[3];
					 nms.country     = args[4];
				 }
			 }
			 nms.showLoginPanel();


	}	                         //End of main

    private boolean checkWebStartClient()
    {
        ClassLoader cl = this.getClass().getClassLoader();
        String clname = cl.toString();
        if(clname.indexOf("JNLP") != -1)
        {
            return true;
        }
        return false;
    }
	
	/**
	 * Creates Web NMS Authentication window User Interface.
	 */
	 JPanel loginPanel;
	private void showLoginPanel()
    {                            //Start of showLoginPanel
	
	
	
	JLabel	labelUname,
			labelPasswd,
    		hostLabel,
			portLabel,
			langLabel,
			countLabel;
	
    JTextArea loginText;
	    
	    //Instaniating objects and layout managers
		frame      = new JFrame();
		loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());
	    loginText  = new JTextArea();
		
		readTitleAndText();
		frame.setTitle(loginTitle);
		Font loginTextFont = new Font("Helvetica",Font.PLAIN,12);
		loginText.setFont(loginTextFont);
		loginText.setText(loginTxt);
	    loginText.setEditable(false);
        loginText.setWrapStyleWord(true);
        loginText.setLineWrap(true);
		loginText.setOpaque(true);
		loginText.setBackground(loginPanel.getBackground());

		JPanel urlPanel         = new JPanel();
		GridBagLayout gbl1      = new GridBagLayout();
		GridBagConstraints gbc1 = new GridBagConstraints();
		urlPanel.setLayout(gbl1);
		gbc1.insets = new Insets(9,5,9,5);
		
		gbc1.anchor    = GridBagConstraints.WEST;
		gbc1.gridx     = 0;
   		gbc1.gridy     = 0;
		gbc1.gridwidth = 4;
		gbc1.weighty   = 2;
		gbc1.fill      = GridBagConstraints.BOTH;
	   	gbl1.setConstraints(loginText,gbc1);
		urlPanel.add(loginText);
		
		hostLabel  = new JLabel("Host");
		hostLabel.setFont(loginTextFont);
		hostLabel.setForeground(Color.black);
		gbc1.fill = GridBagConstraints.NONE;
	   	gbc1.gridx = 0;
		gbc1.gridy = 1;
		gbc1.gridwidth = 1;
		gbc1.weighty = 1;
		gbl1.setConstraints(hostLabel,gbc1);
		urlPanel.add(hostLabel);
		
		hostField  = new JTextField(14);
		hostField.setText(paramHost);
		gbc1.fill  = GridBagConstraints.HORIZONTAL;
		gbc1.gridx = 1;
		gbc1.gridy = 1;
		gbl1.setConstraints(hostField,gbc1);
		urlPanel.add(hostField);
				
		portLabel  = new JLabel("Port");
		portLabel.setFont(loginTextFont);
		portLabel.setForeground(Color.black);
		gbc1.gridx = 2;
		gbc1.gridy = 1;
		gbl1.setConstraints(portLabel,gbc1);
		urlPanel.add(portLabel);
		
		portField  = new com.adventnet.utils.NumericTextField(19);
		portField.setText(paramPort);
		gbc1.fill      = GridBagConstraints.HORIZONTAL;
		gbc1.gridx     = 3;
		gbc1.gridy     = 1;
		gbc1.gridwidth = GridBagConstraints.REMAINDER; 
		gbl1.setConstraints(portField,gbc1);
		urlPanel.add(portField);
		
		langLabel      = new JLabel("Language");
		langLabel.setFont(loginTextFont);
		langLabel.setForeground(Color.black);
		gbc1.gridwidth = 1;
		gbc1.gridx     = 0;
		gbc1.gridy     = 2;
		gbl1.setConstraints(langLabel,gbc1);
		urlPanel.add(langLabel);
		
		readLanguageAndCountry();
		jlanguagecombo = new JComboBox(langVect);
		jcountrycombo  = new JComboBox(countryVect);
		
		
		jlanguagecombo.setFont(loginTextFont);
       	gbc1.gridx = 1;
		gbc1.gridy = 2;
		gbl1.setConstraints(jlanguagecombo,gbc1);
		urlPanel.add(jlanguagecombo);
		
		int count = jlanguagecombo.getItemCount();
		for(int i = 0;i<count;i++)
		{
			String lang = (String)jlanguagecombo.getItemAt(i); 
			String lan = lang.substring(0,2);
			if(language.compareTo(lan) == 0)
			{
				jlanguagecombo.setSelectedItem(lang);
			}
		}
		
		countLabel = new JLabel("Country");
		countLabel.setFont(loginTextFont);
		countLabel.setForeground(Color.black);
		gbc1.gridx = 2;
		gbc1.gridy = 2;
		gbl1.setConstraints(countLabel,gbc1);
		urlPanel.add(countLabel);
		
		jcountrycombo.setFont(loginTextFont);
		gbc1.gridx    = 3;
		gbc1.gridy    = 2;
		gbl1.setConstraints(jcountrycombo,gbc1);
		urlPanel.add(jcountrycombo);


		
		int numbers = jcountrycombo.getItemCount();
		for(int j = 0;j<numbers;j++)
		{
			try
			{
			String lang = (String)jcountrycombo.getItemAt(j); 
			String lan  = lang.substring(0,2);
			if(country.compareTo(lan) == 0)
			{
				jcountrycombo.setSelectedItem(lang);
			}
			}
			catch(StringIndexOutOfBoundsException e)
			{
				//do nothing...
			}
		
		}
	
	    GridBagLayout gbl2      = new GridBagLayout();
	    GridBagConstraints gbc2 = new GridBagConstraints();
	    gbc2.insets             = new Insets(9,0,9,0);
            JPanel buttonPanel      = new JPanel();
            buttonPanel.setLayout(gbl2);
		
		jcheckbox    = new JCheckBox("Show Console",logsDisplay.equalsIgnoreCase("yes"));

                if(webStartMode)
                {
                    hostField.setEnabled(false);
                    jcheckbox.setEnabled(false);
                }

		jcheckbox.setFont(loginTextFont);
		gbc2.weightx = 1.0;
		gbc2.fill    = GridBagConstraints.NONE;
		gbc2.anchor  = GridBagConstraints.WEST;
		gbl2.setConstraints(jcheckbox,gbc2);
		buttonPanel.add(jcheckbox);

                openBU = new JButton("Connect");
		openBU.setFont(loginTextFont);
		gbc2.weightx    = 1.5;
                gbc2.anchor = GridBagConstraints.CENTER;
		gbc2.gridwidth  = GridBagConstraints.RELATIVE;
		gbl2.setConstraints(openBU,gbc2);
		buttonPanel.add(openBU);
		
                exitBU = new JButton("Cancel");
		exitBU.setFont(loginTextFont);
		gbc2.weightx    = 3.0;
		gbc2.anchor     = GridBagConstraints.WEST;
		gbl2.setConstraints(exitBU,gbc2);
                buttonPanel.add(exitBU);
			
		//Adding actionListeners
		openBU.addActionListener(this);
		exitBU.addActionListener(this);
		GridBagLayout gbl      = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets             = new Insets(9,15,9,2);
		JPanel unameLabelPanel = new JPanel();
		unameLabelPanel.setLayout(gbl);
		
		labelUname     = new JLabel("User ID",JLabel.RIGHT);
		labelUname.setFont(loginTextFont);
		labelUname.setForeground(Color.black);
                gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx    = 0;
		gbc.gridx      = 1;
		gbc.gridy      = 1;
		gbl.setConstraints(labelUname,gbc);
		unameLabelPanel.add(labelUname);
		
		unameField     = new JTextField(25);
	  	gbc.fill       = GridBagConstraints.HORIZONTAL;
		gbc.weightx    = 1;
		gbc.gridx      = 2;
		gbc.gridy      = 1;
		gbl.setConstraints(unameField,gbc);
		unameLabelPanel.add(unameField);
		
		labelPasswd    = new JLabel("Password",JLabel.RIGHT);
		labelPasswd.setFont(loginTextFont);
		labelPasswd.setForeground(Color.black);
		gbc.fill       = GridBagConstraints.NONE;
		gbc.weightx    = 0;
		gbc.gridx      = 1;
		gbc.gridy      = 2;
                gbl.setConstraints(labelPasswd,gbc);
                unameLabelPanel.add(labelPasswd);
		
		passwdField    = new JPasswordField(25);
                passwdField.setEchoChar('*');
		gbc.fill       = GridBagConstraints.HORIZONTAL;
		gbc.weightx    = 1;
		gbc.gridx      = 2;
		gbc.gridy      = 2;
		gbl.setConstraints(passwdField,gbc);
		unameLabelPanel.add(passwdField);
		
		//Adding keyactionListeners
		KeyAction keyAction = new KeyAction();
		unameField.addKeyListener(keyAction);
                passwdField.addKeyListener(keyAction);
		hostField.addKeyListener(keyAction);
		portField.addKeyListener(keyAction);
		openBU.addKeyListener(keyAction);
                exitBU.addKeyListener(keyAction);
                jcheckbox.addKeyListener(keyAction);
		
		//Adding WindowListener
		frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e)
            {   
		    	System.exit(0);
            }
            public void windowOpened(WindowEvent e)
            {    
        		unameField.requestFocus();
            }
		});
         
		Border etch = BorderFactory.createEtchedBorder();
		unameLabelPanel.setBorder(etch);
		loginPanel.add(urlPanel,BorderLayout.NORTH);
        loginPanel.add(unameLabelPanel,BorderLayout.CENTER);
		loginPanel.add(buttonPanel,BorderLayout.SOUTH);
	
		JPanel statusPanel = new JPanel(false);
		statusLabel        = new JLabel("Ready");
		statusLabel.setFont(loginTextFont);
		statusLabel.setForeground(Color.black);
		
		
		progressBar = new JProgressBar(0,119);
		progressBar.setBorderPainted(false);
		statusPanel.setLayout(new BorderLayout());
		statusPanel.add("West",statusLabel);
		statusPanel.add("Center",progressBar);
	
		etch = new EtchedBorder(EtchedBorder.RAISED);
		statusPanel.setBorder(etch);
		
		unameField.requestFocus();
		exitBU.setNextFocusableComponent(hostField);
    
		JPanel contentPanel = new JPanel(false);
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add("Center",loginPanel);
		contentPanel.add("South",statusPanel);
		
		frame.getContentPane().add(contentPanel);
		frame.setSize(550,320);
                frame.setResizable(false);
		NmsClientUtil.setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());           
		if(frame != null)
                {
                    NmsClientUtil.centerWindow(frame);
                }
                try
                {
                    ImageIcon  iconFile;
                    if(iconfile != null && !(iconfile.equals("")))
                        {
                            File file = new File(iconfile);
                            if(file.exists())
                                iconFile = new ImageIcon(iconfile);
                            else
                                {
                                    iconFile = new ImageIcon("images/adventneticon.jpg");
                                    System.out.println("The specified image file does not exists.");
                                }
                        }
                    else
                        iconFile = new ImageIcon("images/adventneticon.jpg");
                    frame.setIconImage(iconFile.getImage());
                }
                catch(Exception e)
                {
                }
                frame.setVisible(true);
		
      }             //End of showLoginPanel
	
	/**
	 * Finds the equal item for the string in the Array list.
	 * The string is passed as a argument to the main function.
	 */
	private String  getEquivItem(String idString,String[] list)
	{   
		if(idString != null)
		{
			//start the loop
			int length = list.length;
			for(int i = 0 ; i < length ; i++)
			{
				String item = list[i];
				if(item.startsWith(idString))
				{   
					return item;
				}
			}
		}
		//we don't want to return a null value..
		return list[0];
	}
	
	/**
	 * Handles various key events,
	 * when we make the connection to the WebNMS Server
	 * through the Web NMS Authentication window.
	 */
	class KeyAction extends KeyAdapter 
	{
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				Object obj = e.getSource();
				if (obj instanceof JButton)
				{
					JButton but = (JButton)e.getSource();
					if (but == exitBU)
						System.exit(0);
				}
                                //When the user presses Enter on the User name field
				//we shall bring the focus to the password field.
				else if (obj == unameField)
				{
					passwdField.requestFocus();
					return;
				}
				//changes made here so as to make the Enter key in
				//the text fields viz., username passwd etc work!!
				startConnection();
			}
                        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                        {
                            System.exit(0);
                        }
			
		}
	}
	
	private Thread connectThread = null;
	private Thread watchThread   = null;
	
	/**
	 * Checks  the validity of username,password,hostname,portname, 
	 * When we make the connection to the WebNMS Server,
	 * through Web NMS Authentication Window.
	 */
	private void startConnection()
	{
		stopped = false;
		UName   = unameField.getText().trim();
		UPSWord = new String(passwdField.getPassword());
                UPSWord = UPSWord.trim();
		hostget = hostField.getText().trim();
		portget = portField.getText().trim();
		
		if(!hostget.equals(""))
		{
			whost   = hostget;  
		}
		else
		{
			showAlertMessage("        Enter the host name ","Invalid Entry");
			hostField.requestFocus();
			return;
		}	
				
		if(!portget.equals(""))
		{	
			int portlength = portget.length();
			
			if(portlength <= 5 )
			{
			 	wport   = Integer.parseInt(portget);
			}
			else
			{
				showAlertMessage("Port Number out of range [1 - 65535]","Invalid Entry");
				String portText = portField.getText();
				int portlen = portText.trim().length();
				if(portlen != 0)
				{
					portField.setSelectionStart(0);
					portField.setSelectionEnd(portlen);
				}
				portField.requestFocus();
				return;
			}
		}
		else 
		{
			showAlertMessage("       Enter the port Number ","Invalid Entry");
			portField.requestFocus();
			return;
		}
		
		
		if(wport > 65535 || wport == 0)
		{
			showAlertMessage("Port Number out of range [1 - 65535]","Invalid Entry");
			String portText = portField.getText();
			int portlen = portText.trim().length();
			if(portlen != 0)
			{
				portField.setSelectionStart(0);
				portField.setSelectionEnd(portlen);
			}
			portField.requestFocus();
			return;
		}
		logsDisplay = jcheckbox.isSelected()? "yes":"no";
		language = (String)jlanguagecombo.getSelectedItem();
		country  = (String)jcountrycombo.getSelectedItem();
		
		//get only the first two characters of the selected items..
		language = language.substring(0,2);
		country  = country.substring(0,2);
		
		paramList.put("LANGUAGE",language);			
		paramList.put("COUNTRY",country);
		
		if(UName.length() == 0 && UPSWord.length() == 0)
        {
            showAlertMessage("Enter User Name & Password ","Invalid Entry");
        	unameField.requestFocus();
         }
         else if(UName.length() == 0)
         {
             showAlertMessage("Enter User Name ","Invalid Entry");
             unameField.requestFocus();
         }
         else if (UPSWord.length() == 0)
         {
             showAlertMessage("Enter Password ","Invalid Entry");
             passwdField.requestFocus();
         }
         if(UName.length() != 0 && UPSWord.length() != 0)
		 {
			 connectThread = new Thread(this,"Connect");
			 connectThread.start();
			 
			 //May be we could replace this with javax.swing.Timer
			 //thread which does exactly this.But we here don't use
			 //that.No special reasons!!! 
			 watchThread   = new Thread(this,"Watch");
			 watchThread.setPriority(Thread.MIN_PRIORITY);
			 watchThread.start();
			 
			 openBU.setText("Stop");
			 openBU.setActionCommand("Stop");
			 
			 setEnabledFields(false);
			 //doconnect();
		 }
	}
	
	private void setEnabledFields(boolean flag)
	{


            if(webStartMode)
            {
                jcheckbox.setEnabled(false);
                hostField.setEnabled(false);
            }
            else
            {
                hostField.setEnabled(flag);
		portField.setEnabled(flag);
            }
		
		jcountrycombo.setEnabled(flag);
		jlanguagecombo.setEnabled(flag);
		
		unameField.setEnabled(flag);		
		passwdField.setEnabled(flag);
		
		exitBU.setEnabled(flag);
	}
	
	/**
	 * Accepts an exception and depending upon
	 * that exception ,it clears the fields in the WebNMS Authentication
	 * window.
	 * @param type exception type
	 */
	private void clearAllFields(Exception type)
	{
		if (type instanceof UnknownHostException ||
			type instanceof MalformedURLException ||
			type instanceof ConnectException)
		{
			hostField.setText("");
			portField.setText("");
			hostField.requestFocus();
		}
		else if(type instanceof IOException )
		{
			unameField.setText("");
			passwdField.setText("");
			unameField.requestFocus();
			
		}
	}
	
	/**
	 * This method reads the languages and countries from the file language.txt
	 * and adds it to the combobox.
	 */
	private void readLanguageAndCountry()
	{
		try
		{
			URL url = getClass().getResource("LanguagesAndCountries.txt");
			InputStream is = url.openStream();
			boolean isLanguage = false;
			boolean isCountry  = false;
			langVect = new Vector(10,1);
			countryVect = new Vector(10,1);
			BufferedReader reader  = new BufferedReader(new InputStreamReader(is)); 
			int data,m =0 ;
			String line = "";
			while((line = reader.readLine()) != null)
			{
				line = line.trim();
				if(line.equals("<LANGUAGE>"))
				{
					isLanguage = true;
					isCountry = false;
					continue;
				}
				else if(line.equals("<COUNTRY>"))
				{
					isLanguage = false;
					isCountry = true;
					continue;
				}
				if(isLanguage)
				{
					langVect.addElement (line);
					//jlanguagecombo.addItem(line);  				
				}
				else if(isCountry)				
				{
					countryVect.addElement (line);
					//jcountrycombo.addItem(line);  
				}
			}
			is.close();
		}
		catch(Exception e)
		{
			System.err.println("Exception occured while reading language and country for application client: " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * This method reads the Title and Text from the file LoginText.txt
	 * and adds it to the combobox.
	 */
          
    private boolean expTitleSet = false;
    private boolean expTextSet = false;
    private boolean firstLoginTitleSet = false;
    private boolean firstLoginTextSet = false;
    String passwdDialogIcon = null;
    
    private void readTitleAndText()
    {
        try
        {
            URL url = getClass().getResource("LoginInfo.txt");
            InputStream is = url.openStream();
            StringBuffer sb = new StringBuffer();
            int data;
            while( (data=is.read())!= -1)
            {
                sb.append((char)data);
            }
            is.close();
            
            StringTokenizer st = new StringTokenizer(sb.toString());
            boolean isTitle = false;
            boolean isText = false;
            StringBuffer titlebuf = new StringBuffer();
            StringBuffer textbuf = new StringBuffer();
            StringBuffer expTextbuf = null;
            StringBuffer expTitlebuf = null;
            StringBuffer firstLoginTitlebuf = null;
            StringBuffer firstLoginTextbuf = null;
            
            while(st.hasMoreTokens())
            {
                String token = st.nextToken().trim();
                if(token.equals("<TITLE>"))
                {
                    isTitle = true;
                    isText = false;
                    continue;
                }
                else if(token.equals("<TEXT>"))
                {
                    isText = true;
                    isTitle = false;
                    continue;
                }
                else if(token.equals("<ICON>"))
                {
                    if(st.hasMoreTokens())
                        iconfile = st.nextToken().trim();
                    continue;
                }
                
                else if(token.equals("<PASSWORD_DIALOG_IMAGE>"))
                {
                    if(st.hasMoreTokens())
                        passwdDialogIcon = st.nextToken().trim();
                    continue;
                }
                
                else if(token.equals("<HOST>"))
                {
                    token = paramHost;
                }
                else if(token.equals("<PORT>"))
                {
                    token = "" + paramPort;
                }
                else if(token.equals("<EXPIRY_MESSAGE>"))		//Tag used to identify the message that is popped up
                {					
                    //when the user password expires.
                    expTextSet = true;
                    expTitleSet = false;
                    isText = false;
                    isTitle = false;
                    continue;
                }
                else if(token.equals("<EXPIRY_DIALOG_TITLE>")) 	//Tag used to identify the title for the dialog that
                {												//is popped up when the user password expires		
                    expTitleSet = true;
                    expTextSet = false;
                    isText = false;
                    isTitle = false;
                    continue;
                }
                else if(token.equals("<FIRST_LOGIN_MESSAGE>"))		//Tag used to identify the message that is popped up
                {										                          firstLoginTitleSet = false;
                firstLoginTextSet = true;
                expTextSet = false;
                expTitleSet = false;
                isText = false;
                isTitle = false;
                continue;
                }
                else if(token.equals("<FIRST_LOGIN_DIALOG_TITLE>")) 	//Tag used to identify the title for the dialog that
                {						
                    firstLoginTitleSet = true;
                    firstLoginTextSet = false;
                    expTextSet = false;
                    expTitleSet = false;
                    isText = false;
                    isTitle = false;
                    continue;
                }
                
                if(isTitle)
                {
                    titlebuf.append(token+" ");
                }
                if(isText)
                {
                    textbuf.append(token+" ");
                }
                if(expTitleSet)
                {
                    if(expTitlebuf == null)
                    {
                        expTitlebuf = new StringBuffer();
                    }
                    expTitlebuf.append(token+" ");
                }
                if(expTextSet)
                {
                    if(expTextbuf == null)
                    {
                        expTextbuf = new StringBuffer();
                    }
                    expTextbuf.append(token+" ");
                }
                if(firstLoginTextSet)
                {
                    if(firstLoginTextbuf == null)
                    {
                        firstLoginTextbuf = new StringBuffer();
                    }
                    firstLoginTextbuf.append(token+" ");
                }
                if(firstLoginTitleSet)
                {
                    
                    if(firstLoginTitlebuf == null)
                    {
                        firstLoginTitlebuf = new StringBuffer();
                    }
                    firstLoginTitlebuf.append(token+" ");
                }
            }
            
            
            loginTitle = titlebuf.toString();
            loginTxt = textbuf.toString();
            
            //changes done b'cos of improper return statement
            if(expTextbuf != null)
            {
                expiryText = expTextbuf.toString();
            }
            if(expTitlebuf != null)
            {
                expiryTitle = expTitlebuf.toString();
            }
            if(firstLoginTextbuf != null)
            {
                firstLoginText = firstLoginTextbuf.toString();
            }
            if(firstLoginTitlebuf != null)
            {
                firstLoginTitle = firstLoginTitlebuf.toString();
            }
            
            
        }
        catch(Exception e)
        {
            System.err.println("Exception occured while reading title and text for application client: " + e);
        }
    }
    
    String expiryText = null;
    String expiryTitle = null;
    String firstLoginTitle = null;
    String firstLoginText = null;

	
	/**
	 * Implementation of the Runnable interface.API users will not require
	 * this method.
	 */
	public void run()
	{
		if(Thread.currentThread().getName().equals("Connect"))
		{
			try
			{
				doconnect();
				//showStatusText("Connection Established");
			}
			catch(ClientConnectionException cex)
			{
				String title = "Login Failed";
				Exception type = cex.getException();
                                connecting = false;
				
				if(type instanceof UnknownHostException)
				{
					fornextfocus = "host";
					title = "UnknownHostException";
				}
				else if(type instanceof MalformedURLException)
				{
					title = "MalformedURLException";
				}
				else if (type instanceof ConnectException)
				{
					fornextfocus = "port";
					title = "ConnectException";
				}
                                if(cex.getMessage().equals("Password Expired"))
                                {
                                    showStatusText("Password Expired. Please re-configure password settings");
                                    // We need to set the applet stub so as to get the applet paramters
                                    // necessary invoking the ChangePasswordServlet when
                                    // the user calls reuseSettingsAndProceed() or setNewPasswordConfiguration()
                                    // methods.
                                    setAppletStub();
                                    
                                    // Dialog used for getting the new password
                                    // configuration from the user. 
                                    showPasswordConfigurationDialog("Password Expired");
                                    return;
				}
                                else if(cex.getMessage().equals("First Login"))
                                {
                                    showStatusText("You are logged in for the First time. Please confirm your password");
                                    // We need to set the applet stub so as to get the applet paramters
                                    // necessary invoking the ChangePasswordServlet when
                                    // the user calls reuseSettingsAndProceed() or setNewPasswordConfiguration()
                                    // methods.
                                    setAppletStub();
                                    
                                    // Dialog used for getting the new password
                                    // configuration from the user. 
                                    showPasswordConfigurationDialog("First Login");
                                    return;
				}
				showStatusText("Connection failure");
				showAlertMessage(cex.getMessage(),title);
				hostField.requestFocus();
				//do we really need to clear the fields,I am not for this, because
				//sometimes it seems quite ridiculous to clear the contents of the
				//textfield.
				//clearAllFields(type);
				
				stopConnection();
			}
		}
		else
		{
			int count = 0;
			
			//waiting here for some amount of time
			//may be in future this time out needs to
			//be configurable..
			while(!connected && connecting && count < 120 )
			{
				try
				{
					//i.e., we are giving upto 1min for the connection
					//to take place..Hope this is more than enough to 
					//establish a connection with a remote server.This
					//time interval in future will be made configurable..
					Thread.sleep(1000);
					progressBar.setValue(count);
					count ++;
				}
				catch(Exception e)
				{
				}
			}
			if(!connected && connecting)
			{
				showAlertMessage("Unable to contact host " + hostField.getText(),"Connection Failure");
				stopConnection();
			}
		}
		//doconnect();
	}
	
	//Yes, there is a way to do this more generically. We can use something like
	//a StatusListener which will intimate the staus of the WebNMS client at each
	//instance to its registered listener.
	private void showStatusText(String text)
	{
		if(statusLabel != null)
		{
			statusLabel.setText(text);
		}
	}

	private void checkIfThisCan()
	{
		try
		{
			new JFrame();
		}catch(java.lang.InternalError e)
		{
			System.err.println(e.getMessage());	
			if("Linux".equals(System.getProperty("os.name")))
			{
				System.err.println("This application can run only in X Windows. \nPlease check whether the X Window is running and the DISPLAY variable properly set.");
			}
			System.exit(0);
		}
	}
	
	private void restoreFrame()
	{
		frame.setVisible(true);
		resetCache();
		setEnabledFields(true);
		connectThread = null;
		watchThread = null;
		if(applet != null)
		{
			applet = null;
		}
		openBU.setEnabled(true);
		openBU.setText("Connect");
		openBU.setActionCommand("Connect");
		showStatusText("Ready");
		if(dialog != null)
		{
			dialog.setVisible(false);
			dialog.dispose();
			dialog = null;
		}
		unameField.requestFocus();
		unameField.selectAll();
	}	
	
	// This method resets the hashtable which is used to
	// hold the applet parameters and thie values. 
	
	private void resetCache()
	{
		paramList = new Hashtable(10);
		paramList.put("LANGUAGE",language); //This is because, when the 
		paramList.put("COUNTRY",country);   //user calls the doconnect()
		//setEnabledFields(false);            //he will not get these params.
	}
	
	private void disposeTheDialog()
	{
		if(passwordDialog != null)
		{
			passwordDialog.setVisible(false);
			passwordDialog.dispose();
			frame.setVisible(true);
		}
	}
}                     // End of the class WebNMSClient
