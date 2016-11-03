// $Id: NetworkDetailFilter.java,v 1.3 2008/06/10 10:02:01 karanmercy Exp $

package com.adventnet.nms.example.newobject;


import java.rmi.RemoteException;
import java.util.Properties;

import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.UserTransactionException;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.topodb.FoundFilter;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.TopoObject;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;

/**
 * This class has sample code that demonstrates the following:
 *  1. The skeleton code to represent the structure of a simple discovery filter.
 *  2. It contains WebNMS transaction methods to demonstrate how transaction can
 * be used while adding/updating objects in the database.
 *  3. It also contains code sample to send SNMP queries to the device, this can 
 * be used as a guideline for interacting with the device to retrieve its properties.
 *  4. In addition it outlines a basic business logic of how a discovered object is
 * handled inside the Discovery Filter before being added to the WebNMS database. 
 * 
 * As this class is just a sample, the code implementation provided for the 
 * filterObject(ManagedObject, TopoAPI) method can be changed as per user requirement.
 * 
 * Please Note that the DiscoveryFilter has to implement the FounfFilter interface and
 * hence it is mandatory to implement the filterObject method.
 */

public class NetworkDetailFilter implements FoundFilter
{
	private TransactionAPI transactionAPI = null;

	public ManagedObject filterObject(ManagedObject obj, TopoAPI topoApi) throws NmsStorageException, UserTransactionException
	{
		if (obj == null)  
			return null; 

		if ( ((TopoObject)obj).getIsNetwork() )
		{
			NetworkDetail nd = new NetworkDetail();
			Properties p =obj.getProperties();
			String name= obj.getName();
			nd.setName(name);
			int netClass=0;
			try
			{
				 netClass = new Integer(name.substring(0,name.indexOf("."))).intValue();//No I18N
			}
			catch ( NumberFormatException nfe)
			{
				System.err.println(" Error in detemining the class for a network");//No I18N
				nfe.printStackTrace();
			}
			String netType="";//No I18N
			if ( netClass <= 127)
					netType="Class A";//No I18N
			else if ( netClass >= 128 && netClass < 192 )
					netType="Class B";//No I18N
			else if ( netClass >=192 && netClass <= 223 )
					netType="Class C";//No I18N
			else if ( netClass >= 224 && netClass <= 239 )
					netType="Class D";//No I18N
			else if ( netClass >= 240 && netClass <= 247 )
					netType="Class E";//No I18N
			
			p.put("networkType",netType);//No I18N
			nd.setProperties(p);
			try
			{
				topoApi.addObject(nd);
			}

	    catch(NmsStorageException nse)
	    {
		throw nse;
     	    }
	    catch(UserTransactionException ute)
	    {
		throw ute;
	    } catch (RemoteException re) {
				System.err.println(" Error in adding NetworkDetail");//No I18N
				re.printStackTrace();
			}
			return null;//returning null as the networkDetail object is already added to database.
		}
		return obj;
	}
	private void initTransactionAPI()
	{ 
		try
		{
			transactionAPI = NmsUtil.relapi.getTransactionAPI();
		}
		catch(Exception ex)
		{
			NmsLogMgr.TOPOERR.fail("Exception in getting the handle of TransactionAPI ", ex); //No I18N
		}
	} 
	private void beginTransaction()
	{ 
		try
		{
			transactionAPI.begin();
		}
		catch(javax.transaction.NotSupportedException nse)
		{
		}
		catch(Exception ex)
		{
			NmsLogMgr.TOPOERR.fail("Exception in beginning the transaction ",ex);//No I18N
		}
	} 
	private void commitTransaction()
	{ 
		try
		{
			transactionAPI.commit();
		}
		catch(Exception ex)
		{
			NmsLogMgr.TOPOERR.fail("Exception in committing the transaction ",ex);//No I18N
		}
	} 
	private void rollbackTransaction(Exception exception)
	{ 
		try
		{
			transactionAPI.rollback(exception.getMessage());
		}
		catch(Exception ex)
		{
			NmsLogMgr.TOPOERR.fail("Exception in rolling back the transaction ",ex);//No I18N
		}
	} 

}
