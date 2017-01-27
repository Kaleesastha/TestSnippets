import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;

public class EchoClient {
	public static void main(String[] arstring) {
		try {
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(arstring[0],Integer.parseInt(arstring[1]));
			//String[] enabledCipherSuites = {"TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "SSL_RSA_WITH_RC4_128_MD5", "SSL_RSA_WITH_RC4_128_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA", "SSL_RSA_WITH_3DES_EDE_CBC_SHA"};
			String[] enabledCipherSuites = {"TLS_RSA_WITH_AES_128_CBC_SHA","TLS_RSA_WITH_AES_256_CBC_SHA"};
			//sslsocket.setEnabledCipherSuites(enabledCipherSuites);
			String protocol = arstring[2];
			String[] protocols={protocol};

			String[] b = sslsocket.getEnabledCipherSuites();             
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
			/*System.out.println("Registering a handshake listener...");
			sslsocket.addHandshakeCompletedListener(new MyHandshakeListener());

			System.out.println("Starting handshaking...");*/
			sslsocket.startHandshake();
			System.out.println("Just connected to " + sslsocket.getRemoteSocketAddress());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}

class MyHandshakeListener implements HandshakeCompletedListener {
	public void handshakeCompleted(HandshakeCompletedEvent e) {
		System.out.println("Handshake succesful!");
		System.out.println("Using cipher suite: " + e.getCipherSuite());
	}
}
