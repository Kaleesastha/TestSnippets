package com.adventnet.nms.startnms;
import java.io.*;
import com.adventnet.nms.startclient.WebNMSClient*;
public class StartNMS  
{
	private static Wield wi = null;
   public static void main(String[] args)
   {	
	   GenericBEAPI genBEApi = (GenericBEAPI)Naming.lookup("//localhost/GenericBEAPI"); 

	   try{

		   while (genBEApi == null)
		   {
			   genBEApi = GenericBEAPIImpl.getInstance();//)NmsUtil.getAPI("GNenericBEAPI");
			   Thread.sleep(100);
			   System.err.println("genBEApi is NULL!!"+new Date(System.currentTimeMillis()));
		   }

		   while (genBEApi.isReadyForConnection() == false)
		   {
			   System.err.println("In while wait"+new Date(System.currentTimeMillis()));
			   Thread.sleep(1000);
		   }
	   }
	   catch(Exception e)
	   {
	   }
	   System.err.println("In last of StartNmsJdbc constructor "+new Date(System.currentTimeMillis()));
   }

}

