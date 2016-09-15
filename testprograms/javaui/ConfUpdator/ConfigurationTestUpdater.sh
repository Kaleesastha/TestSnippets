#!/bin/sh

# This File will update the newly added Configuration information into the existing Configuration files without affecting the changes made in it.

	MODTYPE=$1
	VERSION=$2
        ARG3=$3
	ARG4=$4
        ARG5=$5
	ARG6=$6

cd ..
. ./setEnv.sh

CLASSPATH=$NMS_CLASSES:$XML_CLASSPATH:$BUILDER_CLASSPATH:$JIMI_CLASSPATH:$NMS_ARC:$NMS_CLASSES/AdventNetCCLLibrary.jar:$NMS_CLASSES/AdventNetUpdateManagerInstaller.jar:
export CLASSPATH

$JAVA_HOME/bin/java com.adventnet.nms.tools.confchanges.UpdateConfChanges $NMS_HOME $MODTYPE $VERSION $ARG3 $ARG4 $ARG5 $ARG6

cd bin

