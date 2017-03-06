@ echo off



rem $Id: ShutDown.bat,v 1.5 2007/10/19 08:31:43 venkatramanan Exp $
rem This file can be used to ShutDown the server from remote machine also.
rem Just copy the ShutDown.class ,BASE64Encoder.class and this file to remote 
rem machine. Edit this file to set the java_home  and classpath variable

cd ..
call .\setEnv.bat
set HOSTNAME=localhost
set RMI_PORT=1099
set TCP_PORT=20001

set CLASSPATH=%NMS_HOME%\temp;%NMS_SERVER_CLASSES%;%NMS_CLASSES%;%NMS_CLASSES%\ApiUtils.jar;%HBN_CLASSPATH%;%TRANSACTION_CLASSPATH%;%NMS_HOME%\examples\classes
rem if "test"=="%1test" goto default

rem if "test1"=="%1test1" goto usage

set USER_NAME=%1
set USER_PASSWD=%2

rem Usage : java com.adventnet.nms.util.StandAloneShutDown [-h host] [-wp webServerPort] [-rp rmiRegPort] [-tp tcpPort] [-u username]

%JAVA_HOME%\bin\java com.adventnet.nms.util.StandAloneShutDown -h %HOSTNAME% -wp %WEBSERVER_PORT% -rp %RMI_PORT% -tp %TCP_PORT% -u %USER_NAME%
cd bin

goto end

:default

set USER_NAME=root

%JAVA_HOME%\bin\java com.adventnet.launcher.nms.ShutdownServer -HOST_NAME %HOSTNAME% -WEB_SERVER_PORT %WEBSERVER_PORT% -RMI_PORT %RMI_PORT% -TCP_PORT %TCP_PORT% -USER_NAME %USER_NAME% -NMS_RESOURCE_DIRECTORY %NMS_HOME%\html -RESOURCE_PROPERTIES ShutDownNmsServerResources -RESOURCE_LOCALE en_US

cd bin

goto end

:end
