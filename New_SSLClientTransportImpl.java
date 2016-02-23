//$Id: New_SSLClientTransportImpl.java,

/*
 * @(#)New_SSLClientTransportImpl.java
 * Purpose - This class is the interface to the  Client for the SSL mode of communication
 */

package com.webnms.client.transport;

import com.adventnet.management.transport.LogInterface;
import com.adventnet.management.transport.SSLSessionTransportImpl;
import com.adventnet.management.transport.SessionTransportProvider;
import com.adventnet.management.transport.TransportException;
import com.adventnet.management.transport.TransportProvider;
import java.applet.Applet;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import com.adventnet.nms.util.SSLUtil;


/** 
 * This class is the client interface for the SSL mode of communication. This classs handles 
 * the downloading of truststore file and signing the class(jar) to remove the java.policy 
 * dependency. The transportprovider is defined in the conf file transportprovider.conf under conf dir.  
 */
public class New_SSLClientTransportImpl extends Thread implements TransportProvider,Runnable
{
	private final static String  STR_TRUST_STORE ="javax.net.ssl.trustStore";

	//stores the  Server name
	private  String      m_strHostName          = null; 

	//stores the name of the truststore file
	private  String      m_strTrustStoreFile    = null;

	//stores the port number on which the SSLSocket will be created
	private  int         m_nPortNumber          = -1;

	//stores the instance of the SSLSocket
	private SSLSocket   m_sslClientSocket      = null;

	//stores the instance of the SSLSocketFactory
	private SSLSocketFactory m_sslSockFactory  = null;

	//stores the applet parameters
	private  Object [] params;

	/**
	 * creates an instance of <code>New_SSLClientTransportImpl</code>
	 */
	
	public New_SSLClientTransportImpl()
	{
		
	}	
	public New_SSLClientTransportImpl(Object [] sslparams)
	{
		params = sslparams;
	}

	/**
	 * This method dynamically downloads the truststore file from the  Server 
	 *  Server and puts in the user home of the  client using the servlet
	 * <code>DownloadTrustStore</code>.
	 *
	 * @param params parameters that are used for creating the session
	 *
	 * @throws TransportProvider in case of an error during initializing the 
	 *							 transport interface
   	 */
	public void init(java.lang.Object[] params)	throws TransportException
	{
		Applet objTempApplet    = null;
		String strTrustProperty = null;

		try
		{
			objTempApplet = (Applet)params[0];								//applet parameters

			if(objTempApplet == null)
			{
				return;
			}
			m_strHostName  = objTempApplet.getCodeBase().getHost();			//gets the  Server host from the applet to download the file
			String strProto = objTempApplet.getCodeBase().getProtocol();	//gets the protocol from the applet to download the file

			System.out.println("System Name " + m_strHostName);

			int  port  = objTempApplet.getCodeBase().getPort();				//gets the port from the applet to download the file
			m_strTrustStoreFile  =((String)params[6]).trim();

			try
			{
				//Servlet URL to download the server.truststore from the  Server
				URL url = new URL(strProto+"://" + m_strHostName + ":" + port + "/servlets/DownloadTrustStore");
				URLConnection urlc = null;
				if(strProto.equalsIgnoreCase("https"))
				{
					urlc = SSLUtil.getHttpsURLConnection(url);		//download through HTTPS, Secure mode	
				}
				else
				{
					urlc = url.openConnection();							//download through HTTP
				}

				System.out.println("DEBUG: urlc->"+urlc);
				urlc.setDoInput(true);
				urlc.setDoOutput(true);
				urlc.setRequestProperty("Content-type","application/x-www-form-urlencoded");

				BufferedInputStream bis = new BufferedInputStream(urlc.getInputStream());

				String userHome = System.getProperty("user.home");								//gets the user home of the  Client
				FileOutputStream fos = new FileOutputStream(userHome + "/server.truststore");	
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				int by;
				while ((by = bis.read ()) != -1)
				{
					bos.write(by);																//downloads the server.truststore
				}
				if (bis != null) 
				try 
				{
					bis.close(); 
				}
				catch (Exception e){
				}
				if (bos != null) 
				try 
				{ 
					bos.close(); 
				}
				catch (Exception e){
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception while downloading the truststore file, Check if the  server is running or server.truststore file is present under <_HOME>/conf ");
				e.printStackTrace();
			}
			m_nPortNumber  = Integer.parseInt(((String)params[1]).trim());
			strTrustProperty = System.getProperty(STR_TRUST_STORE);
		
			//if(strTrustProperty == null)
			{
				String strTrustFileName = "server.truststore";
			
				System.setProperty(STR_TRUST_STORE,System.getProperty("user.home") + "/" + strTrustFileName);
				System.setProperty("javax.net.ssl.trustStorePassword","webnms");
			}
			System.out.println("truststore here is"+System.getProperty(STR_TRUST_STORE));
			m_sslSockFactory     = (SSLSocketFactory) SSLSocketFactory.getDefault();
		}
		catch(Exception exce)
		{
			System.out.println("some error over here in this class");
			exce.printStackTrace();
			throw new TransportException(exce.getMessage());
		}
	}

	/**
	 * The transportprovider opens an ssl session using this method
	 *
	 * @param params parameters that are used for creating the client session
	 *
	 * @return SessionTransportProvider reference to the SessionTransportProvider object 
	 *									that represents the client session	
	 *
	 * @throws transportprovider in case of an error during opening the transport interface
	 */
	public SessionTransportProvider open(java.lang.String[] params)throws TransportException
	{
		
		try{
		connectSSLSocket();
		return new SSLSessionTransportImpl(m_sslClientSocket,null);
		}
		catch(Exception e)
		{
			System.out.println("it is here");
			e.printStackTrace();
			return null;
		}

			

	
	}	

	/**
	 * Method to close the resources that are used for
	 * secure communication between  Client and Server.
	 *
	 * @throws TransportException in case of an error during closing
	 */
	public void close()throws TransportException
	{
		try
		{
			Properties propsSystem = System.getProperties();		//gets the System Properties
			propsSystem.remove(STR_TRUST_STORE);

			if( m_sslClientSocket != null)
			{
				m_sslClientSocket.close();
				m_sslSockFactory  = null;
				m_sslClientSocket = null;
			}
		}
		catch(Exception exce)
		{
			System.out.println("Exception while closing socket");
			throw new TransportException(exce.getMessage()); 
		}
	}

	//dummy implementation
	public void setLogInterface(LogInterface log){
	}

	/**
	 * This method creates the SSLSocket which is used for communicating
	 * with the  Server through secure mode.
	 *
	 * @return <code>SSLSocket</code> client socket
	 *
	 * @throws TransportException in case of an error during opening 
	 * 							  the transport interface
	 */
	public SSLSocket connectSSLSocket() throws TransportException
	{
		try
		{
			m_sslSockFactory     = (SSLSocketFactory) SSLSocketFactory.getDefault();	 
			System.out.println("ssl facotry in connectSSLSocket"+ m_sslSockFactory);
			System.out.println(m_strHostName + " " + m_nPortNumber);
			m_sslClientSocket = (SSLSocket)m_sslSockFactory.createSocket(m_strHostName,m_nPortNumber);
			System.out.println("this print should come");
			m_sslClientSocket.startHandshake();
			return m_sslClientSocket;
		}
		catch(Exception exec)
		{
			System.out.println("Exception over here");
			exec.printStackTrace();
			throw new TransportException(exec.getMessage());
		}
		
	}

	/**
     * Provides the required permission to access the signed class
     * from non signed class.
     */
	public void run()
	{
		AccessController.doPrivileged(new PrivilegedAction() {

				public Object run()
				{
					try
					{
						init(params);
					}
					catch(Exception e)
					{
						System.err.println("Exception in loading method init");
						e.printStackTrace();
					}
					return null;
				}
		});
	}
}


