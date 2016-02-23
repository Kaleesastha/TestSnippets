package com.adventnet.nms.example.security.authorization;
import com.adventnet.security.authorization.*;
import com.adventnet.nms.util.*;
import java.rmi.Naming;
public class SetDescName
{
	public static void main(String args[])
	{
		try{
			AuthorizationAdmin authAdmin = (AuthorizationAdmin) Naming.lookup("//localhost/NmsAuthAdminAPI");
			System.err.println("Got a reference to NmsAuthAdminAPI");

			System.err.println("Descriptive name of "+args[0] +" before setDescriptiveName is called" +authAdmin.getDescriptiveName(args[0]));
			authAdmin.setDescriptiveName( args[0], args[1]);// Setting descriptive name already existing user
			System.err.println("Descriptive name of "+args[0] +" after setDescriptiveName is called" +authAdmin.getDescriptiveName(args[0]));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
