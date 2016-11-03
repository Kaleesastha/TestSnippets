//$Id: TrapFilterModel.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.nms.runtimeconfig;


//import com.adventnet.nms.util.ResultEventListener;
//import com.adventnet.nms.util.ResultEventAdapter;
//import com.adventnet.nms.util.ResultEventObject;
//import com.adventnet.nms.util.NmsClientUtil;
//import com.adventnet.nms.util.XMLNode;
//import com.adventnet.nms.startnms.GenericConstants;



import com.adventnet.nms.util.*;
import java.io.*;
import java.util.Vector;
import java.util.Hashtable;
import com.adventnet.nms.startnms.GenericConstants;


public class TrapFilterModel implements ResultEventListener  
{

	EventConfiguartionSession eventConfiguartionSession = null;
	TrapFilterUI trapFilterUI = null;
	Hashtable mainHash = null;
	Vector filterIndex = null; //Issue ID 11384942

	public TrapFilterModel(TrapFilterUI trapFilterUi )
	{
		trapFilterUI = trapFilterUi ;
		eventConfiguartionSession =  EventConfiguartionSession.getInstance(); 
		eventConfiguartionSession.register(this); 
	}


	private void send( int operation )
	{
		byte[] b =null;
		send( operation , b);
	}

	private void send( int operation , XMLNode xmlNode )
	{
		byte[] serverData = NmsClientUtil.serializeObject(xmlNode);
	 	send(operation , serverData );
	}
	private void send( int operation , Vector modifyVect )
	{
		byte[] serverData = NmsClientUtil.serializeVector(modifyVect );
	 	send(operation , serverData );
	}
	private void send( int operation , byte[] serverData )
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream(byteStream);
			outp.writeInt(GenericConstants.TRAP_FILTERS);
			outp.writeInt(operation);
			if(serverData != null)
			{
				int len = serverData.length;
				outp.writeInt(len);
				outp.write(serverData,0,len);  
			}
			outp.flush();
			byte[] bytes = byteStream.toByteArray();
			outp.close();
			byteStream.reset();
			byteStream.close(); 
			EventConfiguartionSession.getInstance().send(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("EventConfiguartionSession : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
	}

	//public XMLNode syncSend( int operation , XMLNode modifyNode )
	public byte[] syncSend( int operation , Vector modifyVect)
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream(byteStream);
			outp.writeInt(GenericConstants.TRAP_FILTERS);
			outp.writeInt(operation);
			if(modifyVect!= null)
			{
				byte[] serverData = NmsClientUtil.serializeVector(modifyVect );
				int len = serverData.length;
				outp.writeInt(len);
				outp.write(serverData,0,len);  
			}
			outp.flush();
			byte[] bytes = byteStream.toByteArray();
			outp.close();
			byteStream.reset();
			byteStream.close(); 
			return EventConfiguartionSession.getInstance().syncSend(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("EventConfiguartionSession : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
		return null;
	}


	String comment = "No Comment";
	public void updateTheTrapFilterUI(XMLNode rootFilterNode)
	{
		if(rootFilterNode == null) return;
		Vector childNodes = rootFilterNode.getChildNodes();
		if(childNodes != null)
		{
			mainHash = new Hashtable();
			filterIndex = new Vector(); //Issue ID 11384942
			for(int i = 0; i<childNodes.size();i++)
			{
				XMLNode childNd = (XMLNode)childNodes.elementAt(i);
				if(childNd  != null)
				{
					if(childNd.getNodeType() == XMLNode.ELEMENT)
					{
					Hashtable temp = childNd.getAttributeList();
					if ((temp != null) &&( temp.get("name") != null))
					{
						mainHash.put(temp.get("name") , temp );
						filterIndex.add(temp.get("name")); //Issue ID 11384942
					}
					}else if(childNd.getNodeType() == XMLNode.ELEMENT)
					{
					if(childNd.getNodeValue()!=null)
						comment = childNd.getNodeValue();
					}
				}else
				{
	//				System.err.println("mohantest : Oops NULL .........");	
				}
			}
		}
		else
		{
	//		System.err.println("Error: List Empty ");	
		}
		//trapFilterUI.updateTheUI(mainHash);
		trapFilterUI.updateTheUI(mainHash,filterIndex); //Issue ID 11384942
	}
	public void updateTheUI()
	{
	}

	public void addFilterNode(Vector vect )
	{
		//Not yet implemented
		//send(GenericConstants.ADD , vect); 
	}

	public void deleteFilterNode()
	{
		//Not yet implemented
		//send(GenericConstants.DELETE,new Vector()); 
	}

	public void modifyFilterNode()
	{
		//Not yet implemented
		//send(GenericConstants.MODIFY,new Vector()); 
	}

	public void getFilterNodes()
	{
		send(GenericConstants.GET ); 
	}

	public void setFilterNodes( Hashtable [] ht)
	{
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeName("TRAP_FILTERS");
		rootNode.setNodeType(XMLNode.ELEMENT);
		//for loop
		for(int i=0;i<ht.length;i++)
		{
			if(ht[i] != null)
			{
				XMLNode childNode = new XMLNode();
				childNode.setNodeName("FILTER");
				childNode.setNodeType(XMLNode.ELEMENT);
				childNode.setAttributeList(ht[i]);
				rootNode.addChildNode(childNode);
			}
		}
		send(GenericConstants.SET ,rootNode ); 

		//XMLNode rootNode = new XMLNode();
		//rootNode.setNodeName();
		//rootNode.setNodeType();
		//	for(int i=0;i<ht.length;i++)
		//		send(GenericConstants.SET ,rootNode ); 
	}

	public void reloadFilterNode(Vector reloadVector)
	{
		send(GenericConstants.RELOAD , reloadVector ); 
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
			if(type == GenericConstants.TRAP_FILTERS)
			{
				int operationIndex = inp.readInt();
				//Return the value to the UI
				switch(operationIndex )
				{
						case GenericConstants.ADD:
							{

								int authID = inp.readInt();
								if(authID != GenericConstants.NO_AUTH)
								{
									trapFilterUI.updateTheUI();
								}else
								{
									String errMsg = inp.readUTF();
									trapFilterUI.authenticationFailed(errMsg);
								}
								break;
							}	
							case GenericConstants.SET:
							{

								int authID = inp.readInt();
								if(authID != GenericConstants.NO_AUTH)
								{
									trapFilterUI.updateTheUI();
								}else
								{
									String errMsg = inp.readUTF();
									trapFilterUI.authenticationFailed(errMsg);
								}
								break;
							}

						case GenericConstants.DELETE:
							{

								int authID = inp.readInt();
								if(authID != GenericConstants.NO_AUTH)
								{
									trapFilterUI.updateTheUI();
								}else
								{
									String errMsg = inp.readUTF();
									trapFilterUI.authenticationFailed(errMsg);
								}
								break;
							}
						case GenericConstants.MODIFY:
							{

								int authID = inp.readInt();
								if(authID != GenericConstants.NO_AUTH)
								{
									trapFilterUI.updateTheUI();
								}else
								{
									String errMsg = inp.readUTF();
									trapFilterUI.authenticationFailed(errMsg);
								}
								break;
							}
						case GenericConstants.RELOAD:
							{
								int authID = inp.readInt();
								if(authID != GenericConstants.NO_AUTH)
								{
									trapFilterUI.updateTheUI();
								}else
								{
									String errMsg = inp.readUTF();
									trapFilterUI.authenticationFailed(errMsg);
								}
								break;
							}
						case GenericConstants.GET:
							{
								int authID = inp.readInt();
								if(authID != GenericConstants.NO_AUTH)
								{
									int datalength = inp.readInt();
									byte xmlarr[] = new byte[datalength];
									inp.readFully(xmlarr);
									XMLNode rootLogNode = (XMLNode)NmsClientUtil.deSerializeObject(xmlarr);

									updateTheTrapFilterUI(rootLogNode );
								}else
								{
									String errMsg = inp.readUTF();
									trapFilterUI.authenticationFailed(errMsg);
								}
								break;
							}
						default:
							{

								//System.err.println(" Unknown command received in EventConfiguartionSession : EventConfiguartionSession ");
								break;
							}

					}
			}
		}
		catch(Exception e)
		{
			System.err.println(NmsClientUtil.GetString("TrapFilterModel : Exception in callBack ") + "  " + e);
			e.printStackTrace();
		}
	}
	public String getModuleKey()
	{
		return EventConfiguartionSession.getInstance().getModuleId()+"_DiscFilter";
	}

}
