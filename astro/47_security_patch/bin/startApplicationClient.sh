#!/bin/sh
#$Id: startApplicationClient.sh,v 1.19.2.2 2003/10/30 10:02:52 sunilg Exp $
# This script is to start the Application Client
# set the JAVA_HOME path to jdk.
# if jdk/jre 1.1.x is used, set classpath to swingall.jar also
# refer the documentation if you wish to install standalone client in
# different machine

cd ..
. ./setEnv.sh

#Please do not edit this entry, it will be used by the NarUtilities tool 
NARPATH=:./NetMonitor/build/PerformanceGraphs.jar:./NetMonitor/build/RuntimeConfigFrame.jar:./NetMonitor/build/AuthMain.jar:./NetMonitor/build/ConfigPanel.jar

#Please do not edit this entry, it will be used by the DeploymentWizard tool
CUSTOMPATH=

VULENRABILITY_PATCH=$NMS_HOME/security/security_debug2.jar
CLASSPATH=$VULENRABILITY_PATCH:$NMS_ARC:$SNMP_CLASSPATH:$XML_CLASSPATH:$JIMI_CLASSPATH:$BUILDER_CLASSPATH:$TL1_CLASSPATH:$MS_CLIENT_CLASSPATH:$CLI_CLIENT_CLASSPATH:$CUSTOMPATH:$NARPATH:$NMS_HOME/classes/AdventNetSnmpV3USM.jar:$NMS_CLASSES/ClientExamples.jar:$NMS_CLASSES/AdventNetAboutDialog.jar:$NMS_CLASSES/jnlp.jar:$NMS_CLASSES/AdventNetCCLLibrary.jar:$NMS_CLASSES/AdventNetProBeans.jar:$NMS_CLASSES/AdventNetDMPModels.jar:$NMS_CLASSES/AdventNetNPrevalent.jar:$NMS_CLASSES/AdventNetUpdateManagerInstaller.jar:$SUM_CLIENT_CLASSPATH
export CLASSPATH

echo $CLASSPATH
if [ $# -eq 0 ]
then 
while(true)
do
$JAVA_HOME/bin/java -Xmx200m -Djavax.net.ssl.trustStore=conf/Truststore.truststore -Dcom.sun.management.jmxremote.port=14000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=14500 -Dhttps.protocols=TLSv1 com.adventnet.nms.startclient.WebNMSClient $host $SSL_PORT
#$JAVA_HOME/bin/java -Xmx200m -Dcom.sun.management.jmxremote.port=14000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=14500 com.adventnet.nms.startclient.WebNMSClient $host $WEBSERVER_PORT
if [ $? -eq 126 -o -f Patch/smarttmp.txt ]; then
$JAVA_HOME/bin/java -classpath $UPDATE_MANAGER_CLASSPATH com.adventnet.tools.update.installer.UpdateManager -u conf -h $NMS_HOME -c
if [ $? -ne 0 ]; then 
exit
fi
else
exit 1
fi
done
elif [ $# -eq 1 ]
then
echo "USAGE: sh statrApplicationClient.sh [HOST] [PORT]"
exit1
elif [ $# -eq 2 ]
then
while(true)
do
$JAVA_HOME/bin/java -Xmx200m  -Dcom.sun.management.jmxremote.port=14000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=14500 com.adventnet.nms.startclient.WebNMSClient $1 $2
if [ $? -eq 126 -o -f Patch/smarttmp.txt ]; then
$JAVA_HOME/bin/java -classpath $UPDATE_MANAGER_CLASSPATH com.adventnet.tools.update.installer.UpdateManager -u conf -h $NMS_HOME -c
if [ $? -ne 0 ]; then 
exit
fi
else
exit 1
fi
done
elif [ $# -eq 3 ]
then
while(true)
do
$JAVA_HOME/bin/java -Xmx200m  -Dcom.sun.management.jmxremote.port=14000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=14500 com.adventnet.nms.startclient.WebNMSClient $1 $2 $3
if [ $? -eq 126 -o -f Patch/smarttmp.txt ]; then
$JAVA_HOME/bin/java -classpath $UPDATE_MANAGER_CLASSPATH com.adventnet.tools.update.installer.UpdateManager -u conf -h $NMS_HOME -c
if [ $? -ne 0 ]; then 
exit
fi
else
exit 1
fi
done
elif [ $# -eq 4 ]
then
while(true)
do
$JAVA_HOME/bin/java -Xmx200m  -Dcom.sun.management.jmxremote.port=14000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=14500 com.adventnet.nms.startclient.WebNMSClient $1 $2 $3 $4 
if [ $? -eq 126 -o -f Patch/smarttmp.txt ]; then
$JAVA_HOME/bin/java -classpath $UPDATE_MANAGER_CLASSPATH com.adventnet.tools.update.installer.UpdateManager -u conf -h $NMS_HOME -c
if [ $? -ne 0 ]; then 
exit
fi
else
exit 1
fi
done
elif [ $# -eq 5 ]
then
while(true)
do
$JAVA_HOME/bin/java -Xmx200m  -Dcom.sun.management.jmxremote.port=14000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=14500 com.adventnet.nms.startclient.WebNMSClient $1 $2 $3 $4 $5
if [ $? -eq 126 -o -f Patch/smarttmp.txt ]; then
$JAVA_HOME/bin/java -classpath $UPDATE_MANAGER_CLASSPATH com.adventnet.tools.update.installer.UpdateManager -u conf -h $NMS_HOME -c
if [ $? -ne 0 ]; then 
exit
fi
else
exit 1
fi
done
elif [ $# -gt 5 ]
then
echo "USAGE: sh startApplicationClient.sh [host] [port] [YES/NO] [Language] [Country]"
exit 1
fi


