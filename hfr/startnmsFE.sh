#!/bin/sh
#$Id$

# This script may need to be modified for your environment
# For Linux and Solaris this may work as is, with the JRE 1.2 provided
# Else edit the NMS_HOME, JAVA_HOME and DATABASE_DRIVER variables with correct
# full path. Note that the driver to MYSQL database is used in classpath
# settings and you will have to modify based on the database you will be using

cd ..

CLASSPATH=
export CLASSPATH
CUSTOMPATH=
export CUSTOMPATH

. ./setEnv.sh

export LD_LIBRARY_PATH
MOPATH=./NetMonitor/build/HFR_Project_Server.jar
#cp -f $WEBSERVER_HOME/conf/backup/httpd.conf $WEBSERVER_HOME/conf
#cp -f $WEBSERVER_HOME/bin/backup/apachectl $WEBSERVER_HOME/bin/
cp -f $TOMCAT_HOME/conf/backup/server.xml $TOMCAT_HOME/conf/
#cp -f $TOMCAT_HOME/conf/backup/workers.properties $TOMCAT_HOME/conf
#cp -f $TOMCAT_HOME/conf/backup/mod_jk.conf-auto $TOMCAT_HOME/conf/mod_jk.conf-nms

#CLASSPATH variable is changed to CLASS_PATH . If the classpath is set by using #the CLASSPATH variable ,RMI will load the classes from the CLASSPATH and not fr#om the  codebase.

#CLASS_PATH=$MOPATH:$NMS_CLASSES/CommonUtils.jar:$NMS_CLASSES/CarrierEthernet_Solutions.jar:$NMS_CLASSES:$NMS_SERVER_CLASSES:$SUM_CLIENT_CLASSPATH:$SUM_SERVER_CLASSPATH:$CUSTOMPATH:$SNMP_CLASSPATH:$SAS_CLASSPATH:$XML_CLASSPATH:$OTHER_CLASSPATH:$MS_CLASSPATH:$WEBSERVER_CLASSPATH:$DB_CLASSPATH:$JAVA_CLASSPATH:$TFTP_CLASSPATH:$CLI_CLASSPATH:$TRANSACTION_CLASSPATH:$JIMI_CLASSPATH:$TL1_CLASSPATH:$NMS_CLASSES/AdventNetUI.jar:$NMS_CLASSES/ganymed-ssh2-build210.jar:$LOG4J_CLASSPATH:$HBN_CLASSPATH:$HBN_LIB_CLASSPATH:$JSON_CLASSPATH

CLASS_PATH=$NMS_CLASSES/provisioning_debug.zip:$MOPATH:$NMS_CLASSES/mina-core-2.0.0-M3.jar:$NMS_CLASSES/slf4j-api-1.5.6.jar:$NMS_CLASSES/slf4j-simple-1.5.6.jar:$NMS_CLASSES/ftplet-api-1.0.6.jar:$NMS_CLASSES/ftpserver-core-1.0.6.jar:$NMS_CLASSES/CommonUtils.jar:$NMS_CLASSES/CarrierEthernet_Solutions.jar:$NMS_SERVER_CLASSES:$NMS_CLASSES:$SUM_CLIENT_CLASSPATH:$SUM_SERVER_CLASSPATH:$CUSTOMPATH:$SNMP_CLASSPATH:$SAS_CLASSPATH:$XML_CLASSPATH:$OTHER_CLASSPATH:$MS_CLASSPATH:$WEBSERVER_CLASSPATH:$DB_CLASSPATH:$JAVA_CLASSPATH:$TFTP_CLASSPATH:$CLI_CLASSPATH:$TRANSACTION_CLASSPATH:$NMS_CLASSES/AdventNetSnmpDistributedAPI.jar:$JIMI_CLASSPATH:$MOPATH:$LICENSE_PATH:$FTP_CLASSPATH:$NMS_CLASSES/AdventNetUI.jar:$NMS_CLASSES/ganymed-ssh2-build210.jar:$NMS_CLASSES/RXTXcomm.jar:$NMS_CLASSES/smslib-3.5.2.jar:$HBN_CLASSPATH:$HBN_LIB_CLASSPATH:$SSH2_CLASSPATH:$JSON_CLASSPATH

# Uncomment the following lines for using TL1 Protocol.
CLASS_PATH=$CLASS_PATH:$TL1_CLASSPATH


$JAVA_HOME/bin/java -cp $CLASS_PATH -Dtomcatshutdown.port=$TOMCAT_SHUTDOWNPORT -Dwebserver.rootdir=$WEBSERVER_HOME -Dwebnms.rootdir=$NMS_HOME -Dwebcontainer.home=$TOMCAT_HOME -Dwebserver.port=$WEBSERVER_PORT -Dwebcontainer.port=$WEBCONTAINER_PORT -Dssl.port=$SSL_PORT com.adventnet.nms.util.InitWebSvr $TOMCAT_HOME/conf/server.xml

while(true)
do
$JAVA_HOME/bin/java -cp $CLASS_PATH -Dcatalina.home=$TOMCAT_HOME -Dresource_check="$WEBSERVER_PORT,$WEBCONTAINER_PORT,$TOMCAT_SHUTDOWNPORT" -Dwebserver.port=$WEBSERVER_PORT -Dssl.port=$SSL_PORT -Dwebserver.rootdir=$WEBSERVER_HOME -Djava.awt.headless=true  -Xms1024m -Xmx2048m -XX:PermSize=512m -XX:MaxPermSize=2048m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseConcMarkSweepGC -Dcom.sun.management.jmxremote.port=15000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=15500  com.adventnet.nms.startnms.NmsMainFE BE_PORT 2000 BE_HOST 10.24.66.1 ROOT_DIR $NMS_HOME
if [ $? -eq 126 -o -f Patch/smarttmp.txt ]; then
$JAVA_HOME/bin/java -cp $UPDATE_MANAGER_CLASSPATH com.adventnet.tools.update.installer.UpdateManager -u conf -h $NMS_HOME -c
if [ $? -ne 0 ]; then
exit
fi
else
exit;
fi
done

