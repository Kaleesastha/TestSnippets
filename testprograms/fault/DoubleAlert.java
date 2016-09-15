package test;
import com.adventnet.nms.alertdb.*;
import java.util.*;

public class DoubleAlert extends Alert
{
    private String specialAlertEntity="testEntity";
    private int specialAlertSeverity=3;
    private String specialAlertSource="testSource";
    private double testing = 123.456;
	
	public void setTesting(double test)
	{
		this.testing = test;
	}

	public double getTesting()
	{
		return testing;
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
        if((val = (String)p.remove("testing"))!= null)
        {
            this.testing = Double.parseDouble(val);
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
		p.put("testing",String.valueOf(testing));
        return p;
    }
}

