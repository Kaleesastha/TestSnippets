//$Id: TrapFilterEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Thu Feb 22 12:16:12 IST 2007
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
 * trapFilterEntry group
 */

public class TrapFilterEntry	{


	protected Integer trapFilterIndex = new Integer(1) ;
	protected String alertFilterClassName = "alertFilterClassName not initialized" ;//No I18N
	protected String trapFilterName = "trapFilterName not initialized" ;//No I18N
	protected Integer trapFilterEnable = new Integer(1) ;
	protected String genericType = "genericType not initialized" ;//No I18N
	protected String specificType = "specificType not initialized" ;//No I18N
	protected String enterpriseOID = "enterpriseOID not initialized" ;//No I18N
	protected String trapOID = "trapOID not initialized" ;//No I18N
	protected Integer setFilter = new Integer(1) ;
 // User code starts here
    public TrapFilterEntry()
    {
        trapFilterIndex = new Integer(1) ;
        alertFilterClassName = "" ;//No I18N
        trapFilterName = "" ;//No I18N
        trapFilterEnable = new Integer(1) ;
        genericType = "" ;//No I18N
        specificType = "" ;//No I18N
        enterpriseOID = "" ;//No I18N
        trapOID = "" ;//No I18N
        setFilter = new Integer(0) ;
    }
    // User code ends here

	/**
	 * Handles the  JMX  Get Request for trapFilterIndex
	 */
	public Integer  getTrapFilterIndex()
				 throws AgentException {
		// fill up with necessary processing
		return trapFilterIndex;
	}


	/**
	 * Handles the  JMX  Set Request for trapFilterIndex
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setTrapFilterIndex (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		trapFilterIndex = value;
	}


	/**
	 * Handles the  JMX  Get Request for alertFilterClassName
	 */
	public String  getAlertFilterClassName()
				 throws AgentException {
		// fill up with necessary processing
		return alertFilterClassName;
	}


	/**
	 * Handles the  JMX  Set Request for alertFilterClassName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setAlertFilterClassName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		alertFilterClassName = value;
	}


	/**
	 * Handles the  JMX  Get Request for trapFilterName
	 */
	public String  getTrapFilterName()
				 throws AgentException {
		// fill up with necessary processing
		return trapFilterName;
	}


	/**
	 * Handles the  JMX  Set Request for trapFilterName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setTrapFilterName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		trapFilterName = value;
	}


	/**
	 * Handles the  JMX  Get Request for trapFilterEnable
	 */
	public Integer  getTrapFilterEnable()
				 throws AgentException {
		// fill up with necessary processing
		return trapFilterEnable;
	}


	/**
	 * Handles the  JMX  Set Request for trapFilterEnable
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setTrapFilterEnable (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N

		trapFilterEnable = value;
	}


	/**
	 * Handles the  JMX  Get Request for genericType
	 */
	public String  getGenericType()
				 throws AgentException {
		// fill up with necessary processing
		return genericType;
	}


	/**
	 * Handles the  JMX  Set Request for genericType
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setGenericType (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		genericType = value;
	}


	/**
	 * Handles the  JMX  Get Request for specificType
	 */
	public String  getSpecificType()
				 throws AgentException {
		// fill up with necessary processing
		return specificType;
	}


	/**
	 * Handles the  JMX  Set Request for specificType
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setSpecificType (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		specificType = value;
	}


	/**
	 * Handles the  JMX  Get Request for enterpriseOID
	 */
	public String  getEnterpriseOID()
				 throws AgentException {
		// fill up with necessary processing
		return enterpriseOID;
	}


	/**
	 * Handles the  JMX  Set Request for enterpriseOID
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setEnterpriseOID (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		enterpriseOID = value;
	}


	/**
	 * Handles the  JMX  Get Request for trapOID
	 */
	public String  getTrapOID()
				 throws AgentException {
		// fill up with necessary processing
		return trapOID;
	}


	/**
	 * Handles the  JMX  Set Request for trapOID
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setTrapOID (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		trapOID = value;
	}


	/**
	 * Handles the  JMX  Get Request for setFilter
	 */
	public Integer  getSetFilter()
				 throws AgentException {
		// fill up with necessary processing
		return setFilter;
	}


	/**
	 * Handles the  JMX  Set Request for setFilter
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setSetFilter (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N

		setFilter = value;
	}


}


