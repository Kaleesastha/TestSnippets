// Decompiled by Jad v1.5.7e. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommonFilter3.java

package com.adventnet.nms.extend;

import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.FilterClient;
import java.util.Hashtable;

// Referenced classes of package com.adventnet.nms.extend:
//            SpecialAlert, SpecialEvent3

public class CommonFilter2
    implements FilterClient
{

    public CommonFilter2()
    {
    }

    public void init()
    {
    }

    public Alert filter(Alert alert)
    {
        SpecialAlert2 specialalert2 = new SpecialAlert2();
        java.util.Properties properties = alert.getProperties();
        specialalert2.setEntity((String)properties.remove("entity"));
        specialalert2.setProperties(properties);
        specialalert2.setSpecialAlertEntity2("specialTest" + alert.getId());
        specialalert2.setSpecialAlertSource("rainbow" + alert.getId());
        specialalert2.setSpecialAlertSeverity2(alert.getId() % 7);
        specialalert2.setUserProperty("up1", String.valueOf(alert.getId()));
        specialalert2.setUserProperty("up3", "uv3");
        return specialalert2;
    }

    public Event filter(Event event)
    {  
        SpecialEvent2 specialevent2 = new SpecialEvent2();
        java.util.Properties properties = event.getProperties();
        String s = (String)properties.remove("id");
        specialevent2.setId(Integer.parseInt(s));
        specialevent2.setProperties(properties);
        specialevent2.setSpecialEventName("SpecialEvent" + event.getId());
        specialevent2.setSpecialEventIdentifier("king" + event.getId());
        specialevent2.setSpecialEventIdentity(event.getId() % 7);
        specialevent2.setUserProperty("ev1", String.valueOf(event.getId()));
        specialevent2.setUserProperty("ev3", "uv3");
        return specialevent2;
     
    }
}
