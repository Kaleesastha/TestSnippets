package test;

import java.io.*;
import java.util.*;
import java.net.*;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import org.w3c.dom.*;
//import HTTPClient.HTTPResponse;
import com.adventnet.security.AuthUtil;
import com.adventnet.security.authentication.AuthenticationException;
import com.adventnet.nms.util.BrowserControl;


public class AuthenticateClient{
	public static void main(String args[])
	{
		String userName = args[0];
		String password = args[1];
		String hostAddress = args[2];
		String sessionId =  authenticateNMS(userName, password, hostAddress);
		System.err.println("sessionId : "+ sessionId);
		String urlToOpen = "http://"+hostAddress+":9090/mainLayout.do;jsessionid="+sessionId+"?selectedNode=Home&selectedTab=Home";
		BrowserControl.displayURL(urlToOpen);
	}
	public static String authenticateNMS(String userName, String password, String hostAddress)
	{
		String sessionId = null;
		DataInputStream dis   = null;
		URL servletUrl = null;
		try
		{
			String s;
			//String messageDigest = null;
			BufferedReader reader = null;
			URLConnection urlc = null;
			String challenge = null;
			/*URL challengeUrl  = new URL("http://"+hostAddress+":9090"+"/servlets/GetChallengeServlet");
				urlc = challengeUrl.openConnection();
				urlc.setRequestProperty("userName",userName);//No I18N
				urlc.setDoInput(true);
				urlc.setDoOutput(true);
				urlc.setRequestProperty("Content-type","application/x-www-form-urlencoded");//No I18N
				dis   = new DataInputStream(urlc.getInputStream());
				reader = new BufferedReader(new InputStreamReader(dis));
				if( (s = reader.readLine()) != null && !s.equals("No such user"))
				{ 
					int colonIndex = s.indexOf(";");
					int equalIndex = s.indexOf("=");
					sessionId = s.substring(equalIndex+1,colonIndex);
					challenge = s.substring(s.indexOf("=",colonIndex)+1);
					try
					{
						challenge = URLDecoder.decode(challenge);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					System.err.println("UnAuthorised");	
				} 
				System.err.println("challenge is : "+challenge);
				try
				{
					if(challenge != null)
					{
						messageDigest = AuthUtil.getChallengeKey(userName,password,challenge);
						messageDigest = URLEncoder.encode(messageDigest);
					}
				} 
				catch(AuthenticationException ae){ae.printStackTrace();}*/
			//servletUrl = new URL("http://"+hostAddress+":9090/servlets/AuthenticationServlet;jsessionid="+sessionId+"?hostaddress=" + hostAddress );//No I18N
			servletUrl = new URL("http://"+hostAddress+":9090/servlets/AuthenticationServlet;password=dummy"+"?hostaddress=" + hostAddress );//No I18N
			System.out.println("servletUrl  : "+servletUrl);
			urlc = servletUrl.openConnection();
			//urlc.setRequestProperty("validate","True"); 
			urlc.setRequestProperty("userName",userName);//No I18N  
			/*if(messageDigest != null)
			{
				System.err.println("setting messageDigest "+messageDigest);
				urlc.setRequestProperty("messageDigest",messageDigest);//No I18N
			}*/

			urlc.setDoInput(true);
			urlc.setDoOutput(true);
			urlc.setRequestProperty("Content-type","application/x-www-form-urlencoded");//No I18N

			dis   = new DataInputStream(urlc.getInputStream());
			reader = new BufferedReader(new InputStreamReader(dis));
			while((s = reader.readLine()) != null)
			{  
				System.err.println("s ==> "+s);
				if( !s.equalsIgnoreCase("Success") )
				{
					System.err.println("UnAuthorised");
				}
			}
		}catch(MalformedURLException ee){ee.printStackTrace();}
		catch(UnknownHostException ee){ee.printStackTrace();}
		catch(IOException ee){ee.printStackTrace();}
		finally
		{
			if(dis!=null)
			{
				try
				{
					dis.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		System.err.println("=== returning sessionId ===");
		return sessionId;
	}
}
