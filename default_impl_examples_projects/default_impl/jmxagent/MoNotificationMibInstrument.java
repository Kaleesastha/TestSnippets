 //$Id: MoNotificationMibInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Wed Feb 14 00:34:08 IST 2007
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
 * Handles all the requests under MoNotificationMib group
 */
public class MoNotificationMibInstrument {



	protected String moExtraPropNames = "moExtraPropNames not initialized" ; //No I18N
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
	 * Handles the  JMX  Get Request for moExtraPropNames
	 */
	public String  getMoExtraPropNames()
				 throws AgentException
	{
		// Fill up the stub with required processing

	// User code starts here
	moExtraPropNames = agentRef.moExtraPropNames;
	// User code ends here

		return moExtraPropNames;
	}


	/**
	 * Handles the  JMX  Set Request for moExtraPropNames
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoExtraPropNames (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		moExtraPropNames = value;
		// User code starts here
	agentRef.moExtraPropNames = value;
	/*
	 * Storing the names into the DataBase using NMS's UserStorageAPI.
	 */
	agentRef.updateDefValue("moExtraPropNames",value);//No I18N
	// User code ends here
	}
}


