/* $Id: AlertPolicy.java,v 1.2.4.1 2013/07/02 07:10:33 wesley Exp $ */

package com.adventnet.nms.policies;

import java.util.Properties;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Iterator;
import java.io.IOException;
import com.adventnet.management.policydb.PolicyEvent;
import com.adventnet.management.policydb.PeriodicPolicyObject;
import com.adventnet.management.policydb.NmsPolicyAPI;
import com.adventnet.nms.alertdb.AlertFilter;
import com.adventnet.nms.eventdb.FilterAction;
import com.adventnet.nms.eventdb.NotifierList;
import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;
import com.adventnet.nms.util.NmsUtil ;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.severity.SeverityIterator;


 //
 // An Object that extends PeriodicPolicyObject, serves as a simple
 // example for the user to create a PolicyObject of his own.  This 
 // class primarily walks through all the alerts in the database.
 // checks the time at which the alert was last modified.  If the 
 // modified time is greater than the desired time (which is specified
 // by the user) it takes the corresponding action (also specified by
 // the user)
	 
public class AlertPolicy extends PeriodicPolicyObject
{

    static final long serialVersionUID = 5153940426633522685L;

	public AlertPolicy()
	{
            setUserProperty("secondsLived","3600");
		SeverityIterator iterator = SeverityInfo.getInstance().getIterator();
		iterator.moveToHighest();
		int severity = iterator.getPreviousCriticality();
                //		stringSeverity = SeverityInfo.getInstance().getName(severity);
                setUserProperty("severity",""+severity);
                setUserProperty("category","");
                
                // Intializing protected customizer variable;
                policyObjectCustomizer = "com.adventnet.nms.policyui.AlertPolicyCustomizer";
	}
	
	private transient AlertFilter alfFilter = null;
	
	 // If an Alert of "severity" is in the same state for the
	 // time period (in seconds) specified by this variable then
	 // invoke the required things on that alert.  The default 
	 // value is one hour (3600 seconds)

    //	private int  secondsLived = 3600; 
	
	//this hashtable is used to mark the alert upon which the filter
	//has been applied.  we put the alert's key (entity) and the 
	//time the action was taken in the hashtable.

	private static Hashtable markup = new Hashtable();

    //	private String category = "";

	/**
	 * Sets the properties for this policy object
	 * @param p the property to be set.
	 */

	public void setProperties(Properties p)
	{
                String temp;
		temp = (String)p.remove("secondsLived");
                if (temp != null)
		{
                    setUserProperty("secondsLived" , temp.trim());
		}
                else
                {
                    removeUserProperty("secondsLived");
                }
                
               temp = (String) p.remove("severity");
               if( temp != null)
               {
                   setUserProperty("severity" ,temp.trim());
               }
               else
               {
                   removeUserProperty("severity");
               }
               
		temp = (String)p.remove("category");
		if(temp != null)
		{
                    setUserProperty("category",temp.trim());
		}
                else
                {
                    removeUserProperty("category");
                }

                
                temp =(String)p.remove("alertSource");
                if(temp !=null)
                {
                    setUserProperty("alertSource",temp.trim());
                }
                else
                {
                    removeUserProperty("alertSource");
                }

                

                temp =(String)p.remove("entity");
                if(temp !=null)
                {
                    setUserProperty("entity",temp.trim());
                }
                else
                {
                    removeUserProperty("entity");
                }
                
                temp=(String)p.remove("ownerName");
                if(temp!=null)
                {
                    setUserProperty("ownerName",temp.trim());
                }
                else
                {
                    removeUserProperty("ownerName");
                }

                temp=(String)p.remove("alertGroupName");
                if(temp!=null)
                {
                    setUserProperty("alertGroupName",temp.trim());
                }
                else
                {
                    removeUserProperty("alertGroupName");
                }
                



		// The Propertis object would actually contain the
		// the FilterList in a serialized mode..
		// The object in the properties will be just the byte[];
		byte[] arr =  (byte[])p.remove("AlertFilter");
                if ( arr != null)
                {
                    alfFilter = new AlertFilter();

                    try
                    {		
			alfFilter.decodeObj(arr); 	
                    }catch (Exception ex)
                    {
			NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("problem in decoding - AlertPolicy"), ex);
                    }			
                }
		super.setBaseProperties(p);
	}
	/**
	 * Returns the properties of this policy object
	 * @return "AlertPolicy properties"
	 */

	public Properties getProperties()
	{
		Properties p = super.getBaseProperties();
                String temp = null;
		temp = (String)getUserProperty("secondsLived");
		if (temp != null)
		{
                    p.put("secondsLived" , temp);
		}
                temp = (String) getUserProperty("severity");
		if( temp != null)
		{
                    p.put("severity" ,temp);
		}

		temp = (String)getUserProperty("category");
		if(temp != null)
		{
                    p.put("category",temp);
		}
                
		temp = (String)getUserProperty("entity");
		if(temp != null)
		{
                    p.put("entity",temp);
		}

		temp = (String)getUserProperty("ownerName");
		if(temp != null)
		{
                    p.put("ownerName",temp);
		}

		temp = (String)getUserProperty("alertGroupName");
		if(temp != null)
		{
                    p.put("alertGroupName",temp);
		}
		temp = (String)getUserProperty("alertSource");
		if(temp != null)
		{
                    p.put("alertSource",temp);
		}
                
		

		try
		{
			if (alfFilter != null)
				p.put("AlertFilter", alfFilter.encodeObj());
		}catch(Exception ex)
		{
			NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("problem in encoding - AlertPolicy"), ex);
		
		}
		return p;

	}
	

    //	private int severity;
    //	private String stringSeverity="";


	/**
	 * Returns the customizer class of this policy object
	 * @return "Customizer"
	 */

	public String getPolicyObjectCustomizer()
	{
            // returning protected variable
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

    public void setAlertFilter(AlertFilter af)
    {
        this.alfFilter = af;
    }
    public AlertFilter getAlertFilter()
    {
        return alfFilter;
    }

    private Vector removePicked(Vector vec)
    {
	for(Iterator it=vec.iterator();it.hasNext();)
	{
	  Alert al=(Alert)it.next();
	  if(al.getWho()!=null)
	  {
		it.remove();
	  }
	}
	return vec;
    }


	/**
	 * executes action of this policy object.
	 * @param policyEvt PolicyEvent
	 */
	
	public void executeAction(PolicyEvent policyEvt)
	{
		    boolean chkpick=false;
            int severity = Integer.parseInt(getUserProperty("severity"));
            String category = getUserProperty("category");
            int secondsLived = Integer.parseInt(getUserProperty("secondsLived"));
             
            String alertGroupName=getUserProperty("alertGroupName");
            String ownerName=getUserProperty("ownerName");
            String entity=getUserProperty("entity");
	    String policyName = getName();
            String alertSource=getUserProperty("alertSource");
            

		// now this guy walks thru the data base gets the alerts 
		// that were created within the last five years
		// (five years is a arbitary value by specifying five years
		// we think that we can cover almost all alerts)
		// the value for five years in miliseconds is
		// 5*365*24*60*60*1000=157680000000
            
                AlertAPI alertapi = (AlertAPI)NmsUtil.getAPI("AlertAPI");
		if (alertapi == null)
		{
                    NmsLogMgr.POLICYERR.log(NmsUtil.GetString("Alert API is null in AlertPolicy  returning with out executing Policy "),Log.SUMMARY);
			return;

		}

		Alert A1= new Alert();
		A1.setModTime(0);
		Alert A2= new Alert();
		A2.setModTime(System.currentTimeMillis());
		
		Properties alertMatchingProp = new Properties();
                 
                if(alertGroupName != null)
                {
                    alertMatchingProp.put("groupName",alertGroupName);
                }
                if(alertSource != null)
                {
                    alertMatchingProp.put("source",alertSource);
                }
                if(ownerName != null)
                {
                    if(ownerName.equals("All"))
                    {
			chkpick=true;
                    }
                    else
                    {
                        alertMatchingProp.put("who",ownerName);
                    }
                }
                if(entity != null)
                {
                    alertMatchingProp.put("entity",entity);
                }

                

		if (severity != SeverityInfo.getInstance().getSpecialPurposeSeverity())
		{
			alertMatchingProp.put("severity",Integer.toString(severity));
		}
		if(!(category == null || category.equals("")))
		{
			alertMatchingProp.put("category",category);
		}
		String temp = (String)alertMatchingProp.remove("entity");	
		A1.setProperties(alertMatchingProp);
		if(temp != null)
		{
			A1.setEntity(temp);
		}	
		
		Vector vec = null;
		try
		{
			vec = alertapi.getAlerts(A1, A2);
			if (vec == null) 
			{
				return;
			}
			if(chkpick)
			{
		       	    vec =removePicked(vec);
			}
		}
		catch(Exception ex)
		{

			if(ex instanceof com.adventnet.nms.fault.FaultException)
			{
				System.err.println("Exception while getting alerts. ");
				ex.printStackTrace();
			}			

			NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Error in Invoking API - AlertPolicy"), ex);

		}

		if (vec == null)
		{
			return;
		}

		HashMap timeMap = (HashMap)markup.get(policyName);
		if(timeMap == null)
		{
			timeMap = new HashMap();
			// Calling this to remove the deleted policy names from the markup hashtable.
			refresh();
		}

		HashMap alertMap = new HashMap();
		for (Enumeration en = vec.elements();en.hasMoreElements();)
		{
			Alert  al = (Alert)(en.nextElement());
			Date d = new Date();
			long sec = d.getTime();

			String alertEntity = al.getEntity();
			String time_val = (String)timeMap.get(alertEntity);
			
			if((time_val == null) || ((sec - Long.parseLong(time_val)) > ((long)secondsLived * 1000)))
			{
				if ((sec - al.getModTime()) > ((long)secondsLived * 1000)) 
				{
					// Now we need to apply the filter action..
					if (alfFilter  != null)
					{
							for (Enumeration e = alfFilter.actions.elements();
									e.hasMoreElements();) 
							{
                                                            FilterAction act = (FilterAction) e.nextElement();
                                                            Properties actProps = act.getProperties();
                                                            String name = actProps.getProperty("name"); //No I18N
                                                            FilterAction notAction = NotifierList.getFilterAction(name);
                                                            notAction.runAction(al);
							}
							// Once we have applied the filter we should not
							// consider it for some time...So let us mark the alert
							// dirty i.e we have executed the required action 


							alertMap.put(alertEntity,String.valueOf(sec));	
					}
				}
			}
			else
			{
				alertMap.put(alertEntity,time_val);
			}
		}
		markup.put(policyName,alertMap);

	}
	private void refresh()
	{
		synchronized(markup)
		{
			NmsPolicyAPI api = (NmsPolicyAPI)NmsUtil.getAPI("NmsPolicyAPI"); // No I18N
			Vector policies = null;
			try
			{
				policies = api.getPolicyNames();
			}
			catch (Exception ex)
			{
				NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Problem in getting policy names"), ex); // No I18N
			}
			for (Enumeration e = markup.keys();e.hasMoreElements();)
			{
				String key = (String)e.nextElement();
				if ( !policies.contains(key))
				{
					markup.remove(key);	
				}
			}
		}
	}
}
