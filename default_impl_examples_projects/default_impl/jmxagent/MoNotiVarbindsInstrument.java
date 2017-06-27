 //$Id: MoNotiVarbindsInstrument.java,v 1.3 2007/07/03 07:04:37 barathv Exp $
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
 * @Version :  6.0.0 Wed Feb 14 00:34:11 IST 2007
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
 * Handles all the requests under MoNotiVarbinds group
 */
public class MoNotiVarbindsInstrument {



	protected String moName = "" ;//No I18N
	protected String moownerName = "" ;//No I18N
	protected String moNodeType = "" ;//No I18N
	protected Long  moEnrolTime = new Long(1) ;
	protected Long  moDeEnrolTime = new Long(1) ;
	protected Long  moDataChangeTime = new Long(1) ;
	protected String moExtraProperties = "" ;//No I18N




	/**
	 * Handles the  JMX  Get Request for moName
	 */
	public String  getMoName()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return moName;
	}


	/**
	 * Handles the  JMX  Set Request for moName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoName (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		moName = value;
	}


	/**
	 * Handles the  JMX  Get Request for moownerName
	 */
	public String  getMoownerName()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return moownerName;
	}


	/**
	 * Handles the  JMX  Set Request for moownerName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoownerName (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		moownerName = value;
	}


	/**
	 * Handles the  JMX  Get Request for moNodeType
	 */
	public String  getMoNodeType()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return moNodeType;
	}


	/**
	 * Handles the  JMX  Set Request for moNodeType
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoNodeType (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		moNodeType = value;
	}


	/**
	 * Handles the  JMX  Get Request for moEnrolTime
	 */
	public Long  getMoEnrolTime()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return moEnrolTime;
	}


	/**
	 * Handles the  JMX  Set Request for moEnrolTime
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setMoEnrolTime (Long  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		moEnrolTime = value;
	}


	/**
	 * Handles the  JMX  Get Request for moDeEnrolTime
	 */
	public Long  getMoDeEnrolTime()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return moDeEnrolTime;
	}


	/**
	 * Handles the  JMX  Set Request for moDeEnrolTime
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setMoDeEnrolTime (Long  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		moDeEnrolTime = value;
	}


	/**
	 * Handles the  JMX  Get Request for moDataChangeTime
	 */
	public Long  getMoDataChangeTime()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return moDataChangeTime;
	}


	/**
	 * Handles the  JMX  Set Request for moDataChangeTime
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setMoDataChangeTime (Long  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		moDataChangeTime = value;
	}


	/**
	 * Handles the  JMX  Get Request for moExtraProperties
	 */
	public String  getMoExtraProperties()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return moExtraProperties;
	}


	/**
	 * Handles the  JMX  Set Request for moExtraProperties
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoExtraProperties (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		moExtraProperties = value;
	}
}

