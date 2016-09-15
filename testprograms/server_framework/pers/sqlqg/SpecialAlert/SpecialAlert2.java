// Decompiled by Jad v1.5.7e. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SpecialAlert2.java

package com.adventnet.nms.extend;

import com.adventnet.nms.alertdb.Alert;
import com.adventnet.util.NVProperties;
import java.util.Hashtable;
import java.util.Properties;

public class SpecialAlert2 extends Alert
{

    public SpecialAlert2()
    {
        specialAlertEntity2 = "testEntity";
        specialAlertSeverity2 = 3;
        specialAlertSource = "testSource";
    }

    public String getEntity()
    {
        return super.getEntity();
    }

    public void setEntity(String s)
    {
        super.setEntity(s);
    }

    public void setOwnerName(String s)
    {
        super.setOwnerName(s);
    }

    public String getOwnerName()
    {
        return super.getOwnerName();
    }

    public void setSpecialAlertEntity2(String s)
    {
        specialAlertEntity2 = s;
    }

    public String getSpecialAlertEntity2()
    {
        return specialAlertEntity2;
    }

    public void setSpecialAlertSeverity2(int i)
    {
        specialAlertSeverity2 = i;
    }

    public int getSpecialAlertSeverity2()
    {
        return specialAlertSeverity2;
    }

    public void setSpecialAlertSource(String s)
    {
        specialAlertSource = s;
    }

    public String getSpecialAlertSource()
    {
        return specialAlertSource;
    }

    public void setProperties(Properties properties)
    {
        String s = null;
        if((s = (String)properties.remove("specialAlertEntity2")) != null)
            specialAlertEntity2 = s;
        if((s = (String)properties.remove("specialAlertSource")) != null)
            specialAlertSource = s;
        if((s = (String)properties.remove("specialAlertSeverity2")) != null)
            specialAlertSeverity2 = Integer.parseInt(s);
        super.setProperties(properties);
    }

    public Properties getProperties()
    {
        NVProperties nvproperties = (NVProperties)super.getProperties();
        if(specialAlertEntity2 != null)
            nvproperties.put("specialAlertEntity2", specialAlertEntity2);
        if(specialAlertSource != null)
            nvproperties.put("specialAlertSource", specialAlertSource);
        nvproperties.put("specialAlertSeverity2", String.valueOf(specialAlertSeverity2));
        return nvproperties;
    }

    private String specialAlertEntity2;
    private int specialAlertSeverity2;
    private String specialAlertSource;
}
