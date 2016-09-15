/* $Id: ServerTestToolTest.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */
package test;

import java.util.*;
import java.rmi.*;
import java.net.*;

import com.adventnet.nms.server.testtool.*;

public class ServerTestToolTest
{

	public static void main(String arg[])
	{
		try
		{
		ServerTestToolAPI api = (ServerTestToolAPI)Naming.lookup("ServerTestToolAPI");
		System.out.println(api.executeMethod("loadInterface"));
		System.out.println(api.executeMethod("keyword" , 6));
		System.out.println(api.executeMethod("keyword" , "1" , "2"));
		}catch (Exception e)
		{
			e.printStackTrace();
		}
			
	}
}


