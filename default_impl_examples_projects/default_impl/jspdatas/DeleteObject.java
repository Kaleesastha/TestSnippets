/* $Id: DeleteObject.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package com.adventnet.nms.jsp;

import java.util.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.management.log.Log;
import com.adventnet.nms.fe.topo.TopoFE;
import com.adventnet.nms.fe.topo.TopoSessionBean;
import com.adventnet.nms.fe.common.ModuleNotInitializedException;

//CHANGE START
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.ContainerInterface;
import com.adventnet.nms.fe.utils.NmsFEUtil;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.LockableObject;
import com.adventnet.nms.topodb.Network;
import com.adventnet.nms.topodb.Node;
//CHANGE END

public class DeleteObject {

	private static TopoSessionBean topoSession = null;
	public DeleteObject(){
	}
	private String toReturn = "Request could not be processed successfully.Please refer to logs for more details.";
	private String userName = null;

	/**
	 * Used to delete the object
	 * @return java.lang.String object which tells if the object has been deleted
	 * @param prop the java.util.Properties object of the object being deleted
	 **/
	public String  deleteObject(Properties prop) throws ModuleNotInitializedException {

		userName = prop.getProperty("userName");
                Properties p = new Properties();
                String temp = prop.get("name").toString();
                if(temp != null && temp.trim().length() > 0)
                    {
                        p.setProperty("auditedObj", temp);
                    }
                p.setProperty("category", "Topo");

		if( userName == null || userName.trim().equals("") || userName.equals("null") || ! GenericUtility.isAuthorized(userName, "Delete Object",p) ){
			return GenericUtility.unAuthorizedString;
		}

		if ( topoSession == null ){
			try{
				topoSession = TopoFE.getTopoSession();
			}catch(ModuleNotInitializedException e){
				NmsLogMgr.MISCERR.fail("Exception while reference to TopoSessionBean",e);
				throw e;
			}
		}

        final Object name = prop.get("name");

        NmsLogMgr.MISCUSER.log("User "+userName+" trying to delete : "+ name, Log.SUMMARY);
		if(name == null)
        {
            toReturn = "Please fill in the object Name field.";
            return toReturn;
        }

		try
		{
			Thread deleteNetworkThread = new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            //this vector is added for having the multiple names of the object as a vector instead of comma separated.
                            Vector nameVect = new Vector();
                            if(name instanceof String)
                            {
                                nameVect.addElement(name);
                            }
                            else
                            {
                                nameVect = (Vector)name;
                            }
                            Enumeration e = nameVect.elements();
                            while( e.hasMoreElements())
                            {
                                String objName = (String)e.nextElement();
                                //CHANGE START
                                if(!isSubQueryNotSupportedDelete(objName))
                                {
                                    topoSession.deleteObjectAndSubElements(userName,objName);
                                }
                                //CHANGE END
                            }
                        }
                        catch(Exception e)
                        {
						NmsLogMgr.MISCERR.fail("Exception caught in DeleteObject.java ",e);
						toReturn = "Error deleting object. Please refer to logs for more details.";
                        }
                    }
                };
			deleteNetworkThread.start();
			toReturn = "Request is being processed.";
		}
		catch (Exception e) {
			NmsLogMgr.MISCERR.fail("Exception caught in DeleteObject:"+ e.getMessage(),e);
			toReturn = "Error deleting object. Please refer to logs for more details.";
		}
		return toReturn;
	}

    //CHANGE START

    /**this method returns true, if following conditions are satisfied.
     * database does not support subquery or database is Mysql version 4.1.
     * object to be deleted is a container.
     * not a network
     * for all other condition this method returns false so that normal delete will be invoked.
     **/

    private boolean isSubQueryNotSupportedDelete(String objName)
    {
        try
        {
            TopoAPI api = (TopoAPI)NmsUtil.getAPI("TopoAPI");//No i18N
            if(api == null)
            {
                return false;
            }
            //check for mysql database
            //if(NmsFEUtil.getDatabaseName().toLowerCase().indexOf("mysql") != -1)
            //check done for subQueryNotSupported as Mysql 4.1.28 or less will return subQueryNotSupported
            if (!NmsUtil.isSubQuerySupported())
            {
                NmsLogMgr.MISCUSER.log("Database for non subquery delete : "+NmsFEUtil.getDatabaseName(), Log.DEBUG);//No i18N

                ManagedObject obj = api.getByName(objName);
                //if instance of network dont delete;
                if (obj instanceof Network)
                {
                    return false;
                }
                // if object is instanceof Container then delete;
                if(obj.getIsContainer() || obj instanceof ContainerInterface)
                {

                    invokeRecursiveDelete(api,obj);
                    return true;
                }
            }
            return false;
        }
        catch(Exception ee)
        {
            NmsLogMgr.MISCERR.fail(" Error while invoking Non Sub-query Delete "+ee.getMessage(), ee);//No i18N
        }
        return false;
    }

    //recursive delete utility method
    private void invokeRecursiveDelete(TopoAPI api,ManagedObject mo) throws Exception
    {
        try
        {
            Properties props = new Properties();
            props.put("parentKey", mo.getName());
            Vector objects = api.getObjectNamesWithProps(props);
            int size = objects.size();
            if(size > 0)
            {
                for(int i=0; i < size; i++)
                {
                    ManagedObject childMo = api.getByName((String)objects.get(i));
                    if(childMo != null)
                    {
                        invokeRecursiveDelete(api, childMo);
                    }
                }
            }
            if (mo instanceof Node)
            {
               Vector interfaces = ((Node)mo).getInterfacesList();
               for (int j=0;j<interfaces.size();j++)
               {
                   ManagedObject inf = api.getInterface((String)interfaces.get(j));
                   //lock the interface obj before deletion
                   inf = api.lock(inf,LockableObject.WRITE_LOCK,10);
                   //delete with lock enabled. dont delete subelements
                   api.deleteObject(inf,false,true);
               }
            }
            //lock the MO before deletion
            mo = api.lock(mo,LockableObject.WRITE_LOCK,10);
            //delete with lock enabled. dont delete subelements
            api.deleteObject(mo,false,true);
        }
        catch(Exception ee)
        {
            NmsLogMgr.MISCERR.fail(" Error while Recursive Delete"+ee.getMessage(), ee);//No i18N
            throw ee;
        }
    }

    //CHANGE END
}