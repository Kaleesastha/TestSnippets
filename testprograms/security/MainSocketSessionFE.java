//$Id: MainSocketSessionFE.java,v 1.1 2002/10/25 06:06:57 kumaragurun Exp $
package com.adventnet.nms.startnms;

import java.awt.*;
import java.io.*;
import java.util.*;

/*
import com.adventnet.nms.protocol.*;
import com.adventnet.nms.server.protocol.*;
*/
import com.adventnet.nms.protocol.NmsPDU;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureServerUtilsFE;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.startnms.rmi.*;
import com.adventnet.nms.startclient.rmi.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.util.Queue;

import com.adventnet.management.log.Log;
import com.adventnet.management.transport.*;
import com.adventnet.nms.commonfe.GenericFESession;
import com.adventnet.nms.commonfe.GenericFEAPIImpl;
import com.adventnet.management.config.ConfigPDU;
import com.adventnet.management.log.*;
import com.adventnet.security.audit.AuditAPI;
/**
 * This class is the Session object for the MainSocketServerFE. For every client connecting
 * to the MainSocketServerFE, a new MainSocketSessionFE object is instantiated and passed to all
 * the SocketServerConnectionFE modules registered with the MainSocketServerFE. The modules should
 * use the API methods in this class to communicate with the respective client modules.
 * <br>
 * <b>Note: </b>For com.adventnet.management.transport.SessionTransportProvider javadocs 
 * please refer the snmp api javadocs.
 */
public class MainSocketSessionFE implements Runnable
{
	private String sessionId = "";
	private DispatchManager dc=null;
	private Hashtable sessionTable = new Hashtable(10);
	//private Date clientKeepAliveTime;
	private ClientKeepAlive clientSch;
	private int keepAliveTime = 0;

	private SendToClient stc;
	private SessionTransportProvider transport = null;

    // This is the generic Session which will be used for some Generic FE-CLIENT communication messages.
    private GenericFESession genericSession = null;

    private boolean isGenericSocket = false;

    /**
     * <code>MAX_QUEUE_SIZE</code> is the size the Maximum size to which the Queue
     * for sending data to Client is allowed to grow. If the Queue size exceeds
     * this value, the Client's session will be closed. This is to avoid OutOfMemoryError 
     * in Server.
     */
    private int MAX_QUEUE_SIZE=5000;

	/**
	 * Constructor to instantiate the MainSocketSessionFE with the SessionTransportProvider
	 * to be used for receiving and sending data beween client and server.
	 */ 
	public MainSocketSessionFE(SessionTransportProvider stp, String sid)
	{
        this(stp,sid,false);
	}

        AuditAPI auditApi = null;
        //public static LogUser authuser = null;
    public MainSocketSessionFE(SessionTransportProvider stp, String sid,boolean isGenericSocket)
	{
        int count = 0;
        while (auditApi == null )
        {
            try
            {
                Thread.sleep(500);
                count ++;
            }
            catch ( Exception e ){}
            auditApi = (AuditAPI) NmsUtil.getAPI("NmsAuditAPI");
            if ( count == 5 ) 
            {
                //authuser.log("DEBUG:-) could not get AuditAPI in MainSocketSessionFE.",Log.SUMMARY); 
                break;
            }
        }
        //authuser = LogMgr.getLogUser("NMSAUDIT");
        this.isGenericSocket = isGenericSocket;
		sessionId = sid;
		transport = stp;
		dc=new DispatchManager();
        //starting the Generic Session only after setting the other variables!!
        //Actually we should have only a single instance of GenericFESession per
        //MSSFE.. Since we are instantiating the MSSFE for genericSocket also, we
        // have to avoid this instantiation!!
        if(!isGenericSocket)
        {
            genericSession  = new GenericFESession(this);
        }

		PureServerUtilsFE.clientSocketFE.registerForResponses(sessionId, this);
		clientSch = new ClientKeepAlive();

		stc = new SendToClient();
		stc.setName("SendToClient" + sessionId);
		stc.start();

		try
		{
			if (transport != null) transport.open(new String[0]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
        setMaxQueueSize();
	}

    private void setMaxQueueSize()
    {
        MAX_QUEUE_SIZE = PureServerUtilsFE.max_queue_size;
    }

    /**
     * <code>getMaxQueueSize</code> method returns the maximum size to which the Queue 
     * for sending data to Client is allowed to grow. If the Queue size exceeds
     * this value, the Client's session will be closed. This is to avoid OutOfMemoryError
     * in Server.
     *
     * @return the maximum queue size allowed.
     */
    public int getMaxQueueSize()
    {
        return MAX_QUEUE_SIZE;
    }
    
    /**
     * <code>getQueueSize</code> method returns the current queue-size for the client.
     *
     * @return the queue size.
     */
    public int getQueueSize()
    {
        return dataToClient.size();
    }

    /**
     * Tells whether this session is a generic/secondary session or not
     */
    public boolean isGenericSocketSession()
    {  
        return isGenericSocket;
    }

    public GenericFESession getGenericSession()
    {
        return genericSession;
    }

    /**
     *  Returns the SessionId associated with this session.
     */
    public String getSessionId()
    {
        return sessionId;
    }

	/**
	 * All clients implementing the SocketSessionConnectionFE Interface should register for
	 * responses here with the id the client will use for communication with the server.
	 * The receive() method of the SockeSessionConnectionFE class will be invoked if there
	 * is a client request with the matching id.
	 */
	public String registerForResponses(SocketSessionConnectionFE sockClient, String id)
	{
		NmsLogMgr.MISCUSER.log("Registering Session : " + id,  Log.INTERMEDIATE_DETAIL);
		sessionTable.put(id,sockClient);
		return id;
	}

	private boolean waiting = true;

	private boolean connected = true;

	/**
	 * The thread which listens for request from the client.
	 */
	public void run()
	{
		if (transport == null) return;
		while (connected)
		{
			waiting = true;
			try
			{
				byte[] barr = transport.read();
				waiting = false;
				processNmsPDU(barr);
				Thread.sleep(120);
			}
			catch (Exception e)
			{
				cleanUp();
				break;
			}
		}
	}

	public void processNmsPDU(byte[] barr)
	{
        byte[] dataForAudit = null;
		if (barr == null) return;
		if (barr == NmsPDU.CLOSE_SESSION)
		{
			cleanUp();
			return;
		}
		try
		{
			//clientKeepAliveTime = new Date();

			NmsPDU pdu = NmsPDU.deSerializeNmsPDU(barr);
			String req_type = pdu.id;
			String uniqueID = pdu.uniqueID;

			SocketSessionConnectionFE sockClient = (SocketSessionConnectionFE)sessionTable.get(req_type);
			//sessionTable may be null when User is disabled or when session is closed. This will lead to a NullPointerException which is harmless. This can be fixed by synchronizing the cleanUp and the processNmsPDU methods but will be costly. The NullPointerException has been caught and ignored.
			if (sockClient != null)
			{
				byte[] data = pdu.data;
                dataForAudit = new byte[data.length];
                System.arraycopy(data,0,dataForAudit,0,data.length);
				/*boolean  processed = sockClient.receive(uniqueID,data);
                  if (!processed)
                  { 
                  // Reconstruct the data and pass it to the Backend.
                  // The data originally from the client had
                  // req_type, uniqueId, actual message 
                  PureServerUtilsFE.clientSocketFE.send(req_type, uniqueID+"+"+sessionId, data);
                  }*/
				//DispatchManager.receiveTheData(sockClient,data,req_type,uniqueID,sessionId);
				//Removed static reference of DispatchManger. Instead an instance of DispatchManger is created for each FE.
                //authuser.log("DEBUG:-) sockClient is not null : req_type-->"+req_type+" uniqueID-->"+uniqueID+" sessionId-->"+sessionId+" username->"+username,Log.SUMMARY); 
                //System.out.println("DEBUG:-) sockClient is not null : req_type-->"+req_type+" uniqueID-->"+uniqueID+" sessionId-->"+sessionId+" username->"+username); 
                auditInfo(username,req_type,uniqueID,sessionId,dataForAudit);
				dc.receiveTheData(sockClient,data,req_type,uniqueID,sessionId);
			}
			else
			{
				byte[] data = pdu.data;
                dataForAudit = new byte[data.length];
                System.arraycopy(data,0,dataForAudit,0,data.length);
				waiting = false;
				if(req_type.equals("KEEP_ALIVE"))
				{
					//clientKeepAliveTime = new Date();
					keepAliveTime = 0;
				}
				else if(req_type.equals("BROADCAST_FROM_CLIENT"))
				{
                    //authuser.log("DEBUG:-) BROADCAST_FROM_CLIENT : req_type-->"+req_type+" uniqueID-->"+uniqueID+" sessionId-->"+sessionId+" username->"+username,Log.SUMMARY); 
                    //System.out.println("DEBUG:-) BROADCAST_FROM_CLIENT : req_type-->"+req_type+" uniqueID-->"+uniqueID+" sessionId-->"+sessionId+" username->"+username);
                    auditInfo(username,req_type,uniqueID,sessionId,dataForAudit);
					PureServerUtilsFE.clientSocketFE.send(req_type, uniqueID+"+"+sessionId, data);
				}
				else if (req_type.equals("CLOSE_SESSION"))
				{
                    //authuser.log("DEBUG:-) CLOSE_SESSION : req_type-->"+req_type+" uniqueID-->"+uniqueID+" sessionId-->"+sessionId+" username->"+username,Log.SUMMARY); 
                    //System.out.println("DEBUG:-) CLOSE_SESSION : req_type-->"+req_type+" uniqueID-->"+uniqueID+" sessionId-->"+sessionId+" username->"+username); 
                    auditInfo(username,req_type,uniqueID,sessionId,dataForAudit);
					cleanUp();
				}
				else if(req_type.equals("FE_LIST"))
				{
                    //authuser.log("DEBUG:-) FE_LIST : req_type-->"+req_type+" uniqueID-->"+uniqueID+" sessionId-->"+sessionId+" username->"+username,Log.SUMMARY);
                    //System.out.println("DEBUG:-) FE_LIST : req_type-->"+req_type+" uniqueID-->"+uniqueID+" sessionId-->"+sessionId+" username->"+username); 
                    auditInfo(username,req_type,uniqueID,sessionId,dataForAudit);
                    PureServerUtilsFE.clientSocketFE.send(req_type, uniqueID+"+"+sessionId, data);
				}
				else
				{
					//let's not print this log message for the set of modules
					if (!beModuleList.contains(req_type))
						NmsLogMgr.MISCUSER.log("unknown type received "+ req_type +  ": forwarding to Backend ", Log.INTERMEDIATE_DETAIL);

                    //authuser.log("DEBUG:-)  UNKNOWN TYPE RECEIVED req_type-->"+req_type+" uniqueID-->"+uniqueID+" sessionId-->"+sessionId+" username->"+username,Log.SUMMARY); 
                    //System.out.println("DEBUG:-)  UNKNOWN TYPE RECEIVED req_type-->"+req_type+" uniqueID-->"+uniqueID+" sessionId-->"+sessionId+" username->"+username); 
                    auditInfo(username,req_type,uniqueID,sessionId,dataForAudit);
					PureServerUtilsFE.clientSocketFE.send(req_type, uniqueID+"+"+sessionId, data);
				}
			}
		} 
		catch(StreamCorruptedException sce)
		{
			NmsLogMgr.MISCERR.fail(transport +" Exception occured " + sce, sce);
			//ideally this should be a restart sort of message
			sendCloseSession();
			cleanUp();
			return;
		}
		catch(NullPointerException excp)
		{
			NmsLogMgr.MISCERR.log("MainSocketSessionFE: Session table found to be null",Log.VERBOSE);// Harmless Exception.
		}
		catch (Exception ge)
		{
			NmsLogMgr.MISCERR.fail("Exception occured " + ge, ge);
		}
	}

    private void auditInfo (String username,String req_type,String uniqueID,String sessionId,byte[] data)
    {
        Properties prop = new Properties();
        prop.setProperty("STATUS","AUDIT");
        try
        {
            if ( req_type.equals("CONFIG_CLIENT"))
            {
                ConfigPDU pdu = ConfigPDU.deSerializeConfigPDU(data);
                prop.setProperty("OPERATION",req_type+"+"+uniqueID+"+"+sessionId+"+"+pdu.id+"+"+pdu.uniqueID+"+"+pdu.userName);
            }
            else
            {
                int req_type_audit=0;
                try {
                DataInputStream inp = new DataInputStream(new ByteArrayInputStream(data));
                req_type_audit = inp.readInt();
                } catch ( IOException e ) 
                { 
                    //authuser.log(e.toString(),Log.SUMMARY); 
                }
                
                prop.setProperty("OPERATION",req_type+"+"+uniqueID+"+"+sessionId+"+"+req_type_audit);
            }
            if (username == null)
            {
                //authuser.log("USER NAME is null",Log.SUMMARY); 
                return ;
            }
            else
            {
                auditApi.audit(username,prop);
            }
        } catch ( Exception e ) 
        {
            e.printStackTrace(); 
        }
    }
    
	private void sendCloseSession()
	{
		NmsPDU shutDownPDU = new NmsPDU("CLOSE_SESSION","0",new byte[0]);
		scheduleSend(shutDownPDU);
	}
	
	private static Vector beModuleList = new Vector(3);
	static
	{
		beModuleList.addElement("POLICY_CLIENT");
		beModuleList.addElement("CONFIG_CLIENT");
		beModuleList.addElement("SECURITY_ADMIN");
	}
	
	Queue  dataToClient = new Queue();

	//Queue dataFromClient = new Queue();

	/**
	 * This method is used to send responses or updates to the client. The id is
	 * the module id used by the client to route the packet to the correct module.
	 * If it is a response packet for a request from the client, then the uniqueID
	 * should be the same as what the client sent. If it is an asynchronous update
	 * the server module is sending to the client module, then the uniqueID should
	 * be set to the string "0".
	 */
	public boolean send(String id,String uniqueID,byte[] data)
	{
        NmsPDU clientObj = new NmsPDU(id,uniqueID,data);
        dataToClient.enQ(clientObj);

        if (dataToClient.size() > MAX_QUEUE_SIZE)
        {
            NmsLogMgr.MISCUSER.log(" Queue for sending data to Client("+username+") exceeded MAX_QUEUE_SIZE("+MAX_QUEUE_SIZE+"). Cleaning up Client Session.",  Log.SUMMARY);
            cleanUp();
            return false;
        }
        return true;
	}

    private synchronized boolean scheduleSend(NmsPDU pdu)
    {
        try
        {
            byte[] b = NmsPDU.serializeNmsPDU(pdu);
            int length = b.length;
            if (transport != null)
            {
                transport.write(b,length);
                return true;
            }
            return false;
        }
        catch (TransportException e)
        {
            NmsLogMgr.MISCERR.fail("TransportException in sending data to the client .. Cleaning Up." , null);
            cleanUp();
            return false;
        }
        catch (IOException e)
        {
            NmsLogMgr.MISCERR.fail("Exception in sending data to the client .. Cleaning Up" , null);
            cleanUp();
            return false;
        }
        catch (Exception ee)
        {
            NmsLogMgr.MISCERR.fail("Exception in sending data " + ee , null);
            return false;
        }
    }

	/**
	 * This called when the scheduler schedules this class to send the data to
	 * the client.
	 */
	public synchronized boolean scheduleSend(String id,String uniqueID,byte[] data)
	{
        NmsPDU pdu = new NmsPDU(id,uniqueID,data);
        return scheduleSend(pdu);
	}

	void cleanUp()
	{
		if (transport == null) return;
		try
		{
			transport.close();
		}
		catch (TransportException te)
		{
		}

		transport = null;
		if (sessionTable!=null)
		{
			for(Enumeration e = sessionTable.elements();e.hasMoreElements();) 
			{
				SocketSessionConnectionFE sockClient = (SocketSessionConnectionFE)e.nextElement();
				if(sockClient != null) 
				{
					try
					{
						sockClient.close();
					}
					catch (Exception anye) 
					{
						NmsLogMgr.MISCERR.fail(" Exception in MainSocketSessionFE while cleaning resources occupied by Sessions "+anye, anye);
					}
				}
			}
		}

		//clientKeepAliveTime=null;
		keepAliveTime = 0;
		clientSch=null;
		stc=null;
		sessionTable = null;
		dataToClient.removeAllElements();
		//dataFromClient.removeAllElements();

		//dataFromClient = null;
		//This is added to clean the ModuleDispatchers, when cleaning up this MainSocketSessionFE.
		dc.closeModuleDispatchers();	
		PureServerUtilsFE.clientSocketFE.deRegisterForResponses(sessionId);
        if(!isGenericSocket)
        {
            NmsLogMgr.MISCUSER.log("Client session closed for user "+username, Log.SUMMARY);
        }
        //GenericFEAPIImpl.getAPI().removeActiveUser(username);
		connected = false;
	}

    //This is a work around to maintain the list of active users logged in to this FE //tmkarthi
    //just to make sure the user name is added just once per client (not for once for event, once for alert, etc.).
    private String username = null;

    /**
     * Resends the userName to BE. For use in BE FailOver support.
     * @return whether the operation was successfull.
     */
    public boolean resendUserNameToBE()
    {
        if(username != null)
        {
            sendUserNameToBE(username);
            return true;
        }
        return false;
    }
	public void sendUserNameToBE(String userName)
	{
		String req_type = "USER_NAME";
		byte[] data_arr = userName.getBytes();
		PureServerUtilsFE.clientSocketFE.send(req_type,sessionId,data_arr);
        if(username == null)
        {
            //GenericFEAPIImpl.getAPI().addActiveUser(userName);
            username = userName;
        }
	}
    //Added for MSP
    public String getUserName()
    {
        return username;
    }
    //Added for MSP
	//Inner class SendToClient - starts.
	class SendToClient extends Thread
	{
		public void run()
		{
			while(connected)
			{
				while (dataToClient.size()>0)
				{
					NmsPDU cd = (NmsPDU)dataToClient.deQFirstElement();
					//scheduleSend(cd.id,cd.uniqueID,cd.data);
                    scheduleSend(cd);
				}
				try
				{
					Thread.sleep(100);
				}
				catch(Exception e)
				{
					NmsLogMgr.MISCERR.fail("", e);
				}
			}
		}
	}// Inner class SendToClient - ends.

	// Inner class ClientKeepAlive - starts.
	private class ClientKeepAlive implements Runnable 
	{
		ClientKeepAlive()
		{
			//schedule();
			Thread keepAlive = new Thread(this);
			keepAlive.setName("ClientKeepAlive" + sessionId);
			keepAlive.start();
		}
		public void run()
		{
			if (!connected) return;
			/*if (clientKeepAliveTime != null)
              {
              Date d = new Date();
              Date expire = new Date(clientKeepAliveTime.getTime() + (NmsUtil.keepalive_window_size * 10));
              if (d.after(expire) && waiting)
              {
              NmsLogMgr.MISCUSER.log("No keep alive from Client  Cleaning Up",  Log.SUMMARY);
              cleanUp();
              return;
              }
              }
              schedule();*/


			final int timeToSleep = NmsUtil.keepalive_window_size * 2;


			//The unit for keepalive_window_size is in milliseconds and its default
			//value is 30000 ms. Here the server will be waiting in seconds (default 300 secs)
			//and hence we are multipling the keepalive_window_size by 10 and dividing it by 
			//timeToSleep.
			final int MAXTIME = ( NmsUtil.keepalive_window_size * 10 ) / timeToSleep;

			while(connected)
			{
				if( (keepAliveTime > MAXTIME) && waiting )
				{
					NmsLogMgr.MISCUSER.log("No keep alive from Client  Cleaning Up",  Log.SUMMARY);
					cleanUp();
					return;
				}

				try
				{
					Thread.sleep(timeToSleep);
				}
				catch(Throwable ex)
				{
				}

				keepAliveTime++;
			}
		}

		/*private void schedule()
          {
          Date d = new Date();
          d = new Date(d.getTime() + NmsUtil.keepalive_window_size);
          NmsUtil.scheduler.scheduleTask(this,d);
          }*/
	}// Inner class ClientKeepAlive - ends.
}// class MainSocketSessionFE - ends.
