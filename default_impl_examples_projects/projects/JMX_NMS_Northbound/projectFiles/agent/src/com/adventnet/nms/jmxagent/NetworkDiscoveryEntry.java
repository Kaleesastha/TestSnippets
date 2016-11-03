 //$Id: NetworkDiscoveryEntry.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * networkDiscoveryEntry group
 */

public class NetworkDiscoveryEntry	{


	protected Integer networkDiscoveryIndex = new Integer(1) ;
	protected String netIPAddress = "255.255.255.0" ;//No I18N
	protected String netMask = "255.255.255.0" ;//No I18N
	protected String startIPAddress = "startIPAddress not initialized" ;//No I18N
	protected String endIPAddress = "endIPAddress not initialized" ;//No I18N
	protected Integer doDiscovery = new Integer(1) ;
	protected Integer dhcp = new Integer(1) ;


	/**
	 * Handles the  JMX  Get Request for networkDiscoveryIndex
	 */
	public Integer  getNetworkDiscoveryIndex()
				 throws AgentException {
		// fill up with necessary processing
		return networkDiscoveryIndex;
	}


	/**
	 * Handles the  JMX  Set Request for networkDiscoveryIndex
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setNetworkDiscoveryIndex (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		networkDiscoveryIndex = value;
	}


	/**
	 * Handles the  JMX  Get Request for netIPAddress
	 */
	public String  getNetIPAddress()
				 throws AgentException {
		// fill up with necessary processing
		return netIPAddress;
	}


	/**
	 * Handles the  JMX  Set Request for netIPAddress
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setNetIPAddress (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		StringTokenizer tok =  new StringTokenizer(value,".",true);//No I18N
		if(tok.countTokens() > ((4*2) -1))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		netIPAddress = value;
	}


	/**
	 * Handles the  JMX  Get Request for netMask
	 */
	public String  getNetMask()
				 throws AgentException {
		// fill up with necessary processing
		return netMask;
	}


	/**
	 * Handles the  JMX  Set Request for netMask
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setNetMask (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		StringTokenizer tok =  new StringTokenizer(value,".",true);//No I18N
		if(tok.countTokens() > ((4*2) -1))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		netMask = value;
	}


	/**
	 * Handles the  JMX  Get Request for startIPAddress
	 */
	public String  getStartIPAddress()
				 throws AgentException {
		// fill up with necessary processing
		return startIPAddress;
	}


	/**
	 * Handles the  JMX  Set Request for startIPAddress
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setStartIPAddress (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		startIPAddress = value;
	}


	/**
	 * Handles the  JMX  Get Request for endIPAddress
	 */
	public String  getEndIPAddress()
				 throws AgentException {
		// fill up with necessary processing
		return endIPAddress;
	}


	/**
	 * Handles the  JMX  Set Request for endIPAddress
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setEndIPAddress (String  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);//No I18N

		endIPAddress = value;
	}


	/**
	 * Handles the  JMX  Get Request for doDiscovery
	 */
	public Integer  getDoDiscovery()
				 throws AgentException {
		// fill up with necessary processing
		return doDiscovery;
	}


	/**
	 * Handles the  JMX  Set Request for doDiscovery
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setDoDiscovery (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N

		doDiscovery = value;
	}


	/**
	 * Handles the  JMX  Get Request for dhcp
	 */
	public Integer  getDhcp()
				 throws AgentException {
		// fill up with necessary processing
		return dhcp;
	}


	/**
	 * Handles the  JMX  Set Request for dhcp
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setDhcp (Integer  value )
				 throws AgentException {
		// fill up with necessary processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);//No I18N

		dhcp = value;
	}


}

