//$Id: SnmpManagerFilter.java,v 1.2 2007/04/30 17:21:28 tinku Exp $
package com.adventnet.nms.jmxagent;

import java.util.Vector;

/**
 * This is the interface that needs to be implemented to filter/select the manager
 * list to whom the trap should be sent.
 *
 * To filter out the manager list, the user need to over-ride the method
 * <code>getManagerList()</code>. There, he can process the
 * {@link com.adventnet.nms.jmxagent.SnmpTrapHolder} object which will be passed
 * to this method and select a set of managers from the whole list which is
 * configured in TrapForwarding table.
 *
 * Also, user need to add an entry in <WebNMS_Home>/conf/nmsInterfaces.conf file,
 * so that the JMX Agent can load the implemented filter class and invoke the
 * <code>getManagerList()</code> to get the filtered list of managers.
 *
 * The entry should be like <code><b>snmpManagerFilter="FilterClassName"</b></code>.
 */
public interface SnmpManagerFilter
{
    /**
     * Invoked before sending every trap to the managers. The passed
     * {@link com.adventnet.nms.jmxagent.SnmpTrapHolder} object contains
     * a <code>Vector</code> of manager list which is configured in TrapForwarding table.
     *
     * User needs to return a <code>Vector</code> of managers to whom the trap
     * should be sent. If the user <b>does not</b> want to send this trap to
     * any of the managers, <code>null</code> should be returned.
     *
     * @param holder - a <code>SnmpTrapHolder</code> object.
     *
     * @return a <code>Vector</code> of short-listed <code>ManagerInfo</code>s.
     */
    public Vector getManagerList(SnmpTrapHolder holder);
}
