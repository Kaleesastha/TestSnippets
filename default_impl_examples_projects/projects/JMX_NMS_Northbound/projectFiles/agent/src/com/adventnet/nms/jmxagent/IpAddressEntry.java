  //$Id: IpAddressEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * ipAddressEntry group
 */

public class IpAddressEntry	{


	protected String moNameIndex = "moNameIndex not initialized" ;//No I18N
	protected String parentNode = "parentNode not initialized" ;//No I18N
	protected String parentNet = "255.255.255.0" ;//No I18N
	protected String inheritingOid5 = ".1.3.6.1.2.1" ;//No I18N
	protected String inheritingTableName5 = "inheritingTableName5 not initialized" ;//No I18N


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
	 * Handles the  JMX  Get Request for parentNode
	 */
	public String  getParentNode()
				 throws AgentException {
		// fill up with necessary processing
		return parentNode;
	}


	/**
	 * Handles the  JMX  Set Request for parentNode
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setParentNode (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		parentNode = value;
	}


	/**
	 * Handles the  JMX  Get Request for parentNet
	 */
	public String  getParentNet()
				 throws AgentException {
		// fill up with necessary processing
		return parentNet;
	}


	/**
	 * Handles the  JMX  Set Request for parentNet
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setParentNet (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		StringTokenizer tok =  new StringTokenizer(value,".",true);//No I18N
		if(tok.countTokens() > ((4*2) -1))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		parentNet = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingOid5
	 */
	public String  getInheritingOid5()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingOid5;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingOid5
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingOid5 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		inheritingOid5 = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingTableName5
	 */
	public String  getInheritingTableName5()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingTableName5;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingTableName5
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingTableName5 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		inheritingTableName5 = value;
	}


}

