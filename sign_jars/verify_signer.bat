@echo off

echo *****************************************************
echo *This signs all the jars under classes\signedjars   *
echo *****************************************************
set JDK_HOME=C:\java

if not exist %JDK_HOME%\bin\javac.exe goto SetJDKHome

call setEnv.bat

echo =========================
echo verifying %NMS_HOME%\classes\NmsServerClasses.jar
jarsigner -verify %NMS_HOME%\classes\NmsServerClasses.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetSUMClient.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetSUMClient.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetUpdateManagerInstaller.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetUpdateManagerInstaller.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetSUMServer.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetSUMServer.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetSnmp.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetSnmp.jar

echo =========================
echo verifying %NMS_HOME%\classes\SNMPDebugger.jar
jarsigner -verify %NMS_HOME%\classes\SNMPDebugger.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetSAS.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetSAS.jar

echo =========================
echo verifying %NMS_HOME%\classes\Mail.jar
jarsigner -verify %NMS_HOME%\classes\Mail.jar

echo =========================
echo verifying %NMS_HOME%\classes\activation.jar
jarsigner -verify %NMS_HOME%\classes\activation.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetNPrevalent.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetNPrevalent.jar

echo =========================
echo verifying %NMS_HOME%\classes\bsh-1.2b3.jar
jarsigner -verify %NMS_HOME%\classes\bsh-1.2b3.jar

echo =========================
echo verifying %NMS_HOME%\classes\bsh-1.2b3.jar
jarsigner -verify %NMS_HOME%\classes\bsh-1.2b3.jar

echo =========================
echo verifying %NMS_HOME%\classes\ManagementServer.jar
jarsigner -verify %NMS_HOME%\classes\ManagementServer.jar

echo =========================
echo verifying %NMS_HOME%\apache\tomcat\common\lib\servlet-api.jar
jarsigner -verify %NMS_HOME%\apache\tomcat\common\lib\servlet-api.jar

echo =========================
echo verifying %NMS_HOME%\apache\tomcat\bin\bootstrap.jar
jarsigner -verify %NMS_HOME%\apache\tomcat\bin\bootstrap.jar

echo =========================
echo verifying %NMS_HOME%\apache\tomcat\bin\tomcat-juli.jar
jarsigner -verify %NMS_HOME%\apache\tomcat\bin\tomcat-juli.jar

echo =========================
echo verifying %NMS_HOME%\apache\tomcat\bin\commons-logging-api.jar
jarsigner -verify %NMS_HOME%\apache\tomcat\bin\commons-logging-api.jar

echo =========================
echo verifying %NMS_HOME%\classes\p6spy.jar
jarsigner -verify %NMS_HOME%\classes\p6spy.jar

echo =========================
echo verifying %NMS_HOME%\classes\log4j.jar
jarsigner -verify %NMS_HOME%\classes\log4j.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetTftp.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetTftp.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetCLI.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetCLI.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetCLIClient.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetCLIClient.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetJta.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetJta.jar

echo =========================
echo verifying %NMS_HOME%\classes\jta.jar
jarsigner -verify %NMS_HOME%\classes\jta.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetSnmpDistributedAPI.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetSnmpDistributedAPI.jar

echo =========================
echo verifying %NMS_HOME%\classes\JimiProClasses.jar
jarsigner -verify %NMS_HOME%\classes\JimiProClasses.jar

echo =========================
echo verifying %NMS_HOME%\classes\jfreechart.jar
jarsigner -verify %NMS_HOME%\classes\jfreechart.jar

echo =========================
echo verifying %NMS_HOME%\classes\jcommon.jar
jarsigner -verify %NMS_HOME%\classes\jcommon.jar

echo =========================
echo verifying %NMS_HOME%\classes\ftp.jar
jarsigner -verify %NMS_HOME%\classes\ftp.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetUI.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetUI.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\hibernate3.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\hibernate3.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\c3p0-0.9.1.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\c3p0-0.9.1.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\hibernate3.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\hibernate3.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\ant-antlr-1.6.5.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\ant-antlr-1.6.5.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\ehcache-1.2.3.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\ehcache-1.2.3.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\antlr-2.7.6.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\antlr-2.7.6.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\asm-attrs.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\asm-attrs.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\asm.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\asm.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\cglib-2.1.3.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\cglib-2.1.3.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\dom4j-1.6.1.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\dom4j-1.6.1.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\commons-collections-2.1.1.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\commons-collections-2.1.1.jar

echo =========================
echo verifying %NMS_HOME%\classes\hbnlib\commons-logging-1.0.4.jar
jarsigner -verify %NMS_HOME%\classes\hbnlib\commons-logging-1.0.4.jar

echo =========================
echo verifying %NMS_HOME%\classes\json.jar
jarsigner -verify %NMS_HOME%\classes\json.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetTL1.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetTL1.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetTL1Tools.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetTL1Tools.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetSnmpAgent.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetSnmpAgent.jar

echo =========================
echo verifying %NMS_HOME%\classes\xmojo.jar
jarsigner -verify %NMS_HOME%\classes\xmojo.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetWebNmsAgent.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetWebNmsAgent.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetJmxAgent.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetJmxAgent.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetARUtils.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetARUtils.jar

echo =========================
echo verifying %NMS_HOME%\classes\AdventNetTL1Agent.jar
jarsigner -verify %NMS_HOME%\classes\AdventNetTL1Agent.jar

:SetJDKHome
echo "Please set the JDK_HOME"
goto end

:end
pause
