//$Id: PollEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Thu Feb 22 12:16:11 IST 2007
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
 * pollEntry group
 */

public class PollEntry	{


	protected Long  pollid = new Long(0) ;
	protected String polldataName = "polldataName not initialized" ;//No I18N
	protected String oid = "oid not initialized" ;//No I18N
	protected Long  pollingInterval = new Long(0) ;
	protected Integer failureCount = new Integer(1) ;
	protected Integer failureThreshold = new Integer(1) ;
	protected Long  timeToPoll = new Long(0) ;
	protected Long  polledTime = new Long(0) ;
	protected String agentName = "agentName not initialized" ;//No I18N


	/**
	 * Handles the  JMX  Get Request for pollid
	 */
	public Long  getPollid()
				 throws AgentException {
		// fill up with necessary processing
		return pollid;
	}


	/**
	 * Handles the  JMX  Set Request for pollid
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setPollid (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		pollid = value;
	}


	/**
	 * Handles the  JMX  Get Request for polldataName
	 */
	public String  getPolldataName()
				 throws AgentException {
		// fill up with necessary processing
		return polldataName;
	}


	/**
	 * Handles the  JMX  Set Request for polldataName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setPolldataName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		polldataName = value;
	}


	/**
	 * Handles the  JMX  Get Request for oid
	 */
	public String  getOid()
				 throws AgentException {
		// fill up with necessary processing
		return oid;
	}


	/**
	 * Handles the  JMX  Set Request for oid
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setOid (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		oid = value;
	}


	/**
	 * Handles the  JMX  Get Request for pollingInterval
	 */
	public Long  getPollingInterval()
				 throws AgentException {
		// fill up with necessary processing
		return pollingInterval;
	}


	/**
	 * Handles the  JMX  Set Request for pollingInterval
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setPollingInterval (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		pollingInterval = value;
	}


	/**
	 * Handles the  JMX  Get Request for failureCount
	 */
	public Integer  getFailureCount()
				 throws AgentException {
		// fill up with necessary processing
		return failureCount;
	}


	/**
	 * Handles the  JMX  Set Request for failureCount
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setFailureCount (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		failureCount = value;
	}


	/**
	 * Handles the  JMX  Get Request for failureThreshold
	 */
	public Integer  getFailureThreshold()
				 throws AgentException {
		// fill up with necessary processing
		return failureThreshold;
	}


	/**
	 * Handles the  JMX  Set Request for failureThreshold
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setFailureThreshold (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		failureThreshold = value;
	}


	/**
	 * Handles the  JMX  Get Request for timeToPoll
	 */
	public Long  getTimeToPoll()
				 throws AgentException {
		// fill up with necessary processing
		return timeToPoll;
	}


	/**
	 * Handles the  JMX  Set Request for timeToPoll
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setTimeToPoll (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		timeToPoll = value;
	}


	/**
	 * Handles the  JMX  Get Request for polledTime
	 */
	public Long  getPolledTime()
				 throws AgentException {
		// fill up with necessary processing
		return polledTime;
	}


	/**
	 * Handles the  JMX  Set Request for polledTime
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setPolledTime (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		polledTime = value;
	}


	/**
	 * Handles the  JMX  Get Request for agentName
	 */
	public String  getAgentName()
				 throws AgentException {
		// fill up with necessary processing
		return agentName;
	}


	/**
	 * Handles the  JMX  Set Request for agentName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setAgentName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		agentName = value;
	}


}

