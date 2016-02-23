//import java.util.*;

package com.adventnet.nms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
public class Test1{
	public static void main(String[] s){
		String host = "localhost";
		String port = "9090";
		int retries = 3;
		int i=0;
		//default retries will be 300
		//so with this retries by sleeping 200ms for every failure, the default timeout will be 60 secs (200*300=60000 millisecs)

		strProtocol   = "http";
		URL  url = new URL(strProtocol+"://" + host + ":" + port + "/servlets/ConnectionCheckServlet");//No Internationalisation
		BufferedReader in=null;
		try
		{
			in = new BufferedReader (new InputStreamReader (url.openStream()));
			String line = null;
			while ((line = in.readLine()) != null)
			{
				if(line.indexOf("Successfully") != -1)//No Internationalisation
				{
					// if we receive token like "successfully" we assume that this//No Internationalisation
					// is a success message and print the success message. This
					// is basically done to avoid message "null" in Linux PC's//No Internationalisation
					break;
				}
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
}
