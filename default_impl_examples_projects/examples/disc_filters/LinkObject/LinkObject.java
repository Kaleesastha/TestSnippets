/*
$Id: LinkObject.java,v 1.2 2007/10/19 15:36:09 barathv Exp $
*/
/**
 * LinkObject.java
 */
package com.adventnet.nms.topodb;

import com.adventnet.nms.store.relational.RelationalUtil;
import java.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.netwatch.*;

/** This class demonstrates how a user can create his own CustomObjects.As a test case
* we will create a LinkObject (based on the interfaces of Router when a router is discovered)
* Please refer LinkFilter.java for more details. The LinkObject extends ManagedObject.
* We will put in three properties viz. source,destination and ipADDR, other properties 
* being inherited from ManagedObject.*/
public class LinkObject extends ManagedObject 
{
    public LinkObject()
    {
        setType("LinkObject");//just to get some icon
        setClassname("LinkObject"); 
    }

    /* The source property to indicate the source for link object.
       For our example it will be the router object.*/ 
    private String source = "source";    

    /* The method to set source for LinkObject */
    public void setSource(String source)
    {
        this.source = source;
    }

    /* The method to get the source for LinkObject*/
    public String getSource()
    {
        return source;
    }

    /* The destination property to indicate the destination for link object.
       For our example it will be the  interface of router.*/ 
    private String destination = "destination";

    /* The method to set source for LinkObject */
    public void setDestination(String dest)
    {
        destination = dest;
    }

    /* The method to get the source for LinkObject*/
    public String getDestination()
    {
        return destination;
    }

    /* The ipADDR property to indicate the IpAddress for link object.
       For our example it will be the  IpAddress of interface of router.*/ 
    private String ipADDR = "0.0.0.0";

    /* The method to set ipADDR for LinkObject */
    public String getIpADDR()
    {
        return ipADDR;
    }

    /* The method to get the ipAddress for LinkObject*/
    public void setIpADDR(String ipADDR)
    {
        this.ipADDR = ipADDR;
    }

    /* Get the properties of LinkObject. */
    public Properties getProperties()
    {
        Properties p = super.getProperties();
        p.put("source",source);
        p.put("destination",destination);
        p.put("ipADDR",ipADDR);
        return p;
    }

    /* Set the properties of LinkObject */
    public void setProperties(Properties prop)
    {
        Properties p = prop;

        if (p == null) { System.err.println("Error: Properties object is Null"); return;}

        String s;
        if ((s = p.getProperty("source")) != null) 
        {
            source = s;
            p.remove("source");
        }
        if ((s = p.getProperty("destination")) != null) 
        {
            destination = s;
            p.remove("destination");
        }
        if ((s = p.getProperty("ipADDR")) != null) 
        {
            ipADDR = s;
            p.remove("ipADDR");
        }

        super.setProperties(prop);

        return;
    }

    public Object clone()
    {
        LinkObject cloneObj = (LinkObject)super.clone();               
        return cloneObj;
    }

    /** Method to check the status of LinkStatus*/
    protected int checkStatus() throws java.rmi.RemoteException 
    {
		SeverityInfo sevInfo = SeverityInfo.getInstance();
        int if_status_min=sevInfo.getClear();       
        //int if_status_min=5;       
        
        int stat = getStatus();
        int currentStatus = checkObjStatus();
        if (stat != currentStatus) 
        { // status changed
            //if(currentStatus != 5) 
            if(!sevInfo.isClear(currentStatus)) 
			{
                setFailureCount(getFailureCount() + 1);
                if(getFailureCount() < getFailureThreshold())
                    return getStatus();
            }
            setFailureCount(0);
            setStatus(currentStatus);
        }
        return getStatus();
    }

    int checkObjStatus() 
    {
		SeverityInfo sevInfo = SeverityInfo.getInstance();
		SeverityIterator iterator = sevInfo.getIterator();
		int spSeverity = sevInfo.getSpecialPurposeSeverity();

        if (getTester() == null) 
			return spSeverity;
			//return -1;
        if (getTester().equals("ping")) 
        {
            if (Ping.ping(getIpADDR())) 
				return sevInfo.getClear(); // ping ok -
				//return 5; // ping ok -
            else  
			{
				iterator.moveToHighest();
				return iterator.getPreviousCriticality();
				//return 2;
			}	
        }
        /*if (getTester().equals("snmpping"))
        {
            if (getSnmpport()!=161)
            {
                if (DBServer.topodb.sping.snmpping(getIpAddress(), getCommunity(),getSnmpport())) return 5; 
                else  return 2;
            }
            if (DBServer.topodb.sping.snmpping(getIpAddress(), getCommunity())) return 5; // ping ok
            else  return 2;
        }*/
        
        if (getTester().equals("usertest")) 
        {
            if ((uTest = (UserTester)DBServer.userTesters.get(getUClass())) == null) 
            {
                if (getUClass() == null) 
					return spSeverity;
					//return -1;
                try
                {
                    Class cls = Class.forName(getUClass());
                    if (cls == null) {
                        System.err.println("No User Test class found: "+getUClass());
						return spSeverity;
                        //return 0;
                    }
                    uTest = (UserTester) cls.newInstance();
                    DBServer.userTesters.put(getUClass(),uTest);
                }
                catch (Exception e) 
				{
					System.err.println(e.toString()); 
					return spSeverity;
					//return 0;
				}
            }
            Properties moProps = this.getProperties();
			     Hashtable hash = DBServer.pollProps;
			     String prop = (String)hash.get(this.getClassname());	     
			     Properties pn = new Properties();
                             if (prop != null)
                             {
                                 StringTokenizer st = new StringTokenizer(prop,","); //no i18n
                                 while (st.hasMoreElements())
                                 {
				     String userprop = RelationalUtil.getAntiAlias(st.nextToken());
				     String val = moProps.getProperty(userprop);
                                     if (val != null)
                                     {
                                        pn.setProperty(userprop,val);
                                     }
                                 }
                             }
            return uTest.test(this.getName(),pn, DBServer.topodb);
        }
		return spSeverity;
        //return -1;
    } // end checkObjStatus()    
}
