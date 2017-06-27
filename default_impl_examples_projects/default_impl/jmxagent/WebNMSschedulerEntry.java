//$Id: WebNMSschedulerEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Thu Feb 22 12:16:10 IST 2007
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
 * webNMSschedulerEntry group
 */

public class WebNMSschedulerEntry	{


	protected Integer webNMSSchedulerIndex = new Integer(1) ;
	protected String webNMSSchedulerDesc = "webNMSSchedulerDesc not initialized" ; //No I18N
	protected Integer webNMSSchedulerNumTasks = new Integer(1) ;
	protected Integer webNMSSchedulerNumThreads = new Integer(1) ;
	protected Integer webNMSSchedulerActiveThreads = new Integer(1) ;
	protected Integer webNMSSchedulerIdleThreads = new Integer(1) ;


	/**
	 * Handles the  JMX  Get Request for webNMSSchedulerIndex
	 */
	public Integer  getWebNMSSchedulerIndex()
				 throws AgentException {
		// fill up with necessary processing
		return webNMSSchedulerIndex;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSSchedulerIndex
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSSchedulerIndex (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSSchedulerIndex = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSSchedulerDesc
	 */
	public String  getWebNMSSchedulerDesc()
				 throws AgentException {
		// fill up with necessary processing
		return webNMSSchedulerDesc;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSSchedulerDesc
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSSchedulerDesc (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		webNMSSchedulerDesc = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSSchedulerNumTasks
	 */
	public Integer  getWebNMSSchedulerNumTasks()
				 throws AgentException {
		// fill up with necessary processing
		return webNMSSchedulerNumTasks;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSSchedulerNumTasks
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSSchedulerNumTasks (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSSchedulerNumTasks = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSSchedulerNumThreads
	 */
	public Integer  getWebNMSSchedulerNumThreads()
				 throws AgentException {
		// fill up with necessary processing
		return webNMSSchedulerNumThreads;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSSchedulerNumThreads
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSSchedulerNumThreads (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSSchedulerNumThreads = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSSchedulerActiveThreads
	 */
	public Integer  getWebNMSSchedulerActiveThreads()
				 throws AgentException {
		// fill up with necessary processing
		return webNMSSchedulerActiveThreads;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSSchedulerActiveThreads
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSSchedulerActiveThreads (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSSchedulerActiveThreads = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSSchedulerIdleThreads
	 */
	public Integer  getWebNMSSchedulerIdleThreads()
				 throws AgentException {
		// fill up with necessary processing
		return webNMSSchedulerIdleThreads;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSSchedulerIdleThreads
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSSchedulerIdleThreads (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSSchedulerIdleThreads = value;
	}


}

