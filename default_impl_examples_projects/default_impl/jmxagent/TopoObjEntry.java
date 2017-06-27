//$Id: TopoObjEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * topoObjEntry group
 */

public class TopoObjEntry	{


	protected String moNameIndex = "moNameIndex not initialized" ;//No I18N
	protected String ipAddress = "255.255.255.0" ;//No I18N
	protected String netmask = "255.255.255.0" ;//No I18N
	protected String moCommunity = "moCommunity not initialized" ;//No I18N
	protected String moWriteCommunity = "moWriteCommunity not initialized" ;//No I18N
	protected Integer snmpPort = new Integer(1) ;
	protected String isDHCP = "isDHCP not initialized" ;//No I18N
	protected String baseMibs = "baseMibs not initialized" ;//No I18N
	protected String version = "version not initialized" ;//No I18N
	protected String userName = "userName not initialized" ;//No I18N
	protected String contextName = "contextName not initialized" ;//No I18N
	protected String inheritingOid2 = ".1.3.6.1.2.1" ;//No I18N
	protected String inheritingTableName2 = "inheritingTableName2 not initialized" ;//No I18N


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
	 * Handles the  JMX  Get Request for ipAddress
	 */
	public String  getIpAddress()
				 throws AgentException {
		// fill up with necessary processing
		return ipAddress;
	}


	/**
	 * Handles the  JMX  Set Request for ipAddress
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setIpAddress (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		StringTokenizer tok =  new StringTokenizer(value,".",true);//No I18N
		if(tok.countTokens() > ((4*2) -1))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		ipAddress = value;
	}


	/**
	 * Handles the  JMX  Get Request for netmask
	 */
	public String  getNetmask()
				 throws AgentException {
		// fill up with necessary processing
		return netmask;
	}


	/**
	 * Handles the  JMX  Set Request for netmask
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setNetmask (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		StringTokenizer tok =  new StringTokenizer(value,".",true);//No I18N
		if(tok.countTokens() > ((4*2) -1))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		netmask = value;
	}


	/**
	 * Handles the  JMX  Get Request for moCommunity
	 */
	public String  getMoCommunity()
				 throws AgentException {
		// fill up with necessary processing
		return moCommunity;
	}


	/**
	 * Handles the  JMX  Set Request for moCommunity
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoCommunity (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		moCommunity = value;
	}


	/**
	 * Handles the  JMX  Get Request for moWriteCommunity
	 */
	public String  getMoWriteCommunity()
				 throws AgentException {
		// fill up with necessary processing
		return moWriteCommunity;
	}


	/**
	 * Handles the  JMX  Set Request for moWriteCommunity
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoWriteCommunity (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		moWriteCommunity = value;
	}


	/**
	 * Handles the  JMX  Get Request for snmpPort
	 */
	public Integer  getSnmpPort()
				 throws AgentException {
		// fill up with necessary processing
		return snmpPort;
	}


	/**
	 * Handles the  JMX  Set Request for snmpPort
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setSnmpPort (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		snmpPort = value;
	}


	/**
	 * Handles the  JMX  Get Request for isDHCP
	 */
	public String  getIsDHCP()
				 throws AgentException {
		// fill up with necessary processing
		return isDHCP;
	}


	/**
	 * Handles the  JMX  Set Request for isDHCP
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setIsDHCP (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		isDHCP = value;
	}


	/**
	 * Handles the  JMX  Get Request for baseMibs
	 */
	public String  getBaseMibs()
				 throws AgentException {
		// fill up with necessary processing
		return baseMibs;
	}


	/**
	 * Handles the  JMX  Set Request for baseMibs
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setBaseMibs (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		baseMibs = value;
	}


	/**
	 * Handles the  JMX  Get Request for version
	 */
	public String  getVersion()
				 throws AgentException {
		// fill up with necessary processing
		return version;
	}


	/**
	 * Handles the  JMX  Set Request for version
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setVersion (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		version = value;
	}


	/**
	 * Handles the  JMX  Get Request for userName
	 */
	public String  getUserName()
				 throws AgentException {
		// fill up with necessary processing
		return userName;
	}


	/**
	 * Handles the  JMX  Set Request for userName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setUserName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		userName = value;
	}


	/**
	 * Handles the  JMX  Get Request for contextName
	 */
	public String  getContextName()
				 throws AgentException {
		// fill up with necessary processing
		return contextName;
	}


	/**
	 * Handles the  JMX  Set Request for contextName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setContextName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		contextName = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingOid2
	 */
	public String  getInheritingOid2()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingOid2;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingOid2
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingOid2 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		inheritingOid2 = value;
	}


	/**
	 * Handles the  JMX  Get Request for inheritingTableName2
	 */
	public String  getInheritingTableName2()
				 throws AgentException {
		// fill up with necessary processing
		return inheritingTableName2;
	}


	/**
	 * Handles the  JMX  Set Request for inheritingTableName2
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setInheritingTableName2 (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		inheritingTableName2 = value;
	}


}

