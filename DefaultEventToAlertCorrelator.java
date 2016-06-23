/* $Id : $ */
package com.adventnet.nms.alertdb;

import java.util.*;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.management.log.Log;

import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.EventMgr;

public class DefaultEventToAlertCorrelator implements EventToAlertCorrelator
{
    
    private AlertAPI api;
    private boolean enhancedCorrelation=false;
    public void init()
    {
        enhancedCorrelation= EnhancedEACorrelator.getInstance().jitterCorrelation;
        api = getAlertAPI();
    }

    public Hashtable getNeededAlertProps()
    {
        return null;
    }

    public Alert createNewAlert(Event fail)
    {

        if(enhancedCorrelation)
            {
                String state=fail.getProperties().getProperty("specialState");
                if(state!=null && state.equals("unMarkingEvent"))
                    {
                        return null;
                    }
            }
        Alert a = new Alert();
		a.setId(fail.getId());
		a.setMessage(fail.getText());
		a.setSource(fail.getSource());
		a.setSeverity(fail.getSeverity());
		a.setPreviousSeverity(SeverityInfo.getInstance().getSpecialPurposeSeverity()); 
		a.setCategory(fail.getCategory());
		a.setEntity(fail.getEntity());
		a.setOwnerName(fail.getOwnerName());
		a.setCreateTime(fail.getTime());
		a.setModTime(fail.getTime());
		a.setWebNMS(fail.getWebNMS());
        if ((fail.getText() == null) || (fail.getText().trim().equals("")) || (fail.getText().trim().equalsIgnoreCase("null")))
        {
                a.setNotes("<New Alert Generated with null text>");
        }
		else
        {
                a.setNotes(fail.getText());
        }
		if (fail.getGroupName() != null && ! fail.getGroupName().equals("")	&& ! fail.getGroupName().equals("null"))
        {
                a.setGroupName(fail.getGroupName());
        }
		return a;
    }

    public Alert createUpdatedAlert(Event fail,Alert a)
    {
        if(!doWeNeedToUpdate(a,fail))
        {
                return null;
        }
        a.setPreviousSeverity(a.getSeverity());
		a.setSeverity(fail.getSeverity());
		a.setSource(fail.getSource());
		a.setId(fail.getId());
		a.setMessage(fail.getText());
		a.setCategory(fail.getCategory());		
		a.setModTime(fail.getTime()); 
        a.setWebNMS(fail.getWebNMS());
		return a;
	}

    public Alert getAlert(Event fail,Properties prop)
    {
        if(api == null)
            api = getAlertAPI();
        if(api == null)
        {
            NmsLogMgr.ALERTUSER.log(NmsUtil.GetString("AlertAPI reference is 'null'.  Unable to get Alert with entity : ")+fail.getEntity(),Log.INTERMEDIATE_DETAIL);
            return null;
        }
        String alertEntity = NmsUtil.getKey(fail.getEntity(),fail.getOwnerName());
        Alert a = null;
        try
        {
            a = api.getAlert(alertEntity);
        }
        catch(Exception ee)
        {
            NmsLogMgr.ALERTERR.fail(NmsUtil.GetString("Error while checking out Alert object "),ee);
        }
        return a;
    }
   	
    private boolean doWeNeedToUpdate(Alert a,Event fail) 
    {
        if(enhancedCorrelation)
            {
                String state=fail.getProperties().getProperty("specialState");
                Properties props=a.getProperties();
                if(state!=null)
                    {
                        if(state.equals("unMarkingEvent"))
                            {
                                a.setUserProperty("specialState","NULL"); //No Internationalization
                            }
                        else
                            {
                                a.setUserProperty("specialState",state); //No Internationalization
                            }
                        return true;
                    }
                state=props.getProperty("specialState");
                if(state!=null && (state.equals("jitter")|| state.equals("repeater")))
                    {
                        return false;
                    }
                return checkForUpdate(a,fail);
            }
        else
            {
                return checkForUpdate(a,fail);
            }
        /*Commented as part of ACE & added as a checkForUpdate method

        // If the alert is no longer active do not update
		if (SeverityInfo.getInstance().isNoCriticality(a.getSeverity())) 
        {
                return false;
        }
		if (a.getSeverity()!=fail.getSeverity()) 
        {
                return true;
        }
		
        if((a.getSource() == null) || (fail.getSource() == null))
        {
                return true;
        }
		if(!a.getSource().equals(fail.getSource())) 
        {
                return true;
        }
	
        if (a.getMessage() == null && fail.getText() == null)
        {
                return false;
        }
        else if((a.getMessage() == null && fail.getText()!=null) || (a.getMessage() !=null && fail.getText() == null))
        {
                return true;
        }
        else if (!a.getMessage().equals(fail.getText()))
        {
                return true;
        }

	    return false;
        */
	}
    /* Utility method to get the AlertAPI reference.  
     */
    private AlertAPI getAlertAPI()
    {
        AlertAPI api = null;
        int i = 0;
	api = (AlertAPI)NmsUtil.getAPI("AlertAPI");//No I18N
        while(api == null && i<10)
        {
            try
            {
                Thread.sleep(100);
            }
            catch(Exception e)
            {}
	    api = (AlertAPI)NmsUtil.getAPI("AlertAPI");//No I18N
            i++;
        }
        return api;
    }

    private boolean checkForUpdate(Alert a,Event fail)
    {
        if (SeverityInfo.getInstance().isNoCriticality(a.getSeverity())) 
            {
                return false;
            }
        if (a.getSeverity()!=fail.getSeverity()) 
            {
                return true;
            }
        
        if((a.getSource() == null) || (fail.getSource() == null))
            {
                return true;
            }
        if(!a.getSource().equals(fail.getSource())) 
            {
                return true;
            }
	
        if (a.getMessage() == null && fail.getText() == null)
            {
                return false;
            }
        else if((a.getMessage() == null && fail.getText()!=null) || (a.getMessage() !=null && fail.getText() == null))
            {
                return true;
            }
        else if (!a.getMessage().equals(fail.getText()))
            {
                return true;
            }
        return false;
    }
}










