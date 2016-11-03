/* $Id: TL1ManagerDemo.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package com.adventnet.nms.example.jmxagent;

import com.adventnet.management.*;
import com.adventnet.management.tl1.*;

/**
 * This is an example for getting the value of the WebNMSHost of NMS -TL1 Agent.
 */
public class TL1ManagerDemo
{
    static String hostName = "localhost";
    static String portNo = "9099";

    ManagementServices service = null;
    TL1Property property = new TL1Property();
    ManagementServerResultEvent result = null;
    String sessionID = null;
   
    /**
     * Getting the handle for the ManagementServiceAPI application
     */
    TL1ManagerDemo()
    {
	service = ManagementServicesAPI.getInstance((String[])null,ManagementServicesAPI.MS_TWO_TIER_MODE);

	connect();
	send();
    }
    /**
     * Storing properties in the TL1Property hash file 
     * and getting a connection with the TL1Agent by establishing a session with the Agent
     */    
    void connect()
    {
	property.setTargetHost(hostName);
	property.setTargetPort(portNo);
	property.setComponent(this); 
	sessionID = service.establishSession(property);
    }
    /**
     *  Setting the requisite TL1Commands as properties and
     *  invoking syncSend().The result is printed as a response
     *  to the method
     */
    void send()
    {
	try
	{
	    property.setCommandCode("GETALL-WEBNMSSCHEDULERTABLE");
	    property.setTargetId("");
	    property.setAccessId("AdventNet_WebNMS_MIB_JMX-WebNMSSchedulerTable");
	    property.setGeneralBlock("");
	    property.setMessagePayloadBlock("1,5");

	    if(sessionID != null)
		property.setSessionId(sessionID);

	    result = service.syncSend(property);

	    if(result != null)
	    {
		if(result.getErrString() != null)
		{
		    System.out.println(" Error while getting Response" +result.getErrString());
		}
		else
		{
		    System.out.println(" Resonse received from " + hostName + " is \n" + result.getResult());
		}
	    }
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }

    /**
     * Obtaining the host and the port passed from the command-line
     */
    public static void main(String[] args)
    {
	for (int i = 0 ; i < args.length; i+=2)
	{
	    if(args[i].trim().equals("-HOST"))
		hostName = args[i+1];
	    if(args[i].trim().equals("-PORT"))
		portNo = args[i+1];
	}
	new TL1ManagerDemo();
    }
}
