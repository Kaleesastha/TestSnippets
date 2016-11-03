//$Id: PollFilterModel.java,v 1.1 2006/08/29 13:57:02 build Exp $
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


public class PollFilterModel implements ResultEventListener  
{

	PollConfiguartionSession pollConfiguartionSession = null;
	PollFilterUI pollFilterUI = null;
	Hashtable mainHash = null;

	public PollFilterModel(PollFilterUI pollFilterUi )
	{
		pollFilterUI = pollFilterUi ;
		pollConfiguartionSession =  PollConfiguartionSession.getInstance(); 
		pollConfiguartionSession.register(this); 
	}


	public void send( int operation )
	{
		send( operation , null);
	}

	public void send( int operation , Vector modifyVect )
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream(byteStream);
			outp.writeInt(GenericConstants.DISC_FILTERS);
			outp.writeInt(operation);
			if(modifyVect != null)
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
			PollConfiguartionSession.getInstance().send(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("PollConfiguartionSession : Exception in sendRequest") + "  " + e);
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
			outp.writeInt(GenericConstants.DISC_FILTERS);
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
			return PollConfiguartionSession.getInstance().syncSend(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("PollConfiguartionSession : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
		return null;
	}


	public void updateThePollFilterUI(Vector resultVec)
	{
		if(resultVec== null) 
		{
	//		System.err.println("Error ocurred while updating The PollFilterUI");	
			return;
		}
		pollFilterUI.updateTheUI(resultVec);
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

	public void setFilterNodes(Vector vect)
	{
	//	System.out.println("Into Model............."+vect);
		send(GenericConstants.SET , vect ); 
	}

	public void reloadFilterNode(Vector vect)
	{
		send(GenericConstants.RELOAD , vect ); 
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
					case GenericConstants.SET:
						{
							int authID = inp.readInt();
							if(authID == GenericConstants.NO_AUTH)
							{
								//The user doesn't have permission to do this operation.
								String str = inp.readUTF();
								pollFilterUI.authFailed(str); 
							}
							else
							{
								pollFilterUI.updateTheUI(); 
							}	
							break;    
						}
					case GenericConstants.ADD:
						{
							pollFilterUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.DELETE:
						{
							pollFilterUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.MODIFY:
						{
							pollFilterUI.updateTheUI(); 
							break;    
						}
					case GenericConstants.RELOAD:
						{
							int authID = inp.readInt();
							if(authID == GenericConstants.NO_AUTH)
							{
								String str = inp.readUTF();
								pollFilterUI.authFailed(str); 
							}
							else
							{
								pollFilterUI.updateTheUI(); 
							}
							break;    
						}
					case GenericConstants.GET:
						{
							int authID = inp.readInt();
							if(authID == GenericConstants.NO_AUTH)
							{
								String str = inp.readUTF();
								pollFilterUI.authFailed(str); 
							}
							else
							{
								int datalength = inp.readInt();
								byte vecarr[] = new byte[datalength];
								inp.readFully(vecarr);
								Vector resultVector = NmsClientUtil.deSerializeVector(vecarr);
								updateThePollFilterUI(resultVector); 
							}	
							break;    
						}

					default:
						{
							//						System.err.println(" Unknown command received in PollConfiguartionSession : PollConfiguartionSession ");
							break;
						}

				}
			}
		}
		catch(Exception e)
		{
			System.err.println(NmsClientUtil.GetString("PollConfiguartionSession : Exception in callBack ") + "  " + e);
			e.printStackTrace();
		}
	}
	public String getModuleKey()
	{
		return PollConfiguartionSession.getInstance().getModuleId()+"_DiscFilter";
	}

}
