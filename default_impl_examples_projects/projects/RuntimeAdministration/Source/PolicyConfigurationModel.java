//$Id: PolicyConfigurationModel.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.nms.runtimeconfig;


//import com.adventnet.nms.util.ResultEventListener;
//import com.adventnet.nms.util.ResultEventAdapter;
//import com.adventnet.nms.util.ResultEventObject;
//import com.adventnet.nms.util.NmsClientUtil;
//import com.adventnet.nms.util.GenericConstants;
import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.startnms.GenericConstants;
import java.util.Vector;
import java.util.Hashtable;


public class PolicyConfigurationModel 
{

	PolicyConfigurationSession policyConfiguartionSession = null;
	PolicyConfigurationUI policyConfigurationUI = null;
	String moduleId = "POLICY_CLIENT";
	Hashtable mainHash = null;

	public PolicyConfigurationModel(PolicyConfigurationUI policyConfigurationUi)
	{
		policyConfigurationUI = policyConfigurationUi;
		policyConfiguartionSession  = new PolicyConfigurationSession(this);
	}

	String comment = "No Comments";
	public void updateTheUI(XMLNode rootNode)
	{
		if(rootNode == null) return;
		Vector childNodes = rootNode.getChildNodes();
		if(childNodes != null)
		{
			mainHash = new Hashtable();
			for(int i = 0; i<childNodes.size();i++)
			{
				XMLNode childNd = (XMLNode)childNodes.elementAt(i);
				if(childNd != null)
				{
					if(childNd.getNodeType() == XMLNode.ELEMENT)
					{

						if("POLICY_COLOR".equals(childNd.getNodeName()))
						{
							//PENDING 
							//Atpresent only one color Node is present
							Vector vec = childNd.getChildNodes();
							if(vec != null)
							{
								//for(int i = 0; i<vec.size();i++)
								//{
								XMLNode colorNode = (XMLNode)vec.firstElement();
								Hashtable ht = colorNode.getAttributeList();
								mainHash.put("POLICY_COLOR" , ht);
								//}
							}
						}
						else
						{
							Vector vec = childNd.getChildNodes();
							if(vec != null)
							{
								for(int j = 0; j<vec.size();j++)
								{
									XMLNode policyNode = (XMLNode)vec.elementAt(j);
									Hashtable ht = policyNode.getAttributeList();
									if (ht !=null)
										mainHash.put(ht.get("classname") , ht);
								}
							}
						}

					}
				}
				else if(childNd.getNodeType() == XMLNode.COMMENT)
				{
					if(childNd.getNodeValue()!=null)
						comment = childNd.getNodeValue();
				}

			}

			policyConfigurationUI.updateThePolicyUI(mainHash) ; 
		}else
		{
			//		System.err.println("Error: Policy Empty ");	
		}
	}

	public void updateTheUI()
	{
		policyConfigurationUI.updateTheUI();	
	}


	public void getFilterNodes()
	{
		policyConfiguartionSession.send(GenericConstants.GET); 
	}

	public void setFilterNodes( Hashtable [] ht , Hashtable colorHash)
	{

		XMLNode rootNode = new XMLNode();
		rootNode.setNodeName("POLICY_CONF");
		rootNode.setNodeType(XMLNode.ELEMENT);
		//for loop
		XMLNode policyNode = new XMLNode();
		policyNode.setNodeName("POLICY_LIST");
		policyNode.setNodeType(XMLNode.ELEMENT);
		for(int i=0;i<ht.length;i++)
		{
	//		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSS"+ht[i]);
			if(ht[i] != null)
			{
				XMLNode childNode = new XMLNode();
				childNode.setNodeName("POLICY");
				childNode.setNodeType(XMLNode.ELEMENT);
				childNode.setAttributeList(ht[i]);
				policyNode.addChildNode(childNode);
			}
		}
		rootNode.addChildNode(policyNode);

		XMLNode colorNode = new XMLNode();
		colorNode.setNodeName("POLICY_COLOR");
		colorNode.setNodeType(XMLNode.ELEMENT);

		XMLNode childNode = new XMLNode();
		childNode.setNodeName("COLOR");
		childNode.setNodeType(XMLNode.ELEMENT);
		childNode.setAttributeList((Hashtable)colorHash.get("POLICY_COLOR"));
		//childNode.setAttributeList(colorHash);
		colorNode.addChildNode(childNode);

		rootNode.addChildNode(colorNode);

		policyConfiguartionSession.send(GenericConstants.SET ,rootNode ); 

	}

	public String getModuleId()
	{
		return moduleId;
	}

}
