/* $Id: SwitchTopoListener.java,v 1.9 2008/09/19 12:53:24 tinku Exp $ */
package test;

import java.util.*;
import java.io.*;
import java.rmi.server.*;
import java.rmi.*;
import javax.transaction.*;
import java.sql.*;
import java.text.MessageFormat;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.netwatch.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.store.*;
import com.adventnet.management.transaction.*;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

public class SwitchTopoListener implements TopoBulkListener, Serializable
{
    public boolean deleteObject(XMLNode data) throws NmsException
    {
	if(data == null) return false;
	
	//XMLDataWriter writer = new XMLDataWriter("test.file",data);
	
	Vector subNodes = data.getChildNodes();
	
	for(Enumeration e = subNodes.elements();e.hasMoreElements();)
	{
	    XMLNode childNode = (XMLNode)e.nextElement();
	    if( (childNode == null) || (childNode.getNodeType() != XMLNode.ELEMENT) )
	    {
		continue;
	    }
	    if(childNode.getNodeName().equals("DATA"))
	    {
		Hashtable ht = childNode.getAttributeList();
		String objName = (String)ht.get("parentObjectKey");
		String query = (String)ht.get("sqlQuery");
		if(objName == null) continue;
		
		TopoAPI api = (TopoDB)NmsUtil.getAPI("TopoAPI");
		
		ManagedObject mobj = null;
		try
		{
		    mobj = api.checkOut(objName,0,false,true);
		}
		catch(Exception e2){System.err.println(NmsUtil.GetString("Cannot get object ")+objName+" "+e2);};
		
		if (mobj == null) return false;
		
		if(mobj instanceof Network)
		{
		    
		    /*Properties props = new Properties();
		      props.put("classname","SwitchObject");
		      props.put("ipAddress","192.168.1.*");*/
		    
		    Vector switches = null;
		    try
		    {
			//switches = api.getObjectNamesWithProps(props);
			switches = getSwitchesBelongingToNetwork(mobj.getName());
			if( (switches == null) || switches.isEmpty()) return false;
			
			for(Enumeration enu = switches.elements();enu.hasMoreElements();)
			{
			    String switchName = enu.nextElement().toString();
				Object[] args={switchName};
			    DiscoveryAPI.println(MessageFormat.format(NmsUtil.GetString("Trying to delete {0} from Listener "),args) + api.deleteObjectAndSubElements(switchName));
			}
		    }
		    catch(NmsStorageException nse)
		    {
			throw new NmsException(nse.getMessage(),NmsException.ROLLBACK);
		    }
		    catch(Exception e4)
		    { 
			System.err.println(NmsUtil.GetString("Exception from TestTopoListener ")+e4);
		    }
		    return true;
		}
		else return true;
	    }
	}
	return false;
    }//end deleteObject() method
    
    private Vector getSwitchesBelongingToNetwork(String name) throws NmsStorageException
    {
        String query = "select IpAddress.PARENTNODE from ManagedObject,IpAddress where (ManagedObject.NAME = IpAddress.PARENTNODE) and (ManagedObject.CLASSNAME='SwitchObject') and (IpAddress.PARENTNET = '"+name+"' )";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector retVect = null;
        try
        {
            beginTxn();
        }
        catch(UserTransactionException ute)
        {
	   throw new NmsStorageException(ute.getMessage(),ute);
        }
	
        try
        {
            ps = DBServer.comapi.comtorel.relapi.getPreparedStatement(query,true);
            if (ps == null) return null;
            rs = DBServer.comapi.comtorel.relapi.executeQuery(ps);
            retVect = new Vector();
            while(rs.next())
            {
                retVect.addElement(rs.getString(1));
            }
	    commitTxn();
        }
        catch(UserTransactionException ute)
        {
	   throw new NmsStorageException(ute.getMessage(),ute);
        }
        catch(SystemException se)
        {
	   throw new NmsStorageException(se.getMessage(),se);
        }
        catch(NmsStorageException nse)
        {
	   throw new NmsStorageException(nse.getMessage(),nse);
        }
        
        catch(Exception sqle)
        {
            try
            {
               rollbackTxn(sqle.getMessage());
            }
            catch(UserTransactionException se)
            {
					throw new NmsStorageException(se.getMessage(),se);
            }
            throw new NmsStorageException(sqle.getMessage(),sqle);
        }
        finally
        {
            try
            {
                //[RAJAGOPAL] : Firebird support
                // ResultSet is closed before the PreparedStatement
                if(rs!=null)
                {
                    rs.close();
                }
                if(ps!=null)
                {
                    ps.close();
                }
            }
            catch(Exception e)
            {
                //Firebird throws SQLException when we try to close the already
                //closed ResultSet
                //e.printStackTrace();
            }
        }
        return retVect;
    }
 	public boolean addObjects(Vector managedObjects) throws RemoteException,NmsException
	{
		return true;
	}

   private void beginTxn() throws UserTransactionException
   {
      try
      {
         TransactionAPI.getInstance().begin();
      }
      catch(NotSupportedException nse)
      {
         System.err.println("Transaction Not supported : "+nse);//NO I18N
      }
      catch(SystemException se)
      {
         throw new UserTransactionException(se.getMessage(),se);
      }
   }

   private void commitTxn() throws UserTransactionException,SystemException
   {
      try
      {
         TransactionAPI.getInstance().commit();
      }
      catch(RollbackException re)
      {
         throw new UserTransactionException(re.getMessage(),re);
      }
      catch(HeuristicMixedException e)
      {
         rollbackTxn(e.getMessage());
         throw new UserTransactionException(e.getMessage(),e);
      }
      catch(HeuristicRollbackException e)
      {
         rollbackTxn(e.getMessage());
         throw new UserTransactionException(e.getMessage(),e);
      }
   }

   private void rollbackTxn(String message) throws UserTransactionException
   {
         TransactionAPI.getInstance().rollback(message);
   }
   
}//end of class

