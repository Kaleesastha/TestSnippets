package test;

import java.io.PrintStream;

public class CreateCommands
{
  public static void main(String[] args)
  {
    String[] jarNames = { "NMS_HOME/classes/NmsServerClasses.jar", "NMS_HOME/classes/AdventNetSUMClient.jar", "NMS_HOME/classes/AdventNetUpdateManagerInstaller.jar", "NMS_HOME/classes/AdventNetSUMServer.jar", "NMS_HOME/classes/AdventNetSnmp.jar", "NMS_HOME/classes/SNMPDebugger.jar", "NMS_HOME/classes/AdventNetSAS.jar", "NMS_HOME/classes/Mail.jar", "NMS_HOME/classes/activation.jar", "NMS_HOME/classes/AdventNetNPrevalent.jar", "NMS_HOME/classes/bsh-1.2b3.jar", "NMS_HOME/classes/debugger.jar", "NMS_HOME/classes/ManagementServer.jar", "NMS_HOME/apache/tomcat/common/lib/servlet-api.jar", "NMS_HOME/apache/tomcat/bin/bootstrap.jar", "NMS_HOME/apache/tomcat/bin/tomcat-juli.jar", "NMS_HOME/apache/tomcat/bin/commons-logging-api.jar", "NMS_HOME/classes/mysql_connector.jar", "NMS_HOME/classes/p6spy.jar", "NMS_HOME/classes/log4j.jar", "NMS_HOME/classes/AdventNetTftp.jar", "NMS_HOME/classes/AdventNetCLI.jar", "NMS_HOME/classes/AdventNetCLIClient.jar", "NMS_HOME/classes/AdventNetJta.jar", "NMS_HOME/classes/jta.jar", "NMS_HOME/classes/AdventNetSnmpDistributedAPI.jar", "NMS_HOME/classes/JimiProClasses.jar", "NMS_HOME/classes/jfreechart.jar", "NMS_HOME/classes/jcommon.jar", "NMS_HOME/NetMonitor/build/EMS_Tutorial_Client.jar", "NMS_HOME/classes/ftp.jar", "NMS_HOME/classes/AdventNetUI.jar", "NMS_HOME/classes/ganymed-ssh2-build210.jar", "NMS_HOME/classes/RXTXcomm.jar", "NMS_HOME/classes/smslib-3.5.2.jar", "NMS_HOME/classes/hbnlib/hibernate3.jar", "NMS_HOME/classes/hbnlib/c3p0-0.9.1.jar", "NMS_HOME/classes/hbnlib/hibernate3.jar", "NMS_HOME/classes/hbnlib/ant-antlr-1.6.5.jar", "NMS_HOME/classes/hbnlib/ehcache-1.2.3.jar", "NMS_HOME/classes/hbnlib/antlr-2.7.6.jar", "NMS_HOME/classes/hbnlib/asm-attrs.jar", "NMS_HOME/classes/hbnlib/asm.jar", "NMS_HOME/classes/hbnlib/cglib-2.1.3.jar", "NMS_HOME/classes/hbnlib/dom4j-1.6.1.jar", "NMS_HOME/classes/hbnlib/commons-collections-2.1.1.jar", "NMS_HOME/classes/hbnlib/commons-logging-1.0.4.jar", "NMS_HOME/classes/j2ssh-core.jar", "NMS_HOME/classes/j2ssh-common.jar", "NMS_HOME/classes/json.jar", "NMS_HOME/classes/log4j.jar", "NMS_HOME/classes/CommonPlatform.jar", "NMS_HOME/classes/AdventNetTL1.jar", "NMS_HOME/classes/AdventNetTL1Tools.jar", "NMS_HOME/classes/AdventNetSnmpAgent.jar", "NMS_HOME/classes/xmojo.jar", "NMS_HOME/classes/AdventNetWebNmsAgent.jar", "NMS_HOME/classes/AdventNetJmxAgent.jar", "NMS_HOME/classes/AdventNetARUtils.jar", "NMS_HOME/classes/AdventNetTL1Agent.jar" };

    String nmsHome = "$NMS_HOME/temp/";
    System.err.println(". ./setEnv.sh");
    for (int i = 0; i < jarNames.length; i++)
    {
      String jarName = jarNames[i];
      String command = "cd " + nmsHome + "\njar -xf $" + jarName + "\nrm -rf META-INF\njar -cf test.jar *\nmv test.jar $" + jarName + "\nrm -rf " + nmsHome + "*\ncd ..";
      System.err.println("echo ============================$" + jarName + "====================");
      System.err.println(command);
    }
  }
}
