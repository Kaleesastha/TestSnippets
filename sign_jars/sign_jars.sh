STOREPASS=webnms
KEYPASS=webnms
KEYSTORE=webnms.keystore

JDK_HOME=/home/local/ZOHOCORP/venkat-0773/install/jdk1.6.0_16

export JDK_HOME
NMS_HOME=`pwd`

$JDK_HOME/bin/keytool -genkey -alias webnms -keypass $KEYPASS -keystore $KEYSTORE -storepass $STOREPASS -dname "C=IN,ST=TAMILNADU,O=WEBNMS,OU=ADVENTNET,L=CHENNAI,CN=$hostname"
export NMS_HOME

$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetSnmp.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/Mail.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/SNMPDebugger.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetSnmpV3USM.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/ClientExamples.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/JimiProClasses.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetUI.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/ManagementClient.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetCLIClient.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetTL1.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetTL1Tools.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetUtils.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/ApiUtils.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetJta.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetAboutDialog.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetCCLLibrary.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetProBeans.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetDMPModels.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/jnlp.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/jfreechart.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/jcommon.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/NmsClientClasses.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/NetMonitor/build/RuntimeConfigFrame.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/NetMonitor/build/AuthMain.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/NetMonitor/build/ConfigPanel.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/NetMonitor/build/PerformanceGraphs.jar webnms

#server specific jars
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/NmsServerClasses.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetSUMServer.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetSUMClient.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetUpdateManagerInstaller.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetSAS.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/activation.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetNPrevalent.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/bsh-1.2b3.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/debugger.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/ManagementServer.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetTftp.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetCLI.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/jta.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetSnmpDistributedAPI.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/ftp.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetTL1.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetSnmpAgent.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/xmojo.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetWebNmsAgent.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetJmxAgent.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetARUtils.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/AdventNetTL1Agent.jar webnms

#apache specific jars
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/apache/tomcat/common/lib/servlet-api.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/apache/tomcat/bin/bootstrap.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/apache/tomcat/bin/tomcat-juli.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/apache/tomcat/bin/commons-logging-api.jar webnms

#hibernate related jars
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/hbnlib/ant-antlr-1.6.5.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/hbnlib/antlr-2.7.6.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/hbnlib/asm-attrs.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/hbnlib/asm.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/hbnlib/c3p0-0.9.1.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/hbnlib/cglib-2.1.3.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/hbnlib/commons-collections-2.1.1.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/hbnlib/commons-logging-1.0.4.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/hbnlib/dom4j-1.6.1.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/hbnlib/ehcache-1.2.3.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/hbnlib/hibernate3.jar webnms

$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/log4j.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/p6spy.jar webnms
$JDK_HOME/bin/jarsigner -keystore $KEYSTORE -storepass $STOREPASS -keypass $KEYPASS $NMS_HOME/classes/json.jar webnms
