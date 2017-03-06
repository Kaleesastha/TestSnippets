#!/bin/sh
#$Id: ShutDown.sh,v 1.5.4.1 2011/11/24 13:07:34 vijayanand.c Exp $
# This file can be used to ShutDown the Nms Server. It can be used from remote
# machine also. Just copy the ShutDown.class, BASE64Encoder.class and this file # to remote machine. 

cd ..
. ./setEnv.sh

if [ $# -eq 2 ]
then
	USER_NAME=$1
	USER_PASSWD=$2
else
	USER_NAME=$1
fi

HOSTNAME=localhost
RMI_PORT=1099

CLASSPATH=$NMS_SERVER_CLASSES:$NMS_CLASSES:$NMS_CLASSES/ApiUtils.jar:$HBN_CLASSPATH:$TRANSACTION_CLASSPATH:$NMS_HOME/examples/classes
export CLASSPATH

if [ $# -eq 0 ]
then
	$JAVA_HOME/bin/java com.adventnet.launcher.nms.ShutdownServer -HOST_NAME $HOSTNAME -WEB_SERVER_PORT $WEBSERVER_PORT -RMI_PORT $RMI_PORT -TCP_PORT $TCP_PORT -USER_NAME $USER_NAME -NMS_RESOURCE_DIRECTORY $NMS_HOME/html -RESOURCE_PROPERTIES ShutDownNmsServerResources -RESOURCE_LOCALE en_US 
else if [ $# -eq 1 ]
then
	$JAVA_HOME/bin/java com.adventnet.nms.util.StandAloneShutDown -h $HOSTNAME -wp $WEBSERVER_PORT -rp $RMI_PORT -u $USER_NAME
else 
if [ $# -eq 2 ]
then
	$JAVA_HOME/bin/java com.adventnet.nms.util.StandAloneShutDown -h $HOSTNAME -wp $WEBSERVER_PORT -rp $RMI_PORT -u $USER_NAME -p $USER_PASSWD
fi
fi
fi
