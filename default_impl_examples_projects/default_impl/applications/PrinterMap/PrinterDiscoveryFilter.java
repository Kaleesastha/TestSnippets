/* $Id: PrinterDiscoveryFilter.java,v 1.2.6.1 2012/01/25 05:09:27 karen.r Exp $ */

/* 
   PrinterDiscoveryFilter.java 
*/

package test;

import java.util.Properties;
import java.text.MessageFormat;

import com.adventnet.nms.topodb.FoundFilter;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.TopoObject;
import com.adventnet.nms.topodb.SnmpNode;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.TopoDB;

import com.adventnet.snmp.beans.SnmpTarget;
import com.adventnet.snmp.snmp2.SnmpOID;

import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.EventAPI;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.severity.SeverityIterator;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.NmsLogMgr;


import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 

 
public class PrinterDiscoveryFilter implements FoundFilter {

       Printer printerObj = null;


    public ManagedObject filterObject( ManagedObject obj, TopoAPI api ) throws NmsStorageException, UserTransactionException



    {
        if ( obj == null ) 
            return null;
     
        if( !(obj instanceof TopoObject) ) return obj;

		if(!((TopoObject)obj).getIsNode()) 
            return obj;
        
        if(!((TopoObject)obj).getIsSNMP()) 
            return obj;

        String objName = obj.getName();

        SnmpTarget target = new SnmpTarget();
		try
		{

        target.setTargetHost( objName );
        target.setCommunity(((TopoObject)obj).getCommunity());
        target.setAttemptPartial(true);

        if ( !getIsPrinter( objName,target) ) {
            return obj;
        }

        printerObj = new Printer();
        printerObj.setName( objName );

        Properties objProp = ((SnmpNode) obj).getProperties();
        objProp.remove("name");
        objProp.remove("classname");
        objProp.remove("tester");
        objProp.remove("uClass");
        objProp.put("statusPollEnabled","true");
        printerObj.setProperties( objProp ); 

        queryAndSetPropertiesForPrinter( printerObj,target,api) ;

        try {
            if ( api.addObject( printerObj ) ) {
                if ( printerObj.getStatus() != SeverityInfo.getInstance().getClear() )
                    ((TopoDB)api).genEvent("Printer added in major state",printerObj);
            }

        } catch(NmsStorageException nmse) {
            throw nmse;
        } catch(UserTransactionException ute ) {
            throw ute;

        } catch( Exception ex ) {
			Object[] args={objName};
            NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception while trying to add printer object for {0} PrinterDiscoveryFilter "),args), ex);
            return obj;
        }
		}
		finally
		{
			target.releaseResources();

		}
        return printerObj;
    }

    private boolean getIsPrinter( String printerName,SnmpTarget target) {
        target.setSnmpOID(new SnmpOID("43.5.1.1.1.1")); // just a oid to check for printer and nothing more
        String prtGeneralConfigChanges = target.snmpGet();
        try {
            int unUsed = Integer.parseInt(prtGeneralConfigChanges.trim());
        } catch ( NumberFormatException ne ) {
            return false;
        } catch( NullPointerException  npe ) {
            return false;
        }
        return true;
    }

    private boolean queryAndSetPropertiesForPrinter( Printer printerObj,SnmpTarget target, TopoAPI api) {

        String printerName = printerObj.getName();

        SnmpOID hrDeviceStatusOID = new SnmpOID(".1.3.6.1.2.1.25.3.2.1.5.1");
        SnmpOID hrPrinterStatusOID = new SnmpOID(".1.3.6.1.2.1.25.3.5.1.1.1");
        SnmpOID hrPrinterDetectedErrorStatusOID = new SnmpOID(".1.3.6.1.2.1.25.3.5.1.2.1");
        SnmpOID prtConsoleDisplayBufferTextOID = new SnmpOID(".1.3.6.1.2.1.43.16.5.1.2.1.1");

        String result = null;
        try {

            target.setSnmpOID( hrDeviceStatusOID );
            result = target.snmpGet();
            if( result == null || result.equalsIgnoreCase("null") ) {
				Object[] args={printerName,target.getErrorString()};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error getting hrDeviceStatus for {0} in PrinterDiscoveryFilter. Snmp value got is null : {1}"),args), null);
                return false;
            }

            try{
                printerObj.setDeviceStatus(Integer.parseInt(result.trim()));
            }catch(NumberFormatException nfe) 
            {
				Object[] args={printerName};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error setting deviceStatus for {0} in PrinterDiscoveryFilter. "),args), null);
                return false;
            }
            
            target.setSnmpOID( hrPrinterStatusOID );
            result = target.snmpGet();
            if( result == null || result.equalsIgnoreCase("null") ) {
				Object[] args={printerName,target.getErrorString()};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error getting hrPrinterStatus for {0} in PrinterDiscoveryFilter. Snmp value got is null : {1}"),args), null);
                return false;
            }

            try {
                printerObj.setPrinterStatus(Integer.parseInt(result.trim()));
            }catch(NumberFormatException nfe) 
            {
				Object[] args={printerName};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error setting printerStatus for {0} in PrinterDiscoveryFilter. "),args), null);
                return false;
            }
            
            target.setSnmpOID( hrPrinterDetectedErrorStatusOID );
            result = target.snmpGet();
            if( result == null || result.equalsIgnoreCase("null") ) {
				Object[] args={printerName,target.getErrorString()};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error getting hrPrinterDetectedErrorStatus for {0} in PrinterDiscoveryFilter. Snmp value got is null : {1}"),args), null);
                return false;
            } 
            
            try {
                printerObj.setPrinterDetectedErrStatus(Integer.parseInt(result.trim()));
            }catch(NumberFormatException nfe) 
            {
				Object[] args={printerName};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error setting printerDetectedErrStatus for {0} in PrinterDiscoveryFilter. "),args), null);
                //return false;
            }

            target.setSnmpOID( prtConsoleDisplayBufferTextOID );
            result = target.snmpGet();
            if( result == null || result.equalsIgnoreCase("null") ) {
				Object[] args={printerName,target.getErrorString()};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error getting prtConsoleDisplayBufferText for {0} in PrinterDiscoveryFilter. Snmp value got is null : {1}"),args), null);
                return false;
            }
            printerObj.setConsoleDispBufferText(result);

            String consoleString = getConsoleString(target);
            if ( consoleString == null ) {
				Object[] args={printerName,target.getErrorString()};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error getting data for consoleLightData for printer {0} in PrinterDiscoveryFilter. Snmp value got is null : {1}"),args), null);
                return true; // Atleast all others can be made use of and had. Afterall it is rare
            }
            printerObj.setConsoleLightString(consoleString);

            SeverityIterator Iterator = SeverityInfo.getInstance().getIterator();
            
            int prtStatus = printerObj.getPrinterStatus();
            int devStatus = printerObj.getDeviceStatus();
            
            if( ( devStatus == 2 ) && ( ( prtStatus == 3 ) || ( prtStatus == 4 ) ) )
            {
                printerObj.setStatus(SeverityInfo.getInstance().getClear());
            }
            else 
            {
                Iterator.moveToHighest();
                String interfaceKey=api.getInterfaceObjectKey(printerObj.getIpAddress());
                EventAPI eventAPI = (EventAPI)NmsUtil.getAPI("EventAPI");//No I18N
                Event event = new Event();
                event.setSource(interfaceKey);
                event.setEntity(interfaceKey);
                event.setNode(printerObj.getName());
                event.setSeverity(Iterator.getPreviousCriticality());
                event.setTime(System.currentTimeMillis());
                event.setText("Printer is down");//No I18N
                eventAPI.addEvent(event);
//                printerObj.setStatus(Iterator.getPreviousCriticality());
            }
            
        }
        catch ( Exception ee ) {
			Object[] args={printerObj.getName()};
            NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception while getting Snmp values and setting status for printer {0} in PrinterDiscoveryFilter "),args), ee);
            return false;
        }
        return true;
    }

    // This method has been duplicated in PrinterStatusPoller to get console string during status update
    private String getConsoleString(SnmpTarget target) {

        String[] oids = {".1.3.6.1.2.1.43.17.6.1.5", //prtConsoleDescription
                         ".1.3.6.1.2.1.43.17.6.1.4"}; //prtConsoleColor

        String consoleLightData = null;
        
        target.setObjectIDList(null);
        target.setObjectIDList(oids);
        SnmpOID rootoid = target.getSnmpOIDList()[0];
        boolean moreColumns = true;
        boolean first = true;
        
        while( moreColumns ) {
            String[] results=target.snmpGetNextList();
            
            if ( results == null ) {
                return null;
            }
            
            if( !SnmpTarget.isInSubTree( rootoid, target.getSnmpOIDList()[0] ) ) {
                moreColumns = false;
            } else {
                if ( first ) {
                    consoleLightData = results[0] + "=" + results[1];
                    first = false;
                }
                else
                    consoleLightData = consoleLightData + "," + results[0] + "=" + results[1];
            }
        }
        return consoleLightData;
    }
    
}

