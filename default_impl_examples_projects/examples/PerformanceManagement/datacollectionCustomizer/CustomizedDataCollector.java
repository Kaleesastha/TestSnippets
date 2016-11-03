/*
 * $Id: CustomizedDataCollector.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 * Copyright (c) 2001 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET,INC. MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS
 * SOFTWARE OR ITS DERIVATIVES.
 */
package test;

import java.util.Calendar;
import java.util.Date;

import com.adventnet.nms.poll.CustomDataCollection;

/**
 * CustomizedDataCollector implements the CustomDataCollection 
 * interface to check whether data collection for a particular 
 * agent should be done or not. If the agent is down, it is an 
 * unnecessary process, sending request to agent for data collection.
 * In large networks, sometimes the agent may be down. In case
 * of dial up connection, connection to the agent can be
 * down during certain period, and the same may be 
 * restored after sometime. During these situations, sending request 
 * to the agent for data collection is unncessary. By using 
 * this interface, user can stop data collection for the agent
 * during these times.
 * In this example, it is assumed that an agent whose name
 * equals to 'router' is down during 9 P.M to 9 A.M. During 
 * this period, data collection for this agent is stopped
 * using this example. Users can plugin their own constraints
 * for stopping data collection by using this interface.
 *
 * @author Rajagopal N
 * @version $Revision: 1.1.1.1 $
 * @since WebNMS 2.3 + SP7
 */
public class CustomizedDataCollector implements CustomDataCollection
{
    private static long time = 0;
    private boolean firstTime = true;
    private long suspendedTime = 0;
    private boolean suspended = false;
    
 	// here get the reference time, example-current date,9:00 PM.
    
    private  void setTime()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE),21,0,0);
        Date d = cal.getTime();
        time = d.getTime();
        firstTime = false;
    }

    /**
     * Returns true or false depending on which it is decided data collection 
     * should be done for this agent or not.
     *
     * @param agent AgentName as <code>String</code> for which data
     * collection should be stopped.
     * @return false when data collection need not be done
     * for that agent or otherwise.
     */
    public boolean canProceedDataCollection(String agent)
    {
        // When this method is called for the first time
        // find the reference date and store it in the
        // variable  'time'.
        
        if (firstTime) 
        {
            setTime();
        }
        
        // Assume that we want to suspend data collection for the
        // agent whose name equals 'router',
        // Here we have to check if current time has exceeded 9:00 PM
        // if yes, suspend polls till 9.00 AM of next day and then 
        // resume it again
        
        if (agent.equals("router"))
        {
            // check if we can stop data collection by
            // comparing current time and reference time.

            if ((!suspended) && (canStopDataCollection(agent)))
            {
                //stop data collection, and hence return false
                return false;
            }

            // if it is in a suspended state , check if
            // we can resume it again,(i.e-check if 12 hour
            // has elapsed from the time of suspending data collection).
            
            if (suspended)
            {
                if(canResumeDataCollection(agent))
                {
                    //return true, if data collection can proceed.
                    return true;
                }
                else 
                {
                    return false;
                }
            }
        }
        // otherwise proceed with default data collection
        return true;
    }

    // check if we can resume the data collection
    // by comparing current time with suspended time.
    // if 12 hour has been elapsed resume data collection
    // or wait till 9 A.M of the next day.
    
    private boolean canResumeDataCollection(String agent)
    {
        long currenttime = System.currentTimeMillis();

        // if 12 hour (12 * 3600 seconds) has elapsed,
        // resume data collection, else stay in suspended state.
        
        if ((currenttime - suspendedTime) > (12 * 3600000))
        {
            suspended=false;
            System.out.println("Resuming data collection for agent: "+agent);
            return true;
        }
        return false;
    }
	 
    // This method is used for stopping data collection for a particular agent
    // for a particular time. Then again data collection is resumed for this
    // agent. In this case, for the agent whose names equals to 'router',
    // data collection is stopped at 9 P.M. Data collection
    // is resumed after 9.A.M of next day. This method returns truen when the 
    // data collection is stopped for the agent or otherwise.
 
    private boolean canStopDataCollection(String agent)
    {
        long currentTime = System.currentTimeMillis();
        
        if ((currentTime >= time) && (suspendedTime==0))
        {    
            System.out.println("Suspending data collection for agent: "+agent);
            suspended = true;
            // note the suspended time.
            suspendedTime = currentTime;
            return true;                
        }
        return false;
    }
}
    
    

















