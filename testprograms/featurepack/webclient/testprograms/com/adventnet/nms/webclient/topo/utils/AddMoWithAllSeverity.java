//$Id: AddMoWithAllSeverity.java,v 1.1 2003/11/25 14:20:19 srikrishnan Exp $
package com.adventnet.nms.webclient.topo.utils;

import com.adventnet.nms.topodb.*;

import java.util.*;
import java.rmi.*;
import com.adventnet.nms.util.*;

public class AddMoWithAllSeverity
{

	private TopoAPI topoAPI;
	private String hostName;

	public AddMoWithAllSeverity( )
	{
		System.out.println("Default Construtor Called"); 
	}

	public void updateMO() throws Exception
	{
		this.hostName = "localhost";
		this.topoAPI = getTopoAPI();
		

		ManagedObject mo = new ManagedObject();
		for ( int j = 1; j < 7 ; j++ )
		{
			mo.setName("AddMyObject"+j);
			Properties prop = new Properties();
			prop.setProperty("type","Node");
			prop.setProperty("displayname","testMO"+j);
			prop.setProperty("status", Integer.toString(+j));
			if( j == 6 )
			{
			prop.setProperty("managed","false");
			}
			mo.setProperties(prop);	
			topoAPI.addObject(mo,false,true);
		}
	}

	public TopoAPI  getTopoAPI()
	{
		TopoAPI topoAPI;

		topoAPI = (TopoAPI ) NmsUtil.getAPI( "TopoAPI" );
		if ( topoAPI != null ) {
			return topoAPI;
		} else {
			String apiString = "//" + hostName + "/TopoAPI";
			System.out.println( " API String = " + apiString );
			try {
				topoAPI = ( TopoAPI ) Naming.lookup( apiString );
			} catch ( NotBoundException e ) {
				e.printStackTrace();
			} catch ( RemoteException e ) {
				e.printStackTrace();
			}catch (Exception ion){
			ion.printStackTrace();
			}
			return topoAPI;
		}
	}


	public static void main(String args[])
	{
		AddMoWithAllSeverity addmo = new AddMoWithAllSeverity();
		try
		{
		addmo.updateMO();
		}
		catch (Exception ion)
		{
			ion.printStackTrace();
		}
		//String nameValue = args[0];

		//addmo.getTopoAPI();
		//addmo.updateMO(args[0]);
	}
}
