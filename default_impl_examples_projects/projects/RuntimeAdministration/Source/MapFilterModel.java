//$Id: MapFilterModel.java,v 1.1 2006/08/29 13:57:02 build Exp $
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


public class MapFilterModel implements ResultEventListener  
{

	MapConfiguartionSession mapConfiguartionSession = null;
	MapFilterUI mapFilterUI = null;
	String comment = "No comments";

	public MapFilterModel(MapFilterUI discoveryFilterUi )
	{
		mapFilterUI = discoveryFilterUi ;
		mapConfiguartionSession =  MapConfiguartionSession.getInstance(); 
		mapConfiguartionSession.register(this); 
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
			outp.writeInt(GenericConstants.MAP_FILTERS);
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
			MapConfiguartionSession.getInstance().send(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("MapConfiguartionSession : Exception in sendRequest") + "  " + e);
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
			outp.writeInt(GenericConstants.MAP_FILTERS);
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
			return MapConfiguartionSession.getInstance().syncSend(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("MapConfiguartionSession : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
		return null;
	}


	public void updateTheMapFilterUI(XMLNode rootFilterNode)
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
					//System.err.println("mohantest : comment "+comment);	
				}
			}
		}
		else
		{
	//		System.err.println("Error: List Empty ");	
		}
		referenceVector = (Vector)vect.clone();
		mapFilterUI.updateTheUI(vect); 
	}

	public void updateTheUI()
	{
	}

	public void modifyFilterNode()
	{
		//Not yet implemented
		//send(GenericConstants.MODIFY,new XMLNode()); 
	}

	public void getFilterNodes()
	{
		send(GenericConstants.GET ); 
	}

//mohan

	Vector referenceVector = null;
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
		deleteMapFilterNodes(deleteVect);
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
		addMapFilterNodes( addVect);
	}


	public void addMapFilterNodes( Vector  resultVector)
	{
		////System.out.println("add resultVector"+resultVector);
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeName("FILTER_LIST");
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

	public void deleteMapFilterNodes( Vector  resultVector)
	{
		//System.out.println("resultVector delete "+resultVector);
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeName("FILTER_LIST");
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


	public void setFilterNodes( Vector  resultVector)
	{
		 setFilterNodes(resultVector , true);
	}

	public void setFilterNodes( Vector  resultVector , boolean set)
	{
		//System.out.println("resultVector"+resultVector);
		deleteOldNodes(resultVector);
		addNewNodes(resultVector);
		if(set)
		{
			XMLNode rootNode = new XMLNode();
			rootNode.setNodeName("FILTER_LIST");
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
//mohan
	public void setFilterNodes( String[] str)
	{
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeName("FILTER_LIST");
		rootNode.setNodeType(XMLNode.ELEMENT);
		//for loop
		for(int i=0;i<str.length;i++)
		{
			//	//System.out.println("SSSSSSSSSSSSSSSSSSSSSSSS"+str[i]);
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

	public void reloadFilterNode( String[] str)
	{
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeName("FILTER_LIST");
		rootNode.setNodeType(XMLNode.ELEMENT);
		//for loop
		for(int i=0;i<str.length;i++)
		{
	//		//System.out.println("SSSSSSSSSSSSSSSSSSSSSSSS"+str[i]);
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
			if(type == GenericConstants.MAP_FILTERS)
			{
				int operationIndex = inp.readInt();
				//Return the value to the UI
				switch(operationIndex )
				{
					case GenericConstants.ADD:
						{
							mapFilterUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.DELETE:
						{
							mapFilterUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.MODIFY:
						{
							mapFilterUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.RELOAD:
						{
							mapFilterUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.GET:
						{
							int datalength = inp.readInt();
							byte xmlarr[] = new byte[datalength];
							inp.readFully(xmlarr);
							XMLNode rootLogNode = (XMLNode)NmsClientUtil.deSerializeObject(xmlarr);
							updateTheMapFilterUI(rootLogNode); 
							break;    
						}
					case GenericConstants.SET:
						{
							break;    
						}
					default:
						{
	//						System.err.println(" Unknown command received in MapConfiguartionSession : MapConfiguartionSession ");
							break;
						}

				}
			}
		}
		catch(Exception e)
		{
			System.err.println(NmsClientUtil.GetString("MapConfiguartionSession : Exception in callBack ") + "  " + e);
			e.printStackTrace();
		}
	}
	public String getModuleKey()
	{
		return MapConfiguartionSession.getInstance().getModuleId()+"_MapFilter";
	}

}
