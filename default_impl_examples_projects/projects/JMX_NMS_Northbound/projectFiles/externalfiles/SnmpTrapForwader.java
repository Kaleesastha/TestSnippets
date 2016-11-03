//$Id: SnmpTrapForwader.java,v 1.3 2007/04/30 19:02:06 tinku Exp $
package com.adventnet.nms.jmxagent;

import com.adventnet.nms.util.*;

import com.adventnet.snmp.snmp2.agent.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;
import com.adventnet.nms.util.SocketUtil;

import java.util.*;
import java.io.*;

/**
 * Class to forward the traps to eiter all managers which are configured in TrapForwarding table or
 * managers which returned from filter class. If any filter found, class will invoke the
 * filter's getManagerList() with SnmpTrapHolder, then send the trap to all managers which returned by
 * the filter.
 */
public class SnmpTrapForwader extends SnmpTrapService
{

    SnmpManagerFilter filter = null;

    MibOperations mibOps = new MibOperations();

    boolean isMibLoaded = true;
    private String localip = SocketUtil.getServerHost();

    public SnmpTrapForwader()
    {
	super();

	//isMibLoaded = loadMib();

	//initFilters();
    intializeTrapForwarder();
    }

    public SnmpTrapForwader(int port)
    {
	super(port);

	//isMibLoaded = loadMib();

	//initFilters();
    intializeTrapForwarder();
    }


    public SnmpTrapForwader(boolean isPersistence)
    {
	super(isPersistence);

	//isMibLoaded = loadMib();

	//initFilters();
    intializeTrapForwarder();
    }

    public SnmpTrapForwader(String dir, String fileName, boolean fTovPersistence)
    {
	super(dir, fileName, fTovPersistence);

	//isMibLoaded = loadMib();

	//initFilters();
    intializeTrapForwarder();
    }

    /**
     * To load the mib AdventNet-WebNMS-MIB into SnmpMibOperations if the MIB present in.
     * <WebNMS_Home>/mibs/ directory. While converting the trap varbinds as properties, mibOps is
     * used to find the name of a OID. If the MIB is not present, the OID itslef will be
     * assigned into properties.
     */
    private boolean loadMib()
    {
	try
	{
            mibOps.loadMibModules("\"" + PureUtils.rootDir + File.separator + "mibs" + File.separator + "AdventNet-WebNMS-MIB\"");//No I18N

	    return true;
	}
	catch (Exception ex)
	{
            System.err.println("Error loading MIB AdventNet-WebNMS-MIB: " + ex);//No I18N

	    return false;
	}
    }

    /**
     * To load the filter class if it is specified in <WebNMS_Home>/conf/nmsInterfaces.conf
     * file.
     */
    private void initFilters()
    {
	try
	{
	    XMLDataReader xmld = new XMLDataReader("file:" + PureUtils.rootDir + File.separator + "conf" + File.separator + "nmsInterfaces.conf");//No I18N

	    XMLNode rootNode = xmld.getRootNode();

            Vector nodes = rootNode.getChildNodes();

	    int noOfChildren = nodes.size();

            for(int i = 0;i < noOfChildren; i++)
            {
		XMLNode xmlNode = (XMLNode)nodes.elementAt(i);

		if( (xmlNode.getNodeType() == xmlNode.ELEMENT) &&
		    (xmlNode.getNodeName().equals("INTERFACES")) ) //No I18N
		{
		    String filterClassName = (String)xmlNode.getAttribute("snmpManagerFilter");//No I18N


		    if(filterClassName != null)
		    {
			try
			{
			    filter = (SnmpManagerFilter)Class.forName(filterClassName).newInstance();
			}
			catch(Exception ex)
			{
			    System.err.println(" Problem while loading the filter class " + filterClassName + ": " + ex);//No I18N
			}
		    }
		}
	    }
	}
	catch(Exception e)
	{
	    System.err.println("Exception while reading the file nmsInterfaces.conf: " + e);//No I18N
	}
    }

    /**
     * To invoke the filter and decide the managers to whom the trap should send.
     * This method has been over-ridden from SnmpTrapService class. This method will be
     * called, when SnmpTrapService's sendTrap() called by external class like AdventNet_WebNMS_MIB_JMX.java.
     */
    public void sendTrapToManagersInTable(SnmpOID enterprise,
					  String community,
					  int trapIndex,
					  long timeticks,
					  Vector varbindings,
					  int version)
    {

        Object obj =  getTrapForwardingTable();
        Vector allManagers = new Vector();
        Vector toBeSentManagers = allManagers;

        if(obj instanceof V3TrapForwardingTableInterface)
        {
            V3TrapForwardingTableInterface v3tft = (V3TrapForwardingTableInterface ) obj;

            synchronized (v3tft)
            {
                int noOfManagers =  v3tft.getNumRows();
                for (int i =0; i < noOfManagers; i++)
                {
                    try
                    {
                        V3ForwardingEntry entry = v3tft.getV3ForwardingEntry(i);
                        if (v3tft.getRowStatus(i) != 1)
                            continue;
                        String managerHost = entry.getV3ManagerHost();
                        if (managerHost == null)
                            continue;
                        int managerPort = entry.getV3ManagerPort().intValue();
                        if ((managerPort == -1) || (managerPort == 0))
                            managerPort = 162;
                        allManagers.addElement(new ManagerInfo(managerHost, managerPort,entry));
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        else
        {
            TrapForwardingTableInterface tft = (TrapForwardingTableInterface )obj;
            synchronized (tft)
            {
                int noOfManagers =  tft.getNumRows();
                for (int i =0; i < noOfManagers; i++)
                {

                    try
                    {
                        ForwardingEntry entry = tft.getForwardingEntry(i);
                        if(entry.getV1v2ManagerStatus().intValue() != 1)
                        {
                            continue;
                        }

                        String managerHost = entry.getV1v2ManagerHost();
                        if (managerHost == null)
                            continue;

                        int managerPort = entry.getV1v2ManagerPort().intValue();
                        if ((managerPort == -1) || (managerPort == 0))
                            managerPort = 162;

                        allManagers.addElement(new ManagerInfo(managerHost, managerPort,entry));// According to the entry trap will be sent.
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        /**
         * Warpping all trap details like trap oid, varbinds, time, etc as a SnmpTrapHolder object
         * and calling filter object's getManagerList() if filter class found.
         */
        if(filter != null)
        {
            Properties trapProperties = convertVarbindToProperties(varbindings);

            SnmpTrapHolder holder = new SnmpTrapHolder(enterprise.toString(),
                                                       timeticks,
                                                       community,
                                                       trapProperties,
                                                       allManagers);

            toBeSentManagers = filter.getManagerList(holder);
        }

        if(toBeSentManagers == null)
            return;

        int noOfToBeSentMangers = toBeSentManagers.size();

        /**
         * Sending the trap to either all the managers or the manager list returned by the filter.
         */

        for(int i = 0; i < noOfToBeSentMangers; i++)
        {
            ManagerInfo manager = (ManagerInfo) toBeSentManagers.elementAt(i);
            sendTrap(manager.getForEntry(),enterprise, community, trapIndex, timeticks, varbindings);
            //sendTrap(manager.getHost(),manager.getPort(),enterprise,manager.getCommunity(),
            //trapIndex,timeticks,varbindings,manager.getVersion());
        }

    }


    /**
     * To convert the snmp varbinds as properties. Properties having either name-value pair if
     * AdventNet-WebNMS-MIB has been loaded in to mibOps or oid-value pair.
     */
    private Properties convertVarbindToProperties(Vector varbindings)
    {
	Properties properties = new Properties();

	int noOfVarbinds = varbindings.size();

	for(int i = 0; i < noOfVarbinds; i++)
	{
	    SnmpVarBind varbind = (SnmpVarBind)varbindings.elementAt(i);

	    SnmpOID oid = varbind.getObjectID();

	    String nodeName = oid.toString();

	    if(isMibLoaded)
		nodeName = mibOps.getMibNode(oid).getLabel();

	    SnmpVar var = varbind.getVariable();

	    String value = var.toString();

	    properties.put(nodeName, value);
	}

	return properties;
    }

    // To set the virtualip configured in startnms for the SnmpTrapService - fix for Nortel
    private void setLocalip()
    {
        if (localip != null)
        {
            setLocalAddress(localip);
        }
    }

    private void  intializeTrapForwarder()
    {
        setLocalip();
        isMibLoaded = loadMib();
        initFilters();
    }
}
