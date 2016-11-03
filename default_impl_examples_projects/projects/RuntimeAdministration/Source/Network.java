package com.adventnet.nms.runtimeconfig;
import java.util.*;
import java.lang.*;
import com.adventnet.nms.util.XMLNode;

class Network  implements Cloneable
{
    String IPAddress;
    String NetMask;
    String StartIP;
    String EndIP;
    String DHCP;
    String version;
    String communities;
    String snmpPorts;

    String uniqueUserNames;
    String contextName;
    String uniqueSnmpPorts;
    
    Vector v3Data;
                
    Network() 
    {
        // No argument constructor	
    }	
        
       
    /**
     * Get the value of StartIP.
     * @return value of StartIP.
     */
    public String getStartIP() 
    {
        return StartIP;
    }
        
    /**
     * Set the value of StartIP.
     * @param v  Value to assign to StartIP.
     */
    public void setStartIP(String startIP) 
    {
        this.StartIP = startIP;
    }
        
    /**
     * Get the value of EndIP.
     * @return value of EndIP.
     */
    public String getEndIP() 
    {
        return EndIP;
    }
        
    /**
     * Set the value of EndIP.
     * @param v  Value to assign to EndIP.
     */
    public void setEndIP(String endIP) 
    {
        this.EndIP = endIP;
    }
       
    /**
     * Get the value of DHCP.
     * @return value of DHCP.
     */
    public String getDHCP() 
    {
        return DHCP;
    }
        
    /**
     * Set the value of DHCP.
     * @param v  Value to assign to DHCP.
     */
    public void setDHCP(String dhcp) 
    {
        this.DHCP = dhcp;
    }
               
    /**
     * Get the value of IPAddress.
     * @return value of IPAddress.
     */
    public String getIPAddress() 
    {
        return IPAddress;
    }
        
    /**
     * Set the value of IPAddress.
     * @param v  Value to assign to IPAddress.
     */
    public void setIPAddress(String ipAddr) 
    {
        this.IPAddress = ipAddr;
    }
               
    /**
     * Get the value of NetMask.
     * @return value of NetMask.
     */
    public String getNetMask() 
    {
        return NetMask;
    }
        
    /**
     * Set the value of NetMask.
     * @param v  Value to assign to NetMask.
     */
    public void setNetMask(String netMask) 
    {
        this.NetMask = netMask;
    }

    public void setVersion(String ver)
    {
        
        this.version=ver;
       
    }

    public String getVersion()
    {
        return version;
    }

    public void setCommunities(String communities)
    {
        this.communities=communities;
    }

    public String getCommunities()
    {
        return communities;
    }

    public String getSnmpPorts()
    {
        return snmpPorts;
    }
    
    public void setSnmpPorts(String snmpPorts)
    {
        this.snmpPorts=snmpPorts;
    }

    public Vector getV3Data()
    {
        return v3Data;
    }
    
    public void setClientV3Data(Vector v3Data)
    {
        
        this.v3Data=v3Data;
        setUniqueUserNames();
        setUniqueSnmpPorts();
        setClientContextName();
    }
    
    public void setV3Data(Vector v3Data)
    {
        
        this.v3Data=v3Data;
    }

    private void setUniqueUserNames()
    {
        Vector userNames = new Vector();
        
        for(int i=0;i<v3Data.size();i++)
        {
            Properties prop = (Properties)v3Data.elementAt(i);
            String userName = prop.getProperty("USERNAME");
            
            if(!userNames.contains(userName))
            {
                userNames.addElement(userName);
            }
        }

        StringBuffer buffer = new StringBuffer();
      
        for(int i=0;i<userNames.size();i++)
        {
            String userName= (String)userNames.elementAt(i);
            if(i == (userNames.size()-1))
            {
                buffer.append(userName);
            }
            else
            {
                buffer.append(userName+" ");
            }
        }
        uniqueUserNames=buffer.toString();
        
    }

    private String getUniqueUserNames()
    {
        //setUniqueUserNames();
        return uniqueUserNames;
    }
    
    private void setUniqueSnmpPorts()
    {
        if(v3Data == null && v3Data.size() ==0)
        {
        	return;
        }
        
        Vector snmpPorts = new Vector();
        
        for(int i=0;i<v3Data.size();i++)
        {
            Properties prop = (Properties)v3Data.elementAt(i);
            String snmpPort= prop.getProperty("SNMPAGENTPORT");
            
            if(!snmpPorts.contains(snmpPort))
            {
                snmpPorts.addElement(snmpPort);
            }
        }

        StringBuffer buffer = new StringBuffer();
      
        for(int i=0;i<snmpPorts.size();i++)
        {
            String snmpPort= (String)snmpPorts.elementAt(i);
            if(i == (snmpPorts.size()-1))
            {
                buffer.append(snmpPort);
            }
            else
            {
                buffer.append(snmpPort+" ");
            }
        }
        
        uniqueSnmpPorts=buffer.toString();
         
        
    }
    
    private String getUniqueSnmpPorts()
    {
        //setUniqueSnmpPorts();
        return uniqueSnmpPorts;
    }
    
    private void setClientContextName()
    {
        if(v3Data != null && v3Data.size() !=0)
        {
        	Properties p = (Properties)v3Data.elementAt(0);
        	contextName = p.getProperty("SNMPV3_CONTEXTNAME");
        }
    }

    public void setContextName(String cName)
    {
    	this.contextName=cName;
    }
	
    public String getContextName()
    {
        //setContextName();
        return contextName;
    }
    
    public void populateObject(XMLNode net) 
    {
        setIPAddress((String)net.getAttribute("NETWORK_ID"));
        setNetMask((String)net.getAttribute("NETMASK"));
        setStartIP((String)net.getAttribute("START_IP"));
        setEndIP((String)net.getAttribute("END_IP"));
        setDHCP((String)net.getAttribute("DHCP"));
        
        String version = (String)net.getAttribute("SNMP_VERSION");
        if(version == null || version.equals(""))
        {
            return;
        }
        else if(version.equalsIgnoreCase("V1"))
        {
            setCommunities((String)net.getAttribute("READ_COMMUNITY"));
            setSnmpPorts((String)net.getAttribute("SNMPAGENTPORTS"));
            setVersion("V1");
        }
	// populate object when version is set as V2
	else if(version.equalsIgnoreCase("V2"))
	{
		setCommunities((String)net.getAttribute("READ_COMMUNITY"));
		setSnmpPorts((String)net.getAttribute("SNMPAGENTPORTS"));
		setVersion("V2");
	}			  
        else if(version.equalsIgnoreCase("V3"))
        {
            Vector v3Data = new Vector();
            Vector v3Nodes = net.getChildNodes();
            for(int i=0;i<v3Nodes.size();i++)
            {
                XMLNode v3Node = (XMLNode)v3Nodes.elementAt(i);
                Properties prop = getProps(v3Node);
                v3Data.addElement(prop);
            }
            setV3Data(v3Data);
	setContextName((String)net.getAttribute("SNMPV3_CONTEXTNAME"));
            setUniqueUserNames();
        	setUniqueSnmpPorts();
            setVersion("V3");
        }
    }

            
    public XMLNode getXMLNode() 
    {
        
        XMLNode xmlNode = new XMLNode();
        xmlNode.setNodeType(XMLNode.ELEMENT);
        xmlNode.setNodeName("net");
        if(getIPAddress() != null)
        {
            xmlNode.setAttribute("NETWORK_ID",getIPAddress().trim());
        }
        if(getNetMask()!= null)
        {
            xmlNode.setAttribute("NETMASK",getNetMask().trim());
        }
        if(getStartIP()!= null && !getStartIP().equals(""))
        {
            xmlNode.setAttribute("START_IP",getStartIP().trim());
        }
        if(getEndIP()!= null && !getEndIP().equals(""))
        {
            xmlNode.setAttribute("END_IP",getEndIP().trim());
        }
        if(getDHCP()!= null)
        {
            xmlNode.setAttribute("DHCP",getDHCP().trim());
        }
	
	
        if(version == null || version.equals(""))
        {
            return xmlNode;
        }
        else if(version.equalsIgnoreCase("V1") || version.equalsIgnoreCase("V2"))
        {
            xmlNode.setAttribute("SNMP_VERSION",getVersion());
            xmlNode.setAttribute("SNMPAGENTPORTS",getSnmpPorts());
            xmlNode.setAttribute("READ_COMMUNITY",getCommunities());
            return xmlNode;
        }
        else if(version.equalsIgnoreCase("V3"))
        {
            XMLNode array[] = getV3Node();
            
            for(int i=0;i<array.length;i++)
            {
                XMLNode v3Node = array[i];
                xmlNode.addChildNode(v3Node);
            }
            xmlNode.setAttribute("SNMP_VERSION",getVersion());
            xmlNode.setAttribute("SNMPV3_USERNAMES",getUniqueUserNames());
            xmlNode.setAttribute("SNMPV3_CONTEXTNAME",getContextName());
            xmlNode.setAttribute("SNMPAGENTPORTS",getUniqueSnmpPorts());
        }
        
        return xmlNode;
    }

    private XMLNode[] getV3Node()
    {
        Vector data = getV3Data();
        XMLNode array[] = new XMLNode[data.size()];

        for(int i=0;i<data.size();i++)
        {
            Properties prop = (Properties)data.elementAt(i);
            
            XMLNode node = new XMLNode();
            node.setNodeType(XMLNode.ELEMENT);
            node.setNodeName("SNMP_V3");
            node.setAttribute("SNMPAGENTPORT",prop.getProperty("SNMPAGENTPORT"));
            node.setAttribute("USERNAME",prop.getProperty("USERNAME"));
            node.setAttribute("AUTHPROTOCOL",prop.getProperty("AUTHPROTOCOL"));
            node.setAttribute("AUTHPASSWORD",prop.getProperty("AUTHPASSWORD"));
            node.setAttribute("PRIVPROTOCOL",prop.getProperty("PRIVPROTOCOL"));
            node.setAttribute("PRIVPASSWORD",prop.getProperty("PRIVPASSWORD"));
            node.setAttribute("SECURITYLEVEL",prop.getProperty("SECURITYLEVEL"));
            
            array[i]=node;
        }
        return array;
    }
    
    private Properties getProps(XMLNode node) 
    {
        if (node == null) 
        {
            return null;
        }
        Hashtable h = node.getAttributeList();
        Properties prop = new Properties();
        if (h == null) 
        {
            return prop;
        }
        for (Enumeration en = h.keys(); en.hasMoreElements();) 
        {
            String keyValue = (String)en.nextElement();
            prop.put(keyValue, ((String)node.getAttribute(keyValue)));
        }
        return prop;
    }
    
    public void flushSnmpData()
    {
    	setVersion("");
    	setCommunities("");
    	setSnmpPorts("");
	setContextName("");
    	setV3Data(new Vector());
    }
   public Object clone()
   {
        try
         {
             return super.clone();
        }catch(Exception e)
       {
             e.printStackTrace();
        }
        return null; 
   }    
    
 }















