//$Id: RediscoveryConfigurationInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * Handles all the requests under RediscoveryConfiguration group
 */
public class RediscoveryConfigurationInstrument {



	protected Integer reDiscover = new Integer(1) ;
	protected Integer reDiscoverInterval = new Integer(1) ;
	protected String hour = "hour not initialized" ;//No I18N
	protected String dayOfWeek = "dayOfWeek not initialized" ;//No I18N
	protected String dayOfMonth = "dayOfMonth not initialized" ;//No I18N
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
	 * Handles the  JMX  Get Request for reDiscover
	 */
	public Integer  getReDiscover()
				 throws AgentException
	{
		// Fill up the stub with required processing
			// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	reDiscover = agentRef.topoConfImpl.reDisc;
	// User code ends here

		return reDiscover;
	}


	/**
	 * Handles the  JMX  Set Request for reDiscover
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setReDiscover (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
   		// User code starts here
   		/*
		reDiscover = value;
		*/
			agentRef.topoConfImpl.reDisc = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for reDiscoverInterval
	 */
	public Integer  getReDiscoverInterval()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	reDiscoverInterval = agentRef.topoConfImpl.reDiscInterval;
	// User code ends here

		return reDiscoverInterval;
	}


	/**
	 * Handles the  JMX  Set Request for reDiscoverInterval
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setReDiscoverInterval (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
			// User code starts here
			/*
		reDiscovrInterval = value;
		*/
			agentRef.topoConfImpl.reDiscInterval = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for hour
	 */
	public String  getHour()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	hour = agentRef.topoConfImpl.hour;
	// User code ends here

		return hour;
	}


	/**
	 * Handles the  JMX  Set Request for hour
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setHour (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N
			// User code starts here
			/*

		hour = value;
		*/
			agentRef.topoConfImpl.hour = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for dayOfWeek
	 */
	public String  getDayOfWeek()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	dayOfWeek = agentRef.topoConfImpl.day_of_week;
	// User code ends here

		return dayOfWeek;
	}


	/**
	 * Handles the  JMX  Set Request for dayOfWeek
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setDayOfWeek (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N
			// User code starts here
			/*

		dayOfWeek = value;
		*/
			agentRef.topoConfImpl.day_of_week = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for dayOfMonth
	 */
	public String  getDayOfMonth()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	agentRef.topoConfImpl.loadSeedDatas();
	dayOfMonth = agentRef.topoConfImpl.day_of_month;
	// User code ends here

		return dayOfMonth;
	}


	/**
	 * Handles the  JMX  Set Request for dayOfMonth
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setDayOfMonth (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N
			// User code starts here
			/*

		dayOfMonth = value;
		*/
			agentRef.topoConfImpl.day_of_month = value;
	if(!agentRef.topoConfImpl.setSeedData(1))
	    throw new AgentException("", CommonUtils.GENERR);//No I18N
	// User code ends here

	}
}


