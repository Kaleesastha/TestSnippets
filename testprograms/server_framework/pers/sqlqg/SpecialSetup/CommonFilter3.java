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

public class CommonFilter3
    implements FilterClient
{

    public CommonFilter3()
    {
    }

    public void init()
    {
    }

    public Alert filter(Alert alert)
    {
        SpecialAlert3 specialalert3 = new SpecialAlert3();
        java.util.Properties properties = alert.getProperties();
        specialalert3.setEntity((String)properties.remove("entity"));
        specialalert3.setProperties(properties);
        specialalert3.setSpecialAlertEntity1("specialTest" + alert.getId());
        specialalert3.setSpecialAlertSource("rainbow" + alert.getId());
        specialalert3.setSpecialAlertSeverity1(alert.getId() % 7);
        specialalert3.setUserProperty("up", String.valueOf(alert.getId()));
        specialalert3.setUserProperty("up3", "up3");
        return specialalert3;
    }

    public Event filter(Event event)
    {
        SpecialEvent3 specialevent3 = new SpecialEvent3();
        java.util.Properties properties = event.getProperties();
        String s = (String)properties.remove("id");
        specialevent3.setId(Integer.parseInt(s));
        specialevent3.setProperties(properties);
        specialevent3.setSpecialEventName("SpecialEvent" + event.getId());
        specialevent3.setSpecialEventIdentifier("king" + event.getId());
        specialevent3.setSpecialEventValue(event.getId() % 7);
        specialevent3.setUserProperty("ev1", String.valueOf(event.getId()));
        specialevent3.setUserProperty("ev2", "uv2");
        return specialevent3;
     
    }
}
