/* $Id: StartStopDiscovery.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

/**
 *  StartStopDiscovery.java
 */

package com.adventnet.nms.jsp;

import java.util.*;

import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.fe.topo.TopoFE;
import com.adventnet.nms.fe.topo.TopoSessionBean;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.fe.common.ModuleNotInitializedException;

public class StartStopDiscovery
{
    String toReturn = "Request Successful";
	private static TopoSessionBean topoSession = null;
   	public StartStopDiscovery(){
	}
    public String getTheMessage(Properties parameters) throws ModuleNotInitializedException
	{
		if ( topoSession == null ){
			try{
				topoSession = TopoFE.getTopoSession();
			}catch(ModuleNotInitializedException e){
				NmsLogMgr.MISCERR.fail("Exception while reference to TopoSessionBean",e);
				throw e;
			}
		}

		String userName = parameters.getProperty("userName");

                Properties p = new Properties();
                String temp = p.getProperty("name");
                if(temp != null && temp.trim().length() > 0)
                    {
                        p.setProperty("auditedObj", temp);
                    }
                p.setProperty("category", "Topo");


		if(!GenericUtility.isAuthorized(userName, "Modify Object", p))
		{
			toReturn = GenericUtility.unAuthorizedString;
			return toReturn;
		}

		String objName = parameters.getProperty("name");
		if(objName == null || objName.equals(""))
		{
			//Message invalid object name
			toReturn = "No ManagedObject Selected." ;
			return toReturn;                
		}

		try
		{
			//start Discovery
			topoSession.setDiscover(userName,objName,parameters.containsKey("StartDiscovery"));
		}
		catch (Exception anye)
		{
			//Message when setDiscover failss
			System.err.println(" Exception setting propertiesOf of object-"+objName+" "+anye);            toReturn = "An error occured while processing your request. Please refer to logs for more details.";            return toReturn;
		}

		return toReturn;
	}
}
