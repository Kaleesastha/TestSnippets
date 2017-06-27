//$Id: NodeEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * nodeEntry group
 */

public class NodeEntry	{


	protected String moNameIndex = "moNameIndex not initialized" ;//No I18N
	protected String isRouter = "isRouter not initialized" ;//No I18N
	protected String inheritingOid4 = ".1.3.6.1.2.1" ;//No I18N
	protected String inheritingTableName4 = "inheritingTableName4 not initialized" ;//No I18N


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
	 * Handles the  JMX  Get Request for isRouter
	 */
	public String  getIsRouter()
				 throws AgentException {
		// fill up with necessary processing
		return isRouter;
	}


	/**
	 * Handles the  JMX  Set Request for isRouter
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setIsRouter (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		isRouter = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingOid4
	 */
	public String  getInheritingOid4()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingOid4;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingOid4
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingOid4 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		inheritingOid4 = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingTableName4
	 */
	public String  getInheritingTableName4()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingTableName4;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingTableName4
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingTableName4 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		inheritingTableName4 = value;
	}


}

