//$Id: SecuritySession.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.security.ui;

//WebNMS imports
import com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.SocketConnection;
//Java imports 
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class SecuritySession extends ResultEventAdapter implements SocketConnection
{
    private static SecuritySession securitySession;	
    private static String SECURITY_ID = "SECURITY_ADMIN";

	public SecuritySession()
	{
           PureClientUtils.commonSocket.registerForResponses(this,SECURITY_ID);
	}

	public synchronized void receive(byte[] data)
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis;
		try
		{
			dis= new DataInputStream(bais);
			int ind = dis.readInt();
                        	ResultEventObject resultEventObject = new ResultEventObject(this,bais,ind);
                       	fireResultEvent(resultEventObject);
    			bais.close();
			dis.close();
		}
		catch(Exception e)
	{
			System.err.println(NmsClientUtil.GetString("Exception in receiving data from FE"));
			e.printStackTrace();
		}

	}
    /*
	public void open() 
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		try 
		{
			DataOutputStream outp = new DataOutputStream(byteStream);
			String user=NmsClientUtil.getUserName();
			outp.writeInt(SecurityConstants.READY);
			outp.writeUTF(user);
			outp.flush();
			byte[] bytes = byteStream.toByteArray();
			outp.close();
			byteStream.reset();
			byteStream.close();
			PureClientUtils.commonSocket.send(SecurityConstants.ID,bytes);
		}
		catch (Exception ie) 
		{
			System.err.println(NmsClientUtil.GetString("Could not open SecuritySession  ")+ ie);
			return;
		}
	}
    */
	public void close()
	{

	}
    public void deRegister(ResultEventListener listener)
    {
        super.deRegister(listener);
        PureClientUtils.commonSocket.deRegisterForResponses(SECURITY_ID);
    }

	public static SecuritySession getInstance()
	{
            if ( securitySession == null) 
                securitySession = new SecuritySession(); 
            return securitySession; 
	}

    public void send(byte[] sendData)
    {
        try
        {
            PureClientUtils.commonSocket.send(SECURITY_ID, sendData);
        }
        catch(Exception ee)
        {
            System.err.println(NmsClientUtil.GetString ("Could not send request in Security Session") + ee);//No Internationalisation
        } 
    }

    public byte[] syncSend(byte[] sendData)
   {
	return null;	
    }
}



