/**
 * $Id: TransExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 */

package test;

import java.sql.*;
import java.io.*;
import java.util.*;
import javax.transaction.*;
import java.rmi.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.net.URL;
import com.adventnet.nms.util.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.store.*;
import com.adventnet.management.transaction.*;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;

import com.adventnet.management.transaction.UserTransactionException;



public class TransExample extends HttpServlet
{
    HttpServletRequest req = null;
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {
        doGet(req, res);
    }
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		Hashtable ht = new Hashtable();

                for(Enumeration parameterNames = req.getParameterNames();
                        parameterNames.hasMoreElements();)
                {
                        String param = (String)parameterNames.nextElement();
                        String value = (String)req.getParameter(param);
 
                        if (value == null) value = "-";
 
                        ht.put(param,value);
                }
		boolean isCommit= true; 
                
		String  moName = (String)(ht.get("moname"));
		if(moName==null) 
			moName = "ManagedObject";
	
		String commit =	(String)(ht.get("iscommit"));
		if (commit !=null) 
                	isCommit = new Boolean(commit).booleanValue();     
		
		TopoAPI tapi=null;
	        MapAPI mapi=null;
        	TransactionAPI transAPI=null;         
		try {
			tapi = (TopoAPI)NmsUtil.getAPI("TopoAPI");
			mapi = (MapAPI)NmsUtil.getAPI("MapAPI");
			transAPI = NmsUtil.relapi.getTransactionAPI();
		}
		catch(Exception e){
			System.err.println("Exception in getting the handle of MAPI,TopoAPI"+e);
		}

		try {
			transAPI.begin(TransactionAPI.NOTIFY_TIP,20000);//This implies TIP notification is enabled with 20000 ms as transaction timeout value
		}
		catch(SystemException se) {
			System.err.println("Thrown if system resources are not available"+se);
		}
		catch(NotSupportedException nse){
			System.err.println("Thrown in case of databases not supporting transaction"+nse);
		}

		try {
			ManagedObject mo = new ManagedObject();
			mo.setName(moName);
			tapi.addObject(mo);  //inserts in the ManagedObject table

			String mapName = "ipnet.netmap";
			Properties p = new Properties();
			p.put("name",moName+"_Symbol"); 
			p.put("objName", moName);
			p.put("iconName", "pc.png");
			p.put("mapName",mapName); 
			mapi.addSymbol(mapName,p);//inserts into the MapSymbol table

			if(!isCommit) { //throw exception for rollback
		
	     	try{
                transAPI.rollback();
                }catch(Exception e)
	            {
                System.out.println("Exception thrown for rollback" + e.getMessage());
				throw new Exception("Exception for RollBack");
                }   		
			}

			transAPI.commit();
			System.out.println("ManagedObject "+moName +" added to database");
			System.out.println("MapSymbol "+moName+"_Symbol added to database");
		}

                // Whenever a UserTransactionException is thrown the 
                // transaction as such would have been rolled back! Therefore, we can either 
                // attempt a retry of the request or return an error message saying the particular
                // operation is unsucessful. We are here doing the latter.
                catch(UserTransactionException ute)
                    {
                        try {
                            PrintWriter out = res.getWriter();
                            out.println("Unable to add ");
                            out.println(moName);
                            out.println("Cause: " + ute.getMessage() );
                            out.flush();
                            return;
                        }
                        catch(Exception exc){}
                    }
                // This exception will be thrown whenever there are any issues in updating the database
                catch(NmsStorageException nse)
                    {
                        nse.printStackTrace();
			try {
				transAPI.rollback(nse.getMessage());//rollback in case of exception
			}
			catch(Exception e) {}
                    }

		catch(RemoteException re)
		{
			re.printStackTrace();
			try {
				transAPI.rollback(re.getMessage());//rollback in case of exception
			}
			catch(Exception e) {}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try {
				transAPI.rollback(e.getMessage());//rollback in case of exception
			}
			catch(Exception ee) {}
		}
        }                
}







