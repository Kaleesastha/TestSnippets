package test;

import java.util.*;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.management.log.Log;
import com.adventnet.nms.alertdb.EventToAlertCorrelator;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.alertdb.*;
public class MultiCorrelator implements EventToAlertCorrelator
{
    public void init()
    {
   		//just an initialization method
		//called once during initialization
    }

    private int baseAlertCount=1;
    private int specialAlertCount=1;
    private int secondAlertCount=1;
    private  int updateCount=1;

    public Hashtable getNeededAlertProps()
    {
		Hashtable table = new Hashtable();
        
		//form the vector of object fields
		Vector props = new Vector();
		props.add("priority");
        props.add("source");
		table.put("Alert",props);

		Vector specialAlert = new Vector();
        specialAlert.add("specialAlertEntity");
        specialAlert.add("specialAlertSeverity");
        table.put("SpecialAlert",specialAlert);
        
        Vector secondAlert = new Vector();
        secondAlert.add("secondAlertEntity");
        secondAlert.add("secondAlertSource");
        table.put("SecondAlert",secondAlert);
        return table;
    }

    public Alert createNewAlert(Event fail)
    {
        return createAlert(fail);
    }

    private Alert createAlert(Event fail)
    {
        Alert a = null;
        
        if(fail.getSeverity() ==1)
        {
            a = new Alert();
            a.setSource(fail.getEntity()+"BaseAlert"+baseAlertCount);
            baseAlertCount++;
        }
        else if(fail.getSeverity() == 2)
        {
            a = new SpecialAlert();
            
            a.setSource(fail.getEntity()+"SpecialAlert"+specialAlertCount);
            ((SpecialAlert)a).setSpecialAlertEntity(a.getSource());
            ((SpecialAlert)a).setSpecialAlertSource(a.getSource());
            ((SpecialAlert)a).setSpecialAlertSeverity(specialAlertCount);
            
            specialAlertCount++;
        }
        else if(fail.getSeverity()==3)
        {
            a = new SecondAlert();
            
            a.setSource(fail.getEntity()+"SecondAlert"+secondAlertCount);
            ((SecondAlert)a).setSpecialAlertEntity(a.getSource());
            ((SecondAlert)a).setSpecialAlertSource(a.getSource());
            ((SecondAlert)a).setSpecialAlertSeverity(secondAlertCount);
            
            ((SecondAlert)a).setSecondAlertEntity(a.getSource());
            ((SecondAlert)a).setSecondAlertSource(a.getSource());
            ((SecondAlert)a).setSecondAlertSeverity(secondAlertCount);
            secondAlertCount++;
        }
        setBaseAlertProperties(a,fail);
        return a;
    }

    private void setBaseAlertProperties(Alert a,Event fail)
    {
     	a.setId(fail.getId());
		a.setMessage(fail.getText());
		a.setEntity(fail.getEntity());
		a.setSeverity(fail.getSeverity());
		a.setPreviousSeverity(SeverityInfo.getInstance().getSpecialPurposeSeverity()); 
		a.setCategory(fail.getCategory());
		a.setCreateTime(fail.getTime());
		a.setModTime(fail.getTime());
		if (fail.getGroupName() != null && ! fail.getGroupName().equals("")	&& ! fail.getGroupName().equals("null"))
        {
            a.setGroupName(fail.getGroupName());
        }
    }
    
    public Alert createUpdatedAlert(Event fail,Alert a)
    {
		//update the old Alert from Event object fields
		a.setPreviousSeverity(a.getSeverity());
		a.setSeverity(fail.getSeverity());
		a.setId(fail.getId());
		a.setMessage(fail.getText());
		a.setCategory(fail.getCategory());		
		a.setModTime(fail.getTime()); 
        a.setSource(fail.getSource());
		a.setMessage("This is Update "+updateCount);
        updateCount++;
        return a;
	}

    public Alert getAlert(Event fail,Properties prop)
    {
		System.out.println(" Get Alert Properties =="+prop);
				
		//create the old Alert by getting the classname
		String className = (String)prop.remove("className");
        Alert a = null;
        try
        {
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

}
