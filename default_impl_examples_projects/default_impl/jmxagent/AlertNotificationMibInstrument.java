 //$Id: AlertNotificationMibInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Wed Feb 14 00:34:09 IST 2007
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
 * Handles all the requests under AlertNotificationMib group
 */
public class AlertNotificationMibInstrument {



	protected String alertExtraPropNames = "alertExtraPropNames not initialized" ;//No I18N

 // User code starts here
    AdventNet_WebNMS_MIB_JMX agentRef = null;

    //constructor with agentRef
    public void setAgentReference(AdventNet_WebNMS_MIB_JMX agent)
    {
	this.agentRef = agent;
    }
    // User code ends here


	/**
	 * Handles the  JMX  Get Request for alertExtraPropNames
	 */
	public String  getAlertExtraPropNames()
				 throws AgentException
	{
		// Fill up the stub with required processing
		 // User code starts here
        /** assigning the extra property names assigned by the user
         */
        alertExtraPropNames = agentRef.alertExtraPropNames;
        // User code ends here

		return alertExtraPropNames;
	}


	/**
	 * Handles the  JMX  Set Request for alertExtraPropNames
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setAlertExtraPropNames (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N
			// User code starts here
			/*

		alertExtraPropNames = value;
		*/
		if((value.length() < 0)||(value.length() > 255))
	    throw new AgentException("", CommonUtils.BADVALUE);//No I18N
        /** checking if the syntax is correct for the extra property names,
            if not throwing exception
        */
	if(!agentRef.syntaxOK(value))
	    throw new AgentException("",CommonUtils.BADVALUE);//No I18N
	agentRef.alertExtraPropNames = value;
        alertExtraPropNames = agentRef.alertExtraPropNames;
        /** storing the extra property names in the database
         */
	agentRef.updateDefValue("alertExtraPropNames",value);//No I18N
		// User code ends here
	}
}


