 //$Id: MoCriteriaEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * Contains the data handling under
 * moCriteriaEntry group
 */

public class MoCriteriaEntry	{


	protected Integer criteriaIndex = new Integer(1) ;
	protected Integer propertyName = new Integer(1) ;
	protected String propertyValue = "propertyValue not initialized" ;//No I18N
	protected Integer allow = new Integer(1) ;


	/**
	 * Handles the  JMX  Get Request for criteriaIndex
	 */
	public Integer  getCriteriaIndex()
				 throws AgentException {
		// fill up with necessary processing
		return criteriaIndex;
	}


	/**
	 * Handles the  JMX  Set Request for criteriaIndex
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setCriteriaIndex (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		criteriaIndex = value;
	}


	/**
	 * Handles the  JMX  Get Request for propertyName
	 */
	public Integer  getPropertyName()
				 throws AgentException {
		// fill up with necessary processing
		return propertyName;
	}


	/**
	 * Handles the  JMX  Set Request for propertyName
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setPropertyName (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)||(value.intValue() == 3)||(value.intValue() == 4)||(value.intValue() == 5)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N

		propertyName = value;
	}


	/**
	 * Handles the  JMX  Get Request for propertyValue
	 */
	public String  getPropertyValue()
				 throws AgentException {
		// fill up with necessary processing
		return propertyValue;
	}


	/**
	 * Handles the  JMX  Set Request for propertyValue
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setPropertyValue (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		propertyValue = value;
	}


	/**
	 * Handles the  JMX  Get Request for allow
	 */
	public Integer  getAllow()
				 throws AgentException {
		// fill up with necessary processing
		return allow;
	}


	/**
	 * Handles the  JMX  Set Request for allow
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setAllow (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N

		allow = value;
	}


}

