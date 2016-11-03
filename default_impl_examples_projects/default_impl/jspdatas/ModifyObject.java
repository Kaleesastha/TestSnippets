
/* $Id: ModifyObject.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/**
 *  ModifyObject.java
 **/


package com.adventnet.nms.jsp;

import java.util.Properties;
import java.rmi.RemoteException;

import com.adventnet.nms.fe.topo.TopoSessionBean;
import com.adventnet.nms.fe.topo.TopoFE;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.fe.common.ModuleNotInitializedException;

public class ModifyObject
{
	
	private static TopoSessionBean topoSession = null;
	public ModifyObject(){
	}
	//Initialize String toReturn
	String toReturn = NmsUtil.GetString("Request could not be processed successfully.Please refer to logs for more details.");
	public String getTheMessage(Properties prop) throws ModuleNotInitializedException
	{
		if ( topoSession == null ){
			try{
				topoSession = TopoFE.getTopoSession();
			}catch(ModuleNotInitializedException e){
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while reference to TopoSessionBean"),e);
				throw e;
			}
		}

		String userName = (String)prop.remove("userName");

                Properties p = new Properties();
                String temp = prop.getProperty("name");
                if(temp != null && temp.trim().length() > 0)
                    {
                        p.setProperty("auditedObj", temp);
                    }
                p.setProperty("category", "Topo");

		if(GenericUtility.isAuthorized(userName, "Modify Object", p))
		{      
			//Declare array ik with prefered Key items  
			String ik[] = { "displayName", "name","managed","inUse","uClass","tester","pollInterval","failureThreshold","discover"};

			Properties pr = new Properties();

			//Select the values for predefined key items from the parameters list
			for (int i=0;i<ik.length;i++)
			{  
				String key = ik[i];
				String val =  prop.getProperty(key);
				if (val!=null)
					pr.put(key,val);
			}
			String objname = prop.getProperty("name");
			Properties objProperties = null;
			try{
				objProperties = topoSession.getPropertiesOfObject(userName,objname);
			}catch(RemoteException e){
				NmsLogMgr.MISCERR.fail("Exception while getting the object properties",e);
			}
			if (objProperties == null)
			{
				toReturn = NmsUtil.GetString("The Object with the given name not present in database");
				return toReturn;
			}
			try
			{
				boolean status = false;
				try{
					status= topoSession.updateObjectProperties(userName,pr);
				}catch(RemoteException e){
					NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while updating the object properties"),e);
				}
				if (status) 
				{
					toReturn = NmsUtil.GetString("The Object ") + objname + NmsUtil.GetString(" was successfully modified.");
					return toReturn;
				}
				else 
				{
					return toReturn;
				}
			}
			catch (Exception e)
			{            
				//Message Error in modifying object
				toReturn = NmsUtil.GetString("Exception in modifying Object. Please refer to logs for more details.");
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception caught in ModifyObject ") ,e);            
				return toReturn;
			}
		}
		else
		{
			//Message for unAuthorized user
			toReturn = GenericUtility.unAuthorizedString;
		}

		return toReturn;
	}
}
