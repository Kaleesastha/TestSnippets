package test;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.eventdb.*;
import java.util.*;

public class DoubleFilter implements FilterClient
{
	double testing=123.45;
    public void init()
    {
    }
	
    public Alert filter(Alert a)
    {
		DoubleAlert alt = new DoubleAlert();
		Properties p = a.getProperties();
		alt.setEntity((String)p.remove("entity"));
		alt.setProperties(p);
		alt.setSpecialAlertEntity("specialTest");
       	alt.setSpecialAlertSource("rainbow");
		alt.setSpecialAlertSeverity(a.getSeverity());
       	alt.setUserProperty("apone","saravana");
		alt.setUserProperty("aptwo","kumar");
		testing+=1;
		alt.setTesting(testing);
		return alt;	
	}
	public Event filter(Event e)
    {
		DoubleEvent evt = new DoubleEvent();
		Properties p = e.getProperties();
		evt.setProperties(p);
		evt.setSpecialEventName("SpecialEvent");
		evt.setSpecialEventIdentifier("kingand");
		evt.setSpecialEventSeverity(e.getSeverity());
		evt.setUserProperty("ev1","kumar");
		evt.setUserProperty("ev2","raj");
		testing+=1;
		evt.setTesting(testing);
		return evt;
    }
}
