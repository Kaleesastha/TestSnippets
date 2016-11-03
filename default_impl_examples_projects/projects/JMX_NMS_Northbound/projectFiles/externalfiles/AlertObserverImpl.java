//$Id: AlertObserverImpl.java,v 1.5 2008/08/05 10:24:47 swaminathap Exp $
package com.adventnet.nms.jmxagent ;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.snmp2.agent.*;
import com.adventnet.utils.agent.*;
//import com.adventnet.common.agent.*;
import com.adventnet.utilities.common.*;
import com.adventnet.utils.*;

import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.*;

import java.io.*;
import java.util.*;
import java.rmi.RemoteException;

public class AlertObserverImpl implements AlertListener
{
    //referencing the agent
    AdventNet_WebNMS_MIB_JMX agentName = null;
    private AlertAPI alertAPI=null;

    public AlertObserverImpl(){}

    public AlertObserverImpl(AdventNet_WebNMS_MIB_JMX agentRef)
    {
	this.agentName = agentRef;

	}

	public void register(AlertAPI api)
	{
        this.alertAPI=api;

        try
        {
            alertAPI.addAlertListener(this);
        }	
        catch(RemoteException e)
        {
            LogMgr.getLogUser("AGENTERR").fail(NmsUtil.GetString("Remote exception occured while registering as AlertListener "),e);//No I18N
        }

    }

    private void update(Alert alert)
    {
	SeverityInfo sevInfo = SeverityInfo.getInstance();
	String oid = null;
	int value = 0;
	int seqNum = agentName.sequNo;

	try
	{
	    //check for the !deleted alerts
	    if(alert.getSeverity() != sevInfo.getSpecialPurposeSeverity())
	    {
		value = alert.getSeverity();
		Vector varbindVector = constructFaultVarbindVector(alert,value,seqNum);
		this.sendTrap(varbindVector,value);
	    }
	}
	catch(Exception e)
	{
	    System.out.println(" Exception in AlertObserverImpl: " + e); //No I18N
	}
    }

    private void update(Vector alertVector)
    {

        int size=alertVector.size();
	for(int i=0;i<size;i++)
	{
	    this.update((Alert)(alertVector.elementAt(i)));
	}
    }

    //implementation for AlertListener
    public void listenAlert(AlertActionInformer action)
    {

        if(action.isBatchUpdate())
        {

           update(action.getAlertList());
        }
        else
        {
            update(action.getAlert());
        }

    }

    //implementation for AlertListener
    public void update(XMLNode xmlNode)
    {
        //TODO: implementaion for bulk delete need to be provided
    }

    private Vector constructFaultVarbindVector(Alert alert,int value,int seqNum)
    {
	Vector toReturn = null;

	if(value == FaultUtils.CLEAR_ALARM_NOTIFICATION)
	{
	    toReturn = constructClearAlarmVarbind(alert,seqNum);
	}
	else if(value == FaultUtils.WARNING_ALARM_NOTIFICATION)
	{
	    toReturn = constructWarningAlarmVarbind(alert,seqNum);
	}
	else if(value == FaultUtils.MINOR_ALARM_NOTIFICATION)
	{
	    toReturn = constructMinorAlarmVarbind(alert,seqNum);
	}
	else if(value == FaultUtils.MAJOR_ALARM_NOTIFICATION)
	{
	    toReturn = constructMajorAlarmVarbind(alert,seqNum);
	}
	else if(value == FaultUtils.CRITICAL_ALARM_NOTIFICATION)
	{
	    toReturn = constructCriticalAlarmVarbind(alert,seqNum);
	}

	return toReturn;

    }

    private void sendTrap(Vector varbindVector,int value)
    {
	if(value == FaultUtils.CLEAR_ALARM_NOTIFICATION)
	{
	    agentName.alertClearNotification(varbindVector);
	}
	else if(value == FaultUtils.WARNING_ALARM_NOTIFICATION)
	{
	    agentName.alertWarningNotification(varbindVector);
	}
	else if(value == FaultUtils.MINOR_ALARM_NOTIFICATION)
	{
	    agentName.alertMinorNotification(varbindVector);
	}
	else if(value == FaultUtils.MAJOR_ALARM_NOTIFICATION)
	{
	    agentName.alertMajorNotification(varbindVector);
	}
	else if(value == FaultUtils.CRITICAL_ALARM_NOTIFICATION)
	{
	    agentName.alertCriticalNotification(varbindVector);
	}
    }

    private Vector constructClearAlarmVarbind(Alert alert, int seqNum)
    {
	Vector toReturn = new Vector();
	Properties prop = alert.getUserProperties();
	String propValues = agentName.getPropValues(prop,agentName.alertExtraPropNames);

	//sequenceNum ,alertentity,alertownerName,alertDescription ,alertTimeStamp ,alertCategory ,alertNotificationId ,alertExtraProperties
        //changed to entity and ownername

	toReturn.addElement(new SnmpVarBind(new SnmpOID(GenUtils.sequenceNum),new SnmpInt(++seqNum)));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertentity),new SnmpString(alert.getEntity())));
        toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertownerName),new SnmpString(alert.getOwnerName())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertDescription),new SnmpString(alert.getMessage())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertTimeStamp),new SnmpTimeticks(alert.getModTime())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertCategory),new SnmpString(alert.getCategory())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertNotificationId),new SnmpInt(alert.getId())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertExtraProperties),new SnmpString(propValues)));
	return toReturn;
    }


    private Vector constructWarningAlarmVarbind(Alert alert, int seqNum)
    {
	Vector toReturn = new Vector();
	Properties prop = alert.getUserProperties();
	String propValues = agentName.getPropValues(prop,agentName.alertExtraPropNames);

	//alertCategory ,alertentity,alertownerName,alertDescription ,sequenceNum ,alertTimeStamp ,alertNotificationId ,alertExtraProperties

	toReturn.addElement(new SnmpVarBind(new SnmpOID(GenUtils.sequenceNum),new SnmpUnsignedInt(++seqNum)));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertentity),new SnmpString(alert.getEntity())));
        toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertownerName),new SnmpString(alert.getOwnerName())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertCategory),new SnmpString(alert.getCategory())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertDescription),new SnmpString(alert.getMessage())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertTimeStamp),new SnmpUnsignedInt(alert.getModTime())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertNotificationId),new SnmpInt(alert.getId())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertExtraProperties),new SnmpString(propValues)));

	return toReturn;
    }


    private Vector constructMinorAlarmVarbind(Alert alert, int seqNum)
    {
	Vector toReturn = new Vector();
	Properties prop = alert.getUserProperties();
	String propValues = agentName.getPropValues(prop,agentName.alertExtraPropNames);

	/*sequenceNum ,alertCategory ,alertentity,alertownerName,alertDescription ,alertTimeStamp ,alertNotificationId ,alertExtraProperties */


    toReturn.addElement(new SnmpVarBind(new SnmpOID(GenUtils.sequenceNum),new SnmpUnsignedInt(++seqNum)));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertentity),new SnmpString(alert.getEntity())));
        toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertownerName),new SnmpString(alert.getOwnerName())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertCategory),new SnmpString(alert.getCategory())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertNotificationId),new SnmpInt(alert.getId())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertDescription),new SnmpString(alert.getMessage())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertTimeStamp),new SnmpUnsignedInt(alert.getModTime())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertExtraProperties),new SnmpString(propValues)));

	return toReturn;
    }

    /**
     *This method constructs the Varbind Vector for Major Alarm Notification
     */
    private Vector constructMajorAlarmVarbind(Alert alert, int seqNum)
    {
	Vector toReturn = new Vector();
	Properties prop = alert.getUserProperties();
	String propValues = agentName.getPropValues(prop,agentName.alertExtraPropNames);

	/*sequenceNum,alertentity,alertownerName,alertCategory,alertDescription ,alertTimeStamp ,alertNotificationId ,alertExtraProperties */

	toReturn.addElement(new SnmpVarBind(new SnmpOID(GenUtils.sequenceNum),new SnmpUnsignedInt(++seqNum)));
        toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertentity),new SnmpString(alert.getEntity())));
        toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertownerName),new SnmpString(alert.getOwnerName())));
        toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertCategory),new SnmpString(alert.getCategory())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertNotificationId),new SnmpInt(alert.getId())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertDescription),new SnmpString(alert.getMessage())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertTimeStamp),new SnmpUnsignedInt(alert.getModTime())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertExtraProperties),new SnmpString(propValues)));

	return toReturn;
    }

    /**
     *This method constructs the Varbind Vector for critical Alarm Notification
     */
    private Vector constructCriticalAlarmVarbind(Alert alert, int seqNum)
    {
	Vector toReturn = new Vector();
	Properties prop = alert.getUserProperties();
	String propVales = agentName.getPropValues(prop,agentName.alertExtraPropNames);

	/*sequenceNum ,alertCategory ,alertentity,alertownerName,alertDescription ,alertTimeStamp ,alertNotificationId ,alertExtraProperties */

	toReturn.addElement(new SnmpVarBind(new SnmpOID(GenUtils.sequenceNum),new SnmpUnsignedInt(++seqNum)));
        toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertentity),new SnmpString(alert.getEntity())));
        toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertownerName),new SnmpString(alert.getOwnerName())));

	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertCategory),new SnmpString(alert.getCategory())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertNotificationId),new SnmpInt(alert.getId())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertDescription),new SnmpString(alert.getMessage())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertTimeStamp),new SnmpUnsignedInt(alert.getModTime())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(FaultUtils.alertExtraProperties),new SnmpString(propVales)));

	return toReturn;
    }

    public void deRegister()
    {

        try
        {
            alertAPI.removeAlertListener(this);
        }	
        catch(RemoteException e)
        {
            LogMgr.getLogUser("AGENTERR").fail(NmsUtil.GetString("Remote exception occured while de-registering as AlertListener "),e);//No I18N
        }
    }
}
