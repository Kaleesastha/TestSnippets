//$Id: GenUtils.java,v 1.3 2007/05/01 06:48:00 tinku Exp $
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


//This is generally for the NotificationSequence number for all traps
public class GenUtils
{

    static String sequenceNum = ".1.3.6.1.4.1.2162.4.5.2";//No I18N

    GenUtils()
    {
	sequenceNum = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".5.2";//No I18N
    }
}

