@echo off

rem  If you start NMS Server from a shell with classpath already set to
rem NMS classes, some of the RMI APIs won't bind to registry (especially
rem DeviceConfigurationAPI). So, we unset the CLASSPATH value before
rem starting the nms server.

cd ..

set CLASSPATH=

call .\setEnv.bat

set PATH=%NMS_HOME%\lib;%PATH%

rem CLASSPATH variable is changed to CLASS_PATH . If the classpath is set by usi
rem ng #the CLASSPATH variable ,RMI will load the classes from the CLASSPATH and
rem not from the  codebase.

rem Please do not edit this entry, it will be used by the DeploymentWizard tool
set CUSTOMPATH=

rem Please do not remove/edit this entry, it will be used by the MOWizard tool
set MOPATH=
rem  LICENSE_PATH variable is to locate AdventNetLicense.ali directory path.


set CLASS_PATH=%NMS_SERVER_CLASSES%;%NMS_CLASSES%\AdventNetOPExtn.jar;%NMS_SERVER_CLASSES%;%NMS_CLASSES%;%SUM_CLIENT_CLASSPATH%;%SUM_SERVER_CLASSPATH%;%CUSTOMPATH%;%XML_CLASSPATH%;%OTHER_CLASSPATH%;%MS_CLASSPATH%;%WEBSERVER_CLASSPATH%;%DB_CLASSPATH%;%TFTP_CLASSPATH%;%CLI_CLASSPATH%;%TRANSACTION_CLASSPATH%;%JIMI_CLASSPATH%;%MOPATH%;%LICENSE_PATH%;%FTP_CLASSPATH%;%NMS_CLASSES%\AdventNetUI.jar;%NMS_CLASSES%\ganymed-ssh2-build210.jar;%NMS_CLASSES%\RXTXcomm.jar;%NMS_CLASSES%\smslib-3.5.2.jar;%HBN_CLASSPATH%;%HBN_LIB_CLASSPATH%;%SSH2_CLASSPATH%;%JSON_CLASSPATH%

rem echo %CLASS_PATH%

rem copy %WEBSERVER_HOME%\conf\backup\httpd.conf %WEBSERVER_HOME%\conf > null.txt
copy %TOMCAT_HOME%\conf\backup\server.xml %TOMCAT_HOME%\conf > null.txt
copy %WEBSERVER_HOME%\conf\backup\workers.properties %WEBSERVER_HOME%\conf > null.txt
rem copy %TOMCAT_HOME%\conf\backup\mod_jk.conf-auto %TOMCAT_HOME%\conf\mod_jk.conf-nms >null.txt
del null.txt

rem %JAVA_HOME%\bin\java -cp %CLASS_PATH% -Dtomcatshutdown.port=%TOMCAT_SHUTDOWNPORT% -Dwebserver.rootdir=%WEBSERVER_HOME% -Dwebnms.rootdir=%NMS_HOME%  -Dwebcontainer.home=%TOMCAT_HOME% -Dwebserver.port=%WEBSERVER_PORT% -Dwebcontainer.port=%WEBCONTAINER_PORT% com.adventnet.nms.util.InitWebSvr %WEBSERVER_HOME%\conf\httpd.conf %TOMCAT_HOME%\conf\server.xml %TOMCAT_HOME%\conf\mod_jk.conf-nms %WEBSERVER_HOME%\conf\workers2.properties
%JAVA_HOME%\bin\java -cp %CLASS_PATH% -Dtomcatshutdown.port=%TOMCAT_SHUTDOWNPORT% -Dwebserver.rootdir=%WEBSERVER_HOME% -Dwebnms.rootdir=%NMS_HOME%  -Dwebcontainer.home=%TOMCAT_HOME% -Dwebserver.port=%WEBSERVER_PORT% -Dssl.port=%SSL_PORT% -Dwebcontainer.port=%WEBCONTAINER_PORT% com.adventnet.nms.util.InitWebSvr %TOMCAT_HOME%\conf\server.xml %WEBSERVER_HOME%\conf\workers.properties

ipconfig>%NMS_HOME%\ipconfig.txt


rem Commented CreateSchema since it is called internally by NmsMainBE
rem %JAVA_HOME%\bin\java -cp %CLASS_PATH% -Dwebnms.rootdir=%NMS_HOME% jdbc.CreateSchema

rem Usage: java com.adventnet.nms.startnms.NmsMainBE [NATIVE_PING true/false] [BE_FE TCP/RMI] [NMS_BE_PORT back_end_port_num]  [COUNTRY country_code] [LANGUAGE language_code] [SERVICE true/false] [USERS_DIR userDir] [ROOT_DIR rootDir]

:start1
%JAVA_HOME%\bin\java -cp %CLASS_PATH% -Dcatalina.home=%TOMCAT_HOME% -Dcatalina.base=%TOMCAT_HOME% -Dmysql.home=%MYSQL_HOME% -Xmx100m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseConcMarkSweepGC -Dssl.port=%SSL_PORT% -Dwebserver.port=%SSL_PORT% -Dwebcontainer.port=%SSL_PORT% -Dhttps.protocols=TLSv1 -Djavax.net.ssl.trustStore=conf\Truststore.truststore -Dresource_check="%WEBSERVER_PORT%,%WEBCONTAINER_PORT%,%TOMCAT_SHUTDOWNPORT%" -Dwebserver.rootdir=%WEBSERVER_HOME% -Dcom.sun.management.jmxremote.port=16000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=%COMPUTERNAME% -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=16500 -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djava.util.logging.config.file=%TOMCAT_HOME%\conf\logging.properties -DConnectionCustomizer=test.ConnectionCustomizerImpl com.adventnet.nms.startnms.NmsMainBE NMS_BE_PORT 2000 ROOT_DIR %NMS_HOME% NATIVE_PING true SERVICE TRUE
set serverexit=%errorlevel%
if %serverexit% NEQ 126 goto end
%JAVA_HOME%\bin\java -cp %UPDATE_MANAGER_CLASSPATH% com.adventnet.tools.update.installer.UpdateManager -u conf -h %NMS_HOME% -c
set umexit=%errorlevel%
if %umexit% EQU 0 goto start1
:end


if %errorlevel% NEQ 0 pause

cd bin
