/*
   $Id: ExamplePolicy.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 */
// ExamplePolicy.java
package test.policy.policies;

import java.util.Properties;
import  com.adventnet.management.policydb.PolicyEvent;
import  com.adventnet.management.policydb.PeriodicPolicyObject;


	 
public class ExamplePolicy extends PeriodicPolicyObject
{

    /**
     * Sets the properties for this policy object
     * @param p the property to be set.
     */

    public ExamplePolicy()
    {
        policyObjectCustomizer = "test.policy.policyui.ExamplePolicyCustomizer";
        setUserProperty("message","testMessage");
    }
    public void setProperties(Properties p)
    {
        String str = null;
        str = (String)p.remove("message");
        if(str != null)
        {
            setUserProperty("message",str);
        }
        super.setBaseProperties(p);

    }
    /**
     * Returns the properties of this policy object
     * @return "Policy properties"
     */

    public Properties getProperties()
    {
        Properties p = super.getBaseProperties();
        p.put("message",getUserProperty("message"));
        return p;

    }

    // you can define your own customizer class
    public String getPolicyObjectCustomizer()
    {
        return policyObjectCustomizer;

    }
    /**
     * Returns the help url of this policy object.  The string being relative
     * to the help directory.  Since This Policy Object has a customizer, 
     * specifying help string has no effect because everything is handled
     * by the customizer.  The help string is specified as an illustration.
     * This is more or less equivalent to specifying null here.
     * @return url as string
     */

    public String getHelpURL()
    {
        return "developer_guide/configuration/server_configuration/policy/policy_object_menus.html";
    }

    /**
     * executes action of this policy object.
     * @param policyEvt PolicyEvent
     */
	
    //  You can see the output in <WebNMS-HOME>/logs/stdout.txt
    public void executeAction(PolicyEvent policyEvt)
    {
        String message = getUserProperty("message");
        System.out.println("From ExamplePolicy "+name+" message is :"+message);
    }
}

