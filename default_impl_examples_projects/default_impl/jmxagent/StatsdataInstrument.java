//$Id: StatsdataInstrument.java,v 1.3 2007/10/17 09:24:33 barathv Exp $
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
 * @Version :  6.0.0 Wed Feb 14 00:34:10 IST 2007
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
 * Handles all the requests under Statsdata group
 */
public class StatsdataInstrument {



	protected String statsdataTableName = "statsdataTableName not initialized" ;//No I18N
 // User code starts here

    AdventNet_WebNMS_MIB_JMX agentRef = null;
    public void setAgentReference(AdventNet_WebNMS_MIB_JMX agentRef)
    {
	this.agentRef = agentRef;
    }

    // User code ends here



	/**
	 * Handles the  JMX  Get Request for statsdataTableName
	 */
	public String  getStatsdataTableName()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
      /** get the statsdata table name assigned by the user
       */
      statsdataTableName = agentRef.statstableName;
    	  // User code ends here

		return statsdataTableName;
	}


	/**
	 * Handles the  JMX  Set Request for statsdataTableName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setStatsdataTableName (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		statsdataTableName = value;
		// User code starts here
      /** get the statsdata table name assigned by the user
       */
       agentRef.statstableName = statsdataTableName;
    	  // User code ends here
	}
}



