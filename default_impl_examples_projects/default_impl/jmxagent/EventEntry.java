 //$Id: EventEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * eventEntry group
 */

public class EventEntry	{


	protected Integer evtID = new Integer(1) ;
	protected String evtSource = "evtSource not initialized" ;//No I18N
	protected String evtEntity = "evtEntity not initialized" ;//No I18N
	protected Integer evtSeverity = new Integer(1) ;
	protected String evtCategory = "evtCategory not initialized" ;//No I18N
	protected Long  evtTime = new Long(0) ;
	protected String evtText = "evtText not initialized" ;//No I18N
	protected String eventUserProperties = "eventUserProperties not initialized" ;//No I18N


	/**
	 * Handles the  JMX  Get Request for evtID
	 */
	public Integer  getEvtID()
				 throws AgentException {
		// fill up with necessary processing
		return evtID;
	}


	/**
	 * Handles the  JMX  Set Request for evtID
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setEvtID (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		evtID = value;
	}


	/**
	 * Handles the  JMX  Get Request for evtSource
	 */
	public String  getEvtSource()
				 throws AgentException {
		// fill up with necessary processing
		return evtSource;
	}


	/**
	 * Handles the  JMX  Set Request for evtSource
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setEvtSource (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		evtSource = value;
	}


	/**
	 * Handles the  JMX  Get Request for evtEntity
	 */
	public String  getEvtEntity()
				 throws AgentException {
		// fill up with necessary processing
		return evtEntity;
	}


	/**
	 * Handles the  JMX  Set Request for evtEntity
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setEvtEntity (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		evtEntity = value;
	}


	/**
	 * Handles the  JMX  Get Request for evtSeverity
	 */
	public Integer  getEvtSeverity()
				 throws AgentException {
		// fill up with necessary processing
		return evtSeverity;
	}


	/**
	 * Handles the  JMX  Set Request for evtSeverity
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setEvtSeverity (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		evtSeverity = value;
	}


	/**
	 * Handles the  JMX  Get Request for evtCategory
	 */
	public String  getEvtCategory()
				 throws AgentException {
		// fill up with necessary processing
		return evtCategory;
	}


	/**
	 * Handles the  JMX  Set Request for evtCategory
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setEvtCategory (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		evtCategory = value;
	}


	/**
	 * Handles the  JMX  Get Request for evtTime
	 */
	public Long  getEvtTime()
				 throws AgentException {
		// fill up with necessary processing
		return evtTime;
	}


	/**
	 * Handles the  JMX  Set Request for evtTime
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setEvtTime (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		evtTime = value;
	}


	/**
	 * Handles the  JMX  Get Request for evtText
	 */
	public String  getEvtText()
				 throws AgentException {
		// fill up with necessary processing
		return evtText;
	}


	/**
	 * Handles the  JMX  Set Request for evtText
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setEvtText (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		evtText = value;
	}


	/**
	 * Handles the  JMX  Get Request for eventUserProperties
	 */
	public String  getEventUserProperties()
				 throws AgentException {
		// fill up with necessary processing
		return eventUserProperties;
	}


	/**
	 * Handles the  JMX  Set Request for eventUserProperties
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setEventUserProperties (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		eventUserProperties = value;
	}


}

