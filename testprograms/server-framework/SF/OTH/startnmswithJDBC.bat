@echo off

rem  If you start NMS Server from a shell with classpath already set to
rem NMS classes, some of the RMI APIs won't bind to registry (especially
rem DeviceConfigurationAPI). So, we unset the CLASSPATH value before
rem starting the nms server.

if  "test%1" =="test"  goto usage

if not "test%3" =="test"  goto threeargs 

if not "test%2" =="test" goto twoargs

 if  not  "test%1"=="test" goto onearg

:threeargs
PORT1=%1
PORT2=%2
PORT3=%3
goto skipNext

:twoargs
PORT1=%1
PORT2=%2
goto skipNext
:onearg
PORT1=%1

:skipNext

cd ..

set CLASSPATH=

call .\setEnv.bat

set PATH=%NMS_HOME%\lib;%PATH%

rem echo %PATH%

rem CLASS_PATH=$NMS_CLASSES:$JIMI_CLASSPATH:$XML_CLASSPATH:$NMS_CLASSES/AdventNetUpdateManager.jar:$LICENSE_PATH:$NMS_CLASSES/ApiUtils.jar

set CLASS_PATH=%NMS_CLASSES%;%SUM_CLIENT_CLASSPATH%;%SUM_SERVER_CLASSPATH%;%CUSTOMPATH%;%SNMP_CLASSPATH%;%SAS_CLASSPATH%;%XML_CLASSPATH%;%OTHER_CLASSPATH%;%MS_CLASSPATH%;%WEBSERVER_CLASSPATH%;%DB_CLASSPATH%;%BUILDER_CLASSPATH%;%TFTP_CLASSPATH%;%CLI_CLASSPATH%;%TRANSACTION_CLASSPATH%;%NMS_CLASSES%\AdventNetSnmpDistributedAPI.jar;%JIMI_CLASSPATH%;%MOPATH%;%LICENSE_PATH%;%FTP_CLASSPATH%

rem Uncomment the following lines for using TL1 Protocol.
set CLASS_PATH=%CLASS_PATH%;%TL1_CLASSPATH%

rem Uncomment the following lines for using JMX Agent.
set CLASS_PATH=%CLASS_PATH%;%JMX_AGENT_CLASSPATH%

%JAVA_HOME%/bin/java -mx200M -Dresource_check=%PORT1%,%PORT2%,%PORT3% -Djava.rmi.server.codebase="%CODEBASE_LIST%" -mx100m -cp %CLASS_PATH% com.adventnet.launcher.nms.StartNmsJdbc ROOT_DIR . APACHE_DIR ./apache TOMCAT_DIR ./apache/tomcat BE_FE  TCP NATIVE_PING true NMS_BE_PORT 2000 WEBSERVER_PORT 9090 WEBCONTAINER_PORT 8009 COUNTRY US LANGUAGE en

cd bin
goto end

:usage
echo USAGE: startnmswithJDBC.bat PORT1 [PORT2] [PORT3]

:end






