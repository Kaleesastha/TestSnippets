//$Id: AlertDeletePolicy.java,v 1.2 2007/02/22 15:02:56 srajeswari Exp $
/**
 * <b>@(#)AlertDeletePolicy.java</b>
 * Copyright (c) 1996 Advent Network Management, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 */

package com.adventnet.nms.fault;

// Java imports
import java.util.*;


//Web Nms imports
import com.adventnet.nms.util.NmsLogMgr; 
import com.adventnet.management.log.Log;
import com.adventnet.management.policydb.PolicyEvent; 
import com.adventnet.management.policydb.PeriodicPolicyObject; 
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.severity.SeverityInfo;

/** 
 * This policy is to delete the Alerts matching the specified criteria 
 * from the Web NMS System.
  @author Karthick Srinivasan 
  @version 1.0
 */

public class AlertDeletePolicy extends PeriodicPolicyObject 
{ 

    /** variable to specify the classname while logging messages.
     */
    String className ="AlertDeletePolicy : ";
	
    public AlertDeletePolicy()
	{

	}

	/** 
	 * Sets the properties for this policy object 
     * Except base properties of Policy (<u>status,helpURL,period etc </u> 
     * all other properties i.e.(<u> Alert matching properties </u>)
     * are set as user properties.
	 * @param prop the property to be set.
     */
	public void setProperties(Properties prop) 
	{ 

        Properties base = new Properties();
        setBaseProp(base,prop,"status");
        setBaseProp(base,prop,"helpURL");
        setBaseProp(base,prop,"name");
        setBaseProp(base,prop,"policyObjectCutomizer");
        setBaseProp(base,prop,"period");
        setBaseProp(base,prop,"groupName");
        setUserProperties(prop);
		super.setBaseProperties(base); 	
	}  
    /** Internal Utility method to handle
     * policy properties.
     */
    private void setBaseProp(Properties newProp, Properties oldProp, String key)
    {
        String temp = (String)oldProp.remove(key);
        if(temp != null)
        {
            newProp.put(key,temp);
        }
    }

    /** The criteria  properties for the alerts to be  deleted
     *  should be given using this method. And those properties 
     *  will be set as user properties of Policy.
     */ 
    public void setUserProperties(Properties prop) 
	{
        for(Enumeration enumerate=prop.keys(); enumerate.hasMoreElements();)
        {
            String key = (String)enumerate.nextElement();
            String value=(String)prop.get(key);
            setUserProperty(key ,value);
        }
    }


	/** 
	 * Returns the properties of this policy object 
	 * @return "Policy properties" 
	 */ 
	public Properties getProperties() 
	{ 

		Properties prop = super.getBaseProperties(); 
        Properties userProp = getUserProperties();
        if(userProp != null && (userProp.size() > 0))
        {
            for(Enumeration enumerate=userProp.keys(); enumerate.hasMoreElements();)
            {
                String key = (String)enumerate.nextElement();
                String value=(String)prop.get(key);
                prop.put(key ,value);
            }
        }
		return prop;
	}


	/** 
     * Instead of the default policy UI, if user wants he can create his 
     * own GUI for his policy. In that case this method could return name 
     * of the class which extends PolicyObjectCustomizer.
     * Since there is no customizer this method returns null.
	 * @return "Policy customizer name" 
	 */ 
	public String getPolicyObjectCustomizer() 
	{
		return null;
	}

	/**
	 * Returns the help url of this policy object.  
	 * The string being relative to the help directory.
	 * @return url as string
	 */

	public String getHelpURL()
	{
		return "user_guide/app_ui/policies/appui_policy_access_details/appui_policy_access_detail.html";  
	}

	/**
     *  Core method, invoked whenever this policy needs execution. 
     *  Gets the user properties. From that construct the exact criteria 
     *  properties (criteria for alert to be deleted) as per API
     *  requirement and fetches the data using 
     *  <u>CommonAPI.getObjectNamesWithProps()</u> method. And then 
     *  deletes those alerts.
     */
	public void executeAction(PolicyEvent policyEvt) 
	{
		try
        {
			
            AlertAPI alertAPI = getAlertAPI();
            if(alertAPI == null)
            {
                NmsLogMgr.ALERTERR.log(className+NmsUtil.GetString("Cannot delete the Alerts, AlertAPI is null"), Log.SUMMARY);
                return;
            }

            Properties userProp =(Properties)getUserProperties().clone();

            /** ModTime is removed if it is set as criteria. Because
             * mod Time functionality is handled by Delete Ineterval
             * functionality.
             */
            userProp.remove("modTime");
            /** Delete Interval specifies age of the matching alerts 
             * to be deleted.
             * For example value of 120  specifies alerts
             * which matches the criteria and remains in the same
             * state for more than 120 secs from current time.
             */
            String deleteBefore = (String)userProp.remove("delete Interval");
            if(deleteBefore != null && !(deleteBefore.trim().equals("")))
            {
                long delTime = Long.parseLong(deleteBefore);
                long modTime = System.currentTimeMillis()-(delTime*1000); 
                userProp.setProperty("modTime","<between> 0 and "+modTime);
            }

            /** Severity is assumed to be exact String value. 
             *  for example it has has to be Major,Minor etc. But
             *  in DB, severities are maintained in terms of integer.
             *  Hence these strings are parsed as integers.
             */
            String severity = (String)userProp.remove("severity");
            if(severity != null && !(severity.trim().equals("")))
            {
                String intSeverities = getSeverities(severity);
                if(severity != null && !severity.equals(""))
                {
                    userProp.setProperty("severity",intSeverities);
                }
            }
            /** Fetches Alert Objects entity to be deleted in a vector
             *  by passing criteria Props.
             */
            Vector deleteVector = null;
            try
            {
                deleteVector = alertAPI.getObjectNamesWithProps(userProp);
            }
            catch(Exception e)
            {
                NmsLogMgr.ALERTERR.log(className+NmsUtil.GetString("Matching criteria specified while fetching alerts")+" : "+userProp,Log.SUMMARY);
                NmsLogMgr.ALERTERR.fail(className+NmsUtil.GetString("Exception while fetching Alerts.")+" "+e,e);
            }
            /** If there are objects to be deleted passes the vector
             * of entities to <u>deleteAlerts()</u> method.
             */ 
            if(deleteVector != null && deleteVector.size() > 0)
            {
                deleteAlerts(deleteVector);
            }
        }
		catch(Exception ex)
        {
            NmsLogMgr.ALERTERR.fail(className+NmsUtil.GetString("Error executing AlertDeletePolicy"),ex); 
        }
	}
         
    /** Deletes the Alerts whose entities are supplied in a vector.
     */
    private void deleteAlerts(Vector v)
    {
        if(v == null)
            return;
        AlertAPI alertAPI = getAlertAPI();
        if(alertAPI == null)
        {
            NmsLogMgr.ALERTERR.log(className+NmsUtil.GetString("Cannot delete the Alerts, AlertAPI is null"), Log.SUMMARY);
            return;
        }
        for(int i=0;i<v.size();i++)
        {
            String entity = (String) v.elementAt(i);
            // check out the alert after locking.
            Alert a = null;
            try
            {
                a = (Alert) alertAPI.checkOut(entity,5);
                alertAPI.deleteAlert(a,true);
            }
            catch(Exception e)
            {
                NmsLogMgr.ALERTERR.fail(className+NmsUtil.GetString("Exception occured while trying to check out and delete Alert.")+" "+e,e);
                try
                {
                    if(a !=  null)
                    {
                        alertAPI.unlock(a);
                    }
                }
                catch(Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /** Returns Alert API handle.
     */
    AlertAPI aapi = null;
    private AlertAPI getAlertAPI()
    {
        if(aapi == null)
        {
            aapi = (AlertAPI) NmsUtil.getAPI("AlertAPI");
        }
        return aapi;
    }

    /** Parses String of severities and returns 
     * as integer of severities in string.
     * For Example in default configuration of Severity.conf
     * if supplied argument is of Critical,Major,Minor.
     * it will return 1,2,3.
     */
    private String  getSeverities(String stringSeverities)
    {
        StringBuffer result = new StringBuffer();
        SeverityInfo sevInfo = SeverityInfo.getInstance();
        StringTokenizer tokenizer = new StringTokenizer(stringSeverities,",");
        int spSev = sevInfo.getSpecialPurposeSeverity();
        String stringSev = "";
        String tempStringSev = "";
        boolean addNot=false;
        while(tokenizer.hasMoreTokens())
        {
            tempStringSev =(String)tokenizer.nextToken();
            if(tempStringSev.startsWith("!"))
            {
                stringSev = tempStringSev.substring(1);
                addNot = true;
            }
            else
            {
                stringSev = tempStringSev;
                addNot = false;
            }
            int intSev  = sevInfo.getValue(stringSev.trim());
            if( intSev != spSev)
            {
                String temp = "";
                if(addNot)
                {
                    temp="!"+intSev;
                }
                else
                {
                    temp = intSev+"";
                }
                if(tokenizer.hasMoreTokens())
                {
                    result.append(temp+",");
                }
                else
                {
                    result.append(temp);
                }
            }
        }
        return result.toString();
	}
}




