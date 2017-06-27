//$Id: ManagerInfo.java,v 1.2 2007/04/30 17:21:28 tinku Exp $
    package com.adventnet.nms.jmxagent;

/**
 * A holder class to store the manager's host string and port value.
 */
public class ManagerInfo
{
    private String host;

    private int port;

    private Object forEntry;

    /**
     * Construts a <code>ManagerIndo</code> object.
     *
     * @param host - manager's host string
     * @param port - manager's port number
     */
    public ManagerInfo(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    /**
     * Construts a <code>ManagerIndo</code> object.
     *
     * @param host - manager's host string
     * @param port - manager's port number
     * @param Object - manager's forwardingEntry object
     */
    public ManagerInfo(String host, int port, Object forEntry)
    {
        this.host = host;
        this.port = port;
        this.forEntry = forEntry;
    }


    /**
     * Returns the manager's host string.
     *
     * @return the manager host as a <code>String</code>
     */
    public String getHost()
    {
        return host;
    }

    /**
     * Returns the manager's port number.
     *
     * @return the manager port as <code>int</code>
     */
    public int getPort()
    {
        return port;
    }

    /**
     * Returns the manager's forwardingEntry object. It can be ForwardingEntry or V3ForwardingEntry.
     *
     * @return the manager's forwardingEntry object.
     */
    public Object getForEntry()
    {
        return forEntry;
    }
}
