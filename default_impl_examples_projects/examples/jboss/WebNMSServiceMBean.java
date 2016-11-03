//$Id: WebNMSServiceMBean.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $

/**
 * WebNMSServiceMBean.java
 *
 *
 * Created: Tue Jun 11 16:25:24 2002
 *
 * @author Chitrapandian N
 * @since AdventNet Web NMS 2.3
 */
package com.adventnet.nms.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Mbean interface to the Web NMS Service.
 *
 * @see ServiceMBean
 */
public interface WebNMSServiceMBean
    extends ServiceMBean
{
    /**
     * Sets the bind name of NameLookup interface in JNDI.
     *
     * @param name a <code>String</code> bind name.
     */
    public void setBindName(String name);
    
    /**
     * Returns the bind name of NameLookup interface in JNDI.
     *
     * @return a <code>String</code> bind name.
     */
    public String getBindName();

    /**
     * Sets the arguments used to start Web NMS FE Server 
     * from JBoss Application Server.
     *
     * @param args a <code>String</code> value
     */
    public void setArgs(String args);
    
    /**
     * Returns the arguments used to start Web NMS FE Server 
     * from JBoss Application Server.
     *
     * @return a <code>String</code> value
     */
    public String getArgs();
}

// WebNMSServiceMBean.java
