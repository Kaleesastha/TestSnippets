//$Id: PollingFiltersEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * Contains the data handling under
 * pollingFiltersEntry group
 */

public class PollingFiltersEntry	{


	protected Integer pollingFilterIndex = new Integer(1) ;
	protected String pollingFilterClassName = "pollingFilterClassName not initialized" ;//No I18N


	/**
	 * Handles the  JMX  Get Request for pollingFilterIndex
	 */
	public Integer  getPollingFilterIndex()
				 throws AgentException {
		// fill up with necessary processing
		return pollingFilterIndex;
	}


	/**
	 * Handles the  JMX  Set Request for pollingFilterIndex
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setPollingFilterIndex (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		pollingFilterIndex = value;
	}


	/**
	 * Handles the  JMX  Get Request for pollingFilterClassName
	 */
	public String  getPollingFilterClassName()
				 throws AgentException {
		// fill up with necessary processing
		return pollingFilterClassName;
	}


	/**
	 * Handles the  JMX  Set Request for pollingFilterClassName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setPollingFilterClassName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		pollingFilterClassName = value;
	}


}

