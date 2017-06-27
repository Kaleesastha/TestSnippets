 //$Id: NetworkEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * networkEntry group
 */

public class NetworkEntry	{


	protected String moNameIndex = "moNameIndex not initialized" ;//No I18N
	protected String discover = "discover not initialized" ;//No I18N
	protected Integer discoverStatus = new Integer(1) ;
	protected String inheritingOid3 = ".1.3.6.1.2.1" ;//No I18N
	protected String inheritingTableName3 = "inheritingTableName3 not initialized" ;//No I18N


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
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		moNameIndex = value;
	}


	/**
	 * Handles the  JMX  Get Request for discover
	 */
	public String  getDiscover()
				 throws AgentException {
		// fill up with necessary processing
		return discover;
	}


	/**
	 * Handles the  JMX  Set Request for discover
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setDiscover (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		discover = value;
	}


	/**
	 * Handles the  JMX  Get Request for discoverStatus
	 */
	public Integer  getDiscoverStatus()
				 throws AgentException {
		// fill up with necessary processing
		return discoverStatus;
	}


	/**
	 * Handles the  JMX  Set Request for discoverStatus
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setDiscoverStatus (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		discoverStatus = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingOid3
	 */
	public String  getInheritingOid3()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingOid3;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingOid3
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingOid3 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		inheritingOid3 = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingTableName3
	 */
	public String  getInheritingTableName3()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingTableName3;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingTableName3
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingTableName3 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		inheritingTableName3 = value;
	}


}

