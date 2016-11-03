package com.adventnet.nms.provisioning.server;

//Java imports
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;
import java.io.File;
import java.io.FileNotFoundException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;

//Nms imports
import com.adventnet.nms.provisioning.server.ProvisioningTrigger;
import com.adventnet.nms.provisioning.ProvisioningAPI;
import com.adventnet.nms.provisioning.TriggerException;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.XMLDataReader;
import com.adventnet.nms.util.XMLNode;

/**
 * This class provides a default implementation for handling Jms triggers.
 * This class implements onMessage method of the MessageListener to call the 
 * trigger method of the ProvisioningTrigger(super class). This class needs criteria for
 * filtering the incoming JmsMessages. This is obtained from the value of triggerCriteria attribute in the Template 
 * that initiates this trigger. Its value should be a relative file 
 * from &lt;Product Home&gt; directory.This file should contain an XML tag in 
 * following format.<br> 
 * &lt;?xml version="1.0" encoding="ISO-8859-1"?&gt; 
 *
 * &lt;?CRITERIA  filter="&lt;userValue1&gt;" /&gt;
 * 
 */

public class JMSTrigger extends ProvisioningTrigger implements MessageListener
{

	QueueConnection conn = null;
	public JMSTrigger()
	{
	}

	/**
	 * Registers with the JmsProvider with the specified Queue.
	 */
	public void register() throws TriggerException
	{
		String triggerCriteria=getTemplate().getAttribute("triggerCriteria");
		if(triggerCriteria ==null && triggerCriteria.length() ==0)
			throw new TriggerException("No triggerCriteria atribute specified");
		Hashtable criteriaHashtable = null;
		try
		{
			XMLDataReader xdr= new XMLDataReader(PureUtils.rootDir+triggerCriteria,false);
			XMLNode xml= xdr.getRootNode();
			if (!(xml.getNodeName().equals("CRITERIA")))
			{
				throw new TriggerException("Not a valid criteria tag");
			}
			criteriaHashtable=xml.getAttributeList();
		}
		catch (Exception e)
		{
			String errorMessage = e.getMessage() != null ? e.getMessage() : e.toString();
			throw new TriggerException("Failed while reading the criteria file "+errorMessage);
		}
		try
		{
			Hashtable env = parseJmsConfigurationFile();
			String requestQueueName= (String)env.remove("requestQueue");
			String queueConnectionFactoryName = (String)env.remove("QueueConnectionFactory");
			if(requestQueueName == null)  
			{
				requestQueueName = "requestQueue";
			}
			if(queueConnectionFactoryName == null)
			{
				queueConnectionFactoryName = "QueueConnectionFactory"; 
			}
			// Initiates the context and gets the connection
			Context cont = new InitialContext(env);
			QueueConnectionFactory q = (QueueConnectionFactory) cont.lookup(queueConnectionFactoryName);
			conn = q.createQueueConnection();

			// looks up the JmsDestination.
			Queue requestQueue  = (Queue) cont.lookup(requestQueueName);

			//creates the session and receiver for receiving the message.
			QueueSession receiverSession = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			//Assigns the filter for Receiver. This Listener receives only those messages 
			//that satisfy the filter condition.
			String filter = (String) criteriaHashtable.get("filter");

			QueueReceiver receiver = receiverSession.createReceiver(requestQueue, filter);
			receiver.setMessageListener(this);

			//starts the connection. It will deliver the message from the Destination to the corresponding Listener.
			conn.start();

		}
		catch(Exception e)
		{
			String errorMessage = e.getMessage() != null ? e.getMessage() : e.toString();
			e.printStackTrace();
			throw new TriggerException(errorMessage);
		}
	}


	public void onMessage(Message message)
	{
		Thread t = new Thread(new Runnable(){
			public void run()
			{
				trigger();
			}
		});
		t.start();
	}

	private void trigger()
	{
		try
		{
			super.trigger(null);
		}
		catch(TriggerException e)
		{
			e.printStackTrace();
		}
	}

	//Parses the JmsConfigurationFile and collects the Provider specific information and puts into the Hashtable.
	private Hashtable parseJmsConfigurationFile() throws Exception
	{
		File file = new File(PureUtils.rootDir, "conf/JmsConfiguration.xml");
		if(!file.exists())
			throw new FileNotFoundException("JmsConfiguration.xml is not found");
		Hashtable env = new Hashtable();

		//Parses the JmsConfiguration.xml file.
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(file);
		Element root = doc.getDocumentElement();

		//Gets the provider information from the xml file.
		String contextFactory = root.getAttribute("CONTEXT_FACTORY");
		String provider_Url = root.getAttribute("PROVIDER_URL");
		String requestQueueName = root.getAttribute("requestQueue");
		String queueConnectionFactoryName = root.getAttribute("QueueConnectionFactory");

		if(contextFactory != null && contextFactory.trim().length() != 0)
			env.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
		if(provider_Url != null && provider_Url.trim().length() != 0)
			env.put(Context.PROVIDER_URL, provider_Url);
		if(requestQueueName != null && requestQueueName.trim().length() != 0) 
			env.put("requestQueue", requestQueueName);
		if(queueConnectionFactoryName != null && queueConnectionFactoryName.trim().length() != 0)
			env.put("QueueConnectionFactory", queueConnectionFactoryName);

		//Gets the childNodes
		NodeList list = root.getChildNodes();
		int length = list.getLength();
		for(int i=0; i<length; i++)
		{
			Node node = list.item(i);
			if(!(node instanceof Element))
				continue;
			Element childElement = (Element)node;
			String prop = childElement.getAttribute("property");
			String value = childElement.getAttribute("value");
			if(prop != null && prop.trim().length() != 0 && value != null && value.trim().length() != 0)
			{
				env.put(prop,value);
			}
		}
		return env;
	}

	public void deregister() throws TriggerException
	{
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
			String errorMessage = e.getMessage() != null ? e.getMessage() : e.toString();
			throw new TriggerException("Trigger cannot be deregistered "+errorMessage);
		}
	}
}




