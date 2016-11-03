//$Id: SnmpTrapHolder.java,v 1.2 2007/04/30 17:21:28 tinku Exp $
package com.adventnet.nms.jmxagent;

import java.util.Vector;
import java.util.Properties;


/**
 * A holder class to store the necessary details of an snmp trap. Details can be
 * stored are trapProperties, trapOID, timeticks and community. Additionally, it
 * holds a <code>Vector</code> of managers configured in TrapForwarding table.
 *
 * NMS JMX Agent will be calling the {@link com.adventnet.nms.jmxagent.SnmpManagerFilter}'s
 * <code>getManagerList()</code>with this holder object, before sending each trap
 * to managers.
 *
 */
public class SnmpTrapHolder
{

    private String trapOID;

    private long timeticks;

    private String community;

    private Properties trapProperties;

    private Vector managers;



    /**
     * Constructs an <code>SnmpTrapHolder</code> object.
     *
     * @param trapOID - snmp trap's OID as a <code>String</code>
     * @param timeticks - a long timeticks value
     * @param community - agent's community String
     * @param trapProperties - all varbinds as a <code>Properties</code>
     * @param managers - all configured managers as a <code>Vector</code>
     */
    public SnmpTrapHolder(String trapOID,
			  long timeticks,
			  String community,
			  Properties trapProperties,
			  Vector managers)
    {
	this.trapOID = trapOID;

	this.timeticks = timeticks;

	this.community = community;

	this.trapProperties = trapProperties;

	this.managers = managers;
    }

    /**
     * Returns the trap OID value.
     *
     * @return the trap OID as <code>String</code>
     */
    public String getTrapOID()
    {
	return trapOID;
    }

    /**
     * Returns the timeticks value.
     *
     * @return the timeticks as <code>long</code>
     */
    public long getTimeticks()
    {
	return timeticks;
    }

    /**
     * Returns the community string.
     *
     * @return the community as a <code>String</code>
     */
    public String getCommunity()
    {
	return community;
    }

    /**
     * Returns all properties of an snmp trap. All properties will be returned as name-value pair, if
     * AdventNet-WebNMS-MIB is present in <WebNMS_Home>/mibs directory. Otherwise, properties will be
     * oid-value pair.
     *
     * @return all the properties as a <code>Properties</code>
     */
    public Properties getTrapProperties()
    {
	return trapProperties;
    }

    /**
     * Returns all the manager hosts and ports configured in the TrapForwarding table.
     * Returns a <code>Vector</code> of {@link com.adventnet.nms.jmxagent.ManagerInfo}
     * objects in which, each <code>ManagerInfo</code> object is holding a host and
     * a port of a manager.
     *
     * @return a <code>Vetor</code> of <code>ManagerInfo</code>s
     */
    public Vector getManagers()
    {
	return managers;
    }
}
