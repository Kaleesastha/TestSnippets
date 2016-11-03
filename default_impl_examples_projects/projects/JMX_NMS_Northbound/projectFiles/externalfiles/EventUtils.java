//$Id: EventUtils.java,v 1.3 2007/05/01 06:48:00 tinku Exp $
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
//This generally takes care of all traps to or from the performance module
public class EventUtils
{

    //Fault Noti Varbind OID List.
    static String eventId= ".1.3.6.1.4.1.2162.4.4.7.2.1"; //No I18N
    static String eventSource = ".1.3.6.1.4.1.2162.4.4.7.2.2";//No I18N
    static String eventEntity = ".1.3.6.1.4.1.2162.4.4.7.2.3";//No I18N
    static String eventGenTime = ".1.3.6.1.4.1.2162.4.4.7.2.4";//No I18N
    static String eventSeverity = ".1.3.6.1.4.1.2162.4.4.7.2.5";//No I18N

    //Notification OID
    static String thresholdNotification = ".1.3.6.1.4.1.2162.4.4.7.1.1";//No I18N
}
