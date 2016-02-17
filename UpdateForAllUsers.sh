#!/bin/sh
#$Id: UpdateForAllUsers.sh
#This file is to run the UpdateForAllUsers.sh which is used to updateDB or updateXML for the given user or ALL users

usage()
{
echo Usage : UpdateForAllUsers.sh updateDB userName
echo OR
echo Usage : UpdateForAllUsers.sh updateXML userName
echo userName can be individual user OR all
}

if [ $# -lt 2 ] 
then
  usage
fi
cd ../..
. ./setEnv.sh

Category=$1
UserName=$2

CLASSPATH=$NMS_CLASSES/dbXmlUpdate.zip:$NMS_CLASSES/AdventNetTL1.jar:$NMS_CLASSES/ManagementServer.jar:$NMS_SERVER_CLASSES:$NMS_CLASSES:$XML_CLASSPATH:$SNMP_CLASSPATH:$DB_CLASSPATH:$TRANSACTION_CLASSPATH:$NMS_CLASSES/ApiUtils.jar:$NMS_CLASSES/JimiProClasses.jar:$NMS_CLASSES/AdventNetNPrevalent.jar:$HBN_CLASSPATH:$HBN_LIB_CLASSPATH:$NMS_CLASSES/Mail.jar:$JSON_CLASSPATH:$OTHER_CLASSPATH
export CLASSPATH


$JAVA_HOME/bin/java -Dwebnms.rootdir=$NMS_HOME test.DBXmlAllUsers $1 $2
$JAVA_HOME/bin/java -Dwebnms.rootdir=$NMS_HOME test.ReplaceUserName
