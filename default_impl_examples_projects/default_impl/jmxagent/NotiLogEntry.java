//$Id: NotiLogEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * notiLogEntry group
 */

public class NotiLogEntry	{


	protected Integer notiLogIndex = new Integer(1) ;
	protected Long  notiLogTime = new Long(0) ;
	protected Integer notiLogNumVarBinds = new Integer(1) ;
	protected String notiLogOid = ".1.3.6.1.2.1" ;//No I18N


	/**
	 * Handles the  JMX  Get Request for notiLogIndex
	 */
	public Integer  getNotiLogIndex()
				 throws AgentException {
		// fill up with necessary processing
		return notiLogIndex;
	}


	/**
	 * Handles the  JMX  Set Request for notiLogIndex
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setNotiLogIndex (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		notiLogIndex = value;
	}


	/**
	 * Handles the  JMX  Get Request for notiLogTime
	 */
	public Long  getNotiLogTime()
				 throws AgentException {
		// fill up with necessary processing
		return notiLogTime;
	}


	/**
	 * Handles the  JMX  Set Request for notiLogTime
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setNotiLogTime (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		notiLogTime = value;
	}


	/**
	 * Handles the  JMX  Get Request for notiLogNumVarBinds
	 */
	public Integer  getNotiLogNumVarBinds()
				 throws AgentException {
		// fill up with necessary processing
		return notiLogNumVarBinds;
	}


	/**
	 * Handles the  JMX  Set Request for notiLogNumVarBinds
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setNotiLogNumVarBinds (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		notiLogNumVarBinds = value;
	}


	/**
	 * Handles the  JMX  Get Request for notiLogOid
	 */
	public String  getNotiLogOid()
				 throws AgentException {
		// fill up with necessary processing
		return notiLogOid;
	}


	/**
	 * Handles the  JMX  Set Request for notiLogOid
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setNotiLogOid (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		notiLogOid = value;
	}


}

