@ echo off

rem $Id: startApplicationClient.bat,v 1.8 2008/08/13 12:43:17 tinku Exp $

rem --- set the JAVA_HOME path to jdk
rem --- if jdk/jre 1.1.x is used, set classpath to swingall.jar also
rem --- refer the documentation if you wish to install standalone client in different machine

cd ..

call .\setEnv.bat

rem Please do not edit this entry, it will be used by the NarUtilities tool
set NARPATH=;./NetMonitor/build/PerformanceGraphs.jar;./NetMonitor/build/RuntimeConfigFrame.jar;./NetMonitor/build/AuthMain.jar;./NetMonitor/build/ConfigPanel.jar

rem Please do not edit this entry, it will be used by the DeploymentWizard tool
set CUSTOMPATH=

set CLASSPATH=%NMS_ARC%;%SNMP_CLASSPATH%;%JIMI_CLASSPATH%;%XML_CLASSPATH%;%BUILDER_CLASSPATH%;%MS_CLIENT_CLASSPATH%;%TL1_CLASSPATH%;%CLI_CLIENT_CLASSPATH%;%CUSTOMPATH%;%NARPATH%;%NMS_CLASSES%\AdventNetSnmpV3USM.jar;%NMS_CLASSES%\ClientExamples.jar;%NMS_CLASSES%\AdventNetAboutDialog.jar;%NMS_CLASSES%\jnlp.jar;%NMS_CLASSES%\AdventNetCCLLibrary.jar;%NMS_CLASSES%\AdventNetProBeans.jar;%NMS_CLASSES%\AdventNetDMPModels.jar;%NMS_CLASSES%\Mail.jar;%NMS_CLASSES%\json.jar;%NMS_CLASSES%\AdventNetNPrevalent.jar;%NMS_CLASSES%\AdventNetUpdateManagerInstaller.jar;%SUM_CLIENT_CLASSPATH%


if  "test%1" == "test" goto defaultargs1 

if "test%2" == "test" goto defaultargs2

if not "test%6" == "test" goto defaultargs3

 :start
%JAVA_HOME%\bin\java -XX:+HeapDumpOnOutOfMemoryError -Dcom.sun.management.jmxremote.port=14000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=%COMPUTERNAME% -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=14500 com.adventnet.nms.startclient.WebNMSClient %1 %2 %3 %4 %5
set clientexit=%errorlevel%
if %clientexit% NEQ 126 goto end
%JAVA_HOME%\bin\java -classpath %UPDATE_MANAGER_CLASSPATH% com.adventnet.tools.update.installer.UpdateManager -u conf -h %NMS_HOME% -c
set umexit=%errorlevel%
if %umexit% EQU 0 goto start
goto end

:defaultargs1
 :start
%JAVA_HOME%\bin\java  -XX:+HeapDumpOnOutOfMemoryError -Dcom.sun.management.jmxremote.port=14000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=%COMPUTERNAME% -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=14500 -Dhttps.protocols=TLSv1 -Djavax.net.ssl.trustStore=conf\Truststore.truststore com.adventnet.nms.startclient.WebNMSClient localhost %SSL_PORT%
set clientexit=%errorlevel%
if %clientexit% NEQ 126 goto end
%JAVA_HOME%\bin\java -classpath %UPDATE_MANAGER_CLASSPATH% com.adventnet.tools.update.installer.UpdateManager -u conf -h %NMS_HOME% -c
set umexit=%errorlevel%
if %umexit% EQU 0 goto start
goto end

:defaultargs2
echo "USAGE: startApplicationClient [HOST] [PORT]" 
goto end

:defaultargs3
echo "USAGE: startApplicationClient [HOST] [PORT] [YES/NO] [Language] [Country]"
goto end

:end
cd bin
