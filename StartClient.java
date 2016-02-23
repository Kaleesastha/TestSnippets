package com.adventnet.nms.startnms;
import java.io.*;
import com.adventnet.nms.startclient.WebNMSClient;
import java.util.Date;

public class StartClient
{
	public static void main(String[] args)
	{	
		GenericBEAPI genBEApi = (GenericBEAPI)Naming.lookup("//localhost/GenericBEAPI"); 

		try{

			while (genBEApi == null)
			{
				Thread.sleep(3000);
				System.err.println("genBEApi is NULL!!"+new Date(System.currentTimeMillis()));
			}

			while (genBEApi.isReadyForConnection() == false)
			{
				System.err.println("In while wait"+new Date(System.currentTimeMillis()));
				Thread.sleep(3000);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.err.println("In last of StartClient constructor "+new Date(System.currentTimeMillis()));
		WebNMSClient.main(args);
   }

}
