package test;

import com.adventnet.nms.util.*;
import com.adventnet.security.authorization.AuthorizationAdmin;
import java.util.HashSet;
public class GetAllUsers implements RunProcessInterface
{
	public void callMain(String args[])
	{
		try 
		{
			HashSet userNames=null;
			AuthorizationAdmin authAdmin=null;
			while(authAdmin==null)
			{
				authAdmin = (AuthorizationAdmin) NmsUtil.getAPI ("NmsAuthAdminAPI");  //To get the AuthorizationAdmin handle.
				Thread.sleep(1000);
			}
			System.out.println ( "Successfully got the handle for AuthorizationAdmin");
			userNames = authAdmin.getAllUserNames();  //To get all user names from database
			System.out.println(userNames);
		}
		catch (Exception remoteException) 
		{
			System.out.println ( "Error in getting the handle for AuthorizationAdmin"); 
		}

	}
	public boolean isInitialized()
	{
		return true;
	}
	public void shutDown()
	{

	}
}

