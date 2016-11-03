//$Id: RedirectionTrapFilter.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/**<b>@(#)RedirectionTrapFilter.java </b>	
 * Copyright (c) 1996 Advent Network Management, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 */
package test;

import java.util.Hashtable;
import java.util.Vector;

import java.rmi.*;
import java.io.*;
import java.net.InetAddress;
// AdventNet imports
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.*;
import com.adventnet.nms.eventdb.*;


/**  An example RedirectionTrapFilter used to forward Trap which 
 * WebNms receives to managers specified in an conf file namely 
 * trap.forwarder.
  @author Karthick Srinivasan 
  @version 1.0
*/

public class RedirectionTrapFilter implements TrapFilter
{
    XMLNode node;
    SnmpAPI api = null;
    SnmpSession session = null;
    int refresh = 0;
    Integer syncInt = new Integer(0);

    ConfReader confReader = null;
    boolean firstTime = true;
    /** Initialize Snmp Session which will enable webNms to forward
     *  Snmp traps to Configured Managers.
     */
    private void initSnmp()
    {
        try
        {

            api=new SnmpAPI();
            api.start();
            session=new SnmpSession(api);
            session.open();
        }
        catch(Exception ee)
        {
            NmsLogMgr.EVENTERR.fail(NmsUtil.GetString("Trap Forwarder - RedirectionTrapFilter :  Error while opening SnmpSession in RedirectionTrapFilter.")+" : " + ee,ee);
        }
    }
    
    /** Initialize Snmp session. And gets Conf file reader instance.
     */
    public RedirectionTrapFilter()
    {
        initSnmp();
        confReader = ConfReader.getInstance();
    }

    
    /** Interface method of Trap filter which will for every trap matching
     * the trap filter criteria.
     * From here received trap is forwarded to configured managers.
     */
    public Object applyTrapFilter(SnmpPDU trap_pdu) 
    {
        /** Due to security features V3 traps cannot be  forwarded 
         *  V3 traps are not forwarded and returned for further processing
         * of Web NMS.
         * Whereas V1,V2 and V2c traps are forwarded.
         */
        if(trap_pdu != null && trap_pdu.getVersion() != SnmpAPI.SNMP_VERSION_3)
        {
            try
            {
                Vector managers = null;
                managers = confReader.getMgrList();
                Hashtable props=null;
                for(int j=0;j<managers.size();j++)
                {
                    props=(Hashtable)managers.get(j); 
                    SnmpPDU forwardPDU = getSnmpPDU(trap_pdu);
                    forwardPDU.setRemoteHost(props.get("Dest_Addr").toString());
                    forwardPDU.setRemotePort(Integer.parseInt(props.get("Dest_Port").toString()));   
                    session.send(forwardPDU);

                }
            }
            catch(Exception e)
            {
                NmsLogMgr.EVENTERR.fail(NmsUtil.GetString("Trap Forwarder - RedirectionTrapFilter : Error while forwarding Traps.")+" : " + e,e);
            }
        }
        /** returns the Pdu for further trap Processing.
         */       
        return trap_pdu;    
    }

    private SnmpPDU getSnmpPDU(SnmpPDU pdu) throws Exception 
    {
        SnmpPDU cloneobject= null;
        if (pdu.getVersion() == SnmpAPI.SNMP_VERSION_2 || pdu.getVersion() == SnmpAPI.SNMP_VERSION_2C)
        {
            /** In case of V2c trap  there is no special field to
             *  specify agent name. Source which sends the Trap is
             *  assumed as Agent. 
             *  But in RFC2576-MIB has an OID .1.3.6.1.6.3.18.1.3.0.
             *  using which Agent name can be set for V2c traps. 
             */
            cloneobject=pdu.copy();
            SnmpVarBind varb = cloneobject.getVariableBinding(2);
            boolean addAgentAddress = true;
            /** If the incoming trap does not has  
             * <b>.1.3.6.1.6.3.18.1.3.0.</b> OID in varbind.
             * it will be set by setting Remote host as the agent.
             */
            if(varb != null)
            {
                SnmpOID agentAddrOid = varb.getObjectID();
                SnmpVar agentAddrVal = varb.getVariable();

                if(agentAddrOid != null)
                {
                    if(agentAddrOid.toString().trim().equals(".1.3.6.1.6.3.18.1.3.0"))
                    {
                        /** If the resolved Ip is of 127.0.0.1
                         * Ip of the remote host which has send the 
                         * trap will be set.
                         */
                        if(agentAddrVal != null && isLocalIp(agentAddrVal.toString()))
                        {
                            addAgentAddress  =true;
                            cloneobject.removeVariableBinding(2);
                        }
                        else
                        {
                            addAgentAddress =false;
                        }
                    }
                }
            }
            /** Adds the agent IP varbind.
             */
            if(addAgentAddress)
            {
                String remHost = cloneobject.getRemoteHost();
                if(isLocalIp(remHost))
                {
                    remHost = InetAddress.getLocalHost().getHostAddress();
                }
                SnmpOID oid=new SnmpOID(".1.3.6.1.6.3.18.1.3.0");
                SnmpIpAddress sip =new SnmpIpAddress(remHost);
                SnmpVarBind varbind=new SnmpVarBind(oid,sip);
                cloneobject.addVariableBinding(2,varbind);
            }
            return cloneobject;
        }
        else
        {
            /** V1 trap handling.
             */
            InetAddress inet = pdu.getAgentAddress();
            cloneobject=pdu.copy();
            if(inet != null)
            {
                String ipAddress = inet.getHostAddress();
                /** If the Agent IP is of 127.0.0.1
                 * Ip of the remote host which has send the 
                 * trap will be set.
                 */
                if(isLocalIp(ipAddress))
                {
                    String remHost = cloneobject.getRemoteHost();
                    InetAddress agentInet = null;
                    if(isLocalIp(remHost))
                    {
                        agentInet = InetAddress.getLocalHost();
                    }
                    else
                    {
                        agentInet = InetAddress.getByName(remHost);
                    }
                    cloneobject.setAgentAddress(agentInet);
                } 
            }
            return cloneobject;
        }
    }
    /** Utility method to check whether supplied
     *  argument is of localhost IP i.e. 127.0.0.1
     */
    private boolean isLocalIp(String ip)
    {
        if(ip == null || ip.trim().equals("127.0.0.1"))
        {
            return true;
        }
        else
        {
            return false;
        } 
    }
}








