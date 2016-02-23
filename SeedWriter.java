package com.adventnet.nms.topodb;

import java.util.*;
import com.adventnet.nms.netwatch.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.*;   

public class SeedWriter implements RunProcessInterface
{
    //our proprietary class ... not exposed to any customers
    DiscoveryAPI disc=null;

    //run process implementation. For testing purpose we have implmeneted this intreface
    public boolean isInitialized()
	{
            return true;
	}

    //run process implementation. For testing purpose we have implmeneted this intreface
    public void shutDown()
    {
        System.err.println("Shutdown called .... ");
    }

    //this method takes the type & ip as input . Based on the type... "TO_DISCOVER" & "NOT_TO_DISCOVER" xmlnode will be constructed
    // based on the ipaddress the net node will be constructed

    private XMLNode getNetToSeed(String type, String ip) throws Exception
    {
        XMLNode toDiscNet = new XMLNode();
        toDiscNet.setNodeType(XMLNode.ELEMENT);
        if(type.equalsIgnoreCase("TODISC"))
            {
                toDiscNet.setNodeName("TO_DISCOVER");
            }
        else
            {
                toDiscNet.setNodeName("NOT_TO_DISCOVER");
            }
        toDiscNet.addChildNode(getNetNode(ip));

        return toDiscNet;


    }

    //this method takes the type & ip as input . Based on the type... "TO_DISCOVERIP" & "NOT_TO_DISCOVERIP" xmlnode will be constructed
    // based on the ipaddress the ip node will be constructed

    private XMLNode getIpToSeed(String type, String ip) throws Exception
    {
        XMLNode toDiscIp = new XMLNode();
        toDiscIp.setNodeType(XMLNode.ELEMENT);
        if(type.equalsIgnoreCase("TODISC"))
            {
                toDiscIp.setNodeName("TO_DISCOVERIP");
            }
        else
            {
                toDiscIp.setNodeName("NOT_TO_DISCOVERIP");
            }
        toDiscIp.addChildNode(getIpNode(ip));

        return toDiscIp;


    }

    // based on the ipaddress the ip xmlnode will be constructed
    public XMLNode getIpNode(String ipAddress) 
    {
        
        XMLNode xmlNode = new XMLNode();
        xmlNode.setNodeType(XMLNode.ELEMENT);
        xmlNode.setNodeName("ip");
        xmlNode.setAttribute("NODE_ID",ipAddress);
        xmlNode.setAttribute("NETMASK","255.255.255.0");
        return xmlNode;
    }



    // example 4 creating Discovery xml node
    private XMLNode getDisc() throws Exception
    {
        XMLNode discNode = new XMLNode();
        discNode.setNodeType(XMLNode.ELEMENT);
        discNode.setNodeName("DISCOVERY");
        discNode.setAttribute("DISCOVER","true");
        discNode.setAttribute("DISCOVER_LOCALNET","false");
        discNode.setAttribute("REDISCOVER_INTERVAL","0:15:0");
        //for setting any other props you have to apply yoour logic here
        return discNode;
    }

    // example 4 creating native ping xml node
    private XMLNode getNative() throws Exception
    {
        XMLNode discNode = new XMLNode();
        discNode.setNodeType(XMLNode.ELEMENT);
        discNode.setNodeName("NATIVE_PING");
        discNode.setAttribute("ICMP_RETRIES","1");
        discNode.setAttribute("ICMP_TIMEOUT","5");
        //for setting any other props you have to apply yoour logic here
        return discNode;
    }

    // based on the ipaddress the net xmlnode will be constructed
    public XMLNode getNetNode(String ipAddress) 
    {
        
        XMLNode xmlNode = new XMLNode();
        xmlNode.setNodeType(XMLNode.ELEMENT);
        xmlNode.setNodeName("net");
        xmlNode.setAttribute("NETWORK_ID",ipAddress);
        xmlNode.setAttribute("NETMASK","255.255.255.0");
        // xmlNode.setAttribute("START_IP","192.168.4.100");
        // xmlNode.setAttribute("END_IP","192.168.4.121");
        // xmlNode.setAttribute("DHCP","false");
	
        //For version v1 & v2
        /*
          xmlNode.setAttribute("SNMP_VERSION","v1");
          xmlNode.setAttribute("SNMPAGENTPORTS","161");
          xmlNode.setAttribute("READ_COMMUNITY","public");
        */
        return xmlNode;
    }

    public void callMain(String args[])
    {

        try
            {
                //waiting for the initialization of DiscoveryAPI
                // needed only if you start as a process
                waitForTopoDB();
            }
        catch(Exception e)
            {}
        try
            {   
                //the api is package level access. hence the class should be in the package of "com.adventnet.nms.topodb"

                disc=DBServer.api;

                //the below is the method to retrieve the root node of the existing seed file... 
                XMLNode seedNode=disc.getSeedFile(); 

                Vector childNod  = seedNode.getChildNodes();

               for(Enumeration e = childNod.elements();e.hasMoreElements();)
		{

                    XMLNode temp = (XMLNode)e.nextElement();
                    if(temp==null) 
                        {
                            System.err.println("THe temp is null ");
                            continue;
                        }
                    String nodeName=temp.getNodeName();
                    if(nodeName==null) continue;
                    if(nodeName.equals("NOT_TO_DISCOVER"))
                        {
                            //removes the corresponding child nodes
                            seedNode.deleteChildNode(temp);
                        
                            //For modify operation 
                            //you can use the above temp node to retrieve some data from previous node. 
                            //do the necssary businness logic here
                            seedNode.addChildNode(getNetToSeed("NOTTODISC","192.168.11.0"));
                        }
                    else if(nodeName.equals("TO_DISCOVER"))
                        {
                            //removes the corresponding child nodes
                            seedNode.deleteChildNode(temp);
                            //you can use the above temp node to retrieve some data from previous node
                            //do the necssary businness logic here
                            seedNode.addChildNode(getNetToSeed("TODISC","192.168.4.0"));
                            System.err.println("Inside to disc: "+temp);
                        }
                    else if(nodeName.equals("DISCOVERY"))
                        {
                            //removes  the specified child node
                             seedNode.deleteChildNode(temp);
                             seedNode.addChildNode(getDisc());
                        }
                    else if(nodeName.equals("NOT_TO_DISCOVERIP"))
                        {

                            seedNode.deleteChildNode(temp);
                             seedNode.addChildNode(getIpToSeed("NOTTODISC","192.168.4.121"));
                        }
                    else if(nodeName.equals("TO_DISCOVERIP"))
                        {
                            seedNode.deleteChildNode(temp);
                            seedNode.addChildNode(getIpToSeed("TODISC","192.168.4.210"));
                        }
                }
               
               disc.handleSeedFile(seedNode,"set");
            
            }
        catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    
    private void waitForTopoDB()
    {
     
        while(DBServer.api==null)
            {
                try
                    {
                        Thread.sleep(500);
                    }
                catch(Exception e)
                    {
                    }
            }
       
    }

}


