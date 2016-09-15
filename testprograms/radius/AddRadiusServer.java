package test.radius;

/**
 * AddRadiusServer.java
 *
 *
 * Created: Mon Mar 15 09:25:17 2004
 *
 * @author <a href="mailto:vasus@adventnet.com "</a>
 * @version
 */

import java.io.*;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.ManagedObject;
import java.util.*;
import com.adventnet.nms.util.*;
import test.radius.*;


public class AddRadiusServer 
{

public TopoAPI topoApi;
public test.radius.RadiusServer rs;

    public AddRadiusServer()
    {
	init();        
    }
    

public void init()
{
	try{
	topoApi = (TopoAPI) NmsUtil.getAPI ("TopoAPI");
	}catch(Exception e)
	{
	e.printStackTrace();
	}
}



public void addRadiusServer(String serverName,String ipAddress,int authPort,int accPort,String secret)
{

	try{
		
	checkAndDeleteObject(serverName);

	String aPort = Integer.toString(authPort);
	String acPort = Integer.toString(accPort);
		

        Properties p = new Properties();
	p.put("ipAddress",ipAddress);
        p.put("authport",aPort);
        p.put("acctport",acPort);
        p.put("sharedsecret",secret);


        rs = new test.radius.RadiusServer();
	rs.setName(serverName);
	rs.setTester("ping");
	rs.setType("RadiusServer");
	rs.setPollInterval(30);
        rs.setProperties(p);
	
        boolean b = topoApi.addObject(rs);
	System.out.println("Radius Server object added successfully  ");


        }catch(Exception error)
        {
	System.err.println("Exception while adding object " );
        error.printStackTrace();
        }

}

public void checkAndDeleteObject(String serverName)
{
	try{
		if (topoApi.isManagedObjectPresent(serverName))
		{
		ManagedObject mo = new ManagedObject();
		mo.setName(serverName);
		topoApi.deleteObject(mo);
		
		}
	   }catch(Exception error)
	   {
		System.err.println("Exception while deleting object");
		error.printStackTrace();
	   }
}		
}

