//$Id: VarbindLogEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Thu Feb 22 12:16:11 IST 2007
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
 * varbindLogEntry group
 */

public class VarbindLogEntry	{


	protected Integer notiLogIndex = new Integer(1) ;
	protected Integer varbindIndex = new Integer(1) ;
	protected Integer varbindType = new Integer(1) ;
	protected String varbindValue = "varbindValue not initialized" ;//No I18N


	/**
	 * Handles the  JMX  Get Request for notiLogIndex
	 */
	public Integer  getNotiLogIndex()
				 throws AgentException {
		// fill up with necessary processing
		return notiLogIndex;
	}


	/**
	 * Handles the  JMX  Set Request for notiLogIndex
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setNotiLogIndex (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		notiLogIndex = value;
	}


	/**
	 * Handles the  JMX  Get Request for varbindIndex
	 */
	public Integer  getVarbindIndex()
				 throws AgentException {
		// fill up with necessary processing
		return varbindIndex;
	}


	/**
	 * Handles the  JMX  Set Request for varbindIndex
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setVarbindIndex (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		varbindIndex = value;
	}


	/**
	 * Handles the  JMX  Get Request for varbindType
	 */
	public Integer  getVarbindType()
				 throws AgentException {
		// fill up with necessary processing
		return varbindType;
	}


	/**
	 * Handles the  JMX  Set Request for varbindType
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setVarbindType (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)||(value.intValue() == 3)||(value.intValue() == 4)||(value.intValue() == 5)||(value.intValue() == 6)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N

		varbindType = value;
	}


	/**
	 * Handles the  JMX  Get Request for varbindValue
	 */
	public String  getVarbindValue()
				 throws AgentException {
		// fill up with necessary processing
		return varbindValue;
	}


	/**
	 * Handles the  JMX  Set Request for varbindValue
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setVarbindValue (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		varbindValue = value;
	}


}

