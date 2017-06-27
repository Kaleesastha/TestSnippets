//$Id: SubAgentEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Fri Mar 09 14:26:07 IST 2007
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
 * subAgentEntry group
 */

public class SubAgentEntry	{


	protected String subAgentoid = ".1.3.6.1.2.1" ;//No I18N
	protected String subAgent = "subAgent not initialized" ;//No I18N
	protected Long  subAgentPort = new Long(0) ;
	protected Integer subAgentVersion = new Integer(1) ;
	protected String subAgentCommunity = "subAgentCommunity not initialized" ;//No I18N
	protected Long  timeout = new Long(0) ;
	protected Long  subAgentRetries = new Long(0) ;
	protected Integer rowStatus = new Integer(1) ;


	/**
	 * Handles the  JMX  Get Request for subAgentoid
	 */
	public String  getSubAgentoid()
				 throws AgentException {
		// fill up with necessary processing
		return subAgentoid;
	}


	/**
	 * Handles the  JMX  Set Request for subAgentoid
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setSubAgentoid (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		subAgentoid = value;
	}


	/**
	 * Handles the  JMX  Get Request for subAgent
	 */
	public String  getSubAgent()
				 throws AgentException {
		// fill up with necessary processing
		return subAgent;
	}


	/**
	 * Handles the  JMX  Set Request for subAgent
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setSubAgent (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		subAgent = value;
	}


	/**
	 * Handles the  JMX  Get Request for subAgentPort
	 */
	public Long  getSubAgentPort()
				 throws AgentException {
		// fill up with necessary processing
		return subAgentPort;
	}


	/**
	 * Handles the  JMX  Set Request for subAgentPort
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setSubAgentPort (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		subAgentPort = value;
	}


	/**
	 * Handles the  JMX  Get Request for subAgentVersion
	 */
	public Integer  getSubAgentVersion()
				 throws AgentException {
		// fill up with necessary processing
		return subAgentVersion;
	}


	/**
	 * Handles the  JMX  Set Request for subAgentVersion
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setSubAgentVersion (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		subAgentVersion = value;
	}


	/**
	 * Handles the  JMX  Get Request for subAgentCommunity
	 */
	public String  getSubAgentCommunity()
				 throws AgentException {
		// fill up with necessary processing
		return subAgentCommunity;
	}


	/**
	 * Handles the  JMX  Set Request for subAgentCommunity
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setSubAgentCommunity (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		subAgentCommunity = value;
	}


	/**
	 * Handles the  JMX  Get Request for timeout
	 */
	public Long  getTimeout()
				 throws AgentException {
		// fill up with necessary processing
		return timeout;
	}


	/**
	 * Handles the  JMX  Set Request for timeout
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setTimeout (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		timeout = value;
	}


	/**
	 * Handles the  JMX  Get Request for subAgentRetries
	 */
	public Long  getSubAgentRetries()
				 throws AgentException {
		// fill up with necessary processing
		return subAgentRetries;
	}


	/**
	 * Handles the  JMX  Set Request for subAgentRetries
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setSubAgentRetries (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		subAgentRetries = value;
	}


	/**
	 * Handles the  JMX  Get Request for rowStatus
	 */
	public Integer  getRowStatus()
				 throws AgentException {
		// fill up with necessary processing
		return rowStatus;
	}


	/**
	 * Handles the  JMX  Set Request for rowStatus
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setRowStatus (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)||(value.intValue() == 3)||(value.intValue() == 4)||(value.intValue() == 5)||(value.intValue() == 6)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N

		rowStatus = value;
	}


}

