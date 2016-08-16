#!/bin/sh

cd ..

CLASS_PATH=
export CLASS_PATH

. ./setEnv.sh

CLASS_PATH=$NMS_SERVER_CLASSES:$NMS_CLASSES:$NMS_CLASSES/ftp.jar:$NMS_CLASSES/xalan.jar:$DB_CLASSPATH:$NMS_HOME/html:$NMS_CLASSES/AdventNetNPrevalent.jar:$NMS_CLASSES/AdventNetUpdateManagerInstaller.jar:$HBN_LIB_HOME:$HBN_CLASSPATH:$HBN_LIB_CLASSPATH:$NMS_CLASSES/Mail.jar:$NMS_CLASSES/AdventNetSnmp.jar:$MS_CLASSPATH:$TL1_CLASSPATH:$TRANSACTION_CLASSPATH
export CLASS_PATH

$JAVA_HOME/bin/java -DRESOURCE_PROPERTIES=EnglishToNative -DRESOURCE_LOCALE=en_US -cp $CLASS_PATH com.adventnet.util.support.DebuggingFramework $NMS_HOME/conf/nms-support.xml Automatic

cd bin
