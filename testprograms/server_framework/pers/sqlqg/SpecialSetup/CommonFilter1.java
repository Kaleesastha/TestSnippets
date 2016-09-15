// Decompiled by Jad v1.5.7e. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommonFilter1.java

package com.adventnet.nms.extend;

import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.FilterClient;
import java.util.Hashtable;

// Referenced classes of package com.adventnet.nms.extend:
//            SpecialAlert1, SpecialEvent1

public class CommonFilter1
    implements FilterClient
{

    public CommonFilter1()
    {
    }

    public void init()
    {
    }

    public Alert filter(Alert alert)
    {
        SpecialAlert1 specialalert1 = new SpecialAlert1();
        java.util.Properties properties = alert.getProperties();
        specialalert1.setEntity((String)properties.remove("entity"));
        specialalert1.setProperties(properties);
        specialalert1.setSpecialAlertEntity1("specialTest1" + alert.getId());
        specialalert1.setSpecialAlertSource("rainbow" + alert.getId());
        specialalert1.setSpecialAlertSeverity1(alert.getId() % 7);
        specialalert1.setUserProperty("up1", String.valueOf(alert.getId()));
        specialalert1.setUserProperty("up3", "kumar");
        return specialalert1;
    }

    public Event filter(Event event)
    {
        SpecialEvent1 specialevent1 = new SpecialEvent1();
        java.util.Properties properties = event.getProperties();
        String s = (String)properties.remove("id");
        specialevent1.setId(Integer.parseInt(s));
        specialevent1.setProperties(properties);
        specialevent1.setSpecialEventName("SpecialEvent" + event.getId());
        specialevent1.setSpecialEventId("king" + event.getId());
        specialevent1.setSpecialEventSeverity(event.getId() % 7);
        specialevent1.setUserProperty("ev1", String.valueOf(event.getId()));
        specialevent1.setUserProperty("ev2", "uv1");
        return specialevent1;

      
    }
}
