//$Id: WebNMSSystemInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
/* Copyright (c)  1996 - 2004  Adventnet, Inc. All Rights Reserved.
 * PLEASE READ THE ASSOCIATED COPYRIGHTS FILE FOR MORE DETAILS.
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.
 * ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.
 */

/**
 * @Version :  6.0.0 Wed Feb 14 00:34:04 IST 2007
 * @Author  :  AdventNet Agent Toolkit Java Edition
 */

// Any changes made to this file will be lost, if regenerated.
// User code should be added within user tags for code merging support, if regenerated.



// Package Name (Dont Delete this comment)
package com.adventnet.nms.jmxagent ;

import com.adventnet.utilities.common.*;
import com.adventnet.utils.agent.utils;

import java.io.*;
import java.util.*;

// User code starts here
import java.net.*;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.StandAloneShutDown;
// User code ends here

/**
 * Handles all the requests under WebNMSSystem group
 */
public class WebNMSSystemInstrument {



	protected String webNMSVersion = "webNMSVersion not initialized" ;//No I18N
	protected String webNMSHost = "webNMSHost not initialized" ;//No I18N
	// User code starts here
	/*
	protected String webNMSIpAddress = "255.255.255.0" ;//No I18N
	*/
	// User code ends here
	protected String webNMSIpAddress="127.0.0.1";//No I18N
	protected Integer webNMSTotalMemory = new Integer(1) ;
	protected Integer webNMSFreeMemory = new Integer(1) ;
	protected byte[] webNMSStartTime = { (byte)0x7, (byte)0xCF, (byte)0x9, (byte)0xF, (byte)0xC, (byte)0x3, (byte)0xA, (byte)0x0,(byte)0x2B, (byte)0x5, (byte)0x1E } ;
	protected Long  webNMSUpTime = new Long(0) ;
	protected String webNMSShutdown = "webNMSShutdown not initialized" ;//No I18N
	protected String webNMSVersiondummy = "webNMSVersion not initialized" ;

 	// User code starts here
   	 //by senthil
    	AdventNet_WebNMS_MIB_JMX agentRef = null;

    	//constructor with agentRef
    	public void setAgentReference(AdventNet_WebNMS_MIB_JMX agent)
 	   {
		this.agentRef = agent;
    	}

    // User code ends here



	/**
	 * Handles the  JMX  Get Request for webNMSVersion
	 */
	public String  getWebNMSVersion()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	/*
	 * Getting the WebNMS Version
	 */
	webNMSVersion = NmsUtil.GetString("WebNMS Version x.x");	//No I18N
	// User code ends here

		return webNMSVersion;
	}


	public String  getWebNMSVersiondummy()
			 throws AgentException
{
	// Fill up the stub with required processing
	// User code starts here
/*
* Getting the WebNMS Version
*/
webNMSVersiondummy = NmsUtil.GetString("WebNMS Version x.x");	//No I18N
// User code ends here

	return webNMSVersiondummy;
}
	
	/**
	 * @param webNMSVersiondummy the webNMSVersiondummy to set
	 */

	public synchronized void setWebNMSVersiondummy (String  value )
			 throws AgentException {
	// Fill up the stub with required processing

	if(value == null)
		throw new AgentException("", CommonUtils.WRONGVALUE);
	if(!(((value.length()>=0)&&(value.length()<=255))))
		throw new AgentException("", CommonUtils.WRONGLENGTH);

	webNMSVersion = value;
}
	/**
	 * Handles the  JMX  Set Request for webNMSVersion
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSVersion (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		webNMSVersion = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSHost
	 */
	public String  getWebNMSHost()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	try
	{
	    /*
	     * Getting the localhost's host name
	     */
	    webNMSHost = InetAddress.getLocalHost().getHostName();
	}
	catch (Exception e)
	{
	    agentRef.agentErr.fail("Exception" , e);//No I18N
	    throw new AgentException("", CommonUtils.GENERR);
	}
	// User code ends here
		return webNMSHost;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSHost
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSHost (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		webNMSHost = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSIpAddress
	 */
	public String  getWebNMSIpAddress()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	try
	{
	    /*
	     * Getting the localhost's IPAddress
	     */
	    webNMSIpAddress = InetAddress.getLocalHost().getHostAddress();
	}
	catch (Exception e)
	{
	    agentRef.agentErr.fail("Exception" , e);//No I18N
	    throw new AgentException("", CommonUtils.GENERR);
	}
	// User code ends here

		return webNMSIpAddress;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSIpAddress
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSIpAddress (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		StringTokenizer tok =  new StringTokenizer(value,".",true);// No I18N
		if(tok.countTokens() > ((4*2) -1))
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSIpAddress = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSTotalMemory
	 */
	public Integer  getWebNMSTotalMemory()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	/*
	 * Getting the total disk space
	 */
	webNMSTotalMemory = new Integer((int)Runtime.getRuntime().totalMemory());
	// User code ends here

		return webNMSTotalMemory;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSTotalMemory
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSTotalMemory (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSTotalMemory = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSFreeMemory
	 */
	public Integer  getWebNMSFreeMemory()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	/*
	 * Getting the unused disc space
	 */
	webNMSFreeMemory = new Integer((int)Runtime.getRuntime().freeMemory());
	// User code ends here

		return webNMSFreeMemory;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSFreeMemory
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSFreeMemory (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSFreeMemory = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSStartTime
	 */
	public byte[] getWebNMSStartTime()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	/*
	 * Getting the time at which the NMS-Server had started
	 */
	webNMSStartTime = WebNMSAgentApp.startTime;
	// User code ends here


		return webNMSStartTime;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSStartTime
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSStartTime (byte[] value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
			// User code starts here
	/* Getting the time at which the NMS-Server had started
		webNMSStartTime = value;



	 */
	webNMSStartTime = WebNMSAgentApp.startTime;
	// User code ends here

	}


	/**
	 * Handles the  JMX  Get Request for webNMSUpTime
	 */
	public Long  getWebNMSUpTime()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	/*
	 * Calculating the uptime of NMS-Server
	 */
	long currentTime = System.currentTimeMillis();
	long upTime = currentTime - WebNMSAgentApp.startTimeMillis;
	webNMSUpTime = new Long(upTime);
	// User code ends here

		return webNMSUpTime;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSUpTime
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSUpTime (Long  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSUpTime = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSShutdown
	 */
	public String  getWebNMSShutdown()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	webNMSShutdown = "Set the <userName> and <passWord>";//No I18N
	// User code ends here

		return webNMSShutdown;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSShutdown
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSShutdown (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		webNMSShutdown = value;
	}
}





