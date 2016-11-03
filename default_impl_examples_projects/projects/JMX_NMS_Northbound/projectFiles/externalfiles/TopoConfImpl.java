//$Id: TopoConfImpl.java,v 1.2 2007/04/30 17:21:28 tinku Exp $
package com.adventnet.nms.jmxagent;

import com.adventnet.nms.util.*;

import java.io.*;
import java.util.*;

public class TopoConfImpl
{
    Integer autoDisc,discLocalNet,reDisc,enableLog,enableSnmpPing,enableSnmpV3ping,enableSweep,enableIcmp;

    Integer discInterval,reDiscInterval;
    Integer snmpPingRetries,snmpPingTimeout,pingRetries;
    Integer icmpPingRetries,icmpTimeout,icmpDebugLevel,icmpSweepPkts,icmpSweepInterval;

    String hour,day_of_week,day_of_month,readCommunity,writeCommunity;
    String snmpV3UserName = "";//No I18N
    String snmpV3ContextName = "";//No I18N
    String snmpPort = "";//No I18N

    Hashtable networkDiscoveryTable = new Hashtable();
    Hashtable nodeDiscoveryTable = new Hashtable();
    Hashtable moCriteriaTable = new Hashtable();

    Hashtable discFilterTable = new Hashtable();
    //Hashtable listIconTable = new Hashtable();

    XMLNode discoveryNode = null;
    XMLNode nativePingNode = null;

    private AdventNet_WebNMS_MIB_JMX agentName = null;

    TopoConfImpl(AdventNet_WebNMS_MIB_JMX agentRef)
    {
        agentName = agentRef;
	//loadSeedDatas();
	//loadDiscFilters();
	//loadListIcons();
    }

    boolean loaded = false;

    /*
     * Initializing all the seed file datas and store them into the Hashtables
     */
    void loadSeedDatas()
    {
	if(loaded) return;

	int netDiscIndex = 1;

	com.adventnet.nms.netwatch.DiscoveryAPI discoveryAPI = null;

	/*
	 * Getting the DiscoveryAPI instance
	 */
	for(int con = 0; con < 2; con++)
	{
	    if(discoveryAPI == null)
		discoveryAPI = com.adventnet.nms.netwatch.DiscoveryAPI.getInstance();

	    if(discoveryAPI != null)
		break;
	    else
	    {
		try
		{
		    Thread.sleep(1000);
		}
		catch(Exception e){}
	    }
	}

	if(discoveryAPI == null)
	    return;

	/*
	 * Getting the RootNode of the seed file using the DiscvoeryAPI call
	 */
	XMLNode rootNode = discoveryAPI.getSeedNode();

	Vector childVector = rootNode.getChildNodes();

	for(int i = 0; i < childVector.size(); i++)
	{
	    XMLNode childNode = (XMLNode)childVector.elementAt(i);

	    if(childNode.getNodeType() == XMLNode.ELEMENT)
	    {
		if(childNode.getNodeName().equals("DISCOVERY"))//No I18N
		{
		    /*
		     * Initializing all Discovery details
		     */
		    discoveryNode = childNode;

		    Hashtable childAttr = childNode.getAttributeList();
		    if( ((String)childAttr.get("DISCOVER")).trim().equalsIgnoreCase("true"))//No I18N
			autoDisc = new Integer(1);
		    else
			autoDisc = new Integer(2);

		    if( ((String)childAttr.get("REDISCOVER_ALREADY_DISCOVERED")).trim().equalsIgnoreCase("true"))//No I18N
			reDisc = new Integer(1);
		    else
			reDisc = new Integer(2);

		    if( ((String)childAttr.get("DISCOVER_LOCALNET")).trim().equalsIgnoreCase("true"))//No I18N
			discLocalNet = new Integer(1);
		    else
			discLocalNet = new Integer(2);

		    if( ((String)childAttr.get("ENABLE_LOG")).trim().equalsIgnoreCase("true"))//No I18N
			enableLog = new Integer(1);
		    else
			enableLog = new Integer(2);

		    discInterval = new Integer((String)childAttr.get("DISCOVERY_INTERVAL"));//No I18N
		    reDiscInterval = new Integer((String)childAttr.get("REDISCOVER_INTERVAL"));//No I18N
		    hour = (String)childAttr.get("HOUR");//No I18N
		    day_of_week = (String)childAttr.get("DAY_OF_THE_WEEK");//No I18N
		    day_of_month = (String)childAttr.get("DAY_OF_THE_MONTH");//No I18N

		    if( ((String)childAttr.get("ENABLE_SNMP_DISCOVERY")).trim().equalsIgnoreCase("true"))//No I18N
			enableSnmpPing = new Integer(1);
		    else
			enableSnmpPing = new Integer(2);

		    snmpPingRetries = new Integer((String)childAttr.get("SNMP_RETRIES"));//No I18N
		    snmpPingTimeout = new Integer((String)childAttr.get("SNMP_TIMEOUT"));//No I18N

		    String temp = (String)childAttr.get("SNMP_PORTS");//No I18N
		    snmpPort = (temp != null) ? temp : snmpPort;

		    readCommunity = (String)childAttr.get("READ_COMMUNITY");//No I18N
		    writeCommunity = (String)childAttr.get("WRITE_COMMUNITY");//No I18N

		    if( ((String)childAttr.get("ENABLE_SNMPV3_DISCOVERY")).trim().equalsIgnoreCase("true"))//No I18N
			enableSnmpV3ping = new Integer(1);
		    else
			enableSnmpV3ping = new Integer(2);

		    temp = (String)childAttr.get("SNMPV3_USERNAMES");//No I18N
		    snmpV3UserName = (temp != null) ? temp : snmpV3UserName;
		    temp = (String)childAttr.get("SNMPV3_CONTEXTNAME");//No I18N
		    snmpV3ContextName = (temp != null) ? temp : snmpV3ContextName;

		    if( ((String)childAttr.get("ENABLE_ICMP_DISCOVERY")).trim().equalsIgnoreCase("true"))//No I18N
			enableIcmp = new Integer(1);
		    else
			enableIcmp = new Integer(2);

		    pingRetries = new Integer((String)childAttr.get("PING_RETRIES"));//No I18N
		}
		else if(childNode.getNodeName().equals("NATIVE_PING"))//No I18N
		{
		    /*
		     * Initializing all the Native ping details
		     */
		    nativePingNode = childNode;

		    Hashtable childAttr = childNode.getAttributeList();
		    icmpPingRetries = new Integer((String)childAttr.get("ICMP_RETRIES"));//No I18N
		    icmpTimeout = new Integer((String)childAttr.get("ICMP_TIMEOUT"));//No I18N
		    icmpDebugLevel = new Integer((String)childAttr.get("ICMP_DEBUG_LEVEL"));//No I18N
		    icmpSweepPkts = new Integer((String)childAttr.get("ICMP_SWEEP_PACKETS"));//No I18N
		    icmpSweepInterval = new Integer((String)childAttr.get("ICMP_SWEEP_SLEEP_INTERVAL"));//No I18N

		    if( ((String)childAttr.get("PING_SWEEP")).trim().equalsIgnoreCase("true"))//No I18N
			enableSweep = new Integer(1);
		    else
			enableSweep = new Integer(2);
		}
		else if( (childNode.getNodeName().equals("TO_DISCOVER")) || (childNode.getNodeName().equals("NOT_TO_DISCOVER")))//No I18N
		{
		    /*
		     * Initializing all the network discovery details
		     */
		    Vector vector = childNode.getChildNodes();
		    for(int j = 0; j < vector.size(); j++)
		    {
			XMLNode xmlNode = (XMLNode)vector.elementAt(j);

			if(xmlNode.getNodeType() == XMLNode.ELEMENT)
			{
			    try
			    {
				Hashtable netAttr = xmlNode.getAttributeList();

				NetworkDiscoveryEntry networkDiscoveryEntry = new NetworkDiscoveryEntry();
				networkDiscoveryEntry.setNetworkDiscoveryIndex(new Integer(netDiscIndex));
				networkDiscoveryEntry.setNetIPAddress((String)netAttr.get("NETWORK_ID"));//No I18N
				networkDiscoveryEntry.setNetMask((String)netAttr.get("NETMASK"));//No I18N

				String temp = (String)netAttr.get("START_IP");//No I18N
				if(temp != null)
				    networkDiscoveryEntry.setStartIPAddress(temp);
				temp = (String)netAttr.get("END_IP");//No I18N
				if(temp != null)
				    networkDiscoveryEntry.setEndIPAddress(temp);

				if((childNode.getNodeName().equals("TO_DISCOVER")))//No I18N
				    networkDiscoveryEntry.setDoDiscovery(new Integer(1));
				else
				    networkDiscoveryEntry.setDoDiscovery(new Integer(2));

				temp = (String)netAttr.get("DHCP");//No I18N
				if((temp != null) && temp.trim().equalsIgnoreCase("true"))//No I18N
				    networkDiscoveryEntry.setDhcp(new Integer(1));

				Object[] index = new Object[1];
				index[0] = new Integer(netDiscIndex++);
				networkDiscoveryTable.put(index,networkDiscoveryEntry);
			    }
			    catch(Exception e){}
			}
		    }
		}
		else if( (childNode.getNodeName().equals("TO_DISCOVERIP"))|| (childNode.getNodeName().equals("NOT_TO_DISCOVERIP")))//No I18N
		{
		    /*
		     * Initializing all the node discovery details
		     */
		    Vector vector = childNode.getChildNodes();

		    for(int j = 0; j < vector.size(); j++)
		    {
			XMLNode xmlNode = (XMLNode)vector.elementAt(j);

			if(xmlNode.getNodeType() == XMLNode.ELEMENT)
			{
			    try
			    {
				Hashtable nodeAttr = xmlNode.getAttributeList();

				NodeDiscoveryEntry nodeDiscoveryEntry = new NodeDiscoveryEntry();

				nodeDiscoveryEntry.setNodeIP((String)nodeAttr.get("NODE_ID"));//No I18N
				nodeDiscoveryEntry.setNodeNetMask((String)nodeAttr.get("NETMASK"));//No I18N

				if((childNode.getNodeName().equals("TO_DISCOVERIP")))//No I18N
				    nodeDiscoveryEntry.setNodeDiscoverEnable(new Integer(1));
				else
				    nodeDiscoveryEntry.setNodeDiscoverEnable(new Integer(2));

				String temp = (String)nodeAttr.get("SNMPAGENTPORT");//No I18N
				if(temp != null)
				    nodeDiscoveryEntry.setSnmpAgentPort(new Integer(temp));

				temp = (String)nodeAttr.get("COMMUNITY");//No I18N
				if(temp != null)
				    nodeDiscoveryEntry.setNodeCommunity(temp);

				temp = (String)nodeAttr.get("SNMP_VERSION");//No I18N
				if(temp != null)
				{
				    if(temp.trim().equalsIgnoreCase("SNMPv1"))//No I18N
				       nodeDiscoveryEntry.setSnmpVersion(new Integer(1));
				    else if(temp.trim().equalsIgnoreCase("SNMPv2c"))//No I18N
				       nodeDiscoveryEntry.setSnmpVersion(new Integer(2));
				    else if(temp.trim().equalsIgnoreCase("SNMPv3"))//No I18N
				       nodeDiscoveryEntry.setSnmpVersion(new Integer(3));
				}

				temp = (String)nodeAttr.get("USERNAME");//No I18N
				if(temp != null)
				    nodeDiscoveryEntry.setV3UserName(temp);//No I18N

				temp = (String)nodeAttr.get("CONTEXT_NAME");//No I18N
				if(temp != null)
				    nodeDiscoveryEntry.setV3ContextName(temp);

				Object[] index = new Object[1];
				index[0] = (String)nodeAttr.get("NODE_ID");//No I18N
				nodeDiscoveryTable.put(index,nodeDiscoveryEntry);
			    }
			    catch(Exception e){}
			}
		    }
		}
		else if((childNode.getNodeName().equals("ALLOW_CRITERIA")) || (childNode.getNodeName().equals("DISALLOW_CRITERIA")))//No I18N
		{
		    /*
		     * Initializing all the criteria details
		     */
		    try
		    {
			String[] types = {"name","type","ipAddress","sysOID","isSNMP"};//No I18N
			Hashtable critAttr = childNode.getAttributeList();

			for(int k = 0; k < types.length; k++)
			{
			    String temp = (String)critAttr.get(types[k]);
			    if(temp != null)
			    {
				MoCriteriaEntry moCriteriaEntry = new MoCriteriaEntry();

				moCriteriaEntry.setCriteriaIndex(new Integer(k+1));

				moCriteriaEntry.setPropertyName(new Integer(k+1));
				moCriteriaEntry.setPropertyValue(temp);

				if((childNode.getNodeName().equals("ALLOW_CRITERIA")))//No I18N
				    moCriteriaEntry.setAllow(new Integer(1));
				else
				    moCriteriaEntry.setAllow(new Integer(2));

				Object[] index = new Object[1];
				index[0] = new Integer(k+1);
				moCriteriaTable.put(index,moCriteriaEntry);
			    }
			}
		    }
		    catch(Exception e){}
		}
	    }
	}
	loaded = true;
    }

    boolean discFilterLoaded = false;

    /*
     * Initializing all the discovery filter details
     */
    void loadDiscFilters()
    {
	if(!agentName.initTopo() || discFilterLoaded)
	    return;

	/*
	 * Getting the Root Node for all the discovery filters
	 */
	XMLNode rootNode = com.adventnet.nms.topodb.DBServer.filterList.getDiscFilters();

	Vector childVector = rootNode.getChildNodes();

	for(int i = 0; i < childVector.size(); i++)
	{
	    XMLNode childNode = (XMLNode)childVector.elementAt(i);

	    if(childNode.getNodeType() == XMLNode.ELEMENT)
	    {
		if(childNode.getNodeName().equals("FILTER"))//No I18N
		{
		    try
		    {
			DiscoveryFilterEntry discoveryFilterEntry = new DiscoveryFilterEntry();

			discoveryFilterEntry.setDiscFilterIndex(new Integer(i));

			discoveryFilterEntry.setDiscFilterClassName((String)childNode.getAttribute("className"));//No I18N

			Object[] index = new Object[1];
			index[0] = new Integer(i);
			discFilterTable.put(index,discoveryFilterEntry);
		    }
		    catch(Exception e){}
		}
	    }
	}
	discFilterLoaded = true;
    }

    /*boolean listIconLoaded = false;

    void loadListIcons()
    {
	if(!agentName.initTopo() || listIconLoaded)
	    return;

	XMLNode rootNode = com.adventnet.nms.topodb.DBServer.filterList.getListIcon();

	Vector childVector = rootNode.getChildNodes();

	for(int i = 0; i < childVector.size(); i++)
	{
	    XMLNode childNode = (XMLNode)childVector.elementAt(i);

	    if(childNode.getNodeType() == XMLNode.ELEMENT)
	    {
		if(childNode.getNodeName().equals("DATA"))//No I18N
		{
		    try
		    {
			ListIconEntry listIconEntry = new ListIconEntry();

			listIconEntry.setMoUIConfIndex(new Integer(i));
			listIconEntry.setMotype((String)childNode.getAttribute("TYPE"));//No I18N
			listIconEntry.setMenuName((String)childNode.getAttribute("MENU"));//No I18N
			listIconEntry.setClearSeverityImage((String)childNode.getAttribute("CLEAR_IMG"));//No I18N
			listIconEntry.setWarningSeverityImage((String)childNode.getAttribute("WARNING_IMG"));//No I18N
			listIconEntry.setMinorSeverityImage((String)childNode.getAttribute("MINOR_IMG"));//No I18N
			listIconEntry.setMajorSeverityImage((String)childNode.getAttribute("MAJOR_IMG"));//No I18N
			listIconEntry.setCriticalSeverityImage((String)childNode.getAttribute("CRITICAL_IMG"));//No I18N
			listIconEntry.setUnknownSeverityImage((String)childNode.getAttribute("UNKNOWN_IMG"));//No I18N

			Object[] index = new Object[1];
			index[0] = new Integer(i);
			listIconTable.put(index,listIconEntry);
		    }
		    catch(Exception e){}
		}
	    }
	}
	listIconLoaded = true;
    }*/

    /*
     * Setting all the seed file details
     */
    boolean setSeedData(int mode)
    {
	if(!agentName.initTopo())
	    return false;

	if(mode == 1)
	{
	    //DISCOVERY SET
	    try
	    {
		if(autoDisc.intValue() == 1)
		    discoveryNode.setAttribute("DISCOVER","true");//No I18N
		else
		    discoveryNode.setAttribute("DISCOVER","false");//No I18N

		if(reDisc.intValue() == 1)
		    discoveryNode.setAttribute("REDISCOVER_ALREADY_DISCOVERED","true");//No I18N
		else
		    discoveryNode.setAttribute("REDISCOVER_ALREADY_DISCOVERED","false");//No I18N

		if(discLocalNet.intValue() == 1)
		    discoveryNode.setAttribute("DISCOVER_LOCALNET","true");//No I18N
		else
		    discoveryNode.setAttribute("DISCOVER_LOCALNET","false");//No I18N

		if(enableLog.intValue() == 1)
		    discoveryNode.setAttribute("ENABLE_LOG","true");//No I18N
		else
		    discoveryNode.setAttribute("ENABLE_LOG","false");//No I18N

		discoveryNode.setAttribute("DISCOVERY_INTERVAL",discInterval.toString());//No I18N
		discoveryNode.setAttribute("REDISCOVER_INTERVAL",reDiscInterval.toString());//No I18N
		discoveryNode.setAttribute("HOUR",hour);//No I18N
		discoveryNode.setAttribute("DAY_OF_THE_WEEK",day_of_week);//No I18N
		discoveryNode.setAttribute("DAY_OF_THE_MONTH",day_of_month);//No I18N

		if(enableSnmpPing.intValue() == 1)
		    discoveryNode.setAttribute("ENABLE_SNMP_DISCOVERY","true");//No I18N
		else
		    discoveryNode.setAttribute("ENABLE_SNMP_DISCOVERY","false");//No I18N

		discoveryNode.setAttribute("SNMP_RETRIES",snmpPingRetries.toString());//No I18N
		discoveryNode.setAttribute("SNMP_TIMEOUT",snmpPingTimeout.toString());//No I18N

		if(!snmpPort.equals(""))//No I18N
		    discoveryNode.setAttribute("SNMP_PORTS",snmpPort);//No I18N

		discoveryNode.setAttribute("READ_COMMUNITY",readCommunity);//No I18N
		discoveryNode.setAttribute("WRITE_COMMUNITY",writeCommunity);//No I18N

		if(enableSnmpV3ping.intValue() == 1)
		    discoveryNode.setAttribute("ENABLE_SNMPV3_DISCOVERY","true");//No I18N
		else
		    discoveryNode.setAttribute("ENABLE_SNMPV3_DISCOVERY","false");//No I18N

		if(!snmpV3UserName.equals(""))//No I18N
		    discoveryNode.setAttribute("SNMPV3_USERNAMES",snmpV3UserName);//No I18N

		if(!snmpV3ContextName.equals(""))//No I18N
		    discoveryNode.setAttribute("SNMPV3_CONTEXTNAME",snmpV3ContextName);//No I18N

		if(enableIcmp.intValue() == 1)
		    discoveryNode.setAttribute("ENABLE_ICMP_DISCOVERY","true");//No I18N
		else
		    discoveryNode.setAttribute("ENABLE_ICMP_DISCOVERY","false");//No I18N

		discoveryNode.setAttribute("PING_RETRIES",pingRetries.toString());//No I18N

		com.adventnet.nms.topodb.DBServer.topodb.setDiscoveryParameters(discoveryNode);
	    }
	    catch(Exception e)
	    {
		System.out.println(" Exception while setting the seed datas ");//No I18N
	    }
	}
	else if(mode == 2)
	{
	    //NATIVE PING SET
	    try
	    {
		nativePingNode.setAttribute("ICMP_RETRIES",icmpPingRetries.toString());//No I18N
		nativePingNode.setAttribute("ICMP_TIMEOUT",icmpTimeout.toString());//No I18N
		nativePingNode.setAttribute("ICMP_DEBUG_LEVEL",icmpDebugLevel.toString());//No I18N
		nativePingNode.setAttribute("ICMP_SWEEP_PACKETS",icmpSweepPkts.toString());//No I18N
		nativePingNode.setAttribute("ICMP_SWEEP_SLEEP_INTERVAL",icmpSweepInterval.toString());//No I18N

		if(enableSweep.intValue() == 1)
		    nativePingNode.setAttribute("PING_SWEEP","true");//No I18N
		else
		    nativePingNode.setAttribute("PING_SWEEP","false");//No I18N

		com.adventnet.nms.topodb.DBServer.topodb.setDiscoveryParameters(nativePingNode);
	    }
	    catch(Exception e)
	    {
		System.out.println(" Exception while setting the seed datas ");//No I18N
	    }
	}
	else if(mode == 3)
	{
	    //TO_DISCOVER AND NOT_TO_DISCOVER SET
	    try
	    {
		XMLNode toDiscNode = new XMLNode();
		toDiscNode.setNodeName("TO_DISCOVER");//No I18N
		toDiscNode.setNodeType(XMLNode.ELEMENT);

		XMLNode notToDiscNode = new XMLNode();
		notToDiscNode.setNodeName("NOT_TO_DISCOVER");//No I18N
		notToDiscNode.setNodeType(XMLNode.ELEMENT);

		Enumeration keys = networkDiscoveryTable.keys();
		while(keys.hasMoreElements())
		{
		    NetworkDiscoveryEntry networkDiscoveryEntry =
			(NetworkDiscoveryEntry)networkDiscoveryTable.get(keys.nextElement());

		    XMLNode childNode = new XMLNode();
		    childNode.setNodeName("net");//No I18N
		    childNode.setNodeType(XMLNode.ELEMENT);

		    if(networkDiscoveryEntry.getDoDiscovery().intValue() == 1)
		    {
			childNode.setAttribute("NETWORK_ID",networkDiscoveryEntry.getNetIPAddress());//No I18N
			childNode.setAttribute("NETMASK",networkDiscoveryEntry.getNetMask());//No I18N

			if(!networkDiscoveryEntry.getStartIPAddress().equals(""))//No I18N
			    childNode.setAttribute("START_IP",networkDiscoveryEntry.getStartIPAddress());//No I18N
			else if(!networkDiscoveryEntry.getEndIPAddress().equals(""))//No I18N
			{
			    networkDiscoveryEntry.setStartIPAddress(networkDiscoveryEntry.getEndIPAddress());
			    childNode.setAttribute("START_IP",networkDiscoveryEntry.getEndIPAddress());//No I18N
			}

			if(!networkDiscoveryEntry.getEndIPAddress().equals(""))//No I18N
			    childNode.setAttribute("END_IP",networkDiscoveryEntry.getEndIPAddress());//No I18N
			else if(!networkDiscoveryEntry.getStartIPAddress().equals(""))//No I18N
			{
			    networkDiscoveryEntry.setEndIPAddress(networkDiscoveryEntry.getStartIPAddress());
			    childNode.setAttribute("END_IP",networkDiscoveryEntry.getStartIPAddress());//No I18N
			}

			if(networkDiscoveryEntry.getDhcp().intValue() == 1)
			    childNode.setAttribute("DHCP","true");//No I18N

			toDiscNode.addChildNode(childNode);
		    }
		    else
		    {
			childNode.setAttribute("NETWORK_ID",networkDiscoveryEntry.getNetIPAddress());//No I18N
			childNode.setAttribute("NETMASK",networkDiscoveryEntry.getNetMask());//No I18N

			notToDiscNode.addChildNode(childNode);
		    }
		}

		com.adventnet.nms.topodb.DBServer.topodb.setDiscoveryParameters(toDiscNode);

		com.adventnet.nms.topodb.DBServer.topodb.setDiscoveryParameters(notToDiscNode);

	    }
	    catch(Exception e)
	    {
		System.out.println(" Exception while setting the seed datas ");//No I18N
	    }
	}
	else if(mode == 4)
	{
	    //TO_DISCOVERIP AND NOT_TO_DISCOVERIP SET
	    try
	    {
		XMLNode toDiscNode = new XMLNode();
		toDiscNode.setNodeName("TO_DISCOVERIP");//No I18N
		toDiscNode.setNodeType(XMLNode.ELEMENT);

		XMLNode notToDiscNode = new XMLNode();
		notToDiscNode.setNodeName("NOT_TO_DISCOVERIP");//No I18N
		notToDiscNode.setNodeType(XMLNode.ELEMENT);

		Enumeration keys = nodeDiscoveryTable.keys();
		while(keys.hasMoreElements())
		{
		    NodeDiscoveryEntry nodeDiscoveryEntry =
			(NodeDiscoveryEntry)nodeDiscoveryTable.get(keys.nextElement());

		    XMLNode childNode = new XMLNode();
		    childNode.setNodeName("ip");//No I18N
		    childNode.setNodeType(XMLNode.ELEMENT);

		    if(nodeDiscoveryEntry.getNodeDiscoverEnable().intValue() == 1)
		    {
			childNode.setAttribute("NODE_ID",nodeDiscoveryEntry.getNodeIP());//No I18N
			childNode.setAttribute("NETMASK",nodeDiscoveryEntry.getNodeNetMask());

			if(nodeDiscoveryEntry.getSnmpAgentPort().intValue() != -1)
			    childNode.setAttribute("SNMPAGENTPORT",nodeDiscoveryEntry.getSnmpAgentPort().toString());//No I18N

			int tempVer = nodeDiscoveryEntry.getSnmpVersion().intValue();
			if(tempVer == 1)
			    childNode.setAttribute("SNMP_VERSION","SNMPv1");//No I18N
			else if(tempVer == 2)
			    childNode.setAttribute("SNMP_VERSION","SNMPv2c");//No I18N
			else
			    childNode.setAttribute("SNMP_VERSION","SNMPv3");//No I18N

			if(tempVer == 1 || tempVer == 2)
			    childNode.setAttribute("COMMUNITY",nodeDiscoveryEntry.getNodeCommunity());//No I18N
			else if(tempVer == 3)
			{
			    childNode.setAttribute("USERNAME",nodeDiscoveryEntry.getV3UserName());//No I18N
			    childNode.setAttribute("CONTEXT_NAME",nodeDiscoveryEntry.getV3ContextName());//No I18N
			}

			toDiscNode.addChildNode(childNode);
		    }
		    else
		    {
			childNode.setAttribute("NODE_ID",nodeDiscoveryEntry.getNodeIP());//No I18N
			childNode.setAttribute("NETMASK",nodeDiscoveryEntry.getNodeNetMask());//No I18N

			notToDiscNode.addChildNode(childNode);
		    }
		}

		com.adventnet.nms.topodb.DBServer.topodb.setDiscoveryParameters(toDiscNode);

		com.adventnet.nms.topodb.DBServer.topodb.setDiscoveryParameters(notToDiscNode);

	    }
	    catch(Exception e)
	    {
		System.out.println(" Exception while setting the seed datas ");//No I18N
	    }
	}
	else if(mode == 5)
	{
	    //ALLOW_CRITERIA AND DISALLOW_CRITERIA SET
	    XMLNode allowNode = new XMLNode();
	    allowNode.setNodeName("ALLOW_CRITERIA");//No I18N
	    allowNode.setNodeType(XMLNode.ELEMENT);

	    XMLNode disAllowNode = new XMLNode();
	    disAllowNode.setNodeName("DISALLOW_CRITERIA");//No I18N
	    disAllowNode.setNodeType(XMLNode.ELEMENT);

	    try
	    {
		String[] types = {"name","type","ipAddress","sysOID","isSNMP"};//No I18N
		Enumeration keys = moCriteriaTable.keys();

		while(keys.hasMoreElements())
		{
		    MoCriteriaEntry moCriteriaEntry = (MoCriteriaEntry)moCriteriaTable.get(keys.nextElement());
		    if(moCriteriaEntry.getAllow().intValue() == 1)
			allowNode.setAttribute(types[moCriteriaEntry.getPropertyName().intValue() - 1],
					       moCriteriaEntry.getPropertyValue());
		    else
			disAllowNode.setAttribute(types[moCriteriaEntry.getPropertyName().intValue() - 1],
						  moCriteriaEntry.getPropertyValue());
		}

		com.adventnet.nms.topodb.DBServer.topodb.setDiscoveryParameters(allowNode);

		com.adventnet.nms.topodb.DBServer.topodb.setDiscoveryParameters(disAllowNode);
	    }
	    catch(Exception e)
	    {
		System.out.println(" Exception while setting the seed datas ");//No I18N
	    }
	}
	return true;
    }

    /*
     * Setting the discovery filter class names
     */
    boolean setDiscFilters(String type,String className)
    {
	if(!agentName.initTopo())
	    return false;

	XMLNode rootNode = new XMLNode();
	rootNode.setNodeName("DISCOVERY_FILTERS");//No I18N
	rootNode.setNodeType(XMLNode.ELEMENT);

	XMLNode discFilterNode = new XMLNode();
	discFilterNode.setNodeName("FILTER");//No I18N
	discFilterNode.setNodeType(XMLNode.ELEMENT);
	discFilterNode.setAttribute("className",className);//No I18N

	rootNode.addChildNode(discFilterNode);

	com.adventnet.nms.topodb.DBServer.filterList.handleDiscFilter(rootNode,type);

	return true;
    }

    /*boolean setListIcons(String type,ListIconEntry entry)
    {
	if(!agentName.initTopo())
	    return false;

	try
	{
	    XMLNode rootNode = new XMLNode();
	    rootNode.setNodeName("LIST_ICON_DATA");//No I18N
	    rootNode.setNodeType(XMLNode.ELEMENT);//No I18N

	    XMLNode listIconNode = new XMLNode();
	    listIconNode.setNodeName("DATA");//No I18N
	    listIconNode.setNodeType(XMLNode.ELEMENT);

	    listIconNode.setAttribute("TYPE",entry.getMotype());

	    String temp = entry.getMotype();
	    temp = (entry.getMenuName().equals("")) ? temp : entry.getMenuName();//No I18N
	    listIconNode.setAttribute("MENU",temp);//No I18N
	    entry.setMenuName(temp);

	    listIconNode.setAttribute("CLEAR_IMG",entry.getClearSeverityImage());//No I18N
	    listIconNode.setAttribute("WARNING_IMG",entry.getWarningSeverityImage());//No I18N
	    listIconNode.setAttribute("MINOR_IMG",entry.getMinorSeverityImage());//No I18N
	    listIconNode.setAttribute("MAJOR_IMG",entry.getMajorSeverityImage());//No I18N
	    listIconNode.setAttribute("CRITICAL_IMG",entry.getCriticalSeverityImage());//No I18N
	    listIconNode.setAttribute("UNKNOWN_IMG",entry.getUnknownSeverityImage());//No I18N

	    rootNode.addChildNode(listIconNode);

	    com.adventnet.nms.topodb.DBServer.filterList.handleListIcon(rootNode,type);
	    return true;
	}
	catch(Exception e)
	{
	    return false;
	}
    }*/
}

