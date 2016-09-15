//$Id: TopoTestUtils.java,v 1.3 2003/10/22 03:06:30 srikrishnan Exp $

/**
 * @(#)TopoTestUtils.java
 *
 *  Copyright (c) 2003 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET,
 * INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.
 */

package com.adventnet.nms.webclient.topo.utils;

import java.util.Properties;
import java.util.Vector;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.ManagedObject;

public class TopoTestUtils
{

	/**
	 * Get the TopoAPI handle
	 * @return handle to TopoAPI
	 */
	public static TopoAPI getTopoAPI()
	{
		TopoAPI topoAPI;

		String hostName = "localhost";

		topoAPI = ( TopoAPI ) NmsUtil.getAPI( "TopoAPI" );
		if ( topoAPI != null ) {
			return topoAPI;
		} else {
			String apiString = "//" + hostName + "/TopoAPI";
			try {
				topoAPI = ( TopoAPI ) Naming.lookup( apiString );
			} catch ( NotBoundException e ) {
				e.printStackTrace();
			} catch ( MalformedURLException e ) {
				e.printStackTrace();
			} catch ( RemoteException e ) {
				e.printStackTrace();
			}
			return topoAPI;
		}
	}

	public static String getObjectNameForMOType(String objectType)
	{
		Vector objVector = null;
		String objectName = "EMPTY-OBJECT-NAME";	
		TopoAPI topoAPI ;

		Properties prop = new Properties();
		prop.put("classname",objectType);
		
		TopoTestUtils ttu = new TopoTestUtils();
		topoAPI = ttu.getTopoAPI();
		
		try
		{
		objVector = topoAPI.getObjectNamesWithProps(prop);
		}
		catch (Exception ion)
		{
			ion.printStackTrace();
		}
		
		int size = objVector.size();	
		System.out.println("Number of Objects :->>  " +size); 

		if(objVector.size() != 0)
		{
			objectName = (String) objVector.elementAt(0);					
			return objectName;
		}
		else
		{
			System.out.println("No Object Matches with the type=" +objectType);
			System.out.println("OBJECT NAME -------->> " +objectName); 
			return objectName;
		}

	}


	public static Properties getPropertiesForMOType(String objectType)
	{
		Vector objVector = null;
		TopoAPI topoAPI ;

		Properties prop , prop1;
		prop = new Properties();
		prop.put("classname",objectType);
		
		prop1 = null;

		TopoTestUtils ttu = new TopoTestUtils();
		topoAPI = ttu.getTopoAPI();
		
		try
		{
		objVector = topoAPI.getObjectNamesWithProps(prop);
		}
		catch (Exception ion)
		{
			ion.printStackTrace();
		}

		if(objVector.size() != 0)
		{
			try
			{
				String vectorElement = (String)objVector.firstElement();
				ManagedObject mo;
				mo = (ManagedObject)topoAPI.getByName(vectorElement);
				return mo.getProperties();
			}
			catch (Exception ion)
			{
				ion.printStackTrace();
			}
		}
		/*
		else 
		{
		System.out.println("GetObject Names With Props return Null Vector ie, the size of the returned vector is 0 : and hence returining the empty property   ::>>" +prop1); 
		}
		*/
		return prop1;
	}

	public static void main(String args[])
	{
		TopoTestUtils topoTestUtils;
		topoTestUtils = new TopoTestUtils();

	}
}
