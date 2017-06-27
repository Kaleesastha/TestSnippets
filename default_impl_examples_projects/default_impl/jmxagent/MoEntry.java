 //$Id: MoEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Fri Mar 09 14:26:06 IST 2007
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
 * moEntry group
 */

public class MoEntry	{


	protected String moNameIndex = "moNameIndex not initialized" ;//No I18N
	protected String moOwnerName = "moOwnerName not initialized" ;//No I18N
	protected String moType = "moType not initialized" ;//No I18N
	protected Integer moFailureCount = new Integer(1) ;
	protected Integer moFailureThreshold = new Integer(1) ;
	protected Integer moManaged = new Integer(1) ;
	protected Integer moStatus = new Integer(1) ;
	protected Long  moStatusChangeTime = new Long(0) ;
	protected Long  moStatusUpdateTime = new Long(0) ;
	protected Long  moPollInterval = new Long(0) ;
	protected String moUserProperties = "moUserProperties not initialized" ;//No I18N
	protected String moDerivedProperties = "moDerivedProperties not initialized" ;//No I18N
	protected String inheritingOid1 = ".1.3.6.1.2.1" ;//No I18N
	protected String inheritingTableName1 = "inheritingTableName1 not initialized" ;//No I18N


	/**
	 * Handles the  JMX  Get Request for moNameIndex
	 */
	public String  getMoNameIndex()
				 throws AgentException {
		// fill up with necessary processing
		return moNameIndex;
	}


	/**
	 * Handles the  JMX  Set Request for moNameIndex
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoNameIndex (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		moNameIndex = value;
	}


	/**
	 * Handles the  JMX  Get Request for moOwnerName
	 */
	public String  getMoOwnerName()
				 throws AgentException {
		// fill up with necessary processing
		return moOwnerName;
	}


	/**
	 * Handles the  JMX  Set Request for moOwnerName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoOwnerName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		moOwnerName = value;
	}


	/**
	 * Handles the  JMX  Get Request for moType
	 */
	public String  getMoType()
				 throws AgentException {
		// fill up with necessary processing
		return moType;
	}


	/**
	 * Handles the  JMX  Set Request for moType
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoType (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		moType = value;
	}


	/**
	 * Handles the  JMX  Get Request for moFailureCount
	 */
	public Integer  getMoFailureCount()
				 throws AgentException {
		// fill up with necessary processing
		return moFailureCount;
	}


	/**
	 * Handles the  JMX  Set Request for moFailureCount
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setMoFailureCount (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		moFailureCount = value;
	}


	/**
	 * Handles the  JMX  Get Request for moFailureThreshold
	 */
	public Integer  getMoFailureThreshold()
				 throws AgentException {
		// fill up with necessary processing
		return moFailureThreshold;
	}


	/**
	 * Handles the  JMX  Set Request for moFailureThreshold
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setMoFailureThreshold (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		moFailureThreshold = value;
	}


	/**
	 * Handles the  JMX  Get Request for moManaged
	 */
	public Integer  getMoManaged()
				 throws AgentException {
		// fill up with necessary processing
		return moManaged;
	}


	/**
	 * Handles the  JMX  Set Request for moManaged
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setMoManaged (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);

		moManaged = value;
	}


	/**
	 * Handles the  JMX  Get Request for moStatus
	 */
	public Integer  getMoStatus()
				 throws AgentException {
		// fill up with necessary processing
		return moStatus;
	}


	/**
	 * Handles the  JMX  Set Request for moStatus
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setMoStatus (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		moStatus = value;
	}


	/**
	 * Handles the  JMX  Get Request for moStatusChangeTime
	 */
	public Long  getMoStatusChangeTime()
				 throws AgentException {
		// fill up with necessary processing
		return moStatusChangeTime;
	}


	/**
	 * Handles the  JMX  Set Request for moStatusChangeTime
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setMoStatusChangeTime (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		moStatusChangeTime = value;
	}


	/**
	 * Handles the  JMX  Get Request for moStatusUpdateTime
	 */
	public Long  getMoStatusUpdateTime()
				 throws AgentException {
		// fill up with necessary processing
		return moStatusUpdateTime;
	}


	/**
	 * Handles the  JMX  Set Request for moStatusUpdateTime
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setMoStatusUpdateTime (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		moStatusUpdateTime = value;
	}


	/**
	 * Handles the  JMX  Get Request for moPollInterval
	 */
	public Long  getMoPollInterval()
				 throws AgentException {
		// fill up with necessary processing
		return moPollInterval;
	}


	/**
	 * Handles the  JMX  Set Request for moPollInterval
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setMoPollInterval (Long  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		moPollInterval = value;
	}


	/**
	 * Handles the  JMX  Get Request for moUserProperties
	 */
	public String  getMoUserProperties()
				 throws AgentException {
		// fill up with necessary processing
		return moUserProperties;
	}


	/**
	 * Handles the  JMX  Set Request for moUserProperties
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoUserProperties (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		moUserProperties = value;
	}


	/**
	 * Handles the  JMX  Get Request for moDerivedProperties
	 */
	public String  getMoDerivedProperties()
				 throws AgentException {
		// fill up with necessary processing
		return moDerivedProperties;
	}


	/**
	 * Handles the  JMX  Set Request for moDerivedProperties
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoDerivedProperties (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		moDerivedProperties = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingOid1
	 */
	public String  getInheritingOid1()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingOid1;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingOid1
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingOid1 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		inheritingOid1 = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingTableName1
	 */
	public String  getInheritingTableName1()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingTableName1;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingTableName1
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingTableName1 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		inheritingTableName1 = value;
	}


}

