//$Id: SnmpPingConfigurationInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Fri Mar 09 14:26:08 IST 2007
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



/**
 * Handles all the requests under SnmpPingConfiguration group
 */
public class SnmpPingConfigurationInstrument {



	protected Integer enableSnmpPing = new Integer(1) ;
	protected Integer snmpPingRetries = new Integer(1) ;
	protected Integer snmpPingTimeout = new Integer(1) ;
	protected String snmpport = "snmpport not initialized" ;//No I18N
	protected String readCommunity = "readCommunity not initialized" ;//No I18N
	protected String writeCommunity = "writeCommunity not initialized" ;//No I18N
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
	 * Handles the  JMX  Get Request for enableSnmpPing
	 */
	public Integer  getEnableSnmpPing()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	enableSnmpPing = agentRef.topoConfImpl.enableSnmpPing;
	// User code ends here

		return enableSnmpPing;
	}


	/**
	 * Handles the  JMX  Set Request for enableSnmpPing
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setEnableSnmpPing (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		enableSnmpPing = value;
		*/
			agentRef.topoConfImpl.enableSnmpPing = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for snmpPingRetries
	 */
	public Integer  getSnmpPingRetries()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	snmpPingRetries = agentRef.topoConfImpl.snmpPingRetries;
	// User code ends here

		return snmpPingRetries;
	}


	/**
	 * Handles the  JMX  Set Request for snmpPingRetries
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setSnmpPingRetries (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		snmpPingRetries = value;
		*/
		agentRef.topoConfImpl.snmpPingRetries = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for snmpPingTimeout
	 */
	public Integer  getSnmpPingTimeout()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	snmpPingTimeout = agentRef.topoConfImpl.snmpPingTimeout;
	// User code ends here

		return snmpPingTimeout;
	}


	/**
	 * Handles the  JMX  Set Request for snmpPingTimeout
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setSnmpPingTimeout (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		snmpPingTimeout = value;
		*/
			agentRef.topoConfImpl.snmpPingTimeout = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for snmpport
	 */
	public String  getSnmpport()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	snmpport = agentRef.topoConfImpl.snmpPort;
	// User code ends here
		return snmpport;

	}


	/**
	 * Handles the  JMX  Set Request for snmpport
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setSnmpport (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N
			// User code starts here
			/*
		snmpport = value;
		*/
		agentRef.topoConfImpl.snmpPort = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for readCommunity
	 */
	public String  getReadCommunity()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	readCommunity = agentRef.topoConfImpl.readCommunity;
	// User code ends here

		return readCommunity;
	}


	/**
	 * Handles the  JMX  Set Request for readCommunity
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setReadCommunity (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N
			// User code starts here
			/*

				readCommunity = value;
				*/
				agentRef.topoConfImpl.readCommunity = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for writeCommunity
	 */
	public String  getWriteCommunity()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	writeCommunity = agentRef.topoConfImpl.writeCommunity;
	// User code ends here

		return writeCommunity;
	}


	/**
	 * Handles the  JMX  Set Request for writeCommunity
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setWriteCommunity (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N
			// User code starts here
			/*
		writeCommunity = value;
		*/
			agentRef.topoConfImpl.writeCommunity = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}
}


