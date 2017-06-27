//$Id: SnmpV3ConfigurationInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Fri Mar 09 14:26:09 IST 2007
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
 * Handles all the requests under SnmpV3Configuration group
 */
public class SnmpV3ConfigurationInstrument {



	protected Integer enableV3 = new Integer(1) ;
	protected String v3Username = "v3Username not initialized" ;//No I18N
	protected String v3Contextname = "v3Contextname not initialized" ;//No I18N

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
	 * Handles the  JMX  Get Request for enableV3
	 */
	public Integer  getEnableV3()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	enableV3 = agentRef.topoConfImpl.enableSnmpV3ping;
	// User code ends here

		return enableV3;
	}


	/**
	 * Handles the  JMX  Set Request for enableV3
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setEnableV3 (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		// User code starts here
		/*

		enableV3 = value;
		*/
		agentRef.topoConfImpl.enableSnmpV3ping = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for v3Username
	 */
	public String  getV3Username()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	v3Username = agentRef.topoConfImpl.snmpV3UserName;
	// User code ends here

		return v3Username;
	}


	/**
	 * Handles the  JMX  Set Request for v3Username
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setV3Username (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N
			// User code starts here
			/*

		v3Username = value;
		*/
		agentRef.topoConfImpl.snmpV3UserName = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for v3Contextname
	 */
	public String  getV3Contextname()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	v3Contextname = agentRef.topoConfImpl.snmpV3ContextName;
	// User code ends here

		return v3Contextname;
	}


	/**
	 * Handles the  JMX  Set Request for v3Contextname
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setV3Contextname (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N
		// User code starts here
		/*
		v3Contextname = value;
		*/
			agentRef.topoConfImpl.snmpV3ContextName = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}
}


