import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.KeyManagerFactory;
import java.security.KeyStore;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import  java.io.FileInputStream;

public
class EchoServer {
    public
            static
    void
            main(String[] arstring) {
        try {
		KeyStore             objKeyStore         = KeyStore.getInstance("JKS");
		KeyManagerFactory    objKeyMngrFactory   = KeyManagerFactory.getInstance("SunX509");
		SSLContext           objSSLContext       = SSLContext.getInstance("TLSv1.2");

		objKeyStore.load(new FileInputStream(System.getProperty("javax.net.ssl.keyStore")),System.getProperty("javax.net.ssl.keyStorePassword").toCharArray());
		objKeyMngrFactory.init(objKeyStore,System.getProperty("javax.net.ssl.keyStorePassword").toCharArray());
		objSSLContext.init(objKeyMngrFactory.getKeyManagers(),null,null);
		SSLServerSocketFactory m_sslSockFactory  = objSSLContext.getServerSocketFactory();
		SSLServerSocket sslserversocket = (SSLServerSocket)m_sslSockFactory.createServerSocket(9999);
		//sslserversocket.setEnabledCipherSuites(enabledCipherSuites);
		String protocol = arstring[0];
		String[] protocols={protocol};
		sslserversocket.setEnabledProtocols(protocols);

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
