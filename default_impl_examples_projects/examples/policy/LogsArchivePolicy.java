
//$Id: LogsArchivePolicy.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ 


package test;

import java.util.Properties;

import com.adventnet.management.policydb.PolicyEvent;
import com.adventnet.management.policydb.PeriodicPolicyObject;

/**
 * This LogsArchivePolicy is used to take backup of the log file when the 
 * number of log files are exceed with some range(range can be specified 
 * in PolicyConditionImpl). The executeAction of this policy is just a dummy 
 * implementation. Because we will merge PolicyActionImpl into this policy. 
 */

public class LogsArchivePolicy extends PeriodicPolicyObject
{

    /** 
     * This policy will run after every 5 hour to move the older files to
     * archive.
     **/
    
    public LogsArchivePolicy() 
    { 
	super();
	period = 1000*60*60*5;  // we'll check whether to execute every 5 hours.
	
    }
    
    /**
     * Sets the properties for this policy object
     * @param p the property to be set.
     */

    public void setProperties(Properties p)
    {
        super.setBaseProperties(p);
    }
    

    /**
     * Returns the properties of this policy object
     * @return "ArchivePolicy properties"
     */

    public Properties getProperties()
    {
	Properties p = super.getBaseProperties();
	return p;
    }

    /**
     * Returns the help url of this policy object.  The string being relative
     * to the help directory.  
     * @return url as string
     */

    public String getHelpURL()
    {
	return "User_Guide/Java_UI/Policies/policyviewer.html";
    }

    /** 
     * Returns the properties of this policy object 
     * @return "Policy customizer name" 
     */ 
    public String getPolicyObjectCustomizer() {
	return policyObjectCustomizer;
    }


    /**
     * executes action of this policy object.
     * @param policyEvt PolicyEvent
     */

    public  void executeAction(PolicyEvent policyEvt)
    {
    }
    
}



