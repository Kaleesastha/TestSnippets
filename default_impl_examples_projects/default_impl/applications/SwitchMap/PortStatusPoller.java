/*$Id: PortStatusPoller.java,v 1.9.4.1 2013/07/11 09:24:01 wesley Exp $*/

package test;

import java.util.Vector;
import java.util.Properties;

import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.snmp.snmp2.SnmpVar;
import com.adventnet.snmp.snmp2.SnmpVarBind;

import com.adventnet.snmp.beans.SnmpTarget;

import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.severity.SeverityIterator;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;

import com.adventnet.nms.netwatch.UserTester;

import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.TopoDB;

public class PortStatusPoller implements UserTester {


    /** Implements the interface UserTester for status polling. **/
    public int test(String moname, Properties prop, TopoAPI tapi ) {
		SnmpTarget 	target = new SnmpTarget();
		try
		{
         ManagedObject obj = null;
         try
         {
            obj = tapi.checkOut(moname,((TopoDB)tapi).getLockTimeout(),true,true); 
         }
         catch( Exception ex) {
            NmsLogMgr.TOPOERR.fail("Exception in getting the port object  " + moname,ex); // no i18n
        }
        ManagedObject parent = null;
        try
	{
		if ( !( obj instanceof PortObject ) ) {
			return SeverityInfo.getInstance().getSpecialPurposeSeverity();  // -1
		}
		String parentKey = obj.getParentKey();
		try
		{
			parent = tapi.checkOut( parentKey,0,false,true );
		}
		catch (Exception exe)
		{
			NmsLogMgr.TOPOERR.fail("Exception in PortStatusPoller while getting parent switch for Port " + moname,exe); // no i18n
		}

		if ( parent == null ) {

			obj.setManaged(false);
			tapi.updateObject( obj ,false,true);
			return SeverityInfo.getInstance().getUnknown();
		}
	}
	catch (Exception ex)
	{
		NmsLogMgr.TOPOERR.fail("Exception while tring to update port symbols during status update :", ex ); // no i18n
	}
	finally
	{
		try
		{
			tapi.unlock(obj);
		}
		catch (Exception ex)
		{
			NmsLogMgr.TOPOERR.fail("Exception while unlocking Port Object : "+moname + " ", ex ); // no i18n
		}
	}
        if ( ! ( parent instanceof SwitchObject ) )
            return SeverityInfo.getInstance().getSpecialPurposeSeverity();

        SwitchObject switchObj = (SwitchObject) parent;
        String switchName = switchObj.getName();
        int portNos=switchObj.getNumPorts();
        try {

            //Since SnmpTarget is not thread safe.
            synchronized( target ) {
                
                //Query object for the number of ports.
                target.setTargetHost( switchName );
                target.setCommunity(switchObj.getCommunity());
                //String portIndex = portName.substring( ( portName.lastIndexOf("_Port") + 5 ) );
                return getStatusForPort( (PortObject)obj,target,portNos);
            }
        }
        catch(Exception exp) 
        {
            NmsLogMgr.TOPOERR.fail("Exception in PortStatusPoller while setting portState for : ", exp);
        }
		}
		finally
		{
			target.releaseResources();

		}
        return SeverityInfo.getInstance().getSpecialPurposeSeverity();
    }
    
    private int getStatusForPort( PortObject portObj,SnmpTarget target,int portNos) {

        SnmpOID portState = null;
        
        String portName = portObj.getName();        
        String portIfIndexVal = String.valueOf( portObj.getPortIfIndex() );
        String val = null;
        //PORT STATE FIX START
        String portIndex = portIfIndexVal;
        String oidStr=".1.3.6.1.2.1.17.1.4.1.2"; //no i18n
        target.setSnmpOID(new SnmpOID(oidStr));
        for ( int i = 0; i < portNos; i++ )
        {
            String index= target.snmpGetNext();
            if( index!=null && index.equals(portIfIndexVal))
            {
            	SnmpVarBind varbind = target.snmpGetVariableBinding();
            	if(varbind != null)
            	{
            		SnmpOID snmpoid = varbind.getObjectID();
            		if(snmpoid != null)
            		{
            			String oid=snmpoid.toString();
            			portIndex=oid.substring(oidStr.length()+1);
            			break;
            		}
            	}
            }
        }

        //PORT STATE FIX END

        SeverityIterator sevIter = SeverityInfo.getInstance().getIterator();
        
        try {
            String stateoid = ".1.3.6.1.2.1.17.2.15.1.3." + portIndex; //no i18n
            portState = new SnmpOID( stateoid );
            target.setSnmpOID( portState );
            val = target.snmpGet();
            if ( val == null || val.equalsIgnoreCase("null") ) {
                //NmsLogMgr.TOPOERR.fail("Error getting portState for " + portName + " in PortStatusPoller. Snmp value got is null : " + target.getErrorString(), null);
                sevIter.moveToHighest();
                return sevIter.getPreviousCriticality();  //Major
            }
            int portstatus = Integer.parseInt( val );
            if ( portstatus == 1 )
                portObj.setPortState("disabled");
            else if ( portstatus == 2 )
                portObj.setPortState("blocking");
            else if ( portstatus == 3 )
                portObj.setPortState("listening");
            else if ( portstatus == 4 )
                portObj.setPortState("learning");
            else if ( portstatus == 5 )
                portObj.setPortState("forwarding");
            else if ( portstatus == 6 )
                portObj.setPortState("broken");
            else
                portObj.setPortState("unknown");
          
            if( portstatus == 3 || portstatus == 4 || portstatus == 5 ) {
                return SeverityInfo.getInstance().getClear();
            }
            else if ( portstatus == 6 ) {
                if( sevIter.moveToHighest() ) {
                    return sevIter.getCurrent();
                }
            }
            else if ( portstatus == 1 || portstatus == 2 ) {
                if( sevIter.moveToHighest() ) {
                        return sevIter.getPreviousCriticality();
                }
            }
        }
        catch ( Exception ex ) {
            NmsLogMgr.TOPOERR.fail("Exception in PortStatusPoller while setting portState for : " + portName, ex);
        }
        return SeverityInfo.getInstance().getSpecialPurposeSeverity();
    }

}
