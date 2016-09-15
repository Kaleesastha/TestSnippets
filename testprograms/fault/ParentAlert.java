package test;

import com.adventnet.nms.alertdb.*;
import java.util.*;


public class ParentAlert extends Alert
{
    private String parentAlertEntity="parentEntity";
    private int parentAlertSeverity=5;
    private String parentAlertSource="parentSource";
    
    public ParentAlert()
    {
    }
    
    public ParentAlert(String source)
    {
        super.setSource(source);
    }

	
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
	public void setParentAlertEntity(String ent)
    {
        this.parentAlertEntity=ent;
    }
    public String getParentAlertEntity()
    {
        return parentAlertEntity;
    }
    public void setParentAlertSeverity(int sev)
    {
        this.parentAlertSeverity=sev;
    }
    public int getParentAlertSeverity()
    {
        return parentAlertSeverity;
    }
    public void setParentAlertSource(String source)
    {
        this.parentAlertSource=source;
    }
    public String getParentAlertSource()
    {
        return parentAlertSource;
    }
    public void setProperties(Properties p)
    {
        String val = null;
        if((val = (String)p.remove("parentAlertEntity"))!= null)
        {
            this.parentAlertEntity =val;
        }
        if((val = (String)p.remove("parentAlertSource"))!= null)
        {
            this.parentAlertSource = val;
        }
        if((val = (String)p.remove("parentAlertSeverity"))!= null)
        {
            this.parentAlertSeverity = Integer.parseInt(val);
        }
		super.setProperties(p);
    }
    public Properties getProperties()
    {
        Properties p = super.getProperties();
        if(parentAlertEntity != null)
        {
            p.put("parentAlertEntity",parentAlertEntity);
        }
        if(parentAlertSource != null)
        {
            p.put("parentAlertSource",parentAlertSource);
        }
        p.put("parentAlertSeverity",String.valueOf(parentAlertSeverity));
        return p;
    }
}

