//$Id: PersistentTrapsMibInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Wed Feb 14 00:34:06 IST 2007
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
 * Handles all the requests under PersistentTrapsMib group
 */
public class PersistentTrapsMibInstrument {



	protected Integer maxRows = new Integer(1) ;
	protected Integer sequenceNum = new Integer(1) ;

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
	 * Handles the  JMX  Get Request for maxRows
	 */
	public Integer  getMaxRows()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	maxRows = new Integer(agentRef.maxRows);
	// User code ends here

		return maxRows;
	}


	/**
	 * Handles the  JMX  Set Request for maxRows
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setMaxRows (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		maxRows = value;
		// User code starts here
	agentRef.maxRows = value.intValue();
	agentRef.updateDefValue("maxRows",value.toString());//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for sequenceNum
	 */
	public Integer  getSequenceNum()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	sequenceNum = new Integer(agentRef.sequNo);
	// User code ends here
		return sequenceNum;
	}


	/**
	 * Handles the  JMX  Set Request for sequenceNum
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setSequenceNum (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		sequenceNum = value;
	}
}


