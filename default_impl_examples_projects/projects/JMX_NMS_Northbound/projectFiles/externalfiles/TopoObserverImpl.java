//$Id: TopoObserverImpl.java,v 1.5 2008/08/05 10:24:48 swaminathap Exp $
package com.adventnet.nms.jmxagent;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.snmp2.agent.*;
import com.adventnet.utils.agent.*;
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

/*
 * The utility class to consurut the varbind vector for all MO related notifications
 */
public class TopoObserverImpl implements TopoActionListener
{

    //Reference to the main file.
    AdventNet_WebNMS_MIB_JMX agentName = null;
	TopoAPI topoAPI=null;

    /**
     *Default constructor.
     */
    public TopoObserverImpl(){}

    // Constructor with main file parameter.
    public TopoObserverImpl(AdventNet_WebNMS_MIB_JMX agentName)
	{
	
		this.agentName = agentName;
	}
		
		
	public void register(TopoAPI api)
    {
		this.topoAPI=api;
		try
		{
		topoAPI.register(this);
		}	
		catch(RemoteException e)
		{
            LogMgr.getLogUser("AGENTERR").fail(NmsUtil.GetString("Remote exception occured while registering as TopoActionListener "),e);//No I18N
		}
    }

    public void update(TopoNotificationEvent evt) throws java.rmi.RemoteException
    {
	Vector varbindVector = null;
	int seqNum = agentName.sequNo;
	String type=evt.getUpdateType();
	ManagedObject obj=evt.getNewManagedObject();

	if(type.equals("Added"))//No I18N
	{
	    //the ManagedObject has been added from the database..
	    //Send a moEnrollNotification.

	    varbindVector = this.constructEnrolNotification(obj,seqNum);
	    this.agentName.moEnrolNotification(varbindVector);
	}
	else if(type.equals("Deleted"))//No I18N
	{
	    //A new ManagedObject has been deleted to the Database...
	    //Send a moDeEnrolNotification.

	    obj=evt.getOldManagedObject();
	    varbindVector = this.constructDeEnrolNotification(obj,seqNum);
	    this.agentName.moDeenrolNotification(varbindVector);
	}
	else if (type.equals("Status Update"))//No I18N
	{
	    //The property of the ManagedObject has changed...
	    //Send a moAttributeChange Notification.

	    varbindVector = this.constructAttrbChangeNotification(obj,seqNum);
	    this.agentName.moAttrChangeNotification(varbindVector);
	}
    }

 
	public void deRegister()
	{
		try
		{
			topoAPI.unregister(this);
		}	
		catch(RemoteException e)
		{
            LogMgr.getLogUser("AGENTERR").fail(NmsUtil.GetString("Remote exception occured while de-registering as TopoActionListener "),e);//No I18N
		}
	}

    /*
     * Constructs the varbind vector for Enrol Notification
     */
    private Vector constructEnrolNotification(ManagedObject obj,int seqNum)
    {

	Vector toReturn = new Vector();
	Properties prop = obj.getUserProperties();
	String extraPropValues = agentName.getPropValues(prop,agentName.moExtraPropNames);

	toReturn.addElement(new SnmpVarBind(new SnmpOID(GenUtils.sequenceNum),new SnmpUnsignedInt(seqNum+1)));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moName),new SnmpString(obj.getName())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moOwnerName),new SnmpString(obj.getOwnerName())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moNodeType),new SnmpString(obj.getType())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moEnrolTime),new SnmpGauge(System.currentTimeMillis())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moExtraProperties),new SnmpString(extraPropValues)));

	return toReturn;
    }

    /*
     * Constructs the varbind vector for DeEnrol Notification
     */
    private Vector constructDeEnrolNotification(ManagedObject obj,int seqNum)
    {

	Vector toReturn = new Vector();
	Properties prop = obj.getUserProperties();
	String extraPropValues = agentName.getPropValues(prop,agentName.moExtraPropNames);

	toReturn.addElement(new SnmpVarBind(new SnmpOID(GenUtils.sequenceNum),new SnmpUnsignedInt(seqNum+1)));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moName),new SnmpString(obj.getName())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moOwnerName),new SnmpString(obj.getOwnerName())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moNodeType),new SnmpString(obj.getType())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moDeEnrolTime),new SnmpGauge(System.currentTimeMillis())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moExtraProperties),new SnmpString(extraPropValues)));
	return toReturn;
    }

    /*
     * Constructs the varbind vector for Attribute Change Notification
     */
    private Vector constructAttrbChangeNotification(ManagedObject obj,int seqNum)
    {

	Vector toReturn = new Vector();
	Properties prop = obj.getUserProperties();
	String extraPropValues = agentName.getPropValues(prop,agentName.moExtraPropNames);

	toReturn.addElement(new SnmpVarBind(new SnmpOID(GenUtils.sequenceNum),new SnmpUnsignedInt(seqNum+1)));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moName),new SnmpString(obj.getName())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moOwnerName),new SnmpString(obj.getOwnerName())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moNodeType),new SnmpString(obj.getType())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moDataChangeTime),new SnmpGauge(obj.getStatusChangeTime())));
	toReturn.addElement(new SnmpVarBind(new SnmpOID(TopoUtils.moExtraProperties),new SnmpString(extraPropValues)));

	return toReturn;
    }

}
