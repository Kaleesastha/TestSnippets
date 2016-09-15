//$Id :
package com.adventnet.nms.broadcast;
/**
*BroadcastClient.java
**/
import java.net.*;
import java.util.*;
import java.io.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.*;
/**
* This file acts as the client for Broadcast. he registers with MainSocketClient* and when a broadcast message is received shows it in the status bar by defualt
* The user can also register to this and receive the message and handle it.
**/

public class BroadcastClient implements SocketConnection
{

	// the signature of Broadcast
	static String BROADCASTID = "BROADCAST_FROM_CLIENT";//No Internationalisation
	final static int BROADCAST_INFO=97;

	// instance of this class.
	static BroadcastClient broadcastClient = null;
	
	// hashtable containing the user class registered to it.
	Vector  uiTable = new Vector();
	
	/** register with MainSocketClient */
	private BroadcastClient()
	{
		PureClientUtils.commonSocket.registerForResponses(this,BROADCASTID);	
	}

	/** This method returns the instance of broadcastClient.
	*The user class can use this instance and then send the message.
	*@return  an instance of BroadcastClient
	**/
	public static BroadcastClient getInstance()
	{
		if(broadcastClient==null)
		{
			broadcastClient= new BroadcastClient();
		}
		return broadcastClient;
	}
	/** 
	* This method is used to send message across clients.
	* The user can decide whether he wishes to send the message
	* to all clients connected to the WebNMS-Backend server
	* or only to the clients connected to his FE server only.
	* @param message The string that has to be broadcasted.
	* @param criteria If <t>FECLIENTS</t> : only to that particular FE clients.
	*If <t>ALLCLIENTS</t> : to all the clients connected to the BE server thro 
	* the FE servers.
	**/
	public void send(String message, String criteria)
	{
	  try
	  {
		  ByteArrayOutputStream byteStream=new ByteArrayOutputStream();
		  DataOutputStream outp=new DataOutputStream(byteStream);
		  outp.writeInt(BROADCAST_INFO);
		  outp.writeUTF(message);
		  outp.writeUTF(criteria);
		  outp.flush();
                  PureClientUtils.commonSocket.send(BROADCASTID,byteStream.toByteArray());
	  }
	  catch(IOException e)
	  {
		  NmsClientUtil.err(NmsClientUtil.GetString("Communication error with Server : ")+e);
	  }
	}

	/** 
	*Messages sent to this client will be received by this method.
	* SocketConnection interface implementation.
	* if no listeners is registered, then the message is printed on the status bar.
	* If any listeners are registered, then the message is directed to them.
	*@param message sent to this client
	**/
	public synchronized void receive(byte[] data) 
	{
		try{
			DataInputStream inp = new DataInputStream(new ByteArrayInputStream(data));
			int req_id  = inp.readInt();
			//the data required to display is got.
			String theData = inp.readUTF();
			String theType = inp.readUTF();

                        //this should be removed once the the uiclient
			//is able to register at startup itself.
			if(uiTable.size()== 0)
                        {
                            MainPanel nmsPanel = (MainPanel)NmsClientUtil.getMainPanel();
                            boolean showMsgInStautsBar = nmsPanel.isStatusBarAdded();
                            
                            if(showMsgInStautsBar)
                            {
                                NmsClientUtil.showStatusOnLabel(theData,NmsClientUtil.BROADCAST_COLOR);
                            }
                            else
                            {
                                System.out.println("\r"+theData);
                            }
                            
                        }



			// For testing the test case (id: CATS-JCF-API-BC-010), uncomment the following line
			// System.out.println("Broadcast test " + data); 
			

                       
                        for(int i =0;i< uiTable.size();i++)
			{
                                BroadcastListener bl = (BroadcastListener)uiTable.elementAt(i);
				bl.receiveAndShow(theData);
			}
			
			
		}
		catch(IOException e)
		{
			System.err.println(NmsClientUtil.GetString("Error in receiving data in BroadcastClient ")+e);
		}
	}

	/**
	* the UI class should register with the BroadcastClient
	* inorder to receive the responses.
	* @param bl Interface which listens for the message.
	**/
	public void registerForResponse(BroadcastListener bl)
	{
            
            uiTable.addElement(bl);
	}


    public void unRegisterForResponse(BroadcastListener bl)
    {
        uiTable.removeElement(bl);
    }
    
	/**
	*Another interface method. Called when connection is closed. We can free
	*the resources.
	*/
	public void close()
	{
	}
}

