//$Id: AlertDeleteProcess.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/**<b>@(#)AlertDeleteProcess.java </b>	
 * Copyright (c) 1996 Advent Network Management, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 */

package com.adventnet.nms.fault;


import java.util.*;

import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;
import com.adventnet.management.policydb.*;


/**  An example Alert Delete Process. This will initiate a policy 
 * to delete Alerts matching the criteria properties periodically. And 
 * this will instantiate an Listener which will delete
 * the Alerts that are cleared and notified to that listener. So that
 * alerts will be deleted as soon as it is cleared. Criteria Properties , 
 * whether alerts to be deleted as soon as it is cleared and scheduling
 * period of policy has to be passed as arguments to this process.
 

  @author Karthick Srinivasan 
  @version 1.0
*/


public class AlertDeleteProcess implements RunProcessInterface
{

    private String alertDeleteInterval = null;
    private boolean deleteClear = false;
    private int policyPeriod = 30;
    private Properties criteriaProp = new Properties();
    private boolean isInitialized = false;
    private String policyName = "AlertDeletePolicy";
    private boolean enablePolicy = false;
    private AlertDeleteListener listener = null;

    /** variable to specify the classname while logging messages.
     */
    private String className = "AlertDeleteProcess : ";
    

    public AlertDeleteProcess()
    {

    }

    private void init(String args[])
    {
        /** Parses the Arguments.
         */
        parseOptions(args);

        /** initialize AlertListener to Delete Clear alert  as
         * soon as alert is cleared.
         */ 
        if(deleteClear)
        {
            listener = new AlertDeleteListener();
        }

        /** Initialize Policy to Delete Alerts periodiacally matching the
         * criteria property supplied as argument.
         */
        if(alertDeleteInterval != null && !alertDeleteInterval.equals(""))
        {
            criteriaProp.put("delete Interval",alertDeleteInterval);
        }

        AlertDeletePolicy adp = new AlertDeletePolicy();
        /** Criteria Properties are set as user properties 
         * for the Policy Object.
         */
        adp.setUserProperties(criteriaProp);
        /** Sets the period of execution of Policy.
         */
        adp.setPeriod(policyPeriod);
        /** Sets the Policy Name.
         */
        adp.setName(policyName);
        /** Sets the status of the policy based on ENABLE_POLICY argument
         * value.
         */
        if(enablePolicy)
        {
            adp.setStatus(NmsPolicyAPI.POLICY_ENABLED);
        }
        else
        {
            adp.setStatus(NmsPolicyAPI.POLICY_DISABLED);   
        }

        NmsPolicyAPI policyAPI = null;
       
        /** Waits till Policy API Handle is obtained.
         */
        while(policyAPI == null)
        {
            policyAPI = getPolicyAPI();
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        } 
        
        isInitialized = true;
        
        try
        {
            /** In previous run of Web Nms Server if the same Policy
             * is added it is removed and new one is added once again.
             */
            Vector names =  policyAPI.getPolicyNames();
            if(names.contains(policyName))
            {
                boolean isDeleted = policyAPI.deletePolicy(policyName);
                if(isDeleted)
                {
                    NmsLogMgr.ALERTUSER.log(className+NmsUtil.GetString("AlertDeletePolicy is Deleted successfully"),Log.SUMMARY);
                }
                else
                {
                    NmsLogMgr.ALERTERR.log(className+NmsUtil.GetString("AlertDeletePolicy is not Deleted successfully"),Log.SUMMARY);
                }
            }
            boolean isAdded = policyAPI.addPolicy(adp);
            if(isAdded)
            {
                NmsLogMgr.ALERTUSER.log(className+NmsUtil.GetString("AlertDeletePolicy is Added successfully"),Log.SUMMARY);
            }
            else
            {
                NmsLogMgr.ALERTERR.log(className+NmsUtil.GetString("AlertDeletePolicy is not added successfully."),Log.SUMMARY);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /** This method is used to parse the command line options.
     */
    private void parseOptions(String args[])
    {
        if(args == null)
            return;
        for(int i=0;i<args.length;i++)
        {
            try
            {
                /** The time interval after which Alerts needs
                 *  to be deleted will be parsed in secs.
                 *  Value should be an integer.
                 */
                if(args[i].equalsIgnoreCase("DELETE_INTERVAL"))
                {
                    alertDeleteInterval = (args[i+1]).trim();
                }
                /** The parameter is to decide whether the clear
                 *  alerts has to be deleted as  soon as it is 
                 *  cleared, is parsed here. Value should be 
                 *  boolean.
                 */
                else if(args[i].equalsIgnoreCase("DELETE_CLEAR_IMMEDIATELY"))
                {
                    deleteClear = new Boolean(args[i+1]).booleanValue();
                }
                /** The parameter is to specify status of the
                 *  Alert Delete  policy  whether policy is to be
                 *  enabled or not. And its value should be 
                 *  boolean.
                 */
                else if(args[i].equalsIgnoreCase("ENABLE_POLICY"))
                {
                    enablePolicy = new Boolean(args[i+1]).booleanValue();
                }
                /** The parameter to specify the period of 
                 * execution of policy is parsed here as secs.
                 *  Value should be an integer.
                 */
                else if(args[i].equalsIgnoreCase("POLICY_PERIOD"))
                {
                    policyPeriod = Integer.parseInt(args[i+1]);
                }
                /** The parameter to specify the matching 
                 *  Properties for the alert which has to be 
                 *  deleted on execution of policy. And Criterias 
                 *  should be like below.
                 *  Eg: source=router1,router2;entity=IF-rout*;
                 *  Delimeters <b> '*' and ',' </b>  
                 *  are supported.
                 */
                else if(args[i].equalsIgnoreCase("ALERT_CRITERIA_PROPERTIES"))
                {
                    formCriteria(args[i+1]);
                }
            }
            catch(Exception ee)
            {
                NmsLogMgr.ALERTERR.fail(className+NmsUtil.GetString("Exception while parsing the option")+" : " + args[i] + " "+NmsUtil.GetString("whose value is")+" : " + args[i+1]+ " " + ee,ee);
            }
        }// end for
    }

    /** Parses criteria string and sets the criteria in
     * criteriaProp object.
     */
    private void formCriteria(String criteria)
    {
        if(criteria == null)
        {
            return;
        }
        criteria = criteria.trim();
        StringTokenizer tokenizer = new StringTokenizer(criteria, ";");
        while(tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken().trim();
            int index = token.indexOf('=');
            if(index != -1)
            {
                String key = token.substring(0, index).trim();
                String value =token.substring(index+1).trim();
                setCriteriaValue(key,value);
            }
        }
        
    }
    
    /** Sets given criteria in the form of  key and value in criteriaProp.
     *  But it would not update if modTime is given as criteria. Since
     *  modTime is handled internally in AlertDeletePolicy based on
     *  delete Interval.
     */  
    private void setCriteriaValue(String key,String  value)
    {
        if(key != null && value != null)
        {
            key = key.trim();
            value = value.trim();
        }
        else
        {
            return;
        }
        if((key.equalsIgnoreCase("modTime")) || key.equals("") || value.equals(""))
        {
            return;
        }
        else
        {
            criteriaProp.put(key,value);
        }
    }
     
    /** Returns Policy API handle.
     */
    private NmsPolicyAPI getPolicyAPI()
    {
        return (NmsPolicyAPI) NmsUtil.getAPI("NmsPolicyAPI");
    }
    /** Returns whether policy is initialised or not.
     */
    public boolean isInitialized ()
    {
        return isInitialized;
    }


	/*  To handle shut Down.
	 */
	public void shutDown ()
	{
		// handle shutdown for the process
        if(listener != null)
        {
            listener.deregisterForNotification();
        }
	}

	public void callMain (String[] args)
	{
		// This method is called by the NMS server during startup.
	    init(args);
	}


} // End of class AlertDeleteProcess





