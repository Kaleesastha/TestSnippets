//$Id: ProxyEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Wed Feb 14 00:34:10 IST 2007
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

// User code starts here
import com.adventnet.snmp.beans.*;
// User code ends here
/**
 * Contains the data handling under
 * proxyEntry group
 */

public class ProxyEntry	{


	protected Integer serialNumber = new Integer(1) ;
	protected String hostName = "hostName not initialized" ;//No I18N
	protected Integer devicePort = new Integer(1) ;
	protected String requestOid = ".1.3.6.1.2.1" ;//No I18N
	protected String community = "community not initialized" ;//No I18N
	protected Integer service = new Integer(0) ;
	protected String result = "result not initialized" ;//No I18N
// User code starts here
    //for getNext() and Table
    String nextOid = "";//No I18N
    SnmpTarget target = null;

    public ProxyEntry()
    {
	hostName = "" ;//No I18N
	devicePort = new Integer(161) ;
	requestOid = "" ;//No I18N
	community = "public" ;//No I18N
	service = new Integer(0) ;
	result = "No Result" ;//No I18N
    }
    // User code ends here


	/**
	 * Handles the  JMX  Get Request for serialNumber
	 */
	public Integer  getSerialNumber()
				 throws AgentException {
		// fill up with necessary processing
		return serialNumber;
	}


	/**
	 * Handles the  JMX  Set Request for serialNumber
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setSerialNumber (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		serialNumber = value;
	}


	/**
	 * Handles the  JMX  Get Request for hostName
	 */
	public String  getHostName()
				 throws AgentException {
		// fill up with necessary processing
		return hostName;
	}


	/**
	 * Handles the  JMX  Set Request for hostName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setHostName (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		hostName = value;
	}


	/**
	 * Handles the  JMX  Get Request for devicePort
	 */
	public Integer  getDevicePort()
				 throws AgentException {
		// fill up with necessary processing
		return devicePort;
	}


	/**
	 * Handles the  JMX  Set Request for devicePort
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setDevicePort (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		devicePort = value;
	}


	/**
	 * Handles the  JMX  Get Request for requestOid
	 */
	public String  getRequestOid()
				 throws AgentException {
		// fill up with necessary processing
		return requestOid;
	}


	/**
	 * Handles the  JMX  Set Request for requestOid
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setRequestOid (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		requestOid = value;
	}


	/**
	 * Handles the  JMX  Get Request for community
	 */
	public String  getCommunity()
				 throws AgentException {
		// fill up with necessary processing
		return community;
	}


	/**
	 * Handles the  JMX  Set Request for community
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setCommunity (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		community = value;
	}


	/**
	 * Handles the  JMX  Get Request for service
	 */
	public Integer  getService()
				 throws AgentException {
		// fill up with necessary processing
		return service;
	}


	/**
	 * Handles the  JMX  Set Request for service
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setService (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 0)||(value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N

		service = value;
	}


	/**
	 * Handles the  JMX  Get Request for result
	 */
	public String  getResult()
				 throws AgentException {
		// fill up with necessary processing
		return result;
	}


	/**
	 * Handles the  JMX  Set Request for result
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setResult (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		result = value;
	}


}



