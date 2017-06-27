 //$Id: SnmpInterfaceEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Fri Mar 09 14:26:07 IST 2007
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
 * snmpInterfaceEntry group
 */

public class SnmpInterfaceEntry	{


	protected String moNameIndex = "moNameIndex not initialized" ;//No I18N
	protected String hostnetMask = "255.255.255.0" ;//No I18N
	protected Integer ifIndex = new Integer(1) ;
	protected String physMedia = "physMedia not initialized" ;//No I18N
	protected String physAddress = "physAddress not initialized" ;//No I18N
	protected Integer ifSpeed = new Integer(1) ;
	protected String ifDesc = "ifDesc not initialized" ;//No I18N
	protected String sysOID = ".1.3.6.1.2.1" ;//No I18N
	protected String inheritingOid7 = ".1.3.6.1.2.1" ;//No I18N
	protected String inheritingTableName7 = "inheritingTableName7 not initialized" ;//No I18N


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
	 * Handles the  JMX  Get Request for hostnetMask
	 */
	public String  getHostnetMask()
				 throws AgentException {
		// fill up with necessary processing
		return hostnetMask;
	}


	/**
	 * Handles the  JMX  Set Request for hostnetMask
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setHostnetMask (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		StringTokenizer tok =  new StringTokenizer(value,".",true);//No I18N
		if(tok.countTokens() > ((4*2) -1))
			throw new AgentException("", CommonUtils.WRONGVALUE);
		hostnetMask = value;
	}


	/**
	 * Handles the  JMX  Get Request for ifIndex
	 */
	public Integer  getIfIndex()
				 throws AgentException {
		// fill up with necessary processing
		return ifIndex;
	}


	/**
	 * Handles the  JMX  Set Request for ifIndex
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setIfIndex (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		ifIndex = value;
	}


	/**
	 * Handles the  JMX  Get Request for physMedia
	 */
	public String  getPhysMedia()
				 throws AgentException {
		// fill up with necessary processing
		return physMedia;
	}


	/**
	 * Handles the  JMX  Set Request for physMedia
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setPhysMedia (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		physMedia = value;
	}


	/**
	 * Handles the  JMX  Get Request for physAddress
	 */
	public String  getPhysAddress()
				 throws AgentException {
		// fill up with necessary processing
		return physAddress;
	}


	/**
	 * Handles the  JMX  Set Request for physAddress
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setPhysAddress (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		physAddress = value;
	}


	/**
	 * Handles the  JMX  Get Request for ifSpeed
	 */
	public Integer  getIfSpeed()
				 throws AgentException {
		// fill up with necessary processing
		return ifSpeed;
	}


	/**
	 * Handles the  JMX  Set Request for ifSpeed
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setIfSpeed (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		ifSpeed = value;
	}


	/**
	 * Handles the  JMX  Get Request for ifDesc
	 */
	public String  getIfDesc()
				 throws AgentException {
		// fill up with necessary processing
		return ifDesc;
	}


	/**
	 * Handles the  JMX  Set Request for ifDesc
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setIfDesc (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		ifDesc = value;
	}


	/**
	 * Handles the  JMX  Get Request for sysOID
	 */
	public String  getSysOID()
				 throws AgentException {
		// fill up with necessary processing
		return sysOID;
	}


	/**
	 * Handles the  JMX  Set Request for sysOID
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setSysOID (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		sysOID = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingOid7
	 */
	public String  getInheritingOid7()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingOid7;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingOid7
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingOid7 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		inheritingOid7 = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingTableName7
	 */
	public String  getInheritingTableName7()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingTableName7;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingTableName7
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingTableName7 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		inheritingTableName7 = value;
	}


}

