//$Id: ListIconModel.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.nms.runtimeconfig;


//import com.adventnet.nms.util.ResultEventListener;
//import com.adventnet.nms.util.ResultEventAdapter;
//import com.adventnet.nms.util.ResultEventObject;
//import com.adventnet.nms.util.NmsClientUtil;
//import com.adventnet.nms.util.XMLNode;
//import com.adventnet.nms.startnms.GenericConstants;
import com.adventnet.nms.startnms.GenericConstants;



import com.adventnet.nms.util.*;
import java.io.*;
import java.util.Vector;
import java.util.Hashtable;


public class ListIconModel implements ResultEventListener  
{

	ListConfiguartionSession listConfiguartionSession = null;
	ListIconUI listIconUI = null;
	Hashtable mainHash = null;

	public ListIconModel(ListIconUI listicon)
	{
		listIconUI = listicon;
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
			outp.writeInt(GenericConstants.LIST_ICON);
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
			System.err.println(NmsClientUtil.GetString("ListIconModel : Exception in sendRequest") + "  " + e);
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
			outp.writeInt(GenericConstants.LIST_ICON);
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
			System.err.println(NmsClientUtil.GetString("ListIconModel : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
		return null;
	}


	public void getListIconNodes()
	{
		send(GenericConstants.GET ); 
	}

	String comment = "No Comment";
	public void updateTheListIconUI(XMLNode rootListIconNode)
	{
		if(rootListIconNode == null) return;
		Vector childNodes = rootListIconNode.getChildNodes();
		if(childNodes != null)
		{
			mainHash = new Hashtable();
			for(int i = 0; i<childNodes.size();i++)
			{
				XMLNode childNd = (XMLNode)childNodes.elementAt(i);
				if(childNd.getNodeType() == XMLNode.ELEMENT)
				{
				Hashtable temp = childNd.getAttributeList();
				mainHash.put(temp.get("TYPE") , temp);
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
	//		System.err.println("Error: List Empty ");	
		}
		listIconUI.updateTheUI(mainHash);
	}
	public void addListIconNode(Hashtable hash)
	{
		addListIconNode("DATA" ,hash);
	}

	public void addListIconNode(String nodeName , Hashtable hash)
	{
		send(GenericConstants.ADD , constructNode(nodeName , hash)); 
	}
	public void deleteListIconNode(String nodeName , Hashtable hash)
	{
		send(GenericConstants.DELETE,constructNode(nodeName , hash)); 
	}
	
	public void deleteListIconNode(Hashtable hash)
	{
		
		deleteListIconNode("DATA",hash);
	}
	public void modifyListIconNode(Hashtable hash)
	{
			modifyListIconNode("DATA",hash);
	}

	public void modifyListIconNode(String nodeName , Hashtable hash)
	{
		send(GenericConstants.MODIFY,constructNode(nodeName , hash)); 
	}

	public XMLNode constructNode(String nodeName , Hashtable hash)
	{
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeType( XMLNode.ELEMENT );
		rootNode.setNodeName( "LIST_ICON_DATA" );
		
		XMLNode xmlNode = new XMLNode();
		xmlNode.setNodeType( XMLNode.ELEMENT );
		xmlNode.setNodeName( nodeName );
		xmlNode.setAttributeList( hash );  
		rootNode.addChildNode(xmlNode);

		return rootNode;   
	}

	public void setListIconNodes()
	{
		//Not Applicable
		send(GenericConstants.SET , new XMLNode() ); 
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
			if(type == GenericConstants.LIST_ICON)
			{
				int operationIndex = inp.readInt();
				//Return the value to the UI
				switch(operationIndex )
				{
					case GenericConstants.ADD:
						{
							listIconUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.DELETE:
						{
							listIconUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.MODIFY:
						{
							listIconUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.RELOAD:
						{
							listIconUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.GET:
						{
							int datalength = inp.readInt();
							byte xmlarr[] = new byte[datalength];
							inp.readFully(xmlarr);
							XMLNode rootLogNode = (XMLNode)NmsClientUtil.deSerializeObject(xmlarr);
							updateTheListIconUI(rootLogNode); 
							break;    
						}
					case GenericConstants.SET:
						{
							int datalength = inp.readInt();
							byte xmlarr[] = new byte[datalength];
							inp.readFully(xmlarr);
							XMLNode rootLogNode = (XMLNode)NmsClientUtil.deSerializeObject(xmlarr);
							refreshClient(rootLogNode);
							break;    
						}

					default:
						{
							//System.err.println(" Unknown command received in ListIconModel ");
							break;
						}

				}
			}
		}
		catch(Exception e)
		{
			System.err.println(NmsClientUtil.GetString("ListIconModel : Exception in callBack ") + "  " + e);
			e.printStackTrace();
		}
	}


	public void refreshClient(XMLNode rootLogNode)
	{
		;
	}
	
	public String getModuleKey()
	{
		return ListConfiguartionSession.getInstance().getModuleId()+"_ListIcon";
	}

}


