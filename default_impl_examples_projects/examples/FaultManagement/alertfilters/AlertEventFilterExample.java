/* $Id: AlertEventFilterExample.java,v 1.2 2010/10/29 13:46:03 swaminathap Exp $ */
/** AlertEventFilterExample.java */
package com.adventnet.nms.example.alertfilter;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.poll.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;
import java.rmi.*;
import java.util.*;
import java.io.*;

/** 
 * This class implements an alert/event filter example
 * In this example we will demonstrate how we can stop/start polls for the source
 * of the alert/event . More specificallly we will stop or rather suspend polls
 * (data collection ) for the agent which is the source of the alert/event
 * in case the severity is not clear  and resume it if the severity is clear 
 *
 * This Alert filter action class can be used by renaming the file
 *                  <Web NMS>/conf/examples/alertfilters/AlertEventFilterExample.filters
 * into             <Web NMS>/conf/alert.filters
 * before starting the server or by adding this filter action class dynamically.
 */
public class AlertEventFilterExample implements FilterClient {


  // the pollapi reference 
  private PollAPI api ;

  public AlertEventFilterExample() {
    
  }

  /** This is called when the user filter action class is first instantiated **/
  public void init() {

  }

    /**
 	* This is the filter method which should be implemented if this
    * class is registered as Event filter. This method will be invoked  
    * whenever an event  matches the Event Filter criteria.
    * The matching event object will passed as the parameter.  
    * We call this example only as alert filter by including the entry 
    * in alert filters. Hence this method will not be called. If user 
    * includes this example as event filter this will be called and 
    * stop/start Polling based on severity.
    **/
  public com.adventnet.nms.eventdb.Event filter(com.adventnet.nms.eventdb.Event e) {
  	SeverityInfo sevInfo = SeverityInfo.getInstance();
    //if (e.getSeverity()<5) 
    if (!sevInfo.isNoCriticality(e.getSeverity()) && !sevInfo.isClear(e.getSeverity())) 
	{
      suspendPollsForAgent(e.getSource());
    }
	//else if (e.getSeverity()==5) 
	else if (sevInfo.isClear(e.getSeverity())) 
      resumePollsForAgent(e.getSource());
    return e;
    // note that we do not do anything for info events that is severity==6
  }

    /**
 	* This is the filter method which should be implemented if this
    * class is registered as Alert filter. This method will be invoked  
    * whenever an alert matches the Alert Filter criteria.
    * The matching Alert object will passed as the parameter.  
    * We call this example only as alert filter by including the entry 
    * in alert filters. Hence this method will be called. This method  
    * checks the severity of alert and stops or resumes Polling of the
    * alert's Source based on severity.
    **/
  public com.adventnet.nms.alertdb.Alert filter(com.adventnet.nms.alertdb.Alert a) {
  	SeverityInfo sevInfo = SeverityInfo.getInstance();
    /** If the Severity is not clear, stop the poll.
     */
    if (!sevInfo.isNoCriticality(a.getSeverity()) && !sevInfo.isClear(a.getSeverity())) 
	{
      suspendPollsForAgent(a.getSource());
    }
    /** If the Severity is clear resume the poll.
     */
	else if (sevInfo.isClear(a.getSeverity())) 
      resumePollsForAgent(a.getSource());
    return a;
  }

    /** This gets the handle of PollAPI.
     */
    public void getPollAPI() {
	    api = (PollAPI) NmsUtil.getAPI("PollAPI");//No I18N
	 while (api == null) 
	 {
	     try 
	     { 
	         Thread.sleep(200);
	     } 
	     catch (Exception e)
	     {
	     } 
	     api = (PollAPI) NmsUtil.getAPI("PollAPI");//No I18N
    }
  } // end  getPollAPI()

  /**
   * This method will suspend all the polls configured for a given 
   * agentname.
   */
  private void suspendPollsForAgent(String agentname) {
    try {
      if (agentname==null) return;
      getPollAPI();
      if (api==null) return;
      api.suspendAllPollsForAgent(agentname);
    } catch (RemoteException re) {
      System.err.println("RemoteException trying to suspend polls for "+agentname+" from alertfilter");
      re.printStackTrace();
    }
	catch (Exception ee) {
      System.err.println("Exception while trying to suspend polls for "+agentname+" from alertfilter");
      ee.printStackTrace();
	 } 
  }

  /**
   * This method will resume all the polls configured for a given 
   * agentname.
   */
  private void resumePollsForAgent(String agentname) {
    try {
      getPollAPI();
      if (api==null) return;
      api.resumeAllPollsForAgent(agentname);
    } catch (RemoteException re) {
      System.err.println("RemoteException trying to resume polls for "+agentname+" from alertfilter");
      re.printStackTrace();
    }
	catch (Exception ee) {
      System.err.println("Exception while trying to resume polls for "+agentname+" from alertfilter");
      ee.printStackTrace();
	}
  }
}
