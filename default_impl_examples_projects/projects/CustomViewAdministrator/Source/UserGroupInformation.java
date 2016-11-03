//$Id: UserGroupInformation.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $

package com.adventnet.nms.tools.CustomView;

import com.adventnet.security.authorization.*;
import java.util.*;
import java.rmi.*;
import javax.swing.*;
public class UserGroupInformation
{
	public AuthorizationAdmin userApi;
	public UserGroupInformation()
	{
		try
		{
		String api = "//localhost/NmsAuthAdminAPI";
			userApi = (AuthorizationAdmin)Naming.lookup(api);
		}
		catch(Exception ex)
		{
			System.out.println("Exception\nMessage: " + ex.getMessage());	
			
		}
	}	
	
	public Vector getAllGroups()
	{
        try
		  {
            Vector groups=new Vector();
            groups=userApi.getAllGroups();

				return groups;
		  }
		  catch(Exception x)
		  {
				System.out.println("Exception\nMessage: " + x.getMessage());	
				return null;
		  }

			  
	}
	public Vector getAllUsers()
	{
        try
		  {
        		Vector getusers=new Vector();
            Vector groups=getAllGroups();
            for(int i=0;i<groups.size();i++)
            {
                getusers.addElement(userApi.getUsers(groups.elementAt(i).toString()));
				}
		  	 	return getusers;
		  }
		  catch(Exception ex)
		  {
				System.out.println("Exception\nMessage: " + ex.getMessage());	
				return null;
		  }
	}
	public Vector getUsersForGroup(String group)
	{
		try
		{
			return userApi.getUsers(group);
		}
		catch(Exception e)
		{
			System.out.println("Exception while getting users list for a particular group"+e.getMessage());
			return null;
		}
	}
	
}




