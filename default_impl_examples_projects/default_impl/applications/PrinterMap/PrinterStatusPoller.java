/* $Id: PrinterStatusPoller.java,v 1.10 2008/09/15 10:50:51 barathv Exp $ */

/* PrinterStatusPoller.java */

package test;

import java.util.Vector;
import java.util.Properties;
import java.util.StringTokenizer;

import com.adventnet.snmp.snmp2.SnmpOID;

import com.adventnet.snmp.beans.SnmpTarget;

import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.severity.SeverityIterator;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;

import com.adventnet.nms.netwatch.UserTester;

import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.IpAddress;
import com.adventnet.nms.topodb.TopoObject;
import com.adventnet.nms.topodb.TopoDB;
import com.adventnet.nms.topodb.TopoAPI;

public class PrinterStatusPoller implements UserTester {


    /** Implements the interface UserTester for status polling. **/
    public int test(String moname,Properties prop, TopoAPI tapi ) 
    {
            ManagedObject obj = null;
	    SnmpTarget target = null;
        try
	{ 
		target = new SnmpTarget();
         try
         {
		obj = tapi.checkOut(moname,((TopoDB)tapi).getLockTimeout(),true,true); 	
         }
         catch( Exception ex) {
            NmsLogMgr.TOPOERR.fail("Exception in getting the printer object  " + moname,ex); // no i18n
        }
        if ( !( obj instanceof Printer ) ) {
            return SeverityInfo.getInstance().getSpecialPurposeSeverity();  // -1
        }
        int clear = SeverityInfo.getInstance().getClear();
        int unknown = SeverityInfo.getInstance().getUnknown();

        //String ipAddress = ((TopoObject)obj).getIpAddress();
        //String ifStatus = ( String ) ( ( TopoDB )tapi ).childrenStatus.getProperty( "IF-"+ipAddress );

        //int statusVal = clear;
        //if ( ifStatus != null )
        //    statusVal = Integer.parseInt( ifStatus );
        try {
            String ipAddress = ((TopoObject)obj).getIpAddress();
            IpAddress interfaceObj = tapi.getInterface( ipAddress );
            if ( interfaceObj != null ) { 
                int status = interfaceObj.getStatus();
                if ( status != clear ) {
                    if ( status == unknown ) {
                        updatePrinterObject( ( Printer )obj, tapi, true );
                        return SeverityInfo.getInstance().getSpecialPurposeSeverity();
                    }
                    else {
                        updatePrinterObject( ( Printer )obj, tapi, false );
                        return status;
                    }
                }
            }
        } catch ( Exception ee ) {
            NmsLogMgr.TOPOERR.fail("Exception checking status of interface of Printer : " + obj, ee);
        }

        String printerName = obj.getName();
        String value = null;

        int deviceStatus, printerStatus, prtErrStatus;
        deviceStatus = printerStatus = 0;
        prtErrStatus = -1;
        //SnmpTarget target = new SnmpTarget();
        SeverityIterator sevIter = SeverityInfo.getInstance().getIterator();

        SnmpOID deviceStatusOID = new SnmpOID(".1.3.6.1.2.1.25.3.2.1.5.1");
        SnmpOID printerStatusOID = new SnmpOID(".1.3.6.1.2.1.25.3.5.1.1.1");
        SnmpOID printerDetectedErrorStatusOID = new SnmpOID(".1.3.6.1.2.1.25.3.5.1.2.1");
        SnmpOID prtConsoleDisplayBufferTextOID = new SnmpOID(".1.3.6.1.2.1.43.16.5.1.2.1.1");

        target.setAttemptPartial(true);
        target.setTargetHost(printerName);
        target.setCommunity(((TopoObject)obj).getCommunity());

        target.setSnmpOID(deviceStatusOID); 
        value = target.snmpGet();
        if ( value == null || value.equalsIgnoreCase("null") ) {
            updatePrinterObject( ( Printer )obj, tapi, false );
            if( sevIter.moveToHighest() ) 
            {
                return sevIter.getPreviousCriticality(); // major
            }
        }

        try {
            deviceStatus = Integer.parseInt(value);
        }catch(Exception exp) 
        { 
            NmsLogMgr.TOPOERR.fail("Error getting SnmpValue from printer device status :" + printerName + " " + exp.getMessage(),null);
            return SeverityInfo.getInstance().getSpecialPurposeSeverity(); 
        }
        ((Printer)obj).setDeviceStatus(deviceStatus);

        target.setSnmpOID(printerStatusOID); 
        value = target.snmpGet();
        if ( value == null || value.equalsIgnoreCase("null") ) {
            updatePrinterObject( ( Printer )obj, tapi, false );
            if( sevIter.moveToHighest() ) 
            {
                return sevIter.getPreviousCriticality(); // major
            }
        }

        try {
            printerStatus = Integer.parseInt(value);
        }catch(Exception exp) 
        { 
            NmsLogMgr.TOPOERR.fail("Error getting SnmpValue from printer for printerstatus :" + printerName + " " + exp.getMessage(),null);
            return SeverityInfo.getInstance().getSpecialPurposeSeverity(); 
        }
        ((Printer)obj).setPrinterStatus(printerStatus);
        
        target.setSnmpOID(printerDetectedErrorStatusOID);
        value = target.snmpGet();
        if ( value == null || value.equalsIgnoreCase("null") ) {
            updatePrinterObject( ( Printer )obj, tapi, false );
            if( sevIter.moveToHighest() ) 
            {
                return sevIter.getPreviousCriticality(); // major
            }
        }
        
        if ( value.trim().length() != 0 ) {
            try {
                prtErrStatus = Integer.parseInt(value.trim(),16);
            }catch(Exception exp) 
            {
                NmsLogMgr.TOPOERR.fail("Error getting SnmpValue from printer printerdetectederorstatus  :" + printerName + " " + exp.getMessage(),null);
                //return SeverityInfo.getInstance().getSpecialPurposeSeverity(); 
            }
        }
        ((Printer)obj).setPrinterDetectedErrStatus(prtErrStatus);

        target.setSnmpOID( prtConsoleDisplayBufferTextOID );
        value = target.snmpGet();
        if ( value == null || value.equalsIgnoreCase("null") ) {
            updatePrinterObject( ( Printer )obj, tapi, false );
            if( sevIter.moveToHighest() ) 
            {
                return sevIter.getPreviousCriticality(); // major
            }
        }
        ((Printer)obj).setConsoleDispBufferText( value );

        String consoleString = getConsoleString(target);
        if ( consoleString == null ) {
            updatePrinterObject( ( Printer )obj, tapi, false );
            if( sevIter.moveToHighest() ) 
            {
                return sevIter.getPreviousCriticality(); // major
            }
        }
        ((Printer)obj).setConsoleLightString(consoleString);


        try {
            tapi.updateObject(obj,false,true);
        }
        catch(Exception exp)
        {
            NmsLogMgr.TOPOERR.fail("Exception when updating printer object :" + printerName,exp);
        }


        if(deviceStatus==2 && (printerStatus==4 || printerStatus==3)) // Printing Or Ready
        {
            return SeverityInfo.getInstance().getClear();
        }
        else
        {
            if( sevIter.moveToHighest() ) 
            {
                return sevIter.getPreviousCriticality(); // major
            }
        }
        return SeverityInfo.getInstance().getSpecialPurposeSeverity();
    }
    finally
    {
	    target.releaseResources();
	    try
	    {
		    tapi.unlock(obj);
	    }
	    catch (Exception exe)
	    {
		    NmsLogMgr.TOPOERR.fail("Exception while unlocking ManagedObject : "+obj.getName() + " ", exe ); // no i18n
	    }
    }
    }

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

    private void updatePrinterObject( Printer printerObj, TopoAPI tapi, boolean setUnknownStatus ) {

        if ( printerObj == null )
            return;

        printerObj.setPrinterStatus(-1);
        printerObj.setDeviceStatus(-1);
        printerObj.setPrinterDetectedErrStatus(-1);
        printerObj.setConsoleDispBufferText("UNKNOWN");
        String consoleLightString = printerObj.getConsoleLightString();//NO I18N
        if ( consoleLightString != null ) {
            StringTokenizer stok = new StringTokenizer( consoleLightString, ",");
            StringBuffer sb = new StringBuffer();
            int numTokens = stok.countTokens();
            for ( int z = 1; z <= numTokens; z++ ) {
                String token = stok.nextToken();
                sb.append( token.substring( 0, token.indexOf("=") + 1 ) );
                sb.append("2,");
            }
            String result = sb.toString();
            printerObj.setConsoleLightString(result.substring(0,result.length() - 1));
        }

        if ( setUnknownStatus ) {
            printerObj.setStatus( SeverityInfo.getInstance().getUnknown() );
            printerObj.setManaged(false);
        }

        try {
            tapi.updateObject( printerObj,false,true );
        }
        catch ( Exception ex ) {
            NmsLogMgr.TOPOERR.fail("Exception while updating Printer : " + printerObj, ex);
        }
    }

}




