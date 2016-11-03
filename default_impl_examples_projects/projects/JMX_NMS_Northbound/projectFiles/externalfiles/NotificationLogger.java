//$Id: NotificationLogger.java,v 1.3.10.1 2014/06/13 11:48:04 swaminath Exp $
package com.adventnet.nms.jmxagent ;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.snmp2.agent.*;
import com.adventnet.utils.agent.*;
//import com.adventnet.common.agent.*;
import com.adventnet.utilities.common.*;
import com.adventnet.utils.*;

import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.severity.*;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.adventnet.management.transaction.PreparedStatementWrapper;

import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException;

/*
 * The utility class to log all the Notifications sent the NMS Jmx Agent into DataBase
 */
public class NotificationLogger
{

    AdventNet_WebNMS_MIB_JMX agentName = null;

    public NotificationLogger(AdventNet_WebNMS_MIB_JMX agentName)
    {
	this.agentName = agentName;
    }

    /**
     * This method will log the Notifications to the Log table before sending
     * the notifications to the NMS.
     * @param - varbindVector - The varbinds in the notification.
     *		  - OID - OID of notification that is sent to the NMS.
     */
    public synchronized void addNotificationToLog(Vector varbindVector, String OID)
    {

	int index = agentName.sequNo;
	agentName.sequNo++;

	if (agentName.getNotiLogRowCount() >= agentName.maxRows)
	{
	    PreparedStatementWrapper pstat1 = null;
	    PreparedStatementWrapper pstat2 = null;
	    try
	    {
		agentName.begin();
		pstat1 = agentName.rlAPI.fetchPreparedStatement(agentName.psDeleteRowOfNotiLogID);
		PreparedStatement pstatementNoti = pstat1.getPreparedStatement();
		pstat2 = agentName.rlAPI.fetchPreparedStatement(agentName.psDeleteRowOfVarbindLogID);
		PreparedStatement pstatementVar = pstat2.getPreparedStatement();

		//if row count is less than max value then delete the excess rows
		if(agentName.maxRows < agentName.getNotiLogRowCount())
		{
		    int excess =  agentName.getNotiLogRowCount() - agentName.maxRows;
		    for(int i = 0; i < excess ; i++)
		    {
			//deleting the oldest row fron NotiLog and VarBindLog
			pstatementNoti.setInt(1,agentName.startIndex);
			agentName.rlAPI.executeUpdate(pstatementNoti);
			pstatementVar.setInt(1,agentName.startIndex);
			agentName.rlAPI.executeUpdate(pstatementVar);

			if(agentName.startIndex == agentName.intLimit)
			    agentName.startIndex = 1;
			else
			    agentName.startIndex++;
		    }
		}

		//deleting the oldest row fron NotiLog and VarBindLog
		pstatementNoti.setInt(1,agentName.startIndex);
		agentName.rlAPI.executeUpdate(pstatementNoti);
		pstatementVar.setInt(1,agentName.startIndex);
		agentName.rlAPI.executeUpdate(pstatementVar);

		if(agentName.startIndex == agentName.intLimit)
		    agentName.startIndex = 1;
		else
		    agentName.startIndex++;
		//System.err.println("JMX "+ agentName.rlAPI.isInTransaction());
		agentName.commit();

	    }
	    catch(NmsStorageException nse)
	    {
		agentName.agentErr.fail("Storage Excepiton",nse);//No I18N
		try{
			agentName.rollback(nse.getMessage());
		}
		catch(Exception ex)
		{
		}
	    }
	    catch(UserTransactionException use)
	    {
		agentName.agentErr.fail("Excepiton",use);//No I18N	
	    }
	    catch(Exception e)
	    {
		agentName.agentErr.fail("Excepiton",e);//No I18N
		try{
                        agentName.rollback(e.getMessage());
                }
                catch(Exception ex)
                {
                }
	    }
	    finally
	    {
		agentName.rlAPI.returnPreparedStatement(pstat1);
		agentName.rlAPI.returnPreparedStatement(pstat2);
	    }
	}

	if(index == agentName.intLimit)
	{
	    agentName.sequNo = 1;
	    index = 0;
	}

 	int noOfVarbinds = varbindVector.size();
	long logTime = System.currentTimeMillis();

	PreparedStatementWrapper pstat = null;
	try
	{
	    agentName.begin();
	    pstat = agentName.rlAPI.fetchPreparedStatement(agentName.psInsertIntoNotiID);
	    PreparedStatement pstatement = pstat.getPreparedStatement();

	    pstatement.setInt(1,(++index));
	    pstatement.setLong(2,logTime);
	    pstatement.setInt(3,noOfVarbinds);
	    pstatement.setString(4,OID);
	    agentName.rlAPI.executeUpdate(pstatement);
	    agentName.commit();
	}
	catch(NmsStorageException nse)
        {
                agentName.agentErr.fail("Storage Excepiton",nse);//No I18N
                try{
                        agentName.rollback(nse.getMessage());
                }
                catch(Exception ex)
                {
                }
        }
	catch(UserTransactionException use)
        {
                agentName.agentErr.fail("Excepiton",use);//No I18N        
        }
	catch(Exception e)
	{
	    agentName.agentErr.fail("Exception = " , e);//No I18N
	    try{
                        agentName.rollback(e.getMessage());
            }
            catch(Exception ex)
            {
            }
	}
	finally
	{
	    agentName.rlAPI.returnPreparedStatement(pstat);
	}

	addNotificationVarbindsToLogVariableTable(varbindVector,index);
    }

    /** This method is responsible for populating the NotificationslogVariable table
     *  that will have details of the varbinds in the notification sent to the NMS.
     *  @param - varbindVector - The vector of varbinds in the notifications.
     *		   - index - The index in the NotificationLogTable.
     *		   - value - The type of notification sent to the NMS.
     */
    public void addNotificationVarbindsToLogVariableTable(Vector varbindVector, int index)
    {

	for(int i=1;i<=varbindVector.size();i++)
	{
	    int type = 0;
	    String value = null;

	    SnmpVarBind varbind = (SnmpVarBind)varbindVector.elementAt(i-1);
	    SnmpVar var = varbind.getVariable();
	    byte varType = var.getType();

	    if(varType == SnmpAPI.IPADDRESS)
	    {
		type = 5;
		value = var.toString();
	    }
	    else if(varType == SnmpAPI.OBJID)
	    {
		type = 6;
		value = var.toString();
	    }
	    else if(varType == SnmpAPI.STRING)
	    {
		type = 1;
		value = var.toString();
	    }
	    else if(varType == SnmpAPI.TIMETICKS)
	    {
		type = 3;
		value = var.toString();
	    }
	    else if(varType == SnmpAPI.INTEGER)
	    {
		type = 4;
		value = var.toString();
	    }
	    else if(varType == SnmpAPI.GAUGE)
	    {
		type = 2;
		value = var.toString();
	    }

	    PreparedStatementWrapper pstat = null;
	    try
	    {
		agentName.begin();
		pstat = agentName.rlAPI.fetchPreparedStatement(agentName.psInsertIntoVarID);
		PreparedStatement pstatement = pstat.getPreparedStatement();

		pstatement.setInt(1,index);
		pstatement.setInt(2,i);
		pstatement.setInt(3,type);
		pstatement.setString(4,value);
		agentName.rlAPI.executeUpdate(pstatement);
		agentName.commit();
	    }
	    catch(NmsStorageException nse)
            {
                agentName.agentErr.fail("Storage Excepiton",nse);//No I18N
                try{
                        agentName.rollback(nse.getMessage());
                }
                catch(Exception ex)
                {
                }
            }
	    catch(UserTransactionException use)
            {
                agentName.agentErr.fail("Excepiton",use);//No I18N        
            }
	    catch(Exception e)
	    {
		agentName.agentErr.fail("Exception =  " , e);//No I18N
		try{
                        agentName.rollback(e.getMessage());
                }
                catch(Exception ex)
                {
                }
	    }
	    finally
	    {
		agentName.rlAPI.returnPreparedStatement(pstat);
	    }
	}
    }
}






