/* $Id: CommonFilter.java,v 1.1 2003/01/13 12:04:46 rajalakshmytr Exp $
 *
 * Class which implements FilterClient interface. 
 * This can be used to integrate the above SpecialEvent and SpecialAlert.
 */

package test;

import java.util.*;

import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.eventdb.*;

public class CommonFilter implements FilterClient
{
    public void init()
    {
    }

    public Alert filter(Alert a)
    {
        SpecialAlert alt = new SpecialAlert();
        Properties p = a.getProperties();
        alt.setEntity((String)p.remove("entity"));
        alt.setProperties(p);
        alt.setSpecialAlertEntity("specialTest");
       	alt.setSpecialAlertSource("rainbow");
        alt.setSpecialAlertSeverity(a.getSeverity());
       	alt.setUserProperty("apone","saravana");
        alt.setUserProperty("aptwo","kumar");
        alt.setEvent(a.getEvent());
        return alt;	
    }

    public Event filter(Event e)
    {
        SpecialEvent evt = new SpecialEvent();
        Properties p = e.getProperties();
        String idString =  (String)p.remove("id");
        evt.setId(Integer.parseInt(idString));
        evt.setProperties(p);
        evt.setSpecialEventName("SpecialEvent");
        evt.setSpecialEventIdentifier("kingand");
        evt.setSpecialEventSeverity(e.getSeverity());
        evt.setUserProperty("ev1","kumar");
        evt.setUserProperty("ev2","raj");
        return evt;
    }
}
