#!/bin/sh
#$Id: startnms.sh,v 1.28 2010/11/29 12:33:10 prabakaran Exp $

# This script may need to be modified for your environment.
# For linux and Solaris this may work as is, with the JRE 1.2 provided.
# Else edit the NMS_HOME, JAVA_HOME and DATABASE_DRIVER variables with correct
# full path. Note that the driver to PGSQL database is used in classpath
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

# cp -f $WEBSERVER_HOME/conf/backup/httpd.conf $WEBSERVER_HOME/conf
cp -f $TOMCAT_HOME/conf/backup/server.xml  $TOMCAT_HOME/conf
cp -f $WEBSERVER_HOME/conf/backup/workers.properties $WEBSERVER_HOME/conf
# cp -f $TOMCAT_HOME/conf/backup/mod_jk.conf-auto $TOMCAT_HOME/conf/mod_jk.conf-nms
# cp -f $WEBSERVER_HOME/bin/backup/* $WEBSERVER_HOME/bin

#CLASSPATH variable is changed to CLASS_PATH . If the classpath is set by using #the CLASSPATH variable ,RMI will load the classes from the CLASSPATH and not fr#om the  codebase.
# LICENSE_PATH variable is to locate AdventNetLicense.ali directory path.

CLASS_PATH=$NMS_SERVER_CLASSES:$NMS_CLASSES:$SUM_CLIENT_CLASSPATH:$SUM_SERVER_CLASSPATH:$CUSTOMPATH:$SNMP_CLASSPATH:$SAS_CLASSPATH:$XML_CLASSPATH:$OTHER_CLASSPATH:$MS_CLASSPATH:$WEBSERVER_CLASSPATH:$DB_CLASSPATH:$JAVA_CLASSPATH:$TFTP_CLASSPATH:$CLI_CLASSPATH:$TRANSACTION_CLASSPATH:$NMS_CLASSES/AdventNetSnmpDistributedAPI.jar:$JIMI_CLASSPATH:$MOPATH:$LICENSE_PATH:$FTP_CLASSPATH:$NMS_CLASSES/AdventNetUI.jar:$NMS_CLASSES/ganymed-ssh2-build210.jar:$NMS_CLASSES/RXTXcomm.jar:$NMS_CLASSES/smslib-3.5.2.jar:$HBN_CLASSPATH:$HBN_LIB_CLASSPATH:$SSH2_CLASSPATH:$JSON_CLASSPATH

# Uncomment the following lines for using TL1 Protocol.
CLASS_PATH=$CLASS_PATH:$TL1_CLASSPATH

# Uncomment the following lines for using JMX Agent.
CLASS_PATH=$CLASS_PATH:$JMX_AGENT_CLASSPATH

#$JAVA_HOME/bin/java -cp $CLASS_PATH -Dtomcatshutdown.port=$TOMCAT_SHUTDOWNPORT -Dwebserver.rootdir=$WEBSERVER_HOME -Dwebnms.rootdir=$NMS_HOME -Dwebcontainer.home=$TOMCAT_HOME -Dwebserver.port=$WEBSERVER_PORT -Dwebcontainer.port=$WEBCONTAINER_PORT com.adventnet.nms.util.InitWebSvr $WEBSERVER_HOME/conf/httpd.conf $TOMCAT_HOME/conf/server.xml  $TOMCAT_HOME/conf/mod_jk.conf-nms $WEBSERVER_HOME/conf/workers.properties $WEBSERVER_HOME/bin/apachectl
#-Dnms.mysql.createdb=false -Dhttps.protocols=TLSv1 -Djavax.net.ssl.trustStore=conf/Truststore.truststore 
$JAVA_HOME/bin/java -cp $CLASS_PATH -Dtomcatshutdown.port=$TOMCAT_SHUTDOWNPORT -Dwebserver.rootdir=$WEBSERVER_HOME -Dwebnms.rootdir=$NMS_HOME -Dwebcontainer.home=$TOMCAT_HOME -Dwebserver.port=$WEBSERVER_PORT -Dssl.port=$SSL_PORT -Dwebcontainer.port=$WEBCONTAINER_PORT com.adventnet.nms.util.InitWebSvr $TOMCAT_HOME/conf/server.xml $WEBSERVER_HOME/conf/workers.properties

# Usage: java com.adventnet.nms.startnms.NmsMainBE [NATIVE_PING true/false] [BE_FE TCP/RMI] [NMS_BE_PORT back_end_port_num]  [COUNTRY country_code] [LANGUAGE language_code] [SERVICE true/false] [USERS_DIR userDir] [ROOT_DIR rootDir]

while(true)
do
$JAVA_HOME/bin/java -cp $CLASS_PATH -Dcatalina.home=$TOMCAT_HOME -Dcatalina.base=$TOMCAT_HOME -Dpgsql.home=$PGSQL_HOME -Dssl.port=$SSL_PORT -Dwebserver.port=$WEBSERVER_PORT -Dresource_check="$WEBSERVER_PORT,$WEBCONTAINER_PORT,$TOMCAT_SHUTDOWNPORT" -Dwebserver.rootdir=$WEBSERVER_HOME -mx100m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseConcMarkSweepGC -Dcom.sun.management.jmxremote.port=16000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=`hostname` -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=16500 -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djava.util.logging.config.file=$TOMCAT_HOME/conf/logging.properties -DConnectionCustomizer=test.ConnectionCustomizerImpl -Djava.awt.headless=true -DONEWAY_ENCRYPTION=true -DPASSWORD_ONEWAY_ENCRYPTION=com.adventnet.security.authentication.PasswordEncryptionImpl  -Dfile.encoding=UTF-8 -Xms128m -Xmx1024m -XX:PermSize=64m -XX:MaxPermSize=512m com.adventnet.nms.startnms.NmsMainBE NMS_BE_PORT 2000 ROOT_DIR $NMS_HOME $* SERVICE TRUE
if [ $? -eq 126 -o -f Patch/smarttmp.txt ]; then
$JAVA_HOME/bin/java -cp $UPDATE_MANAGER_CLASSPATH com.adventnet.tools.update.installer.UpdateManager -u conf -h $NMS_HOME -c
if [ $? -ne 0 ]; then
exit
fi
else
exit;
fi
done
