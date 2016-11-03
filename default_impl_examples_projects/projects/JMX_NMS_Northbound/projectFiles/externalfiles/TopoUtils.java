//$Id: TopoUtils.java,v 1.2 2007/04/30 17:21:28 tinku Exp $
package com.adventnet.nms.jmxagent;

public class TopoUtils
{

    //Mo notification OID list
    static String moEnrolNotification = ".1.3.6.1.4.1.2162.4.2.14.2.1";//No I18N
    static String moDeenrolNotification = ".1.3.6.1.4.1.2162.4.2.14.2.2";//No I18N
    static String moAttrChangeNotification = ".1.3.6.1.4.1.2162.4.2.14.2.3";//No I18N

    //Mo notification varbind OID list
    static String moName = ".1.3.6.1.4.1.2162.4.2.14.3.1";//No I18N
    static String moOwnerName = ".1.3.6.1.4.1.2162.4.2.14.3.2";//No I18N
    static String moNodeType = ".1.3.6.1.4.1.2162.4.2.14.3.3";//No I18N
    static String moEnrolTime = ".1.3.6.1.4.1.2162.4.2.14.3.4";//No I18N
    static String moDeEnrolTime = ".1.3.6.1.4.1.2162.4.2.14.3.5";//No I18N
    static String moDataChangeTime = ".1.3.6.1.4.1.2162.4.2.14.3.6";//No I18N
    static String moExtraProperties = ".1.3.6.1.4.1.2162.4.2.14.1";//No I18N

    //Variables to identify the Type of ConfigNotifications.
    static int ENROL_NOTIFICATION = 1;
    static int DEENROL_NOTIFICATION = 2;
    static int ATTRB_CHANGE_NOTIFICATION = 3;

    TopoUtils()
    {
	moEnrolNotification = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".2.14.2.1";//No I18N
	moDeenrolNotification = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".2.14.2.2";//No I18N
	moAttrChangeNotification = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".2.14.2.3";//No I18N

	moName = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".2.14.3.1";//No I18N
	moOwnerName = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".2.14.3.2";//No I18N
	moNodeType = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".2.14.3.3";//No I18N
	moEnrolTime = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".2.14.3.4";//No I18N
	moDeEnrolTime = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".2.14.3.5";//No I18N
	moDataChangeTime = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".2.14.3.6";//No I18N
	moExtraProperties = AdventNet_WebNMS_MIB_JMX.enterpriseOID + ".2.14.1";//No I18N
    }
}
