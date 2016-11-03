//$Id: MapIconModel.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.nms.runtimeconfig;


//import com.adventnet.nms.util.ResultEventListener;
//import com.adventnet.nms.util.ResultEventAdapter;
//import com.adventnet.nms.util.ResultEventObject;
//import com.adventnet.nms.util.NmsClientUtil;
//import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.startnms.GenericConstants;



import com.adventnet.nms.util.*;
import java.io.*;
import java.util.*;


public class MapIconModel implements ResultEventListener  
{

	MapConfiguartionSession mapConfiguartionSession = null;
	MapIconUI mapIconUI = null;
	Hashtable mainHash = null;

	public MapIconModel(MapIconUI mapIconUi)
	{
		mapIconUI = mapIconUi;
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
			outp.writeInt(GenericConstants.MAP_ICON);
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
			System.err.println(NmsClientUtil.GetString("MapIconModel : Exception in sendRequest") + "  " + e);
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
			outp.writeInt(GenericConstants.MAP_ICON);
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
			System.err.println(NmsClientUtil.GetString("MapIconModel : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
		return null;
	}


	String comment = "No Comments";
	public void updateTheMapIconUI(XMLNode xmlnode)
	{
	        if(xmlnode == null)
            return;
        Vector vector = xmlnode.getChildNodes();
        if(vector != null)
        {
            mainHash = new Hashtable();
            for(int i = 0; i < vector.size(); i++)
			{
				XMLNode childNd = (XMLNode)vector.elementAt(i);
				if(childNd.getNodeType() == XMLNode.ELEMENT)
				{
					Hashtable hashtable = childNd.getAttributeList();
					if("OBJTYPES".equals(childNd.getNodeName()))
					{
						mainHash.put("OBJTYPES", hashtable);
					} else
					{
						mainHash.put(hashtable.get("TYPE"), hashtable);
					}
				}
				else if(childNd.getNodeType() == XMLNode.COMMENT)
				{
					if(childNd.getNodeValue()!=null)
						comment = childNd.getNodeValue();
					//System.err.println("mohantest : comment "+comment);	
				}
			}

        } else
        {
       //     System.err.println("Error: Map Empty ");
        }
        mapIconUI.updateTheUI(mainHash);

	}
	/*{
		if(rootMapIconNode == null) return;
		Vector childNodes = rootMapIconNode.getChildNodes();
		//System.err.println("mohantest :rootMapIconNode   "+rootMapIconNode);	
		//System.err.println("mohantest :childNodes "+childNodes );
		if(childNodes != null)
		{
			mainHash = new Hashtable();
			for(int i = 0; i<childNodes.size();i++)
			{
				XMLNode childNd = (XMLNode)childNodes.elementAt(i);
				//System.err.println("mohantest :childNd "+childNd );
				Hashtable temp = childNd.getAttributeList();
				//System.err.println("mohantest :temp "+temp );
				mainHash.put(temp.get("FileName") , temp);
			}
		}
		else
		{
			//System.err.println("Error: Map Empty ");	
		}
		//System.err.println("mohantest mainHash:"+mainHash);
		mapIconUI.updateTheUI(mainHash) ; 
		//return mainHash;
	}*/
	

	public void updateTheUI()
	{
	}

	public void addMapIconNode(String nodeName , Hashtable hash)
	{
		send(GenericConstants.ADD , constructNode(nodeName , hash)); 
	}
	public void deleteMapIconNode(String nodeName , Hashtable hash)
	{
		send(GenericConstants.DELETE,constructNode(nodeName , hash)); 
	}

	public void modifyMapIconNode(String nodeName , Hashtable hash)
	{
		send(GenericConstants.MODIFY,constructNode(nodeName , hash)); 
	}

	public XMLNode constructNode(String nodeName , Hashtable hash)
	{
	  	String str = (String)hash.get("menuName");
		if ( (str != null) && (str.trim().equals("")) )
   		hash.get("iconName");
   		hash.get("MAP_FILTER");
		XMLNode rootNode = new XMLNode();
		rootNode.setNodeType( XMLNode.ELEMENT );
		rootNode.setNodeName( "MAP_ICON_DATA" );
		
		XMLNode xmlNode = new XMLNode();
		xmlNode.setNodeType( XMLNode.ELEMENT );
		xmlNode.setNodeName( nodeName );
		xmlNode.setAttributeList( hash );  
		rootNode.addChildNode(xmlNode);

		return rootNode;   
	}
	
	public void getMapIconNodes()
	{
		send(GenericConstants.GET ); 
	}

	public void setMapIconNodes()
	{
		//Not Applicable
		send(GenericConstants.SET , new XMLNode() ); 
	}

	public void reloadMapIconNode(String nodeName , Hashtable hash)
	{
		send(GenericConstants.RELOAD , constructNode(nodeName , hash)); 
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
			if(type == GenericConstants.MAP_ICON)
			{
				int operationIndex = inp.readInt();
				//Return the value to the UI
				switch(operationIndex )
				{
					case GenericConstants.ADD:
						{
							mapIconUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.DELETE:
						{
							mapIconUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.MODIFY:
						{
							mapIconUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.RELOAD:
						{
							mapIconUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.GET:
						{
							int datalength = inp.readInt();
							byte xmlarr[] = new byte[datalength];
							inp.readFully(xmlarr);
							XMLNode rootLogNode = (XMLNode)NmsClientUtil.deSerializeObject(xmlarr);
							updateTheMapIconUI(rootLogNode); 
							break;    
						}

					default:
						{
//							System.err.println(" Unknown command received in MapIconModel ");
							break;
						}

				}
			}
		}
		catch(Exception e)
		{
			System.err.println(NmsClientUtil.GetString("MapIconModel : Exception in callBack ") + "  " + e);
			e.printStackTrace();
		}
	}
	public String getModuleKey()
	{
		return MapConfiguartionSession.getInstance().getModuleId()+"_MapIcon";
	}

}



