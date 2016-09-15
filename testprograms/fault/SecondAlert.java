/* $Id: SecondAlert.java,v 1.1 2004/07/21 06:04:17 srividya Exp $
 *
 * Extended Alert Object.
 */

package test;

import java.util.*;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;

public class SecondAlert extends SpecialAlert
{
    private String secondAlertEntity="testEntity";
    private int secondAlertSeverity=3;
    private String secondAlertSource="testSource";
    
    public SecondAlert()
    {
    }
    
    public SecondAlert(String source)
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

    public void setSecondAlertEntity(String ent)
    {
        this.secondAlertEntity=ent;
    }

    public String getSecondAlertEntity()
    {
        return secondAlertEntity;
    }

    public void setSecondAlertSeverity(int sev)
    {
        this.secondAlertSeverity=sev;
    }

    public int getSecondAlertSeverity()
    {
        return secondAlertSeverity;
    }

    public void setSecondAlertSource(String source)
    {
        this.secondAlertSource=source;
    }

    public String getSecondAlertSource()
    {
        return secondAlertSource;
    }

    public void setProperties(Properties p)
    {
        String val = null;
        if((val = (String)p.remove("secondAlertEntity"))!= null)
        {
            this.secondAlertEntity =val;
        }
        if((val = (String)p.remove("secondAlertSource"))!= null)
        {
            this.secondAlertSource = val;
        }
        if((val = (String)p.remove("secondAlertSeverity"))!= null)
        {
            this.secondAlertSeverity = Integer.parseInt(val);
        }
        super.setProperties(p);
    }

    public Properties getProperties()
    {
        Properties p = super.getProperties();
        if(secondAlertEntity != null)
        {
            p.put("secondAlertEntity",secondAlertEntity);
        }
        if(secondAlertSource != null)
        {
            p.put("secondAlertSource",secondAlertSource);
        }
        p.put("secondAlertSeverity",String.valueOf(secondAlertSeverity));
        return p;
    }
}
