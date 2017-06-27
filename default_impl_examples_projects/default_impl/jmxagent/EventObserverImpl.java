//$Id: EventObserverImpl.java,v 1.5 2008/08/05 10:24:48 swaminathap Exp $
package com.adventnet.nms.jmxagent ;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.snmp2.agent.*;
import com.adventnet.utils.agent.*;
//import com.adventnet.common.agent.*;
import com.adventnet.utilities.common.*;
import com.adventnet.utils.*;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.*;

import java.io.*;
import java.util.*;
import java.rmi.RemoteException;

public class EventObserverImpl implements EventObserver
{
    //referencing the agent
    AdventNet_WebNMS_MIB_JMX agentName = null;
    private EventAPI eventAPI=null;

    public EventObserverImpl(){}

    public EventObserverImpl(AdventNet_WebNMS_MIB_JMX agentRef)
	{
		this.agentName = agentRef;
	}
	
	public void register(EventAPI api)
    {
        this.eventAPI=api;
        try
        {
             eventAPI.registerForEvents(this);
        }	
        catch(RemoteException e)
        {
            LogMgr.getLogUser("AGENTERR").fail(NmsUtil.GetString("Remote exception occured while registering as EventObserver"),e);//No I18N
        }
    }

    public void update(Event evt)
    {
	int seqNum = agentName.sequNo;

	String categroy = evt.getCategory();
	if( (categroy != null) && (categroy.equals("Threshold")))
	{
	    agentName.thresholdNotification(constructThresholdEventVarbind(evt,seqNum));
	}
    }

    public void update(Vector eventVector)
    {


	for(int i=0;i<eventVector.size();i++)
	{
	    this.update((Event)(eventVector.elementAt(i)));
	}
    }

    private Vector constructThresholdEventVarbind(Event evt,int seqNum)
    {
	Vector toReturn = new Vector();

	toReturn.addElement(new SnmpVarBind(new SnmpOID(GenUtils.sequenceNum),new SnmpUnsignedInt(++seqNum)));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(EventUtils.eventId),new SnmpString(evt.getEntity())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(EventUtils.eventSource),new SnmpString(evt.getSource())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(EventUtils.eventEntity),new SnmpString(evt.getEntity())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(EventUtils.eventGenTime),new SnmpUnsignedInt(evt.getTime())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(EventUtils.eventSeverity),new SnmpUnsignedInt(evt.getSeverity())));
	return toReturn;
    }

    public void deRegister()
    {
        try
        {
            eventAPI.deregisterForEvents(this);
        }	
        catch(RemoteException e)
        {
            LogMgr.getLogUser("AGENTERR").fail(NmsUtil.GetString("Remote exception occured while de-registering as EventObserver "),e);//No I18N
        }
    }

}//end of the eventObserverImpl program

