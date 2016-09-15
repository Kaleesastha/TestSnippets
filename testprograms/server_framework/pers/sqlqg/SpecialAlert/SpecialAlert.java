// Decompiled by Jad v1.5.7e. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SpecialAlert.java

package com.adventnet.nms.extend;

import com.adventnet.nms.alertdb.Alert;
import com.adventnet.util.NVProperties;
import java.util.Hashtable;
import java.util.Properties;

public class SpecialAlert extends Alert
{

    public SpecialAlert()
    {
        specialAlertEntity = "testEntity";
        specialAlertSeverity = 3;
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

    public void setSpecialAlertEntity(String s)
    {
        specialAlertEntity = s;
    }

    public String getSpecialAlertEntity()
    {
        return specialAlertEntity;
    }

    public void setSpecialAlertSeverity(int i)
    {
        specialAlertSeverity = i;
    }

    public int getSpecialAlertSeverity()
    {
        return specialAlertSeverity;
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
        if((s = (String)properties.remove("specialAlertEntity")) != null)
            specialAlertEntity = s;
        if((s = (String)properties.remove("specialAlertSource")) != null)
            specialAlertSource = s;
        if((s = (String)properties.remove("specialAlertSeverity")) != null)
            specialAlertSeverity = Integer.parseInt(s);
        super.setProperties(properties);
    }

    public Properties getProperties()
    {
        NVProperties nvproperties = (NVProperties)super.getProperties();
        if(specialAlertEntity != null)
            nvproperties.put("specialAlertEntity", specialAlertEntity);
        if(specialAlertSource != null)
            nvproperties.put("specialAlertSource", specialAlertSource);
        nvproperties.put("specialAlertSeverity", String.valueOf(specialAlertSeverity));
        return nvproperties;
    }

    private String specialAlertEntity;
    private int specialAlertSeverity;
    private String specialAlertSource;
}
