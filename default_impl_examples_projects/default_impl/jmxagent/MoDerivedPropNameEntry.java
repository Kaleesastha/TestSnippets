 //$Id: MoDerivedPropNameEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Wed Feb 14 00:34:08 IST 2007
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
 * moDerivedPropNameEntry group
 */

public class MoDerivedPropNameEntry	{


	protected Integer indexNum = new Integer(1) ;
	protected String objClassName = "objClassName not initialized" ;//No I18N
	protected String derivedPropNames = "derivedPropNames not initialized" ;//No I18N
	protected String tableOid = ".1.3.6.1.2.1" ;//No I18N
	protected String tableName = "tableName not initialized" ;//No I18N


	/**
	 * Handles the  JMX  Get Request for indexNum
	 */
	public Integer  getIndexNum()
				 throws AgentException {
		// fill up with necessary processing
		return indexNum;
	}


	/**
	 * Handles the  JMX  Set Request for indexNum
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setIndexNum (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		indexNum = value;
	}


	/**
	 * Handles the  JMX  Get Request for objClassName
	 */
	public String  getObjClassName()
				 throws AgentException {
		// fill up with necessary processing
		return objClassName;
	}


	/**
	 * Handles the  JMX  Set Request for objClassName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setObjClassName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		objClassName = value;
	}


	/**
	 * Handles the  JMX  Get Request for derivedPropNames
	 */
	public String  getDerivedPropNames()
				 throws AgentException {
		// fill up with necessary processing
		return derivedPropNames;
	}


	/**
	 * Handles the  JMX  Set Request for derivedPropNames
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setDerivedPropNames (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		derivedPropNames = value;
	}


	/**
	 * Handles the  JMX  Get Request for tableOid
	 */
	public String  getTableOid()
				 throws AgentException {
		// fill up with necessary processing
		return tableOid;
	}


	/**
	 * Handles the  JMX  Set Request for tableOid
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setTableOid (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		tableOid = value;
	}


	/**
	 * Handles the  JMX  Get Request for tableName
	 */
	public String  getTableName()
				 throws AgentException {
		// fill up with necessary processing
		return tableName;
	}


	/**
	 * Handles the  JMX  Set Request for tableName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setTableName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		tableName = value;
	}


}

