@echo off


set ARG1=%1
set ARG2=%2
set ARG3=%3
set ARG4=%4
set ARG5=%5
set ARG6=%6

cd ..

call setEnv.bat

set CLASSPATH=.\myclasses;%NMS_CLASSES%;%XML_CLASSPATH%;%BUILDER_CLASSPATH%;%JIMI_CLASSPATH%;%NMS_ARC%;./classes/AdventNetCCLLibrary.jar;./classes/AdventNetUpdateManagerInstaller.jar;

%JAVA_HOME%\bin\java com.adventnet.nms.tools.confchanges.UpdateConfChanges %NMS_HOME% %ARG1% %ARG2% %ARG3% %ARG4% %ARG5% %ARG6%

cd bin

