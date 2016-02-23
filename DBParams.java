package com.adventnet.nms.startnms;
import java.io.*;
import java.util.*;
import java.net.*;
import java.rmi.Remote;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import com.adventnet.nms.startnms.*;
import com.adventnet.tools.prevalent.*;
import com.adventnet.nms.util.PureUtils

public class DBParams
{
   public static void main(String[] args)
   {	
	//String NmsDir = new String(PureUtils.rootDir);
	File file = new File(PureUtils.rootDir + "/conf/database_params.conf");
	private DBParamsParser parser = null;
	parser = DBParamsParser.getInstance(file);
        String url = parse.getURL();
	System.out.println("The URL is: " + url);
        String user = parse.getUserName();
	System.out.println("The Username is: " + user);
        String driver = parse.getDriverName();
	System.out.println("The Drivername is: " + driver);
        String passwd = parse.getPassword();
	System.out.println("The Password is: " + passwd);
   }

}
