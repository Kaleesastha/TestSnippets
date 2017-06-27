//$Id: AlertEntry.java,v 1.3 2007/07/03 07:01:22 barathv Exp $
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
 * @Version :  6.0.0 Wed Jun 13 11:34:57 IST 2007
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
 * alertEntry group
 */ 

public class AlertEntry	{


	protected String alertEntity = "alertEntity not initialized" ; // no i18n
	protected Long  alertCreateTime = new Long(0) ;
	protected String alertSource = "alertSource not initialized" ; // no i18n
	protected Long  alertModTime = new Long(0) ;
	protected Integer alertSeverity = new Integer(1) ;
	protected Integer alertPreviousSeverity = new Integer(1) ;
	protected String alertCategory = "alertCategory not initialized" ; // no i18n
	protected String alertUserProperties = "alertUserProperties not initialized" ; // no i18n


	/** 
	 * Handles the  JMX  Get Request for alertEntity
	 */ 
	public String  getAlertEntity()
				 throws AgentException {
		// fill up with necessary processing
		return alertEntity;
	}


	/** 
	 * Handles the  JMX  Set Request for alertEntity
	 * @param String  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlertEntity (String  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH); // no i18n

		alertEntity = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alertCreateTime
	 */ 
	public Long  getAlertCreateTime()
				 throws AgentException {
		// fill up with necessary processing
		return alertCreateTime;
	}


	/** 
	 * Handles the  JMX  Set Request for alertCreateTime
	 * @param Long  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlertCreateTime (Long  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		alertCreateTime = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alertSource
	 */ 
	public String  getAlertSource()
				 throws AgentException {
		// fill up with necessary processing
		return alertSource;
	}


	/** 
	 * Handles the  JMX  Set Request for alertSource
	 * @param String  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlertSource (String  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH); // no i18n

		alertSource = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alertModTime
	 */ 
	public Long  getAlertModTime()
				 throws AgentException {
		// fill up with necessary processing
		return alertModTime;
	}


	/** 
	 * Handles the  JMX  Set Request for alertModTime
	 * @param Long  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlertModTime (Long  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		alertModTime = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alertSeverity
	 */ 
	public Integer  getAlertSeverity()
				 throws AgentException {
		// fill up with necessary processing
		return alertSeverity;
	}


	/** 
	 * Handles the  JMX  Set Request for alertSeverity
	 * @param Integer  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlertSeverity (Integer  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		alertSeverity = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alertPreviousSeverity
	 */ 
	public Integer  getAlertPreviousSeverity()
				 throws AgentException {
		// fill up with necessary processing
		return alertPreviousSeverity;
	}


	/** 
	 * Handles the  JMX  Set Request for alertPreviousSeverity
	 * @param Integer  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlertPreviousSeverity (Integer  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		alertPreviousSeverity = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alertCategory
	 */ 
	public String  getAlertCategory()
				 throws AgentException {
		// fill up with necessary processing
		return alertCategory;
	}


	/** 
	 * Handles the  JMX  Set Request for alertCategory
	 * @param String  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlertCategory (String  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH); // no i18n

		alertCategory = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alertUserProperties
	 */ 
	public String  getAlertUserProperties()
				 throws AgentException {
		// fill up with necessary processing
		return alertUserProperties;
	}


	/** 
	 * Handles the  JMX  Set Request for alertUserProperties
	 * @param String  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlertUserProperties (String  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH); // no i18n

		alertUserProperties = value;
	}


}
