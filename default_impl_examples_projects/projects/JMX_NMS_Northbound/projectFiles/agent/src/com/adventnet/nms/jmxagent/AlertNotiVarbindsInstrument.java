 //$Id: AlertNotiVarbindsInstrument.java,v 1.3 2007/07/03 07:02:47 barathv Exp $
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
 * Handles all the requests under AlertNotiVarbinds group
 */
public class AlertNotiVarbindsInstrument {



	protected String alertentity = "" ;//No I18N
	protected String alertownerName = "" ;//No I18N
	protected String alertDescription = "" ;//No I18N
	protected Long  alertTimeStamp = new Long(1) ;
	protected Integer alertNotificationId = new Integer(1) ;
	protected String alertcategory = "" ;//No I18N
	protected String alertExtraProperties = "" ;//No I18N




	/**
	 * Handles the  JMX  Get Request for alertentity
	 */
	public String  getAlertentity()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return alertentity;
	}


	/**
	 * Handles the  JMX  Set Request for alertentity
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setAlertentity (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		alertentity = value;
	}


	/**
	 * Handles the  JMX  Get Request for alertownerName
	 */
	public String  getAlertownerName()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return alertownerName;
	}


	/**
	 * Handles the  JMX  Set Request for alertownerName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setAlertownerName (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		alertownerName = value;
	}


	/**
	 * Handles the  JMX  Get Request for alertDescription
	 */
	public String  getAlertDescription()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return alertDescription;
	}


	/**
	 * Handles the  JMX  Set Request for alertDescription
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setAlertDescription (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		alertDescription = value;
	}


	/**
	 * Handles the  JMX  Get Request for alertTimeStamp
	 */
	public Long  getAlertTimeStamp()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return alertTimeStamp;
	}


	/**
	 * Handles the  JMX  Set Request for alertTimeStamp
	 * @param Long
	 * @throws AgentException on error
	 */
	public synchronized void setAlertTimeStamp (Long  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		alertTimeStamp = value;
	}


	/**
	 * Handles the  JMX  Get Request for alertNotificationId
	 */
	public Integer  getAlertNotificationId()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return alertNotificationId;
	}


	/**
	 * Handles the  JMX  Set Request for alertNotificationId
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setAlertNotificationId (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		alertNotificationId = value;
	}


	/**
	 * Handles the  JMX  Get Request for alertcategory
	 */
	public String  getAlertcategory()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return alertcategory;
	}


	/**
	 * Handles the  JMX  Set Request for alertcategory
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setAlertcategory (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		alertcategory = value;
	}


	/**
	 * Handles the  JMX  Get Request for alertExtraProperties
	 */
	public String  getAlertExtraProperties()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return alertExtraProperties;
	}


	/**
	 * Handles the  JMX  Set Request for alertExtraProperties
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setAlertExtraProperties (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		alertExtraProperties = value;
	}
}

