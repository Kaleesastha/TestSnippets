//$Id: WebNMSTopoMibInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
 * @Version :  6.0.0 Wed Feb 14 00:34:06 IST 2007
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
 * Handles all the requests under WebNMSTopoMib group
 */
public class WebNMSTopoMibInstrument {



	protected Integer webNMSNumObjects = new Integer(1) ;
	protected Integer webNMSNumNetworks = new Integer(1) ;
	protected Integer webNMSNumNodes = new Integer(1) ;
	protected Integer webNMSNumInterfaces = new Integer(1) ;
	protected String moUserPropNames = "moUserPropNames not initialized" ;//No I18N
	// User code starts here
    //by senthil
    AdventNet_WebNMS_MIB_JMX agentRef = null;

    //constructor with agentRef
    public void setAgentReference(AdventNet_WebNMS_MIB_JMX agent)
    {
	this.agentRef = agent;
	moUserPropNames = "" ;//No I18N
    }
    // User code ends here

    /**
     * Handles the  JMX  Get Request for webNMSNumObjects
     */




	/**
	 * Handles the  JMX  Get Request for webNMSNumObjects
	 */
	public Integer  getWebNMSNumObjects()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	try
	{
	    /*
	     * Getting the Number of objects discoverd in NMS
	     */
	    if(agentRef.initTopo())
		webNMSNumObjects = new Integer(agentRef.topoAPI.getNumObjects());
	    else
		throw new AgentException("", CommonUtils.GENERR);
	}
	catch(Exception e)
	{
	    agentRef.agentErr.fail("Exception" , e);//No I18N
	    throw new AgentException("", CommonUtils.GENERR);
	}
	// User code ends here

		return webNMSNumObjects;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSNumObjects
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSNumObjects (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSNumObjects = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSNumNetworks
	 */
	public Integer  getWebNMSNumNetworks()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	try
	{
	    /*
	     * Getting the number of Networks discoverd
	     */
	    if(agentRef.initTopo())
		webNMSNumNetworks = new Integer(agentRef.topoAPI.getNumNetworks());
	    else
		throw new AgentException("", CommonUtils.GENERR);
	}
	catch(Exception e)
	{
	    agentRef.agentErr.fail("Exception" , e);//No I18N
	    throw new AgentException("", CommonUtils.GENERR);
	}
	// User code ends here


		return webNMSNumNetworks;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSNumNetworks
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSNumNetworks (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSNumNetworks = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSNumNodes
	 */
	public Integer  getWebNMSNumNodes()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	try
	{
	    /*
	     * Getting the number of Nodes discoverd
	     */
	    if(agentRef.initTopo())
		webNMSNumNodes = new Integer(agentRef.topoAPI.getNumNodes());
	    else
		throw new AgentException("", CommonUtils.GENERR);
	}
	catch(Exception e)
	{
	    agentRef.agentErr.fail("Exception" , e);//No I18N
	    throw new AgentException("", CommonUtils.GENERR);
	}
	// User code ends here


		return webNMSNumNodes;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSNumNodes
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSNumNodes (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSNumNodes = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSNumInterfaces
	 */
	public Integer  getWebNMSNumInterfaces()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	try
	{
	    /*
	     * Getting the number of Interfaces  discoverd
	     */
	    if(agentRef.initTopo())
		webNMSNumInterfaces = new Integer(agentRef.topoAPI.getNumInterfaces());
	    else
		throw new AgentException("", CommonUtils.GENERR);
	}
	catch(Exception e)
	{
	    agentRef.agentErr.fail("Exception" , e);//No I18N
	    throw new AgentException("", CommonUtils.GENERR);
	}
	// User code ends here

		return webNMSNumInterfaces;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSNumInterfaces
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSNumInterfaces (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSNumInterfaces = value;
	}


	/**
	 * Handles the  JMX  Get Request for moUserPropNames
	 */
	public String  getMoUserPropNames()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	moUserPropNames = agentRef.moUserPropNames;
	// User code ends here

		return moUserPropNames;
	}


	/**
	 * Handles the  JMX  Set Request for moUserPropNames
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setMoUserPropNames (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		moUserPropNames = value;
		// User code starts here
	agentRef.moUserPropNames = value;
	/*
	 * Storing the value into database using NMS's UserStorage API
	 */
	agentRef.updateDefValue("moUserPropNames",value);//No I18N
	// User code ends here
	}
}


