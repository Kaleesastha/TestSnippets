/* $Id: PerformanceCorrelator.java,v 1.3 2008/11/21 18:38:42 swaminathap Exp $ */
package com.adventnet.nms.example;

import java.util.*;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.management.log.Log;
import com.adventnet.nms.alertdb.EventToAlertCorrelator;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.alertdb.Alert;

/**
 * This class is an example implementation of EvenToAlertCorrelator interface, which explains
 * how efficiently EventToAlertCorrelator can be used to improve performance
 * during Alert update.
 *
 * EventToAlertCorrelator can be used to achieve two requirements.
 * <UL>
 * <LI> To implement, user's rules and user's correlation criteria during new Alert creation
 * and existing Alert update.
 * 
 * <LI> To faster the Alert Update.
 * </UL>
 * <p>
 * getNeededAlertProps() method will be invoked during initialization of the server.
 * This method should return the properties which have to be queried from database
 * during Alert update.
 * createNewAlert() method is responsible for creating new Alerts. Users can apply
 * their rule or correlation criteria in the method, to control new Alert creation.
 * During Alert Update, getAlert() method will be invoked. This method
 * should return the old Alert which has to be updated. Arguments of this
 * method will be the properties given in the getNeededAlertProps() method 
 * and the corresponding Event object. Then createUpdatedMethod() method
 * will be invoked for Alert update. Here also users can insert their rules
 * or correlation criteria for Alert update.
 * <p>
 *
 * It is found that through testing, Alert update operation takes more time
 * since it needs the old Alert to be queried from database. It takes more time to query
 * the whole Alert object from database. Instead of quering all the properties,
 * it will be more beneficial, if we query the most important properties
 * which are needed for Alert update. Those properties can be given in the 
 * getNeededAlertProps() method.
 */

public class PerformanceCorrelator implements EventToAlertCorrelator
{
    /**
	 * Initialization method. Can be used to initialize instance variables.
	 */
	public void init()
    {
   		//just an initialization method
		//called once during initialization
    }
	
	/**
	 * Method which has to return the properties of the Alert which need to be
	 * queried from database during Alert update. This method will be invoked
	 * once during startup. Extended Alert properties can also be given.
	 * Multiple extension of Alert object is also supported here.
	 * This example queries only 'message' field from database.
	 * Along with 'message', must needed properties like 'severity','previousSeverity'
	 * 'createTime' will be queried from database.
	 */
    public Hashtable getNeededAlertProps()
    {
        //method which returns the properties which are needed during Alert update
		//these fields and their values will be passed to getAlert method
		Hashtable table = new Hashtable();
        
		//form the vector of object fields
		Vector props = new Vector();
		props.add("message");//No I18N

		//put vector against the tablename
		table.put("Alert",props);
		return table;
    }

	/**
	 * Method which is responsible for new Alert creation. When ever
	 * an Event comes with severity greater than clear, this method
	 * will be invoked if there is no Alert for that Event in the
	 * database. Users can insert their rules and correlation criteria
	 * here. Should return the new Alert object. This example
	 * by default has no rule. It just creates the Alert object and returns.
	 * @return new Alert object.
	 * @param fail Event object which has to be converted into Alert.
	 */
    public Alert createNewAlert(Event fail)
    {
        //instantiate new Alert object and set the fields from Event object
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
		if (fail.getGroupName() != null && ! fail.getGroupName().equals("")	&& ! fail.getGroupName().equals("null"))
        {
            a.setGroupName(fail.getGroupName());
        }
		return a;
    }

	/**
	 * Method which is responsible for Alert update. When ever an non info Event
	 * is received for the Alert which already exists in the database,
	 * this method will be invoked. Users can implement their rules
	 * or correlation criteria here. By default no rule is applied.
	 * @param fail Event which updates the existing Alert.
	 * @param a Old Alert which is going to be updated through Event fail.
	 * @return Updated Alert object.
	 */
    public Alert createUpdatedAlert(Event fail,Alert a)
    {
        if(!needToUpdate(fail,a))
        {
            return null;
        }


		//update the old Alert from Event object fields
		a.setPreviousSeverity(a.getSeverity());
		a.setSeverity(fail.getSeverity());
		a.setSource(fail.getSource());
		a.setId(fail.getId());
		a.setMessage(fail.getText());
		a.setCategory(fail.getCategory());		
		a.setModTime(fail.getTime()); 
        return a;
	}

	/**
	 * Method which is responsible for returning old Alert which is going to be updated.
	 * This method will be supplied by the properties which are given
	 * in the getNeededAlertProps() method and Event object.
	 * @return Old Alert which is going to be updated.
	 * @param fail Event which updates the existing Alert.
	 * @param prop Alert properties which are queried from database.
	 */
    public Alert getAlert(Event fail,Properties prop)
    {
		//create the old Alert by getting the classname
		String className = (String)prop.remove("className");
        Alert a = null;
        try
        {
            //instantiate the old Alert object.
			a = (Alert)Class.forName(className).newInstance();
        }
        catch(Exception ee)
        {
            NmsLogMgr.ALERTERR.fail(NmsUtil.GetString("Exception while initialzing old Alert "),ee);
            return null;
        }
        a.setEntity(prop.getProperty("entity"));
        a.setOwnerName(prop.getProperty("ownerName"));
        //set the properties which will be modified for each and every event
        a.setProperties(prop);
     
        //get the remaining properties from Event and set it to Alert
		return a;
    }


    private boolean needToUpdate(Event fail,Alert a)
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
