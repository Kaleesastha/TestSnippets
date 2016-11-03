/* $Id: RefreshNode.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
package com.adventnet.nms.jsp;

import java.util.*;
import com.adventnet.nms.fe.topo.TopoFE;
import com.adventnet.nms.fe.topo.TopoSessionBean;
import com.adventnet.nms.topodb.TopoObject;
import com.adventnet.nms.topodb.Node;
import com.adventnet.nms.topodb.DBServer;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.fe.common.ModuleNotInitializedException;

public class RefreshNode
{

	private static TopoSessionBean topoSession = null;

	public RefreshNode()
	{
	}

	public boolean refreshNode(Properties pr) throws Exception, ModuleNotInitializedException
	{
        
        
		String userName = (String)pr.remove("userName");        

                Properties p = new Properties();
                String temp = pr.getProperty("Node");
                if(temp != null && temp.trim().length() > 0)
                    {
                        p.setProperty("auditedObj", temp);
                    }
                p.setProperty("category", "Topo");

		if( userName == null || userName.trim().equals("") || userName.equals("null") || ! GenericUtility.isAuthorized(userName,"Refresh Node",p) ){
			throw new Exception(GenericUtility.unAuthorizedString);
		}

		if ( topoSession == null ){
			try{
				topoSession = TopoFE.getTopoSession();
			}catch(ModuleNotInitializedException e){
				NmsLogMgr.MISCERR.fail("Exception while reference to TopoSessionBean",e);
				throw e;
			}
		}
 		String toReturn = "Request could not be processed. Please refer to logs for more details";
		Properties objectProperties = new Properties();

		String name = pr.getProperty("Node");
		objectProperties.setProperty("name",name);
		// Check for valid Name
		if((name==null) || name.trim().equals("") || name.trim().equals("0.0.0.0") ) 
		{
			throw new Exception( "The object must be named.");
		}
        objectProperties.setProperty("netmask", pr.getProperty("NetMask"));
		objectProperties.setProperty("snmpport", pr.getProperty("SnmpAgentPort"));

        
		boolean result=false;
		try
		{  
			result = topoSession.refreshObject(userName, name, objectProperties);
		}
		catch (Exception e)
		{
			NmsLogMgr.MISCERR.fail("Exception caught in RefreshNode: " + e.getMessage(),e);            
			return false;
		}        
		return result;
	}
}
