//$Id: ConfReader.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/**<b>@(#)ConfReader.java </b>	
 * Copyright (c) 1996 Advent Network Management, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 */
package test;

import java.util.Hashtable;
import java.util.Vector;


// AdventNet imports
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.*;

/**  An example ConfReader used to get details of Managers to whom trap 
 * has to forwarded. This will read an conf file namely, trap.forwarder 
 * in  regular intervals. And then stores manager Vs Port details 
 * in a vector of hashtable. Interval for reading the trap.forwarder 
 * has to be configured in  trap.forwarder.

  @author Karthick Srinivasan 
  @version 1.0
*/


public class ConfReader implements Runnable
{
    private Vector nodes,mgrList;
    private XMLNode node;
    private int refresh = 0;
    private  Integer syncInt = new Integer(0);

    private static ConfReader reader = null;
    
    /** gives the Static instance of conf Reader. Avoids duplication of 
     *  ConfReader instances during Refresh of TrapFilter.
     */
    public synchronized static ConfReader getInstance()
    {
        if(reader == null)
        {
            reader = new ConfReader();
        }
        return reader;
    }

    private ConfReader()
    {
        readFile();
    }

    /** Reads the Conf file namely conf/trap.forwarder.
     *  And then stores manager Vs Port details in a vector of hashtable.
     *  Interval for reading the trap.forwarder  has to be configured in
     *  trap.forwarder. Refer trap.forwarder for more details.
     */
    private void readFile()
    {
        String fileName=PureUtils.rootDir+"conf/trap.forwarder";
        XMLDataReader reader = new XMLDataReader(fileName,false);
        nodes = reader.getRootChildNodes();
        /** While setting Manager details.
         * WebNMS should wait till new configuration 
         * details are set in the object.
         * Refer synchronization of same variable in
         * getMgrList() method.
         */
        synchronized(syncInt)
        {
            mgrList=new Vector();
            for(int i=0;i<nodes.size();i++)
            {
                
                node = (XMLNode)nodes.elementAt(i);
                if(node.getNodeType() == XMLNode.ELEMENT)
                {
                    /** Manager details are set as Manager Vs port.
                     */ 
                    if(node.getNodeName().equals("TRAP-REDIRECTION"))
                    {
                        try
                        {
                            mgrList.add((Object)node.getAttributeList());
                        }
                        catch(Exception e)
                        {
                            NmsLogMgr.EVENTERR.fail(NmsUtil.GetString("Trap Forwarder - ConfReader : error in reading Manager details.")+" : " + e,e);
                        }
                    }
                    /** Refresh period is read and configured.
                     */
                    if(node.getNodeName().equals("TRAP-FILE"))
                    {
                        
                        try
                        {
                            String refreshPeriod =(String)node.getAttribute("Refresh_Time");
                            
                            if(refreshPeriod != null)
                            {
                                refresh = Integer.parseInt(refreshPeriod);
                            }
                        }
                        catch(Exception e)
                        {
                            NmsLogMgr.EVENTERR.fail(NmsUtil.GetString("Trap Forwarder - ConfReader : error in reading refresh period for reading trap.forwarder.")+" : " + e,e);
                        }
                    }
                }
            }
        }
        if(refresh != 0)
        {
            schedule(this,refresh*1000);
        }
    }
 
    private void schedule(Runnable rpi,int timeInterval)
    {
        NmsUtil.scheduler.scheduleTask(rpi,System.currentTimeMillis()+timeInterval); 
    }
 
    
    /**
     * Get the value of ManagerList.
     * @return value of MgrList.
     */
    public Vector getMgrList() 
    {
        /** While setting Manager details.
         * WebNMS should wait till new configuration 
         * details are set in the object. 
         * Refer synchronization of same variable
         * in readFile() method.
         */
        synchronized(syncInt)
        {
            return mgrList;
        }
    }

    public void run()
    {
        readFile();
    }
}


