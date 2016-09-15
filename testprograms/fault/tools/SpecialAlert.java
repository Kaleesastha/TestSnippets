/* $Id: SpecialAlert.java,v 1.1 2003/01/13 12:03:54 rajalakshmytr Exp $
 *
 * Extended Alert Object.
 */

package test;

import java.util.*;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;

public class SpecialAlert extends Alert
{
    private String specialAlertEntity="testEntity";
    private int specialAlertSeverity=3;
    private String specialAlertSource="testSource";
    
    public SpecialAlert()
    {
    }
    
    public SpecialAlert(String source)
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
