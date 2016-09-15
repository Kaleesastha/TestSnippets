// Decompiled by Jad v1.5.7e. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommonFilter.java

package com.adventnet.nms.extend;

import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.FilterClient;
import java.util.Hashtable;

// Referenced classes of package com.adventnet.nms.extend:
//            SpecialAlert, SpecialEvent

public class CommonFilter
    implements FilterClient
{

    public CommonFilter()
    {
    }

    public void init()
    {
    }

    public Alert filter(Alert alert)
    {
        SpecialAlert specialalert = new SpecialAlert();
        java.util.Properties properties = alert.getProperties();
        specialalert.setEntity((String)properties.remove("entity"));
        specialalert.setProperties(properties);
        specialalert.setSpecialAlertEntity("specialTest" + alert.getId());
        specialalert.setSpecialAlertSource("rainbow" + alert.getId());
        specialalert.setSpecialAlertSeverity(alert.getId() % 7);
        specialalert.setUserProperty("up1", "" + alert.getId());
        specialalert.setUserProperty("up2", "uv2");
        return specialalert;
    }

   public Event filter(Event event)
    {
        SpecialEvent specialevent = new SpecialEvent();
        java.util.Properties properties = event.getProperties();
        String s = (String)properties.remove("id");
        specialevent.setId(Integer.parseInt(s));
        specialevent.setProperties(properties);
        specialevent.setSpecialEventName("SpecialEvent" + event.getId());
        specialevent.setSpecialEventIdentifier("king" + event.getId());
        specialevent.setSpecialEventSeverity(event.getId() % 7);
        specialevent.setUserProperty("ev1", "" + event.getId());
        specialevent.setUserProperty("ev2", "uv");
        return specialevent;
    }

  }

