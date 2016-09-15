package test;

import java.util.*;
import com.adventnet.nms.mapdb.MapAPI;
import java.rmi.*;
public class UpdateMapSymbol
{
	//declaration of panes and other symbols
	public static void main(String a[])
	{
		if(a.length != 3)
			System.err.println("Usage: test.UpdateMapSymbol <hostName> <MapName> <MapSymbol>");
		try{
			MapAPI api=(MapAPI)Naming.lookup("rmi://"+a[0]+"/MapAPI");
			Properties p = new Properties();
			p.setProperty("label", "ChangedName00000");
			p.setProperty("name",a[2]);
			api.updateSymbol(a[1],p);
			System.err.println("Now changed props are: "+api.getPropertiesOfObject(a[1]));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
