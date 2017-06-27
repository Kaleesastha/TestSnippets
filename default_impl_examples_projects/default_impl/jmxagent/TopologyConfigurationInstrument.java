//$Id: TopologyConfigurationInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Wed Feb 14 00:34:11 IST 2007
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
 * Handles all the requests under TopologyConfiguration group
 */
public class TopologyConfigurationInstrument {

 // User code starts here
    //by senthil
    AdventNet_WebNMS_MIB_JMX agentRef = null;

    //constructor with agentRef
    public void setAgentReference(AdventNet_WebNMS_MIB_JMX agent)
    {
	this.agentRef = agent;
    }
    // User code ends here

	protected Integer autoDiscover = new Integer(1) ;
	protected Integer discoverLocalNet = new Integer(1) ;
	protected Integer discoverInterval = new Integer(1) ;
	protected Integer enablelog = new Integer(1) ;




	/**
	 * Handles the  JMX  Get Request for autoDiscover
	 */
	public Integer  getAutoDiscover()
				 throws AgentException
	{
		// Fill up the stub with required processing
	// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	autoDiscover = agentRef.topoConfImpl.autoDisc;
	// User code ends here
		return autoDiscover;
	}


	/**
	 * Handles the  JMX  Set Request for autoDiscover
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setAutoDiscover (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
                            // User code starts here
                            /*
		autoDiscover = value;
		*/
		if((value.intValue() < 1) || (value.intValue() > 2))
	    throw new AgentException("", CommonUtils.BADVALUE);//No I18N
agentRef.topoConfImpl.autoDisc = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
		// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for discoverLocalNet
	 */
	public Integer  getDiscoverLocalNet()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	discoverLocalNet = agentRef.topoConfImpl.discLocalNet;
	// User code ends here

		return discoverLocalNet;
	}


	/**
	 * Handles the  JMX  Set Request for discoverLocalNet
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setDiscoverLocalNet (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		// User code starts here
		/*
		discoverLocalNet = value;
		*/
		if((value.intValue() < 1) || (value.intValue() > 2))
	    throw new AgentException("", CommonUtils.BADVALUE);//No I18N
	    agentRef.topoConfImpl.discLocalNet = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
		// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for discoverInterval
	 */
	public Integer  getDiscoverInterval()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	discoverInterval = agentRef.topoConfImpl.discInterval;
	// User code ends here
		return discoverInterval;
	}


	/**
	 * Handles the  JMX  Set Request for discoverInterval
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setDiscoverInterval (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		discoverInterval = value;
		*/

	agentRef.topoConfImpl.discInterval = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N


		// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for enablelog
	 */
	public Integer  getEnablelog()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	enablelog = agentRef.topoConfImpl.enableLog;
	// User code ends here
		return enablelog;
	}


	/**
	 * Handles the  JMX  Set Request for enablelog
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setEnablelog (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		// User code starts here
		/*
		enablelog = value;
		*/
		// User code ends here
		agentRef.topoConfImpl.enableLog = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	}
}



