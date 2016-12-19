cd ..
. ./setEnv.sh

MODE_VALUE=$*

MOPATH=$NMS_HOME/NetMonitor/build/EMS_Tutorial_Client.jar:$NMS_HOME/NetMonitor/build/RelationalClasses_Server.jar
OTHER_CLASSPATH=./NetMonitor/build/EMS_Tutorial_Client.jar:$OTHER_CLASSPATH
CLASSPATH=$MOPATH:$NMS_SERVER_CLASSES:$NMS_CLASSES:$XML_CLASSPATH:$DB_CLASSPATH:$NMS_CLASSES/ApiUtils.jar:$HBN_CLASSPATH:$TRANSACTION_CLASSPATH:$HBN_LIB_CLASSPATH:$SNMP_CLASSPATH:$NMS_CLASSES/Mail.jar:$MS_CLASSPATH:$NMS_CLASSES/AdventNetTL1.jar:$NMS_CLASSES/json.jar:$OTHER_CLASSPATH
export CLASSPATH

if [ $# -eq 0 ]
then
$JAVA_HOME/bin/java -Dpgsql.home=$PGSQL_HOME com.adventnet.launcher.nms.ReinitializeAll -ROOT_DIR $NMS_HOME -NMS_RESOURCE_DIRECTORY $NMS_HOME/html -RESOURCE_PROPERTIES ReinitializeNmsResources -RESOURCE_LOCALE en_US
else
# The valid value for MODE_VALUE is 'MODE ALL'.
$JAVA_HOME/bin/java -Dpgsql.home=$PGSQL_HOME -Dwebnms.rootdir=$NMS_HOME jdbc.DropSchema $MODE_VALUE
fi
