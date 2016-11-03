/*
$Id: AlertPolicyCustomizer.java,v 1.1.1.1.8.1 2012/01/25 05:09:27 karen.r Exp $
*/
package com.adventnet.nms.policyui;

import java.util.*;
import java.awt.Window;

import com.adventnet.nms.alertdb.FilterList;
import com.adventnet.nms.alertui.EscalationPolicyAdmin;
import com.adventnet.nms.alertdb.AlertFilter;
import com.adventnet.nms.util.NmsUiAPI;

public class AlertPolicyCustomizer extends PolicyObjectCustomizer
{
    private Properties prop = null;
	
    public Properties getProperties()
    {
        return this.prop;
    }

    public void setProperties(Properties p)
    {
        this.prop = p;
    }
	
    public FilterList fl = new FilterList();
    public void init(Properties p)
    {
	NmsPolicyPanel policyPanel = (NmsPolicyPanel)NmsUiAPI.getNmsPanel("Policy");//No i18n
	policyPanel.client.sendReqToGetNotifiers();
        this.prop = p;
        byte[] arr = (byte[]) p.get("AlertFilter");//No Internationalisation

        if (arr == null) return;
		
        AlertFilter alf = new AlertFilter();
        try
        {		
            alf.decodeObj(arr);
            fl.filters.addElement(alf);
        }
        catch(Exception ex)
        {

        }	
    }

    // Down the line let us support this so that the customzier
    // deals with the policyobject directly...
    //public abstract init (PolicyObject obj);

    public  Window getCustomizer()
    {
	EscalationPolicyAdmin filterAdmin = new EscalationPolicyAdmin(fl,this,prop);
    	filterAdmin.pack();
    	return filterAdmin;
    }
}

