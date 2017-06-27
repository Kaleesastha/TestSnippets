  //$Id: NodeDiscoveryEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Thu Feb 22 12:16:12 IST 2007
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
 * Contains the data handling under
 * nodeDiscoveryEntry group
 */

public class NodeDiscoveryEntry	{


	protected String nodeIP = "255.255.255.0" ;//No I18N
	protected String nodeNetMask = "255.255.255.0" ;//No I18N
	protected Integer nodeDiscoverEnable = new Integer(1) ;
	protected Integer snmpAgentPort = new Integer(1) ;
	protected String nodeCommunity = "nodeCommunity not initialized" ;//No I18N
	protected Integer snmpVersion = new Integer(1) ;
	protected String v3UserName = "v3UserName not initialized" ;//No I18N
	protected String v3ContextName = "v3ContextName not initialized" ;//No I18N


	/**
	 * Handles the  JMX  Get Request for nodeIP
	 */
	public String  getNodeIP()
				 throws AgentException {
		// fill up with necessary processing
		return nodeIP;
	}


	/**
	 * Handles the  JMX  Set Request for nodeIP
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setNodeIP (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		StringTokenizer tok =  new StringTokenizer(value,".",true);//No I18N
		if(tok.countTokens() > ((4*2) -1))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		nodeIP = value;
	}


	/**
	 * Handles the  JMX  Get Request for nodeNetMask
	 */
	public String  getNodeNetMask()
				 throws AgentException {
		// fill up with necessary processing
		return nodeNetMask;
	}


	/**
	 * Handles the  JMX  Set Request for nodeNetMask
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setNodeNetMask (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		StringTokenizer tok =  new StringTokenizer(value,".",true);//No I18N
		if(tok.countTokens() > ((4*2) -1))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		nodeNetMask = value;
	}


	/**
	 * Handles the  JMX  Get Request for nodeDiscoverEnable
	 */
	public Integer  getNodeDiscoverEnable()
				 throws AgentException {
		// fill up with necessary processing
		return nodeDiscoverEnable;
	}


	/**
	 * Handles the  JMX  Set Request for nodeDiscoverEnable
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setNodeDiscoverEnable (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N

		nodeDiscoverEnable = value;
	}


	/**
	 * Handles the  JMX  Get Request for snmpAgentPort
	 */
	public Integer  getSnmpAgentPort()
				 throws AgentException {
		// fill up with necessary processing
		return snmpAgentPort;
	}


	/**
	 * Handles the  JMX  Set Request for snmpAgentPort
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setSnmpAgentPort (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		snmpAgentPort = value;
	}


	/**
	 * Handles the  JMX  Get Request for nodeCommunity
	 */
	public String  getNodeCommunity()
				 throws AgentException {
		// fill up with necessary processing
		return nodeCommunity;
	}


	/**
	 * Handles the  JMX  Set Request for nodeCommunity
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setNodeCommunity (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		nodeCommunity = value;
	}


	/**
	 * Handles the  JMX  Get Request for snmpVersion
	 */
	public Integer  getSnmpVersion()
				 throws AgentException {
		// fill up with necessary processing
		return snmpVersion;
	}


	/**
	 * Handles the  JMX  Set Request for snmpVersion
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setSnmpVersion (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)||(value.intValue() == 3)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N

		snmpVersion = value;
	}


	/**
	 * Handles the  JMX  Get Request for v3UserName
	 */
	public String  getV3UserName()
				 throws AgentException {
		// fill up with necessary processing
		return v3UserName;
	}


	/**
	 * Handles the  JMX  Set Request for v3UserName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setV3UserName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		v3UserName = value;
	}


	/**
	 * Handles the  JMX  Get Request for v3ContextName
	 */
	public String  getV3ContextName()
				 throws AgentException {
		// fill up with necessary processing
		return v3ContextName;
	}


	/**
	 * Handles the  JMX  Set Request for v3ContextName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setV3ContextName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		v3ContextName = value;
	}


}

