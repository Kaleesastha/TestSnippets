//$Id: DiscoveryFilterModel.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.nms.runtimeconfig;


//import com.adventnet.nms.util.ResultEventListener;
//import com.adventnet.nms.util.ResultEventAdapter;
//import com.adventnet.nms.util.ResultEventObject;
//import com.adventnet.nms.util.NmsClientUtil;
//import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.startnms.GenericConstants;



import com.adventnet.nms.util.*;
import java.io.*;
import java.util.Vector;
import java.util.Hashtable;


public class DiscoveryFilterModel implements ResultEventListener  
{

	ListConfiguartionSession listConfiguartionSession = null;
	DiscoveryFilterUI discoveryFilterUI = null;
	Hashtable mainHash = null;
	Vector referenceVector = null;

	public DiscoveryFilterModel(DiscoveryFilterUI discoveryFilterUi )
	{
		discoveryFilterUI = discoveryFilterUi ;
		listConfiguartionSession =  ListConfiguartionSession.getInstance(); 
		listConfiguartionSession.register(this); 
	}


	public void send( int operation )
	{
		send( operation , null);
	}

	public void send( int operation , XMLNode modifyNode )
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream(byteStream);
			outp.writeInt(GenericConstants.DISC_FILTERS);
			outp.writeInt(operation);
			if(modifyNode != null)
			{
				byte[] serverData = NmsClientUtil.serializeObject(modifyNode );
				int len = serverData.length;
				outp.writeInt(len);
				outp.write(serverData,0,len);  
			}
			outp.flush();
			byte[] bytes = byteStream.toByteArray();
			outp.close();
			byteStream.reset();
			byteStream.close(); 
			ListConfiguartionSession.getInstance().send(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("ListConfiguartionSession : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
	}

	//public XMLNode syncSend( int operation , XMLNode modifyNode )
	public byte[] syncSend( int operation , XMLNode modifyNode )
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream(byteStream);
			outp.writeInt(GenericConstants.DISC_FILTERS);
			outp.writeInt(operation);
			if(modifyNode != null)
			{
				byte[] serverData = NmsClientUtil.serializeObject(modifyNode);
				int len = serverData.length;
				outp.writeInt(len);
				outp.write(serverData,0,len);  
			}
			outp.flush();
			byte[] bytes = byteStream.toByteArray();
			outp.close();
			byteStream.reset();
			byteStream.close(); 
			return ListConfiguartionSession.getInstance().syncSend(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("ListConfiguartionSession : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
		return null;
	}


	String comment = "No comments";
	public void updateTheDiscoveryFilterUI(XMLNode rootFilterNode)
	{
		if(rootFilterNode == null) return;
		Vector childNodes = rootFilterNode.getChildNodes();
		Vector  vect = new Vector();
		if(childNodes != null)
		{
			for(int i = 0; i<childNodes.size();i++)
			{
				XMLNode childNd = (XMLNode)childNodes.elementAt(i);
				if(childNd.getNodeType() == XMLNode.ELEMENT)
				{
					Hashtable temp = childNd.getAttributeList();
					vect.addElement(temp.get("className"));
				}
				else if(childNd.getNodeType() == XMLNode.ELEMENT)
				{
					if(childNd.getNodeValue()!=null)
						comment = childNd.getNodeValue();
				}
			}
		}
		else
		{
			//		System.err.println("Error: List Empty ");	
		}
		referenceVector = (Vector)vect.clone();
		discoveryFilterUI.updateTheUI(vect); 
	}

	private void deleteOldNodes(Vector resultVector)
	{
		Vector deleteVect = new Vector();
		for(int i=0;i<referenceVector.size();i++)
		{
			String tempStr = (String)referenceVector.elementAt(i);
			if(!resultVector.contains(tempStr))
			{
				deleteVect.addElement(tempStr);
			}
		}
		deleteDiscoveryFilterNodes(deleteVect);
	}

	private void addNewNodes(Vector resultVector)
	{
		Vector addVect = new Vector();
		for(int i=0;i<resultVector.size();i++)
		{
			String tempStr = (String)resultVector.elementAt(i);
			if(!referenceVector.contains(tempStr))
			{
				addVect.addElement(tempStr);
			}
		}
		addDiscoveryFilterNodes( addVect);
	}


	public void addDiscoveryFilterNodes( Vector  resultVector)
	{
		//System.out.println("add resultVector"+resultVector);
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeName("DISCOVERY_FILTER");
		rootNode.setNodeType(XMLNode.ELEMENT);
		//for loop
		for(int i=0;i<resultVector.size();i++)
		{
			String str = (String)resultVector.elementAt(i);
			if( str!= null)
			{
				XMLNode childNode = new XMLNode();
				childNode.setNodeName("FILTER");
				childNode.setNodeType(XMLNode.ELEMENT);
				Hashtable ht = new Hashtable();
				ht.put("className" , str);
				childNode.setAttributeList(ht);
				rootNode.addChildNode(childNode);
			}
		}
		send(GenericConstants.ADD ,rootNode ); 
	}

	public void deleteDiscoveryFilterNodes( Vector  resultVector)
	{
		//System.out.println("resultVector delete "+resultVector);
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeName("DISCOVERY_FILTER");
		rootNode.setNodeType(XMLNode.ELEMENT);
		//for loop
		for(int i=0;i<resultVector.size();i++)
		{
			String str = (String)resultVector.elementAt(i);
			if( str!= null)
			{
				XMLNode childNode = new XMLNode();
				childNode.setNodeName("FILTER");
				childNode.setNodeType(XMLNode.ELEMENT);
				Hashtable ht = new Hashtable();
				ht.put("className" , str);
				childNode.setAttributeList(ht);
				rootNode.addChildNode(childNode);
			}
		}
		send(GenericConstants.DELETE ,rootNode ); 
	}


	public void setDiscoveryFilterNodes( Vector  resultVector)
	{
		 setDiscoveryFilterNodes(resultVector , true);
	}

	public void setDiscoveryFilterNodes( Vector  resultVector , boolean set)
	{
		//System.out.println("resultVector"+resultVector);
		deleteOldNodes(resultVector);
		addNewNodes(resultVector);
		if(set)
		{
			XMLNode rootNode = new XMLNode();
			rootNode.setNodeName("DISCOVERY_FILTER");
			rootNode.setNodeType(XMLNode.ELEMENT);
			//for loop
			for(int i=0;i<resultVector.size();i++)
			{
				String str = (String)resultVector.elementAt(i);
				if( str!= null)
				{
					XMLNode childNode = new XMLNode();
					childNode.setNodeName("FILTER");
					childNode.setNodeType(XMLNode.ELEMENT);
					Hashtable ht = new Hashtable();
					ht.put("className" , str);
					childNode.setAttributeList(ht);
					rootNode.addChildNode(childNode);
				}
			}
			send(GenericConstants.SET ,rootNode ); 
		}
		referenceVector = (Vector)resultVector.clone();
	}


	public void setDiscoveryFilterNodes( String[] str)
	{
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeName("DISCOVERY_FILTER");
		rootNode.setNodeType(XMLNode.ELEMENT);
		//for loop
		for(int i=0;i<str.length;i++)
		{
	//		System.out.println("str"+str[i]);
			if(str[i] != null)
			{
				XMLNode childNode = new XMLNode();
				childNode.setNodeName("FILTER");
				childNode.setNodeType(XMLNode.ELEMENT);
				Hashtable ht = new Hashtable();
				ht.put("className" , str[i]);
				childNode.setAttributeList(ht);
				rootNode.addChildNode(childNode);
			}
		}
		send(GenericConstants.SET ,rootNode ); 
	}

	public void getDiscoveryFilterNodes()
	{
		send(GenericConstants.GET ); 
	}

	public void reloadDiscoveryFilterNode( String[] str)
	{
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeName("DISCOVERY_FILTER");
		rootNode.setNodeType(XMLNode.ELEMENT);
		//for loop
		for(int i=0;i<str.length;i++)
		{
	//		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSS"+str[i]);
			if(str[i] != null)
			{
				XMLNode childNode = new XMLNode();
				childNode.setNodeName("FILTER");
				childNode.setNodeType(XMLNode.ELEMENT);
				Hashtable ht = new Hashtable();
				ht.put("className" , str[i]);
				childNode.setAttributeList(ht);
				rootNode.addChildNode(childNode);
			}
		}
		send(GenericConstants.RELOAD , rootNode ); 
	}

	//Response From Server Comes Here
	public void callBack(ResultEventObject evt)
	{
		DataInputStream inp = null;
		ByteArrayInputStream bais = evt.getResultEventData();
		try
		{
			inp = new DataInputStream(bais);
			int type = evt.getEventType();
			if(type == GenericConstants.DISC_FILTERS)
			{
				int operationIndex = inp.readInt();
				//Return the value to the UI
				switch(operationIndex )
				{
					case GenericConstants.ADD:
						{
							discoveryFilterUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.DELETE:
						{
							discoveryFilterUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.MODIFY:
						{
							discoveryFilterUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.RELOAD:
						{
							discoveryFilterUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.GET:
						{
							int datalength = inp.readInt();
							byte xmlarr[] = new byte[datalength];
							inp.readFully(xmlarr);
							XMLNode rootLogNode = (XMLNode)NmsClientUtil.deSerializeObject(xmlarr);
							updateTheDiscoveryFilterUI(rootLogNode); 
							break;    
						}

					default:
						{
	//						System.err.println(" Unknown command received in ListConfiguartionSession : ListConfiguartionSession ");
							break;
						}

				}
			}
		}
		catch(Exception e)
		{
			System.err.println(NmsClientUtil.GetString("ListConfiguartionSession : Exception in callBack ") + "  " + e);
			e.printStackTrace();
		}
	}
	public String getModuleKey()
	{
		return ListConfiguartionSession.getInstance().getModuleId()+"_DiscFilter";
	}

}
