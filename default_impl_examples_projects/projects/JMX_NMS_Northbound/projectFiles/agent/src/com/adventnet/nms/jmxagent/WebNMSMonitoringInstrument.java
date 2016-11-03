 //$Id: WebNMSMonitoringInstrument.java,v 1.1 2007/06/18 10:33:54 barathv Exp $
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
 * @Version :  6.0.0 Sat Jun 16 11:37:37 IST 2007
 * @Author  :  AdventNet Agent Toolkit Java Edition 
 */

// Any changes made to this file will be lost, if regenerated. 
// User code should be added within user tags for code merging support, if regenerated.



// Package Name (Dont Delete this comment)
package com.adventnet.nms.jmxagent ; 

import com.adventnet.utilities.common.*;
import com.adventnet.utils.agent.utils;
import com.adventnet.nms.management.NmsManagementAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;

import java.io.*;
import java.util.*;



/**
 * Handles all the requests under WebNMSMonitoring group
 */
public class WebNMSMonitoringInstrument {



	protected Integer statusPollingRate = new Integer(0) ;
	protected Integer dataCollectionRate = new Integer(0) ;
	protected Integer trapProcessingRate = new Integer(0) ;
	protected Integer eventProcessingRate = new Integer(0) ;
	protected Integer alertProcessingRate = new Integer(0) ;
	// User code starts here
    	AdventNet_WebNMS_MIB_JMX agentRef = null;
	NmsManagementAPI api = null;
	
	public WebNMSMonitoringInstrument()
	{
		int retries =0;
		while (true & retries++ < 4)
		{
			RunProcessInterface rpi =  (RunProcessInterface)NmsUtil.runProcessModules.get("com.adventnet.nms.management.WebNMSMgmtBEProcess"); // no i18n
			if(rpi == null || (rpi != null && !rpi.isInitialized()))
			{
				try
				{
					// Wait for WebNMSMgmtBEProcess module to get initialized.
					Thread.sleep(1000);
				}
				catch (Exception ee)
				{
				}
			}
			else
			{
				break;
			} 
		}
		
		if (api == null)
		{	
				api = (NmsManagementAPI) NmsUtil.getAPI ("NmsManagementAPI"); // no i18n
		}

	}
	
    	//constructor with agentRef
    	public void setAgentReference(AdventNet_WebNMS_MIB_JMX agent)
 	   {
		this.agentRef = agent;
    	}

    // User code ends here



	/** 
	 * Handles the  JMX  Get Request for statusPollingRate
	 */ 
	public Integer  getStatusPollingRate()
				 throws AgentException 
	{
		// Fill up the stub with required processing
		try
		{	if (api != null)
			{
				Long ls = new Long(api.getStatusPollingRate());
				Integer in = new Integer(ls.intValue());
				return in;
			}
			else
			{
				return statusPollingRate;
			}
		}
		catch (Exception es)
		{
			es.printStackTrace();
		}
		return statusPollingRate;
	}


	/** 
	 * Handles the  JMX  Set Request for statusPollingRate
	 * @param Integer  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setStatusPollingRate (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing 

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		statusPollingRate = value;
	}


	/** 
	 * Handles the  JMX  Get Request for dataCollectionRate
	 */ 
	public Integer  getDataCollectionRate()
				 throws AgentException 
	{
		// Fill up the stub with required processing 
		try
		{	if (api != null)
			{
				Long ls = new Long(api.getDataCollectionRate());
				Integer in = new Integer(ls.intValue());
				return in;
			}
			else
			{
				return dataCollectionRate;
			}
		}
		catch (Exception es)
		{
			es.printStackTrace();
		}
		return dataCollectionRate;
	}


	/** 
	 * Handles the  JMX  Set Request for dataCollectionRate
	 * @param Integer  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setDataCollectionRate (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing 

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		dataCollectionRate = value;
	}


	/** 
	 * Handles the  JMX  Get Request for trapProcessingRate
	 */ 
	public Integer  getTrapProcessingRate()
				 throws AgentException 
	{
		// Fill up the stub with required processing 
		try
		{	if (api != null)
			{
				Long ls = new Long(api.getTrapRate());
				Integer in = new Integer(ls.intValue());
				return in;
			}
			else
			{
				return trapProcessingRate;
			}
		}
		catch (Exception es)
		{
			es.printStackTrace();
		}
		return trapProcessingRate;
	}


	/** 
	 * Handles the  JMX  Set Request for trapProcessingRate
	 * @param Integer  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setTrapProcessingRate (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing 

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		trapProcessingRate = value;
	}


	/** 
	 * Handles the  JMX  Get Request for eventProcessingRate
	 */ 
	public Integer  getEventProcessingRate()
				 throws AgentException 
	{
		// Fill up the stub with required processing
		try
		{	if (api != null)
			{
				Long ls = new Long(api.getEventRate());
				Integer in = new Integer(ls.intValue());
				return in;
			}
			else
			{
				return eventProcessingRate;
			}
		}
		catch (Exception es)
		{
			es.printStackTrace();
		} 

		return eventProcessingRate;
	}


	/** 
	 * Handles the  JMX  Set Request for eventProcessingRate
	 * @param Integer  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setEventProcessingRate (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing 

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		eventProcessingRate = value;
	}


	/** 
	 * Handles the  JMX  Get Request for alertProcessingRate
	 */ 
	public Integer  getAlertProcessingRate()
				 throws AgentException 
	{
		// Fill up the stub with required processing 
		try
		{	if (api != null)
			{
				Long ls = new Long(api.getAlertRate());
				Integer in = new Integer(ls.intValue());
				return in;
			}
			else
			{
				return alertProcessingRate;
			}
		}
		catch (Exception es)
		{
			es.printStackTrace();
		}

		return alertProcessingRate;
	}


	/** 
	 * Handles the  JMX  Set Request for alertProcessingRate
	 * @param Integer  
	 * @throws AgentException on error  
	 */ 
	public synchronized void setAlertProcessingRate (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing 

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE); // no i18n
		alertProcessingRate = value;
	}
}
