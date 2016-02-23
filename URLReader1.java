/*
 * @(#)URLReader.java	1.1 99/10/06
 *
 * Copyright 1995-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */
import java.net.*;
import java.io.*;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;
import java.security.Security;
import java.security.*;
import java.security.cert.*;
import javax.security.auth.*;
import javax.security.auth.x500.*;
import java.security.PublicKey;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.*;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.HostnameVerifier;
import java.security.cert.CertificateException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateExpiredException;



/* 
 * This example illustrates using a URL to access resources
 * on a secure site.
 *
 * To use Sun's reference implementation of HTTPS protocol, Please set 
 * the following Java system property:
 * 
 *    java.protocol.handler.pkgs = com.sun.net.ssl.internal.www.protocol
 *
 * If you are running inside a firewall, please also set the following
 * Java system properties to the appropriate value:
 *
 *   https.proxyHost = <secure proxy server hostname>
 *   https.proxyPort = <secure proxy server port>
 * 
 */

public class URLReader1 {
    public static void main(String[] args) throws Exception {
	// URL verisign = new URL("https://www.verisign.com/");
	// com.sun.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new MyV());

	 final  String  STR_TRUST_STORE ="javax.net.ssl.trustStore";

	try
	{
	    //register JSSE

	    //Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	    //Security.addProvider(new sun.security.provider.Sun());
	    //Security.addProvider(new com.sun.rsajca.Provider());

	    // Create a trust manager that validates certificate chains
	    // In the absence of this, The server certificate has to be installed
	    // in the keystore used by JavaWebStart JVM

            com.sun.net.ssl.internal.ssl.Provider prov = new com.sun.net.ssl.internal.ssl.Provider();
            sun.security.provider.Sun sun = new sun.security.provider.Sun();
            com.sun.rsajca.Provider prov1 = new com.sun.rsajca.Provider();

            Security.addProvider(prov);
            Security.addProvider(sun);
            Security.addProvider(prov1);




	    TrustManager[] trustAllCerts = new TrustManager[]
	    {
		new X509TrustManager()
		{
		    X509TrustManager sun;

		    {
			TrustManagerFactory tmf =
			    TrustManagerFactory.getInstance("SunX509", "SunJSSE");

			tmf.init((KeyStore)null);
			sun = (X509TrustManager)tmf.getTrustManagers()[0] ;


		    }
		    public java.security.cert.X509Certificate[] getAcceptedIssuers()
		    {
			return null;
		    }
		    public void checkClientTrusted( java.security.cert.X509Certificate[] certs, String authType)
		    {
			System.out.println("checkClientTrusted...");
		    }
		    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) throws CertificateException
		    {
			try
			{

			    System.out.println(" Checking the validity of the certificate..");
			    System.setProperty(STR_TRUST_STORE,System.getProperty("user.home") + "/ssl/server.truststore" );
				
			   System.out.println(System.getProperty(STR_TRUST_STORE));
			   certs[0].checkValidity();

			    X509Certificate[] x509 = sun.getAcceptedIssuers();

			    X500Principal rootxp = certs[0].getIssuerX500Principal();

			    X509Certificate root = null;

			    System.out.println(" length " + x509.length);

			    for(int i = 0; i < x509.length ; i++) {

				X500Principal xp = x509[i].getSubjectX500Principal();
				System.out.println("subjectx500Principal certs  -> " + xp);
				System.out.println("IssuerX500Principal certs  -> " + rootxp);
				if(xp.equals(rootxp)) {
				    root = x509[i];
				    System.out.println("Signed by -> " + xp);
				    System.out.println("Signed by -> " + root);
				}

			    }
			  
			   System.out.println(" root after  " + root); 
				
			    if(root != null) {
				PublicKey pubKey1 = (PublicKey)root.getPublicKey();
				certs[0].verify(pubKey1);
			    }
			    else {
				X500Principal xsub  = certs[0].getSubjectX500Principal();
				System.out.println("IssuerX500Principal rootxp  -> " + rootxp);
				System.out.println(" xsub   -> " + xsub);
				if(xsub.equals(rootxp)) {
				    System.out.println("Self signed cert...");
				}
				else {
				    System.err.println(" Invalid / Trail Certificate in place ...... ");
				    //throw new CertificateNotYetValidException();
				      //throw new CertificateException();
			    		//throw new CertificateException();
				}
			    }
			    System.out.println("Certificate Verified ");
			}
			catch(CertificateExpiredException cee)
			{
			    System.out.println(" Certificate Expired...");
			    throw cee;
			}
			catch(CertificateNotYetValidException cnve)
			{
			    cnve.printStackTrace();
			    System.out.println(" Certificate Not Yet Valid Exception");
			    throw cnve;
			}
			catch(CertificateException ce)
			{
			    System.out.println(" Certificate Exception ");
			    throw ce;
			}
			catch(Exception e)
			{
			    System.out.println(" General Exception ");
			    //e.printStackTrace();
			    throw new CertificateException(e.getMessage());
			}
		    }
		    public boolean isClientTrusted( java.security.cert.X509Certificate[] certs)
		    {
			System.out.println("isClientTrusted...");
			return true;
		    }
		    public boolean isServerTrusted( java.security.cert.X509Certificate[] certs)
		    {
			System.out.println("isServerTrusted...");
			return true;
		    }
		}
	    };

	    // Install the all-trusting trust manager so that the server certificate
	    // need not be installed in client keystore
	    try
	    {
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		HttpsURLConnection.setDefaultHostnameVerifier(
			new HostnameVerifier()
			{
			public boolean verify( String urlHostname, SSLSession session )
			{
			//System.out.println("URLHost:"+urlHostname+" SSLSession:"+session);
			return true;
			}
			});
	    }
	    catch (Exception e)
	    {
		System.out.println("Exception while installing the trust manager "+e);
		e.printStackTrace();
	    }
	}
	catch ( Exception e )
	{
	    System.out.println("Error in initializing SSL");
	    e.printStackTrace();
	}


	//URL verisign = new URL("https://vasus.india.adventnet.com:9091/");
	URL verisign = new URL("https://192.168.116.143:8442");
	BufferedReader in = new BufferedReader(
		new InputStreamReader(
		    verisign.openStream()));

	String inputLine;

	while ((inputLine = in.readLine()) != null)
	    System.out.println(inputLine);

	in.close();
    }
}
