//$Id: PerfNotiVarbindsInstrument.java,v 1.3 2007/07/03 07:05:58 barathv Exp $
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
 * Handles all the requests under PerfNotiVarbinds group
 */
public class PerfNotiVarbindsInstrument {



	protected Integer eventid = new Integer(1) ;
	protected String eventsource = "" ;//No I18N
	protected String eventEntity = "" ;//No I18N
	protected Long  eventGenTime = new Long(0) ;
	protected Integer eventSeverity = new Integer(1) ;




	/**
	 * Handles the  JMX  Get Request for eventid
	 */
	public Integer  getEventid()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return eventid;
	}


	/**
	 * Handles the  JMX  Set Request for eventid
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setEventid (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		eventid = value;
	}


	/**
	 * Handles the  JMX  Get Request for eventsource
	 */
	public String  getEventsource()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return eventsource;
	}


	/**
	 * Handles the  JMX  Set Request for eventsource
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setEventsource (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		eventsource = value;
	}


	/**
	 * Handles the  JMX  Get Request for eventEntity
	 */
	public String  getEventEntity()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return eventEntity;
	}


	/**
	 * Handles the  JMX  Set Request for eventEntity
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setEventEntity (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		eventEntity = value;
	}


	/**
	 * Handles the  JMX  Get Request for eventGenTime
	 */
	public Long  getEventGenTime()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return eventGenTime;
	}


	/**
	 * Handles the  JMX  Set Request for eventGenTime
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setEventGenTime (Long  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		eventGenTime = value;
	}


	/**
	 * Handles the  JMX  Get Request for eventSeverity
	 */
	public Integer  getEventSeverity()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return eventSeverity;
	}


	/**
	 * Handles the  JMX  Set Request for eventSeverity
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setEventSeverity (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		eventSeverity = value;
	}
}

