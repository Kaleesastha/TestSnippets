//$Id: WebNMSPerformanceMibInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
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
import com.adventnet.nms.poll.*;
// User code ends here

/**
 * Handles all the requests under WebNMSPerformanceMib group
 */
public class WebNMSPerformanceMibInstrument {



	protected Integer numPollObjects = new Integer(1) ;
	protected Integer numActivePollers = new Integer(1) ;
	protected Integer numThresholdObjects = new Integer(1) ;

	 // User code starts here
    AdventNet_WebNMS_MIB_JMX agentRef = null;
      public  void setAgentReference(AdventNet_WebNMS_MIB_JMX agentRef)
    {
	this.agentRef = agentRef;
	}

    // User code ends here




	/**
	 * Handles the  JMX  Get Request for numPollObjects
	 */
	public Integer  getNumPollObjects()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
      try{
          if(agentRef.initTopo())
              /** getting the number of poll-objects in the NMS at that instant
               */
		numPollObjects = new Integer(agentRef.pollAPI.getNumPollObjects());
	    else
		throw new AgentException("", CommonUtils.GENERR);
      }
      catch(Exception e)
	  {
	      throw new AgentException("", CommonUtils.BADVALUE);
	  }
	// User code ends here

		return numPollObjects;
	}


	/**
	 * Handles the  JMX  Set Request for numPollObjects
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setNumPollObjects (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		numPollObjects = value;
	}


	/**
	 * Handles the  JMX  Get Request for numActivePollers
	 */
	public Integer  getNumActivePollers()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
      try{
	    if(agentRef.initTopo())
	    {
                /** getting the number of active pollers in the NMS at that instant
                 */
		Vector v=agentRef.pollAPI.getActivePollers();
		numActivePollers =new Integer(v.size());
	    }
	    else
		throw new AgentException("", CommonUtils.GENERR);
      }
      catch(Exception e)
	  {
	      throw new AgentException("", CommonUtils.BADVALUE);
	  }
   	   // User code ends here

		return numActivePollers;
	}


	/**
	 * Handles the  JMX  Set Request for numActivePollers
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setNumActivePollers (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		numActivePollers = value;
	}


	/**
	 * Handles the  JMX  Get Request for numThresholdObjects
	 */
	public Integer  getNumThresholdObjects()
				 throws AgentException
	{
		// Fill up the stub with required processing
		   // User code starts here
try{
            if(agentRef.initTopo())
                {
                    /** getting the number of threshold objects in the NMS at that instant
                     */
                Vector v=agentRef.pollAPI.getAllThresholdObjects();
		numThresholdObjects =new Integer(v.size());
	    }
	    else
		throw new AgentException("", CommonUtils.GENERR);
      }
      catch(Exception e)
	  {
	      throw new AgentException("", CommonUtils.BADVALUE);
	      }
	// User code ends here

		return numThresholdObjects;
	}


	/**
	 * Handles the  JMX  Set Request for numThresholdObjects
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setNumThresholdObjects (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		numThresholdObjects = value;
	}
}



