/*
   $Id: ExampleStopPolicy.java,v 1.1 2003/06/12 11:35:01 roshini Exp $
 */
// ExamplePolicy.java
package com.adventnet.nms.policies;

import java.util.Properties;
import  com.adventnet.management.policydb.*;
import  com.adventnet.management.policydb.PeriodicPolicyObject;


	 
public class ExampleStopPolicy extends PeriodicPolicyObject//PeriodicPolicyObject 
{
    public ExampleStopPolicy()
    {
        
        period=10;
    
    }
    public String getPolicyObjectCustomizer()
    {
        return "ll";
    }
   
    public String getHelpURL()
    {
        return "URL";
    }
    public void setProperties(Properties p)
    {
	  super.setBaseProperties(p);
    }
    public Properties getProperties()
    {
     Properties p = super.getBaseProperties();   
     return p;         
    }

    public void executeAction(PolicyEvent policyEvt)
    {
    	try{
	System.out.println(" message before calling sleep of 60 sec");
	
	
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
    }
}

