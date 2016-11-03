//$Id: StatsDataEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * statsDataEntry group
 */

public class StatsDataEntry	{


	protected Long  pollID = new Long(0) ;
	protected String time = "time not initialized" ;//No I18N
	protected String oidindex = "oidindex not initialized" ;//No I18N
	protected String value = "value not initialized" ;//No I18N


	/**
	 * Handles the  JMX  Get Request for pollID
	 */
	public Long  getPollID()
				 throws AgentException {
		// fill up with necessary processing
		return pollID;
	}


	/**
	 * Handles the  JMX  Set Request for pollID
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setPollID (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		pollID = value;
	}


	/**
	 * Handles the  JMX  Get Request for time
	 */
	public String  getTime()
				 throws AgentException {
		// fill up with necessary processing
		return time;
	}


	/**
	 * Handles the  JMX  Set Request for time
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setTime (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		time = value;
	}


	/**
	 * Handles the  JMX  Get Request for oidindex
	 */
	public String  getOidindex()
				 throws AgentException {
		// fill up with necessary processing
		return oidindex;
	}


	/**
	 * Handles the  JMX  Set Request for oidindex
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setOidindex (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		oidindex = value;
	}


	/**
	 * Handles the  JMX  Get Request for value
	 */
	public String  getValue()
				 throws AgentException {
		// fill up with necessary processing
		return value;
	}


	/**
	 * Handles the  JMX  Set Request for value
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setValue (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		value = value;
	}


}

