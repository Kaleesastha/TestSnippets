#!/bin/sh


cd ..

. ./setEnv.sh

export CLASSPATH=$NMS_CLASSES:$XML_CLASSPATH

$JAVA_HOME/bin/java com.adventnet.nms.tools.nar.CommandLineNarInstaller $*

cd bin
