package test.radius;

import com.adventnet.security.authentication.*;
import com.adventnet.security.authentication.RadiusUtil;
import java.io.*;

public class DummyAuth
{

public DummyAuth()
{

}

boolean returned=true;
RadiusUtil ru = null;

public boolean dummyAuth()
{
        try{
	    
	    ru =  RadiusUtil.getInstance();
	    //.setRadiusServerIP("192.168.112.77");
	    System.out.println(ru.getRadiusServerIP());
	    System.out.println(ru.getSecretKey());

	if(returned = true)    
	{
	  returned = ru.authenticateUser("dummy","dummy");
          Thread.sleep(10000);
	  System.out.println("radius server status " + returned);
	}else
	{
	System.out.println("radius server status false");
	}
	
        }catch(Exception e)
        {
	System.out.println("radius server status false");
        e.printStackTrace();
	return false;
        }
	return returned;
}




public static void main(String args[])
{
	try{
	DummyAuth da = new DummyAuth();
	boolean d=true;
	while(d)	
	{
	da.dummyAuth();
	}
	  
	}catch(Exception e)
	{
	e.printStackTrace();
	}


}
}
