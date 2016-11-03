/*$Id: EmsInventoryAPI.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/

package com.adventnet.nms.provisioning.inventorydb.example;

//Java imports
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Element;

import java.rmi.RemoteException;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

//Nms imports
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.provisioning.inventorydb.InventoryAPI;
import com.adventnet.nms.provisioning.inventorydb.NetworkInventory;
import com.adventnet.nms.provisioning.inventorydb.ProvisioningVariant;
import com.adventnet.nms.provisioning.ProvisioningStorageException;
import com.adventnet.management.transaction.UserTransactionException;

public class EmsInventoryAPI implements InventoryAPI
{
	private final String AVAILABLE = "AVAILABLE";

	private final String NOTAVAILABLE = "NOTAVAILABLE";

	private final String ACTIVE = "ACTIVE";

	private final String INACTIVE = "INACTIVE";

	private final String RESERVED = "RESERVED";

	public EmsInventoryAPI()
	{
	}
	
	private Element getXMLElement(String xmlString, String rootTagName)throws ProvisioningStorageException
	{
		InputSource input = null;

		/** The XML Document **/
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
			System.err.println("Error while parsing XML file. RootTagName: "+rootTagName 
							   + ", line " + exc.getLineNumber ()
							   + ", uri " + exc.getSystemId ());
			exc.printStackTrace();
			String message = exc.getMessage() != null ? exc.getMessage() : exc.toString();
			throw new ProvisioningStorageException("Error while parsing XML file. RootTagName: "
											+rootTagName
											+System.getProperty("line.separator")+message);
		}
		catch (Exception exc) 
		{
			System.err.println(" Exception in parsing the XML file."); 
			exc.printStackTrace();
			String message = exc.getMessage() != null ? exc.getMessage() : exc.toString();
			throw new ProvisioningStorageException("Error while parsing XML file. RootTagName: "
											+rootTagName
											+System.getProperty("line.separator")+message);
		}

		Element root = doc.getDocumentElement();
		if ((root == null) || (rootTagName != null && !root.getTagName().equals(rootTagName))) 
		{
			System.err.println("Root element should be "+ rootTagName);
			throw new ProvisioningStorageException("Root element should be "+ rootTagName);
		}
		return root;
	}

	private String getFileAsString(String filename) throws ProvisioningStorageException
	{
		try
		{
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
			int length = bis.available();
			byte[] barr = new byte[length];
			int readBytes = 0;
			while( readBytes != length )
			{
				readBytes += bis.read(barr, readBytes, length-readBytes );
			}
			bis.close();
			return new String(barr);
		}
		catch(IOException exc)
		{
			System.err.println("Unable to read the file "+filename);
			exc.printStackTrace();
			throw new ProvisioningStorageException("Unable to read the file "+filename);
		}
	}

	private String[] getSwitchNames(String[] switchList)
	{
		int length = switchList.length;
		String[] switchNames = new String[length];
		for(int i = 0 ; i < length ; i++)
		{
			switchNames[i] = switchList[i].substring(0, switchList[i].indexOf(".xml"));
		}
		return switchNames;
	}

	private Properties getInventoryProperties(Element switchElement)throws ProvisioningStorageException
	{
		String schemaMapping = getFileAsString(PureUtils.rootDir+"examples/provisioning/EmsInventory/SchemaMapping.xml");
		Element mapElement = getXMLElement(schemaMapping, "SchemaMapping");
		NamedNodeMap attributes = mapElement.getAttributes();
		int length = attributes.getLength();
		Properties actualVsMatch = new Properties();
		for(int i = 0 ; i < length ; i++)
		{
			Node node = attributes.item(i);
			actualVsMatch.put(node.getNodeName(), node.getNodeValue());
		}
		
		Properties inventoryProp = new Properties();
		attributes = switchElement.getAttributes();
		int size = attributes.getLength();
		for(int i = 0 ; i < size ; i++)
		{
			Node node = attributes.item(i);
			String propName = node.getNodeName();
			String matchName = (String)actualVsMatch.get(propName);
			if(matchName == null)
			{
				continue;
			}
			if(propName.equals("managementState"))
			{
				String propValue = node.getNodeValue();
				if(propValue.trim().equals("managed"))
				{
					inventoryProp.put(matchName, AVAILABLE);
				}
				else
				{
					inventoryProp.put(matchName, NOTAVAILABLE);
				}
				continue;
			}
			if(propName.trim().equals("switchStatus"))
			{
				String propValue = node.getNodeValue();
				if(propValue.trim().equals("active"))
				{
					inventoryProp.put(matchName, ACTIVE);
				}
				else if(propValue.trim().equals("inactive"))
				{
					inventoryProp.put(matchName, INACTIVE);
				}
				else
				{
					inventoryProp.put(matchName, RESERVED);
				}
				continue;
			}
			inventoryProp.put(matchName, node.getNodeValue());
		}
		return inventoryProp;
	}

	public NetworkInventory getInventory(String name)throws RemoteException, ProvisioningStorageException, UserTransactionException
	{
		String fileName = null;
		String dbDirectoryName = PureUtils.rootDir+"examples/provisioning/EmsInventory/EmsDB/";
		File dbFile = new File(dbDirectoryName);
		String[] switchList = dbFile.list();
		String[] switchNames = getSwitchNames(switchList);
		int length = switchNames.length;
		for(int i = 0 ; i < length ; i++)
		{
			if(name.equals(switchNames[i]))
			{
				fileName = dbDirectoryName+switchNames[i]+".xml";
				break;
			}
		}
		if(fileName == null)
		{
			throw new ProvisioningStorageException("No NetworkInventory found with the name "+name);
		}
		String xmlString = getFileAsString(fileName);
		Element switchElement = getXMLElement(xmlString, "Switch");
		NetworkInventory networkInventory = new NetworkInventory();
		Properties inventoryProp = getInventoryProperties(switchElement);
		networkInventory.setName((String)inventoryProp.remove("name"));
		networkInventory.setClassname("NetworkInventory");
		networkInventory.setType("NetworkInventory");
		networkInventory.setProperties(inventoryProp);
		return networkInventory;
	}

	public void addInventory(NetworkInventory networkInventory) throws RemoteException, ProvisioningStorageException, UserTransactionException
	{}

	public void updateInventory(NetworkInventory networkInventory) throws RemoteException,
	ProvisioningStorageException, UserTransactionException
	{}

	public void deleteInventory(String name) throws RemoteException, ProvisioningStorageException, UserTransactionException
	{}

	public void addVariant(ProvisioningVariant variant) throws RemoteException, ProvisioningStorageException, UserTransactionException
	{}

	public ProvisioningVariant getVariant(String variantId) throws RemoteException, ProvisioningStorageException, UserTransactionException
	{
		return null;
	}

	public void updateVariant(ProvisioningVariant variant) throws RemoteException, ProvisioningStorageException, UserTransactionException
	{}

	public void deleteVariant(String variantId) throws RemoteException, ProvisioningStorageException, UserTransactionException
	{}

	public NetworkInventory getInventory(String emsName, String name) throws RemoteException, ProvisioningStorageException
	{
		return null;
	}

	public Vector getInventoryNamesWithProps(Properties prop) throws RemoteException, ProvisioningStorageException, UserTransactionException
	{
		return null;	
	}

	public Vector getCompleteInventoryList()throws RemoteException, ProvisioningStorageException, UserTransactionException
	{
		return null;
	}

	public Vector getCompleteVariantList()throws RemoteException, ProvisioningStorageException, UserTransactionException
	{
		return null;
	}
}

