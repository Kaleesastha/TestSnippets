package com.adventnet.nms.extend;
import com.adventnet.nms.alertdb.*;
import java.util.*;
/*
 * WebNMS supports user specific Alert which should extend com.adventnet.nms.alertdb.Alert object.  
 * This SpecialAlert is the example for user specific Alert. 
 *The Relational Object for user specific Alert can be generated using ObjectToRelational tool.
 * This Relational Object is basically used for storing this user specific Alert in db.
 * This tool needs get and set methods for all variables in the user specific Alert class.  Also we should have
 * getProperties and setProperties methods. Using ObjectToRelational tool, we can also generate DataBaseSchema for
 * the user specific Alert object.
 */ 
public class SpecialAlert extends Alert
{
    private String specialAlertEntity="testEntity";
    private int specialAlertSeverity=3;
    private String specialAlertSource="testSource";
    public String getEntity()
	{
		return super.getEntity();
	}

	public void setEntity(String ent)
	{
	    super.setEntity(ent);	
	}
	public void setOwnerName(String owner)
	{
		super.setOwnerName(owner);
	}
		public String getOwnerName()
	{
		return super.getOwnerName();
	}
	public void setSpecialAlertEntity(String ent)
    {
        this.specialAlertEntity=ent;
    }
    public String getSpecialAlertEntity()
    {
        return specialAlertEntity;
    }
    public void setSpecialAlertSeverity(int sev)
    {
        this.specialAlertSeverity=sev;
    }
    public int getSpecialAlertSeverity()
    {
        return specialAlertSeverity;
    }
    public void setSpecialAlertSource(String source)
    {
        this.specialAlertSource=source;
    }
    public String getSpecialAlertSource()
    {
        return specialAlertSource;
    }
    public void setProperties(Properties p)
    {
        String val = null;
        if((val = (String)p.remove("specialAlertEntity"))!= null)
        {
            this.specialAlertEntity =val;
        }
        if((val = (String)p.remove("specialAlertSource"))!= null)
        {
            this.specialAlertSource = val;
        }
        if((val = (String)p.remove("specialAlertSeverity"))!= null)
        {
            this.specialAlertSeverity = Integer.parseInt(val);
        }
        super.setProperties(p);
    }
    public Properties getProperties()
    {
        Properties p = super.getProperties();
        if(specialAlertEntity != null)
        {
            p.put("specialAlertEntity",specialAlertEntity);
        }
        if(specialAlertSource != null)
        {
            p.put("specialAlertSource",specialAlertSource);
        }
        p.put("specialAlertSeverity",String.valueOf(specialAlertSeverity));
        return p;
    }
}

