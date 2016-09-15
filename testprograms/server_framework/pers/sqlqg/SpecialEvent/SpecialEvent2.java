package com.adventnet.nms.extend;
import com.adventnet.util.NVProperties;
import com.adventnet.nms.eventdb.*;
//import com.adventnet.snmp.snmp2.*;
import java.util.*;
/*
 * WebNMS supports user specific Event object which should extend com.adventnet.nms.eventdb.Event object.  
 * This SpecialEvent is the example for user specific Event. 
 * The Relational Object for user specific Event can be generated using ObjectToRelational tool.
 * This Relational Object is basically used for storing this user specific Event in db.
 * This tool needs get and set methods for all variables in the user specific Event class.  Also we should have
 * getProperties and setProperties methods. Using ObjectToRelational tool, we can also generate DataBaseSchema for
 * the user specific Event object.
 */ 
public class SpecialEvent2 extends Event
{

	private String specialEventName="defaultName";
    private int specialEventIdentity = 2;
    private String specialEventIdentifier = "defaultIdentifier";
    public int getId()
	{
		return super.getId();
		//return id;
	}	

	public void setId(int id)
	{
		super.setId(id);
		//this.id=id;
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
    public void setSpecialEventIdentity(int sev)
    {
        this.specialEventIdentity = sev;
    }
    public void setSpecialEventIdentifier(String word)
    {
        this.specialEventIdentifier = word;
    }
    public String getSpecialEventName()
    {
        return specialEventName;
    }
    public int getSpecialEventIdentity()
    {
        return specialEventIdentity;
    }
    public String getSpecialEventIdentifier()
    {
        return specialEventIdentifier;
    }
    public void setProperties(Properties p)
    {
        String val = null;
        if ((val = p.getProperty("name")) != null)
        {
            if (!val.equals(getKey())) 
            {
                //NmsLogMgr.EVENTUSER.log("Error: Object Name cannot be changed",Log.INTERMEDIATE_DETAIL); 
            }
            p.remove("name");
        }
        if((val = (String)p.remove("specialEventName"))!= null)
        {
            this.specialEventName =val;
        }
        if((val = (String)p.remove("specialEventIdentifier"))!= null)
        {
            this.specialEventIdentifier = val;
        }
        if((val = (String)p.remove("specialEventIdentity"))!= null)
        {
            this.specialEventIdentity = Integer.parseInt(val);
        }
        super.setProperties(p);
    }
    public Properties getProperties()
    {
        NVProperties p = (NVProperties)super.getProperties();
        if(specialEventName != null)
        {
            p.put("specialEventName",specialEventName);
        }
        if(specialEventIdentifier != null)
        {
            p.put("specialEventIdentifier",specialEventIdentifier);
        }
        p.put("specialEventIdentity",String.valueOf(specialEventIdentity));
        return p;
    }
            
}

