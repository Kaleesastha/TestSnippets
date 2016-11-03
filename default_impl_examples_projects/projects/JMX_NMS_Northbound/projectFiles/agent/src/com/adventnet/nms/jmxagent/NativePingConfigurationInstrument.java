 //$Id: NativePingConfigurationInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * Handles all the requests under NativePingConfiguration group
 */
public class NativePingConfigurationInstrument {



	protected Integer nativePingRetries = new Integer(1) ;
	protected Integer nativePingTimeOut = new Integer(1) ;
	protected Integer debugLevel = new Integer(1) ;
	protected Integer enableSweep = new Integer(1) ;
	protected Integer sweepPkts = new Integer(1) ;
	protected Integer sweepSleepInterval = new Integer(1) ;
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
	 * Handles the  JMX  Get Request for nativePingRetries
	 */
	public Integer  getNativePingRetries()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	nativePingRetries = agentRef.topoConfImpl.icmpPingRetries;
	// User code ends here

		return nativePingRetries;
	}


	/**
	 * Handles the  JMX  Set Request for nativePingRetries
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setNativePingRetries (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		nativePingRetries = value;
		*/
			agentRef.topoConfImpl.icmpPingRetries = value;
	if(!agentRef.topoConfImpl.setSeedData(2))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for nativePingTimeOut
	 */
	public Integer  getNativePingTimeOut()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	nativePingTimeOut = agentRef.topoConfImpl.icmpTimeout;
	// User code ends here

		return nativePingTimeOut;
	}


	/**
	 * Handles the  JMX  Set Request for nativePingTimeOut
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setNativePingTimeOut (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		nativePingTimeOut = value;
		*/
			agentRef.topoConfImpl.icmpTimeout = value;
	if(!agentRef.topoConfImpl.setSeedData(2))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for debugLevel
	 */
	public Integer  getDebugLevel()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	debugLevel = agentRef.topoConfImpl.icmpDebugLevel;
	// User code ends here

		return debugLevel;
	}


	/**
	 * Handles the  JMX  Set Request for debugLevel
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setDebugLevel (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		debugLevel = value;
		*/
			agentRef.topoConfImpl.icmpDebugLevel = value;
	if(!agentRef.topoConfImpl.setSeedData(2))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for enableSweep
	 */
	public Integer  getEnableSweep()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	enableSweep = agentRef.topoConfImpl.enableSweep;
	// User code ends here

		return enableSweep;
	}


	/**
	 * Handles the  JMX  Set Request for enableSweep
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setEnableSweep (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		enableSweep = value;
		*/
			agentRef.topoConfImpl.enableSweep = value;
	if(!agentRef.topoConfImpl.setSeedData(2))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for sweepPkts
	 */
	public Integer  getSweepPkts()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	sweepPkts = agentRef.topoConfImpl.icmpSweepPkts;
	// User code ends here

		return sweepPkts;
	}


	/**
	 * Handles the  JMX  Set Request for sweepPkts
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setSweepPkts (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		sweepPkts = value;
		*/
			agentRef.topoConfImpl.icmpSweepPkts = value;
	if(!agentRef.topoConfImpl.setSeedData(2))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for sweepSleepInterval
	 */
	public Integer  getSweepSleepInterval()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	sweepSleepInterval = agentRef.topoConfImpl.icmpSweepInterval;
	// User code ends here

		return sweepSleepInterval;
	}


	/**
	 * Handles the  JMX  Set Request for sweepSleepInterval
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setSweepSleepInterval (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		sweepSleepInterval = value;
		*/
			agentRef.topoConfImpl.icmpSweepInterval = value;
	if(!agentRef.topoConfImpl.setSeedData(2))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}
}


