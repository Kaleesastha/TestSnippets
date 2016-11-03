
/**
 * $Id: DefaultNotificationFilter.java,v 1.2 2008/11/10 11:16:24 sudharshan Exp $
 */
package test;

import com.adventnet.management.tl1.TL1Property;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.tl1.TL1NotificationFilter;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.tl1.message.TL1AutonomousMessage;
import com.adventnet.tl1.message.TL1TextBlock;

public class DefaultNotificationFilter implements TL1NotificationFilter
{
	public DefaultNotificationFilter ()
	{
	}

	public Object applyFilter (TL1Property prop, TL1AutonomousMessage messg)
	{
		Event ie = convertToEvent(prop, messg, System.currentTimeMillis());
		if (ie == null) 
		{ 
			return messg;
		}
		ie.setUserProperty("agentProtocol", "TL1");
		return ie;
	}

	private Event convertToEvent(TL1Property prop,TL1AutonomousMessage autoMess, long eventTime)
	{

		try 
		{
			if ((prop == null) || (autoMess == null)) return null;
			String alarmcode = (autoMess.getAutoId()).getAlarmCode();
			String outputcode = (autoMess.getAutoId()).getOutputCode();
			String severity = null;
			if (outputcode.toLowerCase().indexOf("evt") != -1)
				severity = "INFO";
			else if(alarmcode.equals("AA") || alarmcode.equals("A "))
				severity = "CLEAR";
			else if(alarmcode.equals("*A") || alarmcode.equals("* "))
				severity = "MINOR";
			else if(alarmcode.equals("**"))
				severity = "MAJOR";
			else if(alarmcode.equals("*C"))
				severity = "CRITICAL";
			else 
				severity = "UNKNOWN";     //"CLEAR" is another option

            
			String atag = new String((autoMess.getAutoId()).getATag());
			String sourceId = (autoMess.getHeader()).getSourceId();
			TL1TextBlock textblock = autoMess.getTextBlock();
			String text = null;
			if(textblock != null)
			{
				text = (autoMess.getTextBlock()).toString();
			}
			else
			{
				text ="Autonomous message from " + sourceId;   	
			}

			String dateNtime = autoMess.getHeader().getDate().toString() + autoMess.getHeader().getTime().toString();
			String termination = "UNFINISHED";
			if (autoMess.getTerminator() == ';') 
			{
				termination = "FINISHED";
			}

			String ipaddress = ((TL1Property)prop).getTargetHost();
			String objName = prop.getUserProperty("host_mo_name");
			if ((ipaddress == null)) return null;
			String source = ipaddress;
			String entity = ipaddress + "-" + outputcode;
			if(sourceId != null)
			{
				source = sourceId;
				entity = sourceId+"-"+outputcode;
			}
			if (objName != null) 
			{
				source = objName;
				entity = objName + "-" + outputcode;
			}
			Event evt = new Event();
			evt.setCategory("TL1AutonomousMessage");//No I18N
			evt.setText(text);

			int sev = SeverityInfo.getInstance().getValue(severity);
		    if (!SeverityInfo.getInstance().contains(sev))
		    {
				sev = SeverityInfo.getInstance().getInfo();
		    }
			evt.setSeverity(sev);
			
			evt.setEntity(entity);
			evt.setNode(source);
			evt.setSource(source);
			evt.setGroupName(source);
			evt.setTime(eventTime);
			return evt;	    
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return null;
		}
	}
}
