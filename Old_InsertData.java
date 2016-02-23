package test;
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import com.adventnet.nms.util.*;

public class InsertData
{
	public static void main(String args[])
	{
		try{
			String driver="oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
			//String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=nms-vista1.zohocorpin.com)(PORT=1521))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=orcl)))";
			String url="jdbc:oracle:oci:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCPS)(HOST=192.168.108.171)(PORT=2484)))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=sslclient))(SECURITY=(SSL_SERVER_CERT_DN=\"CN=nms-vista2\")))";
			//String url="jdbc:oracle:thin@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=nms-vista1)(PORT=1521)))";
			String passwd = "sys";
			String user = "system";

			Properties props = new Properties();
					props.setProperty("user", "system");
					props.setProperty("password", "sys");
					props.setProperty("oracle.net.authentication_services","TCPS");
					props.setProperty("javax.net.ssl.trustStorePassword","webnms123");
					props.setProperty("javax.net.ssl.keyStoreType","SSO");
					props.setProperty("javax.net.ssl.keyStore", "C:\\app\\Administrator\\product\\11.1.0\\db_1\\BIN\\certs\\Wallet_client\\cwallet.sso");
					props.setProperty("javax.net.ssl.trustStore", "C:\\app\\Administrator\\product\\11.1.0\\db_1\\BIN\\certs\\Wallet_client");
					props.setProperty("javax.net.ssl.trustStoreType","SSO");

					/*System.setProperty("user", "system");
					System.setProperty("password", "sys");
					System.setProperty("oracle.net.authentication_services","TCPS");
					System.setProperty("javax.net.ssl.trustStorePassword","webnms123");
					System.setProperty("javax.net.ssl.keyStoreType","SSO");
					System.setProperty("javax.net.ssl.keyStore", "C:\\app\\Administrator\\product\\11.1.0\\db_1\\BIN\\certs\\cwallet.sso");
					System.setProperty("javax.net.ssl.trustStore", "C:\\app\\Administrator\\product\\11.1.0\\db_1\\BIN\\certs");
					System.setProperty("javax.net.ssl.trustStoreType","SSO");*/
					//props.setProperty("javax.net.ssl.trustStore","Truststore.truststore");
					//props.setProperty("javax.net.ssl.trustStoreType","JKS");
					//props.setProperty("oracle.net.ssl_cipher_suites","(SSL_RSA_WITH_AES_128_CBC_SHA,SSL_DH_anon_WITH_3DES_EDE_CBC_SHA)");

			Connection conn2 = DriverManager.getConnection(url,props);
			System.err.println("****************************************");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.err.println("****************************************");
			Connection conn1 = DriverManager.getConnection(url,props);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
/*
jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcps)(HOST=servername)(PORT=2484))(CONNECT_DATA=(SERVICE_NAME=servicename)))");

create user sslclient identified externally as 'CN=nms-vista1,C=US';

props.setProperty("javax.net.ssl.trustStore", "C:\\app\\Administrator\\product\\11.1.0\\db_1\\BIN\\certs");
props.setProperty("javax.net.ssl.trustStoreType","SSO");
props.setProperty("javax.net.ssl.keyStoreType","SSO");
props.setProperty("javax.net.ssl.keyStore", "C:\\app\\Administrator\\product\\11.1.0\\db_1\\BIN\\certs\\cwallet.sso");

(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=nms-vista1.zohocorpin.com)(PORT=2484))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=sslclient2))(SECURITY=(SSL_SERVER_CERT_DN=CN=nms-vista,C=US)))


(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=mcharpen-fr-vip.fr.oracle.Com)(PORT=1555))(ADDRESS=(PROTOCOL=TCP)(HOST=suppolite1-vip.fr.oracle.com)(PORT=1555))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=orcl.fr.oracle.com)(FAILOVER_MODE=(TYPE=SELECT)(METHOD=BASIC)(RETRIES=180)(DELAY=5))))
 * */
