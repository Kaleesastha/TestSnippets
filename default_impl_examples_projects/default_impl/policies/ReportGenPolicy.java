/* 
$Id: ReportGenPolicy.java,v 1.1.1.1.4.1 2012/04/05 08:59:02 wesley Exp $ 
*/

package com.adventnet.nms.policies;

import java.util.Properties;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import com.adventnet.management.policydb.PolicyEvent;
import com.adventnet.management.policydb.PolicyObject;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.poll.Reporter;
import com.adventnet.nms.server.dataarchiver.ReportIfc;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil ;
import com.adventnet.management.log.Log;
 	 
public class ReportGenPolicy extends PolicyObject
{

    //	private String reportGenClassNames = "";
    
	/**
	 * Sets the properties for this policy object
	 * @param p the property to be set.
	 */
    public ReportGenPolicy()
    {
        // setting initial values
        setUserProperty("ReportGenClassNames" , "test.InterfacesReport");
    }
	public void setProperties(Properties p)
	{
		String temp = (String)p.remove("ReportGenClassNames");
		if(temp != null)
		{
                    setUserProperty("ReportGenClassNames" , temp.trim());
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
                String temp = getUserProperty("ReportGenClassNames");
                if(temp != null)
                {
                    p.put("ReportGenClassNames",temp);
                }
		return p;
	}
	
	/**
	 * Gets the report generation class names
	 * @return class names as a comma seperated string
	 */
	public String getReportGenerationClassNames()
	{
            return getUserProperty("ReportGenClassNames");
	}
	/**
	 *  sets  the class names which are used for generating reports. Specify fully
	 * qualified class names. To specify multiple classnames use
	 * comma as field seperator
	 * @param classNames classes for running report generator
	 */
	public void setReportGenerationClassNames(String classNames)
	{
		if(classNames != null)
                {
                    setUserProperty("ReportGenClassNames" , classNames.trim());
                }
			
	}

	/**
	 * Returns the customizer class of this policy object
	 * @return "Customizer"
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
            String reportGenClassNames = getUserProperty("ReportGenClassNames");
            if(reportGenClassNames == null)
            {
                NmsLogMgr.POLICYERR.log(NmsUtil.GetString("Specified class name for ReportGenPolicy is null. Can not execute ReportGenPolicy") , Log.VERBOSE);
            }

		PollAPI api = (PollAPI)NmsUtil.getAPI("PollAPI");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		String dateString = (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + "-" + cal.get(Calendar.YEAR);
		
		if(api == null)
		{
			NmsLogMgr.POLICYERR.log(NmsUtil.GetString("PollAPI is null. can not proceed further") , Log.SUMMARY);
                        return;
		}
		
		StringTokenizer st = new StringTokenizer(reportGenClassNames , ",");
		while(st.hasMoreTokens())
		{
			try
			{
				Object reportClass = Class.forName(st.nextToken().trim()).newInstance();
				if(reportClass instanceof Reporter)
				{
					Reporter report = (Reporter)reportClass;
					report.runReport(api, dateString);
				}
				else if(reportClass instanceof ReportIfc)
				{
					ReportIfc reportifc = (ReportIfc)reportClass;
					reportifc.runReport(null);
				}
				
			}
			catch(Exception e)
			{
					NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Exception ReportGenPolicy"), e);
			}
		}
	}
}

