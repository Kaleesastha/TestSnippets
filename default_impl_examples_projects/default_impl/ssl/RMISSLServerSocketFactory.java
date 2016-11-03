//$Id: RMISSLServerSocketFactory.java,v 1.1.4.1 2014/03/06 10:34:00 venkatramanan Exp $

package test;
import java.io.*;
import java.net.*;
import java.rmi.server.*;
import javax.net.ssl.*;
import java.security.KeyStore;
import javax.net.ssl.*;
import java.rmi.server.RMISocketFactory;
import java.util.*;
import com.adventnet.nms.util.NmsUtil;

public class RMISSLServerSocketFactory extends RMISocketFactory  implements Serializable {

    /*
     * Create one SSLServerSocketFactory, so we can reuse sessions
     * created by previous sessions of this SSLContext.
     */
    private SSLServerSocketFactory ssf = null;

	    private String nms_dir;

	    private String                 m_strTrustStoreFile       = null;
	    private String                 m_strTrustStorePassword   = null;
	    private String                 m_strKeyStore             = null;
	    private String                 m_strKeyStorePassword     = null;
	    /*private String keystore_Password = new String();
	    private String keystore_file;
	    private String truststore_file;*/

	    public static int bePort = 2000;
	    public static boolean fe = false;
	    private static int snmpPort=0;
	    private static int configServerPort=2300;
	    public static int JMX_RMI_SERVER_PORT=3000;
	    public static int JMX_RMI_CONNECTOR_PORT=4000;

    public RMISSLServerSocketFactory() throws Exception {
	try {

		String snmp = System.getProperty("com.sun.management.snmp.port");//NO I18N
		if(snmp != null && snmp != "")
		{
			snmpPort = Integer.parseInt(snmp);
		}
		m_strKeyStore  = System.getProperty("javax.net.ssl.keyStore"); //NO I18N
		m_strTrustStoreFile = System.getProperty("javax.net.ssl.trustStore"); //NO I18N
		m_strKeyStorePassword = System.getProperty("javax.net.ssl.keyStorePassword"); //NO I18N
		m_strTrustStorePassword = System.getProperty("javax.net.ssl.trustStorePassword"); //NO I18N
		//storetype value is determined using the system property hence preventing the hardcoding of keyStoreType
		KeyStore objKeyStore=null;	
		KeyManagerFactory objKeyMngrFactory = null;
		SSLContext objSSLContext = null;
		String keyStoreType = System.getProperty("javax.net.ssl.keyStoreType");//No Internationalisation
		//if system property is null default JKS is set.
		if(keyStoreType==null)
		{
			objKeyStore         = KeyStore.getInstance("JKS");//No Internationalisation
		}
		else
		{
			//StoreType is set to the value provided in the systemProperty
			objKeyStore         = KeyStore.getInstance(keyStoreType);
		}
		//Algorithm value is got from the system property before hence configuring it as SunX509- For Lucent:3074762 to have it as IBMX509
		String algorithm = System.getProperty("ssl.KeyManagerFactory.algorithm"); //NO I18N
		if(algorithm != null && !(algorithm.trim().equals(""))) //NO I18N
		{
			objKeyMngrFactory = KeyManagerFactory.getInstance(algorithm);
		}
		else
		{
			objKeyMngrFactory = KeyManagerFactory.getInstance("SunX509"); //NO I18N
		}
		String protocol = System.getProperty("ssl.Protocol"); //NO I18N
		if(protocol != null && !(protocol.trim().equals(""))) //NO I18N
		{
			objSSLContext       = SSLContext.getInstance(protocol);
		}
		else
		{
			objSSLContext       = SSLContext.getInstance("SSLv3");
		}

		if(m_strKeyStore !=null && m_strKeyStore != "" && m_strKeyStorePassword != null && m_strKeyStorePassword != "" && m_strTrustStoreFile != null && m_strTrustStoreFile != "" && m_strKeyStorePassword!=null && m_strKeyStorePassword != ""){
			objKeyStore.load(new FileInputStream(m_strKeyStore),m_strTrustStorePassword.toCharArray());
			objKeyMngrFactory.init(objKeyStore,m_strKeyStorePassword.toCharArray());
			objSSLContext.init(objKeyMngrFactory.getKeyManagers(),null,null);
			ssf = objSSLContext.getServerSocketFactory();
		}
	    /*SSLContext ctx;
	    KeyManagerFactory kmf;
	    KeyStore ks;

	    ks = KeyStore.getInstance("JKS"); //NO I18N

	    keystore_file = System.getProperty("javax.net.ssl.keyStore"); //NO I18N
	    truststore_file = System.getProperty("javax.net.ssl.trustStore"); //NO I18N

	    keystore_Password = System.getProperty("javax.net.ssl.keyStorePassword"); //NO I18N
	    if(keystore_file !=null && keystore_file != "" && truststore_file != null && truststore_file != "" && keystore_Password!=null && keystore_Password != "")
	    {
		    char[] passphrase = keystore_Password.toCharArray();
		    ks.load(new FileInputStream(keystore_file),passphrase);
		    kmf = KeyManagerFactory.getInstance("SunX509"); //NO I18N
		    kmf.init(ks, passphrase);

		    ctx = SSLContext.getInstance("TLSv1.2"); //NO I18N
		    ctx.init(kmf.getKeyManagers(), null, null);

		    ssf = ctx.getServerSocketFactory();
	    }*/
	} 
	catch(IOException ioe)
	{
		if(ioe.getMessage().indexOf("Keystore was tampered with, or password was incorrect") != -1) //NO I18N
		{
			System.err.println("The keystore password::"+m_strKeyStorePassword+":: configured in javax.net.ssl.keyStorePassword property is incorrect. Correct the same & restart the server"); //NO I18N
		}
	}
	/*catch(SSLException ssl)
	{
		if(e.getMessage().indexOf("Keystore was tampered with, or password was incorrect") != -1) //NO I18N
		{
			System.err.println("The keystore password configured in javax.net.ssl.keyStorePassword property is incorrect. Correct the same & restart the server"); //NO I18N
		}
	}*/
	catch (Exception e) {
	    e.printStackTrace();
	    throw e;
	}
    }

    public SSLServerSocket createServerSocket(int port) throws IOException {
	    if (port == NmsUtil.getRegistryPort() || port ==bePort  || port == snmpPort || port == configServerPort || port == JMX_RMI_SERVER_PORT || port == JMX_RMI_CONNECTOR_PORT)
{
		    return (SSLServerSocket)ssf.createServerSocket(port,300);
}
	    else
	    {
		    if(!(fe))
		    {
			    return (SSLServerSocket)ssf.createServerSocket(6020);
		    }
		    else
		    {
			    return (SSLServerSocket)ssf.createServerSocket(6021);
		    }
	    }
    }

    public SSLSocket createSocket(String host, int port) throws IOException {
	    SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
	    SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
	    socket.startHandshake();
	    return socket;

    }
    public int hashCode() { return 58; }
    public boolean equals(Object o) { return this.getClass() == o.getClass(); }

}

