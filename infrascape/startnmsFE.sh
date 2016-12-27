#!/bin/sh
#$Id: startnmsFE.sh,v 1.4 2008/12/22 17:58:15 swaminathap Exp $

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

#cp -f $WEBSERVER_HOME/conf/backup/httpd.conf $WEBSERVER_HOME/conf
#cp -f $WEBSERVER_HOME/bin/backup/apachectl $WEBSERVER_HOME/bin/
cp -f $TOMCAT_HOME/conf/backup/server.xml $TOMCAT_HOME/conf/
cp -f $TOMCAT_HOME/conf/backup/workers.properties $TOMCAT_HOME/conf
#cp -f $TOMCAT_HOME/conf/backup/mod_jk.conf-auto $TOMCAT_HOME/conf/mod_jk.conf-nms

#CLASSPATH variable is changed to CLASS_PATH . If the classpath is set by using #the CLASSPATH variable ,RMI will load the classes from the CLASSPATH and not fr#om the  codebase.

CLASS_PATH=$NMS_CLASSES:$NMS_SERVER_CLASSES:$SUM_CLIENT_CLASSPATH:$SUM_SERVER_CLASSPATH:$CUSTOMPATH:$SNMP_CLASSPATH:$SAS_CLASSPATH:$XML_CLASSPATH:$OTHER_CLASSPATH:$MS_CLASSPATH:$WEBSERVER_CLASSPATH:$DB_CLASSPATH:$JAVA_CLASSPATH:::$TRANSACTION_CLASSPATH:$JIMI_CLASSPATH::$NMS_CLASSES/AdventNetUI.jar:$LOG4J_CLASSPATH:$HBN_CLASSPATH:$HBN_LIB_CLASSPATH:$JSON_CLASSPATH

# Uncomment the following lines for using TL1 Protocol.
# CLASS_PATH=$CLASS_PATH:


$JAVA_HOME/bin/java -cp $CLASS_PATH -Dtomcatshutdown.port=$TOMCAT_SHUTDOWNPORT -Dwebserver.rootdir=$WEBSERVER_HOME -Dwebnms.rootdir=$NMS_HOME -Dwebcontainer.home=$TOMCAT_HOME -Dwebserver.port=$WEBSERVER_PORT -Dwebcontainer.port=$WEBCONTAINER_PORT -Dssl.port=$SSL_PORT com.adventnet.nms.util.InitWebSvr $TOMCAT_HOME/conf/server.xml $TOMCAT_HOME/conf/workers.properties

while(true)
do
$JAVA_HOME/bin/java -Xmx100m -cp $CLASS_PATH -Dcatalina.home=$TOMCAT_HOME -Dresource_check="$WEBSERVER_PORT,$WEBCONTAINER_PORT,$TOMCAT_SHUTDOWNPORT" -Dwebserver.port=$WEBSERVER_PORT -Dssl.port=$SSL_PORT -Dwebserver.rootdir=$WEBSERVER_HOME -Djava.rmi.server.codebase="$CODEBASE_LIST"  -Dcom.sun.management.jmxremote.port=15000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=15500 -Djava.awt.headless=true com.adventnet.nms.startnms.NmsMainFE BE_PORT 2000 BE_HOST 10.208.0.82 ROOT_DIR $NMS_HOME
if [ $? -eq 126 -o -f Patch/smarttmp.txt ]; then
$JAVA_HOME/bin/java -cp $UPDATE_MANAGER_CLASSPATH com.adventnet.tools.update.installer.UpdateManager -u conf -h $NMS_HOME -c
if [ $? -ne 0 ]; then
exit
fi
else
exit;
fi
done

