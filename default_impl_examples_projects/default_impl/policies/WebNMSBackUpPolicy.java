/* $Id: WebNMSBackUpPolicy.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package com.adventnet.nms.policies;

import java.util.Properties;
import com.adventnet.management.policydb.PolicyEvent;
import com.adventnet.management.policydb.PolicyObject;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil ;

 // An Object that extends PolicyObject, serves as a simple
 // example for the user to create a PolicyObject of his own.
 // This policy primarily walks through all the nodes in the 
 // database. Picks up the nodes which matches the criterias 
 // given by the user, and changes their tester to the tester 
 // specified by the user.
	 
public class WebNMSBackUpPolicy extends PolicyObject
{
    public WebNMSBackUpPolicy()
    {
        setUserProperty("BackUpClassNames","jdbc.BackUpImpl");//No Internationalisation
    }
	/**
	 * Sets the properties for this policy object
	 * @param p the property to be set.
	 */
	public void setProperties(Properties p)
	{
		String temp = (String)p.remove("BackUpClassNames");//No Internationalisation
		if(temp != null && temp.trim().length() > 0)
		{
                    setUserProperty("BackUpClassNames",temp.trim());//No Internationalisation
		}
		super.setBaseProperties(p);
	}

	/**
	 * Returns the properties of this policy object
	 * @return Properties
	 */
    public Properties getProperties()
    {
        Properties p = super.getBaseProperties();
        String temp = getUserProperty("BackUpClassNames");//No Internationalisation
        if(temp != null)
        {
            p.put("BackUpClassNames",temp);//No Internationalisation
        }

        return p;
    }
	
		

	/**
	 * Returns the customizer class of this policy object
	 * @return "Customizer"//No Internationalisation
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
	 * executes action of this policy object.
	 * @param policyEvt PolicyEvent
	 */
    public void executeAction(PolicyEvent policyEvt)
    {
        String backUpClassNames = getUserProperty("BackUpClassNames");//No Internationalisation
        if( backUpClassNames == null || backUpClassNames.length() == 0)
        {
            System.out.println(NmsUtil.GetString("No Class name is specified to run BackUp"));
            return;
        }
        try
        {
            com.adventnet.nms.startnms.StartWebNMSBackUp backup = new com.adventnet.nms.startnms.StartWebNMSBackUp(backUpClassNames);
        }
        catch(Exception e)
        {
            NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Exception WebNMSBackUpPolicy"), e);
        }
    }

}

