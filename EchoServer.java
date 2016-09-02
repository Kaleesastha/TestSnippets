/* USAGE:
//Library/Java/JavaVirtualMachines/jdk1.8.0_66.jdk/Contents/Home/bin/java -Dssl.Protocol="TLSv1.2"  -Djavax.net.ssl.keyStore=server.keystore -Djavax.net.ssl.keyStorePassword="webnms" EchoServer
 */
import javax.net.ssl.*;
import java.security.KeyStore;
import java.io.*;

public class EchoServer {
    public static void main(String[] arstring) {
        try {
		String keyStoreType = "JKS";
		String algorithm = "SunX509";
		String protocol = "TLSv1";
		String temp = System.getProperty("javax.net.ssl.keyStoreType");
		if(temp != null && !temp.trim().equals("")){
			keyStoreType=temp;
		}

		temp = System.getProperty("ssl.KeyManagerFactory.algorithm");
		if(temp != null && !temp.trim().equals("")){
			algorithm=temp;
		}

		temp = System.getProperty("ssl.Protocol");
		if(temp != null && !temp.trim().equals("")){
			protocol=temp;
		}

		System.err.println("keyStoreType, algorithm, protocol: "+keyStoreType+", "+algorithm+", "+protocol);
		KeyStore             objKeyStore         = KeyStore.getInstance(keyStoreType);
		KeyManagerFactory    objKeyMngrFactory   = KeyManagerFactory.getInstance(algorithm);
		SSLContext           objSSLContext       = SSLContext.getInstance(protocol);
		objKeyStore.load(new FileInputStream(System.getProperty("javax.net.ssl.keyStore")),System.getProperty("javax.net.ssl.keyStorePassword").toCharArray());
		objKeyMngrFactory.init(objKeyStore,System.getProperty("javax.net.ssl.keyStorePassword").toCharArray());
		objSSLContext.init(objKeyMngrFactory.getKeyManagers(),null,null);
		SSLServerSocketFactory m_sslSockFactory  = objSSLContext.getServerSocketFactory();
		String port = "9999";
		if(arstring.length !=0){
			port = arstring[0];
		}
		int portNo = Integer.parseInt(port);
		SSLServerSocket sslserversocket = (SSLServerSocket)m_sslSockFactory.createServerSocket(portNo);
		//String[] enabledCipherSuites = {"SSL_RSA_WITH_RC4_128_MD5", "SSL_RSA_WITH_RC4_128_SHA"};
		//String[] enabledCipherSuites = {"TLS_RSA_WITH_AES_128_CBC_SHA","TLS_RSA_WITH_AES_256_CBC_SHA"};
		//sslserversocket.setEnabledCipherSuites(enabledCipherSuites);

		String[] b = sslserversocket.getEnabledCipherSuites();             
		if (b != null)
		{
			StringBuffer result = new StringBuffer();
			if (b.length > 0) {
				result.append(b[0]);
				for (int i=1; i<b.length; i++) {
					result.append("\n"); // No I18N
					result.append(b[i]);
				}
			}
			System.out.println("enabled CipherSuites "+result.toString()); // No I18N
		}    
		else
			System.out.println("enabled CipherSuites is null "); // No I18N
		SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();

            InputStream inputstream = sslsocket.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

            String string = null;
            while ((string = bufferedreader.readLine()) != null) {
                System.out.println(string);
                System.out.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
