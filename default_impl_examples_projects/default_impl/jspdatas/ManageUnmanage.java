/* $Id: ManageUnmanage.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package com.adventnet.nms.jsp;

import java.util.*;

import com.adventnet.nms.fe.topo.TopoFE;
import com.adventnet.nms.fe.topo.TopoSessionBean;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.fe.common.ModuleNotInitializedException;

public class ManageUnmanage
{
	private static TopoSessionBean topoSession = null;

	public ManageUnmanage(){
	}

	/**
	 * Used the get status of managing or unmanaging 
	 *
	 * @return java.lang.String object giving the status of Managed/UnManaged
	 * @param parameter the java.util.Properties object  
	 *
	 **/
	public String getManageUnmanage(Properties parameters) throws ModuleNotInitializedException
	{
		if ( topoSession == null ){
			try{
				topoSession = TopoFE.getTopoSession();
			}catch(ModuleNotInitializedException e){
				NmsLogMgr.MISCERR.fail("Exception while reference to TopoSessionBean",e);
				throw e;
			}
		}

		Properties objectProperties = null;
		String toReturn = "Request Successful";
		String userName = parameters.getProperty("userName");
                
                Properties p = new Properties();
                String temp = parameters.getProperty("name");
                if(temp != null && temp.trim().length() > 0)
                    {
                        p.setProperty("auditedObj", temp);
                    }
                p.setProperty("category", "Topo");

		if(!GenericUtility.isAuthorized(parameters.getProperty("userName"), "Modify Object",p))
		{
			toReturn = GenericUtility.unAuthorizedString;
			return toReturn;
		}

		String entity = parameters.getProperty("name");
		if(entity == null || entity.equals(""))
		{
			toReturn = "No ManagedObject Selected." ;
			return toReturn;                
		}            

		try
		{
			objectProperties=topoSession.getPropertiesOfObject(userName,entity);
			if (objectProperties == null) 
			{
				toReturn = "Failed to get Object from API";
				return toReturn;
			}
			String type = objectProperties.getProperty("type");
			if (type.equalsIgnoreCase("Node"))
			{
				topoSession.setManaged(userName,entity,parameters.containsKey("Manage"),"");
			}
			else if(type.equalsIgnoreCase("Network")) 
			{
				topoSession.setManaged(userName,entity,parameters.containsKey("Manage"),"");
			}
			else {
				topoSession.setManaged(userName,entity,parameters.containsKey("Manage"));
			}
		}
		catch (Exception re)
		{
			NmsLogMgr.MISCERR.fail("error updating properties of ManagedObject "+entity+" ",re);
			toReturn = "Request could not be successfully processed.Please refer logs for details";
			return toReturn;
		}
		return toReturn;	
	}
}
