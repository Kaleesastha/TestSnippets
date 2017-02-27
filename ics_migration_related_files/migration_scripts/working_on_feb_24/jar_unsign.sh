. ./setEnv.sh
JAVA_HOME=/opt/motorola/java
export JAVA_HOME
export PATH=$JAVA_HOME/bin:$PATH
echo ============================$NMS_HOME/classes/NmsServerClasses.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/NmsServerClasses.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/NmsServerClasses.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetSUMClient.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetSUMClient.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetSUMClient.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetUpdateManagerInstaller.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetUpdateManagerInstaller.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetUpdateManagerInstaller.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetSUMServer.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetSUMServer.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetSUMServer.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetSnmp.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetSnmp.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetSnmp.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/SNMPDebugger.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/SNMPDebugger.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/SNMPDebugger.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetSAS.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetSAS.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetSAS.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/Mail.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/Mail.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/Mail.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/activation.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/activation.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/activation.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetNPrevalent.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetNPrevalent.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetNPrevalent.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/bsh-1.2b3.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/bsh-1.2b3.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/bsh-1.2b3.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/debugger.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/debugger.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/debugger.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/ManagementServer.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/ManagementServer.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/ManagementServer.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/apache/tomcat/common/lib/servlet-api.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/apache/tomcat/common/lib/servlet-api.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/apache/tomcat/common/lib/servlet-api.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/apache/tomcat/bin/bootstrap.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/apache/tomcat/bin/bootstrap.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/apache/tomcat/bin/bootstrap.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/apache/tomcat/bin/tomcat-juli.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/apache/tomcat/bin/tomcat-juli.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/apache/tomcat/bin/tomcat-juli.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/apache/tomcat/bin/commons-logging-api.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/apache/tomcat/bin/commons-logging-api.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/apache/tomcat/bin/commons-logging-api.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/mysql_connector.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/mysql_connector.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/mysql_connector.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/p6spy.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/p6spy.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/p6spy.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/log4j.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/log4j.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/log4j.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetTftp.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetTftp.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetTftp.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetCLI.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetCLI.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetCLI.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetCLIClient.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetCLIClient.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetCLIClient.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetJta.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetJta.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetJta.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/jta.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/jta.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/jta.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetSnmpDistributedAPI.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetSnmpDistributedAPI.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetSnmpDistributedAPI.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/JimiProClasses.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/JimiProClasses.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/JimiProClasses.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/jfreechart.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/jfreechart.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/jfreechart.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/jcommon.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/jcommon.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/jcommon.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/NetMonitor/build/EMS_Tutorial_Client.jar====================
#cd $NMS_HOME/temp/
#jar -xf $NMS_HOME/NetMonitor/build/EMS_Tutorial_Client.jar
#rm -rf META-INF
#jar -cf test.jar *
#mv test.jar $NMS_HOME/NetMonitor/build/EMS_Tutorial_Client.jar
#rm -rf $NMS_HOME/temp/*
#cd ..
echo ============================$NMS_HOME/classes/ftp.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/ftp.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/ftp.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetUI.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetUI.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetUI.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/ganymed-ssh2-build210.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/ganymed-ssh2-build210.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/ganymed-ssh2-build210.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/RXTXcomm.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/RXTXcomm.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/RXTXcomm.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/smslib-3.5.2.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/smslib-3.5.2.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/smslib-3.5.2.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/hibernate3.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/hibernate3.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/hibernate3.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/c3p0-0.9.1.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/c3p0-0.9.1.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/c3p0-0.9.1.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/hibernate3.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/hibernate3.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/hibernate3.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/ant-antlr-1.6.5.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/ant-antlr-1.6.5.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/ant-antlr-1.6.5.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/ehcache-1.2.3.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/ehcache-1.2.3.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/ehcache-1.2.3.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/antlr-2.7.6.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/antlr-2.7.6.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/antlr-2.7.6.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/asm-attrs.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/asm-attrs.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/asm-attrs.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/asm.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/asm.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/asm.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/cglib-2.1.3.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/cglib-2.1.3.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/cglib-2.1.3.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/dom4j-1.6.1.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/dom4j-1.6.1.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/dom4j-1.6.1.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/commons-collections-2.1.1.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/commons-collections-2.1.1.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/commons-collections-2.1.1.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/hbnlib/commons-logging-1.0.4.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/hbnlib/commons-logging-1.0.4.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/hbnlib/commons-logging-1.0.4.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/j2ssh-core.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/j2ssh-core.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/j2ssh-core.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/j2ssh-common.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/j2ssh-common.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/j2ssh-common.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/json.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/json.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/json.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/log4j.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/log4j.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/log4j.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/CommonPlatform.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/CommonPlatform.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/CommonPlatform.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetTL1.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetTL1.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetTL1.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetTL1Tools.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetTL1Tools.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetTL1Tools.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetSnmpAgent.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetSnmpAgent.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetSnmpAgent.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/xmojo.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/xmojo.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/xmojo.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetWebNmsAgent.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetWebNmsAgent.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetWebNmsAgent.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetJmxAgent.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetJmxAgent.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetJmxAgent.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetARUtils.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetARUtils.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetARUtils.jar
rm -rf $NMS_HOME/temp/*
cd ..
echo ============================$NMS_HOME/classes/AdventNetTL1Agent.jar====================
cd $NMS_HOME/temp/
jar -xf $NMS_HOME/classes/AdventNetTL1Agent.jar
rm -rf META-INF
jar -cf test.jar *
mv test.jar $NMS_HOME/classes/AdventNetTL1Agent.jar
rm -rf $NMS_HOME/temp/*
cd ..
