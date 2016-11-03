//$Id: WebNMSFaultMibInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
// User code starts here
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.snmp.snmp2.agent.*;

import com.adventnet.nms.topodb.*;
// User code ends here


/**
 * Handles all the requests under WebNMSFaultMib group
 */
public class WebNMSFaultMibInstrument {



	protected Integer webNMSNumEvents = new Integer(1) ;
	protected Integer webNMSNumAlerts = new Integer(1) ;
	protected Integer webNMSEventsInBuffer = new Integer(1) ;
	protected Integer webNMSAlertsInBuffer = new Integer(1) ;
	protected String alertUserPropNames = "alertUserPropNames not initialized" ;//No I18N
	protected String eventUserPropNames = "eventUserPropNames not initialized" ;//No I18N




	/**
	 * Handles the  JMX  Get Request for webNMSNumEvents
	 */
	public Integer  getWebNMSNumEvents()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	try {
            /**  invoking the API
                 for obtaining the total number of events in the NMS at that instant
             */
	    if(agentRef.initEvent())
		webNMSNumEvents = new Integer(agentRef.eventAPI.getTotalEventCount());
	    else
		throw new AgentException("", CommonUtils.GENERR);
	    }
	catch(Exception e)
	    {
		throw new AgentException("", CommonUtils.BADVALUE);
	    }
        // User code ends here
		return webNMSNumEvents;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSNumEvents
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSNumEvents (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSNumEvents = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSNumAlerts
	 */
	public Integer  getWebNMSNumAlerts()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	try {
            /**  invoking the API
                 for obtaining the total number of alerts in the NMS at that instant
            */
	    if(agentRef.initAlert())
		webNMSNumAlerts = new Integer(agentRef.alertAPI.getTotalAlertCount());
	    else
		throw new AgentException("", CommonUtils.GENERR);
	    }
	catch(Exception e)
	    {
		throw new AgentException("", CommonUtils.BADVALUE);
	    }
        // User code ends here

		return webNMSNumAlerts;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSNumAlerts
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSNumAlerts (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSNumAlerts = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSEventsInBuffer
	 */
	public Integer  getWebNMSEventsInBuffer()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	try {
            /**  invoking the API
                 for obtaining the total number of events in the buffer at that instant
            */
	    if(agentRef.initEvent())
		webNMSEventsInBuffer = new Integer(agentRef.eventAPI.getEventQueueSize());
	    else
		throw new AgentException("", CommonUtils.GENERR);
	    }
	catch(Exception e)
	    {
		throw new AgentException("", CommonUtils.BADVALUE);
	    }
        // User code ends here

		return webNMSEventsInBuffer;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSEventsInBuffer
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSEventsInBuffer (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSEventsInBuffer = value;
	}


	/**
	 * Handles the  JMX  Get Request for webNMSAlertsInBuffer
	 */
	public Integer  getWebNMSAlertsInBuffer()
				 throws AgentException
	{
		// Fill up the stub with required processing
		 // User code starts here
      try {
          /**  invoking the API
               for obtaining the total number of alerts in the buffer at that instant
          */
	    if(agentRef.initAlert())
		webNMSAlertsInBuffer = new Integer(agentRef.alertAPI.getAlertQueueSize());
	    else
		throw new AgentException("", CommonUtils.GENERR);
	  }
	  catch(Exception e)
	  {
		  throw new AgentException("", CommonUtils.BADVALUE);
	  }
	// User code ends here

		return webNMSAlertsInBuffer;
	}


	/**
	 * Handles the  JMX  Set Request for webNMSAlertsInBuffer
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setWebNMSAlertsInBuffer (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		webNMSAlertsInBuffer = value;
	}


	/**
	 * Handles the  JMX  Get Request for alertUserPropNames
	 */
	public String  getAlertUserPropNames()
				 throws AgentException
	{
		// Fill up the stub with required processing
		  // User code starts here
      /** assigning the user property names given by the user
       */
      alertUserPropNames = agentRef.alertUserPropNames;
     	 // User code ends here

		return alertUserPropNames;
	}


	/**
	 * Handles the  JMX  Set Request for alertUserPropNames
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setAlertUserPropNames (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);
		// User code starts here
		/*

		alertUserPropNames = value;
		*/
		if((value.length() < 0)||(value.length() > 255))
		throw new AgentException("", CommonUtils.BADVALUE);

        /** checking if the syntax of the user property names is correct,
            if not throwing exception
         */

	if(!agentRef.syntaxOK(value))
	    throw new AgentException("",CommonUtils.BADVALUE);

	agentRef.alertUserPropNames=value;
        /** storing the user property names in the database
         */
	agentRef.updateDefValue("alertUserPropNames",value);//No I18N
        alertUserPropNames = agentRef.alertUserPropNames;
		// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for eventUserPropNames
	 */
	public String  getEventUserPropNames()
				 throws AgentException
	{
		// Fill up the stub with required processing
		 // User code starts here
      /** assigning the user property names of events which are given by the user
       */
      eventUserPropNames = agentRef.eventUserPropNames;
      // User code ends here
		return eventUserPropNames;
	}


	/**
	 * Handles the  JMX  Set Request for eventUserPropNames
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setEventUserPropNames (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);
			// User code starts here
		/*
		eventUserPropNames = value;
		*/
		if((value.length() < 0)||(value.length() > 255))
		throw new AgentException("", CommonUtils.BADVALUE);

	/** checking if the syntax is correct for the user property names given by the user,
            if not throwing exception
         */
	if(!agentRef.syntaxOK(value))
	    throw new AgentException("",CommonUtils.BADVALUE);

	agentRef.eventUserPropNames = value;
        /** storing the event user property names in the database
         */
	agentRef.updateDefValue("eventUserPropNames",value);//No I18N
        /** assigning the user property names of events given by the user
         */
        eventUserPropNames = agentRef.eventUserPropNames;
		// User code ends here
	}

	// User code starts here
    AdventNet_WebNMS_MIB_JMX agentRef = null;
    public void setAgentReference(AdventNet_WebNMS_MIB_JMX agentRef)
    {
	this.agentRef = agentRef;
    }
	// User code ends here

}



