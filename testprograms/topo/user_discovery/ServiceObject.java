package com.adventnet.nms.topodb;

import java.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.netwatch.*;

/** This class is used to represent services like FTP, TELNET, Web-servers etc.
 * This is an extended from TopoObject and is used to test userDiscoveryProcess.conf.
*  We will put in three properties viz. port, serviceType, other properties 
* being inherited from TopoObject.*/
public class ServiceObject extends TopoObject
{
    public ServiceObject()
    {
		setClassname("ServiceObject"); 

	}

    private String serviceType = "default";    

    /* The method to set source for LinkObject */
    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
    }

    /* The method to get the source for LinkObject*/
    public String getServiceType()
    {
        return serviceType;
    }



    private int port = 0;

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    /* Get the properties of ServicesObject. */
    public Properties getProperties()
    {
        Properties p = super.getProperties();
        p.put("serviceType",serviceType);
	p.put("port",String.valueOf(port));
        return p;
    }


    public void setProperties(Properties prop)
    {
        Properties p = prop;

        if (p == null) { System.err.println("Error here: Properties object is Null"); return;}

        String s;
        if ((s = p.getProperty("serviceType")) != null) 
        {
            serviceType = s;
            p.remove("serviceType");
        }
        
        if ((s = p.getProperty("port")) != null) 
        {
            port = Integer.parseInt(s);
            p.remove("port");
        }
	
        super.setProperties(prop);

        return;
    }

    public Object clone()
    {
        ServiceObject cloneObj = (ServiceObject)super.clone();               
        return cloneObj;
    }

}




