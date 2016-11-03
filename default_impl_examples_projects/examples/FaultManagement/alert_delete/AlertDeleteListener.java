//$Id: AlertDeleteListener.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/**
 *<b> @(#)AlertDeleteListener.java	</b>
 * Copyright (c) 1996 Advent Network Management, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 */

package com.adventnet.nms.fault;

//Java imports.
import java.util.Vector;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Enumeration;

// Web Nms imports.
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.management.log.*;

/**  An example Alert listener. This Listener will delete
 * the Alerts that are cleared and notified to this listener.
  @author Karthick Srinivasan 
  @version 1.0
*/

public class AlertDeleteListener implements AlertListener
{

    /** variable to specify the classname while logging messages.
     */
    String className = "AlertDeleteListener : ";
    
    /** Empty constructor.  Initialization is done here.
     */
    public AlertDeleteListener()
    {
        initialize();             
    }

    /** This method will be invoked by WebNMS, whenever an Alert
     *  related operation is performed with the alert details.
     *  If the received Alert is of is of clear severity it will
     *  delete the clear alert.
     */
    public void listenAlert(AlertActionInformer aai)
    {
        if(aai.isBatchUpdate())
        {
            // Get the list of Alerts updated
            Vector alertList = aai.getAlertList();
            if(alertList != null)
            {
                for(int i=0;i<alertList.size();i++)
                {
                    Alert a = (Alert) alertList.elementAt(i);
                    /** If the alert is of clear severity 
                     * it will be deleted.
                     */
                    if(checkForDelete(a))
                    {
                        deleteAlert(a);
                    }
                }
            }
        }
        else
        {
            Alert a = aai.getAlert();
            /** If the alert is of clear severity 
             * it will be deleted.
             */
            if(checkForDelete(a))
            {
                deleteAlert(a);
            }
        }
    }

    /** This method will be invoked by WebNMS whenever bulk
     *  delete or purging of Alerts occur.
     */
    public void update(XMLNode xmlNode)
    {
        NmsLogMgr.ALERTUSER.log(className+NmsUtil.GetString("Bulk delete notification for Alerts is received."),Log.SUMMARY);
    }

    // run method which does the processing of deleting Alerts.
    
    /** This method is invoked on starup of the server. To delete
     *  all the clear alerts generated in previous run.
     */
    private void deleteAlerts(Vector v)
    {
        if(v == null)
            return;
        for(int i=0;i<v.size();i++)
        {
            String entity = (String) v.elementAt(i);
            /** check out the alert after locking - 
             *  if ALERT_LOCK is set to 'true'.
             */
            Alert a = null;
            try
            {
                a = (Alert) alertAPI.checkOut(entity,5);
                                
                if(a != null && a.getSeverity() == SeverityInfo.getInstance().getClear())
                {
                    alertAPI.deleteAlert(a,true);
                }
                else
                {
                    alertAPI.unlock(a);
                }
            }
            catch(AccessDeniedException ae)
            {
                NmsLogMgr.ALERTERR.fail(className+NmsUtil.GetString("Exception occured while trying to check out and delete Alert.")+" "+ ae,ae);
            }
            catch(Exception e)
            {
                NmsLogMgr.ALERTERR.fail(className+NmsUtil.GetString("Exception occured while trying to check out and delete Alert.")+" "+e,e);
                unlock(a);
            }
        }
    }
    /** Unlocks the given alert.
     */
    private void unlock(Alert alert)
    {
        try
        {
            if(alertAPI != null)
            {
                alertAPI.unlock(alert);
            }
         
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    /** Deletes the given Alert Object.
     */    
    private void  deleteAlert(Alert a)
    {
        Alert alert = null;
        try
        {
            if(a != null )
            {
                alert =  alertAPI.lock(a,LockableObject.WRITE_LOCK,5);
                alertAPI.deleteAlert(alert,true);
            }
        }
        catch(AccessDeniedException ae)
        {
            NmsLogMgr.ALERTERR.fail(className+NmsUtil.GetString("Cannot delete Alerts.")+" "+ae,ae);
        }
        catch(Exception e)
        {
            NmsLogMgr.ALERTERR.fail(className+NmsUtil.GetString("Cannot delete Alerts.")+" "+e,e);
            if(alert != null)
            {
                unlock(alert);
            }
        }
    }

    /** initialization work done here.
     */
    private void initialize()
    {
        registerForNotification(this);
        Vector deleteVector = getInitialAlerts(SeverityInfo.getInstance().getClear());
        if(deleteVector != null)
        {
            deleteAlerts(deleteVector);
        }
    }
    

    /** This method will register for Alert notification.
     */
    private void registerForNotification(AlertListener alertListener)
    {
        try
        {
            /** This logic can be changed to wait for some time 
             *  before breaking  the loop, bcos this may become
             *  an infinite loop.
             **/
            while(alertAPI == null)
            {
                alertAPI = getAlertAPI();
                Thread.sleep(1000);
            }
            // register for alert notifications
            alertAPI.addAlertListener(alertListener);
        }
        catch(Exception e)
        {
            NmsLogMgr.ALERTERR.fail(className+NmsUtil.GetString("Exception occured while fetching AlertAPI and registering as AlertListener.")+ " "+e,e);
        }
    }
     
    /** This enables to deregister the listener. This method will be 
     *  called from AlertDeleteHandler process while it receives server
     * shutdown notification.
     */
    public void deregisterForNotification()
    {
        deregisterForNotification(this);
    }
    /** Inetrnal method to Deregister for notifications.
     */
    private void deregisterForNotification(AlertListener alertListener)
    {
        if(alertAPI != null)
        {
            try
            {
                alertAPI.removeAlertListener(alertListener);
            }
            catch(Exception e)
            {
                NmsLogMgr.ALERTERR.fail(className+NmsUtil.GetString("Exception occured while deregistering Alert listener.")+" "+e,e);
            }
        }
    }

    /** return AlertAPI handle.
     */
    private AlertAPI alertAPI;
    private AlertAPI getAlertAPI()
    {
        if(alertAPI == null)
        {
            alertAPI= (AlertAPI) NmsUtil.getAPI("AlertAPI");
        }
        return alertAPI;
    }

    /** This method will check if the alert needs to be deleted if so it 
     * will return true else false.
     */
    
    private boolean checkForDelete(Alert a)
    {
        if(a.getSeverity() == SeverityInfo.getInstance().getClear()) 
        {
            /* An alert is cleared.  Delete the alert.
             */
            return true;
        }
        else
        {
            return false;
        }
    }

    /** This will get called during start up of the server. This will
     * return the alerts entity in a vector matching given severity 
     * i.e. 'clear' severity.
     */
    private Vector getInitialAlerts(int severity)
    {
        Properties prop = new Properties();
        String severityStr = "" + severity;
        prop.put("severity",severityStr);
        try
        {
            return alertAPI.getObjectNamesWithProps(prop);
        }
        catch(Exception e)
        {
            NmsLogMgr.ALERTERR.fail(className+NmsUtil.GetString("Exception while fetching Alerts.")+" "+e,e);
        }
        return null;
    }
}










