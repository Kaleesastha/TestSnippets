//$Id: SnmpNodeEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * snmpNodeEntry group
 */

public class SnmpNodeEntry	{


	protected String moNameIndex = "moNameIndex not initialized" ;//No I18N
	protected String hostNetMask = "255.255.255.0" ;//No I18N
	protected String sysDesc = "sysDesc not initialized" ;//No I18N
	protected String sysName = "sysName not initialized" ;//No I18N
	protected String sysOid = ".1.3.6.1.2.1" ;//No I18N
	protected String inheritingOid6 = ".1.3.6.1.2.1" ;//No I18N
	protected String inheritingTableName6 = "inheritingTableName6 not initialized" ;//No I18N


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
	 * Handles the  JMX  Get Request for hostNetMask
	 */
	public String  getHostNetMask()
				 throws AgentException {
		// fill up with necessary processing
		return hostNetMask;
	}


	/**
	 * Handles the  JMX  Set Request for hostNetMask
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setHostNetMask (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		StringTokenizer tok =  new StringTokenizer(value,".",true);//No I18N
		if(tok.countTokens() > ((4*2) -1))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		hostNetMask = value;
	}


	/**
	 * Handles the  JMX  Get Request for sysDesc
	 */
	public String  getSysDesc()
				 throws AgentException {
		// fill up with necessary processing
		return sysDesc;
	}


	/**
	 * Handles the  JMX  Set Request for sysDesc
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setSysDesc (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		sysDesc = value;
	}


	/**
	 * Handles the  JMX  Get Request for sysName
	 */
	public String  getSysName()
				 throws AgentException {
		// fill up with necessary processing
		return sysName;
	}


	/**
	 * Handles the  JMX  Set Request for sysName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setSysName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		sysName = value;
	}


	/**
	 * Handles the  JMX  Get Request for sysOid
	 */
	public String  getSysOid()
				 throws AgentException {
		// fill up with necessary processing
		return sysOid;
	}


	/**
	 * Handles the  JMX  Set Request for sysOid
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setSysOid (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		sysOid = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingOid6
	 */
	public String  getInheritingOid6()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingOid6;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingOid6
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingOid6 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		inheritingOid6 = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingTableName6
	 */
	public String  getInheritingTableName6()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingTableName6;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingTableName6
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingTableName6 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		inheritingTableName6 = value;
	}


}

