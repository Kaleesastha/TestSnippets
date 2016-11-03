//$Id: SmtpMailer.java,v 1.9 2010/10/29 13:46:29 swaminathap Exp $

package com.adventnet.nms.smtp;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.adventnet.nms.util.MailConfigParser;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.security.authorization.Coding;

public class SmtpMailer {

    String serverAddr = "";
    String[] toAddr = {"root"};
    String fromAddr = "sekar@adventnet.com"; //NO I18N
    String subject = "";
    String fileAttachment = null;
    String userName = "";
    String password = "";

	Session session;
	
	int smtp_timeout = 0;
	int smtp_conn_timeout = 0;
        String smtpssl_port="465";//No i18n
	String smtp_port="25";//No i18n
	boolean smtpAuth = false;
	boolean smtpSSL = false;
    /**
        Instantiates a SmtpMailer object and sets the to, from and
        Smtp server addresses. Also opens the connection to the
        Server. The connection to the server is made only by the
        constructor. To connect to a new server, a new SmtpMailer
        object has to be instantiated.
        @param String server: The server name or ip address
        @param String from: The from address like scott@sun.com
        @param String to: The to address like sekar@adventnet.com
    */
    public SmtpMailer(String server, String from, String to, String sub) throws IOException
    {
  	toAddr[0] = to;
        fromAddr = from;
        subject = sub;
        serverAddr = server;
        
	// create some properties and get the default Session
        Properties props = new Properties();
        props.put("mail.smtp.host", serverAddr);
        
        smtp_timeout = getSmtpTimeout();
	smtp_port=getSmtpPort();         //to set port configured in server stratup
	props.put("mail.smtp.port", smtp_port);
		if (smtp_timeout != -1)
		{
			props.put("mail.smtp.timeout",""+smtp_timeout);
		}

		smtp_conn_timeout = getSmtpConnectionTimeout();
		if (smtp_conn_timeout != -1)
		{
			props.put("mail.smtp.connectiontimeout",""+smtp_conn_timeout);
		}
        //props.put("mail.debug", "true");

        //session = Session.getDefaultInstance(props, null);
		session = Session.getInstance(props, null);
    }
	
	
    public SmtpMailer(String server, String from, String[] to, String sub) throws IOException
    {
        toAddr = to;
        fromAddr = from;
        subject = sub;
        serverAddr = server;
    	
    	// create some properties and get the default Session
        Properties props = new Properties();
        props.put("mail.smtp.host", serverAddr);
       	smtp_port=getSmtpPort();         //to set port configured in server stratup
        props.put("mail.smtp.port", smtp_port);	 
        smtp_timeout = getSmtpTimeout();
		if (smtp_timeout != -1)
		{
			props.put("mail.smtp.timeout",""+smtp_timeout);
		}

		smtp_conn_timeout = getSmtpConnectionTimeout();
		if (smtp_conn_timeout != -1)
		{
			props.put("mail.smtp.connectiontimeout",""+smtp_conn_timeout);
		}
        //if (debug) props.put("mail.debug","true");
		
		session = Session.getInstance(props, null);
       // session = Session.getDefaultInstance(props, null);
        //session.setDebug(debug);
    }
    
    //vkarthik - START
   
    public SmtpMailer(String server, String from, String[] to, String sub,String usr, String pwd) throws IOException
    {
	toAddr = to;
        fromAddr = from;
        subject = sub;
        serverAddr = server;
	userName = usr;
	password = pwd;

   	
    	// create some properties and get the default Session
	Properties props = new Properties();
	props.put("mail.smtp.host", serverAddr);
	props.put("mail.smtp.auth","true");
	smtp_port=getSmtpPort();         //to set port configured in server stratup
	props.put("mail.smtp.port", smtp_port);		
	    smtp_timeout = getSmtpTimeout();
		
		if (smtp_timeout != -1)
		{
			props.put("mail.smtp.timeout",""+smtp_timeout);
		}

		smtp_conn_timeout = getSmtpConnectionTimeout();
		if (smtp_conn_timeout != -1)
		{
			props.put("mail.smtp.connectiontimeout",""+smtp_conn_timeout);
		}
        //if (debug) props.put("mail.debug","true");
	String decPassword = null;
	try
	    {
		decPassword  = Coding.convertFromBase(password);
	    }
	catch(Exception e)
	    {
		System.out.println("Exception while Decrpting PASSWORD ");
		e.printStackTrace();
	    }
	SMTPAuthenticator auth = new SMTPAuthenticator(userName,decPassword);
	session = Session.getInstance(props,auth);
              
    }
    
    public SmtpMailer(String server, String from, String[] to, String sub, String fileAttach) throws IOException
    {
	toAddr = to;
        fromAddr = from;
        subject = sub;
        serverAddr = server;
	fileAttachment = fileAttach;
   	
    	// create some properties and get the default Session
        Properties props = new Properties();
        props.put("mail.smtp.host", serverAddr);
       	smtp_port=getSmtpPort();         //to set port configured in server stratup
	props.put("mail.smtp.port", smtp_port);	 
        smtp_timeout = getSmtpTimeout();
		if (smtp_timeout != -1)
		{
			props.put("mail.smtp.timeout",""+smtp_timeout);
		}

		smtp_conn_timeout = getSmtpConnectionTimeout();
		if (smtp_conn_timeout != -1)
		{
			props.put("mail.smtp.connectiontimeout",""+smtp_conn_timeout);
		}
	session = Session.getInstance(props,null);
              
    }

    public SmtpMailer(String server, String from, String[] to, String sub, String fileAttach,String usr, String pwd) throws IOException
    {
	toAddr = to;
        fromAddr = from;
        subject = sub;
        serverAddr = server;
	fileAttachment = fileAttach;
	userName = usr;
	password = pwd;

   	
    	// create some properties and get the default Session
        Properties props = new Properties();
        props.put("mail.smtp.host", serverAddr);
	    props.put("mail.smtp.auth","true");
	    smtp_port=getSmtpPort();         //to set port configured in server stratup
	    props.put("mail.smtp.port", smtp_port);	
	    smtp_timeout = getSmtpTimeout();
		if (smtp_timeout != -1)
		{
			props.put("mail.smtp.timeout",""+smtp_timeout);
		}

		smtp_conn_timeout = getSmtpConnectionTimeout();
		if (smtp_conn_timeout != -1)
		{
			props.put("mail.smtp.connectiontimeout",""+smtp_conn_timeout);
		}
        //if (debug) props.put("mail.debug","true");
	String decPassword = null;
	try
	    {
		decPassword  = Coding.convertFromBase(password);
	    }
	catch(Exception e)
	    {
		System.out.println("Exception while Decrpting PASSWORD ");
		e.printStackTrace();
	    }
	SMTPAuthenticator auth = new SMTPAuthenticator(userName,decPassword);
	session = Session.getInstance(props,auth);
              
    }
    
    public SmtpMailer(String accountName,String subject,String fileAttach) throws Exception
    {
    	Properties accProps = MailConfigParser.getInstance().getMailServerDetails(accountName);
    	if(accProps == null || accProps.size() == 0)
    	{
    		throw new Exception("Mail account details not foung for account : "+accountName);
    	}
    	toAddr = accProps.getProperty("TO_ADDRESS").split(",");
    	smtpSSL = Boolean.parseBoolean(accProps.getProperty("SMTP_SSL"));
    	
	    fromAddr = accProps.getProperty("FROM_ADDRESS");
	    this.subject = subject;
	    serverAddr = accProps.getProperty("SMTP_SERVER");
	    if(fileAttach != null && !fileAttach.trim().equals(""))//No i18n
	    {
	    	fileAttachment = fileAttach;
	    }
	    
	    String usr = accProps.getProperty("USER_NAME");
	    if(usr !=null && !usr.trim().equals(""))//No i18n
	    {
		    userName = usr;
	    }
	    String pwd = accProps.getProperty("PASSWORD");
	    if(pwd !=null && !pwd.trim().equals(""))//No i18n
	    {
	    	password = pwd;
	    }
	    
	    smtp_port = accProps.getProperty("PORT");
	    smtpAuth = Boolean.parseBoolean(accProps.getProperty("AUTH"));
	    validatePort();
	    init();
    }
    
    private void init()
    {
    	try
    	{
    		Properties props = getMailDetails();
    		if(userName.equals("") || password.equals("") || !smtpAuth)//No i18n
    		{
    			session = Session.getInstance(props,null);
    		}
    		else if(!userName.equals("") && !password.equals(""))//No i18n
    		{ 
    			String decPassword =new String();
    			try
    			{
    				decPassword  = Coding.convertFromBase(password);
    			}
    			catch(Exception e)
    			{
    				System.err.println("Exception while Decrypting PASSWORD ");//No i18n
    				e.printStackTrace();
    			}
    			props.put("mail.smtp.auth","true");//No i18n
    			SMTPAuthenticator auth = new SMTPAuthenticator(userName,decPassword);
    			session = Session.getInstance(props,auth);
    		}    		
    	}
    	catch(Exception e)
    	{
    		System.err.println("Exception is : "+e);//No i18n
    		e.printStackTrace();
    	}  

    }
    
    public SmtpMailer(String server, String from, String[] to, String sub, String fileAttach,String usr, String pwd, boolean ssl)throws IOException
    {
	    toAddr = to;
	    fromAddr = from;
	    subject = sub;
	    serverAddr = server;
	    if(fileAttach==null)//No i18n
	    {
		    fileAttach="";//No i18n
	    }
	    if(usr==null)//No i18n
	    {
		    usr="";//No i18n
	    }
	    if(pwd==null)//No i18n
	    {
		    pwd="";//No i18n
	    }
	    fileAttachment = fileAttach;
	    userName = usr;
	    password = pwd;                 
	    try
	    {
	    	Properties props = getMailDetails(ssl);
	    	if(userName.equals("") || password.equals(""))//No i18n
	    	{
	    		session = Session.getInstance(props,null);
	    	} 
	    	else if(!userName.equals("") && !password.equals(""))//No i18n
	    	{ 
	    		String decPassword =new String();
	    		try
	    		{
	    			decPassword  = Coding.convertFromBase(password);
	    		}
	    		catch(Exception e)
	    		{
	    			System.err.println("Exception while Decrypting PASSWORD ");//No i18n
	    			e.printStackTrace();
	    		}
	    		props.put("mail.smtp.auth","true");//No i18n
	    		SMTPAuthenticator auth = new SMTPAuthenticator(userName,decPassword);
	    		session = Session.getInstance(props,auth);
	    	}
	    }
	    catch(Exception e)
	    {
		    System.err.println("Exception is : "+e);//No i18n
		    e.printStackTrace();
	    }  

    }

    public Properties getMailDetails(boolean isSSLMode)
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", serverAddr);//No i18n
       	 
	smtp_port=getSmtpPort();         //to set port configured in server stratup
	props.put("mail.smtp.port", smtp_port); 
        smtp_timeout = getSmtpTimeout();
        if (smtp_timeout != -1)
	{
            props.put("mail.smtp.timeout",""+smtp_timeout);//No i18n
	}
	smtp_conn_timeout = getSmtpConnectionTimeout();
	if (smtp_conn_timeout != -1)
	{
		props.put("mail.smtp.connectiontimeout",""+smtp_conn_timeout);//No i18n
	}
        if(isSSLMode)
        {
            smtpssl_port=getSslPort();
            props.put("mail.smtp.port",smtpssl_port);//No i18n
            props.put("mail.smtp.starttls.enable","true");//No i18n
            props.put("mail.smtp.socketFactory.port",smtpssl_port);//No i18n
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//No i18n
            props.put("mail.smtp.socketFactory.fallback", "false");//No i18n
        }
        return props;
    }
    
    public Properties getMailDetails()
    {
    	Properties props = new Properties();
    	props.put("mail.smtp.host", serverAddr);//No i18n

    	props.put("mail.smtp.port", smtp_port); 
    	smtp_timeout = getSmtpTimeout();
    	if (smtp_timeout != -1)
    	{
    		props.put("mail.smtp.timeout",""+smtp_timeout);//No i18n
    	}
    	smtp_conn_timeout = getSmtpConnectionTimeout();
    	if (smtp_conn_timeout != -1)
    	{
    		props.put("mail.smtp.connectiontimeout",""+smtp_conn_timeout);//No i18n
    	}
    	if(smtpSSL)
    	{
    		props.put("mail.smtp.starttls.enable","true");//No i18n
    		props.put("mail.smtp.socketFactory.port",smtp_port);//No i18n
    		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//No i18n
    		props.put("mail.smtp.socketFactory.fallback", "false");//No i18n
    	}
    	return props;
    }
   
    public String getSmtpPort(){
	    smtp_port= System.getProperty("smtp.port");//No i18n
	    if(smtp_port!=null && !smtp_port.equals(""))//No i18n
	    {
		    return smtp_port;

	    }
	    else
	    {
		    return "25";//No i18n
	    }
    }


    //vkarthik - END

    /* Change the from address */
    public void setFromAddress(String from) {
        fromAddr = from;
    }

    /* Change the to address */
    public void setToAddress(String to) {
        String too[] = new String[1];
        too[0] = to;
    	setToAddress(too);
    }
	
	/* Change the to address */
    public void setToAddress(String[] to) {
    	toAddr = to;
    }

        /* Change the Subject */
    public void setSubject(String sub) {
        subject = sub;
    }
    
    //vkarthik -START
    public void setFileAttachment(String file){
	fileAttachment = file;
    }
    
       
    public String getSslPort(){
        smtpssl_port= System.getProperty("smtp.sslport");//No i18n
        if(smtpssl_port!=null && !smtpssl_port.equals(""))//No i18n
        {
            return smtpssl_port;
            
        }
        else
        {
            return "465";//No i18n
        }
    } 
    
    //vkarthik - END

    /**
        Sends the message to the SMTP Server which should forward the
        mail to the toAddr.
        @param String message: Message to be sent.
        @Excpetion IOException is thrown on send errors
    */
    public void sendMessage(String message) throws IOException,MessagingException
    {
    	try {
            // create a message
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromAddr));
    		InternetAddress[] address = new InternetAddress[toAddr.length];
    		for(int i=0;i<toAddr.length;i++)
    	    {
                address[i] = new InternetAddress(toAddr[i]);
    	    }
            msg.setRecipients(Message.RecipientType.TO, address);
            String charset = NmsUtil.getParameter("EMAIL_CHARSET");//No i18n
            if(charset != null && !charset.trim().equals(""))
            {
                msg.setSubject(subject,charset);
            }
            else
            {
                msg.setSubject(subject);
            }
            msg.setSentDate(new Date());
            // If the desired charset is known, you can use
            // setText(text, charset)
	    
	    //vkarthik - START
	    //msg.setText(message);
	    // create the message part 
	    MimeBodyPart messageBodyPart = new MimeBodyPart();

	    //fill message
        if(charset != null && !charset.trim().equals(""))
        {
            messageBodyPart.setText(message,charset);
        }
        else
        {
            messageBodyPart.setText(message);
        }
	    
	    Multipart multipart = new MimeMultipart();
	    multipart.addBodyPart(messageBodyPart);
	    
	    // create attachment part
	    if(fileAttachment != null)
		{
		    //String fileName = "";
		    StringTokenizer st = new StringTokenizer(fileAttachment,",");
		    while(st.hasMoreTokens())
			{
			  String fileUrl = st.nextToken();
			  messageBodyPart = new MimeBodyPart();
			  DataSource source = new FileDataSource(fileUrl);
			  File file = new File(fileUrl);
			  String filename = file.getName();
			  messageBodyPart.setDataHandler(new DataHandler(source));
			  messageBodyPart.setFileName(filename);
			  multipart.addBodyPart(messageBodyPart);
			}

		  }
	    
	    // Put parts in message
	    msg.setContent(multipart);
	    
	    //vkarthik - END
            Transport.send(msg);

        } catch (MessagingException mex) {
			 System.err.println("Exception while sending mail notification. "+mex.getMessage());
		Exception ex = mex;
            do {
                if (ex instanceof SendFailedException) {
                    SendFailedException sfex = (SendFailedException)ex;
                    Address[] invalid = sfex.getInvalidAddresses();
                    if (invalid != null) {
                        System.err.println("    ** Invalid Addresses");
                        if (invalid != null) {
                            for (int i = 0; i < invalid.length; i++) 
                            System.err.println("         " + invalid[i]);
                        }
                    }
                    Address[] validUnsent = sfex.getValidUnsentAddresses();
                    if (validUnsent != null) {
                        System.err.println("    ** ValidUnsent Addresses");
                        if (validUnsent != null) {
                            for (int i = 0; i < validUnsent.length; i++) 
                            System.err.println("         "+validUnsent[i]);
                        }
                    }
                    Address[] validSent = sfex.getValidSentAddresses();
                    if (validSent != null) {
                        System.err.println("    ** ValidSent Addresses");
                        if (validSent != null) {
                            for (int i = 0; i < validSent.length; i++) 
                            System.err.println("         "+validSent[i]);
                        }
                    }
   
            }
		if ((ex instanceof ConnectException) || (ex instanceof UnknownHostException) || (ex instanceof SocketException))
		{
			System.err.println("Invalid HostName or Port, unable to connect the mail server"); //No I18N
			break;
		}

           } while ((ex = ((MessagingException)ex).getNextException()) != null);
	throw mex;
    }
  }
    //Method for setting SMTP timeout value
    private int getSmtpTimeout()
    {
  	int retTimeoutVal = -1;
  	int smtpTimeout = 0;
    	String timeout=System.getProperty("smtp.timeout");
  		if (timeout != null)
  		{
  			try
  			{
  				smtpTimeout = Integer.parseInt(timeout);
  				if (smtpTimeout > 0)
  				{
  
  					return smtpTimeout;
  				}
  				else
  				{
  				    System.err.println("Invalid SMTP timeout value specified : "+smtpTimeout+", setting default infinite timeout"); //No Internationalisation
  				    return retTimeoutVal;
  				}
  			}
  			catch(Exception e)
  			{
  				System.err.println("Invalid SMTP timeout value, setting default infinite timeout"); //No Internationalisation
  				return retTimeoutVal;
  			}
  		}
  		return retTimeoutVal;
    }
    
	//Method for setting SMTP connection timeout value
    private int getSmtpConnectionTimeout()
    {
    	int retConnTimeoutVal = -1;
    	int smtpConnTimeout = 0;
      	String connTimeout=System.getProperty("smtp.connectiontimeout");
    		if (connTimeout != null)
    		{
    			try
    			{
    				smtpConnTimeout = Integer.parseInt(connTimeout);
    				if (smtpConnTimeout > 0)
    				{
    					return smtpConnTimeout;
    				}
    				else
    				{
    				    System.err.println("Invalid SMTP connection timeout value specified : "+connTimeout+", setting default infinite timeout"); //No Internationalisation
    				    return retConnTimeoutVal;
    				}
    			}
    			catch(Exception e)
    			{
    				System.err.println("Invalid SMTP connection timeout value, setting default infinite timeout"); //No Internationalisation
    				return retConnTimeoutVal;
    			}
    		}
    		return retConnTimeoutVal;
  }

    /* Closes the connection with the server */
    public void close() throws IOException {
    }
    
    private void validatePort()
    {
    	if(smtp_port == null || smtp_port.trim().equals(""))
    	{
    		if(smtpSSL)
    		{
    			smtp_port = "465";

    		}
    		else
    		{	
    			smtp_port = "25";
    		}
    	}
    }
    
}
