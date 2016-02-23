package com.adventnet.nms.example.security.authorization;
import com.adventnet.security.authorization.*;
import com.adventnet.nms.util.*;
public class AddOperation implements RunProcessInterface
{
	public void callMain(String args[])
	{
		try{
			AuthorizationAdmin authAdmin = (AuthorizationAdmin) NmsUtil.getAPI ("NmsAuthAdminAPI");
			while(authAdmin == null)
			{
				Thread.sleep(5000);
			
			}
			System.err.println("Got a reference to NmsAuthAdminAPI");

			authAdmin.addOperation( "NewOperation", "User Administration");// Adding NewOperation to an already existing parent operation (User Administration)
			System.err.println("Added NewOperation to User Administration");
			authAdmin.addOperation( "NewOperation2",null);// API Call.
			System.err.println("Added NewOperation2" );
			System.err.println("Result of adding newUser to newGroup with password: password@3: is"+ authAdmin.createUser("newUser", "newGroup", "password@3"));
			System.err.println("Result of assigning newUser to Users group : "+ authAdmin.assignUserToGroup("newUser", "Users"));
			System.err.println("Result of assigning NewOperation to default newGroup View : "+ authAdmin.assignOperationToView("NewOperation", "default newGroup View"));

		}
		catch(Exception e)
		{
			e.printStackTrace();
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
