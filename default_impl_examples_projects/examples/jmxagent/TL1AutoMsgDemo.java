/* $Id: TL1AutoMsgDemo.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
package com.adventnet.nms.example.jmxagent;

import com.adventnet.management.*;
import com.adventnet.management.tl1.*;
import java.util.*;
/**
 * This program is to show the reception of Autonomous Messages.The MSEventListener
 * interface is implemented and the response is received in the method of the interface
 * namely,setEventResult.
 */
public class TL1AutoMsgDemo implements MSEventListener
{
    static String hostName = "localhost";
    static String portNo = "9099";
    
    ManagementServices service = null;
    TL1Property property = new TL1Property();
    String sessionID = null;

    /**
     * Getting the handle for the ManagementServiceAPI
     */
    TL1AutoMsgDemo()
    {
	service = ManagementServicesAPI.getInstance((String[])null,ManagementServicesAPI.MS_TWO_TIER_MODE);
	connect();
    }
    
    /**
     * Setting the properties for the agent and getting
     * a connection with the TL1Agent by establishing a 
     * session.Registering for receiving TL1Notification
     *
     */
    void connect()
    {
	property.setTargetHost(hostName);
	property.setTargetPort(portNo);
	property.setComponent(this); 
	sessionID = service.establishSession(property);
	property.setSessionId(sessionID);
	service.registerForNotification(property); 
    }

    /**
     * The method of the interface which receives the TL1AutonomousMessages.
     * 
     */
    public void setEventResult(Property property, java.lang.Object result)
    {
	TL1Event event = (TL1Event)result;
	Properties prop = event.getProperties();
	System.out.println(" TL1AutonomousMessage received from " + hostName + " is \n" + prop.getProperty("Text"));
    }

    /**
     * Getting the host and the port values from the user
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
	new TL1AutoMsgDemo();
    }
}
