//$Id: NmsInSSL.java,v 1.1.16.1 2016/06/01 09:24:20 venkatramanan Exp $

package test;
import java.rmi.server.RMISocketFactory;
import test.RMISSLServerSocketFactory;
import java.lang.management.*;
import java.rmi.registry.*;
import java.util.*;
import java.io.*;
import javax.management.*;
import javax.management.remote.*;
import javax.management.remote.rmi.*;
import java.rmi.RMISecurityManager;
import com.adventnet.nms.startnms.*;
import com.adventnet.nms.util.*;
import com.adventnet.launcher.nms.FileUtil;
import com.adventnet.nms.util.InitWebSvr;

/** 
 * This Example is used to configure the NMS with firewall setup.  
 */

public class NmsInSSL
{
    static RMISocketFactory sf;
    private static boolean fe = false;
    private static boolean OK = true;
    public static void main(String argv[])
    {
	    String arg[] = argv;
	    OK = isEverythingOK(arg);
	    if(!OK)
	    {
		    System.exit(125);
	    }
	    try{
		    setRemoteManagementForFirewall();
	    }
	    catch(Exception ex)
	    {
		    ex.printStackTrace();
	    }
	if (fe)
	{
		NmsMainFE.main(argv);
	}
	else
	{
		NmsMainBE.main(argv);
	}
    }
    private static void setRemoteManagementForFirewall() throws Exception
    {
	    if (fe)
	    {
		    RMISSLServerSocketFactory.JMX_RMI_SERVER_PORT=3001;
		    RMISSLServerSocketFactory.JMX_RMI_CONNECTOR_PORT=4001;
	    }
	    String jmxRmiServer= System.getProperty("JMX.RMI.SERVER.PORT"); //NO I18N MRV SSL
	    String jmxRmiConnector = System.getProperty("JMX.RMI.CONNECTOR.PORT");//NO I18N MRV SSL
	    try
	    {
		    if( jmxRmiServer != null)
		    {
			    RMISSLServerSocketFactory.JMX_RMI_SERVER_PORT = Integer.parseInt(jmxRmiServer);
		    }
	    }
	    catch(NumberFormatException e)
	    {
		    System.err.println("Exception in configuring the values for JMX_RMI_SERVER_PORT. Setting the default value for JMX_RMI_SERVER_PORT"); //NO I18N
	    }
	    try
	    {
		    if( jmxRmiConnector != null)
		    {
			    RMISSLServerSocketFactory.JMX_RMI_CONNECTOR_PORT = Integer.parseInt(jmxRmiConnector);
		    }
	    }
	    catch(NumberFormatException ex)
	    {
		    System.err.println("Exception in configuring the value for JMX_RMI_CONNECTOR_PORT. Setting the default value for JMX_RMI_CONNECTOR_PORT"); //NO I18N
	    }
	    catch(Exception exp)
	    {
		    System.err.println("Exception in configuring the values for JMX_RMI_SERVER_PORT and JMX_RMI_CONNECTOR_PORT. Setting the default values for JMX_RMI_SERVER_PORT and JMX_RMI_CONNECTOR_PORT"); //NO I18N
	    }
	    String trustStore = System.getProperty("javax.net.ssl.trustStore"); //NO I18N
	    if(trustStore != null && trustStore != "") //NO I18N
	    {
		    LocateRegistry.createRegistry(RMISSLServerSocketFactory.JMX_RMI_SERVER_PORT,sf,sf);
	    }
	    else
		    LocateRegistry.createRegistry(RMISSLServerSocketFactory.JMX_RMI_SERVER_PORT);
        //Get the MBean Server which is responsible for Monitoring
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        //create an RMI Connector server
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi://localhost:"+RMISSLServerSocketFactory.JMX_RMI_CONNECTOR_PORT+"/jndi/rmi://localhost:"+RMISSLServerSocketFactory.JMX_RMI_SERVER_PORT+"/jmxrmi");//NO I18N
        JMXConnectorServer connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
        //Start the RMI connector server
        connectorServer.start();
 

    }

    private static boolean isEverythingOK(String arg[])
    {
	    String rootDir = new String();
	    String error = new String();
	    boolean OK = true;
	    String usage="java test.NmsInSSL [NATIVE_PING true/false] [BE_FE TCP/RMI/SSL] [NMS_BE_PORT back_end_port_num] [ROOT_DIR rootDir] [SERVICE true/false]";//NO I18N
	    boolean sslOverRMI = false; //NO I18N
	    String nL = "\n********************************************************\n";//NO I18N
	    String keyStore_Password = new String();
	    String trustStore_Password = new String();
	    String keyStore_file = new String();
	    String trustStore_file = new String();
	    String url = new String();
	    String transport_file = new String();

	    try
	    {
		    for(int i=0;i<arg.length;i++)
		    {
			    if(arg[i].equalsIgnoreCase("ROOT_DIR"))//NO I18N
			    {
				    rootDir = arg[i+1];
				    System.setProperty("webnms.rootdir",rootDir);//NO I18N
			    }
			    else if(arg[i].indexOf("BE_PORT") != -1)
			    {
				    RMISSLServerSocketFactory.bePort = Integer.parseInt(arg[i+1]);
			    }
		    }
		    String ssl =  System.getProperty("javax.net.ssl.trustStore");//NO I18N
		    if(ssl != null && !ssl.equals("")) {
			    sslOverRMI = true;
		    }
		    //sslOverRMI = RunRmiRegistry.sslOverRMI();
		    if(rootDir == null || rootDir.equalsIgnoreCase(""))//NO I18N
		    {
			    OK = false;
			    error = error + "WebNMS Root directory not set. The proper way to call NmsInSSL is:"+usage+nL;//NO I18N
			    return OK;
		    }
		    String[] opt=new String[2];
		    String tt_tomcat_dir = System.getProperty("webserver.rootdir");//NO I18N
		    if (tt_tomcat_dir==null)
		    {
			    tt_tomcat_dir = rootDir+File.separator+"apache"+File.separator+"tomcat";//NO I18N
		    }

		    File file2 = new File(tt_tomcat_dir+File.separator+"conf"+File.separator+"backup"+File.separator+"server.xml");//No internationalization
		    File file3 = new File(tt_tomcat_dir+File.separator+"conf"+File.separator+"backup"+File.separator+"workers.properties");//No internationalization
		    if (file2.exists())
		    {
			    FileUtil.doFileCopy(tt_tomcat_dir+File.separator+"conf/backup/server.xml",tt_tomcat_dir+File.separator+"conf/server.xml");//NO I18N
			    opt[0]= tt_tomcat_dir+File.separator+"conf"+File.separator+"server.xml"; //NO I18N
		    }
		    if (file3.exists())
		    {
			    FileUtil.doFileCopy(tt_tomcat_dir+File.separator+"conf/backup/workers.properties",tt_tomcat_dir+File.separator+"conf/workers.properties");//NO I18N
			    opt[1]= tt_tomcat_dir+File.separator+"conf"+File.separator+"workers.properties";//NO I18N
		    }
		    InitWebSvr.main(opt);
		    String fileName = System.getProperty("webnms.rootdir")+File.separator+"conf"+File.separator+"DownloadFiles.xml"; //NO I18N
		    fe = new File(fileName).exists();
		    if(fe)
		    {
			    RMISSLServerSocketFactory.fe = true;
		    }
		    else
		    {
			    RMISSLServerSocketFactory.fe = false;
		    }
		    transport_file = rootDir + "/conf/transportProvider.conf";//NO I18N
		    InputStream fis = CommonUtil.openFile(new File(transport_file));
		    if(sslOverRMI)
		    {
			    TransportXMLReader xmlreader = new TransportXMLReader(fis,"SSL");
			    String[] args = (String[])xmlreader.getArguments();
			    trustStore_file = args[5];
			    keyStore_file = args[7];
			    trustStore_Password = args[6];
			    keyStore_Password = args[8];
			    url = rootDir+"/"+keyStore_file;//NO I18N
			    if(!(new File(url).exists()))
			    {
				    OK = false;
				    error = error + "KeyStore file:"+url+" does not exists. Ensure to have "+url+" in place"+nL;//NO I18N
			    }
			    else
			    {
				    System.setProperty("javax.net.ssl.keyStore",url);//NO I18N
			    }
			    url = trustStore_file; //NO I18N
			    if(!(new File(url).exists()))
			    {
				    OK = false;
				    error = error + "TrustStore file:"+url+" does not exists. Ensure to have "+url+" in place"+nL;//NO I18N
			    }
			    else
			    {
				    System.setProperty("javax.net.ssl.trustStore",url);//NO I18N
			    }
			    if(keyStore_Password != null && keyStore_Password != "")//NO I18N
			    {
				    System.setProperty("javax.net.ssl.keyStorePassword",keyStore_Password);//NO I18N
			    }
			    else
			    {
				    OK = false;
				    error = error + "KeyStore Password defined in "+transport_file+" is NULL. Ensure to properly configure keyStorePassword in "+transport_file+nL;//NO I18N
			    }
			    if(trustStore_Password  != null && trustStore_Password != "")//NO I18N
			    {
				    System.setProperty("javax.net.ssl.trustStorePassword",trustStore_Password);//NO I18N
			    }
			    else
			    {
				    OK = false;
				    error = error + "Truststore Password defined in "+transport_file+" is NULL. Ensure to properly configure Truststore Password in "+transport_file+nL;//NO I18N
			    }
			    String sslPort = System.getProperty("ssl.port");//NO I18N
			    if(sslPort ==null || sslPort =="")
			    {
				    if(fe)
				    {sslPort = "8444";}//NO I18N
				    else
				    {sslPort = "8443";}//NO I18N
			    }
			    System.setProperty("ssl.port",sslPort);//NO I18N
			    System.setProperty("webserver.port",sslPort);//NO I18N
			    System.setProperty("webcontainer.port",sslPort);//NO I18N
			    String snmpPort = System.getProperty("com.sun.management.jmxremote.port");//NO I18N
			    if(snmpPort != null && snmpPort != "")//NO I18N
			    {
				    System.clearProperty("com.sun.management.jmxremote.port");//NO I18N
			    }
			    String jmxremote = System.getProperty("com.sun.management.jmxremote.ssl");//NO I18N
			    if(jmxremote !=null && !jmxremote.equalsIgnoreCase("true"))//NO I18N
			    {
				    System.setProperty("com.sun.management.jmxremote.ssl","true");//NO I18N
			    }
			    if(sslOverRMI)//NO I18N
			    try
			    {
				    sf = new test.RMISSLServerSocketFactory();
				    RMISocketFactory.setSocketFactory(sf);
			    }catch(Exception e)
			    {
				    OK= false;
				    error = error +"Error in initiating Server Socket Factory"+nL;//NO I18N
				    e.printStackTrace();
			    }
		    }

	    }
	    catch(Exception e)
	    {
		    if(sslOverRMI)
		    {
			    OK = false;
		    }
	    }
	    finally{
		    if(!OK)
		    {
			    System.err.println(error);
			    System.err.println("Correct the above errors & restart the server");//NO I18N
		    }
		    return OK;
	    }
    }
}

