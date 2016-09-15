package test;
import com.adventnet.nms.eventdb.*;
import com.adventnet.snmp.snmp2.*;
import java.util.*;

public class DoubleEvent extends Event
{

	private String specialEventName="defaultName";
    private int specialEventSeverity =2;
    private String specialEventIdentifier = "defaultIdentifier";
    float testing = 123.456;
	public int getId()
	{
		return super.getId();
	}	
	
	public float getTesting()
	{
		return testing;
	}

	public void setTesting(float test)
	{
		this.testing = test;
	}	

	public void setId(int id)
	{
		super.setId(id);
	}
	public String getOwnerName()
	{
		return super.getOwnerName();
	}
	public void setOwnerName(String owner)
	{
		super.setOwnerName(owner);
	}
	public void setSpecialEventName(String name)
    {
        this.specialEventName = name;
    }
    public void setSpecialEventSeverity(int sev)
    {
        this.specialEventSeverity = sev;
    }
    public void setSpecialEventIdentifier(String word)
    {
        this.specialEventIdentifier = word;
    }
    public String getSpecialEventName()
    {
        return specialEventName;
    }
    public int getSpecialEventSeverity()
    {
        return specialEventSeverity;
    }
    public String getSpecialEventIdentifier()
    {
        return specialEventIdentifier;
    }
    public void setProperties(Properties p)
    {
        String val = null;
        if((val = (String)p.remove("specialEventName"))!= null)
        {
            this.specialEventName =val;
        }
        if((val = (String)p.remove("specialEventIdentifier"))!= null)
        {
            this.specialEventIdentifier = val;
        }
        if((val = (String)p.remove("specialEventSeverity"))!= null)
        {
            this.specialEventSeverity = Integer.parseInt(val);
        }
        if((val = (String)p.remove("testing"))!= null)
        {
            this.testing = Float.parseFloat(val);
        } 
		super.setProperties(p);
    }
    public Properties getProperties()
    {
        Properties p = super.getProperties();
        if(specialEventName != null)
        {
            p.put("specialEventName",specialEventName);
        }
        if(specialEventIdentifier != null)
        {
            p.put("specialEventIdentifier",specialEventIdentifier);
        }
        p.put("specialEventSeverity",String.valueOf(specialEventSeverity));
		p.put("testing",String.valueOf(testing));
        return p;
    }
            
}

