//$Id: ExampleFE.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.example.befe;


import com.adventnet.nms.util.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.startnms.*;
import com.adventnet.nms.fe.common.BEFailOverEvent;
import com.adventnet.nms.fe.common.BEFailOverListener;

import java.util.*;
import java.io.*;

public class ExampleFE implements RunProcessInterface, BEFailOverListener 
{
	EventAPI eventAPI;
	public void shutDown()
	{
		System.err.println(NmsUtil.GetString("ExampleFE shutdown called"));
	}
	public boolean isInitialized()
	{
		return true;
	}
	public void callMain(String args[])
	{
		try
		{
			ExampleFEServer obj = new ExampleFEServer();
		}
		catch ( Exception e)
		{
			e.printStackTrace();
		}
		PureServerUtilsFE.clientSocketFE.registerBEFailOverListener(this);  

			
	}

	/**
	 * Implementation for BEFailOverListener.
	 */
	public void preBEFailOverNotification(BEFailOverEvent event)
	{
            try{
                String message=NmsUtil.GetString("Connection lost to the WebNMS Back End Server(")+event.getOldBEHost()+").";
                if(event.getNewBEHost()!=null)
                {
                    message+=NmsUtil.GetString("Standby BE(")+event.getNewBEHost()+NmsUtil.GetString(") found. Getting initialized. Please wait till the standby server is completely initialized.");
                }

                ByteArrayOutputStream byteStream=new ByteArrayOutputStream();
                DataOutputStream outp=new DataOutputStream(byteStream);
                outp.writeInt(0);
                outp.writeUTF(message);
                outp.writeUTF("FECLIENTS");
                outp.flush();

                PureServerUtilsFE.clientSocketFE.broadcastMessage("BROADCAST_FROM_CLIENT",byteStream.toByteArray());  

            }catch(IOException e)
            {
                e.printStackTrace();
                System.err.println(NmsUtil.GetString("Exception occured while sending broadcast Message to client about BE Failure.")+e.getMessage()); 
            }
          }	
       	/**
	 * Implementation for BEFailOverListener.
	 */
    
	public void postBEFailOverNotification(BEFailOverEvent event)
	{
            try{
                String message=NmsUtil.GetString("FE successfully connected to the new WebNMS Back End(")+event.getNewBEHost()+")";
                
                ByteArrayOutputStream byteStream=new ByteArrayOutputStream();
                DataOutputStream outp=new DataOutputStream(byteStream);
                outp.writeInt(0);
                outp.writeUTF(message);
                outp.writeUTF("FECLIENTS");
                outp.flush();
                
                PureServerUtilsFE.clientSocketFE.broadcastMessage("BROADCAST_FROM_CLIENT",byteStream.toByteArray());
                
            }catch(IOException e)
            {
                e.printStackTrace();
                System.err.println(NmsUtil.GetString("Exception occured while sending broadcast Message to client about the connection of a new BE.")+e.getMessage()); 
            }

	}	


}
