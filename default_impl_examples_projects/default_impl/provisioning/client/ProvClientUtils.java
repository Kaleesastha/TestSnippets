/* $Id: ProvClientUtils.java,v 1.3 2007/08/02 12:17:35 srajeswari Exp $ */
package com.adventnet.nms.provisioning.ui;

// Java imports
import java.text.MessageFormat;
import java.util.Properties;
import java.rmi.Naming;
import java.net.UnknownHostException;
import java.net.InetAddress;
import javax.naming.CommunicationException;
import java.io.StringReader;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.w3c.dom.Document;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import java.io.*;

// Nms imports
import com.adventnet.nms.provisioning.ProvisioningAPI;
import com.adventnet.nms.provisioning.server.ProvisioningAPIImpl_MessageStub;
import com.adventnet.nms.provisioning.server.ProvisioningAPIImpl_SessionStub;
//import com.adventnet.nms.provisioning.ProvisioningException;
import com.adventnet.nms.provisioning.xml.Template;
import com.adventnet.nms.provisioning.xml.TemplateResult;
import com.adventnet.nms.provisioning.xml.OperationIdentifier;
import com.adventnet.management.config.xml.BaseElement;
import com.adventnet.security.authentication.RMIAccessAPI;
import com.adventnet.nms.util.ClientFrameWorkAPI;
import com.adventnet.management.i18n.AdventNetResourceBundle;

/**
 * The <code>ProvClientUtils</code> class provides methods for obtaining 
 * the references to ProvisioningAPI in RMI mode,JMS mode and TCP SESSION mode.    
 * @since Provisioning 3.0
 */

public class ProvClientUtils
{
	//AdventNetResourceBundle for Internalization support.
	public static AdventNetResourceBundle englishToNative=null;
	
	/**
	 * Returns a reference,for the provisioningAPI in RMI mode,JMS mode and TCP 
	 * SESSION mode.
	 * @param prop The Properties used to create the ProvisioningAPI.
	 * The following keys are mandatory for create the ProvisioningAPI. If any of
	 * the key is null, it throws IllegalArgumentException.<br>
	 *<br>(i) MODE     ---  Specify the Communication mode (RMI|JMS|SESSION).
	 * If the mode is other than the supported mode,
	 * it throws IllegalArgumentException. 
	 * <br>(ii)USERNAME ---  Name of the user. 
	 * <br>(iii) PASSWORD ---  Password of the user.<br>
	 *<br> The other keys are related to RMI as well as SESSION mode are as follows.<br>
	 * 
	 * <br>(i) SERVERNAME  --- Host to which the Client is to be connected.
	 * Default value is "localhost"
	 * <br>(ii) SERVERPORT  --- Port number where the server is running.
	 * In RMI mode the default value is "1099".In SESSION mode the default value is "9090".
	 * <br><br>In JMS mode need to set the environment Properties for the JMSProvider.
	 * Environment Properties used to create the initial context.
	 *<code>
	 *<p>
	 *<blockquote>
	 * Properties prop = new Properties();
	 *<br>
	 * prop.put(&quot;requestQueue&quot;, &lt;Name of the Queue, From this Queue the ProvClient put the request and the ProvServer takes the request.&gt;)
	 *<br>
	 * prop.put(&quot;responseQueue&quot;, &lt;Name of the Queue, From this Queue the ProvClient takes the response and the ProvServer put the response&gt;)
	 *<br>
	 * prop.put(&quot;QueueConnectionFactory&quot;, &lt;The name of the QueueConnectionFactory ,the ProvClient and the ProvServer use it to create the connection with the Jms Provider&gt;)
	 *<br>
	 * prop.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, &lt;ContextFactory Name for the JmsProvider&gt;)
	 *<br>
	 * prop.put(javax.naming.Context.PROVIDER_URL, &lt;Provider_URL for the particular JmsProvider&gt;)
	 *</blockquote>
	 *</code>
	 * Also the Properties may contain Provider Specific Properties.
	 * @return a reference for a ProvisioningAPI.
	 * @throws Exception failed to get the ProvisioningAPI handle.(If any Communication error occurs or Authentication failed for the user.)
	 * @throws IllegalArgumentException if any of the mandatory key is null. 
	 * @see com.adventnet.nms.provisioning.ProvisioningAPI
	 * @since Web NMS 4.0.0
	 */

	public static ProvisioningAPI getProvisioningAPI(Properties prop) throws Exception
	{
		if(prop == null)
			throw new IllegalArgumentException(getString("Properties cannot be null"));
		String mode = (String)prop.remove("MODE");
		String userName = (String)prop.remove("USERNAME");
		String password = (String)prop.remove("PASSWORD");
		String serverName = (String)prop.remove("SERVERNAME");
		String serverPort = (String)prop.remove("SERVERPORT");
		if(mode == null)
			throw new IllegalArgumentException(getString("Property MODE cannot be null"));
		if(userName == null)
			throw new IllegalArgumentException(getString("Property USERNAME cannot be null"));
		if(password == null)
			throw new IllegalArgumentException(getString("Property PASSWORD cannot be null"));
		if(serverName == null)
		{
			try
			{
				serverName = InetAddress.getLocalHost().getHostName();
			}
			catch(UnknownHostException nke)
			{
				serverName ="localhost";
			}
		}
		try
		{
			if(mode.equalsIgnoreCase("RMI"))
			{
				if(serverPort == null)
					serverPort = "1099";
				String url = "rmi://"+serverName+":"+serverPort+"/RMIAccessAPI";
				RMIAccessAPI accessAPI = (RMIAccessAPI) Naming.lookup(url);
				return ((ProvisioningAPI)accessAPI.getAPI(userName, password, "ProvisioningAPI"));
			}
			else if(mode.equalsIgnoreCase("JMS"))
			{
				return new ProvisioningAPIImpl_MessageStub(prop, userName, password);
			}
			else if(mode.equalsIgnoreCase("SESSION"))
			{
				prop.put("USERNAME", userName);
				prop.put("PASSWORD", password);
				prop.put("HOSTNAME", serverName);
				if(serverPort == null)
					serverPort = "9090";
				prop.put("WEB-SERVER-PORT", serverPort);
				new ClientFrameWorkAPI(prop);
				return new ProvisioningAPIImpl_SessionStub();
			}
		}
		catch(Exception e)
		{
			String errorMessage = e.getMessage() != null ? e.getMessage() : e.toString();
			throw new Exception(errorMessage);
		}
		throw new IllegalArgumentException(MessageFormat.format(getString("CommunicationMode {0} is not supported"), new Object[]{mode})); // No I18N
	}


	/** 
	 * Returns a Template,TemplateResult or OperationIdentifier instance with the
	 * given xml String. This is used to get the 
	 * Template/TemplateResult/OperationIdentifier  
	 * instance from the string returned by the provision method.
	 * @see com.adventnet.nms.provisioning.ProvisioningAPI#provision(String)
	 * @since Web NMS 4.0.0
	 */
	public static BaseElement getBaseElement(String xmlString)throws Exception 
	{
		InputSource input = null;
		Document doc = null;
		try 
		{

			// Create an instance of the DocumentBuilderFactory
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			//Get the DocumentBuilder from the factory that we just got above.
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			// turn it into an in-memory object
			InputSource source = new InputSource(new StringReader(xmlString));
			doc = docBuilder.parse(source);	    
		}
		catch (SAXParseException exc) 
		{
			String message = exc.getMessage() != null ? exc.getMessage() : exc.toString();
			throw new Exception(message);
		}
		catch (Exception exc) 
		{
			String message = exc.getMessage() != null ? exc.getMessage() : exc.toString();
			throw new Exception(message);
		}

		Element element = doc.getDocumentElement();
		StringWriter writer = new StringWriter();
		try
		{
			TransformerFactory fac = TransformerFactory.newInstance();
			Transformer trans = fac.newTransformer();
			trans.setOutputProperty("indent","yes");//No I18N
			trans.setOutputProperty("encoding","UTF-8");//No I18N
			DOMSource domSource = new DOMSource(element);
			StreamResult streamResult = new StreamResult(writer);
			trans.transform(domSource, streamResult);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String rootTagName = element.getTagName();
		try
		{
			if(rootTagName.equals("Template"))
			{
				return new Template(writer.toString());
			}
			else if(rootTagName.equals("TemplateResult"))
			{
				return new TemplateResult(writer.toString());
			}
			else if(rootTagName.equals("OperationIdentifier"))
			{
				return new OperationIdentifier(element);
			}
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			throw new Exception(getString("Exception is thrown in getBaseElement method"));
		}
		return null;
	}
	
	/**
	 *This function returns with equivalent string found in AdventNetResourceBundle.
	 *@param key the string whose equivalent string is to be returned.
	 *@return String the equivalent string found in AdventNetResourceBundle.
	 */
	
	public static String getString(String key)
	{
		if ((englishToNative==null)||(key==null))
		{
			return key;
		}
		try
		{
			return englishToNative.getString(key);
		}
		catch(Exception ne)
		{
			return key;
		}                 
	}

}


