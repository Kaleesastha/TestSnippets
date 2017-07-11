#!/bin/sh
#$Id: startnms.sh,v 1.18 2001/07/26 07:14:37 srikrishnan Exp $

# This script may need to be modified for your environment.
# For linux and Solaris this may work as is, with the JRE 1.2 provided.
# Else edit the NMS_HOME, JAVA_HOME and DATABASE_DRIVER variables with correct
# full path. Note that the driver to MYSQL database is used in classpath
# settings and you will have to modify based on the database you will be using

cd ..

CLASSPATH=
export CLASSPATH

. ./setEnv.sh

export LD_LIBRARY_PATH

#Please do not edit this entry, it will be used by the DeploymentWizard tool
CUSTOMPATH=

#Please do not remove/edit this entry, it will be used by the MOWizard tool
MOPATH=

cp -f $TOMCAT_HOME/conf/backup/server.xml  $TOMCAT_HOME/conf
cp -f $TOMCAT_HOME/conf/backup/workers.properties $TOMCAT_HOME/conf
#cp -f $WEBSERVER_HOME/ssl/backup/ssl.conf $WEBSERVER_HOME/ssl

VULENRABILITY_PATCH=$NMS_HOME/security/security_debug2.jar
#CLASSPATH variable is changed to CLASS_PATH . If the classpath is set by using #the CLASSPATH variable ,RMI will load the classes from the CLASSPATH and not fr#om the  codebase.
# LICENSE_PATH variable is to locate AdventNetLicense.ali directory path.
CLASSPATH=$VULENRABILITY_PATCH:$NMS_CLASSES/NmsServerClasses.jar:$NMS_CLASSES:$SUM_CLIENT_CLASSPATH:$SUM_SERVER_CLASSPATH:$CUSTOMPATH:$SNMP_CLASSPATH:$SAS_CLASSPATH:$XML_CLASSPATH:$OTHER_CLASSPATH:$MS_CLASSPATH:$WEBSERVER_CLASSPATH:$DB_CLASSPATH:$JAVA_CLASSPATH:$TFTP_CLASSPATH:$CLI_CLASSPATH:$TRANSACTION_CLASSPATH:$NMS_CLASSES/AdventNetSnmpDistributedAPI.jar:$JIMI_CLASSPATH:$MOPATH:$LICENSE_PATH:$FTP_CLASSPATH:$NMS_CLASSES/AdventNetUI.jar:$LOG4J_CLASSPATH:$SSH2_CLASSPATH

# Uncomment the following lines for using TL1 Protocol.
CLASSPATH=$CLASSPATH:$TL1_CLASSPATH

# Uncomment the following lines for using JMX Agent.
CLASSPATH=$CLASSPATH:$JMX_AGENT_CLASSPATH

echo $CLASSPATH
$JAVA_HOME/bin/java -cp $CLASSPATH -Dssl.port=$SSL_PORT -Dtomcatshutdown.port=$TOMCAT_SHUTDOWNPORT -Dwebserver.rootdir=$WEBSERVER_HOME -Dwebnms.rootdir=$NMS_HOME -Dwebcontainer.home=$TOMCAT_HOME -Dwebserver.port=$WEBSERVER_PORT -Dwebcontainer.port=$WEBCONTAINER_PORT com.adventnet.nms.util.InitWebSvr $TOMCAT_HOME/conf/server.xml $TOMCAT_HOME/conf/workers.properties 


# Usage: java  -Dcom.sun.management.jmxremote.port=16000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=16500  com.adventnet.nms.startnms.NmsMainBE [NATIVE_PING true/false] [BE_FE TCP/RMI] [NMS_BE_PORT back_end_port_num]  [COUNTRY country_code] [LANGUAGE language_code] [SERVICE true/false] [USERS_DIR userDir] [ROOT_DIR rootDir]

while(true)
do
#$JAVA_HOME/bin/java -cp $CLASSPATH -Dssl.port=$SSL_PORT -Dcatalina.home=$TOMCAT_HOME -Dmysql.home=$MYSQL_HOME -Dwebserver.port=$WEBSERVER_PORT -Dresource_check="$WEBSERVER_PORT,$WEBCONTAINER_PORT,$TOMCAT_SHUTDOWNPORT" -Dwebserver.rootdir=$WEBSERVER_HOME -Djava.rmi.server.codebase="$CODEBASE_LIST" -mx100m  -Dcom.sun.management.jmxremote.port=16000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=16500 -Dcatalina.base=$TOMCAT_HOME -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager  -Djava.util.logging.config.file=$TOMCAT_HOME/conf/logging.properties -Dnms.mysql.createdb=false com.adventnet.nms.startnms.NmsMainBE NMS_BE_PORT 2000 ROOT_DIR $NMS_HOME $*
$JAVA_HOME/bin/java -cp $CLASSPATH  -Dnms.mysql.createdb=false -Djava.awt.headless=true -Dssl.port=$SSL_PORT -Djavax.net.ssl.trustStore=conf/Truststore.truststore -Dhttps.protocols=TLSv1 -Dcatalina.home=$TOMCAT_HOME -Dmysql.home=$MYSQL_HOME -Dwebserver.port=$SSL_PORT -Dresource_check="$WEBSERVER_PORT,$WEBCONTAINER_PORT,$TOMCAT_SHUTDOWNPORT" -Dwebserver.rootdir=$WEBSERVER_HOME -Djava.rmi.server.codebase1="$CODEBASE_LIST" -mx100m  -Dcom.sun.management.jmxremote.port=16000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=16500  com.adventnet.nms.startnms.NmsMainBE NMS_BE_PORT 2000 ROOT_DIR $NMS_HOME SERVICE true $*
if [ $? -eq 126 -o -f Patch/smarttmp.txt ]; then
$JAVA_HOME/bin/java -cp $UPDATE_MANAGER_CLASSPATH com.adventnet.tools.update.installer.UpdateManager -u conf -h $NMS_HOME -c
if [ $? -ne 0 ]; then
exit
fi
else
exit;
fi
done

