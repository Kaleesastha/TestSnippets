/*$Id: LinkUpDownTrapFilter.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 */
/** 
 * LinkUpDownTrapFilter.java 
 * Copyright 1999. AdventNet, Inc. All Rights Reserved.
 * Author : G.B.Reddy
 * Changes:
 **/
package test;

import com.adventnet.nms.util.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.*;
import java.util.*;
import java.net.*;
import java.rmi.*;
import java.net.InetAddress;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.SnmpTarget;
import com.adventnet.snmp.mibs.*;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;
import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.severity.*;

/** 
 * This Class is an implementation of TrapFilter specifically designed
 * to treat Link Up and Down Traps. An entry of this class name with 
 * the match criteria is made in the conf/trap.filters configuration file.
 * The raw trap PDU received by the WebNMS Trap Processing Engine 
 * is passed to this class. The PDU is processed and an Event is
 * constructed out of it and returned back to be added by the EventMgr.
 * 
 * @version C_VERSION
 * @author G.B.Reddy
 * @since WebNMS 2.1
 * @see com.adventnet.nms.eventdb.TrapFilter
 **/ 

public class LinkUpDownTrapFilter implements TrapFilter
{
	MibOperations mibOps = null;
	String message = "";
	TopoAPI api;
	EventAPI eapi;
	public LinkUpDownTrapFilter()
	{
		SnmpTarget target = new SnmpTarget();
		mibOps = target.getMibOperations();
		api = getTopoAPI();
		eapi = (EventAPI)NmsUtil.getAPI("EventAPI");
	}

	private String getInterfaceName(String address){

		try{

			Properties pObjectName=new Properties();
			pObjectName.put("isInterface","true");
			pObjectName.put("ipAddress",address);

			Vector vecONames=api.getObjectNamesWithProps(pObjectName);

			if(vecONames!=null && vecONames.size() != 0){
				return  (String)vecONames.elementAt(0);//vecONames.elementAt(0);//api.getInterfaceObjectKey(failedInterfaceIP);
			}
		}
		catch (RemoteException ex)
		{
			NmsLogMgr.EVENTERR.fail(
					NmsUtil.GetString("From LinkUpDownTrapFilter .. Failed to get interface object key : ")+ex, ex);
		}catch(Exception exe){
			exe.printStackTrace();
		}
		return  address;
	}

	/**
	 * The Trap PDU received for LinkUp or LinkDown is passed.
	 */
	static int severityInt = 1;  
	public Object applyTrapFilter(SnmpPDU pdu)
	{
		try 
		{   
			/*Event evt = new Event();  
			evt.setSource( "172.21.168.97" );  
			evt.setEntity( "172.21.168.97" );  
			evt.setText( "Message for 172.21.168.97");  
			evt.setCategory("Test-Category" );  
			evt.setSeverity(severityInt++);  
			if(severityInt > 5){severityInt = 1;}
			if(1==1){
				return evt;
			}*/
			pdu.setAgentAddr("172.21.168.97");
			InetAddress address = InetAddress.getByName("172.21.168.97");
			pdu.setAgentAddress(address);
			UDPProtocolOptions opt = new UDPProtocolOptions(); 
			opt.setRemoteHost("172.21.168.97"); 
			opt.setRemotePort(161); 
			pdu.setProtocolOptions(opt); 
			System.err.println("Trap Receiver :  Agent ADDR : "+pdu.getAgentAddr());
			System.err.println("Trap Receiver : Remote Host : "+pdu.getRemoteHost());
			System.err.println("varbs : "+pdu.printVarBinds());
			System.err.println("==============getVariableBindings==============");
			for (Enumeration e = pdu.getVariableBindings().elements();e.hasMoreElements();)
				System.err.println(((SnmpVarBind) e.nextElement()).toTagString());
			System.err.println("==============getVariableBindings End=========");
		}   
		catch(Exception e)
		{   
			e.printStackTrace();
			System.err.println("error in snmp session");
		}   

		if (pdu.getVersion() == SnmpAPI.SNMP_VERSION_1)
		{
			// if (pdu.getAgentAddress() == null) return pdu;
            String failedInterfaceIP = null;
            if ((failedInterfaceIP = pdu.getAgentAddr()) == null) 
                return pdu;
			if(failedInterfaceIP.trim().equalsIgnoreCase("127.0.0.1"))
			{
				try
				{
					failedInterfaceIP = InetAddress.getLocalHost().getHostAddress();//pdu.getAgentAddress().getLocalHost().getHostAddress();
				}
				catch(java.net.UnknownHostException unknowne)
				{
					System.err.println("Trap from unknown host : " + unknowne);
				}
			}// end if
            if(api == null)
                api = getTopoAPI();

			if (api != null)
			{
				String ifObjectName = null;
				try 
				{
					/* The following call gets the name of the Interface
					   object given the IP Address. */

						ifObjectName = getInterfaceName(failedInterfaceIP);//vecONames.elementAt(0);//api.getInterfaceObjectKey(failedInterfaceIP);
				}
				
				catch (Exception ee)
				{
					NmsLogMgr.EVENTERR.fail(
							NmsUtil.GetString("From LinkUpDownTrapFilter .. Failed to get interface object key : ")+ee, ee);
					return pdu;
				}

				message = "";
				if (ifObjectName == null) // No object with the pdu agent address exists.
				{
                    // For trap received from undiscovered Object, the IP address in String format is set as entity, rather than
                    // going for DNS lookup which will be time costlier.
					ifObjectName = failedInterfaceIP;//pdu.getAgentAddress().getHostName();
					message = "Trap received from Undiscovered object with IP Address : " + ifObjectName + ". ";
				}
				Event evt = getEvent(pdu);
				evt.setEntity(ifObjectName);
				evt.setSource(ifObjectName);
				evt.setNode(doDNSmod(pdu.getRemoteHost()));// since InetAddress.getHostName() is costilier
				//evt.setGroupName(ifObjectName);
				Vector varbinds = pdu.getVariableBindings();
				for (int i = 0; i < varbinds.size(); i++)
				{
					SnmpVarBind varbind = (SnmpVarBind)varbinds.elementAt(i);
					SnmpOID oid = varbind.getObjectID();
					MibNode mnode = mibOps.getMibNode(oid);
					if (mnode != null && mnode.getLabel().equals("ifIndex"))
					{
						if(pdu.getTrapType() == 2)
							message = message + "Interface " + varbind.getVariable() + " down";
						else if(pdu.getTrapType() == 3)
							message = message + "Interface " + varbind.getVariable() + " up";
					}

					else if(mnode == null)
					{
						int index = varbind.toString().lastIndexOf('.');
						String stringVarbind = varbind.toString().trim().substring(0,index);
						if(stringVarbind.equalsIgnoreCase(".1.3.6.1.2.1.2.2.1.1"))
						{
							if(pdu.getTrapType() == 2)
								message = message + "Interface " + varbind.getVariable() + " down";
							else if(pdu.getTrapType() == 3)
								message = message + "Interface " + varbind.getVariable() + " up";
						}

					}
				}
				evt.setText(message);
				return evt;
			}
		}
		else if (pdu.getVersion() == SnmpAPI.SNMP_VERSION_2 ||
				 pdu.getVersion() == SnmpAPI.SNMP_VERSION_2C || 
				 pdu.getVersion() == SnmpAPI.SNMP_VERSION_3)
		{
			if (pdu.getRemoteHost() == null) 
			{
				return pdu;
			}
			String senderIP = pdu.getRemoteHost();
			// if trap received from 'localhost'  converting localhost to corresponding object name - umashankar	
			if(senderIP.trim().equalsIgnoreCase("127.0.0.1"))
			{
				try
				{
					senderIP = InetAddress.getLocalHost().getHostAddress();
				}
				catch(java.net.UnknownHostException unknowne)
				{
					System.err.println("Trap from unknown host : " + unknowne);
				}
			}// end if

			message = "";
			Vector varbinds = pdu.getVariableBindings();
			SnmpVar ifIndex = null, ifAdminStatus = null, ifOperStatus = null;
			for (int i = 0; i < varbinds.size(); i++)
			{ 
				SnmpVarBind varbind = (SnmpVarBind)varbinds.elementAt(i);
				SnmpOID oid = varbind.getObjectID();
				MibNode mnode = mibOps.getMibNode(oid);
				if (mnode != null)
				{
					if ( mnode.getLabel().equals("ifIndex") )
						ifIndex = varbind.getVariable();
					else if ( mnode.getLabel().equals("ifAdminStatus") )
						ifAdminStatus = varbind.getVariable();
					else if ( mnode.getLabel().equals("ifOperStatus") )
						ifOperStatus = varbind.getVariable();
				}
				else
				{
					String stringVarbind = varbind.toString().trim();
					int in = stringVarbind.lastIndexOf('.');
					stringVarbind = stringVarbind.substring(0,in);
					if(stringVarbind.equalsIgnoreCase(".1.3.6.1.2.1.2.2.1.1"))
						ifIndex = varbind.getVariable();
					if(stringVarbind.equalsIgnoreCase(".1.3.6.1.2.1.2.2.1.7"))
						ifAdminStatus = varbind.getVariable();
					if(stringVarbind.equalsIgnoreCase(".1.3.6.1.2.1.2.2.1.8"))
						ifOperStatus = varbind.getVariable();
				}
			} // end for
			 message = message  +" ifAdminStatus = " + ifAdminStatus
			                    + ".  ifOperStatus = " + ifOperStatus 
								+ " Interface " + ifIndex + " ";
            if(api == null)
                 api = getTopoAPI();
			try
			{
				if (api != null)
				{  
					IpAddress ifObj = getInterfaceObject(senderIP); //api.getInterface(senderIP);
					if (ifObj != null)
					{
						if (ifObj instanceof SnmpInterface)
						{
							SnmpInterface snmpInf = (SnmpInterface)ifObj;
							int index = ((Integer)ifIndex.toValue()).intValue();
							if (snmpInf.getIfIndex() == index)
							{
								Event evt = getEvent(pdu);
								evt.setEntity(snmpInf.getName());
								//evt.setGroupName(ifObjectName);
								evt.setSource(snmpInf.getName());
								evt.setNode(doDNSmod(senderIP));
								evt.setText(message);
								return evt;
							}
							else
							{
								SnmpNode parentNode = (SnmpNode)api.getNode(snmpInf.getParentNode());
								Vector ipAddrs = parentNode.getIpaddrs();
								for (int i = 0; i < ipAddrs.size(); i++)
								{
									String ip = (String)ipAddrs.elementAt(i);
									if (ip.equals(snmpInf.getIpAddress()))
									{
										continue;
									}
									else
									{
										SnmpInterface otherInterace = (SnmpInterface)getInterfaceObject(ip);//api.getInterface(ip);
										if (otherInterace.getIfIndex() == index)
										{
											Event evt = getEvent(pdu);
											evt.setEntity(otherInterace.getName());
											evt.setSource(otherInterace.getName());
											evt.setNode(doDNSmod(senderIP));
											evt.setText(message);
											return evt;
										}
									}
								}
							}
						}
						else if ( ifObj instanceof IpAddress )
						{
							Event evt = getEvent(pdu);
							evt.setEntity(ifObj.getName());
							evt.setSource(ifObj.getName());
							evt.setNode(doDNSmod(senderIP));
							evt.setText(message);
							return evt;
						}
					}
					else
					{
						// For trap received from undiscovered Object, the IP address in String format is set as entity, rather than
						// going for DNS lookup which is time costlier.
						String ifObjectName = senderIP;//pdu.getAddress().getHostName();
						message = "Trap received from Undiscovered object with IP Address : " + senderIP + ". " +  message;
						Event evt = getEvent(pdu);
						evt.setEntity(ifObjectName);
						evt.setSource(ifObjectName);
						evt.setNode(ifObjectName);
						evt.setText(message);
						return evt;
					}
				}
			}
			catch(RemoteException ex)
			{
				NmsLogMgr.EVENTERR.fail(
						NmsUtil.GetString("From LinkUpDownTrapFilter : Failed : ")+ex, ex);
				return pdu;
			}
			catch(Exception ee)
			{
				NmsLogMgr.EVENTERR.fail(
						NmsUtil.GetString("From LinkUpDownTrapFilter : Failed : ")+ee, ee);
				return pdu;
			}

		}
		return pdu;
	}

	/** This gets the TopoAPI.
	 **/
	private TopoAPI getTopoAPI() 
	{
		try
            {   
				api =(TopoAPI)NmsUtil.getTopoAPI();
            }
		catch ( Exception e)
            {
                e.printStackTrace();			
            }
		return api;
	} // end  getTopoAPI()

	private Event getEvent(SnmpPDU pdu)
	{
		SeverityIterator iterator = SeverityInfo.getInstance().getIterator();		
		Event evt = new Event();
		evt.setTime((new Date()).getTime());
		evt.setDomain("");
		evt.setNetwork("");
		evt.setCategory("Interface Status");
		if (pdu.getVersion() == SnmpAPI.SNMP_VERSION_1)
		{
			// if Link Down Trap
			if (pdu.getEnterprise().toString().equals(".1.3.6.1.2.1.11")
					&& pdu.getTrapType() == 2 && pdu.getSpecificType() == 0)
			{                
				//evt.setUserProperty("enterprise", ".1.3.6.1.2.1.11");
				//evt.setUserProperty("GT","2");
				//evt.setUserProperty("ST","0");
				iterator.moveToHighest();
				evt.setSeverity(iterator.getCurrent());
				//evt.setSeverity(TopoAPI.CRITICAL);
				//evt.setHelpURL("help/User_Guide/link_down.html");
			}
			else if (pdu.getEnterprise().toString().equals(".1.3.6.1.2.1.11")
					&& pdu.getTrapType() == 3 && pdu.getSpecificType() == 0) // if Link Up Trap
			{                
				//evt.setUserProperty("enterprise", ".1.3.6.1.2.1.11");
				//evt.setUserProperty("GT","3");
				//evt.setUserProperty("ST","0");
				evt.setSeverity(SeverityInfo.getInstance().getClear());
				//evt.setHelpURL("help/User_Guide/link_up.html");
			}
		}
		else if (pdu.getVersion() == SnmpAPI.SNMP_VERSION_2 ||
				 pdu.getVersion() == SnmpAPI.SNMP_VERSION_2C || 
				 pdu.getVersion() == SnmpAPI.SNMP_VERSION_3)
		{
			// if Link Down Trap
			if (pdu.getVariable(1).toString().equals(".1.3.6.1.6.3.1.1.5.3"))
			{
				iterator.moveToHighest();
				evt.setSeverity(iterator.getCurrent());
				//evt.setUserProperty("trapoid", ".1.3.6.1.6.3.1.1.5.3");
				//evt.setHelpURL("help/User_Guide/link_down.html");
				message = message + " down. ";
			}
			else if (pdu.getVariable(1).toString().equals(".1.3.6.1.6.3.1.1.5.4")) // if Link Up Trap
			{
				evt.setSeverity(SeverityInfo.getInstance().getClear());
				//evt.setUserProperty("trapoid", ".1.3.6.1.6.3.1.1.5.4");
				//evt.setHelpURL("help/User_Guide/link_up.html");
				message = message + " Up. ";
			}
			else if(pdu.getVariable(1).toString().equals(".1.3.6.1.4.1.2162.0"))
			{
				iterator.moveToHighest();
				evt.setSeverity(iterator.getPreviousCriticality());
				message = message + " Down. ";
			}
		}

		return evt;
	}

	private IpAddress getInterfaceObject(String address){

		try{

			Properties prop=new Properties();
			prop.put("isInterface","true");
			prop.put("ipAddress",address);
			Vector v=api.getObjects("IpAddress", prop);
			if(v != null && v.size() !=0)
			{
				return ((IpAddress)v.elementAt(0));
			}
		}
		catch (RemoteException ex)
		{
			System.err.println("Exception while using TopoAPI to get Interface Object with IP " + address);
			ex.printStackTrace();
		}
		catch(Exception exe){

			NmsLogMgr.EVENTERR.fail(
					NmsUtil.GetString("From LinkUpDownTrapFilter : Failed : ")+exe, exe);


		}

		return null;
	}

	private String getNodeName(String address){

		try{

		Properties prop=new Properties();
		prop.put("ipAddress",address);
		prop.put("isNode","true");
		Vector v=api.getObjectNamesWithProps(prop);


		if(v!=null && v.size()!=0){

			String o=(String)v.elementAt(0);

			return o;
		}
		 }catch(Exception exe){
		 		exe.printStackTrace();
				NmsLogMgr.EVENTERR.fail("Exception in getNodeName  "+
						" Interface Object with IP " + address + " " + exe, null);
			}
		return address;
	}

	String doDNSmod(String ipaddress)
	{
		// Given the Ip address use the getInterface call and get the 
		// Interface object name corresponding to that address.

		String name = null;
        if(api == null)
            api= getTopoAPI();
		if (api != null)
            { 
                try
				{
				
					return getNodeName(ipaddress);
				}
                
				catch (Exception ee)
                    {
                        System.err.println("Exception while using TopoAPI to get Interface Object with IP " + ipaddress);
                        ee.printStackTrace();
                    }	
            }

        // If the object with the specified Address is not yet discovered, return the Inetaddress 
        // in String format rather than looking for DNS lookup.
        //return (ipaddress.getHostName().toLowerCase().trim());
        return ipaddress;
	}		
}
