//$Id: FaultUtils.java,v 1.3 2007/05/01 06:48:00 tinku Exp $
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
//This generally takes care of all traps to or from the fault module
public class FaultUtils
{

    //Fault Noti Varbind OID List.
    static String alertentity = ".1.3.6.1.4.1.2162.4.3.8.3.1";//No I18N
    static String alertownerName = ".1.3.6.1.4.1.2162.4.3.8.3.2";//No I18N
    static String alertDescription = ".1.3.6.1.4.1.2162.4.3.8.3.3"; //No I18N
    static String alertTimeStamp = ".1.3.6.1.4.1.2162.4.3.8.3.4"; //No I18N
    static String alertNotificationId = ".1.3.6.1.4.1.2162.4.3.8.3.5"; //No I18N
    static String alertCategory = ".1.3.6.1.4.1.2162.4.3.8.3.6"; //No I18N
    static String alertExtraProperties  = ".1.3.6.1.4.1.2162.4.3.8.3.7"; //No I18N

    static int CLEAR_ALARM_NOTIFICATION = 0;
    static int WARNING_ALARM_NOTIFICATION = 0;
    static int MINOR_ALARM_NOTIFICATION = 0;
    static int MAJOR_ALARM_NOTIFICATION = 0;
    static int CRITICAL_ALARM_NOTIFICATION = 0;


    /** This method resolves the OID for the type of Fault Notification that has to be sent
     *  to the NMS.
     */
    static String resolveFaultOID(int value)
    {
	if(value == CLEAR_ALARM_NOTIFICATION)
            /** reading the enterprise OID from the main file.
             */
	    return AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.2.1";//No I18N
	if(value == WARNING_ALARM_NOTIFICATION)
	    return AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.2.2";//No I18N
	if(value == MINOR_ALARM_NOTIFICATION)
	    return AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.2.3";//No I18N
	if(value == MAJOR_ALARM_NOTIFICATION)
	    return AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.2.4";//No I18N
	if(value == CRITICAL_ALARM_NOTIFICATION)
	    return AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.2.5";//No I18N
	else
	    return null;
    }

    FaultUtils()
    {
        /** reading the enterprise OID from the main file.
         */
	alertentity = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.3.1";//No I18N
	alertownerName = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.3.2";//No I18N
	alertDescription = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.3.3";//No I18N
	alertTimeStamp = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.3.4";//No I18N
	alertNotificationId = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.3.5";//No I18N
	alertCategory = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.3.6";//No I18N
	alertExtraProperties  = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".3.8.3.7";//No I18N
    }
}
