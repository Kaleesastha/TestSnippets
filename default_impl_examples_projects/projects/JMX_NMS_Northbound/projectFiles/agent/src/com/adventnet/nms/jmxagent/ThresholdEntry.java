//$Id: ThresholdEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * thresholdEntry group
 */

public class ThresholdEntry	{


	protected String thresholdObjectName = "thresholdObjectName not initialized" ;//No I18N
	protected String thresholdKind = "thresholdKind not initialized" ;//No I18N
	protected String thresholdMessage = "thresholdMessage not initialized" ;//No I18N
	protected String thresholdClearMessage = "thresholdClearMessage not initialized" ;//No I18N
	protected Integer thresholdSeverity = new Integer(1) ;
	protected String thresholdCategory = "thresholdCategory not initialized" ;//No I18N


	/**
	 * Handles the  JMX  Get Request for thresholdObjectName
	 */
	public String  getThresholdObjectName()
				 throws AgentException {
		// fill up with necessary processing
		return thresholdObjectName;
	}


	/**
	 * Handles the  JMX  Set Request for thresholdObjectName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setThresholdObjectName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		thresholdObjectName = value;
	}


	/**
	 * Handles the  JMX  Get Request for thresholdKind
	 */
	public String  getThresholdKind()
				 throws AgentException {
		// fill up with necessary processing
		return thresholdKind;
	}


	/**
	 * Handles the  JMX  Set Request for thresholdKind
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setThresholdKind (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
 if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		thresholdKind = value;
	}


	/**
	 * Handles the  JMX  Get Request for thresholdMessage
	 */
	public String  getThresholdMessage()
				 throws AgentException {
		// fill up with necessary processing
		return thresholdMessage;
	}


	/**
	 * Handles the  JMX  Set Request for thresholdMessage
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setThresholdMessage (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		thresholdMessage = value;
	}


	/**
	 * Handles the  JMX  Get Request for thresholdClearMessage
	 */
	public String  getThresholdClearMessage()
				 throws AgentException {
		// fill up with necessary processing
		return thresholdClearMessage;
	}


	/**
	 * Handles the  JMX  Set Request for thresholdClearMessage
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setThresholdClearMessage (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		thresholdClearMessage = value;
	}


	/**
	 * Handles the  JMX  Get Request for thresholdSeverity
	 */
	public Integer  getThresholdSeverity()
				 throws AgentException {
		// fill up with necessary processing
		return thresholdSeverity;
	}


	/**
	 * Handles the  JMX  Set Request for thresholdSeverity
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setThresholdSeverity (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		thresholdSeverity = value;
	}


	/**
	 * Handles the  JMX  Get Request for thresholdCategory
	 */
	public String  getThresholdCategory()
				 throws AgentException {
		// fill up with necessary processing
		return thresholdCategory;
	}


	/**
	 * Handles the  JMX  Set Request for thresholdCategory
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setThresholdCategory (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		thresholdCategory = value;
	}


}

