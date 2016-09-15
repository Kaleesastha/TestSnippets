import com.adventnet.nms.util.*;
import com.adventnet.util.*;
import java.util.*;
import java.rmi.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.*;

import com.comp.pmserver.dataobjects.uo.BaseUserObject;
import com.comp.pmserver.dataobjects.uo.stpd.VlanPortTableUO; 

public class TestUserStorageAPI
{

	public static void main(String arg[])
	{
		if(arg == null || arg.length != 1)
		{
			System.out.println("Usage : java  TestRelational  moname_for_add  moname_for_delete  moname_for_fetch  moname_for_update ");	
			System.exit(1);
		}
		UserStorageAPI topo = null;
        System.out.println("Outside try");
		try
		{
            System.out.println("Inside Try");
			topo = (UserStorageAPI) Naming.lookup ("//localhost/UserStorageAPI");
			System.out.println ( "Successfully got the handle for UserStorageAPI");
		}
		catch (Exception remoteException) 
		{ 
            remoteException.printStackTrace();
			System.out.println ( "Error in getting the handle for UserStorageAPI"); 
		}
		try
		{
            BaseUserObject  device = new BaseUserObject();
			String deviceName = arg[0];
			device.setName(deviceName);
            device.setClassName("TEST_CLASSNAME");
           
			// SET SOME PROPERTIES OF   VlanPortTableUO  and BaseUserObject
			
			// FOR  ADD
            System.out.println("Before Add");
            if (device == null) System.out.println("Device is null");
            else System.out.println("Device is not null");
            System.out.println("getName : " + device.getName());
			topo.addObject(device,deviceName);
            System.out.println("After Add");




			
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e);
			e.printStackTrace();
		}
	}
}
