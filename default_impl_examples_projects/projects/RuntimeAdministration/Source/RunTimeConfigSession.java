//$Id: RunTimeConfigSession.java,v 1.2 2007/02/22 15:03:07 srajeswari Exp $
package com.adventnet.nms.runtimeconfig;

import com.adventnet.nms.util.ResultEventListener;
import com.adventnet.nms.util.ResultEventAdapter;
import com.adventnet.nms.util.ResultEventObject;
import com.adventnet.nms.util.NmsClientUtil;
//import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.startnms.GenericConstants;
import java.util.Hashtable;
import java.util.Enumeration;


import java.io.*;


public class RunTimeConfigSession extends ResultEventAdapter implements ResultEventListener
{
	
	static RunTimeConfigSession runTimeConfigSession = null;
	//private static RunTimeConfigSession runTimeConfigSession = null;

	public static Hashtable runTimeConfigConstants = new Hashtable();
	private ResultEventAdapter session = null;

	//public static CommonBuilderUIInterface uiInterface = null;

	static
	{
		 runTimeConfigConstants.put("LOG_CLIENT",new Integer(700)); 
		 runTimeConfigConstants.put("LIST_CLIENT",new Integer(701)); 
		 runTimeConfigConstants.put("POLICY_CLIENT",new Integer(702)); 
		 runTimeConfigConstants.put("MAP_CLIENT",new Integer(703)); 
		 runTimeConfigConstants.put("POLL_CLIENT",new Integer(704)); 
		 runTimeConfigConstants.put("EVENT_CLIENT",new Integer(705)); 
	}
    // Added by Balan on 15/03/03
    //This method used for registering modules
    public static void  registerModuleNamesandIds(String strModuleName,int nModuleId)
    {
        Object objTempValue = null;
        int    nIdValue     = -1;
        for(Enumeration enumerate = runTimeConfigConstants.keys();enumerate.hasMoreElements();)
        {
        objTempValue  = runTimeConfigConstants.get(enumerate.nextElement());
        nIdValue      = ((Integer)objTempValue).intValue();
        if( nIdValue == nModuleId)
        {
            System.err.println(NmsClientUtil.GetString("This Module Id Already Registered"));
            return;
        }
        }
        runTimeConfigConstants.put(strModuleName,new Integer(nModuleId));       
  
    }// Add Ends
	
	public static int getModuleIndex(String key)
	{
		Integer i = (Integer)runTimeConfigConstants.get(key);
		if(i != null)
			return i.intValue();
		return -1;	
	}
	
	private RunTimeConfigSession ()
	{
		session = RuntimeConfigFrame.getCommonBuilderUIImpl().getTheGenericSession();
		session.register(this);
	}

	//Just ForWard to GenericSession
    public void send(byte[] sendData)
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream(byteStream);
			outp.writeInt(GenericConstants.RUNTIME_ADMIN);
			outp.write(sendData);
			//outp.write(sendData , 0 , sendData.length);
			outp.flush();
			byte[] bytes = byteStream.toByteArray();
			outp.close();
			byteStream.reset();
			byteStream.close();
			session.send(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("RunTimeConfigSession : Exception in sendRequest") + "  " + e);
			e.printStackTrace();
		}                  
	}

    public byte[] syncSend(byte[] sendData)
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream outp = new DataOutputStream(byteStream);
			outp.writeInt(GenericConstants.RUNTIME_ADMIN);
			outp.write(sendData);
			//outp.write(sendData , 0 , sendData.length);
			outp.flush();
			byte[] bytes = byteStream.toByteArray();
			outp.close();
			byteStream.reset();
			byteStream.close();
			return session.syncSend(bytes);
		}
		catch (Exception e)
		{
			System.err.println(NmsClientUtil.GetString("RunTimeConfigSession : Exception in syncSendRequest") + "  " + e);
			e.printStackTrace();
		}                  
		return null;
	}

		static String serverClassPath = null;
		static String serverClassPathDelimiter = null;

		public static String getClassPath()
		{
			return serverClassPath;
		}

		public static String getClassPathDelimiter()
		{
			return serverClassPathDelimiter;
		}

		public void sendReqForClassPath()
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			try 
			{
				DataOutputStream outp = new DataOutputStream(byteStream);
				outp.writeInt(com.adventnet.nms.startnms.GenericConstants.REQ_CLASS_PATH);
				outp.flush();
				byte[] bytes = byteStream.toByteArray();
				outp.close();
				byteStream.reset();
				byteStream.close();
				session.send(bytes);
			}
			catch (Exception ie) 
			{
				System.err.println(NmsClientUtil.GetString("Unable to get the Server Classpath Setting")+ ie);
				ie.printStackTrace();
				return;
			}
		}

    public void callBack(ResultEventObject evt)
	{
		DataInputStream dis = null;
		ByteArrayInputStream bais = evt.getResultEventData();
		try
		{
			dis= new DataInputStream(bais);
			int type = evt.getEventType();
			if(type == GenericConstants.RUNTIME_ADMIN )
			{
            	String moduleId = dis.readUTF();
                ResultEventObject resultEventObject = new ResultEventObject(this,bais,getModuleIndex(moduleId));
                fireResultEvent(resultEventObject);
			}else if(type == GenericConstants.REQ_CLASS_PATH)
			{
				serverClassPath = dis.readUTF();
				serverClassPathDelimiter = dis.readUTF();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void dispose()
	{
            //Commented by Balan on 25/03/03 for SPP Module
            /*
		//listeners.removeAllElements();
		for(int i = 0;i<listeners.size();i++)
		{
			//System.err.println("mohantest :deRegistered "+listeners.elementAt(i));	
			this.deRegister((ResultEventListener)listeners.elementAt(i)); 
		}
		//System.err.println("mohantest :deRegistered "+this);	
		session.deRegister(this);
		//cleaning sataic variables
		cleanupStaticVariables();
            */
            //Comment Ends

            //Added by Balan on 25/03/03 for RTA Changes Requested  SPP Team to call destroySession method
            //this is an additional requirement by the SPP team
            Object objTempListeners = null;

            for(int i = 0;i<listeners.size();i++)
		{
                        objTempListeners = listeners.elementAt(i);
                        if(objTempListeners instanceof ResultEventListener )
                        {
                            this.deRegister((ResultEventListener)objTempListeners);
                        }
                        if(objTempListeners instanceof ResultEventAdapter )
                        {
                            ((ResultEventAdapter)objTempListeners). destroySession();
                        }
                
		}
		session.deRegister(this);
                cleanupStaticVariables();
                //Add Ends
	}

	private void cleanupStaticVariables()
	{
		EventConfiguartionSession.eventConfiguartionSession = null;
		ListConfiguartionSession.listConfiguartionSession = null;
		//LoggingConfiguartionSession.loggingConfiguartionSession = null;
		MapConfiguartionSession.mapConfiguartionSession = null;
		//PolicyConfigurationSession.policyConfigurationSession = null;
		PollConfiguartionSession.pollConfiguartionSession = null;
		RunTimeConfigSession.runTimeConfigSession = null;
	}

	public String toString()
	{
		return "RunTimeConfigSession";
	}



	public static RunTimeConfigSession getInstance()
	{
		if( runTimeConfigSession == null)
			runTimeConfigSession = new RunTimeConfigSession();
		return runTimeConfigSession;
	}
}
