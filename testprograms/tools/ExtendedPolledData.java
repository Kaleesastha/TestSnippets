//package com.altamar.nms.pm.be;
package com.adventnet.nms.poll;

/**
 * Title: ExtendedPolledData
 * Description: It is a extension of the PolledData class
 * Copyright:    Copyright (c) 2001
 * Company:Altamar Networks
 * @author : Venkatraghavan
 * @version 1.0
 */

 import java.util.*;
 import com.adventnet.nms.topodb.*;
 import com.adventnet.nms.util.NmsException;
 import com.adventnet.nms.util.NmsLogMgr;
 import com.adventnet.nms.util.NmsUtil;
 import com.adventnet.nms.util.WatchUtil;
 import com.adventnet.nms.util.XMLNode;
 import com.adventnet.nms.util.*;
 import com.adventnet.nms.poll.*;
 import com.adventnet.nms.poll.*;
 import javax.swing.*;


public class ExtendedPolledData extends PolledData
{

// collector Name
String collectorName="";
// Port that this PolledData supports
int polPort=0;
// Interface Index supported by the PolledData
String interfaceIndex ="";
// Attribute for which polling is done
String attribute = "";

// Sonet Bucket Number

int sonetBucketNo =0;

// Time cache

long timeCache;  



  public ExtendedPolledData()
  {
    super();
    System.out.println("ExtendedPolledData::ExtendedPolledData");
  }

  public String getCollectorName()
  {
      return collectorName;
  }

 public void setCollectorName(String cname)
  {
      collectorName = cname;
  }

  public int getPolPort()
  {
      return polPort;
  }

 public void setPolPort(int port)
  {
    this.polPort = port;
  }

  public int getSonetBucketNo()
  {
      return sonetBucketNo;
  }

 public void setSonetBucketNo(int sonetBucketNo)
  {
    this.sonetBucketNo = sonetBucketNo;
  }


  public long getTimeCache()
  {
      return timeCache;
  }

  public void setTimeCache(long timeCache)
  {
    this.timeCache = timeCache;
  }



  public String getInterfaceIndex()
  {
      return interfaceIndex;
  }

  public void setInterfaceIndex(String index)
  {
      this.interfaceIndex=index;
  }

  public String getName()
  {
    return super.getName();
  }

 public void setName(String name)
  {
      super.setName(name);
  }

  public String getAgent()
  {
    return super.getAgent();
  }

  public void setAgent(String agent)
  {
    super.setAgent(agent);
  }

  public String getOwnerName()
  {
    return super.getOwnerName();
  }

  public void setOwnerName(String name)
  {
    super.setOwnerName(name);
  }

  public String getOid()
  {
    return super.getOid();
  }

  public void setOid(String oid)
  {
      super.setOid(oid);
  }

  public String getAttribute()
  {
    return attribute;
  }

  public void setAttribute(String name)
  {
      attribute = name;
  }

  public void setProperties(Properties p)
  {
    super.setProperties(p);
    String val = null;
    

    if((val = (String)p.remove("collectorName"))!= null)
    {
      this.collectorName =val;
    }
    if((val = (String)p.remove("interfaceIndex"))!= null)
    {
      this.interfaceIndex = val;
    }
    if((val = (String)p.remove("polPort"))!= null)
    {
        this.polPort = Integer.parseInt(val);
    }

    if((val = (String)p.remove("sonetBucketNo"))!= null)
    {
        this.sonetBucketNo = Integer.parseInt(val);
    }

    if((val = (String)p.remove("timeCache"))!= null)
    {
        this.timeCache = Long.parseLong(val);
    }

    if(p.get("id") != null)
    {
        long id = Long.parseLong(p.getProperty("id"));
        super.setId(id);
    }

    if((val = (String)p.remove("attribute"))!= null)
    {
        this.attribute = val;
    }

  }



  public Properties getProperties()
  {
    Properties p = super.getProperties();
    if(collectorName != null)
    {
       p.put("collectorName",collectorName);
    }
    if(interfaceIndex != null)
    {
      p.put("interfaceIndex",interfaceIndex);
    }
    if(attribute != null)
    {
      p.put("attribute",attribute);
    }
    p.put("polPort",String.valueOf(polPort));

    p.put("timeCache",String.valueOf(timeCache));

    p.put("sonetBucketNo",String.valueOf(sonetBucketNo));
 
    return p;
  }


}



