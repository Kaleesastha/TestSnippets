//$Id: AlarmEntry.java,v 1.3 2007/07/03 06:59:53 barathv Exp $
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
 * alarmEntry group
 */ 

public class AlarmEntry	{


	protected String alarmSource = "alarmSource not initialized" ; // no i18n
	protected String alarmEntity = "alarmEntity not initialized" ;  // no i18n
	protected Integer alarmSeverity = new Integer(1) ;
	protected Integer alarmPreviousSeverity = new Integer(1) ;
	protected Long  alarmCreateTime = new Long(0) ;
	protected Long  alarmModTime = new Long(0) ;
	protected String alarmCategory = "alarmCategory not initialized" ;  // no i18n
	protected String alarmAssignedTo = "alarmAssignedTo not initialized" ;  // no i18n
	protected String alarmUserProperties = "alarmUserProperties not initialized" ;  // no i18n


	/** 
	 * Handles the  JMX  Get Request for alarmSource
	 */ 
	public String  getAlarmSource()
				 throws AgentException {
		// fill up with necessary processing
		return alarmSource;
	}


	/** 
	 * Handles the  JMX  Set Request for alarmSource
	 * @param String  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlarmSource (String  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);  // no i18n
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);  // no i18n

		alarmSource = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alarmEntity
	 */ 
	public String  getAlarmEntity()
				 throws AgentException {
		// fill up with necessary processing
		return alarmEntity;
	}


	/** 
	 * Handles the  JMX  Set Request for alarmEntity
	 * @param String  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlarmEntity (String  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);  // no i18n
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);  // no i18n

		alarmEntity = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alarmSeverity
	 */ 
	public Integer  getAlarmSeverity()
				 throws AgentException {
		// fill up with necessary processing
		return alarmSeverity;
	}


	/** 
	 * Handles the  JMX  Set Request for alarmSeverity
	 * @param Integer  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlarmSeverity (Integer  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);  // no i18n
		alarmSeverity = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alarmPreviousSeverity
	 */ 
	public Integer  getAlarmPreviousSeverity()
				 throws AgentException {
		// fill up with necessary processing
		return alarmPreviousSeverity;
	}


	/** 
	 * Handles the  JMX  Set Request for alarmPreviousSeverity
	 * @param Integer  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlarmPreviousSeverity (Integer  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);  // no i18n
		alarmPreviousSeverity = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alarmCreateTime
	 */ 
	public Long  getAlarmCreateTime()
				 throws AgentException {
		// fill up with necessary processing
		return alarmCreateTime;
	}


	/** 
	 * Handles the  JMX  Set Request for alarmCreateTime
	 * @param Long  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlarmCreateTime (Long  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null) 
			throw new AgentException("", CommonUtils.WRONGVALUE);  // no i18n
		alarmCreateTime = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alarmModTime
	 */ 
	public Long  getAlarmModTime()
				 throws AgentException {
		// fill up with necessary processing
		return alarmModTime;
	}


	/** 
	 * Handles the  JMX  Set Request for alarmModTime
	 * @param Long  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlarmModTime (Long  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);  // no i18n
		alarmModTime = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alarmCategory
	 */ 
	public String  getAlarmCategory()
				 throws AgentException {
		// fill up with necessary processing
		return alarmCategory;
	}


	/** 
	 * Handles the  JMX  Set Request for alarmCategory
	 * @param String  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlarmCategory (String  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);  // no i18n
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);  // no i18n

		alarmCategory = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alarmAssignedTo
	 */ 
	public String  getAlarmAssignedTo()
				 throws AgentException {
		// fill up with necessary processing
		return alarmAssignedTo;
	}


	/** 
	 * Handles the  JMX  Set Request for alarmAssignedTo
	 * @param String  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlarmAssignedTo (String  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);  // no i18n
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);  // no i18n 

		alarmAssignedTo = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alarmUserProperties
	 */ 
	public String  getAlarmUserProperties()
				 throws AgentException {
		// fill up with necessary processing
		return alarmUserProperties;
	}


	/** 
	 * Handles the  JMX  Set Request for alarmUserProperties
	 * @param String  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlarmUserProperties (String  value ) 
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);  // no i18n
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);  // no i18n

		alarmUserProperties = value;
	}


}
