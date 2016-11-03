//$Id: LoggingConfiguartionModel.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.nms.runtimeconfig;


//import com.adventnet.nms.util.ResultEventListener;
//import com.adventnet.nms.util.ResultEventAdapter;
//import com.adventnet.nms.util.ResultEventObject;
//import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.startnms.GenericConstants;
import java.util.Vector;
import java.util.Hashtable;
import java.util.*;


public class LoggingConfiguartionModel 
{

	LoggingConfiguartionSession loggingConfiguartionSession = null;
	LoggingMainUI loggingmainui = null;
	String moduleId = "LOG_CLIENT";

	public LoggingConfiguartionModel(LoggingMainUI loggingUi)
	{
		loggingmainui = loggingUi;
		loggingConfiguartionSession  = new LoggingConfiguartionSession(this);
	}

    public void showDummyPanel()
    {
        loggingmainui.showDummyPanel();
    }
    
	String comment = "No Comment";
	public void updateTheUI(XMLNode rootLogNode)
	{
		Hashtable mainHash = null;
		Vector childNodes = rootLogNode.getChildNodes();
		if(childNodes != null)
		{
			mainHash = new Hashtable();
			for(int i = 0; i<childNodes.size();i++)
			{
				XMLNode childNd = (XMLNode)childNodes.elementAt(i);
				if(childNd.getNodeType() == XMLNode.ELEMENT)
				{
					Hashtable temp = childNd.getAttributeList();
					Vector keyNodes = childNd.getChildNodes();
					if(keyNodes != null)
					{
						//Vector vec = new Vector();
						Hashtable ht = new Hashtable(); 
						for(int j = 0; j<keyNodes.size();j++)
						{
							XMLNode keyNd = (XMLNode)keyNodes.elementAt(j);
							//vec.addElement(keyNd.getAttributeList());
							ht.put(keyNd.getAttributeList().get("Name"),keyNd.getAttributeList());
						}
						temp.put("keyNodeVect",ht);
					}
					if(temp != null)
						mainHash.put(temp.get("FileName") , temp);
				}
				else if(childNd.getNodeType() == XMLNode.ELEMENT)
				{
					if(childNd.getNodeValue()!=null)
						comment = childNd.getNodeValue();
					//System.err.println("mohantest : comment "+comment);	
				}


			}
		}
		else
		{
			System.err.println("Error: Logging Empty ");	
		}
		 loggingmainui.updateTheUI(mainHash);               
		//return mainHash;
	}

	public void updateTheUI()
	{
		//System.out.println("Update the UI of model called");
		loggingmainui.updateTheUI();
	}

	public void addLogNode(Hashtable ht1)
	{
		Hashtable mainhash =(Hashtable) ht1.clone();
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeType( XMLNode.ELEMENT );
		rootNode.setNodeName( "LOGGING_PARAMETERS_CONF" );
		for(Enumeration en = mainhash.elements();en.hasMoreElements();)
		{
			Hashtable tempHash = (Hashtable)en.nextElement();   
			Hashtable ht = (Hashtable)tempHash.clone();   			
			if(ht != null)
			{
				XMLNode xmlNode = new XMLNode();
				xmlNode.setNodeType( XMLNode.ELEMENT );
				String nodeName = "LOG_USER";
				if("stderr.txt".equals(ht.get("FileName")))
				{
					nodeName ="SYS_ERR";
				}
				else if("stdout.txt".equals(ht.get("FileName")))
				{
					nodeName ="SYS_OUT";
				}
				xmlNode.setNodeName( nodeName );
				Hashtable keyMainHash = (Hashtable)ht.remove("keyNodeVect");
				xmlNode.setAttributeList( ht );  
				if(keyMainHash != null)
				{

					for(Enumeration en1 = keyMainHash.elements();en1.hasMoreElements();)
					{

						Hashtable keyHash = (Hashtable)en1.nextElement();
						if(keyHash != null)
						{
							XMLNode keyNode = new XMLNode();
							keyNode.setNodeType( XMLNode.ELEMENT );
							keyNode.setNodeName( "KEY" );
							keyNode.setAttributeList(keyHash);
							xmlNode.addChildNode(keyNode);
						}
					}
				}
				rootNode.addChildNode(xmlNode);
			}
		}
		loggingConfiguartionSession.send(GenericConstants.ADD , rootNode ); 
	}

	public void deleteLogNode()
	{
		//Not yet implemented
		loggingConfiguartionSession.send(GenericConstants.DELETE, new XMLNode()); 
	}
	
	public void modifyLogNode(Hashtable ht1)
	{
		Hashtable mainHash =(Hashtable) ht1.clone();
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeType( XMLNode.ELEMENT );
		rootNode.setNodeName( "LOGGING_PARAMETERS_CONF" );

		for(Enumeration en = mainHash.elements();en.hasMoreElements();)
		{
			Hashtable tempHash = (Hashtable)en.nextElement();   
			Hashtable ht = (Hashtable)tempHash.clone();   			
			if(ht != null)
			{
				XMLNode xmlNode = new XMLNode();
				xmlNode.setNodeType( XMLNode.ELEMENT );
				String nodeName = "LOG_USER";
				if("stderr.txt".equals(ht.get("FileName")))
				{
					nodeName ="SYS_ERR";
				}
				else if("stdout.txt".equals(ht.get("FileName")))
				{
					nodeName ="SYS_OUT";
				}
				xmlNode.setNodeName( nodeName );
				Hashtable keyMainHash = (Hashtable)ht.remove("keyNodeVect");
				xmlNode.setAttributeList( ht );  
				for(Enumeration en1 = keyMainHash.elements();en1.hasMoreElements();)
				{
					Hashtable keyHash = (Hashtable)en1.nextElement();
					if(keyHash != null)
					{
						XMLNode keyNode = new XMLNode();
						keyNode.setNodeType( XMLNode.ELEMENT );
						keyNode.setNodeName( "KEY" );
						keyNode.setAttributeList(keyHash);
						xmlNode.addChildNode(keyNode);
					}
				}
				 rootNode.addChildNode(xmlNode);
			}
		}
		loggingConfiguartionSession.send(GenericConstants.MODIFY, rootNode); 
	}

	public void getLogNodes()
	{
		loggingConfiguartionSession.send(GenericConstants.GET); 
	}

	public void setLogNodes(Hashtable mainHash)
	{
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeType( XMLNode.ELEMENT );
		rootNode.setNodeName( "LOGGING_PARAMETERS_CONF" );

		for(Enumeration en = mainHash.elements();en.hasMoreElements();)
		{
			Hashtable ht =(Hashtable) en.nextElement();
			if(ht != null)
			{
				XMLNode xmlNode = new XMLNode();
				xmlNode.setNodeType( XMLNode.ELEMENT );
				String nodeName = "LOG_USER";
				if("stderr.txt".equals(ht.get("FileName")))
				{
					nodeName ="SYS_ERR";
				}
				else if("stdout.txt".equals(ht.get("FileName")))
				{
					nodeName ="SYS_OUT";
				}
				xmlNode.setNodeName( nodeName );
				Hashtable keyMainHash = (Hashtable)ht.remove("keyNodeVect");
				xmlNode.setAttributeList( ht );  
				for(Enumeration en1 = keyMainHash.elements();en.hasMoreElements();)
				{
					Hashtable keyHash = (Hashtable)en.nextElement();

					if(keyHash != null)
					{
						XMLNode keyNode = new XMLNode();
						keyNode.setNodeType( XMLNode.ELEMENT );
						keyNode.setNodeName( "KEY" );
						keyNode.setAttributeList(keyHash);
						xmlNode.addChildNode(keyNode);
					}
				}
				rootNode.addChildNode(xmlNode);
			}
		}
		loggingConfiguartionSession.send(GenericConstants.SET , rootNode ); 
	}

	public void reloadLogNode()
	{
		loggingConfiguartionSession.send(GenericConstants.RELOAD , new XMLNode() ); 
	}

	public String getModuleId()
	{
		return moduleId;
	}

}



