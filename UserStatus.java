package test;
import com.adventnet.security.authorization.*;
import com.adventnet.nms.authentication.*;
import com.adventnet.nms.util.*;
import java.io.*;
import java.util.*;
import java.rmi .*;
public class UserStatus
{
  /* public void callMain(String args[])
   {	
	try{
	   AuthorizationAdmin authAdmin = (AuthorizationAdmin) NmsUtil.getAPI ("NmsAuthAdminAPI");   
	Hashtable table = authAdmin.getAllAttributes("user");
	System.out.println("The list is =====> " + table.toString());	
	}
	catch(Exception ex)
	{
		System.err.println("Exception in NmsAuthAdminAPI ");
		ex.printStackTrace();
	}
   }
    public boolean isInitialized()
    {
        return true;
    }
    public void shutDown()
    {
	    System.out.println("_______ Shutdown!!");
    }*/
	   //AuthorizationAdmin authAdmin = (AuthorizationAdmin) NmsUtil.getAPI ("NmsAuthAdminAPI");   
	   public static void main(String args[])
	   {
	   try{
	   AuthorizationAdmin authAdmin = (AuthorizationAdmin) Naming. lookup("//localhost/NmsAuthAdminAPI");
	   AuthorizationEngine authEngine = (AuthorizationEngine) Naming. lookup ("//localhost/NmsAuthEngineAPI");
	   Hashtable table = authAdmin.getAllAttributes(args[0]);
	   System.out.println("status = " + (String)authAdmin.getAllAttributes(args[0]).get("status"));
	   System.out.println("The list is =====> " + table.toString());	
	   System.out.println("==>"+authEngine.isAuthorized(args[0], args[1]));
	   }
   	catch(Exception ex)
	{
		System.err.println("Exception in NmsAuthAdminAPI ");
		ex.printStackTrace();
	}
		   
	   }
}

