package test;
import com.adventnet.nms.smtp.SmtpMailer;
import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
public class MySSLEMail
{
	public static void main(String args[])
	{
		String [] toArr={"mfernacz@mrv.com"};
		String fromAddr = "mfernacz@mrv.com";
		String smtphost="pd51013.outlook.com"; 
		System.setProperty("smtp.sslport","587");
		System.setProperty("smtp.secure","true");
		String username="mfernacz@mrv.com";
		String password = "xxxxxxxxxxxx"; //This is the output of Encrypt password script in NMS_HOME/bin/admintools

		TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
			public X509Certificate[] getAcceptedIssuers(){return null;}
			public void checkClientTrusted(X509Certificate[] certs, String authType){}
			public void checkServerTrusted(X509Certificate[] certs, String authType){}
		}};
		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			/*SendEMail call without secure mail - with UserName & Password*/
			SmtpMailer sendEMail = new SmtpMailer(smtphost, fromAddr,toArr, "SSL Mail-SendEMail - UserName Password set",null,username,password,true);
			sendEMail.sendMessage("SSL Test mail without file attachement - but with UserName & Password");
			System.err.println("send Email Notification called completely!!");
		}
		catch(Exception e){e.printStackTrace();} 
	}
}
