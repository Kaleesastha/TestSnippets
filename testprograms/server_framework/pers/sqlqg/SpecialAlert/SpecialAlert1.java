// Decompiled by Jad v1.5.7e. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SpecialAlert1.java

package com.adventnet.nms.extend;

import com.adventnet.nms.alertdb.Alert;
import com.adventnet.util.NVProperties;
import java.util.Hashtable;
import java.util.Properties;

public class SpecialAlert1 extends Alert
{

    public SpecialAlert1()
    {
        specialAlertEntity1 = "testEntity";
        specialAlertSeverity1 = 3;
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

    public void setSpecialAlertEntity1(String s)
    {
        specialAlertEntity1 = s;
    }

    public String getSpecialAlertEntity1()
    {
        return specialAlertEntity1;
    }

    public void setSpecialAlertSeverity1(int i)
    {
        specialAlertSeverity1 = i;
    }

    public int getSpecialAlertSeverity1()
    {
        return specialAlertSeverity1;
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
        if((s = (String)properties.remove("specialAlertEntity1")) != null)
            specialAlertEntity1 = s;
        if((s = (String)properties.remove("specialAlertSource")) != null)
            specialAlertSource = s;
        if((s = (String)properties.remove("specialAlertSeverity1")) != null)
            specialAlertSeverity1 = Integer.parseInt(s);
        super.setProperties(properties);
    }

    public Properties getProperties()
    {
        NVProperties nvproperties = (NVProperties)super.getProperties();
        if(specialAlertEntity1 != null)
            nvproperties.put("specialAlertEntity1", specialAlertEntity1);
        if(specialAlertSource != null)
            nvproperties.put("specialAlertSource", specialAlertSource);
        nvproperties.put("specialAlertSeverity1", String.valueOf(specialAlertSeverity1));
        return nvproperties;
    }

    private String specialAlertEntity1;
    private int specialAlertSeverity1;
    private String specialAlertSource;
}
