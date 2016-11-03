/*
 * $Id: AlertLogPolicy.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/

/*
* AlertLogPolicy.java
*/

package com.adventnet.nms.policies;

import java.util.Properties;
import com.adventnet.nms.alertdb.FaultMgr;
import com.adventnet.management.policydb.PolicyObject;
import com.adventnet.management.policydb.PolicyEvent;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;

public class AlertLogPolicy extends PolicyObject
{
    /*
    * Size of AlertLogger. 
    */		

    /**
     * Sets the properties for this alert log policy object
     * @param p the property to be set.
    */
    public AlertLogPolicy()
    {
        setUserProperty("alertLogSize","1000");

        // Setting policy object customizer as null or empty string
        policyObjectCustomizer = "";
    }
    public void setProperties(Properties p)
    {

		String temp = (String)p.remove("alertLogSize");
		if( temp != null)
		{
			try
			{
                            setUserProperty("alertLogSize",temp.trim());
			}catch(NumberFormatException e)
			{
				System.err.println(NmsUtil.GetString("Number Format Exception occured in AlertLogPolicy"));
				e.printStackTrace();
			}
		}
		super.setBaseProperties(p);
    }

    /**
     * Returns the properties of this alert log policy object
     * @return Properties
     */
    public Properties getProperties()
    {
 	Properties p = super.getBaseProperties();
        String temp = getUserProperty("alertLogSize");
        if(temp != null)
        {
            p.put("alertLogSize",temp);
        }
	return p;
    }


    /**
     * Returns the customizer class of this alert log policy object
     * @return null
     */

    public String getPolicyObjectCustomizer()
    {
 	return policyObjectCustomizer;
    }

    /**
     * Returns the help url of this policy object. As, the default customizer associated
     * with this policy uses the help url directly from the help.conf, this method
     * returns null.
     */
    public String getHelpURL()
    {
        return null;
    }

    /**
     * Executes action of this policy object. Sets the window size of AlertLog.
     * @param policyEvt PolicyEvent
     */
	
    public void executeAction(PolicyEvent policyEvt)
    {
        try
        {
            int alertLogSize = Integer.parseInt(getUserProperty("alertLogSize"));
            com.adventnet.nms.eventdb.EventMgr.getFaultMgr().setAlertWindowSize(alertLogSize);
        }catch(Exception e)
        {
            NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Exception while executing AlertLogPolicy"),e);
        }
    }	
}
