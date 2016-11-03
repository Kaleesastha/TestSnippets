  //$Id: IcmpPingConfigurationInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * Handles all the requests under IcmpPingConfiguration group
 */
public class IcmpPingConfigurationInstrument {



	protected Integer enableIcmp = new Integer(1) ;
	protected Integer icmpPingRetries = new Integer(1) ;
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
	 * Handles the  JMX  Get Request for enableIcmp
	 */
	public Integer  getEnableIcmp()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	enableIcmp = agentRef.topoConfImpl.enableIcmp;
	// User code ends here

		return enableIcmp;
	}


	/**
	 * Handles the  JMX  Set Request for enableIcmp
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setEnableIcmp (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
			enableIcmp = value;
			*/
			agentRef.topoConfImpl.enableIcmp = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here

	}


	/**
	 * Handles the  JMX  Get Request for icmpPingRetries
	 */
	public Integer  getIcmpPingRetries()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	icmpPingRetries =  agentRef.topoConfImpl.pingRetries;
	// User code ends here

		return icmpPingRetries;
	}


	/**
	 * Handles the  JMX  Set Request for icmpPingRetries
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setIcmpPingRetries (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		icmpPingRetries = value;
		*/
			agentRef.topoConfImpl.pingRetries = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}
}


